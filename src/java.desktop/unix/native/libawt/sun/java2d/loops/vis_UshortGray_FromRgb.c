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
#include "vis_AlphbMbcros.h"

/***************************************************************/

extern mlib_d64 vis_d64_div_tbl[256];

/***************************************************************/

#define RGB2GRAY(r, g, b)      \
    (((19672 * (r)) + (38621 * (g)) + (7500 * (b))) >> 8)

/***************************************************************/

stbtic const mlib_s32 RGB_weight[] = {
    (19672/2) | ((19672/2) << 16),
    (38621/2) | ((38621/2) << 16),
    ( 7500/2) | (( 7500/2) << 16),
    /*(1 << 6)*/ - (1 << 22)
};

/***************************************************************/

#define RGB_VARS                                               \
    mlib_d64 r, g, b, br, gb, s02, s13;                        \
    mlib_f32 ff;                                               \
    mlib_f32 blphb = ((mlib_f32*)RGB_weight)[0];               \
    mlib_f32 betb  = ((mlib_f32*)RGB_weight)[1];               \
    mlib_f32 gbmmb = ((mlib_f32*)RGB_weight)[2];               \
    mlib_f32 fzeros = vis_fzeros();                            \
    mlib_d64 d_hblf = vis_to_double_dup(RGB_weight[3]);        \
    mlib_f32 mbsk8000 = vis_to_flobt(0x80008000);              \
                                                               \
    vis_write_gsr(((16 - 7) << 3) | 6)

/***************************************************************/

#define GRAY_U16(ff, r, g, b)          \
{                                      \
    mlib_d64 dr, dg, db;               \
    dr = vis_fmuld8ulx16(r, blphb);    \
    dg = vis_fmuld8ulx16(g, betb);     \
    db = vis_fmuld8ulx16(b, gbmmb);    \
    dr = vis_fpbdd32(dr, dg);          \
    db = vis_fpbdd32(db, d_hblf);      \
    dr = vis_fpbdd32(dr, db);          \
    ff = vis_fpbckfix(dr);             \
    ff = vis_fxors(ff, mbsk8000);      \
}

/***************************************************************/

#define LOAD_BGR(ind)                                          \
    b = vis_fbligndbtb(vis_ld_u8(src + (ind    )), b);         \
    g = vis_fbligndbtb(vis_ld_u8(src + (ind + 1)), g);         \
    r = vis_fbligndbtb(vis_ld_u8(src + (ind + 2)), r)

/***************************************************************/

void ADD_SUFF(IntArgbToUshortGrbyConvert)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 j;
    RGB_VARS;

    if (srcScbn == 4*width && dstScbn == 2*width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        mlib_f32 *src = srcBbse;
        mlib_u16 *dst = dstBbse;
        mlib_u16 *dst_end;

        dst_end = dst + width;

        while (((mlib_s32)dst & 3) && dst < dst_end) {
            r = vis_ld_u8((mlib_u8*)src + 1);
            g = vis_ld_u8((mlib_u8*)src + 2);
            b = vis_ld_u8((mlib_u8*)src + 3);
            GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
            src++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            s02 = vis_fpmerge(src[0], src[1]);
            br = vis_fpmerge(fzeros, vis_rebd_hi(s02));
            gb = vis_fpmerge(fzeros, vis_rebd_lo(s02));
            GRAY_U16(ff, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));
            *(mlib_f32*)dst = ff;
            src += 2;
        }

        while (dst < dst_end) {
            r = vis_ld_u8((mlib_u8*)src + 1);
            g = vis_ld_u8((mlib_u8*)src + 2);
            b = vis_ld_u8((mlib_u8*)src + 3);
            GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
            src++;
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ThreeByteBgrToUshortGrbyConvert)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_u16 *dst_end;
    mlib_s32 j;
    RGB_VARS;

    if (srcScbn == 3*width && dstScbn == 2*width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_u16 *dst = dstBbse;

        dst_end = dst + width;

        while (((mlib_s32)dst & 3) && dst < dst_end) {
            b = vis_ld_u8(src);
            g = vis_ld_u8(src + 1);
            r = vis_ld_u8(src + 2);
            GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
            src += 3;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            LOAD_BGR(3);
            LOAD_BGR(0);
            GRAY_U16(ff, vis_rebd_hi(r), vis_rebd_hi(g), vis_rebd_hi(b));
            *(mlib_f32*)dst = ff;
            src += 3*2;
        }

        while (dst < dst_end) {
            b = vis_ld_u8(src);
            g = vis_ld_u8(src + 1);
            r = vis_ld_u8(src + 2);
            GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
            src += 3;
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToUshortGrbyScbleConvert)(SCALE_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_u16 *dst_end;
    mlib_s32 i, j;
    RGB_VARS;

    for (j = 0; j < height; j++) {
        mlib_f32 *src = srcBbse;
        mlib_u16 *dst = dstBbse;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        dst_end = dst + width;

        while (((mlib_s32)dst & 3) && dst < dst_end) {
            i = tmpsxloc >> shift;
            tmpsxloc += sxinc;
            r = vis_ld_u8((mlib_u8*)(src + i) + 1);
            g = vis_ld_u8((mlib_u8*)(src + i) + 2);
            b = vis_ld_u8((mlib_u8*)(src + i) + 3);
            GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            s02 = vis_fpmerge(src[(tmpsxloc        ) >> shift],
                              src[(tmpsxloc + sxinc) >> shift]);
            tmpsxloc += 2*sxinc;
            br = vis_fpmerge(fzeros, vis_rebd_hi(s02));
            gb = vis_fpmerge(fzeros, vis_rebd_lo(s02));
            GRAY_U16(ff, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));
            *(mlib_f32*)dst = ff;
        }

        while (dst < dst_end) {
            i = tmpsxloc >> shift;
            tmpsxloc += sxinc;
            r = vis_ld_u8((mlib_u8*)(src + i) + 1);
            g = vis_ld_u8((mlib_u8*)(src + i) + 2);
            b = vis_ld_u8((mlib_u8*)(src + i) + 3);
            GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(ThreeByteBgrToUshortGrbyScbleConvert)(SCALE_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_u16 *dst_end;
    mlib_s32 j, i0, i1;
    RGB_VARS;

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_u16 *dst = dstBbse;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        dst_end = dst + width;

        while (((mlib_s32)dst & 3) && dst < dst_end) {
            i0 = 3*(tmpsxloc >> shift);
            tmpsxloc += sxinc;
            b = vis_ld_u8(src + i0);
            g = vis_ld_u8(src + i0 + 1);
            r = vis_ld_u8(src + i0 + 2);
            GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            i0 = 3*(tmpsxloc >> shift);
            tmpsxloc += sxinc;
            i1 = 3*(tmpsxloc >> shift);
            tmpsxloc += sxinc;
            LOAD_BGR(i1);
            LOAD_BGR(i0);
            GRAY_U16(ff, vis_rebd_hi(r), vis_rebd_hi(g), vis_rebd_hi(b));
            *(mlib_f32*)dst = ff;
        }

        while (dst < dst_end) {
            i0 = 3*(tmpsxloc >> shift);
            tmpsxloc += sxinc;
            b = vis_ld_u8(src + i0);
            g = vis_ld_u8(src + i0 + 1);
            r = vis_ld_u8(src + i0 + 2);
            GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

#if 0

void ADD_SUFF(IntArgbBmToUshortGrbyXpbrOver)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dzero = vis_fzero();
    mlib_f32 f0, f1;
    mlib_s32 i, j, mbsk0, mbsk1;
    RGB_VARS;

    if (width < 8) {
        for (j = 0; j < height; j++) {
            mlib_u8  *src = srcBbse;
            mlib_u16 *dst = dstBbse;

            for (i = 0; i < width; i++) {
                if (src[4*i]) {
                    dst[i] = RGB2GRAY(src[4*i + 1], src[4*i + 2], src[4*i + 3]);
                }
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
        return;
    }

    for (j = 0; j < height; j++) {
        mlib_f32 *src = srcBbse;
        mlib_u16 *dst = dstBbse;
        mlib_u16 *dst_end;

        dst_end = dst + width;

        while (((mlib_s32)dst & 7) && dst < dst_end) {
            if (*(mlib_u8*)src) {
                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                vis_st_u16(D64_FROM_F32x2(ff), dst);
            }
            dst++;
            src++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 4); dst += 4) {
            s02 = vis_fpmerge(src[0], src[1]);
            src += 2;
            br = vis_fpmerge(fzeros, vis_rebd_hi(s02));
            gb = vis_fpmerge(fzeros, vis_rebd_lo(s02));
            mbsk0 = vis_fcmpne16(br, dzero) & 0xC;
            GRAY_U16(f0, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));

            s02 = vis_fpmerge(src[0], src[1]);
            src += 2;
            br = vis_fpmerge(fzeros, vis_rebd_hi(s02));
            gb = vis_fpmerge(fzeros, vis_rebd_lo(s02));
            mbsk1 = vis_fcmpne16(br, dzero) >> 2;
            GRAY_U16(f1, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));

            vis_pst_16(vis_freg_pbir(f0, f1), dst, mbsk0 | mbsk1);
        }

        while (dst < dst_end) {
            if (*(mlib_u8*)src) {
                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                vis_st_u16(D64_FROM_F32x2(ff), dst);
            }
            dst++;
            src++;
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbBmToUshortGrbyXpbrBgCopy)(BCOPY_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dzero = vis_fzero(), d_bgpixel;
    mlib_f32 f0, f1;
    mlib_s32 i, j, mbsk0, mbsk1;
    RGB_VARS;

    if (width < 8) {
        for (j = 0; j < height; j++) {
            mlib_u8  *src = srcBbse;
            mlib_u16 *dst = dstBbse;
            mlib_s32 srcpixel, r, g, b;

            for (i = 0; i < width; i++) {
                if (src[4*i]) {
                    dst[i] = RGB2GRAY(src[4*i + 1], src[4*i + 2], src[4*i + 3]);
                } else {
                    dst[i] = bgpixel;
                }
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
        return;
    }

    D64_FROM_U16x4(d_bgpixel, bgpixel);

    for (j = 0; j < height; j++) {
        mlib_f32 *src = srcBbse;
        mlib_u16 *dst = dstBbse;
        mlib_u16 *dst_end;

        dst_end = dst + width;

        while (((mlib_s32)dst & 7) && dst < dst_end) {
            if (*(mlib_u8*)src) {
                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                vis_st_u16(D64_FROM_F32x2(ff), dst);
            } else {
                *dst = bgpixel;
            }
            dst++;
            src++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 4); dst += 4) {
            s02 = vis_fpmerge(src[0], src[1]);
            src += 2;
            br = vis_fpmerge(fzeros, vis_rebd_hi(s02));
            gb = vis_fpmerge(fzeros, vis_rebd_lo(s02));
            mbsk0 = vis_fcmpne16(br, dzero) & 0xC;
            GRAY_U16(f0, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));

            s02 = vis_fpmerge(src[0], src[1]);
            src += 2;
            br = vis_fpmerge(fzeros, vis_rebd_hi(s02));
            gb = vis_fpmerge(fzeros, vis_rebd_lo(s02));
            mbsk1 = vis_fcmpne16(br, dzero) >> 2;
            GRAY_U16(f1, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));

            *(mlib_d64*)dst = d_bgpixel;
            vis_pst_16(vis_freg_pbir(f0, f1), dst, mbsk0 | mbsk1);
        }

        while (dst < dst_end) {
            if (*(mlib_u8*)src) {
                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                vis_st_u16(D64_FROM_F32x2(ff), dst);
            } else {
                *dst = bgpixel;
            }
            dst++;
            src++;
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

#endif

/***************************************************************/

void ADD_SUFF(IntArgbToUshortGrbyXorBlit)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, d_xorpixel, d_blphbmbsk, dzero = vis_fzero();
    mlib_f32 f0, f1;
    mlib_s32 i, j, mbsk0, mbsk1;
    jint  xorpixel = pCompInfo->detbils.xorPixel;
    juint blphbmbsk = pCompInfo->blphbMbsk;
    RGB_VARS;

    if (width < 8) {
        for (j = 0; j < height; j++) {
            mlib_s32 *src = srcBbse;
            mlib_u16 *dst = dstBbse;
            mlib_s32 srcpixel, r, g, b;

            for (i = 0; i < width; i++) {
                srcpixel = src[i];
                if (srcpixel >= 0) continue;
                b = (srcpixel) & 0xff;
                g = (srcpixel >> 8) & 0xff;
                r = (srcpixel >> 16) & 0xff;
                srcpixel = (77*r + 150*g + 29*b + 128) / 256;
                dst[i]  ^= (((srcpixel) ^ (xorpixel)) & ~(blphbmbsk));
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
        return;
    }

    D64_FROM_U16x4(d_xorpixel,  xorpixel);
    D64_FROM_U16x4(d_blphbmbsk, blphbmbsk);

    for (j = 0; j < height; j++) {
        mlib_f32 *src = srcBbse;
        mlib_u16 *dst = dstBbse;
        mlib_u16 *dst_end;

        dst_end = dst + width;

        while (((mlib_s32)dst & 7) && dst < dst_end) {
            if ((*(mlib_u8*)src) & 0x80) {
                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                dd = vis_fxor(D64_FROM_F32x2(ff), d_xorpixel);
                dd = vis_fbndnot(d_blphbmbsk, dd);
                vis_st_u16(vis_fxor(vis_ld_u8(dst), dd), dst);
            }
            dst++;
            src++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 8); dst += 8) {
            s02 = vis_fpmerge(src[0], src[1]);
            src += 2;
            br = vis_fpmerge(fzeros, vis_rebd_hi(s02));
            gb = vis_fpmerge(fzeros, vis_rebd_lo(s02));
            mbsk0 = vis_fcmplt16(br, dzero) & 0xC;
            GRAY_U16(f0, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));

            s02 = vis_fpmerge(src[0], src[1]);
            src += 2;
            br = vis_fpmerge(fzeros, vis_rebd_hi(s02));
            gb = vis_fpmerge(fzeros, vis_rebd_lo(s02));
            mbsk1 = vis_fcmplt16(br, dzero) >> 2;
            GRAY_U16(f1, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));

            dd = vis_freg_pbir(f0, f1);
            dd = vis_fbndnot(d_blphbmbsk, vis_fxor(dd, d_xorpixel));
            vis_pst_16(vis_fxor(*(mlib_d64*)dst, dd), dst, mbsk0 | mbsk1);
        }

        while (dst < dst_end) {
            if ((*(mlib_u8*)src) & 0x80) {
                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                dd = vis_fxor(D64_FROM_F32x2(ff), d_xorpixel);
                dd = vis_fbndnot(d_blphbmbsk, dd);
                vis_st_u16(vis_fxor(vis_ld_u8(dst), dd), dst);
            }
            dst++;
            src++;
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbBmToUshortGrbyScbleXpbrOver)(SCALE_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dzero = vis_fzero();
    mlib_f32 f0, f1;
    mlib_s32 i, j, mbsk0, mbsk1;
    RGB_VARS;

    for (j = 0; j < height; j++) {
        mlib_f32 *src = srcBbse;
        mlib_u16 *dst = dstBbse;
        mlib_u16 *dst_end;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        dst_end = dst + width;

        while (((mlib_s32)dst & 7) && dst < dst_end) {
            i = tmpsxloc >> shift;
            tmpsxloc += sxinc;
            if (*(mlib_u8*)(src + i)) {
                r = vis_ld_u8((mlib_u8*)(src + i) + 1);
                g = vis_ld_u8((mlib_u8*)(src + i) + 2);
                b = vis_ld_u8((mlib_u8*)(src + i) + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                vis_st_u16(D64_FROM_F32x2(ff), dst);
            }
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 4); dst += 4) {
            s02 = vis_fpmerge(src[(tmpsxloc        ) >> shift],
                              src[(tmpsxloc + sxinc) >> shift]);
            tmpsxloc += 2*sxinc;
            br = vis_fpmerge(fzeros, vis_rebd_hi(s02));
            gb = vis_fpmerge(fzeros, vis_rebd_lo(s02));
            mbsk0 = vis_fcmpne16(br, dzero) & 0xC;
            GRAY_U16(f0, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));

            s02 = vis_fpmerge(src[(tmpsxloc        ) >> shift],
                              src[(tmpsxloc + sxinc) >> shift]);
            tmpsxloc += 2*sxinc;
            br = vis_fpmerge(fzeros, vis_rebd_hi(s02));
            gb = vis_fpmerge(fzeros, vis_rebd_lo(s02));
            mbsk1 = vis_fcmpne16(br, dzero) >> 2;
            GRAY_U16(f1, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));

            vis_pst_16(vis_freg_pbir(f0, f1), dst, mbsk0 | mbsk1);
        }

        while (dst < dst_end) {
            i = tmpsxloc >> shift;
            tmpsxloc += sxinc;
            if (*(mlib_u8*)(src + i)) {
                r = vis_ld_u8((mlib_u8*)(src + i) + 1);
                g = vis_ld_u8((mlib_u8*)(src + i) + 2);
                b = vis_ld_u8((mlib_u8*)(src + i) + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                vis_st_u16(D64_FROM_F32x2(ff), dst);
            }
            dst++;
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

#define TBL_MUL ((mlib_s16*)vis_mul8s_tbl + 1)
#define TBL_DIV ((mlib_u8*)vis_div8_tbl + 2)

void ADD_SUFF(IntArgbToUshortGrbySrcOverMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 extrbA;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_u8  *mul8_extrb;
    mlib_u16 *dst_end;
    mlib_d64 srcAx4, dd, d0, d1;
    mlib_d64 done = vis_to_double_dup(0x7fff7fff);
    mlib_s32 j, srcA0, srcA1, srcA2, srcA3;
    RGB_VARS;

    extrbA = (mlib_s32)(pCompInfo->detbils.extrbAlphb * 255.0 + 0.5);
    mul8_extrb = mul8tbble[extrbA];

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (srcScbn == 4*width && dstScbn == 2*width && mbskScbn == width) {
            width *= height;
            height = 1;
        }

        mbskScbn -= width;

        for (j = 0; j < height; j++) {
            mlib_f32 *src = srcBbse;
            mlib_u16 *dst = dstBbse;

            dst_end = dst + width;

            while (((mlib_s32)dst & 3) && dst < dst_end) {
                srcA0 = mul8tbble[mul8_extrb[*pMbsk++]][*(mlib_u8*)src];
                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                d0 = vis_fpbdd16(MUL8_VIS(ff, srcA0), d_hblf);
                d1 = MUL8_VIS(vis_rebd_lo(vis_ld_u8(dst)), 255 - srcA0);
                dd = vis_fpbdd16(d0, d1);
                vis_st_u16(D64_FROM_F32x2(vis_fpbck16(dd)), dst);
                dst++;
                src++;
            }

#prbgmb pipeloop(0)
            for (; dst <= (dst_end - 4); dst += 4) {
                srcA0 = mul8tbble[mul8_extrb[*pMbsk++]][*(mlib_u8*)src];
                srcA1 = mul8tbble[mul8_extrb[*pMbsk++]][*(mlib_u8*)(src + 1)];
                srcA2 = mul8tbble[mul8_extrb[*pMbsk++]][*(mlib_u8*)(src + 2)];
                srcA3 = mul8tbble[mul8_extrb[*pMbsk++]][*(mlib_u8*)(src + 3)];
                srcAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srcA3), srcAx4);
                srcAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srcA2), srcAx4);
                srcAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srcA1), srcAx4);
                srcAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srcA0), srcAx4);

                s02 = vis_fpmerge(src[0], src[1]);
                br = vis_fpmerge(fzeros, vis_rebd_hi(s02));
                gb = vis_fpmerge(fzeros, vis_rebd_lo(s02));
                GRAY_U16(ff, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));
                d0 = vis_fpbdd16(vis_fmul8x16(ff, srcAx4), d_hblf);
                d1 = vis_fmul8x16(*(mlib_f32*)dst, vis_fpsub16(done, srcAx4));
                dd = vis_fpbdd16(d0, d1);
                *(mlib_f32*)dst = vis_fpbck16(dd);
                src += 4;
            }

            while (dst < dst_end) {
                srcA0 = mul8tbble[mul8_extrb[*pMbsk++]][*(mlib_u8*)src];
                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                d0 = vis_fpbdd16(MUL8_VIS(ff, srcA0), d_hblf);
                d1 = MUL8_VIS(vis_rebd_lo(vis_ld_u8(dst)), 255 - srcA0);
                dd = vis_fpbdd16(d0, d1);
                vis_st_u16(D64_FROM_F32x2(vis_fpbck16(dd)), dst);
                dst++;
                src++;
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
            PTR_ADD(pMbsk,  mbskScbn);
        }
    } else {

        if (dstScbn == width && srcScbn == 4*width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            mlib_f32 *src = srcBbse;
            mlib_u16 *dst = dstBbse;

            dst_end = dst + width;

            while (((mlib_s32)dst & 3) && dst < dst_end) {
                srcA0 = mul8_extrb[*(mlib_u8*)src];
                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                d0 = vis_fpbdd16(MUL8_VIS(ff, srcA0), d_hblf);
                d1 = MUL8_VIS(vis_rebd_lo(vis_ld_u8(dst)), 255 - srcA0);
                dd = vis_fpbdd16(d0, d1);
                vis_st_u16(D64_FROM_F32x2(vis_fpbck16(dd)), dst);
                dst++;
                src++;
            }

#prbgmb pipeloop(0)
            for (; dst <= (dst_end - 4); dst += 4) {
                srcA0 = mul8_extrb[*(mlib_u8*)src];
                srcA1 = mul8_extrb[*(mlib_u8*)(src + 1)];
                srcA2 = mul8_extrb[*(mlib_u8*)(src + 2)];
                srcA3 = mul8_extrb[*(mlib_u8*)(src + 3)];
                srcAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srcA3), srcAx4);
                srcAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srcA2), srcAx4);
                srcAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srcA1), srcAx4);
                srcAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srcA0), srcAx4);

                s02 = vis_fpmerge(src[0], src[2]);
                s13 = vis_fpmerge(src[1], src[3]);
                br = vis_fpmerge(vis_rebd_hi(s02), vis_rebd_hi(s13));
                gb = vis_fpmerge(vis_rebd_lo(s02), vis_rebd_lo(s13));
                GRAY_U16(ff, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));
                d0 = vis_fpbdd16(vis_fmul8x16(ff, srcAx4), d_hblf);
                d1 = vis_fmul8x16(*(mlib_f32*)dst, vis_fpsub16(done, srcAx4));
                dd = vis_fpbdd16(d0, d1);
                *(mlib_f32*)dst = vis_fpbck16(dd);
                src += 4;
            }

            while (dst < dst_end) {
                srcA0 = mul8_extrb[*(mlib_u8*)src];
                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                d0 = vis_fpbdd16(MUL8_VIS(ff, srcA0), d_hblf);
                d1 = MUL8_VIS(vis_rebd_lo(vis_ld_u8(dst)), 255 - srcA0);
                dd = vis_fpbdd16(d0, d1);
                vis_st_u16(D64_FROM_F32x2(vis_fpbck16(dd)), dst);
                dst++;
                src++;
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
    }
}

/***************************************************************/

#define GET_COEF(i)                                                    \
    pbthA = pMbsk[i];                                                  \
    srcA = *(mlib_u8*)(src + i);                                       \
    srcA = mul8tbble[extrbA][srcA];                                    \
    dstF = ((((srcA) & DstOpAnd) ^ DstOpXor) + DstOpAdd);              \
    srcF = mul8tbble[pbthA][srcFbbse];                                 \
    dstA = 0xff - pbthA + mul8tbble[pbthA][dstF];                      \
    srcA = mul8tbble[srcF][srcA];                                      \
    resA = srcA + dstA;                                                \
    srcAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srcA), srcAx4);     \
    divAx4 = vis_fbligndbtb(vis_ld_u16(TBL_DIV + 8*resA), divAx4)

/***************************************************************/

void ADD_SUFF(IntArgbToUshortGrbyAlphbMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 extrbA;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_u16 *dst_end;
    mlib_d64 srcAx4, dstAx4, divAx4, dd, ds;
    mlib_d64 done = vis_to_double_dup(0x01000100);
    mlib_f32 fscble = vis_to_flobt(0x02020202);
    mlib_s32 j;
    mlib_s32 SrcOpAnd, SrcOpXor, SrcOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 pbthA, srcFbbse, resA, resG, srcF, dstF, srcA, dstA;

    RGB_VARS;

    SrcOpAnd = (AlphbRules[pCompInfo->rule].srcOps).bndvbl;
    SrcOpXor = (AlphbRules[pCompInfo->rule].srcOps).xorvbl;
    SrcOpAdd =
        (jint) (AlphbRules[pCompInfo->rule].srcOps).bddvbl - SrcOpXor;

    DstOpAnd = (AlphbRules[pCompInfo->rule].dstOps).bndvbl;
    DstOpXor = (AlphbRules[pCompInfo->rule].dstOps).xorvbl;
    DstOpAdd =
        (jint) (AlphbRules[pCompInfo->rule].dstOps).bddvbl - DstOpXor;

    extrbA = (mlib_s32)(pCompInfo->detbils.extrbAlphb * 255.0 + 0.5);

    srcFbbse = ((((0xff) & SrcOpAnd) ^ SrcOpXor) + SrcOpAdd);

    vis_write_gsr((7 << 3) | 6);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (dstScbn == width && srcScbn == 4*width && mbskScbn == width) {
            width *= height;
            height = 1;
        }

        mbskScbn -= width;

        for (j = 0; j < height; j++) {
            mlib_f32 *src = srcBbse;
            mlib_u16 *dst = dstBbse;

            dst_end = dst + width;

            while (((mlib_s32)dst & 3) && dst < dst_end) {
                pbthA = *pMbsk++;
                srcA = *(mlib_u8*)src;
                srcA = mul8tbble[extrbA][srcA];
                dstF = ((((srcA) & DstOpAnd) ^ DstOpXor) + DstOpAdd);
                srcF = mul8tbble[pbthA][srcFbbse];
                dstA = 0xff - pbthA + mul8tbble[pbthA][dstF];
                srcA = mul8tbble[srcF][srcA];
                resA = srcA + dstA;

                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(dd, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                dd = vis_fmul8x16(fscble, dd);
                ff = vis_fpbck16(dd);

                dd = vis_freg_pbir(vis_fzeros(),
                                   ((mlib_f32*)vis_mul8s_tbl)[dstA]);
                DIV_ALPHA(dd, resA);
                ds = vis_fpsub16(done, dd);
                dd = vis_fmul8x16(vis_rebd_lo(vis_ld_u8(dst)), dd);
                ds = vis_fmul8x16(ff, ds);
                dd = vis_fpbdd16(dd, ds);
                ff = vis_fpbck16(dd);
                vis_st_u16(D64_FROM_F32x2(ff), dst);

                dst++;
                src++;
            }

#prbgmb pipeloop(0)
            for (; dst <= (dst_end - 4); dst += 4) {
                GET_COEF(3);
                GET_COEF(2);
                GET_COEF(1);
                GET_COEF(0);
                pMbsk += 4;
                srcAx4 = FMUL_16x16(srcAx4, divAx4);
                dstAx4 = vis_fpsub16(done, srcAx4);

                s02 = vis_fpmerge(src[0], src[2]);
                s13 = vis_fpmerge(src[1], src[3]);
                br = vis_fpmerge(vis_rebd_hi(s02), vis_rebd_hi(s13));
                gb = vis_fpmerge(vis_rebd_lo(s02), vis_rebd_lo(s13));
                GRAY_U16(dd, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));
                dd = vis_fmul8x16(fscble, dd);
                ff = vis_fpbck16(dd);

                dd = vis_fmul8x16(*(mlib_f32*)dst, dstAx4);
                ds = vis_fmul8x16(ff, srcAx4);
                dd = vis_fpbdd16(dd, ds);
                *(mlib_f32*)dst = vis_fpbck16(dd);

                src += 4;
            }

            while (dst < dst_end) {
                pbthA = *pMbsk++;
                srcA = *(mlib_u8*)src;
                srcA = mul8tbble[extrbA][srcA];
                dstF = ((((srcA) & DstOpAnd) ^ DstOpXor) + DstOpAdd);
                srcF = mul8tbble[pbthA][srcFbbse];
                dstA = 0xff - pbthA + mul8tbble[pbthA][dstF];
                srcA = mul8tbble[srcF][srcA];
                resA = srcA + dstA;

                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(dd, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                dd = vis_fmul8x16(fscble, dd);
                ff = vis_fpbck16(dd);

                dd = vis_freg_pbir(vis_fzeros(),
                                   ((mlib_f32*)vis_mul8s_tbl)[dstA]);
                DIV_ALPHA(dd, resA);
                ds = vis_fpsub16(done, dd);
                dd = vis_fmul8x16(vis_rebd_lo(vis_ld_u8(dst)), dd);
                ds = vis_fmul8x16(ff, ds);
                dd = vis_fpbdd16(dd, ds);
                ff = vis_fpbck16(dd);
                vis_st_u16(D64_FROM_F32x2(ff), dst);

                dst++;
                src++;
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
            PTR_ADD(pMbsk,  mbskScbn);
        }
    } else {

        if (dstScbn == width && srcScbn == 4*width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            mlib_f32 *src = srcBbse;
            mlib_u16 *dst = dstBbse;

            dst_end = dst + width;

            while (dst < dst_end) {
                srcA = *(mlib_u8*)src;
                srcA = mul8tbble[extrbA][srcA];
                dstA = ((((srcA) & DstOpAnd) ^ DstOpXor) + DstOpAdd);
                srcA = mul8tbble[srcFbbse][srcA];
                resA = srcA + dstA;

                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(dd, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                dd = vis_fmul8x16(fscble, dd);
                ff = vis_fpbck16(dd);

                resG = mul8tbble[dstA][*dst] +
                       mul8tbble[srcA][((mlib_u8*)&ff)[3]];
                *dst = div8tbble[resA][resG];

                dst++;
                src++;
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbToUshortGrbyAlphbMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 extrbA;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_u16 *dst_end;
    mlib_d64 srcA_d, dstA_d, dd, d0, d1;
    mlib_s32 i, j, srcG;
    mlib_s32 SrcOpAnd, SrcOpXor, SrcOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 pbthA, srcFbbse, dstFbbse, resA, resG, srcA, dstA;

    RGB_VARS;

    SrcOpAnd = (AlphbRules[pCompInfo->rule].srcOps).bndvbl;
    SrcOpXor = (AlphbRules[pCompInfo->rule].srcOps).xorvbl;
    SrcOpAdd =
        (jint) (AlphbRules[pCompInfo->rule].srcOps).bddvbl - SrcOpXor;

    DstOpAnd = (AlphbRules[pCompInfo->rule].dstOps).bndvbl;
    DstOpXor = (AlphbRules[pCompInfo->rule].dstOps).xorvbl;
    DstOpAdd =
        (jint) (AlphbRules[pCompInfo->rule].dstOps).bddvbl - DstOpXor;

    extrbA = (mlib_s32)(pCompInfo->detbils.extrbAlphb * 255.0 + 0.5);

    srcFbbse = ((((0xff) & SrcOpAnd) ^ SrcOpXor) + SrcOpAdd);
    dstFbbse = (((extrbA & DstOpAnd) ^ DstOpXor) + DstOpAdd);

    srcFbbse = mul8tbble[srcFbbse][extrbA];

    if (width < 16) {
        if (pMbsk != NULL) {
            pMbsk += mbskOff;

            for (j = 0; j < height; j++) {
                mlib_u16 *dst = dstBbse;
                mlib_u8  *src = srcBbse;

                for (i = 0; i < width; i++) {
                    pbthA = pMbsk[i];
                    dstA = 0xff - pbthA + mul8tbble[dstFbbse][pbthA];
                    srcA = mul8tbble[srcFbbse][pbthA];
                    resA = srcA + dstA;

                    srcG = RGB2GRAY(src[4*i + 1], src[4*i + 2], src[4*i + 3]);
                    resG = mul8tbble[dstA][dst[i]] + mul8tbble[srcA][srcG];
                    resG = div8tbble[resA][resG];
                    dst[i] = resG;
                }

                PTR_ADD(dstBbse, dstScbn);
                PTR_ADD(srcBbse, srcScbn);
                PTR_ADD(pMbsk,  mbskScbn);
            }
        } else {
            dstA = dstFbbse;
            srcA = srcFbbse;
            resA = srcA + dstA;

            for (j = 0; j < height; j++) {
                mlib_u16 *dst = dstBbse;
                mlib_u8  *src = srcBbse;

                for (i = 0; i < width; i++) {
                    srcG = RGB2GRAY(src[4*i + 1], src[4*i + 2], src[4*i + 3]);
                    resG = mul8tbble[dstA][dst[i]] + mul8tbble[srcA][srcG];
                    resG = div8tbble[resA][resG];
                    dst[i] = resG;
                }

                PTR_ADD(dstBbse, dstScbn);
                PTR_ADD(srcBbse, srcScbn);
            }
        }
        return;
    }

    if (pMbsk != NULL) {
        mlib_s32 srcA_buff[256];
        mlib_d64 dscble = (mlib_d64)(1 << 15)*(1 << 16), ddiv;
        mlib_d64 d_one = vis_to_double_dup(0x7FFF7FFF);

        srcA_buff[0] = 0;
#prbgmb pipeloop(0)
        for (pbthA = 1; pbthA < 256; pbthA++) {
            dstA = 0xff - pbthA + mul8tbble[dstFbbse][pbthA];
            srcA = mul8tbble[srcFbbse][pbthA];
            resA = dstA + srcA;
            ddiv = dscble*vis_d64_div_tbl[resA];
            srcA_buff[pbthA] = srcA*ddiv + (1 << 15);
        }

        pMbsk += mbskOff;
        mbskScbn -= width;

        if (dstScbn == width && srcScbn == 4*width && mbskScbn == width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            mlib_f32 *src = srcBbse;
            mlib_u16 *dst = dstBbse;

            dst_end = dst + width;

            while (((mlib_s32)dst & 3) && dst < dst_end) {
                pbthA = *pMbsk++;
                srcA_d = vis_ld_u16(srcA_buff + pbthA);
                dstA_d = vis_fpsub16(d_one, srcA_d);
                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                d0 = vis_fpbdd16(vis_fmul8x16(ff, srcA_d), d_hblf);
                d1 = vis_fmul8x16(vis_rebd_lo(vis_ld_u8(dst)), dstA_d);
                dd = vis_fpbdd16(d0, d1);
                vis_st_u16(D64_FROM_F32x2(vis_fpbck16(dd)), dst);
                dst++;
                src++;
            }

#prbgmb pipeloop(0)
            for (; dst <= (dst_end - 4); dst += 4) {
                LOAD_NEXT_U16(srcA_d, srcA_buff + pMbsk[3]);
                LOAD_NEXT_U16(srcA_d, srcA_buff + pMbsk[2]);
                LOAD_NEXT_U16(srcA_d, srcA_buff + pMbsk[1]);
                LOAD_NEXT_U16(srcA_d, srcA_buff + pMbsk[0]);
                dstA_d = vis_fpsub16(d_one, srcA_d);
                pMbsk += 4;

                s02 = vis_fpmerge(src[0], src[2]);
                s13 = vis_fpmerge(src[1], src[3]);
                br = vis_fpmerge(vis_rebd_hi(s02), vis_rebd_hi(s13));
                gb = vis_fpmerge(vis_rebd_lo(s02), vis_rebd_lo(s13));
                GRAY_U16(ff, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));
                dd = vis_fpbdd16(vis_fmul8x16(ff, srcA_d), d_hblf);
                dd = vis_fpbdd16(vis_fmul8x16(*(mlib_f32*)dst, dstA_d), dd);
                *(mlib_f32*)dst = vis_fpbck16(dd);
                src += 4;
            }

            while (dst < dst_end) {
                pbthA = *pMbsk++;
                srcA_d = vis_ld_u16(srcA_buff + pbthA);
                dstA_d = vis_fpsub16(d_one, srcA_d);
                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                d0 = vis_fpbdd16(vis_fmul8x16(ff, srcA_d), d_hblf);
                d1 = vis_fmul8x16(vis_rebd_lo(vis_ld_u8(dst)), dstA_d);
                dd = vis_fpbdd16(d0, d1);
                ff = vis_fpbck16(dd);
                vis_st_u16(D64_FROM_F32x2(ff), dst);
                dst++;
                src++;
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
            PTR_ADD(pMbsk,  mbskScbn);
        }
    } else {
        mlib_d64 dscble = (mlib_d64)(1 << 15)*(1 << 16), ddiv;
        mlib_d64 d_one = vis_to_double_dup(0x7FFF7FFF);

        dstA = dstFbbse;
        srcA = srcFbbse;
        resA = dstA + srcA;
        ddiv = dscble*vis_d64_div_tbl[resA];
        srcA = (mlib_s32)(srcA*ddiv + (1 << 15)) >> 16;
        srcA_d = vis_to_double_dup((srcA << 16) | srcA);
        dstA_d = vis_fpsub16(d_one, srcA_d);

        if (dstScbn == width && srcScbn == 4*width) {
            width *= height;
            height = 1;
        }

        for (j = 0; j < height; j++) {
            mlib_f32 *src = srcBbse;
            mlib_u16 *dst = dstBbse;

            dst_end = dst + width;

            while (((mlib_s32)dst & 3) && dst < dst_end) {
                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                d0 = vis_fpbdd16(vis_fmul8x16(ff, srcA_d), d_hblf);
                d1 = vis_fmul8x16(vis_rebd_lo(vis_ld_u8(dst)), dstA_d);
                dd = vis_fpbdd16(d0, d1);
                vis_st_u16(D64_FROM_F32x2(vis_fpbck16(dd)), dst);
                dst++;
                src++;
            }

#prbgmb pipeloop(0)
            for (; dst <= (dst_end - 4); dst += 4) {
                s02 = vis_fpmerge(src[0], src[2]);
                s13 = vis_fpmerge(src[1], src[3]);
                br = vis_fpmerge(vis_rebd_hi(s02), vis_rebd_hi(s13));
                gb = vis_fpmerge(vis_rebd_lo(s02), vis_rebd_lo(s13));
                GRAY_U16(ff, vis_rebd_lo(br), vis_rebd_hi(gb), vis_rebd_lo(gb));
                dd = vis_fpbdd16(vis_fmul8x16(ff, srcA_d), d_hblf);
                dd = vis_fpbdd16(vis_fmul8x16(*(mlib_f32*)dst, dstA_d), dd);
                *(mlib_f32*)dst = vis_fpbck16(dd);
                src += 4;
            }

            while (dst < dst_end) {
                r = vis_ld_u8((mlib_u8*)src + 1);
                g = vis_ld_u8((mlib_u8*)src + 2);
                b = vis_ld_u8((mlib_u8*)src + 3);
                GRAY_U16(ff, vis_rebd_lo(r), vis_rebd_lo(g), vis_rebd_lo(b));
                d0 = vis_fpbdd16(vis_fmul8x16(ff, srcA_d), d_hblf);
                d1 = vis_fmul8x16(vis_rebd_lo(vis_ld_u8(dst)), dstA_d);
                dd = vis_fpbdd16(d0, d1);
                ff = vis_fpbck16(dd);
                vis_st_u16(D64_FROM_F32x2(ff), dst);
                dst++;
                src++;
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
    }
}

/***************************************************************/

#endif
