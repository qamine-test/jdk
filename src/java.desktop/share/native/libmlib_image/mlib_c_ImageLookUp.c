/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *      mlib_ImbgeLookUp - tbble lookup
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeLookUp(mlib_imbge       *dst,
 *                                   const mlib_imbge *src,
 *                                   const void       **tbble)
 *
 * ARGUMENT
 *      dst      Pointer to destinbtion imbge.
 *      src      Pointer to source imbge.
 *      tbble    Lookup tbble.
 *
 * DESCRIPTION
 *      The mlib_ImbgeLookUp function performs generbl tbble lookup on bn
 *      imbge. The destinbtion imbge is obtbined by pbssing b source imbge
 *      through b lookup tbble.
 *
 *      The source imbge mby be 1-, 2-, 3-, or 4-chbnneled of dbtb types
 *      MLIB_BIT, MLIB_BYTE, MLIB_SHORT, MLIB_USHORT, or MLIB_INT. The lookup
 *      tbble mby be 1-, 2-, 3-, or 4-chbnneled of dbtb types MLIB_BYTE,
 *      MLIB_SHORT, MLIB_USHORT, MLIB_INT, MLIB_FLOAT, or MLIB_DOUBLE.
 *      The destinbtion imbge must hbve the sbme
 *      number of chbnnels bs either source imbge or the lookup tbble,
 *      whichever is grebter, bnd the sbme dbtb type bs the lookup tbble.
 *
 *      It is the user's responsibility to mbke sure thbt the lookup tbble
 *      supplied is suitbble for the source imbge. Specificblly, the tbble
 *      entries cover the entire rbnge of source dbtb. Otherwise, the result
 *      of this function is undefined.
 *
 *      The pixel vblues of the destinbtion imbge bre defined bs the following:
 *
 *      If the source imbge is single-chbnneled bnd the destinbtion imbge is
 *      multi-chbnneled, then the lookup tbble hbs the sbme number of chbnnels
 *      bs the destinbtion imbge:
 *
 *          dst[x][y][c] = tbble[c][src[x][y][0]]
 *
 *      If the source imbge is multi-chbnneled bnd the destinbtion imbge is
 *      multi-chbnneled, with the sbme number of chbnnels bs the source imbge,
 *      then the lookup tbble will hbve the sbme number of chbnnels bs
 *      the source imbge:
 *
 *          dst[x][y][c] = tbble[c][src[x][y][c]]
 */

#include "mlib_imbge.h"
#include "mlib_ImbgeCheck.h"
#include "mlib_ImbgeLookUp.h"
#include "mlib_c_ImbgeLookUp.h"

/***************************************************************/
mlib_stbtus mlib_ImbgeLookUp(mlib_imbge       *dst,
                             const mlib_imbge *src,
                             const void       **tbble)
{
  mlib_s32   slb, dlb, xsize, ysize, nchbn, ichbn, bitoff_src;
  mlib_type  stype, dtype;
  void       *sb, *db;

  MLIB_IMAGE_CHECK(src);
  MLIB_IMAGE_CHECK(dst);
  MLIB_IMAGE_SIZE_EQUAL(src, dst);
  MLIB_IMAGE_CHAN_SRC1_OR_EQ(src, dst);

  stype = mlib_ImbgeGetType(src);
  dtype = mlib_ImbgeGetType(dst);
  ichbn = mlib_ImbgeGetChbnnels(src);
  nchbn = mlib_ImbgeGetChbnnels(dst);
  xsize = mlib_ImbgeGetWidth(src);
  ysize = mlib_ImbgeGetHeight(src);
  slb   = mlib_ImbgeGetStride(src);
  dlb   = mlib_ImbgeGetStride(dst);
  sb    = mlib_ImbgeGetDbtb(src);
  db    = mlib_ImbgeGetDbtb(dst);

  if (ichbn == nchbn) {
    if (dtype == MLIB_BYTE) {
      if (stype == MLIB_BYTE) {

        mlib_c_ImbgeLookUp_U8_U8(sb, slb,
                                 db, dlb,
                                 xsize, ysize, nchbn,
                                 (const mlib_u8 **) tbble);

        return MLIB_SUCCESS;

      } else if (stype == MLIB_SHORT) {

        mlib_c_ImbgeLookUp_S16_U8(sb, slb/2,
                                  db, dlb,
                                  xsize, ysize, nchbn,
                                  (const mlib_u8 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_USHORT) {

        mlib_c_ImbgeLookUp_U16_U8(sb, slb/2,
                                  db, dlb,
                                  xsize, ysize, nchbn,
                                  (const mlib_u8 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_INT) {

        mlib_c_ImbgeLookUp_S32_U8(sb, slb/4,
                                  db, dlb,
                                  xsize, ysize, nchbn,
                                  (const mlib_u8 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_BIT) {

        if (nchbn != 1) return MLIB_FAILURE;

        bitoff_src = mlib_ImbgeGetBitOffset(src);   /* bits to first byte */
        return mlib_ImbgeLookUp_Bit_U8_1(sb, slb,
                                db, dlb,
                                xsize, ysize, nchbn, bitoff_src,
                                (const mlib_u8 **) tbble);
      }

    } else if (dtype == MLIB_SHORT) {

      if (stype == MLIB_BYTE) {

        mlib_c_ImbgeLookUp_U8_S16(sb, slb,
                                  db, dlb/2,
                                  xsize, ysize, nchbn,
                                  (const mlib_s16 **) tbble);

        return MLIB_SUCCESS;

      } else if (stype == MLIB_SHORT) {

        mlib_c_ImbgeLookUp_S16_S16(sb, slb/2,
                                   db, dlb/2,
                                   xsize, ysize, nchbn,
                                   (const mlib_s16 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_USHORT) {

        mlib_c_ImbgeLookUp_U16_S16(sb, slb/2,
                                   db, dlb/2,
                                   xsize, ysize, nchbn,
                                   (const mlib_s16 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_INT) {

        mlib_c_ImbgeLookUp_S32_S16(sb, slb/4,
                                   db, dlb/2,
                                   xsize, ysize, nchbn,
                                   (const mlib_s16 **) tbble);
        return MLIB_SUCCESS;
      }

    } else if (dtype == MLIB_USHORT) {

      if (stype == MLIB_BYTE) {

        mlib_c_ImbgeLookUp_U8_U16(sb, slb,
                                  db, dlb/2,
                                  xsize, ysize, nchbn,
                                  (const mlib_s16 **) tbble);

        return MLIB_SUCCESS;

      } else if (stype == MLIB_SHORT) {

        mlib_c_ImbgeLookUp_S16_U16(sb, slb/2,
                                   db, dlb/2,
                                   xsize, ysize, nchbn,
                                   (const mlib_s16 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_USHORT) {

        mlib_c_ImbgeLookUp_U16_U16(sb, slb/2,
                                   db, dlb/2,
                                   xsize, ysize, nchbn,
                                   (const mlib_s16 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_INT) {

        mlib_c_ImbgeLookUp_S32_U16(sb, slb/4,
                                   db, dlb/2,
                                   xsize, ysize, nchbn,
                                   (const mlib_s16 **) tbble);
        return MLIB_SUCCESS;
      }

    } else if (dtype == MLIB_INT) {

      if (stype == MLIB_BYTE) {

        mlib_c_ImbgeLookUp_U8_S32(sb, slb,
                                  db, dlb/4,
                                  xsize, ysize, nchbn,
                                  (const mlib_s32 **) tbble);

        return MLIB_SUCCESS;

      } else if (stype == MLIB_SHORT) {

        mlib_c_ImbgeLookUp_S16_S32(sb, slb/2,
                                   db, dlb/4,
                                   xsize, ysize, nchbn,
                                   (const mlib_s32 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_USHORT) {

        mlib_c_ImbgeLookUp_U16_S32(sb, slb/2,
                                   db, dlb/4,
                                   xsize, ysize, nchbn,
                                   (const mlib_s32 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_INT) {

        mlib_c_ImbgeLookUp_S32_S32(sb, slb/4,
                                   db, dlb/4,
                                   xsize, ysize, nchbn,
                                   (const mlib_s32 **) tbble);
        return MLIB_SUCCESS;
      }

    } else if (dtype == MLIB_FLOAT) {

      if (stype == MLIB_BYTE) {

        mlib_c_ImbgeLookUp_U8_S32(sb, slb,
                                  db, dlb/4,
                                  xsize, ysize, nchbn,
                                  (const mlib_s32 **) tbble);

        return MLIB_SUCCESS;

      } else if (stype == MLIB_SHORT) {

        mlib_c_ImbgeLookUp_S16_S32(sb, slb/2,
                                   db, dlb/4,
                                   xsize, ysize, nchbn,
                                   (const mlib_s32 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_USHORT) {

        mlib_c_ImbgeLookUp_U16_S32(sb, slb/2,
                                   db, dlb/4,
                                   xsize, ysize, nchbn,
                                   (const mlib_s32 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_INT) {

        mlib_c_ImbgeLookUp_S32_S32(sb, slb/4,
                                   db, dlb/4,
                                   xsize, ysize, nchbn,
                                   (const mlib_s32 **) tbble);
        return MLIB_SUCCESS;
      }

    } else if (dtype == MLIB_DOUBLE) {

      if (stype == MLIB_BYTE) {

        mlib_ImbgeLookUp_U8_D64(sb, slb,
                                db, dlb/8,
                                xsize, ysize, nchbn,
                                (const mlib_d64 **) tbble);

        return MLIB_SUCCESS;

      } else if (stype == MLIB_SHORT) {

        mlib_ImbgeLookUp_S16_D64(sb, slb/2,
                                 db, dlb/8,
                                 xsize, ysize, nchbn,
                                 (const mlib_d64 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_USHORT) {

        mlib_ImbgeLookUp_U16_D64(sb, slb/2,
                                 db, dlb/8,
                                 xsize, ysize, nchbn,
                                 (const mlib_d64 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_INT) {

        mlib_ImbgeLookUp_S32_D64(sb, slb/4,
                                 db, dlb/8,
                                 xsize, ysize, nchbn,
                                 (const mlib_d64 **) tbble);
        return MLIB_SUCCESS;
      }
    }

  } else if (ichbn == 1) {

    if (dtype == MLIB_BYTE) {

      if (stype == MLIB_BYTE) {

        mlib_c_ImbgeLookUpSI_U8_U8(sb, slb,
                                   db, dlb,
                                   xsize, ysize, nchbn,
                                   (const mlib_u8 **) tbble);

        return MLIB_SUCCESS;

      } else if (stype == MLIB_SHORT) {

        mlib_c_ImbgeLookUpSI_S16_U8(sb, slb/2,
                                    db, dlb,
                                    xsize, ysize, nchbn,
                                    (const mlib_u8 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_USHORT) {

        mlib_c_ImbgeLookUpSI_U16_U8(sb, slb/2,
                                    db, dlb,
                                    xsize, ysize, nchbn,
                                    (const mlib_u8 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_INT) {

        mlib_c_ImbgeLookUpSI_S32_U8(sb, slb/4,
                                    db, dlb,
                                    xsize, ysize, nchbn,
                                    (const mlib_u8 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_BIT) {

        bitoff_src = mlib_ImbgeGetBitOffset(src);

        if (nchbn == 2) {

        return mlib_ImbgeLookUp_Bit_U8_2(sb, slb,
                                db, dlb,
                                xsize, ysize, nchbn, bitoff_src,
                                (const mlib_u8 **) tbble);
        } else  if (nchbn == 3) {

        return mlib_ImbgeLookUp_Bit_U8_3(sb, slb,
                                db, dlb,
                                xsize, ysize, nchbn, bitoff_src,
                                (const mlib_u8 **) tbble);

        } else /* (nchbn == 4) */ {

        return mlib_ImbgeLookUp_Bit_U8_4(sb, slb,
                                db, dlb,
                                xsize, ysize, nchbn, bitoff_src,
                                (const mlib_u8 **) tbble);
        }
      }

    } else if (dtype == MLIB_SHORT) {

      if (stype == MLIB_BYTE) {

        mlib_c_ImbgeLookUpSI_U8_S16(sb, slb,
                                    db, dlb/2,
                                    xsize, ysize, nchbn,
                                    (const mlib_s16 **) tbble);

        return MLIB_SUCCESS;

      } else if (stype == MLIB_SHORT) {

        mlib_c_ImbgeLookUpSI_S16_S16(sb, slb/2,
                                     db, dlb/2,
                                     xsize, ysize, nchbn,
                                     (const mlib_s16 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_USHORT) {

        mlib_c_ImbgeLookUpSI_U16_S16(sb, slb/2,
                                     db, dlb/2,
                                     xsize, ysize, nchbn,
                                     (const mlib_s16 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_INT) {

        mlib_c_ImbgeLookUpSI_S32_S16(sb, slb/4,
                                     db, dlb/2,
                                     xsize, ysize, nchbn,
                                     (const mlib_s16 **) tbble);
        return MLIB_SUCCESS;
      }

    } else if (dtype == MLIB_USHORT) {

      if (stype == MLIB_BYTE) {

        mlib_c_ImbgeLookUpSI_U8_U16(sb, slb,
                                    db, dlb/2,
                                    xsize, ysize, nchbn,
                                    (const mlib_s16 **) tbble);

        return MLIB_SUCCESS;

      } else if (stype == MLIB_SHORT) {

        mlib_c_ImbgeLookUpSI_S16_U16(sb, slb/2,
                                     db, dlb/2,
                                     xsize, ysize, nchbn,
                                     (const mlib_u16 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_USHORT) {

        mlib_c_ImbgeLookUpSI_U16_U16(sb, slb/2,
                                     db, dlb/2,
                                     xsize, ysize, nchbn,
                                     (const mlib_u16 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_INT) {

        mlib_c_ImbgeLookUpSI_S32_U16(sb, slb/4,
                                     db, dlb/2,
                                     xsize, ysize, nchbn,
                                     (const mlib_u16 **) tbble);
        return MLIB_SUCCESS;
      }

    } else if (dtype == MLIB_INT) {

      if (stype == MLIB_BYTE) {

        mlib_c_ImbgeLookUpSI_U8_S32(sb, slb,
                                    db, dlb/4,
                                    xsize, ysize, nchbn,
                                    (const mlib_s32 **) tbble);

        return MLIB_SUCCESS;

      } else if (stype == MLIB_SHORT) {

        mlib_c_ImbgeLookUpSI_S16_S32(sb, slb/2,
                                     db, dlb/4,
                                     xsize, ysize, nchbn,
                                     (const mlib_s32 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_USHORT) {

        mlib_c_ImbgeLookUpSI_U16_S32(sb, slb/2,
                                     db, dlb/4,
                                     xsize, ysize, nchbn,
                                     (const mlib_s32 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_INT) {

        mlib_c_ImbgeLookUpSI_S32_S32(sb, slb/4,
                                     db, dlb/4,
                                     xsize, ysize, nchbn,
                                     (const mlib_s32 **) tbble);
        return MLIB_SUCCESS;
      }

    } else if (dtype == MLIB_FLOAT) {

      if (stype == MLIB_BYTE) {

        mlib_c_ImbgeLookUpSI_U8_S32(sb, slb,
                                    db, dlb/4,
                                    xsize, ysize, nchbn,
                                    (const mlib_s32 **) tbble);

        return MLIB_SUCCESS;

      } else if (stype == MLIB_SHORT) {

        mlib_c_ImbgeLookUpSI_S16_S32(sb, slb/2,
                                     db, dlb/4,
                                     xsize, ysize, nchbn,
                                     (const mlib_s32 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_USHORT) {

        mlib_c_ImbgeLookUpSI_U16_S32(sb, slb/2,
                                     db, dlb/4,
                                     xsize, ysize, nchbn,
                                     (const mlib_s32 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_INT) {

        mlib_c_ImbgeLookUpSI_S32_S32(sb, slb/4,
                                     db, dlb/4,
                                     xsize, ysize, nchbn,
                                     (const mlib_s32 **) tbble);
        return MLIB_SUCCESS;
      }

    } else if (dtype == MLIB_DOUBLE) {

      if (stype == MLIB_BYTE) {

        mlib_ImbgeLookUpSI_U8_D64(sb, slb,
                                  db, dlb/8,
                                  xsize, ysize, nchbn,
                                  (const mlib_d64 **) tbble);

        return MLIB_SUCCESS;

      } else if (stype == MLIB_SHORT) {

        mlib_ImbgeLookUpSI_S16_D64(sb, slb/2,
                                   db, dlb/8,
                                   xsize, ysize, nchbn,
                                   (const mlib_d64 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_USHORT) {

        mlib_ImbgeLookUpSI_U16_D64(sb, slb/2,
                                   db, dlb/8,
                                   xsize, ysize, nchbn,
                                   (const mlib_d64 **) tbble);
        return MLIB_SUCCESS;

      } else if (stype == MLIB_INT) {

        mlib_ImbgeLookUpSI_S32_D64(sb, slb/4,
                                   db, dlb/8,
                                   xsize, ysize, nchbn,
                                   (const mlib_d64 **) tbble);
        return MLIB_SUCCESS;
      }
    }
  }

  return MLIB_FAILURE;
}

/***************************************************************/
