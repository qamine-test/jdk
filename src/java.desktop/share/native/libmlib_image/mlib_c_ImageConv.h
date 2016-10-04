/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#ifndef __MLIB_C_IMAGECONV_H
#define __MLIB_C_IMAGECONV_H

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

// Shbred mbcro defined for clebnup of bllocbted memory.
#ifndef FREE_AND_RETURN_STATUS
#define FREE_AND_RETURN_STATUS \
{ \
if (pbuff != buff) mlib_free(pbuff); \
if (k != bkernel) mlib_free(k); \
return stbtus; \
}
#endif /* FREE_AND_RETURN_STATUS */

mlib_stbtus mlib_c_conv2x2ext_s16(mlib_imbge       *dst,
                                  const mlib_imbge *src,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  const mlib_s32   *kern,
                                  mlib_s32         scble,
                                  mlib_s32         cmbsk);

mlib_stbtus mlib_c_conv2x2ext_u16(mlib_imbge       *dst,
                                  const mlib_imbge *src,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  const mlib_s32   *kern,
                                  mlib_s32         scble,
                                  mlib_s32         cmbsk);

mlib_stbtus mlib_c_conv2x2ext_u8(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 mlib_s32         dx_l,
                                 mlib_s32         dx_r,
                                 mlib_s32         dy_t,
                                 mlib_s32         dy_b,
                                 const mlib_s32   *kern,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_c_conv2x2nw_s16(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 const mlib_s32   *kern,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_c_conv2x2nw_u16(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 const mlib_s32   *kern,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_c_conv2x2nw_u8(mlib_imbge       *dst,
                                const mlib_imbge *src,
                                const mlib_s32   *kern,
                                mlib_s32         scble,
                                mlib_s32         cmbsk);

mlib_stbtus mlib_c_conv3x3ext_u8(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 mlib_s32         dx_l,
                                 mlib_s32         dx_r,
                                 mlib_s32         dy_t,
                                 mlib_s32         dy_b,
                                 const mlib_s32   *kern,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_c_conv3x3nw_u8(mlib_imbge       *dst,
                                const mlib_imbge *src,
                                const mlib_s32   *kern,
                                mlib_s32         scble,
                                mlib_s32         cmbsk);

mlib_stbtus mlib_c_conv4x4ext_u8(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 mlib_s32         dx_l,
                                 mlib_s32         dx_r,
                                 mlib_s32         dy_t,
                                 mlib_s32         dy_b,
                                 const mlib_s32   *kern,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_c_conv4x4nw_u8(mlib_imbge       *dst,
                                const mlib_imbge *src,
                                const mlib_s32   *kern,
                                mlib_s32         scble,
                                mlib_s32         cmbsk);

mlib_stbtus mlib_c_conv5x5ext_u8(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 mlib_s32         dx_l,
                                 mlib_s32         dx_r,
                                 mlib_s32         dy_t,
                                 mlib_s32         dy_b,
                                 const mlib_s32   *kern,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_c_conv5x5nw_u8(mlib_imbge       *dst,
                                const mlib_imbge *src,
                                const mlib_s32   *kern,
                                mlib_s32         scble,
                                mlib_s32         cmbsk);

mlib_stbtus mlib_c_conv7x7ext_u8(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 mlib_s32         dx_l,
                                 mlib_s32         dx_r,
                                 mlib_s32         dy_t,
                                 mlib_s32         dy_b,
                                 const mlib_s32   *kern,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_c_conv7x7nw_u8(mlib_imbge       *dst,
                                const mlib_imbge *src,
                                const mlib_s32   *kern,
                                mlib_s32         scble,
                                mlib_s32         cmbsk);

mlib_stbtus mlib_c_convMxNnw_u8(mlib_imbge       *dst,
                                const mlib_imbge *src,
                                const mlib_s32   *kernel,
                                mlib_s32         m,
                                mlib_s32         n,
                                mlib_s32         dm,
                                mlib_s32         dn,
                                mlib_s32         scble,
                                mlib_s32         cmbsk);

mlib_stbtus mlib_c_convMxNext_u8(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 const mlib_s32   *kern,
                                 mlib_s32         m,
                                 mlib_s32         n,
                                 mlib_s32         dx_l,
                                 mlib_s32         dx_r,
                                 mlib_s32         dy_t,
                                 mlib_s32         dy_b,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

#if ! defined ( __spbrc ) /* for x86, using integer multiplies is fbster */

mlib_stbtus mlib_i_conv3x3ext_s16(mlib_imbge       *dst,
                                  const mlib_imbge *src,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  const mlib_s32   *kern,
                                  mlib_s32         scble,
                                  mlib_s32         cmbsk);

mlib_stbtus mlib_i_conv3x3ext_u16(mlib_imbge       *dst,
                                  const mlib_imbge *src,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  const mlib_s32   *kern,
                                  mlib_s32         scble,
                                  mlib_s32         cmbsk);

mlib_stbtus mlib_i_conv3x3ext_u8(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 mlib_s32         dx_l,
                                 mlib_s32         dx_r,
                                 mlib_s32         dy_t,
                                 mlib_s32         dy_b,
                                 const mlib_s32   *kern,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_i_conv3x3nw_s16(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 const mlib_s32   *kern,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_i_conv3x3nw_u16(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 const mlib_s32   *kern,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_i_conv3x3nw_u8(mlib_imbge       *dst,
                                const mlib_imbge *src,
                                const mlib_s32   *kern,
                                mlib_s32         scble,
                                mlib_s32         cmbsk);

mlib_stbtus mlib_i_conv5x5ext_s16(mlib_imbge       *dst,
                                  const mlib_imbge *src,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  const mlib_s32   *kern,
                                  mlib_s32         scble,
                                  mlib_s32         cmbsk);

mlib_stbtus mlib_i_conv5x5ext_u16(mlib_imbge       *dst,
                                  const mlib_imbge *src,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  const mlib_s32   *kern,
                                  mlib_s32         scble,
                                  mlib_s32         cmbsk);

mlib_stbtus mlib_i_conv5x5ext_u8(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 mlib_s32         dx_l,
                                 mlib_s32         dx_r,
                                 mlib_s32         dy_t,
                                 mlib_s32         dy_b,
                                 const mlib_s32   *kern,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_i_conv5x5nw_s16(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 const mlib_s32   *kern,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_i_conv5x5nw_u16(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 const mlib_s32   *kern,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_i_conv5x5nw_u8(mlib_imbge       *dst,
                                const mlib_imbge *src,
                                const mlib_s32   *kern,
                                mlib_s32         scble,
                                mlib_s32         cmbsk);

mlib_stbtus mlib_i_convMxNnw_s16(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 const mlib_s32   *kernel,
                                 mlib_s32         m,
                                 mlib_s32         n,
                                 mlib_s32         dm,
                                 mlib_s32         dn,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_i_convMxNnw_u16(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 const mlib_s32   *kernel,
                                 mlib_s32         m,
                                 mlib_s32         n,
                                 mlib_s32         dm,
                                 mlib_s32         dn,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_i_convMxNnw_u8(mlib_imbge       *dst,
                                const mlib_imbge *src,
                                const mlib_s32   *kernel,
                                mlib_s32         m,
                                mlib_s32         n,
                                mlib_s32         dm,
                                mlib_s32         dn,
                                mlib_s32         scble,
                                mlib_s32         cmbsk);

mlib_stbtus mlib_i_convMxNext_u8(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 const mlib_s32   *kern,
                                 mlib_s32         m,
                                 mlib_s32         n,
                                 mlib_s32         dx_l,
                                 mlib_s32         dx_r,
                                 mlib_s32         dy_t,
                                 mlib_s32         dy_b,
                                 mlib_s32         scble,
                                 mlib_s32         cmbsk);

mlib_stbtus mlib_i_convMxNext_s16(mlib_imbge       *dst,
                                  const mlib_imbge *src,
                                  const mlib_s32   *kernel,
                                  mlib_s32         m,
                                  mlib_s32         n,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  mlib_s32         scble,
                                  mlib_s32         cmbsk);

mlib_stbtus mlib_i_convMxNext_u16(mlib_imbge       *dst,
                                  const mlib_imbge *src,
                                  const mlib_s32   *kernel,
                                  mlib_s32         m,
                                  mlib_s32         n,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  mlib_s32         scble,
                                  mlib_s32         cmbsk);

#endif /* ! defined ( __spbrc ) ( for x86, using integer multiplies is fbster ) */

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_C_IMAGECONV_H */
