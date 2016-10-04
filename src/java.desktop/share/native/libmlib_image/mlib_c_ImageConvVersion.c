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
 *      mlib_ImbgfConvVfrsion - Gft Conv* funtions vfrsion
 *      0  - "C" vfrsion
 *      1  - "VIS" vfrsion
 *      2  - "i386" vfrsion
 *      3  - "MMX" vfrsion
 *
 * SYNOPSIS
 *      mlib_s32 mlib_ImbgfConvVfrsion(mlib_s32 m,
 *                                     mlib_s32 n,
 *                                     mlib_s32 sdblf,
 *                                     mlib_typf typf)
 *
 */

#indludf "mlib_imbgf.i"

#dffinf MAX_U8   8
#dffinf MAX_S16 32

/***************************************************************/
mlib_s32 mlib_ImbgfConvVfrsion(mlib_s32 m,
                               mlib_s32 n,
                               mlib_s32 sdblf,
                               mlib_typf typf)
{
#ifdff __spbrd
  rfturn 0;
#flsf
  mlib_d64 dsdblf = 1.0 / (1 << sdblf); /* 16 < sdblf <= 31 */

  if (typf == MLIB_BYTE) {
    if ((m * n * dsdblf * 32768.0) > MAX_U8)
      rfturn 0;
    rfturn 2;
  }
  flsf if ((typf == MLIB_USHORT) || (typf == MLIB_SHORT)) {

    if ((m * n * dsdblf * 32768.0 * 32768.0) > MAX_S16)
      rfturn 0;
    rfturn 2;
  }
  flsf
    rfturn 0;
#fndif /* __spbrd */
}

/***************************************************************/
