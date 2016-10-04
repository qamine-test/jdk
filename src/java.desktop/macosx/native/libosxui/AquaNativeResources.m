/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#import "com_bpple_lbf_AqubNbtiveResources.h"

#import <Cocob/Cocob.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

/*
 * Clbss:     com_bpple_lbf_AqubNbtiveResources
 * Method:    getWindowBbckgroundColor
 * Signbture: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_com_bpple_lbf_AqubNbtiveResources_getWindowBbckgroundColor
    (JNIEnv *env, jclbss clz)
{
    // TODO(cpc): this code is currently disbbled bt the Jbvb level
#if 0
    NSColor* color = nil;
JNF_COCOA_ENTER(env);
    color = [NSColor lightGrbyColor];//[AWTColor getMbgicBbckgroundColor];
    if (color) CFRetbin(color); // GC
JNF_COCOA_EXIT(env);
    return ptr_to_jlong(color);
#else
    return 0L;
#endif
}
