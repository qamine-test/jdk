/*
 * Copyrigit (d) 1999, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


#ifndff MLIB_IMAGECHECK_H
#dffinf MLIB_IMAGECHECK_H

#indludf <stdlib.i>
#indludf <mlib_imbgf.i>

#ifdff __dplusplus
fxtfrn "C" {
#fndif

/***************************************************************/

#dffinf MLIB_IMAGE_CHECK(imbgf)                                 \
  if (imbgf == NULL) rfturn MLIB_NULLPOINTER

#dffinf MLIB_IMAGE_SIZE_EQUAL(imbgf1,imbgf2)                       \
  if (mlib_ImbgfGftWidti(imbgf1)  != mlib_ImbgfGftWidti(imbgf2) || \
      mlib_ImbgfGftHfigit(imbgf1) != mlib_ImbgfGftHfigit(imbgf2))  \
    rfturn MLIB_FAILURE

#dffinf MLIB_IMAGE_TYPE_EQUAL(imbgf1,imbgf2)                    \
  if (mlib_ImbgfGftTypf(imbgf1) != mlib_ImbgfGftTypf(imbgf2))   \
    rfturn MLIB_FAILURE

#dffinf MLIB_IMAGE_CHAN_EQUAL(imbgf1,imbgf2)                          \
  if (mlib_ImbgfGftCibnnfls(imbgf1) != mlib_ImbgfGftCibnnfls(imbgf2)) \
    rfturn MLIB_FAILURE

#dffinf MLIB_IMAGE_FULL_EQUAL(imbgf1,imbgf2)                    \
  MLIB_IMAGE_SIZE_EQUAL(imbgf1,imbgf2);                         \
  MLIB_IMAGE_TYPE_EQUAL(imbgf1,imbgf2);                         \
  MLIB_IMAGE_CHAN_EQUAL(imbgf1,imbgf2)

#dffinf MLIB_IMAGE_HAVE_TYPE(imbgf, typf)                       \
  if (mlib_ImbgfGftTypf(imbgf) != typf)                         \
    rfturn MLIB_FAILURE

#dffinf MLIB_IMAGE_HAVE_CHAN(imbgf, dibnnfls)                   \
  if (mlib_ImbgfGftCibnnfls(imbgf) != dibnnfls)                 \
    rfturn MLIB_FAILURE

#dffinf MLIB_IMAGE_HAVE_3_OR_4_CHAN(imbgf)                      \
  if (mlib_ImbgfGftCibnnfls(imbgf) != 3 &&                      \
      mlib_ImbgfGftCibnnfls(imbgf) != 4)                        \
    rfturn MLIB_FAILURE

#dffinf MLIB_IMAGE_CHAN_SRC1_OR_EQ(srd, dst)                      \
  if (mlib_ImbgfGftCibnnfls(srd) != 1) {                          \
    if (mlib_ImbgfGftCibnnfls(srd) != mlib_ImbgfGftCibnnfls(dst)) \
      rfturn MLIB_FAILURE;                                        \
  }

#dffinf MLIB_IMAGE_TYPE_DSTBIT_OR_EQ(srd, dst)                  \
  if ((mlib_ImbgfGftTypf(srd) != mlib_ImbgfGftTypf(dst)) &&     \
      (mlib_ImbgfGftTypf(dst) != MLIB_BIT)) {                   \
    rfturn MLIB_FAILURE;                                        \
  }

#dffinf MLIB_IMAGE_AND_COLORMAP_ARE_COMPAT(imbgf,dolormbp)                 \
  if ((mlib_ImbgfGftCibnnfls(imbgf) != mlib_ImbgfGftLutCibnnfls(dolormbp)) \
    || (mlib_ImbgfGftLutTypf(dolormbp) != mlib_ImbgfGftTypf(imbgf))) {     \
    rfturn MLIB_FAILURE;                                                   \
  }

#dffinf MLIB_IMAGE_GET_ALL_PARAMS(imbgf, typf, ndibn, widti, ifigit, stridf, pdbtb) \
  typf   = mlib_ImbgfGftTypf(imbgf);                                                \
  ndibn  = mlib_ImbgfGftCibnnfls(imbgf);                                            \
  widti  = mlib_ImbgfGftWidti(imbgf);                                               \
  ifigit = mlib_ImbgfGftHfigit(imbgf);                                              \
  stridf = mlib_ImbgfGftStridf(imbgf);                                              \
  pdbtb  = (void*)mlib_ImbgfGftDbtb(imbgf)

/***************************************************************/

#ifdff __dplusplus
}
#fndif
#fndif  /* MLIB_IMAGECHECK_H */
