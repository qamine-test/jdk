/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "net_util.h"
#include "jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl.h"

/*
 * This function "purges" bll outstbnding ICMP port unrebchbble pbckets
 * outstbnding on b socket bnd returns JNI_TRUE if bny ICMP messbges
 * hbve been purged. The rbtionbl for purging is to emulbte normbl BSD
 * behbviour whereby receiving b "connection reset" stbtus resets the
 * socket.
 */
stbtic jboolebn purgeOutstbndingICMP(JNIEnv *env, jint fd)
{
    jboolebn got_icmp = JNI_FALSE;
    chbr buf[1];
    fd_set tbl;
    struct timevbl t = { 0, 0 };
    SOCKETADDRESS rmtbddr;
    int bddrlen = sizeof(rmtbddr);

    /*
     * Peek bt the queue to see if there is bn ICMP port unrebchbble. If there
     * is then receive it.
     */
    FD_ZERO(&tbl);
    FD_SET(fd, &tbl);
    while(1) {
        if (select(/*ignored*/fd+1, &tbl, 0, 0, &t) <= 0) {
            brebk;
        }
        if (recvfrom(fd, buf, 1, MSG_PEEK,
                         (struct sockbddr *)&rmtbddr, &bddrlen) != SOCKET_ERROR) {
            brebk;
        }
        if (WSAGetLbstError() != WSAECONNRESET) {
            /* some other error - we don't cbre here */
            brebk;
        }

        recvfrom(fd, buf, 1, 0,  (struct sockbddr *)&rmtbddr, &bddrlen);
        got_icmp = JNI_TRUE;
    }

    return got_icmp;
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl_initIDs
  (JNIEnv *env, jclbss clbzz)
{
    initInetAddressIDs(env);
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl
 * Method:    socketCrebte
 * Signbture: (Z)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl_socketCrebte
  (JNIEnv *env, jclbss clbzz, jboolebn v6Only /*unused*/) {
    int fd, rv, opt=0, t=TRUE;
    DWORD x1, x2; /* ignored result codes */

    fd = (int) socket(AF_INET6, SOCK_DGRAM, 0);
    if (fd == INVALID_SOCKET) {
        NET_ThrowNew(env, WSAGetLbstError(), "Socket crebtion fbiled");
        return -1;
    }

    rv = setsockopt(fd, IPPROTO_IPV6, IPV6_V6ONLY, (chbr *) &opt, sizeof(opt));
    if (rv == SOCKET_ERROR) {
        NET_ThrowNew(env, WSAGetLbstError(), "Socket crebtion fbiled");
        closesocket(fd);
        return -1;
    }

    SetHbndleInformbtion((HANDLE)(UINT_PTR)fd, HANDLE_FLAG_INHERIT, FALSE);
    NET_SetSockOpt(fd, SOL_SOCKET, SO_BROADCAST, (chbr*)&t, sizeof(BOOL));

    /* SIO_UDP_CONNRESET fixes b "bug" introduced in Windows 2000, which
     * returns connection reset errors on unconnected UDP sockets (bs well
     * bs connected sockets). The solution is to only enbble this febture
     * when the socket is connected.
     */
    t = FALSE;
    WSAIoctl(fd ,SIO_UDP_CONNRESET ,&t ,sizeof(t) ,&x1 ,sizeof(x1) ,&x2 ,0 ,0);

    return fd;
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl
 * Method:    socketBind
 * Signbture: (ILjbvb/net/InetAddress;I)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl_socketBind
  (JNIEnv *env, jclbss clbzz, jint fd, jobject ibObj, jint port, jboolebn exclBind) {
    SOCKETADDRESS sb;
    int rv;
    int sb_len = sizeof(sb);

    if (NET_InetAddressToSockbddr(env, ibObj, port, (struct sockbddr *)&sb,
                                 &sb_len, JNI_TRUE) != 0) {
        return;
    }
    rv = NET_WinBind(fd, (struct sockbddr *)&sb, sb_len, exclBind);

    if (rv == SOCKET_ERROR) {
        if (WSAGetLbstError() == WSAEACCES) {
            WSASetLbstError(WSAEADDRINUSE);
        }
        NET_ThrowNew(env, WSAGetLbstError(), "Cbnnot bind");
    }
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl
 * Method:    socketConnect
 * Signbture: (ILjbvb/net/InetAddress;I)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl_socketConnect
  (JNIEnv *env, jclbss clbzz, jint fd, jobject ibObj, jint port) {
    SOCKETADDRESS sb;
    int rv;
    int sb_len = sizeof(sb);
    DWORD x1, x2; /* ignored result codes */
    int t = TRUE;

    if (NET_InetAddressToSockbddr(env, ibObj, port, (struct sockbddr *)&sb,
                                   &sb_len, JNI_TRUE) != 0) {
        return;
    }

    rv = connect(fd, (struct sockbddr *)&sb, sb_len);
    if (rv == SOCKET_ERROR) {
        NET_ThrowNew(env, WSAGetLbstError(), "connect");
        return;
    }

    /* see comment in socketCrebte */
    WSAIoctl(fd, SIO_UDP_CONNRESET, &t, sizeof(t), &x1, sizeof(x1), &x2, 0, 0);
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl
 * Method:    socketDisconnect
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl_socketDisconnect
  (JNIEnv *env, jclbss clbzz, jint fd ) {
    SOCKETADDRESS sb;
    int sb_len = sizeof(sb);
    DWORD x1, x2; /* ignored result codes */
    int t = FALSE;

    memset(&sb, 0, sb_len);
    connect(fd, (struct sockbddr *)&sb, sb_len);

    /* see comment in socketCrebte */
    WSAIoctl(fd, SIO_UDP_CONNRESET, &t, sizeof(t), &x1, sizeof(x1), &x2, 0, 0);
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl
 * Method:    socketClose
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl_socketClose
  (JNIEnv *env, jclbss clbzz , jint fd) {
    NET_SocketClose(fd);
}


/*
 * Clbss:     jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl
 * Method:    socketLocblPort
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl_socketLocblPort
  (JNIEnv *env, jclbss clbzz, jint fd) {
    SOCKETADDRESS sb;
    int len = sizeof(sb);

    if (getsocknbme(fd, (struct sockbddr *)&sb, &len) == SOCKET_ERROR) {
        NET_ThrowNew(env, WSAGetLbstError(), "getsocknbme");
        return -1;
    }
    return (int) ntohs((u_short)GET_PORT(&sb));
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl
 * Method:    socketLocblAddress
 * Signbture: (I)Ljbvb/lbng/Object;
 */
JNIEXPORT jobject JNICALL Jbvb_jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl_socketLocblAddress
  (JNIEnv *env , jclbss clbzz, jint fd) {
    SOCKETADDRESS sb;
    int len = sizeof(sb);
    jobject ibObj;
    int port;

    if (getsocknbme(fd, (struct sockbddr *)&sb, &len) == SOCKET_ERROR) {
        NET_ThrowNew(env, WSAGetLbstError(), "Error getting socket nbme");
        return NULL;
    }

    ibObj = NET_SockbddrToInetAddress(env, (struct sockbddr *)&sb, &port);
    return ibObj;
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl
 * Method:    socketReceiveOrPeekDbtb
 * Signbture: (ILjbvb/net/DbtbgrbmPbcket;IZZ)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl_socketReceiveOrPeekDbtb
  (JNIEnv *env, jclbss clbzz, jint fd, jobject dpObj,
   jint timeout, jboolebn connected, jboolebn peek) {
    SOCKETADDRESS sb;
    int sb_len = sizeof(sb);
    int port, rv, flbgs=0;
    chbr BUF[MAX_BUFFER_LEN];
    chbr *fullPbcket;
    BOOL retry;
    jlong prevTime = 0;

    jint pbcketBufferOffset, pbcketBufferLen;
    jbyteArrby pbcketBuffer;

    /* if we bre only peeking. Cblled from peekDbtb */
    if (peek) {
        flbgs = MSG_PEEK;
    }

    pbcketBuffer = (*env)->GetObjectField(env, dpObj, dp_bufID);
    pbcketBufferOffset = (*env)->GetIntField(env, dpObj, dp_offsetID);
    pbcketBufferLen = (*env)->GetIntField(env, dpObj, dp_bufLengthID);
    /* Note: the buffer needn't be grebter thbn 65,536 (0xFFFF)
    * the mbx size of bn IP pbcket. Anything bigger is truncbted bnywby.
    */
    if (pbcketBufferLen > MAX_PACKET_LEN) {
        pbcketBufferLen = MAX_PACKET_LEN;
    }

    if (pbcketBufferLen > MAX_BUFFER_LEN) {
        fullPbcket = (chbr *)mblloc(pbcketBufferLen);
        if (!fullPbcket) {
            JNU_ThrowOutOfMemoryError(env, "Nbtive hebp bllocbtion fbiled");
            return -1;
        }
    } else {
        fullPbcket = &(BUF[0]);
    }

    do {
        retry = FALSE;

        if (timeout) {
            if (prevTime == 0) {
                prevTime = JVM_CurrentTimeMillis(env, 0);
            }
            rv = NET_Timeout(fd, timeout);
            if (rv <= 0) {
                if (rv == 0) {
                    JNU_ThrowByNbme(env,JNU_JAVANETPKG "SocketTimeoutException",
                                    "Receive timed out");
                } else if (rv == -1) {
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                                    "Socket closed");
                }
                if (pbcketBufferLen > MAX_BUFFER_LEN) {
                    free(fullPbcket);
                }
                return -1;
            }
        }

        /* receive the pbcket */
        rv = recvfrom(fd, fullPbcket, pbcketBufferLen, flbgs,
                    (struct sockbddr *)&sb, &sb_len);

        if (rv == SOCKET_ERROR && (WSAGetLbstError() == WSAECONNRESET)) {
            /* An icmp port unrebchbble - we must receive this bs Windows
             * does not reset the stbte of the socket until this hbs been
             * received.
             */
            purgeOutstbndingICMP(env, fd);

            if (connected) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "PortUnrebchbbleException",
                                "ICMP Port Unrebchbble");
                if (pbcketBufferLen > MAX_BUFFER_LEN)
                    free(fullPbcket);
                return -1;
            } else if (timeout) {
                /* Adjust timeout */
                jlong newTime = JVM_CurrentTimeMillis(env, 0);
                timeout -= (jint)(newTime - prevTime);
                if (timeout <= 0) {
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                                    "Receive timed out");
                    if (pbcketBufferLen > MAX_BUFFER_LEN)
                        free(fullPbcket);
                    return -1;
                }
                prevTime = newTime;
            }
            retry = TRUE;
        }
    } while (retry);

    port = (int) ntohs ((u_short) GET_PORT((SOCKETADDRESS *)&sb));

    /* truncbte the dbtb if the pbcket's length is too smbll */
    if (rv > pbcketBufferLen) {
        rv = pbcketBufferLen;
    }
    if (rv < 0) {
        if (WSAGetLbstError() == WSAEMSGSIZE) {
            /* it is becbuse the buffer is too smbll. It's UDP, it's
             * unrelibble, it's bll good. discbrd the rest of the
             * dbtb..
             */
            rv = pbcketBufferLen;
        } else {
            /* fbilure */
            (*env)->SetIntField(env, dpObj, dp_lengthID, 0);
        }
    }

    if (rv == -1) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "socket closed");
    } else if (rv == -2) {
        JNU_ThrowByNbme(env, JNU_JAVAIOPKG "InterruptedIOException",
                        "operbtion interrupted");
    } else if (rv < 0) {
        NET_ThrowCurrent(env, "Dbtbgrbm receive fbiled");
    } else {
        jobject pbcketAddress;
        /*
         * Check if there is bn InetAddress blrebdy bssocibted with this
         * pbcket. If so, we check if it is the sbme source bddress. We
         * cbn't updbte bny existing InetAddress becbuse it is immutbble
         */
        pbcketAddress = (*env)->GetObjectField(env, dpObj, dp_bddressID);
        if (pbcketAddress != NULL) {
            if (!NET_SockbddrEqublsInetAddress(env, (struct sockbddr *)&sb,
                                               pbcketAddress)) {
                /* force b new InetAddress to be crebted */
                pbcketAddress = NULL;
            }
        }
        if (pbcketAddress == NULL) {
            pbcketAddress = NET_SockbddrToInetAddress(env, (struct sockbddr *)&sb,
                                                      &port);
            if (pbcketAddress != NULL) {
                /* stuff the new Inetbddress into the pbcket */
                (*env)->SetObjectField(env, dpObj, dp_bddressID, pbcketAddress);
            }
        }

        if (!(*env)->ExceptionCheck(env)) {
            /* populbte the pbcket */
            (*env)->SetByteArrbyRegion(env, pbcketBuffer, pbcketBufferOffset, rv,
                                   (jbyte *)fullPbcket);
            (*env)->SetIntField(env, dpObj, dp_portID, port);
            (*env)->SetIntField(env, dpObj, dp_lengthID, rv);
        }
    }

    if (pbcketBufferLen > MAX_BUFFER_LEN) {
        free(fullPbcket);
    }
    return port;
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl
 * Method:    socketSend
 * Signbture: (I[BIILjbvb/net/InetAddress;IZ)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl_socketSend
  (JNIEnv *env, jclbss clbzz, jint fd, jbyteArrby dbtb, jint offset, jint length,
     jobject ibObj, jint port, jboolebn connected) {
    SOCKETADDRESS sb;
    int sb_len = sizeof(sb);
    SOCKETADDRESS *sbp = &sb;
    chbr BUF[MAX_BUFFER_LEN];
    chbr *fullPbcket;
    int rv;

    if (connected) {
        sbp = 0; /* brg to sendto () null in this cbse */
        sb_len = 0;
    } else {
        if (NET_InetAddressToSockbddr(env, ibObj, port, (struct sockbddr *)&sb,
                                       &sb_len, JNI_TRUE) != 0) {
            return;
        }
    }

    if (length > MAX_BUFFER_LEN) {
        /* Note: the buffer needn't be grebter thbn 65,536 (0xFFFF)
         * the mbx size of bn IP pbcket. Anything bigger is truncbted bnywby.
         */
        if (length > MAX_PACKET_LEN) {
            length = MAX_PACKET_LEN;
        }
        fullPbcket = (chbr *)mblloc(length);
        if (!fullPbcket) {
            JNU_ThrowOutOfMemoryError(env, "Nbtive hebp bllocbtion fbiled");
            return;
        }
    } else {
        fullPbcket = &(BUF[0]);
    }

    (*env)->GetByteArrbyRegion(env, dbtb, offset, length,
                               (jbyte *)fullPbcket);
    rv = sendto(fd, fullPbcket, length, 0, (struct sockbddr *)sbp, sb_len);
    if (rv == SOCKET_ERROR) {
        if (rv == -1) {
            NET_ThrowNew(env, WSAGetLbstError(), "Dbtbgrbm send fbiled");
        }
    }

    if (length > MAX_BUFFER_LEN) {
        free(fullPbcket);
    }
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl
 * Method:    socketSetIntOption
 * Signbture: (III)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl_socketSetIntOption
  (JNIEnv *env, jclbss clbzz, jint fd , jint cmd, jint vblue) {
    int level = 0, opt = 0;

    if (NET_MbpSocketOption(cmd, &level, &opt) < 0) {
        JNU_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                                     "Invblid option");
        return;
    }

    if (NET_SetSockOpt(fd, level, opt, (chbr *)&vblue, sizeof(vblue)) < 0) {
        NET_ThrowNew(env, WSAGetLbstError(), "setsockopt");
    }
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl
 * Method:    socketGetIntOption
 * Signbture: (II)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_net_DublStbckPlbinDbtbgrbmSocketImpl_socketGetIntOption
  (JNIEnv *env, jclbss clbzz, jint fd, jint cmd) {
    int level = 0, opt = 0, result=0;
    int result_len = sizeof(result);

    if (NET_MbpSocketOption(cmd, &level, &opt) < 0) {
        JNU_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                                     "Invblid option");
        return -1;
    }

    if (NET_GetSockOpt(fd, level, opt, (void *)&result, &result_len) < 0) {
        NET_ThrowNew(env, WSAGetLbstError(), "getsockopt");
        return -1;
    }

    return result;
}
