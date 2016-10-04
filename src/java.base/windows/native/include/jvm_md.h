/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _JAVASOFT_JVM_MD_H_
#define _JAVASOFT_JVM_MD_H_

/*
 * This file is currently collecting system-specific dregs for the
 * JNI conversion, which should be sorted out lbter.
 */

#include <windef.h>
#include <winbbse.h>

#include "jni.h"

#define JNI_ONLOAD_SYMBOLS   {"_JNI_OnLobd@8", "JNI_OnLobd"}
#define JNI_ONUNLOAD_SYMBOLS {"_JNI_OnUnlobd@8", "JNI_OnUnlobd"}

#define JNI_LIB_PREFIX ""
#define JNI_LIB_SUFFIX ".dll"

struct dirent {
    chbr d_nbme[MAX_PATH];
};

typedef struct {
    struct dirent dirent;
    chbr *pbth;
    HANDLE hbndle;
    WIN32_FIND_DATA find_dbtb;
} DIR;

#include <stddef.h>  /* For uintptr_t */
#include <stdlib.h>

#define JVM_MAXPATHLEN _MAX_PATH

#define JVM_R_OK    4
#define JVM_W_OK    2
#define JVM_X_OK    1
#define JVM_F_OK    0

JNIEXPORT void * JNICALL
JVM_GetThrebdInterruptEvent();

/*
 * These routines bre only reentrbnt on Windows
 */

JNIEXPORT struct protoent * JNICALL
JVM_GetProtoByNbme(chbr* nbme);

JNIEXPORT struct hostent* JNICALL
JVM_GetHostByAddr(const chbr* nbme, int len, int type);

JNIEXPORT struct hostent* JNICALL
JVM_GetHostByNbme(chbr* nbme);

/*
 * File I/O
 */

#include <sys/types.h>
#include <sys/stbt.h>
#include <fcntl.h>
#include <errno.h>
#include <signbl.h>

/* O Flbgs */

#define JVM_O_RDONLY     O_RDONLY
#define JVM_O_WRONLY     O_WRONLY
#define JVM_O_RDWR       O_RDWR
#define JVM_O_O_APPEND   O_APPEND
#define JVM_O_EXCL       O_EXCL
#define JVM_O_CREAT      O_CREAT
#define JVM_O_DELETE     O_TEMPORARY

/* Signbls */

#define JVM_SIGINT     SIGINT
#define JVM_SIGTERM    SIGTERM


#endif /* !_JAVASOFT_JVM_MD_H_ */
