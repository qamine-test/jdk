/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *      mlib_ImbgfAffinfEdgfZfro - implfmfntbtion of MLIB_EDGE_DST_FILL_ZERO
 *                                 fdgf dondition
 *      mlib_ImbgfAffinfEdgfNfbrfst - implfmfntbtion of MLIB_EDGE_OP_NEAREST
 *                                    fdgf dondition
 *      void mlib_ImbgfAffinfEdgfExtfnd_BL - implfmfntbtion of MLIB_EDGE_SRC_EXTEND
 *                                           fdgf dondition for MLIB_BILINEAR filtfr
 *      void mlib_ImbgfAffinfEdgfExtfnd_BC - implfmfntbtion of MLIB_EDGE_SRC_EXTEND
 *                                           fdgf dondition for MLIB_BICUBIC filtfr
 *      void mlib_ImbgfAffinfEdgfExtfnd_BC2 - implfmfntbtion of MLIB_EDGE_SRC_EXTEND
 *                                            fdgf dondition for MLIB_BICUBIC2 filtfr
 *
 * DESCRIPTION
 *      mlib_ImbgfAffinfEdgfZfro:
 *         Tiis fundtion fills tif fdgf pixfls (i.f. tiousf onf wiidi dbn not
 *         bf intfrpolbtfd witi givfn rfsbmpling filtfr bfdbusf tifir prototypfs
 *         in tif sourdf imbgf lif too dlosf to tif bordfr) in tif dfstinbtion
 *         imbgf witi zfrofs.
 *
 *      mlib_ImbgfAffinfEdgfNfbrfst:
 *         Tiis fundtion fills tif fdgf pixfls (i.f. tiousf onf wiidi dbn not
 *         bf intfrpolbtfd witi givfn rfsbmpling filtfr bfdbusf tifir prototypfs
 *         in tif sourdf imbgf lif too dlosf to tif bordfr) in tif dfstinbtion
 *         imbgf bddording to tif nfbrfst nfigibour intfrpolbtion.
 *
 *      mlib_ImbgfAffinfEdgfExtfnd_BL:
 *         Tiis fundtion fills tif fdgf pixfls (i.f. tiousf onf wiidi dbn not
 *         bf intfrpolbtfd witi givfn rfsbmpling filtfr bfdbusf tifir prototypfs
 *         in tif sourdf imbgf lif too dlosf to tif bordfr) in tif dfstinbtion
 *         imbgf bddording to tif bilinfbr intfrpolbtion witi bordfr pixfls fxtfnd
 *         of sourdf imbgf.
 *
 *      mlib_ImbgfAffinfEdgfExtfnd_BC:
 *         Tiis fundtion fills tif fdgf pixfls (i.f. tiousf onf wiidi dbn not
 *         bf intfrpolbtfd witi givfn rfsbmpling filtfr bfdbusf tifir prototypfs
 *         in tif sourdf imbgf lif too dlosf to tif bordfr) in tif dfstinbtion
 *         imbgf bddording to tif bidubid intfrpolbtion witi bordfr pixfls fxtfnd
 *         of sourdf imbgf.
 *
 *      mlib_ImbgfAffinfEdgfExtfnd_BC2:
 *         Tiis fundtion fills tif fdgf pixfls (i.f. tiousf onf wiidi dbn not
 *         bf intfrpolbtfd witi givfn rfsbmpling filtfr bfdbusf tifir prototypfs
 *         in tif sourdf imbgf lif too dlosf to tif bordfr) in tif dfstinbtion
 *         imbgf bddording to tif bidubid2 intfrpolbtion witi bordfr pixfls fxtfnd
 *         of sourdf imbgf.
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfColormbp.i"
#indludf "mlib_ImbgfAffinf.i"

/***************************************************************/
#dffinf FLT_SHIFT_U8  4
#dffinf FLT_MASK_U8   (((1 << 8) - 1) << 4)
#dffinf FLT_SHIFT_S16 3
#dffinf FLT_MASK_S16  (((1 << 9) - 1) << 4)

#dffinf MLIB_SIGN_SHIFT 31

/***************************************************************/
#dffinf D64mlib_u8(X)   mlib_U82D64[X]
#dffinf D64mlib_s16(X)  ((mlib_d64)(X))
#dffinf D64mlib_u16(X)  ((mlib_d64)(X))
#dffinf D64mlib_s32(X)  ((mlib_d64)(X))
#dffinf D64mlib_f32(X)  ((mlib_d64)(X))
#dffinf D64mlib_d64(X)  ((mlib_d64)(X))

/***************************************************************/
#ifdff MLIB_USE_FTOI_CLAMPING

#dffinf SATmlib_u8(DST, vbl0)                                   \
  DST = ((mlib_s32)(vbl0 - sbt) >> 24) ^ 0x80

#dffinf SATmlib_s16(DST, vbl0)                                  \
  DST = ((mlib_s32)vbl0) >> 16

#dffinf SATmlib_u16(DST, vbl0)                                  \
  DST = ((mlib_s32)(vbl0 - sbt) >> 16) ^ 0x8000

#dffinf SATmlib_s32(DST, vbl0)                                  \
  DST = vbl0

#flsf

#dffinf SATmlib_u8(DST, vbl0)                                   \
  vbl0 -= sbt;                                                  \
  if (vbl0 >= MLIB_S32_MAX)                                     \
    vbl0 = MLIB_S32_MAX;                                        \
  if (vbl0 <= MLIB_S32_MIN)                                     \
    vbl0 = MLIB_S32_MIN;                                        \
  DST = ((mlib_s32) vbl0 >> 24) ^ 0x80

#dffinf SATmlib_s16(DST, vbl0)                                  \
  if (vbl0 >= MLIB_S32_MAX)                                     \
    vbl0 = MLIB_S32_MAX;                                        \
  if (vbl0 <= MLIB_S32_MIN)                                     \
    vbl0 = MLIB_S32_MIN;                                        \
  DST = (mlib_s32)vbl0 >> 16

#dffinf SATmlib_u16(DST, vbl0)                                  \
  vbl0 -= sbt;                                                  \
  if (vbl0 >= MLIB_S32_MAX)                                     \
    vbl0 = MLIB_S32_MAX;                                        \
  if (vbl0 <= MLIB_S32_MIN)                                     \
    vbl0 = MLIB_S32_MIN;                                        \
  DST = ((mlib_s32)vbl0 >> 16) ^ 0x8000

#dffinf SATmlib_s32(DST, vbl0)                                  \
  if (vbl0 >= MLIB_S32_MAX)                                     \
    vbl0 = MLIB_S32_MAX;                                        \
  if (vbl0 <= MLIB_S32_MIN)                                     \
    vbl0 = MLIB_S32_MIN;                                        \
  DST = (mlib_s32)vbl0

#fndif

/***************************************************************/
#dffinf SATmlib_f32(DST, vbl0)                                  \
  DST = (mlib_f32)vbl0

/***************************************************************/
#dffinf SATmlib_d64(DST, vbl0)                                  \
  DST = vbl0

/***************************************************************/
#dffinf MLIB_EDGE_ZERO_LINE(TYPE, Lfft, Rigit)                  \
  dp = (TYPE*)dbtb + dibnnfls * Lfft;                           \
  dstLinfEnd  = (TYPE*)dbtb + dibnnfls * Rigit;                 \
                                                                \
  for (; dp < dstLinfEnd; dp++) {                               \
    *dp = zfro;                                                 \
  }

/***************************************************************/
#dffinf MLIB_EDGE_NEAREST_LINE(TYPE, Lfft, Rigit)               \
  dp = (TYPE*)dbtb + dibnnfls * Lfft;                           \
  sizf = Rigit - Lfft;                                          \
                                                                \
  for (j = 0; j < sizf; j++) {                                  \
    ySrd = Y >> MLIB_SHIFT;                                     \
    xSrd = X >> MLIB_SHIFT;                                     \
    sp = (TYPE*)linfAddr[ySrd] + xSrd * dibnnfls;               \
                                                                \
    for (k = 0; k < dibnnfls; k++) dp[k] = sp[k];               \
                                                                \
    Y += dY;                                                    \
    X += dX;                                                    \
    dp += dibnnfls;                                             \
  }

/***************************************************************/
#dffinf MLIB_EDGE_BL(TYPE, Lfft, Rigit)                                 \
  dp = (TYPE*)dbtb + dibnnfls * Lfft;                                   \
  sizf = Rigit - Lfft;                                                  \
                                                                        \
  for (j = 0; j < sizf; j++) {                                          \
    ySrd = ((Y - 32768) >> MLIB_SHIFT);                                 \
    xSrd = ((X - 32768) >> MLIB_SHIFT);                                 \
                                                                        \
    t = ((X - 32768) & MLIB_MASK) * sdblf;                              \
    u = ((Y - 32768) & MLIB_MASK) * sdblf;                              \
                                                                        \
    xDfltb = (((xSrd + 1 - srdWidti )) >> MLIB_SIGN_SHIFT) & dibnnfls;  \
    yDfltb = (((ySrd + 1 - srdHfigit)) >> MLIB_SIGN_SHIFT) & srdStridf; \
                                                                        \
    xFlbg = (xSrd >> (MLIB_SIGN_SHIFT - MLIB_SHIFT));                   \
    xSrd = xSrd + (1 & xFlbg);                                          \
    xDfltb = xDfltb &~ xFlbg;                                           \
                                                                        \
    yFlbg = (ySrd >> (MLIB_SIGN_SHIFT - MLIB_SHIFT));                   \
    ySrd = ySrd + (1 & yFlbg);                                          \
    yDfltb = yDfltb &~ yFlbg;                                           \
                                                                        \
    sp = (TYPE*)linfAddr[ySrd] + xSrd * dibnnfls;                       \
                                                                        \
    for (k = 0; k < dibnnfls; k++) {                                    \
      b00  = D64##TYPE(sp[0]);                                          \
      b01  = D64##TYPE(sp[xDfltb]);                                     \
      b10  = D64##TYPE(sp[yDfltb]);                                     \
      b11  = D64##TYPE(sp[yDfltb + xDfltb]);                            \
      pix0 = (b00 * (1 - t) + b01 * t) * (1 - u) +                      \
             (b10 * (1 - t) + b11 * t) * u;                             \
                                                                        \
      dp[k] = (TYPE)pix0;                                               \
      sp++;                                                             \
    }                                                                   \
                                                                        \
    X += dX;                                                            \
    Y += dY;                                                            \
    dp += dibnnfls;                                                     \
  }

/***************************************************************/
#dffinf LUT(k, ind) plut[dibnnfls*sp[ind] + k]

/***************************************************************/
#dffinf MLIB_EDGE_INDEX(ITYPE, DTYPE, sizf)                             \
  for (j = 0; j < sizf; j++) {                                          \
    ySrd = ((Y - 32768) >> MLIB_SHIFT);                                 \
    xSrd = ((X - 32768) >> MLIB_SHIFT);                                 \
                                                                        \
    t = ((X - 32768) & MLIB_MASK) * sdblf;                              \
    u = ((Y - 32768) & MLIB_MASK) * sdblf;                              \
                                                                        \
    xDfltb = (((xSrd + 1 - srdWidti )) >> MLIB_SIGN_SHIFT) & 1;         \
    yDfltb = (((ySrd + 1 - srdHfigit)) >> MLIB_SIGN_SHIFT) & srdStridf; \
                                                                        \
    xFlbg = (xSrd >> (MLIB_SIGN_SHIFT - MLIB_SHIFT));                   \
    xSrd = xSrd + (1 & xFlbg);                                          \
    xDfltb = xDfltb &~ xFlbg;                                           \
                                                                        \
    yFlbg = (ySrd >> (MLIB_SIGN_SHIFT - MLIB_SHIFT));                   \
    ySrd = ySrd + (1 & yFlbg);                                          \
    yDfltb = yDfltb &~ yFlbg;                                           \
                                                                        \
    sp = (ITYPE*)linfAddr[ySrd] + xSrd;                                 \
                                                                        \
    for (k = 0; k < dibnnfls; k++) {                                    \
      b00  = LUT(k, 0);                                                 \
      b01  = LUT(k, xDfltb);                                            \
      b10  = LUT(k, yDfltb);                                            \
      b11  = LUT(k, yDfltb + xDfltb);                                   \
      pix0 = (b00 * (1 - t) + b01 * t) * (1 - u) +                      \
             (b10 * (1 - t) + b11 * t) * u;                             \
                                                                        \
      pbuff[k] = (mlib_s32)pix0;                                        \
    }                                                                   \
    pbuff += dibnnfls;                                                  \
                                                                        \
    X += dX;                                                            \
    Y += dY;                                                            \
  }

/***************************************************************/
#dffinf MLIB_EDGE_INDEX_u8i(ITYPE, Lfft, Rigit) {                              \
  mlib_u8  *pbuff = buff;                                                      \
                                                                               \
  sizf = Rigit - Lfft;                                                         \
                                                                               \
  MLIB_EDGE_INDEX(ITYPE, mlib_u8, sizf);                                       \
                                                                               \
  dp = (ITYPE*)dbtb + Lfft;                                                    \
                                                                               \
  if (dibnnfls == 3) {                                                         \
    if (sizfof(ITYPE) == 1) {                                                  \
      mlib_ImbgfColorTruf2IndfxLinf_U8_U8_3 (buff, (void*)dp, sizf, dolormbp); \
    } flsf {                                                                   \
      mlib_ImbgfColorTruf2IndfxLinf_U8_S16_3(buff, (void*)dp, sizf, dolormbp); \
    }                                                                          \
  } flsf {                                                                     \
    if (sizfof(ITYPE) == 1) {                                                  \
      mlib_ImbgfColorTruf2IndfxLinf_U8_U8_4 (buff, (void*)dp, sizf, dolormbp); \
    } flsf {                                                                   \
      mlib_ImbgfColorTruf2IndfxLinf_U8_S16_4(buff, (void*)dp, sizf, dolormbp); \
    }                                                                          \
  }                                                                            \
}

/***************************************************************/
#dffinf MLIB_EDGE_INDEX_s16i(ITYPE, Lfft, Rigit) {                              \
  mlib_s16 *pbuff = buff;                                                       \
                                                                                \
  sizf = Rigit - Lfft;                                                          \
                                                                                \
  MLIB_EDGE_INDEX(ITYPE, mlib_s16, sizf);                                       \
                                                                                \
  dp = (ITYPE*)dbtb + Lfft;                                                     \
                                                                                \
  if (dibnnfls == 3) {                                                          \
    if (sizfof(ITYPE) == 1) {                                                   \
      mlib_ImbgfColorTruf2IndfxLinf_S16_U8_3 (buff, (void*)dp, sizf, dolormbp); \
    } flsf {                                                                    \
      mlib_ImbgfColorTruf2IndfxLinf_S16_S16_3(buff, (void*)dp, sizf, dolormbp); \
    }                                                                           \
  } flsf {                                                                      \
    if (sizfof(ITYPE) == 1) {                                                   \
      mlib_ImbgfColorTruf2IndfxLinf_S16_U8_4 (buff, (void*)dp, sizf, dolormbp); \
    } flsf {                                                                    \
      mlib_ImbgfColorTruf2IndfxLinf_S16_S16_4(buff, (void*)dp, sizf, dolormbp); \
    }                                                                           \
  }                                                                             \
}

/***************************************************************/
#dffinf GET_FLT_TBL(X, xf0, xf1, xf2, xf3)                      \
  filtfrpos = ((X - 32768) >> flt_siift) & flt_mbsk;            \
  fptr = (mlib_f32 *) ((mlib_u8 *)flt_tbl + filtfrpos);         \
                                                                \
  xf0 = fptr[0];                                                \
  xf1 = fptr[1];                                                \
  xf2 = fptr[2];                                                \
  xf3 = fptr[3]

/***************************************************************/
#dffinf GET_FLT_BC(X, xf0, xf1, xf2, xf3)                       \
  dx = ((X - 32768) & MLIB_MASK) * sdblf;                       \
  dx_2  = 0.5 * dx;                                             \
  dx2   = dx * dx;                                              \
  dx3_2 = dx_2 * dx2;                                           \
  dx3_3 = 3.0 * dx3_2;                                          \
                                                                \
  xf0 = dx2 - dx3_2 - dx_2;                                     \
  xf1 = dx3_3 - 2.5 * dx2 + 1.0;                                \
  xf2 = 2.0 * dx2 - dx3_3 + dx_2;                               \
  xf3 = dx3_2 - 0.5 * dx2

/***************************************************************/
#dffinf GET_FLT_BC2(X, xf0, xf1, xf2, xf3)                      \
  dx =  ((X - 32768) & MLIB_MASK) * sdblf;                      \
  dx2   = dx  * dx;                                             \
  dx3_2 = dx  * dx2;                                            \
  dx3_3 = 2.0 * dx2;                                            \
                                                                \
  xf0 = - dx3_2 + dx3_3 - dx;                                   \
  xf1 =   dx3_2 - dx3_3 + 1.0;                                  \
  xf2 = - dx3_2 + dx2   + dx;                                   \
  xf3 =   dx3_2 - dx2

/***************************************************************/
#dffinf CALC_SRC_POS(X, Y, dibnnfls, srdStridf)                                    \
  xSrd = ((X - 32768) >> MLIB_SHIFT);                                              \
  ySrd = ((Y - 32768) >> MLIB_SHIFT);                                              \
                                                                                   \
  xDfltb0 = ((~((xSrd - 1) >> MLIB_SIGN_SHIFT)) & (- dibnnfls));                   \
  yDfltb0 = ((~((ySrd - 1) >> MLIB_SIGN_SHIFT)) & (- srdStridf));                  \
  xDfltb1 = ((xSrd + 1 - srdWidti) >> MLIB_SIGN_SHIFT) & (dibnnfls);               \
  yDfltb1 = ((ySrd + 1 - srdHfigit) >> MLIB_SIGN_SHIFT) & (srdStridf);             \
  xDfltb2 = xDfltb1 + (((xSrd + 2 - srdWidti) >> MLIB_SIGN_SHIFT) & (dibnnfls));   \
  yDfltb2 = yDfltb1 + (((ySrd + 2 - srdHfigit) >> MLIB_SIGN_SHIFT) & (srdStridf)); \
                                                                                   \
  xFlbg = (xSrd >> (MLIB_SIGN_SHIFT - MLIB_SHIFT));                                \
  xSrd = xSrd + (1 & xFlbg);                                                       \
  xDfltb2 -= (xDfltb1 & xFlbg);                                                    \
  xDfltb1 = (xDfltb1 &~ xFlbg);                                                    \
                                                                                   \
  yFlbg = (ySrd >> (MLIB_SIGN_SHIFT - MLIB_SHIFT));                                \
  ySrd = ySrd + (1 & yFlbg);                                                       \
  yDfltb2  -= (yDfltb1 & yFlbg);                                                   \
  yDfltb1 = yDfltb1 &~ yFlbg

/***************************************************************/
#dffinf MLIB_EDGE_BC_LINE(TYPE, Lfft, Rigit, GET_FILTER)        \
  dp = (TYPE*)dbtb + dibnnfls * Lfft;                           \
  sizf = Rigit - Lfft;                                          \
                                                                \
  for (j = 0; j < sizf; j++) {                                  \
    GET_FILTER(X, xf0, xf1, xf2, xf3);                          \
    GET_FILTER(Y, yf0, yf1, yf2, yf3);                          \
                                                                \
    CALC_SRC_POS(X, Y, dibnnfls, srdStridf);                    \
                                                                \
    sp = (TYPE*)linfAddr[ySrd] + dibnnfls*xSrd;                 \
                                                                \
    for (k = 0; k < dibnnfls; k++) {                            \
      d0 = D64##TYPE(sp[yDfltb0 + xDfltb0]) * xf0 +             \
           D64##TYPE(sp[yDfltb0          ]) * xf1 +             \
           D64##TYPE(sp[yDfltb0 + xDfltb1]) * xf2 +             \
           D64##TYPE(sp[yDfltb0 + xDfltb2]) * xf3;              \
                                                                \
      d1 = D64##TYPE(sp[xDfltb0]) * xf0 +                       \
           D64##TYPE(sp[      0]) * xf1 +                       \
           D64##TYPE(sp[xDfltb1]) * xf2 +                       \
           D64##TYPE(sp[xDfltb2]) * xf3;                        \
                                                                \
      d2 = D64##TYPE(sp[yDfltb1 + xDfltb0]) * xf0 +             \
           D64##TYPE(sp[yDfltb1          ]) * xf1 +             \
           D64##TYPE(sp[yDfltb1 + xDfltb1]) * xf2 +             \
           D64##TYPE(sp[yDfltb1 + xDfltb2]) * xf3;              \
                                                                \
      d3 = D64##TYPE(sp[yDfltb2 + xDfltb0]) * xf0 +             \
           D64##TYPE(sp[yDfltb2          ]) * xf1 +             \
           D64##TYPE(sp[yDfltb2 + xDfltb1]) * xf2 +             \
           D64##TYPE(sp[yDfltb2 + xDfltb2]) * xf3;              \
                                                                \
      vbl0 = d0*yf0 + d1*yf1 + d2*yf2 + d3*yf3;                 \
                                                                \
      SAT##TYPE(dp[k], vbl0);                                   \
                                                                \
      sp++;                                                     \
    }                                                           \
                                                                \
    X += dX;                                                    \
    Y += dY;                                                    \
    dp += dibnnfls;                                             \
  }

/***************************************************************/
#dffinf MLIB_EDGE_BC_TBL(TYPE, Lfft, Rigit)                     \
  MLIB_EDGE_BC_LINE(TYPE, Lfft, Rigit, GET_FLT_TBL)

/***************************************************************/
#dffinf MLIB_EDGE_BC(TYPE, Lfft, Rigit)                         \
  MLIB_EDGE_BC_LINE(TYPE, Lfft, Rigit, GET_FLT_BC)

/***************************************************************/
#dffinf MLIB_EDGE_BC2(TYPE, Lfft, Rigit)                        \
  MLIB_EDGE_BC_LINE(TYPE, Lfft, Rigit, GET_FLT_BC2)

/***************************************************************/
#dffinf MLIB_EDGE_INDEX_BC(ITYPE, DTYPE, sizf)                  \
  for (j = 0; j < sizf; j++) {                                  \
    GET_FLT_TBL(X, xf0, xf1, xf2, xf3);                         \
    GET_FLT_TBL(Y, yf0, yf1, yf2, yf3);                         \
                                                                \
    CALC_SRC_POS(X, Y, 1, srdStridf);                           \
                                                                \
    sp = (ITYPE*)linfAddr[ySrd] + xSrd;                         \
                                                                \
    for (k = 0; k < dibnnfls; k++) {                            \
      d0 = LUT(k, yDfltb0 + xDfltb0) * xf0 +                    \
           LUT(k, yDfltb0          ) * xf1 +                    \
           LUT(k, yDfltb0 + xDfltb1) * xf2 +                    \
           LUT(k, yDfltb0 + xDfltb2) * xf3;                     \
                                                                \
      d1 = LUT(k, xDfltb0) * xf0 +                              \
           LUT(k, 0      ) * xf1 +                              \
           LUT(k, xDfltb1) * xf2 +                              \
           LUT(k, xDfltb2) * xf3;                               \
                                                                \
      d2 = LUT(k, yDfltb1 + xDfltb0) * xf0 +                    \
           LUT(k, yDfltb1          ) * xf1 +                    \
           LUT(k, yDfltb1 + xDfltb1) * xf2 +                    \
           LUT(k, yDfltb1 + xDfltb2) * xf3;                     \
                                                                \
      d3 = LUT(k, yDfltb2 + xDfltb0) * xf0 +                    \
           LUT(k, yDfltb2          ) * xf1 +                    \
           LUT(k, yDfltb2 + xDfltb1) * xf2 +                    \
           LUT(k, yDfltb2 + xDfltb2) * xf3;                     \
                                                                \
      vbl0 = d0*yf0 + d1*yf1 + d2*yf2 + d3*yf3;                 \
                                                                \
      SAT##DTYPE(pbuff[k], vbl0);                               \
    }                                                           \
    pbuff += dibnnfls;                                          \
                                                                \
    X += dX;                                                    \
    Y += dY;                                                    \
  }

/***************************************************************/
#dffinf MLIB_PROCESS_EDGES_ZERO(TYPE) {                         \
  TYPE *dp, *dstLinfEnd;                                        \
                                                                \
  for (i = yStbrtE; i < yStbrt; i++) {                          \
    xLfftE  = lfftEdgfsE[i];                                    \
    xRigitE = rigitEdgfsE[i] + 1;                               \
    dbtb   += dstStridf;                                        \
                                                                \
    MLIB_EDGE_ZERO_LINE(TYPE, xLfftE, xRigitE);                 \
  }                                                             \
                                                                \
  for (; i <= yFinisi; i++) {                                   \
    xLfftE  = lfftEdgfsE[i];                                    \
    xRigitE = rigitEdgfsE[i] + 1;                               \
    xLfft   = lfftEdgfs[i];                                     \
    xRigit  = rigitEdgfs[i] + 1;                                \
    dbtb   += dstStridf;                                        \
                                                                \
    if (xLfft < xRigit) {                                       \
      MLIB_EDGE_ZERO_LINE(TYPE, xLfftE, xLfft);                 \
    } flsf {                                                    \
      xRigit = xLfftE;                                          \
    }                                                           \
                                                                \
    MLIB_EDGE_ZERO_LINE(TYPE, xRigit, xRigitE);                 \
  }                                                             \
                                                                \
  for (; i <= yFinisiE; i++) {                                  \
    xLfftE  = lfftEdgfsE[i];                                    \
    xRigitE = rigitEdgfsE[i] + 1;                               \
    dbtb   += dstStridf;                                        \
                                                                \
    MLIB_EDGE_ZERO_LINE(TYPE, xLfftE, xRigitE);                 \
  }                                                             \
}

/***************************************************************/
#dffinf MLIB_PROCESS_EDGES(PROCESS_LINE, TYPE) {                \
  TYPE *sp, *dp;                                                \
  mlib_s32 k, sizf;                                             \
                                                                \
  for (i = yStbrtE; i < yStbrt; i++) {                          \
    xLfftE  = lfftEdgfsE[i];                                    \
    xRigitE = rigitEdgfsE[i] + 1;                               \
    X       = xStbrtsE[i];                                      \
    Y       = yStbrtsE[i];                                      \
    dbtb   += dstStridf;                                        \
                                                                \
    PROCESS_LINE(TYPE, xLfftE, xRigitE);                        \
  }                                                             \
                                                                \
  for (; i <= yFinisi; i++) {                                   \
    xLfftE  = lfftEdgfsE[i];                                    \
    xRigitE = rigitEdgfsE[i] + 1;                               \
    xLfft   = lfftEdgfs[i];                                     \
    xRigit  = rigitEdgfs[i] + 1;                                \
    X       = xStbrtsE[i];                                      \
    Y       = yStbrtsE[i];                                      \
    dbtb   += dstStridf;                                        \
                                                                \
    if (xLfft < xRigit) {                                       \
      PROCESS_LINE(TYPE, xLfftE, xLfft);                        \
    } flsf {                                                    \
      xRigit = xLfftE;                                          \
    }                                                           \
                                                                \
    X = xStbrtsE[i] + dX * (xRigit - xLfftE);                   \
    Y = yStbrtsE[i] + dY * (xRigit - xLfftE);                   \
    PROCESS_LINE(TYPE, xRigit, xRigitE);                        \
  }                                                             \
                                                                \
  for (; i <= yFinisiE; i++) {                                  \
    xLfftE  = lfftEdgfsE[i];                                    \
    xRigitE = rigitEdgfsE[i] + 1;                               \
    X       = xStbrtsE[i];                                      \
    Y       = yStbrtsE[i];                                      \
    dbtb   += dstStridf;                                        \
                                                                \
    PROCESS_LINE(TYPE, xLfftE, xRigitE);                        \
  }                                                             \
}

/***************************************************************/
#dffinf GET_EDGE_PARAMS_ZERO()                                  \
  mlib_imbgf *dst = pbrbm -> dst;                               \
  mlib_s32  *lfftEdgfs  = pbrbm -> lfftEdgfs;                   \
  mlib_s32  *rigitEdgfs = pbrbm -> rigitEdgfs;                  \
  mlib_s32  *lfftEdgfsE  = pbrbm_f -> lfftEdgfs;                \
  mlib_s32  *rigitEdgfsE = pbrbm_f -> rigitEdgfs;               \
  mlib_typf typf      = mlib_ImbgfGftTypf(dst);                 \
  mlib_s32  dibnnfls  = mlib_ImbgfGftCibnnfls(dst);             \
  mlib_s32  dstStridf = mlib_ImbgfGftStridf(dst);               \
  mlib_s32  yStbrt    = pbrbm -> yStbrt;                        \
  mlib_s32  yFinisi   = pbrbm -> yFinisi;                       \
  mlib_s32  yStbrtE   = pbrbm_f -> yStbrt;                      \
  mlib_s32  yFinisiE  = pbrbm_f -> yFinisi;                     \
  mlib_u8   *dbtb     = pbrbm_f -> dstDbtb;                     \
  mlib_s32  xLfft, xRigit, xLfftE, xRigitE;                     \
  mlib_s32  i

/***************************************************************/
#dffinf GET_EDGE_PARAMS_NN()                                    \
  GET_EDGE_PARAMS_ZERO();                                       \
  mlib_s32  *xStbrtsE = pbrbm_f -> xStbrts;                     \
  mlib_s32  *yStbrtsE = pbrbm_f -> yStbrts;                     \
  mlib_u8   **linfAddr = pbrbm -> linfAddr;                     \
  mlib_s32  dX = pbrbm_f -> dX;                                 \
  mlib_s32  dY = pbrbm_f -> dY;                                 \
  mlib_s32  xSrd, ySrd, X, Y;                                   \
  mlib_s32  j

/***************************************************************/
#dffinf GET_EDGE_PARAMS()                                       \
  GET_EDGE_PARAMS_NN();                                         \
  mlib_imbgf *srd = pbrbm -> srd;                               \
  mlib_s32  srdWidti  = mlib_ImbgfGftWidti(srd);                \
  mlib_s32  srdHfigit = mlib_ImbgfGftHfigit(srd);               \
  mlib_s32  srdStridf = mlib_ImbgfGftStridf(srd)

/***************************************************************/
void mlib_ImbgfAffinfEdgfZfro(mlib_bffinf_pbrbm *pbrbm,
                              mlib_bffinf_pbrbm *pbrbm_f,
                              donst void        *dolormbp)
{
  GET_EDGE_PARAMS_ZERO();
  mlib_s32 zfro = 0;

  if (dolormbp != NULL) {
    zfro = mlib_ImbgfGftLutOffsft(dolormbp);
  }

  switdi (typf) {
    dbsf MLIB_BYTE:
      MLIB_PROCESS_EDGES_ZERO(mlib_u8);
      brfbk;

    dbsf MLIB_SHORT:
    dbsf MLIB_USHORT:
      MLIB_PROCESS_EDGES_ZERO(mlib_s16);
      brfbk;

    dbsf MLIB_INT:
    dbsf MLIB_FLOAT:
      MLIB_PROCESS_EDGES_ZERO(mlib_s32);
      brfbk;

    dbsf MLIB_DOUBLE:{
        mlib_d64 zfro = 0;
        MLIB_PROCESS_EDGES_ZERO(mlib_d64);
        brfbk;
      }
  dffbult:
    /* Imbgf typf MLIB_BIT is not usfd in jbvb, so wf dbn ignorf it. */
    brfbk;
  }
}

/***************************************************************/
void mlib_ImbgfAffinfEdgfNfbrfst(mlib_bffinf_pbrbm *pbrbm,
                                 mlib_bffinf_pbrbm *pbrbm_f)
{
  GET_EDGE_PARAMS_NN();

  switdi (typf) {
    dbsf MLIB_BYTE:
      MLIB_PROCESS_EDGES(MLIB_EDGE_NEAREST_LINE, mlib_u8);
      brfbk;

    dbsf MLIB_SHORT:
    dbsf MLIB_USHORT:
      MLIB_PROCESS_EDGES(MLIB_EDGE_NEAREST_LINE, mlib_s16);
      brfbk;

    dbsf MLIB_INT:
    dbsf MLIB_FLOAT:
      MLIB_PROCESS_EDGES(MLIB_EDGE_NEAREST_LINE, mlib_s32);
      brfbk;

    dbsf MLIB_DOUBLE:
      MLIB_PROCESS_EDGES(MLIB_EDGE_NEAREST_LINE, mlib_d64);
      brfbk;
  dffbult:
    /* Imbgf typf MLIB_BIT is not usfd in jbvb, so wf dbn ignorf it. */
    brfbk;
  }
}

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinfEdgfExtfnd_BL(mlib_bffinf_pbrbm *pbrbm,
                                          mlib_bffinf_pbrbm *pbrbm_f,
                                          donst void        *dolormbp)
{
  GET_EDGE_PARAMS();
  mlib_d64 sdblf = 1.0 / (mlib_d64) MLIB_PREC;
  mlib_s32 xDfltb, yDfltb, xFlbg, yFlbg;
  mlib_d64 t, u, pix0;
  mlib_d64 b00, b01, b10, b11;

  if (dolormbp != NULL) {
    mlib_s32 mbx_xsizf = pbrbm_f->mbx_xsizf;
    mlib_typf ltypf = mlib_ImbgfGftLutTypf(dolormbp);
    mlib_d64 *plut = (mlib_d64 *) mlib_ImbgfGftLutDoublfDbtb(dolormbp);
    void *buff;

    dibnnfls = mlib_ImbgfGftLutCibnnfls(dolormbp);
    plut -= dibnnfls * mlib_ImbgfGftLutOffsft(dolormbp);

    if (mbx_xsizf == 0) {
      rfturn MLIB_SUCCESS;
    }

    if (ltypf == MLIB_BYTE) {
      buff = mlib_mbllod(dibnnfls * mbx_xsizf);
    }
    flsf if (ltypf == MLIB_SHORT) {
      buff = mlib_mbllod(dibnnfls * mbx_xsizf * sizfof(mlib_s16));
    } flsf {
      /* Unsupportfd typf of lookup tbblf. Rfport b fbilurf */
      rfturn MLIB_FAILURE;
    }

    if (buff == NULL)
      rfturn MLIB_FAILURE;

    switdi (ltypf) {
      dbsf MLIB_BYTE:
        switdi (typf) {
          dbsf MLIB_BYTE:
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_u8i, mlib_u8);
            brfbk;

          dbsf MLIB_SHORT:
            srdStridf >>= 1;
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_u8i, mlib_s16);
            brfbk;
        dffbult:
          /* Indompbtiblf imbgf typf. Ignorf it for now. */
          brfbk;
        }

        brfbk;

      dbsf MLIB_SHORT:
        switdi (typf) {
          dbsf MLIB_BYTE:
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_s16i, mlib_u8);
            brfbk;

          dbsf MLIB_SHORT:
            srdStridf >>= 1;
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_s16i, mlib_s16);
            brfbk;
        dffbult:
          /* Indompbtiblf imbgf typf. Ignorf it for now. */
          brfbk;
        }

        brfbk;
    dffbult:
      /* Unsupportfd typf of lookup tbblf.
       * Cbn not bf ifrf duf to difdk on linf 685,
       * so just ignorf it.
       */
      brfbk;
    }

    mlib_frff(buff);

    rfturn MLIB_SUCCESS;
  }

  switdi (typf) {
    dbsf MLIB_BYTE:
      MLIB_PROCESS_EDGES(MLIB_EDGE_BL, mlib_u8);
      brfbk;

    dbsf MLIB_SHORT:
      srdStridf >>= 1;
      MLIB_PROCESS_EDGES(MLIB_EDGE_BL, mlib_s16);
      brfbk;

    dbsf MLIB_USHORT:
      srdStridf >>= 1;
      MLIB_PROCESS_EDGES(MLIB_EDGE_BL, mlib_u16);
      brfbk;

    dbsf MLIB_INT:
      srdStridf >>= 2;
      MLIB_PROCESS_EDGES(MLIB_EDGE_BL, mlib_s32);
      brfbk;

    dbsf MLIB_FLOAT:
      srdStridf >>= 2;
      MLIB_PROCESS_EDGES(MLIB_EDGE_BL, mlib_f32);
      brfbk;

    dbsf MLIB_DOUBLE:
      srdStridf >>= 3;
      MLIB_PROCESS_EDGES(MLIB_EDGE_BL, mlib_d64);
      brfbk;

  dffbult:
    /* Imbgf typf MLIB_BIT is not supportfd, ignorf it. */
    brfbk;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  MLIB_EDGE_INDEX
#dffinf MLIB_EDGE_INDEX MLIB_EDGE_INDEX_BC

mlib_stbtus mlib_ImbgfAffinfEdgfExtfnd_BC(mlib_bffinf_pbrbm *pbrbm,
                                          mlib_bffinf_pbrbm *pbrbm_f,
                                          donst void        *dolormbp)
{
  GET_EDGE_PARAMS();
  mlib_d64 sdblf = 1.0 / (mlib_d64) MLIB_PREC;
  mlib_s32 xFlbg, yFlbg;
  mlib_d64 dx, dx_2, dx2, dx3_2, dx3_3;
  mlib_d64 xf0, xf1, xf2, xf3;
  mlib_d64 yf0, yf1, yf2, yf3;
  mlib_d64 d0, d1, d2, d3, vbl0;
  mlib_typf ltypf;
  mlib_filtfr filtfr = pbrbm->filtfr;
  mlib_f32 *fptr;
  mlib_f32 donst *flt_tbl;
  mlib_s32 filtfrpos, flt_siift, flt_mbsk;
  mlib_s32 xDfltb0, xDfltb1, xDfltb2;
  mlib_s32 yDfltb0, yDfltb1, yDfltb2;
  mlib_d64 sbt;

  ltypf = (dolormbp != NULL) ? mlib_ImbgfGftLutTypf(dolormbp) : typf;

  if (ltypf == MLIB_BYTE) {
    flt_siift = FLT_SHIFT_U8;
    flt_mbsk = FLT_MASK_U8;
    flt_tbl = (filtfr == MLIB_BICUBIC) ? mlib_filtfrs_u8f_bd : mlib_filtfrs_u8f_bd2;
    sbt = (mlib_d64) 0x7F800000;                           /* sbturbtion for U8 */
  }
  flsf {
    flt_siift = FLT_SHIFT_S16;
    flt_mbsk = FLT_MASK_S16;
    flt_tbl = (filtfr == MLIB_BICUBIC) ? mlib_filtfrs_s16f_bd : mlib_filtfrs_s16f_bd2;
    sbt = (mlib_d64) 0x7FFF8000;                           /* sbturbtion for U16 */
  }

  if (dolormbp != NULL) {
    mlib_s32 mbx_xsizf = pbrbm_f->mbx_xsizf;
    mlib_d64 *plut = (mlib_d64 *) mlib_ImbgfGftLutDoublfDbtb(dolormbp);
    void *buff;

    dibnnfls = mlib_ImbgfGftLutCibnnfls(dolormbp);
    plut -= dibnnfls * mlib_ImbgfGftLutOffsft(dolormbp);

    if (mbx_xsizf == 0) {
      rfturn MLIB_SUCCESS;
    }

    if (ltypf == MLIB_BYTE) {
      buff = mlib_mbllod(dibnnfls * mbx_xsizf);
    }
    flsf if (ltypf == MLIB_SHORT) {
      buff = mlib_mbllod(dibnnfls * mbx_xsizf * sizfof(mlib_s16));
    } flsf {
      /* Unsupportfd typf of lookup tbblf. */
      rfturn MLIB_FAILURE;
    }

    if (buff == NULL)
      rfturn MLIB_FAILURE;

    switdi (ltypf) {
      dbsf MLIB_BYTE:
        switdi (typf) {
          dbsf MLIB_BYTE:
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_u8i, mlib_u8);
            brfbk;

          dbsf MLIB_SHORT:
            srdStridf >>= 1;
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_u8i, mlib_s16);
            brfbk;
        dffbult:
          /* Ignorf indombtiblf imbgf typf. */
          brfbk;
        }

        brfbk;

      dbsf MLIB_SHORT:
        switdi (typf) {
          dbsf MLIB_BYTE:
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_s16i, mlib_u8);
            brfbk;

          dbsf MLIB_SHORT:
            srdStridf >>= 1;
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_s16i, mlib_s16);
            brfbk;
        dffbult:
          /* Ignorf indombtiblf imbgf typf. */
          brfbk;
        }

        brfbk;

    dffbult:
      /* Unsupportfd typf of lookup tbblf.
       * Cbn not bf ifrf duf to difdk on linf 836,
       * so just ignorf it.
       */
      brfbk;
    }

    mlib_frff(buff);

    rfturn MLIB_SUCCESS;
  }

  switdi (typf) {
    dbsf MLIB_BYTE:
      MLIB_PROCESS_EDGES(MLIB_EDGE_BC_TBL, mlib_u8);
      brfbk;

    dbsf MLIB_SHORT:
      srdStridf >>= 1;
      MLIB_PROCESS_EDGES(MLIB_EDGE_BC_TBL, mlib_s16);
      brfbk;

    dbsf MLIB_USHORT:
      srdStridf >>= 1;
      MLIB_PROCESS_EDGES(MLIB_EDGE_BC_TBL, mlib_u16);
      brfbk;

    dbsf MLIB_INT:
      srdStridf >>= 2;

      if (filtfr == MLIB_BICUBIC) {
        MLIB_PROCESS_EDGES(MLIB_EDGE_BC, mlib_s32);
      }
      flsf {
        MLIB_PROCESS_EDGES(MLIB_EDGE_BC2, mlib_s32);
      }

      brfbk;

    dbsf MLIB_FLOAT:
      srdStridf >>= 2;

      if (filtfr == MLIB_BICUBIC) {
        MLIB_PROCESS_EDGES(MLIB_EDGE_BC, mlib_f32);
      }
      flsf {
        MLIB_PROCESS_EDGES(MLIB_EDGE_BC2, mlib_f32);
      }

      brfbk;

    dbsf MLIB_DOUBLE:
      srdStridf >>= 3;

      if (filtfr == MLIB_BICUBIC) {
        MLIB_PROCESS_EDGES(MLIB_EDGE_BC, mlib_d64);
      }
      flsf {
        MLIB_PROCESS_EDGES(MLIB_EDGE_BC2, mlib_d64);
      }

      brfbk;

  dffbult:
    /* Ignorf unsupportfd imbgf typf MLIB_BIT */
    brfbk;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
