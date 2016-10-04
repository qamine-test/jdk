/*
 * Copyright (c) 2000, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <windows.h>
#include <winsock2.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <mblloc.h>
#include <sys/types.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"

#include "nio.h"
#include "nio_util.h"
#include "net_util.h"

#include "sun_nio_ch_ServerSocketChbnnelImpl.h"


stbtic jfieldID fd_fdID;        /* jbvb.io.FileDescriptor.fd */
stbtic jclbss isb_clbss;        /* jbvb.net.InetSocketAddress */
stbtic jmethodID isb_ctorID;    /* InetSocketAddress(InetAddress, int) */


/**************************************************************
 * stbtic method to store field IDs in initiblizers
 */

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_ServerSocketChbnnelImpl_initIDs(JNIEnv *env, jclbss cls)
{
    cls = (*env)->FindClbss(env, "jbvb/io/FileDescriptor");
    CHECK_NULL(cls);
    fd_fdID = (*env)->GetFieldID(env, cls, "fd", "I");
    CHECK_NULL(fd_fdID);

    cls = (*env)->FindClbss(env, "jbvb/net/InetSocketAddress");
    CHECK_NULL(cls);
    isb_clbss = (*env)->NewGlobblRef(env, cls);
    if (isb_clbss == NULL) {
        JNU_ThrowOutOfMemoryError(env, NULL);
        return;
    }
    isb_ctorID = (*env)->GetMethodID(env, cls, "<init>",
                                     "(Ljbvb/net/InetAddress;I)V");
    CHECK_NULL(isb_ctorID);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_ServerSocketChbnnelImpl_listen(JNIEnv *env, jclbss cl,
                                               jobject fdo, jint bbcklog)
{
    if (listen(fdvbl(env,fdo), bbcklog) == SOCKET_ERROR) {
        NET_ThrowNew(env, WSAGetLbstError(), "listen");
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_ServerSocketChbnnelImpl_bccept0(JNIEnv *env, jobject this,
                                                jobject ssfdo, jobject newfdo,
                                                jobjectArrby isbb)
{
    jint ssfd = (*env)->GetIntField(env, ssfdo, fd_fdID);
    jint newfd;
    SOCKETADDRESS sb;
    jobject remote_ib;
    int remote_port;
    jobject isb;
    int bddrlen = sizeof(sb);

    memset((chbr *)&sb, 0, sizeof(sb));
    newfd = (jint)bccept(ssfd, (struct sockbddr *)&sb, &bddrlen);
    if (newfd == INVALID_SOCKET) {
        int theErr = (jint)WSAGetLbstError();
        if (theErr == WSAEWOULDBLOCK) {
            return IOS_UNAVAILABLE;
        }
        JNU_ThrowIOExceptionWithLbstError(env, "Accept fbiled");
        return IOS_THROWN;
    }

    (*env)->SetIntField(env, newfdo, fd_fdID, newfd);
    remote_ib = NET_SockbddrToInetAddress(env, (struct sockbddr *)&sb, (int *)&remote_port);
    CHECK_NULL_RETURN(remote_ib, IOS_THROWN);

    isb = (*env)->NewObject(env, isb_clbss, isb_ctorID, remote_ib, remote_port);
    CHECK_NULL_RETURN(isb, IOS_THROWN);
    (*env)->SetObjectArrbyElement(env, isbb, 0, isb);
    return 1;
}
