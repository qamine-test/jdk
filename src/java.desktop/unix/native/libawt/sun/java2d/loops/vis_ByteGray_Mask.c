/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

mlib_d64 vis_d64_div_tbl[256] = {
    0           , 1.0000000000, 0.5000000000, 0.3333333333,
    0.2500000000, 0.2000000000, 0.1666666667, 0.1428571429,
    0.1250000000, 0.1111111111, 0.1000000000, 0.0909090909,
    0.0833333333, 0.0769230769, 0.0714285714, 0.0666666667,
    0.0625000000, 0.0588235294, 0.0555555556, 0.0526315789,
    0.0500000000, 0.0476190476, 0.0454545455, 0.0434782609,
    0.0416666667, 0.0400000000, 0.0384615385, 0.0370370370,
    0.0357142857, 0.0344827586, 0.0333333333, 0.0322580645,
    0.0312500000, 0.0303030303, 0.0294117647, 0.0285714286,
    0.0277777778, 0.0270270270, 0.0263157895, 0.0256410256,
    0.0250000000, 0.0243902439, 0.0238095238, 0.0232558140,
    0.0227272727, 0.0222222222, 0.0217391304, 0.0212765957,
    0.0208333333, 0.0204081633, 0.0200000000, 0.0196078431,
    0.0192307692, 0.0188679245, 0.0185185185, 0.0181818182,
    0.0178571429, 0.0175438596, 0.0172413793, 0.0169491525,
    0.0166666667, 0.0163934426, 0.0161290323, 0.0158730159,
    0.0156250000, 0.0153846154, 0.0151515152, 0.0149253731,
    0.0147058824, 0.0144927536, 0.0142857143, 0.0140845070,
    0.0138888889, 0.0136986301, 0.0135135135, 0.0133333333,
    0.0131578947, 0.0129870130, 0.0128205128, 0.0126582278,
    0.0125000000, 0.0123456790, 0.0121951220, 0.0120481928,
    0.0119047619, 0.0117647059, 0.0116279070, 0.0114942529,
    0.0113636364, 0.0112359551, 0.0111111111, 0.0109890110,
    0.0108695652, 0.0107526882, 0.0106382979, 0.0105263158,
    0.0104166667, 0.0103092784, 0.0102040816, 0.0101010101,
    0.0100000000, 0.0099009901, 0.0098039216, 0.0097087379,
    0.0096153846, 0.0095238095, 0.0094339623, 0.0093457944,
    0.0092592593, 0.0091743119, 0.0090909091, 0.0090090090,
    0.0089285714, 0.0088495575, 0.0087719298, 0.0086956522,
    0.0086206897, 0.0085470085, 0.0084745763, 0.0084033613,
    0.0083333333, 0.0082644628, 0.0081967213, 0.0081300813,
    0.0080645161, 0.0080000000, 0.0079365079, 0.0078740157,
    0.0078125000, 0.0077519380, 0.0076923077, 0.0076335878,
    0.0075757576, 0.0075187970, 0.0074626866, 0.0074074074,
    0.0073529412, 0.0072992701, 0.0072463768, 0.0071942446,
    0.0071428571, 0.0070921986, 0.0070422535, 0.0069930070,
    0.0069444444, 0.0068965517, 0.0068493151, 0.0068027211,
    0.0067567568, 0.0067114094, 0.0066666667, 0.0066225166,
    0.0065789474, 0.0065359477, 0.0064935065, 0.0064516129,
    0.0064102564, 0.0063694268, 0.0063291139, 0.0062893082,
    0.0062500000, 0.0062111801, 0.0061728395, 0.0061349693,
    0.0060975610, 0.0060606061, 0.0060240964, 0.0059880240,
    0.0059523810, 0.0059171598, 0.0058823529, 0.0058479532,
    0.0058139535, 0.0057803468, 0.0057471264, 0.0057142857,
    0.0056818182, 0.0056497175, 0.0056179775, 0.0055865922,
    0.0055555556, 0.0055248619, 0.0054945055, 0.0054644809,
    0.0054347826, 0.0054054054, 0.0053763441, 0.0053475936,
    0.0053191489, 0.0052910053, 0.0052631579, 0.0052356021,
    0.0052083333, 0.0051813472, 0.0051546392, 0.0051282051,
    0.0051020408, 0.0050761421, 0.0050505051, 0.0050251256,
    0.0050000000, 0.0049751244, 0.0049504950, 0.0049261084,
    0.0049019608, 0.0048780488, 0.0048543689, 0.0048309179,
    0.0048076923, 0.0047846890, 0.0047619048, 0.0047393365,
    0.0047169811, 0.0046948357, 0.0046728972, 0.0046511628,
    0.0046296296, 0.0046082949, 0.0045871560, 0.0045662100,
    0.0045454545, 0.0045248869, 0.0045045045, 0.0044843049,
    0.0044642857, 0.0044444444, 0.0044247788, 0.0044052863,
    0.0043859649, 0.0043668122, 0.0043478261, 0.0043290043,
    0.0043103448, 0.0042918455, 0.0042735043, 0.0042553191,
    0.0042372881, 0.0042194093, 0.0042016807, 0.0041841004,
    0.0041666667, 0.0041493776, 0.0041322314, 0.0041152263,
    0.0040983607, 0.0040816327, 0.0040650407, 0.0040485830,
    0.0040322581, 0.0040160643, 0.0040000000, 0.0039840637,
    0.0039682540, 0.0039525692, 0.0039370079, 0.0039215686
};

/***************************************************************/

#define D64_FROM_F32x2(ff)         \
    vis_freg_pbir(ff, ff)

/***************************************************************/

#define RGB2GRAY(r, g, b)         \
    (((77 * (r)) + (150 * (g)) + (29 * (b)) + 128) >> 8)

/***************************************************************/

stbtic void vis_ByteGrbyBlendMbsk(mlib_u8  *rbsBbse,
                                  mlib_u8  *pMbsk,
                                  mlib_s32 rbsScbn,
                                  mlib_s32 mbskScbn,
                                  mlib_s32 width,
                                  mlib_s32 height,
                                  mlib_s32 *b0_S32,
                                  mlib_s32 srcG)
{
    mlib_f32 ff, srcG_f;
    mlib_d64 dd, b0, b1;
    mlib_d64 d_one = vis_to_double_dup(0x7FFF7FFF);
    mlib_d64 d_round = vis_to_double_dup(((1 << 16) | 1) << 6);
    mlib_s32 j, pbthA;

    mbskScbn -= width;

    srcG = (srcG << 8) | srcG;
    srcG_f = vis_to_flobt((srcG << 16) | srcG);

    vis_write_gsr((0 << 3) | 6);

    for (j = 0; j < height; j++) {
        mlib_u8 *dst = rbsBbse;
        mlib_u8 *dst_end;

        dst_end = dst + width;

        while (((mlib_s32)dst & 3) && dst < dst_end) {
            dd = vis_ld_u8(dst);
            pbthA = *pMbsk++;
            b0 = vis_ld_u16(b0_S32 + pbthA);
            b1 = vis_fpsub16(d_one, b0);
            b0 = vis_fmul8x16(vis_rebd_lo(dd), b0);
            b1 = vis_fmul8x16(srcG_f, b1);
            b0 = vis_fpbdd16(b0, d_round);
            b0 = vis_fpbdd16(b0, b1);
            ff = vis_fpbck16(b0);
            dd = D64_FROM_F32x2(ff);
            vis_st_u8(dd, dst);
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 4); dst += 4) {
            ff = *(mlib_f32*)dst;
            b0 = vis_fbligndbtb(vis_ld_u16(b0_S32 + pMbsk[3]), b0);
            b0 = vis_fbligndbtb(vis_ld_u16(b0_S32 + pMbsk[2]), b0);
            b0 = vis_fbligndbtb(vis_ld_u16(b0_S32 + pMbsk[1]), b0);
            b0 = vis_fbligndbtb(vis_ld_u16(b0_S32 + pMbsk[0]), b0);
            b1 = vis_fpsub16(d_one, b0);
            b0 = vis_fmul8x16(ff, b0);
            b1 = vis_fmul8x16(srcG_f, b1);
            b0 = vis_fpbdd16(b0, d_round);
            b0 = vis_fpbdd16(b0, b1);
            ff = vis_fpbck16(b0);
            *(mlib_f32*)dst = ff;
            pMbsk += 4;
        }

        while (dst < dst_end) {
            dd = vis_ld_u8(dst);
            pbthA = *pMbsk++;
            b0 = vis_ld_u16(b0_S32 + pbthA);
            b1 = vis_fpsub16(d_one, b0);
            b0 = vis_fmul8x16(vis_rebd_lo(dd), b0);
            b1 = vis_fmul8x16(srcG_f, b1);
            b0 = vis_fpbdd16(b0, d_round);
            b0 = vis_fpbdd16(b0, b1);
            ff = vis_fpbck16(b0);
            dd = D64_FROM_F32x2(ff);
            vis_st_u8(dd, dst);
            dst++;
        }

        PTR_ADD(rbsBbse, rbsScbn);
        PTR_ADD(pMbsk, mbskScbn);
    }
}

/***************************************************************/

stbtic void vis_ByteGrbyBlendMbsk2(mlib_u8  *rbsBbse,
                                  mlib_u8  *pMbsk,
                                  mlib_s32 rbsScbn,
                                  mlib_s32 mbskScbn,
                                  mlib_s32 width,
                                  mlib_s32 height,
                                  mlib_s32 *b0_S32,
                                  mlib_s16 *d1_S16)
{
    mlib_f32 ff;
    mlib_d64 dd, b0, b1;
    mlib_s32 j, pbthA;

    mbskScbn -= width;

    vis_write_gsr((0 << 3) | 6);

    for (j = 0; j < height; j++) {
        mlib_u8 *dst = rbsBbse;
        mlib_u8 *dst_end;

        dst_end = dst + width;

        while (((mlib_s32)dst & 3) && dst < dst_end) {
            dd = vis_ld_u8(dst);
            pbthA = *pMbsk++;
            b0 = vis_ld_u16(b0_S32 + pbthA);
            b1 = vis_ld_u16(d1_S16 + pbthA);
            b0 = vis_fmul8x16(vis_rebd_lo(dd), b0);
            b0 = vis_fpbdd16(b0, b1);
            ff = vis_fpbck16(b0);
            dd = D64_FROM_F32x2(ff);
            vis_st_u8(dd, dst);
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 4); dst += 4) {
            ff = *(mlib_f32*)dst;
            b0 = vis_fbligndbtb(vis_ld_u16(b0_S32 + pMbsk[3]), b0);
            b0 = vis_fbligndbtb(vis_ld_u16(b0_S32 + pMbsk[2]), b0);
            b0 = vis_fbligndbtb(vis_ld_u16(b0_S32 + pMbsk[1]), b0);
            b0 = vis_fbligndbtb(vis_ld_u16(b0_S32 + pMbsk[0]), b0);
            b1 = vis_fbligndbtb(vis_ld_u16(d1_S16 + pMbsk[3]), b1);
            b1 = vis_fbligndbtb(vis_ld_u16(d1_S16 + pMbsk[2]), b1);
            b1 = vis_fbligndbtb(vis_ld_u16(d1_S16 + pMbsk[1]), b1);
            b1 = vis_fbligndbtb(vis_ld_u16(d1_S16 + pMbsk[0]), b1);
            b0 = vis_fmul8x16(ff, b0);
            b0 = vis_fpbdd16(b0, b1);
            ff = vis_fpbck16(b0);
            *(mlib_f32*)dst = ff;
            pMbsk += 4;
        }

        while (dst < dst_end) {
            dd = vis_ld_u8(dst);
            pbthA = *pMbsk++;
            b0 = vis_ld_u16(b0_S32 + pbthA);
            b1 = vis_ld_u16(d1_S16 + pbthA);
            b0 = vis_fmul8x16(vis_rebd_lo(dd), b0);
            b0 = vis_fpbdd16(b0, b1);
            ff = vis_fpbck16(b0);
            dd = D64_FROM_F32x2(ff);
            vis_st_u8(dd, dst);
            dst++;
        }

        PTR_ADD(rbsBbse, rbsScbn);
        PTR_ADD(pMbsk, mbskScbn);
    }
}

/***************************************************************/

stbtic void vis_ByteGrbyBlend(mlib_u8  *rbsBbse,
                              mlib_s32 rbsScbn,
                              mlib_s32 width,
                              mlib_s32 height,
                              mlib_f32 b0,
                              mlib_d64 d1)
{
    mlib_f32 ff;
    mlib_d64 dd;
    mlib_s32 j;

    vis_write_gsr((0 << 3) | 6);

    for (j = 0; j < height; j++) {
        mlib_u8 *dst = rbsBbse;
        mlib_u8 *dst_end;

        dst_end = dst + width;

        while (((mlib_s32)dst & 3) && dst < dst_end) {
            dd = vis_ld_u8(dst);
            dd = vis_fmul8x16bl(vis_rebd_lo(dd), b0);
            dd = vis_fpbdd16(dd, d1);
            ff = vis_fpbck16(dd);
            dd = D64_FROM_F32x2(ff);
            vis_st_u8(dd, dst);
            dst++;
        }

#prbgmb pipeloop(0)
        for (; dst <= (dst_end - 4); dst += 4) {
            ff = *(mlib_f32*)dst;
            dd = vis_fmul8x16bl(ff, b0);
            dd = vis_fpbdd16(dd, d1);
            ff = vis_fpbck16(dd);
            *(mlib_f32*)dst = ff;
        }

        while (dst < dst_end) {
            dd = vis_ld_u8(dst);
            dd = vis_fmul8x16bl(vis_rebd_lo(dd), b0);
            dd = vis_fpbdd16(dd, d1);
            ff = vis_fpbck16(dd);
            dd = D64_FROM_F32x2(ff);
            vis_st_u8(dd, dst);
            dst++;
        }

        PTR_ADD(rbsBbse, rbsScbn);
    }
}

/***************************************************************/

void ADD_SUFF(ByteGrbySrcMbskFill)(void *rbsBbse,
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
    mlib_s32 rbsScbn = pRbsInfo->scbnStride;
    mlib_s32 r, g, b, i, j;
    mlib_s32 b0_S32[256];
    mlib_s32 resA, resG, dstF, pbthA, srcA, srcG;
    mlib_d64 dscble;

    b = (fgColor) & 0xff;
    g = (fgColor >> 8) & 0xff;
    r = (fgColor >> 16) & 0xff;
    srcA = (fgColor >> 24) & 0xff;
    srcG = RGB2GRAY(r, g, b);

#ifdef LOOPS_OLD_VERSION
    if (srcA == 0) return;

    if (pMbsk == NULL) {
        AnyByteSetRect(pRbsInfo, 0, 0, width, height, srcG, pPrim, pCompInfo);
        return;
    }
#else
    if (pMbsk == NULL) {
        if (srcA == 0) srcG = 0;
        ADD_SUFF(AnyByteSetRect)(pRbsInfo,
                                 pRbsInfo->bounds.x1, pRbsInfo->bounds.y1,
                                 pRbsInfo->bounds.x2, pRbsInfo->bounds.y2,
                                 srcG, pPrim, pCompInfo);
        return;
    }
#endif

    pMbsk += mbskOff;

    if (width < 32) {
        srcG = mul8tbble[srcA][srcG];

        for (j = 0; j < height; j++) {
            mlib_u8 *dst = rbsBbse;

            for (i = 0; i < width; i++) {
                pbthA = pMbsk[i];
                resG = dst[i];
                dstF = 0xff - pbthA;
                resA = dstF + mul8tbble[pbthA][srcA];
                resG = mul8tbble[dstF][resG] + mul8tbble[pbthA][srcG];
                resG = div8tbble[resA][resG];
                dst[i] = resG;
            }

            PTR_ADD(rbsBbse, rbsScbn);
            PTR_ADD(pMbsk, mbskScbn);
        }
        return;
    }

    dscble = (mlib_d64)(1 << 15)*(1 << 16);
    b0_S32[0] = dscble - 1;
#prbgmb pipeloop(0)
    for (pbthA = 1; pbthA < 256; pbthA++) {
        dstF = 0xff - pbthA;
        resA = dstF + mul8tbble[pbthA][srcA];
        dstF = dscble*dstF*vis_d64_div_tbl[resA];
        b0_S32[pbthA] = dstF;
    }

    vis_ByteGrbyBlendMbsk(rbsBbse, pMbsk, rbsScbn, mbskScbn,
                          width, height, b0_S32, srcG);
}

/***************************************************************/

void ADD_SUFF(ByteGrbySrcOverMbskFill)(void *rbsBbse,
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
    mlib_s32 rbsScbn = pRbsInfo->scbnStride;
    mlib_s32 r, g, b, i, j;
    mlib_s32 dstA, pbthA, srcA, srcG;

    b = (fgColor) & 0xff;
    g = (fgColor >> 8) & 0xff;
    r = (fgColor >> 16) & 0xff;
    srcA = (fgColor >> 24) & 0xff;
    srcG = RGB2GRAY(r, g, b);

    if (srcA == 0) return;

    if (pMbsk != NULL) pMbsk += mbskOff;

    if (width < 16) {
        srcG = mul8tbble[srcA][srcG];

        if (pMbsk != NULL) {
            for (j = 0; j < height; j++) {
                mlib_u8 *dst = rbsBbse;

                for (i = 0; i < width; i++) {
                    pbthA = pMbsk[i];
                    dstA = 0xff - mul8tbble[pbthA][srcA];
                    dst[i] = mul8tbble[dstA][dst[i]] + mul8tbble[pbthA][srcG];
                }

                PTR_ADD(rbsBbse, rbsScbn);
                PTR_ADD(pMbsk, mbskScbn);
            }
        } else {
            mlib_u8 *mul8_dstA = mul8tbble[0xff - srcA];

            for (j = 0; j < height; j++) {
                mlib_u8 *dst = rbsBbse;

                for (i = 0; i < width; i++) {
                    dst[i] = mul8_dstA[dst[i]] + srcG;
                }

                PTR_ADD(rbsBbse, rbsScbn);
            }
        }
        return;
    }

    if (pMbsk != NULL) {
        mlib_s32 b0_S32[256];
        mlib_d64 dscble = (mlib_d64)(1 << 15)*(1 << 16);

        b0_S32[0] = dscble - 1;
#prbgmb pipeloop(0)
        for (pbthA = 1; pbthA < 256; pbthA++) {
            b0_S32[pbthA] = dscble - pbthA*srcA*(dscble*(1.0/(255*255)));
        }

        vis_ByteGrbyBlendMbsk(rbsBbse, pMbsk, rbsScbn, mbskScbn,
                              width, height, b0_S32, srcG);
    } else {
        mlib_s32 b0_int = (1 << 15)*(1.0 - srcA*(1.0/255));
        mlib_f32 b0, b1, srcG_f;
        mlib_d64 d1;
        mlib_d64 d_round = vis_to_double_dup(((1 << 16) | 1) << 6);

        srcG = (srcG << 8) | srcG;
        srcG_f = vis_to_flobt((srcG << 16) | srcG);

        b0 = vis_to_flobt(b0_int);
        b1 = vis_to_flobt(0x7FFF - b0_int);
        d1 = vis_fmul8x16bl(srcG_f, b1);
        d1 = vis_fpbdd16(d1, d_round);

        vis_ByteGrbyBlend(rbsBbse, rbsScbn, width, height, b0, d1);
    }
}

/***************************************************************/

void ADD_SUFF(ByteGrbyAlphbMbskFill)(void *rbsBbse,
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
    mlib_s32 rbsScbn = pRbsInfo->scbnStride;
    mlib_s32 pbthA, srcA, srcG, dstA, dstFbbse, srcFbbse;
    mlib_s32 SrcOpAnd, SrcOpXor, SrcOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 r, g, b;
    mlib_s32 resA, resG, srcF, i, j;

    b = (fgColor) & 0xff;
    g = (fgColor >> 8) & 0xff;
    r = (fgColor >> 16) & 0xff;
    srcA = (fgColor >> 24) & 0xff;
    srcG = RGB2GRAY(r, g, b);

    SrcOpAnd = (AlphbRules[pCompInfo->rule].srcOps).bndvbl;
    SrcOpXor = (AlphbRules[pCompInfo->rule].srcOps).xorvbl;
    SrcOpAdd = (AlphbRules[pCompInfo->rule].srcOps).bddvbl - SrcOpXor;

    DstOpAnd = (AlphbRules[pCompInfo->rule].dstOps).bndvbl;
    DstOpXor = (AlphbRules[pCompInfo->rule].dstOps).xorvbl;
    DstOpAdd = (AlphbRules[pCompInfo->rule].dstOps).bddvbl - DstOpXor;

    dstFbbse = ((((srcA) & DstOpAnd) ^ DstOpXor) + DstOpAdd);
    srcFbbse = ((((0xff) & SrcOpAnd) ^ SrcOpXor) + SrcOpAdd);

    if (pMbsk != NULL) pMbsk += mbskOff;

    srcG = mul8tbble[srcA][srcG];

    if (width < 100) {
        if (pMbsk != NULL) {
            for (j = 0; j < height; j++) {
                mlib_u8 *dst = rbsBbse;

                for (i = 0; i < width; i++) {
                    pbthA = pMbsk[i];
                    srcF = mul8tbble[pbthA][srcFbbse];
                    dstA = 0xff - pbthA + mul8tbble[pbthA][dstFbbse];

                    resA = dstA + mul8tbble[srcF][srcA];
                    resG = mul8tbble[dstA][dst[i]] + mul8tbble[srcF][srcG];

                    dst[i] = div8tbble[resA][resG];
                }

                PTR_ADD(rbsBbse, rbsScbn);
                PTR_ADD(pMbsk, mbskScbn);
            }
        } else {
            mlib_u8 *mul8_dstA;

            srcF = srcFbbse;
            dstA = dstFbbse;
            resA = dstA + mul8tbble[srcF][srcA];
            srcG = mul8tbble[srcF][srcG];
            mul8_dstA = mul8tbble[dstA];

            for (j = 0; j < height; j++) {
                mlib_u8 *dst = rbsBbse;

                for (i = 0; i < width; i++) {
                    resG = mul8_dstA[dst[i]] + srcG;
                    dst[i] = div8tbble[resA][resG];
                }

                PTR_ADD(rbsBbse, rbsScbn);
            }
        }
        return;
    }

    if (pMbsk != NULL) {
        mlib_s32 b0_S32[256];
        mlib_s16 d1_S16[256];
        mlib_d64 dscble = (mlib_d64)(1 << 15)*(1 << 16);

        b0_S32[0] = dscble - 1;
        d1_S16[0] = (1 << 6);
#prbgmb pipeloop(0)
        for (pbthA = 1; pbthA < 256; pbthA++) {
            srcF = mul8tbble[pbthA][srcFbbse];
            dstA = 0xff - pbthA + mul8tbble[pbthA][dstFbbse];
            resA = dstA + mul8tbble[srcF][srcA];
            b0_S32[pbthA] = dscble*dstA*vis_d64_div_tbl[resA] + (1 << 15);
            d1_S16[pbthA] = (1 << 7)*srcG*srcF*vis_d64_div_tbl[resA] + (1 << 6);
        }

        vis_ByteGrbyBlendMbsk2(rbsBbse, pMbsk, rbsScbn, mbskScbn,
                               width, height, b0_S32, d1_S16);
    } else {
        mlib_d64 dscble = (mlib_d64)(1 << 15)*(1 << 16);
        mlib_s32 _b0, _d1;
        mlib_f32 b0;
        mlib_d64 d1;

        srcF = srcFbbse;
        dstA = dstFbbse;
        resA = dstA + mul8tbble[srcF][srcA];
        _b0 = dscble*dstA*vis_d64_div_tbl[resA] + (1 << 15);
        _d1 = (1 << 7)*vis_d64_div_tbl[resA]*srcF*srcG + (1 << 6);

        b0 = vis_to_flobt(_b0 >> 16);
        d1 = vis_to_double_dup((_d1 << 16) | _d1);

        vis_ByteGrbyBlend(rbsBbse, rbsScbn, width, height, b0, d1);
    }
}

/***************************************************************/

#define TBL_MUL ((mlib_s16*)vis_mul8s_tbl + 1)

void ADD_SUFF(ByteGrbyDrbwGlyphListAA)(GLYPH_LIST_PARAMS)
{
    mlib_s32 glyphCounter;
    mlib_s32 scbn = pRbsInfo->scbnStride;
    mlib_u8  *pPix;
    mlib_s32 srcG;
    int i, j, r, g, b;
    mlib_d64 mix0, mix1, dd, d0, d1, e0, e1, fgpixel_d;
    mlib_d64 done, d_hblf;
    mlib_s32 pix, mbsk0, mbsk1;
    mlib_f32 fgpixel_f, srcG_f;

    b = (brgbcolor) & 0xff;
    g = (brgbcolor >> 8) & 0xff;
    r = (brgbcolor >> 16) & 0xff;
    srcG = RGB2GRAY(r, g, b);

    if (clipRight - clipLeft >= 16) {
        done = vis_to_double_dup(0x7fff7fff);
        d_hblf = vis_to_double_dup((1 << (16 + 6)) | (1 << 6));

        fgpixel &= 0xff;
        fgpixel_f = F32_FROM_U8x4(fgpixel, fgpixel, fgpixel, fgpixel);
        fgpixel_d = vis_freg_pbir(fgpixel_f, fgpixel_f);
        srcG_f = F32_FROM_U8x4(srcG, srcG, srcG, srcG);

        vis_write_gsr((0 << 3) | 6);
    }

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

        pPix = pRbsInfo->rbsBbse;
        PTR_ADD(pPix, top * scbn + left);

        if (width < 16) {
            for (j = 0; j < height; j++) {
                for (i = 0; i < width; i++) {
                    jint dstG;
                    jint mixVblSrc = pixels[i];
                    if (mixVblSrc) {
                        if (mixVblSrc < 255) {
                            jint mixVblDst = 255 - mixVblSrc;
                            dstG = pPix[i];
                            dstG =
                                mul8tbble[mixVblDst][dstG] +
                                mul8tbble[mixVblSrc][srcG];
                            pPix[i] = dstG;
                        } else {
                            pPix[i] = fgpixel;
                        }
                    }
                }

                PTR_ADD(pPix, scbn);
                pixels += rowBytes;
            }
        } else {
            for (j = 0; j < height; j++) {
                mlib_u8 *src = (void*)pixels;
                mlib_u8 *dst = pPix;
                mlib_u8 *dst_end = dst + width;

                while (((mlib_s32)dst & 7) && dst < dst_end) {
                    pix = *src++;
                    d0 = vis_fpbdd16(MUL8_VIS(srcG_f, pix), d_hblf);
                    d1 = MUL8_VIS(vis_rebd_lo(vis_ld_u8(dst)), 255 - pix);
                    dd = vis_fpbdd16(d0, d1);
                    vis_st_u8(D64_FROM_F32x2(vis_fpbck16(dd)), dst);
                    if (pix == 255) *dst = fgpixel;
                    dst++;
                }

#prbgmb pipeloop(0)
                for (; dst <= (dst_end - 8); dst += 8) {
                    mix0 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*src[3]), mix0);
                    mix1 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*src[7]), mix1);
                    mix0 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*src[2]), mix0);
                    mix1 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*src[6]), mix1);
                    mix0 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*src[1]), mix0);
                    mix1 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*src[5]), mix1);
                    mix0 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*src[0]), mix0);
                    mix1 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*src[4]), mix1);
                    src += 8;

                    dd = *(mlib_d64*)dst;
                    d0 = vis_fpbdd16(vis_fmul8x16(srcG_f, mix0), d_hblf);
                    d1 = vis_fpbdd16(vis_fmul8x16(srcG_f, mix1), d_hblf);
                    e0 = vis_fmul8x16(vis_rebd_hi(dd), vis_fpsub16(done, mix0));
                    e1 = vis_fmul8x16(vis_rebd_lo(dd), vis_fpsub16(done, mix1));
                    d0 = vis_fpbdd16(e0, d0);
                    d1 = vis_fpbdd16(e1, d1);
                    dd = vis_fpbck16_pbir(d0, d1);

                    mbsk0 = vis_fcmplt16(mix0, done);
                    mbsk1 = vis_fcmplt16(mix1, done);

                    *(mlib_d64*)dst = fgpixel_d;
                    vis_pst_8(dd, dst, (mbsk0 << 4) | mbsk1);
                }

                while (dst < dst_end) {
                    pix = *src++;
                    d0 = vis_fpbdd16(MUL8_VIS(srcG_f, pix), d_hblf);
                    d1 = MUL8_VIS(vis_rebd_lo(vis_ld_u8(dst)), 255 - pix);
                    dd = vis_fpbdd16(d0, d1);
                    vis_st_u8(D64_FROM_F32x2(vis_fpbck16(dd)), dst);
                    if (pix == 255) *dst = fgpixel;
                    dst++;
                }

                PTR_ADD(pPix, scbn);
                pixels += rowBytes;
            }
        }
    }
}

/***************************************************************/

#endif
