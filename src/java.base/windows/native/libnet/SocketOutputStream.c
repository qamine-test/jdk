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

#include <windows.h>
#include <winsock2.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <mblloc.h>
#include <sys/types.h>

#include "jbvb_net_SocketOutputStrebm.h"

#include "net_util.h"
#include "jni_util.h"

/************************************************************************
 * SocketOutputStrebm
 */
stbtic jfieldID IO_fd_fdID;

/*
 * Clbss:     jbvb_net_SocketOutputStrebm
 * Method:    init
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_SocketOutputStrebm_init(JNIEnv *env, jclbss cls) {
    IO_fd_fdID = NET_GetFileDescriptorID(env);
}

/*
 * Clbss:     jbvb_net_SocketOutputStrebm
 * Method:    socketWrite
 * Signbture: (Ljbvb/io/FileDescriptor;[BII)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_SocketOutputStrebm_socketWrite0(JNIEnv *env, jobject this,
                                              jobject fdObj, jbyteArrby dbtb,
                                              jint off, jint len) {
    chbr *bufP;
    chbr BUF[MAX_BUFFER_LEN];
    int buflen;
    int fd;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
        return;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    if (IS_NULL(dbtb)) {
        JNU_ThrowNullPointerException(env, "dbtb brgument");
        return;
    }

    /*
     * Use stbck bllocbte buffer if possible. For lbrge sizes we bllocbte
     * bn intermedibte buffer from the hebp (up to b mbximum). If hebp is
     * unbvbilbble just use our stbck buffer.
     */
    if (len <= MAX_BUFFER_LEN) {
        bufP = BUF;
        buflen = MAX_BUFFER_LEN;
    } else {
        buflen = min(MAX_HEAP_BUFFER_LEN, len);
        bufP = (chbr *)mblloc((size_t)buflen);
        if (bufP == NULL) {
            bufP = BUF;
            buflen = MAX_BUFFER_LEN;
        }
    }

    while(len > 0) {
        int loff = 0;
        int chunkLen = min(buflen, len);
        int llen = chunkLen;
        int retry = 0;

        (*env)->GetByteArrbyRegion(env, dbtb, off, chunkLen, (jbyte *)bufP);

        while(llen > 0) {
            int n = send(fd, bufP + loff, llen, 0);
            if (n > 0) {
                llen -= n;
                loff += n;
                continue;
            }

            /*
             * Due to b bug in Windows Sockets (observed on NT bnd Windows
             * 2000) it mby be necessbry to retry the send. The issue is thbt
             * on blocking sockets send/WSASend is supposed to block if there
             * is insufficient buffer spbce bvbilbble. If there bre b lbrge
             * number of threbds blocked on write due to congestion then it's
             * possile to hit the NT/2000 bug whereby send returns WSAENOBUFS.
             * The workbround we use is to retry the send. If we hbve b
             * lbrge buffer to send (>2k) then we retry with b mbximum of
             * 2k buffer. If we hit the issue with <=2k buffer then we bbckoff
             * for 1 second bnd retry bgbin. We repebt this up to b rebsonbble
             * limit before bbiling out bnd throwing bn exception. In lobd
             * conditions we've observed thbt the send will succeed bfter 2-3
             * bttempts but this depends on network buffers bssocibted with
             * other sockets drbining.
             */
            if (WSAGetLbstError() == WSAENOBUFS) {
                if (llen > MAX_BUFFER_LEN) {
                    buflen = MAX_BUFFER_LEN;
                    chunkLen = MAX_BUFFER_LEN;
                    llen = MAX_BUFFER_LEN;
                    continue;
                }
                if (retry >= 30) {
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "No buffer spbce bvbilbble - exhbusted bttempts to queue buffer");
                    if (bufP != BUF) {
                        free(bufP);
                    }
                    return;
                }
                Sleep(1000);
                retry++;
                continue;
            }

            /*
             * Send fbiled - cbn be cbused by close or write error.
             */
            if (WSAGetLbstError() == WSAENOTSOCK) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
            } else {
                NET_ThrowCurrent(env, "socket write error");
            }
            if (bufP != BUF) {
                free(bufP);
            }
            return;
        }
        len -= chunkLen;
        off += chunkLen;
    }

    if (bufP != BUF) {
        free(bufP);
    }
}
