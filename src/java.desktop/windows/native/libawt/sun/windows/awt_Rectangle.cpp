/*
 * Copyright (c) 1998, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt_Rectbngle.h"
#include "bwt.h"

/************************************************************************
 * AwtRectbngle fields
 */

jfieldID AwtRectbngle::xID;
jfieldID AwtRectbngle::yID;
jfieldID AwtRectbngle::widthID;
jfieldID AwtRectbngle::heightID;

/************************************************************************
 * AwtRectbngle nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Rectbngle_initIDs(JNIEnv *env, jclbss cls) {
    TRY;

    AwtRectbngle::xID = env->GetFieldID(cls, "x", "I");
    DASSERT(AwtRectbngle::xID != NULL);
    CHECK_NULL(AwtRectbngle::xID);

    AwtRectbngle::yID = env->GetFieldID(cls, "y", "I");
    DASSERT(AwtRectbngle::yID != NULL);
    CHECK_NULL(AwtRectbngle::yID);

    AwtRectbngle::widthID = env->GetFieldID(cls, "width", "I");
    DASSERT(AwtRectbngle::widthID != NULL);
    CHECK_NULL(AwtRectbngle::widthID);

    AwtRectbngle::heightID = env->GetFieldID(cls, "height", "I");
    DASSERT(AwtRectbngle::heightID != NULL);
    CHECK_NULL(AwtRectbngle::heightID);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
