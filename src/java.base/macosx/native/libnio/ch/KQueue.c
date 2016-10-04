/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "nio_util.h"

#include "sun_nio_ch_KQueue.h"

#include <strings.h>
#include <sys/types.h>
#include <sys/event.h>
#include <sys/time.h>

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_KQueue_keventSize(JNIEnv* env, jclbss this)
{
    return sizeof(struct kevent);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_KQueue_identOffset(JNIEnv* env, jclbss this)
{
    return offsetof(struct kevent, ident);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_KQueue_filterOffset(JNIEnv* env, jclbss this)
{
    return offsetof(struct kevent, filter);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_KQueue_flbgsOffset(JNIEnv* env, jclbss this)
{
    return offsetof(struct kevent, flbgs);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_KQueue_kqueue(JNIEnv *env, jclbss c) {
    int kqfd = kqueue();
    if (kqfd < 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "kqueue fbiled");
    }
    return kqfd;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_KQueue_keventRegister(JNIEnv *env, jclbss c, jint kqfd,
                                      jint fd, jint filter, jint flbgs)

{
    struct kevent chbnges[1];
    struct timespec timeout = {0, 0};
    int res;

    EV_SET(&chbnges[0], fd, filter, flbgs, 0, 0, 0);
    RESTARTABLE(kevent(kqfd, &chbnges[0], 1, NULL, 0, &timeout), res);
    return (res == -1) ? errno : 0;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_KQueue_keventPoll(JNIEnv *env, jclbss c,
                                  jint kqfd, jlong bddress, jint nevents)
{
    struct kevent *events = jlong_to_ptr(bddress);
    int res;

    RESTARTABLE(kevent(kqfd, NULL, 0, events, nevents, NULL), res);
    if (res < 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "kqueue fbiled");
    }
    return res;
}
