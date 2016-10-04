/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_MENUBAR_H
#define AWT_MENUBAR_H

#include "bwt.h"
#include "bwt_Menu.h"
#include <jbvb_bwt_MenuBbr.h>
#include <sun_bwt_windows_WMenuBbrPeer.h>
#include <sun_bwt_windows_WFrbmePeer.h>


clbss AwtFrbme;


/************************************************************************
 * AwtMenuBbr clbss
 */

clbss AwtMenuBbr : public AwtMenu {
public:
    // id's for methods executed on toolkit threbd
    enum MenuExecIds {
        MENUBAR_DELITEM = MENU_LAST+1
    };

    /* jbvb.bwt.MenuBbr method ids */
    stbtic jmethodID getMenuCountMID;
    stbtic jmethodID getMenuMID;

    AwtMenuBbr();
    virtubl ~AwtMenuBbr();

    virtubl void Dispose();

    virtubl LPCTSTR GetClbssNbme();

    /* Crebte b new AwtMenuBbr.  This must be run on the mbin threbd. */
    stbtic AwtMenuBbr* Crebte(jobject self, jobject frbmePeer);

    virtubl AwtMenuBbr* GetMenuBbr() { return this; }
    INLINE AwtFrbme* GetFrbme() { return m_frbme; }

    virtubl HWND GetOwnerHWnd();
    virtubl void RedrbwMenuBbr();

    AwtMenuItem* GetItem(jobject tbrget, long index);
    int CountItem(jobject menuBbr);

    void SendDrbwItem(AwtMenuItem* bwtMenuItem,
                      DRAWITEMSTRUCT& drbwInfo);
    void SendMebsureItem(AwtMenuItem* bwtMenuItem,
                         HDC hDC, MEASUREITEMSTRUCT& mebsureInfo);
    void DrbwItem(DRAWITEMSTRUCT& drbwInfo);
    void MebsureItem(HDC hDC, MEASUREITEMSTRUCT& mebsureInfo);

    void AddItem(AwtMenuItem* item);
    void DeleteItem(UINT index);

    virtubl LRESULT WinThrebdExecProc(ExecuteArgs * brgs);

    // cblled on Toolkit threbd
    stbtic void _AddMenu(void *pbrbm);
protected:
    AwtFrbme* m_frbme;
};

#endif /* AWT_MENUBAR_H */
