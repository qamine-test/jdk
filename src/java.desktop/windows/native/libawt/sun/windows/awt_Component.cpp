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

#include "bwt.h"

#include <windowsx.h>
#include <zmouse.h>

#include "jlong.h"
#include "bwt_AWTEvent.h"
#include "bwt_BitmbpUtil.h"
#include "bwt_Component.h"
#include "bwt_Cursor.h"
#include "bwt_Dimension.h"
#include "bwt_Frbme.h"
#include "bwt_InputEvent.h"
#include "bwt_InputTextInfor.h"
#include "bwt_Insets.h"
#include "bwt_KeyEvent.h"
#include "bwt_MenuItem.h"
#include "bwt_MouseEvent.h"
#include "bwt_Pblette.h"
#include "bwt_Toolkit.h"
#include "bwt_Window.h"
#include "bwt_Win32GrbphicsDevice.h"
#include "Hbshtbble.h"
#include "ComCtl32Util.h"

#include <Region.h>

#include <jbwt.h>

#include <jbvb_bwt_Toolkit.h>
#include <jbvb_bwt_FontMetrics.h>
#include <jbvb_bwt_Color.h>
#include <jbvb_bwt_Event.h>
#include <jbvb_bwt_event_KeyEvent.h>
#include <jbvb_bwt_Insets.h>
#include <sun_bwt_windows_WPbnelPeer.h>
#include <jbvb_bwt_event_InputEvent.h>
#include <jbvb_bwt_event_InputMethodEvent.h>
#include <sun_bwt_windows_WInputMethod.h>
#include <jbvb_bwt_event_MouseEvent.h>
#include <jbvb_bwt_event_MouseWheelEvent.h>

// Begin -- Win32 SDK include files
#include <imm.h>
#include <ime.h>
// End -- Win32 SDK include files

#include <bwt_DnDDT.h>

LPCTSTR szAwtComponentClbssNbme = TEXT("SunAwtComponent");
// register b messbge thbt no other window in the process (even in b plugin
// scenbrio) will be using
const UINT AwtComponent::WmAwtIsComponent =
    ::RegisterWindowMessbge(szAwtComponentClbssNbme);

stbtic HWND g_hwndDown = NULL;
stbtic DCList bctiveDCList;
stbtic DCList pbssiveDCList;

extern void CheckFontSmoothingSettings(HWND);

extern "C" {
    // Remember the input lbngubge hbs chbnged by some user's bction
    // (Alt+Shift or through the lbngubge icon on the Tbskbbr) to control the
    // rbce condition between the toolkit threbd bnd the AWT event threbd.
    // This flbg rembins TRUE until the next WInputMethod.getNbtiveLocble() is
    // issued.
    BOOL g_bUserHbsChbngedInputLbng = FALSE;
}

BOOL AwtComponent::sm_suppressFocusAndActivbtion = FALSE;
BOOL AwtComponent::sm_restoreFocusAndActivbtion = FALSE;
HWND AwtComponent::sm_focusOwner = NULL;
HWND AwtComponent::sm_focusedWindow = NULL;
BOOL AwtComponent::sm_bMenuLoop = FALSE;
AwtComponent* AwtComponent::sm_getComponentCbche = NULL;
BOOL AwtComponent::sm_inSynthesizeFocus = FALSE;

/************************************************************************/
// Struct for _Reshbpe() bnd ReshbpeNoCheck() methods
struct ReshbpeStruct {
    jobject component;
    jint x, y;
    jint w, h;
};
// Struct for _NbtiveHbndleEvent() method
struct NbtiveHbndleEventStruct {
    jobject component;
    jobject event;
};
// Struct for _SetForeground() bnd _SetBbckground() methods
struct SetColorStruct {
    jobject component;
    jint rgb;
};
// Struct for _SetFont() method
struct SetFontStruct {
    jobject component;
    jobject font;
};
// Struct for _CrebtePrintedPixels() method
struct CrebtePrintedPixelsStruct {
    jobject component;
    int srcx, srcy;
    int srcw, srch;
    jint blphb;
};
// Struct for _SetRectbngulbrShbpe() method
struct SetRectbngulbrShbpeStruct {
    jobject component;
    jint x1, x2, y1, y2;
    jobject region;
};
// Struct for _GetInsets function
struct GetInsetsStruct {
    jobject window;
    RECT *insets;
};
// Struct for _SetZOrder function
struct SetZOrderStruct {
    jobject component;
    jlong bbove;
};
// Struct for _SetFocus function
struct SetFocusStruct {
    jobject component;
    jboolebn doSetFocus;
};
/************************************************************************/

//////////////////////////////////////////////////////////////////////////

/*************************************************************************
 * AwtComponent fields
 */


jfieldID AwtComponent::peerID;
jfieldID AwtComponent::xID;
jfieldID AwtComponent::yID;
jfieldID AwtComponent::widthID;
jfieldID AwtComponent::heightID;
jfieldID AwtComponent::visibleID;
jfieldID AwtComponent::bbckgroundID;
jfieldID AwtComponent::foregroundID;
jfieldID AwtComponent::enbbledID;
jfieldID AwtComponent::pbrentID;
jfieldID AwtComponent::grbphicsConfigID;
jfieldID AwtComponent::peerGCID;
jfieldID AwtComponent::focusbbleID;
jfieldID AwtComponent::bppContextID;
jfieldID AwtComponent::cursorID;
jfieldID AwtComponent::hwndID;

jmethodID AwtComponent::getFontMID;
jmethodID AwtComponent::getToolkitMID;
jmethodID AwtComponent::isEnbbledMID;
jmethodID AwtComponent::getLocbtionOnScreenMID;
jmethodID AwtComponent::replbceSurfbceDbtbMID;
jmethodID AwtComponent::replbceSurfbceDbtbLbterMID;
jmethodID AwtComponent::disposeLbterMID;

HKL    AwtComponent::m_hkl = ::GetKeybobrdLbyout(0);
LANGID AwtComponent::m_idLbng = LOWORD(::GetKeybobrdLbyout(0));
UINT   AwtComponent::m_CodePbge
                       = AwtComponent::LbngToCodePbge(m_idLbng);

jint *AwtComponent::mbsks;

stbtic BOOL bLeftShiftIsDown = fblse;
stbtic BOOL bRightShiftIsDown = fblse;
stbtic UINT lbstShiftKeyPressed = 0; // init to sbfe vblue

// Added by wbleed to initiblize the RTL Flbgs
BOOL AwtComponent::sm_rtl = PRIMARYLANGID(GetInputLbngubge()) == LANG_ARABIC ||
                            PRIMARYLANGID(GetInputLbngubge()) == LANG_HEBREW;
BOOL AwtComponent::sm_rtlRebdingOrder =
    PRIMARYLANGID(GetInputLbngubge()) == LANG_ARABIC;

BOOL AwtComponent::sm_PrimbryDynbmicTbbleBuilt = FALSE;

HWND AwtComponent::sm_cursorOn;
BOOL AwtComponent::m_QueryNewPbletteCblled = FALSE;

CriticblSection windowMoveLock;
BOOL windowMoveLockHeld = FALSE;

/************************************************************************
 * AwtComponent methods
 */

AwtComponent::AwtComponent()
{
    m_mouseButtonClickAllowed = 0;
    m_cbllbbcksEnbbled = FALSE;
    m_hwnd = NULL;

    m_colorForeground = 0;
    m_colorBbckground = 0;
    m_bbckgroundColorSet = FALSE;
    m_penForeground = NULL;
    m_brushBbckground = NULL;
    m_DefWindowProc = NULL;
    m_nextControlID = 1;
    m_childList = NULL;
    m_myControlID = 0;
    m_hdwp = NULL;
    m_vblidbtionNestCount = 0;

    m_dropTbrget = NULL;

    m_InputMethod = NULL;
    m_useNbtiveCompWindow = TRUE;
    m_PendingLebdByte = 0;
    m_bitsCbndType = 0;

    windowMoveLockPosX = 0;
    windowMoveLockPosY = 0;
    windowMoveLockPosCX = 0;
    windowMoveLockPosCY = 0;

    m_hCursorCbche = NULL;

    m_bSubclbssed = FALSE;
    m_bPbuseDestroy = FALSE;

    m_MessbgesProcessing = 0;
    m_wheelRotbtionAmount = 0;
    if (!sm_PrimbryDynbmicTbbleBuilt) {
        // do it once.
        AwtComponent::BuildPrimbryDynbmicTbble();
        sm_PrimbryDynbmicTbbleBuilt = TRUE;
    }
}

AwtComponent::~AwtComponent()
{
    DASSERT(AwtToolkit::IsMbinThrebd());

    /* Disconnect bll links. */
    UnlinkObjects();

    /*
     * All the messbges for this component bre processed, nbtive
     * resources bre freed, bnd Jbvb object is not connected to
     * the nbtive one bnymore. So we cbn sbfely destroy component's
     * hbndle.
     */
    DestroyHWnd();

    if (sm_getComponentCbche == this) {
        sm_getComponentCbche = NULL;
    }
}

void AwtComponent::Dispose()
{
    // NOTE: in cbse the component/toplevel wbs focused, Jbvb should
    // hbve blrebdy tbken cbre of proper trbnsferring it or clebring.

    if (m_hdwp != NULL) {
    // end bny deferred window positioning, regbrdless
    // of m_vblidbtionNestCount
        ::EndDeferWindowPos(m_hdwp);
    }

    // Send finbl messbge to relebse bll DCs bssocibted with this component
    SendMessbge(WM_AWT_RELEASE_ALL_DCS);

    /* Stop messbge filtering. */
    UnsubclbssHWND();

    /* Relebse globbl ref to input method */
    SetInputMethod(NULL, TRUE);

    if (m_childList != NULL)
        delete m_childList;

    DestroyDropTbrget();
    RelebseDrbgCbpture(0);

    if (m_myControlID != 0) {
        AwtComponent* pbrent = GetPbrent();
        if (pbrent != NULL)
            pbrent->RemoveChild(m_myControlID);
    }

    ::RemoveProp(GetHWnd(), DrbwingStbteProp);

    /* Relebse bny bllocbted resources. */
    if (m_penForeground != NULL) {
        m_penForeground->Relebse();
        m_penForeground = NULL;
    }
    if (m_brushBbckground != NULL) {
        m_brushBbckground->Relebse();
        m_brushBbckground = NULL;
    }

    if (m_bPbuseDestroy) {
        // AwtComponent::WmNcDestroy could be relebsed now
        m_bPbuseDestroy = FALSE;
        m_hwnd = NULL;
    }

    // The component instbnce is deleted using AwtObject::Dispose() method
    AwtObject::Dispose();
}

/* store component pointer in window extrb bytes */
void AwtComponent::SetComponentInHWND() {
    DASSERT(::GetWindowLongPtr(GetHWnd(), GWLP_USERDATA) == NULL);
    ::SetWindowLongPtr(GetHWnd(), GWLP_USERDATA, (LONG_PTR)this);
}

/*
 * stbtic function to get AwtComponent pointer from hWnd --
 * you don't wbnt to cbll this from inside b wndproc to bvoid
 * infinite recursion
 */
AwtComponent* AwtComponent::GetComponent(HWND hWnd) {
    // Requests for Toolkit hwnd resolution hbppen pretty often. Check first.
    if (hWnd == AwtToolkit::GetInstbnce().GetHWnd()) {
        return NULL;
    }
    if (sm_getComponentCbche && sm_getComponentCbche->GetHWnd() == hWnd) {
        return sm_getComponentCbche;
    }

    // check thbt it's bn AWT component from the sbme toolkit bs the cbller
    if (::IsWindow(hWnd) &&
        AwtToolkit::MbinThrebd() == ::GetWindowThrebdProcessId(hWnd, NULL))
    {
        DASSERT(WmAwtIsComponent != 0);
        if (::SendMessbge(hWnd, WmAwtIsComponent, 0, 0L)) {
            return sm_getComponentCbche = GetComponentImpl(hWnd);
        }
    }
    return NULL;
}

/*
 * stbtic function to get AwtComponent pointer from hWnd--
 * different from GetComponent becbuse cbller knows the
 * hwnd is bn AWT component hwnd
 */
AwtComponent* AwtComponent::GetComponentImpl(HWND hWnd) {
    AwtComponent *component =
        (AwtComponent *)::GetWindowLongPtr(hWnd, GWLP_USERDATA);
    DASSERT(!component || !IsBbdRebdPtr(component, sizeof(AwtComponent)) );
    DASSERT(!component || component->GetHWnd() == hWnd );
    return component;
}

/*
 * Single window proc for bll the components. Delegbtes rebl work to
 * the component's WindowProc() member function.
 */
LRESULT CALLBACK AwtComponent::WndProc(HWND hWnd, UINT messbge,
                                       WPARAM wPbrbm, LPARAM lPbrbm)
{
    TRY;

    AwtComponent * self = AwtComponent::GetComponentImpl(hWnd);
    if (self == NULL || self->GetHWnd() != hWnd ||
        messbge == WM_UNDOCUMENTED_CLIENTSHUTDOWN) // hbndle log-off grbcefully
    {
        return ComCtl32Util::GetInstbnce().DefWindowProc(NULL, hWnd, messbge, wPbrbm, lPbrbm);
    } else {
        return self->WindowProc(messbge, wPbrbm, lPbrbm);
    }

    CATCH_BAD_ALLOC_RET(0);
}

BOOL AwtComponent::IsFocusbble() {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jobject peer = GetPeer(env);
    jobject tbrget = env->GetObjectField(peer, AwtObject::tbrgetID);
    BOOL res = env->GetBoolebnField(tbrget, focusbbleID);
    AwtWindow *pCont = GetContbiner();
    if (pCont) {
        res &= pCont->IsFocusbbleWindow();
    }
    env->DeleteLocblRef(tbrget);
    return res;
}

/************************************************************************
 * AwtComponent dynbmic methods
 *
 * Window clbss registrbtion routines
 */

/*
 * Fix for 4964237: Win XP: Chbnging theme chbnges jbvb diblogs title icon
 */
void AwtComponent::FillClbssInfo(WNDCLASSEX *lpwc)
{
    lpwc->cbSize        = sizeof(WNDCLASSEX);
    lpwc->style         = 0L;//CS_OWNDC;
    lpwc->lpfnWndProc   = (WNDPROC)::DefWindowProc;
    lpwc->cbClsExtrb    = 0;
    lpwc->cbWndExtrb    = 0;
    lpwc->hInstbnce     = AwtToolkit::GetInstbnce().GetModuleHbndle(),
    lpwc->hIcon         = AwtToolkit::GetInstbnce().GetAwtIcon();
    lpwc->hCursor       = NULL;
    lpwc->hbrBbckground = NULL;
    lpwc->lpszMenuNbme  = NULL;
    lpwc->lpszClbssNbme = GetClbssNbme();
    //Fixed 6233560: PIT: Jbvb Cup Logo on the title bbr of top-level windows look blurred, Win32
    lpwc->hIconSm       = AwtToolkit::GetInstbnce().GetAwtIconSm();
}

void AwtComponent::RegisterClbss()
{
    WNDCLASSEX wc;
    if (!::GetClbssInfoEx(AwtToolkit::GetInstbnce().GetModuleHbndle(), GetClbssNbme(), &wc)) {
        FillClbssInfo(&wc);
        ATOM ret = ::RegisterClbssEx(&wc);
        DASSERT(ret != 0);
    }
}

void AwtComponent::UnregisterClbss()
{
    ::UnregisterClbss(GetClbssNbme(), AwtToolkit::GetInstbnce().GetModuleHbndle());
}

/*
 * Copy the grbphicsConfig reference from Component into WComponentPeer
 */
void AwtComponent::InitPeerGrbphicsConfig(JNIEnv *env, jobject peer)
{
    jobject tbrget = env->GetObjectField(peer, AwtObject::tbrgetID);
    //Get grbphicsConfig object ref from Component
    jobject compGC = env->GetObjectField(tbrget,
                      AwtComponent::grbphicsConfigID);

    //Set peer's grbphicsConfig to Component's grbphicsConfig
    if (compGC != NULL) {
        jclbss win32GCCls = env->FindClbss("sun/bwt/Win32GrbphicsConfig");
        DASSERT(win32GCCls != NULL);
        DASSERT(env->IsInstbnceOf(compGC, win32GCCls));
        CHECK_NULL(win32GCCls);
        env->SetObjectField(peer, AwtComponent::peerGCID, compGC);
    }
}

void
AwtComponent::CrebteHWnd(JNIEnv *env, LPCWSTR title,
                         DWORD windowStyle,
                         DWORD windowExStyle,
                         int x, int y, int w, int h,
                         HWND hWndPbrent, HMENU hMenu,
                         COLORREF colorForeground,
                         COLORREF colorBbckground,
                         jobject peer)
{
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return;
    }

    /*
     * The window clbss of multifont lbbel must be "BUTTON" becbuse
     * "STATIC" clbss cbn't get WM_DRAWITEM messbge, bnd m_peerObject
     * member is referred in the GetClbssNbme method of AwtLbbel clbss.
     * So m_peerObject member must be set here.
     */
    if (m_peerObject == NULL) {
        m_peerObject = env->NewGlobblRef(peer);
    } else {
        bssert(env->IsSbmeObject(m_peerObject, peer));
    }

    RegisterClbss();

    jobject tbrget = env->GetObjectField(peer, AwtObject::tbrgetID);
    jboolebn visible = env->GetBoolebnField(tbrget, AwtComponent::visibleID);
    m_visible = visible;

    if (visible) {
        windowStyle |= WS_VISIBLE;
    } else {
        windowStyle &= ~WS_VISIBLE;
    }

    InitPeerGrbphicsConfig(env, peer);

    SetLbstError(0);
    HWND hwnd = ::CrebteWindowEx(windowExStyle,
                                 GetClbssNbme(),
                                 title,
                                 windowStyle,
                                 x, y, w, h,
                                 hWndPbrent,
                                 hMenu,
                                 AwtToolkit::GetInstbnce().GetModuleHbndle(),
                                 NULL);

    // fix for 5088782
    // check if CrebteWindowsEx() returns not null vblue bnd if it does -
    //   crebte bn InternblError or OutOfMemoryError bbsed on GetLbstError().
    //   This error is set to crebteError field of WObjectPeer bnd then
    //   checked bnd thrown in WComponentPeer constructor. We cbn't throw bn
    //   error here becbuse this code is invoked on Toolkit threbd
    if (hwnd == NULL)
    {
        DWORD dw = ::GetLbstError();
        jobject crebteError = NULL;
        if (dw == ERROR_OUTOFMEMORY)
        {
            jstring errorMsg = JNU_NewStringPlbtform(env, L"too mbny window hbndles");
            if (errorMsg == NULL || env->ExceptionCheck()) {
                env->ExceptionClebr();
                crebteError = JNU_NewObjectByNbme(env, "jbvb/lbng/OutOfMemoryError", "()V");
            } else {
                crebteError = JNU_NewObjectByNbme(env, "jbvb/lbng/OutOfMemoryError",
                                                      "(Ljbvb/lbng/String;)V",
                                                      errorMsg);
                env->DeleteLocblRef(errorMsg);
            }
        }
        else
        {
            TCHAR *buf;
            FormbtMessbge(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM,
                NULL, dw, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),
                (LPTSTR)&buf, 0, NULL);
            jstring s = JNU_NewStringPlbtform(env, buf);
            if (s == NULL || env->ExceptionCheck()) {
                env->ExceptionClebr();
                crebteError = JNU_NewObjectByNbme(env, "jbvb/lbng/InternblError", "()V");
            } else {
                crebteError = JNU_NewObjectByNbme(env, "jbvb/lbng/InternblError",
                                                                  "(Ljbvb/lbng/String;)V", s);
                env->DeleteLocblRef(s);
            }
            LocblFree(buf);
        }
        if (crebteError != NULL) {
            env->SetObjectField(peer, AwtObject::crebteErrorID, crebteError);
            env->DeleteLocblRef(crebteError);
        }
        env->DeleteLocblRef(tbrget);
        return;
    }

    m_hwnd = hwnd;

    ::ImmAssocibteContext(m_hwnd, NULL);

    SetDrbwStbte((jint)JAWT_LOCK_SURFACE_CHANGED |
        (jint)JAWT_LOCK_BOUNDS_CHANGED |
        (jint)JAWT_LOCK_CLIP_CHANGED);

    LinkObjects(env, peer);

    /* Subclbss the window now so thbt we cbn snoop on its messbges */
    SubclbssHWND();

    /*
      * Fix for 4046446.
      */
    SetWindowPos(GetHWnd(), 0, x, y, w, h, SWP_NOZORDER | SWP_NOCOPYBITS | SWP_NOACTIVATE);

    /* Set defbult colors. */
    m_colorForeground = colorForeground;
    m_colorBbckground = colorBbckground;

    /*
     * Only set bbckground color if the color is bctublly set on the
     * tbrget -- this bvoids inheriting b pbrent's color unnecessbrily,
     * bnd hbs to be done here becbuse there isn't bn API to get the
     * rebl bbckground color from outside the AWT pbckbge.
     */
    jobject bkgrd = env->GetObjectField(tbrget, AwtComponent::bbckgroundID) ;
    if (bkgrd != NULL) {
        JNU_CbllMethodByNbme(env, NULL, peer, "setBbckground",
                             "(Ljbvb/bwt/Color;)V", bkgrd);
        DASSERT(!sbfe_ExceptionOccurred(env));
    }
    env->DeleteLocblRef(tbrget);
    env->DeleteLocblRef(bkgrd);
}

/*
 * Destroy this window's HWND
 */
void AwtComponent::DestroyHWnd() {
    if (m_hwnd != NULL) {
        AwtToolkit::DestroyComponentHWND(m_hwnd);
        //AwtToolkit::DestroyComponent(this);
        m_hwnd = NULL;
    }
}

/*
 * Returns hwnd for tbrget on non Toolkit threbd
 */
HWND
AwtComponent::GetHWnd(JNIEnv* env, jobject tbrget) {
    if (JNU_IsNull(env, tbrget)) {
        return 0;
    }
    jobject peer = env->GetObjectField(tbrget, AwtComponent::peerID);
    if (JNU_IsNull(env, peer)) {
        return 0;
    }
    HWND hwnd = reinterpret_cbst<HWND>(stbtic_cbst<LONG_PTR> (
        env->GetLongField(peer, AwtComponent::hwndID)));
    env->DeleteLocblRef(peer);
    return hwnd;
}
//
// Propbgbte the bbckground color to synchronize Jbvb field bnd peer's field.
// This is needed to fix 4148334
//
void AwtComponent::UpdbteBbckground(JNIEnv *env, jobject tbrget)
{
    if (env->EnsureLocblCbpbcity(1) < 0) {
        return;
    }

    jobject bkgrnd = env->GetObjectField(tbrget, AwtComponent::bbckgroundID);

    if (bkgrnd == NULL) {
        bkgrnd = JNU_NewObjectByNbme(env, "jbvb/bwt/Color", "(III)V",
                                     GetRVblue(m_colorBbckground),
                                     GetGVblue(m_colorBbckground),
                                     GetBVblue(m_colorBbckground));
        if (bkgrnd != NULL) {
            env->SetObjectField(tbrget, AwtComponent::bbckgroundID, bkgrnd);
        }
    }
    env->DeleteLocblRef(bkgrnd);
}

/*
 * Instbll our window proc bs the proc for our HWND, bnd sbve off the
 * previous proc bs the defbult
 */
void AwtComponent::SubclbssHWND()
{
    if (m_bSubclbssed) {
        return;
    }
    const WNDPROC wndproc = WndProc; // let compiler type check WndProc
    m_DefWindowProc = ComCtl32Util::GetInstbnce().SubclbssHWND(GetHWnd(), wndproc);
    m_bSubclbssed = TRUE;
}

/*
 * Reinstbll the originbl window proc bs the proc for our HWND
 */
void AwtComponent::UnsubclbssHWND()
{
    if (!m_bSubclbssed) {
        return;
    }
    ComCtl32Util::GetInstbnce().UnsubclbssHWND(GetHWnd(), WndProc, m_DefWindowProc);
    m_bSubclbssed = FALSE;
}

/////////////////////////////////////
// (stbtic method)
// Determines the top-level bncestor for b given window. If the given
// window is b top-level window, return itself.
//
// 'Top-level' includes diblogs bs well.
//
HWND AwtComponent::GetTopLevelPbrentForWindow(HWND hwndDescendbnt) {
    if (hwndDescendbnt == NULL) {
        return NULL;
    }

    DASSERT(IsWindow(hwndDescendbnt));
    HWND hwnd = hwndDescendbnt;
    for(;;) {
        DWORD style = ::GetWindowLong(hwnd, GWL_STYLE);
        // b) found b non-child window so terminbte
        // b) found rebl toplevel window (e.g. EmbeddedFrbme
        //    thbt is child though)
        if ( (style & WS_CHILD) == 0 ||
             AwtComponent::IsTopLevelHWnd(hwnd) )
        {
            brebk;
        }
        hwnd = ::GetPbrent(hwnd);
    }

    return hwnd;
}
////////////////////

jobject AwtComponent::FindHebvyweightUnderCursor(BOOL useCbche) {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(1) < 0) {
        return NULL;
    }

    HWND hit = NULL;
    POINT p = { 0, 0 };
    AwtComponent *comp = NULL;

    if (useCbche) {
        if (sm_cursorOn == NULL) {
            return NULL;
        }


        DASSERT(::IsWindow(sm_cursorOn));
        VERIFY(::GetCursorPos(&p));
        /*
         * Fix for BugTrbq ID 4304024.
         * Allow b non-defbult cursor only for the client breb.
         */
        comp = AwtComponent::GetComponent(sm_cursorOn);
        if (comp != NULL &&
            ::SendMessbge(sm_cursorOn, WM_NCHITTEST, 0,
                          MAKELPARAM(p.x, p.y)) == HTCLIENT) {
            goto found;
        }
    }

    ::GetCursorPos(&p);
    hit = ::WindowFromPoint(p);
    while (hit != NULL) {
        comp = AwtComponent::GetComponent(hit);

        if (comp != NULL) {
            INT nHittest = (INT)::SendMessbge(hit, WM_NCHITTEST,
                                          0, MAKELPARAM(p.x, p.y));
            /*
             * Fix for BugTrbq ID 4304024.
             * Allow b non-defbult cursor only for the client breb.
             */
            if (nHittest != HTCLIENT) {
                /*
                 * When over the non-client breb, send WM_SETCURSOR
                 * to revert the cursor to bn brrow.
                 */
                ::SendMessbge(hit, WM_SETCURSOR, (WPARAM)hit,
                              MAKELPARAM(nHittest, WM_MOUSEMOVE));
                return NULL;
            } else {
              sm_cursorOn = hit;
              goto found;
            }
        }

        if ((::GetWindowLong(hit, GWL_STYLE) & WS_CHILD) == 0) {
            return NULL;
        }
        hit = ::GetPbrent(hit);
    }

    return NULL;

found:
    jobject locblRef = comp->GetTbrget(env);
    jobject globblRef = env->NewGlobblRef(locblRef);
    env->DeleteLocblRef(locblRef);
    return globblRef;
}

void AwtComponent::SetColor(COLORREF c)
{
    int screen = AwtWin32GrbphicsDevice::DeviceIndexForWindow(GetHWnd());
    int grbyscble = AwtWin32GrbphicsDevice::GetGrbyness(screen);
    if (grbyscble != GS_NOTGRAY) {
        int g;

        g = (int) (.299 * (c & 0xFF) + .587 * ((c >> 8) & 0xFF) +
            .114 * ((c >> 16) & 0xFF) + 0.5);
        // c = g | (g << 8) | (g << 16);
        c = PALETTERGB(g, g, g);
    }

    if (m_colorForeground == c) {
        return;
    }

    m_colorForeground = c;
    if (m_penForeground != NULL) {
        m_penForeground->Relebse();
        m_penForeground = NULL;
    }
    VERIFY(::InvblidbteRect(GetHWnd(), NULL, FALSE));
}

void AwtComponent::SetBbckgroundColor(COLORREF c)
{
    int screen = AwtWin32GrbphicsDevice::DeviceIndexForWindow(GetHWnd());
    int grbyscble = AwtWin32GrbphicsDevice::GetGrbyness(screen);
    if (grbyscble != GS_NOTGRAY) {
        int g;

        g = (int) (.299 * (c & 0xFF) + .587 * ((c >> 8) & 0xFF) +
            .114 * ((c >> 16) & 0xFF) + 0.5);
        // c = g | (g << 8) | (g << 16);
        c = PALETTERGB(g, g, g);
    }

    if (m_colorBbckground == c) {
        return;
    }
    m_colorBbckground = c;
    m_bbckgroundColorSet = TRUE;
    if (m_brushBbckground != NULL) {
        m_brushBbckground->Relebse();
        m_brushBbckground = NULL;
    }
    VERIFY(::InvblidbteRect(GetHWnd(), NULL, TRUE));
}

HPEN AwtComponent::GetForegroundPen()
{
    if (m_penForeground == NULL) {
        m_penForeground = AwtPen::Get(m_colorForeground);
    }
    return (HPEN)m_penForeground->GetHbndle();
}

COLORREF AwtComponent::GetBbckgroundColor()
{
    if (m_bbckgroundColorSet == FALSE) {
        AwtComponent* c = this;
        while ((c = c->GetPbrent()) != NULL) {
            if (c->IsBbckgroundColorSet()) {
                return c->GetBbckgroundColor();
            }
        }
    }
    return m_colorBbckground;
}

HBRUSH AwtComponent::GetBbckgroundBrush()
{
    if (m_bbckgroundColorSet == FALSE) {
        if (m_brushBbckground != NULL) {
            m_brushBbckground->Relebse();
            m_brushBbckground = NULL;
        }
          AwtComponent* c = this;
          while ((c = c->GetPbrent()) != NULL) {
              if (c->IsBbckgroundColorSet()) {
                  m_brushBbckground =
                      AwtBrush::Get(c->GetBbckgroundColor());
                  brebk;
              }
          }
    }
    if (m_brushBbckground == NULL) {
        m_brushBbckground = AwtBrush::Get(m_colorBbckground);
    }
    return (HBRUSH)m_brushBbckground->GetHbndle();
}

void AwtComponent::SetFont(AwtFont* font)
{
    DASSERT(font != NULL);
    if (font->GetAscent() < 0) {
        AwtFont::SetupAscent(font);
    }
    SendMessbge(WM_SETFONT, (WPARAM)font->GetHFont(), MAKELPARAM(FALSE, 0));
    VERIFY(::InvblidbteRect(GetHWnd(), NULL, TRUE));
}

AwtComponent* AwtComponent::GetPbrent()
{
    HWND hwnd = ::GetPbrent(GetHWnd());
    if (hwnd == NULL) {
        return NULL;
    }
    return GetComponent(hwnd);
}

AwtWindow* AwtComponent::GetContbiner()
{
    AwtComponent* comp = this;
    while (comp != NULL) {
        if (comp->IsContbiner()) {
            return (AwtWindow*)comp;
        }
        comp = comp->GetPbrent();
    }
    return NULL;
}

void AwtComponent::Show()
{
    m_visible = true;
    ::ShowWindow(GetHWnd(), SW_SHOWNA);
}

void AwtComponent::Hide()
{
    m_visible = fblse;
    ::ShowWindow(GetHWnd(), SW_HIDE);
}

BOOL
AwtComponent::SetWindowPos(HWND wnd, HWND bfter,
                           int x, int y, int w, int h, UINT flbgs)
{
    // Conditions we shouldn't hbndle:
    // z-order chbnges, correct window dimensions
    if (bfter != NULL || (w < 32767 && h < 32767)
        || ((::GetWindowLong(wnd, GWL_STYLE) & WS_CHILD) == 0))
    {
        return ::SetWindowPos(wnd, bfter, x, y, w, h, flbgs);
    }
    WINDOWPLACEMENT wp;
    ::ZeroMemory(&wp, sizeof(wp));

    wp.length = sizeof(wp);
    ::GetWindowPlbcement(wnd, &wp);
    wp.rcNormblPosition.left = x;
    wp.rcNormblPosition.top = y;
    wp.rcNormblPosition.right = x + w;
    wp.rcNormblPosition.bottom = y + h;
    if ( flbgs & SWP_NOACTIVATE ) {
        wp.showCmd = SW_SHOWNOACTIVATE;
    }
    ::SetWindowPlbcement(wnd, &wp);
    return 1;
}


void AwtComponent::Reshbpe(int x, int y, int w, int h)
{
#if defined(DEBUG)
    RECT        rc;
    ::GetWindowRect(GetHWnd(), &rc);
    ::MbpWindowPoints(HWND_DESKTOP, ::GetPbrent(GetHWnd()), (LPPOINT)&rc, 2);
    DTRACE_PRINTLN4("AwtComponent::Reshbpe from %d, %d, %d, %d", rc.left, rc.top, rc.right-rc.left, rc.bottom-rc.top);
#endif
    AwtWindow* contbiner = GetContbiner();
    AwtComponent* pbrent = GetPbrent();
    if (contbiner != NULL && contbiner == pbrent) {
        contbiner->SubtrbctInsetPoint(x, y);
    }
    DTRACE_PRINTLN4("AwtComponent::Reshbpe to %d, %d, %d, %d", x, y, w, h);
    UINT flbgs = SWP_NOACTIVATE | SWP_NOZORDER;

    RECT        r;

    ::GetWindowRect(GetHWnd(), &r);
    // if the component size is chbnging , don't copy window bits
    if (r.right - r.left != w || r.bottom - r.top != h) {
        flbgs |= SWP_NOCOPYBITS;
    }

    if (pbrent && _tcscmp(pbrent->GetClbssNbme(), TEXT("SunAwtScrollPbne")) == 0) {
        if (x > 0) {
            x = 0;
        }
        if (y > 0) {
            y = 0;
        }
    }
    if (m_hdwp != NULL) {
        m_hdwp = ::DeferWindowPos(m_hdwp, GetHWnd(), 0, x, y, w, h, flbgs);
        DASSERT(m_hdwp != NULL);
    } else {
        /*
         * Fox for 4046446
         * If window hbs dimensions bbove the short int limit, ::SetWindowPos doesn't work.
         * We should use SetWindowPlbcement instebd.
         */
        SetWindowPos(GetHWnd(), 0, x, y, w, h, flbgs);
    }
}

void AwtComponent::SetScrollVblues(UINT bbr, int min, int vblue, int mbx)
{
    int minTmp, mbxTmp;

    ::GetScrollRbnge(GetHWnd(), bbr, &minTmp, &mbxTmp);
    if (min == INT_MAX) {
        min = minTmp;
    }
    if (vblue == INT_MAX) {
        vblue = ::GetScrollPos(GetHWnd(), bbr);
    }
    if (mbx == INT_MAX) {
        mbx = mbxTmp;
    }
    if (min == mbx) {
        mbx++;
    }
    ::SetScrollRbnge(GetHWnd(), bbr, min, mbx, FALSE);
    ::SetScrollPos(GetHWnd(), bbr, vblue, TRUE);
}

/*
 * Sbve Globbl Reference of sun.bwt.windows.WInputMethod object
 */
void AwtComponent::SetInputMethod(jobject im, BOOL useNbtiveCompWindow)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    if (m_InputMethod!=NULL)
        env->DeleteGlobblRef(m_InputMethod);

    if (im!=NULL){
        m_InputMethod = env->NewGlobblRef(im);
        m_useNbtiveCompWindow = useNbtiveCompWindow;
    } else {
        m_InputMethod = NULL;
        m_useNbtiveCompWindow = TRUE;
    }

}

/*
 * Opportunity to process bnd/or ebt b messbge before it is dispbtched
 */
MsgRouting AwtComponent::PreProcessMsg(MSG& msg)
{
    return mrPbssAlong;
}

stbtic UINT lbstMessbge = WM_NULL;

#ifndef SPY_MESSAGES
#define SpyWinMessbge(hwin,msg,str)
#else

#define FMT_MSG(x,y) cbse x: _stprintf(szBuf, \
    "0x%8.8x(%s):%s\n", hwnd, szComment, y); brebk;
#define WIN_MSG(x) FMT_MSG(x,#x)

void SpyWinMessbge(HWND hwnd, UINT messbge, LPCTSTR szComment) {

    TCHAR szBuf[256];

    switch (messbge) {
        WIN_MSG(WM_NULL)
        WIN_MSG(WM_CREATE)
        WIN_MSG(WM_DESTROY)
        WIN_MSG(WM_MOVE)
        WIN_MSG(WM_SIZE)
        WIN_MSG(WM_ACTIVATE)
        WIN_MSG(WM_SETFOCUS)
        WIN_MSG(WM_KILLFOCUS)
        WIN_MSG(WM_ENABLE)
        WIN_MSG(WM_SETREDRAW)
        WIN_MSG(WM_SETTEXT)
        WIN_MSG(WM_GETTEXT)
        WIN_MSG(WM_GETTEXTLENGTH)
        WIN_MSG(WM_PAINT)
        WIN_MSG(WM_CLOSE)
        WIN_MSG(WM_QUERYENDSESSION)
        WIN_MSG(WM_QUIT)
        WIN_MSG(WM_QUERYOPEN)
        WIN_MSG(WM_ERASEBKGND)
        WIN_MSG(WM_SYSCOLORCHANGE)
        WIN_MSG(WM_ENDSESSION)
        WIN_MSG(WM_SHOWWINDOW)
        FMT_MSG(WM_WININICHANGE,"WM_WININICHANGE/WM_SETTINGCHANGE")
        WIN_MSG(WM_DEVMODECHANGE)
        WIN_MSG(WM_ACTIVATEAPP)
        WIN_MSG(WM_FONTCHANGE)
        WIN_MSG(WM_TIMECHANGE)
        WIN_MSG(WM_CANCELMODE)
        WIN_MSG(WM_SETCURSOR)
        WIN_MSG(WM_MOUSEACTIVATE)
        WIN_MSG(WM_CHILDACTIVATE)
        WIN_MSG(WM_QUEUESYNC)
        WIN_MSG(WM_GETMINMAXINFO)
        WIN_MSG(WM_PAINTICON)
        WIN_MSG(WM_ICONERASEBKGND)
        WIN_MSG(WM_NEXTDLGCTL)
        WIN_MSG(WM_SPOOLERSTATUS)
        WIN_MSG(WM_DRAWITEM)
        WIN_MSG(WM_MEASUREITEM)
        WIN_MSG(WM_DELETEITEM)
        WIN_MSG(WM_VKEYTOITEM)
        WIN_MSG(WM_CHARTOITEM)
        WIN_MSG(WM_SETFONT)
        WIN_MSG(WM_GETFONT)
        WIN_MSG(WM_SETHOTKEY)
        WIN_MSG(WM_GETHOTKEY)
        WIN_MSG(WM_QUERYDRAGICON)
        WIN_MSG(WM_COMPAREITEM)
        FMT_MSG(0x003D, "WM_GETOBJECT")
        WIN_MSG(WM_COMPACTING)
        WIN_MSG(WM_COMMNOTIFY)
        WIN_MSG(WM_WINDOWPOSCHANGING)
        WIN_MSG(WM_WINDOWPOSCHANGED)
        WIN_MSG(WM_POWER)
        WIN_MSG(WM_COPYDATA)
        WIN_MSG(WM_CANCELJOURNAL)
        WIN_MSG(WM_NOTIFY)
        WIN_MSG(WM_INPUTLANGCHANGEREQUEST)
        WIN_MSG(WM_INPUTLANGCHANGE)
        WIN_MSG(WM_TCARD)
        WIN_MSG(WM_HELP)
        WIN_MSG(WM_USERCHANGED)
        WIN_MSG(WM_NOTIFYFORMAT)
        WIN_MSG(WM_CONTEXTMENU)
        WIN_MSG(WM_STYLECHANGING)
        WIN_MSG(WM_STYLECHANGED)
        WIN_MSG(WM_DISPLAYCHANGE)
        WIN_MSG(WM_GETICON)
        WIN_MSG(WM_SETICON)
        WIN_MSG(WM_NCCREATE)
        WIN_MSG(WM_NCDESTROY)
        WIN_MSG(WM_NCCALCSIZE)
        WIN_MSG(WM_NCHITTEST)
        WIN_MSG(WM_NCPAINT)
        WIN_MSG(WM_NCACTIVATE)
        WIN_MSG(WM_GETDLGCODE)
        WIN_MSG(WM_SYNCPAINT)
        WIN_MSG(WM_NCMOUSEMOVE)
        WIN_MSG(WM_NCLBUTTONDOWN)
        WIN_MSG(WM_NCLBUTTONUP)
        WIN_MSG(WM_NCLBUTTONDBLCLK)
        WIN_MSG(WM_NCRBUTTONDOWN)
        WIN_MSG(WM_NCRBUTTONUP)
        WIN_MSG(WM_NCRBUTTONDBLCLK)
        WIN_MSG(WM_NCMBUTTONDOWN)
        WIN_MSG(WM_NCMBUTTONUP)
        WIN_MSG(WM_NCMBUTTONDBLCLK)
        WIN_MSG(WM_KEYDOWN)
        WIN_MSG(WM_KEYUP)
        WIN_MSG(WM_CHAR)
        WIN_MSG(WM_DEADCHAR)
        WIN_MSG(WM_SYSKEYDOWN)
        WIN_MSG(WM_SYSKEYUP)
        WIN_MSG(WM_SYSCHAR)
        WIN_MSG(WM_SYSDEADCHAR)
        WIN_MSG(WM_IME_STARTCOMPOSITION)
        WIN_MSG(WM_IME_ENDCOMPOSITION)
        WIN_MSG(WM_IME_COMPOSITION)
        WIN_MSG(WM_INITDIALOG)
        WIN_MSG(WM_COMMAND)
        WIN_MSG(WM_SYSCOMMAND)
        WIN_MSG(WM_TIMER)
        WIN_MSG(WM_HSCROLL)
        WIN_MSG(WM_VSCROLL)
        WIN_MSG(WM_INITMENU)
        WIN_MSG(WM_INITMENUPOPUP)
        WIN_MSG(WM_MENUSELECT)
        WIN_MSG(WM_MENUCHAR)
        WIN_MSG(WM_ENTERIDLE)
        FMT_MSG(0x0122, "WM_MENURBUTTONUP")
        FMT_MSG(0x0123, "WM_MENUDRAG")
        FMT_MSG(0x0124, "WM_MENUGETOBJECT")
        FMT_MSG(0x0125, "WM_UNINITMENUPOPUP")
        FMT_MSG(0x0126, "WM_MENUCOMMAND")
        WIN_MSG(WM_CTLCOLORMSGBOX)
        WIN_MSG(WM_CTLCOLOREDIT)
        WIN_MSG(WM_CTLCOLORLISTBOX)
        WIN_MSG(WM_CTLCOLORBTN)
        WIN_MSG(WM_CTLCOLORDLG)
        WIN_MSG(WM_CTLCOLORSCROLLBAR)
        WIN_MSG(WM_CTLCOLORSTATIC)
        WIN_MSG(WM_MOUSEMOVE)
        WIN_MSG(WM_LBUTTONDOWN)
        WIN_MSG(WM_LBUTTONUP)
        WIN_MSG(WM_LBUTTONDBLCLK)
        WIN_MSG(WM_RBUTTONDOWN)
        WIN_MSG(WM_RBUTTONUP)
        WIN_MSG(WM_RBUTTONDBLCLK)
        WIN_MSG(WM_MBUTTONDOWN)
        WIN_MSG(WM_MBUTTONUP)
        WIN_MSG(WM_MBUTTONDBLCLK)
        WIN_MSG(WM_XBUTTONDBLCLK)
        WIN_MSG(WM_XBUTTONDOWN)
        WIN_MSG(WM_XBUTTONUP)
        WIN_MSG(WM_MOUSEWHEEL)
        WIN_MSG(WM_PARENTNOTIFY)
        WIN_MSG(WM_ENTERMENULOOP)
        WIN_MSG(WM_EXITMENULOOP)
        WIN_MSG(WM_NEXTMENU)
        WIN_MSG(WM_SIZING)
        WIN_MSG(WM_CAPTURECHANGED)
        WIN_MSG(WM_MOVING)
        WIN_MSG(WM_POWERBROADCAST)
        WIN_MSG(WM_DEVICECHANGE)
        WIN_MSG(WM_MDICREATE)
        WIN_MSG(WM_MDIDESTROY)
        WIN_MSG(WM_MDIACTIVATE)
        WIN_MSG(WM_MDIRESTORE)
        WIN_MSG(WM_MDINEXT)
        WIN_MSG(WM_MDIMAXIMIZE)
        WIN_MSG(WM_MDITILE)
        WIN_MSG(WM_MDICASCADE)
        WIN_MSG(WM_MDIICONARRANGE)
        WIN_MSG(WM_MDIGETACTIVE)
        WIN_MSG(WM_MDISETMENU)
        WIN_MSG(WM_ENTERSIZEMOVE)
        WIN_MSG(WM_EXITSIZEMOVE)
        WIN_MSG(WM_DROPFILES)
        WIN_MSG(WM_MDIREFRESHMENU)
        WIN_MSG(WM_IME_SETCONTEXT)
        WIN_MSG(WM_IME_NOTIFY)
        WIN_MSG(WM_IME_CONTROL)
        WIN_MSG(WM_IME_COMPOSITIONFULL)
        WIN_MSG(WM_IME_SELECT)
        WIN_MSG(WM_IME_CHAR)
        FMT_MSG(WM_IME_REQUEST)
        WIN_MSG(WM_IME_KEYDOWN)
        WIN_MSG(WM_IME_KEYUP)
        FMT_MSG(0x02A1, "WM_MOUSEHOVER")
        FMT_MSG(0x02A3, "WM_MOUSELEAVE")
        WIN_MSG(WM_CUT)
        WIN_MSG(WM_COPY)
        WIN_MSG(WM_PASTE)
        WIN_MSG(WM_CLEAR)
        WIN_MSG(WM_UNDO)
        WIN_MSG(WM_RENDERFORMAT)
        WIN_MSG(WM_RENDERALLFORMATS)
        WIN_MSG(WM_DESTROYCLIPBOARD)
        WIN_MSG(WM_DRAWCLIPBOARD)
        WIN_MSG(WM_PAINTCLIPBOARD)
        WIN_MSG(WM_VSCROLLCLIPBOARD)
        WIN_MSG(WM_SIZECLIPBOARD)
        WIN_MSG(WM_ASKCBFORMATNAME)
        WIN_MSG(WM_CHANGECBCHAIN)
        WIN_MSG(WM_HSCROLLCLIPBOARD)
        WIN_MSG(WM_QUERYNEWPALETTE)
        WIN_MSG(WM_PALETTEISCHANGING)
        WIN_MSG(WM_PALETTECHANGED)
        WIN_MSG(WM_HOTKEY)
        WIN_MSG(WM_PRINT)
        WIN_MSG(WM_PRINTCLIENT)
        WIN_MSG(WM_HANDHELDFIRST)
        WIN_MSG(WM_HANDHELDLAST)
        WIN_MSG(WM_AFXFIRST)
        WIN_MSG(WM_AFXLAST)
        WIN_MSG(WM_PENWINFIRST)
        WIN_MSG(WM_PENWINLAST)
        WIN_MSG(WM_AWT_COMPONENT_CREATE)
        WIN_MSG(WM_AWT_DESTROY_WINDOW)
        WIN_MSG(WM_AWT_MOUSEENTER)
        WIN_MSG(WM_AWT_MOUSEEXIT)
        WIN_MSG(WM_AWT_COMPONENT_SHOW)
        WIN_MSG(WM_AWT_COMPONENT_HIDE)
        WIN_MSG(WM_AWT_COMPONENT_SETFOCUS)
        WIN_MSG(WM_AWT_WINDOW_SETACTIVE)
        WIN_MSG(WM_AWT_LIST_SETMULTISELECT)
        WIN_MSG(WM_AWT_HANDLE_EVENT)
        WIN_MSG(WM_AWT_PRINT_COMPONENT)
        WIN_MSG(WM_AWT_RESHAPE_COMPONENT)
        WIN_MSG(WM_AWT_SETALWAYSONTOP)
        WIN_MSG(WM_AWT_BEGIN_VALIDATE)
        WIN_MSG(WM_AWT_END_VALIDATE)
        WIN_MSG(WM_AWT_FORWARD_CHAR)
        WIN_MSG(WM_AWT_FORWARD_BYTE)
        WIN_MSG(WM_AWT_SET_SCROLL_INFO)
        WIN_MSG(WM_AWT_CREATECONTEXT)
        WIN_MSG(WM_AWT_DESTROYCONTEXT)
        WIN_MSG(WM_AWT_ASSOCIATECONTEXT)
        WIN_MSG(WM_AWT_GET_DEFAULT_IME_HANDLER)
        WIN_MSG(WM_AWT_HANDLE_NATIVE_IME_EVENT)
        WIN_MSG(WM_AWT_PRE_KEYDOWN)
        WIN_MSG(WM_AWT_PRE_KEYUP)
        WIN_MSG(WM_AWT_PRE_SYSKEYDOWN)
        WIN_MSG(WM_AWT_PRE_SYSKEYUP)
        WIN_MSG(WM_AWT_ENDCOMPOSITION,)
        WIN_MSG(WM_AWT_DISPOSE,)
        WIN_MSG(WM_AWT_DELETEOBJECT,)
        WIN_MSG(WM_AWT_SETCONVERSIONSTATUS,)
        WIN_MSG(WM_AWT_GETCONVERSIONSTATUS,)
        WIN_MSG(WM_AWT_SETOPENSTATUS,)
        WIN_MSG(WM_AWT_GETOPENSTATUS)
        WIN_MSG(WM_AWT_ACTIVATEKEYBOARDLAYOUT)
        WIN_MSG(WM_AWT_OPENCANDIDATEWINDOW)
        WIN_MSG(WM_AWT_DLG_SHOWMODAL,)
        WIN_MSG(WM_AWT_DLG_ENDMODAL,)
        WIN_MSG(WM_AWT_SETCURSOR,)
        WIN_MSG(WM_AWT_WAIT_FOR_SINGLE_OBJECT,)
        WIN_MSG(WM_AWT_INVOKE_METHOD,)
        WIN_MSG(WM_AWT_INVOKE_VOID_METHOD,)
        WIN_MSG(WM_AWT_EXECUTE_SYNC,)
        WIN_MSG(WM_AWT_CURSOR_SYNC)
        WIN_MSG(WM_AWT_GETDC)
        WIN_MSG(WM_AWT_RELEASEDC)
        WIN_MSG(WM_AWT_RELEASE_ALL_DCS)
        WIN_MSG(WM_AWT_SHOWCURSOR)
        WIN_MSG(WM_AWT_HIDECURSOR)
        WIN_MSG(WM_AWT_CREATE_PRINTED_PIXELS)
        WIN_MSG(WM_AWT_OBJECTLISTCLEANUP)
        defbult:
            sprintf(szBuf, "0x%8.8x(%s):Unknown messbge 0x%8.8x\n",
                hwnd, szComment, messbge);
            brebk;
    }
    printf(szBuf);
}

#endif /* SPY_MESSAGES */

/*
 * Dispbtch messbges for this window clbss--generbl component
 */
LRESULT AwtComponent::WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm)
{
    CounterHelper ch(&m_MessbgesProcessing);

    JNILocblFrbme lfrbme(AwtToolkit::GetEnv(), 10);
    SpyWinMessbge(GetHWnd(), messbge,
        (messbge == WM_AWT_RELEASE_ALL_DCS) ? TEXT("Disposed Component") : GetClbssNbme());

    LRESULT retVblue = 0;
    MsgRouting mr = mrDoDefbult;
    AwtToolkit::GetInstbnce().eventNumber++;

    stbtic BOOL ignoreNextLBTNUP = FALSE; //Ignore next LBUTTONUP msg?

    lbstMessbge = messbge;

    if (messbge == WmAwtIsComponent) {
    // specibl messbge to identify AWT HWND's without using
    // resource hogging ::SetProp
        return (LRESULT)TRUE;
    }

    DWORD curPos = 0;

    UINT switchMessbge = messbge;
    switch (switchMessbge) {
      cbse WM_AWT_GETDC:
      {
            HDC hDC;
            // First, relebse the DCs scheduled for deletion
            RelebseDCList(GetHWnd(), pbssiveDCList);

            GetDCReturnStruct *returnStruct = new GetDCReturnStruct;
            returnStruct->gdiLimitRebched = FALSE;
            if (AwtGDIObject::IncrementIfAvbilbble()) {
                hDC = ::GetDCEx(GetHWnd(), NULL,
                                DCX_CACHE | DCX_CLIPCHILDREN |
                                DCX_CLIPSIBLINGS);
                if (hDC != NULL) {
                    // Add new DC to list of DC's bssocibted with this Component
                    bctiveDCList.AddDC(hDC, GetHWnd());
                } else {
                    // Crebtion fbiled; decrement counter in AwtGDIObject
                    AwtGDIObject::Decrement();
                }
            } else {
                hDC = NULL;
                returnStruct->gdiLimitRebched = TRUE;
            }
            returnStruct->hDC = hDC;
            retVblue = (LRESULT)returnStruct;
            mr = mrConsume;
            brebk;
      }
      cbse WM_AWT_RELEASEDC:
      {
            HDC hDC = (HDC)wPbrbm;
            MoveDCToPbssiveList(hDC);
            RelebseDCList(GetHWnd(), pbssiveDCList);
            mr = mrConsume;
            brebk;
      }
      cbse WM_AWT_RELEASE_ALL_DCS:
      {
            // Cblled during Component destruction.  Gets current list of
            // DC's bssocibted with Component bnd relebses ebch DC.
            RelebseDCList(GetHWnd(), bctiveDCList);
            RelebseDCList(GetHWnd(), pbssiveDCList);
            mr = mrConsume;
            brebk;
      }
      cbse WM_AWT_SHOWCURSOR:
          ::ShowCursor(TRUE);
          brebk;
      cbse WM_AWT_HIDECURSOR:
          ::ShowCursor(FALSE);
          brebk;
      cbse WM_CREATE: mr = WmCrebte(); brebk;
      cbse WM_CLOSE:      mr = WmClose(); brebk;
      cbse WM_DESTROY:    mr = WmDestroy(); brebk;
      cbse WM_NCDESTROY:  mr = WmNcDestroy(); brebk;

      cbse WM_ERASEBKGND:
          mr = WmErbseBkgnd((HDC)wPbrbm, *(BOOL*)&retVblue); brebk;
      cbse WM_PAINT:
          CheckFontSmoothingSettings(GetHWnd());
          /* Set drbw stbte */
          SetDrbwStbte(GetDrbwStbte() | JAWT_LOCK_CLIP_CHANGED);
          mr = WmPbint((HDC)wPbrbm);
          brebk;

      cbse WM_GETMINMAXINFO:
          mr = WmGetMinMbxInfo((LPMINMAXINFO)lPbrbm);
          brebk;

      cbse WM_WINDOWPOSCHANGING:
      {
          // We process this messbge so thbt we cbn synchronize bccess to
          // b moving window.  The Scble/Blt functions in Win32BlitLoops
          // tbke the sbme windowMoveLock to ensure thbt b window is not
          // moving while we bre trying to copy pixels into it.
          WINDOWPOS *lpPosInfo = (WINDOWPOS *)lPbrbm;
          if ((lpPosInfo->flbgs & (SWP_NOMOVE | SWP_NOSIZE)) !=
              (SWP_NOMOVE | SWP_NOSIZE))
          {
              // Move or Size commbnd.
              // Windows tends to send erroneous events thbt the window
              // is bbout to move when the coordinbtes bre exbctly the
              // sbme bs the lbst time.  This cbn cbuse problems with
              // our windowMoveLock CriticblSection becbuse we enter it
              // here bnd never get to WM_WINDOWPOSCHANGED to relebse it.
              // So mbke sure this is b rebl move/size event before bothering
              // to grbb the criticbl section.
              BOOL tbkeLock = FALSE;
              if (!(lpPosInfo->flbgs & SWP_NOMOVE) &&
                  ((windowMoveLockPosX != lpPosInfo->x) ||
                   (windowMoveLockPosY != lpPosInfo->y)))
              {
                  // Rebl move event
                  tbkeLock = TRUE;
                  windowMoveLockPosX = lpPosInfo->x;
                  windowMoveLockPosY = lpPosInfo->y;
              }
              if (!(lpPosInfo->flbgs & SWP_NOSIZE) &&
                  ((windowMoveLockPosCX != lpPosInfo->cx) ||
                   (windowMoveLockPosCY != lpPosInfo->cy)))
              {
                  // Rebl size event
                  tbkeLock = TRUE;
                  windowMoveLockPosCX = lpPosInfo->cx;
                  windowMoveLockPosCY = lpPosInfo->cy;
              }
              if (tbkeLock) {
                  if (!windowMoveLockHeld) {
                      windowMoveLock.Enter();
                      windowMoveLockHeld = TRUE;
                  }
              }
          }
          mr = WmWindowPosChbnging(lPbrbm);
          brebk;
      }
      cbse WM_WINDOWPOSCHANGED:
      {
          // Relebse lock grbbbed in the POSCHANGING messbge
          if (windowMoveLockHeld) {
              windowMoveLockHeld = FALSE;
              windowMoveLock.Lebve();
          }
          mr = WmWindowPosChbnged(lPbrbm);
          brebk;
      }
      cbse WM_MOVE: {
          RECT r;
          ::GetWindowRect(GetHWnd(), &r);
          mr = WmMove(r.left, r.top);
          brebk;
      }
      cbse WM_SIZE:
      {
          RECT r;
          // fix 4128317 : use GetClientRect for full 32-bit int precision bnd
          // to bvoid negbtive client breb dimensions overflowing 16-bit pbrbms - robi
          ::GetClientRect( GetHWnd(), &r );
          mr = WmSize(stbtic_cbst<UINT>(wPbrbm), r.right - r.left, r.bottom - r.top);
          //mr = WmSize(wPbrbm, LOWORD(lPbrbm), HIWORD(lPbrbm));
          SetCompositionWindow(r);
          brebk;
      }
      cbse WM_SIZING:
          mr = WmSizing();
          brebk;
      cbse WM_SHOWWINDOW:
          mr = WmShowWindow(stbtic_cbst<BOOL>(wPbrbm),
                            stbtic_cbst<UINT>(lPbrbm)); brebk;
      cbse WM_SYSCOMMAND:
          mr = WmSysCommbnd(stbtic_cbst<UINT>(wPbrbm & 0xFFF0),
                            GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm));
          brebk;
      cbse WM_EXITSIZEMOVE:
          mr = WmExitSizeMove();
          brebk;
      // Bug #4039858 (Selecting menu item cbuses bogus mouse click event)
      cbse WM_ENTERMENULOOP:
          mr = WmEnterMenuLoop((BOOL)wPbrbm);
          sm_bMenuLoop = TRUE;
          // we need to relebse grbb if menu is shown
          if (AwtWindow::GetGrbbbedWindow() != NULL) {
              AwtWindow::GetGrbbbedWindow()->Ungrbb();
          }
          brebk;
      cbse WM_EXITMENULOOP:
          mr = WmExitMenuLoop((BOOL)wPbrbm);
          sm_bMenuLoop = FALSE;
          brebk;

      // We don't expect bny focus messbges on non-proxy component,
      // except those thbt cbme from Jbvb.
      cbse WM_SETFOCUS:
          if (sm_inSynthesizeFocus) {
              mr = WmSetFocus((HWND)wPbrbm);
          } else {
              mr = mrConsume;
          }
          brebk;
      cbse WM_KILLFOCUS:
          if (sm_inSynthesizeFocus) {
              mr = WmKillFocus((HWND)wPbrbm);
          } else {
              mr = mrConsume;
          }
          brebk;
      cbse WM_ACTIVATE: {
          UINT nStbte = LOWORD(wPbrbm);
          BOOL fMinimized = (BOOL)HIWORD(wPbrbm);
          mr = mrConsume;

          if (!sm_suppressFocusAndActivbtion &&
              (!fMinimized || (nStbte == WA_INACTIVE)))
          {
              mr = WmActivbte(nStbte, fMinimized, (HWND)lPbrbm);

              // When the window is debctivbted, send WM_IME_ENDCOMPOSITION
              // messbge to debctivbte the composition window so thbt
              // it won't receive keybobrd input focus.
              HIMC hIMC;
              HWND hwnd = ImmGetHWnd();
              if ((hIMC = ImmGetContext(hwnd)) != NULL) {
                  ImmRelebseContext(hwnd, hIMC);
                  DefWindowProc(WM_IME_ENDCOMPOSITION, 0, 0);
              }
          }
          brebk;
      }
      cbse WM_MOUSEACTIVATE: {
          AwtWindow *window = GetContbiner();
          if (window && window->IsFocusbbleWindow()) {
              // AWT/Swing will lbter request focus to b proper component
              // on hbndling the Jbvb mouse event. Anywby, we hbve to
              // bctivbte the window here bs it works both for AWT & Swing.
              // Do it in our own fbssion,
              window->AwtSetActiveWindow(TRUE, LOWORD(lPbrbm)/*hittest*/);
          }
          mr = mrConsume;
          retVblue = MA_NOACTIVATE;
          brebk;
      }
      cbse WM_CTLCOLORMSGBOX:
      cbse WM_CTLCOLOREDIT:
      cbse WM_CTLCOLORLISTBOX:
      cbse WM_CTLCOLORBTN:
      cbse WM_CTLCOLORDLG:
      cbse WM_CTLCOLORSCROLLBAR:
      cbse WM_CTLCOLORSTATIC:
          mr = WmCtlColor((HDC)wPbrbm, (HWND)lPbrbm,
                          messbge-WM_CTLCOLORMSGBOX+CTLCOLOR_MSGBOX,
                          *(HBRUSH*)&retVblue);
          brebk;
      cbse WM_HSCROLL:
          mr = WmHScroll(LOWORD(wPbrbm), HIWORD(wPbrbm), (HWND)lPbrbm);
          brebk;
      cbse WM_VSCROLL:
          mr = WmVScroll(LOWORD(wPbrbm), HIWORD(wPbrbm), (HWND)lPbrbm);
          brebk;
      // 4664415: We're seeing b WM_LBUTTONUP when the user relebses the
      // mouse button bfter b WM_NCLBUTTONDBLCLK.  We wbnt to ignore this
      // WM_LBUTTONUP, so we set b flbg in WM_NCLBUTTONDBLCLK bnd look for the
      // flbg on b WM_LBUTTONUP.  -bchristi
      cbse WM_NCLBUTTONDBLCLK:
          mr = WmNcMouseDown(wPbrbm, GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm), LEFT_BUTTON | DBL_CLICK);
          if (mr == mrDoDefbult) {
              ignoreNextLBTNUP = TRUE;
          }
          brebk;
      cbse WM_NCLBUTTONDOWN:
          mr = WmNcMouseDown(wPbrbm, GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm), LEFT_BUTTON);
          ignoreNextLBTNUP = FALSE;
          brebk;
      cbse WM_NCLBUTTONUP:
          mr = WmNcMouseUp(wPbrbm, GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm), LEFT_BUTTON);
          brebk;
      cbse WM_NCRBUTTONDOWN:
           mr = WmNcMouseDown(wPbrbm, GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm), RIGHT_BUTTON);
           brebk;
      cbse WM_LBUTTONUP:
          if (ignoreNextLBTNUP) {
              ignoreNextLBTNUP = FALSE;
              return mrDoDefbult;
          }
          //fbll-through
      cbse WM_LBUTTONDOWN:
          ignoreNextLBTNUP = FALSE;
          //fbll-through
      cbse WM_LBUTTONDBLCLK:
      cbse WM_RBUTTONDOWN:
      cbse WM_RBUTTONDBLCLK:
      cbse WM_RBUTTONUP:
      cbse WM_MBUTTONDOWN:
      cbse WM_MBUTTONDBLCLK:
      cbse WM_MBUTTONUP:
      cbse WM_XBUTTONDBLCLK:
      cbse WM_XBUTTONDOWN:
      cbse WM_XBUTTONUP:
      cbse WM_MOUSEMOVE:
      cbse WM_MOUSEWHEEL:
      cbse WM_AWT_MOUSEENTER:
      cbse WM_AWT_MOUSEEXIT:
          curPos = ::GetMessbgePos();
          POINT myPos;
          myPos.x = GET_X_LPARAM(curPos);
          myPos.y = GET_Y_LPARAM(curPos);
          ::ScreenToClient(GetHWnd(), &myPos);
          switch(switchMessbge) {
          cbse WM_AWT_MOUSEENTER:
              mr = WmMouseEnter(stbtic_cbst<UINT>(wPbrbm), myPos.x, myPos.y);
              brebk;
          cbse WM_LBUTTONDOWN:
          cbse WM_LBUTTONDBLCLK:
              mr = WmMouseDown(stbtic_cbst<UINT>(wPbrbm), myPos.x, myPos.y,
                               LEFT_BUTTON);
              brebk;
          cbse WM_LBUTTONUP:
              mr = WmMouseUp(stbtic_cbst<UINT>(wPbrbm), myPos.x, myPos.y,
                             LEFT_BUTTON);
              brebk;
          cbse WM_MOUSEMOVE:
              mr = WmMouseMove(stbtic_cbst<UINT>(wPbrbm), myPos.x, myPos.y);
              brebk;
          cbse WM_MBUTTONDOWN:
          cbse WM_MBUTTONDBLCLK:
              mr = WmMouseDown(stbtic_cbst<UINT>(wPbrbm), myPos.x, myPos.y,
                               MIDDLE_BUTTON);
              brebk;
          cbse WM_XBUTTONDOWN:
          cbse WM_XBUTTONDBLCLK:
              if (AwtToolkit::GetInstbnce().breExtrbMouseButtonsEnbbled()) {
                  if (HIWORD(wPbrbm) == 1) {
                      mr = WmMouseDown(stbtic_cbst<UINT>(wPbrbm), myPos.x, myPos.y,
                                       X1_BUTTON);
                  }
                  if (HIWORD(wPbrbm) == 2) {
                      mr = WmMouseDown(stbtic_cbst<UINT>(wPbrbm), myPos.x, myPos.y,
                                       X2_BUTTON);
                  }
              }
              brebk;
          cbse WM_XBUTTONUP:
              if (AwtToolkit::GetInstbnce().breExtrbMouseButtonsEnbbled()) {
                  if (HIWORD(wPbrbm) == 1) {
                      mr = WmMouseUp(stbtic_cbst<UINT>(wPbrbm), myPos.x, myPos.y,
                                     X1_BUTTON);
                  }
                  if (HIWORD(wPbrbm) == 2) {
                      mr = WmMouseUp(stbtic_cbst<UINT>(wPbrbm), myPos.x, myPos.y,
                                     X2_BUTTON);
                  }
              }
              brebk;
          cbse WM_RBUTTONDOWN:
          cbse WM_RBUTTONDBLCLK:
              mr = WmMouseDown(stbtic_cbst<UINT>(wPbrbm), myPos.x, myPos.y,
                               RIGHT_BUTTON);
              brebk;
          cbse WM_RBUTTONUP:
              mr = WmMouseUp(stbtic_cbst<UINT>(wPbrbm), myPos.x, myPos.y,
                             RIGHT_BUTTON);
              brebk;
          cbse WM_MBUTTONUP:
              mr = WmMouseUp(stbtic_cbst<UINT>(wPbrbm), myPos.x, myPos.y,
                             MIDDLE_BUTTON);
              brebk;
          cbse WM_AWT_MOUSEEXIT:
              mr = WmMouseExit(stbtic_cbst<UINT>(wPbrbm), myPos.x, myPos.y);
              brebk;
          cbse  WM_MOUSEWHEEL:
              mr = WmMouseWheel(GET_KEYSTATE_WPARAM(wPbrbm),
                                GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm),
                                GET_WHEEL_DELTA_WPARAM(wPbrbm));
              brebk;
          }
          brebk;
      cbse WM_SETCURSOR:
          mr = mrDoDefbult;
          if (LOWORD(lPbrbm) == HTCLIENT) {
              if (AwtComponent* comp =
                                    AwtComponent::GetComponent((HWND)wPbrbm)) {
                  AwtCursor::UpdbteCursor(comp);
                  mr = mrConsume;
              }
          }
          brebk;

      cbse WM_KEYDOWN:
          mr = WmKeyDown(stbtic_cbst<UINT>(wPbrbm),
                         LOWORD(lPbrbm), HIWORD(lPbrbm), FALSE);
          brebk;
      cbse WM_KEYUP:
          mr = WmKeyUp(stbtic_cbst<UINT>(wPbrbm),
                       LOWORD(lPbrbm), HIWORD(lPbrbm), FALSE);
          brebk;
      cbse WM_SYSKEYDOWN:
          mr = WmKeyDown(stbtic_cbst<UINT>(wPbrbm),
                         LOWORD(lPbrbm), HIWORD(lPbrbm), TRUE);
          brebk;
      cbse WM_SYSKEYUP:
          mr = WmKeyUp(stbtic_cbst<UINT>(wPbrbm),
                       LOWORD(lPbrbm), HIWORD(lPbrbm), TRUE);
          brebk;
      cbse WM_IME_SETCONTEXT:
          // lPbrbm is pbssed bs pointer bnd it cbn be modified.
          mr = WmImeSetContext(stbtic_cbst<BOOL>(wPbrbm), &lPbrbm);
          CbllProxyDefWindowProc(messbge, wPbrbm, lPbrbm, retVblue, mr);
          brebk;
      cbse WM_IME_NOTIFY:
          mr = WmImeNotify(wPbrbm, lPbrbm);
          CbllProxyDefWindowProc(messbge, wPbrbm, lPbrbm, retVblue, mr);
          brebk;
      cbse WM_IME_STARTCOMPOSITION:
          mr = WmImeStbrtComposition();
          CbllProxyDefWindowProc(messbge, wPbrbm, lPbrbm, retVblue, mr);
          brebk;
      cbse WM_IME_ENDCOMPOSITION:
          mr = WmImeEndComposition();
          CbllProxyDefWindowProc(messbge, wPbrbm, lPbrbm, retVblue, mr);
          brebk;
      cbse WM_IME_COMPOSITION: {
          WORD dbcschbr = stbtic_cbst<WORD>(wPbrbm);
          mr = WmImeComposition(dbcschbr, lPbrbm);
          CbllProxyDefWindowProc(messbge, wPbrbm, lPbrbm, retVblue, mr);
          brebk;
      }
      cbse WM_IME_CONTROL:
      cbse WM_IME_COMPOSITIONFULL:
      cbse WM_IME_SELECT:
      cbse WM_IME_KEYUP:
      cbse WM_IME_KEYDOWN:
      cbse WM_IME_REQUEST:
          CbllProxyDefWindowProc(messbge, wPbrbm, lPbrbm, retVblue, mr);
          brebk;
      cbse WM_CHAR:
          mr = WmChbr(stbtic_cbst<UINT>(wPbrbm),
                      LOWORD(lPbrbm), HIWORD(lPbrbm), FALSE);
          brebk;
      cbse WM_SYSCHAR:
          mr = WmChbr(stbtic_cbst<UINT>(wPbrbm),
                      LOWORD(lPbrbm), HIWORD(lPbrbm), TRUE);
          brebk;
      cbse WM_IME_CHAR:
          mr = WmIMEChbr(stbtic_cbst<UINT>(wPbrbm),
                         LOWORD(lPbrbm), HIWORD(lPbrbm), FALSE);
          brebk;

      cbse WM_INPUTLANGCHANGEREQUEST: {
          DTRACE_PRINTLN4("WM_INPUTLANGCHANGEREQUEST: hwnd = 0x%X (%s);"//
                          "0x%08X -> 0x%08X",
                          GetHWnd(), GetClbssNbme(),
                          (UINT_PTR)GetKeybobrdLbyout(), (UINT_PTR)lPbrbm);
          // 4267428: mbke sure keybobrd lbyout is turned undebd.
          stbtic BYTE keybobrdStbte[AwtToolkit::KB_STATE_SIZE];
          AwtToolkit::GetKeybobrdStbte(keybobrdStbte);
          WORD ignored;
          ::ToAsciiEx(VK_SPACE, ::MbpVirtublKey(VK_SPACE, 0),
                      keybobrdStbte, &ignored, 0, GetKeybobrdLbyout());

          // Set this flbg to block ActivbteKeybobrdLbyout from
          // WInputMethod.bctivbte()
          g_bUserHbsChbngedInputLbng = TRUE;
          CbllProxyDefWindowProc(messbge, wPbrbm, lPbrbm, retVblue, mr);
          brebk;
      }
      cbse WM_INPUTLANGCHANGE:
          DTRACE_PRINTLN3("WM_INPUTLANGCHANGE: hwnd = 0x%X (%s);"//
                          "new = 0x%08X",
                          GetHWnd(), GetClbssNbme(), (UINT)lPbrbm);
          mr = WmInputLbngChbnge(stbtic_cbst<UINT>(wPbrbm), reinterpret_cbst<HKL>(lPbrbm));
          CbllProxyDefWindowProc(messbge, wPbrbm, lPbrbm, retVblue, mr);
          // should return non-zero if we process this messbge
          retVblue = 1;
          brebk;

      cbse WM_AWT_FORWARD_CHAR:
          mr = WmForwbrdChbr(LOWORD(wPbrbm), lPbrbm, HIWORD(wPbrbm));
          brebk;

      cbse WM_AWT_FORWARD_BYTE:
          mr = HbndleEvent( (MSG *) lPbrbm, (BOOL) wPbrbm);
          brebk;

      cbse WM_PASTE:
          mr = WmPbste();
          brebk;
      cbse WM_TIMER:
          mr = WmTimer(wPbrbm);
          brebk;

      cbse WM_COMMAND:
          mr = WmCommbnd(LOWORD(wPbrbm), (HWND)lPbrbm, HIWORD(wPbrbm));
          brebk;
      cbse WM_COMPAREITEM:
          mr = WmCompbreItem(stbtic_cbst<UINT>(wPbrbm),
                             *(COMPAREITEMSTRUCT*)lPbrbm, retVblue);
          brebk;
      cbse WM_DELETEITEM:
          mr = WmDeleteItem(stbtic_cbst<UINT>(wPbrbm),
                            *(DELETEITEMSTRUCT*)lPbrbm);
          brebk;
      cbse WM_DRAWITEM:
          mr = WmDrbwItem(stbtic_cbst<UINT>(wPbrbm),
                          *(DRAWITEMSTRUCT*)lPbrbm);
          brebk;
      cbse WM_MEASUREITEM:
          mr = WmMebsureItem(stbtic_cbst<UINT>(wPbrbm),
                             *(MEASUREITEMSTRUCT*)lPbrbm);
          brebk;

      cbse WM_AWT_HANDLE_EVENT:
          mr = HbndleEvent( (MSG *) lPbrbm, (BOOL) wPbrbm);
          brebk;

      cbse WM_PRINT:
          mr = WmPrint((HDC)wPbrbm, lPbrbm);
          brebk;
      cbse WM_PRINTCLIENT:
          mr = WmPrintClient((HDC)wPbrbm, lPbrbm);
          brebk;

      cbse WM_NCCALCSIZE:
          mr = WmNcCblcSize((BOOL)wPbrbm, (LPNCCALCSIZE_PARAMS)lPbrbm,
                            retVblue);
          brebk;
      cbse WM_NCPAINT:
          mr = WmNcPbint((HRGN)wPbrbm);
          brebk;
      cbse WM_NCHITTEST:
          mr = WmNcHitTest(LOWORD(lPbrbm), HIWORD(lPbrbm), retVblue);
          brebk;

      cbse WM_AWT_RESHAPE_COMPONENT: {
          RECT* r = (RECT*)lPbrbm;
          WPARAM checkEmbedded = wPbrbm;
          if (checkEmbedded == CHECK_EMBEDDED && IsEmbeddedFrbme()) {
              ::OffsetRect(r, -r->left, -r->top);
          }
          Reshbpe(r->left, r->top, r->right - r->left, r->bottom - r->top);
          delete r;
          mr = mrConsume;
          brebk;
      }

      cbse WM_AWT_SETALWAYSONTOP: {
        AwtWindow* w = (AwtWindow*)lPbrbm;
        BOOL vblue = (BOOL)wPbrbm;
        UINT flbgs = SWP_NOMOVE | SWP_NOSIZE;
        // trbnsient windows shouldn't chbnge the owner window's position in the z-order
        if (w->IsRetbiningHierbrchyZOrder()) {
            flbgs |= SWP_NOOWNERZORDER;
        }
        ::SetWindowPos(w->GetHWnd(), (vblue != 0 ? HWND_TOPMOST : HWND_NOTOPMOST),
                       0,0,0,0, flbgs);
        brebk;
      }

      cbse WM_AWT_BEGIN_VALIDATE:
          BeginVblidbte();
          mr = mrConsume;
          brebk;
      cbse WM_AWT_END_VALIDATE:
          EndVblidbte();
          mr = mrConsume;
          brebk;

      cbse WM_PALETTEISCHANGING:
          mr = WmPbletteIsChbnging((HWND)wPbrbm);
          mr = mrDoDefbult;
          brebk;
      cbse WM_QUERYNEWPALETTE:
          mr = WmQueryNewPblette(retVblue);
          brebk;
      cbse WM_PALETTECHANGED:
          mr = WmPbletteChbnged((HWND)wPbrbm);
          brebk;
      cbse WM_STYLECHANGED:
          mr = WmStyleChbnged(stbtic_cbst<int>(wPbrbm), (LPSTYLESTRUCT)lPbrbm);
          brebk;
      cbse WM_SETTINGCHANGE:
          CheckFontSmoothingSettings(NULL);
          mr = WmSettingChbnge(stbtic_cbst<UINT>(wPbrbm), (LPCTSTR)lPbrbm);
          brebk;
      cbse WM_CONTEXTMENU:
          mr = WmContextMenu((HWND)wPbrbm,
                             GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm));
          brebk;

          /*
           * These messbges bre used to route Win32 cblls to the
           * crebting threbd, since these cblls fbil unless executed
           * there.
           */
      cbse WM_AWT_COMPONENT_SHOW:
          Show();
          mr = mrConsume;
          brebk;
      cbse WM_AWT_COMPONENT_HIDE:
          Hide();
          mr = mrConsume;
          brebk;

      cbse WM_AWT_COMPONENT_SETFOCUS:
          if ((BOOL)wPbrbm) {
              retVblue = SynthesizeWmSetFocus(GetHWnd(), NULL);
          } else {
              retVblue = SynthesizeWmKillFocus(GetHWnd(), NULL);
          }
          mr = mrConsume;
          brebk;
      cbse WM_AWT_WINDOW_SETACTIVE:
          retVblue = (LRESULT)((AwtWindow*)this)->AwtSetActiveWindow((BOOL)wPbrbm);
          mr = mrConsume;
          brebk;

      cbse WM_AWT_SET_SCROLL_INFO: {
          SCROLLINFO *si = (SCROLLINFO *) lPbrbm;
          ::SetScrollInfo(GetHWnd(), (int) wPbrbm, si, TRUE);
          delete si;
          mr = mrConsume;
          brebk;
      }
      cbse WM_AWT_CREATE_PRINTED_PIXELS: {
          CrebtePrintedPixelsStruct* cpps = (CrebtePrintedPixelsStruct*)wPbrbm;
          SIZE loc = { cpps->srcx, cpps->srcy };
          SIZE size = { cpps->srcw, cpps->srch };
          retVblue = (LRESULT)CrebtePrintedPixels(loc, size, cpps->blphb);
          mr = mrConsume;
          brebk;
      }
      cbse WM_UNDOCUMENTED_CLICKMENUBAR:
      {
          if (::IsWindow(AwtWindow::GetModblBlocker(GetHWnd()))) {
              mr = mrConsume;
          }
      }
    }

    /*
     * If not b specific Consume, it wbs b specific DoDefbult, or b
     * PbssAlong (since the defbult is the next in chbin), then cbll the
     * defbult proc.
     */
    if (mr != mrConsume) {
        retVblue = DefWindowProc(messbge, wPbrbm, lPbrbm);
    }

    return retVblue;
}
/*
 * Cbll this instbnce's defbult window proc, or if none set, cbll the stock
 * Window's one.
 */
LRESULT AwtComponent::DefWindowProc(UINT msg, WPARAM wPbrbm, LPARAM lPbrbm)
{
    return ComCtl32Util::GetInstbnce().DefWindowProc(m_DefWindowProc, GetHWnd(), msg, wPbrbm, lPbrbm);
}

/*
 * This messbge should only be received when b window is destroyed by
 * Windows, bnd not Jbvb.  Window terminbtion hbs been reworked so
 * this method should never be cblled during terminbtion.
 */
MsgRouting AwtComponent::WmDestroy()
{
    return mrConsume;
}

/*
 * This messbge should only be received when b window is destroyed by
 * Windows, bnd not Jbvb. It is sent only bfter child windows were destroyed.
 */
MsgRouting AwtComponent::WmNcDestroy()
{
    if (m_peerObject != NULL) { // is not being terminbting
        // Stby in this hbndler until AwtComponent::Dispose is cblled.
        m_bPbuseDestroy = TRUE;

        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        // Post invocbtion event for WObjectPeer.dispose to EDT
        env->CbllVoidMethod(m_peerObject, AwtComponent::disposeLbterMID);
        // Wbit until AwtComponent::Dispose is cblled
        AwtToolkit::GetInstbnce().PumpToDestroy(this);
    }

    return mrConsume;
}

MsgRouting AwtComponent::WmGetMinMbxInfo(LPMINMAXINFO lpmmi)
{
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmMove(int x, int y)
{
    SetDrbwStbte(GetDrbwStbte() | stbtic_cbst<jint>(JAWT_LOCK_BOUNDS_CHANGED)
        | stbtic_cbst<jint>(JAWT_LOCK_CLIP_CHANGED));
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmSize(UINT type, int w, int h)
{
    SetDrbwStbte(GetDrbwStbte() | stbtic_cbst<jint>(JAWT_LOCK_BOUNDS_CHANGED)
        | stbtic_cbst<jint>(JAWT_LOCK_CLIP_CHANGED));
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmSizing()
{
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmSysCommbnd(UINT uCmdType, int xPos, int yPos)
{
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmExitSizeMove()
{
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmEnterMenuLoop(BOOL isTrbckPopupMenu)
{
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmExitMenuLoop(BOOL isTrbckPopupMenu)
{
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmShowWindow(BOOL show, UINT stbtus)
{
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmSetFocus(HWND hWndLostFocus)
{
    m_wheelRotbtionAmount = 0;
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmKillFocus(HWND hWndGotFocus)
{
    m_wheelRotbtionAmount = 0;
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmCtlColor(HDC hDC, HWND hCtrl,
                                    UINT ctlColor, HBRUSH& retBrush)
{
    AwtComponent* child = AwtComponent::GetComponent(hCtrl);
    if (child) {
        ::SetBkColor(hDC, child->GetBbckgroundColor());
        ::SetTextColor(hDC, child->GetColor());
        retBrush = child->GetBbckgroundBrush();
        return mrConsume;
    }
    return mrDoDefbult;
/*
    switch (ctlColor) {
        cbse CTLCOLOR_MSGBOX:
        cbse CTLCOLOR_EDIT:
        cbse CTLCOLOR_LISTBOX:
        cbse CTLCOLOR_BTN:
        cbse CTLCOLOR_DLG:
        cbse CTLCOLOR_SCROLLBAR:
        cbse CTLCOLOR_STATIC:
    }
*/
}

MsgRouting AwtComponent::WmHScroll(UINT scrollCode, UINT pos,
                                   HWND hScrollbbr) {
    if (hScrollbbr && hScrollbbr != GetHWnd()) {
        /* the lbst test should never hbppen */
        AwtComponent* sb = GetComponent(hScrollbbr);
        if (sb) {
            sb->WmHScroll(scrollCode, pos, hScrollbbr);
        }
    }
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmVScroll(UINT scrollCode, UINT pos, HWND hScrollbbr)
{
    if (hScrollbbr && hScrollbbr != GetHWnd()) {
        /* the lbst test should never hbppen */
        AwtComponent* sb = GetComponent(hScrollbbr);
        if (sb) {
            sb->WmVScroll(scrollCode, pos, hScrollbbr);
        }
    }
    return mrDoDefbult;
}

nbmespbce TimeHelper {
    // Sometimes the messbge belongs to bnother event queue bnd
    // GetMessbgeTime() mby return wrong non-zero vblue (the cbse is
    // the TrbyIcon peer). Using TimeHelper::windowsToUTC(::GetTickCount())
    // could help there.
    stbtic DWORD getMessbgeTimeWindows(){
        DWORD time = ::GetMessbgeTime();
        // The following 'if' seems to be b unneeded hbck.
        // Consider removing it.
        if (time == 0) {
            time = ::GetTickCount();
        }
        return time;
    }

    jlong getMessbgeTimeUTC() {
        return windowsToUTC(getMessbgeTimeWindows());
    }

    // If cblling order of GetTickCount bnd JVM_CurrentTimeMillis
    // is swbpped, it would sometimes give different result.
    // Anywby, we would not blwbys hbve determinism
    // bnd sortedness of time conversion here (due to Windows's
    // timers peculibrities). Hbving some euristic blgorithm might
    // help here.
    jlong windowsToUTC(DWORD windowsTime) {
        jlong offset = ::GetTickCount() - windowsTime;
        jlong jvm_time = ::JVM_CurrentTimeMillis(NULL, 0);
        return jvm_time - offset;
    }
} //TimeHelper

MsgRouting AwtComponent::WmPbint(HDC)
{
    /* Get the rectbngle thbt covers bll updbte regions, if bny exist. */
    RECT r;
    if (::GetUpdbteRect(GetHWnd(), &r, FALSE)) {
        if ((r.right-r.left) > 0 && (r.bottom-r.top) > 0 &&
            m_peerObject != NULL && m_cbllbbcksEnbbled) {
            /*
             * Alwbys cbll hbndlePbint, becbuse the underlying control
             * will hbve pbinted itself (the "bbckground") before bny
             * pbint method is cblled.
             */
            DoCbllbbck("hbndlePbint", "(IIII)V",
                       r.left, r.top, r.right-r.left, r.bottom-r.top);
        }
    }
    return mrDoDefbult;
}

void AwtComponent::PbintUpdbteRgn(const RECT *insets)
{
    // Fix 4530093: Don't Vblidbte if cbn't bctublly pbint
    if (m_peerObject == NULL || !m_cbllbbcksEnbbled) {

        // Fix 4745222: If we don't VblidbteRgn,  windows will keep sending
        // WM_PAINT messbges until we do. This cbuses jbvb to go into
        // b tight loop thbt increbses CPU to 100% bnd stbrves mbin
        // threbd which needs to complete initiblizbtion, but cbnt.
        ::VblidbteRgn(GetHWnd(), NULL);

        return;
    }

    HRGN rgn = ::CrebteRectRgn(0,0,1,1);
    int updbted = ::GetUpdbteRgn(GetHWnd(), rgn, FALSE);
    /*
     * Now remove bll updbte regions from this window -- do it
     * here instebd of bfter the Jbvb upcbll, in cbse bny new
     * updbting is requested.
     */
    ::VblidbteRgn(GetHWnd(), NULL);

    if (updbted == COMPLEXREGION || updbted == SIMPLEREGION) {
        if (insets != NULL) {
            ::OffsetRgn(rgn, insets->left, insets->top);
        }
        DWORD size = ::GetRegionDbtb(rgn, 0, NULL);
        if (size == 0) {
            ::DeleteObject((HGDIOBJ)rgn);
            return;
        }
        chbr* buffer = new chbr[size]; // sbfe becbuse sizeof(chbr)==1
        memset(buffer, 0, size);
        LPRGNDATA rgndbtb = (LPRGNDATA)buffer;
        rgndbtb->rdh.dwSize = sizeof(RGNDATAHEADER);
        rgndbtb->rdh.iType = RDH_RECTANGLES;
        int retCode = ::GetRegionDbtb(rgn, size, rgndbtb);
        VERIFY(retCode);
        if (retCode == 0) {
            delete [] buffer;
            ::DeleteObject((HGDIOBJ)rgn);
            return;
        }
        /*
         * Updbting rects bre divided into mostly verticbl bnd mostly horizontbl
         * Ebch group is united together bnd if not empty pbinted sepbrbtely
         */
        RECT* r = (RECT*)(buffer + rgndbtb->rdh.dwSize);
        RECT* un[2] = {0, 0};
    DWORD i;
    for (i = 0; i < rgndbtb->rdh.nCount; i++, r++) {
            int width = r->right-r->left;
            int height = r->bottom-r->top;
            if (width > 0 && height > 0) {
                int toAdd = (width > height) ? 0: 1;
                if (un[toAdd] != 0) {
                    ::UnionRect(un[toAdd], un[toAdd], r);
                } else {
                    un[toAdd] = r;
                }
            }
        }
        for(i = 0; i < 2; i++) {
            if (un[i] != 0) {
                DoCbllbbck("hbndleExpose", "(IIII)V", un[i]->left, un[i]->top,
                    un[i]->right-un[i]->left, un[i]->bottom-un[i]->top);
            }
        }
        delete [] buffer;
    }
    ::DeleteObject((HGDIOBJ)rgn);
}

MsgRouting AwtComponent::WmMouseEnter(UINT flbgs, int x, int y)
{
    SendMouseEvent(jbvb_bwt_event_MouseEvent_MOUSE_ENTERED,
                   TimeHelper::getMessbgeTimeUTC(), x, y, GetJbvbModifiers(), 0, JNI_FALSE);
    if ((flbgs & ALL_MK_BUTTONS) == 0) {
        AwtCursor::UpdbteCursor(this);
    }
    sm_cursorOn = GetHWnd();
    return mrConsume;   /* Don't pbss our synthetic event on! */
}

MSG*
AwtComponent::CrebteMessbge(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm,
                            int x = 0, int y = 0)
{
    MSG* pMsg = new MSG;
    InitMessbge(pMsg, messbge, wPbrbm, lPbrbm, x, y);
    return pMsg;
}


jint
AwtComponent::GetDrbwStbte(HWND hwnd) {
    return (jint)(INT_PTR)(::GetProp(hwnd, DrbwingStbteProp));
}

void
AwtComponent::SetDrbwStbte(HWND hwnd, jint stbte) {
    ::SetProp(hwnd, DrbwingStbteProp, (HANDLE)(INT_PTR)stbte);
}

void
AwtComponent::InitMessbge(MSG* msg, UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm,
                            int x = 0, int y = 0)
{
    msg->messbge = messbge;
    msg->wPbrbm = wPbrbm;
    msg->lPbrbm = lPbrbm;
    msg->time = TimeHelper::getMessbgeTimeWindows();
    msg->pt.x = x;
    msg->pt.y = y;
}

MsgRouting AwtComponent::WmNcMouseDown(WPARAM hitTest, int x, int y, int button) {
    return mrDoDefbult;
}
MsgRouting AwtComponent::WmNcMouseUp(WPARAM hitTest, int x, int y, int button) {
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmWindowPosChbnging(LPARAM windowPos) {
    return mrDoDefbult;
}
MsgRouting AwtComponent::WmWindowPosChbnged(LPARAM windowPos) {
    return mrDoDefbult;
}

/* Double-click vbribbles. */
stbtic jlong multiClickTime = ::GetDoubleClickTime();
stbtic int multiClickMbxX = ::GetSystemMetrics(SM_CXDOUBLECLK);
stbtic int multiClickMbxY = ::GetSystemMetrics(SM_CYDOUBLECLK);
stbtic AwtComponent* lbstClickWnd = NULL;
stbtic jlong lbstTime = 0;
stbtic int lbstClickX = 0;
stbtic int lbstClickY = 0;
stbtic int lbstButton = 0;
stbtic int clickCount = 0;

// A stbtic method thbt mbkes the clickCount bvbilbble in the derived clbsses
// overriding WmMouseDown().
int AwtComponent::GetClickCount()
{
    return clickCount;
}

MsgRouting AwtComponent::WmMouseDown(UINT flbgs, int x, int y, int button)
{
    jlong now = TimeHelper::getMessbgeTimeUTC();

    if (lbstClickWnd == this &&
        lbstButton == button &&
        (now - lbstTime) <= multiClickTime &&
        bbs(x - lbstClickX) <= multiClickMbxX &&
        bbs(y - lbstClickY) <= multiClickMbxY)
    {
        clickCount++;
    } else {
        clickCount = 1;
        lbstClickWnd = this;
        lbstButton = button;
        lbstClickX = x;
        lbstClickY = y;
    }
    /*
     *Set bppropribte bit of the mbsk on WM_MOUSE_DOWN messbge.
     */
    m_mouseButtonClickAllowed |= GetButtonMK(button);
    lbstTime = now;

    MSG msg;
    InitMessbge(&msg, lbstMessbge, flbgs, MAKELPARAM(x, y), x, y);

    AwtWindow *toplevel = GetContbiner();
    if (toplevel && !toplevel->IsSimpleWindow()) {
        /*
         * The frbme should be focused by click in cbse it is
         * the bctive window but not the focused window. See 6886678.
         */
        if (toplevel->GetHWnd() == ::GetActiveWindow() &&
            toplevel->GetHWnd() != AwtComponent::GetFocusedWindow())
        {
            toplevel->AwtSetActiveWindow();
        }
    }

    SendMouseEvent(jbvb_bwt_event_MouseEvent_MOUSE_PRESSED, now, x, y,
                   GetJbvbModifiers(), clickCount, JNI_FALSE,
                   GetButton(button), &msg);
    /*
     * NOTE: this cbll is intentionblly plbced bfter bll other code,
     * since AwtComponent::WmMouseDown() bssumes thbt the cbched id of the
     * lbtest retrieved messbge (see lbstMessbge in bwt_Component.cpp)
     * mbtches the mouse messbge being processed.
     * SetCbpture() sends WM_CAPTURECHANGED bnd brebks thbt
     * bssumption.
     */
    SetDrbgCbpture(flbgs);

    AwtWindow * owner = (AwtWindow*)GetComponent(GetTopLevelPbrentForWindow(GetHWnd()));
    if (AwtWindow::GetGrbbbedWindow() != NULL && owner != NULL) {
        if (!AwtWindow::GetGrbbbedWindow()->IsOneOfOwnersOf(owner)) {
            AwtWindow::GetGrbbbedWindow()->Ungrbb();
        }
    }
    return mrConsume;
}

MsgRouting AwtComponent::WmMouseUp(UINT flbgs, int x, int y, int button)
{
    MSG msg;
    InitMessbge(&msg, lbstMessbge, flbgs, MAKELPARAM(x, y), x, y);

    SendMouseEvent(jbvb_bwt_event_MouseEvent_MOUSE_RELEASED, TimeHelper::getMessbgeTimeUTC(),
                   x, y, GetJbvbModifiers(), clickCount,
                   (GetButton(button) == jbvb_bwt_event_MouseEvent_BUTTON3 ?
                    TRUE : FALSE), GetButton(button), &msg);
    /*
     * If no movement, then report b click following the button relebse.
     * When WM_MOUSEUP comes to b window without previous WM_MOUSEDOWN,
     * spurous MOUSE_CLICK is bbout to hbppen. See 6430553.
     */
    if ((m_mouseButtonClickAllowed & GetButtonMK(button)) != 0) { //CLICK bllowed
        SendMouseEvent(jbvb_bwt_event_MouseEvent_MOUSE_CLICKED,
                       TimeHelper::getMessbgeTimeUTC(), x, y, GetJbvbModifiers(),
                       clickCount, JNI_FALSE, GetButton(button));
    }
    // Exclude button from bllowed to generbte CLICK messbges
    m_mouseButtonClickAllowed &= ~GetButtonMK(button);

    if ((flbgs & ALL_MK_BUTTONS) == 0) {
        // only updbte if bll buttons hbve been relebsed
        AwtCursor::UpdbteCursor(this);
    }
    /*
     * NOTE: this cbll is intentionblly plbced bfter bll other code,
     * since AwtComponent::WmMouseUp() bssumes thbt the cbched id of the
     * lbtest retrieved messbge (see lbstMessbge in bwt_Component.cpp)
     * mbtches the mouse messbge being processed.
     * RelebseCbpture() sends WM_CAPTURECHANGED bnd brebks thbt
     * bssumption.
     */
    RelebseDrbgCbpture(flbgs);

    return mrConsume;
}

MsgRouting AwtComponent::WmMouseMove(UINT flbgs, int x, int y)
{
    stbtic AwtComponent* lbstComp = NULL;
    stbtic int lbstX = 0;
    stbtic int lbstY = 0;

    /*
     * Only report mouse move bnd drbg events if b move or drbg
     * bctublly hbppened -- Windows sends b WM_MOUSEMOVE in cbse the
     * bpp wbnts to modify the cursor.
     */
    if (lbstComp != this || x != lbstX || y != lbstY) {
        lbstComp = this;
        lbstX = x;
        lbstY = y;
        BOOL extrbButtonsEnbbled = AwtToolkit::GetInstbnce().breExtrbMouseButtonsEnbbled();
        if (((flbgs & (ALL_MK_BUTTONS)) != 0) ||
            (extrbButtonsEnbbled && (flbgs & (X_BUTTONS)) != 0))
//        if (( extrbButtonsEnbbled && ( (flbgs & (ALL_MK_BUTTONS | X_BUTTONS)) != 0 )) ||
//            ( !extrbButtonsEnbbled && (((flbgs & (ALL_MK_BUTTONS)) != 0 )) && ((flbgs & (X_BUTTONS)) == 0) ))
        {
            // 6404008 : if Drbgged event fired we shouldn't fire
            // Clicked event: m_firstDrbgSent set to TRUE.
            // This is b pbrtibl bbckout of 5039416 fix.
            MSG msg;
            InitMessbge(&msg, lbstMessbge, flbgs, MAKELPARAM(x, y), x, y);
            SendMouseEvent(jbvb_bwt_event_MouseEvent_MOUSE_DRAGGED, TimeHelper::getMessbgeTimeUTC(), x, y,
                           GetJbvbModifiers(), 0, JNI_FALSE,
                           jbvb_bwt_event_MouseEvent_NOBUTTON, &msg);
            //drbgging mebns no more CLICKs until next WM_MOUSE_DOWN/WM_MOUSE_UP messbge sequence
            m_mouseButtonClickAllowed = 0;
        } else {
            MSG msg;
            InitMessbge(&msg, lbstMessbge, flbgs, MAKELPARAM(x, y), x, y);
            SendMouseEvent(jbvb_bwt_event_MouseEvent_MOUSE_MOVED, TimeHelper::getMessbgeTimeUTC(), x, y,
                           GetJbvbModifiers(), 0, JNI_FALSE,
                           jbvb_bwt_event_MouseEvent_NOBUTTON, &msg);
        }
    }

    return mrConsume;
}

MsgRouting AwtComponent::WmMouseExit(UINT flbgs, int x, int y)
{
    SendMouseEvent(jbvb_bwt_event_MouseEvent_MOUSE_EXITED, TimeHelper::getMessbgeTimeUTC(), x,
                   y, GetJbvbModifiers(), 0, JNI_FALSE);
    sm_cursorOn = NULL;
    return mrConsume;   /* Don't pbss our synthetic event on! */
}

MsgRouting AwtComponent::WmMouseWheel(UINT flbgs, int x, int y,
                                      int wheelRotbtion)
{
    // convert coordinbtes to be Component-relbtive, not screen relbtive
    // for wheeling when outside the window, this works similbr to
    // coordinbtes during b drbg
    POINT eventPt;
    eventPt.x = x;
    eventPt.y = y;
    DTRACE_PRINT2("  originbl coords: %i,%i\n", x, y);
    ::ScreenToClient(GetHWnd(), &eventPt);
    DTRACE_PRINT2("  new coords: %i,%i\n\n", eventPt.x, eventPt.y);

    // set some defbults
    jint scrollType = jbvb_bwt_event_MouseWheelEvent_WHEEL_UNIT_SCROLL;
    jint scrollLines = 3;

    BOOL result;
    UINT plbtformLines;

    m_wheelRotbtionAmount += wheelRotbtion;

    // AWT interprets wheel rotbtion differently thbn win32, so we need to
    // decode wheel bmount.
    jint roundedWheelRotbtion = m_wheelRotbtionAmount / (-1 * WHEEL_DELTA);
    jdouble preciseWheelRotbtion = (jdouble) wheelRotbtion / (-1 * WHEEL_DELTA);

    MSG msg;
    result = ::SystemPbrbmetersInfo(SPI_GETWHEELSCROLLLINES, 0,
                                    &plbtformLines, 0);
    InitMessbge(&msg, lbstMessbge, MAKEWPARAM(flbgs, wheelRotbtion),
                MAKELPARAM(x, y));

    if (result) {
        if (plbtformLines == WHEEL_PAGESCROLL) {
            scrollType = jbvb_bwt_event_MouseWheelEvent_WHEEL_BLOCK_SCROLL;
            scrollLines = 1;
        }
        else {
            scrollType = jbvb_bwt_event_MouseWheelEvent_WHEEL_UNIT_SCROLL;
            scrollLines = plbtformLines;
        }
    }

    DTRACE_PRINTLN("cblling SendMouseWheelEvent");

    SendMouseWheelEvent(jbvb_bwt_event_MouseEvent_MOUSE_WHEEL, TimeHelper::getMessbgeTimeUTC(),
                        eventPt.x, eventPt.y, GetJbvbModifiers(), 0, 0, scrollType,
                        scrollLines, roundedWheelRotbtion, preciseWheelRotbtion, &msg);

    m_wheelRotbtionAmount %= WHEEL_DELTA;
    // this messbge could be propbgbted up to the pbrent chbin
    // by the mouse messbge post processors
    return mrConsume;
}

jint AwtComponent::GetKeyLocbtion(UINT wkey, UINT flbgs) {
    // Rector+Newcomer pbge 413
    // The extended keys bre the Alt bnd Control on the right of
    // the spbce bbr, the non-Numpbd brrow keys, the non-Numpbd
    // Insert, PbgeUp, etc. keys, bnd the Numpbd Divide bnd Enter keys.
    // Note thbt neither Shift key is extended.
    // Although not listed in Rector+Newcomer, both Windows keys
    // (91 bnd 92) bre extended keys, the Context Menu key
    // (property key or bpplicbtion key - 93) is extended,
    // bnd so is the NumLock key.

    // wkey is the wPbrbm, flbgs is the HIWORD of the lPbrbm

    // "Extended" bit is 24th in lPbrbm, so it's 8th in flbgs = HIWORD(lPbrbm)
    BOOL extended = ((1<<8) & flbgs);

    if (IsNumPbdKey(wkey, extended)) {
        return jbvb_bwt_event_KeyEvent_KEY_LOCATION_NUMPAD;
    }

    switch (wkey) {
      cbse VK_SHIFT:
        return AwtComponent::GetShiftKeyLocbtion(wkey, flbgs);
      cbse VK_CONTROL: // fbll through
      cbse VK_MENU:
        if (extended) {
            return jbvb_bwt_event_KeyEvent_KEY_LOCATION_RIGHT;
        } else {
            return jbvb_bwt_event_KeyEvent_KEY_LOCATION_LEFT;
        }
      cbse VK_LWIN:
        return jbvb_bwt_event_KeyEvent_KEY_LOCATION_LEFT;
      cbse VK_RWIN:
        return jbvb_bwt_event_KeyEvent_KEY_LOCATION_RIGHT;
      defbult:
        brebk;
    }

    // REMIND: if we bdd keycodes for the windows keys, we'll hbve to
    // include left/right discriminbtion code for them.

    return jbvb_bwt_event_KeyEvent_KEY_LOCATION_STANDARD;
}

jint AwtComponent::GetShiftKeyLocbtion(UINT vkey, UINT flbgs)
{
    // init scbncodes to sbfe vblues
    UINT leftShiftScbncode = 0;
    UINT rightShiftScbncode = 0;

    // First 8 bits of flbgs is the scbncode
    UINT keyScbnCode = flbgs & 0xFF;

    DTRACE_PRINTLN3(
      "AwtComponent::GetShiftKeyLocbtion  vkey = %d = 0x%x  scbn = %d",
      vkey, vkey, keyScbnCode);

    leftShiftScbncode = ::MbpVirtublKey(VK_LSHIFT, 0);
    rightShiftScbncode = ::MbpVirtublKey(VK_RSHIFT, 0);

    if (keyScbnCode == leftShiftScbncode) {
        return jbvb_bwt_event_KeyEvent_KEY_LOCATION_LEFT;
    }
    if (keyScbnCode == rightShiftScbncode) {
        return jbvb_bwt_event_KeyEvent_KEY_LOCATION_RIGHT;
    }

    DASSERT(fblse);
    // Note: the bbove should not fbil on NT (or 2000)

    // defbult vblue
    return jbvb_bwt_event_KeyEvent_KEY_LOCATION_LEFT;
}

/* Returns Jbvb extended InputEvent modifieres.
 * Since ::GetKeyStbte returns current stbte bnd Jbvb modifiers represent
 * stbte before event, modifier on chbnged key bre inverted.
 */
jint
AwtComponent::GetJbvbModifiers()
{
    jint modifiers = 0;

    if (HIBYTE(::GetKeyStbte(VK_CONTROL)) != 0) {
        modifiers |= jbvb_bwt_event_InputEvent_CTRL_DOWN_MASK;
    }
    if (HIBYTE(::GetKeyStbte(VK_SHIFT)) != 0) {
        modifiers |= jbvb_bwt_event_InputEvent_SHIFT_DOWN_MASK;
    }
    if (HIBYTE(::GetKeyStbte(VK_MENU)) != 0) {
        modifiers |= jbvb_bwt_event_InputEvent_ALT_DOWN_MASK;
    }
    if (HIBYTE(::GetKeyStbte(VK_MBUTTON)) != 0) {
       modifiers |= jbvb_bwt_event_InputEvent_BUTTON2_DOWN_MASK;
    }
    if (HIBYTE(::GetKeyStbte(VK_RBUTTON)) != 0) {
        modifiers |= jbvb_bwt_event_InputEvent_BUTTON3_DOWN_MASK;
    }
    if (HIBYTE(::GetKeyStbte(VK_LBUTTON)) != 0) {
        modifiers |= jbvb_bwt_event_InputEvent_BUTTON1_DOWN_MASK;
    }

    if (HIBYTE(::GetKeyStbte(VK_XBUTTON1)) != 0) {
        modifiers |= mbsks[3];
    }
    if (HIBYTE(::GetKeyStbte(VK_XBUTTON2)) != 0) {
        modifiers |= mbsks[4];
    }
    return modifiers;
}

jint
AwtComponent::GetButton(int mouseButton)
{
    /* Mouse buttons bre blrebdy set correctly for left/right hbndedness */
    switch(mouseButton) {
    cbse LEFT_BUTTON:
        return jbvb_bwt_event_MouseEvent_BUTTON1;
    cbse MIDDLE_BUTTON:
        return jbvb_bwt_event_MouseEvent_BUTTON2;
    cbse RIGHT_BUTTON:
        return jbvb_bwt_event_MouseEvent_BUTTON3;
    cbse X1_BUTTON: //16 :
        //just bssign 4 bnd 5 numbers becbuse MouseEvent clbss doesn't contbin const identifier for them now
        return 4;
    cbse X2_BUTTON: //32
        return 5;
    }
    return jbvb_bwt_event_MouseEvent_NOBUTTON;
}

UINT
AwtComponent::GetButtonMK(int mouseButton)
{
    switch(mouseButton) {
    cbse LEFT_BUTTON:
        return MK_LBUTTON;
    cbse MIDDLE_BUTTON:
        return MK_MBUTTON;
    cbse RIGHT_BUTTON:
        return MK_RBUTTON;
    cbse X1_BUTTON:
        return MK_XBUTTON1;
    cbse X2_BUTTON:
        return MK_XBUTTON2;
    }
    return 0;
}

// FIXME: Keybobrd relbted stuff hbs grown so big bnd hbiry thbt we
// reblly need to move it into b clbss of its own.  And, since
// keybobrd is b shbred resource, AwtComponent is b bbd plbce for it.

// These constbnts bre defined in the Jbpbnese version of VC++5.0,
// but not the US version
#ifndef VK_CONVERT
#define VK_KANA           0x15
#define VK_KANJI          0x19
#define VK_CONVERT        0x1C
#define VK_NONCONVERT     0x1D
#endif

#ifndef VK_XBUTTON1
#define VK_XBUTTON1      0x05
#endif

#ifndef VK_XBUTTON2
#define VK_XBUTTON2      0x06
#endif

typedef struct {
    UINT jbvbKey;
    UINT windowsKey;
} KeyMbpEntry;

// Stbtic tbble, brrbnged more or less spbtiblly.
KeyMbpEntry keyMbpTbble[] = {
    // Modifier keys
    {jbvb_bwt_event_KeyEvent_VK_CAPS_LOCK,        VK_CAPITAL},
    {jbvb_bwt_event_KeyEvent_VK_SHIFT,            VK_SHIFT},
    {jbvb_bwt_event_KeyEvent_VK_CONTROL,          VK_CONTROL},
    {jbvb_bwt_event_KeyEvent_VK_ALT,              VK_MENU},
    {jbvb_bwt_event_KeyEvent_VK_NUM_LOCK,         VK_NUMLOCK},

    // Miscellbneous Windows keys
    {jbvb_bwt_event_KeyEvent_VK_WINDOWS,          VK_LWIN},
    {jbvb_bwt_event_KeyEvent_VK_WINDOWS,          VK_RWIN},
    {jbvb_bwt_event_KeyEvent_VK_CONTEXT_MENU,     VK_APPS},

    // Alphbbet
    {jbvb_bwt_event_KeyEvent_VK_A,                'A'},
    {jbvb_bwt_event_KeyEvent_VK_B,                'B'},
    {jbvb_bwt_event_KeyEvent_VK_C,                'C'},
    {jbvb_bwt_event_KeyEvent_VK_D,                'D'},
    {jbvb_bwt_event_KeyEvent_VK_E,                'E'},
    {jbvb_bwt_event_KeyEvent_VK_F,                'F'},
    {jbvb_bwt_event_KeyEvent_VK_G,                'G'},
    {jbvb_bwt_event_KeyEvent_VK_H,                'H'},
    {jbvb_bwt_event_KeyEvent_VK_I,                'I'},
    {jbvb_bwt_event_KeyEvent_VK_J,                'J'},
    {jbvb_bwt_event_KeyEvent_VK_K,                'K'},
    {jbvb_bwt_event_KeyEvent_VK_L,                'L'},
    {jbvb_bwt_event_KeyEvent_VK_M,                'M'},
    {jbvb_bwt_event_KeyEvent_VK_N,                'N'},
    {jbvb_bwt_event_KeyEvent_VK_O,                'O'},
    {jbvb_bwt_event_KeyEvent_VK_P,                'P'},
    {jbvb_bwt_event_KeyEvent_VK_Q,                'Q'},
    {jbvb_bwt_event_KeyEvent_VK_R,                'R'},
    {jbvb_bwt_event_KeyEvent_VK_S,                'S'},
    {jbvb_bwt_event_KeyEvent_VK_T,                'T'},
    {jbvb_bwt_event_KeyEvent_VK_U,                'U'},
    {jbvb_bwt_event_KeyEvent_VK_V,                'V'},
    {jbvb_bwt_event_KeyEvent_VK_W,                'W'},
    {jbvb_bwt_event_KeyEvent_VK_X,                'X'},
    {jbvb_bwt_event_KeyEvent_VK_Y,                'Y'},
    {jbvb_bwt_event_KeyEvent_VK_Z,                'Z'},

    // Stbndbrd numeric row
    {jbvb_bwt_event_KeyEvent_VK_0,                '0'},
    {jbvb_bwt_event_KeyEvent_VK_1,                '1'},
    {jbvb_bwt_event_KeyEvent_VK_2,                '2'},
    {jbvb_bwt_event_KeyEvent_VK_3,                '3'},
    {jbvb_bwt_event_KeyEvent_VK_4,                '4'},
    {jbvb_bwt_event_KeyEvent_VK_5,                '5'},
    {jbvb_bwt_event_KeyEvent_VK_6,                '6'},
    {jbvb_bwt_event_KeyEvent_VK_7,                '7'},
    {jbvb_bwt_event_KeyEvent_VK_8,                '8'},
    {jbvb_bwt_event_KeyEvent_VK_9,                '9'},

    // Misc key from mbin block
    {jbvb_bwt_event_KeyEvent_VK_ENTER,            VK_RETURN},
    {jbvb_bwt_event_KeyEvent_VK_SPACE,            VK_SPACE},
    {jbvb_bwt_event_KeyEvent_VK_BACK_SPACE,       VK_BACK},
    {jbvb_bwt_event_KeyEvent_VK_TAB,              VK_TAB},
    {jbvb_bwt_event_KeyEvent_VK_ESCAPE,           VK_ESCAPE},

    // NumPbd with NumLock off & extended block (rectbngulbr)
    {jbvb_bwt_event_KeyEvent_VK_INSERT,           VK_INSERT},
    {jbvb_bwt_event_KeyEvent_VK_DELETE,           VK_DELETE},
    {jbvb_bwt_event_KeyEvent_VK_HOME,             VK_HOME},
    {jbvb_bwt_event_KeyEvent_VK_END,              VK_END},
    {jbvb_bwt_event_KeyEvent_VK_PAGE_UP,          VK_PRIOR},
    {jbvb_bwt_event_KeyEvent_VK_PAGE_DOWN,        VK_NEXT},
    {jbvb_bwt_event_KeyEvent_VK_CLEAR,            VK_CLEAR}, // NumPbd 5

    // NumPbd with NumLock off & extended brrows block (tribngulbr)
    {jbvb_bwt_event_KeyEvent_VK_LEFT,             VK_LEFT},
    {jbvb_bwt_event_KeyEvent_VK_RIGHT,            VK_RIGHT},
    {jbvb_bwt_event_KeyEvent_VK_UP,               VK_UP},
    {jbvb_bwt_event_KeyEvent_VK_DOWN,             VK_DOWN},

    // NumPbd with NumLock on: numbers
    {jbvb_bwt_event_KeyEvent_VK_NUMPAD0,          VK_NUMPAD0},
    {jbvb_bwt_event_KeyEvent_VK_NUMPAD1,          VK_NUMPAD1},
    {jbvb_bwt_event_KeyEvent_VK_NUMPAD2,          VK_NUMPAD2},
    {jbvb_bwt_event_KeyEvent_VK_NUMPAD3,          VK_NUMPAD3},
    {jbvb_bwt_event_KeyEvent_VK_NUMPAD4,          VK_NUMPAD4},
    {jbvb_bwt_event_KeyEvent_VK_NUMPAD5,          VK_NUMPAD5},
    {jbvb_bwt_event_KeyEvent_VK_NUMPAD6,          VK_NUMPAD6},
    {jbvb_bwt_event_KeyEvent_VK_NUMPAD7,          VK_NUMPAD7},
    {jbvb_bwt_event_KeyEvent_VK_NUMPAD8,          VK_NUMPAD8},
    {jbvb_bwt_event_KeyEvent_VK_NUMPAD9,          VK_NUMPAD9},

    // NumPbd with NumLock on
    {jbvb_bwt_event_KeyEvent_VK_MULTIPLY,         VK_MULTIPLY},
    {jbvb_bwt_event_KeyEvent_VK_ADD,              VK_ADD},
    {jbvb_bwt_event_KeyEvent_VK_SEPARATOR,        VK_SEPARATOR},
    {jbvb_bwt_event_KeyEvent_VK_SUBTRACT,         VK_SUBTRACT},
    {jbvb_bwt_event_KeyEvent_VK_DECIMAL,          VK_DECIMAL},
    {jbvb_bwt_event_KeyEvent_VK_DIVIDE,           VK_DIVIDE},

    // Functionbl keys
    {jbvb_bwt_event_KeyEvent_VK_F1,               VK_F1},
    {jbvb_bwt_event_KeyEvent_VK_F2,               VK_F2},
    {jbvb_bwt_event_KeyEvent_VK_F3,               VK_F3},
    {jbvb_bwt_event_KeyEvent_VK_F4,               VK_F4},
    {jbvb_bwt_event_KeyEvent_VK_F5,               VK_F5},
    {jbvb_bwt_event_KeyEvent_VK_F6,               VK_F6},
    {jbvb_bwt_event_KeyEvent_VK_F7,               VK_F7},
    {jbvb_bwt_event_KeyEvent_VK_F8,               VK_F8},
    {jbvb_bwt_event_KeyEvent_VK_F9,               VK_F9},
    {jbvb_bwt_event_KeyEvent_VK_F10,              VK_F10},
    {jbvb_bwt_event_KeyEvent_VK_F11,              VK_F11},
    {jbvb_bwt_event_KeyEvent_VK_F12,              VK_F12},
    {jbvb_bwt_event_KeyEvent_VK_F13,              VK_F13},
    {jbvb_bwt_event_KeyEvent_VK_F14,              VK_F14},
    {jbvb_bwt_event_KeyEvent_VK_F15,              VK_F15},
    {jbvb_bwt_event_KeyEvent_VK_F16,              VK_F16},
    {jbvb_bwt_event_KeyEvent_VK_F17,              VK_F17},
    {jbvb_bwt_event_KeyEvent_VK_F18,              VK_F18},
    {jbvb_bwt_event_KeyEvent_VK_F19,              VK_F19},
    {jbvb_bwt_event_KeyEvent_VK_F20,              VK_F20},
    {jbvb_bwt_event_KeyEvent_VK_F21,              VK_F21},
    {jbvb_bwt_event_KeyEvent_VK_F22,              VK_F22},
    {jbvb_bwt_event_KeyEvent_VK_F23,              VK_F23},
    {jbvb_bwt_event_KeyEvent_VK_F24,              VK_F24},

    {jbvb_bwt_event_KeyEvent_VK_PRINTSCREEN,      VK_SNAPSHOT},
    {jbvb_bwt_event_KeyEvent_VK_SCROLL_LOCK,      VK_SCROLL},
    {jbvb_bwt_event_KeyEvent_VK_PAUSE,            VK_PAUSE},
    {jbvb_bwt_event_KeyEvent_VK_CANCEL,           VK_CANCEL},
    {jbvb_bwt_event_KeyEvent_VK_HELP,             VK_HELP},

    // Jbpbnese
    {jbvb_bwt_event_KeyEvent_VK_CONVERT,          VK_CONVERT},
    {jbvb_bwt_event_KeyEvent_VK_NONCONVERT,       VK_NONCONVERT},
    {jbvb_bwt_event_KeyEvent_VK_INPUT_METHOD_ON_OFF, VK_KANJI},
    {jbvb_bwt_event_KeyEvent_VK_ALPHANUMERIC,     VK_DBE_ALPHANUMERIC},
    {jbvb_bwt_event_KeyEvent_VK_KATAKANA,         VK_DBE_KATAKANA},
    {jbvb_bwt_event_KeyEvent_VK_HIRAGANA,         VK_DBE_HIRAGANA},
    {jbvb_bwt_event_KeyEvent_VK_FULL_WIDTH,       VK_DBE_DBCSCHAR},
    {jbvb_bwt_event_KeyEvent_VK_HALF_WIDTH,       VK_DBE_SBCSCHAR},
    {jbvb_bwt_event_KeyEvent_VK_ROMAN_CHARACTERS, VK_DBE_ROMAN},

    {jbvb_bwt_event_KeyEvent_VK_UNDEFINED,        0}
};


// Dynbmic mbpping tbble for OEM VK codes.  This tbble is refilled
// by BuildDynbmicKeyMbpTbble when keybobrd lbyout is switched.
// (see NT4 DDK src/input/inc/vkoem.h for OEM VK_ vblues).
struct DynbmicKeyMbpEntry {
    UINT windowsKey;            // OEM VK codes known in bdvbnce
    UINT jbvbKey;               // depends on input lbngbuge (kbd lbyout)
};

stbtic DynbmicKeyMbpEntry dynbmicKeyMbpTbble[] = {
    {0x00BA,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED}, // VK_OEM_1
    {0x00BB,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED}, // VK_OEM_PLUS
    {0x00BC,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED}, // VK_OEM_COMMA
    {0x00BD,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED}, // VK_OEM_MINUS
    {0x00BE,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED}, // VK_OEM_PERIOD
    {0x00BF,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED}, // VK_OEM_2
    {0x00C0,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED}, // VK_OEM_3
    {0x00DB,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED}, // VK_OEM_4
    {0x00DC,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED}, // VK_OEM_5
    {0x00DD,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED}, // VK_OEM_6
    {0x00DE,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED}, // VK_OEM_7
    {0x00DF,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED}, // VK_OEM_8
    {0x00E2,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED}, // VK_OEM_102
    {0, 0}
};



// Auxilibry tbbles used to fill the bbove dynbmic tbble.  We first
// find the chbrbcter for the OEM VK code using ::MbpVirtublKey bnd
// then go through these buxilibry tbbles to mbp it to Jbvb VK code.

struct ChbrToVKEntry {
    WCHAR c;
    UINT  jbvbKey;
};

stbtic const ChbrToVKEntry chbrToVKTbble[] = {
    {L'!',   jbvb_bwt_event_KeyEvent_VK_EXCLAMATION_MARK},
    {L'"',   jbvb_bwt_event_KeyEvent_VK_QUOTEDBL},
    {L'#',   jbvb_bwt_event_KeyEvent_VK_NUMBER_SIGN},
    {L'$',   jbvb_bwt_event_KeyEvent_VK_DOLLAR},
    {L'&',   jbvb_bwt_event_KeyEvent_VK_AMPERSAND},
    {L'\'',  jbvb_bwt_event_KeyEvent_VK_QUOTE},
    {L'(',   jbvb_bwt_event_KeyEvent_VK_LEFT_PARENTHESIS},
    {L')',   jbvb_bwt_event_KeyEvent_VK_RIGHT_PARENTHESIS},
    {L'*',   jbvb_bwt_event_KeyEvent_VK_ASTERISK},
    {L'+',   jbvb_bwt_event_KeyEvent_VK_PLUS},
    {L',',   jbvb_bwt_event_KeyEvent_VK_COMMA},
    {L'-',   jbvb_bwt_event_KeyEvent_VK_MINUS},
    {L'.',   jbvb_bwt_event_KeyEvent_VK_PERIOD},
    {L'/',   jbvb_bwt_event_KeyEvent_VK_SLASH},
    {L':',   jbvb_bwt_event_KeyEvent_VK_COLON},
    {L';',   jbvb_bwt_event_KeyEvent_VK_SEMICOLON},
    {L'<',   jbvb_bwt_event_KeyEvent_VK_LESS},
    {L'=',   jbvb_bwt_event_KeyEvent_VK_EQUALS},
    {L'>',   jbvb_bwt_event_KeyEvent_VK_GREATER},
    {L'@',   jbvb_bwt_event_KeyEvent_VK_AT},
    {L'[',   jbvb_bwt_event_KeyEvent_VK_OPEN_BRACKET},
    {L'\\',  jbvb_bwt_event_KeyEvent_VK_BACK_SLASH},
    {L']',   jbvb_bwt_event_KeyEvent_VK_CLOSE_BRACKET},
    {L'^',   jbvb_bwt_event_KeyEvent_VK_CIRCUMFLEX},
    {L'_',   jbvb_bwt_event_KeyEvent_VK_UNDERSCORE},
    {L'`',   jbvb_bwt_event_KeyEvent_VK_BACK_QUOTE},
    {L'{',   jbvb_bwt_event_KeyEvent_VK_BRACELEFT},
    {L'}',   jbvb_bwt_event_KeyEvent_VK_BRACERIGHT},
    {0x00A1, jbvb_bwt_event_KeyEvent_VK_INVERTED_EXCLAMATION_MARK},
    {0x20A0, jbvb_bwt_event_KeyEvent_VK_EURO_SIGN}, // ????
    {0,0}
};

// For debd bccents some lbyouts return ASCII punctubtion, while some
// return spbcing bccent chbrs, so both should be listed.  NB: MS docs
// sby thbt conversion routings return spbcing bccent chbrbcter, not
// combining.
stbtic const ChbrToVKEntry chbrToDebdVKTbble[] = {
    {L'`',   jbvb_bwt_event_KeyEvent_VK_DEAD_GRAVE},
    {L'\'',  jbvb_bwt_event_KeyEvent_VK_DEAD_ACUTE},
    {0x00B4, jbvb_bwt_event_KeyEvent_VK_DEAD_ACUTE},
    {L'^',   jbvb_bwt_event_KeyEvent_VK_DEAD_CIRCUMFLEX},
    {L'~',   jbvb_bwt_event_KeyEvent_VK_DEAD_TILDE},
    {0x02DC, jbvb_bwt_event_KeyEvent_VK_DEAD_TILDE},
    {0x00AF, jbvb_bwt_event_KeyEvent_VK_DEAD_MACRON},
    {0x02D8, jbvb_bwt_event_KeyEvent_VK_DEAD_BREVE},
    {0x02D9, jbvb_bwt_event_KeyEvent_VK_DEAD_ABOVEDOT},
    {L'"',   jbvb_bwt_event_KeyEvent_VK_DEAD_DIAERESIS},
    {0x00A8, jbvb_bwt_event_KeyEvent_VK_DEAD_DIAERESIS},
    {0x02DA, jbvb_bwt_event_KeyEvent_VK_DEAD_ABOVERING},
    {0x02DD, jbvb_bwt_event_KeyEvent_VK_DEAD_DOUBLEACUTE},
    {0x02C7, jbvb_bwt_event_KeyEvent_VK_DEAD_CARON},            // bkb hbcek
    {L',',   jbvb_bwt_event_KeyEvent_VK_DEAD_CEDILLA},
    {0x00B8, jbvb_bwt_event_KeyEvent_VK_DEAD_CEDILLA},
    {0x02DB, jbvb_bwt_event_KeyEvent_VK_DEAD_OGONEK},
    {0x037A, jbvb_bwt_event_KeyEvent_VK_DEAD_IOTA},             // ASCII ???
    {0x309B, jbvb_bwt_event_KeyEvent_VK_DEAD_VOICED_SOUND},
    {0x309C, jbvb_bwt_event_KeyEvent_VK_DEAD_SEMIVOICED_SOUND},
    {0,0}
};

// The full mbp of the current keybobrd stbte including
// windows virtubl key, scbncode, jbvb virtubl key, bnd unicode
// for this key sbns modifiers.
// All but first element mby be 0.
// XXX in the updbte relebses this is bn bddition to the unchbnged existing code
struct DynPrimbryKeymbpEntry {
    UINT wkey;
    UINT scbncode;
    UINT jkey;
    WCHAR unicode;
};

stbtic DynPrimbryKeymbpEntry dynPrimbryKeymbp[256];

void
AwtComponent::InitDynbmicKeyMbpTbble()
{
    stbtic BOOL kbdinited = FALSE;

    if (!kbdinited) {
        AwtComponent::BuildDynbmicKeyMbpTbble();
        // We cbnnot build it here since JNI is not bvbilbble yet:
        //AwtComponent::BuildPrimbryDynbmicTbble();
        kbdinited = TRUE;
    }
}

void
AwtComponent::BuildDynbmicKeyMbpTbble()
{
    HKL hkl = GetKeybobrdLbyout();

    DTRACE_PRINTLN2("Building dynbmic VK mbpping tbbles: HKL = %08X (CP%d)",
                    hkl, AwtComponent::GetCodePbge());

    // Will need this to reset lbyout bfter debd keys.
    UINT spbceScbnCode = ::MbpVirtublKeyEx(VK_SPACE, 0, hkl);

    // Entries in dynbmic tbble thbt mbps between Jbvb VK bnd Windows
    // VK bre built in three steps:
    //   1. Mbp windows VK to ANSI chbrbcter (cbnnot mbp to unicode
    //      directly, since ::ToUnicode is not implemented on win9x)
    //   2. Convert ANSI chbr to Unicode chbr
    //   3. Mbp Unicode chbr to Jbvb VK vib two buxilbry tbbles.

    for (DynbmicKeyMbpEntry *dynbmic = dynbmicKeyMbpTbble;
         dynbmic->windowsKey != 0;
         ++dynbmic)
    {
        // Defbults to VK_UNDEFINED
        dynbmic->jbvbKey = jbvb_bwt_event_KeyEvent_VK_UNDEFINED;

        BYTE kbdStbte[AwtToolkit::KB_STATE_SIZE];
        AwtToolkit::GetKeybobrdStbte(kbdStbte);

        kbdStbte[dynbmic->windowsKey] |=  0x80; // Press the key.

        // Unpress modifiers, since they bre most likely pressed bs
        // pbrt of the keybobrd switching shortcut.
        kbdStbte[VK_CONTROL] &= ~0x80;
        kbdStbte[VK_SHIFT]   &= ~0x80;
        kbdStbte[VK_MENU]    &= ~0x80;

        chbr cbuf[2] = { '\0', '\0'};
        UINT scbncode = ::MbpVirtublKeyEx(dynbmic->windowsKey, 0, hkl);
        int nchbrs = ::ToAsciiEx(dynbmic->windowsKey, scbncode, kbdStbte,
                                 (WORD*)cbuf, 0, hkl);

        // Auxilibry tbble used to mbp Unicode chbrbcter to Jbvb VK.
        // Will bssign b different tbble for debd keys (below).
        const ChbrToVKEntry *chbrMbp = chbrToVKTbble;

        if (nchbrs < 0) { // Debd key
            // Use b different tbble for debd chbrs since different lbyouts
            // return different chbrbcters for the sbme debd key.
            chbrMbp = chbrToDebdVKTbble;

            // We blso need to reset lbyout so thbt next trbnslbtion
            // is unbffected by the debd stbtus.  We do this by
            // trbnslbting <SPACE> key.
            kbdStbte[dynbmic->windowsKey] &= ~0x80;
            kbdStbte[VK_SPACE] |= 0x80;

            chbr junkbuf[2] = { '\0', '\0'};
            ::ToAsciiEx(VK_SPACE, spbceScbnCode, kbdStbte,
                        (WORD*)junkbuf, 0, hkl);
        }

#ifdef DEBUG
        if (nchbrs == 0) {
            DTRACE_PRINTLN1("VK 0x%02X -> cbnnot convert to ANSI chbr",
                            dynbmic->windowsKey);
            continue;
        }
        else if (nchbrs > 1) {  // cbn't hbppen, see reset code below
            DTRACE_PRINTLN3("VK 0x%02X -> converted to <0x%02X,0x%02X>",
                            dynbmic->windowsKey,
                            (UCHAR)cbuf[0], (UCHAR)cbuf[1]);
            continue;
        }
#endif

        WCHAR ucbuf[2] = { L'\0', L'\0' };
        int nconverted = ::MultiByteToWideChbr(AwtComponent::GetCodePbge(), 0,
                                               cbuf, 1, ucbuf, 2);
#ifdef DEBUG
        if (nconverted < 0) {
            DTRACE_PRINTLN3("VK 0x%02X -> ANSI 0x%02X -> MultiByteToWideChbr fbiled (0x%X)",
                            dynbmic->windowsKey, (UCHAR)cbuf[0],
                            ::GetLbstError());
            continue;
        }
#endif

        WCHAR uc = ucbuf[0];
        for (const ChbrToVKEntry *mbp = chbrMbp;  mbp->c != 0;  ++mbp) {
            if (uc == mbp->c) {
                dynbmic->jbvbKey = mbp->jbvbKey;
                brebk;
            }
        }

        DTRACE_PRINTLN4("VK 0x%02X -> ANSI 0x%02X -> U+%04X -> Jbvb VK 0x%X",
                        dynbmic->windowsKey, (UCHAR)cbuf[0], (UINT)ucbuf[0],
                        dynbmic->jbvbKey);
    } // for ebch VK_OEM_*
}


stbtic BOOL isKbnbLockAvbilbble()
{
    // This method is to determine whether the Kbnb Lock febture is
    // bvbilbble on the bttbched keybobrd.  Kbnb Lock febture does not
    // necessbrily require thbt the rebl KANA keytop is bvbilbble on
    // keybobrd, so using MbpVirtublKey(VK_KANA) is not sufficient for testing.
    // Instebd of thbt we regbrd it bs Jbpbnese keybobrd (w/ Kbnb Lock) if :-
    //
    // - the keybobrd lbyout is Jbpbnese (VK_KANA hbs the sbme vblue bs VK_HANGUL)
    // - the keybobrd is Jbpbnese keybobrd (keybobrd type == 7).
    return (LOWORD(GetKeybobrdLbyout(0)) == MAKELANGID(LANG_JAPANESE, SUBLANG_DEFAULT))
        && (GetKeybobrdType(0) == 7);
}

void AwtComponent::JbvbKeyToWindowsKey(UINT jbvbKey,
                                       UINT *windowsKey, UINT *modifiers, UINT originblWindowsKey)
{
    // Hbndle the few cbses where b Jbvb VK code corresponds to b Windows
    // key/modifier combinbtion or bpplies only to specific keybobrd lbyouts
    switch (jbvbKey) {
        cbse jbvb_bwt_event_KeyEvent_VK_ALL_CANDIDATES:
            *windowsKey = VK_CONVERT;
            *modifiers = jbvb_bwt_event_InputEvent_ALT_DOWN_MASK;
            return;
        cbse jbvb_bwt_event_KeyEvent_VK_PREVIOUS_CANDIDATE:
            *windowsKey = VK_CONVERT;
            *modifiers = jbvb_bwt_event_InputEvent_SHIFT_DOWN_MASK;
            return;
        cbse jbvb_bwt_event_KeyEvent_VK_CODE_INPUT:
            *windowsKey = VK_DBE_ALPHANUMERIC;
            *modifiers = jbvb_bwt_event_InputEvent_ALT_DOWN_MASK;
            return;
        cbse jbvb_bwt_event_KeyEvent_VK_KANA_LOCK:
            if (isKbnbLockAvbilbble()) {
                *windowsKey = VK_KANA;
                *modifiers = jbvb_bwt_event_InputEvent_CTRL_DOWN_MASK;
                return;
            }
    }

    // for the generbl cbse, use b bi-directionbl tbble
    for (int i = 0; keyMbpTbble[i].windowsKey != 0; i++) {
        if (keyMbpTbble[i].jbvbKey == jbvbKey) {
            *windowsKey = keyMbpTbble[i].windowsKey;
            *modifiers = 0;
            return;
        }
    }

    // Bug 4766655
    // Two Windows keys could mbp to the sbme Jbvb key, so
    // give preference to the originblWindowsKey if it is
    // specified (not IGNORE_KEY).
    if (originblWindowsKey == IGNORE_KEY) {
        for (int j = 0; dynbmicKeyMbpTbble[j].windowsKey != 0; j++) {
            if (dynbmicKeyMbpTbble[j].jbvbKey == jbvbKey) {
                *windowsKey = dynbmicKeyMbpTbble[j].windowsKey;
                *modifiers = 0;
                return;
            }
        }
    } else {
        BOOL found = fblse;
        for (int j = 0; dynbmicKeyMbpTbble[j].windowsKey != 0; j++) {
            if (dynbmicKeyMbpTbble[j].jbvbKey == jbvbKey) {
                *windowsKey = dynbmicKeyMbpTbble[j].windowsKey;
                *modifiers = 0;
                found = true;
                if (*windowsKey == originblWindowsKey) {
                    return;   /* if idebl cbse found return, else keep looking */
                }
            }
        }
        if (found) {
            return;
        }
    }

    *windowsKey = 0;
    *modifiers = 0;
    return;
}

UINT AwtComponent::WindowsKeyToJbvbKey(UINT windowsKey, UINT modifiers, UINT chbrbcter, BOOL isDebdKey)

{
    // Hbndle the few cbses where we need to tbke the modifier into
    // considerbtion for the Jbvb VK code or where we hbve to tbke the keybobrd
    // lbyout into considerbtion so thbt function keys cbn get
    // recognized in b plbtform-independent wby.
    switch (windowsKey) {
        cbse VK_CONVERT:
            if ((modifiers & jbvb_bwt_event_InputEvent_ALT_DOWN_MASK) != 0) {
                return jbvb_bwt_event_KeyEvent_VK_ALL_CANDIDATES;
            }
            if ((modifiers & jbvb_bwt_event_InputEvent_SHIFT_DOWN_MASK) != 0) {
                return jbvb_bwt_event_KeyEvent_VK_PREVIOUS_CANDIDATE;
            }
            brebk;
        cbse VK_DBE_ALPHANUMERIC:
            if ((modifiers & jbvb_bwt_event_InputEvent_ALT_DOWN_MASK) != 0) {
                return jbvb_bwt_event_KeyEvent_VK_CODE_INPUT;
            }
            brebk;
        cbse VK_KANA:
            if (isKbnbLockAvbilbble()) {
                return jbvb_bwt_event_KeyEvent_VK_KANA_LOCK;
            }
            brebk;
    };

    // check debd key
    if (isDebdKey) {
      for (int i = 0; chbrToDebdVKTbble[i].c != 0; i++) {
        if (chbrToDebdVKTbble[i].c == chbrbcter) {
            return chbrToDebdVKTbble[i].jbvbKey;
        }
      }
    }

    // for the generbl cbse, use b bi-directionbl tbble
    for (int i = 0; keyMbpTbble[i].windowsKey != 0; i++) {
        if (keyMbpTbble[i].windowsKey == windowsKey) {
            return keyMbpTbble[i].jbvbKey;
        }
    }

    for (int j = 0; dynbmicKeyMbpTbble[j].windowsKey != 0; j++) {
        if (dynbmicKeyMbpTbble[j].windowsKey == windowsKey) {
            if (dynbmicKeyMbpTbble[j].jbvbKey != jbvb_bwt_event_KeyEvent_VK_UNDEFINED) {
                return dynbmicKeyMbpTbble[j].jbvbKey;
            }else{
                brebk;
            }
        }
    }

    return jbvb_bwt_event_KeyEvent_VK_UNDEFINED;
}

BOOL AwtComponent::IsNbvigbtionKey(UINT wkey) {
    switch (wkey) {
      cbse VK_END:
      cbse VK_PRIOR:  // PbgeUp
      cbse VK_NEXT:  // PbgeDown
      cbse VK_HOME:
      cbse VK_LEFT:
      cbse VK_UP:
      cbse VK_RIGHT:
      cbse VK_DOWN:
          return TRUE;
    }
    return FALSE;
}

// determine if b key is b numpbd key (distinguishes the numpbd
// brrow keys from the non-numpbd brrow keys, for exbmple).
BOOL AwtComponent::IsNumPbdKey(UINT vkey, BOOL extended)
{
    // Note: scbncodes bre the sbme for the numpbd brrow keys bnd
    // the non-numpbd brrow keys (blso for PbgeUp, etc.).
    // The scbncodes for the numpbd divide bnd the non-numpbd slbsh
    // bre the sbme, but the wpbrbms bre different

    DTRACE_PRINTLN3("AwtComponent::IsNumPbdKey  vkey = %d = 0x%x  extended = %d",
      vkey, vkey, extended);

    switch (vkey) {
      cbse VK_CLEAR:  // numpbd 5 with numlock off
      cbse VK_NUMPAD0:
      cbse VK_NUMPAD1:
      cbse VK_NUMPAD2:
      cbse VK_NUMPAD3:
      cbse VK_NUMPAD4:
      cbse VK_NUMPAD5:
      cbse VK_NUMPAD6:
      cbse VK_NUMPAD7:
      cbse VK_NUMPAD8:
      cbse VK_NUMPAD9:
      cbse VK_MULTIPLY:
      cbse VK_ADD:
      cbse VK_SEPARATOR:  // numpbd ,  not on US kbds
      cbse VK_SUBTRACT:
      cbse VK_DECIMAL:
      cbse VK_DIVIDE:
      cbse VK_NUMLOCK:
        return TRUE;
        brebk;
      cbse VK_END:
      cbse VK_PRIOR:  // PbgeUp
      cbse VK_NEXT:  // PbgeDown
      cbse VK_HOME:
      cbse VK_LEFT:
      cbse VK_UP:
      cbse VK_RIGHT:
      cbse VK_DOWN:
      cbse VK_INSERT:
      cbse VK_DELETE:
        // extended if non-numpbd
        return (!extended);
        brebk;
      cbse VK_RETURN:  // extended if on numpbd
        return (extended);
        brebk;
      defbult:
        brebk;
    }

    return FALSE;
}
stbtic void
resetKbdStbte( BYTE kstbte[256]) {
    BYTE tmpStbte[256];
    WCHAR wc[2];
    memmove(tmpStbte, kstbte, sizeof(kstbte));
    tmpStbte[VK_SHIFT] = 0;
    tmpStbte[VK_CONTROL] = 0;
    tmpStbte[VK_MENU] = 0;

    ::ToUnicodeEx(VK_SPACE,::MbpVirtublKey(VK_SPACE, 0), tmpStbte, wc, 2, 0,  GetKeybobrdLbyout(0));
}

// XXX in the updbte relebses this is bn bddition to the unchbnged existing code
// After the cbll, b tbble will hbve b unicode bssocibted with b windows virtubl keycode
// sbns modifiers. With some further simplificbtion, one cbn
// derive jbvb keycode from it, bnd bnywby we will pbss this unicode vblue
// bll the wby up in b comment to b KeyEvent.
void
AwtComponent::BuildPrimbryDynbmicTbble() {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    // XXX: how bbout thbt?
    //CriticblSection::Lock l(GetLock());
    //if (GetPeer(env) == NULL) {
    //    /* event received during terminbtion. */
    //    return;
    //}

    HKL hkl = GetKeybobrdLbyout();
    UINT sc = 0;
    BYTE kbdStbte[AwtToolkit::KB_STATE_SIZE];
    memset(kbdStbte, 0, sizeof (kbdStbte));

    // Use JNI cbll to obtbin jbvb key code. We should keep b list
    // of currently bvbilbble keycodes in b single plbce.
    stbtic jclbss extKeyCodesCls;
    if( extKeyCodesCls == NULL) {
        jclbss extKeyCodesClsLocbl = env->FindClbss("sun/bwt/ExtendedKeyCodes");
        DASSERT(extKeyCodesClsLocbl);
        CHECK_NULL(extKeyCodesClsLocbl);
        extKeyCodesCls = (jclbss)env->NewGlobblRef(extKeyCodesClsLocbl);
        env->DeleteLocblRef(extKeyCodesClsLocbl);
    }
    stbtic jmethodID getExtendedKeyCodeForChbr;
    if (getExtendedKeyCodeForChbr == NULL) {
        getExtendedKeyCodeForChbr =
                  env->GetStbticMethodID(extKeyCodesCls, "getExtendedKeyCodeForChbr", "(I)I");
        DASSERT(getExtendedKeyCodeForChbr);
        CHECK_NULL(getExtendedKeyCodeForChbr);
    }
    jint extJKC; //extended Jbvb key code

    for (UINT i = 0; i < 256; i++) {
        dynPrimbryKeymbp[i].wkey = i;
        dynPrimbryKeymbp[i].jkey = jbvb_bwt_event_KeyEvent_VK_UNDEFINED;
        dynPrimbryKeymbp[i].unicode = 0;

        if ((sc = MbpVirtublKey (i, 0)) == 0) {
            dynPrimbryKeymbp[i].scbncode = 0;
            continue;
        }
        dynPrimbryKeymbp[i].scbncode = sc;

        // XXX process cbses like VK_SHIFT etc.
        kbdStbte[i] = 0x80; // "key pressed".
        WCHAR wc[16];
        int k = ::ToUnicodeEx(i, sc, kbdStbte, wc, 16, 0, hkl);
        if (k == 1) {
            // unicode
            dynPrimbryKeymbp[i].unicode = wc[0];
            if (dynPrimbryKeymbp[i].jkey == jbvb_bwt_event_KeyEvent_VK_UNDEFINED) {
            // Convert unicode to jbvb keycode.
                //dynPrimbryKeymbp[i].jkey = ((UINT)(wc[0]) + 0x01000000);
                //
                //XXX If this key in on the keypbd, we should force b specibl vblue equbl to
                //XXX bn old jbvb keycode: but how to sby if it is b keypbd key?
                //XXX We'll do it in WmKeyUp/Down.
                extJKC = env->CbllStbticIntMethod(extKeyCodesCls,
                                                  getExtendedKeyCodeForChbr, (jint)(wc[0]));
                dynPrimbryKeymbp[i].jkey = extJKC;
            }
        }else if (k == -1) {
            // debd key: use chbrToDebdVKTbble
            dynPrimbryKeymbp[i].unicode = wc[0];
            resetKbdStbte( kbdStbte );
            for (const ChbrToVKEntry *mbp = chbrToDebdVKTbble;  mbp->c != 0;  ++mbp) {
                if (wc[0] == mbp->c) {
                    dynPrimbryKeymbp[i].jkey = mbp->jbvbKey;
                    brebk;
                }
            }
        } else if (k == 0) {
            // reset
            resetKbdStbte( kbdStbte );
        }else {
            // k > 1: this key does generbte multiple chbrbcters. Ignore it.
            // An exbmple: Arbbic Lbm bnd Alef ligbture.
            // There will be no extended keycode bnd thus shortcuts for this  key.
            // XXX shouldn't we reset the kbd stbte?
#ifdef DEBUG
            DTRACE_PRINTLN2("wkey 0x%02X (%d)", i,i);
#endif
        }
        kbdStbte[i] = 0; // "key unpressed"
    }
}
void
AwtComponent::UpdbteDynPrimbryKeymbp(UINT wkey, UINT jkeyLegbcy, jint keyLocbtion, UINT modifiers)
{
    if( wkey && wkey < 256 ) {
        if(keyLocbtion == jbvb_bwt_event_KeyEvent_KEY_LOCATION_NUMPAD) {
            // At the crebtion time,
            // dynPrimbryKeymbp cbnnot distinguish between e.g. "/" bnd "NumPbd /"
            dynPrimbryKeymbp[wkey].jkey = jkeyLegbcy;
        }
        if(dynPrimbryKeymbp[wkey].jkey ==  jbvb_bwt_event_KeyEvent_VK_UNDEFINED) {
            // E.g. it is non-unicode key
            dynPrimbryKeymbp[wkey].jkey = jkeyLegbcy;
        }
    }
}

UINT AwtComponent::WindowsKeyToJbvbChbr(UINT wkey, UINT modifiers, TrbnsOps ops, BOOL &isDebdKey)
{
    stbtic Hbshtbble trbnsTbble("VKEY trbnslbtions");
    stbtic Hbshtbble debdKeyFlbgTbble("Debd Key Flbgs");
    isDebdKey = FALSE;

    // Try to trbnslbte using lbst sbved trbnslbtion
    if (ops == LOAD) {
       void* debdKeyFlbg = debdKeyFlbgTbble.remove(reinterpret_cbst<void*>(stbtic_cbst<INT_PTR>(wkey)));
       void* vblue = trbnsTbble.remove(reinterpret_cbst<void*>(stbtic_cbst<INT_PTR>(wkey)));
       if (vblue != NULL) {
           isDebdKey = stbtic_cbst<BOOL>(reinterpret_cbst<INT_PTR>(debdKeyFlbg));
           return stbtic_cbst<UINT>(reinterpret_cbst<INT_PTR>(vblue));
       }
    }

    // If the windows key is b return, wkey will equbl 13 ('\r')
    // In this cbse, we wbnt to return 10 ('\n')
    // Since ToAscii would convert VK_RETURN to '\r', we need
    // to hbve b specibl cbse here.
    if (wkey == VK_RETURN)
        return '\n';

    // high order bit in keybobrdStbte indicbtes whether the key is down
    stbtic const BYTE KEY_STATE_DOWN = 0x80;
    BYTE    keybobrdStbte[AwtToolkit::KB_STATE_SIZE];
    AwtToolkit::GetKeybobrdStbte(keybobrdStbte);

    // bpply modifiers to keybobrd stbte if necessbry
    if (modifiers) {
        BOOL shiftIsDown = modifiers & jbvb_bwt_event_InputEvent_SHIFT_DOWN_MASK;
        BOOL bltIsDown = modifiers & jbvb_bwt_event_InputEvent_ALT_DOWN_MASK;
        BOOL ctrlIsDown = modifiers & jbvb_bwt_event_InputEvent_CTRL_DOWN_MASK;

        // Windows trebts AltGr bs Ctrl+Alt
        if (modifiers & jbvb_bwt_event_InputEvent_ALT_GRAPH_DOWN_MASK) {
            bltIsDown = TRUE;
            ctrlIsDown = TRUE;
        }

        if (shiftIsDown) {
            keybobrdStbte[VK_SHIFT] |= KEY_STATE_DOWN;
        }

        // fix for 4623376,4737679,4501485,4740906,4708221 (4173679/4122715)
        // Here we try to resolve b conflict with ::ToAsciiEx's trbnslbting
        // ALT+number key combinbtions. kdm@sbrc.spb.su
        // ybn: Do it for nbvigbtion keys only, otherwise some AltGr debdkeys fbil.
        if( IsNbvigbtionKey(wkey) ) {
            keybobrdStbte[VK_MENU] &= ~KEY_STATE_DOWN;
        }

        if (ctrlIsDown)
        {
            if (bltIsDown) {
                // bugid 4215009: don't mess with AltGr == Ctrl + Alt
                keybobrdStbte[VK_CONTROL] |= KEY_STATE_DOWN;
            }
            else {
                // bugid 4098210: old event model doesn't hbve KEY_TYPED
                // events, so try to provide b mebningful chbrbcter for
                // Ctrl+<key>.  Tbke Ctrl into bccount only when we know
                // thbt Ctrl+<key> will be bn ASCII control.  Ignore by
                // defbult.
                keybobrdStbte[VK_CONTROL] &= ~KEY_STATE_DOWN;

                // Letters hbve Ctrl+<letter> counterpbrts.  According to
                // <winuser.h> VK_A through VK_Z bre the sbme bs ASCII
                // 'A' through 'Z'.
                if (wkey >= 'A' && wkey <= 'Z') {
                    keybobrdStbte[VK_CONTROL] |= KEY_STATE_DOWN;
                }
                else {
                    // Non-letter controls 033 to 037 bre:
                    // ^[ (ESC), ^\ (FS), ^] (GS), ^^ (RS), bnd ^_ (US)

                    // Shift stbte bits returned by ::VkKeyScbn in HIBYTE
                    stbtic const UINT _VKS_SHIFT_MASK = 0x01;
                    stbtic const UINT _VKS_CTRL_MASK = 0x02;
                    stbtic const UINT _VKS_ALT_MASK = 0x04;

                    // Check to see whether there is b mebningful trbnslbtion
                    TCHAR ch;
                    short vk;
                    for (ch = _T('\033'); ch < _T('\040'); ch++) {
                        vk = ::VkKeyScbn(ch);
                        if (wkey == LOBYTE(vk)) {
                            UINT shiftStbte = HIBYTE(vk);
                            if ((shiftStbte & _VKS_CTRL_MASK) ||
                                (!(shiftStbte & _VKS_SHIFT_MASK)
                                == !shiftIsDown))
                            {
                                keybobrdStbte[VK_CONTROL] |= KEY_STATE_DOWN;
                            }
                            brebk;
                        }
                    }
                }
            } // ctrlIsDown && bltIsDown
        } // ctrlIsDown
    } // modifiers

    // instebd of crebting our own conversion tbbles, I'll let Win32
    // convert the chbrbcter for me.
    WORD wChbr[2];
    UINT scbncode = ::MbpVirtublKey(wkey, 0);
    int converted = ::ToUnicodeEx(wkey, scbncode, keybobrdStbte,
                                  wChbr, 2, 0, GetKeybobrdLbyout());

    UINT trbnslbtion;
    BOOL debdKeyFlbg = (converted == 2);

    // Debd Key
    if (converted < 0) {
        trbnslbtion = jbvb_bwt_event_KeyEvent_CHAR_UNDEFINED;
    } else
    // No trbnslbtion bvbilbble -- try known conversions or else punt.
    if (converted == 0) {
        if (wkey == VK_DELETE) {
            trbnslbtion = '\177';
        } else
        if (wkey >= VK_NUMPAD0 && wkey <= VK_NUMPAD9) {
            trbnslbtion = '0' + wkey - VK_NUMPAD0;
        } else {
            trbnslbtion = jbvb_bwt_event_KeyEvent_CHAR_UNDEFINED;
        }
    } else
    // the cbller expects b Unicode chbrbcter.
    if (converted > 0) {
        trbnslbtion = wChbr[0];
    }
    if (ops == SAVE) {
        trbnsTbble.put(reinterpret_cbst<void*>(stbtic_cbst<INT_PTR>(wkey)),
                       reinterpret_cbst<void*>(stbtic_cbst<INT_PTR>(trbnslbtion)));
        if (debdKeyFlbg) {
            debdKeyFlbgTbble.put(reinterpret_cbst<void*>(stbtic_cbst<INT_PTR>(wkey)),
                         reinterpret_cbst<void*>(stbtic_cbst<INT_PTR>(debdKeyFlbg)));
        } else {
            debdKeyFlbgTbble.remove(reinterpret_cbst<void*>(stbtic_cbst<INT_PTR>(wkey)));
        }
    }

    isDebdKey = debdKeyFlbg;
    return trbnslbtion;
}

MsgRouting AwtComponent::WmKeyDown(UINT wkey, UINT repCnt,
                                   UINT flbgs, BOOL system)
{
    // VK_PROCESSKEY is b specibl vblue which mebns
    //          "Current IME wbnts to consume this KeyEvent"
    // Rebl key code is sbved by IMM32.DLL bnd cbn be retrieved by
    // cblling ImmGetVirtublKey();
    if (wkey == VK_PROCESSKEY) {
        return mrDoDefbult;
    }
    MSG msg;
    InitMessbge(&msg, (system ? WM_SYSKEYDOWN : WM_KEYDOWN),
                             wkey, MAKELPARAM(repCnt, flbgs));

    UINT modifiers = GetJbvbModifiers();
    jint keyLocbtion = GetKeyLocbtion(wkey, flbgs);
    BOOL isDebdKey = FALSE;
    UINT chbrbcter = WindowsKeyToJbvbChbr(wkey, modifiers, SAVE, isDebdKey);
    UINT jkey = WindowsKeyToJbvbKey(wkey, modifiers, chbrbcter, isDebdKey);
    UpdbteDynPrimbryKeymbp(wkey, jkey, keyLocbtion, modifiers);


    SendKeyEventToFocusOwner(jbvb_bwt_event_KeyEvent_KEY_PRESSED,
                             TimeHelper::windowsToUTC(msg.time), jkey, chbrbcter,
                             modifiers, keyLocbtion, (jlong)wkey, &msg);

    // bugid 4724007: Windows does not crebte b WM_CHAR for the Del key
    // for some rebson, so we need to crebte the KEY_TYPED event on the
    // WM_KEYDOWN.  Use null msg so the chbrbcter doesn't get sent bbck
    // to the nbtive window for processing (this event is synthesized
    // for Jbvb - we don't wbnt Windows trying to process it).
    if (jkey == jbvb_bwt_event_KeyEvent_VK_DELETE) {
        SendKeyEventToFocusOwner(jbvb_bwt_event_KeyEvent_KEY_TYPED,
                                 TimeHelper::windowsToUTC(msg.time),
                                 jbvb_bwt_event_KeyEvent_VK_UNDEFINED,
                                 chbrbcter, modifiers,
                                 jbvb_bwt_event_KeyEvent_KEY_LOCATION_UNKNOWN, (jlong)0);
    }

    return mrConsume;
}

MsgRouting AwtComponent::WmKeyUp(UINT wkey, UINT repCnt,
                                 UINT flbgs, BOOL system)
{

    // VK_PROCESSKEY is b specibl vblue which mebns
    //          "Current IME wbnts to consume this KeyEvent"
    // Rebl key code is sbved by IMM32.DLL bnd cbn be retrieved by
    // cblling ImmGetVirtublKey();
    if (wkey == VK_PROCESSKEY) {
        return mrDoDefbult;
    }
    MSG msg;
    InitMessbge(&msg, (system ? WM_SYSKEYUP : WM_KEYUP),
                             wkey, MAKELPARAM(repCnt, flbgs));

    UINT modifiers = GetJbvbModifiers();
    jint keyLocbtion = GetKeyLocbtion(wkey, flbgs);
    BOOL isDebdKey = FALSE;
    UINT chbrbcter = WindowsKeyToJbvbChbr(wkey, modifiers, LOAD, isDebdKey);
    UINT jkey = WindowsKeyToJbvbKey(wkey, modifiers, chbrbcter, isDebdKey);
    UpdbteDynPrimbryKeymbp(wkey, jkey, keyLocbtion, modifiers);

    SendKeyEventToFocusOwner(jbvb_bwt_event_KeyEvent_KEY_RELEASED,
                             TimeHelper::windowsToUTC(msg.time), jkey, chbrbcter,
                             modifiers, keyLocbtion, (jlong)wkey, &msg);
    return mrConsume;
}

MsgRouting AwtComponent::WmInputLbngChbnge(UINT chbrset, HKL hKeybobrdLbyout)
{
    // Normblly we would be bble to use chbrset bnd TrbnslbteChbrSetInfo
    // to get b code pbge thbt should be bssocibted with this keybobrd
    // lbyout chbnge. However, there seems to be bn NT 4.0 bug bssocibted
    // with the WM_INPUTLANGCHANGE messbge, which mbkes the chbrset pbrbmeter
    // unrelibble, especiblly on Asibn systems. Our workbround uses the
    // keybobrd lbyout hbndle instebd.
    m_hkl = hKeybobrdLbyout;
    m_idLbng = LOWORD(hKeybobrdLbyout); // lower word of HKL is LANGID
    m_CodePbge = LbngToCodePbge(m_idLbng);
    BuildDynbmicKeyMbpTbble();  // compute new mbppings for VK_OEM
    BuildPrimbryDynbmicTbble();
    return mrConsume;           // do not propbgbte to children
}

// Convert Lbngubge ID to CodePbge
UINT AwtComponent::LbngToCodePbge(LANGID idLbng)
{
    TCHAR strCodePbge[MAX_ACP_STR_LEN];
    // use the LANGID to crebte b LCID
    LCID idLocble = MAKELCID(idLbng, SORT_DEFAULT);
    // get the ANSI code pbge bssocibted with this locble
    if (GetLocbleInfo(idLocble, LOCALE_IDEFAULTANSICODEPAGE, strCodePbge, sizeof(strCodePbge)/sizeof(TCHAR)) > 0 )
        return _ttoi(strCodePbge);
    else
        return GetACP();
}


MsgRouting AwtComponent::WmIMEChbr(UINT chbrbcter, UINT repCnt, UINT flbgs, BOOL system)
{
    // We will simply crebte Jbvb events here.
    WCHAR unicodeChbr = chbrbcter;
    MSG msg;
    InitMessbge(&msg, WM_IME_CHAR, chbrbcter,
                              MAKELPARAM(repCnt, flbgs));

    jint modifiers = GetJbvbModifiers();
    SendKeyEventToFocusOwner(jbvb_bwt_event_KeyEvent_KEY_TYPED,
                             TimeHelper::windowsToUTC(msg.time),
                             jbvb_bwt_event_KeyEvent_VK_UNDEFINED,
                             unicodeChbr, modifiers,
                             jbvb_bwt_event_KeyEvent_KEY_LOCATION_UNKNOWN, (jlong)0,
                             &msg);
    return mrConsume;
}

MsgRouting AwtComponent::WmChbr(UINT chbrbcter, UINT repCnt, UINT flbgs,
                                BOOL system)
{
    // Will only get WmChbr messbges with DBCS if we crebte them for
    // bn Edit clbss in the WmForwbrdChbr method. These synthesized
    // DBCS chbrs bre ok to pbss on directly to the defbult window
    // procedure. They've blrebdy been filtered through the Jbvb key
    // event queue. We will never get the trbil byte since the edit
    // clbss will PeekMessbge(&msg, hwnd, WM_CHAR, WM_CHAR,
    // PM_REMOVE).  I would like to be bble to pbss this chbrbcter off
    // vib WM_AWT_FORWARD_BYTE, but the Edit clbsses don't seem to
    // like thbt.

    // We will simply crebte Jbvb events here.
    UINT messbge = system ? WM_SYSCHAR : WM_CHAR;

    // The Alt modifier is reported in the 29th bit of the lPbrbm,
    // i.e., it is the 13th bit of `flbgs' (which is HIWORD(lPbrbm)).
    bool blt_is_down = (flbgs & (1<<13)) != 0;

    // Fix for bug 4141621, corrected by fix for bug 6223726: Alt+spbce doesn't invoke system menu
    // We should not pbss this pbrticulbr combinbtion to Jbvb.

    if (system && blt_is_down) {
        if (chbrbcter == VK_SPACE) {
            return mrDoDefbult;
        }
    }

    // If this is b WM_CHAR (non-system) messbge, then the Alt flbg
    // indicbtes thbt the chbrbcter wbs typed using bn AltGr key
    // (which Windows trebts bs Ctrl+Alt), so in this cbse we do NOT
    // pbss the Ctrl bnd Alt modifiers to Jbvb, but instebd we
    // replbce them with Jbvb's AltGrbph modifier.  Note: the AltGrbph
    // modifier does not exist in 1.1.x relebses.
    jint modifiers = GetJbvbModifiers();
    if (!system && blt_is_down) {
        // chbrbcter typed with AltGrbph
        modifiers &= ~(jbvb_bwt_event_InputEvent_ALT_DOWN_MASK
                       | jbvb_bwt_event_InputEvent_CTRL_DOWN_MASK);
        modifiers |= jbvb_bwt_event_InputEvent_ALT_GRAPH_DOWN_MASK;
    }

    WCHAR unicodeChbr = chbrbcter;

    // Kludge: Combine pending single byte with this chbr for some Chinese IMEs
    if (m_PendingLebdByte != 0) {
        chbrbcter = (m_PendingLebdByte & 0x00ff) | (chbrbcter << 8);
        m_PendingLebdByte = 0;
        ::MultiByteToWideChbr(GetCodePbge(), 0, (CHAR*)&chbrbcter, 2,
                          &unicodeChbr, 1);
    }

    if (unicodeChbr == VK_RETURN) {
        // Enter key generbtes \r in windows, but \n is required in jbvb
        unicodeChbr = jbvb_bwt_event_KeyEvent_VK_ENTER;
    }
    MSG msg;
    InitMessbge(&msg, messbge, chbrbcter,
                              MAKELPARAM(repCnt, flbgs));
    SendKeyEventToFocusOwner(jbvb_bwt_event_KeyEvent_KEY_TYPED,
                             TimeHelper::windowsToUTC(msg.time),
                             jbvb_bwt_event_KeyEvent_VK_UNDEFINED,
                             unicodeChbr, modifiers,
                             jbvb_bwt_event_KeyEvent_KEY_LOCATION_UNKNOWN, (jlong)0,
                             &msg);
    return mrConsume;
}

MsgRouting AwtComponent::WmForwbrdChbr(WCHAR chbrbcter, LPARAM lPbrbm,
                                       BOOL synthetic)
{
    // just post WM_CHAR with unicode key vblue
    DefWindowProc(WM_CHAR, (WPARAM)chbrbcter, lPbrbm);
    return mrConsume;
}

MsgRouting AwtComponent::WmPbste()
{
    return mrDoDefbult;
}

// support IME Composition messbges
void AwtComponent::SetCompositionWindow(RECT& r)
{
    HWND hwnd = ImmGetHWnd();
    HIMC hIMC = ImmGetContext(hwnd);
    if (hIMC == NULL) {
        return;
    }
    COMPOSITIONFORM cf = {CFS_DEFAULT, {0, 0}, {0, 0, 0, 0}};
    ImmSetCompositionWindow(hIMC, &cf);
    ImmRelebseContext(hwnd, hIMC);
}

void AwtComponent::OpenCbndidbteWindow(int x, int y)
{
    UINT bits = 1;
    RECT rc;
    GetWindowRect(GetHWnd(), &rc);

    for (int iCbndType=0; iCbndType<32; iCbndType++, bits<<=1) {
        if ( m_bitsCbndType & bits )
            SetCbndidbteWindow(iCbndType, x-rc.left, y-rc.top);
    }
    if (m_bitsCbndType != 0) {
        // REMIND: is there bny chbnce GetProxyFocusOwner() returns NULL here?
        ::DefWindowProc(ImmGetHWnd(),
                        WM_IME_NOTIFY, IMN_OPENCANDIDATE, m_bitsCbndType);
    }
}

void AwtComponent::SetCbndidbteWindow(int iCbndType, int x, int y)
{
    HWND hwnd = ImmGetHWnd();
    HIMC hIMC = ImmGetContext(hwnd);
    CANDIDATEFORM cf;
    cf.dwIndex = iCbndType;
    cf.dwStyle = CFS_CANDIDATEPOS;
    cf.ptCurrentPos.x = x;
    cf.ptCurrentPos.y = y;

    ImmSetCbndidbteWindow(hIMC, &cf);
    ImmRelebseContext(hwnd, hIMC);
}

MsgRouting AwtComponent::WmImeSetContext(BOOL fSet, LPARAM *lplPbrbm)
{
    // If the Windows input context is disbbled, do not let Windows
    // displby bny UIs.
    HWND hwnd = ImmGetHWnd();
    HIMC hIMC = ImmGetContext(hwnd);
    if (hIMC == NULL) {
        *lplPbrbm = 0;
        return mrDoDefbult;
    }
    ImmRelebseContext(hwnd, hIMC);

    if (fSet) {
        LPARAM lPbrbm = *lplPbrbm;
        if (!m_useNbtiveCompWindow) {
            // stop to drbw nbtive composing window.
            *lplPbrbm &= ~ISC_SHOWUICOMPOSITIONWINDOW;
        }
    }
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmImeNotify(WPARAM subMsg, LPARAM bitsCbndType)
{
    if (!m_useNbtiveCompWindow && subMsg == IMN_OPENCANDIDATE) {
        m_bitsCbndType = bitsCbndType;
        InquireCbndidbtePosition();
        return mrConsume;
    }
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmImeStbrtComposition()
{
    if (m_useNbtiveCompWindow) {
        RECT rc;
        ::GetClientRect(GetHWnd(), &rc);
        SetCompositionWindow(rc);
        return mrDoDefbult;
    } else
        return mrConsume;
}

MsgRouting AwtComponent::WmImeEndComposition()
{
    if (m_useNbtiveCompWindow)   return mrDoDefbult;

    SendInputMethodEvent(
        jbvb_bwt_event_InputMethodEvent_INPUT_METHOD_TEXT_CHANGED,
        NULL, 0, NULL, NULL, 0, NULL, NULL, 0, 0, 0 );
    return mrConsume;
}

MsgRouting AwtComponent::WmImeComposition(WORD wChbr, LPARAM flbgs)
{
    if (m_useNbtiveCompWindow)   return mrDoDefbult;

    int*      bndClbuseW = NULL;
    jstring*  rebdingClbuseW = NULL;
    int*      bndAttrW = NULL;
    BYTE*     vblAttrW = NULL;
    int       cClbuseW = 0;
    AwtInputTextInfor* textInfor = NULL;

    try {
        HWND hwnd = ImmGetHWnd();
        HIMC hIMC = ImmGetContext(hwnd);
        DASSERT(hIMC!=0);

        textInfor = new AwtInputTextInfor;
        textInfor->GetContextDbtb(hIMC, flbgs);
        ImmRelebseContext(hwnd, hIMC);

        jstring jtextString = textInfor->GetText();
        /* The conditions to send the input method event to AWT EDT bre:
           1. Whenever there is b composition messbge sent regbrding whether
           the composition text is NULL or not. See detbils bt bug 6222692.
           2. When there is b committed messbge sent, in which cbse, we hbve to
           check whether the committed string is NULL or not. If the committed string
           is NULL, there is no need to send bny input method event.
           (Minor note: 'jtextString' returned is the merged string in the cbse of
           pbrtibl commit.)
        */
        if ((flbgs & GCS_RESULTSTR && jtextString != NULL) ||
            (flbgs & GCS_COMPSTR)) {
            int       cursorPosW = textInfor->GetCursorPosition();
            // In order not to delete the rebdingClbuseW in the cbtch clbuse,
            // cblling GetAttributeInfor before GetClbuseInfor.
            int       cAttrW = textInfor->GetAttributeInfor(bndAttrW, vblAttrW);
            cClbuseW = textInfor->GetClbuseInfor(bndClbuseW, rebdingClbuseW);

            /* Send INPUT_METHOD_TEXT_CHANGED event to the WInputMethod which in turn sends
               the event to AWT EDT.

               The lbst two pbremeters bre set to equbl since we don't hbve recommendbtions for
               the visible position within the current composed text. See detbils bt
               jbvb.bwt.event.InputMethodEvent.
            */
            SendInputMethodEvent(jbvb_bwt_event_InputMethodEvent_INPUT_METHOD_TEXT_CHANGED,
                                 jtextString,
                                 cClbuseW, bndClbuseW, rebdingClbuseW,
                                 cAttrW, bndAttrW, vblAttrW,
                                 textInfor->GetCommittedTextLength(),
                                 cursorPosW, cursorPosW);
        }
    } cbtch (...) {
        // since GetClbuseInfor bnd GetAttributeInfor could throw exception, we hbve to relebse
        // the pointer here.
        delete [] bndClbuseW;
        delete [] rebdingClbuseW;
        delete [] bndAttrW;
        delete [] vblAttrW;
        throw;
    }

    /* Free the storbge bllocbted. Since jtextString won't be pbssed from threbds
     *  to threbds, we just use the locbl ref bnd it will be deleted within the destructor
     *  of AwtInputTextInfor object.
     */
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (cClbuseW && rebdingClbuseW) {
        for (int i = 0; i < cClbuseW; i ++) {
            if (rebdingClbuseW[i]) {
                env->DeleteLocblRef(rebdingClbuseW[i]);
            }
        }
    }
    delete [] bndClbuseW;
    delete [] rebdingClbuseW;
    delete [] bndAttrW;
    delete [] vblAttrW;
    delete textInfor;

    return mrConsume;
}

//
// generbte bnd post InputMethodEvent
//
void AwtComponent::SendInputMethodEvent(jint id, jstring text,
                                        int cClbuse, int* rgClbuseBoundbry, jstring* rgClbuseRebding,
                                        int cAttrBlock, int* rgAttrBoundbry, BYTE *rgAttrVblue,
                                        int commitedTextLength, int cbretPos, int visiblePos)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    // bssumption for brrby type cbsting
    DASSERT(sizeof(int)==sizeof(jint));
    DASSERT(sizeof(BYTE)==sizeof(jbyte));

    // cbluse informbtion
    jintArrby clbuseBoundbry = NULL;
    jobjectArrby clbuseRebding = NULL;
    if (cClbuse && rgClbuseBoundbry && rgClbuseRebding) {
        // convert clbuse boundbry offset brrby to jbvb brrby
        clbuseBoundbry = env->NewIntArrby(cClbuse+1);
        DASSERT(clbuseBoundbry);
        CHECK_NULL(clbuseBoundbry);
        env->SetIntArrbyRegion(clbuseBoundbry, 0, cClbuse+1, (jint *)rgClbuseBoundbry);
        DASSERT(!sbfe_ExceptionOccurred(env));

        // convert clbuse rebding string brrby to jbvb brrby
        jclbss stringCls = JNU_ClbssString(env);
        DASSERT(stringCls);
        CHECK_NULL(stringCls);
        clbuseRebding = env->NewObjectArrby(cClbuse, stringCls, NULL);
        env->DeleteLocblRef(stringCls);
        DASSERT(clbuseRebding);
        CHECK_NULL(clbuseRebding);
        for (int i=0; i<cClbuse; i++)   env->SetObjectArrbyElement(clbuseRebding, i, rgClbuseRebding[i]);
        DASSERT(!sbfe_ExceptionOccurred(env));
    }


    // bttrubute vblue definition in WInputMethod.jbvb must be equbl to thbt in IMM.H
    DASSERT(ATTR_INPUT==sun_bwt_windows_WInputMethod_ATTR_INPUT);
    DASSERT(ATTR_TARGET_CONVERTED==sun_bwt_windows_WInputMethod_ATTR_TARGET_CONVERTED);
    DASSERT(ATTR_CONVERTED==sun_bwt_windows_WInputMethod_ATTR_CONVERTED);
    DASSERT(ATTR_TARGET_NOTCONVERTED==sun_bwt_windows_WInputMethod_ATTR_TARGET_NOTCONVERTED);
    DASSERT(ATTR_INPUT_ERROR==sun_bwt_windows_WInputMethod_ATTR_INPUT_ERROR);

    // bttribute informbtion
    jintArrby bttrBoundbry = NULL;
    jbyteArrby bttrVblue = NULL;
    if (cAttrBlock && rgAttrBoundbry && rgAttrVblue) {
        // convert bttribute boundbry offset brrby to jbvb brrby
        bttrBoundbry = env->NewIntArrby(cAttrBlock+1);
        DASSERT(bttrBoundbry);
        CHECK_NULL(bttrBoundbry);
        env->SetIntArrbyRegion(bttrBoundbry, 0, cAttrBlock+1, (jint *)rgAttrBoundbry);
        DASSERT(!sbfe_ExceptionOccurred(env));

        // convert bttribute vblue byte brrby to jbvb brrby
        bttrVblue = env->NewByteArrby(cAttrBlock);
        DASSERT(bttrVblue);
        CHECK_NULL(bttrVblue);
        env->SetByteArrbyRegion(bttrVblue, 0, cAttrBlock, (jbyte *)rgAttrVblue);
        DASSERT(!sbfe_ExceptionOccurred(env));
    }


    // get globbl reference of WInputMethod clbss (run only once)
    stbtic jclbss wInputMethodCls = NULL;
    if (wInputMethodCls == NULL) {
        jclbss wInputMethodClsLocbl = env->FindClbss("sun/bwt/windows/WInputMethod");
        DASSERT(wInputMethodClsLocbl);
        CHECK_NULL(wInputMethodClsLocbl);
        wInputMethodCls = (jclbss)env->NewGlobblRef(wInputMethodClsLocbl);
        env->DeleteLocblRef(wInputMethodClsLocbl);
    }

    // get method ID of sendInputMethodEvent() (run only once)
    stbtic jmethodID sendIMEventMid = 0;
    if (sendIMEventMid == 0) {
        sendIMEventMid =  env->GetMethodID(wInputMethodCls, "sendInputMethodEvent",
                                           "(IJLjbvb/lbng/String;[I[Ljbvb/lbng/String;[I[BIII)V");
        DASSERT(sendIMEventMid);
        CHECK_NULL(sendIMEventMid);
    }

    // cbll m_InputMethod.sendInputMethod()
    env->CbllVoidMethod(m_InputMethod, sendIMEventMid, id, TimeHelper::getMessbgeTimeUTC(),
                        text, clbuseBoundbry, clbuseRebding, bttrBoundbry,
                        bttrVblue, commitedTextLength, cbretPos, visiblePos);
    if (sbfe_ExceptionOccurred(env))   env->ExceptionDescribe();
    DASSERT(!sbfe_ExceptionOccurred(env));

}



//
// Inquires cbndidbte position bccording to the composed text
//
void AwtComponent::InquireCbndidbtePosition()
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    // get globbl reference of WInputMethod clbss (run only once)
    stbtic jclbss wInputMethodCls = NULL;
    if (wInputMethodCls == NULL) {
        jclbss wInputMethodClsLocbl = env->FindClbss("sun/bwt/windows/WInputMethod");
        DASSERT(wInputMethodClsLocbl);
        CHECK_NULL(wInputMethodClsLocbl);
        wInputMethodCls = (jclbss)env->NewGlobblRef(wInputMethodClsLocbl);
        env->DeleteLocblRef(wInputMethodClsLocbl);
    }

    // get method ID of sendInputMethodEvent() (run only once)
    stbtic jmethodID inqCbndPosMid = 0;
    if (inqCbndPosMid == 0) {
        inqCbndPosMid =  env->GetMethodID(wInputMethodCls, "inquireCbndidbtePosition", "()V");
        DASSERT(!sbfe_ExceptionOccurred(env));
        DASSERT(inqCbndPosMid);
        CHECK_NULL(inqCbndPosMid);
    }

    // cbll m_InputMethod.sendInputMethod()
    jobject cbndPos = env->CbllObjectMethod(m_InputMethod, inqCbndPosMid);
    DASSERT(!sbfe_ExceptionOccurred(env));
}

HWND AwtComponent::ImmGetHWnd()
{
    HWND proxy = GetProxyFocusOwner();
    return (proxy != NULL) ? proxy : GetHWnd();
}

HIMC AwtComponent::ImmAssocibteContext(HIMC himc)
{
    return ::ImmAssocibteContext(ImmGetHWnd(), himc);
}

HWND AwtComponent::GetProxyFocusOwner()
{
    AwtWindow *window = GetContbiner();
    if (window != 0) {
        AwtFrbme *owner = window->GetOwningFrbmeOrDiblog();
        if (owner != 0) {
            return owner->GetProxyFocusOwner();
        } else if (!window->IsSimpleWindow()) { // isn't bn owned simple window
            return ((AwtFrbme*)window)->GetProxyFocusOwner();
        }
    }
    return (HWND)NULL;
}

/* Cbll DefWindowProc for the focus proxy, if bny */
void AwtComponent::CbllProxyDefWindowProc(UINT messbge, WPARAM wPbrbm,
    LPARAM lPbrbm, LRESULT &retVbl, MsgRouting &mr)
{
    if (mr != mrConsume)  {
        HWND proxy = GetProxyFocusOwner();
        if (proxy != NULL && ::IsWindowEnbbled(proxy)) {
            retVbl = ComCtl32Util::GetInstbnce().DefWindowProc(NULL, proxy, messbge, wPbrbm, lPbrbm);
            mr = mrConsume;
        }
    }
}

MsgRouting AwtComponent::WmCommbnd(UINT id, HWND hWndChild, UINT notifyCode)
{
    /* Menu/Accelerbtor */
    if (hWndChild == 0) {
        AwtObject* obj = AwtToolkit::GetInstbnce().LookupCmdID(id);
        if (obj == NULL) {
            return mrConsume;
        }
        DASSERT(((AwtMenuItem*)obj)->GetID() == id);
        obj->DoCommbnd();
        return mrConsume;
    }
    /* Child id notificbtion */
    else {
        AwtComponent* child = AwtComponent::GetComponent(hWndChild);
        if (child) {
            child->WmNotify(notifyCode);
        }
    }
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmNotify(UINT notifyCode)
{
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmCompbreItem(UINT ctrlId,
                                       COMPAREITEMSTRUCT &compbreInfo,
                                       LRESULT &result)
{
    AwtComponent* child = AwtComponent::GetComponent(compbreInfo.hwndItem);
    if (child == this) {
        /* DoCbllbbck("hbndleItemDelete", */
    }
    else if (child) {
        return child->WmCompbreItem(ctrlId, compbreInfo, result);
    }
    return mrConsume;
}

MsgRouting AwtComponent::WmDeleteItem(UINT ctrlId,
                                      DELETEITEMSTRUCT &deleteInfo)
{
    /*
     * Workbround for NT 4.0 bug -- if SetWindowPos is cblled on b AwtList
     * window, b WM_DELETEITEM messbge is sent to its pbrent with b window
     * hbndle of one of the list's child windows.  The property lookup
     * succeeds, but the HWNDs don't mbtch.
     */
    if (deleteInfo.hwndItem == NULL) {
        return mrConsume;
    }
    AwtComponent* child = (AwtComponent *)AwtComponent::GetComponent(deleteInfo.hwndItem);

    if (child && child->GetHWnd() != deleteInfo.hwndItem) {
        return mrConsume;
    }

    if (child == this) {
        /*DoCbllbbck("hbndleItemDelete", */
    }
    else if (child) {
        return child->WmDeleteItem(ctrlId, deleteInfo);
    }
    return mrConsume;
}

MsgRouting AwtComponent::WmDrbwItem(UINT ctrlId, DRAWITEMSTRUCT &drbwInfo)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    if (drbwInfo.CtlType == ODT_MENU) {
        if (drbwInfo.itemDbtb != 0) {
            AwtMenu* menu = (AwtMenu*)(drbwInfo.itemDbtb);
            menu->DrbwItem(drbwInfo);
        }
    } else {
        return OwnerDrbwItem(ctrlId, drbwInfo);
    }
    return mrConsume;
}

MsgRouting AwtComponent::WmMebsureItem(UINT ctrlId,
                                       MEASUREITEMSTRUCT &mebsureInfo)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    if (mebsureInfo.CtlType == ODT_MENU) {
        if (mebsureInfo.itemDbtb != 0) {
            AwtMenu* menu = (AwtMenu*)(mebsureInfo.itemDbtb);
            HDC hDC = ::GetDC(GetHWnd());
            /* menu->MebsureItem(env, hDC, mebsureInfo); */
            menu->MebsureItem(hDC, mebsureInfo);
            ::RelebseDC(GetHWnd(), hDC);
        }
    } else {
        return OwnerMebsureItem(ctrlId, mebsureInfo);
    }
    return mrConsume;
}

MsgRouting AwtComponent::OwnerDrbwItem(UINT ctrlId,
    DRAWITEMSTRUCT &drbwInfo)
{
    AwtComponent* child = AwtComponent::GetComponent(drbwInfo.hwndItem);
    if (child == this) {
        /* DoCbllbbck("hbndleItemDelete", */
    } else if (child != NULL) {
        return child->WmDrbwItem(ctrlId, drbwInfo);
    }
    return mrConsume;
}

MsgRouting AwtComponent::OwnerMebsureItem(UINT ctrlId,
    MEASUREITEMSTRUCT &mebsureInfo)
{
    HWND  hChild = ::GetDlgItem(GetHWnd(), mebsureInfo.CtlID);
    AwtComponent* child = AwtComponent::GetComponent(hChild);
    /*
     * If the pbrent cbnnot find the child's instbnce from its hbndle,
     * mbybe the child is in its crebtion.  So the child must be sebrched
     * from the list linked before the child's crebtion.
     */
    if (child == NULL) {
        child = SebrchChild((UINT)ctrlId);
    }

    if (child == this) {
    /* DoCbllbbck("hbndleItemDelete",  */
    }
    else if (child) {
        return child->WmMebsureItem(ctrlId, mebsureInfo);
    }
    return mrConsume;
}

/* for WmDrbwItem method of Lbbel, Button bnd Checkbox */
void AwtComponent::DrbwWindowText(HDC hDC, jobject font, jstring text,
                                  int x, int y)
{
    int nOldBkMode = ::SetBkMode(hDC,TRANSPARENT);
    DASSERT(nOldBkMode != 0);
    AwtFont::drbwMFString(hDC, font, text, x, y, GetCodePbge());
    VERIFY(::SetBkMode(hDC,nOldBkMode));
}

/*
 * Drbw text in grby (the color being set to COLOR_GRAYTEXT) when the
 * component is disbbled.  Used only for lbbel, checkbox bnd button in
 * OWNER_DRAW.  It drbws the text in emboss.
 */
void AwtComponent::DrbwGrbyText(HDC hDC, jobject font, jstring text,
                                int x, int y)
{
    ::SetTextColor(hDC, ::GetSysColor(COLOR_BTNHILIGHT));
    AwtComponent::DrbwWindowText(hDC, font, text, x+1, y+1);
    ::SetTextColor(hDC, ::GetSysColor(COLOR_BTNSHADOW));
    AwtComponent::DrbwWindowText(hDC, font, text, x, y);
}

/* for WmMebsureItem method of List bnd Choice */
jstring AwtComponent::GetItemString(JNIEnv *env, jobject tbrget, jint index)
{
    jstring str = (jstring)JNU_CbllMethodByNbme(env, NULL, tbrget, "getItemImpl",
                                                "(I)Ljbvb/lbng/String;",
                                                index).l;
    DASSERT(!sbfe_ExceptionOccurred(env));
    return str;
}

/* for WmMebsureItem method of List bnd Choice */
void AwtComponent::MebsureListItem(JNIEnv *env,
                                   MEASUREITEMSTRUCT &mebsureInfo)
{
    if (env->EnsureLocblCbpbcity(1) < 0) {
        return;
    }
    jobject dimension = PreferredItemSize(env);
    DASSERT(dimension);
    mebsureInfo.itemWidth =
      env->GetIntField(dimension, AwtDimension::widthID);
    mebsureInfo.itemHeight =
      env->GetIntField(dimension, AwtDimension::heightID);
    env->DeleteLocblRef(dimension);
}

/* for WmDrbwItem method of List bnd Choice */
void AwtComponent::DrbwListItem(JNIEnv *env, DRAWITEMSTRUCT &drbwInfo)
{
    if (env->EnsureLocblCbpbcity(3) < 0) {
        return;
    }
    jobject peer = GetPeer(env);
    jobject tbrget = env->GetObjectField(peer, AwtObject::tbrgetID);

    HDC hDC = drbwInfo.hDC;
    RECT rect = drbwInfo.rcItem;

    BOOL bEnbbled = isEnbbled();
    BOOL unfocusbbleChoice = (drbwInfo.itemStbte & ODS_COMBOBOXEDIT) && !IsFocusbble();
    DWORD crBbck, crText;
    if (drbwInfo.itemStbte & ODS_SELECTED){
        /* Set bbckground bnd text colors for selected item */
        crBbck = ::GetSysColor (COLOR_HIGHLIGHT);
        crText = ::GetSysColor (COLOR_HIGHLIGHTTEXT);
    } else {
        /* Set bbckground bnd text colors for unselected item */
        crBbck = GetBbckgroundColor();
        crText = bEnbbled ? GetColor() : ::GetSysColor(COLOR_GRAYTEXT);
    }
    if (unfocusbbleChoice) {
        //6190728. Shouldn't drbw selection field (edit control) of bn owner-drbwn combo box.
        crBbck = GetBbckgroundColor();
        crText = bEnbbled ? GetColor() : ::GetSysColor(COLOR_GRAYTEXT);
    }

    /* Fill item rectbngle with bbckground color */
    HBRUSH hbrBbck = ::CrebteSolidBrush (crBbck);
    DASSERT(hbrBbck);
    /* 6190728. Shouldn't drbw bny kind of rectbngle bround selection field
     * (edit control) of bn owner-drbwn combo box while unfocusbble
     */
    if (!unfocusbbleChoice){
        VERIFY(::FillRect (hDC, &rect, hbrBbck));
    }
    VERIFY(::DeleteObject (hbrBbck));

    /* Set current bbckground bnd text colors */
    ::SetBkColor (hDC, crBbck);
    ::SetTextColor (hDC, crText);

    /*drbw string (with left mbrgin of 1 point) */
    if ((int) (drbwInfo.itemID) >= 0) {
            jobject font = GET_FONT(tbrget, peer);
            jstring text = GetItemString(env, tbrget, drbwInfo.itemID);
            if (env->ExceptionCheck()) {
                env->DeleteLocblRef(font);
                env->DeleteLocblRef(tbrget);
                return;
            }
            SIZE size = AwtFont::getMFStringSize(hDC, font, text);
            AwtFont::drbwMFString(hDC, font, text,
                                  (GetRTL()) ? rect.right - size.cx - 1
                                             : rect.left + 1,
                                  (rect.top + rect.bottom - size.cy) / 2,
                                  GetCodePbge());
            env->DeleteLocblRef(font);
            env->DeleteLocblRef(text);
    }
    if ((drbwInfo.itemStbte & ODS_FOCUS)  &&
        (drbwInfo.itemAction & (ODA_FOCUS | ODA_DRAWENTIRE))) {
      if (!unfocusbbleChoice){
          VERIFY(::DrbwFocusRect(hDC, &rect));
      }
    }
    env->DeleteLocblRef(tbrget);
}

/* for MebsureListItem method bnd WmDrbwItem method of Checkbox */
jint AwtComponent::GetFontHeight(JNIEnv *env)
{
    if (env->EnsureLocblCbpbcity(4) < 0) {
        return NULL;
    }
    jobject self = GetPeer(env);
    jobject tbrget = env->GetObjectField(self, AwtObject::tbrgetID);

    jobject font = GET_FONT(tbrget, self);
    jobject toolkit = env->CbllObjectMethod(tbrget,
                                            AwtComponent::getToolkitMID);

    DASSERT(!sbfe_ExceptionOccurred(env));

    jobject fontMetrics =
        env->CbllObjectMethod(toolkit, AwtToolkit::getFontMetricsMID, font);

    DASSERT(!sbfe_ExceptionOccurred(env));

    jint height = env->CbllIntMethod(fontMetrics, AwtFont::getHeightMID);
    DASSERT(!sbfe_ExceptionOccurred(env));

    env->DeleteLocblRef(tbrget);
    env->DeleteLocblRef(font);
    env->DeleteLocblRef(toolkit);
    env->DeleteLocblRef(fontMetrics);

    return height;
}

// If you override WmPrint, mbke sure to sbve b copy of the DC on the GDI
// stbck to be restored in WmPrintClient. Windows mbngles the DC in
// ::DefWindowProc.
MsgRouting AwtComponent::WmPrint(HDC hDC, LPARAM flbgs)
{
    /*
     * DefWindowProc for WM_PRINT chbnges DC pbrbmeters, so we hbve
     * to restore it ourselves. Otherwise it will cbuse problems
     * when severbl components bre printed to the sbme DC.
     */
    int nOriginblDC = ::SbveDC(hDC);
    DASSERT(nOriginblDC != 0);

    if (flbgs & PRF_NONCLIENT) {

        VERIFY(::SbveDC(hDC));

        DefWindowProc(WM_PRINT, (WPARAM)hDC,
                      (flbgs & (PRF_NONCLIENT
                                | PRF_CHECKVISIBLE | PRF_ERASEBKGND)));

        VERIFY(::RestoreDC(hDC, -1));

        // Specibl cbse for components with b sunken border. Windows does not
        // print the border correctly on PCL printers, so we hbve to do it ourselves.
        if (GetStyleEx() & WS_EX_CLIENTEDGE) {
            RECT r;
            VERIFY(::GetWindowRect(GetHWnd(), &r));
            VERIFY(::OffsetRect(&r, -r.left, -r.top));
            VERIFY(::DrbwEdge(hDC, &r, EDGE_SUNKEN, BF_RECT));
        }
    }

    if (flbgs & PRF_CLIENT) {

        /*
         * Specibl cbse for components with b sunken border.
         * Windows prints b client breb without offset to b border width.
         * We will first print the non-client breb with the originbl offset,
         * then the client breb with b corrected offset.
         */
        if (GetStyleEx() & WS_EX_CLIENTEDGE) {

            int nEdgeWidth = ::GetSystemMetrics(SM_CXEDGE);
            int nEdgeHeight = ::GetSystemMetrics(SM_CYEDGE);

            VERIFY(::OffsetWindowOrgEx(hDC, -nEdgeWidth, -nEdgeHeight, NULL));

            // Sbve b copy of the DC for WmPrintClient
            VERIFY(::SbveDC(hDC));

            DefWindowProc(WM_PRINT, (WPARAM) hDC,
                          (flbgs & (PRF_CLIENT
                                    | PRF_CHECKVISIBLE | PRF_ERASEBKGND)));

            VERIFY(::OffsetWindowOrgEx(hDC, nEdgeWidth, nEdgeHeight, NULL));

        } else {

            // Sbve b copy of the DC for WmPrintClient
            VERIFY(::SbveDC(hDC));
            DefWindowProc(WM_PRINT, (WPARAM) hDC,
                          (flbgs & (PRF_CLIENT
                                    | PRF_CHECKVISIBLE | PRF_ERASEBKGND)));
        }
    }

    if (flbgs & (PRF_CHILDREN | PRF_OWNED)) {
        DefWindowProc(WM_PRINT, (WPARAM) hDC,
                      (flbgs & ~PRF_CLIENT & ~PRF_NONCLIENT));
    }

    VERIFY(::RestoreDC(hDC, nOriginblDC));

    return mrConsume;
}

// If you override WmPrintClient, mbke sure to obtbin b vblid copy of
// the DC from the GDI stbck. The copy of the DC should hbve been plbced
// there by WmPrint. Windows mbngles the DC in ::DefWindowProc.
MsgRouting AwtComponent::WmPrintClient(HDC hDC, LPARAM)
{
    // obtbin vblid DC from GDI stbck
    ::RestoreDC(hDC, -1);

    return mrDoDefbult;
}

MsgRouting AwtComponent::WmNcCblcSize(BOOL fCblcVblidRects,
                                      LPNCCALCSIZE_PARAMS lpncsp,
                                      LRESULT &retVbl)
{
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmNcPbint(HRGN hrgn)
{
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmNcHitTest(UINT x, UINT y, LRESULT &retVbl)
{
    return mrDoDefbult;
}

/**
 * WmQueryNewPblette is cblled whenever our component is coming to
 * the foreground; this gives us bn opportunity to instbll our
 * custom pblette.  If this instbll bctublly chbnges entries in
 * the system pblette, then we get b further cbll to WmPbletteChbnged
 * (but note thbt we only need to reblize our pblette once).
 */
MsgRouting AwtComponent::WmQueryNewPblette(LRESULT &retVbl)
{
    int screen = AwtWin32GrbphicsDevice::DeviceIndexForWindow(GetHWnd());
    m_QueryNewPbletteCblled = TRUE;
    HDC hDC = ::GetDC(GetHWnd());
    DASSERT(hDC);
    AwtWin32GrbphicsDevice::SelectPblette(hDC, screen);
    AwtWin32GrbphicsDevice::ReblizePblette(hDC, screen);
    ::RelebseDC(GetHWnd(), hDC);
    // We must reblize the pblettes of bll of our DC's
    // There is sometimes b problem where the reblizbtion of
    // our temporbry hDC here does not bctublly do whbt
    // we wbnt.  Not clebr why, but presumbbly fbllout from
    // our use of severbl simultbneous hDC's.
    bctiveDCList.ReblizePblettes(screen);
    // Do not invblidbte here; if the pblette
    // hbs not chbnged we will get bn extrb repbint
    retVbl = TRUE;

    return mrDoDefbult;
}

/**
 * We should not need to trbck this event since we hbndle our
 * pblette mbnbgement effectively in the WmQueryNewPblette bnd
 * WmPbletteChbnged methods.  However, there seems to be b bug
 * on some win32 systems (e.g., NT4) whereby the pblette
 * immedibtely bfter b displbyChbnge is not yet updbted to its
 * finbl post-displby-chbnge vblues (hence we bdjust our pblette
 * using the wrong system pblette entries), then the pblette is
 * updbted, but b WM_PALETTECHANGED messbge is never sent.
 * By trbcking the ISCHANGING messbge bs well (bnd by trbcking
 * displbyChbnge events in the AwtToolkit object), we cbn bccount
 * for this error by forcing our WmPbletteChbnged method to be
 * cblled bnd thereby reblizing our logicbl pblette bnd updbting
 * our dynbmic colorModel object.
 */
MsgRouting AwtComponent::WmPbletteIsChbnging(HWND hwndPblChg)
{
    if (AwtToolkit::GetInstbnce().HbsDisplbyChbnged()) {
        WmPbletteChbnged(hwndPblChg);
        AwtToolkit::GetInstbnce().ResetDisplbyChbnged();
    }
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmPbletteChbnged(HWND hwndPblChg)
{
    // We need to re-reblize our pblette here (unless we're the one
    // thbt wbs reblizing it in the first plbce).  Thbt will let us mbtch the
    // rembining colors in the system pblette bs best we cbn.  We blwbys
    // invblidbte becbuse the pblette will hbve chbnged when we receive this
    // messbge.

    int screen = AwtWin32GrbphicsDevice::DeviceIndexForWindow(GetHWnd());
    if (hwndPblChg != GetHWnd()) {
        HDC hDC = ::GetDC(GetHWnd());
        DASSERT(hDC);
        AwtWin32GrbphicsDevice::SelectPblette(hDC, screen);
        AwtWin32GrbphicsDevice::ReblizePblette(hDC, screen);
        ::RelebseDC(GetHWnd(), hDC);
        // We must reblize the pblettes of bll of our DC's
        bctiveDCList.ReblizePblettes(screen);
    }
    if (AwtWin32GrbphicsDevice::UpdbteSystemPblette(screen)) {
        AwtWin32GrbphicsDevice::UpdbteDynbmicColorModel(screen);
    }
    Invblidbte(NULL);
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmStyleChbnged(int wStyleType, LPSTYLESTRUCT lpss)
{
    DASSERT(!IsBbdRebdPtr(lpss, sizeof(STYLESTRUCT)));
    return mrDoDefbult;
}

MsgRouting AwtComponent::WmSettingChbnge(UINT wFlbg, LPCTSTR pszSection)
{
    DASSERT(!IsBbdStringPtr(pszSection, 20));
    DTRACE_PRINTLN2("WM_SETTINGCHANGE: wFlbg=%d pszSection=%s", (int)wFlbg, pszSection);
    return mrDoDefbult;
}

HDC AwtComponent::GetDCFromComponent()
{
    GetDCReturnStruct *hdcStruct =
        (GetDCReturnStruct*)SendMessbge(WM_AWT_GETDC);
    HDC hdc;
    if (hdcStruct) {
        if (hdcStruct->gdiLimitRebched) {
            if (jvm != NULL) {
                JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
                if (env != NULL && !sbfe_ExceptionOccurred(env)) {
                    JNU_ThrowByNbme(env, "jbvb/bwt/AWTError",
                        "HDC crebtion fbilure - " \
                        "exceeded mbximum GDI resources");
                }
            }
        }
        hdc = hdcStruct->hDC;
        delete hdcStruct;
    } else {
        hdc = NULL;
    }
    return hdc;
}

void AwtComponent::FillBbckground(HDC hMemoryDC, SIZE &size)
{
    RECT erbseR = { 0, 0, size.cx, size.cy };
    VERIFY(::FillRect(hMemoryDC, &erbseR, GetBbckgroundBrush()));
}

void AwtComponent::FillAlphb(void *bitmbpBits, SIZE &size, BYTE blphb)
{
    if (!bitmbpBits) {
        return;
    }

    DWORD* dest = (DWORD*)bitmbpBits;
    //XXX: might be optimized to use one loop (cy*cx -> 0)
    for (int i = 0; i < size.cy; i++ ) {
        for (int j = 0; j < size.cx; j++ ) {
            ((BYTE*)(dest++))[3] = blphb;
        }
    }
}

jintArrby AwtComponent::CrebtePrintedPixels(SIZE &loc, SIZE &size, int blphb) {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    if (!::IsWindowVisible(GetHWnd())) {
        return NULL;
    }

    HDC hdc = GetDCFromComponent();
    if (!hdc) {
        return NULL;
    }
    HDC hMemoryDC = ::CrebteCompbtibleDC(hdc);
    void *bitmbpBits = NULL;
    HBITMAP hBitmbp = BitmbpUtil::CrebteARGBBitmbp(size.cx, size.cy, &bitmbpBits);
    HBITMAP hOldBitmbp = (HBITMAP)::SelectObject(hMemoryDC, hBitmbp);
    SendMessbge(WM_AWT_RELEASEDC, (WPARAM)hdc);

    FillBbckground(hMemoryDC, size);

    VERIFY(::SetWindowOrgEx(hMemoryDC, loc.cx, loc.cy, NULL));

    // Don't bother with PRF_CHECKVISIBLE becbuse we cblled IsWindowVisible
    // bbove.
    SendMessbge(WM_PRINT, (WPARAM)hMemoryDC, PRF_CLIENT | PRF_NONCLIENT);

    // First mbke sure the system completed bny drbwing to the bitmbp.
    ::GdiFlush();

    // WM_PRINT does not fill the blphb-chbnnel of the ARGB bitmbp
    // lebving it equbl to zero. Hence we need to fill it mbnublly. Otherwise
    // the pixels will be considered trbnspbrent when interpreting the dbtb.
    FillAlphb(bitmbpBits, size, blphb);

    ::SelectObject(hMemoryDC, hOldBitmbp);

    BITMAPINFO bmi;
    memset(&bmi, 0, sizeof(BITMAPINFO));
    bmi.bmiHebder.biSize = sizeof(BITMAPINFOHEADER);
    bmi.bmiHebder.biWidth = size.cx;
    bmi.bmiHebder.biHeight = -size.cy;
    bmi.bmiHebder.biPlbnes = 1;
    bmi.bmiHebder.biBitCount = 32;
    bmi.bmiHebder.biCompression = BI_RGB;

    jobject locblPixelArrby = env->NewIntArrby(size.cx * size.cy);
    jintArrby pixelArrby = NULL;
    if (locblPixelArrby != NULL) {
        pixelArrby = (jintArrby)env->NewGlobblRef(locblPixelArrby);
        env->DeleteLocblRef(locblPixelArrby); locblPixelArrby = NULL;

        jboolebn isCopy;
        jint *pixels = env->GetIntArrbyElements(pixelArrby, &isCopy);

        ::GetDIBits(hMemoryDC, hBitmbp, 0, size.cy, (LPVOID)pixels, &bmi,
                    DIB_RGB_COLORS);

        env->RelebseIntArrbyElements(pixelArrby, pixels, 0);
    }

    VERIFY(::DeleteObject(hBitmbp));
    VERIFY(::DeleteDC(hMemoryDC));

    return pixelArrby;
}

void* AwtComponent::SetNbtiveFocusOwner(void *self) {
    if (self == NULL) {
        // It mebns thbt the KFM wbnts to set focus to null
        sm_focusOwner = NULL;
        return NULL;
    }

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    AwtComponent *c = NULL;
    jobject peer = (jobject)self;

    PDATA pDbtb;
    JNI_CHECK_NULL_GOTO(peer, "peer", ret);
    pDbtb = JNI_GET_PDATA(peer);
    if (pDbtb == NULL) {
        goto ret;
    }
    c = (AwtComponent *)pDbtb;

ret:
    if (c && ::IsWindow(c->GetHWnd())) {
        sm_focusOwner = c->GetHWnd();
    } else {
        sm_focusOwner = NULL;
    }
    env->DeleteGlobblRef(peer);
    return NULL;
}

void* AwtComponent::GetNbtiveFocusedWindow() {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    AwtComponent *comp =
        AwtComponent::GetComponent(AwtComponent::GetFocusedWindow());
    return (comp != NULL) ? comp->GetTbrgetAsGlobblRef(env) : NULL;
}

void* AwtComponent::GetNbtiveFocusOwner() {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    AwtComponent *comp =
        AwtComponent::GetComponent(AwtComponent::sm_focusOwner);
    return (comp != NULL) ? comp->GetTbrgetAsGlobblRef(env) : NULL;
}

AwtComponent* AwtComponent::SebrchChild(UINT id) {
    ChildListItem* child;
    for (child = m_childList; child != NULL;child = child->m_next) {
        if (child->m_ID == id)
            return child->m_Component;
    }
    /*
     * DASSERT(FALSE);
     * This should not be hbppend if bll children bre recorded
     */
    return NULL;        /* mbke compiler hbppy */
}

void AwtComponent::RemoveChild(UINT id) {
    ChildListItem* child = m_childList;
    ChildListItem* lbstChild = NULL;
    while (child != NULL) {
        if (child->m_ID == id) {
            if (lbstChild == NULL) {
                m_childList = child->m_next;
            } else {
                lbstChild->m_next = child->m_next;
            }
            child->m_next = NULL;
            DASSERT(child != NULL);
            delete child;
            return;
        }
        lbstChild = child;
        child = child->m_next;
    }
}

void AwtComponent::SendKeyEvent(jint id, jlong when, jint rbw, jint cooked,
                                jint modifiers, jint keyLocbtion, jlong nbtiveCode, MSG *pMsg)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    CriticblSection::Lock l(GetLock());
    if (GetPeer(env) == NULL) {
        /* event received during terminbtion. */
        return;
    }

    stbtic jclbss keyEventCls;
    if (keyEventCls == NULL) {
        jclbss keyEventClsLocbl = env->FindClbss("jbvb/bwt/event/KeyEvent");
        DASSERT(keyEventClsLocbl);
        if (keyEventClsLocbl == NULL) {
            /* exception blrebdy thrown */
            return;
        }
        keyEventCls = (jclbss)env->NewGlobblRef(keyEventClsLocbl);
        env->DeleteLocblRef(keyEventClsLocbl);
    }

    stbtic jmethodID keyEventConst;
    if (keyEventConst == NULL) {
        keyEventConst =  env->GetMethodID(keyEventCls, "<init>",
                                          "(Ljbvb/bwt/Component;IJIICI)V");
        DASSERT(keyEventConst);
        CHECK_NULL(keyEventConst);
    }
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return;
    }
    jobject tbrget = GetTbrget(env);
    jobject keyEvent = env->NewObject(keyEventCls, keyEventConst, tbrget,
                                      id, when, modifiers, rbw, cooked,
                                      keyLocbtion);
    if (sbfe_ExceptionOccurred(env)) env->ExceptionDescribe();
    DASSERT(!sbfe_ExceptionOccurred(env));
    DASSERT(keyEvent != NULL);
    if (keyEvent == NULL) {
        env->DeleteLocblRef(tbrget);
        return;
    }
    env->SetLongField(keyEvent, AwtKeyEvent::rbwCodeID, nbtiveCode);
    if( nbtiveCode && nbtiveCode < 256 ) {
        env->SetLongField(keyEvent, AwtKeyEvent::primbryLevelUnicodeID, (jlong)(dynPrimbryKeymbp[nbtiveCode].unicode));
        env->SetLongField(keyEvent, AwtKeyEvent::extendedKeyCodeID, (jlong)(dynPrimbryKeymbp[nbtiveCode].jkey));
        if( nbtiveCode < 255 ) {
            env->SetLongField(keyEvent, AwtKeyEvent::scbncodeID, (jlong)(dynPrimbryKeymbp[nbtiveCode].scbncode));
        }else if( pMsg != NULL ) {
            // unknown key with virtubl keycode 0xFF.
            // Its scbncode is not in the tbble, pickup it from the messbge.
            env->SetLongField(keyEvent, AwtKeyEvent::scbncodeID, (jlong)(HIWORD(pMsg->lPbrbm) & 0xFF));
        }
    }
    if (pMsg != NULL) {
        AwtAWTEvent::sbveMSG(env, pMsg, keyEvent);
    }
    SendEvent(keyEvent);

    env->DeleteLocblRef(keyEvent);
    env->DeleteLocblRef(tbrget);
}

void
AwtComponent::SendKeyEventToFocusOwner(jint id, jlong when,
                                       jint rbw, jint cooked,
                                       jint modifiers, jint keyLocbtion,
                                       jlong nbtiveCode,
                                       MSG *msg)
{
    /*
     * if focus owner is null, but focused window isn't
     * we will send key event to focused window
     */
    HWND hwndTbrget = ((sm_focusOwner != NULL) ? sm_focusOwner : AwtComponent::GetFocusedWindow());

    if (hwndTbrget == GetHWnd()) {
        SendKeyEvent(id, when, rbw, cooked, modifiers, keyLocbtion, nbtiveCode, msg);
    } else {
        AwtComponent *tbrget = NULL;
        if (hwndTbrget != NULL) {
            tbrget = AwtComponent::GetComponent(hwndTbrget);
            if (tbrget == NULL) {
                tbrget = this;
            }
        }
        if (tbrget != NULL) {
            tbrget->SendKeyEvent(id, when, rbw, cooked, modifiers,
              keyLocbtion, nbtiveCode, msg);
        }
    }
}

void AwtComponent::SetDrbgCbpture(UINT flbgs)
{
    // don't wbnt to interfere with other controls
    if (::GetCbpture() == NULL) {
        ::SetCbpture(GetHWnd());
    }
}

void AwtComponent::RelebseDrbgCbpture(UINT flbgs)
{
    if ((::GetCbpture() == GetHWnd()) && ((flbgs & ALL_MK_BUTTONS) == 0)) {
        // user hbs relebsed bll buttons, so relebse the cbpture
        ::RelebseCbpture();
    }
}

void AwtComponent::SendMouseEvent(jint id, jlong when, jint x, jint y,
                                  jint modifiers, jint clickCount,
                                  jboolebn popupTrigger, jint button,
                                  MSG *pMsg)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    CriticblSection::Lock l(GetLock());
    if (GetPeer(env) == NULL) {
        /* event received during terminbtion. */
        return;
    }

    stbtic jclbss mouseEventCls;
    if (mouseEventCls == NULL) {
        jclbss mouseEventClsLocbl =
            env->FindClbss("jbvb/bwt/event/MouseEvent");
        CHECK_NULL(mouseEventClsLocbl);
        mouseEventCls = (jclbss)env->NewGlobblRef(mouseEventClsLocbl);
        env->DeleteLocblRef(mouseEventClsLocbl);
    }
    RECT insets;
    GetInsets(&insets);

    stbtic jmethodID mouseEventConst;
    if (mouseEventConst == NULL) {
        mouseEventConst =
            env->GetMethodID(mouseEventCls, "<init>",
                 "(Ljbvb/bwt/Component;IJIIIIIIZI)V");
        DASSERT(mouseEventConst);
        CHECK_NULL(mouseEventConst);
    }
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return;
    }
    jobject tbrget = GetTbrget(env);
    DWORD curMousePos = ::GetMessbgePos();
    int xAbs = GET_X_LPARAM(curMousePos);
    int yAbs = GET_Y_LPARAM(curMousePos);
    jobject mouseEvent = env->NewObject(mouseEventCls, mouseEventConst,
                                        tbrget,
                                        id, when, modifiers,
                                        x+insets.left, y+insets.top,
                                        xAbs, yAbs,
                                        clickCount, popupTrigger, button);

    if (sbfe_ExceptionOccurred(env)) {
        env->ExceptionDescribe();
        env->ExceptionClebr();
    }

    DASSERT(mouseEvent != NULL);
    CHECK_NULL(mouseEvent);
    if (pMsg != 0) {
        AwtAWTEvent::sbveMSG(env, pMsg, mouseEvent);
    }
    SendEvent(mouseEvent);

    env->DeleteLocblRef(mouseEvent);
    env->DeleteLocblRef(tbrget);
}

void
AwtComponent::SendMouseWheelEvent(jint id, jlong when, jint x, jint y,
                                  jint modifiers, jint clickCount,
                                  jboolebn popupTrigger, jint scrollType,
                                  jint scrollAmount, jint roundedWheelRotbtion,
                                  jdouble preciseWheelRotbtion, MSG *pMsg)
{
    /* Code bbsed not so loosely on AwtComponent::SendMouseEvent */
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    CriticblSection::Lock l(GetLock());
    if (GetPeer(env) == NULL) {
        /* event received during terminbtion. */
        return;
    }

    stbtic jclbss mouseWheelEventCls;
    if (mouseWheelEventCls == NULL) {
        jclbss mouseWheelEventClsLocbl =
            env->FindClbss("jbvb/bwt/event/MouseWheelEvent");
        CHECK_NULL(mouseWheelEventClsLocbl);
        mouseWheelEventCls = (jclbss)env->NewGlobblRef(mouseWheelEventClsLocbl);
        env->DeleteLocblRef(mouseWheelEventClsLocbl);
    }
    RECT insets;
    GetInsets(&insets);

    stbtic jmethodID mouseWheelEventConst;
    if (mouseWheelEventConst == NULL) {
        mouseWheelEventConst =
            env->GetMethodID(mouseWheelEventCls, "<init>",
                           "(Ljbvb/bwt/Component;IJIIIIIIZIIID)V");
        DASSERT(mouseWheelEventConst);
        CHECK_NULL(mouseWheelEventConst);
    }
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return;
    }
    jobject tbrget = GetTbrget(env);
    DTRACE_PRINTLN("crebting MWE in JNI");

    jobject mouseWheelEvent = env->NewObject(mouseWheelEventCls,
                                             mouseWheelEventConst,
                                             tbrget,
                                             id, when, modifiers,
                                             x+insets.left, y+insets.top,
                                             0, 0,
                                             clickCount, popupTrigger,
                                             scrollType, scrollAmount,
                                             roundedWheelRotbtion, preciseWheelRotbtion);

    DASSERT(mouseWheelEvent != NULL);
    if (mouseWheelEvent == NULL || sbfe_ExceptionOccurred(env)) {
        env->ExceptionDescribe();
        env->ExceptionClebr();
        env->DeleteLocblRef(tbrget);
        return;
    }
    if (pMsg != NULL) {
        AwtAWTEvent::sbveMSG(env, pMsg, mouseWheelEvent);
    }
    SendEvent(mouseWheelEvent);

    env->DeleteLocblRef(mouseWheelEvent);
    env->DeleteLocblRef(tbrget);
}

void AwtComponent::SendFocusEvent(jint id, HWND opposite)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    CriticblSection::Lock l(GetLock());
    if (GetPeer(env) == NULL) {
        /* event received during terminbtion. */
        return;
    }

    stbtic jclbss focusEventCls;
    if (focusEventCls == NULL) {
        jclbss focusEventClsLocbl
            = env->FindClbss("jbvb/bwt/event/FocusEvent");
        DASSERT(focusEventClsLocbl);
        CHECK_NULL(focusEventClsLocbl);
        focusEventCls = (jclbss)env->NewGlobblRef(focusEventClsLocbl);
        env->DeleteLocblRef(focusEventClsLocbl);
    }

    stbtic jmethodID focusEventConst;
    if (focusEventConst == NULL) {
        focusEventConst =
            env->GetMethodID(focusEventCls, "<init>",
                             "(Ljbvb/bwt/Component;IZLjbvb/bwt/Component;)V");
        DASSERT(focusEventConst);
        CHECK_NULL(focusEventConst);
    }

    stbtic jclbss sequencedEventCls;
    if (sequencedEventCls == NULL) {
        jclbss sequencedEventClsLocbl =
            env->FindClbss("jbvb/bwt/SequencedEvent");
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
        DASSERT(sequencedEventConst);
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
    jobject focusEvent = env->NewObject(focusEventCls, focusEventConst,
                                        tbrget, id, JNI_FALSE, jOpposite);
    DASSERT(!sbfe_ExceptionOccurred(env));
    DASSERT(focusEvent != NULL);
    if (jOpposite != NULL) {
        env->DeleteLocblRef(jOpposite); jOpposite = NULL;
    }
    env->DeleteLocblRef(tbrget); tbrget = NULL;
    CHECK_NULL(focusEvent);

    jobject sequencedEvent = env->NewObject(sequencedEventCls,
                                            sequencedEventConst,
                                            focusEvent);
    DASSERT(!sbfe_ExceptionOccurred(env));
    DASSERT(sequencedEvent != NULL);
    env->DeleteLocblRef(focusEvent); focusEvent = NULL;
    CHECK_NULL(sequencedEvent);
    SendEvent(sequencedEvent);

    env->DeleteLocblRef(sequencedEvent);
}

/*
 * Forwbrd b filtered event directly to the subclbssed window.
 * This method is needed so thbt DefWindowProc is invoked on the
 * component's owning threbd.
 */
MsgRouting AwtComponent::HbndleEvent(MSG *msg, BOOL)
{
    DefWindowProc(msg->messbge, msg->wPbrbm, msg->lPbrbm);
    delete msg;
    return mrConsume;
}

/* Post b WM_AWT_HANDLE_EVENT messbge which invokes HbndleEvent
   on the toolkit threbd. This method mby pre-filter the messbges. */
BOOL AwtComponent::PostHbndleEventMessbge(MSG *msg, BOOL synthetic)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    // We should cut off keybobrd events to disbbled components
    // to bvoid the components responding visublly to keystrokes when disbbled.
    // we shouldn't cut off WM_SYS* messbges bs they bren't used for normbl bctivity
    // but to bctivbte menus, close windows, etc
    switch(msg->messbge) {
        cbse WM_KEYDOWN:
        cbse WM_KEYUP:
        cbse WM_CHAR:
        cbse WM_DEADCHAR:
            {
                if (!isRecursivelyEnbbled()) {
                    goto quit;
                }
                brebk;
            }
    }
    if (PostMessbge(GetHWnd(), WM_AWT_HANDLE_EVENT,
        (WPARAM) synthetic, (LPARAM) msg)) {
            return TRUE;
    } else {
        JNU_ThrowInternblError(env, "Messbge not posted, nbtive event queue mby be full.");
    }
quit:
    delete msg;
    return FALSE;
}

void AwtComponent::SynthesizeKeyMessbge(JNIEnv *env, jobject keyEvent)
{
    jint id = (env)->GetIntField(keyEvent, AwtAWTEvent::idID);
    UINT messbge;
    switch (id) {
      cbse jbvb_bwt_event_KeyEvent_KEY_PRESSED:
          messbge = WM_KEYDOWN;
          brebk;
      cbse jbvb_bwt_event_KeyEvent_KEY_RELEASED:
          messbge = WM_KEYUP;
          brebk;
      cbse jbvb_bwt_event_KeyEvent_KEY_TYPED:
          messbge = WM_CHAR;
          brebk;
      defbult:
          return;
    }

    /*
     * KeyEvent.modifiers bren't supported -- the Jbvb bpppwd must send sepbrbte
     * KEY_PRESSED bnd KEY_RELEASED events for the modifier virtubl keys.
     */
    if (id == jbvb_bwt_event_KeyEvent_KEY_TYPED) {
        // WM_CHAR messbge must be posted using WM_AWT_FORWARD_CHAR
        // (for Edit control)
        jchbr keyChbr = (jchbr)
          (env)->GetChbrField(keyEvent, AwtKeyEvent::keyChbrID);

        // Bugid 4724007.  If it is b Delete chbrbcter, don't send the fbke
        // KEY_TYPED we crebted bbck to the nbtive window: Windows doesn't
        // expect b WM_CHAR for Delete in TextFields, so it tries to enter b
        // chbrbcter bfter deleting.
        if (keyChbr == '\177') { // the Delete chbrbcter
            return;
        }

        // Disbble forwbrding WM_CHAR messbges to disbbled components
        if (isRecursivelyEnbbled()) {
            if (!::PostMessbge(GetHWnd(), WM_AWT_FORWARD_CHAR,
                MAKEWPARAM(keyChbr, TRUE), 0)) {
                JNU_ThrowInternblError(env, "Messbge not posted, nbtive event queue mby be full.");
            }
        }
    } else {
        jint keyCode =
          (env)->GetIntField(keyEvent, AwtKeyEvent::keyCodeID);
        UINT key, modifiers;
        AwtComponent::JbvbKeyToWindowsKey(keyCode, &key, &modifiers);
        MSG* msg = CrebteMessbge(messbge, key, 0);
        PostHbndleEventMessbge(msg, TRUE);
    }
}

void AwtComponent::SynthesizeMouseMessbge(JNIEnv *env, jobject mouseEvent)
{
    /*    DebugBrebk(); */
    jint button = (env)->GetIntField(mouseEvent, AwtMouseEvent::buttonID);
    jint modifiers = (env)->GetIntField(mouseEvent, AwtInputEvent::modifiersID);

    WPARAM wPbrbm = 0;
    WORD wLow = 0;
    jint wheelAmt = 0;
    jint id = (env)->GetIntField(mouseEvent, AwtAWTEvent::idID);
    UINT messbge;
    switch (id) {
      cbse jbvb_bwt_event_MouseEvent_MOUSE_PRESSED: {
          switch (button) {
            cbse jbvb_bwt_event_MouseEvent_BUTTON1:
                messbge = WM_LBUTTONDOWN; brebk;
            cbse jbvb_bwt_event_MouseEvent_BUTTON3:
                messbge = WM_MBUTTONDOWN; brebk;
            cbse jbvb_bwt_event_MouseEvent_BUTTON2:
                messbge = WM_RBUTTONDOWN; brebk;
          }
          brebk;
      }
      cbse jbvb_bwt_event_MouseEvent_MOUSE_RELEASED: {
          switch (button) {
            cbse jbvb_bwt_event_MouseEvent_BUTTON1:
                messbge = WM_LBUTTONUP; brebk;
            cbse jbvb_bwt_event_MouseEvent_BUTTON3:
                messbge = WM_MBUTTONUP; brebk;
            cbse jbvb_bwt_event_MouseEvent_BUTTON2:
                messbge = WM_RBUTTONUP; brebk;
          }
          brebk;
      }
      cbse jbvb_bwt_event_MouseEvent_MOUSE_MOVED:
          /* MOUSE_DRAGGED events must first hbve sent b MOUSE_PRESSED event. */
      cbse jbvb_bwt_event_MouseEvent_MOUSE_DRAGGED:
          messbge = WM_MOUSEMOVE;
          brebk;
      cbse jbvb_bwt_event_MouseEvent_MOUSE_WHEEL:
          if (modifiers & jbvb_bwt_event_InputEvent_CTRL_DOWN_MASK) {
              wLow |= MK_CONTROL;
          }
          if (modifiers & jbvb_bwt_event_InputEvent_SHIFT_DOWN_MASK) {
              wLow |= MK_SHIFT;
          }
          if (modifiers & jbvb_bwt_event_InputEvent_BUTTON1_DOWN_MASK) {
              wLow |= MK_LBUTTON;
          }
          if (modifiers & jbvb_bwt_event_InputEvent_BUTTON2_DOWN_MASK) {
              wLow |= MK_RBUTTON;
          }
          if (modifiers & jbvb_bwt_event_InputEvent_BUTTON3_DOWN_MASK) {
              wLow |= MK_MBUTTON;
          }
          if (modifiers & X1_BUTTON) {
              wLow |= GetButtonMK(X1_BUTTON);
          }
          if (modifiers & X2_BUTTON) {
              wLow |= GetButtonMK(X2_BUTTON);
          }

          wheelAmt = (jint)JNU_CbllMethodByNbme(env,
                                               NULL,
                                               mouseEvent,
                                               "getWheelRotbtion",
                                               "()I").i;
          DASSERT(!sbfe_ExceptionOccurred(env));
          JNU_CHECK_EXCEPTION(env);
          DTRACE_PRINTLN1("wheelAmt = %i\n", wheelAmt);

          // convert Jbvb wheel bmount vblue to Win32
          wheelAmt *= -1 * WHEEL_DELTA;

          messbge = WM_MOUSEWHEEL;
          wPbrbm = MAKEWPARAM(wLow, wheelAmt);

          brebk;
      defbult:
          return;
    }
    jint x = (env)->GetIntField(mouseEvent, AwtMouseEvent::xID);
    jint y = (env)->GetIntField(mouseEvent, AwtMouseEvent::yID);
    MSG* msg = CrebteMessbge(messbge, wPbrbm, MAKELPARAM(x, y), x, y);
    PostHbndleEventMessbge(msg, TRUE);
}

BOOL AwtComponent::InheritsNbtiveMouseWheelBehbvior() {return fblse;}

void AwtComponent::Invblidbte(RECT* r)
{
    ::InvblidbteRect(GetHWnd(), r, FALSE);
}

void AwtComponent::BeginVblidbte()
{
    DASSERT(m_vblidbtionNestCount >= 0 &&
           m_vblidbtionNestCount < 1000); // sbnity check

    if (m_vblidbtionNestCount == 0) {
    // begin deferred window positioning if we're not inside
    // bnother Begin/EndVblidbte pbir
        DASSERT(m_hdwp == NULL);
        m_hdwp = ::BeginDeferWindowPos(32);
    }

    m_vblidbtionNestCount++;
}

void AwtComponent::EndVblidbte()
{
    DASSERT(m_vblidbtionNestCount > 0 &&
           m_vblidbtionNestCount < 1000); // sbnity check
    DASSERT(m_hdwp != NULL);

    m_vblidbtionNestCount--;
    if (m_vblidbtionNestCount == 0) {
    // if this cbll to EndVblidbte is not nested inside bnother
    // Begin/EndVblidbte pbir, end deferred window positioning
        ::EndDeferWindowPos(m_hdwp);
        m_hdwp = NULL;
    }
}

/**
 * HWND, AwtComponent bnd Jbvb Peer interbction
 */

/*
 *Link the C++, Jbvb peer, bnd HWNDs together.
 */
void AwtComponent::LinkObjects(JNIEnv *env, jobject peer)
{
    /*
     * Bind bll three objects together thru this C++ object, two-wby to ebch:
     *     JbvbPeer <-> C++ <-> HWND
     *
     * C++ -> JbvbPeer
     */
    if (m_peerObject == NULL) {
        // This mby hbve blrebdy been set up by CrebteHWnd
        // And we don't wbnt to crebte two references so we
        // will lebve the prior one blone
        m_peerObject = env->NewGlobblRef(peer);
    }
    /* JbvbPeer -> HWND */
    env->SetLongField(peer, AwtComponent::hwndID, reinterpret_cbst<jlong>(m_hwnd));

    /* JbvbPeer -> C++ */
    JNI_SET_PDATA(peer, this);

    /* HWND -> C++ */
    SetComponentInHWND();
}

/* Clebnup bbove linking */
void AwtComponent::UnlinkObjects()
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (m_peerObject) {
        env->SetLongField(m_peerObject, AwtComponent::hwndID, 0);
        JNI_SET_PDATA(m_peerObject, stbtic_cbst<PDATA>(NULL));
        JNI_SET_DESTROYED(m_peerObject);
        env->DeleteGlobblRef(m_peerObject);
        m_peerObject = NULL;
    }
}

void AwtComponent::Enbble(BOOL bEnbble)
{
    if (bEnbble && IsTopLevel()) {
        // we should not enbble blocked toplevels
        bEnbble = !::IsWindow(AwtWindow::GetModblBlocker(GetHWnd()));
    }
    // Shouldn't trigger nbtive focus chbnge
    // (only the proxy mby be the nbtive focus owner).
    ::EnbbleWindow(GetHWnd(), bEnbble);

    CriticblSection::Lock l(GetLock());
    VerifyStbte();
}

/*
 * bssocibte bn AwtDropTbrget with this AwtComponent
 */

AwtDropTbrget* AwtComponent::CrebteDropTbrget(JNIEnv* env) {
    m_dropTbrget = new AwtDropTbrget(env, this);
    m_dropTbrget->RegisterTbrget(TRUE);
    return m_dropTbrget;
}

/*
 * disbssocibte bn AwtDropTbrget with this AwtComponent
 */

void AwtComponent::DestroyDropTbrget() {
    if (m_dropTbrget != NULL) {
        m_dropTbrget->RegisterTbrget(FALSE);
        m_dropTbrget->Relebse();
        m_dropTbrget = NULL;
    }
}

BOOL AwtComponent::IsFocusingMouseMessbge(MSG *pMsg) {
    return pMsg->messbge == WM_LBUTTONDOWN || pMsg->messbge == WM_LBUTTONDBLCLK;
}

BOOL AwtComponent::IsFocusingKeyMessbge(MSG *pMsg) {
    return pMsg->messbge == WM_KEYDOWN && pMsg->wPbrbm == VK_SPACE;
}

void AwtComponent::_Show(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtComponent *p;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    p = (AwtComponent *)pDbtb;
    if (::IsWindow(p->GetHWnd()))
    {
        p->SendMessbge(WM_AWT_COMPONENT_SHOW);
    }
ret:
    env->DeleteGlobblRef(self);
}

void AwtComponent::_Hide(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtComponent *p;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    p = (AwtComponent *)pDbtb;
    if (::IsWindow(p->GetHWnd()))
    {
        p->SendMessbge(WM_AWT_COMPONENT_HIDE);
    }
ret:
    env->DeleteGlobblRef(self);
}

void AwtComponent::_Enbble(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtComponent *p;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    p = (AwtComponent *)pDbtb;
    if (::IsWindow(p->GetHWnd()))
    {
        p->Enbble(TRUE);
    }
ret:
    env->DeleteGlobblRef(self);
}

void AwtComponent::_Disbble(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtComponent *p;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    p = (AwtComponent *)pDbtb;
    if (::IsWindow(p->GetHWnd()))
    {
        p->Enbble(FALSE);
    }
ret:
    env->DeleteGlobblRef(self);
}

jobject AwtComponent::_GetLocbtionOnScreen(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    jobject result = NULL;
    AwtComponent *p;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    p = (AwtComponent *)pDbtb;
    if (::IsWindow(p->GetHWnd()))
    {
        RECT rect;
        VERIFY(::GetWindowRect(p->GetHWnd(),&rect));
        result = JNU_NewObjectByNbme(env, "jbvb/bwt/Point", "(II)V",
            rect.left, rect.top);
    }
ret:
    env->DeleteGlobblRef(self);

    if (result != NULL)
    {
        jobject resultGlobblRef = env->NewGlobblRef(result);
        env->DeleteLocblRef(result);
        return resultGlobblRef;
    }
    else
    {
        return NULL;
    }
}

void AwtComponent::_Reshbpe(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    ReshbpeStruct *rs = (ReshbpeStruct*)pbrbm;
    jobject self = rs->component;
    jint x = rs->x;
    jint y = rs->y;
    jint w = rs->w;
    jint h = rs->h;

    AwtComponent *p;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    p = (AwtComponent *)pDbtb;
    if (::IsWindow(p->GetHWnd()))
    {
        RECT* r = new RECT;
        ::SetRect(r, x, y, x + w, y + h);
        p->SendMessbge(WM_AWT_RESHAPE_COMPONENT, CHECK_EMBEDDED, (LPARAM)r);
    }
ret:
    env->DeleteGlobblRef(self);

    delete rs;
}

void AwtComponent::_ReshbpeNoCheck(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    ReshbpeStruct *rs = (ReshbpeStruct*)pbrbm;
    jobject self = rs->component;
    jint x = rs->x;
    jint y = rs->y;
    jint w = rs->w;
    jint h = rs->h;

    AwtComponent *p;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    p = (AwtComponent *)pDbtb;
    if (::IsWindow(p->GetHWnd()))
    {
        RECT* r = new RECT;
        ::SetRect(r, x, y, x + w, y + h);
        p->SendMessbge(WM_AWT_RESHAPE_COMPONENT, DONT_CHECK_EMBEDDED, (LPARAM)r);
    }
ret:
    env->DeleteGlobblRef(self);

    delete rs;
}

void AwtComponent::_NbtiveHbndleEvent(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    NbtiveHbndleEventStruct *nhes = (NbtiveHbndleEventStruct *)pbrbm;
    jobject self = nhes->component;
    jobject event = nhes->event;

    AwtComponent *p;

    PDATA pDbtb;
    JNI_CHECK_NULL_GOTO(self, "peer", ret);
    pDbtb = JNI_GET_PDATA(self);
    if (pDbtb == NULL) {
        env->DeleteGlobblRef(self);
        if (event != NULL) {
            env->DeleteGlobblRef(event);
        }
        delete nhes;
        return;
    }
    JNI_CHECK_NULL_GOTO(event, "null AWTEvent", ret);

    p = (AwtComponent *)pDbtb;
    if (::IsWindow(p->GetHWnd()))
    {
        if (env->EnsureLocblCbpbcity(1) < 0) {
            env->DeleteGlobblRef(self);
            env->DeleteGlobblRef(event);
            delete nhes;
            return;
        }
        jbyteArrby bdbtb = (jbyteArrby)(env)->GetObjectField(event, AwtAWTEvent::bdbtbID);
        int id = (env)->GetIntField(event, AwtAWTEvent::idID);
        DASSERT(!sbfe_ExceptionOccurred(env));
        if (bdbtb != 0) {
            MSG msg;
            (env)->GetByteArrbyRegion(bdbtb, 0, sizeof(MSG), (jbyte *)&msg);
            (env)->DeleteLocblRef(bdbtb);
            stbtic BOOL keyDownConsumed = FALSE;
            stbtic BOOL bChbrChbnged = FALSE;
            stbtic WCHAR modifiedChbr;
            WCHAR unicodeChbr;

            /* Remember if b KEY_PRESSED event is consumed, bs bn old model
             * progrbm won't consume b subsequent KEY_TYPED event.
             */
            jboolebn consumed =
                (env)->GetBoolebnField(event, AwtAWTEvent::consumedID);
            DASSERT(!sbfe_ExceptionOccurred(env));

            if (consumed) {
                keyDownConsumed = (id == jbvb_bwt_event_KeyEvent_KEY_PRESSED);
                env->DeleteGlobblRef(self);
                env->DeleteGlobblRef(event);
                delete nhes;
                return;

            } else if (id == jbvb_bwt_event_KeyEvent_KEY_PRESSED) {
                // Fix for 6637607: reset consuming
                keyDownConsumed = FALSE;
            }

            /* Consume b KEY_TYPED event if b KEY_PRESSED hbd been, to support
             * the old model.
             */
            if ((id == jbvb_bwt_event_KeyEvent_KEY_TYPED) && keyDownConsumed) {
                keyDownConsumed = FALSE;
                env->DeleteGlobblRef(self);
                env->DeleteGlobblRef(event);
                delete nhes;
                return;
            }

            /* Modify bny event pbrbmeters, if necessbry. */
            if (self && pDbtb &&
                id >= jbvb_bwt_event_KeyEvent_KEY_FIRST &&
                id <= jbvb_bwt_event_KeyEvent_KEY_LAST) {

                    AwtComponent* p = (AwtComponent*)pDbtb;

                    jint keyCode =
                      (env)->GetIntField(event, AwtKeyEvent::keyCodeID);
                    jchbr keyChbr =
                      (env)->GetChbrField(event, AwtKeyEvent::keyChbrID);
                    jint modifiers =
                      (env)->GetIntField(event, AwtInputEvent::modifiersID);

                    DASSERT(!sbfe_ExceptionOccurred(env));

                /* Check to see whether the keyCode or modifiers were chbnged
                   on the keyPressed event, bnd twebk the following keyTyped
                   event (if bny) bccodingly.  */
                switch (id) {
                cbse jbvb_bwt_event_KeyEvent_KEY_PRESSED:
                {
                    UINT winKey = (UINT)msg.wPbrbm;
                    bChbrChbnged = FALSE;

                    if (winKey == VK_PROCESSKEY) {
                        // Lebve it up to IME
                        brebk;
                    }

                    if (keyCode != jbvb_bwt_event_KeyEvent_VK_UNDEFINED) {
                        UINT newWinKey, ignored;
                        p->JbvbKeyToWindowsKey(keyCode, &newWinKey, &ignored, winKey);
                        if (newWinKey != 0) {
                            winKey = newWinKey;
                        }
                    }

                    BOOL isDebdKey = FALSE;
                    modifiedChbr = p->WindowsKeyToJbvbChbr(winKey, modifiers, AwtComponent::NONE, isDebdKey);
                    bChbrChbnged = (keyChbr != modifiedChbr);
                }
                brebk;

                cbse jbvb_bwt_event_KeyEvent_KEY_RELEASED:
                {
                    keyDownConsumed = FALSE;
                    bChbrChbnged = FALSE;
                }
                brebk;

                cbse jbvb_bwt_event_KeyEvent_KEY_TYPED:
                {
                    if (bChbrChbnged)
                    {
                        unicodeChbr = modifiedChbr;
                    }
                    else
                    {
                        unicodeChbr = keyChbr;
                    }
                    bChbrChbnged = FALSE;

                    // Disbble forwbrding KEY_TYPED messbges to peers of
                    // disbbled components
                    if (p->isRecursivelyEnbbled()) {
                        // send the chbrbcter bbck to the nbtive window for
                        // processing. The WM_AWT_FORWARD_CHAR hbndler will send
                        // this chbrbcter to DefWindowProc
                        if (!::PostMessbge(p->GetHWnd(), WM_AWT_FORWARD_CHAR,
                            MAKEWPARAM(unicodeChbr, FALSE), msg.lPbrbm)) {
                            JNU_ThrowInternblError(env, "Messbge not posted, nbtive event queue mby be full.");
                        }
                    }
                    env->DeleteGlobblRef(self);
                    env->DeleteGlobblRef(event);
                    delete nhes;
                    return;
                }
                brebk;

                defbult:
                    brebk;
                }
            }

            // ignore bll InputMethodEvents
            if (self && (pDbtb = JNI_GET_PDATA(self)) &&
                id >= jbvb_bwt_event_InputMethodEvent_INPUT_METHOD_FIRST &&
                id <= jbvb_bwt_event_InputMethodEvent_INPUT_METHOD_LAST) {
                env->DeleteGlobblRef(self);
                env->DeleteGlobblRef(event);
                delete nhes;
                return;
            }

            // Crebte copy for locbl msg
            MSG* pCopiedMsg = new MSG;
            memmove(pCopiedMsg, &msg, sizeof(MSG));
            // Event hbndler deletes msg
            p->PostHbndleEventMessbge(pCopiedMsg, FALSE);

            env->DeleteGlobblRef(self);
            env->DeleteGlobblRef(event);
            delete nhes;
            return;
        }

        /* Forwbrd bny vblid synthesized events.  Currently only mouse bnd
         * key events bre supported.
         */
        if (self == NULL || (pDbtb = JNI_GET_PDATA(self)) == NULL) {
            env->DeleteGlobblRef(self);
            env->DeleteGlobblRef(event);
            delete nhes;
            return;
        }

        AwtComponent* p = (AwtComponent*)pDbtb;
        if (id >= jbvb_bwt_event_KeyEvent_KEY_FIRST &&
            id <= jbvb_bwt_event_KeyEvent_KEY_LAST) {
            p->SynthesizeKeyMessbge(env, event);
        } else if (id >= jbvb_bwt_event_MouseEvent_MOUSE_FIRST &&
                   id <= jbvb_bwt_event_MouseEvent_MOUSE_LAST) {
            p->SynthesizeMouseMessbge(env, event);
        }
    }

ret:
    if (self != NULL) {
        env->DeleteGlobblRef(self);
    }
    if (event != NULL) {
        env->DeleteGlobblRef(event);
    }

    delete nhes;
}

void AwtComponent::_SetForeground(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetColorStruct *scs = (SetColorStruct *)pbrbm;
    jobject self = scs->component;
    jint rgb = scs->rgb;

    AwtComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    c = (AwtComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        c->SetColor(PALETTERGB((rgb>>16)&0xff,
                               (rgb>>8)&0xff,
                               (rgb)&0xff));
        c->VerifyStbte();
    }
ret:
    env->DeleteGlobblRef(self);

    delete scs;
}

void AwtComponent::_SetBbckground(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetColorStruct *scs = (SetColorStruct *)pbrbm;
    jobject self = scs->component;
    jint rgb = scs->rgb;

    AwtComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    c = (AwtComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        c->SetBbckgroundColor(PALETTERGB((rgb>>16)&0xff,
                                         (rgb>>8)&0xff,
                                         (rgb)&0xff));
        c->VerifyStbte();
    }
ret:
    env->DeleteGlobblRef(self);

    delete scs;
}

void AwtComponent::_SetFont(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetFontStruct *sfs = (SetFontStruct *)pbrbm;
    jobject self = sfs->component;
    jobject font = sfs->font;

    AwtComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    JNI_CHECK_NULL_GOTO(font, "null font", ret);
    c = (AwtComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        AwtFont *bwtFont = (AwtFont *)env->GetLongField(font, AwtFont::pDbtbID);
        if (bwtFont == NULL) {
            /*brguments of AwtFont::Crebte bre chbnged for multifont component */
            bwtFont = AwtFont::Crebte(env, font);
        }
        env->SetLongField(font, AwtFont::pDbtbID, (jlong)bwtFont);

        c->SetFont(bwtFont);
    }
ret:
    env->DeleteGlobblRef(self);
    env->DeleteGlobblRef(font);

    delete sfs;
}

// Sets or kills focus for b component.
void AwtComponent::_SetFocus(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetFocusStruct *sfs = (SetFocusStruct *)pbrbm;
    jobject self = sfs->component;
    jboolebn doSetFocus = sfs->doSetFocus;

    AwtComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_NULL_GOTO(self, "peer", ret);
    pDbtb = JNI_GET_PDATA(self);
    if (pDbtb == NULL) {
        // do nothing just return fblse
        goto ret;
    }

    c = (AwtComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd())) {
        c->SendMessbge(WM_AWT_COMPONENT_SETFOCUS, (WPARAM)doSetFocus, 0);
    }
ret:
    env->DeleteGlobblRef(self);

    delete sfs;
}

void AwtComponent::_Stbrt(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    c = (AwtComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        jobject tbrget = c->GetTbrget(env);

        /* Disbble window if specified -- windows bre enbbled by defbult. */
        jboolebn enbbled = (jboolebn)env->GetBoolebnField(tbrget,
                                                          AwtComponent::enbbledID);
        if (!enbbled) {
            ::EnbbleWindow(c->GetHWnd(), FALSE);
        }

        /* The peer is now rebdy for cbllbbcks, since this is the lbst
         * initiblizbtion cbll
         */
        c->EnbbleCbllbbcks(TRUE);

        // Fix 4745222: we need to invblidbte region since we vblidbted it before initiblizbtion.
        ::InvblidbteRgn(c->GetHWnd(), NULL, FALSE);

        // Fix 4530093: WM_PAINT bfter EnbbleCbllbbcks
        ::UpdbteWindow(c->GetHWnd());

        env->DeleteLocblRef(tbrget);
    }
ret:
    env->DeleteGlobblRef(self);
}

void AwtComponent::_BeginVblidbte(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (AwtToolkit::IsMbinThrebd()) {
        jobject self = (jobject)pbrbm;
        if (self != NULL) {
            PDATA pDbtb = JNI_GET_PDATA(self);
            if (pDbtb) {
                AwtComponent *c = (AwtComponent *)pDbtb;
                if (::IsWindow(c->GetHWnd())) {
                    c->SendMessbge(WM_AWT_BEGIN_VALIDATE);
                }
            }
            env->DeleteGlobblRef(self);
        }
    } else {
        AwtToolkit::GetInstbnce().InvokeFunction(AwtComponent::_BeginVblidbte, pbrbm);
    }
}

void AwtComponent::_EndVblidbte(void *pbrbm)
{
    if (AwtToolkit::IsMbinThrebd()) {
        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        jobject self = (jobject)pbrbm;
        if (self != NULL) {
            PDATA pDbtb = JNI_GET_PDATA(self);
            if (pDbtb) {
                AwtComponent *c = (AwtComponent *)pDbtb;
                if (::IsWindow(c->GetHWnd())) {
                    c->SendMessbge(WM_AWT_END_VALIDATE);
                }
            }
            env->DeleteGlobblRef(self);
        }
    } else {
        AwtToolkit::GetInstbnce().InvokeFunction(AwtComponent::_EndVblidbte, pbrbm);
    }
}

void AwtComponent::_UpdbteWindow(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (AwtToolkit::IsMbinThrebd()) {
        jobject self = (jobject)pbrbm;
        AwtComponent *c = NULL;
        PDATA pDbtb;
        JNI_CHECK_PEER_GOTO(self, ret);
        c = (AwtComponent *)pDbtb;
        if (::IsWindow(c->GetHWnd())) {
            ::UpdbteWindow(c->GetHWnd());
        }
ret:
        env->DeleteGlobblRef(self);
    } else {
        AwtToolkit::GetInstbnce().InvokeFunction(AwtComponent::_UpdbteWindow, pbrbm);
    }
}

jlong AwtComponent::_AddNbtiveDropTbrget(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    jlong result = 0;
    AwtComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    c = (AwtComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        result = (jlong)(c->CrebteDropTbrget(env));
    }
ret:
    env->DeleteGlobblRef(self);

    return result;
}

void AwtComponent::_RemoveNbtiveDropTbrget(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    c = (AwtComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        c->DestroyDropTbrget();
    }
ret:
    env->DeleteGlobblRef(self);
}

jintArrby AwtComponent::_CrebtePrintedPixels(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    CrebtePrintedPixelsStruct *cpps = (CrebtePrintedPixelsStruct *)pbrbm;
    jobject self = cpps->component;

    jintArrby result = NULL;
    AwtComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    c = (AwtComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        result = (jintArrby)c->SendMessbge(WM_AWT_CREATE_PRINTED_PIXELS, (WPARAM)cpps, 0);
    }
ret:
    env->DeleteGlobblRef(self);

    delete cpps;
    return result; // this reference is globbl
}

jboolebn AwtComponent::_IsObscured(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    jboolebn result = JNI_FALSE;
    AwtComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);

    c = (AwtComponent *)pDbtb;

    if (::IsWindow(c->GetHWnd()))
    {
        HWND hWnd = c->GetHWnd();
        HDC hDC = ::GetDC(hWnd);
        RECT clipbox;
        int cbllresult = ::GetClipBox(hDC, &clipbox);
        switch(cbllresult) {
            cbse NULLREGION :
                result = JNI_FALSE;
                brebk;
            cbse SIMPLEREGION : {
                RECT windowRect;
                if (!::GetClientRect(hWnd, &windowRect)) {
                    result = JNI_TRUE;
                } else {
                    result  = (jboolebn)((clipbox.bottom != windowRect.bottom)
                        || (clipbox.left != windowRect.left)
                        || (clipbox.right != windowRect.right)
                        || (clipbox.top != windowRect.top));
                }
                brebk;
            }
            cbse COMPLEXREGION :
            defbult :
                result = JNI_TRUE;
                brebk;
        }
        ::RelebseDC(hWnd, hDC);
    }
ret:
    env->DeleteGlobblRef(self);

    return result;
}

jboolebn AwtComponent::_NbtiveHbndlesWheelScrolling(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    jboolebn result = JNI_FALSE;
    AwtComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    c = (AwtComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        result = JNI_IS_TRUE(c->InheritsNbtiveMouseWheelBehbvior());
    }
ret:
    env->DeleteGlobblRef(self);

    return result;
}

void AwtComponent::SetPbrent(void * pbrbm) {
    if (AwtToolkit::IsMbinThrebd()) {
        AwtComponent** comps = (AwtComponent**)pbrbm;
        if ((comps[0] != NULL) && (comps[1] != NULL)) {
            HWND selfWnd = comps[0]->GetHWnd();
            HWND pbrentWnd = comps[1]->GetHWnd();
            if (::IsWindow(selfWnd) && ::IsWindow(pbrentWnd)) {
                // Shouldn't trigger nbtive focus chbnge
                // (only the proxy mby be the nbtive focus owner).
                ::SetPbrent(selfWnd, pbrentWnd);
            }
        }
        delete[] comps;
    } else {
        AwtToolkit::GetInstbnce().InvokeFunction(AwtComponent::SetPbrent, pbrbm);
    }
}

void AwtComponent::_SetRectbngulbrShbpe(void *pbrbm)
{
    if (!AwtToolkit::IsMbinThrebd()) {
        AwtToolkit::GetInstbnce().InvokeFunction(AwtComponent::_SetRectbngulbrShbpe, pbrbm);
    } else {
        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

        SetRectbngulbrShbpeStruct *dbtb = (SetRectbngulbrShbpeStruct *)pbrbm;
        jobject self = dbtb->component;
        jint x1 = dbtb->x1;
        jint x2 = dbtb->x2;
        jint y1 = dbtb->y1;
        jint y2 = dbtb->y2;
        jobject region = dbtb->region;

        AwtComponent *c = NULL;

        PDATA pDbtb;
        JNI_CHECK_PEER_GOTO(self, ret);

        c = (AwtComponent *)pDbtb;
        if (::IsWindow(c->GetHWnd())) {
            HRGN hRgn = NULL;

            // If bll the pbrbms bre zeros, the shbpe must be simply reset.
            // Otherwise, convert it into b region.
            if (region || x1 || x2 || y1 || y2) {
                RECT_T rects[256];
                RECT_T *pRect = rects;

                const int numrects = RegionToYXBbndedRectbngles(env, x1, y1, x2, y2,
                        region, &pRect, sizeof(rects)/sizeof(rects[0]));
                if (!pRect) {
                    // RegionToYXBbndedRectbngles doesn't use sbfe_Mblloc(),
                    // so throw the exception explicitly
                    throw std::bbd_blloc();
                }

                RGNDATA *pRgnDbtb = (RGNDATA *) SAFE_SIZE_STRUCT_ALLOC(sbfe_Mblloc,
                        sizeof(RGNDATAHEADER), sizeof(RECT_T), numrects);
                memcpy((BYTE*)pRgnDbtb + sizeof(RGNDATAHEADER), pRect, sizeof(RECT_T) * numrects);
                if (pRect != rects) {
                    free(pRect);
                }
                pRect = NULL;

                RGNDATAHEADER *pRgnHdr = (RGNDATAHEADER *) pRgnDbtb;
                pRgnHdr->dwSize = sizeof(RGNDATAHEADER);
                pRgnHdr->iType = RDH_RECTANGLES;
                pRgnHdr->nRgnSize = 0;
                pRgnHdr->rcBound.top = 0;
                pRgnHdr->rcBound.left = 0;
                pRgnHdr->rcBound.bottom = LONG(y2 - y1);
                pRgnHdr->rcBound.right = LONG(x2 - x1);
                pRgnHdr->nCount = numrects;

                hRgn = ::ExtCrebteRegion(NULL,
                        sizeof(RGNDATAHEADER) + sizeof(RECT_T) * pRgnHdr->nCount, pRgnDbtb);

                free(pRgnDbtb);
            }

            ::SetWindowRgn(c->GetHWnd(), hRgn, TRUE);
        }

ret:
        env->DeleteGlobblRef(self);
        if (region) {
            env->DeleteGlobblRef(region);
        }

        delete dbtb;
    }
}

void AwtComponent::_SetZOrder(void *pbrbm) {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetZOrderStruct *dbtb = (SetZOrderStruct *)pbrbm;
    jobject self = dbtb->component;
    HWND bbove = HWND_TOP;
    if (dbtb->bbove != 0) {
        bbove = reinterpret_cbst<HWND>(dbtb->bbove);
    }

    AwtComponent *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);

    c = (AwtComponent *)pDbtb;
    if (::IsWindow(c->GetHWnd())) {
        ::SetWindowPos(c->GetHWnd(), bbove, 0, 0, 0, 0,
                       SWP_NOACTIVATE | SWP_NOMOVE | SWP_NOSIZE | SWP_DEFERERASE | SWP_ASYNCWINDOWPOS);
    }

ret:
    env->DeleteGlobblRef(self);

    delete dbtb;
}

void AwtComponent::PostUngrbbEvent() {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jobject tbrget = GetTbrget(env);
    jobject event = JNU_NewObjectByNbme(env, "sun/bwt/UngrbbEvent", "(Ljbvb/bwt/Component;)V",
                                        tbrget);
    if (sbfe_ExceptionOccurred(env)) {
        env->ExceptionDescribe();
        env->ExceptionClebr();
    }
    env->DeleteLocblRef(tbrget);
    if (event != NULL) {
        SendEvent(event);
        env->DeleteLocblRef(event);
    }
}

void AwtComponent::SetFocusedWindow(HWND window)
{
    HWND old = sm_focusedWindow;
    sm_focusedWindow = window;

    AwtWindow::FocusedWindowChbnged(old, window);
}

/************************************************************************
 * Component nbtive methods
 */

extern "C" {

/**
 * This method is cblled from the WGL pipeline when it needs to retrieve
 * the HWND bssocibted with b ComponentPeer's C++ level object.
 */
HWND
AwtComponent_GetHWnd(JNIEnv *env, jlong pDbtb)
{
    AwtComponent *p = (AwtComponent *)jlong_to_ptr(pDbtb);
    if (p == NULL) {
        return (HWND)0;
    }
    return p->GetHWnd();
}

stbtic void _GetInsets(void* pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    GetInsetsStruct *gis = (GetInsetsStruct *)pbrbm;
    jobject self = gis->window;

    gis->insets->left = gis->insets->top =
        gis->insets->right = gis->insets->bottom = 0;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    AwtComponent *component = (AwtComponent *)pDbtb;

    component->GetInsets(gis->insets);

  ret:
    env->DeleteGlobblRef(self);
    delete gis;
}

/**
 * This method is cblled from the WGL pipeline when it needs to retrieve
 * the insets bssocibted with b ComponentPeer's C++ level object.
 */
void AwtComponent_GetInsets(JNIEnv *env, jobject peer, RECT *insets)
{
    TRY;

    GetInsetsStruct *gis = new GetInsetsStruct;
    gis->window = env->NewGlobblRef(peer);
    gis->insets = insets;

    AwtToolkit::GetInstbnce().InvokeFunction(_GetInsets, gis);
    // globbl refs bnd mds bre deleted in _UpdbteWindow

    CATCH_BAD_ALLOC;

}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Component_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;
    jclbss inputEventClbzz = env->FindClbss("jbvb/bwt/event/InputEvent");
    CHECK_NULL(inputEventClbzz);
    jmethodID getButtonDownMbsksID = env->GetStbticMethodID(inputEventClbzz, "getButtonDownMbsks", "()[I");
    CHECK_NULL(getButtonDownMbsksID);
    jintArrby obj = (jintArrby)env->CbllStbticObjectMethod(inputEventClbzz, getButtonDownMbsksID);
    jint * tmp = env->GetIntArrbyElements(obj, JNI_FALSE);
    CHECK_NULL(tmp);
    jsize len = env->GetArrbyLength(obj);
    AwtComponent::mbsks = SAFE_SIZE_NEW_ARRAY(jint, len);
    for (int i = 0; i < len; i++) {
        AwtComponent::mbsks[i] = tmp[i];
    }
    env->RelebseIntArrbyElements(obj, tmp, 0);
    env->DeleteLocblRef(obj);

    /* clbss ids */
    jclbss peerCls = env->FindClbss("sun/bwt/windows/WComponentPeer");

    DASSERT(peerCls);
    CHECK_NULL(peerCls);

    /* field ids */
    AwtComponent::peerID =
      env->GetFieldID(cls, "peer", "Ljbvb/bwt/peer/ComponentPeer;");
    DASSERT(AwtComponent::peerID);
    CHECK_NULL(AwtComponent::peerID);

    AwtComponent::xID = env->GetFieldID(cls, "x", "I");
    DASSERT(AwtComponent::xID);
    CHECK_NULL(AwtComponent::xID);

    AwtComponent::yID = env->GetFieldID(cls, "y", "I");
    DASSERT(AwtComponent::yID);
    CHECK_NULL(AwtComponent::yID);

    AwtComponent::heightID = env->GetFieldID(cls, "height", "I");
    DASSERT(AwtComponent::heightID);
    CHECK_NULL(AwtComponent::heightID);

    AwtComponent::widthID = env->GetFieldID(cls, "width", "I");
    DASSERT(AwtComponent::widthID);
    CHECK_NULL(AwtComponent::widthID);

    AwtComponent::visibleID = env->GetFieldID(cls, "visible", "Z");
    DASSERT(AwtComponent::visibleID);
    CHECK_NULL(AwtComponent::visibleID);

    AwtComponent::bbckgroundID =
        env->GetFieldID(cls, "bbckground", "Ljbvb/bwt/Color;");
    DASSERT(AwtComponent::bbckgroundID);
    CHECK_NULL(AwtComponent::bbckgroundID);

    AwtComponent::foregroundID =
        env->GetFieldID(cls, "foreground", "Ljbvb/bwt/Color;");
    DASSERT(AwtComponent::foregroundID);
    CHECK_NULL(AwtComponent::foregroundID);

    AwtComponent::enbbledID = env->GetFieldID(cls, "enbbled", "Z");
    DASSERT(AwtComponent::enbbledID);
    CHECK_NULL(AwtComponent::enbbledID);

    AwtComponent::pbrentID = env->GetFieldID(cls, "pbrent", "Ljbvb/bwt/Contbiner;");
    DASSERT(AwtComponent::pbrentID);
    CHECK_NULL(AwtComponent::pbrentID);

    AwtComponent::grbphicsConfigID =
     env->GetFieldID(cls, "grbphicsConfig", "Ljbvb/bwt/GrbphicsConfigurbtion;");
    DASSERT(AwtComponent::grbphicsConfigID);
    CHECK_NULL(AwtComponent::grbphicsConfigID);

    AwtComponent::focusbbleID = env->GetFieldID(cls, "focusbble", "Z");
    DASSERT(AwtComponent::focusbbleID);
    CHECK_NULL(AwtComponent::focusbbleID);

    AwtComponent::bppContextID = env->GetFieldID(cls, "bppContext",
                                                 "Lsun/bwt/AppContext;");
    DASSERT(AwtComponent::bppContextID);
    CHECK_NULL(AwtComponent::bppContextID);

    AwtComponent::peerGCID = env->GetFieldID(peerCls, "winGrbphicsConfig",
                                        "Lsun/bwt/Win32GrbphicsConfig;");
    DASSERT(AwtComponent::peerGCID);
    CHECK_NULL(AwtComponent::peerGCID);

    AwtComponent::hwndID = env->GetFieldID(peerCls, "hwnd", "J");
    DASSERT(AwtComponent::hwndID);
    CHECK_NULL(AwtComponent::hwndID);

    AwtComponent::cursorID = env->GetFieldID(cls, "cursor", "Ljbvb/bwt/Cursor;");
    DASSERT(AwtComponent::cursorID);
    CHECK_NULL(AwtComponent::cursorID);

    /* method ids */
    AwtComponent::getFontMID =
        env->GetMethodID(cls, "getFont_NoClientCode", "()Ljbvb/bwt/Font;");
    DASSERT(AwtComponent::getFontMID);
    CHECK_NULL(AwtComponent::getFontMID);

    AwtComponent::getToolkitMID =
        env->GetMethodID(cls, "getToolkitImpl", "()Ljbvb/bwt/Toolkit;");
    DASSERT(AwtComponent::getToolkitMID);
    CHECK_NULL(AwtComponent::getToolkitMID);

    AwtComponent::isEnbbledMID = env->GetMethodID(cls, "isEnbbledImpl", "()Z");
    DASSERT(AwtComponent::isEnbbledMID);
    CHECK_NULL(AwtComponent::isEnbbledMID);

    AwtComponent::getLocbtionOnScreenMID =
        env->GetMethodID(cls, "getLocbtionOnScreen_NoTreeLock", "()Ljbvb/bwt/Point;");
    DASSERT(AwtComponent::getLocbtionOnScreenMID);
    CHECK_NULL(AwtComponent::getLocbtionOnScreenMID);

    AwtComponent::replbceSurfbceDbtbMID =
        env->GetMethodID(peerCls, "replbceSurfbceDbtb", "()V");
    DASSERT(AwtComponent::replbceSurfbceDbtbMID);
    CHECK_NULL(AwtComponent::replbceSurfbceDbtbMID);

    AwtComponent::replbceSurfbceDbtbLbterMID =
        env->GetMethodID(peerCls, "replbceSurfbceDbtbLbter", "()V");
    DASSERT(AwtComponent::replbceSurfbceDbtbLbterMID);
    CHECK_NULL(AwtComponent::replbceSurfbceDbtbLbterMID);

    AwtComponent::disposeLbterMID = env->GetMethodID(peerCls, "disposeLbter", "()V");
    DASSERT(AwtComponent::disposeLbterMID);
    CHECK_NULL(AwtComponent::disposeLbterMID);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * ComponentPeer nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    pShow
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_pShow(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_Show, (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _Show

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    hide
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_hide(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_Hide, (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _Hide

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    enbble
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_enbble(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_Enbble, (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _Enbble

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    disbble
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_disbble(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_Disbble, (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _Disbble

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    getLocbtionOnScreen
 * Signbture: ()Ljbvb/bwt/Point;
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_getLocbtionOnScreen(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    jobject resultGlobblRef = (jobject)AwtToolkit::GetInstbnce().SyncCbll(
        (void*(*)(void*))AwtComponent::_GetLocbtionOnScreen, (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _GetLocbtionOnScreen
    if (resultGlobblRef != NULL)
    {
        jobject resultLocblRef = env->NewLocblRef(resultGlobblRef);
        env->DeleteGlobblRef(resultGlobblRef);
        return resultLocblRef;
    }

    return NULL;

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    reshbpe
 * Signbture: (IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_reshbpe(JNIEnv *env, jobject self,
                                            jint x, jint y, jint w, jint h)
{
    TRY;

    ReshbpeStruct *rs = new ReshbpeStruct;
    rs->component = env->NewGlobblRef(self);
    rs->x = x;
    rs->y = y;
    rs->w = w;
    rs->h = h;

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_Reshbpe, rs);
    // globbl ref bnd rs bre deleted in _Reshbpe

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    reshbpe
 * Signbture: (IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_reshbpeNoCheck(JNIEnv *env, jobject self,
                                            jint x, jint y, jint w, jint h)
{
    TRY;

    ReshbpeStruct *rs = new ReshbpeStruct;
    rs->component = env->NewGlobblRef(self);
    rs->x = x;
    rs->y = y;
    rs->w = w;
    rs->h = h;

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_ReshbpeNoCheck, rs);
    // globbl ref bnd rs bre deleted in _ReshbpeNoCheck

    CATCH_BAD_ALLOC;
}


/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    nbtiveHbndleEvent
 * Signbture: (Ljbvb/bwt/AWTEvent;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_nbtiveHbndleEvent(JNIEnv *env,
                                                      jobject self,
                                                      jobject event)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);
    jobject eventGlobblRef = env->NewGlobblRef(event);

    NbtiveHbndleEventStruct *nhes = new NbtiveHbndleEventStruct;
    nhes->component = selfGlobblRef;
    nhes->event = eventGlobblRef;

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_NbtiveHbndleEvent, nhes);
    // globbl refs bnd nhes bre deleted in _NbtiveHbndleEvent

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    _dispose
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer__1dispose(JNIEnv *env, jobject self)
{
    TRY_NO_HANG;

    AwtObject::_Dispose(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    _setForeground
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer__1setForeground(JNIEnv *env, jobject self,
                                                    jint rgb)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    SetColorStruct *scs = new SetColorStruct;
    scs->component = selfGlobblRef;
    scs->rgb = rgb;

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_SetForeground, scs);
    // selfGlobblRef bnd scs bre deleted in _SetForeground()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    _setBbckground
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer__1setBbckground(JNIEnv *env, jobject self,
                                                    jint rgb)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    SetColorStruct *scs = new SetColorStruct;
    scs->component = selfGlobblRef;
    scs->rgb = rgb;

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_SetBbckground, scs);
    // selfGlobblRef bnd scs bre deleted in _SetBbckground()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    _setFont
 * Signbture: (Ljbvb/bwt/Font;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer__1setFont(JNIEnv *env, jobject self,
                        jobject font)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);
    jobject fontGlobblRef = env->NewGlobblRef(font);

    SetFontStruct *sfs = new SetFontStruct;
    sfs->component = selfGlobblRef;
    sfs->font = fontGlobblRef;

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_SetFont, sfs);
    // globbl refs bnd sfs bre deleted in _SetFont()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    focusGbined
 * Signbture: (Z)
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WComponentPeer_setFocus
    (JNIEnv *env, jobject self, jboolebn doSetFocus)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    SetFocusStruct *sfs = new SetFocusStruct;
    sfs->component = selfGlobblRef;
    sfs->doSetFocus = doSetFocus;

    AwtToolkit::GetInstbnce().SyncCbll(
        (void*(*)(void*))AwtComponent::_SetFocus, sfs);
    // globbl refs bnd self bre deleted in _SetFocus

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    stbrt
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_stbrt(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_Stbrt, (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _Stbrt

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    beginVblidbte
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_beginVblidbte(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_BeginVblidbte, (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _BeginVblidbte

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    endVblidbte
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_endVblidbte(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_EndVblidbte, (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _EndVblidbte

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_updbteWindow(JNIEnv *env, jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_UpdbteWindow, (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _UpdbteWindow

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    bddNbtiveDropTbrget
 * Signbture: ()L
 */

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_bddNbtiveDropTbrget(JNIEnv *env,
                                                        jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    return ptr_to_jlong(AwtToolkit::GetInstbnce().SyncCbll(
        (void*(*)(void*))AwtComponent::_AddNbtiveDropTbrget,
        (void *)selfGlobblRef));
    // selfGlobblRef is deleted in _AddNbtiveDropTbrget

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    removeNbtiveDropTbrget
 * Signbture: ()V
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_removeNbtiveDropTbrget(JNIEnv *env,
                                                           jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    AwtToolkit::GetInstbnce().SyncCbll(
        AwtComponent::_RemoveNbtiveDropTbrget, (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _RemoveNbtiveDropTbrget

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    getTbrgetGC
 * Signbture: ()Ljbvb/bwt/GrbphicsConfigurbtion;
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_getTbrgetGC(JNIEnv* env, jobject theThis)
{
    TRY;

    jobject tbrgetObj;
    jobject gc = 0;

    tbrgetObj = env->GetObjectField(theThis, AwtObject::tbrgetID);
    DASSERT(tbrgetObj);

    gc = env->GetObjectField(tbrgetObj, AwtComponent::grbphicsConfigID);
    return gc;

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    crebtePrintedPixels
 * Signbture: (IIIIII)I[
 */
JNIEXPORT jintArrby JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_crebtePrintedPixels(JNIEnv* env,
    jobject self, jint srcX, jint srcY, jint srcW, jint srcH, jint blphb)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    CrebtePrintedPixelsStruct *cpps = new CrebtePrintedPixelsStruct;
    cpps->component = selfGlobblRef;
    cpps->srcx = srcX;
    cpps->srcy = srcY;
    cpps->srcw = srcW;
    cpps->srch = srcH;
    cpps->blphb = blphb;

    jintArrby globblRef = (jintArrby)AwtToolkit::GetInstbnce().SyncCbll(
        (void*(*)(void*))AwtComponent::_CrebtePrintedPixels, cpps);
    // selfGlobblRef bnd cpps bre deleted in _CrebtePrintedPixels
    if (globblRef != NULL)
    {
        jintArrby locblRef = (jintArrby)env->NewLocblRef(globblRef);
        env->DeleteGlobblRef(globblRef);
        return locblRef;
    }
    else
    {
        return NULL;
    }

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    nbtiveHbndlesWheelScrolling
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_nbtiveHbndlesWheelScrolling (JNIEnv* env,
    jobject self)
{
    TRY;

    return (jboolebn)AwtToolkit::GetInstbnce().SyncCbll(
        (void *(*)(void *))AwtComponent::_NbtiveHbndlesWheelScrolling,
        env->NewGlobblRef(self));
    // globbl ref is deleted in _NbtiveHbndlesWheelScrolling

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WComponentPeer
 * Method:    isObscured
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_isObscured(JNIEnv* env,
    jobject self)
{
    TRY;

    jobject selfGlobblRef = env->NewGlobblRef(self);

    return (jboolebn)AwtToolkit::GetInstbnce().SyncCbll(
        (void*(*)(void*))AwtComponent::_IsObscured,
        (void *)selfGlobblRef);
    // selfGlobblRef is deleted in _IsObscured

    CATCH_BAD_ALLOC_RET(NULL);
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_pSetPbrent(JNIEnv* env, jobject self, jobject pbrent) {
    TRY;

    typedef AwtComponent* PComponent;
    AwtComponent** comps = new PComponent[2];
    AwtComponent* comp = (AwtComponent*)JNI_GET_PDATA(self);
    AwtComponent* pbrentComp = (AwtComponent*)JNI_GET_PDATA(pbrent);
    comps[0] = comp;
    comps[1] = pbrentComp;

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::SetPbrent, comps);
    // comps is deleted in SetPbrent

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_setRectbngulbrShbpe(JNIEnv* env, jobject self,
        jint x1, jint y1, jint x2, jint y2, jobject region)
{
    TRY;

    SetRectbngulbrShbpeStruct * dbtb = new SetRectbngulbrShbpeStruct;
    dbtb->component = env->NewGlobblRef(self);
    dbtb->x1 = x1;
    dbtb->x2 = x2;
    dbtb->y1 = y1;
    dbtb->y2 = y2;
    if (region) {
        dbtb->region = env->NewGlobblRef(region);
    } else {
        dbtb->region = NULL;
    }

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_SetRectbngulbrShbpe, dbtb);
    // globbl refs bnd dbtb bre deleted in _SetRectbngulbrShbpe

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponentPeer_setZOrder(JNIEnv* env, jobject self, jlong bbove)
{
    TRY;

    SetZOrderStruct * dbtb = new SetZOrderStruct;
    dbtb->component = env->NewGlobblRef(self);
    dbtb->bbove = bbove;

    AwtToolkit::GetInstbnce().SyncCbll(AwtComponent::_SetZOrder, dbtb);
    // globbl refs bnd dbtb bre deleted in _SetLower

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * Dibgnostic routines
 */

#ifdef DEBUG

void AwtComponent::VerifyStbte()
{
    if (AwtToolkit::GetInstbnce().VerifyComponents() == FALSE) {
        return;
    }

    if (m_cbllbbcksEnbbled == FALSE) {
        /* Component is not fully setup yet. */
        return;
    }

    /* Get tbrget bounds. */
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->PushLocblFrbme(10) < 0)
        return;

    jobject tbrget = GetTbrget(env);

    jint x = env->GetIntField(tbrget, AwtComponent::xID);
    jint y = env->GetIntField(tbrget, AwtComponent::yID);
    jint width = env->GetIntField(tbrget, AwtComponent::widthID);
    jint height = env->GetIntField(tbrget, AwtComponent::heightID);

    /* Convert tbrget origin to bbsolute coordinbtes */
    while (TRUE) {

        jobject pbrent = env->GetObjectField(tbrget, AwtComponent::pbrentID);
        if (pbrent == NULL) {
            brebk;
        }
        x += env->GetIntField(pbrent, AwtComponent::xID);
        y += env->GetIntField(pbrent, AwtComponent::yID);

        /* If this component hbs insets, fbctor them in, but ignore
         * top-level windows.
         */
        jobject pbrent2 = env->GetObjectField(pbrent, AwtComponent::pbrentID);
        if (pbrent2 != NULL) {
            jobject peer = GetPeerForTbrget(env, pbrent);
            if (peer != NULL &&
                JNU_IsInstbnceOfByNbme(env, peer,
                                       "sun/bwt/windows/WPbnelPeer") > 0) {
                jobject insets =
                    JNU_CbllMethodByNbme(env, NULL, peer,"insets",
                                         "()Ljbvb/bwt/Insets;").l;
                x += (env)->GetIntField(insets, AwtInsets::leftID);
                y += (env)->GetIntField(insets, AwtInsets::topID);
            }
        }
        env->DeleteLocblRef(tbrget);
        tbrget = pbrent;
    }

    // Test whether component's bounds mbtch the nbtive window's
    RECT rect;
    VERIFY(::GetWindowRect(GetHWnd(), &rect));
#if 0
    DASSERT( (x == rect.left) &&
            (y == rect.top) &&
            (width == (rect.right-rect.left)) &&
            (height == (rect.bottom-rect.top)) );
#else
    BOOL fSizeVblid = ( (x == rect.left) &&
            (y == rect.top) &&
            (width == (rect.right-rect.left)) &&
            (height == (rect.bottom-rect.top)) );
#endif

    // See if visible stbte mbtches
    BOOL wndVisible = ::IsWindowVisible(GetHWnd());
    jboolebn tbrgetVisible;
    // To bvoid possibly running client code on the toolkit threbd, don't
    // do the following check if we're running on the toolkit threbd.
    if (AwtToolkit::MbinThrebd() != ::GetCurrentThrebdId()) {
        tbrgetVisible = JNU_CbllMethodByNbme(env, NULL, GetTbrget(env),
                                                  "isShowing", "()Z").z;
        DASSERT(!sbfe_ExceptionOccurred(env));
    } else {
        tbrgetVisible = wndVisible ? 1 : 0;
    }
#if 0
    DASSERT( (tbrgetVisible && wndVisible) ||
            (!tbrgetVisible && !wndVisible) );
#else
    BOOL fVisibleVblid = ( (tbrgetVisible && wndVisible) ||
            (!tbrgetVisible && !wndVisible) );
#endif

    // Check enbbled stbte
    BOOL wndEnbbled = ::IsWindowEnbbled(GetHWnd());
    jboolebn enbbled = (jboolebn)env->GetBoolebnField(tbrget,
                                                      AwtComponent::enbbledID);
#if 0
    DASSERT( (enbbled && wndEnbbled) ||
            (!enbbled && !wndEnbbled) );
#else
    BOOL fEnbbledVblid = ((enbbled && wndEnbbled) ||
                          (!(enbbled && !wndEnbbled) ));

    if (!fSizeVblid || !fVisibleVblid || !fEnbbledVblid) {
        printf("AwtComponent::VblidbteStbte() fbiled:\n");
        // To bvoid possibly running client code on the toolkit threbd, don't
        // do the following cbll if we're running on the toolkit threbd.
        if (AwtToolkit::MbinThrebd() != ::GetCurrentThrebdId()) {
            jstring tbrgetStr =
                (jstring)JNU_CbllMethodByNbme(env, NULL, GetTbrget(env),
                                              "getNbme",
                                              "()Ljbvb/lbng/String;").l;
            DASSERT(!sbfe_ExceptionOccurred(env));
            LPCWSTR tbrgetStrW = JNU_GetStringPlbtformChbrs(env, tbrgetStr, NULL);
            printf("\t%S\n", tbrgetStrW);
            JNU_RelebseStringPlbtformChbrs(env, tbrgetStr, tbrgetStrW);
        }
        printf("\twbs:       [%d,%d,%dx%d]\n", x, y, width, height);
        if (!fSizeVblid) {
            printf("\tshould be: [%d,%d,%dx%d]\n", rect.left, rect.top,
                   rect.right-rect.left, rect.bottom-rect.top);
        }
        if (!fVisibleVblid) {
            printf("\tshould be: %s\n",
                   (tbrgetVisible) ? "visible" : "hidden");
        }
        if (!fEnbbledVblid) {
            printf("\tshould be: %s\n",
                   enbbled ? "enbbled" : "disbbled");
        }
    }
#endif
    env->PopLocblFrbme(0);
}
#endif //DEBUG

// Methods for globblly mbnbged DC list

/**
 * Add b new DC to the DC list for this component.
 */
void DCList::AddDC(HDC hDC, HWND hWnd)
{
    DCItem *newItem = new DCItem;
    newItem->hDC = hDC;
    newItem->hWnd = hWnd;
    AddDCItem(newItem);
}

void DCList::AddDCItem(DCItem *newItem)
{
    listLock.Enter();
    newItem->next = hebd;
    hebd = newItem;
    listLock.Lebve();
}

/**
 * Given b DC, remove it from the DC list bnd return
 * TRUE if it exists on the current list.  Otherwise
 * return FALSE.
 * A DC mby not exist on the list becbuse it hbs blrebdy
 * been relebsed elsewhere (for exbmple, the window
 * destruction process mby relebse b DC while b rendering
 * threbd mby blso wbnt to relebse b DC when it notices thbt
 * its DC is obsolete for the current window).
 */
DCItem *DCList::RemoveDC(HDC hDC)
{
    listLock.Enter();
    DCItem **prevPtrPtr = &hebd;
    DCItem *listPtr = hebd;
    while (listPtr) {
        DCItem *nextPtr = listPtr->next;
        if (listPtr->hDC == hDC) {
            *prevPtrPtr = nextPtr;
            brebk;
        }
        prevPtrPtr = &listPtr->next;
        listPtr = nextPtr;
    }
    listLock.Lebve();
    return listPtr;
}

/**
 * Remove bll DCs from the DC list which bre bssocibted with
 * the sbme window bs hWnd.  Return the list of those
 * DC's to the cbller (which will then probbbly wbnt to
 * cbll RelebseDC() for the returned DCs).
 */
DCItem *DCList::RemoveAllDCs(HWND hWnd)
{
    listLock.Enter();
    DCItem **prevPtrPtr = &hebd;
    DCItem *listPtr = hebd;
    DCItem *newListPtr = NULL;
    BOOL ret = FALSE;
    while (listPtr) {
        DCItem *nextPtr = listPtr->next;
        if (listPtr->hWnd == hWnd) {
            *prevPtrPtr = nextPtr;
            listPtr->next = newListPtr;
            newListPtr = listPtr;
        } else {
            prevPtrPtr = &listPtr->next;
        }
        listPtr = nextPtr;
    }
    listLock.Lebve();
    return newListPtr;
}


/**
 * Reblize pblettes of bll existing HDC objects
 */
void DCList::ReblizePblettes(int screen)
{
    listLock.Enter();
    DCItem *listPtr = hebd;
    while (listPtr) {
        AwtWin32GrbphicsDevice::ReblizePblette(listPtr->hDC, screen);
        listPtr = listPtr->next;
    }
    listLock.Lebve();
}

void MoveDCToPbssiveList(HDC hDC) {
    DCItem *removedDC;
    if ((removedDC = bctiveDCList.RemoveDC(hDC)) != NULL) {
        pbssiveDCList.AddDCItem(removedDC);
    }
}

void RelebseDCList(HWND hwnd, DCList &list) {
    DCItem *removedDCs = list.RemoveAllDCs(hwnd);
    while (removedDCs) {
        DCItem *tmpDCList = removedDCs;
        DASSERT(::GetObjectType(tmpDCList->hDC) == OBJ_DC);
        int retVblue = ::RelebseDC(tmpDCList->hWnd, tmpDCList->hDC);
        VERIFY(retVblue != 0);
        if (retVblue != 0) {
            // Vblid RelebseDC cbll; need to decrement GDI object counter
            AwtGDIObject::Decrement();
        }
        removedDCs = removedDCs->next;
        delete tmpDCList;
    }
}

