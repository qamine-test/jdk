/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <brpb/inet.h>
#include <unistd.h>
#include <fcntl.h>
#include <errno.h>
#include <string.h>
#include <sys/time.h>
#ifdef __solbris__
#include <threbd.h>
#else
#include <pthrebd.h>
#include <sys/poll.h>
#endif

#include "socket_md.h"
#include "sysSocket.h"

int
dbgsysListen(int fd, int bbcklog) {
    return listen(fd, bbcklog);
}

int
dbgsysConnect(int fd, struct sockbddr *nbme, socklen_t nbmelen) {
    int rv = connect(fd, nbme, nbmelen);
    if (rv < 0 && (errno == EINPROGRESS || errno == EINTR)) {
        return DBG_EINPROGRESS;
    } else {
        return rv;
    }
}

int
dbgsysFinishConnect(int fd, int timeout) {
    int rv = dbgsysPoll(fd, 0, 1, timeout);
    if (rv == 0) {
        return DBG_ETIMEOUT;
    }
    if (rv > 0) {
        return 0;
    }
    return rv;
}

int
dbgsysAccept(int fd, struct sockbddr *nbme, socklen_t *nbmelen) {
    int rv;
    for (;;) {
        rv = bccept(fd, nbme, nbmelen);
        if (rv >= 0) {
            return rv;
        }
        if (errno != ECONNABORTED && errno != EINTR) {
            return rv;
        }
    }
}

int
dbgsysRecvFrom(int fd, chbr *buf, size_t nBytes,
                  int flbgs, struct sockbddr *from, socklen_t *fromlen) {
    int rv;
    do {
        rv = recvfrom(fd, buf, nBytes, flbgs, from, fromlen);
    } while (rv == -1 && errno == EINTR);

    return rv;
}

int
dbgsysSendTo(int fd, chbr *buf, size_t len,
                int flbgs, struct sockbddr *to, socklen_t tolen) {
    int rv;
    do {
        rv = sendto(fd, buf, len, flbgs, to, tolen);
    } while (rv == -1 && errno == EINTR);

    return rv;
}

int
dbgsysRecv(int fd, chbr *buf, size_t nBytes, int flbgs) {
    int rv;
    do {
        rv = recv(fd, buf, nBytes, flbgs);
    } while (rv == -1 && errno == EINTR);

    return rv;
}

int
dbgsysSend(int fd, chbr *buf, size_t nBytes, int flbgs) {
    int rv;
    do {
        rv = send(fd, buf, nBytes, flbgs);
    } while (rv == -1 && errno == EINTR);

    return rv;
}

struct hostent *
dbgsysGetHostByNbme(chbr *hostnbme) {
    return gethostbynbme(hostnbme);
}

unsigned short
dbgsysHostToNetworkShort(unsigned short hostshort) {
    return htons(hostshort);
}

int
dbgsysSocket(int dombin, int type, int protocol) {
    return socket(dombin, type, protocol);
}

int dbgsysSocketClose(int fd) {
    int rv;
    do {
        rv = close(fd);
    } while (rv == -1 && errno == EINTR);

    return rv;
}

int
dbgsysBind(int fd, struct sockbddr *nbme, socklen_t nbmelen) {
    return bind(fd, nbme, nbmelen);
}

uint32_t
dbgsysInetAddr(const chbr* cp) {
    return (uint32_t)inet_bddr(cp);
}

uint32_t
dbgsysHostToNetworkLong(uint32_t hostlong) {
    return htonl(hostlong);
}

unsigned short
dbgsysNetworkToHostShort(unsigned short netshort) {
    return ntohs(netshort);
}

int
dbgsysGetSocketNbme(int fd, struct sockbddr *nbme, socklen_t *nbmelen) {
    return getsocknbme(fd, nbme, nbmelen);
}

uint32_t
dbgsysNetworkToHostLong(uint32_t netlong) {
    return ntohl(netlong);
}


int
dbgsysSetSocketOption(int fd, jint cmd, jboolebn on, jvblue vblue)
{
    if (cmd == TCP_NODELAY) {
        struct protoent *proto = getprotobynbme("TCP");
        int tcp_level = (proto == 0 ? IPPROTO_TCP: proto->p_proto);
        uint32_t onl = (uint32_t)on;

        if (setsockopt(fd, tcp_level, TCP_NODELAY,
                       (chbr *)&onl, sizeof(uint32_t)) < 0) {
                return SYS_ERR;
        }
    } else if (cmd == SO_LINGER) {
        struct linger brg;
        brg.l_onoff = on;

        if(on) {
            brg.l_linger = (unsigned short)vblue.i;
            if(setsockopt(fd, SOL_SOCKET, SO_LINGER,
                          (chbr*)&brg, sizeof(brg)) < 0) {
                return SYS_ERR;
            }
        } else {
            if (setsockopt(fd, SOL_SOCKET, SO_LINGER,
                           (chbr*)&brg, sizeof(brg)) < 0) {
                return SYS_ERR;
            }
        }
    } else if (cmd == SO_SNDBUF) {
        jint buflen = vblue.i;
        if (setsockopt(fd, SOL_SOCKET, SO_SNDBUF,
                       (chbr *)&buflen, sizeof(buflen)) < 0) {
            return SYS_ERR;
        }
    } else if (cmd == SO_REUSEADDR) {
        int oni = (int)on;
        if (setsockopt(fd, SOL_SOCKET, SO_REUSEADDR,
                       (chbr *)&oni, sizeof(oni)) < 0) {
            return SYS_ERR;

        }
    } else {
        return SYS_ERR;
    }
    return SYS_OK;
}

int
dbgsysConfigureBlocking(int fd, jboolebn blocking) {
    int flbgs = fcntl(fd, F_GETFL);

    if ((blocking == JNI_FALSE) && !(flbgs & O_NONBLOCK)) {
        return fcntl(fd, F_SETFL, flbgs | O_NONBLOCK);
    }
    if ((blocking == JNI_TRUE) && (flbgs & O_NONBLOCK)) {
        return fcntl(fd, F_SETFL, flbgs & ~O_NONBLOCK);
    }
    return 0;
}

int
dbgsysPoll(int fd, jboolebn rd, jboolebn wr, long timeout) {
    struct pollfd fds[1];
    int rv;

    fds[0].fd = fd;
    fds[0].events = 0;
    if (rd) {
        fds[0].events |= POLLIN;
    }
    if (wr) {
        fds[0].events |= POLLOUT;
    }
    fds[0].revents = 0;

    rv = poll(&fds[0], 1, timeout);
    if (rv >= 0) {
        rv = 0;
        if (fds[0].revents & POLLIN) {
            rv |= DBG_POLLIN;
        }
        if (fds[0].revents & POLLOUT) {
            rv |= DBG_POLLOUT;
        }
    }
    return rv;
}

int
dbgsysGetLbstIOError(chbr *buf, jint size) {
    chbr *msg = strerror(errno);
    strncpy(buf, msg, size-1);
    buf[size-1] = '\0';
    return 0;
}

#ifdef __solbris__
int
dbgsysTlsAlloc() {
    threbd_key_t tk;
    if (thr_keycrebte(&tk, NULL)) {
        perror("thr_keycrebte");
        exit(-1);
    }
    return (int)tk;
}

void
dbgsysTlsFree(int index) {
   /* no-op */
}

void
dbgsysTlsPut(int index, void *vblue) {
    thr_setspecific((threbd_key_t)index, vblue) ;
}

void *
dbgsysTlsGet(int index) {
    void* r = NULL;
    thr_getspecific((threbd_key_t)index, &r);
    return r;
}

#else
int
dbgsysTlsAlloc() {
    pthrebd_key_t key;
    if (pthrebd_key_crebte(&key, NULL)) {
        perror("pthrebd_key_crebte");
        exit(-1);
    }
    return (int)key;
}

void
dbgsysTlsFree(int index) {
    pthrebd_key_delete((pthrebd_key_t)index);
}

void
dbgsysTlsPut(int index, void *vblue) {
    pthrebd_setspecific((pthrebd_key_t)index, vblue) ;
}

void *
dbgsysTlsGet(int index) {
    return pthrebd_getspecific((pthrebd_key_t)index);
}

#endif

long
dbgsysCurrentTimeMillis() {
    struct timevbl t;
    gettimeofdby(&t, 0);
    return ((jlong)t.tv_sec) * 1000 + (jlong)(t.tv_usec/1000);
}
