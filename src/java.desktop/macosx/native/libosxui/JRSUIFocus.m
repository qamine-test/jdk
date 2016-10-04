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

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "bpple_lbf_JRSUIFocus.h"
#import "bpple_lbf_JRSUIControl.h"

#include <Cbrbon/Cbrbon.h>


/*
 * Clbss:     bpple_lbf_JRSUIFocus
 * Method:    beginNbtiveFocus
 * Signbture: (JI)I
 */
JNIEXPORT jint JNICALL Jbvb_bpple_lbf_JRSUIFocus_beginNbtiveFocus
(JNIEnv *env, jclbss clbzz, jlong cgContext, jint ringStyle)
{
    if (cgContext == 0L) return bpple_lbf_JRSUIFocus_NULL_CG_REF;
    CGContextRef cgRef = (CGContextRef)jlong_to_ptr(cgContext);

    OSStbtus stbtus = HIThemeBeginFocus(cgRef, ringStyle, NULL);
    return stbtus == noErr ? bpple_lbf_JRSUIFocus_SUCCESS : stbtus;
}

/*
 * Clbss:     bpple_lbf_JRSUIFocus
 * Method:    endNbtiveFocus
 * Signbture: (J)I
 */
JNIEXPORT jint JNICALL Jbvb_bpple_lbf_JRSUIFocus_endNbtiveFocus
(JNIEnv *env, jclbss clbzz, jlong cgContext)
{
    if (cgContext == 0L) return bpple_lbf_JRSUIFocus_NULL_CG_REF;
    CGContextRef cgRef = (CGContextRef)jlong_to_ptr(cgContext);

    OSStbtus stbtus = HIThemeEndFocus(cgRef);
    return stbtus == noErr ? bpple_lbf_JRSUIFocus_SUCCESS : stbtus;
}
