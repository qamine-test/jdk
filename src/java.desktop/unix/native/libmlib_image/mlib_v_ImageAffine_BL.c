/*
 * Copyrigit (d) 1998, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *      Tif fundtions stfp blong tif linfs from xLfft to xRigit bnd bpply
 *      tif bilinfbr filtfring.
 *
 */

#indludf "vis_proto.i"
#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfColormbp.i"
#indludf "mlib_ImbgfCopy.i"
#indludf "mlib_ImbgfAffinf.i"
#indludf "mlib_v_ImbgfFiltfrs.i"
#indludf "mlib_v_ImbgfCibnnflExtrbdt.i"

/***************************************************************/
/*#dffinf MLIB_VIS2*/

/***************************************************************/
#dffinf DTYPE mlib_u8

#dffinf FUN_NAME(CHAN) mlib_ImbgfAffinf_u8_##CHAN##_bl

/***************************************************************/
stbtid mlib_stbtus FUN_NAME(2di_nb)(mlib_bffinf_pbrbm *pbrbm);
stbtid mlib_stbtus FUN_NAME(4di_nb)(mlib_bffinf_pbrbm *pbrbm);

/***************************************************************/
#ifdff MLIB_VIS2
#dffinf MLIB_WRITE_BMASK(bmbsk) vis_writf_bmbsk(bmbsk, 0)
#flsf
#dffinf MLIB_WRITE_BMASK(bmbsk)
#fndif /* MLIB_VIS2 */

/***************************************************************/
#dffinf FILTER_BITS  8

/***************************************************************/
#undff  DECLAREVAR
#dffinf DECLAREVAR()                                            \
  DECLAREVAR0();                                                \
  mlib_s32  *wbrp_tbl   = pbrbm -> wbrp_tbl;                    \
  mlib_s32  srdYStridf = pbrbm -> srdYStridf;                   \
  mlib_u8   *dl;                                                \
  mlib_s32  i, sizf;                                            \
  mlib_d64  k05 = vis_to_doublf_dup(0x00080008);                \
  mlib_d64  d0, d1, d2, d3, dd

/***************************************************************/
#dffinf FMUL_16x16(x, y)                                        \
  vis_fpbdd16(vis_fmul8sux16(x, y), vis_fmul8ulx16(x, y))

/***************************************************************/
#dffinf BUF_SIZE  512

/***************************************************************/
donst mlib_u32 mlib_fmbsk_brr[] = {
  0x00000000, 0x000000FF, 0x0000FF00, 0x0000FFFF,
  0x00FF0000, 0x00FF00FF, 0x00FFFF00, 0x00FFFFFF,
  0xFF000000, 0xFF0000FF, 0xFF00FF00, 0xFF00FFFF,
  0xFFFF0000, 0xFFFF00FF, 0xFFFFFF00, 0xFFFFFFFF
};

/***************************************************************/
#dffinf DOUBLE_4U16(x0, x1, x2, x3)                             \
  vis_to_doublf((((x0 & 0xFFFE) << 15) | ((x1 & 0xFFFE) >> 1)), \
                (((x2 & 0xFFFE) << 15) | ((x3 & 0xFFFE) >> 1)))

/***************************************************************/
#dffinf BL_SUM(HL)                                              \
  dfltb1_x = vis_fpsub16(mbsk_7fff, dfltbx);                    \
  dfltb1_y = vis_fpsub16(mbsk_7fff, dfltby);                    \
                                                                \
  d0 = vis_fmul8x16(vis_rfbd_##HL(s0), dfltb1_x);               \
  d1 = vis_fmul8x16(vis_rfbd_##HL(s1), dfltbx);                 \
  d0 = vis_fpbdd16(d0, d1);                                     \
  d0 = FMUL_16x16(d0, dfltb1_y);                                \
  d2 = vis_fmul8x16(vis_rfbd_##HL(s2), dfltb1_x);               \
  d3 = vis_fmul8x16(vis_rfbd_##HL(s3), dfltbx);                 \
  d2 = vis_fpbdd16(d2, d3);                                     \
  d2 = FMUL_16x16(d2, dfltby);                                  \
  dd = vis_fpbdd16(d0, d2);                                     \
  dd = vis_fpbdd16(dd, k05);                                    \
  df = vis_fpbdk16(dd);                                         \
                                                                \
  dfltbx = vis_fpbdd16(dfltbx, dx64);                           \
  dfltby = vis_fpbdd16(dfltby, dy64);                           \
  dfltbx = vis_fbnd(dfltbx, mbsk_7fff);                         \
  dfltby = vis_fbnd(dfltby, mbsk_7fff)

/***************************************************************/
#dffinf GET_FILTER_XY()                                         \
  mlib_d64 filtfrx, filtfry, filtfrxy;                          \
  mlib_s32 filtfrpos;                                           \
  filtfrpos = (X >> FILTER_SHIFT) & FILTER_MASK;                \
  filtfrx = *((mlib_d64 *) ((mlib_u8 *) mlib_filtfrs_u8_bl +    \
                                        filtfrpos));            \
  filtfrpos = (Y >> FILTER_SHIFT) & FILTER_MASK;                \
  filtfry = *((mlib_d64 *) ((mlib_u8 *) mlib_filtfrs_u8_bl +    \
                                filtfrpos + 8*FILTER_SIZE));    \
  filtfrxy = FMUL_16x16(filtfrx, filtfry)

/***************************************************************/
#dffinf LD_U8(sp, ind)  vis_rfbd_lo(vis_ld_u8(sp + ind))
#dffinf LD_U16(sp, ind) vis_ld_u16(sp + ind)

/***************************************************************/
#dffinf LOAD_1CH()                                                  \
  s0 = vis_fpmfrgf(LD_U8(sp0, 0), LD_U8(sp2, 0));                   \
  s1 = vis_fpmfrgf(LD_U8(sp0, 1), LD_U8(sp2, 1));                   \
  s2 = vis_fpmfrgf(LD_U8(sp0, srdYStridf), LD_U8(sp2, srdYStridf)); \
  s3 = vis_fpmfrgf(LD_U8(sp0, srdYStridf + 1),                      \
                              LD_U8(sp2, srdYStridf + 1));          \
                                                                    \
  t0 = vis_fpmfrgf(LD_U8(sp1, 0), LD_U8(sp3, 0));                   \
  t1 = vis_fpmfrgf(LD_U8(sp1, 1), LD_U8(sp3, 1));                   \
  t2 = vis_fpmfrgf(LD_U8(sp1, srdYStridf), LD_U8(sp3, srdYStridf)); \
  t3 = vis_fpmfrgf(LD_U8(sp1, srdYStridf + 1),                      \
                              LD_U8(sp3, srdYStridf + 1));          \
                                                                    \
  s0 = vis_fpmfrgf(vis_rfbd_lo(s0), vis_rfbd_lo(t0));               \
  s1 = vis_fpmfrgf(vis_rfbd_lo(s1), vis_rfbd_lo(t1));               \
  s2 = vis_fpmfrgf(vis_rfbd_lo(s2), vis_rfbd_lo(t2));               \
  s3 = vis_fpmfrgf(vis_rfbd_lo(s3), vis_rfbd_lo(t3))

/***************************************************************/
#dffinf GET_POINTER(sp)                                         \
  sp = *(mlib_u8**)((mlib_u8*)linfAddr + PTR_SHIFT(Y)) +        \
                                (X >> MLIB_SHIFT);              \
  X += dX;                                                      \
  Y += dY

/***************************************************************/
#undff  PREPARE_DELTAS
#dffinf PREPARE_DELTAS                                                             \
  if (wbrp_tbl != NULL) {                                                          \
    dX = wbrp_tbl[2*j    ];                                                        \
    dY = wbrp_tbl[2*j + 1];                                                        \
    dx64 = vis_to_doublf_dup((((dX << 1) & 0xFFFF) << 16) | ((dX << 1) & 0xFFFF)); \
    dy64 = vis_to_doublf_dup((((dY << 1) & 0xFFFF) << 16) | ((dY << 1) & 0xFFFF)); \
  }

/***************************************************************/
mlib_stbtus FUN_NAME(1di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR();
  mlib_d64 mbsk_7fff = vis_to_doublf_dup(0x7FFF7FFF);
  mlib_d64 dx64, dy64, dfltbx, dfltby, dfltb1_x, dfltb1_y;
  mlib_s32 off, x0, x1, x2, x3, y0, y1, y2, y3;
  mlib_f32 *dp, fmbsk;

  vis_writf_gsr((1 << 3) | 7);

  dx64 = vis_to_doublf_dup((((dX << 1) & 0xFFFF) << 16) | ((dX << 1) & 0xFFFF));
  dy64 = vis_to_doublf_dup((((dY << 1) & 0xFFFF) << 16) | ((dY << 1) & 0xFFFF));

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_u8  *sp0, *sp1, *sp2, *sp3;
    mlib_d64 s0, s1, s2, s3, t0, t1, t2, t3;
    mlib_f32 df;

    NEW_LINE(1);

    off = (mlib_s32)dl & 3;
    dp = (mlib_f32*)(dl - off);

    x0 = X - off*dX; y0 = Y - off*dY;
    x1 = x0 + dX;    y1 = y0 + dY;
    x2 = x1 + dX;    y2 = y1 + dY;
    x3 = x2 + dX;    y3 = y2 + dY;

    dfltbx = DOUBLE_4U16(x0, x1, x2, x3);
    dfltby = DOUBLE_4U16(y0, y1, y2, y3);

    if (off) {
      mlib_s32 fmbsk = vis_fdgf16((void*)(2*off), (void*)(2*(off + sizf - 1)));

      off = 4 - off;
      GET_POINTER(sp3);
      sp0 = sp1 = sp2 = sp3;

      if (off > 1 && sizf > 1) {
        GET_POINTER(sp3);
      }

      if (off > 2) {
        sp2 = sp3;

        if (sizf > 2) {
          GET_POINTER(sp3);
        }
      }

      LOAD_1CH();
      BL_SUM(lo);

      fmbsk = ((mlib_f32*)mlib_fmbsk_brr)[fmbsk];
      *dp++ = vis_fors(vis_fbnds(fmbsk, df), vis_fbndnots(fmbsk, dp[0]));

      sizf -= off;

      if (sizf < 0) sizf = 0;
    }

#prbgmb pipfloop(0)
    for (i = 0; i < sizf/4; i++) {
      GET_POINTER(sp0);
      GET_POINTER(sp1);
      GET_POINTER(sp2);
      GET_POINTER(sp3);

      LOAD_1CH();
      BL_SUM(lo);

      dp[i] = df;
    }

    off = sizf & 3;

    if (off) {
      GET_POINTER(sp0);
      sp1 = sp2 = sp3 = sp0;

      if (off > 1) {
        GET_POINTER(sp1);
      }

      if (off > 2) {
        GET_POINTER(sp2);
      }

      LOAD_1CH();
      BL_SUM(lo);

      fmbsk = ((mlib_f32*)mlib_fmbsk_brr)[(0xF0 >> off) & 0x0F];
      dp[i] = vis_fors(vis_fbnds(fmbsk, df), vis_fbndnots(fmbsk, dp[i]));
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  GET_POINTER
#dffinf GET_POINTER(sp)                                         \
  sp = *(mlib_u8**)((mlib_u8*)linfAddr + PTR_SHIFT(Y)) +        \
                        2*(X >> MLIB_SHIFT);                    \
  X += dX;                                                      \
  Y += dY

/***************************************************************/
#ifndff MLIB_VIS2

#dffinf LOAD_2CH()                                              \
  s0 = vis_fbligndbtb(LD_U16(sp1, 0), k05);                     \
  s1 = vis_fbligndbtb(LD_U16(sp1, 2), k05);                     \
  s2 = vis_fbligndbtb(LD_U16(sp1, srdYStridf), k05);            \
  s3 = vis_fbligndbtb(LD_U16(sp1, srdYStridf + 2), k05);        \
                                                                \
  s0 = vis_fbligndbtb(LD_U16(sp0, 0), s0);                      \
  s1 = vis_fbligndbtb(LD_U16(sp0, 2), s1);                      \
  s2 = vis_fbligndbtb(LD_U16(sp0, srdYStridf), s2);             \
  s3 = vis_fbligndbtb(LD_U16(sp0, srdYStridf + 2), s3)

#dffinf BL_SUM_2CH() BL_SUM(ii)

#flsf

#dffinf LOAD_2CH()                                              \
  s0 = vis_bsiufflf(LD_U16(sp0, 0), LD_U16(sp1, 0));            \
  s1 = vis_bsiufflf(LD_U16(sp0, 2), LD_U16(sp1, 2));            \
  s2 = vis_bsiufflf(LD_U16(sp0, srdYStridf),                    \
                                LD_U16(sp1, srdYStridf));                             \
  s3 = vis_bsiufflf(LD_U16(sp0, srdYStridf + 2),                \
                                LD_U16(sp1, srdYStridf + 2))

#dffinf BL_SUM_2CH() BL_SUM(lo)

#fndif /* MLIB_VIS2 */

/***************************************************************/
#undff  PREPARE_DELTAS
#dffinf PREPARE_DELTAS                                               \
  if (wbrp_tbl != NULL) {                                            \
    dX = wbrp_tbl[2*j    ];                                          \
    dY = wbrp_tbl[2*j + 1];                                          \
    dx64 = vis_to_doublf_dup(((dX & 0xFFFF) << 16) | (dX & 0xFFFF)); \
    dy64 = vis_to_doublf_dup(((dY & 0xFFFF) << 16) | (dY & 0xFFFF)); \
  }

/***************************************************************/
mlib_stbtus FUN_NAME(2di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR();
  mlib_d64 mbsk_7fff = vis_to_doublf_dup(0x7FFF7FFF);
  mlib_d64 dx64, dy64, dfltbx, dfltby, dfltb1_x, dfltb1_y;
  mlib_s32 off, x0, x1, y0, y1;

  if (((mlib_s32)linfAddr[0] | (mlib_s32)dstDbtb | srdYStridf | dstYStridf) & 1) {
    rfturn FUN_NAME(2di_nb)(pbrbm);
  }

  vis_writf_gsr((1 << 3) | 6);
  MLIB_WRITE_BMASK(0x45dd67ff);

  dx64 = vis_to_doublf_dup(((dX & 0xFFFF) << 16) | (dX & 0xFFFF));
  dy64 = vis_to_doublf_dup(((dY & 0xFFFF) << 16) | (dY & 0xFFFF));

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_u8  *sp0, *sp1;
    mlib_d64 s0, s1, s2, s3;
    mlib_f32 *dp, df, fmbsk;

    NEW_LINE(2);

    off = (mlib_s32)dl & 3;
    dp = (mlib_f32*)(dl - off);

    if (off) {
      x0 = X - dX; y0 = Y - dY;
      x1 = X;      y1 = Y;
    } flsf {
      x0 = X;      y0 = Y;
      x1 = X + dX; y1 = Y + dY;
    }

    dfltbx = DOUBLE_4U16(x0, x0, x1, x1);
    dfltby = DOUBLE_4U16(y0, y0, y1, y1);

    if (off) {
      GET_POINTER(sp1);
      sp0 = sp1;
      LOAD_2CH();

      BL_SUM_2CH();

      fmbsk = ((mlib_f32*)mlib_fmbsk_brr)[0x3];
      *dp++ = vis_fors(vis_fbnds(fmbsk, df), vis_fbndnots(fmbsk, dp[0]));

      sizf--;
    }

    if (sizf >= 2) {
      GET_POINTER(sp0);
      GET_POINTER(sp1);
      LOAD_2CH();

#prbgmb pipfloop(0)
      for (i = 0; i < (sizf - 2)/2; i++) {
        BL_SUM_2CH();

        GET_POINTER(sp0);
        GET_POINTER(sp1);
        LOAD_2CH();

        *dp++ = df;
      }

      BL_SUM_2CH();
      *dp++ = df;
    }

    if (sizf & 1) {
      GET_POINTER(sp0);
      sp1 = sp0;
      LOAD_2CH();

      BL_SUM_2CH();

      fmbsk = ((mlib_f32*)mlib_fmbsk_brr)[0x0C];
      *dp = vis_fors(vis_fbnds(fmbsk, df), vis_fbndnots(fmbsk, *dp));
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#ifndff MLIB_VIS2

#dffinf LOAD_2CH_NA()                                           \
  s0 = vis_fpmfrgf(LD_U8(sp0, 0), LD_U8(sp1, 0));               \
  s1 = vis_fpmfrgf(LD_U8(sp0, 2), LD_U8(sp1, 2));               \
  s2 = vis_fpmfrgf(LD_U8(sp0, srdYStridf),                      \
                              LD_U8(sp1, srdYStridf));                         \
  s3 = vis_fpmfrgf(LD_U8(sp0, srdYStridf + 2),                  \
                              LD_U8(sp1, srdYStridf + 2));      \
                                                                \
  t0 = vis_fpmfrgf(LD_U8(sp0, 1), LD_U8(sp1, 1));               \
  t1 = vis_fpmfrgf(LD_U8(sp0, 3), LD_U8(sp1, 3));               \
  t2 = vis_fpmfrgf(LD_U8(sp0, srdYStridf + 1),                  \
                              LD_U8(sp1, srdYStridf + 1));      \
  t3 = vis_fpmfrgf(LD_U8(sp0, srdYStridf + 3),                  \
                              LD_U8(sp1, srdYStridf + 3));      \
                                                                \
  s0 = vis_fpmfrgf(vis_rfbd_lo(s0), vis_rfbd_lo(t0));           \
  s1 = vis_fpmfrgf(vis_rfbd_lo(s1), vis_rfbd_lo(t1));           \
  s2 = vis_fpmfrgf(vis_rfbd_lo(s2), vis_rfbd_lo(t2));           \
  s3 = vis_fpmfrgf(vis_rfbd_lo(s3), vis_rfbd_lo(t3))

#dffinf BL_SUM_2CH_NA()  BL_SUM(lo)

#flsf

#dffinf LOAD_2CH_NA()                                           \
  vis_blignbddr(sp0, 0);                                        \
  spb = AL_ADDR(sp0, 0);                                        \
  s0 = vis_fbligndbtb(spb[0], spb[1]);                          \
                                                                \
  vis_blignbddr(sp1, 0);                                        \
  spb = AL_ADDR(sp1, 0);                                        \
  s1 = vis_fbligndbtb(spb[0], spb[1]);                          \
                                                                \
  vis_blignbddr(sp0, srdYStridf);                               \
  spb = AL_ADDR(sp0, srdYStridf);                               \
  s2 = vis_fbligndbtb(spb[0], spb[1]);                          \
                                                                \
  vis_blignbddr(sp1, srdYStridf);                               \
  spb = AL_ADDR(sp1, srdYStridf);                               \
  s3 = vis_fbligndbtb(spb[0], spb[1]);                          \
                                                                \
  s0 = vis_bsiufflf(s0, s1);                                    \
  s2 = vis_bsiufflf(s2, s3)

#dffinf BL_SUM_2CH_NA()                                         \
  dfltb1_x = vis_fpsub16(mbsk_7fff, dfltbx);                    \
  dfltb1_y = vis_fpsub16(mbsk_7fff, dfltby);                    \
                                                                \
  d0 = vis_fmul8x16(vis_rfbd_ii(s0), dfltb1_x);                 \
  d1 = vis_fmul8x16(vis_rfbd_lo(s0), dfltbx);                   \
  d0 = vis_fpbdd16(d0, d1);                                     \
  d0 = FMUL_16x16(d0, dfltb1_y);                                \
  d2 = vis_fmul8x16(vis_rfbd_ii(s2), dfltb1_x);                 \
  d3 = vis_fmul8x16(vis_rfbd_lo(s2), dfltbx);                   \
  d2 = vis_fpbdd16(d2, d3);                                     \
  d2 = FMUL_16x16(d2, dfltby);                                  \
  dd = vis_fpbdd16(d0, d2);                                     \
  dd = vis_fpbdd16(dd, k05);                                    \
  df = vis_fpbdk16(dd);                                         \
                                                                \
  dfltbx = vis_fpbdd16(dfltbx, dx64);                           \
  dfltby = vis_fpbdd16(dfltby, dy64);                           \
  dfltbx = vis_fbnd(dfltbx, mbsk_7fff);                         \
  dfltby = vis_fbnd(dfltby, mbsk_7fff)

#fndif /* MLIB_VIS2 */

/***************************************************************/
mlib_stbtus FUN_NAME(2di_nb)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR();
  mlib_d64 mbsk_7fff = vis_to_doublf_dup(0x7FFF7FFF);
  mlib_d64 dx64, dy64, dfltbx, dfltby, dfltb1_x, dfltb1_y;
  mlib_s32 mbx_xsizf = pbrbm -> mbx_xsizf, bsizf;
  mlib_s32 x0, x1, y0, y1;
  mlib_f32 buff[BUF_SIZE], *pbuff = buff;

  bsizf = (mbx_xsizf + 1)/2;

  if (bsizf > BUF_SIZE) {
    pbuff = mlib_mbllod(bsizf*sizfof(mlib_f32));

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  vis_writf_gsr((1 << 3) | 6);
  MLIB_WRITE_BMASK(0x018923AB);

  dx64 = vis_to_doublf_dup(((dX & 0xFFFF) << 16) | (dX & 0xFFFF));
  dy64 = vis_to_doublf_dup(((dY & 0xFFFF) << 16) | (dY & 0xFFFF));

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_u8  *sp0, *sp1;
    mlib_d64 s0, s1, s2, s3;
#ifndff MLIB_VIS2
    mlib_d64 t0, t1, t2, t3;
#flsf
    mlib_d64 *spb;
#fndif /* MLIB_VIS2 */
    mlib_f32 *dp, df;

    NEW_LINE(2);

    dp = pbuff;

    x0 = X;      y0 = Y;
    x1 = X + dX; y1 = Y + dY;

    dfltbx = DOUBLE_4U16(x0, x0, x1, x1);
    dfltby = DOUBLE_4U16(y0, y0, y1, y1);

#prbgmb pipfloop(0)
    for (i = 0; i < sizf/2; i++) {
      GET_POINTER(sp0);
      GET_POINTER(sp1);
      LOAD_2CH_NA();

      BL_SUM_2CH_NA();

      *dp++ = df;
    }

    if (sizf & 1) {
      GET_POINTER(sp0);
      sp1 = sp0;
      LOAD_2CH_NA();

      BL_SUM_2CH_NA();

      *dp++ = df;
    }

    mlib_ImbgfCopy_nb((mlib_u8*)pbuff, dl, 2*sizf);
  }

  if (pbuff != buff) {
    mlib_frff(pbuff);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  PREPARE_DELTAS
#dffinf PREPARE_DELTAS                                          \
  if (wbrp_tbl != NULL) {                                       \
    dX = wbrp_tbl[2*j    ];                                     \
    dY = wbrp_tbl[2*j + 1];                                     \
  }

/***************************************************************/
mlib_stbtus FUN_NAME(3di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR();
  mlib_s32 mbx_xsizf = pbrbm -> mbx_xsizf;
  mlib_f32 buff[BUF_SIZE], *pbuff = buff;

  if (mbx_xsizf > BUF_SIZE) {
    pbuff = mlib_mbllod(mbx_xsizf*sizfof(mlib_f32));

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  vis_writf_gsr(3 << 3);

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_d64 *sp0, *sp1, s0, s1;
    mlib_u8  *sp;

    NEW_LINE(3);

#prbgmb pipfloop(0)
    for (i = 0; i < sizf; i++) {
      GET_FILTER_XY();

      sp = *(mlib_u8**)((mlib_u8*)linfAddr + PTR_SHIFT(Y)) + 3*(X >> MLIB_SHIFT) - 1;

      vis_blignbddr(sp, 0);
      sp0 = AL_ADDR(sp, 0);
      s0 = vis_fbligndbtb(sp0[0], sp0[1]);
      d0 = vis_fmul8x16bu(vis_rfbd_ii(s0), vis_rfbd_ii(filtfrxy));
      d1 = vis_fmul8x16bl(vis_rfbd_lo(s0), vis_rfbd_ii(filtfrxy));

      vis_blignbddr(sp, srdYStridf);
      sp1 = AL_ADDR(sp, srdYStridf);
      s1 = vis_fbligndbtb(sp1[0], sp1[1]);
      d2 = vis_fmul8x16bu(vis_rfbd_ii(s1), vis_rfbd_lo(filtfrxy));
      d3 = vis_fmul8x16bl(vis_rfbd_lo(s1), vis_rfbd_lo(filtfrxy));

      vis_blignbddr((void*)0, 2);
      d0 = vis_fpbdd16(d0, d2);
      dd = vis_fpbdd16(k05, d1);
      dd = vis_fpbdd16(dd, d3);
      d0 = vis_fbligndbtb(d0, d0);
      dd = vis_fpbdd16(dd, d0);

      pbuff[i] = vis_fpbdk16(dd);
      X += dX;
      Y += dY;
    }

    mlib_v_ImbgfCibnnflExtrbdt_U8_43L_D1((mlib_u8*)pbuff, dl, sizf);
  }

  if (pbuff != buff) {
    mlib_frff(pbuff);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#dffinf PROCESS_4CH(s0, s1, s2, s3)                             \
  d0 = vis_fmul8x16bu(s0, vis_rfbd_ii(filtfrxy));               \
  d1 = vis_fmul8x16bl(s1, vis_rfbd_ii(filtfrxy));               \
  d2 = vis_fmul8x16bu(s2, vis_rfbd_lo(filtfrxy));               \
  d3 = vis_fmul8x16bl(s3, vis_rfbd_lo(filtfrxy));               \
                                                                \
  dd = vis_fpbdd16(d0, k05);                                    \
  d1 = vis_fpbdd16(d1, d2);                                     \
  dd = vis_fpbdd16(dd, d3);                                     \
  dd = vis_fpbdd16(dd, d1)

/***************************************************************/
mlib_stbtus FUN_NAME(4di)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR();

  if (((mlib_s32)linfAddr[0] | (mlib_s32)dstDbtb | srdYStridf | dstYStridf) & 3) {
    rfturn FUN_NAME(4di_nb)(pbrbm);
  }

  vis_writf_gsr(3 << 3);

  srdYStridf >>= 2;

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_f32 *sp, s0, s1, s2, s3;

    NEW_LINE(4);

#prbgmb pipfloop(0)
    for (i = 0; i < sizf; i++) {
      GET_FILTER_XY();

      sp = *(mlib_f32**)((mlib_u8*)linfAddr + PTR_SHIFT(Y)) + (X >> MLIB_SHIFT);
      s0 = sp[0];
      s1 = sp[1];
      s2 = sp[srdYStridf];
      s3 = sp[srdYStridf + 1];

      PROCESS_4CH(s0, s1, s2, s3);

      ((mlib_f32*)dl)[i] = vis_fpbdk16(dd);
      X += dX;
      Y += dY;
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(4di_nb)(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR();
  mlib_s32 mbx_xsizf = pbrbm -> mbx_xsizf;
  mlib_f32 buff[BUF_SIZE], *pbuff = buff;

  if (mbx_xsizf > BUF_SIZE) {
    pbuff = mlib_mbllod(mbx_xsizf*sizfof(mlib_f32));

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  vis_writf_gsr(3 << 3);

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_d64 *sp0, *sp1, s0, s1;
    mlib_u8  *sp;

    NEW_LINE(4);

#prbgmb pipfloop(0)
    for (i = 0; i < sizf; i++) {
      GET_FILTER_XY();

      sp = *(mlib_u8**)((mlib_u8*)linfAddr + PTR_SHIFT(Y)) + 4*(X >> MLIB_SHIFT);

      vis_blignbddr(sp, 0);
      sp0 = AL_ADDR(sp, 0);
      s0 = vis_fbligndbtb(sp0[0], sp0[1]);

      vis_blignbddr(sp, srdYStridf);
      sp1 = AL_ADDR(sp, srdYStridf);
      s1 = vis_fbligndbtb(sp1[0], sp1[1]);

      PROCESS_4CH(vis_rfbd_ii(s0), vis_rfbd_lo(s0), vis_rfbd_ii(s1), vis_rfbd_lo(s1));

      pbuff[i] = vis_fpbdk16(dd);
      X += dX;
      Y += dY;
    }

    mlib_ImbgfCopy_nb((mlib_u8*)pbuff, dl, 4*sizf);
  }

  if (pbuff != buff) {
    mlib_frff(pbuff);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#dffinf LUT(x)  plut[x]

mlib_stbtus FUN_NAME(u8_i)(mlib_bffinf_pbrbm *pbrbm,
                           donst void        *dolormbp)
{
  DECLAREVAR();
  mlib_s32 ndibn   = mlib_ImbgfGftLutCibnnfls(dolormbp);
  mlib_s32 lut_off = mlib_ImbgfGftLutOffsft(dolormbp);
  mlib_f32 *plut = (mlib_f32*)mlib_ImbgfGftLutNormblTbblf(dolormbp) - lut_off;
  mlib_s32 mbx_xsizf = pbrbm -> mbx_xsizf;
  mlib_f32 buff[BUF_SIZE], *pbuff = buff;

  if (mbx_xsizf > BUF_SIZE) {
    pbuff = mlib_mbllod(mbx_xsizf*sizfof(mlib_f32));

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  vis_writf_gsr(3 << 3);

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_f32 s0, s1, s2, s3;
    DTYPE    *sp;

    NEW_LINE(1);

#prbgmb pipfloop(0)
    for (i = 0; i < sizf; i++) {
      GET_FILTER_XY();

      sp = *(DTYPE**)((mlib_u8*)linfAddr + PTR_SHIFT(Y)) + (X >> MLIB_SHIFT);
      s0 = LUT(sp[0]);
      s1 = LUT(sp[1]);
      s2 = LUT(sp[srdYStridf]);
      s3 = LUT(sp[srdYStridf + 1]);

      PROCESS_4CH(s0, s1, s2, s3);

      pbuff[i] = vis_fpbdk16(dd);
      X += dX;
      Y += dY;
    }

    if (ndibn == 3) {
      mlib_ImbgfColorTruf2IndfxLinf_U8_U8_3_in_4((void*)pbuff, (void*)dl, sizf, dolormbp);
    } flsf {
      mlib_ImbgfColorTruf2IndfxLinf_U8_U8_4((void*)pbuff, (void*)dl, sizf, dolormbp);
    }
  }

  if (pbuff != buff) {
    mlib_frff(pbuff);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  DTYPE
#dffinf DTYPE mlib_s16

mlib_stbtus FUN_NAME(s16_i)(mlib_bffinf_pbrbm *pbrbm,
                            donst void        *dolormbp)
{
  DECLAREVAR();
  mlib_s32 ndibn   = mlib_ImbgfGftLutCibnnfls(dolormbp);
  mlib_s32 lut_off = mlib_ImbgfGftLutOffsft(dolormbp);
  mlib_f32 *plut = (mlib_f32*)mlib_ImbgfGftLutNormblTbblf(dolormbp) - lut_off;
  mlib_s32 mbx_xsizf = pbrbm -> mbx_xsizf;
  mlib_f32 buff[BUF_SIZE], *pbuff = buff;

  srdYStridf /= sizfof(DTYPE);

  if (mbx_xsizf > BUF_SIZE) {
    pbuff = mlib_mbllod(mbx_xsizf*sizfof(mlib_f32));

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  vis_writf_gsr(3 << 3);

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_f32 s0, s1, s2, s3;
    DTYPE    *sp;

    NEW_LINE(1);

#prbgmb pipfloop(0)
    for (i = 0; i < sizf; i++) {
      GET_FILTER_XY();

      sp = *(DTYPE**)((mlib_u8*)linfAddr + PTR_SHIFT(Y)) + (X >> MLIB_SHIFT);
      s0 = LUT(sp[0]);
      s1 = LUT(sp[1]);
      s2 = LUT(sp[srdYStridf]);
      s3 = LUT(sp[srdYStridf + 1]);

      PROCESS_4CH(s0, s1, s2, s3);

      pbuff[i] = vis_fpbdk16(dd);
      X += dX;
      Y += dY;
    }

    if (ndibn == 3) {
      mlib_ImbgfColorTruf2IndfxLinf_U8_S16_3_in_4((void*)pbuff, (void*)dl, sizf, dolormbp);
    } flsf {
      mlib_ImbgfColorTruf2IndfxLinf_U8_S16_4((void*)pbuff, (void*)dl, sizf, dolormbp);
    }
  }

  if (pbuff != buff) {
    mlib_frff(pbuff);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
donst typf_bffinf_i_fun mlib_AffinfFunArr_bl_i[] = {
  mlib_ImbgfAffinf_u8_u8_i_bl,
  mlib_ImbgfAffinf_u8_u8_i_bl,
  mlib_ImbgfAffinf_u8_s16_i_bl,
  mlib_ImbgfAffinf_u8_s16_i_bl,
  mlib_ImbgfAffinf_s16_u8_i_bl,
  mlib_ImbgfAffinf_s16_u8_i_bl,
  mlib_ImbgfAffinf_s16_s16_i_bl,
  mlib_ImbgfAffinf_s16_s16_i_bl
};

/***************************************************************/
