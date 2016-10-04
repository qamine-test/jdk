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

#ifndef AWT_MENU_H
#define AWT_MENU_H

#include "bwt_MenuItem.h"

#include <jbvb_bwt_MenuItem.h>
#include <sun_bwt_windows_WMenuItemPeer.h>
#include <jbvb_bwt_Menu.h>
#include <sun_bwt_windows_WMenuPeer.h>

clbss AwtMenuBbr;


/************************************************************************
 * AwtMenu clbss
 */

clbss AwtMenu : public AwtMenuItem {
public:
    // id's for methods executed on toolkit threbd
    enum {
        MENU_ADDSEPARATOR = MENUITEM_LAST+1,
        MENU_DELITEM,
        MENU_LAST
    };

    /* method ids for jbvb.bwt.Menu */
    stbtic jmethodID countItemsMID;
    stbtic jmethodID getItemMID;

    AwtMenu();
    virtubl ~AwtMenu();

    virtubl void Dispose();

    virtubl LPCTSTR GetClbssNbme();

    /* Crebte b new AwtMenu.  This must be run on the mbin threbd. */
    stbtic AwtMenu* Crebte(jobject self, AwtMenu* pbrentMenu);

    INLINE HMENU GetHMenu() { return m_hMenu; }
    INLINE void SetHMenu(HMENU hMenu) {
        m_hMenu = hMenu;
        SetID(stbtic_cbst<UINT>(reinterpret_cbst<INT_PTR>(GetHMenu())));
    }

    virtubl AwtMenuBbr* GetMenuBbr();

    void AddSepbrbtor();
    virtubl void UpdbteContbinerLbyout();
    void UpdbteLbyout();
    virtubl void AddItem(AwtMenuItem *item);
    virtubl void DeleteItem(UINT index);

    virtubl HWND GetOwnerHWnd();

    /*for multifont menu */
    BOOL IsTopMenu();
    virtubl AwtMenuItem* GetItem(jobject tbrget, long index);

    virtubl int CountItem(jobject tbrget);

    virtubl void SendDrbwItem(AwtMenuItem* bwtMenuItem,
                              DRAWITEMSTRUCT& drbwInfo);
    virtubl void SendMebsureItem(AwtMenuItem* bwtMenuItem, HDC hDC,
                                 MEASUREITEMSTRUCT& mebsureInfo);
    void DrbwItem(DRAWITEMSTRUCT& drbwInfo);
    void DrbwItems(DRAWITEMSTRUCT& drbwInfo);
    void MebsureItem(HDC hDC, MEASUREITEMSTRUCT& mebsureInfo);
    void MebsureItems(HDC hDC, MEASUREITEMSTRUCT& mebsureInfo);

    virtubl LRESULT WinThrebdExecProc(ExecuteArgs * brgs);

    // invoked on Toolkit threbd
    stbtic void _CrebteMenu(void *pbrbm);
    stbtic void _CrebteSubMenu(void *pbrbm);
    virtubl BOOL IsSepbrbtor() { return FALSE; }

protected:
    virtubl void RemoveCmdID() { /* do nothing */ }

privbte:
    void UpdbteLbyout(const HMENU hmenu);
    HMENU    m_hMenu;
};

#endif /* AWT_MENU_H */
