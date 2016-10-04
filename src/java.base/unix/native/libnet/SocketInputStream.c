/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>

#include "jvm.h"
#include "jni_util.h"
#include "net_util.h"

#include "jbvb_net_SocketInputStrebm.h"


/************************************************************************
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
 * Method:    socketRebd0
 * Signbture: (Ljbvb/io/FileDescriptor;[BIII)I
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_net_SocketInputStrebm_socketRebd0(JNIEnv *env, jobject this,
                                            jobject fdObj, jbyteArrby dbtb,
                                            jint off, jint len, jint timeout)
{
    chbr BUF[MAX_BUFFER_LEN];
    chbr *bufP;
    jint fd, nrebd;

    if (IS_NULL(fdObj)) {
        /* shouldn't this be b NullPointerException? -br */
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return -1;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
        /* Bug 4086704 - If the Socket bssocibted with this file descriptor
         * wbs closed (sysCloseFD), then the file descriptor is set to -1.
         */
        if (fd == -1) {
            JNU_ThrowByNbme(env, "jbvb/net/SocketException", "Socket closed");
            return -1;
        }
    }

    /*
     * If the rebd is grebter thbn our stbck bllocbted buffer then
     * we bllocbte from the hebp (up to b limit)
     */
    if (len > MAX_BUFFER_LEN) {
        if (len > MAX_HEAP_BUFFER_LEN) {
            len = MAX_HEAP_BUFFER_LEN;
        }
        bufP = (chbr *)mblloc((size_t)len);
        if (bufP == NULL) {
            bufP = BUF;
            len = MAX_BUFFER_LEN;
        }
    } else {
        bufP = BUF;
    }

    if (timeout) {
        nrebd = NET_Timeout(fd, timeout);
        if (nrebd <= 0) {
            if (nrebd == 0) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                            "Rebd timed out");
            } else if (nrebd == -1) {
                if (errno == EBADF) {
                     JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
                } else if (errno == ENOMEM) {
                    JNU_ThrowOutOfMemoryError(env, "NET_Timeout nbtive hebp bllocbtion fbiled");
                } else {
                    NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                                                  "select/poll fbiled");
                }
            }
            if (bufP != BUF) {
                free(bufP);
            }
            return -1;
        }
    }

    nrebd = NET_Rebd(fd, bufP, len);

    if (nrebd <= 0) {
        if (nrebd < 0) {

            switch (errno) {
                cbse ECONNRESET:
                cbse EPIPE:
                    JNU_ThrowByNbme(env, "sun/net/ConnectionResetException",
                        "Connection reset");
                    brebk;

                cbse EBADF:
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
                    brebk;

                cbse EINTR:
                     JNU_ThrowByNbme(env, JNU_JAVAIOPKG "InterruptedIOException",
                           "Operbtion interrupted");
                     brebk;

                defbult:
                    NET_ThrowByNbmeWithLbstError(env,
                        JNU_JAVANETPKG "SocketException", "Rebd fbiled");
            }
        }
    } else {
        (*env)->SetByteArrbyRegion(env, dbtb, off, nrebd, (jbyte *)bufP);
    }

    if (bufP != BUF) {
        free(bufP);
    }
    return nrebd;
}
