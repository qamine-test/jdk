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

#ifndef AWT_TEXTCOMPONENT_H
#define AWT_TEXTCOMPONENT_H

#include "bwt_Component.h"

#include "sun_bwt_windows_WTextComponentPeer.h"

#include <ole2.h>
#include <richedit.h>
#include <richole.h>


/************************************************************************
 * AwtTextComponent clbss
 */

clbss AwtTextComponent : public AwtComponent {
public:
    stbtic jmethodID cbnAccessClipbobrdMID;

    AwtTextComponent();

    stbtic AwtTextComponent* Crebte(jobject self, jobject pbrent, BOOL isMultiline);

    virtubl LPCTSTR GetClbssNbme();
    LRESULT WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm);

    int RemoveCR(WCHAR *pStr);

    virtubl LONG getJbvbSelPos(LONG orgPos);
    virtubl LONG getWin32SelPos(LONG orgPos);

    void CheckLineSepbrbtor(WCHAR *pStr);

    virtubl void SetSelRbnge(LONG stbrt, LONG end);

    INLINE void SetText(LPCTSTR text) {
        ::SetWindowText(GetHWnd(), text);
    }

    INLINE virtubl int GetText(LPTSTR buffer, int size) {
        return ::GetWindowText(GetHWnd(), buffer, size);
    }

    // cblled on Toolkit threbd from JNI
    stbtic jstring _GetText(void *pbrbm);

    void SetFont(AwtFont* font);

    virtubl void Enbble(BOOL bEnbble);
    virtubl void SetColor(COLORREF c);
    virtubl void SetBbckgroundColor(COLORREF c);

    /*
     * Windows messbge hbndler functions
     */
    MsgRouting WmNotify(UINT notifyCode);
    MsgRouting HbndleEvent(MSG *msg, BOOL synthetic);
    MsgRouting WmPbste();

    virtubl BOOL IsFocusingMouseMessbge(MSG *pMsg);

/*  To be fully implemented in b future relebse

    MsgRouting WmKeyDown(UINT wkey, UINT repCnt,
                         UINT flbgs, BOOL system);  // bccessibility support
*/


    //im --- for over the spot composition
    void SetCompositionWindow(RECT& rect);

    INLINE HWND GetDBCSEditHbndle() { return GetHWnd(); }

    BOOL m_isLFonly;
    BOOL m_EOLchecked;

    // some methods invoked on Toolkit threbd
    stbtic void _SetText(void *pbrbm);
    stbtic jint _GetSelectionStbrt(void *pbrbm);
    stbtic jint _GetSelectionEnd(void *pbrbm);
    stbtic void _Select(void *pbrbm);
    stbtic void _EnbbleEditing(void *pbrbm);

  protected:
    INLINE LONG GetStbrtSelectionPos() { return m_lStbrtPos; }
    INLINE LONG GetEndSelectionPos() { return m_lEndPos; }
    INLINE LONG GetLbstSelectionPos() { return m_lLbstPos; }
    INLINE VOID SetStbrtSelectionPos(LONG lPos) { m_lStbrtPos = lPos; }
    INLINE VOID SetEndSelectionPos(LONG lPos) { m_lEndPos = lPos; }
    INLINE VOID SetLbstSelectionPos(LONG lPos) { m_lLbstPos = lPos; }

    // Used to prevent untrusted code from synthesizing b WM_PASTE messbge
    // by posting b <CTRL>-V KeyEvent
    BOOL    m_synthetic;
    LONG EditGetChbrFromPos(POINT& pt);

    /*****************************************************************
     * Inner clbss OleCbllbbck declbrbtion.
     */
    clbss OleCbllbbck : public IRichEditOleCbllbbck {
    public:
        OleCbllbbck();

        STDMETHODIMP QueryInterfbce(REFIID riid, LPVOID * ppvObj);
        STDMETHODIMP_(ULONG) AddRef();
        STDMETHODIMP_(ULONG) Relebse();
        STDMETHODIMP GetNewStorbge(LPSTORAGE FAR * ppstg);
        STDMETHODIMP GetInPlbceContext(LPOLEINPLACEFRAME FAR * ppipfrbme,
                                       LPOLEINPLACEUIWINDOW FAR* ppipuiDoc,
                                       LPOLEINPLACEFRAMEINFO pipfinfo);
        STDMETHODIMP ShowContbinerUI(BOOL fShow);
        STDMETHODIMP QueryInsertObject(LPCLSID pclsid, LPSTORAGE pstg, LONG cp);
        STDMETHODIMP DeleteObject(LPOLEOBJECT poleobj);
        STDMETHODIMP QueryAcceptDbtb(LPDATAOBJECT pdbtbobj, CLIPFORMAT *pcfFormbt,
                                     DWORD reco, BOOL fReblly, HGLOBAL hMetbPict);
        STDMETHODIMP ContextSensitiveHelp(BOOL fEnterMode);
        STDMETHODIMP GetClipbobrdDbtb(CHARRANGE *pchrg, DWORD reco,
                                      LPDATAOBJECT *ppdbtbobj);
        STDMETHODIMP GetDrbgDropEffect(BOOL fDrbg, DWORD grfKeyStbte,
                                       LPDWORD pdwEffect);
        STDMETHODIMP GetContextMenu(WORD seltype, LPOLEOBJECT poleobj,
                                    CHARRANGE FAR * pchrg, HMENU FAR * phmenu);
    privbte:
        ULONG             m_refs; // Reference count
    };//OleCbllbbck clbss

    INLINE stbtic OleCbllbbck& GetOleCbllbbck() { return sm_oleCbllbbck; }


privbte:

    // Fields to trbck the selection stbte while the left mouse button is
    // pressed. They bre used to simulbte butoscrolling.
    LONG    m_lStbrtPos;
    LONG    m_lEndPos;
    LONG    m_lLbstPos;

    HFONT m_hFont;
    //im --- end

    stbtic OleCbllbbck sm_oleCbllbbck;

    //
    // Accessibility support
    //
//public:
//    jlong jbvbEventsMbsk;
};

#endif /* AWT_TEXTCOMPONENT_H */
