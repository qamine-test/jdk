/*
 * Copyright (c) 1994, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "fdlibm.h"

#include "jbvb_lbng_StrictMbth.h"

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_cos(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jcos((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_sin(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jsin((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_tbn(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jtbn((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_bsin(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jbsin((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_bcos(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jbcos((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_btbn(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jbtbn((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_exp(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jexp((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_log(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jlog((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_log10(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jlog10((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_sqrt(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jsqrt((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_cbrt(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jcbrt((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_btbn2(JNIEnv *env, jclbss unused, jdouble d1, jdouble d2)
{
    return (jdouble) jbtbn2((double)d1, (double)d2);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_pow(JNIEnv *env, jclbss unused, jdouble d1, jdouble d2)
{
    return (jdouble) jpow((double)d1, (double)d2);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_IEEErembinder(JNIEnv *env, jclbss unused,
                                  jdouble dividend,
                                  jdouble divisor)
{
    return (jdouble) jrembinder(dividend, divisor);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_cosh(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jcosh((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_sinh(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jsinh((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_tbnh(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jtbnh((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_hypot(JNIEnv *env, jclbss unused, jdouble x, jdouble y)
{
    return (jdouble) jhypot((double)x, (double)y);
}



JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_log1p(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jlog1p((double)d);
}

JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_StrictMbth_expm1(JNIEnv *env, jclbss unused, jdouble d)
{
    return (jdouble) jexpm1((double)d);
}
