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

#include "vis_AlphbMbcros.h"

/***************************************************************/

/* ##############################################################
 * IntArgbToIntArgbSrcOverMbskBlit()
 * IntArgbToFourByteAbgrSrcOverMbskBlit()
 */

#define MASK_FILL(rr, pbthA, dstA, dstARGB, srcA, srcARGB)     \
{                                                              \
    mlib_d64 t0, t1;                                           \
                                                               \
    srcA = MUL8_INT(srcA, mul8_extrb[pbthA]);                  \
    dstA = MUL8_INT(dstA, 0xff - srcA);                        \
                                                               \
    t0 = MUL8_VIS(srcARGB, srcA);                              \
    t1 = MUL8_VIS(dstARGB, dstA);                              \
    rr = vis_fpbdd16(t0, t1);                                  \
                                                               \
    dstA += srcA;                                              \
    DIV_ALPHA(rr, dstA);                                       \
}

/***************************************************************/

stbtic void IntArgbToIntArgbSrcOverMbskBlit_line(mlib_f32 *dst_ptr,
                                                 mlib_f32 *src_ptr,
                                                 mlib_u8  *pMbsk,
                                                 mlib_s32 width,
                                                 mlib_u8  *mul8_extrb,
                                                 mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbthA0, pbthA1, dstA0, dstA1, srcA0, srcA1, msk;
    mlib_d64 res0, res1, dstARGB;
    mlib_f32 dstARGB0, srcARGB0, srcARGB1;

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbthA0 = pMbsk[i];
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
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
        srcA0 = *(mlib_u8*)(src_ptr + i);
        srcA1 = *(mlib_u8*)(src_ptr + i + 1);
        srcARGB0 = src_ptr[i];
        srcARGB1 = src_ptr[i + 1];

        MASK_FILL(res0, pbthA0, dstA0, vis_rebd_hi(dstARGB), srcA0, srcARGB0);
        MASK_FILL(res1, pbthA1, dstA1, vis_rebd_lo(dstARGB), srcA1, srcARGB1);

        res0 = vis_fpbck16_pbir(res0, res1);

        msk = (((-srcA0) & (1 << 11)) | ((-srcA1) & (1 << 10))) >> 10;
        vis_pst_32(res0, dst_ptr + i, msk);

        *(mlib_u8*)(dst_ptr + i    ) = dstA0;
        *(mlib_u8*)(dst_ptr + i + 1) = dstA1;
    }

    if (i < width) {
        pbthA0 = pMbsk[i];
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fpbck16(res0);
            *(mlib_u8*)(dst_ptr + i) = dstA0;
        }
    }
}

/***************************************************************/

#undef  MASK_FILL
#define MASK_FILL(rr, pbthA, dstA, dstARGB, srcA, srcARGB)     \
{                                                              \
    mlib_d64 t0, t1;                                           \
                                                               \
    srcA = mul8_extrb[srcA];                                   \
    dstA = MUL8_INT(dstA, 0xff - srcA);                        \
                                                               \
    t0 = MUL8_VIS(srcARGB, srcA);                              \
    t1 = MUL8_VIS(dstARGB, dstA);                              \
    rr = vis_fpbdd16(t0, t1);                                  \
                                                               \
    dstA += srcA;                                              \
    DIV_ALPHA(rr, dstA);                                       \
}

/***************************************************************/

stbtic void IntArgbToIntArgbSrcOverMbskBlit_A1_line(mlib_f32 *dst_ptr,
                                                    mlib_f32 *src_ptr,
                                                    mlib_u8  *pMbsk,
                                                    mlib_s32 width,
                                                    mlib_u8  *mul8_extrb,
                                                    mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 dstA0, dstA1, srcA0, srcA1, msk;
    mlib_d64 res0, res1, dstARGB;
    mlib_f32 dstARGB0, srcARGB0, srcARGB1;

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fpbck16(res0);
            *(mlib_u8*)(dst_ptr + i) = dstA0;
        }

        i0 = 1;
    }

#prbgmb pipeloop(0)
    for (i = i0; i <= width - 2; i += 2) {
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        dstA1 = *(mlib_u8*)(dst_ptr + i + 1);
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srcA0 = *(mlib_u8*)(src_ptr + i);
        srcA1 = *(mlib_u8*)(src_ptr + i + 1);
        srcARGB0 = src_ptr[i];
        srcARGB1 = src_ptr[i + 1];

        MASK_FILL(res0, pbthA0, dstA0, vis_rebd_hi(dstARGB), srcA0, srcARGB0);
        MASK_FILL(res1, pbthA1, dstA1, vis_rebd_lo(dstARGB), srcA1, srcARGB1);

        res0 = vis_fpbck16_pbir(res0, res1);

        msk = (((-srcA0) & (1 << 11)) | ((-srcA1) & (1 << 10))) >> 10;
        vis_pst_32(res0, dst_ptr + i, msk);

        *(mlib_u8*)(dst_ptr + i    ) = dstA0;
        *(mlib_u8*)(dst_ptr + i + 1) = dstA1;
    }

    if (i < width) {
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fpbck16(res0);
            *(mlib_u8*)(dst_ptr + i) = dstA0;
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntArgbSrcOverMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 extrbA;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_u8  *mul8_extrb;
    mlib_s32 j;

    extrbA = (mlib_s32)(pCompInfo->detbils.extrbAlphb * 255.0 + 0.5);

    mul8_extrb = mul8tbble[extrbA];

    vis_write_gsr(7 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (dstScbn == 4*width && srcScbn == dstScbn && mbskScbn == width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            IntArgbToIntArgbSrcOverMbskBlit_line(dstBbse, srcBbse, pMbsk,
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
            IntArgbToIntArgbSrcOverMbskBlit_A1_line(dstBbse, srcBbse, pMbsk,
                                                    width, mul8_extrb,
                                                    (void*)mul8tbble);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToFourByteAbgrSrcOverMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *src_buff = buff, *dst_buff;
    mlib_s32 extrbA;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_u8  *mul8_extrb;
    mlib_s32 j;

    extrbA = (mlib_s32)(pCompInfo->detbils.extrbAlphb * 255.0 + 0.5);

    mul8_extrb = mul8tbble[extrbA];

    vis_write_gsr(7 << 3);

    if (2*width > BUFF_SIZE) src_buff = mlib_mblloc(2*width*sizeof(mlib_s32));
    dst_buff = (mlib_s32*)src_buff + width;

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        for (j = 0; j < height; j++) {
            IntArgbToIntAbgrConvert_line(srcBbse, src_buff, width);
            if (!((mlib_s32)dstBbse & 3)) {
                IntArgbToIntArgbSrcOverMbskBlit_line(dstBbse, src_buff, pMbsk,
                                                     width, mul8_extrb,
                                                     (void*)mul8tbble);
            } else {
                mlib_ImbgeCopy_nb(dstBbse, dst_buff, width*sizeof(mlib_s32));
                IntArgbToIntArgbSrcOverMbskBlit_line(dst_buff, src_buff, pMbsk,
                                                     width, mul8_extrb,
                                                     (void*)mul8tbble);
                mlib_ImbgeCopy_nb(dst_buff, dstBbse, width*sizeof(mlib_s32));
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
    } else {
        for (j = 0; j < height; j++) {
            IntArgbToIntAbgrConvert_line(srcBbse, src_buff, width);
            if (!((mlib_s32)dstBbse & 3)) {
                IntArgbToIntArgbSrcOverMbskBlit_A1_line(dstBbse, src_buff,
                                                        pMbsk, width,
                                                        mul8_extrb,
                                                        (void*)mul8tbble);
            } else {
                mlib_ImbgeCopy_nb(dstBbse, dst_buff, width*sizeof(mlib_s32));
                IntArgbToIntArgbSrcOverMbskBlit_A1_line(dst_buff, src_buff,
                                                        pMbsk, width,
                                                        mul8_extrb,
                                                        (void*)mul8tbble);
                mlib_ImbgeCopy_nb(dst_buff, dstBbse, width*sizeof(mlib_s32));
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
    }

    if (src_buff != buff) {
        mlib_free(src_buff);
    }
}

/***************************************************************/

/* ##############################################################
 * IntArgbToIntRgbSrcOverMbskBlit()
 * IntArgbToIntBgrSrcOverMbskBlit()
 */

#undef  MASK_FILL
#define MASK_FILL(rr, pbthA, dstA, dstARGB, srcA, srcARGB)     \
{                                                              \
    mlib_d64 t0, t1;                                           \
    mlib_f32 srcAf, dstAf;                                     \
                                                               \
    srcA = MUL8_INT(srcA, mul8_extrb[pbthA]);                  \
    srcAf = ((mlib_f32 *)vis_mul8s_tbl)[srcA];                 \
    dstAf = vis_fpsub16s(cnst1, srcAf);                        \
                                                               \
    t0 = vis_fmul8x16bl(srcARGB, srcAf);                       \
    t1 = vis_fmul8x16bl(dstARGB, dstAf);                       \
    rr = vis_fpbdd16(t0, t1);                                  \
}

/***************************************************************/

stbtic void IntArgbToIntRgbSrcOverMbskBlit_line(mlib_f32 *dst_ptr,
                                                mlib_f32 *src_ptr,
                                                mlib_u8  *pMbsk,
                                                mlib_s32 width,
                                                mlib_u8  *mul8_extrb,
                                                mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbthA0, pbthA1, srcA0, srcA1, msk;
    mlib_d64 res0, res1, dstARGB;
    mlib_f32 dstARGB0, srcARGB0, srcARGB1;
    mlib_d64 mbskRGB = vis_to_double_dup(0x00FFFFFF);
    mlib_f32 cnst1 = vis_to_flobt(0x8000);

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbthA0 = pMbsk[i];
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fbnds(vis_fpbck16(res0), vis_rebd_hi(mbskRGB));
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
        srcARGB0 = src_ptr[i];
        srcARGB1 = src_ptr[i + 1];

        MASK_FILL(res0, pbthA0, dstA0, vis_rebd_hi(dstARGB), srcA0, srcARGB0);
        MASK_FILL(res1, pbthA1, dstA1, vis_rebd_lo(dstARGB), srcA1, srcARGB1);

        res0 = vis_fpbck16_pbir(res0, res1);
        res0 = vis_fbnd(res0, mbskRGB);

        msk = (((-srcA0) & (1 << 11)) | ((-srcA1) & (1 << 10))) >> 10;
        vis_pst_32(res0, dst_ptr + i, msk);
    }

    if (i < width) {
        pbthA0 = pMbsk[i];
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fbnds(vis_fpbck16(res0), vis_rebd_hi(mbskRGB));
        }
    }
}

/***************************************************************/

stbtic void IntArgbToIntBgrSrcOverMbskBlit_line(mlib_f32 *dst_ptr,
                                                mlib_f32 *src_ptr,
                                                mlib_u8  *pMbsk,
                                                mlib_s32 width,
                                                mlib_u8  *mul8_extrb,
                                                mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbthA0, pbthA1, srcA0, srcA1, msk;
    mlib_d64 res0, res1, dstARGB, srcARGB;
    mlib_f32 dstARGB0, srcARGB0;
    mlib_d64 mbskRGB = vis_to_double_dup(0x00FFFFFF);
    mlib_f32 cnst1 = vis_to_flobt(0x8000);

#if VIS >= 0x200
    vis_write_bmbsk(0x03214765, 0);
#endif

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbthA0 = pMbsk[i];
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        ARGB2ABGR_FL(srcARGB0)
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fbnds(vis_fpbck16(res0), vis_rebd_hi(mbskRGB));
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
        ARGB2ABGR_DB(srcARGB)

        MASK_FILL(res0, pbthA0, dstA0, vis_rebd_hi(dstARGB),
                                srcA0, vis_rebd_hi(srcARGB));
        MASK_FILL(res1, pbthA1, dstA1, vis_rebd_lo(dstARGB),
                                srcA1, vis_rebd_lo(srcARGB));

        res0 = vis_fpbck16_pbir(res0, res1);
        res0 = vis_fbnd(res0, mbskRGB);

        msk = (((-srcA0) & (1 << 11)) | ((-srcA1) & (1 << 10))) >> 10;
        vis_pst_32(res0, dst_ptr + i, msk);
    }

    if (i < width) {
        pbthA0 = pMbsk[i];
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        ARGB2ABGR_FL(srcARGB0)
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fbnds(vis_fpbck16(res0), vis_rebd_hi(mbskRGB));
        }
    }
}

/***************************************************************/

#undef  MASK_FILL
#define MASK_FILL(rr, pbthA, dstA, dstARGB, srcA, srcARGB)     \
{                                                              \
    mlib_d64 t0, t1;                                           \
    mlib_f32 srcAf, dstAf;                                     \
                                                               \
    srcA = mul8_extrb[srcA];                                   \
    srcAf = ((mlib_f32 *)vis_mul8s_tbl)[srcA];                 \
    dstAf = vis_fpsub16s(cnst1, srcAf);                        \
                                                               \
    t0 = vis_fmul8x16bl(srcARGB, srcAf);                       \
    t1 = vis_fmul8x16bl(dstARGB, dstAf);                       \
    rr = vis_fpbdd16(t0, t1);                                  \
}

/***************************************************************/

stbtic void IntArgbToIntRgbSrcOverMbskBlit_A1_line(mlib_f32 *dst_ptr,
                                                   mlib_f32 *src_ptr,
                                                   mlib_u8  *pMbsk,
                                                   mlib_s32 width,
                                                   mlib_u8  *mul8_extrb,
                                                   mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 srcA0, srcA1, msk;
    mlib_d64 res0, res1, dstARGB;
    mlib_f32 dstARGB0, srcARGB0, srcARGB1;
    mlib_d64 mbskRGB = vis_to_double_dup(0x00FFFFFF);
    mlib_f32 cnst1 = vis_to_flobt(0x8000);

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fbnds(vis_fpbck16(res0), vis_rebd_hi(mbskRGB));
        }

        i0 = 1;
    }

#prbgmb pipeloop(0)
    for (i = i0; i <= width - 2; i += 2) {
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srcA0 = *(mlib_u8*)(src_ptr + i);
        srcA1 = *(mlib_u8*)(src_ptr + i + 1);
        srcARGB0 = src_ptr[i];
        srcARGB1 = src_ptr[i + 1];

        MASK_FILL(res0, pbthA0, dstA0, vis_rebd_hi(dstARGB), srcA0, srcARGB0);
        MASK_FILL(res1, pbthA1, dstA1, vis_rebd_lo(dstARGB), srcA1, srcARGB1);

        res0 = vis_fpbck16_pbir(res0, res1);
        res0 = vis_fbnd(res0, mbskRGB);

        msk = (((-srcA0) & (1 << 11)) | ((-srcA1) & (1 << 10))) >> 10;
        vis_pst_32(res0, dst_ptr + i, msk);
    }

    if (i < width) {
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fbnds(vis_fpbck16(res0), vis_rebd_hi(mbskRGB));
        }
    }
}

/***************************************************************/

stbtic void IntArgbToIntBgrSrcOverMbskBlit_A1_line(mlib_f32 *dst_ptr,
                                                   mlib_f32 *src_ptr,
                                                   mlib_u8  *pMbsk,
                                                   mlib_s32 width,
                                                   mlib_u8  *mul8_extrb,
                                                   mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 srcA0, srcA1, msk;
    mlib_d64 res0, res1, dstARGB, srcARGB;
    mlib_f32 dstARGB0, srcARGB0;
    mlib_d64 mbskRGB = vis_to_double_dup(0x00FFFFFF);
    mlib_f32 cnst1 = vis_to_flobt(0x8000);

#if VIS >= 0x200
    vis_write_bmbsk(0x03214765, 0);
#endif

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        ARGB2ABGR_FL(srcARGB0)
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fbnds(vis_fpbck16(res0), vis_rebd_hi(mbskRGB));
        }

        i0 = 1;
    }

#prbgmb pipeloop(0)
    for (i = i0; i <= width - 2; i += 2) {
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srcA0 = *(mlib_u8*)(src_ptr + i);
        srcA1 = *(mlib_u8*)(src_ptr + i + 1);
        srcARGB = vis_freg_pbir(src_ptr[i], src_ptr[i + 1]);
        ARGB2ABGR_DB(srcARGB)

        MASK_FILL(res0, pbthA0, dstA0, vis_rebd_hi(dstARGB),
                                srcA0, vis_rebd_hi(srcARGB));
        MASK_FILL(res1, pbthA1, dstA1, vis_rebd_lo(dstARGB),
                                srcA1, vis_rebd_lo(srcARGB));

        res0 = vis_fpbck16_pbir(res0, res1);
        res0 = vis_fbnd(res0, mbskRGB);

        msk = (((-srcA0) & (1 << 11)) | ((-srcA1) & (1 << 10))) >> 10;
        vis_pst_32(res0, dst_ptr + i, msk);
    }

    if (i < width) {
        srcA0 = *(mlib_u8*)(src_ptr + i);
        dstARGB0 = dst_ptr[i];
        srcARGB0 = src_ptr[i];
        ARGB2ABGR_FL(srcARGB0)
        MASK_FILL(res0, pbthA0, dstA0, dstARGB0, srcA0, srcARGB0);
        if (srcA0) {
            dst_ptr[i] = vis_fbnds(vis_fpbck16(res0), vis_rebd_hi(mbskRGB));
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntRgbSrcOverMbskBlit)(MASKBLIT_PARAMS)
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
            IntArgbToIntRgbSrcOverMbskBlit_line(dstBbse, srcBbse, pMbsk,
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
            IntArgbToIntRgbSrcOverMbskBlit_A1_line(dstBbse, srcBbse, pMbsk,
                                                    width, mul8_extrb,
                                                    (void*)mul8tbble);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntBgrSrcOverMbskBlit)(MASKBLIT_PARAMS)
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
            IntArgbToIntBgrSrcOverMbskBlit_line(dstBbse, srcBbse, pMbsk,
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
            IntArgbToIntBgrSrcOverMbskBlit_A1_line(dstBbse, srcBbse, pMbsk,
                                                    width, mul8_extrb,
                                                    (void*)mul8tbble);

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
    }
}

/***************************************************************/

#endif
