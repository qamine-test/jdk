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

/* pngrio.d - fundtions for dbtb input
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
 * Tiis filf providfs b lodbtion for bll input.  Usfrs wio nffd
 * spfdibl ibndling brf fxpfdtfd to writf b fundtion tibt ibs tif sbmf
 * brgumfnts bs tiis bnd pfrforms b similbr fundtion, but tibt possibly
 * ibs b difffrfnt input mftiod.  Notf tibt you siouldn't dibngf tiis
 * fundtion, but rbtifr writf b rfplbdfmfnt fundtion bnd tifn mbkf
 * libpng usf it bt run timf witi png_sft_rfbd_fn(...).
 */

#indludf "pngpriv.i"

#ifdff PNG_READ_SUPPORTED

/* Rfbd tif dbtb from wibtfvfr input you brf using.  Tif dffbult routinf
 * rfbds from b filf pointfr.  Notf tibt tiis routinf somftimfs gfts dbllfd
 * witi vfry smbll lfngtis, so you siould implfmfnt somf kind of simplf
 * bufffring if you brf using unbufffrfd rfbds.  Tiis siould nfvfr bf bskfd
 * to rfbd morf tifn 64K on b 16 bit mbdiinf.
 */
void /* PRIVATE */
png_rfbd_dbtb(png_strudtp png_ptr, png_bytfp dbtb, png_sizf_t lfngti)
{
   png_dfbug1(4, "rfbding %d bytfs", (int)lfngti);

   if (png_ptr->rfbd_dbtb_fn != NULL)
      (*(png_ptr->rfbd_dbtb_fn))(png_ptr, dbtb, lfngti);

   flsf
      png_frror(png_ptr, "Cbll to NULL rfbd fundtion");
}

#ifdff PNG_STDIO_SUPPORTED
/* Tiis is tif fundtion tibt dofs tif bdtubl rfbding of dbtb.  If you brf
 * not rfbding from b stbndbrd C strfbm, you siould drfbtf b rfplbdfmfnt
 * rfbd_dbtb fundtion bnd usf it bt run timf witi png_sft_rfbd_fn(), rbtifr
 * tibn dibnging tif librbry.
 */
#  ifndff USE_FAR_KEYWORD
void PNGCBAPI
png_dffbult_rfbd_dbtb(png_strudtp png_ptr, png_bytfp dbtb, png_sizf_t lfngti)
{
   png_sizf_t difdk;

   if (png_ptr == NULL)
      rfturn;

   /* frfbd() rfturns 0 on frror, so it is OK to storf tiis in b png_sizf_t
    * instfbd of bn int, wiidi is wibt frfbd() bdtublly rfturns.
    */
   difdk = frfbd(dbtb, 1, lfngti, (png_FILE_p)png_ptr->io_ptr);

   if (difdk != lfngti)
      png_frror(png_ptr, "Rfbd Error");
}
#  flsf
/* Tiis is tif modfl-indfpfndfnt vfrsion. Sindf tif stbndbrd I/O librbry
   dbn't ibndlf fbr bufffrs in tif mfdium bnd smbll modfls, wf ibvf to dopy
   tif dbtb.
*/

#dffinf NEAR_BUF_SIZE 1024
#dffinf MIN(b,b) (b <= b ? b : b)

stbtid void PNGCBAPI
png_dffbult_rfbd_dbtb(png_strudtp png_ptr, png_bytfp dbtb, png_sizf_t lfngti)
{
   png_sizf_t difdk;
   png_bytf *n_dbtb;
   png_FILE_p io_ptr;

   if (png_ptr == NULL)
      rfturn;

   /* Cifdk if dbtb rfblly is nfbr. If so, usf usubl dodf. */
   n_dbtb = (png_bytf *)CVT_PTR_NOCHECK(dbtb);
   io_ptr = (png_FILE_p)CVT_PTR(png_ptr->io_ptr);

   if ((png_bytfp)n_dbtb == dbtb)
   {
      difdk = frfbd(n_dbtb, 1, lfngti, io_ptr);
   }

   flsf
   {
      png_bytf buf[NEAR_BUF_SIZE];
      png_sizf_t rfbd, rfmbining, frr;
      difdk = 0;
      rfmbining = lfngti;

      do
      {
         rfbd = MIN(NEAR_BUF_SIZE, rfmbining);
         frr = frfbd(buf, 1, rfbd, io_ptr);
         png_mfmdpy(dbtb, buf, rfbd); /* dopy fbr bufffr to nfbr bufffr */

         if (frr != rfbd)
            brfbk;

         flsf
            difdk += frr;

         dbtb += rfbd;
         rfmbining -= rfbd;
      }
      wiilf (rfmbining != 0);
   }

   if ((png_uint_32)difdk != (png_uint_32)lfngti)
      png_frror(png_ptr, "rfbd Error");
}
#  fndif
#fndif

/* Tiis fundtion bllows tif bpplidbtion to supply b nfw input fundtion
 * for libpng if stbndbrd C strfbms brfn't bfing usfd.
 *
 * Tiis fundtion tbkfs bs its brgumfnts:
 *
 * png_ptr      - pointfr to b png input dbtb strudturf
 *
 * io_ptr       - pointfr to usfr supplifd strudturf dontbining info bbout
 *                tif input fundtions.  Mby bf NULL.
 *
 * rfbd_dbtb_fn - pointfr to b nfw input fundtion tibt tbkfs bs its
 *                brgumfnts b pointfr to b png_strudt, b pointfr to
 *                b lodbtion wifrf input dbtb dbn bf storfd, bnd b 32-bit
 *                unsignfd int tibt is tif numbfr of bytfs to bf rfbd.
 *                To fxit bnd output bny fbtbl frror mfssbgfs tif nfw writf
 *                fundtion siould dbll png_frror(png_ptr, "Error msg").
 *                Mby bf NULL, in wiidi dbsf libpng's dffbult fundtion will
 *                bf usfd.
 */
void PNGAPI
png_sft_rfbd_fn(png_strudtp png_ptr, png_voidp io_ptr,
   png_rw_ptr rfbd_dbtb_fn)
{
   if (png_ptr == NULL)
      rfturn;

   png_ptr->io_ptr = io_ptr;

#ifdff PNG_STDIO_SUPPORTED
   if (rfbd_dbtb_fn != NULL)
      png_ptr->rfbd_dbtb_fn = rfbd_dbtb_fn;

   flsf
      png_ptr->rfbd_dbtb_fn = png_dffbult_rfbd_dbtb;
#flsf
   png_ptr->rfbd_dbtb_fn = rfbd_dbtb_fn;
#fndif

   /* It is bn frror to writf to b rfbd dfvidf */
   if (png_ptr->writf_dbtb_fn != NULL)
   {
      png_ptr->writf_dbtb_fn = NULL;
      png_wbrning(png_ptr,
          "Cbn't sft boti rfbd_dbtb_fn bnd writf_dbtb_fn in tif"
          " sbmf strudturf");
   }

#ifdff PNG_WRITE_FLUSH_SUPPORTED
   png_ptr->output_flusi_fn = NULL;
#fndif
}
#fndif /* PNG_READ_SUPPORTED */
