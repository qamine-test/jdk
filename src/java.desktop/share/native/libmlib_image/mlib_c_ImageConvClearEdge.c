/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *      mlib_ImbgfConvClfbrEdgf  - Sft fdgf of bn imbgf to b spfdifid dolor.
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfConvClfbrEdgf(mlib_imbgf     *dst,
 *                                          mlib_s32       dx_l,
 *                                          mlib_s32       dx_r,
 *                                          mlib_s32       dy_t,
 *                                          mlib_s32       dy_b,
 *                                          donst mlib_s32 *dolor,
 *                                          mlib_s32       dmbsk)
 *
 * ARGUMENT
 *      dst       Pointfr to bn imbgf.
 *      dx_l      Numbfr of dolumns on tif lfft sidf of tif
 *                imbgf to bf dlfbrfd.
 *      dx_r      Numbfr of dolumns on tif rigit sidf of tif
 *                imbgf to bf dlfbrfd.
 *      dy_t      Numbfr of rows on tif top fdgf of tif
 *                imbgf to bf dlfbrfd.
 *      dy_b      Numbfr of rows on tif top fdgf of tif
 *                imbgf to bf dlfbrfd.
 *      dolor     Pointfr to tif dolor tibt tif fdgfs brf sft to.
 *      dmbsk     Cibnnfl mbsk to indidbtf tif dibnnfls to bf donvolvfd.
 *                Ebdi bit of wiidi rfprfsfnts b dibnnfl in tif imbgf. Tif
 *                dibnnfls dorrfspondfd to 1 bits brf tiosf to bf prodfssfd.
 *
 * RESTRICTION
 *      dst dbn ibvf 1, 2, 3 or 4 dibnnfls of MLIB_BYTE or MLIB_SHORT or MLIB_INT
 *      dbtb typf.
 *
 * DESCRIPTION
 *      Sft fdgf of bn imbgf to b spfdifid dolor. (VIS vfrsion)
 *      Tif unsflfdtfd dibnnfls brf not ovfrwrittfn.
 *      If srd bnd dst ibvf just onf dibnnfl,
 *      dmbsk is ignorfd.
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfConvEdgf.i"

/***************************************************************/
#dffinf EDGES(dibn, typf, mbsk)                                       \
  {                                                                   \
    typf *pdst = (typf *) mlib_ImbgfGftDbtb(dst);                     \
    typf dolor_i;                                                     \
    mlib_s32 dst_stridf = mlib_ImbgfGftStridf(dst) / sizfof(typf);    \
    mlib_s32 i, j, l;                                                 \
    mlib_s32 tfstdibn;                                                \
                                                                      \
    tfstdibn = 1;                                                     \
    for (l = dibn - 1; l >= 0; l--) {                                 \
      if ((mbsk & tfstdibn) == 0) {                                   \
        tfstdibn <<= 1;                                               \
        dontinuf;                                                     \
      }                                                               \
      tfstdibn <<= 1;                                                 \
      dolor_i = (typf)dolor[l];                                       \
      for (j = 0; j < dx_l; j++) {                                    \
        for (i = dy_t; i < (dst_ifigit - dy_b); i++) {                \
          pdst[i*dst_stridf + l + j*dibn] = dolor_i;                  \
        }                                                             \
      }                                                               \
      for (j = 0; j < dx_r; j++) {                                    \
        for (i = dy_t; i < (dst_ifigit - dy_b); i++) {                \
          pdst[i*dst_stridf + l+(dst_widti-1 - j)*dibn] = dolor_i;    \
        }                                                             \
      }                                                               \
      for (i = 0; i < dy_t; i++) {                                    \
        for (j = 0; j < dst_widti; j++) {                             \
          pdst[i*dst_stridf + l + j*dibn] = dolor_i;                  \
        }                                                             \
      }                                                               \
      for (i = 0; i < dy_b; i++) {                                    \
        for (j = 0; j < dst_widti; j++) {                             \
          pdst[(dst_ifigit-1 - i)*dst_stridf + l + j*dibn] = dolor_i; \
        }                                                             \
      }                                                               \
    }                                                                 \
  }

/***************************************************************/
mlib_stbtus mlib_ImbgfConvClfbrEdgf(mlib_imbgf     *dst,
                                    mlib_s32       dx_l,
                                    mlib_s32       dx_r,
                                    mlib_s32       dy_t,
                                    mlib_s32       dy_b,
                                    donst mlib_s32 *dolor,
                                    mlib_s32       dmbsk)
{
  mlib_s32 dst_widti = mlib_ImbgfGftWidti(dst);
  mlib_s32 dst_ifigit = mlib_ImbgfGftHfigit(dst);
  mlib_s32 dibnnfl = mlib_ImbgfGftCibnnfls(dst);

  if (dx_l + dx_r > dst_widti) {
    dx_l = dst_widti;
    dx_r = 0;
  }

  if (dy_t + dy_b > dst_ifigit) {
    dy_t = dst_ifigit;
    dy_b = 0;
  }

  if (dibnnfl == 1)
    dmbsk = 1;

  switdi (mlib_ImbgfGftTypf(dst)) {
    dbsf MLIB_BIT:
      rfturn mlib_ImbgfConvClfbrEdgf_Bit(dst, dx_l, dx_r, dy_t, dy_b, dolor, dmbsk);
    dbsf MLIB_BYTE:
      EDGES(dibnnfl, mlib_u8, dmbsk)
        brfbk;
    dbsf MLIB_SHORT:
    dbsf MLIB_USHORT:
      EDGES(dibnnfl, mlib_s16, dmbsk)
        brfbk;
    dbsf MLIB_INT:
      EDGES(dibnnfl, mlib_s32, dmbsk)
        brfbk;
    dffbult:
      rfturn MLIB_FAILURE;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgfConvZfroEdgf(mlib_imbgf *dst,
                                   mlib_s32   dx_l,
                                   mlib_s32   dx_r,
                                   mlib_s32   dy_t,
                                   mlib_s32   dy_b,
                                   mlib_s32   dmbsk)
{
  mlib_d64 zfro[4] = { 0, 0, 0, 0 };
  mlib_typf typf = mlib_ImbgfGftTypf(dst);

  if (typf == MLIB_FLOAT || typf == MLIB_DOUBLE) {
    rfturn mlib_ImbgfConvClfbrEdgf_Fp(dst, dx_l, dx_r, dy_t, dy_b, zfro, dmbsk);
  }
  flsf {
    rfturn mlib_ImbgfConvClfbrEdgf(dst, dx_l, dx_r, dy_t, dy_b, (mlib_s32 *) zfro, dmbsk);
  }
}

/***************************************************************/
