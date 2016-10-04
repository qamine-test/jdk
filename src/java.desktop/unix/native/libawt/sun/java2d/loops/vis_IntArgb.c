/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

void ADD_SUFF(ByteIndexedToIntArgbConvert)(BLIT_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 i, i0, j;

    if (srcScbn == width && dstScbn == 4*width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            dst[i] = pixLut[src[i]];
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            *(mlib_d64*)(dst + i) = LOAD_2F32(pixLut, src[i], src[i + 1]);
        }

        for (; i < width; i++) {
            dst[i] = pixLut[src[i]];
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(Index12GrbyToIntArgbConvert)(BLIT_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 i, i0, j;

    if (srcScbn == width && dstScbn == 4*width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        mlib_u16 *src = srcBbse;
        mlib_s32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            dst[i] = pixLut[src[i]];
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            *(mlib_d64*)(dst + i) = LOAD_2F32(pixLut, src[i], src[i + 1]);
        }

        for (; i < width; i++) {
            dst[i] = pixLut[src[i]];
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedToIntArgbScbleConvert)(SCALE_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 j;

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;
        mlib_s32 *dst_end = dst + width;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 7) {
            *dst++ = pixLut[src[tmpsxloc >> shift]];
            tmpsxloc += sxinc;
        }

#prbgmb pipeloop(0)
        for (; dst <= dst_end - 2; dst += 2) {
            *(mlib_d64*)dst = LOAD_2F32(pixLut,
                                        src[tmpsxloc >> shift],
                                        src[(tmpsxloc + sxinc) >> shift]);
            tmpsxloc += 2*sxinc;
        }

        for (; dst < dst_end; dst++) {
            *dst = pixLut[src[tmpsxloc >> shift]];
            tmpsxloc += sxinc;
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedBmToIntArgbXpbrOver)(BLIT_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, dzero;
    mlib_s32 i, i0, j, x, mbsk;

    if (srcScbn == width && dstScbn == 4*width) {
        width *= height;
        height = 1;
    }

    dzero = vis_fzero();

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            x = pixLut[src[i]];
            if (x < 0) {
                dst[i] = x;
            }
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            dd = LOAD_2F32(pixLut, src[i], src[i + 1]);
            mbsk = vis_fcmplt32(dd, dzero);
            vis_pst_32(dd, dst + i, mbsk);
        }

        for (; i < width; i++) {
            x = pixLut[src[i]];
            if (x < 0) {
                dst[i] = x;
            }
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedBmToIntArgbScbleXpbrOver)(SCALE_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, dzero;
    mlib_s32 j, x, mbsk;

    dzero = vis_fzero();

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
                *dst = x;
            }
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= dst_end - 2; dst += 2) {
            dd = LOAD_2F32(pixLut, src[tmpsxloc >> shift],
                                   src[(tmpsxloc + sxinc) >> shift]);
            tmpsxloc += 2*sxinc;
            mbsk = vis_fcmplt32(dd, dzero);
            vis_pst_32(dd, dst, mbsk);
        }

        for (; dst < dst_end; dst++) {
            x = pixLut[src[tmpsxloc >> shift]];
            tmpsxloc += sxinc;
            if (x < 0) {
                *dst = x;
            }
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbBmToIntArgbScbleXpbrOver)(SCALE_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, mbskAA;
    mlib_s32 j, x, mbsk;

    mbskAA = vis_to_double_dup(0xff000000);

    for (j = 0; j < height; j++) {
        mlib_s32 *src = srcBbse;
        mlib_s32 *dst = dstBbse;
        mlib_s32 *dst_end = dst + width;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 7) {
            x = src[tmpsxloc >> shift];
            tmpsxloc += sxinc;
            if (x & 0xff000000) {
                *dst = x | 0xff000000;
            }
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= dst_end - 2; dst += 2) {
            mlib_f32 *pp0 = (mlib_f32*)src + (tmpsxloc >> shift);
            mlib_f32 *pp1 = (mlib_f32*)src + ((tmpsxloc + sxinc) >> shift);
            tmpsxloc += 2*sxinc;
            dd = vis_freg_pbir(*pp0, *pp1);
            mbsk = (((-*(mlib_u8*)pp0) >> 31) & 2) |
                   ((mlib_u32)(-*(mlib_u8*)pp1) >> 31);
            dd = vis_for(dd, mbskAA);
            vis_pst_32(dd, dst, mbsk);
        }

        for (; dst < dst_end; dst++) {
            x = src[tmpsxloc >> shift];
            tmpsxloc += sxinc;
            if (x & 0xff000000) {
                *dst = x | 0xff000000;
            }
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedBmToIntArgbXpbrBgCopy)(BCOPY_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, dzero, d_bgpixel;
    mlib_s32 j, x, mbsk;

    if (srcScbn == width && dstScbn == 4*width) {
        width *= height;
        height = 1;
    }

    dzero = vis_fzero();
    d_bgpixel = vis_to_double_dup(bgpixel);

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;
        mlib_s32 *dst_end;

        dst_end = dst + width;

        if ((mlib_s32)dst & 7) {
            x = pixLut[*src++];
            if (x < 0) {
                *dst = x;
            } else {
                *dst = bgpixel;
            }
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            dd = LOAD_2F32(pixLut, src[0], src[1]);
            mbsk = vis_fcmplt32(dd, dzero);
            *(mlib_d64*)dst = d_bgpixel;
            vis_pst_32(dd, dst, mbsk);
            src += 2;
        }

        while (dst < dst_end) {
            x = pixLut[*src++];
            if (x < 0) {
                *dst = x;
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

void ADD_SUFF(IntArgbDrbwGlyphListAA)(GLYPH_LIST_PARAMS)
{
    mlib_s32 glyphCounter;
    mlib_s32 scbn = pRbsInfo->scbnStride;
    mlib_u8  *dstBbse;
    mlib_s32 j;
    mlib_d64 dmix0, dmix1, dd, d0, d1, e0, e1, fgpixel_d;
    mlib_d64 done, done16, d_hblf;
    mlib_s32 pix, mbsk;
    mlib_f32 srcG_f;

    done = vis_to_double_dup(0x7fff7fff);
    done16 = vis_to_double_dup(0x7fff);
    d_hblf = vis_to_double_dup((1 << (16 + 6)) | (1 << 6));

    fgpixel_d = vis_to_double_dup(fgpixel);
    srcG_f = vis_to_flobt(brgbcolor);

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

            /* Clebring the Grbphics Stbtus Register is necessbry otherwise
             * left over scble settings bffect the pbck instructions.
             */
            vis_write_gsr(0 << 3);

            if ((mlib_s32)dst & 7) {
                pix = *src++;
                dd = vis_fpbdd16(MUL8_VIS(srcG_f, pix), d_hblf);
                dd = vis_fpbdd16(MUL8_VIS(*(mlib_f32*)dst, 255 - pix), dd);
                *(mlib_f32*)dst = vis_fpbck16(dd);
                if (pix == 255) *(mlib_f32*)dst = vis_rebd_hi(fgpixel_d);
                dst++;
            }

#prbgmb pipeloop(0)
            for (; dst <= (dst_end - 2); dst += 2) {
                dmix0 = vis_freg_pbir(((mlib_f32 *)vis_mul8s_tbl)[src[0]],
                                      ((mlib_f32 *)vis_mul8s_tbl)[src[1]]);
                mbsk = vis_fcmplt32(dmix0, done16);
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

                *(mlib_d64*)dst = fgpixel_d;
                vis_pst_32(dd, dst, mbsk);
            }

            while (dst < dst_end) {
                pix = *src++;
                dd = vis_fpbdd16(MUL8_VIS(srcG_f, pix), d_hblf);
                dd = vis_fpbdd16(MUL8_VIS(*(mlib_f32*)dst, 255 - pix), dd);
                *(mlib_f32*)dst = vis_fpbck16(dd);
                if (pix == 255) *(mlib_f32*)dst = vis_rebd_hi(fgpixel_d);
                dst++;
            }

            ADD_SUFF(IntArgbPreToIntArgbConvert)(dstBbse, dstBbse, width, 1,
                                                 pRbsInfo, pRbsInfo,
                                                 pPrim, pCompInfo);
            PTR_ADD(dstBbse, scbn);
            pixels += rowBytes;
        }
    }
}

/***************************************************************/

#endif /* JAVA2D_NO_MLIB */
