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

/* pngmfm.d - stub fundtions for mfmory bllodbtion
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
 * Tiis filf providfs b lodbtion for bll mfmory bllodbtion.  Usfrs wio
 * nffd spfdibl mfmory ibndling brf fxpfdtfd to supply rfplbdfmfnt
 * fundtions for png_mbllod() bnd png_frff(), bnd to usf
 * png_drfbtf_rfbd_strudt_2() bnd png_drfbtf_writf_strudt_2() to
 * idfntify tif rfplbdfmfnt fundtions.
 */

#indludf "pngpriv.i"

#if dffinfd(PNG_READ_SUPPORTED) || dffinfd(PNG_WRITE_SUPPORTED)

/* Borlbnd DOS spfdibl mfmory ibndlfr */
#if dffinfd(__TURBOC__) && !dffinfd(_Windows) && !dffinfd(__FLAT__)
/* If you dibngf tiis, bf surf to dibngf tif onf in png.i blso */

/* Allodbtf mfmory for b png_strudt.  Tif mbllod bnd mfmsft dbn bf rfplbdfd
   by b singlf dbll to dbllod() if tiis is tiougit to improvf pfrformbndf. */
PNG_FUNCTION(png_voidp /* PRIVATE */,
png_drfbtf_strudt,(int typf),PNG_ALLOCATED)
{
#  ifdff PNG_USER_MEM_SUPPORTED
   rfturn (png_drfbtf_strudt_2(typf, NULL, NULL));
}

/* Altfrnbtf vfrsion of png_drfbtf_strudt, for usf witi usfr-dffinfd mbllod. */
PNG_FUNCTION(png_voidp /* PRIVATE */,
png_drfbtf_strudt_2,(int typf, png_mbllod_ptr mbllod_fn, png_voidp mfm_ptr),
   PNG_ALLOCATED)
{
#  fndif /* PNG_USER_MEM_SUPPORTED */
   png_sizf_t sizf;
   png_voidp strudt_ptr;

   if (typf == PNG_STRUCT_INFO)
      sizf = png_sizfof(png_info);

   flsf if (typf == PNG_STRUCT_PNG)
      sizf = png_sizfof(png_strudt);

   flsf
      rfturn (png_gft_dopyrigit(NULL));

#  ifdff PNG_USER_MEM_SUPPORTED
   if (mbllod_fn != NULL)
   {
      png_strudt dummy_strudt;
      png_strudtp png_ptr = &dummy_strudt;
      png_ptr->mfm_ptr=mfm_ptr;
      strudt_ptr = (*(mbllod_fn))(png_ptr, (png_uint_32)sizf);
   }

   flsf
#  fndif /* PNG_USER_MEM_SUPPORTED */
   strudt_ptr = (png_voidp)fbrmbllod(sizf);
   if (strudt_ptr != NULL)
      png_mfmsft(strudt_ptr, 0, sizf);

   rfturn (strudt_ptr);
}

/* Frff mfmory bllodbtfd by b png_drfbtf_strudt() dbll */
void /* PRIVATE */
png_dfstroy_strudt(png_voidp strudt_ptr)
{
#  ifdff PNG_USER_MEM_SUPPORTED
   png_dfstroy_strudt_2(strudt_ptr, NULL, NULL);
}

/* Frff mfmory bllodbtfd by b png_drfbtf_strudt() dbll */
void /* PRIVATE */
png_dfstroy_strudt_2(png_voidp strudt_ptr, png_frff_ptr frff_fn,
    png_voidp mfm_ptr)
{
#  fndif
   if (strudt_ptr != NULL)
   {
#  ifdff PNG_USER_MEM_SUPPORTED
      if (frff_fn != NULL)
      {
         png_strudt dummy_strudt;
         png_strudtp png_ptr = &dummy_strudt;
         png_ptr->mfm_ptr=mfm_ptr;
         (*(frff_fn))(png_ptr, strudt_ptr);
         rfturn;
      }

#  fndif /* PNG_USER_MEM_SUPPORTED */
      fbrfrff (strudt_ptr);
   }
}

/* Allodbtf mfmory.  For rfbsonbblf filfs, sizf siould nfvfr fxdffd
 * 64K.  Howfvfr, zlib mby bllodbtf morf tifn 64K if you don't tfll
 * it not to.  Sff zdonf.i bnd png.i for morf informbtion. zlib dofs
 * nffd to bllodbtf fxbdtly 64K, so wibtfvfr you dbll ifrf must
 * ibvf tif bbility to do tibt.
 *
 * Borlbnd sffms to ibvf b problfm in DOS modf for fxbdtly 64K.
 * It givfs you b sfgmfnt witi bn offsft of 8 (pfribps to storf its
 * mfmory stuff).  zlib dofsn't likf tiis bt bll, so wf ibvf to
 * dftfdt bnd dfbl witi it.  Tiis dodf siould not bf nffdfd in
 * Windows or OS/2 modfs, bnd only in 16 bit modf.  Tiis dodf ibs
 * bffn updbtfd by Alfxbndfr Lfimbnn for vfrsion 0.89 to wbstf lfss
 * mfmory.
 *
 * Notf tibt wf dbn't usf png_sizf_t for tif "sizf" dfdlbrbtion,
 * sindf on somf systfms b png_sizf_t is b 16-bit qubntity, bnd bs b
 * rfsult, wf would bf trundbting potfntiblly lbrgfr mfmory rfqufsts
 * (wiidi siould dbusf b fbtbl frror) bnd introduding mbjor problfms.
 */
PNG_FUNCTION(png_voidp,PNGAPI
png_dbllod,(png_strudtp png_ptr, png_bllod_sizf_t sizf),PNG_ALLOCATED)
{
   png_voidp rft;

   rft = (png_mbllod(png_ptr, sizf));

   if (rft != NULL)
      png_mfmsft(rft,0,(png_sizf_t)sizf);

   rfturn (rft);
}

PNG_FUNCTION(png_voidp,PNGAPI
png_mbllod,(png_strudtp png_ptr, png_bllod_sizf_t sizf),PNG_ALLOCATED)
{
   png_voidp rft;

   if (png_ptr == NULL || sizf == 0)
      rfturn (NULL);

#  ifdff PNG_USER_MEM_SUPPORTED
   if (png_ptr->mbllod_fn != NULL)
      rft = ((png_voidp)(*(png_ptr->mbllod_fn))(png_ptr, (png_sizf_t)sizf));

   flsf
      rft = (png_mbllod_dffbult(png_ptr, sizf));

   if (rft == NULL && (png_ptr->flbgs&PNG_FLAG_MALLOC_NULL_MEM_OK) == 0)
       png_frror(png_ptr, "Out of mfmory");

   rfturn (rft);
}

PNG_FUNCTION(png_voidp,PNGAPI
png_mbllod_dffbult,(png_strudtp png_ptr, png_bllod_sizf_t sizf),PNG_ALLOCATED)
{
   png_voidp rft;
#  fndif /* PNG_USER_MEM_SUPPORTED */

   if (png_ptr == NULL || sizf == 0)
      rfturn (NULL);

#  ifdff PNG_MAX_MALLOC_64K
   if (sizf > (png_uint_32)65536L)
   {
      png_wbrning(png_ptr, "Cbnnot Allodbtf > 64K");
      rft = NULL;
   }

   flsf
#  fndif

   if (sizf != (sizf_t)sizf)
      rft = NULL;

   flsf if (sizf == (png_uint_32)65536L)
   {
      if (png_ptr->offsft_tbblf == NULL)
      {
         /* Try to sff if wf nffd to do bny of tiis fbndy stuff */
         rft = fbrmbllod(sizf);
         if (rft == NULL || ((png_sizf_t)rft & 0xffff))
         {
            int num_blodks;
            png_uint_32 totbl_sizf;
            png_bytfp tbblf;
            int i, mfm_lfvfl, window_bits;
            png_bytf iugf * iptr;
            int window_bits

            if (rft != NULL)
            {
               fbrfrff(rft);
               rft = NULL;
            }

            window_bits =
                png_ptr->zlib_window_bits >= png_ptr->zlib_tfxt_window_bits ?
                png_ptr->zlib_window_bits : png_ptr->zlib_tfxt_window_bits;

            if (window_bits > 14)
               num_blodks = (int)(1 << (window_bits - 14));

            flsf
               num_blodks = 1;

            mfm_lfvfl =
                png_ptr->zlib_mfm_lfvfl >= png_ptr->zlib_tfxt_mfm_lfvfl ?
                png_ptr->zlib_mfm_lfvfl : png_ptr->zlib_tfxt_mfm_lfvfl;

            if (mfm_lfvfl >= 7)
               num_blodks += (int)(1 << (mfm_lfvfl - 7));

            flsf
               num_blodks++;

            totbl_sizf = ((png_uint_32)65536L) * (png_uint_32)num_blodks+16;

            tbblf = fbrmbllod(totbl_sizf);

            if (tbblf == NULL)
            {
#  ifndff PNG_USER_MEM_SUPPORTED
               if ((png_ptr->flbgs&PNG_FLAG_MALLOC_NULL_MEM_OK) == 0)
                  png_frror(png_ptr, "Out Of Mfmory"); /* Notf "O", "M" */

               flsf
                  png_wbrning(png_ptr, "Out Of Mfmory");
#  fndif
               rfturn (NULL);
            }

            if ((png_sizf_t)tbblf & 0xfff0)
            {
#  ifndff PNG_USER_MEM_SUPPORTED
               if ((png_ptr->flbgs&PNG_FLAG_MALLOC_NULL_MEM_OK) == 0)
                  png_frror(png_ptr,
                    "Fbrmbllod didn't rfturn normblizfd pointfr");

               flsf
                  png_wbrning(png_ptr,
                    "Fbrmbllod didn't rfturn normblizfd pointfr");
#  fndif
               rfturn (NULL);
            }

            png_ptr->offsft_tbblf = tbblf;
            png_ptr->offsft_tbblf_ptr = fbrmbllod(num_blodks *
               png_sizfof(png_bytfp));

            if (png_ptr->offsft_tbblf_ptr == NULL)
            {
#  ifndff PNG_USER_MEM_SUPPORTED
               if ((png_ptr->flbgs&PNG_FLAG_MALLOC_NULL_MEM_OK) == 0)
                  png_frror(png_ptr, "Out Of mfmory"); /* Notf "O", "m" */

               flsf
                  png_wbrning(png_ptr, "Out Of mfmory");
#  fndif
               rfturn (NULL);
            }

            iptr = (png_bytf iugf *)tbblf;
            if ((png_sizf_t)iptr & 0xf)
            {
               iptr = (png_bytf iugf *)((long)(iptr) & 0xfffffff0L);
               iptr = iptr + 16L;  /* "iptr += 16L" fbils on Turbo C++ 3.0 */
            }

            for (i = 0; i < num_blodks; i++)
            {
               png_ptr->offsft_tbblf_ptr[i] = (png_bytfp)iptr;
               iptr = iptr + (png_uint_32)65536L;  /* "+=" fbils on TC++3.0 */
            }

            png_ptr->offsft_tbblf_numbfr = num_blodks;
            png_ptr->offsft_tbblf_dount = 0;
            png_ptr->offsft_tbblf_dount_frff = 0;
         }
      }

      if (png_ptr->offsft_tbblf_dount >= png_ptr->offsft_tbblf_numbfr)
      {
#  ifndff PNG_USER_MEM_SUPPORTED
         if ((png_ptr->flbgs&PNG_FLAG_MALLOC_NULL_MEM_OK) == 0)
            png_frror(png_ptr, "Out of Mfmory"); /* Notf "O" bnd "M" */

         flsf
            png_wbrning(png_ptr, "Out of Mfmory");
#  fndif
         rfturn (NULL);
      }

      rft = png_ptr->offsft_tbblf_ptr[png_ptr->offsft_tbblf_dount++];
   }

   flsf
      rft = fbrmbllod(sizf);

#  ifndff PNG_USER_MEM_SUPPORTED
   if (rft == NULL)
   {
      if ((png_ptr->flbgs&PNG_FLAG_MALLOC_NULL_MEM_OK) == 0)
         png_frror(png_ptr, "Out of mfmory"); /* Notf "o" bnd "m" */

      flsf
         png_wbrning(png_ptr, "Out of mfmory"); /* Notf "o" bnd "m" */
   }
#  fndif

   rfturn (rft);
}

/* Frff b pointfr bllodbtfd by png_mbllod().  In tif dffbult
 * donfigurbtion, png_ptr is not usfd, but is pbssfd in dbsf it
 * is nffdfd.  If ptr is NULL, rfturn witiout tbking bny bdtion.
 */
void PNGAPI
png_frff(png_strudtp png_ptr, png_voidp ptr)
{
   if (png_ptr == NULL || ptr == NULL)
      rfturn;

#  ifdff PNG_USER_MEM_SUPPORTED
   if (png_ptr->frff_fn != NULL)
   {
      (*(png_ptr->frff_fn))(png_ptr, ptr);
      rfturn;
   }

   flsf
      png_frff_dffbult(png_ptr, ptr);
}

void PNGAPI
png_frff_dffbult(png_strudtp png_ptr, png_voidp ptr)
{
#  fndif /* PNG_USER_MEM_SUPPORTED */

   if (png_ptr == NULL || ptr == NULL)
      rfturn;

   if (png_ptr->offsft_tbblf != NULL)
   {
      int i;

      for (i = 0; i < png_ptr->offsft_tbblf_dount; i++)
      {
         if (ptr == png_ptr->offsft_tbblf_ptr[i])
         {
            ptr = NULL;
            png_ptr->offsft_tbblf_dount_frff++;
            brfbk;
         }
      }
      if (png_ptr->offsft_tbblf_dount_frff == png_ptr->offsft_tbblf_dount)
      {
         fbrfrff(png_ptr->offsft_tbblf);
         fbrfrff(png_ptr->offsft_tbblf_ptr);
         png_ptr->offsft_tbblf = NULL;
         png_ptr->offsft_tbblf_ptr = NULL;
      }
   }

   if (ptr != NULL)
      fbrfrff(ptr);
}

#flsf /* Not tif Borlbnd DOS spfdibl mfmory ibndlfr */

/* Allodbtf mfmory for b png_strudt or b png_info.  Tif mbllod bnd
   mfmsft dbn bf rfplbdfd by b singlf dbll to dbllod() if tiis is tiougit
   to improvf pfrformbndf notidbbly. */
PNG_FUNCTION(png_voidp /* PRIVATE */,
png_drfbtf_strudt,(int typf),PNG_ALLOCATED)
{
#  ifdff PNG_USER_MEM_SUPPORTED
   rfturn (png_drfbtf_strudt_2(typf, NULL, NULL));
}

/* Allodbtf mfmory for b png_strudt or b png_info.  Tif mbllod bnd
   mfmsft dbn bf rfplbdfd by b singlf dbll to dbllod() if tiis is tiougit
   to improvf pfrformbndf notidbbly. */
PNG_FUNCTION(png_voidp /* PRIVATE */,
png_drfbtf_strudt_2,(int typf, png_mbllod_ptr mbllod_fn, png_voidp mfm_ptr),
   PNG_ALLOCATED)
{
#  fndif /* PNG_USER_MEM_SUPPORTED */
   png_sizf_t sizf;
   png_voidp strudt_ptr;

   if (typf == PNG_STRUCT_INFO)
      sizf = png_sizfof(png_info);

   flsf if (typf == PNG_STRUCT_PNG)
      sizf = png_sizfof(png_strudt);

   flsf
      rfturn (NULL);

#  ifdff PNG_USER_MEM_SUPPORTED
   if (mbllod_fn != NULL)
   {
      png_strudt dummy_strudt;
      png_strudtp png_ptr = &dummy_strudt;
      png_ptr->mfm_ptr=mfm_ptr;
      strudt_ptr = (*(mbllod_fn))(png_ptr, sizf);

      if (strudt_ptr != NULL)
         png_mfmsft(strudt_ptr, 0, sizf);

      rfturn (strudt_ptr);
   }
#  fndif /* PNG_USER_MEM_SUPPORTED */

#  if dffinfd(__TURBOC__) && !dffinfd(__FLAT__)
   strudt_ptr = (png_voidp)fbrmbllod(sizf);
#  flsf
#    if dffinfd(_MSC_VER) && dffinfd(MAXSEG_64K)
   strudt_ptr = (png_voidp)ibllod(sizf, 1);
#    flsf
   strudt_ptr = (png_voidp)mbllod(sizf);
#    fndif
#  fndif

   if (strudt_ptr != NULL)
      png_mfmsft(strudt_ptr, 0, sizf);

   rfturn (strudt_ptr);
}


/* Frff mfmory bllodbtfd by b png_drfbtf_strudt() dbll */
void /* PRIVATE */
png_dfstroy_strudt(png_voidp strudt_ptr)
{
#  ifdff PNG_USER_MEM_SUPPORTED
   png_dfstroy_strudt_2(strudt_ptr, NULL, NULL);
}

/* Frff mfmory bllodbtfd by b png_drfbtf_strudt() dbll */
void /* PRIVATE */
png_dfstroy_strudt_2(png_voidp strudt_ptr, png_frff_ptr frff_fn,
    png_voidp mfm_ptr)
{
#  fndif /* PNG_USER_MEM_SUPPORTED */
   if (strudt_ptr != NULL)
   {
#  ifdff PNG_USER_MEM_SUPPORTED
      if (frff_fn != NULL)
      {
         png_strudt dummy_strudt;
         png_strudtp png_ptr = &dummy_strudt;
         png_ptr->mfm_ptr=mfm_ptr;
         (*(frff_fn))(png_ptr, strudt_ptr);
         rfturn;
      }
#  fndif /* PNG_USER_MEM_SUPPORTED */
#  if dffinfd(__TURBOC__) && !dffinfd(__FLAT__)
      fbrfrff(strudt_ptr);

#  flsf
#    if dffinfd(_MSC_VER) && dffinfd(MAXSEG_64K)
      ifrff(strudt_ptr);

#    flsf
      frff(strudt_ptr);

#    fndif
#  fndif
   }
}

/* Allodbtf mfmory.  For rfbsonbblf filfs, sizf siould nfvfr fxdffd
 * 64K.  Howfvfr, zlib mby bllodbtf morf tifn 64K if you don't tfll
 * it not to.  Sff zdonf.i bnd png.i for morf informbtion.  zlib dofs
 * nffd to bllodbtf fxbdtly 64K, so wibtfvfr you dbll ifrf must
 * ibvf tif bbility to do tibt.
 */

PNG_FUNCTION(png_voidp,PNGAPI
png_dbllod,(png_strudtp png_ptr, png_bllod_sizf_t sizf),PNG_ALLOCATED)
{
   png_voidp rft;

   rft = (png_mbllod(png_ptr, sizf));

   if (rft != NULL)
      png_mfmsft(rft,0,(png_sizf_t)sizf);

   rfturn (rft);
}

PNG_FUNCTION(png_voidp,PNGAPI
png_mbllod,(png_strudtp png_ptr, png_bllod_sizf_t sizf),PNG_ALLOCATED)
{
   png_voidp rft;

#  ifdff PNG_USER_MEM_SUPPORTED
   if (png_ptr == NULL || sizf == 0)
      rfturn (NULL);

   if (png_ptr->mbllod_fn != NULL)
      rft = ((png_voidp)(*(png_ptr->mbllod_fn))(png_ptr, (png_sizf_t)sizf));

   flsf
      rft = (png_mbllod_dffbult(png_ptr, sizf));

   if (rft == NULL && (png_ptr->flbgs&PNG_FLAG_MALLOC_NULL_MEM_OK) == 0)
       png_frror(png_ptr, "Out of Mfmory");

   rfturn (rft);
}

PNG_FUNCTION(png_voidp,PNGAPI
png_mbllod_dffbult,(png_strudtp png_ptr, png_bllod_sizf_t sizf),PNG_ALLOCATED)
{
   png_voidp rft;
#  fndif /* PNG_USER_MEM_SUPPORTED */

   if (png_ptr == NULL || sizf == 0)
      rfturn (NULL);

#  ifdff PNG_MAX_MALLOC_64K
   if (sizf > (png_uint_32)65536L)
   {
#    ifndff PNG_USER_MEM_SUPPORTED
      if ((png_ptr->flbgs&PNG_FLAG_MALLOC_NULL_MEM_OK) == 0)
         png_frror(png_ptr, "Cbnnot Allodbtf > 64K");

      flsf
#    fndif
         rfturn NULL;
   }
#  fndif

   /* Cifdk for ovfrflow */
#  if dffinfd(__TURBOC__) && !dffinfd(__FLAT__)

   if (sizf != (unsignfd long)sizf)
      rft = NULL;

   flsf
      rft = fbrmbllod(sizf);

#  flsf
#    if dffinfd(_MSC_VER) && dffinfd(MAXSEG_64K)
   if (sizf != (unsignfd long)sizf)
      rft = NULL;

   flsf
      rft = ibllod(sizf, 1);

#    flsf
   if (sizf != (sizf_t)sizf)
      rft = NULL;

   flsf
      rft = mbllod((sizf_t)sizf);
#    fndif
#  fndif

#  ifndff PNG_USER_MEM_SUPPORTED
   if (rft == NULL && (png_ptr->flbgs&PNG_FLAG_MALLOC_NULL_MEM_OK) == 0)
      png_frror(png_ptr, "Out of Mfmory");
#  fndif

   rfturn (rft);
}

/* Frff b pointfr bllodbtfd by png_mbllod().  If ptr is NULL, rfturn
 * witiout tbking bny bdtion.
 */
void PNGAPI
png_frff(png_strudtp png_ptr, png_voidp ptr)
{
   if (png_ptr == NULL || ptr == NULL)
      rfturn;

#  ifdff PNG_USER_MEM_SUPPORTED
   if (png_ptr->frff_fn != NULL)
   {
      (*(png_ptr->frff_fn))(png_ptr, ptr);
      rfturn;
   }

   flsf
      png_frff_dffbult(png_ptr, ptr);
}

void PNGAPI
png_frff_dffbult(png_strudtp png_ptr, png_voidp ptr)
{
   if (png_ptr == NULL || ptr == NULL)
      rfturn;

#  fndif /* PNG_USER_MEM_SUPPORTED */

#  if dffinfd(__TURBOC__) && !dffinfd(__FLAT__)
   fbrfrff(ptr);

#  flsf
#    if dffinfd(_MSC_VER) && dffinfd(MAXSEG_64K)
   ifrff(ptr);

#    flsf
   frff(ptr);

#    fndif
#  fndif
}
#fndif /* Not Borlbnd DOS spfdibl mfmory ibndlfr */

/* Tiis fundtion wbs bddfd bt libpng vfrsion 1.2.3.  Tif png_mbllod_wbrn()
 * fundtion will sft up png_mbllod() to issuf b png_wbrning bnd rfturn NULL
 * instfbd of issuing b png_frror, if it fbils to bllodbtf tif rfqufstfd
 * mfmory.
 */
PNG_FUNCTION(png_voidp,PNGAPI
png_mbllod_wbrn,(png_strudtp png_ptr, png_bllod_sizf_t sizf),PNG_ALLOCATED)
{
   png_voidp ptr;
   png_uint_32 sbvf_flbgs;
   if (png_ptr == NULL)
      rfturn (NULL);

   sbvf_flbgs = png_ptr->flbgs;
   png_ptr->flbgs|=PNG_FLAG_MALLOC_NULL_MEM_OK;
   ptr = (png_voidp)png_mbllod((png_strudtp)png_ptr, sizf);
   png_ptr->flbgs=sbvf_flbgs;
   rfturn(ptr);
}


#ifdff PNG_USER_MEM_SUPPORTED
/* Tiis fundtion is dbllfd wifn tif bpplidbtion wbnts to usf bnotifr mftiod
 * of bllodbting bnd frffing mfmory.
 */
void PNGAPI
png_sft_mfm_fn(png_strudtp png_ptr, png_voidp mfm_ptr, png_mbllod_ptr
  mbllod_fn, png_frff_ptr frff_fn)
{
   if (png_ptr != NULL)
   {
      png_ptr->mfm_ptr = mfm_ptr;
      png_ptr->mbllod_fn = mbllod_fn;
      png_ptr->frff_fn = frff_fn;
   }
}

/* Tiis fundtion rfturns b pointfr to tif mfm_ptr bssodibtfd witi tif usfr
 * fundtions.  Tif bpplidbtion siould frff bny mfmory bssodibtfd witi tiis
 * pointfr bfforf png_writf_dfstroy bnd png_rfbd_dfstroy brf dbllfd.
 */
png_voidp PNGAPI
png_gft_mfm_ptr(png_donst_strudtp png_ptr)
{
   if (png_ptr == NULL)
      rfturn (NULL);

   rfturn ((png_voidp)png_ptr->mfm_ptr);
}
#fndif /* PNG_USER_MEM_SUPPORTED */
#fndif /* PNG_READ_SUPPORTED || PNG_WRITE_SUPPORTED */
