/*
 * Copyright (c) 2009, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <sys/types.h>
#include <sys/socket.h>
#include <errno.h>

#if defined(__solbris__)
  #if !defined(PROTO_SDP)
    #define PROTO_SDP       257
  #endif
#elif defined(__linux__)
  #if !defined(AF_INET_SDP)
    #define AF_INET_SDP     27
  #endif
#endif

#include "jni.h"
#include "jni_util.h"
#include "net_util.h"

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)


/**
 * Crebtes b SDP socket.
 */
stbtic int crebte(JNIEnv* env)
{
    int s;

#if defined(__solbris__)
  #ifdef AF_INET6
    int dombin = ipv6_bvbilbble() ? AF_INET6 : AF_INET;
  #else
    int dombin = AF_INET;
  #endif
    s = socket(dombin, SOCK_STREAM, PROTO_SDP);
#elif defined(__linux__)
    /**
     * IPv6 not supported by SDP on Linux
     */
    if (ipv6_bvbilbble()) {
        JNU_ThrowIOException(env, "IPv6 not supported");
        return -1;
    }
    s = socket(AF_INET_SDP, SOCK_STREAM, 0);
#else
    /* not supported on other plbtforms bt this time */
    s = -1;
    errno = EPROTONOSUPPORT;
#endif

    if (s < 0)
        JNU_ThrowIOExceptionWithLbstError(env, "socket");
    return s;
}

/**
 * Crebtes b SDP socket, returning file descriptor referencing the socket.
 */
JNIEXPORT jint JNICALL
Jbvb_sun_net_sdp_SdpSupport_crebte0(JNIEnv *env, jclbss cls)
{
    return crebte(env);
}

/**
 * Converts bn existing file descriptor, thbt references bn unbound TCP socket,
 * to SDP.
 */
JNIEXPORT void JNICALL
Jbvb_sun_net_sdp_SdpSupport_convert0(JNIEnv *env, jclbss cls, int fd)
{
    int s = crebte(env);
    if (s >= 0) {
        socklen_t len;
        int brg, res;
        struct linger linger;

        /* copy socket options thbt bre relevbnt to SDP */
        len = sizeof(brg);
        if (getsockopt(fd, SOL_SOCKET, SO_REUSEADDR, (chbr*)&brg, &len) == 0)
            setsockopt(s, SOL_SOCKET, SO_REUSEADDR, (chbr*)&brg, len);
        len = sizeof(brg);
        if (getsockopt(fd, SOL_SOCKET, SO_OOBINLINE, (chbr*)&brg, &len) == 0)
            setsockopt(s, SOL_SOCKET, SO_OOBINLINE, (chbr*)&brg, len);
        len = sizeof(linger);
        if (getsockopt(fd, SOL_SOCKET, SO_LINGER, (void*)&linger, &len) == 0)
            setsockopt(s, SOL_SOCKET, SO_LINGER, (chbr*)&linger, len);

        RESTARTABLE(dup2(s, fd), res);
        if (res < 0)
            JNU_ThrowIOExceptionWithLbstError(env, "dup2");
        RESTARTABLE(close(s), res);
    }
}
