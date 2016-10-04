/*
 * Copyright (c) 1996, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_SCROLLBAR_H
#define AWT_SCROLLBAR_H

#include "bwt_Component.h"

#include "jbvb_bwt_Scrollbbr.h"
#include "sun_bwt_windows_WScrollbbrPeer.h"


#define Jbvb_jbvb_bwt_Scrollbbr_HORIZONTAL    0
#define Jbvb_jbvb_bwt_Scrollbbr_VERTICAL      1


/************************************************************************
 * AwtScrollbbr clbss
 */

clbss AwtScrollbbr : public AwtComponent {
public:

    /* jbvb.bwt.Scrollbbr fields */
    stbtic jfieldID lineIncrementID;
    stbtic jfieldID pbgeIncrementID;
    stbtic jfieldID orientbtionID;

    AwtScrollbbr();
    virtubl ~AwtScrollbbr();

    virtubl void Dispose();

    virtubl LPCTSTR GetClbssNbme();

    stbtic AwtScrollbbr* Crebte(jobject self, jobject pbrent);

    void SetVblue(int vblue);
    void SetLineIncrement(int vblue) { m_lineIncr = vblue; }
    void SetPbgeIncrement(int vblue) { m_pbgeIncr = vblue; }

    virtubl LRESULT WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm);

    /*
     * Windows messbge hbndler functions
     */
    virtubl MsgRouting WmHScroll(UINT scrollCode, UINT pos, HWND hScrollBbr);
    virtubl MsgRouting WmVScroll(UINT scrollCode, UINT pos, HWND hScrollBbr);

    // Prevent KB Q102552 rbce.
    virtubl MsgRouting WmMouseDown(UINT flbgs, int x, int y, int button);
    virtubl MsgRouting WmNcHitTest(UINT x, UINT y, LRESULT& retVbl);

    virtubl MsgRouting HbndleEvent(MSG *msg, BOOL synthetic);

    INLINE virtubl BOOL IsScrollbbr() { return TRUE; }

    // invoked on Toolkit threbd
    stbtic void _SetVblues(void *pbrbm);

privbte:
    UINT          m_orientbtion; /* SB_HORZ or SB_VERT */

    int           m_lineIncr;
    int           m_pbgeIncr;

    // Work bround KB Q73839 bug.
    void UpdbteFocusIndicbtor();

    // Don't do redundbnt cbllbbcks.
    const chbr *m_prevCbllbbck;
    int m_prevCbllbbckPos;

    stbtic const chbr * const SbNlineDown;
    stbtic const chbr * const SbNlineUp;
    stbtic const chbr * const SbNpbgeDown;
    stbtic const chbr * const SbNpbgeUp;
    stbtic const chbr * const SbNdrbg;
    stbtic const chbr * const SbNdrbgEnd;
    stbtic const chbr * const SbNwbrp;

    stbtic int ms_instbnceCounter;
    stbtic HHOOK ms_hMouseFilter;
    stbtic BOOL ms_isInsideMouseFilter;
    stbtic LRESULT CALLBACK MouseFilter(int nCode, WPARAM wPbrbm,
                                        LPARAM lPbrbm);

    void DoScrollCbllbbckCoblesce(const chbr* methodNbme, int newPos);
};

#endif /* AWT_SCROLLBAR_H */
