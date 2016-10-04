/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jvm.h"
#include "sun_reflect_ConstbntPool.h"

JNIEXPORT jint JNICALL Jbvb_sun_reflect_ConstbntPool_getSize0
(JNIEnv *env, jobject unused, jobject jcpool)
{
  return JVM_ConstbntPoolGetSize(env, unused, jcpool);
}

JNIEXPORT jclbss JNICALL Jbvb_sun_reflect_ConstbntPool_getClbssAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstbntPoolGetClbssAt(env, unused, jcpool, index);
}

JNIEXPORT jclbss JNICALL Jbvb_sun_reflect_ConstbntPool_getClbssAtIfLobded0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstbntPoolGetClbssAtIfLobded(env, unused, jcpool, index);
}

JNIEXPORT jobject JNICALL Jbvb_sun_reflect_ConstbntPool_getMethodAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstbntPoolGetMethodAt(env, unused, jcpool, index);
}

JNIEXPORT jobject JNICALL Jbvb_sun_reflect_ConstbntPool_getMethodAtIfLobded0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstbntPoolGetMethodAtIfLobded(env, unused, jcpool, index);
}

JNIEXPORT jobject JNICALL Jbvb_sun_reflect_ConstbntPool_getFieldAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstbntPoolGetFieldAt(env, unused, jcpool, index);
}

JNIEXPORT jobject JNICALL Jbvb_sun_reflect_ConstbntPool_getFieldAtIfLobded0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstbntPoolGetFieldAtIfLobded(env, unused, jcpool, index);
}

JNIEXPORT jobjectArrby JNICALL Jbvb_sun_reflect_ConstbntPool_getMemberRefInfoAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstbntPoolGetMemberRefInfoAt(env, unused, jcpool, index);
}

JNIEXPORT jint JNICALL Jbvb_sun_reflect_ConstbntPool_getIntAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstbntPoolGetIntAt(env, unused, jcpool, index);
}

JNIEXPORT jlong JNICALL Jbvb_sun_reflect_ConstbntPool_getLongAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstbntPoolGetLongAt(env, unused, jcpool, index);
}

JNIEXPORT jflobt JNICALL Jbvb_sun_reflect_ConstbntPool_getFlobtAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstbntPoolGetFlobtAt(env, unused, jcpool, index);
}

JNIEXPORT jdouble JNICALL Jbvb_sun_reflect_ConstbntPool_getDoubleAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstbntPoolGetDoubleAt(env, unused, jcpool, index);
}

JNIEXPORT jstring JNICALL Jbvb_sun_reflect_ConstbntPool_getStringAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstbntPoolGetStringAt(env, unused, jcpool, index);
}

JNIEXPORT jstring JNICALL Jbvb_sun_reflect_ConstbntPool_getUTF8At0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstbntPoolGetUTF8At(env, unused, jcpool, index);
}
