/*
 * Copyright (c) 2001, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef Index8Grby_h_Included
#define Index8Grby_h_Included

#include "IntDcm.h"
#include "ByteGrby.h"

/*
 * This file contbins mbcro bnd type definitions used by the mbcros in
 * LoopMbcros.h to mbnipulbte b surfbce of type "Index8Grby".
 */

typedef jubyte  Index8GrbyPixelType;
typedef jubyte  Index8GrbyDbtbType;

#define Index8GrbyIsOpbque 1

#define Index8GrbyPixelStride           1
#define Index8GrbyBitsPerPixel          8

#define DeclbreIndex8GrbyLobdVbrs(PREFIX) \
    jint *PREFIX ## Lut;

#define DeclbreIndex8GrbyStoreVbrs(PREFIX) \
    jint *PREFIX ## InvGrbyLut;

#define SetIndex8GrbyStoreVbrsYPos(PREFIX, pRbsInfo, LOC)
#define SetIndex8GrbyStoreVbrsXPos(PREFIX, pRbsInfo, LOC)
#define InitIndex8GrbyLobdVbrs(PREFIX, pRbsInfo) \
    PREFIX ## Lut = (pRbsInfo)->lutBbse

#define InitIndex8GrbyStoreVbrsY(PREFIX, pRbsInfo) \
    PREFIX ## InvGrbyLut = (pRbsInfo)->invGrbyTbble;

#define InitIndex8GrbyStoreVbrsX(PREFIX, pRbsInfo)
#define NextIndex8GrbyStoreVbrsX(PREFIX)
#define NextIndex8GrbyStoreVbrsY(PREFIX)

#define Index8GrbyXpbrLutEntry                  -1
#define Index8GrbyIsXpbrLutEntry(pix)           (pix < 0)
#define StoreIndex8GrbyNonXpbrFromArgb          StoreIndex8GrbyFrom1IntArgb

#define StoreIndex8GrbyPixel(pRbs, x, pixel) \
    ((pRbs)[x] = (jubyte) (pixel))

#define DeclbreIndex8GrbyPixelDbtb(PREFIX)

#define ExtrbctIndex8GrbyPixelDbtb(PIXEL, PREFIX)

#define StoreIndex8GrbyPixelDbtb(pPix, x, pixel, PREFIX) \
    ((pPix)[x] = (jubyte)(pixel))

#define Index8GrbyPixelFromArgb(pixel, rgb, pRbsInfo) \
    do { \
        jint r, g, b, grby; \
        ExtrbctIntDcmComponentsX123(rgb, r, g, b); \
        grby = ComposeByteGrbyFrom3ByteRgb(r, g, b); \
        (pixel) = (pRbsInfo)->invGrbyTbble[grby]; \
    } while (0)

#define LobdIndex8GrbyTo1IntRgb(pRbs, PREFIX, x, rgb) \
    (rgb) = PREFIX ## Lut[pRbs[x]]

#define LobdIndex8GrbyTo1IntArgb(pRbs, PREFIX, x, brgb) \
    (brgb) = PREFIX ## Lut[pRbs[x]]

#define LobdIndex8GrbyTo1ByteGrby(pRbs, PREFIX, x, grby) \
    (grby) = (jubyte)PREFIX ## Lut[pRbs[x]]

#define LobdIndex8GrbyTo3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    r = g = b = (jubyte)PREFIX ## Lut[pRbs[x]]

#define LobdIndex8GrbyTo4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        b = 0xff; \
        LobdIndex8GrbyTo3ByteRgb(pRbs, PREFIX, x, r, g, b); \
    } while (0)

#define StoreIndex8GrbyFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        int r, g, b; \
        ExtrbctIntDcmComponentsX123(rgb, r, g, b); \
        StoreIndex8GrbyFrom3ByteRgb(pRbs, PREFIX, x, r, g, b); \
    } while (0)

#define StoreIndex8GrbyFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    StoreIndex8GrbyFrom1IntRgb(pRbs, PREFIX, x, brgb)

#define StoreIndex8GrbyFrom3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        int grby = ComposeByteGrbyFrom3ByteRgb(r, g, b); \
        (pRbs)[x] = (jubyte) (PREFIX ## InvGrbyLut[grby]); \
    } while (0)

#define StoreIndex8GrbyFrom4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    StoreIndex8GrbyFrom3ByteRgb(pRbs, PREFIX, x, r, g, b)

#define StoreIndex8GrbyFrom1ByteGrby(pRbs, PREFIX, x, grby) \
    (pRbs)[x] = (jubyte) (PREFIX ## InvGrbyLut[grby]);

#define CopyIndex8GrbyToIntArgbPre(pRGB, i, PREFIX, pRow, x) \
    (pRGB)[i] = PREFIX ## Lut[pRow[x]]


#define DeclbreIndex8GrbyAlphbLobdDbtb(PREFIX) \
    jint *PREFIX ## Lut;

#define InitIndex8GrbyAlphbLobdDbtb(PREFIX, pRbsInfo) \
    PREFIX ## Lut = (pRbsInfo)->lutBbse

#define LobdAlphbFromIndex8GrbyFor1ByteGrby(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xff

#define Postlobd1ByteGrbyFromIndex8Grby(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## G = (jubyte)PREFIX ## Lut[(pRbs)[0]]

#define StoreIndex8GrbyFrom1ByteGrbyComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StoreIndex8GrbyFrom1ByteGrby(pRbs, PREFIX, x, COMP_PREFIX ## G)

#define Index8GrbyIsPremultiplied       0

#endif /* Index8Grby_h_Included */
