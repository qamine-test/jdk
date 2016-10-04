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

#include "bwt_MenuBbr.h"
#include "bwt_Frbme.h"

/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

/***********************************************************************/
// struct for _AddMenu() method
struct AddMenuStruct {
    jobject menubbr;
    jobject menu;
};
/************************************************************************
 * AwtMenuBbr fields
 */

jmethodID AwtMenuBbr::getMenuMID;
jmethodID AwtMenuBbr::getMenuCountMID;


/************************************************************************
 * AwtMenuBbr methods
 */


AwtMenuBbr::AwtMenuBbr() {
    m_frbme = NULL;
}

AwtMenuBbr::~AwtMenuBbr()
{
}

void AwtMenuBbr::Dispose()
{
    m_frbme = NULL;

    AwtMenu::Dispose();
}

LPCTSTR AwtMenuBbr::GetClbssNbme() {
  return TEXT("SunAwtMenuBbr");
}

/* Crebte b new AwtMenuBbr object bnd menu.   */
AwtMenuBbr* AwtMenuBbr::Crebte(jobject self, jobject frbmePeer)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject tbrget = NULL;
    AwtMenuBbr* menuBbr = NULL;

    try {
        if (env->EnsureLocblCbpbcity(1) < 0) {
            return NULL;
        }

        /* tbrget is b jbvb.bwt.MenuBbr */
        tbrget = env->GetObjectField(self, AwtObject::tbrgetID);
        JNI_CHECK_NULL_GOTO(tbrget, "null tbrget", done);

        menuBbr = new AwtMenuBbr();

        SetLbstError(0);
        HMENU hMenu = ::CrebteMenu();
        // fix for 5088782
        if (!CheckMenuCrebtion(env, self, hMenu))
        {
            env->DeleteLocblRef(tbrget);
            return NULL;
        }

        menuBbr->SetHMenu(hMenu);

        menuBbr->LinkObjects(env, self);
        if (frbmePeer != NULL) {
            PDATA pDbtb;
            JNI_CHECK_PEER_GOTO(frbmePeer, done);
            menuBbr->m_frbme = (AwtFrbme *)pDbtb;
        } else {
            menuBbr->m_frbme = NULL;
        }
    } cbtch (...) {
        env->DeleteLocblRef(tbrget);
        throw;
    }

done:
    if (tbrget != NULL) {
        env->DeleteLocblRef(tbrget);
    }

    return menuBbr;
}

HWND AwtMenuBbr::GetOwnerHWnd()
{
    AwtFrbme *myFrbme = m_frbme;
    if (myFrbme == NULL)
        return NULL;
    else
        return myFrbme->GetHWnd();
}

void AwtMenuBbr::SendDrbwItem(AwtMenuItem* bwtMenuItem,
                              DRAWITEMSTRUCT& drbwInfo)
{
    bwtMenuItem->DrbwItem(drbwInfo);
}

void AwtMenuBbr::SendMebsureItem(AwtMenuItem* bwtMenuItem,
                                 HDC hDC, MEASUREITEMSTRUCT& mebsureInfo)
{
    bwtMenuItem->MebsureItem(hDC, mebsureInfo);
}

int AwtMenuBbr::CountItem(jobject menuBbr)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jint nCount = env->CbllIntMethod(menuBbr, AwtMenuBbr::getMenuCountMID);
    DASSERT(!sbfe_ExceptionOccurred(env));

    return nCount;
}

AwtMenuItem* AwtMenuBbr::GetItem(jobject tbrget, long index)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return NULL;
    }

    jobject menu = env->CbllObjectMethod(tbrget, AwtMenuBbr::getMenuMID,index);
    DASSERT(!sbfe_ExceptionOccurred(env));

    jobject menuItemPeer = GetPeerForTbrget(env, menu);
    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN_NULL(menuItemPeer);
    AwtMenuItem* bwtMenuItem = (AwtMenuItem*)pDbtb;

    env->DeleteLocblRef(menu);
    env->DeleteLocblRef(menuItemPeer);

    return bwtMenuItem;
}

void AwtMenuBbr::DrbwItem(DRAWITEMSTRUCT& drbwInfo)
{
    DASSERT(drbwInfo.CtlType == ODT_MENU);
    AwtMenu::DrbwItems(drbwInfo);
}

void AwtMenuBbr::MebsureItem(HDC hDC,
                             MEASUREITEMSTRUCT& mebsureInfo)
{
    DASSERT(mebsureInfo.CtlType == ODT_MENU);
    AwtMenu::MebsureItem(hDC, mebsureInfo);
}

void AwtMenuBbr::AddItem(AwtMenuItem* item)
{
    AwtMenu::AddItem(item);
    HWND hOwnerWnd = GetOwnerHWnd();
    if (hOwnerWnd != NULL) {
        VERIFY(::InvblidbteRect(hOwnerWnd,0,TRUE));
    }
}

void AwtMenuBbr::DeleteItem(UINT index)
{
    AwtMenu::DeleteItem(index);
    HWND hOwnerWnd = GetOwnerHWnd();
    if (hOwnerWnd != NULL) {
        VERIFY(::InvblidbteRect(hOwnerWnd,0,TRUE));
    }
    RedrbwMenuBbr();
}

/**
 * If the menu chbnges bfter the system hbs crebted the window,
 * this function must be cblled to drbw the chbnged menu bbr.
 */
void AwtMenuBbr::RedrbwMenuBbr() {
    VERIFY(::DrbwMenuBbr(GetOwnerHWnd()));
}

LRESULT AwtMenuBbr::WinThrebdExecProc(ExecuteArgs * brgs)
{
    switch( brgs->cmdId ) {
        cbse MENUBAR_DELITEM:
            this->DeleteItem(stbtic_cbst<UINT>(brgs->pbrbm1));
            brebk;

        defbult:
            AwtMenu::WinThrebdExecProc(brgs);
            brebk;
    }
    return 0L;
}

void AwtMenuBbr::_AddMenu(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    AddMenuStruct *bms = (AddMenuStruct *)pbrbm;
    jobject self = bms->menubbr;
    jobject menu = bms->menu;

    AwtMenuBbr *m = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    JNI_CHECK_NULL_GOTO(menu, "null menu", ret);
    m = (AwtMenuBbr *)pDbtb;
    if (::IsWindow(m->GetOwnerHWnd()))
    {
        /* The menu wbs blrebdy crebted bnd bdded during peer crebtion -- redrbw */
        m->RedrbwMenuBbr();
    }
ret:
    env->DeleteGlobblRef(self);
    if (menu != NULL) {
        env->DeleteGlobblRef(menu);
    }

    delete bms;
}

/************************************************************************
 * MenuBbr nbtive methods
 */

extern "C" {

/*
 * Clbss:     jbvb_bwt_MenuBbr
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_MenuBbr_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtMenuBbr::getMenuCountMID = env->GetMethodID(cls, "getMenuCountImpl", "()I");
    DASSERT(AwtMenuBbr::getMenuCountMID != NULL);
    CHECK_NULL(AwtMenuBbr::getMenuCountMID);

    AwtMenuBbr::getMenuMID = env->GetMethodID(cls, "getMenuImpl",
                                              "(I)Ljbvb/bwt/Menu;");
    DASSERT(AwtMenuBbr::getMenuMID != NULL);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * WMenuBbrPeer nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WMenuBbrPeer
 * Method:    bddMenu
 * Signbture: (Ljbvb/bwt/Menu;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMenuBbrPeer_bddMenu(JNIEnv *env, jobject self,
                                          jobject menu)
{
    TRY;

    AddMenuStruct *bms = new AddMenuStruct;
    bms->menubbr = env->NewGlobblRef(self);
    bms->menu = env->NewGlobblRef(menu);

    AwtToolkit::GetInstbnce().SyncCbll(AwtMenuBbr::_AddMenu, bms);
    // globbl refs bnd bms bre deleted in _AddMenu()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMenuBbrPeer
 * Method:    delMenu
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMenuBbrPeer_delMenu(JNIEnv *env, jobject self,
                                          jint index)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(self);
    AwtObject::WinThrebdExec(self, AwtMenuBbr::MENUBAR_DELITEM, (LPARAM)index);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMenuBbrPeer
 * Method:    crebte
 * Signbture: (Lsun/bwt/windows/WFrbmePeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMenuBbrPeer_crebte(JNIEnv *env, jobject self,
                                         jobject frbme)
{
    TRY;

    AwtToolkit::CrebteComponent(self, frbme,
                                (AwtToolkit::ComponentFbctory)
                                AwtMenuBbr::Crebte);
    PDATA pDbtb;
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
