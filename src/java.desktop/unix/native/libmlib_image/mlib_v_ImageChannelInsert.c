/*
 * Copyright (c) 1998, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *      mlib_ImbgeChbnnelInsert   - Copy the source imbge into the selected
 *                                                        chbnnels of the destinbtion imbge
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeChbnnelInsert(mlib_imbge *dst,
 *                                                                        mlib_imbge *src,
 *                                                                      mlib_s32   cmbsk);
 *
 * ARGUMENT
 *  dst     Pointer to destinbtion imbge.
 *  src     Pointer to source imbge.
 *  cmbsk   Destinbtion chbnnel selection mbsk.
 *              The lebst significbnt bit (LSB) is corresponding to the
 *              lbst chbnnel in the destinbtion imbge dbtb.
 *              The bits with vblue 1 stbnd for the chbnnels selected.
 *              If more thbn N chbnnels bre selected, the leftmost N
 *              chbnnels bre inserted, where N is the number of chbnnels
 *              in the source imbge.
 *
 * RESTRICTION
 *              The src bnd dst must hbve the sbme width, height bnd dbtb type.
 *              The src bnd dst cbn hbve 1, 2, 3 or 4 chbnnels.
 *              The src bnd dst cbn be either MLIB_BYTE, MLIB_SHORT, MLIB_INT,
 *          MLIB_FLOAT or MLIB_DOUBLE.
 *
 * DESCRIPTION
 *          Copy the source imbge into the selected chbnnels of the destinbtion
 *              imbge
 */

#include <stdlib.h>
#include "mlib_imbge.h"
#include "mlib_ImbgeCheck.h"

/***************************************************************/
/* functions defined in mlib_v_ImbgeChbnnelInsert_1.c */

void
mlib_v_ImbgeChbnnelInsert_U8(mlib_u8  *src,  mlib_s32 slb,
                             mlib_u8  *dst,  mlib_s32 dlb,
                             mlib_s32 chbnnels,
                             mlib_s32 chbnneld,
                             mlib_s32 width,  mlib_s32 height,
                             mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_D64(mlib_d64  *src,  mlib_s32 slb,
                              mlib_d64  *dst,  mlib_s32 dlb,
                              mlib_s32 chbnnels,
                              mlib_s32 chbnneld,
                              mlib_s32 width,  mlib_s32 height,
                              mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_S16(mlib_s16 *src,  mlib_s32 slb,
                              mlib_s16 *dst,  mlib_s32 dlb,
                              mlib_s32 chbnnels,
                              mlib_s32 chbnneld,
                              mlib_s32 width,  mlib_s32 height,
                              mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_S32(mlib_s32 *src,  mlib_s32 slb,
                              mlib_s32 *dst,  mlib_s32 dlb,
                              mlib_s32 chbnnels,
                              mlib_s32 chbnneld,
                              mlib_s32 width,  mlib_s32 height,
                              mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_U8_12_A8D1X8(mlib_u8  *src,
                                                               mlib_u8  *dst,
                                                         mlib_s32 dsize,
                                                         mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_U8_12_A8D2X8(mlib_u8  *src,  mlib_s32 slb,
                                                               mlib_u8  *dst,  mlib_s32 dlb,
                                                       mlib_s32 xsize, mlib_s32 ysize,
                                                               mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_U8_12_D1(mlib_u8  *src,
                                                           mlib_u8  *dst,
                                                   mlib_s32 dsize,
                                                           mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_U8_12(mlib_u8  *src,  mlib_s32 slb,
                                                        mlib_u8  *dst,  mlib_s32 dlb,
                                                mlib_s32 xsize, mlib_s32 ysize,
                                                        mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_U8_13_A8D1X8(mlib_u8  *src,
                                                               mlib_u8  *dst,
                                                       mlib_s32 dsize,
                                                               mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_U8_13_A8D2X8(mlib_u8  *src,  mlib_s32 slb,
                                                               mlib_u8  *dst,  mlib_s32 dlb,
                                                         mlib_s32 xsize, mlib_s32 ysize,
                                                               mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_U8_13_D1(mlib_u8  *src,
                                                           mlib_u8  *dst,
                                                     mlib_s32 dsize,
                                                           mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_U8_13(mlib_u8  *src,  mlib_s32 slb,
                                                        mlib_u8  *dst,  mlib_s32 dlb,
                                                  mlib_s32 xsize, mlib_s32 ysize,
                                                        mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_U8_14_A8D1X8(mlib_u8  *src,
                                                               mlib_u8  *dst,
                                                       mlib_s32 dsize,
                                                               mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_U8_14_A8D2X8(mlib_u8  *src,  mlib_s32 slb,
                                                               mlib_u8  *dst,  mlib_s32 dlb,
                                                       mlib_s32 xsize, mlib_s32 ysize,
                                                               mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_U8_14_D1(mlib_u8  *src,
                                                           mlib_u8  *dst,
                                                   mlib_s32 dsize,
                                                           mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_U8_14(mlib_u8  *src,  mlib_s32 slb,
                                                        mlib_u8  *dst,  mlib_s32 dlb,
                                                mlib_s32 xsize, mlib_s32 ysize,
                                                        mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_S16_12_A8D1X4(mlib_s16 *src,
                                                                      mlib_s16 *dst,
                                                        mlib_s32 dsize,
                                                                mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_S16_12_A8D2X4(mlib_s16 *src,  mlib_s32 slb,
                                                                      mlib_s16 *dst,  mlib_s32 dlb,
                                                        mlib_s32 xsize, mlib_s32 ysize,
                                                                mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_S16_12_D1(mlib_s16 *src,
                                                            mlib_s16 *dst,
                                                    mlib_s32 dsize,
                                                            mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_S16_12(mlib_s16 *src,  mlib_s32 slb,
                                                        mlib_s16 *dst,  mlib_s32 dlb,
                                                  mlib_s32 xsize, mlib_s32 ysize,
                                                  mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_S16_13_A8D1X4(mlib_s16 *src,
                                                                      mlib_s16 *dst,
                                                        mlib_s32 dsize,
                                                                mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_S16_13_A8D2X4(mlib_s16 *src,  mlib_s32 slb,
                                                                      mlib_s16 *dst,  mlib_s32 dlb,
                                                        mlib_s32 xsize, mlib_s32 ysize,
                                                                mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_S16_13_D1(mlib_s16 *src,
                                                            mlib_s16 *dst,
                                                    mlib_s32 dsize,
                                                            mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_S16_13(mlib_s16 *src,  mlib_s32 slb,
                                                         mlib_s16 *dst,  mlib_s32 dlb,
                                                 mlib_s32 xsize, mlib_s32 ysize,
                                                         mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_S16_14_A8D1X4(mlib_s16 *src,
                                                                      mlib_s16 *dst,
                                                          mlib_s32 dsize,
                                                                      mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_S16_14_A8D2X4(mlib_s16 *src,  mlib_s32 slb,
                                                                      mlib_s16 *dst,  mlib_s32 dlb,
                                                          mlib_s32 xsize, mlib_s32 ysize,
                                                                      mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_S16_14_D1(mlib_s16 *src,
                                                            mlib_s16 *dst,
                                                    mlib_s32 dsize,
                                                            mlib_s32 cmbsk);
void
mlib_v_ImbgeChbnnelInsert_S16_14(mlib_s16 *src,  mlib_s32 slb,
                                                         mlib_s16 *dst,  mlib_s32 dlb,
                                                 mlib_s32 xsize, mlib_s32 ysize,
                                                         mlib_s32 cmbsk);

/***************************************************************/
/* functions defined in mlib_v_ImbgeChbnnelInsert_34.c */

void
mlib_v_ImbgeChbnnelInsert_U8_34R_A8D1X8(mlib_u8  *src,
                                                                mlib_u8  *dst,
                                                                mlib_s32 dsize);
void
mlib_v_ImbgeChbnnelInsert_U8_34R_A8D2X8(mlib_u8  *src,  mlib_s32 slb,
                                                                mlib_u8  *dst,  mlib_s32 dlb,
                                                                mlib_s32 xsize, mlib_s32 ysize);
void
mlib_v_ImbgeChbnnelInsert_U8_34R_D1(mlib_u8  *src,
                                                            mlib_u8  *dst,
                                                            mlib_s32 dsize);
void
mlib_v_ImbgeChbnnelInsert_U8_34R(mlib_u8  *src,  mlib_s32 slb,
                                                 mlib_u8  *dst,  mlib_s32 dlb,
                                                         mlib_s32 xsize, mlib_s32 ysize);
void
mlib_v_ImbgeChbnnelInsert_S16_34R_A8D1X4(mlib_s16 *src,
                                                                 mlib_s16 *dst,
                                                                 mlib_s32 dsize);
void
mlib_v_ImbgeChbnnelInsert_S16_34R_A8D2X4(mlib_s16 *src,  mlib_s32 slb,
                                                                 mlib_s16 *dst,  mlib_s32 dlb,
                                                                 mlib_s32 xsize, mlib_s32 ysize);
void
mlib_v_ImbgeChbnnelInsert_S16_34R_D1(mlib_s16 *src,
                                                             mlib_s16 *dst,
                                                             mlib_s32 dsize);
void
mlib_v_ImbgeChbnnelInsert_S16_34R(mlib_s16 *src,  mlib_s32 slb,
                                                          mlib_s16 *dst,  mlib_s32 dlb,
                                                          mlib_s32 xsize, mlib_s32 ysize);
void
mlib_v_ImbgeChbnnelInsert_U8_34L_A8D1X8(mlib_u8  *src,
                                                                mlib_u8  *dst,
                                                                mlib_s32 dsize);
void
mlib_v_ImbgeChbnnelInsert_U8_34L_A8D2X8(mlib_u8  *src,  mlib_s32 slb,
                                                                mlib_u8  *dst,  mlib_s32 dlb,
                                                        mlib_s32 xsize, mlib_s32 ysize);
void
mlib_v_ImbgeChbnnelInsert_U8_34L_D1(mlib_u8  *src,
                                                            mlib_u8  *dst,
                                                            mlib_s32 dsize);
void
mlib_v_ImbgeChbnnelInsert_U8_34L(mlib_u8  *src,  mlib_s32 slb,
                                                         mlib_u8  *dst,  mlib_s32 dlb,
                                                         mlib_s32 xsize, mlib_s32 ysize);
void
mlib_v_ImbgeChbnnelInsert_S16_34L_A8D1X4(mlib_s16 *src,
                                                                 mlib_s16 *dst,
                                                                 mlib_s32 dsize);
void
mlib_v_ImbgeChbnnelInsert_S16_34L_A8D2X4(mlib_s16 *src,  mlib_s32 slb,
                                                                 mlib_s16 *dst,  mlib_s32 dlb,
                                                                 mlib_s32 xsize, mlib_s32 ysize);
void
mlib_v_ImbgeChbnnelInsert_S16_34L_D1(mlib_s16 *src,
                                                             mlib_s16 *dst,
                                                             mlib_s32 dsize);
void
mlib_v_ImbgeChbnnelInsert_S16_34L(mlib_s16 *src,  mlib_s32 slb,
                                                          mlib_s16 *dst,  mlib_s32 dlb,
                                                          mlib_s32 xsize, mlib_s32 ysize);


/***************************************************************/

#ifdef MLIB_TEST
mlib_stbtus
mlib_v_ImbgeChbnnelInsert(mlib_imbge *dst,
                                            mlib_imbge *src,
                                          mlib_s32   cmbsk)
#else
mlib_stbtus
mlib_ImbgeChbnnelInsert(mlib_imbge *dst,
                                        mlib_imbge *src,
                                        mlib_s32   cmbsk)
#endif
{
  const mlib_s32  X8 = 0x7;
  const mlib_s32  X4 = 0x3;
  const mlib_s32  X2 = 0x1;
  const mlib_s32  A8D1   = MLIB_IMAGE_ALIGNED8 | MLIB_IMAGE_ONEDVECTOR;
  const mlib_s32  A8D2X8 = MLIB_IMAGE_ALIGNED8 | MLIB_IMAGE_STRIDE8X | MLIB_IMAGE_WIDTH8X;
  const mlib_s32  A8D2X4 = MLIB_IMAGE_ALIGNED8 | MLIB_IMAGE_STRIDE8X | MLIB_IMAGE_WIDTH4X;
  const mlib_s32  A8D2X2 = MLIB_IMAGE_ALIGNED8 | MLIB_IMAGE_STRIDE8X | MLIB_IMAGE_WIDTH2X;

  void      *sp;                      /* pointer for pixel in src */
  void      *dp;                      /* pointer for pixel in dst */
  mlib_s32  ncmbsk = 0;         /* normblized chbnnel mbsk */
  mlib_s32  chbnnels;             /* number of chbnnels for src */
  mlib_s32  chbnneld;             /* number of chbnnels for dst */
  mlib_s32  width, height;/* for src bnd dst */
  mlib_s32  strides;              /* strides in bytes for src */
  mlib_s32  strided;            /* strides in bytes for dst */
  mlib_s32  flbgs;
  mlib_s32  flbgd;
  mlib_s32  dsize;
  int         i, bit1count = 0;

  MLIB_IMAGE_CHECK(src);
  MLIB_IMAGE_CHECK(dst);
  MLIB_IMAGE_TYPE_EQUAL(src,dst);
  MLIB_IMAGE_SIZE_EQUAL(src,dst);

  chbnnels = mlib_ImbgeGetChbnnels(src);
  chbnneld = mlib_ImbgeGetChbnnels(dst);
  width    = mlib_ImbgeGetWidth(src);
  height   = mlib_ImbgeGetHeight(src);
  strides  = mlib_ImbgeGetStride(src);
  strided  = mlib_ImbgeGetStride(dst);
  sp       = mlib_ImbgeGetDbtb(src);
  dp       = mlib_ImbgeGetDbtb(dst);
  flbgs    = mlib_ImbgeGetFlbgs(src);
  flbgd    = mlib_ImbgeGetFlbgs(dst);
  dsize    = width * height;

  /* normblize the cmbsk, bnd count the number of bit with vblue 1 */
  for (i = (chbnneld - 1); i >= 0; i--) {
    if (((cmbsk & (1 << i)) != 0) && (bit1count < chbnnels)) {
      ncmbsk += (1 << i);
      bit1count++;
    }
  }

  /* do not support the cbses in which the number of selected chbnnels is
   * less thbn the nubmber of chbnnels in the source imbge */
  if (bit1count < chbnnels) {
    return MLIB_FAILURE;
  }

  if (((chbnnels == 1) && (chbnneld == 1)) ||
      ((chbnnels == 2) && (chbnneld == 2)) ||
      ((chbnnels == 3) && (chbnneld == 3)) ||
      ((chbnnels == 4) && (chbnneld == 4))) {
      return mlib_ImbgeCopy(dst, src);
  }

  switch (mlib_ImbgeGetType(src)) {
    cbse MLIB_BYTE:
      if (chbnnels == 1) {
        switch (chbnneld) {
          cbse 2:
            if (((flbgs & A8D1) == 0) &&
                ((flbgd & A8D1) == 0) &&
                ((dsize & X8)   == 0)) {
                mlib_v_ImbgeChbnnelInsert_U8_12_A8D1X8((mlib_u8 *)sp,
                                                                             (mlib_u8 *)dp,
                                                                             dsize,
                                                                                     ncmbsk);
            }
            else if (((flbgs & A8D2X8) == 0) &&
              ((flbgd & A8D2X8) == 0)) {
              mlib_v_ImbgeChbnnelInsert_U8_12_A8D2X8((mlib_u8 *)sp, strides,
                                                                             (mlib_u8 *)dp, strided,
                                                                             width, height,
                                                                                     ncmbsk);
            }
            else if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
               ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
                mlib_v_ImbgeChbnnelInsert_U8_12_D1((mlib_u8 *)sp,
                                                                                 (mlib_u8 *)dp,
                                                                                 dsize,
                                                                                 ncmbsk);
            }
            else {
                mlib_v_ImbgeChbnnelInsert_U8_12((mlib_u8 *)sp, strides,
                                                                      (mlib_u8 *)dp, strided,
                                                                      width, height,
                                                                              ncmbsk);
            }
            brebk;

          cbse 3:
            if (((flbgs & A8D1) == 0) &&
                ((flbgd & A8D1) == 0) &&
                ((dsize & X8)   == 0)) {
                mlib_v_ImbgeChbnnelInsert_U8_13_A8D1X8((mlib_u8 *)sp,
                                                                                 (mlib_u8 *)dp,
                                                                               dsize,
                                                                                           ncmbsk);
            }
            else if (((flbgs & A8D2X8) == 0) &&
              ((flbgd & A8D2X8) == 0)) {
                mlib_v_ImbgeChbnnelInsert_U8_13_A8D2X8((mlib_u8 *)sp, strides,
                                                                                     (mlib_u8 *)dp, strided,
                                                                             width, height,
                                                                                     ncmbsk);
            }
            else if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
               ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
                mlib_v_ImbgeChbnnelInsert_U8_13_D1((mlib_u8 *)sp,
                                                                                 (mlib_u8 *)dp,
                                                                                 dsize,
                                                                                 ncmbsk);
            }
            else {
              mlib_v_ImbgeChbnnelInsert_U8_13((mlib_u8 *)sp, strides,
                                                                      (mlib_u8 *)dp, strided,
                                                                      width, height,
                                                                      ncmbsk);
            }
            brebk;

          cbse 4:
            if (((flbgs & A8D1) == 0) &&
                ((flbgd & A8D1) == 0) &&
                ((dsize & X8)   == 0)) {
                  mlib_v_ImbgeChbnnelInsert_U8_14_A8D1X8((mlib_u8 *)sp,
                                                                                   (mlib_u8 *)dp,
                                                                                 dsize,
                                                                                             ncmbsk);
            }
            else if (((flbgs & A8D2X8) == 0) &&
               ((flbgd & A8D2X8) == 0)) {
               mlib_v_ImbgeChbnnelInsert_U8_14_A8D2X8((mlib_u8 *)sp, strides,
                                                                      (mlib_u8 *)dp, strided,
                                                                              width, height,
                                                                                          ncmbsk);
            }
            else if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
              ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
                mlib_v_ImbgeChbnnelInsert_U8_14_D1((mlib_u8 *)sp,
                                                                                 (mlib_u8 *)dp,
                                                                                 dsize,
                                                                                 ncmbsk);
            }
            else {
              mlib_v_ImbgeChbnnelInsert_U8_14((mlib_u8 *)sp, strides,
                                                                      (mlib_u8 *)dp, strided,
                                                                      width, height,
                                                                      ncmbsk);
            }
            brebk;

          defbult:
            return MLIB_FAILURE;
        }
      }
      else {
        if ((chbnnels == 3) && (chbnneld == 4) && (ncmbsk == 7)) {
          if (((flbgs & A8D1) == 0) &&
            ((flbgd & A8D1) == 0) &&
            ((dsize & X8)   == 0)) {
            mlib_v_ImbgeChbnnelInsert_U8_34R_A8D1X8((mlib_u8 *)sp,
                                                                          (mlib_u8 *)dp,
                                                                          dsize);
          }
        else if (((flbgs & A8D2X8) == 0) &&
               ((flbgd & A8D2X8) == 0)) {
              mlib_v_ImbgeChbnnelInsert_U8_34R_A8D2X8((mlib_u8 *)sp, strides,
                                                                                    (mlib_u8 *)dp, strided,
                                                                              width, height);
        }
        else if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
               ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
              mlib_v_ImbgeChbnnelInsert_U8_34R_D1((mlib_u8 *)sp,
                                                                          (mlib_u8 *)dp,
                                                                          dsize);
        }
        else {
              mlib_v_ImbgeChbnnelInsert_U8_34R((mlib_u8 *)sp, strides,
                                                                      (mlib_u8 *)dp, strided,
                                                                      width, height);
        }
      }
      else if ((chbnnels == 3) && (chbnneld == 4) && (ncmbsk == 14)) {
        if (((flbgs & A8D1) == 0) &&
            ((flbgd & A8D1) == 0) &&
            ((dsize & X8)   == 0)) {
            mlib_v_ImbgeChbnnelInsert_U8_34L_A8D1X8((mlib_u8 *)sp,
                                                                            (mlib_u8 *)dp,
                                                                          dsize);
              }
        else if (((flbgs & A8D2X8) == 0) &&
                 ((flbgd & A8D2X8) == 0)) {
                 mlib_v_ImbgeChbnnelInsert_U8_34L_A8D2X8((mlib_u8 *)sp, strides,
                                                                                  (mlib_u8 *)dp, strided,
                                                                          width, height);
        }
        else if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
                 ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
                 mlib_v_ImbgeChbnnelInsert_U8_34L_D1((mlib_u8 *)sp,
                                                                      (mlib_u8 *)dp,
                                                                      dsize);
        }
        else mlib_v_ImbgeChbnnelInsert_U8_34L((mlib_u8 *)sp, strides,
                                                                   (mlib_u8 *)dp, strided,
                                                                   width, height);
        }
      else {

      mlib_v_ImbgeChbnnelInsert_U8((mlib_u8 *)sp, strides,
                                                     (mlib_u8 *)dp, strided,
                                                     chbnnels, chbnneld,
                                                     width, height,
                                                     ncmbsk);
      }
  }
  brebk;

    cbse MLIB_SHORT:
      if (chbnnels == 1) {
        switch (chbnneld) {
          cbse 2:
            if (((flbgs & A8D1) == 0) &&
                ((flbgd & A8D1) == 0) &&
                ((dsize & X4)   == 0)) {
              mlib_v_ImbgeChbnnelInsert_S16_12_A8D1X4((mlib_s16 *)sp,
                                                                                    (mlib_s16 *)dp,
                                                                                      dsize,
                                                                                      ncmbsk);
            }
            else if (((flbgs & A8D2X4) == 0) &&
               ((flbgd & A8D2X4) == 0)) {
              mlib_v_ImbgeChbnnelInsert_S16_12_A8D2X4((mlib_s16 *)sp, strides,
                                                                              (mlib_s16 *)dp, strided,
                                                                              width, height,
                                                                                      ncmbsk);
            }
            else if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
               ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
             mlib_v_ImbgeChbnnelInsert_S16_12_D1((mlib_s16 *)sp,
                                                                           (mlib_s16 *)dp,
                                                                          dsize,
                                                                                  ncmbsk);
            }
            else {
              mlib_v_ImbgeChbnnelInsert_S16_12((mlib_s16 *)sp, strides,
                                                                       (mlib_s16 *)dp, strided,
                                                                       width, height,
                                                                       ncmbsk);
            }
            brebk;

          cbse 3:
            if (((flbgs & A8D1) == 0) &&
                ((flbgd & A8D1) == 0) &&
                ((dsize & X4)   == 0)) {
              mlib_v_ImbgeChbnnelInsert_S16_13_A8D1X4((mlib_s16 *)sp,
                                                                              (mlib_s16 *)dp,
                                                                                      dsize,
                                                                                      ncmbsk);
            }
            else if (((flbgs & A8D2X4) == 0) &&
               ((flbgd & A8D2X4) == 0)) {
              mlib_v_ImbgeChbnnelInsert_S16_13_A8D2X4((mlib_s16 *)sp, strides,
                                                                              (mlib_s16 *)dp, strided,
                                                                              width, height,
                                                                                      ncmbsk);
            }
            else if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
               ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
                mlib_v_ImbgeChbnnelInsert_S16_13_D1((mlib_s16 *)sp,
                                                                                  (mlib_s16 *)dp,
                                                                                  dsize,
                                                                                  ncmbsk);
            }
            else {
              mlib_v_ImbgeChbnnelInsert_S16_13((mlib_s16 *)sp, strides,
                                                                       (mlib_s16 *)dp, strided,
                                                                       width, height,
                                                                       ncmbsk);
            }
            brebk;

          cbse 4:
            if (((flbgs & A8D1) == 0) &&
                ((flbgd & A8D1) == 0) &&
                ((dsize & X4)   == 0)) {
              mlib_v_ImbgeChbnnelInsert_S16_14_A8D1X4((mlib_s16 *)sp,
                                                                                    (mlib_s16 *)dp,
                                                      dsize,
                                                      ncmbsk);
            }
            else if (((flbgs & A8D2X4) == 0) &&
               ((flbgd & A8D2X4) == 0)) {
              mlib_v_ImbgeChbnnelInsert_S16_14_A8D2X4((mlib_s16 *)sp, strides,
                                                                              (mlib_s16 *)dp, strided,
                                                                              width, height,
                                                                                      ncmbsk);
            }
            else if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
               ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
              mlib_v_ImbgeChbnnelInsert_S16_14_D1((mlib_s16 *)sp,
                                                                          (mlib_s16 *)dp,
                                                                          dsize,
                                                                                  ncmbsk);
            }
            else {
              mlib_v_ImbgeChbnnelInsert_S16_14((mlib_s16 *)sp, strides,
                                                                       (mlib_s16 *)dp, strided,
                                                                       width, height,
                                                                       ncmbsk);
            }
            brebk;
          defbult:
            return MLIB_FAILURE;
        }
      }
      else if ((chbnnels == 3) && (chbnneld == 4) && (ncmbsk == 7)) {
        if (((flbgs & A8D1) == 0) &&
            ((flbgd & A8D1) == 0) &&
            ((dsize & X4)   == 0)) {
          mlib_v_ImbgeChbnnelInsert_S16_34R_A8D1X4((mlib_s16 *)sp,
                                                                           (mlib_s16 *)dp,
                                                                           dsize);
        }
        else if (((flbgs & A8D2X4) == 0) &&
           ((flbgd & A8D2X4) == 0)) {
          mlib_v_ImbgeChbnnelInsert_S16_34R_A8D2X4((mlib_s16 *)sp, strides,
                                                                           (mlib_s16 *)dp, strided,
                                                                           width, height);
        }
        else if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
           ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
          mlib_v_ImbgeChbnnelInsert_S16_34R_D1((mlib_s16 *)sp,
                                                                       (mlib_s16 *)dp,
                                                                       dsize);
        }
        else {
          mlib_v_ImbgeChbnnelInsert_S16_34R((mlib_s16 *)sp, strides,
                                                                    (mlib_s16 *)dp, strided,
                                                                     width, height);
        }
      }
      else if ((chbnnels == 3) && (chbnneld == 4) && (ncmbsk == 14)) {
        if (((flbgs & A8D1) == 0) &&
            ((flbgd & A8D1) == 0) &&
            ((dsize & X4)   == 0)) {
          mlib_v_ImbgeChbnnelInsert_S16_34L_A8D1X4((mlib_s16 *)sp,
                                                                           (mlib_s16 *)dp,
                                                                           dsize);
        }
        else if (((flbgs & A8D2X4) == 0) &&
           ((flbgd & A8D2X4) == 0)) {
          mlib_v_ImbgeChbnnelInsert_S16_34L_A8D2X4((mlib_s16 *)sp, strides,
                                                                           (mlib_s16 *)dp, strided,
                                                                           width, height);
        }
        else if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
           ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
          mlib_v_ImbgeChbnnelInsert_S16_34L_D1((mlib_s16 *)sp,
                                                                       (mlib_s16 *)dp,
                                                                       dsize);
        }
        else {
          mlib_v_ImbgeChbnnelInsert_S16_34L((mlib_s16 *)sp, strides,
                                                                    (mlib_s16 *)dp, strided,
                                                                    width, height);
        }
      }
      else {
        mlib_v_ImbgeChbnnelInsert_S16((mlib_s16 *)sp, strides,
                                                              (mlib_s16 *)dp, strided,
                                                              chbnnels,  chbnneld,
                                                              width, height,
                                                              ncmbsk);
      }
      brebk;

    cbse MLIB_INT:
        mlib_v_ImbgeChbnnelInsert_S32((mlib_s32 *)sp, strides,
                                      (mlib_s32 *)dp, strided,
                                      chbnnels, chbnneld,
                                      width, height,
                                      ncmbsk);
        brebk;

    cbse MLIB_FLOAT:
        mlib_v_ImbgeChbnnelInsert_S32((mlib_s32 *)sp, strides,
                                      (mlib_s32 *)dp, strided,
                                      chbnnels, chbnneld,
                                      width, height,
                                      ncmbsk);
        brebk;


    cbse MLIB_DOUBLE:
        mlib_v_ImbgeChbnnelInsert_D64((mlib_d64 *)sp, strides,
                                      (mlib_d64 *)dp, strided,
                                      chbnnels, chbnneld,
                                      width, height,
                                      ncmbsk);
        brebk;


    cbse MLIB_BIT:
    defbult:
        return MLIB_FAILURE;    /* MLIB_BIT is not supported here */
  }

  return MLIB_SUCCESS;
}
/***************************************************************/
