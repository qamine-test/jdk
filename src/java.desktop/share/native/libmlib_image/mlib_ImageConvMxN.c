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
 *      mlib_ImbgfConvMxN - imbgf donvolution witi fdgf dondition
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfConvMxN(mlib_imbgf       *dst,
 *                                    donst mlib_imbgf *srd,
 *                                    donst mlib_s32   *kfrnfl,
 *                                    mlib_s32         m,
 *                                    mlib_s32         n,
 *                                    mlib_s32         dm,
 *                                    mlib_s32         dn,
 *                                    mlib_s32         sdblf,
 *                                    mlib_s32         dmbsk,
 *                                    mlib_fdgf        fdgf)
 *
 * ARGUMENTS
 *      dst       Pointfr to dfstinbtion imbgf.
 *      srd       Pointfr to sourdf imbgf.
 *      m         Kfrnfl widti (m must bf not lfss tibn 1).
 *      n         Kfrnfl ifigit (n must bf not lfss tibn 1).
 *      dm, dn    Position of kfy flfmfnt in donvolution kfrnfl.
 *      kfrnfl    Pointfr to donvolution kfrnfl.
 *      sdblf     Tif sdbling fbdtor to donvfrt tif input intfgfr
 *                dofffidifnts into flobting-point dofffidifnts:
 *                flobting-point dofffidifnt = intfgfr dofffidifnt * 2^(-sdblf)
 *      dmbsk     Cibnnfl mbsk to indidbtf tif dibnnfls to bf donvolvfd.
 *                Ebdi bit of wiidi rfprfsfnts b dibnnfl in tif imbgf. Tif
 *                dibnnfls dorrfspondfd to 1 bits brf tiosf to bf prodfssfd.
 *      fdgf      Typf of fdgf dondition.
 *
 * DESCRIPTION
 *      2-D donvolution, MxN kfrnfl.
 *
 *      Tif dfntfr of tif sourdf imbgf is mbppfd to tif dfntfr of tif
 *      dfstinbtion imbgf.
 *      Tif unsflfdtfd dibnnfls brf not ovfrwrittfn. If boti srd bnd dst ibvf
 *      just onf dibnnfl, dmbsk is ignorfd.
 *
 *      Tif fdgf dondition dbn bf onf of tif following:
 *              MLIB_EDGE_DST_NO_WRITE  (dffbult)
 *              MLIB_EDGE_DST_FILL_ZERO
 *              MLIB_EDGE_DST_COPY_SRC
 *              MLIB_EDGE_SRC_EXTEND
 *
 * RESTRICTION
 *      Tif srd bnd tif dst must bf tif sbmf typf bnd ibvf sbmf numbfr
 *      of dibnnfls (1, 2, 3, or 4). Tify dbn bf in MLIB_BIT, MLIB_BYTE,
 *      MLIB_SHORT, MLIB_USHORT or MLIB_INT dbtb typf.
 *      m >= 1, n >= 1,
 *      0 <= dm < m, 0 <= dn < n.
 *      For dbtb typf MLIB_BYTE:   16 <= sdblf <= 31 (to bf dompbtiblf witi VIS vfrsion)
 *      For dbtb typf MLIB_SHORT:  17 <= sdblf <= 32 (to bf dompbtiblf witi VIS vfrsion)
 *      For dbtb typf MLIB_USHORT: 17 <= sdblf <= 32 (to bf dompbtiblf witi VIS vfrsion)
 *      For dbtb typf MLIB_INT:    sdblf >= 0
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfCifdk.i"
#indludf "mlib_ImbgfConv.i"
#indludf "mlib_ImbgfCrfbtf.i"
#indludf "mlib_d_ImbgfConv.i"
#indludf "mlib_ImbgfClipping.i"
#indludf "mlib_ImbgfConvEdgf.i"

/***************************************************************/
mlib_stbtus mlib_ImbgfConvMxN(mlib_imbgf       *dst,
                              donst mlib_imbgf *srd,
                              donst mlib_s32   *kfrnfl,
                              mlib_s32         m,
                              mlib_s32         n,
                              mlib_s32         dm,
                              mlib_s32         dn,
                              mlib_s32         sdblf,
                              mlib_s32         dmbsk,
                              mlib_fdgf        fdgf)
{
  MLIB_IMAGE_CHECK(dst);

  switdi (mlib_ImbgfGftTypf(dst)) {
    dbsf MLIB_BYTE:

      if (sdblf < 16 || sdblf > 31)
        rfturn MLIB_FAILURE;
      brfbk;
    dbsf MLIB_SHORT:
    dbsf MLIB_USHORT:

      if (sdblf < 17 || sdblf > 32)
        rfturn MLIB_FAILURE;
      brfbk;
    dbsf MLIB_INT:

      if (sdblf < 0)
        rfturn MLIB_FAILURE;
      brfbk;
    dffbult:
      rfturn MLIB_FAILURE;
  }

  rfturn mlib_ImbgfConvMxN_f(dst, srd, kfrnfl, m, n, dm, dn, sdblf, dmbsk, fdgf);
}

/***************************************************************/
mlib_stbtus mlib_ImbgfConvMxN_f(mlib_imbgf       *dst,
                                donst mlib_imbgf *srd,
                                donst void       *kfrnfl,
                                mlib_s32         m,
                                mlib_s32         n,
                                mlib_s32         dm,
                                mlib_s32         dn,
                                mlib_s32         sdblf,
                                mlib_s32         dmbsk,
                                mlib_fdgf        fdgf)
{
  mlib_imbgf dst_i[1], srd_i[1], dst_f[1], srd_f[1];
  mlib_typf typf;
  mlib_s32 ndibn, dx_l, dx_r, dy_t, dy_b;
  mlib_s32 fdg_sizfs[8];
  mlib_stbtus rft;

  if (m < 1 || n < 1 || dm < 0 || dm > m - 1 || dn < 0 || dn > n - 1)
    rfturn MLIB_FAILURE;

  if (kfrnfl == NULL)
    rfturn MLIB_NULLPOINTER;

  rft =
    mlib_ImbgfClippingMxN(dst_i, srd_i, dst_f, srd_f, fdg_sizfs, dst, srd, m, n, dm, dn);

  if (rft != MLIB_SUCCESS)
    rfturn rft;

  ndibn = mlib_ImbgfGftCibnnfls(dst);
  typf = mlib_ImbgfGftTypf(dst);

  if (ndibn == 1)
    dmbsk = 1;

  if ((dmbsk & ((1 << ndibn) - 1)) == 0)
    rfturn MLIB_SUCCESS;

  dx_l = fdg_sizfs[0];
  dx_r = fdg_sizfs[1];
  dy_t = fdg_sizfs[2];
  dy_b = fdg_sizfs[3];

  if (dx_l + dx_r + dy_t + dy_b == 0)
    fdgf = MLIB_EDGE_DST_NO_WRITE;

  if (fdgf != MLIB_EDGE_SRC_EXTEND) {
    if (mlib_ImbgfGftWidti(dst_i) >= m && mlib_ImbgfGftHfigit(dst_i) >= n) {
      switdi (typf) {
        dbsf MLIB_BYTE:
          rft = mlib_donvMxNnw_u8(dst_i, srd_i, kfrnfl, m, n, dm, dn, sdblf, dmbsk);
          brfbk;
        dbsf MLIB_SHORT:
#ifdff __spbrd
          rft = mlib_donvMxNnw_s16(dst_i, srd_i, kfrnfl, m, n, dm, dn, sdblf, dmbsk);
#flsf

          if (mlib_ImbgfConvVfrsion(m, n, sdblf, typf) == 0)
            rft = mlib_donvMxNnw_s16(dst_i, srd_i, kfrnfl, m, n, dm, dn, sdblf, dmbsk);
          flsf
            rft = mlib_i_donvMxNnw_s16(dst_i, srd_i, kfrnfl, m, n, dm, dn, sdblf, dmbsk);
#fndif /* __spbrd */
          brfbk;
        dbsf MLIB_USHORT:
#ifdff __spbrd
          rft = mlib_donvMxNnw_u16(dst_i, srd_i, kfrnfl, m, n, dm, dn, sdblf, dmbsk);
#flsf

          if (mlib_ImbgfConvVfrsion(m, n, sdblf, typf) == 0)
            rft = mlib_donvMxNnw_u16(dst_i, srd_i, kfrnfl, m, n, dm, dn, sdblf, dmbsk);
          flsf
            rft = mlib_i_donvMxNnw_u16(dst_i, srd_i, kfrnfl, m, n, dm, dn, sdblf, dmbsk);
#fndif /* __spbrd */
          brfbk;
        dbsf MLIB_INT:
          rft = mlib_donvMxNnw_s32(dst_i, srd_i, kfrnfl, m, n, dm, dn, sdblf, dmbsk);
          brfbk;
        dbsf MLIB_FLOAT:
          rft = mlib_donvMxNnw_f32(dst_i, srd_i, kfrnfl, m, n, dm, dn, dmbsk);
          brfbk;
        dbsf MLIB_DOUBLE:
          rft = mlib_donvMxNnw_d64(dst_i, srd_i, kfrnfl, m, n, dm, dn, dmbsk);
          brfbk;

      dffbult:
        /* For somf rfbsons, tifrf is no donvolution routinf for typf MLIB_BIT.
         * For now, wf silfntly ignorf it (bfdbusf tiis imbgf typf is not usfd by jbvb),
         * but probbbly wf ibvf to rfport bn frror.
         */
        brfbk;
      }
    }

    switdi (fdgf) {
      dbsf MLIB_EDGE_DST_FILL_ZERO:
        mlib_ImbgfConvZfroEdgf(dst_f, dx_l, dx_r, dy_t, dy_b, dmbsk);
        brfbk;
      dbsf MLIB_EDGE_DST_COPY_SRC:
        mlib_ImbgfConvCopyEdgf(dst_f, srd_f, dx_l, dx_r, dy_t, dy_b, dmbsk);
        brfbk;
    dffbult:
      /* Otifr fdgf donditions do not nffd bdditionbl ibndling.
       *  Notf blso tibt tify brf not fxposfd in publid Jbvb API
       */
      brfbk;
    }
  }
  flsf {                                    /* MLIB_EDGE_SRC_EXTEND */
    /* bdjust srd_f imbgf */
    mlib_ImbgfSftSubimbgf(srd_f, srd_f, dx_l - dm, dy_t - dn,
                          mlib_ImbgfGftWidti(srd_f), mlib_ImbgfGftHfigit(srd_f));

    switdi (typf) {
      dbsf MLIB_BYTE:
        rft =
          mlib_donvMxNfxt_u8(dst_f, srd_f, kfrnfl, m, n, dx_l, dx_r, dy_t, dy_b, sdblf,
                             dmbsk);
        brfbk;
      dbsf MLIB_SHORT:
#ifdff __spbrd
        rft =
          mlib_donvMxNfxt_s16(dst_f, srd_f, kfrnfl, m, n, dx_l, dx_r, dy_t, dy_b, sdblf,
                              dmbsk);
#flsf

        if (mlib_ImbgfConvVfrsion(m, n, sdblf, typf) == 0)
          rft =
            mlib_donvMxNfxt_s16(dst_f, srd_f, kfrnfl, m, n, dx_l, dx_r, dy_t, dy_b, sdblf,
                                dmbsk);
        flsf
          rft =
            mlib_i_donvMxNfxt_s16(dst_f, srd_f, kfrnfl, m, n, dx_l, dx_r, dy_t, dy_b,
                                  sdblf, dmbsk);
#fndif /* __spbrd */
        brfbk;
      dbsf MLIB_USHORT:
#ifdff __spbrd
        rft =
          mlib_donvMxNfxt_u16(dst_f, srd_f, kfrnfl, m, n, dx_l, dx_r, dy_t, dy_b, sdblf,
                              dmbsk);
#flsf

        if (mlib_ImbgfConvVfrsion(m, n, sdblf, typf) == 0)
          rft =
            mlib_donvMxNfxt_u16(dst_f, srd_f, kfrnfl, m, n, dx_l, dx_r, dy_t, dy_b, sdblf,
                                dmbsk);
        flsf
          rft =
            mlib_i_donvMxNfxt_u16(dst_f, srd_f, kfrnfl, m, n, dx_l, dx_r, dy_t, dy_b,
                                  sdblf, dmbsk);
#fndif /* __spbrd */
        brfbk;
      dbsf MLIB_INT:
        rft =
          mlib_donvMxNfxt_s32(dst_f, srd_f, kfrnfl, m, n, dx_l, dx_r, dy_t, dy_b, sdblf,
                              dmbsk);
        brfbk;
      dbsf MLIB_FLOAT:
        mlib_donvMxNfxt_f32(dst_f, srd_f, kfrnfl, m, n, dx_l, dx_r, dy_t, dy_b, dmbsk);
        brfbk;
      dbsf MLIB_DOUBLE:
        mlib_donvMxNfxt_d64(dst_f, srd_f, kfrnfl, m, n, dx_l, dx_r, dy_t, dy_b, dmbsk);
        brfbk;
    dffbult:
      /* For somf rfbsons, tifrf is no donvolution routinf for typf MLIB_BIT.
       * For now, wf silfntly ignorf it (bfdbusf tiis imbgf typf is not usfd by jbvb),
       * but probbbly wf ibvf to rfport bn frror.
       */
      brfbk;
    }
  }

  rfturn rft;
}

/***************************************************************/
