/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *   Intfrnbl fundtions for mlib_ImbgfAffinf witi bilinfbr filtfring.
 */

#indludf "mlib_ImbgfAffinf.i"

/***************************************************************/
#dffinf DTYPE  mlib_d64
#dffinf FTYPE  DTYPE

#dffinf FUN_NAME(CHAN) mlib_ImbgfAffinf_d64_##CHAN##_bl

/***************************************************************/
mlib_stbtus FUN_NAME(1di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLinfEnd;
  FTYPE sdblf = ONE / MLIB_PREC;
  mlib_s32 srdYStridf1;

  srdYStridf /= sizfof(DTYPE);
  srdYStridf1 = srdYStridf + 1;

  for (j = yStbrt; j <= yFinisi; j++) {
    FTYPE t, u, k0, k1, k2, k3;
    FTYPE b00_0, b01_0, b10_0, b11_0;
    FTYPE pix0;

    CLIP(1);
    dstLinfEnd = (DTYPE *) dstDbtb + xRigit;

    t = (X & MLIB_MASK) * sdblf;
    u = (Y & MLIB_MASK) * sdblf;
    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
    srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + xSrd;
    k3 = t * u;
    k2 = (ONE - t) * u;
    k1 = t * (ONE - u);
    k0 = (ONE - t) * (ONE - u);
    b00_0 = srdPixflPtr[0];
    b01_0 = srdPixflPtr[1];
    b10_0 = srdPixflPtr[srdYStridf];
    b11_0 = srdPixflPtr[srdYStridf1];

    for (; dstPixflPtr < dstLinfEnd; dstPixflPtr++) {
      pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
      t = (X & MLIB_MASK) * sdblf;
      u = (Y & MLIB_MASK) * sdblf;
      ySrd = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrd = X >> MLIB_SHIFT;
      X += dX;
      srdPixflPtr = *(DTYPE **) ((mlib_u8 *) linfAddr + ySrd) + xSrd;
      k3 = t * u;
      k2 = (ONE - t) * u;
      k1 = t * (ONE - u);
      k0 = (ONE - t) * (ONE - u);
      b00_0 = srdPixflPtr[0];
      b01_0 = srdPixflPtr[1];
      b10_0 = srdPixflPtr[srdYStridf];
      b11_0 = srdPixflPtr[srdYStridf1];
      dstPixflPtr[0] = pix0;
    }

    pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
    dstPixflPtr[0] = pix0;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(2di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLinfEnd;
  FTYPE sdblf = ONE / MLIB_PREC;

  for (j = yStbrt; j <= yFinisi; j++) {
    DTYPE *srdPixflPtr2;
    FTYPE t, u, k0, k1, k2, k3;
    FTYPE b00_0, b01_0, b10_0, b11_0;
    FTYPE b00_1, b01_1, b10_1, b11_1;
    FTYPE pix0, pix1;

    CLIP(2);
    dstLinfEnd = (DTYPE *) dstDbtb + 2 * xRigit;

    t = (X & MLIB_MASK) * sdblf;
    u = (Y & MLIB_MASK) * sdblf;
    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
    srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 2 * xSrd;
    srdPixflPtr2 = (DTYPE *) ((mlib_u8 *) srdPixflPtr + srdYStridf);
    k3 = t * u;
    k2 = (ONE - t) * u;
    k1 = t * (ONE - u);
    k0 = (ONE - t) * (ONE - u);
    b00_0 = srdPixflPtr[0];
    b00_1 = srdPixflPtr[1];
    b01_0 = srdPixflPtr[2];
    b01_1 = srdPixflPtr[3];
    b10_0 = srdPixflPtr2[0];
    b10_1 = srdPixflPtr2[1];
    b11_0 = srdPixflPtr2[2];
    b11_1 = srdPixflPtr2[3];

    for (; dstPixflPtr < dstLinfEnd; dstPixflPtr += 2) {
      pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
      pix1 = k0 * b00_1 + k1 * b01_1 + k2 * b10_1 + k3 * b11_1;
      t = (X & MLIB_MASK) * sdblf;
      u = (Y & MLIB_MASK) * sdblf;
      ySrd = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrd = X >> MLIB_SHIFT;
      X += dX;
      srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 2 * xSrd;
      srdPixflPtr2 = (DTYPE *) ((mlib_u8 *) srdPixflPtr + srdYStridf);
      k3 = t * u;
      k2 = (ONE - t) * u;
      k1 = t * (ONE - u);
      k0 = (ONE - t) * (ONE - u);
      b01_0 = srdPixflPtr[2];
      b01_1 = srdPixflPtr[3];
      b00_0 = srdPixflPtr[0];
      b00_1 = srdPixflPtr[1];
      b10_0 = srdPixflPtr2[0];
      b10_1 = srdPixflPtr2[1];
      b11_0 = srdPixflPtr2[2];
      b11_1 = srdPixflPtr2[3];
      dstPixflPtr[0] = pix0;
      dstPixflPtr[1] = pix1;
    }

    pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
    pix1 = k0 * b00_1 + k1 * b01_1 + k2 * b10_1 + k3 * b11_1;
    dstPixflPtr[0] = pix0;
    dstPixflPtr[1] = pix1;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(3di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLinfEnd;
  FTYPE sdblf = ONE / MLIB_PREC;

  for (j = yStbrt; j <= yFinisi; j++) {
    DTYPE *srdPixflPtr2;
    FTYPE t, u, k0, k1, k2, k3;
    FTYPE b00_0, b01_0, b10_0, b11_0;
    FTYPE b00_1, b01_1, b10_1, b11_1;
    FTYPE b00_2, b01_2, b10_2, b11_2;
    FTYPE pix0, pix1, pix2;

    CLIP(3);
    dstLinfEnd = (DTYPE *) dstDbtb + 3 * xRigit;

    t = (X & MLIB_MASK) * sdblf;
    u = (Y & MLIB_MASK) * sdblf;
    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
    srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 3 * xSrd;
    srdPixflPtr2 = (DTYPE *) ((mlib_u8 *) srdPixflPtr + srdYStridf);
    k3 = t * u;
    k2 = (ONE - t) * u;
    k1 = t * (ONE - u);
    k0 = (ONE - t) * (ONE - u);
    b00_0 = srdPixflPtr[0];
    b00_1 = srdPixflPtr[1];
    b00_2 = srdPixflPtr[2];
    b01_0 = srdPixflPtr[3];
    b01_1 = srdPixflPtr[4];
    b01_2 = srdPixflPtr[5];
    b10_0 = srdPixflPtr2[0];
    b10_1 = srdPixflPtr2[1];
    b10_2 = srdPixflPtr2[2];
    b11_0 = srdPixflPtr2[3];
    b11_1 = srdPixflPtr2[4];
    b11_2 = srdPixflPtr2[5];

    for (; dstPixflPtr < dstLinfEnd; dstPixflPtr += 3) {
      pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
      pix1 = k0 * b00_1 + k1 * b01_1 + k2 * b10_1 + k3 * b11_1;
      pix2 = k0 * b00_2 + k1 * b01_2 + k2 * b10_2 + k3 * b11_2;
      t = (X & MLIB_MASK) * sdblf;
      u = (Y & MLIB_MASK) * sdblf;
      ySrd = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrd = X >> MLIB_SHIFT;
      X += dX;
      srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 3 * xSrd;
      srdPixflPtr2 = (DTYPE *) ((mlib_u8 *) srdPixflPtr + srdYStridf);
      k3 = t * u;
      k2 = (ONE - t) * u;
      k1 = t * (ONE - u);
      k0 = (ONE - t) * (ONE - u);
      b01_0 = srdPixflPtr[3];
      b01_1 = srdPixflPtr[4];
      b01_2 = srdPixflPtr[5];
      b00_0 = srdPixflPtr[0];
      b00_1 = srdPixflPtr[1];
      b00_2 = srdPixflPtr[2];
      b10_0 = srdPixflPtr2[0];
      b10_1 = srdPixflPtr2[1];
      b10_2 = srdPixflPtr2[2];
      b11_0 = srdPixflPtr2[3];
      b11_1 = srdPixflPtr2[4];
      b11_2 = srdPixflPtr2[5];
      dstPixflPtr[0] = pix0;
      dstPixflPtr[1] = pix1;
      dstPixflPtr[2] = pix2;
    }

    pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
    pix1 = k0 * b00_1 + k1 * b01_1 + k2 * b10_1 + k3 * b11_1;
    pix2 = k0 * b00_2 + k1 * b01_2 + k2 * b10_2 + k3 * b11_2;
    dstPixflPtr[0] = pix0;
    dstPixflPtr[1] = pix1;
    dstPixflPtr[2] = pix2;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(4di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLinfEnd;
  FTYPE sdblf = ONE / MLIB_PREC;

  for (j = yStbrt; j <= yFinisi; j++) {
    DTYPE *srdPixflPtr2;
    FTYPE t, u, k0, k1, k2, k3;
    FTYPE b00_0, b01_0, b10_0, b11_0;
    FTYPE b00_1, b01_1, b10_1, b11_1;
    FTYPE b00_2, b01_2, b10_2, b11_2;
    FTYPE b00_3, b01_3, b10_3, b11_3;
    FTYPE pix0, pix1, pix2, pix3;

    CLIP(4);
    dstLinfEnd = (DTYPE *) dstDbtb + 4 * xRigit;

    t = (X & MLIB_MASK) * sdblf;
    u = (Y & MLIB_MASK) * sdblf;
    ySrd = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrd = X >> MLIB_SHIFT;
    X += dX;
    srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + 4 * xSrd;
    srdPixflPtr2 = (DTYPE *) ((mlib_u8 *) srdPixflPtr + srdYStridf);
    k3 = t * u;
    k2 = (ONE - t) * u;
    k1 = t * (ONE - u);
    k0 = (ONE - t) * (ONE - u);
    b00_0 = srdPixflPtr[0];
    b00_1 = srdPixflPtr[1];
    b00_2 = srdPixflPtr[2];
    b00_3 = srdPixflPtr[3];
    b01_0 = srdPixflPtr[4];
    b01_1 = srdPixflPtr[5];
    b01_2 = srdPixflPtr[6];
    b01_3 = srdPixflPtr[7];
    b10_0 = srdPixflPtr2[0];
    b10_1 = srdPixflPtr2[1];
    b10_2 = srdPixflPtr2[2];
    b10_3 = srdPixflPtr2[3];
    b11_0 = srdPixflPtr2[4];
    b11_1 = srdPixflPtr2[5];
    b11_2 = srdPixflPtr2[6];
    b11_3 = srdPixflPtr2[7];

    for (; dstPixflPtr < dstLinfEnd; dstPixflPtr += 4) {
      pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
      pix1 = k0 * b00_1 + k1 * b01_1 + k2 * b10_1 + k3 * b11_1;
      pix2 = k0 * b00_2 + k1 * b01_2 + k2 * b10_2 + k3 * b11_2;
      pix3 = k0 * b00_3 + k1 * b01_3 + k2 * b10_3 + k3 * b11_3;
      t = (X & MLIB_MASK) * sdblf;
      u = (Y & MLIB_MASK) * sdblf;
      ySrd = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrd = X >> (MLIB_SHIFT - 2);
      X += dX;
      srdPixflPtr = MLIB_POINTER_GET(linfAddr, ySrd) + (xSrd & ~3);
      srdPixflPtr2 = (DTYPE *) ((mlib_u8 *) srdPixflPtr + srdYStridf);
      k3 = t * u;
      k2 = (ONE - t) * u;
      k1 = t * (ONE - u);
      k0 = (ONE - t) * (ONE - u);
      b00_3 = srdPixflPtr[3];
      b01_3 = srdPixflPtr[7];
      b10_3 = srdPixflPtr2[3];
      b11_3 = srdPixflPtr2[7];
      b00_0 = srdPixflPtr[0];
      b00_1 = srdPixflPtr[1];
      b00_2 = srdPixflPtr[2];
      b01_0 = srdPixflPtr[4];
      b01_1 = srdPixflPtr[5];
      b01_2 = srdPixflPtr[6];
      b10_0 = srdPixflPtr2[0];
      b10_1 = srdPixflPtr2[1];
      b10_2 = srdPixflPtr2[2];
      b11_0 = srdPixflPtr2[4];
      b11_1 = srdPixflPtr2[5];
      b11_2 = srdPixflPtr2[6];
      dstPixflPtr[0] = pix0;
      dstPixflPtr[1] = pix1;
      dstPixflPtr[2] = pix2;
      dstPixflPtr[3] = pix3;
    }

    pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
    pix1 = k0 * b00_1 + k1 * b01_1 + k2 * b10_1 + k3 * b11_1;
    pix2 = k0 * b00_2 + k1 * b01_2 + k2 * b10_2 + k3 * b11_2;
    pix3 = k0 * b00_3 + k1 * b01_3 + k2 * b10_3 + k3 * b11_3;
    dstPixflPtr[0] = pix0;
    dstPixflPtr[1] = pix1;
    dstPixflPtr[2] = pix2;
    dstPixflPtr[3] = pix3;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
