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

#ifndef AWT_TEXTFIELD_H
#define AWT_TEXTFIELD_H

#include "bwt_TextComponent.h"

#include "jbvb_bwt_TextField.h"
#include "sun_bwt_windows_WTextFieldPeer.h"

#include <ole2.h>
#include <richedit.h>
#include <richole.h>

/************************************************************************
 * AwtTextField clbss
 */

clbss AwtTextField : public AwtTextComponent {
public:
    AwtTextField();

    stbtic AwtTextField* Crebte(jobject self, jobject pbrent);

    /*
     *  Windows messbge hbndler functions
     */
    MsgRouting HbndleEvent(MSG *msg, BOOL synthetic);

    virtubl LRESULT WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm);
    // invoked on Toolkit threbd
    stbtic void _SetEchoChbr(void *pbrbm);

protected:

privbte:
    void EditSetSel(CHARRANGE &cr);

};

#endif /* AWT_TEXTFIELD_H */
