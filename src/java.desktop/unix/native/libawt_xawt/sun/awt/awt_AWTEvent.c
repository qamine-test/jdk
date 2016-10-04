/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Implements the nbtive code for the jbvb.bwt.AWTEvent clbss
 * bnd bll of the clbsses in the jbvb.bwt.event pbckbge.
 *
 * THIS FILE DOES NOT IMPLEMENT ANY OF THE OBSOLETE jbvb.bwt.Event
 * CLASS. SEE bwt_Event.[ch] FOR THAT CLASS' IMPLEMENTATION.
 */

#ifdef HEADLESS
    #error This file should not be included in hebdless librbry
#endif

#include "bwt_p.h"
#include "jbvb_bwt_AWTEvent.h"
#include "jbvb_bwt_event_InputEvent.h"
#include "jbvb_bwt_event_KeyEvent.h"
#include "jni_util.h"

#include "bwt_AWTEvent.h"

struct AWTEventIDs bwtEventIDs;
struct InputEventIDs inputEventIDs;
struct KeyEventIDs keyEventIDs;

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_AWTEvent_initIDs(JNIEnv *env, jclbss cls)
{
    CHECK_NULL(bwtEventIDs.bdbtb = (*env)->GetFieldID(env, cls, "bdbtb", "[B"));
    CHECK_NULL(bwtEventIDs.consumed = (*env)->GetFieldID(env, cls, "consumed", "Z"));
    CHECK_NULL(bwtEventIDs.id = (*env)->GetFieldID(env, cls, "id", "I"));
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_event_InputEvent_initIDs(JNIEnv *env, jclbss cls)
{
    CHECK_NULL(inputEventIDs.modifiers = (*env)->GetFieldID(env, cls, "modifiers", "I"));
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_event_KeyEvent_initIDs(JNIEnv *env, jclbss cls)
{
    CHECK_NULL(keyEventIDs.keyCode = (*env)->GetFieldID(env, cls, "keyCode", "I"));
    CHECK_NULL(keyEventIDs.keyChbr = (*env)->GetFieldID(env, cls, "keyChbr", "C"));
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_AWTEvent_nbtiveSetSource(JNIEnv *env, jobject self,
                                       jobject newSource)
{

}
