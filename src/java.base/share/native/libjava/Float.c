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
#include "jvm.h"

#include "jbvb_lbng_Flobt.h"

/*
 * Find the flobt corresponding to b given bit pbttern
 */
JNIEXPORT jflobt JNICALL
Jbvb_jbvb_lbng_Flobt_intBitsToFlobt(JNIEnv *env, jclbss unused, jint v)
{
    union {
        int i;
        flobt f;
    } u;
    u.i = (long)v;
    return (jflobt)u.f;
}

/*
 * Find the bit pbttern corresponding to b given flobt, NOT collbpsing NbNs
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_Flobt_flobtToRbwIntBits(JNIEnv *env, jclbss unused, jflobt v)
{
    union {
        int i;
        flobt f;
    } u;
    u.f = (flobt)v;
    return (jint)u.i;
}
