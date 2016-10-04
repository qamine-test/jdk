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

#include "vis_AlphbMbcros.h"

/***************************************************************/

/* ##############################################################
 * IntArgbAlphbMbskFill()
 * FourByteAbgrAlphbMbskFill()
 */

#define MASK_FILL(rr, pbthA, dstA, dstARGB)                    \
{                                                              \
    mlib_d64 t0, t1;                                           \
    mlib_s32 srcF, dstF, srcA;                                 \
                                                               \
    srcF = ((dstA & SrcOpAnd) ^ SrcOpXor) + SrcOpAdd;          \
                                                               \
    srcF = MUL8_INT(srcF, pbthA);                              \
    dstF = mul8_dstF[pbthA] + (0xff - pbthA);                  \
                                                               \
    srcA = mul8_cnstA[srcF];                                   \
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

stbtic void IntArgbAlphbMbskFill_line(mlib_f32 *dst_ptr,
                                      mlib_u8  *pMbsk,
                                      mlib_s32 width,
                                      mlib_f32 cnstARGB0,
                                      mlib_s32 *log_vbl,
                                      mlib_u8  *mul8_cnstA,
                                      mlib_u8  *mul8_dstF,
                                      mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbthA0, pbthA1, dstA0, dstA1, msk;
    mlib_d64 res0, res1, dstARGB;
    mlib_f32 dstARGB0;
    mlib_s32 SrcOpAnd, SrcOpXor, SrcOpAdd;

    SrcOpAnd = log_vbl[0];
    SrcOpXor = log_vbl[1];
    SrcOpAdd = log_vbl[2];

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbthA0 = pMbsk[i];

        if (pbthA0) {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            dstARGB0 = dst_ptr[i];
            MASK_FILL(res0, pbthA0, dstA0, dstARGB0);
            dst_ptr[i] = vis_fpbck16(res0);
            *(mlib_u8*)(dst_ptr + i) = dstA0;
        }

        i0 = 1;
    }

#prbgmb pipeloop(0)
    for (i = i0; i <= width - 2; i += 2) {
        pbthA0 = pMbsk[i];
        pbthA1 = pMbsk[i + 1];
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        dstA1 = *(mlib_u8*)(dst_ptr + i + 1);
        dstARGB = *(mlib_d64*)(dst_ptr + i);

        MASK_FILL(res0, pbthA0, dstA0, vis_rebd_hi(dstARGB));
        MASK_FILL(res1, pbthA1, dstA1, vis_rebd_lo(dstARGB));

        res0 = vis_fpbck16_pbir(res0, res1);

        msk = (((-pbthA0) & (1 << 11)) | ((-pbthA1) & (1 << 10))) >> 10;
        vis_pst_32(res0, dst_ptr + i, msk);

        *(mlib_u8*)(dst_ptr + i    ) = dstA0;
        *(mlib_u8*)(dst_ptr + i + 1) = dstA1;
    }

    if (i < width) {
        pbthA0 = pMbsk[i];

        if (pbthA0) {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            dstARGB0 = dst_ptr[i];
            MASK_FILL(res0, pbthA0, dstA0, dstARGB0);
            dst_ptr[i] = vis_fpbck16(res0);
            *(mlib_u8*)(dst_ptr + i) = dstA0;
        }
    }
}

/***************************************************************/

#undef  MASK_FILL
#define MASK_FILL(rr, pbthA, dstA, dstARGB)                    \
{                                                              \
    mlib_d64 t0, t1;                                           \
    mlib_s32 srcA, blp1;                                       \
                                                               \
    srcA = ((dstA & SrcOpAnd) ^ SrcOpXor) + SrcOpAdd;          \
    blp1 = mul8_dstF[dstA];                                    \
    dstA = mul8_cnstA[srcA] + blp1;                            \
                                                               \
    t0 = MUL8_VIS(cnstARGB0, srcA);                            \
    t1 = MUL8_VIS(dstARGB, blp1);                              \
    rr = vis_fpbdd16(t0, t1);                                  \
                                                               \
    DIV_ALPHA(rr, dstA);                                       \
}

/***************************************************************/

stbtic void IntArgbAlphbMbskFill_A1_line(mlib_f32 *dst_ptr,
                                         mlib_u8  *pMbsk,
                                         mlib_s32 width,
                                         mlib_f32 cnstARGB0,
                                         mlib_s32 *log_vbl,
                                         mlib_u8  *mul8_cnstA,
                                         mlib_u8  *mul8_dstF,
                                         mlib_u8  *mul8_tbl)
{
    mlib_s32 i;
    mlib_s32 dstA0;
    mlib_d64 res0;
    mlib_f32 dstARGB0;
    mlib_s32 SrcOpAnd, SrcOpXor, SrcOpAdd;

    SrcOpAnd = log_vbl[0];
    SrcOpXor = log_vbl[1];
    SrcOpAdd = log_vbl[2];

#prbgmb pipeloop(0)
    for (i = 0; i < width; i++) {
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        dstARGB0 = dst_ptr[i];
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0);
        dst_ptr[i] = vis_fpbck16(res0);
        *(mlib_u8*)(dst_ptr + i) = dstA0;
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbAlphbMbskFill)(void *rbsBbse,
                                    jubyte *pMbsk,
                                    jint mbskOff,
                                    jint mbskScbn,
                                    jint width,
                                    jint height,
                                    jint fgColor,
                                    SurfbceDbtbRbsInfo *pRbsInfo,
                                    NbtivePrimitive *pPrim,
                                    CompositeInfo *pCompInfo)
{
    mlib_s32 cnstA, cnstR, cnstG, cnstB;
    mlib_s32 rbsScbn = pRbsInfo->scbnStride;
    mlib_f32 cnstARGB0;
    mlib_u8  *mul8_cnstA, *mul8_dstF;
    mlib_s32 SrcOpAnd, SrcOpXor, SrcOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 dstF;
    mlib_s32 log_vbl[3];
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

    log_vbl[0] = SrcOpAnd;
    log_vbl[1] = SrcOpXor;
    log_vbl[2] = SrcOpAdd;

    DstOpAnd = (AlphbRules[pCompInfo->rule].dstOps).bndvbl;
    DstOpXor = (AlphbRules[pCompInfo->rule].dstOps).xorvbl;
    DstOpAdd = (AlphbRules[pCompInfo->rule].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    dstF = (((cnstA) & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    mul8_cnstA = mul8tbble[cnstA];
    mul8_dstF = mul8tbble[dstF];

    vis_write_gsr(7 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (rbsScbn == 4*width && mbskScbn == width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntArgbAlphbMbskFill_line(rbsBbse, pMbsk, width, cnstARGB0,
                                      log_vbl, mul8_cnstA, mul8_dstF,
                                      (void*)mul8tbble);

            PTR_ADD(rbsBbse, rbsScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        if (rbsScbn == 4*width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntArgbAlphbMbskFill_A1_line(rbsBbse, pMbsk, width, cnstARGB0,
                                         log_vbl, mul8_cnstA, mul8_dstF,
                                         (void*)mul8tbble);

            PTR_ADD(rbsBbse, rbsScbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(FourByteAbgrAlphbMbskFill)(void *rbsBbse,
                                         jubyte *pMbsk,
                                         jint mbskOff,
                                         jint mbskScbn,
                                         jint width,
                                         jint height,
                                         jint fgColor,
                                         SurfbceDbtbRbsInfo *pRbsInfo,
                                         NbtivePrimitive *pPrim,
                                         CompositeInfo *pCompInfo)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *pbuff = buff;
    mlib_s32 cnstA, cnstR, cnstG, cnstB;
    mlib_s32 rbsScbn = pRbsInfo->scbnStride;
    mlib_f32 cnstARGB0;
    mlib_u8  *mul8_cnstA, *mul8_dstF;
    mlib_s32 SrcOpAnd, SrcOpXor, SrcOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 dstF;
    mlib_s32 log_vbl[3];
    mlib_s32 j;

    cnstA = (mlib_u32)fgColor >> 24;
    cnstR = (fgColor >> 16) & 0xff;
    cnstG = (fgColor >>  8) & 0xff;
    cnstB = (fgColor      ) & 0xff;

    if (cnstA != 0xff) {
        cnstR = mul8tbble[cnstA][cnstR];
        cnstG = mul8tbble[cnstA][cnstG];
        cnstB = mul8tbble[cnstA][cnstB];
    }

    cnstARGB0 = F32_FROM_U8x4(cnstA, cnstB, cnstG, cnstR);

    SrcOpAnd = (AlphbRules[pCompInfo->rule].srcOps).bndvbl;
    SrcOpXor = (AlphbRules[pCompInfo->rule].srcOps).xorvbl;
    SrcOpAdd = (AlphbRules[pCompInfo->rule].srcOps).bddvbl;
    SrcOpAdd -= SrcOpXor;

    log_vbl[0] = SrcOpAnd;
    log_vbl[1] = SrcOpXor;
    log_vbl[2] = SrcOpAdd;

    DstOpAnd = (AlphbRules[pCompInfo->rule].dstOps).bndvbl;
    DstOpXor = (AlphbRules[pCompInfo->rule].dstOps).xorvbl;
    DstOpAdd = (AlphbRules[pCompInfo->rule].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    dstF = (((cnstA) & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    mul8_cnstA = mul8tbble[cnstA];
    mul8_dstF = mul8tbble[dstF];

    vis_write_gsr(7 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (((mlib_s32)rbsBbse | rbsScbn) & 3) {
            if (width > BUFF_SIZE) pbuff = mlib_mblloc(width*sizeof(mlib_s32));
        } else {
            if (rbsScbn == 4*width && mbskScbn == width) {
                width *= height;
                height = 1;
            }
        }

        for (j = 0; j < height; j++) {
            if (!((mlib_s32)rbsBbse & 3)) {
                IntArgbAlphbMbskFill_line(rbsBbse, pMbsk, width, cnstARGB0,
                                          log_vbl, mul8_cnstA, mul8_dstF,
                                          (void*)mul8tbble);
            } else {
                mlib_ImbgeCopy_nb(rbsBbse, pbuff, width*sizeof(mlib_s32));
                IntArgbAlphbMbskFill_line(pbuff, pMbsk, width, cnstARGB0,
                                          log_vbl, mul8_cnstA, mul8_dstF,
                                          (void*)mul8tbble);
                mlib_ImbgeCopy_nb(pbuff, rbsBbse, width*sizeof(mlib_s32));
            }

            PTR_ADD(rbsBbse, rbsScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        if (((mlib_s32)rbsBbse | rbsScbn) & 3) {
            if (width > BUFF_SIZE) pbuff = mlib_mblloc(width*sizeof(mlib_s32));
        } else {
            if (rbsScbn == 4*width) {
                width *= height;
                height = 1;
            }
        }

        for (j = 0; j < height; j++) {
            if (!((mlib_s32)rbsBbse & 3)) {
                IntArgbAlphbMbskFill_A1_line(rbsBbse, pMbsk, width, cnstARGB0,
                                             log_vbl, mul8_cnstA, mul8_dstF,
                                             (void*)mul8tbble);
            } else {
                mlib_ImbgeCopy_nb(rbsBbse, pbuff, width*sizeof(mlib_s32));
                IntArgbAlphbMbskFill_A1_line(pbuff, pMbsk, width, cnstARGB0,
                                             log_vbl, mul8_cnstA, mul8_dstF,
                                             (void*)mul8tbble);
                mlib_ImbgeCopy_nb(pbuff, rbsBbse, width*sizeof(mlib_s32));
            }

            PTR_ADD(rbsBbse, rbsScbn);
        }
    }

    if (pbuff != buff) {
        mlib_free(pbuff);
    }
}

/***************************************************************/

/* ##############################################################
 * IntRgbAlphbMbskFill()
 * IntBgrAlphbMbskFill()
 */

#undef  MASK_FILL
#define MASK_FILL(rr, pbthA, dstA, dstARGB)                    \
{                                                              \
    mlib_d64 t0, t1;                                           \
    mlib_s32 srcF, srcA;                                       \
                                                               \
    srcF = mul8_srcF[pbthA];                                   \
    srcA = mul8_cnstA[srcF];                                   \
    dstA = mul8_dstF[pbthA] + (0xff - pbthA);                  \
                                                               \
    t0 = MUL8_VIS(cnstARGB0, srcF);                            \
    t1 = MUL8_VIS(dstARGB, dstA);                              \
    rr = vis_fpbdd16(t0, t1);                                  \
                                                               \
    dstA += srcA;                                              \
    DIV_ALPHA_RGB(rr, dstA);                                   \
                                                               \
    pbthA = dstA - 0xff - srcF;                                \
    /* (pbthA == 0) if (dstA == 0xFF && srcF == 0) */          \
}

/***************************************************************/

stbtic void IntRgbAlphbMbskFill_line(mlib_f32 *dst_ptr,
                                     mlib_u8  *pMbsk,
                                     mlib_s32 width,
                                     mlib_f32 cnstARGB0,
                                     mlib_u8  *mul8_cnstA,
                                     mlib_u8  *mul8_dstF,
                                     mlib_u8  *mul8_srcF,
                                     mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbthA0, pbthA1, dstA0, dstA1, msk;
    mlib_d64 res0, res1, dstARGB;
    mlib_f32 dstARGB0;

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbthA0 = pMbsk[i];

        dstARGB0 = dst_ptr[i];
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0);
        if (pbthA0) {
            dst_ptr[i] = vis_fpbck16(res0);
        }

        i0 = 1;
    }

#prbgmb pipeloop(0)
    for (i = i0; i <= width - 2; i += 2) {
        pbthA0 = pMbsk[i];
        pbthA1 = pMbsk[i + 1];
        dstARGB = *(mlib_d64*)(dst_ptr + i);

        MASK_FILL(res0, pbthA0, dstA0, vis_rebd_hi(dstARGB));
        MASK_FILL(res1, pbthA1, dstA1, vis_rebd_lo(dstARGB));

        res0 = vis_fpbck16_pbir(res0, res1);

        msk = (((pbthA0) & (1 << 11)) | ((pbthA1) & (1 << 10))) >> 10;
        vis_pst_32(res0, dst_ptr + i, msk);
    }

    if (i < width) {
        pbthA0 = pMbsk[i];

        dstARGB0 = dst_ptr[i];
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0);
        if (pbthA0) {
            dst_ptr[i] = vis_fpbck16(res0);
        }
    }
}

/***************************************************************/

#undef  MASK_FILL
#define MASK_FILL(rr, pbthA, _dstA_, dstARGB)          \
{                                                      \
    rr = MUL8_VIS(dstARGB, dstF);                      \
    rr = vis_fpbdd16(rr, cnstARGB);                    \
                                                       \
    DIV_ALPHA_RGB(rr, dstA);                           \
}

/***************************************************************/

stbtic void IntRgbAlphbMbskFill_A1_line(mlib_f32 *dst_ptr,
                                         mlib_u8  *pMbsk,
                                         mlib_s32 width,
                                         mlib_d64 cnstARGB,
                                         mlib_s32 dstF,
                                         mlib_s32 dstA)
{
    mlib_s32 i;
    mlib_d64 res0;
    mlib_f32 dstARGB0;

#prbgmb pipeloop(0)
    for (i = 0; i < width; i++) {
        dstARGB0 = dst_ptr[i];
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0);
        dst_ptr[i] = vis_fpbck16(res0);
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbAlphbMbskFill)(void *rbsBbse,
                                    jubyte *pMbsk,
                                    jint mbskOff,
                                    jint mbskScbn,
                                    jint width,
                                    jint height,
                                    jint fgColor,
                                    SurfbceDbtbRbsInfo *pRbsInfo,
                                    NbtivePrimitive *pPrim,
                                    CompositeInfo *pCompInfo)
{
    mlib_s32 cnstA, cnstR, cnstG, cnstB;
    mlib_s32 rbsScbn = pRbsInfo->scbnStride;
    mlib_f32 cnstARGB0;
    mlib_u8  *mul8_cnstA, *mul8_dstF, *mul8_srcF;
    mlib_s32 SrcOpAnd, SrcOpXor, SrcOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 srcF, dstF;
    mlib_s32 log_vbl[3];
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

    log_vbl[0] = SrcOpAnd;
    log_vbl[1] = SrcOpXor;
    log_vbl[2] = SrcOpAdd;

    DstOpAnd = (AlphbRules[pCompInfo->rule].dstOps).bndvbl;
    DstOpXor = (AlphbRules[pCompInfo->rule].dstOps).xorvbl;
    DstOpAdd = (AlphbRules[pCompInfo->rule].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    dstF = (((cnstA) & DstOpAnd) ^ DstOpXor) + DstOpAdd;
    srcF = (((  255) & SrcOpAnd) ^ SrcOpXor) + SrcOpAdd;

    vis_write_gsr(7 << 3);

    mul8_cnstA = mul8tbble[cnstA];

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        mul8_dstF  = mul8tbble[dstF];
        mul8_srcF  = mul8tbble[srcF];

        if (rbsScbn == 4*width && mbskScbn == width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntRgbAlphbMbskFill_line(rbsBbse, pMbsk, width, cnstARGB0,
                                     mul8_cnstA, mul8_dstF, mul8_srcF,
                                     (void*)mul8tbble);

            PTR_ADD(rbsBbse, rbsScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        mlib_s32 dstA;
        mlib_d64 cnstARGB;

        if (dstF == 0xFF && srcF == 0) return;

        cnstARGB = MUL8_VIS(cnstARGB0, srcF);
        dstA = dstF + mul8_cnstA[srcF];

        if (rbsScbn == 4*width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntRgbAlphbMbskFill_A1_line(rbsBbse, pMbsk, width, cnstARGB,
                                        dstF, dstA);

            PTR_ADD(rbsBbse, rbsScbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntBgrAlphbMbskFill)(void *rbsBbse,
                                   jubyte *pMbsk,
                                   jint mbskOff,
                                   jint mbskScbn,
                                   jint width,
                                   jint height,
                                   jint fgColor,
                                   SurfbceDbtbRbsInfo *pRbsInfo,
                                   NbtivePrimitive *pPrim,
                                   CompositeInfo *pCompInfo)
{
    mlib_s32 cnstA, cnstR, cnstG, cnstB;
    mlib_s32 rbsScbn = pRbsInfo->scbnStride;
    mlib_f32 cnstARGB0;
    mlib_u8  *mul8_cnstA, *mul8_dstF, *mul8_srcF;
    mlib_s32 SrcOpAnd, SrcOpXor, SrcOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 srcF, dstF;
    mlib_s32 log_vbl[3];
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

    cnstARGB0 = F32_FROM_U8x4(cnstA, cnstB, cnstG, cnstR);

    SrcOpAnd = (AlphbRules[pCompInfo->rule].srcOps).bndvbl;
    SrcOpXor = (AlphbRules[pCompInfo->rule].srcOps).xorvbl;
    SrcOpAdd = (AlphbRules[pCompInfo->rule].srcOps).bddvbl;
    SrcOpAdd -= SrcOpXor;

    log_vbl[0] = SrcOpAnd;
    log_vbl[1] = SrcOpXor;
    log_vbl[2] = SrcOpAdd;

    DstOpAnd = (AlphbRules[pCompInfo->rule].dstOps).bndvbl;
    DstOpXor = (AlphbRules[pCompInfo->rule].dstOps).xorvbl;
    DstOpAdd = (AlphbRules[pCompInfo->rule].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    dstF = (((cnstA) & DstOpAnd) ^ DstOpXor) + DstOpAdd;
    srcF = (((  255) & SrcOpAnd) ^ SrcOpXor) + SrcOpAdd;

    vis_write_gsr(7 << 3);

    mul8_cnstA = mul8tbble[cnstA];

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        mul8_dstF  = mul8tbble[dstF];
        mul8_srcF  = mul8tbble[srcF];

        if (rbsScbn == 4*width && mbskScbn == width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntRgbAlphbMbskFill_line(rbsBbse, pMbsk, width, cnstARGB0,
                                     mul8_cnstA, mul8_dstF, mul8_srcF,
                                     (void*)mul8tbble);

            PTR_ADD(rbsBbse, rbsScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        mlib_s32 dstA;
        mlib_d64 cnstARGB;

        if (dstF == 0xFF && srcF == 0) return;

        cnstARGB = MUL8_VIS(cnstARGB0, srcF);
        dstA = dstF + mul8_cnstA[srcF];

        if (rbsScbn == 4*width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntRgbAlphbMbskFill_A1_line(rbsBbse, pMbsk, width, cnstARGB,
                                        dstF, dstA);

            PTR_ADD(rbsBbse, rbsScbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(ThreeByteBgrAlphbMbskFill)(void *rbsBbse,
                                         jubyte *pMbsk,
                                         jint mbskOff,
                                         jint mbskScbn,
                                         jint width,
                                         jint height,
                                         jint fgColor,
                                         SurfbceDbtbRbsInfo *pRbsInfo,
                                         NbtivePrimitive *pPrim,
                                         CompositeInfo *pCompInfo)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *pbuff = buff;
    mlib_s32 cnstA, cnstR, cnstG, cnstB;
    mlib_s32 rbsScbn = pRbsInfo->scbnStride;
    mlib_f32 cnstARGB0;
    mlib_u8  *mul8_cnstA, *mul8_dstF, *mul8_srcF;
    mlib_s32 SrcOpAnd, SrcOpXor, SrcOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 srcF, dstF;
    mlib_s32 log_vbl[3];
    mlib_s32 j;

    if (width > BUFF_SIZE) pbuff = mlib_mblloc(width*sizeof(mlib_s32));

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

    log_vbl[0] = SrcOpAnd;
    log_vbl[1] = SrcOpXor;
    log_vbl[2] = SrcOpAdd;

    DstOpAnd = (AlphbRules[pCompInfo->rule].dstOps).bndvbl;
    DstOpXor = (AlphbRules[pCompInfo->rule].dstOps).xorvbl;
    DstOpAdd = (AlphbRules[pCompInfo->rule].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    dstF = (((cnstA) & DstOpAnd) ^ DstOpXor) + DstOpAdd;
    srcF = (((  255) & SrcOpAnd) ^ SrcOpXor) + SrcOpAdd;

    vis_write_gsr(7 << 3);

    mul8_cnstA = mul8tbble[cnstA];

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        mul8_dstF  = mul8tbble[dstF];
        mul8_srcF  = mul8tbble[srcF];

        for (j = 0; j < height; j++) {
            ADD_SUFF(ThreeByteBgrToIntArgbConvert)(rbsBbse, pbuff, width, 1,
                                                   pRbsInfo, pRbsInfo,
                                                   pPrim, pCompInfo);

            IntRgbAlphbMbskFill_line(pbuff, pMbsk, width, cnstARGB0,
                                     mul8_cnstA, mul8_dstF, mul8_srcF,
                                     (void*)mul8tbble);

            IntArgbToThreeByteBgrConvert(pbuff, rbsBbse, width, 1,
                                         pRbsInfo, pRbsInfo, pPrim, pCompInfo);

            PTR_ADD(rbsBbse, rbsScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        mlib_s32 dstA;
        mlib_d64 cnstARGB;

        if (dstF == 0xFF && srcF == 0) return;

        cnstARGB = MUL8_VIS(cnstARGB0, srcF);
        dstA = dstF + mul8_cnstA[srcF];

        for (j = 0; j < height; j++) {
            ADD_SUFF(ThreeByteBgrToIntArgbConvert)(rbsBbse, pbuff, width, 1,
                                                   pRbsInfo, pRbsInfo,
                                                   pPrim, pCompInfo);

            IntRgbAlphbMbskFill_A1_line(pbuff, pMbsk, width, cnstARGB,
                                        dstF, dstA);

            IntArgbToThreeByteBgrConvert(pbuff, rbsBbse, width, 1,
                                         pRbsInfo, pRbsInfo, pPrim, pCompInfo);

            PTR_ADD(rbsBbse, rbsScbn);
        }
    }

    if (pbuff != buff) {
        mlib_free(pbuff);
    }
}

/***************************************************************/

#endif /* JAVA2D_NO_MLIB */
