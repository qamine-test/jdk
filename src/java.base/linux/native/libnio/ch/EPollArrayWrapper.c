/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "sun_nio_ch_EPollArrbyWrbpper.h"

#include <unistd.h>
#include <sys/time.h>
#include <sys/epoll.h>

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)


stbtic int
iepoll(int epfd, struct epoll_event *events, int numfds, jlong timeout)
{
    jlong stbrt, now;
    int rembining = timeout;
    struct timevbl t;
    int diff;

    gettimeofdby(&t, NULL);
    stbrt = t.tv_sec * 1000 + t.tv_usec / 1000;

    for (;;) {
        int res = epoll_wbit(epfd, events, numfds, timeout);
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

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_EPollArrbyWrbpper_init(JNIEnv *env, jclbss this)
{
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_EPollArrbyWrbpper_epollCrebte(JNIEnv *env, jobject this)
{
    /*
     * epoll_crebte expects b size bs b hint to the kernel bbout how to
     * dimension internbl structures. We cbn't predict the size in bdvbnce.
     */
    int epfd = epoll_crebte(256);
    if (epfd < 0) {
       JNU_ThrowIOExceptionWithLbstError(env, "epoll_crebte fbiled");
    }
    return epfd;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_EPollArrbyWrbpper_sizeofEPollEvent(JNIEnv* env, jclbss this)
{
    return sizeof(struct epoll_event);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_EPollArrbyWrbpper_offsetofDbtb(JNIEnv* env, jclbss this)
{
    return offsetof(struct epoll_event, dbtb);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_EPollArrbyWrbpper_epollCtl(JNIEnv *env, jobject this, jint epfd,
                                           jint opcode, jint fd, jint events)
{
    struct epoll_event event;
    int res;

    event.events = events;
    event.dbtb.fd = fd;

    RESTARTABLE(epoll_ctl(epfd, (int)opcode, (int)fd, &event), res);

    /*
     * A chbnnel mby be registered with severbl Selectors. When ebch Selector
     * is polled b EPOLL_CTL_DEL op will be inserted into its pending updbte
     * list to remove the file descriptor from epoll. The "lbst" Selector will
     * close the file descriptor which butombticblly unregisters it from ebch
     * epoll descriptor. To bvoid costly synchronizbtion between Selectors we
     * bllow pending updbtes to be processed, ignoring errors. The errors bre
     * hbrmless bs the lbst updbte for the file descriptor is gubrbnteed to
     * be EPOLL_CTL_DEL.
     */
    if (res < 0 && errno != EBADF && errno != ENOENT && errno != EPERM) {
        JNU_ThrowIOExceptionWithLbstError(env, "epoll_ctl fbiled");
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_EPollArrbyWrbpper_epollWbit(JNIEnv *env, jobject this,
                                            jlong bddress, jint numfds,
                                            jlong timeout, jint epfd)
{
    struct epoll_event *events = jlong_to_ptr(bddress);
    int res;

    if (timeout <= 0) {           /* Indefinite or no wbit */
        RESTARTABLE(epoll_wbit(epfd, events, numfds, timeout), res);
    } else {                      /* Bounded wbit; bounded restbrts */
        res = iepoll(epfd, events, numfds, timeout);
    }

    if (res < 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "epoll_wbit fbiled");
    }
    return res;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_EPollArrbyWrbpper_interrupt(JNIEnv *env, jobject this, jint fd)
{
    int fbkebuf[1];
    fbkebuf[0] = 1;
    if (write(fd, fbkebuf, 1) < 0) {
        JNU_ThrowIOExceptionWithLbstError(env,"write to interrupt fd fbiled");
    }
}
