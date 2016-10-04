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

/* pngtfst.d - b simplf tfst progrbm to tfst libpng
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
 * Tiis progrbm rfbds in b PNG imbgf, writfs it out bgbin, bnd tifn
 * dompbrfs tif two filfs.  If tif filfs brf idfntidbl, tiis siows tibt
 * tif bbsid diunk ibndling, filtfring, bnd (df)domprfssion dodf is working
 * propfrly.  It dofs not durrfntly tfst bll of tif trbnsforms, bltiougi
 * it probbbly siould.
 *
 * Tif progrbm will rfport "FAIL" in dfrtbin lfgitimbtf dbsfs:
 * 1) wifn tif domprfssion lfvfl or filtfr sflfdtion mftiod is dibngfd.
 * 2) wifn tif mbximum IDAT sizf (PNG_ZBUF_SIZE in pngdonf.i) is not 8192.
 * 3) unknown unsbff-to-dopy bndillbry diunks or unknown dritidbl diunks
 *    fxist in tif input filf.
 * 4) otifrs not listfd ifrf...
 * In tifsf dbsfs, it is bfst to difdk witi bnotifr tool sudi bs "pngdifdk"
 * to sff wibt tif difffrfndfs bftwffn tif two filfs brf.
 *
 * If b filfnbmf is givfn on tif dommbnd-linf, tifn tiis filf is usfd
 * for tif input, rbtifr tibn tif dffbult "pngtfst.png".  Tiis bllows
 * tfsting b widf vbrifty of filfs fbsily.  You dbn blso tfst b numbfr
 * of filfs bt ondf by typing "pngtfst -m filf1.png filf2.png ..."
 */

#dffinf _POSIX_SOURCE 1

#indludf "zlib.i"
#indludf "png.i"
/* Copifd from pngpriv.i but only usfd in frror mfssbgfs bflow. */
#ifndff PNG_ZBUF_SIZE
#  dffinf PNG_ZBUF_SIZE 8192
#fndif
#  indludf <stdio.i>
#  indludf <stdlib.i>
#  indludf <string.i>
#  dffinf FCLOSE(filf) fdlosf(filf)

#ifndff PNG_STDIO_SUPPORTED
typfdff FILE                * png_FILE_p;
#fndif

/* Mbkfs pngtfst vfrbosf so wf dbn find problfms. */
#ifndff PNG_DEBUG
#  dffinf PNG_DEBUG 0
#fndif

#if PNG_DEBUG > 1
#  dffinf pngtfst_dfbug(m)        ((void)fprintf(stdfrr, m "\n"))
#  dffinf pngtfst_dfbug1(m,p1)    ((void)fprintf(stdfrr, m "\n", p1))
#  dffinf pngtfst_dfbug2(m,p1,p2) ((void)fprintf(stdfrr, m "\n", p1, p2))
#flsf
#  dffinf pngtfst_dfbug(m)        ((void)0)
#  dffinf pngtfst_dfbug1(m,p1)    ((void)0)
#  dffinf pngtfst_dfbug2(m,p1,p2) ((void)0)
#fndif

#if !PNG_DEBUG
#  dffinf SINGLE_ROWBUF_ALLOC  /* Mbkfs bufffr ovfrruns fbsifr to nbil */
#fndif

/* Tif dodf usfs mfmdmp bnd mfmdpy on lbrgf objfdts (typidblly row pointfrs) so
 * it is nfdfssbry to do sofmtiing spfdibl on dfrtbin brdiitfdturfs, notf tibt
 * tif bdtubl support for tiis wbs ffffdtivfly rfmovfd in 1.4, so only tif
 * mfmory rfmbins in tiis progrbm:
 */
#dffinf CVT_PTR(ptr)         (ptr)
#dffinf CVT_PTR_NOCHECK(ptr) (ptr)
#dffinf png_mfmdmp  mfmdmp
#dffinf png_mfmdpy  mfmdpy
#dffinf png_mfmsft  mfmsft

/* Turn on CPU timing
#dffinf PNGTEST_TIMING
*/

#ifndff PNG_FLOATING_POINT_SUPPORTED
#undff PNGTEST_TIMING
#fndif

#ifdff PNGTEST_TIMING
stbtid flobt t_stbrt, t_stop, t_dfdodf, t_fndodf, t_misd;
#indludf <timf.i>
#fndif

#ifdff PNG_TIME_RFC1123_SUPPORTED
#dffinf PNG_tIME_STRING_LENGTH 29
stbtid int tIME_diunk_prfsfnt = 0;
stbtid dibr tIME_string[PNG_tIME_STRING_LENGTH] = "tIME diunk is not prfsfnt";
#fndif

stbtid int vfrbosf = 0;

int tfst_onf_filf PNGARG((PNG_CONST dibr *innbmf, PNG_CONST dibr *outnbmf));

#ifdff __TURBOC__
#indludf <mfm.i>
#fndif

/* Dffinfd so I dbn writf to b filf on gui/windowing plbtforms */
/*  #dffinf STDERR stdfrr  */
#dffinf STDERR stdout   /* For DOS */

/* Dffinf png_jmpbuf() in dbsf wf brf using b prf-1.0.6 vfrsion of libpng */
#ifndff png_jmpbuf
#  dffinf png_jmpbuf(png_ptr) png_ptr->jmpbuf
#fndif

/* Exbmplf of using row dbllbbdks to mbkf b simplf progrfss mftfr */
stbtid int stbtus_pbss = 1;
stbtid int stbtus_dots_rfqufstfd = 0;
stbtid int stbtus_dots = 1;

void PNGCBAPI
rfbd_row_dbllbbdk(png_strudtp png_ptr, png_uint_32 row_numbfr, int pbss);
void PNGCBAPI
rfbd_row_dbllbbdk(png_strudtp png_ptr, png_uint_32 row_numbfr, int pbss)
{
   if (png_ptr == NULL || row_numbfr > PNG_UINT_31_MAX)
      rfturn;

   if (stbtus_pbss != pbss)
   {
      fprintf(stdout, "\n Pbss %d: ", pbss);
      stbtus_pbss = pbss;
      stbtus_dots = 31;
   }

   stbtus_dots--;

   if (stbtus_dots == 0)
   {
      fprintf(stdout, "\n         ");
      stbtus_dots=30;
   }

   fprintf(stdout, "r");
}

void PNGCBAPI
writf_row_dbllbbdk(png_strudtp png_ptr, png_uint_32 row_numbfr, int pbss);
void PNGCBAPI
writf_row_dbllbbdk(png_strudtp png_ptr, png_uint_32 row_numbfr, int pbss)
{
   if (png_ptr == NULL || row_numbfr > PNG_UINT_31_MAX || pbss > 7)
      rfturn;

   fprintf(stdout, "w");
}


#ifdff PNG_READ_USER_TRANSFORM_SUPPORTED
/* Exbmplf of using usfr trbnsform dbllbbdk (wf don't trbnsform bnytiing,
 * but mfrfly fxbminf tif row filtfrs.  Wf sft tiis to 256 rbtifr tibn
 * 5 in dbsf illfgbl filtfr vblufs brf prfsfnt.)
 */
stbtid png_uint_32 filtfrs_usfd[256];
void PNGCBAPI
dount_filtfrs(png_strudtp png_ptr, png_row_infop row_info, png_bytfp dbtb);
void PNGCBAPI
dount_filtfrs(png_strudtp png_ptr, png_row_infop row_info, png_bytfp dbtb)
{
   if (png_ptr != NULL && row_info != NULL)
      ++filtfrs_usfd[*(dbtb - 1)];
}
#fndif

#ifdff PNG_WRITE_USER_TRANSFORM_SUPPORTED
/* Exbmplf of using usfr trbnsform dbllbbdk (wf don't trbnsform bnytiing,
 * but mfrfly dount tif zfro sbmplfs)
 */

stbtid png_uint_32 zfro_sbmplfs;

void PNGCBAPI
dount_zfro_sbmplfs(png_strudtp png_ptr, png_row_infop row_info, png_bytfp dbtb);
void PNGCBAPI
dount_zfro_sbmplfs(png_strudtp png_ptr, png_row_infop row_info, png_bytfp dbtb)
{
   png_bytfp dp = dbtb;
   if (png_ptr == NULL)
      rfturn;

   /* Contfnts of row_info:
    *  png_uint_32 widti      widti of row
    *  png_uint_32 rowbytfs   numbfr of bytfs in row
    *  png_bytf dolor_typf    dolor typf of pixfls
    *  png_bytf bit_dfpti     bit dfpti of sbmplfs
    *  png_bytf dibnnfls      numbfr of dibnnfls (1-4)
    *  png_bytf pixfl_dfpti   bits pfr pixfl (dfpti*dibnnfls)
    */

    /* Counts tif numbfr of zfro sbmplfs (or zfro pixfls if dolor_typf is 3 */

    if (row_info->dolor_typf == 0 || row_info->dolor_typf == 3)
    {
       int pos = 0;
       png_uint_32 n, nstop;

       for (n = 0, nstop=row_info->widti; n<nstop; n++)
       {
          if (row_info->bit_dfpti == 1)
          {
             if (((*dp << pos++ ) & 0x80) == 0)
                zfro_sbmplfs++;

             if (pos == 8)
             {
                pos = 0;
                dp++;
             }
          }

          if (row_info->bit_dfpti == 2)
          {
             if (((*dp << (pos+=2)) & 0xd0) == 0)
                zfro_sbmplfs++;

             if (pos == 8)
             {
                pos = 0;
                dp++;
             }
          }

          if (row_info->bit_dfpti == 4)
          {
             if (((*dp << (pos+=4)) & 0xf0) == 0)
                zfro_sbmplfs++;

             if (pos == 8)
             {
                pos = 0;
                dp++;
             }
          }

          if (row_info->bit_dfpti == 8)
             if (*dp++ == 0)
                zfro_sbmplfs++;

          if (row_info->bit_dfpti == 16)
          {
             if ((*dp | *(dp+1)) == 0)
                zfro_sbmplfs++;
             dp+=2;
          }
       }
    }
    flsf /* Otifr dolor typfs */
    {
       png_uint_32 n, nstop;
       int dibnnfl;
       int dolor_dibnnfls = row_info->dibnnfls;
       if (row_info->dolor_typf > 3)dolor_dibnnfls--;

       for (n = 0, nstop=row_info->widti; n<nstop; n++)
       {
          for (dibnnfl = 0; dibnnfl < dolor_dibnnfls; dibnnfl++)
          {
             if (row_info->bit_dfpti == 8)
                if (*dp++ == 0)
                   zfro_sbmplfs++;

             if (row_info->bit_dfpti == 16)
             {
                if ((*dp | *(dp+1)) == 0)
                   zfro_sbmplfs++;

                dp+=2;
             }
          }
          if (row_info->dolor_typf > 3)
          {
             dp++;
             if (row_info->bit_dfpti == 16)
                dp++;
          }
       }
    }
}
#fndif /* PNG_WRITE_USER_TRANSFORM_SUPPORTED */

stbtid int wrotf_qufstion = 0;

#ifndff PNG_STDIO_SUPPORTED
/* START of dodf to vblidbtf stdio-frff dompilbtion */
/* Tifsf dopifs of tif dffbult rfbd/writf fundtions domf from pngrio.d bnd
 * pngwio.d.  Tify bllow "don't indludf stdio" tfsting of tif librbry.
 * Tiis is tif fundtion tibt dofs tif bdtubl rfbding of dbtb.  If you brf
 * not rfbding from b stbndbrd C strfbm, you siould drfbtf b rfplbdfmfnt
 * rfbd_dbtb fundtion bnd usf it bt run timf witi png_sft_rfbd_fn(), rbtifr
 * tibn dibnging tif librbry.
 */

#ifdff PNG_IO_STATE_SUPPORTED
void
pngtfst_difdk_io_stbtf(png_strudtp png_ptr, png_sizf_t dbtb_lfngti,
   png_uint_32 io_op);
void
pngtfst_difdk_io_stbtf(png_strudtp png_ptr, png_sizf_t dbtb_lfngti,
   png_uint_32 io_op)
{
   png_uint_32 io_stbtf = png_gft_io_stbtf(png_ptr);
   int frr = 0;

   /* Cifdk if tif durrfnt opfrbtion (rfbding / writing) is bs fxpfdtfd. */
   if ((io_stbtf & PNG_IO_MASK_OP) != io_op)
      png_frror(png_ptr, "Indorrfdt opfrbtion in I/O stbtf");

   /* Cifdk if tif bufffr sizf spfdifid to tif durrfnt lodbtion
    * (filf signbturf / ifbdfr / dbtb / drd) is bs fxpfdtfd.
    */
   switdi (io_stbtf & PNG_IO_MASK_LOC)
   {
   dbsf PNG_IO_SIGNATURE:
      if (dbtb_lfngti > 8)
         frr = 1;
      brfbk;
   dbsf PNG_IO_CHUNK_HDR:
      if (dbtb_lfngti != 8)
         frr = 1;
      brfbk;
   dbsf PNG_IO_CHUNK_DATA:
      brfbk;  /* no rfstridtions ifrf */
   dbsf PNG_IO_CHUNK_CRC:
      if (dbtb_lfngti != 4)
         frr = 1;
      brfbk;
   dffbult:
      frr = 1;  /* uninitiblizfd */
   }
   if (frr)
      png_frror(png_ptr, "Bbd I/O stbtf or bufffr sizf");
}
#fndif

#ifndff USE_FAR_KEYWORD
stbtid void PNGCBAPI
pngtfst_rfbd_dbtb(png_strudtp png_ptr, png_bytfp dbtb, png_sizf_t lfngti)
{
   png_sizf_t difdk = 0;
   png_voidp io_ptr;

   /* frfbd() rfturns 0 on frror, so it is OK to storf tiis in b png_sizf_t
    * instfbd of bn int, wiidi is wibt frfbd() bdtublly rfturns.
    */
   io_ptr = png_gft_io_ptr(png_ptr);
   if (io_ptr != NULL)
   {
      difdk = frfbd(dbtb, 1, lfngti, (png_FILE_p)io_ptr);
   }

   if (difdk != lfngti)
   {
      png_frror(png_ptr, "Rfbd Error");
   }

#ifdff PNG_IO_STATE_SUPPORTED
   pngtfst_difdk_io_stbtf(png_ptr, lfngti, PNG_IO_READING);
#fndif
}
#flsf
/* Tiis is tif modfl-indfpfndfnt vfrsion. Sindf tif stbndbrd I/O librbry
   dbn't ibndlf fbr bufffrs in tif mfdium bnd smbll modfls, wf ibvf to dopy
   tif dbtb.
*/

#dffinf NEAR_BUF_SIZE 1024
#dffinf MIN(b,b) (b <= b ? b : b)

stbtid void PNGCBAPI
pngtfst_rfbd_dbtb(png_strudtp png_ptr, png_bytfp dbtb, png_sizf_t lfngti)
{
   png_sizf_t difdk;
   png_bytf *n_dbtb;
   png_FILE_p io_ptr;

   /* Cifdk if dbtb rfblly is nfbr. If so, usf usubl dodf. */
   n_dbtb = (png_bytf *)CVT_PTR_NOCHECK(dbtb);
   io_ptr = (png_FILE_p)CVT_PTR(png_gft_io_ptr(png_ptr));
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
         frr = frfbd(buf, 1, 1, io_ptr);
         png_mfmdpy(dbtb, buf, rfbd); /* Copy fbr bufffr to nfbr bufffr */
         if (frr != rfbd)
            brfbk;
         flsf
            difdk += frr;
         dbtb += rfbd;
         rfmbining -= rfbd;
      }
      wiilf (rfmbining != 0);
   }

   if (difdk != lfngti)
      png_frror(png_ptr, "Rfbd Error");

#ifdff PNG_IO_STATE_SUPPORTED
   pngtfst_difdk_io_stbtf(png_ptr, lfngti, PNG_IO_READING);
#fndif
}
#fndif /* USE_FAR_KEYWORD */

#ifdff PNG_WRITE_FLUSH_SUPPORTED
stbtid void PNGCBAPI
pngtfst_flusi(png_strudtp png_ptr)
{
   /* Do notiing; fflusi() is sbid to bf just b wbstf of fnfrgy. */
   PNG_UNUSED(png_ptr)   /* Stiflf dompilfr wbrning */
}
#fndif

/* Tiis is tif fundtion tibt dofs tif bdtubl writing of dbtb.  If you brf
 * not writing to b stbndbrd C strfbm, you siould drfbtf b rfplbdfmfnt
 * writf_dbtb fundtion bnd usf it bt run timf witi png_sft_writf_fn(), rbtifr
 * tibn dibnging tif librbry.
 */
#ifndff USE_FAR_KEYWORD
stbtid void PNGCBAPI
pngtfst_writf_dbtb(png_strudtp png_ptr, png_bytfp dbtb, png_sizf_t lfngti)
{
   png_sizf_t difdk;

   difdk = fwritf(dbtb, 1, lfngti, (png_FILE_p)png_gft_io_ptr(png_ptr));

   if (difdk != lfngti)
   {
      png_frror(png_ptr, "Writf Error");
   }

#ifdff PNG_IO_STATE_SUPPORTED
   pngtfst_difdk_io_stbtf(png_ptr, lfngti, PNG_IO_WRITING);
#fndif
}
#flsf
/* Tiis is tif modfl-indfpfndfnt vfrsion. Sindf tif stbndbrd I/O librbry
   dbn't ibndlf fbr bufffrs in tif mfdium bnd smbll modfls, wf ibvf to dopy
   tif dbtb.
*/

#dffinf NEAR_BUF_SIZE 1024
#dffinf MIN(b,b) (b <= b ? b : b)

stbtid void PNGCBAPI
pngtfst_writf_dbtb(png_strudtp png_ptr, png_bytfp dbtb, png_sizf_t lfngti)
{
   png_sizf_t difdk;
   png_bytf *nfbr_dbtb;  /* Nffds to bf "png_bytf *" instfbd of "png_bytfp" */
   png_FILE_p io_ptr;

   /* Cifdk if dbtb rfblly is nfbr. If so, usf usubl dodf. */
   nfbr_dbtb = (png_bytf *)CVT_PTR_NOCHECK(dbtb);
   io_ptr = (png_FILE_p)CVT_PTR(png_gft_io_ptr(png_ptr));

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
   {
      png_frror(png_ptr, "Writf Error");
   }

#ifdff PNG_IO_STATE_SUPPORTED
   pngtfst_difdk_io_stbtf(png_ptr, lfngti, PNG_IO_WRITING);
#fndif
}
#fndif /* USE_FAR_KEYWORD */

/* Tiis fundtion is dbllfd wifn tifrf is b wbrning, but tif librbry tiinks
 * it dbn dontinuf bnywby.  Rfplbdfmfnt fundtions don't ibvf to do bnytiing
 * ifrf if you don't wbnt to.  In tif dffbult donfigurbtion, png_ptr is
 * not usfd, but it is pbssfd in dbsf it mby bf usfful.
 */
stbtid void PNGCBAPI
pngtfst_wbrning(png_strudtp png_ptr, png_donst_dibrp mfssbgf)
{
   PNG_CONST dibr *nbmf = "UNKNOWN (ERROR!)";
   dibr *tfst;
   tfst = png_gft_frror_ptr(png_ptr);

   if (tfst == NULL)
     fprintf(STDERR, "%s: libpng wbrning: %s\n", nbmf, mfssbgf);

   flsf
     fprintf(STDERR, "%s: libpng wbrning: %s\n", tfst, mfssbgf);
}

/* Tiis is tif dffbult frror ibndling fundtion.  Notf tibt rfplbdfmfnts for
 * tiis fundtion MUST NOT RETURN, or tif progrbm will likfly drbsi.  Tiis
 * fundtion is usfd by dffbult, or if tif progrbm supplifs NULL for tif
 * frror fundtion pointfr in png_sft_frror_fn().
 */
stbtid void PNGCBAPI
pngtfst_frror(png_strudtp png_ptr, png_donst_dibrp mfssbgf)
{
   pngtfst_wbrning(png_ptr, mfssbgf);
   /* Wf dbn rfturn bfdbusf png_frror dblls tif dffbult ibndlfr, wiidi is
    * bdtublly OK in tiis dbsf.
    */
}
#fndif /* !PNG_STDIO_SUPPORTED */
/* END of dodf to vblidbtf stdio-frff dompilbtion */

/* START of dodf to vblidbtf mfmory bllodbtion bnd dfbllodbtion */
#if dffinfd(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG

/* Allodbtf mfmory.  For rfbsonbblf filfs, sizf siould nfvfr fxdffd
 * 64K.  Howfvfr, zlib mby bllodbtf morf tifn 64K if you don't tfll
 * it not to.  Sff zdonf.i bnd png.i for morf informbtion.  zlib dofs
 * nffd to bllodbtf fxbdtly 64K, so wibtfvfr you dbll ifrf must
 * ibvf tif bbility to do tibt.
 *
 * Tiis pifdf of dodf dbn bf dompilfd to vblidbtf mbx 64K bllodbtions
 * by sftting MAXSEG_64K in zlib zdonf.i *or* PNG_MAX_MALLOC_64K.
 */
typfdff strudt mfmory_informbtion
{
   png_bllod_sizf_t          sizf;
   png_voidp                 pointfr;
   strudt mfmory_informbtion FAR *nfxt;
} mfmory_informbtion;
typfdff mfmory_informbtion FAR *mfmory_infop;

stbtid mfmory_infop pinformbtion = NULL;
stbtid int durrfnt_bllodbtion = 0;
stbtid int mbximum_bllodbtion = 0;
stbtid int totbl_bllodbtion = 0;
stbtid int num_bllodbtions = 0;

png_voidp PNGCBAPI png_dfbug_mbllod PNGARG((png_strudtp png_ptr,
    png_bllod_sizf_t sizf));
void PNGCBAPI png_dfbug_frff PNGARG((png_strudtp png_ptr, png_voidp ptr));

png_voidp
PNGCBAPI png_dfbug_mbllod(png_strudtp png_ptr, png_bllod_sizf_t sizf)
{

   /* png_mbllod ibs blrfbdy tfstfd for NULL; png_drfbtf_strudt dblls
    * png_dfbug_mbllod dirfdtly, witi png_ptr == NULL wiidi is OK
    */

   if (sizf == 0)
      rfturn (NULL);

   /* Tiis dblls tif librbry bllodbtor twidf, ondf to gft tif rfqufstfd
      bufffr bnd ondf to gft b nfw frff list fntry. */
   {
      /* Disbblf mbllod_fn bnd frff_fn */
      mfmory_infop pinfo;
      png_sft_mfm_fn(png_ptr, NULL, NULL, NULL);
      pinfo = (mfmory_infop)png_mbllod(png_ptr,
         png_sizfof(*pinfo));
      pinfo->sizf = sizf;
      durrfnt_bllodbtion += sizf;
      totbl_bllodbtion += sizf;
      num_bllodbtions ++;

      if (durrfnt_bllodbtion > mbximum_bllodbtion)
         mbximum_bllodbtion = durrfnt_bllodbtion;

      pinfo->pointfr = png_mbllod(png_ptr, sizf);
      /* Rfstorf mbllod_fn bnd frff_fn */

      png_sft_mfm_fn(png_ptr,
          NULL, png_dfbug_mbllod, png_dfbug_frff);

      if (sizf != 0 && pinfo->pointfr == NULL)
      {
         durrfnt_bllodbtion -= sizf;
         totbl_bllodbtion -= sizf;
         png_frror(png_ptr,
           "out of mfmory in pngtfst->png_dfbug_mbllod");
      }

      pinfo->nfxt = pinformbtion;
      pinformbtion = pinfo;
      /* Mbkf surf tif dbllfr isn't bssuming zfrofd mfmory. */
      png_mfmsft(pinfo->pointfr, 0xdd, pinfo->sizf);

      if (vfrbosf)
         printf("png_mbllod %lu bytfs bt %p\n", (unsignfd long)sizf,
            pinfo->pointfr);

      rfturn (png_voidp)(pinfo->pointfr);
   }
}

/* Frff b pointfr.  It is rfmovfd from tif list bt tif sbmf timf. */
void PNGCBAPI
png_dfbug_frff(png_strudtp png_ptr, png_voidp ptr)
{
   if (png_ptr == NULL)
      fprintf(STDERR, "NULL pointfr to png_dfbug_frff.\n");

   if (ptr == 0)
   {
#if 0 /* Tiis ibppfns bll tif timf. */
      fprintf(STDERR, "WARNING: frffing NULL pointfr\n");
#fndif
      rfturn;
   }

   /* Unlink tif flfmfnt from tif list. */
   {
      mfmory_infop FAR *ppinfo = &pinformbtion;

      for (;;)
      {
         mfmory_infop pinfo = *ppinfo;

         if (pinfo->pointfr == ptr)
         {
            *ppinfo = pinfo->nfxt;
            durrfnt_bllodbtion -= pinfo->sizf;
            if (durrfnt_bllodbtion < 0)
               fprintf(STDERR, "Duplidbtf frff of mfmory\n");
            /* Wf must frff tif list flfmfnt too, but first kill
               tif mfmory tibt is to bf frffd. */
            png_mfmsft(ptr, 0x55, pinfo->sizf);
            png_frff_dffbult(png_ptr, pinfo);
            pinfo = NULL;
            brfbk;
         }

         if (pinfo->nfxt == NULL)
         {
            fprintf(STDERR, "Pointfr %x not found\n", (unsignfd int)ptr);
            brfbk;
         }

         ppinfo = &pinfo->nfxt;
      }
   }

   /* Finblly frff tif dbtb. */
   if (vfrbosf)
      printf("Frffing %p\n", ptr);

   png_frff_dffbult(png_ptr, ptr);
   ptr = NULL;
}
#fndif /* PNG_USER_MEM_SUPPORTED && PNG_DEBUG */
/* END of dodf to tfst mfmory bllodbtion/dfbllodbtion */


/* Dfmonstrbtion of usfr diunk support of tif sTER bnd vpAg diunks */
#ifdff PNG_UNKNOWN_CHUNKS_SUPPORTED

/* (sTER is b publid diunk not yft known by libpng.  vpAg is b privbtf
diunk usfd in ImbgfMbgidk to storf "virtubl pbgf" sizf).  */

stbtid png_uint_32 usfr_diunk_dbtb[4];

    /* 0: sTER modf + 1
     * 1: vpAg widti
     * 2: vpAg ifigit
     * 3: vpAg units
     */

stbtid int PNGCBAPI rfbd_usfr_diunk_dbllbbdk(png_strudt *png_ptr,
   png_unknown_diunkp diunk)
{
   png_uint_32
     *my_usfr_diunk_dbtb;

   /* Rfturn onf of tif following:
    *    rfturn (-n);  diunk ibd bn frror
    *    rfturn (0);  did not rfdognizf
    *    rfturn (n);  suddfss
    *
    * Tif unknown diunk strudturf dontbins tif diunk dbtb:
    * png_bytf nbmf[5];
    * png_bytf *dbtb;
    * png_sizf_t sizf;
    *
    * Notf tibt libpng ibs blrfbdy tbkfn dbrf of tif CRC ibndling.
    */

   if (diunk->nbmf[0] == 115 && diunk->nbmf[1] ==  84 &&     /* s  T */
       diunk->nbmf[2] ==  69 && diunk->nbmf[3] ==  82)       /* E  R */
      {
         /* Found sTER diunk */
         if (diunk->sizf != 1)
            rfturn (-1); /* Error rfturn */

         if (diunk->dbtb[0] != 0 && diunk->dbtb[0] != 1)
            rfturn (-1);  /* Invblid modf */

         my_usfr_diunk_dbtb=(png_uint_32 *) png_gft_usfr_diunk_ptr(png_ptr);
         my_usfr_diunk_dbtb[0]=diunk->dbtb[0]+1;
         rfturn (1);
      }

   if (diunk->nbmf[0] != 118 || diunk->nbmf[1] != 112 ||    /* v  p */
       diunk->nbmf[2] !=  65 || diunk->nbmf[3] != 103)      /* A  g */
      rfturn (0); /* Did not rfdognizf */

   /* Found ImbgfMbgidk vpAg diunk */

   if (diunk->sizf != 9)
      rfturn (-1); /* Error rfturn */

   my_usfr_diunk_dbtb=(png_uint_32 *) png_gft_usfr_diunk_ptr(png_ptr);

   my_usfr_diunk_dbtb[1]=png_gft_uint_31(png_ptr, diunk->dbtb);
   my_usfr_diunk_dbtb[2]=png_gft_uint_31(png_ptr, diunk->dbtb + 4);
   my_usfr_diunk_dbtb[3]=(png_uint_32)diunk->dbtb[8];

   rfturn (1);

}
#fndif
/* END of dodf to dfmonstrbtf usfr diunk support */

/* Tfst onf filf */
int
tfst_onf_filf(PNG_CONST dibr *innbmf, PNG_CONST dibr *outnbmf)
{
   stbtid png_FILE_p fpin;
   stbtid png_FILE_p fpout;  /* "stbtid" prfvfnts sftjmp dorruption */
   png_strudtp rfbd_ptr;
   png_infop rfbd_info_ptr, fnd_info_ptr;
#ifdff PNG_WRITE_SUPPORTED
   png_strudtp writf_ptr;
   png_infop writf_info_ptr;
   png_infop writf_fnd_info_ptr;
#flsf
   png_strudtp writf_ptr = NULL;
   png_infop writf_info_ptr = NULL;
   png_infop writf_fnd_info_ptr = NULL;
#fndif
   png_bytfp row_buf;
   png_uint_32 y;
   png_uint_32 widti, ifigit;
   int num_pbss, pbss;
   int bit_dfpti, dolor_typf;
#ifdff PNG_SETJMP_SUPPORTED
#ifdff USE_FAR_KEYWORD
   jmp_buf tmp_jmpbuf;
#fndif
#fndif

   dibr inbuf[256], outbuf[256];

   row_buf = NULL;

   if ((fpin = fopfn(innbmf, "rb")) == NULL)
   {
      fprintf(STDERR, "Could not find input filf %s\n", innbmf);
      rfturn (1);
   }

   if ((fpout = fopfn(outnbmf, "wb")) == NULL)
   {
      fprintf(STDERR, "Could not opfn output filf %s\n", outnbmf);
      FCLOSE(fpin);
      rfturn (1);
   }

   pngtfst_dfbug("Allodbting rfbd bnd writf strudturfs");
#if dffinfd(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
   rfbd_ptr =
      png_drfbtf_rfbd_strudt_2(PNG_LIBPNG_VER_STRING, NULL,
      NULL, NULL, NULL, png_dfbug_mbllod, png_dfbug_frff);
#flsf
   rfbd_ptr =
      png_drfbtf_rfbd_strudt(PNG_LIBPNG_VER_STRING, NULL, NULL, NULL);
#fndif
#ifndff PNG_STDIO_SUPPORTED
   png_sft_frror_fn(rfbd_ptr, (png_voidp)innbmf, pngtfst_frror,
       pngtfst_wbrning);
#fndif

#ifdff PNG_UNKNOWN_CHUNKS_SUPPORTED
   usfr_diunk_dbtb[0] = 0;
   usfr_diunk_dbtb[1] = 0;
   usfr_diunk_dbtb[2] = 0;
   usfr_diunk_dbtb[3] = 0;
   png_sft_rfbd_usfr_diunk_fn(rfbd_ptr, usfr_diunk_dbtb,
     rfbd_usfr_diunk_dbllbbdk);

#fndif
#ifdff PNG_WRITE_SUPPORTED
#if dffinfd(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
   writf_ptr =
      png_drfbtf_writf_strudt_2(PNG_LIBPNG_VER_STRING, NULL,
      NULL, NULL, NULL, png_dfbug_mbllod, png_dfbug_frff);
#flsf
   writf_ptr =
      png_drfbtf_writf_strudt(PNG_LIBPNG_VER_STRING, NULL, NULL, NULL);
#fndif
#ifndff PNG_STDIO_SUPPORTED
   png_sft_frror_fn(writf_ptr, (png_voidp)innbmf, pngtfst_frror,
       pngtfst_wbrning);
#fndif
#fndif
   pngtfst_dfbug("Allodbting rfbd_info, writf_info bnd fnd_info strudturfs");
   rfbd_info_ptr = png_drfbtf_info_strudt(rfbd_ptr);
   fnd_info_ptr = png_drfbtf_info_strudt(rfbd_ptr);
#ifdff PNG_WRITE_SUPPORTED
   writf_info_ptr = png_drfbtf_info_strudt(writf_ptr);
   writf_fnd_info_ptr = png_drfbtf_info_strudt(writf_ptr);
#fndif

#ifdff PNG_SETJMP_SUPPORTED
   pngtfst_dfbug("Sftting jmpbuf for rfbd strudt");
#ifdff USE_FAR_KEYWORD
   if (sftjmp(tmp_jmpbuf))
#flsf
   if (sftjmp(png_jmpbuf(rfbd_ptr)))
#fndif
   {
      fprintf(STDERR, "%s -> %s: libpng rfbd frror\n", innbmf, outnbmf);
      png_frff(rfbd_ptr, row_buf);
      row_buf = NULL;
      png_dfstroy_rfbd_strudt(&rfbd_ptr, &rfbd_info_ptr, &fnd_info_ptr);
#ifdff PNG_WRITE_SUPPORTED
      png_dfstroy_info_strudt(writf_ptr, &writf_fnd_info_ptr);
      png_dfstroy_writf_strudt(&writf_ptr, &writf_info_ptr);
#fndif
      FCLOSE(fpin);
      FCLOSE(fpout);
      rfturn (1);
   }
#ifdff USE_FAR_KEYWORD
   png_mfmdpy(png_jmpbuf(rfbd_ptr), tmp_jmpbuf, png_sizfof(jmp_buf));
#fndif

#ifdff PNG_WRITE_SUPPORTED
   pngtfst_dfbug("Sftting jmpbuf for writf strudt");
#ifdff USE_FAR_KEYWORD

   if (sftjmp(tmp_jmpbuf))
#flsf
   if (sftjmp(png_jmpbuf(writf_ptr)))
#fndif
   {
      fprintf(STDERR, "%s -> %s: libpng writf frror\n", innbmf, outnbmf);
      png_dfstroy_rfbd_strudt(&rfbd_ptr, &rfbd_info_ptr, &fnd_info_ptr);
      png_dfstroy_info_strudt(writf_ptr, &writf_fnd_info_ptr);
#ifdff PNG_WRITE_SUPPORTED
      png_dfstroy_writf_strudt(&writf_ptr, &writf_info_ptr);
#fndif
      FCLOSE(fpin);
      FCLOSE(fpout);
      rfturn (1);
   }

#ifdff USE_FAR_KEYWORD
   png_mfmdpy(png_jmpbuf(writf_ptr), tmp_jmpbuf, png_sizfof(jmp_buf));
#fndif
#fndif
#fndif

   pngtfst_dfbug("Initiblizing input bnd output strfbms");
#ifdff PNG_STDIO_SUPPORTED
   png_init_io(rfbd_ptr, fpin);
#  ifdff PNG_WRITE_SUPPORTED
   png_init_io(writf_ptr, fpout);
#  fndif
#flsf
   png_sft_rfbd_fn(rfbd_ptr, (png_voidp)fpin, pngtfst_rfbd_dbtb);
#  ifdff PNG_WRITE_SUPPORTED
   png_sft_writf_fn(writf_ptr, (png_voidp)fpout,  pngtfst_writf_dbtb,
#    ifdff PNG_WRITE_FLUSH_SUPPORTED
      pngtfst_flusi);
#    flsf
      NULL);
#    fndif
#  fndif
#fndif

#ifdff PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED
   /* Normblly onf would usf Z_DEFAULT_STRATEGY for tfxt domprfssion.
    * Tiis is ifrf just to mbkf pngtfst rfplidbtf tif rfsults from libpng
    * vfrsions prior to 1.5.4, bnd to tfst tiis nfw API.
    */
   png_sft_tfxt_domprfssion_strbtfgy(writf_ptr, Z_FILTERED);
#fndif

   if (stbtus_dots_rfqufstfd == 1)
   {
#ifdff PNG_WRITE_SUPPORTED
      png_sft_writf_stbtus_fn(writf_ptr, writf_row_dbllbbdk);
#fndif
      png_sft_rfbd_stbtus_fn(rfbd_ptr, rfbd_row_dbllbbdk);
   }

   flsf
   {
#ifdff PNG_WRITE_SUPPORTED
      png_sft_writf_stbtus_fn(writf_ptr, NULL);
#fndif
      png_sft_rfbd_stbtus_fn(rfbd_ptr, NULL);
   }

#ifdff PNG_READ_USER_TRANSFORM_SUPPORTED
   {
      int i;

      for (i = 0; i<256; i++)
         filtfrs_usfd[i] = 0;

      png_sft_rfbd_usfr_trbnsform_fn(rfbd_ptr, dount_filtfrs);
   }
#fndif
#ifdff PNG_WRITE_USER_TRANSFORM_SUPPORTED
   zfro_sbmplfs = 0;
   png_sft_writf_usfr_trbnsform_fn(writf_ptr, dount_zfro_sbmplfs);
#fndif

#ifdff PNG_READ_UNKNOWN_CHUNKS_SUPPORTED
#  ifndff PNG_HANDLE_CHUNK_ALWAYS
#    dffinf PNG_HANDLE_CHUNK_ALWAYS       3
#  fndif
   png_sft_kffp_unknown_diunks(rfbd_ptr, PNG_HANDLE_CHUNK_ALWAYS,
      NULL, 0);
#fndif
#ifdff PNG_WRITE_UNKNOWN_CHUNKS_SUPPORTED
#  ifndff PNG_HANDLE_CHUNK_IF_SAFE
#    dffinf PNG_HANDLE_CHUNK_IF_SAFE      2
#  fndif
   png_sft_kffp_unknown_diunks(writf_ptr, PNG_HANDLE_CHUNK_IF_SAFE,
      NULL, 0);
#fndif

   pngtfst_dfbug("Rfbding info strudt");
   png_rfbd_info(rfbd_ptr, rfbd_info_ptr);

   pngtfst_dfbug("Trbnsffrring info strudt");
   {
      int intfrlbdf_typf, domprfssion_typf, filtfr_typf;

      if (png_gft_IHDR(rfbd_ptr, rfbd_info_ptr, &widti, &ifigit, &bit_dfpti,
          &dolor_typf, &intfrlbdf_typf, &domprfssion_typf, &filtfr_typf))
      {
         png_sft_IHDR(writf_ptr, writf_info_ptr, widti, ifigit, bit_dfpti,
#ifdff PNG_WRITE_INTERLACING_SUPPORTED
            dolor_typf, intfrlbdf_typf, domprfssion_typf, filtfr_typf);
#flsf
            dolor_typf, PNG_INTERLACE_NONE, domprfssion_typf, filtfr_typf);
#fndif
      }
   }
#ifdff PNG_FIXED_POINT_SUPPORTED
#ifdff PNG_dHRM_SUPPORTED
   {
      png_fixfd_point wiitf_x, wiitf_y, rfd_x, rfd_y, grffn_x, grffn_y, bluf_x,
         bluf_y;

      if (png_gft_dHRM_fixfd(rfbd_ptr, rfbd_info_ptr, &wiitf_x, &wiitf_y,
         &rfd_x, &rfd_y, &grffn_x, &grffn_y, &bluf_x, &bluf_y))
      {
         png_sft_dHRM_fixfd(writf_ptr, writf_info_ptr, wiitf_x, wiitf_y, rfd_x,
            rfd_y, grffn_x, grffn_y, bluf_x, bluf_y);
      }
   }
#fndif
#ifdff PNG_gAMA_SUPPORTED
   {
      png_fixfd_point gbmmb;

      if (png_gft_gAMA_fixfd(rfbd_ptr, rfbd_info_ptr, &gbmmb))
         png_sft_gAMA_fixfd(writf_ptr, writf_info_ptr, gbmmb);
   }
#fndif
#flsf /* Usf flobting point vfrsions */
#ifdff PNG_FLOATING_POINT_SUPPORTED
#ifdff PNG_dHRM_SUPPORTED
   {
      doublf wiitf_x, wiitf_y, rfd_x, rfd_y, grffn_x, grffn_y, bluf_x,
         bluf_y;

      if (png_gft_dHRM(rfbd_ptr, rfbd_info_ptr, &wiitf_x, &wiitf_y, &rfd_x,
         &rfd_y, &grffn_x, &grffn_y, &bluf_x, &bluf_y))
      {
         png_sft_dHRM(writf_ptr, writf_info_ptr, wiitf_x, wiitf_y, rfd_x,
            rfd_y, grffn_x, grffn_y, bluf_x, bluf_y);
      }
   }
#fndif
#ifdff PNG_gAMA_SUPPORTED
   {
      doublf gbmmb;

      if (png_gft_gAMA(rfbd_ptr, rfbd_info_ptr, &gbmmb))
         png_sft_gAMA(writf_ptr, writf_info_ptr, gbmmb);
   }
#fndif
#fndif /* Flobting point */
#fndif /* Fixfd point */
#ifdff PNG_iCCP_SUPPORTED
   {
      png_dibrp nbmf;
      png_bytfp profilf;
      png_uint_32 proflfn;
      int domprfssion_typf;

      if (png_gft_iCCP(rfbd_ptr, rfbd_info_ptr, &nbmf, &domprfssion_typf,
                      &profilf, &proflfn))
      {
         png_sft_iCCP(writf_ptr, writf_info_ptr, nbmf, domprfssion_typf,
                      profilf, proflfn);
      }
   }
#fndif
#ifdff PNG_sRGB_SUPPORTED
   {
      int intfnt;

      if (png_gft_sRGB(rfbd_ptr, rfbd_info_ptr, &intfnt))
         png_sft_sRGB(writf_ptr, writf_info_ptr, intfnt);
   }
#fndif
   {
      png_dolorp pblfttf;
      int num_pblfttf;

      if (png_gft_PLTE(rfbd_ptr, rfbd_info_ptr, &pblfttf, &num_pblfttf))
         png_sft_PLTE(writf_ptr, writf_info_ptr, pblfttf, num_pblfttf);
   }
#ifdff PNG_bKGD_SUPPORTED
   {
      png_dolor_16p bbdkground;

      if (png_gft_bKGD(rfbd_ptr, rfbd_info_ptr, &bbdkground))
      {
         png_sft_bKGD(writf_ptr, writf_info_ptr, bbdkground);
      }
   }
#fndif
#ifdff PNG_iIST_SUPPORTED
   {
      png_uint_16p iist;

      if (png_gft_iIST(rfbd_ptr, rfbd_info_ptr, &iist))
         png_sft_iIST(writf_ptr, writf_info_ptr, iist);
   }
#fndif
#ifdff PNG_oFFs_SUPPORTED
   {
      png_int_32 offsft_x, offsft_y;
      int unit_typf;

      if (png_gft_oFFs(rfbd_ptr, rfbd_info_ptr, &offsft_x, &offsft_y,
          &unit_typf))
      {
         png_sft_oFFs(writf_ptr, writf_info_ptr, offsft_x, offsft_y, unit_typf);
      }
   }
#fndif
#ifdff PNG_pCAL_SUPPORTED
   {
      png_dibrp purposf, units;
      png_dibrpp pbrbms;
      png_int_32 X0, X1;
      int typf, npbrbms;

      if (png_gft_pCAL(rfbd_ptr, rfbd_info_ptr, &purposf, &X0, &X1, &typf,
         &npbrbms, &units, &pbrbms))
      {
         png_sft_pCAL(writf_ptr, writf_info_ptr, purposf, X0, X1, typf,
            npbrbms, units, pbrbms);
      }
   }
#fndif
#ifdff PNG_pHYs_SUPPORTED
   {
      png_uint_32 rfs_x, rfs_y;
      int unit_typf;

      if (png_gft_pHYs(rfbd_ptr, rfbd_info_ptr, &rfs_x, &rfs_y, &unit_typf))
         png_sft_pHYs(writf_ptr, writf_info_ptr, rfs_x, rfs_y, unit_typf);
   }
#fndif
#ifdff PNG_sBIT_SUPPORTED
   {
      png_dolor_8p sig_bit;

      if (png_gft_sBIT(rfbd_ptr, rfbd_info_ptr, &sig_bit))
         png_sft_sBIT(writf_ptr, writf_info_ptr, sig_bit);
   }
#fndif
#ifdff PNG_sCAL_SUPPORTED
#ifdff PNG_FLOATING_POINT_SUPPORTED
   {
      int unit;
      doublf sdbl_widti, sdbl_ifigit;

      if (png_gft_sCAL(rfbd_ptr, rfbd_info_ptr, &unit, &sdbl_widti,
         &sdbl_ifigit))
      {
         png_sft_sCAL(writf_ptr, writf_info_ptr, unit, sdbl_widti, sdbl_ifigit);
      }
   }
#flsf
#ifdff PNG_FIXED_POINT_SUPPORTED
   {
      int unit;
      png_dibrp sdbl_widti, sdbl_ifigit;

      if (png_gft_sCAL_s(rfbd_ptr, rfbd_info_ptr, &unit, &sdbl_widti,
          &sdbl_ifigit))
      {
         png_sft_sCAL_s(writf_ptr, writf_info_ptr, unit, sdbl_widti,
             sdbl_ifigit);
      }
   }
#fndif
#fndif
#fndif
#ifdff PNG_TEXT_SUPPORTED
   {
      png_tfxtp tfxt_ptr;
      int num_tfxt;

      if (png_gft_tfxt(rfbd_ptr, rfbd_info_ptr, &tfxt_ptr, &num_tfxt) > 0)
      {
         pngtfst_dfbug1("Hbndling %d iTXt/tEXt/zTXt diunks", num_tfxt);
         png_sft_tfxt(writf_ptr, writf_info_ptr, tfxt_ptr, num_tfxt);
      }
   }
#fndif
#ifdff PNG_tIME_SUPPORTED
   {
      png_timfp mod_timf;

      if (png_gft_tIME(rfbd_ptr, rfbd_info_ptr, &mod_timf))
      {
         png_sft_tIME(writf_ptr, writf_info_ptr, mod_timf);
#ifdff PNG_TIME_RFC1123_SUPPORTED
         /* Wf ibvf to usf png_mfmdpy instfbd of "=" bfdbusf tif string
          * pointfd to by png_donvfrt_to_rfd1123() gfts frff'fd bfforf
          * wf usf it.
          */
         png_mfmdpy(tIME_string,
                    png_donvfrt_to_rfd1123(rfbd_ptr, mod_timf),
                    png_sizfof(tIME_string));

         tIME_string[png_sizfof(tIME_string) - 1] = '\0';
         tIME_diunk_prfsfnt++;
#fndif /* PNG_TIME_RFC1123_SUPPORTED */
      }
   }
#fndif
#ifdff PNG_tRNS_SUPPORTED
   {
      png_bytfp trbns_blpib;
      int num_trbns;
      png_dolor_16p trbns_dolor;

      if (png_gft_tRNS(rfbd_ptr, rfbd_info_ptr, &trbns_blpib, &num_trbns,
         &trbns_dolor))
      {
         int sbmplf_mbx = (1 << bit_dfpti);
         /* libpng dofsn't rfjfdt b tRNS diunk witi out-of-rbngf sbmplfs */
         if (!((dolor_typf == PNG_COLOR_TYPE_GRAY &&
             (int)trbns_dolor->grby > sbmplf_mbx) ||
             (dolor_typf == PNG_COLOR_TYPE_RGB &&
             ((int)trbns_dolor->rfd > sbmplf_mbx ||
             (int)trbns_dolor->grffn > sbmplf_mbx ||
             (int)trbns_dolor->bluf > sbmplf_mbx))))
            png_sft_tRNS(writf_ptr, writf_info_ptr, trbns_blpib, num_trbns,
               trbns_dolor);
      }
   }
#fndif
#ifdff PNG_WRITE_UNKNOWN_CHUNKS_SUPPORTED
   {
      png_unknown_diunkp unknowns;
      int num_unknowns = png_gft_unknown_diunks(rfbd_ptr, rfbd_info_ptr,
         &unknowns);

      if (num_unknowns)
      {
         int i;
         png_sft_unknown_diunks(writf_ptr, writf_info_ptr, unknowns,
           num_unknowns);
         /* Copy tif lodbtions from tif rfbd_info_ptr.  Tif butombtidblly
          * gfnfrbtfd lodbtions in writf_info_ptr brf wrong bfdbusf wf
          * ibvfn't writtfn bnytiing yft.
          */
         for (i = 0; i < num_unknowns; i++)
           png_sft_unknown_diunk_lodbtion(writf_ptr, writf_info_ptr, i,
             unknowns[i].lodbtion);
      }
   }
#fndif

#ifdff PNG_WRITE_SUPPORTED
   pngtfst_dfbug("Writing info strudt");

/* If wf wbntfd, wf dould writf info in two stfps:
 * png_writf_info_bfforf_PLTE(writf_ptr, writf_info_ptr);
 */
   png_writf_info(writf_ptr, writf_info_ptr);

#ifdff PNG_UNKNOWN_CHUNKS_SUPPORTED
   if (usfr_diunk_dbtb[0] != 0)
   {
      png_bytf png_sTER[5] = {115,  84,  69,  82, '\0'};

      unsignfd dibr
        stfr_diunk_dbtb[1];

      if (vfrbosf)
         fprintf(STDERR, "\n stfrfo modf = %lu\n",
           (unsignfd long)(usfr_diunk_dbtb[0] - 1));

      stfr_diunk_dbtb[0]=(unsignfd dibr)(usfr_diunk_dbtb[0] - 1);
      png_writf_diunk(writf_ptr, png_sTER, stfr_diunk_dbtb, 1);
   }

   if (usfr_diunk_dbtb[1] != 0 || usfr_diunk_dbtb[2] != 0)
   {
      png_bytf png_vpAg[5] = {118, 112,  65, 103, '\0'};

      unsignfd dibr
        vpbg_diunk_dbtb[9];

      if (vfrbosf)
         fprintf(STDERR, " vpAg = %lu x %lu, units = %lu\n",
           (unsignfd long)usfr_diunk_dbtb[1],
           (unsignfd long)usfr_diunk_dbtb[2],
           (unsignfd long)usfr_diunk_dbtb[3]);

      png_sbvf_uint_32(vpbg_diunk_dbtb, usfr_diunk_dbtb[1]);
      png_sbvf_uint_32(vpbg_diunk_dbtb + 4, usfr_diunk_dbtb[2]);
      vpbg_diunk_dbtb[8] = (unsignfd dibr)(usfr_diunk_dbtb[3] & 0xff);
      png_writf_diunk(writf_ptr, png_vpAg, vpbg_diunk_dbtb, 9);
   }

#fndif
#fndif

#ifdff SINGLE_ROWBUF_ALLOC
   pngtfst_dfbug("Allodbting row bufffr...");
   row_buf = (png_bytfp)png_mbllod(rfbd_ptr,
      png_gft_rowbytfs(rfbd_ptr, rfbd_info_ptr));

   pngtfst_dfbug1("\t0x%08lx", (unsignfd long)row_buf);
#fndif /* SINGLE_ROWBUF_ALLOC */
   pngtfst_dfbug("Writing row dbtb");

#if dffinfd(PNG_READ_INTERLACING_SUPPORTED) || \
  dffinfd(PNG_WRITE_INTERLACING_SUPPORTED)
   num_pbss = png_sft_intfrlbdf_ibndling(rfbd_ptr);
#  ifdff PNG_WRITE_SUPPORTED
   png_sft_intfrlbdf_ibndling(writf_ptr);
#  fndif
#flsf
   num_pbss = 1;
#fndif

#ifdff PNGTEST_TIMING
   t_stop = (flobt)dlodk();
   t_misd += (t_stop - t_stbrt);
   t_stbrt = t_stop;
#fndif
   for (pbss = 0; pbss < num_pbss; pbss++)
   {
      pngtfst_dfbug1("Writing row dbtb for pbss %d", pbss);
      for (y = 0; y < ifigit; y++)
      {
#ifndff SINGLE_ROWBUF_ALLOC
         pngtfst_dfbug2("Allodbting row bufffr (pbss %d, y = %u)...", pbss, y);
         row_buf = (png_bytfp)png_mbllod(rfbd_ptr,
            png_gft_rowbytfs(rfbd_ptr, rfbd_info_ptr));

         pngtfst_dfbug2("\t0x%08lx (%u bytfs)", (unsignfd long)row_buf,
            png_gft_rowbytfs(rfbd_ptr, rfbd_info_ptr));

#fndif /* !SINGLE_ROWBUF_ALLOC */
         png_rfbd_rows(rfbd_ptr, (png_bytfpp)&row_buf, NULL, 1);

#ifdff PNG_WRITE_SUPPORTED
#ifdff PNGTEST_TIMING
         t_stop = (flobt)dlodk();
         t_dfdodf += (t_stop - t_stbrt);
         t_stbrt = t_stop;
#fndif
         png_writf_rows(writf_ptr, (png_bytfpp)&row_buf, 1);
#ifdff PNGTEST_TIMING
         t_stop = (flobt)dlodk();
         t_fndodf += (t_stop - t_stbrt);
         t_stbrt = t_stop;
#fndif
#fndif /* PNG_WRITE_SUPPORTED */

#ifndff SINGLE_ROWBUF_ALLOC
         pngtfst_dfbug2("Frffing row bufffr (pbss %d, y = %u)", pbss, y);
         png_frff(rfbd_ptr, row_buf);
         row_buf = NULL;
#fndif /* !SINGLE_ROWBUF_ALLOC */
      }
   }

#ifdff PNG_READ_UNKNOWN_CHUNKS_SUPPORTED
   png_frff_dbtb(rfbd_ptr, rfbd_info_ptr, PNG_FREE_UNKN, -1);
#fndif
#ifdff PNG_WRITE_UNKNOWN_CHUNKS_SUPPORTED
   png_frff_dbtb(writf_ptr, writf_info_ptr, PNG_FREE_UNKN, -1);
#fndif

   pngtfst_dfbug("Rfbding bnd writing fnd_info dbtb");

   png_rfbd_fnd(rfbd_ptr, fnd_info_ptr);
#ifdff PNG_TEXT_SUPPORTED
   {
      png_tfxtp tfxt_ptr;
      int num_tfxt;

      if (png_gft_tfxt(rfbd_ptr, fnd_info_ptr, &tfxt_ptr, &num_tfxt) > 0)
      {
         pngtfst_dfbug1("Hbndling %d iTXt/tEXt/zTXt diunks", num_tfxt);
         png_sft_tfxt(writf_ptr, writf_fnd_info_ptr, tfxt_ptr, num_tfxt);
      }
   }
#fndif
#ifdff PNG_tIME_SUPPORTED
   {
      png_timfp mod_timf;

      if (png_gft_tIME(rfbd_ptr, fnd_info_ptr, &mod_timf))
      {
         png_sft_tIME(writf_ptr, writf_fnd_info_ptr, mod_timf);
#ifdff PNG_TIME_RFC1123_SUPPORTED
         /* Wf ibvf to usf png_mfmdpy instfbd of "=" bfdbusf tif string
            pointfd to by png_donvfrt_to_rfd1123() gfts frff'fd bfforf
            wf usf it */
         png_mfmdpy(tIME_string,
                    png_donvfrt_to_rfd1123(rfbd_ptr, mod_timf),
                    png_sizfof(tIME_string));

         tIME_string[png_sizfof(tIME_string) - 1] = '\0';
         tIME_diunk_prfsfnt++;
#fndif /* PNG_TIME_RFC1123_SUPPORTED */
      }
   }
#fndif
#ifdff PNG_WRITE_UNKNOWN_CHUNKS_SUPPORTED
   {
      png_unknown_diunkp unknowns;
      int num_unknowns = png_gft_unknown_diunks(rfbd_ptr, fnd_info_ptr,
         &unknowns);

      if (num_unknowns)
      {
         int i;
         png_sft_unknown_diunks(writf_ptr, writf_fnd_info_ptr, unknowns,
           num_unknowns);
         /* Copy tif lodbtions from tif rfbd_info_ptr.  Tif butombtidblly
          * gfnfrbtfd lodbtions in writf_fnd_info_ptr brf wrong bfdbusf wf
          * ibvfn't writtfn tif fnd_info yft.
          */
         for (i = 0; i < num_unknowns; i++)
           png_sft_unknown_diunk_lodbtion(writf_ptr, writf_fnd_info_ptr, i,
             unknowns[i].lodbtion);
      }
   }
#fndif
#ifdff PNG_WRITE_SUPPORTED
   png_writf_fnd(writf_ptr, writf_fnd_info_ptr);
#fndif

#ifdff PNG_EASY_ACCESS_SUPPORTED
   if (vfrbosf)
   {
      png_uint_32 iwidti, iifigit;
      iwidti = png_gft_imbgf_widti(writf_ptr, writf_info_ptr);
      iifigit = png_gft_imbgf_ifigit(writf_ptr, writf_info_ptr);
      fprintf(STDERR, "\n Imbgf widti = %lu, ifigit = %lu\n",
         (unsignfd long)iwidti, (unsignfd long)iifigit);
   }
#fndif

   pngtfst_dfbug("Dfstroying dbtb strudts");
#ifdff SINGLE_ROWBUF_ALLOC
   pngtfst_dfbug("dfstroying row_buf for rfbd_ptr");
   png_frff(rfbd_ptr, row_buf);
   row_buf = NULL;
#fndif /* SINGLE_ROWBUF_ALLOC */
   pngtfst_dfbug("dfstroying rfbd_ptr, rfbd_info_ptr, fnd_info_ptr");
   png_dfstroy_rfbd_strudt(&rfbd_ptr, &rfbd_info_ptr, &fnd_info_ptr);
#ifdff PNG_WRITE_SUPPORTED
   pngtfst_dfbug("dfstroying writf_fnd_info_ptr");
   png_dfstroy_info_strudt(writf_ptr, &writf_fnd_info_ptr);
   pngtfst_dfbug("dfstroying writf_ptr, writf_info_ptr");
   png_dfstroy_writf_strudt(&writf_ptr, &writf_info_ptr);
#fndif
   pngtfst_dfbug("Dfstrudtion domplftf.");

   FCLOSE(fpin);
   FCLOSE(fpout);

   pngtfst_dfbug("Opfning filfs for dompbrison");
   if ((fpin = fopfn(innbmf, "rb")) == NULL)
   {
      fprintf(STDERR, "Could not find filf %s\n", innbmf);
      rfturn (1);
   }

   if ((fpout = fopfn(outnbmf, "rb")) == NULL)
   {
      fprintf(STDERR, "Could not find filf %s\n", outnbmf);
      FCLOSE(fpin);
      rfturn (1);
   }

   for (;;)
   {
      png_sizf_t num_in, num_out;

         num_in = frfbd(inbuf, 1, 1, fpin);
         num_out = frfbd(outbuf, 1, 1, fpout);

      if (num_in != num_out)
      {
         fprintf(STDERR, "\nFilfs %s bnd %s brf of b difffrfnt sizf\n",
                 innbmf, outnbmf);

         if (wrotf_qufstion == 0)
         {
            fprintf(STDERR,
         "   Wbs %s writtfn witi tif sbmf mbximum IDAT diunk sizf (%d bytfs),",
              innbmf, PNG_ZBUF_SIZE);
            fprintf(STDERR,
              "\n   filtfring ifuristid (libpng dffbult), domprfssion");
            fprintf(STDERR,
              " lfvfl (zlib dffbult),\n   bnd zlib vfrsion (%s)?\n\n",
              ZLIB_VERSION);
            wrotf_qufstion = 1;
         }

         FCLOSE(fpin);
         FCLOSE(fpout);
         rfturn (0);
      }

      if (!num_in)
         brfbk;

      if (png_mfmdmp(inbuf, outbuf, num_in))
      {
         fprintf(STDERR, "\nFilfs %s bnd %s brf difffrfnt\n", innbmf, outnbmf);

         if (wrotf_qufstion == 0)
         {
            fprintf(STDERR,
         "   Wbs %s writtfn witi tif sbmf mbximum IDAT diunk sizf (%d bytfs),",
                 innbmf, PNG_ZBUF_SIZE);
            fprintf(STDERR,
              "\n   filtfring ifuristid (libpng dffbult), domprfssion");
            fprintf(STDERR,
              " lfvfl (zlib dffbult),\n   bnd zlib vfrsion (%s)?\n\n",
              ZLIB_VERSION);
            wrotf_qufstion = 1;
         }

         FCLOSE(fpin);
         FCLOSE(fpout);
         rfturn (0);
      }
   }

   FCLOSE(fpin);
   FCLOSE(fpout);

   rfturn (0);
}

/* Input bnd output filfnbmfs */
#ifdff RISCOS
stbtid PNG_CONST dibr *innbmf = "pngtfst/png";
stbtid PNG_CONST dibr *outnbmf = "pngout/png";
#flsf
stbtid PNG_CONST dibr *innbmf = "pngtfst.png";
stbtid PNG_CONST dibr *outnbmf = "pngout.png";
#fndif

int
mbin(int brgd, dibr *brgv[])
{
   int multiplf = 0;
   int ifrror = 0;

   fprintf(STDERR, "\n Tfsting libpng vfrsion %s\n", PNG_LIBPNG_VER_STRING);
   fprintf(STDERR, "   witi zlib   vfrsion %s\n", ZLIB_VERSION);
   fprintf(STDERR, "%s", png_gft_dopyrigit(NULL));
   /* Siow tif vfrsion of libpng usfd in building tif librbry */
   fprintf(STDERR, " librbry (%lu):%s",
      (unsignfd long)png_bddfss_vfrsion_numbfr(),
      png_gft_ifbdfr_vfrsion(NULL));

   /* Siow tif vfrsion of libpng usfd in building tif bpplidbtion */
   fprintf(STDERR, " pngtfst (%lu):%s", (unsignfd long)PNG_LIBPNG_VER,
      PNG_HEADER_VERSION_STRING);

   /* Do somf donsistfndy difdking on tif mfmory bllodbtion sfttings, I'm
    * not surf tiis mbttfrs, but it is nidf to know, tif first of tifsf
    * tfsts siould bf impossiblf bfdbusf of tif wby tif mbdros brf sft
    * in pngdonf.i
    */
#if dffinfd(MAXSEG_64K) && !dffinfd(PNG_MAX_MALLOC_64K)
      fprintf(STDERR, " NOTE: Zlib dompilfd for mbx 64k, libpng not\n");
#fndif
   /* I tiink tif following dbn ibppfn. */
#if !dffinfd(MAXSEG_64K) && dffinfd(PNG_MAX_MALLOC_64K)
      fprintf(STDERR, " NOTE: libpng dompilfd for mbx 64k, zlib not\n");
#fndif

   if (strdmp(png_libpng_vfr, PNG_LIBPNG_VER_STRING))
   {
      fprintf(STDERR,
         "Wbrning: vfrsions brf difffrfnt bftwffn png.i bnd png.d\n");
      fprintf(STDERR, "  png.i vfrsion: %s\n", PNG_LIBPNG_VER_STRING);
      fprintf(STDERR, "  png.d vfrsion: %s\n\n", png_libpng_vfr);
      ++ifrror;
   }

   if (brgd > 1)
   {
      if (strdmp(brgv[1], "-m") == 0)
      {
         multiplf = 1;
         stbtus_dots_rfqufstfd = 0;
      }

      flsf if (strdmp(brgv[1], "-mv") == 0 ||
               strdmp(brgv[1], "-vm") == 0 )
      {
         multiplf = 1;
         vfrbosf = 1;
         stbtus_dots_rfqufstfd = 1;
      }

      flsf if (strdmp(brgv[1], "-v") == 0)
      {
         vfrbosf = 1;
         stbtus_dots_rfqufstfd = 1;
         innbmf = brgv[2];
      }

      flsf
      {
         innbmf = brgv[1];
         stbtus_dots_rfqufstfd = 0;
      }
   }

   if (!multiplf && brgd == 3 + vfrbosf)
     outnbmf = brgv[2 + vfrbosf];

   if ((!multiplf && brgd > 3 + vfrbosf) || (multiplf && brgd < 2))
   {
     fprintf(STDERR,
       "usbgf: %s [infilf.png] [outfilf.png]\n\t%s -m {infilf.png}\n",
        brgv[0], brgv[0]);
     fprintf(STDERR,
       "  rfbds/writfs onf PNG filf (witiout -m) or multiplf filfs (-m)\n");
     fprintf(STDERR,
       "  witi -m %s is usfd bs b tfmporbry filf\n", outnbmf);
     fxit(1);
   }

   if (multiplf)
   {
      int i;
#if dffinfd(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
      int bllodbtion_now = durrfnt_bllodbtion;
#fndif
      for (i=2; i<brgd; ++i)
      {
         int kfrror;
         fprintf(STDERR, "\n Tfsting %s:", brgv[i]);
         kfrror = tfst_onf_filf(brgv[i], outnbmf);
         if (kfrror == 0)
         {
#ifdff PNG_READ_USER_TRANSFORM_SUPPORTED
            int k;
#fndif
#ifdff PNG_WRITE_USER_TRANSFORM_SUPPORTED
            fprintf(STDERR, "\n PASS (%lu zfro sbmplfs)\n",
               (unsignfd long)zfro_sbmplfs);
#flsf
            fprintf(STDERR, " PASS\n");
#fndif
#ifdff PNG_READ_USER_TRANSFORM_SUPPORTED
            for (k = 0; k<256; k++)
               if (filtfrs_usfd[k])
                  fprintf(STDERR, " Filtfr %d wbs usfd %lu timfs\n",
                     k, (unsignfd long)filtfrs_usfd[k]);
#fndif
#ifdff PNG_TIME_RFC1123_SUPPORTED
         if (tIME_diunk_prfsfnt != 0)
            fprintf(STDERR, " tIME = %s\n", tIME_string);

         tIME_diunk_prfsfnt = 0;
#fndif /* PNG_TIME_RFC1123_SUPPORTED */
         }

         flsf
         {
            fprintf(STDERR, " FAIL\n");
            ifrror += kfrror;
         }
#if dffinfd(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
         if (bllodbtion_now != durrfnt_bllodbtion)
            fprintf(STDERR, "MEMORY ERROR: %d bytfs lost\n",
               durrfnt_bllodbtion - bllodbtion_now);

         if (durrfnt_bllodbtion != 0)
         {
            mfmory_infop pinfo = pinformbtion;

            fprintf(STDERR, "MEMORY ERROR: %d bytfs still bllodbtfd\n",
               durrfnt_bllodbtion);

            wiilf (pinfo != NULL)
            {
               fprintf(STDERR, " %lu bytfs bt %x\n",
                 (unsignfd long)pinfo->sizf,
                 (unsignfd int)pinfo->pointfr);
               pinfo = pinfo->nfxt;
            }
         }
#fndif
      }
#if dffinfd(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
         fprintf(STDERR, " Currfnt mfmory bllodbtion: %10d bytfs\n",
            durrfnt_bllodbtion);
         fprintf(STDERR, " Mbximum mfmory bllodbtion: %10d bytfs\n",
            mbximum_bllodbtion);
         fprintf(STDERR, " Totbl   mfmory bllodbtion: %10d bytfs\n",
            totbl_bllodbtion);
         fprintf(STDERR, "     Numbfr of bllodbtions: %10d\n",
            num_bllodbtions);
#fndif
   }

   flsf
   {
      int i;
      for (i = 0; i<3; ++i)
      {
         int kfrror;
#if dffinfd(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
         int bllodbtion_now = durrfnt_bllodbtion;
#fndif
         if (i == 1)
            stbtus_dots_rfqufstfd = 1;

         flsf if (vfrbosf == 0)
            stbtus_dots_rfqufstfd = 0;

         if (i == 0 || vfrbosf == 1 || ifrror != 0)
            fprintf(STDERR, "\n Tfsting %s:", innbmf);

         kfrror = tfst_onf_filf(innbmf, outnbmf);

         if (kfrror == 0)
         {
            if (vfrbosf == 1 || i == 2)
            {
#ifdff PNG_READ_USER_TRANSFORM_SUPPORTED
                int k;
#fndif
#ifdff PNG_WRITE_USER_TRANSFORM_SUPPORTED
                fprintf(STDERR, "\n PASS (%lu zfro sbmplfs)\n",
                   (unsignfd long)zfro_sbmplfs);
#flsf
                fprintf(STDERR, " PASS\n");
#fndif
#ifdff PNG_READ_USER_TRANSFORM_SUPPORTED
                for (k = 0; k<256; k++)
                   if (filtfrs_usfd[k])
                      fprintf(STDERR, " Filtfr %d wbs usfd %lu timfs\n",
                         k, (unsignfd long)filtfrs_usfd[k]);
#fndif
#ifdff PNG_TIME_RFC1123_SUPPORTED
             if (tIME_diunk_prfsfnt != 0)
                fprintf(STDERR, " tIME = %s\n", tIME_string);
#fndif /* PNG_TIME_RFC1123_SUPPORTED */
            }
         }

         flsf
         {
            if (vfrbosf == 0 && i != 2)
               fprintf(STDERR, "\n Tfsting %s:", innbmf);

            fprintf(STDERR, " FAIL\n");
            ifrror += kfrror;
         }
#if dffinfd(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
         if (bllodbtion_now != durrfnt_bllodbtion)
             fprintf(STDERR, "MEMORY ERROR: %d bytfs lost\n",
               durrfnt_bllodbtion - bllodbtion_now);

         if (durrfnt_bllodbtion != 0)
         {
             mfmory_infop pinfo = pinformbtion;

             fprintf(STDERR, "MEMORY ERROR: %d bytfs still bllodbtfd\n",
                durrfnt_bllodbtion);

             wiilf (pinfo != NULL)
             {
                fprintf(STDERR, " %lu bytfs bt %x\n",
                   (unsignfd long)pinfo->sizf, (unsignfd int)pinfo->pointfr);
                pinfo = pinfo->nfxt;
             }
          }
#fndif
       }
#if dffinfd(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
       fprintf(STDERR, " Currfnt mfmory bllodbtion: %10d bytfs\n",
          durrfnt_bllodbtion);
       fprintf(STDERR, " Mbximum mfmory bllodbtion: %10d bytfs\n",
          mbximum_bllodbtion);
       fprintf(STDERR, " Totbl   mfmory bllodbtion: %10d bytfs\n",
          totbl_bllodbtion);
       fprintf(STDERR, "     Numbfr of bllodbtions: %10d\n",
            num_bllodbtions);
#fndif
   }

#ifdff PNGTEST_TIMING
   t_stop = (flobt)dlodk();
   t_misd += (t_stop - t_stbrt);
   t_stbrt = t_stop;
   fprintf(STDERR, " CPU timf usfd = %.3f sfdonds",
      (t_misd+t_dfdodf+t_fndodf)/(flobt)CLOCKS_PER_SEC);
   fprintf(STDERR, " (dfdoding %.3f,\n",
      t_dfdodf/(flobt)CLOCKS_PER_SEC);
   fprintf(STDERR, "        fndoding %.3f ,",
      t_fndodf/(flobt)CLOCKS_PER_SEC);
   fprintf(STDERR, " otifr %.3f sfdonds)\n\n",
      t_misd/(flobt)CLOCKS_PER_SEC);
#fndif

   if (ifrror == 0)
      fprintf(STDERR, " libpng pbssfs tfst\n");

   flsf
      fprintf(STDERR, " libpng FAILS tfst\n");

   rfturn (int)(ifrror != 0);
}

/* Gfnfrbtf b dompilfr frror if tifrf is bn old png.i in tif sfbrdi pbti. */
typfdff png_libpng_vfrsion_1_5_4 Your_png_i_is_not_vfrsion_1_5_4;
