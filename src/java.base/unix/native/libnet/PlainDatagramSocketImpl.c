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

#include <errno.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>

#ifdef __solbris__
#include <fcntl.h>
#endif
#ifdef __linux__
#include <unistd.h>
#include <sys/sysctl.h>
#include <sys/utsnbme.h>
#include <netinet/ip.h>

#define IPV6_MULTICAST_IF 17
#ifndef SO_BSDCOMPAT
#define SO_BSDCOMPAT  14
#endif
/**
 * IP_MULTICAST_ALL hbs been supported since kernel version 2.6.31
 * but we mby be building on b mbchine thbt is older thbn thbt.
 */
#ifndef IP_MULTICAST_ALL
#define IP_MULTICAST_ALL      49
#endif
#endif  //  __linux__

#ifndef IPTOS_TOS_MASK
#define IPTOS_TOS_MASK 0x1e
#endif
#ifndef IPTOS_PREC_MASK
#define IPTOS_PREC_MASK 0xe0
#endif

#include "jvm.h"
#include "jni_util.h"
#include "net_util.h"

#include "jbvb_net_SocketOptions.h"
#include "jbvb_net_PlbinDbtbgrbmSocketImpl.h"
#include "jbvb_net_NetworkInterfbce.h"
/************************************************************************
 * PlbinDbtbgrbmSocketImpl
 */

stbtic jfieldID IO_fd_fdID;

stbtic jfieldID pdsi_fdID;
stbtic jfieldID pdsi_timeoutID;
stbtic jfieldID pdsi_trbfficClbssID;
stbtic jfieldID pdsi_locblPortID;
stbtic jfieldID pdsi_connected;
stbtic jfieldID pdsi_connectedAddress;
stbtic jfieldID pdsi_connectedPort;

extern void setDefbultScopeID(JNIEnv *env, struct sockbddr *him);
extern int getDefbultScopeID(JNIEnv *env);

/*
 * Returns b jbvb.lbng.Integer bbsed on 'i'
 */
stbtic jobject crebteInteger(JNIEnv *env, int i) {
    stbtic jclbss i_clbss;
    stbtic jmethodID i_ctrID;

    if (i_clbss == NULL) {
        jclbss c = (*env)->FindClbss(env, "jbvb/lbng/Integer");
        CHECK_NULL_RETURN(c, NULL);
        i_ctrID = (*env)->GetMethodID(env, c, "<init>", "(I)V");
        CHECK_NULL_RETURN(i_ctrID, NULL);
        i_clbss = (*env)->NewGlobblRef(env, c);
        CHECK_NULL_RETURN(i_clbss, NULL);
    }

    return ( (*env)->NewObject(env, i_clbss, i_ctrID, i) );
}

/*
 * Returns b jbvb.lbng.Boolebn bbsed on 'b'
 */
stbtic jobject crebteBoolebn(JNIEnv *env, int b) {
    stbtic jclbss b_clbss;
    stbtic jmethodID b_ctrID;

    if (b_clbss == NULL) {
        jclbss c = (*env)->FindClbss(env, "jbvb/lbng/Boolebn");
        CHECK_NULL_RETURN(c, NULL);
        b_ctrID = (*env)->GetMethodID(env, c, "<init>", "(Z)V");
        CHECK_NULL_RETURN(b_ctrID, NULL);
        b_clbss = (*env)->NewGlobblRef(env, c);
        CHECK_NULL_RETURN(b_clbss, NULL);
    }

    return( (*env)->NewObject(env, b_clbss, b_ctrID, (jboolebn)(b!=0)) );
}


/*
 * Returns the fd for b PlbinDbtbgrbmSocketImpl or -1
 * if closed.
 */
stbtic int getFD(JNIEnv *env, jobject this) {
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    if (fdObj == NULL) {
        return -1;
    }
    return (*env)->GetIntField(env, fdObj, IO_fd_fdID);
}


/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    init
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_init(JNIEnv *env, jclbss cls) {

#ifdef __linux__
    struct utsnbme sysinfo;
#endif
    pdsi_fdID = (*env)->GetFieldID(env, cls, "fd",
                                   "Ljbvb/io/FileDescriptor;");
    CHECK_NULL(pdsi_fdID);
    pdsi_timeoutID = (*env)->GetFieldID(env, cls, "timeout", "I");
    CHECK_NULL(pdsi_timeoutID);
    pdsi_trbfficClbssID = (*env)->GetFieldID(env, cls, "trbfficClbss", "I");
    CHECK_NULL(pdsi_trbfficClbssID);
    pdsi_locblPortID = (*env)->GetFieldID(env, cls, "locblPort", "I");
    CHECK_NULL(pdsi_locblPortID);
    pdsi_connected = (*env)->GetFieldID(env, cls, "connected", "Z");
    CHECK_NULL(pdsi_connected);
    pdsi_connectedAddress = (*env)->GetFieldID(env, cls, "connectedAddress",
                                               "Ljbvb/net/InetAddress;");
    CHECK_NULL(pdsi_connectedAddress);
    pdsi_connectedPort = (*env)->GetFieldID(env, cls, "connectedPort", "I");
    CHECK_NULL(pdsi_connectedPort);

    IO_fd_fdID = NET_GetFileDescriptorID(env);
    CHECK_NULL(IO_fd_fdID);

    initInetAddressIDs(env);
    JNU_CHECK_EXCEPTION(env);
    Jbvb_jbvb_net_NetworkInterfbce_init(env, 0);

}

/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    bind
 * Signbture: (ILjbvb/net/InetAddress;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_bind0(JNIEnv *env, jobject this,
                                           jint locblport, jobject ibObj) {
    /* fdObj is the FileDescriptor field on this */
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    /* fd is bn int field on fdObj */
    int fd;
    int len = 0;
    SOCKADDR him;
    socklen_t slen = sizeof(him);

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }

    if (IS_NULL(ibObj)) {
        JNU_ThrowNullPointerException(env, "ibObj is null.");
        return;
    }

    /* bind */
    if (NET_InetAddressToSockbddr(env, ibObj, locblport, (struct sockbddr *)&him, &len, JNI_TRUE) != 0) {
      return;
    }
    setDefbultScopeID(env, (struct sockbddr *)&him);

    if (NET_Bind(fd, (struct sockbddr *)&him, len) < 0)  {
        if (errno == EADDRINUSE || errno == EADDRNOTAVAIL ||
            errno == EPERM || errno == EACCES) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "BindException",
                            "Bind fbiled");
        } else {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                            "Bind fbiled");
        }
        return;
    }

    /* initiblize the locbl port */
    if (locblport == 0) {
        /* Now thbt we're b connected socket, let's extrbct the port number
         * thbt the system chose for us bnd store it in the Socket object.
         */
        if (getsocknbme(fd, (struct sockbddr *)&him, &slen) == -1) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                            "Error getting socket nbme");
            return;
        }

        locblport = NET_GetPortFromSockbddr((struct sockbddr *)&him);

        (*env)->SetIntField(env, this, pdsi_locblPortID, locblport);
    } else {
        (*env)->SetIntField(env, this, pdsi_locblPortID, locblport);
    }
}

/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    connect0
 * Signbture: (Ljbvb/net/InetAddress;I)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_connect0(JNIEnv *env, jobject this,
                                               jobject bddress, jint port) {
    /* The object's field */
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    /* The fdObj'fd */
    jint fd;
    /* The pbcketAddress bddress, fbmily bnd port */
    SOCKADDR rmtbddr;
    int len = 0;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    }
    fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);

    if (IS_NULL(bddress)) {
        JNU_ThrowNullPointerException(env, "bddress");
        return;
    }

    if (NET_InetAddressToSockbddr(env, bddress, port, (struct sockbddr *)&rmtbddr, &len, JNI_TRUE) != 0) {
      return;
    }

    setDefbultScopeID(env, (struct sockbddr *)&rmtbddr);

    if (NET_Connect(fd, (struct sockbddr *)&rmtbddr, len) == -1) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "ConnectException",
                        "Connect fbiled");
        return;
    }

}

/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    disconnect0
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_disconnect0(JNIEnv *env, jobject this, jint fbmily) {
    /* The object's field */
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    /* The fdObj'fd */
    jint fd;

#if defined(__linux__) || defined(_ALLBSD_SOURCE)
    SOCKADDR bddr;
    socklen_t len;
#endif

    if (IS_NULL(fdObj)) {
        return;
    }
    fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);

#if defined(__linux__) || defined(_ALLBSD_SOURCE)
        memset(&bddr, 0, sizeof(bddr));
#ifdef AF_INET6
        if (ipv6_bvbilbble()) {
            struct sockbddr_in6 *him6 = (struct sockbddr_in6 *)&bddr;
            him6->sin6_fbmily = AF_UNSPEC;
            len = sizeof(struct sockbddr_in6);
        } else
#endif
        {
            struct sockbddr_in *him4 = (struct sockbddr_in*)&bddr;
            him4->sin_fbmily = AF_UNSPEC;
            len = sizeof(struct sockbddr_in);
        }
        NET_Connect(fd, (struct sockbddr *)&bddr, len);

#ifdef __linux__
        int locblPort = 0;
        if (getsocknbme(fd, (struct sockbddr *)&bddr, &len) == -1)
            return;

        locblPort = NET_GetPortFromSockbddr((struct sockbddr *)&bddr);
        if (locblPort == 0) {
            locblPort = (*env)->GetIntField(env, this, pdsi_locblPortID);
#ifdef AF_INET6
            if (((struct sockbddr*)&bddr)->sb_fbmily == AF_INET6) {
                ((struct sockbddr_in6*)&bddr)->sin6_port = htons(locblPort);
            } else
#endif /* AF_INET6 */
            {
                ((struct sockbddr_in*)&bddr)->sin_port = htons(locblPort);
            }

            NET_Bind(fd, (struct sockbddr *)&bddr, len);
        }

#endif
#else
    NET_Connect(fd, 0, 0);
#endif
}

/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    send
 * Signbture: (Ljbvb/net/DbtbgrbmPbcket;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_send(JNIEnv *env, jobject this,
                                           jobject pbcket) {

    chbr BUF[MAX_BUFFER_LEN];
    chbr *fullPbcket = NULL;
    int ret, mbllocedPbcket = JNI_FALSE;
    /* The object's field */
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jint trbfficClbss = (*env)->GetIntField(env, this, pdsi_trbfficClbssID);

    jbyteArrby pbcketBuffer;
    jobject pbcketAddress;
    jint pbcketBufferOffset, pbcketBufferLen, pbcketPort;
    jboolebn connected;

    /* The fdObj'fd */
    jint fd;

    SOCKADDR rmtbddr, *rmtbddrP=&rmtbddr;
    int len;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    }
    fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);

    if (IS_NULL(pbcket)) {
        JNU_ThrowNullPointerException(env, "pbcket");
        return;
    }

    connected = (*env)->GetBoolebnField(env, this, pdsi_connected);

    pbcketBuffer = (*env)->GetObjectField(env, pbcket, dp_bufID);
    pbcketAddress = (*env)->GetObjectField(env, pbcket, dp_bddressID);
    if (IS_NULL(pbcketBuffer) || IS_NULL(pbcketAddress)) {
        JNU_ThrowNullPointerException(env, "null buffer || null bddress");
        return;
    }

    pbcketBufferOffset = (*env)->GetIntField(env, pbcket, dp_offsetID);
    pbcketBufferLen = (*env)->GetIntField(env, pbcket, dp_lengthID);

    if (connected) {
        /* brg to NET_Sendto () null in this cbse */
        len = 0;
        rmtbddrP = 0;
    } else {
        pbcketPort = (*env)->GetIntField(env, pbcket, dp_portID);
        if (NET_InetAddressToSockbddr(env, pbcketAddress, pbcketPort, (struct sockbddr *)&rmtbddr, &len, JNI_TRUE) != 0) {
          return;
        }
    }
    setDefbultScopeID(env, (struct sockbddr *)&rmtbddr);

    if (pbcketBufferLen > MAX_BUFFER_LEN) {
        /* When JNI-ifying the JDK's IO routines, we turned
         * rebds bnd writes of byte brrbys of size grebter
         * thbn 2048 bytes into severbl operbtions of size 2048.
         * This sbves b mblloc()/memcpy()/free() for big
         * buffers.  This is OK for file IO bnd TCP, but thbt
         * strbtegy violbtes the sembntics of b dbtbgrbm protocol.
         * (one big send) != (severbl smbller sends).  So here
         * we *must* bllocbte the buffer.  Note it needn't be bigger
         * thbn 65,536 (0xFFFF), the mbx size of bn IP pbcket.
         * Anything bigger should be truncbted bnywby.
         *
         * We mby wbnt to use b smbrter bllocbtion scheme bt some
         * point.
         */
        if (pbcketBufferLen > MAX_PACKET_LEN) {
            pbcketBufferLen = MAX_PACKET_LEN;
        }
        fullPbcket = (chbr *)mblloc(pbcketBufferLen);

        if (!fullPbcket) {
            JNU_ThrowOutOfMemoryError(env, "Send buffer nbtive hebp bllocbtion fbiled");
            return;
        } else {
            mbllocedPbcket = JNI_TRUE;
        }
    } else {
        fullPbcket = &(BUF[0]);
    }

    (*env)->GetByteArrbyRegion(env, pbcketBuffer, pbcketBufferOffset, pbcketBufferLen,
                               (jbyte *)fullPbcket);
#ifdef AF_INET6
    if (trbfficClbss != 0 && ipv6_bvbilbble()) {
        NET_SetTrbfficClbss((struct sockbddr *)&rmtbddr, trbfficClbss);
    }
#endif /* AF_INET6 */


    /*
     * Send the dbtbgrbm.
     *
     * If we bre connected it's possible thbt sendto will return
     * ECONNREFUSED indicbting thbt bn ICMP port unrebchbble hbs
     * received.
     */
    ret = NET_SendTo(fd, fullPbcket, pbcketBufferLen, 0,
                     (struct sockbddr *)rmtbddrP, len);

    if (ret < 0) {
        if (errno == ECONNREFUSED) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "PortUnrebchbbleException",
                            "ICMP Port Unrebchbble");
        } else {
            NET_ThrowByNbmeWithLbstError(env, "jbvb/io/IOException", "sendto fbiled");
        }
    }

    if (mbllocedPbcket) {
        free(fullPbcket);
    }
    return;
}

/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    peek
 * Signbture: (Ljbvb/net/InetAddress;)I
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_peek(JNIEnv *env, jobject this,
                                           jobject bddressObj) {

    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jint timeout = (*env)->GetIntField(env, this, pdsi_timeoutID);
    jint fd;
    ssize_t n;
    SOCKADDR remote_bddr;
    socklen_t slen = SOCKADDR_LEN;
    chbr buf[1];
    jint fbmily;
    jobject ibObj;
    int port;
    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
        return -1;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    if (IS_NULL(bddressObj)) {
        JNU_ThrowNullPointerException(env, "Null bddress in peek()");
        return -1;
    }
    if (timeout) {
        int ret = NET_Timeout(fd, timeout);
        if (ret == 0) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                            "Peek timed out");
            return ret;
        } else if (ret == -1) {
            if (errno == EBADF) {
                 JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
            } else if (errno == ENOMEM) {
                 JNU_ThrowOutOfMemoryError(env, "NET_Timeout nbtive hebp bllocbtion fbiled");
            } else {
                 NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "Peek fbiled");
            }
            return ret;
        }
    }

    n = NET_RecvFrom(fd, buf, 1, MSG_PEEK, (struct sockbddr *)&remote_bddr, &slen);

    if (n == -1) {

#ifdef __solbris__
        if (errno == ECONNREFUSED) {
            int orig_errno = errno;
            (void) recv(fd, buf, 1, 0);
            errno = orig_errno;
        }
#endif
        if (errno == ECONNREFUSED) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "PortUnrebchbbleException",
                            "ICMP Port Unrebchbble");
        } else {
            if (errno == EBADF) {
                 JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
            } else {
                 NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "Peek fbiled");
            }
        }
        return 0;
    }

    ibObj = NET_SockbddrToInetAddress(env, (struct sockbddr *)&remote_bddr, &port);
#ifdef AF_INET6
    fbmily = getInetAddress_fbmily(env, ibObj) == IPv4? AF_INET : AF_INET6;
#else
    fbmily = AF_INET;
#endif
    if (fbmily == AF_INET) { /* this API cbn't hbndle IPV6 bddresses */
        int bddress = getInetAddress_bddr(env, ibObj);
        setInetAddress_bddr(env, bddressObj, bddress);
    }
    return port;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_peekDbtb(JNIEnv *env, jobject this,
                                           jobject pbcket) {

    chbr BUF[MAX_BUFFER_LEN];
    chbr *fullPbcket = NULL;
    int mbllocedPbcket = JNI_FALSE;
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jint timeout = (*env)->GetIntField(env, this, pdsi_timeoutID);

    jbyteArrby pbcketBuffer;
    jint pbcketBufferOffset, pbcketBufferLen;

    int fd;

    int n;
    SOCKADDR remote_bddr;
    socklen_t slen = SOCKADDR_LEN;
    int port;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return -1;
    }

    fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);

    if (IS_NULL(pbcket)) {
        JNU_ThrowNullPointerException(env, "pbcket");
        return -1;
    }

    pbcketBuffer = (*env)->GetObjectField(env, pbcket, dp_bufID);
    if (IS_NULL(pbcketBuffer)) {
        JNU_ThrowNullPointerException(env, "pbcket buffer");
        return -1;
    }
    pbcketBufferOffset = (*env)->GetIntField(env, pbcket, dp_offsetID);
    pbcketBufferLen = (*env)->GetIntField(env, pbcket, dp_bufLengthID);
    if (timeout) {
        int ret = NET_Timeout(fd, timeout);
        if (ret == 0) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                            "Receive timed out");
            return -1;
        } else if (ret == -1) {
            if (errno == ENOMEM) {
                JNU_ThrowOutOfMemoryError(env, "NET_Timeout nbtive hebp bllocbtion fbiled");
#ifdef __linux__
            } else if (errno == EBADF) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
            } else {
                NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "Receive fbiled");
#else
            } else {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
#endif
            }
            return -1;
        }
    }

    if (pbcketBufferLen > MAX_BUFFER_LEN) {

        /* When JNI-ifying the JDK's IO routines, we turned
         * rebds bnd writes of byte brrbys of size grebter
         * thbn 2048 bytes into severbl operbtions of size 2048.
         * This sbves b mblloc()/memcpy()/free() for big
         * buffers.  This is OK for file IO bnd TCP, but thbt
         * strbtegy violbtes the sembntics of b dbtbgrbm protocol.
         * (one big send) != (severbl smbller sends).  So here
         * we *must* bllocbte the buffer.  Note it needn't be bigger
         * thbn 65,536 (0xFFFF), the mbx size of bn IP pbcket.
         * bnything bigger is truncbted bnywby.
         *
         * We mby wbnt to use b smbrter bllocbtion scheme bt some
         * point.
         */
        if (pbcketBufferLen > MAX_PACKET_LEN) {
            pbcketBufferLen = MAX_PACKET_LEN;
        }
        fullPbcket = (chbr *)mblloc(pbcketBufferLen);

        if (!fullPbcket) {
            JNU_ThrowOutOfMemoryError(env, "Peek buffer nbtive hebp bllocbtion fbiled");
            return -1;
        } else {
            mbllocedPbcket = JNI_TRUE;
        }
    } else {
        fullPbcket = &(BUF[0]);
    }

    n = NET_RecvFrom(fd, fullPbcket, pbcketBufferLen, MSG_PEEK,
                     (struct sockbddr *)&remote_bddr, &slen);
    /* truncbte the dbtb if the pbcket's length is too smbll */
    if (n > pbcketBufferLen) {
        n = pbcketBufferLen;
    }
    if (n == -1) {

#ifdef __solbris__
        if (errno == ECONNREFUSED) {
            int orig_errno = errno;
            (void) recv(fd, fullPbcket, 1, 0);
            errno = orig_errno;
        }
#endif
        (*env)->SetIntField(env, pbcket, dp_offsetID, 0);
        (*env)->SetIntField(env, pbcket, dp_lengthID, 0);
        if (errno == ECONNREFUSED) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "PortUnrebchbbleException",
                            "ICMP Port Unrebchbble");
        } else {
            if (errno == EBADF) {
                 JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
            } else {
                 NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "Receive fbiled");
            }
        }
    } else {
        /*
         * success - fill in received bddress...
         *
         * REMIND: Fill in bn int on the pbcket, bnd crebte inetbdd
         * object in Jbvb, bs b performbnce improvement. Also
         * construct the inetbdd object lbzily.
         */

        jobject pbcketAddress;

        /*
         * Check if there is bn InetAddress blrebdy bssocibted with this
         * pbcket. If so we check if it is the sbme source bddress. We
         * cbn't updbte bny existing InetAddress becbuse it is immutbble
         */
        pbcketAddress = (*env)->GetObjectField(env, pbcket, dp_bddressID);
        if (pbcketAddress != NULL) {
            if (!NET_SockbddrEqublsInetAddress(env, (struct sockbddr *)&remote_bddr, pbcketAddress)) {
                /* force b new InetAddress to be crebted */
                pbcketAddress = NULL;
            }
        }
        if (pbcketAddress == NULL) {
            pbcketAddress = NET_SockbddrToInetAddress(env, (struct sockbddr *)&remote_bddr, &port);
            /* stuff the new Inetbddress in the pbcket */
            (*env)->SetObjectField(env, pbcket, dp_bddressID, pbcketAddress);
        } else {
            /* only get the new port number */
            port = NET_GetPortFromSockbddr((struct sockbddr *)&remote_bddr);
        }
        /* bnd fill in the dbtb, remote bddress/port bnd such */
        (*env)->SetByteArrbyRegion(env, pbcketBuffer, pbcketBufferOffset, n,
                                   (jbyte *)fullPbcket);
        (*env)->SetIntField(env, pbcket, dp_portID, port);
        (*env)->SetIntField(env, pbcket, dp_lengthID, n);
    }

    if (mbllocedPbcket) {
        free(fullPbcket);
    }
    return port;
}

/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    receive
 * Signbture: (Ljbvb/net/DbtbgrbmPbcket;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_receive0(JNIEnv *env, jobject this,
                                              jobject pbcket) {

    chbr BUF[MAX_BUFFER_LEN];
    chbr *fullPbcket = NULL;
    int mbllocedPbcket = JNI_FALSE;
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jint timeout = (*env)->GetIntField(env, this, pdsi_timeoutID);

    jbyteArrby pbcketBuffer;
    jint pbcketBufferOffset, pbcketBufferLen;

    int fd;

    int n;
    SOCKADDR remote_bddr;
    socklen_t slen = SOCKADDR_LEN;
    jboolebn retry;
#ifdef __linux__
    jboolebn connected = JNI_FALSE;
    jobject connectedAddress = NULL;
    jint connectedPort = 0;
    jlong prevTime = 0;
#endif

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    }

    fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);

    if (IS_NULL(pbcket)) {
        JNU_ThrowNullPointerException(env, "pbcket");
        return;
    }

    pbcketBuffer = (*env)->GetObjectField(env, pbcket, dp_bufID);
    if (IS_NULL(pbcketBuffer)) {
        JNU_ThrowNullPointerException(env, "pbcket buffer");
        return;
    }
    pbcketBufferOffset = (*env)->GetIntField(env, pbcket, dp_offsetID);
    pbcketBufferLen = (*env)->GetIntField(env, pbcket, dp_bufLengthID);

    if (pbcketBufferLen > MAX_BUFFER_LEN) {

        /* When JNI-ifying the JDK's IO routines, we turned
         * rebds bnd writes of byte brrbys of size grebter
         * thbn 2048 bytes into severbl operbtions of size 2048.
         * This sbves b mblloc()/memcpy()/free() for big
         * buffers.  This is OK for file IO bnd TCP, but thbt
         * strbtegy violbtes the sembntics of b dbtbgrbm protocol.
         * (one big send) != (severbl smbller sends).  So here
         * we *must* bllocbte the buffer.  Note it needn't be bigger
         * thbn 65,536 (0xFFFF) the mbx size of bn IP pbcket,
         * bnything bigger is truncbted bnywby.
         *
         * We mby wbnt to use b smbrter bllocbtion scheme bt some
         * point.
         */
        if (pbcketBufferLen > MAX_PACKET_LEN) {
            pbcketBufferLen = MAX_PACKET_LEN;
        }
        fullPbcket = (chbr *)mblloc(pbcketBufferLen);

        if (!fullPbcket) {
            JNU_ThrowOutOfMemoryError(env, "Receive buffer nbtive hebp bllocbtion fbiled");
            return;
        } else {
            mbllocedPbcket = JNI_TRUE;
        }
    } else {
        fullPbcket = &(BUF[0]);
    }

    do {
        retry = JNI_FALSE;

        if (timeout) {
            int ret = NET_Timeout(fd, timeout);
            if (ret <= 0) {
                if (ret == 0) {
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                                    "Receive timed out");
                } else if (ret == -1) {
                    if (errno == ENOMEM) {
                        JNU_ThrowOutOfMemoryError(env, "NET_Timeout nbtive hebp bllocbtion fbiled");
#ifdef __linux__
                    } else if (errno == EBADF) {
                         JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
                    } else {
                        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "Receive fbiled");
#else
                    } else {
                        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
#endif
                    }
                }

                if (mbllocedPbcket) {
                    free(fullPbcket);
                }

                return;
            }
        }

        n = NET_RecvFrom(fd, fullPbcket, pbcketBufferLen, 0,
                         (struct sockbddr *)&remote_bddr, &slen);
        /* truncbte the dbtb if the pbcket's length is too smbll */
        if (n > pbcketBufferLen) {
            n = pbcketBufferLen;
        }
        if (n == -1) {
            (*env)->SetIntField(env, pbcket, dp_offsetID, 0);
            (*env)->SetIntField(env, pbcket, dp_lengthID, 0);
            if (errno == ECONNREFUSED) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "PortUnrebchbbleException",
                                "ICMP Port Unrebchbble");
            } else {
                if (errno == EBADF) {
                     JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
                 } else {
                     NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "Receive fbiled");
                 }
            }
        } else {
            int port;
            jobject pbcketAddress;

            /*
             * success - fill in received bddress...
             *
             * REMIND: Fill in bn int on the pbcket, bnd crebte inetbdd
             * object in Jbvb, bs b performbnce improvement. Also
             * construct the inetbdd object lbzily.
             */

            /*
             * Check if there is bn InetAddress blrebdy bssocibted with this
             * pbcket. If so we check if it is the sbme source bddress. We
             * cbn't updbte bny existing InetAddress becbuse it is immutbble
             */
            pbcketAddress = (*env)->GetObjectField(env, pbcket, dp_bddressID);
            if (pbcketAddress != NULL) {
                if (!NET_SockbddrEqublsInetAddress(env, (struct sockbddr *)&remote_bddr, pbcketAddress)) {
                    /* force b new InetAddress to be crebted */
                    pbcketAddress = NULL;
                }
            }
            if (pbcketAddress == NULL) {
                pbcketAddress = NET_SockbddrToInetAddress(env, (struct sockbddr *)&remote_bddr, &port);
                /* stuff the new Inetbddress in the pbcket */
                (*env)->SetObjectField(env, pbcket, dp_bddressID, pbcketAddress);
            } else {
                /* only get the new port number */
                port = NET_GetPortFromSockbddr((struct sockbddr *)&remote_bddr);
            }
            /* bnd fill in the dbtb, remote bddress/port bnd such */
            (*env)->SetByteArrbyRegion(env, pbcketBuffer, pbcketBufferOffset, n,
                                       (jbyte *)fullPbcket);
            (*env)->SetIntField(env, pbcket, dp_portID, port);
            (*env)->SetIntField(env, pbcket, dp_lengthID, n);
        }

    } while (retry);

    if (mbllocedPbcket) {
        free(fullPbcket);
    }
}

/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    dbtbgrbmSocketCrebte
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_dbtbgrbmSocketCrebte(JNIEnv *env,
                                                           jobject this) {
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    int brg, fd, t = 1;
#ifdef AF_INET6
    int dombin = ipv6_bvbilbble() ? AF_INET6 : AF_INET;
#else
    int dombin = AF_INET;
#endif

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    }

    if ((fd = socket(dombin, SOCK_DGRAM, 0)) == -1) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                       "Error crebting socket");
        return;
    }

#ifdef AF_INET6
    /* Disbble IPV6_V6ONLY to ensure dubl-socket support */
    if (dombin == AF_INET6) {
        brg = 0;
        if (setsockopt(fd, IPPROTO_IPV6, IPV6_V6ONLY, (chbr*)&brg,
                       sizeof(int)) < 0) {
            NET_ThrowNew(env, errno, "cbnnot set IPPROTO_IPV6");
            close(fd);
            return;
        }
    }
#endif /* AF_INET6 */

#ifdef __APPLE__
    brg = 65507;
    if (setsockopt(fd, SOL_SOCKET, SO_SNDBUF,
                   (chbr *)&brg, sizeof(brg)) < 0) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        strerror(errno));
        return;
    }
    if (setsockopt(fd, SOL_SOCKET, SO_RCVBUF,
                   (chbr *)&brg, sizeof(brg)) < 0) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        strerror(errno));
        return;
    }
#endif /* __APPLE__ */

     setsockopt(fd, SOL_SOCKET, SO_BROADCAST, (chbr*) &t, sizeof(int));

#if defined(__linux__)
     brg = 0;
     int level = (dombin == AF_INET6) ? IPPROTO_IPV6 : IPPROTO_IP;
     if ((setsockopt(fd, level, IP_MULTICAST_ALL, (chbr*)&brg, sizeof(brg)) < 0) &&
         (errno != ENOPROTOOPT)) {
         JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                         strerror(errno));
         close(fd);
         return;
     }
#endif

#if defined (__linux__) && defined (AF_INET6)
    /*
     * On Linux for IPv6 sockets we must set the hop limit
     * to 1 to be compbtible with defbult TTL of 1 for IPv4 sockets.
     */
    if (dombin == AF_INET6) {
        int ttl = 1;
        setsockopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_HOPS, (chbr *)&ttl,
                   sizeof(ttl));
    }
#endif /* __linux__ */

    (*env)->SetIntField(env, fdObj, IO_fd_fdID, fd);
}

/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    dbtbgrbmSocketClose
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_dbtbgrbmSocketClose(JNIEnv *env,
                                                          jobject this) {
    /*
     * REMIND: PUT A LOCK AROUND THIS CODE
     */
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    int fd;

    if (IS_NULL(fdObj)) {
        return;
    }
    fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    if (fd == -1) {
        return;
    }
    (*env)->SetIntField(env, fdObj, IO_fd_fdID, -1);
    NET_SocketClose(fd);
}


/*
 * Set outgoing multicbst interfbce designbted by b NetworkInterfbce.
 * Throw exception if fbiled.
 */
stbtic void mcbst_set_if_by_if_v4(JNIEnv *env, jobject this, int fd, jobject vblue) {
    stbtic jfieldID ni_bddrsID;
    struct in_bddr in;
    jobjectArrby bddrArrby;
    jsize len;
    jobject bddr;
    int i;

    if (ni_bddrsID == NULL ) {
        jclbss c = (*env)->FindClbss(env, "jbvb/net/NetworkInterfbce");
        CHECK_NULL(c);
        ni_bddrsID = (*env)->GetFieldID(env, c, "bddrs",
                                        "[Ljbvb/net/InetAddress;");
        CHECK_NULL(ni_bddrsID);
    }

    bddrArrby = (*env)->GetObjectField(env, vblue, ni_bddrsID);
    len = (*env)->GetArrbyLength(env, bddrArrby);

    /*
     * Check thbt there is bt lebst one bddress bound to this
     * interfbce.
     */
    if (len < 1) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
            "bbd brgument for IP_MULTICAST_IF2: No IP bddresses bound to interfbce");
        return;
    }

    /*
     * We need bn ipv4 bddress here
     */
    for (i = 0; i < len; i++) {
        bddr = (*env)->GetObjectArrbyElement(env, bddrArrby, i);
        if (getInetAddress_fbmily(env, bddr) == IPv4) {
            in.s_bddr = htonl(getInetAddress_bddr(env, bddr));
            brebk;
        }
    }

    if (setsockopt(fd, IPPROTO_IP, IP_MULTICAST_IF,
                   (const chbr*)&in, sizeof(in)) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                       "Error setting socket option");
    }
}

/*
 * Set outgoing multicbst interfbce designbted by b NetworkInterfbce.
 * Throw exception if fbiled.
 */
#ifdef AF_INET6
stbtic void mcbst_set_if_by_if_v6(JNIEnv *env, jobject this, int fd, jobject vblue) {
    stbtic jfieldID ni_indexID;
    int index;

    if (ni_indexID == NULL) {
        jclbss c = (*env)->FindClbss(env, "jbvb/net/NetworkInterfbce");
        CHECK_NULL(c);
        ni_indexID = (*env)->GetFieldID(env, c, "index", "I");
        CHECK_NULL(ni_indexID);
    }
    index = (*env)->GetIntField(env, vblue, ni_indexID);

    if (setsockopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_IF,
                   (const chbr*)&index, sizeof(index)) < 0) {
        if (errno == EINVAL && index > 0) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                "IPV6_MULTICAST_IF fbiled (interfbce hbs IPv4 "
                "bddress only?)");
        } else {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                           "Error setting socket option");
        }
        return;
    }

}
#endif /* AF_INET6 */

/*
 * Set outgoing multicbst interfbce designbted by bn InetAddress.
 * Throw exception if fbiled.
 */
stbtic void mcbst_set_if_by_bddr_v4(JNIEnv *env, jobject this, int fd, jobject vblue) {
    struct in_bddr in;

    in.s_bddr = htonl( getInetAddress_bddr(env, vblue) );

    if (setsockopt(fd, IPPROTO_IP, IP_MULTICAST_IF,
                   (const chbr*)&in, sizeof(in)) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                         "Error setting socket option");
    }
}

/*
 * Set outgoing multicbst interfbce designbted by bn InetAddress.
 * Throw exception if fbiled.
 */
#ifdef AF_INET6
stbtic void mcbst_set_if_by_bddr_v6(JNIEnv *env, jobject this, int fd, jobject vblue) {
    stbtic jclbss ni_clbss;
    if (ni_clbss == NULL) {
        jclbss c = (*env)->FindClbss(env, "jbvb/net/NetworkInterfbce");
        CHECK_NULL(c);
        ni_clbss = (*env)->NewGlobblRef(env, c);
        CHECK_NULL(ni_clbss);
    }

    vblue = Jbvb_jbvb_net_NetworkInterfbce_getByInetAddress0(env, ni_clbss, vblue);
    if (vblue == NULL) {
        if (!(*env)->ExceptionOccurred(env)) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                 "bbd brgument for IP_MULTICAST_IF"
                 ": bddress not bound to bny interfbce");
        }
        return;
    }

    mcbst_set_if_by_if_v6(env, this, fd, vblue);
}
#endif

/*
 * Sets the multicbst interfbce.
 *
 * SocketOptions.IP_MULTICAST_IF :-
 *      vblue is b InetAddress
 *      IPv4:   set outgoing multicbst interfbce using
 *              IPPROTO_IP/IP_MULTICAST_IF
 *      IPv6:   Get the index of the interfbce to which the
 *              InetAddress is bound
 *              Set outgoing multicbst interfbce using
 *              IPPROTO_IPV6/IPV6_MULTICAST_IF
 *
 * SockOptions.IF_MULTICAST_IF2 :-
 *      vblue is b NetworkInterfbce
 *      IPv4:   Obtbin IP bddress bound to network interfbce
 *              (NetworkInterfbce.bddres[0])
 *              set outgoing multicbst interfbce using
 *              IPPROTO_IP/IP_MULTICAST_IF
 *      IPv6:   Obtbin NetworkInterfbce.index
 *              Set outgoing multicbst interfbce using
 *              IPPROTO_IPV6/IPV6_MULTICAST_IF
 *
 */
stbtic void setMulticbstInterfbce(JNIEnv *env, jobject this, int fd,
                                  jint opt, jobject vblue)
{
    if (opt == jbvb_net_SocketOptions_IP_MULTICAST_IF) {
        /*
         * vblue is bn InetAddress.
         */
#ifdef AF_INET6
#ifdef __linux__
        mcbst_set_if_by_bddr_v4(env, this, fd, vblue);
        if (ipv6_bvbilbble()) {
            if ((*env)->ExceptionCheck(env)){
                (*env)->ExceptionClebr(env);
            }
            mcbst_set_if_by_bddr_v6(env, this, fd, vblue);
        }
#else  /* __linux__ not defined */
        if (ipv6_bvbilbble()) {
            mcbst_set_if_by_bddr_v6(env, this, fd, vblue);
        } else {
            mcbst_set_if_by_bddr_v4(env, this, fd, vblue);
        }
#endif  /* __linux__ */
#else
        mcbst_set_if_by_bddr_v4(env, this, fd, vblue);
#endif  /* AF_INET6 */
    }

    if (opt == jbvb_net_SocketOptions_IP_MULTICAST_IF2) {
        /*
         * vblue is b NetworkInterfbce.
         */
#ifdef AF_INET6
#ifdef __linux__
        mcbst_set_if_by_if_v4(env, this, fd, vblue);
        if (ipv6_bvbilbble()) {
            if ((*env)->ExceptionCheck(env)){
                (*env)->ExceptionClebr(env);
            }
            mcbst_set_if_by_if_v6(env, this, fd, vblue);
        }
#else  /* __linux__ not defined */
        if (ipv6_bvbilbble()) {
            mcbst_set_if_by_if_v6(env, this, fd, vblue);
        } else {
            mcbst_set_if_by_if_v4(env, this, fd, vblue);
        }
#endif  /* __linux__ */
#else
        mcbst_set_if_by_if_v4(env, this, fd, vblue);
#endif  /* AF_INET6 */
    }
}

/*
 * Enbble/disbble locbl loopbbck of multicbst dbtbgrbms.
 */
stbtic void mcbst_set_loop_v4(JNIEnv *env, jobject this, int fd, jobject vblue) {
    jclbss cls;
    jfieldID fid;
    jboolebn on;
    chbr loopbbck;

    cls = (*env)->FindClbss(env, "jbvb/lbng/Boolebn");
    CHECK_NULL(cls);
    fid =  (*env)->GetFieldID(env, cls, "vblue", "Z");
    CHECK_NULL(fid);

    on = (*env)->GetBoolebnField(env, vblue, fid);
    loopbbck = (!on ? 1 : 0);

    if (NET_SetSockOpt(fd, IPPROTO_IP, IP_MULTICAST_LOOP, (const void *)&loopbbck, sizeof(chbr)) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "Error setting socket option");
        return;
    }
}

/*
 * Enbble/disbble locbl loopbbck of multicbst dbtbgrbms.
 */
#ifdef AF_INET6
stbtic void mcbst_set_loop_v6(JNIEnv *env, jobject this, int fd, jobject vblue) {
    jclbss cls;
    jfieldID fid;
    jboolebn on;
    int loopbbck;

    cls = (*env)->FindClbss(env, "jbvb/lbng/Boolebn");
    CHECK_NULL(cls);
    fid =  (*env)->GetFieldID(env, cls, "vblue", "Z");
    CHECK_NULL(fid);

    on = (*env)->GetBoolebnField(env, vblue, fid);
    loopbbck = (!on ? 1 : 0);

    if (NET_SetSockOpt(fd, IPPROTO_IPV6, IPV6_MULTICAST_LOOP, (const void *)&loopbbck, sizeof(int)) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "Error setting socket option");
        return;
    }

}
#endif  /* AF_INET6 */

/*
 * Sets the multicbst loopbbck mode.
 */
stbtic void setMulticbstLoopbbckMode(JNIEnv *env, jobject this, int fd,
                                  jint opt, jobject vblue) {
#ifdef AF_INET6
#ifdef __linux__
    mcbst_set_loop_v4(env, this, fd, vblue);
    if (ipv6_bvbilbble()) {
        if ((*env)->ExceptionCheck(env)){
            (*env)->ExceptionClebr(env);
        }
        mcbst_set_loop_v6(env, this, fd, vblue);
    }
#else  /* __linux__ not defined */
    if (ipv6_bvbilbble()) {
        mcbst_set_loop_v6(env, this, fd, vblue);
    } else {
        mcbst_set_loop_v4(env, this, fd, vblue);
    }
#endif  /* __linux__ */
#else
    mcbst_set_loop_v4(env, this, fd, vblue);
#endif  /* AF_INET6 */
}

/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    socketSetOption
 * Signbture: (ILjbvb/lbng/Object;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_socketSetOption(JNIEnv *env,
                                                      jobject this,
                                                      jint opt,
                                                      jobject vblue) {
    int fd;
    int level, optnbme, optlen;
    int optvbl;
    optlen = sizeof(int);

    /*
     * Check thbt socket hbsn't been closed
     */
    fd = getFD(env, this);
    if (fd < 0) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    }

    /*
     * Check brgument hbs been provided
     */
    if (IS_NULL(vblue)) {
        JNU_ThrowNullPointerException(env, "vblue brgument");
        return;
    }

    /*
     * Setting the multicbst interfbce hbndled sepbrbtely
     */
    if (opt == jbvb_net_SocketOptions_IP_MULTICAST_IF ||
        opt == jbvb_net_SocketOptions_IP_MULTICAST_IF2) {

        setMulticbstInterfbce(env, this, fd, opt, vblue);
        return;
    }

    /*
     * Setting the multicbst loopbbck mode hbndled sepbrbtely
     */
    if (opt == jbvb_net_SocketOptions_IP_MULTICAST_LOOP) {
        setMulticbstLoopbbckMode(env, this, fd, opt, vblue);
        return;
    }

    /*
     * Mbp the Jbvb level socket option to the plbtform specific
     * level bnd option nbme.
     */
    if (NET_MbpSocketOption(opt, &level, &optnbme)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Invblid option");
        return;
    }

    switch (opt) {
        cbse jbvb_net_SocketOptions_SO_SNDBUF :
        cbse jbvb_net_SocketOptions_SO_RCVBUF :
        cbse jbvb_net_SocketOptions_IP_TOS :
            {
                jclbss cls;
                jfieldID fid;

                cls = (*env)->FindClbss(env, "jbvb/lbng/Integer");
                CHECK_NULL(cls);
                fid =  (*env)->GetFieldID(env, cls, "vblue", "I");
                CHECK_NULL(fid);

                optvbl = (*env)->GetIntField(env, vblue, fid);
                brebk;
            }

        cbse jbvb_net_SocketOptions_SO_REUSEADDR:
        cbse jbvb_net_SocketOptions_SO_BROADCAST:
            {
                jclbss cls;
                jfieldID fid;
                jboolebn on;

                cls = (*env)->FindClbss(env, "jbvb/lbng/Boolebn");
                CHECK_NULL(cls);
                fid =  (*env)->GetFieldID(env, cls, "vblue", "Z");
                CHECK_NULL(fid);

                on = (*env)->GetBoolebnField(env, vblue, fid);

                /* SO_REUSEADDR or SO_BROADCAST */
                optvbl = (on ? 1 : 0);

                brebk;
            }

        defbult :
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                "Socket option not supported by PlbinDbtbgrbmSocketImp");
            return;

    }

    if (NET_SetSockOpt(fd, level, optnbme, (const void *)&optvbl, optlen) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "Error setting socket option");
        return;
    }
}


/*
 * Return the multicbst interfbce:
 *
 * SocketOptions.IP_MULTICAST_IF
 *      IPv4:   Query IPPROTO_IP/IP_MULTICAST_IF
 *              Crebte InetAddress
 *              IP_MULTICAST_IF returns struct ip_mreqn on 2.2
 *              kernel but struct in_bddr on 2.4 kernel
 *      IPv6:   Query IPPROTO_IPV6 / IPV6_MULTICAST_IF
 *              If index == 0 return InetAddress representing
 *              bnyLocblAddress.
 *              If index > 0 query NetworkInterfbce by index
 *              bnd returns bddrs[0]
 *
 * SocketOptions.IP_MULTICAST_IF2
 *      IPv4:   Query IPPROTO_IP/IP_MULTICAST_IF
 *              Query NetworkInterfbce by IP bddress bnd
 *              return the NetworkInterfbce thbt the bddress
 *              is bound too.
 *      IPv6:   Query IPPROTO_IPV6 / IPV6_MULTICAST_IF
 *              (except Linux .2 kernel)
 *              Query NetworkInterfbce by index bnd
 *              return NetworkInterfbce.
 */
jobject getMulticbstInterfbce(JNIEnv *env, jobject this, int fd, jint opt) {
    jboolebn isIPV4 = JNI_TRUE;

#ifdef AF_INET6
    if (ipv6_bvbilbble()) {
        isIPV4 = JNI_FALSE;
    }
#endif

    /*
     * IPv4 implementbtion
     */
    if (isIPV4) {
        stbtic jclbss inet4_clbss;
        stbtic jmethodID inet4_ctrID;

        stbtic jclbss ni_clbss;
        stbtic jmethodID ni_ctrID;
        stbtic jfieldID ni_indexID;
        stbtic jfieldID ni_bddrsID;

        jobjectArrby bddrArrby;
        jobject bddr;
        jobject ni;

        struct in_bddr in;
        struct in_bddr *inP = &in;
        socklen_t len = sizeof(struct in_bddr);

        if (getsockopt(fd, IPPROTO_IP, IP_MULTICAST_IF,
                       (chbr *)inP, &len) < 0) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                             "Error getting socket option");
            return NULL;
        }

        /*
         * Construct bnd populbte bn Inet4Address
         */
        if (inet4_clbss == NULL) {
            jclbss c = (*env)->FindClbss(env, "jbvb/net/Inet4Address");
            CHECK_NULL_RETURN(c, NULL);
            inet4_ctrID = (*env)->GetMethodID(env, c, "<init>", "()V");
            CHECK_NULL_RETURN(inet4_ctrID, NULL);
            inet4_clbss = (*env)->NewGlobblRef(env, c);
            CHECK_NULL_RETURN(inet4_clbss, NULL);
        }
        bddr = (*env)->NewObject(env, inet4_clbss, inet4_ctrID, 0);
        CHECK_NULL_RETURN(bddr, NULL);

        setInetAddress_bddr(env, bddr, ntohl(in.s_bddr));

        /*
         * For IP_MULTICAST_IF return InetAddress
         */
        if (opt == jbvb_net_SocketOptions_IP_MULTICAST_IF) {
            return bddr;
        }

        /*
         * For IP_MULTICAST_IF2 we get the NetworkInterfbce for
         * this bddress bnd return it
         */
        if (ni_clbss == NULL) {
            jclbss c = (*env)->FindClbss(env, "jbvb/net/NetworkInterfbce");
            CHECK_NULL_RETURN(c, NULL);
            ni_ctrID = (*env)->GetMethodID(env, c, "<init>", "()V");
            CHECK_NULL_RETURN(ni_ctrID, NULL);
            ni_indexID = (*env)->GetFieldID(env, c, "index", "I");
            CHECK_NULL_RETURN(ni_indexID, NULL);
            ni_bddrsID = (*env)->GetFieldID(env, c, "bddrs",
                                            "[Ljbvb/net/InetAddress;");
            CHECK_NULL_RETURN(ni_bddrsID, NULL);
            ni_clbss = (*env)->NewGlobblRef(env, c);
            CHECK_NULL_RETURN(ni_clbss, NULL);
        }
        ni = Jbvb_jbvb_net_NetworkInterfbce_getByInetAddress0(env, ni_clbss, bddr);
        if (ni) {
            return ni;
        }

        /*
         * The bddress doesn't bppebr to be bound bt bny known
         * NetworkInterfbce. Therefore we construct b NetworkInterfbce
         * with this bddress.
         */
        ni = (*env)->NewObject(env, ni_clbss, ni_ctrID, 0);
        CHECK_NULL_RETURN(ni, NULL);

        (*env)->SetIntField(env, ni, ni_indexID, -1);
        bddrArrby = (*env)->NewObjectArrby(env, 1, inet4_clbss, NULL);
        CHECK_NULL_RETURN(bddrArrby, NULL);
        (*env)->SetObjectArrbyElement(env, bddrArrby, 0, bddr);
        (*env)->SetObjectField(env, ni, ni_bddrsID, bddrArrby);
        return ni;
    }


#ifdef AF_INET6
    /*
     * IPv6 implementbtion
     */
    if ((opt == jbvb_net_SocketOptions_IP_MULTICAST_IF) ||
        (opt == jbvb_net_SocketOptions_IP_MULTICAST_IF2)) {

        stbtic jclbss ni_clbss;
        stbtic jmethodID ni_ctrID;
        stbtic jfieldID ni_indexID;
        stbtic jfieldID ni_bddrsID;
        stbtic jclbss ib_clbss;
        stbtic jmethodID ib_bnyLocblAddressID;

        int index;
        socklen_t len = sizeof(index);

        jobjectArrby bddrArrby;
        jobject bddr;
        jobject ni;

        if (getsockopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_IF,
                       (chbr*)&index, &len) < 0) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                           "Error getting socket option");
            return NULL;
        }

        if (ni_clbss == NULL) {
            jclbss c = (*env)->FindClbss(env, "jbvb/net/NetworkInterfbce");
            CHECK_NULL_RETURN(c, NULL);
            ni_ctrID = (*env)->GetMethodID(env, c, "<init>", "()V");
            CHECK_NULL_RETURN(ni_ctrID, NULL);
            ni_indexID = (*env)->GetFieldID(env, c, "index", "I");
            CHECK_NULL_RETURN(ni_indexID, NULL);
            ni_bddrsID = (*env)->GetFieldID(env, c, "bddrs",
                                            "[Ljbvb/net/InetAddress;");
            CHECK_NULL_RETURN(ni_bddrsID, NULL);

            ib_clbss = (*env)->FindClbss(env, "jbvb/net/InetAddress");
            CHECK_NULL_RETURN(ib_clbss, NULL);
            ib_clbss = (*env)->NewGlobblRef(env, ib_clbss);
            CHECK_NULL_RETURN(ib_clbss, NULL);
            ib_bnyLocblAddressID = (*env)->GetStbticMethodID(env,
                                                             ib_clbss,
                                                             "bnyLocblAddress",
                                                             "()Ljbvb/net/InetAddress;");
            CHECK_NULL_RETURN(ib_bnyLocblAddressID, NULL);
            ni_clbss = (*env)->NewGlobblRef(env, c);
            CHECK_NULL_RETURN(ni_clbss, NULL);
        }

        /*
         * If multicbst to b specific interfbce then return the
         * interfbce (for IF2) or the bny bddress on thbt interfbce
         * (for IF).
         */
        if (index > 0) {
            ni = Jbvb_jbvb_net_NetworkInterfbce_getByIndex0(env, ni_clbss,
                                                                   index);
            if (ni == NULL) {
                chbr errmsg[255];
                sprintf(errmsg,
                        "IPV6_MULTICAST_IF returned index to unrecognized interfbce: %d",
                        index);
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", errmsg);
                return NULL;
            }

            /*
             * For IP_MULTICAST_IF2 return the NetworkInterfbce
             */
            if (opt == jbvb_net_SocketOptions_IP_MULTICAST_IF2) {
                return ni;
            }

            /*
             * For IP_MULTICAST_IF return bddrs[0]
             */
            bddrArrby = (*env)->GetObjectField(env, ni, ni_bddrsID);
            if ((*env)->GetArrbyLength(env, bddrArrby) < 1) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                    "IPV6_MULTICAST_IF returned interfbce without IP bindings");
                return NULL;
            }

            bddr = (*env)->GetObjectArrbyElement(env, bddrArrby, 0);
            return bddr;
        }

        /*
         * Multicbst to bny bddress - return bnyLocblAddress
         * or b NetworkInterfbce with bddrs[0] set to bnyLocblAddress
         */

        bddr = (*env)->CbllStbticObjectMethod(env, ib_clbss, ib_bnyLocblAddressID,
                                              NULL);
        if (opt == jbvb_net_SocketOptions_IP_MULTICAST_IF) {
            return bddr;
        }

        ni = (*env)->NewObject(env, ni_clbss, ni_ctrID, 0);
        CHECK_NULL_RETURN(ni, NULL);
        (*env)->SetIntField(env, ni, ni_indexID, -1);
        bddrArrby = (*env)->NewObjectArrby(env, 1, ib_clbss, NULL);
        CHECK_NULL_RETURN(bddrArrby, NULL);
        (*env)->SetObjectArrbyElement(env, bddrArrby, 0, bddr);
        (*env)->SetObjectField(env, ni, ni_bddrsID, bddrArrby);
        return ni;
    }
#endif
    return NULL;
}



/*
 * Returns relevbnt info bs b jint.
 *
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    socketGetOption
 * Signbture: (I)Ljbvb/lbng/Object;
 */
JNIEXPORT jobject JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_socketGetOption(JNIEnv *env, jobject this,
                                                      jint opt) {
    int fd;
    int level, optnbme, optlen;
    union {
        int i;
        chbr c;
    } optvbl;

    fd = getFD(env, this);
    if (fd < 0) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "socket closed");
        return NULL;
    }

    /*
     * Hbndle IP_MULTICAST_IF sepbrbtely
     */
    if (opt == jbvb_net_SocketOptions_IP_MULTICAST_IF ||
        opt == jbvb_net_SocketOptions_IP_MULTICAST_IF2) {
        return getMulticbstInterfbce(env, this, fd, opt);

    }

    /*
     * SO_BINDADDR implemented using getsocknbme
     */
    if (opt == jbvb_net_SocketOptions_SO_BINDADDR) {
        /* find out locbl IP bddress */
        SOCKADDR him;
        socklen_t len = 0;
        int port;
        jobject ibObj;

        len = SOCKADDR_LEN;

        if (getsocknbme(fd, (struct sockbddr *)&him, &len) == -1) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                           "Error getting socket nbme");
            return NULL;
        }
        ibObj = NET_SockbddrToInetAddress(env, (struct sockbddr *)&him, &port);

        return ibObj;
    }

    /*
     * Mbp the Jbvb level socket option to the plbtform specific
     * level bnd option nbme.
     */
    if (NET_MbpSocketOption(opt, &level, &optnbme)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Invblid option");
        return NULL;
    }

    if (opt == jbvb_net_SocketOptions_IP_MULTICAST_LOOP &&
        level == IPPROTO_IP) {
        optlen = sizeof(optvbl.c);
    } else {
        optlen = sizeof(optvbl.i);
    }

    if (NET_GetSockOpt(fd, level, optnbme, (void *)&optvbl, &optlen) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                         "Error getting socket option");
        return NULL;
    }

    switch (opt) {
        cbse jbvb_net_SocketOptions_IP_MULTICAST_LOOP:
            /* getLoopbbckMode() returns true if IP_MULTICAST_LOOP disbbled */
            if (level == IPPROTO_IP) {
                return crebteBoolebn(env, (int)!optvbl.c);
            } else {
                return crebteBoolebn(env, !optvbl.i);
            }

        cbse jbvb_net_SocketOptions_SO_BROADCAST:
        cbse jbvb_net_SocketOptions_SO_REUSEADDR:
            return crebteBoolebn(env, optvbl.i);

        cbse jbvb_net_SocketOptions_SO_SNDBUF:
        cbse jbvb_net_SocketOptions_SO_RCVBUF:
        cbse jbvb_net_SocketOptions_IP_TOS:
            return crebteInteger(env, optvbl.i);

    }

    /* should never rebch here */
    return NULL;
}

/*
 * Multicbst-relbted cblls
 */

JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_setTTL(JNIEnv *env, jobject this,
                                             jbyte ttl) {
    jint ittl = ttl;
    if (ittl < 0) {
        ittl += 0x100;
    }
    Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_setTimeToLive(env, this, ittl);
}

/*
 * Set TTL for b socket. Throw exception if fbiled.
 */
stbtic void setTTL(JNIEnv *env, int fd, jint ttl) {
    chbr ittl = (chbr)ttl;
    if (setsockopt(fd, IPPROTO_IP, IP_MULTICAST_TTL, (chbr*)&ittl,
                   sizeof(ittl)) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                       "Error setting socket option");
    }
}

/*
 * Set hops limit for b socket. Throw exception if fbiled.
 */
#ifdef AF_INET6
stbtic void setHopLimit(JNIEnv *env, int fd, jint ttl) {
    int ittl = (int)ttl;
    if (setsockopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_HOPS,
                   (chbr*)&ittl, sizeof(ittl)) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                       "Error setting socket option");
    }
}
#endif

/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    setTTL
 * Signbture: (B)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_setTimeToLive(JNIEnv *env, jobject this,
                                                    jint ttl) {

    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    int fd;
    /* it is importbnt to cbst this to b chbr, otherwise setsockopt gets confused */

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    /* setsockopt to be correct TTL */
#ifdef AF_INET6
#ifdef __linux__
    setTTL(env, fd, ttl);
    JNU_CHECK_EXCEPTION(env);
    if (ipv6_bvbilbble()) {
        setHopLimit(env, fd, ttl);
    }
#else  /*  __linux__ not defined */
    if (ipv6_bvbilbble()) {
        setHopLimit(env, fd, ttl);
    } else {
        setTTL(env, fd, ttl);
    }
#endif  /* __linux__ */
#else
    setTTL(env, fd, ttl);
#endif  /* AF_INET6 */
}

/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    getTTL
 * Signbture: ()B
 */
JNIEXPORT jbyte JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_getTTL(JNIEnv *env, jobject this) {
    return (jbyte)Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_getTimeToLive(env, this);
}


/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    getTTL
 * Signbture: ()B
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_getTimeToLive(JNIEnv *env, jobject this) {

    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jint fd = -1;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return -1;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    /* getsockopt of TTL */
#ifdef AF_INET6
    if (ipv6_bvbilbble()) {
        int ttl = 0;
        socklen_t len = sizeof(ttl);

        if (getsockopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_HOPS,
                       (chbr*)&ttl, &len) < 0) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                                         "Error getting socket option");
            return -1;
        }
        return (jint)ttl;
    } else
#endif /* AF_INET6 */
        {
            u_chbr ttl = 0;
            socklen_t len = sizeof(ttl);
            if (getsockopt(fd, IPPROTO_IP, IP_MULTICAST_TTL,
                           (chbr*)&ttl, &len) < 0) {
                NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                               "Error getting socket option");
                return -1;
            }
            return (jint)ttl;
        }
}


/*
 * mcbst_join_lebve: Join or lebve b multicbst group.
 *
 * For IPv4 sockets use IP_ADD_MEMBERSHIP/IP_DROP_MEMBERSHIP socket option
 * to join/lebve multicbst group.
 *
 * For IPv6 sockets use IPV6_ADD_MEMBERSHIP/IPV6_DROP_MEMBERSHIP socket option
 * to join/lebve multicbst group. If multicbst group is bn IPv4 bddress then
 * bn IPv4-mbpped bddress is used.
 *
 * On Linux with IPv6 if we wish to join/lebve bn IPv4 multicbst group then
 * we must use the IPv4 socket options. This is becbuse the IPv6 socket options
 * don't support IPv4-mbpped bddresses. This is true bs per 2.2.19 bnd 2.4.7
 * kernel relebses. In the future it's possible thbt IP_ADD_MEMBERSHIP
 * will be updbted to return ENOPROTOOPT if uses with bn IPv6 socket (Solbris
 * blrebdy does this). Thus to cbter for this we first try with the IPv4
 * socket options bnd if they fbil we use the IPv6 socket options. This
 * seems b rebsonbble fbilsbfe solution.
 */
stbtic void mcbst_join_lebve(JNIEnv *env, jobject this,
                             jobject ibObj, jobject niObj,
                             jboolebn join) {

    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jint fd;
    jint ipv6_join_lebve;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    if (IS_NULL(ibObj)) {
        JNU_ThrowNullPointerException(env, "ibObj");
        return;
    }

    /*
     * Determine if this is bn IPv4 or IPv6 join/lebve.
     */
#ifdef AF_INET6
    ipv6_join_lebve = ipv6_bvbilbble();

#ifdef __linux__
    if (getInetAddress_fbmily(env, ibObj) == IPv4) {
        ipv6_join_lebve = JNI_FALSE;
    }
#endif

#else
    /*
     * IPv6 not compiled in
     */
    ipv6_join_lebve = JNI_FALSE;
#endif

    /*
     * For IPv4 join use IP_ADD_MEMBERSHIP/IP_DROP_MEMBERSHIP socket option
     *
     * On Linux if IPv4 or IPv6 use IP_ADD_MEMBERSHIP/IP_DROP_MEMBERSHIP
     */
    if (!ipv6_join_lebve) {
#ifdef __linux__
        struct ip_mreqn mnbme;
#else
        struct ip_mreq mnbme;
#endif
        int mnbme_len;

        /*
         * joinGroup(InetAddress, NetworkInterfbce) implementbtion :-
         *
         * Linux/IPv6:  use ip_mreqn structure populbted with multicbst
         *              bddress bnd interfbce index.
         *
         * IPv4:        use ip_mreq structure populbted with multicbst
         *              bddress bnd first bddress obtbined from
         *              NetworkInterfbce
         */
        if (niObj != NULL) {
#if defined(__linux__) && defined(AF_INET6)
            if (ipv6_bvbilbble()) {
                stbtic jfieldID ni_indexID;

                if (ni_indexID == NULL) {
                    jclbss c = (*env)->FindClbss(env, "jbvb/net/NetworkInterfbce");
                    CHECK_NULL(c);
                    ni_indexID = (*env)->GetFieldID(env, c, "index", "I");
                    CHECK_NULL(ni_indexID);
                }

                mnbme.imr_multibddr.s_bddr = htonl(getInetAddress_bddr(env, ibObj));
                mnbme.imr_bddress.s_bddr = 0;
                mnbme.imr_ifindex =  (*env)->GetIntField(env, niObj, ni_indexID);
                mnbme_len = sizeof(struct ip_mreqn);
            } else
#endif
            {
                jobjectArrby bddrArrby = (*env)->GetObjectField(env, niObj, ni_bddrsID);
                jobject bddr;

                if ((*env)->GetArrbyLength(env, bddrArrby) < 1) {
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "bbd brgument for IP_ADD_MEMBERSHIP: "
                        "No IP bddresses bound to interfbce");
                    return;
                }
                bddr = (*env)->GetObjectArrbyElement(env, bddrArrby, 0);

                mnbme.imr_multibddr.s_bddr = htonl(getInetAddress_bddr(env, ibObj));
#ifdef __linux__
                mnbme.imr_bddress.s_bddr = htonl(getInetAddress_bddr(env, bddr));
#else
                mnbme.imr_interfbce.s_bddr = htonl(getInetAddress_bddr(env, bddr));
#endif
                mnbme_len = sizeof(struct ip_mreq);
            }
        }


        /*
         * joinGroup(InetAddress) implementbtion :-
         *
         * Linux/IPv6:  use ip_mreqn structure populbted with multicbst
         *              bddress bnd interfbce index. index obtbined
         *              from cbched vblue or IPV6_MULTICAST_IF.
         *
         * IPv4:        use ip_mreq structure populbted with multicbst
         *              bddress bnd locbl bddress obtbined from
         *              IP_MULTICAST_IF. On Linux IP_MULTICAST_IF
         *              returns different structure depending on
         *              kernel.
         */

        if (niObj == NULL) {

#if defined(__linux__) && defined(AF_INET6)
            if (ipv6_bvbilbble()) {

                int index;
                socklen_t len = sizeof(index);

                if (getsockopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_IF,
                               (chbr*)&index, &len) < 0) {
                    NET_ThrowCurrent(env, "getsockopt IPV6_MULTICAST_IF fbiled");
                    return;
                }

                mnbme.imr_multibddr.s_bddr = htonl(getInetAddress_bddr(env, ibObj));
                mnbme.imr_bddress.s_bddr = 0 ;
                mnbme.imr_ifindex = index;
                mnbme_len = sizeof(struct ip_mreqn);
            } else
#endif
            {
                struct in_bddr in;
                struct in_bddr *inP = &in;
                socklen_t len = sizeof(struct in_bddr);

                if (getsockopt(fd, IPPROTO_IP, IP_MULTICAST_IF, (chbr *)inP, &len) < 0) {
                    NET_ThrowCurrent(env, "getsockopt IP_MULTICAST_IF fbiled");
                    return;
                }

#ifdef __linux__
                mnbme.imr_bddress.s_bddr = in.s_bddr;

#else
                mnbme.imr_interfbce.s_bddr = in.s_bddr;
#endif
                mnbme.imr_multibddr.s_bddr = htonl(getInetAddress_bddr(env, ibObj));
                mnbme_len = sizeof(struct ip_mreq);
            }
        }


        /*
         * Join the multicbst group.
         */
        if (setsockopt(fd, IPPROTO_IP, (join ? IP_ADD_MEMBERSHIP:IP_DROP_MEMBERSHIP),
                       (chbr *) &mnbme, mnbme_len) < 0) {

            /*
             * If IP_ADD_MEMBERSHIP returns ENOPROTOOPT on Linux bnd we've got
             * IPv6 enbbled then it's possible thbt the kernel hbs been fixed
             * so we switch to IPV6_ADD_MEMBERSHIP socket option.
             * As of 2.4.7 kernel IPV6_ADD_MEMBERSHIP cbn't hbndle IPv4-mbpped
             * bddresses so we hbve to use IP_ADD_MEMBERSHIP for IPv4 multicbst
             * groups. However if the socket is bn IPv6 socket then then setsockopt
             * should return ENOPROTOOPT. We bssume this will be fixed in Linux
             * bt some stbge.
             */
#if defined(__linux__) && defined(AF_INET6)
            if (errno == ENOPROTOOPT) {
                if (ipv6_bvbilbble()) {
                    ipv6_join_lebve = JNI_TRUE;
                    errno = 0;
                } else  {
                    errno = ENOPROTOOPT;    /* errno cbn be chbnged by ipv6_bvbilbble */
                }
            }
#endif
            if (errno) {
                if (join) {
                    NET_ThrowCurrent(env, "setsockopt IP_ADD_MEMBERSHIP fbiled");
                } else {
                    if (errno == ENOENT)
                        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                            "Not b member of the multicbst group");
                    else
                        NET_ThrowCurrent(env, "setsockopt IP_DROP_MEMBERSHIP fbiled");
                }
                return;
            }
        }

        /*
         * If we hbven't switched to IPv6 socket option then we're done.
         */
        if (!ipv6_join_lebve) {
            return;
        }
    }


    /*
     * IPv6 join. If it's bn IPv4 multicbst group then we use bn IPv4-mbpped
     * bddress.
     */
#ifdef AF_INET6
    {
        struct ipv6_mreq mnbme6;
        jbyteArrby ipbddress;
        jbyte cbddr[16];
        jint fbmily;
        jint bddress;
        fbmily = getInetAddress_fbmily(env, ibObj) == IPv4? AF_INET : AF_INET6;
        if (fbmily == AF_INET) { /* will convert to IPv4-mbpped bddress */
            memset((chbr *) cbddr, 0, 16);
            bddress = getInetAddress_bddr(env, ibObj);

            cbddr[10] = 0xff;
            cbddr[11] = 0xff;

            cbddr[12] = ((bddress >> 24) & 0xff);
            cbddr[13] = ((bddress >> 16) & 0xff);
            cbddr[14] = ((bddress >> 8) & 0xff);
            cbddr[15] = (bddress & 0xff);
        } else {
            getInet6Address_ipbddress(env, ibObj, (chbr*)cbddr);
        }

        memcpy((void *)&(mnbme6.ipv6mr_multibddr), cbddr, sizeof(struct in6_bddr));
        if (IS_NULL(niObj)) {
            int index;
            socklen_t len = sizeof(index);

            if (getsockopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_IF,
                           (chbr*)&index, &len) < 0) {
                NET_ThrowCurrent(env, "getsockopt IPV6_MULTICAST_IF fbiled");
                return;
            }

#ifdef __linux__
            /*
             * On 2.4.8+ if we join b group with the interfbce set to 0
             * then the kernel records the interfbce it decides. This cbuses
             * subsequent lebve groups to fbil bs there is no mbtch. Thus we
             * pick the interfbce if there is b mbtching route.
             */
            if (index == 0) {
                int rt_index = getDefbultIPv6Interfbce(&(mnbme6.ipv6mr_multibddr));
                if (rt_index > 0) {
                    index = rt_index;
                }
            }
#endif
#ifdef MACOSX
            if (fbmily == AF_INET6 && index == 0) {
                index = getDefbultScopeID(env);
            }
#endif
            mnbme6.ipv6mr_interfbce = index;
        } else {
            jint idx = (*env)->GetIntField(env, niObj, ni_indexID);
            mnbme6.ipv6mr_interfbce = idx;
        }

#if defined(_ALLBSD_SOURCE)
#define ADD_MEMBERSHIP          IPV6_JOIN_GROUP
#define DRP_MEMBERSHIP          IPV6_LEAVE_GROUP
#define S_ADD_MEMBERSHIP        "IPV6_JOIN_GROUP"
#define S_DRP_MEMBERSHIP        "IPV6_LEAVE_GROUP"
#else
#define ADD_MEMBERSHIP          IPV6_ADD_MEMBERSHIP
#define DRP_MEMBERSHIP          IPV6_DROP_MEMBERSHIP
#define S_ADD_MEMBERSHIP        "IPV6_ADD_MEMBERSHIP"
#define S_DRP_MEMBERSHIP        "IPV6_DROP_MEMBERSHIP"
#endif

        /* Join the multicbst group */
        if (setsockopt(fd, IPPROTO_IPV6, (join ? ADD_MEMBERSHIP : DRP_MEMBERSHIP),
                       (chbr *) &mnbme6, sizeof (mnbme6)) < 0) {

            if (join) {
                NET_ThrowCurrent(env, "setsockopt " S_ADD_MEMBERSHIP " fbiled");
            } else {
                if (errno == ENOENT) {
                   JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Not b member of the multicbst group");
                } else {
                    NET_ThrowCurrent(env, "setsockopt " S_DRP_MEMBERSHIP " fbiled");
                }
            }
        }
    }
#endif
}

/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    join
 * Signbture: (Ljbvb/net/InetAddress;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_join(JNIEnv *env, jobject this,
                                           jobject ibObj, jobject niObj)
{
    mcbst_join_lebve(env, this, ibObj, niObj, JNI_TRUE);
}

/*
 * Clbss:     jbvb_net_PlbinDbtbgrbmSocketImpl
 * Method:    lebve
 * Signbture: (Ljbvb/net/InetAddress;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinDbtbgrbmSocketImpl_lebve(JNIEnv *env, jobject this,
                                            jobject ibObj, jobject niObj)
{
    mcbst_join_lebve(env, this, ibObj, niObj, JNI_FALSE);
}
