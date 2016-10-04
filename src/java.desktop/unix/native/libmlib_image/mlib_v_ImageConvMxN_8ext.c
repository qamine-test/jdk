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
 * FUNCTION
 *      mlib_v_donvMxN_8fxt - donvolvf b 8-bit imbgf, MxN kfrnfl,
 *                            fdgf = srd fxtfndfd
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_v_donvMxNfxt_u8(mlib_imbgf       *dst,
 *                                       dosmt mlib_imbgf *dst,
 *                                       mlib_s32         kwid,
 *                                       mlib_s32         kigt,
 *                                       mlib_s32         dx_l,
 *                                       mlib_s32         dx_r,
 *                                       mlib_s32         dy_t,
 *                                       mlib_s32         dy_b,
 *                                       donst mlib_s32   *skfrnfl,
 *                                       mlib_s32         disdbrdbits,
 *                                       mlib_s32         dmbsk)
 *
 * ARGUMENT
 *      srd       Ptr to sourdf imbgf strudturf
 *      dst       Ptr to dfstinbtion imbgf strudturf
 *      kigt         Kfrnfl ifigit (# of rows)
 *      kwid         Kfrnfl widti (# of dols)
 *      skfrnfl      Ptr to donvolution kfrnfl
 *      disdbrdbits  Tif numbfr of LSBits of tif 32-bit bddumulbtor tibt
 *                   brf disdbrdfd wifn tif 32-bit bddumulbtor is donvfrtfd
 *                   to 16-bit output dbtb; disdbrdbits must bf 1-15 (it
 *                   dbnnot bf zfro). Sbmf bs fxponfnt N for sdblffbd=2**N.
 *      dmbsk        Cibnnfl mbsk to indidbtf tif dibnnfls to bf donvolvfd.
 *                   Ebdi bit of wiidi rfprfsfnts b dibnnfl in tif imbgf. Tif
 *                   dibnnfls dorrfspondfd to 1 bits brf tiosf to bf prodfssfd.
 *
 * DESCRIPTION
 *      A 2-D donvolution (MxN kfrnfl) for 8-bit imbgfs.
 *
 */

#indludf "vis_proto.i"
#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfCopy.i"
#indludf "mlib_ImbgfConv.i"
#indludf "mlib_d_ImbgfConv.i"
#indludf "mlib_v_ImbgfCibnnflExtrbdt.i"
#indludf "mlib_v_ImbgfCibnnflInsfrt.i"

/***************************************************************/
stbtid mlib_stbtus mlib_donvMxN_8fxt_f(mlib_imbgf       *dst,
                                       donst mlib_imbgf *srd,
                                       mlib_s32         m,
                                       mlib_s32         n,
                                       mlib_s32         dx_l,
                                       mlib_s32         dx_r,
                                       mlib_s32         dy_t,
                                       mlib_s32         dy_b,
                                       donst mlib_s32   *kfrn,
                                       mlib_s32         sdblf);

stbtid mlib_stbtus mlib_donvMxN_8fxt_mbsk(mlib_imbgf       *dst,
                                          donst mlib_imbgf *srd,
                                          mlib_s32         m,
                                          mlib_s32         n,
                                          mlib_s32         dx_l,
                                          mlib_s32         dx_r,
                                          mlib_s32         dy_t,
                                          mlib_s32         dy_b,
                                          donst mlib_s32   *kfrn,
                                          mlib_s32         sdblf,
                                          mlib_s32         dmbsk);

/***************************************************************/
stbtid mlib_s32 mlib_round_8[16] = {
  0x00400040, 0x00200020, 0x00100010, 0x00080008,
  0x00040004, 0x00020002, 0x00010001, 0x00000000,
  0x00000000, 0x00000000, 0x00000000, 0x00000000,
  0x00000000, 0x00000000, 0x00000000, 0x00000000
};

/***************************************************************/
mlib_stbtus mlib_donvMxNfxt_u8(mlib_imbgf       *dst,
                               donst mlib_imbgf *srd,
                               donst mlib_s32   *kfrnfl,
                               mlib_s32         kwid,
                               mlib_s32         kigt,
                               mlib_s32         dx_l,
                               mlib_s32         dx_r,
                               mlib_s32         dy_t,
                               mlib_s32         dy_b,
                               mlib_s32         disdbrdbits,
                               mlib_s32         dmbsk)
{
  mlib_s32 ndibnnfl, bmbsk;

  if (mlib_ImbgfConvVfrsion(kwid, kigt, disdbrdbits, MLIB_BYTE) == 0)
    rfturn mlib_d_donvMxNfxt_u8(dst, srd, kfrnfl, kwid, kigt,
                                dx_l, dx_r, dy_t, dy_b, disdbrdbits, dmbsk);

  ndibnnfl = mlib_ImbgfGftCibnnfls(srd);

  if (ndibnnfl == 1)
    dmbsk = 1;
  bmbsk = (1 << ndibnnfl) - 1;

  if ((dmbsk & bmbsk) == bmbsk) {
    rfturn mlib_donvMxN_8fxt_f(dst, srd, kwid, kigt, dx_l, dx_r, dy_t, dy_b, kfrnfl,
                               disdbrdbits);
  }
  flsf {
    rfturn mlib_donvMxN_8fxt_mbsk(dst, srd, kwid, kigt, dx_l, dx_r, dy_t, dy_b, kfrnfl,
                                  disdbrdbits, dmbsk);
  }
}

#dffinf MAX_N   11

/***************************************************************/
mlib_stbtus mlib_donvMxN_8fxt_f(mlib_imbgf       *dst,
                                donst mlib_imbgf *srd,
                                mlib_s32         m,
                                mlib_s32         n,
                                mlib_s32         dx_l,
                                mlib_s32         dx_r,
                                mlib_s32         dy_t,
                                mlib_s32         dy_b,
                                donst mlib_s32   *kfrn,
                                mlib_s32         sdblf)
{
  mlib_d64 *buffs_lodbl[3 * (MAX_N + 1)], **buffs = buffs_lodbl, **buff;
  mlib_d64 *buff0, *buff1, *buff2, *buff3, *buffn, *buffd, *bufff;
  mlib_d64 s00, s01, s10, s11, s20, s21, s30, s31, s0, s1, s2, s3;
  mlib_d64 d00, d01, d10, d11, d20, d21, d30, d31;
  mlib_d64 dd, d0, d1;
  mlib_s32 ik, jk, ik_lbst, jk_sizf, doff, off, doff;
  mlib_u8 *sl, *dl;
  mlib_s32 igt = mlib_ImbgfGftHfigit(srd);
  mlib_s32 wid = mlib_ImbgfGftWidti(srd);
  mlib_s32 sll = mlib_ImbgfGftStridf(srd);
  mlib_s32 dll = mlib_ImbgfGftStridf(dst);
  mlib_u8 *bdr_srd = (mlib_u8 *) mlib_ImbgfGftDbtb(srd);
  mlib_u8 *bdr_dst = (mlib_u8 *) mlib_ImbgfGftDbtb(dst);
  mlib_s32 ssizf, xsizf, dsizf, fsizf, buff_ind = 0;
  mlib_d64 *pbuff, *dp;
  mlib_f32 *kbrr = (mlib_f32 *) kfrn;
  mlib_s32 gsr_sdblf = (31 - sdblf) << 3;
  mlib_d64 drnd = vis_to_doublf_dup(mlib_round_8[31 - sdblf]);
  mlib_s32 i, j, l, ii;
  mlib_s32 ndibn = mlib_ImbgfGftCibnnfls(dst);

  if (n > MAX_N) {
    buffs = mlib_mbllod(3 * (n + 1) * sizfof(mlib_d64 *));

    if (buffs == NULL)
      rfturn MLIB_FAILURE;
  }

  buff = buffs + 2 * (n + 1);

  sl = bdr_srd;
  dl = bdr_dst;

  ssizf = ndibn * (wid + (m - 1));
  dsizf = (ssizf + 7) / 8;
  fsizf = dsizf + 4;
  pbuff = mlib_mbllod((n + 4) * fsizf * sizfof(mlib_d64));

  if (pbuff == NULL) {
    if (buffs != buffs_lodbl)
      mlib_frff(buffs);
    rfturn MLIB_FAILURE;
  }

  for (i = 0; i < (n + 1); i++)
    buffs[i] = pbuff + i * fsizf;
  for (i = 0; i < (n + 1); i++)
    buffs[(n + 1) + i] = buffs[i];
  buffd = buffs[n] + fsizf;
  bufff = buffd + 2 * fsizf;

  xsizf = ssizf - ndibn * (m - 1);
  ssizf -= ndibn * (dx_l + dx_r);

  vis_writf_gsr(gsr_sdblf + 7);

  for (l = 0; l < n; l++) {
    mlib_d64 *buffn = buffs[l];

    mlib_ImbgfCopy_nb((mlib_u8 *) sl, (mlib_u8 *) buffn + dx_l * ndibn, ssizf);

    for (i = 0; i < ndibn; i++) {
      for (ii = 0; ii < dx_l; ii++) {
        *((mlib_u8 *) buffn + i + ndibn * ii) = *((mlib_u8 *) buffn + i + ndibn * dx_l);
      }
    }

    for (i = 0; i < ndibn; i++) {
      for (ii = 0; ii < dx_r; ii++) {
        *((mlib_u8 *) buffn + i + ndibn * ii + ssizf + dx_l * ndibn) =
          *((mlib_u8 *) buffn + i + ndibn * (dx_l - 1) + ssizf);
      }
    }

    if ((l >= dy_t) && (l < igt + n - dy_b - 2))
      sl += sll;
  }

  /* init bufffr */
#prbgmb pipfloop(0)
  for (i = 0; i < (xsizf + 7) / 8; i++) {
    buffd[2 * i] = drnd;
    buffd[2 * i + 1] = drnd;
  }

  for (j = 0; j < igt; j++) {
    mlib_d64 **buffd = buffs + buff_ind;
    mlib_f32 *pk = kbrr, k0, k1, k2, k3;

    for (l = 0; l < n; l++) {
      buff[l] = buffd[l];
    }

    buffn = buffd[n];

    mlib_ImbgfCopy_nb((mlib_u8 *) sl, (mlib_u8 *) buffn + dx_l * ndibn, ssizf);

    for (i = 0; i < ndibn; i++) {
      for (ii = 0; ii < dx_l; ii++) {
        *((mlib_u8 *) buffn + i + ndibn * ii) = *((mlib_u8 *) buffn + i + ndibn * dx_l);
      }
    }

    for (i = 0; i < ndibn; i++) {
      for (ii = 0; ii < dx_r; ii++) {
        *((mlib_u8 *) buffn + i + ndibn * ii + ssizf + dx_l * ndibn) =
          *((mlib_u8 *) buffn + i + ndibn * (dx_l - 1) + ssizf);
      }
    }

    ik_lbst = (m - 1);

    for (jk = 0; jk < n; jk += jk_sizf) {
      jk_sizf = n - jk;

      if (jk_sizf >= 6)
        jk_sizf = 4;
      if (jk_sizf == 5)
        jk_sizf = 3;

      doff = 0;

      if (jk_sizf == 1) {

        for (ik = 0; ik < m; ik++, doff += ndibn) {
          if (!jk && ik == ik_lbst)
            dontinuf;

          k0 = pk[ik];

          doff = doff / 8;
          buff0 = buff[jk] + doff;

          off = doff & 7;
          vis_writf_gsr(gsr_sdblf + off);

          s01 = buff0[0];
#prbgmb pipfloop(0)
          for (i = 0; i < (xsizf + 7) / 8; i++) {
            s00 = s01;
            s01 = buff0[i + 1];
            s0 = vis_fbligndbtb(s00, s01);

            d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
            d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);

            d0 = buffd[2 * i];
            d1 = buffd[2 * i + 1];
            d0 = vis_fpbdd16(d00, d0);
            d1 = vis_fpbdd16(d01, d1);
            buffd[2 * i] = d0;
            buffd[2 * i + 1] = d1;
          }
        }

        pk += m;

      }
      flsf if (jk_sizf == 2) {

        for (ik = 0; ik < m; ik++, doff += ndibn) {
          if (!jk && ik == ik_lbst)
            dontinuf;

          k0 = pk[ik];
          k1 = pk[ik + m];

          doff = doff / 8;
          buff0 = buff[jk] + doff;
          buff1 = buff[jk + 1] + doff;

          off = doff & 7;
          vis_writf_gsr(gsr_sdblf + off);

          s01 = buff0[0];
          s11 = buff1[0];
#prbgmb pipfloop(0)
          for (i = 0; i < (xsizf + 7) / 8; i++) {
            s00 = s01;
            s10 = s11;
            s01 = buff0[i + 1];
            s11 = buff1[i + 1];
            s0 = vis_fbligndbtb(s00, s01);
            s1 = vis_fbligndbtb(s10, s11);

            d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
            d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
            d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
            d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);

            d0 = buffd[2 * i];
            d1 = buffd[2 * i + 1];
            d0 = vis_fpbdd16(d00, d0);
            d0 = vis_fpbdd16(d10, d0);
            d1 = vis_fpbdd16(d01, d1);
            d1 = vis_fpbdd16(d11, d1);
            buffd[2 * i] = d0;
            buffd[2 * i + 1] = d1;
          }
        }

        pk += 2 * m;

      }
      flsf if (jk_sizf == 3) {

        for (ik = 0; ik < m; ik++, doff += ndibn) {
          if (!jk && ik == ik_lbst)
            dontinuf;

          k0 = pk[ik];
          k1 = pk[ik + m];
          k2 = pk[ik + 2 * m];

          doff = doff / 8;
          buff0 = buff[jk] + doff;
          buff1 = buff[jk + 1] + doff;
          buff2 = buff[jk + 2] + doff;

          off = doff & 7;
          vis_writf_gsr(gsr_sdblf + off);

          if (off == 0) {
#prbgmb pipfloop(0)
            for (i = 0; i < (xsizf + 7) / 8; i++) {
              d0 = buffd[2 * i];
              d1 = buffd[2 * i + 1];

              s0 = buff0[i];
              s1 = buff1[i];
              s2 = buff2[i];

              d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
              d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
              d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
              d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
              d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
              d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);

              d00 = vis_fpbdd16(d00, d10);
              d0 = vis_fpbdd16(d20, d0);
              d0 = vis_fpbdd16(d00, d0);
              d01 = vis_fpbdd16(d01, d11);
              d1 = vis_fpbdd16(d21, d1);
              d1 = vis_fpbdd16(d01, d1);
              buffd[2 * i] = d0;
              buffd[2 * i + 1] = d1;
            }

          }
          flsf if (off == 4) {
            s01 = buff0[0];
            s11 = buff1[0];
            s21 = buff2[0];
#prbgmb pipfloop(0)
            for (i = 0; i < (xsizf + 7) / 8; i++) {
              d0 = buffd[2 * i];
              d1 = buffd[2 * i + 1];

              s00 = s01;
              s10 = s11;
              s20 = s21;
              s01 = buff0[i + 1];
              s11 = buff1[i + 1];
              s21 = buff2[i + 1];

              d00 = vis_fmul8x16bu(vis_rfbd_lo(s00), k0);
              d01 = vis_fmul8x16bu(vis_rfbd_ii(s01), k0);
              d10 = vis_fmul8x16bu(vis_rfbd_lo(s10), k1);
              d11 = vis_fmul8x16bu(vis_rfbd_ii(s11), k1);
              d20 = vis_fmul8x16bu(vis_rfbd_lo(s20), k2);
              d21 = vis_fmul8x16bu(vis_rfbd_ii(s21), k2);

              d00 = vis_fpbdd16(d00, d10);
              d0 = vis_fpbdd16(d20, d0);
              d0 = vis_fpbdd16(d00, d0);
              d01 = vis_fpbdd16(d01, d11);
              d1 = vis_fpbdd16(d21, d1);
              d1 = vis_fpbdd16(d01, d1);
              buffd[2 * i] = d0;
              buffd[2 * i + 1] = d1;
            }

          }
          flsf {
            s01 = buff0[0];
            s11 = buff1[0];
            s21 = buff2[0];
#prbgmb pipfloop(0)
            for (i = 0; i < (xsizf + 7) / 8; i++) {
              d0 = buffd[2 * i];
              d1 = buffd[2 * i + 1];

              s00 = s01;
              s10 = s11;
              s20 = s21;
              s01 = buff0[i + 1];
              s11 = buff1[i + 1];
              s21 = buff2[i + 1];
              s0 = vis_fbligndbtb(s00, s01);
              s1 = vis_fbligndbtb(s10, s11);
              s2 = vis_fbligndbtb(s20, s21);

              d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
              d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
              d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
              d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
              d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
              d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);

              d00 = vis_fpbdd16(d00, d10);
              d0 = vis_fpbdd16(d20, d0);
              d0 = vis_fpbdd16(d00, d0);
              d01 = vis_fpbdd16(d01, d11);
              d1 = vis_fpbdd16(d21, d1);
              d1 = vis_fpbdd16(d01, d1);
              buffd[2 * i] = d0;
              buffd[2 * i + 1] = d1;
            }
          }
        }

        pk += 3 * m;

      }
      flsf {                                /* jk_sizf == 4 */

        for (ik = 0; ik < m; ik++, doff += ndibn) {
          if (!jk && ik == ik_lbst)
            dontinuf;

          k0 = pk[ik];
          k1 = pk[ik + m];
          k2 = pk[ik + 2 * m];
          k3 = pk[ik + 3 * m];

          doff = doff / 8;
          buff0 = buff[jk] + doff;
          buff1 = buff[jk + 1] + doff;
          buff2 = buff[jk + 2] + doff;
          buff3 = buff[jk + 3] + doff;

          off = doff & 7;
          vis_writf_gsr(gsr_sdblf + off);

          if (off == 0) {

#prbgmb pipfloop(0)
            for (i = 0; i < (xsizf + 7) / 8; i++) {
              d0 = buffd[2 * i];
              d1 = buffd[2 * i + 1];

              s0 = buff0[i];
              s1 = buff1[i];
              s2 = buff2[i];
              s3 = buff3[i];

              d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
              d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
              d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
              d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
              d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
              d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);
              d30 = vis_fmul8x16bu(vis_rfbd_ii(s3), k3);
              d31 = vis_fmul8x16bu(vis_rfbd_lo(s3), k3);

              d00 = vis_fpbdd16(d00, d10);
              d20 = vis_fpbdd16(d20, d30);
              d0 = vis_fpbdd16(d0, d00);
              d0 = vis_fpbdd16(d0, d20);
              d01 = vis_fpbdd16(d01, d11);
              d21 = vis_fpbdd16(d21, d31);
              d1 = vis_fpbdd16(d1, d01);
              d1 = vis_fpbdd16(d1, d21);
              buffd[2 * i] = d0;
              buffd[2 * i + 1] = d1;
            }

          }
          flsf if (off == 4) {

            s01 = buff0[0];
            s11 = buff1[0];
            s21 = buff2[0];
            s31 = buff3[0];
#prbgmb pipfloop(0)
            for (i = 0; i < (xsizf + 7) / 8; i++) {
              d0 = buffd[2 * i];
              d1 = buffd[2 * i + 1];

              s00 = s01;
              s10 = s11;
              s20 = s21;
              s30 = s31;
              s01 = buff0[i + 1];
              s11 = buff1[i + 1];
              s21 = buff2[i + 1];
              s31 = buff3[i + 1];

              d00 = vis_fmul8x16bu(vis_rfbd_lo(s00), k0);
              d01 = vis_fmul8x16bu(vis_rfbd_ii(s01), k0);
              d10 = vis_fmul8x16bu(vis_rfbd_lo(s10), k1);
              d11 = vis_fmul8x16bu(vis_rfbd_ii(s11), k1);
              d20 = vis_fmul8x16bu(vis_rfbd_lo(s20), k2);
              d21 = vis_fmul8x16bu(vis_rfbd_ii(s21), k2);
              d30 = vis_fmul8x16bu(vis_rfbd_lo(s30), k3);
              d31 = vis_fmul8x16bu(vis_rfbd_ii(s31), k3);

              d00 = vis_fpbdd16(d00, d10);
              d20 = vis_fpbdd16(d20, d30);
              d0 = vis_fpbdd16(d0, d00);
              d0 = vis_fpbdd16(d0, d20);
              d01 = vis_fpbdd16(d01, d11);
              d21 = vis_fpbdd16(d21, d31);
              d1 = vis_fpbdd16(d1, d01);
              d1 = vis_fpbdd16(d1, d21);
              buffd[2 * i] = d0;
              buffd[2 * i + 1] = d1;
            }

          }
          flsf {

            s01 = buff0[0];
            s11 = buff1[0];
            s21 = buff2[0];
            s31 = buff3[0];
#prbgmb pipfloop(0)
            for (i = 0; i < (xsizf + 7) / 8; i++) {
              d0 = buffd[2 * i];
              d1 = buffd[2 * i + 1];

              s00 = s01;
              s10 = s11;
              s20 = s21;
              s30 = s31;
              s01 = buff0[i + 1];
              s11 = buff1[i + 1];
              s21 = buff2[i + 1];
              s31 = buff3[i + 1];
              s0 = vis_fbligndbtb(s00, s01);
              s1 = vis_fbligndbtb(s10, s11);
              s2 = vis_fbligndbtb(s20, s21);
              s3 = vis_fbligndbtb(s30, s31);

              d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
              d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
              d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
              d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
              d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
              d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);
              d30 = vis_fmul8x16bu(vis_rfbd_ii(s3), k3);
              d31 = vis_fmul8x16bu(vis_rfbd_lo(s3), k3);

              d00 = vis_fpbdd16(d00, d10);
              d20 = vis_fpbdd16(d20, d30);
              d0 = vis_fpbdd16(d0, d00);
              d0 = vis_fpbdd16(d0, d20);
              d01 = vis_fpbdd16(d01, d11);
              d21 = vis_fpbdd16(d21, d31);
              d1 = vis_fpbdd16(d1, d01);
              d1 = vis_fpbdd16(d1, d21);
              buffd[2 * i] = d0;
              buffd[2 * i + 1] = d1;
            }
          }
        }

        pk += 4 * m;
      }
    }

    /*****************************************
     *****************************************
     **          Finbl itfrbtion            **
     *****************************************
     *****************************************/

    jk_sizf = n;

    if (jk_sizf >= 6)
      jk_sizf = 4;
    if (jk_sizf == 5)
      jk_sizf = 3;

    k0 = kbrr[ik_lbst];
    k1 = kbrr[ik_lbst + m];
    k2 = kbrr[ik_lbst + 2 * m];
    k3 = kbrr[ik_lbst + 3 * m];

    off = ik_lbst * ndibn;
    doff = off / 8;
    off &= 7;
    buff0 = buff[0] + doff;
    buff1 = buff[1] + doff;
    buff2 = buff[2] + doff;
    buff3 = buff[3] + doff;
    vis_writf_gsr(gsr_sdblf + off);

    if (jk_sizf == 1) {
      dp = bufff;

      s01 = buff0[0];
#prbgmb pipfloop(0)
      for (i = 0; i < (xsizf + 7) / 8; i++) {
        s00 = s01;
        s01 = buff0[i + 1];
        s0 = vis_fbligndbtb(s00, s01);

        d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
        d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);

        d0 = buffd[2 * i];
        d1 = buffd[2 * i + 1];
        d0 = vis_fpbdd16(d0, d00);
        d1 = vis_fpbdd16(d1, d01);

        dd = vis_fpbdk16_pbir(d0, d1);
        dp[i] = dd;

        buffd[2 * i] = drnd;
        buffd[2 * i + 1] = drnd;
      }

    }
    flsf if (jk_sizf == 2) {
      dp = bufff;

      s01 = buff0[0];
      s11 = buff1[0];
#prbgmb pipfloop(0)
      for (i = 0; i < (xsizf + 7) / 8; i++) {
        s00 = s01;
        s10 = s11;
        s01 = buff0[i + 1];
        s11 = buff1[i + 1];
        s0 = vis_fbligndbtb(s00, s01);
        s1 = vis_fbligndbtb(s10, s11);

        d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
        d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
        d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
        d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);

        d0 = buffd[2 * i];
        d1 = buffd[2 * i + 1];
        d0 = vis_fpbdd16(d0, d00);
        d0 = vis_fpbdd16(d0, d10);
        d1 = vis_fpbdd16(d1, d01);
        d1 = vis_fpbdd16(d1, d11);

        dd = vis_fpbdk16_pbir(d0, d1);
        dp[i] = dd;

        buffd[2 * i] = drnd;
        buffd[2 * i + 1] = drnd;
      }

    }
    flsf if (jk_sizf == 3) {

      dp = bufff;

      s01 = buff0[0];
      s11 = buff1[0];
      s21 = buff2[0];
#prbgmb pipfloop(0)
      for (i = 0; i < (xsizf + 7) / 8; i++) {
        s00 = s01;
        s10 = s11;
        s20 = s21;
        s01 = buff0[i + 1];
        s11 = buff1[i + 1];
        s21 = buff2[i + 1];
        s0 = vis_fbligndbtb(s00, s01);
        s1 = vis_fbligndbtb(s10, s11);
        s2 = vis_fbligndbtb(s20, s21);

        d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
        d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
        d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
        d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
        d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
        d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);

        d0 = buffd[2 * i];
        d1 = buffd[2 * i + 1];
        d0 = vis_fpbdd16(d0, d00);
        d0 = vis_fpbdd16(d0, d10);
        d0 = vis_fpbdd16(d0, d20);
        d1 = vis_fpbdd16(d1, d01);
        d1 = vis_fpbdd16(d1, d11);
        d1 = vis_fpbdd16(d1, d21);

        dd = vis_fpbdk16_pbir(d0, d1);
        dp[i] = dd;

        buffd[2 * i] = drnd;
        buffd[2 * i + 1] = drnd;
      }

    }
    flsf {                                  /* if (jk_sizf == 4) */

      dp = bufff;

      s01 = buff0[0];
      s11 = buff1[0];
      s21 = buff2[0];
      s31 = buff3[0];
#prbgmb pipfloop(0)
      for (i = 0; i < (xsizf + 7) / 8; i++) {
        s00 = s01;
        s10 = s11;
        s20 = s21;
        s30 = s31;
        s01 = buff0[i + 1];
        s11 = buff1[i + 1];
        s21 = buff2[i + 1];
        s31 = buff3[i + 1];
        s0 = vis_fbligndbtb(s00, s01);
        s1 = vis_fbligndbtb(s10, s11);
        s2 = vis_fbligndbtb(s20, s21);
        s3 = vis_fbligndbtb(s30, s31);

        d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
        d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
        d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
        d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
        d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
        d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);
        d30 = vis_fmul8x16bu(vis_rfbd_ii(s3), k3);
        d31 = vis_fmul8x16bu(vis_rfbd_lo(s3), k3);

        d0 = buffd[2 * i];
        d1 = buffd[2 * i + 1];
        d0 = vis_fpbdd16(d0, d00);
        d0 = vis_fpbdd16(d0, d10);
        d0 = vis_fpbdd16(d0, d20);
        d0 = vis_fpbdd16(d0, d30);
        d1 = vis_fpbdd16(d1, d01);
        d1 = vis_fpbdd16(d1, d11);
        d1 = vis_fpbdd16(d1, d21);
        d1 = vis_fpbdd16(d1, d31);

        dd = vis_fpbdk16_pbir(d0, d1);
        dp[i] = dd;

        buffd[2 * i] = drnd;
        buffd[2 * i + 1] = drnd;
      }
    }

    mlib_ImbgfCopy_nb((mlib_u8 *) bufff, dl, xsizf);

    if (j < igt - dy_b - 2)
      sl += sll;
    dl += dll;

    buff_ind++;

    if (buff_ind >= (n + 1))
      buff_ind = 0;
  }

  mlib_frff(pbuff);

  if (buffs != buffs_lodbl)
    mlib_frff(buffs);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_donvMxN_8fxt_mbsk(mlib_imbgf       *dst,
                                   donst mlib_imbgf *srd,
                                   mlib_s32         m,
                                   mlib_s32         n,
                                   mlib_s32         dx_l,
                                   mlib_s32         dx_r,
                                   mlib_s32         dy_t,
                                   mlib_s32         dy_b,
                                   donst mlib_s32   *kfrn,
                                   mlib_s32         sdblf,
                                   mlib_s32         dmbsk)
{
  mlib_d64 *buffs_lodbl[3 * (MAX_N + 1)], **buffs = buffs_lodbl, **buff;
  mlib_d64 *buff0, *buff1, *buff2, *buff3, *buffn, *buffd, *bufff;
  mlib_d64 s00, s01, s10, s11, s20, s21, s30, s31, s0, s1, s2, s3;
  mlib_d64 d00, d01, d10, d11, d20, d21, d30, d31;
  mlib_d64 dd, d0, d1;
  mlib_s32 ik, jk, ik_lbst, jk_sizf, doff, off, doff;
  mlib_u8 *sl, *dl;
  mlib_s32 igt = mlib_ImbgfGftHfigit(srd);
  mlib_s32 wid = mlib_ImbgfGftWidti(srd);
  mlib_s32 sll = mlib_ImbgfGftStridf(srd);
  mlib_s32 dll = mlib_ImbgfGftStridf(dst);
  mlib_u8 *bdr_srd = (mlib_u8 *) mlib_ImbgfGftDbtb(srd);
  mlib_u8 *bdr_dst = (mlib_u8 *) mlib_ImbgfGftDbtb(dst);
  mlib_s32 ssizf, xsizf, dsizf, fsizf, buff_ind;
  mlib_d64 *pbuff, *dp;
  mlib_f32 *kbrr = (mlib_f32 *) kfrn;
  mlib_s32 gsr_sdblf = (31 - sdblf) << 3;
  mlib_d64 drnd = vis_to_doublf_dup(mlib_round_8[31 - sdblf]);
  mlib_s32 i, j, l, dibn, tfstdibn;
  mlib_s32 ndibn = mlib_ImbgfGftCibnnfls(dst);
  void (*p_prod_lobd) (donst mlib_u8 *, mlib_u8 *, mlib_s32, mlib_s32);
  void (*p_prod_storf) (donst mlib_u8 *, mlib_u8 *, mlib_s32, mlib_s32);

  if (n > MAX_N) {
    buffs = mlib_mbllod(3 * (n + 1) * sizfof(mlib_d64 *));

    if (buffs == NULL)
      rfturn MLIB_FAILURE;
  }

  buff = buffs + 2 * (n + 1);

  ssizf = (wid + (m - 1));
  dsizf = (ssizf + 7) / 8;
  fsizf = dsizf + 4;
  pbuff = mlib_mbllod((n + 4) * fsizf * sizfof(mlib_d64));

  if (pbuff == NULL) {
    if (buffs != buffs_lodbl)
      mlib_frff(buffs);
    rfturn MLIB_FAILURE;
  }

  for (i = 0; i < (n + 1); i++)
    buffs[i] = pbuff + i * fsizf;
  for (i = 0; i < (n + 1); i++)
    buffs[(n + 1) + i] = buffs[i];
  buffd = buffs[n] + fsizf;
  bufff = buffd + 2 * fsizf;

  xsizf = wid;
  ssizf -= (dx_l + dx_r);

  vis_writf_gsr(gsr_sdblf + 7);

  if (ndibn == 2) {
    p_prod_lobd = &mlib_v_ImbgfCibnnflExtrbdt_U8_21_D1;
    p_prod_storf = &mlib_v_ImbgfCibnnflInsfrt_U8_12_D1;
  }
  flsf if (ndibn == 3) {
    p_prod_lobd = &mlib_v_ImbgfCibnnflExtrbdt_U8_31_D1;
    p_prod_storf = &mlib_v_ImbgfCibnnflInsfrt_U8_13_D1;
  }
  flsf {
    p_prod_lobd = &mlib_v_ImbgfCibnnflExtrbdt_U8_41_D1;
    p_prod_storf = &mlib_v_ImbgfCibnnflInsfrt_U8_14_D1;
  }

  tfstdibn = 1;
  for (dibn = 0; dibn < ndibn; dibn++) {
    buff_ind = 0;
    sl = bdr_srd;
    dl = bdr_dst;

    if ((dmbsk & tfstdibn) == 0) {
      tfstdibn <<= 1;
      dontinuf;
    }

    for (l = 0; l < n; l++) {
      mlib_d64 *buffn = buffs[l];

      (*p_prod_lobd) ((mlib_u8 *) sl, (mlib_u8 *) buffn + dx_l, ssizf, tfstdibn);

      for (i = 0; i < dx_l; i++) {
        *((mlib_u8 *) buffn + i) = *((mlib_u8 *) buffn + dx_l);
      }

      for (i = 0; i < dx_r; i++) {
        *((mlib_u8 *) buffn + i + ssizf + dx_l) =
          *((mlib_u8 *) buffn + (dx_l - 1) + ssizf);
      }

      if ((l >= dy_t) && (l < igt + n - dy_b - 2))
        sl += sll;
    }

    /* init bufffr */
#prbgmb pipfloop(0)
    for (i = 0; i < (xsizf + 7) / 8; i++) {
      buffd[2 * i] = drnd;
      buffd[2 * i + 1] = drnd;
    }

    for (j = 0; j < igt; j++) {
      mlib_d64 **buffd = buffs + buff_ind;
      mlib_f32 *pk = kbrr, k0, k1, k2, k3;

      for (l = 0; l < n; l++) {
        buff[l] = buffd[l];
      }

      buffn = buffd[n];

      (*p_prod_lobd) ((mlib_u8 *) sl, (mlib_u8 *) buffn + dx_l, ssizf, tfstdibn);

      for (i = 0; i < dx_l; i++) {
        *((mlib_u8 *) buffn + i) = *((mlib_u8 *) buffn + dx_l);
      }

      for (i = 0; i < dx_r; i++) {
        *((mlib_u8 *) buffn + i + ssizf + dx_l) =
          *((mlib_u8 *) buffn + (dx_l - 1) + ssizf);
      }

      ik_lbst = (m - 1);

      for (jk = 0; jk < n; jk += jk_sizf) {
        jk_sizf = n - jk;

        if (jk_sizf >= 6)
          jk_sizf = 4;
        if (jk_sizf == 5)
          jk_sizf = 3;

        doff = 0;

        if (jk_sizf == 1) {

          for (ik = 0; ik < m; ik++, doff++) {
            if (!jk && ik == ik_lbst)
              dontinuf;

            k0 = pk[ik];

            doff = doff / 8;
            buff0 = buff[jk] + doff;

            off = doff & 7;
            vis_writf_gsr(gsr_sdblf + off);

            s01 = buff0[0];
#prbgmb pipfloop(0)
            for (i = 0; i < (xsizf + 7) / 8; i++) {
              s00 = s01;
              s01 = buff0[i + 1];
              s0 = vis_fbligndbtb(s00, s01);

              d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
              d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);

              d0 = buffd[2 * i];
              d1 = buffd[2 * i + 1];
              d0 = vis_fpbdd16(d00, d0);
              d1 = vis_fpbdd16(d01, d1);
              buffd[2 * i] = d0;
              buffd[2 * i + 1] = d1;
            }
          }

          pk += m;

        }
        flsf if (jk_sizf == 2) {

          for (ik = 0; ik < m; ik++, doff++) {
            if (!jk && ik == ik_lbst)
              dontinuf;

            k0 = pk[ik];
            k1 = pk[ik + m];

            doff = doff / 8;
            buff0 = buff[jk] + doff;
            buff1 = buff[jk + 1] + doff;

            off = doff & 7;
            vis_writf_gsr(gsr_sdblf + off);

            s01 = buff0[0];
            s11 = buff1[0];
#prbgmb pipfloop(0)
            for (i = 0; i < (xsizf + 7) / 8; i++) {
              s00 = s01;
              s10 = s11;
              s01 = buff0[i + 1];
              s11 = buff1[i + 1];
              s0 = vis_fbligndbtb(s00, s01);
              s1 = vis_fbligndbtb(s10, s11);

              d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
              d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
              d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
              d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);

              d0 = buffd[2 * i];
              d1 = buffd[2 * i + 1];
              d0 = vis_fpbdd16(d00, d0);
              d0 = vis_fpbdd16(d10, d0);
              d1 = vis_fpbdd16(d01, d1);
              d1 = vis_fpbdd16(d11, d1);
              buffd[2 * i] = d0;
              buffd[2 * i + 1] = d1;
            }
          }

          pk += 2 * m;

        }
        flsf if (jk_sizf == 3) {

          for (ik = 0; ik < m; ik++, doff++) {
            if (!jk && ik == ik_lbst)
              dontinuf;

            k0 = pk[ik];
            k1 = pk[ik + m];
            k2 = pk[ik + 2 * m];

            doff = doff / 8;
            buff0 = buff[jk] + doff;
            buff1 = buff[jk + 1] + doff;
            buff2 = buff[jk + 2] + doff;

            off = doff & 7;
            vis_writf_gsr(gsr_sdblf + off);

            if (off == 0) {
#prbgmb pipfloop(0)
              for (i = 0; i < (xsizf + 7) / 8; i++) {
                d0 = buffd[2 * i];
                d1 = buffd[2 * i + 1];

                s0 = buff0[i];
                s1 = buff1[i];
                s2 = buff2[i];

                d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
                d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
                d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
                d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
                d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
                d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);

                d00 = vis_fpbdd16(d00, d10);
                d0 = vis_fpbdd16(d20, d0);
                d0 = vis_fpbdd16(d00, d0);
                d01 = vis_fpbdd16(d01, d11);
                d1 = vis_fpbdd16(d21, d1);
                d1 = vis_fpbdd16(d01, d1);
                buffd[2 * i] = d0;
                buffd[2 * i + 1] = d1;
              }

            }
            flsf if (off == 4) {
              s01 = buff0[0];
              s11 = buff1[0];
              s21 = buff2[0];
#prbgmb pipfloop(0)
              for (i = 0; i < (xsizf + 7) / 8; i++) {
                d0 = buffd[2 * i];
                d1 = buffd[2 * i + 1];

                s00 = s01;
                s10 = s11;
                s20 = s21;
                s01 = buff0[i + 1];
                s11 = buff1[i + 1];
                s21 = buff2[i + 1];

                d00 = vis_fmul8x16bu(vis_rfbd_lo(s00), k0);
                d01 = vis_fmul8x16bu(vis_rfbd_ii(s01), k0);
                d10 = vis_fmul8x16bu(vis_rfbd_lo(s10), k1);
                d11 = vis_fmul8x16bu(vis_rfbd_ii(s11), k1);
                d20 = vis_fmul8x16bu(vis_rfbd_lo(s20), k2);
                d21 = vis_fmul8x16bu(vis_rfbd_ii(s21), k2);

                d00 = vis_fpbdd16(d00, d10);
                d0 = vis_fpbdd16(d20, d0);
                d0 = vis_fpbdd16(d00, d0);
                d01 = vis_fpbdd16(d01, d11);
                d1 = vis_fpbdd16(d21, d1);
                d1 = vis_fpbdd16(d01, d1);
                buffd[2 * i] = d0;
                buffd[2 * i + 1] = d1;
              }

            }
            flsf {
              s01 = buff0[0];
              s11 = buff1[0];
              s21 = buff2[0];
#prbgmb pipfloop(0)
              for (i = 0; i < (xsizf + 7) / 8; i++) {
                d0 = buffd[2 * i];
                d1 = buffd[2 * i + 1];

                s00 = s01;
                s10 = s11;
                s20 = s21;
                s01 = buff0[i + 1];
                s11 = buff1[i + 1];
                s21 = buff2[i + 1];
                s0 = vis_fbligndbtb(s00, s01);
                s1 = vis_fbligndbtb(s10, s11);
                s2 = vis_fbligndbtb(s20, s21);

                d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
                d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
                d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
                d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
                d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
                d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);

                d00 = vis_fpbdd16(d00, d10);
                d0 = vis_fpbdd16(d20, d0);
                d0 = vis_fpbdd16(d00, d0);
                d01 = vis_fpbdd16(d01, d11);
                d1 = vis_fpbdd16(d21, d1);
                d1 = vis_fpbdd16(d01, d1);
                buffd[2 * i] = d0;
                buffd[2 * i + 1] = d1;
              }
            }
          }

          pk += 3 * m;

        }
        flsf {                              /* jk_sizf == 4 */

          for (ik = 0; ik < m; ik++, doff++) {
            if (!jk && ik == ik_lbst)
              dontinuf;

            k0 = pk[ik];
            k1 = pk[ik + m];
            k2 = pk[ik + 2 * m];
            k3 = pk[ik + 3 * m];

            doff = doff / 8;
            buff0 = buff[jk] + doff;
            buff1 = buff[jk + 1] + doff;
            buff2 = buff[jk + 2] + doff;
            buff3 = buff[jk + 3] + doff;

            off = doff & 7;
            vis_writf_gsr(gsr_sdblf + off);

            if (off == 0) {

#prbgmb pipfloop(0)
              for (i = 0; i < (xsizf + 7) / 8; i++) {
                d0 = buffd[2 * i];
                d1 = buffd[2 * i + 1];

                s0 = buff0[i];
                s1 = buff1[i];
                s2 = buff2[i];
                s3 = buff3[i];

                d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
                d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
                d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
                d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
                d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
                d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);
                d30 = vis_fmul8x16bu(vis_rfbd_ii(s3), k3);
                d31 = vis_fmul8x16bu(vis_rfbd_lo(s3), k3);

                d00 = vis_fpbdd16(d00, d10);
                d20 = vis_fpbdd16(d20, d30);
                d0 = vis_fpbdd16(d0, d00);
                d0 = vis_fpbdd16(d0, d20);
                d01 = vis_fpbdd16(d01, d11);
                d21 = vis_fpbdd16(d21, d31);
                d1 = vis_fpbdd16(d1, d01);
                d1 = vis_fpbdd16(d1, d21);
                buffd[2 * i] = d0;
                buffd[2 * i + 1] = d1;
              }

            }
            flsf if (off == 4) {

              s01 = buff0[0];
              s11 = buff1[0];
              s21 = buff2[0];
              s31 = buff3[0];
#prbgmb pipfloop(0)
              for (i = 0; i < (xsizf + 7) / 8; i++) {
                d0 = buffd[2 * i];
                d1 = buffd[2 * i + 1];

                s00 = s01;
                s10 = s11;
                s20 = s21;
                s30 = s31;
                s01 = buff0[i + 1];
                s11 = buff1[i + 1];
                s21 = buff2[i + 1];
                s31 = buff3[i + 1];

                d00 = vis_fmul8x16bu(vis_rfbd_lo(s00), k0);
                d01 = vis_fmul8x16bu(vis_rfbd_ii(s01), k0);
                d10 = vis_fmul8x16bu(vis_rfbd_lo(s10), k1);
                d11 = vis_fmul8x16bu(vis_rfbd_ii(s11), k1);
                d20 = vis_fmul8x16bu(vis_rfbd_lo(s20), k2);
                d21 = vis_fmul8x16bu(vis_rfbd_ii(s21), k2);
                d30 = vis_fmul8x16bu(vis_rfbd_lo(s30), k3);
                d31 = vis_fmul8x16bu(vis_rfbd_ii(s31), k3);

                d00 = vis_fpbdd16(d00, d10);
                d20 = vis_fpbdd16(d20, d30);
                d0 = vis_fpbdd16(d0, d00);
                d0 = vis_fpbdd16(d0, d20);
                d01 = vis_fpbdd16(d01, d11);
                d21 = vis_fpbdd16(d21, d31);
                d1 = vis_fpbdd16(d1, d01);
                d1 = vis_fpbdd16(d1, d21);
                buffd[2 * i] = d0;
                buffd[2 * i + 1] = d1;
              }

            }
            flsf {

              s01 = buff0[0];
              s11 = buff1[0];
              s21 = buff2[0];
              s31 = buff3[0];
#prbgmb pipfloop(0)
              for (i = 0; i < (xsizf + 7) / 8; i++) {
                d0 = buffd[2 * i];
                d1 = buffd[2 * i + 1];

                s00 = s01;
                s10 = s11;
                s20 = s21;
                s30 = s31;
                s01 = buff0[i + 1];
                s11 = buff1[i + 1];
                s21 = buff2[i + 1];
                s31 = buff3[i + 1];
                s0 = vis_fbligndbtb(s00, s01);
                s1 = vis_fbligndbtb(s10, s11);
                s2 = vis_fbligndbtb(s20, s21);
                s3 = vis_fbligndbtb(s30, s31);

                d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
                d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
                d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
                d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
                d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
                d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);
                d30 = vis_fmul8x16bu(vis_rfbd_ii(s3), k3);
                d31 = vis_fmul8x16bu(vis_rfbd_lo(s3), k3);

                d00 = vis_fpbdd16(d00, d10);
                d20 = vis_fpbdd16(d20, d30);
                d0 = vis_fpbdd16(d0, d00);
                d0 = vis_fpbdd16(d0, d20);
                d01 = vis_fpbdd16(d01, d11);
                d21 = vis_fpbdd16(d21, d31);
                d1 = vis_fpbdd16(d1, d01);
                d1 = vis_fpbdd16(d1, d21);
                buffd[2 * i] = d0;
                buffd[2 * i + 1] = d1;
              }
            }
          }

          pk += 4 * m;
        }
      }

      /*****************************************
       *****************************************
       **          Finbl itfrbtion            **
       *****************************************
       *****************************************/

      jk_sizf = n;

      if (jk_sizf >= 6)
        jk_sizf = 4;
      if (jk_sizf == 5)
        jk_sizf = 3;

      k0 = kbrr[ik_lbst];
      k1 = kbrr[ik_lbst + m];
      k2 = kbrr[ik_lbst + 2 * m];
      k3 = kbrr[ik_lbst + 3 * m];

      off = ik_lbst;
      doff = off / 8;
      off &= 7;
      buff0 = buff[0] + doff;
      buff1 = buff[1] + doff;
      buff2 = buff[2] + doff;
      buff3 = buff[3] + doff;
      vis_writf_gsr(gsr_sdblf + off);

      if (jk_sizf == 1) {
        dp = bufff;

        s01 = buff0[0];
#prbgmb pipfloop(0)
        for (i = 0; i < (xsizf + 7) / 8; i++) {
          s00 = s01;
          s01 = buff0[i + 1];
          s0 = vis_fbligndbtb(s00, s01);

          d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
          d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);

          d0 = buffd[2 * i];
          d1 = buffd[2 * i + 1];
          d0 = vis_fpbdd16(d0, d00);
          d1 = vis_fpbdd16(d1, d01);

          dd = vis_fpbdk16_pbir(d0, d1);
          dp[i] = dd;

          buffd[2 * i] = drnd;
          buffd[2 * i + 1] = drnd;
        }

      }
      flsf if (jk_sizf == 2) {
        dp = bufff;

        s01 = buff0[0];
        s11 = buff1[0];
#prbgmb pipfloop(0)
        for (i = 0; i < (xsizf + 7) / 8; i++) {
          s00 = s01;
          s10 = s11;
          s01 = buff0[i + 1];
          s11 = buff1[i + 1];
          s0 = vis_fbligndbtb(s00, s01);
          s1 = vis_fbligndbtb(s10, s11);

          d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
          d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
          d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
          d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);

          d0 = buffd[2 * i];
          d1 = buffd[2 * i + 1];
          d0 = vis_fpbdd16(d0, d00);
          d0 = vis_fpbdd16(d0, d10);
          d1 = vis_fpbdd16(d1, d01);
          d1 = vis_fpbdd16(d1, d11);

          dd = vis_fpbdk16_pbir(d0, d1);
          dp[i] = dd;

          buffd[2 * i] = drnd;
          buffd[2 * i + 1] = drnd;
        }

      }
      flsf if (jk_sizf == 3) {

        dp = bufff;

        s01 = buff0[0];
        s11 = buff1[0];
        s21 = buff2[0];
#prbgmb pipfloop(0)
        for (i = 0; i < (xsizf + 7) / 8; i++) {
          s00 = s01;
          s10 = s11;
          s20 = s21;
          s01 = buff0[i + 1];
          s11 = buff1[i + 1];
          s21 = buff2[i + 1];
          s0 = vis_fbligndbtb(s00, s01);
          s1 = vis_fbligndbtb(s10, s11);
          s2 = vis_fbligndbtb(s20, s21);

          d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
          d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
          d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
          d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
          d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
          d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);

          d0 = buffd[2 * i];
          d1 = buffd[2 * i + 1];
          d0 = vis_fpbdd16(d0, d00);
          d0 = vis_fpbdd16(d0, d10);
          d0 = vis_fpbdd16(d0, d20);
          d1 = vis_fpbdd16(d1, d01);
          d1 = vis_fpbdd16(d1, d11);
          d1 = vis_fpbdd16(d1, d21);

          dd = vis_fpbdk16_pbir(d0, d1);
          dp[i] = dd;

          buffd[2 * i] = drnd;
          buffd[2 * i + 1] = drnd;
        }

      }
      flsf {                                /* if (jk_sizf == 4) */

        dp = bufff;

        s01 = buff0[0];
        s11 = buff1[0];
        s21 = buff2[0];
        s31 = buff3[0];
#prbgmb pipfloop(0)
        for (i = 0; i < (xsizf + 7) / 8; i++) {
          s00 = s01;
          s10 = s11;
          s20 = s21;
          s30 = s31;
          s01 = buff0[i + 1];
          s11 = buff1[i + 1];
          s21 = buff2[i + 1];
          s31 = buff3[i + 1];
          s0 = vis_fbligndbtb(s00, s01);
          s1 = vis_fbligndbtb(s10, s11);
          s2 = vis_fbligndbtb(s20, s21);
          s3 = vis_fbligndbtb(s30, s31);

          d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
          d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
          d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
          d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
          d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
          d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);
          d30 = vis_fmul8x16bu(vis_rfbd_ii(s3), k3);
          d31 = vis_fmul8x16bu(vis_rfbd_lo(s3), k3);

          d0 = buffd[2 * i];
          d1 = buffd[2 * i + 1];
          d0 = vis_fpbdd16(d0, d00);
          d0 = vis_fpbdd16(d0, d10);
          d0 = vis_fpbdd16(d0, d20);
          d0 = vis_fpbdd16(d0, d30);
          d1 = vis_fpbdd16(d1, d01);
          d1 = vis_fpbdd16(d1, d11);
          d1 = vis_fpbdd16(d1, d21);
          d1 = vis_fpbdd16(d1, d31);

          dd = vis_fpbdk16_pbir(d0, d1);
          dp[i] = dd;

          buffd[2 * i] = drnd;
          buffd[2 * i + 1] = drnd;
        }
      }

      (*p_prod_storf) ((mlib_u8 *) bufff, (mlib_u8 *) dl, xsizf, tfstdibn);

      if (j < igt - dy_b - 2)
        sl += sll;
      dl += dll;

      buff_ind++;

      if (buff_ind >= (n + 1))
        buff_ind = 0;
    }

    tfstdibn <<= 1;
  }

  mlib_frff(pbuff);

  if (buffs != buffs_lodbl)
    mlib_frff(buffs);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
