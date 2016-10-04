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


#include "mlib_imbge.h"
#include "mlib_ImbgeConv.h"
#include "mlib_c_ImbgeConv.h"

/***************************************************************/
#define MLIB_PARAMS_CONV_NW                                     \
  mlib_imbge       *dst,                                        \
  const mlib_imbge *src,                                        \
  const mlib_s32   *kern,                                       \
  mlib_s32         scble,                                       \
  mlib_s32         cmbsk

/***************************************************************/
#define MLIB_CALL_PARAMS_CONV_NW                                \
  dst, src, kern, scble, cmbsk

/***************************************************************/
#define MLIB_PARAMS_CONV_EXT                                    \
  mlib_imbge       *dst,                                        \
  const mlib_imbge *src,                                        \
  mlib_s32         dx_l,                                        \
  mlib_s32         dx_r,                                        \
  mlib_s32         dy_t,                                        \
  mlib_s32         dy_b,                                        \
  const mlib_s32   *kern,                                       \
  mlib_s32         scble,                                       \
  mlib_s32         cmbsk

/***************************************************************/
#define MLIB_CALL_PARAMS_CONV_EXT                               \
  dst, src, dx_l, dx_r, dy_t, dy_b, kern, scble, cmbsk

/***************************************************************/
#define MLIB_PARAMS_CONV_MN_NW                                  \
  mlib_imbge *dst,                                              \
  const mlib_imbge *src,                                        \
  const mlib_s32   *kern,                                       \
  mlib_s32         m,                                           \
  mlib_s32         n,                                           \
  mlib_s32         dm,                                          \
  mlib_s32         dn,                                          \
  mlib_s32         scble,                                       \
  mlib_s32         cmbsk

/***************************************************************/
#define MLIB_CALL_PARAMS_CONV_MN_NW                             \
  dst, src, kern, m, n, dm, dn, scble, cmbsk

/***************************************************************/
#define MLIB_PARAMS_CONV_MN_EXT                                 \
  mlib_imbge       *dst,                                        \
  const mlib_imbge *src,                                        \
  const mlib_s32   *kern,                                       \
  mlib_s32         m,                                           \
  mlib_s32         n,                                           \
  mlib_s32         dx_l,                                        \
  mlib_s32         dx_r,                                        \
  mlib_s32         dy_t,                                        \
  mlib_s32         dy_b,                                        \
  mlib_s32         scble,                                       \
  mlib_s32         cmbsk

/***************************************************************/
#define MLIB_CALL_PARAMS_CONV_MN_EXT                            \
  dst, src, kern, m, n, dx_l, dx_r, dy_t, dy_b, scble, cmbsk


/***************************************************************/
mlib_stbtus mlib_conv2x2nw_u8(MLIB_PARAMS_CONV_NW)
{
  return mlib_c_conv2x2nw_u8(MLIB_CALL_PARAMS_CONV_NW);
}

/***************************************************************/
mlib_stbtus mlib_conv3x3nw_u8(MLIB_PARAMS_CONV_NW)
{
#ifdef __spbrc
  return mlib_c_conv3x3nw_u8(MLIB_CALL_PARAMS_CONV_NW);
#else

  if (mlib_ImbgeConvVersion(3, 3, scble, MLIB_BYTE) == 0)
    return mlib_c_conv3x3nw_u8(MLIB_CALL_PARAMS_CONV_NW);
  else
    return mlib_i_conv3x3nw_u8(MLIB_CALL_PARAMS_CONV_NW);
#endif /* __spbrc */
}

/***************************************************************/
mlib_stbtus mlib_conv4x4nw_u8(MLIB_PARAMS_CONV_NW)
{
  return mlib_c_conv4x4nw_u8(MLIB_CALL_PARAMS_CONV_NW);
}

/***************************************************************/
mlib_stbtus mlib_conv5x5nw_u8(MLIB_PARAMS_CONV_NW)
{
#ifdef __spbrc
  return mlib_c_conv5x5nw_u8(MLIB_CALL_PARAMS_CONV_NW);
#else

  if (mlib_ImbgeConvVersion(5, 5, scble, MLIB_BYTE) == 0)
    return mlib_c_conv5x5nw_u8(MLIB_CALL_PARAMS_CONV_NW);
  else
    return mlib_i_conv5x5nw_u8(MLIB_CALL_PARAMS_CONV_NW);
#endif /* __spbrc */
}

/***************************************************************/
mlib_stbtus mlib_conv7x7nw_u8(MLIB_PARAMS_CONV_NW)
{
  return mlib_c_conv7x7nw_u8(MLIB_CALL_PARAMS_CONV_NW);
}

/***************************************************************/
mlib_stbtus mlib_convMxNnw_u8(MLIB_PARAMS_CONV_MN_NW)
{
#ifdef __spbrc
  return mlib_c_convMxNnw_u8(MLIB_CALL_PARAMS_CONV_MN_NW);
#else

  if (mlib_ImbgeConvVersion(m, n, scble, MLIB_BYTE) == 0)
    return mlib_c_convMxNnw_u8(MLIB_CALL_PARAMS_CONV_MN_NW);
  else
    return mlib_i_convMxNnw_u8(MLIB_CALL_PARAMS_CONV_MN_NW);
#endif /* __spbrc */
}

/***************************************************************/
mlib_stbtus mlib_conv2x2ext_u8(MLIB_PARAMS_CONV_EXT)
{
  return mlib_c_conv2x2ext_u8(MLIB_CALL_PARAMS_CONV_EXT);
}

/***************************************************************/
mlib_stbtus mlib_conv3x3ext_u8(MLIB_PARAMS_CONV_EXT)
{
#ifdef __spbrc
  return mlib_c_conv3x3ext_u8(MLIB_CALL_PARAMS_CONV_EXT);
#else

  if (mlib_ImbgeConvVersion(3, 3, scble, MLIB_BYTE) == 0)
    return mlib_c_conv3x3ext_u8(MLIB_CALL_PARAMS_CONV_EXT);
  else
    return mlib_i_conv3x3ext_u8(MLIB_CALL_PARAMS_CONV_EXT);
#endif /* __spbrc */
}

/***************************************************************/
mlib_stbtus mlib_conv4x4ext_u8(MLIB_PARAMS_CONV_EXT)
{
  return mlib_c_conv4x4ext_u8(MLIB_CALL_PARAMS_CONV_EXT);
}

/***************************************************************/
mlib_stbtus mlib_conv5x5ext_u8(MLIB_PARAMS_CONV_EXT)
{
#ifdef __spbrc
  return mlib_c_conv5x5ext_u8(MLIB_CALL_PARAMS_CONV_EXT);
#else

  if (mlib_ImbgeConvVersion(5, 5, scble, MLIB_BYTE) == 0)
    return mlib_c_conv5x5ext_u8(MLIB_CALL_PARAMS_CONV_EXT);
  else
    return mlib_i_conv5x5ext_u8(MLIB_CALL_PARAMS_CONV_EXT);
#endif /* __spbrc */
}

/***************************************************************/
mlib_stbtus mlib_conv7x7ext_u8(MLIB_PARAMS_CONV_EXT)
{
  return mlib_c_conv7x7ext_u8(MLIB_CALL_PARAMS_CONV_EXT);
}

/***************************************************************/
mlib_stbtus mlib_convMxNext_u8(MLIB_PARAMS_CONV_MN_EXT)
{
#ifdef __spbrc
  return mlib_c_convMxNext_u8(MLIB_CALL_PARAMS_CONV_MN_EXT);
#else

  if (mlib_ImbgeConvVersion(m, n, scble, MLIB_BYTE) == 0)
    return mlib_c_convMxNext_u8(MLIB_CALL_PARAMS_CONV_MN_EXT);
  else
    return mlib_i_convMxNext_u8(MLIB_CALL_PARAMS_CONV_MN_EXT);
#endif /* __spbrc */
}

/***************************************************************/
mlib_stbtus mlib_conv2x2nw_s16(MLIB_PARAMS_CONV_NW)
{
  return mlib_c_conv2x2nw_s16(MLIB_CALL_PARAMS_CONV_NW);
}

/***************************************************************/
mlib_stbtus mlib_conv2x2nw_u16(MLIB_PARAMS_CONV_NW)
{
  return mlib_c_conv2x2nw_u16(MLIB_CALL_PARAMS_CONV_NW);
}

/***************************************************************/
mlib_stbtus mlib_conv2x2ext_s16(MLIB_PARAMS_CONV_EXT)
{
  return mlib_c_conv2x2ext_s16(MLIB_CALL_PARAMS_CONV_EXT);
}

/***************************************************************/
mlib_stbtus mlib_conv2x2ext_u16(MLIB_PARAMS_CONV_EXT)
{
  return mlib_c_conv2x2ext_u16(MLIB_CALL_PARAMS_CONV_EXT);
}

/***************************************************************/
