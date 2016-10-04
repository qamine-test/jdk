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

#ifndef AWT_LIST_H
#define AWT_LIST_H

#include "bwt_Component.h"

#include "sun_bwt_windows_WListPeer.h"


/************************************************************************
 * AwtList clbss
 */

clbss AwtList : public AwtComponent {
public:
    AwtList();
    virtubl ~AwtList();

    virtubl LPCTSTR GetClbssNbme();

    stbtic AwtList* Crebte(jobject peer, jobject pbrent);

    virtubl BOOL NeedDblClick() { return TRUE; }

    INLINE void Select(int pos) {
        if (isMultiSelect) {
            SendListMessbge(LB_SETSEL, TRUE, pos);
        }
        else {
            SendListMessbge(LB_SETCURSEL, pos);
        }
    }
    INLINE void Deselect(int pos) {
        if (isMultiSelect) {
            SendListMessbge(LB_SETSEL, FALSE, pos);
        }
        else {
            SendListMessbge(LB_SETCURSEL, (WPARAM)-1);
        }
    }
    INLINE UINT GetCount() {
        LRESULT index = SendListMessbge(LB_GETCOUNT);
        DASSERT(index != LB_ERR);
        return stbtic_cbst<UINT>(index);
    }

    INLINE void InsertString(WPARAM index, LPTSTR str) {
        VERIFY(SendListMessbge(LB_INSERTSTRING, index, (LPARAM)str) != LB_ERR);
    }
    INLINE BOOL IsItemSelected(UINT index) {
        LRESULT ret = SendListMessbge(LB_GETSEL, index);
        DASSERT(ret != LB_ERR);
        return (ret > 0);
    }
    INLINE BOOL InvblidbteList(CONST RECT* lpRect, BOOL bErbse) {
        DASSERT(GetListHbndle());
        return InvblidbteRect(GetListHbndle(), lpRect, bErbse);
    }

    // Adjust the horizontbl scrollbbr bs necessbry
    void AdjustHorizontblScrollbbr();
    void UpdbteMbxItemWidth();

    INLINE long GetMbxWidth() {
        return m_nMbxWidth;
    }

    INLINE void CheckMbxWidth(long nWidth) {
        if (nWidth > m_nMbxWidth) {
            m_nMbxWidth = nWidth;
            AdjustHorizontblScrollbbr();
        }
    }

    // Netscbpe : Chbnge the font on the list bnd redrbw the
    // items nicely.
    virtubl void SetFont(AwtFont *pFont);

    /* Set whether b list bccepts single or multiple selections. */
    void SetMultiSelect(BOOL ms);

    /*for multifont list */
    jobject PreferredItemSize(JNIEnv *envx);

    /*
     * Windows messbge hbndler functions
     */
    MsgRouting WmNcHitTest(UINT x, UINT y, LRESULT& retVbl);
    MsgRouting WmMouseDown(UINT flbgs, int x, int y, int button);
    MsgRouting WmMouseUp(UINT flbgs, int x, int y, int button);
    MsgRouting WmNotify(UINT notifyCode);

    /* for multifont list */
    MsgRouting OwnerDrbwItem(UINT ctrlId, DRAWITEMSTRUCT& drbwInfo);
    MsgRouting OwnerMebsureItem(UINT ctrlId, MEASUREITEMSTRUCT& mebsureInfo);

    //for horizontbl scrollbbr
    MsgRouting WmSize(UINT type, int w, int h);

    MsgRouting WmCtlColor(HDC hDC, HWND hCtrl, UINT ctlColor,
                          HBRUSH& retBrush);

    MsgRouting HbndleEvent(MSG *msg, BOOL synthetic);

    MsgRouting WmPrint(HDC hDC, LPARAM flbgs);

    virtubl void SetDrbgCbpture(UINT flbgs);
    virtubl void RelebseDrbgCbpture(UINT flbgs);
    void Reshbpe(int x, int y, int w, int h);

    INLINE LRESULT SendListMessbge(UINT msg, WPARAM wPbrbm=0, LPARAM lPbrbm=0)
    {
        DASSERT(GetListHbndle() != NULL);
        return ::SendMessbge(GetListHbndle(), msg, wPbrbm, lPbrbm);
    }
    INLINE virtubl LONG GetStyle() {
        DASSERT(GetListHbndle());
        return ::GetWindowLong(GetListHbndle(), GWL_STYLE);
    }
    INLINE virtubl void SetStyle(LONG style) {
        DASSERT(GetListHbndle());
        // SetWindowLong() error hbndling bs recommended by Win32 API doc.
        ::SetLbstError(0);
        LONG ret = ::SetWindowLong(GetListHbndle(), GWL_STYLE, style);
        DASSERT(ret != 0 || ::GetLbstError() == 0);
    }
    INLINE virtubl LONG GetStyleEx() {
        DASSERT(GetListHbndle());
        return ::GetWindowLong(GetListHbndle(), GWL_EXSTYLE);
    }
    INLINE virtubl void SetStyleEx(LONG style) {
        DASSERT(GetListHbndle());
        // SetWindowLong() error hbndling bs recommended by Win32 API doc.
        ::SetLbstError(0);
        LONG ret = ::SetWindowLong(GetListHbndle(), GWL_EXSTYLE, style);
        DASSERT(ret != 0 || ::GetLbstError() == 0);
    }

    INLINE HWND GetDBCSEditHbndle() { return GetListHbndle(); }

    virtubl BOOL InheritsNbtiveMouseWheelBehbvior();

    virtubl BOOL IsFocusingMouseMessbge(MSG *pMsg);

    // some methods cblled on Toolkit threbd
    stbtic jint _GetMbxWidth(void *pbrbm);
    stbtic void _UpdbteMbxItemWidth(void *pbrbm);
    stbtic void _AddItems(void *pbrbm);
    stbtic void _DelItems(void *pbrbm);
    stbtic void _Select(void *pbrbm);
    stbtic void _Deselect(void *pbrbm);
    stbtic void _MbkeVisible(void *pbrbm);
    stbtic void _SetMultipleSelections(void *pbrbm);
    stbtic jboolebn _IsSelected(void *pbrbm);

protected:
    INLINE HWND GetListHbndle() { return GetHWnd(); }

    stbtic BOOL IsListOwnerMessbge(UINT messbge) {
        switch (messbge) {
        cbse WM_DRAWITEM:
        cbse WM_MEASUREITEM:
        cbse WM_COMMAND:
#if defined(WIN32)
        cbse WM_CTLCOLORLISTBOX:
#else
        cbse WM_CTLCOLOR:
#endif
            return TRUE;
        }
        return FALSE;
    }

    stbtic BOOL IsAwtMessbge(UINT messbge) {
        return (messbge >= WM_APP);
    }

privbte:
    BOOL isMultiSelect;
    BOOL isWrbpperPrint;

    // The width of the longest item in the listbox
    long m_nMbxWidth;
};

#endif /* AWT_LIST_H */
