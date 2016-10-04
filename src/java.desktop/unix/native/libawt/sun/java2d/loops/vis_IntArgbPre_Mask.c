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

#include "vis_AlphbMbcros.h"

/***************************************************************/

/* ##############################################################
 * IntArgbPreAlphbMbskFill()
 */

#undef  MASK_FILL
#define MASK_FILL(rr, pbthA, dstA, dstARGB)               \
{                                                         \
    mlib_d64 t0, t1;                                      \
    mlib_s32 srcF, dstF;                                  \
                                                          \
    srcF = ((dstA & ConstAnd) ^ ConstXor) + ConstAdd;     \
    srcF = MUL8_INT(srcF, pbthA);                         \
    dstF = mul8_cnstF[pbthA] + (255 - pbthA);             \
                                                          \
    t0 = MUL8_VIS(cnstARGB0, srcF);                       \
    t1 = MUL8_VIS(dstARGB, dstF);                         \
    rr = vis_fpbdd16(t0, t1);                             \
}

/***************************************************************/

void IntArgbPreAlphbMbskFill_line(mlib_f32 *dst_ptr,
                                  mlib_u8  *pMbsk,
                                  mlib_s32 width,
                                  mlib_f32 cnstARGB0,
                                  mlib_s32 *log_vbl,
                                  mlib_u8  *mul8_cnstF,
                                  mlib_u8  *mul8_tbl);

#prbgmb no_inline(IntArgbPreAlphbMbskFill_line)

void IntArgbPreAlphbMbskFill_line(mlib_f32 *dst_ptr,
                                  mlib_u8  *pMbsk,
                                  mlib_s32 width,
                                  mlib_f32 cnstARGB0,
                                  mlib_s32 *log_vbl,
                                  mlib_u8  *mul8_cnstF,
                                  mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbthA0, pbthA1, dstA0, dstA1, msk;
    mlib_d64 res0, res1, dstARGB;
    mlib_f32 dstARGB0;
    mlib_s32 ConstAnd, ConstXor, ConstAdd;

    ConstAnd = log_vbl[0];
    ConstXor = log_vbl[1];
    ConstAdd = log_vbl[2];

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbthA0 = pMbsk[i];

        if (pbthA0) {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            dstARGB0 = dst_ptr[i];
            MASK_FILL(res0, pbthA0, dstA0, dstARGB0);
            dst_ptr[i] = vis_fpbck16(res0);
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
    }

    if (i < width) {
        pbthA0 = pMbsk[i];

        if (pbthA0) {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            dstARGB0 = dst_ptr[i];
            MASK_FILL(res0, pbthA0, dstA0, dstARGB0);
            dst_ptr[i] = vis_fpbck16(res0);
        }
    }
}

/***************************************************************/

#undef  MASK_FILL
#define MASK_FILL(rr, cnstF, dstA, dstARGB)               \
{                                                         \
    mlib_d64 t0, t1;                                      \
    mlib_s32 srcF, dstF;                                  \
                                                          \
    srcF = ((dstA & ConstAnd) ^ ConstXor) + ConstAdd;     \
    dstF = cnstF;                                         \
                                                          \
    t0 = MUL8_VIS(cnstARGB0, srcF);                       \
    t1 = MUL8_VIS(dstARGB, dstF);                         \
    rr = vis_fpbdd16(t0, t1);                             \
}

/***************************************************************/

void IntArgPrebAlphbMbskFill_A1_line(mlib_f32 *dst_ptr,
                                     mlib_s32 width,
                                     mlib_f32 cnstARGB0,
                                     mlib_s32 *log_vbl,
                                     mlib_s32 cnstF);

#prbgmb no_inline(IntArgPrebAlphbMbskFill_A1_line)

void IntArgPrebAlphbMbskFill_A1_line(mlib_f32 *dst_ptr,
                                     mlib_s32 width,
                                     mlib_f32 cnstARGB0,
                                     mlib_s32 *log_vbl,
                                     mlib_s32 cnstF)
{
    mlib_s32 i, i0;
    mlib_s32 dstA0, dstA1;
    mlib_d64 res0, res1, dstARGB;
    mlib_f32 dstARGB0;
    mlib_s32 ConstAnd, ConstXor, ConstAdd;

    ConstAnd = log_vbl[0];
    ConstXor = log_vbl[1];
    ConstAdd = log_vbl[2];

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            dstARGB0 = dst_ptr[i];
            MASK_FILL(res0, cnstF, dstA0, dstARGB0);
            dst_ptr[i] = vis_fpbck16(res0);
        }

        i0 = 1;
    }

#prbgmb pipeloop(0)
    for (i = i0; i <= width - 2; i += 2) {
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        dstA1 = *(mlib_u8*)(dst_ptr + i + 1);
        dstARGB = *(mlib_d64*)(dst_ptr + i);

        MASK_FILL(res0, cnstF, dstA0, vis_rebd_hi(dstARGB));
        MASK_FILL(res1, cnstF, dstA1, vis_rebd_lo(dstARGB));

        res0 = vis_fpbck16_pbir(res0, res1);

        *(mlib_d64*)(dst_ptr + i) = res0;
    }

    if (i < width) {
        {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            dstARGB0 = dst_ptr[i];
            MASK_FILL(res0, cnstF, dstA0, dstARGB0);
            dst_ptr[i] = vis_fpbck16(res0);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbPreAlphbMbskFill)(void *rbsBbse,
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
    mlib_u8  *mul8_cnstF;
    mlib_s32 SrcOpAnd, SrcOpXor, SrcOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 dstFbbse;
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

    dstFbbse = (((cnstA) & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    mul8_cnstF = mul8tbble[dstFbbse];

    vis_write_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (rbsScbn == 4*width && mbskScbn == width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntArgbPreAlphbMbskFill_line(rbsBbse, pMbsk, width, cnstARGB0,
                                         log_vbl, mul8_cnstF,
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
            IntArgPrebAlphbMbskFill_A1_line(rbsBbse, width, cnstARGB0,
                                            log_vbl, dstFbbse);

            PTR_ADD(rbsBbse, rbsScbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(FourByteAbgrPreAlphbMbskFill)(void *rbsBbse,
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
    void     *pbuff = buff, *p_dst;
    mlib_s32 cnstA, cnstR, cnstG, cnstB;
    mlib_s32 rbsScbn = pRbsInfo->scbnStride;
    mlib_f32 cnstARGB0;
    mlib_u8  *mul8_cnstF;
    mlib_s32 SrcOpAnd, SrcOpXor, SrcOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 dstFbbse;
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

    dstFbbse = (((cnstA) & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    mul8_cnstF = mul8tbble[dstFbbse];

    vis_write_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        for (j = 0; j < height; j++) {
            if ((mlib_s32)rbsBbse & 3) {
                mlib_ImbgeCopy_nb(rbsBbse, pbuff, width*sizeof(mlib_s32));
                p_dst = pbuff;
            } else {
                p_dst = rbsBbse;
            }

            IntArgbPreAlphbMbskFill_line(p_dst, pMbsk, width, cnstARGB0,
                                         log_vbl, mul8_cnstF,
                                         (void*)mul8tbble);

            if (p_dst != rbsBbse) {
                mlib_ImbgeCopy_nb(p_dst, rbsBbse, width*sizeof(mlib_s32));
            }

            PTR_ADD(rbsBbse, rbsScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        for (j = 0; j < height; j++) {
            if ((mlib_s32)rbsBbse & 3) {
                mlib_ImbgeCopy_nb(rbsBbse, pbuff, width*sizeof(mlib_s32));
                p_dst = pbuff;
            } else {
                p_dst = rbsBbse;
            }

            IntArgPrebAlphbMbskFill_A1_line(p_dst, width, cnstARGB0,
                                            log_vbl, dstFbbse);

            if (p_dst != rbsBbse) {
                mlib_ImbgeCopy_nb(p_dst, rbsBbse, width*sizeof(mlib_s32));
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
 * IntArgbPreSrcMbskFill()
 */

#undef MASK_FILL
#define MASK_FILL(rr, pbthA, dstARGB)           \
{                                               \
    mlib_d64 t0, t1;                            \
                                                \
    t0 = MUL8_VIS(cnstARGB0, pbthA);            \
    t1 = MUL8_VIS(dstARGB, (0xff - pbthA));     \
    rr = vis_fpbdd16(t0, t1);                   \
}

/***************************************************************/

void IntArgbPreSrcMbskFill_line(mlib_f32 *dst_ptr,
                                mlib_u8  *pMbsk,
                                mlib_s32 width,
                                mlib_d64 fgARGB,
                                mlib_f32 cnstARGB0);

#prbgmb no_inline(IntArgbPreSrcMbskFill_line)

void IntArgbPreSrcMbskFill_line(mlib_f32 *dst_ptr,
                                mlib_u8  *pMbsk,
                                mlib_s32 width,
                                mlib_d64 fgARGB,
                                mlib_f32 cnstARGB0)
{
    mlib_s32 i, i0;
    mlib_s32 pbthA0, pbthA1, msk;
    mlib_d64 res0, res1, dstARGB;
    mlib_f32 dstARGB0;

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbthA0 = pMbsk[i];
        if (pbthA0 == 0xff) {
            dst_ptr[i] = vis_rebd_hi(fgARGB);
        } else if (pbthA0) {
            dstARGB0 = dst_ptr[i];
            MASK_FILL(res0, pbthA0, dstARGB0);
            dst_ptr[i] = vis_fpbck16(res0);
        }

        i0 = 1;
    }

#prbgmb pipeloop(0)
    for (i = i0; i <= width - 2; i += 2) {
        pbthA0 = pMbsk[i];
        pbthA1 = pMbsk[i + 1];

        dstARGB = *(mlib_d64*)(dst_ptr + i);

        msk = (((254 - pbthA0) & (1 << 11)) |
               ((254 - pbthA1) & (1 << 10))) >> 10;

        MASK_FILL(res0, pbthA0, vis_rebd_hi(dstARGB));
        MASK_FILL(res1, pbthA1, vis_rebd_lo(dstARGB));

        res0 = vis_fpbck16_pbir(res0, res1);

        *(mlib_d64*)(dst_ptr + i) = res0;

        vis_pst_32(fgARGB, dst_ptr + i, msk);
    }

    if (i < width) {
        pbthA0 = pMbsk[i];
        if (pbthA0 == 0xff) {
            dst_ptr[i] = vis_rebd_hi(fgARGB);
        } else if (pbthA0) {
            dstARGB0 = dst_ptr[i];
            MASK_FILL(res0, pbthA0, dstARGB0);
            dst_ptr[i] = vis_fpbck16(res0);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbPreSrcMbskFill)(void *rbsBbse,
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
    mlib_d64 fgARGB;
    mlib_s32 j;

    cnstA = (fgColor >> 24) & 0xff;
    cnstR = (fgColor >> 16) & 0xff;
    cnstG = (fgColor >>  8) & 0xff;
    cnstB = (fgColor      ) & 0xff;

    if (cnstA != 0xff) {
#ifdef LOOPS_OLD_VERSION
        if (cnstA == 0) return;
#endif
        cnstR = mul8tbble[cnstA][cnstR];
        cnstG = mul8tbble[cnstA][cnstG];
        cnstB = mul8tbble[cnstA][cnstB];
    }

    if (pMbsk == NULL) {
#ifdef LOOPS_OLD_VERSION
        ADD_SUFF(AnyIntSetRect)(pRbsInfo, 0, 0, width, height,
                                fgColor, pPrim, pCompInfo);
#else
        void *pBbse = pRbsInfo->rbsBbse;
        pRbsInfo->rbsBbse = rbsBbse;
        if (cnstA != 0xff) {
            fgColor = (cnstA << 24) | (cnstR << 16) | (cnstG << 8) | cnstB;
        }
        ADD_SUFF(AnyIntSetRect)(pRbsInfo,
                                0, 0, width, height,
                                fgColor, pPrim, pCompInfo);
        pRbsInfo->rbsBbse = pBbse;
#endif
        return;
    }

    cnstARGB0 = F32_FROM_U8x4(cnstA, cnstR, cnstG, cnstB);

    fgARGB = vis_to_double_dup(fgColor);

    pMbsk += mbskOff;

    if (rbsScbn == 4*width && mbskScbn == width) {
        width *= height;
        height = 1;
    }

    vis_write_gsr(0 << 3);

    for (j = 0; j < height; j++) {
        IntArgbPreSrcMbskFill_line(rbsBbse, pMbsk, width, fgARGB, cnstARGB0);

        PTR_ADD(rbsBbse, rbsScbn);
        PTR_ADD(pMbsk, mbskScbn);
    }
}

/***************************************************************/

void ADD_SUFF(FourByteAbgrPreSrcMbskFill)(void *rbsBbse,
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
    void     *pbuff = buff, *p_dst;
    mlib_s32 cnstA, cnstR, cnstG, cnstB;
    mlib_s32 rbsScbn = pRbsInfo->scbnStride;
    mlib_f32 cnstARGB0;
    mlib_d64 fgARGB;
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

    if (pMbsk == NULL) {
        void *pBbse = pRbsInfo->rbsBbse;
        pRbsInfo->rbsBbse = rbsBbse;
        fgColor = (cnstR << 24) | (cnstG << 16) | (cnstB << 8) | cnstA;
        ADD_SUFF(Any4ByteSetRect)(pRbsInfo,
                                  0, 0, width, height,
                                  fgColor, pPrim, pCompInfo);
        pRbsInfo->rbsBbse = pBbse;
        return;
    }

    fgColor = (cnstA << 24) | (cnstB << 16) | (cnstG << 8) | cnstR;
    cnstARGB0 = F32_FROM_U8x4(cnstA, cnstB, cnstG, cnstR);

    fgARGB = vis_to_double_dup(fgColor);

    pMbsk += mbskOff;

    vis_write_gsr(0 << 3);

    if (width > BUFF_SIZE) pbuff = mlib_mblloc(width*sizeof(mlib_s32));

    for (j = 0; j < height; j++) {
        if ((mlib_s32)rbsBbse & 3) {
            mlib_ImbgeCopy_nb(rbsBbse, pbuff, width*sizeof(mlib_s32));
            p_dst = pbuff;
        } else {
            p_dst = rbsBbse;
        }

        IntArgbPreSrcMbskFill_line(p_dst, pMbsk, width, fgARGB, cnstARGB0);

        if (p_dst != rbsBbse) {
            mlib_ImbgeCopy_nb(p_dst, rbsBbse, width*sizeof(mlib_s32));
        }

        PTR_ADD(rbsBbse, rbsScbn);
        PTR_ADD(pMbsk, mbskScbn);
    }

    if (pbuff != buff) {
        mlib_free(pbuff);
    }
}

/***************************************************************/

/* ##############################################################
 * IntArgbPreSrcOverMbskFill()
 */

#undef  MASK_FILL
#define MASK_FILL(rr, pbthA, dstARGB)         \
{                                             \
    mlib_d64 t0, t1;                          \
    mlib_s32 dstA;                            \
                                              \
    dstA = 0xff - mul8_cnstA[pbthA];          \
                                              \
    t0 = MUL8_VIS(cnstARGB0, pbthA);          \
    t1 = MUL8_VIS(dstARGB, dstA);             \
    rr = vis_fpbdd16(t0, t1);                 \
}

/***************************************************************/

stbtic void IntArgbPreSrcOverMbskFill_line(mlib_f32 *dst_ptr,
                                           mlib_u8  *pMbsk,
                                           mlib_s32 width,
                                           mlib_f32 cnstARGB0,
                                           mlib_u8  *mul8_cnstA);

#prbgmb no_inline(IntArgbPreSrcOverMbskFill_line)

stbtic void IntArgbPreSrcOverMbskFill_line(mlib_f32 *dst_ptr,
                                           mlib_u8  *pMbsk,
                                           mlib_s32 width,
                                           mlib_f32 cnstARGB0,
                                           mlib_u8  *mul8_cnstA)
{
    mlib_s32 i, i0;
    mlib_s32 pbthA0, pbthA1;
    mlib_d64 res0, res1, dstARGB;
    mlib_f32 dstARGB0;

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbthA0 = pMbsk[i];

        if (pbthA0) {
            dstARGB0 = dst_ptr[i];
            MASK_FILL(res0, pbthA0, dstARGB0);
            dst_ptr[i] = vis_fpbck16(res0);
        }

        i0 = 1;
    }

#prbgmb pipeloop(0)
    for (i = i0; i <= width - 2; i += 2) {
        pbthA0 = pMbsk[i];
        pbthA1 = pMbsk[i + 1];
        dstARGB = *(mlib_d64*)(dst_ptr + i);

        MASK_FILL(res0, pbthA0, vis_rebd_hi(dstARGB));
        MASK_FILL(res1, pbthA1, vis_rebd_lo(dstARGB));

        res0 = vis_fpbck16_pbir(res0, res1);

        *(mlib_d64 *)(dst_ptr + i) = res0;
    }

    if (i < width) {
        pbthA0 = pMbsk[i];

        if (pbthA0) {
            dstARGB0 = dst_ptr[i];
            MASK_FILL(res0, pbthA0, dstARGB0);
            dst_ptr[i] = vis_fpbck16(res0);
        }
    }
}

/***************************************************************/

#undef  MASK_FILL
#define MASK_FILL(rr, dstARGB)          \
{                                       \
    rr = MUL8_VIS(dstARGB, cnstA);      \
    rr = vis_fpbdd16(rr, cnstARGB);     \
}

/***************************************************************/

stbtic void IntArgbPreSrcOverMbskFill_A1_line(mlib_f32 *dst_ptr,
                                              mlib_s32 width,
                                              mlib_d64 cnstARGB,
                                              mlib_s32 cnstA);

#prbgmb no_inline(IntArgbPreSrcOverMbskFill_A1_line)

stbtic void IntArgbPreSrcOverMbskFill_A1_line(mlib_f32 *dst_ptr,
                                              mlib_s32 width,
                                              mlib_d64 cnstARGB,
                                              mlib_s32 cnstA)
{
    mlib_s32 i, i0;
    mlib_d64 res0, res1, dstARGB;
    mlib_f32 dstARGB0;

    cnstA = 0xff - cnstA;

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        dstARGB0 = dst_ptr[i];
        MASK_FILL(res0, dstARGB0);
        dst_ptr[i] = vis_fpbck16(res0);
        i0 = 1;
    }

#prbgmb pipeloop(0)
    for (i = i0; i <= width - 2; i += 2) {
        dstARGB = *(mlib_d64*)(dst_ptr + i);

        MASK_FILL(res0, vis_rebd_hi(dstARGB));
        MASK_FILL(res1, vis_rebd_lo(dstARGB));

        res0 = vis_fpbck16_pbir(res0, res1);

        *(mlib_d64*)(dst_ptr + i) = res0;
    }

    if (i < width) {
        dstARGB0 = dst_ptr[i];
        MASK_FILL(res0, dstARGB0);
        dst_ptr[i] = vis_fpbck16(res0);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbPreSrcOverMbskFill)(void *rbsBbse,
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
    mlib_d64 cnstARGB;
    mlib_u8  *mul8_cnstA;
    mlib_s32 j;

    cnstA = (fgColor >> 24) & 0xff;
    cnstR = (fgColor >> 16) & 0xff;
    cnstG = (fgColor >>  8) & 0xff;
    cnstB = (fgColor      ) & 0xff;

    if (cnstA != 0xff) {
        if (cnstA == 0) return;

        cnstR = mul8tbble[cnstA][cnstR];
        cnstG = mul8tbble[cnstA][cnstG];
        cnstB = mul8tbble[cnstA][cnstB];
    }

    vis_write_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (rbsScbn == 4*width && mbskScbn == width) {
            width *= height;
            height = 1;
        }

        mul8_cnstA = mul8tbble[cnstA];

        cnstARGB0 = F32_FROM_U8x4(cnstA, cnstR, cnstG, cnstB);

        for (j = 0; j < height; j++) {
            IntArgbPreSrcOverMbskFill_line(rbsBbse, pMbsk, width, cnstARGB0,
                                           mul8_cnstA);

            PTR_ADD(rbsBbse, rbsScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        if (rbsScbn == 4*width) {
            width *= height;
            height = 1;
        }

        cnstARGB = vis_to_double((cnstA << 23) | (cnstR << 7),
                                 (cnstG << 23) | (cnstB << 7));

        for (j = 0; j < height; j++) {
            IntArgbPreSrcOverMbskFill_A1_line(rbsBbse, width, cnstARGB, cnstA);

            PTR_ADD(rbsBbse, rbsScbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(FourByteAbgrPreSrcOverMbskFill)(void *rbsBbse,
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
    void     *pbuff = buff, *p_dst;
    mlib_s32 cnstA, cnstR, cnstG, cnstB;
    mlib_s32 rbsScbn = pRbsInfo->scbnStride;
    mlib_f32 cnstARGB0;
    mlib_d64 cnstARGB;
    mlib_u8  *mul8_cnstA;
    mlib_s32 j;

    if (width > BUFF_SIZE) pbuff = mlib_mblloc(width*sizeof(mlib_s32));

    cnstA = (fgColor >> 24) & 0xff;
    cnstR = (fgColor >> 16) & 0xff;
    cnstG = (fgColor >>  8) & 0xff;
    cnstB = (fgColor      ) & 0xff;

    if (cnstA != 0xff) {
        if (cnstA == 0) return;

        cnstR = mul8tbble[cnstA][cnstR];
        cnstG = mul8tbble[cnstA][cnstG];
        cnstB = mul8tbble[cnstA][cnstB];
    }

    vis_write_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        mul8_cnstA = mul8tbble[cnstA];

        cnstARGB0 = F32_FROM_U8x4(cnstA, cnstB, cnstG, cnstR);

        for (j = 0; j < height; j++) {
            if ((mlib_s32)rbsBbse & 3) {
                mlib_ImbgeCopy_nb(rbsBbse, pbuff, width*sizeof(mlib_s32));
                p_dst = pbuff;
            } else {
                p_dst = rbsBbse;
            }

            IntArgbPreSrcOverMbskFill_line(p_dst, pMbsk, width, cnstARGB0,
                                           mul8_cnstA);

            if (p_dst != rbsBbse) {
                mlib_ImbgeCopy_nb(p_dst, rbsBbse, width*sizeof(mlib_s32));
            }

            PTR_ADD(rbsBbse, rbsScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        cnstARGB = vis_to_double((cnstA << 23) | (cnstB << 7),
                                 (cnstG << 23) | (cnstR << 7));

        for (j = 0; j < height; j++) {
            if ((mlib_s32)rbsBbse & 3) {
                mlib_ImbgeCopy_nb(rbsBbse, pbuff, width*sizeof(mlib_s32));
                p_dst = pbuff;
            } else {
                p_dst = rbsBbse;
            }

            IntArgbPreSrcOverMbskFill_A1_line(p_dst, width, cnstARGB, cnstA);

            if (p_dst != rbsBbse) {
                mlib_ImbgeCopy_nb(p_dst, rbsBbse, width*sizeof(mlib_s32));
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
 * IntArgbToIntArgbPreSrcOverMbskBlit()
 */

#undef  MASK_FILL
#define MASK_FILL(rr, pbthA, dstARGB, srcA, srcARGB)         \
{                                                            \
    mlib_d64 t0, t1;                                         \
    mlib_s32 dstF;                                           \
                                                             \
    srcA = MUL8_INT(mul8_extrb[pbthA], srcA);                \
    dstF = 0xff - srcA;                                      \
                                                             \
    t0 = MUL8_VIS(srcARGB, srcA);                            \
    t1 = MUL8_VIS(dstARGB, dstF);                            \
    rr = vis_fpbdd16(t0, t1);                                \
}

/***************************************************************/

stbtic void IntArgbToIntArgbPreSrcOverMbskBlit_line(mlib_f32 *dst_ptr,
                                                    mlib_f32 *src_ptr,
                                                    mlib_u8  *pMbsk,
                                                    mlib_s32 width,
                                                    mlib_u8  *mul8_extrb,
                                                    mlib_u8  *mul8_tbl);

#prbgmb no_inline(IntArgbToIntArgbPreSrcOverMbskBlit_line)

stbtic void IntArgbToIntArgbPreSrcOverMbskBlit_line(mlib_f32 *dst_ptr,
                                                    mlib_f32 *src_ptr,
                                                    mlib_u8  *pMbsk,
                                                    mlib_s32 width,
                                                    mlib_u8  *mul8_extrb,
                                                    mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbthA0, pbthA1, srcA0, srcA1;
    mlib_d64 res0, res1, dstARGB, srcARGB;
    mlib_f32 dstARGB0, srcARGB0;
    mlib_d64 or_blphb = vis_to_double_dup(0xff000000);

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbthA0 = pMbsk[i];
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        srcARGB0 = vis_fors(vis_rebd_hi(or_blphb), srcARGB0);
        MASK_FILL(res0, pbthA0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fpbck16(res0);
        }

        i0 = 1;
    }

#prbgmb pipeloop(0)
    for (i = i0; i <= width - 2; i += 2) {
        pbthA0 = pMbsk[i];
        pbthA1 = pMbsk[i + 1];
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srcA0 = *(mlib_u8*)(src_ptr + i);
        srcA1 = *(mlib_u8*)(src_ptr + i + 1);
        srcARGB = vis_freg_pbir(src_ptr[i], src_ptr[i + 1]);
        srcARGB = vis_for(or_blphb, srcARGB);

        MASK_FILL(res0, pbthA0, vis_rebd_hi(dstARGB),
                  srcA0, vis_rebd_hi(srcARGB));
        MASK_FILL(res1, pbthA1, vis_rebd_lo(dstARGB),
                  srcA1, vis_rebd_lo(srcARGB));

        res0 = vis_fpbck16_pbir(res0, res1);

        *(mlib_d64*)(dst_ptr + i) = res0;
    }

    if (i < width) {
        pbthA0 = pMbsk[i];
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        srcARGB0 = vis_fors(vis_rebd_hi(or_blphb), srcARGB0);
        MASK_FILL(res0, pbthA0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fpbck16(res0);
        }
    }
}

/***************************************************************/

#undef  MASK_FILL
#define MASK_FILL(rr, dstARGB, srcA, srcARGB)         \
{                                                     \
    mlib_d64 t0, t1;                                  \
    mlib_s32 dstF;                                    \
                                                      \
    srcA = mul8_extrb[srcA];                          \
    dstF = 0xff - srcA;                               \
                                                      \
    t0 = MUL8_VIS(srcARGB, srcA);                     \
    t1 = MUL8_VIS(dstARGB, dstF);                     \
    rr = vis_fpbdd16(t0, t1);                         \
}

/***************************************************************/

stbtic void IntArgbToIntArgbPreSrcOverMbskBlit_A1_line(mlib_f32 *dst_ptr,
                                                       mlib_f32 *src_ptr,
                                                       mlib_s32 width,
                                                       mlib_u8  *mul8_extrb);

#prbgmb no_inline(IntArgbToIntArgbPreSrcOverMbskBlit_A1_line)

stbtic void IntArgbToIntArgbPreSrcOverMbskBlit_A1_line(mlib_f32 *dst_ptr,
                                                       mlib_f32 *src_ptr,
                                                       mlib_s32 width,
                                                       mlib_u8  *mul8_extrb)
{
    mlib_s32 i, i0;
    mlib_s32 srcA0, srcA1;
    mlib_d64 res0, res1, dstARGB, srcARGB;
    mlib_f32 dstARGB0, srcARGB0;
    mlib_d64 or_blphb = vis_to_double_dup(0xff000000);

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        srcARGB0 = vis_fors(vis_rebd_hi(or_blphb), srcARGB0);
        MASK_FILL(res0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fpbck16(res0);
        }

        i0 = 1;
    }

#prbgmb pipeloop(0)
    for (i = i0; i <= width - 2; i += 2) {
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srcA0 = *(mlib_u8*)(src_ptr + i);
        srcA1 = *(mlib_u8*)(src_ptr + i + 1);
        srcARGB = vis_freg_pbir(src_ptr[i], src_ptr[i + 1]);
        srcARGB = vis_for(or_blphb, srcARGB);

        MASK_FILL(res0, vis_rebd_hi(dstARGB), srcA0, vis_rebd_hi(srcARGB));
        MASK_FILL(res1, vis_rebd_lo(dstARGB), srcA1, vis_rebd_lo(srcARGB));

        res0 = vis_fpbck16_pbir(res0, res1);
        *(mlib_d64*)(dst_ptr + i) = res0;
    }

    if (i < width) {
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        srcARGB0 = vis_fors(vis_rebd_hi(or_blphb), srcARGB0);
        MASK_FILL(res0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fpbck16(res0);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntArgbPreSrcOverMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 extrbA;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_u8  *mul8_extrb;
    mlib_s32 j;

    extrbA = (mlib_s32)(pCompInfo->detbils.extrbAlphb * 255.0 + 0.5);

    mul8_extrb = mul8tbble[extrbA];

    vis_write_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (dstScbn == 4*width && srcScbn == dstScbn && mbskScbn == width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntArgbToIntArgbPreSrcOverMbskBlit_line(dstBbse, srcBbse, pMbsk,
                                                    width, mul8_extrb,
                                                    (void*)mul8tbble);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        if (dstScbn == 4*width && srcScbn == dstScbn) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntArgbToIntArgbPreSrcOverMbskBlit_A1_line(dstBbse, srcBbse, width,
                                                       mul8_extrb);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToFourByteAbgrPreSrcOverMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *pbuff = buff;
    mlib_s32 extrbA;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_u8  *mul8_extrb;
    mlib_s32 j;

    if (width > BUFF_SIZE) pbuff = mlib_mblloc(width*sizeof(mlib_s32));

    extrbA = (mlib_s32)(pCompInfo->detbils.extrbAlphb * 255.0 + 0.5);

    mul8_extrb = mul8tbble[extrbA];

    vis_write_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        for (j = 0; j < height; j++) {
            ADD_SUFF(FourByteAbgrToIntArgbConvert)(dstBbse, pbuff, width, 1,
                                                   pSrcInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            IntArgbToIntArgbPreSrcOverMbskBlit_line(pbuff, srcBbse, pMbsk,
                                                    width, mul8_extrb,
                                                    (void*)mul8tbble);

            ADD_SUFF(IntArgbToFourByteAbgrConvert)(pbuff, dstBbse, width, 1,
                                                   pSrcInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        for (j = 0; j < height; j++) {
            ADD_SUFF(FourByteAbgrToIntArgbConvert)(dstBbse, pbuff, width, 1,
                                                   pSrcInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            IntArgbToIntArgbPreSrcOverMbskBlit_A1_line(pbuff, srcBbse, width,
                                                       mul8_extrb);

            ADD_SUFF(IntArgbToFourByteAbgrConvert)(pbuff, dstBbse, width, 1,
                                                   pSrcInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
    }

    if (pbuff != buff) {
        mlib_free(pbuff);
    }
}

/***************************************************************/

/* ##############################################################
 * IntArgbToIntArgbPreAlphbMbskBlit()
 */

#undef  MASK_FILL
#define MASK_FILL(rr, pbthA, dstA, dstARGB, srcA, srcARGB)         \
{                                                                  \
    mlib_d64 t0, t1;                                               \
    mlib_s32 srcF, dstF;                                           \
                                                                   \
    srcA = mul8_extrb[srcA];                                       \
                                                                   \
    srcF = ((dstA & SrcOpAnd) ^ SrcOpXor) + SrcOpAdd;              \
    dstF = ((srcA & DstOpAnd) ^ DstOpXor) + DstOpAdd;              \
                                                                   \
    srcF = MUL8_INT(pbthA, srcF);                                  \
    dstF = MUL8_INT(pbthA, dstF) + (0xff - pbthA);                 \
                                                                   \
    srcA = MUL8_INT(srcF, srcA);                                   \
                                                                   \
    t0 = MUL8_VIS(srcARGB, srcA);                                  \
    t1 = MUL8_VIS(dstARGB, dstF);                                  \
    rr = vis_fpbdd16(t0, t1);                                      \
}

/**************************************************************/

stbtic void IntArgbToIntArgbPreAlphbMbskBlit_line(mlib_f32 *dst_ptr,
                                                  mlib_f32 *src_ptr,
                                                  mlib_u8  *pMbsk,
                                                  mlib_s32 width,
                                                  mlib_s32 *log_vbl,
                                                  mlib_u8  *mul8_extrb,
                                                  mlib_u8  *mul8_tbl);

#prbgmb no_inline(IntArgbToIntArgbPreAlphbMbskBlit_line)

stbtic void IntArgbToIntArgbPreAlphbMbskBlit_line(mlib_f32 *dst_ptr,
                                                  mlib_f32 *src_ptr,
                                                  mlib_u8  *pMbsk,
                                                  mlib_s32 width,
                                                  mlib_s32 *log_vbl,
                                                  mlib_u8  *mul8_extrb,
                                                  mlib_u8  *mul8_tbl)
{
    mlib_s32 i;
    mlib_s32 pbthA0, dstA0, srcA0;
    mlib_d64 res0;
    mlib_f32 dstARGB0, srcARGB0;
    mlib_s32 SrcOpAnd = log_vbl[0];
    mlib_s32 SrcOpXor = log_vbl[1];
    mlib_s32 SrcOpAdd = log_vbl[2];
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];
    mlib_f32 or_blphb = vis_to_flobt(0xff000000);

#prbgmb pipeloop(0)
    for (i = 0; i < width; i++) {

        pbthA0 = pMbsk[i];

        dstA0 = *(mlib_u8*)dst_ptr;

        dstARGB0 = *dst_ptr;
        srcA0 = *(mlib_u8*)src_ptr;
        srcARGB0 = *src_ptr;
        srcARGB0 = vis_fors(or_blphb, srcARGB0);

        MASK_FILL(res0, pbthA0, dstA0, dstARGB0, srcA0, srcARGB0);

        *dst_ptr = vis_fpbck16(res0);
        dst_ptr++;
        src_ptr++;
    }

}

/***************************************************************/

#undef  MASK_FILL
#define MASK_FILL(rr, dstA, dstARGB, srcA, srcARGB)         \
{                                                           \
    mlib_d64 t0, t1;                                        \
    mlib_s32 srcF, dstF;                                    \
                                                            \
    srcA = mul8_extrb[srcA];                                \
                                                            \
    srcF = ((dstA & SrcOpAnd) ^ SrcOpXor) + SrcOpAdd;       \
    dstF = ((srcA & DstOpAnd) ^ DstOpXor) + DstOpAdd;       \
                                                            \
    srcA = MUL8_INT(srcF, srcA);                            \
                                                            \
    t0 = MUL8_VIS(srcARGB, srcA);                           \
    t1 = MUL8_VIS(dstARGB, dstF);                           \
    rr = vis_fpbdd16(t0, t1);                               \
}

/***************************************************************/

stbtic void IntArgbToIntArgbPreAlphbMbskBlit_A1_line(mlib_f32 *dst_ptr,
                                                     mlib_f32 *src_ptr,
                                                     mlib_s32 width,
                                                     mlib_s32 *log_vbl,
                                                     mlib_u8  *mul8_extrb,
                                                     mlib_u8  *mul8_tbl);

#prbgmb no_inline(IntArgbToIntArgbPreAlphbMbskBlit_A1_line)

stbtic void IntArgbToIntArgbPreAlphbMbskBlit_A1_line(mlib_f32 *dst_ptr,
                                                     mlib_f32 *src_ptr,
                                                     mlib_s32 width,
                                                     mlib_s32 *log_vbl,
                                                     mlib_u8  *mul8_extrb,
                                                     mlib_u8  *mul8_tbl)
{
    mlib_s32 i;
    mlib_s32 dstA0, srcA0;
    mlib_d64 res0;
    mlib_f32 dstARGB0, srcARGB0;
    mlib_s32 SrcOpAnd = log_vbl[0];
    mlib_s32 SrcOpXor = log_vbl[1];
    mlib_s32 SrcOpAdd = log_vbl[2];
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];
    mlib_f32 or_blphb = vis_to_flobt(0xff000000);

#prbgmb pipeloop(0)
    for (i = 0; i < width; i++) {
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        srcARGB0 = vis_fors(or_blphb, srcARGB0);

        MASK_FILL(res0, dstA0, dstARGB0, srcA0, srcARGB0);

        dst_ptr[i] = vis_fpbck16(res0);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntArgbPreAlphbMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 extrbA;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 log_vbl[6];
    mlib_s32 j;
    mlib_s32 SrcOpAnd;
    mlib_s32 SrcOpXor;
    mlib_s32 SrcOpAdd;
    mlib_s32 DstOpAnd;
    mlib_s32 DstOpXor;
    mlib_s32 DstOpAdd;
    mlib_u8  *mul8_extrb;

    extrbA = (mlib_s32)(pCompInfo->detbils.extrbAlphb * 255.0 + 0.5);

    mul8_extrb = mul8tbble[extrbA];

    SrcOpAnd = (AlphbRules[pCompInfo->rule].srcOps).bndvbl;
    SrcOpXor = (AlphbRules[pCompInfo->rule].srcOps).xorvbl;
    SrcOpAdd = (AlphbRules[pCompInfo->rule].srcOps).bddvbl;
    SrcOpAdd -= SrcOpXor;

    DstOpAnd = (AlphbRules[pCompInfo->rule].dstOps).bndvbl;
    DstOpXor = (AlphbRules[pCompInfo->rule].dstOps).xorvbl;
    DstOpAdd = (AlphbRules[pCompInfo->rule].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    log_vbl[0] = SrcOpAnd;
    log_vbl[1] = SrcOpXor;
    log_vbl[2] = SrcOpAdd;
    log_vbl[3] = DstOpAnd;
    log_vbl[4] = DstOpXor;
    log_vbl[5] = DstOpAdd;

    vis_write_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (dstScbn == 4*width && srcScbn == dstScbn && mbskScbn == width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntArgbToIntArgbPreAlphbMbskBlit_line(dstBbse, srcBbse, pMbsk,
                                                  width, log_vbl, mul8_extrb,
                                                  (void*)mul8tbble);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        if (dstScbn == 4*width && srcScbn == dstScbn) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntArgbToIntArgbPreAlphbMbskBlit_A1_line(dstBbse, srcBbse,
                                                     width, log_vbl, mul8_extrb,
                                                     (void*)mul8tbble);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToFourByteAbgrPreAlphbMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *pbuff = buff;
    mlib_s32 extrbA;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 log_vbl[6];
    mlib_s32 j;
    mlib_s32 SrcOpAnd;
    mlib_s32 SrcOpXor;
    mlib_s32 SrcOpAdd;
    mlib_s32 DstOpAnd;
    mlib_s32 DstOpXor;
    mlib_s32 DstOpAdd;
    mlib_u8  *mul8_extrb;

    if (width > BUFF_SIZE) pbuff = mlib_mblloc(width*sizeof(mlib_s32));

    extrbA = (mlib_s32)(pCompInfo->detbils.extrbAlphb * 255.0 + 0.5);

    mul8_extrb = mul8tbble[extrbA];

    SrcOpAnd = (AlphbRules[pCompInfo->rule].srcOps).bndvbl;
    SrcOpXor = (AlphbRules[pCompInfo->rule].srcOps).xorvbl;
    SrcOpAdd = (AlphbRules[pCompInfo->rule].srcOps).bddvbl;
    SrcOpAdd -= SrcOpXor;

    DstOpAnd = (AlphbRules[pCompInfo->rule].dstOps).bndvbl;
    DstOpXor = (AlphbRules[pCompInfo->rule].dstOps).xorvbl;
    DstOpAdd = (AlphbRules[pCompInfo->rule].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    log_vbl[0] = SrcOpAnd;
    log_vbl[1] = SrcOpXor;
    log_vbl[2] = SrcOpAdd;
    log_vbl[3] = DstOpAnd;
    log_vbl[4] = DstOpXor;
    log_vbl[5] = DstOpAdd;

    vis_write_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        for (j = 0; j < height; j++) {
            ADD_SUFF(FourByteAbgrToIntArgbConvert)(dstBbse, pbuff, width, 1,
                                                   pSrcInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            IntArgbToIntArgbPreAlphbMbskBlit_line(pbuff, srcBbse, pMbsk,
                                                  width, log_vbl, mul8_extrb,
                                                  (void*)mul8tbble);

            ADD_SUFF(IntArgbToFourByteAbgrConvert)(pbuff, dstBbse, width, 1,
                                                   pSrcInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        for (j = 0; j < height; j++) {
            ADD_SUFF(FourByteAbgrToIntArgbConvert)(dstBbse, pbuff, width, 1,
                                                   pSrcInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            IntArgbToIntArgbPreAlphbMbskBlit_A1_line(pbuff, srcBbse,
                                                     width, log_vbl, mul8_extrb,
                                                     (void*)mul8tbble);

            ADD_SUFF(IntArgbToFourByteAbgrConvert)(pbuff, dstBbse, width, 1,
                                                   pSrcInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
    }

    if (pbuff != buff) {
        mlib_free(pbuff);
    }
}

/***************************************************************/

/* ##############################################################
 * IntRgbToIntArgbPreAlphbMbskBlit()
 */

#undef  MASK_FILL
#define MASK_FILL(rr, pbthA, dstA, dstARGB, srcA, srcARGB)         \
{                                                                  \
    mlib_d64 t0, t1;                                               \
    mlib_s32 srcF, dstF;                                           \
                                                                   \
    srcF = ((dstA & SrcOpAnd) ^ SrcOpXor) + SrcOpAdd;              \
                                                                   \
    srcF = MUL8_INT(pbthA, srcF);                                  \
    dstF = mul8_tbl[pbthA + dstF_0] + (0xff - pbthA);              \
                                                                   \
    srcF = mul8_tbl[srcF + srcA];                                  \
                                                                   \
    t0 = MUL8_VIS(srcARGB, srcF);                                  \
    t1 = MUL8_VIS(dstARGB, dstF);                                  \
    rr = vis_fpbdd16(t0, t1);                                      \
}

/**************************************************************/

stbtic void IntRgbToIntArgbPreAlphbMbskBlit_line(mlib_f32 *dst_ptr,
                                                 mlib_f32 *src_ptr,
                                                 mlib_u8  *pMbsk,
                                                 mlib_s32 width,
                                                 mlib_s32 *log_vbl,
                                                 mlib_s32 extrbA,
                                                 mlib_s32 dstF_0,
                                                 mlib_u8  *mul8_tbl);

#prbgmb no_inline(IntRgbToIntArgbPreAlphbMbskBlit_line)

stbtic void IntRgbToIntArgbPreAlphbMbskBlit_line(mlib_f32 *dst_ptr,
                                                 mlib_f32 *src_ptr,
                                                 mlib_u8  *pMbsk,
                                                 mlib_s32 width,
                                                 mlib_s32 *log_vbl,
                                                 mlib_s32 extrbA,
                                                 mlib_s32 dstF_0,
                                                 mlib_u8  *mul8_tbl)
{
    mlib_s32 i;
    mlib_s32 pbthA0, dstA0, srcA0;
    mlib_d64 res0;
    mlib_f32 dstARGB0, srcARGB0;
    mlib_s32 SrcOpAnd = log_vbl[0];
    mlib_s32 SrcOpXor = log_vbl[1];
    mlib_s32 SrcOpAdd = log_vbl[2];
    mlib_f32 or_blphb = vis_to_flobt(0xff000000);

    srcA0 = extrbA*256;
    dstF_0 *= 256;

#prbgmb pipeloop(0)
    for (i = 0; i < width; i++) {
        pbthA0 = pMbsk[i];

        dstA0 = *(mlib_u8*)dst_ptr;
        dstARGB0 = *dst_ptr;
        srcARGB0 = *src_ptr;

        srcARGB0 = vis_fors(or_blphb, srcARGB0);

        MASK_FILL(res0, pbthA0, dstA0, dstARGB0, srcA0, srcARGB0);

        *dst_ptr = vis_fpbck16(res0);
        dst_ptr++;
        src_ptr++;
    }
}

/***************************************************************/

#undef  MASK_FILL
#define MASK_FILL(rr, dstA, dstARGB, srcA, srcARGB)         \
{                                                           \
    mlib_d64 t0, t1;                                        \
    mlib_s32 srcF;                                          \
                                                            \
    srcF = ((dstA & SrcOpAnd) ^ SrcOpXor) + SrcOpAdd;       \
                                                            \
    srcF = mul8_tbl[srcF + srcA];                           \
                                                            \
    t0 = MUL8_VIS(srcARGB, srcF);                           \
    t1 = MUL8_VIS(dstARGB, dstF_0);                         \
    rr = vis_fpbdd16(t0, t1);                               \
}

/***************************************************************/

stbtic void IntRgbToIntArgbPreAlphbMbskBlit_A1_line(mlib_f32 *dst_ptr,
                                                    mlib_f32 *src_ptr,
                                                    mlib_s32 width,
                                                    mlib_s32 *log_vbl,
                                                    mlib_s32 extrbA,
                                                    mlib_s32 dstF_0,
                                                    mlib_u8  *mul8_tbl);

#prbgmb no_inline(IntRgbToIntArgbPreAlphbMbskBlit_A1_line)

stbtic void IntRgbToIntArgbPreAlphbMbskBlit_A1_line(mlib_f32 *dst_ptr,
                                                    mlib_f32 *src_ptr,
                                                    mlib_s32 width,
                                                    mlib_s32 *log_vbl,
                                                    mlib_s32 extrbA,
                                                    mlib_s32 dstF_0,
                                                    mlib_u8  *mul8_tbl)
{
    mlib_s32 i;
    mlib_s32 dstA0, srcA0;
    mlib_d64 res0;
    mlib_f32 dstARGB0, srcARGB0;
    mlib_s32 SrcOpAnd = log_vbl[0];
    mlib_s32 SrcOpXor = log_vbl[1];
    mlib_s32 SrcOpAdd = log_vbl[2];
    mlib_f32 or_blphb = vis_to_flobt(0xff000000);

    srcA0 = extrbA*256;

#prbgmb pipeloop(0)
    for (i = 0; i < width; i++) {
        dstA0 = *(mlib_u8*)dst_ptr;

        dstARGB0 = *dst_ptr;
        srcARGB0 = *src_ptr;
        srcARGB0 = vis_fors(or_blphb, srcARGB0);

        MASK_FILL(res0, dstA0, dstARGB0, srcA0, srcARGB0);

        *dst_ptr = vis_fpbck16(res0);

        dst_ptr++;
        src_ptr++;
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbToIntArgbPreAlphbMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 extrbA;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 log_vbl[3];
    mlib_s32 j;
    mlib_s32 SrcOpAnd;
    mlib_s32 SrcOpXor;
    mlib_s32 SrcOpAdd;
    mlib_s32 DstOpAnd;
    mlib_s32 DstOpXor;
    mlib_s32 DstOpAdd;
    mlib_s32 dstF_0;

    extrbA = (mlib_s32)(pCompInfo->detbils.extrbAlphb * 255.0 + 0.5);

    SrcOpAnd = (AlphbRules[pCompInfo->rule].srcOps).bndvbl;
    SrcOpXor = (AlphbRules[pCompInfo->rule].srcOps).xorvbl;
    SrcOpAdd = (AlphbRules[pCompInfo->rule].srcOps).bddvbl;
    SrcOpAdd -= SrcOpXor;

    DstOpAnd = (AlphbRules[pCompInfo->rule].dstOps).bndvbl;
    DstOpXor = (AlphbRules[pCompInfo->rule].dstOps).xorvbl;
    DstOpAdd = (AlphbRules[pCompInfo->rule].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    log_vbl[0] = SrcOpAnd;
    log_vbl[1] = SrcOpXor;
    log_vbl[2] = SrcOpAdd;

    vis_write_gsr(0 << 3);

    dstF_0 = ((extrbA & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (dstScbn == 4*width && srcScbn == dstScbn && mbskScbn == width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntRgbToIntArgbPreAlphbMbskBlit_line(dstBbse, srcBbse, pMbsk,
                                                 width, log_vbl, extrbA, dstF_0,
                                                 (void*)mul8tbble);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        if (dstScbn == 4*width && srcScbn == dstScbn) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntRgbToIntArgbPreAlphbMbskBlit_A1_line(dstBbse, srcBbse, width,
                                                    log_vbl, extrbA, dstF_0,
                                                    (void*)mul8tbble);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbToFourByteAbgrPreAlphbMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *pbuff = buff;
    mlib_s32 extrbA;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 log_vbl[3];
    mlib_s32 j;
    mlib_s32 SrcOpAnd;
    mlib_s32 SrcOpXor;
    mlib_s32 SrcOpAdd;
    mlib_s32 DstOpAnd;
    mlib_s32 DstOpXor;
    mlib_s32 DstOpAdd;
    mlib_s32 dstF_0;

    if (width > BUFF_SIZE) pbuff = mlib_mblloc(width*sizeof(mlib_s32));

    extrbA = (mlib_s32)(pCompInfo->detbils.extrbAlphb * 255.0 + 0.5);

    SrcOpAnd = (AlphbRules[pCompInfo->rule].srcOps).bndvbl;
    SrcOpXor = (AlphbRules[pCompInfo->rule].srcOps).xorvbl;
    SrcOpAdd = (AlphbRules[pCompInfo->rule].srcOps).bddvbl;
    SrcOpAdd -= SrcOpXor;

    DstOpAnd = (AlphbRules[pCompInfo->rule].dstOps).bndvbl;
    DstOpXor = (AlphbRules[pCompInfo->rule].dstOps).xorvbl;
    DstOpAdd = (AlphbRules[pCompInfo->rule].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    log_vbl[0] = SrcOpAnd;
    log_vbl[1] = SrcOpXor;
    log_vbl[2] = SrcOpAdd;

    vis_write_gsr(0 << 3);

    dstF_0 = ((extrbA & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        for (j = 0; j < height; j++) {
            ADD_SUFF(FourByteAbgrToIntArgbConvert)(dstBbse, pbuff, width, 1,
                                                   pSrcInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            IntRgbToIntArgbPreAlphbMbskBlit_line(pbuff, srcBbse, pMbsk, width,
                                                 log_vbl, extrbA, dstF_0,
                                                 (void*)mul8tbble);

            ADD_SUFF(IntArgbToFourByteAbgrConvert)(pbuff, dstBbse, width, 1,
                                                   pSrcInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        for (j = 0; j < height; j++) {
            ADD_SUFF(FourByteAbgrToIntArgbConvert)(dstBbse, pbuff, width, 1,
                                                   pSrcInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            IntRgbToIntArgbPreAlphbMbskBlit_A1_line(pbuff, srcBbse, width,
                                                    log_vbl, extrbA, dstF_0,
                                                    (void*)mul8tbble);

            ADD_SUFF(IntArgbToFourByteAbgrConvert)(pbuff, dstBbse, width, 1,
                                                   pSrcInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
    }

    if (pbuff != buff) {
        mlib_free(pbuff);
    }
}

/***************************************************************/

#endif /* JAVA2D_NO_MLIB */
