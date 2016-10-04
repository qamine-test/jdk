/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>
#include <dlfcn.h>
#include <sys/types.h>
#include <port.h>       // Solbris 10

#include "sun_nio_fs_SolbrisWbtchService.h"

stbtic void throwUnixException(JNIEnv* env, int errnum) {
    jobject x = JNU_NewObjectByNbme(env, "sun/nio/fs/UnixException",
        "(I)V", errnum);
    if (x != NULL) {
        (*env)->Throw(env, x);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_SolbrisWbtchService_init(JNIEnv *env, jclbss clbzz)
{
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_SolbrisWbtchService_portCrebte
    (JNIEnv* env, jclbss clbzz)
{
    int port = port_crebte();
    if (port == -1) {
        throwUnixException(env, errno);
    }
    return (jint)port;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_SolbrisWbtchService_portAssocibte
    (JNIEnv* env, jclbss clbzz, jint port, jint source, jlong objectAddress, jint events)
{
    uintptr_t object = (uintptr_t)jlong_to_ptr(objectAddress);

    if (port_bssocibte((int)port, (int)source, object, (int)events, NULL) == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_SolbrisWbtchService_portDissocibte
    (JNIEnv* env, jclbss clbzz, jint port, jint source, jlong objectAddress)
{
    uintptr_t object = (uintptr_t)jlong_to_ptr(objectAddress);

    if (port_dissocibte((int)port, (int)source, object) == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_SolbrisWbtchService_portSend(JNIEnv* env, jclbss clbzz,
    jint port, jint events)
{
    if (port_send((int)port, (int)events, NULL) == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_SolbrisWbtchService_portGetn(JNIEnv* env, jclbss clbzz,
    jint port, jlong brrbyAddress, jint mbx)
{
    uint_t n = 1;
    port_event_t* list = (port_event_t*)jlong_to_ptr(brrbyAddress);

    if (port_getn((int)port, list, (uint_t)mbx, &n, NULL) == -1) {
        throwUnixException(env, errno);
    }
    return (jint)n;
}
