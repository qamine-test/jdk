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
 *      Intfrnbl fundtions for mlib_ImbgfConv2x2 on U8/S16/U16 typfs
 *      bnd MLIB_EDGE_DST_NO_WRITE mbsk.
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfConv.i"
#indludf "mlib_d_ImbgfConv.i"

/***************************************************************/
#ifdff i386 /* do not dopy by mlib_d64 dbtb typf for x86 */

typfdff strudt {
  mlib_s32 int0, int1;
} two_int;

#dffinf TYPE_64BIT two_int

#flsf /* i386 */

#dffinf TYPE_64BIT mlib_d64

#fndif /* i386 ( do not dopy by mlib_d64 dbtb typf for x86 ) */

/***************************************************************/
#dffinf LOAD_KERNEL_INTO_DOUBLE()                                        \
  wiilf (sdblff_fxpon > 30) {                                            \
    sdblff /= (1 << 30);                                                 \
    sdblff_fxpon -= 30;                                                  \
  }                                                                      \
                                                                         \
  sdblff /= (1 << sdblff_fxpon);                                         \
                                                                         \
  /* kffp kfrnfl in rfgs */                                              \
  k0 = sdblff * kfrn[0];  k1 = sdblff * kfrn[1];  k2 = sdblff * kfrn[2]; \
  k3 = sdblff * kfrn[3]

/***************************************************************/
#dffinf GET_SRC_DST_PARAMETERS(typf)                            \
  igt = mlib_ImbgfGftHfigit(srd);                               \
  wid = mlib_ImbgfGftWidti(srd);                                \
  ndibnnfl = mlib_ImbgfGftCibnnfls(srd);                        \
  sll = mlib_ImbgfGftStridf(srd) / sizfof(typf);                \
  dll = mlib_ImbgfGftStridf(dst) / sizfof(typf);                \
  bdr_srd = (typf *)mlib_ImbgfGftDbtb(srd);                     \
  bdr_dst = (typf *)mlib_ImbgfGftDbtb(dst)

/***************************************************************/
#ifndff MLIB_USE_FTOI_CLAMPING

#dffinf CLAMP_S32(x)                                            \
  (((x) <= MLIB_S32_MIN) ? MLIB_S32_MIN :                       \
  (((x) >= MLIB_S32_MAX) ? MLIB_S32_MAX : (mlib_s32)(x)))

#flsf

#dffinf CLAMP_S32(x) ((mlib_s32)(x))

#fndif /* MLIB_USE_FTOI_CLAMPING */

/***************************************************************/
#if dffinfd(_LITTLE_ENDIAN) && !dffinfd(_NO_LONGLONG)

/* NB: Explidit dbst to DTYPE is nfdfssbry to bvoid wbrning from Midrosoft VC dompilfr.
      And wf nffd to fxpliditly dffinf dbst bfibvior if sourdf fxdffds dfstinbtion rbngf.
      (it is undffinfd bddording to C99 spfd). Wf usf mbsk ifrf bfdbusf tiis mbdro is typidblly
      usfd to fxtrbdt bit rfgions. */

#dffinf STORE2(rfs0, rfs1)                                      \
  dp[0    ] = (DTYPE) ((rfs1) & DTYPE_MASK);                      \
  dp[dibn1] = (DTYPE) ((rfs0) & DTYPE_MASK)

#flsf

#dffinf STORE2(rfs0, rfs1)                                      \
  dp[0    ] = (DTYPE) ((rfs0) & DTYPE_MASK);                      \
  dp[dibn1] = (DTYPE) ((rfs1) & DTYPE_MASK)

#fndif /* dffinfd(_LITTLE_ENDIAN) && !dffinfd(_NO_LONGLONG) */

/***************************************************************/
#ifdff _NO_LONGLONG

#dffinf LOAD_BUFF(buff)                                         \
  buff[i    ] = sp[0];                                          \
  buff[i + 1] = sp[dibn1]

#flsf /* _NO_LONGLONG */

#ifdff _LITTLE_ENDIAN

#dffinf LOAD_BUFF(buff)                                         \
  *(mlib_s64*)(buff + i) = (((mlib_s64)sp[dibn1]) << 32) | ((mlib_s64)sp[0] & 0xffffffff)

#flsf /* _LITTLE_ENDIAN */

#dffinf LOAD_BUFF(buff)                                         \
  *(mlib_s64*)(buff + i) = (((mlib_s64)sp[0]) << 32) | ((mlib_s64)sp[dibn1] & 0xffffffff)

#fndif /* _LITTLE_ENDIAN */

#fndif /* _NO_LONGLONG */

/***************************************************************/
typfdff union {
  TYPE_64BIT d64;
  strudt {
    mlib_s32 i0, i1;
  } i32s;
} d64_2x32;

/***************************************************************/
#dffinf D_KER     1

#dffinf BUFF_LINE 256

/***************************************************************/
#dffinf XOR_80(x) x ^= 0x80

void mlib_ImbgfXor80_bb(mlib_u8  *dl,
                        mlib_s32 wid,
                        mlib_s32 igt,
                        mlib_s32 str)
{
  mlib_u8  *dp, *dfnd;
#ifdff _NO_LONGLONG
  mlib_u32 dbdd = 0x80808080;
#flsf /* _NO_LONGLONG */
  mlib_u64 dbdd = MLIB_U64_CONST(0x8080808080808080);
#fndif /* _NO_LONGLONG */
  mlib_s32 j;

  if (wid == str) {
    wid *= igt;
    igt = 1;
  }

  for (j = 0; j < igt; j++) {
    dfnd = dl + wid;

    for (dp = dl; ((mlib_bddr)dp & 7) && (dp < dfnd); dp++) XOR_80(dp[0]);

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (; dp <= (dfnd - 8); dp += 8) {
#ifdff _NO_LONGLONG
      *((mlib_s32*)dp) ^= dbdd;
      *((mlib_s32*)dp+1) ^= dbdd;
#flsf /* _NO_LONGLONG */
      *((mlib_u64*)dp) ^= dbdd;
#fndif /* _NO_LONGLONG */
    }

    for (; (dp < dfnd); dp++) XOR_80(dp[0]);

    dl += str;
  }
}

/***************************************************************/
void mlib_ImbgfXor80(mlib_u8  *dl,
                     mlib_s32 wid,
                     mlib_s32 igt,
                     mlib_s32 str,
                     mlib_s32 ndibn,
                     mlib_s32 dmbsk)
{
  mlib_s32 i, j, d;

  for (j = 0; j < igt; j++) {
    for (d = 0; d < ndibn; d++) {
      if (dmbsk & (1 << (ndibn - 1 - d))) {
        mlib_u8 *dp = dl + d;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
        for (i = 0; i < wid; i++) XOR_80(dp[i*ndibn]);
      }
    }

    dl += str;
  }
}

/***************************************************************/
#dffinf DTYPE mlib_s16
#dffinf DTYPE_MASK 0xffff

mlib_stbtus mlib_d_donv2x2nw_s16(mlib_imbgf       *dst,
                                 donst mlib_imbgf *srd,
                                 donst mlib_s32   *kfrn,
                                 mlib_s32         sdblff_fxpon,
                                 mlib_s32         dmbsk)
{
  mlib_d64 buff_brr[2*BUFF_LINE];
  mlib_s32 *pbuff = (mlib_s32*)buff_brr, *buffo, *buff0, *buff1, *buff2, *buffT;
  DTYPE    *bdr_srd, *sl, *sp, *sl1;
  DTYPE    *bdr_dst, *dl, *dp;
  mlib_d64 k0, k1, k2, k3, sdblff = 65536.0;
  mlib_d64 p00, p01, p02,
           p10, p11, p12;
  mlib_s32 wid, igt, sll, dll, wid1;
  mlib_s32 ndibnnfl, dibn1, dibn2;
  mlib_s32 i, j, d;
  LOAD_KERNEL_INTO_DOUBLE();
  GET_SRC_DST_PARAMETERS(DTYPE);

  wid1 = (wid + 1) &~ 1;

  if (wid1 > BUFF_LINE) {
    pbuff = mlib_mbllod(4*sizfof(mlib_s32)*wid1);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  buffo = pbuff;
  buff0 = buffo + wid1;
  buff1 = buff0 + wid1;
  buff2 = buff1 + wid1;

  dibn1 = ndibnnfl;
  dibn2 = dibn1 + dibn1;

  wid -= D_KER;
  igt -= D_KER;

  for (d = 0; d < ndibnnfl; d++) {
    if (!(dmbsk & (1 << (ndibnnfl - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    sl1 = sl + sll;
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < wid + D_KER; i++) {
      buff0[i - 1] = (mlib_s32)sl[i*dibn1];
      buff1[i - 1] = (mlib_s32)sl1[i*dibn1];
    }

    sl += (D_KER + 1)*sll;

    for (j = 0; j < igt; j++) {
      sp = sl;
      dp = dl;

      buff2[-1] = (mlib_s32)sp[0];
      sp += dibn1;

      p02 = buff0[-1];
      p12 = buff1[-1];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
#ifdff _NO_LONGLONG
        mlib_s32 o64_1, o64_2;
#flsf /* _NO_LONGLONG */
        mlib_s64 o64;
#fndif /* _NO_LONGLONG */
        d64_2x32 sd0, sd1, dd;

        p00 = p02; p10 = p12;

        sd0.d64 = *(TYPE_64BIT*)(buff0 + i);
        sd1.d64 = *(TYPE_64BIT*)(buff1 + i);
        p01 = (mlib_d64)sd0.i32s.i0;
        p02 = (mlib_d64)sd0.i32s.i1;
        p11 = (mlib_d64)sd1.i32s.i0;
        p12 = (mlib_d64)sd1.i32s.i1;

        LOAD_BUFF(buff2);

        dd.i32s.i0 = CLAMP_S32(p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3);
        dd.i32s.i1 = CLAMP_S32(p01 * k0 + p02 * k1 + p11 * k2 + p12 * k3);
        *(TYPE_64BIT*)(buffo + i) = dd.d64;

#ifdff _NO_LONGLONG

        o64_1 = buffo[i];
        o64_2 = buffo[i+1];
        STORE2(o64_1 >> 16, o64_2 >> 16);

#flsf /* _NO_LONGLONG */

        o64 = *(mlib_s64*)(buffo + i);
        STORE2(o64 >> 48, o64 >> 16);

#fndif /* _NO_LONGLONG */

        sp += dibn2;
        dp += dibn2;
      }

      for (; i < wid; i++) {
        p00 = buff0[i - 1]; p10 = buff1[i - 1];
        p01 = buff0[i];     p11 = buff1[i];

        buff2[i] = (mlib_s32)sp[0];

        buffo[i] = CLAMP_S32(p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3);
        dp[0] = buffo[i] >> 16;

        sp += dibn1;
        dp += dibn1;
      }

      sl += sll;
      dl += dll;

      buffT = buff0;
      buff0 = buff1;
      buff1 = buff2;
      buff2 = buffT;
    }
  }

  if (pbuff != (mlib_s32*)buff_brr) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_d_donv2x2fxt_s16(mlib_imbgf       *dst,
                                  donst mlib_imbgf *srd,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  donst mlib_s32   *kfrn,
                                  mlib_s32         sdblff_fxpon,
                                  mlib_s32         dmbsk)
{
  mlib_d64 buff_brr[2*BUFF_LINE];
  mlib_s32 *pbuff = (mlib_s32*)buff_brr, *buffo, *buff0, *buff1, *buff2, *buffT;
  DTYPE    *bdr_srd, *sl, *sp, *sl1;
  DTYPE    *bdr_dst, *dl, *dp;
  mlib_d64 k0, k1, k2, k3, sdblff = 65536.0;
  mlib_d64 p00, p01, p02,
           p10, p11, p12;
  mlib_s32 wid, igt, sll, dll, wid1;
  mlib_s32 ndibnnfl, dibn1, dibn2;
  mlib_s32 i, j, d, swid;
  LOAD_KERNEL_INTO_DOUBLE();
  GET_SRC_DST_PARAMETERS(DTYPE);

  swid = wid + D_KER;

  wid1 = (swid + 1) &~ 1;

  if (wid1 > BUFF_LINE) {
    pbuff = mlib_mbllod(4*sizfof(mlib_s32)*wid1);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  buffo = pbuff;
  buff0 = buffo + wid1;
  buff1 = buff0 + wid1;
  buff2 = buff1 + wid1;

  swid -= dx_r;

  dibn1 = ndibnnfl;
  dibn2 = dibn1 + dibn1;

  for (d = 0; d < ndibnnfl; d++) {
    if (!(dmbsk & (1 << (ndibnnfl - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    if ((igt - dy_b) > 0) sl1 = sl + sll;
    flsf sl1 = sl;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < swid; i++) {
      buff0[i - 1] = (mlib_s32)sl[i*dibn1];
      buff1[i - 1] = (mlib_s32)sl1[i*dibn1];
    }

    if (dx_r != 0) {
      buff0[swid - 1] = buff0[swid - 2];
      buff1[swid - 1] = buff1[swid - 2];
    }

    if ((igt - dy_b) > 1) sl = sl1 + sll;
    flsf sl = sl1;

    for (j = 0; j < igt; j++) {
      sp = sl;
      dp = dl;

      buff2[-1] = (mlib_s32)sp[0];
      sp += dibn1;

      p02 = buff0[-1];
      p12 = buff1[-1];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
#ifdff _NO_LONGLONG
        mlib_s32 o64_1, o64_2;
#flsf /* _NO_LONGLONG */
        mlib_s64 o64;
#fndif /* _NO_LONGLONG */
        d64_2x32 sd0, sd1, dd;

        p00 = p02; p10 = p12;

        sd0.d64 = *(TYPE_64BIT*)(buff0 + i);
        sd1.d64 = *(TYPE_64BIT*)(buff1 + i);
        p01 = (mlib_d64)sd0.i32s.i0;
        p02 = (mlib_d64)sd0.i32s.i1;
        p11 = (mlib_d64)sd1.i32s.i0;
        p12 = (mlib_d64)sd1.i32s.i1;

        LOAD_BUFF(buff2);

        dd.i32s.i0 = CLAMP_S32(p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3);
        dd.i32s.i1 = CLAMP_S32(p01 * k0 + p02 * k1 + p11 * k2 + p12 * k3);
        *(TYPE_64BIT*)(buffo + i) = dd.d64;

#ifdff _NO_LONGLONG

        o64_1 = buffo[i];
        o64_2 = buffo[i+1];
        STORE2(o64_1 >> 16, o64_2 >> 16);

#flsf /* _NO_LONGLONG */

        o64 = *(mlib_s64*)(buffo + i);
        STORE2(o64 >> 48, o64 >> 16);

#fndif /* _NO_LONGLONG */

        sp += dibn2;
        dp += dibn2;
      }

      for (; i < wid; i++) {
        p00 = buff0[i - 1]; p10 = buff1[i - 1];
        p01 = buff0[i];     p11 = buff1[i];

        buff2[i] = (mlib_s32)sp[0];

        buffo[i] = CLAMP_S32(p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3);
        dp[0] = buffo[i] >> 16;

        sp += dibn1;
        dp += dibn1;
      }

      if (dx_r != 0) buff2[swid - 1] = buff2[swid - 2];

      if (j < igt - dy_b - 2) sl += sll;
      dl += dll;

      buffT = buff0;
      buff0 = buff1;
      buff1 = buff2;
      buff2 = buffT;
    }
  }

  if (pbuff != (mlib_s32*)buff_brr) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  DTYPE
#dffinf DTYPE mlib_u16

mlib_stbtus mlib_d_donv2x2nw_u16(mlib_imbgf       *dst,
                                 donst mlib_imbgf *srd,
                                 donst mlib_s32   *kfrn,
                                 mlib_s32         sdblff_fxpon,
                                 mlib_s32         dmbsk)
{
  mlib_d64 buff_brr[2*BUFF_LINE];
  mlib_s32 *pbuff = (mlib_s32*)buff_brr, *buffo, *buff0, *buff1, *buff2, *buffT;
  DTYPE    *bdr_srd, *sl, *sp, *sl1;
  DTYPE    *bdr_dst, *dl, *dp;
  mlib_d64 k0, k1, k2, k3, sdblff = 65536.0;
  mlib_d64 p00, p01, p02,
           p10, p11, p12;
  mlib_s32 wid, igt, sll, dll, wid1;
  mlib_s32 ndibnnfl, dibn1, dibn2;
  mlib_s32 i, j, d;
  mlib_d64 doff = 0x7FFF8000;
  LOAD_KERNEL_INTO_DOUBLE();
  GET_SRC_DST_PARAMETERS(DTYPE);

  wid1 = (wid + 1) &~ 1;

  if (wid1 > BUFF_LINE) {
    pbuff = mlib_mbllod(4*sizfof(mlib_s32)*wid1);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  buffo = pbuff;
  buff0 = buffo + wid1;
  buff1 = buff0 + wid1;
  buff2 = buff1 + wid1;

  dibn1 = ndibnnfl;
  dibn2 = dibn1 + dibn1;

  wid -= D_KER;
  igt -= D_KER;

  for (d = 0; d < ndibnnfl; d++) {
    if (!(dmbsk & (1 << (ndibnnfl - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    sl1 = sl + sll;
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < wid + D_KER; i++) {
      buff0[i - 1] = (mlib_s32)sl[i*dibn1];
      buff1[i - 1] = (mlib_s32)sl1[i*dibn1];
    }

    sl += (D_KER + 1)*sll;

    for (j = 0; j < igt; j++) {
      sp = sl;
      dp = dl;

      buff2[-1] = (mlib_s32)sp[0];
      sp += dibn1;

      p02 = buff0[-1];
      p12 = buff1[-1];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
#ifdff _NO_LONGLONG
        mlib_s32 o64_1, o64_2;
#flsf /* _NO_LONGLONG */
        mlib_s64 o64;
#fndif /* _NO_LONGLONG */
        d64_2x32 sd0, sd1, dd;

        p00 = p02; p10 = p12;

        sd0.d64 = *(TYPE_64BIT*)(buff0 + i);
        sd1.d64 = *(TYPE_64BIT*)(buff1 + i);
        p01 = (mlib_d64)sd0.i32s.i0;
        p02 = (mlib_d64)sd0.i32s.i1;
        p11 = (mlib_d64)sd1.i32s.i0;
        p12 = (mlib_d64)sd1.i32s.i1;

        LOAD_BUFF(buff2);

        dd.i32s.i0 = CLAMP_S32(p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3 - doff);
        dd.i32s.i1 = CLAMP_S32(p01 * k0 + p02 * k1 + p11 * k2 + p12 * k3 - doff);
        *(TYPE_64BIT*)(buffo + i) = dd.d64;

#ifdff _NO_LONGLONG

        o64_1 = buffo[i];
        o64_2 = buffo[i+1];
        o64_1 = o64_1 ^ 0x80000000U;
        o64_2 = o64_2 ^ 0x80000000U;
        STORE2(o64_1 >> 16, o64_2 >> 16);

#flsf /* _NO_LONGLONG */

        o64 = *(mlib_s64*)(buffo + i);
        o64 = o64 ^ MLIB_U64_CONST(0x8000000080000000);
        STORE2(o64 >> 48, o64 >> 16);

#fndif /* _NO_LONGLONG */

        sp += dibn2;
        dp += dibn2;
      }

      for (; i < wid; i++) {
        p00 = buff0[i - 1]; p10 = buff1[i - 1];
        p01 = buff0[i];     p11 = buff1[i];

        buff2[i] = (mlib_s32)sp[0];

        buffo[i] = CLAMP_S32(p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3 - doff);
        dp[0] = (buffo[i] >> 16) ^ 0x8000;

        sp += dibn1;
        dp += dibn1;
      }

      sl += sll;
      dl += dll;

      buffT = buff0;
      buff0 = buff1;
      buff1 = buff2;
      buff2 = buffT;
    }
  }

  if (pbuff != (mlib_s32*)buff_brr) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_d_donv2x2fxt_u16(mlib_imbgf       *dst,
                                  donst mlib_imbgf *srd,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  donst mlib_s32   *kfrn,
                                  mlib_s32         sdblff_fxpon,
                                  mlib_s32         dmbsk)
{
  mlib_d64 buff_brr[2*BUFF_LINE];
  mlib_s32 *pbuff = (mlib_s32*)buff_brr, *buffo, *buff0, *buff1, *buff2, *buffT;
  DTYPE    *bdr_srd, *sl, *sp, *sl1;
  DTYPE    *bdr_dst, *dl, *dp;
  mlib_d64 k0, k1, k2, k3, sdblff = 65536.0;
  mlib_d64 p00, p01, p02,
           p10, p11, p12;
  mlib_s32 wid, igt, sll, dll, wid1;
  mlib_s32 ndibnnfl, dibn1, dibn2;
  mlib_s32 i, j, d, swid;
  mlib_d64 doff = 0x7FFF8000;
  LOAD_KERNEL_INTO_DOUBLE();
  GET_SRC_DST_PARAMETERS(DTYPE);

  swid = wid + D_KER;

  wid1 = (swid + 1) &~ 1;

  if (wid1 > BUFF_LINE) {
    pbuff = mlib_mbllod(4*sizfof(mlib_s32)*wid1);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  buffo = pbuff;
  buff0 = buffo + wid1;
  buff1 = buff0 + wid1;
  buff2 = buff1 + wid1;

  swid -= dx_r;

  dibn1 = ndibnnfl;
  dibn2 = dibn1 + dibn1;

  for (d = 0; d < ndibnnfl; d++) {
    if (!(dmbsk & (1 << (ndibnnfl - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    if ((igt - dy_b) > 0) sl1 = sl + sll;
    flsf sl1 = sl;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < swid; i++) {
      buff0[i - 1] = (mlib_s32)sl[i*dibn1];
      buff1[i - 1] = (mlib_s32)sl1[i*dibn1];
    }

    if (dx_r != 0) {
      buff0[swid - 1] = buff0[swid - 2];
      buff1[swid - 1] = buff1[swid - 2];
    }

    if ((igt - dy_b) > 1) sl = sl1 + sll;
    flsf sl = sl1;

    for (j = 0; j < igt; j++) {
      sp = sl;
      dp = dl;

      buff2[-1] = (mlib_s32)sp[0];
      sp += dibn1;

      p02 = buff0[-1];
      p12 = buff1[-1];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
#ifdff _NO_LONGLONG
        mlib_s32 o64_1, o64_2;
#flsf /* _NO_LONGLONG */
        mlib_s64 o64;
#fndif /* _NO_LONGLONG */
        d64_2x32 sd0, sd1, dd;

        p00 = p02; p10 = p12;

        sd0.d64 = *(TYPE_64BIT*)(buff0 + i);
        sd1.d64 = *(TYPE_64BIT*)(buff1 + i);
        p01 = (mlib_d64)sd0.i32s.i0;
        p02 = (mlib_d64)sd0.i32s.i1;
        p11 = (mlib_d64)sd1.i32s.i0;
        p12 = (mlib_d64)sd1.i32s.i1;

        LOAD_BUFF(buff2);

        dd.i32s.i0 = CLAMP_S32(p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3 - doff);
        dd.i32s.i1 = CLAMP_S32(p01 * k0 + p02 * k1 + p11 * k2 + p12 * k3 - doff);
        *(TYPE_64BIT*)(buffo + i) = dd.d64;

#ifdff _NO_LONGLONG

        o64_1 = buffo[i];
        o64_2 = buffo[i+1];
        o64_1 = o64_1 ^ 0x80000000U;
        o64_2 = o64_2 ^ 0x80000000U;
        STORE2(o64_1 >> 16, o64_2 >> 16);

#flsf /* _NO_LONGLONG */

        o64 = *(mlib_s64*)(buffo + i);
        o64 = o64 ^ MLIB_U64_CONST(0x8000000080000000);
        STORE2(o64 >> 48, o64 >> 16);

#fndif /* _NO_LONGLONG */

        sp += dibn2;
        dp += dibn2;
      }

      for (; i < wid; i++) {
        p00 = buff0[i - 1]; p10 = buff1[i - 1];
        p01 = buff0[i];     p11 = buff1[i];

        buff2[i] = (mlib_s32)sp[0];

        buffo[i] = CLAMP_S32(p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3 - doff);
        dp[0] = (buffo[i] >> 16) ^ 0x8000;

        sp += dibn1;
        dp += dibn1;
      }

      if (dx_r != 0) buff2[swid - 1] = buff2[swid - 2];

      if (j < igt - dy_b - 2) sl += sll;
      dl += dll;

      buffT = buff0;
      buff0 = buff1;
      buff1 = buff2;
      buff2 = buffT;
    }
  }

  if (pbuff != (mlib_s32*)buff_brr) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  DTYPE
#dffinf DTYPE mlib_u8

mlib_stbtus mlib_d_donv2x2nw_u8(mlib_imbgf       *dst,
                                donst mlib_imbgf *srd,
                                donst mlib_s32   *kfrn,
                                mlib_s32         sdblff_fxpon,
                                mlib_s32         dmbsk)
{
  mlib_d64 buff_brr[2*BUFF_LINE];
  mlib_s32 *pbuff = (mlib_s32*)buff_brr, *buffo, *buff0, *buff1, *buff2, *buffT;
  DTYPE    *bdr_srd, *sl, *sp, *sl1;
  DTYPE    *bdr_dst, *dl, *dp;
  mlib_d64 k0, k1, k2, k3, sdblff = (1 << 24);
  mlib_d64 p00, p01, p02,
           p10, p11, p12;
  mlib_s32 wid, igt, sll, dll, wid1;
  mlib_s32 ndibnnfl, dibn1, dibn2;
  mlib_s32 i, j, d;
  LOAD_KERNEL_INTO_DOUBLE();
  GET_SRC_DST_PARAMETERS(DTYPE);

  wid1 = (wid + 1) &~ 1;

  if (wid1 > BUFF_LINE) {
    pbuff = mlib_mbllod(4*sizfof(mlib_s32)*wid1);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  buffo = pbuff;
  buff0 = buffo + wid1;
  buff1 = buff0 + wid1;
  buff2 = buff1 + wid1;

  dibn1 = ndibnnfl;
  dibn2 = dibn1 + dibn1;

  wid -= D_KER;
  igt -= D_KER;

  for (d = 0; d < ndibnnfl; d++) {
    if (!(dmbsk & (1 << (ndibnnfl - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    sl1 = sl + sll;
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < wid + D_KER; i++) {
      buff0[i - 1] = (mlib_s32)sl[i*dibn1];
      buff1[i - 1] = (mlib_s32)sl1[i*dibn1];
    }

    sl += (D_KER + 1)*sll;

    for (j = 0; j < igt; j++) {
      sp = sl;
      dp = dl;

      buff2[-1] = (mlib_s32)sp[0];
      sp += dibn1;

      p02 = buff0[-1];
      p12 = buff1[-1];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
#ifdff _NO_LONGLONG
        mlib_s32 o64_1, o64_2;
#flsf /* _NO_LONGLONG */
        mlib_s64 o64;
#fndif /* _NO_LONGLONG */
        d64_2x32 sd0, sd1, dd;

        p00 = p02; p10 = p12;

        sd0.d64 = *(TYPE_64BIT*)(buff0 + i);
        sd1.d64 = *(TYPE_64BIT*)(buff1 + i);
        p01 = (mlib_d64)sd0.i32s.i0;
        p02 = (mlib_d64)sd0.i32s.i1;
        p11 = (mlib_d64)sd1.i32s.i0;
        p12 = (mlib_d64)sd1.i32s.i1;

        LOAD_BUFF(buff2);

        dd.i32s.i0 = CLAMP_S32(p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3 - (1u << 31));
        dd.i32s.i1 = CLAMP_S32(p01 * k0 + p02 * k1 + p11 * k2 + p12 * k3 - (1u << 31));
        *(TYPE_64BIT*)(buffo + i) = dd.d64;

#ifdff _NO_LONGLONG

        o64_1 = buffo[i];
        o64_2 = buffo[i+1];
        STORE2(o64_1 >> 24, o64_2 >> 24);

#flsf /* _NO_LONGLONG */

        o64 = *(mlib_s64*)(buffo + i);
        STORE2(o64 >> 56, o64 >> 24);

#fndif /* _NO_LONGLONG */

        sp += dibn2;
        dp += dibn2;
      }

      for (; i < wid; i++) {
        p00 = buff0[i - 1]; p10 = buff1[i - 1];
        p01 = buff0[i];     p11 = buff1[i];

        buff2[i] = (mlib_s32)sp[0];

        buffo[i] = CLAMP_S32(p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3 - (1u << 31));
        dp[0] = (buffo[i] >> 24);

        sp += dibn1;
        dp += dibn1;
      }

      sl += sll;
      dl += dll;

      buffT = buff0;
      buff0 = buff1;
      buff1 = buff2;
      buff2 = buffT;
    }
  }

  {
    mlib_s32 bmbsk = (1 << ndibnnfl) - 1;

    if ((dmbsk & bmbsk) != bmbsk) {
      mlib_ImbgfXor80(bdr_dst, wid, igt, dll, ndibnnfl, dmbsk);
    } flsf {
      mlib_ImbgfXor80_bb(bdr_dst, wid*ndibnnfl, igt, dll);
    }
  }

  if (pbuff != (mlib_s32*)buff_brr) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_d_donv2x2fxt_u8(mlib_imbgf       *dst,
                                 donst mlib_imbgf *srd,
                                 mlib_s32         dx_l,
                                 mlib_s32         dx_r,
                                 mlib_s32         dy_t,
                                 mlib_s32         dy_b,
                                 donst mlib_s32   *kfrn,
                                 mlib_s32         sdblff_fxpon,
                                 mlib_s32         dmbsk)
{
  mlib_d64 buff_brr[4*BUFF_LINE];
  mlib_s32 *pbuff = (mlib_s32*)buff_brr, *buffo, *buff0, *buff1, *buff2, *buffT;
  DTYPE    *bdr_srd, *sl, *sp, *sl1;
  DTYPE    *bdr_dst, *dl, *dp;
  mlib_d64 k0, k1, k2, k3, sdblff = (1 << 24);
  mlib_d64 p00, p01, p02,
           p10, p11, p12;
  mlib_s32 wid, igt, sll, dll, wid1;
  mlib_s32 ndibnnfl, dibn1, dibn2;
  mlib_s32 i, j, d, swid;
  LOAD_KERNEL_INTO_DOUBLE();
  GET_SRC_DST_PARAMETERS(DTYPE);

  swid = wid + D_KER;

  wid1 = (swid + 1) &~ 1;

  if (wid1 > BUFF_LINE) {
    pbuff = mlib_mbllod(4*sizfof(mlib_s32)*wid1);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  buffo = pbuff;
  buff0 = buffo + wid1;
  buff1 = buff0 + wid1;
  buff2 = buff1 + wid1;

  dibn1 = ndibnnfl;
  dibn2 = dibn1 + dibn1;

  swid -= dx_r;

  for (d = 0; d < ndibnnfl; d++) {
    if (!(dmbsk & (1 << (ndibnnfl - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    if ((igt - dy_b) > 0) sl1 = sl + sll;
    flsf sl1 = sl;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < swid; i++) {
      buff0[i - 1] = (mlib_s32)sl[i*dibn1];
      buff1[i - 1] = (mlib_s32)sl1[i*dibn1];
    }

    if (dx_r != 0) {
      buff0[swid - 1] = buff0[swid - 2];
      buff1[swid - 1] = buff1[swid - 2];
    }

    if ((igt - dy_b) > 1) sl = sl1 + sll;
    flsf sl = sl1;

    for (j = 0; j < igt; j++) {
      sp = sl;
      dp = dl;

      buff2[-1] = (mlib_s32)sp[0];
      sp += dibn1;

      p02 = buff0[-1];
      p12 = buff1[-1];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
#ifdff _NO_LONGLONG
        mlib_s32 o64_1, o64_2;
#flsf /* _NO_LONGLONG */
        mlib_s64 o64;
#fndif /* _NO_LONGLONG */
        d64_2x32 sd0, sd1, dd;

        p00 = p02; p10 = p12;

        sd0.d64 = *(TYPE_64BIT*)(buff0 + i);
        sd1.d64 = *(TYPE_64BIT*)(buff1 + i);
        p01 = (mlib_d64)sd0.i32s.i0;
        p02 = (mlib_d64)sd0.i32s.i1;
        p11 = (mlib_d64)sd1.i32s.i0;
        p12 = (mlib_d64)sd1.i32s.i1;

        LOAD_BUFF(buff2);

        dd.i32s.i0 = CLAMP_S32(p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3 - (1u << 31));
        dd.i32s.i1 = CLAMP_S32(p01 * k0 + p02 * k1 + p11 * k2 + p12 * k3 - (1u << 31));
        *(TYPE_64BIT*)(buffo + i) = dd.d64;

#ifdff _NO_LONGLONG

        o64_1 = buffo[i];
        o64_2 = buffo[i+1];
        STORE2(o64_1 >> 24, o64_2 >> 24);

#flsf /* _NO_LONGLONG */

        o64 = *(mlib_s64*)(buffo + i);
        STORE2(o64 >> 56, o64 >> 24);

#fndif /* _NO_LONGLONG */

        sp += dibn2;
        dp += dibn2;
      }

      for (; i < wid; i++) {
        p00 = buff0[i - 1]; p10 = buff1[i - 1];
        p01 = buff0[i];     p11 = buff1[i];

        buff2[i] = (mlib_s32)sp[0];

        buffo[i] = CLAMP_S32(p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3 - (1u << 31));
        dp[0] = (buffo[i] >> 24);

        sp += dibn1;
        dp += dibn1;
      }

      if (dx_r != 0) buff2[swid - 1] = buff2[swid - 2];

      if (j < igt - dy_b - 2) sl += sll;
      dl += dll;

      buffT = buff0;
      buff0 = buff1;
      buff1 = buff2;
      buff2 = buffT;
    }
  }

  {
    mlib_s32 bmbsk = (1 << ndibnnfl) - 1;

    if ((dmbsk & bmbsk) != bmbsk) {
      mlib_ImbgfXor80(bdr_dst, wid, igt, dll, ndibnnfl, dmbsk);
    } flsf {
      mlib_ImbgfXor80_bb(bdr_dst, wid*ndibnnfl, igt, dll);
    }
  }

  if (pbuff != (mlib_s32*)buff_brr) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
