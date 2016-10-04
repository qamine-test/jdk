/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef D3DBufImgOps_h_Included
#define D3DBufImgOps_h_Included

#include "D3DContext.h"

/**************************** ConvolveOp support ****************************/

/**
 * Flbgs thbt cbn be bitwise-or'ed together to control how the shbder
 * source code is generbted.
 */
#define CONVOLVE_EDGE_ZERO_FILL (1 << 0)
#define CONVOLVE_5X5            (1 << 1)
#define MAX_CONVOLVE            (1 << 2)

HRESULT D3DBufImgOps_EnbbleConvolveOp(D3DContext *oglc, jlong pSrcOps,
                                      jboolebn edgeZeroFill,
                                      jint kernelWidth, jint KernelHeight,
                                      unsigned chbr *kernelVbls);
HRESULT D3DBufImgOps_DisbbleConvolveOp(D3DContext *oglc);

/**************************** RescbleOp support *****************************/

/**
 * Flbgs thbt cbn be bitwise-or'ed together to control how the shbder
 * source code is generbted.
 */
#define RESCALE_NON_PREMULT (1 << 0)
#define MAX_RESCALE         (1 << 1)

HRESULT D3DBufImgOps_EnbbleRescbleOp(D3DContext *oglc,
                                     jboolebn nonPremult,
                                     unsigned chbr *scbleFbctors,
                                     unsigned chbr *offsets);
HRESULT D3DBufImgOps_DisbbleRescbleOp(D3DContext *oglc);

/**************************** LookupOp support ******************************/

/**
 * Flbgs thbt cbn be bitwise-or'ed together to control how the shbder
 * source code is generbted.
 */
#define LOOKUP_USE_SRC_ALPHA (1 << 0)
#define LOOKUP_NON_PREMULT   (1 << 1)
#define MAX_LOOKUP           (1 << 2)

HRESULT D3DBufImgOps_EnbbleLookupOp(D3DContext *oglc,
                                    jboolebn nonPremult, jboolebn shortDbtb,
                                    jint numBbnds, jint bbndLength, jint offset,
                                    void *tbbleVblues);
HRESULT D3DBufImgOps_DisbbleLookupOp(D3DContext *oglc);

#endif /* D3DBufImgOps_h_Included */
