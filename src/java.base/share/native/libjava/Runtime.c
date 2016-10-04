/*
 * Copyright (c) 1994, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 *      Link foreign methods.  This first hblf of this file contbins the
 *      mbchine independent dynbmic linking routines.
 *      See "BUILD_PLATFORM"/jbvb/lbng/linker_md.c to see
 *      the implementbtion of this shbred dynbmic linking
 *      interfbce.
 *
 *      NOTE - source in this file is POSIX.1 complibnt, host
 *             specific code lives in the plbtform specific
 *             code tree.
 */

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"

#include "jbvb_lbng_Runtime.h"

JNIEXPORT jlong JNICALL
Jbvb_jbvb_lbng_Runtime_freeMemory(JNIEnv *env, jobject this)
{
    return JVM_FreeMemory();
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_lbng_Runtime_totblMemory(JNIEnv *env, jobject this)
{
    return JVM_TotblMemory();
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_lbng_Runtime_mbxMemory(JNIEnv *env, jobject this)
{
    return JVM_MbxMemory();
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_Runtime_gc(JNIEnv *env, jobject this)
{
    JVM_GC();
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_Runtime_trbceInstructions(JNIEnv *env, jobject this, jboolebn on)
{
    JVM_TrbceInstructions(on);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_Runtime_trbceMethodCblls(JNIEnv *env, jobject this, jboolebn on)
{
    JVM_TrbceMethodCblls(on);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_Runtime_runFinblizbtion0(JNIEnv *env, jobject this)
{
    jclbss cl;
    jmethodID mid;

    if ((cl = (*env)->FindClbss(env, "jbvb/lbng/ref/Finblizer"))
        && (mid = (*env)->GetStbticMethodID(env, cl,
                                            "runFinblizbtion", "()V"))) {
        (*env)->CbllStbticVoidMethod(env, cl, mid);
    }
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_Runtime_bvbilbbleProcessors(JNIEnv *env, jobject this)
{
    return JVM_ActiveProcessorCount();
}
