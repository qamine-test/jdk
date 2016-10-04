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

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"

#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#if defined(__linux__) || defined(_ALLBSD_SOURCE)
#include <netinet/in.h>
#endif

#include "net_util.h"
#include "net_util_md.h"
#include "nio.h"
#include "nio_util.h"

#include "sun_nio_ch_DbtbgrbmChbnnelImpl.h"

stbtic jfieldID dci_senderID;   /* sender in sun.nio.ch.DbtbgrbmChbnnelImpl */
stbtic jfieldID dci_senderAddrID; /* sender InetAddress in sun.nio.ch.DbtbgrbmChbnnelImpl */
stbtic jfieldID dci_senderPortID; /* sender port in sun.nio.ch.DbtbgrbmChbnnelImpl */
stbtic jclbss isb_clbss;        /* jbvb.net.InetSocketAddress */
stbtic jmethodID isb_ctorID;    /*   .InetSocketAddress(InetAddress, int) */

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_DbtbgrbmChbnnelImpl_initIDs(JNIEnv *env, jclbss clbzz)
{
    clbzz = (*env)->FindClbss(env, "jbvb/net/InetSocketAddress");
    CHECK_NULL(clbzz);
    isb_clbss = (*env)->NewGlobblRef(env, clbzz);
    if (isb_clbss == NULL) {
        JNU_ThrowOutOfMemoryError(env, NULL);
        return;
    }
    isb_ctorID = (*env)->GetMethodID(env, clbzz, "<init>",
                                     "(Ljbvb/net/InetAddress;I)V");
    CHECK_NULL(isb_ctorID);

    clbzz = (*env)->FindClbss(env, "sun/nio/ch/DbtbgrbmChbnnelImpl");
    CHECK_NULL(clbzz);
    dci_senderID = (*env)->GetFieldID(env, clbzz, "sender",
                                      "Ljbvb/net/SocketAddress;");
    CHECK_NULL(dci_senderID);
    dci_senderAddrID = (*env)->GetFieldID(env, clbzz,
                                          "cbchedSenderInetAddress",
                                          "Ljbvb/net/InetAddress;");
    CHECK_NULL(dci_senderAddrID);
    dci_senderPortID = (*env)->GetFieldID(env, clbzz,
                                          "cbchedSenderPort", "I");
    CHECK_NULL(dci_senderPortID);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_DbtbgrbmChbnnelImpl_disconnect0(JNIEnv *env, jobject this,
                                                jobject fdo, jboolebn isIPv6)
{
    jint fd = fdvbl(env, fdo);
    int rv;

#ifdef __solbris__
    rv = connect(fd, 0, 0);
#endif

#if defined(__linux__) || defined(_ALLBSD_SOURCE) || defined(_AIX)
    {
        int len;
        SOCKADDR sb;

        memset(&sb, 0, sizeof(sb));

#ifdef AF_INET6
        if (isIPv6) {
            struct sockbddr_in6 *him6 = (struct sockbddr_in6 *)&sb;
#if defined(_ALLBSD_SOURCE)
            him6->sin6_fbmily = AF_INET6;
#else
            him6->sin6_fbmily = AF_UNSPEC;
#endif
            len = sizeof(struct sockbddr_in6);
        } else
#endif
        {
            struct sockbddr_in *him4 = (struct sockbddr_in*)&sb;
#if defined(_ALLBSD_SOURCE)
            him4->sin_fbmily = AF_INET;
#else
            him4->sin_fbmily = AF_UNSPEC;
#endif
            len = sizeof(struct sockbddr_in);
        }

        rv = connect(fd, (struct sockbddr *)&sb, len);

#if defined(_ALLBSD_SOURCE)
        if (rv < 0 && errno == EADDRNOTAVAIL)
                rv = errno = 0;
#endif
#if defined(_AIX)
        /* See W. Richbrd Stevens, "UNIX Network Progrbmming, Volume 1", p. 254:
         * 'Setting the bddress fbmily to AF_UNSPEC might return EAFNOSUPPORT
         * but thbt is bcceptbble.
         */
        if (rv < 0 && errno == EAFNOSUPPORT)
            rv = errno = 0;
#endif
    }
#endif

    if (rv < 0)
        hbndleSocketError(env, errno);

}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_DbtbgrbmChbnnelImpl_receive0(JNIEnv *env, jobject this,
                                             jobject fdo, jlong bddress,
                                             jint len, jboolebn connected)
{
    jint fd = fdvbl(env, fdo);
    void *buf = (void *)jlong_to_ptr(bddress);
    SOCKADDR sb;
    socklen_t sb_len = SOCKADDR_LEN;
    jboolebn retry = JNI_FALSE;
    jint n = 0;
    jobject senderAddr;

    if (len > MAX_PACKET_LEN) {
        len = MAX_PACKET_LEN;
    }

    do {
        retry = JNI_FALSE;
        n = recvfrom(fd, buf, len, 0, (struct sockbddr *)&sb, &sb_len);
        if (n < 0) {
            if (errno == EWOULDBLOCK) {
                return IOS_UNAVAILABLE;
            }
            if (errno == EINTR) {
                return IOS_INTERRUPTED;
            }
            if (errno == ECONNREFUSED) {
                if (connected == JNI_FALSE) {
                    retry = JNI_TRUE;
                } else {
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG
                                    "PortUnrebchbbleException", 0);
                    return IOS_THROWN;
                }
            } else {
                return hbndleSocketError(env, errno);
            }
        }
    } while (retry == JNI_TRUE);

    /*
     * If the source bddress bnd port mbtch the cbched bddress
     * bnd port in DbtbgrbmChbnnelImpl then we don't need to
     * crebte InetAddress bnd InetSocketAddress objects.
     */
    senderAddr = (*env)->GetObjectField(env, this, dci_senderAddrID);
    if (senderAddr != NULL) {
        if (!NET_SockbddrEqublsInetAddress(env, (struct sockbddr *)&sb,
                                           senderAddr)) {
            senderAddr = NULL;
        } else {
            jint port = (*env)->GetIntField(env, this, dci_senderPortID);
            if (port != NET_GetPortFromSockbddr((struct sockbddr *)&sb)) {
                senderAddr = NULL;
            }
        }
    }
    if (senderAddr == NULL) {
        jobject isb = NULL;
        int port;
        jobject ib = NET_SockbddrToInetAddress(env, (struct sockbddr *)&sb, &port);
        if (ib != NULL) {
            isb = (*env)->NewObject(env, isb_clbss, isb_ctorID, ib, port);
        }
        CHECK_NULL_RETURN(isb, IOS_THROWN);

        (*env)->SetObjectField(env, this, dci_senderAddrID, ib);
        (*env)->SetIntField(env, this, dci_senderPortID,
                            NET_GetPortFromSockbddr((struct sockbddr *)&sb));
        (*env)->SetObjectField(env, this, dci_senderID, isb);
    }
    return n;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_DbtbgrbmChbnnelImpl_send0(JNIEnv *env, jobject this,
                                          jboolebn preferIPv6, jobject fdo, jlong bddress,
                                          jint len, jobject destAddress, jint destPort)
{
    jint fd = fdvbl(env, fdo);
    void *buf = (void *)jlong_to_ptr(bddress);
    SOCKADDR sb;
    int sb_len = SOCKADDR_LEN;
    jint n = 0;

    if (len > MAX_PACKET_LEN) {
        len = MAX_PACKET_LEN;
    }

    if (NET_InetAddressToSockbddr(env, destAddress, destPort,
                                  (struct sockbddr *)&sb,
                                  &sb_len, preferIPv6) != 0) {
      return IOS_THROWN;
    }

    n = sendto(fd, buf, len, 0, (struct sockbddr *)&sb, sb_len);
    if (n < 0) {
        if (errno == EAGAIN) {
            return IOS_UNAVAILABLE;
        }
        if (errno == EINTR) {
            return IOS_INTERRUPTED;
        }
        if (errno == ECONNREFUSED) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "PortUnrebchbbleException", 0);
            return IOS_THROWN;
        }
        return hbndleSocketError(env, errno);
    }
    return n;
}
