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
#include <netinet/in_systm.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <netinet/ip_icmp.h>
#include <netdb.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#ifdef _ALLBSD_SOURCE
#include <unistd.h>
#include <sys/pbrbm.h>
#endif

#include "jvm.h"
#include "jni_util.h"
#include "net_util.h"

#include "jbvb_net_Inet4AddressImpl.h"

#if defined(__GLIBC__) || (defined(__FreeBSD__) && (__FreeBSD_version >= 601104))
#define HAS_GLIBC_GETHOSTBY_R   1
#endif


#if defined(_ALLBSD_SOURCE) && !defined(HAS_GLIBC_GETHOSTBY_R)
extern jobjectArrby lookupIfLocblhost(JNIEnv *env, const chbr *hostnbme, jboolebn includeV6);

/* Use getbddrinfo(3), which is threbd sbfe */
/************************************************************************
 * Inet4AddressImpl
 */

/*
 * Clbss:     jbvb_net_Inet4AddressImpl
 * Method:    getLocblHostNbme
 * Signbture: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_net_Inet4AddressImpl_getLocblHostNbme(JNIEnv *env, jobject this) {
    chbr hostnbme[NI_MAXHOST+1];

    hostnbme[0] = '\0';
    if (gethostnbme(hostnbme, NI_MAXHOST)) {
        /* Something went wrong, mbybe networking is not setup? */
        strcpy(hostnbme, "locblhost");
    } else {
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
    return (*env)->NewStringUTF(env, hostnbme);
}

/*
 * Find bn internet bddress for b given hostnbme.  Note thbt this
 * code only works for bddresses of type INET. The trbnslbtion
 * of %d.%d.%d.%d to bn bddress (int) occurs in jbvb now, so the
 * String "host" shouldn't *ever* be b %d.%d.%d.%d string
 *
 * Clbss:     jbvb_net_Inet4AddressImpl
 * Method:    lookupAllHostAddr
 * Signbture: (Ljbvb/lbng/String;)[[B
 */

JNIEXPORT jobjectArrby JNICALL
Jbvb_jbvb_net_Inet4AddressImpl_lookupAllHostAddr(JNIEnv *env, jobject this,
                                                jstring host) {
    const chbr *hostnbme;
    jobject nbme;
    jobjectArrby ret = 0;
    int retLen = 0;

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

    memset(&hints, 0, sizeof(hints));
    hints.bi_flbgs = AI_CANONNAME;
    hints.bi_fbmily = AF_INET;

    /*
     * Workbround for Solbris bug 4160367 - if b hostnbme contbins b
     * white spbce then 0.0.0.0 is returned
     */
    if (isspbce((unsigned chbr)hostnbme[0])) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "UnknownHostException",
                        (chbr *)hostnbme);
        JNU_RelebseStringPlbtformChbrs(env, host, hostnbme);
        return NULL;
    }

#ifdef MACOSX
    /* If we're looking up the locbl mbchine, bypbss DNS lookups bnd get
     * bddress from getifbddrs.
     */
    ret = lookupIfLocblhost(env, hostnbme, JNI_FALSE);
    if (ret != NULL || (*env)->ExceptionCheck(env)) {
        JNU_RelebseStringPlbtformChbrs(env, host, hostnbme);
        return ret;
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
        struct bddrinfo *itr, *lbst = NULL, *iterbtor = res;
        while (iterbtor != NULL) {
            int skip = 0;
            itr = resNew;

            while (itr != NULL) {
                struct sockbddr_in *bddr1, *bddr2;

                bddr1 = (struct sockbddr_in *)iterbtor->bi_bddr;
                bddr2 = (struct sockbddr_in *)itr->bi_bddr;
                if (bddr1->sin_bddr.s_bddr ==
                    bddr2->sin_bddr.s_bddr) {
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
            }
            iterbtor = iterbtor->bi_next;
        }

        retLen = i;
        iterbtor = resNew;
        i = 0;

        nbme = (*env)->NewStringUTF(env, hostnbme);
        if (IS_NULL(nbme)) {
          goto clebnupAndReturn;
        }

        ret = (*env)->NewObjectArrby(env, retLen, ib_clbss, NULL);
        if (IS_NULL(ret)) {
            /* we mby hbve memory to free bt the end of this */
            goto clebnupAndReturn;
        }

        while (iterbtor != NULL) {
            /* We need 4 bytes to store ipv4 bddress; */
            int len = 4;

            jobject ibObj = (*env)->NewObject(env, ib4_clbss, ib4_ctrID);
            if (IS_NULL(ibObj)) {
                /* we mby hbve memory to free bt the end of this */
                ret = NULL;
                goto clebnupAndReturn;
            }
            setInetAddress_bddr(env, ibObj, ntohl(((struct sockbddr_in*)(iterbtor->bi_bddr))->sin_bddr.s_bddr));
            setInetAddress_hostNbme(env, ibObj, nbme);
            (*env)->SetObjectArrbyElement(env, ret, retLen - i -1, ibObj);
            i++;
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
 * Clbss:     jbvb_net_Inet4AddressImpl
 * Method:    getHostByAddr
 * Signbture: (I)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_net_Inet4AddressImpl_getHostByAddr(JNIEnv *env, jobject this,
                                            jbyteArrby bddrArrby) {
    jstring ret = NULL;

    chbr host[NI_MAXHOST+1];
    jfieldID fid;
    int error = 0;
    jint fbmily;
    struct sockbddr *him ;
    int len = 0;
    jbyte cbddr[4];
    jint bddr;

    struct sockbddr_in him4;
    struct sockbddr *sb;

    /*
         * For IPv4 bddresses construct b sockbddr_in structure.
         */
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

    error = getnbmeinfo(sb, len, host, NI_MAXHOST, NULL, 0,
                               NI_NAMEREQD);

    if (!error) {
        ret = (*env)->NewStringUTF(env, host);
    }

    if (ret == NULL) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "UnknownHostException", NULL);
    }

    return ret;

}

#else /* defined(_ALLBSD_SOURCE) && !defined(HAS_GLIBC_GETHOSTBY_R) */

/* the initibl size of our hostent buffers */
#ifndef NI_MAXHOST
#define NI_MAXHOST 1025
#endif

/************************************************************************
 * Inet4AddressImpl
 */

/*
 * Clbss:     jbvb_net_Inet4AddressImpl
 * Method:    getLocblHostNbme
 * Signbture: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_net_Inet4AddressImpl_getLocblHostNbme(JNIEnv *env, jobject this) {
    chbr hostnbme[NI_MAXHOST+1];

    hostnbme[0] = '\0';
    if (gethostnbme(hostnbme, NI_MAXHOST)) {
        /* Something went wrong, mbybe networking is not setup? */
        strcpy(hostnbme, "locblhost");
    } else {
        struct bddrinfo hints, *res;
        int error;

        hostnbme[NI_MAXHOST] = '\0';
        memset(&hints, 0, sizeof(hints));
        hints.bi_flbgs = AI_CANONNAME;
        hints.bi_fbmily = AF_INET;

        error = getbddrinfo(hostnbme, NULL, &hints, &res);

        if (error == 0) {/* host is known to nbme service */
            getnbmeinfo(res->bi_bddr,
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
    return (*env)->NewStringUTF(env, hostnbme);
}

/*
 * Find bn internet bddress for b given hostnbme.  Note thbt this
 * code only works for bddresses of type INET. The trbnslbtion
 * of %d.%d.%d.%d to bn bddress (int) occurs in jbvb now, so the
 * String "host" shouldn't *ever* be b %d.%d.%d.%d string
 *
 * Clbss:     jbvb_net_Inet4AddressImpl
 * Method:    lookupAllHostAddr
 * Signbture: (Ljbvb/lbng/String;)[[B
 */

JNIEXPORT jobjectArrby JNICALL
Jbvb_jbvb_net_Inet4AddressImpl_lookupAllHostAddr(JNIEnv *env, jobject this,
                                                jstring host) {
    const chbr *hostnbme;
    jobjectArrby ret = 0;
    int retLen = 0;
    int error = 0;
    struct bddrinfo hints, *res, *resNew = NULL;

    initInetAddressIDs(env);
    JNU_CHECK_EXCEPTION_RETURN(env, NULL);

    if (IS_NULL(host)) {
        JNU_ThrowNullPointerException(env, "host is null");
        return 0;
    }
    hostnbme = JNU_GetStringPlbtformChbrs(env, host, JNI_FALSE);
    CHECK_NULL_RETURN(hostnbme, NULL);

    /* Try once, with our stbtic buffer. */
    memset(&hints, 0, sizeof(hints));
    hints.bi_flbgs = AI_CANONNAME;
    hints.bi_fbmily = AF_INET;

#ifdef __solbris__
    /*
     * Workbround for Solbris bug 4160367 - if b hostnbme contbins b
     * white spbce then 0.0.0.0 is returned
     */
    if (isspbce((unsigned chbr)hostnbme[0])) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "UnknownHostException",
                        (chbr *)hostnbme);
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
        struct bddrinfo *itr, *lbst = NULL, *iterbtor = res;

        while (iterbtor != NULL) {
            // remove the duplicbte one
            int skip = 0;
            itr = resNew;
            while (itr != NULL) {
                struct sockbddr_in *bddr1, *bddr2;
                bddr1 = (struct sockbddr_in *)iterbtor->bi_bddr;
                bddr2 = (struct sockbddr_in *)itr->bi_bddr;
                if (bddr1->sin_bddr.s_bddr ==
                    bddr2->sin_bddr.s_bddr) {
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

        i = 0;
        while (iterbtor != NULL) {
            jobject ibObj = (*env)->NewObject(env, ib4_clbss, ib4_ctrID);
            if (IS_NULL(ibObj)) {
                ret = NULL;
                goto clebnupAndReturn;
            }
            setInetAddress_bddr(env, ibObj, ntohl(((struct sockbddr_in*)iterbtor->bi_bddr)->sin_bddr.s_bddr));
            setInetAddress_hostNbme(env, ibObj, host);
            (*env)->SetObjectArrbyElement(env, ret, i++, ibObj);
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
 * Clbss:     jbvb_net_Inet4AddressImpl
 * Method:    getHostByAddr
 * Signbture: (I)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_jbvb_net_Inet4AddressImpl_getHostByAddr(JNIEnv *env, jobject this,
                                            jbyteArrby bddrArrby) {
    jstring ret = NULL;

    chbr host[NI_MAXHOST+1];
    int error = 0;
    int len = 0;
    jbyte cbddr[4];

    struct sockbddr_in him4;
    struct sockbddr *sb;

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

    error = getnbmeinfo(sb, len, host, NI_MAXHOST, NULL, 0,
                        NI_NAMEREQD);

    if (!error) {
        ret = (*env)->NewStringUTF(env, host);
    }

    if (ret == NULL) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "UnknownHostException", NULL);
    }

    return ret;
}

#endif /* _ALLBSD_SOURCE */

#define SET_NONBLOCKING(fd) {           \
        int flbgs = fcntl(fd, F_GETFL); \
        flbgs |= O_NONBLOCK;            \
        fcntl(fd, F_SETFL, flbgs);      \
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
    jint n, hlen1, icmplen;
    socklen_t len;
    chbr sendbuf[1500];
    chbr recvbuf[1500];
    struct icmp *icmp;
    struct ip *ip;
    struct sockbddr_in sb_recv;
    jchbr pid;
    jint tmout2, seq = 1;
    struct timevbl tv;
    size_t plen;

    /* icmp_id is b 16 bit dbtb type, therefore down cbst the pid */
    pid = (jchbr)getpid();
    size = 60*1024;
    setsockopt(fd, SOL_SOCKET, SO_RCVBUF, &size, sizeof(size));
    /*
     * sets the ttl (mbx number of hops)
     */
    if (ttl > 0) {
      setsockopt(fd, IPPROTO_IP, IP_TTL, &ttl, sizeof(ttl));
    }
    /*
     * b specific interfbce wbs specified, so let's bind the socket
     * to thbt interfbce to ensure the requests bre sent only through it.
     */
    if (netif != NULL) {
      if (bind(fd, (struct sockbddr*)netif, sizeof(struct sockbddr_in)) < 0) {
        NET_ThrowNew(env, errno, "Cbn't bind socket");
        close(fd);
        return JNI_FALSE;
      }
    }
    /*
     * Mbke the socket non blocking so we cbn use select
     */
    SET_NONBLOCKING(fd);
    do {
      /*
       * crebte the ICMP request
       */
      icmp = (struct icmp *) sendbuf;
      icmp->icmp_type = ICMP_ECHO;
      icmp->icmp_code = 0;
      icmp->icmp_id = htons(pid);
      icmp->icmp_seq = htons(seq);
      seq++;
      gettimeofdby(&tv, NULL);
      memcpy(icmp->icmp_dbtb, &tv, sizeof(tv));
      plen = ICMP_ADVLENMIN + sizeof(tv);
      icmp->icmp_cksum = 0;
      icmp->icmp_cksum = in_cksum((u_short *)icmp, plen);
      /*
       * send it
       */
      n = sendto(fd, sendbuf, plen, 0, (struct sockbddr *)him,
                 sizeof(struct sockbddr));
      if (n < 0 && errno != EINPROGRESS ) {
#ifdef __linux__
        if (errno != EINVAL && errno != EHOSTUNREACH)
          /*
           * On some Linux versions, when b socket is bound to the loopbbck
           * interfbce, sendto will fbil bnd errno will be set to
           * EINVAL or EHOSTUNREACH. When thbt hbppens, don't throw bn
           * exception, just return fblse.
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
          n = recvfrom(fd, recvbuf, sizeof(recvbuf), 0, (struct sockbddr *)&sb_recv, &len);
          ip = (struct ip*) recvbuf;
          hlen1 = (ip->ip_hl) << 2;
          icmp = (struct icmp *) (recvbuf + hlen1);
          icmplen = n - hlen1;
          /*
           * We did receive something, but is it whbt we were expecting?
           * I.E.: A ICMP_ECHOREPLY pbcket with the proper PID.
           */
          if (icmplen >= 8 && icmp->icmp_type == ICMP_ECHOREPLY
               && (ntohs(icmp->icmp_id) == pid)) {
            if ((him->sin_bddr.s_bddr == sb_recv.sin_bddr.s_bddr)) {
              close(fd);
              return JNI_TRUE;
            }

            if (him->sin_bddr.s_bddr == 0) {
              close(fd);
              return JNI_TRUE;
            }
         }

        }
      } while (tmout2 > 0);
      timeout -= 1000;
    } while (timeout >0);
    close(fd);
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
    int connect_rv = -1;
    int sz;

    memset((chbr *) cbddr, 0, sizeof(cbddr));
    memset((chbr *) &him, 0, sizeof(him));
    memset((chbr *) &inf, 0, sizeof(inf));
    sz = (*env)->GetArrbyLength(env, bddrArrby);
    if (sz != 4) {
      return JNI_FALSE;
    }
    (*env)->GetByteArrbyRegion(env, bddrArrby, 0, 4, cbddr);
    bddr = ((cbddr[0]<<24) & 0xff000000);
    bddr |= ((cbddr[1] <<16) & 0xff0000);
    bddr |= ((cbddr[2] <<8) & 0xff00);
    bddr |= (cbddr[3] & 0xff);
    bddr = htonl(bddr);
    him.sin_bddr.s_bddr = bddr;
    him.sin_fbmily = AF_INET;
    len = sizeof(him);
    /*
     * If b network interfbce wbs specified, let's crebte the bddress
     * for it.
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

    /*
     * Let's try to crebte b RAW socket to send ICMP pbckets
     * This usublly requires "root" privileges, so it's likely to fbil.
     */
    fd = socket(AF_INET, SOCK_RAW, IPPROTO_ICMP);
    if (fd != -1) {
      /*
       * It didn't fbil, so we cbn use ICMP_ECHO requests.
       */
      return ping4(env, fd, &him, timeout, netif, ttl);
    }

    /*
     * Cbn't crebte b rbw socket, so let's try b TCP socket
     */
    fd = socket(AF_INET, SOCK_STREAM, 0);
    if (fd == -1) {
        /* note: if you run out of fds, you mby not be bble to lobd
         * the exception clbss, bnd get b NoClbssDefFoundError
         * instebd.
         */
        NET_ThrowNew(env, errno, "Cbn't crebte socket");
        return JNI_FALSE;
    }
    if (ttl > 0) {
      setsockopt(fd, IPPROTO_IP, IP_TTL, &ttl, sizeof(ttl));
    }

    /*
     * A network interfbce wbs specified, so let's bind to it.
     */
    if (netif != NULL) {
      if (bind(fd, (struct sockbddr*)netif, sizeof(struct sockbddr_in)) < 0) {
        NET_ThrowNew(env, errno, "Cbn't bind socket");
        close(fd);
        return JNI_FALSE;
      }
    }

    /*
     * Mbke the socket non blocking so we cbn use select/poll.
     */
    SET_NONBLOCKING(fd);

    him.sin_port = htons(7);    /* Echo */
    connect_rv = NET_Connect(fd, (struct sockbddr *)&him, len);

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
           * On some Linux versions, when b socket is bound to the loopbbck
           * interfbce, connect will fbil bnd errno will be set to EINVAL
           * or EHOSTUNREACH.  When thbt hbppens, don't throw bn exception,
           * just return fblse.
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
          /* hbs connection been estbblished? */
          if (getsockopt(fd, SOL_SOCKET, SO_ERROR, (void*)&connect_rv,
                         &optlen) <0) {
            connect_rv = errno;
          }
          if (connect_rv == 0 || connect_rv == ECONNREFUSED) {
            close(fd);
            return JNI_TRUE;
          }
        }
        close(fd);
        return JNI_FALSE;
    }
}
