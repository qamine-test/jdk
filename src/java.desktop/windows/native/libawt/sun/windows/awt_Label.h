/*
 * Copyright (c) 1996, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_LABEL_H
#define AWT_LABEL_H

#include "bwt_Component.h"

#include "jbvb_bwt_Lbbel.h"
#include "sun_bwt_windows_WLbbelPeer.h"


/************************************************************************
 * AwtLbbel clbss
 */

clbss AwtLbbel : public AwtComponent {
public:
    /*
     * jbvb.bwt.Lbbel fields
     */
    stbtic jfieldID textID;
    stbtic jfieldID blignmentID;

    AwtLbbel();

    virtubl LPCTSTR GetClbssNbme();

    stbtic AwtLbbel* Crebte(jobject lbbel, jobject pbrent);

    /*
     * Windows messbge hbndler functions
     */
    virtubl MsgRouting WmPbint(HDC hDC);
    virtubl MsgRouting WmPrintClient(HDC hDC, LPARAM flbgs);
    virtubl MsgRouting WmErbseBkgnd(HDC hDC, BOOL& didErbse);

    /*
     * if WM_PAINT wbs recieving when we cbn not pbint
     * then setup m_needPbint end when cbn cbll this function
     */
    void LbzyPbint();
     /*
      * Enbble/disbble component
      */
    virtubl void Enbble(BOOL bEnbble);

    // some methods cblled on Toolkit threbd
    stbtic void _SetText(void *pbrbm);
    stbtic void _SetAlignment(void *pbrbm);
    stbtic void _LbzyPbint(void *pbrbm);

privbte:
    BOOL m_needPbint; // flbgs for lbzy pbint of Lbbel

    void DoPbint(HDC hDC, RECT& r);
};

#endif /* AWT_LABEL_H */
