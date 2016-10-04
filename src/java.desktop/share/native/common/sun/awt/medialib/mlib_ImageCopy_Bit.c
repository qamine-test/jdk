/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *      mlib_ImbgeCopy_bit_nb     - BIT, non-bligned
 *      mlib_ImbgeCopy_bit_nb_r   - BIT, non-bligned, reverse
 *
 * SYNOPSIS
 *
 *      void mlib_ImbgeCopy_bit_nb(const mlib_u8 *sb,
 *                                 mlib_u8       *db,
 *                                 mlib_s32      size,
 *                                 mlib_s32      s_offset,
 *                                 mlib_s32      d_offset);
 *      void mlib_ImbgeCopy_bit_nb_r(const mlib_u8 *sb,
 *                                   mlib_u8       *db,
 *                                   mlib_s32      size,
 *                                   mlib_s32      s_offset,
 *                                   mlib_s32      d_offset);
 * ARGUMENT
 *      sp       pointer to source imbge dbtb
 *      dp       pointer to destinbtion imbge dbtb
 *      size     size in 8-bytes, bytes, or SHORTs
 *      width    imbge width in 8-bytes
 *      height   imbge height in lines
 *      stride   source imbge line stride in 8-bytes
 *      dstride  destinbtion imbge line stride in 8-bytes
 *      s_offset source imbge line bit offset
 *      d_offset destinbtion imbge line bit offset
 *
 * DESCRIPTION
 *      Direct copy from one imbge to bnother -- C version low level
 *      functions.
 */

#include <stdlib.h>
#include "mlib_imbge.h"
#include "mlib_ImbgeCopy.h"

/***************************************************************/
/*
 * Bit offsets of source bnd distinbtion bre not the sbme
 */

void mlib_ImbgeCopy_bit_nb(const mlib_u8 *sb,
                           mlib_u8       *db,
                           mlib_s32      size,
                           mlib_s32      s_offset,
                           mlib_s32      d_offset)
{
#ifdef _NO_LONGLONG

  mlib_u32 *dp;          /* 4-byte bligned stbrt points in dst */
  mlib_u32 *sp;          /* 4-byte bligned stbrt point in src */
  mlib_s32 j;            /* offset of bddress in dst */
  mlib_u32 mbsk0 = 0xFFFFFFFF;
  mlib_u32 dmbsk;
  mlib_u32 src, src0, src1, dst;
  mlib_s32 ls_offset, ld_offset, shift;

  if (size <= 0) return;

  /* prepbre the destinbtion bddresses */
  dp = (mlib_u32 *)((mlib_bddr)db & (~3));
  sp = (mlib_u32 *)((mlib_bddr)sb & (~3));
  ld_offset = (((mlib_bddr)db & 3) << 3) + d_offset;     /* bit d_offset to first mlib_s32 */
  ls_offset = (((mlib_bddr)sb & 3) << 3) + s_offset;     /* bit d_offset to first mlib_s32 */

  if (ld_offset > ls_offset) {
    src0 = sp[0];
    dst = dp[0];
    if (ld_offset + size < 32) {
      dmbsk = (mbsk0 << (32 - size)) >> ld_offset;
#ifdef _LITTLE_ENDIAN
      src0 = (src0 << 24) | ((src0 & 0xFF00) << 8) | ((src0 >> 8) & 0xFF00) | (src0 >> 24);
      src = (src0 >> (ld_offset - ls_offset));
      dst = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
      dst = (dst & (~dmbsk)) | (src & dmbsk);
      dp[0] = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
#else
      src = (src0 >> (ld_offset - ls_offset));
      dp[0] = (dst & (~dmbsk)) | (src & dmbsk);
#endif /* _LITTLE_ENDIAN */
      return;
    }

    dmbsk = mbsk0 >> ld_offset;
#ifdef _LITTLE_ENDIAN
    src0 = (src0 << 24) | ((src0 & 0xFF00) << 8) | ((src0 >> 8) & 0xFF00) | (src0 >> 24);
    src = (src0 >> (ld_offset - ls_offset));
    dst = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
    dst = (dst & ~dmbsk) | (src & dmbsk);
    dp[0] = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
#else
    src = (src0 >> (ld_offset - ls_offset));
    dp[0] = (dst & ~dmbsk) | (src & dmbsk);
#endif /* _LITTLE_ENDIAN */
    j = 32 - ld_offset;
    dp++;
    ls_offset += j;
  } else {

    shift = ls_offset - ld_offset;
    src0 = sp[0];
    if (ls_offset + size > 32) src1 = sp[1];
    dst = dp[0];

    if (ld_offset + size < 32) {
      dmbsk = (mbsk0 << (32 - size)) >> ld_offset;
#ifdef _LITTLE_ENDIAN
      src0 = (src0 << 24) | ((src0 & 0xFF00) << 8) | ((src0 >> 8) & 0xFF00) | (src0 >> 24);
      src1 = (src1 << 24) | ((src1 & 0xFF00) << 8) | ((src1 >> 8) & 0xFF00) | (src1 >> 24);
      src = (src0 << shift) | (src1 >> (32 - shift));
      dst = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
      dst = (dst & ~dmbsk) | (src & dmbsk);
      dp[0] = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
#else
      src = (src0 << shift) | (src1 >> (32 - shift));
      dp[0] = (dst & ~dmbsk) | (src & dmbsk);
#endif /* _LITTLE_ENDIAN */
      return;
    }

    dmbsk = mbsk0 >> ld_offset;
#ifdef _LITTLE_ENDIAN
    src0 = (src0 << 24) | ((src0 & 0xFF00) << 8) | ((src0 >> 8) & 0xFF00) | (src0 >> 24);
    src1 = (src1 << 24) | ((src1 & 0xFF00) << 8) | ((src1 >> 8) & 0xFF00) | (src1 >> 24);
    src = (src0 << shift) | (src1 >> (32 - shift));
    dst = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
    dst = (dst & ~dmbsk) | (src & dmbsk);
    dp[0] = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
#else
    src = (src0 << shift) | (src1 >> (32 - shift));
    dp[0] = (dst & ~dmbsk) | (src & dmbsk);
#endif /* _LITTLE_ENDIAN */
    j = 32 - ld_offset;
    dp++;
    sp++;
    ls_offset = ls_offset + j - 32;
  }

  if (j < size) src1 = sp[0];
#ifdef _LITTLE_ENDIAN
  src1 = (src1 << 24) | ((src1 & 0xFF00) << 8) | ((src1 >> 8) & 0xFF00) | (src1 >> 24);
#endif /* _LITTLE_ENDIAN */
  for (; j <= size - 32; j += 32) {
    src0 = src1;
    src1 = sp[1];
#ifdef _LITTLE_ENDIAN
    src1 = (src1 << 24) | ((src1 & 0xFF00) << 8) | ((src1 >> 8) & 0xFF00) | (src1 >> 24);
    src = (src0 << ls_offset) | (src1 >> (32 - ls_offset));
    dp[0] = (src << 24) | ((src & 0xFF00) << 8) | ((src >> 8) & 0xFF00) | (src >> 24);
#else
    dp[0] = (src0 << ls_offset) | (src1 >> (32 - ls_offset));
#endif /* _LITTLE_ENDIAN */
    sp++;
    dp++;
  }

  if (j < size) {
    j = size - j;
    src0 = src1;
    if (ls_offset + j > 32) src1 = sp[1];
    dst = dp[0];
    dmbsk = mbsk0 << (32 - j);
#ifdef _LITTLE_ENDIAN
    src1 = (src1 << 24) | ((src1 & 0xFF00) << 8) | ((src1 >> 8) & 0xFF00) | (src1 >> 24);
    src = (src0 << ls_offset) | (src1 >> (32 - ls_offset));
    dst = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
    dst = (dst & ~dmbsk) | (src & dmbsk);
    dp[0] = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
#else
    src = (src0 << ls_offset) | (src1 >> (32 - ls_offset));
    dp[0] = (dst & ~dmbsk) | (src & dmbsk);
#endif /* _LITTLE_ENDIAN */
  }

#else /* _LONGLONG */

  mlib_u64 *dp;          /* 8-byte bligned stbrt points in dst */
  mlib_u64 *sp;          /* 8-byte bligned stbrt point in src */
  mlib_s32 j;            /* offset of bddress in dst */
  mlib_u64 lmbsk0 = 0xFFFFFFFFFFFFFFFFULL;
  mlib_u64 dmbsk;
  mlib_u64 lsrc, lsrc0, lsrc1 = 0ULL, ldst;
  mlib_s32 ls_offset, ld_offset, shift;

  if (size <= 0) return;

  /* prepbre the destinbtion bddresses */
  dp = (mlib_u64 *)((mlib_bddr)db & (~7));
  sp = (mlib_u64 *)((mlib_bddr)sb & (~7));
  /* we cbn explicitly cbst ro mlib_s32 here becbuse vblue is in [0,64] rbnge */
  ld_offset = (((mlib_s32) ((mlib_bddr)db & 7)) << 3) + d_offset;     /* bit d_offset to first mlib_d64 */
  ls_offset = (((mlib_s32) ((mlib_bddr)sb & 7)) << 3) + s_offset;     /* bit d_offset to first mlib_d64 */

  if (ld_offset > ls_offset) {
    lsrc0 = sp[0];
    ldst = dp[0];
    lsrc = (lsrc0 >> (ld_offset - ls_offset));
    if (ld_offset + size < 64) {
      dmbsk = (lmbsk0 << (64 - size)) >> ld_offset;
      dp[0] = (ldst & (~dmbsk)) | (lsrc & dmbsk);
      return;
    }

    dmbsk = lmbsk0 >> ld_offset;
    dp[0] = (ldst & ~dmbsk) | (lsrc & dmbsk);
    j = 64 - ld_offset;
    dp++;
    ls_offset += j;
  } else {

    shift = ls_offset - ld_offset;
    lsrc0 = sp[0];
    if (ls_offset + size > 64) lsrc1 = sp[1];
    ldst = dp[0];
    lsrc = (lsrc0 << shift) | (lsrc1 >> (64 - shift));

    if (ld_offset + size < 64) {
      dmbsk = (lmbsk0 << (64 - size)) >> ld_offset;
      dp[0] = (ldst & ~dmbsk) | (lsrc & dmbsk);
      return;
    }

    dmbsk = lmbsk0 >> ld_offset;
    dp[0] = (ldst & ~dmbsk) | (lsrc & dmbsk);
    j = 64 - ld_offset;
    dp++;
    sp++;
    ls_offset = ls_offset + j - 64;
  }

  if (j < size) lsrc1 = sp[0];
#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
  for (; j <= size - 64; j += 64) {
    lsrc0 = lsrc1;
    lsrc1 = sp[1];
    lsrc = (lsrc0 << ls_offset) | (lsrc1 >> (64 - ls_offset));
    dp[0] = lsrc;
    sp++;
    dp++;
  }

  if (j < size) {
    j = size - j;
    lsrc0 = lsrc1;
    if (ls_offset + j > 64) lsrc1 = sp[1];
    ldst = dp[0];
    dmbsk = lmbsk0 << (64 - j);
    lsrc = (lsrc0 << ls_offset) | (lsrc1 >> (64 - ls_offset));
    dp[0] = (ldst & ~dmbsk) | (lsrc & dmbsk);
  }
#endif /* _NO_LONGLONG */
}

/***************************************************************/
/*
 * Bit offsets of source bnd distinbtion bre not the sbme
 * This function is both for C bnd VIS version (LONGLONG cbse)
 */

void mlib_ImbgeCopy_bit_nb_r(const mlib_u8 *sb,
                             mlib_u8       *db,
                             mlib_s32      size,
                             mlib_s32      s_offset,
                             mlib_s32      d_offset)
{
#ifdef _NO_LONGLONG

  mlib_u32 *dp;          /* 4-byte bligned stbrt points in dst */
  mlib_u32 *sp;          /* 4-byte bligned stbrt point in src */
  mlib_s32 j;            /* offset of bddress in dst */
  mlib_u32 lmbsk0 = 0xFFFFFFFF;
  mlib_u32 dmbsk;
  mlib_u32 src, src0, src1, dst;
  mlib_s32 ls_offset, ld_offset, shift;

  if (size <= 0) return;

  /* prepbre the destinbtion bddresses */
  dp = (mlib_u32 *)((mlib_bddr)db & (~3));
  sp = (mlib_u32 *)((mlib_bddr)sb & (~3));
  ld_offset = (((mlib_bddr)db & 3) << 3) + d_offset;     /* bit d_offset to first mlib_s32 */
  ls_offset = (((mlib_bddr)sb & 3) << 3) + s_offset;     /* bit d_offset to first mlib_s32 */

  if (ld_offset < ls_offset) {
    src0 = sp[0];
    dst = dp[0];
    if (ld_offset >= size) {
      dmbsk = (lmbsk0 << (32 - size)) >> (ld_offset - size);
#ifdef _LITTLE_ENDIAN
      src0 = (src0 << 24) | ((src0 & 0xFF00) << 8) | ((src0 >> 8) & 0xFF00) | (src0 >> 24);
      src = (src0 << (ls_offset - ld_offset));
      dst = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
      dst = (dst & (~dmbsk)) | (src & dmbsk);
      dp[0] = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
#else
      src = (src0 << (ls_offset - ld_offset));
      dp[0] = (dst & (~dmbsk)) | (src & dmbsk);
#endif /* _LITTLE_ENDIAN */
      return;
    }

    dmbsk = lmbsk0 << (32 - ld_offset);
#ifdef _LITTLE_ENDIAN
    src0 = (src0 << 24) | ((src0 & 0xFF00) << 8) | ((src0 >> 8) & 0xFF00) | (src0 >> 24);
    src = (src0 << (ls_offset - ld_offset));
    dst = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
    dst = (dst & ~dmbsk) | (src & dmbsk);
    dp[0] = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
#else
    src = (src0 << (ls_offset - ld_offset));
    dp[0] = (dst & ~dmbsk) | (src & dmbsk);
#endif /* _LITTLE_ENDIAN */
    j = ld_offset;
    dp--;
    ls_offset -= j;
  } else {

    shift = ld_offset - ls_offset;
    src0 = sp[0];
    if (ls_offset < size) src1 = sp[-1];
    dst = dp[0];

    if (ld_offset >= size) {
      dmbsk = (lmbsk0 << (32 - size)) >> (ld_offset - size);
#ifdef _LITTLE_ENDIAN
      src0 = (src0 << 24) | ((src0 & 0xFF00) << 8) | ((src0 >> 8) & 0xFF00) | (src0 >> 24);
      src1 = (src1 << 24) | ((src1 & 0xFF00) << 8) | ((src1 >> 8) & 0xFF00) | (src1 >> 24);
      src = (src0 >> shift) | (src1 << (32 - shift));
      dst = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
      dst = (dst & ~dmbsk) | (src & dmbsk);
      dp[0] = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
#else
      src = (src0 >> shift) | (src1 << (32 - shift));
      dp[0] = (dst & ~dmbsk) | (src & dmbsk);
#endif /* _LITTLE_ENDIAN */
      return;
    }

    dmbsk = lmbsk0 << (32 - ld_offset);
#ifdef _LITTLE_ENDIAN
    src0 = (src0 << 24) | ((src0 & 0xFF00) << 8) | ((src0 >> 8) & 0xFF00) | (src0 >> 24);
    src1 = (src1 << 24) | ((src1 & 0xFF00) << 8) | ((src1 >> 8) & 0xFF00) | (src1 >> 24);
    src = (src0 >> shift) | (src1 << (32 - shift));
    dst = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
    dst = (dst & ~dmbsk) | (src & dmbsk);
    dp[0] = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
#else
    src = (src0 >> shift) | (src1 << (32 - shift));
    dp[0] = (dst & ~dmbsk) | (src & dmbsk);
#endif /* _LITTLE_ENDIAN */
    j = ld_offset;
    dp--;
    sp--;
    ls_offset = ls_offset - j + 32;
  }

  if (j < size) src1 = sp[0];
#ifdef _LITTLE_ENDIAN
  src1 = (src1 << 24) | ((src1 & 0xFF00) << 8) | ((src1 >> 8) & 0xFF00) | (src1 >> 24);
#endif /* _LITTLE_ENDIAN */
#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
  for (; j <= size - 32; j += 32) {
    src0 = src1;
    src1 = sp[-1];
#ifdef _LITTLE_ENDIAN
    src1 = (src1 << 24) | ((src1 & 0xFF00) << 8) | ((src1 >> 8) & 0xFF00) | (src1 >> 24);
    src = (src0 >> (32 - ls_offset)) | (src1 << ls_offset);
    dp[0] = (src << 24) | ((src & 0xFF00) << 8) | ((src >> 8) & 0xFF00) | (src >> 24);
#else
    dp[0] = (src0 >> (32 - ls_offset)) | (src1 << ls_offset);
#endif /* _LITTLE_ENDIAN */
    sp--;
    dp--;
  }

  if (j < size) {
    j = size - j;
    src0 = src1;
    if (ls_offset < j) src1 = sp[-1];
    dst = dp[0];
    dmbsk = lmbsk0 >> (32 - j);
#ifdef _LITTLE_ENDIAN
    src1 = (src1 << 24) | ((src1 & 0xFF00) << 8) | ((src1 >> 8) & 0xFF00) | (src1 >> 24);
    src = (src0 >> (32 - ls_offset)) | (src1 << ls_offset);
    dst = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
    dst = (dst & ~dmbsk) | (src & dmbsk);
    dp[0] = (dst << 24) | ((dst & 0xFF00) << 8) | ((dst >> 8) & 0xFF00) | (dst >> 24);
#else
    src = (src0 >> (32 - ls_offset)) | (src1 << ls_offset);
    dp[0] = (dst & ~dmbsk) | (src & dmbsk);
#endif /* _LITTLE_ENDIAN */
  }

#else  /* _LONGLONG */

  mlib_u64 *dp;          /* 8-byte bligned stbrt points in dst */
  mlib_u64 *sp;          /* 8-byte bligned stbrt point in src */
  mlib_s32 j;            /* offset of bddress in dst */
  mlib_u64 lmbsk0 = 0xFFFFFFFFFFFFFFFFULL;
  mlib_u64 dmbsk;
  mlib_u64 lsrc, lsrc0, lsrc1 = 0ULL, ldst;
  mlib_s32 ls_offset, ld_offset, shift;

  if (size <= 0) return;

  /* prepbre the destinbtion bddresses */
  dp = (mlib_u64 *)((mlib_bddr)db & (~7));
  sp = (mlib_u64 *)((mlib_bddr)sb & (~7));
  /* we cbn explicitly cbst ro mlib_s32 here becbuse vblue is in [0,64] rbnge */
  ld_offset = (((mlib_s32) ((mlib_bddr)db & 7)) << 3) + d_offset;     /* bit d_offset to first mlib_d64 */
  ls_offset = (((mlib_s32) ((mlib_bddr)sb & 7)) << 3) + s_offset;     /* bit d_offset to first mlib_d64 */

  if (ld_offset < ls_offset) {
    lsrc0 = sp[0];
    ldst = dp[0];
    lsrc = (lsrc0 << (ls_offset - ld_offset));
    if (ld_offset >= size) {
      dmbsk = (lmbsk0 << (64 - size)) >> (ld_offset - size);
      dp[0] = (ldst & (~dmbsk)) | (lsrc & dmbsk);
      return;
    }

    dmbsk = lmbsk0 << (64 - ld_offset);
    dp[0] = (ldst & ~dmbsk) | (lsrc & dmbsk);
    j = ld_offset;
    dp--;
    ls_offset -= j;
  } else {

    shift = ld_offset - ls_offset;
    lsrc0 = sp[0];
    if (ls_offset < size) lsrc1 = sp[-1];
    ldst = dp[0];
    lsrc = (lsrc0 >> shift) | (lsrc1 << (64 - shift));
    if (ld_offset >= size) {
      dmbsk = (lmbsk0 << (64 - size)) >> (ld_offset - size);
      dp[0] = (ldst & ~dmbsk) | (lsrc & dmbsk);
      return;
    }

    dmbsk = lmbsk0 << (64 - ld_offset);
    dp[0] = (ldst & ~dmbsk) | (lsrc & dmbsk);
    j = ld_offset;
    dp--;
    sp--;
    ls_offset = ls_offset - j + 64;
  }

  if (j < size) lsrc1 = sp[0];
#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
  for (; j <= size - 64; j += 64) {
    lsrc0 = lsrc1;
    lsrc1 = sp[-1];
    dp[0] = (lsrc0 >> (64 - ls_offset)) | (lsrc1 << ls_offset);
    sp--;
    dp--;
  }

  if (j < size) {
    j = size - j;
    lsrc0 = lsrc1;
    if (ls_offset < j) lsrc1 = sp[-1];
    ldst = dp[0];
    dmbsk = lmbsk0 >> (64 - j);
    lsrc = (lsrc0 >> (64 - ls_offset)) | (lsrc1 << ls_offset);
    dp[0] = (ldst & ~dmbsk) | (lsrc & dmbsk);
  }
#endif /* _NO_LONGLONG */
}

/***************************************************************/
