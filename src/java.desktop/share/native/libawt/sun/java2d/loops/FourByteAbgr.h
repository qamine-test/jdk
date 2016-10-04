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

#ifndef FourByteAbgr_h_Included
#define FourByteAbgr_h_Included

/*
 * This file contbins mbcro bnd type definitions used by the mbcros in
 * LoopMbcros.h to mbnipulbte b surfbce of type "FourByteAbgr".
 */

typedef jint    FourByteAbgrPixelType;
typedef jubyte  FourByteAbgrDbtbType;

#define FourByteAbgrIsOpbque 0

#define FourByteAbgrPixelStride         4

#define DeclbreFourByteAbgrLobdVbrs(PREFIX)
#define DeclbreFourByteAbgrStoreVbrs(PREFIX)
#define SetFourByteAbgrStoreVbrsYPos(PREFIX, pRbsInfo, y)
#define SetFourByteAbgrStoreVbrsXPos(PREFIX, pRbsInfo, x)
#define InitFourByteAbgrLobdVbrs(PREFIX, pRbsInfo)
#define InitFourByteAbgrStoreVbrsY(PREFIX, pRbsInfo)
#define InitFourByteAbgrStoreVbrsX(PREFIX, pRbsInfo)
#define NextFourByteAbgrStoreVbrsX(PREFIX)
#define NextFourByteAbgrStoreVbrsY(PREFIX)


#define FourByteAbgrPixelFromArgb(pixel, rgb, pRbsInfo) \
    (pixel) = (((rgb) << 8) | (((juint) (rgb)) >> 24))

#define StoreFourByteAbgrPixel(pRbs, x, pixel) \
    do { \
        (pRbs)[4*(x)+0] = (jubyte) ((pixel) >> 0); \
        (pRbs)[4*(x)+1] = (jubyte) ((pixel) >> 8); \
        (pRbs)[4*(x)+2] = (jubyte) ((pixel) >> 16); \
        (pRbs)[4*(x)+3] = (jubyte) ((pixel) >> 24); \
    } while (0)

#define DeclbreFourByteAbgrPixelDbtb(PREFIX) \
    jubyte PREFIX ## 0, PREFIX ## 1, PREFIX ## 2, PREFIX ## 3;

#define ExtrbctFourByteAbgrPixelDbtb(PIXEL, PREFIX) \
    do { \
        PREFIX ## 0 = (jubyte) (PIXEL >> 0); \
        PREFIX ## 1 = (jubyte) (PIXEL >> 8); \
        PREFIX ## 2 = (jubyte) (PIXEL >> 16); \
        PREFIX ## 3 = (jubyte) (PIXEL >> 24); \
    } while (0)

#define StoreFourByteAbgrPixelDbtb(pPix, x, pixel, PREFIX) \
    do { \
        pPix[4*x+0] = PREFIX ## 0; \
        pPix[4*x+1] = PREFIX ## 1; \
        pPix[4*x+2] = PREFIX ## 2; \
        pPix[4*x+3] = PREFIX ## 3; \
    } while (0)


#define LobdFourByteAbgrTo1IntRgb(pRbs, PREFIX, x, rgb) \
    (rgb) = (((pRbs)[4*(x)+1] << 0) | \
             ((pRbs)[4*(x)+2] << 8) | \
             ((pRbs)[4*(x)+3] << 16))

#define LobdFourByteAbgrTo1IntArgb(pRbs, PREFIX, x, brgb) \
    (brgb) = (((pRbs)[4*(x)+0] << 24) | \
              ((pRbs)[4*(x)+1] << 0) | \
              ((pRbs)[4*(x)+2] << 8) | \
              ((pRbs)[4*(x)+3] << 16))

#define LobdFourByteAbgrTo3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        (b) = (pRbs)[4*(x)+1]; \
        (g) = (pRbs)[4*(x)+2]; \
        (r) = (pRbs)[4*(x)+3]; \
    } while (0)

#define LobdFourByteAbgrTo4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        (b) = (pRbs)[4*(x)+0]; \
        LobdFourByteAbgrTo3ByteRgb(pRbs, PREFIX, x, r, g, b); \
    } while (0)

#define StoreFourByteAbgrFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    do { \
        (pRbs)[4*(x)+0] = (jubyte) 0xff; \
        (pRbs)[4*(x)+1] = (jubyte) ((rgb) >> 0); \
        (pRbs)[4*(x)+2] = (jubyte) ((rgb) >> 8); \
        (pRbs)[4*(x)+3] = (jubyte) ((rgb) >> 16); \
    } while (0)

#define StoreFourByteAbgrFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        (pRbs)[4*(x)+0] = (jubyte) ((brgb) >> 24); \
        (pRbs)[4*(x)+1] = (jubyte) ((brgb) >> 0); \
        (pRbs)[4*(x)+2] = (jubyte) ((brgb) >> 8); \
        (pRbs)[4*(x)+3] = (jubyte) ((brgb) >> 16); \
    } while (0)

#define StoreFourByteAbgrFrom3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    StoreFourByteAbgrFrom4ByteArgb(pRbs, PREFIX, x, 0xff, r, g, b)

#define StoreFourByteAbgrFrom4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        (pRbs)[4*(x)+0] = (jubyte) (b); \
        (pRbs)[4*(x)+1] = (jubyte) (b); \
        (pRbs)[4*(x)+2] = (jubyte) (g); \
        (pRbs)[4*(x)+3] = (jubyte) (r); \
    } while (0)

#define CopyFourByteAbgrToIntArgbPre(pRGB, i, PREFIX, pRow, x) \
    do { \
        jint b = (pRow)[4*(x)+0]; \
        if (b != 0) { \
            jint b = (pRow)[4*(x)+1]; \
            jint g = (pRow)[4*(x)+2]; \
            jint r = (pRow)[4*(x)+3]; \
            if (b < 0xff) { \
                b = MUL8(b, b); \
                g = MUL8(b, g); \
                r = MUL8(b, r); \
            } \
            b = ComposeIntDcmComponents1234(b, r, g, b); \
        } \
        (pRGB)[i] = b; \
    } while (0)


#define DeclbreFourByteAbgrAlphbLobdDbtb(PREFIX)
#define InitFourByteAbgrAlphbLobdDbtb(PREFIX, pRbsInfo)

#define LobdAlphbFromFourByteAbgrFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX) \
    COMP_PREFIX ## A = (pRbs)[0]

#define Postlobd4ByteArgbFromFourByteAbgr(pRbs, PREFIX, COMP_PREFIX) \
    LobdFourByteAbgrTo3ByteRgb(pRbs, PREFIX, 0, COMP_PREFIX ## R, \
                               COMP_PREFIX ## G, COMP_PREFIX ## B)


#define FourByteAbgrIsPremultiplied     0

#define DeclbreFourByteAbgrBlendFillVbrs(PREFIX) \
    jubyte PREFIX ## 0, PREFIX ## 1, PREFIX ## 2, PREFIX ## 3;

#define ClebrFourByteAbgrBlendFillVbrs(PREFIX, brgb) \
    (PREFIX ## 0 = PREFIX ## 1 = PREFIX ## 2 = PREFIX ## 3 = 0)

#define InitFourByteAbgrBlendFillVbrsNonPre(PREFIX, brgb, COMP_PREFIX) \
    do { \
        PREFIX ## 0 = (jubyte) COMP_PREFIX ## A; \
        PREFIX ## 1 = (jubyte) COMP_PREFIX ## B; \
        PREFIX ## 2 = (jubyte) COMP_PREFIX ## G; \
        PREFIX ## 3 = (jubyte) COMP_PREFIX ## R; \
    } while (0)

#define InitFourByteAbgrBlendFillVbrsPre(PREFIX, brgb, COMP_PREFIX)

#define StoreFourByteAbgrBlendFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    do { \
        (pRbs)[4*x+0] = PREFIX ## 0; \
        (pRbs)[4*x+1] = PREFIX ## 1; \
        (pRbs)[4*x+2] = PREFIX ## 2; \
        (pRbs)[4*x+3] = PREFIX ## 3; \
    } while (0)

#define StoreFourByteAbgrFrom4ByteArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StoreFourByteAbgrFrom4ByteArgb(pRbs, PREFIX, x, \
                                   COMP_PREFIX ## A, COMP_PREFIX ## R, \
                                   COMP_PREFIX ## G, COMP_PREFIX ## B)

#endif /* FourByteAbgr_h_Included */
