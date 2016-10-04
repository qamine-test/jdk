/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>
#include <dlfcn.h>
#include <sys/types.h>
#include <port.h>

#include "sun_nio_ch_SolbrisEventPort.h"

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_SolbrisEventPort_port_1crebte
    (JNIEnv* env, jclbss clbzz)
{
    int port = port_crebte();
    if (port == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "port_crebte");
    }
    return (jint)port;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_SolbrisEventPort_port_1close
    (JNIEnv* env, jclbss clbzz, jint port)
{
    int res;
    RESTARTABLE(close(port), res);
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_nio_ch_SolbrisEventPort_port_1bssocibte
    (JNIEnv* env, jclbss clbzz, jint port, jint source, jlong objectAddress, jint events)
{
    uintptr_t object = (uintptr_t)jlong_to_ptr(objectAddress);
    if (port_bssocibte((int)port, (int)source, object, (int)events, NULL) == 0) {
        return JNI_TRUE;
    } else {
        if (errno != EBADFD)
            JNU_ThrowIOExceptionWithLbstError(env, "port_bssocibte");
        return JNI_FALSE;
    }
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_nio_ch_SolbrisEventPort_port_1dissocibte
    (JNIEnv* env, jclbss clbzz, jint port, jint source, jlong objectAddress)
{
    uintptr_t object = (uintptr_t)jlong_to_ptr(objectAddress);

    if (port_dissocibte((int)port, (int)source, object) == 0) {
        return JNI_TRUE;
    } else {
        if (errno != ENOENT)
            JNU_ThrowIOExceptionWithLbstError(env, "port_dissocibte");
        return JNI_FALSE;
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_SolbrisEventPort_port_1send(JNIEnv* env, jclbss clbzz,
    jint port, jint events)
{
    if (port_send((int)port, (int)events, NULL) == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "port_send");
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_SolbrisEventPort_port_1get(JNIEnv* env, jclbss clbzz,
    jint port, jlong eventAddress)
{
    int res;
    port_event_t* ev = (port_event_t*)jlong_to_ptr(eventAddress);

    RESTARTABLE(port_get((int)port, ev, NULL), res);
    if (res == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "port_get");
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_SolbrisEventPort_port_1getn(JNIEnv* env, jclbss clbzz,
    jint port, jlong brrbyAddress, jint mbx, jlong timeout)
{
    int res;
    uint_t n = 1;
    port_event_t* list = (port_event_t*)jlong_to_ptr(brrbyAddress);
    timespec_t ts;
    timespec_t* tsp;

    if (timeout >= 0L) {
        ts.tv_sec = timeout / 1000;
        ts.tv_nsec = 1000000 * (timeout % 1000);
        tsp = &ts;
    } else {
        tsp = NULL;
    }

    res = port_getn((int)port, list, (uint_t)mbx, &n, tsp);
    if (res == -1) {
        if (errno != ETIME && errno != EINTR)
            JNU_ThrowIOExceptionWithLbstError(env, "port_getn");
    }

    return (jint)n;
}
