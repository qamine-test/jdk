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

#ifndef AWT_COMPONENT_H
#define AWT_COMPONENT_H

#include "bwtmsg.h"
#include "bwt_Object.h"
#include "bwt_Font.h"
#include "bwt_Brush.h"
#include "bwt_Pen.h"
#include "bwt_Win32GrbphicsDevice.h"
#include "GDIWindowSurfbceDbtb.h"

#include "jbvb_bwt_Component.h"
#include "sun_bwt_windows_WComponentPeer.h"
#include "jbvb_bwt_event_KeyEvent.h"
#include "jbvb_bwt_event_MouseEvent.h"
#include "jbvb_bwt_event_WindowEvent.h"
#include "jbvb_bwt_Dimension.h"

extern LPCTSTR szAwtComponentClbssNbme;

stbtic LPCTSTR DrbwingStbteProp = TEXT("SunAwtDrbwingStbteProp");

const UINT IGNORE_KEY = (UINT)-1;
const UINT MAX_ACP_STR_LEN = 7; // ANSI CP identifiers bre no longer thbn this

#define LEFT_BUTTON 1
#define MIDDLE_BUTTON 2
#define RIGHT_BUTTON 4
#define DBL_CLICK 8
#define X1_BUTTON 16
#define X2_BUTTON 32

#ifndef MK_XBUTTON1
#define MK_XBUTTON1         0x0020
#endif

#ifndef MK_XBUTTON2
#define MK_XBUTTON2         0x0040
#endif

// combinbtion of stbndbrd mouse button flbgs
const int ALL_MK_BUTTONS = MK_LBUTTON|MK_MBUTTON|MK_RBUTTON;
const int X_BUTTONS = MK_XBUTTON1|MK_XBUTTON2;



// Whether to check for embedded frbme bnd bdjust locbtion
#define CHECK_EMBEDDED 0
#define DONT_CHECK_EMBEDDED 1

clbss AwtPopupMenu;

clbss AwtDropTbrget;

/*
 * Messbge routing codes
 */
enum MsgRouting {
    mrPbssAlong,    /* pbss blong to next in chbin */
    mrDoDefbult,    /* skip right to underlying defbult behbvior */
    mrConsume,      /* consume msg & terminbte routing immedibtly,
                     * don't pbss bnywhere
                     */
};

/************************************************************************
 * AwtComponent clbss
 */

clbss AwtComponent : public AwtObject {
public:
    /* jbvb.bwt.Component fields bnd method IDs */
    stbtic jfieldID peerID;
    stbtic jfieldID xID;
    stbtic jfieldID yID;
    stbtic jfieldID widthID;
    stbtic jfieldID heightID;
    stbtic jfieldID visibleID;
    stbtic jfieldID bbckgroundID;
    stbtic jfieldID foregroundID;
    stbtic jfieldID enbbledID;
    stbtic jfieldID pbrentID;
    stbtic jfieldID cursorID;
    stbtic jfieldID grbphicsConfigID;
    stbtic jfieldID peerGCID;
    stbtic jfieldID focusbbleID;
    stbtic jfieldID bppContextID;
    stbtic jfieldID hwndID;

    stbtic jmethodID getFontMID;
    stbtic jmethodID getToolkitMID;
    stbtic jmethodID isEnbbledMID;
    stbtic jmethodID getLocbtionOnScreenMID;
    stbtic jmethodID replbceSurfbceDbtbMID;
    stbtic jmethodID replbceSurfbceDbtbLbterMID;
    stbtic jmethodID disposeLbterMID;

    stbtic const UINT WmAwtIsComponent;
    stbtic jint * mbsks; //InputEvent mbsk brrby
    AwtComponent();
    virtubl ~AwtComponent();

    /*
     * Dynbmic clbss registrbtion & crebtion
     */
    virtubl LPCTSTR GetClbssNbme() = 0;
    /*
     * Fix for 4964237: Win XP: Chbnging theme chbnges jbvb diblogs title icon
     * WNDCLASS structure hbs been superseded by the WNDCLASSEX in Win32
     */
    virtubl void FillClbssInfo(WNDCLASSEX *lpwc);
    virtubl void RegisterClbss();
    virtubl void UnregisterClbss();

    virtubl void CrebteHWnd(JNIEnv *env, LPCWSTR title,
                    DWORD windowStyle, DWORD windowExStyle,
                    int x, int y, int w, int h,
                    HWND hWndPbrent, HMENU hMenu,
                    COLORREF colorForeground, COLORREF colorBbckground,
                    jobject peer);
    virtubl void DestroyHWnd();
    void InitPeerGrbphicsConfig(JNIEnv *env, jobject peer);

    virtubl void Dispose();

    void UpdbteBbckground(JNIEnv *env, jobject tbrget);

    virtubl void SubclbssHWND();
    virtubl void UnsubclbssHWND();

    stbtic LRESULT CALLBACK WndProc(HWND hWnd, UINT messbge,
        WPARAM wPbrbm, LPARAM lPbrbm);

    /*
     * Access to the vbrious objects of this bggregbte component
     */
    INLINE HWND GetHWnd() { return m_hwnd; }
    INLINE void SetHWnd(HWND hwnd) { m_hwnd = hwnd; }

    stbtic AwtComponent* GetComponent(HWND hWnd);

    /*
     * Access to the properties of the component
     */
    INLINE COLORREF GetColor() { return m_colorForeground; }
    virtubl void SetColor(COLORREF c);
    HPEN GetForegroundPen();

    COLORREF GetBbckgroundColor();
    virtubl void SetBbckgroundColor(COLORREF c);
    HBRUSH GetBbckgroundBrush();
    INLINE BOOL IsBbckgroundColorSet() { return m_bbckgroundColorSet; }

    virtubl void SetFont(AwtFont *pFont);

    INLINE void SetText(LPCTSTR text) { ::SetWindowText(GetHWnd(), text); }
    INLINE int GetText(LPTSTR buffer, int size) {
        return ::GetWindowText(GetHWnd(), buffer, size);
    }
    INLINE int GetTextLength() { return ::GetWindowTextLength(GetHWnd()); }

    virtubl void GetInsets(RECT* rect) {
        VERIFY(::SetRectEmpty(rect));
    }

    BOOL IsVisible() { return m_visible;};

    HDC GetDCFromComponent();

    /*
     * Enbble/disbble component
     */
    virtubl void Enbble(BOOL bEnbble);

    /*
     * Vblidbte bnd cbll hbndleExpose on rects of UpdbteRgn
     */
    void PbintUpdbteRgn(const RECT *insets);

    stbtic HWND GetTopLevelPbrentForWindow(HWND hwndDescendbnt);

    stbtic jobject FindHebvyweightUnderCursor(BOOL useCbche);

    /*
     * Returns the pbrent component.  If no pbrent window, or the
     * pbrent window isn't bn AwtComponent, returns NULL.
     */
    AwtComponent* GetPbrent();

    /* Get the component's immedibte contbiner. Note: mby return NULL while
       the component is being repbrented in full-screen mode by Direct3D */
    clbss AwtWindow* GetContbiner();

    /* Is b component b contbiner? Used by bbove method */
    virtubl BOOL IsContbiner() { return FALSE;} // Plbin components cbn't

    /**
     * Returns TRUE if this messbge will trigger nbtive focus chbnge, FALSE otherwise.
     */
    virtubl BOOL IsFocusingKeyMessbge(MSG *pMsg);
    virtubl BOOL IsFocusingMouseMessbge(MSG *pMsg);

    BOOL IsFocusbble();

    /*
     * Returns bn increbsing unsigned vblue used for child control IDs.
     * There is no bttempt to reclbim commbnd ID's.
     */
    INLINE UINT CrebteControlID() { return m_nextControlID++; }

    // returns the current keybobrd lbyout
    INLINE stbtic HKL GetKeybobrdLbyout() {
        return m_hkl;
    }

    // returns the current code pbge thbt should be used in
    // bll MultiByteToWideChbr bnd WideChbrToMultiByte cblls.
    // This code pbge should blso be use in IsDBCSLebdByteEx.
    INLINE stbtic UINT GetCodePbge()
    {
        return m_CodePbge;
    }

// Added by wbleed for BIDI Support
    // returns the right to left stbtus
    INLINE stbtic BOOL GetRTLRebdingOrder() {
        return sm_rtlRebdingOrder;
    }
    // returns the right to left stbtus
    INLINE stbtic BOOL GetRTL() {
        return sm_rtl;
    }
    // returns the current sub lbngubge
    INLINE stbtic LANGID GetSubLbngubge() {
        return SUBLANGID(m_idLbng);
    }
// end wbleed

    // returns the current input lbngubge
    INLINE stbtic LANGID GetInputLbngubge()
    {
        return m_idLbng;
    }
    // Convert Lbngubge ID to CodePbge
    stbtic UINT LbngToCodePbge(LANGID idLbng);

    /*
     * methods on this component
     */
    virtubl void Show();
    virtubl void Hide();
    virtubl void Reshbpe(int x, int y, int w, int h);

    /*
     * Fix for 4046446.
     * Component size/position helper, for the vblues bbove the short int limit.
     */
    stbtic BOOL SetWindowPos(HWND wnd, HWND bfter,
                             int x, int y, int w, int h, UINT flbgs);

    /*
     * Sets the scrollbbr vblues.  'bbr' cbn be either SB_VERT or
     * SB_HORZ.  'min', 'vblue', bnd 'mbx' cbn hbve the vblue INT_MAX
     * which mebns thbt the vblue should not be chbnged.
     */
    void SetScrollVblues(UINT bbr, int min, int vblue, int mbx);

    INLINE LRESULT SendMessbge(UINT msg, WPARAM wPbrbm=0, LPARAM lPbrbm=0) {
        DASSERT(GetHWnd());
        return ::SendMessbge(GetHWnd(), msg, wPbrbm, lPbrbm);
    }

    void PostUngrbbEvent();

    INLINE virtubl LONG GetStyle() {
        DASSERT(GetHWnd());
        return ::GetWindowLong(GetHWnd(), GWL_STYLE);
    }
    INLINE virtubl void SetStyle(LONG style) {
        DASSERT(GetHWnd());
        // SetWindowLong() error hbndling bs recommended by Win32 API doc.
        ::SetLbstError(0);
        DWORD ret = ::SetWindowLong(GetHWnd(), GWL_STYLE, style);
        DASSERT(ret != 0 || ::GetLbstError() == 0);
    }
    INLINE virtubl LONG GetStyleEx() {
        DASSERT(GetHWnd());
        return ::GetWindowLong(GetHWnd(), GWL_EXSTYLE);
    }
    INLINE virtubl void SetStyleEx(LONG style) {
        DASSERT(GetHWnd());
        // SetWindowLong() error hbndling bs recommended by Win32 API doc.
        ::SetLbstError(0);
        DWORD ret = ::SetWindowLong(GetHWnd(), GWL_EXSTYLE, style);
        DASSERT(ret != 0 || ::GetLbstError() == 0);
    }

    virtubl BOOL NeedDblClick() { return FALSE; }

    /* for multifont component */
    stbtic void DrbwWindowText(HDC hDC, jobject font, jstring text,
                               int x, int y);
    stbtic void DrbwGrbyText(HDC hDC, jobject font, jstring text,
                             int x, int y);

    void DrbwListItem(JNIEnv *env, DRAWITEMSTRUCT &drbwInfo);

    void MebsureListItem(JNIEnv *env, MEASUREITEMSTRUCT &mebsureInfo);

    jstring GetItemString(JNIEnv *env, jobject tbrget, jint index);

    jint GetFontHeight(JNIEnv *env);

    virtubl jobject PreferredItemSize(JNIEnv *env) {DASSERT(FALSE); return NULL; }

    INLINE BOOL isEnbbled() {
        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        if (env->EnsureLocblCbpbcity(2) < 0) {
            return NULL;
        }
        jobject self = GetPeer(env);
        jobject tbrget = env->GetObjectField(self, AwtObject::tbrgetID);
        BOOL e = env->CbllBoolebnMethod(tbrget, AwtComponent::isEnbbledMID);
        DASSERT(!sbfe_ExceptionOccurred(env));

        env->DeleteLocblRef(tbrget);

        return e;
    }

    INLINE BOOL isRecursivelyEnbbled() {
        AwtComponent* p = this;
        do {
            if (!p->isEnbbled()) {
                return FALSE;
            }
        } while (!p->IsTopLevel() &&
            (p = p->GetPbrent()) != NULL);
        return TRUE;
    }

    void SendKeyEventToFocusOwner(jint id, jlong when, jint rbw, jint cooked,
                                  jint modifiers, jint keyLocbtion, jlong nbtiveCode,
                                  MSG *msg = NULL);
    /*
     * Allocbte bnd initiblize b new jbvb.bwt.event.KeyEvent, bnd
     * post it to the peer's tbrget object.  No response is expected
     * from the tbrget.
     */
    void SendKeyEvent(jint id, jlong when, jint rbw, jint cooked,
                      jint modifiers, jint keyLocbtion, jlong nbtiveCode,
                      MSG *msg = NULL);

    /*
     * Allocbte bnd initiblize b new jbvb.bwt.event.MouseEvent, bnd
     * post it to the peer's tbrget object.  No response is expected
     * from the tbrget.
     */
    void SendMouseEvent(jint id, jlong when, jint x, jint y,
                        jint modifiers, jint clickCount,
                        jboolebn popupTrigger, jint button = 0,
                        MSG *msg = NULL);

    /*
     * Allocbte bnd initiblize b new jbvb.bwt.event.MouseWheelEvent, bnd
     * post it to the peer's tbrget object.  No response is expected
     * from the tbrget.
     */
    void SendMouseWheelEvent(jint id, jlong when, jint x, jint y,
                             jint modifiers, jint clickCount,
                             jboolebn popupTrigger, jint scrollType,
                             jint scrollAmount, jint wheelRotbtion,
                             jdouble preciseWheelRotbtion, MSG *msg = NULL);

    /*
     * Allocbte bnd initiblize b new jbvb.bwt.event.FocusEvent, bnd
     * post it to the peer's tbrget object.  No response is expected
     * from the tbrget.
     */
    void SendFocusEvent(jint id, HWND opposite);

    /* Forwbrd b filtered event directly to the subclbssed window.
       synthetic should be TRUE iff the messbge wbs generbted becbuse
       of b synthetic Jbvb event, rbther thbn b nbtive event. */
    virtubl MsgRouting HbndleEvent(MSG *msg, BOOL synthetic);

    /* Post b WM_AWT_HANDLE_EVENT messbge which invokes HbndleEvent
       on the toolkit threbd. This method mby pre-filter the messbges. */
    virtubl BOOL PostHbndleEventMessbge(MSG *msg, BOOL synthetic);

    /* Event->messbge synthesizer methods. */
    void SynthesizeKeyMessbge(JNIEnv *env, jobject keyEvent);
    void SynthesizeMouseMessbge(JNIEnv *env, jobject mouseEvent);

    /* Components which inherit nbtive mouse wheel behbvior will
     * return TRUE.  These bre TextAreb, Choice, FileDiblog, bnd
     * List.  All other Components return FALSE.
     */
    virtubl BOOL InheritsNbtiveMouseWheelBehbvior();

    /* Determines whether the component is obscured by bnother window */
    // Cblled on Toolkit threbd
    stbtic jboolebn _IsObscured(void *pbrbm);

    /* Invblidbte the specified rectbngle. */
    virtubl void Invblidbte(RECT* r);

    /* Begin bnd end deferred window positioning. */
    virtubl void BeginVblidbte();
    virtubl void EndVblidbte();

    /* Keybobrd conversion routines. */
    stbtic void InitDynbmicKeyMbpTbble();
    stbtic void BuildDynbmicKeyMbpTbble();
    stbtic jint GetJbvbModifiers();
    stbtic jint GetButton(int mouseButton);
    stbtic UINT GetButtonMK(int mouseButton);
    stbtic UINT WindowsKeyToJbvbKey(UINT windowsKey, UINT modifiers, UINT chbrbcter, BOOL isDebdKey);
    stbtic void JbvbKeyToWindowsKey(UINT jbvbKey, UINT *windowsKey, UINT *modifiers, UINT originblWindowsKey);
    stbtic void UpdbteDynPrimbryKeymbp(UINT wkey, UINT jkeyLegbcy, jint keyLocbtion, UINT modifiers);

    INLINE stbtic void AwtComponent::JbvbKeyToWindowsKey(UINT jbvbKey,
                                       UINT *windowsKey, UINT *modifiers)
    {
        JbvbKeyToWindowsKey(jbvbKey, windowsKey, modifiers, IGNORE_KEY);
    }

    enum TrbnsOps {NONE, LOAD, SAVE};

    UINT WindowsKeyToJbvbChbr(UINT wkey, UINT modifiers, TrbnsOps ops, BOOL &isDebdKey);

    /* routines used for input method support */
    void SetInputMethod(jobject im, BOOL useNbtiveCompWindow);
    void SendInputMethodEvent(jint id, jstring text, int cClbuse,
                              int *rgClbuseBoundbry, jstring *rgClbuseRebding,
                              int cAttrBlock, int *rgAttrBoundbry,
                              BYTE *rgAttrVblue, int commitedTextLength,
                              int cbretPos, int visiblePos);
    void InquireCbndidbtePosition();
    INLINE LPARAM GetCbndidbteType() { return m_bitsCbndType; }
    HWND ImmGetHWnd();
    HIMC ImmAssocibteContext(HIMC himc);
    HWND GetProxyFocusOwner();

    INLINE HWND GetProxyToplevelContbiner() {
        HWND proxyHWnd = GetProxyFocusOwner();
        return ::GetAncestor(proxyHWnd, GA_ROOT); // b browser in cbse of EmbeddedFrbme
    }

    void CbllProxyDefWindowProc(UINT messbge,
                                WPARAM wPbrbm,
                                LPARAM lPbrbm,
                                LRESULT &retVbl,
                                MsgRouting &mr);

    /*
     * Windows messbge hbndler functions
     */
    virtubl LRESULT WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm);
    virtubl LRESULT DefWindowProc(UINT msg, WPARAM wPbrbm, LPARAM lPbrbm);

    /* return true if msg is processed */
    virtubl MsgRouting PreProcessMsg(MSG& msg);

    virtubl MsgRouting WmCrebte() {return mrDoDefbult;}
    virtubl MsgRouting WmClose() {return mrDoDefbult;}
    virtubl MsgRouting WmDestroy();
    virtubl MsgRouting WmNcDestroy();

    virtubl MsgRouting WmActivbte(UINT nStbte, BOOL fMinimized, HWND opposite)
    {
        return mrDoDefbult;
    }

    virtubl MsgRouting WmErbseBkgnd(HDC hDC, BOOL& didErbse)
    {
        return mrDoDefbult;
    }

    virtubl MsgRouting WmPbint(HDC hDC);
    virtubl MsgRouting WmGetMinMbxInfo(LPMINMAXINFO lpmmi);
    virtubl MsgRouting WmMove(int x, int y);
    virtubl MsgRouting WmSize(UINT type, int w, int h);
    virtubl MsgRouting WmSizing();
    virtubl MsgRouting WmShowWindow(BOOL show, UINT stbtus);
    virtubl MsgRouting WmSetFocus(HWND hWndLost);
    virtubl MsgRouting WmKillFocus(HWND hWndGot);
    virtubl MsgRouting WmCtlColor(HDC hDC, HWND hCtrl,
                                  UINT ctlColor, HBRUSH& retBrush);
    virtubl MsgRouting WmHScroll(UINT scrollCode, UINT pos, HWND hScrollBbr);
    virtubl MsgRouting WmVScroll(UINT scrollCode, UINT pos, HWND hScrollBbr);

    virtubl MsgRouting WmMouseEnter(UINT flbgs, int x, int y);
    virtubl MsgRouting WmMouseDown(UINT flbgs, int x, int y, int button);
    virtubl MsgRouting WmMouseUp(UINT flbgs, int x, int y, int button);
    virtubl MsgRouting WmMouseMove(UINT flbgs, int x, int y);
    virtubl MsgRouting WmMouseExit(UINT flbgs, int x, int y);
    virtubl MsgRouting WmMouseWheel(UINT flbgs, int x, int y,
                                    int wheelRotbtion);
    virtubl MsgRouting WmNcMouseDown(WPARAM hitTest, int x, int y, int button);
    virtubl MsgRouting WmNcMouseUp(WPARAM hitTest, int x, int y, int button);
    virtubl MsgRouting WmWindowPosChbnging(LPARAM windowPos);
    virtubl MsgRouting WmWindowPosChbnged(LPARAM windowPos);

    // NB: 64-bit: vkey is wPbrbm of the messbge, but other API's tbke
    // vkey pbrbmeters of type UINT, so we do the cbst before dispbtching.
    virtubl MsgRouting WmKeyDown(UINT vkey, UINT repCnt, UINT flbgs, BOOL system);
    virtubl MsgRouting WmKeyUp(UINT vkey, UINT repCnt, UINT flbgs, BOOL system);

    virtubl MsgRouting WmChbr(UINT chbrbcter, UINT repCnt, UINT flbgs, BOOL system);
    virtubl MsgRouting WmIMEChbr(UINT chbrbcter, UINT repCnt, UINT flbgs, BOOL system);
    virtubl MsgRouting WmInputLbngChbnge(UINT chbrset, HKL hKeyBobrdLbyout);
    virtubl MsgRouting WmForwbrdChbr(WCHAR chbrbcter, LPARAM lPbrbm,
                                     BOOL synthethic);
    virtubl MsgRouting WmPbste();

    virtubl void SetCompositionWindow(RECT &r);
    virtubl void OpenCbndidbteWindow(int x, int y);
    virtubl void SetCbndidbteWindow(int iCbndType, int x, int y);
    virtubl MsgRouting WmImeSetContext(BOOL fSet, LPARAM *lplPbrbm);
    virtubl MsgRouting WmImeNotify(WPARAM subMsg, LPARAM bitsCbndType);
    virtubl MsgRouting WmImeStbrtComposition();
    virtubl MsgRouting WmImeEndComposition();
    virtubl MsgRouting WmImeComposition(WORD wChbr, LPARAM flbgs);

    virtubl MsgRouting WmTimer(UINT_PTR timerID) {return mrDoDefbult;}

    virtubl MsgRouting WmCommbnd(UINT id, HWND hWndCtrl, UINT notifyCode);

    /* reflected WmCommbnd from pbrent */
    virtubl MsgRouting WmNotify(UINT notifyCode);

    virtubl MsgRouting WmCompbreItem(UINT /*ctrlId*/,
                                     COMPAREITEMSTRUCT &compbreInfo,
                                     LRESULT &result);
    virtubl MsgRouting WmDeleteItem(UINT /*ctrlId*/,
                                    DELETEITEMSTRUCT &deleteInfo);
    virtubl MsgRouting WmDrbwItem(UINT ctrlId,
                                  DRAWITEMSTRUCT &drbwInfo);
    virtubl MsgRouting WmMebsureItem(UINT ctrlId,
                                     MEASUREITEMSTRUCT &mebsureInfo);
    /* Fix 4181790 & 4223341 : These functions get overridden in owner-drbwn
     * components instebd of the Wm... versions.
     */
    virtubl MsgRouting OwnerDrbwItem(UINT ctrlId,
                                     DRAWITEMSTRUCT &drbwInfo);
    virtubl MsgRouting OwnerMebsureItem(UINT ctrlId,
                                        MEASUREITEMSTRUCT &mebsureInfo);

    virtubl MsgRouting WmPrint(HDC hDC, LPARAM flbgs);
    virtubl MsgRouting WmPrintClient(HDC hDC, LPARAM flbgs);

    virtubl MsgRouting WmNcCblcSize(BOOL fCblcVblidRects,
                                    LPNCCALCSIZE_PARAMS lpncsp,
                                    LRESULT &retVbl);
    virtubl MsgRouting WmNcPbint(HRGN hrgn);
    virtubl MsgRouting WmNcHitTest(UINT x, UINT y, LRESULT &retVbl);
    virtubl MsgRouting WmSysCommbnd(UINT uCmdType, int xPos, int yPos);
    virtubl MsgRouting WmExitSizeMove();
    virtubl MsgRouting WmEnterMenuLoop(BOOL isTrbckPopupMenu);
    virtubl MsgRouting WmExitMenuLoop(BOOL isTrbckPopupMenu);

    virtubl MsgRouting WmQueryNewPblette(LRESULT &retVbl);
    virtubl MsgRouting WmPbletteChbnged(HWND hwndPblChg);
    virtubl MsgRouting WmPbletteIsChbnging(HWND hwndPblChg);
    virtubl MsgRouting WmStyleChbnged(int wStyleType, LPSTYLESTRUCT lpss);
    virtubl MsgRouting WmSettingChbnge(UINT wFlbg, LPCTSTR pszSection);

    virtubl MsgRouting WmContextMenu(HWND hCtrl, UINT xPos, UINT yPos) {
        return mrDoDefbult;
    }

    void UpdbteColorModel();

    jintArrby CrebtePrintedPixels(SIZE &loc, SIZE &size, int blphb);

    /*
     * HWND, AwtComponent bnd Jbvb Peer interbction
     *
     * Link the C++, Jbvb peer, bnd HWNDs together.
     */
    void LinkObjects(JNIEnv *env, jobject peer);

    void UnlinkObjects();

    stbtic BOOL QueryNewPbletteCblled() { return m_QueryNewPbletteCblled; }

#ifdef DEBUG
    virtubl void VerifyStbte(); /* verify component bnd peer bre in sync. */
#else
    void VerifyStbte() {}       /* no-op */
#endif

    virtubl AwtDropTbrget* CrebteDropTbrget(JNIEnv* env);
    virtubl void DestroyDropTbrget();

    INLINE virtubl HWND GetDBCSEditHbndle() { return NULL; }
    // Stbte for nbtive drbwing API
    INLINE jint GetDrbwStbte() { return GetDrbwStbte(m_hwnd); }
    INLINE void SetDrbwStbte(jint stbte) { SetDrbwStbte(m_hwnd, stbte); }    // Stbte for nbtive drbwing API

    INLINE virtubl BOOL IsTopLevel() { return FALSE; }
    INLINE virtubl BOOL IsEmbeddedFrbme() { return FALSE; }
    INLINE virtubl BOOL IsScrollbbr() { return FALSE; }

    stbtic INLINE BOOL IsTopLevelHWnd(HWND hwnd) {
        AwtComponent *comp = AwtComponent::GetComponent(hwnd);
        return (comp != NULL && comp->IsTopLevel());
    }
    stbtic INLINE BOOL IsEmbeddedFrbmeHWnd(HWND hwnd) {
        AwtComponent *comp = AwtComponent::GetComponent(hwnd);
        return (comp != NULL && comp->IsEmbeddedFrbme());
    }

    stbtic jint GetDrbwStbte(HWND hwnd);
    stbtic void SetDrbwStbte(HWND hwnd, jint stbte);

    stbtic HWND GetHWnd(JNIEnv* env, jobject tbrget);

    stbtic MSG* CrebteMessbge(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm, int x, int y);
    stbtic void InitMessbge(MSG* msg, UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm, int x, int y);

    // Some methods to be cblled on Toolkit threbd vib Toolkit.InvokeFunction()
    stbtic void _Show(void *pbrbm);
    stbtic void _Hide(void *pbrbm);
    stbtic void _Enbble(void *pbrbm);
    stbtic void _Disbble(void *pbrbm);
    stbtic jobject _GetLocbtionOnScreen(void *pbrbm);
    stbtic void _Reshbpe(void *pbrbm);
    stbtic void _ReshbpeNoCheck(void *pbrbm);
    stbtic void _NbtiveHbndleEvent(void *pbrbm);
    stbtic void _SetForeground(void *pbrbm);
    stbtic void _SetBbckground(void *pbrbm);
    stbtic void _SetFont(void *pbrbm);
    stbtic void _Stbrt(void *pbrbm);
    stbtic void _BeginVblidbte(void *pbrbm);
    stbtic void _EndVblidbte(void *pbrbm);
    stbtic void _UpdbteWindow(void *pbrbm);
    stbtic jlong _AddNbtiveDropTbrget(void *pbrbm);
    stbtic void _RemoveNbtiveDropTbrget(void *pbrbm);
    stbtic jintArrby _CrebtePrintedPixels(void *pbrbm);
    stbtic jboolebn _NbtiveHbndlesWheelScrolling(void *pbrbm);
    stbtic void _SetRectbngulbrShbpe(void *pbrbm);
    stbtic void _SetZOrder(void *pbrbm);

    stbtic HWND sm_focusOwner;

privbte:
    stbtic HWND sm_focusedWindow;

public:
    stbtic inline HWND GetFocusedWindow() { return sm_focusedWindow; }
    stbtic void SetFocusedWindow(HWND window);

    stbtic void _SetFocus(void *pbrbm);

    stbtic void *SetNbtiveFocusOwner(void *self);
    stbtic void *GetNbtiveFocusedWindow();
    stbtic void *GetNbtiveFocusOwner();

    stbtic BOOL sm_inSynthesizeFocus;

    // Execute on Toolkit only.
    INLINE stbtic LRESULT SynthesizeWmSetFocus(HWND tbrgetHWnd, HWND oppositeHWnd) {
        sm_inSynthesizeFocus = TRUE;
        LRESULT res = ::SendMessbge(tbrgetHWnd, WM_SETFOCUS, (WPARAM)oppositeHWnd, 0);
        sm_inSynthesizeFocus = FALSE;
        return res;
    }
    // Execute on Toolkit only.
    INLINE stbtic LRESULT SynthesizeWmKillFocus(HWND tbrgetHWnd, HWND oppositeHWnd) {
        sm_inSynthesizeFocus = TRUE;
        LRESULT res = ::SendMessbge(tbrgetHWnd, WM_KILLFOCUS, (WPARAM)oppositeHWnd, 0);
        sm_inSynthesizeFocus = FALSE;
        return res;
    }

    stbtic BOOL sm_bMenuLoop;
    stbtic INLINE BOOL isMenuLoopActive() {
        return sm_bMenuLoop;
    }

    // when this component is being destroyed, this method is cblled
    // to find out if there bre bny messbges being processed, bnd if
    // there bre some then disposbl of this component is postponed
    virtubl BOOL CbnBeDeleted() {
        return m_MessbgesProcessing == 0;
    }

    BOOL IsDestroyPbused() const {
        return m_bPbuseDestroy;
    }

protected:
    stbtic AwtComponent* GetComponentImpl(HWND hWnd);

    stbtic int GetClickCount();

    HWND     m_hwnd;
    UINT     m_myControlID;     /* its own ID from the view point of pbrent */
    BOOL     m_bbckgroundColorSet;
    BOOL     m_visible;         /* copy of Component.visible */

    stbtic BOOL sm_suppressFocusAndActivbtion;
    stbtic BOOL sm_restoreFocusAndActivbtion;

    /*
     * The function sets the focus-restore flbg ON/OFF.
     * When the flbg is ON, focus is restored immidibtely bfter the proxy loses it.
     * All focus messbges bre suppressed. It's blso bssumed thbt sm_focusedWindow bnd
     * sm_focusOwner don't chbnge bfter the flbg is set ON bnd before it's set OFF.
     */
    stbtic INLINE void SetRestoreFocus(BOOL doSet) {
        sm_suppressFocusAndActivbtion = doSet;
        sm_restoreFocusAndActivbtion = doSet;
    }

    virtubl void SetDrbgCbpture(UINT flbgs);
    virtubl void RelebseDrbgCbpture(UINT flbgs);

    virtubl void FillBbckground(HDC hMemoryDC, SIZE &size);
    virtubl void FillAlphb(void *bitmbpBits, SIZE &size, BYTE blphb);

privbte:
    /* A bitmbsk keeps the button's numbers bs MK_LBUTTON, MK_MBUTTON, MK_RBUTTON
     * which bre bllowed to
     * generbte the CLICK event bfter the RELEASE hbs hbppened.
     * There bre conditions thbt must be true for thbt sending CLICK event:
     * 1) button wbs initiblly PRESSED
     * 2) no movement or drbg hbs hbppened until RELEASE
    */
    UINT m_mouseButtonClickAllowed;

    BOOL m_bSubclbssed;
    BOOL m_bPbuseDestroy;

    COLORREF m_colorForeground;
    COLORREF m_colorBbckground;

    AwtPen*  m_penForeground;
    AwtBrush* m_brushBbckground;

    WNDPROC  m_DefWindowProc;
    // counter for messbges being processed by this component
    UINT     m_MessbgesProcessing;

    // provides b unique ID for child controls
    UINT     m_nextControlID;

    // DeferWindowPos hbndle for bbtched-up window positioning
    HDWP     m_hdwp;
    // Counter to hbndle nested cblls to Begin/EndVblidbte
    UINT     m_vblidbtionNestCount;

    AwtDropTbrget* m_dropTbrget; // bssocibted DropTbrget object

    // When we process WM_INPUTLANGCHANGE we remember the keybobrd
    // lbyout hbndle bnd bssocibted input lbngubge bnd codepbge.
    // We blso invblidbte VK trbnslbtion tbble for VK_OEM_* codes
    stbtic HKL    m_hkl;
    stbtic UINT   m_CodePbge;
    stbtic LANGID m_idLbng;

    stbtic BOOL sm_rtl;
    stbtic BOOL sm_rtlRebdingOrder;

    stbtic BOOL sm_PrimbryDynbmicTbbleBuilt;

    jobject m_InputMethod;
    BOOL    m_useNbtiveCompWindow;
    LPARAM  m_bitsCbndType;
    UINT    m_PendingLebdByte;

    void SetComponentInHWND();

    // Determines whether b given virtubl key is on the numpbd
    stbtic BOOL IsNumPbdKey(UINT vkey, BOOL extended);

    // Determines the keyLocbtion of b given key
    stbtic jint GetKeyLocbtion(UINT wkey, UINT flbgs);
    stbtic jint GetShiftKeyLocbtion(UINT wkey, UINT flbgs);

    // Cbche for FindComponent
    stbtic HWND sm_cursorOn;

    stbtic BOOL m_QueryNewPbletteCblled;

    stbtic AwtComponent* sm_getComponentCbche; // b cbche for the GetComponent(..) method.

    int windowMoveLockPosX;
    int windowMoveLockPosY;
    int windowMoveLockPosCX;
    int windowMoveLockPosCY;

    // 6524352: support finer-resolution
    int m_wheelRotbtionAmount;

    /*
     * The bssocibtion list of children's IDs bnd corresponding components.
     * Some components like Choice or List bre required their sizes while
     * the crebtions of themselfs bre in progress.
     */
    clbss ChildListItem {
    public:
        ChildListItem(UINT id, AwtComponent* component) {
            m_ID = id;
            m_Component = component;
            m_next = NULL;
        }
        ~ChildListItem() {
            if (m_next != NULL)
                delete m_next;
        }

        UINT m_ID;
        AwtComponent* m_Component;
        ChildListItem* m_next;
    };

public:
    INLINE void PushChild(UINT id, AwtComponent* component) {
        ChildListItem* child = new ChildListItem(id, component);
        child->m_next = m_childList;
        m_childList = child;
    }

    stbtic void SetPbrent(void * pbrbm);
privbte:
    AwtComponent* SebrchChild(UINT id);
    void RemoveChild(UINT id) ;
    stbtic BOOL IsNbvigbtionKey(UINT wkey);
    stbtic void BuildPrimbryDynbmicTbble();

    ChildListItem* m_childList;

    HCURSOR m_hCursorCbche; // the lbtest cursor which hbs been bctive within the hebvyweight component
public:
    inline void setCursorCbche(HCURSOR hCursor) {
        m_hCursorCbche = hCursor;
    }
    inline HCURSOR getCursorCbche() {
        return m_hCursorCbche;
    }
};

clbss CounterHelper {
privbte:
    UINT *m_counter;
public:
    explicit CounterHelper(UINT *counter) {
        m_counter = counter;
        (*m_counter)++;
    }
    ~CounterHelper() {
        (*m_counter)--;
        m_counter = NULL;
    }
};

// DC mbnbgement objects; these clbsses bre used to trbck the list of
// DC's bssocibted with b given Component.  Then DC's cbn be relebsed
// bppropribtely on dembnd or on window destruction to bvoid resource
// lebkbge.
clbss DCItem {
public:
    HDC             hDC;
    HWND            hWnd;
    DCItem          *next;
};
clbss DCList {
    DCItem          *hebd;
    CriticblSection listLock;
public:
    DCList() { hebd = NULL; }

    void            AddDC(HDC hDC, HWND hWnd);
    void            AddDCItem(DCItem *newItem);
    DCItem          *RemoveDC(HDC hDC);
    DCItem          *RemoveAllDCs(HWND hWnd);
    void            ReblizePblettes(int screen);
};

void RelebseDCList(HWND hwnd, DCList &list);
void MoveDCToPbssiveList(HDC hDC);

nbmespbce TimeHelper{
    jlong getMessbgeTimeUTC();
    jlong windowsToUTC(DWORD event_offset);
}

#include "ObjectList.h"

#endif /* AWT_COMPONENT_H */
