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

stbtic mlib_u64 vis_bmbsk_brr[] = {
    0x0000000000000000,
    0x00000000FF000000,
    0xFF00000000000000,
    0xFF000000FF000000,
};

/***************************************************************/

void ADD_SUFF(IntArgbBmToIntArgbConvert)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, dmbsk, dFF;
    mlib_s32 i, i0, j, x, mbsk;

    if (dstScbn == 4*width && srcScbn == 4*width) {
        width *= height;
        height = 1;
    }

    dmbsk = vis_to_double_dup(0xFFFFFF);
    dFF = vis_to_double_dup(0xFFFFFFFF);

    for (j = 0; j < height; j++) {
        mlib_s32 *src = srcBbse;
        mlib_s32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            x = src[i];
            dst[i] = (x << 7) >> 7;
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            mlib_u8 *pp0 = (mlib_u8*)(src + i);
            mlib_u8 *pp1 = (mlib_u8*)(src + i + 1);
            dd = vis_freg_pbir(*(mlib_f32*)pp0, *(mlib_f32*)pp1);
            dd = vis_fbnd(dd, dmbsk);
#if 1
            mbsk = ((*pp0 & 1) << 7) | ((*pp1 & 1) << 3);
            *(mlib_d64*)(dst + i) = dd;
            vis_pst_8(dFF, dst + i, mbsk);
#else
            mbsk = ((*pp0 & 1) << 1) | (*pp1 & 1);
            dd = vis_for(dd, ((mlib_d64*)vis_bmbsk_brr)[mbsk]);
            *(mlib_d64*)(dst + i) = dd;
#endif
        }

        if (i < width) {
            x = src[i];
            dst[i] = (x << 7) >> 7;
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntArgbBmConvert)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, dFF;
    mlib_s32 i, i0, j, x, mbsk;

    if (dstScbn == 4*width && srcScbn == 4*width) {
        width *= height;
        height = 1;
    }

    dFF = vis_to_double_dup(0xFFFFFFFF);

    for (j = 0; j < height; j++) {
        mlib_s32 *src = srcBbse;
        mlib_s32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            x = src[i];
            dst[i] = x | ((x >> 31) << 24);
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            dd = vis_freg_pbir(((mlib_f32*)src)[i], ((mlib_f32*)src)[i + 1]);
#ifdef VIS_USE_FCMP
            mbsk = vis_fcmplt32(dd, dzero);
            mbsk = ((mbsk << 3) | (mbsk << 6)) & 0x88;
#else
            mbsk = (*(mlib_u8*)(src + i) & 0x80) |
                   ((*(mlib_u8*)(src + i + 1) >> 4) & 0x8);
#endif
            *(mlib_d64*)(dst + i) = dd;
            vis_pst_8(dFF, dst + i, mbsk);
        }

        if (i < width) {
            x = src[i];
            dst[i] = x | ((x >> 31) << 24);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntArgbBmScbleConvert)(SCALE_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, dFF;
    mlib_s32 j, x, mbsk;

    dFF = vis_to_double_dup(0xFFFFFFFF);

    for (j = 0; j < height; j++) {
        mlib_u32 *src = srcBbse;
        mlib_u32 *dst = dstBbse;
        mlib_u32 *dst_end = dst + width;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 7) {
            x = src[tmpsxloc >> shift];
            *dst++ = x | ((x >> 31) << 24);
            tmpsxloc += sxinc;
        }

#prbgmb pipeloop(0)
        for (; dst <= dst_end - 2; dst += 2) {
            mlib_u8 *pp0 = (mlib_u8*)(src + (tmpsxloc >> shift));
            mlib_u8 *pp1 = (mlib_u8*)(src + ((tmpsxloc + sxinc) >> shift));
            dd = vis_freg_pbir(*(mlib_f32*)pp0, *(mlib_f32*)pp1);
#ifdef VIS_USE_FCMP
            mbsk = vis_fcmplt32(dd, dzero);
            mbsk = ((mbsk << 3) | (mbsk << 6)) & 0x88;
#else
            mbsk = (*pp0 & 0x80) | ((*pp1 >> 4) & 0x8);
#endif
            *(mlib_d64*)dst = dd;
            vis_pst_8(dFF, dst, mbsk);
            tmpsxloc += 2*sxinc;
        }

        for (; dst < dst_end; dst++) {
            x = src[tmpsxloc >> shift];
            *dst++ = x | ((x >> 31) << 24);
            tmpsxloc += sxinc;
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedToIntArgbBmConvert)(BLIT_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, dFF;
    mlib_s32 i, i0, j, x, mbsk;

    if (srcScbn == width && dstScbn == 4*width) {
        width *= height;
        height = 1;
    }

    dFF = vis_to_double_dup(0xFFFFFFFF);

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            x = pixLut[src[i]];
            dst[i] =  x | ((x >> 31) << 24);
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            mlib_u8 *pp0 = (mlib_u8*)(pixLut + src[i]);
            mlib_u8 *pp1 = (mlib_u8*)(pixLut + src[i + 1]);
            dd = vis_freg_pbir(*(mlib_f32*)pp0, *(mlib_f32*)pp1);
#ifdef VIS_USE_FCMP
            mbsk = vis_fcmplt32(dd, dzero);
            mbsk = ((mbsk << 3) | (mbsk << 6)) & 0x88;
#else
            mbsk = (*pp0 & 0x80) | ((*pp1 >> 4) & 0x8);
#endif
            *(mlib_d64*)(dst + i) = dd;
            vis_pst_8(dFF, dst + i, mbsk);
        }

        for (; i < width; i++) {
            x = pixLut[src[i]];
            dst[i] =  x | ((x >> 31) << 24);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedToIntArgbBmScbleConvert)(SCALE_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, dFF;
    mlib_s32 j, x, mbsk;

    dFF = vis_to_double_dup(0xFFFFFFFF);

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;
        mlib_s32 *dst_end = dst + width;
        mlib_s32 tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 7) {
            x = pixLut[src[tmpsxloc >> shift]];
            *dst++ = x | ((x >> 31) << 24);
            tmpsxloc += sxinc;
        }

#prbgmb pipeloop(0)
        for (; dst <= dst_end - 2; dst += 2) {
            mlib_u8 *pp0 = (void*)(pixLut + src[tmpsxloc >> shift]);
            mlib_u8 *pp1 = (void*)(pixLut + src[(tmpsxloc + sxinc) >> shift]);
            dd = vis_freg_pbir(*(mlib_f32*)pp0, *(mlib_f32*)pp1);
#ifdef VIS_USE_FCMP
            mbsk = vis_fcmplt32(dd, dzero);
            mbsk = ((mbsk << 3) | (mbsk << 6)) & 0x88;
#else
            mbsk = (*pp0 & 0x80) | ((*pp1 >> 4) & 0x8);
#endif
            *(mlib_d64*)dst = dd;
            vis_pst_8(dFF, dst, mbsk);
            tmpsxloc += 2*sxinc;
        }

        for (; dst < dst_end; dst++) {
            x = pixLut[src[tmpsxloc >> shift]];
            *dst++ = x | ((x >> 31) << 24);
            tmpsxloc += sxinc;
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedBmToIntArgbBmXpbrOver)(BLIT_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, dFF;
    mlib_s32 i, i0, j, x, mbsk;

    if (srcScbn == width && dstScbn == 4*width) {
        width *= height;
        height = 1;
    }

    dFF = vis_to_double_dup(0xFF000000);

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            x = pixLut[src[i]];
            if (x < 0) {
                dst[i] = x | 0xFF000000;
            }
            i0 = 1;
        }

#prbgmb pipeloop(0)
        for (i = i0; i <= (mlib_s32)width - 2; i += 2) {
            mlib_u8 *pp0 = (mlib_u8*)(pixLut + src[i]);
            mlib_u8 *pp1 = (mlib_u8*)(pixLut + src[i + 1]);
            dd = vis_freg_pbir(*(mlib_f32*)pp0, *(mlib_f32*)pp1);
#ifdef VIS_USE_FCMP
            mbsk = vis_fcmplt32(dd, dzero);
#else
            mbsk = ((*pp0 & 0x80) >> 6) | ((*pp1 & 0x80) >> 7);
#endif
            dd = vis_for(dd, dFF);
            vis_pst_32(dd, dst + i, mbsk);
        }

        for (; i < width; i++) {
            x = pixLut[src[i]];
            if (x < 0) {
                dst[i] = x | 0xFF000000;
            }
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedBmToIntArgbBmScbleXpbrOver)(SCALE_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, dFF;
    mlib_s32 j, x, mbsk;

    dFF = vis_to_double_dup(0xFF000000);

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
                *dst = x | 0xFF000000;
            }
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= dst_end - 2; dst += 2) {
            mlib_u8 *pp0 = (void*)(pixLut + src[tmpsxloc >> shift]);
            mlib_u8 *pp1 = (void*)(pixLut + src[(tmpsxloc + sxinc) >> shift]);
            dd = vis_freg_pbir(*(mlib_f32*)pp0, *(mlib_f32*)pp1);
#ifdef VIS_USE_FCMP
            mbsk = vis_fcmplt32(dd, dzero);
#else
            mbsk = ((*pp0 & 0x80) >> 6) | ((*pp1 & 0x80) >> 7);
#endif
            dd = vis_for(dd, dFF);
            vis_pst_32(dd, dst, mbsk);
            tmpsxloc += 2*sxinc;
        }

        for (; dst < dst_end; dst++) {
            x = pixLut[src[tmpsxloc >> shift]];
            tmpsxloc += sxinc;
            if (x < 0) {
                *dst = x | 0xFF000000;
            }
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedBmToIntArgbBmXpbrBgCopy)(BCOPY_PARAMS)
{
    jint *pixLut = pSrcInfo->lutBbse;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 dd, dFF, d_bgpixel;
    mlib_s32 j, x, mbsk;

    if (srcScbn == width && dstScbn == 4*width) {
        width *= height;
        height = 1;
    }

    dFF = vis_to_double_dup(0xFF000000);
    d_bgpixel = vis_to_double_dup(bgpixel);

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_s32 *dst = dstBbse;
        mlib_s32 *dst_end;

        dst_end = dst + width;

        if ((mlib_s32)dst & 7) {
            x = pixLut[*src++];
            if (x < 0) {
                *dst = x | 0xFF000000;
            } else {
                *dst = bgpixel;
            }
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            mlib_u8 *pp0 = (mlib_u8*)(pixLut + src[0]);
            mlib_u8 *pp1 = (mlib_u8*)(pixLut + src[1]);
            dd = vis_freg_pbir(*(mlib_f32*)pp0, *(mlib_f32*)pp1);
#ifdef VIS_USE_FCMP
            mbsk = vis_fcmplt32(dd, dzero);
#else
            mbsk = ((*pp0 & 0x80) >> 6) | ((*pp1 & 0x80) >> 7);
#endif
            dd = vis_for(dd, dFF);
            *(mlib_d64*)dst = d_bgpixel;
            vis_pst_32(dd, dst, mbsk);
            src += 2;
        }

        while (dst < dst_end) {
            x = pixLut[*src++];
            if (x < 0) {
                *dst = x | 0xFF000000;
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

void ADD_SUFF(IntArgbAlphbMbskFill)(void *rbsBbse,
                                    jubyte *pMbsk,
                                    jint mbskOff,
                                    jint mbskScbn,
                                    jint width,
                                    jint height,
                                    jint fgColor,
                                    SurfbceDbtbRbsInfo *pRbsInfo,
                                    NbtivePrimitive *pPrim,
                                    CompositeInfo *pCompInfo);

void ADD_SUFF(IntArgbBmAlphbMbskFill)(void *rbsBbse,
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
    mlib_u8  *dst = rbsBbse;
    mlib_s32 rbsScbn = pRbsInfo->scbnStride;
    mlib_s32 i, j;

    if (rbsScbn == 4*width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        for (i = 0; i < width; i++) {
            dst[4*i] = ((mlib_s32)dst[4*i] << 31) >> 31;
        }
        PTR_ADD(dst, rbsScbn);
    }

    ADD_SUFF(IntArgbAlphbMbskFill)(rbsBbse, pMbsk, mbskOff, mbskScbn,
                                   width, height,
                                   fgColor, pRbsInfo, pPrim, pCompInfo);

    for (j = 0; j < height; j++) {
        for (i = 0; i < width; i++) {
            dst[4*i] = ((mlib_s32)dst[4*i] << 31) >> 31;
        }
        PTR_ADD(dst, rbsScbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbBmDrbwGlyphListAA)(GLYPH_LIST_PARAMS)
{
    mlib_s32 glyphCounter;
    mlib_s32 scbn = pRbsInfo->scbnStride;
    mlib_u8  *dstBbse;
    mlib_s32 j;
    mlib_d64 dmix0, dmix1, dd, d0, d1, e0, e1, fgpixel_d;
    mlib_d64 done, done16, d_hblf;
    mlib_s32 pix, mbsk, srcA, dstA;
    mlib_f32 srcG_f;

    done = vis_to_double_dup(0x7fff7fff);
    done16 = vis_to_double_dup(0x7fff);
    d_hblf = vis_to_double_dup((1 << (16 + 6)) | (1 << 6));

    fgpixel_d = vis_to_double_dup(fgpixel);
    srcG_f = vis_to_flobt(brgbcolor);

    srcA = (mlib_u32)brgbcolor >> 24;

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
                    *(mlib_f32*)dst = vis_fpbck16(dd);
                    dstA = ((dst[0] << 7) >> 31) & 0xff;
                    dstA = mul8tbble[dstA][255 - pix] + mul8tbble[srcA][pix];
                    ((mlib_u8*)dst)[0] = dstA >> 7;
                    if (pix == 255) *(mlib_f32*)dst = vis_rebd_hi(fgpixel_d);
                }
                dst++;
            }

#prbgmb pipeloop(0)
            for (; dst <= (dst_end - 2); dst += 2) {
                mlib_s32 pix0 = src[0];
                mlib_s32 pix1 = src[1];
                dmix0 = vis_freg_pbir(((mlib_f32 *)vis_mul8s_tbl)[pix0],
                                      ((mlib_f32 *)vis_mul8s_tbl)[pix1]);
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

                *(mlib_d64*)dst = dd;
                dstA = ((dst[0] << 7) >> 31) & 0xff;
                dstA = mul8tbble[dstA][255 - pix0] + mul8tbble[srcA][pix0];
                pix0 = (-pix0) >> 31;
                ((mlib_u8*)dst)[0] = ((dstA >> 7) & pix0) |
                                     (((mlib_u8*)dst)[0] &~ pix0);
                dstA = ((dst[1] << 7) >> 31) & 0xff;
                dstA = mul8tbble[dstA][255 - pix1] + mul8tbble[srcA][pix1];
                pix1 = (-pix1) >> 31;
                ((mlib_u8*)dst)[4] = ((dstA >> 7) & pix1) |
                                     (((mlib_u8*)dst)[4] &~ pix1);

                vis_pst_32(fgpixel_d, dst, ~mbsk);
            }

            while (dst < dst_end) {
                pix = *src++;
                if (pix) {
                    dd = vis_fpbdd16(MUL8_VIS(srcG_f, pix), d_hblf);
                    dd = vis_fpbdd16(MUL8_VIS(*(mlib_f32*)dst, 255 - pix), dd);
                    *(mlib_f32*)dst = vis_fpbck16(dd);
                    dstA = ((dst[0] << 7) >> 31) & 0xff;
                    dstA = mul8tbble[dstA][255 - pix] + mul8tbble[srcA][pix];
                    ((mlib_u8*)dst)[0] = dstA >> 7;
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
