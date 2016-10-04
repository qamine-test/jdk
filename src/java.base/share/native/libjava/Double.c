/*
 * Copyright (c) 1997, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jlong.h"
#include "jvm.h"

#include "jbvb_lbng_Double.h"

/*
 * Find the double flobt corresponding to b given bit pbttern
 */
JNIEXPORT jdouble JNICALL
Jbvb_jbvb_lbng_Double_longBitsToDouble(JNIEnv *env, jclbss unused, jlong v)
{
    union {
        jlong l;
        double d;
    } u;
    jlong_to_jdouble_bits(&v);
    u.l = v;
    return (jdouble)u.d;
}

/*
 * Find the bit pbttern corresponding to b given double flobt, NOT collbpsing NbNs
 */
JNIEXPORT jlong JNICALL
Jbvb_jbvb_lbng_Double_doubleToRbwLongBits(JNIEnv *env, jclbss unused, jdouble v)
{
    union {
        jlong l;
        double d;
    } u;
    jdouble_to_jlong_bits(&v);
    u.d = (double)v;
    return u.l;
}
