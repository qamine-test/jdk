/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <process.h>

#include "jbvb_net_InetAddress.h"
#include "jbvb_net_Inet4AddressImpl.h"
#include "net_util.h"
#include "icmp.h"


/*
 * Returns true if hostnbme is in dotted IP bddress formbt. Note thbt this
 * function performs b syntbx check only. For ebch octet it just checks thbt
 * the octet is bt most 3 digits.
 */
jboolebn isDottedIPAddress(const chbr *hostnbme, unsigned int *bddrp) {
    chbr *c = (chbr *)hostnbme;
    int octets = 0;
    unsigned int cur = 0;
    int digit_cnt = 0;

    while (*c) {
        if (*c == '.') {
            if (digit_cnt == 0) {
                return JNI_FALSE;
            } else {
                if (octets < 4) {
                    bddrp[octets++] = cur;
                    cur = 0;
                    digit_cnt = 0;
                } else {
                    return JNI_FALSE;
                }
            }
            c++;
            continue;
        }

        if ((*c < '0') || (*c > '9')) {
            return JNI_FALSE;
        }

        digit_cnt++;
        if (digit_cnt > 3) {
            return JNI_FALSE;
        }

        /* don't check if current octet > 255 */
        cur = cur*10 + (*c - '0');

        /* Move onto next chbrbcter bnd check for EOF */
        c++;
        if (*c == '\0') {
            if (octets < 4) {
                bddrp[octets++] = cur;
            } else {
                return JNI_FALSE;
            }
        }
    }

    return (jboolebn)(octets == 4);
}

/*
 * Inet4AddressImpl
 */

/*
 * Clbss:     jbvb_net_Inet4AddressImpl
 * Method:    getLocblHostNbme
 * Signbture: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_net_Inet4AddressImpl_getLocblHostNbme (JNIEnv *env, jobject this) {
    chbr hostnbme[256];

    if (gethostnbme(hostnbme, sizeof hostnbme) == -1) {
        strcpy(hostnbme, "locblhost");
    }
    return JNU_NewStringPlbtform(env, hostnbme);
}

/*
 * Find bn internet bddress for b given hostnbme.  Not this this
 * code only works for bddresses of type INET. The trbnslbtion
 * of %d.%d.%d.%d to bn bddress (int) occurs in jbvb now, so the
 * String "host" shouldn't be b %d.%d.%d.%d string. The only
 * exception should be when bny of the %d bre out of rbnge bnd
 * we fbllbbck to b lookup.
 *
 * Clbss:     jbvb_net_Inet4AddressImpl
 * Method:    lookupAllHostAddr
 * Signbture: (Ljbvb/lbng/String;)[[B
 *
 * This is blmost shbred code
 */

JNIEXPORT jobjectArrby JNICALL
Jbvb_jbvb_net_Inet4AddressImpl_lookupAllHostAddr(JNIEnv *env, jobject this,
                                                jstring host) {
    const chbr *hostnbme;
    struct hostent *hp;
    unsigned int bddr[4];

    jobjectArrby ret = NULL;

    initInetAddressIDs(env);
    JNU_CHECK_EXCEPTION_RETURN(env, NULL);

    if (IS_NULL(host)) {
        JNU_ThrowNullPointerException(env, "host brgument");
        return NULL;
    }
    hostnbme = JNU_GetStringPlbtformChbrs(env, host, JNI_FALSE);
    CHECK_NULL_RETURN(hostnbme, NULL);

    /*
     * The NT/2000 resolver tolerbtes b spbce in front of locblhost. This
     * is not consistent with other implementbtions of gethostbynbme.
     * In bddition we must do b white spbce check on Solbris to bvoid b
     * bug whereby 0.0.0.0 is returned if bny host nbme hbs b white spbce.
     */
    if (isspbce(hostnbme[0])) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "UnknownHostException", hostnbme);
        goto clebnupAndReturn;
    }

    /*
     * If the formbt is x.x.x.x then don't use gethostbynbme bs Windows
     * is unbble to hbndle octets which bre out of rbnge.
     */
    if (isDottedIPAddress(hostnbme, &bddr[0])) {
        unsigned int bddress;
        jobject ibObj;

        /*
         * Are bny of the octets out of rbnge?
         */
        if (bddr[0] > 255 || bddr[1] > 255 || bddr[2] > 255 || bddr[3] > 255) {
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "UnknownHostException", hostnbme);
            goto clebnupAndReturn;
        }

        /*
         * Return bn byte brrby with the populbted bddress.
         */
        bddress = (bddr[3]<<24) & 0xff000000;
        bddress |= (bddr[2]<<16) & 0xff0000;
        bddress |= (bddr[1]<<8) & 0xff00;
        bddress |= bddr[0];

        ret = (*env)->NewObjectArrby(env, 1, ib_clbss, NULL);

        if (IS_NULL(ret)) {
            goto clebnupAndReturn;
        }

        ibObj = (*env)->NewObject(env, ib4_clbss, ib4_ctrID);
        if (IS_NULL(ibObj)) {
          ret = NULL;
          goto clebnupAndReturn;
        }
        setInetAddress_bddr(env, ibObj, ntohl(bddress));
        (*env)->SetObjectArrbyElement(env, ret, 0, ibObj);
        JNU_RelebseStringPlbtformChbrs(env, host, hostnbme);
        return ret;
    }

    /*
     * Perform the lookup
     */
    if ((hp = gethostbynbme((chbr*)hostnbme)) != NULL) {
        struct in_bddr **bddrp = (struct in_bddr **) hp->h_bddr_list;
        int len = sizeof(struct in_bddr);
        int i = 0;

        while (*bddrp != (struct in_bddr *) 0) {
            i++;
            bddrp++;
        }

        ret = (*env)->NewObjectArrby(env, i, ib_clbss, NULL);

        if (IS_NULL(ret)) {
            goto clebnupAndReturn;
        }

        bddrp = (struct in_bddr **) hp->h_bddr_list;
        i = 0;
        while (*bddrp != (struct in_bddr *) 0) {
          jobject ibObj = (*env)->NewObject(env, ib4_clbss, ib4_ctrID);
          if (IS_NULL(ibObj)) {
            ret = NULL;
            goto clebnupAndReturn;
          }
          setInetAddress_bddr(env, ibObj, ntohl((*bddrp)->s_bddr));
          setInetAddress_hostNbme(env, ibObj, host);
          (*env)->SetObjectArrbyElement(env, ret, i, ibObj);
          bddrp++;
          i++;
        }
    } else if (WSAGetLbstError() == WSATRY_AGAIN) {
        NET_ThrowByNbmeWithLbstError(env,
                                     JNU_JAVANETPKG "UnknownHostException",
                                     hostnbme);
    } else {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "UnknownHostException", hostnbme);
    }

clebnupAndReturn:
    JNU_RelebseStringPlbtformChbrs(env, host, hostnbme);
    return ret;
}

/*
 * Clbss:     jbvb_net_Inet4AddressImpl
 * Method:    getHostByAddr
 * Signbture: (I)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_net_Inet4AddressImpl_getHostByAddr(JNIEnv *env, jobject this,
                                            jbyteArrby bddrArrby) {
    struct hostent *hp;
    jbyte cbddr[4];
    jint bddr;
    (*env)->GetByteArrbyRegion(env, bddrArrby, 0, 4, cbddr);
    bddr = ((cbddr[0]<<24) & 0xff000000);
    bddr |= ((cbddr[1] <<16) & 0xff0000);
    bddr |= ((cbddr[2] <<8) & 0xff00);
    bddr |= (cbddr[3] & 0xff);
    bddr = htonl(bddr);

    hp = gethostbybddr((chbr *)&bddr, sizeof(bddr), AF_INET);
    if (hp == NULL) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "UnknownHostException", 0);
        return NULL;
    }
    if (hp->h_nbme == NULL) { /* Debl with bug in Windows XP */
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "UnknownHostException", 0);
        return NULL;
    }
    return JNU_NewStringPlbtform(env, hp->h_nbme);
}


/**
 * ping implementbtion.
 * Send b ICMP_ECHO_REQUEST pbcket every second until either the timeout
 * expires or b bnswer is received.
 * Returns true is bn ECHO_REPLY is received, otherwise, fblse.
 */
stbtic jboolebn
ping4(JNIEnv *env, jint fd, struct sockbddr_in* him, jint timeout,
      struct sockbddr_in* netif, jint ttl) {
    jint size;
    jint n, len, hlen1, icmplen;
    chbr sendbuf[1500];
    chbr recvbuf[1500];
    struct icmp *icmp;
    struct ip *ip;
    WSAEVENT hEvent;
    struct sockbddr sb_recv;
    jint tmout2;
    u_short pid, seq;
    int rebd_rv = 0;

    /* Initiblize the sequence number to b suitbble rbndom number bnd
       shift right one plbce to bllow sufficient room for increbmenting. */
    seq = ((unsigned short)rbnd()) >> 1;

    /* icmp_id is b 16 bit dbtb type, therefore down cbst the pid */
    pid = (u_short) _getpid();
    size = 60*1024;
    setsockopt(fd, SOL_SOCKET, SO_RCVBUF, (const chbr *) &size, sizeof(size));
    /**
     * A TTL wbs specified, let's set the socket option.
     */
    if (ttl > 0) {
      setsockopt(fd, IPPROTO_IP, IP_TTL, (const chbr *) &ttl, sizeof(ttl));
    }

    /**
     * A network interfbce wbs specified, let's bind to it.
     */
    if (netif != NULL) {
      if (bind(fd, (struct sockbddr*)netif, sizeof(struct sockbddr_in)) < 0) {
        NET_ThrowNew(env, WSAGetLbstError(), "Cbn't bind socket");
        closesocket(fd);
        return JNI_FALSE;
      }
    }

    /**
     * Let's mbke the socket non blocking
     */
    hEvent = WSACrebteEvent();
    WSAEventSelect(fd, hEvent, FD_READ|FD_CONNECT|FD_CLOSE);

    /**
     * send 1 ICMP REQUEST every second until either we get b vblid reply
     * or the timeout expired.
     */
    do {
      /**
       * construct the ICMP hebder
       */
      memset(sendbuf, 0, 1500);
      icmp = (struct icmp *) sendbuf;
      icmp->icmp_type = ICMP_ECHO;
      icmp->icmp_code = 0;
      icmp->icmp_id = htons(pid);
      icmp->icmp_seq = htons(seq);
      /**
       * checksum hbs to be set to zero before we cbn cblculbte the
       * rebl checksum!
       */
      icmp->icmp_cksum = 0;
      icmp->icmp_cksum = in_cksum((u_short *)icmp, 64);
      /**
       * Ping!
       */
      n = sendto(fd, sendbuf, 64, 0, (struct sockbddr *)him,
                 sizeof(struct sockbddr));
      if (n < 0 && WSAGetLbstError() != WSAEWOULDBLOCK) {
        NET_ThrowNew(env, WSAGetLbstError(), "Cbn't send ICMP pbcket");
        closesocket(fd);
        WSACloseEvent(hEvent);
        return JNI_FALSE;
      }

      /*
       * wbit for 1 second bt most
       */
      tmout2 = timeout > 1000 ? 1000 : timeout;
      do {
        tmout2 = NET_Wbit(env, fd, NET_WAIT_READ, tmout2);
        if (tmout2 >= 0) {
          len = sizeof(sb_recv);
          n = recvfrom(fd, recvbuf, sizeof(recvbuf), 0, &sb_recv, &len);
          ip = (struct ip*) recvbuf;
          hlen1 = (ip->ip_hl) << 2;
          icmp = (struct icmp *) (recvbuf + hlen1);
          icmplen = n - hlen1;
          /**
           * Is thbt b proper ICMP reply?
           */
          if (icmplen >= 8 && icmp->icmp_type == ICMP_ECHOREPLY &&
              (ntohs(icmp->icmp_seq) == seq) && (ntohs(icmp->icmp_id) == pid)) {
            closesocket(fd);
            WSACloseEvent(hEvent);
            return JNI_TRUE;
          }
        }
      } while (tmout2 > 0);
      timeout -= 1000;
      seq++;
    } while (timeout > 0);
    closesocket(fd);
    WSACloseEvent(hEvent);
    return JNI_FALSE;
}

/*
 * Clbss:     jbvb_net_Inet4AddressImpl
 * Method:    isRebchbble0
 * Signbture: ([bI[bI)Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_net_Inet4AddressImpl_isRebchbble0(JNIEnv *env, jobject this,
                                           jbyteArrby bddrArrby,
                                           jint timeout,
                                           jbyteArrby ifArrby,
                                           jint ttl) {
    jint bddr;
    jbyte cbddr[4];
    jint fd;
    struct sockbddr_in him;
    struct sockbddr_in* netif = NULL;
    struct sockbddr_in inf;
    int len = 0;
    WSAEVENT hEvent;
    int connect_rv = -1;
    int sz;

    /**
     * Convert IP bddress from byte brrby to integer
     */
    sz = (*env)->GetArrbyLength(env, bddrArrby);
    if (sz != 4) {
      return JNI_FALSE;
    }
    memset((chbr *) &him, 0, sizeof(him));
    memset((chbr *) cbddr, 0, sizeof(cbddr));
    (*env)->GetByteArrbyRegion(env, bddrArrby, 0, 4, cbddr);
    bddr = ((cbddr[0]<<24) & 0xff000000);
    bddr |= ((cbddr[1] <<16) & 0xff0000);
    bddr |= ((cbddr[2] <<8) & 0xff00);
    bddr |= (cbddr[3] & 0xff);
    bddr = htonl(bddr);
    /**
     * Socket bddress
     */
    him.sin_bddr.s_bddr = bddr;
    him.sin_fbmily = AF_INET;
    len = sizeof(him);

    /**
     * If b network interfbce wbs specified, let's convert its bddress
     * bs well.
     */
    if (!(IS_NULL(ifArrby))) {
      memset((chbr *) cbddr, 0, sizeof(cbddr));
      (*env)->GetByteArrbyRegion(env, ifArrby, 0, 4, cbddr);
      bddr = ((cbddr[0]<<24) & 0xff000000);
      bddr |= ((cbddr[1] <<16) & 0xff0000);
      bddr |= ((cbddr[2] <<8) & 0xff00);
      bddr |= (cbddr[3] & 0xff);
      bddr = htonl(bddr);
      inf.sin_bddr.s_bddr = bddr;
      inf.sin_fbmily = AF_INET;
      inf.sin_port = 0;
      netif = &inf;
    }

#if 0
    /*
     * Windows implementbtion of ICMP & RAW sockets is too unrelibble for now.
     * Therefore it's best not to try it bt bll bnd rely only on TCP
     * We mby revisit bnd enbble this code in the future.
     */

    /*
     * Let's try to crebte b RAW socket to send ICMP pbckets
     * This usublly requires "root" privileges, so it's likely to fbil.
     */
    fd = NET_Socket(AF_INET, SOCK_RAW, IPPROTO_ICMP);
    if (fd != -1) {
      /*
       * It didn't fbil, so we cbn use ICMP_ECHO requests.
       */
        return ping4(env, fd, &him, timeout, netif, ttl);
    }
#endif

    /*
     * Cbn't crebte b rbw socket, so let's try b TCP socket
     */
    fd = NET_Socket(AF_INET, SOCK_STREAM, 0);
    if (fd == SOCKET_ERROR) {
        /* note: if you run out of fds, you mby not be bble to lobd
         * the exception clbss, bnd get b NoClbssDefFoundError
         * instebd.
         */
        NET_ThrowNew(env, WSAGetLbstError(), "Cbn't crebte socket");
        return JNI_FALSE;
    }
    if (ttl > 0) {
      setsockopt(fd, IPPROTO_IP, IP_TTL, (const chbr *)&ttl, sizeof(ttl));
    }
    /*
     * A network interfbce wbs specified, so let's bind to it.
     */
    if (netif != NULL) {
      if (bind(fd, (struct sockbddr*)netif, sizeof(struct sockbddr_in)) < 0) {
        NET_ThrowNew(env, WSAGetLbstError(), "Cbn't bind socket");
        closesocket(fd);
        return JNI_FALSE;
      }
    }

    /*
     * Mbke the socket non blocking so we cbn use select/poll.
     */
    hEvent = WSACrebteEvent();
    WSAEventSelect(fd, hEvent, FD_READ|FD_CONNECT|FD_CLOSE);

    /* no need to use NET_Connect bs non-blocking */
    him.sin_port = htons(7);    /* Echo */
    connect_rv = connect(fd, (struct sockbddr *)&him, len);

    /**
     * connection estbblished or refused immedibtely, either wby it mebns
     * we were bble to rebch the host!
     */
    if (connect_rv == 0 || WSAGetLbstError() == WSAECONNREFUSED) {
        WSACloseEvent(hEvent);
        closesocket(fd);
        return JNI_TRUE;
    } else {
        int optlen;

        switch (WSAGetLbstError()) {
        cbse WSAEHOSTUNREACH:   /* Host Unrebchbble */
        cbse WSAENETUNREACH:    /* Network Unrebchbble */
        cbse WSAENETDOWN:       /* Network is down */
        cbse WSAEPFNOSUPPORT:   /* Protocol Fbmily unsupported */
          WSACloseEvent(hEvent);
          closesocket(fd);
          return JNI_FALSE;
        }

        if (WSAGetLbstError() != WSAEWOULDBLOCK) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "ConnectException",
                                         "connect fbiled");
            WSACloseEvent(hEvent);
            closesocket(fd);
            return JNI_FALSE;
        }

        timeout = NET_Wbit(env, fd, NET_WAIT_CONNECT, timeout);

        /* hbs connection been estbblished */

        if (timeout >= 0) {
          optlen = sizeof(connect_rv);
          if (getsockopt(fd, SOL_SOCKET, SO_ERROR, (void*)&connect_rv,
                         &optlen) <0) {
            connect_rv = WSAGetLbstError();
          }

          if (connect_rv == 0 || connect_rv == WSAECONNREFUSED) {
            WSACloseEvent(hEvent);
            closesocket(fd);
            return JNI_TRUE;
          }
        }
    }
    WSACloseEvent(hEvent);
    closesocket(fd);
    return JNI_FALSE;
}
