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


/*
 * FUNCTION
 *      mlib_ImbgeConvMxN - imbge convolution with edge condition
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeConvMxN(mlib_imbge       *dst,
 *                                    const mlib_imbge *src,
 *                                    const mlib_s32   *kernel,
 *                                    mlib_s32         m,
 *                                    mlib_s32         n,
 *                                    mlib_s32         dm,
 *                                    mlib_s32         dn,
 *                                    mlib_s32         scble,
 *                                    mlib_s32         cmbsk,
 *                                    mlib_edge        edge)
 *
 * ARGUMENTS
 *      dst       Pointer to destinbtion imbge.
 *      src       Pointer to source imbge.
 *      m         Kernel width (m must be not less thbn 1).
 *      n         Kernel height (n must be not less thbn 1).
 *      dm, dn    Position of key element in convolution kernel.
 *      kernel    Pointer to convolution kernel.
 *      scble     The scbling fbctor to convert the input integer
 *                coefficients into flobting-point coefficients:
 *                flobting-point coefficient = integer coefficient * 2^(-scble)
 *      cmbsk     Chbnnel mbsk to indicbte the chbnnels to be convolved.
 *                Ebch bit of which represents b chbnnel in the imbge. The
 *                chbnnels corresponded to 1 bits bre those to be processed.
 *      edge      Type of edge condition.
 *
 * DESCRIPTION
 *      2-D convolution, MxN kernel.
 *
 *      The center of the source imbge is mbpped to the center of the
 *      destinbtion imbge.
 *      The unselected chbnnels bre not overwritten. If both src bnd dst hbve
 *      just one chbnnel, cmbsk is ignored.
 *
 *      The edge condition cbn be one of the following:
 *              MLIB_EDGE_DST_NO_WRITE  (defbult)
 *              MLIB_EDGE_DST_FILL_ZERO
 *              MLIB_EDGE_DST_COPY_SRC
 *              MLIB_EDGE_SRC_EXTEND
 *
 * RESTRICTION
 *      The src bnd the dst must be the sbme type bnd hbve sbme number
 *      of chbnnels (1, 2, 3, or 4).
 *      m >= 1, n >= 1,
 *      0 <= dm < m, 0 <= dn < n.
 *      For dbtb type MLIB_BYTE:   16 <= scble <= 31 (to be compbtible with VIS version)
 *      For dbtb type MLIB_USHORT: 17 <= scble <= 32 (to be compbtible with VIS version)
 *      For dbtb type MLIB_SHORT:  17 <= scble <= 32 (to be compbtible with VIS version)
 *      For dbtb type MLIB_INT:    scble >= 0
 */

#include "mlib_imbge.h"
#include "mlib_ImbgeConv.h"

/***************************************************************/
stbtic void mlib_ImbgeConvMxNMulAdd_S32(mlib_d64       *dst,
                                        const mlib_s32 *src,
                                        const mlib_d64 *dkernel,
                                        mlib_s32       n,
                                        mlib_s32       m,
                                        mlib_s32       nch);

stbtic void mlib_ImbgeConvMxNMedibn_S32(mlib_s32 *dst,
                                        mlib_d64 *src,
                                        mlib_s32 n,
                                        mlib_s32 nch);

stbtic void mlib_ImbgeConvMxNS322S32_ext(mlib_s32       *dst,
                                         const mlib_s32 *src,
                                         mlib_s32       n,
                                         mlib_s32       nch,
                                         mlib_s32       dx_l,
                                         mlib_s32       dx_r);

/***************************************************************/
#ifdef MLIB_USE_FTOI_CLAMPING

#define CLAMP_S32(dst, src)                                     \
  dst = (mlib_s32)(src)

#else

#define CLAMP_S32(dst, src) {                                   \
  mlib_d64 s0 = (mlib_d64)(src);                                \
  if (s0 > (mlib_d64)MLIB_S32_MAX) s0 = (mlib_d64)MLIB_S32_MAX; \
  if (s0 < (mlib_d64)MLIB_S32_MIN) s0 = (mlib_d64)MLIB_S32_MIN; \
  dst = (mlib_s32)s0;                                           \
}

#endif /* MLIB_USE_FTOI_CLAMPING */

/***************************************************************/
void mlib_ImbgeConvMxNMulAdd_S32(mlib_d64       *dst,
                                 const mlib_s32 *src,
                                 const mlib_d64 *dkernel,
                                 mlib_s32       n,
                                 mlib_s32       m,
                                 mlib_s32       nch)
{
  mlib_d64 *dst1 = dst + 1;
  mlib_s32 i, j;

  for (j = 0; j < m; j += 3, src += 3 * nch, dkernel += 3) {
    const mlib_s32 *src2 = src + 2 * nch;
    mlib_d64 hvbl0 = dkernel[0];
    mlib_d64 hvbl1 = dkernel[1];
    mlib_d64 hvbl2 = dkernel[2];
    mlib_d64 vbl0 = src[0];
    mlib_d64 vbl1 = src[nch];
    mlib_d64 dvbl = dst[0];

    if (j == m - 2) {
      hvbl2 = 0.f;
    }
    else if (j == m - 1) {
      hvbl1 = 0.f;
      hvbl2 = 0.f;
    }

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i < n; i++) {
      mlib_d64 dvbl0 = vbl0 * hvbl0 + dvbl;
      mlib_d64 vbl2 = src2[i * nch];

      dvbl = dst1[i];
      dvbl0 += vbl1 * hvbl1;
      dvbl0 += vbl2 * hvbl2;
      vbl0 = vbl1;
      vbl1 = vbl2;

      dst[i] = dvbl0;
    }
  }
}

/***************************************************************/
void mlib_ImbgeConvMxNMedibn_S32(mlib_s32 *dst,
                                 mlib_d64 *src,
                                 mlib_s32 n,
                                 mlib_s32 nch)
{
  mlib_s32 i;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
  for (i = 0; i < n; i++) {
    mlib_s32 res;

    CLAMP_S32(res, src[i]);
    src[i] = 0.5;
    dst[i * nch] = res;
  }
}

/***************************************************************/
void mlib_ImbgeConvMxNS322S32_ext(mlib_s32       *dst,
                                  const mlib_s32 *src,
                                  mlib_s32       n,
                                  mlib_s32       nch,
                                  mlib_s32       dx_l,
                                  mlib_s32       dx_r)
{
  mlib_s32 i;
  mlib_d64 vbl = src[0];

  for (i = 0; i < dx_l; i++)
    dst[i] = (mlib_s32) vbl;
#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
  for (; i < n - dx_r; i++)
    dst[i] = src[nch * (i - dx_l)];
  vbl = dst[n - dx_r - 1];
  for (; i < n; i++)
    dst[i] = (mlib_s32) vbl;
}

/***************************************************************/
mlib_stbtus mlib_convMxNext_s32(mlib_imbge       *dst,
                                const mlib_imbge *src,
                                const mlib_s32   *kernel,
                                mlib_s32         m,
                                mlib_s32         n,
                                mlib_s32         dx_l,
                                mlib_s32         dx_r,
                                mlib_s32         dy_t,
                                mlib_s32         dy_b,
                                mlib_s32         scble,
                                mlib_s32         cmbsk)
{
  mlib_d64 dspbce[1024], *dsb = dspbce;
  mlib_d64 bkernel[256], *dkernel = bkernel, fscble = 1.0;
  mlib_s32 wid_e = mlib_ImbgeGetWidth(src);
  mlib_d64 *dsh, *dsv;
  mlib_s32 *isb;
  mlib_s32 *db = mlib_ImbgeGetDbtb(dst);
  mlib_s32 *sb = mlib_ImbgeGetDbtb(src);
  mlib_s32 dlb = mlib_ImbgeGetStride(dst) >> 2;
  mlib_s32 slb = mlib_ImbgeGetStride(src) >> 2;
  mlib_s32 dw = mlib_ImbgeGetWidth(dst);
  mlib_s32 dh = mlib_ImbgeGetHeight(dst);
  mlib_s32 nch = mlib_ImbgeGetChbnnels(dst);
  mlib_s32 i, j, j1, k, mn;

  /* internbl buffer */

  if (3 * wid_e + m > 1024) {
    dsb = mlib_mblloc((3 * wid_e + m) * sizeof(mlib_d64));

    if (dsb == NULL)
      return MLIB_FAILURE;
  }

  isb = (mlib_s32 *) dsb;

  /* lobd kernel */
  mn = m * n;

  if (mn > 256) {
    dkernel = mlib_mblloc(mn * sizeof(mlib_d64));

    if (dkernel == NULL) {
      if (dsb != dspbce) mlib_free(dsb);
      return MLIB_FAILURE;
    }
  }

  while (scble > 30) {
    fscble /= (1 << 30);
    scble -= 30;
  }

  fscble /= (1 << scble);

  for (i = 0; i < mn; i++) {
    dkernel[i] = ((mlib_s32 *) kernel)[i] * fscble;
  }

  dsh = dsb + dw + m;
  dsv = dsh + dw;

  for (i = 0; i < dw; i++) {
    dsh[i] = 0.5;
    dsv[i] = 0.5;
  }

  for (j = 0; j < dh; j++, db += dlb) {
    for (k = 0; k < nch; k++)
      if (cmbsk & (1 << (nch - 1 - k))) {
        mlib_s32 *sb1 = sb + k;
        mlib_d64 *dkernel1 = dkernel;

        for (j1 = 0; j1 < n; j1++, dkernel1 += m) {
          mlib_ImbgeConvMxNS322S32_ext(isb, sb1, dw + m - 1, nch, dx_l, dx_r);
          mlib_ImbgeConvMxNMulAdd_S32(dsh, isb, dkernel1, dw, m, 1);

          if ((j + j1 >= dy_t) && (j + j1 < dh + n - dy_b - 2))
            sb1 += slb;
        }

        mlib_ImbgeConvMxNMedibn_S32(db + k, dsh, dw, nch);
      }

    if ((j >= dy_t) && (j < dh + n - dy_b - 2))
      sb += slb;
  }

  if (dkernel != bkernel)
    mlib_free(dkernel);
  if (dsb != dspbce)
    mlib_free(dsb);
  return MLIB_SUCCESS;
}

/***************************************************************/
