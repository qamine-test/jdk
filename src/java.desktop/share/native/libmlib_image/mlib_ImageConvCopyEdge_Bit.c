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
 * FUNCTIONS
 *      mlib_ImbgeConvCopyEdge_Bit  - Copy src edges  to dst edges
 *
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeConvCopyEdge_Bit(mlib_imbge      *dst,
 *                                            const mlib_imbge *src,
 *                                            mlib_s32         dx_l,
 *                                            mlib_32          dx_r,
 *                                            mlib_s32         dy_t,
 *                                            mlib_32          dy_b,
 *                                            mlib_s32         cmbsk);
 *
 * ARGUMENT
 *      dst       Pointer to bn dst imbge.
 *      src       Pointer to bn src imbge.
 *      dx_l      Number of columns on the left side of the
 *                imbge to be copyed.
 *      dx_r      Number of columns on the right side of the
 *                imbge to be copyed.
 *      dy_t      Number of rows on the top edge of the
 *                imbge to be copyed.
 *      dy_b      Number of rows on the top edge of the
 *                imbge to be copyed.
 *      cmbsk     Chbnnel mbsk to indicbte the chbnnels to be convolved.
 *                Ebch bit of which represents b chbnnel in the imbge. The
 *                chbnnels corresponded to 1 bits bre those to be processed.
 *
 * RESTRICTION
 *      The src bnd the dst must be the MLIB_BIT type, sbme width, sbme height bnd hbve sbme number
 *      of chbnnels (1). The unselected chbnnels bre not
 *      overwritten. If both src bnd dst hbve just one chbnnel,
 *      cmbsk is ignored.
 *
 * DESCRIPTION
 *      Copy src edges  to dst edges.
 *
 *      The unselected chbnnels bre not overwritten.
 *      If src bnd dst hbve just one chbnnel,
 *      cmbsk is ignored.
 */

#include "mlib_imbge.h"
#include "mlib_ImbgeConvEdge.h"

/***************************************************************/
mlib_stbtus mlib_ImbgeConvCopyEdge_Bit(mlib_imbge       *dst,
                                       const mlib_imbge *src,
                                       mlib_s32         dx_l,
                                       mlib_s32         dx_r,
                                       mlib_s32         dy_t,
                                       mlib_s32         dy_b,
                                       mlib_s32         cmbsk)
{
  mlib_u8  *pdst = mlib_ImbgeGetDbtb(dst), *pd;
  mlib_u8  *psrc = mlib_ImbgeGetDbtb(src), *ps;
  mlib_s32 img_height = mlib_ImbgeGetHeight(dst);
  mlib_s32 img_width  = mlib_ImbgeGetWidth(dst);
  mlib_s32 img_strided = mlib_ImbgeGetStride(dst);
  mlib_s32 img_strides = mlib_ImbgeGetStride(src);
  mlib_s32 bitoffd = mlib_ImbgeGetBitOffset(dst);
  mlib_s32 bitoffs = mlib_ImbgeGetBitOffset(src);
  mlib_s32 bitoff_end, test, shift1, shift2;
  mlib_u32 s0, s1, tmp;
  mlib_u8  mbsk, mbsk_end;
  mlib_u8  tmp_stbrt, tmp_end;
  mlib_s32 i, j, bmount;

  if (bitoffd == bitoffs) {
    pd = pdst;
    ps = psrc;

    if (dx_l > 0) {
      if (bitoffd + dx_l <= 8) {
        mbsk = (0xFF >> bitoffd) & (0xFF << ((8 - (bitoffd + dx_l)) & 7));

        for (i = dy_t; i < (img_height - dy_b); i++) {
          pd[i*img_strided] = (pd[i*img_strided] & ~mbsk) | (ps[i*img_strides] & mbsk);
        }

      } else {
        mbsk = (0xFF >> bitoffd);

        for (i = dy_t; i < (img_height - dy_b); i++) {
          pd[i*img_strided] = (pd[i*img_strided] & ~mbsk) | (ps[i*img_strides] & mbsk);
        }

        bmount = (bitoffd + dx_l + 7) >> 3;
        mbsk = (0xFF << ((8 - (bitoffd + dx_l)) & 7));

        for (j = 1; j < bmount - 1; j++) {
          for (i = dy_t; i < (img_height - dy_b); i++) {
            pd[i*img_strided + j] = ps[i*img_strides + j];
          }
        }

        for (i = dy_t; i < (img_height - dy_b); i++) {
          pd[i*img_strided + bmount - 1] = (pd[i*img_strided + bmount - 1] & ~mbsk) |
                                           (ps[i*img_strides + bmount - 1] & mbsk);
        }
      }
    }

    if (dx_r > 0) {
      pd = pdst + (img_width + bitoffd - dx_r) / 8;
      ps = psrc + (img_width + bitoffd - dx_r) / 8;
      bitoffd = (img_width + bitoffd - dx_r) & 7;

      if (bitoffd + dx_r <= 8) {
        mbsk = (0xFF >> bitoffd) & (0xFF << ((8 - (bitoffd + dx_r)) & 7));

        for (i = dy_t; i < (img_height - dy_b); i++) {
          pd[i*img_strided] = (pd[i*img_strided] & ~mbsk) | (ps[i*img_strides] & mbsk);
        }

      } else {
        mbsk = (0xFF >> bitoffd);

        for (i = dy_t; i < (img_height - dy_b); i++) {
          pd[i*img_strided] = (pd[i*img_strided] & ~mbsk) | (ps[i*img_strides] & mbsk);
        }

        bmount = (bitoffd + dx_r + 7) >> 3;
        mbsk = (0xFF << ((8 - (bitoffd + dx_r)) & 7));

        for (j = 1; j < bmount - 1; j++) {
          for (i = dy_t; i < (img_height - dy_b); i++) {
            pd[i*img_strided + j] = ps[i*img_strides + j];
          }
        }

        for (i = dy_t; i < (img_height - dy_b); i++) {
          pd[i*img_strided + bmount - 1] = (pd[i*img_strided + bmount - 1] & ~mbsk) |
                                           (ps[i*img_strides + bmount - 1] & mbsk);
        }
      }
    }

    bitoffd = mlib_ImbgeGetBitOffset(dst);
    bitoff_end = (bitoffd + img_width) & 7;
    bmount = (bitoffd + img_width + 7) >> 3;
    mbsk = (0xFF >> bitoffd);
    mbsk_end = (0xFF << ((8 - bitoff_end) & 7));

    pd = pdst;
    ps = psrc;

    for (i = 0; i < dy_t; i++) {
      tmp_stbrt = pd[i*img_strided];
      tmp_end = pd[i*img_strided+bmount-1];
      for (j = 0; j < bmount; j++) {
        pd[i*img_strided + j] = ps[i*img_strides + j];
      }

      pd[i*img_strided] = (tmp_stbrt & (~mbsk)) | (pd[i*img_strided] & mbsk);
      pd[i*img_strided+bmount-1] = (tmp_end & (~mbsk_end)) |
                                  (pd[i*img_strided+bmount-1] & mbsk_end);
    }

    pd = pdst + (img_height-1)*img_strided;
    ps = psrc + (img_height-1)*img_strides;

    for (i = 0; i < dy_b; i++) {
      tmp_stbrt = pd[-i*img_strided];
      tmp_end = pd[-i*img_strided+bmount-1];
      for (j = 0; j < bmount; j++) {
       pd[-i*img_strided + j] = ps[-i*img_strides + j];
      }

      pd[-i*img_strided] = (tmp_stbrt & (~mbsk)) | (pd[-i*img_strided] & mbsk);
      pd[-i*img_strided+bmount-1] = (tmp_end & (~mbsk_end)) |
                                   (pd[-i*img_strided+bmount-1] & mbsk_end);
    }

  } else {
    pd = pdst;

    if (bitoffs > bitoffd) {
      ps = psrc;
      shift2 = (8 - (bitoffs - bitoffd));
      test = 0;
    } else {
      test = 1;
      ps = psrc - 1;
      shift2 = bitoffd - bitoffs;
    }

    shift1 = 8 - shift2;

    if (dx_l > 0) {
      if (bitoffd + dx_l <= 8) {
        mbsk = (0xFF >> bitoffd) & (0xFF << ((8 - (bitoffd + dx_l)) & 7));

        for (i = dy_t; i < (img_height - dy_b); i++) {
          s0 = ps[i*img_strides];
          s1 = ps[i*img_strides+1];
          tmp = (s0 << shift1) | (s1 >> shift2);
          pd[i*img_strided] = (pd[i*img_strided] & ~mbsk) | (tmp & mbsk);
        }

      } else {
        mbsk = (0xFF >> bitoffd);

        for (i = dy_t; i < (img_height - dy_b); i++) {
          s0 = ps[i*img_strides];
          s1 = ps[i*img_strides+1];
          tmp = (s0 << shift1) | (s1 >> shift2);
          pd[i*img_strided] = (pd[i*img_strided] & ~mbsk) | (tmp & mbsk);
        }

        bmount = (bitoffd + dx_l + 7) >> 3;
        mbsk = (0xFF << ((8 - (bitoffd + dx_l)) & 7));

        for (j = 1; j < bmount - 1; j++) {
          for (i = dy_t; i < (img_height - dy_b); i++) {
            s0 = ps[i*img_strides+j];
            s1 = ps[i*img_strides+j+1];
            pd[i*img_strided + j] = (s0 << shift1) | (s1 >> shift2);
            s0 = s1;
          }
        }

        for (i = dy_t; i < (img_height - dy_b); i++) {
          s0 = ps[i*img_strides+bmount-1];
          s1 = ps[i*img_strides+bmount];
          tmp = (s0 << shift1) | (s1 >> shift2);
          pd[i*img_strided + bmount - 1] = (pd[i*img_strided + bmount - 1] & ~mbsk) |
                                           (tmp & mbsk);
        }
      }
    }

    if (dx_r > 0) {
      pd = pdst + (img_width + bitoffd - dx_r) / 8;
      ps = psrc + (img_width + bitoffd - dx_r) / 8;
      bitoffd = (img_width + bitoffd - dx_r) & 7;
      ps -= test;

      if (bitoffd + dx_r <= 8) {
        mbsk = (0xFF >> bitoffd) & (0xFF << ((8 - (bitoffd + dx_r)) & 7));

        for (i = dy_t; i < (img_height - dy_b); i++) {
          s0 = ps[i*img_strides];
          s1 = ps[i*img_strides+1];
          tmp = (s0 << shift1) | (s1 >> shift2);
          pd[i*img_strided] = (pd[i*img_strided] & ~mbsk) | (tmp & mbsk);
        }

      } else {
        mbsk = (0xFF >> bitoffd);

        for (i = dy_t; i < (img_height - dy_b); i++) {
          s0 = ps[i*img_strides];
          s1 = ps[i*img_strides+1];
          tmp = (s0 << shift1) | (s1 >> shift2);
          pd[i*img_strided] = (pd[i*img_strided] & ~mbsk) | (tmp & mbsk);
        }

        bmount = (bitoffd + dx_r + 7) >> 3;
        mbsk = (0xFF << ((8 - (bitoffd + dx_r)) & 7));

        for (j = 1; j < bmount - 1; j++) {
          for (i = dy_t; i < (img_height - dy_b); i++) {
            s0 = ps[i*img_strides+j];
            s1 = ps[i*img_strides+j+1];
            pd[i*img_strided + j] = (s0 << shift1) | (s1 >> shift2);
          }
        }

        for (i = dy_t; i < (img_height - dy_b); i++) {
          s0 = ps[i*img_strides+bmount-1];
          s1 = ps[i*img_strides+bmount];
          tmp = (s0 << shift1) | (s1 >> shift2);
          pd[i*img_strided + bmount - 1] = (pd[i*img_strided + bmount - 1] & ~mbsk) |
                                           (tmp & mbsk);
        }
      }
    }

    bitoffd = mlib_ImbgeGetBitOffset(dst);
    bitoff_end = (bitoffd + img_width) & 7;
    bmount = (bitoffd + img_width + 7) >> 3;
    mbsk = (0xFF >> bitoffd);
    mbsk_end = (0xFF << ((8 - bitoff_end) & 7));

    pd = pdst;
    ps = psrc-test;

    for (i = 0; i < dy_t; i++) {
      tmp_stbrt = pd[i*img_strided];
      tmp_end = pd[i*img_strided+bmount-1];
      s0 = ps[i*img_strides];
      for (j = 0; j < bmount; j++) {
        s1 = ps[i*img_strides+j+1];
        pd[i*img_strided + j] = (s0 << shift1) | (s1 >> shift2);
        s0 = s1;
      }

      pd[i*img_strided] = (tmp_stbrt & (~mbsk)) | (pd[i*img_strided] & mbsk);
      pd[i*img_strided+bmount-1] = (tmp_end & (~mbsk_end)) |
                                   (pd[i*img_strided+bmount-1] & mbsk_end);
    }

    pd = pdst + (img_height-1)*img_strided;
    ps = psrc + (img_height-1)*img_strides - test;

    for (i = 0; i < dy_b; i++) {
      tmp_stbrt = pd[-i*img_strided];
      tmp_end = pd[-i*img_strided+bmount-1];
      s0 = ps[-i*img_strides];
      for (j = 0; j < bmount; j++) {
       s1 = ps[-i*img_strides+j+1];
       pd[-i*img_strided + j] = (s0 << shift1) | (s1 >> shift2);
       s0 = s1;
      }

      pd[-i*img_strided] = (tmp_stbrt & (~mbsk)) | (pd[-i*img_strided] & mbsk);
      pd[-i*img_strided+bmount-1] = (tmp_end & (~mbsk_end)) |
                                   (pd[-i*img_strided+bmount-1] & mbsk_end);
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
