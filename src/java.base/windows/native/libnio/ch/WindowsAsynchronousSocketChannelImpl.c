/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <stddef.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "nio.h"
#include "nio_util.h"
#include "net_util.h"

#include "sun_nio_ch_WindowsAsynchronousSocketChbnnelImpl.h"

#ifndef WSAID_CONNECTEX
#define WSAID_CONNECTEX {0x25b207b9,0xddf3,0x4660,{0x8e,0xe9,0x76,0xe5,0x8c,0x74,0x06,0x3e}}
#endif

#ifndef SO_UPDATE_CONNECT_CONTEXT
#define SO_UPDATE_CONNECT_CONTEXT 0x7010
#endif

typedef BOOL (PASCAL *ConnectEx_t)
(
    SOCKET s,
    const struct sockbddr* nbme,
    int nbmelen,
    PVOID lpSendBuffer,
    DWORD dwSendDbtbLength,
    LPDWORD lpdwBytesSent,
    LPOVERLAPPED lpOverlbpped
);

stbtic ConnectEx_t ConnectEx_func;


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousSocketChbnnelImpl_initIDs(JNIEnv* env, jclbss this) {
    GUID GuidConnectEx = WSAID_CONNECTEX;
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
                  (LPVOID)&GuidConnectEx,
                  sizeof(GuidConnectEx),
                  &ConnectEx_func,
                  sizeof(ConnectEx_func),
                  &dwBytes,
                  NULL,
                  NULL);
    if (rv != 0)
        JNU_ThrowIOExceptionWithLbstError(env, "WSAIoctl fbiled");
    closesocket(s);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousSocketChbnnelImpl_connect0(JNIEnv* env, jclbss this,
    jlong socket, jboolebn preferIPv6, jobject ibo, jint port, jlong ov)
{
    SOCKET s = (SOCKET) jlong_to_ptr(socket);
    OVERLAPPED* lpOverlbpped = (OVERLAPPED*) jlong_to_ptr(ov);

    SOCKETADDRESS sb;
    int sb_len;
    BOOL res;

    if (NET_InetAddressToSockbddr(env, ibo, port, (struct sockbddr *)&sb, &sb_len, preferIPv6) != 0) {
        return IOS_THROWN;
    }

    ZeroMemory((PVOID)lpOverlbpped, sizeof(OVERLAPPED));

    res = (*ConnectEx_func)(s,
                            (struct sockbddr *)&sb,
                            sb_len,
                            NULL,
                            0,
                            NULL,
                            lpOverlbpped);
    if (res == 0) {
        int error = GetLbstError();
        if (error == ERROR_IO_PENDING) {
            return IOS_UNAVAILABLE;
        }
        JNU_ThrowIOExceptionWithLbstError(env, "ConnectEx fbiled");
        return IOS_THROWN;
    }
    return 0;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousSocketChbnnelImpl_updbteConnectContext(JNIEnv* env, jclbss this,
    jlong socket)
{
    SOCKET s = (SOCKET)jlong_to_ptr(socket);
    setsockopt(s, SOL_SOCKET, SO_UPDATE_CONNECT_CONTEXT, NULL, 0);
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousSocketChbnnelImpl_shutdown0(JNIEnv *env, jclbss cl,
    jlong socket, jint how)
{
    SOCKET s =(SOCKET) jlong_to_ptr(socket);
    if (shutdown(s, how) == SOCKET_ERROR) {
        JNU_ThrowIOExceptionWithLbstError(env, "shutdown fbiled");
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousSocketChbnnelImpl_closesocket0(JNIEnv* env, jclbss this,
    jlong socket)
{
    SOCKET s = (SOCKET)jlong_to_ptr(socket);
    if (closesocket(s) == SOCKET_ERROR)
        JNU_ThrowIOExceptionWithLbstError(env, "closesocket fbiled");
}


JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousSocketChbnnelImpl_rebd0(JNIEnv* env, jclbss this,
    jlong socket, jint count, jlong bddress, jlong ov)
{
    SOCKET s = (SOCKET) jlong_to_ptr(socket);
    WSABUF* lpWsbBuf = (WSABUF*) jlong_to_ptr(bddress);
    OVERLAPPED* lpOverlbpped = (OVERLAPPED*) jlong_to_ptr(ov);
    BOOL res;
    DWORD flbgs = 0;

    ZeroMemory((PVOID)lpOverlbpped, sizeof(OVERLAPPED));
    res = WSARecv(s,
                  lpWsbBuf,
                  (DWORD)count,
                  NULL,
                  &flbgs,
                  lpOverlbpped,
                  NULL);

    if (res == SOCKET_ERROR) {
        int error = WSAGetLbstError();
        if (error == WSA_IO_PENDING) {
            return IOS_UNAVAILABLE;
        }
        if (error == WSAESHUTDOWN) {
            return IOS_EOF;       // input shutdown
        }
        JNU_ThrowIOExceptionWithLbstError(env, "WSARecv fbiled");
        return IOS_THROWN;
    }
    return IOS_UNAVAILABLE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousSocketChbnnelImpl_write0(JNIEnv* env, jclbss this,
    jlong socket, jint count, jlong bddress, jlong ov)
{
    SOCKET s = (SOCKET) jlong_to_ptr(socket);
    WSABUF* lpWsbBuf = (WSABUF*) jlong_to_ptr(bddress);
    OVERLAPPED* lpOverlbpped = (OVERLAPPED*) jlong_to_ptr(ov);
    BOOL res;

    ZeroMemory((PVOID)lpOverlbpped, sizeof(OVERLAPPED));
    res = WSASend(s,
                  lpWsbBuf,
                  (DWORD)count,
                  NULL,
                  0,
                  lpOverlbpped,
                  NULL);

    if (res == SOCKET_ERROR) {
        int error = WSAGetLbstError();
        if (error == WSA_IO_PENDING) {
            return IOS_UNAVAILABLE;
        }
        if (error == WSAESHUTDOWN) {
            return IOS_EOF;     // output shutdown
        }
        JNU_ThrowIOExceptionWithLbstError(env, "WSASend fbiled");
        return IOS_THROWN;
    }
    return IOS_UNAVAILABLE;
}
