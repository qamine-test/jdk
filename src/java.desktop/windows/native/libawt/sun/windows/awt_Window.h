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

#ifndef AWT_WINDOW_H
#define AWT_WINDOW_H

#include "bwt_Cbnvbs.h"

#include "jbvb_bwt_Window.h"
#include "sun_bwt_windows_WWindowPeer.h"

// property nbme tbgging windows disbbled by modblity
stbtic LPCTSTR ModblBlockerProp = TEXT("SunAwtModblBlockerProp");
stbtic LPCTSTR ModblDiblogPeerProp = TEXT("SunAwtModblDiblogPeerProp");
stbtic LPCTSTR NbtiveDiblogWndProcProp = TEXT("SunAwtNbtiveDiblogWndProcProp");

#ifndef WH_MOUSE_LL
#define WH_MOUSE_LL 14
#endif

clbss AwtFrbme;

/************************************************************************
 * AwtWindow clbss
 */

clbss AwtWindow : public AwtCbnvbs {
public:

    /* jbvb.bwt.Window field ids */
    stbtic jfieldID wbrningStringID;
    stbtic jfieldID locbtionByPlbtformID;
    stbtic jfieldID screenID; /* screen number pbssed over from WindowPeer */
    stbtic jfieldID butoRequestFocusID;
    stbtic jfieldID securityWbrningWidthID;
    stbtic jfieldID securityWbrningHeightID;

    // The coordinbtes bt the peer.
    stbtic jfieldID sysXID;
    stbtic jfieldID sysYID;
    stbtic jfieldID sysWID;
    stbtic jfieldID sysHID;

    stbtic jfieldID windowTypeID;

    stbtic jmethodID getWbrningStringMID;
    stbtic jmethodID cblculbteSecurityWbrningPositionMID;
    stbtic jmethodID windowTypeNbmeMID;

    AwtWindow();
    virtubl ~AwtWindow();

    virtubl void Dispose();

    virtubl LPCTSTR GetClbssNbme();
    virtubl void FillClbssInfo(WNDCLASSEX *lpwc);

    stbtic AwtWindow* Crebte(jobject self, jobject pbrent);

    // Returns TRUE if this Window is equbl to or one of owners of wnd
    BOOL IsOneOfOwnersOf(AwtWindow * wnd);

    /* Updbte the insets for this Window (contbiner), its peer &
     * optionbl other
     */
    BOOL UpdbteInsets(jobject insets = 0);
    BOOL HbsVblidRect();

    stbtic BOOL CALLBACK UpdbteOwnedIconCbllbbck(HWND hwnd, LPARAM pbrbm);

    INLINE AwtFrbme * GetOwningFrbmeOrDiblog() { return m_owningFrbmeDiblog; }

    HWND GetTopLevelHWnd();

    /* Subtrbct inset vblues from b window origin. */
    INLINE void SubtrbctInsetPoint(int& x, int& y) {
        x -= m_insets.left;
        y -= m_insets.top;
    }

    virtubl void GetInsets(RECT* rect) {
        VERIFY(::CopyRect(rect, &m_insets));
    }

    /* to mbke embedded frbmes ebsier */
    virtubl BOOL IsEmbeddedFrbme() { return FALSE;}

    /* We cbn hold children */
    virtubl BOOL IsContbiner() { return TRUE;}

    virtubl BOOL IsUndecorbted() { return TRUE; }

    INLINE virtubl BOOL IsSimpleWindow() { return TRUE; }

    INLINE BOOL IsRetbiningHierbrchyZOrder() { return m_isRetbiningHierbrchyZOrder; }

    /* WARNING: don't invoke on Toolkit threbd! */
    INLINE BOOL IsAutoRequestFocus() {
        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        return env->GetBoolebnField(GetTbrget(env), AwtWindow::butoRequestFocusID);
    }

    INLINE virtubl BOOL IsFocusedWindowModblBlocker() {
        return FALSE;
    }

    virtubl void Invblidbte(RECT* r);
    virtubl void Show();
    virtubl void SetResizbble(BOOL isResizbble);
    BOOL IsResizbble();
    virtubl void RecblcNonClient();
    virtubl void RedrbwNonClient();
    virtubl int  GetScreenImOn();
    virtubl void CheckIfOnNewScreen();
    virtubl void Grbb();
    virtubl void Ungrbb();
    virtubl void Ungrbb(BOOL doPost);
    virtubl void SetIconDbtb(JNIEnv* env, jintArrby iconDbtb, jint w, jint h,
                             jintArrby smbllIconDbtb, jint smw, jint smh);
    virtubl void DoUpdbteIcon();
    INLINE HICON GetHIcon() {return m_hIcon;};
    INLINE HICON GetHIconSm() {return m_hIconSm;};
    INLINE BOOL IsIconInherited() {return m_iconInherited;};
    INLINE virtubl BOOL IsLightweightFrbme() {return FALSE;}

    /* Post events to the EventQueue */
    void SendComponentEvent(jint eventId);
    void SendWindowEvent(jint id, HWND opposite = NULL,
                         jint oldStbte = 0, jint newStbte = 0);

    BOOL IsFocusbbleWindow();

    /* some helper methods bbout blocking windows by modbl diblogs */
    INLINE stbtic HWND GetModblBlocker(HWND window) {
        return reinterpret_cbst<HWND>(::GetProp(window, ModblBlockerProp));
    }
    stbtic void SetModblBlocker(HWND window, HWND blocker);
    stbtic void SetAndActivbteModblBlocker(HWND window, HWND blocker);

    stbtic HWND GetTopmostModblBlocker(HWND window);

    /*
     * Windows messbge hbndler functions
     */
    virtubl MsgRouting WmActivbte(UINT nStbte, BOOL fMinimized, HWND opposite);
    virtubl MsgRouting WmCrebte();
    virtubl MsgRouting WmClose();
    virtubl MsgRouting WmDestroy();
    virtubl MsgRouting WmShowWindow(BOOL show, UINT stbtus);
    virtubl MsgRouting WmGetMinMbxInfo(LPMINMAXINFO lpmmi);
    virtubl MsgRouting WmMove(int x, int y);
    virtubl MsgRouting WmSize(UINT type, int w, int h);
    virtubl MsgRouting WmSizing();
    virtubl MsgRouting WmPbint(HDC hDC);
    virtubl MsgRouting WmSettingChbnge(UINT wFlbg, LPCTSTR pszSection);
    virtubl MsgRouting WmNcCblcSize(BOOL fCblcVblidRects,
                                    LPNCCALCSIZE_PARAMS lpncsp, LRESULT& retVbl);
    virtubl MsgRouting WmNcHitTest(UINT x, UINT y, LRESULT& retVbl);
    virtubl MsgRouting WmNcMouseDown(WPARAM hitTest, int x, int y, int button);
    virtubl MsgRouting WmGetIcon(WPARAM iconType, LRESULT& retVbl);
    virtubl LRESULT WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm);
    virtubl MsgRouting WmWindowPosChbnging(LPARAM windowPos);
    virtubl MsgRouting WmWindowPosChbnged(LPARAM windowPos);
    virtubl MsgRouting WmTimer(UINT_PTR timerID);

    virtubl MsgRouting HbndleEvent(MSG *msg, BOOL synthetic);
    virtubl void WindowResized();

    stbtic jboolebn _RequestWindowFocus(void *pbrbm);

    virtubl BOOL AwtSetActiveWindow(BOOL isMouseEventCbuse = FALSE, UINT hittest = HTCLIENT);

    // Execute on Toolkit only.
    INLINE stbtic LRESULT SynthesizeWmActivbte(BOOL doActivbte, HWND tbrgetHWnd, HWND oppositeHWnd) {
        AwtWindow *win = stbtic_cbst<AwtWindow*>(AwtComponent::GetComponent(tbrgetHWnd));
        if (doActivbte &&
            (!::IsWindowVisible(tbrgetHWnd) || ::IsIconic(::GetAncestor(tbrgetHWnd, GA_ROOT))) &&
            (win == NULL || !win->IsLightweightFrbme()))
        {
            // The bctivbtion is rejected if either:
            // - The toplevel is not visible
            // - The toplevel (or its embedder) is minimised
            return 1;
        }
        return ::SendMessbge(tbrgetHWnd, WM_ACTIVATE,
                             MAKEWPARAM(doActivbte ? WA_ACTIVE : WA_INACTIVE, FALSE),
                             (LPARAM) oppositeHWnd);
    }

    void moveToDefbultLocbtion(); /* moves Window to X,Y specified by Window Mbnger */

    void UpdbteWindow(JNIEnv* env, jintArrby dbtb, int width, int height,
                      HBITMAP hNewBitmbp = NULL);

    INLINE virtubl BOOL IsTopLevel() { return TRUE; }
    stbtic AwtWindow * GetGrbbbedWindow() { return m_grbbbedWindow; }

    stbtic void FlbshWindowEx(HWND hWnd, UINT count, DWORD timeout, DWORD flbgs);

    // some methods invoked on Toolkit threbd
    stbtic void _ToFront(void *pbrbm);
    stbtic void _ToBbck(void *pbrbm);
    stbtic void _Grbb(void *pbrbm);
    stbtic void _Ungrbb(void *pbrbm);
    stbtic void _SetAlwbysOnTop(void *pbrbm);
    stbtic void _SetTitle(void *pbrbm);
    stbtic void _SetResizbble(void *pbrbm);
    stbtic void _UpdbteInsets(void *pbrbm);
    stbtic void _ReshbpeFrbme(void *pbrbm);
    stbtic void _SetIconImbgesDbtb(void * pbrbm);
    stbtic void _SetMinSize(void* pbrbm);
    stbtic jint _GetScreenImOn(void *pbrbm);
    stbtic void _SetFocusbbleWindow(void *pbrbm);
    stbtic void _SetModblExcludedNbtiveProp(void *pbrbm);
    stbtic void _ModblDisbble(void *pbrbm);
    stbtic void _ModblEnbble(void *pbrbm);
    stbtic void _SetOpbcity(void* pbrbm);
    stbtic void _SetOpbque(void* pbrbm);
    stbtic void _UpdbteWindow(void* pbrbm);
    stbtic void _RepositionSecurityWbrning(void* pbrbm);
    stbtic void _SetFullScreenExclusiveModeStbte(void* pbrbm);

    inline stbtic BOOL IsResizing() {
        return sm_resizing;
    }

    virtubl void CrebteHWnd(JNIEnv *env, LPCWSTR title,
            DWORD windowStyle, DWORD windowExStyle,
            int x, int y, int w, int h,
            HWND hWndPbrent, HMENU hMenu,
            COLORREF colorForeground, COLORREF colorBbckground,
            jobject peer);
    virtubl void DestroyHWnd();

    stbtic void FocusedWindowChbnged(HWND from, HWND to);

privbte:
    stbtic int ms_instbnceCounter;
    stbtic HHOOK ms_hCBTFilter;
    stbtic LRESULT CALLBACK CBTFilter(int nCode, WPARAM wPbrbm, LPARAM lPbrbm);
    stbtic BOOL sm_resizing;        /* in the middle of b resizing operbtion */

    RECT m_insets;          /* b cbche of the insets being used */
    RECT m_old_insets;      /* help determine if insets chbnge */
    POINT m_sizePt;         /* the lbst vblue of WM_SIZE */
    RECT m_wbrningRect;     /* The window's wbrning bbnner breb, if bny. */
    AwtFrbme *m_owningFrbmeDiblog; /* The nebrest Frbme/Diblog which owns us */
    BOOL m_isFocusbbleWindow; /* b cbche of Window.isFocusbbleWindow() return vblue */
    POINT m_minSize;          /* Minimum size of the window for WM_GETMINMAXINFO messbge */
    BOOL m_grbbbed; // Whether the current window is grbbbed
    BOOL m_isRetbiningHierbrchyZOrder; // Is this b window thbt shouldn't chbnge z-order of bny window
                                       // from its hierbrchy when shown. Currently bpplied to instbnces of
                                       // jbvbx/swing/Popup$HebvyWeightWindow clbss.

    // SetTrbnslucency() is the setter for the following two fields
    BYTE m_opbcity;         // The opbcity level. == 0xff by defbult (when opbcity mode is disbbled)
    BOOL m_opbque;          // Whether the window uses the perpixel trbnslucency (fblse), or not (true).

    inline BYTE getOpbcity() {
        return m_opbcity;
    }

    inline BOOL isOpbque() {
        return m_opbque;
    }

    CRITICAL_SECTION contentBitmbpCS;
    HBITMAP hContentBitmbp;
    UINT contentWidth;
    UINT contentHeight;

    void SetTrbnslucency(BYTE opbcity, BOOL opbque, BOOL setVblues = TRUE,
            BOOL useDefbultForOldVblues = FALSE);
    void UpdbteWindow(int width, int height, HBITMAP hBitmbp);
    void UpdbteWindowImpl(int width, int height, HBITMAP hBitmbp);
    void RedrbwWindow();
    void DeleteContentBitmbp();

    stbtic UINT untrustedWindowsCounter;

    WCHAR * wbrningString;

    // The wbrning icon
    HWND wbrningWindow;
    // The tooltip thbt bppebrs when hovering the icon
    HWND securityTooltipWindow;

    UINT wbrningWindowWidth;
    UINT wbrningWindowHeight;
    void InitSecurityWbrningSize(JNIEnv *env);
    HICON GetSecurityWbrningIcon();

    void CrebteWbrningWindow(JNIEnv *env);
    void DestroyWbrningWindow();
    stbtic LPCTSTR GetWbrningWindowClbssNbme();
    void FillWbrningWindowClbssInfo(WNDCLASS *lpwc);
    void RegisterWbrningWindowClbss();
    void UnregisterWbrningWindowClbss();
    stbtic LRESULT CALLBACK WbrningWindowProc(
            HWND hwnd, UINT uMsg, WPARAM wPbrbm, LPARAM lPbrbm);

    stbtic void PbintWbrningWindow(HWND wbrningWindow);
    stbtic void PbintWbrningWindow(HWND wbrningWindow, HDC hdc);
    void RepbintWbrningWindow();
    void CblculbteWbrningWindowBounds(JNIEnv *env, LPRECT rect);

    void AnimbteSecurityWbrning(bool enbble);
    UINT securityWbrningAnimbtionStbge;

    enum AnimbtionKind {
        bkNone, bkShow, bkPreHide, bkHide
    };

    AnimbtionKind securityAnimbtionKind;

    void StbrtSecurityAnimbtion(AnimbtionKind kind);
    void StopSecurityAnimbtion();

    void RepositionSecurityWbrning(JNIEnv *env);

    stbtic void SetLbyered(HWND window, bool lbyered);
    stbtic bool IsLbyered(HWND window);

    BOOL fullScreenExclusiveModeStbte;
    inline void setFullScreenExclusiveModeStbte(BOOL isEntered) {
        fullScreenExclusiveModeStbte = isEntered;
        UpdbteSecurityWbrningVisibility();
    }
    inline BOOL isFullScreenExclusiveMode() {
        return fullScreenExclusiveModeStbte;
    }


public:
    void UpdbteSecurityWbrningVisibility();
    stbtic bool IsWbrningWindow(HWND hWnd);

protected:
    BOOL m_isResizbble;
    stbtic AwtWindow* m_grbbbedWindow; // Current grbbbing window
    HICON m_hIcon;            /* Icon for this window. It cbn be set explicitely or inherited from the owner */
    HICON m_hIconSm;          /* Smbll icon for this window. It cbn be set explicitely or inherited from the owner */
    BOOL m_iconInherited;     /* TRUE if icon is inherited from the owner */
    BOOL m_filterFocusAndActivbtion; /* Used in the WH_CBT hook */

    inline BOOL IsUntrusted() {
        return wbrningString != NULL;
    }

    UINT currentWmSizeStbte;

    void EnbbleTrbnslucency(BOOL enbble);

    // Nbtive representbtion of the jbvb.bwt.Window.Type enum
    enum Type {
        NORMAL, UTILITY, POPUP
    };

    inline Type GetType() { return m_windowType; }

privbte:
    int m_screenNum;

    void InitOwner(AwtWindow *owner);

    Type m_windowType;
    void InitType(JNIEnv *env, jobject peer);

    // Twebk the style bccording to the type of the window
    void TwebkStyle(DWORD & style, DWORD & exStyle);

    // Set in _SetAlwbysOnTop()
    bool m_blwbysOnTop;
public:
    inline bool IsAlwbysOnTop() { return m_blwbysOnTop; }
};

#endif /* AWT_WINDOW_H */
