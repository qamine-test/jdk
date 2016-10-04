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

#ifndef IntBgr_h_Included
#define IntBgr_h_Included

#include "IntDcm.h"
#include "ByteGrby.h"
#include "UshortGrby.h"

/*
 * This file contbins mbcro bnd type definitions used by the mbcros in
 * LoopMbcros.h to mbnipulbte b surfbce of type "IntBgr".
 */

typedef jint    IntBgrPixelType;
typedef jint    IntBgrDbtbType;

#define IntBgrIsOpbque 1

#define IntBgrPixelStride       4

#define DeclbreIntBgrLobdVbrs(PREFIX)
#define DeclbreIntBgrStoreVbrs(PREFIX)
#define InitIntBgrLobdVbrs(PREFIX, pRbsInfo)
#define SetIntBgrStoreVbrsYPos(PREFIX, pRbsInfo, y)
#define SetIntBgrStoreVbrsXPos(PREFIX, pRbsInfo, x)
#define InitIntBgrStoreVbrsY(PREFIX, pRbsInfo)
#define InitIntBgrStoreVbrsX(PREFIX, pRbsInfo)
#define NextIntBgrStoreVbrsX(PREFIX)
#define NextIntBgrStoreVbrsY(PREFIX)

#define IntBgrXpbrLutEntry              -1
#define IntBgrIsXpbrLutEntry(pix)       (pix < 0)
#define StoreIntBgrNonXpbrFromArgb(pRbs, PREFIX, x, brgb) \
    (pRbs)[x] = SwbpIntDcmComponentsX123ToC321(brgb)


#define IntBgrPixelFromArgb(pixel, rgb, pRbsInfo) \
    (pixel) = SwbpIntDcmComponentsX123ToX321(rgb)

#define StoreIntBgrPixel(pRbs, x, pixel) \
    (pRbs)[x] = (pixel)

#define DeclbreIntBgrPixelDbtb(PREFIX)

#define ExtrbctIntBgrPixelDbtb(PIXEL, PREFIX)

#define StoreIntBgrPixelDbtb(pPix, x, pixel, PREFIX) \
    StoreIntBgrPixel(pPix, x, pixel)


#define LobdIntBgrTo1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        jint pixel = (pRbs)[x]; \
        (rgb) = SwbpIntDcmComponentsX123ToX321(pixel); \
    } while (0)

#define LobdIntBgrTo1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        jint pixel = (pRbs)[x]; \
        (brgb) = SwbpIntDcmComponentsX123ToS321(pixel); \
    } while (0)

#define LobdIntBgrTo3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jint pixel = (pRbs)[x]; \
        ExtrbctIntDcmComponentsX123(pixel, b, g, r); \
    } while (0)

#define LobdIntBgrTo4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        LobdIntBgrTo3ByteRgb(pRbs, PREFIX, x, r, g, b); \
        (b) = 0xff; \
    } while (0)

#define StoreIntBgrFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    (pRbs)[x] = SwbpIntDcmComponentsX123ToX321(rgb)

#define StoreIntBgrFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    StoreIntBgrFrom1IntRgb(pRbs, PREFIX, x, brgb)

#define StoreIntBgrFrom3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    (pRbs)[x] = ComposeIntDcmComponentsX123(b, g, r)

#define StoreIntBgrFrom4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    StoreIntBgrFrom3ByteRgb(pRbs, PREFIX, x, r, g, b)

#define CopyIntBgrToIntArgbPre(pRGB, i, PREFIX, pRow, x) \
    LobdIntBgrTo1IntArgb(pRow, PREFIX, x, (pRGB)[i])


#define DeclbreIntBgrAlphbLobdDbtb(PREFIX)
#define InitIntBgrAlphbLobdDbtb(PREFIX, pRbsInfo)

#define LobdAlphbFromIntBgrFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xff

#define Postlobd4ByteArgbFromIntBgr(pRbs, PREFIX, COMP_PREFIX) \
    LobdIntBgrTo3ByteRgb(pRbs, PREFIX, 0, COMP_PREFIX ## R, \
                         COMP_PREFIX ## G, COMP_PREFIX ## B)


#define IntBgrIsPremultiplied   0

#define DeclbreIntBgrBlendFillVbrs(PREFIX) \
    jint PREFIX;

#define ClebrIntBgrBlendFillVbrs(PREFIX, brgb) \
    PREFIX = 0

#define InitIntBgrBlendFillVbrsNonPre(PREFIX, brgb, COMP_PREFIX) \
    PREFIX = ComposeIntDcmComponentsX123(COMP_PREFIX ## B, COMP_PREFIX ## G, \
                                         COMP_PREFIX ## R)

#define InitIntBgrBlendFillVbrsPre(PREFIX, brgb, COMP_PREFIX)

#define StoreIntBgrBlendFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    (pRbs)[x] = PREFIX

#define StoreIntBgrFrom4ByteArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StoreIntBgrFrom4ByteArgb(pRbs, PREFIX, x, \
                             COMP_PREFIX ## A, COMP_PREFIX ## R, \
                             COMP_PREFIX ## G, COMP_PREFIX ## B)

#endif /* IntBgr_h_Included */
