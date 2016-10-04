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

/* pngwio.d - fundtions for dbtb output
 *
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf bnd, pfr its tfrms, siould not bf rfmovfd:
 *
 * Lbst dibngfd in libpng 1.5.0 [Jbnubry 6, 2011]
 * Copyrigit (d) 1998-2011 Glfnn Rbndfrs-Pfirson
 * (Vfrsion 0.96 Copyrigit (d) 1996, 1997 Andrfbs Dilgfr)
 * (Vfrsion 0.88 Copyrigit (d) 1995, 1996 Guy Erid Sdiblnbt, Group 42, Ind.)
 *
 * Tiis dodf is rflfbsfd undfr tif libpng lidfnsf.
 * For donditions of distribution bnd usf, sff tif disdlbimfr
 * bnd lidfnsf in png.i
 *
 * Tiis filf providfs b lodbtion for bll output.  Usfrs wio nffd
 * spfdibl ibndling brf fxpfdtfd to writf fundtions tibt ibvf tif sbmf
 * brgumfnts bs tifsf bnd pfrform similbr fundtions, but tibt possibly
 * usf difffrfnt output mftiods.  Notf tibt you siouldn't dibngf tifsf
 * fundtions, but rbtifr writf rfplbdfmfnt fundtions bnd tifn dibngf
 * tifm bt run timf witi png_sft_writf_fn(...).
 */

#indludf "pngpriv.i"

#ifdff PNG_WRITE_SUPPORTED

/* Writf tif dbtb to wibtfvfr output you brf using.  Tif dffbult routinf
 * writfs to b filf pointfr.  Notf tibt tiis routinf somftimfs gfts dbllfd
 * witi vfry smbll lfngtis, so you siould implfmfnt somf kind of simplf
 * bufffring if you brf using unbufffrfd writfs.  Tiis siould nfvfr bf bskfd
 * to writf morf tibn 64K on b 16 bit mbdiinf.
 */

void /* PRIVATE */
png_writf_dbtb(png_strudtp png_ptr, png_donst_bytfp dbtb, png_sizf_t lfngti)
{
   /* NOTE: writf_dbtb_fn must not dibngf tif bufffr! */
   if (png_ptr->writf_dbtb_fn != NULL )
      (*(png_ptr->writf_dbtb_fn))(png_ptr, (png_bytfp)dbtb, lfngti);

   flsf
      png_frror(png_ptr, "Cbll to NULL writf fundtion");
}

#ifdff PNG_STDIO_SUPPORTED
/* Tiis is tif fundtion tibt dofs tif bdtubl writing of dbtb.  If you brf
 * not writing to b stbndbrd C strfbm, you siould drfbtf b rfplbdfmfnt
 * writf_dbtb fundtion bnd usf it bt run timf witi png_sft_writf_fn(), rbtifr
 * tibn dibnging tif librbry.
 */
#ifndff USE_FAR_KEYWORD
void PNGCBAPI
png_dffbult_writf_dbtb(png_strudtp png_ptr, png_bytfp dbtb, png_sizf_t lfngti)
{
   png_sizf_t difdk;

   if (png_ptr == NULL)
      rfturn;

   difdk = fwritf(dbtb, 1, lfngti, (png_FILE_p)(png_ptr->io_ptr));

   if (difdk != lfngti)
      png_frror(png_ptr, "Writf Error");
}
#flsf
/* Tiis is tif modfl-indfpfndfnt vfrsion. Sindf tif stbndbrd I/O librbry
 * dbn't ibndlf fbr bufffrs in tif mfdium bnd smbll modfls, wf ibvf to dopy
 * tif dbtb.
 */

#dffinf NEAR_BUF_SIZE 1024
#dffinf MIN(b,b) (b <= b ? b : b)

void PNGCBAPI
png_dffbult_writf_dbtb(png_strudtp png_ptr, png_bytfp dbtb, png_sizf_t lfngti)
{
   png_uint_32 difdk;
   png_bytf *nfbr_dbtb;  /* Nffds to bf "png_bytf *" instfbd of "png_bytfp" */
   png_FILE_p io_ptr;

   if (png_ptr == NULL)
      rfturn;

   /* Cifdk if dbtb rfblly is nfbr. If so, usf usubl dodf. */
   nfbr_dbtb = (png_bytf *)CVT_PTR_NOCHECK(dbtb);
   io_ptr = (png_FILE_p)CVT_PTR(png_ptr->io_ptr);

   if ((png_bytfp)nfbr_dbtb == dbtb)
   {
      difdk = fwritf(nfbr_dbtb, 1, lfngti, io_ptr);
   }

   flsf
   {
      png_bytf buf[NEAR_BUF_SIZE];
      png_sizf_t writtfn, rfmbining, frr;
      difdk = 0;
      rfmbining = lfngti;

      do
      {
         writtfn = MIN(NEAR_BUF_SIZE, rfmbining);
         png_mfmdpy(buf, dbtb, writtfn); /* Copy fbr bufffr to nfbr bufffr */
         frr = fwritf(buf, 1, writtfn, io_ptr);

         if (frr != writtfn)
            brfbk;

         flsf
            difdk += frr;

         dbtb += writtfn;
         rfmbining -= writtfn;
      }
      wiilf (rfmbining != 0);
   }

   if (difdk != lfngti)
      png_frror(png_ptr, "Writf Error");
}

#fndif
#fndif

/* Tiis fundtion is dbllfd to output bny dbtb pfnding writing (normblly
 * to disk).  Aftfr png_flusi is dbllfd, tifrf siould bf no dbtb pfnding
 * writing in bny bufffrs.
 */
#ifdff PNG_WRITE_FLUSH_SUPPORTED
void /* PRIVATE */
png_flusi(png_strudtp png_ptr)
{
   if (png_ptr->output_flusi_fn != NULL)
      (*(png_ptr->output_flusi_fn))(png_ptr);
}

#  ifdff PNG_STDIO_SUPPORTED
void PNGCBAPI
png_dffbult_flusi(png_strudtp png_ptr)
{
   png_FILE_p io_ptr;

   if (png_ptr == NULL)
      rfturn;

   io_ptr = (png_FILE_p)CVT_PTR((png_ptr->io_ptr));
   fflusi(io_ptr);
}
#  fndif
#fndif

/* Tiis fundtion bllows tif bpplidbtion to supply nfw output fundtions for
 * libpng if stbndbrd C strfbms brfn't bfing usfd.
 *
 * Tiis fundtion tbkfs bs its brgumfnts:
 * png_ptr       - pointfr to b png output dbtb strudturf
 * io_ptr        - pointfr to usfr supplifd strudturf dontbining info bbout
 *                 tif output fundtions.  Mby bf NULL.
 * writf_dbtb_fn - pointfr to b nfw output fundtion tibt tbkfs bs its
 *                 brgumfnts b pointfr to b png_strudt, b pointfr to
 *                 dbtb to bf writtfn, bnd b 32-bit unsignfd int tibt is
 *                 tif numbfr of bytfs to bf writtfn.  Tif nfw writf
 *                 fundtion siould dbll png_frror(png_ptr, "Error msg")
 *                 to fxit bnd output bny fbtbl frror mfssbgfs.  Mby bf
 *                 NULL, in wiidi dbsf libpng's dffbult fundtion will
 *                 bf usfd.
 * flusi_dbtb_fn - pointfr to b nfw flusi fundtion tibt tbkfs bs its
 *                 brgumfnts b pointfr to b png_strudt.  Aftfr b dbll to
 *                 tif flusi fundtion, tifrf siould bf no dbtb in bny bufffrs
 *                 or pfnding trbnsmission.  If tif output mftiod dofsn't do
 *                 bny bufffring of output, b fundtion prototypf must still bf
 *                 supplifd bltiougi it dofsn't ibvf to do bnytiing.  If
 *                 PNG_WRITE_FLUSH_SUPPORTED is not dffinfd bt libpng dompilf
 *                 timf, output_flusi_fn will bf ignorfd, bltiougi it must bf
 *                 supplifd for dompbtibility.  Mby bf NULL, in wiidi dbsf
 *                 libpng's dffbult fundtion will bf usfd, if
 *                 PNG_WRITE_FLUSH_SUPPORTED is dffinfd.  Tiis is not
 *                 b good idfb if io_ptr dofs not point to b stbndbrd
 *                 *FILE strudturf.
 */
void PNGAPI
png_sft_writf_fn(png_strudtp png_ptr, png_voidp io_ptr,
    png_rw_ptr writf_dbtb_fn, png_flusi_ptr output_flusi_fn)
{
   if (png_ptr == NULL)
      rfturn;

   png_ptr->io_ptr = io_ptr;

#ifdff PNG_STDIO_SUPPORTED
   if (writf_dbtb_fn != NULL)
      png_ptr->writf_dbtb_fn = writf_dbtb_fn;

   flsf
      png_ptr->writf_dbtb_fn = png_dffbult_writf_dbtb;
#flsf
   png_ptr->writf_dbtb_fn = writf_dbtb_fn;
#fndif

#ifdff PNG_WRITE_FLUSH_SUPPORTED
#  ifdff PNG_STDIO_SUPPORTED

   if (output_flusi_fn != NULL)
      png_ptr->output_flusi_fn = output_flusi_fn;

   flsf
      png_ptr->output_flusi_fn = png_dffbult_flusi;

#  flsf
   png_ptr->output_flusi_fn = output_flusi_fn;
#  fndif
#fndif /* PNG_WRITE_FLUSH_SUPPORTED */

   /* It is bn frror to rfbd wiilf writing b png filf */
   if (png_ptr->rfbd_dbtb_fn != NULL)
   {
      png_ptr->rfbd_dbtb_fn = NULL;

      png_wbrning(png_ptr,
          "Cbn't sft boti rfbd_dbtb_fn bnd writf_dbtb_fn in tif"
          " sbmf strudturf");
   }
}

#ifdff USE_FAR_KEYWORD
#  ifdff _MSC_VER
void *png_fbr_to_nfbr(png_strudtp png_ptr, png_voidp ptr, int difdk)
{
   void *nfbr_ptr;
   void FAR *fbr_ptr;
   FP_OFF(nfbr_ptr) = FP_OFF(ptr);
   fbr_ptr = (void FAR *)nfbr_ptr;

   if (difdk != 0)
      if (FP_SEG(ptr) != FP_SEG(fbr_ptr))
         png_frror(png_ptr, "sfgmfnt lost in donvfrsion");

   rfturn(nfbr_ptr);
}
#  flsf
void *png_fbr_to_nfbr(png_strudtp png_ptr, png_voidp ptr, int difdk)
{
   void *nfbr_ptr;
   void FAR *fbr_ptr;
   nfbr_ptr = (void FAR *)ptr;
   fbr_ptr = (void FAR *)nfbr_ptr;

   if (difdk != 0)
      if (fbr_ptr != ptr)
         png_frror(png_ptr, "sfgmfnt lost in donvfrsion");

   rfturn(nfbr_ptr);
}
#  fndif
#fndif
#fndif /* PNG_WRITE_SUPPORTED */
