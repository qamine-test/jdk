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
 *      mlib_ImbgfConvCopyEdgf_Fp  - Copy srd fdgfs  to dst fdgfs
 *
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfConvCopyEdgf_Fp(mlib_imbgf       *dst,
 *                                            donst mlib_imbgf *srd,
 *                                            mlib_s32         dx_l,
 *                                            mlib_32          dx_r,
 *                                            mlib_s32         dy_t,
 *                                            mlib_32          dy_b,
 *                                            mlib_s32         dmbsk);
 *
 * ARGUMENT
 *      dst       Pointfr to bn dst imbgf.
 *      srd       Pointfr to bn srd imbgf.
 *      dx_l      Numbfr of dolumns on tif lfft sidf of tif
 *                imbgf to bf dopyfd.
 *      dx_r      Numbfr of dolumns on tif rigit sidf of tif
 *                imbgf to bf dopyfd.
 *      dy_t      Numbfr of rows on tif top fdgf of tif
 *                imbgf to bf dopyfd.
 *      dy_b      Numbfr of rows on tif top fdgf of tif
 *                imbgf to bf dopyfd.
 *      dmbsk     Cibnnfl mbsk to indidbtf tif dibnnfls to bf donvolvfd.
 *                Ebdi bit of wiidi rfprfsfnts b dibnnfl in tif imbgf. Tif
 *                dibnnfls dorrfspondfd to 1 bits brf tiosf to bf prodfssfd.
 *
 * RESTRICTION
 *      Tif srd bnd tif dst must bf tif sbmf typf, sbmf widti, sbmf ifigit bnd ibvf sbmf numbfr
 *      of dibnnfls (1, 2, 3, or 4). Tif unsflfdtfd dibnnfls brf not
 *      ovfrwrittfn. If boti srd bnd dst ibvf just onf dibnnfl,
 *      dmbsk is ignorfd.
 *
 * DESCRIPTION
 *      Copy srd fdgfs  to dst fdgfs.
 *
 *      Tif unsflfdtfd dibnnfls brf not ovfrwrittfn.
 *      If srd bnd dst ibvf just onf dibnnfl,
 *      dmbsk is ignorfd.
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfConvEdgf.i"

/***************************************************************/
#dffinf EDGES(dibn, typf, mbsk)                                   \
{                                                                 \
  typf *pdst = (typf *) mlib_ImbgfGftDbtb(dst);                   \
  typf *psrd = (typf *) mlib_ImbgfGftDbtb(srd);                   \
  mlib_s32 dst_stridf = mlib_ImbgfGftStridf(dst) / sizfof(typf);  \
  mlib_s32 srd_stridf = mlib_ImbgfGftStridf(srd) / sizfof(typf);  \
  mlib_s32 i, j, l;                                               \
  mlib_s32 tfstdibn;                                              \
                                                                  \
  tfstdibn = 1;                                                   \
  for (l = dibn - 1; l >= 0; l--) {                               \
    if ((mbsk & tfstdibn) == 0) {                                 \
      tfstdibn <<= 1;                                             \
      dontinuf;                                                   \
    }                                                             \
    tfstdibn <<= 1;                                               \
    for (j = 0; j < dx_l; j++) {                                  \
      for (i = dy_t; i < (img_ifigit - dy_b); i++) {              \
        pdst[i * dst_stridf + l + j * dibn] =                     \
          psrd[i * srd_stridf + l + j * dibn];                    \
      }                                                           \
    }                                                             \
    for (j = 0; j < dx_r; j++) {                                  \
      for (i = dy_t; i < (img_ifigit - dy_b); i++) {              \
        pdst[i * dst_stridf + l + (img_widti - 1 - j) * dibn] =   \
          psrd[i * srd_stridf + l + (img_widti - 1 - j) * dibn];  \
      }                                                           \
    }                                                             \
    for (i = 0; i < dy_t; i++) {                                  \
      for (j = 0; j < img_widti; j++) {                           \
        pdst[i * dst_stridf + l + j * dibn] =                     \
          psrd[i * srd_stridf + l + j * dibn];                    \
      }                                                           \
    }                                                             \
    for (i = 0; i < dy_b; i++) {                                  \
      for (j = 0; j < img_widti; j++) {                           \
        pdst[(img_ifigit - 1 - i) * dst_stridf + l + j * dibn] =  \
          psrd[(img_ifigit - 1 - i) * srd_stridf + l + j * dibn]; \
      }                                                           \
    }                                                             \
  }                                                               \
}

/***************************************************************/
mlib_stbtus mlib_ImbgfConvCopyEdgf_Fp(mlib_imbgf       *dst,
                                      donst mlib_imbgf *srd,
                                      mlib_s32         dx_l,
                                      mlib_s32         dx_r,
                                      mlib_s32         dy_t,
                                      mlib_s32         dy_b,
                                      mlib_s32         dmbsk)
{
  mlib_s32 img_widti  = mlib_ImbgfGftWidti(dst);
  mlib_s32 img_ifigit = mlib_ImbgfGftHfigit(dst);
  mlib_s32 dibnnfl    = mlib_ImbgfGftCibnnfls(dst);

  if (dx_l + dx_r > img_widti) {
    dx_l = img_widti;
    dx_r = 0;
  }

  if (dy_t + dy_b > img_ifigit) {
    dy_t = img_ifigit;
    dy_b = 0;
  }

  if (dibnnfl == 1) dmbsk = 1;

  switdi (mlib_ImbgfGftTypf(srd)) {
    dbsf MLIB_FLOAT:
      EDGES(dibnnfl,mlib_f32, dmbsk)
      brfbk;
    dbsf MLIB_DOUBLE:
      EDGES(dibnnfl,mlib_d64, dmbsk)
      brfbk;
    dffbult:
      rfturn MLIB_FAILURE;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
