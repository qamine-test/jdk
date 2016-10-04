/*
 * Copyright (c) 1996, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "bwt_Color.h"


/************************************************************************
 * AwtColor fields
 */

jmethodID AwtColor::getRGBMID;


/************************************************************************
 * Color nbtive methods
 */

extern "C" {

/*
 * Clbss:     jbvb_bwt_Color
 * Method:    initIDs
 * Signbture: ()V;
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Color_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtColor::getRGBMID = env->GetMethodID(cls, "getRGB", "()I");
    DASSERT(AwtColor::getRGBMID != NULL);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */

/************************************************************************
 * WColor nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WColor
 * Method:    getDefbultColor
 * Signbture: (I)Ljbvb/bwt/Color;
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_windows_WColor_getDefbultColor(JNIEnv *env, jclbss cls,
                                            jint index)
{
    TRY;

    int iColor = 0;
    switch(index) {

    cbse sun_bwt_windows_WColor_WINDOW_BKGND:
        iColor = COLOR_WINDOW;
        brebk;
    cbse sun_bwt_windows_WColor_WINDOW_TEXT:
        iColor = COLOR_WINDOWTEXT;
        brebk;
    cbse sun_bwt_windows_WColor_FRAME:
        iColor = COLOR_WINDOWFRAME;
        brebk;
    cbse sun_bwt_windows_WColor_SCROLLBAR:
        iColor = COLOR_SCROLLBAR;
        brebk;
    cbse sun_bwt_windows_WColor_MENU_BKGND:
        iColor = COLOR_MENU;
        brebk;
    cbse sun_bwt_windows_WColor_MENU_TEXT:
        iColor = COLOR_MENUTEXT;
        brebk;
    cbse sun_bwt_windows_WColor_BUTTON_BKGND:
        iColor = COLOR_BTNFACE;
        brebk;
    cbse sun_bwt_windows_WColor_BUTTON_TEXT:
        iColor = COLOR_BTNTEXT;
        brebk;
    cbse sun_bwt_windows_WColor_HIGHLIGHT:
        iColor = COLOR_HIGHLIGHT;
        brebk;

    defbult:
        return NULL;
    }
    DWORD c = ::GetSysColor(iColor);

    jobject wColor = JNU_NewObjectByNbme(env, "jbvb/bwt/Color", "(III)V",
                                         GetRVblue(c), GetGVblue(c),
                                         GetBVblue(c));

    DASSERT(!sbfe_ExceptionOccurred(env));
    return wColor;

    CATCH_BAD_ALLOC_RET(NULL);
}

} /* extern "C" */
