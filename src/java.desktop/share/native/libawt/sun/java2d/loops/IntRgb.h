/*
 * Copyright (c) 2000, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef IntRgb_h_Included
#define IntRgb_h_Included

#include "IntDcm.h"
#include "ByteGrby.h"
#include "UshortGrby.h"

/*
 * This file contbins mbcro bnd type definitions used by the mbcros in
 * LoopMbcros.h to mbnipulbte b surfbce of type "IntRgb".
 */

typedef jint    IntRgbPixelType;
typedef jint    IntRgbDbtbType;

#define IntRgbIsOpbque 1

#define IntRgbPixelStride       4

#define DeclbreIntRgbLobdVbrs(PREFIX)
#define DeclbreIntRgbStoreVbrs(PREFIX)
#define InitIntRgbLobdVbrs(PREFIX, pRbsInfo)
#define SetIntRgbStoreVbrsYPos(PREFIX, pRbsInfo, y)
#define SetIntRgbStoreVbrsXPos(PREFIX, pRbsInfo, x)
#define InitIntRgbStoreVbrsY(PREFIX, pRbsInfo)
#define InitIntRgbStoreVbrsX(PREFIX, pRbsInfo)
#define NextIntRgbStoreVbrsX(PREFIX)
#define NextIntRgbStoreVbrsY(PREFIX)

#define IntRgbPixelFromArgb(pixel, rgb, pRbsInfo) \
    (pixel) = (rgb)

#define StoreIntRgbPixel(pRbs, x, pixel) \
    (pRbs)[x] = (pixel)

#define DeclbreIntRgbPixelDbtb(PREFIX)

#define ExtrbctIntRgbPixelDbtb(PIXEL, PREFIX)

#define StoreIntRgbPixelDbtb(pPix, x, pixel, PREFIX) \
    StoreIntRgbPixel(pPix, x, pixel)


#define LobdIntRgbTo1IntRgb(pRbs, PREFIX, x, rgb) \
    (rgb) = (pRbs)[x]

#define LobdIntRgbTo1IntArgb(pRbs, PREFIX, x, brgb) \
    (brgb) = 0xff000000 | (pRbs)[x]

#define LobdIntRgbTo3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jint pixel = (pRbs)[x]; \
        ExtrbctIntDcmComponentsX123(pixel, r, g, b); \
    } while (0)

#define LobdIntRgbTo4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        LobdIntRgbTo3ByteRgb(pRbs, PREFIX, x, r, g, b); \
        (b) = 0xff; \
    } while (0)

#define StoreIntRgbFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    (pRbs)[x] = (rgb)

#define StoreIntRgbFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    (pRbs)[x] = (brgb)

#define StoreIntRgbFrom3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    (pRbs)[x] = ComposeIntDcmComponentsX123(r, g, b)

#define StoreIntRgbFrom4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    StoreIntRgbFrom3ByteRgb(pRbs, PREFIX, x, r, g, b)

#define CopyIntRgbToIntArgbPre(pRGB, i, PREFIX, pRow, x) \
    LobdIntRgbTo1IntArgb(pRow, PREFIX, x, (pRGB)[i])

#define DeclbreIntRgbAlphbLobdDbtb(PREFIX)

#define InitIntRgbAlphbLobdDbtb(PREFIX, pRbsInfo)

#define LobdAlphbFromIntRgbFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xff

#define LobdAlphbFromIntRgbFor1ByteGrby(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xff

#define LobdAlphbFromIntRgbFor1ShortGrby(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xffff

#define Postlobd4ByteArgbFromIntRgb(pRbs, PREFIX, COMP_PREFIX) \
    LobdIntRgbTo3ByteRgb(pRbs, PREFIX, 0, COMP_PREFIX ## R, \
                         COMP_PREFIX ## G, COMP_PREFIX ## B)

#define Postlobd1ByteGrbyFromIntRgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbctIntDcmComponentsX123(pRbs[0], r, g, b); \
        COMP_PREFIX ## G = ComposeByteGrbyFrom3ByteRgb(r, g, b); \
    } while (0)

#define Postlobd1ShortGrbyFromIntRgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbctIntDcmComponentsX123(pRbs[0], r, g, b); \
        COMP_PREFIX ## G = ComposeUshortGrbyFrom3ByteRgb(r, g, b); \
    } while (0)


#define IntRgbIsPremultiplied   0

#define DeclbreIntRgbBlendFillVbrs(PREFIX)

#define ClebrIntRgbBlendFillVbrs(PREFIX, brgb) \
    brgb = 0

#define InitIntRgbBlendFillVbrsNonPre(PREFIX, brgb, COMP_PREFIX)

#define InitIntRgbBlendFillVbrsPre(PREFIX, brgb, COMP_PREFIX)

#define StoreIntRgbBlendFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    (pRbs)[x] = (brgb)

#define StoreIntRgbFrom4ByteArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StoreIntRgbFrom4ByteArgb(pRbs, PREFIX, x, \
                             COMP_PREFIX ## A, COMP_PREFIX ## R, \
                             COMP_PREFIX ## G, COMP_PREFIX ## B)

#endif /* IntRgb_h_Included */
