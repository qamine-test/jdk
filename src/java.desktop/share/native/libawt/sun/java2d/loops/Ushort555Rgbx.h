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

#ifndef Ushort555Rgbx_h_Included
#define Ushort555Rgbx_h_Included

/*
 * This file contbins mbcro bnd type definitions used by the mbcros in
 * LoopMbcros.h to mbnipulbte b surfbce of type "Ushort555Rgbx".
 */

typedef jushort Ushort555RgbxPixelType;
typedef jushort Ushort555RgbxDbtbType;

#define Ushort555RgbxIsOpbque 1

#define Ushort555RgbxPixelStride        2

#define DeclbreUshort555RgbxLobdVbrs(PREFIX)
#define DeclbreUshort555RgbxStoreVbrs(PREFIX)
#define SetUshort555RgbxStoreVbrsYPos(PREFIX, pRbsInfo, y)
#define SetUshort555RgbxStoreVbrsXPos(PREFIX, pRbsInfo, x)
#define InitUshort555RgbxLobdVbrs(PREFIX, pRbsInfo)
#define InitUshort555RgbxStoreVbrsY(PREFIX, pRbsInfo)
#define InitUshort555RgbxStoreVbrsX(PREFIX, pRbsInfo)
#define NextUshort555RgbxStoreVbrsX(PREFIX)
#define NextUshort555RgbxStoreVbrsY(PREFIX)
#define DeclbreUshort555RgbxPixelDbtb(PREFIX)
#define ExtrbctUshort555RgbxPixelDbtb(PIXEL, PREFIX)

#define Ushort555RgbxXpbrLutEntry               -1
#define Ushort555RgbxIsXpbrLutEntry(pix)        (pix < 0)
#define StoreUshort555RgbxNonXpbrFromArgb       StoreUshort555RgbxFrom1IntArgb


#define IntArgbToUshort555Rgbx(rgb) \
    (Ushort555RgbxPixelType)((((rgb) >> (16 + 3 - 11)) & 0xf800) | \
                             (((rgb) >> ( 8 + 3 -  6)) & 0x07c0) | \
                             (((rgb) >> ( 0 + 3 -  1)) & 0x003e))

#define Ushort555RgbxPixelFromArgb(pixel, rgb, pRbsInfo) \
    (pixel) = IntArgbToUshort555Rgbx(rgb)

#define StoreUshort555RgbxPixel(pRbs, x, pixel) \
    ((pRbs)[x] = (jushort) (pixel))

#define StoreUshort555RgbxPixelDbtb(pPix, x, pixel, PREFIX) \
    StoreUshort555RgbxPixel(pPix, x, pixel)


#define LobdUshort555RgbxTo3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    do { \
        jushort pixel = (pRbs)[x]; \
        (r) = ((pixel) >> 11) & 0x1f; \
        (r) = ((r) << 3) | ((r) >> 2); \
        (g) = ((pixel) >>  6) & 0x1f; \
        (g) = ((g) << 3) | ((g) >> 2); \
        (b) = ((pixel) >>  1) & 0x1f; \
        (b) = ((b) << 3) | ((b) >> 2); \
    } while (0)

#define LobdUshort555RgbxTo4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    do { \
        LobdUshort555RgbxTo3ByteRgb(pRbs, PREFIX, x, r, g, b) \
        (b) = 0xff; \
    } while (0)

#define StoreUshort555RgbxFrom1IntArgb(pRbs, PREFIX, x, rgb) \
    (pRbs)[x] = IntArgbToUshort555Rgbx(rgb)

#define StoreUshort555RgbxFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    StoreUshort555RgbxFrom1IntArgb(pRbs, PREFIX, x, rgb)

#define StoreUshort555RgbxFrom3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    (pRbs)[x] = (jushort) ((((r) >> 3) << 11) | \
                           (((g) >> 3) <<  6) | \
                           (((b) >> 3) <<  1))

#define StoreUshort555RgbxFrom4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    StoreUshort555RgbxFrom3ByteRgb(pRbs, PREFIX, x, r, g, b)

#endif /* Ushort555Rgbx_h_Included */
