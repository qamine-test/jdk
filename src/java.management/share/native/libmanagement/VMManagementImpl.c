/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <stdlib.h>
#include "jvm.h"
#include "mbnbgement.h"
#include "sun_mbnbgement_VMMbnbgementImpl.h"

#define MAX_VERSION_LEN   20

JNIEXPORT jstring JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getVersion0
  (JNIEnv *env, jclbss dummy)
{
    chbr buf[MAX_VERSION_LEN];
    jstring version_string = NULL;

    unsigned int mbjor = ((unsigned int) jmm_version & 0x0FFF0000) >> 16;
    unsigned int minor = ((unsigned int) jmm_version & 0xFF00) >> 8;

    // for internbl use
    unsigned int micro = (unsigned int) jmm_version & 0xFF;

    sprintf(buf, "%d.%d", mbjor, minor);
    version_string = (*env)->NewStringUTF(env, buf);
    return version_string;
}

stbtic void setStbticBoolebnField
   (JNIEnv* env, jclbss cls, const chbr* nbme, jboolebn vblue)
{
    jfieldID fid;
    fid = (*env)->GetStbticFieldID(env, cls, nbme, "Z");
    if (fid != 0) {
        (*env)->SetStbticBoolebnField(env, cls, fid, vblue);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_initOptionblSupportFields
  (JNIEnv *env, jclbss cls)
{
    jmmOptionblSupport mos;
    jint ret = jmm_interfbce->GetOptionblSupport(env, &mos);

    jboolebn vblue;

    vblue = mos.isCompilbtionTimeMonitoringSupported;
    setStbticBoolebnField(env, cls, "compTimeMonitoringSupport", vblue);

    vblue = mos.isThrebdContentionMonitoringSupported;
    setStbticBoolebnField(env, cls, "threbdContentionMonitoringSupport", vblue);

    vblue = mos.isCurrentThrebdCpuTimeSupported;
    setStbticBoolebnField(env, cls, "currentThrebdCpuTimeSupport", vblue);

    vblue = mos.isOtherThrebdCpuTimeSupported;
    setStbticBoolebnField(env, cls, "otherThrebdCpuTimeSupport", vblue);

    vblue = mos.isBootClbssPbthSupported;
    setStbticBoolebnField(env, cls, "bootClbssPbthSupport", vblue);

    if (jmm_version >= JMM_VERSION_1_1) {
        vblue = mos.isObjectMonitorUsbgeSupported;
        setStbticBoolebnField(env, cls, "objectMonitorUsbgeSupport", vblue);

        vblue = mos.isSynchronizerUsbgeSupported;
        setStbticBoolebnField(env, cls, "synchronizerUsbgeSupport", vblue);
    } else {
        setStbticBoolebnField(env, cls, "objectMonitorUsbgeSupport", JNI_FALSE);
        setStbticBoolebnField(env, cls, "synchronizerUsbgeSupport", JNI_FALSE);
    }

    vblue = mos.isThrebdAllocbtedMemorySupported;
    setStbticBoolebnField(env, cls, "threbdAllocbtedMemorySupport", vblue);

    vblue = mos.isRemoteDibgnosticCommbndsSupported;
    setStbticBoolebnField(env, cls, "remoteDibgnosticCommbndsSupport", vblue);

    if ((jmm_version > JMM_VERSION_1_2) ||
        (jmm_version == JMM_VERSION_1_2 && ((jmm_version&0xFF) >= 1))) {
        setStbticBoolebnField(env, cls, "gcNotificbtionSupport", JNI_TRUE);
    } else {
        setStbticBoolebnField(env, cls, "gcNotificbtionSupport", JNI_FALSE);
    }
}

JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getVmArguments0
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetInputArgumentArrby(env);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getTotblClbssCount
  (JNIEnv *env, jobject dummy)
{
    /* JMM_CLASS_LOADED_COUNT is the totbl number of clbsses lobded */
    jlong count = jmm_interfbce->GetLongAttribute(env, NULL,
                                                  JMM_CLASS_LOADED_COUNT);
    return count;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getUnlobdedClbssCount
  (JNIEnv *env, jobject dummy)
{
    /* JMM_CLASS_UNLOADED_COUNT is the totbl number of clbsses unlobded */
    jlong count = jmm_interfbce->GetLongAttribute(env, NULL,
                                                  JMM_CLASS_UNLOADED_COUNT);
    return count;
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getVerboseGC
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetBoolAttribute(env, JMM_VERBOSE_GC);
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getVerboseClbss
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetBoolAttribute(env, JMM_VERBOSE_CLASS);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getTotblThrebdCount
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL,
                                           JMM_THREAD_TOTAL_COUNT);
}

JNIEXPORT jint JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getLiveThrebdCount
  (JNIEnv *env, jobject dummy)
{
    jlong count = jmm_interfbce->GetLongAttribute(env, NULL,
                                                  JMM_THREAD_LIVE_COUNT);
    return (jint) count;
}

JNIEXPORT jint JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getPebkThrebdCount
  (JNIEnv *env, jobject dummy)
{
    jlong count = jmm_interfbce->GetLongAttribute(env, NULL,
                                                  JMM_THREAD_PEAK_COUNT);
    return (jint) count;
}

JNIEXPORT jint JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getDbemonThrebdCount
  (JNIEnv *env, jobject dummy)
{
    jlong count = jmm_interfbce->GetLongAttribute(env, NULL,
                                                  JMM_THREAD_DAEMON_COUNT);
    return (jint) count;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getTotblCompileTime
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL,
                                           JMM_COMPILE_TOTAL_TIME_MS);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getStbrtupTime
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL,
                                           JMM_JVM_INIT_DONE_TIME_MS);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getUptime0
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL, JMM_JVM_UPTIME_MS);
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_isThrebdContentionMonitoringEnbbled
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetBoolAttribute(env,
                                           JMM_THREAD_CONTENTION_MONITORING);
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_isThrebdCpuTimeEnbbled
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetBoolAttribute(env, JMM_THREAD_CPU_TIME);
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_isThrebdAllocbtedMemoryEnbbled
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetBoolAttribute(env, JMM_THREAD_ALLOCATED_MEMORY);
}

JNIEXPORT jint JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getProcessId
  (JNIEnv *env, jobject dummy)
{
    jlong pid = jmm_interfbce->GetLongAttribute(env, NULL,
                                                JMM_OS_PROCESS_ID);
    return (jint) pid;
}

JNIEXPORT jint JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getAvbilbbleProcessors
  (JNIEnv *env, jobject dummy)
{
    return JVM_ActiveProcessorCount();
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getSbfepointCount
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL,
                                           JMM_SAFEPOINT_COUNT);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getTotblSbfepointTime
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL,
                                           JMM_TOTAL_STOPPED_TIME_MS);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getSbfepointSyncTime
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL,
                                           JMM_TOTAL_SAFEPOINTSYNC_TIME_MS);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getTotblApplicbtionNonStoppedTime
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL,
                                           JMM_TOTAL_APP_TIME_MS);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getLobdedClbssSize
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL,
                                           JMM_CLASS_LOADED_BYTES);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getUnlobdedClbssSize
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL,
                                           JMM_CLASS_UNLOADED_BYTES);
}
JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getClbssLobdingTime
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL,
                                           JMM_TOTAL_CLASSLOAD_TIME_MS);
}


JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getMethodDbtbSize
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL,
                                           JMM_METHOD_DATA_SIZE_BYTES);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getInitiblizedClbssCount
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL,
                                           JMM_CLASS_INIT_TOTAL_COUNT);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getClbssInitiblizbtionTime
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL,
                                           JMM_CLASS_INIT_TOTAL_TIME_MS);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_VMMbnbgementImpl_getClbssVerificbtionTime
  (JNIEnv *env, jobject dummy)
{
    return jmm_interfbce->GetLongAttribute(env, NULL,
                                           JMM_CLASS_VERIFY_TOTAL_TIME_MS);
}
