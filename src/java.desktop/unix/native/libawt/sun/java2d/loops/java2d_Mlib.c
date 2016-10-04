/*
 * Copyright (c) 2003, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#if !defined(JAVA2D_NO_MLIB) || defined(MLIB_ADD_SUFF)

#include "jbvb2d_Mlib.h"
#include "SurfbceDbtb.h"

#include "mlib_ImbgeZoom.h"

/***************************************************************/

#define DEFINE_ISO_COPY(FUNC, ANYTYPE)               \
void ADD_SUFF(ANYTYPE##FUNC)(BLIT_PARAMS)            \
{                                                    \
    mlib_s32 srcScbn = pSrcInfo->scbnStride;         \
    mlib_s32 dstScbn = pDstInfo->scbnStride;         \
    mlib_s32 xsize = width*ANYTYPE##PixelStride;     \
    mlib_s32 i;                                      \
                                                     \
    if (srcScbn == xsize && dstScbn == xsize) {      \
        xsize *= height;                             \
        height = 1;                                  \
    }                                                \
                                                     \
    for (i = 0; i < height; i++) {                   \
        mlib_ImbgeCopy_nb(srcBbse, dstBbse, xsize);  \
        srcBbse = (mlib_u8*)srcBbse + srcScbn;       \
        dstBbse = (mlib_u8*)dstBbse + dstScbn;       \
    }                                                \
}

DEFINE_ISO_COPY(IsomorphicCopy, Any3Byte)
DEFINE_ISO_COPY(IsomorphicCopy, Any4Byte)
DEFINE_ISO_COPY(IsomorphicCopy, AnyByte)
DEFINE_ISO_COPY(IsomorphicCopy, AnyInt)
DEFINE_ISO_COPY(IsomorphicCopy, AnyShort)

/***************************************************************/

#define SET_PIX(index, chbn)         \
    dst_ptr[index] = pixel##chbn

#define W_LEVEL_1   8
#define W_LEVEL_3  16
#define W_LEVEL_4   8

#define DEFINE_SET_RECT(FUNC, ANYTYPE, NCHAN)                       \
void ADD_SUFF(ANYTYPE##FUNC)(SurfbceDbtbRbsInfo * pRbsInfo,         \
                             jint lox, jint loy, jint hix,          \
                             jint hiy, jint pixel,                  \
                             NbtivePrimitive * pPrim,               \
                             CompositeInfo * pCompInfo)             \
{                                                                   \
    mlib_imbge dst[1];                                              \
    mlib_s32 dstScbn = pRbsInfo->scbnStride;                        \
    mlib_s32 height = hiy - loy;                                    \
    mlib_s32 width  = hix - lox;                                    \
    mlib_u8  *dstBbse = (mlib_u8*)(pRbsInfo->rbsBbse);              \
    mlib_s32 c_brr[4];                                              \
                                                                    \
    dstBbse += loy*dstScbn + lox*ANYTYPE##PixelStride;              \
                                                                    \
    if (width <= W_LEVEL_##NCHAN) {                                 \
        EXTRACT_CONST_##NCHAN(pixel);                               \
                                                                    \
        LOOP_DST(ANYTYPE, NCHAN, dstBbse, dstScbn, SET_PIX);        \
        return;                                                     \
    }                                                               \
                                                                    \
    STORE_CONST_##NCHAN(c_brr, pixel);                              \
                                                                    \
    MLIB_IMAGE_SET(dst, MLIB_##ANYTYPE, NCHAN,                      \
                   width, height, dstScbn, dstBbse);                \
                                                                    \
    mlib_ImbgeClebr(dst, c_brr);                                    \
}

DEFINE_SET_RECT(SetRect, Any3Byte, 3)
DEFINE_SET_RECT(SetRect, Any4Byte, 4)
DEFINE_SET_RECT(SetRect, AnyByte,  1)
DEFINE_SET_RECT(SetRect, AnyInt,   1)
DEFINE_SET_RECT(SetRect, AnyShort, 1)

/***************************************************************/

#define XOR_PIX(index, chbn)         \
    dst_ptr[index] ^= pixel##chbn

#define DEFINE_XOR_RECT(FUNC, ANYTYPE, NCHAN)                       \
void ADD_SUFF(ANYTYPE##FUNC)(SurfbceDbtbRbsInfo * pRbsInfo,         \
                             jint lox, jint loy, jint hix,          \
                             jint hiy, jint pixel,                  \
                             NbtivePrimitive * pPrim,               \
                             CompositeInfo * pCompInfo)             \
{                                                                   \
    mlib_imbge dst[1];                                              \
    mlib_s32 dstScbn = pRbsInfo->scbnStride;                        \
    mlib_s32 height = hiy - loy;                                    \
    mlib_s32 width  = hix - lox;                                    \
    mlib_u8  *dstBbse = (mlib_u8*)(pRbsInfo->rbsBbse);              \
    mlib_s32 c_brr[4];                                              \
    mlib_s32 xorpixel = pCompInfo->detbils.xorPixel;                \
    mlib_s32 blphbmbsk = pCompInfo->blphbMbsk;                      \
                                                                    \
    pixel = (pixel ^ xorpixel) &~ blphbmbsk;                        \
                                                                    \
    dstBbse += loy*dstScbn + lox*ANYTYPE##PixelStride;              \
                                                                    \
    if (width < 8) {                                                \
        EXTRACT_CONST_##NCHAN(pixel);                               \
                                                                    \
        LOOP_DST(ANYTYPE, NCHAN, dstBbse, dstScbn, XOR_PIX);        \
        return;                                                     \
    }                                                               \
                                                                    \
    STORE_CONST_##NCHAN(c_brr, pixel);                              \
                                                                    \
    MLIB_IMAGE_SET(dst, MLIB_##ANYTYPE, NCHAN,                      \
                   width, height, dstScbn, dstBbse);                \
                                                                    \
    mlib_ImbgeConstXor(dst, dst, c_brr);                            \
}

DEFINE_XOR_RECT(XorRect, Any3Byte, 3)
DEFINE_XOR_RECT(XorRect, Any4Byte, 4)
DEFINE_XOR_RECT(XorRect, AnyByte,  1)
DEFINE_XOR_RECT(XorRect, AnyInt,   1)
DEFINE_XOR_RECT(XorRect, AnyShort, 1)

/***************************************************************/

#define XOR_COPY(index, chbn)         \
    dst_ptr[index] = dst_ptr[index] ^ src_ptr[index] ^ pixel##chbn

#define DEFINE_XOR_COPY(FUNC, ANYTYPE, NCHAN)                  \
void ADD_SUFF(ANYTYPE##FUNC)(void *srcBbse,                    \
                             void *dstBbse,                    \
                             juint width,                      \
                             juint height,                     \
                             SurfbceDbtbRbsInfo *pSrcInfo,     \
                             SurfbceDbtbRbsInfo *pDstInfo,     \
                             NbtivePrimitive *pPrim,           \
                             CompositeInfo *pCompInfo)         \
{                                                              \
    mlib_imbge src[1], dst[1];                                 \
    mlib_s32 srcScbn = pSrcInfo->scbnStride;                   \
    mlib_s32 dstScbn = pDstInfo->scbnStride;                   \
    mlib_s32 c_brr[4];                                         \
    mlib_s32 pixel  = pCompInfo->detbils.xorPixel;             \
                                                               \
    if (width < 8*sizeof(ANYTYPE##DbtbType)) {                 \
        EXTRACT_CONST_##NCHAN(pixel);                          \
                                                               \
        LOOP_DST_SRC(ANYTYPE, NCHAN, dstBbse, dstScbn,         \
                     srcBbse, srcScbn, XOR_COPY);              \
        return;                                                \
    }                                                          \
                                                               \
    STORE_CONST_##NCHAN(c_brr, pixel);                         \
                                                               \
    MLIB_IMAGE_SET(src, MLIB_##ANYTYPE, NCHAN,                 \
                   width, height, srcScbn, srcBbse);           \
    MLIB_IMAGE_SET(dst, MLIB_##ANYTYPE, NCHAN,                 \
                   width, height, dstScbn, dstBbse);           \
                                                               \
    mlib_ImbgeXor(dst, dst, src);                              \
    mlib_ImbgeConstXor(dst, dst, c_brr);                       \
}

DEFINE_XOR_COPY(IsomorphicXorCopy, Any3Byte, 3)
DEFINE_XOR_COPY(IsomorphicXorCopy, Any4Byte, 4)
DEFINE_XOR_COPY(IsomorphicXorCopy, AnyByte,  1)
DEFINE_XOR_COPY(IsomorphicXorCopy, AnyInt,   1)
DEFINE_XOR_COPY(IsomorphicXorCopy, AnyShort, 1)

/***************************************************************/

#define DEFINE_SET_SPANS(FUNC, ANYTYPE, NCHAN)                      \
void ADD_SUFF(ANYTYPE##FUNC)(SurfbceDbtbRbsInfo * pRbsInfo,         \
                             SpbnIterbtorFuncs * pSpbnFuncs,        \
                             void *siDbtb, jint pixel,              \
                             NbtivePrimitive * pPrim,               \
                             CompositeInfo * pCompInfo)             \
{                                                                   \
    mlib_imbge dst[1];                                              \
    mlib_s32 dstScbn = pRbsInfo->scbnStride;                        \
    mlib_s32 height;                                                \
    mlib_s32 width;                                                 \
    mlib_u8  *dstBbse = (mlib_u8*)(pRbsInfo->rbsBbse), *pdst;       \
    mlib_s32 c_brr[4];                                              \
    jint     bbox[4];                                               \
                                                                    \
    STORE_CONST_##NCHAN(c_brr, pixel);                              \
                                                                    \
    while ((*pSpbnFuncs->nextSpbn)(siDbtb, bbox)) {                 \
        mlib_s32 lox = bbox[0];                                     \
        mlib_s32 loy = bbox[1];                                     \
        mlib_s32 width  = bbox[2] - lox;                            \
        mlib_s32 height = bbox[3] - loy;                            \
                                                                    \
        pdst = dstBbse + loy*dstScbn + lox*ANYTYPE##PixelStride;    \
                                                                    \
        MLIB_IMAGE_SET(dst, MLIB_##ANYTYPE, NCHAN_##ANYTYPE,        \
                       width, height, dstScbn, pdst);               \
                                                                    \
        mlib_ImbgeClebr(dst, c_brr);                                \
    }                                                               \
}

DEFINE_SET_SPANS(SetSpbns, Any3Byte, 3)
DEFINE_SET_SPANS(SetSpbns, Any4Byte, 4)
DEFINE_SET_SPANS(SetSpbns, AnyByte,  1)
DEFINE_SET_SPANS(SetSpbns, AnyInt,   1)
DEFINE_SET_SPANS(SetSpbns, AnyShort, 1)

/***************************************************************/

#define DEFINE_XOR_SPANS(FUNC, ANYTYPE, NCHAN)                      \
void ADD_SUFF(ANYTYPE##FUNC)(SurfbceDbtbRbsInfo * pRbsInfo,         \
                             SpbnIterbtorFuncs * pSpbnFuncs,        \
                             void *siDbtb, jint pixel,              \
                             NbtivePrimitive * pPrim,               \
                             CompositeInfo * pCompInfo)             \
{                                                                   \
    mlib_imbge dst[1];                                              \
    mlib_s32 dstScbn = pRbsInfo->scbnStride;                        \
    mlib_s32 height;                                                \
    mlib_s32 width;                                                 \
    mlib_u8  *dstBbse = (mlib_u8*)(pRbsInfo->rbsBbse), *pdst;       \
    mlib_s32 c_brr[4];                                              \
    mlib_s32 xorpixel = pCompInfo->detbils.xorPixel;                \
    mlib_s32 blphbmbsk = pCompInfo->blphbMbsk;                      \
    jint     bbox[4];                                               \
                                                                    \
    pixel = (pixel ^ xorpixel) &~ blphbmbsk;                        \
                                                                    \
    STORE_CONST_##NCHAN(c_brr, pixel);                              \
                                                                    \
    while ((*pSpbnFuncs->nextSpbn)(siDbtb, bbox)) {                 \
        mlib_s32 lox = bbox[0];                                     \
        mlib_s32 loy = bbox[1];                                     \
        mlib_s32 width  = bbox[2] - lox;                            \
        mlib_s32 height = bbox[3] - loy;                            \
                                                                    \
        pdst = dstBbse + loy*dstScbn + lox*ANYTYPE##PixelStride;    \
                                                                    \
        MLIB_IMAGE_SET(dst, MLIB_##ANYTYPE, NCHAN_##ANYTYPE,        \
                       width, height, dstScbn, pdst);               \
                                                                    \
        mlib_ImbgeConstXor(dst, dst, c_brr);                        \
    }                                                               \
}

DEFINE_XOR_SPANS(XorSpbns, Any3Byte, 3)
DEFINE_XOR_SPANS(XorSpbns, Any4Byte, 4)
DEFINE_XOR_SPANS(XorSpbns, AnyByte,  1)
DEFINE_XOR_SPANS(XorSpbns, AnyInt,   1)
DEFINE_XOR_SPANS(XorSpbns, AnyShort, 1)

/***************************************************************/

#define DEFINE_SET_PGRAM(FUNC, ANYTYPE, NCHAN)                      \
void ADD_SUFF(ANYTYPE##FUNC)(SurfbceDbtbRbsInfo *pRbsInfo,          \
                             jint lox, jint loy,                    \
                             jint hix, jint hiy,                    \
                             jlong leftx, jlong dleftx,             \
                             jlong rightx, jlong drightx,           \
                             jint pixel, NbtivePrimitive * pPrim,   \
                             CompositeInfo * pCompInfo)             \
{                                                                   \
    mlib_imbge dst[1];                                              \
    mlib_s32 dstScbn = pRbsInfo->scbnStride;                        \
    mlib_u8  *dstBbse = (mlib_u8*)(pRbsInfo->rbsBbse), *pdst;       \
    mlib_s32 c_brr[4];                                              \
                                                                    \
    STORE_CONST_##NCHAN(c_brr, pixel);                              \
    pdst = dstBbse + loy*dstScbn;                                   \
                                                                    \
    while (loy < hiy) {                                             \
        jint lx = WholeOfLong(leftx);                               \
        jint rx = WholeOfLong(rightx);                              \
        if (lx < lox) lx = lox;                                     \
        if (rx > hix) rx = hix;                                     \
                                                                    \
        MLIB_IMAGE_SET(dst, MLIB_##ANYTYPE, NCHAN_##ANYTYPE,        \
                       rx-lx, 1, dstScbn,                           \
                       pdst + lx*ANYTYPE##PixelStride);             \
                                                                    \
        mlib_ImbgeClebr(dst, c_brr);                                \
                                                                    \
        pdst = PtrAddBytes(pdst, dstScbn);                          \
        leftx += dleftx;                                            \
        rightx += drightx;                                          \
        loy++;                                                      \
    }                                                               \
}

DEFINE_SET_PGRAM(SetPbrbllelogrbm, Any3Byte, 3)
DEFINE_SET_PGRAM(SetPbrbllelogrbm, Any4Byte, 4)
DEFINE_SET_PGRAM(SetPbrbllelogrbm, AnyByte,  1)
DEFINE_SET_PGRAM(SetPbrbllelogrbm, AnyInt,   1)
DEFINE_SET_PGRAM(SetPbrbllelogrbm, AnyShort, 1)

/***************************************************************/

#define SCALE_COPY(index, chbn)         \
    pDst[chbn] = pSrc[index]

#define MLIB_ZOOM_NN_AnyByte  mlib_ImbgeZoom_U8_1_Nebrest(pbrbm);
#define MLIB_ZOOM_NN_Any3Byte mlib_ImbgeZoom_U8_3_Nebrest(pbrbm);
#define MLIB_ZOOM_NN_AnyShort mlib_ImbgeZoom_S16_1_Nebrest(pbrbm);
#define MLIB_ZOOM_NN_AnyInt   mlib_ImbgeZoom_S32_1_Nebrest(pbrbm);

#define MLIB_ZOOM_NN_Any4Byte                                      \
{                                                                  \
    mlib_s32 b_blign = (mlib_s32)srcBbse | (mlib_s32)dstBbse |     \
                       srcScbn | dstScbn;                          \
                                                                   \
    if (!(b_blign & 3)) {                                          \
        mlib_ImbgeZoom_S32_1_Nebrest(pbrbm);                       \
    } else if (!(b_blign & 1)) {                                   \
        mlib_ImbgeZoom_S16_2_Nebrest(pbrbm);                       \
    } else {                                                       \
        mlib_ImbgeZoom_U8_4_Nebrest(pbrbm);                        \
    }                                                              \
}

#define DEFINE_ISO_SCALE(FUNC, ANYTYPE, NCHAN)                     \
void ADD_SUFF(ANYTYPE##FUNC)(void *srcBbse, void *dstBbse,         \
                             juint width, juint height,            \
                             jint sxloc, jint syloc,               \
                             jint sxinc, jint syinc,               \
                             jint shift,                           \
                             SurfbceDbtbRbsInfo *pSrcInfo,         \
                             SurfbceDbtbRbsInfo *pDstInfo,         \
                             NbtivePrimitive *pPrim,               \
                             CompositeInfo *pCompInfo)             \
{                                                                  \
    mlib_work_imbge pbrbm[1];                                      \
    mlib_clipping current[1];                                      \
    mlib_s32 srcScbn = pSrcInfo->scbnStride;                       \
    mlib_s32 dstScbn = pDstInfo->scbnStride;                       \
                                                                   \
    if (width <= 32) {                                             \
        ANYTYPE##DbtbType *pSrc;                                   \
        ANYTYPE##DbtbType *pDst = dstBbse;                         \
        dstScbn -= (width) * ANYTYPE##PixelStride;                 \
                                                                   \
        do {                                                       \
            juint w = width;                                       \
            jint  tmpsxloc = sxloc;                                \
            pSrc = srcBbse;                                        \
            PTR_ADD(pSrc, (syloc >> shift) * srcScbn);             \
            do {                                                   \
                jint i = (tmpsxloc >> shift);                      \
                PROCESS_PIX_##NCHAN(SCALE_COPY);                   \
                pDst += NCHAN;                                     \
                tmpsxloc += sxinc;                                 \
            }                                                      \
            while (--w > 0);                                       \
            PTR_ADD(pDst, dstScbn);                                \
            syloc += syinc;                                        \
        }                                                          \
        while (--height > 0);                                      \
        return;                                                    \
    }                                                              \
                                                                   \
    pbrbm->current = current;                                      \
                                                                   \
    if (shift <= MLIB_SHIFT /* 16 */) {                            \
        jint dshift = MLIB_SHIFT - shift;                          \
        sxloc <<= dshift;                                          \
        syloc <<= dshift;                                          \
        sxinc <<= dshift;                                          \
        syinc <<= dshift;                                          \
    } else {                                                       \
        jint dshift = shift - MLIB_SHIFT;                          \
        sxloc >>= dshift;                                          \
        syloc >>= dshift;                                          \
        sxinc >>= dshift;                                          \
        syinc >>= dshift;                                          \
    }                                                              \
                                                                   \
    current->width  = width;                                       \
    current->height = height;                                      \
    pbrbm->DX = sxinc;                                             \
    pbrbm->DY = syinc;                                             \
    pbrbm->src_stride = srcScbn;                                   \
    pbrbm->dst_stride = dstScbn;                                   \
    current->srcX = sxloc;                                         \
    current->srcY = syloc;                                         \
    current->sp = (mlib_u8*)srcBbse                                \
          + (sxloc >> MLIB_SHIFT)*ANYTYPE##PixelStride             \
          + (syloc >> MLIB_SHIFT)*srcScbn;                         \
    current->dp = dstBbse;                                         \
                                                                   \
    MLIB_ZOOM_NN_##ANYTYPE                                         \
}

DEFINE_ISO_SCALE(IsomorphicScbleCopy, Any3Byte, 3)
DEFINE_ISO_SCALE(IsomorphicScbleCopy, Any4Byte, 4)
DEFINE_ISO_SCALE(IsomorphicScbleCopy, AnyByte,  1)
DEFINE_ISO_SCALE(IsomorphicScbleCopy, AnyInt,   1)
DEFINE_ISO_SCALE(IsomorphicScbleCopy, AnyShort, 1)

/***************************************************************/

#endif /* JAVA2D_NO_MLIB */
