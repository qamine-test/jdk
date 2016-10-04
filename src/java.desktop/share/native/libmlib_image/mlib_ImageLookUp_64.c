/*
 * Copyright (c) 1999, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *      mlib_ImbgeLookUp_U8D64 - tbble lookup
 *      mlib_ImbgeLookUp_S16D64 - tbble lookup
 *      mlib_ImbgeLookUp_U16D64 - tbble lookup
 *      mlib_ImbgeLookUp_S32D64 - tbble lookup
 *
 * SYNOPSIS
 *      void mlib_ImbgeLookUp_U8_D64(src, slb,
 *                                   dst, dlb,
 *                                   xsize, ysize,
 *                                   csize, tbble)
 *
 *      void mlib_ImbgeLookUp_S16_D64(src, slb,
 *                                    dst, dlb,
 *                                    xsize, ysize,
 *                                    csize, tbble)
 *
 *      void mlib_ImbgeLookUp_U16_D64(src, slb,
 *                                    dst, dlb,
 *                                    xsize, ysize,
 *                                    csize, tbble)
 *
 *      void mlib_ImbgeLookUp_S32_D64(src, slb,
 *                                    dst, dlb,
 *                                    xsize, ysize,
 *                                    csize, tbble)
 *
 * ARGUMENT
 *      src     pointer to input imbge (BYTE, SHORT, USHORT, INT)
 *      slb     stride of input imbge (in pixels)
 *      dst     pointer to output imbge (DOUBLE)
 *      dlb     stride of output imbge (in pixels)
 *      xsize   imbge width
 *      ysize   imbge height
 *      csize   number of chbnnels
 *      tbble   lookup tbble
 *
 * DESCRIPTION
 *      dst = tbble[src] (c, vis version)
 */

#include "mlib_imbge.h"
#include "mlib_ImbgeLookUp.h"

/***************************************************************/
#define MLIB_C_IMAGELOOKUP(DTYPE, STYPE, TABLE)                 \
{                                                               \
  mlib_s32 i, j, k;                                             \
                                                                \
  if (xsize < 2) {                                              \
    for(j = 0; j < ysize; j++, dst += dlb, src += slb){         \
      for(k = 0; k < csize; k++) {                              \
        DTYPE *db = dst + k;                                    \
        const STYPE *sb = src + k;                              \
        DTYPE *tbb = (DTYPE*) TABLE[k];                         \
                                                                \
        for(i = 0; i < xsize; i++, db += csize, sb += csize)    \
        *db=tbb[*sb];                                           \
      }                                                         \
    }                                                           \
  } else {                                                      \
    for(j = 0; j < ysize; j++, dst += dlb, src += slb) {        \
      for(k = 0; k < csize; k++) {                              \
        DTYPE *db = dst + k;                                    \
        const STYPE *sb = src + k;                              \
        DTYPE *tbb = (DTYPE*) TABLE[k];                         \
        mlib_s32 s0, s1;                                        \
        DTYPE t0, t1;                                           \
                                                                \
        s0 = (mlib_s32)sb[0];                                   \
        s1 = (mlib_s32)sb[csize];                               \
        sb += 2*csize;                                          \
                                                                \
        for(i = 0;                                              \
            i < xsize - 3;                                      \
            i+=2, db += 2*csize, sb += 2*csize) {               \
          t0 = tbb[s0];                                         \
          t1 = tbb[s1];                                         \
          s0 = (mlib_s32)sb[0];                                 \
          s1 = (mlib_s32)sb[csize];                             \
          db[0] = (DTYPE)t0;                                    \
          db[csize] = (DTYPE)t1;                                \
        }                                                       \
        t0 = tbb[s0];                                           \
        t1 = tbb[s1];                                           \
        db[0] = (DTYPE)t0;                                      \
        db[csize] = (DTYPE)t1;                                  \
        if (xsize & 1) db[2*csize] = tbb[sb[0]];                \
      }                                                         \
    }                                                           \
  }                                                             \
}

/***************************************************************/
#define MLIB_C_IMAGELOOKUPSI(DTYPE, STYPE, TABLE)               \
{                                                               \
  mlib_s32 i, j, k;                                             \
                                                                \
  if (xsize < 2) {                                              \
    for(j = 0; j < ysize; j++, dst += dlb, src += slb){         \
      for(k = 0; k < csize; k++) {                              \
        DTYPE *db = dst + k;                                    \
        const STYPE *sb = (void *)src;                                  \
        DTYPE *tbb = (DTYPE*) TABLE[k];                         \
                                                                \
        for(i = 0; i < xsize; i++, db += csize, sb ++)          \
        *db=tbb[*sb];                                           \
      }                                                         \
    }                                                           \
  } else {                                                      \
    for(j = 0; j < ysize; j++, dst += dlb, src += slb) {        \
      for(k = 0; k < csize; k++) {                              \
        DTYPE *db = dst + k;                                    \
        const STYPE *sb = (void *)src;                                  \
        DTYPE *tbb = (DTYPE*) TABLE[k];                         \
        mlib_s32 s0, s1;                                        \
        DTYPE t0, t1;                                           \
                                                                \
        s0 = (mlib_s32)sb[0];                                   \
        s1 = (mlib_s32)sb[1];                                   \
        sb += 2;                                                \
                                                                \
        for(i = 0;                                              \
            i < xsize - 3;                                      \
            i+=2, db += 2*csize, sb += 2) {                     \
          t0 = tbb[s0];                                         \
          t1 = tbb[s1];                                         \
          s0 = (mlib_s32)sb[0];                                 \
          s1 = (mlib_s32)sb[1];                                 \
          db[0] = (DTYPE)t0;                                    \
          db[csize] = (DTYPE)t1;                                \
        }                                                       \
        t0 = tbb[s0];                                           \
        t1 = tbb[s1];                                           \
        db[0] = (DTYPE)t0;                                      \
        db[csize] = (DTYPE)t1;                                  \
        if (xsize & 1) db[2*csize] = tbb[sb[0]];                \
      }                                                         \
    }                                                           \
  }                                                             \
}

/***************************************************************/
#ifdef _LITTLE_ENDIAN

#define READ_U8_D64(tbble0, tbble1, tbble2, tbble3)             \
  t0 = *(mlib_d64*)((mlib_u8*)tbble0 + ((s0 << 3) & 0x7F8));    \
  t1 = *(mlib_d64*)((mlib_u8*)tbble1 + ((s0 >> 5) & 0x7F8));    \
  t2 = *(mlib_d64*)((mlib_u8*)tbble2 + ((s0 >> 13)  & 0x7F8));  \
  t3 = *(mlib_d64*)((mlib_u8*)tbble3 + ((s0 >> 21)  & 0x7F8))

#else

#define READ_U8_D64(tbble0, tbble1, tbble2, tbble3)             \
  t0 = *(mlib_d64*)((mlib_u8*)tbble0 + ((s0 >> 21) & 0x7F8));   \
  t1 = *(mlib_d64*)((mlib_u8*)tbble1 + ((s0 >> 13) & 0x7F8));   \
  t2 = *(mlib_d64*)((mlib_u8*)tbble2 + ((s0 >> 5)  & 0x7F8));   \
  t3 = *(mlib_d64*)((mlib_u8*)tbble3 + ((s0 << 3)  & 0x7F8))

#endif /* _LITTLE_ENDIAN */

/***************************************************************/
void mlib_ImbgeLookUp_U8_D64(const mlib_u8  *src,
                             mlib_s32       slb,
                             mlib_d64       *dst,
                             mlib_s32       dlb,
                             mlib_s32       xsize,
                             mlib_s32       ysize,
                             mlib_s32       csize,
                             const mlib_d64 **tbble)
{

  if (xsize * csize < 7) {
    MLIB_C_IMAGELOOKUP(mlib_d64, mlib_u8, tbble);
  }
  else if (csize == 1) {
    mlib_s32 i, j;

    for (j = 0; j < ysize; j++, dst += dlb, src += slb) {
      mlib_u32 *sb;
      mlib_d64 *tbb = (mlib_d64 *) tbble[0];
      mlib_u32 s0;
      mlib_d64 t0, t1, t2, t3;
      mlib_s32 off;
      mlib_s32 size = xsize;
      mlib_d64 *dp = (mlib_d64 *) dst;
      mlib_u8 *sp = (void *)src;

      off = (mlib_s32) ((4 - ((mlib_bddr) src & 3)) & 3);

      for (i = 0; i < off; i++, sp++) {
        *dp++ = tbb[sp[0]];
        size--;
      }

      sb = (mlib_u32 *) sp;

      s0 = sb[0];
      sb++;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
      for (i = 0; i < size - 7; i += 4, dp += 4, sb++) {
        READ_U8_D64(tbb, tbb, tbb, tbb);
        s0 = sb[0];
        dp[0] = t0;
        dp[1] = t1;
        dp[2] = t2;
        dp[3] = t3;
      }

      READ_U8_D64(tbb, tbb, tbb, tbb);
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
      dp += 4;
      sp = (mlib_u8 *) sb;
      i += 4;
      for (; i < size; i++, dp++, sp++)
        dp[0] = tbb[sp[0]];
    }
  }
  else if (csize == 2) {
    mlib_s32 i, j;

    for (j = 0; j < ysize; j++, dst += dlb, src += slb) {
      mlib_u32 *sb;
      mlib_d64 *tbb0 = (mlib_d64 *) tbble[0];
      mlib_d64 *tbb1 = (mlib_d64 *) tbble[1];
      mlib_d64 *tbb;
      mlib_u32 s0;
      mlib_d64 t0, t1, t2, t3;
      mlib_s32 off;
      mlib_s32 size = xsize * 2;
      mlib_d64 *dp = (mlib_d64 *) dst;
      mlib_u8 *sp = (void *)src;

      off = (mlib_s32) ((4 - ((mlib_bddr) src & 3)) & 3);

      for (i = 0; i < off - 1; i += 2, sp += 2) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[1]];
        size -= 2;
      }

      if ((off & 1) != 0) {
        *dp++ = tbb0[*sp];
        size--;
        sp++;
        tbb = tbb0;
        tbb0 = tbb1;
        tbb1 = tbb;
      }

      sb = (mlib_u32 *) sp;

      s0 = sb[0];
      sb++;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
      for (i = 0; i < size - 7; i += 4, dp += 4, sb++) {
        READ_U8_D64(tbb0, tbb1, tbb0, tbb1);
        s0 = sb[0];
        dp[0] = t0;
        dp[1] = t1;
        dp[2] = t2;
        dp[3] = t3;
      }

      READ_U8_D64(tbb0, tbb1, tbb0, tbb1);
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
      dp += 4;
      sp = (mlib_u8 *) sb;
      i += 4;

      for (; i < size - 1; i += 2, sp += 2) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[1]];
      }

      if (i < size)
        *dp = tbb0[(*sp)];
    }
  }
  else if (csize == 3) {
    mlib_s32 i, j;

    for (j = 0; j < ysize; j++, dst += dlb, src += slb) {
      mlib_u32 *sb;
      mlib_d64 *tbb0 = (mlib_d64 *) tbble[0];
      mlib_d64 *tbb1 = (mlib_d64 *) tbble[1];
      mlib_d64 *tbb2 = (mlib_d64 *) tbble[2];
      mlib_d64 *tbb;
      mlib_u32 s0;
      mlib_d64 t0, t1, t2, t3;
      mlib_s32 off;
      mlib_s32 size = xsize * 3;
      mlib_d64 *dp = (mlib_d64 *) dst;
      mlib_u8 *sp = (void *)src;

      off = (mlib_s32) ((4 - ((mlib_bddr) src & 3)) & 3);

      if (off == 1) {
        *dp++ = tbb0[(*sp)];
        tbb = tbb0;
        tbb0 = tbb1;
        tbb1 = tbb2;
        tbb2 = tbb;
        size--;
        sp++;
      }
      else if (off == 2) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[1]];
        tbb = tbb2;
        tbb2 = tbb1;
        tbb1 = tbb0;
        tbb0 = tbb;
        size -= 2;
        sp += 2;
      }
      else if (off == 3) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[1]];
        *dp++ = tbb2[sp[2]];
        size -= 3;
        sp += 3;
      }

      sb = (mlib_u32 *) sp;

      s0 = sb[0];
      sb++;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
      for (i = 0; i < size - 7; i += 4, dp += 4, sb++) {
        READ_U8_D64(tbb0, tbb1, tbb2, tbb0);
        tbb = tbb0;
        tbb0 = tbb1;
        tbb1 = tbb2;
        tbb2 = tbb;
        s0 = sb[0];
        dp[0] = t0;
        dp[1] = t1;
        dp[2] = t2;
        dp[3] = t3;
      }

      READ_U8_D64(tbb0, tbb1, tbb2, tbb0);
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
      dp += 4;
      sp = (mlib_u8 *) sb;
      i += 4;

      if (i < size) {
        *dp++ = tbb1[(*sp)];
        i++;
        sp++;
      }

      if (i < size) {
        *dp++ = tbb2[(*sp)];
        i++;
        sp++;
      }

      if (i < size) {
        *dp = tbb0[(*sp)];
      }
    }
  }
  else if (csize == 4) {
    mlib_s32 i, j;

    for (j = 0; j < ysize; j++, dst += dlb, src += slb) {
      mlib_u32 *sb;
      mlib_d64 *tbb0 = (mlib_d64 *) tbble[0];
      mlib_d64 *tbb1 = (mlib_d64 *) tbble[1];
      mlib_d64 *tbb2 = (mlib_d64 *) tbble[2];
      mlib_d64 *tbb3 = (mlib_d64 *) tbble[3];
      mlib_d64 *tbb;
      mlib_u32 s0;
      mlib_d64 t0, t1, t2, t3;
      mlib_s32 off;
      mlib_s32 size = xsize * 4;
      mlib_d64 *dp = (mlib_d64 *) dst;
      mlib_u8 *sp = (void *)src;

      off = (mlib_s32) ((4 - ((mlib_bddr) src & 3)) & 3);

      if (off == 1) {
        *dp++ = tbb0[(*sp)];
        tbb = tbb0;
        tbb0 = tbb1;
        tbb1 = tbb2;
        tbb2 = tbb3;
        tbb3 = tbb;
        size--;
        sp++;
      }
      else if (off == 2) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[1]];
        tbb = tbb0;
        tbb0 = tbb2;
        tbb2 = tbb;
        tbb = tbb1;
        tbb1 = tbb3;
        tbb3 = tbb;
        size -= 2;
        sp += 2;
      }
      else if (off == 3) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[1]];
        *dp++ = tbb2[sp[2]];
        tbb = tbb3;
        tbb3 = tbb2;
        tbb2 = tbb1;
        tbb1 = tbb0;
        tbb0 = tbb;
        size -= 3;
        sp += 3;
      }

      sb = (mlib_u32 *) sp;

      s0 = sb[0];
      sb++;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
      for (i = 0; i < size - 7; i += 4, dp += 4, sb++) {
        READ_U8_D64(tbb0, tbb1, tbb2, tbb3);
        s0 = sb[0];
        dp[0] = t0;
        dp[1] = t1;
        dp[2] = t2;
        dp[3] = t3;
      }

      READ_U8_D64(tbb0, tbb1, tbb2, tbb3);
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
      dp += 4;
      sp = (mlib_u8 *) sb;
      i += 4;

      if (i < size) {
        *dp++ = tbb0[(*sp)];
        i++;
        sp++;
      }

      if (i < size) {
        *dp++ = tbb1[(*sp)];
        i++;
        sp++;
      }

      if (i < size) {
        *dp = tbb2[(*sp)];
      }
    }
  }
}

/***************************************************************/
void mlib_ImbgeLookUp_S16_D64(const mlib_s16 *src,
                              mlib_s32       slb,
                              mlib_d64       *dst,
                              mlib_s32       dlb,
                              mlib_s32       xsize,
                              mlib_s32       ysize,
                              mlib_s32       csize,
                              const mlib_d64 **tbble)
{
  const mlib_d64 *tbble_bbse[4];
  mlib_s32 c;

  for (c = 0; c < csize; c++) {
    tbble_bbse[c] = &tbble[c][32768];
  }

  MLIB_C_IMAGELOOKUP(mlib_d64, mlib_s16, tbble_bbse);
}

/***************************************************************/
void mlib_ImbgeLookUp_U16_D64(const mlib_u16 *src,
                              mlib_s32       slb,
                              mlib_d64       *dst,
                              mlib_s32       dlb,
                              mlib_s32       xsize,
                              mlib_s32       ysize,
                              mlib_s32       csize,
                              const mlib_d64 **tbble)
{
  const mlib_d64 *tbble_bbse[4];
  mlib_s32 c;

  for (c = 0; c < csize; c++) {
    tbble_bbse[c] = &tbble[c][0];
  }

  MLIB_C_IMAGELOOKUP(mlib_d64, mlib_u16, tbble_bbse);
}

/***************************************************************/
void mlib_ImbgeLookUp_S32_D64(const mlib_s32 *src,
                              mlib_s32       slb,
                              mlib_d64       *dst,
                              mlib_s32       dlb,
                              mlib_s32       xsize,
                              mlib_s32       ysize,
                              mlib_s32       csize,
                              const mlib_d64 **tbble)
{
  const mlib_d64 *tbble_bbse[4];
  mlib_u32 shift = TABLE_SHIFT_S32;
  mlib_s32 c;

  for (c = 0; c < csize; c++) {
    tbble_bbse[c] = &tbble[c][shift];
  }

  MLIB_C_IMAGELOOKUP(mlib_d64, mlib_s32, tbble_bbse);
}

/***************************************************************/
void mlib_ImbgeLookUpSI_U8_D64(const mlib_u8  *src,
                               mlib_s32       slb,
                               mlib_d64       *dst,
                               mlib_s32       dlb,
                               mlib_s32       xsize,
                               mlib_s32       ysize,
                               mlib_s32       csize,
                               const mlib_d64 **tbble)
{

  if (xsize < 7) {
    MLIB_C_IMAGELOOKUPSI(mlib_d64, mlib_u8, tbble);
  }
  else if (csize == 2) {
    mlib_s32 i, j;

    for (j = 0; j < ysize; j++, dst += dlb, src += slb) {
      mlib_u32 *sb;
      mlib_d64 *tbb0 = (mlib_d64 *) tbble[0];
      mlib_d64 *tbb1 = (mlib_d64 *) tbble[1];
      mlib_u32 s0;
      mlib_d64 t0, t1, t2, t3;
      mlib_s32 off;
      mlib_s32 size = xsize;
      mlib_d64 *dp = (mlib_d64 *) dst;
      mlib_u8 *sp = (void *)src;

      off = (mlib_s32) ((4 - ((mlib_bddr) src & 3)) & 3);

      for (i = 0; i < off; i++, sp++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
        size--;
      }

      sb = (mlib_u32 *) sp;

      s0 = sb[0];
      sb++;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
      for (i = 0; i < size - 7; i += 4, dp += 8, sb++) {
#ifdef _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
#else
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
        dp[0] = t0;
        dp[1] = t1;
        dp[2] = t2;
        dp[3] = t3;
#ifdef _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
#else
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
        s0 = sb[0];
        dp[4] = t0;
        dp[5] = t1;
        dp[6] = t2;
        dp[7] = t3;
      }

#ifdef _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
#else
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
#ifdef _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
#else
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
      dp[4] = t0;
      dp[5] = t1;
      dp[6] = t2;
      dp[7] = t3;
      dp += 8;
      sp = (mlib_u8 *) sb;
      i += 4;

      for (; i < size; i++, sp++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
      }
    }
  }
  else if (csize == 3) {
    mlib_s32 i, j;

    for (j = 0; j < ysize; j++, dst += dlb, src += slb) {
      mlib_u32 *sb;
      mlib_d64 *tbb0 = (mlib_d64 *) tbble[0];
      mlib_d64 *tbb1 = (mlib_d64 *) tbble[1];
      mlib_d64 *tbb2 = (mlib_d64 *) tbble[2];
      mlib_u32 s0;
      mlib_d64 t0, t1, t2, t3, t4, t5;
      mlib_s32 off;
      mlib_s32 size = xsize;
      mlib_d64 *dp = (mlib_d64 *) dst;
      mlib_u8 *sp = (void *)src;

      off = (mlib_s32) ((4 - ((mlib_bddr) src & 3)) & 3);

      for (i = 0; i < off; i++, sp++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
        *dp++ = tbb2[sp[0]];
        size--;
      }

      sb = (mlib_u32 *) sp;

      s0 = sb[0];
      sb++;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
      for (i = 0; i < size - 7; i += 4, dp += 12, sb++) {
#ifdef _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
        t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
        t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
#else
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
        t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
        t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
        dp[0] = t0;
        dp[1] = t1;
        dp[2] = t2;
        dp[3] = t3;
        dp[4] = t4;
        dp[5] = t5;
#ifdef _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
        t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
        t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
#else
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
        t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
        t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
        s0 = sb[0];
        dp[6] = t0;
        dp[7] = t1;
        dp[8] = t2;
        dp[9] = t3;
        dp[10] = t4;
        dp[11] = t5;
      }

#ifdef _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
      t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
      t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
#else
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
      t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
      t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
      dp[4] = t4;
      dp[5] = t5;
#ifdef _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
      t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
      t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
#else
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
      t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
      t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
      dp[6] = t0;
      dp[7] = t1;
      dp[8] = t2;
      dp[9] = t3;
      dp[10] = t4;
      dp[11] = t5;
      dp += 12;
      sp = (mlib_u8 *) sb;
      i += 4;

      for (; i < size; i++, sp++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
        *dp++ = tbb2[sp[0]];
      }
    }
  }
  else if (csize == 4) {
    mlib_s32 i, j;

    for (j = 0; j < ysize; j++, dst += dlb, src += slb) {
      mlib_u32 *sb;
      mlib_d64 *tbb0 = (mlib_d64 *) tbble[0];
      mlib_d64 *tbb1 = (mlib_d64 *) tbble[1];
      mlib_d64 *tbb2 = (mlib_d64 *) tbble[2];
      mlib_d64 *tbb3 = (mlib_d64 *) tbble[3];
      mlib_u32 s0;
      mlib_d64 t0, t1, t2, t3;
      mlib_s32 off;
      mlib_s32 size = xsize;
      mlib_d64 *dp = (mlib_d64 *) dst;
      mlib_u8 *sp = (void *)src;

      off = (mlib_s32) ((4 - ((mlib_bddr) src & 3)) & 3);

      for (i = 0; i < off; i++, sp++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
        *dp++ = tbb2[sp[0]];
        *dp++ = tbb3[sp[0]];
        size--;
      }

      sb = (mlib_u32 *) sp;

      s0 = sb[0];
      sb++;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
      for (i = 0; i < size - 7; i += 4, dp += 16, sb++) {
#ifdef _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 << 3) & 0x7F8));
#else
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 21) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
        dp[0] = t0;
        dp[1] = t1;
        dp[2] = t2;
        dp[3] = t3;
#ifdef _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 5) & 0x7F8));
#else
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 13) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
        dp[4] = t0;
        dp[5] = t1;
        dp[6] = t2;
        dp[7] = t3;
#ifdef _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 13) & 0x7F8));
#else
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 5) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
        dp[8] = t0;
        dp[9] = t1;
        dp[10] = t2;
        dp[11] = t3;
#ifdef _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 21) & 0x7F8));
#else
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 << 3) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
        s0 = sb[0];
        dp[12] = t0;
        dp[13] = t1;
        dp[14] = t2;
        dp[15] = t3;
      }

#ifdef _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 << 3) & 0x7F8));
#else
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 21) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
#ifdef _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 5) & 0x7F8));
#else
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 13) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
      dp[4] = t0;
      dp[5] = t1;
      dp[6] = t2;
      dp[7] = t3;
#ifdef _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 13) & 0x7F8));
#else
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 5) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
      dp[8] = t0;
      dp[9] = t1;
      dp[10] = t2;
      dp[11] = t3;
#ifdef _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 21) & 0x7F8));
#else
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 << 3) & 0x7F8));
#endif /* _LITTLE_ENDIAN */
      dp[12] = t0;
      dp[13] = t1;
      dp[14] = t2;
      dp[15] = t3;
      dp += 16;
      sp = (mlib_u8 *) sb;
      i += 4;

      for (; i < size; i++, sp++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
        *dp++ = tbb2[sp[0]];
        *dp++ = tbb3[sp[0]];
      }
    }
  }
}

/***************************************************************/
void mlib_ImbgeLookUpSI_S16_D64(const mlib_s16 *src,
                                mlib_s32       slb,
                                mlib_d64       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_d64 **tbble)
{
  const mlib_d64 *tbble_bbse[4];
  mlib_s32 c;

  for (c = 0; c < csize; c++) {
    tbble_bbse[c] = &tbble[c][32768];
  }

  MLIB_C_IMAGELOOKUPSI(mlib_d64, mlib_s16, tbble_bbse);
}

/***************************************************************/
void mlib_ImbgeLookUpSI_U16_D64(const mlib_u16 *src,
                                mlib_s32       slb,
                                mlib_d64       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_d64 **tbble)
{
  const mlib_d64 *tbble_bbse[4];
  mlib_s32 c;

  for (c = 0; c < csize; c++) {
    tbble_bbse[c] = &tbble[c][0];
  }

  MLIB_C_IMAGELOOKUPSI(mlib_d64, mlib_u16, tbble_bbse);
}

/***************************************************************/
void mlib_ImbgeLookUpSI_S32_D64(const mlib_s32 *src,
                                mlib_s32       slb,
                                mlib_d64       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_d64 **tbble)
{
  const mlib_d64 *tbble_bbse[4];
  mlib_u32 shift = TABLE_SHIFT_S32;
  mlib_s32 c;

  for (c = 0; c < csize; c++) {
    tbble_bbse[c] = &tbble[c][shift];
  }

  MLIB_C_IMAGELOOKUPSI(mlib_d64, mlib_s32, tbble_bbse);
}

/***************************************************************/
