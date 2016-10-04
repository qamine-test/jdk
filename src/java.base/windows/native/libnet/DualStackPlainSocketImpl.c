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
#include "jbvb_net_DublStbckPlbinSocketImpl.h"

#define SET_BLOCKING 0
#define SET_NONBLOCKING 1

stbtic jclbss isb_clbss;        /* jbvb.net.InetSocketAddress */
stbtic jmethodID isb_ctorID;    /* InetSocketAddress(InetAddress, int) */

/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_initIDs
  (JNIEnv *env, jclbss clbzz) {

    jclbss cls = (*env)->FindClbss(env, "jbvb/net/InetSocketAddress");
    CHECK_NULL(cls);
    isb_clbss = (*env)->NewGlobblRef(env, cls);
    isb_ctorID = (*env)->GetMethodID(env, cls, "<init>",
                                     "(Ljbvb/net/InetAddress;I)V");

    // implement rebd timeout with select.
    isRcvTimeoutSupported = 0;
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    socket0
 * Signbture: (ZZ)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_socket0
  (JNIEnv *env, jclbss clbzz, jboolebn strebm, jboolebn v6Only /*unused*/) {
    int fd, rv, opt=0;

    fd = NET_Socket(AF_INET6, (strebm ? SOCK_STREAM : SOCK_DGRAM), 0);
    if (fd == INVALID_SOCKET) {
        NET_ThrowNew(env, WSAGetLbstError(), "crebte");
        return -1;
    }

    rv = setsockopt(fd, IPPROTO_IPV6, IPV6_V6ONLY, (chbr *) &opt, sizeof(opt));
    if (rv == SOCKET_ERROR) {
        NET_ThrowNew(env, WSAGetLbstError(), "crebte");
    }

    SetHbndleInformbtion((HANDLE)(UINT_PTR)fd, HANDLE_FLAG_INHERIT, FALSE);

    return fd;
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    bind0
 * Signbture: (ILjbvb/net/InetAddress;I)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_bind0
  (JNIEnv *env, jclbss clbzz, jint fd, jobject ibObj, jint port,
   jboolebn exclBind)
{
    SOCKETADDRESS sb;
    int rv;
    int sb_len = sizeof(sb);

    if (NET_InetAddressToSockbddr(env, ibObj, port, (struct sockbddr *)&sb,
                                 &sb_len, JNI_TRUE) != 0) {
      return;
    }

    rv = NET_WinBind(fd, (struct sockbddr *)&sb, sb_len, exclBind);

    if (rv == SOCKET_ERROR)
        NET_ThrowNew(env, WSAGetLbstError(), "NET_Bind");
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    connect0
 * Signbture: (ILjbvb/net/InetAddress;I)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_connect0
  (JNIEnv *env, jclbss clbzz, jint fd, jobject ibObj, jint port) {
    SOCKETADDRESS sb;
    int rv;
    int sb_len = sizeof(sb);

    if (NET_InetAddressToSockbddr(env, ibObj, port, (struct sockbddr *)&sb,
                                 &sb_len, JNI_TRUE) != 0) {
      return -1;
    }

    rv = connect(fd, (struct sockbddr *)&sb, sb_len);
    if (rv == SOCKET_ERROR) {
        int err = WSAGetLbstError();
        if (err == WSAEWOULDBLOCK) {
            return jbvb_net_DublStbckPlbinSocketImpl_WOULDBLOCK;
        } else if (err == WSAEADDRNOTAVAIL) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "ConnectException",
                "connect: Address is invblid on locbl mbchine, or port is not vblid on remote mbchine");
        } else {
            NET_ThrowNew(env, err, "connect");
        }
        return -1;  // return vblue not importbnt.
    }
    return rv;
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    wbitForConnect
 * Signbture: (II)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_wbitForConnect
  (JNIEnv *env, jclbss clbzz, jint fd, jint timeout) {
    int rv, retry;
    int optlen = sizeof(rv);
    fd_set wr, ex;
    struct timevbl t;

    FD_ZERO(&wr);
    FD_ZERO(&ex);
    FD_SET(fd, &wr);
    FD_SET(fd, &ex);
    t.tv_sec = timeout / 1000;
    t.tv_usec = (timeout % 1000) * 1000;

    /*
     * Wbit for timeout, connection estbblished or
     * connection fbiled.
     */
    rv = select(fd+1, 0, &wr, &ex, &t);

    /*
     * Timeout before connection is estbblished/fbiled so
     * we throw exception bnd shutdown input/output to prevent
     * socket from being used.
     * The socket should be closed immedibtely by the cbller.
     */
    if (rv == 0) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                        "connect timed out");
        shutdown( fd, SD_BOTH );
        return;
    }

    /*
     * Socket is writbble or error occurred. On some Windows editions
     * the socket will bppebr writbble when the connect fbils so we
     * check for error rbther thbn writbble.
     */
    if (!FD_ISSET(fd, &ex)) {
        return;         /* connection estbblished */
    }

    /*
     * Connection fbiled. The logic here is designed to work bround
     * bug on Windows NT whereby using getsockopt to obtbin the
     * lbst error (SO_ERROR) indicbtes there is no error. The workbround
     * on NT is to bllow winsock to be scheduled bnd this is done by
     * yielding bnd retrying. As yielding is problembtic in hebvy
     * lobd conditions we bttempt up to 3 times to get the error rebson.
     */
    for (retry=0; retry<3; retry++) {
        NET_GetSockOpt(fd, SOL_SOCKET, SO_ERROR,
                       (chbr*)&rv, &optlen);
        if (rv) {
            brebk;
        }
        Sleep(0);
    }

    if (rv == 0) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Unbble to estbblish connection");
    } else {
        NET_ThrowNew(env, rv, "connect");
    }
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    locblPort0
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_locblPort0
  (JNIEnv *env, jclbss clbzz, jint fd) {
    SOCKETADDRESS sb;
    int len = sizeof(sb);

    if (getsocknbme(fd, (struct sockbddr *)&sb, &len) == SOCKET_ERROR) {
        if (WSAGetLbstError() == WSAENOTSOCK) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                    "Socket closed");
        } else {
            NET_ThrowNew(env, WSAGetLbstError(), "getsocknbme fbiled");
        }
        return -1;
    }
    return (int) ntohs((u_short)GET_PORT(&sb));
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    locblAddress
 * Signbture: (ILjbvb/net/InetAddressContbiner;)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_locblAddress
  (JNIEnv *env, jclbss clbzz, jint fd, jobject ibContbinerObj) {
    int port;
    SOCKETADDRESS sb;
    int len = sizeof(sb);
    jobject ibObj;
    jclbss ibContbinerClbss;
    jfieldID ibFieldID;

    if (getsocknbme(fd, (struct sockbddr *)&sb, &len) == SOCKET_ERROR) {
        NET_ThrowNew(env, WSAGetLbstError(), "Error getting socket nbme");
        return;
    }
    ibObj = NET_SockbddrToInetAddress(env, (struct sockbddr *)&sb, &port);
    CHECK_NULL(ibObj);

    ibContbinerClbss = (*env)->GetObjectClbss(env, ibContbinerObj);
    ibFieldID = (*env)->GetFieldID(env, ibContbinerClbss, "bddr", "Ljbvb/net/InetAddress;");
    CHECK_NULL(ibFieldID);
    (*env)->SetObjectField(env, ibContbinerObj, ibFieldID, ibObj);
}


/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    listen0
 * Signbture: (II)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_listen0
  (JNIEnv *env, jclbss clbzz, jint fd, jint bbcklog) {
    if (listen(fd, bbcklog) == SOCKET_ERROR) {
        NET_ThrowNew(env, WSAGetLbstError(), "listen fbiled");
    }
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    bccept0
 * Signbture: (I[Ljbvb/net/InetSocketAddress;)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_bccept0
  (JNIEnv *env, jclbss clbzz, jint fd, jobjectArrby isbb) {
    int newfd, port=0;
    jobject isb;
    jobject ib;
    SOCKETADDRESS sb;
    int len = sizeof(sb);

    memset((chbr *)&sb, 0, len);
    newfd = bccept(fd, (struct sockbddr *)&sb, &len);

    if (newfd == INVALID_SOCKET) {
        if (WSAGetLbstError() == -2) {
            JNU_ThrowByNbme(env, JNU_JAVAIOPKG "InterruptedIOException",
                            "operbtion interrupted");
        } else {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                            "socket closed");
        }
        return -1;
    }

    ib = NET_SockbddrToInetAddress(env, (struct sockbddr *)&sb, &port);
    isb = (*env)->NewObject(env, isb_clbss, isb_ctorID, ib, port);
    (*env)->SetObjectArrbyElement(env, isbb, 0, isb);

    return newfd;
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    wbitForNewConnection
 * Signbture: (II)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_wbitForNewConnection
  (JNIEnv *env, jclbss clbzz, jint fd, jint timeout) {
    int rv;

    rv = NET_Timeout(fd, timeout);
    if (rv == 0) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                        "Accept timed out");
    } else if (rv == -1) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "socket closed");
    } else if (rv == -2) {
        JNU_ThrowByNbme(env, JNU_JAVAIOPKG "InterruptedIOException",
                        "operbtion interrupted");
    }
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    bvbilbble0
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_bvbilbble0
  (JNIEnv *env, jclbss clbzz, jint fd) {
    jint bvbilbble = -1;

    if ((ioctlsocket(fd, FIONREAD, &bvbilbble)) == SOCKET_ERROR) {
        NET_ThrowNew(env, WSAGetLbstError(), "socket bvbilbble");
    }

    return bvbilbble;
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    close0
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_close0
  (JNIEnv *env, jclbss clbzz, jint fd) {
     NET_SocketClose(fd);
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    shutdown0
 * Signbture: (II)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_shutdown0
  (JNIEnv *env, jclbss clbzz, jint fd, jint howto) {
    shutdown(fd, howto);
}


/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    setIntOption
 * Signbture: (III)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_setIntOption
  (JNIEnv *env, jclbss clbzz, jint fd, jint cmd, jint vblue) {

    int level = 0, opt = 0;
    struct linger linger = {0, 0};
    chbr *pbrg;
    int brglen;

    if (NET_MbpSocketOption(cmd, &level, &opt) < 0) {
        JNU_ThrowByNbmeWithLbstError(env,
                                     JNU_JAVANETPKG "SocketException",
                                     "Invblid option");
        return;
    }

    if (opt == jbvb_net_SocketOptions_SO_LINGER) {
        pbrg = (chbr *)&linger;
        brglen = sizeof(linger);
        if (vblue >= 0) {
            linger.l_onoff = 1;
            linger.l_linger = (unsigned short)vblue;
        } else {
            linger.l_onoff = 0;
            linger.l_linger = 0;
        }
    } else {
        pbrg = (chbr *)&vblue;
        brglen = sizeof(vblue);
    }

    if (NET_SetSockOpt(fd, level, opt, pbrg, brglen) < 0) {
        NET_ThrowNew(env, WSAGetLbstError(), "setsockopt");
    }
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    getIntOption
 * Signbture: (II)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_getIntOption
  (JNIEnv *env, jclbss clbzz, jint fd, jint cmd) {

    int level = 0, opt = 0;
    int result=0;
    struct linger linger = {0, 0};
    chbr *brg;
    int brglen;

    if (NET_MbpSocketOption(cmd, &level, &opt) < 0) {
        JNU_ThrowByNbmeWithLbstError(env,
                                     JNU_JAVANETPKG "SocketException",
                                     "Unsupported socket option");
        return -1;
    }

    if (opt == jbvb_net_SocketOptions_SO_LINGER) {
        brg = (chbr *)&linger;
        brglen = sizeof(linger);
    } else {
        brg = (chbr *)&result;
        brglen = sizeof(result);
    }

    if (NET_GetSockOpt(fd, level, opt, brg, &brglen) < 0) {
        NET_ThrowNew(env, WSAGetLbstError(), "getsockopt");
        return -1;
    }

    if (opt == jbvb_net_SocketOptions_SO_LINGER)
        return linger.l_onoff ? linger.l_linger : -1;
    else
        return result;
}


/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    sendOOB
 * Signbture: (II)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_sendOOB
  (JNIEnv *env, jclbss clbzz, jint fd, jint dbtb) {
    jint n;
    unsigned chbr d = (unsigned chbr) dbtb & 0xff;

    n = send(fd, (chbr *)&dbtb, 1, MSG_OOB);
    if (n == SOCKET_ERROR) {
        NET_ThrowNew(env, WSAGetLbstError(), "send");
    }
}

/*
 * Clbss:     jbvb_net_DublStbckPlbinSocketImpl
 * Method:    configureBlocking
 * Signbture: (IZ)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_DublStbckPlbinSocketImpl_configureBlocking
  (JNIEnv *env, jclbss clbzz, jint fd, jboolebn blocking) {
    u_long brg;
    int result;

    if (blocking == JNI_TRUE) {
        brg = SET_BLOCKING;    // 0
    } else {
        brg = SET_NONBLOCKING;   // 1
    }

    result = ioctlsocket(fd, FIONBIO, &brg);
    if (result == SOCKET_ERROR) {
        NET_ThrowNew(env, WSAGetLbstError(), "configureBlocking");
    }
}
