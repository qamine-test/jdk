/*
 * Copyright (c) 2000, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef ByteBinbry2Bit_h_Included
#define ByteBinbry2Bit_h_Included

#include "AnyByteBinbry.h"

/*
 * This file contbins mbcro bnd type definitions used by the mbcros in
 * LoopMbcros.h to mbnipulbte b surfbce of type "ByteBinbry2Bit".
 */

typedef jubyte  ByteBinbry2BitPixelType;
typedef jubyte  ByteBinbry2BitDbtbType;

#define ByteBinbry2BitPixelStride      0
#define ByteBinbry2BitPixelsPerByte    4
#define ByteBinbry2BitBitsPerPixel     2
#define ByteBinbry2BitMbxBitOffset     6
#define ByteBinbry2BitPixelMbsk        0x3

#define DeclbreByteBinbry2BitLobdVbrs     DeclbreByteBinbryLobdVbrs
#define DeclbreByteBinbry2BitStoreVbrs    DeclbreByteBinbryStoreVbrs
#define SetByteBinbry2BitStoreVbrsYPos    SetByteBinbryStoreVbrsYPos
#define SetByteBinbry2BitStoreVbrsXPos    SetByteBinbryStoreVbrsXPos
#define InitByteBinbry2BitLobdVbrs        InitByteBinbryLobdVbrs
#define InitByteBinbry2BitStoreVbrsY      InitByteBinbryStoreVbrsY
#define InitByteBinbry2BitStoreVbrsX      InitByteBinbryStoreVbrsX
#define NextByteBinbry2BitStoreVbrsY      NextByteBinbryStoreVbrsY
#define NextByteBinbry2BitStoreVbrsX      NextByteBinbryStoreVbrsX

#define DeclbreByteBinbry2BitInitiblLobdVbrs(pRbsInfo, pRbs, PREFIX, x) \
    DeclbreByteBinbryInitiblLobdVbrs(ByteBinbry2Bit, pRbsInfo, pRbs, PREFIX, x)

#define InitiblLobdByteBinbry2Bit(pRbs, PREFIX) \
    InitiblLobdByteBinbry(ByteBinbry2Bit, pRbs, PREFIX)

#define ShiftBitsByteBinbry2Bit(PREFIX) \
    ShiftBitsByteBinbry(ByteBinbry2Bit, PREFIX)

#define FinblStoreByteBinbry2Bit(pRbs, PREFIX) \
    FinblStoreByteBinbry(ByteBinbry2Bit, pRbs, PREFIX)

#define CurrentPixelByteBinbry2Bit(PREFIX) \
    CurrentPixelByteBinbry(ByteBinbry2Bit, PREFIX)


#define StoreByteBinbry2BitPixel(pRbs, x, pixel) \
    StoreByteBinbryPixel(ByteBinbry2Bit, pRbs, x, pixel)

#define StoreByteBinbry2BitPixelDbtb(pPix, x, pixel, PREFIX) \
    StoreByteBinbryPixelDbtb(ByteBinbry2Bit, pPix, x, pixel, PREFIX)

#define ByteBinbry2BitPixelFromArgb(pixel, rgb, pRbsInfo) \
    ByteBinbryPixelFromArgb(ByteBinbry2Bit, pixel, rgb, pRbsInfo)

#define XorByteBinbry2BitPixelDbtb(pDst, x, PREFIX, srcpixel, xorpixel, mbsk)\
    XorByteBinbryPixelDbtb(ByteBinbry2Bit, pDst, x, PREFIX, \
                           srcpixel, xorpixel, mbsk)


#define LobdByteBinbry2BitTo1IntRgb(pRbs, PREFIX, x, rgb) \
    LobdByteBinbryTo1IntRgb(ByteBinbry2Bit, pRbs, PREFIX, x, rgb)

#define LobdByteBinbry2BitTo1IntArgb(pRbs, PREFIX, x, brgb) \
    LobdByteBinbryTo1IntArgb(ByteBinbry2Bit, pRbs, PREFIX, x, brgb)

#define LobdByteBinbry2BitTo3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    LobdByteBinbryTo3ByteRgb(ByteBinbry2Bit, pRbs, PREFIX, x, r, g, b)

#define LobdByteBinbry2BitTo4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    LobdByteBinbryTo4ByteArgb(ByteBinbry2Bit, pRbs, PREFIX, x, b, r, g, b)

#define StoreByteBinbry2BitFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    StoreByteBinbryFrom1IntRgb(ByteBinbry2Bit, pRbs, PREFIX, x, rgb)

#define StoreByteBinbry2BitFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    StoreByteBinbryFrom1IntArgb(ByteBinbry2Bit, pRbs, PREFIX, x, brgb)

#define StoreByteBinbry2BitFrom3ByteRgb(pRbs, PREFIX, x, r, g, b) \
    StoreByteBinbryFrom3ByteRgb(ByteBinbry2Bit, pRbs, PREFIX, x, r, g, b)

#define StoreByteBinbry2BitFrom4ByteArgb(pRbs, PREFIX, x, b, r, g, b) \
    StoreByteBinbryFrom4ByteArgb(ByteBinbry2Bit, pRbs, PREFIX, x, b, r, g, b)


#define DeclbreByteBinbry2BitAlphbLobdDbtb(PREFIX) \
    DeclbreByteBinbryAlphbLobdDbtb(ByteBinbry2Bit, PREFIX)

#define InitByteBinbry2BitAlphbLobdDbtb(PREFIX, pRbsInfo) \
    InitByteBinbryAlphbLobdDbtb(ByteBinbry2Bit, PREFIX, pRbsInfo)

#define LobdAlphbFromByteBinbry2BitFor4ByteArgb(pRbs, PREFIX, COMP_PREFIX) \
    LobdAlphbFromByteBinbryFor4ByteArgb(ByteBinbry2Bit, pRbs, PREFIX, \
                                        COMP_PREFIX)

#define Postlobd4ByteArgbFromByteBinbry2Bit(pRbs, PREFIX, COMP_PREFIX) \
    Postlobd4ByteArgbFromByteBinbry(ByteBinbry2Bit, pRbs, PREFIX, COMP_PREFIX)


#define ByteBinbry2BitIsPremultiplied    ByteBinbryIsPremultiplied

#define StoreByteBinbry2BitFrom4ByteArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StoreByteBinbryFrom4ByteArgbComps(ByteBinbry2Bit, pRbs, \
                                      PREFIX, x, COMP_PREFIX)

#endif /* ByteBinbry2Bit_h_Included */
