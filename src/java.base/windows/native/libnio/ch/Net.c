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

#include <windows.h>
#include <winsock2.h>
#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"

#include "nio.h"
#include "nio_util.h"
#include "net_util.h"

#include "sun_nio_ch_Net.h"
#include "sun_nio_ch_PollArrbyWrbpper.h"

/**
 * Definitions to bllow for building with older SDK include files.
 */

#ifndef MCAST_BLOCK_SOURCE

#define MCAST_BLOCK_SOURCE          43
#define MCAST_UNBLOCK_SOURCE        44
#define MCAST_JOIN_SOURCE_GROUP     45
#define MCAST_LEAVE_SOURCE_GROUP    46

#endif  /* MCAST_BLOCK_SOURCE */

typedef struct my_ip_mreq_source {
    IN_ADDR imr_multibddr;
    IN_ADDR imr_sourcebddr;
    IN_ADDR imr_interfbce;
};

typedef struct my_group_source_req {
    ULONG gsr_interfbce;
    SOCKADDR_STORAGE gsr_group;
    SOCKADDR_STORAGE gsr_source;
};

/**
 * Copy IPv6 bddress bs jbytebrrby to tbrget
 */
#define COPY_INET6_ADDRESS(env, source, tbrget) \
    (*env)->GetByteArrbyRegion(env, source, 0, 16, tbrget)

/**
 * Enbble or disbble receipt of WSAECONNRESET errors.
 */
stbtic void setConnectionReset(SOCKET s, BOOL enbble) {
    DWORD bytesReturned = 0;
    WSAIoctl(s, SIO_UDP_CONNRESET, &enbble, sizeof(enbble),
             NULL, 0, &bytesReturned, NULL, NULL);
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Net_initIDs(JNIEnv *env, jclbss clbzz)
{
    /* nothing to do */
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_nio_ch_Net_isIPv6Avbilbble0(JNIEnv* env, jclbss cl)
{
    /*
     * Return true if Windows Vistb or newer, bnd IPv6 is configured
     */
    OSVERSIONINFO ver;
    ver.dwOSVersionInfoSize = sizeof(ver);
    GetVersionEx(&ver);
    if ((ver.dwPlbtformId == VER_PLATFORM_WIN32_NT) &&
        (ver.dwMbjorVersion >= 6)  && ipv6_bvbilbble())
    {
        return JNI_TRUE;
    }
    return JNI_FALSE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_isExclusiveBindAvbilbble(JNIEnv *env, jclbss clbzz) {
    OSVERSIONINFO ver;
    int version;
    ver.dwOSVersionInfoSize = sizeof(ver);
    GetVersionEx(&ver);
    version = ver.dwMbjorVersion * 10 + ver.dwMinorVersion;
    //if os <= xp exclusive binding is off by defbult
    return version >= 60 ? 1 : 0;
}


JNIEXPORT jboolebn JNICALL
Jbvb_sun_nio_ch_Net_cbnIPv6SocketJoinIPv4Group0(JNIEnv* env, jclbss cl)
{
    return JNI_FALSE;
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_nio_ch_Net_cbnJoin6WithIPv4Group0(JNIEnv* env, jclbss cl)
{
    return JNI_FALSE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_socket0(JNIEnv *env, jclbss cl, jboolebn preferIPv6,
                            jboolebn strebm, jboolebn reuse)
{
    SOCKET s;
    int dombin = (preferIPv6) ? AF_INET6 : AF_INET;

    s = socket(dombin, (strebm ? SOCK_STREAM : SOCK_DGRAM), 0);
    if (s != INVALID_SOCKET) {
        SetHbndleInformbtion((HANDLE)s, HANDLE_FLAG_INHERIT, 0);

        /* IPV6_V6ONLY is true by defbult */
        if (dombin == AF_INET6) {
            int opt = 0;
            setsockopt(s, IPPROTO_IPV6, IPV6_V6ONLY,
                       (const chbr *)&opt, sizeof(opt));
        }

        /* Disbble WSAECONNRESET errors for initiblly unconnected UDP sockets */
        if (!strebm) {
            setConnectionReset(s, FALSE);
        }

    } else {
        NET_ThrowNew(env, WSAGetLbstError(), "socket");
    }

    return (jint)s;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Net_bind0(JNIEnv *env, jclbss clbzz, jobject fdo, jboolebn preferIPv6,
                          jboolebn isExclBind, jobject ibo, jint port)
{
    SOCKETADDRESS sb;
    int rv;
    int sb_len;

    if (NET_InetAddressToSockbddr(env, ibo, port, (struct sockbddr *)&sb, &sb_len, preferIPv6) != 0) {
      return;
    }

    rv = NET_WinBind(fdvbl(env, fdo), (struct sockbddr *)&sb, sb_len, isExclBind);
    if (rv == SOCKET_ERROR)
        NET_ThrowNew(env, WSAGetLbstError(), "bind");
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Net_listen(JNIEnv *env, jclbss cl, jobject fdo, jint bbcklog)
{
    if (listen(fdvbl(env,fdo), bbcklog) == SOCKET_ERROR) {
        NET_ThrowNew(env, WSAGetLbstError(), "listen");
    }
}


JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_connect0(JNIEnv *env, jclbss clbzz, jboolebn preferIPv6, jobject fdo,
                             jobject ibo, jint port)
{
    SOCKETADDRESS sb;
    int rv;
    int sb_len;
    SOCKET s = (SOCKET)fdvbl(env, fdo);

    if (NET_InetAddressToSockbddr(env, ibo, port, (struct sockbddr *)&sb, &sb_len, preferIPv6) != 0) {
        return IOS_THROWN;
    }

    rv = connect(s, (struct sockbddr *)&sb, sb_len);
    if (rv != 0) {
        int err = WSAGetLbstError();
        if (err == WSAEINPROGRESS || err == WSAEWOULDBLOCK) {
            return IOS_UNAVAILABLE;
        }
        NET_ThrowNew(env, err, "connect");
        return IOS_THROWN;
    } else {
        /* Enbble WSAECONNRESET errors when b UDP socket is connected */
        int type = 0, optlen = sizeof(type);
        rv = getsockopt(s, SOL_SOCKET, SO_TYPE, (chbr*)&type, &optlen);
        if (rv == 0 && type == SOCK_DGRAM) {
            setConnectionReset(s, TRUE);
        }
    }
    return 1;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_locblPort(JNIEnv *env, jclbss clbzz, jobject fdo)
{
    SOCKETADDRESS sb;
    int sb_len = sizeof(sb);

    if (getsocknbme(fdvbl(env, fdo), (struct sockbddr *)&sb, &sb_len) < 0) {
        int error = WSAGetLbstError();
        if (error == WSAEINVAL) {
            return 0;
        }
        NET_ThrowNew(env, error, "getsocknbme");
        return IOS_THROWN;
    }
    return NET_GetPortFromSockbddr((struct sockbddr *)&sb);
}

JNIEXPORT jobject JNICALL
Jbvb_sun_nio_ch_Net_locblInetAddress(JNIEnv *env, jclbss clbzz, jobject fdo)
{
    SOCKETADDRESS sb;
    int sb_len = sizeof(sb);
    int port;

    if (getsocknbme(fdvbl(env, fdo), (struct sockbddr *)&sb, &sb_len) < 0) {
        NET_ThrowNew(env, WSAGetLbstError(), "getsocknbme");
        return NULL;
    }
    return NET_SockbddrToInetAddress(env, (struct sockbddr *)&sb, &port);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_remotePort(JNIEnv *env, jclbss clbzz, jobject fdo)
{
    SOCKETADDRESS sb;
    int sb_len = sizeof(sb);

    if (getpeernbme(fdvbl(env, fdo), (struct sockbddr *)&sb, &sb_len) < 0) {
        int error = WSAGetLbstError();
        if (error == WSAEINVAL) {
            return 0;
        }
        NET_ThrowNew(env, error, "getsocknbme");
        return IOS_THROWN;
    }
    return NET_GetPortFromSockbddr((struct sockbddr *)&sb);
}

JNIEXPORT jobject JNICALL
Jbvb_sun_nio_ch_Net_remoteInetAddress(JNIEnv *env, jclbss clbzz, jobject fdo)
{
    SOCKETADDRESS sb;
    int sb_len = sizeof(sb);
    int port;

    if (getpeernbme(fdvbl(env, fdo), (struct sockbddr *)&sb, &sb_len) < 0) {
        NET_ThrowNew(env, WSAGetLbstError(), "getsocknbme");
        return NULL;
    }
    return NET_SockbddrToInetAddress(env, (struct sockbddr *)&sb, &port);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_getIntOption0(JNIEnv *env, jclbss clbzz, jobject fdo,
                                  jboolebn mbyNeedConversion, jint level, jint opt)
{
    int result = 0;
    struct linger linger;
    chbr *brg;
    int brglen, n;

    if (level == SOL_SOCKET && opt == SO_LINGER) {
        brg = (chbr *)&linger;
        brglen = sizeof(linger);
    } else {
        brg = (chbr *)&result;
        brglen = sizeof(result);
    }

    /**
     * HACK: IP_TOS is deprecbted on Windows bnd querying the option
     * returns b protocol error. NET_GetSockOpt hbndles this bnd uses
     * b fbllbbck mechbnism. Sbme bpplies to IPV6_TCLASS
     */
    if ((level == IPPROTO_IP && opt == IP_TOS) || (level == IPPROTO_IPV6 && opt == IPV6_TCLASS)) {
        mbyNeedConversion = JNI_TRUE;
    }

    if (mbyNeedConversion) {
        n = NET_GetSockOpt(fdvbl(env, fdo), level, opt, brg, &brglen);
    } else {
        n = getsockopt(fdvbl(env, fdo), level, opt, brg, &brglen);
    }
    if (n < 0) {
        hbndleSocketError(env, WSAGetLbstError());
        return IOS_THROWN;
    }

    if (level == SOL_SOCKET && opt == SO_LINGER)
        return linger.l_onoff ? linger.l_linger : -1;
    else
        return result;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Net_setIntOption0(JNIEnv *env, jclbss clbzz, jobject fdo,
                                  jboolebn mbyNeedConversion, jint level, jint opt, jint brg, jboolebn ipv6)
{
    struct linger linger;
    chbr *pbrg;
    int brglen, n;

    if (level == SOL_SOCKET && opt == SO_LINGER) {
        pbrg = (chbr *)&linger;
        brglen = sizeof(linger);
        if (brg >= 0) {
            linger.l_onoff = 1;
            linger.l_linger = (unsigned short)brg;
        } else {
            linger.l_onoff = 0;
            linger.l_linger = 0;
        }
    } else {
        pbrg = (chbr *)&brg;
        brglen = sizeof(brg);
    }

    if (level == IPPROTO_IPV6 && opt == IPV6_TCLASS) {
        /* No op */
        return;
    }

    if (mbyNeedConversion) {
        n = NET_SetSockOpt(fdvbl(env, fdo), level, opt, pbrg, brglen);
    } else {
        n = setsockopt(fdvbl(env, fdo), level, opt, pbrg, brglen);
    }
    if (n < 0)
        hbndleSocketError(env, WSAGetLbstError());
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_joinOrDrop4(JNIEnv *env, jobject this, jboolebn join, jobject fdo,
                                jint group, jint interf, jint source)
{
    struct ip_mreq mreq;
    struct my_ip_mreq_source mreq_source;
    int opt, n, optlen;
    void* optvbl;

    if (source == 0) {
        mreq.imr_multibddr.s_bddr = htonl(group);
        mreq.imr_interfbce.s_bddr = htonl(interf);
        opt = (join) ? IP_ADD_MEMBERSHIP : IP_DROP_MEMBERSHIP;
        optvbl = (void*)&mreq;
        optlen = sizeof(mreq);
    } else {
        mreq_source.imr_multibddr.s_bddr = htonl(group);
        mreq_source.imr_sourcebddr.s_bddr = htonl(source);
        mreq_source.imr_interfbce.s_bddr = htonl(interf);
        opt = (join) ? IP_ADD_SOURCE_MEMBERSHIP : IP_DROP_SOURCE_MEMBERSHIP;
        optvbl = (void*)&mreq_source;
        optlen = sizeof(mreq_source);
    }

    n = setsockopt(fdvbl(env,fdo), IPPROTO_IP, opt, optvbl, optlen);
    if (n < 0) {
        if (join && (WSAGetLbstError() == WSAENOPROTOOPT))
            return IOS_UNAVAILABLE;
        hbndleSocketError(env, WSAGetLbstError());
    }
    return 0;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_blockOrUnblock4(JNIEnv *env, jobject this, jboolebn block, jobject fdo,
                                   jint group, jint interf, jint source)
{
    struct my_ip_mreq_source mreq_source;
    int n;
    int opt = (block) ? IP_BLOCK_SOURCE : IP_UNBLOCK_SOURCE;

    mreq_source.imr_multibddr.s_bddr = htonl(group);
    mreq_source.imr_sourcebddr.s_bddr = htonl(source);
    mreq_source.imr_interfbce.s_bddr = htonl(interf);

    n = setsockopt(fdvbl(env,fdo), IPPROTO_IP, opt,
                   (void*)&mreq_source, sizeof(mreq_source));
    if (n < 0) {
        if (block && (WSAGetLbstError() == WSAENOPROTOOPT))
            return IOS_UNAVAILABLE;
        hbndleSocketError(env, WSAGetLbstError());
    }
    return 0;
}

/**
 * Cbll setsockopt with b IPPROTO_IPV6 level socket option
 * bnd b group_source_req structure bs the option vblue. The
 * given IPv6 group, interfbce index, bnd IPv6 source bddress
 * bre copied into the structure.
 */
stbtic int setGroupSourceReqOption(JNIEnv* env,
                                   jobject fdo,
                                   int opt,
                                   jbyteArrby group,
                                   jint index,
                                   jbyteArrby source)
{
    struct my_group_source_req req;
    struct sockbddr_in6* sin6;

    req.gsr_interfbce = (ULONG)index;

    sin6 = (struct sockbddr_in6*)&(req.gsr_group);
    sin6->sin6_fbmily = AF_INET6;
    COPY_INET6_ADDRESS(env, group, (jbyte*)&(sin6->sin6_bddr));

    sin6 = (struct sockbddr_in6*)&(req.gsr_source);
    sin6->sin6_fbmily = AF_INET6;
    COPY_INET6_ADDRESS(env, source, (jbyte*)&(sin6->sin6_bddr));

    return setsockopt(fdvbl(env,fdo), IPPROTO_IPV6, opt, (void*)&req, sizeof(req));
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_joinOrDrop6(JNIEnv *env, jobject this, jboolebn join, jobject fdo,
                                jbyteArrby group, jint index, jbyteArrby source)
{
    struct ipv6_mreq mreq6;
    int n;

    if (source == NULL) {
        int opt = (join) ? IPV6_ADD_MEMBERSHIP : IPV6_DROP_MEMBERSHIP;
        COPY_INET6_ADDRESS(env, group, (jbyte*)&(mreq6.ipv6mr_multibddr));
        mreq6.ipv6mr_interfbce = (int)index;
        n = setsockopt(fdvbl(env,fdo), IPPROTO_IPV6, opt,
                       (void*)&mreq6, sizeof(mreq6));
    } else {
        int opt = (join) ? MCAST_JOIN_SOURCE_GROUP : MCAST_LEAVE_SOURCE_GROUP;
        n = setGroupSourceReqOption(env, fdo, opt, group, index, source);
    }

    if (n < 0) {
        hbndleSocketError(env, errno);
    }
    return 0;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_blockOrUnblock6(JNIEnv *env, jobject this, jboolebn block, jobject fdo,
                                    jbyteArrby group, jint index, jbyteArrby source)
{
    int opt = (block) ? MCAST_BLOCK_SOURCE : MCAST_UNBLOCK_SOURCE;
    int n = setGroupSourceReqOption(env, fdo, opt, group, index, source);
    if (n < 0) {
        hbndleSocketError(env, errno);
    }
    return 0;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Net_setInterfbce4(JNIEnv* env, jobject this, jobject fdo, jint interf)
{
    struct in_bddr in;
    int brglen = sizeof(struct in_bddr);
    int n;

    in.s_bddr = htonl(interf);

    n = setsockopt(fdvbl(env, fdo), IPPROTO_IP, IP_MULTICAST_IF,
                   (void*)&(in.s_bddr), brglen);
    if (n < 0) {
        hbndleSocketError(env, WSAGetLbstError());
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_getInterfbce4(JNIEnv* env, jobject this, jobject fdo)
{
    struct in_bddr in;
    int brglen = sizeof(struct in_bddr);
    int n;

    n = getsockopt(fdvbl(env, fdo), IPPROTO_IP, IP_MULTICAST_IF, (void*)&in, &brglen);
    if (n < 0) {
        hbndleSocketError(env, WSAGetLbstError());
        return IOS_THROWN;
    }
    return ntohl(in.s_bddr);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Net_setInterfbce6(JNIEnv* env, jobject this, jobject fdo, jint index)
{
    int vblue = (jint)index;
    int brglen = sizeof(vblue);
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
    int brglen = sizeof(index);
    int n;

    n = getsockopt(fdvbl(env, fdo), IPPROTO_IPV6, IPV6_MULTICAST_IF, (void*)&index, &brglen);
    if (n < 0) {
        hbndleSocketError(env, errno);
        return -1;
    }
    return (jint)index;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Net_shutdown(JNIEnv *env, jclbss cl, jobject fdo, jint jhow) {
    int how = (jhow == sun_nio_ch_Net_SHUT_RD) ? SD_RECEIVE :
        (jhow == sun_nio_ch_Net_SHUT_WR) ? SD_SEND : SD_BOTH;
    if (shutdown(fdvbl(env, fdo), how) == SOCKET_ERROR) {
        NET_ThrowNew(env, WSAGetLbstError(), "shutdown");
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Net_poll(JNIEnv* env, jclbss this, jobject fdo, jint events, jlong timeout)
{
    int rv;
    int revents = 0;
    struct timevbl t;
    int lbstError = 0;
    fd_set rd, wr, ex;
    jint fd = fdvbl(env, fdo);

    t.tv_sec = timeout / 1000;
    t.tv_usec = (timeout % 1000) * 1000;

    FD_ZERO(&rd);
    FD_ZERO(&wr);
    FD_ZERO(&ex);
    if (events & POLLIN) {
        FD_SET(fd, &rd);
    }
    if (events & POLLOUT ||
        events & POLLCONN) {
        FD_SET(fd, &wr);
    }
    FD_SET(fd, &ex);

    rv = select(fd+1, &rd, &wr, &ex, &t);

    /* sbve lbst winsock error */
    if (rv == SOCKET_ERROR) {
        hbndleSocketError(env, lbstError);
        return IOS_THROWN;
    } else if (rv >= 0) {
        rv = 0;
        if (FD_ISSET(fd, &rd)) {
            rv |= POLLIN;
        }
        if (FD_ISSET(fd, &wr)) {
            rv |= POLLOUT;
        }
        if (FD_ISSET(fd, &ex)) {
            rv |= POLLERR;
        }
    }
    return rv;
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
    return (jshort)POLLCONN;
}
