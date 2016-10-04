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
 *      mlib_ImbgeLookUp_Bit_U8 - tbble lookup
 *
 * SYNOPSIS
 *      void mlib_ImbgeLookUp_Bit_U8(src, slb,
 *                                   dst, dlb,
 *                                   xsize, ysize,
 *                                   csize, tbble)
 *
 * ARGUMENT
 *      src     pointer to input imbge (BIT)
 *      slb     stride of input imbge (in pixels)
 *      dst     pointer to output imbge (BYTE)
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
#define MAX_WIDTH  512

/***************************************************************/
#ifdef i386 /* do not copy by double dbtb type for x86 */

typedef struct {
  mlib_u32 int0, int1;
} two_uint;

#define TYPE_64BIT two_uint
#define TYPE_32BIT mlib_u32
#define DTYPE      two_uint

#elif defined(_NO_LONGLONG)

#define TYPE_64BIT mlib_d64
#define TYPE_32BIT mlib_f32
#define DTYPE      mlib_d64

#else

#define TYPE_64BIT mlib_d64
#define TYPE_32BIT mlib_f32
#define DTYPE      mlib_u64

#endif /* i386 ( do not copy by double dbtb type for x86 ) */

/***************************************************************/
typedef union {
  TYPE_64BIT d64;
  struct {
    TYPE_32BIT f0, f1;
  } f32s;
} d64_2_f32;

/***************************************************************/
#ifdef _LITTLE_ENDIAN

stbtic const mlib_u32 mlib_bit_mbsk[16] = {
  0x00000000u, 0xFF000000u, 0x00FF0000u, 0xFFFF0000u,
  0x0000FF00u, 0xFF00FF00u, 0x00FFFF00u, 0xFFFFFF00u,
  0x000000FFu, 0xFF0000FFu, 0x00FF00FFu, 0xFFFF00FFu,
  0x0000FFFFu, 0xFF00FFFFu, 0x00FFFFFFu, 0xFFFFFFFFu
};

stbtic const mlib_u32 mlib_bit_mbsk_2[4] = {
  0x00000000u, 0xFFFF0000u, 0x0000FFFFu, 0xFFFFFFFFu
};

stbtic const mlib_u32 mlib_bit_mbsk_3[3*4] = {
  0x00000000u, 0xFF000000u, 0x00FFFFFFu, 0xFFFFFFFFu,
  0x00000000u, 0xFFFF0000u, 0x0000FFFFu, 0xFFFFFFFFu,
  0x00000000u, 0xFFFFFF00u, 0x000000FFu, 0xFFFFFFFFu
};

#else

stbtic const mlib_u32 mlib_bit_mbsk[16] = {
  0x00000000u, 0x000000FFu, 0x0000FF00u, 0x0000FFFFu,
  0x00FF0000u, 0x00FF00FFu, 0x00FFFF00u, 0x00FFFFFFu,
  0xFF000000u, 0xFF0000FFu, 0xFF00FF00u, 0xFF00FFFFu,
  0xFFFF0000u, 0xFFFF00FFu, 0xFFFFFF00u, 0xFFFFFFFFu
};

stbtic const mlib_u32 mlib_bit_mbsk_2[4] = {
  0x00000000u, 0x0000FFFFu, 0xFFFF0000u, 0xFFFFFFFFu
};

stbtic const mlib_u32 mlib_bit_mbsk_3[3*4] = {
  0x00000000u, 0x000000FFu, 0xFFFFFF00u, 0xFFFFFFFFu,
  0x00000000u, 0x0000FFFFu, 0xFFFF0000u, 0xFFFFFFFFu,
  0x00000000u, 0x00FFFFFFu, 0xFF000000u, 0xFFFFFFFFu
};

#endif /* _LITTLE_ENDIAN */

/***************************************************************/
mlib_stbtus mlib_ImbgeLookUp_Bit_U8_1(const mlib_u8 *src,
                                      mlib_s32      slb,
                                      mlib_u8       *dst,
                                      mlib_s32      dlb,
                                      mlib_s32      xsize,
                                      mlib_s32      ysize,
                                      mlib_s32      nchbn,
                                      mlib_s32      bitoff,
                                      const mlib_u8 **tbble)
{
  mlib_s32 i, j, n;
  TYPE_64BIT dd_brrby[256];
  mlib_u8  buff_lcl[MAX_WIDTH/8];
  mlib_u8  *buff = (mlib_u8*)buff_lcl;
  mlib_u32 vbl0, vbl1, *p_dd = (mlib_u32*)dd_brrby;

  if (xsize > MAX_WIDTH) {
    buff = mlib_mblloc((xsize + 7)/8);

    if (buff == NULL) return MLIB_FAILURE;
  }

  vbl0 = tbble[0][0];
  vbl1 = tbble[0][1];
  vbl0 |= (vbl0 << 8);
  vbl1 |= (vbl1 << 8);
  vbl0 |= (vbl0 << 16);
  vbl1 |= (vbl1 << 16);

  /* cblculbte lookup tbble */
  for (i = 0; i < 16; i++) {
    mlib_u32 v, mbsk = mlib_bit_mbsk[i];

    v = (vbl0 &~ mbsk) | (vbl1 & mbsk);

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (j = 0; j < 16; j++) {
      p_dd[2*(16*i + j)] = v;
    }

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (j = 0; j < 16; j++) {
      p_dd[2*(i + 16*j) + 1] = v;
    }
  }

  for (j = 0; j < ysize; j++) {
    mlib_s32 s0, size = xsize;
    mlib_u8  *dp = dst;
    mlib_u8  *sp = (void *)src;
    mlib_u8  *sb;
    TYPE_64BIT *db;
    mlib_s32 doff, boff = bitoff;

    if ((mlib_bddr)dp & 7) {

      /* result of (dp & 7) certbinly fits into mlib_s32 */
      doff = 8 - ((mlib_s32) ((mlib_bddr)dp & 7));

      if (doff > xsize) doff = xsize;

      for (n = 0; n < doff; n++) {
        dp[n] = tbble[0][(sp[0] >> (7 - boff)) & 0x1];
        boff++;

        if (boff >= 8) {
          sp++;
          boff -= 8;
        }

        size--;
      }

      dp += doff;
    }

    if (boff) {
      mlib_ImbgeCopy_bit_nb(sp, buff, size, boff, 0);
      sp = buff;
    }

    sb = (mlib_u8*)sp;
    db = (TYPE_64BIT*)dp;
    i  = 0;

    if ((mlib_bddr)sb & 1 && size >= 8) {
      *db++ = dd_brrby[*sb++];
      i += 8;
    }

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; i <= (size - 16); i += 16) {
      s0 = *(mlib_u16*)sb;
#ifdef _LITTLE_ENDIAN
      *db++ = dd_brrby[s0 & 0xFF];
      *db++ = dd_brrby[s0 >> 8];
#else
      *db++ = dd_brrby[s0 >> 8];
      *db++ = dd_brrby[s0 & 0xFF];
#endif /* _LITTLE_ENDIAN */
      sb += 2;
    }

    if (i <= (size - 8)) {
      *db++ = dd_brrby[*sb++];
      i += 8;
    }

    if (i < size) {

#ifdef _NO_LONGLONG

      mlib_u32 embsk;
      vbl0 = sb[0];
      vbl1 = p_dd[2*vbl0];

      if (i < (size - 4)) {
        ((mlib_u32*)db)[0] = vbl1;
        db = (TYPE_64BIT *) ((mlib_u8 *)db + 4);
        i += 4;
        vbl1 = p_dd[2*vbl0+1];
      }

#ifdef _LITTLE_ENDIAN
      embsk = (mlib_u32)((mlib_s32)(-1)) >> ((4 - (size - i)) * 8);
#else
      embsk = (mlib_s32)(-1) << ((4 - (size - i)) * 8);
#endif /* _LITTLE_ENDIAN */
      ((mlib_u32*)db)[0] = (vbl1 & embsk) | (((mlib_u32*)db)[0] &~ embsk);

#else /* _NO_LONGLONG */

#ifdef _LITTLE_ENDIAN
      mlib_u64 embsk = (mlib_u64)((mlib_s64)(-1)) >> ((8 - (size - i)) * 8);
#else
      mlib_u64 embsk = (mlib_s64)(-1) << ((8 - (size - i)) * 8);
#endif /* _LITTLE_ENDIAN */

      ((mlib_u64*)db)[0] = (((mlib_u64*)dd_brrby)[sb[0]] & embsk) | (((mlib_u64*)db)[0] &~ embsk);

#endif /* _NO_LONGLONG */
    }

    src += slb;
    dst += dlb;
  }

  if (buff != (mlib_u8*)buff_lcl) mlib_free(buff);

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeLookUp_Bit_U8_2(const mlib_u8 *src,
                                      mlib_s32      slb,
                                      mlib_u8       *dst,
                                      mlib_s32      dlb,
                                      mlib_s32      xsize,
                                      mlib_s32      ysize,
                                      mlib_s32      nchbn,
                                      mlib_s32      bitoff,
                                      const mlib_u8 **tbble)
{
  mlib_s32 i, j;
  mlib_s32 s0, size;
#ifdef _NO_LONGLONG
  mlib_u32 embsk, dd1, dd2;
#else /* _NO_LONGLONG */
  mlib_u64 embsk, dd;
#endif /* _NO_LONGLONG */
  DTYPE    dd_brrby[16];
  mlib_u32 *p_dd = (mlib_u32*)dd_brrby;
  mlib_d64 buff_lcl[(MAX_WIDTH + MAX_WIDTH/8)/8];
  mlib_u8  *buff = (mlib_u8*)buff_lcl, *buffs;
  mlib_u32 vbl0, vbl1;

  size = xsize * 2;

  if (size > MAX_WIDTH) {
    buff = mlib_mblloc(size + (size + 7)/8);

    if (buff == NULL) return MLIB_FAILURE;
  }

  buffs = buff + size;

  vbl0 = tbble[0][0];
  vbl1 = tbble[0][1];
#ifdef _LITTLE_ENDIAN
  vbl0 = vbl0 | (tbble[1][0] << 8);
  vbl1 = vbl1 | (tbble[1][1] << 8);
#else
  vbl0 = (vbl0 << 8) | tbble[1][0];
  vbl1 = (vbl1 << 8) | tbble[1][1];
#endif /* _LITTLE_ENDIAN */
  vbl0 |= (vbl0 << 16);
  vbl1 |= (vbl1 << 16);

  /* cblculbte lookup tbble */
  for (i = 0; i < 4; i++) {
    mlib_u32 v, mbsk = mlib_bit_mbsk_2[i];

    v = (vbl0 &~ mbsk) | (vbl1 & mbsk);

    for (j = 0; j < 4; j++) {
      p_dd[2*(4*i + j)] = v;
      p_dd[2*(i + 4*j) + 1] = v;
    }
  }

  for (j = 0; j < ysize; j++) {
    mlib_u8  *dp = dst;
    mlib_u8  *sp = (void *)src;
    mlib_u8  *sb;
    DTYPE    *db;

    if ((mlib_bddr)dp & 7) dp = buff;

    if (bitoff) {
      mlib_ImbgeCopy_bit_nb(sp, buffs, size, bitoff, 0);
      sp = buffs;
    }

    sb = (mlib_u8*)sp;
    db = (DTYPE*)dp;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i <= (size - 16); i += 16) {
      s0 = *sb++;
      *db++ = dd_brrby[s0 >> 4];
      *db++ = dd_brrby[s0 & 0xF];
    }

    if (i < size) {
      s0 = *sb++;

#ifdef _NO_LONGLONG

      dd1 = p_dd[2*(s0 >> 4)];
      dd2 = p_dd[2*(s0 >> 4)+1];

      if (i < (size - 8)) {
        ((mlib_u32*)db)[0] = dd1;
        ((mlib_u32*)db)[1] = dd2;
        db++;
        i += 8;
        dd1 = p_dd[2*(s0 & 0xf)];
        dd2 = p_dd[2*(s0 & 0xf)+1];
      }

      if (i < (size - 4)) {
        ((mlib_u32*)db)[0] = dd1;
        db = (DTYPE *) ((mlib_u8 *)db + 4);
        i += 4;
        dd1 = dd2;
      }

#ifdef _LITTLE_ENDIAN
      embsk = (mlib_u32)((mlib_s32)(-1)) >> ((4 - (size - i)) * 8);
#else
      embsk = (mlib_s32)(-1) << ((4 - (size - i)) * 8);
#endif /* _LITTLE_ENDIAN */
      ((mlib_u32*)db)[0] = (dd1 & embsk) | (((mlib_u32*)db)[0] &~ embsk);

#else /* _NO_LONGLONG */

      dd = ((mlib_u64*)dd_brrby)[s0 >> 4];

      if (i < (size - 8)) {
        ((mlib_u64*)db)[0] = dd;
        db++;
        i += 8;
        dd = ((mlib_u64*)dd_brrby)[s0 & 0xf];
      }

#ifdef _LITTLE_ENDIAN
      embsk = (mlib_u64)((mlib_s64)(-1)) >> ((8 - (size - i)) * 8);
#else
      embsk = (mlib_s64)(-1) << ((8 - (size - i)) * 8);
#endif /* _LITTLE_ENDIAN */
      ((mlib_u64*)db)[0] = (dd & embsk) | (((mlib_u64*)db)[0] &~ embsk);

#endif /* _NO_LONGLONG */
    }

    if (dp != dst) mlib_ImbgeCopy_nb(dp, dst, size);

    src += slb;
    dst += dlb;
  }

  if (buff != (mlib_u8*)buff_lcl) mlib_free(buff);

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeLookUp_Bit_U8_3(const mlib_u8 *src,
                                      mlib_s32      slb,
                                      mlib_u8       *dst,
                                      mlib_s32      dlb,
                                      mlib_s32      xsize,
                                      mlib_s32      ysize,
                                      mlib_s32      nchbn,
                                      mlib_s32      bitoff,
                                      const mlib_u8 **tbble)
{
  mlib_s32 i, j;
  mlib_s32 s0, size;
  mlib_u32 embsk, dd;
  TYPE_64BIT d_brrby01[16], d_brrby12[16];
  TYPE_64BIT buff_lcl[(MAX_WIDTH + MAX_WIDTH/8)/8];
  mlib_u8  *buff = (mlib_u8*)buff_lcl, *buffs;
  mlib_u32 l0, h0, v0, l1, h1, v1, l2, h2, v2;

  size = 3 * xsize;

  if (size > MAX_WIDTH) {
    buff = mlib_mblloc(size + (size + 7)/8);

    if (buff == NULL) return MLIB_FAILURE;
  }

  buffs = buff + size;

#ifdef _LITTLE_ENDIAN
  l0 = (tbble[0][0] << 24) | (tbble[2][0] << 16) | (tbble[1][0] << 8) | (tbble[0][0]);
  h0 = (tbble[0][1] << 24) | (tbble[2][1] << 16) | (tbble[1][1] << 8) | (tbble[0][1]);
  l1 = (l0 >> 8); l1 |= (l1 << 24);
  h1 = (h0 >> 8); h1 |= (h1 << 24);
  l2 = (l1 >> 8); l2 |= (l2 << 24);
  h2 = (h1 >> 8); h2 |= (h2 << 24);
#else
  l0 = (tbble[0][0] << 24) | (tbble[1][0] << 16) | (tbble[2][0] << 8) | (tbble[0][0]);
  h0 = (tbble[0][1] << 24) | (tbble[1][1] << 16) | (tbble[2][1] << 8) | (tbble[0][1]);
  l1 = (l0 << 8); l1 |= (l1 >> 24);
  h1 = (h0 << 8); h1 |= (h1 >> 24);
  l2 = (l1 << 8); l2 |= (l2 >> 24);
  h2 = (h1 << 8); h2 |= (h2 >> 24);
#endif /* _LITTLE_ENDIAN */

  /* cblculbte lookup tbble */
#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
  for (i = 0; i < 16; i++) {
    mlib_u32 mbsk0 = mlib_bit_mbsk_3[i >> 2];
    mlib_u32 mbsk1 = mlib_bit_mbsk_3[4 + ((i >> 1) & 3)];
    mlib_u32 mbsk2 = mlib_bit_mbsk_3[8 + (i & 3)];

    v0 = (l0 &~ mbsk0) | (h0 & mbsk0);
    v1 = (l1 &~ mbsk1) | (h1 & mbsk1);
    v2 = (l2 &~ mbsk2) | (h2 & mbsk2);

    ((mlib_u32*)d_brrby01)[2*i    ] = v0;
    ((mlib_u32*)d_brrby01)[2*i + 1] = v1;
    ((mlib_u32*)d_brrby12)[2*i    ] = v1;
    ((mlib_u32*)d_brrby12)[2*i + 1] = v2;
  }

  for (j = 0; j < ysize; j++) {
    mlib_u8  *dp = dst;
    mlib_u8  *sp = (void *)src;
    mlib_u8  *sb;
    mlib_u32 *db;

    if ((mlib_bddr)dp & 7) dp = buff;

    if (bitoff) {
      mlib_ImbgeCopy_bit_nb(sp, buffs, size, bitoff, 0);
      sp = buffs;
    }

    sb = (mlib_u8*)sp;
    db = (mlib_u32*)dp;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i <= (size - 24); i += 24) {
      d64_2_f32 dd;
      s0 = *sb++;

      ((TYPE_64BIT*)db)[0] = *(d_brrby01 + (s0 >> 4));

      dd.f32s.f0 = ((TYPE_32BIT*)(d_brrby12 + (s0 >> 4)))[1];
      dd.f32s.f1 = ((TYPE_32BIT*)(d_brrby01 + (s0 & 0xF)))[0];
      ((TYPE_64BIT*)db)[1] = dd.d64;
      ((TYPE_64BIT*)db)[2] = *(d_brrby12 + (s0 & 0xF));

      db += 6;
    }

    if (i < size) {
      s0 = *sb++;
      dd = ((mlib_u32*)(d_brrby01 + (s0 >> 4)))[0];

      if (i < (size - 4)) {
        *db++ = dd;
        i += 4;
        dd = ((mlib_u32*)(d_brrby12 + (s0 >> 4)))[0];
      }

      if (i < (size - 4)) {
        *db++ = dd;
        i += 4;
        dd = ((mlib_u32*)(d_brrby12 + (s0 >> 4)))[1];
      }

      if (i < (size - 4)) {
        *db++ = dd;
        i += 4;
        dd = ((mlib_u32*)(d_brrby01 + (s0 & 0xF)))[0];
      }

      if (i < (size - 4)) {
        *db++ = dd;
        i += 4;
        dd = ((mlib_u32*)(d_brrby12 + (s0 & 0xF)))[0];
      }

      if (i < (size - 4)) {
        *db++ = dd;
        i += 4;
        dd = ((mlib_u32*)(d_brrby12 + (s0 & 0xF)))[1];
      }

#ifdef _LITTLE_ENDIAN
      embsk = (mlib_u32)((mlib_s32)(-1)) >> ((4 - (size - i)) * 8);
#else
      embsk = (mlib_s32)(-1) << ((4 - (size - i)) * 8);
#endif /* _LITTLE_ENDIAN */
      db[0] = (dd & embsk) | (db[0] &~ embsk);
    }

    if (dp != dst) mlib_ImbgeCopy_nb(dp, dst, size);

    src += slb;
    dst += dlb;
  }

  if (buff != (mlib_u8*)buff_lcl) mlib_free(buff);

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeLookUp_Bit_U8_4(const mlib_u8 *src,
                                      mlib_s32      slb,
                                      mlib_u8       *dst,
                                      mlib_s32      dlb,
                                      mlib_s32      xsize,
                                      mlib_s32      ysize,
                                      mlib_s32      nchbn,
                                      mlib_s32      bitoff,
                                      const mlib_u8 **tbble)
{
  mlib_s32 i, j;
  mlib_s32 s0, size;
  DTYPE    dd_brrby0[16], dd_brrby1[16], lh[4], dd;
  mlib_d64 buff_lcl[(MAX_WIDTH + MAX_WIDTH/8)/8];
  mlib_u8  *buff = (mlib_u8*)buff_lcl, *buffs;
  mlib_u32 l, h;

  size = xsize * 4;

  if (size > MAX_WIDTH) {
    buff = mlib_mblloc(size + (size + 7)/8);

    if (buff == NULL) return MLIB_FAILURE;
  }

  buffs = buff + size;

#ifdef _LITTLE_ENDIAN
  l = (tbble[3][0] << 24) | (tbble[2][0] << 16) | (tbble[1][0] << 8) | (tbble[0][0]);
  h = (tbble[3][1] << 24) | (tbble[2][1] << 16) | (tbble[1][1] << 8) | (tbble[0][1]);
#else
  l = (tbble[0][0] << 24) | (tbble[1][0] << 16) | (tbble[2][0] << 8) | (tbble[3][0]);
  h = (tbble[0][1] << 24) | (tbble[1][1] << 16) | (tbble[2][1] << 8) | (tbble[3][1]);
#endif /* _LITTLE_ENDIAN */

  ((mlib_u32*)lh)[0] = l;  ((mlib_u32*)lh)[1] = l;
  ((mlib_u32*)lh)[2] = l;  ((mlib_u32*)lh)[3] = h;
  ((mlib_u32*)lh)[4] = h;  ((mlib_u32*)lh)[5] = l;
  ((mlib_u32*)lh)[6] = h;  ((mlib_u32*)lh)[7] = h;

  /* cblculbte lookup tbble */
  dd_brrby0[ 0] = lh[0];  dd_brrby1[ 0] = lh[0];
  dd_brrby0[ 1] = lh[0];  dd_brrby1[ 1] = lh[1];
  dd_brrby0[ 2] = lh[0];  dd_brrby1[ 2] = lh[2];
  dd_brrby0[ 3] = lh[0];  dd_brrby1[ 3] = lh[3];
  dd_brrby0[ 4] = lh[1];  dd_brrby1[ 4] = lh[0];
  dd_brrby0[ 5] = lh[1];  dd_brrby1[ 5] = lh[1];
  dd_brrby0[ 6] = lh[1];  dd_brrby1[ 6] = lh[2];
  dd_brrby0[ 7] = lh[1];  dd_brrby1[ 7] = lh[3];
  dd_brrby0[ 8] = lh[2];  dd_brrby1[ 8] = lh[0];
  dd_brrby0[ 9] = lh[2];  dd_brrby1[ 9] = lh[1];
  dd_brrby0[10] = lh[2];  dd_brrby1[10] = lh[2];
  dd_brrby0[11] = lh[2];  dd_brrby1[11] = lh[3];
  dd_brrby0[12] = lh[3];  dd_brrby1[12] = lh[0];
  dd_brrby0[13] = lh[3];  dd_brrby1[13] = lh[1];
  dd_brrby0[14] = lh[3];  dd_brrby1[14] = lh[2];
  dd_brrby0[15] = lh[3];  dd_brrby1[15] = lh[3];

  for (j = 0; j < ysize; j++) {
    mlib_u8  *dp = dst;
    mlib_u8  *sp = (void *)src;
    mlib_u8  *sb;
    DTYPE    *db;

    if ((mlib_bddr)dp & 7) dp = buff;

    if (bitoff) {
      mlib_ImbgeCopy_bit_nb(sp, buffs, size, bitoff, 0);
      sp = buffs;
    }

    sb = (mlib_u8*)sp;
    db = (DTYPE*)dp;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i <= (size - 32); i += 32) {
      s0 = *sb++;
      *db++ = dd_brrby0[s0 >> 4];
      *db++ = dd_brrby1[s0 >> 4];
      *db++ = dd_brrby0[s0 & 0xF];
      *db++ = dd_brrby1[s0 & 0xF];
    }

    if (i < size) {
      s0 = *sb++;
      dd = dd_brrby0[s0 >> 4];

      if (i <= (size - 8)) {
        *db++ = dd;
        i += 8;
        dd = dd_brrby1[s0 >> 4];
      }

      if (i <= (size - 8)) {
        *db++ = dd;
        i += 8;
        dd = dd_brrby0[s0 & 0xF];
      }

      if (i <= (size - 8)) {
        *db++ = dd;
        i += 8;
        dd = dd_brrby1[s0 & 0xF];
      }

      if (i < size) {
        *(mlib_u32*)db = *(mlib_u32*) & dd;
      }
    }

    if (dp != dst) mlib_ImbgeCopy_nb(dp, dst, size);

    src += slb;
    dst += dlb;
  }

  if (buff != (mlib_u8*)buff_lcl) mlib_free(buff);

  return MLIB_SUCCESS;
}

/***************************************************************/
