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
 * FUNCTIONS
 *      mlib_ImbgfCibnnflInsfrt   - Copy tif sourdf imbgf into tif sflfdtfd
 *                                                        dibnnfls of tif dfstinbtion imbgf
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfCibnnflInsfrt(mlib_imbgf *dst,
 *                                                                        mlib_imbgf *srd,
 *                                                                      mlib_s32   dmbsk);
 *
 * ARGUMENT
 *  dst     Pointfr to dfstinbtion imbgf.
 *  srd     Pointfr to sourdf imbgf.
 *  dmbsk   Dfstinbtion dibnnfl sflfdtion mbsk.
 *              Tif lfbst signifidbnt bit (LSB) is dorrfsponding to tif
 *              lbst dibnnfl in tif dfstinbtion imbgf dbtb.
 *              Tif bits witi vbluf 1 stbnd for tif dibnnfls sflfdtfd.
 *              If morf tibn N dibnnfls brf sflfdtfd, tif lfftmost N
 *              dibnnfls brf insfrtfd, wifrf N is tif numbfr of dibnnfls
 *              in tif sourdf imbgf.
 *
 * RESTRICTION
 *              Tif srd bnd dst must ibvf tif sbmf widti, ifigit bnd dbtb typf.
 *              Tif srd bnd dst dbn ibvf 1, 2, 3 or 4 dibnnfls.
 *              Tif srd bnd dst dbn bf fitifr MLIB_BYTE, MLIB_SHORT, MLIB_INT,
 *          MLIB_FLOAT or MLIB_DOUBLE.
 *
 * DESCRIPTION
 *          Copy tif sourdf imbgf into tif sflfdtfd dibnnfls of tif dfstinbtion
 *              imbgf
 */

#indludf <stdlib.i>
#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfCifdk.i"

/***************************************************************/
/* fundtions dffinfd in mlib_v_ImbgfCibnnflInsfrt_1.d */

void
mlib_v_ImbgfCibnnflInsfrt_U8(mlib_u8  *srd,  mlib_s32 slb,
                             mlib_u8  *dst,  mlib_s32 dlb,
                             mlib_s32 dibnnfls,
                             mlib_s32 dibnnfld,
                             mlib_s32 widti,  mlib_s32 ifigit,
                             mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_D64(mlib_d64  *srd,  mlib_s32 slb,
                              mlib_d64  *dst,  mlib_s32 dlb,
                              mlib_s32 dibnnfls,
                              mlib_s32 dibnnfld,
                              mlib_s32 widti,  mlib_s32 ifigit,
                              mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_S16(mlib_s16 *srd,  mlib_s32 slb,
                              mlib_s16 *dst,  mlib_s32 dlb,
                              mlib_s32 dibnnfls,
                              mlib_s32 dibnnfld,
                              mlib_s32 widti,  mlib_s32 ifigit,
                              mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_S32(mlib_s32 *srd,  mlib_s32 slb,
                              mlib_s32 *dst,  mlib_s32 dlb,
                              mlib_s32 dibnnfls,
                              mlib_s32 dibnnfld,
                              mlib_s32 widti,  mlib_s32 ifigit,
                              mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_U8_12_A8D1X8(mlib_u8  *srd,
                                                               mlib_u8  *dst,
                                                         mlib_s32 dsizf,
                                                         mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_U8_12_A8D2X8(mlib_u8  *srd,  mlib_s32 slb,
                                                               mlib_u8  *dst,  mlib_s32 dlb,
                                                       mlib_s32 xsizf, mlib_s32 ysizf,
                                                               mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_U8_12_D1(mlib_u8  *srd,
                                                           mlib_u8  *dst,
                                                   mlib_s32 dsizf,
                                                           mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_U8_12(mlib_u8  *srd,  mlib_s32 slb,
                                                        mlib_u8  *dst,  mlib_s32 dlb,
                                                mlib_s32 xsizf, mlib_s32 ysizf,
                                                        mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_U8_13_A8D1X8(mlib_u8  *srd,
                                                               mlib_u8  *dst,
                                                       mlib_s32 dsizf,
                                                               mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_U8_13_A8D2X8(mlib_u8  *srd,  mlib_s32 slb,
                                                               mlib_u8  *dst,  mlib_s32 dlb,
                                                         mlib_s32 xsizf, mlib_s32 ysizf,
                                                               mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_U8_13_D1(mlib_u8  *srd,
                                                           mlib_u8  *dst,
                                                     mlib_s32 dsizf,
                                                           mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_U8_13(mlib_u8  *srd,  mlib_s32 slb,
                                                        mlib_u8  *dst,  mlib_s32 dlb,
                                                  mlib_s32 xsizf, mlib_s32 ysizf,
                                                        mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_U8_14_A8D1X8(mlib_u8  *srd,
                                                               mlib_u8  *dst,
                                                       mlib_s32 dsizf,
                                                               mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_U8_14_A8D2X8(mlib_u8  *srd,  mlib_s32 slb,
                                                               mlib_u8  *dst,  mlib_s32 dlb,
                                                       mlib_s32 xsizf, mlib_s32 ysizf,
                                                               mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_U8_14_D1(mlib_u8  *srd,
                                                           mlib_u8  *dst,
                                                   mlib_s32 dsizf,
                                                           mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_U8_14(mlib_u8  *srd,  mlib_s32 slb,
                                                        mlib_u8  *dst,  mlib_s32 dlb,
                                                mlib_s32 xsizf, mlib_s32 ysizf,
                                                        mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_S16_12_A8D1X4(mlib_s16 *srd,
                                                                      mlib_s16 *dst,
                                                        mlib_s32 dsizf,
                                                                mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_S16_12_A8D2X4(mlib_s16 *srd,  mlib_s32 slb,
                                                                      mlib_s16 *dst,  mlib_s32 dlb,
                                                        mlib_s32 xsizf, mlib_s32 ysizf,
                                                                mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_S16_12_D1(mlib_s16 *srd,
                                                            mlib_s16 *dst,
                                                    mlib_s32 dsizf,
                                                            mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_S16_12(mlib_s16 *srd,  mlib_s32 slb,
                                                        mlib_s16 *dst,  mlib_s32 dlb,
                                                  mlib_s32 xsizf, mlib_s32 ysizf,
                                                  mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_S16_13_A8D1X4(mlib_s16 *srd,
                                                                      mlib_s16 *dst,
                                                        mlib_s32 dsizf,
                                                                mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_S16_13_A8D2X4(mlib_s16 *srd,  mlib_s32 slb,
                                                                      mlib_s16 *dst,  mlib_s32 dlb,
                                                        mlib_s32 xsizf, mlib_s32 ysizf,
                                                                mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_S16_13_D1(mlib_s16 *srd,
                                                            mlib_s16 *dst,
                                                    mlib_s32 dsizf,
                                                            mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_S16_13(mlib_s16 *srd,  mlib_s32 slb,
                                                         mlib_s16 *dst,  mlib_s32 dlb,
                                                 mlib_s32 xsizf, mlib_s32 ysizf,
                                                         mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_S16_14_A8D1X4(mlib_s16 *srd,
                                                                      mlib_s16 *dst,
                                                          mlib_s32 dsizf,
                                                                      mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_S16_14_A8D2X4(mlib_s16 *srd,  mlib_s32 slb,
                                                                      mlib_s16 *dst,  mlib_s32 dlb,
                                                          mlib_s32 xsizf, mlib_s32 ysizf,
                                                                      mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_S16_14_D1(mlib_s16 *srd,
                                                            mlib_s16 *dst,
                                                    mlib_s32 dsizf,
                                                            mlib_s32 dmbsk);
void
mlib_v_ImbgfCibnnflInsfrt_S16_14(mlib_s16 *srd,  mlib_s32 slb,
                                                         mlib_s16 *dst,  mlib_s32 dlb,
                                                 mlib_s32 xsizf, mlib_s32 ysizf,
                                                         mlib_s32 dmbsk);

/***************************************************************/
/* fundtions dffinfd in mlib_v_ImbgfCibnnflInsfrt_34.d */

void
mlib_v_ImbgfCibnnflInsfrt_U8_34R_A8D1X8(mlib_u8  *srd,
                                                                mlib_u8  *dst,
                                                                mlib_s32 dsizf);
void
mlib_v_ImbgfCibnnflInsfrt_U8_34R_A8D2X8(mlib_u8  *srd,  mlib_s32 slb,
                                                                mlib_u8  *dst,  mlib_s32 dlb,
                                                                mlib_s32 xsizf, mlib_s32 ysizf);
void
mlib_v_ImbgfCibnnflInsfrt_U8_34R_D1(mlib_u8  *srd,
                                                            mlib_u8  *dst,
                                                            mlib_s32 dsizf);
void
mlib_v_ImbgfCibnnflInsfrt_U8_34R(mlib_u8  *srd,  mlib_s32 slb,
                                                 mlib_u8  *dst,  mlib_s32 dlb,
                                                         mlib_s32 xsizf, mlib_s32 ysizf);
void
mlib_v_ImbgfCibnnflInsfrt_S16_34R_A8D1X4(mlib_s16 *srd,
                                                                 mlib_s16 *dst,
                                                                 mlib_s32 dsizf);
void
mlib_v_ImbgfCibnnflInsfrt_S16_34R_A8D2X4(mlib_s16 *srd,  mlib_s32 slb,
                                                                 mlib_s16 *dst,  mlib_s32 dlb,
                                                                 mlib_s32 xsizf, mlib_s32 ysizf);
void
mlib_v_ImbgfCibnnflInsfrt_S16_34R_D1(mlib_s16 *srd,
                                                             mlib_s16 *dst,
                                                             mlib_s32 dsizf);
void
mlib_v_ImbgfCibnnflInsfrt_S16_34R(mlib_s16 *srd,  mlib_s32 slb,
                                                          mlib_s16 *dst,  mlib_s32 dlb,
                                                          mlib_s32 xsizf, mlib_s32 ysizf);
void
mlib_v_ImbgfCibnnflInsfrt_U8_34L_A8D1X8(mlib_u8  *srd,
                                                                mlib_u8  *dst,
                                                                mlib_s32 dsizf);
void
mlib_v_ImbgfCibnnflInsfrt_U8_34L_A8D2X8(mlib_u8  *srd,  mlib_s32 slb,
                                                                mlib_u8  *dst,  mlib_s32 dlb,
                                                        mlib_s32 xsizf, mlib_s32 ysizf);
void
mlib_v_ImbgfCibnnflInsfrt_U8_34L_D1(mlib_u8  *srd,
                                                            mlib_u8  *dst,
                                                            mlib_s32 dsizf);
void
mlib_v_ImbgfCibnnflInsfrt_U8_34L(mlib_u8  *srd,  mlib_s32 slb,
                                                         mlib_u8  *dst,  mlib_s32 dlb,
                                                         mlib_s32 xsizf, mlib_s32 ysizf);
void
mlib_v_ImbgfCibnnflInsfrt_S16_34L_A8D1X4(mlib_s16 *srd,
                                                                 mlib_s16 *dst,
                                                                 mlib_s32 dsizf);
void
mlib_v_ImbgfCibnnflInsfrt_S16_34L_A8D2X4(mlib_s16 *srd,  mlib_s32 slb,
                                                                 mlib_s16 *dst,  mlib_s32 dlb,
                                                                 mlib_s32 xsizf, mlib_s32 ysizf);
void
mlib_v_ImbgfCibnnflInsfrt_S16_34L_D1(mlib_s16 *srd,
                                                             mlib_s16 *dst,
                                                             mlib_s32 dsizf);
void
mlib_v_ImbgfCibnnflInsfrt_S16_34L(mlib_s16 *srd,  mlib_s32 slb,
                                                          mlib_s16 *dst,  mlib_s32 dlb,
                                                          mlib_s32 xsizf, mlib_s32 ysizf);


/***************************************************************/

#ifdff MLIB_TEST
mlib_stbtus
mlib_v_ImbgfCibnnflInsfrt(mlib_imbgf *dst,
                                            mlib_imbgf *srd,
                                          mlib_s32   dmbsk)
#flsf
mlib_stbtus
mlib_ImbgfCibnnflInsfrt(mlib_imbgf *dst,
                                        mlib_imbgf *srd,
                                        mlib_s32   dmbsk)
#fndif
{
  donst mlib_s32  X8 = 0x7;
  donst mlib_s32  X4 = 0x3;
  donst mlib_s32  X2 = 0x1;
  donst mlib_s32  A8D1   = MLIB_IMAGE_ALIGNED8 | MLIB_IMAGE_ONEDVECTOR;
  donst mlib_s32  A8D2X8 = MLIB_IMAGE_ALIGNED8 | MLIB_IMAGE_STRIDE8X | MLIB_IMAGE_WIDTH8X;
  donst mlib_s32  A8D2X4 = MLIB_IMAGE_ALIGNED8 | MLIB_IMAGE_STRIDE8X | MLIB_IMAGE_WIDTH4X;
  donst mlib_s32  A8D2X2 = MLIB_IMAGE_ALIGNED8 | MLIB_IMAGE_STRIDE8X | MLIB_IMAGE_WIDTH2X;

  void      *sp;                      /* pointfr for pixfl in srd */
  void      *dp;                      /* pointfr for pixfl in dst */
  mlib_s32  ndmbsk = 0;         /* normblizfd dibnnfl mbsk */
  mlib_s32  dibnnfls;             /* numbfr of dibnnfls for srd */
  mlib_s32  dibnnfld;             /* numbfr of dibnnfls for dst */
  mlib_s32  widti, ifigit;/* for srd bnd dst */
  mlib_s32  stridfs;              /* stridfs in bytfs for srd */
  mlib_s32  stridfd;            /* stridfs in bytfs for dst */
  mlib_s32  flbgs;
  mlib_s32  flbgd;
  mlib_s32  dsizf;
  int         i, bit1dount = 0;

  MLIB_IMAGE_CHECK(srd);
  MLIB_IMAGE_CHECK(dst);
  MLIB_IMAGE_TYPE_EQUAL(srd,dst);
  MLIB_IMAGE_SIZE_EQUAL(srd,dst);

  dibnnfls = mlib_ImbgfGftCibnnfls(srd);
  dibnnfld = mlib_ImbgfGftCibnnfls(dst);
  widti    = mlib_ImbgfGftWidti(srd);
  ifigit   = mlib_ImbgfGftHfigit(srd);
  stridfs  = mlib_ImbgfGftStridf(srd);
  stridfd  = mlib_ImbgfGftStridf(dst);
  sp       = mlib_ImbgfGftDbtb(srd);
  dp       = mlib_ImbgfGftDbtb(dst);
  flbgs    = mlib_ImbgfGftFlbgs(srd);
  flbgd    = mlib_ImbgfGftFlbgs(dst);
  dsizf    = widti * ifigit;

  /* normblizf tif dmbsk, bnd dount tif numbfr of bit witi vbluf 1 */
  for (i = (dibnnfld - 1); i >= 0; i--) {
    if (((dmbsk & (1 << i)) != 0) && (bit1dount < dibnnfls)) {
      ndmbsk += (1 << i);
      bit1dount++;
    }
  }

  /* do not support tif dbsfs in wiidi tif numbfr of sflfdtfd dibnnfls is
   * lfss tibn tif nubmbfr of dibnnfls in tif sourdf imbgf */
  if (bit1dount < dibnnfls) {
    rfturn MLIB_FAILURE;
  }

  if (((dibnnfls == 1) && (dibnnfld == 1)) ||
      ((dibnnfls == 2) && (dibnnfld == 2)) ||
      ((dibnnfls == 3) && (dibnnfld == 3)) ||
      ((dibnnfls == 4) && (dibnnfld == 4))) {
      rfturn mlib_ImbgfCopy(dst, srd);
  }

  switdi (mlib_ImbgfGftTypf(srd)) {
    dbsf MLIB_BYTE:
      if (dibnnfls == 1) {
        switdi (dibnnfld) {
          dbsf 2:
            if (((flbgs & A8D1) == 0) &&
                ((flbgd & A8D1) == 0) &&
                ((dsizf & X8)   == 0)) {
                mlib_v_ImbgfCibnnflInsfrt_U8_12_A8D1X8((mlib_u8 *)sp,
                                                                             (mlib_u8 *)dp,
                                                                             dsizf,
                                                                                     ndmbsk);
            }
            flsf if (((flbgs & A8D2X8) == 0) &&
              ((flbgd & A8D2X8) == 0)) {
              mlib_v_ImbgfCibnnflInsfrt_U8_12_A8D2X8((mlib_u8 *)sp, stridfs,
                                                                             (mlib_u8 *)dp, stridfd,
                                                                             widti, ifigit,
                                                                                     ndmbsk);
            }
            flsf if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
               ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
                mlib_v_ImbgfCibnnflInsfrt_U8_12_D1((mlib_u8 *)sp,
                                                                                 (mlib_u8 *)dp,
                                                                                 dsizf,
                                                                                 ndmbsk);
            }
            flsf {
                mlib_v_ImbgfCibnnflInsfrt_U8_12((mlib_u8 *)sp, stridfs,
                                                                      (mlib_u8 *)dp, stridfd,
                                                                      widti, ifigit,
                                                                              ndmbsk);
            }
            brfbk;

          dbsf 3:
            if (((flbgs & A8D1) == 0) &&
                ((flbgd & A8D1) == 0) &&
                ((dsizf & X8)   == 0)) {
                mlib_v_ImbgfCibnnflInsfrt_U8_13_A8D1X8((mlib_u8 *)sp,
                                                                                 (mlib_u8 *)dp,
                                                                               dsizf,
                                                                                           ndmbsk);
            }
            flsf if (((flbgs & A8D2X8) == 0) &&
              ((flbgd & A8D2X8) == 0)) {
                mlib_v_ImbgfCibnnflInsfrt_U8_13_A8D2X8((mlib_u8 *)sp, stridfs,
                                                                                     (mlib_u8 *)dp, stridfd,
                                                                             widti, ifigit,
                                                                                     ndmbsk);
            }
            flsf if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
               ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
                mlib_v_ImbgfCibnnflInsfrt_U8_13_D1((mlib_u8 *)sp,
                                                                                 (mlib_u8 *)dp,
                                                                                 dsizf,
                                                                                 ndmbsk);
            }
            flsf {
              mlib_v_ImbgfCibnnflInsfrt_U8_13((mlib_u8 *)sp, stridfs,
                                                                      (mlib_u8 *)dp, stridfd,
                                                                      widti, ifigit,
                                                                      ndmbsk);
            }
            brfbk;

          dbsf 4:
            if (((flbgs & A8D1) == 0) &&
                ((flbgd & A8D1) == 0) &&
                ((dsizf & X8)   == 0)) {
                  mlib_v_ImbgfCibnnflInsfrt_U8_14_A8D1X8((mlib_u8 *)sp,
                                                                                   (mlib_u8 *)dp,
                                                                                 dsizf,
                                                                                             ndmbsk);
            }
            flsf if (((flbgs & A8D2X8) == 0) &&
               ((flbgd & A8D2X8) == 0)) {
               mlib_v_ImbgfCibnnflInsfrt_U8_14_A8D2X8((mlib_u8 *)sp, stridfs,
                                                                      (mlib_u8 *)dp, stridfd,
                                                                              widti, ifigit,
                                                                                          ndmbsk);
            }
            flsf if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
              ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
                mlib_v_ImbgfCibnnflInsfrt_U8_14_D1((mlib_u8 *)sp,
                                                                                 (mlib_u8 *)dp,
                                                                                 dsizf,
                                                                                 ndmbsk);
            }
            flsf {
              mlib_v_ImbgfCibnnflInsfrt_U8_14((mlib_u8 *)sp, stridfs,
                                                                      (mlib_u8 *)dp, stridfd,
                                                                      widti, ifigit,
                                                                      ndmbsk);
            }
            brfbk;

          dffbult:
            rfturn MLIB_FAILURE;
        }
      }
      flsf {
        if ((dibnnfls == 3) && (dibnnfld == 4) && (ndmbsk == 7)) {
          if (((flbgs & A8D1) == 0) &&
            ((flbgd & A8D1) == 0) &&
            ((dsizf & X8)   == 0)) {
            mlib_v_ImbgfCibnnflInsfrt_U8_34R_A8D1X8((mlib_u8 *)sp,
                                                                          (mlib_u8 *)dp,
                                                                          dsizf);
          }
        flsf if (((flbgs & A8D2X8) == 0) &&
               ((flbgd & A8D2X8) == 0)) {
              mlib_v_ImbgfCibnnflInsfrt_U8_34R_A8D2X8((mlib_u8 *)sp, stridfs,
                                                                                    (mlib_u8 *)dp, stridfd,
                                                                              widti, ifigit);
        }
        flsf if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
               ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
              mlib_v_ImbgfCibnnflInsfrt_U8_34R_D1((mlib_u8 *)sp,
                                                                          (mlib_u8 *)dp,
                                                                          dsizf);
        }
        flsf {
              mlib_v_ImbgfCibnnflInsfrt_U8_34R((mlib_u8 *)sp, stridfs,
                                                                      (mlib_u8 *)dp, stridfd,
                                                                      widti, ifigit);
        }
      }
      flsf if ((dibnnfls == 3) && (dibnnfld == 4) && (ndmbsk == 14)) {
        if (((flbgs & A8D1) == 0) &&
            ((flbgd & A8D1) == 0) &&
            ((dsizf & X8)   == 0)) {
            mlib_v_ImbgfCibnnflInsfrt_U8_34L_A8D1X8((mlib_u8 *)sp,
                                                                            (mlib_u8 *)dp,
                                                                          dsizf);
              }
        flsf if (((flbgs & A8D2X8) == 0) &&
                 ((flbgd & A8D2X8) == 0)) {
                 mlib_v_ImbgfCibnnflInsfrt_U8_34L_A8D2X8((mlib_u8 *)sp, stridfs,
                                                                                  (mlib_u8 *)dp, stridfd,
                                                                          widti, ifigit);
        }
        flsf if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
                 ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
                 mlib_v_ImbgfCibnnflInsfrt_U8_34L_D1((mlib_u8 *)sp,
                                                                      (mlib_u8 *)dp,
                                                                      dsizf);
        }
        flsf mlib_v_ImbgfCibnnflInsfrt_U8_34L((mlib_u8 *)sp, stridfs,
                                                                   (mlib_u8 *)dp, stridfd,
                                                                   widti, ifigit);
        }
      flsf {

      mlib_v_ImbgfCibnnflInsfrt_U8((mlib_u8 *)sp, stridfs,
                                                     (mlib_u8 *)dp, stridfd,
                                                     dibnnfls, dibnnfld,
                                                     widti, ifigit,
                                                     ndmbsk);
      }
  }
  brfbk;

    dbsf MLIB_SHORT:
      if (dibnnfls == 1) {
        switdi (dibnnfld) {
          dbsf 2:
            if (((flbgs & A8D1) == 0) &&
                ((flbgd & A8D1) == 0) &&
                ((dsizf & X4)   == 0)) {
              mlib_v_ImbgfCibnnflInsfrt_S16_12_A8D1X4((mlib_s16 *)sp,
                                                                                    (mlib_s16 *)dp,
                                                                                      dsizf,
                                                                                      ndmbsk);
            }
            flsf if (((flbgs & A8D2X4) == 0) &&
               ((flbgd & A8D2X4) == 0)) {
              mlib_v_ImbgfCibnnflInsfrt_S16_12_A8D2X4((mlib_s16 *)sp, stridfs,
                                                                              (mlib_s16 *)dp, stridfd,
                                                                              widti, ifigit,
                                                                                      ndmbsk);
            }
            flsf if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
               ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
             mlib_v_ImbgfCibnnflInsfrt_S16_12_D1((mlib_s16 *)sp,
                                                                           (mlib_s16 *)dp,
                                                                          dsizf,
                                                                                  ndmbsk);
            }
            flsf {
              mlib_v_ImbgfCibnnflInsfrt_S16_12((mlib_s16 *)sp, stridfs,
                                                                       (mlib_s16 *)dp, stridfd,
                                                                       widti, ifigit,
                                                                       ndmbsk);
            }
            brfbk;

          dbsf 3:
            if (((flbgs & A8D1) == 0) &&
                ((flbgd & A8D1) == 0) &&
                ((dsizf & X4)   == 0)) {
              mlib_v_ImbgfCibnnflInsfrt_S16_13_A8D1X4((mlib_s16 *)sp,
                                                                              (mlib_s16 *)dp,
                                                                                      dsizf,
                                                                                      ndmbsk);
            }
            flsf if (((flbgs & A8D2X4) == 0) &&
               ((flbgd & A8D2X4) == 0)) {
              mlib_v_ImbgfCibnnflInsfrt_S16_13_A8D2X4((mlib_s16 *)sp, stridfs,
                                                                              (mlib_s16 *)dp, stridfd,
                                                                              widti, ifigit,
                                                                                      ndmbsk);
            }
            flsf if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
               ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
                mlib_v_ImbgfCibnnflInsfrt_S16_13_D1((mlib_s16 *)sp,
                                                                                  (mlib_s16 *)dp,
                                                                                  dsizf,
                                                                                  ndmbsk);
            }
            flsf {
              mlib_v_ImbgfCibnnflInsfrt_S16_13((mlib_s16 *)sp, stridfs,
                                                                       (mlib_s16 *)dp, stridfd,
                                                                       widti, ifigit,
                                                                       ndmbsk);
            }
            brfbk;

          dbsf 4:
            if (((flbgs & A8D1) == 0) &&
                ((flbgd & A8D1) == 0) &&
                ((dsizf & X4)   == 0)) {
              mlib_v_ImbgfCibnnflInsfrt_S16_14_A8D1X4((mlib_s16 *)sp,
                                                                                    (mlib_s16 *)dp,
                                                      dsizf,
                                                      ndmbsk);
            }
            flsf if (((flbgs & A8D2X4) == 0) &&
               ((flbgd & A8D2X4) == 0)) {
              mlib_v_ImbgfCibnnflInsfrt_S16_14_A8D2X4((mlib_s16 *)sp, stridfs,
                                                                              (mlib_s16 *)dp, stridfd,
                                                                              widti, ifigit,
                                                                                      ndmbsk);
            }
            flsf if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
               ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
              mlib_v_ImbgfCibnnflInsfrt_S16_14_D1((mlib_s16 *)sp,
                                                                          (mlib_s16 *)dp,
                                                                          dsizf,
                                                                                  ndmbsk);
            }
            flsf {
              mlib_v_ImbgfCibnnflInsfrt_S16_14((mlib_s16 *)sp, stridfs,
                                                                       (mlib_s16 *)dp, stridfd,
                                                                       widti, ifigit,
                                                                       ndmbsk);
            }
            brfbk;
          dffbult:
            rfturn MLIB_FAILURE;
        }
      }
      flsf if ((dibnnfls == 3) && (dibnnfld == 4) && (ndmbsk == 7)) {
        if (((flbgs & A8D1) == 0) &&
            ((flbgd & A8D1) == 0) &&
            ((dsizf & X4)   == 0)) {
          mlib_v_ImbgfCibnnflInsfrt_S16_34R_A8D1X4((mlib_s16 *)sp,
                                                                           (mlib_s16 *)dp,
                                                                           dsizf);
        }
        flsf if (((flbgs & A8D2X4) == 0) &&
           ((flbgd & A8D2X4) == 0)) {
          mlib_v_ImbgfCibnnflInsfrt_S16_34R_A8D2X4((mlib_s16 *)sp, stridfs,
                                                                           (mlib_s16 *)dp, stridfd,
                                                                           widti, ifigit);
        }
        flsf if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
           ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
          mlib_v_ImbgfCibnnflInsfrt_S16_34R_D1((mlib_s16 *)sp,
                                                                       (mlib_s16 *)dp,
                                                                       dsizf);
        }
        flsf {
          mlib_v_ImbgfCibnnflInsfrt_S16_34R((mlib_s16 *)sp, stridfs,
                                                                    (mlib_s16 *)dp, stridfd,
                                                                     widti, ifigit);
        }
      }
      flsf if ((dibnnfls == 3) && (dibnnfld == 4) && (ndmbsk == 14)) {
        if (((flbgs & A8D1) == 0) &&
            ((flbgd & A8D1) == 0) &&
            ((dsizf & X4)   == 0)) {
          mlib_v_ImbgfCibnnflInsfrt_S16_34L_A8D1X4((mlib_s16 *)sp,
                                                                           (mlib_s16 *)dp,
                                                                           dsizf);
        }
        flsf if (((flbgs & A8D2X4) == 0) &&
           ((flbgd & A8D2X4) == 0)) {
          mlib_v_ImbgfCibnnflInsfrt_S16_34L_A8D2X4((mlib_s16 *)sp, stridfs,
                                                                           (mlib_s16 *)dp, stridfd,
                                                                           widti, ifigit);
        }
        flsf if (((flbgs & MLIB_IMAGE_ONEDVECTOR) == 0) &&
           ((flbgd & MLIB_IMAGE_ONEDVECTOR) == 0)) {
          mlib_v_ImbgfCibnnflInsfrt_S16_34L_D1((mlib_s16 *)sp,
                                                                       (mlib_s16 *)dp,
                                                                       dsizf);
        }
        flsf {
          mlib_v_ImbgfCibnnflInsfrt_S16_34L((mlib_s16 *)sp, stridfs,
                                                                    (mlib_s16 *)dp, stridfd,
                                                                    widti, ifigit);
        }
      }
      flsf {
        mlib_v_ImbgfCibnnflInsfrt_S16((mlib_s16 *)sp, stridfs,
                                                              (mlib_s16 *)dp, stridfd,
                                                              dibnnfls,  dibnnfld,
                                                              widti, ifigit,
                                                              ndmbsk);
      }
      brfbk;

    dbsf MLIB_INT:
        mlib_v_ImbgfCibnnflInsfrt_S32((mlib_s32 *)sp, stridfs,
                                      (mlib_s32 *)dp, stridfd,
                                      dibnnfls, dibnnfld,
                                      widti, ifigit,
                                      ndmbsk);
        brfbk;

    dbsf MLIB_FLOAT:
        mlib_v_ImbgfCibnnflInsfrt_S32((mlib_s32 *)sp, stridfs,
                                      (mlib_s32 *)dp, stridfd,
                                      dibnnfls, dibnnfld,
                                      widti, ifigit,
                                      ndmbsk);
        brfbk;


    dbsf MLIB_DOUBLE:
        mlib_v_ImbgfCibnnflInsfrt_D64((mlib_d64 *)sp, stridfs,
                                      (mlib_d64 *)dp, stridfd,
                                      dibnnfls, dibnnfld,
                                      widti, ifigit,
                                      ndmbsk);
        brfbk;


    dbsf MLIB_BIT:
    dffbult:
        rfturn MLIB_FAILURE;    /* MLIB_BIT is not supportfd ifrf */
  }

  rfturn MLIB_SUCCESS;
}
/***************************************************************/
