/*
 * Copyright (c) 1997, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _VDrbwingArebP_h_
#define _VDrbwingArebP_h_

#include <Xm/DrbwingAP.h>
#include "VDrbwingAreb.h"


/***************************************************************
 * VDrbwingAreb Widget Dbtb Structures
 *
 *
 **************************************************************/

/* Define pbrt clbss structure */
typedef struct _VDrbwingArebClbss {
        XtPointer                       extension;
} VDrbwingArebClbssPbrt;

/* Define the full clbss record */
typedef struct _VDrbwingArebClbssRec {
        CoreClbssPbrt           core_clbss;
        CompositeClbssPbrt      composite_clbss;
        ConstrbintClbssPbrt     constrbint_clbss;
        XmMbnbgerClbssPbrt      mbnbger_clbss;
        XmDrbwingArebClbssPbrt  drbwing_breb_clbss;
        VDrbwingArebClbssPbrt   vdrbwingbreb_clbss;
} VDrbwingArebClbssRec;

/* Externbl definition for clbss record */
extern VDrbwingArebClbssRec vDrbwingArebClbssRec;

typedef struct {
        Visubl *visubl;
} VDrbwingArebPbrt;

/****************************************************************
 *
 * Full instbnce record declbrbtion
 *
 ****************************************************************/

typedef struct _VDrbwingArebRec
{
        CorePbrt                core;
        CompositePbrt           composite;
        ConstrbintPbrt          constrbint;
        XmMbnbgerPbrt           mbnbger;
        XmDrbwingArebPbrt       drbwing_breb;
        VDrbwingArebPbrt        vdrbwing_breb;
} VDrbwingArebRec;



#endif /* !_VDrbwingArebP_h_ */
