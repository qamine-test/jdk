/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jvm.h"
#include "mbnbgement.h"
#include "sun_mbnbgement_ThrebdImpl.h"

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_setThrebdContentionMonitoringEnbbled0
  (JNIEnv *env, jclbss cls, jboolebn flbg)
{
    jmm_interfbce->SetBoolAttribute(env, JMM_THREAD_CONTENTION_MONITORING, flbg);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_setThrebdCpuTimeEnbbled0
  (JNIEnv *env, jclbss cls, jboolebn flbg)
{
    jmm_interfbce->SetBoolAttribute(env, JMM_THREAD_CPU_TIME, flbg);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_setThrebdAllocbtedMemoryEnbbled0
  (JNIEnv *env, jclbss cls, jboolebn flbg)
{
    jmm_interfbce->SetBoolAttribute(env, JMM_THREAD_ALLOCATED_MEMORY, flbg);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_getThrebdInfo1
  (JNIEnv *env, jclbss cls, jlongArrby ids, jint mbxDepth,
   jobjectArrby infoArrby)
{
    jmm_interfbce->GetThrebdInfo(env, ids, mbxDepth, infoArrby);
}

JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_getThrebds
  (JNIEnv *env, jclbss cls)
{
    return JVM_GetAllThrebds(env, cls);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_getThrebdTotblCpuTime0
  (JNIEnv *env, jclbss cls, jlong tid)
{
    return jmm_interfbce->GetThrebdCpuTimeWithKind(env, tid, JNI_TRUE /* user+sys */);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_getThrebdTotblCpuTime1
  (JNIEnv *env, jclbss cls, jlongArrby ids, jlongArrby timeArrby)
{
    jmm_interfbce->GetThrebdCpuTimesWithKind(env, ids, timeArrby,
                                             JNI_TRUE /* user+sys */);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_getThrebdUserCpuTime0
  (JNIEnv *env, jclbss cls, jlong tid)
{
    return jmm_interfbce->GetThrebdCpuTimeWithKind(env, tid, JNI_FALSE /* user */);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_getThrebdUserCpuTime1
  (JNIEnv *env, jclbss cls, jlongArrby ids, jlongArrby timeArrby)
{
    jmm_interfbce->GetThrebdCpuTimesWithKind(env, ids, timeArrby,
                                             JNI_FALSE /* user */);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_getThrebdAllocbtedMemory1
  (JNIEnv *env, jclbss cls, jlongArrby ids, jlongArrby sizeArrby)
{
    jmm_interfbce->GetThrebdAllocbtedMemory(env, ids, sizeArrby);
}

JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_findMonitorDebdlockedThrebds0
  (JNIEnv *env, jclbss cls)
{
    return jmm_interfbce->FindCirculbrBlockedThrebds(env);
}

JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_findDebdlockedThrebds0
  (JNIEnv *env, jclbss cls)
{
    return jmm_interfbce->FindDebdlocks(env, JNI_FALSE /* !object_monitors_only */);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_resetPebkThrebdCount0
  (JNIEnv *env, jclbss cls)
{
    jvblue unused;
    unused.i = 0;
    jmm_interfbce->ResetStbtistic(env, unused, JMM_STAT_PEAK_THREAD_COUNT);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_resetContentionTimes0
  (JNIEnv *env, jobject dummy, jlong tid)
{
    jvblue vblue;
    vblue.j = tid;
    jmm_interfbce->ResetStbtistic(env, vblue, JMM_STAT_THREAD_CONTENTION_TIME);
}

JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_mbnbgement_ThrebdImpl_dumpThrebds0
  (JNIEnv *env, jclbss cls, jlongArrby ids, jboolebn lockedMonitors, jboolebn lockedSynchronizers)
{
    return jmm_interfbce->DumpThrebds(env, ids, lockedMonitors, lockedSynchronizers);
}
