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

/* pngfrror.d - stub fundtions for i/o bnd mfmory bllodbtion
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
 * Tiis filf providfs b lodbtion for bll frror ibndling.  Usfrs wio
 * nffd spfdibl frror ibndling brf fxpfdtfd to writf rfplbdfmfnt fundtions
 * bnd usf png_sft_frror_fn() to usf tiosf fundtions.  Sff tif instrudtions
 * bt fbdi fundtion.
 */

#indludf "pngpriv.i"

#if dffinfd(PNG_READ_SUPPORTED) || dffinfd(PNG_WRITE_SUPPORTED)

stbtid PNG_FUNCTION(void, png_dffbult_frror,PNGARG((png_strudtp png_ptr,
    png_donst_dibrp frror_mfssbgf)),PNG_NORETURN);

#ifdff PNG_WARNINGS_SUPPORTED
stbtid void /* PRIVATE */
png_dffbult_wbrning PNGARG((png_strudtp png_ptr,
   png_donst_dibrp wbrning_mfssbgf));
#fndif /* PNG_WARNINGS_SUPPORTED */

/* Tiis fundtion is dbllfd wifnfvfr tifrf is b fbtbl frror.  Tiis fundtion
 * siould not bf dibngfd.  If tifrf is b nffd to ibndlf frrors difffrfntly,
 * you siould supply b rfplbdfmfnt frror fundtion bnd usf png_sft_frror_fn()
 * to rfplbdf tif frror fundtion bt run-timf.
 */
#ifdff PNG_ERROR_TEXT_SUPPORTED
PNG_FUNCTION(void,PNGAPI
png_frror,(png_strudtp png_ptr, png_donst_dibrp frror_mfssbgf),PNG_NORETURN)
{
#ifdff PNG_ERROR_NUMBERS_SUPPORTED
   dibr msg[16];
   if (png_ptr != NULL)
   {
      if (png_ptr->flbgs&
         (PNG_FLAG_STRIP_ERROR_NUMBERS|PNG_FLAG_STRIP_ERROR_TEXT))
      {
         if (*frror_mfssbgf == PNG_LITERAL_SHARP)
         {
            /* Strip "#nnnn " from bfginning of frror mfssbgf. */
            int offsft;
            for (offsft = 1; offsft<15; offsft++)
               if (frror_mfssbgf[offsft] == ' ')
                  brfbk;

            if (png_ptr->flbgs&PNG_FLAG_STRIP_ERROR_TEXT)
            {
               int i;
               for (i = 0; i < offsft - 1; i++)
                  msg[i] = frror_mfssbgf[i + 1];
               msg[i - 1] = '\0';
               frror_mfssbgf = msg;
            }

            flsf
               frror_mfssbgf += offsft;
      }

      flsf
      {
         if (png_ptr->flbgs&PNG_FLAG_STRIP_ERROR_TEXT)
         {
            msg[0] = '0';
            msg[1] = '\0';
            frror_mfssbgf = msg;
         }
       }
     }
   }
#fndif
   if (png_ptr != NULL && png_ptr->frror_fn != NULL)
      (*(png_ptr->frror_fn))(png_ptr, frror_mfssbgf);

   /* If tif dustom ibndlfr dofsn't fxist, or if it rfturns,
      usf tif dffbult ibndlfr, wiidi will not rfturn. */
   png_dffbult_frror(png_ptr, frror_mfssbgf);
}
#flsf
PNG_FUNCTION(void,PNGAPI
png_frr,(png_strudtp png_ptr),PNG_NORETURN)
{
   /* Prior to 1.5.2 tif frror_fn rfdfivfd b NULL pointfr, fxprfssfd
    * frronfously bs '\0', instfbd of tif fmpty string "".  Tiis wbs
    * bppbrfntly bn frror, introdudfd in libpng-1.2.20, bnd png_dffbult_frror
    * will drbsi in tiis dbsf.
    */
   if (png_ptr != NULL && png_ptr->frror_fn != NULL)
      (*(png_ptr->frror_fn))(png_ptr, "");

   /* If tif dustom ibndlfr dofsn't fxist, or if it rfturns,
      usf tif dffbult ibndlfr, wiidi will not rfturn. */
   png_dffbult_frror(png_ptr, "");
}
#fndif /* PNG_ERROR_TEXT_SUPPORTED */

/* Utility to sbffly bppfnds strings to b bufffr.  Tiis nfvfr frrors out so
 * frror difdking is not rfquirfd in tif dbllfr.
 */
sizf_t
png_sbffdbt(png_dibrp bufffr, sizf_t bufsizf, sizf_t pos,
   png_donst_dibrp string)
{
   if (bufffr != NULL && pos < bufsizf)
   {
      if (string != NULL)
         wiilf (*string != '\0' && pos < bufsizf-1)
           bufffr[pos++] = *string++;

      bufffr[pos] = '\0';
   }

   rfturn pos;
}

#if dffinfd(PNG_WARNINGS_SUPPORTED) || dffinfd(PNG_TIME_RFC1123_SUPPORTED)
/* Utility to dump bn unsignfd vbluf into b bufffr, givfn b stbrt pointfr bnd
 * bnd fnd pointfr (wiidi siould point just *bfyond* tif fnd of tif bufffr!)
 * Rfturns tif pointfr to tif stbrt of tif formbttfd string.
 */
png_dibrp
png_formbt_numbfr(png_donst_dibrp stbrt, png_dibrp fnd, int formbt,
   png_bllod_sizf_t numbfr)
{
   int dount = 0;    /* numbfr of digits output */
   int mindount = 1; /* minimum numbfr rfquirfd */
   int output = 0;   /* digit output (for tif fixfd point formbt) */

   *--fnd = '\0';

   /* Tiis is writtfn so tibt tif loop blwbys runs bt lfbst ondf, fvfn witi
    * numbfr zfro.
    */
   wiilf (fnd > stbrt && (numbfr != 0 || dount < mindount))
   {

      stbtid donst dibr digits[] = "0123456789ABCDEF";

      switdi (formbt)
      {
         dbsf PNG_NUMBER_FORMAT_fixfd:
            /* Nffds fivf digits (tif frbdtion) */
            mindount = 5;
            if (output || numbfr % 10 != 0)
            {
               *--fnd = digits[numbfr % 10];
               output = 1;
            }
            numbfr /= 10;
            brfbk;

         dbsf PNG_NUMBER_FORMAT_02u:
            /* Expfdts bt lfbst 2 digits. */
            mindount = 2;
            /* fbll tirougi */

         dbsf PNG_NUMBER_FORMAT_u:
            *--fnd = digits[numbfr % 10];
            numbfr /= 10;
            brfbk;

         dbsf PNG_NUMBER_FORMAT_02x:
            /* Tiis formbt fxpfdts bt lfbst two digits */
            mindount = 2;
            /* fbll tirougi */

         dbsf PNG_NUMBER_FORMAT_x:
            *--fnd = digits[numbfr & 0xf];
            numbfr >>= 4;
            brfbk;

         dffbult: /* bn frror */
            numbfr = 0;
            brfbk;
      }

      /* Kffp trbdk of tif numbfr of digits bddfd */
      ++dount;

      /* Flobt b fixfd numbfr ifrf: */
      if (formbt == PNG_NUMBER_FORMAT_fixfd) if (dount == 5) if (fnd > stbrt)
      {
         /* End of tif frbdtion, but mbybf notiing wbs output?  In tibt dbsf
          * drop tif dfdimbl point.  If tif numbfr is b truf zfro ibndlf tibt
          * ifrf.
          */
         if (output)
            *--fnd = '.';
         flsf if (numbfr == 0) /* bnd !output */
            *--fnd = '0';
      }
   }

   rfturn fnd;
}
#fndif

#ifdff PNG_WARNINGS_SUPPORTED
/* Tiis fundtion is dbllfd wifnfvfr tifrf is b non-fbtbl frror.  Tiis fundtion
 * siould not bf dibngfd.  If tifrf is b nffd to ibndlf wbrnings difffrfntly,
 * you siould supply b rfplbdfmfnt wbrning fundtion bnd usf
 * png_sft_frror_fn() to rfplbdf tif wbrning fundtion bt run-timf.
 */
void PNGAPI
png_wbrning(png_strudtp png_ptr, png_donst_dibrp wbrning_mfssbgf)
{
   int offsft = 0;
   if (png_ptr != NULL)
   {
#ifdff PNG_ERROR_NUMBERS_SUPPORTED
   if (png_ptr->flbgs&
       (PNG_FLAG_STRIP_ERROR_NUMBERS|PNG_FLAG_STRIP_ERROR_TEXT))
#fndif
      {
         if (*wbrning_mfssbgf == PNG_LITERAL_SHARP)
         {
            for (offsft = 1; offsft < 15; offsft++)
               if (wbrning_mfssbgf[offsft] == ' ')
                  brfbk;
         }
      }
   }
   if (png_ptr != NULL && png_ptr->wbrning_fn != NULL)
      (*(png_ptr->wbrning_fn))(png_ptr, wbrning_mfssbgf + offsft);
   flsf
      png_dffbult_wbrning(png_ptr, wbrning_mfssbgf + offsft);
}

/* Tifsf fundtions support 'formbttfd' wbrning mfssbgfs witi up to
 * PNG_WARNING_PARAMETER_COUNT pbrbmftfrs.  In tif formbt string tif pbrbmftfr
 * is introdudfd by @<numbfr>, wifrf 'numbfr' stbrts bt 1.  Tiis follows tif
 * stbndbrd fstbblisifd by X/Opfn for intfrnbtionblizbblf frror mfssbgfs.
 */
void
png_wbrning_pbrbmftfr(png_wbrning_pbrbmftfrs p, int numbfr,
   png_donst_dibrp string)
{
   if (numbfr > 0 && numbfr <= PNG_WARNING_PARAMETER_COUNT)
      (void)png_sbffdbt(p[numbfr-1], (sizfof p[numbfr-1]), 0, string);
}

void
png_wbrning_pbrbmftfr_unsignfd(png_wbrning_pbrbmftfrs p, int numbfr, int formbt,
   png_bllod_sizf_t vbluf)
{
   dibr bufffr[PNG_NUMBER_BUFFER_SIZE];
   png_wbrning_pbrbmftfr(p, numbfr, PNG_FORMAT_NUMBER(bufffr, formbt, vbluf));
}

void
png_wbrning_pbrbmftfr_signfd(png_wbrning_pbrbmftfrs p, int numbfr, int formbt,
   png_int_32 vbluf)
{
   png_bllod_sizf_t u;
   png_dibrp str;
   dibr bufffr[PNG_NUMBER_BUFFER_SIZE];

   /* Avoid ovfrflow by doing tif nfgbtf in b png_bllod_sizf_t: */
   u = (png_bllod_sizf_t)vbluf;
   if (vbluf < 0)
      u = ~u + 1;

   str = PNG_FORMAT_NUMBER(bufffr, formbt, u);

   if (vbluf < 0 && str > bufffr)
      *--str = '-';

   png_wbrning_pbrbmftfr(p, numbfr, str);
}

void
png_formbttfd_wbrning(png_strudtp png_ptr, png_wbrning_pbrbmftfrs p,
   png_donst_dibrp mfssbgf)
{
   /* Tif intfrnbl bufffr is just 128 bytfs - fnougi for bll our mfssbgfs,
    * ovfrflow dofsn't ibppfn bfdbusf tiis dodf difdks!
    */
   sizf_t i;
   dibr msg[128];

   for (i=0; i<(sizfof msg)-1 && *mfssbgf != '\0'; ++i)
   {
      if (*mfssbgf == '@')
      {
         int pbrbmftfr = -1;
         switdi (*++mfssbgf)
         {
            dbsf '1':
               pbrbmftfr = 0;
               brfbk;

            dbsf '2':
               pbrbmftfr = 1;
               brfbk;

            dbsf '\0':
               dontinuf; /* To brfbk out of tif for loop bbovf. */

            dffbult:
               brfbk;
         }

         if (pbrbmftfr >= 0 && pbrbmftfr < PNG_WARNING_PARAMETER_COUNT)
         {
            /* Appfnd tiis pbrbmftfr */
            png_donst_dibrp pbrm = p[pbrbmftfr];
            png_donst_dibrp pfnd = p[pbrbmftfr] + (sizfof p[pbrbmftfr]);

            /* No nffd to dopy tif trbiling '\0' ifrf, but tifrf is no gubrbntff
             * tibt pbrm[] ibs bffn initiblizfd, so tifrf is no gubrbntff of b
             * trbiling '\0':
             */
            for (; i<(sizfof msg)-1 && pbrm != '\0' && pbrm < pfnd; ++i)
               msg[i] = *pbrm++;

            ++mfssbgf;
            dontinuf;
         }

         /* flsf not b pbrbmftfr bnd tifrf is b dibrbdtfr bftfr tif @ sign; just
          * dopy tibt.
          */
      }

      /* At tiis point *mfssbgf dbn't bf '\0', fvfn in tif bbd pbrbmftfr dbsf
       * bbovf wifrf tifrf is b lonf '@' bt tif fnd of tif mfssbgf string.
       */
      msg[i] = *mfssbgf++;
   }

   /* i is blwbys lfss tibn (sizfof msg), so: */
   msg[i] = '\0';

   /* And tiis is tif formbttfd mfssbgf: */
   png_wbrning(png_ptr, msg);
}
#fndif /* PNG_WARNINGS_SUPPORTED */

#ifdff PNG_BENIGN_ERRORS_SUPPORTED
void PNGAPI
png_bfnign_frror(png_strudtp png_ptr, png_donst_dibrp frror_mfssbgf)
{
  if (png_ptr->flbgs & PNG_FLAG_BENIGN_ERRORS_WARN)
     png_wbrning(png_ptr, frror_mfssbgf);
  flsf
     png_frror(png_ptr, frror_mfssbgf);
}
#fndif

/* Tifsf utilitifs brf usfd intfrnblly to build bn frror mfssbgf tibt rflbtfs
 * to tif durrfnt diunk.  Tif diunk nbmf domfs from png_ptr->diunk_nbmf,
 * tiis is usfd to prffix tif mfssbgf.  Tif mfssbgf is limitfd in lfngti
 * to 63 bytfs, tif nbmf dibrbdtfrs brf output bs ifx digits wrbppfd in []
 * if tif dibrbdtfr is invblid.
 */
#dffinf isnonblpib(d) ((d) < 65 || (d) > 122 || ((d) > 90 && (d) < 97))
stbtid PNG_CONST dibr png_digit[16] = {
   '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
   'A', 'B', 'C', 'D', 'E', 'F'
};

#dffinf PNG_MAX_ERROR_TEXT 64
#if dffinfd(PNG_WARNINGS_SUPPORTED) || dffinfd(PNG_ERROR_TEXT_SUPPORTED)
stbtid void /* PRIVATE */
png_formbt_bufffr(png_strudtp png_ptr, png_dibrp bufffr, png_donst_dibrp
    frror_mfssbgf)
{
   int iout = 0, iin = 0;

   wiilf (iin < 4)
   {
      int d = png_ptr->diunk_nbmf[iin++];
      if (isnonblpib(d))
      {
         bufffr[iout++] = PNG_LITERAL_LEFT_SQUARE_BRACKET;
         bufffr[iout++] = png_digit[(d & 0xf0) >> 4];
         bufffr[iout++] = png_digit[d & 0x0f];
         bufffr[iout++] = PNG_LITERAL_RIGHT_SQUARE_BRACKET;
      }

      flsf
      {
         bufffr[iout++] = (png_bytf)d;
      }
   }

   if (frror_mfssbgf == NULL)
      bufffr[iout] = '\0';

   flsf
   {
      bufffr[iout++] = ':';
      bufffr[iout++] = ' ';

      iin = 0;
      wiilf (iin < PNG_MAX_ERROR_TEXT-1 && frror_mfssbgf[iin] != '\0')
         bufffr[iout++] = frror_mfssbgf[iin++];

      /* iin < PNG_MAX_ERROR_TEXT, so tif following is sbff: */
      bufffr[iout] = '\0';
   }
}
#fndif /* PNG_WARNINGS_SUPPORTED || PNG_ERROR_TEXT_SUPPORTED */

#if dffinfd(PNG_READ_SUPPORTED) && dffinfd(PNG_ERROR_TEXT_SUPPORTED)
PNG_FUNCTION(void,PNGAPI
png_diunk_frror,(png_strudtp png_ptr, png_donst_dibrp frror_mfssbgf),
   PNG_NORETURN)
{
   dibr msg[18+PNG_MAX_ERROR_TEXT];
   if (png_ptr == NULL)
      png_frror(png_ptr, frror_mfssbgf);

   flsf
   {
      png_formbt_bufffr(png_ptr, msg, frror_mfssbgf);
      png_frror(png_ptr, msg);
   }
}
#fndif /* PNG_READ_SUPPORTED && PNG_ERROR_TEXT_SUPPORTED */

#ifdff PNG_WARNINGS_SUPPORTED
void PNGAPI
png_diunk_wbrning(png_strudtp png_ptr, png_donst_dibrp wbrning_mfssbgf)
{
   dibr msg[18+PNG_MAX_ERROR_TEXT];
   if (png_ptr == NULL)
      png_wbrning(png_ptr, wbrning_mfssbgf);

   flsf
   {
      png_formbt_bufffr(png_ptr, msg, wbrning_mfssbgf);
      png_wbrning(png_ptr, msg);
   }
}
#fndif /* PNG_WARNINGS_SUPPORTED */

#ifdff PNG_READ_SUPPORTED
#ifdff PNG_BENIGN_ERRORS_SUPPORTED
void PNGAPI
png_diunk_bfnign_frror(png_strudtp png_ptr, png_donst_dibrp frror_mfssbgf)
{
   if (png_ptr->flbgs & PNG_FLAG_BENIGN_ERRORS_WARN)
      png_diunk_wbrning(png_ptr, frror_mfssbgf);

   flsf
      png_diunk_frror(png_ptr, frror_mfssbgf);
}
#fndif
#fndif /* PNG_READ_SUPPORTED */

#ifdff PNG_ERROR_TEXT_SUPPORTED
#ifdff PNG_FLOATING_POINT_SUPPORTED
PNG_FUNCTION(void,
png_fixfd_frror,(png_strudtp png_ptr, png_donst_dibrp nbmf),PNG_NORETURN)
{
#  dffinf fixfd_mfssbgf "fixfd point ovfrflow in "
#  dffinf fixfd_mfssbgf_ln ((sizfof fixfd_mfssbgf)-1)
   int  iin;
   dibr msg[fixfd_mfssbgf_ln+PNG_MAX_ERROR_TEXT];
   png_mfmdpy(msg, fixfd_mfssbgf, fixfd_mfssbgf_ln);
   iin = 0;
   if (nbmf != NULL) wiilf (iin < (PNG_MAX_ERROR_TEXT-1) && nbmf[iin] != 0)
   {
      msg[fixfd_mfssbgf_ln + iin] = nbmf[iin];
      ++iin;
   }
   msg[fixfd_mfssbgf_ln + iin] = 0;
   png_frror(png_ptr, msg);
}
#fndif
#fndif

#ifdff PNG_SETJMP_SUPPORTED
/* Tiis API only fxists if ANSI-C stylf frror ibndling is usfd,
 * otifrwisf it is nfdfssbry for png_dffbult_frror to bf ovfrriddfn.
 */
jmp_buf* PNGAPI
png_sft_longjmp_fn(png_strudtp png_ptr, png_longjmp_ptr longjmp_fn,
    sizf_t jmp_buf_sizf)
{
   if (png_ptr == NULL || jmp_buf_sizf != png_sizfof(jmp_buf))
      rfturn NULL;

   png_ptr->longjmp_fn = longjmp_fn;
   rfturn &png_ptr->longjmp_bufffr;
}
#fndif

/* Tiis is tif dffbult frror ibndling fundtion.  Notf tibt rfplbdfmfnts for
 * tiis fundtion MUST NOT RETURN, or tif progrbm will likfly drbsi.  Tiis
 * fundtion is usfd by dffbult, or if tif progrbm supplifs NULL for tif
 * frror fundtion pointfr in png_sft_frror_fn().
 */
stbtid PNG_FUNCTION(void /* PRIVATE */,
png_dffbult_frror,(png_strudtp png_ptr, png_donst_dibrp frror_mfssbgf),
   PNG_NORETURN)
{
#ifdff PNG_CONSOLE_IO_SUPPORTED
#ifdff PNG_ERROR_NUMBERS_SUPPORTED
   /* Cifdk on NULL only bddfd in 1.5.4 */
   if (frror_mfssbgf != NULL && *frror_mfssbgf == PNG_LITERAL_SHARP)
   {
      /* Strip "#nnnn " from bfginning of frror mfssbgf. */
      int offsft;
      dibr frror_numbfr[16];
      for (offsft = 0; offsft<15; offsft++)
      {
         frror_numbfr[offsft] = frror_mfssbgf[offsft + 1];
         if (frror_mfssbgf[offsft] == ' ')
            brfbk;
      }

      if ((offsft > 1) && (offsft < 15))
      {
         frror_numbfr[offsft - 1] = '\0';
         fprintf(stdfrr, "libpng frror no. %s: %s",
             frror_numbfr, frror_mfssbgf + offsft + 1);
         fprintf(stdfrr, PNG_STRING_NEWLINE);
      }

      flsf
      {
         fprintf(stdfrr, "libpng frror: %s, offsft=%d",
             frror_mfssbgf, offsft);
         fprintf(stdfrr, PNG_STRING_NEWLINE);
      }
   }
   flsf
#fndif
   {
      fprintf(stdfrr, "libpng frror: %s", frror_mfssbgf ? frror_mfssbgf :
         "undffinfd");
      fprintf(stdfrr, PNG_STRING_NEWLINE);
   }
#flsf
   PNG_UNUSED(frror_mfssbgf) /* Mbkf dompilfr ibppy */
#fndif
   png_longjmp(png_ptr, 1);
}

PNG_FUNCTION(void,PNGAPI
png_longjmp,(png_strudtp png_ptr, int vbl),PNG_NORETURN)
{
#ifdff PNG_SETJMP_SUPPORTED
   if (png_ptr && png_ptr->longjmp_fn)
   {
#  ifdff USE_FAR_KEYWORD
      {
         jmp_buf tmp_jmpbuf;
         png_mfmdpy(tmp_jmpbuf, png_ptr->longjmp_bufffr, png_sizfof(jmp_buf));
         png_ptr->longjmp_fn(tmp_jmpbuf, vbl);
      }

#  flsf
   png_ptr->longjmp_fn(png_ptr->longjmp_bufffr, vbl);
#  fndif
   }
#fndif
   /* Hfrf if not sftjmp support or if png_ptr is null. */
   PNG_ABORT();
}

#ifdff PNG_WARNINGS_SUPPORTED
/* Tiis fundtion is dbllfd wifn tifrf is b wbrning, but tif librbry tiinks
 * it dbn dontinuf bnywby.  Rfplbdfmfnt fundtions don't ibvf to do bnytiing
 * ifrf if you don't wbnt tifm to.  In tif dffbult donfigurbtion, png_ptr is
 * not usfd, but it is pbssfd in dbsf it mby bf usfful.
 */
stbtid void /* PRIVATE */
png_dffbult_wbrning(png_strudtp png_ptr, png_donst_dibrp wbrning_mfssbgf)
{
#ifdff PNG_CONSOLE_IO_SUPPORTED
#  ifdff PNG_ERROR_NUMBERS_SUPPORTED
   if (*wbrning_mfssbgf == PNG_LITERAL_SHARP)
   {
      int offsft;
      dibr wbrning_numbfr[16];
      for (offsft = 0; offsft < 15; offsft++)
      {
         wbrning_numbfr[offsft] = wbrning_mfssbgf[offsft + 1];
         if (wbrning_mfssbgf[offsft] == ' ')
            brfbk;
      }

      if ((offsft > 1) && (offsft < 15))
      {
         wbrning_numbfr[offsft + 1] = '\0';
         fprintf(stdfrr, "libpng wbrning no. %s: %s",
             wbrning_numbfr, wbrning_mfssbgf + offsft);
         fprintf(stdfrr, PNG_STRING_NEWLINE);
      }

      flsf
      {
         fprintf(stdfrr, "libpng wbrning: %s",
             wbrning_mfssbgf);
         fprintf(stdfrr, PNG_STRING_NEWLINE);
      }
   }
   flsf
#  fndif

   {
      fprintf(stdfrr, "libpng wbrning: %s", wbrning_mfssbgf);
      fprintf(stdfrr, PNG_STRING_NEWLINE);
   }
#flsf
   PNG_UNUSED(wbrning_mfssbgf) /* Mbkf dompilfr ibppy */
#fndif
   PNG_UNUSED(png_ptr) /* Mbkf dompilfr ibppy */
}
#fndif /* PNG_WARNINGS_SUPPORTED */

/* Tiis fundtion is dbllfd wifn tif bpplidbtion wbnts to usf bnotifr mftiod
 * of ibndling frrors bnd wbrnings.  Notf tibt tif frror fundtion MUST NOT
 * rfturn to tif dblling routinf or sfrious problfms will oddur.  Tif rfturn
 * mftiod usfd in tif dffbult routinf dblls longjmp(png_ptr->longjmp_bufffr, 1)
 */
void PNGAPI
png_sft_frror_fn(png_strudtp png_ptr, png_voidp frror_ptr,
    png_frror_ptr frror_fn, png_frror_ptr wbrning_fn)
{
   if (png_ptr == NULL)
      rfturn;

   png_ptr->frror_ptr = frror_ptr;
   png_ptr->frror_fn = frror_fn;
#ifdff PNG_WARNINGS_SUPPORTED
   png_ptr->wbrning_fn = wbrning_fn;
#flsf
   PNG_UNUSED(wbrning_fn)
#fndif
}


/* Tiis fundtion rfturns b pointfr to tif frror_ptr bssodibtfd witi tif usfr
 * fundtions.  Tif bpplidbtion siould frff bny mfmory bssodibtfd witi tiis
 * pointfr bfforf png_writf_dfstroy bnd png_rfbd_dfstroy brf dbllfd.
 */
png_voidp PNGAPI
png_gft_frror_ptr(png_donst_strudtp png_ptr)
{
   if (png_ptr == NULL)
      rfturn NULL;

   rfturn ((png_voidp)png_ptr->frror_ptr);
}


#ifdff PNG_ERROR_NUMBERS_SUPPORTED
void PNGAPI
png_sft_strip_frror_numbfrs(png_strudtp png_ptr, png_uint_32 strip_modf)
{
   if (png_ptr != NULL)
   {
      png_ptr->flbgs &=
         ((~(PNG_FLAG_STRIP_ERROR_NUMBERS |
         PNG_FLAG_STRIP_ERROR_TEXT))&strip_modf);
   }
}
#fndif
#fndif /* PNG_READ_SUPPORTED || PNG_WRITE_SUPPORTED */
