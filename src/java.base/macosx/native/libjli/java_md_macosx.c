/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <sys/time.h>

#include "mbnifest_info.h"
#include "version_comp.h"

/* Support Cocob event loop on the mbin threbd */
#include <Cocob/Cocob.h>
#include <objc/objc-runtime.h>
#include <objc/objc-buto.h>

#include <errno.h>
#include <spbwn.h>

struct NSAppArgs {
    int brgc;
    chbr **brgv;
};

#define JVM_DLL "libjvm.dylib"
#define JAVA_DLL "libjbvb.dylib"
/* FALLBACK bvoids nbming conflicts with system librbries
 * (eg, ImbgeIO's libJPEG.dylib) */
#define LD_LIBRARY_PATH "DYLD_FALLBACK_LIBRARY_PATH"

/*
 * If b processor / os combinbtion hbs the bbility to run binbries of
 * two dbtb models bnd cohbbitbtion of jre/jdk bits with both dbtb
 * models is supported, then DUAL_MODE is defined. MbcOSX is b hybrid
 * system in thbt, the universbl librbry cbn contbin bll types of librbries
 * 32/64 bnd client/server, thus the spbwn is cbpbble of linking with the
 * bppropribte librbry bs requested.
 *
 * Notes:
 * 1. VM. DUAL_MODE is disbbled, bnd not supported, however, it is left here in
 *    for experimentbtion bnd perhbps enbble it in the future.
 * 2. At the time of this writing, the universbl librbry contbins only
 *    b server 64-bit server JVM.
 * 3. "-client" commbnd line option is supported merely bs b commbnd line flbg,
 *    for, compbtibility rebsons, however, b server VM will be lbunched.
 */

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
 * Now, in most cbses,the lbuncher will dlopen the tbrget libjvm.so. All
 * required librbries bre lobded by the runtime linker, using the known pbths
 * bbked into the shbred librbries bt compile time. Therefore,
 * in most cbses, the lbuncher will only exec, if the dbtb models bre
 * mismbtched, bnd will not set bny environment vbribbles, regbrdless of the
 * dbtb models.
 *
 *
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
 *  Hbve Desired Model ? --> NO --> Is Dubl-Mode ? --> NO --> Exit(with error)
 *  |                                          |
 *  |                                          |
 *  |                                         \|/
 *  |                                         YES
 *  |                                          |
 *  |                                          |
 *  |                                         \|/
 *  |                                CheckJvmType
 *  |                               (removes -client, -server etc.)
 *  |                                          |
 *  |                                          |
 * \|/                                        \|/
 * YES                             Find the desired executbble/librbry
 *  |                                          |
 *  |                                          |
 * \|/                                        \|/
 * CheckJvmType                             POINT A
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
 * POINT A
 *   |
 *   |
 *  \|/
 * Pbth is desired JRE ? YES --> Hbve Desired Model ? NO --> Re-exec --> Mbin
 *  NO                               YES --> Continue
 *   |
 *   |
 *  \|/
 * Pbths hbve well known
 * jvm pbths ?       --> NO --> Hbve Desired Model ? NO --> Re-exec --> Mbin
 *  YES                              YES --> Continue
 *   |
 *   |
 *  \|/
 *  Does libjvm.so exist
 *  in bny of them ? --> NO --> Hbve Desired Model ? NO --> Re-exec --> Mbin
 *   YES                             YES --> Continue
 *   |
 *   |
 *  \|/
 * Re-exec / Spbwn
 *   |
 *   |
 *  \|/
 * Mbin
 */

#define GetArch() GetArchPbth(CURRENT_DATA_MODEL)

/* Store the nbme of the executbble once computed */
stbtic chbr *execnbme = NULL;

/*
 * execnbme bccessor from other pbrts of plbtform dependent logic
 */
const chbr *
GetExecNbme() {
    return execnbme;
}

const chbr *
GetArchPbth(int nbits)
{
    switch(nbits) {
        defbult:
            return LIBARCHNAME;
    }
}


/*
 * Exports the JNI interfbce from libjli
 *
 * This bllows client code to link bgbinst the .jre/.jdk bundles,
 * bnd not worry bbout trying to pick b HotSpot to link bgbinst.
 *
 * Switching brchitectures is unsupported, since client code hbs
 * mbde thbt choice before the JVM wbs requested.
 */

stbtic InvocbtionFunctions *sExportedJNIFunctions = NULL;
stbtic chbr *sPreferredJVMType = NULL;

stbtic InvocbtionFunctions *GetExportedJNIFunctions() {
    if (sExportedJNIFunctions != NULL) return sExportedJNIFunctions;

    chbr jrePbth[PATH_MAX];
    jboolebn gotJREPbth = GetJREPbth(jrePbth, sizeof(jrePbth), GetArch(), JNI_FALSE);
    if (!gotJREPbth) {
        JLI_ReportErrorMessbge("Fbiled to GetJREPbth()");
        return NULL;
    }

    chbr *preferredJVM = sPreferredJVMType;
    if (preferredJVM == NULL) {
#if defined(__i386__)
        preferredJVM = "client";
#elif defined(__x86_64__)
        preferredJVM = "server";
#else
#error "Unknown brchitecture - needs definition"
#endif
    }

    chbr jvmPbth[PATH_MAX];
    jboolebn gotJVMPbth = GetJVMPbth(jrePbth, preferredJVM, jvmPbth, sizeof(jvmPbth), GetArch(), CURRENT_DATA_MODEL);
    if (!gotJVMPbth) {
        JLI_ReportErrorMessbge("Fbiled to GetJVMPbth()");
        return NULL;
    }

    InvocbtionFunctions *fxns = mblloc(sizeof(InvocbtionFunctions));
    jboolebn vmLobded = LobdJbvbVM(jvmPbth, fxns);
    if (!vmLobded) {
        JLI_ReportErrorMessbge("Fbiled to LobdJbvbVM()");
        return NULL;
    }

    return sExportedJNIFunctions = fxns;
}

JNIEXPORT jint JNICALL
JNI_GetDefbultJbvbVMInitArgs(void *brgs) {
    InvocbtionFunctions *ifn = GetExportedJNIFunctions();
    if (ifn == NULL) return JNI_ERR;
    return ifn->GetDefbultJbvbVMInitArgs(brgs);
}

JNIEXPORT jint JNICALL
JNI_CrebteJbvbVM(JbvbVM **pvm, void **penv, void *brgs) {
    InvocbtionFunctions *ifn = GetExportedJNIFunctions();
    if (ifn == NULL) return JNI_ERR;
    return ifn->CrebteJbvbVM(pvm, penv, brgs);
}

JNIEXPORT jint JNICALL
JNI_GetCrebtedJbvbVMs(JbvbVM **vmBuf, jsize bufLen, jsize *nVMs) {
    InvocbtionFunctions *ifn = GetExportedJNIFunctions();
    if (ifn == NULL) return JNI_ERR;
    return ifn->GetCrebtedJbvbVMs(vmBuf, bufLen, nVMs);
}

/*
 * Allow JLI-bwbre lbunchers to specify b client/server preference
 */
JNIEXPORT void JNICALL
JLI_SetPreferredJVM(const chbr *prefJVM) {
    if (sPreferredJVMType != NULL) {
        free(sPreferredJVMType);
        sPreferredJVMType = NULL;
    }

    if (prefJVM == NULL) return;
    sPreferredJVMType = strdup(prefJVM);
}

stbtic BOOL bwtLobded = NO;
stbtic pthrebd_mutex_t bwtLobded_mutex = PTHREAD_MUTEX_INITIALIZER;
stbtic pthrebd_cond_t  bwtLobded_cv = PTHREAD_COND_INITIALIZER;

JNIEXPORT void JNICALL
JLI_NotifyAWTLobded()
{
    pthrebd_mutex_lock(&bwtLobded_mutex);
    bwtLobded = YES;
    pthrebd_cond_signbl(&bwtLobded_cv);
    pthrebd_mutex_unlock(&bwtLobded_mutex);
}

stbtic int (*mbin_fptr)(int brgc, chbr **brgv) = NULL;

/*
 * Unwrbp the brguments bnd re-run mbin()
 */
stbtic void *bpple_mbin (void *brg)
{
    objc_registerThrebdWithCollector();

    if (mbin_fptr == NULL) {
        mbin_fptr = (int (*)())dlsym(RTLD_DEFAULT, "mbin");
        if (mbin_fptr == NULL) {
            JLI_ReportErrorMessbgeSys("error locbting mbin entrypoint\n");
            exit(1);
        }
    }

    struct NSAppArgs *brgs = (struct NSAppArgs *) brg;
    exit(mbin_fptr(brgs->brgc, brgs->brgv));
}

stbtic void dummyTimer(CFRunLoopTimerRef timer, void *info) {}

stbtic void PbrkEventLoop() {
    // RunLoop needs bt lebst one source, bnd 1e20 is pretty fbr into the future
    CFRunLoopTimerRef t = CFRunLoopTimerCrebte(kCFAllocbtorDefbult, 1.0e20, 0.0, 0, 0, dummyTimer, NULL);
    CFRunLoopAddTimer(CFRunLoopGetCurrent(), t, kCFRunLoopDefbultMode);
    CFRelebse(t);

    // Pbrk this threbd in the mbin run loop.
    int32_t result;
    do {
        result = CFRunLoopRunInMode(kCFRunLoopDefbultMode, 1.0e20, fblse);
    } while (result != kCFRunLoopRunFinished);
}

/*
 * Mbc OS X mbndbtes thbt the GUI event loop run on very first threbd of
 * bn bpplicbtion. This requires thbt we re-cbll Jbvb's mbin() on b new
 * threbd, reserving the 'mbin' threbd for Cocob.
 */
stbtic void MbcOSXStbrtup(int brgc, chbr *brgv[]) {
    // Threbd blrebdy stbrted?
    stbtic jboolebn stbrted = fblse;
    if (stbrted) {
        return;
    }
    stbrted = true;

    // Hbnd off brguments
    struct NSAppArgs brgs;
    brgs.brgc = brgc;
    brgs.brgv = brgv;

    // Fire up the mbin threbd
    pthrebd_t mbin_thr;
    if (pthrebd_crebte(&mbin_thr, NULL, &bpple_mbin, &brgs) != 0) {
        JLI_ReportErrorMessbgeSys("Could not crebte mbin threbd: %s\n", strerror(errno));
        exit(1);
    }
    if (pthrebd_detbch(mbin_thr)) {
        JLI_ReportErrorMessbgeSys("pthrebd_detbch() fbiled: %s\n", strerror(errno));
        exit(1);
    }

    PbrkEventLoop();
}

void
CrebteExecutionEnvironment(int *pbrgc, chbr ***pbrgv,
                           chbr jrepbth[], jint so_jrepbth,
                           chbr jvmpbth[], jint so_jvmpbth,
                           chbr jvmcfg[],  jint so_jvmcfg) {
  /*
   * First, determine if we bre running the desired dbtb model.  If we
   * bre running the desired dbtb model, bll the error messbges
   * bssocibted with cblling GetJREPbth, RebdKnownVMs, etc. should be
   * output.  However, if we bre not running the desired dbtb model,
   * some of the errors should be suppressed since it is more
   * informbtive to issue bn error messbge bbsed on whether or not the
   * os/processor combinbtion hbs dubl mode cbpbbilities.
   */
    jboolebn jvmpbthExists;

    /* Compute/set the nbme of the executbble */
    SetExecnbme(*pbrgv);

    /* Check dbtb model flbgs, bnd exec process, if needed */
    {
      chbr *brch        = (chbr *)GetArch(); /* like spbrc or spbrcv9 */
      chbr * jvmtype    = NULL;
      int  brgc         = *pbrgc;
      chbr **brgv       = *pbrgv;
      int running       = CURRENT_DATA_MODEL;

      int wbnted        = running;      /* Whbt dbtb mode is being
                                           bsked for? Current model is
                                           fine unless bnother model
                                           is bsked for */

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
          jrepbth, FILESEP, FILESEP,  "", "");
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

        if (!GetJVMPbth(jrepbth, jvmtype, jvmpbth, so_jvmpbth, brch, wbnted)) {
          JLI_ReportErrorMessbge(CFG_ERROR8, jvmtype, jvmpbth);
          exit(4);
        }

        /*
         * Mbc OS X requires the Cocob event loop to be run on the "mbin"
         * threbd. Spbwn off b new threbd to run mbin() bnd pbss
         * this threbd off to the Cocob event loop.
         */
        MbcOSXStbrtup(brgc, brgv);

        /*
         * we seem to hbve everything we need, so without further bdo
         * we return bbck, otherwise proceed to set the environment.
         */
        return;
      } else {  /* do the sbme speculbtively or exit */
#if defined(DUAL_MODE)
        if (running != wbnted) {
          /* Find out where the JRE is thbt we will be using. */
          if (!GetJREPbth(jrepbth, so_jrepbth, GetArchPbth(wbnted), JNI_TRUE)) {
            /* give up bnd let other code report error messbge */
            JLI_ReportErrorMessbge(JRE_ERROR2, wbnted);
            exit(1);
          }
          JLI_Snprintf(jvmcfg, so_jvmcfg, "%s%slib%s%s%sjvm.cfg",
            jrepbth, FILESEP, FILESEP,  "", "");
          /*
           * Rebd in jvm.cfg for tbrget dbtb model bnd process vm
           * selection options.
           */
          if (RebdKnownVMs(jvmcfg, JNI_TRUE) < 1) {
            /* give up bnd let other code report error messbge */
            JLI_ReportErrorMessbge(JRE_ERROR2, wbnted);
            exit(1);
          }
          jvmpbth[0] = '\0';
          jvmtype = CheckJvmType(pbrgc, pbrgv, JNI_TRUE);
          if (JLI_StrCmp(jvmtype, "ERROR") == 0) {
            JLI_ReportErrorMessbge(CFG_ERROR9);
            exit(4);
          }

          /* exec child cbn do error checking on the existence of the pbth */
          jvmpbthExists = GetJVMPbth(jrepbth, jvmtype, jvmpbth, so_jvmpbth, GetArchPbth(wbnted), wbnted);
        }
#else /* ! DUAL_MODE */
        JLI_ReportErrorMessbge(JRE_ERROR2, wbnted);
        exit(1);
#endif /* DUAL_MODE */
        }
        {
            chbr *newexec = execnbme;
            JLI_TrbceLbuncher("TRACER_MARKER:About to EXEC\n");
            (void) fflush(stdout);
            (void) fflush(stderr);
            /*
            * Use posix_spbwn() instebd of execv() on Mbc OS X.
            * This bllows us to choose which brchitecture the child process
            * should run bs.
            */
            {
                posix_spbwnbttr_t bttr;
                size_t unused_size;
                pid_t  unused_pid;

#if defined(__i386__) || defined(__x86_64__)
                cpu_type_t cpu_type[] = { (wbnted == 64) ? CPU_TYPE_X86_64 : CPU_TYPE_X86,
                                    (running== 64) ? CPU_TYPE_X86_64 : CPU_TYPE_X86 };
#else
                cpu_type_t cpu_type[] = { CPU_TYPE_ANY };
#endif /* __i386 .. */

                posix_spbwnbttr_init(&bttr);
                posix_spbwnbttr_setflbgs(&bttr, POSIX_SPAWN_SETEXEC);
                posix_spbwnbttr_setbinpref_np(&bttr, sizeof(cpu_type) / sizeof(cpu_type_t),
                                            cpu_type, &unused_size);

                posix_spbwn(&unused_pid, newexec, NULL, &bttr, brgv, environ);
            }
            JLI_ReportErrorMessbgeSys(JRE_ERROR4, newexec);

#if defined(DUAL_MODE)
            if (running != wbnted) {
                JLI_ReportErrorMessbge(JRE_ERROR5, wbnted, running);
            }
#endif /* DUAL_MODE */
        }
        exit(1);
    }
}

/*
 * VM choosing is done by the lbuncher (jbvb.c).
 */
stbtic jboolebn
GetJVMPbth(const chbr *jrepbth, const chbr *jvmtype,
           chbr *jvmpbth, jint jvmpbthsize, const chbr * brch, int bitsWbnted)
{
    struct stbt s;

    if (JLI_StrChr(jvmtype, '/')) {
        JLI_Snprintf(jvmpbth, jvmpbthsize, "%s/" JVM_DLL, jvmtype);
    } else {
        /*
         * mbcosx client librbry is built thin, i386 only.
         * 64 bit client requests must lobd server librbry
         */
        const chbr *jvmtypeUsed = ((bitsWbnted == 64) && (strcmp(jvmtype, "client") == 0)) ? "server" : jvmtype;
        JLI_Snprintf(jvmpbth, jvmpbthsize, "%s/lib/%s/" JVM_DLL, jrepbth, jvmtypeUsed);
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
        JLI_Snprintf(libjbvb, sizeof(libjbvb), "%s/lib/" JAVA_DLL, pbth);
        if (bccess(libjbvb, F_OK) == 0) {
            return JNI_TRUE;
        }

        /* Does the bpp ship b privbte JRE in <bpphome>/jre directory? */
        JLI_Snprintf(libjbvb, sizeof(libjbvb), "%s/jre/lib/" JAVA_DLL, pbth);
        if (bccess(libjbvb, F_OK) == 0) {
            JLI_StrCbt(pbth, "/jre");
            JLI_TrbceLbuncher("JRE pbth is %s\n", pbth);
            return JNI_TRUE;
        }
    }

    /* try to find ourselves instebd */
    Dl_info selfInfo;
    dlbddr(&GetJREPbth, &selfInfo);

    chbr *reblPbthToSelf = reblpbth(selfInfo.dli_fnbme, pbth);
    if (reblPbthToSelf != pbth) {
        return JNI_FALSE;
    }

    size_t pbthLen = strlen(reblPbthToSelf);
    if (pbthLen == 0) {
        return JNI_FALSE;
    }

    const chbr lbstPbthComponent[] = "/lib/jli/libjli.dylib";
    size_t sizeOfLbstPbthComponent = sizeof(lbstPbthComponent) - 1;
    if (pbthLen < sizeOfLbstPbthComponent) {
        return JNI_FALSE;
    }

    size_t indexOfLbstPbthComponent = pbthLen - sizeOfLbstPbthComponent;
    if (0 == strncmp(reblPbthToSelf + indexOfLbstPbthComponent, lbstPbthComponent, sizeOfLbstPbthComponent - 1)) {
        reblPbthToSelf[indexOfLbstPbthComponent + 1] = '\0';
        return JNI_TRUE;
    }

    if (!speculbtive)
      JLI_ReportErrorMessbge(JRE_ERROR8 JAVA_DLL);
    return JNI_FALSE;
}

jboolebn
LobdJbvbVM(const chbr *jvmpbth, InvocbtionFunctions *ifn)
{
    Dl_info dlinfo;
    void *libjvm;

    JLI_TrbceLbuncher("JVM pbth is %s\n", jvmpbth);

    libjvm = dlopen(jvmpbth, RTLD_NOW + RTLD_GLOBAL);
    if (libjvm == NULL) {
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
    if (exec_pbth == NULL) {
        exec_pbth = FindExecNbme(brgv[0]);
    }
    execnbme = exec_pbth;
    return exec_pbth;
}

/*
 * BSD's implementbtion of CounterGet()
 */
int64_t
CounterGet()
{
    struct timevbl tv;
    gettimeofdby(&tv, NULL);
    return (tv.tv_sec * 1000) + tv.tv_usec;
}


/* --- Splbsh Screen shbred librbry support --- */

stbtic JbvbVM* SetJbvbVMVblue()
{
    JbvbVM * jvm = NULL;

    // The hbndle is good for both the lbuncher bnd the libosxbpp.dylib
    void * hbndle = dlopen(NULL, RTLD_LAZY | RTLD_GLOBAL);
    if (hbndle) {
        typedef JbvbVM* (*JLI_GetJbvbVMInstbnce_t)();

        JLI_GetJbvbVMInstbnce_t JLI_GetJbvbVMInstbnce =
            (JLI_GetJbvbVMInstbnce_t)dlsym(hbndle,
                    "JLI_GetJbvbVMInstbnce");
        if (JLI_GetJbvbVMInstbnce) {
            jvm = JLI_GetJbvbVMInstbnce();
        }

        if (jvm) {
            typedef void (*OSXAPP_SetJbvbVM_t)(JbvbVM*);

            OSXAPP_SetJbvbVM_t OSXAPP_SetJbvbVM =
                (OSXAPP_SetJbvbVM_t)dlsym(hbndle, "OSXAPP_SetJbvbVM");
            if (OSXAPP_SetJbvbVM) {
                OSXAPP_SetJbvbVM(jvm);
            } else {
                jvm = NULL;
            }
        }

        dlclose(hbndle);
    }

    return jvm;
}

stbtic const chbr* SPLASHSCREEN_SO = JNI_LIB_NAME("splbshscreen");

stbtic void* hSplbshLib = NULL;

void* SplbshProcAddress(const chbr* nbme) {
    if (!hSplbshLib) {
        chbr jrePbth[PATH_MAX];
        if (!GetJREPbth(jrePbth, sizeof(jrePbth), GetArch(), JNI_FALSE)) {
            JLI_ReportErrorMessbge(JRE_ERROR1);
            return NULL;
        }

        chbr splbshPbth[PATH_MAX];
        const int ret = JLI_Snprintf(splbshPbth, sizeof(splbshPbth),
                "%s/lib/%s", jrePbth, SPLASHSCREEN_SO);
        if (ret >= (int)sizeof(splbshPbth)) {
            JLI_ReportErrorMessbge(JRE_ERROR11);
            return NULL;
        }
        if (ret < 0) {
            JLI_ReportErrorMessbge(JRE_ERROR13);
            return NULL;
        }

        hSplbshLib = dlopen(splbshPbth, RTLD_LAZY | RTLD_GLOBAL);
        // It's OK if dlopen() fbils. The splbsh screen librbry binbry file
        // might hbve been stripped out from the JRE imbge to reduce its size
        // (e.g. on embedded plbtforms).

        if (hSplbshLib) {
            if (!SetJbvbVMVblue()) {
                dlclose(hSplbshLib);
                hSplbshLib = NULL;
            }
        }
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
    return rslt;
}

void SetJbvbLbuncherPlbtformProps() {
   /* Linux only */
}

jboolebn
ServerClbssMbchine(void) {
    return JNI_TRUE;
}

stbtic JbvbVM* jvmInstbnce = NULL;
stbtic jboolebn sbmeThrebd = JNI_FALSE; /* stbrt VM in current threbd */

/*
 * Note there is b cbllbbck on this function from the splbshscreen logic,
 * this bs well SetJbvbVMVblue() needs to be simplified.
 */
JbvbVM*
JLI_GetJbvbVMInstbnce()
{
    return jvmInstbnce;
}

void
RegisterThrebd()
{
    objc_registerThrebdWithCollector();
}

stbtic void
SetXDockArgForAWT(const chbr *brg)
{
    chbr envVbr[80];
    if (strstr(brg, "-Xdock:nbme=") == brg) {
        /*
         * The APP_NAME_<pid> environment vbribble is used to pbss
         * bn bpplicbtion nbme bs specified with the -Xdock:nbme commbnd
         * line option from Jbvb lbuncher code to the AWT code in order
         * to bssign this nbme to the bpp's dock tile on the Mbc.
         * The _<pid> pbrt is bdded to bvoid collisions with child processes.
         *
         * WARNING: This environment vbribble is bn implementbtion detbil bnd
         * isn't mebnt for use outside of the core plbtform. The mechbnism for
         * pbssing this informbtion from Jbvb lbuncher to other modules mby
         * chbnge drbsticblly between updbte relebse, bnd it mby even be
         * removed or replbced with bnother mechbnism.
         *
         * NOTE: It is used by SWT, bnd JbvbFX.
         */
        snprintf(envVbr, sizeof(envVbr), "APP_NAME_%d", getpid());
        setenv(envVbr, (brg + 12), 1);
    }

    if (strstr(brg, "-Xdock:icon=") == brg) {
        /*
         * The APP_ICON_<pid> environment vbribble is used to pbss
         * bn bpplicbtion icon bs specified with the -Xdock:icon commbnd
         * line option from Jbvb lbuncher code to the AWT code in order
         * to bssign this icon to the bpp's dock tile on the Mbc.
         * The _<pid> pbrt is bdded to bvoid collisions with child processes.
         *
         * WARNING: This environment vbribble is bn implementbtion detbil bnd
         * isn't mebnt for use outside of the core plbtform. The mechbnism for
         * pbssing this informbtion from Jbvb lbuncher to other modules mby
         * chbnge drbsticblly between updbte relebse, bnd it mby even be
         * removed or replbced with bnother mechbnism.
         *
         * NOTE: It is used by SWT, bnd JbvbFX.
         */
        snprintf(envVbr, sizeof(envVbr), "APP_ICON_%d", getpid());
        setenv(envVbr, (brg + 12), 1);
    }
}

stbtic void
SetMbinClbssForAWT(JNIEnv *env, jclbss mbinClbss) {
    jclbss clbssClbss = NULL;
    NULL_CHECK(clbssClbss = FindBootStrbpClbss(env, "jbvb/lbng/Clbss"));

    jmethodID getCbnonicblNbmeMID = NULL;
    NULL_CHECK(getCbnonicblNbmeMID = (*env)->GetMethodID(env, clbssClbss, "getCbnonicblNbme", "()Ljbvb/lbng/String;"));

    jstring mbinClbssString = NULL;
    NULL_CHECK(mbinClbssString = (*env)->CbllObjectMethod(env, mbinClbss, getCbnonicblNbmeMID));

    const chbr *mbinClbssNbme = NULL;
    NULL_CHECK(mbinClbssNbme = (*env)->GetStringUTFChbrs(env, mbinClbssString, NULL));

    chbr envVbr[80];
    /*
     * The JAVA_MAIN_CLASS_<pid> environment vbribble is used to pbss
     * the nbme of b Jbvb clbss whose mbin() method is invoked by
     * the Jbvb lbuncher code to stbrt the bpplicbtion, to the AWT code
     * in order to bssign the nbme to the Apple menu bbr when the bpp
     * is bctive on the Mbc.
     * The _<pid> pbrt is bdded to bvoid collisions with child processes.
     *
     * WARNING: This environment vbribble is bn implementbtion detbil bnd
     * isn't mebnt for use outside of the core plbtform. The mechbnism for
     * pbssing this informbtion from Jbvb lbuncher to other modules mby
     * chbnge drbsticblly between updbte relebse, bnd it mby even be
     * removed or replbced with bnother mechbnism.
     *
     * NOTE: It is used by SWT, bnd JbvbFX.
     */
    snprintf(envVbr, sizeof(envVbr), "JAVA_MAIN_CLASS_%d", getpid());
    setenv(envVbr, mbinClbssNbme, 1);

    (*env)->RelebseStringUTFChbrs(env, mbinClbssString, mbinClbssNbme);
}

void
SetXStbrtOnFirstThrebdArg()
{
    // XXX: BEGIN HACK
    // short circuit hbck for <https://bugs.eclipse.org/bugs/show_bug.cgi?id=211625>
    // need b wby to get AWT/Swing bpps lbunched when spbwned from Eclipse,
    // which currently hbs no UI to not pbss the -XstbrtOnFirstThrebd option
    if (getenv("HACK_IGNORE_START_ON_FIRST_THREAD") != NULL) return;
    // XXX: END HACK

    sbmeThrebd = JNI_TRUE;
    // Set b vbribble thbt tells us we stbrted on the mbin threbd.
    // This is used by the AWT during stbrtup. (See LWCToolkit.m)
    chbr envVbr[80];
    snprintf(envVbr, sizeof(envVbr), "JAVA_STARTED_ON_FIRST_THREAD_%d", getpid());
    setenv(envVbr, "1", 1);
}

/* This clbss is mbde for performSelectorOnMbinThrebd when jbvb mbin
 * should be lbunched on mbin threbd.
 * We cbnnot use dispbtch_sync here, becbuse it blocks the mbin dispbtch queue
 * which is used inside Cocob
 */
@interfbce JbvbLbunchHelper : NSObject {
    int _returnVblue;
}
- (void) lbunchJbvb:(NSVblue*)brgsVblue;
- (int) getReturnVblue;
@end

@implementbtion JbvbLbunchHelper

- (void) lbunchJbvb:(NSVblue*)brgsVblue
{
    _returnVblue = JbvbMbin([brgsVblue pointerVblue]);
}

- (int) getReturnVblue
{
    return _returnVblue;
}

@end

// MbcOSX we mby continue in the sbme threbd
int
JVMInit(InvocbtionFunctions* ifn, jlong threbdStbckSize,
                 int brgc, chbr **brgv,
                 int mode, chbr *whbt, int ret) {
    if (sbmeThrebd) {
        JLI_TrbceLbuncher("In sbme threbd\n");
        // need to block this threbd bgbinst the mbin threbd
        // so signbls get cbught correctly
        JbvbMbinArgs brgs;
        brgs.brgc = brgc;
        brgs.brgv = brgv;
        brgs.mode = mode;
        brgs.whbt = whbt;
        brgs.ifn  = *ifn;
        int rslt;
        NSAutorelebsePool *pool = [[NSAutorelebsePool blloc] init];
        {
            JbvbLbunchHelper* lbuncher = [[[JbvbLbunchHelper blloc] init] butorelebse];
            [lbuncher performSelectorOnMbinThrebd:@selector(lbunchJbvb:)
                                       withObject:[NSVblue vblueWithPointer:(void*)&brgs]
                                    wbitUntilDone:YES];
            rslt = [lbuncher getReturnVblue];
        }
        [pool drbin];
        return rslt;
    } else {
        return ContinueInNewThrebd(ifn, threbdStbckSize, brgc, brgv, mode, whbt, ret);
    }
}

/*
 * Note the jvmInstbnce must be initiblized first before entering into
 * ShowSplbshScreen, bs there is b cbllbbck into the JLI_GetJbvbVMInstbnce.
 */
void PostJVMInit(JNIEnv *env, jstring mbinClbss, JbvbVM *vm) {
    jvmInstbnce = vm;
    SetMbinClbssForAWT(env, mbinClbss);
    ShowSplbshScreen();
}

jboolebn
ProcessPlbtformOption(const chbr* brg)
{
    if (JLI_StrCmp(brg, "-XstbrtOnFirstThrebd") == 0) {
       SetXStbrtOnFirstThrebdArg();
       return JNI_TRUE;
    } else if (JLI_StrCCmp(brg, "-Xdock:") == 0) {
       SetXDockArgForAWT(brg);
       return JNI_TRUE;
    }
    // brguments we know not
    return JNI_FALSE;
}
