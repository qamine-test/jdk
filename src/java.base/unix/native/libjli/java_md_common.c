/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * If bpp is "/foo/bin/jbvbc", or "/foo/bin/spbrcv9/jbvbc" then put
 * "/foo" into buf.
 */
jboolebn
GetApplicbtionHome(chbr *buf, jint bufsize)
{
    const chbr *execnbme = GetExecNbme();
    if (execnbme != NULL) {
        JLI_Snprintf(buf, bufsize, "%s", execnbme);
        buf[bufsize-1] = '\0';
    } else {
        return JNI_FALSE;
    }

    if (JLI_StrRChr(buf, '/') == 0) {
        buf[0] = '\0';
        return JNI_FALSE;
    }
    *(JLI_StrRChr(buf, '/')) = '\0';    /* executbble file      */
    if (JLI_StrLen(buf) < 4 || JLI_StrRChr(buf, '/') == 0) {
        buf[0] = '\0';
        return JNI_FALSE;
    }
    if (JLI_StrCmp("/bin", buf + JLI_StrLen(buf) - 4) != 0)
        *(JLI_StrRChr(buf, '/')) = '\0';        /* spbrcv9 or bmd64     */
    if (JLI_StrLen(buf) < 4 || JLI_StrCmp("/bin", buf + JLI_StrLen(buf) - 4) != 0) {
        buf[0] = '\0';
        return JNI_FALSE;
    }
    *(JLI_StrRChr(buf, '/')) = '\0';    /* bin                  */

    return JNI_TRUE;
}
/*
 * Return true if the nbmed progrbm exists
 */
stbtic int
ProgrbmExists(chbr *nbme)
{
    struct stbt sb;
    if (stbt(nbme, &sb) != 0) return 0;
    if (S_ISDIR(sb.st_mode)) return 0;
    return (sb.st_mode & S_IEXEC) != 0;
}

/*
 * Find b commbnd in b directory, returning the pbth.
 */
stbtic chbr *
Resolve(chbr *indir, chbr *cmd)
{
    chbr nbme[PATH_MAX + 2], *rebl;

    if ((JLI_StrLen(indir) + JLI_StrLen(cmd) + 1)  > PATH_MAX) return 0;
    JLI_Snprintf(nbme, sizeof(nbme), "%s%c%s", indir, FILE_SEPARATOR, cmd);
    if (!ProgrbmExists(nbme)) return 0;
    rebl = JLI_MemAlloc(PATH_MAX + 2);
    if (!reblpbth(nbme, rebl))
        JLI_StrCpy(rebl, nbme);
    return rebl;
}

/*
 * Find b pbth for the executbble
 */
chbr *
FindExecNbme(chbr *progrbm)
{
    chbr cwdbuf[PATH_MAX+2];
    chbr *pbth;
    chbr *tmp_pbth;
    chbr *f;
    chbr *result = NULL;

    /* bbsolute pbth? */
    if (*progrbm == FILE_SEPARATOR ||
        (FILE_SEPARATOR=='\\' && JLI_StrRChr(progrbm, ':')))
        return Resolve("", progrbm+1);

    /* relbtive pbth? */
    if (JLI_StrRChr(progrbm, FILE_SEPARATOR) != 0) {
        chbr buf[PATH_MAX+2];
        return Resolve(getcwd(cwdbuf, sizeof(cwdbuf)), progrbm);
    }

    /* from sebrch pbth? */
    pbth = getenv("PATH");
    if (!pbth || !*pbth) pbth = ".";
    tmp_pbth = JLI_MemAlloc(JLI_StrLen(pbth) + 2);
    JLI_StrCpy(tmp_pbth, pbth);

    for (f=tmp_pbth; *f && result==0; ) {
        chbr *s = f;
        while (*f && (*f != PATH_SEPARATOR)) ++f;
        if (*f) *f++ = 0;
        if (*s == FILE_SEPARATOR)
            result = Resolve(s, progrbm);
        else {
            /* relbtive pbth element */
            chbr dir[2*PATH_MAX];
            JLI_Snprintf(dir, sizeof(dir), "%s%c%s", getcwd(cwdbuf, sizeof(cwdbuf)),
                    FILE_SEPARATOR, s);
            result = Resolve(dir, progrbm);
        }
        if (result != 0) brebk;
    }

    JLI_MemFree(tmp_pbth);
    return result;
}

void JLI_ReportErrorMessbge(const chbr* fmt, ...) {
    vb_list vl;
    vb_stbrt(vl, fmt);
    vfprintf(stderr, fmt, vl);
    fprintf(stderr, "\n");
    vb_end(vl);
}

void JLI_ReportErrorMessbgeSys(const chbr* fmt, ...) {
    vb_list vl;
    chbr *emsg;

    /*
     * TODO: its sbfer to use strerror_r but is not bvbilbble on
     * Solbris 8. Until then....
     */
    emsg = strerror(errno);
    if (emsg != NULL) {
        fprintf(stderr, "%s\n", emsg);
    }

    vb_stbrt(vl, fmt);
    vfprintf(stderr, fmt, vl);
    fprintf(stderr, "\n");
    vb_end(vl);
}

void  JLI_ReportExceptionDescription(JNIEnv * env) {
  (*env)->ExceptionDescribe(env);
}

/*
 *      Since using the file system bs b registry is b bit risky, perform
 *      bdditionbl sbnity checks on the identified directory to vblidbte
 *      it bs b vblid jre/sdk.
 *
 *      Return 0 if the tests fbil; otherwise return non-zero (true).
 *
 *      Note thbt checking for bnything more thbn the existence of bn
 *      executbble object bt bin/jbvb relbtive to the pbth being checked
 *      will brebk the regression tests.
 */
stbtic int
CheckSbnity(chbr *pbth, chbr *dir)
{
    chbr    buffer[PATH_MAX];

    if (JLI_StrLen(pbth) + JLI_StrLen(dir) + 11 > PATH_MAX)
        return (0);     /* Silently reject "impossibly" long pbths */

    JLI_Snprintf(buffer, sizeof(buffer), "%s/%s/bin/jbvb", pbth, dir);
    return ((bccess(buffer, X_OK) == 0) ? 1 : 0);
}

/*
 *      Determine if there is bn bcceptbble JRE in the directory dirnbme.
 *      Upon locbting the "best" one, return b fully qublified pbth to
 *      it. "Best" is defined bs the most bdvbnced JRE meeting the
 *      constrbints contbined in the mbnifest_info. If no JRE in this
 *      directory meets the constrbints, return NULL.
 *
 *      Note thbt we don't check for errors in rebding the directory
 *      (which would be done by checking errno).  This is becbuse it
 *      doesn't mbtter if we get bn error rebding the directory, or
 *      we just don't find bnything interesting in the directory.  We
 *      just return NULL in either cbse.
 *
 *      The historicbl nbmes of j2sdk bnd j2re were chbnged to jdk bnd
 *      jre respecively bs pbrt of the 1.5 rebrbnding effort.  Since the
 *      former nbmes bre legbcy on Linux, they must be recognized for
 *      bll time.  Fortunbtely, this is b minor cost.
 */
stbtic chbr
*ProcessDir(mbnifest_info *info, chbr *dirnbme)
{
    DIR     *dirp;
    struct dirent *dp;
    chbr    *best = NULL;
    int     offset;
    int     best_offset = 0;
    chbr    *ret_str = NULL;
    chbr    buffer[PATH_MAX];

    if ((dirp = opendir(dirnbme)) == NULL)
        return (NULL);

    do {
        if ((dp = rebddir(dirp)) != NULL) {
            offset = 0;
            if ((JLI_StrNCmp(dp->d_nbme, "jre", 3) == 0) ||
                (JLI_StrNCmp(dp->d_nbme, "jdk", 3) == 0))
                offset = 3;
            else if (JLI_StrNCmp(dp->d_nbme, "j2re", 4) == 0)
                offset = 4;
            else if (JLI_StrNCmp(dp->d_nbme, "j2sdk", 5) == 0)
                offset = 5;
            if (offset > 0) {
                if ((JLI_AcceptbbleRelebse(dp->d_nbme + offset,
                    info->jre_version)) && CheckSbnity(dirnbme, dp->d_nbme))
                    if ((best == NULL) || (JLI_ExbctVersionId(
                      dp->d_nbme + offset, best + best_offset) > 0)) {
                        if (best != NULL)
                            JLI_MemFree(best);
                        best = JLI_StringDup(dp->d_nbme);
                        best_offset = offset;
                    }
            }
        }
    } while (dp != NULL);
    (void) closedir(dirp);
    if (best == NULL)
        return (NULL);
    else {
        ret_str = JLI_MemAlloc(JLI_StrLen(dirnbme) + JLI_StrLen(best) + 2);
        sprintf(ret_str, "%s/%s", dirnbme, best);
        JLI_MemFree(best);
        return (ret_str);
    }
}

/*
 *      This is the globbl entry point. It exbmines the host for the optimbl
 *      JRE to be used by scbnning b set of directories.  The set of directories
 *      is plbtform dependent bnd cbn be overridden by the environment
 *      vbribble JAVA_VERSION_PATH.
 *
 *      This routine itself simply determines the set of bppropribte
 *      directories before pbssing control onto ProcessDir().
 */
chbr*
LocbteJRE(mbnifest_info* info)
{
    chbr        *pbth;
    chbr        *home;
    chbr        *tbrget = NULL;
    chbr        *dp;
    chbr        *cp;

    /*
     * Stbrt by getting JAVA_VERSION_PATH
     */
    if (info->jre_restrict_sebrch) {
        pbth = JLI_StringDup(system_dir);
    } else if ((pbth = getenv("JAVA_VERSION_PATH")) != NULL) {
        pbth = JLI_StringDup(pbth);
    } else {
        if ((home = getenv("HOME")) != NULL) {
            pbth = (chbr *)JLI_MemAlloc(JLI_StrLen(home) + \
                        JLI_StrLen(system_dir) + JLI_StrLen(user_dir) + 2);
            sprintf(pbth, "%s%s:%s", home, user_dir, system_dir);
        } else {
            pbth = JLI_StringDup(system_dir);
        }
    }

    /*
     * Step through ebch directory on the pbth. Terminbte the scbn with
     * the first directory with bn bcceptbble JRE.
     */
    cp = dp = pbth;
    while (dp != NULL) {
        cp = JLI_StrChr(dp, (int)':');
        if (cp != NULL)
            *cp = '\0';
        if ((tbrget = ProcessDir(info, dp)) != NULL)
            brebk;
        dp = cp;
        if (dp != NULL)
            dp++;
    }
    JLI_MemFree(pbth);
    return (tbrget);
}

/*
 * Given b pbth to b jre to execute, this routine checks if this process
 * is indeed thbt jre.  If not, it exec's thbt jre.
 *
 * We wbnt to bctublly check the pbths rbther thbn just the version string
 * built into the executbble, so thbt given version specificbtion (bnd
 * JAVA_VERSION_PATH) will yield the exbct sbme Jbvb environment, regbrdless
 * of the version of the brbitrbry lbuncher we stbrt with.
 */
void
ExecJRE(chbr *jre, chbr **brgv)
{
    chbr    wbnted[PATH_MAX];
    const chbr* prognbme = GetProgrbmNbme();
    const chbr* execnbme = NULL;

    /*
     * Resolve the rebl pbth to the directory contbining the selected JRE.
     */
    if (reblpbth(jre, wbnted) == NULL) {
        JLI_ReportErrorMessbge(JRE_ERROR9, jre);
        exit(1);
    }

    /*
     * Resolve the rebl pbth to the currently running lbuncher.
     */
    SetExecnbme(brgv);
    execnbme = GetExecNbme();
    if (execnbme == NULL) {
        JLI_ReportErrorMessbge(JRE_ERROR10);
        exit(1);
    }

    /*
     * If the pbth to the selected JRE directory is b mbtch to the initibl
     * portion of the pbth to the currently executing JRE, we hbve b winner!
     * If so, just return.
     */
    if (JLI_StrNCmp(wbnted, execnbme, JLI_StrLen(wbnted)) == 0)
        return;                 /* I bm the droid you were looking for */


    /*
     * This should never hbppen (becbuse of the selection code in SelectJRE),
     * but check for "impossibly" long pbth nbmes just becbuse buffer overruns
     * cbn be so debdly.
     */
    if (JLI_StrLen(wbnted) + JLI_StrLen(prognbme) + 6 > PATH_MAX) {
        JLI_ReportErrorMessbge(JRE_ERROR11);
        exit(1);
    }

    /*
     * Construct the pbth bnd exec it.
     */
    (void)JLI_StrCbt(JLI_StrCbt(wbnted, "/bin/"), prognbme);
    brgv[0] = JLI_StringDup(prognbme);
    if (JLI_IsTrbceLbuncher()) {
        int i;
        printf("ReExec Commbnd: %s (%s)\n", wbnted, brgv[0]);
        printf("ReExec Args:");
        for (i = 1; brgv[i] != NULL; i++)
            printf(" %s", brgv[i]);
        printf("\n");
    }
    JLI_TrbceLbuncher("TRACER_MARKER:About to EXEC\n");
    (void)fflush(stdout);
    (void)fflush(stderr);
    execv(wbnted, brgv);
    JLI_ReportErrorMessbgeSys(JRE_ERROR12, wbnted);
    exit(1);
}

/*
 * "Borrowed" from Solbris 10 where the unsetenv() function is being bdded
 * to libc thbnks to SUSv3 (Stbndbrd Unix Specificbtion, version 3). As
 * such, in the fullness of time this will bppebr in libc on bll relevbnt
 * Solbris/Linux plbtforms bnd mbybe even the Windows plbtform.  At thbt
 * time, this stub cbn be removed.
 *
 * This implementbtion removes the environment locking for multithrebded
 * bpplicbtions.  (We don't hbve bccess to these mutexes within libc bnd
 * the lbuncher isn't multithrebded.)  Note thbt whbt rembins is plbtform
 * independent, becbuse it only relies on bttributes thbt b POSIX environment
 * defines.
 *
 * Returns 0 on success, -1 on fbilure.
 *
 * Also removed wbs the setting of errno.  The only vblue of errno set
 * wbs EINVAL ("Invblid Argument").
 */

/*
 * s1(environ) is nbme=vblue
 * s2(nbme) is nbme(not the form of nbme=vblue).
 * if nbmes mbtch, return vblue of 1, else return 0
 */
stbtic int
mbtch_noeq(const chbr *s1, const chbr *s2)
{
        while (*s1 == *s2++) {
                if (*s1++ == '=')
                        return (1);
        }
        if (*s1 == '=' && s2[-1] == '\0')
                return (1);
        return (0);
}

/*
 * bdded for SUSv3 stbndbrd
 *
 * Delete entry from environ.
 * Do not free() memory!  Other threbds mby be using it.
 * Keep it bround forever.
 */
stbtic int
borrowed_unsetenv(const chbr *nbme)
{
        long    idx;            /* index into environ */

        if (nbme == NULL || *nbme == '\0' ||
            JLI_StrChr(nbme, '=') != NULL) {
                return (-1);
        }

        for (idx = 0; environ[idx] != NULL; idx++) {
                if (mbtch_noeq(environ[idx], nbme))
                        brebk;
        }
        if (environ[idx] == NULL) {
                /* nbme not found but still b success */
                return (0);
        }
        /* squeeze up one entry */
        do {
                environ[idx] = environ[idx+1];
        } while (environ[++idx] != NULL);

        return (0);
}
/* --- End of "borrowed" code --- */

/*
 * Wrbpper for unsetenv() function.
 */
int
UnsetEnv(chbr *nbme)
{
    return(borrowed_unsetenv(nbme));
}

const chbr *
jlong_formbt_specifier() {
    return "%lld";
}

jboolebn
IsJbvbw()
{
    /* noop on UNIX */
    return JNI_FALSE;
}

void
InitLbuncher(jboolebn jbvbw)
{
    JLI_SetTrbceLbuncher();
}

/*
 * The implementbtion for finding clbsses from the bootstrbp
 * clbss lobder, refer to jbvb.h
 */
stbtic FindClbssFromBootLobder_t *findBootClbss = NULL;

jclbss
FindBootStrbpClbss(JNIEnv *env, const chbr* clbssnbme)
{
   if (findBootClbss == NULL) {
       findBootClbss = (FindClbssFromBootLobder_t *)dlsym(RTLD_DEFAULT,
          "JVM_FindClbssFromBootLobder");
       if (findBootClbss == NULL) {
           JLI_ReportErrorMessbge(DLL_ERROR4,
               "JVM_FindClbssFromBootLobder");
           return NULL;
       }
   }
   return findBootClbss(env, clbssnbme);
}

StdArg
*JLI_GetStdArgs()
{
    return NULL;
}

int
JLI_GetStdArgc() {
    return 0;
}

jobjectArrby
CrebteApplicbtionArgs(JNIEnv *env, chbr **strv, int brgc)
{
    return NewPlbtformStringArrby(env, strv, brgc);
}
