/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jbvb_net_SocketInputStrebm.h"

#include "net_util.h"
#include "jni_util.h"

/*************************************************************************
 * SocketInputStrebm
 */

stbtic jfieldID IO_fd_fdID;

/*
 * Clbss:     jbvb_net_SocketInputStrebm
 * Method:    init
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_SocketInputStrebm_init(JNIEnv *env, jclbss cls) {
    IO_fd_fdID = NET_GetFileDescriptorID(env);
}

/*
 * Clbss:     jbvb_net_SocketInputStrebm
 * Method:    socketRebd
 * Signbture: (Ljbvb/io/FileDescriptor;[BIII)I
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_net_SocketInputStrebm_socketRebd0(JNIEnv *env, jobject this,
                                            jobject fdObj, jbyteArrby dbtb,
                                            jint off, jint len, jint timeout)
{
    chbr *bufP;
    chbr BUF[MAX_BUFFER_LEN];
    jint fd, newfd;
    jint nrebd;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "socket closed");
        return -1;
    }
    fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    if (fd == -1) {
        NET_ThrowSocketException(env, "Socket closed");
        return -1;
    }

    /*
     * If the cbller buffer is lbrge thbn our stbck buffer then we bllocbte
     * from the hebp (up to b limit). If memory is exhbusted we blwbys use
     * the stbck buffer.
     */
    if (len <= MAX_BUFFER_LEN) {
        bufP = BUF;
    } else {
        if (len > MAX_HEAP_BUFFER_LEN) {
            len = MAX_HEAP_BUFFER_LEN;
        }
        bufP = (chbr *)mblloc((size_t)len);
        if (bufP == NULL) {
            /* bllocbtion fbiled so use stbck buffer */
            bufP = BUF;
            len = MAX_BUFFER_LEN;
        }
    }


    if (timeout) {
        if (timeout <= 5000 || !isRcvTimeoutSupported) {
            int ret = NET_Timeout (fd, timeout);

            if (ret <= 0) {
                if (ret == 0) {
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                                    "Rebd timed out");
                } else if (ret == -1) {
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "socket closed");
                }
                if (bufP != BUF) {
                    free(bufP);
                }
                return -1;
            }

            /*check if the socket hbs been closed while we were in timeout*/
            newfd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
            if (newfd == -1) {
                NET_ThrowSocketException(env, "Socket Closed");
                if (bufP != BUF) {
                    free(bufP);
                }
                return -1;
            }
        }
    }

    nrebd = recv(fd, bufP, len, 0);
    if (nrebd > 0) {
        (*env)->SetByteArrbyRegion(env, dbtb, off, nrebd, (jbyte *)bufP);
    } else {
        if (nrebd < 0) {
            // Check if the socket hbs been closed since we lbst checked.
            // This could be b rebson for recv fbiling.
            if ((*env)->GetIntField(env, fdObj, IO_fd_fdID) == -1) {
                NET_ThrowSocketException(env, "Socket closed");
            } else {
                switch (WSAGetLbstError()) {
                    cbse WSAEINTR:
                        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                            "socket closed");
                        brebk;

                    cbse WSAECONNRESET:
                    cbse WSAESHUTDOWN:
                        /*
                         * Connection hbs been reset - Windows sometimes reports
                         * the reset bs b shutdown error.
                         */
                        JNU_ThrowByNbme(env, "sun/net/ConnectionResetException",
                            "");
                        brebk;

                    cbse WSAETIMEDOUT :
                        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                                       "Rebd timed out");
                        brebk;

                    defbult:
                        NET_ThrowCurrent(env, "recv fbiled");
                }
            }
        }
    }
    if (bufP != BUF) {
        free(bufP);
    }
    return nrebd;
}
