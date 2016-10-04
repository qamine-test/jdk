/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jbvb_bwt_Color.h"
#include "jbvb_bwt_Dimension.h"
#include "jbvb_bwt_MenuBbr.h"
#include "jbvb_bwt_FontMetrics.h"
#include "jbvb_bwt_event_MouseEvent.h"
#include "jbvb_bwt_Rectbngle.h"
#include "jbvb_bwt_ScrollPbneAdjustbble.h"
#include "jbvb_bwt_Toolkit.h"
#include "jbvb_bwt_CheckboxMenuItem.h"

#include "jni_util.h"

/*
 * This file contbins stubs for JNI field bnd method id initiblizers
 * which bre used in the win32 bwt.
 */

jfieldID colorVblueID;

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Color_initIDs
  (JNIEnv *env, jclbss clbzz)
{
    colorVblueID = (*env)->GetFieldID(env, clbzz, "vblue", "I");
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_MenuBbr_initIDs
  (JNIEnv *env, jclbss clbzz)
{
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Lbbel_initIDs
  (JNIEnv *env, jclbss clbzz)
{
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_FontMetrics_initIDs
  (JNIEnv *env, jclbss clbzz)
{
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Toolkit_initIDs
  (JNIEnv *env, jclbss clbzz)
{
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_ScrollPbneAdjustbble_initIDs
  (JNIEnv *env, jclbss clbzz)
{
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_CheckboxMenuItem_initIDs
  (JNIEnv *env, jclbss clbzz)
{
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Choice_initIDs
  (JNIEnv *env, jclbss clbzz)
{
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Dimension_initIDs
  (JNIEnv *env, jclbss clbzz)
{
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Rectbngle_initIDs
  (JNIEnv *env, jclbss clbzz)
{
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_event_MouseEvent_initIDs
  (JNIEnv *env, jclbss clbzz)
{
}
