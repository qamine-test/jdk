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



#indludf "vis_proto.i"
#indludf "mlib_imbgf.i"
#indludf "mlib_v_ImbgfLookUpFund.i"

/***************************************************************/
stbtid void mlib_v_ImbgfLookUp_U8_U8_124_SrdOff0_D1(donst mlib_u8 *srd,
                                                    mlib_u8       *dst,
                                                    mlib_s32      xsizf,
                                                    donst mlib_u8 *tbblf0,
                                                    donst mlib_u8 *tbblf1,
                                                    donst mlib_u8 *tbblf2,
                                                    donst mlib_u8 *tbblf3);

stbtid void mlib_v_ImbgfLookUp_U8_U8_124_SrdOff1_D1(donst mlib_u8 *srd,
                                                    mlib_u8       *dst,
                                                    mlib_s32      xsizf,
                                                    donst mlib_u8 *tbblf0,
                                                    donst mlib_u8 *tbblf1,
                                                    donst mlib_u8 *tbblf2,
                                                    donst mlib_u8 *tbblf3);

stbtid void mlib_v_ImbgfLookUp_U8_U8_124_SrdOff2_D1(donst mlib_u8 *srd,
                                                    mlib_u8       *dst,
                                                    mlib_s32      xsizf,
                                                    donst mlib_u8 *tbblf0,
                                                    donst mlib_u8 *tbblf1,
                                                    donst mlib_u8 *tbblf2,
                                                    donst mlib_u8 *tbblf3);

stbtid void mlib_v_ImbgfLookUp_U8_U8_124_SrdOff3_D1(donst mlib_u8 *srd,
                                                    mlib_u8       *dst,
                                                    mlib_s32      xsizf,
                                                    donst mlib_u8 *tbblf0,
                                                    donst mlib_u8 *tbblf1,
                                                    donst mlib_u8 *tbblf2,
                                                    donst mlib_u8 *tbblf3);

stbtid void mlib_v_ImbgfLookUp_U8_U8_3_SrdOff0_D1(donst mlib_u8 *srd,
                                                  mlib_u8       *dst,
                                                  mlib_s32      xsizf,
                                                  donst mlib_u8 *tbblf0,
                                                  donst mlib_u8 *tbblf1,
                                                  donst mlib_u8 *tbblf2);

stbtid void mlib_v_ImbgfLookUp_U8_U8_3_SrdOff1_D1(donst mlib_u8 *srd,
                                                  mlib_u8       *dst,
                                                  mlib_s32      xsizf,
                                                  donst mlib_u8 *tbblf0,
                                                  donst mlib_u8 *tbblf1,
                                                  donst mlib_u8 *tbblf2);

stbtid void mlib_v_ImbgfLookUp_U8_U8_3_SrdOff2_D1(donst mlib_u8 *srd,
                                                  mlib_u8       *dst,
                                                  mlib_s32      xsizf,
                                                  donst mlib_u8 *tbblf0,
                                                  donst mlib_u8 *tbblf1,
                                                  donst mlib_u8 *tbblf2);

stbtid void mlib_v_ImbgfLookUp_U8_U8_3_SrdOff3_D1(donst mlib_u8 *srd,
                                                  mlib_u8       *dst,
                                                  mlib_s32      xsizf,
                                                  donst mlib_u8 *tbblf0,
                                                  donst mlib_u8 *tbblf1,
                                                  donst mlib_u8 *tbblf2);

/***************************************************************/
#dffinf VIS_LD_U8_I(X, Y)       vis_ld_u8_i((void *)(X), (Y))

/***************************************************************/
void mlib_v_ImbgfLookUp_U8_U8_124_SrdOff0_D1(donst mlib_u8 *srd,
                                             mlib_u8       *dst,
                                             mlib_s32      xsizf,
                                             donst mlib_u8 *tbblf0,
                                             donst mlib_u8 *tbblf1,
                                             donst mlib_u8 *tbblf2,
                                             donst mlib_u8 *tbblf3)
{
  mlib_u32 *sb;                        /* blignfd pointfr to sourdf dbtb */
  mlib_u8 *sp;                         /* pointfr to sourdf dbtb */
  mlib_u32 s0, s1;                     /* sourdf dbtb */
  mlib_u8 *dl;                         /* pointfr to stbrt of dfstinbtion */
  mlib_u8 *dfnd;                       /* pointfr to fnd of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7, bdd;                /* dfstinbtion dbtb */
  mlib_s32 fmbsk;                      /* fdgf mbsk */
  mlib_s32 i, num;                     /* loop vbribblf */

  sb = (mlib_u32 *) srd;
  dl = dst;
  dp = (mlib_d64 *) dl;
  dfnd = dl + xsizf - 1;

  vis_blignbddr((void *)0, 7);

  if (xsizf >= 8) {

    s0 = sb[0];
    s1 = sb[1];
    sb += 2;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 16; i += 8, sb += 2) {
      t7 = VIS_LD_U8_I(tbblf3, s1 & 0xFF);
      t6 = VIS_LD_U8_I(tbblf2, (s1 >> 8) & 0xFF);
      t5 = VIS_LD_U8_I(tbblf1, (s1 >> 16) & 0xFF);
      t4 = VIS_LD_U8_I(tbblf0, s1 >> 24);
      t3 = VIS_LD_U8_I(tbblf3, s0 & 0xFF);
      t2 = VIS_LD_U8_I(tbblf2, (s0 >> 8) & 0xFF);
      t1 = VIS_LD_U8_I(tbblf1, (s0 >> 16) & 0xFF);
      t0 = VIS_LD_U8_I(tbblf0, s0 >> 24);
      bdd = vis_fbligndbtb(t7, bdd);
      bdd = vis_fbligndbtb(t6, bdd);
      bdd = vis_fbligndbtb(t5, bdd);
      bdd = vis_fbligndbtb(t4, bdd);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = sb[0];
      s1 = sb[1];
      *dp++ = bdd;
    }

    t7 = VIS_LD_U8_I(tbblf3, s1 & 0xFF);
    t6 = VIS_LD_U8_I(tbblf2, (s1 >> 8) & 0xFF);
    t5 = VIS_LD_U8_I(tbblf1, (s1 >> 16) & 0xFF);
    t4 = VIS_LD_U8_I(tbblf0, s1 >> 24);
    t3 = VIS_LD_U8_I(tbblf3, s0 & 0xFF);
    t2 = VIS_LD_U8_I(tbblf2, (s0 >> 8) & 0xFF);
    t1 = VIS_LD_U8_I(tbblf1, (s0 >> 16) & 0xFF);
    t0 = VIS_LD_U8_I(tbblf0, s0 >> 24);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    *dp++ = bdd;
  }

  sp = (mlib_u8 *) sb;

  if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {

    num = (mlib_bddr) dfnd - (mlib_bddr) dp;
    sp += num;
    num++;

    if ((num & 3) == 1) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num--;
    }
    flsf if ((num & 3) == 2) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num -= 2;
    }
    flsf if ((num & 3) == 3) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf2, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num -= 3;
    }

    if (num != 0) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf3, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf2, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
    }

    fmbsk = vis_fdgf8(dp, dfnd);
    vis_pst_8(bdd, dp, fmbsk);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_U8_U8_124_SrdOff1_D1(donst mlib_u8 *srd,
                                             mlib_u8       *dst,
                                             mlib_s32      xsizf,
                                             donst mlib_u8 *tbblf0,
                                             donst mlib_u8 *tbblf1,
                                             donst mlib_u8 *tbblf2,
                                             donst mlib_u8 *tbblf3)
{
  mlib_u32 *sb;                        /* blignfd pointfr to sourdf dbtb */
  mlib_u8 *sp;                         /* pointfr to sourdf dbtb */
  mlib_u32 s0, s1, s2;                 /* sourdf dbtb */
  mlib_u8 *dl;                         /* pointfr to stbrt of dfstinbtion */
  mlib_u8 *dfnd;                       /* pointfr to fnd of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7, bdd;                /* dfstinbtion dbtb */
  mlib_s32 fmbsk;                      /* fdgf mbsk */
  mlib_s32 i, num;                     /* loop vbribblf */

  sb = (mlib_u32 *) (srd - 1);
  dl = dst;
  dp = (mlib_d64 *) dl;
  dfnd = dl + xsizf - 1;

  vis_blignbddr((void *)0, 7);

  s0 = *sb++;

  if (xsizf >= 8) {

    s1 = sb[0];
    s2 = sb[1];
    sb += 2;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 16; i += 8, sb += 2) {
      t7 = VIS_LD_U8_I(tbblf3, s2 >> 24);
      t6 = VIS_LD_U8_I(tbblf2, s1 & 0xFF);
      t5 = VIS_LD_U8_I(tbblf1, (s1 >> 8) & 0xFF);
      t4 = VIS_LD_U8_I(tbblf0, (s1 >> 16) & 0xFF);
      t3 = VIS_LD_U8_I(tbblf3, s1 >> 24);
      t2 = VIS_LD_U8_I(tbblf2, s0 & 0xFF);
      t1 = VIS_LD_U8_I(tbblf1, (s0 >> 8) & 0xFF);
      t0 = VIS_LD_U8_I(tbblf0, (s0 >> 16) & 0xFF);
      bdd = vis_fbligndbtb(t7, bdd);
      bdd = vis_fbligndbtb(t6, bdd);
      bdd = vis_fbligndbtb(t5, bdd);
      bdd = vis_fbligndbtb(t4, bdd);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = s2;
      s1 = sb[0];
      s2 = sb[1];
      *dp++ = bdd;
    }

    t7 = VIS_LD_U8_I(tbblf3, s2 >> 24);
    t6 = VIS_LD_U8_I(tbblf2, s1 & 0xFF);
    t5 = VIS_LD_U8_I(tbblf1, (s1 >> 8) & 0xFF);
    t4 = VIS_LD_U8_I(tbblf0, (s1 >> 16) & 0xFF);
    t3 = VIS_LD_U8_I(tbblf3, s1 >> 24);
    t2 = VIS_LD_U8_I(tbblf2, s0 & 0xFF);
    t1 = VIS_LD_U8_I(tbblf1, (s0 >> 8) & 0xFF);
    t0 = VIS_LD_U8_I(tbblf0, (s0 >> 16) & 0xFF);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    *dp++ = bdd;
  }

  sp = (mlib_u8 *) sb;
  sp -= 3;

  if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {

    num = (mlib_bddr) dfnd - (mlib_bddr) dp;
    sp += num;
    num++;

    if ((num & 3) == 1) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num--;
    }
    flsf if ((num & 3) == 2) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num -= 2;
    }
    flsf if ((num & 3) == 3) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf2, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num -= 3;
    }

    if (num != 0) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf3, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf2, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
    }

    fmbsk = vis_fdgf8(dp, dfnd);
    vis_pst_8(bdd, dp, fmbsk);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_U8_U8_124_SrdOff2_D1(donst mlib_u8 *srd,
                                             mlib_u8       *dst,
                                             mlib_s32      xsizf,
                                             donst mlib_u8 *tbblf0,
                                             donst mlib_u8 *tbblf1,
                                             donst mlib_u8 *tbblf2,
                                             donst mlib_u8 *tbblf3)
{
  mlib_u32 *sb;                        /* blignfd pointfr to sourdf dbtb */
  mlib_u8 *sp;                         /* pointfr to sourdf dbtb */
  mlib_u32 s0, s1, s2;                 /* sourdf dbtb */
  mlib_u8 *dl;                         /* pointfr to stbrt of dfstinbtion */
  mlib_u8 *dfnd;                       /* pointfr to fnd of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7, bdd;                /* dfstinbtion dbtb */
  mlib_s32 fmbsk;                      /* fdgf mbsk */
  mlib_s32 i, num;                     /* loop vbribblf */

  sb = (mlib_u32 *) (srd - 2);
  dl = dst;
  dp = (mlib_d64 *) dl;
  dfnd = dl + xsizf - 1;

  vis_blignbddr((void *)0, 7);

  s0 = *sb++;

  if (xsizf >= 8) {

    s1 = sb[0];
    s2 = sb[1];
    sb += 2;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 16; i += 8, sb += 2) {
      t7 = VIS_LD_U8_I(tbblf3, (s2 >> 16) & 0xFF);
      t6 = VIS_LD_U8_I(tbblf2, s2 >> 24);
      t5 = VIS_LD_U8_I(tbblf1, s1 & 0xFF);
      t4 = VIS_LD_U8_I(tbblf0, (s1 >> 8) & 0xFF);
      t3 = VIS_LD_U8_I(tbblf3, (s1 >> 16) & 0xFF);
      t2 = VIS_LD_U8_I(tbblf2, s1 >> 24);
      t1 = VIS_LD_U8_I(tbblf1, s0 & 0xFF);
      t0 = VIS_LD_U8_I(tbblf0, (s0 >> 8) & 0xFF);
      bdd = vis_fbligndbtb(t7, bdd);
      bdd = vis_fbligndbtb(t6, bdd);
      bdd = vis_fbligndbtb(t5, bdd);
      bdd = vis_fbligndbtb(t4, bdd);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = s2;
      s1 = sb[0];
      s2 = sb[1];
      *dp++ = bdd;
    }

    t7 = VIS_LD_U8_I(tbblf3, (s2 >> 16) & 0xFF);
    t6 = VIS_LD_U8_I(tbblf2, s2 >> 24);
    t5 = VIS_LD_U8_I(tbblf1, s1 & 0xFF);
    t4 = VIS_LD_U8_I(tbblf0, (s1 >> 8) & 0xFF);
    t3 = VIS_LD_U8_I(tbblf3, (s1 >> 16) & 0xFF);
    t2 = VIS_LD_U8_I(tbblf2, s1 >> 24);
    t1 = VIS_LD_U8_I(tbblf1, s0 & 0xFF);
    t0 = VIS_LD_U8_I(tbblf0, (s0 >> 8) & 0xFF);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    *dp++ = bdd;
  }

  sp = (mlib_u8 *) sb;
  sp -= 2;

  if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {

    num = (mlib_bddr) dfnd - (mlib_bddr) dp;
    sp += num;
    num++;

    if ((num & 3) == 1) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num--;
    }
    flsf if ((num & 3) == 2) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num -= 2;
    }
    flsf if ((num & 3) == 3) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf2, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num -= 3;
    }

    if (num != 0) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf3, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf2, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
    }

    fmbsk = vis_fdgf8(dp, dfnd);
    vis_pst_8(bdd, dp, fmbsk);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_U8_U8_124_SrdOff3_D1(donst mlib_u8 *srd,
                                             mlib_u8       *dst,
                                             mlib_s32      xsizf,
                                             donst mlib_u8 *tbblf0,
                                             donst mlib_u8 *tbblf1,
                                             donst mlib_u8 *tbblf2,
                                             donst mlib_u8 *tbblf3)
{
  mlib_u32 *sb;                        /* blignfd pointfr to sourdf dbtb */
  mlib_u8 *sp;                         /* pointfr to sourdf dbtb */
  mlib_u32 s0, s1, s2;                 /* sourdf dbtb */
  mlib_u8 *dl;                         /* pointfr to stbrt of dfstinbtion */
  mlib_u8 *dfnd;                       /* pointfr to fnd of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7, bdd;                /* dfstinbtion dbtb */
  mlib_s32 fmbsk;                      /* fdgf mbsk */
  mlib_s32 i, num;                     /* loop vbribblf */

  sb = (mlib_u32 *) (srd - 3);
  dl = dst;
  dp = (mlib_d64 *) dl;
  dfnd = dl + xsizf - 1;

  vis_blignbddr((void *)0, 7);

  s0 = *sb++;

  if (xsizf >= 8) {

    s1 = sb[0];
    s2 = sb[1];
    sb += 2;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 16; i += 8, sb += 2) {
      t7 = VIS_LD_U8_I(tbblf3, (s2 >> 8) & 0xFF);
      t6 = VIS_LD_U8_I(tbblf2, (s2 >> 16) & 0xFF);
      t5 = VIS_LD_U8_I(tbblf1, s2 >> 24);
      t4 = VIS_LD_U8_I(tbblf0, s1 & 0xFF);
      t3 = VIS_LD_U8_I(tbblf3, (s1 >> 8) & 0xFF);
      t2 = VIS_LD_U8_I(tbblf2, (s1 >> 16) & 0xFF);
      t1 = VIS_LD_U8_I(tbblf1, s1 >> 24);
      t0 = VIS_LD_U8_I(tbblf0, s0 & 0xFF);
      bdd = vis_fbligndbtb(t7, bdd);
      bdd = vis_fbligndbtb(t6, bdd);
      bdd = vis_fbligndbtb(t5, bdd);
      bdd = vis_fbligndbtb(t4, bdd);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = s2;
      s1 = sb[0];
      s2 = sb[1];
      *dp++ = bdd;
    }

    t7 = VIS_LD_U8_I(tbblf3, (s2 >> 8) & 0xFF);
    t6 = VIS_LD_U8_I(tbblf2, (s2 >> 16) & 0xFF);
    t5 = VIS_LD_U8_I(tbblf1, s2 >> 24);
    t4 = VIS_LD_U8_I(tbblf0, s1 & 0xFF);
    t3 = VIS_LD_U8_I(tbblf3, (s1 >> 8) & 0xFF);
    t2 = VIS_LD_U8_I(tbblf2, (s1 >> 16) & 0xFF);
    t1 = VIS_LD_U8_I(tbblf1, s1 >> 24);
    t0 = VIS_LD_U8_I(tbblf0, s0 & 0xFF);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    *dp++ = bdd;
  }

  sp = (mlib_u8 *) sb;
  sp--;

  if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {

    num = (mlib_bddr) dfnd - (mlib_bddr) dp;
    sp += num;
    num++;

    if ((num & 3) == 1) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num--;
    }
    flsf if ((num & 3) == 2) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num -= 2;
    }
    flsf if ((num & 3) == 3) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf2, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num -= 3;
    }

    if (num != 0) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf3, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf2, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
    }

    fmbsk = vis_fdgf8(dp, dfnd);
    vis_pst_8(bdd, dp, fmbsk);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_U8_U8_1(donst mlib_u8 *srd,
                                mlib_s32      slb,
                                mlib_u8       *dst,
                                mlib_s32      dlb,
                                mlib_s32      xsizf,
                                mlib_s32      ysizf,
                                donst mlib_u8 **tbblf)
{
  mlib_u8 *sl;
  mlib_u8 *dl;
  donst mlib_u8 *tbb = tbblf[0];
  mlib_s32 j, i;

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    mlib_u8 *sp = sl;
    mlib_u8 *dp = dl;
    mlib_s32 off, sizf = xsizf;

    off = (8 - ((mlib_bddr) dp & 7)) & 7;

    off = (off < sizf) ? off : sizf;

    for (i = 0; i < off; i++) {
      *dp++ = tbb[(*sp++)];
      sizf--;
    }

    if (sizf > 0) {

      off = (mlib_bddr) sp & 3;

      if (off == 0) {
        mlib_v_ImbgfLookUp_U8_U8_124_SrdOff0_D1(sp, dp, sizf, tbb, tbb, tbb,
                                                tbb);
      }
      flsf if (off == 1) {
        mlib_v_ImbgfLookUp_U8_U8_124_SrdOff1_D1(sp, dp, sizf, tbb, tbb, tbb,
                                                tbb);
      }
      flsf if (off == 2) {
        mlib_v_ImbgfLookUp_U8_U8_124_SrdOff2_D1(sp, dp, sizf, tbb, tbb, tbb,
                                                tbb);
      }
      flsf {
        mlib_v_ImbgfLookUp_U8_U8_124_SrdOff3_D1(sp, dp, sizf, tbb, tbb, tbb,
                                                tbb);
      }
    }

    sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_U8_U8_2(donst mlib_u8 *srd,
                                mlib_s32      slb,
                                mlib_u8       *dst,
                                mlib_s32      dlb,
                                mlib_s32      xsizf,
                                mlib_s32      ysizf,
                                donst mlib_u8 **tbblf)
{
  mlib_u8 *sl;
  mlib_u8 *dl;
  donst mlib_u8 *tbb;
  mlib_s32 j, i;

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    mlib_u8 *sp = sl;
    mlib_u8 *dp = dl;
    mlib_s32 off, sizf = xsizf * 2;
    donst mlib_u8 *tbb0 = tbblf[0];
    donst mlib_u8 *tbb1 = tbblf[1];

    off = (8 - ((mlib_bddr) dp & 7)) & 7;

    off = (off < sizf) ? off : sizf;

    for (i = 0; i < off - 1; i += 2) {
      *dp++ = tbb0[(*sp++)];
      *dp++ = tbb1[(*sp++)];
      sizf -= 2;
    }

    if ((off & 1) != 0) {
      *dp++ = tbb0[(*sp++)];
      sizf--;
      tbb = tbb0;
      tbb0 = tbb1;
      tbb1 = tbb;
    }

    if (sizf > 0) {

      off = (mlib_bddr) sp & 3;

      if (off == 0) {
        mlib_v_ImbgfLookUp_U8_U8_124_SrdOff0_D1(sp, dp, sizf, tbb0, tbb1, tbb0,
                                                tbb1);
      }
      flsf if (off == 1) {
        mlib_v_ImbgfLookUp_U8_U8_124_SrdOff1_D1(sp, dp, sizf, tbb0, tbb1, tbb0,
                                                tbb1);
      }
      flsf if (off == 2) {
        mlib_v_ImbgfLookUp_U8_U8_124_SrdOff2_D1(sp, dp, sizf, tbb0, tbb1, tbb0,
                                                tbb1);
      }
      flsf {
        mlib_v_ImbgfLookUp_U8_U8_124_SrdOff3_D1(sp, dp, sizf, tbb0, tbb1, tbb0,
                                                tbb1);
      }
    }

    sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_U8_U8_4(donst mlib_u8 *srd,
                                mlib_s32      slb,
                                mlib_u8       *dst,
                                mlib_s32      dlb,
                                mlib_s32      xsizf,
                                mlib_s32      ysizf,
                                donst mlib_u8 **tbblf)
{
  mlib_u8 *sl;
  mlib_u8 *dl;
  donst mlib_u8 *tbb;
  mlib_s32 j;

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    mlib_u8 *sp = sl;
    mlib_u8 *dp = dl;
    donst mlib_u8 *tbb0 = tbblf[0];
    donst mlib_u8 *tbb1 = tbblf[1];
    donst mlib_u8 *tbb2 = tbblf[2];
    donst mlib_u8 *tbb3 = tbblf[3];
    mlib_s32 off, sizf = xsizf * 4;

    off = (8 - ((mlib_bddr) dp & 7)) & 7;

    off = (off < sizf) ? off : sizf;

    if (off >= 4) {
      *dp++ = tbb0[(*sp++)];
      *dp++ = tbb1[(*sp++)];
      *dp++ = tbb2[(*sp++)];
      *dp++ = tbb3[(*sp++)];
      sizf -= 4;
      off -= 4;
    }

    if (off == 1) {
      *dp++ = tbb0[(*sp++)];
      tbb = tbb0;
      tbb0 = tbb1;
      tbb1 = tbb2;
      tbb2 = tbb3;
      tbb3 = tbb;
      sizf--;
    }
    flsf if (off == 2) {
      *dp++ = tbb0[(*sp++)];
      *dp++ = tbb1[(*sp++)];
      tbb = tbb0;
      tbb0 = tbb2;
      tbb2 = tbb;
      tbb = tbb1;
      tbb1 = tbb3;
      tbb3 = tbb;
      sizf -= 2;
    }
    flsf if (off == 3) {
      *dp++ = tbb0[(*sp++)];
      *dp++ = tbb1[(*sp++)];
      *dp++ = tbb2[(*sp++)];
      tbb = tbb3;
      tbb3 = tbb2;
      tbb2 = tbb1;
      tbb1 = tbb0;
      tbb0 = tbb;
      sizf -= 3;
    }

    if (sizf > 0) {

      off = (mlib_bddr) sp & 3;

      if (off == 0) {
        mlib_v_ImbgfLookUp_U8_U8_124_SrdOff0_D1(sp, dp, sizf, tbb0, tbb1, tbb2,
                                                tbb3);
      }
      flsf if (off == 1) {
        mlib_v_ImbgfLookUp_U8_U8_124_SrdOff1_D1(sp, dp, sizf, tbb0, tbb1, tbb2,
                                                tbb3);
      }
      flsf if (off == 2) {
        mlib_v_ImbgfLookUp_U8_U8_124_SrdOff2_D1(sp, dp, sizf, tbb0, tbb1, tbb2,
                                                tbb3);
      }
      flsf {
        mlib_v_ImbgfLookUp_U8_U8_124_SrdOff3_D1(sp, dp, sizf, tbb0, tbb1, tbb2,
                                                tbb3);
      }
    }

    sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_U8_U8_3_SrdOff0_D1(donst mlib_u8 *srd,
                                           mlib_u8       *dst,
                                           mlib_s32      xsizf,
                                           donst mlib_u8 *tbblf0,
                                           donst mlib_u8 *tbblf1,
                                           donst mlib_u8 *tbblf2)
{
  mlib_u32 *sb;                        /* blignfd pointfr to sourdf dbtb */
  mlib_u8 *sp;                         /* pointfr to sourdf dbtb */
  mlib_u32 s0, s1;                     /* sourdf dbtb */
  mlib_u8 *dl;                         /* pointfr to stbrt of dfstinbtion */
  mlib_u8 *dfnd;                       /* pointfr to fnd of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7, bdd;                /* dfstinbtion dbtb */
  mlib_s32 fmbsk;                      /* fdgf mbsk */
  mlib_s32 i, num;                     /* loop vbribblf */
  donst mlib_u8 *tbblf;

  sb = (mlib_u32 *) srd;
  dl = dst;
  dp = (mlib_d64 *) dl;
  dfnd = dl + xsizf - 1;

  vis_blignbddr((void *)0, 7);

  if (xsizf >= 8) {

    s0 = sb[0];
    s1 = sb[1];
    sb += 2;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 16; i += 8, sb += 2) {
      t7 = VIS_LD_U8_I(tbblf1, s1 & 0xFF);
      t6 = VIS_LD_U8_I(tbblf0, (s1 >> 8) & 0xFF);
      t5 = VIS_LD_U8_I(tbblf2, (s1 >> 16) & 0xFF);
      t4 = VIS_LD_U8_I(tbblf1, s1 >> 24);
      t3 = VIS_LD_U8_I(tbblf0, s0 & 0xFF);
      t2 = VIS_LD_U8_I(tbblf2, (s0 >> 8) & 0xFF);
      t1 = VIS_LD_U8_I(tbblf1, (s0 >> 16) & 0xFF);
      t0 = VIS_LD_U8_I(tbblf0, s0 >> 24);
      bdd = vis_fbligndbtb(t7, bdd);
      bdd = vis_fbligndbtb(t6, bdd);
      bdd = vis_fbligndbtb(t5, bdd);
      bdd = vis_fbligndbtb(t4, bdd);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      tbblf = tbblf0;
      tbblf0 = tbblf2;
      tbblf2 = tbblf1;
      tbblf1 = tbblf;
      s0 = sb[0];
      s1 = sb[1];
      *dp++ = bdd;
    }

    t7 = VIS_LD_U8_I(tbblf1, s1 & 0xFF);
    t6 = VIS_LD_U8_I(tbblf0, (s1 >> 8) & 0xFF);
    t5 = VIS_LD_U8_I(tbblf2, (s1 >> 16) & 0xFF);
    t4 = VIS_LD_U8_I(tbblf1, s1 >> 24);
    t3 = VIS_LD_U8_I(tbblf0, s0 & 0xFF);
    t2 = VIS_LD_U8_I(tbblf2, (s0 >> 8) & 0xFF);
    t1 = VIS_LD_U8_I(tbblf1, (s0 >> 16) & 0xFF);
    t0 = VIS_LD_U8_I(tbblf0, s0 >> 24);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    tbblf = tbblf0;
    tbblf0 = tbblf2;
    tbblf2 = tbblf1;
    tbblf1 = tbblf;
    *dp++ = bdd;
  }

  sp = (mlib_u8 *) sb;

  if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {

    num = (mlib_bddr) dfnd - (mlib_bddr) dp;
    sp += num;
    num++;
    i = num - 3 * (num / 3);

    if (i == 2) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num -= 2;
    }
    flsf if (i == 1) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num--;
    }

#prbgmb pipfloop(0)
    for (i = 0; i < num; i += 3) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf2, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
    }

    fmbsk = vis_fdgf8(dp, dfnd);
    vis_pst_8(bdd, dp, fmbsk);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_U8_U8_3_SrdOff1_D1(donst mlib_u8 *srd,
                                           mlib_u8       *dst,
                                           mlib_s32      xsizf,
                                           donst mlib_u8 *tbblf0,
                                           donst mlib_u8 *tbblf1,
                                           donst mlib_u8 *tbblf2)
{
  mlib_u32 *sb;                        /* blignfd pointfr to sourdf dbtb */
  mlib_u8 *sp;                         /* pointfr to sourdf dbtb */
  mlib_u32 s0, s1, s2;                 /* sourdf dbtb */
  mlib_u8 *dl;                         /* pointfr to stbrt of dfstinbtion */
  mlib_u8 *dfnd;                       /* pointfr to fnd of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7, bdd;                /* dfstinbtion dbtb */
  mlib_s32 fmbsk;                      /* fdgf mbsk */
  mlib_s32 i, num;                     /* loop vbribblf */
  donst mlib_u8 *tbblf;

  sb = (mlib_u32 *) (srd - 1);
  dl = dst;
  dp = (mlib_d64 *) dl;
  dfnd = dl + xsizf - 1;

  vis_blignbddr((void *)0, 7);

  s0 = *sb++;

  if (xsizf >= 8) {

    s1 = sb[0];
    s2 = sb[1];
    sb += 2;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 16; i += 8, sb += 2) {
      t7 = VIS_LD_U8_I(tbblf1, s2 >> 24);
      t6 = VIS_LD_U8_I(tbblf0, s1 & 0xFF);
      t5 = VIS_LD_U8_I(tbblf2, (s1 >> 8) & 0xFF);
      t4 = VIS_LD_U8_I(tbblf1, (s1 >> 16) & 0xFF);
      t3 = VIS_LD_U8_I(tbblf0, s1 >> 24);
      t2 = VIS_LD_U8_I(tbblf2, s0 & 0xFF);
      t1 = VIS_LD_U8_I(tbblf1, (s0 >> 8) & 0xFF);
      t0 = VIS_LD_U8_I(tbblf0, (s0 >> 16) & 0xFF);
      bdd = vis_fbligndbtb(t7, bdd);
      bdd = vis_fbligndbtb(t6, bdd);
      bdd = vis_fbligndbtb(t5, bdd);
      bdd = vis_fbligndbtb(t4, bdd);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      tbblf = tbblf0;
      tbblf0 = tbblf2;
      tbblf2 = tbblf1;
      tbblf1 = tbblf;
      s0 = s2;
      s1 = sb[0];
      s2 = sb[1];
      *dp++ = bdd;
    }

    t7 = VIS_LD_U8_I(tbblf1, s2 >> 24);
    t6 = VIS_LD_U8_I(tbblf0, s1 & 0xFF);
    t5 = VIS_LD_U8_I(tbblf2, (s1 >> 8) & 0xFF);
    t4 = VIS_LD_U8_I(tbblf1, (s1 >> 16) & 0xFF);
    t3 = VIS_LD_U8_I(tbblf0, s1 >> 24);
    t2 = VIS_LD_U8_I(tbblf2, s0 & 0xFF);
    t1 = VIS_LD_U8_I(tbblf1, (s0 >> 8) & 0xFF);
    t0 = VIS_LD_U8_I(tbblf0, (s0 >> 16) & 0xFF);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    tbblf = tbblf0;
    tbblf0 = tbblf2;
    tbblf2 = tbblf1;
    tbblf1 = tbblf;
    *dp++ = bdd;
  }

  sp = (mlib_u8 *) sb;
  sp -= 3;

  if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {

    num = (mlib_bddr) dfnd - (mlib_bddr) dp;
    sp += num;
    num++;
    i = num - 3 * (num / 3);

    if (i == 2) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num -= 2;
    }
    flsf if (i == 1) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num--;
    }

#prbgmb pipfloop(0)
    for (i = 0; i < num; i += 3) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf2, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
    }

    fmbsk = vis_fdgf8(dp, dfnd);
    vis_pst_8(bdd, dp, fmbsk);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_U8_U8_3_SrdOff2_D1(donst mlib_u8 *srd,
                                           mlib_u8       *dst,
                                           mlib_s32      xsizf,
                                           donst mlib_u8 *tbblf0,
                                           donst mlib_u8 *tbblf1,
                                           donst mlib_u8 *tbblf2)
{
  mlib_u32 *sb;                        /* blignfd pointfr to sourdf dbtb */
  mlib_u8 *sp;                         /* pointfr to sourdf dbtb */
  mlib_u32 s0, s1, s2;                 /* sourdf dbtb */
  mlib_u8 *dl;                         /* pointfr to stbrt of dfstinbtion */
  mlib_u8 *dfnd;                       /* pointfr to fnd of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7, bdd;                /* dfstinbtion dbtb */
  mlib_s32 fmbsk;                      /* fdgf mbsk */
  mlib_s32 i, num;                     /* loop vbribblf */
  donst mlib_u8 *tbblf;

  sb = (mlib_u32 *) (srd - 2);
  dl = dst;
  dp = (mlib_d64 *) dl;
  dfnd = dl + xsizf - 1;

  vis_blignbddr((void *)0, 7);

  s0 = *sb++;

  if (xsizf >= 8) {

    s1 = sb[0];
    s2 = sb[1];
    sb += 2;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 16; i += 8, sb += 2) {
      t7 = VIS_LD_U8_I(tbblf1, (s2 >> 16) & 0xFF);
      t6 = VIS_LD_U8_I(tbblf0, s2 >> 24);
      t5 = VIS_LD_U8_I(tbblf2, s1 & 0xFF);
      t4 = VIS_LD_U8_I(tbblf1, (s1 >> 8) & 0xFF);
      t3 = VIS_LD_U8_I(tbblf0, (s1 >> 16) & 0xFF);
      t2 = VIS_LD_U8_I(tbblf2, s1 >> 24);
      t1 = VIS_LD_U8_I(tbblf1, s0 & 0xFF);
      t0 = VIS_LD_U8_I(tbblf0, (s0 >> 8) & 0xFF);
      bdd = vis_fbligndbtb(t7, bdd);
      bdd = vis_fbligndbtb(t6, bdd);
      bdd = vis_fbligndbtb(t5, bdd);
      bdd = vis_fbligndbtb(t4, bdd);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      tbblf = tbblf0;
      tbblf0 = tbblf2;
      tbblf2 = tbblf1;
      tbblf1 = tbblf;
      s0 = s2;
      s1 = sb[0];
      s2 = sb[1];
      *dp++ = bdd;
    }

    t7 = VIS_LD_U8_I(tbblf1, (s2 >> 16) & 0xFF);
    t6 = VIS_LD_U8_I(tbblf0, s2 >> 24);
    t5 = VIS_LD_U8_I(tbblf2, s1 & 0xFF);
    t4 = VIS_LD_U8_I(tbblf1, (s1 >> 8) & 0xFF);
    t3 = VIS_LD_U8_I(tbblf0, (s1 >> 16) & 0xFF);
    t2 = VIS_LD_U8_I(tbblf2, s1 >> 24);
    t1 = VIS_LD_U8_I(tbblf1, s0 & 0xFF);
    t0 = VIS_LD_U8_I(tbblf0, (s0 >> 8) & 0xFF);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    tbblf = tbblf0;
    tbblf0 = tbblf2;
    tbblf2 = tbblf1;
    tbblf1 = tbblf;
    *dp++ = bdd;
  }

  sp = (mlib_u8 *) sb;
  sp -= 2;

  if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {

    num = (mlib_bddr) dfnd - (mlib_bddr) dp;
    sp += num;
    num++;
    i = num - 3 * (num / 3);

    if (i == 2) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num -= 2;
    }
    flsf if (i == 1) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num--;
    }

#prbgmb pipfloop(0)
    for (i = 0; i < num; i += 3) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf2, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
    }

    fmbsk = vis_fdgf8(dp, dfnd);
    vis_pst_8(bdd, dp, fmbsk);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_U8_U8_3_SrdOff3_D1(donst mlib_u8 *srd,
                                           mlib_u8       *dst,
                                           mlib_s32      xsizf,
                                           donst mlib_u8 *tbblf0,
                                           donst mlib_u8 *tbblf1,
                                           donst mlib_u8 *tbblf2)
{
  mlib_u32 *sb;                        /* blignfd pointfr to sourdf dbtb */
  mlib_u8 *sp;                         /* pointfr to sourdf dbtb */
  mlib_u32 s0, s1, s2;                 /* sourdf dbtb */
  mlib_u8 *dl;                         /* pointfr to stbrt of dfstinbtion */
  mlib_u8 *dfnd;                       /* pointfr to fnd of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7, bdd;                /* dfstinbtion dbtb */
  mlib_s32 fmbsk;                      /* fdgf mbsk */
  mlib_s32 i, num;                     /* loop vbribblf */
  donst mlib_u8 *tbblf;

  sb = (mlib_u32 *) (srd - 3);
  dl = dst;
  dp = (mlib_d64 *) dl;
  dfnd = dl + xsizf - 1;

  vis_blignbddr((void *)0, 7);

  s0 = *sb++;

  if (xsizf >= 8) {

    s1 = sb[0];
    s2 = sb[1];
    sb += 2;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 16; i += 8, sb += 2) {
      t7 = VIS_LD_U8_I(tbblf1, (s2 >> 8) & 0xFF);
      t6 = VIS_LD_U8_I(tbblf0, (s2 >> 16) & 0xFF);
      t5 = VIS_LD_U8_I(tbblf2, s2 >> 24);
      t4 = VIS_LD_U8_I(tbblf1, s1 & 0xFF);
      t3 = VIS_LD_U8_I(tbblf0, (s1 >> 8) & 0xFF);
      t2 = VIS_LD_U8_I(tbblf2, (s1 >> 16) & 0xFF);
      t1 = VIS_LD_U8_I(tbblf1, s1 >> 24);
      t0 = VIS_LD_U8_I(tbblf0, s0 & 0xFF);
      bdd = vis_fbligndbtb(t7, bdd);
      bdd = vis_fbligndbtb(t6, bdd);
      bdd = vis_fbligndbtb(t5, bdd);
      bdd = vis_fbligndbtb(t4, bdd);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      tbblf = tbblf0;
      tbblf0 = tbblf2;
      tbblf2 = tbblf1;
      tbblf1 = tbblf;
      s0 = s2;
      s1 = sb[0];
      s2 = sb[1];
      *dp++ = bdd;
    }

    t7 = VIS_LD_U8_I(tbblf1, (s2 >> 8) & 0xFF);
    t6 = VIS_LD_U8_I(tbblf0, (s2 >> 16) & 0xFF);
    t5 = VIS_LD_U8_I(tbblf2, s2 >> 24);
    t4 = VIS_LD_U8_I(tbblf1, s1 & 0xFF);
    t3 = VIS_LD_U8_I(tbblf0, (s1 >> 8) & 0xFF);
    t2 = VIS_LD_U8_I(tbblf2, (s1 >> 16) & 0xFF);
    t1 = VIS_LD_U8_I(tbblf1, s1 >> 24);
    t0 = VIS_LD_U8_I(tbblf0, s0 & 0xFF);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    tbblf = tbblf0;
    tbblf0 = tbblf2;
    tbblf2 = tbblf1;
    tbblf1 = tbblf;
    *dp++ = bdd;
  }

  sp = (mlib_u8 *) sb;
  sp--;

  if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {

    num = (mlib_bddr) dfnd - (mlib_bddr) dp;
    sp += num;
    num++;
    i = num - 3 * (num / 3);

    if (i == 2) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num -= 2;
    }
    flsf if (i == 1) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
      num--;
    }

#prbgmb pipfloop(0)
    for (i = 0; i < num; i += 3) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf2, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U8_I(tbblf0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
    }

    fmbsk = vis_fdgf8(dp, dfnd);
    vis_pst_8(bdd, dp, fmbsk);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_U8_U8_3(donst mlib_u8 *srd,
                                mlib_s32      slb,
                                mlib_u8       *dst,
                                mlib_s32      dlb,
                                mlib_s32      xsizf,
                                mlib_s32      ysizf,
                                donst mlib_u8 **tbblf)
{
  mlib_u8 *sl;
  mlib_u8 *dl;
  donst mlib_u8 *tbb;
  mlib_s32 j, i;

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    mlib_u8 *sp = sl;
    mlib_u8 *dp = dl;
    donst mlib_u8 *tbb0 = tbblf[0];
    donst mlib_u8 *tbb1 = tbblf[1];
    donst mlib_u8 *tbb2 = tbblf[2];
    mlib_s32 off, sizf = xsizf * 3;

    off = (8 - ((mlib_bddr) dp & 7)) & 7;

    off = (off < sizf) ? off : sizf;

    for (i = 0; i < off - 2; i += 3) {
      *dp++ = tbb0[(*sp++)];
      *dp++ = tbb1[(*sp++)];
      *dp++ = tbb2[(*sp++)];
      sizf -= 3;
    }

    off -= i;

    if (off == 1) {
      *dp++ = tbb0[(*sp++)];
      tbb = tbb0;
      tbb0 = tbb1;
      tbb1 = tbb2;
      tbb2 = tbb;
      sizf--;
    }
    flsf if (off == 2) {
      *dp++ = tbb0[(*sp++)];
      *dp++ = tbb1[(*sp++)];
      tbb = tbb2;
      tbb2 = tbb1;
      tbb1 = tbb0;
      tbb0 = tbb;
      sizf -= 2;
    }

    if (sizf > 0) {

      off = (mlib_bddr) sp & 3;

      if (off == 0) {
        mlib_v_ImbgfLookUp_U8_U8_3_SrdOff0_D1(sp, dp, sizf, tbb0, tbb1, tbb2);
      }
      flsf if (off == 1) {
        mlib_v_ImbgfLookUp_U8_U8_3_SrdOff1_D1(sp, dp, sizf, tbb0, tbb1, tbb2);
      }
      flsf if (off == 2) {
        mlib_v_ImbgfLookUp_U8_U8_3_SrdOff2_D1(sp, dp, sizf, tbb0, tbb1, tbb2);
      }
      flsf {
        mlib_v_ImbgfLookUp_U8_U8_3_SrdOff3_D1(sp, dp, sizf, tbb0, tbb1, tbb2);
      }
    }

    sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
