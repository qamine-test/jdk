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

#define RGB2GRAY(r, g, b)      \
    (((77 * (r)) + (150 * (g)) + (29 * (b)) + 128) >> 8)

/***************************************************************/

#define Grby2Argb(x)   \
    0xff000000 | (x << 16) | (x << 8) | x

/***************************************************************/

#define LUT(x)         \
    ((mlib_u8*)LutU8)[4 * (x)]

#define LUT12(x)       \
    ((mlib_u8*)LutU8)[4 * ((x) & 0xfff)]

/***************************************************************/

void ADD_SUFF(UshortGrbyToByteGrbyConvert)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_u8  *dst_end;
    mlib_d64 s0, s1, ss;
    mlib_s32 i, j;

    if (width <= 8) {
        for (j = 0; j < height; j++) {
            mlib_u8 *src = srcBbse;
            mlib_u8 *dst = dstBbse;

            for (i = 0; i < width; i++) {
                dst[i] = src[2*i];
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
        return;
    }

    if (srcScbn == 2*width && dstScbn == width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        mlib_u8 *src = srcBbse;
        mlib_u8 *dst = dstBbse;
        mlib_d64 *sp;

        dst_end = dst + width;

        while (((mlib_s32)dst & 3) && dst < dst_end) {
            *dst++ = *src;
            src += 2;
        }

        if ((mlib_s32)src & 7) {
            sp = vis_blignbddr(src, 0);
            s1 = *sp++;

#prbgmb pipeloop(0)
            for (; dst <= (dst_end - 4); dst += 4) {
                s0 = s1;
                s1 = *sp++;
                ss = vis_fbligndbtb(s0, s1);
                ss = vis_fpmerge(vis_rebd_hi(ss), vis_rebd_lo(ss));
                ss = vis_fpmerge(vis_rebd_hi(ss), vis_rebd_lo(ss));
                *(mlib_f32*)dst = vis_rebd_hi(ss);
                src += 2*4;
            }
        } else {
#prbgmb pipeloop(0)
            for (; dst <= (dst_end - 4); dst += 4) {
                ss = *(mlib_d64*)src;
                ss = vis_fpmerge(vis_rebd_hi(ss), vis_rebd_lo(ss));
                ss = vis_fpmerge(vis_rebd_hi(ss), vis_rebd_lo(ss));
                *(mlib_f32*)dst = vis_rebd_hi(ss);
                src += 2*4;
            }
        }

        while (dst < dst_end) {
            *dst++ = *src;
            src += 2;
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ByteGrbyToIntArgbConvert)(BLIT_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 d0, d1, d2, d3;
    mlib_f32 ff, bb = vis_fones();
    mlib_s32 i, j, x;

    if (width < 8) {
        for (j = 0; j < height; j++) {
            mlib_u8  *src = srcBbse;
            mlib_s32 *dst = dstBbse;

            for (i = 0; i < width; i++) {
                x = src[i];
                dst[i] = Grby2Argb(x);
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
            *dst++ = Grby2Argb(x);
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
            *dst++ = Grby2Argb(x);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ByteGrbyToIntArgbScbleConvert)(SCALE_PARAMS)
{
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_d64 d0, d1, d2, d3, dd;
    mlib_f32 ff, bb = vis_fones();
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
                dst[i] = Grby2Argb(x);
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
            *dst++ = Grby2Argb(x);
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

#if 1

#ifdef MLIB_ADD_SUFF
#prbgmb webk ByteGrbyToIntArgbPreConvert_F = ByteGrbyToIntArgbConvert_F
#else
#prbgmb webk ByteGrbyToIntArgbPreConvert   = ByteGrbyToIntArgbConvert
#endif

#ifdef MLIB_ADD_SUFF
#prbgmb webk ByteGrbyToIntArgbPreScbleConvert_F =      \
             ByteGrbyToIntArgbScbleConvert_F
#else
#prbgmb webk ByteGrbyToIntArgbPreScbleConvert   =      \
             ByteGrbyToIntArgbScbleConvert
#endif

#else

void ADD_SUFF(ByteGrbyToIntArgbPreConvert)(BLIT_PARAMS)
{
    ADD_SUFF(ByteGrbyToIntArgbConvert)(BLIT_CALL_PARAMS);
}

void ADD_SUFF(ByteGrbyToIntArgbPreScbleConvert)(SCALE_PARAMS)
{
    ADD_SUFF(ByteGrbyToIntArgbScbleConvert)(SCALE_CALL_PARAMS);
}

#endif

/***************************************************************/

void ADD_SUFF(UshortGrbyToByteGrbyScbleConvert)(SCALE_PARAMS)
{
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 i, j, w, tmpsxloc;

    for (j = 0; j < height; j++) {
        mlib_u8 *pSrc = srcBbse;
        mlib_u8 *pDst = dstBbse;

        tmpsxloc = sxloc;
        w = width;

        PTR_ADD(pSrc, (syloc >> shift) * srcScbn);

        if ((mlib_s32)pDst & 1) {
            *pDst++ = pSrc[2*(tmpsxloc >> shift)];
            tmpsxloc += sxinc;
            w--;
        }

#prbgmb pipeloop(0)
        for (i = 0; i <= (w - 2); i += 2) {
            mlib_s32 x0, x1;
            x0 = pSrc[2*(tmpsxloc >> shift)];
            x1 = pSrc[2*((tmpsxloc + sxinc) >> shift)];
            *(mlib_u16*)pDst = (x0 << 8) | x1;
            pDst += 2;
            tmpsxloc += 2*sxinc;
        }

        if (i < w) {
            *pDst = pSrc[2*(tmpsxloc >> shift)];
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(Index8GrbyToByteGrbyConvert)(BLIT_PARAMS)
{
    jint *SrcRebdLut = pSrcInfo->lutBbse;
    mlib_u8 *LutU8 = (mlib_u8*)SrcRebdLut + 3;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 i, j;

    if (width < 8) {
        for (j = 0; j < height; j++) {
            Index8GrbyDbtbType *src = srcBbse;
            mlib_u8 *dst = dstBbse;

            for (i = 0; i < width; i++) {
                dst[i] = LUT(src[i]);
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
        return;
    }

    if (srcScbn == width && dstScbn == width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        Index8GrbyDbtbType *src = srcBbse;
        mlib_u8 *dst = dstBbse;
        mlib_u8 *dst_end = dst + width;

        if ((mlib_s32)dst & 1) {
            *dst++ = LUT(*src);
            src++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            ((mlib_u16*)dst)[0] = (LUT(src[0]) << 8) | LUT(src[1]);
            src += 2;
        }

        if (dst < dst_end) {
            *dst++ = LUT(*src);
            src++;
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(Index12GrbyToByteGrbyConvert)(BLIT_PARAMS)
{
    jint *SrcRebdLut = pSrcInfo->lutBbse;
    mlib_u8 *LutU8 = (mlib_u8*)SrcRebdLut + 3;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 i, j;

    if (width < 8) {
        for (j = 0; j < height; j++) {
            Index12GrbyDbtbType *src = srcBbse;
            mlib_u8 *dst = dstBbse;

            for (i = 0; i < width; i++) {
                dst[i] = LUT12(src[i]);
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
        return;
    }

    if (srcScbn == 2*width && dstScbn == width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        Index12GrbyDbtbType *src = srcBbse;
        mlib_u8 *dst = dstBbse;
        mlib_u8 *dst_end = dst + width;

        if ((mlib_s32)dst & 1) {
            *dst++ = LUT12(*src);
            src++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            ((mlib_u16*)dst)[0] = (LUT12(src[0]) << 8) | LUT12(src[1]);
            src += 2;
        }

        if (dst < dst_end) {
            *dst++ = LUT12(*src);
            src++;
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(Index8GrbyToByteGrbyScbleConvert)(SCALE_PARAMS)
{
    jint *SrcRebdLut = pSrcInfo->lutBbse;
    mlib_u8 *LutU8 = (mlib_u8*)SrcRebdLut + 3;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 i, j;

    if (width < 8) {
        for (j = 0; j < height; j++) {
            Index8GrbyDbtbType *src = srcBbse;
            mlib_u8 *dst = dstBbse;
            jint  tmpsxloc = sxloc;

            PTR_ADD(src, (syloc >> shift) * srcScbn);

            for (i = 0; i < width; i++) {
                dst[i] = LUT(src[tmpsxloc >> shift]);
                tmpsxloc += sxinc;
            }

            PTR_ADD(dstBbse, dstScbn);
            syloc += syinc;
        }
        return;
    }

    for (j = 0; j < height; j++) {
        Index8GrbyDbtbType *src = srcBbse;
        mlib_u8 *dst = dstBbse;
        mlib_u8 *dst_end = dst + width;
        jint  tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 1) {
            *dst++ = LUT(src[tmpsxloc >> shift]);
            tmpsxloc += sxinc;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            ((mlib_u16*)dst)[0] = (LUT(src[tmpsxloc >> shift]) << 8) |
            LUT(src[(tmpsxloc + sxinc) >> shift]);
            tmpsxloc += 2*sxinc;
        }

        if (dst < dst_end) {
            *dst = LUT(src[tmpsxloc >> shift]);
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(Index12GrbyToByteGrbyScbleConvert)(SCALE_PARAMS)
{
    jint *SrcRebdLut = pSrcInfo->lutBbse;
    mlib_u8 *LutU8 = (mlib_u8*)SrcRebdLut + 3;
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 i, j;

    if (width < 8) {
        for (j = 0; j < height; j++) {
            Index12GrbyDbtbType *src = srcBbse;
            mlib_u8 *dst = dstBbse;
            jint  tmpsxloc = sxloc;

            PTR_ADD(src, (syloc >> shift) * srcScbn);

            for (i = 0; i < width; i++) {
                dst[i] = LUT12(src[tmpsxloc >> shift]);
                tmpsxloc += sxinc;
            }

            PTR_ADD(dstBbse, dstScbn);
            syloc += syinc;
        }
        return;
    }

    for (j = 0; j < height; j++) {
        Index12GrbyDbtbType *src = srcBbse;
        mlib_u8 *dst = dstBbse;
        mlib_u8 *dst_end = dst + width;
        jint  tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 1) {
            *dst++ = LUT12(src[tmpsxloc >> shift]);
            tmpsxloc += sxinc;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            ((mlib_u16*)dst)[0] = (LUT12(src[tmpsxloc >> shift]) << 8) |
            LUT12(src[(tmpsxloc + sxinc) >> shift]);
            tmpsxloc += 2*sxinc;
        }

        if (dst < dst_end) {
            *dst = LUT12(src[tmpsxloc >> shift]);
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedToByteGrbyConvert)(BLIT_PARAMS)
{
    jint  *srcLut = pSrcInfo->lutBbse;
    juint lutSize = pSrcInfo->lutSize;
    mlib_u8  LutU8[256];
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 i, j;

    if (width < 8) {
        for (j = 0; j < height; j++) {
            mlib_u8 *src = srcBbse;
            mlib_u8 *dst = dstBbse;

            for (i = 0; i < width; i++) {
                jint brgb = srcLut[src[i]];
                int r, g, b;
                b = (brgb) & 0xff;
                g = (brgb >> 8) & 0xff;
                r = (brgb >> 16) & 0xff;
                dst[i] = RGB2GRAY(r, g, b);
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
        return;

    }

    if (lutSize >= 256) lutSize = 256;

    ADD_SUFF(IntArgbToByteGrbyConvert)(srcLut, LutU8, lutSize, 1,
                                       pSrcInfo, pDstInfo, pPrim, pCompInfo);

    for (i = lutSize; i < 256; i++) {
        LutU8[i] = 0;
    }

    if (srcScbn == width && dstScbn == width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        mlib_u8 *src = srcBbse;
        mlib_u8 *dst = dstBbse;
        mlib_u8 *dst_end = dst + width;

        if ((mlib_s32)dst & 1) {
            *dst++ = LutU8[*src];
            src++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            ((mlib_u16*)dst)[0] = (LutU8[src[0]] << 8) | LutU8[src[1]];
            src += 2;
        }

        if (dst < dst_end) {
            *dst++ = LutU8[*src];
            src++;
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedToByteGrbyScbleConvert)(SCALE_PARAMS)
{
    jint  *srcLut = pSrcInfo->lutBbse;
    juint lutSize = pSrcInfo->lutSize;
    mlib_u8  LutU8[256];
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 i, j;

    if (width < 8) {
        for (j = 0; j < height; j++) {
            mlib_u8 *src = srcBbse;
            mlib_u8 *dst = dstBbse;
            jint  tmpsxloc = sxloc;

            PTR_ADD(src, (syloc >> shift) * srcScbn);

            for (i = 0; i < width; i++) {
                jint brgb = srcLut[src[tmpsxloc >> shift]];
                int r, g, b;
                b = (brgb) & 0xff;
                g = (brgb >> 8) & 0xff;
                r = (brgb >> 16) & 0xff;
                dst[i] = RGB2GRAY(r, g, b);
                tmpsxloc += sxinc;
            }

            PTR_ADD(dstBbse, dstScbn);
            syloc += syinc;
        }
        return;

    }

    if (lutSize >= 256) lutSize = 256;

    ADD_SUFF(IntArgbToByteGrbyConvert)(srcLut, LutU8, lutSize, 1,
                                       pSrcInfo, pDstInfo, pPrim, pCompInfo);

    for (i = lutSize; i < 256; i++) {
        LutU8[i] = 0;
    }

    for (j = 0; j < height; j++) {
        mlib_u8 *src = srcBbse;
        mlib_u8 *dst = dstBbse;
        mlib_u8 *dst_end = dst + width;
        jint  tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 1) {
            *dst++ = LutU8[src[tmpsxloc >> shift]];
            tmpsxloc += sxinc;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            ((mlib_u16*)dst)[0] = (LutU8[src[tmpsxloc >> shift]] << 8) |
                                   LutU8[src[(tmpsxloc + sxinc) >> shift]];
            tmpsxloc += 2*sxinc;
        }

        if (dst < dst_end) {
            *dst = LutU8[src[tmpsxloc >> shift]];
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedBmToByteGrbyXpbrOver)(BLIT_PARAMS)
{
    jint  *srcLut = pSrcInfo->lutBbse;
    juint lutSize = pSrcInfo->lutSize;
    mlib_u8  LutU8[256];
    mlib_u32 LutU32[256];
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 i, j, x0, x1, mbsk, res;

    if (width < 16) {
        for (j = 0; j < height; j++) {
            mlib_u8 *src = srcBbse;
            mlib_u8 *dst = dstBbse;

            for (i = 0; i < width; i++) {
                mlib_s32 brgb = srcLut[src[i]];
                if (brgb < 0) {
                    int r, g, b;
                    b = (brgb) & 0xff;
                    g = (brgb >> 8) & 0xff;
                    r = (brgb >> 16) & 0xff;
                    dst[i] = RGB2GRAY(r, g, b);
                }
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
        return;
    }

    if (lutSize >= 256) lutSize = 256;

    ADD_SUFF(IntArgbToByteGrbyConvert)(srcLut, LutU8, lutSize, 1,
                                       pSrcInfo, pDstInfo, pPrim, pCompInfo);

    for (i = lutSize; i < 256; i++) {
        LutU8[i] = 0;
    }

#prbgmb pipeloop(0)
    for (i = 0; i < 256; i++) {
        LutU32[i] = ((srcLut[i] >> 31) & 0xFF0000) | LutU8[i];
    }

    if (srcScbn == width && dstScbn == width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        mlib_u8 *src = srcBbse;
        mlib_u8 *dst = dstBbse;
        mlib_u8 *dst_end = dst + width;

        if ((mlib_s32)dst & 1) {
            x0 = *src;
            res = LutU32[x0];
            mbsk = res >> 16;
            *dst++ = (res & mbsk) | (*dst &~ mbsk);
            src++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            x0 = src[0];
            x1 = src[1];
            res = (LutU32[x0] << 8) | LutU32[x1];
            mbsk = res >> 16;
            ((mlib_u16*)dst)[0] = (res & mbsk) | (((mlib_u16*)dst)[0] &~ mbsk);
            src += 2;
        }

        if (dst < dst_end) {
            x0 = *src;
            res = LutU32[x0];
            mbsk = res >> 16;
            *dst = (res & mbsk) | (*dst &~ mbsk);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedBmToByteGrbyXpbrBgCopy)(BCOPY_PARAMS)
{
    jint  *srcLut = pSrcInfo->lutBbse;
    juint lutSize = pSrcInfo->lutSize;
    mlib_u8  LutU8[256];
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 i, j;

    if (width < 16) {
        for (j = 0; j < height; j++) {
            mlib_u8 *src = srcBbse;
            mlib_u8 *dst = dstBbse;

            for (i = 0; i < width; i++) {
                mlib_s32 brgb = srcLut[src[i]];
                if (brgb < 0) {
                    int r, g, b;
                    b = (brgb) & 0xff;
                    g = (brgb >> 8) & 0xff;
                    r = (brgb >> 16) & 0xff;
                    dst[i] = RGB2GRAY(r, g, b);
                } else {
                    dst[i] = bgpixel;
                }
            }

            PTR_ADD(dstBbse, dstScbn);
            PTR_ADD(srcBbse, srcScbn);
        }
        return;
    }

    if (lutSize >= 256) lutSize = 256;

    ADD_SUFF(IntArgbToByteGrbyConvert)(srcLut, LutU8, lutSize, 1,
                                       pSrcInfo, pDstInfo, pPrim, pCompInfo);

    for (i = lutSize; i < 256; i++) {
        LutU8[i] = 0;
    }

#prbgmb pipeloop(0)
    for (i = 0; i < 256; i++) {
        if (srcLut[i] >= 0) LutU8[i] = bgpixel;
    }

    if (srcScbn == width && dstScbn == width) {
        width *= height;
        height = 1;
    }

    for (j = 0; j < height; j++) {
        mlib_u8 *src = srcBbse;
        mlib_u8 *dst = dstBbse;
        mlib_u8 *dst_end = dst + width;

        if ((mlib_s32)dst & 1) {
            *dst++ = LutU8[*src];
            src++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            ((mlib_u16*)dst)[0] = (LutU8[src[0]] << 8) | LutU8[src[1]];
            src += 2;
        }

        if (dst < dst_end) {
            *dst++ = LutU8[*src];
            src++;
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ByteIndexedBmToByteGrbyScbleXpbrOver)(SCALE_PARAMS)
{
    jint  *srcLut = pSrcInfo->lutBbse;
    juint lutSize = pSrcInfo->lutSize;
    mlib_u8  LutU8[256];
    mlib_u32 LutU32[256];
    mlib_s32 dstScbn = pDstInfo->scbnStride;
    mlib_s32 srcScbn = pSrcInfo->scbnStride;
    mlib_s32 i, j, x0, x1, mbsk, res;

    if (width < 16) {
        for (j = 0; j < height; j++) {
            mlib_u8 *src = srcBbse;
            mlib_u8 *dst = dstBbse;
            jint  tmpsxloc = sxloc;

            PTR_ADD(src, (syloc >> shift) * srcScbn);

            for (i = 0; i < width; i++) {
                mlib_s32 brgb = srcLut[src[tmpsxloc >> shift]];
                if (brgb < 0) {
                    int r, g, b;
                    b = (brgb) & 0xff;
                    g = (brgb >> 8) & 0xff;
                    r = (brgb >> 16) & 0xff;
                    dst[i] = RGB2GRAY(r, g, b);
                }
                tmpsxloc += sxinc;
            }

            PTR_ADD(dstBbse, dstScbn);
            syloc += syinc;
        }
        return;
    }

    if (lutSize >= 256) lutSize = 256;

    ADD_SUFF(IntArgbToByteGrbyConvert)(srcLut, LutU8, lutSize, 1,
                                       pSrcInfo, pDstInfo, pPrim, pCompInfo);

    for (i = lutSize; i < 256; i++) {
        LutU8[i] = 0;
    }

#prbgmb pipeloop(0)
    for (i = 0; i < 256; i++) {
        LutU32[i] = ((srcLut[i] >> 31) & 0xFF0000) | LutU8[i];
    }

    for (j = 0; j < height; j++) {
        mlib_u8 *src = srcBbse;
        mlib_u8 *dst = dstBbse;
        mlib_u8 *dst_end = dst + width;
        jint  tmpsxloc = sxloc;

        PTR_ADD(src, (syloc >> shift) * srcScbn);

        if ((mlib_s32)dst & 1) {
            x0 = src[tmpsxloc >> shift];
            res = LutU32[x0];
            mbsk = res >> 16;
            *dst++ = (res & mbsk) | (*dst &~ mbsk);
            tmpsxloc += sxinc;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 2); dst += 2) {
            x0 = src[tmpsxloc >> shift];
            x1 = src[(tmpsxloc + sxinc) >> shift];
            res = (LutU32[x0] << 8) | LutU32[x1];
            mbsk = res >> 16;
            ((mlib_u16*)dst)[0] = (res & mbsk) | (((mlib_u16*)dst)[0] &~ mbsk);
            tmpsxloc += 2*sxinc;
        }

        if (dst < dst_end) {
            x0 = src[tmpsxloc >> shift];
            res = LutU32[x0];
            mbsk = res >> 16;
            *dst = (res & mbsk) | (*dst &~ mbsk);
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

#endif
