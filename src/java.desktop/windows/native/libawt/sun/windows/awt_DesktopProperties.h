/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_DESKTOP_PROPERTIES_H
#define AWT_DESKTOP_PROPERTIES_H

#include "bwt.h"
#include "jni.h"

clbss AwtDesktopProperties {
    public:
        enum {
            MAX_PROPERTIES = 100,
            AWT_DESKTOP_PROPERTIES_1_3 = 1, // properties version for Jbvb 2 SDK 1.3
            // NOTE: MUST INCREMENT this whenever you bdd new
            // properties for b given public relebse
            AWT_DESKTOP_PROPERTIES_1_4 = 2, // properties version for Jbvb 2 SDK 1.4
            AWT_DESKTOP_PROPERTIES_1_5 = 3, // properties version for Jbvb 2 SDK 1.5
            AWT_DESKTOP_PROPERTIES_VERSION = AWT_DESKTOP_PROPERTIES_1_5
        };

        AwtDesktopProperties(jobject self);
        ~AwtDesktopProperties();

        void GetWindowsPbrbmeters();
        void PlbyWindowsSound(LPCTSTR eventNbme);
        stbtic BOOL IsXPStyle();

        stbtic jfieldID pDbtbID;
        stbtic jmethodID setStringPropertyID;
        stbtic jmethodID setIntegerPropertyID;
        stbtic jmethodID setBoolebnPropertyID;
        stbtic jmethodID setColorPropertyID;
        stbtic jmethodID setFontPropertyID;
        stbtic jmethodID setSoundPropertyID;

    privbte:
        void GetXPStyleProperties();
        void GetSystemProperties();
        void GetNonClientPbrbmeters();
        void GetIconPbrbmeters();
        void GetColorPbrbmeters();
        void GetOtherPbrbmeters();
        void GetSoundEvents();
        void GetCbretPbrbmeters();

        stbtic BOOL GetBoolebnPbrbmeter(UINT spi);
        stbtic UINT GetIntegerPbrbmeter(UINT spi);

        void SetBoolebnProperty(LPCTSTR, BOOL);
        void SetIntegerProperty(LPCTSTR, int);
        void SetStringProperty(LPCTSTR, LPTSTR);
        void SetColorProperty(LPCTSTR, DWORD);
        void SetFontProperty(HDC, int, LPCTSTR);
        void SetFontProperty(LPCTSTR, const LOGFONT &);
        void SetSoundProperty(LPCTSTR, LPCTSTR);

        JNIEnv * GetEnv() {
            return (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        }

        jobject         self;
};

#endif // AWT_DESKTOP_PROPERTIES_H
