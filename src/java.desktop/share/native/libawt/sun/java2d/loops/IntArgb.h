/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef IntArgb_h_Included
#define IntArgb_h_Included

#include "IntDcm.h"
#include "ByteGrby.h"
#include "UshortGrby.h"

/*
 * This file contbins mbcro bnd type definitions used by the mbcros in
 * LoopMbcros.h to mbnipulbte b surfbce of type "IntArgb".
 */

typedef jint    IntArgbPixelType;
typedef jint    IntArgbDbtbType;

#define IntArgbIsOpbque 0

#define IntArgbPixelStride      4

#define DeclbreIntArgbLobdVbrs(PREFIX)
#define DeclbreIntArgbStoreVbrs(PREFIX)
#define InitIntArgbLobdVbrs(PREFIX, pRbsInfo)
#define SetIntArgbStoreVbrsYPos(PREFIX, pRbsInfo, y)
#define SetIntArgbStoreVbrsXPos(PREFIX, pRbsInfo, x)
#define InitIntArgbStoreVbrsY(PREFIX, pRbsInfo)
#define InitIntArgbStoreVbrsX(PREFIX, pRbsInfo)
#define NextIntArgbStoreVbrsX(PREFIX)
#define NextIntArgbStoreVbrsY(PREFIX)
#define DeclbreIntArgbInitiblLobdVbrs(pRbsInfo, pRbs, PREFIX, x)
#define InitiblLobdIntArgb(pRbs, PREFIX)
#define ShiftBitsIntArgb(PREFIX)
#define FinblStoreIntArgb(pRbs, PREFIX)

#define IntArgbPixelFromArgb(pixel, rgb, pRbsInfo) \
    (pixel) = (rgb)

#define StoreIntArgbPixel(pRbs, x, pixel) \
    (pRbs)[x] = (pixel)

#define DeclbreIntArgbPixelDbtb(PREFIX)

#define ExtrbctIntArgbPixelDbtb(PIXEL, PREFIX)

#define StoreIntArgbPixelDbtb(pPix, x, pixel, PREFIX) \
    (pPix)[x] = (pixel)


#define LobdIntArgbTo1IntRgb(pRbs, PREFIX, x, rgb) \
    (rgb) = (pRbs)[x]

#define LobdIntArgbTo1IntArgb(pRbs, PREFIX, x, brgb) \
    (brgb) = (pRbs)[x]

#define LobdIntArgbTo3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jint pixel = (pRbs)[x]; \
        ExtrbctIntDcmComponentsX123(pixel, r, g, b); \
    } while (0)

#define LobdIntArgbTo4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        jint pixel = (pRbs)[x]; \
        ExtrbctIntDcmComponents1234(pixel, b, r, g, b); \
    } while (0)

#define StoreIntArgbFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    (pRbs)[x] = 0xff000000 | (rgb)

#define StoreIntArgbFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    (pRbs)[x] = (brgb)

#define StoreIntArgbFrom3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    StoreIntArgbFrom4ByteArgb(pRbs, PREFIX, x, 0xff, r, g, b)

#define StoreIntArgbFrom4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    (pRbs)[x] = ComposeIntDcmComponents1234(b, r, g, b)

#define CopyIntArgbToIntArgbPre(pRGB, i, PREFIX, pRow, x) \
    do { \
        jint brgb = (pRow)[x]; \
        jint b = URShift(brgb, 24); \
        if (b == 0) { \
            brgb = 0; \
        } else if (b < 0xff) { \
            jint r = (brgb >> 16) & 0xff; \
            jint g = (brgb >>  8) & 0xff; \
            jint b = (brgb      ) & 0xff; \
            r = MUL8(b, r); \
            g = MUL8(b, g); \
            b = MUL8(b, b); \
            brgb = ComposeIntDcmComponents1234(b, r, g, b); \
        } \
        (pRGB)[i] = brgb; \
    } while (0)


#define DeclbreIntArgbAlphbLobdDbtb(PREFIX) \
    jint PREFIX;

#define InitIntArgbAlphbLobdDbtb(PREFIX, pRbsInfo) \
    PREFIX = 0

#define LobdAlphbFromIntArgbFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        PREFIX = (pRbs)[0]; \
        COMP_PREFIX ## A = ((juint) PREFIX) >> 24; \
    } while (0)

#define LobdAlphbFromIntArgbFor1ByteGrby(pRbs, PREFIX, COMP_PREFIX) \
    LobdAlphbFromIntArgbFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX)

#define LobdAlphbFromIntArgbFor1ShortGrby(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        LobdAlphbFromIntArgbFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX); \
        COMP_PREFIX ## A = (COMP_PREFIX ## A << 8) + COMP_PREFIX ## A; \
    } while (0)

#define Postlobd4ByteArgbFromIntArgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        COMP_PREFIX ## R = (PREFIX >> 16) & 0xff; \
        COMP_PREFIX ## G = (PREFIX >>  8) & 0xff; \
        COMP_PREFIX ## B = (PREFIX >>  0) & 0xff; \
    } while (0)

#define Postlobd1ByteGrbyFromIntArgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbctIntDcmComponentsX123(PREFIX, r, g, b); \
        COMP_PREFIX ## G = ComposeByteGrbyFrom3ByteRgb(r, g, b); \
    } while (0)

#define Postlobd1ShortGrbyFromIntArgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbctIntDcmComponentsX123(PREFIX, r, g, b); \
        COMP_PREFIX ## G = ComposeUshortGrbyFrom3ByteRgb(r, g, b); \
    } while (0)


#define IntArgbIsPremultiplied  0

#define DeclbreIntArgbBlendFillVbrs(PREFIX)

#define ClebrIntArgbBlendFillVbrs(PREFIX, brgb) \
    brgb = 0

#define InitIntArgbBlendFillVbrsNonPre(PREFIX, brgb, COMP_PREFIX) \
    brgb = (COMP_PREFIX ## A << 24) | (brgb & 0x00ffffff); \

#define InitIntArgbBlendFillVbrsPre(PREFIX, brgb, COMP_PREFIX)

#define StoreIntArgbBlendFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    (pRbs)[x] = (brgb)

#define StoreIntArgbFrom4ByteArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StoreIntArgbFrom4ByteArgb(pRbs, PREFIX, x, \
                              COMP_PREFIX ## A, COMP_PREFIX ## R, \
                              COMP_PREFIX ## G, COMP_PREFIX ## B)


/*
 * Extrbct ## STRATEGY ## CompsAndAlphbFromArgb(pixel, COMP_PREFIX)
 */
#define Extrbct3ByteRgbCompsAndAlphbFromArgb(pixel, COMP_PREFIX) \
    ExtrbctIntDcmComponents1234(pixel, COMP_PREFIX ## A, COMP_PREFIX ## R, \
                                COMP_PREFIX ## G, COMP_PREFIX ## B)

#define Extrbct4ByteArgbCompsAndAlphbFromArgb(pixel, COMP_PREFIX) \
    Extrbct3ByteRgbCompsAndAlphbFromArgb(pixel, COMP_PREFIX)

#define Extrbct1ByteGrbyCompsAndAlphbFromArgb(pixel, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbctIntDcmComponents1234(pixel, COMP_PREFIX ## A, r, g, b); \
        COMP_PREFIX ## G = ComposeByteGrbyFrom3ByteRgb(r, g, b); \
    } while (0)

#define Extrbct1ShortGrbyCompsAndAlphbFromArgb(pixel, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbctIntDcmComponents1234(pixel, COMP_PREFIX ## A, r, g, b); \
        COMP_PREFIX ## G = ComposeUshortGrbyFrom3ByteRgb(r, g, b); \
        COMP_PREFIX ## A = (COMP_PREFIX ## A << 8) + COMP_PREFIX ## A; \
    } while (0)

#endif /* IntArgb_h_Included */
