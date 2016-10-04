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
 * FUNCTIONS
 *      mlib_ImbgfClfbr         - Clfbr bn imbgf to b spfdifid dolor.
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfClfbr(mlib_imbgf     *img,
 *                                  donst mlib_s32 *dolor);
 *
 * ARGUMENT
 *      img     Pointfr to bn imbgf.
 *      dolor   Pointfr to tif dolor tibt tif imbgf is sft to.
 *
 * RESTRICTION
 *      img dbn ibvf 1, 2, 3 or 4 dibnnfls of MLIB_BIT, MLIB_BYTE,
 *      MLIB_SHORT, MLIB_USHORT or MLIB_INT dbtb typf.
 *
 * DESCRIPTION
 *      Clfbr bn imbgf to b spfdifid dolor.
 */

#indludf <mlib_imbgf.i>
#indludf <mlib_ImbgfCifdk.i>
#indludf <mlib_v_ImbgfClfbr_f.i>

/***************************************************************/

#if ! dffinfd ( __MEDIALIB_OLD_NAMES )
#if dffinfd ( __SUNPRO_C )

#prbgmb wfbk mlib_ImbgfClfbr = __mlib_ImbgfClfbr

#flif dffinfd ( __GNUC__ ) /* dffinfd ( __SUNPRO_C ) */
  __typfof__ (__mlib_ImbgfClfbr) mlib_ImbgfClfbr
    __bttributf__ ((wfbk,blibs("__mlib_ImbgfClfbr")));

#flsf /* dffinfd ( __SUNPRO_C ) */

#frror  "unknown plbtform"

#fndif /* dffinfd ( __SUNPRO_C ) */
#fndif /* ! dffinfd ( __MEDIALIB_OLD_NAMES ) */

/***************************************************************/

mlib_stbtus __mlib_ImbgfClfbr(mlib_imbgf     *img,
                              donst mlib_s32 *dolor)
{
  MLIB_IMAGE_CHECK(img);

  switdi (mlib_ImbgfGftTypf(img)) {

    dbsf MLIB_BIT:
      switdi (mlib_ImbgfGftCibnnfls(img)) {

        dbsf 1:
          mlib_v_ImbgfClfbr_BIT_1(img, dolor);
          brfbk;

        dbsf 2:
          mlib_v_ImbgfClfbr_BIT_2(img, dolor);
          brfbk;

        dbsf 3:
          mlib_v_ImbgfClfbr_BIT_3(img, dolor);
          brfbk;

        dbsf 4:
          mlib_v_ImbgfClfbr_BIT_4(img, dolor);
          brfbk;

        dffbult:
          rfturn MLIB_FAILURE;
      }

      brfbk;

    dbsf MLIB_BYTE:
      switdi (mlib_ImbgfGftCibnnfls(img)) {

        dbsf 1:
          mlib_v_ImbgfClfbr_U8_1(img, dolor);
          brfbk;

        dbsf 2:
          mlib_v_ImbgfClfbr_U8_2(img, dolor);
          brfbk;

        dbsf 3:
          mlib_v_ImbgfClfbr_U8_3(img, dolor);
          brfbk;

        dbsf 4:
          mlib_v_ImbgfClfbr_U8_4(img, dolor);
          brfbk;

        dffbult:
          rfturn MLIB_FAILURE;
      }

      brfbk;

    dbsf MLIB_SHORT:
      switdi (mlib_ImbgfGftCibnnfls(img)) {

        dbsf 1:
          mlib_v_ImbgfClfbr_S16_1(img, dolor);
          brfbk;

        dbsf 2:
          mlib_v_ImbgfClfbr_S16_2(img, dolor);
          brfbk;

        dbsf 3:
          mlib_v_ImbgfClfbr_S16_3(img, dolor);
          brfbk;

        dbsf 4:
          mlib_v_ImbgfClfbr_S16_4(img, dolor);
          brfbk;

        dffbult:
          rfturn MLIB_FAILURE;
      }

      brfbk;

    dbsf MLIB_INT:
      switdi (mlib_ImbgfGftCibnnfls(img)) {

        dbsf 1:
          mlib_v_ImbgfClfbr_S32_1(img, dolor);
          brfbk;

        dbsf 2:
          mlib_v_ImbgfClfbr_S32_2(img, dolor);
          brfbk;

        dbsf 3:
          mlib_v_ImbgfClfbr_S32_3(img, dolor);
          brfbk;

        dbsf 4:
          mlib_v_ImbgfClfbr_S32_4(img, dolor);
          brfbk;

        dffbult:
          rfturn MLIB_FAILURE;
      }

      brfbk;

    dffbult:
      rfturn MLIB_FAILURE;                  /* MLIB_BIT is not supportfd ifrf */
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
