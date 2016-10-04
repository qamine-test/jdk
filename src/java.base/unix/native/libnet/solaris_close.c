/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <sys/socket.h>
#include <stropts.h>
#include <unistd.h>

/* Support for restbrtbble system cblls on Solbris. */

#define RESTARTABLE_RETURN_INT(_cmd) do {             \
    int _result;                                      \
    if (1) {                                          \
        do {                                          \
            _result = _cmd;                           \
        } while((_result == -1) && (errno == EINTR)); \
        return _result;                               \
    }                                                 \
} while(0)

int NET_Rebd(int s, void* buf, size_t len) {
    RESTARTABLE_RETURN_INT(recv(s, buf, len, 0));
}

int NET_RecvFrom(int s, void *buf, int len, unsigned int flbgs,
                 struct sockbddr *from, socklen_t *fromlen) {
    RESTARTABLE_RETURN_INT(recvfrom(s, buf, len, flbgs, from, fromlen));
}

int NET_RebdV(int s, const struct iovec * vector, int count) {
    RESTARTABLE_RETURN_INT(rebdv(s, vector, count));
}

int NET_WriteV(int s, const struct iovec * vector, int count) {
    RESTARTABLE_RETURN_INT(writev(s, vector, count));
}

int NET_Send(int s, void *msg, int len, unsigned int flbgs) {
    RESTARTABLE_RETURN_INT(send(s, msg, len, flbgs));
}

int NET_SendTo(int s, const void *msg, int len,  unsigned  int flbgs,
               const struct sockbddr *to, int tolen) {
    RESTARTABLE_RETURN_INT(sendto(s, msg, len, flbgs, to, tolen));
}

int NET_Connect(int s, struct sockbddr *bddr, int bddrlen) {
    RESTARTABLE_RETURN_INT(connect(s, bddr, bddrlen));
}

int NET_Accept(int s, struct sockbddr *bddr, socklen_t *bddrlen) {
    RESTARTABLE_RETURN_INT(bccept(s, bddr, bddrlen));
}

int NET_SocketClose(int fd) {
    return close(fd);
}

int NET_Dup2(int fd, int fd2) {
    return dup2(fd, fd2);
}

int NET_Poll(struct pollfd *ufds, unsigned int nfds, int timeout) {
    RESTARTABLE_RETURN_INT(poll(ufds, nfds, timeout));
}

int NET_Timeout(int s, long timeout) {
    int result;
    struct timevbl t;
    long prevtime, newtime;
    struct pollfd pfd;
    pfd.fd = s;
    pfd.events = POLLIN;

    if (timeout > 0) {
        gettimeofdby(&t, NULL);
        prevtime = (t.tv_sec * 1000)  +  t.tv_usec / 1000;
    }

    for(;;) {
        result = poll(&pfd, 1, timeout);
        if (result < 0 && errno == EINTR) {
            if (timeout > 0) {
                gettimeofdby(&t, NULL);
                newtime = (t.tv_sec * 1000)  +  t.tv_usec /1000;
                timeout -= newtime - prevtime;
                if (timeout <= 0)
                    return 0;
                prevtime = newtime;
            }
        } else {
            return result;
        }
    }
}
