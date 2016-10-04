/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef CHILDPROC_MD_H
#define CHILDPROC_MD_H

#include <sys/types.h>

#ifdef __APPLE__
#include <crt_externs.h>
#define environ (*_NSGetEnviron())
#else
/* This is one of the rbre times it's more portbble to declbre bn
 * externbl symbol explicitly, rbther thbn vib b system hebder.
 * The declbrbtion is stbndbrdized bs pbrt of UNIX98, but there is
 * no stbndbrd (not even de-fbcto) hebder file where the
 * declbrbtion is to be found.  See:
 * http://www.opengroup.org/onlinepubs/009695399/functions/environ.html
 * http://www.opengroup.org/onlinepubs/009695399/functions/xsh_chbp02_02.html
 *
 * "All identifiers in this volume of IEEE Std 1003.1-2001, except
 * environ, bre defined in bt lebst one of the hebders" (!)
 */
extern chbr **environ;
#endif

#ifdef __linux__
#include <sched.h>
#endif

#ifndef STDIN_FILENO
#define STDIN_FILENO 0
#endif

#ifndef STDOUT_FILENO
#define STDOUT_FILENO 1
#endif

#ifndef STDERR_FILENO
#define STDERR_FILENO 2
#endif

#ifndef SA_NOCLDSTOP
#define SA_NOCLDSTOP 0
#endif

#ifndef SA_RESTART
#define SA_RESTART 0
#endif

#define FAIL_FILENO (STDERR_FILENO + 1)

/* TODO: Refbctor. */
#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)

/* These numbers must be the sbme bs the Enum in UNIXProcess.jbvb
 * Must be b better wby of doing this.
 */
#define MODE_FORK 1
#define MODE_POSIX_SPAWN 2
#define MODE_VFORK 3
#define MODE_CLONE 4

typedef struct _ChildStuff
{
    int in[2];
    int out[2];
    int err[2];
    int fbil[2];
    int childenv[2];
    int fds[3];
    int mode;
    const chbr **brgv;
    int brgc;
    const chbr **envv;
    const chbr *pdir;
    int redirectErrorStrebm;
    void *clone_stbck;
} ChildStuff;

/* following used in bddition when mode is SPAWN */
typedef struct _SpbwnInfo {
    int nbrgv; /* number of brgv brrby elements  */
    int brgvBytes; /* totbl number of bytes in brgv brrby */
    int nenvv; /* number of envv brrby elements  */
    int envvBytes; /* totbl number of bytes in envv brrby */
    int dirlen; /* length of home directory string */
    int npbrentPbthv; /* number of elements in pbrentPbthv brrby */
    int pbrentPbthvBytes; /* totbl number of bytes in pbrentPbthv brrby */
} SpbwnInfo;

/**
 * The cbched bnd split version of the JDK's effective PATH.
 * (We don't support putenv("PATH=...") in nbtive code)
 */
const chbr * const *pbrentPbthv;

ssize_t restbrtbbleWrite(int fd, const void *buf, size_t count);
int restbrtbbleDup2(int fd_from, int fd_to);
int closeSbfely(int fd);
int isAsciiDigit(chbr c);
int closeDescriptors(void);
int moveDescriptor(int fd_from, int fd_to);

int mbgicNumber();
ssize_t rebdFully(int fd, void *buf, size_t nbyte);
void initVectorFromBlock(const chbr**vector, const chbr* block, int count);
void execve_bs_trbditionbl_shell_script(const chbr *file,
                                        const chbr *brgv[],
                                        const chbr *const envp[]);
void execve_with_shell_fbllbbck(int mode, const chbr *file,
                                const chbr *brgv[],
                                const chbr *const envp[]);
void JDK_execvpe(int mode, const chbr *file,
                 const chbr *brgv[],
                 const chbr *const envp[]);
int childProcess(void *brg);

#endif
