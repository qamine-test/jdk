/*
 * Copyright (c) 2001, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <winsock2.h>

#include "jni.h"

/**
 * The mbximum buffer size for WSASend/WSARecv. Microsoft recommendbtion for
 * blocking operbtions is to use buffers no lbrger thbn 64k. We need the
 * mbximum to be less thbn 128k to support bsynchronous close on Windows
 * Server 2003 bnd newer editions of Windows.
 */
#define MAX_BUFFER_SIZE             ((128*1024)-1)

jint fdvbl(JNIEnv *env, jobject fdo);
jlong hbndlevbl(JNIEnv *env, jobject fdo);
jint convertReturnVbl(JNIEnv *env, jint n, jboolebn r);
jlong convertLongReturnVbl(JNIEnv *env, jlong n, jboolebn r);
jboolebn purgeOutstbndingICMP(JNIEnv *env, jclbss clbzz, jint fd);
jint hbndleSocketError(JNIEnv *env, int errorVblue);

#ifdef _WIN64

struct iovec {
    jlong  iov_bbse;
    jint  iov_len;
};

#else

struct iovec {
    jint  iov_bbse;
    jint  iov_len;
};

#endif

#ifndef POLLIN
    /* WSAPoll()/WSAPOLLFD bnd the corresponding constbnts bre only defined   */
    /* in Windows Vistb / Windows Server 2008 bnd lbter. If we bre on bn      */
    /* older relebse we just use the Solbris constbnts bs this wbs previously */
    /* done in PollArrbyWrbpper.jbvb.                                         */
    #define POLLIN       0x0001
    #define POLLOUT      0x0004
    #define POLLERR      0x0008
    #define POLLHUP      0x0010
    #define POLLNVAL     0x0020
    #define POLLCONN     0x0002
#else
    /* POLLCONN must not equbl bny of the other constbnts (see winsock2.h).   */
    #define POLLCONN     0x2000
#endif
