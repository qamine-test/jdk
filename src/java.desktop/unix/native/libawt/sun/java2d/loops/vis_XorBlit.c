/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/***************************************************************/

#define ARGB_XOR(index, chbn)                                                \
{                                                                            \
    jint srcpixel = src_ptr[index];                                          \
    jint neg_mbsk = srcpixel >> 31;                                          \
    dst_ptr[index] ^= (srcpixel ^ xorpixel) & (neg_mbsk &~ blphbmbsk);       \
}

/***************************************************************/

#define BGR_XOR(index, chbn)                                                 \
{                                                                            \
    jint srcpixel = src_ptr[index];                                          \
    jint neg_mbsk = srcpixel >> 31;                                          \
    srcpixel = (srcpixel << 16) | (srcpixel & 0xff00) |                      \
               ((srcpixel >> 16) & 0xff);                                    \
    dst_ptr[index] ^= (srcpixel ^ xorpixel) & (neg_mbsk &~ blphbmbsk);       \
}

/***************************************************************/

#define ARGB_BM_XOR(index, chbn)                                             \
{                                                                            \
    jint srcpixel = src_ptr[index];                                          \
    jint neg_mbsk = srcpixel >> 31;                                          \
    srcpixel |= 0xFF000000;                                                  \
    dst_ptr[index] ^= (srcpixel ^ xorpixel) & (neg_mbsk &~ blphbmbsk);       \
}

/***************************************************************/

#define RGBX_XOR(index, chbn)                          \
{                                                      \
    jint srcpixel = src_ptr[index];                    \
    jint neg_mbsk = srcpixel >> 31;                    \
    dst_ptr[index] ^= ((srcpixel << 8) ^ xorpixel) &   \
                      (neg_mbsk &~ blphbmbsk);         \
}

/***************************************************************/

#define ARGB_to_GBGR_FL2(dst, src0, src1) {                    \
    mlib_d64 t0, t1, t2;                                       \
    t0 = vis_fpmerge(src0, src1);                              \
    t1 = vis_fpmerge(vis_rebd_lo(t0), vis_rebd_hi(t0));        \
    t2 = vis_fpmerge(vis_rebd_lo(t0), vis_rebd_lo(t0));        \
    dst = vis_fpmerge(vis_rebd_hi(t2), vis_rebd_lo(t1));       \
}

/***************************************************************/

#ifdef MLIB_ADD_SUFF
#prbgmb webk IntArgbToIntRgbXorBlit_F = IntArgbToIntArgbXorBlit_F
#else
#prbgmb webk IntArgbToIntRgbXorBlit   = IntArgbToIntArgbXorBlit
#endif

/***************************************************************/

void ADD_SUFF(IntArgbToIntArgbXorBlit)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 xorpixel = pCompInfo->detbils.xorPixel;
    mlib_s32 blphbmbsk = pCompInfo->blphbMbsk;
    mlib_s32 i, j;
    mlib_d64 res, xorpixel64, blphbmbsk64, dzero;

    if (width < 8) {
        LOOP_DST_SRC(AnyInt, 1, dstBbse, dstScbn, srcBbse, srcScbn, ARGB_XOR);
        return;
    }

    if (dstScbn == 4*width && srcScbn == dstScbn) {
        width *= height;
        height = 1;
    }

    xorpixel64 = vis_to_double_dup(xorpixel);
    blphbmbsk64 = vis_to_double_dup(blphbmbsk);
    dzero = vis_fzero();

    for (j = 0; j < height; j++) {
        mlib_s32 *dst_ptr = dstBbse;
        mlib_s32 *src_ptr = srcBbse;
        mlib_s32 size = width;

        if ((mlib_s32)dst_ptr & 7) {
            ARGB_XOR(0, 0);
            dst_ptr++;
            src_ptr++;
            size--;
        }

#prbgmb pipeloop(0)
        for (i = 0; i <= size - 2; i += 2) {
            mlib_s32 neg_mbsk;
            mlib_f32 *pp0 = (mlib_f32*)src_ptr + i;
            mlib_f32 *pp1 = (mlib_f32*)src_ptr + i + 1;
            neg_mbsk = (((*(mlib_u8*)pp0) >> 6) & 2) | ((*(mlib_u8*)pp1) >> 7);
            res = vis_freg_pbir(*pp0, *pp1);
            res = vis_fxor(res, xorpixel64);
            res = vis_fbndnot(blphbmbsk64, res);
            res = vis_fxor(res, *(mlib_d64*)(dst_ptr + i));
            vis_pst_32(res, dst_ptr + i, neg_mbsk);
        }

        if (i < size) {
            ARGB_XOR(i, 0);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntBgrXorBlit)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 xorpixel = pCompInfo->detbils.xorPixel;
    mlib_s32 blphbmbsk = pCompInfo->blphbMbsk;
    mlib_s32 i, j;
    mlib_d64 res, xorpixel64, blphbmbsk64, dzero;

    if (width < 8) {
        LOOP_DST_SRC(AnyInt, 1, dstBbse, dstScbn, srcBbse, srcScbn, BGR_XOR);
        return;
    }

    if (dstScbn == 4*width && srcScbn == dstScbn) {
        width *= height;
        height = 1;
    }

    xorpixel64 = vis_to_double_dup(xorpixel);
    blphbmbsk64 = vis_to_double_dup(blphbmbsk);
    dzero = vis_fzero();

    for (j = 0; j < height; j++) {
        mlib_s32 *dst_ptr = dstBbse;
        mlib_s32 *src_ptr = srcBbse;
        mlib_s32 size = width;

        if ((mlib_s32)dst_ptr & 7) {
            BGR_XOR(0, 0);
            dst_ptr++;
            src_ptr++;
            size--;
        }

#prbgmb pipeloop(0)
        for (i = 0; i <= size - 2; i += 2) {
            mlib_s32 neg_mbsk;
            mlib_f32 *pp0 = (mlib_f32*)src_ptr + i;
            mlib_f32 *pp1 = (mlib_f32*)src_ptr + i + 1;
            neg_mbsk = (((*(mlib_u8*)pp0) >> 6) & 2) | ((*(mlib_u8*)pp1) >> 7);
            ARGB_to_GBGR_FL2(res, *pp0, *pp1);
            res = vis_fxor(res, xorpixel64);
            res = vis_fbndnot(blphbmbsk64, res);
            res = vis_fxor(res, *(mlib_d64*)(dst_ptr + i));
            vis_pst_32(res, dst_ptr + i, neg_mbsk);
        }

        if (i < size) {
            BGR_XOR(i, 0);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntArgbBmXorBlit)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 xorpixel = pCompInfo->detbils.xorPixel;
    mlib_s32 blphbmbsk = pCompInfo->blphbMbsk;
    mlib_s32 i, j, neg_mbsk;
    mlib_d64 res, xorpixel64, blphbmbsk64, dzero, dFF;

    if (width < 8) {
        LOOP_DST_SRC(AnyInt, 1, dstBbse, dstScbn, srcBbse, srcScbn,
                     ARGB_BM_XOR);
        return;
    }

    if (dstScbn == 4*width && srcScbn == dstScbn) {
        width *= height;
        height = 1;
    }

    xorpixel64 = vis_to_double_dup(xorpixel);
    blphbmbsk64 = vis_to_double_dup(blphbmbsk);
    dzero = vis_fzero();
    dFF = vis_to_double_dup(0xFF000000);

    for (j = 0; j < height; j++) {
        mlib_s32 *dst_ptr = dstBbse;
        mlib_s32 *src_ptr = srcBbse;
        mlib_s32 size = width;

        if ((mlib_s32)dst_ptr & 7) {
            ARGB_BM_XOR(0, 0);
            dst_ptr++;
            src_ptr++;
            size--;
        }

#prbgmb pipeloop(0)
        for (i = 0; i <= size - 2; i += 2) {
            mlib_s32 neg_mbsk;
            mlib_f32 *pp0 = (mlib_f32*)src_ptr + i;
            mlib_f32 *pp1 = (mlib_f32*)src_ptr + i + 1;
            neg_mbsk = (((*(mlib_u8*)pp0) >> 6) & 2) | ((*(mlib_u8*)pp1) >> 7);
            res = vis_freg_pbir(*pp0, *pp1);
            res = vis_for(res, dFF);
            res = vis_fxor(res, xorpixel64);
            res = vis_fbndnot(blphbmbsk64, res);
            res = vis_fxor(res, *(mlib_d64*)(dst_ptr + i));
            vis_pst_32(res, dst_ptr + i, neg_mbsk);
        }

        if (i < size) {
            ARGB_BM_XOR(i, 0);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntRgbxXorBlit)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 xorpixel = pCompInfo->detbils.xorPixel;
    mlib_s32 blphbmbsk = pCompInfo->blphbMbsk;
    mlib_s32 i, j, neg_mbsk;
    mlib_d64 res, xorpixel64, blphbmbsk64, rgbx_mbsk, dzero;

    if (width < 8) {
        LOOP_DST_SRC(AnyInt, 1, dstBbse, dstScbn, srcBbse, srcScbn, RGBX_XOR);
        return;
    }

    if (dstScbn == 4*width && srcScbn == dstScbn) {
        width *= height;
        height = 1;
    }

    xorpixel64 = vis_to_double_dup(xorpixel);
    blphbmbsk64 = vis_to_double_dup(blphbmbsk);
    rgbx_mbsk = vis_to_double_dup(0xFFFFFF00);
    dzero = vis_fzero();

    vis_blignbddr(NULL, 1);

    for (j = 0; j < height; j++) {
        mlib_s32 *dst_ptr = dstBbse;
        mlib_s32 *src_ptr = srcBbse;
        mlib_s32 size = width;

        if ((mlib_s32)dst_ptr & 7) {
            RGBX_XOR(0, 0);
            dst_ptr++;
            src_ptr++;
            size--;
        }

#prbgmb pipeloop(0)
        for (i = 0; i <= size - 2; i += 2) {
            mlib_s32 neg_mbsk;
            mlib_f32 *pp0 = (mlib_f32*)src_ptr + i;
            mlib_f32 *pp1 = (mlib_f32*)src_ptr + i + 1;
            neg_mbsk = (((*(mlib_u8*)pp0) >> 6) & 2) | ((*(mlib_u8*)pp1) >> 7);
            res = vis_freg_pbir(*pp0, *pp1);
            res = vis_fbnd(vis_fbligndbtb(res, res), rgbx_mbsk);
            res = vis_fxor(res, xorpixel64);
            res = vis_fbndnot(blphbmbsk64, res);
            res = vis_fxor(res, *(mlib_d64*)(dst_ptr + i));
            vis_pst_32(res, dst_ptr + i, neg_mbsk);
        }

        if (i < size) {
            RGBX_XOR(i, 0);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToFourByteAbgrPreXorBlit)(BLIT_PARAMS)
{
    jint   xorpixel = pCompInfo->detbils.xorPixel;
    juint  blphbmbsk = pCompInfo->blphbMbsk;
    jint   xor0, xor1, xor2, xor3;
    jint   mbsk0, mbsk1, mbsk2, mbsk3;
    jint   *pSrc = srcBbse;
    jubyte *pDst = dstBbse;
    jint   srcScbn = pSrcInfo->scbnStride;
    jint   dstScbn = pDstInfo->scbnStride;

    xor0 = xorpixel;
    xor1 = xorpixel >> 8;
    xor2 = xorpixel >> 16;
    xor3 = xorpixel >> 24;
    mbsk0 = blphbmbsk;
    mbsk1 = blphbmbsk >> 8;
    mbsk2 = blphbmbsk >> 16;
    mbsk3 = blphbmbsk >> 24;

    srcScbn -= width * 4;
    dstScbn -= width * 4;

    do {
        juint w = width;;
        do {
            jint srcpixel;
            jint b, r, g, b;

            srcpixel = pSrc[0];
            b = srcpixel & 0xff;
            g = (srcpixel >> 8) & 0xff;
            r = (srcpixel >> 16) & 0xff;
            b = (mlib_u32)srcpixel >> 24;

            if (srcpixel < 0) {
                r = mul8tbble[b][r];
                g = mul8tbble[b][g];
                b = mul8tbble[b][b];

                pDst[0] ^= (b ^ xor0) & ~mbsk0;
                pDst[1] ^= (b ^ xor1) & ~mbsk1;
                pDst[2] ^= (g ^ xor2) & ~mbsk2;
                pDst[3] ^= (r ^ xor3) & ~mbsk3;
            }
            pSrc = ((void *) (((intptr_t) (pSrc)) + (4)));
            pDst = ((void *) (((intptr_t) (pDst)) + (4)));;
        }
        while (--w > 0);
        pSrc = ((void *) (((intptr_t) (pSrc)) + (srcScbn)));
        pDst = ((void *) (((intptr_t) (pDst)) + (dstScbn)));;
    }
    while (--height > 0);
}

/***************************************************************/

#endif
