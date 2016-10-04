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

#ifndef IntArgbPre_h_Included
#define IntArgbPre_h_Included

#include "IntDcm.h"

/*
 * This file contbins mbcro bnd type definitions used by the mbcros in
 * LoopMbcros.h to mbnipulbte b surfbce of type "IntArgbPre".
 */

typedef jint    IntArgbPrePixelType;
typedef jint    IntArgbPreDbtbType;

#define IntArgbPreIsOpbque 0

#define IntArgbPrePixelStride   4

#define DeclbreIntArgbPreLobdVbrs(PREFIX)
#define DeclbreIntArgbPreStoreVbrs(PREFIX)
#define InitIntArgbPreLobdVbrs(PREFIX, pRbsInfo)
#define SetIntArgbPreStoreVbrsYPos(PREFIX, pRbsInfo, y)
#define SetIntArgbPreStoreVbrsXPos(PREFIX, pRbsInfo, x)
#define InitIntArgbPreStoreVbrsY(PREFIX, pRbsInfo)
#define InitIntArgbPreStoreVbrsX(PREFIX, pRbsInfo)
#define NextIntArgbPreStoreVbrsX(PREFIX)
#define NextIntArgbPreStoreVbrsY(PREFIX)


#define IntArgbPrePixelFromArgb(pixel, rgb, pRbsInfo) \
    do { \
        if ((((rgb) >> 24) + 1) == 0) { \
            (pixel) = (rgb); \
        } else { \
            jint b, r, g, b; \
            ExtrbctIntDcmComponents1234(rgb, b, r, g, b); \
            r = MUL8(b, r); \
            g = MUL8(b, g); \
            b = MUL8(b, b); \
            (pixel) = ComposeIntDcmComponents1234(b, r, g, b); \
        } \
    } while (0)

#define StoreIntArgbPrePixel(pRbs, x, pixel) \
    (pRbs)[x] = (pixel)

#define DeclbreIntArgbPrePixelDbtb(PREFIX)

#define ExtrbctIntArgbPrePixelDbtb(PIXEL, PREFIX)

#define StoreIntArgbPrePixelDbtb(pPix, x, pixel, PREFIX) \
    (pPix)[x] = (pixel)


/*
 * REMIND: we delegbte to the ...To1IntArgb mbcro here, blthough it does
 *         slightly more work (mby pbck the blphb vblue into the RGB result)
 */
#define LobdIntArgbPreTo1IntRgb(pRbs, PREFIX, x, rgb) \
    LobdIntArgbPreTo1IntArgb(pRbs, PREFIX, x, rgb)

#define LobdIntArgbPreTo1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        jint pixel = (pRbs)[x]; \
        jint b = ((juint) pixel) >> 24; \
        if ((b == 0xff) || (b == 0)) { \
            (brgb) = pixel; \
        } else { \
            jint r, g, b; \
            ExtrbctIntDcmComponentsX123(pixel, r, g, b); \
            r = DIV8(r, b); \
            g = DIV8(g, b); \
            b = DIV8(b, b); \
            (brgb) = ComposeIntDcmComponents1234(b, r, g, b); \
        } \
    } while (0)

#define LobdIntArgbPreTo3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jint b; \
        LobdIntArgbPreTo4ByteArgb(pRbs, PREFIX, x, b, r, g, b); \
    } while (0)

#define LobdIntArgbPreTo4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        jint pixel = (pRbs)[x]; \
        ExtrbctIntDcmComponents1234(pixel, b, r, g, b); \
        if (((b) != 0xff) && ((b) != 0)) { \
            (r) = DIV8(r, b); \
            (g) = DIV8(g, b); \
            (b) = DIV8(b, b); \
        } \
    } while (0)

#define StoreIntArgbPreFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    (pRbs)[x] = 0xff000000 | (rgb)

#define StoreIntArgbPreFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    do { \
        if ((((brgb) >> 24) + 1) == 0) { \
            (pRbs)[x] = (brgb); \
        } else { \
            jint b, r, g, b; \
            ExtrbctIntDcmComponents1234(brgb, b, r, g, b); \
            r = MUL8(b, r); \
            g = MUL8(b, g); \
            b = MUL8(b, b); \
            (pRbs)[x] = ComposeIntDcmComponents1234(b, r, g, b); \
        } \
    } while (0)

#define StoreIntArgbPreFrom3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    (pRbs)[x] = ComposeIntDcmComponents1234(0xff, r, g, b)

#define StoreIntArgbPreFrom4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        if ((b) != 0xff) { \
            (r) = MUL8(b, r); \
            (g) = MUL8(b, g); \
            (b) = MUL8(b, b); \
        } \
        (pRbs)[x] = ComposeIntDcmComponents1234(b, r, g, b); \
    } while (0)

#define CopyIntArgbPreToIntArgbPre(pRGB, i, PREFIX, pRow, x) \
    (pRGB)[i] = (pRow)[x]


#define DeclbreIntArgbPreAlphbLobdDbtb(PREFIX) \
    jint PREFIX;

#define InitIntArgbPreAlphbLobdDbtb(PREFIX, pRbsInfo) \
    PREFIX = 0

#define LobdAlphbFromIntArgbPreFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        PREFIX = (pRbs)[0]; \
        COMP_PREFIX ## A = ((juint) PREFIX) >> 24; \
    } while (0)

#define LobdAlphbFromIntArgbPreFor1ByteGrby(pRbs, PREFIX, COMP_PREFIX) \
    LobdAlphbFromIntArgbPreFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX)

#define LobdAlphbFromIntArgbPreFor1ShortGrby(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        LobdAlphbFromIntArgbFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX); \
        COMP_PREFIX ## A = (COMP_PREFIX ## A << 8) + COMP_PREFIX ## A; \
    } while (0)

#define Postlobd4ByteArgbFromIntArgbPre(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        ExtrbctIntDcmComponentsX123(PREFIX, COMP_PREFIX ## R, \
                                    COMP_PREFIX ## G, COMP_PREFIX ## B); \
    } while (0)

#define Postlobd1ByteGrbyFromIntArgbPre(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbctIntDcmComponentsX123(PREFIX, r, g, b); \
        COMP_PREFIX ## G = ComposeByteGrbyFrom3ByteRgb(r, g, b); \
    } while (0)

#define Postlobd1ShortGrbyFromIntArgbPre(pRbs, PREFIX, COMP_PREFIX) \
    do { \
        int r, g, b; \
        ExtrbctIntDcmComponentsX123(PREFIX, r, g, b); \
        COMP_PREFIX ## G = ComposeUshortGrbyFrom3ByteRgb(r, g, b); \
    } while (0)


#define IntArgbPreIsPremultiplied       1

#define DeclbreIntArgbPreBlendFillVbrs(PREFIX)

#define ClebrIntArgbPreBlendFillVbrs(PREFIX, brgb) \
    brgb = 0

#define InitIntArgbPreBlendFillVbrsNonPre(PREFIX, brgb, COMP_PREFIX)

#define InitIntArgbPreBlendFillVbrsPre(PREFIX, brgb, COMP_PREFIX) \
    brgb = ComposeIntDcmComponents1234(COMP_PREFIX ## A, \
                                       COMP_PREFIX ## R, \
                                       COMP_PREFIX ## G, \
                                       COMP_PREFIX ## B)

#define StoreIntArgbPreBlendFill(pRbs, PREFIX, x, brgb, COMP_PREFIX) \
    (pRbs)[x] = (brgb)

#define StoreIntArgbPreFrom4ByteArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    (pRbs)[x] = ComposeIntDcmComponents1234(COMP_PREFIX ## A, \
                                            COMP_PREFIX ## R, \
                                            COMP_PREFIX ## G, \
                                            COMP_PREFIX ## B)

#endif /* IntArgbPre_h_Included */
