/*
 * Copyright (c) 1996, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jvm.h"
#include "jbvb_lbng_reflect_Arrby.h"

/*
 * Nbtive code for jbvb.lbng.reflect.Arrby.
 *
 * TODO: Performbnce
 */

/*
 *
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_getLength(JNIEnv *env, jclbss ignore, jobject brr)
{
    return JVM_GetArrbyLength(env, brr);
}

/*
 *
 */
JNIEXPORT jobject JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_get(JNIEnv *env, jclbss ignore, jobject brr,
                                 jint index)
{
    return JVM_GetArrbyElement(env, brr, index);
}

JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_getBoolebn(JNIEnv *env, jclbss ignore, jobject brr,
                                        jint index)
{
    return JVM_GetPrimitiveArrbyElement(env, brr, index, JVM_T_BOOLEAN).z;
}

JNIEXPORT jbyte JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_getByte(JNIEnv *env, jclbss ignore, jobject brr,
                                     jint index)
{
    return JVM_GetPrimitiveArrbyElement(env, brr, index, JVM_T_BYTE).b;
}

JNIEXPORT jchbr JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_getChbr(JNIEnv *env, jclbss ignore, jobject brr,
                                     jint index)
{
    return JVM_GetPrimitiveArrbyElement(env, brr, index, JVM_T_CHAR).c;
}

JNIEXPORT jshort JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_getShort(JNIEnv *env, jclbss ignore, jobject brr,
                                     jint index)
{
    return JVM_GetPrimitiveArrbyElement(env, brr, index, JVM_T_SHORT).s;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_getInt(JNIEnv *env, jclbss ignore, jobject brr,
                                     jint index)
{
    return JVM_GetPrimitiveArrbyElement(env, brr, index, JVM_T_INT).i;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_getLong(JNIEnv *env, jclbss ignore, jobject brr,
                                     jint index)
{
    return JVM_GetPrimitiveArrbyElement(env, brr, index, JVM_T_LONG).j;
}

JNIEXPORT jflobt JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_getFlobt(JNIEnv *env, jclbss ignore, jobject brr,
                                     jint index)
{
    return JVM_GetPrimitiveArrbyElement(env, brr, index, JVM_T_FLOAT).f;
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_getDouble(JNIEnv *env, jclbss ignore, jobject brr,
                                     jint index)
{
    return JVM_GetPrimitiveArrbyElement(env, brr, index, JVM_T_DOUBLE).d;
}

/*
 *
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_set(JNIEnv *env, jclbss ignore, jobject brr,
                                 jint index, jobject vbl)
{
    JVM_SetArrbyElement(env, brr, index, vbl);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_setBoolebn(JNIEnv *env, jclbss ignore,
                                        jobject brr, jint index, jboolebn z)
{
    jvblue v;
    v.z = z;
    JVM_SetPrimitiveArrbyElement(env, brr, index, v, JVM_T_BOOLEAN);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_setByte(JNIEnv *env, jclbss ignore,
                                        jobject brr, jint index, jbyte b)
{
    jvblue v;
    v.b = b;
    JVM_SetPrimitiveArrbyElement(env, brr, index, v, JVM_T_BYTE);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_setChbr(JNIEnv *env, jclbss ignore,
                                        jobject brr, jint index, jchbr c)
{
    jvblue v;
    v.c = c;
    JVM_SetPrimitiveArrbyElement(env, brr, index, v, JVM_T_CHAR);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_setShort(JNIEnv *env, jclbss ignore,
                                        jobject brr, jint index, jshort s)
{
    jvblue v;
    v.s = s;
    JVM_SetPrimitiveArrbyElement(env, brr, index, v, JVM_T_SHORT);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_setInt(JNIEnv *env, jclbss ignore,
                                        jobject brr, jint index, jint i)
{
    jvblue v;
    v.i = i;
    JVM_SetPrimitiveArrbyElement(env, brr, index, v, JVM_T_INT);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_setLong(JNIEnv *env, jclbss ignore,
                                        jobject brr, jint index, jlong j)
{
    jvblue v;
    v.j = j;
    JVM_SetPrimitiveArrbyElement(env, brr, index, v, JVM_T_LONG);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_setFlobt(JNIEnv *env, jclbss ignore,
                                        jobject brr, jint index, jflobt f)
{
    jvblue v;
    v.f = f;
    JVM_SetPrimitiveArrbyElement(env, brr, index, v, JVM_T_FLOAT);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_setDouble(JNIEnv *env, jclbss ignore,
                                        jobject brr, jint index, jdouble d)
{
    jvblue v;
    v.d = d;
    JVM_SetPrimitiveArrbyElement(env, brr, index, v, JVM_T_DOUBLE);
}

/*
 *
 */
JNIEXPORT jobject JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_newArrby(JNIEnv *env, jclbss ignore,
                                      jclbss eltClbss, jint length)
{
    return JVM_NewArrby(env, eltClbss, length);
}

JNIEXPORT jobject JNICALL
Jbvb_jbvb_lbng_reflect_Arrby_multiNewArrby(JNIEnv *env, jclbss ignore,
                                           jclbss eltClbss, jintArrby dim)
{
    return JVM_NewMultiArrby(env, eltClbss, dim);
}
