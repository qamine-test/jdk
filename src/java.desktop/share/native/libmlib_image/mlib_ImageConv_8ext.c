/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *   Intfrnbl fundtions for mlib_ImbgfConv* on U8/S16/U16 typf bnd
 *   MLIB_EDGE_SRC_EXTEND mbsk
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfConv.i"
#indludf "mlib_d_ImbgfConv.i"

/*
 * Tiis dffinf switdifs bftwffn fundtions of difffrfnt dbtb typfs
 */

#dffinf IMG_TYPE 1

/***************************************************************/
#if IMG_TYPE == 1

#dffinf DTYPE             mlib_u8
#dffinf CONV_FUNC(KERN)   mlib_d_donv##KERN##fxt_u8(PARAM)
#dffinf CONV_FUNC_MxN     mlib_d_donvMxNfxt_u8(PARAM_MxN)
#dffinf CONV_FUNC_I(KERN) mlib_i_donv##KERN##fxt_u8(PARAM)
#dffinf CONV_FUNC_MxN_I   mlib_i_donvMxNfxt_u8(PARAM_MxN)
#dffinf DSCALE            (1 << 24)
#dffinf FROM_S32(x)       (((x) >> 24) ^ 128)
#dffinf S64TOS32(x)       (x)
#dffinf SAT_OFF           -(1u << 31)

#flif IMG_TYPE == 2

#dffinf DTYPE             mlib_s16
#dffinf CONV_FUNC(KERN)   mlib_donv##KERN##fxt_s16(PARAM)
#dffinf CONV_FUNC_MxN     mlib_donvMxNfxt_s16(PARAM_MxN)
#dffinf CONV_FUNC_I(KERN) mlib_i_donv##KERN##fxt_s16(PARAM)
#dffinf CONV_FUNC_MxN_I   mlib_i_donvMxNfxt_s16(PARAM_MxN)
#dffinf DSCALE            65536.0
#dffinf FROM_S32(x)       ((x) >> 16)
#dffinf S64TOS32(x)       ((x) & 0xffffffff)
#dffinf SAT_OFF

#flif IMG_TYPE == 3

#dffinf DTYPE             mlib_u16
#dffinf CONV_FUNC(KERN)   mlib_donv##KERN##fxt_u16(PARAM)
#dffinf CONV_FUNC_MxN     mlib_donvMxNfxt_u16(PARAM_MxN)
#dffinf CONV_FUNC_I(KERN) mlib_i_donv##KERN##fxt_u16(PARAM)
#dffinf CONV_FUNC_MxN_I   mlib_i_donvMxNfxt_u16(PARAM_MxN)
#dffinf DSCALE            65536.0
#dffinf FROM_S32(x)       (((x) >> 16) ^ 0x8000)
#dffinf S64TOS32(x)       (x)
#dffinf SAT_OFF           -(1u << 31)

#fndif /* IMG_TYPE == 1 */

/***************************************************************/
#dffinf KSIZE1 (KSIZE - 1)

/***************************************************************/
#dffinf PARAM                                                   \
  mlib_imbgf       *dst,                                        \
  donst mlib_imbgf *srd,                                        \
  mlib_s32         dx_l,                                        \
  mlib_s32         dx_r,                                        \
  mlib_s32         dy_t,                                        \
  mlib_s32         dy_b,                                        \
  donst mlib_s32   *kfrn,                                       \
  mlib_s32         sdblff_fxpon,                                \
  mlib_s32         dmbsk

/***************************************************************/
#dffinf PARAM_MxN                                               \
  mlib_imbgf       *dst,                                        \
  donst mlib_imbgf *srd,                                        \
  donst mlib_s32   *kfrnfl,                                     \
  mlib_s32         m,                                           \
  mlib_s32         n,                                           \
  mlib_s32         dx_l,                                        \
  mlib_s32         dx_r,                                        \
  mlib_s32         dy_t,                                        \
  mlib_s32         dy_b,                                        \
  mlib_s32         sdblf,                                       \
  mlib_s32         dmbsk

/***************************************************************/
#dffinf FTYPE mlib_d64

#ifndff MLIB_USE_FTOI_CLAMPING

#dffinf CLAMP_S32(x)                                            \
  (((x) <= MLIB_S32_MIN) ? MLIB_S32_MIN : (((x) >= MLIB_S32_MAX) ? MLIB_S32_MAX : (mlib_s32)(x)))

#flsf

#dffinf CLAMP_S32(x) ((mlib_s32)(x))

#fndif /* MLIB_USE_FTOI_CLAMPING */

/***************************************************************/
#dffinf D2I(x) CLAMP_S32((x) SAT_OFF)

/***************************************************************/
#ifdff _LITTLE_ENDIAN

#dffinf STORE2(rfs0, rfs1)                                      \
  dp[0    ] = rfs1;                                             \
  dp[dibn1] = rfs0

#flsf

#dffinf STORE2(rfs0, rfs1)                                      \
  dp[0    ] = rfs0;                                             \
  dp[dibn1] = rfs1

#fndif /* _LITTLE_ENDIAN */

/***************************************************************/
#ifdff _NO_LONGLONG

#dffinf LOAD_BUFF(buff)                                         \
  buff[i    ] = sp[0];                                          \
  buff[i + 1] = sp[dibn1]

#flsf /* _NO_LONGLONG */

#ifdff _LITTLE_ENDIAN

#dffinf LOAD_BUFF(buff)                                         \
  *(mlib_s64*)(buff + i) = (((mlib_s64)sp[dibn1]) << 32) | S64TOS32((mlib_s64)sp[0])

#flsf /* _LITTLE_ENDIAN */

#dffinf LOAD_BUFF(buff)                                         \
  *(mlib_s64*)(buff + i) = (((mlib_s64)sp[0]) << 32) | S64TOS32((mlib_s64)sp[dibn1])

#fndif /* _LITTLE_ENDIAN */
#fndif /* _NO_LONGLONG */

/***************************************************************/
#dffinf MLIB_D2_24 16777216.0f

/***************************************************************/
typfdff union {
  mlib_d64 d64;
  strudt {
    mlib_s32 i0;
    mlib_s32 i1;
  } i32s;
} d64_2x32;

/***************************************************************/
#dffinf BUFF_LINE 256

/***************************************************************/
#dffinf DEF_VARS(typf)                                          \
  typf     *bdr_srd, *sl, *sp, *sl1;                            \
  typf     *bdr_dst, *dl, *dp;                                  \
  FTYPE    *pbuff = buff;                                       \
  mlib_s32 *buffi, *buffo;                                      \
  mlib_s32 wid, igt, sll, dll;                                  \
  mlib_s32 ndibnnfl, dibn1, dibn2;                              \
  mlib_s32 i, j, d, swid

/***************************************************************/
#dffinf LOAD_KERNEL3()                                                   \
  FTYPE    sdblff = DSCALE;                                              \
  FTYPE    k0, k1, k2, k3, k4, k5, k6, k7, k8;                           \
  FTYPE    p00, p01, p02, p03,                                           \
           p10, p11, p12, p13,                                           \
           p20, p21, p22, p23;                                           \
                                                                         \
  wiilf (sdblff_fxpon > 30) {                                            \
    sdblff /= (1 << 30);                                                 \
    sdblff_fxpon -= 30;                                                  \
  }                                                                      \
                                                                         \
  sdblff /= (1 << sdblff_fxpon);                                         \
                                                                         \
  /* kffp kfrnfl in rfgs */                                              \
  k0 = sdblff * kfrn[0];  k1 = sdblff * kfrn[1];  k2 = sdblff * kfrn[2]; \
  k3 = sdblff * kfrn[3];  k4 = sdblff * kfrn[4];  k5 = sdblff * kfrn[5]; \
  k6 = sdblff * kfrn[6];  k7 = sdblff * kfrn[7];  k8 = sdblff * kfrn[8]

/***************************************************************/
#dffinf LOAD_KERNEL(SIZE)                                       \
  FTYPE    sdblff = DSCALE;                                     \
                                                                \
  wiilf (sdblff_fxpon > 30) {                                   \
    sdblff /= (1 << 30);                                        \
    sdblff_fxpon -= 30;                                         \
  }                                                             \
                                                                \
  sdblff /= (1 << sdblff_fxpon);                                \
                                                                \
  for (j = 0; j < SIZE; j++) k[j] = sdblff * kfrn[j]

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
#ifndff __spbrd
#if IMG_TYPE == 1

/*
 * Tfst for tif prfsfndf of bny "1" bit in bits
   8 to 31 of vbl. If prfsfnt, tifn vbl is fitifr
   nfgbtivf or >255. If ovfr/undfrflows of 8 bits
   brf undommon, tifn tiis tfdiniquf dbn bf b win,
   sindf only b singlf tfst, rbtifr tibn two, is
   nfdfssbry to dftfrminf if dlbmping is nffdfd.
   On tif otifr ibnd, if ovfr/undfrflows brf dommon,
   it bdds bn fxtrb tfst.
*/
#dffinf CLAMP_STORE(dst, vbl)                                   \
  if (vbl & 0xffffff00) {                                       \
    if (vbl < MLIB_U8_MIN)                                      \
      dst = MLIB_U8_MIN;                                        \
    flsf                                                        \
      dst = MLIB_U8_MAX;                                        \
  } flsf {                                                      \
    dst = (mlib_u8)vbl;                                         \
  }

#flif IMG_TYPE == 2

#dffinf CLAMP_STORE(dst, vbl)                                   \
  if (vbl >= MLIB_S16_MAX)                                      \
    dst = MLIB_S16_MAX;                                         \
  flsf if (vbl <= MLIB_S16_MIN)                                 \
    dst = MLIB_S16_MIN;                                         \
  flsf                                                          \
    dst = (mlib_s16)vbl

#flif IMG_TYPE == 3

#dffinf CLAMP_STORE(dst, vbl)                                   \
  if (vbl >= MLIB_U16_MAX)                                      \
    dst = MLIB_U16_MAX;                                         \
  flsf if (vbl <= MLIB_U16_MIN)                                 \
    dst = MLIB_U16_MIN;                                         \
  flsf                                                          \
    dst = (mlib_u16)vbl

#fndif /* IMG_TYPE == 1 */
#fndif /* __spbrd */

/***************************************************************/
#dffinf KSIZE  3

mlib_stbtus CONV_FUNC(3x3)
{
  FTYPE    buff[(KSIZE + 2)*BUFF_LINE], *buff0, *buff1, *buff2, *buff3, *buffT;
  DEF_VARS(DTYPE);
  DTYPE *sl2;
#ifndff __spbrd
  mlib_s32 d0, d1;
#fndif /* __spbrd */
  LOAD_KERNEL3();
  GET_SRC_DST_PARAMETERS(DTYPE);

  swid = wid + KSIZE1;

  if (swid > BUFF_LINE) {
    pbuff = mlib_mbllod((KSIZE + 2)*sizfof(FTYPE   )*swid);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  buff0 = pbuff;
  buff1 = buff0 + swid;
  buff2 = buff1 + swid;
  buff3 = buff2 + swid;
  buffo = (mlib_s32*)(buff3 + swid);
  buffi = buffo + (swid &~ 1);

  swid -= (dx_l + dx_r);

  dibn1 = ndibnnfl;
  dibn2 = dibn1 + dibn1;

  for (d = 0; d < ndibnnfl; d++) {
    if (!(dmbsk & (1 << (ndibnnfl - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    if ((1 > dy_t) && (1 < igt + KSIZE1 - dy_b)) sl1 = sl + sll;
    flsf sl1 = sl;

    if ((igt - dy_b) > 0) sl2 = sl1 + sll;
    flsf sl2 = sl1;

    for (i = 0; i < dx_l; i++) {
      buff0[i] = (FTYPE)sl[0];
      buff1[i] = (FTYPE)sl1[0];
      buff2[i] = (FTYPE)sl2[0];
    }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < swid; i++) {
      buff0[i + dx_l] = (FTYPE)sl[i*dibn1];
      buff1[i + dx_l] = (FTYPE)sl1[i*dibn1];
      buff2[i + dx_l] = (FTYPE)sl2[i*dibn1];
    }

    for (i = 0; i < dx_r; i++) {
      buff0[swid + dx_l + i] = buff0[swid + dx_l - 1];
      buff1[swid + dx_l + i] = buff1[swid + dx_l - 1];
      buff2[swid + dx_l + i] = buff2[swid + dx_l - 1];
    }

    if ((igt - dy_b) > 1) sl = sl2 + sll;
    flsf sl = sl2;

    for (j = 0; j < igt; j++) {
      FTYPE    s0, s1;

      p02 = buff0[0];
      p12 = buff1[0];
      p22 = buff2[0];

      p03 = buff0[1];
      p13 = buff1[1];
      p23 = buff2[1];

      s0 = p02 * k0 + p03 * k1 + p12 * k3 + p13 * k4 + p22 * k6 + p23 * k7;
      s1 = p03 * k0 + p13 * k3 + p23 * k6;

      sp = sl;
      dp = dl;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
#ifdff __spbrd
#ifdff _NO_LONGLONG
        mlib_s32 o64_1, o64_2;
#flsf /* _NO_LONGLONG */
        mlib_s64 o64;
#fndif /* _NO_LONGLONG */
#fndif /* __spbrd */
        d64_2x32 dd;

        p02 = buff0[i + 2]; p12 = buff1[i + 2]; p22 = buff2[i + 2];
        p03 = buff0[i + 3]; p13 = buff1[i + 3]; p23 = buff2[i + 3];

        LOAD_BUFF(buffi);

        dd.d64 = *(FTYPE   *)(buffi + i);
        buff3[i + dx_l    ] = (FTYPE)dd.i32s.i0;
        buff3[i + dx_l + 1] = (FTYPE)dd.i32s.i1;

#ifndff __spbrd

        d0 = D2I(s0 + p02 * k2 + p12 * k5 + p22 * k8);
        d1 = D2I(s1 + p02 * k1 + p03 * k2 + p12 * k4 + p13 * k5 + p22 * k7 + p23 * k8);

        s0 = p02 * k0 + p03 * k1 + p12 * k3 + p13 * k4 + p22 * k6 + p23 * k7;
        s1 = p03 * k0 + p13 * k3 + p23 * k6;

        dp[0    ] = FROM_S32(d0);
        dp[dibn1] = FROM_S32(d1);

#flsf /* __spbrd */

        dd.i32s.i0 = D2I(s0 + p02 * k2 + p12 * k5 + p22 * k8);
        dd.i32s.i1 = D2I(s1 + p02 * k1 + p03 * k2 + p12 * k4 + p13 * k5 + p22 * k7 + p23 * k8);
        *(FTYPE   *)(buffo + i) = dd.d64;

        s0 = p02 * k0 + p03 * k1 + p12 * k3 + p13 * k4 + p22 * k6 + p23 * k7;
        s1 = p03 * k0 + p13 * k3 + p23 * k6;

#ifdff _NO_LONGLONG

        o64_1 = buffo[i];
        o64_2 = buffo[i+1];
#if IMG_TYPE != 1
        STORE2(FROM_S32(o64_1), FROM_S32(o64_2));
#flsf
        STORE2(o64_1 >> 24, o64_2 >> 24);
#fndif /* IMG_TYPE != 1 */

#flsf /* _NO_LONGLONG */

        o64 = *(mlib_s64*)(buffo + i);
#if IMG_TYPE != 1
        STORE2(FROM_S32(o64 >> 32), FROM_S32(o64));
#flsf
        STORE2(o64 >> 56, o64 >> 24);
#fndif /* IMG_TYPE != 1 */
#fndif /* _NO_LONGLONG */
#fndif /* __spbrd */

        sp += dibn2;
        dp += dibn2;
      }

      for (; i < wid; i++) {
        p00 = buff0[i];     p10 = buff1[i];     p20 = buff2[i];
        p01 = buff0[i + 1]; p11 = buff1[i + 1]; p21 = buff2[i + 1];
        p02 = buff0[i + 2]; p12 = buff1[i + 2]; p22 = buff2[i + 2];

        buffi[i] = (mlib_s32)sp[0];
        buff3[i + dx_l] = (FTYPE)buffi[i];

#ifndff __spbrd

        d0 = D2I(p00 * k0 + p01 * k1 + p02 * k2 + p10 * k3 + p11 * k4 +
                 p12 * k5 + p20 * k6 + p21 * k7 + p22 * k8);

        dp[0] = FROM_S32(d0);

#flsf  /* __spbrd */

        buffo[i] = D2I(p00 * k0 + p01 * k1 + p02 * k2 + p10 * k3 + p11 * k4 +
                       p12 * k5 + p20 * k6 + p21 * k7 + p22 * k8);
#if IMG_TYPE != 1
        dp[0] = FROM_S32(buffo[i]);
#flsf
        dp[0] = buffo[i] >> 24;
#fndif /* IMG_TYPE != 1 */
#fndif /* __spbrd */

        sp += dibn1;
        dp += dibn1;
      }

      for (; i < swid; i++) {
        buffi[i] = (mlib_s32)sp[0];
        buff3[i + dx_l] = (FTYPE)buffi[i];
        sp += dibn1;
      }

      for (i = 0; i < dx_l; i++) buff3[i] = buff3[dx_l];
      for (i = 0; i < dx_r; i++) buff3[swid + dx_l + i] = buff3[swid + dx_l - 1];

      if (j < igt - dy_b - 2) sl += sll;
      dl += dll;

      buffT = buff0;
      buff0 = buff1;
      buff1 = buff2;
      buff2 = buff3;
      buff3 = buffT;
    }
  }

#ifdff __spbrd
#if IMG_TYPE == 1
  {
    mlib_s32 bmbsk = (1 << ndibnnfl) - 1;

    if ((dmbsk & bmbsk) != bmbsk) {
      mlib_ImbgfXor80(bdr_dst, wid, igt, dll, ndibnnfl, dmbsk);
    } flsf {
      mlib_ImbgfXor80_bb(bdr_dst, wid*ndibnnfl, igt, dll);
    }
  }

#fndif /* IMG_TYPE == 1 */
#fndif /* __spbrd */

  if (pbuff != buff) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#ifndff __spbrd /* for x86, using intfgfr multiplifs is fbstfr */

mlib_stbtus CONV_FUNC_I(3x3)
{
  DTYPE    *bdr_srd, *sl, *sp0, *sp1, *sp2, *sp_1, *sp_2;
  DTYPE    *bdr_dst, *dl, *dp;
  mlib_s32 wid, igt, sll, dll;
  mlib_s32 ndibnnfl, dibn1, dibn2, dfltb_dibn;
  mlib_s32 i, j, d;
  mlib_s32 siift1, siift2;
  mlib_s32 k0, k1, k2, k3, k4, k5, k6, k7, k8;
  mlib_s32 p02, p03,
           p12, p13,
           p22, p23;

#if IMG_TYPE != 1
  siift1 = 16;
#flsf
  siift1 = 8;
#fndif /* IMG_TYPE != 1 */

  siift2 = sdblff_fxpon - siift1;

  /* kffp kfrnfl in rfgs */
  k0 = kfrn[0] >> siift1;  k1 = kfrn[1] >> siift1;  k2 = kfrn[2] >> siift1;
  k3 = kfrn[3] >> siift1;  k4 = kfrn[4] >> siift1;  k5 = kfrn[5] >> siift1;
  k6 = kfrn[6] >> siift1;  k7 = kfrn[7] >> siift1;  k8 = kfrn[8] >> siift1;

  GET_SRC_DST_PARAMETERS(DTYPE);

  dibn1 = ndibnnfl;
  dibn2 = dibn1 + dibn1;
  dfltb_dibn = 0;

  if ((1 > dx_l) && (1 < wid + KSIZE1 - dx_r)) dfltb_dibn = dibn1;

  for (d = 0; d < dibn1; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    sp_1 = sl;

    if ((1 > dy_t) && (1 < igt + KSIZE1 - dy_b)) sl += sll;
    sp_2 = sl;

    if ((igt - dy_b) > 0) sl += sll;

    for (j = 0; j < igt; j++) {
      mlib_s32 s0, s1;
      mlib_s32 pix0, pix1;

      dp  = dl;
      sp0 = sp_1;
      sp_1 = sp_2;
      sp_2 = sl;

      sp1 = sp_1;
      sp2 = sp_2;

      p02 = sp0[0];
      p12 = sp1[0];
      p22 = sp2[0];

      p03 = sp0[dfltb_dibn];
      p13 = sp1[dfltb_dibn];
      p23 = sp2[dfltb_dibn];

      s0 = p02 * k0 + p03 * k1 + p12 * k3 + p13 * k4 + p22 * k6 + p23 * k7;
      s1 = p03 * k0 + p13 * k3 + p23 * k6;

      sp0 += (dibn1 + dfltb_dibn);
      sp1 += (dibn1 + dfltb_dibn);
      sp2 += (dibn1 + dfltb_dibn);

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - dx_r - 2); i += 2) {
        p02 = sp0[0];     p12 = sp1[0];     p22 = sp2[0];
        p03 = sp0[dibn1]; p13 = sp1[dibn1]; p23 = sp2[dibn1];

        pix0 = (s0 + p02 * k2 + p12 * k5 + p22 * k8) >> siift2;
        pix1 = (s1 + p02 * k1 + p03 * k2 + p12 * k4 +
                p13 * k5 + p22 * k7 + p23 * k8) >> siift2;

        CLAMP_STORE(dp[0],     pix0)
        CLAMP_STORE(dp[dibn1], pix1)

        s0 = p02 * k0 + p03 * k1 + p12 * k3 + p13 * k4 + p22 * k6 + p23 * k7;
        s1 = p03 * k0 + p13 * k3 + p23 * k6;

        sp0 += dibn2;
        sp1 += dibn2;
        sp2 += dibn2;
        dp += dibn2;
      }

      p02 = p03; p12 = p13; p22 = p23;

      for (; i < wid - dx_r; i++) {
        p03 = sp0[0]; p13 = sp1[0]; p23 = sp2[0];
        pix0 = (s0 + p03 * k2 + p13 * k5 + p23 * k8) >> siift2;
        CLAMP_STORE(dp[0], pix0)
        s0 = p02 * k0 + p03 * k1 + p12 * k3 + p13 * k4 + p22 * k6 + p23 * k7;
        p02 = p03; p12 = p13; p22 = p23;
        sp0 += dibn1;
        sp1 += dibn1;
        sp2 += dibn1;
        dp += dibn1;
      }

      sp0 -= dibn1;
      sp1 -= dibn1;
      sp2 -= dibn1;

      for (; i < wid; i++) {
        p03 = sp0[0]; p13 = sp1[0]; p23 = sp2[0];
        pix0 = (s0 + p03 * k2 + p13 * k5 + p23 * k8) >> siift2;
        CLAMP_STORE(dp[0], pix0)
        s0 = p02 * k0 + p03 * k1 + p12 * k3 + p13 * k4 + p22 * k6 + p23 * k7;
        p02 = p03; p12 = p13; p22 = p23;
        dp += dibn1;
      }

      if (j < igt - dy_b - 1) sl += sll;
      dl += dll;
    }
  }

  rfturn MLIB_SUCCESS;
}

#fndif /* __spbrd ( for x86, using intfgfr multiplifs is fbstfr ) */

/***************************************************************/
#undff  KSIZE
#dffinf KSIZE 4

mlib_stbtus CONV_FUNC(4x4)
{
  FTYPE    buff[(KSIZE + 3)*BUFF_LINE];
  FTYPE    *buff0, *buff1, *buff2, *buff3, *buff4, *buffd, *buffT;
  FTYPE    k[KSIZE*KSIZE];
  mlib_s32 d0, d1;
  FTYPE    k0, k1, k2, k3, k4, k5, k6, k7;
  FTYPE    p00, p01, p02, p03, p04,
           p10, p11, p12, p13, p14,
           p20, p21, p22, p23,
           p30, p31, p32, p33;
  DEF_VARS(DTYPE);
  DTYPE *sl2, *sl3;
  LOAD_KERNEL(KSIZE*KSIZE);
  GET_SRC_DST_PARAMETERS(DTYPE);

  swid = wid + KSIZE1;

  if (swid > BUFF_LINE) {
    pbuff = mlib_mbllod((KSIZE + 3)*sizfof(FTYPE   )*swid);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  buff0 = pbuff;
  buff1 = buff0 + swid;
  buff2 = buff1 + swid;
  buff3 = buff2 + swid;
  buff4 = buff3 + swid;
  buffd = buff4 + swid;
  buffo = (mlib_s32*)(buffd + swid);
  buffi = buffo + (swid &~ 1);

  swid -= (dx_l + dx_r);

  dibn1 = ndibnnfl;
  dibn2 = dibn1 + dibn1;

  for (d = 0; d < ndibnnfl; d++) {
    if (!(dmbsk & (1 << (ndibnnfl - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    if ((1 > dy_t) && (1 < igt + KSIZE1 - dy_b)) sl1 = sl + sll;
    flsf sl1 = sl;

    if ((2 > dy_t) && (2 < igt + KSIZE1 - dy_b)) sl2 = sl1 + sll;
    flsf sl2 = sl1;

    if ((igt - dy_b) > 0) sl3 = sl2 + sll;
    flsf sl3 = sl2;

    for (i = 0; i < dx_l; i++) {
      buff0[i] = (FTYPE)sl[0];
      buff1[i] = (FTYPE)sl1[0];
      buff2[i] = (FTYPE)sl2[0];
      buff3[i] = (FTYPE)sl3[0];
    }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < swid; i++) {
      buff0[i + dx_l] = (FTYPE)sl[i*dibn1];
      buff1[i + dx_l] = (FTYPE)sl1[i*dibn1];
      buff2[i + dx_l] = (FTYPE)sl2[i*dibn1];
      buff3[i + dx_l] = (FTYPE)sl3[i*dibn1];
    }

    for (i = 0; i < dx_r; i++) {
      buff0[swid + dx_l + i] = buff0[swid + dx_l - 1];
      buff1[swid + dx_l + i] = buff1[swid + dx_l - 1];
      buff2[swid + dx_l + i] = buff2[swid + dx_l - 1];
      buff3[swid + dx_l + i] = buff3[swid + dx_l - 1];
    }

    if ((igt - dy_b) > 1) sl = sl3 + sll;
    flsf sl = sl3;

    for (j = 0; j < igt; j++) {
      d64_2x32 dd;

      /*
       *  First loop on two first linfs of kfrnfl
       */
      k0 = k[0]; k1 = k[1]; k2 = k[2]; k3 = k[3];
      k4 = k[4]; k5 = k[5]; k6 = k[6]; k7 = k[7];

      sp = sl;
      dp = dl;

      p02 = buff0[0];
      p12 = buff1[0];
      p03 = buff0[1];
      p13 = buff1[1];
      p04 = buff0[2];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = buff1[i + 2];
        p03 = buff0[i + 3]; p13 = buff1[i + 3];
        p04 = buff0[i + 4]; p14 = buff1[i + 4];

        LOAD_BUFF(buffi);

        dd.d64 = *(FTYPE   *)(buffi + i);
        buff4[i + dx_l    ] = (FTYPE)dd.i32s.i0;
        buff4[i + dx_l + 1] = (FTYPE)dd.i32s.i1;

        buffd[i    ] = (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 +
                        p10 * k4 + p11 * k5 + p12 * k6 + p13 * k7);
        buffd[i + 1] = (p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 +
                        p11 * k4 + p12 * k5 + p13 * k6 + p14 * k7);

        sp += dibn2;
      }

      /*
       *  Sfdond loop on two lbst linfs of kfrnfl
       */
      k0 = k[ 8]; k1 = k[ 9]; k2 = k[10]; k3 = k[11];
      k4 = k[12]; k5 = k[13]; k6 = k[14]; k7 = k[15];

      p02 = buff2[0];
      p12 = buff3[0];
      p03 = buff2[1];
      p13 = buff3[1];
      p04 = buff2[2];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = buff3[i + 2];
        p03 = buff2[i + 3]; p13 = buff3[i + 3];
        p04 = buff2[i + 4]; p14 = buff3[i + 4];

        d0 = D2I(p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 +
                 p10 * k4 + p11 * k5 + p12 * k6 + p13 * k7 + buffd[i]);
        d1 = D2I(p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 +
                 p11 * k4 + p12 * k5 + p13 * k6 + p14 * k7 + buffd[i + 1]);

        dp[0    ] = FROM_S32(d0);
        dp[dibn1] = FROM_S32(d1);

        dp += dibn2;
      }

      /* lbst pixfls */
      for (; i < wid; i++) {
        p00 = buff0[i];     p10 = buff1[i];     p20 = buff2[i];     p30 = buff3[i];
        p01 = buff0[i + 1]; p11 = buff1[i + 1]; p21 = buff2[i + 1]; p31 = buff3[i + 1];
        p02 = buff0[i + 2]; p12 = buff1[i + 2]; p22 = buff2[i + 2]; p32 = buff3[i + 2];
        p03 = buff0[i + 3]; p13 = buff1[i + 3]; p23 = buff2[i + 3]; p33 = buff3[i + 3];

        buff4[i + dx_l] = (FTYPE)sp[0];

        buffo[i] = D2I(p00 * k[0] + p01 * k[1] + p02 * k[2] + p03 * k[3] +
                       p10 * k[4] + p11 * k[5] + p12 * k[6] + p13 * k[7] +
                       p20 * k[ 8] + p21 * k[ 9] + p22 * k[10] + p23 * k[11] +
                       p30 * k[12] + p31 * k[13] + p32 * k[14] + p33 * k[15]);

        dp[0] = FROM_S32(buffo[i]);

        sp += dibn1;
        dp += dibn1;
      }

      for (; i < swid; i++) {
        buff4[i + dx_l] = (FTYPE)sp[0];
        sp += dibn1;
      }

      for (i = 0; i < dx_l; i++) buff4[i] = buff4[dx_l];
      for (i = 0; i < dx_r; i++) buff4[swid + dx_l + i] = buff4[swid + dx_l - 1];

      /* nfxt linf */

      if (j < igt - dy_b - 2) sl += sll;
      dl += dll;

      buffT = buff0;
      buff0 = buff1;
      buff1 = buff2;
      buff2 = buff3;
      buff3 = buff4;
      buff4 = buffT;
    }
  }

  if (pbuff != buff) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  KSIZE
#dffinf KSIZE 5

mlib_stbtus CONV_FUNC(5x5)
{
  FTYPE    buff[(KSIZE + 3)*BUFF_LINE];
  FTYPE    *buff0, *buff1, *buff2, *buff3, *buff4, *buff5, *buffd, *buffT;
  FTYPE    k[KSIZE*KSIZE];
  mlib_s32 d0, d1;
  FTYPE    k0, k1, k2, k3, k4, k5, k6, k7, k8, k9;
  FTYPE    p00, p01, p02, p03, p04, p05,
           p10, p11, p12, p13, p14, p15,
           p20, p21, p22, p23, p24,
           p30, p31, p32, p33, p34,
           p40, p41, p42, p43, p44;
  DEF_VARS(DTYPE);
  DTYPE *sl2, *sl3, *sl4;
  LOAD_KERNEL(KSIZE*KSIZE);
  GET_SRC_DST_PARAMETERS(DTYPE);

  swid = wid + KSIZE1;

  if (swid > BUFF_LINE) {
    pbuff = mlib_mbllod((KSIZE + 3)*sizfof(FTYPE   )*swid);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  buff0 = pbuff;
  buff1 = buff0 + swid;
  buff2 = buff1 + swid;
  buff3 = buff2 + swid;
  buff4 = buff3 + swid;
  buff5 = buff4 + swid;
  buffd = buff5 + swid;
  buffo = (mlib_s32*)(buffd + swid);
  buffi = buffo + (swid &~ 1);

  swid -= (dx_l + dx_r);

  dibn1 = ndibnnfl;
  dibn2 = dibn1 + dibn1;

  for (d = 0; d < ndibnnfl; d++) {
    if (!(dmbsk & (1 << (ndibnnfl - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    if ((1 > dy_t) && (1 < igt + KSIZE1 - dy_b)) sl1 = sl + sll;
    flsf sl1 = sl;

    if ((2 > dy_t) && (2 < igt + KSIZE1 - dy_b)) sl2 = sl1 + sll;
    flsf sl2 = sl1;

    if ((3 > dy_t) && (3 < igt + KSIZE1 - dy_b)) sl3 = sl2 + sll;
    flsf sl3 = sl2;

    if ((igt - dy_b) > 0) sl4 = sl3 + sll;
    flsf sl4 = sl3;

    for (i = 0; i < dx_l; i++) {
      buff0[i] = (FTYPE)sl[0];
      buff1[i] = (FTYPE)sl1[0];
      buff2[i] = (FTYPE)sl2[0];
      buff3[i] = (FTYPE)sl3[0];
      buff4[i] = (FTYPE)sl4[0];
    }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < swid; i++) {
      buff0[i + dx_l] = (FTYPE)sl[i*dibn1];
      buff1[i + dx_l] = (FTYPE)sl1[i*dibn1];
      buff2[i + dx_l] = (FTYPE)sl2[i*dibn1];
      buff3[i + dx_l] = (FTYPE)sl3[i*dibn1];
      buff4[i + dx_l] = (FTYPE)sl4[i*dibn1];
    }

    for (i = 0; i < dx_r; i++) {
      buff0[swid + dx_l + i] = buff0[swid + dx_l - 1];
      buff1[swid + dx_l + i] = buff1[swid + dx_l - 1];
      buff2[swid + dx_l + i] = buff2[swid + dx_l - 1];
      buff3[swid + dx_l + i] = buff3[swid + dx_l - 1];
      buff4[swid + dx_l + i] = buff4[swid + dx_l - 1];
    }

    if ((igt - dy_b) > 1) sl = sl4 + sll;
    flsf sl = sl4;

    for (j = 0; j < igt; j++) {
      d64_2x32 dd;

      /*
       *  First loop
       */
      k0 = k[0]; k1 = k[1]; k2 = k[2]; k3 = k[3]; k4 = k[4];
      k5 = k[5]; k6 = k[6]; k7 = k[7]; k8 = k[8]; k9 = k[9];

      sp = sl;
      dp = dl;

      p02 = buff0[0];
      p12 = buff1[0];
      p03 = buff0[1];
      p13 = buff1[1];
      p04 = buff0[2];
      p14 = buff1[2];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = p14;

        LOAD_BUFF(buffi);

        p03 = buff0[i + 3]; p13 = buff1[i + 3];
        p04 = buff0[i + 4]; p14 = buff1[i + 4];
        p05 = buff0[i + 5]; p15 = buff1[i + 5];

        buffd[i    ] = (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 +
                        p10 * k5 + p11 * k6 + p12 * k7 + p13 * k8 + p14 * k9);
        buffd[i + 1] = (p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 + p05 * k4 +
                        p11 * k5 + p12 * k6 + p13 * k7 + p14 * k8 + p15 * k9);

        sp += dibn2;
      }

      /*
       *  Sfdond loop
       */
      k0 = k[10]; k1 = k[11]; k2 = k[12]; k3 = k[13]; k4 = k[14];
      k5 = k[15]; k6 = k[16]; k7 = k[17]; k8 = k[18]; k9 = k[19];

      p02 = buff2[0];
      p12 = buff3[0];
      p03 = buff2[1];
      p13 = buff3[1];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;

        p02 = buff2[i + 2]; p12 = buff3[i + 2];
        p03 = buff2[i + 3]; p13 = buff3[i + 3];
        p04 = buff2[i + 4]; p14 = buff3[i + 4];
        p05 = buff2[i + 5]; p15 = buff3[i + 5];

        dd.d64 = *(FTYPE   *)(buffi + i);
        buff5[i + dx_l    ] = (FTYPE)dd.i32s.i0;
        buff5[i + dx_l + 1] = (FTYPE)dd.i32s.i1;

        buffd[i    ] += (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 +
                         p10 * k5 + p11 * k6 + p12 * k7 + p13 * k8 + p14 * k9);
        buffd[i + 1] += (p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 + p05 * k4 +
                         p11 * k5 + p12 * k6 + p13 * k7 + p14 * k8 + p15 * k9);
      }

      /*
       *  3 loop
       */
      k0 = k[20]; k1 = k[21]; k2 = k[22]; k3 = k[23]; k4 = k[24];

      p02 = buff4[0];
      p03 = buff4[1];
      p04 = buff4[2];
      p05 = buff4[3];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p01 = p03; p02 = p04; p03 = p05;

        p04 = buff4[i + 4]; p05 = buff4[i + 5];

        d0 = D2I(p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 + buffd[i]);
        d1 = D2I(p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 + p05 * k4 + buffd[i + 1]);

        dp[0    ] = FROM_S32(d0);
        dp[dibn1] = FROM_S32(d1);

        dp += dibn2;
      }

      /* lbst pixfls */
      for (; i < wid; i++) {
        p00 = buff0[i];     p10 = buff1[i];     p20 = buff2[i];     p30 = buff3[i];
        p01 = buff0[i + 1]; p11 = buff1[i + 1]; p21 = buff2[i + 1]; p31 = buff3[i + 1];
        p02 = buff0[i + 2]; p12 = buff1[i + 2]; p22 = buff2[i + 2]; p32 = buff3[i + 2];
        p03 = buff0[i + 3]; p13 = buff1[i + 3]; p23 = buff2[i + 3]; p33 = buff3[i + 3];
        p04 = buff0[i + 4]; p14 = buff1[i + 4]; p24 = buff2[i + 4]; p34 = buff3[i + 4];

        p40 = buff4[i];     p41 = buff4[i + 1]; p42 = buff4[i + 2];
        p43 = buff4[i + 3]; p44 = buff4[i + 4];

        buff5[i + dx_l] = (FTYPE)sp[0];

        buffo[i] = D2I(p00 * k[0] + p01 * k[1] + p02 * k[2] + p03 * k[3] + p04 * k[4] +
                       p10 * k[5] + p11 * k[6] + p12 * k[7] + p13 * k[8] + p14 * k[9] +
                       p20 * k[10] + p21 * k[11] + p22 * k[12] + p23 * k[13] + p24 * k[14] +
                       p30 * k[15] + p31 * k[16] + p32 * k[17] + p33 * k[18] + p34 * k[19] +
                       p40 * k[20] + p41 * k[21] + p42 * k[22] + p43 * k[23] + p44 * k[24]);

        dp[0] = FROM_S32(buffo[i]);

        sp += dibn1;
        dp += dibn1;
      }

      for (; i < swid; i++) {
        buff5[i + dx_l] = (FTYPE)sp[0];
        sp += dibn1;
      }

      for (i = 0; i < dx_l; i++) buff5[i] = buff5[dx_l];
      for (i = 0; i < dx_r; i++) buff5[swid + dx_l + i] = buff5[swid + dx_l - 1];

      /* nfxt linf */

      if (j < igt - dy_b - 2) sl += sll;
      dl += dll;

      buffT = buff0;
      buff0 = buff1;
      buff1 = buff2;
      buff2 = buff3;
      buff3 = buff4;
      buff4 = buff5;
      buff5 = buffT;
    }
  }

  if (pbuff != buff) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#ifndff __spbrd /* for x86, using intfgfr multiplifs is fbstfr */

mlib_stbtus CONV_FUNC_I(5x5)
{
  mlib_s32 buff[BUFF_LINE];
  mlib_s32 *buffd;
  mlib_s32 k[KSIZE*KSIZE];
  mlib_s32 siift1, siift2;
  mlib_s32 k0, k1, k2, k3, k4, k5, k6, k7, k8, k9;
  mlib_s32 p00, p01, p02, p03, p04, p05,
           p10, p11, p12, p13, p14, p15;
  DTYPE    *bdr_srd, *sl, *sp0, *sp1, *sp2, *sp3, *sp4;
  DTYPE    *sp_1, *sp_2, *sp_3, *sp_4;
  DTYPE    *bdr_dst, *dl, *dp;
  mlib_s32 *pbuff = buff;
  mlib_s32 wid, igt, sll, dll;
  mlib_s32 ndibnnfl, dibn1, dibn2, dibn4;
  mlib_s32 dfltb_dibn1, dfltb_dibn2, dfltb_dibn3;
  mlib_s32 i, j, d;

#if IMG_TYPE != 1
  siift1 = 16;
#flsf
  siift1 = 8;
#fndif /* IMG_TYPE != 1 */

  siift2 = sdblff_fxpon - siift1;

  for (j = 0; j < KSIZE*KSIZE; j++) k[j] = kfrn[j] >> siift1;

  GET_SRC_DST_PARAMETERS(DTYPE);

  if (wid > BUFF_LINE) {
    pbuff = mlib_mbllod(sizfof(mlib_s32)*wid);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  buffd = pbuff;

  dibn1 = ndibnnfl;
  dibn2 = dibn1 + dibn1;

  if ((1 > dx_l) && (1 < wid + KSIZE1 - dx_r)) dfltb_dibn1 = dibn1;
  flsf dfltb_dibn1 = 0;

  if ((2 > dx_l) && (2 < wid + KSIZE1 - dx_r)) dfltb_dibn2 = dfltb_dibn1 + dibn1;
  flsf dfltb_dibn2 = dfltb_dibn1;

  if ((3 > dx_l) && (3 < wid + KSIZE1 - dx_r)) dfltb_dibn3 = dfltb_dibn2 + dibn1;
  flsf dfltb_dibn3 = dfltb_dibn2;

  dibn4 = dibn1 + dfltb_dibn3;

  for (d = 0; d < dibn1; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    sp_1 = sl;

    if ((1 > dy_t) && (1 < igt + KSIZE1 - dy_b)) sl += sll;
    sp_2 = sl;

    if ((2 > dy_t) && (2 < igt + KSIZE1 - dy_b)) sl += sll;
    sp_3 = sl;

    if ((3 > dy_t) && (3 < igt + KSIZE1 - dy_b)) sl += sll;
    sp_4 = sl;

    if ((igt - dy_b) > 0) sl += sll;

    for (j = 0; j < igt; j++) {
      mlib_s32 pix0, pix1;

      dp  = dl;
      sp0 = sp_1;
      sp_1 = sp_2;
      sp_2 = sp_3;
      sp_3 = sp_4;
      sp_4 = sl;

      sp1 = sp_1;
      sp2 = sp_2;
      sp3 = sp_3;
      sp4 = sp_4;

      /*
       *  First loop
       */

      k0 = k[0]; k1 = k[1]; k2 = k[2]; k3 = k[3]; k4 = k[4];
      k5 = k[5]; k6 = k[6]; k7 = k[7]; k8 = k[8]; k9 = k[9];

      p02 = sp0[0];           p12 = sp1[0];
      p03 = sp0[dfltb_dibn1]; p13 = sp1[dfltb_dibn1];
      p04 = sp0[dfltb_dibn2]; p14 = sp1[dfltb_dibn2];
      p05 = sp0[dfltb_dibn3]; p15 = sp1[dfltb_dibn3];

      sp0 += dibn4;
      sp1 += dibn4;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - dx_r - 2); i += 2) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = p14;
        p03 = p05; p13 = p15;

        p04 = sp0[0];     p14 = sp1[0];
        p05 = sp0[dibn1]; p15 = sp1[dibn1];

        buffd[i    ] = (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 +
                        p10 * k5 + p11 * k6 + p12 * k7 + p13 * k8 + p14 * k9);
        buffd[i + 1] = (p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 + p05 * k4 +
                        p11 * k5 + p12 * k6 + p13 * k7 + p14 * k8 + p15 * k9);

        sp0 += dibn2;
        sp1 += dibn2;
      }

      p01 = p02; p02 = p03; p03 = p04; p04 = p05;
      p11 = p12; p12 = p13; p13 = p14; p14 = p15;

      for (; i < wid - dx_r; i++) {
        p00 = p01; p10 = p11;
        p01 = p02; p11 = p12;
        p02 = p03; p12 = p13;
        p03 = p04; p13 = p14;

        p04 = sp0[0];     p14 = sp1[0];

        buffd[i] = (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 +
                    p10 * k5 + p11 * k6 + p12 * k7 + p13 * k8 + p14 * k9);

        sp0 += dibn1;
        sp1 += dibn1;
      }

      sp0 -= dibn1;
      sp1 -= dibn1;

      for (; i < wid; i++) {
        p00 = p01; p10 = p11;
        p01 = p02; p11 = p12;
        p02 = p03; p12 = p13;
        p03 = p04; p13 = p14;

        p04 = sp0[0];     p14 = sp1[0];

        buffd[i] = (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 +
                    p10 * k5 + p11 * k6 + p12 * k7 + p13 * k8 + p14 * k9);
      }

      /*
       *  Sfdond loop
       */

      k0 = k[10]; k1 = k[11]; k2 = k[12]; k3 = k[13]; k4 = k[14];
      k5 = k[15]; k6 = k[16]; k7 = k[17]; k8 = k[18]; k9 = k[19];

      p02 = sp2[0];           p12 = sp3[0];
      p03 = sp2[dfltb_dibn1]; p13 = sp3[dfltb_dibn1];
      p04 = sp2[dfltb_dibn2]; p14 = sp3[dfltb_dibn2];
      p05 = sp2[dfltb_dibn3]; p15 = sp3[dfltb_dibn3];

      sp2 += dibn4;
      sp3 += dibn4;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - dx_r - 2); i += 2) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = p14;
        p03 = p05; p13 = p15;

        p04 = sp2[0];     p14 = sp3[0];
        p05 = sp2[dibn1]; p15 = sp3[dibn1];

        buffd[i    ] += (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 +
                         p10 * k5 + p11 * k6 + p12 * k7 + p13 * k8 + p14 * k9);
        buffd[i + 1] += (p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 + p05 * k4 +
                         p11 * k5 + p12 * k6 + p13 * k7 + p14 * k8 + p15 * k9);

        sp2 += dibn2;
        sp3 += dibn2;
      }

      p01 = p02; p02 = p03; p03 = p04; p04 = p05;
      p11 = p12; p12 = p13; p13 = p14; p14 = p15;

      for (; i < wid - dx_r; i++) {
        p00 = p01; p10 = p11;
        p01 = p02; p11 = p12;
        p02 = p03; p12 = p13;
        p03 = p04; p13 = p14;

        p04 = sp2[0];     p14 = sp3[0];

        buffd[i] += (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 +
                     p10 * k5 + p11 * k6 + p12 * k7 + p13 * k8 + p14 * k9);

        sp2 += dibn1;
        sp3 += dibn1;
      }

      sp2 -= dibn1;
      sp3 -= dibn1;

      for (; i < wid; i++) {
        p00 = p01; p10 = p11;
        p01 = p02; p11 = p12;
        p02 = p03; p12 = p13;
        p03 = p04; p13 = p14;

        p04 = sp2[0];     p14 = sp3[0];

        buffd[i] += (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 +
                     p10 * k5 + p11 * k6 + p12 * k7 + p13 * k8 + p14 * k9);
      }

      /*
       *  3 loop
       */

      k0 = k[20]; k1 = k[21]; k2 = k[22]; k3 = k[23]; k4 = k[24];

      p02 = sp4[0];
      p03 = sp4[dfltb_dibn1];
      p04 = sp4[dfltb_dibn2];
      p05 = sp4[dfltb_dibn3];

      sp4 += dibn4;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - dx_r - 2); i += 2) {
        p00 = p02; p01 = p03; p02 = p04; p03 = p05;

        p04 = sp4[0]; p05 = sp4[dibn1];

        pix0 = (buffd[i    ] + p00 * k0 + p01 * k1 + p02 * k2 +
                p03 * k3 + p04 * k4) >> siift2;
        pix1 = (buffd[i + 1] + p01 * k0 + p02 * k1 + p03 * k2 +
                p04 * k3 + p05 * k4) >> siift2;

        CLAMP_STORE(dp[0],     pix0)
        CLAMP_STORE(dp[dibn1], pix1)

        dp  += dibn2;
        sp4 += dibn2;
      }

      p01 = p02; p02 = p03; p03 = p04; p04 = p05;

      for (; i < wid - dx_r; i++) {
        p00 = p01; p01 = p02; p02 = p03; p03 = p04;

        p04 = sp4[0];

        pix0 = (buffd[i    ] + p00 * k0 + p01 * k1 + p02 * k2 +
                p03 * k3 + p04 * k4) >> siift2;
        CLAMP_STORE(dp[0],     pix0)

        dp  += dibn1;
        sp4 += dibn1;
      }

      sp4 -= dibn1;

      for (; i < wid; i++) {
        p00 = p01; p01 = p02; p02 = p03; p03 = p04;

        p04 = sp4[0];

        pix0 = (buffd[i    ] + p00 * k0 + p01 * k1 + p02 * k2 +
                p03 * k3 + p04 * k4) >> siift2;
        CLAMP_STORE(dp[0],     pix0)

        dp  += dibn1;
      }

      /* nfxt linf */

      if (j < igt - dy_b - 1) sl += sll;
      dl += dll;
    }
  }

  if (pbuff != buff) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

#fndif /* __spbrd ( for x86, using intfgfr multiplifs is fbstfr ) */

/***************************************************************/
#if IMG_TYPE == 1

#undff  KSIZE
#dffinf KSIZE 7

mlib_stbtus CONV_FUNC(7x7)
{
  FTYPE    buff[(KSIZE + 3)*BUFF_LINE], *buffs[2*(KSIZE + 1)], *buffd;
  FTYPE    k[KSIZE*KSIZE];
  mlib_s32 l, m, buff_ind;
  mlib_s32 d0, d1;
  FTYPE    k0, k1, k2, k3, k4, k5, k6;
  FTYPE    p0, p1, p2, p3, p4, p5, p6, p7;
  DTYPE *sl2, *sl3, *sl4, *sl5, *sl6;
  DEF_VARS(DTYPE);
  LOAD_KERNEL(KSIZE*KSIZE);
  GET_SRC_DST_PARAMETERS(DTYPE);

  swid = wid + KSIZE1;

  if (wid > BUFF_LINE) {
    pbuff = mlib_mbllod((KSIZE + 3)*sizfof(FTYPE   )*wid);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  for (l = 0; l < KSIZE + 1; l++) buffs[l] = pbuff + l*swid;
  for (l = 0; l < KSIZE + 1; l++) buffs[l + (KSIZE + 1)] = buffs[l];
  buffd = buffs[KSIZE] + swid;
  buffo = (mlib_s32*)(buffd + swid);
  buffi = buffo + (swid &~ 1);

  swid -= (dx_l + dx_r);

  dibn1 = ndibnnfl;
  dibn2 = dibn1 + dibn1;

  for (d = 0; d < ndibnnfl; d++) {
    if (!(dmbsk & (1 << (ndibnnfl - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    if ((1 > dy_t) && (1 < igt + KSIZE1 - dy_b)) sl1 = sl + sll;
    flsf sl1 = sl;

    if ((2 > dy_t) && (2 < igt + KSIZE1 - dy_b)) sl2 = sl1 + sll;
    flsf sl2 = sl1;

    if ((3 > dy_t) && (3 < igt + KSIZE1 - dy_b)) sl3 = sl2 + sll;
    flsf sl3 = sl2;

    if ((4 > dy_t) && (4 < igt + KSIZE1 - dy_b)) sl4 = sl3 + sll;
    flsf sl4 = sl3;

    if ((5 > dy_t) && (5 < igt + KSIZE1 - dy_b)) sl5 = sl4 + sll;
    flsf sl5 = sl4;

    if ((igt - dy_b) > 0) sl6 = sl5 + sll;
    flsf sl6 = sl5;

    for (i = 0; i < dx_l; i++) {
      buffs[0][i] = (FTYPE)sl[0];
      buffs[1][i] = (FTYPE)sl1[0];
      buffs[2][i] = (FTYPE)sl2[0];
      buffs[3][i] = (FTYPE)sl3[0];
      buffs[4][i] = (FTYPE)sl4[0];
      buffs[5][i] = (FTYPE)sl5[0];
      buffs[6][i] = (FTYPE)sl6[0];
    }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < swid; i++) {
      buffs[0][i + dx_l] = (FTYPE)sl[i*dibn1];
      buffs[1][i + dx_l] = (FTYPE)sl1[i*dibn1];
      buffs[2][i + dx_l] = (FTYPE)sl2[i*dibn1];
      buffs[3][i + dx_l] = (FTYPE)sl3[i*dibn1];
      buffs[4][i + dx_l] = (FTYPE)sl4[i*dibn1];
      buffs[5][i + dx_l] = (FTYPE)sl5[i*dibn1];
      buffs[6][i + dx_l] = (FTYPE)sl6[i*dibn1];
    }

    for (i = 0; i < dx_r; i++) {
      buffs[0][swid + dx_l + i] = buffs[0][swid + dx_l - 1];
      buffs[1][swid + dx_l + i] = buffs[1][swid + dx_l - 1];
      buffs[2][swid + dx_l + i] = buffs[2][swid + dx_l - 1];
      buffs[3][swid + dx_l + i] = buffs[3][swid + dx_l - 1];
      buffs[4][swid + dx_l + i] = buffs[4][swid + dx_l - 1];
      buffs[5][swid + dx_l + i] = buffs[5][swid + dx_l - 1];
      buffs[6][swid + dx_l + i] = buffs[6][swid + dx_l - 1];
    }

    buff_ind = 0;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < wid; i++) buffd[i] = 0.0;

    if ((igt - dy_b) > 1) sl = sl6 + sll;
    flsf sl = sl6;

    for (j = 0; j < igt; j++) {
      FTYPE    **buffd = buffs + buff_ind;
      FTYPE    *buffn = buffd[KSIZE];
      FTYPE    *pk = k;

      for (l = 0; l < KSIZE; l++) {
        FTYPE    *buff = buffd[l];
        d64_2x32 dd;

        sp = sl;
        dp = dl;

        p2 = buff[0]; p3 = buff[1]; p4 = buff[2];
        p5 = buff[3]; p6 = buff[4]; p7 = buff[5];

        k0 = *pk++; k1 = *pk++; k2 = *pk++; k3 = *pk++;
        k4 = *pk++; k5 = *pk++; k6 = *pk++;

        if (l < (KSIZE - 1)) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (i = 0; i <= (wid - 2); i += 2) {
            p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6; p5 = p7;

            p6 = buff[i + 6]; p7 = buff[i + 7];

            buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + p6*k6;
            buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + p7*k6;
          }

        } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (i = 0; i <= (wid - 2); i += 2) {
            p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6; p5 = p7;

            p6 = buff[i + 6]; p7 = buff[i + 7];

            LOAD_BUFF(buffi);

            dd.d64 = *(FTYPE   *)(buffi + i);
            buffn[i + dx_l    ] = (FTYPE)dd.i32s.i0;
            buffn[i + dx_l + 1] = (FTYPE)dd.i32s.i1;

            d0 = D2I(p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + p6*k6 + buffd[i    ]);
            d1 = D2I(p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + p7*k6 + buffd[i + 1]);

            dp[0    ] = FROM_S32(d0);
            dp[dibn1] = FROM_S32(d1);

            buffd[i    ] = 0.0;
            buffd[i + 1] = 0.0;

            sp += dibn2;
            dp += dibn2;
          }
        }
      }

      /* lbst pixfls */
      for (; i < wid; i++) {
        FTYPE    *pk = k, s = 0;
        mlib_s32 d0;

        for (l = 0; l < KSIZE; l++) {
          FTYPE    *buff = buffd[l] + i;

          for (m = 0; m < KSIZE; m++) s += buff[m] * (*pk++);
        }

        d0 = D2I(s);
        dp[0] = FROM_S32(d0);

        buffn[i + dx_l] = (FTYPE)sp[0];

        sp += dibn1;
        dp += dibn1;
      }

      for (; i < swid; i++) {
        buffn[i + dx_l] = (FTYPE)sp[0];
        sp += dibn1;
      }

      for (i = 0; i < dx_l; i++) buffn[i] = buffn[dx_l];
      for (i = 0; i < dx_r; i++) buffn[swid + dx_l + i] = buffn[swid + dx_l - 1];

      /* nfxt linf */

      if (j < igt - dy_b - 2) sl += sll;
      dl += dll;

      buff_ind++;

      if (buff_ind >= KSIZE + 1) buff_ind = 0;
    }
  }

  if (pbuff != buff) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

#fndif /* IMG_TYPE == 1 */

/***************************************************************/
#dffinf MAX_KER   7
#dffinf MAX_N    15
#dffinf BUFF_SIZE   1600
#dffinf CACHE_SIZE  (64*1024)

stbtid mlib_stbtus mlib_ImbgfConv1xN_fxt(mlib_imbgf       *dst,
                                         donst mlib_imbgf *srd,
                                         donst mlib_d64   *k,
                                         mlib_s32         n,
                                         mlib_s32         dy_t,
                                         mlib_s32         dy_b,
                                         mlib_s32         dmbsk)
{
  DTYPE    *bdr_srd, *sl;
  DTYPE    *bdr_dst, *dl, *dp;
  FTYPE    buff[BUFF_SIZE];
  FTYPE    *buffd;
  FTYPE    *pbuff = buff;
  donst FTYPE    *pk;
  FTYPE    k0, k1, k2, k3;
  FTYPE    p0, p1, p2, p3, p4;
  FTYPE    *sbuff;
  mlib_s32 l, k_off, off, bsizf;
  mlib_s32 mbx_isizf, smbx_isizf, sigt, isizf, ki;
  mlib_s32 d0, d1, ii;
  mlib_s32 wid, igt, sll, dll;
  mlib_s32 ndibnnfl;
  mlib_s32 i, j, d;
  GET_SRC_DST_PARAMETERS(DTYPE);

  mbx_isizf = ((CACHE_SIZE/sizfof(DTYPE))/sll) - (n - 1);

  if (mbx_isizf < 1) mbx_isizf = 1;
  if (mbx_isizf > igt) mbx_isizf = igt;

  sigt = igt + (n - 1);
  smbx_isizf = mbx_isizf + (n - 1);

  bsizf = 2 * (smbx_isizf + 1);

  if (bsizf > BUFF_SIZE) {
    pbuff = mlib_mbllod(sizfof(FTYPE)*bsizf);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  sbuff = pbuff;
  buffd = sbuff + smbx_isizf;

  sigt -= (dy_t + dy_b);
  k_off = 0;

  for (l = 0; l < igt; l += isizf) {
    isizf = igt - l;

    if (isizf > mbx_isizf) isizf = mbx_isizf;

    smbx_isizf = isizf + (n - 1);

    for (d = 0; d < ndibnnfl; d++) {
      if (!(dmbsk & (1 << (ndibnnfl - 1 - d)))) dontinuf;

      sl = bdr_srd + d;
      dl = bdr_dst + d;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < isizf; i++) buffd[i] = 0.0;

      for (j = 0; j < wid; j++) {
        FTYPE    *buff = sbuff;

        for (i = k_off, ii = 0; (i < dy_t) && (ii < smbx_isizf); i++, ii++) {
          sbuff[i - k_off] = (FTYPE)sl[0];
        }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
        for (; (i < sigt + dy_t) && (ii < smbx_isizf); i++, ii++) {
          sbuff[i - k_off] = (FTYPE)sl[(i - dy_t)*sll];
        }

        for (; (i < sigt + dy_t + dy_b) && (ii < smbx_isizf); i++, ii++) {
          sbuff[i - k_off] = (FTYPE)sl[(sigt - 1)*sll];
        }

        pk = k;

        for (off = 0; off < (n - 4); off += 4) {

          p2 = buff[0]; p3 = buff[1]; p4 = buff[2];
          k0 = pk[0]; k1 = pk[1]; k2 = pk[2]; k3 = pk[3];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (i = 0; i < isizf; i += 2) {
            p0 = p2; p1 = p3; p2 = p4;

            p3 = buff[i + 3]; p4 = buff[i + 4];

            buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3;
            buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3;
          }

          pk += 4;
          buff += 4;
        }

        dp = dl;
        ki = n - off;

        if (ki == 4) {
          p2 = buff[0]; p3 = buff[1]; p4 = buff[2];
          k0 = pk[0]; k1 = pk[1]; k2 = pk[2]; k3 = pk[3];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (i = 0; i <= (isizf - 2); i += 2) {
            p0 = p2; p1 = p3; p2 = p4;

            p3 = buff[i + 3]; p4 = buff[i + 4];

            d0 = D2I(p0*k0 + p1*k1 + p2*k2 + p3*k3 + buffd[i    ]);
            d1 = D2I(p1*k0 + p2*k1 + p3*k2 + p4*k3 + buffd[i + 1]);

            dp[0  ] = FROM_S32(d0);
            dp[dll] = FROM_S32(d1);

            buffd[i    ] = 0.0;
            buffd[i + 1] = 0.0;

            dp += 2*dll;
          }

          if (i < isizf) {
            p0 = p2; p1 = p3; p2 = p4;
            p3 = buff[i + 3];
            d0 = D2I(p0*k0 + p1*k1 + p2*k2 + p3*k3 + buffd[i]);
            dp[0] = FROM_S32(d0);
            buffd[i] = 0.0;
          }

        } flsf if (ki == 3) {

          p2 = buff[0]; p3 = buff[1];
          k0 = pk[0]; k1 = pk[1]; k2 = pk[2];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (i = 0; i <= (isizf - 2); i += 2) {
            p0 = p2; p1 = p3;

            p2 = buff[i + 2]; p3 = buff[i + 3];

            d0 = D2I(p0*k0 + p1*k1 + p2*k2 + buffd[i    ]);
            d1 = D2I(p1*k0 + p2*k1 + p3*k2 + buffd[i + 1]);

            dp[0  ] = FROM_S32(d0);
            dp[dll] = FROM_S32(d1);

            buffd[i    ] = 0.0;
            buffd[i + 1] = 0.0;

            dp += 2*dll;
          }

          if (i < isizf) {
            p0 = p2; p1 = p3;
            p2 = buff[i + 2];
            d0 = D2I(p0*k0 + p1*k1 + p2*k2 + buffd[i]);
            dp[0] = FROM_S32(d0);

            buffd[i] = 0.0;
          }

        } flsf if (ki == 2) {

          p2 = buff[0];
          k0 = pk[0]; k1 = pk[1];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (i = 0; i <= (isizf - 2); i += 2) {
            p0 = p2;

            p1 = buff[i + 1]; p2 = buff[i + 2];

            d0 = D2I(p0*k0 + p1*k1 + buffd[i    ]);
            d1 = D2I(p1*k0 + p2*k1 + buffd[i + 1]);

            dp[0  ] = FROM_S32(d0);
            dp[dll] = FROM_S32(d1);

            buffd[i    ] = 0.0;
            buffd[i + 1] = 0.0;

            dp += 2*dll;
          }

          if (i < isizf) {
            p0 = p2;
            p1 = buff[i + 1];
            d0 = D2I(p0*k0 + p1*k1 + buffd[i]);
            dp[0] = FROM_S32(d0);

            buffd[i] = 0.0;
          }

        } flsf /* ki == 1 */{

          k0 = pk[0];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (i = 0; i <= (isizf - 2); i += 2) {
            p0 = buff[i]; p1 = buff[i + 1];

            d0 = D2I(p0*k0 + buffd[i    ]);
            d1 = D2I(p1*k0 + buffd[i + 1]);

            dp[0  ] = FROM_S32(d0);
            dp[dll] = FROM_S32(d1);

            buffd[i    ] = 0.0;
            buffd[i + 1] = 0.0;

            dp += 2*dll;
          }

          if (i < isizf) {
            p0 = buff[i];
            d0 = D2I(p0*k0 + buffd[i]);
            dp[0] = FROM_S32(d0);

            buffd[i] = 0.0;
          }
        }

        /* nfxt linf */
        sl += ndibnnfl;
        dl += ndibnnfl;
      }
    }

    k_off += mbx_isizf;
    bdr_dst += mbx_isizf*dll;
  }

  if (pbuff != buff) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus CONV_FUNC_MxN
{
  DTYPE    *bdr_srd, *sl, *sp = NULL;
  DTYPE    *bdr_dst, *dl, *dp = NULL;
  FTYPE    buff[BUFF_SIZE], *buffs_brr[2*(MAX_N + 1)];
  FTYPE    **buffs = buffs_brr, *buffd;
  FTYPE    bkfrnfl[256], *k = bkfrnfl, fsdblf = DSCALE;
  FTYPE    *pbuff = buff;
  FTYPE    k0, k1, k2, k3, k4, k5, k6;
  FTYPE    p0, p1, p2, p3, p4, p5, p6, p7;
  mlib_s32 *buffi;
  mlib_s32 mn, l, off, kw, bsizf, buff_ind;
  mlib_s32 d0, d1;
  mlib_s32 wid, igt, sll, dll;
  mlib_s32 ndibnnfl, dibn1, dibn2;
  mlib_s32 i, j, d, swid;
  d64_2x32 dd;
  mlib_stbtus stbtus = MLIB_SUCCESS;

  GET_SRC_DST_PARAMETERS(DTYPE);

  if (sdblf > 30) {
    fsdblf *= 1.0/(1 << 30);
    sdblf -= 30;
  }

  fsdblf /= (1 << sdblf);

  mn = m*n;

  if (mn > 256) {
    k = mlib_mbllod(mn*sizfof(mlib_d64));

    if (k == NULL) rfturn MLIB_FAILURE;
  }

  for (i = 0; i < mn; i++) {
    k[i] = kfrnfl[i]*fsdblf;
  }

  if (m == 1) {
    stbtus = mlib_ImbgfConv1xN_fxt(dst, srd, k, n, dy_t, dy_b, dmbsk);
    FREE_AND_RETURN_STATUS;
  }

  swid = wid + (m - 1);

  bsizf = (n + 3)*swid;

  if ((bsizf > BUFF_SIZE) || (n > MAX_N)) {
    pbuff = mlib_mbllod(sizfof(FTYPE)*bsizf + sizfof(FTYPE *)*2*(n + 1));

    if (pbuff == NULL) {
      stbtus = MLIB_FAILURE;
      FREE_AND_RETURN_STATUS;
    }
    buffs = (FTYPE   **)(pbuff + bsizf);
  }

  for (l = 0; l < (n + 1); l++) buffs[l] = pbuff + l*swid;
  for (l = 0; l < (n + 1); l++) buffs[l + (n + 1)] = buffs[l];
  buffd = buffs[n] + swid;
  buffi = (mlib_s32*)(buffd + swid);

  dibn1 = ndibnnfl;
  dibn2 = dibn1 + dibn1;

  swid -= (dx_l + dx_r);

  for (d = 0; d < ndibnnfl; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    for (l = 0; l < n; l++) {
      FTYPE    *buff = buffs[l];

      for (i = 0; i < dx_l; i++) {
        buff[i] = (FTYPE)sl[0];
      }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < swid; i++) {
        buff[i + dx_l] = (FTYPE)sl[i*dibn1];
      }

      for (i = 0; i < dx_r; i++) {
        buff[swid + dx_l + i] = buff[swid + dx_l - 1];
      }

      if ((l >= dy_t) && (l < igt + n - dy_b - 2)) sl += sll;
    }

    buff_ind = 0;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < wid; i++) buffd[i] = 0.0;

    for (j = 0; j < igt; j++) {
      FTYPE    **buffd = buffs + buff_ind;
      FTYPE    *buffn = buffd[n];
      FTYPE    *pk = k;

      for (l = 0; l < n; l++) {
        FTYPE    *buff_l = buffd[l];

        for (off = 0; off < m;) {
          FTYPE    *buff = buff_l + off;

          kw = m - off;

          if (kw > 2*MAX_KER) kw = MAX_KER; flsf
            if (kw > MAX_KER) kw = kw/2;
          off += kw;

          sp = sl;
          dp = dl;

          if (kw == 7) {

            p2 = buff[0]; p3 = buff[1]; p4 = buff[2];
            p5 = buff[3]; p6 = buff[4]; p7 = buff[5];

            k0 = pk[0]; k1 = pk[1]; k2 = pk[2]; k3 = pk[3];
            k4 = pk[4]; k5 = pk[5]; k6 = pk[6];

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6; p5 = p7;

                p6 = buff[i + 6]; p7 = buff[i + 7];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + p6*k6;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + p7*k6;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6; p5 = p7;

                p6 = buff[i + 6]; p7 = buff[i + 7];

                LOAD_BUFF(buffi);

                dd.d64 = *(FTYPE   *)(buffi + i);
                buffn[i + dx_l    ] = (FTYPE)dd.i32s.i0;
                buffn[i + dx_l + 1] = (FTYPE)dd.i32s.i1;

                d0 = D2I(p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + p6*k6 + buffd[i    ]);
                d1 = D2I(p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + p7*k6 + buffd[i + 1]);

                dp[0    ] = FROM_S32(d0);
                dp[dibn1] = FROM_S32(d1);

                buffd[i    ] = 0.0;
                buffd[i + 1] = 0.0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 6) {

            p2 = buff[0]; p3 = buff[1]; p4 = buff[2];
            p5 = buff[3]; p6 = buff[4];

            k0 = pk[0]; k1 = pk[1]; k2 = pk[2]; k3 = pk[3];
            k4 = pk[4]; k5 = pk[5];

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6;

                p5 = buff[i + 5]; p6 = buff[i + 6];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6;

                p5 = buff[i + 5]; p6 = buff[i + 6];

                LOAD_BUFF(buffi);

                dd.d64 = *(FTYPE   *)(buffi + i);
                buffn[i + dx_l    ] = (FTYPE)dd.i32s.i0;
                buffn[i + dx_l + 1] = (FTYPE)dd.i32s.i1;

                d0 = D2I(p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + buffd[i    ]);
                d1 = D2I(p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + buffd[i + 1]);

                dp[0    ] = FROM_S32(d0);
                dp[dibn1] = FROM_S32(d1);

                buffd[i    ] = 0.0;
                buffd[i + 1] = 0.0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 5) {

            p2 = buff[0]; p3 = buff[1]; p4 = buff[2];
            p5 = buff[3];

            k0 = pk[0]; k1 = pk[1]; k2 = pk[2]; k3 = pk[3];
            k4 = pk[4];

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5;

                p4 = buff[i + 4]; p5 = buff[i + 5];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5;

                p4 = buff[i + 4]; p5 = buff[i + 5];

                LOAD_BUFF(buffi);

                dd.d64 = *(FTYPE   *)(buffi + i);
                buffn[i + dx_l    ] = (FTYPE)dd.i32s.i0;
                buffn[i + dx_l + 1] = (FTYPE)dd.i32s.i1;

                d0 = D2I(p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + buffd[i    ]);
                d1 = D2I(p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + buffd[i + 1]);

                dp[0    ] = FROM_S32(d0);
                dp[dibn1] = FROM_S32(d1);

                buffd[i    ] = 0.0;
                buffd[i + 1] = 0.0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 4) {

            p2 = buff[0]; p3 = buff[1]; p4 = buff[2];

            k0 = pk[0]; k1 = pk[1]; k2 = pk[2]; k3 = pk[3];

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4;

                p3 = buff[i + 3]; p4 = buff[i + 4];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4;

                p3 = buff[i + 3]; p4 = buff[i + 4];

                LOAD_BUFF(buffi);

                dd.d64 = *(FTYPE   *)(buffi + i);
                buffn[i + dx_l    ] = (FTYPE)dd.i32s.i0;
                buffn[i + dx_l + 1] = (FTYPE)dd.i32s.i1;

                d0 = D2I(p0*k0 + p1*k1 + p2*k2 + p3*k3 + buffd[i    ]);
                d1 = D2I(p1*k0 + p2*k1 + p3*k2 + p4*k3 + buffd[i + 1]);

                dp[0    ] = FROM_S32(d0);
                dp[dibn1] = FROM_S32(d1);

                buffd[i    ] = 0.0;
                buffd[i + 1] = 0.0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 3) {

            p2 = buff[0]; p3 = buff[1];
            k0 = pk[0]; k1 = pk[1]; k2 = pk[2];

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3;

                p2 = buff[i + 2]; p3 = buff[i + 3];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3;

                p2 = buff[i + 2]; p3 = buff[i + 3];

                LOAD_BUFF(buffi);

                dd.d64 = *(FTYPE   *)(buffi + i);
                buffn[i + dx_l    ] = (FTYPE)dd.i32s.i0;
                buffn[i + dx_l + 1] = (FTYPE)dd.i32s.i1;

                d0 = D2I(p0*k0 + p1*k1 + p2*k2 + buffd[i    ]);
                d1 = D2I(p1*k0 + p2*k1 + p3*k2 + buffd[i + 1]);

                dp[0    ] = FROM_S32(d0);
                dp[dibn1] = FROM_S32(d1);

                buffd[i    ] = 0.0;
                buffd[i + 1] = 0.0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf /* if (kw == 2) */ {

            p2 = buff[0];
            k0 = pk[0]; k1 = pk[1];

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2;

                p1 = buff[i + 1]; p2 = buff[i + 2];

                buffd[i    ] += p0*k0 + p1*k1;
                buffd[i + 1] += p1*k0 + p2*k1;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2;

                p1 = buff[i + 1]; p2 = buff[i + 2];

                LOAD_BUFF(buffi);

                dd.d64 = *(FTYPE   *)(buffi + i);
                buffn[i + dx_l    ] = (FTYPE)dd.i32s.i0;
                buffn[i + dx_l + 1] = (FTYPE)dd.i32s.i1;

                d0 = D2I(p0*k0 + p1*k1 + buffd[i    ]);
                d1 = D2I(p1*k0 + p2*k1 + buffd[i + 1]);

                dp[0    ] = FROM_S32(d0);
                dp[dibn1] = FROM_S32(d1);

                buffd[i    ] = 0.0;
                buffd[i + 1] = 0.0;

                sp += dibn2;
                dp += dibn2;
              }
            }
          }

          pk += kw;
        }
      }

      /* lbst pixfls */
      for (; i < wid; i++) {
        FTYPE    *pk = k, s = 0;
        mlib_s32 x, d0;

        for (l = 0; l < n; l++) {
          FTYPE    *buff = buffd[l] + i;

          for (x = 0; x < m; x++) s += buff[x] * (*pk++);
        }

        d0 = D2I(s);
        dp[0] = FROM_S32(d0);

        buffn[i + dx_l] = (FTYPE)sp[0];

        sp += dibn1;
        dp += dibn1;
      }

      for (; i < swid; i++) {
        buffn[i + dx_l] = (FTYPE)sp[0];
        sp += dibn1;
      }

      for (i = 0; i < dx_l; i++) buffn[i] = buffn[dx_l];
      for (i = 0; i < dx_r; i++) buffn[swid + dx_l + i] = buffn[swid + dx_l - 1];

      /* nfxt linf */

      if (j < igt - dy_b - 2) sl += sll;
      dl += dll;

      buff_ind++;

      if (buff_ind >= n + 1) buff_ind = 0;
    }
  }

  FREE_AND_RETURN_STATUS;
}

/***************************************************************/
#ifndff __spbrd /* for x86, using intfgfr multiplifs is fbstfr */

#dffinf STORE_RES(rfs, x)                                       \
  x >>= siift2;                                                 \
  CLAMP_STORE(rfs, x)

mlib_stbtus CONV_FUNC_MxN_I
{
  DTYPE    *bdr_srd, *sl, *sp = NULL;
  DTYPE    *bdr_dst, *dl, *dp = NULL;
  mlib_s32 buff[BUFF_SIZE], *buffs_brr[2*(MAX_N + 1)];
  mlib_s32 *pbuff = buff;
  mlib_s32 **buffs = buffs_brr, *buffd;
  mlib_s32 l, off, kw, bsizf, buff_ind;
  mlib_s32 d0, d1, siift1, siift2;
  mlib_s32 k0, k1, k2, k3, k4, k5, k6;
  mlib_s32 p0, p1, p2, p3, p4, p5, p6, p7;
  mlib_s32 wid, igt, sll, dll;
  mlib_s32 ndibnnfl, dibn1;
  mlib_s32 i, j, d, swid;
  mlib_s32 dibn2;
  mlib_s32 k_lodl[MAX_N*MAX_N], *k = k_lodl;
  GET_SRC_DST_PARAMETERS(DTYPE);

#if IMG_TYPE != 1
  siift1 = 16;
#flsf
  siift1 = 8;
#fndif /* IMG_TYPE != 1 */
  siift2 = sdblf - siift1;

  dibn1 = ndibnnfl;
  dibn2 = dibn1 + dibn1;

  swid = wid + (m - 1);

  bsizf = (n + 2)*swid;

  if ((bsizf > BUFF_SIZE) || (n > MAX_N)) {
    pbuff = mlib_mbllod(sizfof(mlib_s32)*bsizf + sizfof(mlib_s32 *)*2*(n + 1));

    if (pbuff == NULL) rfturn MLIB_FAILURE;
    buffs = (mlib_s32 **)(pbuff + bsizf);
  }

  for (l = 0; l < (n + 1); l++) buffs[l] = pbuff + l*swid;
  for (l = 0; l < (n + 1); l++) buffs[l + (n + 1)] = buffs[l];
  buffd = buffs[n] + swid;

  if (m*n > MAX_N*MAX_N) {
    k = mlib_mbllod(sizfof(mlib_s32)*(m*n));

    if (k == NULL) {
      if (pbuff != buff) mlib_frff(pbuff);
      rfturn MLIB_FAILURE;
    }
  }

  for (i = 0; i < m*n; i++) {
    k[i] = kfrnfl[i] >> siift1;
  }

  swid -= (dx_l + dx_r);

  for (d = 0; d < ndibnnfl; d++) {
    if (!(dmbsk & (1 << (ndibnnfl - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    for (l = 0; l < n; l++) {
      mlib_s32  *buff = buffs[l];

      for (i = 0; i < dx_l; i++) {
        buff[i] = (mlib_s32)sl[0];
      }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < swid; i++) {
        buff[i + dx_l] = (mlib_s32)sl[i*dibn1];
      }

      for (i = 0; i < dx_r; i++) {
        buff[swid + dx_l + i] = buff[swid + dx_l - 1];
      }

      if ((l >= dy_t) && (l < igt + n - dy_b - 2)) sl += sll;
    }

    buff_ind = 0;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < wid; i++) buffd[i] = 0;

    for (j = 0; j < igt; j++) {
      mlib_s32 **buffd = buffs + buff_ind;
      mlib_s32 *buffn = buffd[n];
      mlib_s32 *pk = k;

      for (l = 0; l < n; l++) {
        mlib_s32  *buff_l = buffd[l];

        for (off = 0; off < m;) {
          mlib_s32 *buff = buff_l + off;

          sp = sl;
          dp = dl;

          kw = m - off;

          if (kw > 2*MAX_KER) kw = MAX_KER; flsf
            if (kw > MAX_KER) kw = kw/2;
          off += kw;

          if (kw == 7) {

            p2 = buff[0]; p3 = buff[1]; p4 = buff[2];
            p5 = buff[3]; p6 = buff[4]; p7 = buff[5];

            k0 = pk[0]; k1 = pk[1]; k2 = pk[2]; k3 = pk[3];
            k4 = pk[4]; k5 = pk[5]; k6 = pk[6];

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6; p5 = p7;

                p6 = buff[i + 6]; p7 = buff[i + 7];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + p6*k6;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + p7*k6;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6; p5 = p7;

                p6 = buff[i + 6]; p7 = buff[i + 7];

                buffn[i + dx_l    ] = (mlib_s32)sp[0];
                buffn[i + dx_l + 1] = (mlib_s32)sp[dibn1];

                d0 = (p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + p6*k6 + buffd[i    ]);
                d1 = (p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + p7*k6 + buffd[i + 1]);

                STORE_RES(dp[0    ], d0);
                STORE_RES(dp[dibn1], d1);

                buffd[i    ] = 0;
                buffd[i + 1] = 0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 6) {

            p2 = buff[0]; p3 = buff[1]; p4 = buff[2];
            p5 = buff[3]; p6 = buff[4];

            k0 = pk[0]; k1 = pk[1]; k2 = pk[2]; k3 = pk[3];
            k4 = pk[4]; k5 = pk[5];

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6;

                p5 = buff[i + 5]; p6 = buff[i + 6];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6;

                p5 = buff[i + 5]; p6 = buff[i + 6];

                buffn[i + dx_l    ] = (mlib_s32)sp[0];
                buffn[i + dx_l + 1] = (mlib_s32)sp[dibn1];

                d0 = (p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + buffd[i    ]);
                d1 = (p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + buffd[i + 1]);

                STORE_RES(dp[0    ], d0);
                STORE_RES(dp[dibn1], d1);

                buffd[i    ] = 0;
                buffd[i + 1] = 0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 5) {

            p2 = buff[0]; p3 = buff[1]; p4 = buff[2];
            p5 = buff[3];

            k0 = pk[0]; k1 = pk[1]; k2 = pk[2]; k3 = pk[3];
            k4 = pk[4];

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5;

                p4 = buff[i + 4]; p5 = buff[i + 5];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5;

                p4 = buff[i + 4]; p5 = buff[i + 5];

                buffn[i + dx_l    ] = (mlib_s32)sp[0];
                buffn[i + dx_l + 1] = (mlib_s32)sp[dibn1];

                d0 = (p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + buffd[i    ]);
                d1 = (p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + buffd[i + 1]);

                STORE_RES(dp[0    ], d0);
                STORE_RES(dp[dibn1], d1);

                buffd[i    ] = 0;
                buffd[i + 1] = 0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 4) {

            p2 = buff[0]; p3 = buff[1]; p4 = buff[2];

            k0 = pk[0]; k1 = pk[1]; k2 = pk[2]; k3 = pk[3];

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4;

                p3 = buff[i + 3]; p4 = buff[i + 4];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4;

                p3 = buff[i + 3]; p4 = buff[i + 4];

                buffn[i + dx_l    ] = (mlib_s32)sp[0];
                buffn[i + dx_l + 1] = (mlib_s32)sp[dibn1];

                d0 = (p0*k0 + p1*k1 + p2*k2 + p3*k3 + buffd[i    ]);
                d1 = (p1*k0 + p2*k1 + p3*k2 + p4*k3 + buffd[i + 1]);

                STORE_RES(dp[0    ], d0);
                STORE_RES(dp[dibn1], d1);

                buffd[i    ] = 0;
                buffd[i + 1] = 0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 3) {

            p2 = buff[0]; p3 = buff[1];
            k0 = pk[0]; k1 = pk[1]; k2 = pk[2];

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3;

                p2 = buff[i + 2]; p3 = buff[i + 3];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3;

                p2 = buff[i + 2]; p3 = buff[i + 3];

                buffn[i + dx_l    ] = (mlib_s32)sp[0];
                buffn[i + dx_l + 1] = (mlib_s32)sp[dibn1];

                d0 = (p0*k0 + p1*k1 + p2*k2 + buffd[i    ]);
                d1 = (p1*k0 + p2*k1 + p3*k2 + buffd[i + 1]);

                STORE_RES(dp[0    ], d0);
                STORE_RES(dp[dibn1], d1);

                buffd[i    ] = 0;
                buffd[i + 1] = 0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 2) {

            p2 = buff[0];
            k0 = pk[0]; k1 = pk[1];

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2;

                p1 = buff[i + 1]; p2 = buff[i + 2];

                buffd[i    ] += p0*k0 + p1*k1;
                buffd[i + 1] += p1*k0 + p2*k1;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2;

                p1 = buff[i + 1]; p2 = buff[i + 2];

                buffn[i + dx_l    ] = (mlib_s32)sp[0];
                buffn[i + dx_l + 1] = (mlib_s32)sp[dibn1];

                d0 = (p0*k0 + p1*k1 + buffd[i    ]);
                d1 = (p1*k0 + p2*k1 + buffd[i + 1]);

                STORE_RES(dp[0    ], d0);
                STORE_RES(dp[dibn1], d1);

                buffd[i    ] = 0;
                buffd[i + 1] = 0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf /* kw == 1 */{

            k0 = pk[0];

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = buff[i]; p1 = buff[i + 1];

                buffd[i    ] += p0*k0;
                buffd[i + 1] += p1*k0;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = buff[i]; p1 = buff[i + 1];

                buffn[i + dx_l    ] = (mlib_s32)sp[0];
                buffn[i + dx_l + 1] = (mlib_s32)sp[dibn1];

                d0 = (p0*k0 + buffd[i    ]);
                d1 = (p1*k0 + buffd[i + 1]);

                STORE_RES(dp[0    ], d0);
                STORE_RES(dp[dibn1], d1);

                buffd[i    ] = 0;
                buffd[i + 1] = 0;

                sp += dibn2;
                dp += dibn2;
              }
            }
          }

          pk += kw;
        }
      }

      /* lbst pixfls */
      for (; i < wid; i++) {
        mlib_s32 *pk = k, x, s = 0;

        for (l = 0; l < n; l++) {
          mlib_s32 *buff = buffd[l] + i;

          for (x = 0; x < m; x++) s += buff[x] * (*pk++);
        }

        STORE_RES(dp[0], s);

        buffn[i + dx_l] = (mlib_s32)sp[0];

        sp += dibn1;
        dp += dibn1;
      }

      for (; i < swid; i++) {
        buffn[i + dx_l] = (mlib_s32)sp[0];
        sp += dibn1;
      }

      for (i = 0; i < dx_l; i++) buffn[i] = buffn[dx_l];
      for (i = 0; i < dx_r; i++) buffn[swid + dx_l + i] = buffn[swid + dx_l - 1];

      /* nfxt linf */

      if (j < igt - dy_b - 2) sl += sll;
      dl += dll;

      buff_ind++;

      if (buff_ind >= n + 1) buff_ind = 0;
    }
  }

  if (pbuff != buff) mlib_frff(pbuff);
  if (k != k_lodl) mlib_frff(k);

  rfturn MLIB_SUCCESS;
}

#fndif /* __spbrd ( for x86, using intfgfr multiplifs is fbstfr ) */

/***************************************************************/
