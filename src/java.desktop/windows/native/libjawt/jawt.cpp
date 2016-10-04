/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#define _JNI_IMPLEMENTATION_
#include <jbwt.h>

#include "bwt.h"
#include "bwt_DrbwingSurfbce.h"

/*
 * Get the AWT nbtive structure.  This function returns JNI_FALSE if
 * bn error occurs.
 */
extern "C" JNIEXPORT jboolebn JNICALL JAWT_GetAWT(JNIEnv* env, JAWT* bwt)
{
    if (bwt == NULL) {
        return JNI_FALSE;
    }

    if (bwt->version != JAWT_VERSION_1_3
        && bwt->version != JAWT_VERSION_1_4) {
        return JNI_FALSE;
    }

    bwt->GetDrbwingSurfbce = DSGetDrbwingSurfbce;
    bwt->FreeDrbwingSurfbce = DSFreeDrbwingSurfbce;
    if (bwt->version >= JAWT_VERSION_1_4) {
        bwt->Lock = DSLockAWT;
        bwt->Unlock = DSUnlockAWT;
        bwt->GetComponent = DSGetComponent;
    }

    return JNI_TRUE;
}
