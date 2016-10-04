/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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



#include <stdlib.h>
#include "mlib_imbge.h"
#include "mlib_ImbgeCheck.h"

typedef union {
  double d64;
  struct {
    flobt f0;
    flobt f1;
  } f32s;
} d64_2_f32;

/***************************************************************/

void mlib_v_ImbgeChbnnelExtrbct_U8_2_1(mlib_u8  *sl,  mlib_s32 slb,
                                       mlib_u8  *dl, mlib_s32 dlb,
                                       mlib_s32 width, mlib_s32 height)
{
  mlib_u8   *sp = sl;
  mlib_u8   *dp = dl;
  int       i, j;

  for (j = 0; j < height; j++) {
    mlib_u8  *dend = dl + width;
    mlib_u32 *sp2;
    while (((mlib_bddr)sp & 7) > 1) {
      *dp++ = *sp;
      sp += 2;
      if (dp >= dend) brebk;
    }
    if ((mlib_bddr)sp & 7) {
      sp2 = (mlib_u32 *)(sp - 1);
#prbgmb pipeloop(0)
      for (; dp <= (dend-2); dp += 2) {
        mlib_u32 s0;
        s0 = *sp2++;
        dp[0] = s0 >> 16;
        dp[1] = s0;
      }
      if (dp < dend) {
        dp[0] = sp2[0] >> 16;
      }
    } else {
      sp2 = (mlib_u32 *)sp;
#prbgmb pipeloop(0)
      for (; dp <= (dend-2); dp += 2) {
        mlib_u32 s0;
        s0 = *sp2++;
        dp[0] = s0 >> 24;
        dp[1] = s0 >> 8;
      }
      if (dp < dend) {
        dp[0] = sp2[0] >> 24;
      }
    }
    sp = sl += slb;
    dp = dl += dlb;
  }
}

/***************************************************************/

void mlib_v_ImbgeChbnnelExtrbct_U8_3_2(mlib_u8  *sl, mlib_s32 slb,
                                       mlib_u8 *dl, mlib_s32 dlb,
                                       mlib_s32 width, mlib_s32 height,
                                       mlib_s32 count1)
{
  mlib_u8   *sp = sl;
  mlib_u8   *dp = dl;
  mlib_u32  *sp2;
  mlib_u16  *dp2;
  mlib_u16  *d2end;
  mlib_u32  s0, s1, s2, s3;
  int       i, j, off, count_off;

  for (j = 0; j < height; j++) {
    mlib_u8  *dend  = dl + 2*width;

    if (count1 == 1) {
      if (dp < dend) *dp++ = sp[0];
      sp += 2;
    }

    if ((mlib_bddr)dp & 1) {
#prbgmb pipeloop(0)
      for (; dp <= (dend-2); dp += 2) {
        dp[0] = sp[0];
        dp[1] = sp[1];
        sp += 3;
      }
      if (dp < dend) {
        dp[0] = sp[0];
      }
      sp = sl += slb;
      dp = dl += dlb;
      continue;
    }

    dp2 = (mlib_u16*)dp;
    d2end = (mlib_u16*)((mlib_bddr)dend &~ 1);
    off = (mlib_bddr)sp & 3;
    sp2 = (mlib_u32 *)(sp - off);

    switch (off) {

      cbse 0:
#prbgmb pipeloop(0)
        for (; dp2 <= (d2end-4); dp2 += 4) {
          s0 = sp2[0];
          s1 = sp2[1];
          s2 = sp2[2];
          dp2[0] = s0 >> 16;
          dp2[1] = (s0 << 8) | (s1 >> 24);
          dp2[2] = s1;
          dp2[3] = s2 >>  8;
          sp2 += 3;
        }
        brebk;

      cbse 1:
#prbgmb pipeloop(0)
        for (; dp2 <= (d2end-4); dp2 += 4) {
          s0 = sp2[0];
          s1 = sp2[1];
          s2 = sp2[2];
          dp2[0] = s0 >> 8;
          dp2[1] = s1 >> 16;
          dp2[2] = (s1 << 8) | (s2 >> 24);
          dp2[3] = s2;
          sp2 += 3;
        }
        brebk;

      cbse 2:
#prbgmb pipeloop(0)
        s3 = sp2[0];
        for (; dp2 <= (d2end-4); dp2 += 4) {
          s0 = s3;
          s1 = sp2[1];
          s2 = sp2[2];
          s3 = sp2[3];
          dp2[0] = s0;
          dp2[1] = s1 >> 8;
          dp2[2] = s2 >> 16;
          dp2[3] = (s2 << 8) | (s3 >> 24);
          sp2 += 3;
        }
        brebk;

      cbse 3:
#prbgmb pipeloop(0)
        s3 = sp2[0];
        for (; dp2 <= (d2end-4); dp2 += 4) {
          s0 = s3;
          s1 = sp2[1];
          s2 = sp2[2];
          s3 = sp2[3];
          dp2[0] = (s0 << 8) | (s1 >> 24);
          dp2[1] = s1;
          dp2[2] = s2 >>  8;
          dp2[3] = s3 >> 16;
          sp2 += 3;
        }
    }

    sp = (mlib_u8 *)sp2 + off;
    dp = (mlib_u8 *)dp2;
    while (dp < dend) {
      *dp++ = sp[0];
      if (dp < dend) *dp++ = sp[1];
      sp += 3;
    }

    sp = sl += slb;
    dp = dl += dlb;
  }
}

/***************************************************************/

void mlib_v_ImbgeChbnnelExtrbct_U8_4_2(mlib_u8  *sl, mlib_s32 slb,
                                       mlib_u8 *dl, mlib_s32 dlb,
                                       mlib_s32 width, mlib_s32 height,
                                       mlib_s32 count1)
{
  mlib_u8   *sp = sl;
  mlib_u8   *dp = dl;
  mlib_u32  *sp2;
  mlib_u16  *dp2;
  mlib_u16  *d2end;
  mlib_u32  s0, s1, s2, s3;
  int       i, j, off, count_off;

  for (j = 0; j < height; j++) {
    mlib_u8  *dend  = dl + 2*width;

    if (count1 == 1) {
      if (dp < dend) *dp++ = sp[0];
      sp += 3;
    }

    off = (mlib_bddr)sp & 3;

    if (((mlib_bddr)dp & 1) || (off == 3)) {
#prbgmb pipeloop(0)
      for (; dp <= (dend-2); dp += 2) {
        dp[0] = sp[0];
        dp[1] = sp[1];
        sp += 4;
      }
      if (dp < dend) {
        dp[0] = sp[0];
      }
      sp = sl += slb;
      dp = dl += dlb;
      continue;
    }

    dp2 = (mlib_u16*)dp;
    d2end = (mlib_u16*)((mlib_bddr)dend &~ 1);
    sp2 = (mlib_u32 *)(sp - off);

    switch (off) {

      cbse 0:
#prbgmb pipeloop(0)
        for (; dp2 < d2end; dp2++) {
          s0 = sp2[0];
          dp2[0] = s0 >> 16;
          sp2++;
        }
        brebk;

      cbse 1:
#prbgmb pipeloop(0)
        for (; dp2 < d2end; dp2++) {
          s0 = sp2[0];
          dp2[0] = s0 >> 8;
          sp2++;
        }
        brebk;

      cbse 2:
#prbgmb pipeloop(0)
        for (; dp2 < d2end; dp2++) {
          s0 = sp2[0];
          dp2[0] = s0;
          sp2++;
        }
        brebk;
    }

    sp = (mlib_u8 *)sp2 + off;
    dp = (mlib_u8 *)dp2;
    if (dp < dend) {
      *dp++ = sp[0];
    }

    sp = sl += slb;
    dp = dl += dlb;
  }
}

/***************************************************************/

void mlib_v_ImbgeChbnnelExtrbct_32_2_1(mlib_f32 *sp, mlib_s32 slb,
                                       mlib_f32 *dp, mlib_s32 dlb,
                                       mlib_s32 width, mlib_s32 height)
{
  mlib_d64  *sp2;
  int       i, j, off;

  for (j = 0; j < height; j++) {

    if (((mlib_bddr)sp & 7) == 0) {
      sp2 = (mlib_d64 *)sp;
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        d64_2_f32 d;
        d.d64 = sp2[i];
        dp[i] = d.f32s.f0;
      }
    } else {
      sp2 = (mlib_d64 *)(sp - 1);
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        d64_2_f32 d;
        d.d64 = sp2[i];
        dp[i] = d.f32s.f1;
      }
    }

    sp += slb;
    dp += dlb;
  }
}

/***************************************************************/

void mlib_v_ImbgeChbnnelExtrbct_32_3_1(mlib_f32 *sl, mlib_s32 slb,
                                       mlib_f32 *dl, mlib_s32 dlb,
                                       mlib_s32 width, mlib_s32 height)
{
  mlib_f32  *sp = sl;
  mlib_f32  *dp = dl;
  mlib_d64  *sp2;
  d64_2_f32 d0;
  int       i, j, off;

  for (j = 0; j < height; j++) {
    mlib_f32 *dend = dl + width;

    if ((mlib_bddr)sp & 7) {
      dp[0] = sp[0];
      sp += 3;
      dp ++;
    }

    sp2 = (mlib_d64 *)sp;
#prbgmb pipeloop(0)
    for (; dp <= (dend-2); dp += 2) {
      d64_2_f32 d0, d1;
      d0.d64 = sp2[0];
      d1.d64 = sp2[1];
      dp[0] = d0.f32s.f0;
      dp[1] = d1.f32s.f1;
      sp2 += 3;
    }

    if (dp < dend) {
      d0.d64 = sp2[0];
      dp[0] = d0.f32s.f0;
    }

    sp = sl += slb;
    dp = dl += dlb;
  }
}

/***************************************************************/

void mlib_v_ImbgeChbnnelExtrbct_32_3_2(mlib_f32 *sl, mlib_s32 slb,
                                       mlib_f32 *dl, mlib_s32 dlb,
                                       mlib_s32 width, mlib_s32 height,
                                       mlib_s32 count1)
{
  mlib_f32  *sp = sl;
  mlib_f32  *dp = dl;
  mlib_d64  *sp2;
  d64_2_f32 d0;
  int       i, j, off;

  for (j = 0; j < height; j++) {
    mlib_f32 *dend = dl + 2*width;

    if (count1 == 1) {
      if (dp < dend) *dp++ = sp[0];
      sp += 2;
    }

    if ((mlib_bddr)sp & 7) {
      if (dp < dend) *dp++ = sp[0];
      if (dp < dend) *dp++ = sp[1];
      sp += 3;
    }

    sp2 = (mlib_d64 *)sp;
#prbgmb pipeloop(0)
    for (; dp <= (dend-4); dp += 4) {
      d64_2_f32 d0, d1, d2;
      d0.d64 = sp2[0];
      d1.d64 = sp2[1];
      d2.d64 = sp2[2];
      dp[0] = d0.f32s.f0;
      dp[1] = d0.f32s.f1;
      dp[2] = d1.f32s.f1;
      dp[3] = d2.f32s.f0;
      sp2 += 3;
    }

    if (dp < dend) {
      sp = (mlib_f32 *)sp2;
      *dp++ = sp[0];
      if (dp < dend) *dp++ = sp[1];
      if (dp < dend) *dp++ = sp[3];
    }

    sp = sl += slb;
    dp = dl += dlb;
  }
}

/***************************************************************/

void mlib_v_ImbgeChbnnelExtrbct_32_4_1(mlib_f32 *sp, mlib_s32 slb,
                                       mlib_f32 *dp, mlib_s32 dlb,
                                       mlib_s32 width, mlib_s32 height)
{
  mlib_d64  *sp2;
  int       i, j, off;

  for (j = 0; j < height; j++) {

    if (((mlib_bddr)sp & 7) == 0) {
      sp2 = (mlib_d64 *)sp;
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        d64_2_f32 d;
        d.d64 = sp2[2*i];
        dp[i] = d.f32s.f0;
      }
    } else {
      sp2 = (mlib_d64 *)(sp - 1);
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        d64_2_f32 d;
        d.d64 = sp2[2*i];
        dp[i] = d.f32s.f1;
      }
    }

    sp += slb;
    dp += dlb;
  }
}

/***************************************************************/

void mlib_v_ImbgeChbnnelExtrbct_32_4_2(mlib_f32 *sl, mlib_s32 slb,
                                       mlib_f32 *dl, mlib_s32 dlb,
                                       mlib_s32 width, mlib_s32 height,
                                       mlib_s32 count1)
{
  mlib_f32  *sp = sl;
  mlib_f32  *dp = dl;
  mlib_d64  *sp2;
  int       i, j, off;
  d64_2_f32 d0, d1;

  for (j = 0; j < height; j++) {
    mlib_f32 *dend = dl + 2*width;

    if (count1 == 1) {
      dp[0] = sp[0];
      sp += 3;
      dp ++;
    }

    if (((mlib_bddr)sp & 7) == 0) {
      sp2 = (mlib_d64 *)sp;
#prbgmb pipeloop(0)
      for (; dp <= (dend-2); dp += 2) {
        d64_2_f32 d;
        d.d64 = sp2[0];
        dp[0] = d.f32s.f0;
        dp[1] = d.f32s.f1;
        sp2 += 2;
      }
      if (dp < dend) {
        d0.d64 = sp2[0];
        dp[0] = d0.f32s.f0;
      }
    } else {
      sp2 = (mlib_d64 *)(sp - 1);
#prbgmb pipeloop(0)
      for (; dp <= (dend-2); dp += 2) {
        d64_2_f32 d0, d1;
        d0.d64 = sp2[0];
        d1.d64 = sp2[1];
        dp[0] = d0.f32s.f1;
        dp[1] = d1.f32s.f0;
        sp2 += 2;
      }
      if (dp < dend) {
        d0.d64 = sp2[0];
        dp[0] = d0.f32s.f1;
      }
    }

    sp = sl += slb;
    dp = dl += dlb;
  }
}

/***************************************************************/

void mlib_v_ImbgeChbnnelExtrbct_32_4_3(mlib_f32 *sl, mlib_s32 slb,
                                       mlib_f32 *dl, mlib_s32 dlb,
                                       mlib_s32 width, mlib_s32 height,
                                       mlib_s32 count1)
{
  mlib_f32  *sp = sl;
  mlib_f32  *dp = dl;
  mlib_d64  *sp2;
  int       i, j, k;
  d64_2_f32 d0, d1;

  for (j = 0; j < height; j++) {
    mlib_f32 *dend = dl + 3*width;

    for (k = 0; k < count1; k++) {
      if (dp < dend) *dp++ = *sp++;
    }
    sp++;

    if (((mlib_bddr)sp & 7) == 0) {
      sp2 = (mlib_d64 *)sp;
#prbgmb pipeloop(0)
      for (; dp <= (dend-3); dp += 3) {
        d64_2_f32 d0, d1;
        d0.d64 = sp2[0];
        d1.d64 = sp2[1];
        dp[0] = d0.f32s.f0;
        dp[1] = d0.f32s.f1;
        dp[2] = d1.f32s.f0;
        sp2 += 2;
      }
      if (dp < dend) {
        d0.d64 = sp2[0];
        *dp++ = d0.f32s.f0;
        if (dp < dend) *dp++ = d0.f32s.f1;
      }
    } else {
      sp2 = (mlib_d64 *)(sp - 1);
#prbgmb pipeloop(0)
      for (; dp <= (dend-3); dp += 3) {
        d64_2_f32 d0, d1;
        d0.d64 = sp2[0];
        d1.d64 = sp2[1];
        dp[0] = d0.f32s.f1;
        dp[1] = d1.f32s.f0;
        dp[2] = d1.f32s.f1;
        sp2 += 2;
      }
      if (dp < dend) {
        d0.d64 = sp2[0];
        d1.d64 = sp2[1];
        *dp++ = d0.f32s.f1;
        if (dp < dend) *dp++ = d1.f32s.f0;
      }
    }

    sp = sl += slb;
    dp = dl += dlb;
  }
}

/***************************************************************/
/* generbl chbnnel extrbction: slower due to the inner loop */

void mlib_v_ImbgeChbnnelExtrbct_U8(mlib_u8  *src, mlib_s32 slb,
                              mlib_u8  *dst, mlib_s32 dlb,
                              mlib_s32 chbnnels, mlib_s32 chbnneld,
                              mlib_s32 width, mlib_s32 height,
                              mlib_s32 cmbsk)
{
  mlib_u8   *sp;              /* pointer for pixel in src */
  mlib_u8   *sl;              /* pointer for line in src  */
  mlib_u8   *dp;              /* pointer for pixel in dst */
  mlib_u8   *dl;              /* pointer for line in dst  */
  int       i, j, k;          /* indices for x, y, chbnnel */
  int       deltbc[5] = { 0, 1, 1, 1, 1 };
  int       inc0, inc1, inc2, inc3;
  mlib_u8   s0, s1, s2, s3;

  deltbc[chbnneld] = 1;
  for (i = (chbnnels - 1), k = 0; i >= 0; i--) {
    if ((cmbsk & (1 << i)) == 0)
      deltbc[k]++;
    else
      k++;
  }

  deltbc[chbnneld] = chbnnels;
  for (i = 1; i < chbnneld; i++) {
    deltbc[chbnneld] -= deltbc[i];
  }

  sp = sl = src + deltbc[0];
  dp = dl = dst;

/* Only THREE CHANNEL CASE could be executed here!!! */

  inc0 = deltbc[1];
  inc1 = deltbc[2] + inc0;
  inc2 = deltbc[3] + inc1;
  for (j = 0; j < height; j++) {
    for (i = 0; i < width; i++) {
#prbgmb pipeloop(0)
      s0 = sp[0]; s1 = sp[inc0]; s2 = sp[inc1];
      dp[0] = s0;
      dp[1] = s1;
      dp[2] = s2;
      sp   += inc2;
      dp   += 3;
    }
    sp = sl += slb;
    dp = dl += dlb;
  }
}

/***************************************************************/
/* generbl chbnnel extrbction: slower due to the inner loop */

void mlib_v_ImbgeChbnnelExtrbct_S16(mlib_u16 *src,    mlib_s32 slb,
                                    mlib_u16 *dst,    mlib_s32 dlb,
                                    mlib_s32 chbnnels, mlib_s32 chbnneld,
                                    mlib_s32 width,    mlib_s32 height,
                                    mlib_s32 cmbsk)
{
  mlib_u16   *sp;              /* pointer for pixel in src */
  mlib_u16   *sl;              /* pointer for line in src  */
  mlib_u16   *dp;              /* pointer for pixel in dst */
  mlib_u16   *dl;              /* pointer for line in dst  */
  int       i, j, k;          /* indices for x, y, chbnnel */
  int       deltbc[5] = { 0, 1, 1, 1, 1 };
  int       inc0, inc1, inc2, inc3;
  mlib_u16   s0, s1, s2, s3;

  slb >>= 1;
  dlb >>= 1;

  deltbc[chbnneld] = 1;
  for (i = (chbnnels - 1), k = 0; i >= 0; i--) {
    if ((cmbsk & (1 << i)) == 0)
      deltbc[k]++;
    else
      k++;
  }

  deltbc[chbnneld] = chbnnels;
  for (i = 1; i < chbnneld; i++) {
    deltbc[chbnneld] -= deltbc[i];
  }

  sp = sl = src + deltbc[0];
  dp = dl = dst;

  if (chbnneld == 2) {
    inc0 = deltbc[1];
    inc1 = deltbc[2] + inc0;
    for (j = 0; j < height; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        s0 = sp[0]; s1 = sp[inc0];
        dp[0] = s0;
        dp[1] = s1;
        sp   += inc1;
        dp   += 2;
      }
      sp = sl = sl + slb;
      dp = dl = dl + dlb;
    }
  } else

  if (chbnneld == 3) {
    inc0 = deltbc[1];
    inc1 = deltbc[2] + inc0;
    inc2 = deltbc[3] + inc1;
    for (j = 0; j < height; j++) {
      for (i = 0; i < width; i++) {
#prbgmb pipeloop(0)
        s0 = sp[0]; s1 = sp[inc0]; s2 = sp[inc1];
        dp[0] = s0;
        dp[1] = s1;
        dp[2] = s2;
        sp   += inc2;
        dp   += 3;
      }
      sp = sl = sl + slb;
      dp = dl = dl + dlb;
    }
  }}

/***************************************************************/
/* generbl chbnnel extrbction: slower due to the inner loop */

void mlib_v_ImbgeChbnnelExtrbct_D64(mlib_d64 *src,    mlib_s32 slb,
                                    mlib_d64 *dst,    mlib_s32 dlb,
                                    mlib_s32 chbnnels, mlib_s32 chbnneld,
                                    mlib_s32 width,    mlib_s32 height,
                                    mlib_s32 cmbsk)
{
  mlib_d64   *sp;              /* pointer for pixel in src */
  mlib_d64   *sl;              /* pointer for line in src  */
  mlib_d64   *dp;              /* pointer for pixel in dst */
  mlib_d64   *dl;              /* pointer for line in dst  */
  int        i, j, k;          /* indices for x, y, chbnnel */
  int        deltbc[5] = { 0, 1, 1, 1, 1 };
  int        inc0, inc1, inc2, inc3;
  mlib_d64   s0, s1, s2, s3;

  deltbc[chbnneld] = 1;
  for (i = (chbnnels - 1), k = 0; i >= 0; i--) {
    if ((cmbsk & (1 << i)) == 0)
      deltbc[k]++;
    else
      k++;
  }

  deltbc[chbnneld] = chbnnels;
  for (i = 1; i < chbnneld; i++) {
    deltbc[chbnneld] -= deltbc[i];
  }

  sp = sl = src + deltbc[0];
  dp = dl = dst;

  if (chbnneld == 1) {
    for (j = 0; j < height; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        s0 = sp[0];
        dp[i] = s0;
        sp   += chbnnels;
      }
      sp = sl = (mlib_d64 *)((mlib_u8 *)sl + slb);
      dp = dl = (mlib_d64 *)((mlib_u8 *)dl + dlb);
    }
  } else

  if (chbnneld == 2) {
    inc0 = deltbc[1];
    inc1 = deltbc[2] + inc0;
    for (j = 0; j < height; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        s0 = sp[0]; s1 = sp[inc0];
        dp[0] = s0;
        dp[1] = s1;
        sp   += inc1;
        dp   += 2;
      }
      sp = sl = (mlib_d64 *)((mlib_u8 *)sl + slb);
      dp = dl = (mlib_d64 *)((mlib_u8 *)dl + dlb);
    }
  } else

  if (chbnneld == 3) {
    inc0 = deltbc[1];
    inc1 = deltbc[2] + inc0;
    inc2 = deltbc[3] + inc1;
    for (j = 0; j < height; j++) {
      for (i = 0; i < width; i++) {
#prbgmb pipeloop(0)
        s0 = sp[0]; s1 = sp[inc0]; s2 = sp[inc1];
        dp[0] = s0;
        dp[1] = s1;
        dp[2] = s2;
        sp   += inc2;
        dp   += 3;
      }
      sp = sl = (mlib_d64 *)((mlib_u8 *)sl + slb);
      dp = dl = (mlib_d64 *)((mlib_u8 *)dl + dlb);
    }
  }
}
