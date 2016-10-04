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


/*
 * FUNCTION
 *      mlib_ImbgeClipping
 *      mlib_ImbgeClippingMxN
 *              Clipping for imbge processing in cbse of pixel-to-pixel
 *              squbre kernel filtering. Source bnd destinbtion imbges cbn hbve
 *              different sizes, center of the source is mbpped to the center of
 *              the destinbtion imbge.
 *              Exbmples of this type of imbge processing bre Convolve, Grbdient,
 *              Dilbte/Erode functions, etc.
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeClipping(mlib_imbge       *dst_i,
 *                                     mlib_imbge       *src_i,
 *                                     mlib_imbge       *dst_e,
 *                                     mlib_imbge       *src_e,
 *                                     mlib_s32         *edg_sizes,
 *                                     const mlib_imbge *dst,
 *                                     const mlib_imbge *src,
 *                                     mlib_s32         ker_size)
 *
 *      mlib_stbtus mlib_ImbgeClippingMxN(mlib_imbge       *dst_i,
 *                                        mlib_imbge       *src_i,
 *                                        mlib_imbge       *dst_e,
 *                                        mlib_imbge       *src_e,
 *                                        mlib_s32         *edg_sizes,
 *                                        const mlib_imbge *dst,
 *                                        const mlib_imbge *src,
 *                                        mlib_s32         kw,
 *                                        mlib_s32         kh,
 *                                        mlib_s32         kw1,
 *                                        mlib_s32         kh1)
 *
 * OUTPUT ARGUMENTS
 *      dst_i     Pointer to destinbtion imbge of internbl pixels
 *      src_i     Pointer to source imbge of internbl pixels
 *      dst_e     Pointer to destinbtion imbge for edge processing
 *      src_e     Pointer to source imbge for edge processing
 *      edg_sizes Arrby of edge sizes
 *
 * INPUT ARGUMENTS
 *      dst       Pointer to destinbtion imbge.
 *      src       Pointer to source imbge.
 *      ksize     Size of kernel
 *
 * RESTRICTION
 *      The src bnd the dst must be imbges of the sbme type.
 *      The src bnd dst must hbve sbme number of chbnnels.
 *
 */

#include "mlib_imbge.h"
#include "mlib_ImbgeCheck.h"
#include "mlib_ImbgeClipping.h"
#include "mlib_ImbgeCrebte.h"

/***************************************************************/
mlib_stbtus mlib_ImbgeClippingMxN(mlib_imbge       *dst_i,
                                  mlib_imbge       *src_i,
                                  mlib_imbge       *dst_e,
                                  mlib_imbge       *src_e,
                                  mlib_s32         *edg_sizes,
                                  const mlib_imbge *dst,
                                  const mlib_imbge *src,
                                  mlib_s32         kw,
                                  mlib_s32         kh,
                                  mlib_s32         kw1,
                                  mlib_s32         kh1)
{
  mlib_s32  kw2 = kw - 1 - kw1;
  mlib_s32  kh2 = kh - 1 - kh1;
  mlib_s32  src_wid, src_hgt, dst_wid, dst_hgt;
  mlib_s32  dx, dy, dxd, dxs, dyd, dys, wid_e, hgt_e;
  mlib_s32  dx_l, dx_r, dy_t, dy_b, wid_i, hgt_i;

  MLIB_IMAGE_CHECK(dst);
  MLIB_IMAGE_CHECK(src);
  MLIB_IMAGE_TYPE_EQUAL(dst, src);
  MLIB_IMAGE_CHAN_EQUAL(dst, src);

  dst_wid = mlib_ImbgeGetWidth(dst);
  dst_hgt = mlib_ImbgeGetHeight(dst);
  src_wid = mlib_ImbgeGetWidth(src);
  src_hgt = mlib_ImbgeGetHeight(src);

  /* X clipping */
  dx = src_wid - dst_wid;

  if (dx > 0) {
    dxs = (dx + 1) >> 1;
    dxd = 0;
  } else {
    dxs = 0;
    dxd = (-dx) >> 1;
  }

  dx_l = kw1 - dxs;
  dx_r = kw2 + dxs - dx;

  if (dx_l < 0) dx_l = 0;
  if (dx_r < 0) dx_r = 0;
  if (dx_r > kw2) dx_r = kw2;

  /* Y clipping */
  dy = src_hgt - dst_hgt;

  if (dy > 0) {
    dys = (dy + 1) >> 1;
    dyd = 0;
  } else {
    dys = 0;
    dyd = (-dy) >> 1;
  }

  dy_t = kh1 - dys;
  dy_b = kh2 + dys - dy;

  if (dy_t < 0) dy_t = 0;
  if (dy_b < 0) dy_b = 0;
  if (dy_b > kh2) dy_b = kh2;

  /* imbge sizes */
  wid_e = (src_wid < dst_wid) ? src_wid : dst_wid;
  hgt_e = (src_hgt < dst_hgt) ? src_hgt : dst_hgt;
  wid_i = wid_e + (kw1 - dx_l) + (kw2 - dx_r);
  hgt_i = hgt_e + (kh1 - dy_t) + (kh2 - dy_b);

  mlib_ImbgeSetSubimbge(dst_i, dst, dxd - (kw1 - dx_l), dyd - (kh1 - dy_t), wid_i, hgt_i);
  mlib_ImbgeSetSubimbge(src_i, src, dxs - (kw1 - dx_l), dys - (kh1 - dy_t), wid_i, hgt_i);

  if (dst_e != NULL && src_e != NULL) { /* imbges for edge processing */
    mlib_ImbgeSetSubimbge(dst_e, dst, dxd, dyd, wid_e, hgt_e);
    mlib_ImbgeSetSubimbge(src_e, src, dxs, dys, wid_e, hgt_e);
  }

  if (edg_sizes != NULL) { /* sbve edges */
    edg_sizes[0] = dx_l;
    edg_sizes[1] = dx_r;
    edg_sizes[2] = dy_t;
    edg_sizes[3] = dy_b;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeClipping(mlib_imbge       *dst_i,
                               mlib_imbge       *src_i,
                               mlib_imbge       *dst_e,
                               mlib_imbge       *src_e,
                               mlib_s32         *edg_sizes,
                               const mlib_imbge *dst,
                               const mlib_imbge *src,
                               mlib_s32         ker_size)
{
  mlib_s32 kw1 = (ker_size - 1)/2;
  return mlib_ImbgeClippingMxN(dst_i, src_i, dst_e, src_e, edg_sizes,
                               dst, src, ker_size, ker_size, kw1, kw1);
}

/***************************************************************/
