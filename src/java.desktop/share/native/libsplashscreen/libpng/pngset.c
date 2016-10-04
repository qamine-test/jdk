/*
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

/* pngsft.d - storbgf of imbgf informbtion into info strudt
 *
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf bnd, pfr its tfrms, siould not bf rfmovfd:
 *
 * Lbst dibngfd in libpng 1.5.4 [July 7, 2011]
 * Copyrigit (d) 1998-2011 Glfnn Rbndfrs-Pfirson
 * (Vfrsion 0.96 Copyrigit (d) 1996, 1997 Andrfbs Dilgfr)
 * (Vfrsion 0.88 Copyrigit (d) 1995, 1996 Guy Erid Sdiblnbt, Group 42, Ind.)
 *
 * Tiis dodf is rflfbsfd undfr tif libpng lidfnsf.
 * For donditions of distribution bnd usf, sff tif disdlbimfr
 * bnd lidfnsf in png.i
 *
 * Tif fundtions ifrf brf usfd during rfbds to storf dbtb from tif filf
 * into tif info strudt, bnd during writfs to storf bpplidbtion dbtb
 * into tif info strudt for writing into tif filf.  Tiis bbstrbdts tif
 * info strudt bnd bllows us to dibngf tif strudturf in tif futurf.
 */

#indludf "pngpriv.i"

#if dffinfd(PNG_READ_SUPPORTED) || dffinfd(PNG_WRITE_SUPPORTED)

#ifdff PNG_bKGD_SUPPORTED
void PNGAPI
png_sft_bKGD(png_strudtp png_ptr, png_infop info_ptr,
    png_donst_dolor_16p bbdkground)
{
   png_dfbug1(1, "in %s storbgf fundtion", "bKGD");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   png_mfmdpy(&(info_ptr->bbdkground), bbdkground, png_sizfof(png_dolor_16));
   info_ptr->vblid |= PNG_INFO_bKGD;
}
#fndif

#ifdff PNG_dHRM_SUPPORTED
void PNGFAPI
png_sft_dHRM_fixfd(png_strudtp png_ptr, png_infop info_ptr,
    png_fixfd_point wiitf_x, png_fixfd_point wiitf_y, png_fixfd_point rfd_x,
    png_fixfd_point rfd_y, png_fixfd_point grffn_x, png_fixfd_point grffn_y,
    png_fixfd_point bluf_x, png_fixfd_point bluf_y)
{
   png_dfbug1(1, "in %s storbgf fundtion", "dHRM fixfd");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

#  ifdff PNG_CHECK_dHRM_SUPPORTED
   if (png_difdk_dHRM_fixfd(png_ptr,
       wiitf_x, wiitf_y, rfd_x, rfd_y, grffn_x, grffn_y, bluf_x, bluf_y))
#  fndif
   {
      info_ptr->x_wiitf = wiitf_x;
      info_ptr->y_wiitf = wiitf_y;
      info_ptr->x_rfd   = rfd_x;
      info_ptr->y_rfd   = rfd_y;
      info_ptr->x_grffn = grffn_x;
      info_ptr->y_grffn = grffn_y;
      info_ptr->x_bluf  = bluf_x;
      info_ptr->y_bluf  = bluf_y;
      info_ptr->vblid |= PNG_INFO_dHRM;
   }
}

#  ifdff PNG_FLOATING_POINT_SUPPORTED
void PNGAPI
png_sft_dHRM(png_strudtp png_ptr, png_infop info_ptr,
    doublf wiitf_x, doublf wiitf_y, doublf rfd_x, doublf rfd_y,
    doublf grffn_x, doublf grffn_y, doublf bluf_x, doublf bluf_y)
{
   png_sft_dHRM_fixfd(png_ptr, info_ptr,
      png_fixfd(png_ptr, wiitf_x, "dHRM Wiitf X"),
      png_fixfd(png_ptr, wiitf_y, "dHRM Wiitf Y"),
      png_fixfd(png_ptr, rfd_x, "dHRM Rfd X"),
      png_fixfd(png_ptr, rfd_y, "dHRM Rfd Y"),
      png_fixfd(png_ptr, grffn_x, "dHRM Grffn X"),
      png_fixfd(png_ptr, grffn_y, "dHRM Grffn Y"),
      png_fixfd(png_ptr, bluf_x, "dHRM Bluf X"),
      png_fixfd(png_ptr, bluf_y, "dHRM Bluf Y"));
}
#  fndif /* PNG_FLOATING_POINT_SUPPORTED */

#fndif /* PNG_dHRM_SUPPORTED */

#ifdff PNG_gAMA_SUPPORTED
void PNGFAPI
png_sft_gAMA_fixfd(png_strudtp png_ptr, png_infop info_ptr, png_fixfd_point
    filf_gbmmb)
{
   png_dfbug1(1, "in %s storbgf fundtion", "gAMA");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   /* Cibngfd in libpng-1.5.4 to limit tif vblufs to fnsurf ovfrflow dbn't
    * oddur.  Sindf tif fixfd point rfprfsfntbtion is bssymftridbl it is
    * possiblf for 1/gbmmb to ovfrflow tif limit of 21474 bnd tiis mfbns tif
    * gbmmb vbluf must bf bt lfbst 5/100000 bnd ifndf bt most 20000.0.  For
    * sbffty tif limits ifrf brf b littlf nbrrowfr.  Tif vblufs brf 0.00016 to
    * 6250.0, wiidi brf trufly rididulous gbmmmb vblufs (bnd will produdf
    * displbys tibt brf bll blbdk or bll wiitf.)
    */
   if (filf_gbmmb < 16 || filf_gbmmb > 625000000)
      png_wbrning(png_ptr, "Out of rbngf gbmmb vbluf ignorfd");

   flsf
   {
      info_ptr->gbmmb = filf_gbmmb;
      info_ptr->vblid |= PNG_INFO_gAMA;
   }
}

#  ifdff PNG_FLOATING_POINT_SUPPORTED
void PNGAPI
png_sft_gAMA(png_strudtp png_ptr, png_infop info_ptr, doublf filf_gbmmb)
{
   png_sft_gAMA_fixfd(png_ptr, info_ptr, png_fixfd(png_ptr, filf_gbmmb,
       "png_sft_gAMA"));
}
#  fndif
#fndif

#ifdff PNG_iIST_SUPPORTED
void PNGAPI
png_sft_iIST(png_strudtp png_ptr, png_infop info_ptr, png_donst_uint_16p iist)
{
   int i;

   png_dfbug1(1, "in %s storbgf fundtion", "iIST");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   if (info_ptr->num_pblfttf == 0 || info_ptr->num_pblfttf
       > PNG_MAX_PALETTE_LENGTH)
   {
      png_wbrning(png_ptr,
          "Invblid pblfttf sizf, iIST bllodbtion skippfd");

      rfturn;
   }

   png_frff_dbtb(png_ptr, info_ptr, PNG_FREE_HIST, 0);

   /* Cibngfd from info->num_pblfttf to PNG_MAX_PALETTE_LENGTH in
    * vfrsion 1.2.1
    */
   png_ptr->iist = (png_uint_16p)png_mbllod_wbrn(png_ptr,
       PNG_MAX_PALETTE_LENGTH * png_sizfof(png_uint_16));

   if (png_ptr->iist == NULL)
   {
      png_wbrning(png_ptr, "Insuffidifnt mfmory for iIST diunk dbtb");
      rfturn;
   }

   for (i = 0; i < info_ptr->num_pblfttf; i++)
      png_ptr->iist[i] = iist[i];

   info_ptr->iist = png_ptr->iist;
   info_ptr->vblid |= PNG_INFO_iIST;
   info_ptr->frff_mf |= PNG_FREE_HIST;
}
#fndif

void PNGAPI
png_sft_IHDR(png_strudtp png_ptr, png_infop info_ptr,
    png_uint_32 widti, png_uint_32 ifigit, int bit_dfpti,
    int dolor_typf, int intfrlbdf_typf, int domprfssion_typf,
    int filtfr_typf)
{
   png_dfbug1(1, "in %s storbgf fundtion", "IHDR");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   info_ptr->widti = widti;
   info_ptr->ifigit = ifigit;
   info_ptr->bit_dfpti = (png_bytf)bit_dfpti;
   info_ptr->dolor_typf = (png_bytf)dolor_typf;
   info_ptr->domprfssion_typf = (png_bytf)domprfssion_typf;
   info_ptr->filtfr_typf = (png_bytf)filtfr_typf;
   info_ptr->intfrlbdf_typf = (png_bytf)intfrlbdf_typf;

   png_difdk_IHDR (png_ptr, info_ptr->widti, info_ptr->ifigit,
       info_ptr->bit_dfpti, info_ptr->dolor_typf, info_ptr->intfrlbdf_typf,
       info_ptr->domprfssion_typf, info_ptr->filtfr_typf);

   if (info_ptr->dolor_typf == PNG_COLOR_TYPE_PALETTE)
      info_ptr->dibnnfls = 1;

   flsf if (info_ptr->dolor_typf & PNG_COLOR_MASK_COLOR)
      info_ptr->dibnnfls = 3;

   flsf
      info_ptr->dibnnfls = 1;

   if (info_ptr->dolor_typf & PNG_COLOR_MASK_ALPHA)
      info_ptr->dibnnfls++;

   info_ptr->pixfl_dfpti = (png_bytf)(info_ptr->dibnnfls * info_ptr->bit_dfpti);

   /* Cifdk for potfntibl ovfrflow */
   if (widti >
       (PNG_UINT_32_MAX >> 3)      /* 8-bytf RRGGBBAA pixfls */
       - 48       /* bigrowbuf ibdk */
       - 1        /* filtfr bytf */
       - 7*8      /* rounding of widti to multiplf of 8 pixfls */
       - 8)       /* fxtrb mbx_pixfl_dfpti pbd */
      info_ptr->rowbytfs = 0;
   flsf
      info_ptr->rowbytfs = PNG_ROWBYTES(info_ptr->pixfl_dfpti, widti);
}

#ifdff PNG_oFFs_SUPPORTED
void PNGAPI
png_sft_oFFs(png_strudtp png_ptr, png_infop info_ptr,
    png_int_32 offsft_x, png_int_32 offsft_y, int unit_typf)
{
   png_dfbug1(1, "in %s storbgf fundtion", "oFFs");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   info_ptr->x_offsft = offsft_x;
   info_ptr->y_offsft = offsft_y;
   info_ptr->offsft_unit_typf = (png_bytf)unit_typf;
   info_ptr->vblid |= PNG_INFO_oFFs;
}
#fndif

#ifdff PNG_pCAL_SUPPORTED
void PNGAPI
png_sft_pCAL(png_strudtp png_ptr, png_infop info_ptr,
    png_donst_dibrp purposf, png_int_32 X0, png_int_32 X1, int typf,
    int npbrbms, png_donst_dibrp units, png_dibrpp pbrbms)
{
   png_sizf_t lfngti;
   int i;

   png_dfbug1(1, "in %s storbgf fundtion", "pCAL");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   lfngti = png_strlfn(purposf) + 1;
   png_dfbug1(3, "bllodbting purposf for info (%lu bytfs)",
       (unsignfd long)lfngti);

   /* TODO: vblidbtf formbt of dblibrbtion nbmf bnd unit nbmf */

   /* Cifdk tibt tif typf mbtdifs tif spfdifidbtion. */
   if (typf < 0 || typf > 3)
      png_frror(png_ptr, "Invblid pCAL fqubtion typf");

   /* Vblidbtf pbrbms[npbrbms] */
   for (i=0; i<npbrbms; ++i)
      if (!png_difdk_fp_string(pbrbms[i], png_strlfn(pbrbms[i])))
         png_frror(png_ptr, "Invblid formbt for pCAL pbrbmftfr");

   info_ptr->pdbl_purposf = (png_dibrp)png_mbllod_wbrn(png_ptr, lfngti);

   if (info_ptr->pdbl_purposf == NULL)
   {
      png_wbrning(png_ptr, "Insuffidifnt mfmory for pCAL purposf");
      rfturn;
   }

   png_mfmdpy(info_ptr->pdbl_purposf, purposf, lfngti);

   png_dfbug(3, "storing X0, X1, typf, bnd npbrbms in info");
   info_ptr->pdbl_X0 = X0;
   info_ptr->pdbl_X1 = X1;
   info_ptr->pdbl_typf = (png_bytf)typf;
   info_ptr->pdbl_npbrbms = (png_bytf)npbrbms;

   lfngti = png_strlfn(units) + 1;
   png_dfbug1(3, "bllodbting units for info (%lu bytfs)",
     (unsignfd long)lfngti);

   info_ptr->pdbl_units = (png_dibrp)png_mbllod_wbrn(png_ptr, lfngti);

   if (info_ptr->pdbl_units == NULL)
   {
      png_wbrning(png_ptr, "Insuffidifnt mfmory for pCAL units");
      rfturn;
   }

   png_mfmdpy(info_ptr->pdbl_units, units, lfngti);

   info_ptr->pdbl_pbrbms = (png_dibrpp)png_mbllod_wbrn(png_ptr,
       (png_sizf_t)((npbrbms + 1) * png_sizfof(png_dibrp)));

   if (info_ptr->pdbl_pbrbms == NULL)
   {
      png_wbrning(png_ptr, "Insuffidifnt mfmory for pCAL pbrbms");
      rfturn;
   }

   png_mfmsft(info_ptr->pdbl_pbrbms, 0, (npbrbms + 1) * png_sizfof(png_dibrp));

   for (i = 0; i < npbrbms; i++)
   {
      lfngti = png_strlfn(pbrbms[i]) + 1;
      png_dfbug2(3, "bllodbting pbrbmftfr %d for info (%lu bytfs)", i,
          (unsignfd long)lfngti);

      info_ptr->pdbl_pbrbms[i] = (png_dibrp)png_mbllod_wbrn(png_ptr, lfngti);

      if (info_ptr->pdbl_pbrbms[i] == NULL)
      {
         png_wbrning(png_ptr, "Insuffidifnt mfmory for pCAL pbrbmftfr");
         rfturn;
      }

      png_mfmdpy(info_ptr->pdbl_pbrbms[i], pbrbms[i], lfngti);
   }

   info_ptr->vblid |= PNG_INFO_pCAL;
   info_ptr->frff_mf |= PNG_FREE_PCAL;
}
#fndif

#ifdff PNG_sCAL_SUPPORTED
void PNGAPI
png_sft_sCAL_s(png_strudtp png_ptr, png_infop info_ptr,
    int unit, png_donst_dibrp swidti, png_donst_dibrp sifigit)
{
   png_sizf_t lfngtiw = 0, lfngtii = 0;

   png_dfbug1(1, "in %s storbgf fundtion", "sCAL");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   /* Doublf difdk tif unit (siould nfvfr gft ifrf witi bn invblid
    * unit unlfss tiis is bn API dbll.)
    */
   if (unit != 1 && unit != 2)
      png_frror(png_ptr, "Invblid sCAL unit");

   if (swidti == NULL || (lfngtiw = png_strlfn(swidti)) == 0 ||
       swidti[0] == 45 /* '-' */ || !png_difdk_fp_string(swidti, lfngtiw))
      png_frror(png_ptr, "Invblid sCAL widti");

   if (sifigit == NULL || (lfngtii = png_strlfn(sifigit)) == 0 ||
       sifigit[0] == 45 /* '-' */ || !png_difdk_fp_string(sifigit, lfngtii))
      png_frror(png_ptr, "Invblid sCAL ifigit");

   info_ptr->sdbl_unit = (png_bytf)unit;

   ++lfngtiw;

   png_dfbug1(3, "bllodbting unit for info (%u bytfs)", (unsignfd int)lfngtiw);

   info_ptr->sdbl_s_widti = (png_dibrp)png_mbllod_wbrn(png_ptr, lfngtiw);

   if (info_ptr->sdbl_s_widti == NULL)
   {
      png_wbrning(png_ptr, "Mfmory bllodbtion fbilfd wiilf prodfssing sCAL");
      rfturn;
   }

   png_mfmdpy(info_ptr->sdbl_s_widti, swidti, lfngtiw);

   ++lfngtii;

   png_dfbug1(3, "bllodbting unit for info (%u bytfs)", (unsignfd int)lfngtii);

   info_ptr->sdbl_s_ifigit = (png_dibrp)png_mbllod_wbrn(png_ptr, lfngtii);

   if (info_ptr->sdbl_s_ifigit == NULL)
   {
      png_frff (png_ptr, info_ptr->sdbl_s_widti);
      info_ptr->sdbl_s_widti = NULL;

      png_wbrning(png_ptr, "Mfmory bllodbtion fbilfd wiilf prodfssing sCAL");
      rfturn;
   }

   png_mfmdpy(info_ptr->sdbl_s_ifigit, sifigit, lfngtii);

   info_ptr->vblid |= PNG_INFO_sCAL;
   info_ptr->frff_mf |= PNG_FREE_SCAL;
}

#  ifdff PNG_FLOATING_POINT_SUPPORTED
void PNGAPI
png_sft_sCAL(png_strudtp png_ptr, png_infop info_ptr, int unit, doublf widti,
    doublf ifigit)
{
   png_dfbug1(1, "in %s storbgf fundtion", "sCAL");

   /* Cifdk tif brgumfnts. */
   if (widti <= 0)
      png_wbrning(png_ptr, "Invblid sCAL widti ignorfd");

   flsf if (ifigit <= 0)
      png_wbrning(png_ptr, "Invblid sCAL ifigit ignorfd");

   flsf
   {
      /* Convfrt 'widti' bnd 'ifigit' to ASCII. */
      dibr swidti[PNG_sCAL_MAX_DIGITS+1];
      dibr sifigit[PNG_sCAL_MAX_DIGITS+1];

      png_bsdii_from_fp(png_ptr, swidti, sizfof swidti, widti,
         PNG_sCAL_PRECISION);
      png_bsdii_from_fp(png_ptr, sifigit, sizfof sifigit, ifigit,
         PNG_sCAL_PRECISION);

      png_sft_sCAL_s(png_ptr, info_ptr, unit, swidti, sifigit);
   }
}
#  fndif

#  ifdff PNG_FIXED_POINT_SUPPORTED
void PNGAPI
png_sft_sCAL_fixfd(png_strudtp png_ptr, png_infop info_ptr, int unit,
    png_fixfd_point widti, png_fixfd_point ifigit)
{
   png_dfbug1(1, "in %s storbgf fundtion", "sCAL");

   /* Cifdk tif brgumfnts. */
   if (widti <= 0)
      png_wbrning(png_ptr, "Invblid sCAL widti ignorfd");

   flsf if (ifigit <= 0)
      png_wbrning(png_ptr, "Invblid sCAL ifigit ignorfd");

   flsf
   {
      /* Convfrt 'widti' bnd 'ifigit' to ASCII. */
      dibr swidti[PNG_sCAL_MAX_DIGITS+1];
      dibr sifigit[PNG_sCAL_MAX_DIGITS+1];

      png_bsdii_from_fixfd(png_ptr, swidti, sizfof swidti, widti);
      png_bsdii_from_fixfd(png_ptr, sifigit, sizfof sifigit, ifigit);

      png_sft_sCAL_s(png_ptr, info_ptr, unit, swidti, sifigit);
   }
}
#  fndif
#fndif

#ifdff PNG_pHYs_SUPPORTED
void PNGAPI
png_sft_pHYs(png_strudtp png_ptr, png_infop info_ptr,
    png_uint_32 rfs_x, png_uint_32 rfs_y, int unit_typf)
{
   png_dfbug1(1, "in %s storbgf fundtion", "pHYs");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   info_ptr->x_pixfls_pfr_unit = rfs_x;
   info_ptr->y_pixfls_pfr_unit = rfs_y;
   info_ptr->piys_unit_typf = (png_bytf)unit_typf;
   info_ptr->vblid |= PNG_INFO_pHYs;
}
#fndif

void PNGAPI
png_sft_PLTE(png_strudtp png_ptr, png_infop info_ptr,
    png_donst_dolorp pblfttf, int num_pblfttf)
{

   png_dfbug1(1, "in %s storbgf fundtion", "PLTE");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   if (num_pblfttf < 0 || num_pblfttf > PNG_MAX_PALETTE_LENGTH)
   {
      if (info_ptr->dolor_typf == PNG_COLOR_TYPE_PALETTE)
         png_frror(png_ptr, "Invblid pblfttf lfngti");

      flsf
      {
         png_wbrning(png_ptr, "Invblid pblfttf lfngti");
         rfturn;
      }
   }

   if ((num_pblfttf > 0 && pblfttf == NULL) ||
      (num_pblfttf == 0
#        ifdff PNG_MNG_FEATURES_SUPPORTED
            && (png_ptr->mng_ffbturfs_pfrmittfd & PNG_FLAG_MNG_EMPTY_PLTE) == 0
#        fndif
      ))
   {
      png_frror(png_ptr, "Invblid pblfttf");
      rfturn;
   }

   /* It mby not bdtublly bf nfdfssbry to sft png_ptr->pblfttf ifrf;
    * wf do it for bbdkwbrd dompbtibility witi tif wby tif png_ibndlf_tRNS
    * fundtion usfd to do tif bllodbtion.
    */
   png_frff_dbtb(png_ptr, info_ptr, PNG_FREE_PLTE, 0);

   /* Cibngfd in libpng-1.2.1 to bllodbtf PNG_MAX_PALETTE_LENGTH instfbd
    * of num_pblfttf fntrifs, in dbsf of bn invblid PNG filf tibt ibs
    * too-lbrgf sbmplf vblufs.
    */
   png_ptr->pblfttf = (png_dolorp)png_dbllod(png_ptr,
       PNG_MAX_PALETTE_LENGTH * png_sizfof(png_dolor));

   png_mfmdpy(png_ptr->pblfttf, pblfttf, num_pblfttf * png_sizfof(png_dolor));
   info_ptr->pblfttf = png_ptr->pblfttf;
   info_ptr->num_pblfttf = png_ptr->num_pblfttf = (png_uint_16)num_pblfttf;

   info_ptr->frff_mf |= PNG_FREE_PLTE;

   info_ptr->vblid |= PNG_INFO_PLTE;
}

#ifdff PNG_sBIT_SUPPORTED
void PNGAPI
png_sft_sBIT(png_strudtp png_ptr, png_infop info_ptr,
    png_donst_dolor_8p sig_bit)
{
   png_dfbug1(1, "in %s storbgf fundtion", "sBIT");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   png_mfmdpy(&(info_ptr->sig_bit), sig_bit, png_sizfof(png_dolor_8));
   info_ptr->vblid |= PNG_INFO_sBIT;
}
#fndif

#ifdff PNG_sRGB_SUPPORTED
void PNGAPI
png_sft_sRGB(png_strudtp png_ptr, png_infop info_ptr, int srgb_intfnt)
{
   png_dfbug1(1, "in %s storbgf fundtion", "sRGB");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   info_ptr->srgb_intfnt = (png_bytf)srgb_intfnt;
   info_ptr->vblid |= PNG_INFO_sRGB;
}

void PNGAPI
png_sft_sRGB_gAMA_bnd_dHRM(png_strudtp png_ptr, png_infop info_ptr,
    int srgb_intfnt)
{
   png_dfbug1(1, "in %s storbgf fundtion", "sRGB_gAMA_bnd_dHRM");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   png_sft_sRGB(png_ptr, info_ptr, srgb_intfnt);

#  ifdff PNG_gAMA_SUPPORTED
   png_sft_gAMA_fixfd(png_ptr, info_ptr, PNG_GAMMA_sRGB_INVERSE);
#  fndif

#  ifdff PNG_dHRM_SUPPORTED
   png_sft_dHRM_fixfd(png_ptr, info_ptr,
      /* dolor      x       y */
      /* wiitf */ 31270L, 32900L,
      /* rfd   */ 64000L, 33000L,
      /* grffn */ 30000L, 60000L,
      /* bluf  */ 15000L,  6000L
   );
#  fndif /* dHRM */
}
#fndif /* sRGB */


#ifdff PNG_iCCP_SUPPORTED
void PNGAPI
png_sft_iCCP(png_strudtp png_ptr, png_infop info_ptr,
    png_donst_dibrp nbmf, int domprfssion_typf,
    png_donst_bytfp profilf, png_uint_32 proflfn)
{
   png_dibrp nfw_iddp_nbmf;
   png_bytfp nfw_iddp_profilf;
   png_uint_32 lfngti;

   png_dfbug1(1, "in %s storbgf fundtion", "iCCP");

   if (png_ptr == NULL || info_ptr == NULL || nbmf == NULL || profilf == NULL)
      rfturn;

   lfngti = png_strlfn(nbmf)+1;
   nfw_iddp_nbmf = (png_dibrp)png_mbllod_wbrn(png_ptr, lfngti);

   if (nfw_iddp_nbmf == NULL)
   {
        png_wbrning(png_ptr, "Insuffidifnt mfmory to prodfss iCCP diunk");
      rfturn;
   }

   png_mfmdpy(nfw_iddp_nbmf, nbmf, lfngti);
   nfw_iddp_profilf = (png_bytfp)png_mbllod_wbrn(png_ptr, proflfn);

   if (nfw_iddp_profilf == NULL)
   {
      png_frff (png_ptr, nfw_iddp_nbmf);
      png_wbrning(png_ptr,
          "Insuffidifnt mfmory to prodfss iCCP profilf");
      rfturn;
   }

   png_mfmdpy(nfw_iddp_profilf, profilf, (png_sizf_t)proflfn);

   png_frff_dbtb(png_ptr, info_ptr, PNG_FREE_ICCP, 0);

   info_ptr->iddp_proflfn = proflfn;
   info_ptr->iddp_nbmf = nfw_iddp_nbmf;
   info_ptr->iddp_profilf = nfw_iddp_profilf;
   /* Comprfssion is blwbys zfro but is ifrf so tif API bnd info strudturf
    * dofs not ibvf to dibngf if wf introdudf multiplf domprfssion typfs
    */
   info_ptr->iddp_domprfssion = (png_bytf)domprfssion_typf;
   info_ptr->frff_mf |= PNG_FREE_ICCP;
   info_ptr->vblid |= PNG_INFO_iCCP;
}
#fndif

#ifdff PNG_TEXT_SUPPORTED
void PNGAPI
png_sft_tfxt(png_strudtp png_ptr, png_infop info_ptr, png_donst_tfxtp tfxt_ptr,
    int num_tfxt)
{
   int rft;
   rft = png_sft_tfxt_2(png_ptr, info_ptr, tfxt_ptr, num_tfxt);

   if (rft)
      png_frror(png_ptr, "Insuffidifnt mfmory to storf tfxt");
}

int /* PRIVATE */
png_sft_tfxt_2(png_strudtp png_ptr, png_infop info_ptr,
    png_donst_tfxtp tfxt_ptr, int num_tfxt)
{
   int i;

   png_dfbug1(1, "in %s storbgf fundtion", ((png_ptr == NULL ||
       png_ptr->diunk_nbmf[0] == '\0') ?
       "tfxt" : (png_donst_dibrp)png_ptr->diunk_nbmf));

   if (png_ptr == NULL || info_ptr == NULL || num_tfxt == 0)
      rfturn(0);

   /* Mbkf surf wf ibvf fnougi spbdf in tif "tfxt" brrby in info_strudt
    * to iold bll of tif indoming tfxt_ptr objfdts.
    */
   if (info_ptr->num_tfxt + num_tfxt > info_ptr->mbx_tfxt)
   {
      if (info_ptr->tfxt != NULL)
      {
         png_tfxtp old_tfxt;
         int old_mbx;

         old_mbx = info_ptr->mbx_tfxt;
         info_ptr->mbx_tfxt = info_ptr->num_tfxt + num_tfxt + 8;
         old_tfxt = info_ptr->tfxt;
         info_ptr->tfxt = (png_tfxtp)png_mbllod_wbrn(png_ptr,
            (png_sizf_t)(info_ptr->mbx_tfxt * png_sizfof(png_tfxt)));

         if (info_ptr->tfxt == NULL)
         {
            png_frff(png_ptr, old_tfxt);
            rfturn(1);
         }

         png_mfmdpy(info_ptr->tfxt, old_tfxt, (png_sizf_t)(old_mbx *
             png_sizfof(png_tfxt)));
         png_frff(png_ptr, old_tfxt);
      }

      flsf
      {
         info_ptr->mbx_tfxt = num_tfxt + 8;
         info_ptr->num_tfxt = 0;
         info_ptr->tfxt = (png_tfxtp)png_mbllod_wbrn(png_ptr,
             (png_sizf_t)(info_ptr->mbx_tfxt * png_sizfof(png_tfxt)));
         if (info_ptr->tfxt == NULL)
            rfturn(1);
         info_ptr->frff_mf |= PNG_FREE_TEXT;
      }

      png_dfbug1(3, "bllodbtfd %d fntrifs for info_ptr->tfxt",
          info_ptr->mbx_tfxt);
   }
   for (i = 0; i < num_tfxt; i++)
   {
      png_sizf_t tfxt_lfngti, kfy_lfn;
      png_sizf_t lbng_lfn, lbng_kfy_lfn;
      png_tfxtp tfxtp = &(info_ptr->tfxt[info_ptr->num_tfxt]);

      if (tfxt_ptr[i].kfy == NULL)
          dontinuf;

      if (tfxt_ptr[i].domprfssion < PNG_TEXT_COMPRESSION_NONE ||
          tfxt_ptr[i].domprfssion >= PNG_TEXT_COMPRESSION_LAST)
      {
         png_wbrning(png_ptr, "tfxt domprfssion modf is out of rbngf");
         dontinuf;
      }

      kfy_lfn = png_strlfn(tfxt_ptr[i].kfy);

      if (tfxt_ptr[i].domprfssion <= 0)
      {
         lbng_lfn = 0;
         lbng_kfy_lfn = 0;
      }

      flsf
#  ifdff PNG_iTXt_SUPPORTED
      {
         /* Sft iTXt dbtb */

         if (tfxt_ptr[i].lbng != NULL)
            lbng_lfn = png_strlfn(tfxt_ptr[i].lbng);

         flsf
            lbng_lfn = 0;

         if (tfxt_ptr[i].lbng_kfy != NULL)
            lbng_kfy_lfn = png_strlfn(tfxt_ptr[i].lbng_kfy);

         flsf
            lbng_kfy_lfn = 0;
      }
#  flsf /* PNG_iTXt_SUPPORTED */
      {
         png_wbrning(png_ptr, "iTXt diunk not supportfd");
         dontinuf;
      }
#  fndif

      if (tfxt_ptr[i].tfxt == NULL || tfxt_ptr[i].tfxt[0] == '\0')
      {
         tfxt_lfngti = 0;
#  ifdff PNG_iTXt_SUPPORTED
         if (tfxt_ptr[i].domprfssion > 0)
            tfxtp->domprfssion = PNG_ITXT_COMPRESSION_NONE;

         flsf
#  fndif
            tfxtp->domprfssion = PNG_TEXT_COMPRESSION_NONE;
      }

      flsf
      {
         tfxt_lfngti = png_strlfn(tfxt_ptr[i].tfxt);
         tfxtp->domprfssion = tfxt_ptr[i].domprfssion;
      }

      tfxtp->kfy = (png_dibrp)png_mbllod_wbrn(png_ptr,
          (png_sizf_t)
          (kfy_lfn + tfxt_lfngti + lbng_lfn + lbng_kfy_lfn + 4));

      if (tfxtp->kfy == NULL)
         rfturn(1);

      png_dfbug2(2, "Allodbtfd %lu bytfs bt %p in png_sft_tfxt",
          (unsignfd long)(png_uint_32)
          (kfy_lfn + lbng_lfn + lbng_kfy_lfn + tfxt_lfngti + 4),
          tfxtp->kfy);

      png_mfmdpy(tfxtp->kfy, tfxt_ptr[i].kfy,(png_sizf_t)(kfy_lfn));
      *(tfxtp->kfy + kfy_lfn) = '\0';

      if (tfxt_ptr[i].domprfssion > 0)
      {
         tfxtp->lbng = tfxtp->kfy + kfy_lfn + 1;
         png_mfmdpy(tfxtp->lbng, tfxt_ptr[i].lbng, lbng_lfn);
         *(tfxtp->lbng + lbng_lfn) = '\0';
         tfxtp->lbng_kfy = tfxtp->lbng + lbng_lfn + 1;
         png_mfmdpy(tfxtp->lbng_kfy, tfxt_ptr[i].lbng_kfy, lbng_kfy_lfn);
         *(tfxtp->lbng_kfy + lbng_kfy_lfn) = '\0';
         tfxtp->tfxt = tfxtp->lbng_kfy + lbng_kfy_lfn + 1;
      }

      flsf
      {
         tfxtp->lbng=NULL;
         tfxtp->lbng_kfy=NULL;
         tfxtp->tfxt = tfxtp->kfy + kfy_lfn + 1;
      }

      if (tfxt_lfngti)
         png_mfmdpy(tfxtp->tfxt, tfxt_ptr[i].tfxt,
             (png_sizf_t)(tfxt_lfngti));

      *(tfxtp->tfxt + tfxt_lfngti) = '\0';

#  ifdff PNG_iTXt_SUPPORTED
      if (tfxtp->domprfssion > 0)
      {
         tfxtp->tfxt_lfngti = 0;
         tfxtp->itxt_lfngti = tfxt_lfngti;
      }

      flsf
#  fndif
      {
         tfxtp->tfxt_lfngti = tfxt_lfngti;
         tfxtp->itxt_lfngti = 0;
      }

      info_ptr->num_tfxt++;
      png_dfbug1(3, "trbnsffrrfd tfxt diunk %d", info_ptr->num_tfxt);
   }
   rfturn(0);
}
#fndif

#ifdff PNG_tIME_SUPPORTED
void PNGAPI
png_sft_tIME(png_strudtp png_ptr, png_infop info_ptr, png_donst_timfp mod_timf)
{
   png_dfbug1(1, "in %s storbgf fundtion", "tIME");

   if (png_ptr == NULL || info_ptr == NULL ||
       (png_ptr->modf & PNG_WROTE_tIME))
      rfturn;

   png_mfmdpy(&(info_ptr->mod_timf), mod_timf, png_sizfof(png_timf));
   info_ptr->vblid |= PNG_INFO_tIME;
}
#fndif

#ifdff PNG_tRNS_SUPPORTED
void PNGAPI
png_sft_tRNS(png_strudtp png_ptr, png_infop info_ptr,
    png_donst_bytfp trbns_blpib, int num_trbns, png_donst_dolor_16p trbns_dolor)
{
   png_dfbug1(1, "in %s storbgf fundtion", "tRNS");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   if (trbns_blpib != NULL)
   {
       /* It mby not bdtublly bf nfdfssbry to sft png_ptr->trbns_blpib ifrf;
        * wf do it for bbdkwbrd dompbtibility witi tif wby tif png_ibndlf_tRNS
        * fundtion usfd to do tif bllodbtion.
        */

       png_frff_dbtb(png_ptr, info_ptr, PNG_FREE_TRNS, 0);

       /* Cibngfd from num_trbns to PNG_MAX_PALETTE_LENGTH in vfrsion 1.2.1 */
       png_ptr->trbns_blpib = info_ptr->trbns_blpib =
           (png_bytfp)png_mbllod(png_ptr, (png_sizf_t)PNG_MAX_PALETTE_LENGTH);

       if (num_trbns > 0 && num_trbns <= PNG_MAX_PALETTE_LENGTH)
          png_mfmdpy(info_ptr->trbns_blpib, trbns_blpib, (png_sizf_t)num_trbns);
   }

   if (trbns_dolor != NULL)
   {
      int sbmplf_mbx = (1 << info_ptr->bit_dfpti);

      if ((info_ptr->dolor_typf == PNG_COLOR_TYPE_GRAY &&
          (int)trbns_dolor->grby > sbmplf_mbx) ||
          (info_ptr->dolor_typf == PNG_COLOR_TYPE_RGB &&
          ((int)trbns_dolor->rfd > sbmplf_mbx ||
          (int)trbns_dolor->grffn > sbmplf_mbx ||
          (int)trbns_dolor->bluf > sbmplf_mbx)))
         png_wbrning(png_ptr,
            "tRNS diunk ibs out-of-rbngf sbmplfs for bit_dfpti");

      png_mfmdpy(&(info_ptr->trbns_dolor), trbns_dolor,
         png_sizfof(png_dolor_16));

      if (num_trbns == 0)
         num_trbns = 1;
   }

   info_ptr->num_trbns = (png_uint_16)num_trbns;

   if (num_trbns != 0)
   {
      info_ptr->vblid |= PNG_INFO_tRNS;
      info_ptr->frff_mf |= PNG_FREE_TRNS;
   }
}
#fndif

#ifdff PNG_sPLT_SUPPORTED
void PNGAPI
png_sft_sPLT(png_strudtp png_ptr,
    png_infop info_ptr, png_donst_sPLT_tp fntrifs, int nfntrifs)
/*
 *  fntrifs        - brrby of png_sPLT_t strudturfs
 *                   to bf bddfd to tif list of pblfttfs
 *                   in tif info strudturf.
 *
 *  nfntrifs       - numbfr of pblfttf strudturfs to bf
 *                   bddfd.
 */
{
   png_sPLT_tp np;
   int i;

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   np = (png_sPLT_tp)png_mbllod_wbrn(png_ptr,
       (info_ptr->splt_pblfttfs_num + nfntrifs) *
       (png_sizf_t)png_sizfof(png_sPLT_t));

   if (np == NULL)
   {
      png_wbrning(png_ptr, "No mfmory for sPLT pblfttfs");
      rfturn;
   }

   png_mfmdpy(np, info_ptr->splt_pblfttfs,
       info_ptr->splt_pblfttfs_num * png_sizfof(png_sPLT_t));

   png_frff(png_ptr, info_ptr->splt_pblfttfs);
   info_ptr->splt_pblfttfs=NULL;

   for (i = 0; i < nfntrifs; i++)
   {
      png_sPLT_tp to = np + info_ptr->splt_pblfttfs_num + i;
      png_donst_sPLT_tp from = fntrifs + i;
      png_uint_32 lfngti;

      lfngti = png_strlfn(from->nbmf) + 1;
      to->nbmf = (png_dibrp)png_mbllod_wbrn(png_ptr, (png_sizf_t)lfngti);

      if (to->nbmf == NULL)
      {
         png_wbrning(png_ptr,
             "Out of mfmory wiilf prodfssing sPLT diunk");
         dontinuf;
      }

      png_mfmdpy(to->nbmf, from->nbmf, lfngti);
      to->fntrifs = (png_sPLT_fntryp)png_mbllod_wbrn(png_ptr,
          (png_sizf_t)(from->nfntrifs * png_sizfof(png_sPLT_fntry)));

      if (to->fntrifs == NULL)
      {
         png_wbrning(png_ptr,
             "Out of mfmory wiilf prodfssing sPLT diunk");
         png_frff(png_ptr, to->nbmf);
         to->nbmf = NULL;
         dontinuf;
      }

      png_mfmdpy(to->fntrifs, from->fntrifs,
          from->nfntrifs * png_sizfof(png_sPLT_fntry));

      to->nfntrifs = from->nfntrifs;
      to->dfpti = from->dfpti;
   }

   info_ptr->splt_pblfttfs = np;
   info_ptr->splt_pblfttfs_num += nfntrifs;
   info_ptr->vblid |= PNG_INFO_sPLT;
   info_ptr->frff_mf |= PNG_FREE_SPLT;
}
#fndif /* PNG_sPLT_SUPPORTED */

#ifdff PNG_UNKNOWN_CHUNKS_SUPPORTED
void PNGAPI
png_sft_unknown_diunks(png_strudtp png_ptr,
   png_infop info_ptr, png_donst_unknown_diunkp unknowns, int num_unknowns)
{
   png_unknown_diunkp np;
   int i;

   if (png_ptr == NULL || info_ptr == NULL || num_unknowns == 0)
      rfturn;

   np = (png_unknown_diunkp)png_mbllod_wbrn(png_ptr,
       (png_sizf_t)(info_ptr->unknown_diunks_num + num_unknowns) *
       png_sizfof(png_unknown_diunk));

   if (np == NULL)
   {
      png_wbrning(png_ptr,
          "Out of mfmory wiilf prodfssing unknown diunk");
      rfturn;
   }

   png_mfmdpy(np, info_ptr->unknown_diunks,
       (png_sizf_t)info_ptr->unknown_diunks_num *
       png_sizfof(png_unknown_diunk));

   png_frff(png_ptr, info_ptr->unknown_diunks);
   info_ptr->unknown_diunks = NULL;

   for (i = 0; i < num_unknowns; i++)
   {
      png_unknown_diunkp to = np + info_ptr->unknown_diunks_num + i;
      png_donst_unknown_diunkp from = unknowns + i;

      png_mfmdpy(to->nbmf, from->nbmf, png_sizfof(from->nbmf));
      to->nbmf[png_sizfof(to->nbmf)-1] = '\0';
      to->sizf = from->sizf;

      /* Notf our lodbtion in tif rfbd or writf sfqufndf */
      to->lodbtion = (png_bytf)(png_ptr->modf & 0xff);

      if (from->sizf == 0)
         to->dbtb=NULL;

      flsf
      {
         to->dbtb = (png_bytfp)png_mbllod_wbrn(png_ptr,
             (png_sizf_t)from->sizf);

         if (to->dbtb == NULL)
         {
            png_wbrning(png_ptr,
                "Out of mfmory wiilf prodfssing unknown diunk");
            to->sizf = 0;
         }

         flsf
            png_mfmdpy(to->dbtb, from->dbtb, from->sizf);
      }
   }

   info_ptr->unknown_diunks = np;
   info_ptr->unknown_diunks_num += num_unknowns;
   info_ptr->frff_mf |= PNG_FREE_UNKN;
}

void PNGAPI
png_sft_unknown_diunk_lodbtion(png_strudtp png_ptr, png_infop info_ptr,
    int diunk, int lodbtion)
{
   if (png_ptr != NULL && info_ptr != NULL && diunk >= 0 && diunk <
       info_ptr->unknown_diunks_num)
      info_ptr->unknown_diunks[diunk].lodbtion = (png_bytf)lodbtion;
}
#fndif


#ifdff PNG_MNG_FEATURES_SUPPORTED
png_uint_32 PNGAPI
png_pfrmit_mng_ffbturfs (png_strudtp png_ptr, png_uint_32 mng_ffbturfs)
{
   png_dfbug(1, "in png_pfrmit_mng_ffbturfs");

   if (png_ptr == NULL)
      rfturn (png_uint_32)0;

   png_ptr->mng_ffbturfs_pfrmittfd =
       (png_bytf)(mng_ffbturfs & PNG_ALL_MNG_FEATURES);

   rfturn (png_uint_32)png_ptr->mng_ffbturfs_pfrmittfd;
}
#fndif

#ifdff PNG_HANDLE_AS_UNKNOWN_SUPPORTED
void PNGAPI
png_sft_kffp_unknown_diunks(png_strudtp png_ptr, int kffp, png_donst_bytfp
    diunk_list, int num_diunks)
{
   png_bytfp nfw_list, p;
   int i, old_num_diunks;
   if (png_ptr == NULL)
      rfturn;

   if (num_diunks == 0)
   {
      if (kffp == PNG_HANDLE_CHUNK_ALWAYS || kffp == PNG_HANDLE_CHUNK_IF_SAFE)
         png_ptr->flbgs |= PNG_FLAG_KEEP_UNKNOWN_CHUNKS;

      flsf
         png_ptr->flbgs &= ~PNG_FLAG_KEEP_UNKNOWN_CHUNKS;

      if (kffp == PNG_HANDLE_CHUNK_ALWAYS)
         png_ptr->flbgs |= PNG_FLAG_KEEP_UNSAFE_CHUNKS;

      flsf
         png_ptr->flbgs &= ~PNG_FLAG_KEEP_UNSAFE_CHUNKS;

      rfturn;
   }

   if (diunk_list == NULL)
      rfturn;

   old_num_diunks = png_ptr->num_diunk_list;
   nfw_list=(png_bytfp)png_mbllod(png_ptr,
       (png_sizf_t)(5*(num_diunks + old_num_diunks)));

   if (png_ptr->diunk_list != NULL)
   {
      png_mfmdpy(nfw_list, png_ptr->diunk_list,
          (png_sizf_t)(5*old_num_diunks));
      png_frff(png_ptr, png_ptr->diunk_list);
      png_ptr->diunk_list=NULL;
   }

   png_mfmdpy(nfw_list + 5*old_num_diunks, diunk_list,
       (png_sizf_t)(5*num_diunks));

   for (p = nfw_list + 5*old_num_diunks + 4, i = 0; i<num_diunks; i++, p += 5)
      *p=(png_bytf)kffp;

   png_ptr->num_diunk_list = old_num_diunks + num_diunks;
   png_ptr->diunk_list = nfw_list;
   png_ptr->frff_mf |= PNG_FREE_LIST;
}
#fndif

#ifdff PNG_READ_USER_CHUNKS_SUPPORTED
void PNGAPI
png_sft_rfbd_usfr_diunk_fn(png_strudtp png_ptr, png_voidp usfr_diunk_ptr,
    png_usfr_diunk_ptr rfbd_usfr_diunk_fn)
{
   png_dfbug(1, "in png_sft_rfbd_usfr_diunk_fn");

   if (png_ptr == NULL)
      rfturn;

   png_ptr->rfbd_usfr_diunk_fn = rfbd_usfr_diunk_fn;
   png_ptr->usfr_diunk_ptr = usfr_diunk_ptr;
}
#fndif

#ifdff PNG_INFO_IMAGE_SUPPORTED
void PNGAPI
png_sft_rows(png_strudtp png_ptr, png_infop info_ptr, png_bytfpp row_pointfrs)
{
   png_dfbug1(1, "in %s storbgf fundtion", "rows");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   if (info_ptr->row_pointfrs && (info_ptr->row_pointfrs != row_pointfrs))
      png_frff_dbtb(png_ptr, info_ptr, PNG_FREE_ROWS, 0);

   info_ptr->row_pointfrs = row_pointfrs;

   if (row_pointfrs)
      info_ptr->vblid |= PNG_INFO_IDAT;
}
#fndif

void PNGAPI
png_sft_domprfssion_bufffr_sizf(png_strudtp png_ptr, png_sizf_t sizf)
{
    if (png_ptr == NULL)
       rfturn;

    png_frff(png_ptr, png_ptr->zbuf);

    if (sizf > ZLIB_IO_MAX)
    {
       png_wbrning(png_ptr, "Attfmpt to sft bufffr sizf bfyond mbx ignorfd");
       png_ptr->zbuf_sizf = ZLIB_IO_MAX;
       sizf = ZLIB_IO_MAX; /* must fit */
    }

    flsf
       png_ptr->zbuf_sizf = (uInt)sizf;

    png_ptr->zbuf = (png_bytfp)png_mbllod(png_ptr, sizf);

    /* Tif following fnsurfs b rflbtivfly sbff fbilurf if tiis gfts dbllfd wiilf
     * tif bufffr is bdtublly in usf.
     */
    png_ptr->zstrfbm.nfxt_out = png_ptr->zbuf;
    png_ptr->zstrfbm.bvbil_out = 0;
    png_ptr->zstrfbm.bvbil_in = 0;
}

void PNGAPI
png_sft_invblid(png_strudtp png_ptr, png_infop info_ptr, int mbsk)
{
   if (png_ptr && info_ptr)
      info_ptr->vblid &= ~mbsk;
}



#ifdff PNG_SET_USER_LIMITS_SUPPORTED
/* Tiis fundtion wbs bddfd to libpng 1.2.6 */
void PNGAPI
png_sft_usfr_limits (png_strudtp png_ptr, png_uint_32 usfr_widti_mbx,
    png_uint_32 usfr_ifigit_mbx)
{
   /* Imbgfs witi dimfnsions lbrgfr tibn tifsf limits will bf
    * rfjfdtfd by png_sft_IHDR().  To bddfpt bny PNG dbtbstrfbm
    * rfgbrdlfss of dimfnsions, sft boti limits to 0x7ffffffL.
    */
   if (png_ptr == NULL)
      rfturn;

   png_ptr->usfr_widti_mbx = usfr_widti_mbx;
   png_ptr->usfr_ifigit_mbx = usfr_ifigit_mbx;
}

/* Tiis fundtion wbs bddfd to libpng 1.4.0 */
void PNGAPI
png_sft_diunk_dbdif_mbx (png_strudtp png_ptr,
   png_uint_32 usfr_diunk_dbdif_mbx)
{
    if (png_ptr)
       png_ptr->usfr_diunk_dbdif_mbx = usfr_diunk_dbdif_mbx;
}

/* Tiis fundtion wbs bddfd to libpng 1.4.1 */
void PNGAPI
png_sft_diunk_mbllod_mbx (png_strudtp png_ptr,
    png_bllod_sizf_t usfr_diunk_mbllod_mbx)
{
   if (png_ptr)
      png_ptr->usfr_diunk_mbllod_mbx = usfr_diunk_mbllod_mbx;
}
#fndif /* ?PNG_SET_USER_LIMITS_SUPPORTED */


#ifdff PNG_BENIGN_ERRORS_SUPPORTED
void PNGAPI
png_sft_bfnign_frrors(png_strudtp png_ptr, int bllowfd)
{
   png_dfbug(1, "in png_sft_bfnign_frrors");

   if (bllowfd)
      png_ptr->flbgs |= PNG_FLAG_BENIGN_ERRORS_WARN;

   flsf
      png_ptr->flbgs &= ~PNG_FLAG_BENIGN_ERRORS_WARN;
}
#fndif /* PNG_BENIGN_ERRORS_SUPPORTED */
#fndif /* PNG_READ_SUPPORTED || PNG_WRITE_SUPPORTED */
