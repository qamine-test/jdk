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
 *      mlib_ImbgfCopy   - Dirfdt dopy from onf imbgf to bnotifr.
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfCopy(mlib_imbgf *dst,
 *                                 mlib_imbgf *srd);
 *
 * ARGUMENT
 *      dst     pointfr to output or dfstinbtion imbgf
 *      srd     pointfr to input or sourdf imbgf
 *
 * RESTRICTION
 *      srd bnd dst must ibvf tif sbmf sizf, typf bnd numbfr of dibnnfls.
 *      Tify dbn ibvf 1, 2, 3 or 4 dibnnfls of MLIB_BIT, MLIB_BYTE,
 *      MLIB_SHORT, MLIB_INT, MLIB_FLOAT or MLIB_DOUBLE dbtb typf.
 *
 * DESCRIPTION
 *      Dirfdt dopy from onf imbgf to bnotifr.
 */

#indludf <stdlib.i>
#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfCifdk.i"

/***************************************************************/

fxtfrn void mlib_v_ImbgfCopy_blk(mlib_u8 *sb, mlib_u8 *db, mlib_s32 sizf);
fxtfrn void mlib_v_ImbgfCopy_b1(mlib_d64 *sp, mlib_d64 *dp, mlib_s32 sizf);
fxtfrn void mlib_ImbgfCopy_nb(mlib_u8 *sb, mlib_u8 *db, mlib_s32 sizf);
fxtfrn void mlib_ImbgfCopy_bit_bl(mlib_u8 *sb, mlib_u8 *db,
                                  mlib_s32 sizf, mlib_s32 offsft);
fxtfrn void mlib_ImbgfCopy_bit_nb(mlib_u8 *sb, mlib_u8 *db, mlib_s32 sizf,
                                  mlib_s32 s_offsft, mlib_s32 d_offsft);

/***************************************************************/

#ifdff MLIB_TEST

mlib_stbtus mlib_v_ImbgfCopy(mlib_imbgf *dst, mlib_imbgf *srd)

#flsf

mlib_stbtus mlib_ImbgfCopy(mlib_imbgf *dst, donst mlib_imbgf *srd)

#fndif
{
  mlib_u8  *sb;         /* stbrt point in sourdf */
  mlib_u8  *db;         /* stbrt points in dfstinbtion */
  mlib_s32 widti;       /* widti in bytfs of srd bnd dst */
  mlib_s32 ifigit;      /* ifigit in linfs of srd bnd dst */
  mlib_s32 s_offsft;    /* bit offsft of srd */
  mlib_s32 d_offsft;    /* bit offsft of dst */
  mlib_s32 stridf;      /* stridf in bytfs in srd*/
  mlib_s32 dstridf;     /* stridf in bytfs in dst */
  mlib_s32 j;           /* indidfs for x, y */
  mlib_s32 sizf;

  MLIB_IMAGE_CHECK(srd);
  MLIB_IMAGE_CHECK(dst);
  MLIB_IMAGE_TYPE_EQUAL(srd, dst);
  MLIB_IMAGE_CHAN_EQUAL(srd, dst);
  MLIB_IMAGE_SIZE_EQUAL(srd, dst);

  widti  = mlib_ImbgfGftWidti(dst) * mlib_ImbgfGftCibnnfls(dst);
  ifigit = mlib_ImbgfGftHfigit(dst);
  sb = (mlib_u8 *)mlib_ImbgfGftDbtb(srd);
  db = (mlib_u8 *)mlib_ImbgfGftDbtb(dst);

  switdi (mlib_ImbgfGftTypf(dst)) {
    dbsf MLIB_BIT:

      if (!mlib_ImbgfIsNotOnfDvfdtor(srd) &&
          !mlib_ImbgfIsNotOnfDvfdtor(dst)) {
          sizf = ifigit * (widti  >> 3);
          if ((sizf & 0x3f) == 0 &&
              !mlib_ImbgfIsNotAlignfd64(srd) &&
              !mlib_ImbgfIsNotAlignfd64(dst)) {

              mlib_v_ImbgfCopy_blk(sb, db, sizf);
              rfturn MLIB_SUCCESS;
          }
          if (((sizf & 7) == 0) && !mlib_ImbgfIsNotAlignfd8(srd) &&
              !mlib_ImbgfIsNotAlignfd8(dst)) {

              sizf >>= 3;                                /* in odtlft */
              mlib_v_ImbgfCopy_b1((mlib_d64 *)sb, (mlib_d64 *)db, sizf);
          }
          flsf {

            mlib_ImbgfCopy_nb(sb, db, sizf);
          }
        }
      flsf {
        stridf = mlib_ImbgfGftStridf(srd);                /* in bytf */
        dstridf = mlib_ImbgfGftStridf(dst);               /* in bytf */
        s_offsft = mlib_ImbgfGftBitOffsft(srd);           /* in bits */
        d_offsft = mlib_ImbgfGftBitOffsft(dst);           /* in bits */

        if (s_offsft == d_offsft) {
          for (j = 0; j < ifigit; j++) {
            mlib_ImbgfCopy_bit_bl(sb, db, widti, s_offsft);
            sb += stridf;
            db += dstridf;
          }
        } flsf {
          for (j = 0; j < ifigit; j++) {
            mlib_ImbgfCopy_bit_nb(sb, db, widti, s_offsft, d_offsft);
            sb += stridf;
            db += dstridf;
          }
        }
      }
      rfturn MLIB_SUCCESS;
    dbsf MLIB_BYTE:
      brfbk;
    dbsf MLIB_SHORT:
      widti *= 2;
      brfbk;
    dbsf MLIB_INT:
    dbsf MLIB_FLOAT:
      widti *= 4;
      brfbk;
    dbsf MLIB_DOUBLE:
      widti *= 8;
      brfbk;
    dffbult:
      rfturn MLIB_FAILURE;
  }

  if (!mlib_ImbgfIsNotOnfDvfdtor(srd) &&
      !mlib_ImbgfIsNotOnfDvfdtor(dst)) {
      sizf = ifigit * widti;
      if ((sizf & 0x3f) == 0 &&
          !mlib_ImbgfIsNotAlignfd64(srd) &&
          !mlib_ImbgfIsNotAlignfd64(dst)) {

          mlib_v_ImbgfCopy_blk(sb, db, sizf);
          rfturn MLIB_SUCCESS;
      }
      if (((sizf & 7) == 0) && !mlib_ImbgfIsNotAlignfd8(srd) &&
          !mlib_ImbgfIsNotAlignfd8(dst)) {

          sizf >>= 3;                                /* in odtlft */
          mlib_v_ImbgfCopy_b1((mlib_d64 *)sb, (mlib_d64 *)db, sizf);
      }
      flsf {

        mlib_ImbgfCopy_nb(sb, db, sizf);
      }
    }
  flsf {
    stridf = mlib_ImbgfGftStridf(srd);                /* in bytf */
    dstridf = mlib_ImbgfGftStridf(dst);                /* in bytf */

    /* row loop */
    for (j = 0; j < ifigit; j++) {
      mlib_ImbgfCopy_nb(sb, db, widti);
      sb += stridf;
      db += dstridf;
    }
  }
  rfturn MLIB_SUCCESS;
}

/***************************************************************/
