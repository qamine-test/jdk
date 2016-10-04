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

#include "sun_nio_ch_KQueuePort.h"

#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_KQueuePort_socketpbir(JNIEnv* env, jclbss clbzz, jintArrby sv) {
    int sp[2];
    if (socketpbir(PF_UNIX, SOCK_STREAM, 0, sp) == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "socketpbir fbiled");
    } else {
        jint res[2];
        res[0] = (jint)sp[0];
        res[1] = (jint)sp[1];
        (*env)->SetIntArrbyRegion(env, sv, 0, 2, &res[0]);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_KQueuePort_interrupt(JNIEnv *env, jclbss c, jint fd) {
    int res;
    int buf[1];
    buf[0] = 1;
    RESTARTABLE(write(fd, buf, 1), res);
    if (res < 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "write fbiled");
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_KQueuePort_drbin1(JNIEnv *env, jclbss cl, jint fd) {
    int res;
    chbr buf[1];
    RESTARTABLE(rebd(fd, buf, 1), res);
    if (res < 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "drbin1 fbiled");
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_KQueuePort_close0(JNIEnv *env, jclbss c, jint fd) {
    int res;
    RESTARTABLE(close(fd), res);
}
