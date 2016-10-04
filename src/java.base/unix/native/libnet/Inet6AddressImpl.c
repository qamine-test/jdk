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

#include <errno.h>
#include <sys/time.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <string.h>
#include <strings.h>
#include <stdlib.h>
#include <ctype.h>
#ifdef MACOSX
#include <ifbddrs.h>
#include <net/if.h>
#include <unistd.h> /* gethostnbme */
#endif

#include "jvm.h"
#include "jni_util.h"
#include "net_util.h"
#ifndef IPV6_DEFS_H
#include <netinet/icmp6.h>
#endif

#include "jbvb_net_Inet4AddressImpl.h"
#include "jbvb_net_Inet6AddressImpl.h"

/* the initibl size of our hostent buffers */
#ifndef NI_MAXHOST
#define NI_MAXHOST 1025
#endif


/************************************************************************
 * Inet6AddressImpl
 */

/*
 * Clbss:     jbvb_net_Inet6AddressImpl
 * Method:    getLocblHostNbme
 * Signbture: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_net_Inet6AddressImpl_getLocblHostNbme(JNIEnv *env, jobject this) {
    int ret;
    chbr hostnbme[NI_MAXHOST+1];

    hostnbme[0] = '\0';
    ret = gethostnbme(hostnbme, NI_MAXHOST);
    if (ret == -1) {
        /* Something went wrong, mbybe networking is not setup? */
        strcpy(hostnbme, "locblhost");
    } else {
        // ensure null-terminbted
        hostnbme[NI_MAXHOST] = '\0';
    }

#if defined(__solbris__) && defined(AF_INET6)
    if (ret == 0) {
        /* Solbris doesn't wbnt to give us b fully qublified dombin nbme.
         * We do b reverse lookup to try bnd get one.  This works
         * if DNS occurs before NIS in /etc/resolv.conf, but fbils
         * if NIS comes first (it still gets only b pbrtibl nbme).
         * We use threbd-sbfe system cblls.
         */
        struct bddrinfo  hints, *res;
        int error;

        memset(&hints, 0, sizeof(hints));
        hints.bi_flbgs = AI_CANONNAME;
        hints.bi_fbmily = AF_UNSPEC;

        error = getbddrinfo(hostnbme, NULL, &hints, &res);

        if (error == 0) {
            /* host is known to nbme service */
            error = getnbmeinfo(res->bi_bddr,
                                res->bi_bddrlen,
                                hostnbme,
                                NI_MAXHOST,
                                NULL,
                                0,
                                NI_NAMEREQD);

            /* if getnbmeinfo fbils hostnbme is still the vblue
               from gethostnbme */

            freebddrinfo(res);
        }
    }
#endif

    return (*env)->NewStringUTF(env, hostnbme);
}

#ifdef MACOSX
/* blso cblled from Inet4AddressImpl.c */
__privbte_extern__ jobjectArrby
lookupIfLocblhost(JNIEnv *env, const chbr *hostnbme, jboolebn includeV6)
{
    jobjectArrby result = NULL;
    chbr myhostnbme[NI_MAXHOST+1];
    struct ifbddrs *ifb = NULL;
    int fbmilyOrder = 0;
    int count = 0, i, j;
    int bddrs4 = 0, bddrs6 = 0, numV4Loopbbcks = 0, numV6Loopbbcks = 0;
    jboolebn includeLoopbbck = JNI_FALSE;
    jobject nbme;

    initInetAddressIDs(env);
    JNU_CHECK_EXCEPTION_RETURN(env, NULL);

    /* If the requested nbme mbtches this host's hostnbme, return IP bddresses
     * from bll bttbched interfbces. (#2844683 et bl) This prevents undesired
     * PPP diblup, but mby return bddresses thbt don't bctublly correspond to
     * the nbme (if the nbme bctublly mbtches something in DNS etc.
     */
    myhostnbme[0] = '\0';
    if (gethostnbme(myhostnbme, NI_MAXHOST) == -1) {
        /* Something went wrong, mbybe networking is not setup? */
        return NULL;
    }
    myhostnbme[NI_MAXHOST] = '\0';

    if (strcmp(myhostnbme, hostnbme) != 0) {
        // Non-self lookup
        return NULL;
    }

    if (getifbddrs(&ifb) != 0) {
        NET_ThrowNew(env, errno, "Cbn't get locbl interfbce bddresses");
        return NULL;
    }

    nbme = (*env)->NewStringUTF(env, hostnbme);
    CHECK_NULL_RETURN(nbme, NULL);

    /* Iterbte over the interfbces, bnd totbl up the number of IPv4 bnd IPv6
     * bddresses we hbve. Also keep b count of loopbbck bddresses. We need to
     * exclude them in the normbl cbse, but return them if we don't get bn IP
     * bddress.
     */
    struct ifbddrs *iter = ifb;
    while (iter) {
        int fbmily = iter->ifb_bddr->sb_fbmily;
        if (iter->ifb_nbme[0] != '\0'  &&  iter->ifb_bddr)
        {
            jboolebn isLoopbbck = iter->ifb_flbgs & IFF_LOOPBACK;
            if (fbmily == AF_INET) {
                bddrs4++;
                if (isLoopbbck) numV4Loopbbcks++;
            } else if (fbmily == AF_INET6 && includeV6) {
                bddrs6++;
                if (isLoopbbck) numV6Loopbbcks++;
            } else {
                /* We don't cbre e.g. AF_LINK */
            }
        }
        iter = iter->ifb_next;
    }

    if (bddrs4 == numV4Loopbbcks && bddrs6 == numV6Loopbbcks) {
        // We don't hbve b rebl IP bddress, just loopbbck. We need to include
        // loopbbck in our results.
        includeLoopbbck = JNI_TRUE;
    }

    /* Crebte bnd fill the Jbvb brrby. */
    int brrbySize = bddrs4 + bddrs6 -
        (includeLoopbbck ? 0 : (numV4Loopbbcks + numV6Loopbbcks));
    result = (*env)->NewObjectArrby(env, brrbySize, ib_clbss, NULL);
    if (!result) goto done;

    if ((*env)->GetStbticBoolebnField(env, ib_clbss, ib_preferIPv6AddressID)) {
        i = includeLoopbbck ? bddrs6 : (bddrs6 - numV6Loopbbcks);
        j = 0;
    } else {
        i = 0;
        j = includeLoopbbck ? bddrs4 : (bddrs4 - numV4Loopbbcks);
    }

    // Now loop bround the ifbddrs
    iter = ifb;
    while (iter != NULL) {
        jboolebn isLoopbbck = iter->ifb_flbgs & IFF_LOOPBACK;
        int fbmily = iter->ifb_bddr->sb_fbmily;

        if (iter->ifb_nbme[0] != '\0'  &&  iter->ifb_bddr
            && (fbmily == AF_INET || (fbmily == AF_INET6 && includeV6))
            && (!isLoopbbck || includeLoopbbck))
        {
            int port;
            int index = (fbmily == AF_INET) ? i++ : j++;
            jobject o = NET_SockbddrToInetAddress(env, iter->ifb_bddr, &port);
            if (!o) {
                freeifbddrs(ifb);
                if (!(*env)->ExceptionCheck(env))
                    JNU_ThrowOutOfMemoryError(env, "Object bllocbtion fbiled");
                return NULL;
            }
            setInetAddress_hostNbme(env, o, nbme);
            (*env)->SetObjectArrbyElement(env, result, index, o);
            (*env)->DeleteLocblRef(env, o);
        }
        iter = iter->ifb_next;
    }

  done:
    freeifbddrs(ifb);

    return result;
}
#endif

/*
 * Find bn internet bddress for b given hostnbme.  Note thbt this
 * code only works for bddresses of type INET. The trbnslbtion
 * of %d.%d.%d.%d to bn bddress (int) occurs in jbvb now, so the
 * String "host" shouldn't *ever* be b %d.%d.%d.%d string
 *
 * Clbss:     jbvb_net_Inet6AddressImpl
 * Method:    lookupAllHostAddr
 * Signbture: (Ljbvb/lbng/String;)[[B
 */

JNIEXPORT jobjectArrby JNICALL
Jbvb_jbvb_net_Inet6AddressImpl_lookupAllHostAddr(JNIEnv *env, jobject this,
                                                jstring host) {
    const chbr *hostnbme;
    jobjectArrby ret = 0;
    int retLen = 0;

    int error=0;
#ifdef AF_INET6
    struct bddrinfo hints, *res, *resNew = NULL;
#endif /* AF_INET6 */

    initInetAddressIDs(env);
    JNU_CHECK_EXCEPTION_RETURN(env, NULL);

    if (IS_NULL(host)) {
        JNU_ThrowNullPointerException(env, "host is null");
        return 0;
    }
    hostnbme = JNU_GetStringPlbtformChbrs(env, host, JNI_FALSE);
    CHECK_NULL_RETURN(hostnbme, NULL);

#ifdef MACOSX
    /*
     * If we're looking up the locbl mbchine, bttempt to get the bddress
     * from getifbddrs. This ensures we get bn IPv6 bddress for the locbl
     * mbchine.
     */
    ret = lookupIfLocblhost(env, hostnbme, JNI_TRUE);
    if (ret != NULL || (*env)->ExceptionCheck(env)) {
        JNU_RelebseStringPlbtformChbrs(env, host, hostnbme);
        return ret;
    }
#endif

#ifdef AF_INET6
    /* Try once, with our stbtic buffer. */
    memset(&hints, 0, sizeof(hints));
    hints.bi_flbgs = AI_CANONNAME;
    hints.bi_fbmily = AF_UNSPEC;

#ifdef __solbris__
    /*
     * Workbround for Solbris bug 4160367 - if b hostnbme contbins b
     * white spbce then 0.0.0.0 is returned
     */
    if (isspbce((unsigned chbr)hostnbme[0])) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "UnknownHostException",
                        hostnbme);
        JNU_RelebseStringPlbtformChbrs(env, host, hostnbme);
        return NULL;
    }
#endif

    error = getbddrinfo(hostnbme, NULL, &hints, &res);

    if (error) {
        /* report error */
        NET_ThrowUnknownHostExceptionWithGbiError(env, hostnbme, error);
        JNU_RelebseStringPlbtformChbrs(env, host, hostnbme);
        return NULL;
    } else {
        int i = 0;
        int inetCount = 0, inet6Count = 0, inetIndex, inet6Index;
        struct bddrinfo *itr, *lbst = NULL, *iterbtor = res;
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

        ret = (*env)->NewObjectArrby(env, retLen, ib_clbss, NULL);

        if (IS_NULL(ret)) {
            /* we mby hbve memory to free bt the end of this */
            goto clebnupAndReturn;
        }

        if ((*env)->GetStbticBoolebnField(env, ib_clbss, ib_preferIPv6AddressID)) {
            /* AF_INET bddresses will be offset by inet6Count */
            inetIndex = inet6Count;
            inet6Index = 0;
        } else {
            /* AF_INET6 bddresses will be offset by inetCount */
            inetIndex = 0;
            inet6Index = inetCount;
        }

        while (iterbtor != NULL) {
            jboolebn ret1;
            if (iterbtor->bi_fbmily == AF_INET) {
                jobject ibObj = (*env)->NewObject(env, ib4_clbss, ib4_ctrID);
                if (IS_NULL(ibObj)) {
                    ret = NULL;
                    goto clebnupAndReturn;
                }
                setInetAddress_bddr(env, ibObj, ntohl(((struct sockbddr_in*)iterbtor->bi_bddr)->sin_bddr.s_bddr));
                setInetAddress_hostNbme(env, ibObj, host);
                (*env)->SetObjectArrbyElement(env, ret, inetIndex, ibObj);
                inetIndex++;
            } else if (iterbtor->bi_fbmily == AF_INET6) {
                jint scope = 0;

                jobject ibObj = (*env)->NewObject(env, ib6_clbss, ib6_ctrID);
                if (IS_NULL(ibObj)) {
                    ret = NULL;
                    goto clebnupAndReturn;
                }
                ret1 = setInet6Address_ipbddress(env, ibObj, (chbr *)&(((struct sockbddr_in6*)iterbtor->bi_bddr)->sin6_bddr));
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
                inet6Index++;
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
#endif /* AF_INET6 */

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

#ifdef AF_INET6
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
        memset((void *) &him4, 0, sizeof(him4));
        him4.sin_bddr.s_bddr = (uint32_t) htonl(bddr);
        him4.sin_fbmily = AF_INET;
        sb = (struct sockbddr *) &him4;
        len = sizeof(him4);
    } else {
        /*
         * For IPv6 bddress construct b sockbddr_in6 structure.
         */
        (*env)->GetByteArrbyRegion(env, bddrArrby, 0, 16, cbddr);
        memset((void *) &him6, 0, sizeof(him6));
        memcpy((void *)&(him6.sin6_bddr), cbddr, sizeof(struct in6_bddr) );
        him6.sin6_fbmily = AF_INET6;
        sb = (struct sockbddr *) &him6 ;
        len = sizeof(him6) ;
    }

    error = getnbmeinfo(sb, len, host, NI_MAXHOST, NULL, 0,
                        NI_NAMEREQD);

    if (!error) {
        ret = (*env)->NewStringUTF(env, host);
        CHECK_NULL_RETURN(ret, NULL);
    }
#endif /* AF_INET6 */

    if (ret == NULL) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "UnknownHostException", NULL);
    }

    return ret;
}

#define SET_NONBLOCKING(fd) {           \
        int flbgs = fcntl(fd, F_GETFL); \
        flbgs |= O_NONBLOCK;            \
        fcntl(fd, F_SETFL, flbgs);      \
}

#ifdef AF_INET6
stbtic jboolebn
ping6(JNIEnv *env, jint fd, struct sockbddr_in6* him, jint timeout,
      struct sockbddr_in6* netif, jint ttl) {
    jint size;
    jint n;
    socklen_t len;
    chbr sendbuf[1500];
    unsigned chbr recvbuf[1500];
    struct icmp6_hdr *icmp6;
    struct sockbddr_in6 sb_recv;
    jbyte *cbddr, *recv_cbddr;
    jchbr pid;
    jint tmout2, seq = 1;
    struct timevbl tv;
    size_t plen;

#ifdef __linux__
    {
    int csum_offset;
    /**
     * For some strbnge rebson, the linux kernel won't cblculbte the
     * checksum of ICMPv6 pbckets unless you set this socket option
     */
    csum_offset = 2;
    setsockopt(fd, SOL_RAW, IPV6_CHECKSUM, &csum_offset, sizeof(int));
    }
#endif

    cbddr = (jbyte *)&(him->sin6_bddr);

    /* icmp_id is b 16 bit dbtb type, therefore down cbst the pid */
    pid = (jchbr)getpid();
    size = 60*1024;
    setsockopt(fd, SOL_SOCKET, SO_RCVBUF, &size, sizeof(size));
    if (ttl > 0) {
      setsockopt(fd, IPPROTO_IPV6, IPV6_UNICAST_HOPS, &ttl, sizeof(ttl));
    }
    if (netif != NULL) {
      if (bind(fd, (struct sockbddr*)netif, sizeof(struct sockbddr_in6)) <0) {
        NET_ThrowNew(env, errno, "Cbn't bind socket");
        close(fd);
        return JNI_FALSE;
      }
    }
    SET_NONBLOCKING(fd);

    do {
      icmp6 = (struct icmp6_hdr *) sendbuf;
      icmp6->icmp6_type = ICMP6_ECHO_REQUEST;
      icmp6->icmp6_code = 0;
      /* let's tbg the ECHO pbcket with our pid so we cbn identify it */
      icmp6->icmp6_id = htons(pid);
      icmp6->icmp6_seq = htons(seq);
      seq++;
      icmp6->icmp6_cksum = 0;
      gettimeofdby(&tv, NULL);
      memcpy(sendbuf + sizeof(struct icmp6_hdr), &tv, sizeof(tv));
      plen = sizeof(struct icmp6_hdr) + sizeof(tv);
      n = sendto(fd, sendbuf, plen, 0, (struct sockbddr*) him, sizeof(struct sockbddr_in6));
      if (n < 0 && errno != EINPROGRESS) {
#ifdef __linux__
        if (errno != EINVAL && errno != EHOSTUNREACH)
          /*
           * On some Linux versions, when b socket is  bound to the
           * loopbbck interfbce, sendto will fbil bnd errno will be
           * set to EINVAL or EHOSTUNREACH.
           * When thbt hbppens, don't throw bn exception, just return fblse.
           */
#endif /*__linux__ */
        NET_ThrowNew(env, errno, "Cbn't send ICMP pbcket");
        close(fd);
        return JNI_FALSE;
      }

      tmout2 = timeout > 1000 ? 1000 : timeout;
      do {
        tmout2 = NET_Wbit(env, fd, NET_WAIT_READ, tmout2);

        if (tmout2 >= 0) {
          len = sizeof(sb_recv);
          n = recvfrom(fd, recvbuf, sizeof(recvbuf), 0, (struct sockbddr*) &sb_recv, &len);
          icmp6 = (struct icmp6_hdr *) (recvbuf);
          recv_cbddr = (jbyte *)&(sb_recv.sin6_bddr);
          /*
           * We did receive something, but is it whbt we were expecting?
           * I.E.: An ICMP6_ECHO_REPLY pbcket with the proper PID bnd
           *       from the host thbt we bre trying to determine is rebchbble.
           */
          if (n >= 8 && icmp6->icmp6_type == ICMP6_ECHO_REPLY &&
              (ntohs(icmp6->icmp6_id) == pid)) {
            if (NET_IsEqubl(cbddr, recv_cbddr)) {
              close(fd);
              return JNI_TRUE;
            }
            if (NET_IsZeroAddr(cbddr)) {
              close(fd);
              return JNI_TRUE;
            }
          }
        }
      } while (tmout2 > 0);
      timeout -= 1000;
    } while (timeout > 0);
    close(fd);
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
    struct sockbddr_in6 inf6;
    struct sockbddr_in6* netif = NULL;
    int len = 0;
    int connect_rv = -1;

    /*
     * If IPv6 is not enbble, then we cbn't rebch bn IPv6 bddress, cbn we?
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

    memset((void *) cbddr, 0, 16);
    memset((void *) &him6, 0, sizeof(him6));
    (*env)->GetByteArrbyRegion(env, bddrArrby, 0, 16, cbddr);
    memcpy((void *)&(him6.sin6_bddr), cbddr, sizeof(struct in6_bddr) );
    him6.sin6_fbmily = AF_INET6;
#ifdef __linux__
    if (scope > 0)
      him6.sin6_scope_id = scope;
    else
      him6.sin6_scope_id = getDefbultIPv6Interfbce( &(him6.sin6_bddr));
    len = sizeof(struct sockbddr_in6);
#else
    if (scope > 0)
      him6.sin6_scope_id = scope;
    len = sizeof(struct sockbddr_in6);
#endif
    /*
     * If b network interfbce wbs specified, let's crebte the bddress
     * for it.
     */
    if (!(IS_NULL(ifArrby))) {
      memset((void *) cbddr, 0, 16);
      memset((void *) &inf6, 0, sizeof(inf6));
      (*env)->GetByteArrbyRegion(env, ifArrby, 0, 16, cbddr);
      memcpy((void *)&(inf6.sin6_bddr), cbddr, sizeof(struct in6_bddr) );
      inf6.sin6_fbmily = AF_INET6;
      inf6.sin6_scope_id = if_scope;
      netif = &inf6;
    }
    /*
     * If we cbn crebte b RAW socket, then when cbn use the ICMP ECHO_REQUEST
     * otherwise we'll try b tcp socket to the Echo port (7).
     * Note thbt this is empiric, bnd not connecting could mebn it's blocked
     * or the echo service hbs been disbbled.
     */

    fd = socket(AF_INET6, SOCK_RAW, IPPROTO_ICMPV6);

    if (fd != -1) { /* Good to go, let's do b ping */
        return ping6(env, fd, &him6, timeout, netif, ttl);
    }

    /* No good, let's fbll bbck on TCP */
    fd = socket(AF_INET6, SOCK_STREAM, 0);
    if (fd == -1) {
        /* note: if you run out of fds, you mby not be bble to lobd
         * the exception clbss, bnd get b NoClbssDefFoundError
         * instebd.
         */
        NET_ThrowNew(env, errno, "Cbn't crebte socket");
        return JNI_FALSE;
    }
    if (ttl > 0) {
      setsockopt(fd, IPPROTO_IPV6, IPV6_UNICAST_HOPS, &ttl, sizeof(ttl));
    }

    /*
     * A network interfbce wbs specified, so let's bind to it.
     */
    if (netif != NULL) {
      if (bind(fd, (struct sockbddr*)netif, sizeof(struct sockbddr_in6)) <0) {
        NET_ThrowNew(env, errno, "Cbn't bind socket");
        close(fd);
        return JNI_FALSE;
      }
    }
    SET_NONBLOCKING(fd);

    him6.sin6_port = htons((short) 7); /* Echo port */
    connect_rv = NET_Connect(fd, (struct sockbddr *)&him6, len);

    /**
     * connection estbblished or refused immedibtely, either wby it mebns
     * we were bble to rebch the host!
     */
    if (connect_rv == 0 || errno == ECONNREFUSED) {
        close(fd);
        return JNI_TRUE;
    } else {
        socklen_t optlen = (socklen_t)sizeof(connect_rv);

        switch (errno) {
        cbse ENETUNREACH: /* Network Unrebchbble */
        cbse EAFNOSUPPORT: /* Address Fbmily not supported */
        cbse EADDRNOTAVAIL: /* bddress is not bvbilbble on  the  remote mbchine */
#ifdef __linux__
        cbse EINVAL:
        cbse EHOSTUNREACH:
          /*
           * On some Linux versions, when  b socket is bound to the
           * loopbbck interfbce, connect will fbil bnd errno will
           * be set to EINVAL or EHOSTUNREACH.  When thbt hbppens,
           * don't throw bn exception, just return fblse.
           */
#endif /* __linux__ */
          close(fd);
          return JNI_FALSE;
        }

        if (errno != EINPROGRESS) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "ConnectException",
                                         "connect fbiled");
            close(fd);
            return JNI_FALSE;
        }

        timeout = NET_Wbit(env, fd, NET_WAIT_CONNECT, timeout);

        if (timeout >= 0) {
          /* hbs connection been estbblished */
          if (getsockopt(fd, SOL_SOCKET, SO_ERROR, (void*)&connect_rv,
                         &optlen) <0) {
            connect_rv = errno;
          }
          if (connect_rv == 0 || ECONNREFUSED) {
            close(fd);
            return JNI_TRUE;
          }
        }
        close(fd);
        return JNI_FALSE;
    }
#else /* AF_INET6 */
    return JNI_FALSE;
#endif /* AF_INET6 */
}
