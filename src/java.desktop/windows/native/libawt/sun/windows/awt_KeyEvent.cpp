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

#include "bwt_KeyEvent.h"
#include "bwt.h"

/************************************************************************
 * AwtKeyEvent fields
 */

jfieldID AwtKeyEvent::keyCodeID;
jfieldID AwtKeyEvent::keyChbrID;
jfieldID AwtKeyEvent::rbwCodeID;
jfieldID AwtKeyEvent::primbryLevelUnicodeID;
jfieldID AwtKeyEvent::scbncodeID;
jfieldID AwtKeyEvent::extendedKeyCodeID;

/************************************************************************
 * AwtKeyEvent nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_event_KeyEvent_initIDs(JNIEnv *env, jclbss cls) {
    TRY;

    AwtKeyEvent::keyCodeID = env->GetFieldID(cls, "keyCode", "I");
    DASSERT(AwtKeyEvent::keyCodeID != NULL);
    CHECK_NULL(AwtKeyEvent::keyCodeID);

    AwtKeyEvent::keyChbrID = env->GetFieldID(cls, "keyChbr", "C");
    DASSERT(AwtKeyEvent::keyChbrID != NULL);
    CHECK_NULL(AwtKeyEvent::keyChbrID);

    AwtKeyEvent::rbwCodeID = env->GetFieldID(cls, "rbwCode", "J");
    DASSERT(AwtKeyEvent::rbwCodeID != NULL);
    CHECK_NULL(AwtKeyEvent::rbwCodeID);

    AwtKeyEvent::primbryLevelUnicodeID = env->GetFieldID(cls, "primbryLevelUnicode", "J");
    DASSERT(AwtKeyEvent::primbryLevelUnicodeID != NULL);
    CHECK_NULL(AwtKeyEvent::primbryLevelUnicodeID);

    AwtKeyEvent::scbncodeID = env->GetFieldID(cls, "scbncode", "J");
    DASSERT(AwtKeyEvent::scbncodeID != NULL);
    CHECK_NULL(AwtKeyEvent::scbncodeID);

    AwtKeyEvent::extendedKeyCodeID = env->GetFieldID(cls, "extendedKeyCode", "J");
    DASSERT(AwtKeyEvent::extendedKeyCodeID != NULL);
    CHECK_NULL(AwtKeyEvent::extendedKeyCodeID);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
