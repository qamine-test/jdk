/*
 * Copyright (c) 1999, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _AWT_PRINT_DIALOG_H_
#define _AWT_PRINT_DIALOG_H_

#include "stdhdrs.h"
#include <commdlg.h>

/************************************************************************
 * AwtPrintDiblog clbss
 */

clbss AwtPrintDiblog {
public:
    stbtic jfieldID AwtPrintDiblog::controlID;
    stbtic jfieldID AwtPrintDiblog::pbrentID;
    stbtic jfieldID AwtPrintDiblog::pbgeID;
    stbtic jmethodID AwtPrintDiblog::setHWndMID;

    stbtic BOOL PrintDlg(LPPRINTDLG);

    // cblled on Toolkit threbd
    stbtic jboolebn _Show(void *pbrbm);

    stbtic void _ToFront(void *pbrbm);
    stbtic void _ToBbck(void *pbrbm);
};

#endif
