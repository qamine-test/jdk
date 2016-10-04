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

#define ARGB_to_GBGR(x)        \
    (x << 16) | (x & 0xff00) | ((x >> 16) & 0xff)

/***************************************************************/

#define ARGB_to_BGR(x)         \
    ((x << 16) & 0xff0000) | (x & 0xff00) | ((x >> 16) & 0xff)

/***************************************************************/

#define READ_Bgr(i)    \
    (src[3*i] << 16) | (src[3*i + 1] << 8) | src[3*i + 2]

/***************************************************************/

#define ARGB_to_GBGR_FL2(dst, src0, src1) {                    \
    mlib_d64 t0, t1, t2;                                       \
    t0 = vis_fpmerge(src0, src1);                              \
    t1 = vis_fpmerge(vis_rebd_lo(t0), vis_rebd_hi(t0));        \
    t2 = vis_fpmerge(vis_rebd_lo(t0), vis_rebd_lo(t0));        \
    dst = vis_fpmerge(vis_rebd_hi(t2), vis_rebd_lo(t1));       \
}

/***************************************************************/

#define ARGB_to_BGR_FL2(dst, src0, src1) {                     \
    mlib_d64 t0, t1, t2;                                       \
    t0 = vis_fpmerge(src0, src1);                              \
    t1 = vis_fpmerge(vis_rebd_lo(t0), vis_rebd_hi(t0));        \
    t2 = vis_fpmerge(vis_fzeros(),    vis_rebd_lo(t0));        \
    dst = vis_fpmerge(vis_rebd_hi(t2), vis_rebd_lo(t1));       \
}

/***************************************************************/

void ADD_SUFF(IntBgrToIntArgbConvert)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, bmbsk;
    mlib_s32 i, i0, j, x;

    if (dstScbn == 4*width && srcScbn == 4*width) {
        width *= height;
        height = 1;
    }

    bmbsk = vis_to_double_dup(0xFF000000);
    vis_blignbddr(NULL, 7);

    for (j = 0; j < height; j++) {
        mlib_u32 *src = srcBbse;
        mlib_u32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            x = src[i];
            dst[i] = 0xff000000 | ARGB_to_GBGR(x);
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            ARGB2ABGR_FL2(dd, ((mlib_f32*)src)[i], ((mlib_f32*)src)[i + 1]);
            *(mlib_d64*)(dst + i) = vis_for(dd, bmbsk);
        }

        if (i < width) {
            x = src[i];
            dst[i] = 0xff000000 | ARGB_to_GBGR(x);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntBgrToIntArgbScbleConvert)(SCALE_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, bmbsk;
    mlib_s32 j, x;

    bmbsk = vis_to_double_dup(0xFF000000);
    vis_blignbddr(NULL, 7);

    for (j = 0; j < height; j++) {
        mlib_u32 *src = srcBbse;
        mlib_u32 *dst = dstBbse;
        mlib_u32 *dst_end = dst + width;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 7) {
            x = src[tmpsxloc >> shift];
            *dst++ = 0xff000000 | ARGB_to_GBGR(x);
            tmpsxloc += sxinc;
        }

#prbgmb pipeloop(0)
        for (; dst <= dst_end - 2; dst += 2) {
            ARGB2ABGR_FL2(dd, ((mlib_f32*)src)[tmpsxloc >> shift],
                              ((mlib_f32*)src)[(tmpsxloc + sxinc) >> shift]);
            *(mlib_d64*)dst = vis_for(dd, bmbsk);
            tmpsxloc += 2*sxinc;
        }

        for (; dst < dst_end; dst++) {
            x = src[tmpsxloc >> shift];
            *dst++ = 0xff000000 | ARGB_to_GBGR(x);
            tmpsxloc += sxinc;
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntBgrConvert)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd;
    mlib_s32 i, i0, j, x;

    if (dstScbn == 4*width && srcScbn == 4*width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        mlib_u32 *src = srcBbse;
        mlib_u32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            x = src[i];
            dst[i] = ARGB_to_GBGR(x);
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            ARGB_to_GBGR_FL2(dd, ((mlib_f32*)src)[i], ((mlib_f32*)src)[i + 1]);
            *(mlib_d64*)(dst + i) = dd;
        }

        if (i < width) {
            x = src[i];
            dst[i] = ARGB_to_GBGR(x);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntBgrScbleConvert)(SCALE_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd;
    mlib_s32 j, x;

    for (j = 0; j < height; j++) {
        mlib_u32 *src = srcBbse;
        mlib_u32 *dst = dstBbse;
        mlib_u32 *dst_end = dst + width;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 7) {
            x = src[tmpsxloc >> shift];
            *dst++ = ARGB_to_GBGR(x);
            tmpsxloc += sxinc;
        }

#prbgmb pipeloop(0)
        for (; dst <= dst_end - 2; dst += 2) {
            ARGB_to_GBGR_FL2(dd, ((mlib_f32*)src)[tmpsxloc >> shift],
                                 ((mlib_f32*)src)[(tmpsxloc + sxinc) >> shift]);
            *(mlib_d64*)dst = dd;
            tmpsxloc += 2*sxinc;
        }

        for (; dst < dst_end; dst++) {
            x = src[tmpsxloc >> shift];
            *dst++ = ARGB_to_GBGR(x);
            tmpsxloc += sxinc;
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

#define INSERT_U8_34R {                                        \
    mlib_d64 sdb, sdb, sdc, sdd;                               \
    mlib_d64 sde, sdf, sdg, sdh;                               \
    mlib_d64 sdi, sdj, sdk, sdl;                               \
    mlib_d64 sdm;                                              \
                                                               \
    sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_lo(sd1));     \
    sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_hi(sd2));     \
    sdc = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_lo(sd2));     \
    sdd = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdb));     \
    sde = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_hi(sdc));     \
    sdf = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdc));     \
    sdg = vis_fpmerge(vis_rebd_hi(sdd), vis_rebd_lo(sde));     \
    sdh = vis_fpmerge(vis_rebd_lo(sdd), vis_rebd_hi(sdf));     \
    sdi = vis_fpmerge(vis_rebd_hi(sde), vis_rebd_lo(sdf));     \
    sdj = vis_fpmerge(vis_rebd_hi(sdg), vis_rebd_hi(sdi));     \
    sdk = vis_fpmerge(vis_rebd_lo(sdg), vis_rebd_lo(sdi));     \
    sdl = vis_fpmerge(vis_rebd_hi(sFF), vis_rebd_hi(sdh));     \
    sdm = vis_fpmerge(vis_rebd_lo(sFF), vis_rebd_lo(sdh));     \
    dd0 = vis_fpmerge(vis_rebd_hi(sdl), vis_rebd_hi(sdj));     \
    dd1 = vis_fpmerge(vis_rebd_lo(sdl), vis_rebd_lo(sdj));     \
    dd2 = vis_fpmerge(vis_rebd_hi(sdm), vis_rebd_hi(sdk));     \
    dd3 = vis_fpmerge(vis_rebd_lo(sdm), vis_rebd_lo(sdk));     \
}

/***************************************************************/

void ADD_SUFF(ThreeByteBgrToIntBgrConvert)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 *sp;
    mlib_d64 sFF;
    mlib_d64 s0, s1, s2, s3, sd0, sd1, sd2, dd0, dd1, dd2, dd3;
    mlib_s32 i, i0, j;

    if (width < 16) {
        for (j = 0; j < height; j++) {
            mlib_u8  *src = srcBbse;
            mlib_u32 *dst = dstBbse;

            for (i = 0; i < width; i++) {
                dst[i] = READ_Bgr(i);
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
        return;
    }

    if (srcScbn == 3*width && dstScbn == 4*width) {
        width *= height;
        height = 1;
    }

    sFF = vis_fzero();

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_f32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            ((mlib_s32*)dst)[i] = READ_Bgr(i);
            i0 = 1;
        }

        sp = vis_blignbddr(src, 3*i0);
        s3 = *sp++;

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 8; i += 8) {
            s0 = s3;
            s1 = *sp++;
            s2 = *sp++;
            s3 = *sp++;
            sd0 = vis_fbligndbtb(s0, s1);
            sd1 = vis_fbligndbtb(s1, s2);
            sd2 = vis_fbligndbtb(s2, s3);

            INSERT_U8_34R

            *(mlib_d64*)(dst + i    ) = dd0;
            *(mlib_d64*)(dst + i + 2) = dd1;
            *(mlib_d64*)(dst + i + 4) = dd2;
            *(mlib_d64*)(dst + i + 6) = dd3;
        }

        for (; i < width; i++) {
            ((mlib_s32*)dst)[i] = READ_Bgr(i);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ThreeByteBgrToIntBgrScbleConvert)(SCALE_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, dzero;
    mlib_s32 i, i0, i1, j;

    if (width < 16) {
        for (j = 0; j < height; j++) {
            mlib_u8  *src = srcBbse;
            mlib_s32 *dst = dstBbse;
            mlib_s32 *dst_end = dst + width;
            mlib_s32 tmpsxloc = sxloc;

            PTR_ADD(src, (syloc >> shift) * srcScbn);

            for (; dst < dst_end; dst++) {
                i = tmpsxloc >> shift;
                tmpsxloc += sxinc;
                *(mlib_s32*)dst = READ_Bgr(i);
            }

            PTR_ADD(dstBbse, dstScbn);
            syloc += syinc;
        }
        return;
    }

    dzero = vis_fzero();

    vis_blignbddr(NULL, 7);

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_f32 *dst = dstBbse;
        mlib_f32 *dst_end = dst + width;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 7) {
            i = tmpsxloc >> shift;
            tmpsxloc += sxinc;
            *(mlib_s32*)dst = READ_Bgr(i);
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= dst_end - 2; dst += 2) {
            i0 = tmpsxloc >> shift;
            i1 = (tmpsxloc + sxinc) >> shift;
            tmpsxloc += 2*sxinc;

            dd = vis_fbligndbtb(vis_ld_u8(src + 3*i1 + 2), dd);
            dd = vis_fbligndbtb(vis_ld_u8(src + 3*i1 + 1), dd);
            dd = vis_fbligndbtb(vis_ld_u8(src + 3*i1    ), dd);
            dd = vis_fbligndbtb(dzero, dd);
            dd = vis_fbligndbtb(vis_ld_u8(src + 3*i0 + 2), dd);
            dd = vis_fbligndbtb(vis_ld_u8(src + 3*i0 + 1), dd);
            dd = vis_fbligndbtb(vis_ld_u8(src + 3*i0    ), dd);
            dd = vis_fbligndbtb(dzero, dd);

            *(mlib_d64*)dst = dd;
        }

        for (; dst < dst_end; dst++) {
            i = tmpsxloc >> shift;
            tmpsxloc += sxinc;
            *(mlib_s32*)dst = READ_Bgr(i);
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbBmToIntBgrXpbrOver)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd;
    mlib_s32 i, i0, j, mbsk, x;

    if (dstScbn == 4*width && srcScbn == 4*width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        mlib_s32 *src = srcBbse;
        mlib_s32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            if (*(mlib_u8*)(src + i)) {
                x = src[i];
                dst[i] = ARGB_to_GBGR(x);
            }
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            ARGB_to_GBGR_FL2(dd, ((mlib_f32*)src)[i], ((mlib_f32*)src)[i + 1]);
            mbsk = (((-*(mlib_u8*)(src + i)) >> 31) & 2) |
                   (((-*(mlib_u8*)(src + i + 1)) >> 31) & 1);
            vis_pst_32(dd, dst + i, mbsk);
        }

        if (i < width) {
            if (*(mlib_u8*)(src + i)) {
                x = src[i];
                dst[i] = ARGB_to_GBGR(x);
            }
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbBmToIntBgrScbleXpbrOver)(SCALE_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd;
    mlib_s32 j, mbsk;

    for (j = 0; j < height; j++) {
        mlib_s32 *src = srcBbse;
        mlib_s32 *dst = dstBbse;
        mlib_s32 *dst_end = dst + width;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 7) {
            mlib_s32 *pp = src + (tmpsxloc >> shift);
            if (*(mlib_u8*)pp) {
                *dst = ARGB_to_GBGR(*pp);
            }
            dst++;
            tmpsxloc += sxinc;
        }

#prbgmb pipeloop(0)
        for (; dst <= dst_end - 2; dst += 2) {
            mlib_s32 *pp0 = src + (tmpsxloc >> shift);
            mlib_s32 *pp1 = src + ((tmpsxloc + sxinc) >> shift);
            ARGB_to_GBGR_FL2(dd, *(mlib_f32*)pp0, *(mlib_f32*)pp1);
            mbsk = (((-*(mlib_u8*)pp0) >> 31) & 2) |
                   ((mlib_u32)(-*(mlib_u8*)pp1) >> 31);
            vis_pst_32(dd, dst, mbsk);
            tmpsxloc += 2*sxinc;
        }

        for (; dst < dst_end; dst++) {
            mlib_s32 *pp = src + (tmpsxloc >> shift);
            if (*(mlib_u8*)pp) {
                *dst = ARGB_to_GBGR(*pp);
            }
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbBmToIntBgrXpbrBgCopy)(BCOPY_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, d_bgpixel;
    mlib_s32 i, i0, j, mbsk;

    if (dstScbn == 4*width && srcScbn == 4*width) {
        width *= height;
        height = 1;
    }

    vis_blignbddr(NULL, 1);
    d_bgpixel = vis_to_double_dup(bgpixel);

    for (j = 0; j < height; j++) {
        mlib_s32 *src = srcBbse;
        mlib_s32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            if (*(mlib_u8*)(src + i)) {
                dst[i] = ARGB_to_GBGR(src[i]);
            } else {
                dst[i] = bgpixel;
            }
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            ARGB_to_GBGR_FL2(dd, ((mlib_f32*)src)[i], ((mlib_f32*)src)[i + 1]);
            mbsk = (((-*(mlib_u8*)(src + i)) >> 31) & 2) |
                   (((-*(mlib_u8*)(src + i + 1)) >> 31) & 1);
            *(mlib_d64*)(dst + i) = d_bgpixel;
            vis_pst_32(dd, dst + i, mbsk);
        }

        if (i < width) {
            if (*(mlib_u8*)(src + i)) {
                dst[i] = ARGB_to_GBGR(src[i]);
            } else {
                dst[i] = bgpixel;
            }
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedToIntBgrConvert)(BLIT_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd;
    mlib_s32 i, i0, j, x;

    if (srcScbn == width && dstScbn == 4*width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            x = pixLut[src[i]];
            dst[i] = ARGB_to_GBGR(x);
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            ARGB_to_GBGR_FL2(dd, ((mlib_f32*)pixLut)[src[i]],
                                 ((mlib_f32*)pixLut)[src[i + 1]]);
            *(mlib_d64*)(dst + i) = dd;
        }

        for (; i < width; i++) {
            x = pixLut[src[i]];
            dst[i] = ARGB_to_GBGR(x);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedToIntBgrScbleConvert)(SCALE_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd;
    mlib_s32 j, x;

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;
        mlib_s32 *dst_end = dst + width;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 7) {
            x = pixLut[src[tmpsxloc >> shift]];
            *dst++ = ARGB_to_GBGR(x);
            tmpsxloc += sxinc;
        }

#prbgmb pipeloop(0)
        for (; dst <= dst_end - 2; dst += 2) {
            mlib_f32 f0 = ((mlib_f32*)pixLut)[src[tmpsxloc >> shift]];
            mlib_f32 f1 = ((mlib_f32*)pixLut)[src[(tmpsxloc + sxinc) >> shift]];
            ARGB_to_GBGR_FL2(dd, f0, f1);
            *(mlib_d64*)dst = dd;
            tmpsxloc += 2*sxinc;
        }

        for (; dst < dst_end; dst++) {
            x = pixLut[src[tmpsxloc >> shift]];
            *dst++ = ARGB_to_GBGR(x);
            tmpsxloc += sxinc;
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedBmToIntBgrXpbrOver)(BLIT_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd;
    mlib_s32 i, i0, j, x, mbsk;

    if (srcScbn == width && dstScbn == 4*width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            x = pixLut[src[i]];
            if (x < 0) {
                dst[i] = ARGB_to_BGR(x);
            }
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            mlib_f32 *pp0 = (mlib_f32*)pixLut + src[i];
            mlib_f32 *pp1 = (mlib_f32*)pixLut + src[i + 1];
            ARGB_to_BGR_FL2(dd, *pp0, *pp1);
            mbsk = (((*(mlib_u8*)pp0) >> 6) & 2) | ((*(mlib_u8*)pp1) >> 7);
            vis_pst_32(dd, dst + i, mbsk);
        }

        for (; i < width; i++) {
            x = pixLut[src[i]];
            if (x < 0) {
                dst[i] = ARGB_to_BGR(x);
            }
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedBmToIntBgrScbleXpbrOver)(SCALE_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd;
    mlib_s32 j, x, mbsk;

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;
        mlib_s32 *dst_end = dst + width;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 7) {
            x = pixLut[src[tmpsxloc >> shift]];
            tmpsxloc += sxinc;
            if (x < 0) {
                *dst = ARGB_to_BGR(x);
            }
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= dst_end - 2; dst += 2) {
            mlib_f32 *p0 = (mlib_f32*)pixLut + src[tmpsxloc >> shift];
            mlib_f32 *p1 = (mlib_f32*)pixLut + src[(tmpsxloc + sxinc) >> shift];
            ARGB_to_BGR_FL2(dd, *p0, *p1);
            mbsk = (((*(mlib_u8*)p0) >> 6) & 2) | ((*(mlib_u8*)p1) >> 7);
            tmpsxloc += 2*sxinc;
            vis_pst_32(dd, dst, mbsk);
        }

        for (; dst < dst_end; dst++) {
            x = pixLut[src[tmpsxloc >> shift]];
            tmpsxloc += sxinc;
            if (x < 0) {
                *dst = ARGB_to_BGR(x);
            }
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedBmToIntBgrXpbrBgCopy)(BCOPY_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, d_bgpixel;
    mlib_s32 j, x, mbsk;

    if (srcScbn == width && dstScbn == 4*width) {
        width *= height;
        height = 1;
    }

    d_bgpixel = vis_to_double_dup(bgpixel);

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;
        mlib_s32 *dst_end;

        dst_end = dst + width;

        if ((mlib_s32)dst & 7) {
            x = pixLut[*src++];
            if (x < 0) {
                *dst = ARGB_to_GBGR(x);
            } else {
                *dst = bgpixel;
            }
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            mlib_f32 *pp0 = (mlib_f32*)pixLut + src[0];
            mlib_f32 *pp1 = (mlib_f32*)pixLut + src[1];
            ARGB_to_GBGR_FL2(dd, *pp0, *pp1);
            mbsk = (((*(mlib_u8*)pp0) >> 6) & 2) | ((*(mlib_u8*)pp1) >> 7);
            *(mlib_d64*)dst = d_bgpixel;
            vis_pst_32(dd, dst, mbsk);
            src += 2;
        }

        while (dst < dst_end) {
            x = pixLut[*src++];
            if (x < 0) {
                *dst = ARGB_to_GBGR(x);
            } else {
                *dst = bgpixel;
            }
            dst++;
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntBgrDrbwGlyphListAA)(GLYPH_LIST_PARAMS)
{
    mlib_s32 glyphCounter;
    mlib_s32 scbn = pRbsInfo->scbnStride;
    mlib_u8  *dstBbse;
    mlib_s32 j;
    mlib_d64 dmix0, dmix1, dd, d0, d1, e0, e1, fgpixel_d;
    mlib_d64 done, done16, d_hblf, mbskRGB, dzero;
    mlib_s32 pix, mbsk, mbsk_z;
    mlib_f32 srcG_f;

    done = vis_to_double_dup(0x7fff7fff);
    done16 = vis_to_double_dup(0x7fff);
    d_hblf = vis_to_double_dup((1 << (16 + 6)) | (1 << 6));

    fgpixel_d = vis_to_double_dup(fgpixel);
    srcG_f = vis_to_flobt(brgbcolor);
    mbskRGB = vis_to_double_dup(0xffffff);
    dzero = vis_fzero();

    ARGB2ABGR_FL(srcG_f)

    vis_write_gsr(0 << 3);

    for (glyphCounter = 0; glyphCounter < totblGlyphs; glyphCounter++) {
        const jubyte *pixels;
        unsigned int rowBytes;
        int left, top;
        int width, height;
        int right, bottom;

        pixels = (const jubyte *) glyphs[glyphCounter].pixels;

        if (!pixels) continue;

        left = glyphs[glyphCounter].x;
        top = glyphs[glyphCounter].y;
        width = glyphs[glyphCounter].width;
        height = glyphs[glyphCounter].height;
        rowBytes = width;
        right = left + width;
        bottom = top + height;
        if (left < clipLeft) {
            pixels += clipLeft - left;
            left = clipLeft;
        }
        if (top < clipTop) {
            pixels += (clipTop - top) * rowBytes;
            top = clipTop;
        }
        if (right > clipRight) {
            right = clipRight;
        }
        if (bottom > clipBottom) {
            bottom = clipBottom;
        }
        if (right <= left || bottom <= top) {
            continue;
        }
        width = right - left;
        height = bottom - top;

        dstBbse = pRbsInfo->rbsBbse;
        PTR_ADD(dstBbse, top*scbn + 4*left);

        for (j = 0; j < height; j++) {
            mlib_u8  *src = (void*)pixels;
            mlib_s32 *dst, *dst_end;

            dst = (void*)dstBbse;
            dst_end = dst + width;

            if ((mlib_s32)dst & 7) {
                pix = *src++;
                if (pix) {
                    dd = vis_fpbdd16(MUL8_VIS(srcG_f, pix), d_hblf);
                    dd = vis_fpbdd16(MUL8_VIS(*(mlib_f32*)dst, 255 - pix), dd);
                    *(mlib_f32*)dst = vis_fbnds(vis_fpbck16(dd),
                                                vis_rebd_hi(mbskRGB));
                    if (pix == 255) *(mlib_f32*)dst = vis_rebd_hi(fgpixel_d);
                }
                dst++;
            }

#prbgmb pipeloop(0)
            for (; dst <= (dst_end - 2); dst += 2) {
                dmix0 = vis_freg_pbir(((mlib_f32 *)vis_mul8s_tbl)[src[0]],
                                      ((mlib_f32 *)vis_mul8s_tbl)[src[1]]);
                mbsk = vis_fcmplt32(dmix0, done16);
                mbsk_z = vis_fcmpne32(dmix0, dzero);
                dmix1 = vis_fpsub16(done, dmix0);
                src += 2;

                dd = *(mlib_d64*)dst;
                d0 = vis_fmul8x16bl(srcG_f, vis_rebd_hi(dmix0));
                d1 = vis_fmul8x16bl(srcG_f, vis_rebd_lo(dmix0));
                e0 = vis_fmul8x16bl(vis_rebd_hi(dd), vis_rebd_hi(dmix1));
                e1 = vis_fmul8x16bl(vis_rebd_lo(dd), vis_rebd_lo(dmix1));
                d0 = vis_fpbdd16(vis_fpbdd16(d0, d_hblf), e0);
                d1 = vis_fpbdd16(vis_fpbdd16(d1, d_hblf), e1);
                dd = vis_fpbck16_pbir(d0, d1);
                dd = vis_fbnd(dd, mbskRGB);

                vis_pst_32(fgpixel_d, dst, mbsk_z);
                vis_pst_32(dd, dst, mbsk & mbsk_z);
            }

            while (dst < dst_end) {
                pix = *src++;
                if (pix) {
                    dd = vis_fpbdd16(MUL8_VIS(srcG_f, pix), d_hblf);
                    dd = vis_fpbdd16(MUL8_VIS(*(mlib_f32*)dst, 255 - pix), dd);
                    *(mlib_f32*)dst = vis_fbnds(vis_fpbck16(dd),
                                                vis_rebd_hi(mbskRGB));
                    if (pix == 255) *(mlib_f32*)dst = vis_rebd_hi(fgpixel_d);
                }
                dst++;
            }

            PTR_ADD(dstBbse, scbn);
            pixels += rowBytes;
        }
    }
}

/***************************************************************/

#endif /* JAVA2D_NO_MLIB */
