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

#define Grby2Rgb(x)    \
    (x << 16) | (x << 8) | x

/***************************************************************/

#define INT_RGB(r, g, b)       \
    ((r << 16) | (g << 8) | b)

/***************************************************************/

void ADD_SUFF(IntRgbToIntArgbConvert)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, mbsk;
    mlib_s32 i, i0, j;

    if (dstScbn == 4*width && srcScbn == 4*width) {
        width *= height;
        height = 1;
    }

    mbsk = vis_to_double_dup(0xFF000000);

    for (j = 0; j < height; j++) {
        mlib_f32 *src = srcBbse;
        mlib_f32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            dst[i] = vis_fors(src[i], vis_rebd_hi(mbsk));
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            dd = vis_freg_pbir(src[i], src[i + 1]);

            *(mlib_d64*)(dst + i) = vis_for(dd, mbsk);
        }

        if (i < width) {
            dst[i] = vis_fors(src[i], vis_rebd_hi(mbsk));
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbToIntArgbScbleConvert)(SCALE_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, mbsk;
    mlib_s32 j;

    mbsk = vis_to_double_dup(0xFF000000);

    for (j = 0; j < height; j++) {
        mlib_f32 *src = srcBbse;
        mlib_f32 *dst = dstBbse;
        mlib_f32 *dst_end = dst + width;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 7) {
            *dst++ = vis_fors(src[tmpsxloc >> shift], vis_rebd_hi(mbsk));
            tmpsxloc += sxinc;
        }

#prbgmb pipeloop(0)
        for (; dst <= dst_end - 2; dst += 2) {
            dd = vis_freg_pbir(src[tmpsxloc >> shift],
                               src[(tmpsxloc + sxinc) >> shift]);
            *(mlib_d64*)dst = vis_for(dd, mbsk);
            tmpsxloc += 2*sxinc;
        }

        for (; dst < dst_end; dst++) {
            *dst++ = vis_fors(src[tmpsxloc >> shift], vis_rebd_hi(mbsk));
            tmpsxloc += sxinc;
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

#define BGR_TO_ARGB {                                          \
    mlib_d64 sdb, sdb, sdc, sdd, sde, sdf;                     \
    mlib_d64 s_1, s_2, s_3, b13, b13, b02, b02;                \
                                                               \
    sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_lo(sd1));     \
    sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_hi(sd2));     \
    sdc = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_lo(sd2));     \
                                                               \
    sdd = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdb));     \
    sde = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_hi(sdc));     \
    sdf = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdc));     \
                                                               \
    s_3 = vis_fpmerge(vis_rebd_hi(sdd), vis_rebd_lo(sde));     \
    s_2 = vis_fpmerge(vis_rebd_lo(sdd), vis_rebd_hi(sdf));     \
    s_1 = vis_fpmerge(vis_rebd_hi(sde), vis_rebd_lo(sdf));     \
                                                               \
    b13 = vis_fpmerge(vis_rebd_hi(s_1), vis_rebd_hi(s_3));     \
    b13 = vis_fpmerge(vis_rebd_lo(s_1), vis_rebd_lo(s_3));     \
    b02 = vis_fpmerge(vis_rebd_hi(s_0), vis_rebd_hi(s_2));     \
    b02 = vis_fpmerge(vis_rebd_lo(s_0), vis_rebd_lo(s_2));     \
                                                               \
    dd0 = vis_fpmerge(vis_rebd_hi(b02), vis_rebd_hi(b13));     \
    dd1 = vis_fpmerge(vis_rebd_lo(b02), vis_rebd_lo(b13));     \
    dd2 = vis_fpmerge(vis_rebd_hi(b02), vis_rebd_hi(b13));     \
    dd3 = vis_fpmerge(vis_rebd_lo(b02), vis_rebd_lo(b13));     \
}

/***************************************************************/

void ADD_SUFF(ThreeByteBgrToIntRgbConvert)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 *sp;
    mlib_d64 s_0;
    mlib_d64 s0, s1, s2, s3, sd0, sd1, sd2, dd0, dd1, dd2, dd3;
    mlib_s32 i, i0, j;

    if (width < 16) {
        for (j = 0; j < height; j++) {
            mlib_u8  *src = srcBbse;
            mlib_s32 *dst = dstBbse;

            for (i = 0; i < width; i++) {
                dst[i] = INT_RGB(src[3*i + 2], src[3*i + 1], src[3*i]);
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

    s_0 = vis_fzero();

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_f32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            ((mlib_s32*)dst)[i] = INT_RGB(src[3*i + 2], src[3*i + 1], src[3*i]);
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

            BGR_TO_ARGB

            *(mlib_d64*)(dst + i    ) = dd0;
            *(mlib_d64*)(dst + i + 2) = dd1;
            *(mlib_d64*)(dst + i + 4) = dd2;
            *(mlib_d64*)(dst + i + 6) = dd3;
        }

        for (; i < width; i++) {
            ((mlib_s32*)dst)[i] = INT_RGB(src[3*i + 2], src[3*i + 1], src[3*i]);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ThreeByteBgrToIntRgbScbleConvert)(SCALE_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, mbskFF;
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
                *(mlib_s32*)dst = INT_RGB(src[3*i + 2], src[3*i + 1], src[3*i]);
            }

            PTR_ADD(dstBbse, dstScbn);
            syloc += syinc;
        }
        return;
    }

    mbskFF = vis_fzero();

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
            *(mlib_s32*)dst = INT_RGB(src[3*i + 2], src[3*i + 1], src[3*i]);
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= dst_end - 2; dst += 2) {
            i0 = tmpsxloc >> shift;
            i1 = (tmpsxloc + sxinc) >> shift;
            tmpsxloc += 2*sxinc;

            dd = vis_fbligndbtb(vis_ld_u8(src + 3*i1    ), dd);
            dd = vis_fbligndbtb(vis_ld_u8(src + 3*i1 + 1), dd);
            dd = vis_fbligndbtb(vis_ld_u8(src + 3*i1 + 2), dd);
            dd = vis_fbligndbtb(mbskFF, dd);
            dd = vis_fbligndbtb(vis_ld_u8(src + 3*i0    ), dd);
            dd = vis_fbligndbtb(vis_ld_u8(src + 3*i0 + 1), dd);
            dd = vis_fbligndbtb(vis_ld_u8(src + 3*i0 + 2), dd);
            dd = vis_fbligndbtb(mbskFF, dd);

            *(mlib_d64*)dst = dd;
        }

        for (; dst < dst_end; dst++) {
            i = tmpsxloc >> shift;
            tmpsxloc += sxinc;
            *(mlib_s32*)dst = INT_RGB(src[3*i + 2], src[3*i + 1], src[3*i]);
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(ByteGrbyToIntRgbConvert)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 d0, d1, d2, d3;
    mlib_f32 ff, bb = vis_fzero();
    mlib_s32 i, j, x;

    if (width < 8) {
        for (j = 0; j < height; j++) {
            mlib_u8  *src = srcBbse;
            mlib_s32 *dst = dstBbse;

            for (i = 0; i < width; i++) {
                x = src[i];
                dst[i] = Grby2Rgb(x);
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
        return;
    }

    if (srcScbn == width && dstScbn == 4*width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;
        mlib_s32 *dst_end;

        dst_end = dst + width;

        while (((mlib_s32)src & 3) && dst < dst_end) {
            x = *src++;
            *dst++ = Grby2Rgb(x);
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 4); dst += 4) {
            ff = *(mlib_f32*)src;
            d0 = vis_fpmerge(bb, ff);
            d1 = vis_fpmerge(ff, ff);
            d2 = vis_fpmerge(vis_rebd_hi(d0), vis_rebd_hi(d1));
            d3 = vis_fpmerge(vis_rebd_lo(d0), vis_rebd_lo(d1));
            ((mlib_f32*)dst)[0] = vis_rebd_hi(d2);
            ((mlib_f32*)dst)[1] = vis_rebd_lo(d2);
            ((mlib_f32*)dst)[2] = vis_rebd_hi(d3);
            ((mlib_f32*)dst)[3] = vis_rebd_lo(d3);
            src += 4;
        }

        while (dst < dst_end) {
            x = *src++;
            *dst++ = Grby2Rgb(x);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ByteGrbyToIntRgbScbleConvert)(SCALE_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 d0, d1, d2, d3, dd;
    mlib_f32 ff, bb = vis_fzero();
    mlib_s32 i, j, x;

    if (width < 16) {
        for (j = 0; j < height; j++) {
            mlib_u8  *src = srcBbse;
            mlib_s32 *dst = dstBbse;
            mlib_s32 tmpsxloc = sxloc;

            PTR_ADD(src, (syloc >> shift) * srcScbn);

            for (i = 0; i < width; i++) {
                x = src[tmpsxloc >> shift];
                tmpsxloc += sxinc;
                dst[i] = Grby2Rgb(x);
            }

            PTR_ADD(dstBbse, dstScbn);
            syloc += syinc;
        }
        return;
    }

    vis_blignbddr(NULL, 7);

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;
        mlib_s32 *dst_end;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        dst_end = dst + width;

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 4); dst += 4) {
            LOAD_NEXT_U8(dd, src + ((tmpsxloc + 3*sxinc) >> shift));
            LOAD_NEXT_U8(dd, src + ((tmpsxloc + 2*sxinc) >> shift));
            LOAD_NEXT_U8(dd, src + ((tmpsxloc +   sxinc) >> shift));
            LOAD_NEXT_U8(dd, src + ((tmpsxloc          ) >> shift));
            tmpsxloc += 4*sxinc;
            ff = vis_rebd_hi(dd);
            d0 = vis_fpmerge(bb, ff);
            d1 = vis_fpmerge(ff, ff);
            d2 = vis_fpmerge(vis_rebd_hi(d0), vis_rebd_hi(d1));
            d3 = vis_fpmerge(vis_rebd_lo(d0), vis_rebd_lo(d1));
            ((mlib_f32*)dst)[0] = vis_rebd_hi(d2);
            ((mlib_f32*)dst)[1] = vis_rebd_lo(d2);
            ((mlib_f32*)dst)[2] = vis_rebd_hi(d3);
            ((mlib_f32*)dst)[3] = vis_rebd_lo(d3);
        }

        while (dst < dst_end) {
            x = src[tmpsxloc >> shift];
            tmpsxloc += sxinc;
            *dst++ = Grby2Rgb(x);
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbBmToIntRgbXpbrOver)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd;
    mlib_s32 i, i0, j, mbsk;

    if (dstScbn == 4*width && srcScbn == 4*width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        mlib_f32 *src = srcBbse;
        mlib_f32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            if (*(mlib_u8*)(src + i)) {
                dst[i] = src[i];
            }
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            dd = vis_freg_pbir(src[i], src[i + 1]);

            mbsk = (((-*(mlib_u8*)(src + i)) >> 31) & 2) |
                   (((-*(mlib_u8*)(src + i + 1)) >> 31) & 1);
            vis_pst_32(dd, dst + i, mbsk);
        }

        if (i < width) {
            if (*(mlib_u8*)(src + i)) {
                dst[i] = src[i];
            }
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbBmToIntRgbXpbrBgCopy)(BCOPY_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, d_bgpixel;
    mlib_s32 i, i0, j, mbsk;

    if (dstScbn == 4*width && srcScbn == 4*width) {
        width *= height;
        height = 1;
    }

    d_bgpixel = vis_to_double_dup(bgpixel);

    for (j = 0; j < height; j++) {
        mlib_f32 *src = srcBbse;
        mlib_f32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            if (*(mlib_u8*)(src + i)) {
                dst[i] = src[i];
            } else {
                dst[i] = vis_rebd_hi(d_bgpixel);
            }
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            dd = vis_freg_pbir(src[i], src[i + 1]);

            mbsk = (((-*(mlib_u8*)(src + i)) >> 31) & 2) |
                   (((-*(mlib_u8*)(src + i + 1)) >> 31) & 1);
            *(mlib_d64*)(dst + i) = d_bgpixel;
            vis_pst_32(dd, dst + i, mbsk);
        }

        if (i < width) {
            if (*(mlib_u8*)(src + i)) {
                dst[i] = src[i];
            } else {
                dst[i] = vis_rebd_hi(d_bgpixel);
            }
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbDrbwGlyphListAA)(GLYPH_LIST_PARAMS)
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
