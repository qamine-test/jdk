/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_ROBOT_H
#define AWT_ROBOT_H

#include "bwt_Toolkit.h"
#include "bwt_Object.h"
#include "sun_bwt_windows_WRobotPeer.h"
#include "jlong.h"

clbss AwtRobot : public AwtObject
{
    public:
        AwtRobot( jobject peer );
        virtubl ~AwtRobot();

        void MouseMove( jint x, jint y);
        void MousePress( jint buttonMbsk );
        void MouseRelebse( jint buttonMbsk );

        void MouseWheel(jint wheelAmt);
        jint getNumberOfButtons();

        void GetRGBPixels(jint x, jint y, jint width, jint height, jintArrby pixelArrby);

        void KeyPress( jint key );
        void KeyRelebse( jint key );
        stbtic AwtRobot * GetRobot( jobject self );

    privbte:
        void DoKeyEvent( jint jkey, DWORD dwFlbgs );
        stbtic jint WinToJbvbPixel(USHORT r, USHORT g, USHORT b);
};

#endif // AWT_ROBOT_H
