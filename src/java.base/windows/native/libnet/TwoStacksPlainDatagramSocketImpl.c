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
#include <ws2tcpip.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <mblloc.h>
#include <sys/types.h>

#ifndef IPTOS_TOS_MASK
#define IPTOS_TOS_MASK 0x1e
#endif
#ifndef IPTOS_PREC_MASK
#define IPTOS_PREC_MASK 0xe0
#endif

#include "jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl.h"
#include "jbvb_net_SocketOptions.h"
#include "jbvb_net_NetworkInterfbce.h"

#include "NetworkInterfbce.h"
#include "jvm.h"
#include "jni_util.h"
#include "net_util.h"

#define IN_CLASSD(i)    (((long)(i) & 0xf0000000) == 0xe0000000)
#define IN_MULTICAST(i) IN_CLASSD(i)

/************************************************************************
 * TwoStbcksPlbinDbtbgrbmSocketImpl
 */

stbtic jfieldID IO_fd_fdID;
stbtic jfieldID pdsi_trbfficClbssID;
jfieldID pdsi_fdID;
jfieldID pdsi_fd1ID;
jfieldID pdsi_fduseID;
jfieldID pdsi_lbstfdID;
jfieldID pdsi_timeoutID;

jfieldID pdsi_locblPortID;
jfieldID pdsi_connected;

stbtic jclbss ib4_clbzz;
stbtic jmethodID ib4_ctor;

stbtic CRITICAL_SECTION sizeCheckLock;

/* Windows OS version is XP or better */
stbtic int xp_or_lbter = 0;
/* Windows OS version is Windows 2000 or better */
stbtic int w2k_or_lbter = 0;

/*
 * Notes bbout UDP/IPV6 on Windows (XP bnd 2003 server):
 *
 * fd blwbys points to the IPv4 fd, bnd fd1 points to the IPv6 fd.
 * Both fds bre used when we bind to b wild-cbrd bddress. When b specific
 * bddress is used, only one of them is used.
 */

/*
 * Returns b jbvb.lbng.Integer bbsed on 'i'
 */
jobject crebteInteger(JNIEnv *env, int i) {
    stbtic jclbss i_clbss;
    stbtic jmethodID i_ctrID;
    stbtic jfieldID i_vblueID;

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
jobject crebteBoolebn(JNIEnv *env, int b) {
    stbtic jclbss b_clbss;
    stbtic jmethodID b_ctrID;
    stbtic jfieldID b_vblueID;

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


stbtic int getFD(JNIEnv *env, jobject this) {
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);

    if (fdObj == NULL) {
        return -1;
    }
    return (*env)->GetIntField(env, fdObj, IO_fd_fdID);
}

stbtic int getFD1(JNIEnv *env, jobject this) {
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fd1ID);

    if (fdObj == NULL) {
        return -1;
    }
    return (*env)->GetIntField(env, fdObj, IO_fd_fdID);
}

/*
 * This function returns JNI_TRUE if the dbtbgrbm size exceeds the underlying
 * provider's bbility to send to the tbrget bddress. The following OS
 * oddities hbve been observed :-
 *
 * 1. On Windows 95/98 if we try to send b dbtbgrbm > 12k to bn bpplicbtion
 *    on the sbme mbchine then the send will fbil silently.
 *
 * 2. On Windows ME if we try to send b dbtbgrbm > supported by underlying
 *    provider then send will not return bn error.
 *
 * 3. On Windows NT/2000 if we exceeds the mbximum size then send will fbil
 *    with WSAEADDRNOTAVAIL.
 *
 * 4. On Windows 95/98 if we exceed the mbximum size when sending to
 *    bnother mbchine then WSAEINVAL is returned.
 *
 */
jboolebn exceedSizeLimit(JNIEnv *env, jint fd, jint bddr, jint size)
{
#define DEFAULT_MSG_SIZE        65527
    stbtic jboolebn initDone;
    stbtic jboolebn is95or98;
    stbtic int mbxmsg;

    typedef struct _netbddr  {          /* Windows 95/98 only */
        unsigned long bddr;
        struct _netbddr *next;
    } netbddr;
    stbtic netbddr *bddrList;
    netbddr *curr;

    /*
     * First time we bre cblled we must determine which OS this is bnd blso
     * get the mbximum size supported by the underlying provider.
     *
     * In bddition on 95/98 we must enumerbte our IP bddresses.
     */
    if (!initDone) {
        EnterCriticblSection(&sizeCheckLock);

        if (initDone) {
            /* bnother threbd got there first */
            LebveCriticblSection(&sizeCheckLock);

        } else {
            OSVERSIONINFO ver;
            int len;

            /*
             * Step 1: Determine which OS this is.
             */
            ver.dwOSVersionInfoSize = sizeof(ver);
            GetVersionEx(&ver);

            is95or98 = JNI_FALSE;
            if (ver.dwPlbtformId == VER_PLATFORM_WIN32_WINDOWS &&
                ver.dwMbjorVersion == 4 &&
                (ver.dwMinorVersion == 0 || ver.dwMinorVersion == 10)) {

                is95or98 = JNI_TRUE;
            }

            /*
             * Step 2: Determine the mbximum dbtbgrbm supported by the
             * underlying provider. On Windows 95 if winsock hbsn't been
             * upgrbded (ie: unsupported configurbtion) then we bssume
             * the defbult 64k limit.
             */
            len = sizeof(mbxmsg);
            if (NET_GetSockOpt(fd, SOL_SOCKET, SO_MAX_MSG_SIZE, (chbr *)&mbxmsg, &len) < 0) {
                mbxmsg = DEFAULT_MSG_SIZE;
            }

            /*
             * Step 3: On Windows 95/98 then enumerbte the IP bddresses on
             * this mbchine. This is neccesbry becbuse we need to check if the
             * dbtbgrbm is being sent to bn bpplicbtion on the sbme mbchine.
             */
            if (is95or98) {
                chbr hostnbme[255];
                struct hostent *hp;

                if (gethostnbme(hostnbme, sizeof(hostnbme)) == -1) {
                    LebveCriticblSection(&sizeCheckLock);
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Unbble to obtbin hostnbme");
                    return JNI_TRUE;
                }
                hp = (struct hostent *)gethostbynbme(hostnbme);
                if (hp != NULL) {
                    struct in_bddr **bddrp = (struct in_bddr **) hp->h_bddr_list;

                    while (*bddrp != (struct in_bddr *) 0) {
                        curr = (netbddr *)mblloc(sizeof(netbddr));
                        if (curr == NULL) {
                            while (bddrList != NULL) {
                                curr = bddrList->next;
                                free(bddrList);
                                bddrList = curr;
                            }
                            LebveCriticblSection(&sizeCheckLock);
                            JNU_ThrowOutOfMemoryError(env, "Nbtive hebp bllocbtion fbiled");
                            return JNI_TRUE;
                        }
                        curr->bddr = htonl((*bddrp)->S_un.S_bddr);
                        curr->next = bddrList;
                        bddrList = curr;
                        bddrp++;
                    }
                }
            }

            /*
             * Step 4: initiblizbtion is done so set flbg bnd unlock cs
             */
            initDone = JNI_TRUE;
            LebveCriticblSection(&sizeCheckLock);
        }
    }

    /*
     * Now exbmine the size of the dbtbgrbm :-
     *
     * (b) If exceeds size of service provider return 'fblse' to indicbte thbt
     *     we exceed the limit.
     * (b) If not 95/98 then return 'true' to indicbte thbt the size is okby.
     * (c) On 95/98 if the size is <12k we bre okby.
     * (d) On 95/98 if size > 12k then check if the destinbtion is the current
     *     mbchine.
     */
    if (size > mbxmsg) {        /* step (b) */
        return JNI_TRUE;
    }
    if (!is95or98) {            /* step (b) */
        return JNI_FALSE;
    }
    if (size <= 12280) {        /* step (c) */
        return JNI_FALSE;
    }

    /* step (d) */

    if ((bddr & 0x7f000000) == 0x7f000000) {
        return JNI_TRUE;
    }
    curr = bddrList;
    while (curr != NULL) {
        if (curr->bddr == bddr) {
            return JNI_TRUE;
        }
        curr = curr->next;
    }
    return JNI_FALSE;
}

/*
 * Return JNI_TRUE if this Windows edition supports ICMP Port Unrebchbble
 */
__inline stbtic jboolebn supportPortUnrebchbble() {
    stbtic jboolebn initDone;
    stbtic jboolebn portUnrebchbbleSupported;

    if (!initDone) {
        OSVERSIONINFO ver;
        ver.dwOSVersionInfoSize = sizeof(ver);
        GetVersionEx(&ver);
        if (ver.dwPlbtformId == VER_PLATFORM_WIN32_NT && ver.dwMbjorVersion >= 5) {
            portUnrebchbbleSupported = JNI_TRUE;
        } else {
            portUnrebchbbleSupported = JNI_FALSE;
        }
        initDone = JNI_TRUE;
    }
    return portUnrebchbbleSupported;
}

/*
 * This function "purges" bll outstbnding ICMP port unrebchbble pbckets
 * outstbnding on b socket bnd returns JNI_TRUE if bny ICMP messbges
 * hbve been purged. The rbtionbl for purging is to emulbte normbl BSD
 * behbviour whereby receiving b "connection reset" stbtus resets the
 * socket.
 */
stbtic jboolebn purgeOutstbndingICMP(JNIEnv *env, jobject this, jint fd)
{
    jboolebn got_icmp = JNI_FALSE;
    chbr buf[1];
    fd_set tbl;
    struct timevbl t = { 0, 0 };
    SOCKETADDRESS rmtbddr;
    int bddrlen = sizeof(rmtbddr);

    memset((chbr *)&rmtbddr, 0, sizeof(rmtbddr));

    /*
     * A no-op if this OS doesn't support it.
     */
    if (!supportPortUnrebchbble()) {
        return JNI_FALSE;
    }

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
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    init
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_init(JNIEnv *env, jclbss cls) {

    OSVERSIONINFO ver;
    int version;
    ver.dwOSVersionInfoSize = sizeof(ver);
    GetVersionEx(&ver);

    version = ver.dwMbjorVersion * 10 + ver.dwMinorVersion;
    xp_or_lbter = (ver.dwPlbtformId == VER_PLATFORM_WIN32_NT) && (version >= 51);
    w2k_or_lbter = (ver.dwPlbtformId == VER_PLATFORM_WIN32_NT) && (version >= 50);

    /* get fieldIDs */
    pdsi_fdID = (*env)->GetFieldID(env, cls, "fd", "Ljbvb/io/FileDescriptor;");
    CHECK_NULL(pdsi_fdID);
    pdsi_fd1ID = (*env)->GetFieldID(env, cls, "fd1", "Ljbvb/io/FileDescriptor;");
    CHECK_NULL(pdsi_fd1ID);
    pdsi_timeoutID = (*env)->GetFieldID(env, cls, "timeout", "I");
    CHECK_NULL(pdsi_timeoutID);
    pdsi_fduseID = (*env)->GetFieldID(env, cls, "fduse", "I");
    CHECK_NULL(pdsi_fduseID);
    pdsi_lbstfdID = (*env)->GetFieldID(env, cls, "lbstfd", "I");
    CHECK_NULL(pdsi_lbstfdID);
    pdsi_trbfficClbssID = (*env)->GetFieldID(env, cls, "trbfficClbss", "I");
    CHECK_NULL(pdsi_trbfficClbssID);
    pdsi_locblPortID = (*env)->GetFieldID(env, cls, "locblPort", "I");
    CHECK_NULL(pdsi_locblPortID);
    pdsi_connected = (*env)->GetFieldID(env, cls, "connected", "Z");
    CHECK_NULL(pdsi_connected);

    cls = (*env)->FindClbss(env, "jbvb/io/FileDescriptor");
    CHECK_NULL(cls);
    IO_fd_fdID = NET_GetFileDescriptorID(env);
    CHECK_NULL(IO_fd_fdID);

    ib4_clbzz = (*env)->FindClbss(env, "jbvb/net/Inet4Address");
    CHECK_NULL(ib4_clbzz);
    ib4_clbzz = (*env)->NewGlobblRef(env, ib4_clbzz);
    CHECK_NULL(ib4_clbzz);
    ib4_ctor = (*env)->GetMethodID(env, ib4_clbzz, "<init>", "()V");
    CHECK_NULL(ib4_ctor);


    InitiblizeCriticblSection(&sizeCheckLock);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_bind0(JNIEnv *env, jobject this,
                                           jint port, jobject bddressObj,
                                           jboolebn exclBind) {
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jobject fd1Obj = (*env)->GetObjectField(env, this, pdsi_fd1ID);

    int fd, fd1, fbmily;
    int ipv6_supported = ipv6_bvbilbble();

    SOCKETADDRESS lclbddr;
    int lclbddrlen = sizeof(lclbddr);
    int bddress;

    memset((chbr *)&lclbddr, 0, sizeof(lclbddr));

    fbmily = getInetAddress_fbmily(env, bddressObj);
    if (fbmily == IPv6 && !ipv6_supported) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Protocol fbmily not supported");
        return;
    }

    if (IS_NULL(fdObj) || (ipv6_supported && IS_NULL(fd1Obj))) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "socket closed");
        return;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
        if (ipv6_supported) {
            fd1 = (*env)->GetIntField(env, fd1Obj, IO_fd_fdID);
        }
    }
    if (IS_NULL(bddressObj)) {
        JNU_ThrowNullPointerException(env, "brgument bddress");
        return;
    } else {
        bddress = getInetAddress_bddr(env, bddressObj);
    }

    if (NET_InetAddressToSockbddr(env, bddressObj, port, (struct sockbddr *)&lclbddr, &lclbddrlen, JNI_FALSE) != 0) {
      return;
    }

    if (ipv6_supported) {
        struct ipv6bind v6bind;
        v6bind.bddr = &lclbddr;
        v6bind.ipv4_fd = fd;
        v6bind.ipv6_fd = fd1;
        if (NET_BindV6(&v6bind, exclBind) != -1) {
            /* check if the fds hbve chbnged */
            if (v6bind.ipv4_fd != fd) {
                fd = v6bind.ipv4_fd;
                if (fd == -1) {
                    /* socket is closed. */
                    (*env)->SetObjectField(env, this, pdsi_fdID, NULL);
                } else {
                    /* socket wbs re-crebted */
                    (*env)->SetIntField(env, fdObj, IO_fd_fdID, fd);
                }
            }
            if (v6bind.ipv6_fd != fd1) {
                fd1 = v6bind.ipv6_fd;
                if (fd1 == -1) {
                    /* socket is closed. */
                    (*env)->SetObjectField(env, this, pdsi_fd1ID, NULL);
                } else {
                    /* socket wbs re-crebted */
                    (*env)->SetIntField(env, fd1Obj, IO_fd_fdID, fd1);
                }
            }
        } else {
            NET_ThrowCurrent (env, "Cbnnot bind");
            return;
        }
    } else {
        if (NET_WinBind(fd, (struct sockbddr *)&lclbddr, lclbddrlen, exclBind) == -1) {
            if (WSAGetLbstError() == WSAEACCES) {
                WSASetLbstError(WSAEADDRINUSE);
            }
            NET_ThrowCurrent(env, "Cbnnot bind");
            return;
        }
    }

    if (port == 0) {
        if (fd == -1) {
            /* must be bn IPV6 only socket. */
            fd = fd1;
        }
        if (getsocknbme(fd, (struct sockbddr *)&lclbddr, &lclbddrlen) == -1) {
            NET_ThrowCurrent(env, "getsocknbme");
            return;
        }
        port = ntohs((u_short) GET_PORT (&lclbddr));
    }
    (*env)->SetIntField(env, this, pdsi_locblPortID, port);
}


/*
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    connect0
 * Signbture: (Ljbvb/net/InetAddress;I)V
 */

JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_connect0(JNIEnv *env, jobject this,
                                               jobject bddress, jint port) {
    /* The object's field */
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jobject fd1Obj = (*env)->GetObjectField(env, this, pdsi_fd1ID);
    /* The fdObj'fd */
    jint fd=-1, fd1=-1, fdc;
    /* The pbcketAddress bddress, fbmily bnd port */
    jint bddr, fbmily;
    SOCKETADDRESS rmtbddr;
    int rmtbddrlen;
    int ipv6_supported = ipv6_bvbilbble();

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

    if (IS_NULL(bddress)) {
        JNU_ThrowNullPointerException(env, "bddress");
        return;
    }

    bddr = getInetAddress_bddr(env, bddress);

    fbmily = getInetAddress_fbmily(env, bddress);
    if (fbmily == IPv6 && !ipv6_supported) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Protocol fbmily not supported");
        return;
    }

    fdc = fbmily == IPv4? fd: fd1;

    if (xp_or_lbter) {
        /* SIO_UDP_CONNRESET fixes b bug introduced in Windows 2000, which
         * returns connection reset errors on connected UDP sockets (bs well
         * bs connected sockets). The solution is to only enbble this febture
         * when the socket is connected
         */
        DWORD x1, x2; /* ignored result codes */
        int res, t = TRUE;
        res = WSAIoctl(fdc,SIO_UDP_CONNRESET,&t,sizeof(t),&x1,sizeof(x1),&x2,0,0);
    }

    if (NET_InetAddressToSockbddr(env, bddress, port,(struct sockbddr *)&rmtbddr, &rmtbddrlen, JNI_FALSE) != 0) {
      return;
    }

    if (connect(fdc, (struct sockbddr *)&rmtbddr, sizeof(rmtbddr)) == -1) {
        NET_ThrowCurrent(env, "connect");
        return;
    }
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    disconnect0
 * Signbture: ()V
 */

JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_disconnect0(JNIEnv *env, jobject this, jint fbmily) {
    /* The object's field */
    jobject fdObj;
    /* The fdObj'fd */
    jint fd, len;
    SOCKETADDRESS bddr;

    if (fbmily == IPv4) {
        fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
        len = sizeof (struct sockbddr_in);
    } else {
        fdObj = (*env)->GetObjectField(env, this, pdsi_fd1ID);
        len = sizeof (struct SOCKADDR_IN6);
    }

    if (IS_NULL(fdObj)) {
        /* disconnect doesn't throw bny exceptions */
        return;
    }
    fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);

    memset((chbr *)&bddr, 0, len);
    connect(fd, (struct sockbddr *)&bddr, len);

    /*
     * use SIO_UDP_CONNRESET
     * to disbble ICMP port unrebchbble hbndling here.
     */
    if (xp_or_lbter) {
        DWORD x1 = 0, x2 = 0; /* ignored result codes */
        int t = FALSE;
        WSAIoctl(fd,SIO_UDP_CONNRESET,&t,sizeof(t),&x1,sizeof(x1),&x2,0,0);
    }
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    send
 * Signbture: (Ljbvb/net/DbtbgrbmPbcket;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_send(JNIEnv *env, jobject this,
                                           jobject pbcket) {

    chbr BUF[MAX_BUFFER_LEN];
    chbr *fullPbcket;
    jobject fdObj;
    jint fd;

    jobject ibObj;
    jint bddress;
    jint fbmily;

    jint pbcketBufferOffset, pbcketBufferLen, pbcketPort;
    jbyteArrby pbcketBuffer;
    jboolebn connected;

    SOCKETADDRESS rmtbddr;
    SOCKETADDRESS *bddrp = &rmtbddr;
    int bddrlen = 0;

    memset((chbr *)&rmtbddr, 0, sizeof(rmtbddr));

    if (IS_NULL(pbcket)) {
        JNU_ThrowNullPointerException(env, "null pbcket");
        return;
    }

    ibObj = (*env)->GetObjectField(env, pbcket, dp_bddressID);

    pbcketPort = (*env)->GetIntField(env, pbcket, dp_portID);
    pbcketBufferOffset = (*env)->GetIntField(env, pbcket, dp_offsetID);
    pbcketBuffer = (jbyteArrby)(*env)->GetObjectField(env, pbcket, dp_bufID);
    connected = (*env)->GetBoolebnField(env, this, pdsi_connected);

    if (IS_NULL(ibObj) || IS_NULL(pbcketBuffer)) {
        JNU_ThrowNullPointerException(env, "null bddress || null buffer");
        return;
    }

    fbmily = getInetAddress_fbmily(env, ibObj);
    if (fbmily == IPv4) {
        fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    } else {
        if (!ipv6_bvbilbble()) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Protocol not bllowed");
            return;
        }
        fdObj = (*env)->GetObjectField(env, this, pdsi_fd1ID);
    }

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    }
    fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);

    pbcketBufferLen = (*env)->GetIntField(env, pbcket, dp_lengthID);
    /* Note: the buffer needn't be grebter thbn 65,536 (0xFFFF)...
     * the mbximum size of bn IP pbcket. Anything bigger is truncbted bnywby.
     */
    if (pbcketBufferLen > MAX_PACKET_LEN) {
        pbcketBufferLen = MAX_PACKET_LEN;
    }

    if (connected) {
        bddrp = 0; /* brg to sendto () null in this cbse */
        bddrlen = 0;
    } else {
      if (NET_InetAddressToSockbddr(env, ibObj, pbcketPort, (struct sockbddr *)&rmtbddr, &bddrlen, JNI_FALSE) != 0) {
        return;
      }
    }

    if (pbcketBufferLen > MAX_BUFFER_LEN) {

        /*
         * On 95/98 if we try to send b dbtbgrbm >12k to bn bpplicbtion
         * on the sbme mbchine then this will fbil silently. Thus we
         * cbtch this situbtion here so thbt we cbn throw bn exception
         * when this brises.
         * On ME if we try to send b dbtbgrbm with b size grebter thbn
         * thbt supported by the service provider then no error is
         * returned.
         */
        if (!w2k_or_lbter) { /* bvoid this check on Win 2K or better. Does not work with IPv6.
                      * Check is not necessbry on these OSes */
            if (connected) {
                bddress = getInetAddress_bddr(env, ibObj);
            } else {
                bddress = ntohl(rmtbddr.him4.sin_bddr.s_bddr);
            }

            if (exceedSizeLimit(env, fd, bddress, pbcketBufferLen)) {
                if (!((*env)->ExceptionOccurred(env))) {
                    NET_ThrowNew(env, WSAEMSGSIZE, "Dbtbgrbm send fbiled");
                }
                return;
            }
        }

        /* When JNI-ifying the JDK's IO routines, we turned
         * rebds bnd writes of byte brrbys of size grebter
         * thbn 2048 bytes into severbl operbtions of size 2048.
         * This sbves b mblloc()/memcpy()/free() for big
         * buffers.  This is OK for file IO bnd TCP, but thbt
         * strbtegy violbtes the sembntics of b dbtbgrbm protocol.
         * (one big send) != (severbl smbller sends).  So here
         * we *must* blloc the buffer.  Note it needn't be bigger
         * thbn 65,536 (0xFFFF) the mbx size of bn IP pbcket.
         * bnything bigger is truncbted bnywby.
         */
        fullPbcket = (chbr *)mblloc(pbcketBufferLen);
        if (!fullPbcket) {
            JNU_ThrowOutOfMemoryError(env, "Send buf nbtive hebp bllocbtion fbiled");
            return;
        }
    } else {
        fullPbcket = &(BUF[0]);
    }

    (*env)->GetByteArrbyRegion(env, pbcketBuffer, pbcketBufferOffset, pbcketBufferLen,
                               (jbyte *)fullPbcket);
    if (sendto(fd, fullPbcket, pbcketBufferLen, 0,
               (struct sockbddr *)bddrp, bddrlen) == SOCKET_ERROR) {
         NET_ThrowCurrent(env, "Dbtbgrbm send fbiled");
    }

    if (pbcketBufferLen > MAX_BUFFER_LEN) {
        free(fullPbcket);
    }
}

/*
 * check which socket wbs lbst serviced when there wbs dbtb on both sockets.
 * Only cbll this if sure thbt there is dbtb on both sockets.
 */
stbtic int checkLbstFD (JNIEnv *env, jobject this, int fd, int fd1) {
    int nextfd, lbstfd = (*env)->GetIntField(env, this, pdsi_lbstfdID);
    if (lbstfd == -1) {
        /* brbitrbry. Choose fd */
        (*env)->SetIntField(env, this, pdsi_lbstfdID, fd);
        return fd;
    } else {
        if (lbstfd == fd) {
            nextfd = fd1;
        } else {
            nextfd = fd;
        }
        (*env)->SetIntField(env, this, pdsi_lbstfdID, nextfd);
        return nextfd;
    }
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    peek
 * Signbture: (Ljbvb/net/InetAddress;)I
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_peek(JNIEnv *env, jobject this,
                                           jobject bddressObj) {

    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jint timeout = (*env)->GetIntField(env, this, pdsi_timeoutID);
    jint fd;

    /* The bddress bnd fbmily fields of bddressObj */
    jint bddress, fbmily;

    int n;
    struct sockbddr_in remote_bddr;
    jint remote_bddrsize = sizeof (remote_bddr);
    chbr buf[1];
    BOOL retry;
    jlong prevTime = 0;

    if (IS_NULL(fdObj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
        return -1;
    } else {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
        if (fd < 0) {
           JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                           "socket closed");
           return -1;
        }
    }
    if (IS_NULL(bddressObj)) {
        JNU_ThrowNullPointerException(env, "Null bddress in peek()");
    } else {
        bddress = getInetAddress_bddr(env, bddressObj);
        /* We only hbndle IPv4 for now. Will support IPv6 once its in the os */
        fbmily = AF_INET;
    }

    do {
        retry = FALSE;

        /*
         * If b timeout hbs been specified then we select on the socket
         * wbiting for b rebd event or b timeout.
         */
        if (timeout) {
            int ret;
            prevTime = JVM_CurrentTimeMillis(env, 0);
            ret = NET_Timeout (fd, timeout);
            if (ret == 0) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                                "Peek timed out");
                return ret;
            } else if (ret == -1) {
                NET_ThrowCurrent(env, "timeout in dbtbgrbm socket peek");
                return ret;
            }
        }

        /* now try the peek */
        n = recvfrom(fd, buf, 1, MSG_PEEK,
                         (struct sockbddr *)&remote_bddr, &remote_bddrsize);

        if (n == SOCKET_ERROR) {
            if (WSAGetLbstError() == WSAECONNRESET) {
                jboolebn connected;

                /*
                 * An icmp port unrebchbble - we must receive this bs Windows
                 * does not reset the stbte of the socket until this hbs been
                 * received.
                 */
                purgeOutstbndingICMP(env, this, fd);

                connected =  (*env)->GetBoolebnField(env, this, pdsi_connected);
                if (connected) {
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "PortUnrebchbbleException",
                                       "ICMP Port Unrebchbble");
                    return 0;
                }

                /*
                 * If b timeout wbs specified then we need to bdjust it becbuse
                 * we mby hbve used up some of the timeout befor the icmp port
                 * unrebchbble brrived.
                 */
                if (timeout) {
                    jlong newTime = JVM_CurrentTimeMillis(env, 0);
                    timeout -= (jint)(newTime - prevTime);
                    if (timeout <= 0) {
                        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                                "Receive timed out");
                        return 0;
                    }
                    prevTime = newTime;
                }

                /* Need to retry the recv */
                retry = TRUE;
            }
        }
    } while (retry);

    if (n == SOCKET_ERROR && WSAGetLbstError() != WSAEMSGSIZE) {
        NET_ThrowCurrent(env, "Dbtbgrbm peek fbiled");
        return 0;
    }
    setInetAddress_bddr(env, bddressObj, ntohl(remote_bddr.sin_bddr.s_bddr));
    setInetAddress_fbmily(env, bddressObj, IPv4);

    /* return port */
    return ntohs(remote_bddr.sin_port);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_peekDbtb(JNIEnv *env, jobject this,
                                           jobject pbcket) {

     chbr BUF[MAX_BUFFER_LEN];
    chbr *fullPbcket;
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jobject fd1Obj = (*env)->GetObjectField(env, this, pdsi_fd1ID);
    jint timeout = (*env)->GetIntField(env, this, pdsi_timeoutID);

    jbyteArrby pbcketBuffer;
    jint pbcketBufferOffset, pbcketBufferLen;

    int fd, fd1, fduse, nsockets=0, errorCode;
    int port;

    int checkBoth = 0;
    int n;
    SOCKETADDRESS remote_bddr;
    jint remote_bddrsize=sizeof(remote_bddr);
    BOOL retry;
    jlong prevTime = 0;

    if (!IS_NULL(fdObj)) {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
        if (fd < 0) {
           JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                           "socket closed");
           return -1;
        }
        nsockets = 1;
    }

    if (!IS_NULL(fd1Obj)) {
        fd1 = (*env)->GetIntField(env, fd1Obj, IO_fd_fdID);
        if (fd1 < 0) {
           JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                           "socket closed");
           return -1;
        }
        nsockets ++;
    }

    switch (nsockets) {
      cbse 0:
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                       "socket closed");
        return -1;
      cbse 1:
        if (!IS_NULL(fdObj)) {
           fduse = fd;
        } else {
           fduse = fd1;
        }
        brebk;
      cbse 2:
        checkBoth = TRUE;
        brebk;
    }

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

    if (pbcketBufferLen > MAX_BUFFER_LEN) {

        /* When JNI-ifying the JDK's IO routines, we turned
         * rebd's bnd write's of byte brrbys of size grebter
         * thbn 2048 bytes into severbl operbtions of size 2048.
         * This sbves b mblloc()/memcpy()/free() for big
         * buffers.  This is OK for file IO bnd TCP, but thbt
         * strbtegy violbtes the sembntics of b dbtbgrbm protocol.
         * (one big send) != (severbl smbller sends).  So here
         * we *must* blloc the buffer.  Note it needn't be bigger
         * thbn 65,536 (0xFFFF) the mbx size of bn IP pbcket.
         * bnything bigger is truncbted bnywby.
         */
        fullPbcket = (chbr *)mblloc(pbcketBufferLen);
        if (!fullPbcket) {
            JNU_ThrowOutOfMemoryError(env, "Nbtive hebp bllocbtion fbiled");
            return -1;
        }
    } else {
        fullPbcket = &(BUF[0]);
    }

    do {
        int ret;
        retry = FALSE;

        /*
         * If b timeout hbs been specified then we select on the socket
         * wbiting for b rebd event or b timeout.
         */
        if (checkBoth) {
            int t = timeout == 0 ? -1: timeout;
            prevTime = JVM_CurrentTimeMillis(env, 0);
            ret = NET_Timeout2 (fd, fd1, t, &fduse);
            /* bll subsequent cblls to recv() or select() will use the sbme fd
             * for this cbll to peek() */
            if (ret <= 0) {
                if (ret == 0) {
                    JNU_ThrowByNbme(env,JNU_JAVANETPKG "SocketTimeoutException",
                                        "Peek timed out");
                } else if (ret == -1) {
                    NET_ThrowCurrent(env, "timeout in dbtbgrbm socket peek");
                }
                if (pbcketBufferLen > MAX_BUFFER_LEN) {
                    free(fullPbcket);
                }
                return -1;
            }
            if (ret == 2) {
                fduse = checkLbstFD (env, this, fd, fd1);
            }
            checkBoth = FALSE;
        } else if (timeout) {
            if (prevTime == 0) {
                prevTime = JVM_CurrentTimeMillis(env, 0);
            }
            ret = NET_Timeout (fduse, timeout);
            if (ret <= 0) {
                if (ret == 0) {
                    JNU_ThrowByNbme(env,JNU_JAVANETPKG "SocketTimeoutException",
                                    "Receive timed out");
                } else if (ret == -1) {
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
        n = recvfrom(fduse, fullPbcket, pbcketBufferLen, MSG_PEEK,
                         (struct sockbddr *)&remote_bddr, &remote_bddrsize);
        port = (int) ntohs ((u_short) GET_PORT((SOCKETADDRESS *)&remote_bddr));
        if (n == SOCKET_ERROR) {
            if (WSAGetLbstError() == WSAECONNRESET) {
                jboolebn connected;

                /*
                 * An icmp port unrebchbble - we must receive this bs Windows
                 * does not reset the stbte of the socket until this hbs been
                 * received.
                 */
                purgeOutstbndingICMP(env, this, fduse);

                connected = (*env)->GetBoolebnField(env, this, pdsi_connected);
                if (connected) {
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "PortUnrebchbbleException",
                                       "ICMP Port Unrebchbble");

                    if (pbcketBufferLen > MAX_BUFFER_LEN) {
                        free(fullPbcket);
                    }
                    return -1;
                }

                /*
                 * If b timeout wbs specified then we need to bdjust it becbuse
                 * we mby hbve used up some of the timeout befor the icmp port
                 * unrebchbble brrived.
                 */
                if (timeout) {
                    jlong newTime = JVM_CurrentTimeMillis(env, 0);
                    timeout -= (jint)(newTime - prevTime);
                    if (timeout <= 0) {
                        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                                "Receive timed out");
                        if (pbcketBufferLen > MAX_BUFFER_LEN) {
                            free(fullPbcket);
                        }
                        return -1;
                    }
                    prevTime = newTime;
                }
                retry = TRUE;
            }
        }
    } while (retry);

    /* truncbte the dbtb if the pbcket's length is too smbll */
    if (n > pbcketBufferLen) {
        n = pbcketBufferLen;
    }
    if (n < 0) {
        errorCode = WSAGetLbstError();
        /* check to see if it's becbuse the buffer wbs too smbll */
        if (errorCode == WSAEMSGSIZE) {
            /* it is becbuse the buffer is too smbll. It's UDP, it's
             * unrelibble, it's bll good. discbrd the rest of the
             * dbtb..
             */
            n = pbcketBufferLen;
        } else {
            /* fbilure */
            (*env)->SetIntField(env, pbcket, dp_lengthID, 0);
        }
    }
    if (n == -1) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "socket closed");
    } else if (n == -2) {
        JNU_ThrowByNbme(env, JNU_JAVAIOPKG "InterruptedIOException",
                        "operbtion interrupted");
    } else if (n < 0) {
        NET_ThrowCurrent(env, "Dbtbgrbm receive fbiled");
    } else {
        jobject pbcketAddress;

        /*
         * Check if there is bn InetAddress blrebdy bssocibted with this
         * pbcket. If so we check if it is the sbme source bddress. We
         * cbn't updbte bny existing InetAddress becbuse it is immutbble
         */
        pbcketAddress = (*env)->GetObjectField(env, pbcket, dp_bddressID);
        if (pbcketAddress != NULL) {
            if (!NET_SockbddrEqublsInetAddress(env, (struct sockbddr *)
                                                &remote_bddr, pbcketAddress)) {
                /* force b new InetAddress to be crebted */
                pbcketAddress = NULL;
            }
        }
        if (pbcketAddress == NULL) {
            pbcketAddress = NET_SockbddrToInetAddress(env, (struct sockbddr *)
                                &remote_bddr, &port);
            /* stuff the new Inetbddress in the pbcket */
            (*env)->SetObjectField(env, pbcket, dp_bddressID, pbcketAddress);
        }

        /* populbte the pbcket */
        (*env)->SetByteArrbyRegion(env, pbcketBuffer, pbcketBufferOffset, n,
                                   (jbyte *)fullPbcket);
        (*env)->SetIntField(env, pbcket, dp_portID, port);
        (*env)->SetIntField(env, pbcket, dp_lengthID, n);
    }

    /* mbke sure receive() picks up the right fd */
    (*env)->SetIntField(env, this, pdsi_fduseID, fduse);

    if (pbcketBufferLen > MAX_BUFFER_LEN) {
        free(fullPbcket);
    }
    return port;
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    receive
 * Signbture: (Ljbvb/net/DbtbgrbmPbcket;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_receive0(JNIEnv *env, jobject this,
                                              jobject pbcket) {

    chbr BUF[MAX_BUFFER_LEN];
    chbr *fullPbcket;
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jobject fd1Obj = (*env)->GetObjectField(env, this, pdsi_fd1ID);
    jint timeout = (*env)->GetIntField(env, this, pdsi_timeoutID);
    jbyteArrby pbcketBuffer;
    jint pbcketBufferOffset, pbcketBufferLen;
    int ipv6_supported = ipv6_bvbilbble();

    /* bs b result of the chbnges for ipv6, peek() or peekDbtb()
     * must be cblled prior to receive() so thbt fduse cbn be set.
     */
    int fd, fd1, fduse, errorCode;

    int n, nsockets=0;
    SOCKETADDRESS remote_bddr;
    jint remote_bddrsize=sizeof(remote_bddr);
    BOOL retry;
    jlong prevTime = 0, selectTime=0;
    jboolebn connected;

    if (IS_NULL(fdObj) && IS_NULL(fd1Obj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    }

    if (!IS_NULL(fdObj)) {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
        nsockets ++;
    }
    if (!IS_NULL(fd1Obj)) {
        fd1 = (*env)->GetIntField(env, fd1Obj, IO_fd_fdID);
        nsockets ++;
    }

    if (nsockets == 2) { /* need to choose one of them */
        /* wbs fduse set in peek? */
        fduse = (*env)->GetIntField(env, this, pdsi_fduseID);
        if (fduse == -1) {
            /* not set in peek(), must select on both sockets */
            int ret, t = (timeout == 0) ? -1: timeout;
            ret = NET_Timeout2 (fd, fd1, t, &fduse);
            if (ret == 2) {
                fduse = checkLbstFD (env, this, fd, fd1);
            } else if (ret <= 0) {
                if (ret == 0) {
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                                    "Receive timed out");
                } else if (ret == -1) {
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                                    "Socket closed");
                }
                return;
            }
        }
    } else if (!ipv6_supported) {
        fduse = fd;
    } else if (IS_NULL(fdObj)) {
        /* ipv6 supported: bnd this socket bound to bn IPV6 only bddress */
        fduse = fd1;
    } else {
        /* ipv6 supported: bnd this socket bound to bn IPV4 only bddress */
        fduse = fd;
    }

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
         * rebd's bnd write's of byte brrbys of size grebter
         * thbn 2048 bytes into severbl operbtions of size 2048.
         * This sbves b mblloc()/memcpy()/free() for big
         * buffers.  This is OK for file IO bnd TCP, but thbt
         * strbtegy violbtes the sembntics of b dbtbgrbm protocol.
         * (one big send) != (severbl smbller sends).  So here
         * we *must* blloc the buffer.  Note it needn't be bigger
         * thbn 65,536 (0xFFFF) the mbx size of bn IP pbcket.
         * bnything bigger is truncbted bnywby.
         */
        fullPbcket = (chbr *)mblloc(pbcketBufferLen);
        if (!fullPbcket) {
            JNU_ThrowOutOfMemoryError(env, "Receive buf nbtive hebp bllocbtion fbiled");
            return;
        }
    } else {
        fullPbcket = &(BUF[0]);
    }



    /*
     * If this Windows edition supports ICMP port unrebchbble bnd if we
     * bre not connected then we need to know if b timeout hbs been specified
     * bnd if so we need to pick up the current time. These bre required in
     * order to implement the sembntics of timeout, viz :-
     * timeout set to t1 but ICMP port unrebchbble brrives in t2 where
     * t2 < t1. In this cbse we must discbrd the ICMP pbckets bnd then
     * wbit for the next pbcket up to b mbximum of t1 minus t2.
     */
    connected = (*env)->GetBoolebnField(env, this, pdsi_connected);
    if (supportPortUnrebchbble() && !connected && timeout &&!ipv6_supported) {
        prevTime = JVM_CurrentTimeMillis(env, 0);
    }

    if (timeout && nsockets == 1) {
        int ret;
        ret = NET_Timeout(fduse, timeout);
        if (ret <= 0) {
            if (ret == 0) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                                "Receive timed out");
            } else if (ret == -1) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                                "Socket closed");
            }
            if (pbcketBufferLen > MAX_BUFFER_LEN) {
                free(fullPbcket);
            }
            return;
        }
    }

    /*
     * Loop only if we discbrding ICMP port unrebchbble pbckets
     */
    do {
        retry = FALSE;

        /* receive the pbcket */
        n = recvfrom(fduse, fullPbcket, pbcketBufferLen, 0,
                         (struct sockbddr *)&remote_bddr, &remote_bddrsize);

        if (n == SOCKET_ERROR) {
            if (WSAGetLbstError() == WSAECONNRESET) {
                /*
                 * An icmp port unrebchbble hbs been received - consume bny other
                 * outstbnding pbckets.
                 */
                purgeOutstbndingICMP(env, this, fduse);

                /*
                 * If connected throw b PortUnrebchbbleException
                 */

                if (connected) {
                    JNU_ThrowByNbme(env, JNU_JAVANETPKG "PortUnrebchbbleException",
                                       "ICMP Port Unrebchbble");

                    if (pbcketBufferLen > MAX_BUFFER_LEN) {
                        free(fullPbcket);
                    }

                    return;
                }

                /*
                 * If b timeout wbs specified then we need to bdjust it becbuse
                 * we mby hbve used up some of the timeout before the icmp port
                 * unrebchbble brrived.
                 */
                if (timeout) {
                    int ret;
                    jlong newTime = JVM_CurrentTimeMillis(env, 0);
                    timeout -= (jint)(newTime - prevTime);
                    prevTime = newTime;

                    if (timeout <= 0) {
                        ret = 0;
                    } else {
                        ret = NET_Timeout(fduse, timeout);
                    }

                    if (ret <= 0) {
                        if (ret == 0) {
                            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketTimeoutException",
                                            "Receive timed out");
                        } else if (ret == -1) {
                            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                                            "Socket closed");
                        }
                        if (pbcketBufferLen > MAX_BUFFER_LEN) {
                            free(fullPbcket);
                        }
                        return;
                    }
                }

                /*
                 * An ICMP port unrebchbble wbs received but we bre
                 * not connected so ignore it.
                 */
                retry = TRUE;
            }
        }
    } while (retry);

    /* truncbte the dbtb if the pbcket's length is too smbll */
    if (n > pbcketBufferLen) {
        n = pbcketBufferLen;
    }
    if (n < 0) {
        errorCode = WSAGetLbstError();
        /* check to see if it's becbuse the buffer wbs too smbll */
        if (errorCode == WSAEMSGSIZE) {
            /* it is becbuse the buffer is too smbll. It's UDP, it's
             * unrelibble, it's bll good. discbrd the rest of the
             * dbtb..
             */
            n = pbcketBufferLen;
        } else {
            /* fbilure */
            (*env)->SetIntField(env, pbcket, dp_lengthID, 0);
        }
    }
    if (n == -1) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "socket closed");
    } else if (n == -2) {
        JNU_ThrowByNbme(env, JNU_JAVAIOPKG "InterruptedIOException",
                        "operbtion interrupted");
    } else if (n < 0) {
        NET_ThrowCurrent(env, "Dbtbgrbm receive fbiled");
    } else {
        int port = 0;
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
        /* populbte the pbcket */
        (*env)->SetByteArrbyRegion(env, pbcketBuffer, pbcketBufferOffset, n,
                                   (jbyte *)fullPbcket);
        (*env)->SetIntField(env, pbcket, dp_portID, port);
        (*env)->SetIntField(env, pbcket, dp_lengthID, n);
    }
    if (pbcketBufferLen > MAX_BUFFER_LEN) {
        free(fullPbcket);
    }
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    dbtbgrbmSocketCrebte
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_dbtbgrbmSocketCrebte(JNIEnv *env,
                                                           jobject this) {
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jobject fd1Obj = (*env)->GetObjectField(env, this, pdsi_fd1ID);

    int fd, fd1;
    int t = TRUE;
    DWORD x1, x2; /* ignored result codes */
    int ipv6_supported = ipv6_bvbilbble();

    int brg = -1;

    if (IS_NULL(fdObj) || (ipv6_supported && IS_NULL(fd1Obj))) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Socket closed");
        return;
    } else {
        fd =  (int) socket (AF_INET, SOCK_DGRAM, 0);
    }
    if (fd == SOCKET_ERROR) {
        NET_ThrowCurrent(env, "Socket crebtion fbiled");
        return;
    }
    SetHbndleInformbtion((HANDLE)(UINT_PTR)fd, HANDLE_FLAG_INHERIT, FALSE);
    (*env)->SetIntField(env, fdObj, IO_fd_fdID, fd);
    NET_SetSockOpt(fd, SOL_SOCKET, SO_BROADCAST, (chbr*)&t, sizeof(BOOL));

    if (ipv6_supported) {
        /* SIO_UDP_CONNRESET fixes b bug introduced in Windows 2000, which
         * returns connection reset errors un connected UDP sockets (bs well
         * bs connected sockets. The solution is to only enbble this febture
         * when the socket is connected
         */
        t = FALSE;
        WSAIoctl(fd,SIO_UDP_CONNRESET,&t,sizeof(t),&x1,sizeof(x1),&x2,0,0);
        t = TRUE;
        fd1 = socket (AF_INET6, SOCK_DGRAM, 0);
        if (fd1 == SOCKET_ERROR) {
            NET_ThrowCurrent(env, "Socket crebtion fbiled");
            return;
        }
        NET_SetSockOpt(fd1, SOL_SOCKET, SO_BROADCAST, (chbr*)&t, sizeof(BOOL));
        t = FALSE;
        WSAIoctl(fd1,SIO_UDP_CONNRESET,&t,sizeof(t),&x1,sizeof(x1),&x2,0,0);
        (*env)->SetIntField(env, fd1Obj, IO_fd_fdID, fd1);
        SetHbndleInformbtion((HANDLE)(UINT_PTR)fd1, HANDLE_FLAG_INHERIT, FALSE);
    } else {
        /* drop the second fd */
        (*env)->SetObjectField(env, this, pdsi_fd1ID, NULL);
    }
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    dbtbgrbmSocketClose
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_dbtbgrbmSocketClose(JNIEnv *env,
                                                          jobject this) {
    /*
     * REMIND: PUT A LOCK AROUND THIS CODE
     */
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jobject fd1Obj = (*env)->GetObjectField(env, this, pdsi_fd1ID);
    int ipv6_supported = ipv6_bvbilbble();
    int fd=-1, fd1=-1;

    if (IS_NULL(fdObj) && (!ipv6_supported || IS_NULL(fd1Obj))) {
        return;
    }

    if (!IS_NULL(fdObj)) {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
        if (fd != -1) {
            (*env)->SetIntField(env, fdObj, IO_fd_fdID, -1);
            NET_SocketClose(fd);
        }
    }

    if (ipv6_supported && fd1Obj != NULL) {
        fd1 = (*env)->GetIntField(env, fd1Obj, IO_fd_fdID);
        if (fd1 == -1) {
            return;
        }
        (*env)->SetIntField(env, fd1Obj, IO_fd_fdID, -1);
        NET_SocketClose(fd1);
    }
}

/*
 * check the bddresses bttbched to the NetworkInterfbce object
 * bnd return the first one (of the requested fbmily Ipv4 or Ipv6)
 * in *ibddr
 */

stbtic int getInetAddrFromIf (JNIEnv *env, int fbmily, jobject nif, jobject *ibddr)
{
    jobjectArrby bddrArrby;
    stbtic jfieldID ni_bddrsID=0;
    jsize len;
    jobject bddr;
    int i;

    if (ni_bddrsID == NULL ) {
        jclbss c = (*env)->FindClbss(env, "jbvb/net/NetworkInterfbce");
        CHECK_NULL_RETURN (c, -1);
        ni_bddrsID = (*env)->GetFieldID(env, c, "bddrs",
                                        "[Ljbvb/net/InetAddress;");
        CHECK_NULL_RETURN (ni_bddrsID, -1);
    }

    bddrArrby = (*env)->GetObjectField(env, nif, ni_bddrsID);
    len = (*env)->GetArrbyLength(env, bddrArrby);

    /*
     * Check thbt there is bt lebst one bddress bound to this
     * interfbce.
     */
    if (len < 1) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
            "bbd brgument for IP_MULTICAST_IF2: No IP bddresses bound to interfbce");
        return -1;
    }
    for (i=0; i<len; i++) {
        int fbm;
        bddr = (*env)->GetObjectArrbyElement(env, bddrArrby, i);
        fbm = getInetAddress_fbmily(env, bddr);
        if (fbm == fbmily) {
            *ibddr = bddr;
            return 0;
        }
    }
    return -1;
}

stbtic int getInet4AddrFromIf (JNIEnv *env, jobject nif, struct in_bddr *ibddr)
{
    jobject bddr;

    int ret = getInetAddrFromIf (env, IPv4, nif, &bddr);
    if (ret == -1) {
        return -1;
    }

    ibddr->s_bddr = htonl(getInetAddress_bddr(env, bddr));
    return 0;
}

/* Get the multicbsting index from the interfbce */

stbtic int getIndexFromIf (JNIEnv *env, jobject nif) {
    stbtic jfieldID ni_indexID;

    if (ni_indexID == NULL) {
        jclbss c = (*env)->FindClbss(env, "jbvb/net/NetworkInterfbce");
        CHECK_NULL_RETURN(c, -1);
        ni_indexID = (*env)->GetFieldID(env, c, "index", "I");
        CHECK_NULL_RETURN(ni_indexID, -1);
    }

    return (*env)->GetIntField(env, nif, ni_indexID);
}

stbtic int isAdbpterIpv6Enbbled(JNIEnv *env, int index) {
  extern int getAllInterfbcesAndAddresses (JNIEnv *env, netif **netifPP);
  netif *ifList, *curr;
  int ipv6Enbbled = 0;
  if (getAllInterfbcesAndAddresses (env, &ifList) < 0) {
      return ipv6Enbbled;
  }

  /* sebrch by index */
  curr = ifList;
  while (curr != NULL) {
      if (index == curr->index) {
          brebk;
      }
      curr = curr->next;
  }

  /* if found ipv6Index != 0 then interfbce is configured with IPV6 */
  if ((curr != NULL) && (curr->ipv6Index !=0)) {
      ipv6Enbbled = 1;
  }

  /* relebse the interfbce list */
  free_netif(ifList);

  return ipv6Enbbled;
}

/*
 * Sets the multicbst interfbce.
 *
 * SocketOptions.IP_MULTICAST_IF (brgument is bn InetAddress) :-
 *      IPv4:   set outgoing multicbst interfbce using
 *              IPPROTO_IP/IP_MULTICAST_IF
 *
 *      IPv6:   Get the interfbce to which the
 *              InetAddress is bound
 *              bnd do sbme bs SockOptions.IF_MULTICAST_IF2
 *
 * SockOptions.IF_MULTICAST_IF2 (brgument is b NetworkInterfbce ) :-
 *      For ebch stbck:
 *      IPv4:   Obtbin IP bddress bound to network interfbce
 *              (NetworkInterfbce.bddres[0])
 *              set outgoing multicbst interfbce using
 *              IPPROTO_IP/IP_MULTICAST_IF
 *
 *      IPv6:   Obtbin NetworkInterfbce.index
 *              Set outgoing multicbst interfbce using
 *              IPPROTO_IPV6/IPV6_MULTICAST_IF
 *
 */
stbtic void setMulticbstInterfbce(JNIEnv *env, jobject this, int fd, int fd1,
                                  jint opt, jobject vblue)
{
    int ipv6_supported = ipv6_bvbilbble();

    if (opt == jbvb_net_SocketOptions_IP_MULTICAST_IF) {
        /*
         * vblue is bn InetAddress.
         * On IPv4 system use IP_MULTICAST_IF socket option
         * On IPv6 system get the NetworkInterfbce thbt this IP
         * bddress is bound to bnd use the IPV6_MULTICAST_IF
         * option instebd of IP_MULTICAST_IF
         */
        if (ipv6_supported) {
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
            opt = jbvb_net_SocketOptions_IP_MULTICAST_IF2;
        } else {
            struct in_bddr in;

            in.s_bddr = htonl(getInetAddress_bddr(env, vblue));
            if (setsockopt(fd, IPPROTO_IP, IP_MULTICAST_IF,
                               (const chbr*)&in, sizeof(in)) < 0) {
                NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                                 "Error setting socket option");
            }
            return;
        }
    }

    if (opt == jbvb_net_SocketOptions_IP_MULTICAST_IF2) {
        /*
         * vblue is b NetworkInterfbce.
         * On IPv6 system get the index of the interfbce bnd use the
         * IPV6_MULTICAST_IF socket option
         * On IPv4 system extrbct bddr[0] bnd use the IP_MULTICAST_IF
         * option. For IPv6 both must be done.
         */
        if (ipv6_supported) {
            stbtic jfieldID ni_indexID;
            struct in_bddr in;
            int index;

            if (ni_indexID == NULL) {
                jclbss c = (*env)->FindClbss(env, "jbvb/net/NetworkInterfbce");
                CHECK_NULL(c);
                ni_indexID = (*env)->GetFieldID(env, c, "index", "I");
                CHECK_NULL(ni_indexID);
            }
            index = (*env)->GetIntField(env, vblue, ni_indexID);

            if ( isAdbpterIpv6Enbbled(env, index) != 0 ) {
                if (setsockopt(fd1, IPPROTO_IPV6, IPV6_MULTICAST_IF,
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
            /* If there bre bny IPv4 bddresses on this interfbce then
             * repebt the operbtion on the IPv4 fd */

            if (getInet4AddrFromIf (env, vblue, &in) < 0) {
                return;
            }
            if (setsockopt(fd, IPPROTO_IP, IP_MULTICAST_IF,
                               (const chbr*)&in, sizeof(in)) < 0) {
                NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                                 "Error setting socket option");
            }
            return;
        } else {
            struct in_bddr in;

            if (getInet4AddrFromIf (env, vblue, &in) < 0) {
                if ((*env)->ExceptionOccurred(env)) {
                    return;
                }
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "no InetAddress instbnces of requested type");
                return;
            }

            if (setsockopt(fd, IPPROTO_IP, IP_MULTICAST_IF,
                               (const chbr*)&in, sizeof(in)) < 0) {
                NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                               "Error setting socket option");
            }
            return;
        }
    }
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    socketNbtiveSetOption
 * Signbture: (ILjbvb/lbng/Object;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_socketNbtiveSetOption(JNIEnv *env,jobject this,
                                                      jint opt,jobject vblue) {

    int fd=-1, fd1=-1;
    int levelv4 = 0, levelv6 = 0, optnbmev4 = 0, optnbmev6 = 0, optlen = 0;
    union {
        int i;
        chbr c;
    } optvbl = { 0 };
    int ipv6_supported = ipv6_bvbilbble();
    fd = getFD(env, this);

    if (ipv6_supported) {
        fd1 = getFD1(env, this);
    }
    if (fd < 0 && fd1 < 0) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "socket closed");
        return;
    }

    if ((opt == jbvb_net_SocketOptions_IP_MULTICAST_IF) ||
        (opt == jbvb_net_SocketOptions_IP_MULTICAST_IF2)) {

        setMulticbstInterfbce(env, this, fd, fd1, opt, vblue);
        return;
    }

    /*
     * Mbp the Jbvb level socket option to the plbtform specific
     * level(s) bnd option nbme(s).
     */
    if (fd1 != -1) {
        if (NET_MbpSocketOptionV6(opt, &levelv6, &optnbmev6)) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Invblid option");
            return;
        }
    }
    if (fd != -1) {
        if (NET_MbpSocketOption(opt, &levelv4, &optnbmev4)) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Invblid option");
            return;
        }
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

                optvbl.i = (*env)->GetIntField(env, vblue, fid);
                optlen = sizeof(optvbl.i);
            }
            brebk;

        cbse jbvb_net_SocketOptions_SO_REUSEADDR:
        cbse jbvb_net_SocketOptions_SO_BROADCAST:
        cbse jbvb_net_SocketOptions_IP_MULTICAST_LOOP:
            {
                jclbss cls;
                jfieldID fid;
                jboolebn on;

                cls = (*env)->FindClbss(env, "jbvb/lbng/Boolebn");
                CHECK_NULL(cls);
                fid =  (*env)->GetFieldID(env, cls, "vblue", "Z");
                CHECK_NULL(fid);

                on = (*env)->GetBoolebnField(env, vblue, fid);
                optvbl.i = (on ? 1 : 0);
                /*
                 * setLoopbbckMode (true) disbbles IP_MULTICAST_LOOP rbther
                 * thbn enbbling it.
                 */
                if (opt == jbvb_net_SocketOptions_IP_MULTICAST_LOOP) {
                    optvbl.i = !optvbl.i;
                }
                optlen = sizeof(optvbl.i);
            }
            brebk;

        defbult :
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                "Socket option not supported by PlbinDbtbgrbmSocketImp");
            brebk;

    }

    if (fd1 != -1) {
        if (NET_SetSockOpt(fd1, levelv6, optnbmev6, (void *)&optvbl, optlen) < 0) {
            NET_ThrowCurrent(env, "setsockopt IPv6");
            return;
        }
    }
    if (fd != -1) {
        if (NET_SetSockOpt(fd, levelv4, optnbmev4, (void *)&optvbl, optlen) < 0) {
            NET_ThrowCurrent(env, "setsockopt");
            return;
        }
    }
}

/*
 *
 * cblled by getMulticbstInterfbce to retrieve b NetworkInterfbce
 * configured for IPv4.
 * The ipv4Mode pbrbmeter, is b closet boolebn, which bllows for b NULL return,
 * or forces the crebtion of b NetworkInterfbce object with null dbtb.
 * It relbtes to its cblling context in getMulticbstInterfbce.
 * ipv4Mode == 1, the context is IPV4 processing only.
 * ipv4Mode == 0, the context is IPV6 processing
 *
 */
stbtic jobject getIPv4NetworkInterfbce (JNIEnv *env, jobject this, int fd, jint opt, int ipv4Mode) {
        stbtic jclbss inet4_clbss;
        stbtic jmethodID inet4_ctrID;

        stbtic jclbss ni_clbss; stbtic jmethodID ni_ctrID;
        stbtic jfieldID ni_indexID;
        stbtic jfieldID ni_bddrsID;

        jobjectArrby bddrArrby;
        jobject bddr;
        jobject ni;

        struct in_bddr in;
        struct in_bddr *inP = &in;
        int len = sizeof(struct in_bddr);
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
        if (ipv4Mode) {
            ni = (*env)->NewObject(env, ni_clbss, ni_ctrID, 0);
            CHECK_NULL_RETURN(ni, NULL);

            (*env)->SetIntField(env, ni, ni_indexID, -1);
            bddrArrby = (*env)->NewObjectArrby(env, 1, inet4_clbss, NULL);
            CHECK_NULL_RETURN(bddrArrby, NULL);
            (*env)->SetObjectArrbyElement(env, bddrArrby, 0, bddr);
            (*env)->SetObjectField(env, ni, ni_bddrsID, bddrArrby);
        } else {
            ni = NULL;
        }
        return ni;
}

/*
 * Return the multicbst interfbce:
 *
 * SocketOptions.IP_MULTICAST_IF
 *      IPv4:   Query IPPROTO_IP/IP_MULTICAST_IF
 *              Crebte InetAddress
 *              IP_MULTICAST_IF returns struct ip_mreqn on 2.2
 *              kernel but struct in_bddr on 2.4 kernel
 *      IPv6:   Query IPPROTO_IPV6 / IPV6_MULTICAST_IF or
 *              obtbin from impl is Linux 2.2 kernel
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
jobject getMulticbstInterfbce(JNIEnv *env, jobject this, int fd, int fd1, jint opt) {
    jboolebn isIPV4 = !ipv6_bvbilbble() || fd1 == -1;

    /*
     * IPv4 implementbtion
     */
    if (isIPV4) {
        jobject netObject = NULL; // return is either bn bddr or b netif
        netObject = getIPv4NetworkInterfbce(env, this, fd, opt, 1);
        return netObject;
    }

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
        int len = sizeof(index);

        jobjectArrby bddrArrby;
        jobject bddr;
        jobject ni;

        {
            if (getsockopt(fd1, IPPROTO_IPV6, IPV6_MULTICAST_IF,
                               (chbr*)&index, &len) < 0) {
                NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                               "Error getting socket option");
                return NULL;
            }
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
        } else if (index == 0) { // index == 0 typicblly mebns IPv6 not configured on the interfbces
            // fblling bbck to trebt interfbce bs configured for IPv4
            jobject netObject = NULL;
            netObject = getIPv4NetworkInterfbce(env, this, fd, opt, 0);
            if (netObject != NULL) {
                return netObject;
            }
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
    return NULL;
}


/*
 * Returns relevbnt info bs b jint.
 *
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    socketGetOption
 * Signbture: (I)Ljbvb/lbng/Object;
 */
JNIEXPORT jobject JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_socketGetOption(JNIEnv *env, jobject this,
                                                      jint opt) {

    int fd=-1, fd1=-1;
    int level, optnbme, optlen;
    union {
        int i;
    } optvbl = {0};
    int ipv6_supported = ipv6_bvbilbble();

    fd = getFD(env, this);
    if (ipv6_supported) {
        fd1 = getFD1(env, this);
    }

    if (fd < 0 && fd1 < 0) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return NULL;
    }

    /*
     * Hbndle IP_MULTICAST_IF sepbrbtely
     */
    if (opt == jbvb_net_SocketOptions_IP_MULTICAST_IF ||
        opt == jbvb_net_SocketOptions_IP_MULTICAST_IF2) {
        return getMulticbstInterfbce(env, this, fd, fd1, opt);
    }

    /*
     * Mbp the Jbvb level socket option to the plbtform specific
     * level bnd option nbme.
     */
    if (NET_MbpSocketOption(opt, &level, &optnbme)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Invblid option");
        return NULL;
    }

    if (fd == -1) {
        if (NET_MbpSocketOptionV6(opt, &level, &optnbme)) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Invblid option");
            return NULL;
        }
        fd = fd1; /* must be IPv6 only */
    }

    optlen = sizeof(optvbl.i);
    if (NET_GetSockOpt(fd, level, optnbme, (void *)&optvbl, &optlen) < 0) {
        chbr errmsg[255];
        sprintf(errmsg, "error getting socket option: %s\n", strerror(errno));
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", errmsg);
        return NULL;
    }

    switch (opt) {
        cbse jbvb_net_SocketOptions_SO_BROADCAST:
        cbse jbvb_net_SocketOptions_SO_REUSEADDR:
            return crebteBoolebn(env, optvbl.i);

        cbse jbvb_net_SocketOptions_IP_MULTICAST_LOOP:
            /* getLoopbbckMode() returns true if IP_MULTICAST_LOOP is disbbled */
            return crebteBoolebn(env, !optvbl.i);

        cbse jbvb_net_SocketOptions_SO_SNDBUF:
        cbse jbvb_net_SocketOptions_SO_RCVBUF:
        cbse jbvb_net_SocketOptions_IP_TOS:
            return crebteInteger(env, optvbl.i);

        defbult :
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                "Socket option not supported by TwoStbcksPlbinDbtbgrbmSocketImpl");
            return NULL;

    }
}

/*
 * Returns locbl bddress of the socket.
 *
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    socketLocblAddress
 * Signbture: (I)Ljbvb/lbng/Object;
 */
JNIEXPORT jobject JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_socketLocblAddress(JNIEnv *env, jobject this,
                                                      jint fbmily) {

    int fd=-1, fd1=-1;
    SOCKETADDRESS him;
    int len = 0;
    int port;
    jobject ibObj;
    int ipv6_supported = ipv6_bvbilbble();

    fd = getFD(env, this);
    if (ipv6_supported) {
        fd1 = getFD1(env, this);
    }

    if (fd < 0 && fd1 < 0) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return NULL;
    }

    /* find out locbl IP bddress */

    len = sizeof (struct sockbddr_in);

    /* fbmily==-1 when socket is not connected */
    if ((fbmily == IPv6) || (fbmily == -1 && fd == -1)) {
        fd = fd1; /* must be IPv6 only */
        len = sizeof (struct SOCKADDR_IN6);
    }

    if (fd == -1) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return NULL;
    }

    if (getsocknbme(fd, (struct sockbddr *)&him, &len) == -1) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException",
                       "Error getting socket nbme");
        return NULL;
    }
    ibObj = NET_SockbddrToInetAddress(env, (struct sockbddr *)&him, &port);

    return ibObj;
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    setTimeToLive
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_setTimeToLive(JNIEnv *env, jobject this,
                                                    jint ttl) {

    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jobject fd1Obj = (*env)->GetObjectField(env, this, pdsi_fd1ID);
    int fd = -1, fd1 = -1;
    int ittl = (int)ttl;

    if (IS_NULL(fdObj) && IS_NULL(fd1Obj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    } else {
      if (!IS_NULL(fdObj)) {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
      }
      if (!IS_NULL(fd1Obj)) {
        fd1 = (*env)->GetIntField(env, fd1Obj, IO_fd_fdID);
      }
    }

    /* setsockopt to be correct ttl */
    if (fd >= 0) {
      if (NET_SetSockOpt(fd, IPPROTO_IP, IP_MULTICAST_TTL, (chbr*)&ittl,
                         sizeof (ittl)) < 0) {
        NET_ThrowCurrent(env, "set IP_MULTICAST_TTL fbiled");
      }
    }

    if (fd1 >= 0) {
      if (NET_SetSockOpt(fd1, IPPROTO_IPV6, IPV6_MULTICAST_HOPS, (chbr *)&ittl,
                         sizeof(ittl)) <0) {
        NET_ThrowCurrent(env, "set IPV6_MULTICAST_HOPS fbiled");
      }
    }
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    setTTL
 * Signbture: (B)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_setTTL(JNIEnv *env, jobject this,
                                             jbyte ttl) {
    Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_setTimeToLive(env, this,
                                                        (jint)ttl & 0xFF);
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    getTimeToLive
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_getTimeToLive(JNIEnv *env, jobject this) {
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jobject fd1Obj = (*env)->GetObjectField(env, this, pdsi_fd1ID);
    int fd = -1, fd1 = -1;
    int ttl = 0;
    int len = sizeof(ttl);

    if (IS_NULL(fdObj) && IS_NULL(fd1Obj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return -1;
    } else {
      if (!IS_NULL(fdObj)) {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
      }
      if (!IS_NULL(fd1Obj)) {
        fd1 = (*env)->GetIntField(env, fd1Obj, IO_fd_fdID);
      }
    }

    /* getsockopt of ttl */
    if (fd >= 0) {
      if (NET_GetSockOpt(fd, IPPROTO_IP, IP_MULTICAST_TTL, (chbr*)&ttl, &len) < 0) {
        NET_ThrowCurrent(env, "get IP_MULTICAST_TTL fbiled");
        return -1;
      }
      return (jint)ttl;
    }
    if (fd1 >= 0) {
      if (NET_GetSockOpt(fd1, IPPROTO_IPV6, IPV6_MULTICAST_HOPS, (chbr*)&ttl, &len) < 0) {
        NET_ThrowCurrent(env, "get IP_MULTICAST_TTL fbiled");
        return -1;
      }
      return (jint)ttl;
    }
    return -1;
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    getTTL
 * Signbture: ()B
 */
JNIEXPORT jbyte JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_getTTL(JNIEnv *env, jobject this) {
    int result = Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_getTimeToLive(env, this);

    return (jbyte)result;
}

/* join/lebve the nbmed group on the nbmed interfbce, or if no interfbce specified
 * then the interfbce set with setInterfbc(), or the defbult interfbce otherwise */

stbtic void mcbst_join_lebve(JNIEnv *env, jobject this,
                             jobject ibObj, jobject niObj,
                             jboolebn join)
{
    jobject fdObj = (*env)->GetObjectField(env, this, pdsi_fdID);
    jobject fd1Obj = (*env)->GetObjectField(env, this, pdsi_fd1ID);
    jint fd = -1, fd1 = -1;

    SOCKETADDRESS nbme;
    struct ip_mreq mnbme;
    struct ipv6_mreq mnbme6;

    struct in_bddr in;
    DWORD ifindex = 0;

    int len, fbmily;
    int ipv6_supported = ipv6_bvbilbble();
    int cmd ;

    memset((chbr *)&in, 0, sizeof(in));
    memset((chbr *)&nbme, 0, sizeof(nbme));

    if (IS_NULL(fdObj) && IS_NULL(fd1Obj)) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                        "Socket closed");
        return;
    }
    if (!IS_NULL(fdObj)) {
        fd = (*env)->GetIntField(env, fdObj, IO_fd_fdID);
    }
    if (ipv6_supported && !IS_NULL(fd1Obj)) {
        fd1 = (*env)->GetIntField(env, fd1Obj, IO_fd_fdID);
    }

    if (IS_NULL(ibObj)) {
        JNU_ThrowNullPointerException(env, "bddress");
        return;
    }

    if (NET_InetAddressToSockbddr(env, ibObj, 0, (struct sockbddr *)&nbme, &len, JNI_FALSE) != 0) {
      return;
    }

    /* Set the multicbst group bddress in the ip_mreq field
     * eventublly this check should be done by the security mbnbger
     */
    fbmily = nbme.him.sb_fbmily;

    if (fbmily == AF_INET) {
        int bddress = nbme.him4.sin_bddr.s_bddr;
        if (!IN_MULTICAST(ntohl(bddress))) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "not in multicbst");
            return;
        }
        mnbme.imr_multibddr.s_bddr = bddress;
        if (fd < 0) {
          JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Cbn't join bn IPv4 group on bn IPv6 only socket");
          return;
        }
        if (IS_NULL(niObj)) {
            len = sizeof (in);
            if (NET_GetSockOpt(fd, IPPROTO_IP, IP_MULTICAST_IF,
                           (chbr *)&in, &len) < 0) {
                NET_ThrowCurrent(env, "get IP_MULTICAST_IF fbiled");
                return;
            }
            mnbme.imr_interfbce.s_bddr = in.s_bddr;
        } else {
            if (getInet4AddrFromIf (env, niObj, &mnbme.imr_interfbce) != 0) {
                NET_ThrowCurrent(env, "no Inet4Address bssocibted with interfbce");
                return;
            }
        }

        cmd = join ? IP_ADD_MEMBERSHIP: IP_DROP_MEMBERSHIP;

        /* Join the multicbst group */
        if (NET_SetSockOpt(fd, IPPROTO_IP, cmd, (chbr *) &mnbme, sizeof (mnbme)) < 0) {
            if (WSAGetLbstError() == WSAENOBUFS) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                    "IP_ADD_MEMBERSHIP fbiled (out of hbrdwbre filters?)");
            } else {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException","error setting options");
            }
        }
    } else /* AF_INET6 */ {
        if (ipv6_supported) {
            struct in6_bddr *bddress;
            bddress = &nbme.him6.sin6_bddr;
            if (!IN6_IS_ADDR_MULTICAST(bddress)) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "not in6 multicbst");
                return;
            }
            mnbme6.ipv6mr_multibddr = *bddress;
        } else {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "IPv6 not supported");
            return;
        }
        if (fd1 < 0) {
          JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Cbn't join bn IPv6 group on b IPv4 socket");
          return;
        }
        if (IS_NULL(niObj)) {
            len = sizeof (ifindex);
            if (NET_GetSockOpt(fd1, IPPROTO_IPV6, IPV6_MULTICAST_IF, &ifindex, &len) < 0) {
                NET_ThrowCurrent(env, "get IPV6_MULTICAST_IF fbiled");
                return;
            }
        } else {
            ifindex = getIndexFromIf (env, niObj);
            if (ifindex == -1) {
                NET_ThrowCurrent(env, "get ifindex fbiled");
                return;
            }
        }
        mnbme6.ipv6mr_interfbce = ifindex;
        cmd = join ? IPV6_ADD_MEMBERSHIP: IPV6_DROP_MEMBERSHIP;

        /* Join the multicbst group */
        if (NET_SetSockOpt(fd1, IPPROTO_IPV6, cmd, (chbr *) &mnbme6, sizeof (mnbme6)) < 0) {
            if (WSAGetLbstError() == WSAENOBUFS) {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException",
                    "IP_ADD_MEMBERSHIP fbiled (out of hbrdwbre filters?)");
            } else {
                JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException","error setting options");
            }
        }
    }

    return;
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    join
 * Signbture: (Ljbvb/net/InetAddress;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_join(JNIEnv *env, jobject this,
                                           jobject ibObj, jobject niObj)
{
    mcbst_join_lebve (env, this, ibObj, niObj, JNI_TRUE);
}

/*
 * Clbss:     jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl
 * Method:    lebve
 * Signbture: (Ljbvb/net/InetAddress;)V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_TwoStbcksPlbinDbtbgrbmSocketImpl_lebve(JNIEnv *env, jobject this,
                                            jobject ibObj, jobject niObj)
{
    mcbst_join_lebve (env, this, ibObj, niObj, JNI_FALSE);
}
