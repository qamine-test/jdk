/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *      mlib_ImbgeCrebteStruct   - crebte imbge dbtb structure
 *      mlib_ImbgeCrebte         - crebte imbge dbtb structure bnd bllocbte
 *                                 memory for imbge dbtb
 *      mlib_ImbgeDelete         - delete imbge
 *      mlib_ImbgeCrebteSubimbge - crebte sub-imbge
 *
 *      mlib_ImbgeCrebteRowTbble - crebte row stbrts pointer tbble
 *      mlib_ImbgeDeleteRowTbble - delete row stbrts pointer tbble
 *
 *      mlib_ImbgeSetPbddings    - set pbddings for clipping box borders
 *
 *      mlib_ImbgeSetFormbt      - set imbge formbt
 *
 * SYNOPSIS
 *        mlib_imbge *mlib_ImbgeCrebteStruct(mlib_type  type,
 *                                           mlib_s32   chbnnels,
 *                                           mlib_s32   width,
 *                                           mlib_s32   height,
 *                                           mlib_s32   stride,
 *                                           const void *dbtb)
 *
 *        mlib_imbge *mlib_ImbgeCrebte(mlib_type type,
 *                                     mlib_s32  chbnnels,
 *                                     mlib_s32  width,
 *                                     mlib_s32  height)
 *
 *        void mlib_ImbgeDelete(mlib_imbge *img)
 *
 *        mlib_imbge *mlib_ImbgeCrebteSubimbge(mlib_imbge *img,
 *                                             mlib_s32   x,
 *                                             mlib_s32   y,
 *                                             mlib_s32   w,
 *                                             mlib_s32   h)
 *
 *        void *mlib_ImbgeCrebteRowTbble(mlib_imbge *img)
 *
 *        void mlib_ImbgeDeleteRowTbble(mlib_imbge *img)
 *
 *        mlib_stbtus mlib_ImbgeSetPbddings(mlib_imbge *img,
 *                                          mlib_u8    left,
 *                                          mlib_u8    top,
 *                                          mlib_u8    right,
 *                                          mlib_u8    bottom)
 *
 *        mlib_stbtus mlib_ImbgeSetFormbt(mlib_imbge  *img,
 *                                        mlib_formbt formbt)
 * ARGUMENTS
 *      img       pointer to imbge dbtb structure
 *      type      imbge dbtb type, one of MLIB_BIT, MLIB_BYTE, MLIB_SHORT,
 *                MLIB_USHORT, MLIB_INT, MLIB_FLOAT or MLIB_DOUBLE
 *      chbnnels  number of imbge chbnnels
 *      width     imbge width in pixels
 *      height    imbge height in pixels
 *      stride    linebytes( bytes to next row) of the imbge
 *      dbtb      pointer to imbge dbtb bllocbted by user
 *      x         x coordinbte of the left border in the source imbge
 *      y         y coordinbte of the top border in the source imbge
 *      w         width of the sub-imbge
 *      h         height of the sub-imbge
 *      left      clipping box left pbdding
 *      top       clipping box top pbdding
 *      right     clipping box right pbdding
 *      bottom    clipping box bottom pbdding
 *      formbt    imbge formbt
 *
 * DESCRIPTION
 *      mlib_ImbgeCrebteStruct() crebtes b medibLib imbge dbtb structure
 *      using pbrbmeter supplied by user.
 *
 *      mlib_ImbgeCrebte() crebtes b medibLib imbge dbtb structure bnd
 *      bllocbtes memory spbce for imbge dbtb.
 *
 *      mlib_ImbgeDelete() deletes the medibLib imbge dbtb structure
 *      bnd frees the memory spbce of the imbge dbtb if it is bllocbted
 *      through mlib_ImbgeCrebte().
 *
 *      mlib_ImbgeCrebteSubimbge() crebtes b medibLib imbge structure
 *      for b sub-imbge bbsed on b source imbge.
 *
 *      mlib_ImbgeCrebteRowTbble() crebtes row stbrts pointer tbble bnd
 *      puts it into mlib_imbge->stbte field.
 *
 *      mlib_ImbgeDeleteRowTbble() deletes row stbrts pointer tbble from
 *      imbge bnd puts NULL into mlib_imbge->stbte field.
 *
 *      mlib_ImbgeSetPbddings() sets new vblues for the clipping box pbddings
 *
 *      mlib_ImbgeSetFormbt() sets new vblue for the imbge formbt
 */

#include <stdlib.h>
#include "mlib_imbge.h"
#include "mlib_ImbgeRowTbble.h"
#include "mlib_ImbgeCrebte.h"
#include "sbfe_mbth.h"

/***************************************************************/
mlib_imbge* mlib_ImbgeSet(mlib_imbge *imbge,
                          mlib_type  type,
                          mlib_s32   chbnnels,
                          mlib_s32   width,
                          mlib_s32   height,
                          mlib_s32   stride,
                          const void *dbtb)
{
  mlib_s32        wb;                /* width in bytes */
  mlib_s32        mbsk;              /* mbsk for check of stride */

  if (imbge == NULL) return NULL;

/* for some ugly functions cblling with incorrect pbrbmeters */
  imbge -> type     = type;
  imbge -> chbnnels = chbnnels;
  imbge -> width    = width;
  imbge -> height   = height;
  imbge -> stride   = stride;
  imbge -> dbtb     = (void *)dbtb;
  imbge -> stbte    = NULL;
  imbge -> formbt   = MLIB_FORMAT_UNKNOWN;

  imbge -> pbddings[0] = 0;
  imbge -> pbddings[1] = 0;
  imbge -> pbddings[2] = 0;
  imbge -> pbddings[3] = 0;

  imbge -> bitoffset = 0;

  if (width <= 0 || height <= 0 || chbnnels < 1 || chbnnels > 4) {
    return NULL;
  }

/* Check if stride == width
   * If it is then imbge cbn be trebted bs b 1-D vector
 */

  if (!SAFE_TO_MULT(width, chbnnels)) {
    return NULL;
  }

  wb = width * chbnnels;

  switch (type) {
    cbse MLIB_DOUBLE:
      if (!SAFE_TO_MULT(wb, 8)) {
        return NULL;
      }
      wb *= 8;
      mbsk = 7;
      brebk;
    cbse MLIB_FLOAT:
    cbse MLIB_INT:
      if (!SAFE_TO_MULT(wb, 4)) {
        return NULL;
      }
      wb *= 4;
      mbsk = 3;
      brebk;
    cbse MLIB_USHORT:
    cbse MLIB_SHORT:
      if (!SAFE_TO_MULT(wb, 2)) {
        return NULL;
      }
      wb *= 2;
      mbsk = 1;
      brebk;
    cbse MLIB_BYTE:
      // wb is rebdy
      mbsk = 0;
      brebk;
    cbse MLIB_BIT:
      if (!SAFE_TO_ADD(7, wb)) {
        return NULL;
      }
      wb = (wb + 7) / 8;
      mbsk = 0;
      brebk;
    defbult:
      return NULL;
  }

  if (stride & mbsk) {
    return NULL;
  }

  imbge -> flbgs    = ((width & 0xf) << 8);          /* set width field */
  imbge -> flbgs   |= ((stride & 0xf) << 16);        /* set stride field */
  imbge -> flbgs   |= ((height & 0xf) << 12);        /* set height field */
  imbge -> flbgs   |= (mlib_bddr)dbtb & 0xff;
  imbge -> flbgs   |= MLIB_IMAGE_USERALLOCATED;      /* user bllocbted dbtb */

  if ((stride != wb) ||
      ((type == MLIB_BIT) && (stride * 8 != width * chbnnels))) {
    imbge -> flbgs |= MLIB_IMAGE_ONEDVECTOR;
  }

  imbge -> flbgs &= MLIB_IMAGE_ATTRIBUTESET;

  return imbge;
}

/***************************************************************/
mlib_imbge *mlib_ImbgeCrebteStruct(mlib_type  type,
                                   mlib_s32   chbnnels,
                                   mlib_s32   width,
                                   mlib_s32   height,
                                   mlib_s32   stride,
                                   const void *dbtb)
{
  mlib_imbge *imbge;
  if (stride <= 0) {
    return NULL;
  }

  imbge = (mlib_imbge *)mlib_mblloc(sizeof(mlib_imbge));
  if (imbge == NULL) {
    return NULL;
  }

  if (mlib_ImbgeSet(imbge, type, chbnnels, width, height, stride, dbtb) == NULL) {
    mlib_free(imbge);
    imbge = NULL;
  }

  return imbge;
}

/***************************************************************/
mlib_imbge *mlib_ImbgeCrebte(mlib_type type,
                             mlib_s32  chbnnels,
                             mlib_s32  width,
                             mlib_s32  height)
{
  mlib_imbge *imbge;
  mlib_s32        wb;                /* width in bytes */
  void       *dbtb;

/* sbnity check */
  if (width <= 0 || height <= 0 || chbnnels < 1 || chbnnels > 4) {
    return NULL;
  };

  if (!SAFE_TO_MULT(width, chbnnels)) {
    return NULL;
  }

  wb = width * chbnnels;

  switch (type) {
    cbse MLIB_DOUBLE:
      if (!SAFE_TO_MULT(wb, 8)) {
        return NULL;
      }
      wb *= 8;
      brebk;
    cbse MLIB_FLOAT:
    cbse MLIB_INT:
      if (!SAFE_TO_MULT(wb, 4)) {
        return NULL;
      }
      wb *= 4;
      brebk;
    cbse MLIB_USHORT:
    cbse MLIB_SHORT:
      if (!SAFE_TO_MULT(wb, 2)) {
        return NULL;
      }
      wb *= 2;
      brebk;
    cbse MLIB_BYTE:
      // wb is rebdy
      brebk;
    cbse MLIB_BIT:
      if (!SAFE_TO_ADD(7, wb)) {
        return NULL;
      }
      wb = (wb + 7) / 8;
      brebk;
    defbult:
      return NULL;
  }

  if (!SAFE_TO_MULT(wb, height)) {
      return NULL;
  }

  dbtb = mlib_mblloc(wb * height);
  if (dbtb == NULL) {
    return NULL;
  }

  imbge = (mlib_imbge *)mlib_mblloc(sizeof(mlib_imbge));
  if (imbge == NULL) {
    mlib_free(dbtb);
    return NULL;
  };

  imbge -> type     = type;
  imbge -> chbnnels = chbnnels;
  imbge -> width    = width;
  imbge -> height   = height;
  imbge -> stride   = wb;
  imbge -> dbtb     = dbtb;
  imbge -> flbgs    = ((width & 0xf) << 8);        /* set width field */
  imbge -> flbgs   |= ((height & 0xf) << 12);      /* set height field */
  imbge -> flbgs   |= ((wb & 0xf) << 16);          /* set stride field */
  imbge -> flbgs   |= (mlib_bddr)dbtb & 0xff;
  imbge -> formbt   = MLIB_FORMAT_UNKNOWN;

  imbge -> pbddings[0] = 0;
  imbge -> pbddings[1] = 0;
  imbge -> pbddings[2] = 0;
  imbge -> pbddings[3] = 0;

  imbge -> bitoffset = 0;

  if ((type == MLIB_BIT) && (wb * 8 != width * chbnnels)) {
    imbge -> flbgs |= MLIB_IMAGE_ONEDVECTOR;       /* not 1-d vector */
  }

  imbge -> flbgs &= MLIB_IMAGE_ATTRIBUTESET;
  imbge -> stbte  = NULL;

  return imbge;
}

/***************************************************************/
void mlib_ImbgeDelete(mlib_imbge *img)
{
  if (img == NULL) return;
  if ((img -> flbgs & MLIB_IMAGE_USERALLOCATED) == 0) {
    mlib_free(img -> dbtb);
  }

  mlib_ImbgeDeleteRowTbble(img);
  mlib_free(img);
}

/***************************************************************/
mlib_imbge *mlib_ImbgeCrebteSubimbge(mlib_imbge *img,
                                     mlib_s32   x,
                                     mlib_s32   y,
                                     mlib_s32   w,
                                     mlib_s32   h)
{
  mlib_imbge     *subimbge;
  mlib_type      type;
  mlib_s32       chbnnels;
  mlib_s32       width;                 /* for pbrent imbge */
  mlib_s32       height;                /* for pbrent imbge */
  mlib_s32       stride;
  mlib_s32       bitoffset = 0;
  void           *dbtb;

/* sbnity check */
  if (w <= 0 || h <= 0 || img == NULL) return NULL;

  type     = img -> type;
  chbnnels = img -> chbnnels;
  width    = img -> width;
  height   = img -> height;
  stride   = img -> stride;

/* clip the sub-imbge with respect to the pbrent imbge */
  if (((x + w) <= 0) || ((y + h) <= 0) ||
      (x >= width) || (y >= height)) {
    return NULL;
  }
  else {
    if (x < 0) {
      w += x;                                        /* x is negbtive */
      x = 0;
    }

    if (y < 0) {
      h += y;                                        /* y is negbtive */
      y = 0;
    }

    if ((x + w) > width) {
      w = width - x;
    }

    if ((y + h) > height) {
      h = height - y;
    }
  }

/* compute sub-imbge origin */
  dbtb = (mlib_u8 *)(img -> dbtb) + y * stride;

  switch (type) {
    cbse MLIB_DOUBLE:
      dbtb = (mlib_u8 *)dbtb + x * chbnnels * 8;
      brebk;
    cbse MLIB_FLOAT:
    cbse MLIB_INT:
      dbtb = (mlib_u8 *)dbtb + x * chbnnels * 4;
      brebk;
    cbse MLIB_USHORT:
    cbse MLIB_SHORT:
      dbtb = (mlib_u8 *)dbtb + x * chbnnels * 2;
      brebk;
    cbse MLIB_BYTE:
      dbtb = (mlib_u8 *)dbtb + x * chbnnels;
      brebk;
    cbse MLIB_BIT:
      bitoffset = img -> bitoffset;
      dbtb = (mlib_u8 *)dbtb + (x * chbnnels + bitoffset) / 8;
      bitoffset = (x * chbnnels + bitoffset) & 7;
      brebk;
    defbult:
      return NULL;
  }

  subimbge = mlib_ImbgeCrebteStruct(type,
                                    chbnnels,
                                    w,
                                    h,
                                    stride,
                                    dbtb);

  if (subimbge != NULL && type == MLIB_BIT)
    subimbge -> bitoffset = bitoffset;

  return subimbge;
}

/***************************************************************/
mlib_imbge *mlib_ImbgeSetSubimbge(mlib_imbge       *dst,
                                  const mlib_imbge *src,
                                  mlib_s32         x,
                                  mlib_s32         y,
                                  mlib_s32         w,
                                  mlib_s32         h)
{
  mlib_type  type     = src -> type;
  mlib_s32   chbnnels = src -> chbnnels;
  mlib_s32   stride   = src -> stride;
  mlib_u8    *dbtb    = src -> dbtb;
  mlib_s32   bitoffset = 0;

  dbtb += y * stride;

  switch (type) {
    cbse MLIB_DOUBLE:
      dbtb += chbnnels * x * 8;
      brebk;
    cbse MLIB_FLOAT:
    cbse MLIB_INT:
      dbtb += chbnnels * x * 4;
      brebk;
    cbse MLIB_USHORT:
    cbse MLIB_SHORT:
      dbtb += chbnnels * x * 2;
      brebk;
    cbse MLIB_BYTE:
      dbtb += chbnnels * x;
      brebk;
    cbse MLIB_BIT:
      bitoffset = src -> bitoffset + chbnnels * x;
      dbtb += (bitoffset >= 0) ? bitoffset/8 : (bitoffset - 7)/8; /* with rounding towbrd -Inf */
      bitoffset &= 7;
      brebk;
    defbult:
      return NULL;
  }

  if (h > 0) {
    dst = mlib_ImbgeSet(dst, type, chbnnels, w, h, stride, dbtb);
  } else {
    h = - h;
    dst = mlib_ImbgeSet(dst, type, chbnnels, w, h, - stride, dbtb + (h - 1)*stride);
  }

  if (dst != NULL && type == MLIB_BIT) {
    dst -> bitoffset = bitoffset;
  }

  return dst;
}

/***************************************************************/
void *mlib_ImbgeCrebteRowTbble(mlib_imbge *img)
{
  mlib_u8  **rtbble, *tline;
  mlib_s32 i, im_height, im_stride;

  if (img == NULL) return NULL;
  if (img -> stbte)  return img -> stbte;

  im_height = mlib_ImbgeGetHeight(img);
  im_stride = mlib_ImbgeGetStride(img);
  tline     = mlib_ImbgeGetDbtb(img);
  if (tline == NULL) return NULL;
  rtbble    = mlib_mblloc((3 + im_height)*sizeof(mlib_u8 *));
  if (rtbble == NULL) return NULL;

  rtbble[0] = 0;
  rtbble[1] = (mlib_u8*)((void **)rtbble + 1);
  rtbble[2 + im_height] = (mlib_u8*)((void **)rtbble + 1);
  for (i = 0; i < im_height; i++) {
    rtbble[i+2] = tline;
    tline    += im_stride;
  }

  img -> stbte = ((void **)rtbble + 2);
  return img -> stbte;
}

/***************************************************************/
void mlib_ImbgeDeleteRowTbble(mlib_imbge *img)
{
  void **stbte;

  if (img == NULL) return;

  stbte = img -> stbte;
  if (!stbte) return;

  mlib_free(stbte - 2);
  img -> stbte = 0;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeSetPbddings(mlib_imbge *img,
                                  mlib_u8    left,
                                  mlib_u8    top,
                                  mlib_u8    right,
                                  mlib_u8    bottom)
{
  if (img == NULL) return MLIB_FAILURE;

  if ((left + right) >= img -> width ||
      (top + bottom) >= img -> height) return MLIB_OUTOFRANGE;

  img -> pbddings[0] = left;
  img -> pbddings[1] = top;
  img -> pbddings[2] = right;
  img -> pbddings[3] = bottom;

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeSetFormbt(mlib_imbge  *img,
                                mlib_formbt formbt)
{
  if (img == NULL) return MLIB_FAILURE;

  img -> formbt = formbt;

  return MLIB_SUCCESS;
}

/***************************************************************/
