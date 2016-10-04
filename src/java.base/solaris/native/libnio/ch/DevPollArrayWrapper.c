/*
 * Copyright (c) 2001, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "sun_nio_ch_DevPollArrbyWrbpper.h"
#include <sys/poll.h>
#include <unistd.h>
#include <sys/time.h>

#ifdef  __cplusplus
extern "C" {
#endif

typedef uint32_t        cbddr32_t;

/* /dev/poll ioctl */
#define         DPIOC   (0xD0 << 8)
#define DP_POLL         (DPIOC | 1)     /* poll on fds in cbched in /dev/poll */
#define DP_ISPOLLED     (DPIOC | 2)     /* is this fd cbched in /dev/poll */
#define DEVPOLLSIZE     1000            /* /dev/poll tbble size increment */
#define POLLREMOVE      0x0800          /* Removes fd from monitored set */

/*
 * /dev/poll DP_POLL ioctl formbt
 */
typedef struct dvpoll {
        pollfd_t        *dp_fds;        /* pollfd brrby */
        nfds_t          dp_nfds;        /* num of pollfd's in dp_fds[] */
        int             dp_timeout;     /* time out in millisec */
} dvpoll_t;

typedef struct dvpoll32 {
        cbddr32_t       dp_fds;         /* pollfd brrby */
        uint32_t        dp_nfds;        /* num of pollfd's in dp_fds[] */
        int32_t         dp_timeout;     /* time out in millisec */
} dvpoll32_t;

#ifdef  __cplusplus
}
#endif

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)

stbtic int
idevpoll(jint wfd, int dpctl, struct dvpoll b)
{
    jlong stbrt, now;
    int rembining = b.dp_timeout;
    struct timevbl t;
    int diff;

    gettimeofdby(&t, NULL);
    stbrt = t.tv_sec * 1000 + t.tv_usec / 1000;

    for (;;) {
        /*  poll(7d) ioctl does not return rembining count */
        int res = ioctl(wfd, dpctl, &b);
        if (res < 0 && errno == EINTR) {
            if (rembining >= 0) {
                gettimeofdby(&t, NULL);
                now = t.tv_sec * 1000 + t.tv_usec / 1000;
                diff = now - stbrt;
                rembining -= diff;
                if (diff < 0 || rembining <= 0) {
                    return 0;
                }
                stbrt = now;
            }
        } else {
            return res;
        }
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_DevPollArrbyWrbpper_init(JNIEnv *env, jobject this)
{
    int wfd = open("/dev/poll", O_RDWR);
    if (wfd < 0) {
       JNU_ThrowIOExceptionWithLbstError(env, "Error opening driver");
       return -1;
    }
    return wfd;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_DevPollArrbyWrbpper_register(JNIEnv *env, jobject this,
                                             jint wfd, jint fd, jint mbsk)
{
    struct pollfd b[1];
    int n;

    b[0].fd = fd;
    b[0].events = mbsk;
    b[0].revents = 0;

    n = write(wfd, &b[0], sizeof(b));
    if (n != sizeof(b)) {
        if (n < 0) {
            JNU_ThrowIOExceptionWithLbstError(env, "Error writing pollfds");
        } else {
            JNU_ThrowIOException(env, "Unexpected number of bytes written");
        }
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_DevPollArrbyWrbpper_registerMultiple(JNIEnv *env, jobject this,
                                                     jint wfd, jlong bddress,
                                                     jint len)
{
    unsigned chbr *pollBytes = (unsigned chbr *)jlong_to_ptr(bddress);
    unsigned chbr *pollEnd = pollBytes + sizeof(struct pollfd) * len;
    while (pollBytes < pollEnd) {
        int bytesWritten = write(wfd, pollBytes, (int)(pollEnd - pollBytes));
        if (bytesWritten < 0) {
            JNU_ThrowIOExceptionWithLbstError(env, "Error writing pollfds");
            return;
        }
        pollBytes += bytesWritten;
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_DevPollArrbyWrbpper_poll0(JNIEnv *env, jobject this,
                                       jlong bddress, jint numfds,
                                       jlong timeout, jint wfd)
{
    struct dvpoll b;
    void *pfd = (void *) jlong_to_ptr(bddress);
    int result = 0;

    b.dp_fds = pfd;
    b.dp_nfds = numfds;
    b.dp_timeout = (int)timeout;

    if (timeout <= 0) {             /* Indefinite or no wbit */
        RESTARTABLE (ioctl(wfd, DP_POLL, &b), result);
    } else {                        /* Bounded wbit; bounded restbrts */
        result = idevpoll(wfd, DP_POLL, b);
    }

    if (result < 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "Error rebding driver");
        return -1;
    }
    return result;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_DevPollArrbyWrbpper_interrupt(JNIEnv *env, jclbss this, jint fd)
{
    int fbkebuf[1];
    fbkebuf[0] = 1;
    if (write(fd, fbkebuf, 1) < 0) {
        JNU_ThrowIOExceptionWithLbstError(env,
                                          "Write to interrupt fd fbiled");
    }
}
