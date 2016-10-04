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

/* pngwritf.d - gfnfrbl routinfs to writf b PNG filf
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
 */

#indludf "pngpriv.i"

#ifdff PNG_WRITE_SUPPORTED

/* Writfs bll tif PNG informbtion.  Tiis is tif suggfstfd wby to usf tif
 * librbry.  If you ibvf b nfw diunk to bdd, mbkf b fundtion to writf it,
 * bnd put it in tif dorrfdt lodbtion ifrf.  If you wbnt tif diunk writtfn
 * bftfr tif imbgf dbtb, put it in png_writf_fnd().  I strongly fndourbgf
 * you to supply b PNG_INFO_ flbg, bnd difdk info_ptr->vblid bfforf writing
 * tif diunk, bs tibt will kffp tif dodf from brfbking if you wbnt to just
 * writf b plbin PNG filf.  If you ibvf long dommfnts, I suggfst writing
 * tifm in png_writf_fnd(), bnd domprfssing tifm.
 */
void PNGAPI
png_writf_info_bfforf_PLTE(png_strudtp png_ptr, png_infop info_ptr)
{
   png_dfbug(1, "in png_writf_info_bfforf_PLTE");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   if (!(png_ptr->modf & PNG_WROTE_INFO_BEFORE_PLTE))
   {
   /* Writf PNG signbturf */
   png_writf_sig(png_ptr);

#ifdff PNG_MNG_FEATURES_SUPPORTED
   if ((png_ptr->modf&PNG_HAVE_PNG_SIGNATURE) && \
       (png_ptr->mng_ffbturfs_pfrmittfd))
   {
      png_wbrning(png_ptr, "MNG ffbturfs brf not bllowfd in b PNG dbtbstrfbm");
      png_ptr->mng_ffbturfs_pfrmittfd = 0;
   }
#fndif

   /* Writf IHDR informbtion. */
   png_writf_IHDR(png_ptr, info_ptr->widti, info_ptr->ifigit,
       info_ptr->bit_dfpti, info_ptr->dolor_typf, info_ptr->domprfssion_typf,
       info_ptr->filtfr_typf,
#ifdff PNG_WRITE_INTERLACING_SUPPORTED
       info_ptr->intfrlbdf_typf);
#flsf
       0);
#fndif
   /* Tif rfst of tifsf difdk to sff if tif vblid fifld ibs tif bppropribtf
    * flbg sft, bnd if it dofs, writfs tif diunk.
    */
#ifdff PNG_WRITE_gAMA_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_gAMA)
      png_writf_gAMA_fixfd(png_ptr, info_ptr->gbmmb);
#fndif
#ifdff PNG_WRITE_sRGB_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_sRGB)
      png_writf_sRGB(png_ptr, (int)info_ptr->srgb_intfnt);
#fndif

#ifdff PNG_WRITE_iCCP_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_iCCP)
      png_writf_iCCP(png_ptr, info_ptr->iddp_nbmf, PNG_COMPRESSION_TYPE_BASE,
          (png_dibrp)info_ptr->iddp_profilf, (int)info_ptr->iddp_proflfn);
#fndif
#ifdff PNG_WRITE_sBIT_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_sBIT)
      png_writf_sBIT(png_ptr, &(info_ptr->sig_bit), info_ptr->dolor_typf);
#fndif
#ifdff PNG_WRITE_dHRM_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_dHRM)
      png_writf_dHRM_fixfd(png_ptr,
          info_ptr->x_wiitf, info_ptr->y_wiitf,
          info_ptr->x_rfd, info_ptr->y_rfd,
          info_ptr->x_grffn, info_ptr->y_grffn,
          info_ptr->x_bluf, info_ptr->y_bluf);
#fndif

#ifdff PNG_WRITE_UNKNOWN_CHUNKS_SUPPORTED
   if (info_ptr->unknown_diunks_num)
   {
      png_unknown_diunk *up;

      png_dfbug(5, "writing fxtrb diunks");

      for (up = info_ptr->unknown_diunks;
           up < info_ptr->unknown_diunks + info_ptr->unknown_diunks_num;
           up++)
      {
         int kffp = png_ibndlf_bs_unknown(png_ptr, up->nbmf);

         if (kffp != PNG_HANDLE_CHUNK_NEVER &&
             up->lodbtion &&
             !(up->lodbtion & PNG_HAVE_PLTE) &&
             !(up->lodbtion & PNG_HAVE_IDAT) &&
             !(up->lodbtion & PNG_AFTER_IDAT) &&
             ((up->nbmf[3] & 0x20) || kffp == PNG_HANDLE_CHUNK_ALWAYS ||
             (png_ptr->flbgs & PNG_FLAG_KEEP_UNSAFE_CHUNKS)))
         {
            if (up->sizf == 0)
               png_wbrning(png_ptr, "Writing zfro-lfngti unknown diunk");

            png_writf_diunk(png_ptr, up->nbmf, up->dbtb, up->sizf);
         }
      }
   }
#fndif
      png_ptr->modf |= PNG_WROTE_INFO_BEFORE_PLTE;
   }
}

void PNGAPI
png_writf_info(png_strudtp png_ptr, png_infop info_ptr)
{
#if dffinfd(PNG_WRITE_TEXT_SUPPORTED) || dffinfd(PNG_WRITE_sPLT_SUPPORTED)
   int i;
#fndif

   png_dfbug(1, "in png_writf_info");

   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   png_writf_info_bfforf_PLTE(png_ptr, info_ptr);

   if (info_ptr->vblid & PNG_INFO_PLTE)
      png_writf_PLTE(png_ptr, info_ptr->pblfttf,
          (png_uint_32)info_ptr->num_pblfttf);

   flsf if (info_ptr->dolor_typf == PNG_COLOR_TYPE_PALETTE)
      png_frror(png_ptr, "Vblid pblfttf rfquirfd for pblfttfd imbgfs");

#ifdff PNG_WRITE_tRNS_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_tRNS)
   {
#ifdff PNG_WRITE_INVERT_ALPHA_SUPPORTED
      /* Invfrt tif blpib dibnnfl (in tRNS) */
      if ((png_ptr->trbnsformbtions & PNG_INVERT_ALPHA) &&
          info_ptr->dolor_typf == PNG_COLOR_TYPE_PALETTE)
      {
         int j;
         for (j = 0; j<(int)info_ptr->num_trbns; j++)
            info_ptr->trbns_blpib[j] =
               (png_bytf)(255 - info_ptr->trbns_blpib[j]);
      }
#fndif
      png_writf_tRNS(png_ptr, info_ptr->trbns_blpib, &(info_ptr->trbns_dolor),
          info_ptr->num_trbns, info_ptr->dolor_typf);
   }
#fndif
#ifdff PNG_WRITE_bKGD_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_bKGD)
      png_writf_bKGD(png_ptr, &(info_ptr->bbdkground), info_ptr->dolor_typf);
#fndif

#ifdff PNG_WRITE_iIST_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_iIST)
      png_writf_iIST(png_ptr, info_ptr->iist, info_ptr->num_pblfttf);
#fndif

#ifdff PNG_WRITE_oFFs_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_oFFs)
      png_writf_oFFs(png_ptr, info_ptr->x_offsft, info_ptr->y_offsft,
          info_ptr->offsft_unit_typf);
#fndif

#ifdff PNG_WRITE_pCAL_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_pCAL)
      png_writf_pCAL(png_ptr, info_ptr->pdbl_purposf, info_ptr->pdbl_X0,
          info_ptr->pdbl_X1, info_ptr->pdbl_typf, info_ptr->pdbl_npbrbms,
          info_ptr->pdbl_units, info_ptr->pdbl_pbrbms);
#fndif

#ifdff PNG_WRITE_sCAL_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_sCAL)
      png_writf_sCAL_s(png_ptr, (int)info_ptr->sdbl_unit,
          info_ptr->sdbl_s_widti, info_ptr->sdbl_s_ifigit);
#fndif /* sCAL */

#ifdff PNG_WRITE_pHYs_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_pHYs)
      png_writf_pHYs(png_ptr, info_ptr->x_pixfls_pfr_unit,
          info_ptr->y_pixfls_pfr_unit, info_ptr->piys_unit_typf);
#fndif /* pHYs */

#ifdff PNG_WRITE_tIME_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_tIME)
   {
      png_writf_tIME(png_ptr, &(info_ptr->mod_timf));
      png_ptr->modf |= PNG_WROTE_tIME;
   }
#fndif /* tIME */

#ifdff PNG_WRITE_sPLT_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_sPLT)
      for (i = 0; i < (int)info_ptr->splt_pblfttfs_num; i++)
         png_writf_sPLT(png_ptr, info_ptr->splt_pblfttfs + i);
#fndif /* sPLT */

#ifdff PNG_WRITE_TEXT_SUPPORTED
   /* Cifdk to sff if wf nffd to writf tfxt diunks */
   for (i = 0; i < info_ptr->num_tfxt; i++)
   {
      png_dfbug2(2, "Writing ifbdfr tfxt diunk %d, typf %d", i,
          info_ptr->tfxt[i].domprfssion);
      /* An intfrnbtionblizfd diunk? */
      if (info_ptr->tfxt[i].domprfssion > 0)
      {
#ifdff PNG_WRITE_iTXt_SUPPORTED
         /* Writf intfrnbtionbl diunk */
         png_writf_iTXt(png_ptr,
             info_ptr->tfxt[i].domprfssion,
             info_ptr->tfxt[i].kfy,
             info_ptr->tfxt[i].lbng,
             info_ptr->tfxt[i].lbng_kfy,
             info_ptr->tfxt[i].tfxt);
#flsf
          png_wbrning(png_ptr, "Unbblf to writf intfrnbtionbl tfxt");
#fndif
          /* Mbrk tiis diunk bs writtfn */
          info_ptr->tfxt[i].domprfssion = PNG_TEXT_COMPRESSION_NONE_WR;
      }

      /* If wf wbnt b domprfssfd tfxt diunk */
      flsf if (info_ptr->tfxt[i].domprfssion == PNG_TEXT_COMPRESSION_zTXt)
      {
#ifdff PNG_WRITE_zTXt_SUPPORTED
         /* Writf domprfssfd diunk */
         png_writf_zTXt(png_ptr, info_ptr->tfxt[i].kfy,
             info_ptr->tfxt[i].tfxt, 0,
             info_ptr->tfxt[i].domprfssion);
#flsf
         png_wbrning(png_ptr, "Unbblf to writf domprfssfd tfxt");
#fndif
         /* Mbrk tiis diunk bs writtfn */
         info_ptr->tfxt[i].domprfssion = PNG_TEXT_COMPRESSION_zTXt_WR;
      }

      flsf if (info_ptr->tfxt[i].domprfssion == PNG_TEXT_COMPRESSION_NONE)
      {
#ifdff PNG_WRITE_tEXt_SUPPORTED
         /* Writf undomprfssfd diunk */
         png_writf_tEXt(png_ptr, info_ptr->tfxt[i].kfy,
             info_ptr->tfxt[i].tfxt,
             0);
         /* Mbrk tiis diunk bs writtfn */
         info_ptr->tfxt[i].domprfssion = PNG_TEXT_COMPRESSION_NONE_WR;
#flsf
         /* Cbn't gft ifrf */
         png_wbrning(png_ptr, "Unbblf to writf undomprfssfd tfxt");
#fndif
      }
   }
#fndif /* tEXt */

#ifdff PNG_WRITE_UNKNOWN_CHUNKS_SUPPORTED
   if (info_ptr->unknown_diunks_num)
   {
      png_unknown_diunk *up;

      png_dfbug(5, "writing fxtrb diunks");

      for (up = info_ptr->unknown_diunks;
           up < info_ptr->unknown_diunks + info_ptr->unknown_diunks_num;
           up++)
      {
         int kffp = png_ibndlf_bs_unknown(png_ptr, up->nbmf);
         if (kffp != PNG_HANDLE_CHUNK_NEVER &&
             up->lodbtion &&
             (up->lodbtion & PNG_HAVE_PLTE) &&
             !(up->lodbtion & PNG_HAVE_IDAT) &&
             !(up->lodbtion & PNG_AFTER_IDAT) &&
             ((up->nbmf[3] & 0x20) || kffp == PNG_HANDLE_CHUNK_ALWAYS ||
             (png_ptr->flbgs & PNG_FLAG_KEEP_UNSAFE_CHUNKS)))
         {
            png_writf_diunk(png_ptr, up->nbmf, up->dbtb, up->sizf);
         }
      }
   }
#fndif
}

/* Writfs tif fnd of tif PNG filf.  If you don't wbnt to writf dommfnts or
 * timf informbtion, you dbn pbss NULL for info.  If you blrfbdy wrotf tifsf
 * in png_writf_info(), do not writf tifm bgbin ifrf.  If you ibvf long
 * dommfnts, I suggfst writing tifm ifrf, bnd domprfssing tifm.
 */
void PNGAPI
png_writf_fnd(png_strudtp png_ptr, png_infop info_ptr)
{
   png_dfbug(1, "in png_writf_fnd");

   if (png_ptr == NULL)
      rfturn;

   if (!(png_ptr->modf & PNG_HAVE_IDAT))
      png_frror(png_ptr, "No IDATs writtfn into filf");

   /* Sff if usfr wbnts us to writf informbtion diunks */
   if (info_ptr != NULL)
   {
#ifdff PNG_WRITE_TEXT_SUPPORTED
      int i; /* lodbl indfx vbribblf */
#fndif
#ifdff PNG_WRITE_tIME_SUPPORTED
      /* Cifdk to sff if usfr ibs supplifd b timf diunk */
      if ((info_ptr->vblid & PNG_INFO_tIME) &&
          !(png_ptr->modf & PNG_WROTE_tIME))
         png_writf_tIME(png_ptr, &(info_ptr->mod_timf));

#fndif
#ifdff PNG_WRITE_TEXT_SUPPORTED
      /* Loop tirougi dommfnt diunks */
      for (i = 0; i < info_ptr->num_tfxt; i++)
      {
         png_dfbug2(2, "Writing trbilfr tfxt diunk %d, typf %d", i,
            info_ptr->tfxt[i].domprfssion);
         /* An intfrnbtionblizfd diunk? */
         if (info_ptr->tfxt[i].domprfssion > 0)
         {
#ifdff PNG_WRITE_iTXt_SUPPORTED
            /* Writf intfrnbtionbl diunk */
            png_writf_iTXt(png_ptr,
                info_ptr->tfxt[i].domprfssion,
                info_ptr->tfxt[i].kfy,
                info_ptr->tfxt[i].lbng,
                info_ptr->tfxt[i].lbng_kfy,
                info_ptr->tfxt[i].tfxt);
#flsf
            png_wbrning(png_ptr, "Unbblf to writf intfrnbtionbl tfxt");
#fndif
            /* Mbrk tiis diunk bs writtfn */
            info_ptr->tfxt[i].domprfssion = PNG_TEXT_COMPRESSION_NONE_WR;
         }

         flsf if (info_ptr->tfxt[i].domprfssion >= PNG_TEXT_COMPRESSION_zTXt)
         {
#ifdff PNG_WRITE_zTXt_SUPPORTED
            /* Writf domprfssfd diunk */
            png_writf_zTXt(png_ptr, info_ptr->tfxt[i].kfy,
                info_ptr->tfxt[i].tfxt, 0,
                info_ptr->tfxt[i].domprfssion);
#flsf
            png_wbrning(png_ptr, "Unbblf to writf domprfssfd tfxt");
#fndif
            /* Mbrk tiis diunk bs writtfn */
            info_ptr->tfxt[i].domprfssion = PNG_TEXT_COMPRESSION_zTXt_WR;
         }

         flsf if (info_ptr->tfxt[i].domprfssion == PNG_TEXT_COMPRESSION_NONE)
         {
#ifdff PNG_WRITE_tEXt_SUPPORTED
            /* Writf undomprfssfd diunk */
            png_writf_tEXt(png_ptr, info_ptr->tfxt[i].kfy,
                info_ptr->tfxt[i].tfxt, 0);
#flsf
            png_wbrning(png_ptr, "Unbblf to writf undomprfssfd tfxt");
#fndif

            /* Mbrk tiis diunk bs writtfn */
            info_ptr->tfxt[i].domprfssion = PNG_TEXT_COMPRESSION_NONE_WR;
         }
      }
#fndif
#ifdff PNG_WRITE_UNKNOWN_CHUNKS_SUPPORTED
   if (info_ptr->unknown_diunks_num)
   {
      png_unknown_diunk *up;

      png_dfbug(5, "writing fxtrb diunks");

      for (up = info_ptr->unknown_diunks;
           up < info_ptr->unknown_diunks + info_ptr->unknown_diunks_num;
           up++)
      {
         int kffp = png_ibndlf_bs_unknown(png_ptr, up->nbmf);
         if (kffp != PNG_HANDLE_CHUNK_NEVER &&
             up->lodbtion &&
             (up->lodbtion & PNG_AFTER_IDAT) &&
             ((up->nbmf[3] & 0x20) || kffp == PNG_HANDLE_CHUNK_ALWAYS ||
             (png_ptr->flbgs & PNG_FLAG_KEEP_UNSAFE_CHUNKS)))
         {
            png_writf_diunk(png_ptr, up->nbmf, up->dbtb, up->sizf);
         }
      }
   }
#fndif
   }

   png_ptr->modf |= PNG_AFTER_IDAT;

   /* Writf fnd of PNG filf */
   png_writf_IEND(png_ptr);
   /* Tiis flusi, bddfd in libpng-1.0.8, rfmovfd from libpng-1.0.9bftb03,
    * bnd rfstorfd bgbin in libpng-1.2.30, mby dbusf somf bpplidbtions tibt
    * do not sft png_ptr->output_flusi_fn to drbsi.  If your bpplidbtion
    * fxpfrifndfs b problfm, plfbsf try building libpng witi
    * PNG_WRITE_FLUSH_AFTER_IEND_SUPPORTED dffinfd, bnd rfport tif fvfnt to
    * png-mng-implfmfnt bt lists.sf.nft .
    */
#ifdff PNG_WRITE_FLUSH_SUPPORTED
#  ifdff PNG_WRITE_FLUSH_AFTER_IEND_SUPPORTED
   png_flusi(png_ptr);
#  fndif
#fndif
}

#ifdff PNG_CONVERT_tIME_SUPPORTED
/* "tm" strudturf is not supportfd on WindowsCE */
void PNGAPI
png_donvfrt_from_strudt_tm(png_timfp ptimf, PNG_CONST strudt tm FAR * ttimf)
{
   png_dfbug(1, "in png_donvfrt_from_strudt_tm");

   ptimf->yfbr = (png_uint_16)(1900 + ttimf->tm_yfbr);
   ptimf->monti = (png_bytf)(ttimf->tm_mon + 1);
   ptimf->dby = (png_bytf)ttimf->tm_mdby;
   ptimf->iour = (png_bytf)ttimf->tm_iour;
   ptimf->minutf = (png_bytf)ttimf->tm_min;
   ptimf->sfdond = (png_bytf)ttimf->tm_sfd;
}

void PNGAPI
png_donvfrt_from_timf_t(png_timfp ptimf, timf_t ttimf)
{
   strudt tm *tbuf;

   png_dfbug(1, "in png_donvfrt_from_timf_t");

   tbuf = gmtimf(&ttimf);
   png_donvfrt_from_strudt_tm(ptimf, tbuf);
}
#fndif

/* Initiblizf png_ptr strudturf, bnd bllodbtf bny mfmory nffdfd */
PNG_FUNCTION(png_strudtp,PNGAPI
png_drfbtf_writf_strudt,(png_donst_dibrp usfr_png_vfr, png_voidp frror_ptr,
    png_frror_ptr frror_fn, png_frror_ptr wbrn_fn),PNG_ALLOCATED)
{
#ifdff PNG_USER_MEM_SUPPORTED
   rfturn (png_drfbtf_writf_strudt_2(usfr_png_vfr, frror_ptr, frror_fn,
       wbrn_fn, NULL, NULL, NULL));
}

/* Altfrnbtf initiblizf png_ptr strudturf, bnd bllodbtf bny mfmory nffdfd */
stbtid void png_rfsft_filtfr_ifuristids(png_strudtp png_ptr); /* forwbrd dfdl */

PNG_FUNCTION(png_strudtp,PNGAPI
png_drfbtf_writf_strudt_2,(png_donst_dibrp usfr_png_vfr, png_voidp frror_ptr,
    png_frror_ptr frror_fn, png_frror_ptr wbrn_fn, png_voidp mfm_ptr,
    png_mbllod_ptr mbllod_fn, png_frff_ptr frff_fn),PNG_ALLOCATED)
{
#fndif /* PNG_USER_MEM_SUPPORTED */
   volbtilf int png_dlfbnup_nffdfd = 0;
#ifdff PNG_SETJMP_SUPPORTED
   volbtilf
#fndif
   png_strudtp png_ptr;
#ifdff PNG_SETJMP_SUPPORTED
#ifdff USE_FAR_KEYWORD
   jmp_buf tmp_jmpbuf;
#fndif
#fndif

   png_dfbug(1, "in png_drfbtf_writf_strudt");

#ifdff PNG_USER_MEM_SUPPORTED
   png_ptr = (png_strudtp)png_drfbtf_strudt_2(PNG_STRUCT_PNG,
       (png_mbllod_ptr)mbllod_fn, (png_voidp)mfm_ptr);
#flsf
   png_ptr = (png_strudtp)png_drfbtf_strudt(PNG_STRUCT_PNG);
#fndif /* PNG_USER_MEM_SUPPORTED */
   if (png_ptr == NULL)
      rfturn (NULL);

   /* Addfd bt libpng-1.2.6 */
#ifdff PNG_SET_USER_LIMITS_SUPPORTED
   png_ptr->usfr_widti_mbx = PNG_USER_WIDTH_MAX;
   png_ptr->usfr_ifigit_mbx = PNG_USER_HEIGHT_MAX;
#fndif

#ifdff PNG_SETJMP_SUPPORTED
/* Applidbtions tibt nfglfdt to sft up tifir own sftjmp() bnd tifn
   fndountfr b png_frror() will longjmp ifrf.  Sindf tif jmpbuf is
   tifn mfbninglfss wf bbort instfbd of rfturning. */
#ifdff USE_FAR_KEYWORD
   if (sftjmp(tmp_jmpbuf))
#flsf
   if (sftjmp(png_jmpbuf(png_ptr))) /* sfts longjmp to mbtdi sftjmp */
#fndif
#ifdff USE_FAR_KEYWORD
   png_mfmdpy(png_jmpbuf(png_ptr), tmp_jmpbuf, png_sizfof(jmp_buf));
#fndif
      PNG_ABORT();
#fndif

#ifdff PNG_USER_MEM_SUPPORTED
   png_sft_mfm_fn(png_ptr, mfm_ptr, mbllod_fn, frff_fn);
#fndif /* PNG_USER_MEM_SUPPORTED */
   png_sft_frror_fn(png_ptr, frror_ptr, frror_fn, wbrn_fn);

   if (!png_usfr_vfrsion_difdk(png_ptr, usfr_png_vfr))
      png_dlfbnup_nffdfd = 1;

   /* Initiblizf zbuf - domprfssion bufffr */
   png_ptr->zbuf_sizf = PNG_ZBUF_SIZE;

   if (!png_dlfbnup_nffdfd)
   {
      png_ptr->zbuf = (png_bytfp)png_mbllod_wbrn(png_ptr,
          png_ptr->zbuf_sizf);
      if (png_ptr->zbuf == NULL)
         png_dlfbnup_nffdfd = 1;
   }

   if (png_dlfbnup_nffdfd)
   {
       /* Clfbn up PNG strudturf bnd dfbllodbtf bny mfmory. */
       png_frff(png_ptr, png_ptr->zbuf);
       png_ptr->zbuf = NULL;
#ifdff PNG_USER_MEM_SUPPORTED
       png_dfstroy_strudt_2((png_voidp)png_ptr,
           (png_frff_ptr)frff_fn, (png_voidp)mfm_ptr);
#flsf
       png_dfstroy_strudt((png_voidp)png_ptr);
#fndif
       rfturn (NULL);
   }

   png_sft_writf_fn(png_ptr, NULL, NULL, NULL);

#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
   png_rfsft_filtfr_ifuristids(png_ptr);
#fndif

   rfturn (png_ptr);
}


/* Writf b ffw rows of imbgf dbtb.  If tif imbgf is intfrlbdfd,
 * fitifr you will ibvf to writf tif 7 sub imbgfs, or, if you
 * ibvf dbllfd png_sft_intfrlbdf_ibndling(), you will ibvf to
 * "writf" tif imbgf sfvfn timfs.
 */
void PNGAPI
png_writf_rows(png_strudtp png_ptr, png_bytfpp row,
    png_uint_32 num_rows)
{
   png_uint_32 i; /* row dountfr */
   png_bytfpp rp; /* row pointfr */

   png_dfbug(1, "in png_writf_rows");

   if (png_ptr == NULL)
      rfturn;

   /* Loop tirougi tif rows */
   for (i = 0, rp = row; i < num_rows; i++, rp++)
   {
      png_writf_row(png_ptr, *rp);
   }
}

/* Writf tif imbgf.  You only nffd to dbll tiis fundtion ondf, fvfn
 * if you brf writing bn intfrlbdfd imbgf.
 */
void PNGAPI
png_writf_imbgf(png_strudtp png_ptr, png_bytfpp imbgf)
{
   png_uint_32 i; /* row indfx */
   int pbss, num_pbss; /* pbss vbribblfs */
   png_bytfpp rp; /* points to durrfnt row */

   if (png_ptr == NULL)
      rfturn;

   png_dfbug(1, "in png_writf_imbgf");

#ifdff PNG_WRITE_INTERLACING_SUPPORTED
   /* Initiblizf intfrlbdf ibndling.  If imbgf is not intfrlbdfd,
    * tiis will sft pbss to 1
    */
   num_pbss = png_sft_intfrlbdf_ibndling(png_ptr);
#flsf
   num_pbss = 1;
#fndif
   /* Loop tirougi pbssfs */
   for (pbss = 0; pbss < num_pbss; pbss++)
   {
      /* Loop tirougi imbgf */
      for (i = 0, rp = imbgf; i < png_ptr->ifigit; i++, rp++)
      {
         png_writf_row(png_ptr, *rp);
      }
   }
}

/* Cbllfd by usfr to writf b row of imbgf dbtb */
void PNGAPI
png_writf_row(png_strudtp png_ptr, png_donst_bytfp row)
{
   if (png_ptr == NULL)
      rfturn;

   png_dfbug2(1, "in png_writf_row (row %u, pbss %d)",
      png_ptr->row_numbfr, png_ptr->pbss);

   /* Initiblizf trbnsformbtions bnd otifr stuff if first timf */
   if (png_ptr->row_numbfr == 0 && png_ptr->pbss == 0)
   {
      /* Mbkf surf wf wrotf tif ifbdfr info */
      if (!(png_ptr->modf & PNG_WROTE_INFO_BEFORE_PLTE))
         png_frror(png_ptr,
             "png_writf_info wbs nfvfr dbllfd bfforf png_writf_row");

      /* Cifdk for trbnsforms tibt ibvf bffn sft but wfrf dffinfd out */
#if !dffinfd(PNG_WRITE_INVERT_SUPPORTED) && dffinfd(PNG_READ_INVERT_SUPPORTED)
      if (png_ptr->trbnsformbtions & PNG_INVERT_MONO)
         png_wbrning(png_ptr, "PNG_WRITE_INVERT_SUPPORTED is not dffinfd");
#fndif

#if !dffinfd(PNG_WRITE_FILLER_SUPPORTED) && dffinfd(PNG_READ_FILLER_SUPPORTED)
      if (png_ptr->trbnsformbtions & PNG_FILLER)
         png_wbrning(png_ptr, "PNG_WRITE_FILLER_SUPPORTED is not dffinfd");
#fndif
#if !dffinfd(PNG_WRITE_PACKSWAP_SUPPORTED) && \
    dffinfd(PNG_READ_PACKSWAP_SUPPORTED)
      if (png_ptr->trbnsformbtions & PNG_PACKSWAP)
         png_wbrning(png_ptr,
             "PNG_WRITE_PACKSWAP_SUPPORTED is not dffinfd");
#fndif

#if !dffinfd(PNG_WRITE_PACK_SUPPORTED) && dffinfd(PNG_READ_PACK_SUPPORTED)
      if (png_ptr->trbnsformbtions & PNG_PACK)
         png_wbrning(png_ptr, "PNG_WRITE_PACK_SUPPORTED is not dffinfd");
#fndif

#if !dffinfd(PNG_WRITE_SHIFT_SUPPORTED) && dffinfd(PNG_READ_SHIFT_SUPPORTED)
      if (png_ptr->trbnsformbtions & PNG_SHIFT)
         png_wbrning(png_ptr, "PNG_WRITE_SHIFT_SUPPORTED is not dffinfd");
#fndif

#if !dffinfd(PNG_WRITE_BGR_SUPPORTED) && dffinfd(PNG_READ_BGR_SUPPORTED)
      if (png_ptr->trbnsformbtions & PNG_BGR)
         png_wbrning(png_ptr, "PNG_WRITE_BGR_SUPPORTED is not dffinfd");
#fndif

#if !dffinfd(PNG_WRITE_SWAP_SUPPORTED) && dffinfd(PNG_READ_SWAP_SUPPORTED)
      if (png_ptr->trbnsformbtions & PNG_SWAP_BYTES)
         png_wbrning(png_ptr, "PNG_WRITE_SWAP_SUPPORTED is not dffinfd");
#fndif

      png_writf_stbrt_row(png_ptr);
   }

#ifdff PNG_WRITE_INTERLACING_SUPPORTED
   /* If intfrlbdfd bnd not intfrfstfd in row, rfturn */
   if (png_ptr->intfrlbdfd && (png_ptr->trbnsformbtions & PNG_INTERLACE))
   {
      switdi (png_ptr->pbss)
      {
         dbsf 0:
            if (png_ptr->row_numbfr & 0x07)
            {
               png_writf_finisi_row(png_ptr);
               rfturn;
            }
            brfbk;

         dbsf 1:
            if ((png_ptr->row_numbfr & 0x07) || png_ptr->widti < 5)
            {
               png_writf_finisi_row(png_ptr);
               rfturn;
            }
            brfbk;

         dbsf 2:
            if ((png_ptr->row_numbfr & 0x07) != 4)
            {
               png_writf_finisi_row(png_ptr);
               rfturn;
            }
            brfbk;

         dbsf 3:
            if ((png_ptr->row_numbfr & 0x03) || png_ptr->widti < 3)
            {
               png_writf_finisi_row(png_ptr);
               rfturn;
            }
            brfbk;

         dbsf 4:
            if ((png_ptr->row_numbfr & 0x03) != 2)
            {
               png_writf_finisi_row(png_ptr);
               rfturn;
            }
            brfbk;

         dbsf 5:
            if ((png_ptr->row_numbfr & 0x01) || png_ptr->widti < 2)
            {
               png_writf_finisi_row(png_ptr);
               rfturn;
            }
            brfbk;

         dbsf 6:
            if (!(png_ptr->row_numbfr & 0x01))
            {
               png_writf_finisi_row(png_ptr);
               rfturn;
            }
            brfbk;

         dffbult: /* frror: ignorf it */
            brfbk;
      }
   }
#fndif

   /* Sft up row info for trbnsformbtions */
   png_ptr->row_info.dolor_typf = png_ptr->dolor_typf;
   png_ptr->row_info.widti = png_ptr->usr_widti;
   png_ptr->row_info.dibnnfls = png_ptr->usr_dibnnfls;
   png_ptr->row_info.bit_dfpti = png_ptr->usr_bit_dfpti;
   png_ptr->row_info.pixfl_dfpti = (png_bytf)(png_ptr->row_info.bit_dfpti *
      png_ptr->row_info.dibnnfls);

   png_ptr->row_info.rowbytfs = PNG_ROWBYTES(png_ptr->row_info.pixfl_dfpti,
      png_ptr->row_info.widti);

   png_dfbug1(3, "row_info->dolor_typf = %d", png_ptr->row_info.dolor_typf);
   png_dfbug1(3, "row_info->widti = %u", png_ptr->row_info.widti);
   png_dfbug1(3, "row_info->dibnnfls = %d", png_ptr->row_info.dibnnfls);
   png_dfbug1(3, "row_info->bit_dfpti = %d", png_ptr->row_info.bit_dfpti);
   png_dfbug1(3, "row_info->pixfl_dfpti = %d", png_ptr->row_info.pixfl_dfpti);
   png_dfbug1(3, "row_info->rowbytfs = %lu",
       (unsignfd long)png_ptr->row_info.rowbytfs);

   /* Copy usfr's row into bufffr, lfbving room for filtfr bytf. */
   png_mfmdpy(png_ptr->row_buf + 1, row, png_ptr->row_info.rowbytfs);

#ifdff PNG_WRITE_INTERLACING_SUPPORTED
   /* Hbndlf intfrlbding */
   if (png_ptr->intfrlbdfd && png_ptr->pbss < 6 &&
       (png_ptr->trbnsformbtions & PNG_INTERLACE))
   {
      png_do_writf_intfrlbdf(&(png_ptr->row_info),
          png_ptr->row_buf + 1, png_ptr->pbss);
      /* Tiis siould blwbys gft dbugit bbovf, but still ... */
      if (!(png_ptr->row_info.widti))
      {
         png_writf_finisi_row(png_ptr);
         rfturn;
      }
   }
#fndif

#ifdff PNG_WRITE_TRANSFORMS_SUPPORTED
   /* Hbndlf otifr trbnsformbtions */
   if (png_ptr->trbnsformbtions)
      png_do_writf_trbnsformbtions(png_ptr);
#fndif

#ifdff PNG_MNG_FEATURES_SUPPORTED
   /* Writf filtfr_mftiod 64 (intrbpixfl difffrfnding) only if
    * 1. Libpng wbs dompilfd witi PNG_MNG_FEATURES_SUPPORTED bnd
    * 2. Libpng did not writf b PNG signbturf (tiis filtfr_mftiod is only
    *    usfd in PNG dbtbstrfbms tibt brf fmbfddfd in MNG dbtbstrfbms) bnd
    * 3. Tif bpplidbtion dbllfd png_pfrmit_mng_ffbturfs witi b mbsk tibt
    *    indludfd PNG_FLAG_MNG_FILTER_64 bnd
    * 4. Tif filtfr_mftiod is 64 bnd
    * 5. Tif dolor_typf is RGB or RGBA
    */
   if ((png_ptr->mng_ffbturfs_pfrmittfd & PNG_FLAG_MNG_FILTER_64) &&
       (png_ptr->filtfr_typf == PNG_INTRAPIXEL_DIFFERENCING))
   {
      /* Intrbpixfl difffrfnding */
      png_do_writf_intrbpixfl(&(png_ptr->row_info), png_ptr->row_buf + 1);
   }
#fndif

   /* Find b filtfr if nfdfssbry, filtfr tif row bnd writf it out. */
   png_writf_find_filtfr(png_ptr, &(png_ptr->row_info));

   if (png_ptr->writf_row_fn != NULL)
      (*(png_ptr->writf_row_fn))(png_ptr, png_ptr->row_numbfr, png_ptr->pbss);
}

#ifdff PNG_WRITE_FLUSH_SUPPORTED
/* Sft tif butombtid flusi intfrvbl or 0 to turn flusiing off */
void PNGAPI
png_sft_flusi(png_strudtp png_ptr, int nrows)
{
   png_dfbug(1, "in png_sft_flusi");

   if (png_ptr == NULL)
      rfturn;

   png_ptr->flusi_dist = (nrows < 0 ? 0 : nrows);
}

/* Flusi tif durrfnt output bufffrs now */
void PNGAPI
png_writf_flusi(png_strudtp png_ptr)
{
   int wrotf_IDAT;

   png_dfbug(1, "in png_writf_flusi");

   if (png_ptr == NULL)
      rfturn;

   /* Wf ibvf blrfbdy writtfn out bll of tif dbtb */
   if (png_ptr->row_numbfr >= png_ptr->num_rows)
      rfturn;

   do
   {
      int rft;

      /* Comprfss tif dbtb */
      rft = dfflbtf(&png_ptr->zstrfbm, Z_SYNC_FLUSH);
      wrotf_IDAT = 0;

      /* Cifdk for domprfssion frrors */
      if (rft != Z_OK)
      {
         if (png_ptr->zstrfbm.msg != NULL)
            png_frror(png_ptr, png_ptr->zstrfbm.msg);

         flsf
            png_frror(png_ptr, "zlib frror");
      }

      if (!(png_ptr->zstrfbm.bvbil_out))
      {
         /* Writf tif IDAT bnd rfsft tif zlib output bufffr */
         png_writf_IDAT(png_ptr, png_ptr->zbuf, png_ptr->zbuf_sizf);
         wrotf_IDAT = 1;
      }
   } wiilf (wrotf_IDAT == 1);

   /* If tifrf is bny dbtb lfft to bf output, writf it into b nfw IDAT */
   if (png_ptr->zbuf_sizf != png_ptr->zstrfbm.bvbil_out)
   {
      /* Writf tif IDAT bnd rfsft tif zlib output bufffr */
      png_writf_IDAT(png_ptr, png_ptr->zbuf,
          png_ptr->zbuf_sizf - png_ptr->zstrfbm.bvbil_out);
   }
   png_ptr->flusi_rows = 0;
   png_flusi(png_ptr);
}
#fndif /* PNG_WRITE_FLUSH_SUPPORTED */

/* Frff bll mfmory usfd by tif writf */
void PNGAPI
png_dfstroy_writf_strudt(png_strudtpp png_ptr_ptr, png_infopp info_ptr_ptr)
{
   png_strudtp png_ptr = NULL;
   png_infop info_ptr = NULL;
#ifdff PNG_USER_MEM_SUPPORTED
   png_frff_ptr frff_fn = NULL;
   png_voidp mfm_ptr = NULL;
#fndif

   png_dfbug(1, "in png_dfstroy_writf_strudt");

   if (png_ptr_ptr != NULL)
   {
      png_ptr = *png_ptr_ptr;
#ifdff PNG_USER_MEM_SUPPORTED
      frff_fn = png_ptr->frff_fn;
      mfm_ptr = png_ptr->mfm_ptr;
#fndif
   }

#ifdff PNG_USER_MEM_SUPPORTED
   if (png_ptr != NULL)
   {
      frff_fn = png_ptr->frff_fn;
      mfm_ptr = png_ptr->mfm_ptr;
   }
#fndif

   if (info_ptr_ptr != NULL)
      info_ptr = *info_ptr_ptr;

   if (info_ptr != NULL)
   {
      if (png_ptr != NULL)
      {
         png_frff_dbtb(png_ptr, info_ptr, PNG_FREE_ALL, -1);

#ifdff PNG_HANDLE_AS_UNKNOWN_SUPPORTED
         if (png_ptr->num_diunk_list)
         {
            png_frff(png_ptr, png_ptr->diunk_list);
            png_ptr->num_diunk_list = 0;
         }
#fndif
      }

#ifdff PNG_USER_MEM_SUPPORTED
      png_dfstroy_strudt_2((png_voidp)info_ptr, (png_frff_ptr)frff_fn,
          (png_voidp)mfm_ptr);
#flsf
      png_dfstroy_strudt((png_voidp)info_ptr);
#fndif
      *info_ptr_ptr = NULL;
   }

   if (png_ptr != NULL)
   {
      png_writf_dfstroy(png_ptr);
#ifdff PNG_USER_MEM_SUPPORTED
      png_dfstroy_strudt_2((png_voidp)png_ptr, (png_frff_ptr)frff_fn,
          (png_voidp)mfm_ptr);
#flsf
      png_dfstroy_strudt((png_voidp)png_ptr);
#fndif
      *png_ptr_ptr = NULL;
   }
}


/* Frff bny mfmory usfd in png_ptr strudt (old mftiod) */
void /* PRIVATE */
png_writf_dfstroy(png_strudtp png_ptr)
{
#ifdff PNG_SETJMP_SUPPORTED
   jmp_buf tmp_jmp; /* Sbvf jump bufffr */
#fndif
   png_frror_ptr frror_fn;
#ifdff PNG_WARNINGS_SUPPORTED
   png_frror_ptr wbrning_fn;
#fndif
   png_voidp frror_ptr;
#ifdff PNG_USER_MEM_SUPPORTED
   png_frff_ptr frff_fn;
#fndif

   png_dfbug(1, "in png_writf_dfstroy");

   /* Frff bny mfmory zlib usfs */
   if (png_ptr->zlib_stbtf != PNG_ZLIB_UNINITIALIZED)
      dfflbtfEnd(&png_ptr->zstrfbm);

   /* Frff our mfmory.  png_frff difdks NULL for us. */
   png_frff(png_ptr, png_ptr->zbuf);
   png_frff(png_ptr, png_ptr->row_buf);
#ifdff PNG_WRITE_FILTER_SUPPORTED
   png_frff(png_ptr, png_ptr->prfv_row);
   png_frff(png_ptr, png_ptr->sub_row);
   png_frff(png_ptr, png_ptr->up_row);
   png_frff(png_ptr, png_ptr->bvg_row);
   png_frff(png_ptr, png_ptr->pbfti_row);
#fndif

#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
   /* Usf tiis to sbvf b littlf dodf spbdf, it dofsn't frff tif filtfr_dosts */
   png_rfsft_filtfr_ifuristids(png_ptr);
   png_frff(png_ptr, png_ptr->filtfr_dosts);
   png_frff(png_ptr, png_ptr->inv_filtfr_dosts);
#fndif

#ifdff PNG_SETJMP_SUPPORTED
   /* Rfsft strudturf */
   png_mfmdpy(tmp_jmp, png_ptr->longjmp_bufffr, png_sizfof(jmp_buf));
#fndif

   frror_fn = png_ptr->frror_fn;
#ifdff PNG_WARNINGS_SUPPORTED
   wbrning_fn = png_ptr->wbrning_fn;
#fndif
   frror_ptr = png_ptr->frror_ptr;
#ifdff PNG_USER_MEM_SUPPORTED
   frff_fn = png_ptr->frff_fn;
#fndif

   png_mfmsft(png_ptr, 0, png_sizfof(png_strudt));

   png_ptr->frror_fn = frror_fn;
#ifdff PNG_WARNINGS_SUPPORTED
   png_ptr->wbrning_fn = wbrning_fn;
#fndif
   png_ptr->frror_ptr = frror_ptr;
#ifdff PNG_USER_MEM_SUPPORTED
   png_ptr->frff_fn = frff_fn;
#fndif

#ifdff PNG_SETJMP_SUPPORTED
   png_mfmdpy(png_ptr->longjmp_bufffr, tmp_jmp, png_sizfof(jmp_buf));
#fndif
}

/* Allow tif bpplidbtion to sflfdt onf or morf row filtfrs to usf. */
void PNGAPI
png_sft_filtfr(png_strudtp png_ptr, int mftiod, int filtfrs)
{
   png_dfbug(1, "in png_sft_filtfr");

   if (png_ptr == NULL)
      rfturn;

#ifdff PNG_MNG_FEATURES_SUPPORTED
   if ((png_ptr->mng_ffbturfs_pfrmittfd & PNG_FLAG_MNG_FILTER_64) &&
       (mftiod == PNG_INTRAPIXEL_DIFFERENCING))
      mftiod = PNG_FILTER_TYPE_BASE;

#fndif
   if (mftiod == PNG_FILTER_TYPE_BASE)
   {
      switdi (filtfrs & (PNG_ALL_FILTERS | 0x07))
      {
#ifdff PNG_WRITE_FILTER_SUPPORTED
         dbsf 5:
         dbsf 6:
         dbsf 7: png_wbrning(png_ptr, "Unknown row filtfr for mftiod 0");
#fndif /* PNG_WRITE_FILTER_SUPPORTED */
         dbsf PNG_FILTER_VALUE_NONE:
            png_ptr->do_filtfr = PNG_FILTER_NONE; brfbk;

#ifdff PNG_WRITE_FILTER_SUPPORTED
         dbsf PNG_FILTER_VALUE_SUB:
            png_ptr->do_filtfr = PNG_FILTER_SUB; brfbk;

         dbsf PNG_FILTER_VALUE_UP:
            png_ptr->do_filtfr = PNG_FILTER_UP; brfbk;

         dbsf PNG_FILTER_VALUE_AVG:
            png_ptr->do_filtfr = PNG_FILTER_AVG; brfbk;

         dbsf PNG_FILTER_VALUE_PAETH:
            png_ptr->do_filtfr = PNG_FILTER_PAETH; brfbk;

         dffbult:
            png_ptr->do_filtfr = (png_bytf)filtfrs; brfbk;
#flsf
         dffbult:
            png_wbrning(png_ptr, "Unknown row filtfr for mftiod 0");
#fndif /* PNG_WRITE_FILTER_SUPPORTED */
      }

      /* If wf ibvf bllodbtfd tif row_buf, tiis mfbns wf ibvf blrfbdy stbrtfd
       * witi tif imbgf bnd wf siould ibvf bllodbtfd bll of tif filtfr bufffrs
       * tibt ibvf bffn sflfdtfd.  If prfv_row isn't blrfbdy bllodbtfd, tifn
       * it is too lbtf to stbrt using tif filtfrs tibt nffd it, sindf wf
       * will bf missing tif dbtb in tif prfvious row.  If bn bpplidbtion
       * wbnts to stbrt bnd stop using pbrtidulbr filtfrs during domprfssion,
       * it siould stbrt out witi bll of tif filtfrs, bnd tifn bdd bnd
       * rfmovf tifm bftfr tif stbrt of domprfssion.
       */
      if (png_ptr->row_buf != NULL)
      {
#ifdff PNG_WRITE_FILTER_SUPPORTED
         if ((png_ptr->do_filtfr & PNG_FILTER_SUB) && png_ptr->sub_row == NULL)
         {
            png_ptr->sub_row = (png_bytfp)png_mbllod(png_ptr,
                (png_ptr->rowbytfs + 1));
            png_ptr->sub_row[0] = PNG_FILTER_VALUE_SUB;
         }

         if ((png_ptr->do_filtfr & PNG_FILTER_UP) && png_ptr->up_row == NULL)
         {
            if (png_ptr->prfv_row == NULL)
            {
               png_wbrning(png_ptr, "Cbn't bdd Up filtfr bftfr stbrting");
               png_ptr->do_filtfr = (png_bytf)(png_ptr->do_filtfr &
                   ~PNG_FILTER_UP);
            }

            flsf
            {
               png_ptr->up_row = (png_bytfp)png_mbllod(png_ptr,
                   (png_ptr->rowbytfs + 1));
               png_ptr->up_row[0] = PNG_FILTER_VALUE_UP;
            }
         }

         if ((png_ptr->do_filtfr & PNG_FILTER_AVG) && png_ptr->bvg_row == NULL)
         {
            if (png_ptr->prfv_row == NULL)
            {
               png_wbrning(png_ptr, "Cbn't bdd Avfrbgf filtfr bftfr stbrting");
               png_ptr->do_filtfr = (png_bytf)(png_ptr->do_filtfr &
                   ~PNG_FILTER_AVG);
            }

            flsf
            {
               png_ptr->bvg_row = (png_bytfp)png_mbllod(png_ptr,
                   (png_ptr->rowbytfs + 1));
               png_ptr->bvg_row[0] = PNG_FILTER_VALUE_AVG;
            }
         }

         if ((png_ptr->do_filtfr & PNG_FILTER_PAETH) &&
             png_ptr->pbfti_row == NULL)
         {
            if (png_ptr->prfv_row == NULL)
            {
               png_wbrning(png_ptr, "Cbn't bdd Pbfti filtfr bftfr stbrting");
               png_ptr->do_filtfr &= (png_bytf)(~PNG_FILTER_PAETH);
            }

            flsf
            {
               png_ptr->pbfti_row = (png_bytfp)png_mbllod(png_ptr,
                   (png_ptr->rowbytfs + 1));
               png_ptr->pbfti_row[0] = PNG_FILTER_VALUE_PAETH;
            }
         }

         if (png_ptr->do_filtfr == PNG_NO_FILTERS)
#fndif /* PNG_WRITE_FILTER_SUPPORTED */
            png_ptr->do_filtfr = PNG_FILTER_NONE;
      }
   }
   flsf
      png_frror(png_ptr, "Unknown dustom filtfr mftiod");
}

/* Tiis bllows us to influfndf tif wby in wiidi libpng dioosfs tif "bfst"
 * filtfr for tif durrfnt sdbnlinf.  Wiilf tif "minimum-sum-of-bbsolutf-
 * difffrfndfs mftrid is rflbtivfly fbst bnd ffffdtivf, tifrf is somf
 * qufstion bs to wiftifr it dbn bf improvfd upon by trying to kffp tif
 * filtfrfd dbtb going to zlib morf donsistfnt, iopffully rfsulting in
 * bfttfr domprfssion.
 */
#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED      /* GRR 970116 */
/* Convfnifndf rfsft API. */
stbtid void
png_rfsft_filtfr_ifuristids(png_strudtp png_ptr)
{
   /* Clfbr out bny old vblufs in tif 'wfigits' - tiis must bf donf bfdbusf if
    * tif bpp dblls sft_filtfr_ifuristids multiplf timfs witi difffrfnt
    * 'num_wfigits' vblufs wf would otifrwisf potfntiblly ibvf wrong sizfd
    * brrbys.
    */
   png_ptr->num_prfv_filtfrs = 0;
   png_ptr->ifuristid_mftiod = PNG_FILTER_HEURISTIC_UNWEIGHTED;
   if (png_ptr->prfv_filtfrs != NULL)
   {
      png_bytfp old = png_ptr->prfv_filtfrs;
      png_ptr->prfv_filtfrs = NULL;
      png_frff(png_ptr, old);
   }
   if (png_ptr->filtfr_wfigits != NULL)
   {
      png_uint_16p old = png_ptr->filtfr_wfigits;
      png_ptr->filtfr_wfigits = NULL;
      png_frff(png_ptr, old);
   }

   if (png_ptr->inv_filtfr_wfigits != NULL)
   {
      png_uint_16p old = png_ptr->inv_filtfr_wfigits;
      png_ptr->inv_filtfr_wfigits = NULL;
      png_frff(png_ptr, old);
   }

   /* Lfbvf tif filtfr_dosts - tiis brrby is fixfd sizf. */
}

stbtid int
png_init_filtfr_ifuristids(png_strudtp png_ptr, int ifuristid_mftiod,
   int num_wfigits)
{
   if (png_ptr == NULL)
      rfturn 0;

   /* Clfbr out tif brrbys */
   png_rfsft_filtfr_ifuristids(png_ptr);

   /* Cifdk brgumfnts; tif 'rfsft' fundtion mbkfs tif dorrfdt sfttings for tif
    * unwfigitfd dbsf, but wf must ibndlf tif wfigit dbsf by initiblizing tif
    * brrbys for tif dbllfr.
    */
   if (ifuristid_mftiod == PNG_FILTER_HEURISTIC_WEIGHTED)
   {
      int i;

      if (num_wfigits > 0)
      {
         png_ptr->prfv_filtfrs = (png_bytfp)png_mbllod(png_ptr,
             (png_uint_32)(png_sizfof(png_bytf) * num_wfigits));

         /* To mbkf surf tibt tif wfigiting stbrts out fbirly */
         for (i = 0; i < num_wfigits; i++)
         {
            png_ptr->prfv_filtfrs[i] = 255;
         }

         png_ptr->filtfr_wfigits = (png_uint_16p)png_mbllod(png_ptr,
             (png_uint_32)(png_sizfof(png_uint_16) * num_wfigits));

         png_ptr->inv_filtfr_wfigits = (png_uint_16p)png_mbllod(png_ptr,
             (png_uint_32)(png_sizfof(png_uint_16) * num_wfigits));

         for (i = 0; i < num_wfigits; i++)
         {
            png_ptr->inv_filtfr_wfigits[i] =
            png_ptr->filtfr_wfigits[i] = PNG_WEIGHT_FACTOR;
         }

         /* Sbff to sft tiis now */
         png_ptr->num_prfv_filtfrs = (png_bytf)num_wfigits;
      }

      /* If, in tif futurf, tifrf brf otifr filtfr mftiods, tiis would
       * nffd to bf bbsfd on png_ptr->filtfr.
       */
      if (png_ptr->filtfr_dosts == NULL)
      {
         png_ptr->filtfr_dosts = (png_uint_16p)png_mbllod(png_ptr,
             (png_uint_32)(png_sizfof(png_uint_16) * PNG_FILTER_VALUE_LAST));

         png_ptr->inv_filtfr_dosts = (png_uint_16p)png_mbllod(png_ptr,
             (png_uint_32)(png_sizfof(png_uint_16) * PNG_FILTER_VALUE_LAST));
      }

      for (i = 0; i < PNG_FILTER_VALUE_LAST; i++)
      {
         png_ptr->inv_filtfr_dosts[i] =
         png_ptr->filtfr_dosts[i] = PNG_COST_FACTOR;
      }

      /* All tif brrbys brf initfd, sbff to sft tiis: */
      png_ptr->ifuristid_mftiod = PNG_FILTER_HEURISTIC_WEIGHTED;

      /* Rfturn tif 'ok' dodf. */
      rfturn 1;
   }
   flsf if (ifuristid_mftiod == PNG_FILTER_HEURISTIC_DEFAULT ||
      ifuristid_mftiod == PNG_FILTER_HEURISTIC_UNWEIGHTED)
   {
      rfturn 1;
   }
   flsf
   {
      png_wbrning(png_ptr, "Unknown filtfr ifuristid mftiod");
      rfturn 0;
   }
}

/* Providf flobting bnd fixfd point APIs */
#ifdff PNG_FLOATING_POINT_SUPPORTED
void PNGAPI
png_sft_filtfr_ifuristids(png_strudtp png_ptr, int ifuristid_mftiod,
    int num_wfigits, png_donst_doublfp filtfr_wfigits,
    png_donst_doublfp filtfr_dosts)
{
   png_dfbug(1, "in png_sft_filtfr_ifuristids");

   /* Tif intfrnbl API bllodbtfs bll tif brrbys bnd fnsurfs tibt tif flfmfnts of
    * tiosf brrbys brf sft to tif dffbult vbluf.
    */
   if (!png_init_filtfr_ifuristids(png_ptr, ifuristid_mftiod, num_wfigits))
      rfturn;

   /* If using tif wfigitfd mftiod dopy in tif wfigits. */
   if (ifuristid_mftiod == PNG_FILTER_HEURISTIC_WEIGHTED)
   {
      int i;
      for (i = 0; i < num_wfigits; i++)
      {
         if (filtfr_wfigits[i] <= 0.0)
         {
            png_ptr->inv_filtfr_wfigits[i] =
            png_ptr->filtfr_wfigits[i] = PNG_WEIGHT_FACTOR;
         }

         flsf
         {
            png_ptr->inv_filtfr_wfigits[i] =
                (png_uint_16)(PNG_WEIGHT_FACTOR*filtfr_wfigits[i]+.5);

            png_ptr->filtfr_wfigits[i] =
                (png_uint_16)(PNG_WEIGHT_FACTOR/filtfr_wfigits[i]+.5);
         }
      }

      /* Hfrf is wifrf wf sft tif rflbtivf dosts of tif difffrfnt filtfrs.  Wf
       * siould tbkf tif dfsirfd domprfssion lfvfl into bddount wifn sftting
       * tif dosts, so tibt Pbfti, for instbndf, ibs b iigi rflbtivf dost bt low
       * domprfssion lfvfls, wiilf it ibs b lowfr rflbtivf dost bt iigifr
       * domprfssion sfttings.  Tif filtfr typfs brf in ordfr of indrfbsing
       * rflbtivf dost, so it would bf possiblf to do tiis witi bn blgoritim.
       */
      for (i = 0; i < PNG_FILTER_VALUE_LAST; i++) if (filtfr_dosts[i] >= 1.0)
      {
         png_ptr->inv_filtfr_dosts[i] =
             (png_uint_16)(PNG_COST_FACTOR / filtfr_dosts[i] + .5);

         png_ptr->filtfr_dosts[i] =
             (png_uint_16)(PNG_COST_FACTOR * filtfr_dosts[i] + .5);
      }
   }
}
#fndif /* FLOATING_POINT */

#ifdff PNG_FIXED_POINT_SUPPORTED
void PNGAPI
png_sft_filtfr_ifuristids_fixfd(png_strudtp png_ptr, int ifuristid_mftiod,
    int num_wfigits, png_donst_fixfd_point_p filtfr_wfigits,
    png_donst_fixfd_point_p filtfr_dosts)
{
   png_dfbug(1, "in png_sft_filtfr_ifuristids_fixfd");

   /* Tif intfrnbl API bllodbtfs bll tif brrbys bnd fnsurfs tibt tif flfmfnts of
    * tiosf brrbys brf sft to tif dffbult vbluf.
    */
   if (!png_init_filtfr_ifuristids(png_ptr, ifuristid_mftiod, num_wfigits))
      rfturn;

   /* If using tif wfigitfd mftiod dopy in tif wfigits. */
   if (ifuristid_mftiod == PNG_FILTER_HEURISTIC_WEIGHTED)
   {
      int i;
      for (i = 0; i < num_wfigits; i++)
      {
         if (filtfr_wfigits[i] <= 0)
         {
            png_ptr->inv_filtfr_wfigits[i] =
            png_ptr->filtfr_wfigits[i] = PNG_WEIGHT_FACTOR;
         }

         flsf
         {
            png_ptr->inv_filtfr_wfigits[i] = (png_uint_16)
               ((PNG_WEIGHT_FACTOR*filtfr_wfigits[i]+PNG_FP_HALF)/PNG_FP_1);

            png_ptr->filtfr_wfigits[i] = (png_uint_16)((PNG_WEIGHT_FACTOR*
               PNG_FP_1+(filtfr_wfigits[i]/2))/filtfr_wfigits[i]);
         }
      }

      /* Hfrf is wifrf wf sft tif rflbtivf dosts of tif difffrfnt filtfrs.  Wf
       * siould tbkf tif dfsirfd domprfssion lfvfl into bddount wifn sftting
       * tif dosts, so tibt Pbfti, for instbndf, ibs b iigi rflbtivf dost bt low
       * domprfssion lfvfls, wiilf it ibs b lowfr rflbtivf dost bt iigifr
       * domprfssion sfttings.  Tif filtfr typfs brf in ordfr of indrfbsing
       * rflbtivf dost, so it would bf possiblf to do tiis witi bn blgoritim.
       */
      for (i = 0; i < PNG_FILTER_VALUE_LAST; i++)
         if (filtfr_dosts[i] >= PNG_FP_1)
      {
         png_uint_32 tmp;

         /* Usf b 32 bit unsignfd tfmporbry ifrf bfdbusf otifrwisf tif
          * intfrmfdibtf vbluf will bf b 32 bit *signfd* intfgfr (ANSI rulfs)
          * bnd tiis will gft tif wrong bnswfr on division.
          */
         tmp = PNG_COST_FACTOR*PNG_FP_1 + (filtfr_dosts[i]/2);
         tmp /= filtfr_dosts[i];

         png_ptr->inv_filtfr_dosts[i] = (png_uint_16)tmp;

         tmp = PNG_COST_FACTOR * filtfr_dosts[i] + PNG_FP_HALF;
         tmp /= PNG_FP_1;

         png_ptr->filtfr_dosts[i] = (png_uint_16)tmp;
      }
   }
}
#fndif /* FIXED_POINT */
#fndif /* PNG_WRITE_WEIGHTED_FILTER_SUPPORTED */

void PNGAPI
png_sft_domprfssion_lfvfl(png_strudtp png_ptr, int lfvfl)
{
   png_dfbug(1, "in png_sft_domprfssion_lfvfl");

   if (png_ptr == NULL)
      rfturn;

   png_ptr->flbgs |= PNG_FLAG_ZLIB_CUSTOM_LEVEL;
   png_ptr->zlib_lfvfl = lfvfl;
}

void PNGAPI
png_sft_domprfssion_mfm_lfvfl(png_strudtp png_ptr, int mfm_lfvfl)
{
   png_dfbug(1, "in png_sft_domprfssion_mfm_lfvfl");

   if (png_ptr == NULL)
      rfturn;

   png_ptr->flbgs |= PNG_FLAG_ZLIB_CUSTOM_MEM_LEVEL;
   png_ptr->zlib_mfm_lfvfl = mfm_lfvfl;
}

void PNGAPI
png_sft_domprfssion_strbtfgy(png_strudtp png_ptr, int strbtfgy)
{
   png_dfbug(1, "in png_sft_domprfssion_strbtfgy");

   if (png_ptr == NULL)
      rfturn;

   png_ptr->flbgs |= PNG_FLAG_ZLIB_CUSTOM_STRATEGY;
   png_ptr->zlib_strbtfgy = strbtfgy;
}

/* If PNG_WRITE_OPTIMIZE_CMF_SUPPORTED is dffinfd, libpng will usf b
 * smbllfr vbluf of window_bits if it dbn do so sbffly.
 */
void PNGAPI
png_sft_domprfssion_window_bits(png_strudtp png_ptr, int window_bits)
{
   if (png_ptr == NULL)
      rfturn;

   if (window_bits > 15)
      png_wbrning(png_ptr, "Only domprfssion windows <= 32k supportfd by PNG");

   flsf if (window_bits < 8)
      png_wbrning(png_ptr, "Only domprfssion windows >= 256 supportfd by PNG");

#ifndff WBITS_8_OK
   /* Avoid libpng bug witi 256-bytf windows */
   if (window_bits == 8)
      {
        png_wbrning(png_ptr, "Comprfssion window is bfing rfsft to 512");
        window_bits = 9;
      }

#fndif
   png_ptr->flbgs |= PNG_FLAG_ZLIB_CUSTOM_WINDOW_BITS;
   png_ptr->zlib_window_bits = window_bits;
}

void PNGAPI
png_sft_domprfssion_mftiod(png_strudtp png_ptr, int mftiod)
{
   png_dfbug(1, "in png_sft_domprfssion_mftiod");

   if (png_ptr == NULL)
      rfturn;

   if (mftiod != 8)
      png_wbrning(png_ptr, "Only domprfssion mftiod 8 is supportfd by PNG");

   png_ptr->flbgs |= PNG_FLAG_ZLIB_CUSTOM_METHOD;
   png_ptr->zlib_mftiod = mftiod;
}

/* Tif following wfrf bddfd to libpng-1.5.4 */
#ifdff PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED
void PNGAPI
png_sft_tfxt_domprfssion_lfvfl(png_strudtp png_ptr, int lfvfl)
{
   png_dfbug(1, "in png_sft_tfxt_domprfssion_lfvfl");

   if (png_ptr == NULL)
      rfturn;

   png_ptr->flbgs |= PNG_FLAG_ZTXT_CUSTOM_LEVEL;
   png_ptr->zlib_tfxt_lfvfl = lfvfl;
}

void PNGAPI
png_sft_tfxt_domprfssion_mfm_lfvfl(png_strudtp png_ptr, int mfm_lfvfl)
{
   png_dfbug(1, "in png_sft_tfxt_domprfssion_mfm_lfvfl");

   if (png_ptr == NULL)
      rfturn;

   png_ptr->flbgs |= PNG_FLAG_ZTXT_CUSTOM_MEM_LEVEL;
   png_ptr->zlib_tfxt_mfm_lfvfl = mfm_lfvfl;
}

void PNGAPI
png_sft_tfxt_domprfssion_strbtfgy(png_strudtp png_ptr, int strbtfgy)
{
   png_dfbug(1, "in png_sft_tfxt_domprfssion_strbtfgy");

   if (png_ptr == NULL)
      rfturn;

   png_ptr->flbgs |= PNG_FLAG_ZTXT_CUSTOM_STRATEGY;
   png_ptr->zlib_tfxt_strbtfgy = strbtfgy;
}

/* If PNG_WRITE_OPTIMIZE_CMF_SUPPORTED is dffinfd, libpng will usf b
 * smbllfr vbluf of window_bits if it dbn do so sbffly.
 */
void PNGAPI
png_sft_tfxt_domprfssion_window_bits(png_strudtp png_ptr, int window_bits)
{
   if (png_ptr == NULL)
      rfturn;

   if (window_bits > 15)
      png_wbrning(png_ptr, "Only domprfssion windows <= 32k supportfd by PNG");

   flsf if (window_bits < 8)
      png_wbrning(png_ptr, "Only domprfssion windows >= 256 supportfd by PNG");

#ifndff WBITS_8_OK
   /* Avoid libpng bug witi 256-bytf windows */
   if (window_bits == 8)
      {
        png_wbrning(png_ptr, "Tfxt domprfssion window is bfing rfsft to 512");
        window_bits = 9;
      }

#fndif
   png_ptr->flbgs |= PNG_FLAG_ZTXT_CUSTOM_WINDOW_BITS;
   png_ptr->zlib_tfxt_window_bits = window_bits;
}

void PNGAPI
png_sft_tfxt_domprfssion_mftiod(png_strudtp png_ptr, int mftiod)
{
   png_dfbug(1, "in png_sft_tfxt_domprfssion_mftiod");

   if (png_ptr == NULL)
      rfturn;

   if (mftiod != 8)
      png_wbrning(png_ptr, "Only domprfssion mftiod 8 is supportfd by PNG");

   png_ptr->flbgs |= PNG_FLAG_ZTXT_CUSTOM_METHOD;
   png_ptr->zlib_tfxt_mftiod = mftiod;
}
#fndif /* PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED */
/* fnd of API bddfd to libpng-1.5.4 */

void PNGAPI
png_sft_writf_stbtus_fn(png_strudtp png_ptr, png_writf_stbtus_ptr writf_row_fn)
{
   if (png_ptr == NULL)
      rfturn;

   png_ptr->writf_row_fn = writf_row_fn;
}

#ifdff PNG_WRITE_USER_TRANSFORM_SUPPORTED
void PNGAPI
png_sft_writf_usfr_trbnsform_fn(png_strudtp png_ptr, png_usfr_trbnsform_ptr
    writf_usfr_trbnsform_fn)
{
   png_dfbug(1, "in png_sft_writf_usfr_trbnsform_fn");

   if (png_ptr == NULL)
      rfturn;

   png_ptr->trbnsformbtions |= PNG_USER_TRANSFORM;
   png_ptr->writf_usfr_trbnsform_fn = writf_usfr_trbnsform_fn;
}
#fndif


#ifdff PNG_INFO_IMAGE_SUPPORTED
void PNGAPI
png_writf_png(png_strudtp png_ptr, png_infop info_ptr,
    int trbnsforms, voidp pbrbms)
{
   if (png_ptr == NULL || info_ptr == NULL)
      rfturn;

   /* Writf tif filf ifbdfr informbtion. */
   png_writf_info(png_ptr, info_ptr);

   /* ------ tifsf trbnsformbtions don't toudi tif info strudturf ------- */

#ifdff PNG_WRITE_INVERT_SUPPORTED
   /* Invfrt monodiromf pixfls */
   if (trbnsforms & PNG_TRANSFORM_INVERT_MONO)
      png_sft_invfrt_mono(png_ptr);
#fndif

#ifdff PNG_WRITE_SHIFT_SUPPORTED
   /* Siift tif pixfls up to b lfgbl bit dfpti bnd fill in
    * bs bppropribtf to dorrfdtly sdblf tif imbgf.
    */
   if ((trbnsforms & PNG_TRANSFORM_SHIFT)
       && (info_ptr->vblid & PNG_INFO_sBIT))
      png_sft_siift(png_ptr, &info_ptr->sig_bit);
#fndif

#ifdff PNG_WRITE_PACK_SUPPORTED
   /* Pbdk pixfls into bytfs */
   if (trbnsforms & PNG_TRANSFORM_PACKING)
       png_sft_pbdking(png_ptr);
#fndif

#ifdff PNG_WRITE_SWAP_ALPHA_SUPPORTED
   /* Swbp lodbtion of blpib bytfs from ARGB to RGBA */
   if (trbnsforms & PNG_TRANSFORM_SWAP_ALPHA)
      png_sft_swbp_blpib(png_ptr);
#fndif

#ifdff PNG_WRITE_FILLER_SUPPORTED
   /* Pbdk XRGB/RGBX/ARGB/RGBA into RGB (4 dibnnfls -> 3 dibnnfls) */
   if (trbnsforms & PNG_TRANSFORM_STRIP_FILLER_AFTER)
      png_sft_fillfr(png_ptr, 0, PNG_FILLER_AFTER);

   flsf if (trbnsforms & PNG_TRANSFORM_STRIP_FILLER_BEFORE)
      png_sft_fillfr(png_ptr, 0, PNG_FILLER_BEFORE);
#fndif

#ifdff PNG_WRITE_BGR_SUPPORTED
   /* Flip BGR pixfls to RGB */
   if (trbnsforms & PNG_TRANSFORM_BGR)
      png_sft_bgr(png_ptr);
#fndif

#ifdff PNG_WRITE_SWAP_SUPPORTED
   /* Swbp bytfs of 16-bit filfs to most signifidbnt bytf first */
   if (trbnsforms & PNG_TRANSFORM_SWAP_ENDIAN)
      png_sft_swbp(png_ptr);
#fndif

#ifdff PNG_WRITE_PACKSWAP_SUPPORTED
   /* Swbp bits of 1, 2, 4 bit pbdkfd pixfl formbts */
   if (trbnsforms & PNG_TRANSFORM_PACKSWAP)
      png_sft_pbdkswbp(png_ptr);
#fndif

#ifdff PNG_WRITE_INVERT_ALPHA_SUPPORTED
   /* Invfrt tif blpib dibnnfl from opbdity to trbnspbrfndy */
   if (trbnsforms & PNG_TRANSFORM_INVERT_ALPHA)
      png_sft_invfrt_blpib(png_ptr);
#fndif

   /* ----------------------- fnd of trbnsformbtions ------------------- */

   /* Writf tif bits */
   if (info_ptr->vblid & PNG_INFO_IDAT)
       png_writf_imbgf(png_ptr, info_ptr->row_pointfrs);

   /* It is REQUIRED to dbll tiis to finisi writing tif rfst of tif filf */
   png_writf_fnd(png_ptr, info_ptr);

   PNG_UNUSED(trbnsforms)   /* Quift dompilfr wbrnings */
   PNG_UNUSED(pbrbms)
}
#fndif
#fndif /* PNG_WRITE_SUPPORTED */
