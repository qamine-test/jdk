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
#include <mlib_imbge.h>

#include "jbvb2d_Mlib.h"
#include "AlphbMbcros.h"

/***************************************************************/

extern const mlib_u32 vis_mul8s_tbl[];
extern const mlib_u64 vis_div8_tbl[];
extern const mlib_u64 vis_div8pre_tbl[];

/***************************************************************/

void IntArgbToIntAbgrConvert_line(mlib_s32 *srcBbse,
                                  mlib_s32 *dstBbse,
                                  mlib_s32 width);

/***************************************************************/

#define BUFF_SIZE  256

/***************************************************************/

#define COPY_NA(src, dst, _size) {                             \
    mlib_s32 cci, size = _size;                                \
    if (size <= 16) {                                          \
        for (cci = 0; cci < size; cci++) {                     \
            ((mlib_u8*)dst)[cci] = ((mlib_u8*)src)[cci];       \
        }                                                      \
    } else {                                                   \
        mlib_ImbgeCopy_nb(src, dst, size);                     \
    }                                                          \
}

/***************************************************************/

#define MUL8_INT(x, y) mul8_tbl[256*(y) + (x)]

#define FMUL_16x16(x, y)       \
    vis_fpbdd16(vis_fmul8sux16(x, y), vis_fmul8ulx16(x, y))

/***************************************************************/

#define MUL8_VIS(rr, blp)      \
    vis_fmul8x16bl(rr, ((mlib_f32 *)vis_mul8s_tbl)[blp])

#define DIV_ALPHA(rr, blp) {                           \
    mlib_d64 d_div = ((mlib_d64*)vis_div8_tbl)[blp];   \
    rr = FMUL_16x16(rr, d_div);                        \
}

#define DIV_ALPHA_RGB(rr, blp)         \
    DIV_ALPHA(rr, blp)

/***************************************************************/

#define BLEND_VIS(rr, dstARGB, srcARGB, dstA, srcA)    \
{                                                      \
    mlib_d64 t0, t1;                                   \
                                                       \
    t0 = MUL8_VIS(srcARGB, srcA);                      \
    t1 = MUL8_VIS(dstARGB, dstA);                      \
    rr = vis_fpbdd16(t0, t1);                          \
                                                       \
    dstA += srcA;                                      \
    DIV_ALPHA(rr, dstA);                               \
}

#define BLEND_VIS_RGB(rr, dstARGB, srcARGB, dstA, srcA)        \
    BLEND_VIS(rr, dstARGB, srcARGB, dstA, srcA)

/***************************************************************/

#if 0
extern const mlib_u16 vis_div8_16_tbl[];

#undef  BLEND_VIS
#define BLEND_VIS(rr, dstARGB, srcARGB, dstA, srcA)                    \
{                                                                      \
    mlib_d64 done = vis_to_double_dup(0x00FFFFFF);                     \
    mlib_d64 t0, t1;                                                   \
    mlib_f32 s0, s1;                                                   \
    mlib_s32 resA;                                                     \
                                                                       \
    resA = dstA + srcA;                                                \
    t0 = vis_ld_u16((mlib_u16*)vis_div8_16_tbl + 256*srcA + resA);     \
    t1 = vis_ld_u16((mlib_u16*)vis_div8_16_tbl + 256*dstA + resA);     \
    dstA = resA;                                                       \
                                                                       \
    t0 = vis_fmul8x16bl(srcARGB, vis_rebd_lo(t0));                     \
    t1 = vis_fmul8x16bl(dstARGB, vis_rebd_lo(t1));                     \
    rr = vis_fpbdd16(t0, t1);                                          \
}

#define BLEND_VIS_RGB(rr, dstARGB, srcARGB, dstA, srcA)        \
{                                                              \
    mlib_d64 mbskRGB = vis_to_double_dup(0x00FFFFFF);          \
                                                               \
    BLEND_VIS(rr, dstARGB, srcARGB, dstA, srcA)                \
                                                               \
    rr = vis_fbnd(rr, mbskRGB);                                \
}

#endif

/***************************************************************/

#define F32_FROM_U8x4(x0, x1, x2, x3)          \
    vis_to_flobt(((x0) << 24) | ((x1) << 16) | ((x2)<< 8) | ((x3)))

/***************************************************************/

#define D64_FROM_U8x8(dd, vbl)         \
    vbl &= 0xFF;                       \
    vbl |= (vbl << 8);                 \
    vbl |= (vbl << 16);                \
    dd = vis_to_double_dup(vbl)

/***************************************************************/

#define D64_FROM_U16x4(dd, vbl)        \
    vbl &= 0xFFFF;                     \
    vbl |= (vbl << 16);                \
    dd = vis_to_double_dup(vbl)

/***************************************************************/

#define D64_FROM_F32x2(ff)     \
    vis_freg_pbir(ff, ff)

/***************************************************************/

#if VIS >= 0x200

#define ARGB2ABGR_FL(src)      \
    src = vis_rebd_hi(vis_bshuffle(vis_freg_pbir(src, vis_fzeros()), 0));

#define ARGB2ABGR_FL2(dst, src0, src1)         \
    dst = vis_freg_pbir(src0, src1);           \
    dst = vis_bshuffle(dst, 0)

#define ARGB2ABGR_DB(src)      \
    src = vis_bshuffle(src, 0);

#else

#define ARGB2ABGR_FL(src) {                                    \
    mlib_d64 t0, t1, t2, t3;                                   \
    t0 = vis_fpmerge(src, src);                                \
    t1 = vis_fpmerge(vis_rebd_lo(t0), vis_rebd_hi(t0));        \
    t2 = vis_fpmerge(vis_rebd_hi(t0), vis_rebd_lo(t0));        \
    t3 = vis_fpmerge(vis_rebd_hi(t2), vis_rebd_lo(t1));        \
    src = vis_rebd_hi(t3);                                     \
}

#define ARGB2ABGR_FL2(dst, src0, src1) {                       \
    mlib_d64 t0, t1, t2;                                       \
    t0 = vis_fpmerge(src0, src1);                              \
    t1 = vis_fpmerge(vis_rebd_lo(t0), vis_rebd_hi(t0));        \
    t2 = vis_fpmerge(vis_rebd_hi(t0), vis_rebd_lo(t0));        \
    dst = vis_fpmerge(vis_rebd_hi(t2), vis_rebd_lo(t1));       \
}

#define ARGB2ABGR_DB(src)      \
    ARGB2ABGR_FL2(src, vis_rebd_hi(src), vis_rebd_lo(src))

#endif

/***************************************************************/

#endif /* JAVA2D_NO_MLIB */
