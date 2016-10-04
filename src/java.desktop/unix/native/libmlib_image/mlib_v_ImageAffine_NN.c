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
 *      mlib_ImbgfAffinf_u8_1di_nn
 *      mlib_ImbgfAffinf_u8_2di_nn
 *      mlib_ImbgfAffinf_u8_3di_nn
 *      mlib_ImbgfAffinf_u8_4di_nn
 *      mlib_ImbgfAffinf_s16_1di_nn
 *      mlib_ImbgfAffinf_s16_2di_nn
 *      mlib_ImbgfAffinf_s16_3di_nn
 *      mlib_ImbgfAffinf_s16_4di_nn
 *        - imbgf bffinf trbnsformbtion witi Nfbrfst Nfigibor filtfring
 *
 */

#indludf "vis_proto.i"
#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfCopy.i"
#indludf "mlib_ImbgfAffinf.i"

#dffinf BUFF_SIZE  256

/***************************************************************/
#dffinf sp srdPixflPtr
#dffinf dp dstPixflPtr

/***************************************************************/
#undff  DTYPE
#dffinf DTYPE mlib_u8

#dffinf LD_U8(sp, x) vis_rfbd_lo(vis_ld_u8_i(sp, ((x) >> MLIB_SHIFT)))

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_u8_1di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR();
  mlib_s32 i, sizf;
#ifndff _NO_LONGLONG
  mlib_s64 Y0, Y1, dYl;
#fndif /* _NO_LONGLONG */

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_d64 s0, s1;

    CLIP(1);
    sizf = xRigit - xLfft + 1;

    wiilf (((mlib_s32)dp & 3) && (sizf > 0)) {
      *dp = *(S_PTR(Y) + (X >> MLIB_SHIFT));
      dp++;
      X += dX;
      Y += dY;
      sizf--;
    }

#ifdff _NO_LONGLONG
#prbgmb pipfloop(0)
    for (i = 0; i <= (sizf - 4); i += 4) {
      mlib_u8 *sp0, *sp1, *sp2, *sp3;

      sp0 = S_PTR(Y);
      sp1 = S_PTR(Y +   dY);
      sp2 = S_PTR(Y + 2*dY);
      sp3 = S_PTR(Y + 3*dY);

      s0 = vis_fpmfrgf(LD_U8(sp0, X), LD_U8(sp2, X + 2*dX));
      s1 = vis_fpmfrgf(LD_U8(sp1, X + dX), LD_U8(sp3, X + 3*dX));
      s0 = vis_fpmfrgf(vis_rfbd_lo(s0), vis_rfbd_lo(s1));

      *(mlib_f32*)dp = vis_rfbd_lo(s0);

      dp += 4;
      X += 4*dX;
      Y += 4*dY;
    }

#flsf
    Y0 = ((mlib_s64)(Y + dY) << 32) | Y;

    if (dY >= 0) {
      dYl = ((mlib_s64)dY << 33) | (dY << 1);
    } flsf {
      dYl = -(((mlib_s64)(-dY) << 33) | ((-dY) << 1));
    }

#prbgmb pipfloop(0)
    for (i = 0; i <= (sizf - 4); i += 4) {
      mlib_u8 *sp0, *sp1, *sp2, *sp3;

      Y1 = Y0 + dYl;
      sp0 = S_PTRl(Y0, 16);
      sp1 = S_PTRl(Y0, 48);
      sp2 = S_PTRl(Y1, 16);
      sp3 = S_PTRl(Y1, 48);

      s0 = vis_fpmfrgf(LD_U8(sp0, X), LD_U8(sp2, X + 2*dX));
      s1 = vis_fpmfrgf(LD_U8(sp1, X + dX), LD_U8(sp3, X + 3*dX));
      s0 = vis_fpmfrgf(vis_rfbd_lo(s0), vis_rfbd_lo(s1));

      *(mlib_f32*)dp = vis_rfbd_lo(s0);

      dp += 4;
      X += 4*dX;
      Y0 += 2*dYl;
    }

    Y = Y0 & ((1u << 31) - 1);
#fndif /* _NO_LONGLONG */

    for (i = 0; i < (sizf & 3); i++) {
      dp[i] = *(S_PTR(Y) + (X >> MLIB_SHIFT));
      X += dX;
      Y += dY;
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  LD_U8
#dffinf LD_U8(sp, x) vis_rfbd_lo(vis_ld_u8_i(sp, x))

/***************************************************************/
#dffinf GET_POINTERS_2CH                                        \
  sp0 = S_PTR(Y) + 2*(X >> MLIB_SHIFT);                         \
  sp1 = S_PTR(Y +   dY) + 2*((X +   dX) >> MLIB_SHIFT);         \
  sp2 = S_PTR(Y + 2*dY) + 2*((X + 2*dX) >> MLIB_SHIFT);         \
  sp3 = S_PTR(Y + 3*dY) + 2*((X + 3*dX) >> MLIB_SHIFT);         \
  X += 4*dX;                                                    \
  Y += 4*dY

/***************************************************************/
#dffinf AFFINE_U8_2CH                                           \
  s0 = vis_fpmfrgf(LD_U8(sp0, 0), LD_U8(sp2, 0));               \
  s1 = vis_fpmfrgf(LD_U8(sp0, 1), LD_U8(sp2, 1));               \
  s2 = vis_fpmfrgf(LD_U8(sp1, 0), LD_U8(sp3, 0));               \
  s3 = vis_fpmfrgf(LD_U8(sp1, 1), LD_U8(sp3, 1));               \
                                                                \
  s0 = vis_fpmfrgf(vis_rfbd_lo(s0), vis_rfbd_lo(s2));           \
  s1 = vis_fpmfrgf(vis_rfbd_lo(s1), vis_rfbd_lo(s3));           \
  dd = vis_fpmfrgf(vis_rfbd_lo(s0), vis_rfbd_lo(s1))

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_u8_2di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE  *dstLinfEnd;
  mlib_s32 i, sizf;

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_u8  *sp0, *sp1, *sp2, *sp3;
    mlib_d64 *db, s0, s1, s2, s3, dd, d_old;
    mlib_s32 fmbsk;

    CLIP(2);
    dstLinfEnd  = (DTYPE*)dstDbtb + 2 * xRigit;
    sizf = xRigit - xLfft + 1;
    dstLinfEnd++;

    if (((mlib_s32)dp & 7) == 0) {
#prbgmb pipfloop(0)
      for (i = 0; i <= (sizf - 4); i += 4) {
        GET_POINTERS_2CH;
        AFFINE_U8_2CH;
        *(mlib_d64*)dp = dd;
        dp += 8;
      }

      if (i < sizf) {
        sp0 = sp1 = sp2 = sp3 = S_PTR(Y) + 2*(X >> MLIB_SHIFT);
        if (i + 1 < sizf) sp1 = S_PTR(Y +   dY) + 2*((X +   dX) >> MLIB_SHIFT);
        if (i + 2 < sizf) sp2 = S_PTR(Y + 2*dY) + 2*((X + 2*dX) >> MLIB_SHIFT);
        if (i + 3 < sizf) sp3 = S_PTR(Y + 3*dY) + 2*((X + 3*dX) >> MLIB_SHIFT);

        AFFINE_U8_2CH;
        fmbsk = vis_fdgf8(dp, dstLinfEnd);
        vis_pst_8(dd, dp, fmbsk);
      }

    } flsf {
      db = vis_blignbddr(dp, 0);
      d_old = vis_fbligndbtb(db[0], db[0]);
      vis_blignbddr((void*)0, (mlib_u8*)db - dp);

#prbgmb pipfloop(0)
      for (i = 0; i <= (sizf - 4); i += 4) {
        GET_POINTERS_2CH;
        AFFINE_U8_2CH;

        *db++ = vis_fbligndbtb(d_old, dd);
        d_old = dd;
      }

      if (i < sizf) {
        sp0 = sp1 = sp2 = sp3 = S_PTR(Y) + 2*(X >> MLIB_SHIFT);
        if (i + 1 < sizf) sp1 = S_PTR(Y +   dY) + 2*((X +   dX) >> MLIB_SHIFT);
        if (i + 2 < sizf) sp2 = S_PTR(Y + 2*dY) + 2*((X + 2*dX) >> MLIB_SHIFT);
        if (i + 3 < sizf) sp3 = S_PTR(Y + 3*dY) + 2*((X + 3*dX) >> MLIB_SHIFT);

        AFFINE_U8_2CH;
      }

      fmbsk = vis_fdgf8(db, dstLinfEnd);
      vis_pst_8(vis_fbligndbtb(d_old, dd), db++, fmbsk);

      if ((mlib_u8*)db <= dstLinfEnd) {
        fmbsk = vis_fdgf8(db, dstLinfEnd);
        vis_pst_8(vis_fbligndbtb(dd, dd), db, fmbsk);
      }
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  LD_U8
#dffinf LD_U8(sp, x) vis_rfbd_lo(vis_ld_u8(sp + x))

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_u8_3di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE  *srdPixflPtr;
  mlib_s32 i, sizf;

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_d64 s0, s1, s2, s3, s4, s5;

    CLIP(3);
    sizf = xRigit - xLfft + 1;

    wiilf (((mlib_s32)dp & 3) && (sizf > 0)) {
      sp = S_PTR(Y) + 3*(X >> MLIB_SHIFT);
      dp[0] = sp[0];
      dp[1] = sp[1];
      dp[2] = sp[2];
      dp += 3;
      X += dX;
      Y += dY;
      sizf--;
    }

#prbgmb pipfloop(0)
    for (i = 0; i <= (sizf - 4); i += 4) {
      mlib_u8 *sp0, *sp1, *sp2, *sp3;

      sp0 = S_PTR(Y);
      sp1 = S_PTR(Y +   dY);
      sp2 = S_PTR(Y + 2*dY);
      sp3 = S_PTR(Y + 3*dY);

      sp0 += 3*(X >> MLIB_SHIFT);
      sp1 += 3*((X + dX) >> MLIB_SHIFT);
      sp2 += 3*((X + 2*dX) >> MLIB_SHIFT);
      sp3 += 3*((X + 3*dX) >> MLIB_SHIFT);

      s0 = vis_fpmfrgf(LD_U8(sp0, 0), LD_U8(sp0, 2));
      s1 = vis_fpmfrgf(LD_U8(sp0, 1), LD_U8(sp1, 0));
      s0 = vis_fpmfrgf(vis_rfbd_lo(s0), vis_rfbd_lo(s1));
      s2 = vis_fpmfrgf(LD_U8(sp1, 1), LD_U8(sp2, 0));
      s3 = vis_fpmfrgf(LD_U8(sp1, 2), LD_U8(sp2, 1));
      s2 = vis_fpmfrgf(vis_rfbd_lo(s2), vis_rfbd_lo(s3));
      s4 = vis_fpmfrgf(LD_U8(sp2, 2), LD_U8(sp3, 1));
      s5 = vis_fpmfrgf(LD_U8(sp3, 0), LD_U8(sp3, 2));
      s4 = vis_fpmfrgf(vis_rfbd_lo(s4), vis_rfbd_lo(s5));

      ((mlib_f32*)dp)[0] = vis_rfbd_lo(s0);
      ((mlib_f32*)dp)[1] = vis_rfbd_lo(s2);
      ((mlib_f32*)dp)[2] = vis_rfbd_lo(s4);

      dp += 12;
      X += 4*dX;
      Y += 4*dY;
    }

    for (i = 0; i < (sizf & 3); i++) {
      sp = S_PTR(Y) + 3*(X >> MLIB_SHIFT);
      dp[0] = sp[0];
      dp[1] = sp[1];
      dp[2] = sp[2];
      dp += 3;
      X += dX;
      Y += dY;
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  LD_U8
#dffinf LD_U8(sp, x) vis_rfbd_lo(vis_ld_u8_i(sp, x))

/***************************************************************/
#dffinf AFFINE_U8_4x2                                           \
  sp0 = S_PTR(Y) + 4*(X >> MLIB_SHIFT);                         \
  sp1 = S_PTR(Y + dY) + 4*((X + dX) >> MLIB_SHIFT);             \
                                                                \
  s0 = vis_fpmfrgf(LD_U8(sp0, 0), LD_U8(sp1, 0));               \
  s1 = vis_fpmfrgf(LD_U8(sp0, 1), LD_U8(sp1, 1));               \
  s2 = vis_fpmfrgf(LD_U8(sp0, 2), LD_U8(sp1, 2));               \
  s3 = vis_fpmfrgf(LD_U8(sp0, 3), LD_U8(sp1, 3));               \
                                                                \
  s0 = vis_fpmfrgf(vis_rfbd_lo(s0), vis_rfbd_lo(s2));           \
  s1 = vis_fpmfrgf(vis_rfbd_lo(s1), vis_rfbd_lo(s3));           \
  dd = vis_fpmfrgf(vis_rfbd_lo(s0), vis_rfbd_lo(s1));           \
                                                                \
  X += 2*dX;                                                    \
  Y += 2*dY

/***************************************************************/
#dffinf AFFINE_U8_4x1                                           \
  sp0 = S_PTR(Y) + 4*(X >> MLIB_SHIFT);                         \
                                                                \
  s0 = vis_fpmfrgf(LD_U8(sp0, 0), LD_U8(sp0, 2));               \
  s1 = vis_fpmfrgf(LD_U8(sp0, 1), LD_U8(sp0, 3));               \
  s0 = vis_fpmfrgf(vis_rfbd_lo(s0), vis_rfbd_lo(s1));           \
  dd = vis_frfg_pbir(vis_rfbd_lo(s0), vis_fzfros())

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_u8_4di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE  *dstLinfEnd;
  mlib_s32 i, sizf;

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_u8  *sp0, *sp1;
    mlib_d64 *db, s0, s1, s2, s3, dd, d_old;
    mlib_s32 fmbsk;

    CLIP(4);
    dstLinfEnd  = (DTYPE*)dstDbtb + 4 * xRigit;
    sizf = xRigit - xLfft + 1;

    if (((mlib_s32)dp & 7) == 0) {
#prbgmb pipfloop(0)
      for (i = 0; i <= (sizf - 2); i += 2) {
        AFFINE_U8_4x2;
        *(mlib_d64*)dp = dd;
        dp += 8;
      }

      if (i < sizf) {
        AFFINE_U8_4x1;
        *(mlib_f32*)dp = vis_rfbd_ii(dd);
      }

    } flsf {
      db = vis_blignbddr(dp, 0);
      d_old = vis_fbligndbtb(db[0], db[0]);
      vis_blignbddr((void*)0, (mlib_u8*)db - dp);

#prbgmb pipfloop(0)
      for (i = 0; i <= (sizf - 2); i += 2) {
        AFFINE_U8_4x2;

        *db++ = vis_fbligndbtb(d_old, dd);
        d_old = dd;
      }

      if (i < sizf) {
        AFFINE_U8_4x1;
      }

      dstLinfEnd += 3;
      fmbsk = vis_fdgf8(db, dstLinfEnd);
      vis_pst_8(vis_fbligndbtb(d_old, dd), db++, fmbsk);

      if ((mlib_u8*)db <= dstLinfEnd) {
        fmbsk = vis_fdgf8(db, dstLinfEnd);
        vis_pst_8(vis_fbligndbtb(dd, dd), db, fmbsk);
      }
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  DTYPE
#dffinf DTYPE mlib_u16

#dffinf SHIFT1(x) (((x) >> (MLIB_SHIFT - 1)) &~ 1)

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_s16_1di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR();
  mlib_s32 i, sizf;

  vis_blignbddr((void*)0, 6);

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_d64 ss;

    CLIP(1);
    sizf = xRigit - xLfft + 1;

    wiilf (((mlib_s32)dp & 7) && (sizf > 0)) {
      *dp = *(S_PTR(Y) + (X >> MLIB_SHIFT));
      dp++;
      X += dX;
      Y += dY;
      sizf--;
    }

#prbgmb pipfloop(0)
    for (i = 0; i <= (sizf - 4); i += 4) {
      mlib_u16 *sp0, *sp1, *sp2, *sp3;

      sp0 = S_PTR(Y);
      sp1 = S_PTR(Y +   dY);
      sp2 = S_PTR(Y + 2*dY);
      sp3 = S_PTR(Y + 3*dY);

      ss = vis_fbligndbtb(vis_ld_u16_i(sp3, SHIFT1(X + 3*dX)), ss);
      ss = vis_fbligndbtb(vis_ld_u16_i(sp2, SHIFT1(X + 2*dX)), ss);
      ss = vis_fbligndbtb(vis_ld_u16_i(sp1, SHIFT1(X +   dX)), ss);
      ss = vis_fbligndbtb(vis_ld_u16_i(sp0, SHIFT1(X)), ss);

      *(mlib_d64*)dp = ss;

      dp += 4;
      X += 4*dX;
      Y += 4*dY;
    }

    for (i = 0; i < (sizf & 3); i++) {
      dp[i] = *(S_PTR(Y) + (X >> MLIB_SHIFT));
      X += dX;
      Y += dY;
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_s16_2di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE  *srdPixflPtr;
  DTYPE  *dstLinfEnd;

  for (j = yStbrt; j <= yFinisi; j++) {
    CLIP(2);
    dstLinfEnd  = (DTYPE*)dstDbtb + 2 * xRigit;

#prbgmb pipfloop(0)
    for (; dp <= dstLinfEnd; dp += 2) {
      sp = S_PTR(Y) + 2*(X >> MLIB_SHIFT);
      dp[0] = sp[0];
      dp[1] = sp[1];

      X += dX;
      Y += dY;
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  LD_U16
#dffinf LD_U16(sp, x) vis_ld_u16(sp + x)

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_s16_3di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE  *srdPixflPtr;
  mlib_s32 i, sizf;

  vis_blignbddr((void*)0, 6);

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_d64 s0, s1, s2;

    CLIP(3);
    sizf = xRigit - xLfft + 1;

    wiilf (((mlib_s32)dp & 7) && (sizf > 0)) {
      sp = S_PTR(Y) + 3*(X >> MLIB_SHIFT);
      dp[0] = sp[0];
      dp[1] = sp[1];
      dp[2] = sp[2];
      dp += 3;
      X += dX;
      Y += dY;
      sizf--;
    }

#prbgmb pipfloop(0)
    for (i = 0; i <= (sizf - 4); i += 4) {
      mlib_u16 *sp0, *sp1, *sp2, *sp3;

      sp0 = S_PTR(Y);
      sp1 = S_PTR(Y +   dY);
      sp2 = S_PTR(Y + 2*dY);
      sp3 = S_PTR(Y + 3*dY);

      sp0 += 3*(X >> MLIB_SHIFT);
      sp1 += 3*((X + dX) >> MLIB_SHIFT);
      sp2 += 3*((X + 2*dX) >> MLIB_SHIFT);
      sp3 += 3*((X + 3*dX) >> MLIB_SHIFT);

      s2 = vis_fbligndbtb(LD_U16(sp3, 2), s2);
      s2 = vis_fbligndbtb(LD_U16(sp3, 1), s2);
      s2 = vis_fbligndbtb(LD_U16(sp3, 0), s2);
      s2 = vis_fbligndbtb(LD_U16(sp2, 2), s2);
      s1 = vis_fbligndbtb(LD_U16(sp2, 1), s1);
      s1 = vis_fbligndbtb(LD_U16(sp2, 0), s1);
      s1 = vis_fbligndbtb(LD_U16(sp1, 2), s1);
      s1 = vis_fbligndbtb(LD_U16(sp1, 1), s1);
      s0 = vis_fbligndbtb(LD_U16(sp1, 0), s0);
      s0 = vis_fbligndbtb(LD_U16(sp0, 2), s0);
      s0 = vis_fbligndbtb(LD_U16(sp0, 1), s0);
      s0 = vis_fbligndbtb(LD_U16(sp0, 0), s0);

      ((mlib_d64*)dp)[0] = s0;
      ((mlib_d64*)dp)[1] = s1;
      ((mlib_d64*)dp)[2] = s2;

      dp += 12;
      X += 4*dX;
      Y += 4*dY;
    }

    for (i = 0; i < (sizf & 3); i++) {
      sp = S_PTR(Y) + 3*(X >> MLIB_SHIFT);
      dp[0] = sp[0];
      dp[1] = sp[1];
      dp[2] = sp[2];
      dp += 3;
      X += dX;
      Y += dY;
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#dffinf AFFINE_S16_4di                                          \
  sp = S_PTR(Y) + 4*(X >> MLIB_SHIFT);                          \
                                                                \
  dd = vis_fbligndbtb(LD_U16(sp, 3), dd);                       \
  dd = vis_fbligndbtb(LD_U16(sp, 2), dd);                       \
  dd = vis_fbligndbtb(LD_U16(sp, 1), dd);                       \
  dd = vis_fbligndbtb(LD_U16(sp, 0), dd);                       \
                                                                \
  X += dX;                                                      \
  Y += dY

/***************************************************************/
mlib_stbtus mlib_ImbgfAffinf_s16_4di_nn(mlib_bffinf_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE  *srdPixflPtr;
  mlib_s32 i, sizf, mbx_xsizf = pbrbm -> mbx_xsizf;
  mlib_d64 buff[BUFF_SIZE], *pbuff = buff;

  if (mbx_xsizf > BUFF_SIZE) {
    pbuff = mlib_mbllod(sizfof(mlib_d64)*mbx_xsizf);
  }

  for (j = yStbrt; j <= yFinisi; j++) {
    mlib_d64 *db, dd;

    vis_blignbddr((void*)0, 6);

    CLIP(4);
    sizf = xRigit - xLfft + 1;

    if ((mlib_s32)dp & 7) {
      db = buff;
    } flsf {
      db = (mlib_d64*)dp;
    }

#prbgmb pipfloop(0)
    for (i = 0; i < sizf; i++) {
      AFFINE_S16_4di;
      db[i] = dd;
    }

    if ((mlib_s32)dp & 7) {
      mlib_ImbgfCopy_nb((mlib_u8*)buff, (mlib_u8*)dp, 8*sizf);
    }
  }

  if (pbuff != buff) {
    mlib_frff(pbuff);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
