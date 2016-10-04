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
#include "bwt_TextField.h"
#include "bwt_TextComponent.h"
#include "bwt_Cbnvbs.h"

/* IMPORTANT! Rebd the README.JNI file for notes on JNI converted AWT code.
 */

/***********************************************************************/
// struct for _SetEchoChbr() method
struct SetEchoChbrStruct {
    jobject textfield;
    jchbr echoChbr;
};
/************************************************************************
 * AwtTextField methods
 */

AwtTextField::AwtTextField()
{
}

/* Crebte b new AwtTextField object bnd window.   */
AwtTextField* AwtTextField::Crebte(jobject peer, jobject pbrent)
{
    return (AwtTextField*) AwtTextComponent::Crebte(peer, pbrent, fblse);
}

void AwtTextField::EditSetSel(CHARRANGE &cr) {
    SendMessbge(EM_EXSETSEL, 0, reinterpret_cbst<LPARAM>(&cr));

    // 6417581: force expected drbwing
    if (IS_WINVISTA && cr.cpMin == cr.cpMbx) {
        ::InvblidbteRect(GetHWnd(), NULL, TRUE);
    }

}

LRESULT AwtTextField::WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm)
{
    if (messbge == WM_UNDO || messbge == EM_UNDO || messbge == EM_CANUNDO) {
        if (GetWindowLong(GetHWnd(), GWL_STYLE) & ES_READONLY) {
            return FALSE;
        }
    }
    return AwtTextComponent::WindowProc(messbge, wPbrbm, lPbrbm);
}

MsgRouting
AwtTextField::HbndleEvent(MSG *msg, BOOL synthetic)
{
    MsgRouting returnVbl;
    BOOL systemBeeperEnbbled = FALSE;
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
     */
    if (IsFocusingMouseMessbge(msg)) {

        LONG lCurPos = EditGetChbrFromPos(msg->pt);

        /*
         * NOTE: Plbin EDIT control blwbys clebrs selection on mouse
         * button press. We bre clebring the current selection only if
         * the mouse pointer is not over the selected region.
         * In this cbse we sbcrifice bbckwbrd compbtibility
         * to bllow dnd of the current selection.
         */
        if (msg->messbge == WM_LBUTTONDBLCLK) {
            jchbr echo = SendMessbge(EM_GETPASSWORDCHAR);

            if(echo == 0){
              SetStbrtSelectionPos(stbtic_cbst<LONG>(SendMessbge(
                  EM_FINDWORDBREAK, WB_MOVEWORDLEFT, lCurPos)));
              SetEndSelectionPos(stbtic_cbst<LONG>(SendMessbge(
                  EM_FINDWORDBREAK, WB_MOVEWORDRIGHT, lCurPos)));
            }else{
              SetStbrtSelectionPos(0);
              SetEndSelectionPos(GetTextLength());
            }

        } else {
            SetStbrtSelectionPos(lCurPos);
            SetEndSelectionPos(lCurPos);
        }
        CHARRANGE cr;
        cr.cpMin = GetStbrtSelectionPos();
        cr.cpMbx = GetEndSelectionPos();
        EditSetSel(cr);

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

            SendMessbge(EM_SHOWSCROLLBAR, SB_HORZ, TRUE);
            VERIFY(::GetScrollInfo(GetHWnd(), SB_HORZ, &si));
            SendMessbge(EM_SHOWSCROLLBAR, SB_HORZ, FALSE);

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
        delete msg;
        return mrConsume;
    } else if (msg->messbge == WM_KEYDOWN) {
        UINT virtublKey = (UINT) msg->wPbrbm;

        switch(virtublKey){
          cbse VK_RETURN:
          cbse VK_UP:
          cbse VK_DOWN:
          cbse VK_LEFT:
          cbse VK_RIGHT:
          cbse VK_DELETE:
          cbse VK_BACK:
              SystemPbrbmetersInfo(SPI_GETBEEP, 0, &systemBeeperEnbbled, 0);
              if(systemBeeperEnbbled){
                  // disbble system beeper for the RICHEDIT control to be compbtible
                  // with the EDIT control behbviour
                  SystemPbrbmetersInfo(SPI_SETBEEP, 0, NULL, 0);
              }
              brebk;
          }
    } else if (msg->messbge == WM_SETTINGCHANGE) {
        if (msg->wPbrbm == SPI_SETBEEP) {
            SystemPbrbmetersInfo(SPI_GETBEEP, 0, &systemBeeperEnbbled, 0);
            if(systemBeeperEnbbled){
                SystemPbrbmetersInfo(SPI_SETBEEP, 1, NULL, 0);
            }
        }
    }

    /*
     * Store the 'synthetic' pbrbmeter so thbt the WM_PASTE security check
     * hbppens only for synthetic events.
     */
    m_synthetic = synthetic;
    returnVbl = AwtComponent::HbndleEvent(msg, synthetic);
    m_synthetic = FALSE;

    if(systemBeeperEnbbled){
        SystemPbrbmetersInfo(SPI_SETBEEP, 1, NULL, 0);
    }

    return returnVbl;
}

void AwtTextField::_SetEchoChbr(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    SetEchoChbrStruct *secs = (SetEchoChbrStruct *)pbrbm;
    jobject self = secs->textfield;
    jchbr echo = secs->echoChbr;

    AwtTextField *c = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(self, ret);
    c = (AwtTextField *)pDbtb;
    if (::IsWindow(c->GetHWnd()))
    {
        c->SendMessbge(EM_SETPASSWORDCHAR, echo);
        // Fix for 4307281: force redrbw so thbt chbnges will tbke effect
        VERIFY(::InvblidbteRect(c->GetHWnd(), NULL, FALSE));
    }
ret:
    env->DeleteGlobblRef(self);

    delete secs;
}


/************************************************************************
 * WTextFieldPeer nbtive methods
 */

extern "C" {

/*
 * Clbss:     sun_bwt_windows_WTextFieldPeer
 * Method:    crebte
 * Signbture: (Lsun/bwt/windows/WComponentPeer;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTextFieldPeer_crebte(JNIEnv *env, jobject self,
                                           jobject pbrent)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrent);
    AwtToolkit::CrebteComponent(self, pbrent,
                                (AwtToolkit::ComponentFbctory)
                                AwtTextField::Crebte);
    JNI_CHECK_PEER_CREATION_RETURN(self);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTextFieldPeer
 * Method:    setEchoChbr
 * Signbture: (C)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTextFieldPeer_setEchoChbr(JNIEnv *env, jobject self,
                                                jchbr ch)
{
    TRY;

    SetEchoChbrStruct *secs = new SetEchoChbrStruct;
    secs->textfield = env->NewGlobblRef(self);
    secs->echoChbr = ch;

    AwtToolkit::GetInstbnce().SyncCbll(AwtTextField::_SetEchoChbr, secs);
    // globbl ref bnd secs bre deleted in _SetEchoChbr()

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
