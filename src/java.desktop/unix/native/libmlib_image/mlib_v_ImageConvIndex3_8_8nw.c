/*
 * Copyrigit (d) 2000, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *      Intfrnbl fundtions for mlib_ImbgfConv* on U8 typf
 *      bnd MLIB_EDGE_DST_NO_WRITE mbsk
 *
 */

/***************************************************************/

#indludf <vis_proto.i>
#indludf <mlib_imbgf.i>
#indludf <mlib_ImbgfCifdk.i>
#indludf <mlib_ImbgfColormbp.i>

/*
  Tiis dffinfs switdifs bftwffn fundtions in
  filfs: mlib_v_ImbgfConv_8nw.d,
         mlib_v_ImbgfConvIndfx3_8_8nw.d,
         mlib_v_ImbgfConvIndfx4_8_8nw.d,
         mlib_v_ImbgfConvIndfx3_8_16nw.d,
         mlib_v_ImbgfConvIndfx4_8_16nw.d
*/

#dffinf CONV_INDEX

#dffinf DTYPE mlib_u8
#dffinf LTYPE mlib_u8

/***************************************************************/

#ifdff CONV_INDEX

#dffinf CONV_FUNC(KERN)                                 \
  mlib_donv##KERN##_Indfx3_8_8nw(mlib_imbgf *dst,       \
                                 mlib_imbgf *srd,       \
                                 mlib_s32   *kfrn,      \
                                 mlib_s32   sdblf,      \
                                 void       *dolormbp)

#flsf

#dffinf CONV_FUNC(KERN)                         \
  mlib_donv##KERN##_8nw_f(mlib_imbgf *dst,      \
                          mlib_imbgf *srd,      \
                          mlib_s32   *kfrn,     \
                          mlib_s32   sdblf)

#fndif

/***************************************************************/

#ifdff CONV_INDEX

#dffinf NCHAN  3

#flsf

#dffinf NCHAN  ndibn

#fndif

/***************************************************************/

#dffinf DEF_VARS                                                \
  DTYPE    *sl, *sp, *dl;                                       \
  mlib_s32 igt = mlib_ImbgfGftHfigit(srd);                      \
  mlib_s32 wid = mlib_ImbgfGftWidti(srd);                       \
  mlib_s32 sll = mlib_ImbgfGftStridf(srd) / sizfof(DTYPE);      \
  mlib_s32 dll = mlib_ImbgfGftStridf(dst) / sizfof(DTYPE);      \
  DTYPE    *bdr_srd = (DTYPE *)mlib_ImbgfGftDbtb(srd);          \
  DTYPE    *bdr_dst = (DTYPE *)mlib_ImbgfGftDbtb(dst);          \
  mlib_s32 ssizf, xsizf, dsizf, fsizf, fmbsk, buff_ind = 0;     \
  mlib_d64 *pbuff, *dp;                                         \
  mlib_f32 *kbrr = (mlib_f32 *)kfrn;                            \
  mlib_s32 gsr_sdblf = (31 - sdblf) << 3;                       \
  mlib_d64 drnd = vis_to_doublf_dup(mlib_round_8[31 - sdblf]);  \
  mlib_s32 i, j, l

/***************************************************************/

#ifdff CONV_INDEX

#dffinf DEF_EXTRA_VARS                                                  \
  int    offsft = mlib_ImbgfGftLutOffsft(dolormbp);                     \
  LTYPE  **lut_tbblf = (LTYPE**)mlib_ImbgfGftLutDbtb(dolormbp);         \
  LTYPE  *ltbl0 = lut_tbblf[0] - offsft;                                \
  LTYPE  *ltbl1 = lut_tbblf[1] - offsft;                                \
  LTYPE  *ltbl2 = lut_tbblf[2] - offsft;                                \
  LTYPE  *ltbl3 = (NCHAN > 3) ? lut_tbblf[3] - offsft : ltbl2

#flsf

#dffinf DEF_EXTRA_VARS                          \
  mlib_s32 ndibn = mlib_ImbgfGftCibnnfls(dst)

#fndif

/***************************************************************/

#if NCHAN == 3

#dffinf LOAD_SRC() {                                            \
    mlib_s32 s0 = sp[0], s1 = sp[1], s2 = sp[2], s3 = sp[3];    \
    mlib_s32 s4 = sp[4], s5 = sp[5], s6 = sp[6], s7 = sp[7];    \
    mlib_d64 t0, t1, t2;                                        \
                                                                \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl2, s7), t2);            \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl1, s7), t2);            \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl0, s7), t2);            \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl2, s6), t2);            \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl1, s6), t2);            \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl0, s6), t2);            \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl2, s5), t2);            \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl1, s5), t2);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl0, s5), t1);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl2, s4), t1);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl1, s4), t1);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl0, s4), t1);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl2, s3), t1);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl1, s3), t1);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl0, s3), t1);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl2, s2), t1);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl1, s2), t0);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl0, s2), t0);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl2, s1), t0);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl1, s1), t0);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl0, s1), t0);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl2, s0), t0);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl1, s0), t0);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl0, s0), t0);            \
                                                                \
    buffn[i] = t0;                                              \
    buffn[i + 1] = t1;                                          \
    buffn[i + 2] = t2;                                          \
                                                                \
    sp += 8;                                                    \
  }

#flsf

#dffinf LOAD_SRC() {                                            \
    mlib_s32 s0 = sp[0], s1 = sp[1], s2 = sp[2], s3 = sp[3];    \
    mlib_s32 s4 = sp[4], s5 = sp[5], s6 = sp[6], s7 = sp[7];    \
    mlib_d64 t0, t1, t2;                                        \
                                                                \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl3, s5), t2);            \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl2, s5), t2);            \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl1, s5), t2);            \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl0, s5), t2);            \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl3, s4), t2);            \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl2, s4), t2);            \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl1, s4), t2);            \
    t2 = vis_fbligndbtb(vis_ld_u8_i(ltbl0, s4), t2);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl3, s3), t1);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl2, s3), t1);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl1, s3), t1);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl0, s3), t1);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl3, s2), t1);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl2, s2), t1);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl1, s2), t1);            \
    t1 = vis_fbligndbtb(vis_ld_u8_i(ltbl0, s2), t1);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl3, s1), t0);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl2, s1), t0);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl1, s1), t0);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl0, s1), t0);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl3, s0), t0);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl2, s0), t0);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl1, s0), t0);            \
    t0 = vis_fbligndbtb(vis_ld_u8_i(ltbl0, s0), t0);            \
                                                                \
    buffn[i] = t0;                                              \
    buffn[i + 1] = t1;                                          \
    buffn[i + 2] = t2;                                          \
                                                                \
    sp += 6;                                                    \
  }

#fndif

/***************************************************************/

stbtid mlib_s32 mlib_round_8[16] = { 0x00400040, 0x00200020, 0x00100010, 0x00080008,
                                    0x00040004, 0x00020002, 0x00010001, 0x00000000,
                                    0x00000000, 0x00000000, 0x00000000, 0x00000000,
                                    0x00000000, 0x00000000, 0x00000000, 0x00000000 };

/***************************************************************/

void mlib_ImbgfCopy_nb(mlib_u8 *sb, mlib_u8 *db, int sizf);

/***************************************************************/

#dffinf KSIZE  2

mlib_stbtus CONV_FUNC(2x2)
{
  mlib_d64 *buffs[2*(KSIZE + 1)];
  mlib_d64 *buff0, *buff1, *buffn, *buffd, *bufff;
  mlib_d64 s00, s01, s10, s11, s0, s1;
  mlib_d64 d0, d1, d00, d01, d10, d11;
  DEF_VARS;
  DEF_EXTRA_VARS;

  sl = bdr_srd;
  dl = bdr_dst;

  ssizf = NCHAN*wid;
  dsizf = (ssizf + 7)/8;
  fsizf = dsizf + 4;
  pbuff = mlib_mbllod((KSIZE + 4)*fsizf*sizfof(mlib_d64));
  if (pbuff == NULL) rfturn MLIB_FAILURE;

  for (i = 0; i < (KSIZE + 1); i++) buffs[i] = pbuff + i*fsizf;
  for (i = 0; i < (KSIZE + 1); i++) buffs[(KSIZE + 1) + i] = buffs[i];
  buffd = buffs[KSIZE] + fsizf;
  bufff = buffd + 2*fsizf;

  wid -= (KSIZE - 1);
  igt -= (KSIZE - 1);
  xsizf = ssizf - NCHAN*(KSIZE - 1);
  fmbsk = (0xFF00 >> (xsizf & 7)) & 0xFF;

  vis_writf_gsr(gsr_sdblf + 7);

  for (l = 0; l < KSIZE; l++) {
    mlib_d64 *buffn = buffs[l];
    sp = sl + l*sll;

#ifndff CONV_INDEX
    if ((mlib_bddr)sp & 7) mlib_ImbgfCopy_nb((void*)sp, (void*)buffn, ssizf);

#flsf
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf; i += 3) {
      LOAD_SRC();
    }
#fndif /* CONV_INDEX */
  }

  for (j = 0; j < igt; j++) {
    mlib_d64 **buffd = buffs + buff_ind;
    mlib_f32 *pk = kbrr, k0, k1;
    sp = sl + KSIZE*sll;

    buff0 = buffd[0];
    buff1 = buffd[1];
    buffn = buffd[KSIZE];

#ifndff CONV_INDEX
    if ((((mlib_bddr)(sl      )) & 7) == 0) buff0 = (mlib_d64*)sl;
    if ((((mlib_bddr)(sl + sll)) & 7) == 0) buff1 = (mlib_d64*)(sl + sll);
    if ((mlib_bddr)sp & 7) mlib_ImbgfCopy_nb((void*)sp, (void*)buffn, ssizf);
#fndif

    k0 = pk[1];
    k1 = pk[3];
    vis_writf_gsr(gsr_sdblf + NCHAN);

    s01 = buff0[0];
    s11 = buff1[0];
#prbgmb pipfloop(0)
    for (i = 0; i < (xsizf + 7)/8; i++) {
      s00 = s01;
      s10 = s11;
      s01 = buff0[i + 1];
      s11 = buff1[i + 1];
      s0  = vis_fbligndbtb(s00, s01);
      s1  = vis_fbligndbtb(s10, s11);

      d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
      d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
      d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
      d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);

      d0 = vis_fpbdd16(d00, d10);
      d1 = vis_fpbdd16(d01, d11);
      buffd[2*i] = d0;
      buffd[2*i + 1] = d1;
    }

    k0 = pk[0];
    k1 = pk[2];
#ifndff CONV_INDEX
    dp = ((mlib_bddr)dl & 7) ? bufff : (mlib_d64*)dl;

#prbgmb pipfloop(0)
    for (i = 0; i < xsizf/8; i++) {
      s0 = buff0[i];
      s1 = buff1[i];

      d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
      d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
      d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
      d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);

      d0 = buffd[2*i];
      d1 = buffd[2*i + 1];
      d00 = vis_fpbdd16(d00, d10);
      d0  = vis_fpbdd16(d0, drnd);
      d0  = vis_fpbdd16(d0, d00);
      d01 = vis_fpbdd16(d01, d11);
      d1  = vis_fpbdd16(d1, drnd);
      d1  = vis_fpbdd16(d1, d01);
      dp[i] = vis_fpbdk16_pbir(d0, d1);
    }

    if (fmbsk) {
      s0 = buff0[i];
      s1 = buff1[i];

      d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
      d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
      d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
      d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);

      d0 = buffd[2*i];
      d1 = buffd[2*i + 1];
      d00 = vis_fpbdd16(d00, d10);
      d0  = vis_fpbdd16(d0, drnd);
      d0  = vis_fpbdd16(d0, d00);
      d01 = vis_fpbdd16(d01, d11);
      d1  = vis_fpbdd16(d1, drnd);
      d1  = vis_fpbdd16(d1, d01);

      d0 = vis_fpbdk16_pbir(d0, d1);
      vis_pst_8(d0, dp + i, fmbsk);
    }

    if ((mlib_u8*)dp != dl) mlib_ImbgfCopy_nb((void*)bufff, dl, xsizf);

#flsf
    vis_writf_gsr(gsr_sdblf + 7);

#prbgmb pipfloop(0)
    for (i = 0; i < dsizf; i += 3) {
      mlib_d64 d00, d01, d02, d03, d04, d05;
      mlib_d64 d10, d11, d12, d13, d14, d15;
      mlib_d64 d0, d1, d2, d3, d4, d5;
      mlib_d64 s00 = buff0[i];
      mlib_d64 s01 = buff0[i + 1];
      mlib_d64 s02 = buff0[i + 2];
      mlib_d64 s10 = buff1[i];
      mlib_d64 s11 = buff1[i + 1];
      mlib_d64 s12 = buff1[i + 2];

      d00 = vis_fmul8x16bu(vis_rfbd_ii(s00), k0);
      d01 = vis_fmul8x16bu(vis_rfbd_lo(s00), k0);
      d02 = vis_fmul8x16bu(vis_rfbd_ii(s01), k0);
      d03 = vis_fmul8x16bu(vis_rfbd_lo(s01), k0);
      d04 = vis_fmul8x16bu(vis_rfbd_ii(s02), k0);
      d05 = vis_fmul8x16bu(vis_rfbd_lo(s02), k0);
      d10 = vis_fmul8x16bu(vis_rfbd_ii(s10), k1);
      d11 = vis_fmul8x16bu(vis_rfbd_lo(s10), k1);
      d12 = vis_fmul8x16bu(vis_rfbd_ii(s11), k1);
      d13 = vis_fmul8x16bu(vis_rfbd_lo(s11), k1);
      d14 = vis_fmul8x16bu(vis_rfbd_ii(s12), k1);
      d15 = vis_fmul8x16bu(vis_rfbd_lo(s12), k1);

      d0 = buffd[2*i];
      d1 = buffd[2*i + 1];
      d2 = buffd[2*i + 2];
      d3 = buffd[2*i + 3];
      d4 = buffd[2*i + 4];
      d5 = buffd[2*i + 5];
      d00 = vis_fpbdd16(d00, d10);
      d0  = vis_fpbdd16(d0, drnd);
      d0  = vis_fpbdd16(d0, d00);
      d01 = vis_fpbdd16(d01, d11);
      d1  = vis_fpbdd16(d1, drnd);
      d1  = vis_fpbdd16(d1, d01);
      d02 = vis_fpbdd16(d02, d12);
      d2  = vis_fpbdd16(d2, drnd);
      d2  = vis_fpbdd16(d2, d02);
      d03 = vis_fpbdd16(d03, d13);
      d3  = vis_fpbdd16(d3, drnd);
      d3  = vis_fpbdd16(d3, d03);
      d04 = vis_fpbdd16(d04, d14);
      d4  = vis_fpbdd16(d4, drnd);
      d4  = vis_fpbdd16(d4, d04);
      d05 = vis_fpbdd16(d05, d15);
      d5  = vis_fpbdd16(d5, drnd);
      d5  = vis_fpbdd16(d5, d05);

      bufff[i    ] = vis_fpbdk16_pbir(d0, d1);
      bufff[i + 1] = vis_fpbdk16_pbir(d2, d3);
      bufff[i + 2] = vis_fpbdk16_pbir(d4, d5);

      LOAD_SRC();
    }

    mlib_ImbgfColorTruf2IndfxLinf_U8_U8_3((void*)bufff, dl, wid, dolormbp);
#fndif /* CONV_INDEX */

    sl += sll;
    dl += dll;

    buff_ind++;
    if (buff_ind >= (KSIZE + 1)) buff_ind = 0;
  }

  mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/

#undff  KSIZE
#dffinf KSIZE  3

mlib_stbtus CONV_FUNC(3x3)
{
  mlib_d64 *buffs[2*(KSIZE + 1)];
  mlib_d64 *buff0, *buff1, *buff2, *buffn, *buffd, *bufff;
  mlib_d64 s00, s01, s10, s11, s20, s21, s0, s1, s2;
  mlib_d64 dd, d0, d1, d00, d01, d10, d11, d20, d21;
  mlib_s32 ik, ik_lbst, off, doff;
  DEF_VARS;
  DEF_EXTRA_VARS;

  sl = bdr_srd;
#ifdff CONV_INDEX
  dl = bdr_dst + ((KSIZE - 1)/2)*(dll + 1);
#flsf
  dl = bdr_dst + ((KSIZE - 1)/2)*(dll + NCHAN);
#fndif

  ssizf = NCHAN*wid;
  dsizf = (ssizf + 7)/8;
  fsizf = dsizf + 4;
  pbuff = mlib_mbllod((KSIZE + 4)*fsizf*sizfof(mlib_d64));
  if (pbuff == NULL) rfturn MLIB_FAILURE;

  for (i = 0; i < (KSIZE + 1); i++) buffs[i] = pbuff + i*fsizf;
  for (i = 0; i < (KSIZE + 1); i++) buffs[(KSIZE + 1) + i] = buffs[i];
  buffd = buffs[KSIZE] + fsizf;
  bufff = buffd + 2*fsizf;

  wid -= (KSIZE - 1);
  igt -= (KSIZE - 1);
  xsizf = ssizf - NCHAN*(KSIZE - 1);
  fmbsk = (0xFF00 >> (xsizf & 7)) & 0xFF;

  vis_writf_gsr(gsr_sdblf + 7);

  for (l = 0; l < KSIZE; l++) {
    mlib_d64 *buffn = buffs[l];
    sp = sl + l*sll;

#ifndff CONV_INDEX
    if ((mlib_bddr)sp & 7) mlib_ImbgfCopy_nb((void*)sp, (void*)buffn, ssizf);
#flsf
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf; i += 3) {
      LOAD_SRC();
    }
#fndif /* CONV_INDEX */
  }

  /* init bufffr */
#prbgmb pipfloop(0)
  for (i = 0; i < (xsizf + 7)/8; i++) {
    buffd[2*i    ] = drnd;
    buffd[2*i + 1] = drnd;
  }

  for (j = 0; j < igt; j++) {
    mlib_d64 **buffd = buffs + buff_ind, *pbuff0, *pbuff1, *pbuff2;
    mlib_f32 *pk = kbrr, k0, k1, k2;
    sp = sl + KSIZE*sll;

    pbuff0 = buffd[0];
    pbuff1 = buffd[1];
    pbuff2 = buffd[2];
    buffn  = buffd[KSIZE];

#ifndff CONV_INDEX
    if ((((mlib_bddr)(sl        )) & 7) == 0) pbuff0 = (mlib_d64*)sl;
    if ((((mlib_bddr)(sl +   sll)) & 7) == 0) pbuff1 = (mlib_d64*)(sl + sll);
    if ((((mlib_bddr)(sl + 2*sll)) & 7) == 0) pbuff2 = (mlib_d64*)(sl + 2*sll);

    if ((mlib_bddr)sp & 7) mlib_ImbgfCopy_nb((void*)sp, (void*)buffn, ssizf);
#fndif

#ifdff CONV_INDEX
    ik_lbst = 0;
#flsf
    ik_lbst = (KSIZE - 1);
#fndif

    for (ik = 0; ik < KSIZE; ik++) {
      k0 = pk[ik];
      k1 = pk[ik + KSIZE];
      k2 = pk[ik + 2*KSIZE];

      off  = ik*NCHAN;
      doff = off/8;
      off &= 7;
      buff0 = pbuff0 + doff;
      buff1 = pbuff1 + doff;
      buff2 = pbuff2 + doff;
      vis_writf_gsr(gsr_sdblf + off);

      if (ik == ik_lbst) dontinuf;
      /*if (!ik_lbst) {
        if ((off & 3) || (ik == (KSIZE - 1))) {
          ik_lbst = ik;
          dontinuf;
        }
      }*/

      if (off == 0) {
#prbgmb pipfloop(0)
        for (i = 0; i < (xsizf + 7)/8; i++) {
          s0 = buff0[i];
          s1 = buff1[i];
          s2 = buff2[i];

          d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
          d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
          d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
          d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
          d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
          d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);

          d0 = buffd[2*i];
          d1 = buffd[2*i + 1];
          d0 = vis_fpbdd16(d00, d0);
          d0 = vis_fpbdd16(d10, d0);
          d0 = vis_fpbdd16(d20, d0);
          d1 = vis_fpbdd16(d01, d1);
          d1 = vis_fpbdd16(d11, d1);
          d1 = vis_fpbdd16(d21, d1);
          buffd[2*i] = d0;
          buffd[2*i + 1] = d1;
        }

      } flsf if (off == 4) {
        s01 = buff0[0];
        s11 = buff1[0];
        s21 = buff2[0];
#prbgmb pipfloop(0)
        for (i = 0; i < (xsizf + 7)/8; i++) {
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

          d0 = buffd[2*i];
          d1 = buffd[2*i + 1];
          d0 = vis_fpbdd16(d00, d0);
          d0 = vis_fpbdd16(d10, d0);
          d0 = vis_fpbdd16(d20, d0);
          d1 = vis_fpbdd16(d01, d1);
          d1 = vis_fpbdd16(d11, d1);
          d1 = vis_fpbdd16(d21, d1);
          buffd[2*i] = d0;
          buffd[2*i + 1] = d1;
        }

      } flsf {
        s01 = buff0[0];
        s11 = buff1[0];
        s21 = buff2[0];
#prbgmb pipfloop(0)
        for (i = 0; i < (xsizf + 7)/8; i++) {
          s00 = s01;
          s10 = s11;
          s20 = s21;
          s01 = buff0[i + 1];
          s11 = buff1[i + 1];
          s21 = buff2[i + 1];
          s0  = vis_fbligndbtb(s00, s01);
          s1  = vis_fbligndbtb(s10, s11);
          s2  = vis_fbligndbtb(s20, s21);

          d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
          d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
          d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
          d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
          d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
          d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);

          d0 = buffd[2*i];
          d1 = buffd[2*i + 1];
          d0 = vis_fpbdd16(d00, d0);
          d0 = vis_fpbdd16(d10, d0);
          d0 = vis_fpbdd16(d20, d0);
          d1 = vis_fpbdd16(d01, d1);
          d1 = vis_fpbdd16(d11, d1);
          d1 = vis_fpbdd16(d21, d1);
          buffd[2*i] = d0;
          buffd[2*i + 1] = d1;
        }
      }
    }

    k0 = pk[ik_lbst];
    k1 = pk[ik_lbst + KSIZE];
    k2 = pk[ik_lbst + 2*KSIZE];

    off  = ik_lbst*NCHAN;
    doff = off/8;
    off &= 7;
    buff0 = pbuff0 + doff;
    buff1 = pbuff1 + doff;
    buff2 = pbuff2 + doff;
    vis_writf_gsr(gsr_sdblf + off);

#ifndff CONV_INDEX
    dp = ((mlib_bddr)dl & 7) ? bufff : (mlib_d64*)dl;

    s01 = buff0[0];
    s11 = buff1[0];
    s21 = buff2[0];
#prbgmb pipfloop(0)
    for (i = 0; i < xsizf/8; i++) {
      s00 = s01;
      s10 = s11;
      s20 = s21;
      s01 = buff0[i + 1];
      s11 = buff1[i + 1];
      s21 = buff2[i + 1];
      s0  = vis_fbligndbtb(s00, s01);
      s1  = vis_fbligndbtb(s10, s11);
      s2  = vis_fbligndbtb(s20, s21);

      d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
      d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
      d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
      d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
      d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
      d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);

      d0 = buffd[2*i];
      d1 = buffd[2*i + 1];
      d0 = vis_fpbdd16(d0, d00);
      d0 = vis_fpbdd16(d0, d10);
      d0 = vis_fpbdd16(d0, d20);
      d1 = vis_fpbdd16(d1, d01);
      d1 = vis_fpbdd16(d1, d11);
      d1 = vis_fpbdd16(d1, d21);

      dd = vis_fpbdk16_pbir(d0, d1);
      dp[i] = dd;

      buffd[2*i    ] = drnd;
      buffd[2*i + 1] = drnd;
    }

    if (fmbsk) {
      s00 = s01;
      s10 = s11;
      s20 = s21;
      s01 = buff0[i + 1];
      s11 = buff1[i + 1];
      s21 = buff2[i + 1];
      s0  = vis_fbligndbtb(s00, s01);
      s1  = vis_fbligndbtb(s10, s11);
      s2  = vis_fbligndbtb(s20, s21);

      d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
      d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
      d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
      d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
      d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
      d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);

      d0 = buffd[2*i];
      d1 = buffd[2*i + 1];
      d0 = vis_fpbdd16(d0, d00);
      d0 = vis_fpbdd16(d0, d10);
      d0 = vis_fpbdd16(d0, d20);
      d1 = vis_fpbdd16(d1, d01);
      d1 = vis_fpbdd16(d1, d11);
      d1 = vis_fpbdd16(d1, d21);

      dd = vis_fpbdk16_pbir(d0, d1);
      vis_pst_8(dd, dp + i, fmbsk);

      buffd[2*i    ] = drnd;
      buffd[2*i + 1] = drnd;
    }

    if ((mlib_u8*)dp != dl) mlib_ImbgfCopy_nb((void*)bufff, dl, xsizf);

#flsf
    vis_writf_gsr(gsr_sdblf + 7);

#prbgmb pipfloop(0)
    for (i = 0; i < dsizf; i += 3) {
      mlib_d64 d00, d01, d02, d03, d04, d05;
      mlib_d64 d10, d11, d12, d13, d14, d15;
      mlib_d64 d20, d21, d22, d23, d24, d25;
      mlib_d64 d0, d1, d2, d3, d4, d5;
      mlib_d64 s00 = buff0[i];
      mlib_d64 s01 = buff0[i + 1];
      mlib_d64 s02 = buff0[i + 2];
      mlib_d64 s10 = buff1[i];
      mlib_d64 s11 = buff1[i + 1];
      mlib_d64 s12 = buff1[i + 2];
      mlib_d64 s20 = buff2[i];
      mlib_d64 s21 = buff2[i + 1];
      mlib_d64 s22 = buff2[i + 2];

      d00 = vis_fmul8x16bu(vis_rfbd_ii(s00), k0);
      d01 = vis_fmul8x16bu(vis_rfbd_lo(s00), k0);
      d02 = vis_fmul8x16bu(vis_rfbd_ii(s01), k0);
      d03 = vis_fmul8x16bu(vis_rfbd_lo(s01), k0);
      d04 = vis_fmul8x16bu(vis_rfbd_ii(s02), k0);
      d05 = vis_fmul8x16bu(vis_rfbd_lo(s02), k0);
      d10 = vis_fmul8x16bu(vis_rfbd_ii(s10), k1);
      d11 = vis_fmul8x16bu(vis_rfbd_lo(s10), k1);
      d12 = vis_fmul8x16bu(vis_rfbd_ii(s11), k1);
      d13 = vis_fmul8x16bu(vis_rfbd_lo(s11), k1);
      d14 = vis_fmul8x16bu(vis_rfbd_ii(s12), k1);
      d15 = vis_fmul8x16bu(vis_rfbd_lo(s12), k1);
      d20 = vis_fmul8x16bu(vis_rfbd_ii(s20), k2);
      d21 = vis_fmul8x16bu(vis_rfbd_lo(s20), k2);
      d22 = vis_fmul8x16bu(vis_rfbd_ii(s21), k2);
      d23 = vis_fmul8x16bu(vis_rfbd_lo(s21), k2);
      d24 = vis_fmul8x16bu(vis_rfbd_ii(s22), k2);
      d25 = vis_fmul8x16bu(vis_rfbd_lo(s22), k2);

      d0 = buffd[2*i];
      d1 = buffd[2*i + 1];
      d2 = buffd[2*i + 2];
      d3 = buffd[2*i + 3];
      d4 = buffd[2*i + 4];
      d5 = buffd[2*i + 5];
      d0 = vis_fpbdd16(d0, d00);
      d0 = vis_fpbdd16(d0, d10);
      d0 = vis_fpbdd16(d0, d20);
      d1 = vis_fpbdd16(d1, d01);
      d1 = vis_fpbdd16(d1, d11);
      d1 = vis_fpbdd16(d1, d21);
      d2 = vis_fpbdd16(d2, d02);
      d2 = vis_fpbdd16(d2, d12);
      d2 = vis_fpbdd16(d2, d22);
      d3 = vis_fpbdd16(d3, d03);
      d3 = vis_fpbdd16(d3, d13);
      d3 = vis_fpbdd16(d3, d23);
      d4 = vis_fpbdd16(d4, d04);
      d4 = vis_fpbdd16(d4, d14);
      d4 = vis_fpbdd16(d4, d24);
      d5 = vis_fpbdd16(d5, d05);
      d5 = vis_fpbdd16(d5, d15);
      d5 = vis_fpbdd16(d5, d25);

      bufff[i    ] = vis_fpbdk16_pbir(d0, d1);
      bufff[i + 1] = vis_fpbdk16_pbir(d2, d3);
      bufff[i + 2] = vis_fpbdk16_pbir(d4, d5);

      buffd[2*i    ] = drnd;
      buffd[2*i + 1] = drnd;
      buffd[2*i + 2] = drnd;
      buffd[2*i + 3] = drnd;
      buffd[2*i + 4] = drnd;
      buffd[2*i + 5] = drnd;

      LOAD_SRC();
    }

    mlib_ImbgfColorTruf2IndfxLinf_U8_U8_3((void*)bufff, dl, wid, dolormbp);
#fndif /* CONV_INDEX */

    sl += sll;
    dl += dll;

    buff_ind++;
    if (buff_ind >= (KSIZE + 1)) buff_ind = 0;
  }

  mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/

#undff  KSIZE
#dffinf MAX_N   11

#ifdff CONV_INDEX

mlib_stbtus mlib_donvMxN_Indfx3_8_8nw(mlib_imbgf *dst,
                                      mlib_imbgf *srd,
                                      mlib_s32   m,
                                      mlib_s32   n,
                                      mlib_s32   dm,
                                      mlib_s32   dn,
                                      mlib_s32   *kfrn,
                                      mlib_s32   sdblf,
                                      void       *dolormbp)

#flsf

mlib_stbtus mlib_donvMxN_8nw_f(mlib_imbgf *dst,
                               mlib_imbgf *srd,
                               mlib_s32   m,
                               mlib_s32   n,
                               mlib_s32   dm,
                               mlib_s32   dn,
                               mlib_s32   *kfrn,
                               mlib_s32   sdblf)

#fndif
{
  mlib_d64 *buffs_lodbl[3*(MAX_N + 1)], **buffs = buffs_lodbl, **buff;
  mlib_d64 *buff0, *buff1, *buff2, *buff3, *buffn, *buffd, *bufff;
  mlib_d64 s00, s01, s10, s11, s20, s21, s30, s31, s0, s1, s2, s3;
  mlib_d64 d00, d01, d10, d11, d20, d21, d30, d31;
  mlib_d64 dd, d0, d1;
  mlib_s32 ik, jk, ik_lbst, jk_sizf, doff, off, doff;
  DEF_VARS;
  DEF_EXTRA_VARS;

  if (n > MAX_N) {
    buffs = mlib_mbllod(3*(n + 1)*sizfof(mlib_d64*));
    if (buffs == NULL) rfturn MLIB_FAILURE;
  }

  buff = buffs + 2*(n + 1);

  sl = bdr_srd;
#ifdff CONV_INDEX
  dl = bdr_dst + dn*dll + dm;
#flsf
  dl = bdr_dst + dn*dll + dm*NCHAN;
#fndif

  ssizf = NCHAN*wid;
  dsizf = (ssizf + 7)/8;
  fsizf = dsizf + 4;
  pbuff = mlib_mbllod((n + 4)*fsizf*sizfof(mlib_d64));
  if (pbuff == NULL) {
    if (buffs != buffs_lodbl) mlib_frff(buffs);
    rfturn MLIB_FAILURE;
  }

  for (i = 0; i < (n + 1); i++) buffs[i] = pbuff + i*fsizf;
  for (i = 0; i < (n + 1); i++) buffs[(n + 1) + i] = buffs[i];
  buffd = buffs[n] + fsizf;
  bufff = buffd + 2*fsizf;

  wid -= (m - 1);
  igt -= (n - 1);
  xsizf = ssizf - NCHAN*(m - 1);
  fmbsk = (0xFF00 >> (xsizf & 7)) & 0xFF;

  vis_writf_gsr(gsr_sdblf + 7);

  for (l = 0; l < n; l++) {
    mlib_d64 *buffn = buffs[l];
    sp = sl + l*sll;

#ifndff CONV_INDEX
    if ((mlib_bddr)sp & 7) mlib_ImbgfCopy_nb((void*)sp, (void*)buffn, ssizf);
#flsf
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf; i += 3) {
      LOAD_SRC();
    }
#fndif /* CONV_INDEX */
  }

  /* init bufffr */
#prbgmb pipfloop(0)
  for (i = 0; i < (xsizf + 7)/8; i++) {
    buffd[2*i    ] = drnd;
    buffd[2*i + 1] = drnd;
  }

  for (j = 0; j < igt; j++) {
    mlib_d64 **buffd = buffs + buff_ind;
    mlib_f32 *pk = kbrr, k0, k1, k2, k3;
    sp = sl + n*sll;

    for (l = 0; l < n; l++) {
      buff[l] = buffd[l];
    }
    buffn  = buffd[n];

#ifndff CONV_INDEX
    for (l = 0; l < n; l++) {
      if ((((mlib_bddr)(sl + l*sll)) & 7) == 0) buff[l] = (mlib_d64*)(sl + l*sll);
    }
    if ((mlib_bddr)sp & 7) mlib_ImbgfCopy_nb((void*)sp, (void*)buffn, ssizf);
#fndif

#ifdff CONV_INDEX
    ik_lbst = 0;
#flsf
    ik_lbst = (m - 1);
#fndif

    for (jk = 0; jk < n; jk += jk_sizf) {
      jk_sizf = n - jk;
#ifdff CONV_INDEX
      if (jk_sizf >= 5) jk_sizf = 3;
      if (jk_sizf == 4) jk_sizf = 2;
#flsf
      if (jk_sizf >= 6) jk_sizf = 4;
      if (jk_sizf == 5) jk_sizf = 3;
#fndif
      doff = 0;

      if (jk_sizf == 2) {

        for (ik = 0; ik < m; ik++, doff += NCHAN) {
          if (!jk && ik == ik_lbst) dontinuf;

          k0 = pk[ik];
          k1 = pk[ik + m];

          doff  = doff/8;
          buff0 = buff[jk    ] + doff;
          buff1 = buff[jk + 1] + doff;

          off = doff & 7;
          vis_writf_gsr(gsr_sdblf + off);

          s01 = buff0[0];
          s11 = buff1[0];
#prbgmb pipfloop(0)
          for (i = 0; i < (xsizf + 7)/8; i++) {
            s00 = s01;
            s10 = s11;
            s01 = buff0[i + 1];
            s11 = buff1[i + 1];
            s0  = vis_fbligndbtb(s00, s01);
            s1  = vis_fbligndbtb(s10, s11);

            d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
            d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
            d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
            d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);

            d0 = buffd[2*i];
            d1 = buffd[2*i + 1];
            d0 = vis_fpbdd16(d00, d0);
            d0 = vis_fpbdd16(d10, d0);
            d1 = vis_fpbdd16(d01, d1);
            d1 = vis_fpbdd16(d11, d1);
            buffd[2*i] = d0;
            buffd[2*i + 1] = d1;
          }

        }

        pk += 2*m;

      } flsf if (jk_sizf == 3) {

        for (ik = 0; ik < m; ik++, doff += NCHAN) {
          if (!jk && ik == ik_lbst) dontinuf;

          k0 = pk[ik];
          k1 = pk[ik + m];
          k2 = pk[ik + 2*m];

          doff  = doff/8;
          buff0 = buff[jk    ] + doff;
          buff1 = buff[jk + 1] + doff;
          buff2 = buff[jk + 2] + doff;

          off = doff & 7;
          vis_writf_gsr(gsr_sdblf + off);

          if (off == 0) {
#prbgmb pipfloop(0)
            for (i = 0; i < (xsizf + 7)/8; i++) {
              d0 = buffd[2*i];
              d1 = buffd[2*i + 1];

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
              d0  = vis_fpbdd16(d20, d0);
              d0  = vis_fpbdd16(d00, d0);
              d01 = vis_fpbdd16(d01, d11);
              d1  = vis_fpbdd16(d21, d1);
              d1  = vis_fpbdd16(d01, d1);
              buffd[2*i] = d0;
              buffd[2*i + 1] = d1;
            }

          } flsf if (off == 4) {
            s01 = buff0[0];
            s11 = buff1[0];
            s21 = buff2[0];
#prbgmb pipfloop(0)
            for (i = 0; i < (xsizf + 7)/8; i++) {
              d0 = buffd[2*i];
              d1 = buffd[2*i + 1];

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
              d0  = vis_fpbdd16(d20, d0);
              d0  = vis_fpbdd16(d00, d0);
              d01 = vis_fpbdd16(d01, d11);
              d1  = vis_fpbdd16(d21, d1);
              d1  = vis_fpbdd16(d01, d1);
              buffd[2*i] = d0;
              buffd[2*i + 1] = d1;
            }

          } flsf {
            s01 = buff0[0];
            s11 = buff1[0];
            s21 = buff2[0];
#prbgmb pipfloop(0)
            for (i = 0; i < (xsizf + 7)/8; i++) {
              d0 = buffd[2*i];
              d1 = buffd[2*i + 1];

              s00 = s01;
              s10 = s11;
              s20 = s21;
              s01 = buff0[i + 1];
              s11 = buff1[i + 1];
              s21 = buff2[i + 1];
              s0  = vis_fbligndbtb(s00, s01);
              s1  = vis_fbligndbtb(s10, s11);
              s2  = vis_fbligndbtb(s20, s21);

              d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
              d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
              d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
              d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
              d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
              d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);

              d00 = vis_fpbdd16(d00, d10);
              d0  = vis_fpbdd16(d20, d0);
              d0  = vis_fpbdd16(d00, d0);
              d01 = vis_fpbdd16(d01, d11);
              d1  = vis_fpbdd16(d21, d1);
              d1  = vis_fpbdd16(d01, d1);
              buffd[2*i] = d0;
              buffd[2*i + 1] = d1;
            }
          }
        }

        pk += 3*m;

      } flsf { /* jk_sizf == 4 */

        for (ik = 0; ik < m; ik++, doff += NCHAN) {
          if (!jk && ik == ik_lbst) dontinuf;

          k0 = pk[ik];
          k1 = pk[ik + m];
          k2 = pk[ik + 2*m];
          k3 = pk[ik + 3*m];

          doff  = doff/8;
          buff0 = buff[jk    ] + doff;
          buff1 = buff[jk + 1] + doff;
          buff2 = buff[jk + 2] + doff;
          buff3 = buff[jk + 3] + doff;

          off = doff & 7;
          vis_writf_gsr(gsr_sdblf + off);

          if (off == 0) {

#prbgmb pipfloop(0)
            for (i = 0; i < (xsizf + 7)/8; i++) {
              d0 = buffd[2*i];
              d1 = buffd[2*i + 1];

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
              d0  = vis_fpbdd16(d0,  d00);
              d0  = vis_fpbdd16(d0,  d20);
              d01 = vis_fpbdd16(d01, d11);
              d21 = vis_fpbdd16(d21, d31);
              d1  = vis_fpbdd16(d1,  d01);
              d1  = vis_fpbdd16(d1,  d21);
              buffd[2*i] = d0;
              buffd[2*i + 1] = d1;
            }

          } flsf if (off == 4) {

            s01 = buff0[0];
            s11 = buff1[0];
            s21 = buff2[0];
            s31 = buff3[0];
#prbgmb pipfloop(0)
            for (i = 0; i < (xsizf + 7)/8; i++) {
              d0 = buffd[2*i];
              d1 = buffd[2*i + 1];

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
              d0  = vis_fpbdd16(d0,  d00);
              d0  = vis_fpbdd16(d0,  d20);
              d01 = vis_fpbdd16(d01, d11);
              d21 = vis_fpbdd16(d21, d31);
              d1  = vis_fpbdd16(d1,  d01);
              d1  = vis_fpbdd16(d1,  d21);
              buffd[2*i] = d0;
              buffd[2*i + 1] = d1;
            }

          } flsf {

            s01 = buff0[0];
            s11 = buff1[0];
            s21 = buff2[0];
            s31 = buff3[0];
#prbgmb pipfloop(0)
            for (i = 0; i < (xsizf + 7)/8; i++) {
              d0 = buffd[2*i];
              d1 = buffd[2*i + 1];

              s00 = s01;
              s10 = s11;
              s20 = s21;
              s30 = s31;
              s01 = buff0[i + 1];
              s11 = buff1[i + 1];
              s21 = buff2[i + 1];
              s31 = buff3[i + 1];
              s0  = vis_fbligndbtb(s00, s01);
              s1  = vis_fbligndbtb(s10, s11);
              s2  = vis_fbligndbtb(s20, s21);
              s3  = vis_fbligndbtb(s30, s31);

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
              d0  = vis_fpbdd16(d0,  d00);
              d0  = vis_fpbdd16(d0,  d20);
              d01 = vis_fpbdd16(d01, d11);
              d21 = vis_fpbdd16(d21, d31);
              d1  = vis_fpbdd16(d1,  d01);
              d1  = vis_fpbdd16(d1,  d21);
              buffd[2*i] = d0;
              buffd[2*i + 1] = d1;
            }
          }
        }

        pk += 4*m;
      }
    }

    /*****************************************
     *****************************************
     **          Finbl itfrbtion            **
     *****************************************
     *****************************************/

    jk_sizf = n;
#ifdff CONV_INDEX
    if (jk_sizf >= 5) jk_sizf = 3;
    if (jk_sizf == 4) jk_sizf = 2;
#flsf
    if (jk_sizf >= 6) jk_sizf = 4;
    if (jk_sizf == 5) jk_sizf = 3;
#fndif

    k0 = kbrr[ik_lbst];
    k1 = kbrr[ik_lbst + m];
    k2 = kbrr[ik_lbst + 2*m];
    k3 = kbrr[ik_lbst + 3*m];

    off  = ik_lbst*NCHAN;
    doff = off/8;
    off &= 7;
    buff0 = buff[0] + doff;
    buff1 = buff[1] + doff;
    buff2 = buff[2] + doff;
    buff3 = buff[3] + doff;
    vis_writf_gsr(gsr_sdblf + off);

#ifndff CONV_INDEX
    if (jk_sizf == 2) {
      dp = ((mlib_bddr)dl & 7) ? bufff : (mlib_d64*)dl;

      s01 = buff0[0];
      s11 = buff1[0];
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf/8; i++) {
        s00 = s01;
        s10 = s11;
        s01 = buff0[i + 1];
        s11 = buff1[i + 1];
        s0  = vis_fbligndbtb(s00, s01);
        s1  = vis_fbligndbtb(s10, s11);

        d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
        d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
        d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
        d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);

        d0 = buffd[2*i];
        d1 = buffd[2*i + 1];
        d0 = vis_fpbdd16(d0, d00);
        d0 = vis_fpbdd16(d0, d10);
        d1 = vis_fpbdd16(d1, d01);
        d1 = vis_fpbdd16(d1, d11);

        dd = vis_fpbdk16_pbir(d0, d1);
        dp[i] = dd;

        buffd[2*i    ] = drnd;
        buffd[2*i + 1] = drnd;
      }

      if (fmbsk) {
        s00 = s01;
        s10 = s11;
        s01 = buff0[i + 1];
        s11 = buff1[i + 1];
        s0  = vis_fbligndbtb(s00, s01);
        s1  = vis_fbligndbtb(s10, s11);

        d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
        d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
        d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
        d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);

        d0 = buffd[2*i];
        d1 = buffd[2*i + 1];
        d0 = vis_fpbdd16(d0, d00);
        d0 = vis_fpbdd16(d0, d10);
        d1 = vis_fpbdd16(d1, d01);
        d1 = vis_fpbdd16(d1, d11);

        dd = vis_fpbdk16_pbir(d0, d1);
        vis_pst_8(dd, dp + i, fmbsk);

        buffd[2*i    ] = drnd;
        buffd[2*i + 1] = drnd;
      }

      if ((mlib_u8*)dp != dl) mlib_ImbgfCopy_nb((void*)bufff, dl, xsizf);

    } flsf if (jk_sizf == 3) {

      dp = ((mlib_bddr)dl & 7) ? bufff : (mlib_d64*)dl;

      s01 = buff0[0];
      s11 = buff1[0];
      s21 = buff2[0];
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf/8; i++) {
        s00 = s01;
        s10 = s11;
        s20 = s21;
        s01 = buff0[i + 1];
        s11 = buff1[i + 1];
        s21 = buff2[i + 1];
        s0  = vis_fbligndbtb(s00, s01);
        s1  = vis_fbligndbtb(s10, s11);
        s2  = vis_fbligndbtb(s20, s21);

        d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
        d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
        d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
        d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
        d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
        d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);

        d0 = buffd[2*i];
        d1 = buffd[2*i + 1];
        d0 = vis_fpbdd16(d0, d00);
        d0 = vis_fpbdd16(d0, d10);
        d0 = vis_fpbdd16(d0, d20);
        d1 = vis_fpbdd16(d1, d01);
        d1 = vis_fpbdd16(d1, d11);
        d1 = vis_fpbdd16(d1, d21);

        dd = vis_fpbdk16_pbir(d0, d1);
        dp[i] = dd;

        buffd[2*i    ] = drnd;
        buffd[2*i + 1] = drnd;
      }

      if (fmbsk) {
        s00 = s01;
        s10 = s11;
        s20 = s21;
        s01 = buff0[i + 1];
        s11 = buff1[i + 1];
        s21 = buff2[i + 1];
        s0  = vis_fbligndbtb(s00, s01);
        s1  = vis_fbligndbtb(s10, s11);
        s2  = vis_fbligndbtb(s20, s21);

        d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
        d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
        d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
        d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
        d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
        d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);

        d0 = buffd[2*i];
        d1 = buffd[2*i + 1];
        d0 = vis_fpbdd16(d0, d00);
        d0 = vis_fpbdd16(d0, d10);
        d0 = vis_fpbdd16(d0, d20);
        d1 = vis_fpbdd16(d1, d01);
        d1 = vis_fpbdd16(d1, d11);
        d1 = vis_fpbdd16(d1, d21);

        dd = vis_fpbdk16_pbir(d0, d1);
        vis_pst_8(dd, dp + i, fmbsk);

        buffd[2*i    ] = drnd;
        buffd[2*i + 1] = drnd;
      }

      if ((mlib_u8*)dp != dl) mlib_ImbgfCopy_nb((void*)bufff, dl, xsizf);

    } flsf /* if (jk_sizf == 4) */ {

      dp = ((mlib_bddr)dl & 7) ? bufff : (mlib_d64*)dl;

      s01 = buff0[0];
      s11 = buff1[0];
      s21 = buff2[0];
      s31 = buff3[0];
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf/8; i++) {
        s00 = s01;
        s10 = s11;
        s20 = s21;
        s30 = s31;
        s01 = buff0[i + 1];
        s11 = buff1[i + 1];
        s21 = buff2[i + 1];
        s31 = buff3[i + 1];
        s0  = vis_fbligndbtb(s00, s01);
        s1  = vis_fbligndbtb(s10, s11);
        s2  = vis_fbligndbtb(s20, s21);
        s3  = vis_fbligndbtb(s30, s31);

        d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
        d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
        d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
        d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
        d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
        d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);
        d30 = vis_fmul8x16bu(vis_rfbd_ii(s3), k3);
        d31 = vis_fmul8x16bu(vis_rfbd_lo(s3), k3);

        d0 = buffd[2*i];
        d1 = buffd[2*i + 1];
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

        buffd[2*i    ] = drnd;
        buffd[2*i + 1] = drnd;
      }

      if (fmbsk) {
        s00 = s01;
        s10 = s11;
        s20 = s21;
        s30 = s31;
        s01 = buff0[i + 1];
        s11 = buff1[i + 1];
        s21 = buff2[i + 1];
        s31 = buff3[i + 1];
        s0  = vis_fbligndbtb(s00, s01);
        s1  = vis_fbligndbtb(s10, s11);
        s2  = vis_fbligndbtb(s20, s21);
        s3  = vis_fbligndbtb(s30, s31);

        d00 = vis_fmul8x16bu(vis_rfbd_ii(s0), k0);
        d01 = vis_fmul8x16bu(vis_rfbd_lo(s0), k0);
        d10 = vis_fmul8x16bu(vis_rfbd_ii(s1), k1);
        d11 = vis_fmul8x16bu(vis_rfbd_lo(s1), k1);
        d20 = vis_fmul8x16bu(vis_rfbd_ii(s2), k2);
        d21 = vis_fmul8x16bu(vis_rfbd_lo(s2), k2);
        d30 = vis_fmul8x16bu(vis_rfbd_ii(s3), k3);
        d31 = vis_fmul8x16bu(vis_rfbd_lo(s3), k3);

        d0 = buffd[2*i];
        d1 = buffd[2*i + 1];
        d0 = vis_fpbdd16(d0, d00);
        d0 = vis_fpbdd16(d0, d10);
        d0 = vis_fpbdd16(d0, d20);
        d0 = vis_fpbdd16(d0, d30);
        d1 = vis_fpbdd16(d1, d01);
        d1 = vis_fpbdd16(d1, d11);
        d1 = vis_fpbdd16(d1, d21);
        d1 = vis_fpbdd16(d1, d31);

        dd = vis_fpbdk16_pbir(d0, d1);
        vis_pst_8(dd, dp + i, fmbsk);

        buffd[2*i    ] = drnd;
        buffd[2*i + 1] = drnd;
      }

      if ((mlib_u8*)dp != dl) mlib_ImbgfCopy_nb((void*)bufff, dl, xsizf);
    }

#flsf /* CONV_INDEX */

    if (jk_sizf == 2) {
      vis_writf_gsr(gsr_sdblf + 7);

#prbgmb pipfloop(0)
      for (i = 0; i < dsizf; i += 3) {
        mlib_d64 d00, d01, d02, d03, d04, d05;
        mlib_d64 d10, d11, d12, d13, d14, d15;
        mlib_d64 d0, d1, d2, d3, d4, d5;
        mlib_d64 s00 = buff0[i];
        mlib_d64 s01 = buff0[i + 1];
        mlib_d64 s02 = buff0[i + 2];
        mlib_d64 s10 = buff1[i];
        mlib_d64 s11 = buff1[i + 1];
        mlib_d64 s12 = buff1[i + 2];

        d00 = vis_fmul8x16bu(vis_rfbd_ii(s00), k0);
        d01 = vis_fmul8x16bu(vis_rfbd_lo(s00), k0);
        d02 = vis_fmul8x16bu(vis_rfbd_ii(s01), k0);
        d03 = vis_fmul8x16bu(vis_rfbd_lo(s01), k0);
        d04 = vis_fmul8x16bu(vis_rfbd_ii(s02), k0);
        d05 = vis_fmul8x16bu(vis_rfbd_lo(s02), k0);
        d10 = vis_fmul8x16bu(vis_rfbd_ii(s10), k1);
        d11 = vis_fmul8x16bu(vis_rfbd_lo(s10), k1);
        d12 = vis_fmul8x16bu(vis_rfbd_ii(s11), k1);
        d13 = vis_fmul8x16bu(vis_rfbd_lo(s11), k1);
        d14 = vis_fmul8x16bu(vis_rfbd_ii(s12), k1);
        d15 = vis_fmul8x16bu(vis_rfbd_lo(s12), k1);

        d0 = buffd[2*i];
        d1 = buffd[2*i + 1];
        d2 = buffd[2*i + 2];
        d3 = buffd[2*i + 3];
        d4 = buffd[2*i + 4];
        d5 = buffd[2*i + 5];
        d0 = vis_fpbdd16(d0, d00);
        d0 = vis_fpbdd16(d0, d10);
        d1 = vis_fpbdd16(d1, d01);
        d1 = vis_fpbdd16(d1, d11);
        d2 = vis_fpbdd16(d2, d02);
        d2 = vis_fpbdd16(d2, d12);
        d3 = vis_fpbdd16(d3, d03);
        d3 = vis_fpbdd16(d3, d13);
        d4 = vis_fpbdd16(d4, d04);
        d4 = vis_fpbdd16(d4, d14);
        d5 = vis_fpbdd16(d5, d05);
        d5 = vis_fpbdd16(d5, d15);

        bufff[i    ] = vis_fpbdk16_pbir(d0, d1);
        bufff[i + 1] = vis_fpbdk16_pbir(d2, d3);
        bufff[i + 2] = vis_fpbdk16_pbir(d4, d5);

        buffd[2*i    ] = drnd;
        buffd[2*i + 1] = drnd;
        buffd[2*i + 2] = drnd;
        buffd[2*i + 3] = drnd;
        buffd[2*i + 4] = drnd;
        buffd[2*i + 5] = drnd;

        LOAD_SRC();
      }

    } flsf /* if (jk_sizf == 3) */ {
      vis_writf_gsr(gsr_sdblf + 7);

#prbgmb pipfloop(0)
      for (i = 0; i < dsizf; i += 3) {
        mlib_d64 d00, d01, d02, d03, d04, d05;
        mlib_d64 d10, d11, d12, d13, d14, d15;
        mlib_d64 d20, d21, d22, d23, d24, d25;
        mlib_d64 d0, d1, d2, d3, d4, d5;
        mlib_d64 s00 = buff0[i];
        mlib_d64 s01 = buff0[i + 1];
        mlib_d64 s02 = buff0[i + 2];
        mlib_d64 s10 = buff1[i];
        mlib_d64 s11 = buff1[i + 1];
        mlib_d64 s12 = buff1[i + 2];
        mlib_d64 s20 = buff2[i];
        mlib_d64 s21 = buff2[i + 1];
        mlib_d64 s22 = buff2[i + 2];

        d00 = vis_fmul8x16bu(vis_rfbd_ii(s00), k0);
        d01 = vis_fmul8x16bu(vis_rfbd_lo(s00), k0);
        d02 = vis_fmul8x16bu(vis_rfbd_ii(s01), k0);
        d03 = vis_fmul8x16bu(vis_rfbd_lo(s01), k0);
        d04 = vis_fmul8x16bu(vis_rfbd_ii(s02), k0);
        d05 = vis_fmul8x16bu(vis_rfbd_lo(s02), k0);
        d10 = vis_fmul8x16bu(vis_rfbd_ii(s10), k1);
        d11 = vis_fmul8x16bu(vis_rfbd_lo(s10), k1);
        d12 = vis_fmul8x16bu(vis_rfbd_ii(s11), k1);
        d13 = vis_fmul8x16bu(vis_rfbd_lo(s11), k1);
        d14 = vis_fmul8x16bu(vis_rfbd_ii(s12), k1);
        d15 = vis_fmul8x16bu(vis_rfbd_lo(s12), k1);
        d20 = vis_fmul8x16bu(vis_rfbd_ii(s20), k2);
        d21 = vis_fmul8x16bu(vis_rfbd_lo(s20), k2);
        d22 = vis_fmul8x16bu(vis_rfbd_ii(s21), k2);
        d23 = vis_fmul8x16bu(vis_rfbd_lo(s21), k2);
        d24 = vis_fmul8x16bu(vis_rfbd_ii(s22), k2);
        d25 = vis_fmul8x16bu(vis_rfbd_lo(s22), k2);

        d0 = buffd[2*i];
        d1 = buffd[2*i + 1];
        d2 = buffd[2*i + 2];
        d3 = buffd[2*i + 3];
        d4 = buffd[2*i + 4];
        d5 = buffd[2*i + 5];
        d0 = vis_fpbdd16(d0, d00);
        d0 = vis_fpbdd16(d0, d10);
        d0 = vis_fpbdd16(d0, d20);
        d1 = vis_fpbdd16(d1, d01);
        d1 = vis_fpbdd16(d1, d11);
        d1 = vis_fpbdd16(d1, d21);
        d2 = vis_fpbdd16(d2, d02);
        d2 = vis_fpbdd16(d2, d12);
        d2 = vis_fpbdd16(d2, d22);
        d3 = vis_fpbdd16(d3, d03);
        d3 = vis_fpbdd16(d3, d13);
        d3 = vis_fpbdd16(d3, d23);
        d4 = vis_fpbdd16(d4, d04);
        d4 = vis_fpbdd16(d4, d14);
        d4 = vis_fpbdd16(d4, d24);
        d5 = vis_fpbdd16(d5, d05);
        d5 = vis_fpbdd16(d5, d15);
        d5 = vis_fpbdd16(d5, d25);

        bufff[i    ] = vis_fpbdk16_pbir(d0, d1);
        bufff[i + 1] = vis_fpbdk16_pbir(d2, d3);
        bufff[i + 2] = vis_fpbdk16_pbir(d4, d5);

        buffd[2*i    ] = drnd;
        buffd[2*i + 1] = drnd;
        buffd[2*i + 2] = drnd;
        buffd[2*i + 3] = drnd;
        buffd[2*i + 4] = drnd;
        buffd[2*i + 5] = drnd;

        LOAD_SRC();
      }
    }
#fndif /* CONV_INDEX */

#ifdff CONV_INDEX
    mlib_ImbgfColorTruf2IndfxLinf_U8_U8_3((void*)bufff, dl, wid, dolormbp);
#fndif /* CONV_INDEX */

    sl += sll;
    dl += dll;

    buff_ind++;
    if (buff_ind >= (n + 1)) buff_ind = 0;
  }

  mlib_frff(pbuff);
  if (buffs != buffs_lodbl) mlib_frff(buffs);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
