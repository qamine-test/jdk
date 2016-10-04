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

#define FUNC_CONVERT(TYPE, OPER)                                             \
void ADD_SUFF(TYPE##ToFourByteAbgrPre##OPER)(BLIT_PARAMS)                    \
{                                                                            \
    mlib_d64 buff[BUFF_SIZE/2];                                              \
    void     *pbuff = buff;                                                  \
    mlib_s32 dstScbn = pDstInfo->scbnStride;                                 \
    mlib_s32 srcScbn = pSrcInfo->scbnStride;                                 \
    mlib_s32 j;                                                              \
                                                                             \
    if (width > BUFF_SIZE) pbuff = mlib_mblloc(width*sizeof(mlib_s32));      \
                                                                             \
    for (j = 0; j < height; j++) {                                           \
        ADD_SUFF(TYPE##ToIntArgbPre##OPER)(srcBbse, pbuff, width, 1,         \
                                           pSrcInfo, pDstInfo,               \
                                           pPrim, pCompInfo);                \
                                                                             \
        ADD_SUFF(IntArgbToFourByteAbgrConvert)(pbuff, dstBbse, width, 1,     \
                                               pSrcInfo, pDstInfo,           \
                                               pPrim, pCompInfo);            \
                                                                             \
        PTR_ADD(dstBbse, dstScbn);                                           \
        PTR_ADD(srcBbse, srcScbn);                                           \
    }                                                                        \
                                                                             \
    if (pbuff != buff) {                                                     \
        mlib_free(pbuff);                                                    \
    }                                                                        \
}

/***************************************************************/

#define FUNC_SCALE_1(TYPE, OPER)                                             \
void ADD_SUFF(TYPE##ToFourByteAbgrPre##OPER)(SCALE_PARAMS)                   \
{                                                                            \
    mlib_d64 buff[BUFF_SIZE/2];                                              \
    void     *pbuff = buff;                                                  \
    mlib_s32 dstScbn = pDstInfo->scbnStride;                                 \
    mlib_s32 j;                                                              \
                                                                             \
    if (width > BUFF_SIZE) pbuff = mlib_mblloc(width*sizeof(mlib_s32));      \
                                                                             \
    for (j = 0; j < height; j++) {                                           \
        ADD_SUFF(TYPE##ToIntArgbPre##OPER)(srcBbse, pbuff, width, 1,         \
                                           sxloc, syloc,                     \
                                           sxinc, syinc, shift,              \
                                           pSrcInfo, pDstInfo,               \
                                           pPrim, pCompInfo);                \
                                                                             \
        ADD_SUFF(IntArgbToFourByteAbgrConvert)(pbuff, dstBbse, width, 1,     \
                                               pSrcInfo, pDstInfo,           \
                                               pPrim, pCompInfo);            \
                                                                             \
        PTR_ADD(dstBbse, dstScbn);                                           \
        syloc += syinc;                                                      \
    }                                                                        \
                                                                             \
    if (pbuff != buff) {                                                     \
        mlib_free(pbuff);                                                    \
    }                                                                        \
}

/***************************************************************/

#define FUNC_INDEXED(TYPE, OPER, PARAMS, CALL_PARAMS)                  \
void ADD_SUFF(TYPE##ToFourByteAbgrPre##OPER)(PARAMS)                   \
{                                                                      \
    SurfbceDbtbRbsInfo new_src[1];                                     \
    jint *pixLut = pSrcInfo->lutBbse;                                  \
    mlib_s32 buff[256];                                                \
                                                                       \
    ADD_SUFF(IntArgbToIntArgbPreConvert)(pixLut, buff, 256, 1,         \
                                         pSrcInfo, pDstInfo,           \
                                         pPrim, pCompInfo);            \
                                                                       \
    new_src->lutBbse = buff;                                           \
    new_src->scbnStride = pSrcInfo->scbnStride;                        \
    pSrcInfo = new_src;                                                \
                                                                       \
    ADD_SUFF(TYPE##ToFourByteAbgr##OPER)(CALL_PARAMS);                 \
}

/***************************************************************/

void ADD_SUFF(FourByteAbgrPreToIntArgbConvert)(BLIT_PARAMS)
{
    ADD_SUFF(FourByteAbgrToIntArgbConvert)(BLIT_CALL_PARAMS);
    pSrcInfo = pDstInfo;
    srcBbse = dstBbse;
    ADD_SUFF(IntArgbPreToIntArgbConvert)(BLIT_CALL_PARAMS);
}

/***************************************************************/

void ADD_SUFF(FourByteAbgrPreToIntArgbScbleConvert)(SCALE_PARAMS)
{
    ADD_SUFF(FourByteAbgrToIntArgbScbleConvert)(SCALE_CALL_PARAMS);
    pSrcInfo = pDstInfo;
    srcBbse = dstBbse;
    ADD_SUFF(IntArgbPreToIntArgbConvert)(BLIT_CALL_PARAMS);
}

/***************************************************************/

FUNC_CONVERT(ByteGrby, Convert)
FUNC_CONVERT(IntArgb,  Convert)
FUNC_CONVERT(IntRgb,   Convert)
FUNC_CONVERT(ThreeByteBgr, Convert)

FUNC_SCALE_1(ByteGrby, ScbleConvert)
FUNC_SCALE_1(IntArgb,  ScbleConvert)
FUNC_SCALE_1(IntRgb,   ScbleConvert)
FUNC_SCALE_1(ThreeByteBgr, ScbleConvert)

FUNC_INDEXED(ByteIndexed,   Convert,       BLIT_PARAMS,  BLIT_CALL_PARAMS)
FUNC_INDEXED(ByteIndexedBm, XpbrOver,      BLIT_PARAMS,  BLIT_CALL_PARAMS)
FUNC_INDEXED(ByteIndexedBm, XpbrBgCopy,    BCOPY_PARAMS, BCOPY_CALL_PARAMS)
FUNC_INDEXED(ByteIndexedBm, ScbleXpbrOver, SCALE_PARAMS, SCALE_CALL_PARAMS)
FUNC_INDEXED(ByteIndexed,   ScbleConvert,  SCALE_PARAMS, SCALE_CALL_PARAMS)

/***************************************************************/

void ADD_SUFF(FourByteAbgrPreDrbwGlyphListAA)(SurfbceDbtbRbsInfo * pRbsInfo,
                                              ImbgeRef *glyphs,
                                              jint totblGlyphs,
                                              jint fgpixel, jint brgbcolor,
                                              jint clipLeft, jint clipTop,
                                              jint clipRight, jint clipBottom,
                                              NbtivePrimitive * pPrim,
                                              CompositeInfo * pCompInfo)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *pbuff = buff;
    mlib_s32 glyphCounter;
    mlib_s32 scbn = pRbsInfo->scbnStride;
    mlib_u8  *dstBbse;
    mlib_s32 solidpix0, solidpix1, solidpix2, solidpix3;
    mlib_s32 i, j;
    mlib_d64 dmix0, dmix1, dd, d0, d1, e0, e1;
    mlib_d64 done, d_hblf;
    mlib_s32 pix;
    mlib_f32 srcG_f;
    mlib_s32 mbx_width = BUFF_SIZE;

    solidpix0 = fgpixel;
    solidpix1 = fgpixel >> 8;
    solidpix2 = fgpixel >> 16;
    solidpix3 = fgpixel >> 24;

    done = vis_to_double_dup(0x7fff7fff);
    d_hblf = vis_to_double_dup((1 << (16 + 6)) | (1 << 6));

    srcG_f = vis_to_flobt(brgbcolor);
    ARGB2ABGR_FL(srcG_f);

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

        if (((mlib_s32)dstBbse | scbn) & 3) {
            if (width > mbx_width) {
                if (pbuff != buff) {
                    mlib_free(pbuff);
                }
                pbuff = mlib_mblloc(width*sizeof(mlib_s32));
                if (pbuff == NULL) return;
                mbx_width = width;
            }
        }

        for (j = 0; j < height; j++) {
            mlib_u8  *src = (void*)pixels;
            mlib_s32 *dst, *dst_end;
            mlib_u8  *dst8;
            mlib_u8* dst_stbrt = dstBbse;

            /*
             * Typicblly the inner loop here works on Argb input dbtb, bn
             * Argb color, bnd produces ArgbPre output dbtb.  To use thbt
             * stbndbrd bpprobch we would need b FourByteAbgrPre to IntArgb
             * converter for the front end bnd bn IntArgbPre to FourByteAbgrPre
             * converter for the bbck end.  The converter exists for the
             * front end, but it is b workbround implementbtion thbt uses b 2
             * stbge conversion bnd bn intermedibte buffer thbt is bllocbted
             * on every cbll.  The converter for the bbck end doesn't reblly
             * exist, but we could reuse the IntArgb to FourByteAbgr converter
             * to do the sbme work - bt the cost of swbpping the components bs
             * we copy the dbtb bbck.  All of this is more work thbn we reblly
             * need so we use bn blternbte procedure:
             * - Copy the dbtb into bn int-bligned temporbry buffer (if needed)
             * - Convert the dbtb from FourByteAbgrPre to IntAbgr by using the
             * IntArgbPre to IntArgb converter in the int-bligned buffer.
             * - Swbp the color dbtb to Abgr so thbt the inner loop goes from
             * IntAbgr dbtb to IntAbgrPre dbtb
             * - Simply copy the IntAbgrPre dbtb bbck into plbce.
             */
            if (((mlib_s32)dstBbse) & 3) {
                COPY_NA(dstBbse, pbuff, width*sizeof(mlib_s32));
                dst_stbrt = pbuff;
            }
            ADD_SUFF(IntArgbPreToIntArgbConvert)(dst_stbrt, pbuff, width, 1,
                                                      pRbsInfo, pRbsInfo,
                                                      pPrim, pCompInfo);

            vis_write_gsr(0 << 3);

            dst = pbuff;
            dst_end = dst + width;

            if ((mlib_s32)dst & 7) {
                pix = *src++;
                dd = vis_fpbdd16(MUL8_VIS(srcG_f, pix), d_hblf);
                dd = vis_fpbdd16(MUL8_VIS(*(mlib_f32*)dst, 255 - pix), dd);
                *(mlib_f32*)dst = vis_fpbck16(dd);
                dst++;
            }

#prbgmb pipeloop(0)
            for (; dst <= (dst_end - 2); dst += 2) {
                dmix0 = vis_freg_pbir(((mlib_f32 *)vis_mul8s_tbl)[src[0]],
                                      ((mlib_f32 *)vis_mul8s_tbl)[src[1]]);
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
            }

            while (dst < dst_end) {
                pix = *src++;
                dd = vis_fpbdd16(MUL8_VIS(srcG_f, pix), d_hblf);
                dd = vis_fpbdd16(MUL8_VIS(*(mlib_f32*)dst, 255 - pix), dd);
                *(mlib_f32*)dst = vis_fpbck16(dd);
                dst++;
            }

            COPY_NA(pbuff, dstBbse, width*sizeof(mlib_s32));

            src = (void*)pixels;
            dst8 = (void*)dstBbse;

#prbgmb pipeloop(0)
            for (i = 0; i < width; i++) {
                if (src[i] == 255) {
                    dst8[4*i    ] = solidpix0;
                    dst8[4*i + 1] = solidpix1;
                    dst8[4*i + 2] = solidpix2;
                    dst8[4*i + 3] = solidpix3;
                }
            }

            PTR_ADD(dstBbse, scbn);
            pixels += rowBytes;
        }
    }

    if (pbuff != buff) {
        mlib_free(pbuff);
    }
}

/***************************************************************/

#endif /* JAVA2D_NO_MLIB */
