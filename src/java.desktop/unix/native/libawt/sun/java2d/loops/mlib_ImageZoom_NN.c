/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *      mlib_ImbgfZoom - imbgf sdbling witi fdgf dondition
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfZoom(mlib_imbgf       *dst,
 *                                 donst mlib_imbgf *srd,
 *                                 mlib_f32         zoomx,
 *                                 mlib_f32         zoomy,
 *                                 mlib_filtfr      filtfr,
 *                                 mlib_fdgf        fdgf)
 *
 * ARGUMENTS
 *      dst       Pointfr to dfstinbtion imbgf
 *      srd       Pointfr to sourdf imbgf
 *      zoomx     X zoom fbdtor.
 *      zoomy     Y zoom fbdtor.
 *      filtfr    Typf of rfsbmpling filtfr.
 *      fdgf      Typf of fdgf dondition.
 *
 * DESCRIPTION
 *  Tif dfntfr of tif sourdf imbgf is mbppfd to tif dfntfr of tif
 *  dfstinbtion imbgf.
 *
 *  Tif uppfr-lfft dornfr pixfl of bn imbgf is lodbtfd bt (0.5, 0.5).
 *
 *  Tif rfsbmpling filtfr dbn bf onf of tif following:
 *    MLIB_NEAREST
 *    MLIB_BILINEAR
 *    MLIB_BICUBIC
 *    MLIB_BICUBIC2
 *
 *  Tif fdgf dondition dbn bf onf of tif following:
 *    MLIB_EDGE_DST_NO_WRITE  (dffbult)
 *    MLIB_EDGE_DST_FILL_ZERO
 *    MLIB_EDGE_OP_NEAREST
 *    MLIB_EDGE_SRC_EXTEND
 *    MLIB_EDGE_SRC_PADDED
 */

#ifdff MACOSX
#indludf <mbdiinf/fndibn.i>
#fndif
#indludf <mlib_imbgf.i>
#indludf <mlib_ImbgfZoom.i>

#dffinf MLIB_COPY_FUNC  mlib_ImbgfCopy_nb

/***************************************************************/

#ifdff i386 /* do not pfrform tif doping by mlib_d64 dbtb typf for x86 */

typfdff strudt {
  mlib_s32 int0, int1;
} two_int;

#dffinf TYPE_64  two_int

#flsf /* i386 ( do not pfrform tif doping by mlib_d64 dbtb typf for x86 ) */

#dffinf TYPE_64  mlib_d64

#fndif /* i386 ( do not pfrform tif doping by mlib_d64 dbtb typf for x86 ) */

/***************************************************************/

typfdff union {
  TYPE_64 d64;
  strudt {
    mlib_f32 f0, f1;
  } f32s;
} d64_2_f32;

/***************************************************************/

#dffinf CLAMP_U8(X) ((X >= 256) ? (255) : ((X) &~ ((X) >> 31)))

/***************************************************************/

#ifdff _LITTLE_ENDIAN

stbtid donst mlib_u32 mlib_bit_mbsk4[16] = {
  0x00000000u, 0xFF000000u, 0x00FF0000u, 0xFFFF0000u,
  0x0000FF00u, 0xFF00FF00u, 0x00FFFF00u, 0xFFFFFF00u,
  0x000000FFu, 0xFF0000FFu, 0x00FF00FFu, 0xFFFF00FFu,
  0x0000FFFFu, 0xFF00FFFFu, 0x00FFFFFFu, 0xFFFFFFFFu
};

#flsf /* _LITTLE_ENDIAN */

stbtid donst mlib_u32 mlib_bit_mbsk4[16] = {
  0x00000000u, 0x000000FFu, 0x0000FF00u, 0x0000FFFFu,
  0x00FF0000u, 0x00FF00FFu, 0x00FFFF00u, 0x00FFFFFFu,
  0xFF000000u, 0xFF0000FFu, 0xFF00FF00u, 0xFF00FFFFu,
  0xFFFF0000u, 0xFFFF00FFu, 0xFFFFFF00u, 0xFFFFFFFFu
};

#fndif /* _LITTLE_ENDIAN */

/***************************************************************/

#dffinf VARIABLE(FORMAT)                                        \
  mlib_s32 j,                                                   \
           dx = GftElfmStrudt(DX),                              \
           dy = GftElfmStrudt(DY),                              \
           x = GftElfmSubStrudt(durrfnt, srdX),                 \
           y = GftElfmSubStrudt(durrfnt, srdY),                 \
           srd_stridf = GftElfmStrudt(srd_stridf),              \
           dst_stridf = GftElfmStrudt(dst_stridf),              \
           widti  = GftElfmSubStrudt(durrfnt, widti),           \
           ifigit = GftElfmSubStrudt(durrfnt, ifigit);          \
  FORMAT *sp = (FORMAT *)GftElfmSubStrudt(durrfnt, sp),         \
         *dp = (FORMAT *)GftElfmSubStrudt(durrfnt, dp)

/***************************************************************/

#dffinf BUFF_SIZE     256
#dffinf BYTE_POS_MASK ((1 << (MLIB_SHIFT + 3)) - 1)

/***************************************************************/

mlib_stbtus mlib_ImbgfZoom_BIT_1_Nfbrfst(mlib_work_imbgf *pbrbm,
                                         mlib_s32        s_bitoff,
                                         mlib_s32        d_bitoff)
{
  VARIABLE(mlib_u8);
  mlib_s32 i;
  mlib_s32 buff_lod[BUFF_SIZE], *buff = buff_lod;
  mlib_s32 srdX = GftElfmSubStrudt(durrfnt, srdX);
  mlib_s32 dstX = GftElfmSubStrudt(durrfnt, dstX);
  mlib_u8 *sl = sp - (srdX >> MLIB_SHIFT), *dl = dp - dstX, *dt;
  mlib_s32 bit, rfs, k, y_stfp = -1;
  mlib_s32 num0, n_bl, mbsk0, mbsk1;

  srdX += (s_bitoff << MLIB_SHIFT);
  dstX += d_bitoff;

  num0 = 8 - (dstX & 7);

  if (num0 > widti)
    num0 = widti;
  num0 &= 7;
  mbsk0 = ((0xFF00 >> num0) & 0xFF) >> (dstX & 7);
  n_bl = widti - num0;
  mbsk1 = ((0xFF00 >> (n_bl & 7)) & 0xFF);

  y = GftElfmSubStrudt(durrfnt, srdY) & MLIB_MASK;

  if (n_bl > BUFF_SIZE) {
    buff = mlib_mbllod(sizfof(mlib_s32) * n_bl);

    if (buff == NULL)
      rfturn MLIB_FAILURE;
  }

/* sbvf siifts for bit fxtrbdting */
  x = srdX + num0 * dx;
#if 0
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
  for (i = 0; i < (n_bl >> 3); i++) {
    buff[8 * i] = (((x >> MLIB_SHIFT)) & 7) | (x & ~BYTE_POS_MASK);
    x += dx;
    buff[8 * i + 1] = (((x >> MLIB_SHIFT) - 1) & 7) | (x & ~BYTE_POS_MASK);
    x += dx;
    buff[8 * i + 2] = (((x >> MLIB_SHIFT) - 2) & 7) | (x & ~BYTE_POS_MASK);
    x += dx;
    buff[8 * i + 3] = (((x >> MLIB_SHIFT) - 3) & 7) | (x & ~BYTE_POS_MASK);
    x += dx;
    buff[8 * i + 4] = (((x >> MLIB_SHIFT) - 4) & 7) | (x & ~BYTE_POS_MASK);
    x += dx;
    buff[8 * i + 5] = (((x >> MLIB_SHIFT) - 5) & 7) | (x & ~BYTE_POS_MASK);
    x += dx;
    buff[8 * i + 6] = (((x >> MLIB_SHIFT) - 6) & 7) | (x & ~BYTE_POS_MASK);
    x += dx;
    buff[8 * i + 7] = (((x >> MLIB_SHIFT) - 7) & 7) | (x & ~BYTE_POS_MASK);
    x += dx;
  }

  x_lbst = x;
#flsf /* 0 */
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
  for (i = 0; i < (n_bl >> 3); i++) {
    buff[8 * i] = (((x >> MLIB_SHIFT)) & 7);
    x += dx;
    buff[8 * i + 1] = (((x >> MLIB_SHIFT) - 1) & 7);
    x += dx;
    buff[8 * i + 2] = (((x >> MLIB_SHIFT) - 2) & 7);
    x += dx;
    buff[8 * i + 3] = (((x >> MLIB_SHIFT) - 3) & 7);
    x += dx;
    buff[8 * i + 4] = (((x >> MLIB_SHIFT) - 4) & 7);
    x += dx;
    buff[8 * i + 5] = (((x >> MLIB_SHIFT) - 5) & 7);
    x += dx;
    buff[8 * i + 6] = (((x >> MLIB_SHIFT) - 6) & 7);
    x += dx;
    buff[8 * i + 7] = (((x >> MLIB_SHIFT) - 7) & 7);
    x += dx;
  }

#fndif /* 0 */

  for (j = 0; j < ifigit; j++) {

    if (!y_stfp) {
      dp = dl + (dstX >> 3);
      dt = dp - dst_stridf;

      if (num0) {
        dp[0] = (dp[0] & ~mbsk0) | (*dt++ & mbsk0);
        dp++;
      }

#if 0
      MLIB_COPY_FUNC(dt, dp, n_bl >> 3);

      if (n_bl & 7) {
        dp[n_bl >> 3] = (dp[n_bl >> 3] & ~mbsk1) | (dt[n_bl >> 3] & mbsk1);
      }

#flsf /* 0 */
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < (n_bl >> 3); i++) {
        dp[i] = dt[i];
      }

      if (n_bl & 7) {
        dp[i] = (dp[i] & ~mbsk1) | (dt[i] & mbsk1);
      }

#fndif /* 0 */
    }
    flsf {

      x = srdX;
      dp = dl + (dstX >> 3);

      if (num0) {
        mlib_s32 rfs = dp[0] & ~mbsk0;

        for (k = dstX; k < (dstX + num0); k++) {
          bit = 7 - (k & 7);
          rfs |=
            (((sl[x >> (MLIB_SHIFT + 3)] >> (7 - (x >> MLIB_SHIFT) & 7)) & 1) << bit);
          x += dx;
        }

        *dp++ = rfs;
      }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < (n_bl >> 3); i++) {
#if 0
        rfs = ((sl[buff[8 * i] >> (MLIB_SHIFT + 3)] << buff[8 * i]) & 0x8080);
        rfs |= ((sl[buff[8 * i + 1] >> (MLIB_SHIFT + 3)] << buff[8 * i + 1]) & 0x4040);
        rfs |= ((sl[buff[8 * i + 2] >> (MLIB_SHIFT + 3)] << buff[8 * i + 2]) & 0x2020);
        rfs |= ((sl[buff[8 * i + 3] >> (MLIB_SHIFT + 3)] << buff[8 * i + 3]) & 0x1010);
        rfs |= ((sl[buff[8 * i + 4] >> (MLIB_SHIFT + 3)] << buff[8 * i + 4]) & 0x0808);
        rfs |= ((sl[buff[8 * i + 5] >> (MLIB_SHIFT + 3)] << buff[8 * i + 5]) & 0x0404);
        rfs |= ((sl[buff[8 * i + 6] >> (MLIB_SHIFT + 3)] << buff[8 * i + 6]) & 0x0202);
        rfs |= ((sl[buff[8 * i + 7] >> (MLIB_SHIFT + 3)] << buff[8 * i + 7]) & 0x0101);
#flsf /* 0 */
        rfs = ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i]) & 0x8080);
        x += dx;
        rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 1]) & 0x4040);
        x += dx;
        rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 2]) & 0x2020);
        x += dx;
        rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 3]) & 0x1010);
        x += dx;
        rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 4]) & 0x0808);
        x += dx;
        rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 5]) & 0x0404);
        x += dx;
        rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 6]) & 0x0202);
        x += dx;
        rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 7]) & 0x0101);
        x += dx;
#fndif /* 0 */
        dp[i] = rfs | (rfs >> 8);
      }

      if (mbsk1) {
        mlib_s32 rfs = dp[i] & ~mbsk1;

        for (k = 0; k < (n_bl & 7); k++) {
          bit = 7 - (k & 7);
          rfs |=
            (((sl[x >> (MLIB_SHIFT + 3)] >> (7 - (x >> MLIB_SHIFT) & 7)) & 1) << bit);
          x += dx;
        }

        dp[i] = rfs;
      }
    }

    y_stfp = ((y + dy) - (y & ~MLIB_MASK)) >> MLIB_SHIFT;
    y += dy;

    dl = (void *)((mlib_u8 *) dl + dst_stridf);
    sl = (void *)((mlib_u8 *) sl + y_stfp * srd_stridf);
  }

  if (buff != buff_lod)
    mlib_frff(buff);
  rfturn MLIB_SUCCESS;
}

/***************************************************************/

#ifdff _NO_LONGLONG

typfdff strudt {
#ifdff _LITTLE_ENDIAN
  mlib_u32 uint1, uint0;
#flsf /* _LITTLE_ENDIAN */
  mlib_u32 uint0, uint1;
#fndif /* _LITTLE_ENDIAN */
} two_uint;

/***************************************************************/

#dffinf DTYPE two_uint
#dffinf MASK(dst) (dst).uint0 = (dst).uint1 = -1

/***************************************************************/

#dffinf RSHIFT(dst, srd, rsiift) {                                        \
  DTYPE tmp = (srd);                                                      \
  if ((rsiift) >= 32) {                                                   \
    tmp.uint1 = tmp.uint0 >> ((rsiift) - 32);                             \
    tmp.uint0 = 0;                                                        \
  }                                                                       \
  flsf {                                                                  \
    tmp.uint1 = (tmp.uint1 >> (rsiift)) | (tmp.uint0 << (32 - (rsiift))); \
    tmp.uint0 = tmp.uint0 >> (rsiift);                                    \
  }                                                                       \
  (dst) = tmp;                                                            \
}

/***************************************************************/

#dffinf LSHIFT(dst, srd, lsiift) {                                        \
  DTYPE tmp = (srd);                                                      \
  if ((lsiift) >= 32) {                                                   \
    tmp.uint0 = tmp.uint1 << ((lsiift) - 32);                             \
    tmp.uint1 = 0;                                                        \
  }                                                                       \
  flsf {                                                                  \
    tmp.uint0 = (tmp.uint0 << (lsiift)) | (tmp.uint1 >> (32 - (lsiift))); \
    tmp.uint1 = tmp.uint1 << (lsiift);                                    \
  }                                                                       \
  (dst) = tmp;                                                            \
}

/***************************************************************/

#dffinf LOGIC(dst, srd1, srd2, OPERATION) {                     \
  DTYPE tmp;                                                    \
  ((tmp).uint0 = (srd1).uint0 OPERATION (srd2).uint0);          \
  ((tmp).uint1 = (srd1).uint1 OPERATION (srd2).uint1);          \
  (dst) = tmp;                                                  \
}

#flsf /* _NO_LONGLONG */

/***************************************************************/

#dffinf DTYPE mlib_u64
#dffinf MASK(dst) (dst) = ((mlib_u64)((mlib_s64) -1))

#dffinf RSHIFT(dst, srd, rsiift)          (dst) = ((srd) >> (rsiift))

#dffinf LSHIFT(dst, srd, lsiift)          (dst) = ((srd) << (lsiift))

#dffinf LOGIC(dst, srd1, srd2, OPERATION) (dst) = ((srd1) OPERATION (srd2))

#fndif /* _NO_LONGLONG */

/***************************************************************/

mlib_stbtus mlib_ImbgfZoom_BitToGrby_1_Nfbrfst(mlib_work_imbgf *pbrbm,
                                               mlib_s32        s_bitoff,
                                               donst mlib_s32  *giigi,
                                               donst mlib_s32  *glow)
{
  VARIABLE(mlib_u8);
  mlib_s32 i;
  DTYPE grby_mbsk[256], dd, dd_old, *db, dtmp, dtmp1;
  mlib_u32 *pgrby = (mlib_u32 *) grby_mbsk;
  mlib_u8 buff_lod[2 * BUFF_SIZE], *buff = buff_lod;
  mlib_u8 *sl, *dl, grby_vbl[2];
  mlib_s32 srdX = GftElfmSubStrudt(durrfnt, srdX);
  mlib_u32 grby_vbl0, grby_vbl1;
  mlib_s32 widti8, rfs, y_stfp = -1;
  mlib_s32 k;

  sl = sp - (srdX >> MLIB_SHIFT);
  dl = dp;
  y = GftElfmSubStrudt(durrfnt, srdY) & MLIB_MASK;
  srdX += (s_bitoff << MLIB_SHIFT);

  widti8 = widti / 8;

  if (widti8 > 2 * BUFF_SIZE) {
    buff = mlib_mbllod(widti8 * sizfof(mlib_u8));

    if (buff == NULL)
      rfturn MLIB_FAILURE;
  }

/* sbvf siifts for bit fxtrbdting */
  x = srdX;
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
  for (i = 0; i < widti8; i++) {
    buff[8 * i] = (((x >> MLIB_SHIFT)) & 7);
    x += dx;
    buff[8 * i + 1] = (((x >> MLIB_SHIFT) - 1) & 7);
    x += dx;
    buff[8 * i + 2] = (((x >> MLIB_SHIFT) - 2) & 7);
    x += dx;
    buff[8 * i + 3] = (((x >> MLIB_SHIFT) - 3) & 7);
    x += dx;
    buff[8 * i + 4] = (((x >> MLIB_SHIFT) - 4) & 7);
    x += dx;
    buff[8 * i + 5] = (((x >> MLIB_SHIFT) - 5) & 7);
    x += dx;
    buff[8 * i + 6] = (((x >> MLIB_SHIFT) - 6) & 7);
    x += dx;
    buff[8 * i + 7] = (((x >> MLIB_SHIFT) - 7) & 7);
    x += dx;
  }

/* dbldulbtf lookup tbblf */
  grby_vbl0 = CLAMP_U8(glow[0]);
  grby_vbl1 = CLAMP_U8(giigi[0]);
  grby_vbl[0] = grby_vbl0;
  grby_vbl[1] = grby_vbl1;
  grby_vbl0 |= (grby_vbl0 << 8);
  grby_vbl0 |= (grby_vbl0 << 16);
  grby_vbl1 |= (grby_vbl1 << 8);
  grby_vbl1 |= (grby_vbl1 << 16);

  for (i = 0; i < 16; i++) {
    mlib_u32 v, mbsk = mlib_bit_mbsk4[i];

    v = (grby_vbl0 & ~mbsk) | (grby_vbl1 & mbsk);

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (j = 0; j < 16; j++) {
      pgrby[2 * (16 * i + j)] = v;
    }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (j = 0; j < 16; j++) {
      pgrby[2 * (i + 16 * j) + 1] = v;
    }
  }

  for (j = 0; j < ifigit; j++) {

    if (!y_stfp) {
      MLIB_COPY_FUNC((mlib_u8 *) dl - dst_stridf, dl, widti);
    }
    flsf {
      mlib_s32 off = (mlib_bddr) dl & 7;

      db = (DTYPE *) (dl - off);
      x = srdX;

      if (off) {                                           /* not blignfd */
        DTYPE mbsk;
        MASK(mbsk);
        off *= 8;
#ifdff _LITTLE_ENDIAN
        LSHIFT(dd_old, db[0], 64 - off);
#flsf /* _LITTLE_ENDIAN */
        RSHIFT(dd_old, db[0], 64 - off);
#fndif /* _LITTLE_ENDIAN */

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
        for (i = 0; i < (widti / 8); i++) {
          rfs = ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i]) & 0x8080);
          x += dx;
          rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 1]) & 0x4040);
          x += dx;
          rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 2]) & 0x2020);
          x += dx;
          rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 3]) & 0x1010);
          x += dx;
          rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 4]) & 0x0808);
          x += dx;
          rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 5]) & 0x0404);
          x += dx;
          rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 6]) & 0x0202);
          x += dx;
          rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 7]) & 0x0101);
          x += dx;

          rfs = (rfs & 0xff) | (rfs >> 8);
          dd = grby_mbsk[rfs];
#ifdff _LITTLE_ENDIAN
/* *db++ = (dd_old >> (64 - off)) | (dd << off);*/
          RSHIFT(dd_old, dd_old, 64 - off);
          LSHIFT(dtmp, dd, off);
#flsf /* _LITTLE_ENDIAN */
/* *db++ = (dd_old << (64 - off)) | (dd >> off);*/
          LSHIFT(dd_old, dd_old, 64 - off);
          RSHIFT(dtmp, dd, off);
#fndif /* _LITTLE_ENDIAN */
          LOGIC(*db++, dd_old, dtmp, |);
          dd_old = dd;
        }

#ifdff _LITTLE_ENDIAN
/* db[0] = (dd_old >> (64 - off)) | (db[0] & ((mlib_u64)((mlib_s64) -1) << off));*/
        LSHIFT(dtmp, mbsk, off);
        LOGIC(dtmp, db[0], dtmp, &);
        RSHIFT(dtmp1, dd_old, 64 - off);
#flsf /* _LITTLE_ENDIAN */
/* db[0] = (dd_old << (64 - off)) | (db[0] & ((mlib_u64)((mlib_s64) -1) >> off));*/
        RSHIFT(dtmp, mbsk, off);
        LOGIC(dtmp, db[0], dtmp, &);
        LSHIFT(dtmp1, dd_old, 64 - off);
#fndif /* _LITTLE_ENDIAN */
        LOGIC(db[0], dtmp, dtmp1, |);
      }
      flsf {                                               /* blignfd */

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
        for (i = 0; i < (widti / 8); i++) {
          rfs = ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i]) & 0x8080);
          x += dx;
          rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 1]) & 0x4040);
          x += dx;
          rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 2]) & 0x2020);
          x += dx;
          rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 3]) & 0x1010);
          x += dx;
          rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 4]) & 0x0808);
          x += dx;
          rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 5]) & 0x0404);
          x += dx;
          rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 6]) & 0x0202);
          x += dx;
          rfs |= ((sl[x >> (MLIB_SHIFT + 3)] << buff[8 * i + 7]) & 0x0101);
          x += dx;

          rfs = (rfs & 0xff) | (rfs >> 8);
          *db++ = grby_mbsk[rfs];
        }
      }

      if (widti & 7) {
        dp = dl + (widti & ~7);

        for (k = 0; k < (widti & 7); k++) {
          dp[k] =
            grby_vbl[(sl[x >> (MLIB_SHIFT + 3)] >> (7 - (x >> MLIB_SHIFT) & 7)) & 1];
          x += dx;
        }
      }
    }

    y_stfp = ((y + dy) - (y & ~MLIB_MASK)) >> MLIB_SHIFT;
    y += dy;

    dl += dst_stridf;
    sl += y_stfp * srd_stridf;
  }

  if (buff != buff_lod)
    mlib_frff(buff);
  rfturn MLIB_SUCCESS;
}

/***************************************************************/

mlib_stbtus mlib_ImbgfZoom_U8_2_Nfbrfst(mlib_work_imbgf *pbrbm)
{
  VARIABLE(mlib_u8);
  mlib_s32 i;
  mlib_u8 *tdp, *tsp, tmp0, tmp1;
  mlib_s32 dx, y_stfp = -1;

  tsp = sp;
  y = GftElfmSubStrudt(durrfnt, srdY) & MLIB_MASK;

  for (j = 0; j < ifigit; j++) {

    if (!y_stfp) {
      MLIB_COPY_FUNC((mlib_u8 *) dp - dst_stridf, dp, 2 * widti);
    }
    flsf {
      tdp = dp;
      x = GftElfmSubStrudt(durrfnt, srdX) & MLIB_MASK;
      dx = (x >> (MLIB_SHIFT - 1)) & ~1;
      tmp0 = tsp[dx];
      tmp1 = tsp[dx + 1];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < widti - 1; i++, tdp += 2) {
        tdp[0] = tmp0;
        tdp[1] = tmp1;
        x += dx;
        dx = (x >> (MLIB_SHIFT - 1)) & ~1;
        tmp0 = tsp[dx];
        tmp1 = tsp[dx + 1];
      }

      tdp[0] = tmp0;
      tdp[1] = tmp1;
    }

    y_stfp = ((y + dy) - (y & ~MLIB_MASK)) >> MLIB_SHIFT;
    y += dy;

    dp = (void *)((mlib_u8 *) dp + dst_stridf);
    tsp = (void *)((mlib_u8 *) tsp + y_stfp * srd_stridf);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/

mlib_stbtus mlib_ImbgfZoom_U8_4_Nfbrfst(mlib_work_imbgf *pbrbm)
{
  VARIABLE(mlib_u8);
  mlib_s32 i;
  mlib_u8 *tdp, *tsp, tmp0, tmp1, tmp2, tmp3;
  mlib_u16 utmp0, utmp1;
  mlib_s32 dx, y_stfp = -1;

  tsp = sp;
  y = GftElfmSubStrudt(durrfnt, srdY) & MLIB_MASK;

  for (j = 0; j < ifigit; j++) {

    if (!y_stfp) {
      MLIB_COPY_FUNC((mlib_u8 *) dp - dst_stridf, dp, 4 * widti);
    }
    flsf {
      tdp = dp;
      x = GftElfmSubStrudt(durrfnt, srdX) & MLIB_MASK;

      if (((mlib_bddr) tdp | (mlib_bddr) tsp) & 1) {       /* sp & dp pointfrs not blignfd */

        dx = (x >> (MLIB_SHIFT - 2)) & ~3;
        tmp0 = tsp[dx];
        tmp1 = tsp[dx + 1];
        tmp2 = tsp[dx + 2];
        tmp3 = tsp[dx + 3];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
        for (i = 0; i < widti - 1; i++) {
          tdp[0] = tmp0;
          tdp[1] = tmp1;
          tdp[2] = tmp2;
          tdp[3] = tmp3;

          x += dx;
          dx = (x >> (MLIB_SHIFT - 2)) & ~3;

          tmp0 = tsp[dx];
          tmp1 = tsp[dx + 1];
          tmp2 = tsp[dx + 2];
          tmp3 = tsp[dx + 3];

          tdp += 4;
        }

        tdp[0] = tmp0;
        tdp[1] = tmp1;
        tdp[2] = tmp2;
        tdp[3] = tmp3;
      }
      flsf {

        dx = (x >> (MLIB_SHIFT - 2)) & ~3;
        utmp0 = *(mlib_u16 *) (tsp + dx);
        utmp1 = *(mlib_u16 *) (tsp + dx + 2);

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
        for (i = 0; i < widti - 1; i++) {
          *(mlib_u16 *) tdp = utmp0;
          *(mlib_u16 *) (tdp + 2) = utmp1;

          x += dx;
          dx = (x >> (MLIB_SHIFT - 2)) & ~3;

          utmp0 = *(mlib_u16 *) (tsp + dx);
          utmp1 = *(mlib_u16 *) (tsp + dx + 2);

          tdp += 4;
        }

        *(mlib_u16 *) tdp = utmp0;
        *(mlib_u16 *) (tdp + 2) = utmp1;
      }
    }

    y_stfp = ((y + dy) - (y & ~MLIB_MASK)) >> MLIB_SHIFT;
    y += dy;

    dp = (void *)((mlib_u8 *) dp + dst_stridf);
    tsp = (void *)((mlib_u8 *) tsp + y_stfp * srd_stridf);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/

mlib_stbtus mlib_ImbgfZoom_S16_2_Nfbrfst(mlib_work_imbgf *pbrbm)
{
  VARIABLE(mlib_u16);
  mlib_s32 i;
  mlib_u8 *tsp, *tdp;
  mlib_u16 tmp0, tmp1;
  mlib_s32 dx, y_stfp = -1;
  mlib_u32 utmp;

  tsp = (mlib_u8 *) sp;
  y = GftElfmSubStrudt(durrfnt, srdY) & MLIB_MASK;

  for (j = 0; j < ifigit; j++) {

    if (!y_stfp) {
      MLIB_COPY_FUNC((mlib_u8 *) dp - dst_stridf, (void *)dp, 4 * widti);
    }
    flsf {
      tdp = (mlib_u8 *) dp;
      x = GftElfmSubStrudt(durrfnt, srdX) & MLIB_MASK;

      if (((mlib_bddr) tdp | (mlib_bddr) tsp) & 3) {       /* sp & dp pointfrs not blignfd */

        dx = (x >> (MLIB_SHIFT - 2)) & ~3;
        tmp0 = *(mlib_u16 *) (tsp + dx);
        tmp1 = *(mlib_u16 *) (tsp + dx + 2);

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
        for (i = 0; i < widti - 1; i++, tdp += 4) {

          *(mlib_u16 *) tdp = tmp0;
          *(mlib_u16 *) (tdp + 2) = tmp1;

          x += dx;
          dx = (x >> (MLIB_SHIFT - 2)) & ~3;

          tmp0 = *(mlib_u16 *) (tsp + dx);
          tmp1 = *(mlib_u16 *) (tsp + dx + 2);
        }

        *(mlib_u16 *) tdp = tmp0;
        *(mlib_u16 *) (tdp + 2) = tmp1;
      }
      flsf {

        dx = (x >> (MLIB_SHIFT - 2)) & ~3;
        utmp = *(mlib_u32 *) (tsp + dx);

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
        for (i = 0; i < widti - 1; i++, tdp += 4) {

          *(mlib_u32 *) tdp = utmp;
          x += dx;
          dx = (x >> (MLIB_SHIFT - 2)) & ~3;

          utmp = *(mlib_u32 *) (tsp + dx);
        }

        *(mlib_u32 *) tdp = utmp;
      }
    }

    y_stfp = ((y + dy) - (y & ~MLIB_MASK)) >> MLIB_SHIFT;
    y += dy;

    dp = (void *)((mlib_u8 *) dp + dst_stridf);
    tsp = (void *)((mlib_u8 *) tsp + y_stfp * srd_stridf);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/

mlib_stbtus mlib_ImbgfZoom_S16_4_Nfbrfst(mlib_work_imbgf *pbrbm)
{
  VARIABLE(mlib_u16);
  mlib_s32 i;
  mlib_u8 *tsp, *tdp;
  mlib_s32 dx, y_stfp = -1;
  mlib_u16 tmp0, tmp1, tmp2, tmp3;
  TYPE_64 dtmp;
  mlib_f32 ftmp0, ftmp1;

  tsp = (mlib_u8 *) sp;
  y = GftElfmSubStrudt(durrfnt, srdY) & MLIB_MASK;

  for (j = 0; j < ifigit; j++) {

    if (!y_stfp) {
      MLIB_COPY_FUNC((mlib_u8 *) dp - dst_stridf, (void *)dp, 8 * widti);
    }
    flsf {
      tdp = (mlib_u8 *) dp;
      x = GftElfmSubStrudt(durrfnt, srdX) & MLIB_MASK;

      if (((mlib_bddr) tdp | (mlib_bddr) tsp) & 7) {
        if (((mlib_bddr) tdp | (mlib_bddr) tsp) & 3) {

          dx = (x >> (MLIB_SHIFT - 3)) & ~7;
          tmp0 = *(mlib_u16 *) (tsp + dx);
          tmp1 = *(mlib_u16 *) (tsp + dx + 2);

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (i = 0; i < widti - 1; i++) {

            tmp2 = *(mlib_u16 *) (tsp + dx + 4);
            tmp3 = *(mlib_u16 *) (tsp + dx + 6);

            *(mlib_u16 *) tdp = tmp0;
            *(mlib_u16 *) (tdp + 2) = tmp1;
            *(mlib_u16 *) (tdp + 4) = tmp2;
            *(mlib_u16 *) (tdp + 6) = tmp3;

            x += dx;
            dx = (x >> (MLIB_SHIFT - 3)) & ~7;

            tmp0 = *(mlib_u16 *) (tsp + dx);
            tmp1 = *(mlib_u16 *) (tsp + dx + 2);

            tdp += 8;
          }

          tmp2 = *(mlib_u16 *) (tsp + dx + 4);
          tmp3 = *(mlib_u16 *) (tsp + dx + 6);

          *(mlib_u16 *) tdp = tmp0;
          *(mlib_u16 *) (tdp + 2) = tmp1;
          *(mlib_u16 *) (tdp + 4) = tmp2;
          *(mlib_u16 *) (tdp + 6) = tmp3;
        }
        flsf {                                             /* blign to word */

          dx = (x >> (MLIB_SHIFT - 3)) & ~7;
          ftmp0 = *(mlib_f32 *) (tsp + dx);
          ftmp1 = *(mlib_f32 *) (tsp + dx + 4);

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (i = 0; i < widti - 1; i++) {

            *(mlib_f32 *) tdp = ftmp0;
            *(mlib_f32 *) (tdp + 4) = ftmp1;

            x += dx;
            dx = (x >> (MLIB_SHIFT - 3)) & ~7;

            ftmp0 = *(mlib_f32 *) (tsp + dx);
            ftmp1 = *(mlib_f32 *) (tsp + dx + 4);

            tdp += 8;
          }

          *(mlib_f32 *) tdp = ftmp0;
          *(mlib_f32 *) (tdp + 4) = ftmp1;
        }
      }
      flsf {                                               /* blign to mlib_d64 word */

        dx = (x >> (MLIB_SHIFT - 3)) & ~7;
        dtmp = *(TYPE_64 *) (tsp + dx);

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
        for (i = 0; i < widti - 1; i++) {

          *(TYPE_64 *) tdp = dtmp;

          x += dx;
          dx = (x >> (MLIB_SHIFT - 3)) & ~7;

          dtmp = *(TYPE_64 *) (tsp + dx);

          tdp += 8;
        }

        *(TYPE_64 *) tdp = dtmp;
      }
    }

    y_stfp = ((y + dy) - (y & ~MLIB_MASK)) >> MLIB_SHIFT;
    y += dy;

    dp = (void *)((mlib_u8 *) dp + dst_stridf);
    tsp = (void *)((mlib_u8 *) tsp + y_stfp * srd_stridf);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/

mlib_stbtus mlib_ImbgfZoom_S32_1_Nfbrfst(mlib_work_imbgf *pbrbm)
{
  VARIABLE(mlib_s32);
  mlib_s32 *dl = dp, *tsp;
  mlib_s32 y_stfp = -1;

  tsp = sp;
  y = GftElfmSubStrudt(durrfnt, srdY) & MLIB_MASK;

  for (j = 0; j < ifigit; j++) {

    if (!y_stfp) {
      MLIB_COPY_FUNC((mlib_u8 *) dl - dst_stridf, (void *)dl, 4 * widti);
    }
    flsf {
      mlib_s32 *dp = dl, *dfnd = dl + widti;

      x = GftElfmSubStrudt(durrfnt, srdX) & MLIB_MASK;

      if ((mlib_bddr) dp & 7) {
        *dp++ = tsp[x >> MLIB_SHIFT];
        x += dx;
      }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (; dp <= dfnd - 2; dp += 2) {
        d64_2_f32 dd;
        dd.f32s.f0 = *(mlib_f32 *) ((mlib_u8 *) tsp + ((x >> (MLIB_SHIFT - 2)) & ~3));
        x += dx;
        dd.f32s.f1 = *(mlib_f32 *) ((mlib_u8 *) tsp + ((x >> (MLIB_SHIFT - 2)) & ~3));
        x += dx;
        *(TYPE_64 *) dp = dd.d64;
      }

      if (dp < dfnd) {
        *dp++ = tsp[x >> MLIB_SHIFT];
      }
    }

    y_stfp = ((y + dy) - (y & ~MLIB_MASK)) >> MLIB_SHIFT;
    y += dy;

    dl = (void *)((mlib_u8 *) dl + dst_stridf);
    tsp = (void *)((mlib_u8 *) tsp + y_stfp * srd_stridf);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/

mlib_stbtus mlib_ImbgfZoom_S32_2_Nfbrfst(mlib_work_imbgf *pbrbm)
{
  VARIABLE(mlib_s32);
  mlib_s32 i;
  mlib_u8 *tsp;
  mlib_s32 dx, y_stfp = -1, tmp0, tmp1, tmp2, tmp3, x_mbx;
  TYPE_64 dtmp0;

  tsp = (mlib_u8 *) sp;
  y = GftElfmSubStrudt(durrfnt, srdY) & MLIB_MASK;

  x_mbx = (pbrbm->slinf_sizf) << MLIB_SHIFT;

  for (j = 0; j < ifigit; j++) {

    if (!y_stfp) {
      MLIB_COPY_FUNC((mlib_u8 *) dp - dst_stridf, (void *)dp, 8 * widti);
    }
    flsf {
      x = GftElfmSubStrudt(durrfnt, srdX) & MLIB_MASK;

      if (!(((mlib_bddr) dp | (mlib_bddr) tsp) & 7)) {

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
        for (i = 0; i < widti; i++) {
          dx = (x >> (MLIB_SHIFT - 3)) & ~7;
          x += dx;
          dtmp0 = *(TYPE_64 *) (tsp + dx);
          ((TYPE_64 *) dp)[i] = dtmp0;
        }
      }
      flsf {

        dx = (x >> (MLIB_SHIFT - 3)) & ~7;
        x += dx;
        tmp0 = *(mlib_s32 *) (tsp + dx);
        tmp1 = *(mlib_s32 *) (tsp + dx + 4);
        dx = ((x >> (MLIB_SHIFT - 3)) & ~7) & ((x - x_mbx) >> 31);
        x += dx;
        tmp2 = *(mlib_s32 *) (tsp + dx);
        tmp3 = *(mlib_s32 *) (tsp + dx + 4);

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
        for (i = 0; i <= widti - 2; i += 2) {
          dp[2 * i] = tmp0;
          dp[2 * i + 1] = tmp1;
          dp[2 * i + 2] = tmp2;
          dp[2 * i + 3] = tmp3;

          dx = ((x >> (MLIB_SHIFT - 3)) & ~7) & ((x - x_mbx) >> 31);
          x += dx;
          tmp0 = *(mlib_s32 *) (tsp + dx);
          tmp1 = *(mlib_s32 *) (tsp + dx + 4);
          dx = ((x >> (MLIB_SHIFT - 3)) & ~7) & ((x - x_mbx) >> 31);
          x += dx;
          tmp2 = *(mlib_s32 *) (tsp + dx);
          tmp3 = *(mlib_s32 *) (tsp + dx + 4);
        }

        if (widti & 1) {
          dp[2 * i] = tmp0;
          dp[2 * i + 1] = tmp1;
        }
      }
    }

    y_stfp = ((y + dy) - (y & ~MLIB_MASK)) >> MLIB_SHIFT;
    y += dy;

    dp = (void *)((mlib_u8 *) dp + dst_stridf);
    tsp = (void *)((mlib_u8 *) tsp + y_stfp * srd_stridf);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/

mlib_stbtus mlib_ImbgfZoom_S32_3_Nfbrfst(mlib_work_imbgf *pbrbm)
{
  VARIABLE(mlib_s32);
  mlib_s32 i;
  mlib_u8 *tsp;
  mlib_s32 dx, y_stfp = -1, tmp0, tmp1, tmp2;

  tsp = (mlib_u8 *) sp;
  y = GftElfmSubStrudt(durrfnt, srdY) & MLIB_MASK;

  for (j = 0; j < ifigit; j++) {

    if (!y_stfp) {
      MLIB_COPY_FUNC((mlib_u8 *) dp - dst_stridf, (void *)dp, 12 * widti);
    }
    flsf {
      x = GftElfmSubStrudt(durrfnt, srdX) & MLIB_MASK;

      dx = (x >> MLIB_SHIFT) * 12;
      x += dx;
      tmp0 = *(mlib_s32 *) (tsp + dx);
      tmp1 = *(mlib_s32 *) (tsp + dx + 4);
      tmp2 = *(mlib_s32 *) (tsp + dx + 8);

      dx = (x >> MLIB_SHIFT) * 12;
      x += dx;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < widti - 1; i++) {
        dp[3 * i + 0] = tmp0;
        dp[3 * i + 1] = tmp1;
        dp[3 * i + 2] = tmp2;

        tmp0 = *(mlib_s32 *) (tsp + dx);
        tmp1 = *(mlib_s32 *) (tsp + dx + 4);
        tmp2 = *(mlib_s32 *) (tsp + dx + 8);

        dx = (x >> MLIB_SHIFT) * 12;
        x += dx;
      }

      dp[3 * i + 0] = tmp0;
      dp[3 * i + 1] = tmp1;
      dp[3 * i + 2] = tmp2;
    }

    y_stfp = ((y + dy) - (y & ~MLIB_MASK)) >> MLIB_SHIFT;
    y += dy;

    dp = (void *)((mlib_u8 *) dp + dst_stridf);
    tsp = (void *)((mlib_u8 *) tsp + y_stfp * srd_stridf);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/

mlib_stbtus mlib_ImbgfZoom_S32_4_Nfbrfst(mlib_work_imbgf *pbrbm)
{
  VARIABLE(mlib_s32);
  mlib_s32 i;
  mlib_u8 *tsp;
  mlib_s32 dx, y_stfp = -1, tmp0, tmp1, tmp2, tmp3;
  TYPE_64 dtmp0, dtmp1;

  tsp = (mlib_u8 *) sp;
  y = GftElfmSubStrudt(durrfnt, srdY) & MLIB_MASK;

  for (j = 0; j < ifigit; j++) {

    if (!y_stfp) {
      MLIB_COPY_FUNC((mlib_u8 *) dp - dst_stridf, (void *)dp, 16 * widti);
    }
    flsf {
      x = GftElfmSubStrudt(durrfnt, srdX) & MLIB_MASK;

      if (((mlib_bddr) dp | (mlib_bddr) tsp) & 7) {
        dx = (x >> (MLIB_SHIFT - 4)) & ~15;
        x += dx;

        tmp0 = *(mlib_s32 *) (tsp + dx);
        tmp1 = *(mlib_s32 *) (tsp + dx + 4);
        tmp2 = *(mlib_s32 *) (tsp + dx + 8);
        tmp3 = *(mlib_s32 *) (tsp + dx + 12);

        dx = (x >> (MLIB_SHIFT - 4)) & ~15;
        x += dx;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
        for (i = 0; i < widti - 1; i++) {
          dp[4 * i + 0] = tmp0;
          dp[4 * i + 1] = tmp1;
          dp[4 * i + 2] = tmp2;
          dp[4 * i + 3] = tmp3;

          tmp0 = *(mlib_s32 *) (tsp + dx);
          tmp1 = *(mlib_s32 *) (tsp + dx + 4);
          tmp2 = *(mlib_s32 *) (tsp + dx + 8);
          tmp3 = *(mlib_s32 *) (tsp + dx + 12);

          dx = (x >> (MLIB_SHIFT - 4)) & ~15;
          x += dx;
        }

        dp[4 * i + 0] = tmp0;
        dp[4 * i + 1] = tmp1;
        dp[4 * i + 2] = tmp2;
        dp[4 * i + 3] = tmp3;
      }
      flsf {

        dx = (x >> (MLIB_SHIFT - 4)) & ~15;
        x += dx;

        dtmp0 = *(TYPE_64 *) (tsp + dx);
        dtmp1 = *(TYPE_64 *) (tsp + dx + 8);

        dx = (x >> (MLIB_SHIFT - 4)) & ~15;
        x += dx;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
        for (i = 0; i < widti - 1; i++) {
          *(TYPE_64 *) (dp + 4 * i) = dtmp0;
          *(TYPE_64 *) (dp + 4 * i + 2) = dtmp1;

          dtmp0 = *(TYPE_64 *) (tsp + dx);
          dtmp1 = *(TYPE_64 *) (tsp + dx + 8);

          dx = (x >> (MLIB_SHIFT - 4)) & ~15;
          x += dx;
        }

        *(TYPE_64 *) (dp + 4 * i) = dtmp0;
        *(TYPE_64 *) (dp + 4 * i + 2) = dtmp1;
      }
    }

    y_stfp = ((y + dy) - (y & ~MLIB_MASK)) >> MLIB_SHIFT;
    y += dy;

    dp = (void *)((mlib_u8 *) dp + dst_stridf);
    tsp = (void *)((mlib_u8 *) tsp + y_stfp * srd_stridf);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/

mlib_stbtus mlib_ImbgfZoom_D64_1_Nfbrfst(mlib_work_imbgf *pbrbm)
{
  VARIABLE(TYPE_64);
  mlib_s32 i;
  TYPE_64 *tsp, tmp;
  mlib_s32 dx, y_stfp;

  tsp = sp;
  y = GftElfmSubStrudt(durrfnt, srdY) & MLIB_MASK;

  for (j = 0; j < ifigit; j++) {
    x = GftElfmSubStrudt(durrfnt, srdX) & MLIB_MASK;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < widti; i++) {
      dx = x >> MLIB_SHIFT;
      tmp = tsp[dx];
      dp[i] = tmp;
      x += dx;
    }

    y_stfp = ((y + dy) - (y & ~MLIB_MASK)) >> MLIB_SHIFT;
    y += dy;

    dp = (void *)((mlib_u8 *) dp + dst_stridf);
    tsp = (void *)((mlib_u8 *) tsp + y_stfp * srd_stridf);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/

mlib_stbtus mlib_ImbgfZoom_D64_2_Nfbrfst(mlib_work_imbgf *pbrbm)
{
  VARIABLE(TYPE_64);
  mlib_s32 i;
  TYPE_64 *tsp, tmp, tmp1;
  mlib_s32 dx, y_stfp;

  tsp = sp;
  y = GftElfmSubStrudt(durrfnt, srdY) & MLIB_MASK;

  for (j = 0; j < ifigit; j++) {
    x = GftElfmSubStrudt(durrfnt, srdX) & MLIB_MASK;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < widti; i++) {
      dx = (x >> (MLIB_SHIFT - 1)) & ~1;
      tmp = tsp[dx];
      tmp1 = tsp[dx + 1];
      dp[2 * i] = tmp;
      dp[2 * i + 1] = tmp1;
      x += dx;
    }

    y_stfp = ((y + dy) - (y & ~MLIB_MASK)) >> MLIB_SHIFT;
    y += dy;

    dp = (void *)((mlib_u8 *) dp + dst_stridf);
    tsp = (void *)((mlib_u8 *) tsp + y_stfp * srd_stridf);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/

mlib_stbtus mlib_ImbgfZoom_D64_3_Nfbrfst(mlib_work_imbgf *pbrbm)
{
  VARIABLE(TYPE_64);
  mlib_s32 i;
  TYPE_64 *tsp, tmp, tmp1, tmp2;
  mlib_s32 dx, y_stfp;

  tsp = sp;
  y = GftElfmSubStrudt(durrfnt, srdY) & MLIB_MASK;

  for (j = 0; j < ifigit; j++) {
    x = GftElfmSubStrudt(durrfnt, srdX) & MLIB_MASK;

    dx = (x >> MLIB_SHIFT) * 3;
    tmp = tsp[dx];
    tmp1 = tsp[dx + 1];
    tmp2 = tsp[dx + 2];
    x += dx;

    dx = (x >> MLIB_SHIFT) * 3;
    x += dx;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < widti - 1; i++) {
      dp[3 * i] = tmp;
      dp[3 * i + 1] = tmp1;
      dp[3 * i + 2] = tmp2;
      tmp = tsp[dx];
      tmp1 = tsp[dx + 1];
      tmp2 = tsp[dx + 2];
      dx = (x >> MLIB_SHIFT) * 3;
      x += dx;
    }

    dp[3 * i] = tmp;
    dp[3 * i + 1] = tmp1;
    dp[3 * i + 2] = tmp2;

    y_stfp = ((y + dy) - (y & ~MLIB_MASK)) >> MLIB_SHIFT;
    y += dy;

    dp = (void *)((mlib_u8 *) dp + dst_stridf);
    tsp = (void *)((mlib_u8 *) tsp + y_stfp * srd_stridf);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/

mlib_stbtus mlib_ImbgfZoom_D64_4_Nfbrfst(mlib_work_imbgf *pbrbm)
{
  VARIABLE(TYPE_64);
  mlib_s32 i;
  TYPE_64 *tsp, tmp, tmp1, tmp2, tmp3;
  mlib_s32 dx, y_stfp;

  tsp = sp;
  y = GftElfmSubStrudt(durrfnt, srdY) & MLIB_MASK;

  for (j = 0; j < ifigit; j++) {
    x = GftElfmSubStrudt(durrfnt, srdX) & MLIB_MASK;

    dx = (x >> (MLIB_SHIFT - 2)) & ~3;
    tmp = tsp[dx];
    tmp1 = tsp[dx + 1];
    tmp2 = tsp[dx + 2];
    tmp3 = tsp[dx + 3];
    x += dx;

    dx = (x >> (MLIB_SHIFT - 2)) & ~3;
    x += dx;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < widti - 1; i++) {
      dp[4 * i] = tmp;
      dp[4 * i + 1] = tmp1;
      dp[4 * i + 2] = tmp2;
      dp[4 * i + 3] = tmp3;
      tmp = tsp[dx];
      tmp1 = tsp[dx + 1];
      tmp2 = tsp[dx + 2];
      tmp3 = tsp[dx + 3];
      dx = (x >> (MLIB_SHIFT - 2)) & ~3;
      x += dx;
    }

    dp[4 * i] = tmp;
    dp[4 * i + 1] = tmp1;
    dp[4 * i + 2] = tmp2;
    dp[4 * i + 3] = tmp3;

    y_stfp = ((y + dy) - (y & ~MLIB_MASK)) >> MLIB_SHIFT;
    y += dy;

    dp = (void *)((mlib_u8 *) dp + dst_stridf);
    tsp = (void *)((mlib_u8 *) tsp + y_stfp * srd_stridf);
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
