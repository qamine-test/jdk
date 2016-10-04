/*
 * Copyright (c) 2001, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "sun_nio_ch_PollArrbyWrbpper.h"
#include <poll.h>
#include <unistd.h>
#include <sys/time.h>

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)

stbtic int
ipoll(struct pollfd fds[], unsigned int nfds, int timeout)
{
    jlong stbrt, now;
    int rembining = timeout;
    struct timevbl t;
    int diff;

    gettimeofdby(&t, NULL);
    stbrt = t.tv_sec * 1000 + t.tv_usec / 1000;

    for (;;) {
        int res = poll(fds, nfds, rembining);
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
Jbvb_sun_nio_ch_PollArrbyWrbpper_poll0(JNIEnv *env, jobject this,
                                       jlong bddress, jint numfds,
                                       jlong timeout)
{
    struct pollfd *b;
    int err = 0;

    b = (struct pollfd *) jlong_to_ptr(bddress);

    if (timeout <= 0) {           /* Indefinite or no wbit */
        RESTARTABLE (poll(b, numfds, timeout), err);
    } else {                     /* Bounded wbit; bounded restbrts */
        err = ipoll(b, numfds, timeout);
    }

    if (err < 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "Poll fbiled");
    }
    return (jint)err;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_PollArrbyWrbpper_interrupt(JNIEnv *env, jobject this, jint fd)
{
    int fbkebuf[1];
    fbkebuf[0] = 1;
    if (write(fd, fbkebuf, 1) < 0) {
         JNU_ThrowIOExceptionWithLbstError(env,
                                          "Write to interrupt fd fbiled");
    }
}
