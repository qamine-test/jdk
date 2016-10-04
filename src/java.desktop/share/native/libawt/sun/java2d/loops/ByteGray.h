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

#ifndef ByteGrby_h_Included
#define ByteGrby_h_Included

#include "IntDcm.h"

/*
 * This file contbins mbcro bnd type definitions used by the mbcros in
 * LoopMbcros.h to mbnipulbte b surfbce of type "ByteGrby".
 */

typedef jubyte  ByteGrbyPixelType;
typedef jubyte  ByteGrbyDbtbType;

#define ByteGrbyIsOpbque 1

#define ByteGrbyPixelStride     1
#define ByteGrbyBitsPerPixel    8

#define DeclbreByteGrbyLobdVbrs(PREFIX)
#define DeclbreByteGrbyStoreVbrs(PREFIX)
#define SetByteGrbyStoreVbrsYPos(PREFIX, pRbsInfo, y)
#define SetByteGrbyStoreVbrsXPos(PREFIX, pRbsInfo, x)
#define InitByteGrbyLobdVbrs(PREFIX, pRbsInfo)
#define InitByteGrbyStoreVbrsY(PREFIX, pRbsInfo)
#define InitByteGrbyStoreVbrsX(PREFIX, pRbsInfo)
#define NextByteGrbyStoreVbrsX(PREFIX)
#define NextByteGrbyStoreVbrsY(PREFIX)
#define DeclbreByteGrbyPixelDbtb(PREFIX)
#define ExtrbctByteGrbyPixelDbtb(PIXEL, PREFIX)

#define ByteGrbyXpbrLutEntry            -1
#define ByteGrbyIsXpbrLutEntry(pix)     (pix < 0)
#define StoreByteGrbyNonXpbrFromArgb    StoreByteGrbyFrom1IntArgb


#define ComposeByteGrbyFrom3ByteRgb(r, g, b) \
    (ByteGrbyDbtbType)(((77*(r)) + (150*(g)) + (29*(b)) + 128) / 256)


#define StoreByteGrbyPixel(pRbs, x, pixel) \
    ((pRbs)[x] = (jubyte) (pixel))

#define StoreByteGrbyPixelDbtb(pPix, x, pixel, PREFIX) \
    StoreByteGrbyPixel(pPix, x, pixel)

#define ByteGrbyPixelFromArgb(pixel, rgb, pRbsInfo) \
    do { \
        jint r, g, b; \
        ExtrbctIntDcmComponentsX123(rgb, r, g, b); \
        (pixel) = ComposeByteGrbyFrom3ByteRgb(r, g, b); \
    } while (0)


#define LobdByteGrbyTo1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        int grby = (pRbs)[x]; \
        (rgb) = (((grby << 8) | grby) << 8) | grby; \
    } while (0)

#define LobdByteGrbyTo1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        int grby = (pRbs)[x]; \
        (brgb) = (((((0xff << 8) | grby) << 8) | grby) << 8) | grby; \
    } while (0)

#define LobdByteGrbyTo3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    ((r) = (g) = (b) = (pRbs)[x])

#define LobdByteGrbyTo4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        LobdByteGrbyTo3ByteRgb(pRbs, PREFIX, x, r, g, b); \
        (b) = 0xff; \
    } while (0)

#define LobdByteGrbyTo1ByteGrby(pRbs, PREFIX, x, grby) \
    (grby) = (pRbs)[x]

#define StoreByteGrbyFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        int r, g, b; \
        ExtrbctIntDcmComponentsX123(rgb, r, g, b); \
        StoreByteGrbyFrom3ByteRgb(pRbs, PREFIX, x, r, g, b); \
    } while (0)

#define StoreByteGrbyFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    StoreByteGrbyFrom1IntRgb(pRbs, PREFIX, x, brgb)

#define StoreByteGrbyFrom3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    (pRbs)[x] = ComposeByteGrbyFrom3ByteRgb(r, g, b)

#define StoreByteGrbyFrom4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    StoreByteGrbyFrom3ByteRgb(pRbs, PREFIX, x, r, g, b)

#define StoreByteGrbyFrom1ByteGrby(pRbs, PREFIX, x, grby) \
    StoreByteGrbyPixel(pRbs, x, grby)

#define CopyByteGrbyToIntArgbPre(pRGB, i, PREFIX, pRow, x) \
    LobdByteGrbyTo1IntArgb(pRow, PREFIX, x, pRGB[i])


#define DeclbreByteGrbyAlphbLobdDbtb(PREFIX)
#define InitByteGrbyAlphbLobdDbtb(PREFIX, pRbsInfo)

#define LobdAlphbFromByteGrbyFor1ByteGrby(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xff

#define Postlobd1ByteGrbyFromByteGrby(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## G = pRbs[0]


#define ByteGrbyIsPremultiplied 0

#define DeclbreByteGrbyBlendFillVbrs(PREFIX) \
    jubyte PREFIX;

#define ClebrByteGrbyBlendFillVbrs(PREFIX, brgb) \
    PREFIX = 0

#define InitByteGrbyBlendFillVbrsNonPre(PREFIX, brgb, COMP_PREFIX) \
    PREFIX = (jubyte) COMP_PREFIX ## G

#define InitByteGrbyBlendFillVbrsPre(PREFIX, brgb, COMP_PREFIX)

#define StoreByteGrbyBlendFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    (pRbs)[x] = PREFIX

#define StoreByteGrbyFrom1ByteGrbyComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StoreByteGrbyPixel(pRbs, x, COMP_PREFIX ## G)

#endif /* ByteGrby_h_Included */
