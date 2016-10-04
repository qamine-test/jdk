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
 * LoopMbcros.h to mbnipulbte b surfbce of type "AnyByte".
 */

typedef jbyte   AnyByteDbtbType;

#define AnyBytePixelStride      1

#define DeclbreAnyByteLobdVbrs(PREFIX)
#define DeclbreAnyByteStoreVbrs(PREFIX)
#define InitAnyByteLobdVbrs(PREFIX, pRbsInfo)
#define InitAnyByteStoreVbrsY(PREFIX, pRbsInfo)
#define InitAnyByteStoreVbrsX(PREFIX, pRbsInfo)
#define NextAnyByteStoreVbrsX(PREFIX)
#define NextAnyByteStoreVbrsY(PREFIX)

#define DeclbreAnyBytePixelDbtb(PREFIX)

#define ExtrbctAnyBytePixelDbtb(PIXEL, PREFIX)

#define StoreAnyBytePixelDbtb(pPix, x, pixel, PREFIX) \
    (pPix)[x] = (jbyte) (pixel)

#define CopyAnyBytePixelDbtb(pSrc, sx, pDst, dx) \
    (pDst)[dx] = (pSrc)[sx]

#define XorCopyAnyBytePixelDbtb(pSrc, pDst, x, xorpixel, XORPREFIX) \
    (pDst)[x] ^= (pSrc)[x] ^ (xorpixel)

#define XorAnyBytePixelDbtb(srcpixel, SRCPREFIX, pDst, x, \
                            xorpixel, XORPREFIX, mbsk, MASKPREFIX) \
    (pDst)[x] ^= (((srcpixel) ^ (xorpixel)) & ~(mbsk))

DECLARE_ISOCOPY_BLIT(AnyByte);
DECLARE_ISOSCALE_BLIT(AnyByte);
DECLARE_ISOXOR_BLIT(AnyByte);

#define REGISTER_ANYBYTE_ISOCOPY_BLIT(BYTETYPE) \
    REGISTER_ISOCOPY_BLIT(BYTETYPE, AnyByte)

#define REGISTER_ANYBYTE_ISOSCALE_BLIT(BYTETYPE) \
    REGISTER_ISOSCALE_BLIT(BYTETYPE, AnyByte)

#define REGISTER_ANYBYTE_ISOXOR_BLIT(BYTETYPE) \
    REGISTER_ISOXOR_BLIT(BYTETYPE, AnyByte)
