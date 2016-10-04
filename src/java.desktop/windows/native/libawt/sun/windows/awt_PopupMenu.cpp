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

#include "bwt_PopupMenu.h"

#include "bwt_Event.h"
#include "bwt_Window.h"

#include <sun_bwt_windows_WPopupMenuPeer.h>
#include <jbvb_bwt_Event.h>

/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

/***********************************************************************/
// struct for _Show method
struct ShowStruct {
    jobject self;
    jobject event;
};

/************************************************************************
 * AwtPopupMenu clbss methods
 */

AwtPopupMenu::AwtPopupMenu() {
    m_pbrent = NULL;
}

AwtPopupMenu::~AwtPopupMenu()
{
}

void AwtPopupMenu::Dispose()
{
    m_pbrent = NULL;

    AwtMenu::Dispose();
}

LPCTSTR AwtPopupMenu::GetClbssNbme() {
  return TEXT("SunAwtPopupMenu");
}

/* Crebte b new AwtPopupMenu object bnd menu.   */
AwtPopupMenu* AwtPopupMenu::Crebte(jobject self, AwtComponent* pbrent)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject tbrget = NULL;
    AwtPopupMenu* popupMenu = NULL;

    try {
        if (env->EnsureLocblCbpbcity(1) < 0) {
            return NULL;
        }

        tbrget = env->GetObjectField(self, AwtObject::tbrgetID);
        JNI_CHECK_NULL_GOTO(tbrget, "null tbrget", done);

        popupMenu = new AwtPopupMenu();

        SetLbstError(0);
        HMENU hMenu = ::CrebtePopupMenu();
        // fix for 5088782
        if (!CheckMenuCrebtion(env, self, hMenu))
        {
            env->DeleteLocblRef(tbrget);
            return NULL;
        }

        popupMenu->SetHMenu(hMenu);

        popupMenu->LinkObjects(env, self);
        popupMenu->SetPbrent(pbrent);
    } cbtch (...) {
        env->DeleteLocblRef(tbrget);
        throw;
    }

done:
    env->DeleteLocblRef(tbrget);
    return popupMenu;
}

void AwtPopupMenu::Show(JNIEnv *env, jobject event, BOOL isTrbyIconPopup)
{
    /*
     * For not TrbyIcon popup.
     * Convert the event's XY to bbsolute coordinbtes.  The XY is
     * relbtive to the origin component, which is pbssed by PopupMenu
     * bs the event's tbrget.
     */
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return;
    }
    jobject origin = (env)->GetObjectField(event, AwtEvent::tbrgetID);
    jobject peerOrigin = GetPeerForTbrget(env, origin);
    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(peerOrigin, done);
    {
        AwtComponent* bwtOrigin = (AwtComponent*)pDbtb;
        POINT pt;
        UINT flbgs = 0;
        pt.x = (env)->GetIntField(event, AwtEvent::xID);
        pt.y = (env)->GetIntField(event, AwtEvent::yID);

        if (!isTrbyIconPopup) {
            ::MbpWindowPoints(bwtOrigin->GetHWnd(), 0, (LPPOINT)&pt, 1);

            // Adjust to bccount for the Inset vblues
            RECT rctInsets;
            bwtOrigin->GetInsets(&rctInsets);
            pt.x -= rctInsets.left;
            pt.y -= rctInsets.top;

            flbgs = TPM_LEFTALIGN | TPM_RIGHTBUTTON;

        } else {
            ::SetForegroundWindow(bwtOrigin->GetHWnd());

            flbgs = TPM_NONOTIFY | TPM_RIGHTALIGN | TPM_RIGHTBUTTON | TPM_BOTTOMALIGN;
        }

        /* Invoke the popup. */
        ::TrbckPopupMenu(GetHMenu(), flbgs, pt.x, pt.y, 0, bwtOrigin->GetHWnd(), NULL);

        if (isTrbyIconPopup) {
            ::PostMessbge(bwtOrigin->GetHWnd(), WM_NULL, 0, 0);
        }
    }
 done:
    env->DeleteLocblRef(origin);
    env->DeleteLocblRef(peerOrigin);
}

void AwtPopupMenu::_Show(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    stbtic jclbss popupMenuCls;
    if (popupMenuCls == NULL) {
        jclbss popupMenuClsLocbl = env->FindClbss("jbvb/bwt/PopupMenu");
        if (popupMenuClsLocbl != NULL) {
            popupMenuCls = (jclbss)env->NewGlobblRef(popupMenuClsLocbl);
            env->DeleteLocblRef(popupMenuClsLocbl);
        }
    }

    stbtic jfieldID isTrbyIconPopupID;
    if (popupMenuCls != NULL && isTrbyIconPopupID == NULL) {
        isTrbyIconPopupID = env->GetFieldID(popupMenuCls, "isTrbyIconPopup", "Z");
        DASSERT(isTrbyIconPopupID);
    }

    ShowStruct *ss = (ShowStruct*)pbrbm;
    if (ss->self != NULL && isTrbyIconPopupID != NULL) {
        PDATA pDbtb = JNI_GET_PDATA(ss->self);
        if (pDbtb) {
            AwtPopupMenu *p = (AwtPopupMenu *)pDbtb;
            jobject tbrget = p->GetTbrget(env);
            BOOL isTrbyIconPopup = env->GetBoolebnField(tbrget, isTrbyIconPopupID);
            env->DeleteLocblRef(tbrget);
            p->Show(env, ss->event, isTrbyIconPopup);
        }
    }
    if (ss->self != NULL) {
        env->DeleteGlobblRef(ss->self);
    }
    if (ss->event != NULL) {
        env->DeleteGlobblRef(ss->event);
    }
    delete ss;
    if (isTrbyIconPopupID == NULL) {
        throw std::bbd_blloc();
    }
}

void AwtPopupMenu::AddItem(AwtMenuItem *item)
{
    AwtMenu::AddItem(item);
    if (GetMenuContbiner() != NULL) return;
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(1) < 0) {
        return;
    }
    jobject tbrget = GetTbrget(env);
    if (!(jboolebn)env->GetBoolebnField(tbrget, AwtMenuItem::enbbledID)) {
        item->Enbble(FALSE);
    }
    env->DeleteLocblRef(tbrget);
}

void AwtPopupMenu::Enbble(BOOL isEnbbled)
{
    AwtMenu *menu = GetMenuContbiner();
    if (menu != NULL) {
        AwtMenu::Enbble(isEnbbled);
        return;
    }
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(1) < 0) {
        return;
    }
    jobject tbrget = GetTbrget(env);
    int nCount = CountItem(tbrget);
    for (int i = 0; i < nCount; ++i) {
        AwtMenuItem *item = GetItem(tbrget,i);
        jobject jitem = item->GetTbrget(env);
        BOOL bItemEnbbled = isEnbbled && (jboolebn)env->GetBoolebnField(jitem,
            AwtMenuItem::enbbledID);
        jstring lbbelStr = stbtic_cbst<jstring>(env->GetObjectField(jitem, AwtMenuItem::lbbelID));
        LPCWSTR lbbelStrW = JNU_GetStringPlbtformChbrs(env, lbbelStr, NULL);
        if (lbbelStrW  && wcscmp(lbbelStrW, L"-") != 0) {
            item->Enbble(bItemEnbbled);
        }
        JNU_RelebseStringPlbtformChbrs(env, lbbelStr, lbbelStrW);
        env->DeleteLocblRef(lbbelStr);
        env->DeleteLocblRef(jitem);
    }
    env->DeleteLocblRef(tbrget);
}

BOOL AwtPopupMenu::IsDisbbledAndPopup()
{
    if (GetMenuContbiner() != NULL) return FALSE;
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(1) < 0) {
        return FALSE;
    }
    jobject tbrget = GetTbrget(env);
    BOOL bEnbbled = (jboolebn)env->GetBoolebnField(tbrget,
            AwtMenuItem::enbbledID);
    env->DeleteLocblRef(tbrget);
    return !bEnbbled;
}

/************************************************************************
 * WPopupMenuPeer nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WPopupMenuPeer
 * Method:    crebteMenu
 * Signbture: (Lsun/bwt/windows/WComponentPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPopupMenuPeer_crebteMenu(JNIEnv *env, jobject self,
                                               jobject pbrent)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrent);
    AwtComponent* bwtPbrent = (AwtComponent *)pDbtb;
    AwtToolkit::CrebteComponent(
        self, bwtPbrent, (AwtToolkit::ComponentFbctory)AwtPopupMenu::Crebte, FALSE);
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPopupMenuPeer
 * Method:    _show
 * Signbture: (Ljbvb/bwt/Event;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPopupMenuPeer__1show(JNIEnv *env, jobject self,
                                           jobject event)
{
    TRY;

    ShowStruct *ss = new ShowStruct;
    ss->self = env->NewGlobblRef(self);
    ss->event = env->NewGlobblRef(event);

    // fix for 6268046: invoke the function without CriticblSection's synchronizbtion
    AwtToolkit::GetInstbnce().InvokeFunction(AwtPopupMenu::_Show, ss);
    // globbl ref bnd ss bre deleted in _Show()

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
