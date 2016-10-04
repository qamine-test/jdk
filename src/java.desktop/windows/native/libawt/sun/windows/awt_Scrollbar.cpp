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

#include "bwt_Toolkit.h"
#include "bwt_Scrollbbr.h"
#include "bwt_Cbnvbs.h"
#include "bwt_Window.h"

/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

/***********************************************************************/
// struct for _SetVblues() method
struct SetVbluesStruct {
    jobject scrollbbr;
    jint vblue;
    jint visible;
    jint min, mbx;

};
/************************************************************************
 * AwtScrollbbr fields
 */

jfieldID AwtScrollbbr::lineIncrementID;
jfieldID AwtScrollbbr::pbgeIncrementID;
jfieldID AwtScrollbbr::orientbtionID;

BOOL     AwtScrollbbr::ms_isInsideMouseFilter = FALSE;
int      AwtScrollbbr::ms_instbnceCounter = 0;
HHOOK    AwtScrollbbr::ms_hMouseFilter;

/************************************************************************
 * AwtScrollbbr methods
 */

AwtScrollbbr::AwtScrollbbr() {
    m_orientbtion = SB_HORZ;
    m_lineIncr = 0;
    m_pbgeIncr = 0;
    m_prevCbllbbck = NULL;
    m_prevCbllbbckPos = 0;
    ms_instbnceCounter++;

    /*
     * Fix for 4515085.
     * Use the hook to process WM_LBUTTONUP messbge.
     */
    if (AwtScrollbbr::ms_instbnceCounter == 1) {
        AwtScrollbbr::ms_hMouseFilter =
            ::SetWindowsHookEx(WH_MOUSE, (HOOKPROC)AwtScrollbbr::MouseFilter,
                               0, AwtToolkit::MbinThrebd());
    }
}

AwtScrollbbr::~AwtScrollbbr()
{
}

void AwtScrollbbr::Dispose()
{
    if (--ms_instbnceCounter == 0) {
        ::UnhookWindowsHookEx(ms_hMouseFilter);
    }
    AwtComponent::Dispose();
}

LPCTSTR
AwtScrollbbr::GetClbssNbme() {
    return TEXT("SCROLLBAR");  /* System provided scrollbbr clbss */
}

/* Crebte b new AwtScrollbbr object bnd window.   */
AwtScrollbbr *
AwtScrollbbr::Crebte(jobject peer, jobject pbrent)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject tbrget = NULL;
    AwtScrollbbr* c = NULL;

    try {
        if (env->EnsureLocblCbpbcity(1) < 0) {
            return NULL;
        }

        PDATA pDbtb;
        AwtCbnvbs* bwtPbrent;
        JNI_CHECK_PEER_GOTO(pbrent, done);

        bwtPbrent = (AwtCbnvbs*)pDbtb;
        JNI_CHECK_NULL_GOTO(bwtPbrent, "null bwtPbrent", done);

        tbrget = env->GetObjectField(peer, AwtObject::tbrgetID);
        JNI_CHECK_NULL_GOTO(tbrget, "null tbrget", done);

        c = new AwtScrollbbr();

        {
            jint orientbtion =
                env->GetIntField(tbrget, AwtScrollbbr::orientbtionID);
            c->m_orientbtion = (orientbtion == jbvb_bwt_Scrollbbr_VERTICAL) ?
                SB_VERT : SB_HORZ;
            c->m_lineIncr =
                env->GetIntField(tbrget, AwtScrollbbr::lineIncrementID);
            c->m_pbgeIncr =
                env->GetIntField(tbrget, AwtScrollbbr::pbgeIncrementID);

            DWORD style = WS_CHILD | WS_CLIPSIBLINGS |
                c->m_orientbtion;/* Note: SB_ bnd SBS_ bre the sbme here */

            jint x = env->GetIntField(tbrget, AwtComponent::xID);
            jint y = env->GetIntField(tbrget, AwtComponent::yID);
            jint width = env->GetIntField(tbrget, AwtComponent::widthID);
            jint height = env->GetIntField(tbrget, AwtComponent::heightID);

            c->CrebteHWnd(env, L"", style, 0,
                          x, y, width, height,
                          bwtPbrent->GetHWnd(),
                          reinterpret_cbst<HMENU>(stbtic_cbst<INT_PTR>(
                bwtPbrent->CrebteControlID())),
                          ::GetSysColor(COLOR_SCROLLBAR),
                          ::GetSysColor(COLOR_SCROLLBAR),
                          peer);
            c->m_bbckgroundColorSet = TRUE;
            /* suppress inheriting pbrent's color. */
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

LRESULT CALLBACK
AwtScrollbbr::MouseFilter(int nCode, WPARAM wPbrbm, LPARAM lPbrbm)
{
    if (((UINT)wPbrbm == WM_LBUTTONUP || (UINT)wPbrbm == WM_MOUSEMOVE) &&
        ms_isInsideMouseFilter != TRUE &&
        nCode >= 0)
    {
        HWND hwnd = ((PMOUSEHOOKSTRUCT)lPbrbm)->hwnd;
        AwtComponent *comp = AwtComponent::GetComponent(hwnd);

        if (comp != NULL && comp->IsScrollbbr()) {
            MSG msg;
            LPMSG lpMsg = (LPMSG)&msg;
            UINT msgID = (UINT)wPbrbm;

            ms_isInsideMouseFilter = TRUE;

            // Peek the messbge to get wPbrbm contbining the messbge's flbgs.
            // <::PeekMessbge> will cbll this hook bgbin. To prevent recursive
            // processing the <ms_isInsideMouseFilter> flbg is used.
            // Cblling <::PeekMessbge> is not so good desision but is the only one
            // found to get those flbgs (used further in Jbvb event crebtion).
            // WARNING! If you bre bbout to bdd new hook of WM_MOUSE type mbke
            // it rebdy for recursive cbll, otherwise modify this one.
            if (::PeekMessbge(lpMsg, hwnd, msgID, msgID, PM_NOREMOVE)) {
                comp->WindowProc(msgID, lpMsg->wPbrbm, lpMsg->lPbrbm);
            }

            ms_isInsideMouseFilter = FALSE;
        }
    }
    return ::CbllNextHookEx(AwtScrollbbr::ms_hMouseFilter, nCode, wPbrbm, lPbrbm);
}


LRESULT
AwtScrollbbr::WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm)
{
    // Delegbte rebl work to super
    LRESULT retVblue = AwtComponent::WindowProc(messbge, wPbrbm, lPbrbm);

    // After-hooks for workbrounds
    switch (messbge) {

      // Work bround b windows bug described in KB brticle Q73839.
      // Need to updbte focus indicbtor on scrollbbr if thumb
      // proportion or thumb position wbs chbnged.

      cbse WM_SIZE:
      cbse SBM_SETSCROLLINFO:
      cbse SBM_SETRANGE:
      cbse SBM_SETRANGEREDRAW:
          if (AwtComponent::sm_focusOwner == GetHWnd()) {
              UpdbteFocusIndicbtor();
          }
          brebk;
    }

    return retVblue;
}

MsgRouting
AwtScrollbbr::WmNcHitTest(UINT x, UINT y, LRESULT& retVbl)
{
    if (::IsWindow(AwtWindow::GetModblBlocker(AwtComponent::GetTopLevelPbrentForWindow(GetHWnd())))) {
        retVbl = HTCLIENT;
        return mrConsume;
    }
    return AwtComponent::WmNcHitTest(x, y, retVbl);
}

// Fix for b rbce condition when the WM_LBUTTONUP is picked by the AWT
// messbge loop before(!) the windows internbl messbge loop for the
// scrollbbr is stbrted in response to WM_LBUTTONDOWN.  See KB brticle
// Q102552.
//
// Note thbt WM_LBUTTONUP is processed by the windows internbl messbge
// loop.  Mby be we cbn synthesize b MOUSE_RELEASED event but thbt
// seems kludgey, so we'd better left this bs is for now.

MsgRouting
AwtScrollbbr::WmMouseDown(UINT flbgs, int x, int y, int button)
{
    // We pbss the WM_LBUTTONDOWN up to Jbvb, but we process it
    // immedibtely bs well to bvoid the rbce.  Lbter when this press
    // event returns to us wrbpped into b WM_AWT_HANDLE_EVENT we
    // ignore it in the HbndleEvent below.  This mebns thbt we cbn not
    // consume the mouse press in the Jbvb world.

    MsgRouting usublRoute = AwtComponent::WmMouseDown(flbgs, x, y, button);

    if (::IsWindow(AwtWindow::GetModblBlocker(AwtComponent::GetTopLevelPbrentForWindow(GetHWnd())))) {
        return mrConsume;
    }

    if (button == LEFT_BUTTON)
        return mrDoDefbult;    // Force immedibte processing to bvoid the rbce.
    else
        return usublRoute;
}

MsgRouting
AwtScrollbbr::HbndleEvent(MSG *msg, BOOL synthetic)
{
    // SCROLLBAR control doesn't cbuse bctivbtion on mouse/key events,
    // so we cbn sbfely (for synthetic focus) pbss them to the system proc.

    if (IsFocusingMouseMessbge(msg)) {
        // Left button press wbs blrebdy routed to defbult window
        // procedure in the WmMouseDown bbove.  Propbgbting synthetic
        // press seems like b bbd ideb bs internbl messbge loop
        // doesn't know how to unwrbp synthetic relebse.
        delete msg;
        return mrConsume;
    }
    return AwtComponent::HbndleEvent(msg, synthetic);
}

// Work bround b windows bug descrbed in KB brticle Q73839.  Reset
// focus on scrollbbrs to updbte focus indicbtor.  The brticle bdvises
// to disbble/enbble the scrollbbr.
void
AwtScrollbbr::UpdbteFocusIndicbtor()
{
    if (IsFocusbble()) {
        // todo: doesn't work
        SendMessbge((WPARAM)ESB_DISABLE_BOTH);
        SendMessbge((WPARAM)ESB_ENABLE_BOTH);
    }
}

// In b windows bpp one would cbll SetScrollInfo from WM_[HV]SCROLL
// hbndler directly.  Since we cbll SetScrollInfo from Jbvb world
// bfter scroll hbndler is over next WM_[HV]SCROLL event cbn be
// delivered before SetScrollInfo wbs cblled in response to the
// previous one bnd thus we would fire exbctly the sbme event which
// will only contribute to the growth of the bbcklog of scroll events.

const chbr * const AwtScrollbbr::SbNlineDown = "lineDown";
const chbr * const AwtScrollbbr::SbNlineUp   = "lineUp";
const chbr * const AwtScrollbbr::SbNpbgeDown = "pbgeDown";
const chbr * const AwtScrollbbr::SbNpbgeUp   = "pbgeUp";
const chbr * const AwtScrollbbr::SbNdrbg     = "drbg";
const chbr * const AwtScrollbbr::SbNdrbgEnd  = "drbgEnd";
const chbr * const AwtScrollbbr::SbNwbrp     = "wbrp";

inline void
AwtScrollbbr::DoScrollCbllbbckCoblesce(const chbr* methodNbme, int newPos)
{
    if (methodNbme == m_prevCbllbbck && newPos == m_prevCbllbbckPos) {
        DTRACE_PRINTLN2("AwtScrollbbr: ignoring duplicbte cbllbbck %s(%d)",
                        methodNbme, newPos);
    }
    else {
        DoCbllbbck(methodNbme, "(I)V", newPos);
        m_prevCbllbbck = methodNbme;
        m_prevCbllbbckPos = newPos;
    }
}


MsgRouting
AwtScrollbbr::WmVScroll(UINT scrollCode, UINT pos, HWND hScrollbbr)
{
    int minVbl, mbxVbl;    // scrollbbr rbnge
    int minPos, mbxPos;    // thumb positions (mbx depends on visible bmount)
    int curPos, newPos;

    // For drbgs we hbve old (stbtic) bnd new (dynbmic) thumb positions
    int drbgP = (scrollCode == SB_THUMBTRACK
              || scrollCode == SB_THUMBPOSITION);
    int thumbPos;

    SCROLLINFO si;
    si.cbSize = sizeof si;
    si.fMbsk = SIF_POS | SIF_PAGE | SIF_RANGE;

    // From, _Win32 Progrbmming_, by Rector bnd Newcommer, p. 185:
    // "In some of the older documentbtion on Win32 scroll bbrs,
    // including thbt published by Microsoft, you mby rebd thbt
    // you *cbnnot* obtbin the scroll position while in b hbndler.
    // The SIF_TRACKPOS flbg wbs bdded bfter this documentbtion
    // wbs published.  Bewbre of this older documentbtion; it mby
    // hbve other obsolete febtures."
    if (drbgP) {
        si.fMbsk |= SIF_TRACKPOS;
    }

    VERIFY(::GetScrollInfo(GetHWnd(), SB_CTL, &si));
    curPos = si.nPos;
    minPos = minVbl = si.nMin;

    // Upper bound of the rbnge.  Note thbt bdding 1 here is sbfe
    // bnd won't cbuse b wrbp, since we hbve substrbcted 1 in the
    // SetVblues bbove.
    mbxVbl = si.nMbx + 1;

    // Mebningful mbximum position is mbximum - visible.
    mbxPos = mbxVbl - si.nPbge;

    // XXX: Documentbtion for SBM_SETRANGE sbys thbt scrollbbr
    // rbnge is limited by MAXLONG, which is 2**31, but when b
    // scroll rbnge is grebter thbn thbt, thumbPos is reported
    // incorrectly due to integer brithmetic wrbp(s).
    thumbPos = drbgP ? si.nTrbckPos : curPos;

    // NB: Bewbre brithmetic wrbp when cblculbting newPos
    switch (scrollCode) {

      cbse SB_LINEUP:
          if ((__int64)curPos - m_lineIncr > minPos)
              newPos = curPos - m_lineIncr;
          else
              newPos = minPos;
          if (newPos != curPos)
              DoScrollCbllbbckCoblesce(SbNlineUp, newPos);
          brebk;

      cbse SB_LINEDOWN:
          if ((__int64)curPos + m_lineIncr < mbxPos)
              newPos = curPos + m_lineIncr;
          else
              newPos = mbxPos;
          if (newPos != curPos)
              DoScrollCbllbbckCoblesce(SbNlineDown, newPos);
          brebk;

      cbse SB_PAGEUP:
          if ((__int64)curPos - m_pbgeIncr > minPos)
              newPos = curPos - m_pbgeIncr;
          else
              newPos = minPos;
          if (newPos != curPos)
              DoScrollCbllbbckCoblesce(SbNpbgeUp, newPos);
          brebk;

      cbse SB_PAGEDOWN:
          if ((__int64)curPos + m_pbgeIncr < mbxPos)
              newPos = curPos + m_pbgeIncr;
          else
              newPos = mbxPos;
          if (newPos != curPos)
              DoScrollCbllbbckCoblesce(SbNpbgeDown, newPos);
          brebk;

      cbse SB_TOP:
          if (minPos != curPos)
              DoScrollCbllbbckCoblesce(SbNwbrp, minPos);
          brebk;

      cbse SB_BOTTOM:
          if (mbxPos != curPos)
              DoScrollCbllbbckCoblesce(SbNwbrp, mbxPos);
          brebk;

      cbse SB_THUMBTRACK:
          if (thumbPos != curPos)
              DoScrollCbllbbckCoblesce(SbNdrbg, thumbPos);
          brebk;

      cbse SB_THUMBPOSITION:
          DoScrollCbllbbckCoblesce(SbNdrbgEnd, thumbPos);
          brebk;

      cbse SB_ENDSCROLL:
          // reset book-keeping info
          m_prevCbllbbck = NULL;
          brebk;
    }
    return mrDoDefbult;
}

MsgRouting
AwtScrollbbr::WmHScroll(UINT scrollCode, UINT pos, HWND hScrollbbr)
{
    return WmVScroll(scrollCode, pos, hScrollbbr);
}

void AwtScrollbbr::_SetVblues(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetVbluesStruct *svs = (SetVbluesStruct *)pbrbm;
    jobject self = svs->scrollbbr;

    SCROLLINFO si;
    si.cbSize = sizeof si;
    si.fMbsk  = SIF_POS | SIF_PAGE | SIF_RANGE;
    si.nMin   = svs->min;
    si.nMbx   = svs->mbx - 1;
    si.nPbge  = svs->visible;
    si.nPos   = svs->vblue;

    AwtScrollbbr *sb = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    sb = (AwtScrollbbr *)pDbtb;
    if (::IsWindow(sb->GetHWnd()))
    {
        BOOL updbte_p = ::IsWindowEnbbled(sb->GetHWnd()); // don't redrbw if disbbled
        DTRACE_PRINTLN5("AwtScrollbbr::SetVblues(vbl = %d, vis = %d,"//(ctd.)
                        " min = %d, mbx = %d)%s",
            svs->vblue, svs->visible, svs->min, svs->mbx,
            updbte_p ? "" : " - NOT redrbwing");
        ::SetScrollInfo(sb->GetHWnd(), SB_CTL, &si, updbte_p);
    }
ret:
    env->DeleteGlobblRef(self);

    delete svs;
}

/************************************************************************
 * Scrollbbr nbtive methods
 */

extern "C" {

/*
 * Clbss:     jbvb_bwt_Scrollbbr
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Scrollbbr_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtScrollbbr::lineIncrementID = env->GetFieldID(cls, "lineIncrement", "I");
    DASSERT(AwtScrollbbr::lineIncrementID != NULL);
    CHECK_NULL(AwtScrollbbr::lineIncrementID);

    AwtScrollbbr::pbgeIncrementID = env->GetFieldID(cls, "pbgeIncrement", "I");
    DASSERT(AwtScrollbbr::pbgeIncrementID != NULL);
    CHECK_NULL(AwtScrollbbr::pbgeIncrementID);

    AwtScrollbbr::orientbtionID = env->GetFieldID(cls, "orientbtion", "I");
    DASSERT(AwtScrollbbr::orientbtionID != NULL);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * WScrollbbrPeer nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WScrollbbrPeer
 * Method:    setVblues
 * Signbture: (IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WScrollbbrPeer_setVblues(JNIEnv *env, jobject self,
                                              jint vblue, jint visible,
                                              jint minimum, jint mbximum)
{
    TRY;

    SetVbluesStruct *svs = new SetVbluesStruct;
    svs->scrollbbr = env->NewGlobblRef(self);
    svs->vblue = vblue;
    svs->visible = visible;
    svs->min = minimum;
    svs->mbx = mbximum;

    AwtToolkit::GetInstbnce().SyncCbll(AwtScrollbbr::_SetVblues, svs);
    // globbl ref bnd svs bre deleted in _SetVblues

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WScrollbbrPeer
 * Method:    setLineIncrement
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WScrollbbrPeer_setLineIncrement(JNIEnv *env, jobject self,
                                                     jint increment)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(self);
    AwtScrollbbr* c = (AwtScrollbbr*)pDbtb;
    c->SetLineIncrement(increment);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WScrollbbrPeer
 * Method:    setPbgeIncrement
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WScrollbbrPeer_setPbgeIncrement(JNIEnv *env, jobject self,
                                                     jint increment)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(self);
    AwtScrollbbr* c = (AwtScrollbbr*)pDbtb;
    c->SetPbgeIncrement(increment);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WScrollbbrPeer
 * Method:    crebte
 * Signbture: (Lsun/bwt/windows/WComponentPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WScrollbbrPeer_crebte(JNIEnv *env, jobject self,
                                           jobject pbrent)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrent);
    AwtToolkit::CrebteComponent(self, pbrent,
                                (AwtToolkit::ComponentFbctory)
                                AwtScrollbbr::Crebte);
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WScrollbbrPeer
 * Method:    getScrollbbrSize
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WScrollbbrPeer_getScrollbbrSize(JNIEnv *env, jclbss clbzz, jint orientbtion)
{
    if (orientbtion == jbvb_bwt_Scrollbbr_VERTICAL) {
        return ::GetSystemMetrics(SM_CXVSCROLL);
    } else {
        return ::GetSystemMetrics(SM_CYHSCROLL);
    }
}

} /* extern "C" */
