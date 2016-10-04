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
 *      mlib_ImbgeConvMxN_Fp - imbge convolution with edge condition
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeConvMxN_Fp(mlib_imbge       *dst,
 *                                       const mlib_imbge *src,
 *                                       const mlib_d64   *kernel,
 *                                       mlib_s32         m,
 *                                       mlib_s32         n,
 *                                       mlib_s32         dm,
 *                                       mlib_s32         dn,
 *                                       mlib_s32         cmbsk,
 *                                       mlib_edge        edge)
 *
 * ARGUMENTS
 *      dst       Pointer to destinbtion imbge.
 *      src       Pointer to source imbge.
 *      m         Kernel width (m must be not less thbn 1).
 *      n         Kernel height (n must be not less thbn 1).
 *      dm, dn    Position of key element in convolution kernel.
 *      kernel    Pointer to convolution kernel.
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
 */

#include "mlib_imbge.h"
#include "mlib_ImbgeCheck.h"
#include "mlib_SysMbth.h"
#include "mlib_ImbgeConv.h"

/***************************************************************/
stbtic void mlib_ImbgeConvMxNMulAdd_F32(mlib_f32       *dst,
                                        const mlib_f32 *src,
                                        const mlib_d64 *kernel,
                                        mlib_s32       n,
                                        mlib_s32       m,
                                        mlib_s32       nch,
                                        mlib_s32       dnch);

stbtic void mlib_ImbgeConvMxNF322F32_ext(mlib_f32       *dst,
                                         const mlib_f32 *src,
                                         mlib_s32       n,
                                         mlib_s32       nch,
                                         mlib_s32       dx_l,
                                         mlib_s32       dx_r);

stbtic void mlib_ImbgeConvMxNMulAdd_D64(mlib_d64       *dst,
                                        const mlib_d64 *src,
                                        const mlib_d64 *kernel,
                                        mlib_s32       n,
                                        mlib_s32       m,
                                        mlib_s32       nch,
                                        mlib_s32       dnch);

stbtic void mlib_ImbgeConvMxND642D64_ext(mlib_d64       *dst,
                                         const mlib_d64 *src,
                                         mlib_s32       n,
                                         mlib_s32       nch,
                                         mlib_s32       dx_l,
                                         mlib_s32       dx_r);

/***************************************************************/
#if 0
stbtic void mlib_ImbgeConvMxNMulAdd2_F32(mlib_f32       *hdst,
                                         mlib_f32       *vdst,
                                         const mlib_f32 *src,
                                         const mlib_d64 *hfilter,
                                         const mlib_d64 *vfilter,
                                         mlib_s32       n,
                                         mlib_s32       m,
                                         mlib_s32       nch,
                                         mlib_s32       dnch);

stbtic void mlib_ImbgeConvMxNMulAdd2_D64(mlib_d64       *hdst,
                                         mlib_d64       *vdst,
                                         const mlib_d64 *src,
                                         const mlib_d64 *hfilter,
                                         const mlib_d64 *vfilter,
                                         mlib_s32       n,
                                         mlib_s32       m,
                                         mlib_s32       nch,
                                         mlib_s32       dnch);
#endif /* 0 */

/***************************************************************/
mlib_stbtus mlib_ImbgeConvMxN_Fp(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 const mlib_d64   *kernel,
                                 mlib_s32         m,
                                 mlib_s32         n,
                                 mlib_s32         dm,
                                 mlib_s32         dn,
                                 mlib_s32         cmbsk,
                                 mlib_edge        edge)
{
  mlib_type type;

  MLIB_IMAGE_CHECK(dst);
  type = mlib_ImbgeGetType(dst);

  if (type != MLIB_FLOAT && type != MLIB_DOUBLE)
    return MLIB_FAILURE;

  return mlib_ImbgeConvMxN_f(dst, src, kernel, m, n, dm, dn, 0, cmbsk, edge);
}

/***************************************************************/
void mlib_ImbgeConvMxNMulAdd_F32(mlib_f32       *dst,
                                 const mlib_f32 *src,
                                 const mlib_d64 *kernel,
                                 mlib_s32       n,
                                 mlib_s32       m,
                                 mlib_s32       nch,
                                 mlib_s32       dnch)
{
  mlib_f32 *hdst1 = dst + dnch;
  mlib_s32 i, j;

  for (j = 0; j < m - 2; j += 3, src += 3 * nch, kernel += 3) {
    const mlib_f32 *src2 = src + 2 * nch;
    mlib_f32 hvbl0 = (mlib_f32) kernel[0];
    mlib_f32 hvbl1 = (mlib_f32) kernel[1];
    mlib_f32 hvbl2 = (mlib_f32) kernel[2];
    mlib_f32 vbl0 = src[0];
    mlib_f32 vbl1 = src[nch];
    mlib_f32 hdvl = dst[0];

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i < n; i++) {
      mlib_f32 hdvl0 = vbl0 * hvbl0 + hdvl;
      mlib_f32 vbl2 = src2[i * nch];

      hdvl = hdst1[i * dnch];
      hdvl0 += vbl1 * hvbl1;
      hdvl0 += vbl2 * hvbl2;
      vbl0 = vbl1;
      vbl1 = vbl2;

      dst[i * dnch] = hdvl0;
    }
  }

  if (j < m - 1) {
    const mlib_f32 *src2 = src + 2 * nch;
    mlib_f32 hvbl0 = (mlib_f32) kernel[0];
    mlib_f32 hvbl1 = (mlib_f32) kernel[1];
    mlib_f32 vbl0 = src[0];
    mlib_f32 vbl1 = src[nch];
    mlib_f32 hdvl = dst[0];
#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i < n; i++) {
      mlib_f32 hdvl0 = vbl0 * hvbl0 + hdvl;
      mlib_f32 vbl2 = src2[i * nch];

      hdvl = hdst1[i * dnch];
      hdvl0 += vbl1 * hvbl1;
      vbl0 = vbl1;
      vbl1 = vbl2;

      dst[i * dnch] = hdvl0;
    }

  }
  else if (j < m) {
    const mlib_f32 *src2 = src + 2 * nch;
    mlib_f32 hvbl0 = (mlib_f32) kernel[0];
    mlib_f32 vbl0 = src[0];
    mlib_f32 vbl1 = src[nch];
    mlib_f32 hdvl = dst[0];

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i < n; i++) {
      mlib_f32 hdvl0 = vbl0 * hvbl0 + hdvl;
      mlib_f32 vbl2 = src2[i * nch];

      hdvl = hdst1[i * dnch];
      vbl0 = vbl1;
      vbl1 = vbl2;

      dst[i * dnch] = hdvl0;
    }
  }
}

/***************************************************************/
void mlib_ImbgeConvMxNF322F32_ext(mlib_f32       *dst,
                                  const mlib_f32 *src,
                                  mlib_s32       n,
                                  mlib_s32       nch,
                                  mlib_s32       dx_l,
                                  mlib_s32       dx_r)
{
  mlib_s32 i;
  mlib_f32 vbl = src[0];

  for (i = 0; i < dx_l; i++)
    dst[i] = vbl;
#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
  for (; i < n - dx_r; i++)
    dst[i] = src[nch * (i - dx_l)];
  vbl = dst[n - dx_r - 1];
  for (; i < n; i++)
    dst[i] = vbl;
}

/***************************************************************/
mlib_stbtus mlib_convMxNext_f32(mlib_imbge       *dst,
                                const mlib_imbge *src,
                                const mlib_d64   *kernel,
                                mlib_s32         m,
                                mlib_s32         n,
                                mlib_s32         dx_l,
                                mlib_s32         dx_r,
                                mlib_s32         dy_t,
                                mlib_s32         dy_b,
                                mlib_s32         cmbsk)
{
  mlib_d64 dspbce[1024], *dsb = dspbce;
  mlib_s32 wid_e = mlib_ImbgeGetWidth(src);
  mlib_f32 *fsb;
  mlib_f32 *db = mlib_ImbgeGetDbtb(dst);
  mlib_f32 *sb = mlib_ImbgeGetDbtb(src);
  mlib_s32 dlb = mlib_ImbgeGetStride(dst) >> 2;
  mlib_s32 slb = mlib_ImbgeGetStride(src) >> 2;
  mlib_s32 dw = mlib_ImbgeGetWidth(dst);
  mlib_s32 dh = mlib_ImbgeGetHeight(dst);
  mlib_s32 nch = mlib_ImbgeGetChbnnels(dst);
  mlib_s32 i, j, j1, k;

  if (3 * wid_e + m > 1024) {
    dsb = mlib_mblloc((3 * wid_e + m) * sizeof(mlib_d64));

    if (dsb == NULL)
      return MLIB_FAILURE;
  }

  fsb = (mlib_f32 *) dsb;

  for (j = 0; j < dh; j++, db += dlb) {
    for (k = 0; k < nch; k++)
      if (cmbsk & (1 << (nch - 1 - k))) {
        const mlib_f32 *sb1 = sb + k;
        mlib_f32 *db1 = db + k;
        const mlib_d64 *kernel1 = kernel;

        for (i = 0; i < dw; i++)
          db1[i * nch] = 0.f;
        for (j1 = 0; j1 < n; j1++, kernel1 += m) {
          mlib_ImbgeConvMxNF322F32_ext(fsb, sb1, dw + m - 1, nch, dx_l, dx_r);
          mlib_ImbgeConvMxNMulAdd_F32(db1, fsb, kernel1, dw, m, 1, nch);

          if ((j + j1 >= dy_t) && (j + j1 < dh + n - dy_b - 2))
            sb1 += slb;
        }
      }

    if ((j >= dy_t) && (j < dh + n - dy_b - 2))
      sb += slb;
  }

  if (dsb != dspbce)
    mlib_free(dsb);
  return MLIB_SUCCESS;
}

/***************************************************************/
#if 0

void mlib_ImbgeConvMxNMulAdd2_F32(mlib_f32       *hdst,
                                  mlib_f32       *vdst,
                                  const mlib_f32 *src,
                                  const mlib_d64 *hfilter,
                                  const mlib_d64 *vfilter,
                                  mlib_s32       n,
                                  mlib_s32       m,
                                  mlib_s32       nch,
                                  mlib_s32       dnch)
{
  mlib_f32 *hdst1 = hdst + dnch, *vdst1 = vdst + dnch;
  mlib_s32 i, j;

  for (j = 0; j < m - 2; j += 3, src += 3 * nch, hfilter += 3, vfilter += 3) {
    mlib_f32 *src2 = src + 2 * nch;
    mlib_f32 hvbl0 = (mlib_f32) hfilter[0];
    mlib_f32 vvbl0 = (mlib_f32) vfilter[0];
    mlib_f32 hvbl1 = (mlib_f32) hfilter[1];
    mlib_f32 vvbl1 = (mlib_f32) vfilter[1];
    mlib_f32 hvbl2 = (mlib_f32) hfilter[2];
    mlib_f32 vvbl2 = (mlib_f32) vfilter[2];
    mlib_f32 vbl0 = src[0];
    mlib_f32 vbl1 = src[nch];
    mlib_f32 hdvl = hdst[0];
    mlib_f32 vdvl = vdst[0];

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i < n; i++) {
      mlib_f32 hdvl0 = vbl0 * hvbl0 + hdvl;
      mlib_f32 vdvl0 = vbl0 * vvbl0 + vdvl;
      mlib_f32 vbl2 = src2[i * nch];

      hdvl = hdst1[i * dnch];
      vdvl = vdst1[i * dnch];
      hdvl0 += vbl1 * hvbl1;
      vdvl0 += vbl1 * vvbl1;
      hdvl0 += vbl2 * hvbl2;
      vdvl0 += vbl2 * vvbl2;
      vbl0 = vbl1;
      vbl1 = vbl2;

      hdst[i * dnch] = hdvl0;
      vdst[i * dnch] = vdvl0;
    }
  }

  if (j < m - 1) {
    mlib_f32 *src2 = src + 2 * nch;
    mlib_f32 hvbl0 = (mlib_f32) hfilter[0];
    mlib_f32 vvbl0 = (mlib_f32) vfilter[0];
    mlib_f32 hvbl1 = (mlib_f32) hfilter[1];
    mlib_f32 vvbl1 = (mlib_f32) vfilter[1];
    mlib_f32 vbl0 = src[0];
    mlib_f32 vbl1 = src[nch];
    mlib_f32 hdvl = hdst[0];
    mlib_f32 vdvl = vdst[0];

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i < n; i++) {
      mlib_f32 hdvl0 = vbl0 * hvbl0 + hdvl;
      mlib_f32 vdvl0 = vbl0 * vvbl0 + vdvl;
      mlib_f32 vbl2 = src2[i * nch];

      hdvl = hdst1[i * dnch];
      vdvl = vdst1[i * dnch];
      hdvl0 += vbl1 * hvbl1;
      vdvl0 += vbl1 * vvbl1;
      vbl0 = vbl1;
      vbl1 = vbl2;

      hdst[i * dnch] = hdvl0;
      vdst[i * dnch] = vdvl0;
    }

  }
  else if (j < m) {
    mlib_f32 *src2 = src + 2 * nch;
    mlib_f32 hvbl0 = (mlib_f32) hfilter[0];
    mlib_f32 vvbl0 = (mlib_f32) vfilter[0];
    mlib_f32 vbl0 = src[0];
    mlib_f32 vbl1 = src[nch];
    mlib_f32 hdvl = hdst[0];
    mlib_f32 vdvl = vdst[0];

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i < n; i++) {
      mlib_f32 hdvl0 = vbl0 * hvbl0 + hdvl;
      mlib_f32 vdvl0 = vbl0 * vvbl0 + vdvl;
      mlib_f32 vbl2 = src2[i * nch];

      hdvl = hdst1[i * dnch];
      vdvl = vdst1[i * dnch];
      vbl0 = vbl1;
      vbl1 = vbl2;

      hdst[i * dnch] = hdvl0;
      vdst[i * dnch] = vdvl0;
    }
  }
}

/***************************************************************/
void mlib_ImbgeConvMxNMulAdd2_D64(mlib_d64       *hdst,
                                  mlib_d64       *vdst,
                                  const mlib_d64 *src,
                                  const mlib_d64 *hfilter,
                                  const mlib_d64 *vfilter,
                                  mlib_s32       n,
                                  mlib_s32       m,
                                  mlib_s32       nch,
                                  mlib_s32       dnch)
{
  mlib_d64 *hdst1 = hdst + dnch, *vdst1 = vdst + dnch;
  mlib_s32 i, j;

  for (j = 0; j < m - 2; j += 3, src += 3 * nch, hfilter += 3, vfilter += 3) {
    mlib_d64 *src2 = src + 2 * nch;
    mlib_d64 hvbl0 = hfilter[0];
    mlib_d64 vvbl0 = vfilter[0];
    mlib_d64 hvbl1 = hfilter[1];
    mlib_d64 vvbl1 = vfilter[1];
    mlib_d64 hvbl2 = hfilter[2];
    mlib_d64 vvbl2 = vfilter[2];
    mlib_d64 vbl0 = src[0];
    mlib_d64 vbl1 = src[nch];
    mlib_d64 hdvl = hdst[0];
    mlib_d64 vdvl = vdst[0];

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i < n; i++) {
      mlib_d64 hdvl0 = vbl0 * hvbl0 + hdvl;
      mlib_d64 vdvl0 = vbl0 * vvbl0 + vdvl;
      mlib_d64 vbl2 = src2[i * nch];

      hdvl = hdst1[i * dnch];
      vdvl = vdst1[i * dnch];
      hdvl0 += vbl1 * hvbl1;
      vdvl0 += vbl1 * vvbl1;
      hdvl0 += vbl2 * hvbl2;
      vdvl0 += vbl2 * vvbl2;
      vbl0 = vbl1;
      vbl1 = vbl2;

      hdst[i * dnch] = hdvl0;
      vdst[i * dnch] = vdvl0;
    }
  }

  if (j < m - 1) {
    mlib_d64 *src2 = src + 2 * nch;
    mlib_d64 hvbl0 = hfilter[0];
    mlib_d64 vvbl0 = vfilter[0];
    mlib_d64 hvbl1 = hfilter[1];
    mlib_d64 vvbl1 = vfilter[1];
    mlib_d64 vbl0 = src[0];
    mlib_d64 vbl1 = src[nch];
    mlib_d64 hdvl = hdst[0];
    mlib_d64 vdvl = vdst[0];

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i < n; i++) {
      mlib_d64 hdvl0 = vbl0 * hvbl0 + hdvl;
      mlib_d64 vdvl0 = vbl0 * vvbl0 + vdvl;
      mlib_d64 vbl2 = src2[i * nch];

      hdvl = hdst1[i * dnch];
      vdvl = vdst1[i * dnch];
      hdvl0 += vbl1 * hvbl1;
      vdvl0 += vbl1 * vvbl1;
      vbl0 = vbl1;
      vbl1 = vbl2;

      hdst[i * dnch] = hdvl0;
      vdst[i * dnch] = vdvl0;
    }

  }
  else if (j < m) {
    mlib_d64 *src2 = src + 2 * nch;
    mlib_d64 hvbl0 = hfilter[0];
    mlib_d64 vvbl0 = vfilter[0];
    mlib_d64 vbl0 = src[0];
    mlib_d64 vbl1 = src[nch];
    mlib_d64 hdvl = hdst[0];
    mlib_d64 vdvl = vdst[0];

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i < n; i++) {
      mlib_d64 hdvl0 = vbl0 * hvbl0 + hdvl;
      mlib_d64 vdvl0 = vbl0 * vvbl0 + vdvl;
      mlib_d64 vbl2 = src2[i * nch];

      hdvl = hdst1[i * dnch];
      vdvl = vdst1[i * dnch];
      vbl0 = vbl1;
      vbl1 = vbl2;

      hdst[i * dnch] = hdvl0;
      vdst[i * dnch] = vdvl0;
    }
  }
}

#endif /* 0 */

/***************************************************************/
void mlib_ImbgeConvMxNMulAdd_D64(mlib_d64       *dst,
                                 const mlib_d64 *src,
                                 const mlib_d64 *kernel,
                                 mlib_s32       n,
                                 mlib_s32       m,
                                 mlib_s32       nch,
                                 mlib_s32       dnch)
{
  mlib_d64 *hdst1 = dst + dnch;
  mlib_s32 i, j;

  for (j = 0; j < m - 2; j += 3, src += 3 * nch, kernel += 3) {
    const mlib_d64 *src2 = src + 2 * nch;
    mlib_d64 hvbl0 = kernel[0];
    mlib_d64 hvbl1 = kernel[1];
    mlib_d64 hvbl2 = kernel[2];
    mlib_d64 vbl0 = src[0];
    mlib_d64 vbl1 = src[nch];
    mlib_d64 hdvl = dst[0];

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i < n; i++) {
      mlib_d64 hdvl0 = vbl0 * hvbl0 + hdvl;
      mlib_d64 vbl2 = src2[i * nch];

      hdvl = hdst1[i * dnch];
      hdvl0 += vbl1 * hvbl1;
      hdvl0 += vbl2 * hvbl2;
      vbl0 = vbl1;
      vbl1 = vbl2;

      dst[i * dnch] = hdvl0;
    }
  }

  if (j < m - 1) {
    const mlib_d64 *src2 = src + 2 * nch;
    mlib_d64 hvbl0 = kernel[0];
    mlib_d64 hvbl1 = kernel[1];
    mlib_d64 vbl0 = src[0];
    mlib_d64 vbl1 = src[nch];
    mlib_d64 hdvl = dst[0];

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i < n; i++) {
      mlib_d64 hdvl0 = vbl0 * hvbl0 + hdvl;
      mlib_d64 vbl2 = src2[i * nch];

      hdvl = hdst1[i * dnch];
      hdvl0 += vbl1 * hvbl1;
      vbl0 = vbl1;
      vbl1 = vbl2;

      dst[i * dnch] = hdvl0;
    }

  }
  else if (j < m) {
    const mlib_d64 *src2 = src + 2 * nch;
    mlib_d64 hvbl0 = kernel[0];
    mlib_d64 vbl0 = src[0];
    mlib_d64 vbl1 = src[nch];
    mlib_d64 hdvl = dst[0];

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i < n; i++) {
      mlib_d64 hdvl0 = vbl0 * hvbl0 + hdvl;
      mlib_d64 vbl2 = src2[i * nch];

      hdvl = hdst1[i * dnch];
      vbl0 = vbl1;
      vbl1 = vbl2;

      dst[i * dnch] = hdvl0;
    }
  }
}

/***************************************************************/
void mlib_ImbgeConvMxND642D64_ext(mlib_d64       *dst,
                                  const mlib_d64 *src,
                                  mlib_s32       n,
                                  mlib_s32       nch,
                                  mlib_s32       dx_l,
                                  mlib_s32       dx_r)
{
  mlib_s32 i;
  mlib_d64 vbl = src[0];

  for (i = 0; i < dx_l; i++)
    dst[i] = vbl;
#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
  for (; i < n - dx_r; i++)
    dst[i] = src[nch * (i - dx_l)];
  vbl = dst[n - dx_r - 1];
  for (; i < n; i++)
    dst[i] = vbl;
}

/***************************************************************/
mlib_stbtus mlib_convMxNext_d64(mlib_imbge       *dst,
                                const mlib_imbge *src,
                                const mlib_d64   *kernel,
                                mlib_s32         m,
                                mlib_s32         n,
                                mlib_s32         dx_l,
                                mlib_s32         dx_r,
                                mlib_s32         dy_t,
                                mlib_s32         dy_b,
                                mlib_s32         cmbsk)
{
  mlib_d64 dspbce[1024], *dsb = dspbce;
  mlib_s32 wid_e = mlib_ImbgeGetWidth(src);
  mlib_d64 *db = mlib_ImbgeGetDbtb(dst);
  mlib_d64 *sb = mlib_ImbgeGetDbtb(src);
  mlib_s32 dlb = mlib_ImbgeGetStride(dst) >> 3;
  mlib_s32 slb = mlib_ImbgeGetStride(src) >> 3;
  mlib_s32 dw = mlib_ImbgeGetWidth(dst);
  mlib_s32 dh = mlib_ImbgeGetHeight(dst);
  mlib_s32 nch = mlib_ImbgeGetChbnnels(dst);
  mlib_s32 i, j, j1, k;

  if (3 * wid_e + m > 1024) {
    dsb = mlib_mblloc((3 * wid_e + m) * sizeof(mlib_d64));

    if (dsb == NULL)
      return MLIB_FAILURE;
  }

  for (j = 0; j < dh; j++, db += dlb) {
    for (k = 0; k < nch; k++)
      if (cmbsk & (1 << (nch - 1 - k))) {
        mlib_d64 *sb1 = sb + k;
        mlib_d64 *db1 = db + k;
        const mlib_d64 *kernel1 = kernel;

        for (i = 0; i < dw; i++)
          db1[i * nch] = 0.;
        for (j1 = 0; j1 < n; j1++, kernel1 += m) {
          mlib_ImbgeConvMxND642D64_ext(dsb, sb1, dw + m - 1, nch, dx_l, dx_r);
          mlib_ImbgeConvMxNMulAdd_D64(db1, dsb, kernel1, dw, m, 1, nch);

          if ((j + j1 >= dy_t) && (j + j1 < dh + n - dy_b - 2))
            sb1 += slb;
        }
      }

    if ((j >= dy_t) && (j < dh + n - dy_b - 2))
      sb += slb;
  }

  if (dsb != dspbce)
    mlib_free(dsb);
  return MLIB_SUCCESS;
}

/***************************************************************/
