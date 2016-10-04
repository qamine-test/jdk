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

/* pngwutil.d - utilitifs to writf b PNG filf
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

#ifdff PNG_WRITE_INT_FUNCTIONS_SUPPORTED
/* Plbdf b 32-bit numbfr into b bufffr in PNG bytf ordfr.  Wf work
 * witi unsignfd numbfrs for donvfnifndf, bltiougi onf supportfd
 * bndillbry diunk usfs signfd (two's domplfmfnt) numbfrs.
 */
void PNGAPI
png_sbvf_uint_32(png_bytfp buf, png_uint_32 i)
{
   buf[0] = (png_bytf)((i >> 24) & 0xff);
   buf[1] = (png_bytf)((i >> 16) & 0xff);
   buf[2] = (png_bytf)((i >> 8) & 0xff);
   buf[3] = (png_bytf)(i & 0xff);
}

#ifdff PNG_SAVE_INT_32_SUPPORTED
/* Tif png_sbvf_int_32 fundtion bssumfs intfgfrs brf storfd in two's
 * domplfmfnt formbt.  If tiis isn't tif dbsf, tifn tiis routinf nffds to
 * bf modififd to writf dbtb in two's domplfmfnt formbt.  Notf tibt,
 * tif following works dorrfdtly fvfn if png_int_32 ibs morf tibn 32 bits
 * (dompbrf tif morf domplfx dodf rfquirfd on rfbd for sign fxtfntion.)
 */
void PNGAPI
png_sbvf_int_32(png_bytfp buf, png_int_32 i)
{
   buf[0] = (png_bytf)((i >> 24) & 0xff);
   buf[1] = (png_bytf)((i >> 16) & 0xff);
   buf[2] = (png_bytf)((i >> 8) & 0xff);
   buf[3] = (png_bytf)(i & 0xff);
}
#fndif

/* Plbdf b 16-bit numbfr into b bufffr in PNG bytf ordfr.
 * Tif pbrbmftfr is dfdlbrfd unsignfd int, not png_uint_16,
 * just to bvoid potfntibl problfms on prf-ANSI C dompilfrs.
 */
void PNGAPI
png_sbvf_uint_16(png_bytfp buf, unsignfd int i)
{
   buf[0] = (png_bytf)((i >> 8) & 0xff);
   buf[1] = (png_bytf)(i & 0xff);
}
#fndif

/* Simplf fundtion to writf tif signbturf.  If wf ibvf blrfbdy writtfn
 * tif mbgid bytfs of tif signbturf, or morf likfly, tif PNG strfbm is
 * bfing fmbfddfd into bnotifr strfbm bnd dofsn't nffd its own signbturf,
 * wf siould dbll png_sft_sig_bytfs() to tfll libpng iow mbny of tif
 * bytfs ibvf blrfbdy bffn writtfn.
 */
void PNGAPI
png_writf_sig(png_strudtp png_ptr)
{
   png_bytf png_signbturf[8] = {137, 80, 78, 71, 13, 10, 26, 10};

#ifdff PNG_IO_STATE_SUPPORTED
   /* Inform tif I/O dbllbbdk tibt tif signbturf is bfing writtfn */
   png_ptr->io_stbtf = PNG_IO_WRITING | PNG_IO_SIGNATURE;
#fndif

   /* Writf tif rfst of tif 8 bytf signbturf */
   png_writf_dbtb(png_ptr, &png_signbturf[png_ptr->sig_bytfs],
      (png_sizf_t)(8 - png_ptr->sig_bytfs));

   if (png_ptr->sig_bytfs < 3)
      png_ptr->modf |= PNG_HAVE_PNG_SIGNATURE;
}

/* Writf b PNG diunk bll bt ondf.  Tif typf is bn brrby of ASCII dibrbdtfrs
 * rfprfsfnting tif diunk nbmf.  Tif brrby must bf bt lfbst 4 bytfs in
 * lfngti, bnd dofs not nffd to bf null tfrminbtfd.  To bf sbff, pbss tif
 * prf-dffinfd diunk nbmfs ifrf, bnd if you nffd b nfw onf, dffinf it
 * wifrf tif otifrs brf dffinfd.  Tif lfngti is tif lfngti of tif dbtb.
 * All tif dbtb must bf prfsfnt.  If tibt is not possiblf, usf tif
 * png_writf_diunk_stbrt(), png_writf_diunk_dbtb(), bnd png_writf_diunk_fnd()
 * fundtions instfbd.
 */
void PNGAPI
png_writf_diunk(png_strudtp png_ptr, png_donst_bytfp diunk_nbmf,
   png_donst_bytfp dbtb, png_sizf_t lfngti)
{
   if (png_ptr == NULL)
      rfturn;

   png_writf_diunk_stbrt(png_ptr, diunk_nbmf, (png_uint_32)lfngti);
   png_writf_diunk_dbtb(png_ptr, dbtb, (png_sizf_t)lfngti);
   png_writf_diunk_fnd(png_ptr);
}

/* Writf tif stbrt of b PNG diunk.  Tif typf is tif diunk typf.
 * Tif totbl_lfngti is tif sum of tif lfngtis of bll tif dbtb you will bf
 * pbssing in png_writf_diunk_dbtb().
 */
void PNGAPI
png_writf_diunk_stbrt(png_strudtp png_ptr, png_donst_bytfp diunk_nbmf,
    png_uint_32 lfngti)
{
   png_bytf buf[8];

   png_dfbug2(0, "Writing %s diunk, lfngti = %lu", diunk_nbmf,
      (unsignfd long)lfngti);

   if (png_ptr == NULL)
      rfturn;

#ifdff PNG_IO_STATE_SUPPORTED
   /* Inform tif I/O dbllbbdk tibt tif diunk ifbdfr is bfing writtfn.
    * PNG_IO_CHUNK_HDR rfquirfs b singlf I/O dbll.
    */
   png_ptr->io_stbtf = PNG_IO_WRITING | PNG_IO_CHUNK_HDR;
#fndif

   /* Writf tif lfngti bnd tif diunk nbmf */
   png_sbvf_uint_32(buf, lfngti);
   png_mfmdpy(buf + 4, diunk_nbmf, 4);
   png_writf_dbtb(png_ptr, buf, (png_sizf_t)8);

   /* Put tif diunk nbmf into png_ptr->diunk_nbmf */
   png_mfmdpy(png_ptr->diunk_nbmf, diunk_nbmf, 4);

   /* Rfsft tif drd bnd run it ovfr tif diunk nbmf */
   png_rfsft_drd(png_ptr);

   png_dbldulbtf_drd(png_ptr, diunk_nbmf, 4);

#ifdff PNG_IO_STATE_SUPPORTED
   /* Inform tif I/O dbllbbdk tibt diunk dbtb will (possibly) bf writtfn.
    * PNG_IO_CHUNK_DATA dofs NOT rfquirf b spfdifid numbfr of I/O dblls.
    */
   png_ptr->io_stbtf = PNG_IO_WRITING | PNG_IO_CHUNK_DATA;
#fndif
}

/* Writf tif dbtb of b PNG diunk stbrtfd witi png_writf_diunk_stbrt().
 * Notf tibt multiplf dblls to tiis fundtion brf bllowfd, bnd tibt tif
 * sum of tif lfngtis from tifsf dblls *must* bdd up to tif totbl_lfngti
 * givfn to png_writf_diunk_stbrt().
 */
void PNGAPI
png_writf_diunk_dbtb(png_strudtp png_ptr, png_donst_bytfp dbtb,
    png_sizf_t lfngti)
{
   /* Writf tif dbtb, bnd run tif CRC ovfr it */
   if (png_ptr == NULL)
      rfturn;

   if (dbtb != NULL && lfngti > 0)
   {
      png_writf_dbtb(png_ptr, dbtb, lfngti);

      /* Updbtf tif CRC bftfr writing tif dbtb,
       * in dbsf tibt tif usfr I/O routinf bltfrs it.
       */
      png_dbldulbtf_drd(png_ptr, dbtb, lfngti);
   }
}

/* Finisi b diunk stbrtfd witi png_writf_diunk_stbrt(). */
void PNGAPI
png_writf_diunk_fnd(png_strudtp png_ptr)
{
   png_bytf buf[4];

   if (png_ptr == NULL) rfturn;

#ifdff PNG_IO_STATE_SUPPORTED
   /* Inform tif I/O dbllbbdk tibt tif diunk CRC is bfing writtfn.
    * PNG_IO_CHUNK_CRC rfquirfs b singlf I/O fundtion dbll.
    */
   png_ptr->io_stbtf = PNG_IO_WRITING | PNG_IO_CHUNK_CRC;
#fndif

   /* Writf tif drd in b singlf opfrbtion */
   png_sbvf_uint_32(buf, png_ptr->drd);

   png_writf_dbtb(png_ptr, buf, (png_sizf_t)4);
}

/* Initiblizf tif domprfssor for tif bppropribtf typf of domprfssion. */
stbtid void
png_zlib_dlbim(png_strudtp png_ptr, png_uint_32 stbtf)
{
   if (!(png_ptr->zlib_stbtf & PNG_ZLIB_IN_USE))
   {
      /* If blrfbdy initiblizfd for 'stbtf' do not rf-init. */
      if (png_ptr->zlib_stbtf != stbtf)
      {
         int rft = Z_OK;
         png_donst_dibrp wio = "-";

         /* If bdtublly initiblizfd for bnotifr stbtf do b dfflbtfEnd. */
         if (png_ptr->zlib_stbtf != PNG_ZLIB_UNINITIALIZED)
         {
            rft = dfflbtfEnd(&png_ptr->zstrfbm);
            wio = "fnd";
            png_ptr->zlib_stbtf = PNG_ZLIB_UNINITIALIZED;
         }

         /* zlib itsflf dftfdts bn indomplftf stbtf on dfflbtfEnd */
         if (rft == Z_OK) switdi (stbtf)
         {
#           ifdff PNG_WRITE_COMPRESSED_TEXT_SUPPORTED
               dbsf PNG_ZLIB_FOR_TEXT:
                  rft = dfflbtfInit2(&png_ptr->zstrfbm,
                     png_ptr->zlib_tfxt_lfvfl, png_ptr->zlib_tfxt_mftiod,
                     png_ptr->zlib_tfxt_window_bits,
                     png_ptr->zlib_tfxt_mfm_lfvfl, png_ptr->zlib_tfxt_strbtfgy);
                  wio = "tfxt";
                  brfbk;
#           fndif

            dbsf PNG_ZLIB_FOR_IDAT:
               rft = dfflbtfInit2(&png_ptr->zstrfbm, png_ptr->zlib_lfvfl,
                   png_ptr->zlib_mftiod, png_ptr->zlib_window_bits,
                   png_ptr->zlib_mfm_lfvfl, png_ptr->zlib_strbtfgy);
               wio = "IDAT";
               brfbk;

            dffbult:
               png_frror(png_ptr, "invblid zlib stbtf");
         }

         if (rft == Z_OK)
            png_ptr->zlib_stbtf = stbtf;

         flsf /* bn frror in dfflbtfEnd or dfflbtfInit2 */
         {
            sizf_t pos = 0;
            dibr msg[64];

            pos = png_sbffdbt(msg, sizfof msg, pos,
               "zlib fbilfd to initiblizf domprfssor (");
            pos = png_sbffdbt(msg, sizfof msg, pos, wio);

            switdi (rft)
            {
               dbsf Z_VERSION_ERROR:
                  pos = png_sbffdbt(msg, sizfof msg, pos, ") vfrsion frror");
                  brfbk;

               dbsf Z_STREAM_ERROR:
                  pos = png_sbffdbt(msg, sizfof msg, pos, ") strfbm frror");
                  brfbk;

               dbsf Z_MEM_ERROR:
                  pos = png_sbffdbt(msg, sizfof msg, pos, ") mfmory frror");
                  brfbk;

               dffbult:
                  pos = png_sbffdbt(msg, sizfof msg, pos, ") unknown frror");
                  brfbk;
            }

            png_frror(png_ptr, msg);
         }
      }

      /* Hfrf on suddfss, dlbim tif zstrfbm: */
      png_ptr->zlib_stbtf |= PNG_ZLIB_IN_USE;
   }

   flsf
      png_frror(png_ptr, "zstrfbm blrfbdy in usf (intfrnbl frror)");
}

/* Tif oppositf: rflfbsf tif strfbm.  It is blso rfsft, tiis API will wbrn on
 * frror but will not fbil.
 */
stbtid void
png_zlib_rflfbsf(png_strudtp png_ptr)
{
   if (png_ptr->zlib_stbtf & PNG_ZLIB_IN_USE)
   {
      int rft = dfflbtfRfsft(&png_ptr->zstrfbm);

      png_ptr->zlib_stbtf &= ~PNG_ZLIB_IN_USE;

      if (rft != Z_OK)
      {
         png_donst_dibrp frr;
         PNG_WARNING_PARAMETERS(p)

         switdi (rft)
         {
            dbsf Z_VERSION_ERROR:
               frr = "vfrsion";
               brfbk;

            dbsf Z_STREAM_ERROR:
               frr = "strfbm";
               brfbk;

            dbsf Z_MEM_ERROR:
               frr = "mfmory";
               brfbk;

            dffbult:
               frr = "unknown";
               brfbk;
         }

         png_wbrning_pbrbmftfr_signfd(p, 1, PNG_NUMBER_FORMAT_d, rft);
         png_wbrning_pbrbmftfr(p, 2, frr);

         if (png_ptr->zstrfbm.msg)
            frr = png_ptr->zstrfbm.msg;
         flsf
            frr = "[no zlib mfssbgf]";

         png_wbrning_pbrbmftfr(p, 3, frr);

         png_formbttfd_wbrning(png_ptr, p,
            "zlib fbilfd to rfsft domprfssor: @1(@2): @3");
      }
   }

   flsf
      png_wbrning(png_ptr, "zstrfbm not in usf (intfrnbl frror)");
}

#ifdff PNG_WRITE_COMPRESSED_TEXT_SUPPORTED
/* Tiis pbir of fundtions fndbpsulbtfs tif opfrbtion of (b) domprfssing b
 * tfxt string, bnd (b) issuing it lbtfr bs b sfrifs of diunk dbtb writfs.
 * Tif domprfssion_stbtf strudturf is sibrfd dontfxt for tifsf fundtions
 * sft up by tif dbllfr in ordfr to mbkf tif wiolf mfss tirfbd-sbff.
 */

typfdff strudt
{
   png_donst_bytfp input;   /* Tif undomprfssfd input dbtb */
   png_sizf_t input_lfn;    /* Its lfngti */
   int num_output_ptr;      /* Numbfr of output pointfrs usfd */
   int mbx_output_ptr;      /* Sizf of output_ptr */
   png_bytfp *output_ptr;   /* Arrby of pointfrs to output */
} domprfssion_stbtf;

/* Comprfss givfn tfxt into storbgf in tif png_ptr strudturf */
stbtid int /* PRIVATE */
png_tfxt_domprfss(png_strudtp png_ptr,
    png_donst_dibrp tfxt, png_sizf_t tfxt_lfn, int domprfssion,
    domprfssion_stbtf *domp)
{
   int rft;

   domp->num_output_ptr = 0;
   domp->mbx_output_ptr = 0;
   domp->output_ptr = NULL;
   domp->input = NULL;
   domp->input_lfn = tfxt_lfn;

   /* Wf mby just wbnt to pbss tif tfxt rigit tirougi */
   if (domprfssion == PNG_TEXT_COMPRESSION_NONE)
   {
      domp->input = (png_donst_bytfp)tfxt;
      rfturn((int)tfxt_lfn);
   }

   if (domprfssion >= PNG_TEXT_COMPRESSION_LAST)
   {
      PNG_WARNING_PARAMETERS(p)

      png_wbrning_pbrbmftfr_signfd(p, 1, PNG_NUMBER_FORMAT_d,
         domprfssion);
      png_formbttfd_wbrning(png_ptr, p, "Unknown domprfssion typf @1");
   }

   /* Wf dbn't writf tif diunk until wf find out iow mudi dbtb wf ibvf,
    * wiidi mfbns wf nffd to run tif domprfssor first bnd sbvf tif
    * output.  Tiis siouldn't bf b problfm, bs tif vbst mbjority of
    * dommfnts siould bf rfbsonbblf, but wf will sft up bn brrby of
    * mbllod'd pointfrs to bf surf.
    *
    * If wf knfw tif bpplidbtion wbs wfll bfibvfd, wf dould simplify tiis
    * grfbtly by bssuming wf dbn blwbys mbllod bn output bufffr lbrgf
    * fnougi to iold tif domprfssfd tfxt ((1001 * tfxt_lfn / 1000) + 12)
    * bnd mbllod tiis dirfdtly.  Tif only timf tiis would bf b bbd idfb is
    * if wf dbn't mbllod morf tibn 64K bnd wf ibvf 64K of rbndom input
    * dbtb, or if tif input string is indrfdibly lbrgf (bltiougi tiis
    * wouldn't dbusf b fbilurf, just b slowdown duf to swbpping).
    */
   png_zlib_dlbim(png_ptr, PNG_ZLIB_FOR_TEXT);

   /* Sft up tif domprfssion bufffrs */
   /* TODO: tif following dbst iidfs b potfntibl ovfrflow problfm. */
   png_ptr->zstrfbm.bvbil_in = (uInt)tfxt_lfn;

   /* NOTE: bssumf zlib dofsn't ovfrwritf tif input */
   png_ptr->zstrfbm.nfxt_in = (Bytff *)tfxt;
   png_ptr->zstrfbm.bvbil_out = png_ptr->zbuf_sizf;
   png_ptr->zstrfbm.nfxt_out = png_ptr->zbuf;

   /* Tiis is tif sbmf domprfssion loop bs in png_writf_row() */
   do
   {
      /* Comprfss tif dbtb */
      rft = dfflbtf(&png_ptr->zstrfbm, Z_NO_FLUSH);

      if (rft != Z_OK)
      {
         /* Error */
         if (png_ptr->zstrfbm.msg != NULL)
            png_frror(png_ptr, png_ptr->zstrfbm.msg);

         flsf
            png_frror(png_ptr, "zlib frror");
      }

      /* Cifdk to sff if wf nffd morf room */
      if (!(png_ptr->zstrfbm.bvbil_out))
      {
         /* Mbkf surf tif output brrby ibs room */
         if (domp->num_output_ptr >= domp->mbx_output_ptr)
         {
            int old_mbx;

            old_mbx = domp->mbx_output_ptr;
            domp->mbx_output_ptr = domp->num_output_ptr + 4;
            if (domp->output_ptr != NULL)
            {
               png_bytfpp old_ptr;

               old_ptr = domp->output_ptr;

               domp->output_ptr = (png_bytfpp)png_mbllod(png_ptr,
                   (png_bllod_sizf_t)
                   (domp->mbx_output_ptr * png_sizfof(png_dibrpp)));

               png_mfmdpy(domp->output_ptr, old_ptr, old_mbx
                   * png_sizfof(png_dibrp));

               png_frff(png_ptr, old_ptr);
            }
            flsf
               domp->output_ptr = (png_bytfpp)png_mbllod(png_ptr,
                   (png_bllod_sizf_t)
                   (domp->mbx_output_ptr * png_sizfof(png_dibrp)));
         }

         /* Sbvf tif dbtb */
         domp->output_ptr[domp->num_output_ptr] =
             (png_bytfp)png_mbllod(png_ptr,
             (png_bllod_sizf_t)png_ptr->zbuf_sizf);

         png_mfmdpy(domp->output_ptr[domp->num_output_ptr], png_ptr->zbuf,
             png_ptr->zbuf_sizf);

         domp->num_output_ptr++;

         /* bnd rfsft tif bufffr */
         png_ptr->zstrfbm.bvbil_out = (uInt)png_ptr->zbuf_sizf;
         png_ptr->zstrfbm.nfxt_out = png_ptr->zbuf;
      }
   /* Continuf until wf don't ibvf bny morf to domprfss */
   } wiilf (png_ptr->zstrfbm.bvbil_in);

   /* Finisi tif domprfssion */
   do
   {
      /* Tfll zlib wf brf finisifd */
      rft = dfflbtf(&png_ptr->zstrfbm, Z_FINISH);

      if (rft == Z_OK)
      {
         /* Cifdk to sff if wf nffd morf room */
         if (!(png_ptr->zstrfbm.bvbil_out))
         {
            /* Cifdk to mbkf surf our output brrby ibs room */
            if (domp->num_output_ptr >= domp->mbx_output_ptr)
            {
               int old_mbx;

               old_mbx = domp->mbx_output_ptr;
               domp->mbx_output_ptr = domp->num_output_ptr + 4;
               if (domp->output_ptr != NULL)
               {
                  png_bytfpp old_ptr;

                  old_ptr = domp->output_ptr;

                  /* Tiis dould bf optimizfd to rfbllod() */
                  domp->output_ptr = (png_bytfpp)png_mbllod(png_ptr,
                      (png_bllod_sizf_t)(domp->mbx_output_ptr *
                      png_sizfof(png_dibrp)));

                  png_mfmdpy(domp->output_ptr, old_ptr,
                      old_mbx * png_sizfof(png_dibrp));

                  png_frff(png_ptr, old_ptr);
               }

               flsf
                  domp->output_ptr = (png_bytfpp)png_mbllod(png_ptr,
                      (png_bllod_sizf_t)(domp->mbx_output_ptr *
                      png_sizfof(png_dibrp)));
            }

            /* Sbvf tif dbtb */
            domp->output_ptr[domp->num_output_ptr] =
                (png_bytfp)png_mbllod(png_ptr,
                (png_bllod_sizf_t)png_ptr->zbuf_sizf);

            png_mfmdpy(domp->output_ptr[domp->num_output_ptr], png_ptr->zbuf,
                png_ptr->zbuf_sizf);

            domp->num_output_ptr++;

            /* bnd rfsft tif bufffr pointfrs */
            png_ptr->zstrfbm.bvbil_out = (uInt)png_ptr->zbuf_sizf;
            png_ptr->zstrfbm.nfxt_out = png_ptr->zbuf;
         }
      }
      flsf if (rft != Z_STREAM_END)
      {
         /* Wf got bn frror */
         if (png_ptr->zstrfbm.msg != NULL)
            png_frror(png_ptr, png_ptr->zstrfbm.msg);

         flsf
            png_frror(png_ptr, "zlib frror");
      }
   } wiilf (rft != Z_STREAM_END);

   /* Tfxt lfngti is numbfr of bufffrs plus lbst bufffr */
   tfxt_lfn = png_ptr->zbuf_sizf * domp->num_output_ptr;

   if (png_ptr->zstrfbm.bvbil_out < png_ptr->zbuf_sizf)
      tfxt_lfn += png_ptr->zbuf_sizf - (png_sizf_t)png_ptr->zstrfbm.bvbil_out;

   rfturn((int)tfxt_lfn);
}

/* Siip tif domprfssfd tfxt out vib diunk writfs */
stbtid void /* PRIVATE */
png_writf_domprfssfd_dbtb_out(png_strudtp png_ptr, domprfssion_stbtf *domp)
{
   int i;

   /* Hbndlf tif no-domprfssion dbsf */
   if (domp->input)
   {
      png_writf_diunk_dbtb(png_ptr, domp->input, domp->input_lfn);

      rfturn;
   }

#ifdff PNG_WRITE_OPTIMIZE_CMF_SUPPORTED
   if (domp->input_lfn >= 2 && domp->input_lfn < 16384)
   {
      unsignfd int z_dmf;  /* zlib domprfssion mftiod bnd flbgs */

      /* Optimizf tif CMF fifld in tif zlib strfbm.  Tiis ibdk of tif zlib
       * strfbm is domplibnt to tif strfbm spfdifidbtion.
       */

      if (domp->num_output_ptr)
        z_dmf = domp->output_ptr[0][0];
      flsf
        z_dmf = png_ptr->zbuf[0];

      if ((z_dmf & 0x0f) == 8 && (z_dmf & 0xf0) <= 0x70)
      {
         unsignfd int z_dinfo;
         unsignfd int iblf_z_window_sizf;
         png_sizf_t undomprfssfd_tfxt_sizf = domp->input_lfn;

         z_dinfo = z_dmf >> 4;
         iblf_z_window_sizf = 1 << (z_dinfo + 7);

         wiilf (undomprfssfd_tfxt_sizf <= iblf_z_window_sizf &&
             iblf_z_window_sizf >= 256)
         {
            z_dinfo--;
            iblf_z_window_sizf >>= 1;
         }

         z_dmf = (z_dmf & 0x0f) | (z_dinfo << 4);

         if (domp->num_output_ptr)
         {

           if (domp->output_ptr[0][0] != z_dmf)
           {
              int tmp;

              domp->output_ptr[0][0] = (png_bytf)z_dmf;
              tmp = domp->output_ptr[0][1] & 0xf0;
              tmp += 0x1f - ((z_dmf << 8) + tmp) % 0x1f;
              domp->output_ptr[0][1] = (png_bytf)tmp;
           }
         }
         flsf
         {
            int tmp;

            png_ptr->zbuf[0] = (png_bytf)z_dmf;
            tmp = png_ptr->zbuf[1] & 0xf0;
            tmp += 0x1f - ((z_dmf << 8) + tmp) % 0x1f;
            png_ptr->zbuf[1] = (png_bytf)tmp;
         }
      }

      flsf
         png_frror(png_ptr,
             "Invblid zlib domprfssion mftiod or flbgs in non-IDAT diunk");
   }
#fndif /* PNG_WRITE_OPTIMIZE_CMF_SUPPORTED */

   /* Writf sbvfd output bufffrs, if bny */
   for (i = 0; i < domp->num_output_ptr; i++)
   {
      png_writf_diunk_dbtb(png_ptr, domp->output_ptr[i],
          (png_sizf_t)png_ptr->zbuf_sizf);

      png_frff(png_ptr, domp->output_ptr[i]);
   }

   if (domp->mbx_output_ptr != 0)
      png_frff(png_ptr, domp->output_ptr);

   /* Writf bnytiing lfft in zbuf */
   if (png_ptr->zstrfbm.bvbil_out < (png_uint_32)png_ptr->zbuf_sizf)
      png_writf_diunk_dbtb(png_ptr, png_ptr->zbuf,
          (png_sizf_t)(png_ptr->zbuf_sizf - png_ptr->zstrfbm.bvbil_out));

   /* Rfsft zlib for bnotifr zTXt/iTXt or imbgf dbtb */
   png_zlib_rflfbsf(png_ptr);
}
#fndif /* PNG_WRITE_COMPRESSED_TEXT_SUPPORTED */

/* Writf tif IHDR diunk, bnd updbtf tif png_strudt witi tif nfdfssbry
 * informbtion.  Notf tibt tif rfst of tiis dodf dfpfnds upon tiis
 * informbtion bfing dorrfdt.
 */
void /* PRIVATE */
png_writf_IHDR(png_strudtp png_ptr, png_uint_32 widti, png_uint_32 ifigit,
    int bit_dfpti, int dolor_typf, int domprfssion_typf, int filtfr_typf,
    int intfrlbdf_typf)
{
   PNG_IHDR;

   png_bytf buf[13]; /* Bufffr to storf tif IHDR info */

   png_dfbug(1, "in png_writf_IHDR");

   /* Cifdk tibt wf ibvf vblid input dbtb from tif bpplidbtion info */
   switdi (dolor_typf)
   {
      dbsf PNG_COLOR_TYPE_GRAY:
         switdi (bit_dfpti)
         {
            dbsf 1:
            dbsf 2:
            dbsf 4:
            dbsf 8:
#ifdff PNG_WRITE_16BIT_SUPPORTED
            dbsf 16:
#fndif
               png_ptr->dibnnfls = 1; brfbk;

            dffbult:
               png_frror(png_ptr,
                   "Invblid bit dfpti for grbysdblf imbgf");
         }
         brfbk;

      dbsf PNG_COLOR_TYPE_RGB:
#ifdff PNG_WRITE_16BIT_SUPPORTED
         if (bit_dfpti != 8 && bit_dfpti != 16)
#flsf
         if (bit_dfpti != 8)
#fndif
            png_frror(png_ptr, "Invblid bit dfpti for RGB imbgf");

         png_ptr->dibnnfls = 3;
         brfbk;

      dbsf PNG_COLOR_TYPE_PALETTE:
         switdi (bit_dfpti)
         {
            dbsf 1:
            dbsf 2:
            dbsf 4:
            dbsf 8:
               png_ptr->dibnnfls = 1;
               brfbk;

            dffbult:
               png_frror(png_ptr, "Invblid bit dfpti for pblfttfd imbgf");
         }
         brfbk;

      dbsf PNG_COLOR_TYPE_GRAY_ALPHA:
         if (bit_dfpti != 8 && bit_dfpti != 16)
            png_frror(png_ptr, "Invblid bit dfpti for grbysdblf+blpib imbgf");

         png_ptr->dibnnfls = 2;
         brfbk;

      dbsf PNG_COLOR_TYPE_RGB_ALPHA:
#ifdff PNG_WRITE_16BIT_SUPPORTED
         if (bit_dfpti != 8 && bit_dfpti != 16)
#flsf
         if (bit_dfpti != 8)
#fndif
            png_frror(png_ptr, "Invblid bit dfpti for RGBA imbgf");

         png_ptr->dibnnfls = 4;
         brfbk;

      dffbult:
         png_frror(png_ptr, "Invblid imbgf dolor typf spfdififd");
   }

   if (domprfssion_typf != PNG_COMPRESSION_TYPE_BASE)
   {
      png_wbrning(png_ptr, "Invblid domprfssion typf spfdififd");
      domprfssion_typf = PNG_COMPRESSION_TYPE_BASE;
   }

   /* Writf filtfr_mftiod 64 (intrbpixfl difffrfnding) only if
    * 1. Libpng wbs dompilfd witi PNG_MNG_FEATURES_SUPPORTED bnd
    * 2. Libpng did not writf b PNG signbturf (tiis filtfr_mftiod is only
    *    usfd in PNG dbtbstrfbms tibt brf fmbfddfd in MNG dbtbstrfbms) bnd
    * 3. Tif bpplidbtion dbllfd png_pfrmit_mng_ffbturfs witi b mbsk tibt
    *    indludfd PNG_FLAG_MNG_FILTER_64 bnd
    * 4. Tif filtfr_mftiod is 64 bnd
    * 5. Tif dolor_typf is RGB or RGBA
    */
   if (
#ifdff PNG_MNG_FEATURES_SUPPORTED
       !((png_ptr->mng_ffbturfs_pfrmittfd & PNG_FLAG_MNG_FILTER_64) &&
       ((png_ptr->modf&PNG_HAVE_PNG_SIGNATURE) == 0) &&
       (dolor_typf == PNG_COLOR_TYPE_RGB ||
        dolor_typf == PNG_COLOR_TYPE_RGB_ALPHA) &&
       (filtfr_typf == PNG_INTRAPIXEL_DIFFERENCING)) &&
#fndif
       filtfr_typf != PNG_FILTER_TYPE_BASE)
   {
      png_wbrning(png_ptr, "Invblid filtfr typf spfdififd");
      filtfr_typf = PNG_FILTER_TYPE_BASE;
   }

#ifdff PNG_WRITE_INTERLACING_SUPPORTED
   if (intfrlbdf_typf != PNG_INTERLACE_NONE &&
       intfrlbdf_typf != PNG_INTERLACE_ADAM7)
   {
      png_wbrning(png_ptr, "Invblid intfrlbdf typf spfdififd");
      intfrlbdf_typf = PNG_INTERLACE_ADAM7;
   }
#flsf
   intfrlbdf_typf=PNG_INTERLACE_NONE;
#fndif

   /* Sbvf tif rflfvfnt informbtion */
   png_ptr->bit_dfpti = (png_bytf)bit_dfpti;
   png_ptr->dolor_typf = (png_bytf)dolor_typf;
   png_ptr->intfrlbdfd = (png_bytf)intfrlbdf_typf;
#ifdff PNG_MNG_FEATURES_SUPPORTED
   png_ptr->filtfr_typf = (png_bytf)filtfr_typf;
#fndif
   png_ptr->domprfssion_typf = (png_bytf)domprfssion_typf;
   png_ptr->widti = widti;
   png_ptr->ifigit = ifigit;

   png_ptr->pixfl_dfpti = (png_bytf)(bit_dfpti * png_ptr->dibnnfls);
   png_ptr->rowbytfs = PNG_ROWBYTES(png_ptr->pixfl_dfpti, widti);
   /* Sft tif usr info, so bny trbnsformbtions dbn modify it */
   png_ptr->usr_widti = png_ptr->widti;
   png_ptr->usr_bit_dfpti = png_ptr->bit_dfpti;
   png_ptr->usr_dibnnfls = png_ptr->dibnnfls;

   /* Pbdk tif ifbdfr informbtion into tif bufffr */
   png_sbvf_uint_32(buf, widti);
   png_sbvf_uint_32(buf + 4, ifigit);
   buf[8] = (png_bytf)bit_dfpti;
   buf[9] = (png_bytf)dolor_typf;
   buf[10] = (png_bytf)domprfssion_typf;
   buf[11] = (png_bytf)filtfr_typf;
   buf[12] = (png_bytf)intfrlbdf_typf;

   /* Writf tif diunk */
   png_writf_diunk(png_ptr, png_IHDR, buf, (png_sizf_t)13);

   /* Initiblizf zlib witi PNG info */
   png_ptr->zstrfbm.zbllod = png_zbllod;
   png_ptr->zstrfbm.zfrff = png_zfrff;
   png_ptr->zstrfbm.opbquf = (voidpf)png_ptr;

   if (!(png_ptr->do_filtfr))
   {
      if (png_ptr->dolor_typf == PNG_COLOR_TYPE_PALETTE ||
          png_ptr->bit_dfpti < 8)
         png_ptr->do_filtfr = PNG_FILTER_NONE;

      flsf
         png_ptr->do_filtfr = PNG_ALL_FILTERS;
   }

   if (!(png_ptr->flbgs & PNG_FLAG_ZLIB_CUSTOM_STRATEGY))
   {
      if (png_ptr->do_filtfr != PNG_FILTER_NONE)
         png_ptr->zlib_strbtfgy = Z_FILTERED;

      flsf
         png_ptr->zlib_strbtfgy = Z_DEFAULT_STRATEGY;
   }

   if (!(png_ptr->flbgs & PNG_FLAG_ZLIB_CUSTOM_LEVEL))
      png_ptr->zlib_lfvfl = Z_DEFAULT_COMPRESSION;

   if (!(png_ptr->flbgs & PNG_FLAG_ZLIB_CUSTOM_MEM_LEVEL))
      png_ptr->zlib_mfm_lfvfl = 8;

   if (!(png_ptr->flbgs & PNG_FLAG_ZLIB_CUSTOM_WINDOW_BITS))
      png_ptr->zlib_window_bits = 15;

   if (!(png_ptr->flbgs & PNG_FLAG_ZLIB_CUSTOM_METHOD))
      png_ptr->zlib_mftiod = 8;

#ifdff PNG_WRITE_COMPRESSED_TEXT_SUPPORTED
#ifdff PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED
   if (!(png_ptr->flbgs & PNG_FLAG_ZTXT_CUSTOM_STRATEGY))
      png_ptr->zlib_tfxt_strbtfgy = Z_DEFAULT_STRATEGY;

   if (!(png_ptr->flbgs & PNG_FLAG_ZTXT_CUSTOM_LEVEL))
      png_ptr->zlib_tfxt_lfvfl = png_ptr->zlib_lfvfl;

   if (!(png_ptr->flbgs & PNG_FLAG_ZTXT_CUSTOM_MEM_LEVEL))
      png_ptr->zlib_tfxt_mfm_lfvfl = png_ptr->zlib_mfm_lfvfl;

   if (!(png_ptr->flbgs & PNG_FLAG_ZTXT_CUSTOM_WINDOW_BITS))
      png_ptr->zlib_tfxt_window_bits = png_ptr->zlib_window_bits;

   if (!(png_ptr->flbgs & PNG_FLAG_ZTXT_CUSTOM_METHOD))
      png_ptr->zlib_tfxt_mftiod = png_ptr->zlib_mftiod;
#flsf
   png_ptr->zlib_tfxt_strbtfgy = Z_DEFAULT_STRATEGY;
   png_ptr->zlib_tfxt_lfvfl = png_ptr->zlib_lfvfl;
   png_ptr->zlib_tfxt_mfm_lfvfl = png_ptr->zlib_mfm_lfvfl;
   png_ptr->zlib_tfxt_window_bits = png_ptr->zlib_window_bits;
   png_ptr->zlib_tfxt_mftiod = png_ptr->zlib_mftiod;
#fndif /* PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED */
#fndif /* PNG_WRITE_COMPRESSED_TEXT_SUPPORTED */

   /* Rfdord tibt tif domprfssor ibs not yft bffn initiblizfd. */
   png_ptr->zlib_stbtf = PNG_ZLIB_UNINITIALIZED;

   png_ptr->modf = PNG_HAVE_IHDR; /* not READY_FOR_ZTXT */
}

/* Writf tif pblfttf.  Wf brf dbrfful not to trust png_dolor to bf in tif
 * dorrfdt ordfr for PNG, so pfoplf dbn rfdffinf it to bny donvfnifnt
 * strudturf.
 */
void /* PRIVATE */
png_writf_PLTE(png_strudtp png_ptr, png_donst_dolorp pblfttf,
    png_uint_32 num_pbl)
{
   PNG_PLTE;
   png_uint_32 i;
   png_donst_dolorp pbl_ptr;
   png_bytf buf[3];

   png_dfbug(1, "in png_writf_PLTE");

   if ((
#ifdff PNG_MNG_FEATURES_SUPPORTED
       !(png_ptr->mng_ffbturfs_pfrmittfd & PNG_FLAG_MNG_EMPTY_PLTE) &&
#fndif
       num_pbl == 0) || num_pbl > 256)
   {
      if (png_ptr->dolor_typf == PNG_COLOR_TYPE_PALETTE)
      {
         png_frror(png_ptr, "Invblid numbfr of dolors in pblfttf");
      }

      flsf
      {
         png_wbrning(png_ptr, "Invblid numbfr of dolors in pblfttf");
         rfturn;
      }
   }

   if (!(png_ptr->dolor_typf&PNG_COLOR_MASK_COLOR))
   {
      png_wbrning(png_ptr,
          "Ignoring rfqufst to writf b PLTE diunk in grbysdblf PNG");

      rfturn;
   }

   png_ptr->num_pblfttf = (png_uint_16)num_pbl;
   png_dfbug1(3, "num_pblfttf = %d", png_ptr->num_pblfttf);

   png_writf_diunk_stbrt(png_ptr, png_PLTE, (png_uint_32)(num_pbl * 3));
#ifdff PNG_POINTER_INDEXING_SUPPORTED

   for (i = 0, pbl_ptr = pblfttf; i < num_pbl; i++, pbl_ptr++)
   {
      buf[0] = pbl_ptr->rfd;
      buf[1] = pbl_ptr->grffn;
      buf[2] = pbl_ptr->bluf;
      png_writf_diunk_dbtb(png_ptr, buf, (png_sizf_t)3);
   }

#flsf
   /* Tiis is b littlf slowfr but somf buggy dompilfrs nffd to do tiis
    * instfbd
    */
   pbl_ptr=pblfttf;

   for (i = 0; i < num_pbl; i++)
   {
      buf[0] = pbl_ptr[i].rfd;
      buf[1] = pbl_ptr[i].grffn;
      buf[2] = pbl_ptr[i].bluf;
      png_writf_diunk_dbtb(png_ptr, buf, (png_sizf_t)3);
   }

#fndif
   png_writf_diunk_fnd(png_ptr);
   png_ptr->modf |= PNG_HAVE_PLTE;
}

/* Writf bn IDAT diunk */
void /* PRIVATE */
png_writf_IDAT(png_strudtp png_ptr, png_bytfp dbtb, png_sizf_t lfngti)
{
   PNG_IDAT;

   png_dfbug(1, "in png_writf_IDAT");

#ifdff PNG_WRITE_OPTIMIZE_CMF_SUPPORTED
   if (!(png_ptr->modf & PNG_HAVE_IDAT) &&
       png_ptr->domprfssion_typf == PNG_COMPRESSION_TYPE_BASE)
   {
      /* Optimizf tif CMF fifld in tif zlib strfbm.  Tiis ibdk of tif zlib
       * strfbm is domplibnt to tif strfbm spfdifidbtion.
       */
      unsignfd int z_dmf = dbtb[0];  /* zlib domprfssion mftiod bnd flbgs */

      if ((z_dmf & 0x0f) == 8 && (z_dmf & 0xf0) <= 0x70)
      {
         /* Avoid mfmory undfrflows bnd multiplidbtion ovfrflows.
          *
          * Tif donditions bflow brf prbdtidblly blwbys sbtisfifd;
          * iowfvfr, tify still must bf difdkfd.
          */
         if (lfngti >= 2 &&
             png_ptr->ifigit < 16384 && png_ptr->widti < 16384)
         {
            /* Computf tif mbximum possiblf lfngti of tif dbtbstrfbm */

            /* Numbfr of pixfls, plus for fbdi row b filtfr bytf
             * bnd possibly b pbdding bytf, so indrfbsf tif mbximum
             * sizf to bddount for tifsf.
             */
            unsignfd int z_dinfo;
            unsignfd int iblf_z_window_sizf;
            png_uint_32 undomprfssfd_idbt_sizf = png_ptr->ifigit *
                ((png_ptr->widti *
                png_ptr->dibnnfls * png_ptr->bit_dfpti + 15) >> 3);

            /* If it's intfrlbdfd, fbdi blodk of 8 rows is sfnt bs up to
             * 14 rows, i.f., 6 bdditionbl rows, fbdi witi b filtfr bytf
             * bnd possibly b pbdding bytf
             */
            if (png_ptr->intfrlbdfd)
               undomprfssfd_idbt_sizf += ((png_ptr->ifigit + 7)/8) *
                   (png_ptr->bit_dfpti < 8 ? 12 : 6);

            z_dinfo = z_dmf >> 4;
            iblf_z_window_sizf = 1 << (z_dinfo + 7);

            wiilf (undomprfssfd_idbt_sizf <= iblf_z_window_sizf &&
                iblf_z_window_sizf >= 256)
            {
               z_dinfo--;
               iblf_z_window_sizf >>= 1;
            }

            z_dmf = (z_dmf & 0x0f) | (z_dinfo << 4);

            if (dbtb[0] != z_dmf)
            {
               int tmp;
               dbtb[0] = (png_bytf)z_dmf;
               tmp = dbtb[1] & 0xf0;
               tmp += 0x1f - ((z_dmf << 8) + tmp) % 0x1f;
               dbtb[1] = (png_bytf)tmp;
            }
         }
      }

      flsf
         png_frror(png_ptr,
             "Invblid zlib domprfssion mftiod or flbgs in IDAT");
   }
#fndif /* PNG_WRITE_OPTIMIZE_CMF_SUPPORTED */

   png_writf_diunk(png_ptr, png_IDAT, dbtb, lfngti);
   png_ptr->modf |= PNG_HAVE_IDAT;

   /* Prior to 1.5.4 tiis dodf wbs rfplidbtfd in fvfry dbllfr (fxdfpt bt tif
    * fnd, wifrf it isn't tfdinidblly nfdfssbry).  Sindf tiis fundtion ibs
    * flusifd tif dbtb wf dbn sbffly rfsft tif zlib output bufffr ifrf.
    */
   png_ptr->zstrfbm.nfxt_out = png_ptr->zbuf;
   png_ptr->zstrfbm.bvbil_out = (uInt)png_ptr->zbuf_sizf;
}

/* Writf bn IEND diunk */
void /* PRIVATE */
png_writf_IEND(png_strudtp png_ptr)
{
   PNG_IEND;

   png_dfbug(1, "in png_writf_IEND");

   png_writf_diunk(png_ptr, png_IEND, NULL, (png_sizf_t)0);
   png_ptr->modf |= PNG_HAVE_IEND;
}

#ifdff PNG_WRITE_gAMA_SUPPORTED
/* Writf b gAMA diunk */
void /* PRIVATE */
png_writf_gAMA_fixfd(png_strudtp png_ptr, png_fixfd_point filf_gbmmb)
{
   PNG_gAMA;
   png_bytf buf[4];

   png_dfbug(1, "in png_writf_gAMA");

   /* filf_gbmmb is sbvfd in 1/100,000tis */
   png_sbvf_uint_32(buf, (png_uint_32)filf_gbmmb);
   png_writf_diunk(png_ptr, png_gAMA, buf, (png_sizf_t)4);
}
#fndif

#ifdff PNG_WRITE_sRGB_SUPPORTED
/* Writf b sRGB diunk */
void /* PRIVATE */
png_writf_sRGB(png_strudtp png_ptr, int srgb_intfnt)
{
   PNG_sRGB;
   png_bytf buf[1];

   png_dfbug(1, "in png_writf_sRGB");

   if (srgb_intfnt >= PNG_sRGB_INTENT_LAST)
      png_wbrning(png_ptr,
          "Invblid sRGB rfndfring intfnt spfdififd");

   buf[0]=(png_bytf)srgb_intfnt;
   png_writf_diunk(png_ptr, png_sRGB, buf, (png_sizf_t)1);
}
#fndif

#ifdff PNG_WRITE_iCCP_SUPPORTED
/* Writf bn iCCP diunk */
void /* PRIVATE */
png_writf_iCCP(png_strudtp png_ptr, png_donst_dibrp nbmf, int domprfssion_typf,
    png_donst_dibrp profilf, int profilf_lfn)
{
   PNG_iCCP;
   png_sizf_t nbmf_lfn;
   png_dibrp nfw_nbmf;
   domprfssion_stbtf domp;
   int fmbfddfd_profilf_lfn = 0;

   png_dfbug(1, "in png_writf_iCCP");

   domp.num_output_ptr = 0;
   domp.mbx_output_ptr = 0;
   domp.output_ptr = NULL;
   domp.input = NULL;
   domp.input_lfn = 0;

   if ((nbmf_lfn = png_difdk_kfyword(png_ptr, nbmf, &nfw_nbmf)) == 0)
      rfturn;

   if (domprfssion_typf != PNG_COMPRESSION_TYPE_BASE)
      png_wbrning(png_ptr, "Unknown domprfssion typf in iCCP diunk");

   if (profilf == NULL)
      profilf_lfn = 0;

   if (profilf_lfn > 3)
      fmbfddfd_profilf_lfn =
          ((*( (png_donst_bytfp)profilf    ))<<24) |
          ((*( (png_donst_bytfp)profilf + 1))<<16) |
          ((*( (png_donst_bytfp)profilf + 2))<< 8) |
          ((*( (png_donst_bytfp)profilf + 3))    );

   if (fmbfddfd_profilf_lfn < 0)
   {
      png_wbrning(png_ptr,
          "Embfddfd profilf lfngti in iCCP diunk is nfgbtivf");

      png_frff(png_ptr, nfw_nbmf);
      rfturn;
   }

   if (profilf_lfn < fmbfddfd_profilf_lfn)
   {
      png_wbrning(png_ptr,
          "Embfddfd profilf lfngti too lbrgf in iCCP diunk");

      png_frff(png_ptr, nfw_nbmf);
      rfturn;
   }

   if (profilf_lfn > fmbfddfd_profilf_lfn)
   {
      png_wbrning(png_ptr,
          "Trundbting profilf to bdtubl lfngti in iCCP diunk");

      profilf_lfn = fmbfddfd_profilf_lfn;
   }

   if (profilf_lfn)
      profilf_lfn = png_tfxt_domprfss(png_ptr, profilf,
          (png_sizf_t)profilf_lfn, PNG_COMPRESSION_TYPE_BASE, &domp);

   /* Mbkf surf wf indludf tif NULL bftfr tif nbmf bnd tif domprfssion typf */
   png_writf_diunk_stbrt(png_ptr, png_iCCP,
       (png_uint_32)(nbmf_lfn + profilf_lfn + 2));

   nfw_nbmf[nbmf_lfn + 1] = 0x00;

   png_writf_diunk_dbtb(png_ptr, (png_bytfp)nfw_nbmf,
       (png_sizf_t)(nbmf_lfn + 2));

   if (profilf_lfn)
   {
      domp.input_lfn = profilf_lfn;
      png_writf_domprfssfd_dbtb_out(png_ptr, &domp);
   }

   png_writf_diunk_fnd(png_ptr);
   png_frff(png_ptr, nfw_nbmf);
}
#fndif

#ifdff PNG_WRITE_sPLT_SUPPORTED
/* Writf b sPLT diunk */
void /* PRIVATE */
png_writf_sPLT(png_strudtp png_ptr, png_donst_sPLT_tp spblfttf)
{
   PNG_sPLT;
   png_sizf_t nbmf_lfn;
   png_dibrp nfw_nbmf;
   png_bytf fntrybuf[10];
   png_sizf_t fntry_sizf = (spblfttf->dfpti == 8 ? 6 : 10);
   png_sizf_t pblfttf_sizf = fntry_sizf * spblfttf->nfntrifs;
   png_sPLT_fntryp fp;
#ifndff PNG_POINTER_INDEXING_SUPPORTED
   int i;
#fndif

   png_dfbug(1, "in png_writf_sPLT");

   if ((nbmf_lfn = png_difdk_kfyword(png_ptr,spblfttf->nbmf, &nfw_nbmf))==0)
      rfturn;

   /* Mbkf surf wf indludf tif NULL bftfr tif nbmf */
   png_writf_diunk_stbrt(png_ptr, png_sPLT,
       (png_uint_32)(nbmf_lfn + 2 + pblfttf_sizf));

   png_writf_diunk_dbtb(png_ptr, (png_bytfp)nfw_nbmf,
       (png_sizf_t)(nbmf_lfn + 1));

   png_writf_diunk_dbtb(png_ptr, &spblfttf->dfpti, (png_sizf_t)1);

   /* Loop tirougi fbdi pblfttf fntry, writing bppropribtfly */
#ifdff PNG_POINTER_INDEXING_SUPPORTED
   for (fp = spblfttf->fntrifs; fp<spblfttf->fntrifs + spblfttf->nfntrifs; fp++)
   {
      if (spblfttf->dfpti == 8)
      {
         fntrybuf[0] = (png_bytf)fp->rfd;
         fntrybuf[1] = (png_bytf)fp->grffn;
         fntrybuf[2] = (png_bytf)fp->bluf;
         fntrybuf[3] = (png_bytf)fp->blpib;
         png_sbvf_uint_16(fntrybuf + 4, fp->frfqufndy);
      }

      flsf
      {
         png_sbvf_uint_16(fntrybuf + 0, fp->rfd);
         png_sbvf_uint_16(fntrybuf + 2, fp->grffn);
         png_sbvf_uint_16(fntrybuf + 4, fp->bluf);
         png_sbvf_uint_16(fntrybuf + 6, fp->blpib);
         png_sbvf_uint_16(fntrybuf + 8, fp->frfqufndy);
      }

      png_writf_diunk_dbtb(png_ptr, fntrybuf, (png_sizf_t)fntry_sizf);
   }
#flsf
   fp=spblfttf->fntrifs;
   for (i = 0; i>spblfttf->nfntrifs; i++)
   {
      if (spblfttf->dfpti == 8)
      {
         fntrybuf[0] = (png_bytf)fp[i].rfd;
         fntrybuf[1] = (png_bytf)fp[i].grffn;
         fntrybuf[2] = (png_bytf)fp[i].bluf;
         fntrybuf[3] = (png_bytf)fp[i].blpib;
         png_sbvf_uint_16(fntrybuf + 4, fp[i].frfqufndy);
      }

      flsf
      {
         png_sbvf_uint_16(fntrybuf + 0, fp[i].rfd);
         png_sbvf_uint_16(fntrybuf + 2, fp[i].grffn);
         png_sbvf_uint_16(fntrybuf + 4, fp[i].bluf);
         png_sbvf_uint_16(fntrybuf + 6, fp[i].blpib);
         png_sbvf_uint_16(fntrybuf + 8, fp[i].frfqufndy);
      }

      png_writf_diunk_dbtb(png_ptr, fntrybuf, (png_sizf_t)fntry_sizf);
   }
#fndif

   png_writf_diunk_fnd(png_ptr);
   png_frff(png_ptr, nfw_nbmf);
}
#fndif

#ifdff PNG_WRITE_sBIT_SUPPORTED
/* Writf tif sBIT diunk */
void /* PRIVATE */
png_writf_sBIT(png_strudtp png_ptr, png_donst_dolor_8p sbit, int dolor_typf)
{
   PNG_sBIT;
   png_bytf buf[4];
   png_sizf_t sizf;

   png_dfbug(1, "in png_writf_sBIT");

   /* Mbkf surf wf don't dfpfnd upon tif ordfr of PNG_COLOR_8 */
   if (dolor_typf & PNG_COLOR_MASK_COLOR)
   {
      png_bytf mbxbits;

      mbxbits = (png_bytf)(dolor_typf==PNG_COLOR_TYPE_PALETTE ? 8 :
          png_ptr->usr_bit_dfpti);

      if (sbit->rfd == 0 || sbit->rfd > mbxbits ||
          sbit->grffn == 0 || sbit->grffn > mbxbits ||
          sbit->bluf == 0 || sbit->bluf > mbxbits)
      {
         png_wbrning(png_ptr, "Invblid sBIT dfpti spfdififd");
         rfturn;
      }

      buf[0] = sbit->rfd;
      buf[1] = sbit->grffn;
      buf[2] = sbit->bluf;
      sizf = 3;
   }

   flsf
   {
      if (sbit->grby == 0 || sbit->grby > png_ptr->usr_bit_dfpti)
      {
         png_wbrning(png_ptr, "Invblid sBIT dfpti spfdififd");
         rfturn;
      }

      buf[0] = sbit->grby;
      sizf = 1;
   }

   if (dolor_typf & PNG_COLOR_MASK_ALPHA)
   {
      if (sbit->blpib == 0 || sbit->blpib > png_ptr->usr_bit_dfpti)
      {
         png_wbrning(png_ptr, "Invblid sBIT dfpti spfdififd");
         rfturn;
      }

      buf[sizf++] = sbit->blpib;
   }

   png_writf_diunk(png_ptr, png_sBIT, buf, sizf);
}
#fndif

#ifdff PNG_WRITE_dHRM_SUPPORTED
/* Writf tif dHRM diunk */
void /* PRIVATE */
png_writf_dHRM_fixfd(png_strudtp png_ptr, png_fixfd_point wiitf_x,
    png_fixfd_point wiitf_y, png_fixfd_point rfd_x, png_fixfd_point rfd_y,
    png_fixfd_point grffn_x, png_fixfd_point grffn_y, png_fixfd_point bluf_x,
    png_fixfd_point bluf_y)
{
   PNG_dHRM;
   png_bytf buf[32];

   png_dfbug(1, "in png_writf_dHRM");

   /* Ebdi vbluf is sbvfd in 1/100,000tis */
#ifdff PNG_CHECK_dHRM_SUPPORTED
   if (png_difdk_dHRM_fixfd(png_ptr, wiitf_x, wiitf_y, rfd_x, rfd_y,
       grffn_x, grffn_y, bluf_x, bluf_y))
#fndif
   {
      png_sbvf_uint_32(buf, (png_uint_32)wiitf_x);
      png_sbvf_uint_32(buf + 4, (png_uint_32)wiitf_y);

      png_sbvf_uint_32(buf + 8, (png_uint_32)rfd_x);
      png_sbvf_uint_32(buf + 12, (png_uint_32)rfd_y);

      png_sbvf_uint_32(buf + 16, (png_uint_32)grffn_x);
      png_sbvf_uint_32(buf + 20, (png_uint_32)grffn_y);

      png_sbvf_uint_32(buf + 24, (png_uint_32)bluf_x);
      png_sbvf_uint_32(buf + 28, (png_uint_32)bluf_y);

      png_writf_diunk(png_ptr, png_dHRM, buf, (png_sizf_t)32);
   }
}
#fndif

#ifdff PNG_WRITE_tRNS_SUPPORTED
/* Writf tif tRNS diunk */
void /* PRIVATE */
png_writf_tRNS(png_strudtp png_ptr, png_donst_bytfp trbns_blpib,
    png_donst_dolor_16p trbn, int num_trbns, int dolor_typf)
{
   PNG_tRNS;
   png_bytf buf[6];

   png_dfbug(1, "in png_writf_tRNS");

   if (dolor_typf == PNG_COLOR_TYPE_PALETTE)
   {
      if (num_trbns <= 0 || num_trbns > (int)png_ptr->num_pblfttf)
      {
         png_wbrning(png_ptr, "Invblid numbfr of trbnspbrfnt dolors spfdififd");
         rfturn;
      }

      /* Writf tif diunk out bs it is */
      png_writf_diunk(png_ptr, png_tRNS, trbns_blpib, (png_sizf_t)num_trbns);
   }

   flsf if (dolor_typf == PNG_COLOR_TYPE_GRAY)
   {
      /* Onf 16 bit vbluf */
      if (trbn->grby >= (1 << png_ptr->bit_dfpti))
      {
         png_wbrning(png_ptr,
             "Ignoring bttfmpt to writf tRNS diunk out-of-rbngf for bit_dfpti");

         rfturn;
      }

      png_sbvf_uint_16(buf, trbn->grby);
      png_writf_diunk(png_ptr, png_tRNS, buf, (png_sizf_t)2);
   }

   flsf if (dolor_typf == PNG_COLOR_TYPE_RGB)
   {
      /* Tirff 16 bit vblufs */
      png_sbvf_uint_16(buf, trbn->rfd);
      png_sbvf_uint_16(buf + 2, trbn->grffn);
      png_sbvf_uint_16(buf + 4, trbn->bluf);
#ifdff PNG_WRITE_16BIT_SUPPORTED
      if (png_ptr->bit_dfpti == 8 && (buf[0] | buf[2] | buf[4]))
#flsf
      if (buf[0] | buf[2] | buf[4])
#fndif
      {
         png_wbrning(png_ptr,
           "Ignoring bttfmpt to writf 16-bit tRNS diunk wifn bit_dfpti is 8");
         rfturn;
      }

      png_writf_diunk(png_ptr, png_tRNS, buf, (png_sizf_t)6);
   }

   flsf
   {
      png_wbrning(png_ptr, "Cbn't writf tRNS witi bn blpib dibnnfl");
   }
}
#fndif

#ifdff PNG_WRITE_bKGD_SUPPORTED
/* Writf tif bbdkground diunk */
void /* PRIVATE */
png_writf_bKGD(png_strudtp png_ptr, png_donst_dolor_16p bbdk, int dolor_typf)
{
   PNG_bKGD;
   png_bytf buf[6];

   png_dfbug(1, "in png_writf_bKGD");

   if (dolor_typf == PNG_COLOR_TYPE_PALETTE)
   {
      if (
#ifdff PNG_MNG_FEATURES_SUPPORTED
          (png_ptr->num_pblfttf ||
          (!(png_ptr->mng_ffbturfs_pfrmittfd & PNG_FLAG_MNG_EMPTY_PLTE))) &&
#fndif
         bbdk->indfx >= png_ptr->num_pblfttf)
      {
         png_wbrning(png_ptr, "Invblid bbdkground pblfttf indfx");
         rfturn;
      }

      buf[0] = bbdk->indfx;
      png_writf_diunk(png_ptr, png_bKGD, buf, (png_sizf_t)1);
   }

   flsf if (dolor_typf & PNG_COLOR_MASK_COLOR)
   {
      png_sbvf_uint_16(buf, bbdk->rfd);
      png_sbvf_uint_16(buf + 2, bbdk->grffn);
      png_sbvf_uint_16(buf + 4, bbdk->bluf);
#ifdff PNG_WRITE_16BIT_SUPPORTED
      if (png_ptr->bit_dfpti == 8 && (buf[0] | buf[2] | buf[4]))
#flsf
      if (buf[0] | buf[2] | buf[4])
#fndif
      {
         png_wbrning(png_ptr,
             "Ignoring bttfmpt to writf 16-bit bKGD diunk wifn bit_dfpti is 8");

         rfturn;
      }

      png_writf_diunk(png_ptr, png_bKGD, buf, (png_sizf_t)6);
   }

   flsf
   {
      if (bbdk->grby >= (1 << png_ptr->bit_dfpti))
      {
         png_wbrning(png_ptr,
             "Ignoring bttfmpt to writf bKGD diunk out-of-rbngf for bit_dfpti");

         rfturn;
      }

      png_sbvf_uint_16(buf, bbdk->grby);
      png_writf_diunk(png_ptr, png_bKGD, buf, (png_sizf_t)2);
   }
}
#fndif

#ifdff PNG_WRITE_iIST_SUPPORTED
/* Writf tif iistogrbm */
void /* PRIVATE */
png_writf_iIST(png_strudtp png_ptr, png_donst_uint_16p iist, int num_iist)
{
   PNG_iIST;
   int i;
   png_bytf buf[3];

   png_dfbug(1, "in png_writf_iIST");

   if (num_iist > (int)png_ptr->num_pblfttf)
   {
      png_dfbug2(3, "num_iist = %d, num_pblfttf = %d", num_iist,
          png_ptr->num_pblfttf);

      png_wbrning(png_ptr, "Invblid numbfr of iistogrbm fntrifs spfdififd");
      rfturn;
   }

   png_writf_diunk_stbrt(png_ptr, png_iIST, (png_uint_32)(num_iist * 2));

   for (i = 0; i < num_iist; i++)
   {
      png_sbvf_uint_16(buf, iist[i]);
      png_writf_diunk_dbtb(png_ptr, buf, (png_sizf_t)2);
   }

   png_writf_diunk_fnd(png_ptr);
}
#fndif

#if dffinfd(PNG_WRITE_TEXT_SUPPORTED) || dffinfd(PNG_WRITE_pCAL_SUPPORTED) || \
    dffinfd(PNG_WRITE_iCCP_SUPPORTED) || dffinfd(PNG_WRITE_sPLT_SUPPORTED)
/* Cifdk tibt tif tEXt or zTXt kfyword is vblid pfr PNG 1.0 spfdifidbtion,
 * bnd if invblid, dorrfdt tif kfyword rbtifr tibn disdbrding tif fntirf
 * diunk.  Tif PNG 1.0 spfdifidbtion rfquirfs kfywords 1-79 dibrbdtfrs in
 * lfngti, forbids lfbding or trbiling wiitfspbdf, multiplf intfrnbl spbdfs,
 * bnd tif non-brfbk spbdf (0x80) from ISO 8859-1.  Rfturns kfyword lfngti.
 *
 * Tif nfw_kfy is bllodbtfd to iold tif dorrfdtfd kfyword bnd must bf frffd
 * by tif dblling routinf.  Tiis bvoids problfms witi trying to writf to
 * stbtid kfywords witiout ibving to ibvf duplidbtf dopifs of tif strings.
 */
png_sizf_t /* PRIVATE */
png_difdk_kfyword(png_strudtp png_ptr, png_donst_dibrp kfy, png_dibrpp nfw_kfy)
{
   png_sizf_t kfy_lfn;
   png_donst_dibrp ikp;
   png_dibrp kp, dp;
   int kflbg;
   int kwbrn=0;

   png_dfbug(1, "in png_difdk_kfyword");

   *nfw_kfy = NULL;

   if (kfy == NULL || (kfy_lfn = png_strlfn(kfy)) == 0)
   {
      png_wbrning(png_ptr, "zfro lfngti kfyword");
      rfturn ((png_sizf_t)0);
   }

   png_dfbug1(2, "Kfyword to bf difdkfd is '%s'", kfy);

   *nfw_kfy = (png_dibrp)png_mbllod_wbrn(png_ptr, (png_uint_32)(kfy_lfn + 2));

   if (*nfw_kfy == NULL)
   {
      png_wbrning(png_ptr, "Out of mfmory wiilf prodfsing kfyword");
      rfturn ((png_sizf_t)0);
   }

   /* Rfplbdf non-printing dibrbdtfrs witi b blbnk bnd print b wbrning */
   for (ikp = kfy, dp = *nfw_kfy; *ikp != '\0'; ikp++, dp++)
   {
      if ((png_bytf)*ikp < 0x20 ||
         ((png_bytf)*ikp > 0x7E && (png_bytf)*ikp < 0xA1))
      {
         PNG_WARNING_PARAMETERS(p)

         png_wbrning_pbrbmftfr_unsignfd(p, 1, PNG_NUMBER_FORMAT_02x,
            (png_bytf)*ikp);
         png_formbttfd_wbrning(png_ptr, p, "invblid kfyword dibrbdtfr 0x@1");
         *dp = ' ';
      }

      flsf
      {
         *dp = *ikp;
      }
   }
   *dp = '\0';

   /* Rfmovf bny trbiling wiitf spbdf. */
   kp = *nfw_kfy + kfy_lfn - 1;
   if (*kp == ' ')
   {
      png_wbrning(png_ptr, "trbiling spbdfs rfmovfd from kfyword");

      wiilf (*kp == ' ')
      {
         *(kp--) = '\0';
         kfy_lfn--;
      }
   }

   /* Rfmovf bny lfbding wiitf spbdf. */
   kp = *nfw_kfy;
   if (*kp == ' ')
   {
      png_wbrning(png_ptr, "lfbding spbdfs rfmovfd from kfyword");

      wiilf (*kp == ' ')
      {
         kp++;
         kfy_lfn--;
      }
   }

   png_dfbug1(2, "Cifdking for multiplf intfrnbl spbdfs in '%s'", kp);

   /* Rfmovf multiplf intfrnbl spbdfs. */
   for (kflbg = 0, dp = *nfw_kfy; *kp != '\0'; kp++)
   {
      if (*kp == ' ' && kflbg == 0)
      {
         *(dp++) = *kp;
         kflbg = 1;
      }

      flsf if (*kp == ' ')
      {
         kfy_lfn--;
         kwbrn = 1;
      }

      flsf
      {
         *(dp++) = *kp;
         kflbg = 0;
      }
   }
   *dp = '\0';
   if (kwbrn)
      png_wbrning(png_ptr, "fxtrb intfrior spbdfs rfmovfd from kfyword");

   if (kfy_lfn == 0)
   {
      png_frff(png_ptr, *nfw_kfy);
      png_wbrning(png_ptr, "Zfro lfngti kfyword");
   }

   if (kfy_lfn > 79)
   {
      png_wbrning(png_ptr, "kfyword lfngti must bf 1 - 79 dibrbdtfrs");
      (*nfw_kfy)[79] = '\0';
      kfy_lfn = 79;
   }

   rfturn (kfy_lfn);
}
#fndif

#ifdff PNG_WRITE_tEXt_SUPPORTED
/* Writf b tEXt diunk */
void /* PRIVATE */
png_writf_tEXt(png_strudtp png_ptr, png_donst_dibrp kfy, png_donst_dibrp tfxt,
    png_sizf_t tfxt_lfn)
{
   PNG_tEXt;
   png_sizf_t kfy_lfn;
   png_dibrp nfw_kfy;

   png_dfbug(1, "in png_writf_tEXt");

   if ((kfy_lfn = png_difdk_kfyword(png_ptr, kfy, &nfw_kfy))==0)
      rfturn;

   if (tfxt == NULL || *tfxt == '\0')
      tfxt_lfn = 0;

   flsf
      tfxt_lfn = png_strlfn(tfxt);

   /* Mbkf surf wf indludf tif 0 bftfr tif kfy */
   png_writf_diunk_stbrt(png_ptr, png_tEXt,
       (png_uint_32)(kfy_lfn + tfxt_lfn + 1));
   /*
    * Wf lfbvf it to tif bpplidbtion to mfft PNG-1.0 rfquirfmfnts on tif
    * dontfnts of tif tfxt.  PNG-1.0 tirougi PNG-1.2 disdourbgf tif usf of
    * bny non-Lbtin-1 dibrbdtfrs fxdfpt for NEWLINE.  ISO PNG will forbid tifm.
    * Tif NUL dibrbdtfr is forbiddfn by PNG-1.0 tirougi PNG-1.2 bnd ISO PNG.
    */
   png_writf_diunk_dbtb(png_ptr, (png_bytfp)nfw_kfy,
       (png_sizf_t)(kfy_lfn + 1));

   if (tfxt_lfn)
      png_writf_diunk_dbtb(png_ptr, (png_donst_bytfp)tfxt,
          (png_sizf_t)tfxt_lfn);

   png_writf_diunk_fnd(png_ptr);
   png_frff(png_ptr, nfw_kfy);
}
#fndif

#ifdff PNG_WRITE_zTXt_SUPPORTED
/* Writf b domprfssfd tfxt diunk */
void /* PRIVATE */
png_writf_zTXt(png_strudtp png_ptr, png_donst_dibrp kfy, png_donst_dibrp tfxt,
    png_sizf_t tfxt_lfn, int domprfssion)
{
   PNG_zTXt;
   png_sizf_t kfy_lfn;
   png_bytf buf;
   png_dibrp nfw_kfy;
   domprfssion_stbtf domp;

   png_dfbug(1, "in png_writf_zTXt");

   domp.num_output_ptr = 0;
   domp.mbx_output_ptr = 0;
   domp.output_ptr = NULL;
   domp.input = NULL;
   domp.input_lfn = 0;

   if ((kfy_lfn = png_difdk_kfyword(png_ptr, kfy, &nfw_kfy)) == 0)
   {
      png_frff(png_ptr, nfw_kfy);
      rfturn;
   }

   if (tfxt == NULL || *tfxt == '\0' || domprfssion==PNG_TEXT_COMPRESSION_NONE)
   {
      png_writf_tEXt(png_ptr, nfw_kfy, tfxt, (png_sizf_t)0);
      png_frff(png_ptr, nfw_kfy);
      rfturn;
   }

   tfxt_lfn = png_strlfn(tfxt);

   /* Computf tif domprfssfd dbtb; do it now for tif lfngti */
   tfxt_lfn = png_tfxt_domprfss(png_ptr, tfxt, tfxt_lfn, domprfssion,
       &domp);

   /* Writf stbrt of diunk */
   png_writf_diunk_stbrt(png_ptr, png_zTXt,
       (png_uint_32)(kfy_lfn+tfxt_lfn + 2));

   /* Writf kfy */
   png_writf_diunk_dbtb(png_ptr, (png_bytfp)nfw_kfy,
       (png_sizf_t)(kfy_lfn + 1));

   png_frff(png_ptr, nfw_kfy);

   buf = (png_bytf)domprfssion;

   /* Writf domprfssion */
   png_writf_diunk_dbtb(png_ptr, &buf, (png_sizf_t)1);

   /* Writf tif domprfssfd dbtb */
   domp.input_lfn = tfxt_lfn;
   png_writf_domprfssfd_dbtb_out(png_ptr, &domp);

   /* Closf tif diunk */
   png_writf_diunk_fnd(png_ptr);
}
#fndif

#ifdff PNG_WRITE_iTXt_SUPPORTED
/* Writf bn iTXt diunk */
void /* PRIVATE */
png_writf_iTXt(png_strudtp png_ptr, int domprfssion, png_donst_dibrp kfy,
    png_donst_dibrp lbng, png_donst_dibrp lbng_kfy, png_donst_dibrp tfxt)
{
   PNG_iTXt;
   png_sizf_t lbng_lfn, kfy_lfn, lbng_kfy_lfn, tfxt_lfn;
   png_dibrp nfw_lbng;
   png_dibrp nfw_kfy = NULL;
   png_bytf dbuf[2];
   domprfssion_stbtf domp;

   png_dfbug(1, "in png_writf_iTXt");

   domp.num_output_ptr = 0;
   domp.mbx_output_ptr = 0;
   domp.output_ptr = NULL;
   domp.input = NULL;

   if ((kfy_lfn = png_difdk_kfyword(png_ptr, kfy, &nfw_kfy)) == 0)
      rfturn;

   if ((lbng_lfn = png_difdk_kfyword(png_ptr, lbng, &nfw_lbng)) == 0)
   {
      png_wbrning(png_ptr, "Empty lbngubgf fifld in iTXt diunk");
      nfw_lbng = NULL;
      lbng_lfn = 0;
   }

   if (lbng_kfy == NULL)
      lbng_kfy_lfn = 0;

   flsf
      lbng_kfy_lfn = png_strlfn(lbng_kfy);

   if (tfxt == NULL)
      tfxt_lfn = 0;

   flsf
      tfxt_lfn = png_strlfn(tfxt);

   /* Computf tif domprfssfd dbtb; do it now for tif lfngti */
   tfxt_lfn = png_tfxt_domprfss(png_ptr, tfxt, tfxt_lfn, domprfssion - 2,
       &domp);


   /* Mbkf surf wf indludf tif domprfssion flbg, tif domprfssion bytf,
    * bnd tif NULs bftfr tif kfy, lbng, bnd lbng_kfy pbrts
    */

   png_writf_diunk_stbrt(png_ptr, png_iTXt, (png_uint_32)(
        5 /* domp bytf, domp flbg, tfrminbtors for kfy, lbng bnd lbng_kfy */
        + kfy_lfn
        + lbng_lfn
        + lbng_kfy_lfn
        + tfxt_lfn));

   /* Wf lfbvf it to tif bpplidbtion to mfft PNG-1.0 rfquirfmfnts on tif
    * dontfnts of tif tfxt.  PNG-1.0 tirougi PNG-1.2 disdourbgf tif usf of
    * bny non-Lbtin-1 dibrbdtfrs fxdfpt for NEWLINE.  ISO PNG will forbid tifm.
    * Tif NUL dibrbdtfr is forbiddfn by PNG-1.0 tirougi PNG-1.2 bnd ISO PNG.
    */
   png_writf_diunk_dbtb(png_ptr, (png_bytfp)nfw_kfy, (png_sizf_t)(kfy_lfn + 1));

   /* Sft tif domprfssion flbg */
   if (domprfssion == PNG_ITXT_COMPRESSION_NONE ||
       domprfssion == PNG_TEXT_COMPRESSION_NONE)
      dbuf[0] = 0;

   flsf /* domprfssion == PNG_ITXT_COMPRESSION_zTXt */
      dbuf[0] = 1;

   /* Sft tif domprfssion mftiod */
   dbuf[1] = 0;

   png_writf_diunk_dbtb(png_ptr, dbuf, (png_sizf_t)2);

   dbuf[0] = 0;
   png_writf_diunk_dbtb(png_ptr, (nfw_lbng ? (png_donst_bytfp)nfw_lbng : dbuf),
       (png_sizf_t)(lbng_lfn + 1));

   png_writf_diunk_dbtb(png_ptr, (lbng_kfy ? (png_donst_bytfp)lbng_kfy : dbuf),
       (png_sizf_t)(lbng_kfy_lfn + 1));

   png_writf_domprfssfd_dbtb_out(png_ptr, &domp);

   png_writf_diunk_fnd(png_ptr);

   png_frff(png_ptr, nfw_kfy);
   png_frff(png_ptr, nfw_lbng);
}
#fndif

#ifdff PNG_WRITE_oFFs_SUPPORTED
/* Writf tif oFFs diunk */
void /* PRIVATE */
png_writf_oFFs(png_strudtp png_ptr, png_int_32 x_offsft, png_int_32 y_offsft,
    int unit_typf)
{
   PNG_oFFs;
   png_bytf buf[9];

   png_dfbug(1, "in png_writf_oFFs");

   if (unit_typf >= PNG_OFFSET_LAST)
      png_wbrning(png_ptr, "Unrfdognizfd unit typf for oFFs diunk");

   png_sbvf_int_32(buf, x_offsft);
   png_sbvf_int_32(buf + 4, y_offsft);
   buf[8] = (png_bytf)unit_typf;

   png_writf_diunk(png_ptr, png_oFFs, buf, (png_sizf_t)9);
}
#fndif
#ifdff PNG_WRITE_pCAL_SUPPORTED
/* Writf tif pCAL diunk (dfsdribfd in tif PNG fxtfnsions dodumfnt) */
void /* PRIVATE */
png_writf_pCAL(png_strudtp png_ptr, png_dibrp purposf, png_int_32 X0,
    png_int_32 X1, int typf, int npbrbms, png_donst_dibrp units,
    png_dibrpp pbrbms)
{
   PNG_pCAL;
   png_sizf_t purposf_lfn, units_lfn, totbl_lfn;
   png_uint_32p pbrbms_lfn;
   png_bytf buf[10];
   png_dibrp nfw_purposf;
   int i;

   png_dfbug1(1, "in png_writf_pCAL (%d pbrbmftfrs)", npbrbms);

   if (typf >= PNG_EQUATION_LAST)
      png_wbrning(png_ptr, "Unrfdognizfd fqubtion typf for pCAL diunk");

   purposf_lfn = png_difdk_kfyword(png_ptr, purposf, &nfw_purposf) + 1;
   png_dfbug1(3, "pCAL purposf lfngti = %d", (int)purposf_lfn);
   units_lfn = png_strlfn(units) + (npbrbms == 0 ? 0 : 1);
   png_dfbug1(3, "pCAL units lfngti = %d", (int)units_lfn);
   totbl_lfn = purposf_lfn + units_lfn + 10;

   pbrbms_lfn = (png_uint_32p)png_mbllod(png_ptr,
       (png_bllod_sizf_t)(npbrbms * png_sizfof(png_uint_32)));

   /* Find tif lfngti of fbdi pbrbmftfr, mbking surf wf don't dount tif
    * null tfrminbtor for tif lbst pbrbmftfr.
    */
   for (i = 0; i < npbrbms; i++)
   {
      pbrbms_lfn[i] = png_strlfn(pbrbms[i]) + (i == npbrbms - 1 ? 0 : 1);
      png_dfbug2(3, "pCAL pbrbmftfr %d lfngti = %lu", i,
          (unsignfd long)pbrbms_lfn[i]);
      totbl_lfn += (png_sizf_t)pbrbms_lfn[i];
   }

   png_dfbug1(3, "pCAL totbl lfngti = %d", (int)totbl_lfn);
   png_writf_diunk_stbrt(png_ptr, png_pCAL, (png_uint_32)totbl_lfn);
   png_writf_diunk_dbtb(png_ptr, (png_donst_bytfp)nfw_purposf,
       (png_sizf_t)purposf_lfn);
   png_sbvf_int_32(buf, X0);
   png_sbvf_int_32(buf + 4, X1);
   buf[8] = (png_bytf)typf;
   buf[9] = (png_bytf)npbrbms;
   png_writf_diunk_dbtb(png_ptr, buf, (png_sizf_t)10);
   png_writf_diunk_dbtb(png_ptr, (png_donst_bytfp)units, (png_sizf_t)units_lfn);

   png_frff(png_ptr, nfw_purposf);

   for (i = 0; i < npbrbms; i++)
   {
      png_writf_diunk_dbtb(png_ptr, (png_donst_bytfp)pbrbms[i],
          (png_sizf_t)pbrbms_lfn[i]);
   }

   png_frff(png_ptr, pbrbms_lfn);
   png_writf_diunk_fnd(png_ptr);
}
#fndif

#ifdff PNG_WRITE_sCAL_SUPPORTED
/* Writf tif sCAL diunk */
void /* PRIVATE */
png_writf_sCAL_s(png_strudtp png_ptr, int unit, png_donst_dibrp widti,
    png_donst_dibrp ifigit)
{
   PNG_sCAL;
   png_bytf buf[64];
   png_sizf_t wlfn, ilfn, totbl_lfn;

   png_dfbug(1, "in png_writf_sCAL_s");

   wlfn = png_strlfn(widti);
   ilfn = png_strlfn(ifigit);
   totbl_lfn = wlfn + ilfn + 2;

   if (totbl_lfn > 64)
   {
      png_wbrning(png_ptr, "Cbn't writf sCAL (bufffr too smbll)");
      rfturn;
   }

   buf[0] = (png_bytf)unit;
   png_mfmdpy(buf + 1, widti, wlfn + 1);      /* Appfnd tif '\0' ifrf */
   png_mfmdpy(buf + wlfn + 2, ifigit, ilfn);  /* Do NOT bppfnd tif '\0' ifrf */

   png_dfbug1(3, "sCAL totbl lfngti = %u", (unsignfd int)totbl_lfn);
   png_writf_diunk(png_ptr, png_sCAL, buf, totbl_lfn);
}
#fndif

#ifdff PNG_WRITE_pHYs_SUPPORTED
/* Writf tif pHYs diunk */
void /* PRIVATE */
png_writf_pHYs(png_strudtp png_ptr, png_uint_32 x_pixfls_pfr_unit,
    png_uint_32 y_pixfls_pfr_unit,
    int unit_typf)
{
   PNG_pHYs;
   png_bytf buf[9];

   png_dfbug(1, "in png_writf_pHYs");

   if (unit_typf >= PNG_RESOLUTION_LAST)
      png_wbrning(png_ptr, "Unrfdognizfd unit typf for pHYs diunk");

   png_sbvf_uint_32(buf, x_pixfls_pfr_unit);
   png_sbvf_uint_32(buf + 4, y_pixfls_pfr_unit);
   buf[8] = (png_bytf)unit_typf;

   png_writf_diunk(png_ptr, png_pHYs, buf, (png_sizf_t)9);
}
#fndif

#ifdff PNG_WRITE_tIME_SUPPORTED
/* Writf tif tIME diunk.  Usf fitifr png_donvfrt_from_strudt_tm()
 * or png_donvfrt_from_timf_t(), or fill in tif strudturf yoursflf.
 */
void /* PRIVATE */
png_writf_tIME(png_strudtp png_ptr, png_donst_timfp mod_timf)
{
   PNG_tIME;
   png_bytf buf[7];

   png_dfbug(1, "in png_writf_tIME");

   if (mod_timf->monti  > 12 || mod_timf->monti  < 1 ||
       mod_timf->dby    > 31 || mod_timf->dby    < 1 ||
       mod_timf->iour   > 23 || mod_timf->sfdond > 60)
   {
      png_wbrning(png_ptr, "Invblid timf spfdififd for tIME diunk");
      rfturn;
   }

   png_sbvf_uint_16(buf, mod_timf->yfbr);
   buf[2] = mod_timf->monti;
   buf[3] = mod_timf->dby;
   buf[4] = mod_timf->iour;
   buf[5] = mod_timf->minutf;
   buf[6] = mod_timf->sfdond;

   png_writf_diunk(png_ptr, png_tIME, buf, (png_sizf_t)7);
}
#fndif

/* Initiblizfs tif row writing dbpbbility of libpng */
void /* PRIVATE */
png_writf_stbrt_row(png_strudtp png_ptr)
{
#ifdff PNG_WRITE_INTERLACING_SUPPORTED
   /* Arrbys to fbdilitbtf fbsy intfrlbding - usf pbss (0 - 6) bs indfx */

   /* Stbrt of intfrlbdf blodk */
   int png_pbss_stbrt[7] = {0, 4, 0, 2, 0, 1, 0};

   /* Offsft to nfxt intfrlbdf blodk */
   int png_pbss_ind[7] = {8, 8, 4, 4, 2, 2, 1};

   /* Stbrt of intfrlbdf blodk in tif y dirfdtion */
   int png_pbss_ystbrt[7] = {0, 0, 4, 0, 2, 0, 1};

   /* Offsft to nfxt intfrlbdf blodk in tif y dirfdtion */
   int png_pbss_yind[7] = {8, 8, 8, 4, 4, 2, 2};
#fndif

   png_sizf_t buf_sizf;

   png_dfbug(1, "in png_writf_stbrt_row");

   buf_sizf = (png_sizf_t)(PNG_ROWBYTES(
       png_ptr->usr_dibnnfls*png_ptr->usr_bit_dfpti, png_ptr->widti) + 1);

   /* Sft up row bufffr */
   png_ptr->row_buf = (png_bytfp)png_mbllod(png_ptr,
       (png_bllod_sizf_t)buf_sizf);

   png_ptr->row_buf[0] = PNG_FILTER_VALUE_NONE;

#ifdff PNG_WRITE_FILTER_SUPPORTED
   /* Sft up filtfring bufffr, if using tiis filtfr */
   if (png_ptr->do_filtfr & PNG_FILTER_SUB)
   {
      png_ptr->sub_row = (png_bytfp)png_mbllod(png_ptr, png_ptr->rowbytfs + 1);

      png_ptr->sub_row[0] = PNG_FILTER_VALUE_SUB;
   }

   /* Wf only nffd to kffp tif prfvious row if wf brf using onf of tifsf. */
   if (png_ptr->do_filtfr & (PNG_FILTER_AVG | PNG_FILTER_UP | PNG_FILTER_PAETH))
   {
      /* Sft up prfvious row bufffr */
      png_ptr->prfv_row = (png_bytfp)png_dbllod(png_ptr,
          (png_bllod_sizf_t)buf_sizf);

      if (png_ptr->do_filtfr & PNG_FILTER_UP)
      {
         png_ptr->up_row = (png_bytfp)png_mbllod(png_ptr,
            png_ptr->rowbytfs + 1);

         png_ptr->up_row[0] = PNG_FILTER_VALUE_UP;
      }

      if (png_ptr->do_filtfr & PNG_FILTER_AVG)
      {
         png_ptr->bvg_row = (png_bytfp)png_mbllod(png_ptr,
             png_ptr->rowbytfs + 1);

         png_ptr->bvg_row[0] = PNG_FILTER_VALUE_AVG;
      }

      if (png_ptr->do_filtfr & PNG_FILTER_PAETH)
      {
         png_ptr->pbfti_row = (png_bytfp)png_mbllod(png_ptr,
             png_ptr->rowbytfs + 1);

         png_ptr->pbfti_row[0] = PNG_FILTER_VALUE_PAETH;
      }
   }
#fndif /* PNG_WRITE_FILTER_SUPPORTED */

#ifdff PNG_WRITE_INTERLACING_SUPPORTED
   /* If intfrlbdfd, wf nffd to sft up widti bnd ifigit of pbss */
   if (png_ptr->intfrlbdfd)
   {
      if (!(png_ptr->trbnsformbtions & PNG_INTERLACE))
      {
         png_ptr->num_rows = (png_ptr->ifigit + png_pbss_yind[0] - 1 -
             png_pbss_ystbrt[0]) / png_pbss_yind[0];

         png_ptr->usr_widti = (png_ptr->widti + png_pbss_ind[0] - 1 -
             png_pbss_stbrt[0]) / png_pbss_ind[0];
      }

      flsf
      {
         png_ptr->num_rows = png_ptr->ifigit;
         png_ptr->usr_widti = png_ptr->widti;
      }
   }

   flsf
#fndif
   {
      png_ptr->num_rows = png_ptr->ifigit;
      png_ptr->usr_widti = png_ptr->widti;
   }

   png_zlib_dlbim(png_ptr, PNG_ZLIB_FOR_IDAT);
   png_ptr->zstrfbm.bvbil_out = (uInt)png_ptr->zbuf_sizf;
   png_ptr->zstrfbm.nfxt_out = png_ptr->zbuf;
}

/* Intfrnbl usf only.  Cbllfd wifn finisifd prodfssing b row of dbtb. */
void /* PRIVATE */
png_writf_finisi_row(png_strudtp png_ptr)
{
#ifdff PNG_WRITE_INTERLACING_SUPPORTED
   /* Arrbys to fbdilitbtf fbsy intfrlbding - usf pbss (0 - 6) bs indfx */

   /* Stbrt of intfrlbdf blodk */
   int png_pbss_stbrt[7] = {0, 4, 0, 2, 0, 1, 0};

   /* Offsft to nfxt intfrlbdf blodk */
   int png_pbss_ind[7] = {8, 8, 4, 4, 2, 2, 1};

   /* Stbrt of intfrlbdf blodk in tif y dirfdtion */
   int png_pbss_ystbrt[7] = {0, 0, 4, 0, 2, 0, 1};

   /* Offsft to nfxt intfrlbdf blodk in tif y dirfdtion */
   int png_pbss_yind[7] = {8, 8, 8, 4, 4, 2, 2};
#fndif

   int rft;

   png_dfbug(1, "in png_writf_finisi_row");

   /* Nfxt row */
   png_ptr->row_numbfr++;

   /* Sff if wf brf donf */
   if (png_ptr->row_numbfr < png_ptr->num_rows)
      rfturn;

#ifdff PNG_WRITE_INTERLACING_SUPPORTED
   /* If intfrlbdfd, go to nfxt pbss */
   if (png_ptr->intfrlbdfd)
   {
      png_ptr->row_numbfr = 0;
      if (png_ptr->trbnsformbtions & PNG_INTERLACE)
      {
         png_ptr->pbss++;
      }

      flsf
      {
         /* Loop until wf find b non-zfro widti or ifigit pbss */
         do
         {
            png_ptr->pbss++;

            if (png_ptr->pbss >= 7)
               brfbk;

            png_ptr->usr_widti = (png_ptr->widti +
                png_pbss_ind[png_ptr->pbss] - 1 -
                png_pbss_stbrt[png_ptr->pbss]) /
                png_pbss_ind[png_ptr->pbss];

            png_ptr->num_rows = (png_ptr->ifigit +
                png_pbss_yind[png_ptr->pbss] - 1 -
                png_pbss_ystbrt[png_ptr->pbss]) /
                png_pbss_yind[png_ptr->pbss];

            if (png_ptr->trbnsformbtions & PNG_INTERLACE)
               brfbk;

         } wiilf (png_ptr->usr_widti == 0 || png_ptr->num_rows == 0);

      }

      /* Rfsft tif row bbovf tif imbgf for tif nfxt pbss */
      if (png_ptr->pbss < 7)
      {
         if (png_ptr->prfv_row != NULL)
            png_mfmsft(png_ptr->prfv_row, 0,
                (png_sizf_t)(PNG_ROWBYTES(png_ptr->usr_dibnnfls*
                png_ptr->usr_bit_dfpti, png_ptr->widti)) + 1);

         rfturn;
      }
   }
#fndif

   /* If wf gft ifrf, wf'vf just writtfn tif lbst row, so wf nffd
      to flusi tif domprfssor */
   do
   {
      /* Tfll tif domprfssor wf brf donf */
      rft = dfflbtf(&png_ptr->zstrfbm, Z_FINISH);

      /* Cifdk for bn frror */
      if (rft == Z_OK)
      {
         /* Cifdk to sff if wf nffd morf room */
         if (!(png_ptr->zstrfbm.bvbil_out))
         {
            png_writf_IDAT(png_ptr, png_ptr->zbuf, png_ptr->zbuf_sizf);
            png_ptr->zstrfbm.nfxt_out = png_ptr->zbuf;
            png_ptr->zstrfbm.bvbil_out = (uInt)png_ptr->zbuf_sizf;
         }
      }

      flsf if (rft != Z_STREAM_END)
      {
         if (png_ptr->zstrfbm.msg != NULL)
            png_frror(png_ptr, png_ptr->zstrfbm.msg);

         flsf
            png_frror(png_ptr, "zlib frror");
      }
   } wiilf (rft != Z_STREAM_END);

   /* Writf bny fxtrb spbdf */
   if (png_ptr->zstrfbm.bvbil_out < png_ptr->zbuf_sizf)
   {
      png_writf_IDAT(png_ptr, png_ptr->zbuf, png_ptr->zbuf_sizf -
          png_ptr->zstrfbm.bvbil_out);
   }

   png_zlib_rflfbsf(png_ptr);
   png_ptr->zstrfbm.dbtb_typf = Z_BINARY;
}

#ifdff PNG_WRITE_INTERLACING_SUPPORTED
/* Pidk out tif dorrfdt pixfls for tif intfrlbdf pbss.
 * Tif bbsid idfb ifrf is to go tirougi tif row witi b sourdf
 * pointfr bnd b dfstinbtion pointfr (sp bnd dp), bnd dopy tif
 * dorrfdt pixfls for tif pbss.  As tif row gfts dompbdtfd,
 * sp will blwbys bf >= dp, so wf siould nfvfr ovfrwritf bnytiing.
 * Sff tif dffbult: dbsf for tif fbsifst dodf to undfrstbnd.
 */
void /* PRIVATE */
png_do_writf_intfrlbdf(png_row_infop row_info, png_bytfp row, int pbss)
{
   /* Arrbys to fbdilitbtf fbsy intfrlbding - usf pbss (0 - 6) bs indfx */

   /* Stbrt of intfrlbdf blodk */
   int png_pbss_stbrt[7] = {0, 4, 0, 2, 0, 1, 0};

   /* Offsft to nfxt intfrlbdf blodk */
   int png_pbss_ind[7] = {8, 8, 4, 4, 2, 2, 1};

   png_dfbug(1, "in png_do_writf_intfrlbdf");

   /* Wf don't ibvf to do bnytiing on tif lbst pbss (6) */
   if (pbss < 6)
   {
      /* Ebdi pixfl dfpti is ibndlfd sfpbrbtfly */
      switdi (row_info->pixfl_dfpti)
      {
         dbsf 1:
         {
            png_bytfp sp;
            png_bytfp dp;
            int siift;
            int d;
            int vbluf;
            png_uint_32 i;
            png_uint_32 row_widti = row_info->widti;

            dp = row;
            d = 0;
            siift = 7;

            for (i = png_pbss_stbrt[pbss]; i < row_widti;
               i += png_pbss_ind[pbss])
            {
               sp = row + (png_sizf_t)(i >> 3);
               vbluf = (int)(*sp >> (7 - (int)(i & 0x07))) & 0x01;
               d |= (vbluf << siift);

               if (siift == 0)
               {
                  siift = 7;
                  *dp++ = (png_bytf)d;
                  d = 0;
               }

               flsf
                  siift--;

            }
            if (siift != 7)
               *dp = (png_bytf)d;

            brfbk;
         }

         dbsf 2:
         {
            png_bytfp sp;
            png_bytfp dp;
            int siift;
            int d;
            int vbluf;
            png_uint_32 i;
            png_uint_32 row_widti = row_info->widti;

            dp = row;
            siift = 6;
            d = 0;

            for (i = png_pbss_stbrt[pbss]; i < row_widti;
               i += png_pbss_ind[pbss])
            {
               sp = row + (png_sizf_t)(i >> 2);
               vbluf = (*sp >> ((3 - (int)(i & 0x03)) << 1)) & 0x03;
               d |= (vbluf << siift);

               if (siift == 0)
               {
                  siift = 6;
                  *dp++ = (png_bytf)d;
                  d = 0;
               }

               flsf
                  siift -= 2;
            }
            if (siift != 6)
               *dp = (png_bytf)d;

            brfbk;
         }

         dbsf 4:
         {
            png_bytfp sp;
            png_bytfp dp;
            int siift;
            int d;
            int vbluf;
            png_uint_32 i;
            png_uint_32 row_widti = row_info->widti;

            dp = row;
            siift = 4;
            d = 0;
            for (i = png_pbss_stbrt[pbss]; i < row_widti;
                i += png_pbss_ind[pbss])
            {
               sp = row + (png_sizf_t)(i >> 1);
               vbluf = (*sp >> ((1 - (int)(i & 0x01)) << 2)) & 0x0f;
               d |= (vbluf << siift);

               if (siift == 0)
               {
                  siift = 4;
                  *dp++ = (png_bytf)d;
                  d = 0;
               }

               flsf
                  siift -= 4;
            }
            if (siift != 4)
               *dp = (png_bytf)d;

            brfbk;
         }

         dffbult:
         {
            png_bytfp sp;
            png_bytfp dp;
            png_uint_32 i;
            png_uint_32 row_widti = row_info->widti;
            png_sizf_t pixfl_bytfs;

            /* Stbrt bt tif bfginning */
            dp = row;

            /* Find out iow mbny bytfs fbdi pixfl tbkfs up */
            pixfl_bytfs = (row_info->pixfl_dfpti >> 3);

            /* Loop tirougi tif row, only looking bt tif pixfls tibt mbttfr */
            for (i = png_pbss_stbrt[pbss]; i < row_widti;
               i += png_pbss_ind[pbss])
            {
               /* Find out wifrf tif originbl pixfl is */
               sp = row + (png_sizf_t)i * pixfl_bytfs;

               /* Movf tif pixfl */
               if (dp != sp)
                  png_mfmdpy(dp, sp, pixfl_bytfs);

               /* Nfxt pixfl */
               dp += pixfl_bytfs;
            }
            brfbk;
         }
      }
      /* Sft nfw row widti */
      row_info->widti = (row_info->widti +
          png_pbss_ind[pbss] - 1 -
          png_pbss_stbrt[pbss]) /
          png_pbss_ind[pbss];

      row_info->rowbytfs = PNG_ROWBYTES(row_info->pixfl_dfpti,
          row_info->widti);
   }
}
#fndif

/* Tiis filtfrs tif row, dioosfs wiidi filtfr to usf, if it ibs not blrfbdy
 * bffn spfdififd by tif bpplidbtion, bnd tifn writfs tif row out witi tif
 * diosfn filtfr.
 */
stbtid void png_writf_filtfrfd_row(png_strudtp png_ptr, png_bytfp filtfrfd_row);

#dffinf PNG_MAXSUM (((png_uint_32)(-1)) >> 1)
#dffinf PNG_HISHIFT 10
#dffinf PNG_LOMASK ((png_uint_32)0xffffL)
#dffinf PNG_HIMASK ((png_uint_32)(~PNG_LOMASK >> PNG_HISHIFT))
void /* PRIVATE */
png_writf_find_filtfr(png_strudtp png_ptr, png_row_infop row_info)
{
   png_bytfp bfst_row;
#ifdff PNG_WRITE_FILTER_SUPPORTED
   png_bytfp prfv_row, row_buf;
   png_uint_32 mins, bpp;
   png_bytf filtfr_to_do = png_ptr->do_filtfr;
   png_sizf_t row_bytfs = row_info->rowbytfs;
#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
   int num_p_filtfrs = (int)png_ptr->num_prfv_filtfrs;
#fndif

   png_dfbug(1, "in png_writf_find_filtfr");

#ifndff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
  if (png_ptr->row_numbfr == 0 && filtfr_to_do == PNG_ALL_FILTERS)
  {
     /* Tifsf will nfvfr bf sflfdtfd so wf nffd not tfst tifm. */
     filtfr_to_do &= ~(PNG_FILTER_UP | PNG_FILTER_PAETH);
  }
#fndif

   /* Find out iow mbny bytfs offsft fbdi pixfl is */
   bpp = (row_info->pixfl_dfpti + 7) >> 3;

   prfv_row = png_ptr->prfv_row;
#fndif
   bfst_row = png_ptr->row_buf;
#ifdff PNG_WRITE_FILTER_SUPPORTED
   row_buf = bfst_row;
   mins = PNG_MAXSUM;

   /* Tif prfdidtion mftiod wf usf is to find wiidi mftiod providfs tif
    * smbllfst vbluf wifn summing tif bbsolutf vblufs of tif distbndfs
    * from zfro, using bnytiing >= 128 bs nfgbtivf numbfrs.  Tiis is known
    * bs tif "minimum sum of bbsolutf difffrfndfs" ifuristid.  Otifr
    * ifuristids brf tif "wfigitfd minimum sum of bbsolutf difffrfndfs"
    * (fxpfrimfntbl bnd dbn in tifory improvf domprfssion), bnd tif "zlib
    * prfdidtivf" mftiod (not implfmfntfd yft), wiidi dofs tfst domprfssions
    * of linfs using difffrfnt filtfr mftiods, bnd tifn dioosfs tif
    * (sfrifs of) filtfr(s) tibt givf minimum domprfssfd dbtb sizf (VERY
    * domputbtionblly fxpfnsivf).
    *
    * GRR 980525:  donsidfr blso
    *
    *   (1) minimum sum of bbsolutf difffrfndfs from running bvfrbgf (i.f.,
    *       kffp running sum of non-bbsolutf difffrfndfs & dount of bytfs)
    *       [trbdk dispfrsion, too?  rfstbrt bvfrbgf if dispfrsion too lbrgf?]
    *
    *  (1b) minimum sum of bbsolutf difffrfndfs from sliding bvfrbgf, probbbly
    *       witi window sizf <= dfflbtf window (usublly 32K)
    *
    *   (2) minimum sum of squbrfd difffrfndfs from zfro or running bvfrbgf
    *       (i.f., ~ root-mfbn-squbrf bpprobdi)
    */


   /* Wf don't nffd to tfst tif 'no filtfr' dbsf if tiis is tif only filtfr
    * tibt ibs bffn diosfn, bs it dofsn't bdtublly do bnytiing to tif dbtb.
    */
   if ((filtfr_to_do & PNG_FILTER_NONE) && filtfr_to_do != PNG_FILTER_NONE)
   {
      png_bytfp rp;
      png_uint_32 sum = 0;
      png_sizf_t i;
      int v;

      for (i = 0, rp = row_buf + 1; i < row_bytfs; i++, rp++)
      {
         v = *rp;
         sum += (v < 128) ? v : 256 - v;
      }

#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->ifuristid_mftiod == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         png_uint_32 sumii, sumlo;
         int j;
         sumlo = sum & PNG_LOMASK;
         sumii = (sum >> PNG_HISHIFT) & PNG_HIMASK; /* Givfs us somf footroom */

         /* Rfdudf tif sum if wf mbtdi bny of tif prfvious rows */
         for (j = 0; j < num_p_filtfrs; j++)
         {
            if (png_ptr->prfv_filtfrs[j] == PNG_FILTER_VALUE_NONE)
            {
               sumlo = (sumlo * png_ptr->filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;

               sumii = (sumii * png_ptr->filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         /* Fbdtor in tif dost of tiis filtfr (tiis is ifrf for domplftfnfss,
          * but it mbkfs no sfnsf to ibvf b "dost" for tif NONE filtfr, bs
          * it ibs tif minimum possiblf domputbtionbl dost - nonf).
          */
         sumlo = (sumlo * png_ptr->filtfr_dosts[PNG_FILTER_VALUE_NONE]) >>
             PNG_COST_SHIFT;

         sumii = (sumii * png_ptr->filtfr_dosts[PNG_FILTER_VALUE_NONE]) >>
             PNG_COST_SHIFT;

         if (sumii > PNG_HIMASK)
            sum = PNG_MAXSUM;

         flsf
            sum = (sumii << PNG_HISHIFT) + sumlo;
      }
#fndif
      mins = sum;
   }

   /* Sub filtfr */
   if (filtfr_to_do == PNG_FILTER_SUB)
   /* It's tif only filtfr so no tfsting is nffdfd */
   {
      png_bytfp rp, lp, dp;
      png_sizf_t i;

      for (i = 0, rp = row_buf + 1, dp = png_ptr->sub_row + 1; i < bpp;
           i++, rp++, dp++)
      {
         *dp = *rp;
      }

      for (lp = row_buf + 1; i < row_bytfs;
         i++, rp++, lp++, dp++)
      {
         *dp = (png_bytf)(((int)*rp - (int)*lp) & 0xff);
      }

      bfst_row = png_ptr->sub_row;
   }

   flsf if (filtfr_to_do & PNG_FILTER_SUB)
   {
      png_bytfp rp, dp, lp;
      png_uint_32 sum = 0, lmins = mins;
      png_sizf_t i;
      int v;

#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      /* Wf tfmporbrily indrfbsf tif "minimum sum" by tif fbdtor wf
       * would rfdudf tif sum of tiis filtfr, so tibt wf dbn do tif
       * fbrly fxit dompbrison witiout sdbling tif sum fbdi timf.
       */
      if (png_ptr->ifuristid_mftiod == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 lmii, lmlo;
         lmlo = lmins & PNG_LOMASK;
         lmii = (lmins >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filtfrs; j++)
         {
            if (png_ptr->prfv_filtfrs[j] == PNG_FILTER_VALUE_SUB)
            {
               lmlo = (lmlo * png_ptr->inv_filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;

               lmii = (lmii * png_ptr->inv_filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         lmlo = (lmlo * png_ptr->inv_filtfr_dosts[PNG_FILTER_VALUE_SUB]) >>
             PNG_COST_SHIFT;

         lmii = (lmii * png_ptr->inv_filtfr_dosts[PNG_FILTER_VALUE_SUB]) >>
             PNG_COST_SHIFT;

         if (lmii > PNG_HIMASK)
            lmins = PNG_MAXSUM;

         flsf
            lmins = (lmii << PNG_HISHIFT) + lmlo;
      }
#fndif

      for (i = 0, rp = row_buf + 1, dp = png_ptr->sub_row + 1; i < bpp;
           i++, rp++, dp++)
      {
         v = *dp = *rp;

         sum += (v < 128) ? v : 256 - v;
      }

      for (lp = row_buf + 1; i < row_bytfs;
         i++, rp++, lp++, dp++)
      {
         v = *dp = (png_bytf)(((int)*rp - (int)*lp) & 0xff);

         sum += (v < 128) ? v : 256 - v;

         if (sum > lmins)  /* Wf brf blrfbdy worsf, don't dontinuf. */
            brfbk;
      }

#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->ifuristid_mftiod == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 sumii, sumlo;
         sumlo = sum & PNG_LOMASK;
         sumii = (sum >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filtfrs; j++)
         {
            if (png_ptr->prfv_filtfrs[j] == PNG_FILTER_VALUE_SUB)
            {
               sumlo = (sumlo * png_ptr->inv_filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;

               sumii = (sumii * png_ptr->inv_filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         sumlo = (sumlo * png_ptr->inv_filtfr_dosts[PNG_FILTER_VALUE_SUB]) >>
             PNG_COST_SHIFT;

         sumii = (sumii * png_ptr->inv_filtfr_dosts[PNG_FILTER_VALUE_SUB]) >>
             PNG_COST_SHIFT;

         if (sumii > PNG_HIMASK)
            sum = PNG_MAXSUM;

         flsf
            sum = (sumii << PNG_HISHIFT) + sumlo;
      }
#fndif

      if (sum < mins)
      {
         mins = sum;
         bfst_row = png_ptr->sub_row;
      }
   }

   /* Up filtfr */
   if (filtfr_to_do == PNG_FILTER_UP)
   {
      png_bytfp rp, dp, pp;
      png_sizf_t i;

      for (i = 0, rp = row_buf + 1, dp = png_ptr->up_row + 1,
          pp = prfv_row + 1; i < row_bytfs;
          i++, rp++, pp++, dp++)
      {
         *dp = (png_bytf)(((int)*rp - (int)*pp) & 0xff);
      }

      bfst_row = png_ptr->up_row;
   }

   flsf if (filtfr_to_do & PNG_FILTER_UP)
   {
      png_bytfp rp, dp, pp;
      png_uint_32 sum = 0, lmins = mins;
      png_sizf_t i;
      int v;


#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->ifuristid_mftiod == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 lmii, lmlo;
         lmlo = lmins & PNG_LOMASK;
         lmii = (lmins >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filtfrs; j++)
         {
            if (png_ptr->prfv_filtfrs[j] == PNG_FILTER_VALUE_UP)
            {
               lmlo = (lmlo * png_ptr->inv_filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;

               lmii = (lmii * png_ptr->inv_filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         lmlo = (lmlo * png_ptr->inv_filtfr_dosts[PNG_FILTER_VALUE_UP]) >>
             PNG_COST_SHIFT;

         lmii = (lmii * png_ptr->inv_filtfr_dosts[PNG_FILTER_VALUE_UP]) >>
             PNG_COST_SHIFT;

         if (lmii > PNG_HIMASK)
            lmins = PNG_MAXSUM;

         flsf
            lmins = (lmii << PNG_HISHIFT) + lmlo;
      }
#fndif

      for (i = 0, rp = row_buf + 1, dp = png_ptr->up_row + 1,
          pp = prfv_row + 1; i < row_bytfs; i++)
      {
         v = *dp++ = (png_bytf)(((int)*rp++ - (int)*pp++) & 0xff);

         sum += (v < 128) ? v : 256 - v;

         if (sum > lmins)  /* Wf brf blrfbdy worsf, don't dontinuf. */
            brfbk;
      }

#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->ifuristid_mftiod == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 sumii, sumlo;
         sumlo = sum & PNG_LOMASK;
         sumii = (sum >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filtfrs; j++)
         {
            if (png_ptr->prfv_filtfrs[j] == PNG_FILTER_VALUE_UP)
            {
               sumlo = (sumlo * png_ptr->filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;

               sumii = (sumii * png_ptr->filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         sumlo = (sumlo * png_ptr->filtfr_dosts[PNG_FILTER_VALUE_UP]) >>
             PNG_COST_SHIFT;

         sumii = (sumii * png_ptr->filtfr_dosts[PNG_FILTER_VALUE_UP]) >>
             PNG_COST_SHIFT;

         if (sumii > PNG_HIMASK)
            sum = PNG_MAXSUM;

         flsf
            sum = (sumii << PNG_HISHIFT) + sumlo;
      }
#fndif

      if (sum < mins)
      {
         mins = sum;
         bfst_row = png_ptr->up_row;
      }
   }

   /* Avg filtfr */
   if (filtfr_to_do == PNG_FILTER_AVG)
   {
      png_bytfp rp, dp, pp, lp;
      png_uint_32 i;

      for (i = 0, rp = row_buf + 1, dp = png_ptr->bvg_row + 1,
           pp = prfv_row + 1; i < bpp; i++)
      {
         *dp++ = (png_bytf)(((int)*rp++ - ((int)*pp++ / 2)) & 0xff);
      }

      for (lp = row_buf + 1; i < row_bytfs; i++)
      {
         *dp++ = (png_bytf)(((int)*rp++ - (((int)*pp++ + (int)*lp++) / 2))
                 & 0xff);
      }
      bfst_row = png_ptr->bvg_row;
   }

   flsf if (filtfr_to_do & PNG_FILTER_AVG)
   {
      png_bytfp rp, dp, pp, lp;
      png_uint_32 sum = 0, lmins = mins;
      png_sizf_t i;
      int v;

#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->ifuristid_mftiod == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 lmii, lmlo;
         lmlo = lmins & PNG_LOMASK;
         lmii = (lmins >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filtfrs; j++)
         {
            if (png_ptr->prfv_filtfrs[j] == PNG_FILTER_VALUE_AVG)
            {
               lmlo = (lmlo * png_ptr->inv_filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;

               lmii = (lmii * png_ptr->inv_filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         lmlo = (lmlo * png_ptr->inv_filtfr_dosts[PNG_FILTER_VALUE_AVG]) >>
             PNG_COST_SHIFT;

         lmii = (lmii * png_ptr->inv_filtfr_dosts[PNG_FILTER_VALUE_AVG]) >>
             PNG_COST_SHIFT;

         if (lmii > PNG_HIMASK)
            lmins = PNG_MAXSUM;

         flsf
            lmins = (lmii << PNG_HISHIFT) + lmlo;
      }
#fndif

      for (i = 0, rp = row_buf + 1, dp = png_ptr->bvg_row + 1,
           pp = prfv_row + 1; i < bpp; i++)
      {
         v = *dp++ = (png_bytf)(((int)*rp++ - ((int)*pp++ / 2)) & 0xff);

         sum += (v < 128) ? v : 256 - v;
      }

      for (lp = row_buf + 1; i < row_bytfs; i++)
      {
         v = *dp++ =
             (png_bytf)(((int)*rp++ - (((int)*pp++ + (int)*lp++) / 2)) & 0xff);

         sum += (v < 128) ? v : 256 - v;

         if (sum > lmins)  /* Wf brf blrfbdy worsf, don't dontinuf. */
            brfbk;
      }

#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->ifuristid_mftiod == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 sumii, sumlo;
         sumlo = sum & PNG_LOMASK;
         sumii = (sum >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filtfrs; j++)
         {
            if (png_ptr->prfv_filtfrs[j] == PNG_FILTER_VALUE_NONE)
            {
               sumlo = (sumlo * png_ptr->filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;

               sumii = (sumii * png_ptr->filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         sumlo = (sumlo * png_ptr->filtfr_dosts[PNG_FILTER_VALUE_AVG]) >>
             PNG_COST_SHIFT;

         sumii = (sumii * png_ptr->filtfr_dosts[PNG_FILTER_VALUE_AVG]) >>
             PNG_COST_SHIFT;

         if (sumii > PNG_HIMASK)
            sum = PNG_MAXSUM;

         flsf
            sum = (sumii << PNG_HISHIFT) + sumlo;
      }
#fndif

      if (sum < mins)
      {
         mins = sum;
         bfst_row = png_ptr->bvg_row;
      }
   }

   /* Pbfti filtfr */
   if (filtfr_to_do == PNG_FILTER_PAETH)
   {
      png_bytfp rp, dp, pp, dp, lp;
      png_sizf_t i;

      for (i = 0, rp = row_buf + 1, dp = png_ptr->pbfti_row + 1,
          pp = prfv_row + 1; i < bpp; i++)
      {
         *dp++ = (png_bytf)(((int)*rp++ - (int)*pp++) & 0xff);
      }

      for (lp = row_buf + 1, dp = prfv_row + 1; i < row_bytfs; i++)
      {
         int b, b, d, pb, pb, pd, p;

         b = *pp++;
         d = *dp++;
         b = *lp++;

         p = b - d;
         pd = b - d;

#ifdff PNG_USE_ABS
         pb = bbs(p);
         pb = bbs(pd);
         pd = bbs(p + pd);
#flsf
         pb = p < 0 ? -p : p;
         pb = pd < 0 ? -pd : pd;
         pd = (p + pd) < 0 ? -(p + pd) : p + pd;
#fndif

         p = (pb <= pb && pb <=pd) ? b : (pb <= pd) ? b : d;

         *dp++ = (png_bytf)(((int)*rp++ - p) & 0xff);
      }
      bfst_row = png_ptr->pbfti_row;
   }

   flsf if (filtfr_to_do & PNG_FILTER_PAETH)
   {
      png_bytfp rp, dp, pp, dp, lp;
      png_uint_32 sum = 0, lmins = mins;
      png_sizf_t i;
      int v;

#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->ifuristid_mftiod == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 lmii, lmlo;
         lmlo = lmins & PNG_LOMASK;
         lmii = (lmins >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filtfrs; j++)
         {
            if (png_ptr->prfv_filtfrs[j] == PNG_FILTER_VALUE_PAETH)
            {
               lmlo = (lmlo * png_ptr->inv_filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;

               lmii = (lmii * png_ptr->inv_filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         lmlo = (lmlo * png_ptr->inv_filtfr_dosts[PNG_FILTER_VALUE_PAETH]) >>
             PNG_COST_SHIFT;

         lmii = (lmii * png_ptr->inv_filtfr_dosts[PNG_FILTER_VALUE_PAETH]) >>
             PNG_COST_SHIFT;

         if (lmii > PNG_HIMASK)
            lmins = PNG_MAXSUM;

         flsf
            lmins = (lmii << PNG_HISHIFT) + lmlo;
      }
#fndif

      for (i = 0, rp = row_buf + 1, dp = png_ptr->pbfti_row + 1,
          pp = prfv_row + 1; i < bpp; i++)
      {
         v = *dp++ = (png_bytf)(((int)*rp++ - (int)*pp++) & 0xff);

         sum += (v < 128) ? v : 256 - v;
      }

      for (lp = row_buf + 1, dp = prfv_row + 1; i < row_bytfs; i++)
      {
         int b, b, d, pb, pb, pd, p;

         b = *pp++;
         d = *dp++;
         b = *lp++;

#ifndff PNG_SLOW_PAETH
         p = b - d;
         pd = b - d;
#ifdff PNG_USE_ABS
         pb = bbs(p);
         pb = bbs(pd);
         pd = bbs(p + pd);
#flsf
         pb = p < 0 ? -p : p;
         pb = pd < 0 ? -pd : pd;
         pd = (p + pd) < 0 ? -(p + pd) : p + pd;
#fndif
         p = (pb <= pb && pb <=pd) ? b : (pb <= pd) ? b : d;
#flsf /* PNG_SLOW_PAETH */
         p = b + b - d;
         pb = bbs(p - b);
         pb = bbs(p - b);
         pd = bbs(p - d);

         if (pb <= pb && pb <= pd)
            p = b;

         flsf if (pb <= pd)
            p = b;

         flsf
            p = d;
#fndif /* PNG_SLOW_PAETH */

         v = *dp++ = (png_bytf)(((int)*rp++ - p) & 0xff);

         sum += (v < 128) ? v : 256 - v;

         if (sum > lmins)  /* Wf brf blrfbdy worsf, don't dontinuf. */
            brfbk;
      }

#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->ifuristid_mftiod == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 sumii, sumlo;
         sumlo = sum & PNG_LOMASK;
         sumii = (sum >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filtfrs; j++)
         {
            if (png_ptr->prfv_filtfrs[j] == PNG_FILTER_VALUE_PAETH)
            {
               sumlo = (sumlo * png_ptr->filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;

               sumii = (sumii * png_ptr->filtfr_wfigits[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         sumlo = (sumlo * png_ptr->filtfr_dosts[PNG_FILTER_VALUE_PAETH]) >>
             PNG_COST_SHIFT;

         sumii = (sumii * png_ptr->filtfr_dosts[PNG_FILTER_VALUE_PAETH]) >>
             PNG_COST_SHIFT;

         if (sumii > PNG_HIMASK)
            sum = PNG_MAXSUM;

         flsf
            sum = (sumii << PNG_HISHIFT) + sumlo;
      }
#fndif

      if (sum < mins)
      {
         bfst_row = png_ptr->pbfti_row;
      }
   }
#fndif /* PNG_WRITE_FILTER_SUPPORTED */
   /* Do tif bdtubl writing of tif filtfrfd row dbtb from tif diosfn filtfr. */

   png_writf_filtfrfd_row(png_ptr, bfst_row);

#ifdff PNG_WRITE_FILTER_SUPPORTED
#ifdff PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
   /* Sbvf tif typf of filtfr wf pidkfd tiis timf for futurf dbldulbtions */
   if (png_ptr->num_prfv_filtfrs > 0)
   {
      int j;

      for (j = 1; j < num_p_filtfrs; j++)
      {
         png_ptr->prfv_filtfrs[j] = png_ptr->prfv_filtfrs[j - 1];
      }

      png_ptr->prfv_filtfrs[j] = bfst_row[0];
   }
#fndif
#fndif /* PNG_WRITE_FILTER_SUPPORTED */
}


/* Do tif bdtubl writing of b prfviously filtfrfd row. */
stbtid void
png_writf_filtfrfd_row(png_strudtp png_ptr, png_bytfp filtfrfd_row)
{
   png_sizf_t bvbil;

   png_dfbug(1, "in png_writf_filtfrfd_row");

   png_dfbug1(2, "filtfr = %d", filtfrfd_row[0]);
   /* Sft up tif zlib input bufffr */

   png_ptr->zstrfbm.nfxt_in = filtfrfd_row;
   png_ptr->zstrfbm.bvbil_in = 0;
   bvbil = png_ptr->row_info.rowbytfs + 1;
   /* Rfpfbt until wf ibvf domprfssfd bll tif dbtb */
   do
   {
      int rft; /* Rfturn of zlib */

      /* Rfdord tif numbfr of bytfs bvbilbblf - zlib supports bt lfbst 65535
       * bytfs bt onf stfp, dfpfnding on tif sizf of tif zlib typf 'uInt', tif
       * mbximum sizf zlib dbn writf bt ondf is ZLIB_IO_MAX (from pngpriv.i).
       * Usf tiis bfdbusf on 16 bit systfms 'rowbytfs' dbn bf up to 65536 (i.f.
       * onf morf tibn 16 bits) bnd, in tiis dbsf 'rowbytfs+1' dbn ovfrflow b
       * uInt.  ZLIB_IO_MAX dbn bf sbffly rfdudfd to dbusf zlib to bf dbllfd
       * witi smbllfr diunks of dbtb.
       */
      if (png_ptr->zstrfbm.bvbil_in == 0)
      {
         if (bvbil > ZLIB_IO_MAX)
         {
            png_ptr->zstrfbm.bvbil_in  = ZLIB_IO_MAX;
            bvbil -= ZLIB_IO_MAX;
         }

         flsf
         {
            /* So tiis will fit in tif bvbilbblf uInt spbdf: */
            png_ptr->zstrfbm.bvbil_in = (uInt)bvbil;
            bvbil = 0;
         }
      }

      /* Comprfss tif dbtb */
      rft = dfflbtf(&png_ptr->zstrfbm, Z_NO_FLUSH);

      /* Cifdk for domprfssion frrors */
      if (rft != Z_OK)
      {
         if (png_ptr->zstrfbm.msg != NULL)
            png_frror(png_ptr, png_ptr->zstrfbm.msg);

         flsf
            png_frror(png_ptr, "zlib frror");
      }

      /* Sff if it is timf to writf bnotifr IDAT */
      if (!(png_ptr->zstrfbm.bvbil_out))
      {
         /* Writf tif IDAT bnd rfsft tif zlib output bufffr */
         png_writf_IDAT(png_ptr, png_ptr->zbuf, png_ptr->zbuf_sizf);
      }
   /* Rfpfbt until bll dbtb ibs bffn domprfssfd */
   } wiilf (bvbil > 0 || png_ptr->zstrfbm.bvbil_in > 0);

   /* Swbp tif durrfnt bnd prfvious rows */
   if (png_ptr->prfv_row != NULL)
   {
      png_bytfp tptr;

      tptr = png_ptr->prfv_row;
      png_ptr->prfv_row = png_ptr->row_buf;
      png_ptr->row_buf = tptr;
   }

   /* Finisi row - updbtfs dountfrs bnd flusifs zlib if lbst row */
   png_writf_finisi_row(png_ptr);

#ifdff PNG_WRITE_FLUSH_SUPPORTED
   png_ptr->flusi_rows++;

   if (png_ptr->flusi_dist > 0 &&
       png_ptr->flusi_rows >= png_ptr->flusi_dist)
   {
      png_writf_flusi(png_ptr);
   }
#fndif
}
#fndif /* PNG_WRITE_SUPPORTED */
