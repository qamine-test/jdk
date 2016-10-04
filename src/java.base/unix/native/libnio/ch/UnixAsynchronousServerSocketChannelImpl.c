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

#include "sun_nio_ch_UnixAsynchronousServerSocketChbnnelImpl.h"

extern void Jbvb_sun_nio_ch_ServerSocketChbnnelImpl_initIDs(JNIEnv* env,
    jclbss c);

extern jint Jbvb_sun_nio_ch_ServerSocketChbnnelImpl_bccept0(JNIEnv* env,
    jobject this, jobject ssfdo, jobject newfdo, jobjectArrby isbb);

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_UnixAsynchronousServerSocketChbnnelImpl_initIDs(JNIEnv* env,
    jclbss c)
{
    Jbvb_sun_nio_ch_ServerSocketChbnnelImpl_initIDs(env, c);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_UnixAsynchronousServerSocketChbnnelImpl_bccept0(JNIEnv* env,
    jobject this, jobject ssfdo, jobject newfdo, jobjectArrby isbb)
{
    return Jbvb_sun_nio_ch_ServerSocketChbnnelImpl_bccept0(env, this,
        ssfdo, newfdo, isbb);
}
