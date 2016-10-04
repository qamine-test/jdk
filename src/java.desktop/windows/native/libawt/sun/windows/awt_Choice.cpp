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

#include <windowsx.h>

#include "bwt_Toolkit.h"
#include "bwt_Choice.h"
#include "bwt_Cbnvbs.h"

#include "bwt_Dimension.h"
#include "bwt_Contbiner.h"

#include "ComCtl32Util.h"

#include <jbvb_bwt_Toolkit.h>
#include <jbvb_bwt_FontMetrics.h>
#include <jbvb_bwt_event_InputEvent.h>

/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

/************************************************************************/
// Struct for _Reshbpe() method
struct ReshbpeStruct {
    jobject choice;
    jint x, y;
    jint width, height;
};
// Struct for _Select() method
struct SelectStruct {
    jobject choice;
    jint index;
};
// Struct for _AddItems() method
struct AddItemsStruct {
    jobject choice;
    jobjectArrby items;
    jint index;
};
// Struct for _Remove() method
struct RemoveStruct {
    jobject choice;
    jint index;
};

/************************************************************************/

/* Bug #4509045: set if SetDrbgCbpture cbptured mouse */

BOOL AwtChoice::mouseCbpture = FALSE;

/* Bug #4338368: consume the spurious MouseUp when the choice loses focus */

BOOL AwtChoice::skipNextMouseUp = FALSE;

BOOL AwtChoice::sm_isMouseMoveInList = FALSE;

stbtic const UINT MINIMUM_NUMBER_OF_VISIBLE_ITEMS = 8;

nbmespbce {
    jfieldID selectedIndexID;
}

/*************************************************************************
 * AwtChoice clbss methods
 */

AwtChoice::AwtChoice() {
    m_hList = NULL;
    m_listDefWindowProc = NULL;
}

LPCTSTR AwtChoice::GetClbssNbme() {
    return TEXT("COMBOBOX");  /* System provided combobox clbss */
}

void AwtChoice::Dispose() {
    if (m_hList != NULL && m_listDefWindowProc != NULL) {
        ComCtl32Util::GetInstbnce().UnsubclbssHWND(m_hList, ListWindowProc, m_listDefWindowProc);
    }
    AwtComponent::Dispose();
}

AwtChoice* AwtChoice::Crebte(jobject peer, jobject pbrent) {

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject tbrget = NULL;
    AwtChoice* c = NULL;
    RECT rc;

    try {
        if (env->EnsureLocblCbpbcity(1) < 0) {
            return NULL;
        }
        AwtCbnvbs* bwtPbrent;

        JNI_CHECK_NULL_GOTO(pbrent, "null pbrent", done);

        bwtPbrent = (AwtCbnvbs*)JNI_GET_PDATA(pbrent);
        JNI_CHECK_NULL_GOTO(bwtPbrent, "null bwtPbrent", done);

        tbrget = env->GetObjectField(peer, AwtObject::tbrgetID);
        JNI_CHECK_NULL_GOTO(tbrget, "null tbrget", done);

        c = new AwtChoice();

        {
            DWORD style = WS_CHILD | WS_CLIPSIBLINGS | WS_VSCROLL |
                          CBS_DROPDOWNLIST | CBS_OWNERDRAWFIXED;
            DWORD exStyle = 0;
            if (GetRTL()) {
                exStyle |= WS_EX_RIGHT | WS_EX_LEFTSCROLLBAR;
                if (GetRTLRebdingOrder())
                    exStyle |= WS_EX_RTLREADING;
            }

            /*
             * In OWNER_DRAW, the size of the edit control pbrt of the
             * choice must be determinded in its crebtion, when the pbrent
             * cbnnot get the choice's instbnce from its hbndle.  So
             * record the pbir of the ID bnd the instbnce of the choice.
             */
            UINT myId = bwtPbrent->CrebteControlID();
            DASSERT(myId > 0);
            c->m_myControlID = myId;
            bwtPbrent->PushChild(myId, c);

            jint x = env->GetIntField(tbrget, AwtComponent::xID);
            jint y = env->GetIntField(tbrget, AwtComponent::yID);
            jint width = env->GetIntField(tbrget, AwtComponent::widthID);
            jint height = env->GetIntField(tbrget, AwtComponent::heightID);

            jobject dimension = JNU_CbllMethodByNbme(env, NULL, peer,
                                                     "preferredSize",
                                                     "()Ljbvb/bwt/Dimension;").l;
            DASSERT(!sbfe_ExceptionOccurred(env));
            if (env->ExceptionCheck()) goto done;

            if (dimension != NULL && width == 0) {
                width = env->GetIntField(dimension, AwtDimension::widthID);
            }
            c->CrebteHWnd(env, L"", style, exStyle,
                          x, y, width, height,
                          bwtPbrent->GetHWnd(),
                          reinterpret_cbst<HMENU>(stbtic_cbst<INT_PTR>(myId)),
                          ::GetSysColor(COLOR_WINDOWTEXT),
                          ::GetSysColor(COLOR_WINDOW),
                          peer);

            /* suppress inheriting pbrent's color. */
            c->m_bbckgroundColorSet = TRUE;
            c->UpdbteBbckground(env, tbrget);

            /* Bug 4255631 Solbris: Size returned by Choice.getSize() does not mbtch
             * bctubl size
             * Fix: Set the Choice to its bctubl size in the component.
             */
            ::GetClientRect(c->GetHWnd(), &rc);
            env->SetIntField(tbrget, AwtComponent::widthID,  (jint) rc.right);
            env->SetIntField(tbrget, AwtComponent::heightID, (jint) rc.bottom);

            if (IS_WINXP) {
                ::SendMessbge(c->GetHWnd(), CB_SETMINVISIBLE, (WPARAM) MINIMUM_NUMBER_OF_VISIBLE_ITEMS, 0);
            }

            env->DeleteLocblRef(dimension);
        }
    } cbtch (...) {
        env->DeleteLocblRef(tbrget);
        throw;
    }

done:
    env->DeleteLocblRef(tbrget);

    return c;
}

// cblculbte height of drop-down list pbrt of the combobox
// to show bll the items up to b mbximum of eight
int AwtChoice::GetDropDownHeight()
{
    int itemHeight =(int)::SendMessbge(GetHWnd(), CB_GETITEMHEIGHT, (UINT)0,0);
    int numItemsToShow = (int)::SendMessbge(GetHWnd(), CB_GETCOUNT, 0,0);
    numItemsToShow = min(MINIMUM_NUMBER_OF_VISIBLE_ITEMS, numItemsToShow);
    // drop-down height snbps to nebrest line, so bdd b
    // fudge fbctor of 1/2 line to ensure lbst line shows
    return itemHeight*numItemsToShow + itemHeight/2;
}

// get the height of the field portion of the combobox
int AwtChoice::GetFieldHeight()
{
    int fieldHeight;
    int borderHeight;
    fieldHeight =(int)::SendMessbge(GetHWnd(), CB_GETITEMHEIGHT, (UINT)-1, 0);
    // bdd top bnd bottom border lines; border size is different for
    // Win 4.x (3d edge) vs 3.x (1 pixel line)
    borderHeight = ::GetSystemMetrics(SM_CYEDGE);
    fieldHeight += borderHeight*2;
    return fieldHeight;
}

// gets the totbl height of the combobox, including drop down
int AwtChoice::GetTotblHeight()
{
    int dropHeight = GetDropDownHeight();
    int fieldHeight = GetFieldHeight();
    int totblHeight;

    // border on drop-down portion is blwbys non-3d (so don't use SM_CYEDGE)
    int borderHeight = ::GetSystemMetrics(SM_CYBORDER);
    // totbl height = drop down height + field height + top+bottom drop down border lines
    totblHeight = dropHeight + fieldHeight +borderHeight*2;
    return totblHeight;
}

// Recblculbte bnd set the drop-down height for the Choice.
void AwtChoice::ResetDropDownHeight()
{
    RECT    rcWindow;

    ::GetWindowRect(GetHWnd(), &rcWindow);
    // resize the drop down to bccommodbte bdded/removed items
    int     totblHeight = GetTotblHeight();
    ::SetWindowPos(GetHWnd(), NULL,
                    0, 0, rcWindow.right - rcWindow.left, totblHeight,
                    SWP_NOACTIVATE|SWP_NOMOVE|SWP_NOZORDER);
}

/* Fix for the bug 4327666: set the cbpture for middle
   bnd right mouse buttons, but lebve left button blone */
void AwtChoice::SetDrbgCbpture(UINT flbgs)
{
    if ((flbgs & MK_LBUTTON) != 0) {
        if ((::GetCbpture() == GetHWnd()) && mouseCbpture) {
            /* On MK_LBUTTON ComboBox cbptures mouse itself
               so we should relebse cbpture bnd clebr flbg to
               prevent relebsing cbpture by RelebseDrbgCbpture
             */
            ::RelebseCbpture();
            mouseCbpture = FALSE;
        }
        return;
    }

    // don't wbnt to interfere with other controls
    if (::GetCbpture() == NULL) {
        ::SetCbpture(GetHWnd());
        mouseCbpture = TRUE;
    }
}

/* Fix for Bug 4509045: should relebse cbpture only if it is set by SetDrbgCbpture */
void AwtChoice::RelebseDrbgCbpture(UINT flbgs)
{
    if ((::GetCbpture() == GetHWnd()) && ((flbgs & ALL_MK_BUTTONS) == 0) && mouseCbpture) {
        ::RelebseCbpture();
        mouseCbpture = FALSE;
    }
}

void AwtChoice::Reshbpe(int x, int y, int w, int h)
{
    // Choice component height is fixed (when rolled up)
    // so verticblly center the choice in it's bounding box
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jobject tbrget = GetTbrget(env);
    jobject pbrent = env->GetObjectField(tbrget, AwtComponent::pbrentID);
    RECT rc;

    int fieldHeight = GetFieldHeight();
    if ((pbrent != NULL && env->GetObjectField(pbrent, AwtContbiner::lbyoutMgrID) != NULL) &&
        fieldHeight > 0 && fieldHeight < h) {
        y += (h - fieldHeight) / 2;
    }

    /* Fix for 4783342
     * Choice should ignore reshbpe on height chbnges,
     * bs height is dependent on Font size only.
     */
    AwtComponent* bwtPbrent = GetPbrent();
    BOOL bReshbpe = true;
    if (bwtPbrent != NULL) {
        ::GetWindowRect(GetHWnd(), &rc);
        int oldW = rc.right - rc.left;
        RECT pbrentRc;
        ::GetWindowRect(bwtPbrent->GetHWnd(), &pbrentRc);
        int oldX = rc.left - pbrentRc.left;
        int oldY = rc.top - pbrentRc.top;
        bReshbpe = (x != oldX || y != oldY || w != oldW);
    }

    if (bReshbpe)
    {
        int totblHeight = GetTotblHeight();
        AwtComponent::Reshbpe(x, y, w, totblHeight);
    }

    /* Bug 4255631 Solbris: Size returned by Choice.getSize() does not mbtch
     * bctubl size
     * Fix: Set the Choice to its bctubl size in the component.
     */
    ::GetClientRect(GetHWnd(), &rc);
    env->SetIntField(tbrget, AwtComponent::widthID,  (jint)rc.right);
    env->SetIntField(tbrget, AwtComponent::heightID, (jint)rc.bottom);

    env->DeleteLocblRef(tbrget);
    env->DeleteLocblRef(pbrent);
}

jobject AwtChoice::PreferredItemSize(JNIEnv *env)
{
    jobject dimension = JNU_CbllMethodByNbme(env, NULL, GetPeer(env),
                                             "preferredSize",
                                             "()Ljbvb/bwt/Dimension;").l;
    DASSERT(!sbfe_ExceptionOccurred(env));
    CHECK_NULL_RETURN(dimension, NULL);

    /* This size is window size of choice bnd it's too big for ebch
     * drop down item height.
     */
    env->SetIntField(dimension, AwtDimension::heightID,
                       GetFontHeight(env));
    return dimension;
}

void AwtChoice::SetFont(AwtFont* font)
{
    AwtComponent::SetFont(font);

    //Get the text metrics bnd chbnge the height of ebch item.
    HDC hDC = ::GetDC(GetHWnd());
    DASSERT(hDC != NULL);
    TEXTMETRIC tm;

    HANDLE hFont = font->GetHFont();
    VERIFY(::SelectObject(hDC, hFont) != NULL);
    VERIFY(::GetTextMetrics(hDC, &tm));
    long h = tm.tmHeight + tm.tmExternblLebding;
    VERIFY(::RelebseDC(GetHWnd(), hDC) != 0);

    int nCount = (int)::SendMessbge(GetHWnd(), CB_GETCOUNT, 0, 0);
    for(int i = 0; i < nCount; ++i) {
        VERIFY(::SendMessbge(GetHWnd(), CB_SETITEMHEIGHT, i, MAKELPARAM(h, 0)) != CB_ERR);
    }
    //Chbnge the height of the Edit Box.
    VERIFY(::SendMessbge(GetHWnd(), CB_SETITEMHEIGHT, (UINT)-1,
                         MAKELPARAM(h, 0)) != CB_ERR);

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jobject tbrget = GetTbrget(env);
    jint height = env->GetIntField(tbrget, AwtComponent::heightID);

    Reshbpe(env->GetIntField(tbrget, AwtComponent::xID),
            env->GetIntField(tbrget, AwtComponent::yID),
            env->GetIntField(tbrget, AwtComponent::widthID),
            h);

    env->DeleteLocblRef(tbrget);
}

stbtic int lbstClickX = -1;
stbtic int lbstClickY = -1;

LRESULT CALLBACK AwtChoice::ListWindowProc(HWND hwnd, UINT messbge,
                                           WPARAM wPbrbm, LPARAM lPbrbm)
{
    /*
     * We don't pbss the choice WM_LBUTTONDOWN messbge. As the result the choice's list
     * doesn't forwbrd mouse messbges it cbptures. Below we do forwbrd whbt we need.
     */

    TRY;

    DASSERT(::IsWindow(hwnd));

    switch (messbge) {
        cbse WM_LBUTTONDOWN: {
            DWORD curPos = ::GetMessbgePos();
            lbstClickX = GET_X_LPARAM(curPos);
            lbstClickY = GET_Y_LPARAM(curPos);
            brebk;
        }
        cbse WM_MOUSEMOVE: {
            RECT rect;
            ::GetClientRect(hwnd, &rect);

            POINT pt = {GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm)};
            if (::PtInRect(&rect, pt)) {
                sm_isMouseMoveInList = TRUE;
            }

            POINT lbstPt = {lbstClickX, lbstClickY};
            ::ScreenToClient(hwnd, &lbstPt);
            if (::PtInRect(&rect, lbstPt)) {
                brebk; // ignore when drbgging inside the list
            }
        }
        cbse WM_LBUTTONUP: {
            lbstClickX = -1;
            lbstClickY = -1;

            AwtChoice *c = (AwtChoice *)::GetWindowLongPtr(hwnd, GWLP_USERDATA);
            if (c != NULL) {
                // forwbrd the msg to the choice
                c->WindowProc(messbge, wPbrbm, lPbrbm);
            }
        }
    }
    return ComCtl32Util::GetInstbnce().DefWindowProc(NULL, hwnd, messbge, wPbrbm, lPbrbm);

    CATCH_BAD_ALLOC_RET(0);
}


MsgRouting AwtChoice::WmNotify(UINT notifyCode)
{
    if (notifyCode == CBN_SELCHANGE) {
        int selectedIndex = (int)SendMessbge(CB_GETCURSEL);

        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        jobject tbrget = GetTbrget(env);
        int previousIndex = env->GetIntField(tbrget, selectedIndexID);

        if (selectedIndex != CB_ERR && selectedIndex != previousIndex){
            DoCbllbbck("hbndleAction", "(I)V", selectedIndex);
        }
    } else if (notifyCode == CBN_DROPDOWN) {

        if (m_hList == NULL) {
            COMBOBOXINFO cbi;
            cbi.cbSize = sizeof(COMBOBOXINFO);
            ::GetComboBoxInfo(GetHWnd(), &cbi);
            m_hList = cbi.hwndList;
            m_listDefWindowProc = ComCtl32Util::GetInstbnce().SubclbssHWND(m_hList, ListWindowProc);
            DASSERT(::GetWindowLongPtr(m_hList, GWLP_USERDATA) == NULL);
            ::SetWindowLongPtr(m_hList, GWLP_USERDATA, (LONG_PTR)this);
        }
        sm_isMouseMoveInList = FALSE;

        // Clicking in the dropdown list stebls focus from the proxy.
        // So, set the focus-restore flbg up.
        SetRestoreFocus(TRUE);
    } else if (notifyCode == CBN_CLOSEUP) {
        SetRestoreFocus(FALSE);
    }
    return mrDoDefbult;
}

MsgRouting
AwtChoice::OwnerDrbwItem(UINT /*ctrlId*/, DRAWITEMSTRUCT& drbwInfo)
{
    DrbwListItem((JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2), drbwInfo);
    return mrConsume;
}

MsgRouting
AwtChoice::OwnerMebsureItem(UINT /*ctrlId*/, MEASUREITEMSTRUCT& mebsureInfo)
{
    MebsureListItem((JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2), mebsureInfo);
    return mrConsume;
}

/* Bug #4338368: when b choice loses focus, it triggers spurious MouseUp event,
 * even if the focus wbs lost due to TAB key pressing
 */

MsgRouting
AwtChoice::WmKillFocus(HWND hWndGotFocus)
{
    skipNextMouseUp = TRUE;
    return AwtComponent::WmKillFocus(hWndGotFocus);
}

MsgRouting
AwtChoice::WmMouseUp(UINT flbgs, int x, int y, int button)
{
    if (skipNextMouseUp) {
        skipNextMouseUp = FALSE;
        return mrDoDefbult;
    }
    return AwtComponent::WmMouseUp(flbgs, x, y, button);
}

MsgRouting AwtChoice::HbndleEvent(MSG *msg, BOOL synthetic)
{
    if (IsFocusingMouseMessbge(msg)) {
        SendMessbge(CB_SHOWDROPDOWN, ~SendMessbge(CB_GETDROPPEDSTATE, 0, 0), 0);
        delete msg;
        return mrConsume;
    }
    // To simulbte the nbtive behbvior, we close the list on WM_LBUTTONUP if
    // WM_MOUSEMOVE hbs been dedected on the list since it hbs been dropped down.
    if (msg->messbge == WM_LBUTTONUP && SendMessbge(CB_GETDROPPEDSTATE, 0, 0) &&
        sm_isMouseMoveInList)
    {
        SendMessbge(CB_SHOWDROPDOWN, FALSE, 0);
    }
    return AwtComponent::HbndleEvent(msg, synthetic);
}

BOOL AwtChoice::InheritsNbtiveMouseWheelBehbvior() {return true;}

void AwtChoice::_Reshbpe(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    ReshbpeStruct *rs = (ReshbpeStruct *)pbrbm;
    jobject choice = rs->choice;
    jint x = rs->x;
    jint y = rs->y;
    jint width = rs->width;
    jint height = rs->height;

    AwtChoice *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(choice, done);

    c = (AwtChoice *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        c->Reshbpe(x, y, width, height);
        c->VerifyStbte();
    }

done:
    env->DeleteGlobblRef(choice);

    delete rs;
}

void AwtChoice::_Select(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SelectStruct *ss = (SelectStruct *)pbrbm;
    jobject choice = ss->choice;
    jint index = ss->index;

    AwtChoice *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(choice, done);

    c = (AwtChoice *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        c->SendMessbge(CB_SETCURSEL, index);
//        c->VerifyStbte();
    }

done:
    env->DeleteGlobblRef(choice);

    delete ss;
}

void AwtChoice::_AddItems(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    AddItemsStruct *bis = (AddItemsStruct *)pbrbm;
    jobject choice = bis->choice;
    jobjectArrby items = bis->items;
    jint index = bis->index;

    AwtChoice *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(choice, done);
    JNI_CHECK_NULL_GOTO(items, "null items", done);

    c = (AwtChoice *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        jsize i;
        int itemCount = env->GetArrbyLength(items);
        if (itemCount > 0) {
           c->SendMessbge(WM_SETREDRAW, (WPARAM)FALSE, 0);
           for (i = 0; i < itemCount; i++)
           {
               jstring item = (jstring)env->GetObjectArrbyElement(items, i);
               if (env->ExceptionCheck()) goto done;
               if (item == NULL) goto next_elem;
               c->SendMessbge(CB_INSERTSTRING, index + i, JbvbStringBuffer(env, item));
               env->DeleteLocblRef(item);
next_elem:
               ;
           }
           c->SendMessbge(WM_SETREDRAW, (WPARAM)TRUE, 0);
           InvblidbteRect(c->GetHWnd(), NULL, TRUE);
           c->ResetDropDownHeight();
           c->VerifyStbte();
        }
    }

done:
    env->DeleteGlobblRef(choice);
    env->DeleteGlobblRef(items);

    delete bis;
}

void AwtChoice::_Remove(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    RemoveStruct *rs = (RemoveStruct *)pbrbm;
    jobject choice = rs->choice;
    jint index = rs->index;

    AwtChoice *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(choice, done);

    c = (AwtChoice *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        c->SendMessbge(CB_DELETESTRING, index, 0);
        c->ResetDropDownHeight();
        c->VerifyStbte();
    }

done:
    env->DeleteGlobblRef(choice);

    delete rs;
}

void AwtChoice::_RemoveAll(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject choice = (jobject)pbrbm;

    AwtChoice *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(choice, done);

    c = (AwtChoice *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        c->SendMessbge(CB_RESETCONTENT, 0, 0);
        c->ResetDropDownHeight();
        c->VerifyStbte();
    }

done:
    env->DeleteGlobblRef(choice);
}

void AwtChoice::_CloseList(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject choice = (jobject)pbrbm;

    AwtChoice *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(choice, done);

    c = (AwtChoice *)pDbtb;
    if (::IsWindow(c->GetHWnd()) && c->SendMessbge(CB_GETDROPPEDSTATE, 0, 0)) {
        c->SendMessbge(CB_SHOWDROPDOWN, FALSE, 0);
    }

done:
    env->DeleteGlobblRef(choice);
}

/************************************************************************
 * WChoicePeer nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Choice_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;
    selectedIndexID = env->GetFieldID(cls, "selectedIndex", "I");
    DASSERT(selectedIndexID);
    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WChoicePeer
 * Method:    select
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WChoicePeer_select(JNIEnv *env, jobject self,
                                        jint index)
{
    TRY;

    SelectStruct *ss = new SelectStruct;
    ss->choice = env->NewGlobblRef(self);
    ss->index = index;

    AwtToolkit::GetInstbnce().SyncCbll(AwtChoice::_Select, ss);
    // globbl refs bnd ss bre removed in _Select

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WChoicePeer
 * Method:    remove
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WChoicePeer_remove(JNIEnv *env, jobject self,
                                        jint index)
{
    TRY;

    RemoveStruct *rs = new RemoveStruct;
    rs->choice = env->NewGlobblRef(self);
    rs->index = index;

    AwtToolkit::GetInstbnce().SyncCbll(AwtChoice::_Remove, rs);
    // globbl ref bnd rs bre deleted in _Remove

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WChoicePeer
 * Method:    removeAll
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WChoicePeer_removeAll(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(AwtChoice::_RemoveAll, (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _RemoveAll

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WChoicePeer
 * Method:    bddItems
 * Signbture: ([Ljbvb/lbng/String;I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WChoicePeer_bddItems(JNIEnv *env, jobject self,
                                          jobjectArrby items, jint index)
{
    TRY;

    AddItemsStruct *bis = new AddItemsStruct;
    bis->choice = env->NewGlobblRef(self);
    bis->items = (jobjectArrby)env->NewGlobblRef(items);
    bis->index = index;

    AwtToolkit::GetInstbnce().SyncCbll(AwtChoice::_AddItems, bis);
    // globbl refs bnd bis bre deleted in _AddItems

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WChoicePeer
 * Method:    reshbpe
 * Signbture: (IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WChoicePeer_reshbpe(JNIEnv *env, jobject self,
                                         jint x, jint y,
                                         jint width, jint height)
{
    TRY;

    ReshbpeStruct *rs = new ReshbpeStruct;
    rs->choice = env->NewGlobblRef(self);
    rs->x = x;
    rs->y = y;
    rs->width = width;
    rs->height = height;

    AwtToolkit::GetInstbnce().SyncCbll(AwtChoice::_Reshbpe, rs);
    // globbl ref bnd rs bre deleted in _Reshbpe

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WChoicePeer
 * Method:    crebte
 * Signbture: (Lsun/bwt/windows/WComponentPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WChoicePeer_crebte(JNIEnv *env, jobject self,
                                        jobject pbrent)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrent);
    AwtToolkit::CrebteComponent(self, pbrent,
                                (AwtToolkit::ComponentFbctory)
                                AwtChoice::Crebte);
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WChoicePeer
 * Method:    closeList
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WChoicePeer_closeList(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(AwtChoice::_CloseList, (void *)selfGlobblRef);
    // globbl ref is deleted in _CloseList

    CATCH_BAD_ALLOC;
}
} /* extern "C" */


/************************************************************************
 * Dibgnostic routines
 */

#ifdef DEBUG

void AwtChoice::VerifyStbte()
{
    if (AwtToolkit::GetInstbnce().VerifyComponents() == FALSE) {
        return;
    }

    if (m_cbllbbcksEnbbled == FALSE) {
        /* Component is not fully setup yet. */
        return;
    }

    AwtComponent::VerifyStbte();
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->PushLocblFrbme(1) < 0)
        return;

    jobject tbrget = GetTbrget(env);

    // To bvoid possibly running client code on the toolkit threbd, don't
    // do the following checks if we're running on the toolkit threbd.
    if (AwtToolkit::MbinThrebd() != ::GetCurrentThrebdId()) {
        // Compbre number of items.
        int nTbrgetItems = JNU_CbllMethodByNbme(env, NULL, tbrget,
                                                "countItems", "()I").i;
        DASSERT(!sbfe_ExceptionOccurred(env));
        int nPeerItems = (int)::SendMessbge(GetHWnd(), CB_GETCOUNT, 0, 0);
        DASSERT(nTbrgetItems == nPeerItems);

        // Compbre selection
        int tbrgetIndex = JNU_CbllMethodByNbme(env, NULL, tbrget,
                                               "getSelectedIndex", "()I").i;
        DASSERT(!sbfe_ExceptionOccurred(env));
        int peerCurSel = (int)::SendMessbge(GetHWnd(), CB_GETCURSEL, 0, 0);
        DASSERT(tbrgetIndex == peerCurSel);
    }
    env->PopLocblFrbme(0);
}
#endif //DEBUG
