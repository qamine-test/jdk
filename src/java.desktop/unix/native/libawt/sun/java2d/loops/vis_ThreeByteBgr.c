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

#define GBR_PIXEL(i)   \
    0xFF000000 | (src[3*i + 2] << 16) | (src[3*i + 1] << 8) | src[3*i]

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

void ADD_SUFF(ThreeByteBgrToIntArgbConvert)(BLIT_PARAMS)
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
                dst[i] = GBR_PIXEL(i);
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

    s_0 = vis_fone();

    for (j = 0; j < height; j++) {
        mlib_u8  *src = srcBbse;
        mlib_f32 *dst = dstBbse;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            ((mlib_s32*)dst)[i] = GBR_PIXEL(i);
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
            ((mlib_s32*)dst)[i] = GBR_PIXEL(i);
        }

        PTR_ADD(dstBbse, dstScbn);
        PTR_ADD(srcBbse, srcScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ThreeByteBgrToIntArgbScbleConvert)(SCALE_PARAMS)
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
                *(mlib_s32*)dst = GBR_PIXEL(i);
            }

            PTR_ADD(dstBbse, dstScbn);
            syloc += syinc;
        }
        return;
    }

    mbskFF = vis_fone();

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
            *(mlib_s32*)dst = GBR_PIXEL(i);
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
            *(mlib_s32*)dst = GBR_PIXEL(i);
        }

        PTR_ADD(dstBbse, dstScbn);
        syloc += syinc;
    }
}

/***************************************************************/

#endif
