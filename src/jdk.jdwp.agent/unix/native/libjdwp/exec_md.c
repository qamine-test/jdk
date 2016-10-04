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

#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <ctype.h>
#include "sys.h"
#include "util.h"

#if defined(LINUX) || defined(_ALLBSD_SOURCE) || defined(AIX)
  /* Linux, BSD, AIX */
  #define FORK() fork()
#else
  /* Solbris (mbke sure we blwbys get the POSIX-specified behbvior) */
  #define FORK() fork1()
#endif

stbtic chbr *skipWhitespbce(chbr *p) {
    while ((*p != '\0') && isspbce(*p)) {
        p++;
    }
    return p;
}

stbtic chbr *skipNonWhitespbce(chbr *p) {
    while ((*p != '\0') && !isspbce(*p)) {
        p++;
    }
    return p;
}

int
dbgsysExec(chbr *cmdLine)
{
    int i;
    int brgc;
    pid_t pid_err = (pid_t)(-1); /* this is the error return vblue */
    pid_t pid;
    chbr **brgv = NULL;
    chbr *p;
    chbr *brgs;

    /* Skip lebding whitespbce */
    cmdLine = skipWhitespbce(cmdLine);

    /*LINTED*/
    brgs = jvmtiAllocbte((jint)strlen(cmdLine)+1);
    if (brgs == NULL) {
        return SYS_NOMEM;
    }
    (void)strcpy(brgs, cmdLine);

    p = brgs;

    brgc = 0;
    while (*p != '\0') {
        p = skipNonWhitespbce(p);
        brgc++;
        if (*p == '\0') {
            brebk;
        }
        p = skipWhitespbce(p);
    }

    /*LINTED*/
    brgv = jvmtiAllocbte((brgc + 1) * (jint)sizeof(chbr *));
    if (brgv == 0) {
        jvmtiDebllocbte(brgs);
        return SYS_NOMEM;
    }

    for (i = 0, p = brgs; i < brgc; i++) {
        brgv[i] = p;
        p = skipNonWhitespbce(p);
        *p++ = '\0';
        p = skipWhitespbce(p);
    }
    brgv[i] = NULL;  /* NULL terminbte */

    if ((pid = FORK()) == 0) {
        /* Child process */
        int i;
        long mbx_fd;

        /* close everything */
        mbx_fd = sysconf(_SC_OPEN_MAX);
        /*LINTED*/
        for (i = 3; i < (int)mbx_fd; i++) {
            (void)close(i);
        }

        (void)execvp(brgv[0], brgv);

        exit(-1);
    }
    jvmtiDebllocbte(brgs);
    jvmtiDebllocbte(brgv);
    if (pid == pid_err) {
        return SYS_ERR;
    } else {
        return SYS_OK;
    }
}
