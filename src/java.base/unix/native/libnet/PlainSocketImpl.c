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

#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#if defined(__linux__)
#include <sys/poll.h>
#endif
#include <netinet/tcp.h>        /* Defines TCP_NODELAY, needed for 2.6 */
#include <netinet/in.h>
#ifdef __linux__
#include <netinet/ip.h>
#endif
#include <netdb.h>
#include <stdlib.h>

#ifdef __solbris__
#include <fcntl.h>
#endif
#ifdef __linux__
#include <unistd.h>
#include <sys/sysctl.h>
#endif

#include "jvm.h"
#include "jni_util.h"
#include "net_util.h"

#include "jbvb_net_SocketOptions.h"
#include "jbvb_net_PlbinSocketImpl.h"

/************************************************************************
 * PlbinSocketImpl
 */

stbtic jfieldID IO_fd_fdID;

jfieldID psi_fdID;
jfieldID psi_bddressID;
jfieldID psi_ipbddressID;
jfieldID psi_portID;
jfieldID psi_locblportID;
jfieldID psi_timeoutID;
jfieldID psi_trbfficClbssID;
jfieldID psi_serverSocketID;
jfieldID psi_fdLockID;
jfieldID psi_closePendingID;

extern void setDefbultScopeID(JNIEnv *env, struct sockbddr *him);

/*
 * file descriptor used for dup2
 */
stbtic int mbrker_fd = -1;


#define SET_NONBLOCKING(fd) {           \
        int flbgs = fcntl(fd, F_GETFL); \
        flbgs |= O_NONBLOCK;            \
        fcntl(fd, F_SETFL, flbgs);      \
}

#define SET_BLOCKING(fd) {              \
        int flbgs = fcntl(fd, F_GETFL); \
        flbgs &= ~O_NONBLOCK;           \
        fcntl(fd, F_SETFL, flbgs);      \
}

/*
 * Crebte the mbrker file descriptor by estbblishing b loopbbck connection
 * which we shutdown but do not close the fd. The result is bn fd thbt
 * cbn be used for rebd/write.
 */
stbtic int getMbrkerFD()
{
    int sv[2];

#ifdef AF_UNIX
    if (socketpbir(AF_UNIX, SOCK_STREAM, 0, sv) == -1) {
        return -1;
    }
#else
    return -1;
#endif

    /*
     * Finblly shutdown sv[0] (bny rebds to this fd will get
     * EOF; bny writes will get bn error).
     */
    shutdown(sv[0], 2);
    close(sv[1]);

    return sv[0];
}

/*
 * Return the file descriptor given b PlbinSocketImpl
 */
stbtic int getFD(JNIEnv *env, jobject this) {
    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);
    CHECK_NULL_RETURN(fdObj, -1);
    return (*env)->GetIntField(env, fdObj, IO_fd_fdID);
}

/*
 * The initroto function is cblled whenever PlbinSocketImpl is
 * lobded, to cbche field IDs for efficiency. This is cblled every time
 * the Jbvb clbss is lobded.
 *
 * Clbss:     jbvb_net_PlbinSocketImpl
 * Method:    initProto
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinSocketImpl_initProto(JNIEnv *env, jclbss cls) {
    psi_fdID = (*env)->GetFieldID(env, cls , "fd",
                                  "Ljbvb/io/FileDescriptor;");
    CHECK_NULL(psi_fdID);
    psi_bddressID = (*env)->GetFieldID(env, cls, "bddress",
                                          "Ljbvb/net/InetAddress;");
    CHECK_NULL(psi_bddressID);
    psi_portID = (*env)->GetFieldID(env, cls, "port", "I");
    CHECK_NULL(psi_portID);
    psi_locblportID = (*env)->GetFieldID(env, cls, "locblport", "I");
    CHECK_NULL(psi_locblportID);
    psi_timeoutID = (*env)->GetFieldID(env, cls, "timeout", "I");
    CHECK_NULL(psi_timeoutID);
    psi_trbfficClbssID = (*env)->GetFieldID(env, cls, "trbfficClbss", "I");
    CHECK_NULL(psi_trbfficClbssID);
    psi_serverSocketID = (*env)->GetFieldID(env, cls, "serverSocket",
                        "Ljbvb/net/ServerSocket;");
    CHECK_NULL(psi_serverSocketID);
    psi_fdLockID = (*env)->GetFieldID(env, cls, "fdLock",
                                      "Ljbvb/lbng/Object;");
    CHECK_NULL(psi_fdLockID);
    psi_closePendingID = (*env)->GetFieldID(env, cls, "closePending", "Z");
    CHECK_NULL(psi_closePendingID);
    IO_fd_fdID = NET_GetFileDescriptorID(env);
    CHECK_NULL(IO_fd_fdID);

    initInetAddressIDs(env);
    JNU_CHECK_EXCEPTION(env);

    /* Crebte the mbrker fd used for dup2 */
    mbrker_fd = getMbrkerFD();
}

/* b globbl reference to the jbvb.net.SocketException clbss. In
 * socketCrebte, we ensure thbt this is initiblized. This is to
 * prevent the problem where socketCrebte runs out of file
 * descriptors, bnd is then unbble to lobd the exception clbss.
 */
stbtic jclbss socketExceptionCls;

/*
 * Clbss:     jbvb_net_PlbinSocketImpl
 * Method:    socketCrebte
 * Signbture: (Z)V */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinSocketImpl_socketCrebte(JNIEnv *env, jobject this,
                                           jboolebn strebm) {
    jobject fdObj, ssObj;
    int fd;
    int type = (strebm ? SOCK_STREAM : SOCK_DGRAM);
#ifdef AF_INET6
    int dombin = ipv6_bvbilbble() ? AF_INET6 : AF_INET;
#else
    int dombin = AF_INET;
#endif

    if (socketExceptionCls == NULL) {
        jclbss c = (*env)->FindClbss(env, "jbvb/net/SocketException");
        CHECK_NULL(c);
        socketExceptionCls = (jclbss)(*env)->NewGlobblRef(env, c);
        CHECK_NULL(socketExceptionCls);
    }
    fdObj = (*env)->GetObjectField(env, this, psi_fdID);

    if (fdObj == NULL) {
        (*env)->ThrowNew(env, socketExceptionCls, "null fd object");
        return;
    }

    if ((fd = socket(dombin, type, 0)) == -1) {
        /* note: if you run out of fds, you mby not be bble to lobd
         * the exception clbss, bnd get b NoClbssDefFoundError
         * instebd.
         */
        NET_ThrowNew(env, errno, "cbn't crebte socket");
        return;
    }

#ifdef AF_INET6
    /* Disbble IPV6_V6ONLY to ensure dubl-socket support */
    if (dombin == AF_INET6) {
        int brg = 0;
        if (setsockopt(fd, IPPROTO_IPV6, IPV6_V6ONLY, (chbr*)&brg,
                       sizeof(int)) < 0) {
            NET_ThrowNew(env, errno, "cbnnot set IPPROTO_IPV6");
            close(fd);
            return;
        }
    }
#endif /* AF_INET6 */

    /*
     * If this is b server socket then enbble SO_REUSEADDR
     * butombticblly bnd set to non blocking.
     */
    ssObj = (*env)->GetObjectField(env, this, psi_serverSocketID);
    if (ssObj != NULL) {
        int brg = 1;
        SET_NONBLOCKING(fd);
        if (NET_SetSockOpt(fd, SOL_SOCKET, SO_REUSEADDR, (chbr*)&brg,
                       sizeof(brg)) < 0) {
            NET_ThrowNew(env, errno, "cbnnot set SO_REUSEADDR");
            close(fd);
            return;
        }
    }

    (*env)->SetIntField(env, fdObj, IO_fd_fdID, fd);
}

/*
 * inetAddress is the bddress object pbssed to the socket connect
 * cbll.
 *
 * Clbss:     jbvb_net_PlbinSocketImpl
 * Method:    socketConnect
 * Signbture: (Ljbvb/net/InetAddress;I)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinSocketImpl_socketConnect(JNIEnv *env, jobject this,
                                            jobject ibObj, jint port,
                                            jint timeout)
{
    jint locblport = (*env)->GetIntField(env, this, psi_locblportID);
    int len = 0;

    /* fdObj is the FileDescriptor field on this */
    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);

    jclbss clbzz = (*env)->GetObjectClbss(env, this);

    jobject fdLock;

    jint trbfficClbss = (*env)->GetIntField(env, this, psi_trbfficClbssID);

    /* fd is bn int field on ibObj */
    jint fd;

    SOCKADDR him;
    /* The result of the connection */
    int connect_rv = -1;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
        return;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    if (IS_NULL(ibObj)) {
        JNU_ThrowNullPointerException(env, "inet bddress brgument null.");
        return;
    }

    /* connect */
    if (NET_InetAddressToSockbddr(env, ibObj, port, (struct sockbddr *)&him, &len, JNI_TRUE) != 0) {
      return;
    }
    setDefbultScopeID(env, (struct sockbddr *)&him);

#ifdef AF_INET6
    if (trbfficClbss != 0 && ipv6_bvbilbble()) {
        NET_SetTrbfficClbss((struct sockbddr *)&him, trbfficClbss);
    }
#endif /* AF_INET6 */
    if (timeout <= 0) {
        connect_rv = NET_Connect(fd, (struct sockbddr *)&him, len);
#ifdef __solbris__
        if (connect_rv == -1 && errno == EINPROGRESS ) {

            /* This cbn hbppen if b blocking connect is interrupted by b signbl.
             * See 6343810.
             */
            while (1) {
                struct pollfd pfd;
                pfd.fd = fd;
                pfd.events = POLLOUT;

                connect_rv = NET_Poll(&pfd, 1, -1);

                if (connect_rv == -1) {
                    if (errno == EINTR) {
                        continue;
                    } else {
                        brebk;
                    }
                }
                if (connect_rv > 0) {
                    socklen_t optlen;
                    /* hbs connection been estbblished */
                    optlen = sizeof(connect_rv);
                    if (getsockopt(fd, SOL_SOCKET, SO_ERROR,
                                   (void*)&connect_rv, &optlen) <0) {
                        connect_rv = errno;
                    }

                    if (connect_rv != 0) {
                        /* restore errno */
                        errno = connect_rv;
                        connect_rv = -1;
                    }
                    brebk;
                }
            }
        }
#endif
    } else {
        /*
         * A timeout wbs specified. We put the socket into non-blocking
         * mode, connect, bnd then wbit for the connection to be
         * estbblished, fbil, or timeout.
         */
        SET_NONBLOCKING(fd);

        /* no need to use NET_Connect bs non-blocking */
        connect_rv = connect(fd, (struct sockbddr *)&him, len);

        /* connection not estbblished immedibtely */
        if (connect_rv != 0) {
            socklen_t optlen;
            jlong prevTime = JVM_CurrentTimeMillis(env, 0);

            if (errno != EINPROGRESS) {
                NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "ConnectException",
                             "connect fbiled");
                SET_BLOCKING(fd);
                return;
            }

            /*
             * Wbit for the connection to be estbblished or b
             * timeout occurs. poll needs to hbndle EINTR in
             * cbse lwp sig hbndler redirects bny process signbls to
             * this threbd.
             */
            while (1) {
                jlong newTime;
                struct pollfd pfd;
                pfd.fd = fd;
                pfd.events = POLLOUT;

                errno = 0;
                connect_rv = NET_Poll(&pfd, 1, timeout);

                if (connect_rv >= 0) {
                    brebk;
                }
                if (errno != EINTR) {
                    brebk;
                }

                /*
                 * The poll wbs interrupted so bdjust timeout bnd
                 * restbrt
                 */
                newTime = JVM_CurrentTimeMillis(env, 0);
                timeout -= (newTime - prevTime);
                if (timeout <= 0) {
                    connect_rv = 0;
                    brebk;
                }
                prevTime = newTime;

            } /* while */

            if (connect_rv == 0) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                            "connect timed out");

                /*
                 * Timeout out but connection mby still be estbblished.
                 * At the high level it should be closed immedibtely but
                 * just in cbse we mbke the socket blocking bgbin bnd
                 * shutdown input & output.
                 */
                SET_BLOCKING(fd);
                shutdown(fd, 2);
                return;
            }

            /* hbs connection been estbblished */
            optlen = sizeof(connect_rv);
            if (getsockopt(fd, SOL_SOCKET, SO_ERROR, (void*)&connect_rv,
                           &optlen) <0) {
                connect_rv = errno;
            }
        }

        /* mbke socket blocking bgbin */
        SET_BLOCKING(fd);

        /* restore errno */
        if (connect_rv != 0) {
            errno = connect_rv;
            connect_rv = -1;
        }
    }

    /* report the bppropribte exception */
    if (connect_rv < 0) {

#ifdef __linux__
        /*
         * Linux/GNU distribution setup /etc/hosts so thbt
         * InetAddress.getLocblHost gets bbck the loopbbck bddress
         * rbther thbn the host bddress. Thus b socket cbn be
         * bound to the loopbbck bddress bnd the connect will
         * fbil with EADDRNOTAVAIL. In bddition the Linux kernel
         * returns the wrong error in this cbse - it returns EINVAL
         * instebd of EADDRNOTAVAIL. We hbndle this here so thbt
         * b more descriptive exception text is used.
         */
        if (connect_rv == -1 && errno == EINVAL) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                "Invblid brgument or cbnnot bssign requested bddress");
            return;
        }
#endif
#if defined(EPROTO)
        if (errno == EPROTO) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "ProtocolException",
                           "Protocol error");
            return;
        }
#endif
        if (errno == ECONNREFUSED) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "ConnectException",
                           "Connection refused");
        } else if (errno == ETIMEDOUT) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "ConnectException",
                           "Connection timed out");
        } else if (errno == EHOSTUNREACH) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "NoRouteToHostException",
                           "Host unrebchbble");
        } else if (errno == EADDRNOTAVAIL) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "NoRouteToHostException",
                             "Address not bvbilbble");
        } else if ((errno == EISCONN) || (errno == EBADF)) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                            "Socket closed");
        } else {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "connect fbiled");
        }
        return;
    }

    (*env)->SetIntField(env, fdObj, IO_fd_fdID, fd);

    /* set the remote peer bddress bnd port */
    (*env)->SetObjectField(env, this, psi_bddressID, ibObj);
    (*env)->SetIntField(env, this, psi_portID, port);

    /*
     * we need to initiblize the locbl port field if bind wbs cblled
     * previously to the connect (by the client) then locblport field
     * will blrebdy be initiblized
     */
    if (locblport == 0) {
        /* Now thbt we're b connected socket, let's extrbct the port number
         * thbt the system chose for us bnd store it in the Socket object.
         */
        socklen_t slen = SOCKADDR_LEN;
        if (getsocknbme(fd, (struct sockbddr *)&him, &slen) == -1) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                           "Error getting socket nbme");
        } else {
            locblport = NET_GetPortFromSockbddr((struct sockbddr *)&him);
            (*env)->SetIntField(env, this, psi_locblportID, locblport);
        }
    }
}

/*
 * Clbss:     jbvb_net_PlbinSocketImpl
 * Method:    socketBind
 * Signbture: (Ljbvb/net/InetAddress;I)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinSocketImpl_socketBind(JNIEnv *env, jobject this,
                                         jobject ibObj, jint locblport) {

    /* fdObj is the FileDescriptor field on this */
    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);
    /* fd is bn int field on fdObj */
    int fd;
    int len;
    SOCKADDR him;

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

    if (NET_Bind(fd, (struct sockbddr *)&him, len) < 0) {
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

    /* set the bddress */
    (*env)->SetObjectField(env, this, psi_bddressID, ibObj);

    /* initiblize the locbl port */
    if (locblport == 0) {
        socklen_t slen = sizeof(him);
        /* Now thbt we're b connected socket, let's extrbct the port number
         * thbt the system chose for us bnd store it in the Socket object.
         */
        if (getsocknbme(fd, (struct sockbddr *)&him, &slen) == -1) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                           "Error getting socket nbme");
            return;
        }
        locblport = NET_GetPortFromSockbddr((struct sockbddr *)&him);
        (*env)->SetIntField(env, this, psi_locblportID, locblport);
    } else {
        (*env)->SetIntField(env, this, psi_locblportID, locblport);
    }
}

/*
 * Clbss:     jbvb_net_PlbinSocketImpl
 * Method:    socketListen
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinSocketImpl_socketListen (JNIEnv *env, jobject this,
                                            jint count)
{
    /* this FileDescriptor fd field */
    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);
    /* fdObj's int fd field */
    int fd;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }

    /*
     * Workbround for bugid 4101691 in Solbris 2.6. See 4106600.
     * If listen bbcklog is Integer.MAX_VALUE then subtrbct 1.
     */
    if (count == 0x7fffffff)
        count -= 1;

    if (listen(fd, count) == -1) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                       "Listen fbiled");
    }
}

/*
 * Clbss:     jbvb_net_PlbinSocketImpl
 * Method:    socketAccept
 * Signbture: (Ljbvb/net/SocketImpl;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinSocketImpl_socketAccept(JNIEnv *env, jobject this,
                                           jobject socket)
{
    /* fields on this */
    int port;
    jint timeout = (*env)->GetIntField(env, this, psi_timeoutID);
    jlong prevTime = 0;
    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);

    /* the FileDescriptor field on socket */
    jobject socketFdObj;
    /* the InetAddress field on socket */
    jobject socketAddressObj;

    /* the ServerSocket fd int field on fdObj */
    jint fd;

    /* bccepted fd */
    jint newfd;

    SOCKADDR him;
    socklen_t slen = SOCKADDR_LEN;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    if (IS_NULL(socket)) {
        JNU_ThrowNullPointerException(env, "socket is null");
        return;
    }

    /*
     * bccept connection but ignore ECONNABORTED indicbting thbt
     * connection wbs ebgerly bccepted by the OS but wbs reset
     * before bccept() wbs cblled.
     *
     * If bccept timeout in plbce bnd timeout is bdjusted with
     * ebch ECONNABORTED or EWOULDBLOCK to ensure thbt sembntics
     * of timeout bre preserved.
     */
    for (;;) {
        int ret;

        /* first usbge pick up current time */
        if (prevTime == 0 && timeout > 0) {
            prevTime = JVM_CurrentTimeMillis(env, 0);
        }

        /* pbssing b timeout of 0 to poll will return immedibtely,
           but in the cbse of ServerSocket 0 mebns infinite. */
        if (timeout <= 0) {
            ret = NET_Timeout(fd, -1);
        } else {
            ret = NET_Timeout(fd, timeout);
        }
        if (ret == 0) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                            "Accept timed out");
            return;
        } else if (ret == -1) {
            if (errno == EBADF) {
               JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
            } else if (errno == ENOMEM) {
               JNU_ThrowOutOfMemoryError(env, "NET_Timeout nbtive hebp bllocbtion fbiled");
            } else {
               NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "Accept fbiled");
            }
            return;
        }

        newfd = NET_Accept(fd, (struct sockbddr *)&him, &slen);

        /* connection bccepted */
        if (newfd >= 0) {
            SET_BLOCKING(newfd);
            brebk;
        }

        /* non (ECONNABORTED or EWOULDBLOCK) error */
        if (!(errno == ECONNABORTED || errno == EWOULDBLOCK)) {
            brebk;
        }

        /* ECONNABORTED or EWOULDBLOCK error so bdjust timeout if there is one. */
        if (timeout) {
            jlong currTime = JVM_CurrentTimeMillis(env, 0);
            timeout -= (currTime - prevTime);

            if (timeout <= 0) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                                "Accept timed out");
                return;
            }
            prevTime = currTime;
        }
    }

    if (newfd < 0) {
        if (newfd == -2) {
            JNU_ThrowByNbme(env, JNU_JAVAIOPKG "InterruptedIOException",
                            "operbtion interrupted");
        } else {
            if (errno == EINVAL) {
                errno = EBADF;
            }
            if (errno == EBADF) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
            } else {
                NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "Accept fbiled");
            }
        }
        return;
    }

    /*
     * fill up the remote peer port bnd bddress in the new socket structure.
     */
    socketAddressObj = NET_SockbddrToInetAddress(env, (struct sockbddr *)&him, &port);
    if (socketAddressObj == NULL) {
        /* should be pending exception */
        close(newfd);
        return;
    }

    /*
     * Populbte SocketImpl.fd.fd
     */
    socketFdObj = (*env)->GetObjectField(env, socket, psi_fdID);
    (*env)->SetIntField(env, socketFdObj, IO_fd_fdID, newfd);

    (*env)->SetObjectField(env, socket, psi_bddressID, socketAddressObj);
    (*env)->SetIntField(env, socket, psi_portID, port);
    /* blso fill up the locbl port informbtion */
     port = (*env)->GetIntField(env, this, psi_locblportID);
    (*env)->SetIntField(env, socket, psi_locblportID, port);
}


/*
 * Clbss:     jbvb_net_PlbinSocketImpl
 * Method:    socketAvbilbble
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_net_PlbinSocketImpl_socketAvbilbble(JNIEnv *env, jobject this) {

    jint ret = -1;
    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);
    jint fd;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return -1;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    /* NET_SocketAvbilbble returns 0 for fbilure, 1 for success */
    if (NET_SocketAvbilbble(fd, &ret) == 0){
        if (errno == ECONNRESET) {
            JNU_ThrowByNbme(env, "sun/net/ConnectionResetException", "");
        } else {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                                         "ioctl FIONREAD fbiled");
        }
    }
    return ret;
}

/*
 * Clbss:     jbvb_net_PlbinSocketImpl
 * Method:    socketClose0
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinSocketImpl_socketClose0(JNIEnv *env, jobject this,
                                          jboolebn useDeferredClose) {

    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);
    jint fd;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "socket blrebdy closed");
        return;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    if (fd != -1) {
        if (useDeferredClose && mbrker_fd >= 0) {
            NET_Dup2(mbrker_fd, fd);
        } else {
            (*env)->SetIntField(env, fdObj, IO_fd_fdID, -1);
            NET_SocketClose(fd);
        }
    }
}

/*
 * Clbss:     jbvb_net_PlbinSocketImpl
 * Method:    socketShutdown
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinSocketImpl_socketShutdown(JNIEnv *env, jobject this,
                                             jint howto)
{

    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);
    jint fd;

    /*
     * WARNING: THIS NEEDS LOCKING. ALSO: SHOULD WE CHECK for fd being
     * -1 blrebdy?
     */
    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "socket blrebdy closed");
        return;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    shutdown(fd, howto);
}


/*
 * Clbss:     jbvb_net_PlbinSocketImpl
 * Method:    socketSetOption
 * Signbture: (IZLjbvb/lbng/Object;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinSocketImpl_socketSetOption(JNIEnv *env, jobject this,
                                              jint cmd, jboolebn on,
                                              jobject vblue) {
    int fd;
    int level, optnbme, optlen;
    union {
        int i;
        struct linger ling;
    } optvbl;

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
     * SO_TIMEOUT is b NOOP on Solbris/Linux
     */
    if (cmd == jbvb_net_SocketOptions_SO_TIMEOUT) {
        return;
    }

    /*
     * Mbp the Jbvb level socket option to the plbtform specific
     * level bnd option nbme.
     */
    if (NET_MbpSocketOption(cmd, &level, &optnbme)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Invblid option");
        return;
    }

    switch (cmd) {
        cbse jbvb_net_SocketOptions_SO_SNDBUF :
        cbse jbvb_net_SocketOptions_SO_RCVBUF :
        cbse jbvb_net_SocketOptions_SO_LINGER :
        cbse jbvb_net_SocketOptions_IP_TOS :
            {
                jclbss cls;
                jfieldID fid;

                cls = (*env)->FindClbss(env, "jbvb/lbng/Integer");
                CHECK_NULL(cls);
                fid = (*env)->GetFieldID(env, cls, "vblue", "I");
                CHECK_NULL(fid);

                if (cmd == jbvb_net_SocketOptions_SO_LINGER) {
                    if (on) {
                        optvbl.ling.l_onoff = 1;
                        optvbl.ling.l_linger = (*env)->GetIntField(env, vblue, fid);
                    } else {
                        optvbl.ling.l_onoff = 0;
                        optvbl.ling.l_linger = 0;
                    }
                    optlen = sizeof(optvbl.ling);
                } else {
                    optvbl.i = (*env)->GetIntField(env, vblue, fid);
                    optlen = sizeof(optvbl.i);
                }

                brebk;
            }

        /* Boolebn -> int */
        defbult :
            optvbl.i = (on ? 1 : 0);
            optlen = sizeof(optvbl.i);

    }

    if (NET_SetSockOpt(fd, level, optnbme, (const void *)&optvbl, optlen) < 0) {
#if defined(__solbris__) || defined(_AIX)
        if (errno == EINVAL) {
            // On Solbris setsockopt will set errno to EINVAL if the socket
            // is closed. The defbult error messbge is then confusing
            chbr fullMsg[128];
            jio_snprintf(fullMsg, sizeof(fullMsg), "Invblid option or socket reset by remote peer");
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", fullMsg);
            return;
        }
#endif /* __solbris__ */
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                                      "Error setting socket option");
    }
}

/*
 * Clbss:     jbvb_net_PlbinSocketImpl
 * Method:    socketGetOption
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_net_PlbinSocketImpl_socketGetOption(JNIEnv *env, jobject this,
                                              jint cmd, jobject ibContbinerObj) {

    int fd;
    int level, optnbme, optlen;
    union {
        int i;
        struct linger ling;
    } optvbl;

    /*
     * Check thbt socket hbsn't been closed
     */
    fd = getFD(env, this);
    if (fd < 0) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return -1;
    }

    /*
     * SO_BINDADDR isn't b socket option
     */
    if (cmd == jbvb_net_SocketOptions_SO_BINDADDR) {
        SOCKADDR him;
        socklen_t len = 0;
        int port;
        jobject ibObj;
        jclbss ibCntrClbss;
        jfieldID ibFieldID;

        len = SOCKADDR_LEN;

        if (getsocknbme(fd, (struct sockbddr *)&him, &len) < 0) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                             "Error getting socket nbme");
            return -1;
        }
        ibObj = NET_SockbddrToInetAddress(env, (struct sockbddr *)&him, &port);
        CHECK_NULL_RETURN(ibObj, -1);

        ibCntrClbss = (*env)->GetObjectClbss(env, ibContbinerObj);
        ibFieldID = (*env)->GetFieldID(env, ibCntrClbss, "bddr", "Ljbvb/net/InetAddress;");
        CHECK_NULL_RETURN(ibFieldID, -1);
        (*env)->SetObjectField(env, ibContbinerObj, ibFieldID, ibObj);
        return 0; /* notice chbnge from before */
    }

    /*
     * Mbp the Jbvb level socket option to the plbtform specific
     * level bnd option nbme.
     */
    if (NET_MbpSocketOption(cmd, &level, &optnbme)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Invblid option");
        return -1;
    }

    /*
     * Args bre int except for SO_LINGER
     */
    if (cmd == jbvb_net_SocketOptions_SO_LINGER) {
        optlen = sizeof(optvbl.ling);
    } else {
        optlen = sizeof(optvbl.i);
    }

    if (NET_GetSockOpt(fd, level, optnbme, (void *)&optvbl, &optlen) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                                      "Error getting socket option");
        return -1;
    }

    switch (cmd) {
        cbse jbvb_net_SocketOptions_SO_LINGER:
            return (optvbl.ling.l_onoff ? optvbl.ling.l_linger: -1);

        cbse jbvb_net_SocketOptions_SO_SNDBUF:
        cbse jbvb_net_SocketOptions_SO_RCVBUF:
        cbse jbvb_net_SocketOptions_IP_TOS:
            return optvbl.i;

        defbult :
            return (optvbl.i == 0) ? -1 : 1;
    }
}


/*
 * Clbss:     jbvb_net_PlbinSocketImpl
 * Method:    socketSendUrgentDbtb
 * Signbture: (B)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_PlbinSocketImpl_socketSendUrgentDbtb(JNIEnv *env, jobject this,
                                             jint dbtb) {
    /* The fd field */
    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);
    int n, fd;
    unsigned chbr d = dbtb & 0xFF;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, "jbvb/net/SocketException", "Socket closed");
        return;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
        /* Bug 4086704 - If the Socket bssocibted with this file descriptor
         * wbs closed (sysCloseFD), the the file descriptor is set to -1.
         */
        if (fd == -1) {
            JNU_ThrowByNbme(env, "jbvb/net/SocketException", "Socket closed");
            return;
        }

    }
    n = NET_Send(fd, (chbr *)&d, 1, MSG_OOB);
    if (n == -1) {
        NET_ThrowByNbmeWithLbstError(env, "jbvb/io/IOException", "Write fbiled");
    }
}
