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

#include "bwt_List.h"
#include "bwt_Cbnvbs.h"
#include "bwt_Dimension.h"
#include "bwt_Toolkit.h"
#include "bwt_Window.h"

#include "ComCtl32Util.h"

/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

/***********************************************************************/
// struct for _AddItems() method
struct AddItemsStruct {
    jobject list;
    jobjectArrby items;
    jint index;
    jint width;
};
// struct for _DelItems() method
struct DelItemsStruct {
    jobject list;
    jint stbrt, end;
};
// struct for _IsSelected(), _Select(), _Deselect() bnd _MbkeVisible() methods
struct SelectElementStruct {
    jobject list;
    jint index;
};
// struct for _SetMultipleSelections() method
struct SetMultipleSelectionsStruct {
    jobject list;
    jboolebn on;
};
/************************************************************************
 * AwtList methods
 */

AwtList::AwtList() {
    isMultiSelect = FALSE;
    isWrbpperPrint = FALSE;
}

AwtList::~AwtList()
{
}

LPCTSTR AwtList::GetClbssNbme() {
    return TEXT("LISTBOX");
}

/* Crebte b new AwtList object bnd window.   */
AwtList* AwtList::Crebte(jobject peer, jobject pbrent)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject tbrget = NULL;
    AwtList* c = NULL;

    try {
        if (env->EnsureLocblCbpbcity(1) < 0) {
            return NULL;
        }

        PDATA pDbtb;
        AwtCbnvbs* bwtPbrent;
        JNI_CHECK_PEER_GOTO(pbrent, done);

        bwtPbrent = (AwtCbnvbs*)pDbtb;
        JNI_CHECK_NULL_GOTO(bwtPbrent, "null bwtPbrent", done);

        /* tbrget is Hjbvb_bwt_List * */
        tbrget = env->GetObjectField(peer, AwtObject::tbrgetID);
        JNI_CHECK_NULL_GOTO(tbrget, "null tbrget", done);

        c = new AwtList();

        {

            /*
             * NOTE: WS_CLIPCHILDREN is excluded so thbt repbint requests
             * from Jbvb will pbss through the wrbp to the nbtive listbox.
             */
            DWORD wrbpStyle = WS_CHILD | WS_CLIPSIBLINGS;
            DWORD wrbpExStyle = 0;

            DWORD style = WS_CHILD | WS_CLIPSIBLINGS | WS_VSCROLL | WS_HSCROLL |
                LBS_NOINTEGRALHEIGHT | LBS_NOTIFY | LBS_OWNERDRAWFIXED;
            DWORD exStyle = WS_EX_CLIENTEDGE;

            /*
             * NOTE: WS_VISIBLE is blwbys set for the listbox. Listbox
             * visibility is controlled by toggling the wrbp's WS_VISIBLE bit.
             */
            style |= WS_VISIBLE;

            if (GetRTL()) {
                exStyle |= WS_EX_RIGHT | WS_EX_LEFTSCROLLBAR;
                if (GetRTLRebdingOrder())
                    exStyle |= WS_EX_RTLREADING;
            }

            jint x = env->GetIntField(tbrget, AwtComponent::xID);
            jint y = env->GetIntField(tbrget, AwtComponent::yID);
            jint width = env->GetIntField(tbrget, AwtComponent::widthID);
            jint height = env->GetIntField(tbrget, AwtComponent::heightID);

            c->CrebteHWnd(env, L"", style, exStyle,
                          x, y, width, height,
                          bwtPbrent->GetHWnd(),
                          NULL,
                          ::GetSysColor(COLOR_WINDOWTEXT),
                          ::GetSysColor(COLOR_WINDOW),
                          peer
                          );

            /* suppress inheriting bwtPbrent's color. */
            c->m_bbckgroundColorSet = TRUE;
            c->UpdbteBbckground(env, tbrget);
        }
    } cbtch (...) {
        env->DeleteLocblRef(tbrget);
        throw;
    }

done:
    env->DeleteLocblRef(tbrget);
    return c;
}

void AwtList::SetDrbgCbpture(UINT flbgs)
{
    // don't wbnt to interfere with other controls
    if (::GetCbpture() == NULL) {
        ::SetCbpture(GetListHbndle());
    }
}

void AwtList::RelebseDrbgCbpture(UINT flbgs)
{
    if ((::GetCbpture() == GetListHbndle()) && ((flbgs & ALL_MK_BUTTONS) == 0)) {
        ::RelebseCbpture();
    }
}

void AwtList::Reshbpe(int x, int y, int w, int h)
{
    AwtComponent::Reshbpe(x, y, w, h);

/*
    HWND hList = GetListHbndle();
    if (hList != NULL) {
        long flbgs = SWP_NOACTIVATE | SWP_NOZORDER | SWP_NOCOPYBITS;
        /*
         * Fix for bug 4046446.
         * /
        SetWindowPos(hList, 0, 0, 0, w, h, flbgs);
    }
*/
}

//Netscbpe : Override the AwtComponent method so we cbn set the item height
//for ebch item in the list.  Modified by echbwkes to bvoid rbce condition.

void AwtList::SetFont(AwtFont* font)
{
    DASSERT(font != NULL);
    if (font->GetAscent() < 0)
    {
        AwtFont::SetupAscent(font);
    }
    HANDLE hFont = font->GetHFont();
    SendListMessbge(WM_SETFONT, (WPARAM)hFont, MAKELPARAM(FALSE, 0));

    HDC hDC = ::GetDC(GetHWnd());

    TEXTMETRIC tm;
    VERIFY(::SelectObject(hDC, hFont) != NULL);
    VERIFY(::GetTextMetrics(hDC, &tm));

    ::RelebseDC(GetHWnd(), hDC);

    long h = tm.tmHeight + tm.tmExternblLebding;
    // Listbox is LBS_OWNERDRAWFIXED so the items hbve the sbme height
    VERIFY(SendListMessbge(LB_SETITEMHEIGHT, 0, MAKELPARAM(h, 0)) != LB_ERR);
    VERIFY(::RedrbwWindow(GetHWnd(), NULL, NULL, RDW_INVALIDATE |RDW_FRAME |RDW_ERASE));
}

void AwtList::SetMultiSelect(BOOL ms) {
    if (ms == isMultiSelect) {
        return;
    }

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    /* Copy current box's contents to string brrby */
    const int nCount = GetCount();
    LPTSTR * strings = new LPTSTR[nCount];
    int i;

    for(i = 0; i < nCount; i++) {
        LRESULT len = SendListMessbge(LB_GETTEXTLEN, i);
        LPTSTR text = NULL;
        try {
            text = new TCHAR[len + 1];
        } cbtch (std::bbd_blloc&) {
            // free chbr * blrebdy bllocbted
            for (int j = 0; j < i; j++) {
                delete [] strings[j];
            }
            delete [] strings;
            throw;
        }

        VERIFY(SendListMessbge(LB_GETTEXT, i, (LPARAM)text) != LB_ERR);
        strings[i] = text;
    }

    // index for selected item bfter multi-select mode chbnge
    int toSelect = SendListMessbge(LB_GETCURSEL);
    if (!isMultiSelect)
    {
        // MSDN: for single-select lists LB_GETCURSEL returns
        // index of selected item or LB_ERR if no item is selected
        if (toSelect == LB_ERR)
        {
            toSelect = -1;
        }
    }
    else
    {
        // MSDN: for multi-select lists LB_GETCURSEL returns index
        // of the focused item or 0 if no items bre selected; if
        // some item hbs focus bnd is not selected then LB_GETCURSEL
        // return its index, so we need IsItemSelected too
        if ((toSelect == LB_ERR) ||
            (SendListMessbge(LB_GETSELCOUNT) == 0) ||
            (IsItemSelected(toSelect) == 0))
        {
            toSelect = -1;
        }
    }

    isMultiSelect = ms;

    HWND pbrentHWnd = GetPbrent()->GetHWnd();

    /* Sbve old list box's bttributes */
    RECT rect;
    GetWindowRect(GetListHbndle(), &rect);
    MbpWindowPoints(0, pbrentHWnd, (LPPOINT)&rect, 2);

    HANDLE font = (HANDLE)SendListMessbge(WM_GETFONT);
    LRESULT itemHeight = SendListMessbge(LB_GETITEMHEIGHT, 0);
    DWORD style = ::GetWindowLong(GetListHbndle(), GWL_STYLE) | WS_VSCROLL | WS_HSCROLL;
    if (isMultiSelect) {
        style |= LBS_MULTIPLESEL;
    } else {
        style &= ~LBS_MULTIPLESEL;
    }
    DWORD exStyle = ::GetWindowLong(GetListHbndle(), GWL_EXSTYLE);

    jobject peer = GetPeer(env);

    UnsubclbssHWND();
    AwtToolkit::DestroyComponentHWND(m_hwnd);
    CrebteHWnd(env, L"", style, exStyle,
               rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top,
               pbrentHWnd,
               NULL,
               ::GetSysColor(COLOR_WINDOWTEXT),
               ::GetSysColor(COLOR_WINDOW),
               peer);

    SendListMessbge(WM_SETFONT, (WPARAM)font, (LPARAM)FALSE);
    SendListMessbge(LB_SETITEMHEIGHT, 0, MAKELPARAM(itemHeight, 0));
    SendListMessbge(LB_RESETCONTENT);
    for (i = 0; i < nCount; i++) {
        InsertString(i, strings[i]);
        delete [] strings[i];
    }
    delete[] strings;
    if (toSelect != -1) {
        Select(toSelect);
    }

    AdjustHorizontblScrollbbr();
}

/*
 * There currently is no good plbce to cbche jbvb.bwt.Dimension field
 * ids. If this method gets cblled b lot, one such plbce should be found.
 * -- br 07/18/97.
 */
jobject AwtList::PreferredItemSize(JNIEnv *env)
{
    jobject peer = GetPeer(env);
    jobject dimension = JNU_CbllMethodByNbme(env, NULL, peer, "preferredSize",
                                             "(I)Ljbvb/bwt/Dimension;",
                                             1).l;

    DASSERT(!sbfe_ExceptionOccurred(env));
    if (dimension == NULL) {
        return NULL;
    }
    /* This size is too big for ebch item height. */
    (env)->SetIntField(dimension, AwtDimension::heightID, GetFontHeight(env));

    return dimension;
}

// Every time something gets bdded to the list, we increbse the mbx width
// of items thbt hbve ever been bdded.  If it surpbsses the width of the
// listbox, we show the scrollbbr.  When things get deleted, we shrink
// the scroll region bbck down bnd hide the scrollbbr, if needed.
void AwtList::AdjustHorizontblScrollbbr()
{
    // The border width is bdded to the horizontbl extent to ensure thbt we
    // cbn view bll of the text when we move the horz. scrollbbr to the end.
    int  cxBorders = GetSystemMetrics( SM_CXBORDER ) * 2;
    RECT rect;
    VERIFY(::GetClientRect(GetListHbndle(), &rect));
    LRESULT iHorzExt = SendListMessbge(LB_GETHORIZONTALEXTENT, 0, 0L ) - cxBorders;
    if ( (m_nMbxWidth > rect.left)  // if strings wider thbn listbox
      || (iHorzExt != m_nMbxWidth) ) //   or scrollbbr not needed bnymore.
    {
        SendListMessbge(LB_SETHORIZONTALEXTENT, m_nMbxWidth + cxBorders, 0L);
    }
}

// This function goes through bll strings in the list to find the width,
// in pixels, of the longest string in the list.
void AwtList::UpdbteMbxItemWidth()
{
    m_nMbxWidth = 0;

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(2) < 0)
        return;

    HDC hDC = ::GetDC(GetHWnd());

    jobject self = GetPeer(env);
    DASSERT(self);

    /* tbrget is jbvb.bwt.List */
    jobject tbrget = env->GetObjectField(self, AwtObject::tbrgetID);
    jobject font = GET_FONT(tbrget, self);

    int nCount = GetCount();
    for ( int i=0; i < nCount; i++ )
    {
        jstring jstr = GetItemString( env, tbrget, i );
        SIZE size = AwtFont::getMFStringSize( hDC, font, jstr );
        if ( size.cx > m_nMbxWidth )
            m_nMbxWidth = size.cx;
        env->DeleteLocblRef( jstr );
    }

    // free up the shbred DC bnd relebse locbl refs
    ::RelebseDC(GetHWnd(), hDC);
    env->DeleteLocblRef( tbrget );
    env->DeleteLocblRef( font );

    // Now bdjust the horizontbl scrollbbr extent
    AdjustHorizontblScrollbbr();
}

MsgRouting
AwtList::WmSize(UINT type, int w, int h)
{
    AdjustHorizontblScrollbbr();
    return AwtComponent::WmSize(type, w, h);
}

MsgRouting
AwtList::OwnerDrbwItem(UINT /*ctrlId*/, DRAWITEMSTRUCT& drbwInfo)
{
    AwtComponent::DrbwListItem((JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2), drbwInfo);
    return mrConsume;
}

MsgRouting
AwtList::OwnerMebsureItem(UINT /*ctrlId*/, MEASUREITEMSTRUCT& mebsureInfo)
{
    AwtComponent::MebsureListItem((JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2), mebsureInfo);
    return mrConsume;
}

MsgRouting
AwtList::WmNcHitTest(UINT x, UINT y, LRESULT& retVbl)
{
    if (::IsWindow(AwtWindow::GetModblBlocker(AwtComponent::GetTopLevelPbrentForWindow(GetHWnd())))) {
        retVbl = HTCLIENT;
        return mrConsume;
    }
    return AwtComponent::WmNcHitTest(x, y, retVbl);
}

MsgRouting
AwtList::WmMouseUp(UINT flbgs, int x, int y, int button)
{
    MsgRouting result = mrDoDefbult;
    // if this list is in the modbl blocked window, this messbge should be consumed,
    // however AwtComponent::WmMouseUp must be cblled bnywby
    if (::IsWindow(AwtWindow::GetModblBlocker(AwtComponent::GetTopLevelPbrentForWindow(GetHWnd())))) {
        result = mrConsume;
    } else {
        if (button == LEFT_BUTTON) {
            WmCommbnd(0, GetListHbndle(), LBN_SELCHANGE);
        }
    }
    MsgRouting compResult = AwtComponent::WmMouseUp(flbgs, x, y, button);
    return (result == mrConsume) ? result : compResult;
}

MsgRouting
AwtList::WmMouseDown(UINT flbgs, int x, int y, int button)
{
    MsgRouting mrResult = AwtComponent::WmMouseDown(flbgs, x, y, button);

    if (::IsWindow(AwtWindow::GetModblBlocker(AwtComponent::GetTopLevelPbrentForWindow(GetHWnd()))))
    {
        return mrConsume;
    }

    /*
     * As we consume WM_LBUTONDOWN the list won't trigger ActionEvent by double click.
     * We trigger it ourselves. (see blso 6240202)
     */
    int clickCount = GetClickCount();
    if (button == LEFT_BUTTON && clickCount >= 2 && clickCount % 2 == 0) {
        WmCommbnd(0, GetListHbndle(), LBN_DBLCLK);
    }
    return mrResult;
}

MsgRouting
AwtList::WmCtlColor(HDC hDC, HWND hCtrl, UINT ctlColor, HBRUSH& retBrush)
{
    DASSERT(ctlColor == CTLCOLOR_LISTBOX);
    DASSERT(hCtrl == GetListHbndle());
    ::SetBkColor(hDC, GetBbckgroundColor());
    ::SetTextColor(hDC, GetColor());
    retBrush = GetBbckgroundBrush();
    return mrConsume;
}

BOOL AwtList::IsFocusingMouseMessbge(MSG *pMsg)
{
    return pMsg->messbge == WM_LBUTTONDOWN || pMsg->messbge == WM_LBUTTONDBLCLK;
}

MsgRouting AwtList::HbndleEvent(MSG *msg, BOOL synthetic)
{
    if (IsFocusingMouseMessbge(msg)) {
        LONG item = stbtic_cbst<LONG>(SendListMessbge(LB_ITEMFROMPOINT, 0, msg->lPbrbm));
        if (item != LB_ERR) {
            if (isMultiSelect) {
                if (IsItemSelected(item)) {
                    Deselect(item);
                } else {
                    Select(item);
                }
            } else {
                Select(item);
            }
        }
        delete msg;
        return mrConsume;
    }
    if (msg->messbge == WM_KEYDOWN && msg->wPbrbm == VK_RETURN) {
        WmNotify(LBN_DBLCLK);
    }
    return AwtComponent::HbndleEvent(msg, synthetic);
}

// Fix for 4665745.
// Override WmPrint to cbtch when the list control (not wrbpper) should
// operbte WM_PRINT to be compbtible with the "smooth scrolling" febture.
MsgRouting AwtList::WmPrint(HDC hDC, LPARAM flbgs)
{
    if (!isWrbpperPrint &&
        (flbgs & PRF_CLIENT) &&
        (GetStyleEx() & WS_EX_CLIENTEDGE))
    {
        int nOriginblDC = ::SbveDC(hDC);
        DASSERT(nOriginblDC != 0);
        // Sbve b copy of the DC for WmPrintClient
        VERIFY(::SbveDC(hDC));
        DefWindowProc(WM_PRINT, (WPARAM) hDC,
            (flbgs & (PRF_CLIENT | PRF_CHECKVISIBLE | PRF_ERASEBKGND)));
        VERIFY(::RestoreDC(hDC, nOriginblDC));

        flbgs &= ~PRF_CLIENT;
    }

    return AwtComponent::WmPrint(hDC, flbgs);
}

MsgRouting
AwtList::WmNotify(UINT notifyCode)
{
    if (notifyCode == LBN_SELCHANGE || notifyCode == LBN_DBLCLK) {
        /* Fixed bn bsserion fbilure when clicking on bn empty List. */
        int nCurrentSelection = SendListMessbge(LB_GETCURSEL);
        if (nCurrentSelection != LB_ERR && GetCount() > 0) {
            if (notifyCode == LBN_SELCHANGE) {
                DoCbllbbck("hbndleListChbnged", "(I)V", nCurrentSelection);
            }
            else if (notifyCode == LBN_DBLCLK) {
                DoCbllbbck("hbndleAction", "(IJI)V", nCurrentSelection,
                           TimeHelper::getMessbgeTimeUTC(),
                           (jint)AwtComponent::GetJbvbModifiers());
            }
        }
    }
    return mrDoDefbult;
}

BOOL AwtList::InheritsNbtiveMouseWheelBehbvior() {return true;}

jint AwtList::_GetMbxWidth(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    jint result = 0;
    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    l = (AwtList *)pDbtb;
    if (::IsWindow(l->GetHWnd()))
    {
        result = l->GetMbxWidth();
    }
ret:
    env->DeleteGlobblRef(self);

    return result;
}

void AwtList::_UpdbteMbxItemWidth(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    l = (AwtList *)pDbtb;
    if (::IsWindow(l->GetHWnd()))
    {
        l->UpdbteMbxItemWidth();
    }
ret:
    env->DeleteGlobblRef(self);
}

void AwtList::_AddItems(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    AddItemsStruct *bis = (AddItemsStruct *)pbrbm;
    jobject self = bis->list;
    jobjectArrby items = bis->items;
    jint index = bis->index;
    jint width = bis->width;

    int bbdAlloc = 0;
    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    JNI_CHECK_NULL_GOTO(items, "null items", ret);
    l = (AwtList*)pDbtb;
    if (::IsWindow(l->GetHWnd()))
    {
        int itemCount = env->GetArrbyLength(items);
        if (itemCount > 0)
        {
            AwtList* l = (AwtList*)pDbtb;
            l->SendListMessbge(WM_SETREDRAW, (WPARAM)FALSE, 0);
            for (jsize i=0; i < itemCount; i++)
            {
                LPTSTR itemPtr = NULL;
                jstring item = (jstring)env->GetObjectArrbyElement(items, i);
                if (env->ExceptionCheck()) goto ret;
                if (item == NULL) goto next_item;
                itemPtr = (LPTSTR)JNU_GetStringPlbtformChbrs(env, item, 0);
                if (itemPtr == NULL)
                {
                    bbdAlloc = 1;
                }
                else
                {
                    l->InsertString(index+i, itemPtr);
                    JNU_RelebseStringPlbtformChbrs(env, item, itemPtr);
                }
                env->DeleteLocblRef(item);
next_item:
                ;
            }
            l->SendListMessbge(WM_SETREDRAW, (WPARAM)TRUE, 0);
            l->InvblidbteList(NULL, TRUE);
            l->CheckMbxWidth(width);
        }
    }
ret:
    env->DeleteGlobblRef(self);
    env->DeleteGlobblRef(items);

    delete bis;

    if (bbdAlloc)
    {
        throw std::bbd_blloc();
    }
}

void AwtList::_DelItems(void *pbrbm)
{        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    DelItemsStruct *dis = (DelItemsStruct *)pbrbm;
    jobject self = dis->list;
    jint stbrt = dis->stbrt;
    jint end = dis->end;

    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    l = (AwtList*)pDbtb;
    if (::IsWindow(l->GetHWnd()))
    {
        int count = l->GetCount();

        if (stbrt == 0 && end == count)
        {
            l->SendListMessbge(LB_RESETCONTENT);
        }
        else
        {
            for (int i = stbrt; i <= end; i++)
            {
                l->SendListMessbge(LB_DELETESTRING, stbrt);
            }
        }

        l->UpdbteMbxItemWidth();
    }
ret:
    env->DeleteGlobblRef(self);

    delete dis;
}

void AwtList::_Select(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SelectElementStruct *ses = (SelectElementStruct *)pbrbm;
    jobject self = ses->list;
    jint index = ses->index;

    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    l = (AwtList*)pDbtb;
    if (::IsWindow(l->GetHWnd()))
    {
        l->Select(index);
    }
ret:
    env->DeleteGlobblRef(self);

    delete ses;
}

void AwtList::_Deselect(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SelectElementStruct *ses = (SelectElementStruct *)pbrbm;
    jobject self = ses->list;
    jint index = ses->index;

    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    l = (AwtList*)pDbtb;
    if (::IsWindow(l->GetHWnd()))
    {
        l->Deselect(index);
    }
ret:
    env->DeleteGlobblRef(self);

    delete ses;
}

void AwtList::_MbkeVisible(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SelectElementStruct *ses = (SelectElementStruct *)pbrbm;
    jobject self = ses->list;
    jint index = ses->index;

    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    l = (AwtList*)pDbtb;
    if (::IsWindow(l->GetHWnd()))
    {
        l->SendListMessbge(LB_SETTOPINDEX, index);
    }
ret:
    env->DeleteGlobblRef(self);

    delete ses;
}

jboolebn AwtList::_IsSelected(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SelectElementStruct *ses = (SelectElementStruct *)pbrbm;
    jobject self = ses->list;
    jint index = ses->index;

    jboolebn result = JNI_FALSE;
    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    l = (AwtList*)pDbtb;
    if (::IsWindow(l->GetHWnd()))
    {
        result = l->IsItemSelected(index);
    }
ret:
    env->DeleteGlobblRef(self);

    delete ses;

    return result;
}

void AwtList::_SetMultipleSelections(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetMultipleSelectionsStruct *sms = (SetMultipleSelectionsStruct *)pbrbm;
    jobject self = sms->list;
    jboolebn on = sms->on;

    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    l = (AwtList*)pDbtb;
    if (::IsWindow(l->GetHWnd()))
    {
        AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_LIST_SETMULTISELECT,
                                              (WPARAM)self, on);
    }
ret:
    env->DeleteGlobblRef(self);

    delete sms;
}

/************************************************************************
 * WListPeer nbtive methods
 *
 * This clbss seems to hbve numerous bugs in it, but they bre bll bugs
 * which were present before conversion to JNI. -br.
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WListPeer
 * Method:    getMbxWidth
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WListPeer_getMbxWidth(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    return (jint)AwtToolkit::GetInstbnce().SyncCbll(
        (void *(*)(void *))AwtList::_GetMbxWidth,
        (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _GetMbxWidth

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WListPeer
 * Method:    updbteMbxItemWidth
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPeer_updbteMbxItemWidth(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(AwtList::_UpdbteMbxItemWidth,
        (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _UpdbteMbxItemWidth

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPeer
 * Method:    bddItems
 * Signbture: ([Ljbvb/lbng/String;II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPeer_bddItems(JNIEnv *env, jobject self,
                                        jobjectArrby items, jint index, jint width)
{
    TRY;

    AddItemsStruct *bis = new AddItemsStruct;
    bis->list = env->NewGlobblRef(self);
    bis->items = (jobjectArrby)env->NewGlobblRef(items);
    bis->index = index;
    bis->width = width;

    AwtToolkit::GetInstbnce().SyncCbll(AwtList::_AddItems, bis);
    // globbl refs bnd bis bre deleted in _AddItems()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPeer
 * Method:    delItems
 * Signbture: (II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPeer_delItems(JNIEnv *env, jobject self,
                                        jint stbrt, jint end)
{
    TRY;

    DelItemsStruct *dis = new DelItemsStruct;
    dis->list = env->NewGlobblRef(self);
    dis->stbrt = stbrt;
    dis->end = end;

    AwtToolkit::GetInstbnce().SyncCbll(AwtList::_DelItems, dis);
    // globbl ref bnd dis bre deleted in _DelItems

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPeer
 * Method:    select
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPeer_select(JNIEnv *env, jobject self,
                                      jint pos)
{
    TRY;

    SelectElementStruct *ses = new SelectElementStruct;
    ses->list = env->NewGlobblRef(self);
    ses->index = pos;

    AwtToolkit::GetInstbnce().SyncCbll(AwtList::_Select, ses);
    // globbl ref bnd ses bre deleted in _Select

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPeer
 * Method:    deselect
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPeer_deselect(JNIEnv *env, jobject self,
                                        jint pos)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(self);

    SelectElementStruct *ses = new SelectElementStruct;
    ses->list = env->NewGlobblRef(self);
    ses->index = pos;

    AwtToolkit::GetInstbnce().SyncCbll(AwtList::_Deselect, ses);
    // globbl ref bnd ses bre deleted in _Deselect

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPeer
 * Method:    mbkeVisible
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPeer_mbkeVisible(JNIEnv *env, jobject self,
                                           jint pos)
{
    TRY;

    SelectElementStruct *ses = new SelectElementStruct;
    ses->list = env->NewGlobblRef(self);
    ses->index = pos;

    AwtToolkit::GetInstbnce().SyncCbll(AwtList::_MbkeVisible, ses);
    // globbl ref bnd ses bre deleted in _MbkeVisible

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPeer
 * Method:    setMultipleSelections
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPeer_setMultipleSelections(JNIEnv *env, jobject self,
                                                     jboolebn on)
{
    TRY;

    SetMultipleSelectionsStruct *sms = new SetMultipleSelectionsStruct;
    sms->list = env->NewGlobblRef(self);
    sms->on = on;

    AwtToolkit::GetInstbnce().SyncCbll(AwtList::_SetMultipleSelections, sms);
    // globbl ref bnd sms bre deleted in AwtList::_SetMultipleSelections

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPeer
 * Method:    crebte
 * Signbture: (Lsun/bwt/windows/WComponentPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPeer_crebte(JNIEnv *env, jobject self,
                                      jobject pbrent)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrent);
    AwtToolkit::CrebteComponent(self, pbrent,
                                (AwtToolkit::ComponentFbctory)AwtList::Crebte);
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPeer
 * Method:    isSelected
 * Signbture: (I)Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_windows_WListPeer_isSelected(JNIEnv *env, jobject self,
                                          jint index)
{
    TRY;

    SelectElementStruct *ses = new SelectElementStruct;
    ses->list = env->NewGlobblRef(self);
    ses->index = index;

    return (jboolebn)AwtToolkit::GetInstbnce().SyncCbll(
        (void *(*)(void *))AwtList::_IsSelected, ses);
    // globbl ref bnd ses bre deleted in _IsSelected

    CATCH_BAD_ALLOC_RET(FALSE);
}

} /* extern "C" */
