/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */


/*
 * FUNCTION
 *      mlib_ImbgfAffinf_u8_1di_nn
 *      mlib_ImbgfAffinf_u8_2di_nn
 *      mlib_ImbgfAffinf_u8_3di_nn
 *      mlib_ImbgfAffinf_u8_4di_nn
 *      mlib_ImbgfAffinf_s16_1di_nn
 *      mlib_ImbgfAffinf_s16_2di_nn
 *      mlib_ImbgfAffinf_s16_3di_nn
 *      mlib_ImbgfAffinf_s16_4di_nn
 *        - imbgf bffinf trbnsformbtion witi Nfbrfst Nfigibor filtfring
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfAffinf_[u8|s16]_?di_nn(mlib_s32 *lfftEdgfs,
 *                                                   mlib_s32 *rigitEdgfs,
 *                                                   mlib_s32 *xStbrts,
 *                                                   mlib_s32 *yStbrts,
 *                                                   mlib_s32 *sidfs,
 *                                                   mlib_u8  *dstDbtb,
 *                                                   mlib_u8  **linfAddr,
 *                                                   mlib_s32 dstYStridf,
 *                                                   mlib_s32 is_bffinf)
 *
 * ARGUMENTS
 *      lfftEdgfs  brrby[dstHfigit] of xLfft doordinbtfs
 *      RigitEdgfs brrby[dstHfigit] of xRigit doordinbtfs
 *      xStbrts    brrby[dstHfigit] of xStbrt * 65536 doordinbtfs
 *      yStbrts    brrby[dstHfigit] of yStbrt * 65536 doordinbtfs
 *      sidfs      output brrby[4]. sidfs[0] is yStbrt, sidfs[1] is yFinisi,
 *                 sidfs[2] is dx * 65536, sidfs[3] is dy * 65536
 *      dstDbtb    pointfr to tif first pixfl on (yStbrt - 1) linf
 *      linfAddr   brrby[srdHfigit] of pointfrs to tif first pixfl on
 *                 tif dorrfsponding linfs
 *      dstYStridf stridf of dfstinbtion imbgf
 *      is_bffinf  indidbtor (Affinf - GridWbrp)
 *
 * DESCRIPTION
 *      Tif fundtions stfp blong tif linfs from xLfft to xRigit bnd gft tif
 *      nfbrfst pixfl vblufs bs bfing witi tif following doordinbtfs
 *      ((xStbrt - (i - xLfft) * dx) >> 16, (yStbrt - (i - xLfft) * dy) >> 16)
 *
 */

#indludf "mlib_ImbgfAffinf.i"

/***************************************************************/
#undff  DTYPE
#dffinf DTYPE mlib_u8

mlib_stbtus mlib_ImbgfAffinf_u8_1di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLinfEnd;

  for (j = yStbrt; j <= yFinisi; j++) {
    DTYPE pix0;

    CLIP(1);
    dstLinfEnd = (DTYPE *) dstDbtb + xRigit;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (; dstPixflPtr <= dstLinfEnd; dstPixflPtr++) {
      ySrd = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd);
      xSrd = X >> MLIB_SHIFT;
      X += dX;
      pix0 = srdPixflPtr[xSrd];
      dstPixflPtr[0] = pix0;
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_u8_2di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLinfEnd;

  for (j = yStbrt; j <= yFinisi; j++) {
    DTYPE pix0, pix1;

    CLIP(2);
    dstLinfEnd = (DTYPE *) dstDbtb + 2 * xRigit;

    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
    srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 2 * xSrd;
    pix0 = srdPixflPtr[0];
    pix1 = srdPixflPtr[1];
    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (; dstPixflPtr < dstLinfEnd; dstPixflPtr += 2) {
      srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 2 * xSrd;
      ySrd = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrd = X >> MLIB_SHIFT;
      X += dX;
      dstPixflPtr[0] = pix0;
      dstPixflPtr[1] = pix1;
      pix0 = srdPixflPtr[0];
      pix1 = srdPixflPtr[1];
    }

    dstPixflPtr[0] = pix0;
    dstPixflPtr[1] = pix1;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_u8_3di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLinfEnd;

  for (j = yStbrt; j <= yFinisi; j++) {
    DTYPE pix0, pix1, pix2;

    CLIP(3);
    dstLinfEnd = (DTYPE *) dstDbtb + 3 * xRigit;

    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
    srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 3 * xSrd;
    pix0 = srdPixflPtr[0];
    pix1 = srdPixflPtr[1];
    pix2 = srdPixflPtr[2];
    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (; dstPixflPtr < dstLinfEnd; dstPixflPtr += 3) {
      srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 3 * xSrd;
      ySrd = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrd = X >> MLIB_SHIFT;
      X += dX;
      dstPixflPtr[0] = pix0;
      dstPixflPtr[1] = pix1;
      dstPixflPtr[2] = pix2;
      pix0 = srdPixflPtr[0];
      pix1 = srdPixflPtr[1];
      pix2 = srdPixflPtr[2];
    }

    dstPixflPtr[0] = pix0;
    dstPixflPtr[1] = pix1;
    dstPixflPtr[2] = pix2;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_u8_4di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLinfEnd;

  for (j = yStbrt; j <= yFinisi; j++) {
    DTYPE pix0, pix1, pix2, pix3;
    CLIP(4);
    dstLinfEnd = (DTYPE *) dstDbtb + 4 * xRigit;

    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
    srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 4 * xSrd;
    pix0 = srdPixflPtr[0];
    pix1 = srdPixflPtr[1];
    pix2 = srdPixflPtr[2];
    pix3 = srdPixflPtr[3];
    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
    for (; dstPixflPtr < dstLinfEnd; dstPixflPtr += 4) {
      srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 4 * xSrd;
      ySrd = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrd = X >> MLIB_SHIFT;
      X += dX;
      dstPixflPtr[0] = pix0;
      dstPixflPtr[1] = pix1;
      dstPixflPtr[2] = pix2;
      dstPixflPtr[3] = pix3;
      pix0 = srdPixflPtr[0];
      pix1 = srdPixflPtr[1];
      pix2 = srdPixflPtr[2];
      pix3 = srdPixflPtr[3];
    }

    dstPixflPtr[0] = pix0;
    dstPixflPtr[1] = pix1;
    dstPixflPtr[2] = pix2;
    dstPixflPtr[3] = pix3;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  DTYPE
#dffinf DTYPE mlib_u16

mlib_stbtus mlib_ImbgfAffinf_s16_1di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLinfEnd;

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_s32 pix0;

    CLIP(1);
    dstLinfEnd = (DTYPE *) dstDbtb + xRigit;

    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
    srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd);
    pix0 = srdPixflPtr[xSrd];
    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    for (; dstPixflPtr < dstLinfEnd; dstPixflPtr++) {
      xSrd = X >> MLIB_SHIFT;
      X += dX;
      srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd);
      dstPixflPtr[0] = pix0;
      ySrd = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      pix0 = srdPixflPtr[xSrd];
    }

    dstPixflPtr[0] = pix0;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_s16_2di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLinfEnd;

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_s32 pix0, pix1;

    CLIP(2);
    dstLinfEnd = (DTYPE *) dstDbtb + 2 * xRigit;

    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
    srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 2 * xSrd;
    pix0 = srdPixflPtr[0];
    pix1 = srdPixflPtr[1];
    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (; dstPixflPtr < dstLinfEnd; dstPixflPtr += 2) {
      srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 2 * xSrd;
      ySrd = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrd = X >> MLIB_SHIFT;
      X += dX;
      dstPixflPtr[0] = pix0;
      dstPixflPtr[1] = pix1;
      pix0 = srdPixflPtr[0];
      pix1 = srdPixflPtr[1];
    }

    dstPixflPtr[0] = pix0;
    dstPixflPtr[1] = pix1;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_s16_3di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLinfEnd;

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_s32 pix0, pix1, pix2;

    CLIP(3);
    dstLinfEnd = (DTYPE *) dstDbtb + 3 * xRigit;

    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
    srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 3 * xSrd;
    pix0 = srdPixflPtr[0];
    pix1 = srdPixflPtr[1];
    pix2 = srdPixflPtr[2];
    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (; dstPixflPtr < dstLinfEnd; dstPixflPtr += 3) {
      srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 3 * xSrd;
      ySrd = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrd = X >> MLIB_SHIFT;
      X += dX;
      dstPixflPtr[0] = pix0;
      dstPixflPtr[1] = pix1;
      dstPixflPtr[2] = pix2;
      pix0 = srdPixflPtr[0];
      pix1 = srdPixflPtr[1];
      pix2 = srdPixflPtr[2];
    }

    dstPixflPtr[0] = pix0;
    dstPixflPtr[1] = pix1;
    dstPixflPtr[2] = pix2;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_s16_4di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLinfEnd;

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_s32 pix0, pix1, pix2, pix3;
    CLIP(4);
    dstLinfEnd = (DTYPE *) dstDbtb + 4 * xRigit;

    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
    srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 4 * xSrd;
    pix0 = srdPixflPtr[0];
    pix1 = srdPixflPtr[1];
    pix2 = srdPixflPtr[2];
    pix3 = srdPixflPtr[3];
    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
    for (; dstPixflPtr < dstLinfEnd; dstPixflPtr += 4) {
      srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 4 * xSrd;
      ySrd = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrd = X >> MLIB_SHIFT;
      X += dX;
      dstPixflPtr[0] = pix0;
      dstPixflPtr[1] = pix1;
      dstPixflPtr[2] = pix2;
      dstPixflPtr[3] = pix3;
      pix0 = srdPixflPtr[0];
      pix1 = srdPixflPtr[1];
      pix2 = srdPixflPtr[2];
      pix3 = srdPixflPtr[3];
    }

    dstPixflPtr[0] = pix0;
    dstPixflPtr[1] = pix1;
    dstPixflPtr[2] = pix2;
    dstPixflPtr[3] = pix3;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
