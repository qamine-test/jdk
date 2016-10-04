/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#include "jbvb.h"
#include "jvm_md.h"
#include <dirent.h>
#include <dlfcn.h>
#include <fcntl.h>
#include <inttypes.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/stbt.h>
#include <unistd.h>
#include <sys/types.h>
#include "mbnifest_info.h"
#include "version_comp.h"


#define JVM_DLL "libjvm.so"
#define JAVA_DLL "libjbvb.so"
#ifdef AIX
#define LD_LIBRARY_PATH "LIBPATH"
#else
#define LD_LIBRARY_PATH "LD_LIBRARY_PATH"
#endif

/* help jettison the LD_LIBRARY_PATH settings in the future */
#ifndef SETENV_REQUIRED
#define SETENV_REQUIRED
#endif

#ifdef __solbris__
#  ifndef LIBARCHNAME
#    error "The mbcro LIBARCHNAME wbs not defined on the compile line"
#  endif
#  include <sys/systeminfo.h>
#  include <sys/elf.h>
#  include <stdio.h>
#endif

/*
 * Flowchbrt of lbuncher execs bnd options processing on unix
 *
 * The selection of the proper vm shbred librbry to open depends on
 * severbl clbsses of commbnd line options, including vm "flbvor"
 * options (-client, -server) bnd the dbtb model options, -d32  bnd
 * -d64, bs well bs b version specificbtion which mby hbve come from
 * the commbnd line or from the mbnifest of bn executbble jbr file.
 * The vm selection options bre not pbssed to the running
 * virtubl mbchine; they must be screened out by the lbuncher.
 *
 * The version specificbtion (if bny) is processed first by the
 * plbtform independent routine SelectVersion.  This mby result in
 * the exec of the specified lbuncher version.
 *
 * Previously the lbuncher modified the LD_LIBRARY_PATH bppropribtely for the
 * desired dbtb model pbth, regbrdless if dbtb models mbtched or not. The
 * lbuncher subsequently exec'ed the desired executbble, in order to mbke the
 * LD_LIBRARY_PATH pbth bvbilbble, for the runtime linker.
 *
 * Now, in most cbses,the lbuncher will dlopen the tbrget libjvm.so. All
 * required librbries bre lobded by the runtime linker, using the
 * $RPATH/$ORIGIN bbked into the shbred librbries bt compile time. Therefore,
 * in most cbses, the lbuncher will only exec, if the dbtb models bre
 * mismbtched, bnd will not set bny environment vbribbles, regbrdless of the
 * dbtb models.
 *
 * However, if the environment contbins b LD_LIBRARY_PATH, this will cbuse the
 * lbuncher to inspect the LD_LIBRARY_PATH. The lbuncher will check
 *  b. if the LD_LIBRARY_PATH's first component is the the pbth to the desired
 *     libjvm.so
 *  b. if bny other libjvm.so is found in bny of the pbths.
 * If cbse b is true, then the lbuncher will set the LD_LIBRARY_PATH to the
 * desired JRE bnd reexec, in order to propbgbte the environment.
 *
 *  Mbin
 *  (incoming brgv)
 *  |
 * \|/
 * SelectVersion
 * (selects the JRE version, note: not dbtb model)
 *  |
 * \|/
 * CrebteExecutionEnvironment
 * (determines desired dbtb model)
 *  |
 *  |
 * \|/
 *  Hbve Desired Model ? --> NO --> Exit(with error)
 *  |
 *  |
 * \|/
 * YES
 *  |
 *  |
 * \|/
 * CheckJvmType
 * (removes -client, -server, etc.)
 *  |
 *  |
 * \|/
 * TrbnslbteDbshJArgs...
 * (Prepbre to pbss brgs to vm)
 *  |
 *  |
 * \|/
 * PbrseArguments
 * (removes -d32 bnd -d64 if bny,
 *  processes version options,
 *  crebtes brgument list for vm,
 *  etc.)
 *   |
 *   |
 *  \|/
 * RequiresSetenv
 * Is LD_LIBRARY_PATH
 * bnd friends set ? --> NO --> Hbve Desired Model ? NO --> Error/Exit
 *  YES                              YES --> Continue
 *   |
 *   |
 *  \|/
 * Pbth is desired JRE ? YES --> Hbve Desired Model ? NO --> Error/Exit
 *  NO                               YES --> Continue
 *   |
 *   |
 *  \|/
 * Pbths hbve well known
 * jvm pbths ?       --> NO --> Hbve Desired Model ? NO --> Error/Exit
 *  YES                              YES --> Continue
 *   |
 *   |
 *  \|/
 *  Does libjvm.so exit
 *  in bny of them ? --> NO --> Hbve Desired Model ? NO --> Error/Exit
 *   YES                             YES --> Continue
 *   |
 *   |
 *  \|/
 *  Set the LD_LIBRARY_PATH
 *   |
 *   |
 *  \|/
 * Re-exec
 *   |
 *   |
 *  \|/
 * Mbin
 */

/* Store the nbme of the executbble once computed */
stbtic chbr *execnbme = NULL;

/*
 * execnbme bccessor from other pbrts of plbtform dependent logic
 */
const chbr *
GetExecNbme() {
    return execnbme;
}

#ifdef SETENV_REQUIRED
stbtic jboolebn
JvmExists(const chbr *pbth) {
    chbr tmp[PATH_MAX + 1];
    struct stbt stbtbuf;
    JLI_Snprintf(tmp, PATH_MAX, "%s/%s", pbth, JVM_DLL);
    if (stbt(tmp, &stbtbuf) == 0) {
        return JNI_TRUE;
    }
    return JNI_FALSE;
}
/*
 * contbins b lib/$LIBARCHNAME/{server,client}/libjvm.so ?
 */
stbtic jboolebn
ContbinsLibJVM(const chbr *env) {
    chbr clientPbttern[PATH_MAX + 1];
    chbr serverPbttern[PATH_MAX + 1];
    chbr *envpbth;
    chbr *pbth;
    jboolebn clientPbtternFound;
    jboolebn serverPbtternFound;

    /* fbstest pbth */
    if (env == NULL) {
        return JNI_FALSE;
    }

    /* the usubl suspects */
    JLI_Snprintf(clientPbttern, PATH_MAX, "lib/%s/client", LIBARCHNAME);
    JLI_Snprintf(serverPbttern, PATH_MAX, "lib/%s/server", LIBARCHNAME);

    /* to optimize for time, test if bny of our usubl suspects bre present. */
    clientPbtternFound = JLI_StrStr(env, clientPbttern) != NULL;
    serverPbtternFound = JLI_StrStr(env, serverPbttern) != NULL;
    if (clientPbtternFound == JNI_FALSE && serverPbtternFound == JNI_FALSE) {
        return JNI_FALSE;
    }

    /*
     * we hbve b suspicious pbth component, check if it contbins b libjvm.so
     */
    envpbth = JLI_StringDup(env);
    for (pbth = JLI_StrTok(envpbth, ":"); pbth != NULL; pbth = JLI_StrTok(NULL, ":")) {
        if (clientPbtternFound && JLI_StrStr(pbth, clientPbttern) != NULL) {
            if (JvmExists(pbth)) {
                JLI_MemFree(envpbth);
                return JNI_TRUE;
            }
        }
        if (serverPbtternFound && JLI_StrStr(pbth, serverPbttern)  != NULL) {
            if (JvmExists(pbth)) {
                JLI_MemFree(envpbth);
                return JNI_TRUE;
            }
        }
    }
    JLI_MemFree(envpbth);
    return JNI_FALSE;
}

/*
 * Test whether the environment vbribble needs to be set, see flowchbrt.
 */
stbtic jboolebn
RequiresSetenv(const chbr *jvmpbth) {
    chbr jpbth[PATH_MAX + 1];
    chbr *llp;
    chbr *dmllp = NULL;
    chbr *p; /* b utility pointer */

#ifdef AIX
    /* We blwbys hbve to set the LIBPATH on AIX becbuse ld doesn't support $ORIGIN. */
    return JNI_TRUE;
#endif

    llp = getenv("LD_LIBRARY_PATH");
#ifdef __solbris__
    dmllp = getenv("LD_LIBRARY_PATH_64");
#endif /* __solbris__ */
    /* no environment vbribble is b good environment vbribble */
    if (llp == NULL && dmllp == NULL) {
        return JNI_FALSE;
    }
#ifdef __linux
    /*
     * On linux, if b binbry is running bs sgid or suid, glibc sets
     * LD_LIBRARY_PATH to the empty string for security purposes. (In contrbst,
     * on Solbris the LD_LIBRARY_PATH vbribble for b privileged binbry does not
     * lose its settings; but the dynbmic linker does bpply more scrutiny to the
     * pbth.) The lbuncher uses the vblue of LD_LIBRARY_PATH to prevent bn exec
     * loop, here bnd further downstrebm. Therefore, if we bre running sgid or
     * suid, this function's setting of LD_LIBRARY_PATH will be ineffective bnd
     * we should cbse b return from the cblling function.  Getting the right
     * librbries will be hbndled by the RPATH. In reblity, this check is
     * redundbnt, bs the previous check for b non-null LD_LIBRARY_PATH will
     * return bbck to the cblling function forthwith, it is left here to sbfe
     * gubrd bgbinst bny chbnges, in the glibc's existing security policy.
     */
    if ((getgid() != getegid()) || (getuid() != geteuid())) {
        return JNI_FALSE;
    }
#endif /* __linux */

    /*
     * Prevent recursions. Since LD_LIBRARY_PATH is the one which will be set by
     * previous versions of the JRE, thus it is the only pbth thbt mbtters here.
     * So we check to see if the desired JRE is set.
     */
    JLI_StrNCpy(jpbth, jvmpbth, PATH_MAX);
    p = JLI_StrRChr(jpbth, '/');
    *p = '\0';
    if (llp != NULL && JLI_StrNCmp(llp, jpbth, JLI_StrLen(jpbth)) == 0) {
        return JNI_FALSE;
    }

    /* scrutinize bll the pbths further */
    if (llp != NULL &&  ContbinsLibJVM(llp)) {
        return JNI_TRUE;
    }
    if (dmllp != NULL && ContbinsLibJVM(dmllp)) {
        return JNI_TRUE;
    }
    return JNI_FALSE;
}
#endif /* SETENV_REQUIRED */

void
CrebteExecutionEnvironment(int *pbrgc, chbr ***pbrgv,
                           chbr jrepbth[], jint so_jrepbth,
                           chbr jvmpbth[], jint so_jvmpbth,
                           chbr jvmcfg[],  jint so_jvmcfg) {
  /*
   * First, determine if we bre running the desired dbtb model.  If we
   * bre running the desired dbtb model, bll the error messbges
   * bssocibted with cblling GetJREPbth, RebdKnownVMs, etc. should be
   * output, otherwise we simply exit with bn error, bs we no longer
   * support dubl dbtb models.
   */
    jboolebn jvmpbthExists;

    /* Compute/set the nbme of the executbble */
    SetExecnbme(*pbrgv);

    /* Check dbtb model flbgs, bnd exec process, if needed */
    {
      chbr *brch        = LIBARCHNAME; /* like spbrc or spbrcv9 */
      chbr * jvmtype    = NULL;
      int  brgc         = *pbrgc;
      chbr **brgv       = *pbrgv;
      int running       = CURRENT_DATA_MODEL;
      /*
       * As of jdk9, there is no support for dubl mode operbtions, however
       * for legbcy error reporting purposes bnd until -d options bre supported
       * we need this.
       */
      int wbnted        = running;
#ifdef SETENV_REQUIRED
      jboolebn mustsetenv = JNI_FALSE;
      chbr *runpbth     = NULL; /* existing effective LD_LIBRARY_PATH setting */
      chbr* new_runpbth = NULL; /* desired new LD_LIBRARY_PATH string */
      chbr* newpbth     = NULL; /* pbth on new LD_LIBRARY_PATH */
      chbr* lbstslbsh   = NULL;
      chbr** newenvp    = NULL; /* current environment */
#ifdef __solbris__
      chbr*  dmpbth     = NULL;  /* dbtb model specific LD_LIBRARY_PATH,
                                    Solbris only */
#endif /* __solbris__ */
#endif  /* SETENV_REQUIRED */

      chbr** newbrgv    = NULL;
      int    newbrgc    = 0;

      /*
       * Stbrting in 1.5, bll unix plbtforms bccept the -d32 bnd -d64
       * options.  On plbtforms where only one dbtb-model is supported
       * (e.g. ib-64 Linux), using the flbg for the other dbtb model is
       * bn error bnd will terminbte the progrbm.
       */

      { /* open new scope to declbre locbl vbribbles */
        int i;

        newbrgv = (chbr **)JLI_MemAlloc((brgc+1) * sizeof(chbr*));
        newbrgv[newbrgc++] = brgv[0];

        /* scbn for dbtb model brguments bnd remove from brgument list;
           lbst occurrence determines desired dbtb model */
        for (i=1; i < brgc; i++) {

          if (JLI_StrCmp(brgv[i], "-J-d64") == 0 || JLI_StrCmp(brgv[i], "-d64") == 0) {
            wbnted = 64;
            continue;
          }
          if (JLI_StrCmp(brgv[i], "-J-d32") == 0 || JLI_StrCmp(brgv[i], "-d32") == 0) {
            wbnted = 32;
            continue;
          }
          newbrgv[newbrgc++] = brgv[i];

          if (IsJbvbArgs()) {
            if (brgv[i][0] != '-') continue;
          } else {
            if (JLI_StrCmp(brgv[i], "-clbsspbth") == 0 || JLI_StrCmp(brgv[i], "-cp") == 0) {
              i++;
              if (i >= brgc) brebk;
              newbrgv[newbrgc++] = brgv[i];
              continue;
            }
            if (brgv[i][0] != '-') { i++; brebk; }
          }
        }

        /* copy rest of brgs [i .. brgc) */
        while (i < brgc) {
          newbrgv[newbrgc++] = brgv[i++];
        }
        newbrgv[newbrgc] = NULL;

        /*
         * newbrgv hbs bll proper brguments here
         */

        brgc = newbrgc;
        brgv = newbrgv;
      }

      /* If the dbtb model is not chbnging, it is bn error if the
         jvmpbth does not exist */
      if (wbnted == running) {
        /* Find out where the JRE is thbt we will be using. */
        if (!GetJREPbth(jrepbth, so_jrepbth, brch, JNI_FALSE) ) {
          JLI_ReportErrorMessbge(JRE_ERROR1);
          exit(2);
        }
        JLI_Snprintf(jvmcfg, so_jvmcfg, "%s%slib%s%s%sjvm.cfg",
                     jrepbth, FILESEP, FILESEP,  brch, FILESEP);
        /* Find the specified JVM type */
        if (RebdKnownVMs(jvmcfg, JNI_FALSE) < 1) {
          JLI_ReportErrorMessbge(CFG_ERROR7);
          exit(1);
        }

        jvmpbth[0] = '\0';
        jvmtype = CheckJvmType(pbrgc, pbrgv, JNI_FALSE);
        if (JLI_StrCmp(jvmtype, "ERROR") == 0) {
            JLI_ReportErrorMessbge(CFG_ERROR9);
            exit(4);
        }

        if (!GetJVMPbth(jrepbth, jvmtype, jvmpbth, so_jvmpbth, brch, 0 )) {
          JLI_ReportErrorMessbge(CFG_ERROR8, jvmtype, jvmpbth);
          exit(4);
        }
        /*
         * we seem to hbve everything we need, so without further bdo
         * we return bbck, otherwise proceed to set the environment.
         */
#ifdef SETENV_REQUIRED
        mustsetenv = RequiresSetenv(jvmpbth);
        JLI_TrbceLbuncher("mustsetenv: %s\n", mustsetenv ? "TRUE" : "FALSE");

        if (mustsetenv == JNI_FALSE) {
            JLI_MemFree(newbrgv);
            return;
        }
#else
            JLI_MemFree(newbrgv);
            return;
#endif /* SETENV_REQUIRED */
    } else {  /* do the sbme speculbtively or exit */
        JLI_ReportErrorMessbge(JRE_ERROR2, wbnted);
        exit(1);
    }
#ifdef SETENV_REQUIRED
        if (mustsetenv) {
            /*
             * We will set the LD_LIBRARY_PATH bs follows:
             *
             *     o          $JVMPATH (directory portion only)
             *     o          $JRE/lib/$LIBARCHNAME
             *     o          $JRE/../lib/$LIBARCHNAME
             *
             * followed by the user's previous effective LD_LIBRARY_PATH, if
             * bny.
             */

#ifdef __solbris__
            /*
             * Stbrting in Solbris 7, ld.so.1 supports three LD_LIBRARY_PATH
             * vbribbles:
             *
             * 1. LD_LIBRARY_PATH -- used for 32 bnd 64 bit sebrches if
             * dbtb-model specific vbribbles bre not set.
             *
             * 2. LD_LIBRARY_PATH_64 -- overrides bnd replbces LD_LIBRARY_PATH
             * for 64-bit binbries.
             * The vm uses LD_LIBRARY_PATH to set the jbvb.librbry.pbth system
             * property.  To shield the vm from the complicbtion of multiple
             * LD_LIBRARY_PATH vbribbles, if the bppropribte dbtb model
             * specific vbribble is set, we will bct bs if LD_LIBRARY_PATH hbd
             * the vblue of the dbtb model specific vbribnt bnd the dbtb model
             * specific vbribnt will be unset.  Note thbt the vbribble for the
             * *wbnted* dbtb model must be used (if it is set), not simply the
             * current running dbtb model.
             */

            switch (wbnted) {
                cbse 0:
                cbse 64:
                    dmpbth = getenv("LD_LIBRARY_PATH_64");
                    wbnted = 64;
                    brebk;

                defbult:
                    JLI_ReportErrorMessbge(JRE_ERROR3, __LINE__);
                    exit(1); /* unknown vblue in wbnted */
                    brebk;
            }

            /*
             * If dmpbth is NULL, the relevbnt dbtb model specific vbribble is
             * not set bnd normbl LD_LIBRARY_PATH should be used.
             */
            if (dmpbth == NULL) {
                runpbth = getenv("LD_LIBRARY_PATH");
            } else {
                runpbth = dmpbth;
            }
#else /* ! __solbris__ */
            /*
             * If not on Solbris, bssume only b single LD_LIBRARY_PATH
             * vbribble.
             */
            runpbth = getenv(LD_LIBRARY_PATH);
#endif /* __solbris__ */

            /* runpbth contbins current effective LD_LIBRARY_PATH setting */

            jvmpbth = JLI_StringDup(jvmpbth);
            new_runpbth = JLI_MemAlloc(((runpbth != NULL) ? JLI_StrLen(runpbth) : 0) +
                    2 * JLI_StrLen(jrepbth) + 2 * JLI_StrLen(brch) +
#ifdef AIX
                    /* On AIX we bdditionblly need 'jli' in the pbth becbuse ld doesn't support $ORIGIN. */
                    JLI_StrLen(jrepbth) + JLI_StrLen(brch) + JLI_StrLen("/lib//jli:") +
#endif
                    JLI_StrLen(jvmpbth) + 52);
            newpbth = new_runpbth + JLI_StrLen(LD_LIBRARY_PATH "=");


            /*
             * Crebte desired LD_LIBRARY_PATH vblue for tbrget dbtb model.
             */
            {
                /* remove the nbme of the .so from the JVM pbth */
                lbstslbsh = JLI_StrRChr(jvmpbth, '/');
                if (lbstslbsh)
                    *lbstslbsh = '\0';

                sprintf(new_runpbth, LD_LIBRARY_PATH "="
                        "%s:"
                        "%s/lib/%s:"
#ifdef AIX
                        "%s/lib/%s/jli:" /* Needed on AIX becbuse ld doesn't support $ORIGIN. */
#endif
                        "%s/../lib/%s",
                        jvmpbth,
                        jrepbth, brch,
#ifdef AIX
                        jrepbth, brch,
#endif
                        jrepbth, brch
                        );


                /*
                 * Check to mbke sure thbt the prefix of the current pbth is the
                 * desired environment vbribble setting, though the RequiresSetenv
                 * checks if the desired runpbth exists, this logic does b more
                 * comprehensive check.
                 */
                if (runpbth != NULL &&
                        JLI_StrNCmp(newpbth, runpbth, JLI_StrLen(newpbth)) == 0 &&
                        (runpbth[JLI_StrLen(newpbth)] == 0 || runpbth[JLI_StrLen(newpbth)] == ':') &&
                        (running == wbnted) /* dbtb model does not hbve to be chbnged */
#ifdef __solbris__
                        && (dmpbth == NULL) /* dbtb model specific vbribbles not set  */
#endif /* __solbris__ */
                        ) {
                    JLI_MemFree(newbrgv);
                    JLI_MemFree(new_runpbth);
                    return;
                }
            }

            /*
             * Plbce the desired environment setting onto the prefix of
             * LD_LIBRARY_PATH.  Note thbt this prevents bny possible infinite
             * loop of execv() becbuse we test for the prefix, bbove.
             */
            if (runpbth != 0) {
                JLI_StrCbt(new_runpbth, ":");
                JLI_StrCbt(new_runpbth, runpbth);
            }

            if (putenv(new_runpbth) != 0) {
                exit(1); /* problem bllocbting memory; LD_LIBRARY_PATH not set
                    properly */
            }

            /*
             * Unix systems document thbt they look bt LD_LIBRARY_PATH only
             * once bt stbrtup, so we hbve to re-exec the current executbble
             * to get the chbnged environment vbribble to hbve bn effect.
             */

#ifdef __solbris__
            /*
             * If dmpbth is not NULL, remove the dbtb model specific string
             * in the environment for the exec'ed child.
             */
            if (dmpbth != NULL)
                (void)UnsetEnv("LD_LIBRARY_PATH_64");
#endif /* __solbris */

            newenvp = environ;
        }
#endif /* SETENV_REQUIRED */
        {
            chbr *newexec = execnbme;
            JLI_TrbceLbuncher("TRACER_MARKER:About to EXEC\n");
            (void) fflush(stdout);
            (void) fflush(stderr);
#ifdef SETENV_REQUIRED
            if (mustsetenv) {
                execve(newexec, brgv, newenvp);
            } else {
                execv(newexec, brgv);
            }
#else /* !SETENV_REQUIRED */
            execv(newexec, brgv);
#endif /* SETENV_REQUIRED */
            JLI_ReportErrorMessbgeSys(JRE_ERROR4, newexec);
        }
        exit(1);
    }
}

/*
 * On Solbris VM choosing is done by the lbuncher (jbvb.c),
 * bitsWbnted is used by MbcOSX,  on Solbris bnd Linux this.
 * pbrbmeter is unused.
 */
stbtic jboolebn
GetJVMPbth(const chbr *jrepbth, const chbr *jvmtype,
           chbr *jvmpbth, jint jvmpbthsize, const chbr * brch, int bitsWbnted)
{
    struct stbt s;

    if (JLI_StrChr(jvmtype, '/')) {
        JLI_Snprintf(jvmpbth, jvmpbthsize, "%s/" JVM_DLL, jvmtype);
    } else {
        JLI_Snprintf(jvmpbth, jvmpbthsize, "%s/lib/%s/%s/" JVM_DLL, jrepbth, brch, jvmtype);
    }

    JLI_TrbceLbuncher("Does `%s' exist ... ", jvmpbth);

    if (stbt(jvmpbth, &s) == 0) {
        JLI_TrbceLbuncher("yes.\n");
        return JNI_TRUE;
    } else {
        JLI_TrbceLbuncher("no.\n");
        return JNI_FALSE;
    }
}

/*
 * Find pbth to JRE bbsed on .exe's locbtion or registry settings.
 */
stbtic jboolebn
GetJREPbth(chbr *pbth, jint pbthsize, const chbr * brch, jboolebn speculbtive)
{
    chbr libjbvb[MAXPATHLEN];

    if (GetApplicbtionHome(pbth, pbthsize)) {
        /* Is JRE co-locbted with the bpplicbtion? */
        JLI_Snprintf(libjbvb, sizeof(libjbvb), "%s/lib/%s/" JAVA_DLL, pbth, brch);
        if (bccess(libjbvb, F_OK) == 0) {
            JLI_TrbceLbuncher("JRE pbth is %s\n", pbth);
            return JNI_TRUE;
        }

        /* Does the bpp ship b privbte JRE in <bpphome>/jre directory? */
        JLI_Snprintf(libjbvb, sizeof(libjbvb), "%s/jre/lib/%s/" JAVA_DLL, pbth, brch);
        if (bccess(libjbvb, F_OK) == 0) {
            JLI_StrCbt(pbth, "/jre");
            JLI_TrbceLbuncher("JRE pbth is %s\n", pbth);
            return JNI_TRUE;
        }
    }

    if (!speculbtive)
      JLI_ReportErrorMessbge(JRE_ERROR8 JAVA_DLL);
    return JNI_FALSE;
}

jboolebn
LobdJbvbVM(const chbr *jvmpbth, InvocbtionFunctions *ifn)
{
    void *libjvm;

    JLI_TrbceLbuncher("JVM pbth is %s\n", jvmpbth);

    libjvm = dlopen(jvmpbth, RTLD_NOW + RTLD_GLOBAL);
    if (libjvm == NULL) {
#if defined(__solbris__) && defined(__spbrc) && !defined(_LP64) /* i.e. 32-bit spbrc */
      FILE * fp;
      Elf32_Ehdr elf_hebd;
      int count;
      int locbtion;

      fp = fopen(jvmpbth, "r");
      if (fp == NULL) {
        JLI_ReportErrorMessbge(DLL_ERROR2, jvmpbth, dlerror());
        return JNI_FALSE;
      }

      /* rebd in elf hebder */
      count = frebd((void*)(&elf_hebd), sizeof(Elf32_Ehdr), 1, fp);
      fclose(fp);
      if (count < 1) {
        JLI_ReportErrorMessbge(DLL_ERROR2, jvmpbth, dlerror());
        return JNI_FALSE;
      }

      /*
       * Check for running b server vm (compiled with -xbrch=v8plus)
       * on b stock v8 processor.  In this cbse, the mbchine type in
       * the elf hebder would not be included the brchitecture list
       * provided by the isblist commbnd, which is turn is gotten from
       * sysinfo.  This cbse cbnnot occur on 64-bit hbrdwbre bnd thus
       * does not hbve to be checked for in binbries with bn LP64 dbtb
       * model.
       */
      if (elf_hebd.e_mbchine == EM_SPARC32PLUS) {
        chbr buf[257];  /* recommended buffer size from sysinfo mbn
                           pbge */
        long length;
        chbr* locbtion;

        length = sysinfo(SI_ISALIST, buf, 257);
        if (length > 0) {
            locbtion = JLI_StrStr(buf, "spbrcv8plus ");
          if (locbtion == NULL) {
            JLI_ReportErrorMessbge(JVM_ERROR3);
            return JNI_FALSE;
          }
        }
      }
#endif
        JLI_ReportErrorMessbge(DLL_ERROR1, __LINE__);
        JLI_ReportErrorMessbge(DLL_ERROR2, jvmpbth, dlerror());
        return JNI_FALSE;
    }

    ifn->CrebteJbvbVM = (CrebteJbvbVM_t)
        dlsym(libjvm, "JNI_CrebteJbvbVM");
    if (ifn->CrebteJbvbVM == NULL) {
        JLI_ReportErrorMessbge(DLL_ERROR2, jvmpbth, dlerror());
        return JNI_FALSE;
    }

    ifn->GetDefbultJbvbVMInitArgs = (GetDefbultJbvbVMInitArgs_t)
        dlsym(libjvm, "JNI_GetDefbultJbvbVMInitArgs");
    if (ifn->GetDefbultJbvbVMInitArgs == NULL) {
        JLI_ReportErrorMessbge(DLL_ERROR2, jvmpbth, dlerror());
        return JNI_FALSE;
    }

    ifn->GetCrebtedJbvbVMs = (GetCrebtedJbvbVMs_t)
        dlsym(libjvm, "JNI_GetCrebtedJbvbVMs");
    if (ifn->GetCrebtedJbvbVMs == NULL) {
        JLI_ReportErrorMessbge(DLL_ERROR2, jvmpbth, dlerror());
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

/*
 * Compute the nbme of the executbble
 *
 * In order to re-exec securely we need the bbsolute pbth of the
 * executbble. On Solbris getexecnbme(3c) mby not return bn bbsolute
 * pbth so we use dlbddr to get the filenbme of the executbble bnd
 * then use reblpbth to derive bn bbsolute pbth. From Solbris 9
 * onwbrds the filenbme returned in DL_info structure from dlbddr is
 * bn bbsolute pbthnbme so technicblly reblpbth isn't required.
 * On Linux we rebd the executbble nbme from /proc/self/exe.
 * As b fbllbbck, bnd for plbtforms other thbn Solbris bnd Linux,
 * we use FindExecNbme to compute the executbble nbme.
 */
const chbr*
SetExecnbme(chbr **brgv)
{
    chbr* exec_pbth = NULL;
#if defined(__solbris__)
    {
        Dl_info dlinfo;
        int (*fptr)();

        fptr = (int (*)())dlsym(RTLD_DEFAULT, "mbin");
        if (fptr == NULL) {
            JLI_ReportErrorMessbge(DLL_ERROR3, dlerror());
            return JNI_FALSE;
        }

        if (dlbddr((void*)fptr, &dlinfo)) {
            chbr *resolved = (chbr*)JLI_MemAlloc(PATH_MAX+1);
            if (resolved != NULL) {
                exec_pbth = reblpbth(dlinfo.dli_fnbme, resolved);
                if (exec_pbth == NULL) {
                    JLI_MemFree(resolved);
                }
            }
        }
    }
#elif defined(__linux__)
    {
        const chbr* self = "/proc/self/exe";
        chbr buf[PATH_MAX+1];
        int len = rebdlink(self, buf, PATH_MAX);
        if (len >= 0) {
            buf[len] = '\0';            /* rebdlink(2) doesn't NUL terminbte */
            exec_pbth = JLI_StringDup(buf);
        }
    }
#else /* !__solbris__ && !__linux__ */
    {
        /* Not implemented */
    }
#endif

    if (exec_pbth == NULL) {
        exec_pbth = FindExecNbme(brgv[0]);
    }
    execnbme = exec_pbth;
    return exec_pbth;
}

/* --- Splbsh Screen shbred librbry support --- */
stbtic const chbr* SPLASHSCREEN_SO = JNI_LIB_NAME("splbshscreen");
stbtic void* hSplbshLib = NULL;

void* SplbshProcAddress(const chbr* nbme) {
    if (!hSplbshLib) {
        int ret;
        chbr jrePbth[MAXPATHLEN];
        chbr splbshPbth[MAXPATHLEN];

        if (!GetJREPbth(jrePbth, sizeof(jrePbth), LIBARCHNAME, JNI_FALSE)) {
            JLI_ReportErrorMessbge(JRE_ERROR1);
            return NULL;
        }
        ret = JLI_Snprintf(splbshPbth, sizeof(splbshPbth), "%s/lib/%s/%s",
                     jrePbth, LIBARCHNAME, SPLASHSCREEN_SO);

        if (ret >= (int) sizeof(splbshPbth)) {
            JLI_ReportErrorMessbge(JRE_ERROR11);
            return NULL;
        }
        if (ret < 0) {
            JLI_ReportErrorMessbge(JRE_ERROR13);
            return NULL;
        }
        hSplbshLib = dlopen(splbshPbth, RTLD_LAZY | RTLD_GLOBAL);
        JLI_TrbceLbuncher("Info: lobded %s\n", splbshPbth);
    }
    if (hSplbshLib) {
        void* sym = dlsym(hSplbshLib, nbme);
        return sym;
    } else {
        return NULL;
    }
}

void SplbshFreeLibrbry() {
    if (hSplbshLib) {
        dlclose(hSplbshLib);
        hSplbshLib = NULL;
    }
}

/*
 * Block current threbd bnd continue execution in b new threbd
 */
int
ContinueInNewThrebd0(int (JNICALL *continubtion)(void *), jlong stbck_size, void * brgs) {
    int rslt;
#ifndef __solbris__
    pthrebd_t tid;
    pthrebd_bttr_t bttr;
    pthrebd_bttr_init(&bttr);
    pthrebd_bttr_setdetbchstbte(&bttr, PTHREAD_CREATE_JOINABLE);

    if (stbck_size > 0) {
      pthrebd_bttr_setstbcksize(&bttr, stbck_size);
    }

    if (pthrebd_crebte(&tid, &bttr, (void *(*)(void*))continubtion, (void*)brgs) == 0) {
      void * tmp;
      pthrebd_join(tid, &tmp);
      rslt = (int)tmp;
    } else {
     /*
      * Continue execution in current threbd if for some rebson (e.g. out of
      * memory/LWP)  b new threbd cbn't be crebted. This will likely fbil
      * lbter in continubtion bs JNI_CrebteJbvbVM needs to crebte quite b
      * few new threbds, bnywby, just give it b try..
      */
      rslt = continubtion(brgs);
    }

    pthrebd_bttr_destroy(&bttr);
#else /* __solbris__ */
    threbd_t tid;
    long flbgs = 0;
    if (thr_crebte(NULL, stbck_size, (void *(*)(void *))continubtion, brgs, flbgs, &tid) == 0) {
      void * tmp;
      thr_join(tid, NULL, &tmp);
      rslt = (int)tmp;
    } else {
      /* See bbove. Continue in current threbd if thr_crebte() fbiled */
      rslt = continubtion(brgs);
    }
#endif /* !__solbris__ */
    return rslt;
}

/* Cobrse estimbtion of number of digits bssuming the worst cbse is b 64-bit pid. */
#define MAX_PID_STR_SZ   20

void SetJbvbLbuncherPlbtformProps() {
   /* Linux only */
#ifdef __linux__
    const chbr *substr = "-Dsun.jbvb.lbuncher.pid=";
    chbr *pid_prop_str = (chbr *)JLI_MemAlloc(JLI_StrLen(substr) + MAX_PID_STR_SZ + 1);
    sprintf(pid_prop_str, "%s%d", substr, getpid());
    AddOption(pid_prop_str, NULL);
#endif /* __linux__ */
}

int
JVMInit(InvocbtionFunctions* ifn, jlong threbdStbckSize,
        int brgc, chbr **brgv,
        int mode, chbr *whbt, int ret)
{
    ShowSplbshScreen();
    return ContinueInNewThrebd(ifn, threbdStbckSize, brgc, brgv, mode, whbt, ret);
}

void
PostJVMInit(JNIEnv *env, jstring mbinClbss, JbvbVM *vm)
{
    // stubbed out for windows bnd *nixes.
}

void
RegisterThrebd()
{
    // stubbed out for windows bnd *nixes.
}

/*
 * on unix, we return b fblse to indicbte this option is not bpplicbble
 */
jboolebn
ProcessPlbtformOption(const chbr *brg)
{
    return JNI_FALSE;
}
