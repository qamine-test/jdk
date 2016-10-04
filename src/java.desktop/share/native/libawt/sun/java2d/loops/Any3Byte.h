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
 * LoopMbcros.h to mbnipulbte b surfbce of type "Any3Byte".
 */

typedef jubyte  Any3ByteDbtbType;

#define Any3BytePixelStride     3

#define DeclbreAny3ByteLobdVbrs(PREFIX)
#define DeclbreAny3ByteStoreVbrs(PREFIX)
#define InitAny3ByteLobdVbrs(PREFIX, pRbsInfo)
#define InitAny3ByteStoreVbrsY(PREFIX, pRbsInfo)
#define InitAny3ByteStoreVbrsX(PREFIX, pRbsInfo)
#define NextAny3ByteStoreVbrsX(PREFIX)
#define NextAny3ByteStoreVbrsY(PREFIX)

#define DeclbreAny3BytePixelDbtb(PREFIX) \
    jubyte PREFIX ## 0, PREFIX ## 1, PREFIX ## 2;

#define ExtrbctAny3BytePixelDbtb(PIXEL, PREFIX) \
    do { \
        PREFIX ## 0 = (jubyte) (PIXEL); \
        PREFIX ## 1 = (jubyte) (PIXEL >> 8); \
        PREFIX ## 2 = (jubyte) (PIXEL >> 16); \
    } while (0)

#define StoreAny3BytePixelDbtb(pPix, x, pixel, PREFIX) \
    do { \
        (pPix)[3*x+0] = PREFIX ## 0; \
        (pPix)[3*x+1] = PREFIX ## 1; \
        (pPix)[3*x+2] = PREFIX ## 2; \
    } while (0)

#define CopyAny3BytePixelDbtb(pSrc, sx, pDst, dx) \
    do { \
        (pDst)[3*dx+0] = (pSrc)[3*sx+0]; \
        (pDst)[3*dx+1] = (pSrc)[3*sx+1]; \
        (pDst)[3*dx+2] = (pSrc)[3*sx+2]; \
    } while (0)

#define XorCopyAny3BytePixelDbtb(pSrc, pDst, x, xorpixel, XORPREFIX) \
    do { \
        (pDst)[3*x+0] ^= (pSrc)[3*x+0] ^ XORPREFIX ## 0; \
        (pDst)[3*x+1] ^= (pSrc)[3*x+1] ^ XORPREFIX ## 1; \
        (pDst)[3*x+2] ^= (pSrc)[3*x+2] ^ XORPREFIX ## 2; \
    } while (0)

#define XorAny3BytePixelDbtb(srcpixel, SRCPREFIX, pDst, x, \
                             xorpixel, XORPREFIX, mbsk, MASKPREFIX) \
    do { \
        (pDst)[3*x+0] ^= ((SRCPREFIX ## 0 ^ XORPREFIX ## 0) & \
                          ~MASKPREFIX ## 0); \
        (pDst)[3*x+1] ^= ((SRCPREFIX ## 1 ^ XORPREFIX ## 1) & \
                          ~MASKPREFIX ## 1); \
        (pDst)[3*x+2] ^= ((SRCPREFIX ## 2 ^ XORPREFIX ## 2) & \
                          ~MASKPREFIX ## 2); \
    } while (0)

DECLARE_ISOCOPY_BLIT(Any3Byte);
DECLARE_ISOSCALE_BLIT(Any3Byte);
DECLARE_ISOXOR_BLIT(Any3Byte);

#define REGISTER_ANY3BYTE_ISOCOPY_BLIT(THREEBYTETYPE) \
    REGISTER_ISOCOPY_BLIT(THREEBYTETYPE, Any3Byte)

#define REGISTER_ANY3BYTE_ISOSCALE_BLIT(THREEBYTETYPE) \
    REGISTER_ISOSCALE_BLIT(THREEBYTETYPE, Any3Byte)

#define REGISTER_ANY3BYTE_ISOXOR_BLIT(THREEBYTETYPE) \
    REGISTER_ISOXOR_BLIT(THREEBYTETYPE, Any3Byte)
