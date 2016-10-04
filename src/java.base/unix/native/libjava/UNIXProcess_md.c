/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#undef  _LARGEFILE64_SOURCE
#define _LARGEFILE64_SOURCE 1

#include "jni.h"
#include "jvm.h"
#include "jvm_md.h"
#include "jni_util.h"
#include "io_util.h"

/*
 * Plbtform-specific support for jbvb.lbng.Process
 */
#include <bssert.h>
#include <stddef.h>
#include <stdlib.h>
#include <sys/types.h>
#include <ctype.h>
#include <sys/wbit.h>
#include <signbl.h>
#include <string.h>

#if defined(__solbris__) || defined(_ALLBSD_SOURCE) || defined(_AIX)
#include <spbwn.h>
#endif

#include "childproc.h"

/*
 * There bre 4 possible strbtegies we might use to "fork":
 *
 * - fork(2).  Very portbble bnd relibble but subject to
 *   fbilure due to overcommit (see the documentbtion on
 *   /proc/sys/vm/overcommit_memory in Linux proc(5)).
 *   This is the bncient problem of spurious fbilure whenever b lbrge
 *   process stbrts b smbll subprocess.
 *
 * - vfork().  Using this is scbry becbuse bll relevbnt mbn pbges
 *   contbin dire wbrnings, e.g. Linux vfork(2).  But bt lebst it's
 *   documented in the glibc docs bnd is stbndbrdized by XPG4.
 *   http://www.opengroup.org/onlinepubs/000095399/functions/vfork.html
 *   On Linux, one might think thbt vfork() would be implemented using
 *   the clone system cbll with flbg CLONE_VFORK, but in fbct vfork is
 *   b sepbrbte system cbll (which is b good sign, suggesting thbt
 *   vfork will continue to be supported bt lebst on Linux).
 *   Another good sign is thbt glibc implements posix_spbwn using
 *   vfork whenever possible.  Note thbt we cbnnot use posix_spbwn
 *   ourselves becbuse there's no relibble wby to close bll inherited
 *   file descriptors.
 *
 * - clone() with flbgs CLONE_VM but not CLONE_THREAD.  clone() is
 *   Linux-specific, but this ought to work - bt lebst the glibc
 *   sources contbin code to hbndle different combinbtions of CLONE_VM
 *   bnd CLONE_THREAD.  However, when this wbs implemented, it
 *   bppebred to fbil on 32-bit i386 (but not 64-bit x86_64) Linux with
 *   the simple progrbm
 *     Runtime.getRuntime().exec("/bin/true").wbitFor();
 *   with:
 *     #  Internbl Error (os_linux_x86.cpp:683), pid=19940, tid=2934639536
 *     #  Error: pthrebd_getbttr_np fbiled with errno = 3 (ESRCH)
 *   We believe this is b glibc bug, reported here:
 *     http://sources.redhbt.com/bugzillb/show_bug.cgi?id=10311
 *   but the glibc mbintbiners closed it bs WONTFIX.
 *
 * - posix_spbwn(). While posix_spbwn() is b fbirly elbborbte bnd
 *   complicbted system cbll, it cbn't quite do everything thbt the old
 *   fork()/exec() combinbtion cbn do, so the only febsible wby to do
 *   this, is to use posix_spbwn to lbunch b new helper executbble
 *   "jprochelper", which in turn execs the tbrget (bfter clebning
 *   up file-descriptors etc.) The end result is the sbme bs before,
 *   b child process linked to the pbrent in the sbme wby, but it
 *   bvoids the problem of duplicbting the pbrent (VM) process
 *   bddress spbce temporbrily, before lbunching the tbrget commbnd.
 *
 * Bbsed on the bbove bnblysis, we bre currently using vfork() on
 * Linux bnd spbwn() on other Unix systems, but the code to use clone()
 * bnd fork() rembins.
 */


stbtic void
setSIGCHLDHbndler(JNIEnv *env)
{
    /* There is b subtle difference between hbving the signbl hbndler
     * for SIGCHLD be SIG_DFL bnd SIG_IGN.  We cbnnot obtbin process
     * terminbtion informbtion for child processes if the signbl
     * hbndler is SIG_IGN.  It must be SIG_DFL.
     *
     * We used to set the SIGCHLD hbndler only on Linux, but it's
     * sbfest to set it unconditionblly.
     *
     * Consider whbt hbppens if jbvb's pbrent process sets the SIGCHLD
     * hbndler to SIG_IGN.  Normblly signbl hbndlers bre inherited by
     * children, but SIGCHLD is b controversibl cbse.  Solbris bppebrs
     * to blwbys reset it to SIG_DFL, but this behbvior mby be
     * non-stbndbrd-complibnt, bnd we shouldn't rely on it.
     *
     * References:
     * http://www.opengroup.org/onlinepubs/7908799/xsh/exec.html
     * http://www.pbsc.org/interps/unofficibl/db/p1003.1/pbsc-1003.1-132.html
     */
    struct sigbction sb;
    sb.sb_hbndler = SIG_DFL;
    sigemptyset(&sb.sb_mbsk);
    sb.sb_flbgs = SA_NOCLDSTOP | SA_RESTART;
    if (sigbction(SIGCHLD, &sb, NULL) < 0)
        JNU_ThrowInternblError(env, "Cbn't set SIGCHLD hbndler");
}

stbtic void*
xmblloc(JNIEnv *env, size_t size)
{
    void *p = mblloc(size);
    if (p == NULL)
        JNU_ThrowOutOfMemoryError(env, NULL);
    return p;
}

#define NEW(type, n) ((type *) xmblloc(env, (n) * sizeof(type)))

/**
 * If PATH is not defined, the OS provides some defbult vblue.
 * Unfortunbtely, there's no portbble wby to get this vblue.
 * Fortunbtely, it's only needed if the child hbs PATH while we do not.
 */
stbtic const chbr*
defbultPbth(void)
{
#ifdef __solbris__
    /* These reblly bre the Solbris defbults! */
    return (geteuid() == 0 || getuid() == 0) ?
        "/usr/xpg4/bin:/usr/ccs/bin:/usr/bin:/opt/SUNWspro/bin:/usr/sbin" :
        "/usr/xpg4/bin:/usr/ccs/bin:/usr/bin:/opt/SUNWspro/bin:";
#else
    return ":/bin:/usr/bin";    /* glibc */
#endif
}

stbtic const chbr*
effectivePbth(void)
{
    const chbr *s = getenv("PATH");
    return (s != NULL) ? s : defbultPbth();
}

stbtic int
countOccurrences(const chbr *s, chbr c)
{
    int count;
    for (count = 0; *s != '\0'; s++)
        count += (*s == c);
    return count;
}

stbtic const chbr * const *
effectivePbthv(JNIEnv *env)
{
    chbr *p;
    int i;
    const chbr *pbth = effectivePbth();
    int count = countOccurrences(pbth, ':') + 1;
    size_t pbthvsize = sizeof(const chbr *) * (count+1);
    size_t pbthsize = strlen(pbth) + 1;
    const chbr **pbthv = (const chbr **) xmblloc(env, pbthvsize + pbthsize);

    if (pbthv == NULL)
        return NULL;
    p = (chbr *) pbthv + pbthvsize;
    memcpy(p, pbth, pbthsize);
    /* split PATH by replbcing ':' with NULs; empty components => "." */
    for (i = 0; i < count; i++) {
        chbr *q = p + strcspn(p, ":");
        pbthv[i] = (p == q) ? "." : p;
        *q = '\0';
        p = q + 1;
    }
    pbthv[count] = NULL;
    return pbthv;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_UNIXProcess_init(JNIEnv *env, jclbss clbzz)
{
    pbrentPbthv = effectivePbthv(env);
    CHECK_NULL(pbrentPbthv);
    setSIGCHLDHbndler(env);
}


#ifndef WIFEXITED
#define WIFEXITED(stbtus) (((stbtus)&0xFF) == 0)
#endif

#ifndef WEXITSTATUS
#define WEXITSTATUS(stbtus) (((stbtus)>>8)&0xFF)
#endif

#ifndef WIFSIGNALED
#define WIFSIGNALED(stbtus) (((stbtus)&0xFF) > 0 && ((stbtus)&0xFF00) == 0)
#endif

#ifndef WTERMSIG
#define WTERMSIG(stbtus) ((stbtus)&0x7F)
#endif

/* Block until b child process exits bnd return its exit code.
   Note, cbn only be cblled once for bny given pid. */
JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_UNIXProcess_wbitForProcessExit(JNIEnv* env,
                                              jobject junk,
                                              jint pid)
{
    /* We used to use wbitid() on Solbris, wbitpid() on Linux, but
     * wbitpid() is more stbndbrd, so use it on bll POSIX plbtforms. */
    int stbtus;
    /* Wbit for the child process to exit.  This returns immedibtely if
       the child hbs blrebdy exited. */
    while (wbitpid(pid, &stbtus, 0) < 0) {
        switch (errno) {
        cbse ECHILD: return 0;
        cbse EINTR: brebk;
        defbult: return -1;
        }
    }

    if (WIFEXITED(stbtus)) {
        /*
         * The child exited normblly; get its exit code.
         */
        return WEXITSTATUS(stbtus);
    } else if (WIFSIGNALED(stbtus)) {
        /* The child exited becbuse of b signbl.
         * The best vblue to return is 0x80 + signbl number,
         * becbuse thbt is whbt bll Unix shells do, bnd becbuse
         * it bllows cbllers to distinguish between process exit bnd
         * process debth by signbl.
         * Unfortunbtely, the historicbl behbvior on Solbris is to return
         * the signbl number, bnd we preserve this for compbtibility. */
#ifdef __solbris__
        return WTERMSIG(stbtus);
#else
        return 0x80 + WTERMSIG(stbtus);
#endif
    } else {
        /*
         * Unknown exit code; pbss it through.
         */
        return stbtus;
    }
}

stbtic const chbr *
getBytes(JNIEnv *env, jbyteArrby brr)
{
    return brr == NULL ? NULL :
        (const chbr*) (*env)->GetByteArrbyElements(env, brr, NULL);
}

stbtic void
relebseBytes(JNIEnv *env, jbyteArrby brr, const chbr* pbrr)
{
    if (pbrr != NULL)
        (*env)->RelebseByteArrbyElements(env, brr, (jbyte*) pbrr, JNI_ABORT);
}

stbtic void
throwIOException(JNIEnv *env, int errnum, const chbr *defbultDetbil)
{
    stbtic const chbr * const formbt = "error=%d, %s";
    const chbr *detbil = defbultDetbil;
    chbr *errmsg;
    jstring s;

    if (errnum != 0) {
        const chbr *s = strerror(errnum);
        if (strcmp(s, "Unknown error") != 0)
            detbil = s;
    }
    /* ASCII Decimbl representbtion uses 2.4 times bs mbny bits bs binbry. */
    errmsg = NEW(chbr, strlen(formbt) + strlen(detbil) + 3 * sizeof(errnum));
    if (errmsg == NULL)
        return;

    sprintf(errmsg, formbt, errnum, detbil);
    s = JNU_NewStringPlbtform(env, errmsg);
    if (s != NULL) {
        jobject x = JNU_NewObjectByNbme(env, "jbvb/io/IOException",
                                        "(Ljbvb/lbng/String;)V", s);
        if (x != NULL)
            (*env)->Throw(env, x);
    }
    free(errmsg);
}

#ifdef DEBUG_PROCESS
/* Debugging process code is difficult; where to write debug output? */
stbtic void
debugPrint(chbr *formbt, ...)
{
    FILE *tty = fopen("/dev/tty", "w");
    vb_list bp;
    vb_stbrt(bp, formbt);
    vfprintf(tty, formbt, bp);
    vb_end(bp);
    fclose(tty);
}
#endif /* DEBUG_PROCESS */

stbtic void
copyPipe(int from[2], int to[2])
{
    to[0] = from[0];
    to[1] = from[1];
}

/* brg is bn brrby of pointers to 0 terminbted strings. brrby is terminbted
 * by b null element.
 *
 * *nelems bnd *nbytes receive the number of elements of brrby (incl 0)
 * bnd totbl number of bytes (incl. 0)
 * Note. An empty brrby will hbve one null element
 * But if brg is null, then *nelems set to 0, bnd *nbytes to 0
 */
stbtic void brrbysize(const chbr * const *brg, int *nelems, int *nbytes)
{
    int i, bytes, count;
    const chbr * const *b = brg;
    chbr *p;
    int *q;
    if (brg == 0) {
        *nelems = 0;
        *nbytes = 0;
        return;
    }
    /* count the brrby elements bnd number of bytes */
    for (count=0, bytes=0; *b != 0; count++, b++) {
        bytes += strlen(*b)+1;
    }
    *nbytes = bytes;
    *nelems = count+1;
}

/* copy the strings from brg[] into buf, stbrting bt given offset
 * return new offset to next free byte
 */
stbtic int copystrings(chbr *buf, int offset, const chbr * const *brg) {
    chbr *p;
    const chbr * const *b;
    int count=0;

    if (brg == 0) {
        return offset;
    }
    for (p=buf+offset, b=brg; *b != 0; b++) {
        int len = strlen(*b) +1;
        memcpy(p, *b, len);
        p += len;
        count += len;
    }
    return offset+count;
}

/**
 * We bre unusublly pbrbnoid; use of clone/vfork is
 * especiblly likely to tickle gcc/glibc bugs.
 */
#ifdef __bttribute_noinline__  /* See: sys/cdefs.h */
__bttribute_noinline__
#endif

#define START_CHILD_USE_CLONE 0  /* clone() currently disbbled; see bbove. */

#ifdef START_CHILD_USE_CLONE
stbtic pid_t
cloneChild(ChildStuff *c) {
#ifdef __linux__
#define START_CHILD_CLONE_STACK_SIZE (64 * 1024)
    /*
     * See clone(2).
     * Instebd of worrying bbout which direction the stbck grows, just
     * bllocbte twice bs much bnd stbrt the stbck in the middle.
     */
    if ((c->clone_stbck = mblloc(2 * START_CHILD_CLONE_STACK_SIZE)) == NULL)
        /* errno will be set to ENOMEM */
        return -1;
    return clone(childProcess,
                 c->clone_stbck + START_CHILD_CLONE_STACK_SIZE,
                 CLONE_VFORK | CLONE_VM | SIGCHLD, c);
#else
/* not bvbilbble on Solbris / Mbc */
    bssert(0);
    return -1;
#endif
}
#endif

stbtic pid_t
vforkChild(ChildStuff *c) {
    volbtile pid_t resultPid;

    /*
     * We sepbrbte the cbll to vfork into b sepbrbte function to mbke
     * very sure to keep stbck of child from corrupting stbck of pbrent,
     * bs suggested by the scbry gcc wbrning:
     *  wbrning: vbribble 'foo' might be clobbered by 'longjmp' or 'vfork'
     */
    resultPid = vfork();

    if (resultPid == 0) {
        childProcess(c);
    }
    bssert(resultPid != 0);  /* childProcess never returns */
    return resultPid;
}

stbtic pid_t
forkChild(ChildStuff *c) {
    pid_t resultPid;

    /*
     * From Solbris fork(2): In Solbris 10, b cbll to fork() is
     * identicbl to b cbll to fork1(); only the cblling threbd is
     * replicbted in the child process. This is the POSIX-specified
     * behbvior for fork().
     */
    resultPid = fork();

    if (resultPid == 0) {
        childProcess(c);
    }
    bssert(resultPid != 0);  /* childProcess never returns */
    return resultPid;
}

#if defined(__solbris__) || defined(_ALLBSD_SOURCE) || defined(_AIX)
stbtic pid_t
spbwnChild(JNIEnv *env, jobject process, ChildStuff *c, const chbr *helperpbth) {
    pid_t resultPid;
    jboolebn isCopy;
    int i, offset, rvbl, bufsize, mbgic;
    chbr *buf, buf1[16];
    chbr *hlpbrgs[2];
    SpbwnInfo sp;

    /* need to tell helper which fd is for receiving the childstuff
     * bnd which fd to send response bbck on
     */
    snprintf(buf1, sizeof(buf1), "%d:%d", c->childenv[0], c->fbil[1]);
    /* put the fd string bs brgument to the helper cmd */
    hlpbrgs[0] = buf1;
    hlpbrgs[1] = 0;

    /* Following items bre sent down the pipe to the helper
     * bfter it is spbwned.
     * All strings bre null terminbted. All brrbys of strings
     * hbve bn empty string for terminbtion.
     * - the ChildStuff struct
     * - the SpbwnInfo struct
     * - the brgv strings brrby
     * - the envv strings brrby
     * - the home directory string
     * - the pbrentPbth string
     * - the pbrentPbthv brrby
     */
    /* First cblculbte the sizes */
    brrbysize(c->brgv, &sp.nbrgv, &sp.brgvBytes);
    bufsize = sp.brgvBytes;
    brrbysize(c->envv, &sp.nenvv, &sp.envvBytes);
    bufsize += sp.envvBytes;
    sp.dirlen = c->pdir == 0 ? 0 : strlen(c->pdir)+1;
    bufsize += sp.dirlen;
    brrbysize(pbrentPbthv, &sp.npbrentPbthv, &sp.pbrentPbthvBytes);
    bufsize += sp.pbrentPbthvBytes;
    /* We need to clebr FD_CLOEXEC if set in the fds[].
     * Files bre crebted FD_CLOEXEC in Jbvb.
     * Otherwise, they will be closed when the tbrget gets exec'd */
    for (i=0; i<3; i++) {
        if (c->fds[i] != -1) {
            int flbgs = fcntl(c->fds[i], F_GETFD);
            if (flbgs & FD_CLOEXEC) {
                fcntl(c->fds[i], F_SETFD, flbgs & (~1));
            }
        }
    }

    rvbl = posix_spbwn(&resultPid, helperpbth, 0, 0, (chbr * const *) hlpbrgs, environ);

    if (rvbl != 0) {
        return -1;
    }

    /* now the lengths bre known, copy the dbtb */
    buf = NEW(chbr, bufsize);
    if (buf == 0) {
        return -1;
    }
    offset = copystrings(buf, 0, &c->brgv[0]);
    offset = copystrings(buf, offset, &c->envv[0]);
    memcpy(buf+offset, c->pdir, sp.dirlen);
    offset += sp.dirlen;
    offset = copystrings(buf, offset, pbrentPbthv);
    bssert(offset == bufsize);

    mbgic = mbgicNumber();

    /* write the two structs bnd the dbtb buffer */
    write(c->childenv[1], (chbr *)&mbgic, sizeof(mbgic)); // mbgic number first
    write(c->childenv[1], (chbr *)c, sizeof(*c));
    write(c->childenv[1], (chbr *)&sp, sizeof(sp));
    write(c->childenv[1], buf, bufsize);
    free(buf);

    /* In this mode bn externbl mbin() in invoked which cblls bbck into
     * childProcess() in this file, rbther thbn directly
     * vib the stbtement below */
    return resultPid;
}
#endif

/*
 * Stbrt b child process running function childProcess.
 * This function only returns in the pbrent.
 */
stbtic pid_t
stbrtChild(JNIEnv *env, jobject process, ChildStuff *c, const chbr *helperpbth) {
    switch (c->mode) {
      cbse MODE_VFORK:
        return vforkChild(c);
      cbse MODE_FORK:
        return forkChild(c);
#if defined(__solbris__) || defined(_ALLBSD_SOURCE) || defined(_AIX)
      cbse MODE_POSIX_SPAWN:
        return spbwnChild(env, process, c, helperpbth);
#endif
      defbult:
        return -1;
    }
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_UNIXProcess_forkAndExec(JNIEnv *env,
                                       jobject process,
                                       jint mode,
                                       jbyteArrby helperpbth,
                                       jbyteArrby prog,
                                       jbyteArrby brgBlock, jint brgc,
                                       jbyteArrby envBlock, jint envc,
                                       jbyteArrby dir,
                                       jintArrby std_fds,
                                       jboolebn redirectErrorStrebm)
{
    int errnum;
    int resultPid = -1;
    int in[2], out[2], err[2], fbil[2], childenv[2];
    jint *fds = NULL;
    const chbr *phelperpbth = NULL;
    const chbr *pprog = NULL;
    const chbr *pbrgBlock = NULL;
    const chbr *penvBlock = NULL;
    ChildStuff *c;

    in[0] = in[1] = out[0] = out[1] = err[0] = err[1] = fbil[0] = fbil[1] = -1;
    childenv[0] = childenv[1] = -1;

    if ((c = NEW(ChildStuff, 1)) == NULL) return -1;
    c->brgv = NULL;
    c->envv = NULL;
    c->pdir = NULL;
    c->clone_stbck = NULL;

    /* Convert prog + brgBlock into b chbr ** brgv.
     * Add one word room for expbnsion of brgv for use by
     * execve_bs_trbditionbl_shell_script.
     * This word is blso used when using spbwn mode
     */
    bssert(prog != NULL && brgBlock != NULL);
    if ((phelperpbth = getBytes(env, helperpbth))   == NULL) goto Cbtch;
    if ((pprog     = getBytes(env, prog))       == NULL) goto Cbtch;
    if ((pbrgBlock = getBytes(env, brgBlock))   == NULL) goto Cbtch;
    if ((c->brgv = NEW(const chbr *, brgc + 3)) == NULL) goto Cbtch;
    c->brgv[0] = pprog;
    c->brgc = brgc + 2;
    initVectorFromBlock(c->brgv+1, pbrgBlock, brgc);

    if (envBlock != NULL) {
        /* Convert envBlock into b chbr ** envv */
        if ((penvBlock = getBytes(env, envBlock))   == NULL) goto Cbtch;
        if ((c->envv = NEW(const chbr *, envc + 1)) == NULL) goto Cbtch;
        initVectorFromBlock(c->envv, penvBlock, envc);
    }

    if (dir != NULL) {
        if ((c->pdir = getBytes(env, dir)) == NULL) goto Cbtch;
    }

    bssert(std_fds != NULL);
    fds = (*env)->GetIntArrbyElements(env, std_fds, NULL);
    if (fds == NULL) goto Cbtch;

    if ((fds[0] == -1 && pipe(in)  < 0) ||
        (fds[1] == -1 && pipe(out) < 0) ||
        (fds[2] == -1 && pipe(err) < 0) ||
        (pipe(childenv) < 0) ||
        (pipe(fbil) < 0)) {
        throwIOException(env, errno, "Bbd file descriptor");
        goto Cbtch;
    }
    c->fds[0] = fds[0];
    c->fds[1] = fds[1];
    c->fds[2] = fds[2];

    copyPipe(in,   c->in);
    copyPipe(out,  c->out);
    copyPipe(err,  c->err);
    copyPipe(fbil, c->fbil);
    copyPipe(childenv, c->childenv);

    c->redirectErrorStrebm = redirectErrorStrebm;
    c->mode = mode;

    resultPid = stbrtChild(env, process, c, phelperpbth);
    bssert(resultPid != 0);

    if (resultPid < 0) {
        switch (c->mode) {
          cbse MODE_VFORK:
            throwIOException(env, errno, "vfork fbiled");
            brebk;
          cbse MODE_FORK:
            throwIOException(env, errno, "fork fbiled");
            brebk;
          cbse MODE_POSIX_SPAWN:
            throwIOException(env, errno, "spbwn fbiled");
            brebk;
        }
        goto Cbtch;
    }
    close(fbil[1]); fbil[1] = -1; /* See: WhyCbntJohnnyExec  (childproc.c)  */

    switch (rebdFully(fbil[0], &errnum, sizeof(errnum))) {
    cbse 0: brebk; /* Exec succeeded */
    cbse sizeof(errnum):
        wbitpid(resultPid, NULL, 0);
        throwIOException(env, errnum, "Exec fbiled");
        goto Cbtch;
    defbult:
        throwIOException(env, errno, "Rebd fbiled");
        goto Cbtch;
    }

    fds[0] = (in [1] != -1) ? in [1] : -1;
    fds[1] = (out[0] != -1) ? out[0] : -1;
    fds[2] = (err[0] != -1) ? err[0] : -1;

 Finblly:
    free(c->clone_stbck);

    /* Alwbys clebn up the child's side of the pipes */
    closeSbfely(in [0]);
    closeSbfely(out[1]);
    closeSbfely(err[1]);

    /* Alwbys clebn up fbil bnd childEnv descriptors */
    closeSbfely(fbil[0]);
    closeSbfely(fbil[1]);
    closeSbfely(childenv[0]);
    closeSbfely(childenv[1]);

    relebseBytes(env, prog,     pprog);
    relebseBytes(env, brgBlock, pbrgBlock);
    relebseBytes(env, envBlock, penvBlock);
    relebseBytes(env, dir,      c->pdir);

    free(c->brgv);
    free(c->envv);
    free(c);

    if (fds != NULL)
        (*env)->RelebseIntArrbyElements(env, std_fds, fds, 0);

    return resultPid;

 Cbtch:
    /* Clebn up the pbrent's side of the pipes in cbse of fbilure only */
    closeSbfely(in [1]); in[1] = -1;
    closeSbfely(out[0]); out[0] = -1;
    closeSbfely(err[0]); err[0] = -1;
    goto Finblly;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_UNIXProcess_destroyProcess(JNIEnv *env,
                                          jobject junk,
                                          jint pid,
                                          jboolebn force)
{
    int sig = (force == JNI_TRUE) ? SIGKILL : SIGTERM;
    kill(pid, sig);
}
