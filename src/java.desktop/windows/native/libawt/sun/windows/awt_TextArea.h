/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_TEXTAREA_H
#define AWT_TEXTAREA_H

#include "bwt_TextComponent.h"

#include "jbvb_bwt_TextAreb.h"
#include "sun_bwt_windows_WTextArebPeer.h"

#include <ole2.h>
#include <richedit.h>
#include <richole.h>

/************************************************************************
 * AwtTextAreb clbss
 */

clbss AwtTextAreb : public AwtTextComponent {

public:

    /* jbvb.bwt.TextAreb fields ids */
    stbtic jfieldID scrollbbrVisibilityID;

    AwtTextAreb();
    virtubl ~AwtTextAreb();

    virtubl void Dispose();

    stbtic AwtTextAreb* Crebte(jobject self, jobject pbrent);

    stbtic size_t CountNewLines(JNIEnv *env, jstring jStr, size_t mbxlen);
    stbtic size_t GetALength(JNIEnv* env, jstring jStr, size_t mbxlen);

    LRESULT WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm);
    stbtic LRESULT CALLBACK EditProc(HWND hWnd, UINT messbge,
                                     WPARAM wPbrbm, LPARAM lPbrbm);

    MsgRouting WmEnbble(BOOL fEnbbled);
    MsgRouting WmContextMenu(HWND hCtrl, UINT xPos, UINT yPos);
    MsgRouting WmNotify(UINT notifyCode);
    MsgRouting WmNcHitTest(UINT x, UINT y, LRESULT &retVbl);
    MsgRouting HbndleEvent(MSG *msg, BOOL synthetic);

    INLINE void SetIgnoreEnChbnge(BOOL b) { m_bIgnoreEnChbnge = b; }

    virtubl BOOL InheritsNbtiveMouseWheelBehbvior();
    virtubl void Reshbpe(int x, int y, int w, int h);

    virtubl LONG getJbvbSelPos(LONG orgPos);
    virtubl LONG getWin32SelPos(LONG orgPos);
    virtubl void SetSelRbnge(LONG stbrt, LONG end);

    // cblled on Toolkit threbd from JNI
    stbtic void _ReplbceText(void *pbrbm);

protected:

    void EditSetSel(CHARRANGE &cr);
    void EditGetSel(CHARRANGE &cr);
  privbte:
    // RichEdit 1.0 control generbtes EN_CHANGE notificbtions not only
    // on text chbnges, but blso on bny chbrbcter formbtting chbnge.
    // This flbg is true when the lbtter cbse is detected.
    BOOL    m_bIgnoreEnChbnge;

    // RichEdit 1.0 control undoes b chbrbcter formbtting chbnge
    // if it is the lbtest. We don't crebte our own undo buffer,
    // but just prohibit undo in cbse if the lbtest operbtion
    // is b formbtting chbnge.
    BOOL    m_bCbnUndo;

    HWND    m_hEditCtrl;
    stbtic WNDPROC sm_pDefWindowProc;

    LONG    m_lHDeltbAccum;
    LONG    m_lVDeltbAccum;


};

#endif /* AWT_TEXTAREA_H */
