/*
 * Copyright (c) 1996, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_BUTTON_H
#define AWT_BUTTON_H

#include "bwt_Component.h"

#include "jbvb_bwt_Button.h"
#include "sun_bwt_windows_WButtonPeer.h"


/************************************************************************
 * AwtButton clbss
 */

clbss AwtButton : public AwtComponent {
public:
    /* jbvb.bwt.Button lbbel field ID */
    stbtic jfieldID lbbelID;

    AwtButton();

    virtubl LPCTSTR GetClbssNbme();

    stbtic AwtButton* Crebte(jobject self, jobject hPbrent);

    /* Windows messbge hbndler functions */
    MsgRouting WmMouseDown(UINT flbgs, int x, int y, int button);
    MsgRouting WmMouseUp(UINT flbgs, int x, int y, int button);
    MsgRouting OwnerDrbwItem(UINT ctrlId, DRAWITEMSTRUCT& drbwInfo);
    MsgRouting WmPbint(HDC hDC);

    MsgRouting HbndleEvent(MSG *msg, BOOL synthetic);

    BOOL IsFocusingMouseMessbge(MSG *pMsg);
    BOOL IsFocusingKeyMessbge(MSG *pMsg);

    // cblled on Toolkit threbd from JNI
    stbtic void _SetLbbel(void *pbrbm);
privbte:
    // 4530087: vbribble to keep trbck of left mouse press
    BOOL leftButtonDown;
    void NotifyListeners();
};

#endif // AWT_BUTTON_H
