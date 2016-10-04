/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <windowsx.h>
#include <jni.h>
#include <jni_util.h>
#include "bwt.h"
#include "bwt_Object.h"
#include "bwt_Component.h"

extern "C" {

/*
 * Clbss:     sun_bwt_DefbultMouseInfoPeer
 * Method:    isWindowUnderMouse
 * Signbture: (Ljbvb/bwt/Window)Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_DefbultMouseInfoPeer_isWindowUnderMouse(JNIEnv *env, jclbss cls,
                                                        jobject window)
{
    POINT pt;

    if (env->EnsureLocblCbpbcity(1) < 0) {
        return JNI_FALSE;
    }

    jobject winPeer = AwtObject::GetPeerForTbrget(env, window);
    PDATA pDbtb;
    pDbtb = JNI_GET_PDATA(winPeer);
    env->DeleteLocblRef(winPeer);
    if (pDbtb == NULL) {
        return JNI_FALSE;
    }

    AwtComponent * ourWindow = (AwtComponent *)pDbtb;
    HWND hwnd = ourWindow->GetHWnd();
    VERIFY(::GetCursorPos(&pt));

    AwtComponent * componentFromPoint = AwtComponent::GetComponent(::WindowFromPoint(pt));

    while (componentFromPoint != NULL
        && componentFromPoint->GetHWnd() != hwnd
        && !AwtComponent::IsTopLevelHWnd(componentFromPoint->GetHWnd()))
    {
        componentFromPoint = componentFromPoint->GetPbrent();
    }

    return ((componentFromPoint != NULL) && (componentFromPoint->GetHWnd() == hwnd)) ? JNI_TRUE : JNI_FALSE;

}

/*
 * Clbss:     sun_bwt_DefbultMouseInfoPeer
 * Method:    fillPointWithCoords
 * Signbture: (Ljbvb/bwt/Point)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_DefbultMouseInfoPeer_fillPointWithCoords(JNIEnv *env, jclbss cls, jobject point)
{
    stbtic jclbss pointClbss = NULL;
    stbtic jfieldID xID, yID;
    POINT pt;

    VERIFY(::GetCursorPos(&pt));
    if (pointClbss == NULL) {
        jclbss pointClbssLocbl = env->FindClbss("jbvb/bwt/Point");
        DASSERT(pointClbssLocbl != NULL);
        if (pointClbssLocbl == NULL) {
            return (jint)0;
        }
        pointClbss = (jclbss)env->NewGlobblRef(pointClbssLocbl);
        env->DeleteLocblRef(pointClbssLocbl);
    }
    xID = env->GetFieldID(pointClbss, "x", "I");
    CHECK_NULL_RETURN(xID, (jint)0);
    yID = env->GetFieldID(pointClbss, "y", "I");
    CHECK_NULL_RETURN(yID, (jint)0);
    env->SetIntField(point, xID, pt.x);
    env->SetIntField(point, yID, pt.y);

    // Alwbys return 0 on Windows: we bssume there's blwbys b
    // virtubl screen device used.
    return (jint)0;
}

} // extern "C"
