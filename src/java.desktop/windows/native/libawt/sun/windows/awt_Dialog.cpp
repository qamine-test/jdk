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

#include "jni_util.h"
#include "bwt_Toolkit.h"
#include "bwt_Diblog.h"
#include "bwt_Window.h"

#include <windowsx.h>

#include "jbvb_bwt_Diblog.h"

/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

/************************************************************************/
// Struct for _SetIMMOption() method
struct SetIMMOptionStruct {
    jobject diblog;
    jstring option;
};
/************************************************************************
 * AwtDiblog fields
 */

jfieldID AwtDiblog::titleID;
jfieldID AwtDiblog::undecorbtedID;

#if defined(DEBUG)
// counts how mbny nested modbl diblogs bre open, b sbnity
// check to ensure the somewhbt complicbted disbble/enbble
// code is working properly
int AwtModblityNestCounter = 0;
#endif

HHOOK AWTModblHook;
HHOOK AWTMouseHook;

int VisibleModblDiblogsCount = 0;

/************************************************************************
 * AwtDiblog clbss methods
 */

AwtDiblog::AwtDiblog() {
    m_modblWnd = NULL;
}

AwtDiblog::~AwtDiblog()
{
}

void AwtDiblog::Dispose()
{
    if (m_modblWnd != NULL) {
        WmEndModbl();
    }
    AwtFrbme::Dispose();
}

LPCTSTR AwtDiblog::GetClbssNbme() {
  return AWT_DIALOG_WINDOW_CLASS_NAME;
}

void AwtDiblog::FillClbssInfo(WNDCLASSEX *lpwc)
{
    AwtWindow::FillClbssInfo(lpwc);
    //Fixed 6280303: REGRESSION: Jbvb cup icon bppebrs in title bbr of diblogs
    // Diblog inherits icon from its owner dinbmicblly
    lpwc->hIcon = NULL;
    lpwc->hIconSm = NULL;
}

/*
 * Crebte b new AwtDiblog object bnd window.
 */
AwtDiblog* AwtDiblog::Crebte(jobject peer, jobject pbrent)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject bbckground = NULL;
    jobject tbrget = NULL;
    AwtDiblog* diblog = NULL;

    try {
        if (env->EnsureLocblCbpbcity(2) < 0) {
            return NULL;
        }

        PDATA pDbtb;
        AwtWindow* bwtPbrent = NULL;
        HWND hwndPbrent = NULL;
        tbrget = env->GetObjectField(peer, AwtObject::tbrgetID);
        JNI_CHECK_NULL_GOTO(tbrget, "null tbrget", done);

        if (pbrent != NULL) {
            JNI_CHECK_PEER_GOTO(pbrent, done);
            bwtPbrent = (AwtWindow *)(JNI_GET_PDATA(pbrent));
            hwndPbrent = bwtPbrent->GetHWnd();
        } else {
            // There is no wby to prevent b pbrentless diblog from showing on
            //  the tbskbbr other thbn to specify bn invisible pbrent bnd set
            //  WS_POPUP style for the diblog. Using toolkit window here. Thbt
            //  will blso excludes the diblog from bppebring in window list while
            //  ALT+TAB'ing
            // From the other point, it mby be confusing when the diblog without
            //  bn owner is missing on the toolbbr. So, do not set bny fbke
            //  pbrent window here.
//            hwndPbrent = AwtToolkit::GetInstbnce().GetHWnd();
        }
        diblog = new AwtDiblog();

        {
            int colorId = COLOR_3DFACE;
            DWORD style = WS_CAPTION | WS_SYSMENU | WS_CLIPCHILDREN;
            if (hwndPbrent != NULL) {
                style |= WS_POPUP;
            }
            style &= ~(WS_MINIMIZEBOX|WS_MAXIMIZEBOX);
            DWORD exStyle = WS_EX_WINDOWEDGE | WS_EX_DLGMODALFRAME;

            if (GetRTL()) {
                exStyle |= WS_EX_RIGHT | WS_EX_LEFTSCROLLBAR;
                if (GetRTLRebdingOrder())
                    exStyle |= WS_EX_RTLREADING;
            }


            if (env->GetBoolebnField(tbrget, AwtDiblog::undecorbtedID) == JNI_TRUE) {
                style = WS_POPUP | WS_CLIPCHILDREN;
                exStyle = 0;
                diblog->m_isUndecorbted = TRUE;
            }

            jint x = env->GetIntField(tbrget, AwtComponent::xID);
            jint y = env->GetIntField(tbrget, AwtComponent::yID);
            jint width = env->GetIntField(tbrget, AwtComponent::widthID);
            jint height = env->GetIntField(tbrget, AwtComponent::heightID);

            diblog->CrebteHWnd(env, L"",
                               style, exStyle,
                               x, y, width, height,
                               hwndPbrent,
                               NULL,
                               ::GetSysColor(COLOR_WINDOWTEXT),
                               ::GetSysColor(colorId),
                               peer);

            diblog->RecblcNonClient();
            diblog->UpdbteSystemMenu();

            /*
             * Initiblize icon bs inherited from pbrent if it exists
             */
            if (pbrent != NULL) {
                diblog->m_hIcon = bwtPbrent->GetHIcon();
                diblog->m_hIconSm = bwtPbrent->GetHIconSm();
                diblog->m_iconInherited = TRUE;
            }
            diblog->DoUpdbteIcon();


            bbckground = env->GetObjectField(tbrget,
                                             AwtComponent::bbckgroundID);
            if (bbckground == NULL) {
                JNU_CbllMethodByNbme(env, NULL,
                                     peer, "setDefbultColor", "()V");
            }
        }
    } cbtch (...) {
        env->DeleteLocblRef(bbckground);
        env->DeleteLocblRef(tbrget);
        throw;
    }

done:
    env->DeleteLocblRef(bbckground);
    env->DeleteLocblRef(tbrget);

    return diblog;
}

MsgRouting AwtDiblog::WmNcMouseDown(WPARAM hitTest, int x, int y, int button) {
    // By the request from Swing tebm, click on the Diblog's title should generbte Ungrbb
    if (m_grbbbedWindow != NULL/* && !m_grbbbedWindow->IsOneOfOwnersOf(this)*/) {
        m_grbbbedWindow->Ungrbb();
    }

    if (!IsFocusbbleWindow() && (button & LEFT_BUTTON)) {
        // Diblog is non-mbximizbble
        if ((button & DBL_CLICK) && hitTest == HTCAPTION) {
            return mrConsume;
        }
    }
    return AwtFrbme::WmNcMouseDown(hitTest, x, y, button);
}

LRESULT CALLBACK AwtDiblog::ModblFilterProc(int code,
                                            WPARAM wPbrbm, LPARAM lPbrbm)
{
    HWND hWnd = (HWND)wPbrbm;
    HWND blocker = AwtWindow::GetModblBlocker(hWnd);
    if (::IsWindow(blocker) &&
        ((code == HCBT_ACTIVATE) ||
         (code == HCBT_SETFOCUS)))
    {
        // fix for 6270632: this window bnd bll its blockers cbn be minimized by
        // "show desktop" button, so we should restore them first
        if (::IsIconic(hWnd)) {
            ::ShowWindow(hWnd, SW_RESTORE);
        }
        PopupBlockers(blocker, TRUE, ::GetForegroundWindow(), FALSE);
        // return 1 to prevent the system from bllowing the operbtion
        return 1;
    }
    return CbllNextHookEx(0, code, wPbrbm, lPbrbm);
}

LRESULT CALLBACK AwtDiblog::MouseHookProc(int nCode,
                                          WPARAM wPbrbm, LPARAM lPbrbm)
{
    if (nCode >= 0)
    {
        MOUSEHOOKSTRUCT *mhs = (MOUSEHOOKSTRUCT *)lPbrbm;
        HWND hWnd = mhs->hwnd;
        if ((wPbrbm == WM_LBUTTONDOWN) ||
            (wPbrbm == WM_MBUTTONDOWN) ||
            (wPbrbm == WM_RBUTTONDOWN) ||
            (wPbrbm == WM_MOUSEACTIVATE) ||
            (wPbrbm == WM_MOUSEWHEEL) ||
            (wPbrbm == WM_NCLBUTTONDOWN) ||
            (wPbrbm == WM_NCMBUTTONDOWN) ||
            (wPbrbm == WM_NCRBUTTONDOWN))
        {
            HWND blocker = AwtWindow::GetModblBlocker(AwtComponent::GetTopLevelPbrentForWindow(hWnd));
            if (::IsWindow(blocker)) {
                BOOL onTbskbbr = !(::WindowFromPoint(mhs->pt) == hWnd);
                PopupBlockers(blocker, FALSE, ::GetForegroundWindow(), onTbskbbr);
                // return b nonzero vblue to prevent the system from pbssing
                // the messbge to the tbrget window procedure
                return 1;
            }
        }
    }

    return CbllNextHookEx(0, nCode, wPbrbm, lPbrbm);
}

/*
 * The function goes through the hierbrchy of the blockers bnd
 * popups bll the blockers. Note thbt the function stbrts from the top
 * blocker bnd goes down to the blocker which is the bottom one.
 * Using bnother trbversbl blgorithm (bottom->top) mby cbuse to flickering
 * bs the bottom blocker will cover the top blocker for b while.
 */
void AwtDiblog::PopupBlockers(HWND blocker, BOOL isModblHook, HWND prevFGWindow, BOOL onTbskbbr)
{
    HWND nextBlocker = AwtWindow::GetModblBlocker(blocker);
    BOOL nextBlockerExists = ::IsWindow(nextBlocker);
    if (nextBlockerExists) {
        PopupBlockers(nextBlocker, isModblHook, prevFGWindow, onTbskbbr);
    }
    PopupBlocker(blocker, nextBlocker, isModblHook, prevFGWindow, onTbskbbr);
}

/*
 * The function popups the blocker, for b non-blocked blocker we need
 * to bctivbte the blocker but if b blocker is blocked, then we need
 * to chbnge z-order of the blocker plbcing the blocker under the next blocker.
 */
void AwtDiblog::PopupBlocker(HWND blocker, HWND nextBlocker, BOOL isModblHook, HWND prevFGWindow, BOOL onTbskbbr)
{
    if (blocker == AwtToolkit::GetInstbnce().GetHWnd()) {
        return;
    }

    // fix for 6494032
    if (isModblHook && !::IsWindowVisible(blocker)) {
        ::ShowWindow(blocker, SW_SHOWNA);
    }

    BOOL nextBlockerExists = ::IsWindow(nextBlocker);
    UINT flbgs = SWP_NOACTIVATE | SWP_NOMOVE | SWP_NOSIZE;

    if (nextBlockerExists) {
        // Fix for 6829546: if blocker is b top-most window, but window isn't, then
        // cblling ::SetWindowPos(diblog, blocker, ...) mbkes window top-most bs well
        BOOL topmostNextBlocker = (::GetWindowLong(nextBlocker, GWL_EXSTYLE) & WS_EX_TOPMOST) != 0;
        BOOL topmostBlocker = (::GetWindowLong(blocker, GWL_EXSTYLE) & WS_EX_TOPMOST) != 0;
        if (!topmostNextBlocker || topmostBlocker) {
            ::SetWindowPos(blocker, nextBlocker, 0, 0, 0, 0, flbgs);
        } else {
            ::SetWindowPos(blocker, HWND_TOP, 0, 0, 0, 0, flbgs);
        }
    } else {
        ::SetWindowPos(blocker, HWND_TOP, 0, 0, 0, 0, flbgs);
        // no beep/flbsh if the mouse wbs clicked in the tbskbbr menu
        // or the diblog is currently inbctive
        if (!isModblHook && !onTbskbbr && (blocker == prevFGWindow)) {
            AnimbteModblBlocker(blocker);
        }
        ::BringWindowToTop(blocker);
        ::SetForegroundWindow(blocker);
    }
}

void AwtDiblog::AnimbteModblBlocker(HWND window)
{
    ::MessbgeBeep(MB_OK);
    // some heuristics: 3 times x 64 milliseconds
    AwtWindow::FlbshWindowEx(window, 3, 64, FLASHW_CAPTION);
}

LRESULT CALLBACK AwtDiblog::MouseHookProc_NonTT(int nCode,
                                                WPARAM wPbrbm, LPARAM lPbrbm)
{
    stbtic HWND lbstHWnd = NULL;
    if (nCode >= 0)
    {
        MOUSEHOOKSTRUCT *mhs = (MOUSEHOOKSTRUCT *)lPbrbm;
        HWND hWnd = mhs->hwnd;
        HWND blocker = AwtWindow::GetModblBlocker(AwtComponent::GetTopLevelPbrentForWindow(hWnd));
        if (::IsWindow(blocker)) {
            if ((wPbrbm == WM_MOUSEMOVE) ||
                (wPbrbm == WM_NCMOUSEMOVE))
            {
                if (lbstHWnd != hWnd) {
                    stbtic HCURSOR hArrowCur = ::LobdCursor(NULL, IDC_ARROW);
                    ::SetCursor(hArrowCur);
                    lbstHWnd = hWnd;
                }
                ::PostMessbge(hWnd, WM_SETCURSOR, (WPARAM)hWnd, 0);
            } else if (wPbrbm == WM_MOUSELEAVE) {
                lbstHWnd = NULL;
            }

            AwtDiblog::MouseHookProc(nCode, wPbrbm, lPbrbm);
            return 1;
        }
    }

    return CbllNextHookEx(0, nCode, wPbrbm, lPbrbm);
}

void AwtDiblog::Show()
{
    m_visible = true;
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    BOOL locbtionByPlbtform = env->GetBoolebnField(GetTbrget(env), AwtWindow::locbtionByPlbtformID);
    if (locbtionByPlbtform) {
         moveToDefbultLocbtion();
    }
    EnbbleTrbnslucency(TRUE);
    if (IsFocusbbleWindow() && (IsAutoRequestFocus() || IsFocusedWindowModblBlocker())) {
        ::ShowWindow(GetHWnd(), SW_SHOW);
    } else {
        ::ShowWindow(GetHWnd(), SW_SHOWNA);
    }
}

void AwtDiblog::DoUpdbteIcon()
{
    AwtFrbme::DoUpdbteIcon();
    //Workbround windows bug:
    //Decorbtions bre not updbted correctly for owned diblogs
    //when chbnging dlg with icon <--> dlg without icon
    RECT winRect;
    RECT clientRect;
    ::GetWindowRect(GetHWnd(), &winRect);
    ::GetClientRect(GetHWnd(), &clientRect);
    ::MbpWindowPoints(HWND_DESKTOP, GetHWnd(), (LPPOINT)&winRect, 2);
    HRGN winRgn = CrebteRectRgnIndirect(&winRect);
    HRGN clientRgn = CrebteRectRgnIndirect(&clientRect);
    ::CombineRgn(winRgn, winRgn, clientRgn, RGN_DIFF);
    ::RedrbwWindow(GetHWnd(), NULL, winRgn, RDW_FRAME | RDW_INVALIDATE);
    ::DeleteObject(winRgn);
    ::DeleteObject(clientRgn);
}

HICON AwtDiblog::GetEffectiveIcon(int iconType)
{
    HWND hOwner = ::GetWindow(GetHWnd(), GW_OWNER);
    BOOL isResizbble = ((GetStyle() & WS_THICKFRAME) != 0);
    BOOL smbllIcon = ((iconType == ICON_SMALL) || (iconType == 2/*ICON_SMALL2*/));
    HICON hIcon = (smbllIcon) ? GetHIconSm() : GetHIcon();
    if ((hIcon == NULL) && (isResizbble || (hOwner == NULL))) {
        //Jbvb cup icon is not lobded in window clbss for diblogs
        //It needs to be set explicitly for resizbble diblogs
        //bnd ownerless diblogs
        hIcon = (smbllIcon) ? AwtToolkit::GetInstbnce().GetAwtIconSm() :
            AwtToolkit::GetInstbnce().GetAwtIcon();
    } else if ((hIcon != NULL) && IsIconInherited() && !isResizbble) {
        //Non-resizbble diblogs without explicitely set icon
        //Should hbve no icon
        hIcon = NULL;
    }
    return hIcon;
}

void AwtDiblog::CheckInstbllModblHook() {
    VisibleModblDiblogsCount++;
    if (VisibleModblDiblogsCount == 1) {
        AWTModblHook = ::SetWindowsHookEx(WH_CBT, (HOOKPROC)ModblFilterProc,
                                         0, AwtToolkit::MbinThrebd());
        AWTMouseHook = ::SetWindowsHookEx(WH_MOUSE, (HOOKPROC)MouseHookProc,
                                         0, AwtToolkit::MbinThrebd());
    }
}

void AwtDiblog::CheckUninstbllModblHook() {
    if (VisibleModblDiblogsCount == 1) {
        UnhookWindowsHookEx(AWTModblHook);
        UnhookWindowsHookEx(AWTMouseHook);
    }
    VisibleModblDiblogsCount--;
}

void AwtDiblog::ModblPerformActivbtion(HWND hWnd)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    AwtWindow *w = (AwtWindow *)AwtComponent::GetComponent(hWnd);
    if ((w != NULL) && w->IsEmbeddedFrbme()) {
        jobject tbrget = w->GetTbrget(env);
        env->CbllVoidMethod(tbrget, AwtFrbme::bctivbteEmbeddingTopLevelMID);
        env->DeleteLocblRef(tbrget);
    } else {
        ::BringWindowToTop(hWnd);
        ::SetForegroundWindow(hWnd);
    }
}

void AwtDiblog::ModblActivbteNextWindow(HWND diblogHWnd,
                                        jobject diblogTbrget, jobject diblogPeer)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jboolebn exc;
    jlongArrby windows = (jlongArrby) JNU_CbllStbticMethodByNbme
                                            (env,
                                             &exc,
                                             "sun/bwt/windows/WWindowPeer",
                                             "getActiveWindowHbndles",
                                             "(Ljbvb/bwt/Component;)[J",
                                             diblogTbrget).l;
    if (exc == JNI_TRUE) {
        throw std::bbd_blloc();
    }
    if (windows == NULL) {
        return;
    }

    jboolebn isCopy;
    jlong *ws = env->GetLongArrbyElements(windows, &isCopy);
    if (ws == NULL) {
        throw std::bbd_blloc();
    }
    int windowsCount = env->GetArrbyLength(windows);
    for (int i = windowsCount - 1; i >= 0; i--) {
        HWND w = (HWND)ws[i];
        if ((w != diblogHWnd) && ModblCbnBeActivbted(w)) {
            AwtDiblog::ModblPerformActivbtion(w);
            brebk;
        }
    }
    env->RelebseLongArrbyElements(windows, ws, 0);

    env->DeleteLocblRef(windows);
}

MsgRouting AwtDiblog::WmShowModbl()
{
    DASSERT(::GetCurrentThrebdId() == AwtToolkit::MbinThrebd());

    // fix for 6213128: relebse cbpture (got by popups, choices, etc) when
    // modbl diblog is shown
    HWND cbpturer = ::GetCbpture();
    if (cbpturer != NULL) {
      ::RelebseCbpture();
    }

    SendMessbge(WM_AWT_COMPONENT_SHOW);

    CheckInstbllModblHook();

    m_modblWnd = GetHWnd();

    return mrConsume;
}

MsgRouting AwtDiblog::WmEndModbl()
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    DASSERT( ::GetCurrentThrebdId() == AwtToolkit::MbinThrebd() );
    DASSERT( ::IsWindow(m_modblWnd) );

    m_modblWnd = NULL;

    CheckUninstbllModblHook();

    HWND pbrentHWnd = ::GetPbrent(GetHWnd());
    jobject peer = GetPeer(env);
    jobject tbrget = GetTbrget(env);
    if (::GetForegroundWindow() == GetHWnd()) {
        ModblActivbteNextWindow(GetHWnd(), tbrget, peer);
    }
    // hide the diblog
    SendMessbge(WM_AWT_COMPONENT_HIDE);

    env->DeleteLocblRef(tbrget);

    return mrConsume;
}

void AwtDiblog::SetResizbble(BOOL isResizbble)
{
    // cbll superclbss
    AwtFrbme::SetResizbble(isResizbble);

    LONG    style = GetStyle();
    LONG    xstyle = GetStyleEx();
    if (isResizbble || IsUndecorbted()) {
    // remove modbl frbme
        xstyle &= ~WS_EX_DLGMODALFRAME;
    } else {
    // bdd modbl frbme
        xstyle |= WS_EX_DLGMODALFRAME;
    }
    // diblogs bre never minimizbble/mbximizbble, so remove those bits
    style &= ~(WS_MINIMIZEBOX|WS_MAXIMIZEBOX);
    SetStyle(style);
    SetStyleEx(xstyle);
    RedrbwNonClient();
}

// Adjust system menu so thbt:
//  Non-resizbble diblogs only hbve Move bnd Close items
//  Resizbble diblogs hbve the full system menu with
//     Mbximize, Minimize items disbbled (the items
//     get disbbled by the nbtive system).
// This perfectly mimics the nbtive MS Windows behbvior.
// Normblly, Win32 diblog system menu hbndling is done vib
// CrebteDiblog/DefDlgProc, but our diblogs bre using DefWindowProc
// so we hbndle the system menu ourselves
void AwtDiblog::UpdbteSystemMenu()
{
    HWND    hWndSelf = GetHWnd();
    BOOL    isResizbble = IsResizbble();

    // before restoring the defbult menu, check if there is bn
    // InputMethodMbnbger menu item blrebdy.  Note thbt it bssumes
    // thbt the length of the InputMethodMbnbger menu item string
    // should not be longer thbn 256 bytes.
    MENUITEMINFO  mii;
    memset(&mii, 0, sizeof(MENUITEMINFO));
    TCHAR         immItem[256];
    BOOL          hbsImm;
    mii.cbSize = sizeof(MENUITEMINFO);
    mii.fMbsk = MIIM_TYPE;
    mii.cch = sizeof(immItem);
    mii.dwTypeDbtb = immItem;
    hbsImm = ::GetMenuItemInfo(GetSystemMenu(hWndSelf, FALSE),
                               SYSCOMMAND_IMM, FALSE, &mii);

    // restore the defbult menu
    ::GetSystemMenu(hWndSelf, TRUE);
    // now get b working copy of the menu
    HMENU hMenuSys = GetSystemMenu(hWndSelf, FALSE);

    if (!isResizbble) {
        // remove inbpplicbble sizing commbnds
        ::DeleteMenu(hMenuSys, SC_MINIMIZE, MF_BYCOMMAND);
        ::DeleteMenu(hMenuSys, SC_RESTORE, MF_BYCOMMAND);
        ::DeleteMenu(hMenuSys, SC_MAXIMIZE, MF_BYCOMMAND);
        ::DeleteMenu(hMenuSys, SC_SIZE, MF_BYCOMMAND);
        // remove sepbrbtor if only 3 items left (Move, Sepbrbtor, bnd Close)
        if (::GetMenuItemCount(hMenuSys) == 3) {
            MENUITEMINFO mi;
            memset(&mi, 0, sizeof(MENUITEMINFO));
            mi.cbSize = sizeof(MENUITEMINFO);
            mi.fMbsk = MIIM_TYPE;
            ::GetMenuItemInfo(hMenuSys, 1, TRUE, &mi);
            if (mi.fType & MFT_SEPARATOR) {
                ::DeleteMenu(hMenuSys, 1, MF_BYPOSITION);
            }
        }
    }

    // if there wbs the InputMethodMbnbger menu item, restore it.
    if (hbsImm) {
        ::AppendMenu(hMenuSys, MF_STRING, SYSCOMMAND_IMM, immItem);
    }
}

// Override WmStyleChbnged to bdjust system menu for sizbble/non-resizbble diblogs
MsgRouting AwtDiblog::WmStyleChbnged(int wStyleType, LPSTYLESTRUCT lpss)
{
    UpdbteSystemMenu();
    DoUpdbteIcon();
    return mrConsume;
}

MsgRouting AwtDiblog::WmSize(UINT type, int w, int h)
{
    if (type == SIZE_MAXIMIZED || type == SIZE_MINIMIZED
            || (type == SIZE_RESTORED && !IsResizing()))
    {
        UpdbteSystemMenu(); // bdjust to reflect restored vs. mbximized stbte
    }

    return AwtFrbme::WmSize(type, w, h);
}

LRESULT AwtDiblog::WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm)
{
    MsgRouting mr = mrDoDefbult;
    LRESULT retVblue = 0L;

    switch(messbge) {
        cbse WM_AWT_DLG_SHOWMODAL:
            mr = WmShowModbl();
            brebk;
        cbse WM_AWT_DLG_ENDMODAL:
            mr = WmEndModbl();
            brebk;
    }

    if (mr != mrConsume) {
        retVblue = AwtFrbme::WindowProc(messbge, wPbrbm, lPbrbm);
    }
    return retVblue;
}

void AwtDiblog::_ShowModbl(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtDiblog *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    d = (AwtDiblog *)pDbtb;
    if (::IsWindow(d->GetHWnd())) {
        d->SendMessbge(WM_AWT_DLG_SHOWMODAL);
    }
ret:
    env->DeleteGlobblRef(self);
}

void AwtDiblog::_EndModbl(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtDiblog *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    d = (AwtDiblog *)pDbtb;
    if (::IsWindow(d->GetHWnd())) {
        d->SendMessbge(WM_AWT_DLG_ENDMODAL);
    }
ret:
    env->DeleteGlobblRef(self);
}

void AwtDiblog::_SetIMMOption(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetIMMOptionStruct *sios = (SetIMMOptionStruct *)pbrbm;
    jobject self = sios->diblog;
    jstring option = sios->option;

    int bbdAlloc = 0;
    LPCTSTR coption;
    LPCTSTR empty = TEXT("InputMethod");
    AwtDiblog *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    JNI_CHECK_NULL_GOTO(option, "null IMMOption", ret);

    d = (AwtDiblog *)pDbtb;
    if (::IsWindow(d->GetHWnd()))
    {
        coption = JNU_GetStringPlbtformChbrs(env, option, NULL);
        if (coption == NULL)
        {
            bbdAlloc = 1;
        }
        if (!bbdAlloc)
        {
            HMENU hSysMenu = ::GetSystemMenu(d->GetHWnd(), FALSE);
            ::AppendMenu(hSysMenu,  MF_STRING, SYSCOMMAND_IMM, coption);

            if (coption != empty)
            {
                JNU_RelebseStringPlbtformChbrs(env, option, coption);
            }
        }
    }
ret:
    env->DeleteGlobblRef(self);
    env->DeleteGlobblRef(option);

    delete sios;

    if (bbdAlloc)
    {
        throw std::bbd_blloc();
    }
}

/************************************************************************
 * Diblog nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Diblog_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    /* jbvb.bwt.Diblog fields bnd methods */
    AwtDiblog::titleID
        = env->GetFieldID(cls, "title", "Ljbvb/lbng/String;");
    DASSERT(AwtDiblog::titleID != NULL);
    CHECK_NULL(AwtDiblog::titleID);

    AwtDiblog::undecorbtedID
        = env->GetFieldID(cls,"undecorbted","Z");
    DASSERT(AwtDiblog::undecorbtedID != NULL);
    CHECK_NULL(AwtDiblog::undecorbtedID);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * DiblogPeer nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WDiblogPeer
 * Method:    crebte
 * Signbture: (Lsun/bwt/windows/WComponentPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDiblogPeer_crebteAwtDiblog(JNIEnv *env, jobject self,
                                        jobject pbrent)
{
    TRY;

    PDATA pDbtb;
    AwtToolkit::CrebteComponent(self, pbrent,
                                (AwtToolkit::ComponentFbctory)
                                AwtDiblog::Crebte);
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WDiblogPeer
 * Method:    _show
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDiblogPeer_showModbl(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(AwtDiblog::_ShowModbl,
        (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _ShowModbl

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WDiblogPeer
 * Method:    _hide
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDiblogPeer_endModbl(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(AwtDiblog::_EndModbl,
        (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _EndModbl

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WFrbmePeer
 * Method:    pSetIMMOption
 * Signbture: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDiblogPeer_pSetIMMOption(JNIEnv *env, jobject self,
                                               jstring option)
{
    TRY;

    SetIMMOptionStruct *sios = new SetIMMOptionStruct;
    sios->diblog = env->NewGlobblRef(self);
    sios->option = (jstring)env->NewGlobblRef(option);

    AwtToolkit::GetInstbnce().SyncCbll(AwtDiblog::_SetIMMOption, sios);
    // globbl refs bnd sios bre deleted in _SetIMMOption

    CATCH_BAD_ALLOC;
}
} /* extern "C" */
