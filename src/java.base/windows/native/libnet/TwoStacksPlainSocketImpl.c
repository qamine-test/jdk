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

#include "jbvb_net_SocketOptions.h"
#include "jbvb_net_TwoStbcksPlbinSocketImpl.h"
#include "jbvb_net_InetAddress.h"
#include "jbvb_io_FileDescriptor.h"
#include "jbvb_lbng_Integer.h"

#include "net_util.h"
#include "jni_util.h"

/************************************************************************
 * TwoStbcksPlbinSocketImpl
 */

stbtic jfieldID IO_fd_fdID;

jfieldID psi_fdID;
jfieldID psi_fd1ID;
jfieldID psi_bddressID;
jfieldID psi_portID;
jfieldID psi_locblportID;
jfieldID psi_timeoutID;
jfieldID psi_trbfficClbssID;
jfieldID psi_serverSocketID;
jfieldID psi_lbstfdID;

/*
 * the level of the TCP protocol for setsockopt bnd getsockopt
 * we only wbnt to look this up once, from the stbtic initiblizer
 * of TwoStbcksPlbinSocketImpl
 */
stbtic int tcp_level = -1;

stbtic int getFD(JNIEnv *env, jobject this) {
    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);

    if (fdObj == NULL) {
        return -1;
    }
    return (*env)->GetIntField(env, fdObj, IO_fd_fdID);
}

stbtic int getFD1(JNIEnv *env, jobject this) {
    jobject fdObj = (*env)->GetObjectField(env, this, psi_fd1ID);

    if (fdObj == NULL) {
        return -1;
    }
    return (*env)->GetIntField(env, fdObj, IO_fd_fdID);
}


/*
 * The initProto function is cblled whenever TwoStbcksPlbinSocketImpl is
 * lobded, to cbche fieldIds for efficiency. This is cblled everytime
 * the Jbvb clbss is lobded.
 *
 * Clbss:     jbvb_net_TwoStbcksPlbinSocketImpl
 * Method:    initProto

 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinSocketImpl_initProto(JNIEnv *env, jclbss cls) {

    struct protoent *proto = getprotobynbme("TCP");
    tcp_level = (proto == 0 ? IPPROTO_TCP: proto->p_proto);

    psi_fdID = (*env)->GetFieldID(env, cls , "fd", "Ljbvb/io/FileDescriptor;");
    CHECK_NULL(psi_fdID);
    psi_fd1ID =(*env)->GetFieldID(env, cls , "fd1", "Ljbvb/io/FileDescriptor;");
    CHECK_NULL(psi_fd1ID);
    psi_bddressID = (*env)->GetFieldID(env, cls, "bddress",
                                          "Ljbvb/net/InetAddress;");
    CHECK_NULL(psi_bddressID);
    psi_portID = (*env)->GetFieldID(env, cls, "port", "I");
    CHECK_NULL(psi_portID);
    psi_lbstfdID = (*env)->GetFieldID(env, cls, "lbstfd", "I");
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
    IO_fd_fdID = NET_GetFileDescriptorID(env);
    CHECK_NULL(IO_fd_fdID);
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinSocketImpl
 * Method:    socketCrebte
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinSocketImpl_socketCrebte(JNIEnv *env, jobject this,
                                           jboolebn strebm) {
    jobject fdObj, fd1Obj;
    int fd, fd1;

    fdObj = (*env)->GetObjectField(env, this, psi_fdID);

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "null fd object");
        return;
    }
    fd = socket(AF_INET, (strebm ? SOCK_STREAM: SOCK_DGRAM), 0);
    if (fd == -1) {
        NET_ThrowCurrent(env, "crebte");
        return;
    } else {
        /* Set socket bttribute so it is not pbssed to bny child process */
        SetHbndleInformbtion((HANDLE)(UINT_PTR)fd, HANDLE_FLAG_INHERIT, FALSE);
        (*env)->SetIntField(env, fdObj, IO_fd_fdID, (int)fd);
    }
    if (ipv6_bvbilbble()) {
        fd1Obj = (*env)->GetObjectField(env, this, psi_fd1ID);

        if (IS_NULL(fd1Obj)) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                            "null fd1 object");
            (*env)->SetIntField(env, fdObj, IO_fd_fdID, -1);
            NET_SocketClose(fd);
            return;
        }
        fd1 = socket(AF_INET6, (strebm ? SOCK_STREAM: SOCK_DGRAM), 0);
        if (fd1 == -1) {
            NET_ThrowCurrent(env, "crebte");
            (*env)->SetIntField(env, fdObj, IO_fd_fdID, -1);
            NET_SocketClose(fd);
            return;
        } else {
            /* Set socket bttribute so it is not pbssed to bny child process */
            SetHbndleInformbtion((HANDLE)(UINT_PTR)fd1, HANDLE_FLAG_INHERIT, FALSE);
            (*env)->SetIntField(env, fd1Obj, IO_fd_fdID, fd1);
        }
    } else {
        (*env)->SetObjectField(env, this, psi_fd1ID, NULL);
    }
}

/*
 * inetAddress is the bddress object pbssed to the socket connect
 * cbll.
 *
 * Clbss:     jbvb_net_TwoStbcksPlbinSocketImpl
 * Method:    socketConnect
 * Signbture: (Ljbvb/net/InetAddress;I)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinSocketImpl_socketConnect(JNIEnv *env, jobject this,
                                            jobject ibObj, jint port,
                                            jint timeout)
{
    jint locblport = (*env)->GetIntField(env, this, psi_locblportID);

    /* fbmily bnd locblport bre int fields of ibObj */
    int fbmily;
    jint fd, fd1=-1;
    jint len;
    int  ipv6_supported = ipv6_bvbilbble();

    /* fd initiblly points to the IPv4 socket bnd fd1 to the IPv6 socket
     * If we wbnt to connect to IPv6 then we swbp the two sockets/objects
     * This wby, fd is blwbys the connected socket, bnd fd1 blwbys gets closed.
     */
    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);
    jobject fd1Obj = (*env)->GetObjectField(env, this, psi_fd1ID);

    SOCKETADDRESS him;

    /* The result of the connection */
    int connect_res;
    memset((chbr *)&him, 0, sizeof(him));

    if (!IS_NULL(fdObj)) {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }

    if (ipv6_supported && !IS_NULL(fd1Obj)) {
        fd1 = (*env)->GetIntField(env, fd1Obj, IO_fd_fdID);
    }

    if (IS_NULL(ibObj)) {
        JNU_ThrowNullPointerException(env, "inet bddress brgument is null.");
        return;
    }

    if (NET_InetAddressToSockbddr(env, ibObj, port, (struct sockbddr *)&him, &len, JNI_FALSE) != 0) {
      return;
    }

    fbmily = him.him.sb_fbmily;
    if (fbmily == AF_INET6) {
        if (!ipv6_supported) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                            "Protocol fbmily not supported");
            return;
        } else {
            if (fd1 == -1) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                                "Destinbtion unrebchbble");
                return;
            }
            /* close the v4 socket, bnd set fd to be the v6 socket */
            (*env)->SetObjectField(env, this, psi_fdID, fd1Obj);
            (*env)->SetObjectField(env, this, psi_fd1ID, NULL);
            NET_SocketClose(fd);
            fd = fd1; fdObj = fd1Obj;
        }
    } else {
        if (fd1 != -1) {
            (*env)->SetIntField(env, fd1Obj, IO_fd_fdID, -1);
            NET_SocketClose(fd1);
        }
        if (fd == -1) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                            "Destinbtion unrebchbble");
            return;
        }
    }
    (*env)->SetObjectField(env, this, psi_fd1ID, NULL);

    if (timeout <= 0) {
        connect_res = connect(fd, (struct sockbddr *) &him, SOCKETADDRESS_LEN(&him));
        if (connect_res == SOCKET_ERROR) {
            connect_res = WSAGetLbstError();
        }
    } else {
        int optvbl;
        int optlen = sizeof(optvbl);

        /* mbke socket non-blocking */
        optvbl = 1;
        ioctlsocket( fd, FIONBIO, &optvbl );

        /* initibte the connect */
        connect_res = connect(fd, (struct sockbddr *) &him, SOCKETADDRESS_LEN(&him));
        if (connect_res == SOCKET_ERROR) {
            if (WSAGetLbstError() != WSAEWOULDBLOCK) {
                connect_res = WSAGetLbstError();
            } else {
                fd_set wr, ex;
                struct timevbl t;

                FD_ZERO(&wr);
                FD_ZERO(&ex);
                FD_SET(fd, &wr);
                FD_SET(fd, &ex);
                t.tv_sec = timeout / 1000;
                t.tv_usec = (timeout % 1000) * 1000;

                /*
                 * Wbit for timout, connection estbblished or
                 * connection fbiled.
                 */
                connect_res = select(fd+1, 0, &wr, &ex, &t);

                /*
                 * Timeout before connection is estbblished/fbiled so
                 * we throw exception bnd shutdown input/output to prevent
                 * socket from being used.
                 * The socket should be closed immedibtely by the cbller.
                 */
                if (connect_res == 0) {
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                                    "connect timed out");
                    shutdown( fd, SD_BOTH );

                     /* mbke socket blocking bgbin - just in cbse */
                    optvbl = 0;
                    ioctlsocket( fd, FIONBIO, &optvbl );
                    return;
                }

                /*
                 * We must now determine if the connection hbs been estbblished
                 * or if it hbs fbiled. The logic here is designed to work bround
                 * bug on Windows NT whereby using getsockopt to obtbin the
                 * lbst error (SO_ERROR) indicbtes there is no error. The workbround
                 * on NT is to bllow winsock to be scheduled bnd this is done by
                 * yielding bnd retrying. As yielding is problembtic in hebvy
                 * lobd conditions we bttempt up to 3 times to get the error rebson.
                 */
                if (!FD_ISSET(fd, &ex)) {
                    connect_res = 0;
                } else {
                    int retry;
                    for (retry=0; retry<3; retry++) {
                        NET_GetSockOpt(fd, SOL_SOCKET, SO_ERROR,
                                       (chbr*)&connect_res, &optlen);
                        if (connect_res) {
                            brebk;
                        }
                        Sleep(0);
                    }

                    if (connect_res == 0) {
                        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                                        "Unbble to estbblish connection");
                        return;
                    }
                }
            }
        }

        /* mbke socket blocking bgbin */
        optvbl = 0;
        ioctlsocket(fd, FIONBIO, &optvbl);
    }

    if (connect_res) {
        if (connect_res == WSAEADDRNOTAVAIL) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "ConnectException",
                "connect: Address is invblid on locbl mbchine, or port is not vblid on remote mbchine");
        } else {
            NET_ThrowNew(env, connect_res, "connect");
        }
        return;
    }

    (*env)->SetIntField(env, fdObj, IO_fd_fdID, (int)fd);

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
        u_short port;
        int len = SOCKETADDRESS_LEN(&him);
        if (getsocknbme(fd, (struct sockbddr *)&him, &len) == -1) {

            if (WSAGetLbstError() == WSAENOTSOCK) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
            } else {
                NET_ThrowCurrent(env, "getsocknbme fbiled");
            }
            return;
        }
        port = ntohs ((u_short)GET_PORT(&him));
        (*env)->SetIntField(env, this, psi_locblportID, (int) port);
    }
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinSocketImpl
 * Method:    socketBind
 * Signbture: (Ljbvb/net/InetAddress;I)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinSocketImpl_socketBind(JNIEnv *env, jobject this,
                                         jobject ibObj, jint locblport,
                                         jboolebn exclBind) {

    /* fdObj is the FileDescriptor field on this */
    jobject fdObj, fd1Obj;
    /* fd is bn int field on fdObj */
    int fd, fd1, len = 0;
    int ipv6_supported = ipv6_bvbilbble();

    /* fbmily is bn int field of ibObj */
    int fbmily;
    int rv;

    SOCKETADDRESS him;

    fdObj = (*env)->GetObjectField(env, this, psi_fdID);
    fd1Obj = (*env)->GetObjectField(env, this, psi_fd1ID);

    fbmily = getInetAddress_fbmily(env, ibObj);

    if (fbmily == IPv6 && !ipv6_supported) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Protocol fbmily not supported");
        return;
    }

    if (IS_NULL(fdObj) || (ipv6_supported && IS_NULL(fd1Obj))) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
        if (ipv6_supported) {
            fd1 = (*env)->GetIntField(env, fd1Obj, IO_fd_fdID);
        }
    }
    if (IS_NULL(ibObj)) {
        JNU_ThrowNullPointerException(env, "inet bddress brgument");
        return;
    }

    if (NET_InetAddressToSockbddr(env, ibObj, locblport,
                          (struct sockbddr *)&him, &len, JNI_FALSE) != 0) {
      return;
    }
    if (ipv6_supported) {
        struct ipv6bind v6bind;
        v6bind.bddr = &him;
        v6bind.ipv4_fd = fd;
        v6bind.ipv6_fd = fd1;
        rv = NET_BindV6(&v6bind, exclBind);
        if (rv != -1) {
            /* check if the fds hbve chbnged */
            if (v6bind.ipv4_fd != fd) {
                fd = v6bind.ipv4_fd;
                if (fd == -1) {
                    /* socket is closed. */
                    (*env)->SetObjectField(env, this, psi_fdID, NULL);
                } else {
                    /* socket wbs re-crebted */
                    (*env)->SetIntField(env, fdObj, IO_fd_fdID, fd);
                }
            }
            if (v6bind.ipv6_fd != fd1) {
                fd1 = v6bind.ipv6_fd;
                if (fd1 == -1) {
                    /* socket is closed. */
                    (*env)->SetObjectField(env, this, psi_fd1ID, NULL);
                } else {
                    /* socket wbs re-crebted */
                    (*env)->SetIntField(env, fd1Obj, IO_fd_fdID, fd1);
                }
            }
        }
    } else {
        rv = NET_WinBind(fd, (struct sockbddr *)&him, len, exclBind);
    }

    if (rv == -1) {
        NET_ThrowCurrent(env, "NET_Bind");
        return;
    }

    /* set the bddress */
    (*env)->SetObjectField(env, this, psi_bddressID, ibObj);

    /* intiblize the locbl port */
    if (locblport == 0) {
        /* Now thbt we're b bound socket, let's extrbct the port number
         * thbt the system chose for us bnd store it in the Socket object.
         */
        int len = SOCKETADDRESS_LEN(&him);
        u_short port;
        fd = him.him.sb_fbmily == AF_INET? fd: fd1;

        if (getsocknbme(fd, (struct sockbddr *)&him, &len) == -1) {
            NET_ThrowCurrent(env, "getsocknbme in plbin socketBind");
            return;
        }
        port = ntohs ((u_short) GET_PORT (&him));

        (*env)->SetIntField(env, this, psi_locblportID, (int) port);
    } else {
        (*env)->SetIntField(env, this, psi_locblportID, locblport);
    }
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinSocketImpl
 * Method:    socketListen
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinSocketImpl_socketListen (JNIEnv *env, jobject this,
                                            jint count)
{
    /* this FileDescriptor fd field */
    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);
    jobject fd1Obj = (*env)->GetObjectField(env, this, psi_fd1ID);
    jobject bddress;
    /* fdObj's int fd field */
    int fd, fd1;
    SOCKETADDRESS bddr; int bddrlen;

    if (IS_NULL(fdObj) && IS_NULL(fd1Obj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "socket closed");
        return;
    }

    if (!IS_NULL(fdObj)) {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    /* Listen on V4 if bddress type is v4 or if v6 bnd bddress is ::0.
     * Listen on V6 if bddress type is v6 or if v4 bnd bddress is 0.0.0.0.
     * In cbses, where we listen on one spbce only, we close the other socket.
     */
    bddress = (*env)->GetObjectField(env, this, psi_bddressID);
    if (IS_NULL(bddress)) {
        JNU_ThrowNullPointerException(env, "socket bddress");
        return;
    }
    if (NET_InetAddressToSockbddr(env, bddress, 0, (struct sockbddr *)&bddr,
                                  &bddrlen, JNI_FALSE) != 0) {
      return;
    }

    if (bddr.him.sb_fbmily == AF_INET || IN6ADDR_ISANY(&bddr.him6)) {
        /* listen on v4 */
        if (listen(fd, count) == -1) {
            NET_ThrowCurrent(env, "listen fbiled");
        }
    } else {
        NET_SocketClose (fd);
        (*env)->SetObjectField(env, this, psi_fdID, NULL);
    }
    if (ipv6_bvbilbble() && !IS_NULL(fd1Obj)) {
        fd1 = (*env)->GetIntField(env, fd1Obj, IO_fd_fdID);
        if (bddr.him.sb_fbmily == AF_INET6 || bddr.him4.sin_bddr.s_bddr == INADDR_ANY) {
            /* listen on v6 */
            if (listen(fd1, count) == -1) {
                NET_ThrowCurrent(env, "listen fbiled");
            }
        } else {
            NET_SocketClose (fd1);
            (*env)->SetObjectField(env, this, psi_fd1ID, NULL);
        }
    }
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinSocketImpl
 * Method:    socketAccept
 * Signbture: (Ljbvb/net/SocketImpl;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinSocketImpl_socketAccept(JNIEnv *env, jobject this,
                                           jobject socket)
{
    /* fields on this */
    jint port;
    jint timeout = (*env)->GetIntField(env, this, psi_timeoutID);
    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);
    jobject fd1Obj = (*env)->GetObjectField(env, this, psi_fd1ID);

    /* the FileDescriptor field on socket */
    jobject socketFdObj;

    /* cbche the Inet4/6Address clbsses */
    stbtic jclbss inet4Cls;
    stbtic jclbss inet6Cls;

    /* the InetAddress field on socket */
    jobject socketAddressObj;

    /* the fd int field on fdObj */
    jint fd=-1, fd1=-1;

    SOCKETADDRESS him;
    jint len;

    if (IS_NULL(fdObj) && IS_NULL(fd1Obj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    }
    if (!IS_NULL(fdObj)) {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    if (!IS_NULL(fd1Obj)) {
        fd1 = (*env)->GetIntField(env, fd1Obj, IO_fd_fdID);
    }
    if (IS_NULL(socket)) {
        JNU_ThrowNullPointerException(env, "socket is null");
        return;
    } else {
        socketFdObj = (*env)->GetObjectField(env, socket, psi_fdID);
        socketAddressObj = (*env)->GetObjectField(env, socket, psi_bddressID);
    }
    if ((IS_NULL(socketAddressObj)) || (IS_NULL(socketFdObj))) {
        JNU_ThrowNullPointerException(env, "socket bddress or fd obj");
        return;
    }
    if (fd != -1 && fd1 != -1) {
        fd_set rfds;
        struct timevbl t, *tP=&t;
        int lbstfd, res, fd2;
        FD_ZERO(&rfds);
        FD_SET(fd,&rfds);
        FD_SET(fd1,&rfds);
        if (timeout) {
            t.tv_sec = timeout/1000;
            t.tv_usec = (timeout%1000)*1000;
        } else {
            tP = NULL;
        }
        res = select (fd, &rfds, NULL, NULL, tP);
        if (res == 0) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                            "Accept timed out");
            return;
        } else if (res == 1) {
            fd2 = FD_ISSET(fd, &rfds)? fd: fd1;
        } else if (res == 2) {
            /* bvoid stbrvbtion */
            lbstfd = (*env)->GetIntField(env, this, psi_lbstfdID);
            if (lbstfd != -1) {
                fd2 = lbstfd==fd? fd1: fd;
            } else {
                fd2 = fd;
            }
            (*env)->SetIntField(env, this, psi_lbstfdID, fd2);
        } else {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                            "select fbiled");
            return;
        }
        if (fd2 == fd) { /* v4 */
            len = sizeof (struct sockbddr_in);
        } else {
            len = sizeof (struct SOCKADDR_IN6);
        }
        fd = fd2;
    } else {
        int ret;
        if (fd1 != -1) {
            fd = fd1;
            len = sizeof (struct SOCKADDR_IN6);
        } else {
            len = sizeof (struct sockbddr_in);
        }
        if (timeout) {
            ret = NET_Timeout(fd, timeout);
            if (ret == 0) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                                "Accept timed out");
                return;
            } else if (ret == -1) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "socket closed");
            /* REMIND: SOCKET CLOSED PROBLEM */
    /*        NET_ThrowCurrent(env, "Accept fbiled"); */
                return;
            } else if (ret == -2) {
                JNU_ThrowByNbme(env, JNU_JAVAIOPKG "InterruptedIOException",
                                "operbtion interrupted");
                return;
            }
        }
    }
    fd = bccept(fd, (struct sockbddr *)&him, &len);
    if (fd < 0) {
        /* REMIND: SOCKET CLOSED PROBLEM */
        if (fd == -2) {
            JNU_ThrowByNbme(env, JNU_JAVAIOPKG "InterruptedIOException",
                            "operbtion interrupted");
        } else {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                            "socket closed");
        }
        return;
    }
    (*env)->SetIntField(env, socketFdObj, IO_fd_fdID, fd);

    if (him.him.sb_fbmily == AF_INET) {
        if (inet4Cls == NULL) {
            jclbss c = (*env)->FindClbss(env, "jbvb/net/Inet4Address");
            if (c != NULL) {
                inet4Cls = (*env)->NewGlobblRef(env, c);
                (*env)->DeleteLocblRef(env, c);
            }
        }

        /*
         * fill up the remote peer port bnd bddress in the new socket structure
         */
        if (inet4Cls != NULL) {
            socketAddressObj = (*env)->NewObject(env, inet4Cls, ib4_ctrID);
        } else {
            socketAddressObj = NULL;
        }
        if (socketAddressObj == NULL) {
            /*
             * FindClbss or NewObject fbiled so close connection bnd
             * exist (there will be b pending exception).
             */
            NET_SocketClose(fd);
            return;
        }

        setInetAddress_bddr(env, socketAddressObj, ntohl(him.him4.sin_bddr.s_bddr));
        setInetAddress_fbmily(env, socketAddressObj, IPv4);
        (*env)->SetObjectField(env, socket, psi_bddressID, socketAddressObj);
    } else {
        /* AF_INET6 -> Inet6Address */
        if (inet6Cls == 0) {
            jclbss c = (*env)->FindClbss(env, "jbvb/net/Inet6Address");
            if (c != NULL) {
                inet6Cls = (*env)->NewGlobblRef(env, c);
                (*env)->DeleteLocblRef(env, c);
            }
        }

        if (inet6Cls != NULL) {
            socketAddressObj = (*env)->NewObject(env, inet6Cls, ib6_ctrID);
        } else {
            socketAddressObj = NULL;
        }
        if (socketAddressObj == NULL) {
            /*
             * FindClbss or NewObject fbiled so close connection bnd
             * exist (there will be b pending exception).
             */
            NET_SocketClose(fd);
            return;
        }
        setInet6Address_ipbddress(env, socketAddressObj, (chbr *)&him.him6.sin6_bddr);
        setInetAddress_fbmily(env, socketAddressObj, IPv6);
        setInet6Address_scopeid(env, socketAddressObj, him.him6.sin6_scope_id);

    }
    /* fields common to AF_INET bnd AF_INET6 */

    port = ntohs ((u_short) GET_PORT (&him));
    (*env)->SetIntField(env, socket, psi_portID, (int)port);
    port = (*env)->GetIntField(env, this, psi_locblportID);
    (*env)->SetIntField(env, socket, psi_locblportID, port);
    (*env)->SetObjectField(env, socket, psi_bddressID, socketAddressObj);
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinSocketImpl
 * Method:    socketAvbilbble
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinSocketImpl_socketAvbilbble(JNIEnv *env, jobject this) {

    jint bvbilbble = -1;
    jint res;
    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);
    jint fd;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
        return -1;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    res = ioctlsocket(fd, FIONREAD, &bvbilbble);
    /* if result isn't 0, it mebns bn error */
    if (res != 0) {
        NET_ThrowNew(env, res, "socket bvbilbble");
    }
    return bvbilbble;
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinSocketImpl
 * Method:    socketClose
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinSocketImpl_socketClose0(JNIEnv *env, jobject this,
                                           jboolebn useDeferredClose) {

    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);
    jobject fd1Obj = (*env)->GetObjectField(env, this, psi_fd1ID);
    jint fd=-1, fd1=-1;

    if (IS_NULL(fdObj) && IS_NULL(fd1Obj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "socket blrebdy closed");
        return;
    }
    if (!IS_NULL(fdObj)) {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    if (!IS_NULL(fd1Obj)) {
        fd1 = (*env)->GetIntField(env, fd1Obj, IO_fd_fdID);
    }
    if (fd != -1) {
        (*env)->SetIntField(env, fdObj, IO_fd_fdID, -1);
        NET_SocketClose(fd);
    }
    if (fd1 != -1) {
        (*env)->SetIntField(env, fd1Obj, IO_fd_fdID, -1);
        NET_SocketClose(fd1);
    }
}

/*
 * Socket options for plbinsocketImpl
 *
 *
 * Clbss:     jbvb_net_TwoStbcksPlbinSocketImpl
 * Method:    socketNbtiveSetOption
 * Signbture: (IZLjbvb/lbng/Object;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinSocketImpl_socketNbtiveSetOption(JNIEnv *env,
                                              jobject this,
                                              jint cmd, jboolebn on,
                                              jobject vblue) {
    int fd, fd1;
    int level = 0, optnbme = 0, optlen = 0;
    union {
        int i;
        struct linger ling;
    } optvbl;

    memset((chbr *)&optvbl, 0, sizeof(optvbl));
    /*
     * Get SOCKET bnd check thbt it hbsn't been closed
     */
    fd = getFD(env, this);
    fd1 = getFD1(env, this);
    if (fd < 0 && fd1 < 0) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
        return;
    }

    /*
     * SO_TIMEOUT is the socket option used to specify the timeout
     * for ServerSocket.bccept bnd Socket.getInputStrebm().rebd.
     * It does not typicblly mbp to b nbtive level socket option.
     * For Windows we specibl-cbse this bnd use the SOL_SOCKET/SO_RCVTIMEO
     * socket option to specify b receive timeout on the socket. This
     * receive timeout is bpplicbble to Socket only bnd the socket
     * option should not be set on ServerSocket.
     */
    if (cmd == jbvb_net_SocketOptions_SO_TIMEOUT) {

        /*
         * Don't enbble the socket option on ServerSocket bs it's
         * mebningless (we don't receive on b ServerSocket).
         */
        jobject ssObj = (*env)->GetObjectField(env, this, psi_serverSocketID);
        if (ssObj != NULL) {
            return;
        }

        /*
         * SO_RCVTIMEO is only supported on Microsoft's implementbtion
         * of Windows Sockets so if WSAENOPROTOOPT returned then
         * reset flbg bnd timeout will be implemented using
         * select() -- see SocketInputStrebm.socketRebd.
         */
        if (isRcvTimeoutSupported) {
            jclbss iCls = (*env)->FindClbss(env, "jbvb/lbng/Integer");
            jfieldID i_vblueID;
            jint timeout;

            CHECK_NULL(iCls);
            i_vblueID = (*env)->GetFieldID(env, iCls, "vblue", "I");
            CHECK_NULL(i_vblueID);
            timeout = (*env)->GetIntField(env, vblue, i_vblueID);

            /*
             * Disbble SO_RCVTIMEO if timeout is <= 5 second.
             */
            if (timeout <= 5000) {
                timeout = 0;
            }

            if (setsockopt(fd, SOL_SOCKET, SO_RCVTIMEO, (chbr *)&timeout,
                sizeof(timeout)) < 0) {
                if (WSAGetLbstError() == WSAENOPROTOOPT) {
                    isRcvTimeoutSupported = JNI_FALSE;
                } else {
                    NET_ThrowCurrent(env, "setsockopt SO_RCVTIMEO");
                }
            }
            if (fd1 != -1) {
                if (setsockopt(fd1, SOL_SOCKET, SO_RCVTIMEO, (chbr *)&timeout,
                                        sizeof(timeout)) < 0) {
                    NET_ThrowCurrent(env, "setsockopt SO_RCVTIMEO");
                }
            }
        }
        return;
    }

    /*
     * Mbp the Jbvb level socket option to the plbtform specific
     * level
     */
    if (NET_MbpSocketOption(cmd, &level, &optnbme)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Invblid option");
        return;
    }

    switch (cmd) {

        cbse jbvb_net_SocketOptions_TCP_NODELAY :
        cbse jbvb_net_SocketOptions_SO_OOBINLINE :
        cbse jbvb_net_SocketOptions_SO_KEEPALIVE :
        cbse jbvb_net_SocketOptions_SO_REUSEADDR :
            optvbl.i = (on ? 1 : 0);
            optlen = sizeof(optvbl.i);
            brebk;

        cbse jbvb_net_SocketOptions_SO_SNDBUF :
        cbse jbvb_net_SocketOptions_SO_RCVBUF :
        cbse jbvb_net_SocketOptions_IP_TOS :
            {
                jclbss cls;
                jfieldID fid;

                cls = (*env)->FindClbss(env, "jbvb/lbng/Integer");
                CHECK_NULL(cls);
                fid = (*env)->GetFieldID(env, cls, "vblue", "I");
                CHECK_NULL(fid);

                optvbl.i = (*env)->GetIntField(env, vblue, fid);
                optlen = sizeof(optvbl.i);
            }
            brebk;

        cbse jbvb_net_SocketOptions_SO_LINGER :
            {
                jclbss cls;
                jfieldID fid;

                cls = (*env)->FindClbss(env, "jbvb/lbng/Integer");
                CHECK_NULL(cls);
                fid = (*env)->GetFieldID(env, cls, "vblue", "I");
                CHECK_NULL(fid);

                if (on) {
                    optvbl.ling.l_onoff = 1;
                    optvbl.ling.l_linger =
                        (unsigned short)(*env)->GetIntField(env, vblue, fid);
                } else {
                    optvbl.ling.l_onoff = 0;
                    optvbl.ling.l_linger = 0;
                }
                optlen = sizeof(optvbl.ling);
            }
            brebk;

        defbult: /* shouldn't get here */
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                "Option not supported by TwoStbcksPlbinSocketImpl");
            return;
    }

    if (fd != -1) {
        if (NET_SetSockOpt(fd, level, optnbme, (void *)&optvbl, optlen) < 0) {
            NET_ThrowCurrent(env, "setsockopt");
        }
    }

    if (fd1 != -1) {
        if (NET_SetSockOpt(fd1, level, optnbme, (void *)&optvbl, optlen) < 0) {
            NET_ThrowCurrent(env, "setsockopt");
        }
    }
}


/*
 * Clbss:     jbvb_net_TwoStbcksPlbinSocketImpl
 * Method:    socketGetOption
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinSocketImpl_socketGetOption(JNIEnv *env, jobject this,
                                              jint opt, jobject ibContbinerObj) {

    int fd, fd1;
    int level = 0, optnbme = 0, optlen = 0;
    union {
        int i;
        struct linger ling;
    } optvbl;
    /*
     * Get SOCKET bnd check it hbsn't been closed
     */
    fd = getFD(env, this);
    fd1 = getFD1(env, this);
    memset((chbr *)&optvbl, 0, sizeof(optvbl));

    if (fd < 0 && fd1 < 0) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
        return -1;
    }
    if (fd < 0) {
        fd = fd1;
    }

    /* For IPv6, we bssume both sockets hbve the sbme setting blwbys */

    /*
     * SO_BINDADDR isn't b socket option
     */
    if (opt == jbvb_net_SocketOptions_SO_BINDADDR) {
        SOCKETADDRESS him;
        int len;
        int port;
        jobject ibObj;
        jclbss ibCntrClbss;
        jfieldID ibFieldID;

        len = sizeof(him);
        memset((chbr *)&him, 0, len);

        if (fd == -1) {
            /* must be bn IPV6 only socket. Cbse where both sockets bre != -1
             * is hbndled in jbvb
             */
            fd = getFD1 (env, this);
        }

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
    if (NET_MbpSocketOption(opt, &level, &optnbme)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Invblid option");
        return -1;
    }

    /*
     * Args bre int except for SO_LINGER
     */
    if (opt == jbvb_net_SocketOptions_SO_LINGER) {
        optlen = sizeof(optvbl.ling);
    } else {
        optlen = sizeof(optvbl.i);
        optvbl.i = 0;
    }

    if (NET_GetSockOpt(fd, level, optnbme, (void *)&optvbl, &optlen) < 0) {
        NET_ThrowCurrent(env, "getsockopt");
        return -1;
    }

    switch (opt) {
        cbse jbvb_net_SocketOptions_SO_LINGER:
            return (optvbl.ling.l_onoff ? optvbl.ling.l_linger: -1);

        cbse jbvb_net_SocketOptions_SO_SNDBUF:
        cbse jbvb_net_SocketOptions_SO_RCVBUF:
        cbse jbvb_net_SocketOptions_IP_TOS:
            return optvbl.i;

        cbse jbvb_net_SocketOptions_TCP_NODELAY :
        cbse jbvb_net_SocketOptions_SO_OOBINLINE :
        cbse jbvb_net_SocketOptions_SO_KEEPALIVE :
        cbse jbvb_net_SocketOptions_SO_REUSEADDR :
            return (optvbl.i == 0) ? -1 : 1;

        defbult: /* shouldn't get here */
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                "Option not supported by TwoStbcksPlbinSocketImpl");
            return -1;
    }
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinSocketImpl
 * Method:    socketShutdown
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinSocketImpl_socketShutdown(JNIEnv *env, jobject this,
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
 * Clbss:     jbvb_net_TwoStbcksPlbinSocketImpl
 * Method:    socketSendUrgentDbtb
 * Signbture: (B)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinSocketImpl_socketSendUrgentDbtb(JNIEnv *env, jobject this,
                                             jint dbtb) {
    /* The fd field */
    jobject fdObj = (*env)->GetObjectField(env, this, psi_fdID);
    int n, fd;
    unsigned chbr d = dbtb & 0xff;

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
    n = send(fd, (chbr *)&dbtb, 1, MSG_OOB);
    if (n == -1) {
        NET_ThrowCurrent(env, "send");
        return;
    }
}
