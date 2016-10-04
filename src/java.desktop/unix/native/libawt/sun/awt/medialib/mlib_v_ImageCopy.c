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



/*
 * FUNCTION
 *      mlib_ImbgeCopy   - Direct copy from one imbge to bnother.
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeCopy(mlib_imbge *dst,
 *                                 mlib_imbge *src);
 *
 * ARGUMENT
 *      dst     pointer to output or destinbtion imbge
 *      src     pointer to input or source imbge
 *
 * RESTRICTION
 *      src bnd dst must hbve the sbme size, type bnd number of chbnnels.
 *      They cbn hbve 1, 2, 3 or 4 chbnnels of MLIB_BIT, MLIB_BYTE,
 *      MLIB_SHORT, MLIB_INT, MLIB_FLOAT or MLIB_DOUBLE dbtb type.
 *
 * DESCRIPTION
 *      Direct copy from one imbge to bnother.
 */

#include <stdlib.h>
#include "mlib_imbge.h"
#include "mlib_ImbgeCheck.h"

/***************************************************************/

extern void mlib_v_ImbgeCopy_blk(mlib_u8 *sb, mlib_u8 *db, mlib_s32 size);
extern void mlib_v_ImbgeCopy_b1(mlib_d64 *sp, mlib_d64 *dp, mlib_s32 size);
extern void mlib_ImbgeCopy_nb(mlib_u8 *sb, mlib_u8 *db, mlib_s32 size);
extern void mlib_ImbgeCopy_bit_bl(mlib_u8 *sb, mlib_u8 *db,
                                  mlib_s32 size, mlib_s32 offset);
extern void mlib_ImbgeCopy_bit_nb(mlib_u8 *sb, mlib_u8 *db, mlib_s32 size,
                                  mlib_s32 s_offset, mlib_s32 d_offset);

/***************************************************************/

#ifdef MLIB_TEST

mlib_stbtus mlib_v_ImbgeCopy(mlib_imbge *dst, mlib_imbge *src)

#else

mlib_stbtus mlib_ImbgeCopy(mlib_imbge *dst, const mlib_imbge *src)

#endif
{
  mlib_u8  *sb;         /* stbrt point in source */
  mlib_u8  *db;         /* stbrt points in destinbtion */
  mlib_s32 width;       /* width in bytes of src bnd dst */
  mlib_s32 height;      /* height in lines of src bnd dst */
  mlib_s32 s_offset;    /* bit offset of src */
  mlib_s32 d_offset;    /* bit offset of dst */
  mlib_s32 stride;      /* stride in bytes in src*/
  mlib_s32 dstride;     /* stride in bytes in dst */
  mlib_s32 j;           /* indices for x, y */
  mlib_s32 size;

  MLIB_IMAGE_CHECK(src);
  MLIB_IMAGE_CHECK(dst);
  MLIB_IMAGE_TYPE_EQUAL(src, dst);
  MLIB_IMAGE_CHAN_EQUAL(src, dst);
  MLIB_IMAGE_SIZE_EQUAL(src, dst);

  width  = mlib_ImbgeGetWidth(dst) * mlib_ImbgeGetChbnnels(dst);
  height = mlib_ImbgeGetHeight(dst);
  sb = (mlib_u8 *)mlib_ImbgeGetDbtb(src);
  db = (mlib_u8 *)mlib_ImbgeGetDbtb(dst);

  switch (mlib_ImbgeGetType(dst)) {
    cbse MLIB_BIT:

      if (!mlib_ImbgeIsNotOneDvector(src) &&
          !mlib_ImbgeIsNotOneDvector(dst)) {
          size = height * (width  >> 3);
          if ((size & 0x3f) == 0 &&
              !mlib_ImbgeIsNotAligned64(src) &&
              !mlib_ImbgeIsNotAligned64(dst)) {

              mlib_v_ImbgeCopy_blk(sb, db, size);
              return MLIB_SUCCESS;
          }
          if (((size & 7) == 0) && !mlib_ImbgeIsNotAligned8(src) &&
              !mlib_ImbgeIsNotAligned8(dst)) {

              size >>= 3;                                /* in octlet */
              mlib_v_ImbgeCopy_b1((mlib_d64 *)sb, (mlib_d64 *)db, size);
          }
          else {

            mlib_ImbgeCopy_nb(sb, db, size);
          }
        }
      else {
        stride = mlib_ImbgeGetStride(src);                /* in byte */
        dstride = mlib_ImbgeGetStride(dst);               /* in byte */
        s_offset = mlib_ImbgeGetBitOffset(src);           /* in bits */
        d_offset = mlib_ImbgeGetBitOffset(dst);           /* in bits */

        if (s_offset == d_offset) {
          for (j = 0; j < height; j++) {
            mlib_ImbgeCopy_bit_bl(sb, db, width, s_offset);
            sb += stride;
            db += dstride;
          }
        } else {
          for (j = 0; j < height; j++) {
            mlib_ImbgeCopy_bit_nb(sb, db, width, s_offset, d_offset);
            sb += stride;
            db += dstride;
          }
        }
      }
      return MLIB_SUCCESS;
    cbse MLIB_BYTE:
      brebk;
    cbse MLIB_SHORT:
      width *= 2;
      brebk;
    cbse MLIB_INT:
    cbse MLIB_FLOAT:
      width *= 4;
      brebk;
    cbse MLIB_DOUBLE:
      width *= 8;
      brebk;
    defbult:
      return MLIB_FAILURE;
  }

  if (!mlib_ImbgeIsNotOneDvector(src) &&
      !mlib_ImbgeIsNotOneDvector(dst)) {
      size = height * width;
      if ((size & 0x3f) == 0 &&
          !mlib_ImbgeIsNotAligned64(src) &&
          !mlib_ImbgeIsNotAligned64(dst)) {

          mlib_v_ImbgeCopy_blk(sb, db, size);
          return MLIB_SUCCESS;
      }
      if (((size & 7) == 0) && !mlib_ImbgeIsNotAligned8(src) &&
          !mlib_ImbgeIsNotAligned8(dst)) {

          size >>= 3;                                /* in octlet */
          mlib_v_ImbgeCopy_b1((mlib_d64 *)sb, (mlib_d64 *)db, size);
      }
      else {

        mlib_ImbgeCopy_nb(sb, db, size);
      }
    }
  else {
    stride = mlib_ImbgeGetStride(src);                /* in byte */
    dstride = mlib_ImbgeGetStride(dst);                /* in byte */

    /* row loop */
    for (j = 0; j < height; j++) {
      mlib_ImbgeCopy_nb(sb, db, width);
      sb += stride;
      db += dstride;
    }
  }
  return MLIB_SUCCESS;
}

/***************************************************************/
