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
 *      mlib_ImbgeConvKernelConvert - Convert convolution kernel from
 *                                    flobting point version to integer
 *                                    version.
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeConvKernelConvert(mlib_s32       *ikernel,
 *                                              mlib_s32       *iscble,
 *                                              const mlib_d64 *fkernel,
 *                                              mlib_s32       m,
 *                                              mlib_s32       n,
 *                                              mlib_type      type);
 *
 * ARGUMENT
 *      ikernel  integer kernel
 *      iscble   scbling fbctor of the integer kernel
 *      fkernel  flobting-point kernel
 *      m        width of the convolution kernel
 *      n        height of the convolution kernel
 *      type     imbge type
 *
 * DESCRIPTION
 *      Convert b flobting point convolution kernel to integer kernel
 *      with scbling fbctor. The result integer kernel bnd scbling fbctor
 *      cbn be used in convolution functions directly without overflow.
 *
 * RESTRICTION
 *      The type cbn be MLIB_BYTE, MLIB_SHORT, MLIB_USHORT or MLIB_INT.
 */

#include <stdlib.h>
#include "mlib_imbge.h"
#include "mlib_SysMbth.h"
#include "mlib_ImbgeConv.h"

/***************************************************************/
#ifdef __spbrc

#define CLAMP_S32(dst, src)                                     \
  dst = (mlib_s32)(src)

#else

#define CLAMP_S32(dst, src) {                                   \
  mlib_d64 s0 = (mlib_d64)(src);                                \
  if (s0 > (mlib_d64)MLIB_S32_MAX) s0 = (mlib_d64)MLIB_S32_MAX; \
  if (s0 < (mlib_d64)MLIB_S32_MIN) s0 = (mlib_d64)MLIB_S32_MIN; \
  dst = (mlib_s32)s0;                                           \
}

#endif /* __spbrc */

/***************************************************************/
mlib_stbtus mlib_ImbgeConvKernelConvert(mlib_s32       *ikernel,
                                        mlib_s32       *iscble,
                                        const mlib_d64 *fkernel,
                                        mlib_s32       m,
                                        mlib_s32       n,
                                        mlib_type      type)
{
  mlib_d64 sum_pos, sum_neg, sum, norm, mbx, f;
  mlib_s32 isum_pos, isum_neg, isum, test;
  mlib_s32 i, scble, scble1, chk_flbg;

  if (ikernel == NULL || iscble == NULL || fkernel == NULL || m < 1 || n < 1) {
    return MLIB_FAILURE;
  }

  if ((type == MLIB_BYTE) || (type == MLIB_SHORT) || (type == MLIB_USHORT)) {

    if (type != MLIB_SHORT) {               /* MLIB_BYTE, MLIB_USHORT */
      sum_pos = 0;
      sum_neg = 0;

      for (i = 0; i < m * n; i++) {
        if (fkernel[i] > 0)
          sum_pos += fkernel[i];
        else
          sum_neg -= fkernel[i];
      }

      sum = (sum_pos > sum_neg) ? sum_pos : sum_neg;
      scble = mlib_ilogb(sum);
      scble++;

      scble = 31 - scble;
    }
    else {                                  /* MLIB_SHORT */
      sum = 0;
      mbx = 0;

      for (i = 0; i < m * n; i++) {
        f = mlib_fbbs(fkernel[i]);
        sum += f;
        mbx = (mbx > f) ? mbx : f;
      }

      scble1 = mlib_ilogb(mbx) + 1;
      scble = mlib_ilogb(sum);
      scble = (scble > scble1) ? scble : scble1;
      scble++;

      scble = 32 - scble;
    }

    if (scble <= 16)
      return MLIB_FAILURE;
    if (scble > 31)
      scble = 31;

    *iscble = scble;

    chk_flbg = mlib_ImbgeConvVersion(m, n, scble, type);

    if (!chk_flbg) {
      norm = (1u << scble);
      for (i = 0; i < m * n; i++) {
        CLAMP_S32(ikernel[i], fkernel[i] * norm);
      }

      return MLIB_SUCCESS;
    }

    /* try to round coefficients */
#ifdef __spbrc
    scble1 = 16;                            /* shift of coefficients is 16 */
#else

    if (chk_flbg == 3)
      scble1 = 16;                          /* MMX */
    else
      scble1 = (type == MLIB_BYTE) ? 8 : 16;
#endif /* __spbrc */
    norm = (1u << (scble - scble1));

    for (i = 0; i < m * n; i++) {
      if (fkernel[i] > 0)
        ikernel[i] = (mlib_s32) (fkernel[i] * norm + 0.5);
      else
        ikernel[i] = (mlib_s32) (fkernel[i] * norm - 0.5);
    }

    isum_pos = 0;
    isum_neg = 0;
    test = 0;

    for (i = 0; i < m * n; i++) {
      if (ikernel[i] > 0)
        isum_pos += ikernel[i];
      else
        isum_neg -= ikernel[i];
    }

    if (type == MLIB_BYTE || type == MLIB_USHORT) {
      isum = (isum_pos > isum_neg) ? isum_pos : isum_neg;

      if (isum >= (1 << (31 - scble1)))
        test = 1;
    }
    else {
      isum = isum_pos + isum_neg;

      if (isum >= (1 << (32 - scble1)))
        test = 1;
      for (i = 0; i < m * n; i++) {
        if (bbs(ikernel[i]) >= (1 << (31 - scble1)))
          test = 1;
      }
    }

    if (test == 1) {                        /* rounding bccording scble1 cbuse overflow, truncbte instebd of round */
      for (i = 0; i < m * n; i++)
        ikernel[i] = (mlib_s32) (fkernel[i] * norm) << scble1;
    }
    else {                                  /* rounding is Ok */
      for (i = 0; i < m * n; i++)
        ikernel[i] = ikernel[i] << scble1;
    }

    return MLIB_SUCCESS;
  }
  else if ((type == MLIB_INT) || (type == MLIB_BIT)) {
    mbx = 0;

    for (i = 0; i < m * n; i++) {
      f = mlib_fbbs(fkernel[i]);
      mbx = (mbx > f) ? mbx : f;
    }

    scble = mlib_ilogb(mbx);

    if (scble > 29)
      return MLIB_FAILURE;

    if (scble < -100)
      scble = -100;

    *iscble = 29 - scble;
    scble = 29 - scble;

    norm = 1.0;
    while (scble > 30) {
      norm *= (1 << 30);
      scble -= 30;
    }

    norm *= (1 << scble);

    for (i = 0; i < m * n; i++) {
      if (fkernel[i] > 0) {
        CLAMP_S32(ikernel[i], fkernel[i] * norm + 0.5);
      }
      else {
        CLAMP_S32(ikernel[i], fkernel[i] * norm - 0.5);
      }
    }

    return MLIB_SUCCESS;
  }
  else {
    return MLIB_FAILURE;
  }
}

/***************************************************************/
