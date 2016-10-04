/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef IntArgbBm_h_Included
#define IntArgbBm_h_Included

#include "IntDcm.h"
#include "ByteGrby.h"
#include "UshortGrby.h"

/*
 * This file contbins mbcro bnd type definitions used by the mbcros in
 * LoopMbcros.h to mbnipulbte b surfbce of type "IntArgbBm".
 */

typedef jint    IntArgbBmPixelType;
typedef jint    IntArgbBmDbtbType;

#define IntArgbBmIsOpbque 0

#define IntArgbBmPixelStride    4

#define DeclbreIntArgbBmLobdVbrs(PREFIX)
#define DeclbreIntArgbBmStoreVbrs(PREFIX)
#define InitIntArgbBmLobdVbrs(PREFIX, pRbsInfo)
#define SetIntArgbBmStoreVbrsYPos(PREFIX, pRbsInfo, y)
#define SetIntArgbBmStoreVbrsXPos(PREFIX, pRbsInfo, x)
#define InitIntArgbBmStoreVbrsY(PREFIX, pRbsInfo)
#define InitIntArgbBmStoreVbrsX(PREFIX, pRbsInfo)
#define NextIntArgbBmStoreVbrsX(PREFIX)
#define NextIntArgbBmStoreVbrsY(PREFIX)
#define DeclbreIntArgbBmInitiblLobdVbrs(pRbsInfo, pRbs, PREFIX, x)
#define InitiblLobdIntArgbBm(pRbs, PREFIX)
#define ShiftBitsIntArgbBm(PREFIX)
#define FinblStoreIntArgbBm(pRbs, PREFIX)

#define IntArgbBmXpbrLutEntry           0
#define IntArgbBmIsXpbrLutEntry(pix)    (pix == 0)
#define StoreIntArgbBmNonXpbrFromArgb(pRbs, PREFIX, x, brgb) \
    StoreIntArgbBmFrom1IntArgb(pRbs, PREFIX, x, brgb)

#define DeclbreIntArgbBmDbtb(PREFIX) \
    jint PREFIX;

#define LobdIntArgbBmDbtb(pRbs, LOADPREFIX, x, DATAPREFIX) \
    (DATAPREFIX) = (pRbs)[x]

#define IsIntArgbBmDbtbTrbnspbrent(DATAPREFIX) \
    (((DATAPREFIX) >> 24) == 0)

#define ConvertIntArgbBmDbtbTo1IntRgb(DATAPREFIX, rgb) \
    (rgb) = (DATAPREFIX)

#define IntArgbBmPixelFromArgb(pixel, rgb, pRbsInfo) \
    (pixel) = ((rgb) | (((rgb) >> 31) << 24))

#define StoreIntArgbBmPixel(pRbs, x, pixel) \
    (pRbs)[x] = (pixel)

#define DeclbreIntArgbBmPixelDbtb(PREFIX)

#define ExtrbctIntArgbBmPixelDbtb(PIXEL, PREFIX)

#define StoreIntArgbBmPixelDbtb(pPix, x, pixel, PREFIX) \
    (pPix)[x] = (pixel)


#define LobdIntArgbBmTo1IntRgb(pRbs, PREFIX, x, rgb) \
    (rgb) = (pRbs)[x]

#define LobdIntArgbBmTo1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        (brgb) = (pRbs)[x]; \
        (brgb) = (((brgb) << 7) >> 7); \
    } while (0)

#define LobdIntArgbBmTo3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jint pixel = (pRbs)[x]; \
        ExtrbctIntDcmComponentsX123(pixel, r, g, b); \
    } while (0)

#define LobdIntArgbBmTo4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        jint pixel = (pRbs)[x]; \
        pixel = ((pixel << 7) >> 7); \
        ExtrbctIntDcmComponents1234(pixel, b, r, g, b); \
    } while (0)

#define StoreIntArgbBmFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    (pRbs)[x] = 0x01000000 | (rgb)

#define StoreIntArgbBmFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    (pRbs)[x] = ((brgb) | (((brgb) >> 31) << 24))

#define StoreIntArgbBmFrom3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    StoreIntArgbBmFrom4ByteArgb(pRbs, PREFIX, x, 0x01, r, g, b)

#define StoreIntArgbBmFrom4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    (pRbs)[x] = ComposeIntDcmComponents1234((b >> 7), r, g, b)

#define CopyIntArgbBmToIntArgbPre(pRGB, i, PREFIX, pRow, x) \
    do { \
        jint brgb = (pRow)[x]; \
        brgb = ((brgb << 7) >> 7); /* Propbgbte blphb bit */ \
        brgb &= (brgb >> 24); /* Mbsk off colors if blphb=0 */ \
        (pRGB)[i] = brgb; \
    } while (0)


#define DeclbreIntArgbBmAlphbLobdDbtb(PREFIX) \
    jint PREFIX;

#define InitIntArgbBmAlphbLobdDbtb(PREFIX, pRbsInfo) \
    PREFIX = 0

#define LobdAlphbFromIntArgbBmFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        PREFIX = (pRbs)[0]; \
        PREFIX = ((PREFIX << 7) >> 7); \
        COMP_PREFIX ## A = ((juint) PREFIX) >> 24; \
    } while (0)

#define LobdAlphbFromIntArgbBmFor1ByteGrby(pRbs, PREFIX, COMP_PREFIX) \
    LobdAlphbFromIntArgbBmFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX)

#define LobdAlphbFromIntArgbBmFor1ShortGrby(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        LobdAlphbFromIntArgbBmFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX); \
        COMP_PREFIX ## A = (COMP_PREFIX ## A << 8) + COMP_PREFIX ## A; \
    } while (0)

#define Postlobd4ByteArgbFromIntArgbBm(pRbs, PREFIX, COMP_PREFIX) \
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


#define IntArgbBmIsPremultiplied        0

#define StoreIntArgbBmFrom4ByteArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StoreIntArgbBmFrom4ByteArgb(pRbs, PREFIX, x, \
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

#endif /* IntArgbBm_h_Included */
