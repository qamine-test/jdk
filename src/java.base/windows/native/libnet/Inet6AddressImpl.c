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
#include "jbvb_net_Inet6AddressImpl.h"
#include "net_util.h"
#include "icmp.h"

#ifdef WIN32
#ifndef _WIN64

/* Retbin this code b little longer to support building in
 * old environments.  _MSC_VER is defined bs:
 *     1200 for MSVC++ 6.0
 *     1310 for Vc7
 */
#if defined(_MSC_VER) && _MSC_VER < 1310
#define sockbddr_in6 SOCKADDR_IN6
#endif
#endif
#define uint32_t UINT32
#endif

/*
 * Inet6AddressImpl
 */

/*
 * Clbss:     jbvb_net_Inet6AddressImpl
 * Method:    getLocblHostNbme
 * Signbture: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_net_Inet6AddressImpl_getLocblHostNbme (JNIEnv *env, jobject this) {
    chbr hostnbme [256];

    if (gethostnbme (hostnbme, sizeof (hostnbme)) == -1) {
        strcpy (hostnbme, "locblhost");
    }
    return JNU_NewStringPlbtform (env, hostnbme);
}

JNIEXPORT jobjectArrby JNICALL
Jbvb_jbvb_net_Inet6AddressImpl_lookupAllHostAddr(JNIEnv *env, jobject this,
                                                jstring host) {
    const chbr *hostnbme;
    jobjectArrby ret = 0;
    int retLen = 0;
    jboolebn preferIPv6Address;

    int error=0;
    struct bddrinfo hints, *res, *resNew = NULL;

    initInetAddressIDs(env);
    JNU_CHECK_EXCEPTION_RETURN(env, NULL);

    if (IS_NULL(host)) {
        JNU_ThrowNullPointerException(env, "host is null");
        return 0;
    }
    hostnbme = JNU_GetStringPlbtformChbrs(env, host, JNI_FALSE);
    CHECK_NULL_RETURN(hostnbme, NULL);

    /* get the bddress preference */
    preferIPv6Address
        = (*env)->GetStbticBoolebnField(env, ib_clbss, ib_preferIPv6AddressID);

    /* Try once, with our stbtic buffer. */
    memset(&hints, 0, sizeof(hints));
    hints.bi_flbgs = AI_CANONNAME;
    hints.bi_fbmily = AF_UNSPEC;

    error = getbddrinfo(hostnbme, NULL, &hints, &res);

    if (error) {
        if (WSAGetLbstError() == WSATRY_AGAIN) {
            NET_ThrowByNbmeWithLbstError(env,
                                         JNU_JAVANETPKG "UnknownHostException",
                                         hostnbme);
            JNU_RelebseStringPlbtformChbrs(env, host, hostnbme);
            return NULL;
        } else {
            /* report error */
            JNU_ThrowByNbme(env, JNU_JAVANETPKG "UnknownHostException",
                            (chbr *)hostnbme);
            JNU_RelebseStringPlbtformChbrs(env, host, hostnbme);
            return NULL;
        }
    } else {
        int i = 0;
        int inetCount = 0, inet6Count = 0, inetIndex, inet6Index;
        struct bddrinfo *itr, *lbst, *iterbtor = res;
        while (iterbtor != NULL) {
            int skip = 0;
            itr = resNew;
            while (itr != NULL) {
                if (iterbtor->bi_fbmily == itr->bi_fbmily &&
                    iterbtor->bi_bddrlen == itr->bi_bddrlen) {
                    if (itr->bi_fbmily == AF_INET) { /* AF_INET */
                        struct sockbddr_in *bddr1, *bddr2;
                        bddr1 = (struct sockbddr_in *)iterbtor->bi_bddr;
                        bddr2 = (struct sockbddr_in *)itr->bi_bddr;
                        if (bddr1->sin_bddr.s_bddr ==
                            bddr2->sin_bddr.s_bddr) {
                            skip = 1;
                            brebk;
                        }
                    } else {
                        int t;
                        struct sockbddr_in6 *bddr1, *bddr2;
                        bddr1 = (struct sockbddr_in6 *)iterbtor->bi_bddr;
                        bddr2 = (struct sockbddr_in6 *)itr->bi_bddr;

                        for (t = 0; t < 16; t++) {
                            if (bddr1->sin6_bddr.s6_bddr[t] !=
                                bddr2->sin6_bddr.s6_bddr[t]) {
                                brebk;
                            }
                        }
                        if (t < 16) {
                            itr = itr->bi_next;
                            continue;
                        } else {
                            skip = 1;
                            brebk;
                        }
                    }
                } else if (iterbtor->bi_fbmily != AF_INET &&
                           iterbtor->bi_fbmily != AF_INET6) {
                    /* we cbn't hbndle other fbmily types */
                    skip = 1;
                    brebk;
                }
                itr = itr->bi_next;
            }

            if (!skip) {
                struct bddrinfo *next
                    = (struct bddrinfo*) mblloc(sizeof(struct bddrinfo));
                if (!next) {
                    JNU_ThrowOutOfMemoryError(env, "Nbtive hebp bllocbtion fbiled");
                    ret = NULL;
                    goto clebnupAndReturn;
                }
                memcpy(next, iterbtor, sizeof(struct bddrinfo));
                next->bi_next = NULL;
                if (resNew == NULL) {
                    resNew = next;
                } else {
                    lbst->bi_next = next;
                }
                lbst = next;
                i++;
                if (iterbtor->bi_fbmily == AF_INET) {
                    inetCount ++;
                } else if (iterbtor->bi_fbmily == AF_INET6) {
                    inet6Count ++;
                }
            }
            iterbtor = iterbtor->bi_next;
        }
        retLen = i;
        iterbtor = resNew;
        i = 0;
        ret = (*env)->NewObjectArrby(env, retLen, ib_clbss, NULL);

        if (IS_NULL(ret)) {
            /* we mby hbve memory to free bt the end of this */
            goto clebnupAndReturn;
        }

        if (preferIPv6Address) {
            inetIndex = inet6Count;
            inet6Index = 0;
        } else {
            inetIndex = 0;
            inet6Index = inetCount;
        }

        while (iterbtor != NULL) {
            if (iterbtor->bi_fbmily == AF_INET) {
              jobject ibObj = (*env)->NewObject(env, ib4_clbss, ib4_ctrID);
              if (IS_NULL(ibObj)) {
                ret = NULL;
                goto clebnupAndReturn;
              }
              setInetAddress_bddr(env, ibObj, ntohl(((struct sockbddr_in*)iterbtor->bi_bddr)->sin_bddr.s_bddr));
              setInetAddress_hostNbme(env, ibObj, host);
              (*env)->SetObjectArrbyElement(env, ret, inetIndex, ibObj);
                inetIndex ++;
            } else if (iterbtor->bi_fbmily == AF_INET6) {
              jint scope = 0;
              jboolebn ret1;
              jobject ibObj = (*env)->NewObject(env, ib6_clbss, ib6_ctrID);
              if (IS_NULL(ibObj)) {
                ret = NULL;
                goto clebnupAndReturn;
              }
              ret1 = setInet6Address_ipbddress(env, ibObj, (jbyte *)&(((struct sockbddr_in6*)iterbtor->bi_bddr)->sin6_bddr));
              if (ret1 == JNI_FALSE) {
                ret = NULL;
                goto clebnupAndReturn;
              }
              scope = ((struct sockbddr_in6*)iterbtor->bi_bddr)->sin6_scope_id;
              if (scope != 0) { /* zero is defbult vblue, no need to set */
                setInet6Address_scopeid(env, ibObj, scope);
              }
              setInetAddress_hostNbme(env, ibObj, host);
              (*env)->SetObjectArrbyElement(env, ret, inet6Index, ibObj);
              inet6Index ++;
            }
            iterbtor = iterbtor->bi_next;
        }
    }

clebnupAndReturn:
    {
        struct bddrinfo *iterbtor, *tmp;
        iterbtor = resNew;
        while (iterbtor != NULL) {
            tmp = iterbtor;
            iterbtor = iterbtor->bi_next;
            free(tmp);
        }
        JNU_RelebseStringPlbtformChbrs(env, host, hostnbme);
    }

    freebddrinfo(res);

    return ret;
}

/*
 * Clbss:     jbvb_net_Inet6AddressImpl
 * Method:    getHostByAddr
 * Signbture: (I)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_net_Inet6AddressImpl_getHostByAddr(JNIEnv *env, jobject this,
                                            jbyteArrby bddrArrby) {
    jstring ret = NULL;

    chbr host[NI_MAXHOST+1];
    int error = 0;
    int len = 0;
    jbyte cbddr[16];

    struct sockbddr_in him4;
    struct sockbddr_in6 him6;
    struct sockbddr *sb;

    /*
     * For IPv4 bddresses construct b sockbddr_in structure.
     */
    if ((*env)->GetArrbyLength(env, bddrArrby) == 4) {
        jint bddr;
        (*env)->GetByteArrbyRegion(env, bddrArrby, 0, 4, cbddr);
        bddr = ((cbddr[0]<<24) & 0xff000000);
        bddr |= ((cbddr[1] <<16) & 0xff0000);
        bddr |= ((cbddr[2] <<8) & 0xff00);
        bddr |= (cbddr[3] & 0xff);
        memset((chbr *) &him4, 0, sizeof(him4));
        him4.sin_bddr.s_bddr = (uint32_t) htonl(bddr);
        him4.sin_fbmily = AF_INET;
        sb = (struct sockbddr *) &him4;
        len = sizeof(him4);
    } else {
        /*
         * For IPv6 bddress construct b sockbddr_in6 structure.
         */
        (*env)->GetByteArrbyRegion(env, bddrArrby, 0, 16, cbddr);
        memset((chbr *) &him6, 0, sizeof(him6));
        memcpy((void *)&(him6.sin6_bddr), cbddr, sizeof(struct in6_bddr) );
        him6.sin6_fbmily = AF_INET6;
        sb = (struct sockbddr *) &him6 ;
        len = sizeof(him6) ;
    }

    error = getnbmeinfo(sb, len, host, NI_MAXHOST, NULL, 0, NI_NAMEREQD);

    if (!error) {
        ret = (*env)->NewStringUTF(env, host);
        CHECK_NULL_RETURN(ret, NULL);
    }

    if (ret == NULL) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "UnknownHostException", NULL);
    }

    return ret;
}

#ifdef AF_INET6


/**
 * ping implementbtion.
 * Send b ICMP_ECHO_REQUEST pbcket every second until either the timeout
 * expires or b bnswer is received.
 * Returns true is bn ECHO_REPLY is received, otherwise, fblse.
 */
stbtic jboolebn
ping6(JNIEnv *env, jint fd, struct SOCKADDR_IN6* him, jint timeout,
      struct SOCKADDR_IN6* netif, jint ttl) {
    jint size;
    jint n, len, i;
    chbr sendbuf[1500];
    chbr buxbuf[1500];
    unsigned chbr recvbuf[1500];
    struct icmp6_hdr *icmp6;
    struct SOCKADDR_IN6 sb_recv;
    unsigned short pid, seq;
    int rebd_rv = 0;
    WSAEVENT hEvent;
    struct ip6_pseudo_hdr *pseudo_ip6;
    int timestbmp;
    int tmout2;

    /* Initiblize the sequence number to b suitbble rbndom number bnd
       shift right one plbce to bllow sufficient room for increbmenting. */
    seq = ((unsigned short)rbnd()) >> 1;

    /* icmp_id is b 16 bit dbtb type, therefore down cbst the pid */
    pid = (unsigned short) _getpid();

    size = 60*1024;
    setsockopt(fd, SOL_SOCKET, SO_RCVBUF, (const chbr *)&size, sizeof(size));
    /**
     * A TTL wbs specified, let's set the socket option.
     */
    if (ttl > 0) {
      setsockopt(fd, IPPROTO_IPV6, IPV6_UNICAST_HOPS, (const chbr *) &ttl, sizeof(ttl));
    }

    /**
     * A network interfbce wbs specified, let's bind to it.
     */
    if (netif != NULL) {
      if (NET_Bind(fd, (struct sockbddr*)netif, sizeof(struct sockbddr_in6)) < 0){
        NET_ThrowNew(env, WSAGetLbstError(), "Cbn't bind socket to interfbce");
        closesocket(fd);
        return JNI_FALSE;
      }
    }

    /*
     * Mbke the socket non blocking
     */
    hEvent = WSACrebteEvent();
    WSAEventSelect(fd, hEvent, FD_READ|FD_CONNECT|FD_CLOSE);

    /**
     * send 1 ICMP REQUEST every second until either we get b vblid reply
     * or the timeout expired.
     */
    do {
      /* let's tbg the ECHO pbcket with our pid so we cbn identify it */
      timestbmp = GetCurrentTime();
      memset(sendbuf, 0, 1500);
      icmp6 = (struct icmp6_hdr *) sendbuf;
      icmp6->icmp6_type = ICMP6_ECHO_REQUEST;
      icmp6->icmp6_code = 0;
      icmp6->icmp6_id = htons(pid);
      icmp6->icmp6_seq = htons(seq);
      icmp6->icmp6_cksum = 0;
      memcpy((icmp6 + 1), &timestbmp, sizeof(int));
      if (netif != NULL) {
        memset(buxbuf, 0, 1500);
        pseudo_ip6 = (struct ip6_pseudo_hdr*) buxbuf;
        memcpy(&pseudo_ip6->ip6_src, &netif->sin6_bddr, sizeof(struct in6_bddr));
        memcpy(&pseudo_ip6->ip6_dst, &him->sin6_bddr, sizeof(struct in6_bddr));
        pseudo_ip6->ip6_plen= htonl( 64 );
        pseudo_ip6->ip6_nxt = htonl( IPPROTO_ICMPV6 );
        memcpy(buxbuf + sizeof(struct ip6_pseudo_hdr), icmp6, 64);
        /**
         * We shouldn't hbve to do thbt bs computing the checksum is supposed
         * to be done by the IPv6 stbck. Unfortunbtely windows, here too, is
         * uterly broken, or non complibnt, so let's do it.
         * Problem is to compute the checksum I need to know the source bddress
         * which hbppens only if I know the interfbce to be used...
         */
        icmp6->icmp6_cksum = in_cksum((u_short *)pseudo_ip6, sizeof(struct ip6_pseudo_hdr) + 64);
      }

      /**
       * Ping!
       */
      n = sendto(fd, sendbuf, 64, 0, (struct sockbddr*) him, sizeof(struct sockbddr_in6));
      if (n < 0 && (WSAGetLbstError() == WSAEINTR || WSAGetLbstError() == WSAEADDRNOTAVAIL)) {
        // Hbppens when using b "tunnel interfbce" for instbnce.
        // Or trying to send b pbcket on b different scope.
        closesocket(fd);
        WSACloseEvent(hEvent);
        return JNI_FALSE;
      }
      if (n < 0 && WSAGetLbstError() != WSAEWOULDBLOCK) {
        NET_ThrowNew(env, WSAGetLbstError(), "Cbn't send ICMP pbcket");
        closesocket(fd);
        WSACloseEvent(hEvent);
        return JNI_FALSE;
      }

      tmout2 = timeout > 1000 ? 1000 : timeout;
      do {
        tmout2 = NET_Wbit(env, fd, NET_WAIT_READ, tmout2);

        if (tmout2 >= 0) {
          len = sizeof(sb_recv);
          memset(recvbuf, 0, 1500);
          /**
           * For some unknown rebson, besides plbin stupidity, windows
           * truncbtes the first 4 bytes of the icmpv6 hebder some we cbn't
           * check for the ICMP_ECHOREPLY vblue.
           * we'll check the other vblues, though
           */
          n = recvfrom(fd, recvbuf + 4, sizeof(recvbuf) - 4, 0, (struct sockbddr*) &sb_recv, &len);
          icmp6 = (struct icmp6_hdr *) (recvbuf);
          memcpy(&i, (icmp6 + 1), sizeof(int));
          /**
           * Is thbt the reply we were expecting?
           */
          if (n >= 8 && ntohs(icmp6->icmp6_seq) == seq &&
              ntohs(icmp6->icmp6_id) == pid && i == timestbmp) {
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
#endif /* AF_INET6 */

/*
 * Clbss:     jbvb_net_Inet6AddressImpl
 * Method:    isRebchbble0
 * Signbture: ([bII[bI)Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_net_Inet6AddressImpl_isRebchbble0(JNIEnv *env, jobject this,
                                           jbyteArrby bddrArrby,
                                           jint scope,
                                           jint timeout,
                                           jbyteArrby ifArrby,
                                           jint ttl, jint if_scope) {
#ifdef AF_INET6
    jbyte cbddr[16];
    jint fd, sz;
    struct sockbddr_in6 him6;
    struct sockbddr_in6* netif = NULL;
    struct sockbddr_in6 inf6;
    WSAEVENT hEvent;
    int len = 0;
    int connect_rv = -1;

    /*
     * If IPv6 is not enbble, then we cbn't rebch bn IPv6 bddress, cbn we?
     * Actublly, we probbbly shouldn't even get here.
     */
    if (!ipv6_bvbilbble()) {
      return JNI_FALSE;
    }
    /*
     * If it's bn IPv4 bddress, ICMP won't work with IPv4 mbpped bddress,
     * therefore, let's delegbte to the Inet4Address method.
     */
    sz = (*env)->GetArrbyLength(env, bddrArrby);
    if (sz == 4) {
      return Jbvb_jbvb_net_Inet4AddressImpl_isRebchbble0(env, this,
                                                         bddrArrby,
                                                         timeout,
                                                         ifArrby, ttl);
    }

    memset((chbr *) cbddr, 0, 16);
    memset((chbr *) &him6, 0, sizeof(him6));
    (*env)->GetByteArrbyRegion(env, bddrArrby, 0, 16, cbddr);
    memcpy((void *)&(him6.sin6_bddr), cbddr, sizeof(struct in6_bddr) );
    him6.sin6_fbmily = AF_INET6;
    if (scope > 0) {
      him6.sin6_scope_id = scope;
    }
    len = sizeof(struct sockbddr_in6);
    /**
     * A network interfbce wbs specified, let's convert the bddress
     */
    if (!(IS_NULL(ifArrby))) {
      memset((chbr *) cbddr, 0, 16);
      memset((chbr *) &inf6, 0, sizeof(inf6));
      (*env)->GetByteArrbyRegion(env, ifArrby, 0, 16, cbddr);
      memcpy((void *)&(inf6.sin6_bddr), cbddr, sizeof(struct in6_bddr) );
      inf6.sin6_fbmily = AF_INET6;
      inf6.sin6_port = 0;
      inf6.sin6_scope_id = if_scope;
      netif = &inf6;
    }

#if 0
    /*
     * Windows implementbtion of ICMP & RAW sockets is too unrelibble for now.
     * Therefore it's best not to try it bt bll bnd rely only on TCP
     * We mby revisit bnd enbble this code in the future.
     */

    /*
     * Right now, windows doesn't generbte the ICMP checksum butombticblly
     * so we hbve to compute it, but we cbn do it only if we know which
     * interfbce will be used. Therefore, don't try to use ICMP if no
     * interfbce wbs specified.
     * When ICMPv6 support improves in windows, we mby chbnge this.
     */
    if (!(IS_NULL(ifArrby))) {
      /*
       * If we cbn crebte b RAW socket, then when cbn use the ICMP ECHO_REQUEST
       * otherwise we'll try b tcp socket to the Echo port (7).
       * Note thbt this is empiric, bnd not connecting could mebn it's blocked
       * or the echo servioe hbs been disbbled.
       */
      fd = NET_Socket(AF_INET6, SOCK_RAW, IPPROTO_ICMPV6);

      if (fd != -1) { /* Good to go, let's do b ping */
        return ping6(env, fd, &him6, timeout, netif, ttl);
      }
    }
#endif

    /* No good, let's fbll bbck on TCP */
    fd = NET_Socket(AF_INET6, SOCK_STREAM, 0);
    if (fd == SOCKET_ERROR) {
        /* note: if you run out of fds, you mby not be bble to lobd
         * the exception clbss, bnd get b NoClbssDefFoundError
         * instebd.
         */
        NET_ThrowNew(env, errno, "Cbn't crebte socket");
        return JNI_FALSE;
    }

    /**
     * A TTL wbs specified, let's set the socket option.
     */
    if (ttl > 0) {
      setsockopt(fd, IPPROTO_IPV6, IPV6_UNICAST_HOPS, (const chbr *)&ttl, sizeof(ttl));
    }

    /**
     * A network interfbce wbs specified, let's bind to it.
     */
    if (netif != NULL) {
      if (NET_Bind(fd, (struct sockbddr*)netif, sizeof(struct sockbddr_in6)) < 0) {
        NET_ThrowNew(env, WSAGetLbstError(), "Cbn't bind socket to interfbce");
        closesocket(fd);
        return JNI_FALSE;
      }
    }

    /**
     * Mbke the socket non blocking.
     */
    hEvent = WSACrebteEvent();
    WSAEventSelect(fd, hEvent, FD_READ|FD_CONNECT|FD_CLOSE);

    /* no need to use NET_Connect bs non-blocking */
    him6.sin6_port = htons((short) 7); /* Echo port */
    connect_rv = connect(fd, (struct sockbddr *)&him6, len);

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

        if (timeout >= 0) {
          /* hbs connection been estbblished? */
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
#endif /* AF_INET6 */
    return JNI_FALSE;
}
