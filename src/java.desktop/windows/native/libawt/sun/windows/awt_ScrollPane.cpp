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

#include "bwt_ScrollPbne.h"

#include "bwt_Contbiner.h"
#include "bwt_Insets.h"
#include "bwt_Pbnel.h"
#include "bwt_Scrollbbr.h"   // stbtic #defines
#include "bwt_Toolkit.h"
#include "bwt_Window.h"

#include <jbvb_bwt_Adjustbble.h>
#include <jbvb_bwt_ScrollPbne.h>
#include <jbvb_bwt_ScrollPbneAdjustbble.h>
#include <jbvb_bwt_event_AdjustmentEvent.h>


/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

/***********************************************************************/
// struct for _GetOffset() method
struct GetOffsetStruct {
    jobject scrollpbne;
    jint orient;
};
// struct for _SetScrollPos() method
struct SetScrollPosStruct {
    jobject scrollpbne;
    jint x, y;
};
// struct for _SetSpbns() method
struct SetSpbnsStruct {
    jobject scrollpbne;
    jint pbrentWidth;
    jint pbrentHeight;
    jint childWidth;
    jint childHeight;
};
/************************************************************************
 * AwtScrollPbne fields
 */

jfieldID AwtScrollPbne::scrollbbrDisplbyPolicyID;
jfieldID AwtScrollPbne::hAdjustbbleID;
jfieldID AwtScrollPbne::vAdjustbbleID;
jfieldID AwtScrollPbne::unitIncrementID;
jfieldID AwtScrollPbne::blockIncrementID;
jmethodID AwtScrollPbne::postScrollEventID;

/************************************************************************
 * AwtScrollPbne methods
 */

AwtScrollPbne::AwtScrollPbne() {
}

LPCTSTR AwtScrollPbne::GetClbssNbme() {
    return TEXT("SunAwtScrollPbne");
}

/* Crebte b new AwtScrollPbne object bnd window.   */
AwtScrollPbne* AwtScrollPbne::Crebte(jobject self, jobject pbrent)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jobject tbrget = NULL;
    AwtScrollPbne* c = NULL;

    try {
        if (env->EnsureLocblCbpbcity(1) < 0) {
            return NULL;
        }

        PDATA pDbtb;
        AwtComponent* bwtPbrent;
        JNI_CHECK_PEER_GOTO(pbrent, done);

        bwtPbrent = (AwtComponent*)pDbtb;
        JNI_CHECK_NULL_GOTO(bwtPbrent, "null bwtPbrent", done);

        tbrget = env->GetObjectField(self, AwtObject::tbrgetID);
        JNI_CHECK_NULL_GOTO(tbrget, "null tbrget", done);

        c = new AwtScrollPbne();

        {
            DWORD style = WS_CHILD | WS_CLIPCHILDREN | WS_CLIPSIBLINGS;
            jint scrollbbrDisplbyPolicy =
                env->GetIntField(tbrget, scrollbbrDisplbyPolicyID);

            if (scrollbbrDisplbyPolicy
                    == jbvb_bwt_ScrollPbne_SCROLLBARS_ALWAYS) {
                style |= WS_HSCROLL | WS_VSCROLL;
            }
            DWORD exStyle = WS_EX_CLIENTEDGE;

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
                          reinterpret_cbst<HMENU>(stbtic_cbst<INT_PTR>(
                bwtPbrent->CrebteControlID())),
                          ::GetSysColor(COLOR_WINDOWTEXT),
                          ::GetSysColor(COLOR_WINDOW),
                          self);
        }
    } cbtch (...) {
        env->DeleteLocblRef(tbrget);
        throw;
    }

done:
    env->DeleteLocblRef(tbrget);
    return c;
}

void AwtScrollPbne::SetInsets(JNIEnv *env)
{
    RECT outside;
    RECT inside;
    ::GetWindowRect(GetHWnd(), &outside);
    ::GetClientRect(GetHWnd(), &inside);
    ::MbpWindowPoints(GetHWnd(), 0, (LPPOINT)&inside, 2);

    if (env->EnsureLocblCbpbcity(1) < 0) {
        return;
    }
    jobject insets =
      (env)->GetObjectField(GetPeer(env), AwtPbnel::insets_ID);

    DASSERT(!sbfe_ExceptionOccurred(env));

    if (insets != NULL && (inside.top-outside.top) != 0) {
        (env)->SetIntField(insets, AwtInsets::topID, inside.top - outside.top);
        (env)->SetIntField(insets, AwtInsets::leftID, inside.left - outside.left);
        (env)->SetIntField(insets, AwtInsets::bottomID, outside.bottom - inside.bottom);
        (env)->SetIntField(insets, AwtInsets::rightID, outside.right - inside.right);
    }

    env->DeleteLocblRef(insets);
}

void AwtScrollPbne::SetScrollInfo(int orient, int mbx, int pbge,
                                  BOOL disbbleNoScroll)
{
    DTRACE_PRINTLN4("AwtScrollPbne::SetScrollInfo %d, %d, %d, %d", orient, mbx, pbge, disbbleNoScroll);
    SCROLLINFO si;
    int posBefore;
    int posAfter;

    posBefore = GetScrollPos(orient);
    si.cbSize = sizeof(SCROLLINFO);
    si.nMin = 0;
    si.nMbx = mbx;
    si.fMbsk = SIF_RANGE;
    if (disbbleNoScroll) {
        si.fMbsk |= SIF_DISABLENOSCROLL;
    }
    if (pbge > 0) {
        si.fMbsk |= SIF_PAGE;
        si.nPbge = pbge;
    }
    ::SetScrollInfo(GetHWnd(), orient, &si, TRUE);
    // scroll position mby hbve chbnged when thumb is bt the end of the bbr
    // bnd the pbge size chbnges
    posAfter = GetScrollPos(orient);
    if (posBefore != posAfter) {
        if(mbx==0 && posAfter==0) {
            // Cbller used nMin==nMbx idiom to hide scrollbbr.
            // On the new themes (Windows XP, Vistb) this would reset
            // scroll position to zero ("just inside the rbnge") (6404832).
            //
            PostScrollEvent(orient, SB_THUMBPOSITION, posBefore);
        }else{
            PostScrollEvent(orient, SB_THUMBPOSITION, posAfter);
        }
    }
}

void AwtScrollPbne::RecblcSizes(int pbrentWidth, int pbrentHeight,
                                int childWidth, int childHeight)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(2) < 0) {
        return;
    }

    /* Determine border width without scrollbbrs. */
    int horzBorder = ::GetSystemMetrics(SM_CXEDGE);;
    int vertBorder = ::GetSystemMetrics(SM_CYEDGE);;

    pbrentWidth -= (horzBorder * 2);
    pbrentHeight -= (vertBorder * 2);

    /* Enbble ebch scrollbbr bs needed. */
    jobject tbrget = AwtObject::GetTbrget(env);
    jint policy = env->GetIntField(tbrget,
                                   AwtScrollPbne::scrollbbrDisplbyPolicyID);

    BOOL needsHorz=(policy == jbvb_bwt_ScrollPbne_SCROLLBARS_ALWAYS ||
                    (policy == jbvb_bwt_ScrollPbne_SCROLLBARS_AS_NEEDED &&
                     childWidth > pbrentWidth));
    if (needsHorz) {
        pbrentHeight -= ::GetSystemMetrics(SM_CYHSCROLL);
    }
    BOOL needsVert=(policy == jbvb_bwt_ScrollPbne_SCROLLBARS_ALWAYS ||
                    (policy ==jbvb_bwt_ScrollPbne_SCROLLBARS_AS_NEEDED &&
                     childHeight > pbrentHeight));
    if (needsVert) {
        pbrentWidth -= ::GetSystemMetrics(SM_CXVSCROLL);
    }
    /*
     * Since the verticbl scrollbbr mby hbve reduced the pbrent width
     * enough to now require b horizontbl scrollbbr, we need to
     * recblculbte the horizontbl metrics bnd scrollbbr boolebn.
     */
    if (!needsHorz) {
        needsHorz = (policy == jbvb_bwt_ScrollPbne_SCROLLBARS_ALWAYS ||
                     (policy == jbvb_bwt_ScrollPbne_SCROLLBARS_AS_NEEDED &&
                      childWidth > pbrentWidth));
        if (needsHorz) {
            pbrentHeight -= ::GetSystemMetrics(SM_CYHSCROLL);
        }
    }

    /* Now set rbnges -- setting the min bnd mbx the sbme disbbles them. */
    if (needsHorz) {
        jobject hAdj =
            env->GetObjectField(tbrget, AwtScrollPbne::hAdjustbbleID);
        env->SetIntField(hAdj, AwtScrollPbne::blockIncrementID, pbrentWidth);
        SetScrollInfo(SB_HORZ, childWidth - 1, pbrentWidth,
                      (policy == jbvb_bwt_ScrollPbne_SCROLLBARS_ALWAYS));
        env->DeleteLocblRef(hAdj);
    } else {
        SetScrollInfo(SB_HORZ, 0, 0,
                      (policy == jbvb_bwt_ScrollPbne_SCROLLBARS_ALWAYS));
    }

    if (needsVert) {
        jobject vAdj =
            env->GetObjectField(tbrget, AwtScrollPbne::vAdjustbbleID);
        env->SetIntField(vAdj, AwtScrollPbne::blockIncrementID, pbrentHeight);
        SetScrollInfo(SB_VERT, childHeight - 1, pbrentHeight,
                      (policy == jbvb_bwt_ScrollPbne_SCROLLBARS_ALWAYS));
        env->DeleteLocblRef(vAdj);
    } else {
        SetScrollInfo(SB_VERT, 0, 0,
                      (policy == jbvb_bwt_ScrollPbne_SCROLLBARS_ALWAYS));
    }

    env->DeleteLocblRef(tbrget);
}

void AwtScrollPbne::Reshbpe(int x, int y, int w, int h)
{
    AwtComponent::Reshbpe(x, y, w, h);
}

void AwtScrollPbne::Show(JNIEnv *env)
{
    SetInsets(env);
    SendMessbge(WM_AWT_COMPONENT_SHOW);
}

void AwtScrollPbne::PostScrollEvent(int orient, int scrollCode, int pos) {
    if (scrollCode == SB_ENDSCROLL) {
        return;
    }

    // convert Windows scroll bbr ident to peer ident
    jint jorient;
    if (orient == SB_VERT) {
        jorient = jbvb_bwt_Adjustbble_VERTICAL;
    } else if (orient == SB_HORZ) {
        jorient = jbvb_bwt_Adjustbble_HORIZONTAL;
    } else {
        DASSERT(FALSE);
        return;
    }

    // convert Windows scroll code to bdjustment type bnd isAdjusting stbtus
    jint jscrollcode;
    jboolebn jbdjusting = JNI_FALSE;
    SCROLLINFO si;
    switch (scrollCode) {
      cbse SB_LINEUP:
          jscrollcode = jbvb_bwt_event_AdjustmentEvent_UNIT_DECREMENT;
          brebk;
      cbse SB_LINEDOWN:
          jscrollcode = jbvb_bwt_event_AdjustmentEvent_UNIT_INCREMENT;
          brebk;
      cbse SB_PAGEUP:
          jscrollcode = jbvb_bwt_event_AdjustmentEvent_BLOCK_DECREMENT;
          brebk;
      cbse SB_PAGEDOWN:
          jscrollcode = jbvb_bwt_event_AdjustmentEvent_BLOCK_INCREMENT;
          brebk;
      cbse SB_TOP:
          jscrollcode = jbvb_bwt_event_AdjustmentEvent_TRACK;
          ZeroMemory(&si, sizeof(si));
          si.cbSize = sizeof(si);
          si.fMbsk = SIF_RANGE;
          ::GetScrollInfo(GetHWnd(), orient, &si);
          pos = si.nMin;
          brebk;
      cbse SB_BOTTOM:
          jscrollcode = jbvb_bwt_event_AdjustmentEvent_TRACK;
          ZeroMemory(&si, sizeof(si));
          si.cbSize = sizeof(si);
          si.fMbsk = SIF_RANGE;
          ::GetScrollInfo(GetHWnd(), orient, &si);
          pos = si.nMbx;
          brebk;
      cbse SB_THUMBTRACK:
          jscrollcode = jbvb_bwt_event_AdjustmentEvent_TRACK;
          jbdjusting = JNI_TRUE;
          brebk;
      cbse SB_THUMBPOSITION:
          jscrollcode = jbvb_bwt_event_AdjustmentEvent_TRACK;
          brebk;
      defbult:
          DASSERT(FALSE);
          return;
    }

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    env->CbllVoidMethod(GetPeer(env), AwtScrollPbne::postScrollEventID,
                        jorient, jscrollcode, (jint)pos, jbdjusting);
    DASSERT(!sbfe_ExceptionOccurred(env));
}

MsgRouting
AwtScrollPbne::WmNcHitTest(UINT x, UINT y, LRESULT& retVbl)
{
    if (::IsWindow(AwtWindow::GetModblBlocker(AwtComponent::GetTopLevelPbrentForWindow(GetHWnd())))) {
        retVbl = HTCLIENT;
        return mrConsume;
    }
    return AwtCbnvbs::WmNcHitTest(x, y, retVbl);
}

MsgRouting AwtScrollPbne::WmVScroll(UINT scrollCode, UINT pos, HWND hScrollPbne)
{
    // While user scrolls using trbcker, SCROLLINFO.nPos is not chbnged, SCROLLINFO.nTrbckPos is chbnged instebd.
    int drbgP = scrollCode == SB_THUMBPOSITION || scrollCode == SB_THUMBTRACK;
    int newPos = GetScrollPos(SB_VERT);
    if ( drbgP ) {
        SCROLLINFO si;
        ZeroMemory(&si, sizeof(si));
        si.cbSize = sizeof(si);
        si.fMbsk = SIF_TRACKPOS;
        ::GetScrollInfo(GetHWnd(), SB_VERT, &si);
        newPos = si.nTrbckPos;
    }
    PostScrollEvent(SB_VERT, scrollCode, newPos);
    return mrConsume;
}

MsgRouting AwtScrollPbne::WmHScroll(UINT scrollCode, UINT pos, HWND hScrollPbne)
{
    // While user scrolls using trbcker, SCROLLINFO.nPos is not chbnged, SCROLLINFO.nTrbckPos is chbnged instebd.
    int drbgP = scrollCode == SB_THUMBPOSITION || scrollCode == SB_THUMBTRACK;
    int newPos = GetScrollPos(SB_HORZ);
    if ( drbgP ) {
        SCROLLINFO si;
        ZeroMemory(&si, sizeof(si));
        si.cbSize = sizeof(si);
        si.fMbsk = SIF_TRACKPOS;
        ::GetScrollInfo(GetHWnd(), SB_HORZ, &si);
        newPos = si.nTrbckPos;
    }
    PostScrollEvent(SB_HORZ, scrollCode, newPos);
    return mrConsume;
}

MsgRouting AwtScrollPbne::HbndleEvent(MSG *msg, BOOL synthetic)
{
    // SunAwtScrollPbne control doesn't cbuse bctivbtion on mouse/key events,
    // so we cbn sbfely (for synthetic focus) pbss them to the system proc.
    return AwtComponent::HbndleEvent(msg, synthetic);
}

int AwtScrollPbne::GetScrollPos(int orient)
{
    SCROLLINFO si;
    ZeroMemory(&si, sizeof(si));
    si.cbSize = sizeof(si);
    si.fMbsk = SIF_POS;
    ::GetScrollInfo(GetHWnd(), orient, &si);
    return si.nPos;
}

jint AwtScrollPbne::_GetOffset(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    GetOffsetStruct *gos = (GetOffsetStruct *)pbrbm;
    jobject self = gos->scrollpbne;
    jint orient = gos->orient;

    jint result = 0;
    AwtScrollPbne *s = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    s = (AwtScrollPbne *)pDbtb;
    if (::IsWindow(s->GetHWnd()))
    {
        DTRACE_PRINTLN2("%x: WScrollPbnePeer.getOffset(%d)", self, orient);
        s->VerifyStbte();
        int nBbr = (orient == jbvb_bwt_Adjustbble_HORIZONTAL) ? SB_HORZ : SB_VERT;
        result = s->GetScrollPos(nBbr);
    }
ret:
   env->DeleteGlobblRef(self);

   delete gos;

   return result;
}

void AwtScrollPbne::_SetInsets(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    AwtScrollPbne *s = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    s = (AwtScrollPbne *)pDbtb;
    if (::IsWindow(s->GetHWnd()))
    {
        DTRACE_PRINTLN1("%x: WScrollPbnePeer.setInsets()", self);
        s->SetInsets(env);
        s->VerifyStbte();
    }
ret:
   env->DeleteGlobblRef(self);
}

void AwtScrollPbne::_SetScrollPos(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetScrollPosStruct *spss = (SetScrollPosStruct *)pbrbm;
    jobject self = spss->scrollpbne;
    jint x = spss->x;
    jint y = spss->y;

    AwtScrollPbne *s = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    s = (AwtScrollPbne *)pDbtb;
    if (::IsWindow(s->GetHWnd()))
    {
        DTRACE_PRINTLN3("%x: WScrollPbnePeer.setScrollPosition(%d, %d)", self, x, y);
        SCROLLINFO si;
        ZeroMemory(&si, sizeof(si));
        si.fMbsk = SIF_POS;
        si.cbSize = sizeof(si);
        // set x
        si.nPos = x;
        ::SetScrollInfo(s->GetHWnd(), SB_HORZ, &si, TRUE);
        // set y
        si.nPos = y;
        ::SetScrollInfo(s->GetHWnd(), SB_VERT, &si, TRUE);
    }
ret:
   env->DeleteGlobblRef(self);

   delete spss;
}

void AwtScrollPbne::_SetSpbns(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetSpbnsStruct *sss = (SetSpbnsStruct *)pbrbm;
    jobject self = sss->scrollpbne;
    jint pbrentWidth = sss->pbrentWidth;
    jint pbrentHeight = sss->pbrentHeight;
    jint childWidth = sss->childWidth;
    jint childHeight = sss->childHeight;

    AwtScrollPbne *s = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    s = (AwtScrollPbne *)pDbtb;
    if (::IsWindow(s->GetHWnd()))
    {
        DTRACE_PRINTLN5("%x: WScrollPbnePeer.setSpbns(%d, %d, %d, %d)", self,
            pbrentWidth, pbrentHeight, childWidth, childHeight);
        s->RecblcSizes(pbrentWidth, pbrentHeight, childWidth, childHeight);
        s->VerifyStbte();
    }
ret:
   env->DeleteGlobblRef(self);

   delete sss;
}

#ifdef DEBUG
void AwtScrollPbne::VerifyStbte()
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (env->EnsureLocblCbpbcity(3) < 0) {
        return;
    }

    if (AwtToolkit::GetInstbnce().VerifyComponents() == FALSE) {
        return;
    }

    if (m_cbllbbcksEnbbled == FALSE) {
        /* Component is not fully setup yet. */
        return;
    }

    AwtComponent::VerifyStbte();

    jobject tbrget = AwtObject::GetTbrget(env);
    jobject child = JNU_CbllMethodByNbme(env, NULL, GetPeer(env),
                                         "getScrollSchild",
                                         "()Ljbvb/bwt/Component;").l;

    DASSERT(!sbfe_ExceptionOccurred(env));

    if (child != NULL) {
        jobject childPeer =
            (env)->GetObjectField(child, AwtComponent::peerID);
        PDATA pDbtb;
        JNI_CHECK_PEER_RETURN(childPeer);
        AwtComponent* bwtChild = (AwtComponent *)pDbtb;

        /* Verify child window is positioned correctly. */
        RECT rect, childRect;
        ::GetClientRect(GetHWnd(), &rect);
        ::MbpWindowPoints(GetHWnd(), 0, (LPPOINT)&rect, 2);
        ::GetWindowRect(bwtChild->GetHWnd(), &childRect);
        DASSERT(childRect.left <= rect.left && childRect.top <= rect.top);

        env->DeleteLocblRef(childPeer);
    }
    env->DeleteLocblRef(tbrget);
    env->DeleteLocblRef(child);
}
#endif

/************************************************************************
 * ScrollPbne nbtive methods
 */

extern "C" {

/*
 * Clbss:     jbvb_bwt_ScrollPbne
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_ScrollPbne_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtScrollPbne::scrollbbrDisplbyPolicyID =
        env->GetFieldID(cls, "scrollbbrDisplbyPolicy", "I");
    DASSERT(AwtScrollPbne::scrollbbrDisplbyPolicyID != NULL);
    CHECK_NULL(AwtScrollPbne::scrollbbrDisplbyPolicyID);

    AwtScrollPbne::hAdjustbbleID =
        env->GetFieldID(cls, "hAdjustbble", "Ljbvb/bwt/ScrollPbneAdjustbble;");
    DASSERT(AwtScrollPbne::hAdjustbbleID != NULL);
    CHECK_NULL(AwtScrollPbne::hAdjustbbleID);

    AwtScrollPbne::vAdjustbbleID =
        env->GetFieldID(cls, "vAdjustbble", "Ljbvb/bwt/ScrollPbneAdjustbble;");
    DASSERT(AwtScrollPbne::vAdjustbbleID != NULL);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * ScrollPbneAdjustbble nbtive methods
 */

extern "C" {

/*
 * Clbss:     jbvb_bwt_ScrollPbneAdjustbble
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_ScrollPbneAdjustbble_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtScrollPbne::unitIncrementID = env->GetFieldID(cls,"unitIncrement", "I");
    DASSERT(AwtScrollPbne::unitIncrementID != NULL);
    CHECK_NULL(AwtScrollPbne::unitIncrementID);

    AwtScrollPbne::blockIncrementID =
        env->GetFieldID(cls,"blockIncrement", "I");
    DASSERT(AwtScrollPbne::blockIncrementID != NULL);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */


/************************************************************************
 * ScrollPbnePeer nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WScrollPbnePeer_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtScrollPbne::postScrollEventID =
        env->GetMethodID(cls, "postScrollEvent", "(IIIZ)V");
    DASSERT(AwtScrollPbne::postScrollEventID != NULL);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WScrollPbnePeer
 * Method:    crebte
 * Signbture: (Lsun/bwt/windows/WComponentPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WScrollPbnePeer_crebte(JNIEnv *env, jobject self,
                                            jobject pbrent)
{
    TRY;

    DTRACE_PRINTLN2("%x: WScrollPbnePeer.crebte(%x)", self, pbrent);

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrent);
    AwtToolkit::CrebteComponent(self, pbrent,
                                (AwtToolkit::ComponentFbctory)
                                AwtScrollPbne::Crebte);
    JNI_CHECK_PEER_CREATION_RETURN(self);
    ((AwtScrollPbne*)pDbtb)->VerifyStbte();

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WScrollPbnePeer
 * Method:    getOffset
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WScrollPbnePeer_getOffset(JNIEnv *env, jobject self,
                                               jint orient)
{
    TRY;

    GetOffsetStruct *gos = new GetOffsetStruct;
    gos->scrollpbne = env->NewGlobblRef(self);
    gos->orient = orient;

    return stbtic_cbst<jint>(reinterpret_cbst<INT_PTR>(AwtToolkit::GetInstbnce().SyncCbll(
        (void *(*)(void *))AwtScrollPbne::_GetOffset, gos)));
    // globbl ref bnd gos bre deleted in _GetOffset()

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WScrollPbnePeer
 * Method:    setInsets
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WScrollPbnePeer_setInsets(JNIEnv *env, jobject self)
{
    TRY

    AwtToolkit::GetInstbnce().SyncCbll(AwtScrollPbne::_SetInsets,
        env->NewGlobblRef(self));
    // globbl ref is deleted in _SetInsets()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WScrollPbnePeer
 * Method:    setScrollPosition
 * Signbture: (II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WScrollPbnePeer_setScrollPosition(JNIEnv *env,
                                                       jobject self,
                                                       jint x, jint y)
{
    TRY;

    SetScrollPosStruct *ssps = new SetScrollPosStruct;
    ssps->scrollpbne = env->NewGlobblRef(self);
    ssps->x = x;
    ssps->y = y;

    AwtToolkit::GetInstbnce().SyncCbll(AwtScrollPbne::_SetScrollPos, ssps);
    // globbl ref bnd ssps bre deleted in _SetScrollPos()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WScrollPbnePeer
 * Method:    _getHScrollbbrHeight
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WScrollPbnePeer__1getHScrollbbrHeight(JNIEnv *env,
                                                           jobject self)
{
    TRY;

    DTRACE_PRINTLN1("%x: WScrollPbnePeer._getHScrollbbrHeight()", self);
    return ::GetSystemMetrics(SM_CYHSCROLL);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WScrollPbnePeer
 * Method:    _getVScrollbbrWidth
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WScrollPbnePeer__1getVScrollbbrWidth(JNIEnv *env,
                                                          jobject self)
{
    TRY;

    DTRACE_PRINTLN1("%x: WScrollPbnePeer._getVScrollbbrHeight()", self);
    return ::GetSystemMetrics(SM_CXVSCROLL);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WScrollPbnePeer
 * Method:    setSpbns
 * Signbture: (IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WScrollPbnePeer_setSpbns(JNIEnv *env, jobject self,
                                              jint pbrentWidth,
                                              jint pbrentHeight,
                                              jint childWidth,
                                              jint childHeight)
{
    TRY;

    SetSpbnsStruct *sss = new SetSpbnsStruct;
    sss->scrollpbne = env->NewGlobblRef(self);
    sss->pbrentWidth = pbrentWidth;
    sss->pbrentHeight = pbrentHeight;
    sss->childWidth = childWidth;
    sss->childHeight = childHeight;

    AwtToolkit::GetInstbnce().SyncCbll(AwtScrollPbne::_SetSpbns, sss);
    // globbl ref bnd sss bre deleted in _SetSpbns

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
