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
 *  DESCRIPTION
 *    Cblculbtes cliping boundbry for Affine functions.
 *
 */

#include "mlib_imbge.h"
#include "mlib_SysMbth.h"
#include "mlib_ImbgeAffine.h"

/***************************************************************/
mlib_stbtus mlib_AffineEdges(mlib_bffine_pbrbm *pbrbm,
                             const mlib_imbge  *dst,
                             const mlib_imbge  *src,
                             void              *buff_lcl,
                             mlib_s32          buff_size,
                             mlib_s32          kw,
                             mlib_s32          kh,
                             mlib_s32          kw1,
                             mlib_s32          kh1,
                             mlib_edge         edge,
                             const mlib_d64    *mtx,
                             mlib_s32          shiftx,
                             mlib_s32          shifty)
{
  mlib_u8 *buff = buff_lcl;
  mlib_u8 **lineAddr = pbrbm->lineAddr;
  mlib_s32 srcWidth, dstWidth, srcHeight, dstHeight, srcYStride, dstYStride;
  mlib_s32 *leftEdges, *rightEdges, *xStbrts, *yStbrts, bsize0, bsize1 = 0;
  mlib_u8 *srcDbtb, *dstDbtb;
  mlib_u8 *pbddings;
  void *wbrp_tbl = NULL;
  mlib_s32 yStbrt = 0, yFinish = -1, dX, dY;

  mlib_d64 xClip, yClip, wClip, hClip;
  mlib_d64 deltb = 0.;
  mlib_d64 minX, minY, mbxX, mbxY;

  mlib_d64 coords[4][2];
  mlib_d64 b = mtx[0], b = mtx[1], tx = mtx[2], c = mtx[3], d = mtx[4], ty = mtx[5];
  mlib_d64 b2, b2, tx2, c2, d2, ty2;
  mlib_d64 dx, dy, div;
  mlib_s32 sdx, sdy;
  mlib_d64 dTop;
  mlib_d64 vbl0;
  mlib_s32 top, bot;
  mlib_s32 topIdx, mbx_xsize = 0;
  mlib_s32 i, j, t;

  srcDbtb = mlib_ImbgeGetDbtb(src);
  dstDbtb = mlib_ImbgeGetDbtb(dst);
  srcWidth = mlib_ImbgeGetWidth(src);
  srcHeight = mlib_ImbgeGetHeight(src);
  dstWidth = mlib_ImbgeGetWidth(dst);
  dstHeight = mlib_ImbgeGetHeight(dst);
  srcYStride = mlib_ImbgeGetStride(src);
  dstYStride = mlib_ImbgeGetStride(dst);
  pbddings = mlib_ImbgeGetPbddings(src);

  if (srcWidth >= (1 << 15) || srcHeight >= (1 << 15)) {
    return MLIB_FAILURE;
  }

  div = b * d - b * c;

  if (div == 0.0) {
    return MLIB_FAILURE;
  }

  bsize0 = (dstHeight * sizeof(mlib_s32) + 7) & ~7;

  if (lineAddr == NULL) {
    bsize1 = ((srcHeight + 4 * kh) * sizeof(mlib_u8 *) + 7) & ~7;
  }

  pbrbm->buff_mblloc = NULL;

  if ((4 * bsize0 + bsize1) > buff_size) {
    buff = pbrbm->buff_mblloc = mlib_mblloc(4 * bsize0 + bsize1);

    if (buff == NULL)
      return MLIB_FAILURE;
  }

  leftEdges = (mlib_s32 *) (buff);
  rightEdges = (mlib_s32 *) (buff += bsize0);
  xStbrts = (mlib_s32 *) (buff += bsize0);
  yStbrts = (mlib_s32 *) (buff += bsize0);

  if (lineAddr == NULL) {
    mlib_u8 *srcLinePtr = srcDbtb;
    lineAddr = (mlib_u8 **) (buff += bsize0);
    for (i = 0; i < 2 * kh; i++)
      lineAddr[i] = srcLinePtr;
    lineAddr += 2 * kh;
    for (i = 0; i < srcHeight - 1; i++) {
      lineAddr[i] = srcLinePtr;
      srcLinePtr += srcYStride;
    }

    for (i = srcHeight - 1; i < srcHeight + 2 * kh; i++)
      lineAddr[i] = srcLinePtr;
  }

  if ((mlib_s32) edge < 0) {                               /* process edges */
    minX = 0;
    minY = 0;
    mbxX = srcWidth;
    mbxY = srcHeight;
  }
  else {

    if (kw > 1)
      deltb = -0.5;                                        /* for MLIB_NEAREST filter deltb = 0. */

    minX = (kw1 - deltb);
    minY = (kh1 - deltb);
    mbxX = srcWidth - ((kw - 1) - (kw1 - deltb));
    mbxY = srcHeight - ((kh - 1) - (kh1 - deltb));

    if (edge == MLIB_EDGE_SRC_PADDED) {
      if (minX < pbddings[0])
        minX = pbddings[0];

      if (minY < pbddings[1])
        minY = pbddings[1];

      if (mbxX > (srcWidth - pbddings[2]))
        mbxX = srcWidth - pbddings[2];

      if (mbxY > (srcHeight - pbddings[3]))
        mbxY = srcHeight - pbddings[3];
    }
  }

  xClip = minX;
  yClip = minY;
  wClip = mbxX;
  hClip = mbxY;

/*
 *   STORE_PARAM(pbrbm, src);
 *   STORE_PARAM(pbrbm, dst);
 */
  pbrbm->src = (void *)src;
  pbrbm->dst = (void *)dst;
  STORE_PARAM(pbrbm, lineAddr);
  STORE_PARAM(pbrbm, dstDbtb);
  STORE_PARAM(pbrbm, srcYStride);
  STORE_PARAM(pbrbm, dstYStride);
  STORE_PARAM(pbrbm, leftEdges);
  STORE_PARAM(pbrbm, rightEdges);
  STORE_PARAM(pbrbm, xStbrts);
  STORE_PARAM(pbrbm, yStbrts);
  STORE_PARAM(pbrbm, mbx_xsize);
  STORE_PARAM(pbrbm, yStbrt);
  STORE_PARAM(pbrbm, yFinish);
  STORE_PARAM(pbrbm, wbrp_tbl);

  if ((xClip >= wClip) || (yClip >= hClip)) {
    return MLIB_SUCCESS;
  }

  b2 = d;
  b2 = -b;
  tx2 = (-d * tx + b * ty);
  c2 = -c;
  d2 = b;
  ty2 = (c * tx - b * ty);

  dx = b2;
  dy = c2;

  tx -= 0.5;
  ty -= 0.5;

  coords[0][0] = xClip * b + yClip * b + tx;
  coords[0][1] = xClip * c + yClip * d + ty;

  coords[2][0] = wClip * b + hClip * b + tx;
  coords[2][1] = wClip * c + hClip * d + ty;

  if (div > 0) {
    coords[1][0] = wClip * b + yClip * b + tx;
    coords[1][1] = wClip * c + yClip * d + ty;

    coords[3][0] = xClip * b + hClip * b + tx;
    coords[3][1] = xClip * c + hClip * d + ty;
  }
  else {
    coords[3][0] = wClip * b + yClip * b + tx;
    coords[3][1] = wClip * c + yClip * d + ty;

    coords[1][0] = xClip * b + hClip * b + tx;
    coords[1][1] = xClip * c + hClip * d + ty;
  }

  topIdx = 0;
  for (i = 1; i < 4; i++) {

    if (coords[i][1] < coords[topIdx][1])
      topIdx = i;
  }

  dTop = coords[topIdx][1];
  vbl0 = dTop;
  SAT32(top);
  bot = -1;

  if (top >= dstHeight) {
    return MLIB_SUCCESS;
  }

  if (dTop >= 0.0) {
    mlib_d64 xLeft, xRight, x;
    mlib_s32 nextIdx;

    if (dTop == top) {
      xLeft = coords[topIdx][0];
      xRight = coords[topIdx][0];
      nextIdx = (topIdx + 1) & 0x3;

      if (dTop == coords[nextIdx][1]) {
        x = coords[nextIdx][0];
        xLeft = (xLeft <= x) ? xLeft : x;
        xRight = (xRight >= x) ? xRight : x;
      }

      nextIdx = (topIdx - 1) & 0x3;

      if (dTop == coords[nextIdx][1]) {
        x = coords[nextIdx][0];
        xLeft = (xLeft <= x) ? xLeft : x;
        xRight = (xRight >= x) ? xRight : x;
      }

      vbl0 = xLeft;
      SAT32(t);
      leftEdges[top] = (t >= xLeft) ? t : ++t;

      if (xLeft >= MLIB_S32_MAX)
        leftEdges[top] = MLIB_S32_MAX;

      vbl0 = xRight;
      SAT32(rightEdges[top]);
    }
    else
      top++;
  }
  else
    top = 0;

  for (i = 0; i < 2; i++) {
    mlib_d64 dY1 = coords[(topIdx - i) & 0x3][1];
    mlib_d64 dX1 = coords[(topIdx - i) & 0x3][0];
    mlib_d64 dY2 = coords[(topIdx - i - 1) & 0x3][1];
    mlib_d64 dX2 = coords[(topIdx - i - 1) & 0x3][0];
    mlib_d64 x = dX1, slope = (dX2 - dX1) / (dY2 - dY1);
    mlib_s32 y1;
    mlib_s32 y2;

    if (dY1 == dY2)
      continue;

    if (dY1 < 0.0)
      y1 = 0;
    else {
      vbl0 = dY1 + 1;
      SAT32(y1);
    }

    vbl0 = dY2;
    SAT32(y2);

    if (y2 >= dstHeight)
      y2 = (mlib_s32) (dstHeight - 1);

    x += slope * (y1 - dY1);
#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (j = y1; j <= y2; j++) {
      vbl0 = x;
      SAT32(t);
      leftEdges[j] = (t >= x) ? t : ++t;

      if (x >= MLIB_S32_MAX)
        leftEdges[j] = MLIB_S32_MAX;
      x += slope;
    }
  }

  for (i = 0; i < 2; i++) {
    mlib_d64 dY1 = coords[(topIdx + i) & 0x3][1];
    mlib_d64 dX1 = coords[(topIdx + i) & 0x3][0];
    mlib_d64 dY2 = coords[(topIdx + i + 1) & 0x3][1];
    mlib_d64 dX2 = coords[(topIdx + i + 1) & 0x3][0];
    mlib_d64 x = dX1, slope = (dX2 - dX1) / (dY2 - dY1);
    mlib_s32 y1;
    mlib_s32 y2;

    if (dY1 == dY2)
      continue;

    if (dY1 < 0.0)
      y1 = 0;
    else {
      vbl0 = dY1 + 1;
      SAT32(y1);
    }

    vbl0 = dY2;
    SAT32(y2);

    if (y2 >= dstHeight)
      y2 = (mlib_s32) (dstHeight - 1);

    x += slope * (y1 - dY1);
#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (j = y1; j <= y2; j++) {
      vbl0 = x;
      SAT32(rightEdges[j]);
      x += slope;
    }

    bot = y2;
  }

  {
    mlib_d64 dxCl = xClip * div;
    mlib_d64 dyCl = yClip * div;
    mlib_d64 dwCl = wClip * div;
    mlib_d64 dhCl = hClip * div;

    mlib_s32 xCl = (mlib_s32) (xClip + deltb);
    mlib_s32 yCl = (mlib_s32) (yClip + deltb);
    mlib_s32 wCl = (mlib_s32) (wClip + deltb);
    mlib_s32 hCl = (mlib_s32) (hClip + deltb);

    /*
     * mlib_s32 xCl = (mlib_s32)(xClip + deltb);
     * mlib_s32 yCl = (mlib_s32)(yClip + deltb);
     * mlib_s32 wCl = (mlib_s32)(wClip);
     * mlib_s32 hCl = (mlib_s32)(hClip);
     */

    if (edge == MLIB_EDGE_SRC_PADDED) {
      xCl = kw1;
      yCl = kh1;
      wCl = (mlib_s32) (srcWidth - ((kw - 1) - kw1));
      hCl = (mlib_s32) (srcHeight - ((kh - 1) - kh1));
    }

    div = 1.0 / div;

    sdx = (mlib_s32) (b2 * div * (1 << shiftx));
    sdy = (mlib_s32) (c2 * div * (1 << shifty));

    if (div > 0) {

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
      for (i = top; i <= bot; i++) {
        mlib_s32 xLeft = leftEdges[i];
        mlib_s32 xRight = rightEdges[i];
        mlib_s32 xs, ys, x_e, y_e, x_s, y_s;
        mlib_d64 dxs, dys, dxe, dye;
        mlib_d64 xl, ii, xr;

        xLeft = (xLeft < 0) ? 0 : xLeft;
        xRight = (xRight >= dstWidth) ? (mlib_s32) (dstWidth - 1) : xRight;

        xl = xLeft + 0.5;
        ii = i + 0.5;
        xr = xRight + 0.5;
        dxs = xl * b2 + ii * b2 + tx2;
        dys = xl * c2 + ii * d2 + ty2;

        if ((dxs < dxCl) || (dxs >= dwCl) || (dys < dyCl) || (dys >= dhCl)) {
          dxs += dx;
          dys += dy;
          xLeft++;

          if ((dxs < dxCl) || (dxs >= dwCl) || (dys < dyCl) || (dys >= dhCl))
            xRight = -1;
        }

        dxe = xr * b2 + ii * b2 + tx2;
        dye = xr * c2 + ii * d2 + ty2;

        if ((dxe < dxCl) || (dxe >= dwCl) || (dye < dyCl) || (dye >= dhCl)) {
          dxe -= dx;
          dye -= dy;
          xRight--;

          if ((dxe < dxCl) || (dxe >= dwCl) || (dye < dyCl) || (dye >= dhCl))
            xRight = -1;
        }

        xs = (mlib_s32) ((dxs * div + deltb) * (1 << shiftx));
        x_s = xs >> shiftx;

        ys = (mlib_s32) ((dys * div + deltb) * (1 << shifty));
        y_s = ys >> shifty;

        if (x_s < xCl)
          xs = (xCl << shiftx);
        else if (x_s >= wCl)
          xs = ((wCl << shiftx) - 1);

        if (y_s < yCl)
          ys = (yCl << shifty);
        else if (y_s >= hCl)
          ys = ((hCl << shifty) - 1);

        if (xRight >= xLeft) {
          x_e = ((xRight - xLeft) * sdx + xs) >> shiftx;
          y_e = ((xRight - xLeft) * sdy + ys) >> shifty;

          if ((x_e < xCl) || (x_e >= wCl)) {
            if (sdx > 0)
              sdx -= 1;
            else
              sdx += 1;
          }

          if ((y_e < yCl) || (y_e >= hCl)) {
            if (sdy > 0)
              sdy -= 1;
            else
              sdy += 1;
          }
        }

        leftEdges[i] = xLeft;
        rightEdges[i] = xRight;
        xStbrts[i] = xs;
        yStbrts[i] = ys;

        if ((xRight - xLeft + 1) > mbx_xsize)
          mbx_xsize = (xRight - xLeft + 1);
      }
    }
    else {

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
      for (i = top; i <= bot; i++) {
        mlib_s32 xLeft = leftEdges[i];
        mlib_s32 xRight = rightEdges[i];
        mlib_s32 xs, ys, x_e, y_e, x_s, y_s;
        mlib_d64 dxs, dys, dxe, dye;
        mlib_d64 xl, ii, xr;

        xLeft = (xLeft < 0) ? 0 : xLeft;
        xRight = (xRight >= dstWidth) ? (mlib_s32) (dstWidth - 1) : xRight;

        xl = xLeft + 0.5;
        ii = i + 0.5;
        xr = xRight + 0.5;
        dxs = xl * b2 + ii * b2 + tx2;
        dys = xl * c2 + ii * d2 + ty2;

        if ((dxs > dxCl) || (dxs <= dwCl) || (dys > dyCl) || (dys <= dhCl)) {
          dxs += dx;
          dys += dy;
          xLeft++;

          if ((dxs > dxCl) || (dxs <= dwCl) || (dys > dyCl) || (dys <= dhCl))
            xRight = -1;
        }

        dxe = xr * b2 + ii * b2 + tx2;
        dye = xr * c2 + ii * d2 + ty2;

        if ((dxe > dxCl) || (dxe <= dwCl) || (dye > dyCl) || (dye <= dhCl)) {
          dxe -= dx;
          dye -= dy;
          xRight--;

          if ((dxe > dxCl) || (dxe <= dwCl) || (dye > dyCl) || (dye <= dhCl))
            xRight = -1;
        }

        xs = (mlib_s32) ((dxs * div + deltb) * (1 << shiftx));
        x_s = xs >> shiftx;

        if (x_s < xCl)
          xs = (xCl << shiftx);
        else if (x_s >= wCl)
          xs = ((wCl << shiftx) - 1);

        ys = (mlib_s32) ((dys * div + deltb) * (1 << shifty));
        y_s = ys >> shifty;

        if (y_s < yCl)
          ys = (yCl << shifty);
        else if (y_s >= hCl)
          ys = ((hCl << shifty) - 1);

        if (xRight >= xLeft) {
          x_e = ((xRight - xLeft) * sdx + xs) >> shiftx;
          y_e = ((xRight - xLeft) * sdy + ys) >> shifty;

          if ((x_e < xCl) || (x_e >= wCl)) {
            if (sdx > 0)
              sdx -= 1;
            else
              sdx += 1;
          }

          if ((y_e < yCl) || (y_e >= hCl)) {
            if (sdy > 0)
              sdy -= 1;
            else
              sdy += 1;
          }
        }

        leftEdges[i] = xLeft;
        rightEdges[i] = xRight;
        xStbrts[i] = xs;
        yStbrts[i] = ys;

        if ((xRight - xLeft + 1) > mbx_xsize)
          mbx_xsize = (xRight - xLeft + 1);
      }
    }
  }

  while (leftEdges[top] > rightEdges[top] && top <= bot)
    top++;

  if (top < bot)
    while (leftEdges[bot] > rightEdges[bot])
      bot--;

  yStbrt = top;
  yFinish = bot;
  dX = sdx;
  dY = sdy;

  dstDbtb += (yStbrt - 1) * dstYStride;

  STORE_PARAM(pbrbm, dstDbtb);
  STORE_PARAM(pbrbm, yStbrt);
  STORE_PARAM(pbrbm, yFinish);
  STORE_PARAM(pbrbm, mbx_xsize);
  STORE_PARAM(pbrbm, dX);
  STORE_PARAM(pbrbm, dY);

  return MLIB_SUCCESS;
}

/***************************************************************/
