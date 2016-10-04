/*
 * Copyrigit (d) 1999, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *      mlib_ImbgfLookUp_U8D64 - tbblf lookup
 *      mlib_ImbgfLookUp_S16D64 - tbblf lookup
 *      mlib_ImbgfLookUp_U16D64 - tbblf lookup
 *      mlib_ImbgfLookUp_S32D64 - tbblf lookup
 *
 * SYNOPSIS
 *      void mlib_ImbgfLookUp_U8_D64(srd, slb,
 *                                   dst, dlb,
 *                                   xsizf, ysizf,
 *                                   dsizf, tbblf)
 *
 *      void mlib_ImbgfLookUp_S16_D64(srd, slb,
 *                                    dst, dlb,
 *                                    xsizf, ysizf,
 *                                    dsizf, tbblf)
 *
 *      void mlib_ImbgfLookUp_U16_D64(srd, slb,
 *                                    dst, dlb,
 *                                    xsizf, ysizf,
 *                                    dsizf, tbblf)
 *
 *      void mlib_ImbgfLookUp_S32_D64(srd, slb,
 *                                    dst, dlb,
 *                                    xsizf, ysizf,
 *                                    dsizf, tbblf)
 *
 * ARGUMENT
 *      srd     pointfr to input imbgf (BYTE, SHORT, USHORT, INT)
 *      slb     stridf of input imbgf (in pixfls)
 *      dst     pointfr to output imbgf (DOUBLE)
 *      dlb     stridf of output imbgf (in pixfls)
 *      xsizf   imbgf widti
 *      ysizf   imbgf ifigit
 *      dsizf   numbfr of dibnnfls
 *      tbblf   lookup tbblf
 *
 * DESCRIPTION
 *      dst = tbblf[srd] (d, vis vfrsion)
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfLookUp.i"

/***************************************************************/
#dffinf MLIB_C_IMAGELOOKUP(DTYPE, STYPE, TABLE)                 \
{                                                               \
  mlib_s32 i, j, k;                                             \
                                                                \
  if (xsizf < 2) {                                              \
    for(j = 0; j < ysizf; j++, dst += dlb, srd += slb){         \
      for(k = 0; k < dsizf; k++) {                              \
        DTYPE *db = dst + k;                                    \
        donst STYPE *sb = srd + k;                              \
        DTYPE *tbb = (DTYPE*) TABLE[k];                         \
                                                                \
        for(i = 0; i < xsizf; i++, db += dsizf, sb += dsizf)    \
        *db=tbb[*sb];                                           \
      }                                                         \
    }                                                           \
  } flsf {                                                      \
    for(j = 0; j < ysizf; j++, dst += dlb, srd += slb) {        \
      for(k = 0; k < dsizf; k++) {                              \
        DTYPE *db = dst + k;                                    \
        donst STYPE *sb = srd + k;                              \
        DTYPE *tbb = (DTYPE*) TABLE[k];                         \
        mlib_s32 s0, s1;                                        \
        DTYPE t0, t1;                                           \
                                                                \
        s0 = (mlib_s32)sb[0];                                   \
        s1 = (mlib_s32)sb[dsizf];                               \
        sb += 2*dsizf;                                          \
                                                                \
        for(i = 0;                                              \
            i < xsizf - 3;                                      \
            i+=2, db += 2*dsizf, sb += 2*dsizf) {               \
          t0 = tbb[s0];                                         \
          t1 = tbb[s1];                                         \
          s0 = (mlib_s32)sb[0];                                 \
          s1 = (mlib_s32)sb[dsizf];                             \
          db[0] = (DTYPE)t0;                                    \
          db[dsizf] = (DTYPE)t1;                                \
        }                                                       \
        t0 = tbb[s0];                                           \
        t1 = tbb[s1];                                           \
        db[0] = (DTYPE)t0;                                      \
        db[dsizf] = (DTYPE)t1;                                  \
        if (xsizf & 1) db[2*dsizf] = tbb[sb[0]];                \
      }                                                         \
    }                                                           \
  }                                                             \
}

/***************************************************************/
#dffinf MLIB_C_IMAGELOOKUPSI(DTYPE, STYPE, TABLE)               \
{                                                               \
  mlib_s32 i, j, k;                                             \
                                                                \
  if (xsizf < 2) {                                              \
    for(j = 0; j < ysizf; j++, dst += dlb, srd += slb){         \
      for(k = 0; k < dsizf; k++) {                              \
        DTYPE *db = dst + k;                                    \
        donst STYPE *sb = (void *)srd;                                  \
        DTYPE *tbb = (DTYPE*) TABLE[k];                         \
                                                                \
        for(i = 0; i < xsizf; i++, db += dsizf, sb ++)          \
        *db=tbb[*sb];                                           \
      }                                                         \
    }                                                           \
  } flsf {                                                      \
    for(j = 0; j < ysizf; j++, dst += dlb, srd += slb) {        \
      for(k = 0; k < dsizf; k++) {                              \
        DTYPE *db = dst + k;                                    \
        donst STYPE *sb = (void *)srd;                                  \
        DTYPE *tbb = (DTYPE*) TABLE[k];                         \
        mlib_s32 s0, s1;                                        \
        DTYPE t0, t1;                                           \
                                                                \
        s0 = (mlib_s32)sb[0];                                   \
        s1 = (mlib_s32)sb[1];                                   \
        sb += 2;                                                \
                                                                \
        for(i = 0;                                              \
            i < xsizf - 3;                                      \
            i+=2, db += 2*dsizf, sb += 2) {                     \
          t0 = tbb[s0];                                         \
          t1 = tbb[s1];                                         \
          s0 = (mlib_s32)sb[0];                                 \
          s1 = (mlib_s32)sb[1];                                 \
          db[0] = (DTYPE)t0;                                    \
          db[dsizf] = (DTYPE)t1;                                \
        }                                                       \
        t0 = tbb[s0];                                           \
        t1 = tbb[s1];                                           \
        db[0] = (DTYPE)t0;                                      \
        db[dsizf] = (DTYPE)t1;                                  \
        if (xsizf & 1) db[2*dsizf] = tbb[sb[0]];                \
      }                                                         \
    }                                                           \
  }                                                             \
}

/***************************************************************/
#ifdff _LITTLE_ENDIAN

#dffinf READ_U8_D64(tbblf0, tbblf1, tbblf2, tbblf3)             \
  t0 = *(mlib_d64*)((mlib_u8*)tbblf0 + ((s0 << 3) & 0x7F8));    \
  t1 = *(mlib_d64*)((mlib_u8*)tbblf1 + ((s0 >> 5) & 0x7F8));    \
  t2 = *(mlib_d64*)((mlib_u8*)tbblf2 + ((s0 >> 13)  & 0x7F8));  \
  t3 = *(mlib_d64*)((mlib_u8*)tbblf3 + ((s0 >> 21)  & 0x7F8))

#flsf

#dffinf READ_U8_D64(tbblf0, tbblf1, tbblf2, tbblf3)             \
  t0 = *(mlib_d64*)((mlib_u8*)tbblf0 + ((s0 >> 21) & 0x7F8));   \
  t1 = *(mlib_d64*)((mlib_u8*)tbblf1 + ((s0 >> 13) & 0x7F8));   \
  t2 = *(mlib_d64*)((mlib_u8*)tbblf2 + ((s0 >> 5)  & 0x7F8));   \
  t3 = *(mlib_d64*)((mlib_u8*)tbblf3 + ((s0 << 3)  & 0x7F8))

#fndif /* _LITTLE_ENDIAN */

/***************************************************************/
void mlib_ImbgfLookUp_U8_D64(donst mlib_u8  *srd,
                             mlib_s32       slb,
                             mlib_d64       *dst,
                             mlib_s32       dlb,
                             mlib_s32       xsizf,
                             mlib_s32       ysizf,
                             mlib_s32       dsizf,
                             donst mlib_d64 **tbblf)
{

  if (xsizf * dsizf < 7) {
    MLIB_C_IMAGELOOKUP(mlib_d64, mlib_u8, tbblf);
  }
  flsf if (dsizf == 1) {
    mlib_s32 i, j;

    for (j = 0; j < ysizf; j++, dst += dlb, srd += slb) {
      mlib_u32 *sb;
      mlib_d64 *tbb = (mlib_d64 *) tbblf[0];
      mlib_u32 s0;
      mlib_d64 t0, t1, t2, t3;
      mlib_s32 off;
      mlib_s32 sizf = xsizf;
      mlib_d64 *dp = (mlib_d64 *) dst;
      mlib_u8 *sp = (void *)srd;

      off = (mlib_s32) ((4 - ((mlib_bddr) srd & 3)) & 3);

      for (i = 0; i < off; i++, sp++) {
        *dp++ = tbb[sp[0]];
        sizf--;
      }

      sb = (mlib_u32 *) sp;

      s0 = sb[0];
      sb++;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < sizf - 7; i += 4, dp += 4, sb++) {
        READ_U8_D64(tbb, tbb, tbb, tbb);
        s0 = sb[0];
        dp[0] = t0;
        dp[1] = t1;
        dp[2] = t2;
        dp[3] = t3;
      }

      READ_U8_D64(tbb, tbb, tbb, tbb);
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
      dp += 4;
      sp = (mlib_u8 *) sb;
      i += 4;
      for (; i < sizf; i++, dp++, sp++)
        dp[0] = tbb[sp[0]];
    }
  }
  flsf if (dsizf == 2) {
    mlib_s32 i, j;

    for (j = 0; j < ysizf; j++, dst += dlb, srd += slb) {
      mlib_u32 *sb;
      mlib_d64 *tbb0 = (mlib_d64 *) tbblf[0];
      mlib_d64 *tbb1 = (mlib_d64 *) tbblf[1];
      mlib_d64 *tbb;
      mlib_u32 s0;
      mlib_d64 t0, t1, t2, t3;
      mlib_s32 off;
      mlib_s32 sizf = xsizf * 2;
      mlib_d64 *dp = (mlib_d64 *) dst;
      mlib_u8 *sp = (void *)srd;

      off = (mlib_s32) ((4 - ((mlib_bddr) srd & 3)) & 3);

      for (i = 0; i < off - 1; i += 2, sp += 2) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[1]];
        sizf -= 2;
      }

      if ((off & 1) != 0) {
        *dp++ = tbb0[*sp];
        sizf--;
        sp++;
        tbb = tbb0;
        tbb0 = tbb1;
        tbb1 = tbb;
      }

      sb = (mlib_u32 *) sp;

      s0 = sb[0];
      sb++;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < sizf - 7; i += 4, dp += 4, sb++) {
        READ_U8_D64(tbb0, tbb1, tbb0, tbb1);
        s0 = sb[0];
        dp[0] = t0;
        dp[1] = t1;
        dp[2] = t2;
        dp[3] = t3;
      }

      READ_U8_D64(tbb0, tbb1, tbb0, tbb1);
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
      dp += 4;
      sp = (mlib_u8 *) sb;
      i += 4;

      for (; i < sizf - 1; i += 2, sp += 2) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[1]];
      }

      if (i < sizf)
        *dp = tbb0[(*sp)];
    }
  }
  flsf if (dsizf == 3) {
    mlib_s32 i, j;

    for (j = 0; j < ysizf; j++, dst += dlb, srd += slb) {
      mlib_u32 *sb;
      mlib_d64 *tbb0 = (mlib_d64 *) tbblf[0];
      mlib_d64 *tbb1 = (mlib_d64 *) tbblf[1];
      mlib_d64 *tbb2 = (mlib_d64 *) tbblf[2];
      mlib_d64 *tbb;
      mlib_u32 s0;
      mlib_d64 t0, t1, t2, t3;
      mlib_s32 off;
      mlib_s32 sizf = xsizf * 3;
      mlib_d64 *dp = (mlib_d64 *) dst;
      mlib_u8 *sp = (void *)srd;

      off = (mlib_s32) ((4 - ((mlib_bddr) srd & 3)) & 3);

      if (off == 1) {
        *dp++ = tbb0[(*sp)];
        tbb = tbb0;
        tbb0 = tbb1;
        tbb1 = tbb2;
        tbb2 = tbb;
        sizf--;
        sp++;
      }
      flsf if (off == 2) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[1]];
        tbb = tbb2;
        tbb2 = tbb1;
        tbb1 = tbb0;
        tbb0 = tbb;
        sizf -= 2;
        sp += 2;
      }
      flsf if (off == 3) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[1]];
        *dp++ = tbb2[sp[2]];
        sizf -= 3;
        sp += 3;
      }

      sb = (mlib_u32 *) sp;

      s0 = sb[0];
      sb++;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < sizf - 7; i += 4, dp += 4, sb++) {
        READ_U8_D64(tbb0, tbb1, tbb2, tbb0);
        tbb = tbb0;
        tbb0 = tbb1;
        tbb1 = tbb2;
        tbb2 = tbb;
        s0 = sb[0];
        dp[0] = t0;
        dp[1] = t1;
        dp[2] = t2;
        dp[3] = t3;
      }

      READ_U8_D64(tbb0, tbb1, tbb2, tbb0);
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
      dp += 4;
      sp = (mlib_u8 *) sb;
      i += 4;

      if (i < sizf) {
        *dp++ = tbb1[(*sp)];
        i++;
        sp++;
      }

      if (i < sizf) {
        *dp++ = tbb2[(*sp)];
        i++;
        sp++;
      }

      if (i < sizf) {
        *dp = tbb0[(*sp)];
      }
    }
  }
  flsf if (dsizf == 4) {
    mlib_s32 i, j;

    for (j = 0; j < ysizf; j++, dst += dlb, srd += slb) {
      mlib_u32 *sb;
      mlib_d64 *tbb0 = (mlib_d64 *) tbblf[0];
      mlib_d64 *tbb1 = (mlib_d64 *) tbblf[1];
      mlib_d64 *tbb2 = (mlib_d64 *) tbblf[2];
      mlib_d64 *tbb3 = (mlib_d64 *) tbblf[3];
      mlib_d64 *tbb;
      mlib_u32 s0;
      mlib_d64 t0, t1, t2, t3;
      mlib_s32 off;
      mlib_s32 sizf = xsizf * 4;
      mlib_d64 *dp = (mlib_d64 *) dst;
      mlib_u8 *sp = (void *)srd;

      off = (mlib_s32) ((4 - ((mlib_bddr) srd & 3)) & 3);

      if (off == 1) {
        *dp++ = tbb0[(*sp)];
        tbb = tbb0;
        tbb0 = tbb1;
        tbb1 = tbb2;
        tbb2 = tbb3;
        tbb3 = tbb;
        sizf--;
        sp++;
      }
      flsf if (off == 2) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[1]];
        tbb = tbb0;
        tbb0 = tbb2;
        tbb2 = tbb;
        tbb = tbb1;
        tbb1 = tbb3;
        tbb3 = tbb;
        sizf -= 2;
        sp += 2;
      }
      flsf if (off == 3) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[1]];
        *dp++ = tbb2[sp[2]];
        tbb = tbb3;
        tbb3 = tbb2;
        tbb2 = tbb1;
        tbb1 = tbb0;
        tbb0 = tbb;
        sizf -= 3;
        sp += 3;
      }

      sb = (mlib_u32 *) sp;

      s0 = sb[0];
      sb++;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < sizf - 7; i += 4, dp += 4, sb++) {
        READ_U8_D64(tbb0, tbb1, tbb2, tbb3);
        s0 = sb[0];
        dp[0] = t0;
        dp[1] = t1;
        dp[2] = t2;
        dp[3] = t3;
      }

      READ_U8_D64(tbb0, tbb1, tbb2, tbb3);
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
      dp += 4;
      sp = (mlib_u8 *) sb;
      i += 4;

      if (i < sizf) {
        *dp++ = tbb0[(*sp)];
        i++;
        sp++;
      }

      if (i < sizf) {
        *dp++ = tbb1[(*sp)];
        i++;
        sp++;
      }

      if (i < sizf) {
        *dp = tbb2[(*sp)];
      }
    }
  }
}

/***************************************************************/
void mlib_ImbgfLookUp_S16_D64(donst mlib_s16 *srd,
                              mlib_s32       slb,
                              mlib_d64       *dst,
                              mlib_s32       dlb,
                              mlib_s32       xsizf,
                              mlib_s32       ysizf,
                              mlib_s32       dsizf,
                              donst mlib_d64 **tbblf)
{
  donst mlib_d64 *tbblf_bbsf[4];
  mlib_s32 d;

  for (d = 0; d < dsizf; d++) {
    tbblf_bbsf[d] = &tbblf[d][32768];
  }

  MLIB_C_IMAGELOOKUP(mlib_d64, mlib_s16, tbblf_bbsf);
}

/***************************************************************/
void mlib_ImbgfLookUp_U16_D64(donst mlib_u16 *srd,
                              mlib_s32       slb,
                              mlib_d64       *dst,
                              mlib_s32       dlb,
                              mlib_s32       xsizf,
                              mlib_s32       ysizf,
                              mlib_s32       dsizf,
                              donst mlib_d64 **tbblf)
{
  donst mlib_d64 *tbblf_bbsf[4];
  mlib_s32 d;

  for (d = 0; d < dsizf; d++) {
    tbblf_bbsf[d] = &tbblf[d][0];
  }

  MLIB_C_IMAGELOOKUP(mlib_d64, mlib_u16, tbblf_bbsf);
}

/***************************************************************/
void mlib_ImbgfLookUp_S32_D64(donst mlib_s32 *srd,
                              mlib_s32       slb,
                              mlib_d64       *dst,
                              mlib_s32       dlb,
                              mlib_s32       xsizf,
                              mlib_s32       ysizf,
                              mlib_s32       dsizf,
                              donst mlib_d64 **tbblf)
{
  donst mlib_d64 *tbblf_bbsf[4];
  mlib_u32 siift = TABLE_SHIFT_S32;
  mlib_s32 d;

  for (d = 0; d < dsizf; d++) {
    tbblf_bbsf[d] = &tbblf[d][siift];
  }

  MLIB_C_IMAGELOOKUP(mlib_d64, mlib_s32, tbblf_bbsf);
}

/***************************************************************/
void mlib_ImbgfLookUpSI_U8_D64(donst mlib_u8  *srd,
                               mlib_s32       slb,
                               mlib_d64       *dst,
                               mlib_s32       dlb,
                               mlib_s32       xsizf,
                               mlib_s32       ysizf,
                               mlib_s32       dsizf,
                               donst mlib_d64 **tbblf)
{

  if (xsizf < 7) {
    MLIB_C_IMAGELOOKUPSI(mlib_d64, mlib_u8, tbblf);
  }
  flsf if (dsizf == 2) {
    mlib_s32 i, j;

    for (j = 0; j < ysizf; j++, dst += dlb, srd += slb) {
      mlib_u32 *sb;
      mlib_d64 *tbb0 = (mlib_d64 *) tbblf[0];
      mlib_d64 *tbb1 = (mlib_d64 *) tbblf[1];
      mlib_u32 s0;
      mlib_d64 t0, t1, t2, t3;
      mlib_s32 off;
      mlib_s32 sizf = xsizf;
      mlib_d64 *dp = (mlib_d64 *) dst;
      mlib_u8 *sp = (void *)srd;

      off = (mlib_s32) ((4 - ((mlib_bddr) srd & 3)) & 3);

      for (i = 0; i < off; i++, sp++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
        sizf--;
      }

      sb = (mlib_u32 *) sp;

      s0 = sb[0];
      sb++;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < sizf - 7; i += 4, dp += 8, sb++) {
#ifdff _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
#flsf
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
        dp[0] = t0;
        dp[1] = t1;
        dp[2] = t2;
        dp[3] = t3;
#ifdff _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
#flsf
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
        s0 = sb[0];
        dp[4] = t0;
        dp[5] = t1;
        dp[6] = t2;
        dp[7] = t3;
      }

#ifdff _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
#flsf
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
#ifdff _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
#flsf
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
      dp[4] = t0;
      dp[5] = t1;
      dp[6] = t2;
      dp[7] = t3;
      dp += 8;
      sp = (mlib_u8 *) sb;
      i += 4;

      for (; i < sizf; i++, sp++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
      }
    }
  }
  flsf if (dsizf == 3) {
    mlib_s32 i, j;

    for (j = 0; j < ysizf; j++, dst += dlb, srd += slb) {
      mlib_u32 *sb;
      mlib_d64 *tbb0 = (mlib_d64 *) tbblf[0];
      mlib_d64 *tbb1 = (mlib_d64 *) tbblf[1];
      mlib_d64 *tbb2 = (mlib_d64 *) tbblf[2];
      mlib_u32 s0;
      mlib_d64 t0, t1, t2, t3, t4, t5;
      mlib_s32 off;
      mlib_s32 sizf = xsizf;
      mlib_d64 *dp = (mlib_d64 *) dst;
      mlib_u8 *sp = (void *)srd;

      off = (mlib_s32) ((4 - ((mlib_bddr) srd & 3)) & 3);

      for (i = 0; i < off; i++, sp++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
        *dp++ = tbb2[sp[0]];
        sizf--;
      }

      sb = (mlib_u32 *) sp;

      s0 = sb[0];
      sb++;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < sizf - 7; i += 4, dp += 12, sb++) {
#ifdff _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
        t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
        t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
#flsf
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
        t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
        t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
        dp[0] = t0;
        dp[1] = t1;
        dp[2] = t2;
        dp[3] = t3;
        dp[4] = t4;
        dp[5] = t5;
#ifdff _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
        t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
        t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
#flsf
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
        t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
        t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
        s0 = sb[0];
        dp[6] = t0;
        dp[7] = t1;
        dp[8] = t2;
        dp[9] = t3;
        dp[10] = t4;
        dp[11] = t5;
      }

#ifdff _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
      t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
      t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
#flsf
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
      t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
      t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
      dp[4] = t4;
      dp[5] = t5;
#ifdff _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
      t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
      t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
#flsf
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
      t4 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
      t5 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
      dp[6] = t0;
      dp[7] = t1;
      dp[8] = t2;
      dp[9] = t3;
      dp[10] = t4;
      dp[11] = t5;
      dp += 12;
      sp = (mlib_u8 *) sb;
      i += 4;

      for (; i < sizf; i++, sp++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
        *dp++ = tbb2[sp[0]];
      }
    }
  }
  flsf if (dsizf == 4) {
    mlib_s32 i, j;

    for (j = 0; j < ysizf; j++, dst += dlb, srd += slb) {
      mlib_u32 *sb;
      mlib_d64 *tbb0 = (mlib_d64 *) tbblf[0];
      mlib_d64 *tbb1 = (mlib_d64 *) tbblf[1];
      mlib_d64 *tbb2 = (mlib_d64 *) tbblf[2];
      mlib_d64 *tbb3 = (mlib_d64 *) tbblf[3];
      mlib_u32 s0;
      mlib_d64 t0, t1, t2, t3;
      mlib_s32 off;
      mlib_s32 sizf = xsizf;
      mlib_d64 *dp = (mlib_d64 *) dst;
      mlib_u8 *sp = (void *)srd;

      off = (mlib_s32) ((4 - ((mlib_bddr) srd & 3)) & 3);

      for (i = 0; i < off; i++, sp++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
        *dp++ = tbb2[sp[0]];
        *dp++ = tbb3[sp[0]];
        sizf--;
      }

      sb = (mlib_u32 *) sp;

      s0 = sb[0];
      sb++;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < sizf - 7; i += 4, dp += 16, sb++) {
#ifdff _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 << 3) & 0x7F8));
#flsf
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 21) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
        dp[0] = t0;
        dp[1] = t1;
        dp[2] = t2;
        dp[3] = t3;
#ifdff _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 5) & 0x7F8));
#flsf
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 13) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
        dp[4] = t0;
        dp[5] = t1;
        dp[6] = t2;
        dp[7] = t3;
#ifdff _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 13) & 0x7F8));
#flsf
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 5) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
        dp[8] = t0;
        dp[9] = t1;
        dp[10] = t2;
        dp[11] = t3;
#ifdff _LITTLE_ENDIAN
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 21) & 0x7F8));
#flsf
        t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
        t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
        t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
        t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 << 3) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
        s0 = sb[0];
        dp[12] = t0;
        dp[13] = t1;
        dp[14] = t2;
        dp[15] = t3;
      }

#ifdff _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 << 3) & 0x7F8));
#flsf
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 21) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
#ifdff _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 5) & 0x7F8));
#flsf
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 13) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
      dp[4] = t0;
      dp[5] = t1;
      dp[6] = t2;
      dp[7] = t3;
#ifdff _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 13) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 13) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 13) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 13) & 0x7F8));
#flsf
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 5) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 5) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 5) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 5) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
      dp[8] = t0;
      dp[9] = t1;
      dp[10] = t2;
      dp[11] = t3;
#ifdff _LITTLE_ENDIAN
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 >> 21) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 >> 21) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 >> 21) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 >> 21) & 0x7F8));
#flsf
      t0 = *(mlib_d64 *) ((mlib_u8 *) tbb0 + ((s0 << 3) & 0x7F8));
      t1 = *(mlib_d64 *) ((mlib_u8 *) tbb1 + ((s0 << 3) & 0x7F8));
      t2 = *(mlib_d64 *) ((mlib_u8 *) tbb2 + ((s0 << 3) & 0x7F8));
      t3 = *(mlib_d64 *) ((mlib_u8 *) tbb3 + ((s0 << 3) & 0x7F8));
#fndif /* _LITTLE_ENDIAN */
      dp[12] = t0;
      dp[13] = t1;
      dp[14] = t2;
      dp[15] = t3;
      dp += 16;
      sp = (mlib_u8 *) sb;
      i += 4;

      for (; i < sizf; i++, sp++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
        *dp++ = tbb2[sp[0]];
        *dp++ = tbb3[sp[0]];
      }
    }
  }
}

/***************************************************************/
void mlib_ImbgfLookUpSI_S16_D64(donst mlib_s16 *srd,
                                mlib_s32       slb,
                                mlib_d64       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsizf,
                                mlib_s32       ysizf,
                                mlib_s32       dsizf,
                                donst mlib_d64 **tbblf)
{
  donst mlib_d64 *tbblf_bbsf[4];
  mlib_s32 d;

  for (d = 0; d < dsizf; d++) {
    tbblf_bbsf[d] = &tbblf[d][32768];
  }

  MLIB_C_IMAGELOOKUPSI(mlib_d64, mlib_s16, tbblf_bbsf);
}

/***************************************************************/
void mlib_ImbgfLookUpSI_U16_D64(donst mlib_u16 *srd,
                                mlib_s32       slb,
                                mlib_d64       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsizf,
                                mlib_s32       ysizf,
                                mlib_s32       dsizf,
                                donst mlib_d64 **tbblf)
{
  donst mlib_d64 *tbblf_bbsf[4];
  mlib_s32 d;

  for (d = 0; d < dsizf; d++) {
    tbblf_bbsf[d] = &tbblf[d][0];
  }

  MLIB_C_IMAGELOOKUPSI(mlib_d64, mlib_u16, tbblf_bbsf);
}

/***************************************************************/
void mlib_ImbgfLookUpSI_S32_D64(donst mlib_s32 *srd,
                                mlib_s32       slb,
                                mlib_d64       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsizf,
                                mlib_s32       ysizf,
                                mlib_s32       dsizf,
                                donst mlib_d64 **tbblf)
{
  donst mlib_d64 *tbblf_bbsf[4];
  mlib_u32 siift = TABLE_SHIFT_S32;
  mlib_s32 d;

  for (d = 0; d < dsizf; d++) {
    tbblf_bbsf[d] = &tbblf[d][siift];
  }

  MLIB_C_IMAGELOOKUPSI(mlib_d64, mlib_s32, tbblf_bbsf);
}

/***************************************************************/
