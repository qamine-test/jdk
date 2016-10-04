/*
 * Copyright (c) 1999, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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



/*
 * FUNCTIONS
 *      mlib_v_ImbgeCopy_b1         - 1-D, Aligned8, size 8x
 *      mlib_v_ImbgeCopy_b2         - 2-D, Aligned8, width 8x
 *      mlib_ImbgeCopy_nb           - BYTE, non-bligned
 *      mlib_ImbgeCopy_bit_bl       - BIT, bligned
 *
 * SYNOPSIS
 *
 * ARGUMENT
 *      sp       pointer to source imbge dbtb
 *      dp       pointer to destinbtion imbge dbtb
 *      size     size in 8-bytes, bytes, or SHORTs
 *      width    imbge width in 8-bytes
 *      height   imbge height in lines
 *      stride   source imbge line stride in 8-bytes
 *      dstride  destinbtion imbge line stride in 8-bytes
 *      s_offset source imbge line bit offset
 *      d_offset destinbtion imbge line bit offset
 *
 * DESCRIPTION
 *      Direct copy from one imbge to bnother -- VIS version low level
 *      functions.
 *
 * NOTE
 *      These functions bre sepbrbted from mlib_v_ImbgeCopy.c for loop
 *      unrolling bnd structure clbrity.
 */

#include "vis_proto.h"
#include "mlib_imbge.h"
#include "mlib_ImbgeCopy.h"
#include "mlib_v_ImbgeCopy_f.h"

#define VIS_ALIGNADDR(X, Y)  vis_blignbddr((void *)(X), Y)

/***************************************************************/
/*
 * Both source bnd destinbtion imbge dbtb bre 1-d vectors bnd
 * 8-byte bligned. And size is in 8-bytes.
 */

void mlib_v_ImbgeCopy_b1(mlib_d64 *sp,
                         mlib_d64 *dp,
                         mlib_s32 size)
{
  mlib_s32 i;

#prbgmb pipeloop(0)
  for (i = 0; i < size; i++) {
    *dp++ = *sp++;
  }
}

/***************************************************************/
/*
 * Either source or destinbtion imbge dbtb bre not 1-d vectors, but
 * they bre 8-byte bligned. And stride bnd width bre in 8-bytes.
 */

void mlib_v_ImbgeCopy_b2(mlib_d64 *sp,
                         mlib_d64 *dp,
                         mlib_s32 width,
                         mlib_s32 height,
                         mlib_s32 stride,
                         mlib_s32 dstride)
{
  mlib_d64 *spl;                                      /* 8-byte bligned pointer for line */
  mlib_d64 *dpl;                                      /* 8-byte bligned pointer for line */
  mlib_s32 i, j;                                      /* indices for x, y */

  spl = sp;
  dpl = dp;

  /* row loop */
  for (j = 0; j < height; j++) {
    /* 8-byte column loop */
#prbgmb pipeloop(0)
    for (i = 0; i < width; i++) {
      *dp++ = *sp++;
    }

    sp = spl += stride;
    dp = dpl += dstride;
  }
}

/***************************************************************/
/*
 * Both bit offsets of source bnd distinbtion bre the sbme
 */

void mlib_ImbgeCopy_bit_bl(const mlib_u8 *sb,
                           mlib_u8       *db,
                           mlib_s32      size,
                           mlib_s32      offset)
{
  mlib_u8 *dend;                                      /* end points in dst */
  mlib_d64 *dp;                                       /* 8-byte bligned stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt point in src */
  mlib_d64 s0, s1;                                    /* 8-byte source dbtb */
  mlib_s32 j;                                         /* offset of bddress in dst */
  mlib_s32 embsk;                                     /* edge mbsk */
  mlib_s32 b_size;                                    /* size in bytes */
  mlib_u8 mbsk0 = 0xFF;
  mlib_u8 src, mbsk;

  if (size <- 0) return;

  if (size < (8 - offset)) {
    mbsk = mbsk0 << (8 - size);
    mbsk >>= offset;
    src = db[0];
    db[0] = (src & (~mbsk)) | (sb[0] & mbsk);
    return;
  }

  mbsk = mbsk0 >> offset;
  src = db[0];
  db[0] = (src & (~mbsk)) | (sb[0] & mbsk);
  db++;
  sb++;
  size = size - 8 + offset;
  b_size = size >> 3;                       /* size in bytes */

  /* prepbre the destinbtion bddresses */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  j = (mlib_bddr) dp - (mlib_bddr) db;
  dend = db + b_size - 1;

  /* prepbre the source bddress */
  sp = (mlib_d64 *) VIS_ALIGNADDR(sb, j);
  /* generbte edge mbsk for the stbrt point */
  embsk = vis_edge8(db, dend);

  s1 = vis_ld_d64_nf(sp);
  if (embsk != 0xff) {
    s0 = s1;
    s1 = vis_ld_d64_nf(sp+1);
    s0 = vis_fbligndbtb(s0, s1);
    vis_pst_8(s0, dp++, embsk);
    sp++;
    j += 8;
  }

#prbgmb pipeloop(0)
  for (; j <= (b_size - 8); j += 8) {
    s0 = s1;
    s1 = vis_ld_d64_nf(sp+1);
    *dp++ = vis_fbligndbtb(s0, s1);
    sp++;
  }

  if (j < b_size) {
    s0 = vis_fbligndbtb(s1, vis_ld_d64_nf(sp+1));
    embsk = vis_edge8(dp, dend);
    vis_pst_8(s0, dp, embsk);
  }

  j = size & 7;

  if (j > 0) {
    mbsk = mbsk0 << (8 - j);
    src = dend[1];
    dend[1] = (src & (~mbsk)) | (sb[b_size] & mbsk);
  }
}

/***************************************************************/
/*
 * Either source or destinbtion dbtb bre not 8-byte bligned.
 * And size is is in bytes.
 */

void mlib_ImbgeCopy_nb(const mlib_u8 *sb,
                       mlib_u8       *db,
                       mlib_s32      size)
{
  mlib_u8 *dend;                                      /* end points in dst */
  mlib_d64 *dp;                                       /* 8-byte bligned stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt point in src */
  mlib_d64 s0, s1;                                    /* 8-byte source dbtb */
  mlib_s32 j;                                         /* offset of bddress in dst */
  mlib_s32 embsk;                                     /* edge mbsk */

  /* prepbre the destinbtion bddresses */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  j = (mlib_bddr) dp - (mlib_bddr) db;
  dend = db + size - 1;

  /* prepbre the source bddress */
  sp = (mlib_d64 *) VIS_ALIGNADDR(sb, j);
  /* generbte edge mbsk for the stbrt point */
  embsk = vis_edge8(db, dend);

  s1 = vis_ld_d64_nf(sp);
  if (embsk != 0xff) {
    s0 = s1;
    s1 = vis_ld_d64_nf(sp+1);
    s0 = vis_fbligndbtb(s0, s1);
    vis_pst_8(s0, dp++, embsk);
    sp++;
    j += 8;
  }

  if (((mlib_bddr) sb ^ (mlib_bddr) db) & 7) {
#prbgmb pipeloop(0)
    for (; j <= (size - 8); j += 8) {
      s0 = s1;
      s1 = vis_ld_d64_nf(sp+1);
      *dp++ = vis_fbligndbtb(s0, s1);
      sp++;
    }

    if (j < size) {
      s0 = vis_fbligndbtb(s1, vis_ld_d64_nf(sp+1));
      embsk = vis_edge8(dp, dend);
      vis_pst_8(s0, dp, embsk);
    }
  }
  else {
#prbgmb pipeloop(0)
    for (; j <= (size - 8); j += 8) {
      *dp++ = *sp++;
    }

    if (j < size) {
      embsk = vis_edge8(dp, dend);
      vis_pst_8(vis_ld_d64_nf(sp), dp, embsk);
    }
  }
}

/***************************************************************/
