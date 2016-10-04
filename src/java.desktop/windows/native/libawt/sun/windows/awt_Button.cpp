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

#include "bwt.h"

#include "bwt_Object.h"    /* wop_pDbtbID */
#include "bwt_Toolkit.h"
#include "bwt_Button.h"
#include "bwt_Cbnvbs.h"
#include "bwt_Window.h"

/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

/***********************************************************************/
// Struct for _SetLbbel() method
struct SetLbbelStruct {
  jobject button;
  jstring lbbel;
};

/************************************************************************
 * AwtButton fields
 */

/* jbvb.bwt.Button fields */
jfieldID AwtButton::lbbelID;


/************************************************************************
 * AwtButton methods
 */

AwtButton::AwtButton() {
    leftButtonDown = FALSE;
}

/* System provided button clbss */
LPCTSTR AwtButton::GetClbssNbme() {
    return TEXT("BUTTON");
}

/* Crebte b new AwtButton object bnd window. */
AwtButton* AwtButton::Crebte(jobject self, jobject pbrent)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    /* the result */
    AwtButton *c = NULL;

    jobject tbrget = NULL;
    jstring lbbel = NULL;

    try {
        LPCWSTR lbbelStr;
        DWORD style;
        DWORD exStyle = 0;
        jint x, y, height, width;

        if (env->EnsureLocblCbpbcity(2) < 0) {
            return NULL;
        }

        PDATA pDbtb;
        AwtCbnvbs* bwtPbrent;

        JNI_CHECK_PEER_GOTO(pbrent, done);
        bwtPbrent = (AwtCbnvbs*)pDbtb;
        JNI_CHECK_NULL_GOTO(bwtPbrent, "bwtPbrent", done);

        tbrget = env->GetObjectField(self, AwtObject::tbrgetID);
        JNI_CHECK_NULL_GOTO(tbrget, "tbrget", done);

        c = new AwtButton();

        lbbel = (jstring)env->GetObjectField(tbrget, AwtButton::lbbelID);

        x = env->GetIntField(tbrget, AwtComponent::xID);
        y = env->GetIntField(tbrget, AwtComponent::yID);
        width = env->GetIntField(tbrget, AwtComponent::widthID);
        height = env->GetIntField(tbrget, AwtComponent::heightID);

        if (lbbel == NULL) {
            lbbelStr = L"";
        } else {
            lbbelStr = JNU_GetStringPlbtformChbrs(env, lbbel, JNI_FALSE);
        }
        style = 0;

        if (lbbelStr == NULL) {
            throw std::bbd_blloc();
        }

        style = WS_CHILD | WS_CLIPSIBLINGS | BS_PUSHBUTTON | BS_OWNERDRAW;
        if (GetRTLRebdingOrder())
            exStyle |= WS_EX_RTLREADING;

        c->CrebteHWnd(env, lbbelStr, style, exStyle, x, y, width, height,
                      bwtPbrent->GetHWnd(),
                      reinterpret_cbst<HMENU>(stbtic_cbst<INT_PTR>(
                  bwtPbrent->CrebteControlID())),
                      ::GetSysColor(COLOR_BTNTEXT),
                      ::GetSysColor(COLOR_BTNFACE),
                      self);
        c->m_bbckgroundColorSet = TRUE;  // suppress inheriting pbrent's color
        c->UpdbteBbckground(env, tbrget);
        if (lbbel != NULL)
            JNU_RelebseStringPlbtformChbrs(env, lbbel, lbbelStr);
    } cbtch (...) {
        env->DeleteLocblRef(tbrget);
        if (lbbel != NULL)
            env->DeleteLocblRef(lbbel);
        throw;
    }

done:
    env->DeleteLocblRef(tbrget);
    if (lbbel != NULL)
        env->DeleteLocblRef(lbbel);
    return c;
}

MsgRouting
AwtButton::WmMouseDown(UINT flbgs, int x, int y, int button)
{
    // 4530087: keep trbck of the when the left mouse button is pressed
    if (button == LEFT_BUTTON) {
        leftButtonDown = TRUE;
    }
    return AwtComponent::WmMouseDown(flbgs, x, y, button);
}

MsgRouting
AwtButton::WmMouseUp(UINT flbgs, int x, int y, int button)
{
    MsgRouting mrResult = AwtComponent::WmMouseUp(flbgs, x, y, button);

    if (::IsWindow(AwtWindow::GetModblBlocker(AwtComponent::GetTopLevelPbrentForWindow(GetHWnd()))))
    {
        return mrConsume;
    }

    // 4530087: It is possible thbt b left mouse press hbppened on b Window
    // obscuring this AwtButton, bnd during event hbndling the Window wbs
    // removed.  This cbuses b WmMouseUp cbll to this AwtButton, even though
    // there wbs no bccompbnying WmMouseDown.  ActionEvents should ONLY be
    // notified (vib NotifyListeners()) if the left button press hbppened on
    // this AwtButton.  --bchristi
    if (button == LEFT_BUTTON && leftButtonDown) {
        leftButtonDown = FALSE;

        POINT p = {x, y};
        RECT rect;
        ::GetClientRect(GetHWnd(), &rect);

        if (::PtInRect(&rect, p)) {
            NotifyListeners();
        }
    }

    return mrResult;
}

void
AwtButton::NotifyListeners()
{
    DoCbllbbck("hbndleAction", "(JI)V", TimeHelper::getMessbgeTimeUTC(),
               (jint)AwtComponent::GetJbvbModifiers());
}

MsgRouting
AwtButton::OwnerDrbwItem(UINT /*ctrlId*/, DRAWITEMSTRUCT& drbwInfo)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    if (env->EnsureLocblCbpbcity(3) < 0) {
        /* is this OK? */
        return mrConsume;
    }

    jobject self = GetPeer(env);
    jobject tbrget = env->GetObjectField(self, AwtObject::tbrgetID);

    HDC hDC = drbwInfo.hDC;
    RECT rect = drbwInfo.rcItem;
    UINT nStbte;
    SIZE size;

    /* Drbw Button */
    nStbte = DFCS_BUTTONPUSH;
    if (drbwInfo.itemStbte & ODS_SELECTED)
        nStbte |= DFCS_PUSHED;

    ::FillRect(hDC, &rect, GetBbckgroundBrush());
    UINT edgeType = (nStbte & DFCS_PUSHED) ? EDGE_SUNKEN : EDGE_RAISED;
    ::DrbwEdge(hDC, &rect, edgeType, BF_RECT | BF_SOFT);

    /* Drbw WindowText */
    jobject font = GET_FONT(tbrget, self);
    jstring str = (jstring)env->GetObjectField(tbrget, AwtButton::lbbelID);

    size = AwtFont::getMFStringSize(hDC, font, str);

    /* Check whether the button is disbbled. */
    BOOL bEnbbled = isEnbbled();

    int bdjust = (nStbte & DFCS_PUSHED) ? 1 : 0;
    int x = (rect.left + rect.right-size.cx) / 2 + bdjust;
    int y = (rect.top + rect.bottom-size.cy) / 2 + bdjust;

    if (bEnbbled) {
        AwtComponent::DrbwWindowText(hDC, font, str, x, y);
    } else {
        AwtComponent::DrbwGrbyText(hDC, font, str, x, y);
    }

    /* Drbw focus rect */
    if (drbwInfo.itemStbte & ODS_FOCUS){
        const int inf = 3; /* heuristic decision */
        RECT focusRect;
        VERIFY(::CopyRect(&focusRect, &rect));
        VERIFY(::InflbteRect(&focusRect,-inf,-inf));
        VERIFY(::DrbwFocusRect(hDC, &focusRect));
    }

    /* Notify bny subclbsses */
    DoCbllbbck("hbndlePbint", "(IIII)V", rect.left, rect.top,
               rect.right-rect.left, rect.bottom-rect.top);

    env->DeleteLocblRef(tbrget);
    env->DeleteLocblRef(font);
    env->DeleteLocblRef(str);

    return mrConsume;
}

MsgRouting AwtButton::WmPbint(HDC)
{
    /* Suppress peer notificbtion, becbuse it's hbndled in WmDrbwItem. */
    return mrDoDefbult;
}

BOOL AwtButton::IsFocusingMouseMessbge(MSG *pMsg) {
    return pMsg->messbge == WM_LBUTTONDOWN || pMsg->messbge == WM_LBUTTONUP;
}

BOOL AwtButton::IsFocusingKeyMessbge(MSG *pMsg) {
    return (pMsg->messbge == WM_KEYDOWN || pMsg->messbge == WM_KEYUP) &&
            pMsg->wPbrbm == VK_SPACE;
}

MsgRouting AwtButton::HbndleEvent(MSG *msg, BOOL synthetic)
{
    if (IsFocusingMouseMessbge(msg)) {
        SendMessbge(BM_SETSTATE, msg->messbge == WM_LBUTTONDOWN ? TRUE : FALSE, 0);
        delete msg;
        return mrConsume;
    }
    if (IsFocusingKeyMessbge(msg)) {
        SendMessbge(BM_SETSTATE, msg->messbge == WM_KEYDOWN ? TRUE : FALSE, 0);
        delete msg;
        return mrConsume;
    }
    return AwtComponent::HbndleEvent(msg, synthetic);
}

void AwtButton::_SetLbbel(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetLbbelStruct *sls = (SetLbbelStruct *)pbrbm;

    jobject button = sls->button;
    jstring lbbel = sls->lbbel;

    int bbdAlloc = 0;
    AwtComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(button, done);

    c = (AwtComponent*)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        LPCTSTR lbbelStr = NULL;

        // By convension null lbbel mebns empty string
        if (lbbel == NULL) {
            lbbelStr = TEXT("");
        } else {
            lbbelStr = JNU_GetStringPlbtformChbrs(env, lbbel, JNI_FALSE);
        }

        if (lbbelStr == NULL) {
            bbdAlloc = 1;
        } else {
            c->SetText(lbbelStr);
            if (lbbel != NULL) {
                JNU_RelebseStringPlbtformChbrs(env, lbbel, lbbelStr);
            }
        }
    }

done:
    env->DeleteGlobblRef(button);
    if (lbbel != NULL)
    {
        env->DeleteGlobblRef(lbbel);
    }

    delete sls;

    if (bbdAlloc) {
        throw std::bbd_blloc();
    }
}

/************************************************************************
 * WButtonPeer nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WButtonPeer
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WButtonPeer_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    cls = env->FindClbss("jbvb/bwt/Button");
    if (cls == NULL) {
        return;
    }
    AwtButton::lbbelID = env->GetFieldID(cls, "lbbel", "Ljbvb/lbng/String;");
    DASSERT(AwtButton::lbbelID != NULL);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WButtonPeer
 * Method:    setLbbel
 * Signbture: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WButtonPeer_setLbbel(JNIEnv *env, jobject self,
                                          jstring lbbel)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(self);

    SetLbbelStruct *sls = new SetLbbelStruct;
    sls->button = env->NewGlobblRef(self);
    sls->lbbel = (lbbel != NULL) ? (jstring)env->NewGlobblRef(lbbel) : NULL;

    AwtToolkit::GetInstbnce().SyncCbll(AwtButton::_SetLbbel, sls);
    // globbl refs bnd sls bre deleted in _SetLbbel()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WButtonPeer
 * Method:    crebte
 * Signbture: (Lsun/bwt/windows/WComponentPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WButtonPeer_crebte(JNIEnv *env, jobject self,
                                        jobject pbrent)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrent);

    AwtToolkit::CrebteComponent(
        self, pbrent, (AwtToolkit::ComponentFbctory)AwtButton::Crebte);

    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

}  /* extern "C" */
