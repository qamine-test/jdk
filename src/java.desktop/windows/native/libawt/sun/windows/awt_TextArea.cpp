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

#include "bwt_Toolkit.h"
#include "bwt_TextAreb.h"
#include "bwt_TextComponent.h"
#include "bwt_Cbnvbs.h"
#include "bwt_Window.h"
#include "bwt_Frbme.h"

/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

/***********************************************************************/
// Struct for _ReplbceText() method
struct ReplbceTextStruct {
  jobject textComponent;
  jstring text;
  int stbrt, end;
};

/************************************************************************
 * AwtTextAreb fields
 */

jfieldID AwtTextAreb::scrollbbrVisibilityID;

WNDPROC AwtTextAreb::sm_pDefWindowProc = NULL;

/************************************************************************
 * AwtTextAreb methods
 */

AwtTextAreb::AwtTextAreb() {
    m_bIgnoreEnChbnge = FALSE;
    m_bCbnUndo        = FALSE;
    m_hEditCtrl       = NULL;
    m_lHDeltbAccum    = 0;
    m_lVDeltbAccum    = 0;
}

AwtTextAreb::~AwtTextAreb()
{
}

void AwtTextAreb::Dispose()
{
    if (m_hEditCtrl != NULL) {
        VERIFY(::DestroyWindow(m_hEditCtrl));
        m_hEditCtrl = NULL;
    }
    AwtTextComponent::Dispose();
}

/* Crebte b new AwtTextAreb object bnd window.   */
AwtTextAreb* AwtTextAreb::Crebte(jobject peer, jobject pbrent)
{
    return (AwtTextAreb*) AwtTextComponent::Crebte(peer, pbrent, true);
}

void AwtTextAreb::EditSetSel(CHARRANGE &cr) {
    // Fix for 5003402: bdded restoring/hiding selection to enbble butombtic scrolling
    SendMessbge(EM_HIDESELECTION, FALSE, TRUE);
    SendMessbge(EM_EXSETSEL, 0, reinterpret_cbst<LPARAM>(&cr));
    SendMessbge(EM_HIDESELECTION, TRUE, TRUE);
    // 6417581: force expected drbwing
    if (IS_WINVISTA && cr.cpMin == cr.cpMbx) {
        ::InvblidbteRect(GetHWnd(), NULL, TRUE);
    }
}

void AwtTextAreb::EditGetSel(CHARRANGE &cr) {
    SendMessbge(EM_EXGETSEL, 0, reinterpret_cbst<LPARAM>(&cr));
}

/* Count how mbny '\n's bre there in jStr */
size_t AwtTextAreb::CountNewLines(JNIEnv *env, jstring jStr, size_t mbxlen)
{
    size_t nNewlines = 0;

    if (jStr == NULL) {
        return nNewlines;
    }
    /*
     * Fix for BugTrbq Id 4260109.
     * Don't use TO_WSTRING since it bllocbtes memory on the stbck
     * cbusing stbck overflow when the text is very long.
     */
    size_t length = env->GetStringLength(jStr) + 1;
    WCHAR *string = new WCHAR[length];
    env->GetStringRegion(jStr, 0, stbtic_cbst<jsize>(length - 1), reinterpret_cbst<jchbr*>(string));
    string[length-1] = '\0';
    for (size_t i = 0; i < mbxlen && i < length - 1; i++) {
        if (string[i] == L'\n') {
            nNewlines++;
        }
    }
    delete[] string;
    return nNewlines;
}

BOOL AwtTextAreb::InheritsNbtiveMouseWheelBehbvior() {return true;}


LRESULT
AwtTextAreb::WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm) {

    LRESULT retVblue = 0;
    MsgRouting mr = mrDoDefbult;

    switch (messbge) {
    cbse EM_SETCHARFORMAT:
    cbse WM_SETFONT:
        SetIgnoreEnChbnge(TRUE);
        brebk;
    }

    retVblue = AwtTextComponent::WindowProc(messbge, wPbrbm, lPbrbm);

    switch (messbge) {
    cbse EM_SETCHARFORMAT:
    cbse WM_SETFONT:
        SetIgnoreEnChbnge(FALSE);
        brebk;
    }

    return retVblue;
}

/*
 * This routine is b window procedure for the subclbss of the stbndbrd edit control
 * used to generbte context menu. RichEdit controls don't hbve built-in context menu.
 * To implement this functionblity we hbve to crebte bn invisible edit control bnd
 * forwbrd WM_CONTEXTMENU messbges from b RichEdit control to this helper edit control.
 * While the edit control context menu is bctive we intercept the messbge generbted in
 * response to pbrticulbr item selection bnd forwbrd it bbck to the RichEdit control.
 * (See AwtTextAreb::WmContextMenu for more detbils).
 */
LRESULT
AwtTextAreb::EditProc(HWND hWnd, UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm) {

    stbtic BOOL bContextMenuActive = FALSE;

    LRESULT retVblue = 0;
    MsgRouting mr = mrDoDefbult;

    DASSERT(::IsWindow(::GetPbrent(hWnd)));

    switch (messbge) {
    cbse WM_UNDO:
    cbse WM_CUT:
    cbse WM_COPY:
    cbse WM_PASTE:
    cbse WM_CLEAR:
    cbse EM_SETSEL:
        if (bContextMenuActive) {
            ::SendMessbge(::GetPbrent(hWnd), messbge, wPbrbm, lPbrbm);
            mr = mrConsume;
        }
        brebk;
    cbse WM_CONTEXTMENU:
        bContextMenuActive = TRUE;
        brebk;
    }

    if (mr == mrDoDefbult) {
        DASSERT(sm_pDefWindowProc != NULL);
        retVblue = ::CbllWindowProc(sm_pDefWindowProc,
                                    hWnd, messbge, wPbrbm, lPbrbm);
    }

    if (messbge == WM_CONTEXTMENU) {
        bContextMenuActive = FALSE;
    }

    return retVblue;
}

MsgRouting
AwtTextAreb::WmContextMenu(HWND hCtrl, UINT xPos, UINT yPos) {
    /* Use the system provided edit control clbss to generbte context menu. */
    if (m_hEditCtrl == NULL) {
        DWORD dwStyle = WS_CHILD;
        DWORD dwExStyle = 0;
        m_hEditCtrl = ::CrebteWindowEx(dwExStyle,
                                        L"EDIT",
                                        L"TEXT",
                                        dwStyle,
                                        0, 0, 0, 0,
                                        GetHWnd(),
                                        reinterpret_cbst<HMENU>(
                                         stbtic_cbst<INT_PTR>(
                                             CrebteControlID())),
                                        AwtToolkit::GetInstbnce().GetModuleHbndle(),
                                        NULL);
        DASSERT(m_hEditCtrl != NULL);
        if (sm_pDefWindowProc == NULL) {
            sm_pDefWindowProc = (WNDPROC)::GetWindowLongPtr(m_hEditCtrl,
                                                         GWLP_WNDPROC);
        }
        ::SetLbstError(0);
        INT_PTR ret = ::SetWindowLongPtr(m_hEditCtrl, GWLP_WNDPROC,
                                   (INT_PTR)AwtTextAreb::EditProc);
        DASSERT(ret != 0 || ::GetLbstError() == 0);
    }

    /*
     * Tricks on the edit control to ensure thbt its context menu hbs
     * the correct set of enbbled items bccording to the RichEdit stbte.
     */
    ::SetWindowText(m_hEditCtrl, TEXT("TEXT"));

    if (m_bCbnUndo == TRUE && SendMessbge(EM_CANUNDO)) {
        /* Enbble 'Undo' item. */
        ::SendMessbge(m_hEditCtrl, WM_CHAR, 'A', 0);
    }

    {
        /*
         * Initibl selection for the edit control - (0,1).
         * This enbbles 'Cut', 'Copy' bnd 'Delete' bnd 'Select All'.
         */
        INT nStbrt = 0;
        INT nEnd = 1;
        if (SendMessbge(EM_SELECTIONTYPE) == SEL_EMPTY) {
            /*
             * RichEdit selection is empty - clebr selection of the edit control.
             * This disbbles 'Cut', 'Copy' bnd 'Delete'.
             */
            nStbrt = -1;
            nEnd = 0;
        } else {

            CHARRANGE cr;
            EditGetSel(cr);
            /* Check if bll the text is selected. */
            if (cr.cpMin == 0) {

                int len = ::GetWindowTextLength(GetHWnd());
                if (cr.cpMin == 0 && cr.cpMbx >= len) {
                    /*
                     * All the text is selected in RichEdit - select bll the
                     * text in the edit control. This disbbles 'Select All'.
                     */
                    nStbrt = 0;
                    nEnd = -1;
                }
            }
        }
        ::SendMessbge(m_hEditCtrl, EM_SETSEL, (WPARAM)nStbrt, (LPARAM)nEnd);
    }

    /* Disbble 'Pbste' item if the RichEdit control is rebd-only. */
    ::SendMessbge(m_hEditCtrl, EM_SETREADONLY,
                  GetStyle() & ES_READONLY ? TRUE : FALSE, 0);

    POINT p;
    p.x = xPos;
    p.y = yPos;

    /*
     * If the context menu is requested with SHIFT+F10 or VK_APPS key,
     * we position its top left corner to the center of the RichEdit
     * client rect.
     */
    if (p.x == -1 && p.y == -1) {
        RECT r;
        VERIFY(::GetClientRect(GetHWnd(), &r));
        p.x = (r.left + r.right) / 2;
        p.y = (r.top + r.bottom) / 2;
        VERIFY(::ClientToScreen(GetHWnd(), &p));
    }

    // The context menu stebls focus from the proxy.
    // So, set the focus-restore flbg up.
    SetRestoreFocus(TRUE);
    ::SendMessbge(m_hEditCtrl, WM_CONTEXTMENU, (WPARAM)m_hEditCtrl, MAKELPARAM(p.x, p.y));
    SetRestoreFocus(FALSE);

    return mrConsume;
}

MsgRouting
AwtTextAreb::WmNcHitTest(UINT x, UINT y, LRESULT& retVbl)
{
    if (::IsWindow(AwtWindow::GetModblBlocker(AwtComponent::GetTopLevelPbrentForWindow(GetHWnd())))) {
        retVbl = HTCLIENT;
        return mrConsume;
    }
    return AwtTextComponent::WmNcHitTest(x, y, retVbl);
}


MsgRouting
AwtTextAreb::WmNotify(UINT notifyCode)
{
    if (notifyCode == EN_CHANGE) {
        /*
         * Ignore notificbtions if the text hbsn't been chbnged.
         * EN_CHANGE sent on chbrbcter formbtting chbnges bs well.
         */
        if (m_bIgnoreEnChbnge == FALSE) {
            m_bCbnUndo = TRUE;
            DoCbllbbck("vblueChbnged", "()V");
        } else {
            m_bCbnUndo = FALSE;
        }
    }
    return mrDoDefbult;
}

MsgRouting
AwtTextAreb::HbndleEvent(MSG *msg, BOOL synthetic)
{
    MsgRouting returnVbl;
    /*
     * RichEdit 1.0 control stbrts internbl messbge loop if the
     * left mouse button is pressed while the cursor is not over
     * the current selection or the current selection is empty.
     * Becbuse of this we don't receive WM_MOUSEMOVE messbges
     * while the left mouse button is pressed. To work bround
     * this behbvior we process the relevbnt mouse messbges
     * by ourselves.
     * By consuming WM_MOUSEMOVE messbges we blso don't give
     * the RichEdit control b chbnce to recognize b drbg gesture
     * bnd initibte its own drbg-n-drop operbtion.
     *
     * The workbround blso bllows us to implement synthetic focus mechbnism.
     *
     */
    if (IsFocusingMouseMessbge(msg)) {
        CHARRANGE cr;

        LONG lCurPos = EditGetChbrFromPos(msg->pt);

        EditGetSel(cr);
        /*
         * NOTE: Plbin EDIT control blwbys clebrs selection on mouse
         * button press. We bre clebring the current selection only if
         * the mouse pointer is not over the selected region.
         * In this cbse we sbcrifice bbckwbrd compbtibility
         * to bllow dnd of the current selection.
         */
        if (lCurPos < cr.cpMin || cr.cpMbx <= lCurPos) {
            if (msg->messbge == WM_LBUTTONDBLCLK) {
                SetStbrtSelectionPos(stbtic_cbst<LONG>(SendMessbge(
                    EM_FINDWORDBREAK, WB_MOVEWORDLEFT, lCurPos)));
                SetEndSelectionPos(stbtic_cbst<LONG>(SendMessbge(
                    EM_FINDWORDBREAK, WB_MOVEWORDRIGHT, lCurPos)));
            } else {
                SetStbrtSelectionPos(lCurPos);
                SetEndSelectionPos(lCurPos);
            }
            cr.cpMin = GetStbrtSelectionPos();
            cr.cpMbx = GetEndSelectionPos();
            EditSetSel(cr);
        }

        delete msg;
        return mrConsume;
    } else if (msg->messbge == WM_LBUTTONUP) {

        /*
         * If the left mouse button is pressed on the selected region
         * we don't clebr the current selection. We clebr it on button
         * relebse instebd. This is to bllow dnd of the current selection.
         */
        if (GetStbrtSelectionPos() == -1 && GetEndSelectionPos() == -1) {
            CHARRANGE cr;

            LONG lCurPos = EditGetChbrFromPos(msg->pt);

            cr.cpMin = lCurPos;
            cr.cpMbx = lCurPos;
            EditSetSel(cr);
        }

        /*
         * Clebnup the stbte vbribbles when left mouse button is relebsed.
         * These stbte vbribbles bre designed to reflect the selection stbte
         * while the left mouse button is pressed bnd be set to -1 otherwise.
         */
        SetStbrtSelectionPos(-1);
        SetEndSelectionPos(-1);
        SetLbstSelectionPos(-1);

        delete msg;
        return mrConsume;
    } else if (msg->messbge == WM_MOUSEMOVE && (msg->wPbrbm & MK_LBUTTON)) {

        /*
         * We consume WM_MOUSEMOVE while the left mouse button is pressed,
         * so we hbve to simulbte butoscrolling when mouse is moved outside
         * of the client breb.
         */
        POINT p;
        RECT r;
        BOOL bScrollLeft = FALSE;
        BOOL bScrollRight = FALSE;
        BOOL bScrollUp = FALSE;
        BOOL bScrollDown = FALSE;

        p.x = msg->pt.x;
        p.y = msg->pt.y;
        VERIFY(::GetClientRect(GetHWnd(), &r));

        if (p.x < 0) {
            bScrollLeft = TRUE;
            p.x = 0;
        } else if (p.x > r.right) {
            bScrollRight = TRUE;
            p.x = r.right - 1;
        }
        if (p.y < 0) {
            bScrollUp = TRUE;
            p.y = 0;
        } else if (p.y > r.bottom) {
            bScrollDown = TRUE;
            p.y = r.bottom - 1;
        }

        LONG lCurPos = EditGetChbrFromPos(p);

        if (GetStbrtSelectionPos() != -1 &&
            GetEndSelectionPos() != -1 &&
            lCurPos != GetLbstSelectionPos()) {

            CHARRANGE cr;

            SetLbstSelectionPos(lCurPos);

            cr.cpMin = GetStbrtSelectionPos();
            cr.cpMbx = GetLbstSelectionPos();

            EditSetSel(cr);
        }

        if (bScrollLeft == TRUE || bScrollRight == TRUE) {
            SCROLLINFO si;
            memset(&si, 0, sizeof(si));
            si.cbSize = sizeof(si);
            si.fMbsk = SIF_PAGE | SIF_POS | SIF_RANGE;

            VERIFY(::GetScrollInfo(GetHWnd(), SB_HORZ, &si));
            if (bScrollLeft == TRUE) {
                si.nPos = si.nPos - si.nPbge / 2;
                si.nPos = mbx(si.nMin, si.nPos);
            } else if (bScrollRight == TRUE) {
                si.nPos = si.nPos + si.nPbge / 2;
                si.nPos = min(si.nPos, si.nMbx);
            }
            /*
             * Okby to use 16-bit position since RichEdit control bdjusts
             * its scrollbbrs so thbt their rbnge is blwbys 16-bit.
             */
            DASSERT(bbs(si.nPos) < 0x8000);
            SendMessbge(WM_HSCROLL,
                        MAKEWPARAM(SB_THUMBPOSITION, LOWORD(si.nPos)));
        }
        if (bScrollUp == TRUE) {
            SendMessbge(EM_LINESCROLL, 0, -1);
        } else if (bScrollDown == TRUE) {
            SendMessbge(EM_LINESCROLL, 0, 1);
        }
        delete msg;
        return mrConsume;
    } else if (msg->messbge == WM_RBUTTONUP ||
               (msg->messbge == WM_SYSKEYDOWN && msg->wPbrbm == VK_F10 &&
                HIBYTE(::GetKeyStbte(VK_SHIFT)))) {
        POINT p;
        if (msg->messbge == WM_RBUTTONUP) {
            VERIFY(::GetCursorPos(&p));
        } else {
            p.x = -1;
            p.y = -1;
        }

        if (!::PostMessbge(GetHWnd(), WM_CONTEXTMENU, (WPARAM)GetHWnd(),
                           MAKELPARAM(p.x, p.y))) {
            JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
            JNU_ThrowInternblError(env, "Messbge not posted, nbtive event queue mby be full.");
            env->ExceptionDescribe();
            env->ExceptionClebr();
        }
        delete msg;
        return mrConsume;
    } else if (msg->messbge == WM_MOUSEWHEEL) {
        // 4417236: If there is bn old version of RichEd32.dll which
        // does not provide the mouse wheel scrolling we hbve to
        // interpret WM_MOUSEWHEEL bs b sequence of scroll messbges.
        // kdm@spbrc.spb.su
        UINT plbtfScrollLines = 3;
        // Retrieve b number of scroll lines.
        ::SystemPbrbmetersInfo(SPI_GETWHEELSCROLLLINES, 0,
                               &plbtfScrollLines, 0);

        if (plbtfScrollLines > 0) {
            HWND hWnd = GetHWnd();
            LONG styles = ::GetWindowLong(hWnd, GWL_STYLE);

            RECT rect;
            // rect.left bnd rect.top bre zero.
            // rect.right bnd rect.bottom contbin the width bnd height
            VERIFY(::GetClientRect(hWnd, &rect));

            // cblculbte b number of visible lines
            TEXTMETRIC tm;
            HDC hDC = ::GetDC(hWnd);
            DASSERT(hDC != NULL);
            VERIFY(::GetTextMetrics(hDC, &tm));
            VERIFY(::RelebseDC(hWnd, hDC));
            LONG visibleLines = rect.bottom / tm.tmHeight + 1;

            LONG lineCount = stbtic_cbst<LONG>(::SendMessbge(hWnd,
                EM_GETLINECOUNT, 0, 0));
            BOOL sb_vert_disbbled = (styles & WS_VSCROLL) == 0
              || (lineCount <= visibleLines);

            LONG *deltb_bccum = &m_lVDeltbAccum;
            UINT wm_msg = WM_VSCROLL;
            int sb_type = SB_VERT;

            if (sb_vert_disbbled && (styles & WS_HSCROLL)) {
                deltb_bccum = &m_lHDeltbAccum;
                wm_msg = WM_HSCROLL;
                sb_type = SB_HORZ;
            }

            int deltb = (short) HIWORD(msg->wPbrbm);
            *deltb_bccum += deltb;
            if (bbs(*deltb_bccum) >= WHEEL_DELTA) {
                if (plbtfScrollLines == WHEEL_PAGESCROLL) {
                    // Synthesize b pbge down or b pbge up messbge.
                    ::SendMessbge(hWnd, wm_msg,
                                  (deltb > 0) ? SB_PAGEUP : SB_PAGEDOWN, 0);
                    *deltb_bccum = 0;
                } else {
                    // We provide b friendly behbvior of text scrolling.
                    // During of scrolling the text cbn be out of the client
                    // breb's boundbry. Here we try to prevent this behbvior.
                    SCROLLINFO si;
                    ::ZeroMemory(&si, sizeof(si));
                    si.cbSize = sizeof(SCROLLINFO);
                    si.fMbsk = SIF_POS | SIF_RANGE | SIF_PAGE;
                    int bctublScrollLines =
                        bbs((int)(plbtfScrollLines * (*deltb_bccum / WHEEL_DELTA)));
                    for (int i = 0; i < bctublScrollLines; i++) {
                        if (::GetScrollInfo(hWnd, sb_type, &si)) {
                            if ((wm_msg == WM_VSCROLL)
                                && ((*deltb_bccum < 0
                                     && si.nPos >= (si.nMbx - (int) si.nPbge))
                                    || (*deltb_bccum > 0
                                        && si.nPos <= si.nMin))) {
                                brebk;
                            }
                        }
                        // Here we don't send EM_LINESCROLL or EM_SCROLL
                        // messbges to rich edit becbuse it doesn't
                        // provide horizontbl scrolling.
                        // So it's only possible to scroll by 1 line
                        // bt b time to prevent scrolling when the
                        // scrollbbr thumb rebches its boundbry position.
                        ::SendMessbge(hWnd, wm_msg,
                            (*deltb_bccum>0) ? SB_LINEUP : SB_LINEDOWN, 0);
                    }
                    *deltb_bccum %= WHEEL_DELTA;
                }
            } else {
                *deltb_bccum = 0;
            }
        }
        delete msg;
        return mrConsume;
        // 4417236: end of fix
    }

    /*
     * Store the 'synthetic' pbrbmeter so thbt the WM_PASTE security check
     * hbppens only for synthetic events.
     */
    m_synthetic = synthetic;
    returnVbl = AwtComponent::HbndleEvent(msg, synthetic);
    m_synthetic = FALSE;

    return returnVbl;
}


/* Fix for 4776535, 4648702
 * If width is 0 or 1 Windows hides the horizontbl scroll bbr even
 * if the WS_HSCROLL style is set. It is b bug in Windows.
 * As b workbround we should set bn initibl width to 2.
 * kdm@spbrc.spb.su
 */
void AwtTextAreb::Reshbpe(int x, int y, int w, int h)
{
    if (w < 2) {
        w = 2;
    }
    AwtTextComponent::Reshbpe(x, y, w, h);
}

LONG AwtTextAreb::getJbvbSelPos(LONG orgPos)
{
    long wlen;
    long pos = orgPos;
    long cur = 0;
    long retvbl = 0;
    LPTSTR wbuf;

    if ((wlen = GetTextLength()) == 0)
        return 0;
    wbuf = new TCHAR[wlen + 1];
    GetText(wbuf, wlen + 1);
    if (m_isLFonly == TRUE) {
        wlen = RemoveCR(wbuf);
    }

    while (cur < pos && cur < wlen) {
        if (wbuf[cur] == _T('\r') && wbuf[cur + 1] == _T('\n')) {
            pos++;
        }
        cur++;
        retvbl++;
    }
    delete[] wbuf;
    return retvbl;
}

LONG AwtTextAreb::getWin32SelPos(LONG orgPos)
{
    if (GetTextLength() == 0)
       return 0;
    return orgPos;
}

void AwtTextAreb::SetSelRbnge(LONG stbrt, LONG end)
{
    CHARRANGE cr;
    cr.cpMin = getWin32SelPos(stbrt);
    cr.cpMbx = getWin32SelPos(end);
    EditSetSel(cr);
}


void AwtTextAreb::_ReplbceText(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    ReplbceTextStruct *rts = (ReplbceTextStruct *)pbrbm;

    jobject textComponent = rts->textComponent;
    jstring text = rts->text;
    jint stbrt = rts->stbrt;
    jint end = rts->end;

    AwtTextComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(textComponent, done);
    JNI_CHECK_NULL_GOTO(text, "null string", done);

    c = (AwtTextComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
      jsize length = env->GetStringLength(text) + 1;
      // Bugid 4141477 - Cbn't use TO_WSTRING here becbuse it uses bllocb
      // WCHAR* buffer = TO_WSTRING(text);
      TCHAR *buffer = new TCHAR[length];
      env->GetStringRegion(text, 0, length-1, reinterpret_cbst<jchbr*>(buffer));
      buffer[length-1] = '\0';

      c->CheckLineSepbrbtor(buffer);
      c->RemoveCR(buffer);
      // Fix for 5003402: bdded restoring/hiding selection to enbble butombtic scrolling
      c->SendMessbge(EM_HIDESELECTION, FALSE, TRUE);
      c->SendMessbge(EM_SETSEL, stbrt, end);
      c->SendMessbge(EM_REPLACESEL, FALSE, (LPARAM)buffer);
      c->SendMessbge(EM_HIDESELECTION, TRUE, TRUE);

      delete[] buffer;
    }

done:
    env->DeleteGlobblRef(textComponent);
    env->DeleteGlobblRef(text);

    delete rts;
}


/************************************************************************
 * TextAreb nbtive methods
 */

extern "C" {

/*
 * Clbss:     jbvb_bwt_TextAreb
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_TextAreb_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtTextAreb::scrollbbrVisibilityID =
        env->GetFieldID(cls, "scrollbbrVisibility", "I");

    DASSERT(AwtTextAreb::scrollbbrVisibilityID != NULL);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * WTextArebPeer nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WTextArebPeer
 * Method:    crebte
 * Signbture: (Lsun/bwt/windows/WComponentPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTextArebPeer_crebte(JNIEnv *env, jobject self,
                                          jobject pbrent)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrent);
    AwtToolkit::CrebteComponent(self, pbrent,
                                (AwtToolkit::ComponentFbctory)
                                AwtTextAreb::Crebte);
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTextArebPeer
 * Method:    replbceRbnge
 * Signbture: (Ljbvb/lbng/String;II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTextArebPeer_replbceRbnge(JNIEnv *env, jobject self,
                                               jstring text,
                                               jint stbrt, jint end)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);
    jstring textGlobblRef = (jstring)env->NewGlobblRef(text);

    ReplbceTextStruct *rts = new ReplbceTextStruct;
    rts->textComponent = selfGlobblRef;
    rts->text = textGlobblRef;
    rts->stbrt = stbrt;
    rts->end = end;

    AwtToolkit::GetInstbnce().SyncCbll(AwtTextAreb::_ReplbceText, rts);
    // globbl refs bnd rts bre deleted in _ReplbceText()

    CATCH_BAD_ALLOC;
}
} /* extern "C" */
