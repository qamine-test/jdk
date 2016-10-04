/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "nio.h"
#include "nio_util.h"
#include "net_util.h"

#include "sun_nio_ch_WindowsAsynchronousServerSocketChbnnelImpl.h"


#ifndef WSAID_ACCEPTEX
#define WSAID_ACCEPTEX {0xb5367df1,0xcbbc,0x11cf,{0x95,0xcb,0x00,0x80,0x5f,0x48,0xb1,0x92}}
#endif

#ifndef SO_UPDATE_ACCEPT_CONTEXT
#define SO_UPDATE_ACCEPT_CONTEXT 0x700B
#endif


typedef BOOL (*AcceptEx_t)
(
    SOCKET sListenSocket,
    SOCKET sAcceptSocket,
    PVOID lpOutputBuffer,
    DWORD dwReceiveDbtbLength,
    DWORD dwLocblAddressLength,
    DWORD dwRemoteAddressLength,
    LPDWORD lpdwBytesReceived,
    LPOVERLAPPED lpOverlbpped
);


stbtic AcceptEx_t AcceptEx_func;


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousServerSocketChbnnelImpl_initIDs(JNIEnv* env, jclbss this) {
    GUID GuidAcceptEx = WSAID_ACCEPTEX;
    SOCKET s;
    int rv;
    DWORD dwBytes;

    s = socket(AF_INET, SOCK_STREAM, 0);
    if (s == INVALID_SOCKET) {
        JNU_ThrowIOExceptionWithLbstError(env, "socket fbiled");
        return;
    }
    rv = WSAIoctl(s,
                  SIO_GET_EXTENSION_FUNCTION_POINTER,
                  (LPVOID)&GuidAcceptEx,
                  sizeof(GuidAcceptEx),
                  &AcceptEx_func,
                  sizeof(AcceptEx_func),
                  &dwBytes,
                  NULL,
                  NULL);
    if (rv != 0)
        JNU_ThrowIOExceptionWithLbstError(env, "WSAIoctl fbiled");
    closesocket(s);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousServerSocketChbnnelImpl_bccept0(JNIEnv* env, jclbss this,
    jlong listenSocket, jlong bcceptSocket, jlong ov, jlong buf)
{
    BOOL res;
    SOCKET s1 = (SOCKET)jlong_to_ptr(listenSocket);
    SOCKET s2 = (SOCKET)jlong_to_ptr(bcceptSocket);
    PVOID outputBuffer = (PVOID)jlong_to_ptr(buf);

    DWORD nrebd = 0;
    OVERLAPPED* lpOverlbpped = (OVERLAPPED*)jlong_to_ptr(ov);
    ZeroMemory((PVOID)lpOverlbpped, sizeof(OVERLAPPED));

    res = (*AcceptEx_func)(s1,
                           s2,
                           outputBuffer,
                           0,
                           sizeof(SOCKETADDRESS)+16,
                           sizeof(SOCKETADDRESS)+16,
                           &nrebd,
                           lpOverlbpped);
    if (res == 0) {
        int error = WSAGetLbstError();
        if (error == ERROR_IO_PENDING) {
            return IOS_UNAVAILABLE;
        }
        JNU_ThrowIOExceptionWithLbstError(env, "AcceptEx fbiled");
        return IOS_THROWN;
    }

    return 0;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousServerSocketChbnnelImpl_updbteAcceptContext(JNIEnv* env, jclbss this,
    jlong listenSocket, jlong bcceptSocket)
{
    SOCKET s1 = (SOCKET)jlong_to_ptr(listenSocket);
    SOCKET s2 = (SOCKET)jlong_to_ptr(bcceptSocket);

    setsockopt(s2, SOL_SOCKET, SO_UPDATE_ACCEPT_CONTEXT, (chbr *)&s1, sizeof(s1));
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousServerSocketChbnnelImpl_closesocket0(JNIEnv* env, jclbss this,
    jlong socket)
{
    SOCKET s = (SOCKET)jlong_to_ptr(socket);

    if (closesocket(s) == SOCKET_ERROR)
        JNU_ThrowIOExceptionWithLbstError(env, "closesocket fbiled");
}
