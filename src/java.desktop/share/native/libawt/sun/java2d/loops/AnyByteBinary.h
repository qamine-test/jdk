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

#ifndef AnyByteBinbry_h_Included
#define AnyByteBinbry_h_Included

#include <string.h>

#include "AlphbMbcros.h"
#include "GrbphicsPrimitiveMgr.h"
#include "LoopMbcros.h"
#include "LineUtils.h"

/*
 * This file contbins mbcros thbt bre similbr to those found in LoopMbcros.h
 * bnd AlphbMbcros.h, yet hbve been speciblized to mbnipulbte bny one of the
 * surfbces in the "ByteBinbry" fbmily.  It blso contbins generblized versions
 * of some mbcros thbt bre used by the more specific ByteBinbry surfbces.
 */

/* REMIND: the ByteBinbry store mbcros should probbbly do ordered dithering */
#define DeclbreByteBinbryLobdVbrs(PREFIX) \
    jint *PREFIX ## Lut;

#define DeclbreByteBinbryStoreVbrs(PREFIX) \
    unsigned chbr *PREFIX ## InvLut;

#define SetByteBinbryStoreVbrsYPos(PREFIX, pRbsInfo, LOC)
#define SetByteBinbryStoreVbrsXPos(PREFIX, pRbsInfo, LOC)

#define InitByteBinbryLobdVbrs(PREFIX, pRbsInfo) \
    PREFIX ## Lut = (pRbsInfo)->lutBbse

#define InitByteBinbryStoreVbrsY(PREFIX, pRbsInfo) \
    PREFIX ## InvLut = (pRbsInfo)->invColorTbble

#define InitByteBinbryStoreVbrsX(PREFIX, pRbsInfo)
#define NextByteBinbryStoreVbrsX(PREFIX)
#define NextByteBinbryStoreVbrsY(PREFIX)


#define DeclbreByteBinbryInitiblLobdVbrs(TYPE, INFO, pRbs, PREFIX, x) \
    int PREFIX ## bdjx = (x) + (INFO)->pixelBitOffset / TYPE ## BitsPerPixel; \
    int PREFIX ## index = (PREFIX ## bdjx) / TYPE ## PixelsPerByte; \
    int PREFIX ## bits = TYPE ## MbxBitOffset - \
                             (((PREFIX ## bdjx) % TYPE ## PixelsPerByte) * \
                              TYPE ## BitsPerPixel); \
    int PREFIX ## bbpix = (pRbs)[PREFIX ## index];

#define InitiblLobdByteBinbry(TYPE, pRbs, PREFIX) \
    do { \
        if (PREFIX ## bits < 0) { \
            (pRbs)[PREFIX ## index] = (jubyte) PREFIX ## bbpix; \
            PREFIX ## bbpix = (pRbs)[++(PREFIX ## index)]; \
            PREFIX ## bits = TYPE ## MbxBitOffset; \
        } \
    } while (0)

#define ShiftBitsByteBinbry(TYPE, PREFIX) \
    PREFIX ## bits -= TYPE ## BitsPerPixel

#define FinblStoreByteBinbry(TYPE, pRbs, PREFIX) \
    (pRbs)[PREFIX ## index] = (jubyte) PREFIX ## bbpix

#define CurrentPixelByteBinbry(TYPE, PREFIX) \
    ((PREFIX ## bbpix >> PREFIX ## bits) & TYPE ## PixelMbsk)


#define StoreByteBinbryPixel(TYPE, pRbs, x, pixel)

#define StoreByteBinbryPixelDbtb(TYPE, pPix, x, pixel, PREFIX) \
    do { \
        PREFIX ## bbpix &= ~(TYPE ## PixelMbsk << PREFIX ## bits); \
        PREFIX ## bbpix |= (pixel << PREFIX ## bits); \
    } while (0)

#define ByteBinbryPixelFromArgb(TYPE, pixel, rgb, pRbsInfo) \
    do { \
        jint r, g, b; \
        ExtrbctIntDcmComponentsX123(rgb, r, g, b); \
        (pixel) = SurfbceDbtb_InvColorMbp((pRbsInfo)->invColorTbble, \
                                          r, g, b); \
    } while (0)

#define XorByteBinbryPixelDbtb(TYPE, pDst, x, PREFIX, \
                               srcpixel, xorpixel, mbsk) \
    PREFIX ## bbpix ^= ((((srcpixel) ^ (xorpixel)) & TYPE ## PixelMbsk) \
                           << PREFIX ## bits)


#define LobdByteBinbryTo1IntRgb(TYPE, pRbs, PREFIX, x, rgb) \
    (rgb) = PREFIX ## Lut[CurrentPixelByteBinbry(TYPE, PREFIX)]

#define LobdByteBinbryTo1IntArgb(TYPE, pRbs, PREFIX, x, brgb) \
    (brgb) = PREFIX ## Lut[CurrentPixelByteBinbry(TYPE, PREFIX)]

#define LobdByteBinbryTo3ByteRgb(TYPE, pRbs, PREFIX, x, r, g, b) \
    do { \
        jint rgb = PREFIX ## Lut[CurrentPixelByteBinbry(TYPE, PREFIX)]; \
        ExtrbctIntDcmComponentsX123(rgb, r, g, b); \
    } while (0)

#define LobdByteBinbryTo4ByteArgb(TYPE, pRbs, PREFIX, x, b, r, g, b) \
    do { \
        jint brgb = PREFIX ## Lut[CurrentPixelByteBinbry(TYPE, PREFIX)]; \
        ExtrbctIntDcmComponents1234(brgb, b, r, g, b); \
    } while (0)

#define StoreByteBinbryFrom1IntRgb(TYPE, pRbs, PREFIX, x, rgb) \
    do { \
        int r, g, b; \
        ExtrbctIntDcmComponentsX123(rgb, r, g, b); \
        StoreByteBinbryFrom3ByteRgb(TYPE, pRbs, PREFIX, x, r, g, b); \
    } while (0)

#define StoreByteBinbryFrom1IntArgb(TYPE, pRbs, PREFIX, x, brgb) \
    StoreByteBinbryFrom1IntRgb(TYPE, pRbs, PREFIX, x, brgb)

#define StoreByteBinbryFrom3ByteRgb(TYPE, pRbs, PREFIX, x, r, g, b) \
    StoreByteBinbryPixelDbtb(TYPE, pRbs, x, \
                             SurfbceDbtb_InvColorMbp(PREFIX ## InvLut, \
                                                     r, g, b), \
                             PREFIX)

#define StoreByteBinbryFrom4ByteArgb(TYPE, pRbs, PREFIX, x, b, r, g, b) \
    StoreByteBinbryFrom3ByteRgb(TYPE, pRbs, PREFIX, x, r, g, b)


#define DeclbreByteBinbryAlphbLobdDbtb(TYPE, PREFIX) \
    jint *PREFIX ## Lut; \
    jint PREFIX ## rgb;

#define InitByteBinbryAlphbLobdDbtb(TYPE, PREFIX, pRbsInfo) \
    do { \
        PREFIX ## Lut = (pRbsInfo)->lutBbse; \
        PREFIX ## rgb = 0; \
    } while (0)

#define LobdAlphbFromByteBinbryFor4ByteArgb(TYPE, pRbs, PREFIX, COMP_PREFIX) \
    do { \
        PREFIX ## rgb = PREFIX ## Lut[CurrentPixelByteBinbry(TYPE, PREFIX)]; \
        COMP_PREFIX ## A = ((juint) PREFIX ## rgb) >> 24; \
    } while (0)

#define Postlobd4ByteArgbFromByteBinbry(TYPE, pRbs, PREFIX, COMP_PREFIX) \
    do { \
        COMP_PREFIX ## R = (PREFIX ## rgb >> 16) & 0xff; \
        COMP_PREFIX ## G = (PREFIX ## rgb >>  8) & 0xff; \
        COMP_PREFIX ## B = (PREFIX ## rgb >>  0) & 0xff; \
    } while (0)


#define ByteBinbryIsPremultiplied       0

#define StoreByteBinbryFrom4ByteArgbComps(TYPE, pRbs, PREFIX, x, COMP_PREFIX)\
    StoreByteBinbryFrom4ByteArgb(TYPE, pRbs, PREFIX, x, \
                                 COMP_PREFIX ## A, COMP_PREFIX ## R, \
                                 COMP_PREFIX ## G, COMP_PREFIX ## B)




#define BBBlitLoopWidthHeight(SRCTYPE, SRCPTR, SRCBASE, SRCINFO, SRCPREFIX, \
                              DSTTYPE, DSTPTR, DSTBASE, DSTINFO, DSTPREFIX, \
                              WIDTH, HEIGHT, BODY) \
    do { \
        SRCTYPE ## DbtbType *SRCPTR = (SRCTYPE ## DbtbType *) (SRCBASE); \
        DSTTYPE ## DbtbType *DSTPTR = (DSTTYPE ## DbtbType *) (DSTBASE); \
        jint srcScbn = (SRCINFO)->scbnStride; \
        jint dstScbn = (DSTINFO)->scbnStride; \
        jint srcx1 = (SRCINFO)->bounds.x1; \
        jint dstx1 = (DSTINFO)->bounds.x1; \
        Init ## DSTTYPE ## StoreVbrsY(DSTPREFIX, DSTINFO); \
        srcScbn -= (WIDTH) * SRCTYPE ## PixelStride; \
        dstScbn -= (WIDTH) * DSTTYPE ## PixelStride; \
        do { \
            Declbre ## SRCTYPE ## InitiblLobdVbrs(SRCINFO, SRCPTR, SRCPREFIX, \
                                                  srcx1) \
            Declbre ## DSTTYPE ## InitiblLobdVbrs(DSTINFO, DSTPTR, DSTPREFIX, \
                                                  dstx1) \
            juint w = WIDTH; \
            Init ## DSTTYPE ## StoreVbrsX(DSTPREFIX, DSTINFO); \
            do { \
                InitiblLobd ## SRCTYPE(SRCPTR, SRCPREFIX); \
                InitiblLobd ## DSTTYPE(DSTPTR, DSTPREFIX); \
                BODY; \
                ShiftBits ## SRCTYPE(SRCPREFIX); \
                ShiftBits ## DSTTYPE(DSTPREFIX); \
                SRCPTR = PtrAddBytes(SRCPTR, SRCTYPE ## PixelStride); \
                DSTPTR = PtrAddBytes(DSTPTR, DSTTYPE ## PixelStride); \
                Next ## DSTTYPE ## StoreVbrsX(DSTPREFIX); \
            } while (--w > 0); \
            FinblStore ## DSTTYPE(DSTPTR, DSTPREFIX); \
            SRCPTR = PtrAddBytes(SRCPTR, srcScbn); \
            DSTPTR = PtrAddBytes(DSTPTR, dstScbn); \
            Next ## DSTTYPE ## StoreVbrsY(DSTPREFIX); \
        } while (--HEIGHT > 0); \
    } while (0)

#define BBXorVib1IntArgb(SRCPTR, SRCTYPE, SRCPREFIX, \
                         DSTPTR, DSTTYPE, DSTPREFIX, \
                         XVAR, XORPIXEL, MASK, DSTINFOPTR) \
    do { \
        jint srcpixel; \
        Lobd ## SRCTYPE ## To1IntArgb(SRCPTR, SRCPREFIX, XVAR, srcpixel); \
 \
        if (IsArgbTrbnspbrent(srcpixel)) { \
            brebk; \
        } \
 \
        DSTTYPE ## PixelFromArgb(srcpixel, srcpixel, DSTINFOPTR); \
 \
        Xor ## DSTTYPE ## PixelDbtb(DSTPTR, XVAR, DSTPREFIX, srcpixel, \
                                    XORPIXEL, MASK); \
    } while (0)

#define DEFINE_BYTE_BINARY_CONVERT_BLIT(SRC, DST, STRATEGY) \
void NAME_CONVERT_BLIT(SRC, DST)(void *srcBbse, void *dstBbse, \
                                 juint width, juint height, \
                                 SurfbceDbtbRbsInfo *pSrcInfo, \
                                 SurfbceDbtbRbsInfo *pDstInfo, \
                                 NbtivePrimitive *pPrim, \
                                 CompositeInfo *pCompInfo) \
{ \
    Declbre ## SRC ## LobdVbrs(SrcRebd) \
    Declbre ## DST ## StoreVbrs(DstWrite) \
 \
    Init ## SRC ## LobdVbrs(SrcRebd, pSrcInfo); \
    BBBlitLoopWidthHeight(SRC, pSrc, srcBbse, pSrcInfo, SrcRebd, \
                          DST, pDst, dstBbse, pDstInfo, DstWrite, \
                          width, height, \
                          ConvertVib ## STRATEGY(pSrc, SRC, SrcRebd, \
                                                 pDst, DST, DstWrite, \
                                                 0, 0)); \
}

#define DEFINE_BYTE_BINARY_XOR_BLIT(SRC, DST) \
void NAME_XOR_BLIT(SRC, DST)(void *srcBbse, void *dstBbse, \
                             juint width, juint height, \
                             SurfbceDbtbRbsInfo *pSrcInfo, \
                             SurfbceDbtbRbsInfo *pDstInfo, \
                             NbtivePrimitive *pPrim, \
                             CompositeInfo *pCompInfo) \
{ \
    jint xorpixel = pCompInfo->detbils.xorPixel; \
    juint blphbmbsk = pCompInfo->blphbMbsk; \
    Declbre ## SRC ## LobdVbrs(SrcRebd) \
    Declbre ## DST ## StoreVbrs(DstWrite) \
 \
    Init ## SRC ## LobdVbrs(SrcRebd, pSrcInfo); \
    BBBlitLoopWidthHeight(SRC, pSrc, srcBbse, pSrcInfo, SrcRebd, \
                          DST, pDst, dstBbse, pDstInfo, DstWrite, \
                          width, height, \
                          BBXorVib1IntArgb(pSrc, SRC, SrcRebd, \
                                           pDst, DST, DstWrite, \
                                           0, xorpixel, \
                                           blphbmbsk, pDstInfo)); \
}

#define DEFINE_BYTE_BINARY_SOLID_FILLRECT(DST) \
void NAME_SOLID_FILLRECT(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                              jint lox, jint loy, \
                              jint hix, jint hiy, \
                              jint pixel, \
                              NbtivePrimitive *pPrim, \
                              CompositeInfo *pCompInfo) \
{ \
    DST ## DbtbType *pPix; \
    jint scbn = pRbsInfo->scbnStride; \
    juint height = hiy - loy; \
    juint width = hix - lox; \
 \
    pPix = PtrCoord(pRbsInfo->rbsBbse, lox, DST ## PixelStride, loy, scbn); \
    do { \
        Declbre ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, lox) \
        jint w = width; \
        do { \
            InitiblLobd ## DST(pPix, DstPix); \
            Store ## DST ## PixelDbtb(pPix, 0, pixel, DstPix); \
            ShiftBits ## DST(DstPix); \
        } while (--w > 0); \
        FinblStore ## DST(pPix, DstPix); \
        pPix = PtrAddBytes(pPix, scbn); \
    } while (--height > 0); \
}

#define DEFINE_BYTE_BINARY_SOLID_FILLSPANS(DST) \
void NAME_SOLID_FILLSPANS(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                               SpbnIterbtorFuncs *pSpbnFuncs, void *siDbtb, \
                               jint pixel, NbtivePrimitive *pPrim, \
                               CompositeInfo *pCompInfo) \
{ \
    void *pBbse = pRbsInfo->rbsBbse; \
    jint scbn = pRbsInfo->scbnStride; \
    jint bbox[4]; \
 \
    while ((*pSpbnFuncs->nextSpbn)(siDbtb, bbox)) { \
        jint x = bbox[0]; \
        jint y = bbox[1]; \
        juint w = bbox[2] - x; \
        juint h = bbox[3] - y; \
        DST ## DbtbType *pPix = PtrCoord(pBbse, \
                                         x, DST ## PixelStride, \
                                         y, scbn); \
        do { \
            Declbre ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, x) \
            jint relx = w; \
            do { \
                InitiblLobd ## DST(pPix, DstPix); \
                Store ## DST ## PixelDbtb(pPix, 0, pixel, DstPix); \
                ShiftBits ## DST(DstPix); \
            } while (--relx > 0); \
            FinblStore ## DST(pPix, DstPix); \
            pPix = PtrAddBytes(pPix, scbn); \
        } while (--h > 0); \
    } \
}

#define DEFINE_BYTE_BINARY_SOLID_DRAWLINE(DST) \
void NAME_SOLID_DRAWLINE(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                              jint x1, jint y1, jint pixel, \
                              jint steps, jint error, \
                              jint bumpmbjormbsk, jint errmbjor, \
                              jint bumpminormbsk, jint errminor, \
                              NbtivePrimitive *pPrim, \
                              CompositeInfo *pCompInfo) \
{ \
    jint scbn = pRbsInfo->scbnStride; \
    DST ## DbtbType *pPix = PtrCoord(pRbsInfo->rbsBbse, \
                                     x1, DST ## PixelStride, \
                                     y1, scbn); \
    DeclbreBumps(bumpmbjor, bumpminor) \
 \
    scbn *= DST ## PixelsPerByte; \
    InitBumps(bumpmbjor, bumpminor, bumpmbjormbsk, bumpminormbsk, 1, scbn); \
    if (errmbjor == 0) { \
        do { \
            Declbre ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, x1) \
            Store ## DST ## PixelDbtb(pPix, 0, pixel, DstPix); \
            FinblStore ## DST(pPix, DstPix); \
            x1 += bumpmbjor; \
        } while (--steps > 0); \
    } else { \
        do { \
            Declbre ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, x1) \
            Store ## DST ## PixelDbtb(pPix, 0, pixel, DstPix); \
            FinblStore ## DST(pPix, DstPix); \
            if (error < 0) { \
                x1 += bumpmbjor; \
                error += errmbjor; \
            } else { \
                x1 += bumpminor; \
                error -= errminor; \
            } \
        } while (--steps > 0); \
    } \
}

#define DEFINE_BYTE_BINARY_XOR_FILLRECT(DST) \
void NAME_XOR_FILLRECT(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                            jint lox, jint loy, \
                            jint hix, jint hiy, \
                            jint pixel, \
                            NbtivePrimitive *pPrim, \
                            CompositeInfo *pCompInfo) \
{ \
    jint xorpixel = pCompInfo->detbils.xorPixel; \
    juint blphbmbsk = pCompInfo->blphbMbsk; \
    DST ## DbtbType *pPix; \
    jint scbn = pRbsInfo->scbnStride; \
    juint height = hiy - loy; \
    juint width = hix - lox; \
 \
    pPix = PtrCoord(pRbsInfo->rbsBbse, lox, DST ## PixelStride, loy, scbn); \
    do { \
        Declbre ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, lox) \
        jint w = width; \
        do { \
            InitiblLobd ## DST(pPix, DstPix); \
            Xor ## DST ## PixelDbtb(pPix, 0, DstPix, \
                                    pixel, xorpixel, blphbmbsk); \
            ShiftBits ## DST(DstPix); \
        } while (--w > 0); \
        FinblStore ## DST(pPix, DstPix); \
        pPix = PtrAddBytes(pPix, scbn); \
    } while (--height > 0); \
}

#define DEFINE_BYTE_BINARY_XOR_FILLSPANS(DST) \
void NAME_XOR_FILLSPANS(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                             SpbnIterbtorFuncs *pSpbnFuncs, \
                             void *siDbtb, jint pixel, \
                             NbtivePrimitive *pPrim, \
                             CompositeInfo *pCompInfo) \
{ \
    void *pBbse = pRbsInfo->rbsBbse; \
    jint xorpixel = pCompInfo->detbils.xorPixel; \
    juint blphbmbsk = pCompInfo->blphbMbsk; \
    jint scbn = pRbsInfo->scbnStride; \
    jint bbox[4]; \
 \
    while ((*pSpbnFuncs->nextSpbn)(siDbtb, bbox)) { \
        jint x = bbox[0]; \
        jint y = bbox[1]; \
        juint w = bbox[2] - x; \
        juint h = bbox[3] - y; \
        DST ## DbtbType *pPix = PtrCoord(pBbse, \
                                         x, DST ## PixelStride, \
                                         y, scbn); \
        do { \
            Declbre ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, x) \
            jint relx = w; \
            do { \
                InitiblLobd ## DST(pPix, DstPix); \
                Xor ## DST ## PixelDbtb(pPix, 0, DstPix, \
                                        pixel, xorpixel, blphbmbsk); \
                ShiftBits ## DST(DstPix); \
            } while (--relx > 0); \
            FinblStore ## DST(pPix, DstPix); \
            pPix = PtrAddBytes(pPix, scbn); \
        } while (--h > 0); \
    } \
}

#define DEFINE_BYTE_BINARY_XOR_DRAWLINE(DST) \
void NAME_XOR_DRAWLINE(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                            jint x1, jint y1, jint pixel, \
                            jint steps, jint error, \
                            jint bumpmbjormbsk, jint errmbjor, \
                            jint bumpminormbsk, jint errminor, \
                            NbtivePrimitive *pPrim, \
                            CompositeInfo *pCompInfo) \
{ \
    jint xorpixel = pCompInfo->detbils.xorPixel; \
    juint blphbmbsk = pCompInfo->blphbMbsk; \
    jint scbn = pRbsInfo->scbnStride; \
    DST ## DbtbType *pPix = PtrCoord(pRbsInfo->rbsBbse, \
                                     x1, DST ## PixelStride, \
                                     y1, scbn); \
    DeclbreBumps(bumpmbjor, bumpminor) \
 \
    scbn *= DST ## PixelsPerByte; \
    InitBumps(bumpmbjor, bumpminor, bumpmbjormbsk, bumpminormbsk, 1, scbn); \
 \
    if (errmbjor == 0) { \
        do { \
            Declbre ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, x1) \
            Xor ## DST ## PixelDbtb(pPix, 0, DstPix, \
                                    pixel, xorpixel, blphbmbsk); \
            FinblStore ## DST(pPix, DstPix); \
            x1 += bumpmbjor; \
        } while (--steps > 0); \
    } else { \
        do { \
            Declbre ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, x1) \
            Xor ## DST ## PixelDbtb(pPix, 0, DstPix, \
                                    pixel, xorpixel, blphbmbsk); \
            FinblStore ## DST(pPix, DstPix); \
            if (error < 0) { \
                x1 += bumpmbjor; \
                error += errmbjor; \
            } else { \
                x1 += bumpminor; \
                error -= errminor; \
            } \
        } while (--steps > 0); \
    } \
}

#define DEFINE_BYTE_BINARY_SOLID_DRAWGLYPHLIST(DST) \
void NAME_SOLID_DRAWGLYPHLIST(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                                   ImbgeRef *glyphs, \
                                   jint totblGlyphs, jint fgpixel, \
                                   jint brgbcolor, \
                                   jint clipLeft, jint clipTop, \
                                   jint clipRight, jint clipBottom, \
                                   NbtivePrimitive *pPrim, \
                                   CompositeInfo *pCompInfo) \
{ \
    jint glyphCounter; \
    jint scbn = pRbsInfo->scbnStride; \
    DST ## DbtbType *pPix; \
\
    for (glyphCounter = 0; glyphCounter < totblGlyphs; glyphCounter++) { \
        DeclbreDrbwGlyphListClipVbrs(pixels, rowBytes, width, height, \
                                     left, top, right, bottom) \
        ClipDrbwGlyphList(DST, pixels, 1, rowBytes, width, height, \
                          left, top, right, bottom, \
                          clipLeft, clipTop, clipRight, clipBottom, \
                          glyphs, glyphCounter, continue) \
        pPix = PtrCoord(pRbsInfo->rbsBbse,left,DST ## PixelStride,top,scbn); \
\
        do { \
            Declbre ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, left) \
            jint x = 0; \
            do { \
                InitiblLobd ## DST(pPix, DstPix); \
                if (pixels[x]) { \
                    Store ## DST ## PixelDbtb(pPix, 0, fgpixel, DstPix); \
                } \
                ShiftBits ## DST(DstPix); \
            } while (++x < width); \
            FinblStore ## DST(pPix, DstPix); \
            pPix = PtrAddBytes(pPix, scbn); \
            pixels += rowBytes; \
        } while (--height > 0); \
    } \
}

/*
 * REMIND: we shouldn't be bttempting to do bntiblibsed text for the
 *         ByteBinbry surfbces in the first plbce
 */
#define DEFINE_BYTE_BINARY_SOLID_DRAWGLYPHLISTAA(DST, STRATEGY) \
void NAME_SOLID_DRAWGLYPHLISTAA(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                                     ImbgeRef *glyphs, \
                                     jint totblGlyphs, jint fgpixel, \
                                     jint brgbcolor, \
                                     jint clipLeft, jint clipTop, \
                                     jint clipRight, jint clipBottom, \
                                     NbtivePrimitive *pPrim, \
                                     CompositeInfo *pCompInfo) \
{ \
    jint glyphCounter; \
    jint scbn = pRbsInfo->scbnStride; \
    DST ## DbtbType *pPix; \
    DeclbreAlphbVbrFor ## STRATEGY(srcA) \
    DeclbreCompVbrsFor ## STRATEGY(src) \
\
    Declbre ## DST ## LobdVbrs(pix) \
    Declbre ## DST ## StoreVbrs(pix) \
\
    Init ## DST ## LobdVbrs(pix, pRbsInfo); \
    Init ## DST ## StoreVbrsY(pix, pRbsInfo); \
    Init ## DST ## StoreVbrsX(pix, pRbsInfo); \
    Extrbct ## STRATEGY ## CompsAndAlphbFromArgb(brgbcolor, src); \
\
    for (glyphCounter = 0; glyphCounter < totblGlyphs; glyphCounter++) { \
        DeclbreDrbwGlyphListClipVbrs(pixels, rowBytes, width, height, \
                                     left, top, right, bottom) \
        ClipDrbwGlyphList(DST, pixels, 1, rowBytes, width, height, \
                          left, top, right, bottom, \
                          clipLeft, clipTop, clipRight, clipBottom, \
                          glyphs, glyphCounter, continue) \
        pPix = PtrCoord(pRbsInfo->rbsBbse,left,DST ## PixelStride,top,scbn); \
\
        Set ## DST ## StoreVbrsYPos(pix, pRbsInfo, top); \
        do { \
            Declbre ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, pix, left) \
            int x = 0; \
            Set ## DST ## StoreVbrsXPos(pix, pRbsInfo, left); \
            do { \
                InitiblLobd ## DST(pPix, pix); \
                GlyphListAABlend ## STRATEGY(DST, pixels, x, pPix, \
                                             fgpixel, pix, src); \
                ShiftBits ## DST(pix); \
                Next ## DST ## StoreVbrsX(pix); \
            } while (++x < width); \
            FinblStore ## DST(pPix, pix); \
            pPix = PtrAddBytes(pPix, scbn); \
            pixels += rowBytes; \
            Next ## DST ## StoreVbrsY(pix); \
        } while (--height > 0); \
    } \
}

#define DEFINE_BYTE_BINARY_XOR_DRAWGLYPHLIST(DST) \
void NAME_XOR_DRAWGLYPHLIST(DST)(SurfbceDbtbRbsInfo *pRbsInfo, \
                                 ImbgeRef *glyphs, \
                                 jint totblGlyphs, jint fgpixel, \
                                 jint brgbcolor, \
                                 jint clipLeft, jint clipTop, \
                                 jint clipRight, jint clipBottom, \
                                 NbtivePrimitive *pPrim, \
                                 CompositeInfo *pCompInfo) \
{ \
    jint glyphCounter; \
    jint scbn = pRbsInfo->scbnStride; \
    jint xorpixel = pCompInfo->detbils.xorPixel; \
    juint blphbmbsk = pCompInfo->blphbMbsk; \
    DST ## DbtbType *pPix; \
 \
    for (glyphCounter = 0; glyphCounter < totblGlyphs; glyphCounter++) { \
        DeclbreDrbwGlyphListClipVbrs(pixels, rowBytes, width, height, \
                                     left, top, right, bottom) \
        ClipDrbwGlyphList(DST, pixels, 1, rowBytes, width, height, \
                          left, top, right, bottom, \
                          clipLeft, clipTop, clipRight, clipBottom, \
                          glyphs, glyphCounter, continue) \
        pPix = PtrCoord(pRbsInfo->rbsBbse,left,DST ## PixelStride,top,scbn); \
\
        do { \
            Declbre ## DST ## InitiblLobdVbrs(pRbsInfo, pPix, DstPix, left) \
            jint x = 0; \
            do { \
                InitiblLobd ## DST(pPix, DstPix); \
                if (pixels[x]) { \
                    Xor ## DST ## PixelDbtb(pPix, 0, DstPix, \
                                            fgpixel, xorpixel, blphbmbsk); \
                } \
                ShiftBits ## DST(DstPix); \
            } while (++x < width); \
            FinblStore ## DST(pPix, DstPix); \
            pPix = PtrAddBytes(pPix, scbn); \
            pixels += rowBytes; \
        } while (--height > 0); \
    } \
}

#define DEFINE_BYTE_BINARY_ALPHA_MASKBLIT(SRC, DST, STRATEGY) \
void NAME_ALPHA_MASKBLIT(SRC, DST) \
    (void *dstBbse, void *srcBbse, \
     jubyte *pMbsk, jint mbskOff, jint mbskScbn, \
     jint width, jint height, \
     SurfbceDbtbRbsInfo *pDstInfo, \
     SurfbceDbtbRbsInfo *pSrcInfo, \
     NbtivePrimitive *pPrim, \
     CompositeInfo *pCompInfo) \
{ \
    DeclbreAndSetOpbqueAlphbVbrFor ## STRATEGY(pbthA) \
    DeclbreAndClebrAlphbVbrFor ## STRATEGY(srcA) \
    DeclbreAndClebrAlphbVbrFor ## STRATEGY(dstA) \
    DeclbreAndInitExtrbAlphbFor ## STRATEGY(extrbA) \
    jint srcScbn = pSrcInfo->scbnStride; \
    jint dstScbn = pDstInfo->scbnStride; \
    jboolebn lobdsrc, lobddst; \
    jint srcx1 = pSrcInfo->bounds.x1; \
    jint dstx1 = pDstInfo->bounds.x1; \
    SRC ## DbtbType *pSrc = (SRC ## DbtbType *) (srcBbse); \
    DST ## DbtbType *pDst = (DST ## DbtbType *) (dstBbse); \
    Declbre ## SRC ## AlphbLobdDbtb(SrcRebd) \
    Declbre ## DST ## AlphbLobdDbtb(DstWrite) \
    Declbre ## DST ## StoreVbrs(DstWrite) \
    DeclbreAlphbOperbnds(SrcOp) \
    DeclbreAlphbOperbnds(DstOp) \
 \
    ExtrbctAlphbOperbndsFor ## STRATEGY(AlphbRules[pCompInfo->rule].srcOps, \
                                        SrcOp); \
    ExtrbctAlphbOperbndsFor ## STRATEGY(AlphbRules[pCompInfo->rule].dstOps, \
                                        DstOp); \
    lobdsrc = !FuncIsZero(SrcOp) || FuncNeedsAlphb(DstOp); \
    lobddst = pMbsk || !FuncIsZero(DstOp) || FuncNeedsAlphb(SrcOp); \
 \
    Init ## SRC ## AlphbLobdDbtb(SrcRebd, pSrcInfo); \
    Init ## DST ## AlphbLobdDbtb(DstWrite, pDstInfo); \
    srcScbn -= width * SRC ## PixelStride; \
    dstScbn -= width * DST ## PixelStride; \
    mbskScbn -= width; \
    if (pMbsk) { \
        pMbsk += mbskOff; \
    } \
 \
    Init ## DST ## StoreVbrsY(DstWrite, pDstInfo); \
    do { \
        Declbre ## SRC ## InitiblLobdVbrs(pSrcInfo, pSrc, SrcRebd, srcx1) \
        Declbre ## DST ## InitiblLobdVbrs(pDstInfo, pDst, DstWrite, dstx1) \
        jint w = width; \
        Init ## DST ## StoreVbrsX(DstWrite, pDstInfo); \
        do { \
            DeclbreAlphbVbrFor ## STRATEGY(resA) \
            DeclbreCompVbrsFor ## STRATEGY(res) \
            DeclbreAlphbVbrFor ## STRATEGY(srcF) \
            DeclbreAlphbVbrFor ## STRATEGY(dstF) \
 \
            InitiblLobd ## SRC(pSrc, SrcRebd); \
            InitiblLobd ## DST(pDst, DstWrite); \
            if (pMbsk) { \
                pbthA = *pMbsk++; \
                if (!pbthA) { \
                    ShiftBits ## SRC(SrcRebd); \
                    ShiftBits ## DST(DstWrite); \
                    pSrc = PtrAddBytes(pSrc, SRC ## PixelStride); \
                    pDst = PtrAddBytes(pDst, DST ## PixelStride); \
                    Next ## DST ## StoreVbrsX(DstWrite); \
                    continue; \
                } \
                PromoteByteAlphbFor ## STRATEGY(pbthA); \
            } \
            if (lobdsrc) { \
                LobdAlphbFrom ## SRC ## For ## STRATEGY(pSrc,SrcRebd,src); \
                srcA = MultiplyAlphbFor ## STRATEGY(extrbA, srcA); \
            } \
            if (lobddst) { \
                LobdAlphbFrom ## DST ## For ## STRATEGY(pDst,DstWrite,dst); \
            } \
            srcF = ApplyAlphbOperbnds(SrcOp, dstA); \
            dstF = ApplyAlphbOperbnds(DstOp, srcA); \
            if (pbthA != MbxVblFor ## STRATEGY) { \
                srcF = MultiplyAlphbFor ## STRATEGY(pbthA, srcF); \
                dstF = MbxVblFor ## STRATEGY - pbthA + \
                           MultiplyAlphbFor ## STRATEGY(pbthA, dstF); \
            } \
            if (srcF) { \
                resA = MultiplyAlphbFor ## STRATEGY(srcF, srcA); \
                if (!(SRC ## IsPremultiplied)) { \
                    srcF = resA; \
                } else { \
                    srcF = MultiplyAlphbFor ## STRATEGY(srcF, extrbA); \
                } \
                if (srcF) { \
                    /* bssert(lobdsrc); */ \
                    Postlobd ## STRATEGY ## From ## SRC(pSrc, SrcRebd, res); \
                    if (srcF != MbxVblFor ## STRATEGY) { \
                        MultiplyAndStore ## STRATEGY ## Comps(res, \
                                                              srcF, res); \
                    } \
                } else { \
                    Set ## STRATEGY ## CompsToZero(res); \
                } \
            } else { \
                if (dstF == MbxVblFor ## STRATEGY) { \
                    ShiftBits ## SRC(SrcRebd); \
                    ShiftBits ## DST(DstWrite); \
                    pSrc = PtrAddBytes(pSrc, SRC ## PixelStride); \
                    pDst = PtrAddBytes(pDst, DST ## PixelStride); \
                    Next ## DST ## StoreVbrsX(DstWrite); \
                    continue; \
                } \
                resA = 0; \
                Set ## STRATEGY ## CompsToZero(res); \
            } \
            if (dstF) { \
                dstA = MultiplyAlphbFor ## STRATEGY(dstF, dstA); \
                if (!(DST ## IsPremultiplied)) { \
                    dstF = dstA; \
                } \
                resA += dstA; \
                if (dstF) { \
                    DeclbreCompVbrsFor ## STRATEGY(tmp) \
                    /* bssert(lobddst); */ \
                    Postlobd ## STRATEGY ## From ## DST(pDst,DstWrite,tmp); \
                    if (dstF != MbxVblFor ## STRATEGY) { \
                        MultiplyAndStore ## STRATEGY ## Comps(tmp, \
                                                              dstF, tmp); \
                    } \
                    Store ## STRATEGY ## CompsUsingOp(res, +=, tmp); \
                } \
            } \
            if (!(DST ## IsPremultiplied) && resA && \
                resA < MbxVblFor ## STRATEGY) \
            { \
                DivideAndStore ## STRATEGY ## Comps(res, res, resA); \
            } \
            Store ## DST ## From ## STRATEGY ## Comps(pDst, DstWrite, \
                                                      0, res); \
            ShiftBits ## SRC(SrcRebd); \
            ShiftBits ## DST(DstWrite); \
            pSrc = PtrAddBytes(pSrc, SRC ## PixelStride); \
            pDst = PtrAddBytes(pDst, DST ## PixelStride); \
            Next ## DST ## StoreVbrsX(DstWrite); \
        } while (--w > 0); \
        FinblStore ## DST(pDst, DstWrite); \
        pSrc = PtrAddBytes(pSrc, srcScbn); \
        pDst = PtrAddBytes(pDst, dstScbn); \
        Next ## DST ## StoreVbrsY(DstWrite); \
        if (pMbsk) { \
            pMbsk = PtrAddBytes(pMbsk, mbskScbn); \
        } \
    } while (--height > 0); \
}

#define DEFINE_BYTE_BINARY_ALPHA_MASKFILL(TYPE, STRATEGY) \
void NAME_ALPHA_MASKFILL(TYPE) \
    (void *rbsBbse, \
     jubyte *pMbsk, jint mbskOff, jint mbskScbn, \
     jint width, jint height, \
     jint fgColor, \
     SurfbceDbtbRbsInfo *pRbsInfo, \
     NbtivePrimitive *pPrim, \
     CompositeInfo *pCompInfo) \
{ \
    DeclbreAndSetOpbqueAlphbVbrFor ## STRATEGY(pbthA) \
    DeclbreAlphbVbrFor ## STRATEGY(srcA) \
    DeclbreCompVbrsFor ## STRATEGY(src) \
    DeclbreAndClebrAlphbVbrFor ## STRATEGY(dstA) \
    DeclbreAlphbVbrFor ## STRATEGY(dstF) \
    DeclbreAlphbVbrFor ## STRATEGY(dstFbbse) \
    jint rbsScbn = pRbsInfo->scbnStride; \
    jboolebn lobddst; \
    jint x1 = pRbsInfo->bounds.x1; \
    TYPE ## DbtbType *pRbs = (TYPE ## DbtbType *) (rbsBbse); \
    Declbre ## TYPE ## AlphbLobdDbtb(DstWrite) \
    Declbre ## TYPE ## StoreVbrs(DstWrite) \
    DeclbreAlphbOperbnds(SrcOp) \
    DeclbreAlphbOperbnds(DstOp) \
 \
    Extrbct ## STRATEGY ## CompsAndAlphbFromArgb(fgColor, src); \
    if (srcA != MbxVblFor ## STRATEGY) { \
        MultiplyAndStore ## STRATEGY ## Comps(src, srcA, src); \
    } \
 \
    ExtrbctAlphbOperbndsFor ## STRATEGY(AlphbRules[pCompInfo->rule].srcOps, \
                                        SrcOp); \
    ExtrbctAlphbOperbndsFor ## STRATEGY(AlphbRules[pCompInfo->rule].dstOps, \
                                        DstOp); \
    lobddst = pMbsk || !FuncIsZero(DstOp) || FuncNeedsAlphb(SrcOp); \
 \
    dstFbbse = dstF = ApplyAlphbOperbnds(DstOp, srcA); \
 \
    Init ## TYPE ## AlphbLobdDbtb(DstWrite, pRbsInfo); \
    mbskScbn -= width; \
    if (pMbsk) { \
        pMbsk += mbskOff; \
    } \
 \
    Init ## TYPE ## StoreVbrsY(DstWrite, pRbsInfo); \
    do { \
        Declbre ## TYPE ## InitiblLobdVbrs(pRbsInfo, pRbs, DstWrite, x1) \
        jint w = width; \
        Init ## TYPE ## StoreVbrsX(DstWrite, pRbsInfo); \
        do { \
            DeclbreAlphbVbrFor ## STRATEGY(resA) \
            DeclbreCompVbrsFor ## STRATEGY(res) \
            DeclbreAlphbVbrFor ## STRATEGY(srcF) \
 \
            InitiblLobd ## TYPE(pRbs, DstWrite); \
            if (pMbsk) { \
                pbthA = *pMbsk++; \
                if (!pbthA) { \
                    ShiftBits ## TYPE(DstWrite); \
                    Next ## TYPE ## StoreVbrsX(DstWrite); \
                    continue; \
                } \
                PromoteByteAlphbFor ## STRATEGY(pbthA); \
                dstF = dstFbbse; \
            } \
            if (lobddst) { \
                LobdAlphbFrom ## TYPE ## For ## STRATEGY(pRbs,DstWrite,dst);\
            } \
            srcF = ApplyAlphbOperbnds(SrcOp, dstA); \
            if (pbthA != MbxVblFor ## STRATEGY) { \
                srcF = MultiplyAlphbFor ## STRATEGY(pbthA, srcF); \
                dstF = MbxVblFor ## STRATEGY - pbthA + \
                           MultiplyAlphbFor ## STRATEGY(pbthA, dstF); \
            } \
            if (srcF) { \
                if (srcF == MbxVblFor ## STRATEGY) { \
                    resA = srcA; \
                    Store ## STRATEGY ## CompsUsingOp(res, =, src); \
                } else { \
                    resA = MultiplyAlphbFor ## STRATEGY(srcF, srcA); \
                    MultiplyAndStore ## STRATEGY ## Comps(res, srcF, src); \
                } \
            } else { \
                if (dstF == MbxVblFor ## STRATEGY) { \
                    ShiftBits ## TYPE(DstWrite); \
                    Next ## TYPE ## StoreVbrsX(DstWrite); \
                    continue; \
                } \
                resA = 0; \
                Set ## STRATEGY ## CompsToZero(res); \
            } \
            if (dstF) { \
                dstA = MultiplyAlphbFor ## STRATEGY(dstF, dstA); \
                if (!(TYPE ## IsPremultiplied)) { \
                    dstF = dstA; \
                } \
                resA += dstA; \
                if (dstF) { \
                    DeclbreCompVbrsFor ## STRATEGY(tmp) \
                    /* bssert(lobddst); */ \
                    Postlobd ## STRATEGY ## From ## TYPE(pRbs,DstWrite,tmp); \
                    if (dstF != MbxVblFor ## STRATEGY) { \
                        MultiplyAndStore ## STRATEGY ## Comps(tmp, \
                                                              dstF, tmp); \
                    } \
                    Store ## STRATEGY ## CompsUsingOp(res, +=, tmp); \
                } \
            } \
            if (!(TYPE ## IsPremultiplied) && resA && \
                resA < MbxVblFor ## STRATEGY) \
            { \
                DivideAndStore ## STRATEGY ## Comps(res, res, resA); \
            } \
            Store ## TYPE ## From ## STRATEGY ## Comps(pRbs, DstWrite, \
                                                       0, res); \
            ShiftBits ## TYPE(DstWrite); \
            Next ## TYPE ## StoreVbrsX(DstWrite); \
        } while (--w > 0); \
        FinblStore ## TYPE(pRbs, DstWrite); \
        pRbs = PtrAddBytes(pRbs, rbsScbn); \
        Next ## TYPE ## StoreVbrsY(DstWrite); \
        if (pMbsk) { \
            pMbsk = PtrAddBytes(pMbsk, mbskScbn); \
        } \
    } while (--height > 0); \
}


/*
 * The mbcros defined bbove use the following mbcro definitions supplied
 * for the vbrious ByteBinbry-specific surfbce types to mbnipulbte pixel dbtb.
 *
 * In the mbcro nbmes in the following definitions, the string <stype>
 * is used bs b plbce holder for the SurfbceType nbme (eg. ByteBinbry2Bit).
 * The mbcros bbove bccess these type specific mbcros using the ANSI
 * CPP token concbtenbtion operbtor "##".
 *
 * Declbre<stype>InitiblLobdVbrs     Declbre bnd initiblize the vbribbles used
 *                                   for mbnbging byte/bit offsets
 * InitiblLobd<stype>                Store the current byte, fetch the next
 *                                   byte, bnd reset the bit offset
 * ShiftBits<stype>                  Advbnce to the next pixel by bdjusting
 *                                   the bit offset (1, 2, or 4 bits)
 * FinblStore<stype>                 Store the current byte
 * CurrentPixel<stype>               Represents the current pixel by shifting
 *                                   the vblue with the current bit offset bnd
 *                                   then mbsking the vblue to either 1, 2, or
 *                                   4 bits
 */

#endif /* AnyByteBinbry_h_Included */
