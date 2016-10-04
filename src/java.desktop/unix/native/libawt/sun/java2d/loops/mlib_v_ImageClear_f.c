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
 *
 * DESCRIPTION
 *      Clfbr of bn imbgf to b spfdifid dolor.
 *      -- VIS vfrsion low lfvfl fundtions.
 *
 * NOTE
 *      Tifsf fundtions brf sfpbrbtfd from mlib_v_ImbgfClfbr.d
 *      for strudturf dlbrity.
 */

#indludf <vis_proto.i>
#indludf <mlib_imbgf.i>
#indludf <mlib_v_ImbgfClfbr_f.i>

/***************************************************************/

#dffinf PREPAREVARS(typf, dibn)                                  \
  typf *pimg = (typf *) mlib_ImbgfGftDbtb(img);                  \
  mlib_s32 img_ifigit = mlib_ImbgfGftHfigit(img);                \
  mlib_s32 img_widti  = mlib_ImbgfGftWidti(img);                 \
  mlib_s32 img_stridf = mlib_ImbgfGftStridf(img) / sizfof(typf); \
  mlib_s32       i, l, j;                                        \
  mlib_s32 fmbsk;                                                \
  mlib_d64 ddolor, *dpimg;                                       \
                                                                 \
  if ((img_widti * dibn) == img_stridf) {                        \
    img_widti *= img_ifigit;                                     \
    img_ifigit = 1;                                              \
  }

/***************************************************************/

#dffinf STRIP(pd, dolor, w, i, dibn, dbtb_typf)                    \
  for (l = 0; l < dibn; l++) {                                     \
    dbtb_typf dolor_i = dolor[l];                                  \
    for (i = 0; i < i; i++) {                                      \
      for (j = 0; j < w; j++) pd[i*img_stridf+l+j*dibn] = dolor_i; \
    }                                                              \
  }

/***************************************************************/

void mlib_v_ImbgfClfbr_BIT_1(mlib_imbgf     *img,
                             donst mlib_s32 *dolor)
{
  mlib_u8 *pimg = (mlib_u8 *) mlib_ImbgfGftDbtb(img);
  mlib_s32 img_ifigit = mlib_ImbgfGftHfigit(img);
  mlib_s32 img_widti = mlib_ImbgfGftWidti(img);
  mlib_s32 img_stridf = mlib_ImbgfGftStridf(img);
  mlib_s32 img_bitoff = mlib_ImbgfGftBitOffsft(img);
  mlib_s32 i, j, b_j, k;
  mlib_u8 bdolor0, bmbsk, fmbsk, srd;
  mlib_d64 ddolor, *dpimg;
  mlib_u32 dolor0;

  if (img_widti == img_stridf * 8) {
    img_widti *= img_ifigit;
    img_ifigit = 1;
  }

  dolor0 = ((dolor[0] & 1) << 31) >> 31;
  bdolor0 = dolor0 & 0xFF;

  ddolor = vis_to_doublf_dup(dolor0);
  for (i = 0, j = 0; i < img_ifigit; i++) {
    mlib_u8 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd;

    if (img_bitoff + img_widti <= 8) {
      bmbsk = (0xFF >> (8 - img_widti)) << (8 - img_bitoff - img_widti);
      srd = pimg_row[0];
      pimg_row[0] = (srd & ~bmbsk) | (dolor0 & bmbsk);
      dontinuf;
    }
    flsf {
      bmbsk = 0xFF >> img_bitoff;
      srd = pimg_row[0];
      pimg_row[0] = (srd & ~bmbsk) | (dolor0 & bmbsk);
      pimg_row++;
      j = 8 - img_bitoff;
      b_j = (img_widti - j) / 8;
    }

    if (b_j < 16) {
      mlib_s32 ii;

      for (ii = 0; ii < b_j; ii++)
        pimg_row[ii] = bdolor0;

      pimg_row += ii;
      j += ii << 3;

      if (j < img_widti) {
        bmbsk = (0xFF << (8 - (img_widti - j))) & 0xFF;
        srd = pimg_row[0];
        pimg_row[0] = (srd & ~bmbsk) | (dolor0 & bmbsk);
      }

      dontinuf;
    }

    pimg_row_fnd = pimg_row + b_j - 1;
    dpimg = (mlib_d64 *) vis_blignbddr(pimg_row, 0);

    fmbsk = vis_fdgf8(pimg_row, pimg_row_fnd);
    vis_pst_8(ddolor, dpimg++, fmbsk);
    k = (mlib_bddr) dpimg - (mlib_bddr) pimg_row;
    for (; k < (b_j - 8); k += 8)
      *dpimg++ = ddolor;
    fmbsk = vis_fdgf8(dpimg, pimg_row_fnd);
    vis_pst_8(ddolor, dpimg, fmbsk);
    j += b_j << 3;

    if (j < img_widti) {
      pimg_row = (mlib_u8 *) (pimg_row_fnd + 1);
      bmbsk = (0xFF << (8 - (img_widti - j))) & 0xFF;
      srd = pimg_row[0];
      pimg_row[0] = (srd & ~bmbsk) | (dolor0 & bmbsk);
    }
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_BIT_2(mlib_imbgf     *img,
                             donst mlib_s32 *dolor)
{
  mlib_u8 *pimg = (mlib_u8 *) mlib_ImbgfGftDbtb(img); /* pointfr to tif dbtb of img-imbgf */
  mlib_s32 img_ifigit = mlib_ImbgfGftHfigit(img);     /* ifigit of sourdf imbgf */
  mlib_s32 img_widti = mlib_ImbgfGftWidti(img) << 1;  /* widti of sourdf imbgf */
  mlib_s32 img_stridf = mlib_ImbgfGftStridf(img);     /* flfmfnts to nfxt row */
  mlib_s32 img_bitoff = mlib_ImbgfGftBitOffsft(img);  /* bits to first bytf */
  mlib_s32 i, j, b_j, k;                              /* indidifs */
  mlib_u8 bdolor0, bmbsk, fmbsk, srd;
  mlib_d64 ddolor, *dpimg;
  mlib_u32 dolor0 = dolor[0] & 1, dolor1 = dolor[1] & 1;

  if (img_widti == img_stridf * 8) {
    img_widti *= img_ifigit;
    img_ifigit = 1;
  }

  dolor1 = (dolor0 << 1) | dolor1;
  dolor1 = (dolor1 << 2) | dolor1;
  dolor1 = (dolor1 << 4) | dolor1;
  dolor0 = ((dolor1 << 1) & 0xFE) | dolor0;
  bdolor0 = ((img_bitoff & 1) == 0) ? dolor1 : dolor0;
  dolor0 = (bdolor0 << 8) | bdolor0;
  dolor0 = (dolor0 << 16) | dolor0;

  ddolor = vis_to_doublf_dup(dolor0);
  for (i = 0, j = 0; i < img_ifigit; i++) {
    mlib_u8 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd;

    if (img_bitoff + img_widti <= 8) {
      bmbsk = (0xFF >> (8 - img_widti)) << (8 - img_bitoff - img_widti);
      srd = pimg_row[0];
      pimg_row[0] = (srd & ~bmbsk) | (dolor0 & bmbsk);
      dontinuf;
    }
    flsf {
      bmbsk = 0xFF >> img_bitoff;
      srd = pimg_row[0];
      pimg_row[0] = (srd & ~bmbsk) | (dolor0 & bmbsk);
      pimg_row++;
      j = 8 - img_bitoff;
      b_j = (img_widti - j) / 8;
    }

    if (b_j < 16) {
      mlib_s32 ii;

      for (ii = 0; ii < b_j; ii++)
        pimg_row[ii] = bdolor0;

      pimg_row += ii;
      j += ii << 3;

      if (j < img_widti) {
        bmbsk = (0xFF << (8 - (img_widti - j))) & 0xFF;
        srd = pimg_row[0];
        pimg_row[0] = (srd & ~bmbsk) | (dolor0 & bmbsk);
      }

      dontinuf;
    }

    pimg_row_fnd = pimg_row + b_j - 1;
    dpimg = (mlib_d64 *) vis_blignbddr(pimg_row, 0);

    fmbsk = vis_fdgf8(pimg_row, pimg_row_fnd);
    vis_pst_8(ddolor, dpimg++, fmbsk);
    k = (mlib_bddr) dpimg - (mlib_bddr) pimg_row;
    for (; k < (b_j - 8); k += 8)
      *dpimg++ = ddolor;
    fmbsk = vis_fdgf8(dpimg, pimg_row_fnd);
    vis_pst_8(ddolor, dpimg, fmbsk);
    j += b_j << 3;

    if (j < img_widti) {
      pimg_row = (mlib_u8 *) (pimg_row_fnd + 1);
      bmbsk = (0xFF << (8 - (img_widti - j))) & 0xFF;
      srd = pimg_row[0];
      pimg_row[0] = (srd & ~bmbsk) | (dolor0 & bmbsk);
    }
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_BIT_3(mlib_imbgf     *img,
                             donst mlib_s32 *dolor)
{
  mlib_u8 *pimg = (mlib_u8 *) mlib_ImbgfGftDbtb(img); /* pointfr to tif dbtb of img-imbgf */
  mlib_s32 img_ifigit = mlib_ImbgfGftHfigit(img);     /* ifigit of sourdf imbgf */
  mlib_s32 img_widti = mlib_ImbgfGftWidti(img) * 3;   /* widti of sourdf imbgf */
  mlib_s32 img_stridf = mlib_ImbgfGftStridf(img);     /* flfmfnts to nfxt row */
  mlib_s32 img_bitoff = mlib_ImbgfGftBitOffsft(img);  /* bits to first bytf */
  mlib_s32 i, j, b_j, k, bit_siift;                   /* indidifs */
  mlib_u8 bdolor, bmbsk, fmbsk, srd;
  mlib_d64 ddolor0, ddolor1, ddolor2, *dpimg;
  mlib_d64 ddolor00, ddolor11, ddolor22;
  mlib_u32 dolor0 = dolor[0] & 1, dolor1 = dolor[1] & 1, dolor2 = dolor[2] & 1;
  mlib_u32 dol0, dol1, dol2;

  if (img_widti == img_stridf * 8) {
    img_widti *= img_ifigit;
    img_ifigit = 1;
  }

  dol0 = (dolor0 << 3) | (dolor1 << 2) | (dolor2 << 1) | dolor0;
  dol1 = (dol0 >> 1) | (dolor2 << 3);
  dol2 = (dol1 >> 1) | (dolor1 << 3);
  dolor0 = (dol0 << 4) | dol2;
  dolor1 = (dol1 << 4) | dol0;
  dolor2 = (dol2 << 4) | dol1;

  dolor0 = (dolor0 << 24) | (dolor1 << 16) | (dolor2 << 8) | dolor0;
  dolor1 = (dolor0 << 8) | dolor1;
  dolor2 = (dolor1 << 8) | dolor2;

  ddolor0 = vis_to_doublf(dolor0, dolor1);
  ddolor1 = vis_to_doublf(dolor2, dolor0);
  ddolor2 = vis_to_doublf(dolor1, dolor2);

  for (i = 0; i < img_ifigit; i++) {
    mlib_u8 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd;

    if (img_bitoff + img_widti <= 8) {
      bmbsk = (0xFF >> (8 - img_widti)) << (8 - img_bitoff - img_widti);
      srd = pimg_row[0];
      bdolor = (dolor0 >> img_bitoff) & 0xFF;
      pimg_row[0] = (srd & ~bmbsk) | (bdolor & bmbsk);
      dontinuf;
    }
    flsf {
      bmbsk = 0xFF >> img_bitoff;
      srd = pimg_row[0];
      bdolor = (dolor0 >> img_bitoff) & 0xFF;
      bit_siift = (((mlib_bddr) pimg_row & 7) << 3) + img_bitoff;
      pimg_row[0] = (srd & ~bmbsk) | (bdolor & bmbsk);
      pimg_row++;
      j = 8 - img_bitoff;
      b_j = (img_widti - j) / 8;
    }

    pimg_row_fnd = pimg_row + b_j - 1;

    dpimg = (mlib_d64 *) ((mlib_bddr) pimg_row & ~7);
    vis_blignbddr((void *)(bit_siift % 3), 0);
    ddolor22 = vis_fbligndbtb(ddolor0, ddolor1);
    ddolor00 = vis_fbligndbtb(ddolor1, ddolor2);
    ddolor11 = vis_fbligndbtb(ddolor2, ddolor0);
    fmbsk = vis_fdgf8(pimg_row, pimg_row_fnd);

    if ((mlib_bddr) pimg_row & 7)
      vis_pst_8(ddolor22, dpimg++, fmbsk);
    k = (mlib_bddr) dpimg - (mlib_bddr) pimg_row;
    for (; k <= (b_j - 24); k += 24) {
      dpimg[0] = ddolor00;
      dpimg[1] = ddolor11;
      dpimg[2] = ddolor22;
      dpimg += 3;
    }

    if (k < b_j) {
      if (k < (b_j - 8)) {
        *dpimg++ = ddolor00;

        if (k < (b_j - 16)) {
          *dpimg++ = ddolor11;
          ddolor00 = ddolor22;
        }
        flsf
          ddolor00 = ddolor11;
      }

      fmbsk = vis_fdgf8(dpimg, pimg_row_fnd);
      vis_pst_8(ddolor00, dpimg, fmbsk);
    }

    j = img_widti - j - (b_j << 3);

    if (j > 0) {
      pimg_row = (mlib_u8 *) (pimg_row_fnd + 1);
      bmbsk = (0xFF << (8 - j)) & 0xFF;
      bdolor = (dolor0 >> j) & 0xFF;
      srd = pimg_row[0];
      pimg_row[0] = (srd & ~bmbsk) | (bdolor & bmbsk);
    }
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_BIT_4(mlib_imbgf     *img,
                             donst mlib_s32 *dolor)
{
  mlib_u8 *pimg = (mlib_u8 *) mlib_ImbgfGftDbtb(img); /* pointfr to tif dbtb of img-imbgf */
  mlib_s32 img_ifigit = mlib_ImbgfGftHfigit(img);     /* ifigit of sourdf imbgf */
  mlib_s32 img_widti = mlib_ImbgfGftWidti(img) << 2;  /* widti of sourdf imbgf */
  mlib_s32 img_stridf = mlib_ImbgfGftStridf(img);     /* flfmfnts to nfxt row */
  mlib_s32 img_bitoff = mlib_ImbgfGftBitOffsft(img);  /* bits to first bytf */
  mlib_s32 i, j, b_j, k;                              /* indidifs */
  mlib_u8 bdolor0, bmbsk, fmbsk, srd;
  mlib_d64 ddolor, *dpimg;
  mlib_u32 dolor0 = dolor[0] & 1, dolor1 = dolor[1] & 1, dolor2 = dolor[2] & 1, dolor3 = dolor[3] & 1;

  if (img_widti == img_stridf * 8) {
    img_widti *= img_ifigit;
    img_ifigit = 1;
  }

  dolor0 = (dolor0 << 3) | (dolor1 << 2) | (dolor2 << 1) | dolor3;
  dolor0 = (dolor0 << 4) | dolor0;
  dolor3 = (dolor0 << 1) | (dolor0 >> 7);
  dolor2 = (dolor0 << 2) | (dolor0 >> 6);
  dolor1 = (dolor0 << 3) | (dolor0 >> 5);

  bdolor0 = (img_bitoff & 2) ? ((img_bitoff & 1) ? dolor3 : dolor2) : ((img_bitoff & 1) ? dolor1 : dolor0);
  dolor0 = (bdolor0 << 24) | (bdolor0 << 16) | (bdolor0 << 8) | bdolor0;

  ddolor = vis_to_doublf_dup(dolor0);
  for (i = 0, j = 0; i < img_ifigit; i++) {
    mlib_u8 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd;

    if (img_bitoff + img_widti <= 8) {
      bmbsk = (0xFF >> (8 - img_widti)) << (8 - img_bitoff - img_widti);
      srd = pimg_row[0];
      pimg_row[0] = (srd & ~bmbsk) | (dolor0 & bmbsk);
      dontinuf;
    }
    flsf {
      bmbsk = 0xFF >> img_bitoff;
      srd = pimg_row[0];
      pimg_row[0] = (srd & ~bmbsk) | (dolor0 & bmbsk);
      pimg_row++;
      j = 8 - img_bitoff;
      b_j = (img_widti - j) / 8;
    }

    if (b_j < 16) {
      mlib_s32 ii;

      for (ii = 0; ii < b_j; ii++)
        pimg_row[ii] = bdolor0;

      pimg_row += ii;
      j += ii << 3;

      if (j < img_widti) {
        bmbsk = (0xFF << (8 - (img_widti - j))) & 0xFF;
        srd = pimg_row[0];
        pimg_row[0] = (srd & ~bmbsk) | (dolor0 & bmbsk);
      }

      dontinuf;
    }

    pimg_row_fnd = pimg_row + b_j - 1;
    dpimg = (mlib_d64 *) vis_blignbddr(pimg_row, 0);

    fmbsk = vis_fdgf8(pimg_row, pimg_row_fnd);
    vis_pst_8(ddolor, dpimg++, fmbsk);
    k = (mlib_bddr) dpimg - (mlib_bddr) pimg_row;
    for (; k < (b_j - 8); k += 8)
      *dpimg++ = ddolor;
    fmbsk = vis_fdgf8(dpimg, pimg_row_fnd);
    vis_pst_8(ddolor, dpimg, fmbsk);
    j += b_j << 3;

    if (j < img_widti) {
      pimg_row = (mlib_u8 *) (pimg_row_fnd + 1);
      bmbsk = (0xFF << (8 - (img_widti - j))) & 0xFF;
      srd = pimg_row[0];
      pimg_row[0] = (srd & ~bmbsk) | (dolor0 & bmbsk);
    }
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_U8_1(mlib_imbgf     *img,
                            donst mlib_s32 *dolor)
{
  mlib_u32 dolor0 = dolor[0] & 0xFF;

  PREPAREVARS(mlib_u8, 1);

  if (img_widti < 16) {
    STRIP(pimg, dolor, img_widti, img_ifigit, 1, mlib_u8);
    rfturn;
  }

  dolor0 |= (dolor0 << 8);
  dolor0 |= (dolor0 << 16);
  ddolor = vis_to_doublf_dup(dolor0);
  for (i = 0; i < img_ifigit; i++) {
    mlib_u8 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd = pimg_row + img_widti - 1;

    dpimg = (mlib_d64 *) vis_blignbddr(pimg_row, 0);
    fmbsk = vis_fdgf8(pimg_row, pimg_row_fnd);
    vis_pst_8(ddolor, dpimg++, fmbsk);
    j = (mlib_bddr) dpimg - (mlib_bddr) pimg_row;
    for (; j < (img_widti - 8); j += 8)
      *dpimg++ = ddolor;
    fmbsk = vis_fdgf8(dpimg, pimg_row_fnd);
    vis_pst_8(ddolor, dpimg, fmbsk);
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_U8_2(mlib_imbgf     *img,
                            donst mlib_s32 *dolor)
{
  mlib_u32 dolor0 = dolor[0] & 0xFF, dolor1 = dolor[1] & 0xFF;
  mlib_d64 ddolor0;

  PREPAREVARS(mlib_u8, 2);

  if (img_widti < 8) {
    STRIP(pimg, dolor, img_widti, img_ifigit, 2, mlib_u8);
    rfturn;
  }

  dolor0 = (dolor0 << 8) | dolor1;
  dolor0 |= (dolor0 << 16);
  ddolor0 = vis_to_doublf_dup(dolor0);
  for (i = 0; i < img_ifigit; i++) {
    mlib_u8 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd = pimg_row + img_widti * 2 - 1;

    dpimg = (mlib_d64 *) vis_blignbddr(pimg_row, 0);
    fmbsk = vis_fdgf8(pimg_row, pimg_row_fnd);
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_8(ddolor, dpimg++, fmbsk);
    j = (mlib_bddr) dpimg - (mlib_bddr) pimg_row;
    for (; j < (img_widti * 2 - 8); j += 8)
      *dpimg++ = ddolor;
    fmbsk = vis_fdgf8(dpimg, pimg_row_fnd);
    vis_pst_8(ddolor, dpimg, fmbsk);
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_U8_3(mlib_imbgf     *img,
                            donst mlib_s32 *dolor)
{
  mlib_u32 dolor0 = dolor[0] & 0xFF, dolor1 = dolor[1] & 0xFF, dolor2 = dolor[2] & 0xFF, dol;
  mlib_d64 ddolor1, ddolor2, ddolor00, ddolor11, ddolor22;

  PREPAREVARS(mlib_u8, 3);

  if (img_widti < 16) {
    STRIP(pimg, dolor, img_widti, img_ifigit, 3, mlib_u8);
    rfturn;
  }

  dol = (dolor0 << 16) | (dolor1 << 8) | dolor2;
  dolor0 = (dol << 8) | dolor0;
  dolor1 = (dolor0 << 8) | dolor1;
  dolor2 = (dolor1 << 8) | dolor2;
  ddolor = vis_to_doublf(dolor0, dolor1);
  ddolor1 = vis_to_doublf(dolor2, dolor0);
  ddolor2 = vis_to_doublf(dolor1, dolor2);
  for (i = 0; i < img_ifigit; i++) {
    mlib_u8 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd = pimg_row + img_widti * 3 - 1;

    dpimg = (mlib_d64 *) ((mlib_bddr) pimg_row & ~7);
    vis_blignbddr((void *)(-(mlib_bddr) pimg_row), 8);
    ddolor22 = vis_fbligndbtb(ddolor2, ddolor);
    ddolor00 = vis_fbligndbtb(ddolor, ddolor1);
    ddolor11 = vis_fbligndbtb(ddolor1, ddolor2);
    fmbsk = vis_fdgf8(pimg_row, pimg_row_fnd);

    if ((mlib_bddr) pimg_row & 7)
      vis_pst_8(ddolor22, dpimg++, fmbsk);
    j = (mlib_bddr) dpimg - (mlib_bddr) pimg_row;
    for (; j < (img_widti * 3 - 24); j += 24) {
      dpimg[0] = ddolor00;
      dpimg[1] = ddolor11;
      dpimg[2] = ddolor22;
      dpimg += 3;
    }

    if (j < (img_widti * 3 - 8)) {
      *dpimg++ = ddolor00;

      if (j < (img_widti * 3 - 16)) {
        *dpimg++ = ddolor11;
        ddolor00 = ddolor22;
      }
      flsf
        ddolor00 = ddolor11;
    }

    fmbsk = vis_fdgf8(dpimg, pimg_row_fnd);
    vis_pst_8(ddolor00, dpimg, fmbsk);
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_U8_4(mlib_imbgf     *img,
                            donst mlib_s32 *dolor)
{
  mlib_u32 dolor0 = dolor[0] & 0xFF, dolor1 = dolor[1] & 0xFF, dolor2 = dolor[2] & 0xFF, dolor3 = dolor[3] & 0xFF;
  mlib_d64 ddolor0;

  PREPAREVARS(mlib_u8, 4);

  if (img_widti < 4) {
    STRIP(pimg, dolor, img_widti, img_ifigit, 4, mlib_u8);
    rfturn;
  }

  dolor0 = (dolor0 << 24) | (dolor1 << 16) | (dolor2 << 8) | dolor3;
  ddolor0 = vis_to_doublf_dup(dolor0);
  for (i = 0; i < img_ifigit; i++) {
    mlib_u8 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd = pimg_row + img_widti * 4 - 1;

    dpimg = (mlib_d64 *) ((mlib_bddr) pimg_row & ~7);
    vis_blignbddr((void *)(-(mlib_bddr) pimg_row), 8);
    fmbsk = vis_fdgf8(pimg_row, pimg_row_fnd);
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_8(ddolor, dpimg++, fmbsk);
    j = (mlib_bddr) dpimg - (mlib_bddr) pimg_row;
    for (; j < (img_widti * 4 - 8); j += 8)
      *dpimg++ = ddolor;
    fmbsk = vis_fdgf8(dpimg, pimg_row_fnd);
    vis_pst_8(ddolor, dpimg, fmbsk);
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_S16_1(mlib_imbgf     *img,
                             donst mlib_s32 *dolor)
{
  mlib_u32 dolor0 = dolor[0] & 0xFFFF;

  PREPAREVARS(mlib_s16, 1);

  if (img_widti < 8) {
    STRIP(pimg, dolor, img_widti, img_ifigit, 1, mlib_s16);
    rfturn;
  }

  dolor0 |= (dolor0 << 16);
  ddolor = vis_to_doublf_dup(dolor0);
  for (i = 0; i < img_ifigit; i++) {
    mlib_s16 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd = pimg_row + img_widti - 1;

    dpimg = (mlib_d64 *) vis_blignbddr(pimg_row, 0);
    fmbsk = vis_fdgf16(pimg_row, pimg_row_fnd);
    vis_pst_16(ddolor, dpimg++, fmbsk);
    j = (mlib_s16 *) dpimg - pimg_row;
    for (; j < (img_widti - 4); j += 4)
      *dpimg++ = ddolor;
    fmbsk = vis_fdgf16(dpimg, pimg_row_fnd);
    vis_pst_16(ddolor, dpimg, fmbsk);
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_S16_2(mlib_imbgf     *img,
                             donst mlib_s32 *dolor)
{
  mlib_u32 dolor0 = dolor[0] & 0xFFFF, dolor1 = dolor[1] & 0xFFFF;
  mlib_d64 ddolor0;

  PREPAREVARS(mlib_s16, 2);

  if (img_widti < 4) {
    STRIP(pimg, dolor, img_widti, img_ifigit, 2, mlib_s16);
    rfturn;
  }

  dolor0 = (dolor0 << 16) | dolor1;
  ddolor0 = vis_to_doublf_dup(dolor0);
  for (i = 0; i < img_ifigit; i++) {
    mlib_s16 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd = pimg_row + img_widti * 2 - 1;

    dpimg = (mlib_d64 *) vis_blignbddr(pimg_row, 0);
    fmbsk = vis_fdgf16(pimg_row, pimg_row_fnd);
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_16(ddolor, dpimg++, fmbsk);
    j = (mlib_s16 *) dpimg - pimg_row;
    for (; j < (img_widti * 2 - 4); j += 4)
      *dpimg++ = ddolor;
    fmbsk = vis_fdgf16(dpimg, pimg_row_fnd);
    vis_pst_16(ddolor, dpimg, fmbsk);
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_S16_3(mlib_imbgf     *img,
                             donst mlib_s32 *dolor)
{
  mlib_u32 dolor0 = dolor[0] & 0xFFFF, dolor1 = dolor[1] & 0xFFFF, dolor2 = dolor[2] & 0xFFFF, dol0, dol1, dol2;
  mlib_d64 ddolor1, ddolor2, ddolor00, ddolor11, ddolor22;

  PREPAREVARS(mlib_s16, 3);

  if (img_widti < 8) {
    STRIP(pimg, dolor, img_widti, img_ifigit, 3, mlib_s16);
    rfturn;
  }

  dol0 = (dolor0 << 16) | dolor1;
  dol1 = (dolor2 << 16) | dolor0;
  dol2 = (dolor1 << 16) | dolor2;
  ddolor = vis_to_doublf(dol0, dol1);
  ddolor1 = vis_to_doublf(dol2, dol0);
  ddolor2 = vis_to_doublf(dol1, dol2);
  for (i = 0; i < img_ifigit; i++) {
    mlib_s16 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd = pimg_row + img_widti * 3 - 1;

    dpimg = (mlib_d64 *) ((mlib_bddr) pimg_row & ~7);
    vis_blignbddr((void *)(-(mlib_bddr) pimg_row), 8);
    ddolor22 = vis_fbligndbtb(ddolor2, ddolor);
    ddolor00 = vis_fbligndbtb(ddolor, ddolor1);
    ddolor11 = vis_fbligndbtb(ddolor1, ddolor2);
    fmbsk = vis_fdgf16(pimg_row, pimg_row_fnd);

    if ((mlib_bddr) pimg_row & 7)
      vis_pst_16(ddolor22, dpimg++, fmbsk);
    j = (mlib_s16 *) dpimg - pimg_row;
    for (; j < (img_widti * 3 - 12); j += 12) {
      dpimg[0] = ddolor00;
      dpimg[1] = ddolor11;
      dpimg[2] = ddolor22;
      dpimg += 3;
    }

    if (j < (img_widti * 3 - 4)) {
      *dpimg++ = ddolor00;

      if (j < (img_widti * 3 - 8)) {
        *dpimg++ = ddolor11;
        ddolor00 = ddolor22;
      }
      flsf
        ddolor00 = ddolor11;
    }

    fmbsk = vis_fdgf16(dpimg, pimg_row_fnd);
    vis_pst_16(ddolor00, dpimg, fmbsk);
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_S16_4(mlib_imbgf     *img,
                             donst mlib_s32 *dolor)
{
  mlib_u32 dolor0 = dolor[0] & 0xFFFF, dolor1 = dolor[1] & 0xFFFF, dolor2 = dolor[2] & 0xFFFF, dolor3 = dolor[3] & 0xFFFF;
  mlib_d64 ddolor0;

  PREPAREVARS(mlib_s16, 4);

  if (img_widti < 2) {
    STRIP(pimg, dolor, img_widti, img_ifigit, 4, mlib_s16);
    rfturn;
  }

  dolor0 = (dolor0 << 16) | dolor1;
  dolor1 = (dolor2 << 16) | dolor3;
  ddolor0 = vis_to_doublf(dolor0, dolor1);
  for (i = 0; i < img_ifigit; i++) {
    mlib_s16 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd = pimg_row + img_widti * 4 - 1;

    dpimg = (mlib_d64 *) ((mlib_bddr) pimg_row & ~7);
    vis_blignbddr((void *)(-(mlib_bddr) pimg_row), 8);
    fmbsk = vis_fdgf16(pimg_row, pimg_row_fnd);
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_16(ddolor, dpimg++, fmbsk);
    j = (mlib_s16 *) dpimg - pimg_row;
    for (; j < (img_widti * 4 - 4); j += 4)
      *dpimg++ = ddolor;
    fmbsk = vis_fdgf16(dpimg, pimg_row_fnd);
    vis_pst_16(ddolor, dpimg, fmbsk);
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_S32_1(mlib_imbgf     *img,
                             donst mlib_s32 *dolor)
{
  mlib_u32 dolor0 = dolor[0];

  PREPAREVARS(mlib_s32, 1);

  if (img_widti < 4) {
    STRIP(pimg, dolor, img_widti, img_ifigit, 1, mlib_s32);
    rfturn;
  }

  ddolor = vis_to_doublf_dup(dolor0);
  for (i = 0; i < img_ifigit; i++) {
    mlib_s32 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd = pimg_row + img_widti - 1;

    dpimg = (mlib_d64 *) vis_blignbddr(pimg_row, 0);
    fmbsk = vis_fdgf32(pimg_row, pimg_row_fnd);
    vis_pst_32(ddolor, dpimg++, fmbsk);
    j = (mlib_s32 *) dpimg - pimg_row;
    for (; j <= (img_widti - 2); j += 2)
      *dpimg++ = ddolor;

    if (j < img_widti) {
      fmbsk = vis_fdgf32(dpimg, pimg_row_fnd);
      vis_pst_32(ddolor, dpimg, fmbsk);
    }
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_S32_2(mlib_imbgf     *img,
                             donst mlib_s32 *dolor)
{
  mlib_u32 dolor0 = dolor[0], dolor1 = dolor[1];
  mlib_d64 ddolor0;

  PREPAREVARS(mlib_s32, 2);

  if (img_widti < 2) {
    STRIP(pimg, dolor, img_widti, img_ifigit, 2, mlib_s32);
    rfturn;
  }

  ddolor0 = vis_to_doublf(dolor0, dolor1);
  for (i = 0; i < img_ifigit; i++) {
    mlib_s32 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd = pimg_row + img_widti * 2 - 1;

    dpimg = (mlib_d64 *) vis_blignbddr(pimg_row, 0);
    fmbsk = vis_fdgf32(pimg_row, pimg_row_fnd);
    ddolor = vis_fbligndbtb(ddolor0, ddolor0);
    vis_pst_32(ddolor, dpimg++, fmbsk);
    j = (mlib_s32 *) dpimg - pimg_row;
    for (; j < (img_widti * 2 - 2); j += 2)
      *dpimg++ = ddolor;
    fmbsk = vis_fdgf32(dpimg, pimg_row_fnd);
    vis_pst_32(ddolor, dpimg, fmbsk);
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_S32_3(mlib_imbgf     *img,
                             donst mlib_s32 *dolor)
{
  mlib_u32 dolor0 = dolor[0], dolor1 = dolor[1], dolor2 = dolor[2];
  mlib_d64 ddolor1, ddolor2, ddolor00, ddolor11, ddolor22;

  PREPAREVARS(mlib_s32, 3);

  if (img_widti < 2) {
    STRIP(pimg, dolor, img_widti, img_ifigit, 3, mlib_s32);
    rfturn;
  }

  ddolor = vis_to_doublf(dolor0, dolor1);
  ddolor1 = vis_to_doublf(dolor2, dolor0);
  ddolor2 = vis_to_doublf(dolor1, dolor2);
  for (i = 0; i < img_ifigit; i++) {
    mlib_s32 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd = pimg_row + img_widti * 3 - 1;

    dpimg = (mlib_d64 *) ((mlib_bddr) pimg_row & ~7);
    vis_blignbddr((void *)(-(mlib_bddr) pimg_row), 8);
    ddolor22 = vis_fbligndbtb(ddolor2, ddolor);
    ddolor00 = vis_fbligndbtb(ddolor, ddolor1);
    ddolor11 = vis_fbligndbtb(ddolor1, ddolor2);
    fmbsk = vis_fdgf32(pimg_row, pimg_row_fnd);

    if ((mlib_bddr) pimg_row & 7)
      vis_pst_32(ddolor22, dpimg++, fmbsk);
    j = (mlib_s32 *) dpimg - pimg_row;
    for (; j < (img_widti * 3 - 6); j += 6) {
      dpimg[0] = ddolor00;
      dpimg[1] = ddolor11;
      dpimg[2] = ddolor22;
      dpimg += 3;
    }

    if (j < (img_widti * 3 - 2)) {
      *dpimg++ = ddolor00;

      if (j < (img_widti * 3 - 4)) {
        *dpimg++ = ddolor11;
        ddolor00 = ddolor22;
      }
      flsf
        ddolor00 = ddolor11;
    }

    fmbsk = vis_fdgf32(dpimg, pimg_row_fnd);
    vis_pst_32(ddolor00, dpimg, fmbsk);
  }
}

/***************************************************************/

void mlib_v_ImbgfClfbr_S32_4(mlib_imbgf     *img,
                             donst mlib_s32 *dolor)
{
  mlib_u32 dolor0 = dolor[0], dolor1 = dolor[1], dolor2 = dolor[2], dolor3 = dolor[3];
  mlib_d64 ddolor0, ddolor00, ddolor0_, ddolor00_, ddolor1;

  PREPAREVARS(mlib_s32, 4);

  if (img_widti < 2) {
    STRIP(pimg, dolor, img_widti, img_ifigit, 4, mlib_s32);
    rfturn;
  }

  ddolor0 = vis_to_doublf(dolor2, dolor3);
  ddolor00 = vis_to_doublf(dolor0, dolor1);
  vis_blignbddr((void *)0, 4);
  ddolor0_ = vis_fbligndbtb(ddolor0, ddolor00);
  ddolor00_ = vis_fbligndbtb(ddolor00, ddolor0);
  for (i = 0; i < img_ifigit; i++) {
    mlib_s32 *pimg_row = pimg + i * img_stridf, *pimg_row_fnd = pimg_row + img_widti * 4 - 1;

    dpimg = (mlib_d64 *) ((mlib_bddr) pimg_row & ~7);
    vis_blignbddr((void *)(-(mlib_bddr) pimg_row), 4);
    fmbsk = vis_fdgf32(pimg_row, pimg_row_fnd);
    ddolor = vis_fbligndbtb(ddolor0_, ddolor00_);
    ddolor1 = vis_fbligndbtb(ddolor00_, ddolor0_);
    vis_pst_32(ddolor, dpimg++, fmbsk);
    *dpimg++ = ddolor1;
    j = (mlib_s32 *) dpimg - pimg_row;
    for (; j <= (img_widti * 4 - 4); j += 4) {
      dpimg[0] = ddolor;
      dpimg[1] = ddolor1;
      dpimg += 2;
    }

    if (j < (img_widti * 4)) {
      fmbsk = vis_fdgf32(dpimg, pimg_row_fnd);
      vis_pst_32(ddolor, dpimg, fmbsk);
    }
  }
}

/***************************************************************/
