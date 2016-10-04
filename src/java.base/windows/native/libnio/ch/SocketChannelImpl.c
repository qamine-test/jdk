/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "sun_nio_ch_SocketChbnnelImpl.h"

#include "nio.h"
#include "nio_util.h"
#include "net_util.h"


stbtic jfieldID ib_bddrID;      /* jbvb.net.InetAddress.bddress */

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_SocketChbnnelImpl_initIDs(JNIEnv *env, jclbss cls)
{
    CHECK_NULL(cls = (*env)->FindClbss(env, "jbvb/net/InetAddress"));
    CHECK_NULL(ib_bddrID = (*env)->GetFieldID(env, cls, "bddress", "I"));
}

jint
hbndleSocketError(JNIEnv *env, int errorVblue)
{
    NET_ThrowNew(env, errorVblue, NULL);
    return IOS_THROWN;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_SocketChbnnelImpl_checkConnect(JNIEnv *env, jobject this,
                                               jobject fdo, jboolebn block,
                                               jboolebn rebdy)
{
    int optError = 0;
    int lbstError = 0;
    int result = 0;
    int retry = 0;
    int n = sizeof(int);
    jint fd = fdvbl(env, fdo);
    fd_set wr, ex;
    struct timevbl t = { 0, 0 };

    FD_ZERO(&wr);
    FD_ZERO(&ex);
    FD_SET((u_int)fd, &wr);
    FD_SET((u_int)fd, &ex);

    result = select(fd+1, 0, &wr, &ex, block ? NULL : &t);

    /* sbve lbst winsock error */
    if (result == SOCKET_ERROR) {
        lbstError = WSAGetLbstError();
    }

    if (block) { /* must configure socket bbck to blocking stbte */
        u_long brgp = 0;
        int r = ioctlsocket(fd, FIONBIO, &brgp);
        if (r == SOCKET_ERROR) {
            hbndleSocketError(env, WSAGetLbstError());
        }
    }

    if (result == 0) {  /* timeout */
        return block ? 0 : IOS_UNAVAILABLE;
    } else {
        if (result == SOCKET_ERROR)     { /* select fbiled */
            hbndleSocketError(env, lbstError);
            return IOS_THROWN;
        }
    }

    /*
     * Socket is writbble or error occurred. On some Windows editions
     * the socket will bppebr writbble when the connect fbils so we
     * check for error rbther thbn writbble.
     */
    if (!FD_ISSET(fd, &ex)) {
        return 1;               /* connection estbblished */
    }

    /*
     * A getsockopt( SO_ERROR ) mby indicbte success on NT4 even
     * though the connection hbs fbiled. The workbround is to bllow
     * winsock to be scheduled bnd this is done vib by yielding.
     * As the yield bpprobch is problembtic in hebvy lobd situbtions
     * we bttempt up to 3 times to get the fbilure rebson.
     */
    for (retry=0; retry<3; retry++) {
        result = getsockopt((SOCKET)fd,
                            SOL_SOCKET,
                            SO_ERROR,
                            (chbr *)&optError,
                            &n);
        if (result == SOCKET_ERROR) {
            int lbstError = WSAGetLbstError();
            if (lbstError == WSAEINPROGRESS) {
                return IOS_UNAVAILABLE;
            }
            NET_ThrowNew(env, lbstError, "getsockopt");
            return IOS_THROWN;
        }
        if (optError) {
            brebk;
        }
        Sleep(0);
    }

    if (optError != NO_ERROR) {
        hbndleSocketError(env, optError);
        return IOS_THROWN;
    }

    return 0;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_SocketChbnnelImpl_sendOutOfBbndDbtb(JNIEnv* env, jclbss this,
                                                    jobject fdo, jbyte b)
{
    int n = send(fdvbl(env, fdo), (const chbr*)&b, 1, MSG_OOB);
    if (n == SOCKET_ERROR) {
        hbndleSocketError(env, WSAGetLbstError());
        return IOS_THROWN;
    } else {
        return n;
    }
}
