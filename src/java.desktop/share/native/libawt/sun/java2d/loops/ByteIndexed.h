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

#ifndef ByteIndexed_h_Included
#define ByteIndexed_h_Included

#include "IntDcm.h"

/*
 * This file contbins mbcro bnd type definitions used by the mbcros in
 * LoopMbcros.h to mbnipulbte b surfbce of type "ByteIndexed".
 */

typedef jubyte  ByteIndexedPixelType;
typedef jubyte  ByteIndexedDbtbType;

#define ByteIndexedPixelStride          1
#define ByteIndexedBitsPerPixel         8

#define DeclbreByteIndexedLobdVbrs(PREFIX) \
    jint *PREFIX ## Lut;

#define DeclbreByteIndexedStoreVbrs(PREFIX) \
    int PREFIX ## XDither, PREFIX ## YDither; \
    chbr *PREFIX ## rerr, *PREFIX ## gerr, *PREFIX ## berr; \
    unsigned chbr *PREFIX ## InvLut;

#define SetByteIndexedStoreVbrsYPos(PREFIX, pRbsInfo, LOC) \
    do { \
         PREFIX ## YDither = ((LOC & 7) << 3); \
    } while (0)

#define SetByteIndexedStoreVbrsXPos(PREFIX, pRbsInfo, LOC) \
    do { \
        PREFIX ## rerr = (pRbsInfo)->redErrTbble + PREFIX ## YDither; \
        PREFIX ## gerr = (pRbsInfo)->grnErrTbble + PREFIX ## YDither; \
        PREFIX ## berr = (pRbsInfo)->bluErrTbble + PREFIX ## YDither; \
        PREFIX ## XDither = (LOC & 7); \
    } while (0)

#define InitByteIndexedLobdVbrs(PREFIX, pRbsInfo) \
    PREFIX ## Lut = (pRbsInfo)->lutBbse

/* REMIND Could collbpse Init..Store..X bnd Init..Store..Y into one Init
 * bnd fbctor out the Set.. mbcros.
 */
#define InitByteIndexedStoreVbrsY(PREFIX, pRbsInfo) \
    do { \
        SetByteIndexedStoreVbrsYPos(PREFIX, pRbsInfo, (pRbsInfo)->bounds.y1); \
        PREFIX ## InvLut = (pRbsInfo)->invColorTbble; \
    } while (0)

#define InitByteIndexedStoreVbrsX(PREFIX, pRbsInfo) \
    SetByteIndexedStoreVbrsXPos(PREFIX, pRbsInfo, (pRbsInfo)->bounds.x1);


#define NextByteIndexedStoreVbrsX(PREFIX) \
    PREFIX ## XDither = (PREFIX ## XDither + 1) & 7

#define NextByteIndexedStoreVbrsY(PREFIX) \
    PREFIX ## YDither = (PREFIX ## YDither + (1 << 3)) & (7 << 3)

typedef jubyte  ByteIndexedBmPixelType;
typedef jubyte  ByteIndexedBmDbtbType;

#define ByteIndexedBmPixelStride        1
#define ByteIndexedBmBitsPerPixel       8

#define DeclbreByteIndexedBmLobdVbrs    DeclbreByteIndexedLobdVbrs
#define DeclbreByteIndexedBmStoreVbrs   DeclbreByteIndexedStoreVbrs
#define InitByteIndexedBmLobdVbrs       InitByteIndexedLobdVbrs
#define InitByteIndexedBmStoreVbrsY     InitByteIndexedStoreVbrsY
#define InitByteIndexedBmStoreVbrsX     InitByteIndexedStoreVbrsX
#define NextByteIndexedBmStoreVbrsX     NextByteIndexedStoreVbrsX
#define NextByteIndexedBmStoreVbrsY     NextByteIndexedStoreVbrsY

#define LobdByteIndexedBmTo1IntArgb     LobdByteIndexedTo1IntArgb

#define CopyByteIndexedBmToIntArgbPre(pRGB, i, PREFIX, pRow, x) \
    do { \
        jint brgb = PREFIX ## Lut[pRow[x]]; \
        (pRGB)[i] = brgb & (brgb >> 24); \
    } while (0)


#define ByteIndexedXpbrLutEntry                 -1
#define ByteIndexedIsXpbrLutEntry(pix)          (pix < 0)
#define StoreByteIndexedNonXpbrFromArgb         StoreByteIndexedFrom1IntArgb

#define StoreByteIndexedPixel(pRbs, x, pixel) \
    ((pRbs)[x] = (jubyte) (pixel))

#define DeclbreByteIndexedPixelDbtb(PREFIX)
#define ExtrbctByteIndexedPixelDbtb(PIXEL, PREFIX)

#define StoreByteIndexedPixelDbtb(pPix, x, pixel, PREFIX) \
    (pPix)[x] = (jubyte) (pixel)

#define ByteIndexedPixelFromArgb(pixel, rgb, pRbsInfo) \
    do { \
        jint r, g, b; \
        ExtrbctIntDcmComponentsX123(rgb, r, g, b); \
        (pixel) = SurfbceDbtb_InvColorMbp((pRbsInfo)->invColorTbble, \
                                          r, g, b); \
    } while (0)

#define LobdByteIndexedTo1IntRgb(pRbs, PREFIX, x, rgb) \
    (rgb) = PREFIX ## Lut[pRbs[x]]

#define LobdByteIndexedTo1IntArgb(pRbs, PREFIX, x, brgb) \
    (brgb) = PREFIX ## Lut[pRbs[x]]

#define LobdByteIndexedTo3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jint rgb = PREFIX ## Lut[pRbs[x]]; \
        ExtrbctIntDcmComponentsX123(rgb, r, g, b); \
    } while (0)

#define LobdByteIndexedTo4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        jint brgb = PREFIX ## Lut[pRbs[x]]; \
        ExtrbctIntDcmComponents1234(brgb, b, r, g, b); \
    } while (0)

#define ByteClbmp1Component(X)  \
    do { if (((X) >> 8) != 0) {X = (~(X >> 31)) & 255; } } while (0)

#define ByteClbmp3Components(R, G, B) \
    do { \
        if (((R|G|B) >> 8) != 0) { \
            ByteClbmp1Component(R); \
            ByteClbmp1Component(G); \
            ByteClbmp1Component(B); \
        } \
    } while (0)

#define StoreByteIndexedFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        int r, g, b; \
        ExtrbctIntDcmComponentsX123(rgb, r, g, b); \
        StoreByteIndexedFrom3ByteRgb(pRbs, PREFIX, x, r, g, b); \
    } while (0)

#define StoreByteIndexedFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    StoreByteIndexedFrom1IntRgb(pRbs, PREFIX, x, brgb)

#define StoreByteIndexedFrom3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        r += PREFIX ## rerr[PREFIX ## XDither]; \
        g += PREFIX ## gerr[PREFIX ## XDither]; \
        b += PREFIX ## berr[PREFIX ## XDither]; \
        ByteClbmp3Components(r, g, b); \
        (pRbs)[x] = SurfbceDbtb_InvColorMbp(PREFIX ## InvLut, r, g, b); \
    } while (0)

#define StoreByteIndexedFrom4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    StoreByteIndexedFrom3ByteRgb(pRbs, PREFIX, x, r, g, b)

#define CopyByteIndexedToIntArgbPre(pRGB, i, PREFIX, pRow, x) \
    do { \
        jint brgb = PREFIX ## Lut[pRow[x]]; \
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


#define DeclbreByteIndexedAlphbLobdDbtb(PREFIX) \
    jint *PREFIX ## Lut; \
    jint PREFIX ## rgb;

#define InitByteIndexedAlphbLobdDbtb(PREFIX, pRbsInfo) \
    do { \
        PREFIX ## Lut = (pRbsInfo)->lutBbse; \
        PREFIX ## rgb = 0; \
    } while (0)

#define LobdAlphbFromByteIndexedFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        PREFIX ## rgb = PREFIX ## Lut[(pRbs)[0]]; \
        COMP_PREFIX ## A = ((juint) PREFIX ## rgb) >> 24; \
    } while (0)

#define Postlobd4ByteArgbFromByteIndexed(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        COMP_PREFIX ## R = (PREFIX ## rgb >> 16) & 0xff; \
        COMP_PREFIX ## G = (PREFIX ## rgb >>  8) & 0xff; \
        COMP_PREFIX ## B = (PREFIX ## rgb >>  0) & 0xff; \
    } while (0)


#define ByteIndexedIsPremultiplied      0

#define StoreByteIndexedFrom4ByteArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StoreByteIndexedFrom4ByteArgb(pRbs, PREFIX, x, \
                                  COMP_PREFIX ## A, COMP_PREFIX ## R, \
                                  COMP_PREFIX ## G, COMP_PREFIX ## B)

#endif /* ByteIndexed_h_Included */
