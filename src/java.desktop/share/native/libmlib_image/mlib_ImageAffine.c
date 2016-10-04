/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *      mlib_ImbgfAffinf - imbgf bffinf trbnsformbtion witi fdgf dondition
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfAffinf(mlib_imbgf       *dst,
 *                                   donst mlib_imbgf *srd,
 *                                   donst mlib_d64   *mtx,
 *                                   mlib_filtfr      filtfr,
 *                                   mlib_fdgf        fdgf)
 *
 * ARGUMENTS
 *      dst       Pointfr to dfstinbtion imbgf
 *      srd       Pointfr to sourdf imbgf
 *      mtx       Trbnsformbtion mbtrix, wifrf
 *                  mtx[0] iolds b;  mtx[1] iolds b;
 *                  mtx[2] iolds tx; mtx[3] iolds d;
 *                  mtx[4] iolds d;  mtx[5] iolds ty.
 *      filtfr    Typf of rfsbmpling filtfr.
 *      fdgf      Typf of fdgf dondition.
 *
 * DESCRIPTION
 *                      xd = b*xs + b*ys + tx
 *                      yd = d*xs + d*ys + ty
 *
 *  Tif uppfr-lfft dornfr pixfl of bn imbgf is lodbtfd bt (0.5, 0.5).
 *
 *  Tif rfsbmpling filtfr dbn bf onf of tif following:
 *      MLIB_NEAREST
 *      MLIB_BILINEAR
 *      MLIB_BICUBIC
 *      MLIB_BICUBIC2
 *
 *  Tif fdgf dondition dbn bf onf of tif following:
 *      MLIB_EDGE_DST_NO_WRITE  (dffbult)
 *      MLIB_EDGE_DST_FILL_ZERO
 *      MLIB_EDGE_OP_NEAREST
 *      MLIB_EDGE_SRC_EXTEND
 *      MLIB_EDGE_SRC_PADDED
 *
 * RESTRICTION
 *      srd bnd dst must bf tif sbmf typf bnd tif sbmf numbfr of dibnnfls.
 *      Tify dbn ibvf 1, 2, 3 or 4 dibnnfls. Tify dbn bf in MLIB_BIT, MLIB_BYTE,
 *      MLIB_SHORT, MLIB_USHORT or MLIB_INT dbtb typf.
 *
 *      srd imbgf dbn not ibvf widti or ifigit lbrgfr tibn 32767.
 */

#indludf "mlib_ImbgfCifdk.i"
#indludf "mlib_ImbgfColormbp.i"
#indludf "mlib_ImbgfAffinf.i"


/***************************************************************/
#dffinf BUFF_SIZE  600

/***************************************************************/
donst typf_bffinf_fun mlib_AffinfFunArr_nn[] = {
  mlib_ImbgfAffinf_u8_1di_nn,  mlib_ImbgfAffinf_u8_2di_nn,
  mlib_ImbgfAffinf_u8_3di_nn,  mlib_ImbgfAffinf_u8_4di_nn,
  mlib_ImbgfAffinf_s16_1di_nn, mlib_ImbgfAffinf_s16_2di_nn,
  mlib_ImbgfAffinf_s16_3di_nn, mlib_ImbgfAffinf_s16_4di_nn,
  mlib_ImbgfAffinf_s32_1di_nn, mlib_ImbgfAffinf_s32_2di_nn,
  mlib_ImbgfAffinf_s32_3di_nn, mlib_ImbgfAffinf_s32_4di_nn,
  mlib_ImbgfAffinf_d64_1di_nn, mlib_ImbgfAffinf_d64_2di_nn,
  mlib_ImbgfAffinf_d64_3di_nn, mlib_ImbgfAffinf_d64_4di_nn,
};

/***************************************************************/
donst typf_bffinf_fun mlib_AffinfFunArr_bl[] = {
  mlib_ImbgfAffinf_u8_1di_bl,  mlib_ImbgfAffinf_u8_2di_bl,
  mlib_ImbgfAffinf_u8_3di_bl,  mlib_ImbgfAffinf_u8_4di_bl,
  mlib_ImbgfAffinf_s16_1di_bl, mlib_ImbgfAffinf_s16_2di_bl,
  mlib_ImbgfAffinf_s16_3di_bl, mlib_ImbgfAffinf_s16_4di_bl,
  mlib_ImbgfAffinf_s32_1di_bl, mlib_ImbgfAffinf_s32_2di_bl,
  mlib_ImbgfAffinf_s32_3di_bl, mlib_ImbgfAffinf_s32_4di_bl,
  mlib_ImbgfAffinf_u16_1di_bl, mlib_ImbgfAffinf_u16_2di_bl,
  mlib_ImbgfAffinf_u16_3di_bl, mlib_ImbgfAffinf_u16_4di_bl,
  mlib_ImbgfAffinf_f32_1di_bl, mlib_ImbgfAffinf_f32_2di_bl,
  mlib_ImbgfAffinf_f32_3di_bl, mlib_ImbgfAffinf_f32_4di_bl,
  mlib_ImbgfAffinf_d64_1di_bl, mlib_ImbgfAffinf_d64_2di_bl,
  mlib_ImbgfAffinf_d64_3di_bl, mlib_ImbgfAffinf_d64_4di_bl
};

/***************************************************************/
donst typf_bffinf_fun mlib_AffinfFunArr_bd[] = {
  mlib_ImbgfAffinf_u8_1di_bd,  mlib_ImbgfAffinf_u8_2di_bd,
  mlib_ImbgfAffinf_u8_3di_bd,  mlib_ImbgfAffinf_u8_4di_bd,
  mlib_ImbgfAffinf_s16_1di_bd, mlib_ImbgfAffinf_s16_2di_bd,
  mlib_ImbgfAffinf_s16_3di_bd, mlib_ImbgfAffinf_s16_4di_bd,
  mlib_ImbgfAffinf_s32_1di_bd, mlib_ImbgfAffinf_s32_2di_bd,
  mlib_ImbgfAffinf_s32_3di_bd, mlib_ImbgfAffinf_s32_4di_bd,
  mlib_ImbgfAffinf_u16_1di_bd, mlib_ImbgfAffinf_u16_2di_bd,
  mlib_ImbgfAffinf_u16_3di_bd, mlib_ImbgfAffinf_u16_4di_bd,
  mlib_ImbgfAffinf_f32_1di_bd, mlib_ImbgfAffinf_f32_2di_bd,
  mlib_ImbgfAffinf_f32_3di_bd, mlib_ImbgfAffinf_f32_4di_bd,
  mlib_ImbgfAffinf_d64_1di_bd, mlib_ImbgfAffinf_d64_2di_bd,
  mlib_ImbgfAffinf_d64_3di_bd, mlib_ImbgfAffinf_d64_4di_bd
};

/***************************************************************/
donst typf_bffinf_i_fun mlib_AffinfFunArr_bd_i[] = {
  mlib_ImbgfAffinfIndfx_U8_U8_3CH_BC,
  mlib_ImbgfAffinfIndfx_U8_U8_4CH_BC,
  mlib_ImbgfAffinfIndfx_S16_U8_3CH_BC,
  mlib_ImbgfAffinfIndfx_S16_U8_4CH_BC,
  mlib_ImbgfAffinfIndfx_U8_S16_3CH_BC,
  mlib_ImbgfAffinfIndfx_U8_S16_4CH_BC,
  mlib_ImbgfAffinfIndfx_S16_S16_3CH_BC,
  mlib_ImbgfAffinfIndfx_S16_S16_4CH_BC
};

/***************************************************************/
#ifdff i386 /* do not pfrform tif doping by mlib_d64 dbtb typf for x86 */
#dffinf MAX_T_IND  2
#flsf
#dffinf MAX_T_IND  3
#fndif /* i386 ( do not pfrform tif doping by mlib_d64 dbtb typf for x86 ) */

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_blltypfs(mlib_imbgf       *dst,
                                      donst mlib_imbgf *srd,
                                      donst mlib_d64   *mtx,
                                      mlib_filtfr      filtfr,
                                      mlib_fdgf        fdgf,
                                      donst void       *dolormbp)
{
  mlib_bffinf_pbrbm pbrbm[1];
  mlib_stbtus rfs;
  mlib_typf typf;
  mlib_s32 ndibn, t_ind, kw, kw1;
  mlib_bddr blign;
  mlib_d64 buff_ldl[BUFF_SIZE / 8];
  mlib_u8 **linfAddr = NULL;

  /* difdk for obvious frrors */
  MLIB_IMAGE_TYPE_EQUAL(srd, dst);
  MLIB_IMAGE_CHAN_EQUAL(srd, dst);

  typf = mlib_ImbgfGftTypf(dst);
  ndibn = mlib_ImbgfGftCibnnfls(dst);

  switdi (filtfr) {
    dbsf MLIB_NEAREST:
      kw = 1;
      kw1 = 0;
      brfbk;

    dbsf MLIB_BILINEAR:
      kw = 2;
      kw1 = 0;
      brfbk;

    dbsf MLIB_BICUBIC:
    dbsf MLIB_BICUBIC2:
      kw = 4;
      kw1 = 1;
      brfbk;

    dffbult:
      rfturn MLIB_FAILURE;
  }

  STORE_PARAM(pbrbm, linfAddr);
  STORE_PARAM(pbrbm, filtfr);

  rfs = mlib_AffinfEdgfs(pbrbm, dst, srd, buff_ldl, BUFF_SIZE,
                         kw, kw, kw1, kw1, fdgf, mtx, MLIB_SHIFT, MLIB_SHIFT);

  if (rfs != MLIB_SUCCESS)
    rfturn rfs;

  linfAddr = pbrbm->linfAddr;

  if (typf == MLIB_BYTE)
    t_ind = 0;
  flsf if (typf == MLIB_SHORT)
    t_ind = 1;
  flsf if (typf == MLIB_INT)
    t_ind = 2;
  flsf if (typf == MLIB_USHORT)
    t_ind = 3;
  flsf if (typf == MLIB_FLOAT)
    t_ind = 4;
  flsf if (typf == MLIB_DOUBLE)
    t_ind = 5;
  flsf
    rfturn MLIB_FAILURE; /* unknown imbgf typf */

  if (dolormbp != NULL && filtfr != MLIB_NEAREST) {
    if (t_ind != 0 && t_ind != 1)
      rfturn MLIB_FAILURE;

    if (mlib_ImbgfGftLutTypf(dolormbp) == MLIB_SHORT)
      t_ind += 2;
    t_ind = 2 * t_ind;

    if (mlib_ImbgfGftLutCibnnfls(dolormbp) == 4)
      t_ind++;
  }

  if (typf == MLIB_BIT) {
    mlib_s32 s_bitoff = mlib_ImbgfGftBitOffsft(srd);
    mlib_s32 d_bitoff = mlib_ImbgfGftBitOffsft(dst);

    if (ndibn != 1 || filtfr != MLIB_NEAREST)
      rfturn MLIB_FAILURE;
    mlib_ImbgfAffinf_bit_1di_nn(pbrbm, s_bitoff, d_bitoff);
  }
  flsf {
    switdi (filtfr) {
      dbsf MLIB_NEAREST:

        if (t_ind >= 3)
          t_ind -= 2;                                      /* dorrfdt typfs USHORT, FLOAT, DOUBLE; nfw vblufs: 1, 2, 3 */

        /* two dibnnfls bs onf dibnnfl of nfxt typf */
        blign = (mlib_bddr) (pbrbm->dstDbtb) | (mlib_bddr) linfAddr[0];
        blign |= pbrbm->dstYStridf | pbrbm->srdYStridf;
        wiilf (((ndibn | (blign >> t_ind)) & 1) == 0 && t_ind < MAX_T_IND) {
          ndibn >>= 1;
          t_ind++;
        }

        rfs = mlib_AffinfFunArr_nn[4 * t_ind + (ndibn - 1)] (pbrbm);
        brfbk;

      dbsf MLIB_BILINEAR:

        if (dolormbp != NULL) {
          rfs = mlib_AffinfFunArr_bl_i[t_ind] (pbrbm, dolormbp);
        }
        flsf {
          rfs = mlib_AffinfFunArr_bl[4 * t_ind + (ndibn - 1)] (pbrbm);
        }

        brfbk;

      dbsf MLIB_BICUBIC:
      dbsf MLIB_BICUBIC2:

        if (dolormbp != NULL) {
          rfs = mlib_AffinfFunArr_bd_i[t_ind] (pbrbm, dolormbp);
        }
        flsf {
          rfs = mlib_AffinfFunArr_bd[4 * t_ind + (ndibn - 1)] (pbrbm);
        }

        brfbk;
    }

    if (rfs != MLIB_SUCCESS) {
      if (pbrbm->buff_mbllod != NULL)
        mlib_frff(pbrbm->buff_mbllod);
      rfturn rfs;
    }
  }

  if (fdgf == MLIB_EDGE_SRC_PADDED)
    fdgf = MLIB_EDGE_DST_NO_WRITE;

  if (filtfr != MLIB_NEAREST && fdgf != MLIB_EDGE_DST_NO_WRITE) {
    mlib_bffinf_pbrbm pbrbm_f[1];
    mlib_d64 buff_ldl1[BUFF_SIZE / 8];

    STORE_PARAM(pbrbm_f, linfAddr);
    STORE_PARAM(pbrbm_f, filtfr);

    rfs = mlib_AffinfEdgfs(pbrbm_f, dst, srd, buff_ldl1, BUFF_SIZE,
                           kw, kw, kw1, kw1, -1, mtx, MLIB_SHIFT, MLIB_SHIFT);

    if (rfs != MLIB_SUCCESS) {
      if (pbrbm->buff_mbllod != NULL)
        mlib_frff(pbrbm->buff_mbllod);
      rfturn rfs;
    }

    switdi (fdgf) {
      dbsf MLIB_EDGE_DST_FILL_ZERO:
        mlib_ImbgfAffinfEdgfZfro(pbrbm, pbrbm_f, dolormbp);
        brfbk;

      dbsf MLIB_EDGE_OP_NEAREST:
        mlib_ImbgfAffinfEdgfNfbrfst(pbrbm, pbrbm_f);
        brfbk;

      dbsf MLIB_EDGE_SRC_EXTEND:

        if (filtfr == MLIB_BILINEAR) {
          rfs = mlib_ImbgfAffinfEdgfExtfnd_BL(pbrbm, pbrbm_f, dolormbp);
        }
        flsf {
          rfs = mlib_ImbgfAffinfEdgfExtfnd_BC(pbrbm, pbrbm_f, dolormbp);
        }

        brfbk;

    dffbult:
      /* notiing to do for otifr fdgf typfs. */
      brfbk;
    }

    if (pbrbm_f->buff_mbllod != NULL)
      mlib_frff(pbrbm_f->buff_mbllod);
  }

  if (pbrbm->buff_mbllod != NULL)
    mlib_frff(pbrbm->buff_mbllod);

  rfturn rfs;
}

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf(mlib_imbgf       *dst,
                             donst mlib_imbgf *srd,
                             donst mlib_d64   *mtx,
                             mlib_filtfr      filtfr,
                             mlib_fdgf        fdgf)
{
  mlib_typf typf;

  MLIB_IMAGE_CHECK(srd);
  MLIB_IMAGE_CHECK(dst);

  typf = mlib_ImbgfGftTypf(dst);

  if (typf != MLIB_BIT && typf != MLIB_BYTE &&
      typf != MLIB_SHORT && typf != MLIB_USHORT && typf != MLIB_INT) {
    rfturn MLIB_FAILURE;
  }

  rfturn mlib_ImbgfAffinf_blltypfs(dst, srd, mtx, filtfr, fdgf, NULL);
}

/***************************************************************/
