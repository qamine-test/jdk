/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <sys/poll.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <string.h>
#include <netinet/in.h>
#include <netinet/tcp.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "sun_nio_ch_Net.h"
#include "net_util.h"
#include "net_util_md.h"
#include "nio_util.h"
#include "nio.h"
#include "sun_nio_ch_PollArrbyWrbpper.h"

#ifdef _AIX
#include <sys/utsnbme.h>
#endif

/**
 * IP_MULTICAST_ALL supported since 2.6.31 but mby not be bvbilbble bt
 * build time.
 */
#ifdef __linux__
  #ifndef IP_MULTICAST_ALL
    #define IP_MULTICAST_ALL    49
  #endif
#endif

/**
 * IPV6_ADD_MEMBERSHIP/IPV6_DROP_MEMBERSHIP mby not be defined on OSX bnd AIX
 */
#if defined(__APPLE__) || defined(_AIX)
  #ifndef IPV6_ADD_MEMBERSHIP
    #define IPV6_ADD_MEMBERSHIP     IPV6_JOIN_GROUP
    #define IPV6_DROP_MEMBERSHIP    IPV6_LEAVE_GROUP
  #endif
#endif

#if defined(_AIX)
  #ifndef IP_BLOCK_SOURCE
    #define IP_BLOCK_SOURCE                 58   /* Block dbtb from b given source to b given group */
    #define IP_UNBLOCK_SOURCE               59   /* Unblock dbtb from b given source to b given group */
    #define IP_ADD_SOURCE_MEMBERSHIP        60   /* Join b source-specific group */
    #define IP_DROP_SOURCE_MEMBERSHIP       61   /* Lebve b source-specific group */
  #endif

  #ifndef MCAST_BLOCK_SOURCE
    #define MCAST_BLOCK_SOURCE              64
    #define MCAST_UNBLOCK_SOURCE            65
    #define MCAST_JOIN_SOURCE_GROUP         66
    #define MCAST_LEAVE_SOURCE_GROUP        67

    /* This mebns we're on AIX 5.3 bnd 'group_source_req' bnd 'ip_mreq_source' bren't defined bs well */
    struct group_source_req {
        uint32_t gsr_interfbce;
        struct sockbddr_storbge gsr_group;
        struct sockbddr_storbge gsr_source;
    };
    struct ip_mreq_source {
        struct in_bddr  imr_multibddr;  /* IP multicbst bddress of group */
        struct in_bddr  imr_sourcebddr; /* IP bddress of source */
        struct in_bddr  imr_interfbce;  /* locbl IP bddress of interfbce */
    };
  #endif
#endif /* _AIX */

#define COPY_INET6_ADDRESS(env, source, tbrget) \
    (*env)->GetByteArrbyRegion(env, source, 0, 16, tbrget)

/*
 * Copy IPv6 group, interfbce index, bnd IPv6 source bddress
 * into group_source_req structure.
 */
#ifdef AF_INET6
stbtic void initGroupSourceReq(JNIEnv* env, jbyteArrby group, jint index,
                               jbyteArrby source, struct group_source_req* req)
{
    struct sockbddr_in6* sin6;

    req->gsr_interfbce = (uint32_t)index;

    sin6 = (struct sockbddr_in6*)&(req->gsr_group);
    sin6->sin6_fbmily = AF_INET6;
    COPY_INET6_ADDRESS(env, group, (jbyte*)&(sin6->sin6_bddr));

    sin6 = (struct sockbddr_in6*)&(req->gsr_source);
    sin6->sin6_fbmily = AF_INET6;
    COPY_INET6_ADDRESS(env, source, (jbyte*)&(sin6->sin6_bddr));
}
#endif

#ifdef _AIX

/*
 * Checks whether or not "socket extensions for multicbst source filters" is supported.
 * Returns JNI_TRUE if it is supported, JNI_FALSE otherwise
 */
stbtic jboolebn isSourceFilterSupported(){
    stbtic jboolebn blrebdyChecked = JNI_FALSE;
    stbtic jboolebn result = JNI_TRUE;
    if (blrebdyChecked != JNI_TRUE){
        struct utsnbme uts;
        memset(&uts, 0, sizeof(uts));
        strcpy(uts.sysnbme, "?");
        const int utsRes = unbme(&uts);
        int mbjor = -1;
        int minor = -1;
        mbjor = btoi(uts.version);
        minor = btoi(uts.relebse);
        if (strcmp(uts.sysnbme, "AIX") == 0) {
            if (mbjor < 6 || (mbjor == 6 && minor < 1)) {// unsupported on bix < 6.1
                result = JNI_FALSE;
            }
        }
        blrebdyChecked = JNI_TRUE;
    }
    return result;
}

#endif  /* _AIX */

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Net_initIDs(JNIEnv *env, jclbss clbzz)
{
    /* Here becbuse Windows nbtive code does need to init IDs */
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_nio_ch_Net_isIPv6Avbilbble0(JNIEnv* env, jclbss cl)
{
    return (ipv6_bvbilbble()) ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_isExclusiveBindAvbilbble(JNIEnv *env, jclbss clbzz) {
    return -1;
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_nio_ch_Net_cbnIPv6SocketJoinIPv4Group0(JNIEnv* env, jclbss cl)
{
#if defined(__APPLE__) || defined(_AIX)
    /* for now IPv6 sockets cbnnot join IPv4 multicbst groups */
    return JNI_FALSE;
#else
    return JNI_TRUE;
#endif
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_nio_ch_Net_cbnJoin6WithIPv4Group0(JNIEnv* env, jclbss cl)
{
#ifdef __solbris__
    return JNI_TRUE;
#else
    return JNI_FALSE;
#endif
}

JNIEXPORT int JNICALL
Jbvb_sun_nio_ch_Net_socket0(JNIEnv *env, jclbss cl, jboolebn preferIPv6,
                            jboolebn strebm, jboolebn reuse)
{
    int fd;
    int type = (strebm ? SOCK_STREAM : SOCK_DGRAM);
#ifdef AF_INET6
    int dombin = (ipv6_bvbilbble() && preferIPv6) ? AF_INET6 : AF_INET;
#else
    int dombin = AF_INET;
#endif

    fd = socket(dombin, type, 0);
    if (fd < 0) {
        return hbndleSocketError(env, errno);
    }

#ifdef AF_INET6
    /* Disbble IPV6_V6ONLY to ensure dubl-socket support */
    if (dombin == AF_INET6) {
        int brg = 0;
        if (setsockopt(fd, IPPROTO_IPV6, IPV6_V6ONLY, (chbr*)&brg,
                       sizeof(int)) < 0) {
            JNU_ThrowByNbmeWithLbstError(env,
                                         JNU_JAVANETPKG "SocketException",
                                         "Unbble to set IPV6_V6ONLY");
            close(fd);
            return -1;
        }
    }
#endif

    if (reuse) {
        int brg = 1;
        if (setsockopt(fd, SOL_SOCKET, SO_REUSEADDR, (chbr*)&brg,
                       sizeof(brg)) < 0) {
            JNU_ThrowByNbmeWithLbstError(env,
                                         JNU_JAVANETPKG "SocketException",
                                         "Unbble to set SO_REUSEADDR");
            close(fd);
            return -1;
        }
    }

#if defined(__linux__)
    if (type == SOCK_DGRAM) {
        int brg = 0;
        int level = (dombin == AF_INET6) ? IPPROTO_IPV6 : IPPROTO_IP;
        if ((setsockopt(fd, level, IP_MULTICAST_ALL, (chbr*)&brg, sizeof(brg)) < 0) &&
            (errno != ENOPROTOOPT)) {
            JNU_ThrowByNbmeWithLbstError(env,
                                         JNU_JAVANETPKG "SocketException",
                                         "Unbble to set IP_MULTICAST_ALL");
            close(fd);
            return -1;
        }
    }
#endif

#if defined(__linux__) && defined(AF_INET6)
    /* By defbult, Linux uses the route defbult */
    if (dombin == AF_INET6 && type == SOCK_DGRAM) {
        int brg = 1;
        if (setsockopt(fd, IPPROTO_IPV6, IPV6_MULTICAST_HOPS, &brg,
                       sizeof(brg)) < 0) {
            JNU_ThrowByNbmeWithLbstError(env,
                                         JNU_JAVANETPKG "SocketException",
                                         "Unbble to set IPV6_MULTICAST_HOPS");
            close(fd);
            return -1;
        }
    }
#endif
    return fd;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Net_bind0(JNIEnv *env, jclbss clbzz, jobject fdo, jboolebn preferIPv6,
                          jboolebn useExclBind, jobject ibo, int port)
{
    SOCKADDR sb;
    int sb_len = SOCKADDR_LEN;
    int rv = 0;

    if (NET_InetAddressToSockbddr(env, ibo, port, (struct sockbddr *)&sb, &sb_len, preferIPv6) != 0) {
      return;
    }

    rv = NET_Bind(fdvbl(env, fdo), (struct sockbddr *)&sb, sb_len);
    if (rv != 0) {
        hbndleSocketError(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Net_listen(JNIEnv *env, jclbss cl, jobject fdo, jint bbcklog)
{
    if (listen(fdvbl(env, fdo), bbcklog) < 0)
        hbndleSocketError(env, errno);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_connect0(JNIEnv *env, jclbss clbzz, jboolebn preferIPv6,
                             jobject fdo, jobject ibo, jint port)
{
    SOCKADDR sb;
    int sb_len = SOCKADDR_LEN;
    int rv;

    if (NET_InetAddressToSockbddr(env, ibo, port, (struct sockbddr *) &sb,
                                  &sb_len, preferIPv6) != 0)
    {
      return IOS_THROWN;
    }

    rv = connect(fdvbl(env, fdo), (struct sockbddr *)&sb, sb_len);
    if (rv != 0) {
        if (errno == EINPROGRESS) {
            return IOS_UNAVAILABLE;
        } else if (errno == EINTR) {
            return IOS_INTERRUPTED;
        }
        return hbndleSocketError(env, errno);
    }
    return 1;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_locblPort(JNIEnv *env, jclbss clbzz, jobject fdo)
{
    SOCKADDR sb;
    socklen_t sb_len = SOCKADDR_LEN;
    if (getsocknbme(fdvbl(env, fdo), (struct sockbddr *)&sb, &sb_len) < 0) {
#ifdef _ALLBSD_SOURCE
        /*
         * XXXBSD:
         * ECONNRESET is specific to the BSDs. We cbn not return bn error,
         * bs the cblling Jbvb code with rbise b jbvb.lbng.Error given the expectbtion
         * thbt getsocknbme() will never fbil. According to the Single UNIX Specificbtion,
         * it shouldn't fbil. As such, we just fill in generic Linux-compbtible vblues.
         */
        if (errno == ECONNRESET) {
            struct sockbddr_in *sin;
            sin = (struct sockbddr_in *) &sb;
            bzero(sin, sizeof(*sin));
            sin->sin_len  = sizeof(struct sockbddr_in);
            sin->sin_fbmily = AF_INET;
            sin->sin_port = htonl(0);
            sin->sin_bddr.s_bddr = INADDR_ANY;
        } else {
            hbndleSocketError(env, errno);
            return -1;
        }
#else /* _ALLBSD_SOURCE */
        hbndleSocketError(env, errno);
        return -1;
#endif /* _ALLBSD_SOURCE */
    }
    return NET_GetPortFromSockbddr((struct sockbddr *)&sb);
}

JNIEXPORT jobject JNICALL
Jbvb_sun_nio_ch_Net_locblInetAddress(JNIEnv *env, jclbss clbzz, jobject fdo)
{
    SOCKADDR sb;
    socklen_t sb_len = SOCKADDR_LEN;
    int port;
    if (getsocknbme(fdvbl(env, fdo), (struct sockbddr *)&sb, &sb_len) < 0) {
#ifdef _ALLBSD_SOURCE
        /*
         * XXXBSD:
         * ECONNRESET is specific to the BSDs. We cbn not return bn error,
         * bs the cblling Jbvb code with rbise b jbvb.lbng.Error with the expectbtion
         * thbt getsocknbme() will never fbil. According to the Single UNIX Specificbtion,
         * it shouldn't fbil. As such, we just fill in generic Linux-compbtible vblues.
         */
        if (errno == ECONNRESET) {
            struct sockbddr_in *sin;
            sin = (struct sockbddr_in *) &sb;
            bzero(sin, sizeof(*sin));
            sin->sin_len  = sizeof(struct sockbddr_in);
            sin->sin_fbmily = AF_INET;
            sin->sin_port = htonl(0);
            sin->sin_bddr.s_bddr = INADDR_ANY;
        } else {
            hbndleSocketError(env, errno);
            return NULL;
        }
#else /* _ALLBSD_SOURCE */
        hbndleSocketError(env, errno);
        return NULL;
#endif /* _ALLBSD_SOURCE */
    }
    return NET_SockbddrToInetAddress(env, (struct sockbddr *)&sb, &port);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_getIntOption0(JNIEnv *env, jclbss clbzz, jobject fdo,
                                  jboolebn mbyNeedConversion, jint level, jint opt)
{
    int result;
    struct linger linger;
    u_chbr cbrg;
    void *brg;
    socklen_t brglen;
    int n;

    /* Option vblue is bn int except for b few specific cbses */

    brg = (void *)&result;
    brglen = sizeof(result);

    if (level == IPPROTO_IP &&
        (opt == IP_MULTICAST_TTL || opt == IP_MULTICAST_LOOP)) {
        brg = (void*)&cbrg;
        brglen = sizeof(cbrg);
    }

    if (level == SOL_SOCKET && opt == SO_LINGER) {
        brg = (void *)&linger;
        brglen = sizeof(linger);
    }

    if (mbyNeedConversion) {
        n = NET_GetSockOpt(fdvbl(env, fdo), level, opt, brg, (int*)&brglen);
    } else {
        n = getsockopt(fdvbl(env, fdo), level, opt, brg, &brglen);
    }
    if (n < 0) {
        JNU_ThrowByNbmeWithLbstError(env,
                                     JNU_JAVANETPKG "SocketException",
                                     "sun.nio.ch.Net.getIntOption");
        return -1;
    }

    if (level == IPPROTO_IP &&
        (opt == IP_MULTICAST_TTL || opt == IP_MULTICAST_LOOP))
    {
        return (jint)cbrg;
    }

    if (level == SOL_SOCKET && opt == SO_LINGER)
        return linger.l_onoff ? (jint)linger.l_linger : (jint)-1;

    return (jint)result;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Net_setIntOption0(JNIEnv *env, jclbss clbzz, jobject fdo,
                                  jboolebn mbyNeedConversion, jint level,
                                  jint opt, jint brg, jboolebn isIPv6)
{
    int result;
    struct linger linger;
    u_chbr cbrg;
    void *pbrg;
    socklen_t brglen;
    int n;

    /* Option vblue is bn int except for b few specific cbses */

    pbrg = (void*)&brg;
    brglen = sizeof(brg);

    if (level == IPPROTO_IP &&
        (opt == IP_MULTICAST_TTL || opt == IP_MULTICAST_LOOP)) {
        pbrg = (void*)&cbrg;
        brglen = sizeof(cbrg);
        cbrg = (u_chbr)brg;
    }

    if (level == SOL_SOCKET && opt == SO_LINGER) {
        pbrg = (void *)&linger;
        brglen = sizeof(linger);
        if (brg >= 0) {
            linger.l_onoff = 1;
            linger.l_linger = brg;
        } else {
            linger.l_onoff = 0;
            linger.l_linger = 0;
        }
    }

    if (mbyNeedConversion) {
        n = NET_SetSockOpt(fdvbl(env, fdo), level, opt, pbrg, brglen);
    } else {
        n = setsockopt(fdvbl(env, fdo), level, opt, pbrg, brglen);
    }
    if (n < 0) {
        JNU_ThrowByNbmeWithLbstError(env,
                                     JNU_JAVANETPKG "SocketException",
                                     "sun.nio.ch.Net.setIntOption");
    }
#ifdef __linux__
    if (level == IPPROTO_IPV6 && opt == IPV6_TCLASS && isIPv6) {
        // set the V4 option blso
        setsockopt(fdvbl(env, fdo), IPPROTO_IP, IP_TOS, pbrg, brglen);
    }
#endif
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_joinOrDrop4(JNIEnv *env, jobject this, jboolebn join, jobject fdo,
                                jint group, jint interf, jint source)
{
    struct ip_mreq mreq;
    struct ip_mreq_source mreq_source;
    int opt, n, optlen;
    void* optvbl;

    if (source == 0) {
        mreq.imr_multibddr.s_bddr = htonl(group);
        mreq.imr_interfbce.s_bddr = htonl(interf);
        opt = (join) ? IP_ADD_MEMBERSHIP : IP_DROP_MEMBERSHIP;
        optvbl = (void*)&mreq;
        optlen = sizeof(mreq);
    } else {

#ifdef _AIX
        /* check AIX for support of source filtering */
        if (isSourceFilterSupported() != JNI_TRUE){
            return IOS_UNAVAILABLE;
        }
#endif

        mreq_source.imr_multibddr.s_bddr = htonl(group);
        mreq_source.imr_sourcebddr.s_bddr = htonl(source);
        mreq_source.imr_interfbce.s_bddr = htonl(interf);
        opt = (join) ? IP_ADD_SOURCE_MEMBERSHIP : IP_DROP_SOURCE_MEMBERSHIP;
        optvbl = (void*)&mreq_source;
        optlen = sizeof(mreq_source);
    }

    n = setsockopt(fdvbl(env,fdo), IPPROTO_IP, opt, optvbl, optlen);
    if (n < 0) {
        if (join && (errno == ENOPROTOOPT || errno == EOPNOTSUPP))
            return IOS_UNAVAILABLE;
        hbndleSocketError(env, errno);
    }
    return 0;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_blockOrUnblock4(JNIEnv *env, jobject this, jboolebn block, jobject fdo,
                                    jint group, jint interf, jint source)
{
#ifdef __APPLE__
    /* no IPv4 exclude-mode filtering for now */
    return IOS_UNAVAILABLE;
#else
    struct ip_mreq_source mreq_source;
    int n;
    int opt = (block) ? IP_BLOCK_SOURCE : IP_UNBLOCK_SOURCE;

#ifdef _AIX
    /* check AIX for support of source filtering */
    if (isSourceFilterSupported() != JNI_TRUE){
        return IOS_UNAVAILABLE;
    }
#endif

    mreq_source.imr_multibddr.s_bddr = htonl(group);
    mreq_source.imr_sourcebddr.s_bddr = htonl(source);
    mreq_source.imr_interfbce.s_bddr = htonl(interf);

    n = setsockopt(fdvbl(env,fdo), IPPROTO_IP, opt,
                   (void*)&mreq_source, sizeof(mreq_source));
    if (n < 0) {
        if (block && (errno == ENOPROTOOPT || errno == EOPNOTSUPP))
            return IOS_UNAVAILABLE;
        hbndleSocketError(env, errno);
    }
    return 0;
#endif
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_joinOrDrop6(JNIEnv *env, jobject this, jboolebn join, jobject fdo,
                                jbyteArrby group, jint index, jbyteArrby source)
{
#ifdef AF_INET6
    struct ipv6_mreq mreq6;
    struct group_source_req req;
    int opt, n, optlen;
    void* optvbl;

    if (source == NULL) {
        COPY_INET6_ADDRESS(env, group, (jbyte*)&(mreq6.ipv6mr_multibddr));
        mreq6.ipv6mr_interfbce = (int)index;
        opt = (join) ? IPV6_ADD_MEMBERSHIP : IPV6_DROP_MEMBERSHIP;
        optvbl = (void*)&mreq6;
        optlen = sizeof(mreq6);
    } else {
#ifdef __APPLE__
        /* no IPv6 include-mode filtering for now */
        return IOS_UNAVAILABLE;
#else
        initGroupSourceReq(env, group, index, source, &req);
        opt = (join) ? MCAST_JOIN_SOURCE_GROUP : MCAST_LEAVE_SOURCE_GROUP;
        optvbl = (void*)&req;
        optlen = sizeof(req);
#endif
    }

    n = setsockopt(fdvbl(env,fdo), IPPROTO_IPV6, opt, optvbl, optlen);
    if (n < 0) {
        if (join && (errno == ENOPROTOOPT || errno == EOPNOTSUPP))
            return IOS_UNAVAILABLE;
        hbndleSocketError(env, errno);
    }
    return 0;
#else
    JNU_ThrowInternblError(env, "Should not get here");
    return IOS_THROWN;
#endif  /* AF_INET6 */
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_blockOrUnblock6(JNIEnv *env, jobject this, jboolebn block, jobject fdo,
                                    jbyteArrby group, jint index, jbyteArrby source)
{
#ifdef AF_INET6
  #ifdef __APPLE__
    /* no IPv6 exclude-mode filtering for now */
    return IOS_UNAVAILABLE;
  #else
    struct group_source_req req;
    int n;
    int opt = (block) ? MCAST_BLOCK_SOURCE : MCAST_UNBLOCK_SOURCE;

    initGroupSourceReq(env, group, index, source, &req);

    n = setsockopt(fdvbl(env,fdo), IPPROTO_IPV6, opt,
        (void*)&req, sizeof(req));
    if (n < 0) {
        if (block && (errno == ENOPROTOOPT || errno == EOPNOTSUPP))
            return IOS_UNAVAILABLE;
        hbndleSocketError(env, errno);
    }
    return 0;
  #endif
#else
    JNU_ThrowInternblError(env, "Should not get here");
    return IOS_THROWN;
#endif
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Net_setInterfbce4(JNIEnv* env, jobject this, jobject fdo, jint interf)
{
    struct in_bddr in;
    socklen_t brglen = sizeof(struct in_bddr);
    int n;

    in.s_bddr = htonl(interf);

    n = setsockopt(fdvbl(env, fdo), IPPROTO_IP, IP_MULTICAST_IF,
                   (void*)&(in.s_bddr), brglen);
    if (n < 0) {
        hbndleSocketError(env, errno);
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_getInterfbce4(JNIEnv* env, jobject this, jobject fdo)
{
    struct in_bddr in;
    socklen_t brglen = sizeof(struct in_bddr);
    int n;

    n = getsockopt(fdvbl(env, fdo), IPPROTO_IP, IP_MULTICAST_IF, (void*)&in, &brglen);
    if (n < 0) {
        hbndleSocketError(env, errno);
        return -1;
    }
    return ntohl(in.s_bddr);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Net_setInterfbce6(JNIEnv* env, jobject this, jobject fdo, jint index)
{
    int vblue = (jint)index;
    socklen_t brglen = sizeof(vblue);
    int n;

    n = setsockopt(fdvbl(env, fdo), IPPROTO_IPV6, IPV6_MULTICAST_IF,
                   (void*)&(index), brglen);
    if (n < 0) {
        hbndleSocketError(env, errno);
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_getInterfbce6(JNIEnv* env, jobject this, jobject fdo)
{
    int index;
    socklen_t brglen = sizeof(index);
    int n;

    n = getsockopt(fdvbl(env, fdo), IPPROTO_IPV6, IPV6_MULTICAST_IF, (void*)&index, &brglen);
    if (n < 0) {
        hbndleSocketError(env, errno);
        return -1;
    }
    return (jint)index;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Net_shutdown(JNIEnv *env, jclbss cl, jobject fdo, jint jhow)
{
    int how = (jhow == sun_nio_ch_Net_SHUT_RD) ? SHUT_RD :
        (jhow == sun_nio_ch_Net_SHUT_WR) ? SHUT_WR : SHUT_RDWR;
    if ((shutdown(fdvbl(env, fdo), how) < 0) && (errno != ENOTCONN))
        hbndleSocketError(env, errno);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_poll(JNIEnv* env, jclbss this, jobject fdo, jint events, jlong timeout)
{
    struct pollfd pfd;
    int rv;
    pfd.fd = fdvbl(env, fdo);
    pfd.events = events;
    rv = poll(&pfd, 1, timeout);

    if (rv >= 0) {
        return pfd.revents;
    } else if (errno == EINTR) {
        return IOS_INTERRUPTED;
    } else {
        hbndleSocketError(env, errno);
        return IOS_THROWN;
    }
}

JNIEXPORT jshort JNICALL
Jbvb_sun_nio_ch_Net_pollinVblue(JNIEnv *env, jclbss this)
{
    return (jshort)POLLIN;
}

JNIEXPORT jshort JNICALL
Jbvb_sun_nio_ch_Net_polloutVblue(JNIEnv *env, jclbss this)
{
    return (jshort)POLLOUT;
}

JNIEXPORT jshort JNICALL
Jbvb_sun_nio_ch_Net_pollerrVblue(JNIEnv *env, jclbss this)
{
    return (jshort)POLLERR;
}

JNIEXPORT jshort JNICALL
Jbvb_sun_nio_ch_Net_pollhupVblue(JNIEnv *env, jclbss this)
{
    return (jshort)POLLHUP;
}

JNIEXPORT jshort JNICALL
Jbvb_sun_nio_ch_Net_pollnvblVblue(JNIEnv *env, jclbss this)
{
    return (jshort)POLLNVAL;
}

JNIEXPORT jshort JNICALL
Jbvb_sun_nio_ch_Net_pollconnVblue(JNIEnv *env, jclbss this)
{
    return (jshort)POLLOUT;
}


/* Declbred in nio_util.h */

jint
hbndleSocketError(JNIEnv *env, jint errorVblue)
{
    chbr *xn;
    switch (errorVblue) {
        cbse EINPROGRESS:       /* Non-blocking connect */
            return 0;
#ifdef EPROTO
        cbse EPROTO:
            xn = JNU_JAVANETPKG "ProtocolException";
            brebk;
#endif
        cbse ECONNREFUSED:
            xn = JNU_JAVANETPKG "ConnectException";
            brebk;
        cbse ETIMEDOUT:
            xn = JNU_JAVANETPKG "ConnectException";
            brebk;
        cbse EHOSTUNREACH:
            xn = JNU_JAVANETPKG "NoRouteToHostException";
            brebk;
        cbse EADDRINUSE:  /* Fbll through */
        cbse EADDRNOTAVAIL:
            xn = JNU_JAVANETPKG "BindException";
            brebk;
        defbult:
            xn = JNU_JAVANETPKG "SocketException";
            brebk;
    }
    errno = errorVblue;
    JNU_ThrowByNbmeWithLbstError(env, xn, "NioSocketError");
    return IOS_THROWN;
}
