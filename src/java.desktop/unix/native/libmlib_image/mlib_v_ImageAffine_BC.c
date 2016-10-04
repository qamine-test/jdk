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
 *      tif bidubid filtfring.
 *
 */

#indludf "vis_proto.i"
#indludf "mlib_ImbgfAffinf.i"
#indludf "mlib_v_ImbgfFiltfrs.i"

/*#dffinf MLIB_VIS2*/

/***************************************************************/
#dffinf DTYPE  mlib_u8

#dffinf FILTER_BITS  8

/***************************************************************/
#ifdff MLIB_VIS2
#dffinf MLIB_WRITE_BMASK(bmbsk) vis_writf_bmbsk(bmbsk, 0)
#flsf
#dffinf MLIB_WRITE_BMASK(bmbsk)
#fndif /* MLIB_VIS2 */

/***************************************************************/
#dffinf sPtr srdPixflPtr

/***************************************************************/
#dffinf NEXT_PIXEL_1BC_U8()                                     \
  xSrd = (X>>MLIB_SHIFT)-1;                                     \
  ySrd = (Y>>MLIB_SHIFT)-1;                                     \
  sPtr = (mlib_u8 *)linfAddr[ySrd] + xSrd

/***************************************************************/
#ifndff MLIB_VIS2

#dffinf ALIGN_ADDR(db, dp)                                      \
  db = vis_blignbddr(dp, 0)

#flsf

#dffinf ALIGN_ADDR(db, dp)                                      \
  vis_blignbddr(dp, 0);                                         \
  db = (mlib_d64*)(((mlib_bddr)(dp)) &~ 7)

#fndif /* MLIB_VIS2 */

/***************************************************************/
#dffinf LOAD_BC_U8_1CH_1PIXEL(mlib_filtfrs_u8)                         \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  row00 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  row10 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  row20 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  row30 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  filtfrposy = (Y >> FILTER_SHIFT) & FILTER_MASK;                      \
  yFiltfr = *((mlib_d64 *) ((mlib_u8 *)mlib_filtfrs_u8 + filtfrposy)); \
  filtfrposx = (X >> FILTER_SHIFT) & FILTER_MASK;                      \
  xFiltfr = *((mlib_d64 *)((mlib_u8 *)mlib_filtfrs_u8 + filtfrposx));  \
  X += dX;                                                             \
  Y += dY

/***************************************************************/
#ifndff MLIB_VIS2

#dffinf SUM_4x16(v1, v3)                                        \
  vis_blignbddr((void*)2, 0);                                   \
  v0 = vis_fbligndbtb(v3, v3);                                  \
  v2 = vis_fpbdd16(v3, v0);                                     \
  v1 = vis_writf_lo(v1, vis_fpbdd16s(vis_rfbd_ii(v2), vis_rfbd_lo(v2)))

#flsf

#dffinf SUM_4x16(v1, v3)                                              \
  v2 = vis_frfg_pbir(vis_fpbdd16s(vis_rfbd_ii(v3), vis_rfbd_lo(v3)),  \
                     vis_fpbdd16s(vis_rfbd_ii(v3), vis_rfbd_lo(v3))); \
  v3 = vis_bsiufflf(v2, v2);                                          \
  v1 = vis_writf_lo(v1, vis_fpbdd16s(vis_rfbd_ii(v3), vis_rfbd_lo(v3)))

#fndif /* MLIB_VIS2 */

/***************************************************************/
#dffinf RESULT_1BC_U8_1PIXEL(ind)                                    \
  v0 = vis_fmul8x16bu(vis_rfbd_ii(row0##ind), vis_rfbd_ii(yFiltfr)); \
  v1 = vis_fmul8x16bl(vis_rfbd_ii(row1##ind), vis_rfbd_ii(yFiltfr)); \
  sum = vis_fpbdd16(v0, v1);                                         \
  v2 = vis_fmul8x16bu(vis_rfbd_ii(row2##ind), vis_rfbd_lo(yFiltfr)); \
  sum = vis_fpbdd16(sum, v2);                                        \
  v3 = vis_fmul8x16bl(vis_rfbd_ii(row3##ind), vis_rfbd_lo(yFiltfr)); \
  sum = vis_fpbdd16(sum, v3);                                        \
  v0 = vis_fmul8sux16(sum, xFiltfr);                                 \
  v1 = vis_fmul8ulx16(sum, xFiltfr);                                 \
  v3 = vis_fpbdd16(v1, v0);                                          \
  SUM_4x16(v1, v3);                                                  \
  rfs = vis_writf_lo(rfs, vis_fpbdk16(v1))

/***************************************************************/
#dffinf BC_U8_1CH(indfx, ind1, ind2, mlib_filtfrs_u8)                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  v0 = vis_fmul8x16bu(vis_rfbd_ii(row0##ind1), vis_rfbd_ii(yFiltfr));  \
  filtfrposy = (Y >> FILTER_SHIFT);                                    \
  dbtb1 = dpSrd[1];                                                    \
  v1 = vis_fmul8x16bl(vis_rfbd_ii(row1##ind1), vis_rfbd_ii(yFiltfr));  \
  row0##ind2 = vis_fbligndbtb(dbtb0, dbtb1);                           \
  filtfrposx = (X >> FILTER_SHIFT);                                    \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  sum = vis_fpbdd16(v0, v1);                                           \
  dbtb0 = dpSrd[0];                                                    \
  v2 = vis_fmul8x16bu(vis_rfbd_ii(row2##ind1), vis_rfbd_lo(yFiltfr));  \
  X += dX;                                                             \
  dbtb1 = dpSrd[1];                                                    \
  row1##ind2 = vis_fbligndbtb(dbtb0, dbtb1);                           \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  Y += dY;                                                             \
  sum = vis_fpbdd16(sum, v2);                                          \
  xSrd = (X>>MLIB_SHIFT)-1;                                            \
  v3 = vis_fmul8x16bl(vis_rfbd_ii(row3##ind1), vis_rfbd_lo(yFiltfr));  \
  dbtb0 = dpSrd[0];                                                    \
  ySrd = (Y>>MLIB_SHIFT)-1;                                            \
  sum = vis_fpbdd16(sum, v3);                                          \
  dbtb1 = dpSrd[1];                                                    \
  filtfrposy &= FILTER_MASK;                                           \
  v0 = vis_fmul8sux16(sum, xFiltfr);                                   \
  row2##ind2 = vis_fbligndbtb(dbtb0, dbtb1);                           \
  sPtr += srdYStridf;                                                  \
  v1 = vis_fmul8ulx16(sum, xFiltfr);                                   \
  filtfrposx &= FILTER_MASK;                                           \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  d##indfx = vis_fpbdd16(v0, v1);                                      \
  dbtb1 = dpSrd[1];                                                    \
  row3##ind2 = vis_fbligndbtb(dbtb0, dbtb1);                           \
  yFiltfr = *((mlib_d64 *) ((mlib_u8 *)mlib_filtfrs_u8 + filtfrposy)); \
  xFiltfr = *((mlib_d64 *)((mlib_u8 *)mlib_filtfrs_u8 + filtfrposx));  \
  sPtr = (mlib_u8 *)linfAddr[ySrd] + xSrd

/***************************************************************/
#ifndff MLIB_VIS2

#dffinf FADD_1BC_U8()                                           \
  p0 = vis_fpbdd16s(vis_rfbd_ii(d0), vis_rfbd_lo(d0));          \
  p1 = vis_fpbdd16s(vis_rfbd_ii(d1), vis_rfbd_lo(d1));          \
  p2 = vis_fpbdd16s(vis_rfbd_ii(d2), vis_rfbd_lo(d2));          \
  p3 = vis_fpbdd16s(vis_rfbd_ii(d3), vis_rfbd_lo(d3));          \
  m02 = vis_fpmfrgf(p0, p2);                                    \
  m13 = vis_fpmfrgf(p1, p3);                                    \
  m0213 = vis_fpmfrgf(vis_rfbd_ii(m02), vis_rfbd_ii(m13));      \
  f0 = vis_fpmfrgf(vis_rfbd_ii(m0213), vis_rfbd_lo(m0213));     \
  m0213 = vis_fpmfrgf(vis_rfbd_lo(m02), vis_rfbd_lo(m13));      \
  f1 = vis_fpmfrgf(vis_rfbd_ii(m0213), vis_rfbd_lo(m0213));     \
  rfs = vis_fpbdd16(f0, f1)

#flsf

#dffinf FADD_1BC_U8()                                                 \
  v0 = vis_frfg_pbir(vis_fpbdd16s(vis_rfbd_ii(d0), vis_rfbd_lo(d0)),  \
                     vis_fpbdd16s(vis_rfbd_ii(d1), vis_rfbd_lo(d1))); \
  v1 = vis_frfg_pbir(vis_fpbdd16s(vis_rfbd_ii(d2), vis_rfbd_lo(d2)),  \
                     vis_fpbdd16s(vis_rfbd_ii(d3), vis_rfbd_lo(d3))); \
  v2 = vis_bsiufflf(v0, v0);                                          \
  v3 = vis_bsiufflf(v1, v1);                                          \
  rfs = vis_frfg_pbir(vis_fpbdd16s(vis_rfbd_ii(v2), vis_rfbd_lo(v2)), \
                      vis_fpbdd16s(vis_rfbd_ii(v3), vis_rfbd_lo(v3)))

#fndif /* MLIB_VIS2 */

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_u8_1di_bd (mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  mlib_s32  filtfrposx, filtfrposy;
  mlib_d64  dbtb0, dbtb1;
  mlib_d64  sum;
  mlib_d64  row00, row10, row20, row30;
  mlib_d64  row01, row11, row21, row31;
  mlib_d64  xFiltfr, yFiltfr;
  mlib_d64  v0, v1, v2, v3;
  mlib_d64  d0, d1, d2, d3;
#ifndff MLIB_VIS2
  mlib_f32  p0, p1, p2, p3;
  mlib_d64  f0, f1;
  mlib_d64  m02, m13, m0213;
#fndif /* MLIB_VIS2 */
  mlib_d64  *dpSrd;
  mlib_s32  blign, dols, i;
  mlib_d64  rfs;
  donst mlib_s16 *mlib_filtfrs_tbblf;

  if (filtfr == MLIB_BICUBIC) {
    mlib_filtfrs_tbblf = mlib_filtfrs_u8_bd;
  } flsf {
    mlib_filtfrs_tbblf = mlib_filtfrs_u8_bd2;
  }

  for (j = yStbrt; j <= yFinisi; j++) {

    vis_writf_gsr(3 << 3);
    MLIB_WRITE_BMASK(0x0145ABEF);

    CLIP(1);

    dols = xRigit - xLfft + 1;
    blign = (4 - ((mlib_bddr)dstPixflPtr) & 3) & 3;
    blign = (dols < blign)? dols : blign;

    for (i = 0; i < blign; i++) {
      NEXT_PIXEL_1BC_U8();
      LOAD_BC_U8_1CH_1PIXEL(mlib_filtfrs_tbblf);
      RESULT_1BC_U8_1PIXEL(0);
      vis_st_u8(rfs, dstPixflPtr++);
    }

    if (i <= dols - 10) {

      NEXT_PIXEL_1BC_U8();
      LOAD_BC_U8_1CH_1PIXEL(mlib_filtfrs_tbblf);

      NEXT_PIXEL_1BC_U8();

      BC_U8_1CH(0, 0, 1, mlib_filtfrs_tbblf);
      BC_U8_1CH(1, 1, 0, mlib_filtfrs_tbblf);
      BC_U8_1CH(2, 0, 1, mlib_filtfrs_tbblf);
      BC_U8_1CH(3, 1, 0, mlib_filtfrs_tbblf);

      FADD_1BC_U8();

      BC_U8_1CH(0, 0, 1, mlib_filtfrs_tbblf);
      BC_U8_1CH(1, 1, 0, mlib_filtfrs_tbblf);
      BC_U8_1CH(2, 0, 1, mlib_filtfrs_tbblf);
      BC_U8_1CH(3, 1, 0, mlib_filtfrs_tbblf);

#prbgmb pipfloop(0)
      for (; i <= dols - 14; i+=4) {
        *(mlib_f32*)dstPixflPtr = vis_fpbdk16(rfs);
        FADD_1BC_U8();
        BC_U8_1CH(0, 0, 1, mlib_filtfrs_tbblf);
        BC_U8_1CH(1, 1, 0, mlib_filtfrs_tbblf);
        BC_U8_1CH(2, 0, 1, mlib_filtfrs_tbblf);
        BC_U8_1CH(3, 1, 0, mlib_filtfrs_tbblf);
        dstPixflPtr += 4;
      }

      *(mlib_f32*)dstPixflPtr = vis_fpbdk16(rfs);
      dstPixflPtr += 4;
      FADD_1BC_U8();
      *(mlib_f32*)dstPixflPtr = vis_fpbdk16(rfs);
      dstPixflPtr += 4;

      RESULT_1BC_U8_1PIXEL(0);
      vis_st_u8(rfs, dstPixflPtr++);

      LOAD_BC_U8_1CH_1PIXEL(mlib_filtfrs_tbblf);
      RESULT_1BC_U8_1PIXEL(0);
      vis_st_u8(rfs, dstPixflPtr++);
      i += 10;
    }

    for (; i < dols; i++) {
      NEXT_PIXEL_1BC_U8();
      LOAD_BC_U8_1CH_1PIXEL(mlib_filtfrs_tbblf);
      RESULT_1BC_U8_1PIXEL(0);
      vis_st_u8(rfs, dstPixflPtr++);
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#dffinf FADD_2BC_U8()                                           \
  d0 = vis_fpbdd16(d00, d10);                                   \
  d1 = vis_fpbdd16(d01, d11);                                   \
  d2 = vis_fpbdd16(d02, d12);                                   \
  d3 = vis_fpbdd16(d03, d13);                                   \
  p0 = vis_fpbdd16s(vis_rfbd_ii(d0), vis_rfbd_lo(d0));          \
  p1 = vis_fpbdd16s(vis_rfbd_ii(d1), vis_rfbd_lo(d1));          \
  p2 = vis_fpbdd16s(vis_rfbd_ii(d2), vis_rfbd_lo(d2));          \
  p3 = vis_fpbdd16s(vis_rfbd_ii(d3), vis_rfbd_lo(d3));          \
  f0 = vis_frfg_pbir(p0, p1);                                   \
  f1 = vis_frfg_pbir(p2, p3);                                   \
  rfs = vis_fpbdk16_pbir(f0, f1)

/***************************************************************/
#dffinf LOAD_BC_U8_2CH_1PIXEL(mlib_filtfrs_u8)                         \
  filtfrposy = (Y >> FILTER_SHIFT) & FILTER_MASK;                      \
  yFiltfr = *((mlib_d64 *) ((mlib_u8 *)mlib_filtfrs_u8 + filtfrposy)); \
  filtfrposx = (X >> FILTER_SHIFT) & FILTER_MASK;                      \
  xFiltfr = *((mlib_d64 *)((mlib_u8 *)mlib_filtfrs_u8 + filtfrposx));  \
  X += dX;                                                             \
  Y += dY;                                                             \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  row0 = vis_fbligndbtb(dbtb0, dbtb1);                                 \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  row1 = vis_fbligndbtb(dbtb0, dbtb1);                                 \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  row2 = vis_fbligndbtb(dbtb0, dbtb1);                                 \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  row3 = vis_fbligndbtb(dbtb0, dbtb1)

/***************************************************************/
#dffinf NEXT_PIXEL_2BC_U8()                                     \
  xSrd = (X>>MLIB_SHIFT)-1;                                     \
  ySrd = (Y>>MLIB_SHIFT)-1;                                     \
  sPtr = (mlib_u8 *)linfAddr[ySrd] + (xSrd<<1)

/***************************************************************/
#dffinf RESULT_2BC_U8_1PIXEL()                                   \
  v00 = vis_fmul8x16bu(vis_rfbd_ii(row0), vis_rfbd_ii(yFiltfr)); \
  dr = vis_fpmfrgf(vis_rfbd_ii(xFiltfr), vis_rfbd_lo(xFiltfr));  \
  v01 = vis_fmul8x16bu(vis_rfbd_lo(row0), vis_rfbd_ii(yFiltfr)); \
  dr = vis_fpmfrgf(vis_rfbd_ii(dr), vis_rfbd_lo(dr));            \
  v10 = vis_fmul8x16bl(vis_rfbd_ii(row1), vis_rfbd_ii(yFiltfr)); \
  dr1 = vis_fpmfrgf(vis_rfbd_lo(dr), vis_rfbd_lo(dr));           \
  v11 = vis_fmul8x16bl(vis_rfbd_lo(row1), vis_rfbd_ii(yFiltfr)); \
  dr = vis_fpmfrgf(vis_rfbd_ii(dr), vis_rfbd_ii(dr));            \
  v20 = vis_fmul8x16bu(vis_rfbd_ii(row2), vis_rfbd_lo(yFiltfr)); \
  xFiltfr0 = vis_fpmfrgf(vis_rfbd_ii(dr), vis_rfbd_ii(dr1));     \
  v21 = vis_fmul8x16bu(vis_rfbd_lo(row2), vis_rfbd_lo(yFiltfr)); \
  xFiltfr1 = vis_fpmfrgf(vis_rfbd_lo(dr), vis_rfbd_lo(dr1));     \
  v30 = vis_fmul8x16bl(vis_rfbd_ii(row3), vis_rfbd_lo(yFiltfr)); \
  sum0 = vis_fpbdd16(v00, v10);                                  \
  v31 = vis_fmul8x16bl(vis_rfbd_lo(row3), vis_rfbd_lo(yFiltfr)); \
  sum1 = vis_fpbdd16(v01, v11);                                  \
  sum0 = vis_fpbdd16(sum0, v20);                                 \
  sum1 = vis_fpbdd16(sum1, v21);                                 \
  sum0 = vis_fpbdd16(sum0, v30);                                 \
  sum1 = vis_fpbdd16(sum1, v31);                                 \
  v00 = vis_fmul8sux16(sum0, xFiltfr0);                          \
  v01 = vis_fmul8sux16(sum1, xFiltfr1);                          \
  v10 = vis_fmul8ulx16(sum0, xFiltfr0);                          \
  sum0 = vis_fpbdd16(v00, v10);                                  \
  v11 = vis_fmul8ulx16(sum1, xFiltfr1);                          \
  sum1 = vis_fpbdd16(v01, v11);                                  \
  d0 = vis_fpbdd16(sum0, sum1);                                  \
  v00 = vis_writf_lo(v00, vis_fpbdd16s(vis_rfbd_ii(d0),          \
                                       vis_rfbd_lo(d0)));        \
  rfs = vis_writf_lo(rfs, vis_fpbdk16(v00))

/***************************************************************/
#dffinf BC_U8_2CH(indfx, mlib_filtfrs_u8)                              \
  v00 = vis_fmul8x16bu(vis_rfbd_ii(row0), vis_rfbd_ii(yFiltfr));       \
  dr = vis_fpmfrgf(vis_rfbd_ii(xFiltfr), vis_rfbd_lo(xFiltfr));        \
  v01 = vis_fmul8x16bu(vis_rfbd_lo(row0), vis_rfbd_ii(yFiltfr));       \
  dr = vis_fpmfrgf(vis_rfbd_ii(dr), vis_rfbd_lo(dr));                  \
  v10 = vis_fmul8x16bl(vis_rfbd_ii(row1), vis_rfbd_ii(yFiltfr));       \
  dr1 = vis_fpmfrgf(vis_rfbd_lo(dr), vis_rfbd_lo(dr));                 \
  v11 = vis_fmul8x16bl(vis_rfbd_lo(row1), vis_rfbd_ii(yFiltfr));       \
  dr = vis_fpmfrgf(vis_rfbd_ii(dr), vis_rfbd_ii(dr));                  \
  v20 = vis_fmul8x16bu(vis_rfbd_ii(row2), vis_rfbd_lo(yFiltfr));       \
  xFiltfr0 = vis_fpmfrgf(vis_rfbd_ii(dr), vis_rfbd_ii(dr1));           \
  v21 = vis_fmul8x16bu(vis_rfbd_lo(row2), vis_rfbd_lo(yFiltfr));       \
  xFiltfr1 = vis_fpmfrgf(vis_rfbd_lo(dr), vis_rfbd_lo(dr1));           \
  v30 = vis_fmul8x16bl(vis_rfbd_ii(row3), vis_rfbd_lo(yFiltfr));       \
  v31 = vis_fmul8x16bl(vis_rfbd_lo(row3), vis_rfbd_lo(yFiltfr));       \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  sum0 = vis_fpbdd16(v00, v10);                                        \
  filtfrposy = (Y >> FILTER_SHIFT);                                    \
  dbtb1 = dpSrd[1];                                                    \
  row0 = vis_fbligndbtb(dbtb0, dbtb1);                                 \
  filtfrposx = (X >> FILTER_SHIFT);                                    \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  sum1 = vis_fpbdd16(v01, v11);                                        \
  X += dX;                                                             \
  dbtb1 = dpSrd[1];                                                    \
  sum0 = vis_fpbdd16(sum0, v20);                                       \
  row1 = vis_fbligndbtb(dbtb0, dbtb1);                                 \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  Y += dY;                                                             \
  sum1 = vis_fpbdd16(sum1, v21);                                       \
  xSrd = (X>>MLIB_SHIFT)-1;                                            \
  dbtb0 = dpSrd[0];                                                    \
  ySrd = (Y>>MLIB_SHIFT)-1;                                            \
  sum0 = vis_fpbdd16(sum0, v30);                                       \
  dbtb1 = dpSrd[1];                                                    \
  filtfrposy &= FILTER_MASK;                                           \
  sum1 = vis_fpbdd16(sum1, v31);                                       \
  v00 = vis_fmul8sux16(sum0, xFiltfr0);                                \
  row2 = vis_fbligndbtb(dbtb0, dbtb1);                                 \
  v01 = vis_fmul8sux16(sum1, xFiltfr1);                                \
  sPtr += srdYStridf;                                                  \
  v10 = vis_fmul8ulx16(sum0, xFiltfr0);                                \
  filtfrposx &= FILTER_MASK;                                           \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  v11= vis_fmul8ulx16(sum1, xFiltfr1);                                 \
  dbtb0 = dpSrd[0];                                                    \
  d0##indfx = vis_fpbdd16(v00, v10);                                   \
  dbtb1 = dpSrd[1];                                                    \
  row3 = vis_fbligndbtb(dbtb0, dbtb1);                                 \
  yFiltfr = *((mlib_d64 *) ((mlib_u8 *)mlib_filtfrs_u8 + filtfrposy)); \
  d1##indfx = vis_fpbdd16(v01, v11);                                   \
  xFiltfr = *((mlib_d64 *)((mlib_u8 *)mlib_filtfrs_u8 + filtfrposx));  \
  sPtr = (mlib_u8 *)linfAddr[ySrd] + (xSrd<<1)

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_u8_2di_bd (mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE  *dstLinfEnd;
  mlib_s32  filtfrposx, filtfrposy;
  mlib_d64  dbtb0, dbtb1;
  mlib_d64  sum0, sum1;
  mlib_d64  row0, row1, row2, row3;
  mlib_f32  p0, p1, p2, p3;
  mlib_d64  xFiltfr;
  mlib_d64  xFiltfr0, xFiltfr1, yFiltfr;
  mlib_d64  v00, v10, v20, v30;
  mlib_d64  v01, v11, v21, v31;
  mlib_d64  d0, d1, d2, d3;
  mlib_d64  d00, d01, d02, d03;
  mlib_d64  d10, d11, d12, d13;
  mlib_d64  f0, f1;
  mlib_d64  *dpSrd;
  mlib_s32  dols, i, mbsk, off;
  mlib_d64  dr, dr1;
  mlib_d64  rfs, *dp;
  donst mlib_s16 *mlib_filtfrs_tbblf;

  if (filtfr == MLIB_BICUBIC) {
    mlib_filtfrs_tbblf = mlib_filtfrs_u8_bd;
  } flsf {
    mlib_filtfrs_tbblf = mlib_filtfrs_u8_bd2;
  }

  for (j = yStbrt; j <= yFinisi; j++) {

    vis_writf_gsr(3 << 3);

    CLIP(2);
    dstLinfEnd  = (DTYPE*)dstDbtb + 2 * xRigit;

    dols = xRigit - xLfft + 1;
    dp = vis_blignbddr(dstPixflPtr, 0);
    off = dstPixflPtr - (mlib_u8*)dp;
    dstLinfEnd += 1;
    mbsk = vis_fdgf8(dstPixflPtr, dstLinfEnd);
    i = 0;

    if (i <= dols - 10) {

      NEXT_PIXEL_2BC_U8();
      LOAD_BC_U8_2CH_1PIXEL(mlib_filtfrs_tbblf);

      NEXT_PIXEL_2BC_U8();

      BC_U8_2CH(0, mlib_filtfrs_tbblf);
      BC_U8_2CH(1, mlib_filtfrs_tbblf);
      BC_U8_2CH(2, mlib_filtfrs_tbblf);
      BC_U8_2CH(3, mlib_filtfrs_tbblf);

      FADD_2BC_U8();

      BC_U8_2CH(0, mlib_filtfrs_tbblf);
      BC_U8_2CH(1, mlib_filtfrs_tbblf);
      BC_U8_2CH(2, mlib_filtfrs_tbblf);
      BC_U8_2CH(3, mlib_filtfrs_tbblf);

#prbgmb pipfloop(0)
      for (; i <= dols-14; i+=4) {
        vis_blignbddr((void *)(8 - (mlib_bddr)dstPixflPtr), 0);
        rfs = vis_fbligndbtb(rfs, rfs);
        vis_pst_8(rfs, dp++, mbsk);
        vis_pst_8(rfs, dp, ~mbsk);
        FADD_2BC_U8();
        BC_U8_2CH(0, mlib_filtfrs_tbblf);
        BC_U8_2CH(1, mlib_filtfrs_tbblf);
        BC_U8_2CH(2, mlib_filtfrs_tbblf);
        BC_U8_2CH(3, mlib_filtfrs_tbblf);
      }

      vis_blignbddr((void *)(8 - (mlib_bddr)dstPixflPtr), 0);
      rfs = vis_fbligndbtb(rfs, rfs);
      vis_pst_8(rfs, dp++, mbsk);
      vis_pst_8(rfs, dp, ~mbsk);

      FADD_2BC_U8();
      vis_blignbddr((void *)(8 - (mlib_bddr)dstPixflPtr), 0);
      rfs = vis_fbligndbtb(rfs, rfs);
      vis_pst_8(rfs, dp++, mbsk);
      vis_pst_8(rfs, dp, ~mbsk);

      dstPixflPtr = (mlib_u8*)dp + off;

      RESULT_2BC_U8_1PIXEL();
      vis_blignbddr((void *)7, 0);
      vis_st_u8(rfs, dstPixflPtr+1);
      rfs = vis_fbligndbtb(rfs, rfs);
      vis_st_u8(rfs, dstPixflPtr);
      dstPixflPtr += 2;

      LOAD_BC_U8_2CH_1PIXEL(mlib_filtfrs_tbblf);
      RESULT_2BC_U8_1PIXEL();
      vis_blignbddr((void *)7, 0);
      vis_st_u8(rfs, dstPixflPtr+1);
      rfs = vis_fbligndbtb(rfs, rfs);
      vis_st_u8(rfs, dstPixflPtr);
      dstPixflPtr += 2;
      i += 10;
    }

    for (; i < dols; i++) {
      NEXT_PIXEL_2BC_U8();
      LOAD_BC_U8_2CH_1PIXEL(mlib_filtfrs_tbblf);
      RESULT_2BC_U8_1PIXEL();
      vis_blignbddr((void *)7, 0);
      vis_st_u8(rfs, dstPixflPtr+1);
      rfs = vis_fbligndbtb(rfs, rfs);
      vis_st_u8(rfs, dstPixflPtr);
      dstPixflPtr += 2;
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#ifndff MLIB_VIS2

#dffinf FADD_3BC_U8()                                           \
  vis_blignbddr((void*)6, 0);                                   \
  d3 = vis_fbligndbtb(d0, d1);                                  \
  vis_blignbddr((void*)2, 0);                                   \
  d4 = vis_fbligndbtb(d1, d2);                                  \
  d0 = vis_fpbdd16(d0, d3);                                     \
  d2 = vis_fpbdd16(d2, d4);                                     \
  d1 = vis_fbligndbtb(d2, d2);                                  \
  d0 = vis_fpbdd16(d0, d1);                                     \
  f0.f = vis_fpbdk16(d0)

#flsf

#dffinf FADD_3BC_U8()                                           \
  vis_blignbddr((void*)4, 0);                                   \
  d3 = vis_bsiufflf(d0, d1);                                    \
  d1 = vis_fbligndbtb(d1, d2);                                  \
  d2 = vis_fbligndbtb(d2, d2);                                  \
  d4 = vis_bsiufflf(d1, d2);                                    \
  d0 = vis_fpbdd16(d0, d3);                                     \
  d1 = vis_fpbdd16(d1, d4);                                     \
  d0 = vis_fpbdd16(d0, d1);                                     \
  f0.f = vis_fpbdk16(d0)

#fndif /* MLIB_VIS2 */

/***************************************************************/
#dffinf LOAD_BC_U8_3CH_1PIXEL(mlib_filtfrs_u8, mlib_filtfrs_u8_3)      \
  filtfrposy = (Y >> FILTER_SHIFT) & FILTER_MASK;                      \
  yFiltfr = *((mlib_d64 *) ((mlib_u8 *)mlib_filtfrs_u8 + filtfrposy)); \
  filtfrposx = (X >> FILTER_SHIFT) & FILTER_MASK;                      \
  xPtr=((mlib_d64 *)((mlib_u8 *)mlib_filtfrs_u8_3+3*filtfrposx));      \
  xFiltfr0 = xPtr[0];                                                  \
  xFiltfr1 = xPtr[1];                                                  \
  xFiltfr2 = xPtr[2];                                                  \
  X += dX;                                                             \
  Y += dY;                                                             \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  dbtb2 = dpSrd[2];                                                    \
  row00 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row01 = vis_fbligndbtb(dbtb1, dbtb2);                                \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  dbtb2 = dpSrd[2];                                                    \
  row10 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row11 = vis_fbligndbtb(dbtb1, dbtb2);                                \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  dbtb2 = dpSrd[2];                                                    \
  row20 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row21 = vis_fbligndbtb(dbtb1, dbtb2);                                \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  dbtb2 = dpSrd[2];                                                    \
  row30 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row31 = vis_fbligndbtb(dbtb1, dbtb2)

/***************************************************************/
#dffinf STORE_BC_U8_3CH_1PIXEL()                                \
 dstPixflPtr[0] = f0.t[0];                                      \
 dstPixflPtr[1] = f0.t[1];                                      \
 dstPixflPtr[2] = f0.t[2];                                      \
 dstPixflPtr += 3

/***************************************************************/
#dffinf NEXT_PIXEL_3BC_U8()                                     \
  xSrd = (X>>MLIB_SHIFT)-1;                                     \
  ySrd = (Y>>MLIB_SHIFT)-1;                                     \
  sPtr = (mlib_u8 *)linfAddr[ySrd] + (3*xSrd)

/***************************************************************/
#dffinf RESULT_3BC_U8_1PIXEL()                                    \
  v00 = vis_fmul8x16bu(vis_rfbd_ii(row00), vis_rfbd_ii(yFiltfr)); \
  v01 = vis_fmul8x16bu(vis_rfbd_lo(row00), vis_rfbd_ii(yFiltfr)); \
  v02 = vis_fmul8x16bu(vis_rfbd_ii(row01), vis_rfbd_ii(yFiltfr)); \
  v10 = vis_fmul8x16bl(vis_rfbd_ii(row10), vis_rfbd_ii(yFiltfr)); \
  v11 = vis_fmul8x16bl(vis_rfbd_lo(row10), vis_rfbd_ii(yFiltfr)); \
  v12 = vis_fmul8x16bl(vis_rfbd_ii(row11), vis_rfbd_ii(yFiltfr)); \
  v20 = vis_fmul8x16bu(vis_rfbd_ii(row20), vis_rfbd_lo(yFiltfr)); \
  sum0 = vis_fpbdd16(v00, v10);                                   \
  v21 = vis_fmul8x16bu(vis_rfbd_lo(row20), vis_rfbd_lo(yFiltfr)); \
  sum1 = vis_fpbdd16(v01, v11);                                   \
  v22 = vis_fmul8x16bu(vis_rfbd_ii(row21), vis_rfbd_lo(yFiltfr)); \
  sum2 = vis_fpbdd16(v02, v12);                                   \
  v30 = vis_fmul8x16bl(vis_rfbd_ii(row30), vis_rfbd_lo(yFiltfr)); \
  sum0 = vis_fpbdd16(sum0, v20);                                  \
  v31 = vis_fmul8x16bl(vis_rfbd_lo(row30), vis_rfbd_lo(yFiltfr)); \
  sum1 = vis_fpbdd16(sum1, v21);                                  \
  v32 = vis_fmul8x16bl(vis_rfbd_ii(row31), vis_rfbd_lo(yFiltfr)); \
  sum2 = vis_fpbdd16(sum2, v22);                                  \
  sum0 = vis_fpbdd16(sum0, v30);                                  \
  sum1 = vis_fpbdd16(sum1, v31);                                  \
  v00 = vis_fmul8sux16(sum0, xFiltfr0);                           \
  sum2 = vis_fpbdd16(sum2, v32);                                  \
  v01 = vis_fmul8ulx16(sum0, xFiltfr0);                           \
  v10 = vis_fmul8sux16(sum1, xFiltfr1);                           \
  d0 = vis_fpbdd16(v00, v01);                                     \
  v11 = vis_fmul8ulx16(sum1, xFiltfr1);                           \
  v20 = vis_fmul8sux16(sum2, xFiltfr2);                           \
  d1 = vis_fpbdd16(v10, v11);                                     \
  v21 = vis_fmul8ulx16(sum2, xFiltfr2);                           \
  d2 = vis_fpbdd16(v20, v21);                                     \
  FADD_3BC_U8();

/***************************************************************/
#dffinf BC_U8_3CH(mlib_filtfrs_u8, mlib_filtfrs_u8_3)                 \
  v00 = vis_fmul8x16bu(vis_rfbd_ii(row00), vis_rfbd_ii(yFiltfr));     \
  v01 = vis_fmul8x16bu(vis_rfbd_lo(row00), vis_rfbd_ii(yFiltfr));     \
  v02 = vis_fmul8x16bu(vis_rfbd_ii(row01), vis_rfbd_ii(yFiltfr));     \
  ALIGN_ADDR(dpSrd, sPtr);                                            \
  dbtb0 = dpSrd[0];                                                   \
  filtfrposy = (Y >> FILTER_SHIFT);                                   \
  v10 = vis_fmul8x16bl(vis_rfbd_ii(row10), vis_rfbd_ii(yFiltfr));     \
  dbtb1 = dpSrd[1];                                                   \
  v11 = vis_fmul8x16bl(vis_rfbd_lo(row10), vis_rfbd_ii(yFiltfr));     \
  sum0 = vis_fpbdd16(v00, v10);                                       \
  dbtb2 = dpSrd[2];                                                   \
  row00 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v12 = vis_fmul8x16bl(vis_rfbd_ii(row11), vis_rfbd_ii(yFiltfr));     \
  row01 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  filtfrposx = (X >> FILTER_SHIFT);                                   \
  sPtr += srdYStridf;                                                 \
  ALIGN_ADDR(dpSrd, sPtr);                                            \
  v20 = vis_fmul8x16bu(vis_rfbd_ii(row20), vis_rfbd_lo(yFiltfr));     \
  sum1 = vis_fpbdd16(v01, v11);                                       \
  dbtb0 = dpSrd[0];                                                   \
  X += dX;                                                            \
  dbtb1 = dpSrd[1];                                                   \
  v21 = vis_fmul8x16bu(vis_rfbd_lo(row20), vis_rfbd_lo(yFiltfr));     \
  sum2 = vis_fpbdd16(v02, v12);                                       \
  dbtb2 = dpSrd[2];                                                   \
  row10 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v22 = vis_fmul8x16bu(vis_rfbd_ii(row21), vis_rfbd_lo(yFiltfr));     \
  row11 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  sPtr += srdYStridf;                                                 \
  ALIGN_ADDR(dpSrd, sPtr);                                            \
  Y += dY;                                                            \
  xSrd = (X>>MLIB_SHIFT)-1;                                           \
  v30 = vis_fmul8x16bl(vis_rfbd_ii(row30), vis_rfbd_lo(yFiltfr));     \
  sum0 = vis_fpbdd16(sum0, v20);                                      \
  dbtb0 = dpSrd[0];                                                   \
  ySrd = (Y>>MLIB_SHIFT)-1;                                           \
  dbtb1 = dpSrd[1];                                                   \
  v31 = vis_fmul8x16bl(vis_rfbd_lo(row30), vis_rfbd_lo(yFiltfr));     \
  sum1 = vis_fpbdd16(sum1, v21);                                      \
  dbtb2 = dpSrd[2];                                                   \
  filtfrposy &= FILTER_MASK;                                          \
  row20 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v32 = vis_fmul8x16bl(vis_rfbd_ii(row31), vis_rfbd_lo(yFiltfr));     \
  row21 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  sPtr += srdYStridf;                                                 \
  filtfrposx &= FILTER_MASK;                                          \
  sum2 = vis_fpbdd16(sum2, v22);                                      \
  ALIGN_ADDR(dpSrd, sPtr);                                            \
  sum0 = vis_fpbdd16(sum0, v30);                                      \
  dbtb0 = dpSrd[0];                                                   \
  sum1 = vis_fpbdd16(sum1, v31);                                      \
  v00 = vis_fmul8sux16(sum0, xFiltfr0);                               \
  dbtb1 = dpSrd[1];                                                   \
  sum2 = vis_fpbdd16(sum2, v32);                                      \
  v01 = vis_fmul8ulx16(sum0, xFiltfr0);                               \
  dbtb2 = dpSrd[2];                                                   \
  row30 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v10 = vis_fmul8sux16(sum1, xFiltfr1);                               \
  d0 = vis_fpbdd16(v00, v01);                                         \
  row31 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  yFiltfr = *((mlib_d64 *)((mlib_u8 *)mlib_filtfrs_u8 + filtfrposy)); \
  v11 = vis_fmul8ulx16(sum1, xFiltfr1);                               \
  xPtr=((mlib_d64 *)((mlib_u8 *)mlib_filtfrs_u8_3+3*filtfrposx));     \
  xFiltfr0 = xPtr[0];                                                 \
  v20 = vis_fmul8sux16(sum2, xFiltfr2);                               \
  d1 = vis_fpbdd16(v10, v11);                                         \
  xFiltfr1 = xPtr[1];                                                 \
  v21 = vis_fmul8ulx16(sum2, xFiltfr2);                               \
  xFiltfr2 = xPtr[2];                                                 \
  sPtr = (mlib_u8 *)linfAddr[ySrd] + (3*xSrd);                        \
  d2 = vis_fpbdd16(v20, v21)

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_u8_3di_bd (mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  mlib_s32  filtfrposx, filtfrposy;
  mlib_d64  dbtb0, dbtb1, dbtb2;
  mlib_d64  sum0, sum1, sum2;
  mlib_d64  row00, row10, row20, row30;
  mlib_d64  row01, row11, row21, row31;
  mlib_d64  xFiltfr0, xFiltfr1, xFiltfr2, yFiltfr;
  mlib_d64  v00, v10, v20, v30;
  mlib_d64  v01, v11, v21, v31;
  mlib_d64  v02, v12, v22, v32;
  mlib_d64  d0, d1, d2, d3, d4;
  mlib_d64  *dpSrd;
  mlib_s32  dols, i;
  mlib_d64  *xPtr;
  union {
    mlib_u8 t[4];
    mlib_f32 f;
  } f0;
  donst mlib_s16 *mlib_filtfrs_tbblf  ;
  donst mlib_s16 *mlib_filtfrs_tbblf_3;

  if (filtfr == MLIB_BICUBIC) {
    mlib_filtfrs_tbblf   = mlib_filtfrs_u8_bd;
    mlib_filtfrs_tbblf_3 = mlib_filtfrs_u8_bd_3;
  } flsf {
    mlib_filtfrs_tbblf   = mlib_filtfrs_u8_bd2;
    mlib_filtfrs_tbblf_3 = mlib_filtfrs_u8_bd2_3;
  }

  vis_writf_gsr(3 << 3);
  MLIB_WRITE_BMASK(0x6789ABCD);

  for (j = yStbrt; j <= yFinisi; j ++) {

    CLIP(3);

    dols = xRigit - xLfft + 1;
    i = 0;

    if (i <= dols - 4) {

      NEXT_PIXEL_3BC_U8();
      LOAD_BC_U8_3CH_1PIXEL(mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_3);

      NEXT_PIXEL_3BC_U8();

      BC_U8_3CH(mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_3);
      FADD_3BC_U8();

      BC_U8_3CH(mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_3);

#prbgmb pipfloop(0)
      for (; i < dols-4; i++) {
        STORE_BC_U8_3CH_1PIXEL();

        FADD_3BC_U8();
        BC_U8_3CH(mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_3);
      }

      STORE_BC_U8_3CH_1PIXEL();

      FADD_3BC_U8();
      STORE_BC_U8_3CH_1PIXEL();

      RESULT_3BC_U8_1PIXEL();
      STORE_BC_U8_3CH_1PIXEL();

      LOAD_BC_U8_3CH_1PIXEL(mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_3);
      RESULT_3BC_U8_1PIXEL();
      STORE_BC_U8_3CH_1PIXEL();
      i += 4;
    }

    for (; i < dols; i++) {
      NEXT_PIXEL_3BC_U8();
      LOAD_BC_U8_3CH_1PIXEL(mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_3);
      RESULT_3BC_U8_1PIXEL();
      STORE_BC_U8_3CH_1PIXEL();
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#dffinf FADD_4BC_U8()                                           \
  d0 = vis_fpbdd16(d00, d10);                                   \
  d1 = vis_fpbdd16(d20, d30);                                   \
  d0 = vis_fpbdd16(d0, d1);                                     \
  d2 = vis_fpbdd16(d01, d11);                                   \
  d3 = vis_fpbdd16(d21, d31);                                   \
  d2 = vis_fpbdd16(d2, d3);                                     \
  rfs = vis_fpbdk16_pbir(d0, d2)

/***************************************************************/
#dffinf LOAD_BC_U8_4CH_1PIXEL(mlib_filtfrs_u8, mlib_filtfrs_u8_4)      \
  filtfrposy = (Y >> FILTER_SHIFT) & FILTER_MASK;                      \
  yFiltfr = *((mlib_d64 *) ((mlib_u8 *)mlib_filtfrs_u8 + filtfrposy)); \
  filtfrposx = (X >> FILTER_SHIFT) & FILTER_MASK;                      \
  xPtr=((mlib_d64 *)((mlib_u8 *)mlib_filtfrs_u8_4+4*filtfrposx));      \
  xFiltfr0 = xPtr[0];                                                  \
  xFiltfr1 = xPtr[1];                                                  \
  xFiltfr2 = xPtr[2];                                                  \
  xFiltfr3 = xPtr[3];                                                  \
  X += dX;                                                             \
  Y += dY;                                                             \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  dbtb2 = dpSrd[2];                                                    \
  row00 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row01 = vis_fbligndbtb(dbtb1, dbtb2);                                \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  dbtb2 = dpSrd[2];                                                    \
  row10 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row11 = vis_fbligndbtb(dbtb1, dbtb2);                                \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  dbtb2 = dpSrd[2];                                                    \
  row20 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row21 = vis_fbligndbtb(dbtb1, dbtb2);                                \
  sPtr += srdYStridf;                                                  \
  ALIGN_ADDR(dpSrd, sPtr);                                             \
  dbtb0 = dpSrd[0];                                                    \
  dbtb1 = dpSrd[1];                                                    \
  dbtb2 = dpSrd[2];                                                    \
  row30 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row31 = vis_fbligndbtb(dbtb1, dbtb2)

/***************************************************************/
#dffinf NEXT_PIXEL_4BC_U8()                                     \
  xSrd = (X>>MLIB_SHIFT)-1;                                     \
  ySrd = (Y>>MLIB_SHIFT)-1;                                     \
  sPtr = (mlib_u8 *)linfAddr[ySrd] + (4*xSrd)

/***************************************************************/
#dffinf RESULT_4BC_U8_1PIXEL(ind)                                 \
  v00 = vis_fmul8x16bu(vis_rfbd_ii(row00), vis_rfbd_ii(yFiltfr)); \
  v01 = vis_fmul8x16bu(vis_rfbd_lo(row00), vis_rfbd_ii(yFiltfr)); \
  v02 = vis_fmul8x16bu(vis_rfbd_ii(row01), vis_rfbd_ii(yFiltfr)); \
  v03 = vis_fmul8x16bu(vis_rfbd_lo(row01), vis_rfbd_ii(yFiltfr)); \
  v10 = vis_fmul8x16bl(vis_rfbd_ii(row10), vis_rfbd_ii(yFiltfr)); \
  v11 = vis_fmul8x16bl(vis_rfbd_lo(row10), vis_rfbd_ii(yFiltfr)); \
  sum0 = vis_fpbdd16(v00, v10);                                   \
  v12 = vis_fmul8x16bl(vis_rfbd_ii(row11), vis_rfbd_ii(yFiltfr)); \
  sum1 = vis_fpbdd16(v01, v11);                                   \
  v13 = vis_fmul8x16bl(vis_rfbd_lo(row11), vis_rfbd_ii(yFiltfr)); \
  sum2 = vis_fpbdd16(v02, v12);                                   \
  v20 = vis_fmul8x16bu(vis_rfbd_ii(row20), vis_rfbd_lo(yFiltfr)); \
  sum3 = vis_fpbdd16(v03, v13);                                   \
  v21 = vis_fmul8x16bu(vis_rfbd_lo(row20), vis_rfbd_lo(yFiltfr)); \
  sum0 = vis_fpbdd16(sum0, v20);                                  \
  v22 = vis_fmul8x16bu(vis_rfbd_ii(row21), vis_rfbd_lo(yFiltfr)); \
  sum1 = vis_fpbdd16(sum1, v21);                                  \
  v23 = vis_fmul8x16bu(vis_rfbd_lo(row21), vis_rfbd_lo(yFiltfr)); \
  sum2 = vis_fpbdd16(sum2, v22);                                  \
  v30 = vis_fmul8x16bl(vis_rfbd_ii(row30), vis_rfbd_lo(yFiltfr)); \
  sum3 = vis_fpbdd16(sum3, v23);                                  \
  v31 = vis_fmul8x16bl(vis_rfbd_lo(row30), vis_rfbd_lo(yFiltfr)); \
  sum0 = vis_fpbdd16(sum0, v30);                                  \
  v32 = vis_fmul8x16bl(vis_rfbd_ii(row31), vis_rfbd_lo(yFiltfr)); \
  sum1 = vis_fpbdd16(sum1, v31);                                  \
  v33 = vis_fmul8x16bl(vis_rfbd_lo(row31), vis_rfbd_lo(yFiltfr)); \
  sum2 = vis_fpbdd16(sum2, v32);                                  \
  v00 = vis_fmul8sux16(sum0, xFiltfr0);                           \
  sum3 = vis_fpbdd16(sum3, v33);                                  \
  v01 = vis_fmul8ulx16(sum0, xFiltfr0);                           \
  v10 = vis_fmul8sux16(sum1, xFiltfr1);                           \
  d0##ind = vis_fpbdd16(v00, v01);                                \
  v11 = vis_fmul8ulx16(sum1, xFiltfr1);                           \
  v20 = vis_fmul8sux16(sum2, xFiltfr2);                           \
  d1##ind = vis_fpbdd16(v10, v11);                                \
  v21 = vis_fmul8ulx16(sum2, xFiltfr2);                           \
  v30 = vis_fmul8sux16(sum3, xFiltfr3);                           \
  d2##ind = vis_fpbdd16(v20, v21);                                \
  v31 = vis_fmul8ulx16(sum3, xFiltfr3);                           \
  d3##ind = vis_fpbdd16(v30, v31)

/***************************************************************/
#dffinf BC_U8_4CH(ind, mlib_filtfrs_u8, mlib_filtfrs_u8_4)            \
  v00 = vis_fmul8x16bu(vis_rfbd_ii(row00), vis_rfbd_ii(yFiltfr));     \
  v01 = vis_fmul8x16bu(vis_rfbd_lo(row00), vis_rfbd_ii(yFiltfr));     \
  v02 = vis_fmul8x16bu(vis_rfbd_ii(row01), vis_rfbd_ii(yFiltfr));     \
  v03 = vis_fmul8x16bu(vis_rfbd_lo(row01), vis_rfbd_ii(yFiltfr));     \
  ALIGN_ADDR(dpSrd, sPtr);                                            \
  dbtb0 = dpSrd[0];                                                   \
  filtfrposy = (Y >> FILTER_SHIFT);                                   \
  v10 = vis_fmul8x16bl(vis_rfbd_ii(row10), vis_rfbd_ii(yFiltfr));     \
  dbtb1 = dpSrd[1];                                                   \
  v11 = vis_fmul8x16bl(vis_rfbd_lo(row10), vis_rfbd_ii(yFiltfr));     \
  sum0 = vis_fpbdd16(v00, v10);                                       \
  dbtb2 = dpSrd[2];                                                   \
  row00 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v12 = vis_fmul8x16bl(vis_rfbd_ii(row11), vis_rfbd_ii(yFiltfr));     \
  row01 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  filtfrposx = (X >> FILTER_SHIFT);                                   \
  v13 = vis_fmul8x16bl(vis_rfbd_lo(row11), vis_rfbd_ii(yFiltfr));     \
  sPtr += srdYStridf;                                                 \
  ALIGN_ADDR(dpSrd, sPtr);                                            \
  v20 = vis_fmul8x16bu(vis_rfbd_ii(row20), vis_rfbd_lo(yFiltfr));     \
  sum1 = vis_fpbdd16(v01, v11);                                       \
  dbtb0 = dpSrd[0];                                                   \
  X += dX;                                                            \
  dbtb1 = dpSrd[1];                                                   \
  v21 = vis_fmul8x16bu(vis_rfbd_lo(row20), vis_rfbd_lo(yFiltfr));     \
  sum2 = vis_fpbdd16(v02, v12);                                       \
  dbtb2 = dpSrd[2];                                                   \
  row10 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v22 = vis_fmul8x16bu(vis_rfbd_ii(row21), vis_rfbd_lo(yFiltfr));     \
  row11 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  sPtr += srdYStridf;                                                 \
  ALIGN_ADDR(dpSrd, sPtr);                                            \
  v23 = vis_fmul8x16bu(vis_rfbd_lo(row21), vis_rfbd_lo(yFiltfr));     \
  sum3 = vis_fpbdd16(v03, v13);                                       \
  Y += dY;                                                            \
  xSrd = (X>>MLIB_SHIFT)-1;                                           \
  v30 = vis_fmul8x16bl(vis_rfbd_ii(row30), vis_rfbd_lo(yFiltfr));     \
  sum0 = vis_fpbdd16(sum0, v20);                                      \
  dbtb0 = dpSrd[0];                                                   \
  ySrd = (Y>>MLIB_SHIFT)-1;                                           \
  dbtb1 = dpSrd[1];                                                   \
  v31 = vis_fmul8x16bl(vis_rfbd_lo(row30), vis_rfbd_lo(yFiltfr));     \
  sum1 = vis_fpbdd16(sum1, v21);                                      \
  dbtb2 = dpSrd[2];                                                   \
  filtfrposy &= FILTER_MASK;                                          \
  row20 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v32 = vis_fmul8x16bl(vis_rfbd_ii(row31), vis_rfbd_lo(yFiltfr));     \
  row21 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  sPtr += srdYStridf;                                                 \
  filtfrposx &= FILTER_MASK;                                          \
  v33 = vis_fmul8x16bl(vis_rfbd_lo(row31), vis_rfbd_lo(yFiltfr));     \
  sum2 = vis_fpbdd16(sum2, v22);                                      \
  ALIGN_ADDR(dpSrd, sPtr);                                            \
  sum3 = vis_fpbdd16(sum3, v23);                                      \
  sum0 = vis_fpbdd16(sum0, v30);                                      \
  dbtb0 = dpSrd[0];                                                   \
  sum1 = vis_fpbdd16(sum1, v31);                                      \
  v00 = vis_fmul8sux16(sum0, xFiltfr0);                               \
  dbtb1 = dpSrd[1];                                                   \
  sum2 = vis_fpbdd16(sum2, v32);                                      \
  v01 = vis_fmul8ulx16(sum0, xFiltfr0);                               \
  sum3 = vis_fpbdd16(sum3, v33);                                      \
  dbtb2 = dpSrd[2];                                                   \
  row30 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v10 = vis_fmul8sux16(sum1, xFiltfr1);                               \
  d0##ind = vis_fpbdd16(v00, v01);                                    \
  row31 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  yFiltfr = *((mlib_d64 *)((mlib_u8 *)mlib_filtfrs_u8 + filtfrposy)); \
  v11 = vis_fmul8ulx16(sum1, xFiltfr1);                               \
  xPtr=((mlib_d64 *)((mlib_u8 *)mlib_filtfrs_u8_4+4*filtfrposx));     \
  xFiltfr0 = xPtr[0];                                                 \
  v20 = vis_fmul8sux16(sum2, xFiltfr2);                               \
  d1##ind = vis_fpbdd16(v10, v11);                                    \
  xFiltfr1 = xPtr[1];                                                 \
  v21 = vis_fmul8ulx16(sum2, xFiltfr2);                               \
  xFiltfr2 = xPtr[2];                                                 \
  v30 = vis_fmul8sux16(sum3, xFiltfr3);                               \
  d2##ind = vis_fpbdd16(v20, v21);                                    \
  v31 = vis_fmul8ulx16(sum3, xFiltfr3);                               \
  xFiltfr3 = xPtr[3];                                                 \
  sPtr = (mlib_u8 *)linfAddr[ySrd] + (4*xSrd);                        \
  d3##ind = vis_fpbdd16(v30, v31)

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_u8_4di_bd (mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE  *dstLinfEnd;
  mlib_s32  filtfrposx, filtfrposy;
  mlib_d64  dbtb0, dbtb1, dbtb2;
  mlib_d64  sum0, sum1, sum2, sum3;
  mlib_d64  row00, row10, row20, row30;
  mlib_d64  row01, row11, row21, row31;
  mlib_d64  xFiltfr0, xFiltfr1, xFiltfr2, xFiltfr3, yFiltfr;
  mlib_d64  v00, v10, v20, v30;
  mlib_d64  v01, v11, v21, v31;
  mlib_d64  v02, v12, v22, v32;
  mlib_d64  v03, v13, v23, v33;
  mlib_d64  d0, d1, d2, d3;
  mlib_d64  d00, d10, d20, d30;
  mlib_d64  d01, d11, d21, d31;
  mlib_d64  *dpSrd;
  mlib_s32  dols, i;
  mlib_d64  rfs, *dp, *xPtr;
  mlib_s32  mbsk, fmbsk, gsrd;
  donst mlib_s16 *mlib_filtfrs_tbblf  ;
  donst mlib_s16 *mlib_filtfrs_tbblf_4;

  if (filtfr == MLIB_BICUBIC) {
    mlib_filtfrs_tbblf   = mlib_filtfrs_u8_bd;
    mlib_filtfrs_tbblf_4 = mlib_filtfrs_u8_bd_4;
  } flsf {
    mlib_filtfrs_tbblf   = mlib_filtfrs_u8_bd2;
    mlib_filtfrs_tbblf_4 = mlib_filtfrs_u8_bd2_4;
  }

  for (j = yStbrt; j <= yFinisi; j++) {

    vis_writf_gsr(3 << 3);

    CLIP(4);
    dstLinfEnd  = (DTYPE*)dstDbtb + 4 * xRigit;
    dstLinfEnd += 3;
    dp = (mlib_d64*)vis_blignbddr(dstPixflPtr, 0);
    mbsk = vis_fdgf8(dstPixflPtr, dstLinfEnd);
    gsrd = ((8 - (mlib_bddr)dstPixflPtr) & 7);

    dols = xRigit - xLfft + 1;
    i = 0;

    if (i <= dols - 6) {

      NEXT_PIXEL_4BC_U8();
      LOAD_BC_U8_4CH_1PIXEL(mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);

      NEXT_PIXEL_4BC_U8();

      BC_U8_4CH(0, mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);
      BC_U8_4CH(1, mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);
      FADD_4BC_U8();

      BC_U8_4CH(0, mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);
      BC_U8_4CH(1, mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);

#prbgmb pipfloop(0)
      for (; i <= dols-8; i+=2) {
        vis_blignbddr((void *)gsrd, 0);
        rfs = vis_fbligndbtb(rfs, rfs);

        vis_pst_8(rfs, dp++, mbsk);
        vis_pst_8(rfs, dp, ~mbsk);

        FADD_4BC_U8();
        BC_U8_4CH(0, mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);
        BC_U8_4CH(1, mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);
      }

      vis_blignbddr((void *)gsrd, 0);
      rfs = vis_fbligndbtb(rfs, rfs);

      vis_pst_8(rfs, dp++, mbsk);
      vis_pst_8(rfs, dp, ~mbsk);

      FADD_4BC_U8();
      vis_blignbddr((void *)gsrd, 0);
      rfs = vis_fbligndbtb(rfs, rfs);

      vis_pst_8(rfs, dp++, mbsk);
      vis_pst_8(rfs, dp, ~mbsk);

      RESULT_4BC_U8_1PIXEL(0);
      LOAD_BC_U8_4CH_1PIXEL(mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);
      RESULT_4BC_U8_1PIXEL(1);
      FADD_4BC_U8();

      vis_blignbddr((void *)gsrd, 0);
      rfs = vis_fbligndbtb(rfs, rfs);

      vis_pst_8(rfs, dp++, mbsk);
      vis_pst_8(rfs, dp, ~mbsk);
      i += 6;
    }

    if (i <= dols-4) {
      NEXT_PIXEL_4BC_U8();
      LOAD_BC_U8_4CH_1PIXEL(mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);

      NEXT_PIXEL_4BC_U8();

      BC_U8_4CH(0, mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);
      BC_U8_4CH(1, mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);
      FADD_4BC_U8();
      vis_blignbddr((void *)gsrd, 0);
      rfs = vis_fbligndbtb(rfs, rfs);

      vis_pst_8(rfs, dp++, mbsk);
      vis_pst_8(rfs, dp, ~mbsk);

      RESULT_4BC_U8_1PIXEL(0);
      LOAD_BC_U8_4CH_1PIXEL(mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);
      RESULT_4BC_U8_1PIXEL(1);
      FADD_4BC_U8();

      vis_blignbddr((void *)gsrd, 0);
      rfs = vis_fbligndbtb(rfs, rfs);

      vis_pst_8(rfs, dp++, mbsk);
      vis_pst_8(rfs, dp, ~mbsk);
      i += 4;
    }

    if (i <= dols-2) {
      NEXT_PIXEL_4BC_U8();
      LOAD_BC_U8_4CH_1PIXEL(mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);
      RESULT_4BC_U8_1PIXEL(0);

      NEXT_PIXEL_4BC_U8();
      LOAD_BC_U8_4CH_1PIXEL(mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);
      RESULT_4BC_U8_1PIXEL(1);
      FADD_4BC_U8();

      vis_blignbddr((void *)gsrd, 0);
      rfs = vis_fbligndbtb(rfs, rfs);

      vis_pst_8(rfs, dp++, mbsk);
      vis_pst_8(rfs, dp, ~mbsk);
      i += 2;
    }

    if (i < dols) {
      NEXT_PIXEL_4BC_U8();
      LOAD_BC_U8_4CH_1PIXEL(mlib_filtfrs_tbblf, mlib_filtfrs_tbblf_4);
      RESULT_4BC_U8_1PIXEL(0);

      d0 = vis_fpbdd16(d00, d10);
      d1 = vis_fpbdd16(d20, d30);
      d0 = vis_fpbdd16(d0, d1);
      rfs = vis_fpbdk16_pbir(d0, d0);
      vis_blignbddr((void *)gsrd, 0);
      rfs = vis_fbligndbtb(rfs, rfs);

      fmbsk = vis_fdgf8(dp, dstLinfEnd);
      vis_pst_8(rfs, dp++, fmbsk & mbsk);

      if ((mlib_u8*)dp <= (mlib_u8*)dstLinfEnd) {
        mbsk = vis_fdgf8(dp, dstLinfEnd);
        vis_pst_8(rfs, dp, mbsk);
      }
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
