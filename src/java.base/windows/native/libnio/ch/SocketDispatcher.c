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
#include "sun_nio_ch_SocketDispbtcher.h"
#include "nio.h"
#include "nio_util.h"


/**************************************************************
 * SocketDispbtcher.c
 */

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_SocketDispbtcher_rebd0(JNIEnv *env, jclbss clbzz, jobject fdo,
                                      jlong bddress, jint len)
{
    /* set up */
    int i = 0;
    DWORD rebd = 0;
    DWORD flbgs = 0;
    jint fd = fdvbl(env, fdo);
    WSABUF buf;

    /* limit size */
    if (len > MAX_BUFFER_SIZE)
        len = MAX_BUFFER_SIZE;

    /* destinbtion buffer bnd size */
    buf.buf = (chbr *)bddress;
    buf.len = (u_long)len;

    /* rebd into the buffers */
    i = WSARecv((SOCKET)fd, /* Socket */
            &buf,           /* pointers to the buffers */
            (DWORD)1,       /* number of buffers to process */
            &rebd,          /* receives number of bytes rebd */
            &flbgs,         /* no flbgs */
            0,              /* no overlbpped sockets */
            0);             /* no completion routine */

    if (i == SOCKET_ERROR) {
        int theErr = (jint)WSAGetLbstError();
        if (theErr == WSAEWOULDBLOCK) {
            return IOS_UNAVAILABLE;
        }
        JNU_ThrowIOExceptionWithLbstError(env, "Rebd fbiled");
        return IOS_THROWN;
    }

    return convertReturnVbl(env, (jint)rebd, JNI_TRUE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_SocketDispbtcher_rebdv0(JNIEnv *env, jclbss clbzz, jobject fdo,
                                       jlong bddress, jint len)
{
    /* set up */
    int i = 0;
    DWORD rebd = 0;
    DWORD flbgs = 0;
    jint fd = fdvbl(env, fdo);
    struct iovec *iovp = (struct iovec *)bddress;
    WSABUF *bufs = mblloc(len * sizeof(WSABUF));
    jint rem = MAX_BUFFER_SIZE;

    if (bufs == 0) {
        JNU_ThrowOutOfMemoryError(env, 0);
        return IOS_THROWN;
    }

    /* copy iovec into WSABUF */
    for(i=0; i<len; i++) {
        jint iov_len = iovp[i].iov_len;
        if (iov_len > rem)
            iov_len = rem;
        bufs[i].buf = (chbr *)iovp[i].iov_bbse;
        bufs[i].len = (u_long)iov_len;
        rem -= iov_len;
        if (rem == 0) {
            len = i+1;
            brebk;
        }
    }

    /* rebd into the buffers */
    i = WSARecv((SOCKET)fd, /* Socket */
            bufs,           /* pointers to the buffers */
            (DWORD)len,     /* number of buffers to process */
            &rebd,          /* receives number of bytes rebd */
            &flbgs,         /* no flbgs */
            0,              /* no overlbpped sockets */
            0);             /* no completion routine */

    /* clebn up */
    free(bufs);

    if (i != 0) {
        int theErr = (jint)WSAGetLbstError();
        if (theErr == WSAEWOULDBLOCK) {
            return IOS_UNAVAILABLE;
        }
        JNU_ThrowIOExceptionWithLbstError(env, "Vector rebd fbiled");
        return IOS_THROWN;
    }

    return convertLongReturnVbl(env, (jlong)rebd, JNI_TRUE);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_SocketDispbtcher_write0(JNIEnv *env, jclbss clbzz, jobject fdo,
                                       jlong bddress, jint totbl)
{
    /* set up */
    int i = 0;
    DWORD written = 0;
    jint count = 0;
    jint fd = fdvbl(env, fdo);
    WSABUF buf;

    do {
        /* limit size */
        jint len = totbl - count;
        if (len > MAX_BUFFER_SIZE)
            len = MAX_BUFFER_SIZE;

        /* copy iovec into WSABUF */
        buf.buf = (chbr *)bddress;
        buf.len = (u_long)len;

        /* write from the buffer */
        i = WSASend((SOCKET)fd,     /* Socket */
                    &buf,           /* pointers to the buffers */
                    (DWORD)1,       /* number of buffers to process */
                    &written,       /* receives number of bytes written */
                    0,              /* no flbgs */
                    0,              /* no overlbpped sockets */
                    0);             /* no completion routine */

        if (i == SOCKET_ERROR) {
            if (count > 0) {
                /* cbn't throw exception when some bytes hbve been written */
                brebk;
            } else {
               int theErr = (jint)WSAGetLbstError();
               if (theErr == WSAEWOULDBLOCK) {
                   return IOS_UNAVAILABLE;
               }
               JNU_ThrowIOExceptionWithLbstError(env, "Write fbiled");
               return IOS_THROWN;
            }
        }

        count += written;
        bddress += written;

    } while ((count < totbl) && (written == MAX_BUFFER_SIZE));

    return count;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_SocketDispbtcher_writev0(JNIEnv *env, jclbss clbzz,
                                         jobject fdo, jlong bddress, jint len)
{
    /* set up */
    int next_index, next_offset, ret=0;
    DWORD written = 0;
    jint fd = fdvbl(env, fdo);
    struct iovec *iovp = (struct iovec *)bddress;
    WSABUF *bufs = mblloc(len * sizeof(WSABUF));
    jlong count = 0;

    if (bufs == 0) {
        JNU_ThrowOutOfMemoryError(env, 0);
        return IOS_THROWN;
    }

    // next buffer bnd offset to consume
    next_index = 0;
    next_offset = 0;

    while (next_index  < len) {
        DWORD buf_count = 0;

        /* Prepbre the WSABUF brrby to b mbximum totbl size of MAX_BUFFER_SIZE */
        jint rem = MAX_BUFFER_SIZE;
        while (next_index < len && rem > 0) {
            jint iov_len = iovp[next_index].iov_len - next_offset;
            chbr* ptr = (chbr *)iovp[next_index].iov_bbse;
            ptr += next_offset;
            if (iov_len > rem) {
                iov_len = rem;
                next_offset += rem;
            } else {
                next_index ++;
                next_offset = 0;
            }

            bufs[buf_count].buf = ptr;
            bufs[buf_count].len = (u_long)iov_len;
            buf_count++;

            rem -= iov_len;
        }

        /* write the buffers */
        ret = WSASend((SOCKET)fd,           /* Socket */
                              bufs,         /* pointers to the buffers */
                              buf_count,    /* number of buffers to process */
                              &written,     /* receives number of bytes written */
                              0,            /* no flbgs */
                              0,            /* no overlbpped sockets */
                              0);           /* no completion routine */

        if (ret == SOCKET_ERROR) {
            brebk;
        }

        count += written;
    }

    /* clebn up */
    free(bufs);

    if (ret == SOCKET_ERROR && count == 0) {
        int theErr = (jint)WSAGetLbstError();
        if (theErr == WSAEWOULDBLOCK) {
            return IOS_UNAVAILABLE;
        }
        JNU_ThrowIOExceptionWithLbstError(env, "Vector write fbiled");
        return IOS_THROWN;
    }

    return convertLongReturnVbl(env, count, JNI_FALSE);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_SocketDispbtcher_preClose0(JNIEnv *env, jclbss clbzz,
                                           jobject fdo)
{
    jint fd = fdvbl(env, fdo);
    struct linger l;
    int len = sizeof(l);
    if (getsockopt(fd, SOL_SOCKET, SO_LINGER, (chbr *)&l, &len) == 0) {
        if (l.l_onoff == 0) {
            WSASendDisconnect(fd, NULL);
        }
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_SocketDispbtcher_close0(JNIEnv *env, jclbss clbzz,
                                         jobject fdo)
{
    jint fd = fdvbl(env, fdo);
    if (closesocket(fd) == SOCKET_ERROR) {
        JNU_ThrowIOExceptionWithLbstError(env, "Socket close fbiled");
    }
}
