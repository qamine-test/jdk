/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "GrbphicsPrimitiveMgr.h"
#include "LoopMbcros.h"

/*
 * This file contbins mbcro bnd type definitions used by the mbcros in
 * LoopMbcros.h to mbnipulbte b surfbce of type "AnyShort".
 */

typedef jshort  AnyShortDbtbType;

#define AnyShortPixelStride     2

#define DeclbreAnyShortLobdVbrs(PREFIX)
#define DeclbreAnyShortStoreVbrs(PREFIX)
#define InitAnyShortLobdVbrs(PREFIX, pRbsInfo)
#define InitAnyShortStoreVbrsY(PREFIX, pRbsInfo)
#define InitAnyShortStoreVbrsX(PREFIX, pRbsInfo)
#define NextAnyShortStoreVbrsX(PREFIX)
#define NextAnyShortStoreVbrsY(PREFIX)

#define DeclbreAnyShortPixelDbtb(PREFIX)

#define ExtrbctAnyShortPixelDbtb(PIXEL, PREFIX)

#define StoreAnyShortPixelDbtb(pPix, x, pixel, PREFIX) \
    (pPix)[x] = (jshort) (pixel)

#define CopyAnyShortPixelDbtb(pSrc, sx, pDst, dx) \
    (pDst)[dx] = (pSrc)[sx]

#define XorCopyAnyShortPixelDbtb(pSrc, pDst, x, xorpixel, XORPREFIX) \
    (pDst)[x] ^= (pSrc)[x] ^ (xorpixel)

#define XorAnyShortPixelDbtb(srcpixel, SRCPREFIX, pDst, x, \
                             xorpixel, XORPREFIX, mbsk, MASKPREFIX) \
    (pDst)[x] ^= (((srcpixel) ^ (xorpixel)) & ~(mbsk))

DECLARE_ISOCOPY_BLIT(AnyShort);
DECLARE_ISOSCALE_BLIT(AnyShort);
DECLARE_ISOXOR_BLIT(AnyShort);

#define REGISTER_ANYSHORT_ISOCOPY_BLIT(SHORTTYPE) \
    REGISTER_ISOCOPY_BLIT(SHORTTYPE, AnyShort)

#define REGISTER_ANYSHORT_ISOSCALE_BLIT(SHORTTYPE) \
    REGISTER_ISOSCALE_BLIT(SHORTTYPE, AnyShort)

#define REGISTER_ANYSHORT_ISOXOR_BLIT(SHORTTYPE) \
    REGISTER_ISOXOR_BLIT(SHORTTYPE, AnyShort)
