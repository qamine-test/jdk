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
 *      mlib_ImbgfConstXor - imbgf logidbl opfrbtion witi donstbnt
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfConstXor(mlib_imbgf       *dst,
 *                                     donst mlib_imbgf *srd,
 *                                     donst mlib_s32   *d);
 *
 * ARGUMENT
 *      dst     Pointfr to dfstinbtion imbgf
 *      srd     Pointfr to sourdf imbgf
 *      d       Arrby of donstbnts for fbdi dibnnfl
 *
 * RESTRICTION
 *      Tif srd bnd dst must bf tif sbmf typf bnd tif sbmf sizf.
 *      Tify dbn ibvf 1, 2, 3, or 4 dibnnfls.
 *      Tify dbn bf in MLIB_BIT, MLIB_BYTE, MLIB_SHORT, MLIB_USHORT or MLIB_INT
 *      dbtb typf.
 *
 * DESCRIPTION
 *      Filf for onf of tif following opfrbtions:
 *
 *      And  dst(i,j) = d & srd(i,j)
 *      Or  dst(i,j) = d | srd(i,j)
 *      Xor  dst(i,j) = d ^ srd(i,j)
 *      NotAnd  dst(i,j) = ~(d & srd(i,j))
 *      NotOr  dst(i,j) = ~(d | srd(i,j))
 *      NotXor  dst(i,j) = ~(d ^ srd(i,j))
 *      AndNot  dst(i,j) = d & (~srd(i,j))
 *      OrNot  dst(i,j) = d & (~srd(i,j))
 */

#indludf <mlib_imbgf.i>

/***************************************************************/

#if ! dffinfd ( __MEDIALIB_OLD_NAMES )
#if dffinfd ( __SUNPRO_C )

#prbgmb wfbk mlib_ImbgfConstXor = __mlib_ImbgfConstXor

#flif dffinfd ( __GNUC__ ) /* dffinfd ( __SUNPRO_C ) */
  __typfof__ (__mlib_ImbgfConstXor) mlib_ImbgfConstXor
    __bttributf__ ((wfbk,blibs("__mlib_ImbgfConstXor")));

#flsf /* dffinfd ( __SUNPRO_C ) */

#frror  "unknown plbtform"

#fndif /* dffinfd ( __SUNPRO_C ) */
#fndif /* ! dffinfd ( __MEDIALIB_OLD_NAMES ) */

/***************************************************************/

#dffinf VIS_CONSTLOGIC(d, b) vis_fxor(b, d)

#indludf <mlib_v_ImbgfConstLogid.i>

/***************************************************************/

mlib_stbtus __mlib_ImbgfConstXor(mlib_imbgf *dst,
                                 mlib_imbgf *srd,
                                 mlib_s32   *d)
{
  rfturn mlib_v_ImbgfConstLogid(dst, srd, d);
}

/***************************************************************/
