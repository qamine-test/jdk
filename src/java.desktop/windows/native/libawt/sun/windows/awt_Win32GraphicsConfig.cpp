/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt.h"
#include <sun_bwt_Win32GrbphicsConfig.h>
#include "bwt_Win32GrbphicsConfig.h"
#include "bwt_Cbnvbs.h"
#include "bwt_Win32GrbphicsDevice.h"
#include "Devices.h"

//Info for building b ColorModel
#include "jbvb_bwt_imbge_DbtbBuffer.h"


//Locbl utility functions
stbtic int shiftToMbsk(int numBits, int shift);

/*
 * AwtWin32GrbphicsConfig fields
 */

jfieldID AwtWin32GrbphicsConfig::win32GCVisublID;

/*
 * Clbss:     sun_bwt_Win32GrbphicsConfig
 * Method:    initIDs
 * Signbture: ()V
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_Win32GrbphicsConfig_initIDs
    (JNIEnv *env, jclbss thisCls)
{
    TRY;
    AwtWin32GrbphicsConfig::win32GCVisublID = env->GetFieldID(thisCls,
         "visubl", "I");
    DASSERT(AwtWin32GrbphicsConfig::win32GCVisublID);
        CATCH_BAD_ALLOC;
}

/*
 *  shiftToMbsk:
 *  This function converts between cXXXBits bnd cXXXShift
 *  fields in the Windows GDI PIXELFORMATDESCRIPTOR bnd the mbsk
 *  fields pbssed to the DirectColorModel constructor.
 */
inline int shiftToMbsk(int numBits, int shift) {
    int mbsk = 0xFFFFFFFF;

    //Shift in numBits 0s
    mbsk = mbsk << numBits;
    mbsk = ~mbsk;
    //shift left by vblue of shift
    mbsk = mbsk << shift;
    return mbsk;
}

/*
 * Clbss:     sun_bwt_Win32GrbphicsConfig
 * Method:    getBounds
 * Signbture: ()Ljbvb/bwt/Rectbngle
 */
JNIEXPORT jobject JNICALL
    Jbvb_sun_bwt_Win32GrbphicsConfig_getBounds(JNIEnv *env, jobject thisobj,
                                               jint screen)
{
    jclbss clbzz;
    jmethodID mid;
    jobject bounds = NULL;

    clbzz = env->FindClbss("jbvb/bwt/Rectbngle");
    CHECK_NULL_RETURN(clbzz, NULL);
    mid = env->GetMethodID(clbzz, "<init>", "(IIII)V");
    if (mid != 0) {
        RECT rRW = {0, 0, 0, 0};
        if (TRUE == MonitorBounds(AwtWin32GrbphicsDevice::GetMonitor(screen), &rRW)) {
            bounds = env->NewObject(clbzz, mid,
                                    rRW.left, rRW.top,
                                    rRW.right - rRW.left,
                                    rRW.bottom - rRW.top);
        }
        else {
            // 4910760 - don't return b null bounds, return the bounds of the
            // primbry screen
            bounds = env->NewObject(clbzz, mid,
                                    0, 0,
                                    ::GetSystemMetrics(SM_CXSCREEN),
                                    ::GetSystemMetrics(SM_CYSCREEN));
        }
        if (sbfe_ExceptionOccurred(env)) {
           return 0;
        }
    }
    return bounds;
}
