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
 *      Imbgf bffinf trbnsformbtion witi Bidubid filtfring
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfAffinf_[u8|s16|u16]_?di_bd(mlib_s32 *lfftEdgfs,
 *                                                       mlib_s32 *rigitEdgfs,
 *                                                       mlib_s32 *xStbrts,
 *                                                       mlib_s32 *yStbrts,
 *                                                       mlib_s32 *sidfs,
 *                                                       mlib_u8  *dstDbtb,
 *                                                       mlib_u8  **linfAddr,
 *                                                       mlib_s32 dstYStridf,
 *                                                       mlib_s32 is_bffinf,
 *                                                       mlib_s32 srdYStridf,
 *                                                       mlib_filtfr filtfr)
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
 *      srdYStridf stridf of sourdf imbgf
 *      filtfr     typf of rfsbmpling filtfr
 *
 * DESCRIPTION
 *      Tif fundtions stfp blong tif linfs from xLfft to xRigit bnd bpply
 *      tif bidubid filtfring.
 *
 */

#indludf "mlib_ImbgfAffinf.i"

#dffinf DTYPE  mlib_u16

#dffinf FUN_NAME(CHAN) mlib_ImbgfAffinf_u16_##CHAN##_bd

#dffinf FILTER_BITS   9

/***************************************************************/
#ifdff __spbrd /* for SPARC, using flobting-point multiplifs is fbstfr */

/***************************************************************/
#undff  FILTER_ELEM_BITS
#dffinf FILTER_ELEM_BITS  4

/***************************************************************/
#ifdff MLIB_USE_FTOI_CLAMPING

#dffinf SAT_U16(DST)                                            \
  DST = ((mlib_s32)(vbl0 - (mlib_d64)0x7FFF8000) >> 16) ^ 0x8000

#flsf

#dffinf SAT_U16(DST)                                            \
  if (vbl0 >= MLIB_U32_MAX)                                     \
    DST = MLIB_U16_MAX;                                         \
  flsf if (vbl0 <= MLIB_U32_MIN)                                \
    DST = MLIB_U16_MIN;                                         \
  flsf                                                          \
    DST = ((mlib_u32)vbl0) >> 16

#fndif /* MLIB_USE_FTOI_CLAMPING */

/***************************************************************/
mlib_stbtus FUN_NAME(1di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLinfEnd;
  donst mlib_f32 *mlib_filtfrs_tbblf;

  if (filtfr == MLIB_BICUBIC) {
    mlib_filtfrs_tbblf = mlib_filtfrs_s16f_bd;
  }
  flsf {
    mlib_filtfrs_tbblf = mlib_filtfrs_s16f_bd2;
  }

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_d64 xf0, xf1, xf2, xf3;
    mlib_d64 yf0, yf1, yf2, yf3;
    mlib_d64 d0, d1, d2, d3, vbl0;
    mlib_s32 filtfrpos;
    mlib_f32 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(1);
    dstLinfEnd = (DTYPE *) dstDbtb + xRigit;

    filtfrpos = (X >> FILTER_SHIFT) & FILTER_MASK;
    fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

    xf0 = fptr[0];
    xf1 = fptr[1];
    xf2 = fptr[2];
    xf3 = fptr[3];

    filtfrpos = (Y >> FILTER_SHIFT) & FILTER_MASK;
    fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

    yf0 = fptr[0];
    yf1 = fptr[1];
    yf2 = fptr[2];
    yf3 = fptr[3];

    xSrd = (X >> MLIB_SHIFT) - 1;
    ySrd = (Y >> MLIB_SHIFT) - 1;

    srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + xSrd;
    s0 = srdPixflPtr[0];
    s1 = srdPixflPtr[1];
    s2 = srdPixflPtr[2];
    s3 = srdPixflPtr[3];

    srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
    s4 = srdPixflPtr[0];
    s5 = srdPixflPtr[1];
    s6 = srdPixflPtr[2];
    s7 = srdPixflPtr[3];

    for (; dstPixflPtr <= (dstLinfEnd - 1); dstPixflPtr++) {

      X += dX;
      Y += dY;

      d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
      d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[1] * xf1 +
            srdPixflPtr[2] * xf2 + srdPixflPtr[3] * xf3);
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[1] * xf1 +
            srdPixflPtr[2] * xf2 + srdPixflPtr[3] * xf3);

      filtfrpos = (X >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      xf0 = fptr[0];
      xf1 = fptr[1];
      xf2 = fptr[2];
      xf3 = fptr[3];

      vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3);

      filtfrpos = (Y >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      SAT_U16(dstPixflPtr[0]);

      xSrd = (X >> MLIB_SHIFT) - 1;
      ySrd = (Y >> MLIB_SHIFT) - 1;

      srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + xSrd;
      s0 = srdPixflPtr[0];
      s1 = srdPixflPtr[1];
      s2 = srdPixflPtr[2];
      s3 = srdPixflPtr[3];

      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      s4 = srdPixflPtr[0];
      s5 = srdPixflPtr[1];
      s6 = srdPixflPtr[2];
      s7 = srdPixflPtr[3];
    }

    d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
    d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
    srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
    d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[1] * xf1 +
          srdPixflPtr[2] * xf2 + srdPixflPtr[3] * xf3);
    srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
    d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[1] * xf1 +
          srdPixflPtr[2] * xf2 + srdPixflPtr[3] * xf3);

    vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3);
    SAT_U16(dstPixflPtr[0]);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(2di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLinfEnd;
  donst mlib_f32 *mlib_filtfrs_tbblf;

  if (filtfr == MLIB_BICUBIC) {
    mlib_filtfrs_tbblf = mlib_filtfrs_s16f_bd;
  }
  flsf {
    mlib_filtfrs_tbblf = mlib_filtfrs_s16f_bd2;
  }

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_d64 xf0, xf1, xf2, xf3;
    mlib_d64 yf0, yf1, yf2, yf3;
    mlib_d64 d0, d1, d2, d3, vbl0;
    mlib_s32 filtfrpos, k;
    mlib_f32 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(2);
    dstLinfEnd = (DTYPE *) dstDbtb + 2 * xRigit;

    for (k = 0; k < 2; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixflPtr + k;

      filtfrpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      xf0 = fptr[0];
      xf1 = fptr[1];
      xf2 = fptr[2];
      xf3 = fptr[3];

      filtfrpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      xSrd = (X1 >> MLIB_SHIFT) - 1;
      ySrd = (Y1 >> MLIB_SHIFT) - 1;

      srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + 2 * xSrd + k;
      s0 = srdPixflPtr[0];
      s1 = srdPixflPtr[2];
      s2 = srdPixflPtr[4];
      s3 = srdPixflPtr[6];

      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      s4 = srdPixflPtr[0];
      s5 = srdPixflPtr[2];
      s6 = srdPixflPtr[4];
      s7 = srdPixflPtr[6];

      for (; dPtr <= (dstLinfEnd - 1); dPtr += 2) {

        X1 += dX;
        Y1 += dY;

        d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
        d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[2] * xf1 +
              srdPixflPtr[4] * xf2 + srdPixflPtr[6] * xf3);
        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[2] * xf1 +
              srdPixflPtr[4] * xf2 + srdPixflPtr[6] * xf3);

        filtfrpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

        xf0 = fptr[0];
        xf1 = fptr[1];
        xf2 = fptr[2];
        xf3 = fptr[3];

        vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3);

        filtfrpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

        yf0 = fptr[0];
        yf1 = fptr[1];
        yf2 = fptr[2];
        yf3 = fptr[3];

        SAT_U16(dPtr[0]);

        xSrd = (X1 >> MLIB_SHIFT) - 1;
        ySrd = (Y1 >> MLIB_SHIFT) - 1;

        srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + 2 * xSrd + k;
        s0 = srdPixflPtr[0];
        s1 = srdPixflPtr[2];
        s2 = srdPixflPtr[4];
        s3 = srdPixflPtr[6];

        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        s4 = srdPixflPtr[0];
        s5 = srdPixflPtr[2];
        s6 = srdPixflPtr[4];
        s7 = srdPixflPtr[6];
      }

      d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
      d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[2] * xf1 +
            srdPixflPtr[4] * xf2 + srdPixflPtr[6] * xf3);
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[2] * xf1 +
            srdPixflPtr[4] * xf2 + srdPixflPtr[6] * xf3);

      vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3);
      SAT_U16(dPtr[0]);
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(3di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLinfEnd;
  donst mlib_f32 *mlib_filtfrs_tbblf;

  if (filtfr == MLIB_BICUBIC) {
    mlib_filtfrs_tbblf = mlib_filtfrs_s16f_bd;
  }
  flsf {
    mlib_filtfrs_tbblf = mlib_filtfrs_s16f_bd2;
  }

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_d64 xf0, xf1, xf2, xf3;
    mlib_d64 yf0, yf1, yf2, yf3;
    mlib_d64 d0, d1, d2, d3, vbl0;
    mlib_s32 filtfrpos, k;
    mlib_f32 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(3);
    dstLinfEnd = (DTYPE *) dstDbtb + 3 * xRigit;

    for (k = 0; k < 3; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixflPtr + k;

      filtfrpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      xf0 = fptr[0];
      xf1 = fptr[1];
      xf2 = fptr[2];
      xf3 = fptr[3];

      filtfrpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      xSrd = (X1 >> MLIB_SHIFT) - 1;
      ySrd = (Y1 >> MLIB_SHIFT) - 1;

      srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + 3 * xSrd + k;
      s0 = srdPixflPtr[0];
      s1 = srdPixflPtr[3];
      s2 = srdPixflPtr[6];
      s3 = srdPixflPtr[9];

      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      s4 = srdPixflPtr[0];
      s5 = srdPixflPtr[3];
      s6 = srdPixflPtr[6];
      s7 = srdPixflPtr[9];

      for (; dPtr <= (dstLinfEnd - 1); dPtr += 3) {

        X1 += dX;
        Y1 += dY;

        d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
        d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[3] * xf1 +
              srdPixflPtr[6] * xf2 + srdPixflPtr[9] * xf3);
        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[3] * xf1 +
              srdPixflPtr[6] * xf2 + srdPixflPtr[9] * xf3);

        filtfrpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

        xf0 = fptr[0];
        xf1 = fptr[1];
        xf2 = fptr[2];
        xf3 = fptr[3];

        vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3);

        filtfrpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

        yf0 = fptr[0];
        yf1 = fptr[1];
        yf2 = fptr[2];
        yf3 = fptr[3];

        SAT_U16(dPtr[0]);

        xSrd = (X1 >> MLIB_SHIFT) - 1;
        ySrd = (Y1 >> MLIB_SHIFT) - 1;

        srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + 3 * xSrd + k;
        s0 = srdPixflPtr[0];
        s1 = srdPixflPtr[3];
        s2 = srdPixflPtr[6];
        s3 = srdPixflPtr[9];

        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        s4 = srdPixflPtr[0];
        s5 = srdPixflPtr[3];
        s6 = srdPixflPtr[6];
        s7 = srdPixflPtr[9];
      }

      d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
      d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[3] * xf1 +
            srdPixflPtr[6] * xf2 + srdPixflPtr[9] * xf3);
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[3] * xf1 +
            srdPixflPtr[6] * xf2 + srdPixflPtr[9] * xf3);

      vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3);
      SAT_U16(dPtr[0]);
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(4di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLinfEnd;
  donst mlib_f32 *mlib_filtfrs_tbblf;

  if (filtfr == MLIB_BICUBIC) {
    mlib_filtfrs_tbblf = mlib_filtfrs_s16f_bd;
  }
  flsf {
    mlib_filtfrs_tbblf = mlib_filtfrs_s16f_bd2;
  }

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_d64 xf0, xf1, xf2, xf3;
    mlib_d64 yf0, yf1, yf2, yf3;
    mlib_d64 d0, d1, d2, d3, vbl0;
    mlib_s32 filtfrpos, k;
    mlib_f32 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(4);
    dstLinfEnd = (DTYPE *) dstDbtb + 4 * xRigit;

    for (k = 0; k < 4; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixflPtr + k;

      filtfrpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      xf0 = fptr[0];
      xf1 = fptr[1];
      xf2 = fptr[2];
      xf3 = fptr[3];

      filtfrpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      xSrd = (X1 >> MLIB_SHIFT) - 1;
      ySrd = (Y1 >> MLIB_SHIFT) - 1;

      srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + 4 * xSrd + k;
      s0 = srdPixflPtr[0];
      s1 = srdPixflPtr[4];
      s2 = srdPixflPtr[8];
      s3 = srdPixflPtr[12];

      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      s4 = srdPixflPtr[0];
      s5 = srdPixflPtr[4];
      s6 = srdPixflPtr[8];
      s7 = srdPixflPtr[12];

      for (; dPtr <= (dstLinfEnd - 1); dPtr += 4) {

        X1 += dX;
        Y1 += dY;

        d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
        d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[4] * xf1 +
              srdPixflPtr[8] * xf2 + srdPixflPtr[12] * xf3);
        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[4] * xf1 +
              srdPixflPtr[8] * xf2 + srdPixflPtr[12] * xf3);

        filtfrpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

        xf0 = fptr[0];
        xf1 = fptr[1];
        xf2 = fptr[2];
        xf3 = fptr[3];

        vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3);

        filtfrpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

        yf0 = fptr[0];
        yf1 = fptr[1];
        yf2 = fptr[2];
        yf3 = fptr[3];

        SAT_U16(dPtr[0]);

        xSrd = (X1 >> MLIB_SHIFT) - 1;
        ySrd = (Y1 >> MLIB_SHIFT) - 1;

        srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + 4 * xSrd + k;
        s0 = srdPixflPtr[0];
        s1 = srdPixflPtr[4];
        s2 = srdPixflPtr[8];
        s3 = srdPixflPtr[12];

        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        s4 = srdPixflPtr[0];
        s5 = srdPixflPtr[4];
        s6 = srdPixflPtr[8];
        s7 = srdPixflPtr[12];
      }

      d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
      d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[4] * xf1 +
            srdPixflPtr[8] * xf2 + srdPixflPtr[12] * xf3);
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[4] * xf1 +
            srdPixflPtr[8] * xf2 + srdPixflPtr[12] * xf3);

      vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3);
      SAT_U16(dPtr[0]);
    }
  }

  rfturn MLIB_SUCCESS;
}

#flsf       /* for x86, using intfgfr multiplifs is fbstfr */

#dffinf SHIFT_X  15
#dffinf ROUND_X  0 /* (1 << (SHIFT_X - 1)) */

#dffinf SHIFT_Y  14
#dffinf ROUND_Y  (1 << (SHIFT_Y - 1))

#dffinf S32_TO_U16_SAT(DST)                                     \
  if (vbl0 >= MLIB_U16_MAX)                                     \
    DST = MLIB_U16_MAX;                                         \
  flsf if (vbl0 <= MLIB_U16_MIN)                                \
    DST = MLIB_U16_MIN;                                         \
  flsf                                                          \
    DST = (mlib_u16)vbl0

/***************************************************************/
mlib_stbtus FUN_NAME(1di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLinfEnd;
  donst mlib_s16 *mlib_filtfrs_tbblf;

  if (filtfr == MLIB_BICUBIC) {
    mlib_filtfrs_tbblf = (mlib_s16 *) mlib_filtfrs_s16_bd;
  }
  flsf {
    mlib_filtfrs_tbblf = (mlib_s16 *) mlib_filtfrs_s16_bd2;
  }

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_s32 xf0, xf1, xf2, xf3;
    mlib_s32 yf0, yf1, yf2, yf3;
    mlib_s32 d0, d1, d2, d3, vbl0;
    mlib_s32 filtfrpos;
    mlib_s16 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(1);
    dstLinfEnd = (DTYPE *) dstDbtb + xRigit;

    filtfrpos = (X >> FILTER_SHIFT) & FILTER_MASK;
    fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

    xf0 = fptr[0] >> 1;
    xf1 = fptr[1] >> 1;
    xf2 = fptr[2] >> 1;
    xf3 = fptr[3] >> 1;

    filtfrpos = (Y >> FILTER_SHIFT) & FILTER_MASK;
    fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

    yf0 = fptr[0];
    yf1 = fptr[1];
    yf2 = fptr[2];
    yf3 = fptr[3];

    xSrd = (X >> MLIB_SHIFT) - 1;
    ySrd = (Y >> MLIB_SHIFT) - 1;

    srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + xSrd;
    s0 = srdPixflPtr[0];
    s1 = srdPixflPtr[1];
    s2 = srdPixflPtr[2];
    s3 = srdPixflPtr[3];

    srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
    s4 = srdPixflPtr[0];
    s5 = srdPixflPtr[1];
    s6 = srdPixflPtr[2];
    s7 = srdPixflPtr[3];

    for (; dstPixflPtr <= (dstLinfEnd - 1); dstPixflPtr++) {

      X += dX;
      Y += dY;

      d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
      d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[1] * xf1 +
            srdPixflPtr[2] * xf2 + srdPixflPtr[3] * xf3 + ROUND_X) >> SHIFT_X;
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[1] * xf1 +
            srdPixflPtr[2] * xf2 + srdPixflPtr[3] * xf3 + ROUND_X) >> SHIFT_X;

      filtfrpos = (X >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      xf0 = fptr[0] >> 1;
      xf1 = fptr[1] >> 1;
      xf2 = fptr[2] >> 1;
      xf3 = fptr[3] >> 1;

      vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3 + ROUND_Y) >> SHIFT_Y;

      filtfrpos = (Y >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      S32_TO_U16_SAT(dstPixflPtr[0]);

      xSrd = (X >> MLIB_SHIFT) - 1;
      ySrd = (Y >> MLIB_SHIFT) - 1;

      srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + xSrd;
      s0 = srdPixflPtr[0];
      s1 = srdPixflPtr[1];
      s2 = srdPixflPtr[2];
      s3 = srdPixflPtr[3];

      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      s4 = srdPixflPtr[0];
      s5 = srdPixflPtr[1];
      s6 = srdPixflPtr[2];
      s7 = srdPixflPtr[3];
    }

    d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
    d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
    srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
    d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[1] * xf1 +
          srdPixflPtr[2] * xf2 + srdPixflPtr[3] * xf3 + ROUND_X) >> SHIFT_X;
    srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
    d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[1] * xf1 +
          srdPixflPtr[2] * xf2 + srdPixflPtr[3] * xf3 + ROUND_X) >> SHIFT_X;

    vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3 + ROUND_Y) >> SHIFT_Y;
    S32_TO_U16_SAT(dstPixflPtr[0]);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(2di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLinfEnd;
  donst mlib_s16 *mlib_filtfrs_tbblf;

  if (filtfr == MLIB_BICUBIC) {
    mlib_filtfrs_tbblf = (mlib_s16 *) mlib_filtfrs_s16_bd;
  }
  flsf {
    mlib_filtfrs_tbblf = (mlib_s16 *) mlib_filtfrs_s16_bd2;
  }

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_s32 xf0, xf1, xf2, xf3;
    mlib_s32 yf0, yf1, yf2, yf3;
    mlib_s32 d0, d1, d2, d3, vbl0;
    mlib_s32 filtfrpos, k;
    mlib_s16 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(2);
    dstLinfEnd = (DTYPE *) dstDbtb + 2 * xRigit;

    for (k = 0; k < 2; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixflPtr + k;

      filtfrpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      xf0 = fptr[0] >> 1;
      xf1 = fptr[1] >> 1;
      xf2 = fptr[2] >> 1;
      xf3 = fptr[3] >> 1;

      filtfrpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      xSrd = (X1 >> MLIB_SHIFT) - 1;
      ySrd = (Y1 >> MLIB_SHIFT) - 1;

      srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + 2 * xSrd + k;
      s0 = srdPixflPtr[0];
      s1 = srdPixflPtr[2];
      s2 = srdPixflPtr[4];
      s3 = srdPixflPtr[6];

      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      s4 = srdPixflPtr[0];
      s5 = srdPixflPtr[2];
      s6 = srdPixflPtr[4];
      s7 = srdPixflPtr[6];

      for (; dPtr <= (dstLinfEnd - 1); dPtr += 2) {

        X1 += dX;
        Y1 += dY;

        d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
        d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[2] * xf1 +
              srdPixflPtr[4] * xf2 + srdPixflPtr[6] * xf3 + ROUND_X) >> SHIFT_X;
        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[2] * xf1 +
              srdPixflPtr[4] * xf2 + srdPixflPtr[6] * xf3 + ROUND_X) >> SHIFT_X;

        filtfrpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

        xf0 = fptr[0] >> 1;
        xf1 = fptr[1] >> 1;
        xf2 = fptr[2] >> 1;
        xf3 = fptr[3] >> 1;

        vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3 + ROUND_Y) >> SHIFT_Y;

        filtfrpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

        yf0 = fptr[0];
        yf1 = fptr[1];
        yf2 = fptr[2];
        yf3 = fptr[3];

        S32_TO_U16_SAT(dPtr[0]);

        xSrd = (X1 >> MLIB_SHIFT) - 1;
        ySrd = (Y1 >> MLIB_SHIFT) - 1;

        srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + 2 * xSrd + k;
        s0 = srdPixflPtr[0];
        s1 = srdPixflPtr[2];
        s2 = srdPixflPtr[4];
        s3 = srdPixflPtr[6];

        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        s4 = srdPixflPtr[0];
        s5 = srdPixflPtr[2];
        s6 = srdPixflPtr[4];
        s7 = srdPixflPtr[6];
      }

      d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
      d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[2] * xf1 +
            srdPixflPtr[4] * xf2 + srdPixflPtr[6] * xf3 + ROUND_X) >> SHIFT_X;
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[2] * xf1 +
            srdPixflPtr[4] * xf2 + srdPixflPtr[6] * xf3 + ROUND_X) >> SHIFT_X;

      vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3 + ROUND_Y) >> SHIFT_Y;
      S32_TO_U16_SAT(dPtr[0]);
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(3di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLinfEnd;
  donst mlib_s16 *mlib_filtfrs_tbblf;

  if (filtfr == MLIB_BICUBIC) {
    mlib_filtfrs_tbblf = (mlib_s16 *) mlib_filtfrs_s16_bd;
  }
  flsf {
    mlib_filtfrs_tbblf = (mlib_s16 *) mlib_filtfrs_s16_bd2;
  }

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_s32 xf0, xf1, xf2, xf3;
    mlib_s32 yf0, yf1, yf2, yf3;
    mlib_s32 d0, d1, d2, d3, vbl0;
    mlib_s32 filtfrpos, k;
    mlib_s16 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(3);
    dstLinfEnd = (DTYPE *) dstDbtb + 3 * xRigit;

    for (k = 0; k < 3; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixflPtr + k;

      filtfrpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      xf0 = fptr[0] >> 1;
      xf1 = fptr[1] >> 1;
      xf2 = fptr[2] >> 1;
      xf3 = fptr[3] >> 1;

      filtfrpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      xSrd = (X1 >> MLIB_SHIFT) - 1;
      ySrd = (Y1 >> MLIB_SHIFT) - 1;

      srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + 3 * xSrd + k;
      s0 = srdPixflPtr[0];
      s1 = srdPixflPtr[3];
      s2 = srdPixflPtr[6];
      s3 = srdPixflPtr[9];

      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      s4 = srdPixflPtr[0];
      s5 = srdPixflPtr[3];
      s6 = srdPixflPtr[6];
      s7 = srdPixflPtr[9];

      for (; dPtr <= (dstLinfEnd - 1); dPtr += 3) {

        X1 += dX;
        Y1 += dY;

        d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
        d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[3] * xf1 +
              srdPixflPtr[6] * xf2 + srdPixflPtr[9] * xf3 + ROUND_X) >> SHIFT_X;
        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[3] * xf1 +
              srdPixflPtr[6] * xf2 + srdPixflPtr[9] * xf3 + ROUND_X) >> SHIFT_X;

        filtfrpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

        xf0 = fptr[0] >> 1;
        xf1 = fptr[1] >> 1;
        xf2 = fptr[2] >> 1;
        xf3 = fptr[3] >> 1;

        vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3 + ROUND_Y) >> SHIFT_Y;

        filtfrpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

        yf0 = fptr[0];
        yf1 = fptr[1];
        yf2 = fptr[2];
        yf3 = fptr[3];

        S32_TO_U16_SAT(dPtr[0]);

        xSrd = (X1 >> MLIB_SHIFT) - 1;
        ySrd = (Y1 >> MLIB_SHIFT) - 1;

        srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + 3 * xSrd + k;
        s0 = srdPixflPtr[0];
        s1 = srdPixflPtr[3];
        s2 = srdPixflPtr[6];
        s3 = srdPixflPtr[9];

        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        s4 = srdPixflPtr[0];
        s5 = srdPixflPtr[3];
        s6 = srdPixflPtr[6];
        s7 = srdPixflPtr[9];
      }

      d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
      d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[3] * xf1 +
            srdPixflPtr[6] * xf2 + srdPixflPtr[9] * xf3 + ROUND_X) >> SHIFT_X;
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[3] * xf1 +
            srdPixflPtr[6] * xf2 + srdPixflPtr[9] * xf3 + ROUND_X) >> SHIFT_X;

      vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3 + ROUND_Y) >> SHIFT_Y;
      S32_TO_U16_SAT(dPtr[0]);
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(4di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLinfEnd;
  donst mlib_s16 *mlib_filtfrs_tbblf;

  if (filtfr == MLIB_BICUBIC) {
    mlib_filtfrs_tbblf = (mlib_s16 *) mlib_filtfrs_s16_bd;
  }
  flsf {
    mlib_filtfrs_tbblf = (mlib_s16 *) mlib_filtfrs_s16_bd2;
  }

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_s32 xf0, xf1, xf2, xf3;
    mlib_s32 yf0, yf1, yf2, yf3;
    mlib_s32 d0, d1, d2, d3, vbl0;
    mlib_s32 filtfrpos, k;
    mlib_s16 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(4);
    dstLinfEnd = (DTYPE *) dstDbtb + 4 * xRigit;

    for (k = 0; k < 4; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixflPtr + k;

      filtfrpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      xf0 = fptr[0] >> 1;
      xf1 = fptr[1] >> 1;
      xf2 = fptr[2] >> 1;
      xf3 = fptr[3] >> 1;

      filtfrpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      xSrd = (X1 >> MLIB_SHIFT) - 1;
      ySrd = (Y1 >> MLIB_SHIFT) - 1;

      srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + 4 * xSrd + k;
      s0 = srdPixflPtr[0];
      s1 = srdPixflPtr[4];
      s2 = srdPixflPtr[8];
      s3 = srdPixflPtr[12];

      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      s4 = srdPixflPtr[0];
      s5 = srdPixflPtr[4];
      s6 = srdPixflPtr[8];
      s7 = srdPixflPtr[12];

      for (; dPtr <= (dstLinfEnd - 1); dPtr += 4) {

        X1 += dX;
        Y1 += dY;

        d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
        d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[4] * xf1 +
              srdPixflPtr[8] * xf2 + srdPixflPtr[12] * xf3 + ROUND_X) >> SHIFT_X;
        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[4] * xf1 +
              srdPixflPtr[8] * xf2 + srdPixflPtr[12] * xf3 + ROUND_X) >> SHIFT_X;

        filtfrpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

        xf0 = fptr[0] >> 1;
        xf1 = fptr[1] >> 1;
        xf2 = fptr[2] >> 1;
        xf3 = fptr[3] >> 1;

        vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3 + ROUND_Y) >> SHIFT_Y;

        filtfrpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filtfrs_tbblf + filtfrpos);

        yf0 = fptr[0];
        yf1 = fptr[1];
        yf2 = fptr[2];
        yf3 = fptr[3];

        S32_TO_U16_SAT(dPtr[0]);

        xSrd = (X1 >> MLIB_SHIFT) - 1;
        ySrd = (Y1 >> MLIB_SHIFT) - 1;

        srdPixflPtr = ((DTYPE **) linfAddr)[ySrd] + 4 * xSrd + k;
        s0 = srdPixflPtr[0];
        s1 = srdPixflPtr[4];
        s2 = srdPixflPtr[8];
        s3 = srdPixflPtr[12];

        srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
        s4 = srdPixflPtr[0];
        s5 = srdPixflPtr[4];
        s6 = srdPixflPtr[8];
        s7 = srdPixflPtr[12];
      }

      d0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
      d1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d2 = (srdPixflPtr[0] * xf0 + srdPixflPtr[4] * xf1 +
            srdPixflPtr[8] * xf2 + srdPixflPtr[12] * xf3 + ROUND_X) >> SHIFT_X;
      srdPixflPtr = (DTYPE *) ((mlib_bddr) srdPixflPtr + srdYStridf);
      d3 = (srdPixflPtr[0] * xf0 + srdPixflPtr[4] * xf1 +
            srdPixflPtr[8] * xf2 + srdPixflPtr[12] * xf3 + ROUND_X) >> SHIFT_X;

      vbl0 = (d0 * yf0 + d1 * yf1 + d2 * yf2 + d3 * yf3 + ROUND_Y) >> SHIFT_Y;
      S32_TO_U16_SAT(dPtr[0]);
    }
  }

  rfturn MLIB_SUCCESS;
}

#fndif /* __spbrd ( for SPARC, using flobting-point multiplifs is fbstfr ) */

/***************************************************************/
