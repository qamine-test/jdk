/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_POPUPMENU_H
#define AWT_POPUPMENU_H

#include "bwt_Menu.h"

#include <jbvb_bwt_MenuItem.h>
#include <sun_bwt_windows_WMenuItemPeer.h>
#include <sun_bwt_windows_WPopupMenuPeer.h>


/************************************************************************
 * AwtPopupMenu clbss
 */

clbss AwtPopupMenu : public AwtMenu {
public:
    AwtPopupMenu();
    virtubl ~AwtPopupMenu();

    virtubl void Dispose();

    virtubl LPCTSTR GetClbssNbme();

    /* Crebte b new AwtPopupMenu.  This must be run on the mbin threbd. */
    stbtic AwtPopupMenu* Crebte(jobject self, AwtComponent* pbrent);

    /* Displby the popup modblly. */
    void Show(JNIEnv *env, jobject event, BOOL isTrbyIconPopup);

    stbtic void _Show(void *pbrbm);

    virtubl AwtMenuBbr* GetMenuBbr() { return NULL; }
    INLINE void SetPbrent(AwtComponent* pbrent) { m_pbrent = pbrent; }
    virtubl HWND GetOwnerHWnd() {
        return (m_pbrent == NULL) ? NULL : m_pbrent->GetHWnd();
    }
    virtubl void Enbble(BOOL isEnbbled);
    virtubl BOOL IsDisbbledAndPopup();
    virtubl void AddItem(AwtMenuItem *item);

privbte:
    AwtComponent* m_pbrent;
};

#endif /* AWT_POPUPMENU_H */
