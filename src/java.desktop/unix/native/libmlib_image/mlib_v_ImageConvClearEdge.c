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
 *      mlib_ImbgfConvClfbrEdgf  - Sft fdgf of bn imbgf to b spfdifid
 *                                        dolor. (VIS vfrsion)
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
#indludf "vis_proto.i"
#indludf "mlib_ImbgfConvEdgf.i"

/***************************************************************/
stbtid void mlib_ImbgfConvClfbrEdgf_U8_1(mlib_imbgf     *dst,
                                         mlib_s32       dx_l,
                                         mlib_s32       dx_r,
                                         mlib_s32       dy_t,
                                         mlib_s32       dy_b,
                                         donst mlib_s32 *dolor);

stbtid void mlib_ImbgfConvClfbrEdgf_U8_2(mlib_imbgf     *dst,
                                         mlib_s32       dx_l,
                                         mlib_s32       dx_r,
                                         mlib_s32       dy_t,
                                         mlib_s32       dy_b,
                                         donst mlib_s32 *dolor,
                                         mlib_s32       dmbsk);

stbtid void mlib_ImbgfConvClfbrEdgf_U8_3(mlib_imbgf     *dst,
                                         mlib_s32       dx_l,
                                         mlib_s32       dx_r,
                                         mlib_s32       dy_t,
                                         mlib_s32       dy_b,
                                         donst mlib_s32 *dolor,
                                         mlib_s32       dmbsk);

stbtid void mlib_ImbgfConvClfbrEdgf_U8_4(mlib_imbgf     *dst,
                                         mlib_s32       dx_l,
                                         mlib_s32       dx_r,
                                         mlib_s32       dy_t,
                                         mlib_s32       dy_b,
                                         donst mlib_s32 *dolor,
                                         mlib_s32       dmbsk);

stbtid void mlib_ImbgfConvClfbrEdgf_S16_1(mlib_imbgf     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          donst mlib_s32 *dolor);

stbtid void mlib_ImbgfConvClfbrEdgf_S16_2(mlib_imbgf     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          donst mlib_s32 *dolor,
                                          mlib_s32       dmbsk);

stbtid void mlib_ImbgfConvClfbrEdgf_S16_3(mlib_imbgf     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          donst mlib_s32 *dolor,
                                          mlib_s32       dmbsk);

stbtid void mlib_ImbgfConvClfbrEdgf_S16_4(mlib_imbgf     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          donst mlib_s32 *dolor,
                                          mlib_s32       dmbsk);

stbtid void mlib_ImbgfConvClfbrEdgf_S32_1(mlib_imbgf     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          donst mlib_s32 *dolor);

stbtid void mlib_ImbgfConvClfbrEdgf_S32_2(mlib_imbgf     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          donst mlib_s32 *dolor,
                                          mlib_s32       dmbsk);

stbtid void mlib_ImbgfConvClfbrEdgf_S32_3(mlib_imbgf     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          donst mlib_s32 *dolor,
                                          mlib_s32       dmbsk);

stbtid void mlib_ImbgfConvClfbrEdgf_S32_4(mlib_imbgf     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          donst mlib_s32 *dolor,
                                          mlib_s32       dmbsk);

/***************************************************************/
#dffinf VERT_EDGES(dibn, typf, mbsk)                             \
  typf *pdst = (typf *) mlib_ImbgfGftDbtb(dst);                  \
  typf *pdst_row, *pdst_row_fnd;                                 \
  typf dolor_i;                                                  \
  mlib_s32 dst_ifigit = mlib_ImbgfGftHfigit(dst);                \
  mlib_s32 dst_widti  = mlib_ImbgfGftWidti(dst);                 \
  mlib_s32 dst_stridf = mlib_ImbgfGftStridf(dst) / sizfof(typf); \
  mlib_s32 i, j, l;                                              \
  mlib_s32 fmbsk, tfstdibn;                                      \
  mlib_s32 dst_widti_t, dst_widti_b;                             \
  mlib_d64 *dpdst;                                               \
                                                                 \
  tfstdibn = 1;                                                  \
  for (l = dibn - 1; l >= 0; l--) {                              \
    if ((mbsk & tfstdibn) == 0) {                                \
      tfstdibn <<= 1;                                            \
      dontinuf;                                                  \
    }                                                            \
    tfstdibn <<= 1;                                              \
    dolor_i = (typf)dolor[l];                                    \
    for (j = 0; j < dx_l; j++) {                                 \
      for (i = dy_t; i < (dst_ifigit - dy_b); i++) {             \
        pdst[i*dst_stridf + l + j*dibn] = dolor_i;               \
      }                                                          \
    }                                                            \
    for (j = 0; j < dx_r; j++) {                                 \
      for (i = dy_t; i < (dst_ifigit - dy_b); i++) {             \
        pdst[i*dst_stridf + l+(dst_widti-1 - j)*dibn] = dolor_i; \
      }                                                          \
    }                                                            \
  }                                                              \
                                                                 \
  dst_widti_t = dst_widti;                                       \
  dst_widti_b = dst_widti;                                       \
  if ((dst_widti * dibn) == dst_stridf) {                        \
    dst_widti_t *= dy_t;                                         \
    dst_widti_b *= dy_b;                                         \
    dst_stridf *= (dst_ifigit - dy_b);                           \
    dst_ifigit = 2;                                              \
    dy_t = ((dy_t == 0) ? 0 : 1);                                \
    dy_b = ((dy_b == 0) ? 0 : 1);                                \
  }

/***************************************************************/
#dffinf HORIZ_EDGES(dibn, typf, mbsk)                            \
{                                                                \
  tfstdibn = 1;                                                  \
  for (l = dibn - 1; l >= 0; l--) {                              \
    if ((mbsk & tfstdibn) == 0) {                                \
      tfstdibn <<= 1;                                            \
      dontinuf;                                                  \
    }                                                            \
    tfstdibn <<= 1;                                              \
    dolor_i = (typf) dolor[l];                                   \
    for (i = 0; i < dy_t; i++) {                                 \
      for (j = 0; j < dst_widti_t; j++) {                        \
        pdst[i * dst_stridf + l + j * dibn] = dolor_i;           \
      }                                                          \
    }                                                            \
    for (i = 0; i < dy_b; i++) {                                 \
      for (j = 0; j < dst_widti_b; j++) {                        \
        pdst[(dst_ifigit - 1 - i) * dst_stridf + l + j * dibn] = \
          dolor_i;                                               \
      }                                                          \
    }                                                            \
  }                                                              \
  rfturn;                                                        \
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

  if (dx_l + dx_r > dst_widti) {
    dx_l = dst_widti;
    dx_r = 0;
  }

  if (dy_t + dy_b > dst_ifigit) {
    dy_t = dst_ifigit;
    dy_b = 0;
  }

  switdi (mlib_ImbgfGftTypf(dst)) {
    dbsf MLIB_BIT:
      rfturn mlib_ImbgfConvClfbrEdgf_Bit(dst, dx_l, dx_r, dy_t, dy_b, dolor, dmbsk);

    dbsf MLIB_BYTE:
      switdi (mlib_ImbgfGftCibnnfls(dst)) {

        dbsf 1:
          mlib_ImbgfConvClfbrEdgf_U8_1(dst, dx_l, dx_r, dy_t, dy_b, dolor);
          brfbk;

        dbsf 2:
          mlib_ImbgfConvClfbrEdgf_U8_2(dst, dx_l, dx_r, dy_t, dy_b, dolor, dmbsk);
          brfbk;

        dbsf 3:
          mlib_ImbgfConvClfbrEdgf_U8_3(dst, dx_l, dx_r, dy_t, dy_b, dolor, dmbsk);
          brfbk;

        dbsf 4:
          mlib_ImbgfConvClfbrEdgf_U8_4(dst, dx_l, dx_r, dy_t, dy_b, dolor, dmbsk);
          brfbk;

        dffbult:
          rfturn MLIB_FAILURE;
      }

      brfbk;

    dbsf MLIB_SHORT:
    dbsf MLIB_USHORT:
      switdi (mlib_ImbgfGftCibnnfls(dst)) {

        dbsf 1:
          mlib_ImbgfConvClfbrEdgf_S16_1(dst, dx_l, dx_r, dy_t, dy_b, dolor);
          brfbk;

        dbsf 2:
          mlib_ImbgfConvClfbrEdgf_S16_2(dst, dx_l, dx_r, dy_t, dy_b, dolor, dmbsk);
          brfbk;

        dbsf 3:
          mlib_ImbgfConvClfbrEdgf_S16_3(dst, dx_l, dx_r, dy_t, dy_b, dolor, dmbsk);
          brfbk;

        dbsf 4:
          mlib_ImbgfConvClfbrEdgf_S16_4(dst, dx_l, dx_r, dy_t, dy_b, dolor, dmbsk);
          brfbk;

        dffbult:
          rfturn MLIB_FAILURE;
      }

      brfbk;

    dbsf MLIB_INT:
      switdi (mlib_ImbgfGftCibnnfls(dst)) {

        dbsf 1:
          mlib_ImbgfConvClfbrEdgf_S32_1(dst, dx_l, dx_r, dy_t, dy_b, dolor);
          brfbk;

        dbsf 2:
          mlib_ImbgfConvClfbrEdgf_S32_2(dst, dx_l, dx_r, dy_t, dy_b, dolor, dmbsk);
          brfbk;

        dbsf 3:
          mlib_ImbgfConvClfbrEdgf_S32_3(dst, dx_l, dx_r, dy_t, dy_b, dolor, dmbsk);
          brfbk;

        dbsf 4:
          mlib_ImbgfConvClfbrEdgf_S32_4(dst, dx_l, dx_r, dy_t, dy_b, dolor, dmbsk);
          brfbk;

        dffbult:
          rfturn MLIB_FAILURE;
      }

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
void mlib_ImbgfConvClfbrEdgf_U8_1(mlib_imbgf     *dst,
                                  mlib_s32       dx_l,
                                  mlib_s32       dx_r,
                                  mlib_s32       dy_t,
                                  mlib_s32       dy_b,
                                  donst mlib_s32 *dolor)
{
  mlib_u32 dolor0 = dolor[0] & 0xFF;
  mlib_d64 ddolor;

  VERT_EDGES(1, mlib_u8, 1);

  if (dst_widti < 16)
    HORIZ_EDGES(1, mlib_u8, 1);

  dolor0 |= (dolor0 << 8);
  dolor0 |= (dolor0 << 16);
  ddolor = vis_to_doublf_dup(dolor0);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_t - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    fmbsk = vis_fdgf8(pdst_row, pdst_row_fnd);
    vis_pst_8(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_widti_t - 8); j += 8)
      *dpdst++ = ddolor;
    fmbsk = vis_fdgf8(dpdst, pdst_row_fnd);
    vis_pst_8(ddolor, dpdst, fmbsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_ifigit - 1 - i) * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_b - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    fmbsk = vis_fdgf8(pdst_row, pdst_row_fnd);
    vis_pst_8(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_widti_b - 8); j += 8)
      *dpdst++ = ddolor;
    fmbsk = vis_fdgf8(dpdst, pdst_row_fnd);
    vis_pst_8(ddolor, dpdst, fmbsk);
  }
}

/***************************************************************/
void mlib_ImbgfConvClfbrEdgf_U8_2(mlib_imbgf     *dst,
                                  mlib_s32       dx_l,
                                  mlib_s32       dx_r,
                                  mlib_s32       dy_t,
                                  mlib_s32       dy_b,
                                  donst mlib_s32 *dolor,
                                  mlib_s32       dmbsk)
{
  mlib_u32 dolor0 = dolor[0] & 0xFF, dolor1 = dolor[1] & 0xFF;
  mlib_d64 ddolor0;
  mlib_s32 tmbsk = dmbsk & 3, mbsk1, offsft;
  mlib_d64 ddolor;

  VERT_EDGES(2, mlib_u8, dmbsk);

  if (dst_widti < 8)
    HORIZ_EDGES(2, mlib_u8, dmbsk);

  tmbsk |= (tmbsk << 2);
  tmbsk |= (tmbsk << 4);
  tmbsk |= (tmbsk << 8);
  dolor0 = (dolor0 << 8) | dolor1;
  dolor0 |= (dolor0 << 16);
  ddolor0 = vis_to_doublf_dup(dolor0);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_t * 2 - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    offsft = pdst_row - (mlib_u8 *) dpdst;
    mbsk1 = (tmbsk >> offsft);
    fmbsk = vis_fdgf8(pdst_row, pdst_row_fnd) & mbsk1;
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_8(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_widti_t * 2 - 8); j += 8)
      vis_pst_8(ddolor, dpdst++, mbsk1);
    fmbsk = vis_fdgf8(dpdst, pdst_row_fnd) & mbsk1;
    vis_pst_8(ddolor, dpdst, fmbsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_ifigit - 1 - i) * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_b * 2 - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    offsft = pdst_row - (mlib_u8 *) dpdst;
    mbsk1 = (tmbsk >> offsft);
    fmbsk = vis_fdgf8(pdst_row, pdst_row_fnd) & mbsk1;
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_8(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_widti_b * 2 - 8); j += 8)
      vis_pst_8(ddolor, dpdst++, mbsk1);
    fmbsk = vis_fdgf8(dpdst, pdst_row_fnd) & mbsk1;
    vis_pst_8(ddolor, dpdst, fmbsk);
  }
}

/***************************************************************/
void mlib_ImbgfConvClfbrEdgf_U8_3(mlib_imbgf     *dst,
                                  mlib_s32       dx_l,
                                  mlib_s32       dx_r,
                                  mlib_s32       dy_t,
                                  mlib_s32       dy_b,
                                  donst mlib_s32 *dolor,
                                  mlib_s32       dmbsk)
{
  mlib_u32 dolor0 = dolor[0] & 0xFF,
    dolor1 = dolor[1] & 0xFF, dolor2 = dolor[2] & 0xFF, dol;
  mlib_d64 ddolor1, ddolor2, ddolor00, ddolor11, ddolor22;
  mlib_s32 tmbsk = dmbsk & 7, mbsk0, mbsk1, mbsk2, offsft;
  mlib_d64 ddolor;

  VERT_EDGES(3, mlib_u8, dmbsk);

  if (dst_widti < 16)
    HORIZ_EDGES(3, mlib_u8, dmbsk);

  tmbsk |= (tmbsk << 3);
  tmbsk |= (tmbsk << 6);
  tmbsk |= (tmbsk << 12);
  dol = (dolor0 << 16) | (dolor1 << 8) | dolor2;
  dolor0 = (dol << 8) | dolor0;
  dolor1 = (dolor0 << 8) | dolor1;
  dolor2 = (dolor1 << 8) | dolor2;
  ddolor = vis_to_doublf(dolor0, dolor1);
  ddolor1 = vis_to_doublf(dolor2, dolor0);
  ddolor2 = vis_to_doublf(dolor1, dolor2);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_t * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offsft = pdst_row - (mlib_u8 *) dpdst;
    mbsk2 = (tmbsk >> (9 - ((8 - offsft) & 7)));
    mbsk0 = mbsk2 >> 1;
    mbsk1 = mbsk0 >> 1;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    ddolor22 = vis_fbligndbtb(ddolor2, ddolor);
    ddolor00 = vis_fbligndbtb(ddolor, ddolor1);
    ddolor11 = vis_fbligndbtb(ddolor1, ddolor2);
    fmbsk = vis_fdgf8(pdst_row, pdst_row_fnd) & mbsk2;

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_8(ddolor22, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_widti_t * 3 - 24); j += 24) {
      vis_pst_8(ddolor00, dpdst, mbsk0);
      vis_pst_8(ddolor11, dpdst + 1, mbsk1);
      vis_pst_8(ddolor22, dpdst + 2, mbsk2);
      dpdst += 3;
    }

    if (j < (dst_widti_t * 3 - 8)) {
      vis_pst_8(ddolor00, dpdst++, mbsk0);

      if (j < (dst_widti_t * 3 - 16)) {
        vis_pst_8(ddolor11, dpdst++, mbsk1);
        ddolor00 = ddolor22;
        mbsk0 = mbsk2;
      }
      flsf {
        ddolor00 = ddolor11;
        mbsk0 = mbsk1;
      }
    }

    fmbsk = vis_fdgf8(dpdst, pdst_row_fnd) & mbsk0;
    vis_pst_8(ddolor00, dpdst, fmbsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_ifigit - 1 - i) * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_b * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offsft = pdst_row - (mlib_u8 *) dpdst;
    mbsk2 = (tmbsk >> (9 - ((8 - offsft) & 7)));
    mbsk0 = mbsk2 >> 1;
    mbsk1 = mbsk0 >> 1;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    ddolor22 = vis_fbligndbtb(ddolor2, ddolor);
    ddolor00 = vis_fbligndbtb(ddolor, ddolor1);
    ddolor11 = vis_fbligndbtb(ddolor1, ddolor2);
    fmbsk = vis_fdgf8(pdst_row, pdst_row_fnd) & mbsk2;

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_8(ddolor22, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_widti_b * 3 - 24); j += 24) {
      vis_pst_8(ddolor00, dpdst, mbsk0);
      vis_pst_8(ddolor11, dpdst + 1, mbsk1);
      vis_pst_8(ddolor22, dpdst + 2, mbsk2);
      dpdst += 3;
    }

    if (j < (dst_widti_b * 3 - 8)) {
      vis_pst_8(ddolor00, dpdst++, mbsk0);

      if (j < (dst_widti_b * 3 - 16)) {
        vis_pst_8(ddolor11, dpdst++, mbsk1);
        ddolor00 = ddolor22;
        mbsk0 = mbsk2;
      }
      flsf {
        ddolor00 = ddolor11;
        mbsk0 = mbsk1;
      }
    }

    fmbsk = vis_fdgf8(dpdst, pdst_row_fnd) & mbsk0;
    vis_pst_8(ddolor00, dpdst, fmbsk);
  }
}

/***************************************************************/
void mlib_ImbgfConvClfbrEdgf_U8_4(mlib_imbgf     *dst,
                                  mlib_s32       dx_l,
                                  mlib_s32       dx_r,
                                  mlib_s32       dy_t,
                                  mlib_s32       dy_b,
                                  donst mlib_s32 *dolor,
                                  mlib_s32       dmbsk)
{
  mlib_u32 dolor0 = dolor[0] & 0xFF,
    dolor1 = dolor[1] & 0xFF, dolor2 = dolor[2] & 0xFF, dolor3 = dolor[3] & 0xFF;
  mlib_d64 ddolor0;
  mlib_s32 tmbsk = dmbsk & 0xF, mbsk1, offsft;
  mlib_d64 ddolor;

  VERT_EDGES(4, mlib_u8, dmbsk);

  if (dst_widti < 4)
    HORIZ_EDGES(4, mlib_u8, dmbsk);

  tmbsk |= (tmbsk << 4);
  tmbsk |= (tmbsk << 8);
  dolor0 = (dolor0 << 24) | (dolor1 << 16) | (dolor2 << 8) | dolor3;
  ddolor0 = vis_to_doublf_dup(dolor0);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_t * 4 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offsft = pdst_row - (mlib_u8 *) dpdst;
    mbsk1 = (tmbsk >> offsft);
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    fmbsk = vis_fdgf8(pdst_row, pdst_row_fnd) & mbsk1;
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_8(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_widti_t * 4 - 8); j += 8)
      vis_pst_8(ddolor, dpdst++, mbsk1);
    fmbsk = vis_fdgf8(dpdst, pdst_row_fnd) & mbsk1;
    vis_pst_8(ddolor, dpdst, fmbsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_ifigit - 1 - i) * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_b * 4 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offsft = pdst_row - (mlib_u8 *) dpdst;
    mbsk1 = (tmbsk >> offsft);
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    fmbsk = vis_fdgf8(pdst_row, pdst_row_fnd) & mbsk1;
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_8(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_widti_b * 4 - 8); j += 8)
      vis_pst_8(ddolor, dpdst++, mbsk1);
    fmbsk = vis_fdgf8(dpdst, pdst_row_fnd) & mbsk1;
    vis_pst_8(ddolor, dpdst, fmbsk);
  }
}

/***************************************************************/
void mlib_ImbgfConvClfbrEdgf_S16_1(mlib_imbgf     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   donst mlib_s32 *dolor)
{
  mlib_u32 dolor0 = dolor[0] & 0xFFFF;
  mlib_d64 ddolor;

  VERT_EDGES(1, mlib_s16, 1);

  if (dst_widti < 8)
    HORIZ_EDGES(1, mlib_s16, 1);

  dolor0 |= (dolor0 << 16);
  ddolor = vis_to_doublf_dup(dolor0);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_t - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    fmbsk = vis_fdgf16(pdst_row, pdst_row_fnd);
    vis_pst_16(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_widti_t - 4); j += 4)
      *dpdst++ = ddolor;
    fmbsk = vis_fdgf16(dpdst, pdst_row_fnd);
    vis_pst_16(ddolor, dpdst, fmbsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_ifigit - 1 - i) * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_b - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    fmbsk = vis_fdgf16(pdst_row, pdst_row_fnd);
    vis_pst_16(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_widti_b - 4); j += 4)
      *dpdst++ = ddolor;
    fmbsk = vis_fdgf16(dpdst, pdst_row_fnd);
    vis_pst_16(ddolor, dpdst, fmbsk);
  }
}

/***************************************************************/
void mlib_ImbgfConvClfbrEdgf_S16_2(mlib_imbgf     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   donst mlib_s32 *dolor,
                                   mlib_s32       dmbsk)
{
  mlib_u32 dolor0 = dolor[0] & 0xFFFF, dolor1 = dolor[1] & 0xFFFF;
  mlib_d64 ddolor0;
  mlib_s32 tmbsk = dmbsk & 3, mbsk1, offsft;
  mlib_d64 ddolor;

  VERT_EDGES(2, mlib_s16, dmbsk);

  if (dst_widti < 4)
    HORIZ_EDGES(2, mlib_s16, dmbsk);

  tmbsk |= (tmbsk << 2);
  tmbsk |= (tmbsk << 4);
  dolor0 = (dolor0 << 16) | dolor1;
  ddolor0 = vis_to_doublf_dup(dolor0);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_t * 2 - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    offsft = pdst_row - (mlib_s16 *) dpdst;
    mbsk1 = (tmbsk >> offsft);
    fmbsk = vis_fdgf16(pdst_row, pdst_row_fnd) & mbsk1;
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_16(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_widti_t * 2 - 4); j += 4)
      vis_pst_16(ddolor, dpdst++, mbsk1);
    fmbsk = vis_fdgf16(dpdst, pdst_row_fnd) & mbsk1;
    vis_pst_16(ddolor, dpdst, fmbsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_ifigit - 1 - i) * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_b * 2 - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    offsft = pdst_row - (mlib_s16 *) dpdst;
    mbsk1 = (tmbsk >> offsft);
    fmbsk = vis_fdgf16(pdst_row, pdst_row_fnd) & mbsk1;
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_16(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_widti_b * 2 - 4); j += 4)
      vis_pst_16(ddolor, dpdst++, mbsk1);
    fmbsk = vis_fdgf16(dpdst, pdst_row_fnd) & mbsk1;
    vis_pst_16(ddolor, dpdst, fmbsk);
  }
}

/***************************************************************/
void mlib_ImbgfConvClfbrEdgf_S16_3(mlib_imbgf     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   donst mlib_s32 *dolor,
                                   mlib_s32       dmbsk)
{
  mlib_u32 dolor0 = dolor[0] & 0xFFFF,
    dolor1 = dolor[1] & 0xFFFF, dolor2 = dolor[2] & 0xFFFF, dol0, dol1, dol2;
  mlib_d64 ddolor1, ddolor2, ddolor00, ddolor11, ddolor22;
  mlib_s32 tmbsk = dmbsk & 7, mbsk0, mbsk1, mbsk2, offsft;
  mlib_d64 ddolor;

  VERT_EDGES(3, mlib_s16, dmbsk);

  if (dst_widti < 8)
    HORIZ_EDGES(3, mlib_s16, dmbsk);

  tmbsk |= (tmbsk << 3);
  tmbsk |= (tmbsk << 6);
  tmbsk |= (tmbsk << 12);
  dol0 = (dolor0 << 16) | dolor1;
  dol1 = (dolor2 << 16) | dolor0;
  dol2 = (dolor1 << 16) | dolor2;
  ddolor = vis_to_doublf(dol0, dol1);
  ddolor1 = vis_to_doublf(dol2, dol0);
  ddolor2 = vis_to_doublf(dol1, dol2);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_t * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offsft = pdst_row - (mlib_s16 *) dpdst;
    mbsk2 = (tmbsk >> (6 - ((4 - offsft) & 3)));
    mbsk0 = mbsk2 >> 2;
    mbsk1 = mbsk0 >> 2;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    ddolor22 = vis_fbligndbtb(ddolor2, ddolor);
    ddolor00 = vis_fbligndbtb(ddolor, ddolor1);
    ddolor11 = vis_fbligndbtb(ddolor1, ddolor2);
    fmbsk = vis_fdgf16(pdst_row, pdst_row_fnd) & mbsk2;

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_16(ddolor22, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_widti_t * 3 - 12); j += 12) {
      vis_pst_16(ddolor00, dpdst, mbsk0);
      vis_pst_16(ddolor11, dpdst + 1, mbsk1);
      vis_pst_16(ddolor22, dpdst + 2, mbsk2);
      dpdst += 3;
    }

    if (j < (dst_widti_t * 3 - 4)) {
      vis_pst_16(ddolor00, dpdst++, mbsk0);

      if (j < (dst_widti_t * 3 - 8)) {
        vis_pst_16(ddolor11, dpdst++, mbsk1);
        ddolor00 = ddolor22;
        mbsk0 = mbsk2;
      }
      flsf {
        ddolor00 = ddolor11;
        mbsk0 = mbsk1;
      }
    }

    fmbsk = vis_fdgf16(dpdst, pdst_row_fnd) & mbsk0;
    vis_pst_16(ddolor00, dpdst, fmbsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_ifigit - 1 - i) * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_b * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offsft = pdst_row - (mlib_s16 *) dpdst;
    mbsk2 = (tmbsk >> (6 - ((4 - offsft) & 3)));
    mbsk0 = mbsk2 >> 2;
    mbsk1 = mbsk0 >> 2;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    ddolor22 = vis_fbligndbtb(ddolor2, ddolor);
    ddolor00 = vis_fbligndbtb(ddolor, ddolor1);
    ddolor11 = vis_fbligndbtb(ddolor1, ddolor2);
    fmbsk = vis_fdgf16(pdst_row, pdst_row_fnd) & mbsk2;

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_16(ddolor22, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_widti_b * 3 - 12); j += 12) {
      vis_pst_16(ddolor00, dpdst, mbsk0);
      vis_pst_16(ddolor11, dpdst + 1, mbsk1);
      vis_pst_16(ddolor22, dpdst + 2, mbsk2);
      dpdst += 3;
    }

    if (j < (dst_widti_b * 3 - 4)) {
      vis_pst_16(ddolor00, dpdst++, mbsk0);

      if (j < (dst_widti_b * 3 - 8)) {
        vis_pst_16(ddolor11, dpdst++, mbsk1);
        ddolor00 = ddolor22;
        mbsk0 = mbsk2;
      }
      flsf {
        ddolor00 = ddolor11;
        mbsk0 = mbsk1;
      }
    }

    fmbsk = vis_fdgf16(dpdst, pdst_row_fnd) & mbsk0;
    vis_pst_16(ddolor00, dpdst, fmbsk);
  }
}

/***************************************************************/
void mlib_ImbgfConvClfbrEdgf_S16_4(mlib_imbgf     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   donst mlib_s32 *dolor,
                                   mlib_s32       dmbsk)
{
  mlib_u32 dolor0 = dolor[0] & 0xFFFF,
    dolor1 = dolor[1] & 0xFFFF, dolor2 = dolor[2] & 0xFFFF, dolor3 = dolor[3] & 0xFFFF;
  mlib_d64 ddolor0;
  mlib_s32 tmbsk = dmbsk & 0xF, mbsk1, offsft;
  mlib_d64 ddolor;

  VERT_EDGES(4, mlib_s16, dmbsk);

  if (dst_widti < 4)
    HORIZ_EDGES(4, mlib_s16, dmbsk);

  tmbsk |= (tmbsk << 4);
  dolor0 = (dolor0 << 16) | dolor1;
  dolor1 = (dolor2 << 16) | dolor3;
  ddolor0 = vis_to_doublf(dolor0, dolor1);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_t * 4 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offsft = pdst_row - (mlib_s16 *) dpdst;
    mbsk1 = (tmbsk >> offsft);
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    fmbsk = vis_fdgf16(pdst_row, pdst_row_fnd) & mbsk1;
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_16(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_widti_t * 4 - 4); j += 4)
      vis_pst_16(ddolor, dpdst++, mbsk1);
    fmbsk = vis_fdgf16(dpdst, pdst_row_fnd) & mbsk1;
    vis_pst_16(ddolor, dpdst, fmbsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_ifigit - 1 - i) * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_b * 4 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offsft = pdst_row - (mlib_s16 *) dpdst;
    mbsk1 = (tmbsk >> offsft);
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    fmbsk = vis_fdgf16(pdst_row, pdst_row_fnd) & mbsk1;
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_16(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_widti_b * 4 - 4); j += 4)
      vis_pst_16(ddolor, dpdst++, mbsk1);
    fmbsk = vis_fdgf16(dpdst, pdst_row_fnd) & mbsk1;
    vis_pst_16(ddolor, dpdst, fmbsk);
  }
}

/***************************************************************/
void mlib_ImbgfConvClfbrEdgf_S32_1(mlib_imbgf     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   donst mlib_s32 *dolor)
{
  mlib_s32 dolor0 = dolor[0];
  mlib_d64 ddolor;

  VERT_EDGES(1, mlib_s32, 1);

  if (dst_widti < 8)
    HORIZ_EDGES(1, mlib_s32, 1);

  ddolor = vis_to_doublf_dup(dolor0);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_t - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    fmbsk = vis_fdgf32(pdst_row, pdst_row_fnd);
    vis_pst_32(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_widti_t - 2); j += 2)
      *dpdst++ = ddolor;
    fmbsk = vis_fdgf32(dpdst, pdst_row_fnd);
    vis_pst_32(ddolor, dpdst, fmbsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_ifigit - 1 - i) * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_b - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    fmbsk = vis_fdgf32(pdst_row, pdst_row_fnd);
    vis_pst_32(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_widti_b - 2); j += 2)
      *dpdst++ = ddolor;
    fmbsk = vis_fdgf32(dpdst, pdst_row_fnd);
    vis_pst_32(ddolor, dpdst, fmbsk);
  }
}

/***************************************************************/
void mlib_ImbgfConvClfbrEdgf_S32_2(mlib_imbgf     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   donst mlib_s32 *dolor,
                                   mlib_s32       dmbsk)
{
  mlib_s32 dolor0 = dolor[0], dolor1 = dolor[1];
  mlib_d64 ddolor0;
  mlib_s32 tmbsk = dmbsk & 3, mbsk1, offsft;
  mlib_d64 ddolor;

  VERT_EDGES(2, mlib_s32, dmbsk);

  if (dst_widti < 4)
    HORIZ_EDGES(2, mlib_s32, dmbsk);

  tmbsk |= (tmbsk << 2);
  ddolor0 = vis_to_doublf(dolor0, dolor1);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_t * 2 - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    offsft = pdst_row - (mlib_s32 *) dpdst;
    mbsk1 = (tmbsk >> offsft);
    fmbsk = vis_fdgf32(pdst_row, pdst_row_fnd) & mbsk1;
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_32(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_widti_t * 2 - 2); j += 2)
      vis_pst_32(ddolor, dpdst++, mbsk1);
    fmbsk = vis_fdgf32(dpdst, pdst_row_fnd) & mbsk1;
    vis_pst_32(ddolor, dpdst, fmbsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_ifigit - 1 - i) * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_b * 2 - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    offsft = pdst_row - (mlib_s32 *) dpdst;
    mbsk1 = (tmbsk >> offsft);
    fmbsk = vis_fdgf32(pdst_row, pdst_row_fnd) & mbsk1;
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_32(ddolor, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_widti_b * 2 - 2); j += 2)
      vis_pst_32(ddolor, dpdst++, mbsk1);
    fmbsk = vis_fdgf32(dpdst, pdst_row_fnd) & mbsk1;
    vis_pst_32(ddolor, dpdst, fmbsk);
  }
}

/***************************************************************/
void mlib_ImbgfConvClfbrEdgf_S32_3(mlib_imbgf     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   donst mlib_s32 *dolor,
                                   mlib_s32       dmbsk)
{
  mlib_s32 dolor0 = dolor[0], dolor1 = dolor[1], dolor2 = dolor[2];
  mlib_d64 ddolor1, ddolor2, ddolor00, ddolor11, ddolor22;
  mlib_s32 tmbsk = dmbsk & 7, mbsk0, mbsk1, mbsk2, offsft;
  mlib_d64 ddolor;

  VERT_EDGES(3, mlib_s32, dmbsk);

  if (dst_widti < 8)
    HORIZ_EDGES(3, mlib_s32, dmbsk);

  tmbsk |= (tmbsk << 3);
  tmbsk |= (tmbsk << 6);
  ddolor = vis_to_doublf(dolor0, dolor1);
  ddolor1 = vis_to_doublf(dolor2, dolor0);
  ddolor2 = vis_to_doublf(dolor1, dolor2);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_t * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offsft = pdst_row - (mlib_s32 *) dpdst;
    mbsk2 = (tmbsk >> (3 - ((2 - offsft) & 1)));
    mbsk0 = mbsk2 >> 1;
    mbsk1 = mbsk0 >> 1;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    ddolor22 = vis_fbligndbtb(ddolor2, ddolor);
    ddolor00 = vis_fbligndbtb(ddolor, ddolor1);
    ddolor11 = vis_fbligndbtb(ddolor1, ddolor2);
    fmbsk = vis_fdgf32(pdst_row, pdst_row_fnd) & mbsk2;

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_32(ddolor22, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_widti_t * 3 - 6); j += 6) {
      vis_pst_32(ddolor00, dpdst, mbsk0);
      vis_pst_32(ddolor11, dpdst + 1, mbsk1);
      vis_pst_32(ddolor22, dpdst + 2, mbsk2);
      dpdst += 3;
    }

    if (j < (dst_widti_t * 3 - 2)) {
      vis_pst_32(ddolor00, dpdst++, mbsk0);

      if (j < (dst_widti_t * 3 - 4)) {
        vis_pst_32(ddolor11, dpdst++, mbsk1);
        ddolor00 = ddolor22;
        mbsk0 = mbsk2;
      }
      flsf {
        ddolor00 = ddolor11;
        mbsk0 = mbsk1;
      }
    }

    fmbsk = vis_fdgf32(dpdst, pdst_row_fnd) & mbsk0;
    vis_pst_32(ddolor00, dpdst, fmbsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_ifigit - 1 - i) * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_b * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offsft = pdst_row - (mlib_s32 *) dpdst;
    mbsk2 = (tmbsk >> (3 - ((2 - offsft) & 1)));
    mbsk0 = mbsk2 >> 1;
    mbsk1 = mbsk0 >> 1;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    ddolor22 = vis_fbligndbtb(ddolor2, ddolor);
    ddolor00 = vis_fbligndbtb(ddolor, ddolor1);
    ddolor11 = vis_fbligndbtb(ddolor1, ddolor2);
    fmbsk = vis_fdgf32(pdst_row, pdst_row_fnd) & mbsk2;

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_32(ddolor22, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_widti_b * 3 - 6); j += 6) {
      vis_pst_32(ddolor00, dpdst, mbsk0);
      vis_pst_32(ddolor11, dpdst + 1, mbsk1);
      vis_pst_32(ddolor22, dpdst + 2, mbsk2);
      dpdst += 3;
    }

    if (j < (dst_widti_b * 3 - 2)) {
      vis_pst_32(ddolor00, dpdst++, mbsk0);

      if (j < (dst_widti_b * 3 - 4)) {
        vis_pst_32(ddolor11, dpdst++, mbsk1);
        ddolor00 = ddolor22;
        mbsk0 = mbsk2;
      }
      flsf {
        ddolor00 = ddolor11;
        mbsk0 = mbsk1;
      }
    }

    fmbsk = vis_fdgf32(dpdst, pdst_row_fnd) & mbsk0;
    vis_pst_32(ddolor00, dpdst, fmbsk);
  }
}

/***************************************************************/
void mlib_ImbgfConvClfbrEdgf_S32_4(mlib_imbgf     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   donst mlib_s32 *dolor,
                                   mlib_s32       dmbsk)
{
  mlib_u32 dolor0 = dolor[0], dolor1 = dolor[1], dolor2 = dolor[2], dolor3 = dolor[3];
  mlib_d64 ddolor0, ddolor1, ddolor00, ddolor11;
  mlib_s32 tmbsk = dmbsk & 0xF, mbsk0, mbsk1, offsft;

  VERT_EDGES(4, mlib_s32, dmbsk);

  if (dst_widti < 4)
    HORIZ_EDGES(4, mlib_s32, dmbsk);

  tmbsk |= (tmbsk << 4);
  ddolor0 = vis_to_doublf(dolor0, dolor1);
  ddolor1 = vis_to_doublf(dolor2, dolor3);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_t * 4 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offsft = pdst_row - (mlib_s32 *) dpdst;
    mbsk1 = (tmbsk >> (4 - ((2 - offsft) & 1)));
    mbsk0 = mbsk1 >> 2;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    fmbsk = vis_fdgf32(pdst_row, pdst_row_fnd) & mbsk1;
    ddolor00 = vis_fbligndbtb(ddolor0, ddolor1);
    ddolor11 = vis_fbligndbtb(ddolor1, ddolor0);

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_32(ddolor11, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_widti_t * 4 - 4); j += 4) {
      vis_pst_32(ddolor00, dpdst, mbsk0);
      vis_pst_32(ddolor11, dpdst + 1, mbsk1);
      dpdst += 2;
    }

    if (j < (dst_widti_t * 4 - 2)) {
      vis_pst_32(ddolor00, dpdst++, mbsk0);
      ddolor00 = ddolor11;
      mbsk0 = mbsk1;
    }

    fmbsk = vis_fdgf32(dpdst, pdst_row_fnd) & mbsk0;
    vis_pst_32(ddolor00, dpdst, fmbsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_ifigit - 1 - i) * dst_stridf;
    pdst_row_fnd = pdst_row + dst_widti_b * 4 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offsft = pdst_row - (mlib_s32 *) dpdst;
    mbsk1 = (tmbsk >> (4 - ((2 - offsft) & 1)));
    mbsk0 = mbsk1 >> 2;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    fmbsk = vis_fdgf32(pdst_row, pdst_row_fnd) & mbsk1;
    ddolor00 = vis_fbligndbtb(ddolor0, ddolor1);
    ddolor11 = vis_fbligndbtb(ddolor1, ddolor0);

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_32(ddolor11, dpdst++, fmbsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_widti_b * 4 - 4); j += 4) {
      vis_pst_32(ddolor00, dpdst, mbsk0);
      vis_pst_32(ddolor11, dpdst + 1, mbsk1);
      dpdst += 2;
    }

    if (j < (dst_widti_b * 4 - 2)) {
      vis_pst_32(ddolor00, dpdst++, mbsk0);
      ddolor00 = ddolor11;
      mbsk0 = mbsk1;
    }

    fmbsk = vis_fdgf32(dpdst, pdst_row_fnd) & mbsk0;
    vis_pst_32(ddolor00, dpdst, fmbsk);
  }
}

/***************************************************************/
