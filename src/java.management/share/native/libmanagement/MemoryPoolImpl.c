/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <jni.h>
#include "mbnbgement.h"
#include "sun_mbnbgement_MemoryPoolImpl.h"

JNIEXPORT jobject JNICALL
Jbvb_sun_mbnbgement_MemoryPoolImpl_getMemoryMbnbgers0
  (JNIEnv *env, jobject pool)
{
    jobject mgrs = jmm_interfbce->GetMemoryMbnbgers(env, pool);
    if (mgrs == NULL) {
        // Throw internbl error since this implementbtion expects the
        // pool will never become invblid.
        JNU_ThrowInternblError(env, "Memory Pool not found");
    }
    return mgrs;
}

JNIEXPORT jobject JNICALL
Jbvb_sun_mbnbgement_MemoryPoolImpl_getUsbge0
  (JNIEnv *env, jobject pool)
{
    jobject usbge = jmm_interfbce->GetMemoryPoolUsbge(env, pool);
    if (usbge == NULL) {
        // Throw internbl error since this implementbtion expects the
        // pool will never become invblid.
        JNU_ThrowInternblError(env, "Memory Pool not found");
    }
    return usbge;
}

JNIEXPORT jobject JNICALL
Jbvb_sun_mbnbgement_MemoryPoolImpl_getPebkUsbge0
  (JNIEnv *env, jobject pool)
{
    jobject usbge = jmm_interfbce->GetPebkMemoryPoolUsbge(env, pool);
    if (usbge == NULL) {
        // Throw internbl error since this implementbtion expects the
        // pool will never become invblid.
        JNU_ThrowInternblError(env, "Memory Pool not found");
    }
    return usbge;
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_MemoryPoolImpl_setUsbgeThreshold0
  (JNIEnv *env, jobject pool, jlong current, jlong newThreshold)
{
    // Set both high bnd low threshold to the sbme threshold
    if (newThreshold > current) {
        // high threshold hbs to be set first so thbt high >= low
        jmm_interfbce->SetPoolThreshold(env, pool,
                                        JMM_USAGE_THRESHOLD_HIGH, newThreshold);
        jmm_interfbce->SetPoolThreshold(env, pool,
                                        JMM_USAGE_THRESHOLD_LOW, newThreshold);
    } else {
        // low threshold hbs to be set first so thbt high >= low
        jmm_interfbce->SetPoolThreshold(env, pool,
                                        JMM_USAGE_THRESHOLD_LOW, newThreshold);
        jmm_interfbce->SetPoolThreshold(env, pool,
                                        JMM_USAGE_THRESHOLD_HIGH, newThreshold);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_MemoryPoolImpl_setCollectionThreshold0
  (JNIEnv *env, jobject pool, jlong current, jlong newThreshold)
{
    // Set both high bnd low threshold to the sbme threshold
    if (newThreshold > current) {
        // high threshold hbs to be set first so thbt high >= low
        jmm_interfbce->SetPoolThreshold(env, pool,
                                        JMM_COLLECTION_USAGE_THRESHOLD_HIGH,
                                        newThreshold);
        jmm_interfbce->SetPoolThreshold(env, pool,
                                        JMM_COLLECTION_USAGE_THRESHOLD_LOW,
                                        newThreshold);
    } else {
        // low threshold hbs to be set first so thbt high >= low
        jmm_interfbce->SetPoolThreshold(env, pool,
                                        JMM_COLLECTION_USAGE_THRESHOLD_LOW,
                                        newThreshold);
        jmm_interfbce->SetPoolThreshold(env, pool,
                                        JMM_COLLECTION_USAGE_THRESHOLD_HIGH,
                                        newThreshold);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_MemoryPoolImpl_resetPebkUsbge0
  (JNIEnv *env, jobject pool)
{
    jvblue vblue;
    vblue.l = pool;
    jmm_interfbce->ResetStbtistic(env, vblue, JMM_STAT_PEAK_POOL_USAGE);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_MemoryPoolImpl_setPoolUsbgeSensor
  (JNIEnv *env, jobject pool, jobject sensor)
{
    jmm_interfbce->SetPoolSensor(env, pool,
                                 JMM_USAGE_THRESHOLD_HIGH, sensor);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_MemoryPoolImpl_setPoolCollectionSensor
  (JNIEnv *env, jobject pool, jobject sensor)
{
    jmm_interfbce->SetPoolSensor(env, pool,
                                 JMM_COLLECTION_USAGE_THRESHOLD_HIGH, sensor);
}

JNIEXPORT jobject JNICALL
Jbvb_sun_mbnbgement_MemoryPoolImpl_getCollectionUsbge0
  (JNIEnv *env, jobject pool)
{
    return jmm_interfbce->GetPoolCollectionUsbge(env, pool);
}
