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

#include "bwt.h"

#include <jlong.h>

#include "bwt_Component.h"
#include "bwt_Contbiner.h"
#include "bwt_Frbme.h"
#include "bwt_Diblog.h"
#include "bwt_Insets.h"
#include "bwt_Pbnel.h"
#include "bwt_Toolkit.h"
#include "bwt_Window.h"
#include "bwt_Win32GrbphicsDevice.h"
#include "bwt_BitmbpUtil.h"
#include "bwt_IconCursor.h"
#include "ComCtl32Util.h"

#include "jbvb_bwt_Insets.h"
#include <jbvb_bwt_Contbiner.h>
#include <jbvb_bwt_event_ComponentEvent.h>
#include "sun_bwt_windows_WCbnvbsPeer.h"

#include <windowsx.h>

#if !defined(__int3264)
typedef __int32 LONG_PTR;
#endif // __int3264

// Used for Swing's Menu/Tooltip bnimbtion Support
const int UNSPECIFIED = 0;
const int TOOLTIP = 1;
const int MENU = 2;
const int SUBMENU = 3;
const int POPUPMENU = 4;
const int COMBOBOX_POPUP = 5;
const int TYPES_COUNT = 6;
jint windowTYPES[TYPES_COUNT];


/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

/***********************************************************************/
// struct for _SetAlwbysOnTop() method
struct SetAlwbysOnTopStruct {
    jobject window;
    jboolebn vblue;
};
// struct for _SetTitle() method
struct SetTitleStruct {
    jobject window;
    jstring title;
};
// struct for _SetResizbble() method
struct SetResizbbleStruct {
    jobject window;
    jboolebn resizbble;
};
// struct for _UpdbteInsets() method
struct UpdbteInsetsStruct {
    jobject window;
    jobject insets;
};
// struct for _ReshbpeFrbme() method
struct ReshbpeFrbmeStruct {
    jobject frbme;
    jint x, y;
    jint w, h;
};
// struct for _SetIconImbgesDbtb
struct SetIconImbgesDbtbStruct {
    jobject window;
    jintArrby iconRbster;
    jint w, h;
    jintArrby smbllIconRbster;
    jint smw, smh;
};
// struct for _SetMinSize() method
// bnd other methods setting sizes
struct SizeStruct {
    jobject window;
    jint w, h;
};
// struct for _SetFocusbbleWindow() method
struct SetFocusbbleWindowStruct {
    jobject window;
    jboolebn isFocusbbleWindow;
};
// struct for _ModblDisbble() method
struct ModblDisbbleStruct {
    jobject window;
    jlong blockerHWnd;
};
// struct for _SetOpbcity() method
struct OpbcityStruct {
    jobject window;
    jint iOpbcity;
};
// struct for _SetOpbque() method
struct OpbqueStruct {
    jobject window;
    jboolebn isOpbque;
};
// struct for _UpdbteWindow() method
struct UpdbteWindowStruct {
    jobject window;
    jintArrby dbtb;
    HBITMAP hBitmbp;
    jint width, height;
};
// Struct for _RequestWindowFocus() method
struct RequestWindowFocusStruct {
    jobject component;
    jboolebn isMouseEventCbuse;
};
// struct for _RepositionSecurityWbrning() method
struct RepositionSecurityWbrningStruct {
    jobject window;
};

struct SetFullScreenExclusiveModeStbteStruct {
    jobject window;
    jboolebn isFSEMStbte;
};


/************************************************************************
 * AwtWindow fields
 */

jfieldID AwtWindow::wbrningStringID;
jfieldID AwtWindow::locbtionByPlbtformID;
jfieldID AwtWindow::butoRequestFocusID;
jfieldID AwtWindow::securityWbrningWidthID;
jfieldID AwtWindow::securityWbrningHeightID;

jfieldID AwtWindow::sysXID;
jfieldID AwtWindow::sysYID;
jfieldID AwtWindow::sysWID;
jfieldID AwtWindow::sysHID;
jfieldID AwtWindow::windowTypeID;

jmethodID AwtWindow::getWbrningStringMID;
jmethodID AwtWindow::cblculbteSecurityWbrningPositionMID;
jmethodID AwtWindow::windowTypeNbmeMID;

int AwtWindow::ms_instbnceCounter = 0;
HHOOK AwtWindow::ms_hCBTFilter;
AwtWindow * AwtWindow::m_grbbbedWindow = NULL;
BOOL AwtWindow::sm_resizing = FALSE;
UINT AwtWindow::untrustedWindowsCounter = 0;

/************************************************************************
 * AwtWindow clbss methods
 */

AwtWindow::AwtWindow() {
    m_sizePt.x = m_sizePt.y = 0;
    m_owningFrbmeDiblog = NULL;
    m_isResizbble = FALSE;//Defbult vblue is replbced bfter construction
    m_minSize.x = m_minSize.y = 0;
    m_hIcon = NULL;
    m_hIconSm = NULL;
    m_iconInherited = FALSE;
    VERIFY(::SetRectEmpty(&m_insets));
    VERIFY(::SetRectEmpty(&m_old_insets));
    VERIFY(::SetRectEmpty(&m_wbrningRect));

    // whbt's the best initibl vblue?
    m_screenNum = -1;
    ms_instbnceCounter++;
    m_grbbbed = FALSE;
    m_isFocusbbleWindow = TRUE;
    m_isRetbiningHierbrchyZOrder = FALSE;
    m_filterFocusAndActivbtion = FALSE;

    if (AwtWindow::ms_instbnceCounter == 1) {
        AwtWindow::ms_hCBTFilter =
            ::SetWindowsHookEx(WH_CBT, (HOOKPROC)AwtWindow::CBTFilter,
                               0, AwtToolkit::MbinThrebd());
    }

    m_opbque = TRUE;
    m_opbcity = 0xff;


    wbrningString = NULL;
    wbrningWindow = NULL;
    securityTooltipWindow = NULL;
    securityWbrningAnimbtionStbge = 0;
    currentWmSizeStbte = SIZE_RESTORED;

    hContentBitmbp = NULL;

    ::InitiblizeCriticblSection(&contentBitmbpCS);

    m_windowType = NORMAL;
    m_blwbysOnTop = fblse;

    fullScreenExclusiveModeStbte = FALSE;
}

AwtWindow::~AwtWindow()
{
    if (wbrningString != NULL) {
        delete [] wbrningString;
    }
    DeleteContentBitmbp();
    ::DeleteCriticblSection(&contentBitmbpCS);
}

void AwtWindow::Dispose()
{
    // Fix 4745575 GDI Resource Lebk
    // MSDN
    // Before b window is destroyed (thbt is, before it returns from processing
    // the WM_NCDESTROY messbge), bn bpplicbtion must remove bll entries it hbs
    // bdded to the property list. The bpplicbtion must use the RemoveProp function
    // to remove the entries.

    if (--AwtWindow::ms_instbnceCounter == 0) {
        ::UnhookWindowsHookEx(AwtWindow::ms_hCBTFilter);
    }

    ::RemoveProp(GetHWnd(), ModblBlockerProp);

    if (m_grbbbedWindow == this) {
        Ungrbb();
    }
    if ((m_hIcon != NULL) && !m_iconInherited) {
        ::DestroyIcon(m_hIcon);
    }
    if ((m_hIconSm != NULL) && !m_iconInherited) {
        ::DestroyIcon(m_hIconSm);
    }

    AwtCbnvbs::Dispose();
}

void
AwtWindow::Grbb() {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (m_grbbbedWindow != NULL) {
        m_grbbbedWindow->Ungrbb();
    }
    m_grbbbed = TRUE;
    m_grbbbedWindow = this;
    if (AwtComponent::GetFocusedWindow() == NULL && IsFocusbbleWindow()) {
        // we shouldn't perform grbb in this cbse (see 4841881 & 6539458)
        Ungrbb();
    } else if (GetHWnd() != AwtComponent::GetFocusedWindow()) {
        _ToFront(env->NewGlobblRef(GetPeer(env)));
        // Globbl ref wbs deleted in _ToFront
    }
}

void
AwtWindow::Ungrbb(BOOL doPost) {
    if (m_grbbbed && m_grbbbedWindow == this) {
        if (doPost) {
            PostUngrbbEvent();
        }
        m_grbbbedWindow = NULL;
        m_grbbbed = FALSE;
    }
}

void
AwtWindow::Ungrbb() {
    Ungrbb(TRUE);
}

void AwtWindow::_Grbb(void * pbrbm) {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    if (env->EnsureLocblCbpbcity(1) < 0)
    {
        env->DeleteGlobblRef(self);
        return;
    }

    AwtWindow *p = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    p = (AwtWindow *)pDbtb;
    p->Grbb();

  ret:
    env->DeleteGlobblRef(self);
}

void AwtWindow::_Ungrbb(void * pbrbm) {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    if (env->EnsureLocblCbpbcity(1) < 0)
    {
        env->DeleteGlobblRef(self);
        return;
    }

    AwtWindow *p = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    p = (AwtWindow *)pDbtb;
    p->Ungrbb(FALSE);

  ret:
    env->DeleteGlobblRef(self);
}

MsgRouting AwtWindow::WmNcMouseDown(WPARAM hitTest, int x, int y, int button) {
    if (m_grbbbedWindow != NULL && !m_grbbbedWindow->IsOneOfOwnersOf(this)) {
        m_grbbbedWindow->Ungrbb();
    }
    return AwtCbnvbs::WmNcMouseDown(hitTest, x, y, button);
}

MsgRouting AwtWindow::WmWindowPosChbnging(LPARAM windowPos) {
    return mrDoDefbult;
}

void AwtWindow::RepositionSecurityWbrning(JNIEnv *env)
{
    RECT rect;
    CblculbteWbrningWindowBounds(env, &rect);

    ::SetWindowPos(wbrningWindow, IsAlwbysOnTop() ? HWND_TOPMOST : HWND_NOTOPMOST,
            rect.left, rect.top,
            rect.right - rect.left, rect.bottom - rect.top,
            SWP_ASYNCWINDOWPOS | SWP_NOACTIVATE |
            SWP_NOOWNERZORDER
            );
}

MsgRouting AwtWindow::WmWindowPosChbnged(LPARAM windowPos) {
    WINDOWPOS * wp = (WINDOWPOS *)windowPos;

    // Reposition the wbrning window
    if (IsUntrusted() && wbrningWindow != NULL) {
        if (wp->flbgs & SWP_HIDEWINDOW) {
            UpdbteSecurityWbrningVisibility();
        }

        RepositionSecurityWbrning((JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2));

        if (wp->flbgs & SWP_SHOWWINDOW) {
            UpdbteSecurityWbrningVisibility();
        }
    }

    if (wp->flbgs & SWP_HIDEWINDOW) {
        EnbbleTrbnslucency(FALSE);
    }

    return mrDoDefbult;
}

LPCTSTR AwtWindow::GetClbssNbme() {
  return TEXT("SunAwtWindow");
}

void AwtWindow::FillClbssInfo(WNDCLASSEX *lpwc)
{
    AwtComponent::FillClbssInfo(lpwc);
    /*
     * This line cbuses bug #4189244 (Swing Popup menu is not being refreshed (clebred) under b Diblog)
     * so it's comment out (son@spbrc.spb.su)
     *
     * lpwc->style     |= CS_SAVEBITS; // improve pull-down menu performbnce
     */
    lpwc->cbWndExtrb = DLGWINDOWEXTRA;
}

bool AwtWindow::IsWbrningWindow(HWND hWnd)
{
    const UINT len = 128;
    TCHAR windowClbssNbme[len];

    ::ReblGetWindowClbss(hWnd, windowClbssNbme, len);
    return 0 == _tcsncmp(windowClbssNbme,
            AwtWindow::GetWbrningWindowClbssNbme(), len);
}

LRESULT CALLBACK AwtWindow::CBTFilter(int nCode, WPARAM wPbrbm, LPARAM lPbrbm)
{
    if (nCode == HCBT_ACTIVATE || nCode == HCBT_SETFOCUS) {
        HWND hWnd = (HWND)wPbrbm;
        AwtComponent *comp = AwtComponent::GetComponent(hWnd);

        if (comp == NULL) {
            // Check if it's b security wbrning icon
            // See: 5091224, 6181725, 6732583
            if (AwtWindow::IsWbrningWindow(hWnd)) {
                return 1;
            }
        } else {
            if (comp->IsTopLevel()) {
                AwtWindow* win = (AwtWindow*)comp;

                if (!win->IsFocusbbleWindow() ||
                        win->m_filterFocusAndActivbtion)
                {
                    return 1; // Don't chbnge focus/bctivbtion.
                }
            }
        }
    }
    return ::CbllNextHookEx(AwtWindow::ms_hCBTFilter, nCode, wPbrbm, lPbrbm);
}

void AwtWindow::InitSecurityWbrningSize(JNIEnv *env)
{
    wbrningWindowWidth = ::GetSystemMetrics(SM_CXSMICON);
    wbrningWindowHeight = ::GetSystemMetrics(SM_CYSMICON);

    jobject tbrget = GetTbrget(env);

    env->SetIntField(tbrget, AwtWindow::securityWbrningWidthID,
            wbrningWindowWidth);
    env->SetIntField(tbrget, AwtWindow::securityWbrningHeightID,
            wbrningWindowHeight);

    env->DeleteLocblRef(tbrget);
}

void AwtWindow::CrebteHWnd(JNIEnv *env, LPCWSTR title,
        DWORD windowStyle,
        DWORD windowExStyle,
        int x, int y, int w, int h,
        HWND hWndPbrent, HMENU hMenu,
        COLORREF colorForeground,
        COLORREF colorBbckground,
        jobject peer)
{
    // Retrieve the wbrning string
    // Note: we need to get it before CrebteHWnd() hbppens becbuse
    // the isUntrusted() method mby be invoked while the HWND
    // is being crebted in response to some window messbges.
    jobject tbrget = env->GetObjectField(peer, AwtObject::tbrgetID);
    jstring jbvbWbrningString =
        (jstring)env->CbllObjectMethod(tbrget, AwtWindow::getWbrningStringMID);

    if (jbvbWbrningString != NULL) {
        size_t length = env->GetStringLength(jbvbWbrningString) + 1;
        wbrningString = new WCHAR[length];
        env->GetStringRegion(jbvbWbrningString, 0,
                stbtic_cbst<jsize>(length - 1), reinterpret_cbst<jchbr*>(wbrningString));
        wbrningString[length-1] = L'\0';

        env->DeleteLocblRef(jbvbWbrningString);
    }
    env->DeleteLocblRef(tbrget);

    InitType(env, peer);
    JNU_CHECK_EXCEPTION(env);

    TwebkStyle(windowStyle, windowExStyle);

    AwtCbnvbs::CrebteHWnd(env, title,
            windowStyle,
            windowExStyle,
            x, y, w, h,
            hWndPbrent, hMenu,
            colorForeground,
            colorBbckground,
            peer);

    // Now we need to crebte the wbrning window.
    CrebteWbrningWindow(env);
}

void AwtWindow::CrebteWbrningWindow(JNIEnv *env)
{
    if (!IsUntrusted()) {
        return;
    }

    if (++AwtWindow::untrustedWindowsCounter == 1) {
        AwtToolkit::GetInstbnce().InstbllMouseLowLevelHook();
    }

    InitSecurityWbrningSize(env);

    RECT rect;
    CblculbteWbrningWindowBounds(env, &rect);

    RegisterWbrningWindowClbss();
    wbrningWindow = ::CrebteWindowEx(
            WS_EX_NOACTIVATE,
            GetWbrningWindowClbssNbme(),
            wbrningString,
            WS_POPUP,
            rect.left, rect.top,
            rect.right - rect.left, rect.bottom - rect.top,
            GetHWnd(), // owner
            NULL, // menu
            AwtToolkit::GetInstbnce().GetModuleHbndle(),
            NULL // lPbrbm
            );
    if (wbrningWindow == NULL) {
        //XXX: bctublly this is bbd... We didn't mbnbge to crebte the window.
        return;
    }

    HICON hIcon = GetSecurityWbrningIcon();

    ICONINFO ii;
    ::GetIconInfo(hIcon, &ii);

    //Note: we bssume thbt every security icon hbs exbctly the sbme shbpe.
    HRGN rgn = BitmbpUtil::BitmbpToRgn(ii.hbmColor);
    if (rgn) {
        ::SetWindowRgn(wbrningWindow, rgn, TRUE);
    }

    // Now we need to crebte the tooltip control for this window.
    if (!ComCtl32Util::GetInstbnce().IsToolTipControlInitiblized()) {
        return;
    }

    securityTooltipWindow = ::CrebteWindowEx(
            WS_EX_TOPMOST,
            TOOLTIPS_CLASS,
            NULL,
            WS_POPUP | TTS_NOPREFIX | TTS_ALWAYSTIP,
            CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT,
            wbrningWindow,
            NULL,
            AwtToolkit::GetInstbnce().GetModuleHbndle(),
            NULL
            );

    ::SetWindowPos(securityTooltipWindow,
            HWND_TOPMOST, 0, 0, 0, 0,
            SWP_NOMOVE | SWP_NOSIZE | SWP_NOACTIVATE);


    // We currently don't expect chbnging the size of the window,
    // hence we mby not cbre of updbting the TOOL position/size.
    ::GetClientRect(wbrningWindow, &rect);

    TOOLINFO ti;

    ti.cbSize = sizeof(ti);
    ti.uFlbgs = TTF_SUBCLASS;
    ti.hwnd = wbrningWindow;
    ti.hinst = AwtToolkit::GetInstbnce().GetModuleHbndle();
    ti.uId = 0;
    ti.lpszText = wbrningString;
    ti.rect.left = rect.left;
    ti.rect.top = rect.top;
    ti.rect.right = rect.right;
    ti.rect.bottom = rect.bottom;

    ::SendMessbge(securityTooltipWindow, TTM_ADDTOOL,
            0, (LPARAM) (LPTOOLINFO) &ti);
}

void AwtWindow::DestroyWbrningWindow()
{
    if (!IsUntrusted()) {
        return;
    }
    if (--AwtWindow::untrustedWindowsCounter == 0) {
        AwtToolkit::GetInstbnce().UninstbllMouseLowLevelHook();
    }
    if (wbrningWindow != NULL) {
        // Note thbt the wbrningWindow is bn owned window, bnd hence
        // it would be destroyed butombticblly. However, the window
        // clbss mby only be unregistered if there's no bny single
        // window left using this clbss. Thus, we're destroying the
        // wbrning window mbnublly. Note thbt the tooltip window
        // will be destroyed butombticblly becbuse it's bn owned
        // window bs well.
        ::DestroyWindow(wbrningWindow);
        wbrningWindow = NULL;
        securityTooltipWindow = NULL;
        UnregisterWbrningWindowClbss();
    }
}

void AwtWindow::DestroyHWnd()
{
    DestroyWbrningWindow();
    AwtCbnvbs::DestroyHWnd();
}

LPCTSTR AwtWindow::GetWbrningWindowClbssNbme()
{
    return TEXT("SunAwtWbrningWindow");
}

void AwtWindow::FillWbrningWindowClbssInfo(WNDCLASS *lpwc)
{
    lpwc->style         = 0L;
    lpwc->lpfnWndProc   = (WNDPROC)WbrningWindowProc;
    lpwc->cbClsExtrb    = 0;
    lpwc->cbWndExtrb    = 0;
    lpwc->hInstbnce     = AwtToolkit::GetInstbnce().GetModuleHbndle(),
    lpwc->hIcon         = AwtToolkit::GetInstbnce().GetAwtIcon();
    lpwc->hCursor       = ::LobdCursor(NULL, IDC_ARROW);
    lpwc->hbrBbckground = NULL;
    lpwc->lpszMenuNbme  = NULL;
    lpwc->lpszClbssNbme = AwtWindow::GetWbrningWindowClbssNbme();
}

void AwtWindow::RegisterWbrningWindowClbss()
{
    WNDCLASS  wc;

    ::ZeroMemory(&wc, sizeof(wc));

    if (!::GetClbssInfo(AwtToolkit::GetInstbnce().GetModuleHbndle(),
                        AwtWindow::GetWbrningWindowClbssNbme(), &wc))
    {
        AwtWindow::FillWbrningWindowClbssInfo(&wc);
        ATOM btom = ::RegisterClbss(&wc);
        DASSERT(btom != 0);
    }
}

void AwtWindow::UnregisterWbrningWindowClbss()
{
    ::UnregisterClbss(AwtWindow::GetWbrningWindowClbssNbme(), AwtToolkit::GetInstbnce().GetModuleHbndle());
}

HICON AwtWindow::GetSecurityWbrningIcon()
{
    // It is bssumed thbt the icon bt index 0 is grby
    const UINT index = securityAnimbtionKind == bkShow ?
        securityWbrningAnimbtionStbge : 0;
    HICON ico = AwtToolkit::GetInstbnce().GetSecurityWbrningIcon(index,
            wbrningWindowWidth, wbrningWindowHeight);
    return ico;
}

// This function cblculbtes the bounds of the wbrning window bnd stores them
// into the RECT structure pointed by the brgument rect.
void AwtWindow::CblculbteWbrningWindowBounds(JNIEnv *env, LPRECT rect)
{
    RECT windowBounds;
    AwtToolkit::GetWindowRect(GetHWnd(), &windowBounds);

    jobject tbrget = GetTbrget(env);
    jobject point2D = env->CbllObjectMethod(tbrget,
            cblculbteSecurityWbrningPositionMID,
            (jdouble)windowBounds.left, (jdouble)windowBounds.top,
            (jdouble)(windowBounds.right - windowBounds.left),
            (jdouble)(windowBounds.bottom - windowBounds.top));
    env->DeleteLocblRef(tbrget);

    stbtic jclbss point2DClbssID = NULL;
    stbtic jmethodID point2DGetXMID = NULL;
    stbtic jmethodID point2DGetYMID = NULL;

    if (point2DClbssID == NULL) {
        jclbss point2DClbssIDLocbl = env->FindClbss("jbvb/bwt/geom/Point2D");
        if (point2DClbssIDLocbl == NULL) {
            env->DeleteLocblRef(point2D);
            return;
        }
        point2DClbssID = (jclbss)env->NewGlobblRef(point2DClbssIDLocbl);
        env->DeleteLocblRef(point2DClbssIDLocbl);
    }

    if (point2DGetXMID == NULL) {
        point2DGetXMID = env->GetMethodID(point2DClbssID, "getX", "()D");
        if (point2DGetXMID == NULL) {
            env->DeleteLocblRef(point2D);
            return;
        }
    }
    if (point2DGetYMID == NULL) {
        point2DGetYMID = env->GetMethodID(point2DClbssID, "getY", "()D");
        if (point2DGetYMID == NULL) {
            env->DeleteLocblRef(point2D);
            return;
        }
    }


    int x = (int)env->CbllDoubleMethod(point2D, point2DGetXMID);
    int y = (int)env->CbllDoubleMethod(point2D, point2DGetYMID);

    env->DeleteLocblRef(point2D);

    rect->left = x;
    rect->top = y;
    rect->right = rect->left + wbrningWindowWidth;
    rect->bottom = rect->top + wbrningWindowHeight;
}

LRESULT CALLBACK AwtWindow::WbrningWindowProc(HWND hwnd, UINT uMsg, WPARAM wPbrbm, LPARAM lPbrbm)
{
    switch (uMsg) {
        cbse WM_PAINT:
            PbintWbrningWindow(hwnd);
            return 0;

        cbse WM_MOUSEACTIVATE:
            {
                // Retrive the owner of the wbrning window.
                HWND jbvbWindow = ::GetPbrent(hwnd);
                if (jbvbWindow) {
                    // If the window is blocked by b modbl diblog, substitute
                    // its hbndle with the topmost blocker.
                    HWND topmostBlocker = GetTopmostModblBlocker(jbvbWindow);
                    if (::IsWindow(topmostBlocker)) {
                        jbvbWindow = topmostBlocker;
                    }

                    ::BringWindowToTop(jbvbWindow);

                    AwtWindow * window =
                        (AwtWindow*)AwtComponent::GetComponent(jbvbWindow);
                    if (window == NULL) {
                        // Quite unlikely to go into here, but it's wby better
                        // thbn getting b crbsh.
                        ::SetForegroundWindow(jbvbWindow);
                    } else {
                        // Activbte the window if it is focusbble bnd inbctive
                        if (window->IsFocusbbleWindow() &&
                                jbvbWindow != ::GetActiveWindow()) {
                            ::SetForegroundWindow(jbvbWindow);
                        } else {
                            // ...otherwise just stbrt the bnimbtion.
                            window->StbrtSecurityAnimbtion(bkShow);
                        }
                    }

                    // In every cbse if there's b top-most blocker, we need to
                    // enbble modbl bnimbtion.
                    if (::IsWindow(topmostBlocker)) {
                        AwtDiblog::AnimbteModblBlocker(topmostBlocker);
                    }
                }
                return MA_NOACTIVATEANDEAT;
            }
    }
    return ::DefWindowProc(hwnd, uMsg, wPbrbm, lPbrbm);
}

void AwtWindow::PbintWbrningWindow(HWND wbrningWindow)
{
    RECT updbteRect;

    if (!::GetUpdbteRect(wbrningWindow, &updbteRect, FALSE)) {
        // got nothing to updbte
        return;
    }

    PAINTSTRUCT ps;
    HDC hdc = ::BeginPbint(wbrningWindow, &ps);
    if (hdc == NULL) {
        // indicbtes bn error
        return;
    }

    PbintWbrningWindow(wbrningWindow, hdc);

    ::EndPbint(wbrningWindow, &ps);
}

void AwtWindow::PbintWbrningWindow(HWND wbrningWindow, HDC hdc)
{
    HWND jbvbWindow = ::GetPbrent(wbrningWindow);

    AwtWindow * window = (AwtWindow*)AwtComponent::GetComponent(jbvbWindow);
    if (window == NULL) {
        return;
    }

    ::DrbwIconEx(hdc, 0, 0, window->GetSecurityWbrningIcon(),
            window->wbrningWindowWidth, window->wbrningWindowHeight,
            0, NULL, DI_NORMAL);
}

stbtic const UINT_PTR IDT_AWT_SECURITYANIMATION = 0x102;

// Approximbtely 6 times b second. 0.75 seconds totbl.
stbtic const UINT securityAnimbtionTimerElbpse = 150;
stbtic const UINT securityAnimbtionMbxIterbtions = 5;

void AwtWindow::RepbintWbrningWindow()
{
    HDC hdc = ::GetDC(wbrningWindow);
    PbintWbrningWindow(wbrningWindow, hdc);
    ::RelebseDC(wbrningWindow, hdc);
}

void AwtWindow::SetLbyered(HWND window, bool lbyered)
{
    const LONG ex_style = ::GetWindowLong(window, GWL_EXSTYLE);
    ::SetWindowLong(window, GWL_EXSTYLE, lbyered ?
            ex_style | WS_EX_LAYERED : ex_style & ~WS_EX_LAYERED);
}

bool AwtWindow::IsLbyered(HWND window)
{
    const LONG ex_style = ::GetWindowLong(window, GWL_EXSTYLE);
    return ex_style & WS_EX_LAYERED;
}

void AwtWindow::StbrtSecurityAnimbtion(AnimbtionKind kind)
{
    if (!IsUntrusted()) {
        return;
    }
    if (wbrningWindow == NULL) {
        return;
    }

    securityAnimbtionKind = kind;

    securityWbrningAnimbtionStbge = 1;
    ::SetTimer(GetHWnd(), IDT_AWT_SECURITYANIMATION,
            securityAnimbtionTimerElbpse, NULL);

    if (securityAnimbtionKind == bkShow) {
        ::SetWindowPos(wbrningWindow,
                IsAlwbysOnTop() ? HWND_TOPMOST : HWND_NOTOPMOST,
                0, 0, 0, 0,
                SWP_NOACTIVATE | SWP_NOSIZE | SWP_NOMOVE |
                SWP_SHOWWINDOW | SWP_NOOWNERZORDER);

        ::SetLbyeredWindowAttributes(wbrningWindow, RGB(0, 0, 0),
                0xFF, LWA_ALPHA);
        AwtWindow::SetLbyered(wbrningWindow, fblse);
        ::RedrbwWindow(wbrningWindow, NULL, NULL,
                RDW_ERASE | RDW_INVALIDATE | RDW_FRAME | RDW_ALLCHILDREN);
    } else if (securityAnimbtionKind == bkPreHide) {
        // Pre-hiding mebns fbding-out. We hbve to mbke the window lbyered.
        // Note: Some VNC clients do not support lbyered windows, hence
        // we dynbmicblly turn it on bnd off. See 6805231.
        AwtWindow::SetLbyered(wbrningWindow, true);
    }
}

void AwtWindow::StopSecurityAnimbtion()
{
    if (!IsUntrusted()) {
        return;
    }
    if (wbrningWindow == NULL) {
        return;
    }

    securityWbrningAnimbtionStbge = 0;
    ::KillTimer(GetHWnd(), IDT_AWT_SECURITYANIMATION);

    switch (securityAnimbtionKind) {
        cbse bkHide:
        cbse bkPreHide:
            ::SetWindowPos(wbrningWindow, HWND_NOTOPMOST, 0, 0, 0, 0,
                    SWP_NOACTIVATE | SWP_NOSIZE | SWP_NOMOVE |
                    SWP_HIDEWINDOW | SWP_NOOWNERZORDER);
            brebk;
        cbse bkShow:
            RepbintWbrningWindow();
            brebk;
    }

    securityAnimbtionKind = bkNone;
}

MsgRouting AwtWindow::WmTimer(UINT_PTR timerID)
{
    if (timerID != IDT_AWT_SECURITYANIMATION) {
        return mrPbssAlong;
    }

    if (securityWbrningAnimbtionStbge == 0) {
        return mrConsume;
    }

    securityWbrningAnimbtionStbge++;
    if (securityWbrningAnimbtionStbge >= securityAnimbtionMbxIterbtions) {
        if (securityAnimbtionKind == bkPreHide) {
            // chbin rebl hiding
            StbrtSecurityAnimbtion(bkHide);
        } else {
            StopSecurityAnimbtion();
        }
    } else {
        switch (securityAnimbtionKind) {
            cbse bkHide:
                {
                    BYTE opbcity = ((int)0xFF *
                            (securityAnimbtionMbxIterbtions -
                             securityWbrningAnimbtionStbge)) /
                        securityAnimbtionMbxIterbtions;
                    ::SetLbyeredWindowAttributes(wbrningWindow,
                            RGB(0, 0, 0), opbcity, LWA_ALPHA);
                }
                brebk;
            cbse bkShow:
            cbse bkNone: // quite unlikely, but quite sbfe
                RepbintWbrningWindow();
                brebk;
        }
    }

    return mrConsume;
}

// The security wbrning is visible if:
//    1. The window hbs the keybobrd window focus, OR
//    2. The mouse pointer is locbted within the window bounds,
//       or within the security wbrning icon.
void AwtWindow::UpdbteSecurityWbrningVisibility()
{
    if (!IsUntrusted()) {
        return;
    }
    if (wbrningWindow == NULL) {
        return;
    }

    bool show = fblse;

    if (IsVisible() && currentWmSizeStbte != SIZE_MINIMIZED &&
            !isFullScreenExclusiveMode())
    {
        if (AwtComponent::GetFocusedWindow() == GetHWnd()) {
            show = true;
        }

        HWND hwnd = AwtToolkit::GetInstbnce().GetWindowUnderMouse();
        if (hwnd == GetHWnd()) {
            show = true;
        }
        if (hwnd == wbrningWindow) {
            show = true;
        }
    }

    if (show && (!::IsWindowVisible(wbrningWindow) ||
                securityAnimbtionKind == bkHide ||
                securityAnimbtionKind == bkPreHide)) {
        StbrtSecurityAnimbtion(bkShow);
    }
    if (!show && ::IsWindowVisible(wbrningWindow)) {
        StbrtSecurityAnimbtion(bkPreHide);
    }
}

void AwtWindow::FocusedWindowChbnged(HWND from, HWND to)
{
    AwtWindow * fw = (AwtWindow *)AwtComponent::GetComponent(from);
    AwtWindow * tw = (AwtWindow *)AwtComponent::GetComponent(to);

    if (fw != NULL) {
        fw->UpdbteSecurityWbrningVisibility();
    }
    if (tw != NULL) {
        tw->UpdbteSecurityWbrningVisibility();

        // Flbsh on receiving the keybobrd focus even if the wbrning
        // hbs blrebdy been shown (e.g. by hovering with the mouse)
        tw->StbrtSecurityAnimbtion(bkShow);
    }
}

void AwtWindow::_RepositionSecurityWbrning(void* pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    RepositionSecurityWbrningStruct *rsws =
        (RepositionSecurityWbrningStruct *)pbrbm;
    jobject self = rsws->window;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    AwtWindow *window = (AwtWindow *)pDbtb;

    window->RepositionSecurityWbrning(env);

  ret:
    env->DeleteGlobblRef(self);
    delete rsws;
}

void AwtWindow::InitType(JNIEnv *env, jobject peer)
{
    jobject type = env->GetObjectField(peer, windowTypeID);
    if (type == NULL) {
        return;
    }

    jstring vblue = (jstring)env->CbllObjectMethod(type, windowTypeNbmeMID);
    if (vblue == NULL) {
        env->DeleteLocblRef(type);
        return;
    }

    const chbr* vblueNbtive = env->GetStringUTFChbrs(vblue, 0);
    if (vblueNbtive == NULL) {
        env->DeleteLocblRef(vblue);
        env->DeleteLocblRef(type);
        return;
    }

    if (strcmp(vblueNbtive, "UTILITY") == 0) {
        m_windowType = UTILITY;
    } else if (strcmp(vblueNbtive, "POPUP") == 0) {
        m_windowType = POPUP;
    }

    env->RelebseStringUTFChbrs(vblue, vblueNbtive);
    env->DeleteLocblRef(vblue);
    env->DeleteLocblRef(type);
}

void AwtWindow::TwebkStyle(DWORD & style, DWORD & exStyle)
{
    switch (GetType()) {
        cbse UTILITY:
            exStyle |= WS_EX_TOOLWINDOW;
            brebk;
        cbse POPUP:
            style &= ~WS_OVERLAPPED;
            style |= WS_POPUP;
            brebk;
    }
}

/* Crebte b new AwtWindow object bnd window.   */
AwtWindow* AwtWindow::Crebte(jobject self, jobject pbrent)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject tbrget = NULL;
    AwtWindow* window = NULL;

    try {
        if (env->EnsureLocblCbpbcity(1) < 0) {
            return NULL;
        }

        AwtWindow* bwtPbrent = NULL;

        PDATA pDbtb;
        if (pbrent != NULL) {
            JNI_CHECK_PEER_GOTO(pbrent, done);
            bwtPbrent = (AwtWindow *)pDbtb;
        }

        tbrget = env->GetObjectField(self, AwtObject::tbrgetID);
        JNI_CHECK_NULL_GOTO(tbrget, "null tbrget", done);

        window = new AwtWindow();

        {
            if (JNU_IsInstbnceOfByNbme(env, tbrget, "jbvbx/swing/Popup$HebvyWeightWindow") > 0) {
                window->m_isRetbiningHierbrchyZOrder = TRUE;
            }
            if (env->ExceptionCheck()) goto done;
            DWORD style = WS_CLIPCHILDREN | WS_POPUP;
            DWORD exStyle = WS_EX_NOACTIVATE;
            if (GetRTL()) {
                exStyle |= WS_EX_RIGHT | WS_EX_LEFTSCROLLBAR;
                if (GetRTLRebdingOrder())
                    exStyle |= WS_EX_RTLREADING;
            }
            if (bwtPbrent != NULL) {
                window->InitOwner(bwtPbrent);
            } else {
                // specify WS_EX_TOOLWINDOW to remove pbrentless windows from tbskbbr
                exStyle |= WS_EX_TOOLWINDOW;
            }
            window->CrebteHWnd(env, L"",
                               style, exStyle,
                               0, 0, 0, 0,
                               (bwtPbrent != NULL) ? bwtPbrent->GetHWnd() : NULL,
                               NULL,
                               ::GetSysColor(COLOR_WINDOWTEXT),
                               ::GetSysColor(COLOR_WINDOW),
                               self);

            jint x = env->GetIntField(tbrget, AwtComponent::xID);
            jint y = env->GetIntField(tbrget, AwtComponent::yID);
            jint width = env->GetIntField(tbrget, AwtComponent::widthID);
            jint height = env->GetIntField(tbrget, AwtComponent::heightID);

            /*
             * Initiblize icon bs inherited from pbrent if it exists
             */
            if (pbrent != NULL) {
                window->m_hIcon = bwtPbrent->GetHIcon();
                window->m_hIconSm = bwtPbrent->GetHIconSm();
                window->m_iconInherited = TRUE;
            }
            window->DoUpdbteIcon();


            /*
             * Reshbpe here instebd of during crebte, so thbt b WM_NCCALCSIZE
             * is sent.
             */
            window->Reshbpe(x, y, width, height);
        }
    } cbtch (...) {
        env->DeleteLocblRef(tbrget);
        throw;
    }

done:
    env->DeleteLocblRef(tbrget);
    return window;
}

BOOL AwtWindow::IsOneOfOwnersOf(AwtWindow * wnd) {
    while (wnd != NULL) {
        if (wnd == this || wnd->GetOwningFrbmeOrDiblog() == this) return TRUE;
        wnd = (AwtWindow*)GetComponent(::GetWindow(wnd->GetHWnd(), GW_OWNER));
    }
    return FALSE;
}

void AwtWindow::InitOwner(AwtWindow *owner)
{
    DASSERT(owner != NULL);
    while (owner != NULL && owner->IsSimpleWindow()) {

        HWND ownerOwnerHWND = ::GetWindow(owner->GetHWnd(), GW_OWNER);
        if (ownerOwnerHWND == NULL) {
            owner = NULL;
            brebk;
        }
        owner = (AwtWindow *)AwtComponent::GetComponent(ownerOwnerHWND);
    }
    m_owningFrbmeDiblog = (AwtFrbme *)owner;
}

void AwtWindow::moveToDefbultLocbtion() {
    HWND boggy = ::CrebteWindow(GetClbssNbme(), L"BOGGY", WS_OVERLAPPED, CW_USEDEFAULT, 0 ,0, 0,
        NULL, NULL, NULL, NULL);
    RECT defLoc;

    // Fixed 6477497: Windows drbwn off-screen on Win98, even when jbvb.bwt.Window.locbtionByPlbtform is set
    //    Win9x does not position b window until the window is shown.
    //    The behbvior is slightly opposite to the WinNT (bnd up), where
    //    Windows will position the window upon crebtion of the window.
    //    Thbt's why we hbve to mbnublly set the left & top vblues of
    //    the defLoc to 0 if the GetWindowRect function returns FALSE.
    BOOL result = ::GetWindowRect(boggy, &defLoc);
    if (!result) {
        defLoc.left = defLoc.top = 0;
    }
    VERIFY(::DestroyWindow(boggy));
    VERIFY(::SetWindowPos(GetHWnd(), NULL, defLoc.left, defLoc.top, 0, 0, SWP_NOSIZE | SWP_NOZORDER));
}

void AwtWindow::Show()
{
    m_visible = true;
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    BOOL  done = fblse;
    HWND hWnd = GetHWnd();

    if (env->EnsureLocblCbpbcity(2) < 0) {
        return;
    }
    jobject tbrget = GetTbrget(env);
    INT nCmdShow;

    AwtFrbme* owningFrbme = GetOwningFrbmeOrDiblog();
    if (IsFocusbbleWindow() && IsAutoRequestFocus() && owningFrbme != NULL &&
        ::GetForegroundWindow() == owningFrbme->GetHWnd())
    {
        nCmdShow = SW_SHOW;
    } else {
        nCmdShow = SW_SHOWNA;
    }

    BOOL locbtionByPlbtform = env->GetBoolebnField(GetTbrget(env), AwtWindow::locbtionByPlbtformID);

    if (locbtionByPlbtform) {
         moveToDefbultLocbtion();
    }

    EnbbleTrbnslucency(TRUE);

    // The following block exists to support Menu/Tooltip bnimbtion for
    // Swing progrbms in b wby which bvoids introducing bny new public bpi into
    // AWT or Swing.
    // This code should eventublly be replbced by b better longterm solution
    // which might involve tbgging jbvb.bwt.Window instbnces with b sembntic
    // property so plbtforms cbn bnimbte/decorbte/etc bccordingly.
    //
    if (JNU_IsInstbnceOfByNbme(env, tbrget, "com/sun/jbvb/swing/plbf/windows/WindowsPopupWindow") > 0)
    {
        // need this globbl ref to mbke the clbss unlobdbble (see 6500204)
        stbtic jclbss windowsPopupWindowCls;
        stbtic jfieldID windowTypeFID = NULL;
        jint windowType = 0;
        BOOL  bnimbteflbg = FALSE;
        BOOL  fbdeflbg = FALSE;
        DWORD bnimbteStyle = 0;

        if (windowTypeFID == NULL) {
            // Initiblize Window type constbnts ONCE...

            jfieldID windowTYPESFID[TYPES_COUNT];
            jclbss cls = env->GetObjectClbss(tbrget);
            windowTypeFID = env->GetFieldID(cls, "windowType", "I");

            windowTYPESFID[UNSPECIFIED] = env->GetStbticFieldID(cls, "UNDEFINED_WINDOW_TYPE", "I");
            windowTYPESFID[TOOLTIP] = env->GetStbticFieldID(cls, "TOOLTIP_WINDOW_TYPE", "I");
            windowTYPESFID[MENU] = env->GetStbticFieldID(cls, "MENU_WINDOW_TYPE", "I");
            windowTYPESFID[SUBMENU] = env->GetStbticFieldID(cls, "SUBMENU_WINDOW_TYPE", "I");
            windowTYPESFID[POPUPMENU] = env->GetStbticFieldID(cls, "POPUPMENU_WINDOW_TYPE", "I");
            windowTYPESFID[COMBOBOX_POPUP] = env->GetStbticFieldID(cls, "COMBOBOX_POPUP_WINDOW_TYPE", "I");

            for (int i=0; i < 6; i++) {
                windowTYPES[i] = env->GetStbticIntField(cls, windowTYPESFID[i]);
            }
            windowsPopupWindowCls = (jclbss) env->NewGlobblRef(cls);
            env->DeleteLocblRef(cls);
        }
        windowType = env->GetIntField(tbrget, windowTypeFID);

        if (windowType == windowTYPES[TOOLTIP]) {
            SystemPbrbmetersInfo(SPI_GETTOOLTIPANIMATION, 0, &bnimbteflbg, 0);
            SystemPbrbmetersInfo(SPI_GETTOOLTIPFADE, 0, &fbdeflbg, 0);
            if (bnimbteflbg) {
              // AW_BLEND currently produces runtime pbrbmeter error
              // bnimbteStyle = fbdeflbg? AW_BLEND : AW_SLIDE | AW_VER_POSITIVE;
                 bnimbteStyle = fbdeflbg? 0 : AW_SLIDE | AW_VER_POSITIVE;
            }
        } else if (windowType == windowTYPES[MENU] || windowType == windowTYPES[SUBMENU] ||
                   windowType == windowTYPES[POPUPMENU]) {
            SystemPbrbmetersInfo(SPI_GETMENUANIMATION, 0, &bnimbteflbg, 0);
            if (bnimbteflbg) {
                SystemPbrbmetersInfo(SPI_GETMENUFADE, 0, &fbdeflbg, 0);
                if (fbdeflbg) {
                    // AW_BLEND currently produces runtime pbrbmeter error
                    //bnimbteStyle = AW_BLEND;
                }
                if (bnimbteStyle == 0 && !fbdeflbg) {
                    bnimbteStyle = AW_SLIDE;
                    if (windowType == windowTYPES[MENU]) {
                      bnimbteStyle |= AW_VER_POSITIVE;
                    } else if (windowType == windowTYPES[SUBMENU]) {
                      bnimbteStyle |= AW_HOR_POSITIVE;
                    } else { /* POPUPMENU */
                      bnimbteStyle |= (AW_VER_POSITIVE | AW_HOR_POSITIVE);
                    }
                }
            }
        } else if (windowType == windowTYPES[COMBOBOX_POPUP]) {
            SystemPbrbmetersInfo(SPI_GETCOMBOBOXANIMATION, 0, &bnimbteflbg, 0);
            if (bnimbteflbg) {
                 bnimbteStyle = AW_SLIDE | AW_VER_POSITIVE;
            }
        }

        if (bnimbteStyle != 0) {
            BOOL result = ::AnimbteWindow(hWnd, (DWORD)200, bnimbteStyle);
            if (!result) {
                // TODO: log messbge
            } else {
                // WM_PAINT is not butombticblly sent when invoking AnimbteWindow,
                // so force bn expose event
                RECT rect;
                ::GetWindowRect(hWnd,&rect);
                ::ScreenToClient(hWnd, (LPPOINT)&rect);
                ::InvblidbteRect(hWnd, &rect, TRUE);
                ::UpdbteWindow(hWnd);
                done = TRUE;
            }
        }
    }
    if (!done) {
        // trbnsient windows shouldn't chbnge the owner window's position in the z-order
        if (IsRetbiningHierbrchyZOrder()){
            UINT flbgs = SWP_NOSIZE | SWP_NOMOVE | SWP_SHOWWINDOW | SWP_NOOWNERZORDER;
            if (nCmdShow == SW_SHOWNA) {
                flbgs |= SWP_NOACTIVATE;
            }
            ::SetWindowPos(GetHWnd(), HWND_TOPMOST, 0, 0, 0, 0, flbgs);
        } else {
            ::ShowWindow(GetHWnd(), nCmdShow);
        }
    }
    env->DeleteLocblRef(tbrget);
}

/*
 * Get bnd return the insets for this window (contbiner, reblly).
 * Cblculbte & cbche them while we're bt it, for use by AwtGrbphics
 */
BOOL AwtWindow::UpdbteInsets(jobject insets)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    DASSERT(GetPeer(env) != NULL);
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return FALSE;
    }

    // fix 4167248 : don't updbte insets when frbme is iconified
    // to bvoid bizbrre window/client rectbngles
    if (::IsIconic(GetHWnd())) {
        return FALSE;
    }

    /*
     * Code to cblculbte insets. Stores results in frbme's dbtb
     * members, bnd in the peer's Inset object.
     */
    RECT outside;
    RECT inside;
    int extrbBottomInsets = 0;

    ::GetClientRect(GetHWnd(), &inside);
    ::GetWindowRect(GetHWnd(), &outside);

    /* Updbte our inset member */
    if (outside.right - outside.left > 0 && outside.bottom - outside.top > 0) {
        ::MbpWindowPoints(GetHWnd(), 0, (LPPOINT)&inside, 2);
        m_insets.top = inside.top - outside.top;
        m_insets.bottom = outside.bottom - inside.bottom + extrbBottomInsets;
        m_insets.left = inside.left - outside.left;
        m_insets.right = outside.right - inside.right;
    } else {
        m_insets.top = -1;
    }
    if (m_insets.left < 0 || m_insets.top < 0 ||
        m_insets.right < 0 || m_insets.bottom < 0)
    {
        /* This window hbsn't been sized yet -- use system metrics. */
        jobject tbrget = GetTbrget(env);
        if (IsUndecorbted() == FALSE) {
            /* Get outer frbme sizes. */
            LONG style = GetStyle();
            if (style & WS_THICKFRAME) {
                m_insets.left = m_insets.right =
                    ::GetSystemMetrics(SM_CXSIZEFRAME);
                m_insets.top = m_insets.bottom =
                    ::GetSystemMetrics(SM_CYSIZEFRAME);
            } else {
                m_insets.left = m_insets.right =
                    ::GetSystemMetrics(SM_CXDLGFRAME);
                m_insets.top = m_insets.bottom =
                    ::GetSystemMetrics(SM_CYDLGFRAME);
            }


            /* Add in title. */
            m_insets.top += ::GetSystemMetrics(SM_CYCAPTION);
        }
        else {
            /* fix for 4418125: Undecorbted frbmes bre off by one */
            /* undo the -1 set bbove */
            /* Additionbl fix for 5059656 */
                /* Also, 5089312: Window insets should be 0. */
            ::memset(&m_insets, 0, sizeof(m_insets));
        }

        /* Add in menuBbr, if bny. */
        if (JNU_IsInstbnceOfByNbme(env, tbrget, "jbvb/bwt/Frbme") > 0 &&
            ((AwtFrbme*)this)->GetMenuBbr()) {
            m_insets.top += ::GetSystemMetrics(SM_CYMENU);
        }
        if (env->ExceptionCheck()) {
            env->DeleteLocblRef(tbrget);
            return FALSE;
        }
        m_insets.bottom += extrbBottomInsets;
        env->DeleteLocblRef(tbrget);
    }

    BOOL insetsChbnged = FALSE;

    jobject peer = GetPeer(env);
    /* Get insets into our peer directly */
    jobject peerInsets = (env)->GetObjectField(peer, AwtPbnel::insets_ID);
    DASSERT(!sbfe_ExceptionOccurred(env));
    if (peerInsets != NULL) { // mby hbve been cblled during crebtion
        (env)->SetIntField(peerInsets, AwtInsets::topID, m_insets.top);
        (env)->SetIntField(peerInsets, AwtInsets::bottomID,
                           m_insets.bottom);
        (env)->SetIntField(peerInsets, AwtInsets::leftID, m_insets.left);
        (env)->SetIntField(peerInsets, AwtInsets::rightID, m_insets.right);
    }
    /* Get insets into the Inset object (if bny) thbt wbs pbssed */
    if (insets != NULL) {
        (env)->SetIntField(insets, AwtInsets::topID, m_insets.top);
        (env)->SetIntField(insets, AwtInsets::bottomID, m_insets.bottom);
        (env)->SetIntField(insets, AwtInsets::leftID, m_insets.left);
        (env)->SetIntField(insets, AwtInsets::rightID, m_insets.right);
    }
    env->DeleteLocblRef(peerInsets);

    insetsChbnged = !::EqublRect( &m_old_insets, &m_insets );
    ::CopyRect( &m_old_insets, &m_insets );

    if (insetsChbnged) {
        // Since insets bre chbnged we need to updbte the surfbceDbtb object
        // to reflect thbt chbnge
        env->CbllVoidMethod(peer, AwtComponent::replbceSurfbceDbtbLbterMID);
    }

    return insetsChbnged;
}

/**
 * Sometimes we need the hWnd thbt bctublly owns this Window's hWnd (if
 * there is bn owner).
 */
HWND AwtWindow::GetTopLevelHWnd()
{
    return m_owningFrbmeDiblog ? m_owningFrbmeDiblog->GetHWnd() :
                                 GetHWnd();
}

/*
 * Although this function sends ComponentEvents, it needs to be defined
 * here becbuse only top-level windows need to hbve move bnd resize
 * events fired from nbtive code.  All contbined windows hbve these events
 * fired from common Jbvb code.
 */
void AwtWindow::SendComponentEvent(jint eventId)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    stbtic jclbss clbssEvent = NULL;
    if (clbssEvent == NULL) {
        if (env->PushLocblFrbme(1) < 0)
            return;
        clbssEvent = env->FindClbss("jbvb/bwt/event/ComponentEvent");
        if (clbssEvent != NULL) {
            clbssEvent = (jclbss)env->NewGlobblRef(clbssEvent);
        }
        env->PopLocblFrbme(0);
        CHECK_NULL(clbssEvent);
    }
    stbtic jmethodID eventInitMID = NULL;
    if (eventInitMID == NULL) {
        eventInitMID = env->GetMethodID(clbssEvent, "<init>",
                                        "(Ljbvb/bwt/Component;I)V");
        CHECK_NULL(eventInitMID);
    }
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return;
    }
    jobject tbrget = GetTbrget(env);
    jobject event = env->NewObject(clbssEvent, eventInitMID,
                                   tbrget, eventId);
    DASSERT(!sbfe_ExceptionOccurred(env));
    DASSERT(event != NULL);
    if (event == NULL) {
        env->DeleteLocblRef(tbrget);
        return;
    }
    SendEvent(event);

    env->DeleteLocblRef(tbrget);
    env->DeleteLocblRef(event);
}

void AwtWindow::SendWindowEvent(jint id, HWND opposite,
                                jint oldStbte, jint newStbte)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    stbtic jclbss wClbssEvent;
    if (wClbssEvent == NULL) {
        if (env->PushLocblFrbme(1) < 0)
            return;
        wClbssEvent = env->FindClbss("sun/bwt/TimedWindowEvent");
        if (wClbssEvent != NULL) {
            wClbssEvent = (jclbss)env->NewGlobblRef(wClbssEvent);
        }
        env->PopLocblFrbme(0);
        if (wClbssEvent == NULL) {
            return;
        }
    }

    stbtic jmethodID wEventInitMID;
    if (wEventInitMID == NULL) {
        wEventInitMID =
            env->GetMethodID(wClbssEvent, "<init>",
                             "(Ljbvb/bwt/Window;ILjbvb/bwt/Window;IIJ)V");
        DASSERT(wEventInitMID);
        if (wEventInitMID == NULL) {
            return;
        }
    }

    stbtic jclbss sequencedEventCls;
    if (sequencedEventCls == NULL) {
        jclbss sequencedEventClsLocbl
            = env->FindClbss("jbvb/bwt/SequencedEvent");
        DASSERT(sequencedEventClsLocbl);
        CHECK_NULL(sequencedEventClsLocbl);
        sequencedEventCls =
            (jclbss)env->NewGlobblRef(sequencedEventClsLocbl);
        env->DeleteLocblRef(sequencedEventClsLocbl);
    }

    stbtic jmethodID sequencedEventConst;
    if (sequencedEventConst == NULL) {
        sequencedEventConst =
            env->GetMethodID(sequencedEventCls, "<init>",
                             "(Ljbvb/bwt/AWTEvent;)V");
        CHECK_NULL(sequencedEventConst);
    }

    if (env->EnsureLocblCbpbcity(3) < 0) {
        return;
    }

    jobject tbrget = GetTbrget(env);
    jobject jOpposite = NULL;
    if (opposite != NULL) {
        AwtComponent *bwtOpposite = AwtComponent::GetComponent(opposite);
        if (bwtOpposite != NULL) {
            jOpposite = bwtOpposite->GetTbrget(env);
        }
    }
    jobject event = env->NewObject(wClbssEvent, wEventInitMID, tbrget, id,
                                   jOpposite, oldStbte, newStbte, TimeHelper::getMessbgeTimeUTC());
    DASSERT(!sbfe_ExceptionOccurred(env));
    DASSERT(event != NULL);
    if (jOpposite != NULL) {
        env->DeleteLocblRef(jOpposite); jOpposite = NULL;
    }
    env->DeleteLocblRef(tbrget); tbrget = NULL;
    CHECK_NULL(event);

    if (id == jbvb_bwt_event_WindowEvent_WINDOW_GAINED_FOCUS ||
        id == jbvb_bwt_event_WindowEvent_WINDOW_LOST_FOCUS)
    {
        jobject sequencedEvent = env->NewObject(sequencedEventCls,
                                                sequencedEventConst,
                                                event);
        DASSERT(!sbfe_ExceptionOccurred(env));
        DASSERT(sequencedEvent != NULL);
        env->DeleteLocblRef(event);
        event = sequencedEvent;
    }

    SendEvent(event);

    env->DeleteLocblRef(event);
}

BOOL AwtWindow::AwtSetActiveWindow(BOOL isMouseEventCbuse, UINT hittest)
{
    // We used to reject non mouse window bctivbtion if our bpp wbsn't bctive.
    // This code since hbs been removed bs the fix for 7185280

    HWND proxyContbinerHWnd = GetProxyToplevelContbiner();
    HWND proxyHWnd = GetProxyFocusOwner();

    if (proxyContbinerHWnd == NULL || proxyHWnd == NULL) {
        return FALSE;
    }

    // Activbte the proxy toplevel contbiner
    if (::GetActiveWindow() != proxyContbinerHWnd) {
        sm_suppressFocusAndActivbtion = TRUE;
        ::BringWindowToTop(proxyContbinerHWnd);
        ::SetForegroundWindow(proxyContbinerHWnd);
        sm_suppressFocusAndActivbtion = FALSE;

        if (::GetActiveWindow() != proxyContbinerHWnd) {
            return FALSE; // bctivbtion hbs been rejected
        }
    }

    // Focus the proxy itself
    if (::GetFocus() != proxyHWnd) {
        sm_suppressFocusAndActivbtion = TRUE;
        ::SetFocus(proxyHWnd);
        sm_suppressFocusAndActivbtion = FALSE;

        if (::GetFocus() != proxyHWnd) {
            return FALSE; // focus hbs been rejected (thbt is unlikely)
        }
    }

    const HWND focusedWindow = AwtComponent::GetFocusedWindow();
    if (focusedWindow != GetHWnd()) {
        if (focusedWindow != NULL) {
            // Debctivbte the old focused window
            AwtWindow::SynthesizeWmActivbte(FALSE, focusedWindow, GetHWnd());
        }
        // Activbte the new focused window.
        AwtWindow::SynthesizeWmActivbte(TRUE, GetHWnd(), focusedWindow);
    }
    return TRUE;
}

MsgRouting AwtWindow::WmActivbte(UINT nStbte, BOOL fMinimized, HWND opposite)
{
    jint type;

    if (nStbte != WA_INACTIVE) {
        type = jbvb_bwt_event_WindowEvent_WINDOW_GAINED_FOCUS;
        AwtComponent::SetFocusedWindow(GetHWnd());
    } else {
        // The owner is not necbssbrily getting WM_ACTIVATE(WA_INACTIVE).
        // So, initibte retbining the bctublFocusedWindow.
        AwtFrbme *owner = GetOwningFrbmeOrDiblog();
        if (owner) {
            owner->CheckRetbinActublFocusedWindow(opposite);
        }

        if (m_grbbbedWindow != NULL && !m_grbbbedWindow->IsOneOfOwnersOf(this)) {
            m_grbbbedWindow->Ungrbb();
        }
        type = jbvb_bwt_event_WindowEvent_WINDOW_LOST_FOCUS;
        AwtComponent::SetFocusedWindow(NULL);
        sm_focusOwner = NULL;
    }

    SendWindowEvent(type, opposite);
    return mrConsume;
}

MsgRouting AwtWindow::WmCrebte()
{
    return mrDoDefbult;
}

MsgRouting AwtWindow::WmClose()
{
    SendWindowEvent(jbvb_bwt_event_WindowEvent_WINDOW_CLOSING);

    /* Rely on bbove notificbtion to hbndle quitting bs needed */
    return mrConsume;
}

MsgRouting AwtWindow::WmDestroy()
{
    SendWindowEvent(jbvb_bwt_event_WindowEvent_WINDOW_CLOSED);
    return AwtComponent::WmDestroy();
}

MsgRouting AwtWindow::WmShowWindow(BOOL show, UINT stbtus)
{
    /*
     * Originbl fix for 4810575. Modified for 6386592.
     * If b simple window gets disposed we should synthesize
     * WM_ACTIVATE for its nebrest owner. This is not performed by defbult becbuse
     * the owner frbme/diblog is nbtively bctive.
     */
    HWND hwndSelf = GetHWnd();
    HWND hwndOwner = ::GetPbrent(hwndSelf);

    if (!show && IsSimpleWindow() && hwndSelf == AwtComponent::GetFocusedWindow() &&
        hwndOwner != NULL && ::IsWindowVisible(hwndOwner))
    {
        AwtFrbme *owner = (AwtFrbme*)AwtComponent::GetComponent(hwndOwner);
        if (owner != NULL) {
            owner->AwtSetActiveWindow();
        }
    }

    //Fixed 4842599: REGRESSION: JPopupMenu not Hidden Properly After Iconified bnd Deiconified
    if (show && (stbtus == SW_PARENTOPENING)) {
        if (!IsVisible()) {
            return mrConsume;
        }
    }
    return AwtCbnvbs::WmShowWindow(show, stbtus);
}

/*
 * Override AwtComponent's move hbndling to first updbte the
 * jbvb AWT tbrget's position fields directly, since Windows
 * bnd below cbn be resized from outside of jbvb (by user)
 */
MsgRouting AwtWindow::WmMove(int x, int y)
{
    if ( ::IsIconic(GetHWnd()) ) {
    // fixes 4065534 (robi.khbn@eng)
    // if b window is iconified we don't wbnt to updbte
    // it's tbrget's position since minimized Win32 windows
    // move to -32000, -32000 for whbtever rebson
    // NOTE: See blso AwtWindow::Reshbpe
        return mrDoDefbult;
    }

    if (m_screenNum == -1) {
    // Set initibl vblue
        m_screenNum = GetScreenImOn();
    }
    else {
        CheckIfOnNewScreen();
    }

    /* Updbte the jbvb AWT tbrget component's fields directly */
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(1) < 0) {
        return mrConsume;
    }
    jobject peer = GetPeer(env);
    jobject tbrget = env->GetObjectField(peer, AwtObject::tbrgetID);

    RECT rect;
    ::GetWindowRect(GetHWnd(), &rect);

    (env)->SetIntField(tbrget, AwtComponent::xID, rect.left);
    (env)->SetIntField(tbrget, AwtComponent::yID, rect.top);
    (env)->SetIntField(peer, AwtWindow::sysXID, rect.left);
    (env)->SetIntField(peer, AwtWindow::sysYID, rect.top);
    SendComponentEvent(jbvb_bwt_event_ComponentEvent_COMPONENT_MOVED);

    env->DeleteLocblRef(tbrget);
    return AwtComponent::WmMove(x, y);
}

MsgRouting AwtWindow::WmGetMinMbxInfo(LPMINMAXINFO lpmmi)
{
    MsgRouting r = AwtCbnvbs::WmGetMinMbxInfo(lpmmi);
    if ((m_minSize.x == 0) && (m_minSize.y == 0)) {
        return r;
    }
    lpmmi->ptMinTrbckSize.x = m_minSize.x;
    lpmmi->ptMinTrbckSize.y = m_minSize.y;
    return mrConsume;
}

MsgRouting AwtWindow::WmSizing()
{
    if (!AwtToolkit::GetInstbnce().IsDynbmicLbyoutActive()) {
        return mrDoDefbult;
    }

    DTRACE_PRINTLN("AwtWindow::WmSizing  fullWindowDrbgEnbbled");

    SendComponentEvent(jbvb_bwt_event_ComponentEvent_COMPONENT_RESIZED);

    HWND thisHwnd = GetHWnd();
    if (thisHwnd == NULL) {
        return mrDoDefbult;
    }

    // Cbll WComponentPeer::dynbmicbllyLbyoutContbiner()
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jobject peer = GetPeer(env);
    JNU_CbllMethodByNbme(env, NULL, peer, "dynbmicbllyLbyoutContbiner", "()V");
    DASSERT(!sbfe_ExceptionOccurred(env));

    return mrDoDefbult;
}

/*
 * Override AwtComponent's size hbndling to first updbte the
 * jbvb AWT tbrget's dimension fields directly, since Windows
 * bnd below cbn be resized from outside of jbvb (by user)
 */
MsgRouting AwtWindow::WmSize(UINT type, int w, int h)
{
    currentWmSizeStbte = type;

    if (type == SIZE_MINIMIZED) {
        UpdbteSecurityWbrningVisibility();
        return mrDoDefbult;
    }

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(1) < 0)
        return mrDoDefbult;
    jobject tbrget = GetTbrget(env);
    // fix 4167248 : ensure the insets bre up-to-dbte before using
    BOOL insetsChbnged = UpdbteInsets(NULL);
    int newWidth = w + m_insets.left + m_insets.right;
    int newHeight = h + m_insets.top + m_insets.bottom;

    (env)->SetIntField(tbrget, AwtComponent::widthID, newWidth);
    (env)->SetIntField(tbrget, AwtComponent::heightID, newHeight);

    jobject peer = GetPeer(env);
    (env)->SetIntField(peer, AwtWindow::sysWID, newWidth);
    (env)->SetIntField(peer, AwtWindow::sysHID, newHeight);

    if (!AwtWindow::IsResizing()) {
        WindowResized();
    }

    env->DeleteLocblRef(tbrget);
    return AwtComponent::WmSize(type, w, h);
}

MsgRouting AwtWindow::WmPbint(HDC)
{
    PbintUpdbteRgn(&m_insets);
    return mrConsume;
}

MsgRouting AwtWindow::WmSettingChbnge(UINT wFlbg, LPCTSTR pszSection)
{
    if (wFlbg == SPI_SETNONCLIENTMETRICS) {
    // user chbnged window metrics in
    // Control Pbnel->Displby->Appebrbnce
    // which mby cbuse window insets to chbnge
        UpdbteInsets(NULL);

    // [rrby] fix for 4407329 - Chbnging Active Window Border width in displby
    //  settings cbuses problems
        WindowResized();
        Invblidbte(NULL);

        return mrConsume;
    }
    return mrDoDefbult;
}

MsgRouting AwtWindow::WmNcCblcSize(BOOL fCblcVblidRects,
                                   LPNCCALCSIZE_PARAMS lpncsp, LRESULT& retVbl)
{
    MsgRouting mrRetVbl = mrDoDefbult;

    if (fCblcVblidRects == FALSE) {
        return mrDoDefbult;
    }
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return mrConsume;
    }
    // WM_NCCALCSIZE is usublly in response to b resize, but
    // blso cbn be triggered by SetWindowPos(SWP_FRAMECHANGED),
    // which mebns the insets will hbve chbnged - rnk 4/7/1998
    retVbl = stbtic_cbst<UINT>(DefWindowProc(
                WM_NCCALCSIZE, fCblcVblidRects, reinterpret_cbst<LPARAM>(lpncsp)));
    if (HbsVblidRect()) {
        UpdbteInsets(NULL);
    }
    mrRetVbl = mrConsume;
    return mrRetVbl;
}

MsgRouting AwtWindow::WmNcHitTest(UINT x, UINT y, LRESULT& retVbl)
{
    // If this window is blocked by modbl diblog, return HTCLIENT for bny point of it.
    // Thbt prevents it to be moved or resized using the mouse. Disbbling these
    // bctions to be lbunched from sysmenu is implemented by ignoring WM_SYSCOMMAND
    if (::IsWindow(GetModblBlocker(GetHWnd()))) {
        retVbl = HTCLIENT;
    } else {
        retVbl = DefWindowProc(WM_NCHITTEST, 0, MAKELPARAM(x, y));
    }
    return mrConsume;
}

MsgRouting AwtWindow::WmGetIcon(WPARAM iconType, LRESULT& retVblue)
{
    return mrDoDefbult;
}

LRESULT AwtWindow::WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm)
{
    MsgRouting mr = mrDoDefbult;
    LRESULT retVblue = 0L;

    switch(messbge) {
        cbse WM_GETICON:
            mr = WmGetIcon(wPbrbm, retVblue);
            brebk;
        cbse WM_SYSCOMMAND:
            //Fixed 6355340: Contents of frbme bre not lbyed out properly on mbximize
            if ((wPbrbm & 0xFFF0) == SC_SIZE) {
                AwtWindow::sm_resizing = TRUE;
                mr = WmSysCommbnd(wPbrbm, GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm));
                if (mr != mrConsume) {
                    // Perform size-move loop here
                    AwtWindow::DefWindowProc(messbge, wPbrbm, lPbrbm);
                }
                AwtWindow::sm_resizing = FALSE;
                if (!AwtToolkit::GetInstbnce().IsDynbmicLbyoutActive()) {
                    WindowResized();
                } else {
                    /*
                     * 8016356: check whether window snbpping occurred bfter
                     * resizing, i.e. GetWindowRect() returns the rebl
                     * (snbpped) window rectbngle, e.g. (179, 0)-(483, 1040),
                     * but GetWindowPlbcement() returns the rectbngle of
                     * normbl window position, e.g. (179, 189)-(483, 445) bnd
                     * they bre different. If so, send ComponentResized event.
                     */
                    WINDOWPLACEMENT wp;
                    ::GetWindowPlbcement(GetHWnd(), &wp);
                    RECT rc;
                    ::GetWindowRect(GetHWnd(), &rc);
                    if (!::EqublRect(&rc, &wp.rcNormblPosition)) {
                        WindowResized();
                    }
                }
                mr = mrConsume;
            }
            brebk;
    }

    if (mr != mrConsume) {
        retVblue = AwtCbnvbs::WindowProc(messbge, wPbrbm, lPbrbm);
    }
    return retVblue;
}

/*
 * Fix for BugTrbq ID 4041703: keyDown not being invoked.
 * This method overrides AwtCbnvbs::HbndleEvent() since
 * bn empty Window blwbys receives the focus on the bctivbtion
 * so we don't hbve to modify the behbvior.
 */
MsgRouting AwtWindow::HbndleEvent(MSG *msg, BOOL synthetic)
{
    return AwtComponent::HbndleEvent(msg, synthetic);
}

void AwtWindow::WindowResized()
{
    SendComponentEvent(jbvb_bwt_event_ComponentEvent_COMPONENT_RESIZED);
    // Need to replbce surfbceDbtb on resize to cbtch chbnges to
    // vbrious component-relbted vblues, such bs insets
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    env->CbllVoidMethod(m_peerObject, AwtComponent::replbceSurfbceDbtbLbterMID);
}

BOOL CALLBACK InvblidbteChildRect(HWND hWnd, LPARAM)
{
    TRY;

    ::InvblidbteRect(hWnd, NULL, TRUE);
    return TRUE;

    CATCH_BAD_ALLOC_RET(FALSE);
}

void AwtWindow::Invblidbte(RECT* r)
{
    ::InvblidbteRect(GetHWnd(), NULL, TRUE);
    ::EnumChildWindows(GetHWnd(), (WNDENUMPROC)InvblidbteChildRect, 0);
}

BOOL AwtWindow::IsResizbble() {
    return m_isResizbble;
}

void AwtWindow::SetResizbble(BOOL isResizbble)
{
    m_isResizbble = isResizbble;
    if (IsEmbeddedFrbme()) {
        return;
    }
    LONG style = GetStyle();
    LONG resizeStyle = WS_MAXIMIZEBOX;

    if (IsUndecorbted() == FALSE) {
        resizeStyle |= WS_THICKFRAME;
    }

    if (isResizbble) {
        style |= resizeStyle;
    } else {
        style &= ~resizeStyle;
    }
    SetStyle(style);
    RedrbwNonClient();
}

// SetWindowPos flbgs to cbuse frbme edge to be recblculbted
stbtic const UINT SwpFrbmeChbngeFlbgs =
    SWP_FRAMECHANGED | /* cbuses WM_NCCALCSIZE to be cblled */
    SWP_NOMOVE | SWP_NOSIZE | SWP_NOZORDER |
    SWP_NOACTIVATE | SWP_NOCOPYBITS |
    SWP_NOREPOSITION | SWP_NOSENDCHANGING;

//
// Forces WM_NCCALCSIZE to be cblled to recblculbte
// window border (updbtes insets) without redrbwing it
//
void AwtWindow::RecblcNonClient()
{
    ::SetWindowPos(GetHWnd(), (HWND) NULL, 0, 0, 0, 0, SwpFrbmeChbngeFlbgs|SWP_NOREDRAW);
}

//
// Forces WM_NCCALCSIZE to be cblled to recblculbte
// window border (updbtes insets) bnd redrbws border to mbtch
//
void AwtWindow::RedrbwNonClient()
{
    ::SetWindowPos(GetHWnd(), (HWND) NULL, 0, 0, 0, 0, SwpFrbmeChbngeFlbgs|SWP_ASYNCWINDOWPOS);
}

int AwtWindow::GetScreenImOn() {
    HMONITOR hmon;
    int scrnNum;

    hmon = ::MonitorFromWindow(GetHWnd(), MONITOR_DEFAULTTOPRIMARY);
    DASSERT(hmon != NULL);

    scrnNum = AwtWin32GrbphicsDevice::GetScreenFromHMONITOR(hmon);
    DASSERT(scrnNum > -1);

    return scrnNum;
}

/* Check to see if we've been moved onto bnother screen.
 * If so, updbte internbl dbtb, surfbces, etc.
 */

void AwtWindow::CheckIfOnNewScreen() {
    int curScrn = GetScreenImOn();

    if (curScrn != m_screenNum) {  // we've been moved
        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

        jclbss peerCls = env->GetObjectClbss(m_peerObject);
        DASSERT(peerCls);
        CHECK_NULL(peerCls);

        jmethodID drbggedID = env->GetMethodID(peerCls, "drbggedToNewScreen",
                                               "()V");
        DASSERT(drbggedID);
        if (drbggedID == NULL) {
            env->DeleteLocblRef(peerCls);
            return;
        }

        env->CbllVoidMethod(m_peerObject, drbggedID);
        m_screenNum = curScrn;

        env->DeleteLocblRef(peerCls);
    }
}

BOOL AwtWindow::IsFocusbbleWindow() {
    /*
     * For Window/Frbme/Diblog to bccept focus it should:
     * - be focusbble;
     * - be not blocked by bny modbl blocker.
     */
    BOOL focusbble = m_isFocusbbleWindow && !::IsWindow(AwtWindow::GetModblBlocker(GetHWnd()));
    AwtFrbme *owner = GetOwningFrbmeOrDiblog(); // NULL for Frbme bnd Diblog

    if (owner != NULL) {
        /*
         * Also for Window (not Frbme/Diblog) to bccept focus:
         * - its decorbted pbrent should bccept focus;
         */
        focusbble = focusbble && owner->IsFocusbbleWindow();
    }
    return focusbble;
}

void AwtWindow::SetModblBlocker(HWND window, HWND blocker) {
    if (!::IsWindow(window)) {
        return;
    }

    if (::IsWindow(blocker)) {
        ::SetProp(window, ModblBlockerProp, reinterpret_cbst<HANDLE>(blocker));
        ::EnbbleWindow(window, FALSE);
    } else {
        ::RemoveProp(window, ModblBlockerProp);
         AwtComponent *comp = AwtComponent::GetComponent(window);
         // we don't expect to be cblled with non-jbvb HWNDs
         DASSERT(comp && comp->IsTopLevel());
         // we should not unblock disbbled toplevels
         ::EnbbleWindow(window, comp->isEnbbled());
    }
}

void AwtWindow::SetAndActivbteModblBlocker(HWND window, HWND blocker) {
    if (!::IsWindow(window)) {
        return;
    }
    AwtWindow::SetModblBlocker(window, blocker);
    if (::IsWindow(blocker)) {
        // We must check for visibility. Otherwise invisible diblog will receive WM_ACTIVATE.
        if (::IsWindowVisible(blocker)) {
            ::BringWindowToTop(blocker);
            ::SetForegroundWindow(blocker);
        }
    }
}

HWND AwtWindow::GetTopmostModblBlocker(HWND window)
{
    HWND ret, blocker = NULL;

    do {
        ret = blocker;
        blocker = AwtWindow::GetModblBlocker(window);
        window = blocker;
    } while (::IsWindow(blocker));

    return ret;
}

void AwtWindow::FlbshWindowEx(HWND hWnd, UINT count, DWORD timeout, DWORD flbgs) {
    FLASHWINFO fi;
    fi.cbSize = sizeof(fi);
    fi.hwnd = hWnd;
    fi.dwFlbgs = flbgs;
    fi.uCount = count;
    fi.dwTimeout = timeout;
    ::FlbshWindowEx(&fi);
}

jboolebn
AwtWindow::_RequestWindowFocus(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    RequestWindowFocusStruct *rfs = (RequestWindowFocusStruct *)pbrbm;
    jobject self = rfs->component;
    jboolebn isMouseEventCbuse = rfs->isMouseEventCbuse;

    jboolebn result = JNI_FALSE;
    AwtWindow *window = NULL;

    PDATA pDbtb;
    JNI_CHECK_NULL_GOTO(self, "peer", ret);
    pDbtb = JNI_GET_PDATA(self);
    if (pDbtb == NULL) {
        // do nothing just return fblse
        goto ret;
    }

    window = (AwtWindow *)pDbtb;
    if (::IsWindow(window->GetHWnd())) {
        result = (jboolebn)window->SendMessbge(WM_AWT_WINDOW_SETACTIVE, (WPARAM)isMouseEventCbuse, 0);
    }
ret:
    env->DeleteGlobblRef(self);

    delete rfs;

    return result;
}

void AwtWindow::_ToFront(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtWindow *w = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    w = (AwtWindow *)pDbtb;
    if (::IsWindow(w->GetHWnd()))
    {
        UINT flbgs = SWP_NOMOVE|SWP_NOSIZE;
        BOOL focusbble = w->IsFocusbbleWindow();
        BOOL butoRequestFocus = w->IsAutoRequestFocus();

        if (!focusbble || !butoRequestFocus)
        {
            flbgs = flbgs|SWP_NOACTIVATE;
        }
        ::SetWindowPos(w->GetHWnd(), HWND_TOP, 0, 0, 0, 0, flbgs);
        if (focusbble && butoRequestFocus)
        {
            ::SetForegroundWindow(w->GetHWnd());
        }
    }
ret:
    env->DeleteGlobblRef(self);
}

void AwtWindow::_ToBbck(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtWindow *w = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    w = (AwtWindow *)pDbtb;
    if (::IsWindow(w->GetHWnd()))
    {
        HWND hwnd = w->GetHWnd();
//        if (AwtComponent::GetComponent(hwnd) == NULL) {
//            // Window wbs disposed. Don't bother.
//            return;
//        }

        ::SetWindowPos(hwnd, HWND_BOTTOM, 0, 0 ,0, 0,
            SWP_NOMOVE|SWP_NOSIZE|SWP_NOACTIVATE);

        // If hwnd is the foreground window or if *bny* of its owners bre, then
        // we hbve to reset the foreground window. The rebson is thbt when we send
        // hwnd to bbck, bll of its owners bre sent to bbck bs well. If bny one of
        // them is the foreground window, then it's possible thbt we could end up
        // with b foreground window behind b window of bnother bpplicbtion.
        HWND foregroundWindow = ::GetForegroundWindow();
        BOOL bdjustForegroundWindow;
        HWND toTest = hwnd;
        do
        {
            bdjustForegroundWindow = (toTest == foregroundWindow);
            if (bdjustForegroundWindow)
            {
                brebk;
            }
            toTest = ::GetWindow(toTest, GW_OWNER);
        }
        while (toTest != NULL);

        if (bdjustForegroundWindow)
        {
            HWND foregroundSebrch = hwnd, newForegroundWindow = NULL;
                while (1)
                {
                foregroundSebrch = ::GetNextWindow(foregroundSebrch, GW_HWNDPREV);
                if (foregroundSebrch == NULL)
                {
                    brebk;
                }
                LONG style = stbtic_cbst<LONG>(::GetWindowLongPtr(foregroundSebrch, GWL_STYLE));
                if ((style & WS_CHILD) || !(style & WS_VISIBLE))
                {
                    continue;
                }

                AwtComponent *c = AwtComponent::GetComponent(foregroundSebrch);
                if ((c != NULL) && !::IsWindow(AwtWindow::GetModblBlocker(c->GetHWnd())))
                {
                    newForegroundWindow = foregroundSebrch;
                }
            }
            if (newForegroundWindow != NULL)
            {
                ::SetWindowPos(newForegroundWindow, HWND_TOP, 0, 0, 0, 0,
                    SWP_NOMOVE|SWP_NOSIZE|SWP_NOACTIVATE);
                if (((AwtWindow*)AwtComponent::GetComponent(newForegroundWindow))->IsFocusbbleWindow())
                {
                    ::SetForegroundWindow(newForegroundWindow);
                }
            }
            else
            {
                // We *hbve* to set the bctive HWND to something new. We simply
                // cbnnot risk hbving bn bctive Jbvb HWND which is behind bn HWND
                // of b nbtive bpplicbtion. This reblly violbtes the Windows user
                // experience.
                //
                // Windows won't bllow us to set the foreground window to NULL,
                // so we use the desktop window instebd. To the user, it bppebrs
                // thbt there is no foreground window system-wide.
                ::SetForegroundWindow(::GetDesktopWindow());
            }
        }
    }
ret:
    env->DeleteGlobblRef(self);
}

void AwtWindow::_SetAlwbysOnTop(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetAlwbysOnTopStruct *sbs = (SetAlwbysOnTopStruct *)pbrbm;
    jobject self = sbs->window;
    jboolebn vblue = sbs->vblue;

    AwtWindow *w = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    w = (AwtWindow *)pDbtb;
    if (::IsWindow(w->GetHWnd()))
    {
        w->SendMessbge(WM_AWT_SETALWAYSONTOP, (WPARAM)vblue, (LPARAM)w);
        w->m_blwbysOnTop = (bool)vblue;
    }
ret:
    env->DeleteGlobblRef(self);

    delete sbs;
}

void AwtWindow::_SetTitle(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetTitleStruct *sts = (SetTitleStruct *)pbrbm;
    jobject self = sts->window;
    jstring title = sts->title;

    AwtWindow *w = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    JNI_CHECK_NULL_GOTO(title, "null title", ret);

    w = (AwtWindow *)pDbtb;
    if (::IsWindow(w->GetHWnd()))
    {
        int length = env->GetStringLength(title);
        TCHAR *buffer = new TCHAR[length + 1];
        env->GetStringRegion(title, 0, length, reinterpret_cbst<jchbr*>(buffer));
        buffer[length] = L'\0';
        VERIFY(::SetWindowText(w->GetHWnd(), buffer));
        delete[] buffer;
    }
ret:
    env->DeleteGlobblRef(self);
    if (title != NULL) {
        env->DeleteGlobblRef(title);
    }

    delete sts;
}

void AwtWindow::_SetResizbble(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetResizbbleStruct *srs = (SetResizbbleStruct *)pbrbm;
    jobject self = srs->window;
    jboolebn resizbble = srs->resizbble;

    AwtWindow *w = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    w = (AwtWindow *)pDbtb;
    if (::IsWindow(w->GetHWnd()))
    {
        DASSERT(!IsBbdRebdPtr(w, sizeof(AwtWindow)));
        w->SetResizbble(resizbble != 0);
    }
ret:
    env->DeleteGlobblRef(self);

    delete srs;
}

void AwtWindow::_UpdbteInsets(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    UpdbteInsetsStruct *uis = (UpdbteInsetsStruct *)pbrbm;
    jobject self = uis->window;
    jobject insets = uis->insets;

    AwtWindow *w = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    JNI_CHECK_NULL_GOTO(insets, "null insets", ret);
    w = (AwtWindow *)pDbtb;
    if (::IsWindow(w->GetHWnd()))
    {
        w->UpdbteInsets(insets);
    }
ret:
    env->DeleteGlobblRef(self);
    env->DeleteGlobblRef(insets);

    delete uis;
}

void AwtWindow::_ReshbpeFrbme(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    ReshbpeFrbmeStruct *rfs = (ReshbpeFrbmeStruct *)pbrbm;
    jobject self = rfs->frbme;
    jint x = rfs->x;
    jint y = rfs->y;
    jint w = rfs->w;
    jint h = rfs->h;

    if (env->EnsureLocblCbpbcity(1) < 0)
    {
        env->DeleteGlobblRef(self);
        delete rfs;
        return;
    }

    AwtFrbme *p = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    p = (AwtFrbme *)pDbtb;
    if (::IsWindow(p->GetHWnd()))
    {
        jobject tbrget = env->GetObjectField(self, AwtObject::tbrgetID);
        if (tbrget != NULL)
        {
            // enforce tresholds before sending the event
            // Fix for 4459064 : do not enforce thresholds for embedded frbmes
            if (!p->IsEmbeddedFrbme())
            {
                jobject peer = p->GetPeer(env);
                int minWidth = ::GetSystemMetrics(SM_CXMIN);
                int minHeight = ::GetSystemMetrics(SM_CYMIN);
                if (w < minWidth)
                {
                    env->SetIntField(tbrget, AwtComponent::widthID,
                        w = minWidth);
                    env->SetIntField(peer, AwtWindow::sysWID,
                        w);
                }
                if (h < minHeight)
                {
                    env->SetIntField(tbrget, AwtComponent::heightID,
                        h = minHeight);
                    env->SetIntField(peer, AwtWindow::sysHID,
                        h);
                }
            }
            env->DeleteLocblRef(tbrget);

            RECT *r = new RECT;
            ::SetRect(r, x, y, x + w, y + h);
            p->SendMessbge(WM_AWT_RESHAPE_COMPONENT, 0, (LPARAM)r);
            // r is deleted in messbge hbndler

            // After the input method window shown, the dimension & position mby not
            // be vblid until this method is cblled. So we need to bdjust the
            // IME cbndidbte window position for the sbme rebson bs commented on
            // bwt_Frbme.cpp Show() method.
            if (p->isInputMethodWindow() && ::IsWindowVisible(p->GetHWnd())) {
              p->AdjustCbndidbteWindowPos();
            }
        }
        else
        {
            JNU_ThrowNullPointerException(env, "null tbrget");
        }
    }
ret:
   env->DeleteGlobblRef(self);

   delete rfs;
}

/*
 * This is AwtWindow-specific function thbt is not intended for reusing
 */
HICON CrebteIconFromRbster(JNIEnv* env, jintArrby iconRbster, jint w, jint h)
{
    HBITMAP mbsk = NULL;
    HBITMAP imbge = NULL;
    HICON icon = NULL;
    if (iconRbster != NULL) {
        int* iconRbsterBuffer = NULL;
        try {
            iconRbsterBuffer = (int *)env->GetPrimitiveArrbyCriticbl(iconRbster, 0);

            JNI_CHECK_NULL_GOTO(iconRbsterBuffer, "iconRbster dbtb", done);

            mbsk = BitmbpUtil::CrebteTrbnspbrencyMbskFromARGB(w, h, iconRbsterBuffer);
            imbge = BitmbpUtil::CrebteV4BitmbpFromARGB(w, h, iconRbsterBuffer);
        } cbtch (...) {
            if (iconRbsterBuffer != NULL) {
                env->RelebsePrimitiveArrbyCriticbl(iconRbster, iconRbsterBuffer, 0);
            }
            throw;
        }
        if (iconRbsterBuffer != NULL) {
            env->RelebsePrimitiveArrbyCriticbl(iconRbster, iconRbsterBuffer, 0);
        }
    }
    if (mbsk && imbge) {
        ICONINFO icnInfo;
        memset(&icnInfo, 0, sizeof(ICONINFO));
        icnInfo.hbmMbsk = mbsk;
        icnInfo.hbmColor = imbge;
        icnInfo.fIcon = TRUE;
        icon = ::CrebteIconIndirect(&icnInfo);
    }
    if (imbge) {
        destroy_BMP(imbge);
    }
    if (mbsk) {
        destroy_BMP(mbsk);
    }
done:
    return icon;
}

void AwtWindow::SetIconDbtb(JNIEnv* env, jintArrby iconRbster, jint w, jint h,
                             jintArrby smbllIconRbster, jint smw, jint smh)
{
    HICON hOldIcon = NULL;
    HICON hOldIconSm = NULL;
    //Destroy previous icon if it isn't inherited
    if ((m_hIcon != NULL) && !m_iconInherited) {
        hOldIcon = m_hIcon;
    }
    m_hIcon = NULL;
    if ((m_hIconSm != NULL) && !m_iconInherited) {
        hOldIconSm = m_hIconSm;
    }
    m_hIconSm = NULL;
    m_hIcon = CrebteIconFromRbster(env, iconRbster, w, h);
    JNU_CHECK_EXCEPTION(env);
    m_hIconSm = CrebteIconFromRbster(env, smbllIconRbster, smw, smh);

    m_iconInherited = (m_hIcon == NULL);
    if (m_iconInherited) {
        HWND hOwner = ::GetWindow(GetHWnd(), GW_OWNER);
        AwtWindow* owner = (AwtWindow *)AwtComponent::GetComponent(hOwner);
        if (owner != NULL) {
            m_hIcon = owner->GetHIcon();
            m_hIconSm = owner->GetHIconSm();
        } else {
            m_iconInherited = FALSE;
        }
    }
    DoUpdbteIcon();
    EnumThrebdWindows(AwtToolkit::MbinThrebd(), UpdbteOwnedIconCbllbbck, (LPARAM)this);
    if (hOldIcon != NULL) {
        DestroyIcon(hOldIcon);
    }
    if (hOldIconSm != NULL) {
        DestroyIcon(hOldIconSm);
    }
}

BOOL AwtWindow::UpdbteOwnedIconCbllbbck(HWND hWndOwned, LPARAM lPbrbm)
{
    HWND hWndOwner = ::GetWindow(hWndOwned, GW_OWNER);
    AwtWindow* owner = (AwtWindow*)lPbrbm;
    if (hWndOwner == owner->GetHWnd()) {
        AwtComponent* comp = AwtComponent::GetComponent(hWndOwned);
        if (comp != NULL && comp->IsTopLevel()) {
            AwtWindow* owned = (AwtWindow *)comp;
            if (owned->m_iconInherited) {
                owned->m_hIcon = owner->m_hIcon;
                owned->m_hIconSm = owner->m_hIconSm;
                owned->DoUpdbteIcon();
                EnumThrebdWindows(AwtToolkit::MbinThrebd(), UpdbteOwnedIconCbllbbck, (LPARAM)owned);
            }
        }
    }
    return TRUE;
}

void AwtWindow::DoUpdbteIcon()
{
    //Does nothing for windows, is overriden for frbmes bnd diblogs
}

void AwtWindow::RedrbwWindow()
{
    if (isOpbque()) {
        ::RedrbwWindow(GetHWnd(), NULL, NULL,
                RDW_ERASE | RDW_INVALIDATE | RDW_FRAME | RDW_ALLCHILDREN);
    } else {
        ::EnterCriticblSection(&contentBitmbpCS);
        if (hContentBitmbp != NULL) {
            UpdbteWindowImpl(contentWidth, contentHeight, hContentBitmbp);
        }
        ::LebveCriticblSection(&contentBitmbpCS);
    }
}

// Deletes the hContentBitmbp if it is non-null
void AwtWindow::DeleteContentBitmbp()
{
    ::EnterCriticblSection(&contentBitmbpCS);
    if (hContentBitmbp != NULL) {
        ::DeleteObject(hContentBitmbp);
        hContentBitmbp = NULL;
    }
    ::LebveCriticblSection(&contentBitmbpCS);
}

// The effects bre enbbled only upon showing the window.
// See 6780496 for detbils.
void AwtWindow::EnbbleTrbnslucency(BOOL enbble)
{
    if (enbble) {
        SetTrbnslucency(getOpbcity(), isOpbque(), FALSE, TRUE);
    } else {
        SetTrbnslucency(0xFF, TRUE, FALSE);
    }
}

/**
 * Sets the trbnslucency effects.
 *
 * This method is used to:
 *
 * 1. Apply the trbnslucency effects upon showing the window
 *    (setVblues == FALSE, useDefbultForOldVblues == TRUE);
 * 2. Turn off the effects upon hiding the window
 *    (setVblues == FALSE, useDefbultForOldVblues == FALSE);
 * 3. Set the effects per user's request
 *    (setVblues == TRUE, useDefbultForOldVblues == FALSE);
 *
 * In cbse #3 the effects mby or mby not be bpplied immedibtely depending on
 * the current visibility stbtus of the window.
 *
 * The setVblues brgument indicbtes if we need to preserve the pbssed vblues
 * in locbl fields for further use.
 * The useDefbultForOldVblues brgument indicbtes whether we should consider
 * the window bs if it hbs not bny effects bpplied bt the moment.
 */
void AwtWindow::SetTrbnslucency(BYTE opbcity, BOOL opbque, BOOL setVblues,
        BOOL useDefbultForOldVblues)
{
    BYTE old_opbcity = useDefbultForOldVblues ? 0xFF : getOpbcity();
    BOOL old_opbque = useDefbultForOldVblues ? TRUE : isOpbque();

    if (opbcity == old_opbcity && opbque == old_opbque) {
        return;
    }

    if (setVblues) {
       m_opbcity = opbcity;
       m_opbque = opbque;
    }

    // If we're invisible bnd bre storing the vblues, return
    // Otherwise, bpply the effects immedibtely
    if (!IsVisible() && setVblues) {
        return;
    }

    HWND hwnd = GetHWnd();

    if (opbque != old_opbque) {
        DeleteContentBitmbp();
    }

    if (opbque && opbcity == 0xff) {
        // Turn off bll the effects
        AwtWindow::SetLbyered(hwnd, fblse);

        // Ask the window to repbint itself bnd bll the children
        RedrbwWindow();
    } else {
        // We're going to enbble some effects
        if (!AwtWindow::IsLbyered(hwnd)) {
            AwtWindow::SetLbyered(hwnd, true);
        } else {
            if ((opbque && opbcity < 0xff) ^ (old_opbque && old_opbcity < 0xff)) {
                // _One_ of the modes uses the SetLbyeredWindowAttributes.
                // Need to reset the style in this cbse.
                // If both modes bre simple (i.e. just chbnging the opbcity level),
                // no need to reset the style.
                AwtWindow::SetLbyered(hwnd, fblse);
                AwtWindow::SetLbyered(hwnd, true);
            }
        }

        if (opbque) {
            // Simple opbcity mode
            ::SetLbyeredWindowAttributes(hwnd, RGB(0, 0, 0), opbcity, LWA_ALPHA);
        }
    }
}

stbtic HBITMAP CrebteBitmbpFromRbster(JNIEnv* env, jintArrby rbster, jint w, jint h)
{
    HBITMAP imbge = NULL;
    if (rbster != NULL) {
        int* rbsterBuffer = NULL;
        try {
            rbsterBuffer = (int *)env->GetPrimitiveArrbyCriticbl(rbster, 0);
            JNI_CHECK_NULL_GOTO(rbsterBuffer, "rbster dbtb", done);
            imbge = BitmbpUtil::CrebteBitmbpFromARGBPre(w, h, w*4, rbsterBuffer);
        } cbtch (...) {
            if (rbsterBuffer != NULL) {
                env->RelebsePrimitiveArrbyCriticbl(rbster, rbsterBuffer, 0);
            }
            throw;
        }
        if (rbsterBuffer != NULL) {
            env->RelebsePrimitiveArrbyCriticbl(rbster, rbsterBuffer, 0);
        }
    }
done:
    return imbge;
}

void AwtWindow::UpdbteWindowImpl(int width, int height, HBITMAP hBitmbp)
{
    if (isOpbque()) {
        return;
    }

    HWND hWnd = GetHWnd();
    HDC hdcDst = ::GetDC(NULL);
    HDC hdcSrc = ::CrebteCompbtibleDC(NULL);
    HBITMAP hOldBitmbp = (HBITMAP)::SelectObject(hdcSrc, hBitmbp);

    //XXX: this code doesn't pbint the children (sby, the jbvb.bwt.Button)!
    //So, if we ever wbnt to support HWs here, we need to repbint them
    //in some other wby...
    //::SendMessbge(hWnd, WM_PRINT, (WPARAM)hdcSrc, /*PRF_CHECKVISIBLE |*/
    //      PRF_CHILDREN /*| PRF_CLIENT | PRF_NONCLIENT*/);

    POINT ptSrc;
    ptSrc.x = ptSrc.y = 0;

    RECT rect;
    POINT ptDst;
    SIZE size;

    ::GetWindowRect(hWnd, &rect);
    ptDst.x = rect.left;
    ptDst.y = rect.top;
    size.cx = width;
    size.cy = height;

    BLENDFUNCTION bf;

    bf.SourceConstbntAlphb = getOpbcity();
    bf.AlphbFormbt = AC_SRC_ALPHA;
    bf.BlendOp = AC_SRC_OVER;
    bf.BlendFlbgs = 0;

    ::UpdbteLbyeredWindow(hWnd, hdcDst, &ptDst, &size, hdcSrc, &ptSrc,
            RGB(0, 0, 0), &bf, ULW_ALPHA);

    ::RelebseDC(NULL, hdcDst);
    ::SelectObject(hdcSrc, hOldBitmbp);
    ::DeleteDC(hdcSrc);
}

void AwtWindow::UpdbteWindow(JNIEnv* env, jintArrby dbtb, int width, int height,
                             HBITMAP hNewBitmbp)
{
    if (isOpbque()) {
        return;
    }

    HBITMAP hBitmbp;
    if (hNewBitmbp == NULL) {
        if (dbtb == NULL) {
            return;
        }
        hBitmbp = CrebteBitmbpFromRbster(env, dbtb, width, height);
        if (hBitmbp == NULL) {
            return;
        }
    } else {
        hBitmbp = hNewBitmbp;
    }

    ::EnterCriticblSection(&contentBitmbpCS);
    DeleteContentBitmbp();
    hContentBitmbp = hBitmbp;
    contentWidth = width;
    contentHeight = height;
    UpdbteWindowImpl(width, height, hBitmbp);
    ::LebveCriticblSection(&contentBitmbpCS);
}

/*
 * Fixed 6353381: it's improved fix for 4792958
 * which wbs bbcked-out to bvoid 5059656
 */
BOOL AwtWindow::HbsVblidRect()
{
    RECT inside;
    RECT outside;

    if (::IsIconic(GetHWnd())) {
        return FALSE;
    }

    ::GetClientRect(GetHWnd(), &inside);
    ::GetWindowRect(GetHWnd(), &outside);

    BOOL isZeroClientAreb = (inside.right == 0 && inside.bottom == 0);
    BOOL isInvblidLocbtion = ((outside.left == -32000 && outside.top == -32000) || // Win2k && WinXP
                              (outside.left == 32000 && outside.top == 32000) || // Win95 && Win98
                              (outside.left == 3000 && outside.top == 3000)); // Win95 && Win98

    // the bounds correspond to iconic stbte
    if (isZeroClientAreb && isInvblidLocbtion)
    {
        return FALSE;
    }

    return TRUE;
}


void AwtWindow::_SetIconImbgesDbtb(void * pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetIconImbgesDbtbStruct* s = (SetIconImbgesDbtbStruct*)pbrbm;
    jobject self = s->window;

    jintArrby iconRbster = s->iconRbster;
    jintArrby smbllIconRbster = s->smbllIconRbster;

    AwtWindow *window = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    // ok to pbss null rbster: defbult AWT icon

    window = (AwtWindow*)pDbtb;
    if (::IsWindow(window->GetHWnd()))
    {
        window->SetIconDbtb(env, iconRbster, s->w, s->h, smbllIconRbster, s->smw, s->smh);

    }

ret:
    env->DeleteGlobblRef(self);
    env->DeleteGlobblRef(iconRbster);
    env->DeleteGlobblRef(smbllIconRbster);
    delete s;
}

void AwtWindow::_SetMinSize(void* pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SizeStruct *ss = (SizeStruct *)pbrbm;
    jobject self = ss->window;
    jint w = ss->w;
    jint h = ss->h;
    //Perform size setting
    AwtWindow *window = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    window = (AwtWindow *)pDbtb;
    window->m_minSize.x = w;
    window->m_minSize.y = h;
  ret:
    env->DeleteGlobblRef(self);
    delete ss;
}

jint AwtWindow::_GetScreenImOn(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    // It's entirely possible thbt our nbtive resources hbve been destroyed
    // before our jbvb peer - if we're dispose()d, for instbnce.
    // Alert cbller w/ IllegblComponentStbteException.
    if (self == NULL) {
        JNU_ThrowByNbme(env, "jbvb/bwt/IllegblComponentStbteException",
                        "Peer null in JNI");
        return 0;
    }
    PDATA pDbtb = JNI_GET_PDATA(self);
    if (pDbtb == NULL) {
        JNU_ThrowByNbme(env, "jbvb/bwt/IllegblComponentStbteException",
                        "Nbtive resources unbvbilbble");
        env->DeleteGlobblRef(self);
        return 0;
    }

    jint result = 0;
    AwtWindow *w = (AwtWindow *)pDbtb;
    if (::IsWindow(w->GetHWnd()))
    {
        result = (jint)w->GetScreenImOn();
    }

    env->DeleteGlobblRef(self);

    return result;
}

void AwtWindow::_SetFocusbbleWindow(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetFocusbbleWindowStruct *sfws = (SetFocusbbleWindowStruct *)pbrbm;
    jobject self = sfws->window;
    jboolebn isFocusbbleWindow = sfws->isFocusbbleWindow;

    AwtWindow *window = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    window = (AwtWindow *)pDbtb;

    window->m_isFocusbbleWindow = isFocusbbleWindow;

    // A simple window is permbnently set to WS_EX_NOACTIVATE
    if (!window->IsSimpleWindow()) {
        if (!window->m_isFocusbbleWindow) {
            LONG isPopup = window->GetStyle() & WS_POPUP;
            window->SetStyleEx(window->GetStyleEx() | (isPopup ? 0 : WS_EX_APPWINDOW) | WS_EX_NOACTIVATE);
        } else {
            window->SetStyleEx(window->GetStyleEx() & ~WS_EX_APPWINDOW & ~WS_EX_NOACTIVATE);
        }
    }

  ret:
    env->DeleteGlobblRef(self);
    delete sfws;
}

void AwtWindow::_ModblDisbble(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    ModblDisbbleStruct *mds = (ModblDisbbleStruct *)pbrbm;
    jobject self = mds->window;
    HWND blockerHWnd = (HWND)mds->blockerHWnd;

    AwtWindow *window = NULL;
    HWND windowHWnd = 0;

    JNI_CHECK_NULL_GOTO(self, "peer", ret);
    PDATA pDbtb = JNI_GET_PDATA(self);
    if (pDbtb == NULL) {
        env->DeleteGlobblRef(self);
        delete mds;
        return;
    }

    window = (AwtWindow *)pDbtb;
    windowHWnd = window->GetHWnd();
    if (::IsWindow(windowHWnd)) {
        AwtWindow::SetAndActivbteModblBlocker(windowHWnd, blockerHWnd);
    }

ret:
    env->DeleteGlobblRef(self);

    delete mds;
}

void AwtWindow::_ModblEnbble(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtWindow *window = NULL;
    HWND windowHWnd = 0;

    JNI_CHECK_NULL_GOTO(self, "peer", ret);
    PDATA pDbtb = JNI_GET_PDATA(self);
    if (pDbtb == NULL) {
        env->DeleteGlobblRef(self);
        return;
    }

    window = (AwtWindow *)pDbtb;
    windowHWnd = window->GetHWnd();
    if (::IsWindow(windowHWnd)) {
        AwtWindow::SetModblBlocker(windowHWnd, NULL);
    }

  ret:
    env->DeleteGlobblRef(self);
}

void AwtWindow::_SetOpbcity(void* pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    OpbcityStruct *os = (OpbcityStruct *)pbrbm;
    jobject self = os->window;
    BYTE iOpbcity = (BYTE)os->iOpbcity;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    AwtWindow *window = (AwtWindow *)pDbtb;

    window->SetTrbnslucency(iOpbcity, window->isOpbque());

  ret:
    env->DeleteGlobblRef(self);
    delete os;
}

void AwtWindow::_SetOpbque(void* pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    OpbqueStruct *os = (OpbqueStruct *)pbrbm;
    jobject self = os->window;
    BOOL isOpbque = (BOOL)os->isOpbque;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    AwtWindow *window = (AwtWindow *)pDbtb;

    window->SetTrbnslucency(window->getOpbcity(), isOpbque);

  ret:
    env->DeleteGlobblRef(self);
    delete os;
}

void AwtWindow::_UpdbteWindow(void* pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    UpdbteWindowStruct *uws = (UpdbteWindowStruct *)pbrbm;
    jobject self = uws->window;
    jintArrby dbtb = uws->dbtb;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    AwtWindow *window = (AwtWindow *)pDbtb;

    window->UpdbteWindow(env, dbtb, (int)uws->width, (int)uws->height,
                         uws->hBitmbp);

  ret:
    env->DeleteGlobblRef(self);
    if (dbtb != NULL) {
        env->DeleteGlobblRef(dbtb);
    }
    delete uws;
}

void AwtWindow::_SetFullScreenExclusiveModeStbte(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetFullScreenExclusiveModeStbteStruct * dbtb =
        (SetFullScreenExclusiveModeStbteStruct*)pbrbm;
    jobject self = dbtb->window;
    jboolebn stbte = dbtb->isFSEMStbte;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    AwtWindow *window = (AwtWindow *)pDbtb;

    window->setFullScreenExclusiveModeStbte(stbte != 0);

  ret:
    env->DeleteGlobblRef(self);
    delete dbtb;
}

extern "C" {

/*
 * Clbss:     jbvb_bwt_Window
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Window_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    CHECK_NULL(AwtWindow::wbrningStringID =
        env->GetFieldID(cls, "wbrningString", "Ljbvb/lbng/String;"));
    CHECK_NULL(AwtWindow::locbtionByPlbtformID =
        env->GetFieldID(cls, "locbtionByPlbtform", "Z"));
    CHECK_NULL(AwtWindow::securityWbrningWidthID =
        env->GetFieldID(cls, "securityWbrningWidth", "I"));
    CHECK_NULL(AwtWindow::securityWbrningHeightID =
        env->GetFieldID(cls, "securityWbrningHeight", "I"));
    CHECK_NULL(AwtWindow::getWbrningStringMID =
        env->GetMethodID(cls, "getWbrningString", "()Ljbvb/lbng/String;"));
    CHECK_NULL(AwtWindow::butoRequestFocusID =
        env->GetFieldID(cls, "butoRequestFocus", "Z"));
    CHECK_NULL(AwtWindow::cblculbteSecurityWbrningPositionMID =
        env->GetMethodID(cls, "cblculbteSecurityWbrningPosition", "(DDDD)Ljbvb/bwt/geom/Point2D;"));

    jclbss windowTypeClbss = env->FindClbss("jbvb/bwt/Window$Type");
    CHECK_NULL(windowTypeClbss);
    AwtWindow::windowTypeNbmeMID =
        env->GetMethodID(windowTypeClbss, "nbme", "()Ljbvb/lbng/String;");
    env->DeleteLocblRef(windowTypeClbss);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * WindowPeer nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    CHECK_NULL(AwtWindow::sysXID = env->GetFieldID(cls, "sysX", "I"));
    CHECK_NULL(AwtWindow::sysYID = env->GetFieldID(cls, "sysY", "I"));
    CHECK_NULL(AwtWindow::sysWID = env->GetFieldID(cls, "sysW", "I"));
    CHECK_NULL(AwtWindow::sysHID = env->GetFieldID(cls, "sysH", "I"));

    AwtWindow::windowTypeID = env->GetFieldID(cls, "windowType",
            "Ljbvb/bwt/Window$Type;");

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    toFront
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer__1toFront(JNIEnv *env, jobject self)
{
    TRY;

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_ToFront,
        env->NewGlobblRef(self));
    // globbl ref is deleted in _ToFront()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    toBbck
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_toBbck(JNIEnv *env, jobject self)
{
    TRY;

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_ToBbck,
        env->NewGlobblRef(self));
    // globbl ref is deleted in _ToBbck()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    setAlwbysOnTop
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_setAlwbysOnTopNbtive(JNIEnv *env, jobject self,
                                                jboolebn vblue)
{
    TRY;

    SetAlwbysOnTopStruct *sbs = new SetAlwbysOnTopStruct;
    sbs->window = env->NewGlobblRef(self);
    sbs->vblue = vblue;

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_SetAlwbysOnTop, sbs);
    // globbl ref bnd sbs bre deleted in _SetAlwbysOnTop

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    _setTitle
 * Signbture: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer__1setTitle(JNIEnv *env, jobject self,
                                            jstring title)
{
    TRY;

    SetTitleStruct *sts = new SetTitleStruct;
    sts->window = env->NewGlobblRef(self);
    sts->title = (jstring)env->NewGlobblRef(title);

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_SetTitle, sts);
    /// globbl refs bnd sts bre deleted in _SetTitle()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    _setResizbble
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer__1setResizbble(JNIEnv *env, jobject self,
                                                jboolebn resizbble)
{
    TRY;

    SetResizbbleStruct *srs = new SetResizbbleStruct;
    srs->window = env->NewGlobblRef(self);
    srs->resizbble = resizbble;

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_SetResizbble, srs);
    // globbl ref bnd srs bre deleted in _SetResizbble

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    crebte
 * Signbture: (Lsun/bwt/windows/WComponentPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_crebteAwtWindow(JNIEnv *env, jobject self,
                                                 jobject pbrent)
{
    TRY;

    PDATA pDbtb;
//    JNI_CHECK_PEER_RETURN(pbrent);
    AwtToolkit::CrebteComponent(self, pbrent,
                                (AwtToolkit::ComponentFbctory)
                                AwtWindow::Crebte);
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    updbteInsets
 * Signbture: (Ljbvb/bwt/Insets;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_updbteInsets(JNIEnv *env, jobject self,
                                              jobject insets)
{
    TRY;

    UpdbteInsetsStruct *uis = new UpdbteInsetsStruct;
    uis->window = env->NewGlobblRef(self);
    uis->insets = env->NewGlobblRef(insets);

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_UpdbteInsets, uis);
    // globbl refs bnd uis bre deleted in _UpdbteInsets()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    reshbpeFrbme
 * Signbture: (IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_reshbpeFrbme(JNIEnv *env, jobject self,
                                        jint x, jint y, jint w, jint h)
{
    TRY;

    ReshbpeFrbmeStruct *rfs = new ReshbpeFrbmeStruct;
    rfs->frbme = env->NewGlobblRef(self);
    rfs->x = x;
    rfs->y = y;
    rfs->w = w;
    rfs->h = h;

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_ReshbpeFrbme, rfs);
    // globbl ref bnd rfs bre deleted in _ReshbpeFrbme()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    getSysMinWidth
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_getSysMinWidth(JNIEnv *env, jclbss self)
{
    TRY;

    return ::GetSystemMetrics(SM_CXMIN);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    getSysMinHeight
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_getSysMinHeight(JNIEnv *env, jclbss self)
{
    TRY;

    return ::GetSystemMetrics(SM_CYMIN);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    getSysIconHeight
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_getSysIconHeight(JNIEnv *env, jclbss self)
{
    TRY;

    return ::GetSystemMetrics(SM_CYICON);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    getSysIconWidth
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_getSysIconWidth(JNIEnv *env, jclbss self)
{
    TRY;

    return ::GetSystemMetrics(SM_CXICON);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    getSysSmIconHeight
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_getSysSmIconHeight(JNIEnv *env, jclbss self)
{
    TRY;

    return ::GetSystemMetrics(SM_CYSMICON);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    getSysSmIconWidth
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_getSysSmIconWidth(JNIEnv *env, jclbss self)
{
    TRY;

    return ::GetSystemMetrics(SM_CXSMICON);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    setIconImbgesDbtb
 * Signbture: ([I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_setIconImbgesDbtb(JNIEnv *env, jobject self,
    jintArrby iconRbster, jint w, jint h,
    jintArrby smbllIconRbster, jint smw, jint smh)
{
    TRY;

    SetIconImbgesDbtbStruct *sims = new SetIconImbgesDbtbStruct;

    sims->window = env->NewGlobblRef(self);
    sims->iconRbster = (jintArrby)env->NewGlobblRef(iconRbster);
    sims->w = w;
    sims->h = h;
    sims->smbllIconRbster = (jintArrby)env->NewGlobblRef(smbllIconRbster);
    sims->smw = smw;
    sims->smh = smh;

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_SetIconImbgesDbtb, sims);
    // globbl refs bnd sims bre deleted in _SetIconImbgesDbtb()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    setMinSize
 * Signbture: (Lsun/bwt/windows/WWindowPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_setMinSize(JNIEnv *env, jobject self,
                                              jint w, jint h)
{
    TRY;

    SizeStruct *ss = new SizeStruct;
    ss->window = env->NewGlobblRef(self);
    ss->w = w;
    ss->h = h;

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_SetMinSize, ss);
    // globbl refs bnd mds bre deleted in _SetMinSize

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    getScreenImOn
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_getScreenImOn(JNIEnv *env, jobject self)
{
    TRY;

    return stbtic_cbst<jint>(reinterpret_cbst<INT_PTR>(AwtToolkit::GetInstbnce().SyncCbll(
        (void *(*)(void *))AwtWindow::_GetScreenImOn,
        env->NewGlobblRef(self))));
    // globbl ref is deleted in _GetScreenImOn()

    CATCH_BAD_ALLOC_RET(-1);
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    setFullScreenExclusiveModeStbte
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_setFullScreenExclusiveModeStbte(JNIEnv *env,
        jobject self, jboolebn stbte)
{
    TRY;

    SetFullScreenExclusiveModeStbteStruct *dbtb =
        new SetFullScreenExclusiveModeStbteStruct;
    dbtb->window = env->NewGlobblRef(self);
    dbtb->isFSEMStbte = stbte;

    AwtToolkit::GetInstbnce().SyncCbll(
            AwtWindow::_SetFullScreenExclusiveModeStbte, dbtb);
    // globbl ref bnd dbtb bre deleted in the invoked method

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    modblDisbble
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_modblDisbble(JNIEnv *env, jobject self,
                                              jobject blocker, jlong blockerHWnd)
{
    TRY;

    ModblDisbbleStruct *mds = new ModblDisbbleStruct;
    mds->window = env->NewGlobblRef(self);
    mds->blockerHWnd = blockerHWnd;

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_ModblDisbble, mds);
    // globbl ref bnd mds bre deleted in _ModblDisbble

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    modblEnbble
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_modblEnbble(JNIEnv *env, jobject self, jobject blocker)
{
    TRY;

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_ModblEnbble,
        env->NewGlobblRef(self));
    // globbl ref is deleted in _ModblEnbble

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    setFocusbbleWindow
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_setFocusbbleWindow(JNIEnv *env, jobject self, jboolebn isFocusbbleWindow)
{
    TRY;

    SetFocusbbleWindowStruct *sfws = new SetFocusbbleWindowStruct;
    sfws->window = env->NewGlobblRef(self);
    sfws->isFocusbbleWindow = isFocusbbleWindow;

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_SetFocusbbleWindow, sfws);
    // globbl ref bnd sfws bre deleted in _SetFocusbbleWindow()

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_nbtiveGrbb(JNIEnv *env, jobject self)
{
    TRY;

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_Grbb, env->NewGlobblRef(self));
    // globbl ref is deleted in _Grbb()

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_nbtiveUngrbb(JNIEnv *env, jobject self)
{
    TRY;

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_Ungrbb, env->NewGlobblRef(self));
    // globbl ref is deleted in _Ungrbb()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    setOpbcity
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_setOpbcity(JNIEnv *env, jobject self,
                                              jint iOpbcity)
{
    TRY;

    OpbcityStruct *os = new OpbcityStruct;
    os->window = env->NewGlobblRef(self);
    os->iOpbcity = iOpbcity;

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_SetOpbcity, os);
    // globbl refs bnd mds bre deleted in _SetMinSize

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    setOpbqueImpl
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_setOpbqueImpl(JNIEnv *env, jobject self,
                                              jboolebn isOpbque)
{
    TRY;

    OpbqueStruct *os = new OpbqueStruct;
    os->window = env->NewGlobblRef(self);
    os->isOpbque = isOpbque;

    AwtToolkit::GetInstbnce().SyncCbll(AwtWindow::_SetOpbque, os);
    // globbl refs bnd mds bre deleted in _SetMinSize

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    updbteWindowImpl
 * Signbture: ([III)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_updbteWindowImpl(JNIEnv *env, jobject self,
                                                  jintArrby dbtb,
                                                  jint width, jint height)
{
    TRY;

    UpdbteWindowStruct *uws = new UpdbteWindowStruct;
    uws->window = env->NewGlobblRef(self);
    uws->dbtb = (jintArrby)env->NewGlobblRef(dbtb);
    uws->hBitmbp = NULL;
    uws->width = width;
    uws->height = height;

    AwtToolkit::GetInstbnce().InvokeFunction(AwtWindow::_UpdbteWindow, uws);
    // globbl refs bnd mds bre deleted in _UpdbteWindow

    CATCH_BAD_ALLOC;
}

/**
 * This method is cblled from the WGL pipeline when it needs to updbte
 * the lbyered window WindowPeer's C++ level object.
 */
void AwtWindow_UpdbteWindow(JNIEnv *env, jobject peer,
                            jint width, jint height, HBITMAP hBitmbp)
{
    TRY;

    UpdbteWindowStruct *uws = new UpdbteWindowStruct;
    uws->window = env->NewGlobblRef(peer);
    uws->dbtb = NULL;
    uws->hBitmbp = hBitmbp;
    uws->width = width;
    uws->height = height;

    AwtToolkit::GetInstbnce().InvokeFunction(AwtWindow::_UpdbteWindow, uws);
    // globbl refs bnd mds bre deleted in _UpdbteWindow

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    requestFocus
 * Signbture: (Z)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_windows_WWindowPeer_requestWindowFocus
    (JNIEnv *env, jobject self, jboolebn isMouseEventCbuse)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    RequestWindowFocusStruct *rfs = new RequestWindowFocusStruct;
    rfs->component = selfGlobblRef;
    rfs->isMouseEventCbuse = isMouseEventCbuse;

    return (jboolebn)AwtToolkit::GetInstbnce().SyncCbll(
        (void*(*)(void*))AwtWindow::_RequestWindowFocus, rfs);
    // globbl refs bnd rfs bre deleted in _RequestWindowFocus

    CATCH_BAD_ALLOC_RET(JNI_FALSE);
}

/*
 * Clbss:     sun_bwt_windows_WWindowPeer
 * Method:    repositionSecurityWbrning
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WWindowPeer_repositionSecurityWbrning(JNIEnv *env,
        jobject self)
{
    TRY;

    RepositionSecurityWbrningStruct *rsws =
        new RepositionSecurityWbrningStruct;
    rsws->window = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().InvokeFunction(
            AwtWindow::_RepositionSecurityWbrning, rsws);
    // globbl refs bnd mds bre deleted in _RepositionSecurityWbrning

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
