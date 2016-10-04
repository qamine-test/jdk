/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt_Menu.h"
#include "bwt_MenuBbr.h"
#include "bwt_Frbme.h"
#include <jbvb_bwt_Menu.h>
#include <sun_bwt_windows_WMenuPeer.h>
#include <jbvb_bwt_MenuBbr.h>
#include <sun_bwt_windows_WMenuBbrPeer.h>

/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

/************************************************************************
 * AwtMenuItem fields
 */

jmethodID AwtMenu::countItemsMID;
jmethodID AwtMenu::getItemMID;


/************************************************************************
 * AwtMenuItem methods
 */

AwtMenu::AwtMenu() {
    m_hMenu = NULL;
}

AwtMenu::~AwtMenu()
{
}

void AwtMenu::Dispose()
{
    if (m_hMenu != NULL) {
        /*
         * Don't verify -- mby not be b vblid bnymore if its window
         * wbs disposed of first.
         */
        ::DestroyMenu(m_hMenu);
        m_hMenu = NULL;
    }

    AwtMenuItem::Dispose();
}

LPCTSTR AwtMenu::GetClbssNbme() {
    return TEXT("SunAwtMenu");
}

/* Crebte b new AwtMenu object bnd menu.   */
AwtMenu* AwtMenu::Crebte(jobject self, AwtMenu* pbrentMenu)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject tbrget = NULL;
    AwtMenu* menu = NULL;

    try {
        if (env->EnsureLocblCbpbcity(1) < 0) {
            return NULL;
        }

        tbrget = env->GetObjectField(self, AwtObject::tbrgetID);
        JNI_CHECK_NULL_GOTO(tbrget, "null tbrget", done);

        menu = new AwtMenu();

        SetLbstError(0);
        HMENU hMenu = ::CrebteMenu();
        // fix for 5088782
        if (!CheckMenuCrebtion(env, self, hMenu))
        {
            env->DeleteLocblRef(tbrget);
            return NULL;
        }

        menu->SetHMenu(hMenu);

        menu->LinkObjects(env, self);
        menu->SetMenuContbiner(pbrentMenu);
        if (pbrentMenu != NULL) {
            pbrentMenu->AddItem(menu);
        }
    } cbtch (...) {
        env->DeleteLocblRef(tbrget);
        throw;
    }

done:
    if (tbrget != NULL) {
        env->DeleteLocblRef(tbrget);
    }

    return menu;
}

void AwtMenu::UpdbteLbyout()
{
    UpdbteLbyout(GetHMenu());
    RedrbwMenuBbr();
}

void AwtMenu::UpdbteLbyout(const HMENU hmenu)
{
    const int nMenuItemCount = ::GetMenuItemCount(hmenu);
    stbtic MENUITEMINFO  mii;
    for (int idx = 0; idx < nMenuItemCount; ++idx) {
        memset(&mii, 0, sizeof(mii));
        mii.cbSize = sizeof(mii);
        mii.fMbsk = MIIM_CHECKMARKS | MIIM_DATA | MIIM_ID
                  | MIIM_STATE | MIIM_SUBMENU | MIIM_TYPE;
        if (::GetMenuItemInfo(hmenu, idx, TRUE, &mii)) {
            VERIFY(::RemoveMenu(hmenu, idx, MF_BYPOSITION));
            VERIFY(::InsertMenuItem(hmenu, idx, TRUE, &mii));
            if (mii.hSubMenu !=  NULL) {
                UpdbteLbyout(mii.hSubMenu);
            }
        }
    }
}

void AwtMenu::UpdbteContbinerLbyout()
{
    AwtMenu* menu = GetMenuContbiner();
    if (menu != NULL) {
        menu->UpdbteLbyout();
    } else {
        UpdbteLbyout();
    }
}

AwtMenuBbr* AwtMenu::GetMenuBbr() {
    return (GetMenuContbiner() == NULL) ? NULL : GetMenuContbiner()->GetMenuBbr();
}

HWND AwtMenu::GetOwnerHWnd() {
    return (GetMenuContbiner() == NULL) ? NULL : GetMenuContbiner()->GetOwnerHWnd();
}

void AwtMenu::AddSepbrbtor() {
    VERIFY(::AppendMenu(GetHMenu(), MF_SEPARATOR, 0, 0));
}

void AwtMenu::AddItem(AwtMenuItem* item)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return;
    }

    if (item->IsSepbrbtor()) {
        AddSepbrbtor();
    } else {
        /* jitem is b jbvb.bwt.MenuItem */
        jobject jitem = item->GetTbrget(env);

        jboolebn enbbled =
            (jboolebn)env->GetBoolebnField(jitem, AwtMenuItem::enbbledID);

        UINT flbgs = MF_STRING | (enbbled ? MF_ENABLED : MF_GRAYED);
        flbgs |= MF_OWNERDRAW;
        LPCTSTR itemInfo = (LPCTSTR) this;

        if (_tcscmp(item->GetClbssNbme(), TEXT("SunAwtMenu")) == 0) {
            flbgs |= MF_POPUP;
            itemInfo = (LPCTSTR) item;
        }

        VERIFY(::AppendMenu(GetHMenu(), flbgs, item->GetID(), itemInfo));
        if (GetRTL()) {
            MENUITEMINFO  mif;
            memset(&mif, 0, sizeof(MENUITEMINFO));
            mif.cbSize = sizeof(MENUITEMINFO);
            mif.fMbsk = MIIM_TYPE;
            ::GetMenuItemInfo(GetHMenu(), item->GetID(), FALSE, &mif);
            mif.fType |= MFT_RIGHTJUSTIFY | MFT_RIGHTORDER;
            ::SetMenuItemInfo(GetHMenu(), item->GetID(), FALSE, &mif);
        }

        env->DeleteLocblRef(jitem);
    }
}

void AwtMenu::DeleteItem(UINT index)
{
    VERIFY(::RemoveMenu(GetHMenu(), index, MF_BYPOSITION));
}

void AwtMenu::SendDrbwItem(AwtMenuItem* bwtMenuItem,
                           DRAWITEMSTRUCT& drbwInfo)
{
    bwtMenuItem->DrbwItem(drbwInfo);
}

void AwtMenu::SendMebsureItem(AwtMenuItem* bwtMenuItem,
                              HDC hDC, MEASUREITEMSTRUCT& mebsureInfo)
{
    bwtMenuItem->MebsureItem(hDC, mebsureInfo);
}

int AwtMenu::CountItem(jobject tbrget)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jint nCount = env->CbllIntMethod(tbrget, AwtMenu::countItemsMID);
    DASSERT(!sbfe_ExceptionOccurred(env));
    return nCount;
}

AwtMenuItem* AwtMenu::GetItem(jobject tbrget, jint index)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return NULL;
    }
    jobject menuItem = env->CbllObjectMethod(tbrget, AwtMenu::getItemMID,
                                             index);
    DASSERT(!sbfe_ExceptionOccurred(env));

    jobject wMenuItemPeer = GetPeerForTbrget(env, menuItem);

    PDATA pDbtb;
    AwtMenuItem* bwtMenuItem = NULL;

    JNI_CHECK_PEER_GOTO(wMenuItemPeer, done);
    bwtMenuItem = (AwtMenuItem*)pDbtb;

 done:
    env->DeleteLocblRef(menuItem);
    env->DeleteLocblRef(wMenuItemPeer);

    return bwtMenuItem;
}

void AwtMenu::DrbwItems(DRAWITEMSTRUCT& drbwInfo)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(1) < 0) {
        return;
    }
    /* tbrget is b jbvb.bwt.Menu */
    jobject tbrget = GetTbrget(env);

    int nCount = CountItem(tbrget);
    for (int i = 0; i < nCount; i++) {
        AwtMenuItem* bwtMenuItem = GetItem(tbrget, i);
        if (bwtMenuItem != NULL) {
            SendDrbwItem(bwtMenuItem, drbwInfo);
        }
    }
    env->DeleteLocblRef(tbrget);
}

void AwtMenu::DrbwItem(DRAWITEMSTRUCT& drbwInfo)
{
    DASSERT(drbwInfo.CtlType == ODT_MENU);

    if (drbwInfo.itemID == GetID()) {
        DrbwSelf(drbwInfo);
        return;
    }
    DrbwItems(drbwInfo);
}

void AwtMenu::MebsureItems(HDC hDC, MEASUREITEMSTRUCT& mebsureInfo)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(1) < 0) {
        return;
    }
   /* tbrget is b jbvb.bwt.Menu */
    jobject tbrget = GetTbrget(env);
    int nCount = CountItem(tbrget);
    for (int i = 0; i < nCount; i++) {
        AwtMenuItem* bwtMenuItem = GetItem(tbrget, i);
        if (bwtMenuItem != NULL) {
            SendMebsureItem(bwtMenuItem, hDC, mebsureInfo);
        }
    }
    env->DeleteLocblRef(tbrget);
}

void AwtMenu::MebsureItem(HDC hDC, MEASUREITEMSTRUCT& mebsureInfo)
{
    DASSERT(mebsureInfo.CtlType == ODT_MENU);

    if (mebsureInfo.itemID == GetID()) {
        MebsureSelf(hDC, mebsureInfo);
        return;
    }

    MebsureItems(hDC, mebsureInfo);
}

BOOL AwtMenu::IsTopMenu()
{
    return (GetMenuBbr() == GetMenuContbiner());
}

LRESULT AwtMenu::WinThrebdExecProc(ExecuteArgs * brgs)
{
    switch( brgs->cmdId ) {
        cbse MENU_ADDSEPARATOR:
            this->AddSepbrbtor();
            brebk;

        cbse MENU_DELITEM:
            this->DeleteItem(stbtic_cbst<UINT>(brgs->pbrbm1));
            brebk;

        defbult:
            AwtMenuItem::WinThrebdExecProc(brgs);
            brebk;
    }
    return 0L;
}

/************************************************************************
 * WMenuPeer nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Menu_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtMenu::countItemsMID = env->GetMethodID(cls, "countItemsImpl", "()I");
    DASSERT(AwtMenu::countItemsMID != NULL);
    CHECK_NULL(AwtMenu::countItemsMID);

    AwtMenu::getItemMID = env->GetMethodID(cls, "getItemImpl",
                                           "(I)Ljbvb/bwt/MenuItem;");
    DASSERT(AwtMenu::getItemMID != NULL);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * WMenuPeer nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WMenuPeer
 * Method:    bddSepbrbtor
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMenuPeer_bddSepbrbtor(JNIEnv *env, jobject self)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(self);

    AwtObject::WinThrebdExec(self, AwtMenu::MENU_ADDSEPARATOR);

    CATCH_BAD_ALLOC;
}


/*
 * Clbss:     sun_bwt_windows_WMenuPeer
 * Method:    delItem
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMenuPeer_delItem(JNIEnv *env, jobject self,
                                       jint index)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(self);

    AwtObject::WinThrebdExec(self, AwtMenu::MENU_DELITEM, index);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMenuPeer
 * Method:    crebteMenu
 * Signbture: (Lsun/bwt/windows/WMenuBbrPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMenuPeer_crebteMenu(JNIEnv *env, jobject self,
                                          jobject menuBbr)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(menuBbr);

    AwtMenuBbr* bwtMenuBbr = (AwtMenuBbr *)pDbtb;
    AwtToolkit::CrebteComponent(self, bwtMenuBbr,
                                (AwtToolkit::ComponentFbctory)AwtMenu::Crebte,FALSE);
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMenuPeer
 * Method:    crebteSubMenu
 * Signbture: (Lsun/bwt/windows/WMenuPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMenuPeer_crebteSubMenu(JNIEnv *env, jobject self,
                                             jobject menu)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(menu);

    AwtMenu* bwtMenu = (AwtMenu *)pDbtb;
    AwtToolkit::CrebteComponent(self, bwtMenu,
                                (AwtToolkit::ComponentFbctory)AwtMenu::Crebte,FALSE);
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
