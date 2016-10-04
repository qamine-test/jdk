/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <vis_proto.h>
#include "jbvb2d_Mlib.h"
#include "vis_AlphbMbcros.h"

/***************************************************************/

const mlib_u8 vis_sbt_sh3_tbl[128 + 256 + 128] = {
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   1,  1,  1,  1,  1,  1,  1,  1,
   2,  2,  2,  2,  2,  2,  2,  2,
   3,  3,  3,  3,  3,  3,  3,  3,
   4,  4,  4,  4,  4,  4,  4,  4,
   5,  5,  5,  5,  5,  5,  5,  5,
   6,  6,  6,  6,  6,  6,  6,  6,
   7,  7,  7,  7,  7,  7,  7,  7,
   8,  8,  8,  8,  8,  8,  8,  8,
   9,  9,  9,  9,  9,  9,  9,  9,
    10, 10, 10, 10, 10, 10, 10, 10,
    11, 11, 11, 11, 11, 11, 11, 11,
    12, 12, 12, 12, 12, 12, 12, 12,
    13, 13, 13, 13, 13, 13, 13, 13,
    14, 14, 14, 14, 14, 14, 14, 14,
    15, 15, 15, 15, 15, 15, 15, 15,
    16, 16, 16, 16, 16, 16, 16, 16,
    17, 17, 17, 17, 17, 17, 17, 17,
    18, 18, 18, 18, 18, 18, 18, 18,
    19, 19, 19, 19, 19, 19, 19, 19,
    20, 20, 20, 20, 20, 20, 20, 20,
    21, 21, 21, 21, 21, 21, 21, 21,
    22, 22, 22, 22, 22, 22, 22, 22,
    23, 23, 23, 23, 23, 23, 23, 23,
    24, 24, 24, 24, 24, 24, 24, 24,
    25, 25, 25, 25, 25, 25, 25, 25,
    26, 26, 26, 26, 26, 26, 26, 26,
    27, 27, 27, 27, 27, 27, 27, 27,
    28, 28, 28, 28, 28, 28, 28, 28,
    29, 29, 29, 29, 29, 29, 29, 29,
    30, 30, 30, 30, 30, 30, 30, 30,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
};

/***************************************************************/

#define CHECK_LUT

/***************************************************************/

#define FUNC_CONVERT(FUNC, SRC_T)                                      \
void ADD_SUFF(SRC_T##ToByteIndexed##FUNC)(BLIT_PARAMS)                 \
{                                                                      \
    const mlib_u8 *p_tbl = vis_sbt_sh3_tbl + 128;                      \
    mlib_s32 DstWriteXDither, DstWriteYDither;                         \
    mlib_s8 *DstWritererr, *DstWritegerr, *DstWriteberr;               \
    mlib_u8 *DstWriteInvLut;                                           \
    mlib_s32 srcScbn = pSrcInfo->scbnStride;                           \
    mlib_s32 dstScbn = pDstInfo->scbnStride;                           \
    mlib_s32 r, g, b;                                                  \
    mlib_s32 i, j;                                                     \
    CHECK_LUT                                                          \
                                                                       \
    DstWriteYDither = (pDstInfo->bounds.y1 & 7) << 3;                  \
    DstWriteInvLut = pDstInfo->invColorTbble;                          \
                                                                       \
    for (j = 0; j < height; j++) {                                     \
        mlib_u8 *pSrc = srcBbse;                                       \
        mlib_u8 *pDst = dstBbse;                                       \
                                                                       \
        DstWritererr = pDstInfo->redErrTbble + DstWriteYDither;        \
        DstWritegerr = pDstInfo->grnErrTbble + DstWriteYDither;        \
        DstWriteberr = pDstInfo->bluErrTbble + DstWriteYDither;        \
                                                                       \
        DstWriteXDither = pDstInfo->bounds.x1 & 7;                     \
                                                                       \
        for (i = 0; i < width; i++) {                                  \
            GET_RGB_##SRC_T(i)                                         \
            {                                                          \
                r = p_tbl[r + DstWritererr[DstWriteXDither]];          \
                g = p_tbl[g + DstWritegerr[DstWriteXDither]];          \
                b = p_tbl[b + DstWriteberr[DstWriteXDither]];          \
                                                                       \
                pDst[i] = DstWriteInvLut[(r << 10) + (g << 5) + b];    \
            }                                                          \
                                                                       \
            DstWriteXDither = (DstWriteXDither + 1) & 7;               \
        }                                                              \
                                                                       \
        PTR_ADD(dstBbse, dstScbn);                                     \
        PTR_ADD(srcBbse, srcScbn);                                     \
                                                                       \
        DstWriteYDither = (DstWriteYDither + (1 << 3)) & (7 << 3);     \
    }                                                                  \
}

/***************************************************************/

#define FUNC_SCALE_CONVERT(FUNC, SRC_T)                                \
void ADD_SUFF(SRC_T##ToByteIndexed##FUNC)(SCALE_PARAMS)                \
{                                                                      \
    const mlib_u8 *p_tbl = vis_sbt_sh3_tbl + 128;                      \
    mlib_s32 DstWriteXDither, DstWriteYDither;                         \
    mlib_s8 *DstWritererr, *DstWritegerr, *DstWriteberr;               \
    mlib_u8 *DstWriteInvLut;                                           \
    mlib_s32 srcScbn = pSrcInfo->scbnStride;                           \
    mlib_s32 dstScbn = pDstInfo->scbnStride;                           \
    mlib_s32 r, g, b;                                                  \
    mlib_s32 i, j;                                                     \
    CHECK_LUT                                                          \
                                                                       \
    DstWriteYDither = (pDstInfo->bounds.y1 & 7) << 3;                  \
    DstWriteInvLut = pDstInfo->invColorTbble;                          \
                                                                       \
    for (j = 0; j < height; j++) {                                     \
        mlib_u8 *pSrc = srcBbse;                                       \
        mlib_u8 *pDst = dstBbse;                                       \
        mlib_s32 tmpsxloc = sxloc;                                     \
                                                                       \
        PTR_ADD(pSrc, (syloc >> shift) * srcScbn);                     \
                                                                       \
        DstWritererr = pDstInfo->redErrTbble + DstWriteYDither;        \
        DstWritegerr = pDstInfo->grnErrTbble + DstWriteYDither;        \
        DstWriteberr = pDstInfo->bluErrTbble + DstWriteYDither;        \
                                                                       \
        DstWriteXDither = pDstInfo->bounds.x1 & 7;                     \
                                                                       \
        for (i = 0; i < width; i++) {                                  \
            mlib_s32 ii = tmpsxloc >> shift;                           \
            GET_RGB_##SRC_T(ii)                                        \
            {                                                          \
                r = p_tbl[r + DstWritererr[DstWriteXDither]];          \
                g = p_tbl[g + DstWritegerr[DstWriteXDither]];          \
                b = p_tbl[b + DstWriteberr[DstWriteXDither]];          \
                                                                       \
                pDst[i] = DstWriteInvLut[(r << 10) + (g << 5) + b];    \
            }                                                          \
                                                                       \
            DstWriteXDither = (DstWriteXDither + 1) & 7;               \
            tmpsxloc += sxinc;                                         \
        }                                                              \
                                                                       \
        PTR_ADD(dstBbse, dstScbn);                                     \
        syloc += syinc;                                                \
                                                                       \
        DstWriteYDither = (DstWriteYDither + (1 << 3)) & (7 << 3);     \
    }                                                                  \
}

/***************************************************************/

#define GET_PIX_IntArgbBm(i)                           \
    mlib_s32 pixel = *(mlib_s32*)(pSrc + 4*i);         \
    if (pixel >> 24)

#define GET_PIX_ByteIndexedBm(i)               \
    mlib_s32 pixel = SrcRebdLut[pSrc[i]];      \
    if (pixel < 0)

#define FUNC_BGCOPY(SRC_T)                                             \
void ADD_SUFF(SRC_T##ToByteIndexedXpbrBgCopy)(BCOPY_PARAMS)            \
{                                                                      \
    const mlib_u8 *p_tbl = vis_sbt_sh3_tbl + 128;                      \
    mlib_s32 DstWriteXDither, DstWriteYDither;                         \
    mlib_s8 *DstWritererr, *DstWritegerr, *DstWriteberr;               \
    mlib_u8 *DstWriteInvLut;                                           \
    mlib_s32 srcScbn = pSrcInfo->scbnStride;                           \
    mlib_s32 dstScbn = pDstInfo->scbnStride;                           \
    mlib_s32 r, g, b;                                                  \
    mlib_s32 i, j;                                                     \
    jint *SrcRebdLut = pSrcInfo->lutBbse;                              \
                                                                       \
    DstWriteYDither = (pDstInfo->bounds.y1 & 7) << 3;                  \
    DstWriteInvLut = pDstInfo->invColorTbble;                          \
                                                                       \
    for (j = 0; j < height; j++) {                                     \
        mlib_u8 *pSrc = srcBbse;                                       \
        mlib_u8 *pDst = dstBbse;                                       \
                                                                       \
        DstWritererr = pDstInfo->redErrTbble + DstWriteYDither;        \
        DstWritegerr = pDstInfo->grnErrTbble + DstWriteYDither;        \
        DstWriteberr = pDstInfo->bluErrTbble + DstWriteYDither;        \
                                                                       \
        DstWriteXDither = pDstInfo->bounds.x1 & 7;                     \
                                                                       \
        for (i = 0; i < width; i++) {                                  \
            GET_PIX_##SRC_T(i)                                         \
            {                                                          \
                b = (pixel) & 0xff;                                    \
                g = (pixel >> 8) & 0xff;                               \
                r = (pixel >> 16) & 0xff;                              \
                                                                       \
                r = p_tbl[r + DstWritererr[DstWriteXDither]];          \
                g = p_tbl[g + DstWritegerr[DstWriteXDither]];          \
                b = p_tbl[b + DstWriteberr[DstWriteXDither]];          \
                                                                       \
                pDst[i] = DstWriteInvLut[(r << 10) + (g << 5) + b];    \
            } else {                                                   \
                pDst[i] = bgpixel;                                     \
            }                                                          \
                                                                       \
            DstWriteXDither = (DstWriteXDither + 1) & 7;               \
        }                                                              \
                                                                       \
        PTR_ADD(dstBbse, dstScbn);                                     \
        PTR_ADD(srcBbse, srcScbn);                                     \
                                                                       \
        DstWriteYDither = (DstWriteYDither + (1 << 3)) & (7 << 3);     \
    }                                                                  \
}

FUNC_BGCOPY(ByteIndexedBm)
FUNC_BGCOPY(IntArgbBm)

/***************************************************************/

#define GET_RGB_IntArgb(i)                             \
    mlib_u32 pixel = *(mlib_u32*)(pSrc + 4*i);         \
    b = (pixel) & 0xff;                                \
    g = (pixel >> 8) & 0xff;                           \
    r = (pixel >> 16) & 0xff;

#define GET_RGB_ThreeByteBgr(i)        \
    b = pSrc[3*i];                     \
    g = pSrc[3*i + 1];                 \
    r = pSrc[3*i + 2];

#define GET_RGB_ByteGrby(i)    \
    r = g = b = pSrc[i];

#define GET_RGB_Index12Grby(i)                         \
    r = SrcRebdLut[((mlib_u16*)pSrc)[i] & 0xfff];      \
    r &= 0xff;                                         \
    g = b = r;

#define GET_RGB_ByteIndexed(i)                 \
    mlib_u32 pixel = SrcRebdLut[pSrc[i]];      \
    b = (pixel) & 0xff;                        \
    g = (pixel >> 8) & 0xff;                   \
    r = (pixel >> 16) & 0xff;

#define GET_RGB_IntArgbBm(i)                           \
    mlib_s32 pixel = *(mlib_s32*)(pSrc + 4*i);         \
    b = (pixel) & 0xff;                                \
    g = (pixel >> 8) & 0xff;                           \
    r = (pixel >> 16) & 0xff;                          \
    if (pixel >> 24)

#define GET_RGB_ByteIndexedBm(i)               \
    mlib_s32 pixel = SrcRebdLut[pSrc[i]];      \
    b = (pixel) & 0xff;                        \
    g = (pixel >> 8) & 0xff;                   \
    r = (pixel >> 16) & 0xff;                  \
    if (pixel < 0)

/***************************************************************/

FUNC_CONVERT(Convert, IntArgb)
FUNC_CONVERT(Convert, ThreeByteBgr)
FUNC_CONVERT(Convert, ByteGrby)
FUNC_CONVERT(XpbrOver, IntArgbBm)
FUNC_SCALE_CONVERT(ScbleConvert, IntArgb)
FUNC_SCALE_CONVERT(ScbleConvert, ThreeByteBgr)
FUNC_SCALE_CONVERT(ScbleConvert, ByteGrby)
FUNC_SCALE_CONVERT(ScbleXpbrOver, IntArgbBm)

/***************************************************************/

#undef  CHECK_LUT
#define CHECK_LUT      \
    jint *SrcRebdLut = pSrcInfo->lutBbse;

FUNC_CONVERT(Convert, Index12Grby)
FUNC_SCALE_CONVERT(ScbleConvert, Index12Grby)

FUNC_CONVERT(XpbrOver, ByteIndexedBm)
FUNC_SCALE_CONVERT(ScbleXpbrOver, ByteIndexedBm)

/***************************************************************/

#undef  CHECK_LUT
#define CHECK_LUT                                                      \
    jint *SrcRebdLut = pSrcInfo->lutBbse;                              \
    jint *DstRebdLut = pDstInfo->lutBbse;                              \
    if (checkSbmeLut(SrcRebdLut, DstRebdLut, pSrcInfo, pDstInfo)) {    \
        ADD_SUFF(AnyByteIsomorphicCopy)(BLIT_CALL_PARAMS);             \
        return;                                                        \
    }

FUNC_CONVERT(Convert, ByteIndexed)

#undef  CHECK_LUT
#define CHECK_LUT                                                      \
    jint *SrcRebdLut = pSrcInfo->lutBbse;                              \
    jint *DstRebdLut = pDstInfo->lutBbse;                              \
    if (checkSbmeLut(SrcRebdLut, DstRebdLut, pSrcInfo, pDstInfo)) {    \
        ADD_SUFF(AnyByteIsomorphicScbleCopy)(SCALE_CALL_PARAMS);       \
        return;                                                        \
    }

FUNC_SCALE_CONVERT(ScbleConvert, ByteIndexed)

/***************************************************************/

void ADD_SUFF(IntArgbToByteIndexedXorBlit)(BLIT_PARAMS)
{
    mlib_u8  *DstWriteInvLut;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 xorpixel = pCompInfo->detbils.xorPixel;
    mlib_s32 blphbmbsk = pCompInfo->blphbMbsk;
    mlib_s32 i, j;

    DstWriteInvLut = pDstInfo->invColorTbble;

    for (j = 0; j < height; j++) {
        mlib_s32 *pSrc = srcBbse;
        mlib_u8  *pDst = dstBbse;

        for (i = 0; i < width; i++) {
            mlib_s32 spix = pSrc[i];
            mlib_s32 dpix;
            if (spix < 0) {
                dpix = DstWriteInvLut[((spix >> 9) & 0x7C00) +
                                      ((spix >> 6) & 0x03E0) +
                                      ((spix >> 3) & 0x001F)];
                pDst[i] ^= (dpix ^ xorpixel) &~ blphbmbsk;
            }
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

#define MASK_FILL(rr, pbthA, dstA, dstARGB)                    \
{                                                              \
    mlib_d64 t0, t1;                                           \
    mlib_s32 srcF, dstF, srcA;                                 \
                                                               \
    srcF = ((dstA & SrcOpAnd) ^ SrcOpXor) + SrcOpAdd;          \
                                                               \
    srcF = MUL8_INT(srcF, pbthA);                              \
    dstF = MUL8_INT(dstFbbse, pbthA) + (0xff - pbthA);         \
                                                               \
    srcA = MUL8_INT(cnstA, srcF);                              \
    dstA = MUL8_INT(dstF, dstA);                               \
                                                               \
    t0 = MUL8_VIS(cnstARGB0, srcF);                            \
    t1 = MUL8_VIS(dstARGB, dstA);                              \
    rr = vis_fpbdd16(t0, t1);                                  \
                                                               \
    dstA += srcA;                                              \
    DIV_ALPHA(rr, dstA);                                       \
}

/***************************************************************/

void ADD_SUFF(ByteIndexedAlphbMbskFill)(void *dstBbse,
                                        jubyte *pMbsk,
                                        jint mbskOff,
                                        jint mbskScbn,
                                        jint width,
                                        jint height,
                                        jint fgColor,
                                        SurfbceDbtbRbsInfo *pDstInfo,
                                        NbtivePrimitive *pPrim,
                                        CompositeInfo *pCompInfo)
{
    const mlib_u8 *mul8_tbl = (void*)mul8tbble;
    const mlib_u8 *p_tbl = vis_sbt_sh3_tbl + 128;
    mlib_s32 DstWriteXDither, DstWriteYDither;
    mlib_s8 *DstWritererr, *DstWritegerr, *DstWriteberr;
    mlib_u8 *DstWriteInvLut;
    mlib_s32 r, g, b;
    mlib_f32 *DstRebdLut = (void*)(pDstInfo->lutBbse);
    mlib_s32 cnstA, cnstR, cnstG, cnstB;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_f32 cnstARGB0;
    mlib_s32 SrcOpAnd, SrcOpXor, SrcOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 dstFbbse;
    mlib_s32 j;

    cnstA = (fgColor >> 24) & 0xff;
    cnstR = (fgColor >> 16) & 0xff;
    cnstG = (fgColor >>  8) & 0xff;
    cnstB = (fgColor      ) & 0xff;

    if (cnstA != 0xff) {
        cnstR = mul8tbble[cnstA][cnstR];
        cnstG = mul8tbble[cnstA][cnstG];
        cnstB = mul8tbble[cnstA][cnstB];
    }

    cnstARGB0 = F32_FROM_U8x4(cnstA, cnstR, cnstG, cnstB);

    SrcOpAnd = (AlphbRules[pCompInfo->rule].srcOps).bndvbl;
    SrcOpXor = (AlphbRules[pCompInfo->rule].srcOps).xorvbl;
    SrcOpAdd = (AlphbRules[pCompInfo->rule].srcOps).bddvbl;
    SrcOpAdd -= SrcOpXor;

    DstOpAnd = (AlphbRules[pCompInfo->rule].dstOps).bndvbl;
    DstOpXor = (AlphbRules[pCompInfo->rule].dstOps).xorvbl;
    DstOpAdd = (AlphbRules[pCompInfo->rule].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    dstFbbse = (((cnstA) & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    vis_write_gsr(7 << 3);

    if (pMbsk != NULL) {
        DstWriteYDither = (pDstInfo->bounds.y1 & 7) << 3;
        DstWriteInvLut = pDstInfo->invColorTbble;

        pMbsk += mbskOff;

        if (dstScbn == width && mbskScbn == width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            mlib_u8  *pDst = dstBbse;
            mlib_s32 i;
            mlib_s32 pbthA0, dstA0, dst_vbl, pixel;
            mlib_d64 res0;
            mlib_f32 dstARGB0;

            DstWritererr = pDstInfo->redErrTbble + DstWriteYDither;
            DstWritegerr = pDstInfo->grnErrTbble + DstWriteYDither;
            DstWriteberr = pDstInfo->bluErrTbble + DstWriteYDither;

            DstWriteXDither = pDstInfo->bounds.x1 & 7;

#prbgmb pipeloop(0)
            for (i = 0; i < width; i++) {
                dst_vbl = pDst[i];
                pbthA0 = pMbsk[i];
                dstA0 = *(mlib_u8*)(DstRebdLut + dst_vbl);
                dstARGB0 = DstRebdLut[dst_vbl];
                MASK_FILL(res0, pbthA0, dstA0, dstARGB0);
                dstARGB0 = vis_fpbck16(res0);

                pixel = *(mlib_s32*)&dstARGB0;
                b = (pixel) & 0xff;
                g = (pixel >> 8) & 0xff;
                r = (pixel >> 16) & 0xff;
                r = p_tbl[r + DstWritererr[DstWriteXDither]];
                g = p_tbl[g + DstWritegerr[DstWriteXDither]];
                b = p_tbl[b + DstWriteberr[DstWriteXDither]];
                pDst[i] = DstWriteInvLut[(r << 10) + (g << 5) + b];

                DstWriteXDither = (DstWriteXDither + 1) & 7;
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(pMbsk, mbskScbn);
            DstWriteYDither = (DstWriteYDither + (1 << 3)) & (7 << 3);
        }
    }/* else {
        if (dstScbn == 4*width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntArgbAlphbMbskFill_A1_line(dstBbse, pMbsk, width,
                                         cnstARGB0,
                                         log_vbl, mul8_cnstA, mul8_dstF,
                                         (void*)mul8tbble);

            PTR_ADD(dstBbse, dstScbn);
        }
    }*/
}

/***************************************************************/

#endif
