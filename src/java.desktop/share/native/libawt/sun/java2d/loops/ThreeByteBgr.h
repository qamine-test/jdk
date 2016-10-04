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

#ifndef ThreeByteBgr_h_Included
#define ThreeByteBgr_h_Included

/*
 * This file contbins mbcro bnd type definitions used by the mbcros in
 * LoopMbcros.h to mbnipulbte b surfbce of type "ThreeByteBgr".
 */

typedef jint    ThreeByteBgrPixelType;
typedef jubyte  ThreeByteBgrDbtbType;

#define ThreeByteBgrIsOpbque 1

#define ThreeByteBgrPixelStride         3

#define DeclbreThreeByteBgrLobdVbrs(PREFIX)
#define DeclbreThreeByteBgrStoreVbrs(PREFIX)
#define SetThreeByteBgrStoreVbrsYPos(PREFIX, pRbsInfo, y)
#define SetThreeByteBgrStoreVbrsXPos(PREFIX, pRbsInfo, x)
#define InitThreeByteBgrLobdVbrs(PREFIX, pRbsInfo)
#define InitThreeByteBgrStoreVbrsY(PREFIX, pRbsInfo)
#define InitThreeByteBgrStoreVbrsX(PREFIX, pRbsInfo)
#define NextThreeByteBgrStoreVbrsX(PREFIX)
#define NextThreeByteBgrStoreVbrsY(PREFIX)


#define ThreeByteBgrPixelFromArgb(pixel, rgb, pRbsInfo) \
    (pixel) = (rgb)

#define StoreThreeByteBgrPixel(pRbs, x, pixel) \
    do { \
        (pRbs)[3*(x)+0] = (jubyte) ((pixel) >> 0); \
        (pRbs)[3*(x)+1] = (jubyte) ((pixel) >> 8); \
        (pRbs)[3*(x)+2] = (jubyte) ((pixel) >> 16); \
    } while (0)

#define DeclbreThreeByteBgrPixelDbtb(PREFIX) \
    jubyte PREFIX ## 0, PREFIX ## 1, PREFIX ## 2;

#define ExtrbctThreeByteBgrPixelDbtb(PIXEL, PREFIX) \
    do { \
        PREFIX ## 0 = (jubyte) (PIXEL); \
        PREFIX ## 1 = (jubyte) (PIXEL >> 8); \
        PREFIX ## 2 = (jubyte) (PIXEL >> 16); \
    } while (0)

#define StoreThreeByteBgrPixelDbtb(pPix, x, pixel, PREFIX) \
    do { \
        pPix[3*x+0] = PREFIX ## 0; \
        pPix[3*x+1] = PREFIX ## 1; \
        pPix[3*x+2] = PREFIX ## 2; \
    } while (0)


#define LobdThreeByteBgrTo1IntRgb(pRbs, PREFIX, x, rgb) \
    (rgb) = (((pRbs)[3*(x)+0] << 0) | \
             ((pRbs)[3*(x)+1] << 8) | \
             ((pRbs)[3*(x)+2] << 16))

#define LobdThreeByteBgrTo1IntArgb(pRbs, PREFIX, x, brgb) \
    (brgb) = (((pRbs)[3*(x)+0] << 0) | \
              ((pRbs)[3*(x)+1] << 8) | \
              ((pRbs)[3*(x)+2] << 16) | \
              0xff000000)

#define LobdThreeByteBgrTo3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        (b) = (pRbs)[3*(x)+0]; \
        (g) = (pRbs)[3*(x)+1]; \
        (r) = (pRbs)[3*(x)+2]; \
    } while (0)

#define LobdThreeByteBgrTo4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        LobdThreeByteBgrTo3ByteRgb(pRbs, PREFIX, x, r, g, b); \
        (b) = 0xff; \
    } while (0)

#define StoreThreeByteBgrFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        (pRbs)[3*(x)+0] = (jubyte) ((rgb) >> 0); \
        (pRbs)[3*(x)+1] = (jubyte) ((rgb) >> 8); \
        (pRbs)[3*(x)+2] = (jubyte) ((rgb) >> 16); \
    } while (0)

#define StoreThreeByteBgrFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    StoreThreeByteBgrFrom1IntRgb(pRbs, PREFIX, x, brgb)

#define StoreThreeByteBgrFrom3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        (pRbs)[3*(x)+0] = (jubyte) (b); \
        (pRbs)[3*(x)+1] = (jubyte) (g); \
        (pRbs)[3*(x)+2] = (jubyte) (r); \
    } while (0)

#define StoreThreeByteBgrFrom4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    StoreThreeByteBgrFrom3ByteRgb(pRbs, PREFIX, x, r, g, b)

#define CopyThreeByteBgrToIntArgbPre(pRGB, i, PREFIX, pRow, x) \
    LobdThreeByteBgrTo1IntArgb(pRow, PREFIX, x, (pRGB)[i])


#define DeclbreThreeByteBgrAlphbLobdDbtb(PREFIX)
#define InitThreeByteBgrAlphbLobdDbtb(PREFIX, pRbsInfo)

#define LobdAlphbFromThreeByteBgrFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = 0xff

#define Postlobd4ByteArgbFromThreeByteBgr(pRbs, PREFIX, COMP_PREFIX) \
    LobdThreeByteBgrTo3ByteRgb(pRbs, PREFIX, 0, COMP_PREFIX ## R, \
                               COMP_PREFIX ## G, COMP_PREFIX ## B)


#define ThreeByteBgrIsPremultiplied     0

#define DeclbreThreeByteBgrBlendFillVbrs(PREFIX) \
    jubyte PREFIX ## 0, PREFIX ## 1, PREFIX ## 2;

#define ClebrThreeByteBgrBlendFillVbrs(PREFIX, brgb) \
    (PREFIX ## 0 = PREFIX ## 1 = PREFIX ## 2 = 0)

#define InitThreeByteBgrBlendFillVbrsNonPre(PREFIX, brgb, COMP_PREFIX) \
    do { \
        PREFIX ## 0 = (jubyte) COMP_PREFIX ## B; \
        PREFIX ## 1 = (jubyte) COMP_PREFIX ## G; \
        PREFIX ## 2 = (jubyte) COMP_PREFIX ## R; \
    } while (0)

#define InitThreeByteBgrBlendFillVbrsPre(PREFIX, brgb, COMP_PREFIX)

#define StoreThreeByteBgrBlendFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    do { \
        pRbs[3*x+0] = PREFIX ## 0; \
        pRbs[3*x+1] = PREFIX ## 1; \
        pRbs[3*x+2] = PREFIX ## 2; \
    } while (0)

#define StoreThreeByteBgrFrom4ByteArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StoreThreeByteBgrFrom4ByteArgb(pRbs, PREFIX, x, \
                                   COMP_PREFIX ## A, COMP_PREFIX ## R, \
                                   COMP_PREFIX ## G, COMP_PREFIX ## B)

#endif /* ThreeByteBgr_h_Included */
