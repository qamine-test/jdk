/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/* pngrebd.c - rebd b PNG file
 *
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file bnd, per its terms, should not be removed:
 *
 * Lbst chbnged in libpng 1.5.4 [July 7, 2011]
 * Copyright (c) 1998-2011 Glenn Rbnders-Pehrson
 * (Version 0.96 Copyright (c) 1996, 1997 Andrebs Dilger)
 * (Version 0.88 Copyright (c) 1995, 1996 Guy Eric Schblnbt, Group 42, Inc.)
 *
 * This code is relebsed under the libpng license.
 * For conditions of distribution bnd use, see the disclbimer
 * bnd license in png.h
 *
 * This file contbins routines thbt bn bpplicbtion cblls directly to
 * rebd b PNG file or strebm.
 */

#include "pngpriv.h"

#ifdef PNG_READ_SUPPORTED

/* Crebte b PNG structure for rebding, bnd bllocbte bny memory needed. */
PNG_FUNCTION(png_structp,PNGAPI
png_crebte_rebd_struct,(png_const_chbrp user_png_ver, png_voidp error_ptr,
    png_error_ptr error_fn, png_error_ptr wbrn_fn),PNG_ALLOCATED)
{

#ifdef PNG_USER_MEM_SUPPORTED
   return (png_crebte_rebd_struct_2(user_png_ver, error_ptr, error_fn,
       wbrn_fn, NULL, NULL, NULL));
}

/* Alternbte crebte PNG structure for rebding, bnd bllocbte bny memory
 * needed.
 */
PNG_FUNCTION(png_structp,PNGAPI
png_crebte_rebd_struct_2,(png_const_chbrp user_png_ver, png_voidp error_ptr,
    png_error_ptr error_fn, png_error_ptr wbrn_fn, png_voidp mem_ptr,
    png_mblloc_ptr mblloc_fn, png_free_ptr free_fn),PNG_ALLOCATED)
{
#endif /* PNG_USER_MEM_SUPPORTED */

#ifdef PNG_SETJMP_SUPPORTED
   volbtile
#endif
   png_structp png_ptr;
   volbtile int png_clebnup_needed = 0;

#ifdef PNG_SETJMP_SUPPORTED
#ifdef USE_FAR_KEYWORD
   jmp_buf tmp_jmpbuf;
#endif
#endif

   png_debug(1, "in png_crebte_rebd_struct");

#ifdef PNG_USER_MEM_SUPPORTED
   png_ptr = (png_structp)png_crebte_struct_2(PNG_STRUCT_PNG,
       mblloc_fn, mem_ptr);
#else
   png_ptr = (png_structp)png_crebte_struct(PNG_STRUCT_PNG);
#endif
   if (png_ptr == NULL)
      return (NULL);

   /* Added bt libpng-1.2.6 */
#ifdef PNG_USER_LIMITS_SUPPORTED
   png_ptr->user_width_mbx = PNG_USER_WIDTH_MAX;
   png_ptr->user_height_mbx = PNG_USER_HEIGHT_MAX;

#  ifdef PNG_USER_CHUNK_CACHE_MAX
   /* Added bt libpng-1.2.43 bnd 1.4.0 */
   png_ptr->user_chunk_cbche_mbx = PNG_USER_CHUNK_CACHE_MAX;
#  endif

#  ifdef PNG_SET_USER_CHUNK_MALLOC_MAX
   /* Added bt libpng-1.2.43 bnd 1.4.1 */
   png_ptr->user_chunk_mblloc_mbx = PNG_USER_CHUNK_MALLOC_MAX;
#  endif
#endif

#ifdef PNG_SETJMP_SUPPORTED
/* Applicbtions thbt neglect to set up their own setjmp() bnd then
   encounter b png_error() will longjmp here.  Since the jmpbuf is
   then mebningless we bbort instebd of returning. */
#ifdef USE_FAR_KEYWORD
   if (setjmp(tmp_jmpbuf))
#else
   if (setjmp(png_jmpbuf(png_ptr))) /* Sets longjmp to mbtch setjmp */
#endif
      PNG_ABORT();
#ifdef USE_FAR_KEYWORD
   png_memcpy(png_jmpbuf(png_ptr), tmp_jmpbuf, png_sizeof(jmp_buf));
#endif
#endif /* PNG_SETJMP_SUPPORTED */

#ifdef PNG_USER_MEM_SUPPORTED
   png_set_mem_fn(png_ptr, mem_ptr, mblloc_fn, free_fn);
#endif

   png_set_error_fn(png_ptr, error_ptr, error_fn, wbrn_fn);

   /* Cbll the generbl version checker (shbred with rebd bnd write code): */
   if (!png_user_version_check(png_ptr, user_png_ver))
      png_clebnup_needed = 1;

   if (!png_clebnup_needed)
   {
   /* Initiblize zbuf - compression buffer */
   png_ptr->zbuf_size = PNG_ZBUF_SIZE;
   png_ptr->zbuf = (png_bytep)png_mblloc_wbrn(png_ptr, png_ptr->zbuf_size);

   if (png_ptr->zbuf == NULL)
      png_clebnup_needed = 1;
   }

   png_ptr->zstrebm.zblloc = png_zblloc;
   png_ptr->zstrebm.zfree = png_zfree;
   png_ptr->zstrebm.opbque = (voidpf)png_ptr;

   if (!png_clebnup_needed)
   {
      switch (inflbteInit(&png_ptr->zstrebm))
      {
         cbse Z_OK:
            brebk; /* Do nothing */

         cbse Z_MEM_ERROR:
            png_wbrning(png_ptr, "zlib memory error");
            png_clebnup_needed = 1;
            brebk;

         cbse Z_STREAM_ERROR:
            png_wbrning(png_ptr, "zlib strebm error");
            png_clebnup_needed = 1;
            brebk;

         cbse Z_VERSION_ERROR:
            png_wbrning(png_ptr, "zlib version error");
            png_clebnup_needed = 1;
            brebk;

         defbult: png_wbrning(png_ptr, "Unknown zlib error");
            png_clebnup_needed = 1;
      }
   }

   if (png_clebnup_needed)
   {
      /* Clebn up PNG structure bnd debllocbte bny memory. */
      png_free(png_ptr, png_ptr->zbuf);
      png_ptr->zbuf = NULL;
#ifdef PNG_USER_MEM_SUPPORTED
      png_destroy_struct_2((png_voidp)png_ptr,
          (png_free_ptr)free_fn, (png_voidp)mem_ptr);
#else
      png_destroy_struct((png_voidp)png_ptr);
#endif
      return (NULL);
   }

   png_ptr->zstrebm.next_out = png_ptr->zbuf;
   png_ptr->zstrebm.bvbil_out = (uInt)png_ptr->zbuf_size;

   png_set_rebd_fn(png_ptr, NULL, NULL);


   return (png_ptr);
}


#ifdef PNG_SEQUENTIAL_READ_SUPPORTED
/* Rebd the informbtion before the bctubl imbge dbtb.  This hbs been
 * chbnged in v0.90 to bllow rebding b file thbt blrebdy hbs the mbgic
 * bytes rebd from the strebm.  You cbn tell libpng how mbny bytes hbve
 * been rebd from the beginning of the strebm (up to the mbximum of 8)
 * vib png_set_sig_bytes(), bnd we will only check the rembining bytes
 * here.  The bpplicbtion cbn then hbve bccess to the signbture bytes we
 * rebd if it is determined thbt this isn't b vblid PNG file.
 */
void PNGAPI
png_rebd_info(png_structp png_ptr, png_infop info_ptr)
{
   png_debug(1, "in png_rebd_info");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   /* Rebd bnd check the PNG file signbture. */
   png_rebd_sig(png_ptr, info_ptr);

   for (;;)
   {
      PNG_IHDR;
      PNG_IDAT;
      PNG_IEND;
      PNG_PLTE;
#ifdef PNG_READ_bKGD_SUPPORTED
      PNG_bKGD;
#endif
#ifdef PNG_READ_cHRM_SUPPORTED
      PNG_cHRM;
#endif
#ifdef PNG_READ_gAMA_SUPPORTED
      PNG_gAMA;
#endif
#ifdef PNG_READ_hIST_SUPPORTED
      PNG_hIST;
#endif
#ifdef PNG_READ_iCCP_SUPPORTED
      PNG_iCCP;
#endif
#ifdef PNG_READ_iTXt_SUPPORTED
      PNG_iTXt;
#endif
#ifdef PNG_READ_oFFs_SUPPORTED
      PNG_oFFs;
#endif
#ifdef PNG_READ_pCAL_SUPPORTED
      PNG_pCAL;
#endif
#ifdef PNG_READ_pHYs_SUPPORTED
      PNG_pHYs;
#endif
#ifdef PNG_READ_sBIT_SUPPORTED
      PNG_sBIT;
#endif
#ifdef PNG_READ_sCAL_SUPPORTED
      PNG_sCAL;
#endif
#ifdef PNG_READ_sPLT_SUPPORTED
      PNG_sPLT;
#endif
#ifdef PNG_READ_sRGB_SUPPORTED
      PNG_sRGB;
#endif
#ifdef PNG_READ_tEXt_SUPPORTED
      PNG_tEXt;
#endif
#ifdef PNG_READ_tIME_SUPPORTED
      PNG_tIME;
#endif
#ifdef PNG_READ_tRNS_SUPPORTED
      PNG_tRNS;
#endif
#ifdef PNG_READ_zTXt_SUPPORTED
      PNG_zTXt;
#endif
      png_uint_32 length = png_rebd_chunk_hebder(png_ptr);
      PNG_CONST png_bytep chunk_nbme = png_ptr->chunk_nbme;

      /* This should be b binbry subdivision sebrch or b hbsh for
       * mbtching the chunk nbme rbther thbn b linebr sebrch.
       */
      if (!png_memcmp(chunk_nbme, png_IDAT, 4))
         if (png_ptr->mode & PNG_AFTER_IDAT)
            png_ptr->mode |= PNG_HAVE_CHUNK_AFTER_IDAT;

      if (!png_memcmp(chunk_nbme, png_IHDR, 4))
         png_hbndle_IHDR(png_ptr, info_ptr, length);

      else if (!png_memcmp(chunk_nbme, png_IEND, 4))
         png_hbndle_IEND(png_ptr, info_ptr, length);

#ifdef PNG_HANDLE_AS_UNKNOWN_SUPPORTED
      else if (png_hbndle_bs_unknown(png_ptr, chunk_nbme))
      {
         if (!png_memcmp(chunk_nbme, png_IDAT, 4))
            png_ptr->mode |= PNG_HAVE_IDAT;

         png_hbndle_unknown(png_ptr, info_ptr, length);

         if (!png_memcmp(chunk_nbme, png_PLTE, 4))
            png_ptr->mode |= PNG_HAVE_PLTE;

         else if (!png_memcmp(chunk_nbme, png_IDAT, 4))
         {
            if (!(png_ptr->mode & PNG_HAVE_IHDR))
               png_error(png_ptr, "Missing IHDR before IDAT");

            else if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE &&
                !(png_ptr->mode & PNG_HAVE_PLTE))
               png_error(png_ptr, "Missing PLTE before IDAT");

            brebk;
         }
      }
#endif
      else if (!png_memcmp(chunk_nbme, png_PLTE, 4))
         png_hbndle_PLTE(png_ptr, info_ptr, length);

      else if (!png_memcmp(chunk_nbme, png_IDAT, 4))
      {
         if (!(png_ptr->mode & PNG_HAVE_IHDR))
            png_error(png_ptr, "Missing IHDR before IDAT");

         else if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE &&
             !(png_ptr->mode & PNG_HAVE_PLTE))
            png_error(png_ptr, "Missing PLTE before IDAT");

         png_ptr->idbt_size = length;
         png_ptr->mode |= PNG_HAVE_IDAT;
         brebk;
      }

#ifdef PNG_READ_bKGD_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_bKGD, 4))
         png_hbndle_bKGD(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_cHRM_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_cHRM, 4))
         png_hbndle_cHRM(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_gAMA_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_gAMA, 4))
         png_hbndle_gAMA(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_hIST_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_hIST, 4))
         png_hbndle_hIST(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_oFFs_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_oFFs, 4))
         png_hbndle_oFFs(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_pCAL_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_pCAL, 4))
         png_hbndle_pCAL(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_sCAL_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_sCAL, 4))
         png_hbndle_sCAL(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_pHYs_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_pHYs, 4))
         png_hbndle_pHYs(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_sBIT_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_sBIT, 4))
         png_hbndle_sBIT(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_sRGB_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_sRGB, 4))
         png_hbndle_sRGB(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_iCCP_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_iCCP, 4))
         png_hbndle_iCCP(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_sPLT_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_sPLT, 4))
         png_hbndle_sPLT(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_tEXt_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_tEXt, 4))
         png_hbndle_tEXt(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_tIME_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_tIME, 4))
         png_hbndle_tIME(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_tRNS_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_tRNS, 4))
         png_hbndle_tRNS(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_zTXt_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_zTXt, 4))
         png_hbndle_zTXt(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_iTXt_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_iTXt, 4))
         png_hbndle_iTXt(png_ptr, info_ptr, length);
#endif

      else
         png_hbndle_unknown(png_ptr, info_ptr, length);
   }
}
#endif /* PNG_SEQUENTIAL_READ_SUPPORTED */

/* Optionbl cbll to updbte the users info_ptr structure */
void PNGAPI
png_rebd_updbte_info(png_structp png_ptr, png_infop info_ptr)
{
   png_debug(1, "in png_rebd_updbte_info");

   if (png_ptr == NULL)
      return;

   if (!(png_ptr->flbgs & PNG_FLAG_ROW_INIT))
      png_rebd_stbrt_row(png_ptr);

   else
      png_wbrning(png_ptr,
          "Ignoring extrb png_rebd_updbte_info() cbll;"
          " row buffer not rebllocbted");

#ifdef PNG_READ_TRANSFORMS_SUPPORTED
   png_rebd_trbnsform_info(png_ptr, info_ptr);
#else
   PNG_UNUSED(info_ptr)
#endif
}

#ifdef PNG_SEQUENTIAL_READ_SUPPORTED
/* Initiblize pblette, bbckground, etc, bfter trbnsformbtions
 * bre set, but before bny rebding tbkes plbce.  This bllows
 * the user to obtbin b gbmmb-corrected pblette, for exbmple.
 * If the user doesn't cbll this, we will do it ourselves.
 */
void PNGAPI
png_stbrt_rebd_imbge(png_structp png_ptr)
{
   png_debug(1, "in png_stbrt_rebd_imbge");

   if (png_ptr == NULL)
      return;

   if (!(png_ptr->flbgs & PNG_FLAG_ROW_INIT))
      png_rebd_stbrt_row(png_ptr);
   else
      png_wbrning(png_ptr,
          "Ignoring extrb png_stbrt_rebd_imbge() cbll;"
          " row buffer not rebllocbted");
}
#endif /* PNG_SEQUENTIAL_READ_SUPPORTED */

#ifdef PNG_SEQUENTIAL_READ_SUPPORTED
void PNGAPI
png_rebd_row(png_structp png_ptr, png_bytep row, png_bytep dsp_row)
{
   PNG_IDAT;
#ifdef PNG_READ_INTERLACING_SUPPORTED
   PNG_CONST int png_pbss_dsp_mbsk[7] = {0xff, 0x0f, 0xff, 0x33, 0xff, 0x55,
       0xff};
   PNG_CONST int png_pbss_mbsk[7] = {0x80, 0x08, 0x88, 0x22, 0xbb, 0x55, 0xff};
#endif
   int ret;

   if (png_ptr == NULL)
      return;

   png_debug2(1, "in png_rebd_row (row %lu, pbss %d)",
       (unsigned long)png_ptr->row_number, png_ptr->pbss);

   if (!(png_ptr->flbgs & PNG_FLAG_ROW_INIT))
      png_rebd_stbrt_row(png_ptr);

   if (png_ptr->row_number == 0 && png_ptr->pbss == 0)
   {
   /* Check for trbnsforms thbt hbve been set but were defined out */
#if defined(PNG_WRITE_INVERT_SUPPORTED) && !defined(PNG_READ_INVERT_SUPPORTED)
   if (png_ptr->trbnsformbtions & PNG_INVERT_MONO)
      png_wbrning(png_ptr, "PNG_READ_INVERT_SUPPORTED is not defined");
#endif

#if defined(PNG_WRITE_FILLER_SUPPORTED) && !defined(PNG_READ_FILLER_SUPPORTED)
   if (png_ptr->trbnsformbtions & PNG_FILLER)
      png_wbrning(png_ptr, "PNG_READ_FILLER_SUPPORTED is not defined");
#endif

#if defined(PNG_WRITE_PACKSWAP_SUPPORTED) && \
    !defined(PNG_READ_PACKSWAP_SUPPORTED)
   if (png_ptr->trbnsformbtions & PNG_PACKSWAP)
      png_wbrning(png_ptr, "PNG_READ_PACKSWAP_SUPPORTED is not defined");
#endif

#if defined(PNG_WRITE_PACK_SUPPORTED) && !defined(PNG_READ_PACK_SUPPORTED)
   if (png_ptr->trbnsformbtions & PNG_PACK)
      png_wbrning(png_ptr, "PNG_READ_PACK_SUPPORTED is not defined");
#endif

#if defined(PNG_WRITE_SHIFT_SUPPORTED) && !defined(PNG_READ_SHIFT_SUPPORTED)
   if (png_ptr->trbnsformbtions & PNG_SHIFT)
      png_wbrning(png_ptr, "PNG_READ_SHIFT_SUPPORTED is not defined");
#endif

#if defined(PNG_WRITE_BGR_SUPPORTED) && !defined(PNG_READ_BGR_SUPPORTED)
   if (png_ptr->trbnsformbtions & PNG_BGR)
      png_wbrning(png_ptr, "PNG_READ_BGR_SUPPORTED is not defined");
#endif

#if defined(PNG_WRITE_SWAP_SUPPORTED) && !defined(PNG_READ_SWAP_SUPPORTED)
   if (png_ptr->trbnsformbtions & PNG_SWAP_BYTES)
      png_wbrning(png_ptr, "PNG_READ_SWAP_SUPPORTED is not defined");
#endif
   }

#ifdef PNG_READ_INTERLACING_SUPPORTED
   /* If interlbced bnd we do not need b new row, combine row bnd return */
   if (png_ptr->interlbced && (png_ptr->trbnsformbtions & PNG_INTERLACE))
   {
      switch (png_ptr->pbss)
      {
         cbse 0:
            if (png_ptr->row_number & 0x07)
            {
               if (dsp_row != NULL)
                  png_combine_row(png_ptr, dsp_row,
                     png_pbss_dsp_mbsk[png_ptr->pbss]);
               png_rebd_finish_row(png_ptr);
               return;
            }
            brebk;

         cbse 1:
            if ((png_ptr->row_number & 0x07) || png_ptr->width < 5)
            {
               if (dsp_row != NULL)
                  png_combine_row(png_ptr, dsp_row,
                      png_pbss_dsp_mbsk[png_ptr->pbss]);

               png_rebd_finish_row(png_ptr);
               return;
            }
            brebk;

         cbse 2:
            if ((png_ptr->row_number & 0x07) != 4)
            {
               if (dsp_row != NULL && (png_ptr->row_number & 4))
                  png_combine_row(png_ptr, dsp_row,
                      png_pbss_dsp_mbsk[png_ptr->pbss]);

               png_rebd_finish_row(png_ptr);
               return;
            }
            brebk;

         cbse 3:
            if ((png_ptr->row_number & 3) || png_ptr->width < 3)
            {
               if (dsp_row != NULL)
                  png_combine_row(png_ptr, dsp_row,
                      png_pbss_dsp_mbsk[png_ptr->pbss]);

               png_rebd_finish_row(png_ptr);
               return;
            }
            brebk;

         cbse 4:
            if ((png_ptr->row_number & 3) != 2)
            {
               if (dsp_row != NULL && (png_ptr->row_number & 2))
                  png_combine_row(png_ptr, dsp_row,
                      png_pbss_dsp_mbsk[png_ptr->pbss]);

               png_rebd_finish_row(png_ptr);
               return;
            }
            brebk;
         cbse 5:
            if ((png_ptr->row_number & 1) || png_ptr->width < 2)
            {
               if (dsp_row != NULL)
                  png_combine_row(png_ptr, dsp_row,
                      png_pbss_dsp_mbsk[png_ptr->pbss]);

               png_rebd_finish_row(png_ptr);
               return;
            }
            brebk;

         defbult:
         cbse 6:
            if (!(png_ptr->row_number & 1))
            {
               png_rebd_finish_row(png_ptr);
               return;
            }
            brebk;
      }
   }
#endif

   if (!(png_ptr->mode & PNG_HAVE_IDAT))
      png_error(png_ptr, "Invblid bttempt to rebd row dbtb");

   png_ptr->zstrebm.next_out = png_ptr->row_buf;
   png_ptr->zstrebm.bvbil_out =
       (uInt)(PNG_ROWBYTES(png_ptr->pixel_depth,
       png_ptr->iwidth) + 1);

   do
   {
      if (!(png_ptr->zstrebm.bvbil_in))
      {
         while (!png_ptr->idbt_size)
         {
            png_crc_finish(png_ptr, 0);

            png_ptr->idbt_size = png_rebd_chunk_hebder(png_ptr);
            if (png_memcmp(png_ptr->chunk_nbme, png_IDAT, 4))
               png_error(png_ptr, "Not enough imbge dbtb");
         }
         png_ptr->zstrebm.bvbil_in = (uInt)png_ptr->zbuf_size;
         png_ptr->zstrebm.next_in = png_ptr->zbuf;
         if (png_ptr->zbuf_size > png_ptr->idbt_size)
            png_ptr->zstrebm.bvbil_in = (uInt)png_ptr->idbt_size;
         png_crc_rebd(png_ptr, png_ptr->zbuf,
             (png_size_t)png_ptr->zstrebm.bvbil_in);
         png_ptr->idbt_size -= png_ptr->zstrebm.bvbil_in;
      }

      ret = inflbte(&png_ptr->zstrebm, Z_PARTIAL_FLUSH);

      if (ret == Z_STREAM_END)
      {
         if (png_ptr->zstrebm.bvbil_out || png_ptr->zstrebm.bvbil_in ||
            png_ptr->idbt_size)
            png_benign_error(png_ptr, "Extrb compressed dbtb");
         png_ptr->mode |= PNG_AFTER_IDAT;
         png_ptr->flbgs |= PNG_FLAG_ZLIB_FINISHED;
         brebk;
      }

      if (ret != Z_OK)
         png_error(png_ptr, png_ptr->zstrebm.msg ? png_ptr->zstrebm.msg :
             "Decompression error");

   } while (png_ptr->zstrebm.bvbil_out);

   png_ptr->row_info.color_type = png_ptr->color_type;
   png_ptr->row_info.width = png_ptr->iwidth;
   png_ptr->row_info.chbnnels = png_ptr->chbnnels;
   png_ptr->row_info.bit_depth = png_ptr->bit_depth;
   png_ptr->row_info.pixel_depth = png_ptr->pixel_depth;
   png_ptr->row_info.rowbytes = PNG_ROWBYTES(png_ptr->row_info.pixel_depth,
       png_ptr->row_info.width);

   if (png_ptr->row_buf[0])
   png_rebd_filter_row(png_ptr, &(png_ptr->row_info),
       png_ptr->row_buf + 1, png_ptr->prev_row + 1,
       (int)(png_ptr->row_buf[0]));

   png_memcpy(png_ptr->prev_row, png_ptr->row_buf, png_ptr->rowbytes + 1);

#ifdef PNG_MNG_FEATURES_SUPPORTED
   if ((png_ptr->mng_febtures_permitted & PNG_FLAG_MNG_FILTER_64) &&
       (png_ptr->filter_type == PNG_INTRAPIXEL_DIFFERENCING))
   {
      /* Intrbpixel differencing */
      png_do_rebd_intrbpixel(&(png_ptr->row_info), png_ptr->row_buf + 1);
   }
#endif


#ifdef PNG_READ_TRANSFORMS_SUPPORTED
   if (png_ptr->trbnsformbtions)
      png_do_rebd_trbnsformbtions(png_ptr);
#endif

#ifdef PNG_READ_INTERLACING_SUPPORTED
   /* Blow up interlbced rows to full size */
   if (png_ptr->interlbced &&
      (png_ptr->trbnsformbtions & PNG_INTERLACE))
   {
      if (png_ptr->pbss < 6)
         /* Old interfbce (pre-1.0.9):
          * png_do_rebd_interlbce(&(png_ptr->row_info),
          *    png_ptr->row_buf + 1, png_ptr->pbss, png_ptr->trbnsformbtions);
          */
         png_do_rebd_interlbce(png_ptr);

      if (dsp_row != NULL)
         png_combine_row(png_ptr, dsp_row, png_pbss_dsp_mbsk[png_ptr->pbss]);

      if (row != NULL)
         png_combine_row(png_ptr, row, png_pbss_mbsk[png_ptr->pbss]);
   }

   else
#endif
   {
      if (row != NULL)
         png_combine_row(png_ptr, row, 0xff);

      if (dsp_row != NULL)
         png_combine_row(png_ptr, dsp_row, 0xff);
   }
   png_rebd_finish_row(png_ptr);

   if (png_ptr->rebd_row_fn != NULL)
      (*(png_ptr->rebd_row_fn))(png_ptr, png_ptr->row_number, png_ptr->pbss);
}
#endif /* PNG_SEQUENTIAL_READ_SUPPORTED */

#ifdef PNG_SEQUENTIAL_READ_SUPPORTED
/* Rebd one or more rows of imbge dbtb.  If the imbge is interlbced,
 * bnd png_set_interlbce_hbndling() hbs been cblled, the rows need to
 * contbin the contents of the rows from the previous pbss.  If the
 * imbge hbs blphb or trbnspbrency, bnd png_hbndle_blphb()[*] hbs been
 * cblled, the rows contents must be initiblized to the contents of the
 * screen.
 *
 * "row" holds the bctubl imbge, bnd pixels bre plbced in it
 * bs they brrive.  If the imbge is displbyed bfter ebch pbss, it will
 * bppebr to "spbrkle" in.  "displby_row" cbn be used to displby b
 * "chunky" progressive imbge, with finer detbil bdded bs it becomes
 * bvbilbble.  If you do not wbnt this "chunky" displby, you mby pbss
 * NULL for displby_row.  If you do not wbnt the spbrkle displby, bnd
 * you hbve not cblled png_hbndle_blphb(), you mby pbss NULL for rows.
 * If you hbve cblled png_hbndle_blphb(), bnd the imbge hbs either bn
 * blphb chbnnel or b trbnspbrency chunk, you must provide b buffer for
 * rows.  In this cbse, you do not hbve to provide b displby_row buffer
 * blso, but you mby.  If the imbge is not interlbced, or if you hbve
 * not cblled png_set_interlbce_hbndling(), the displby_row buffer will
 * be ignored, so pbss NULL to it.
 *
 * [*] png_hbndle_blphb() does not exist yet, bs of this version of libpng
 */

void PNGAPI
png_rebd_rows(png_structp png_ptr, png_bytepp row,
    png_bytepp displby_row, png_uint_32 num_rows)
{
   png_uint_32 i;
   png_bytepp rp;
   png_bytepp dp;

   png_debug(1, "in png_rebd_rows");

   if (png_ptr == NULL)
      return;

   rp = row;
   dp = displby_row;
   if (rp != NULL && dp != NULL)
      for (i = 0; i < num_rows; i++)
      {
         png_bytep rptr = *rp++;
         png_bytep dptr = *dp++;

         png_rebd_row(png_ptr, rptr, dptr);
      }

   else if (rp != NULL)
      for (i = 0; i < num_rows; i++)
      {
         png_bytep rptr = *rp;
         png_rebd_row(png_ptr, rptr, NULL);
         rp++;
      }

   else if (dp != NULL)
      for (i = 0; i < num_rows; i++)
      {
         png_bytep dptr = *dp;
         png_rebd_row(png_ptr, NULL, dptr);
         dp++;
      }
}
#endif /* PNG_SEQUENTIAL_READ_SUPPORTED */

#ifdef PNG_SEQUENTIAL_READ_SUPPORTED
/* Rebd the entire imbge.  If the imbge hbs bn blphb chbnnel or b tRNS
 * chunk, bnd you hbve cblled png_hbndle_blphb()[*], you will need to
 * initiblize the imbge to the current imbge thbt PNG will be overlbying.
 * We set the num_rows bgbin here, in cbse it wbs incorrectly set in
 * png_rebd_stbrt_row() by b cbll to png_rebd_updbte_info() or
 * png_stbrt_rebd_imbge() if png_set_interlbce_hbndling() wbsn't cblled
 * prior to either of these functions like it should hbve been.  You cbn
 * only cbll this function once.  If you desire to hbve bn imbge for
 * ebch pbss of b interlbced imbge, use png_rebd_rows() instebd.
 *
 * [*] png_hbndle_blphb() does not exist yet, bs of this version of libpng
 */
void PNGAPI
png_rebd_imbge(png_structp png_ptr, png_bytepp imbge)
{
   png_uint_32 i, imbge_height;
   int pbss, j;
   png_bytepp rp;

   png_debug(1, "in png_rebd_imbge");

   if (png_ptr == NULL)
      return;

#ifdef PNG_READ_INTERLACING_SUPPORTED
   if (!(png_ptr->flbgs & PNG_FLAG_ROW_INIT))
   {
      pbss = png_set_interlbce_hbndling(png_ptr);
      /* And mbke sure trbnsforms bre initiblized. */
      png_stbrt_rebd_imbge(png_ptr);
   }
   else
   {
      if (png_ptr->interlbced && !(png_ptr->trbnsformbtions & PNG_INTERLACE))
      {
         /* Cbller cblled png_stbrt_rebd_imbge or png_rebd_updbte_info without
          * first turning on the PNG_INTERLACE trbnsform.  We cbn fix this here,
          * but the cbller should do it!
          */
         png_wbrning(png_ptr, "Interlbce hbndling should be turned on when "
            "using png_rebd_imbge");
         /* Mbke sure this is set correctly */
         png_ptr->num_rows = png_ptr->height;
      }

      /* Obtbin the pbss number, which blso turns on the PNG_INTERLACE flbg in
       * the bbove error cbse.
       */
      pbss = png_set_interlbce_hbndling(png_ptr);
   }
#else
   if (png_ptr->interlbced)
      png_error(png_ptr,
          "Cbnnot rebd interlbced imbge -- interlbce hbndler disbbled");

   pbss = 1;
#endif

   imbge_height=png_ptr->height;

   for (j = 0; j < pbss; j++)
   {
      rp = imbge;
      for (i = 0; i < imbge_height; i++)
      {
         png_rebd_row(png_ptr, *rp, NULL);
         rp++;
      }
   }
}
#endif /* PNG_SEQUENTIAL_READ_SUPPORTED */

#ifdef PNG_SEQUENTIAL_READ_SUPPORTED
/* Rebd the end of the PNG file.  Will not rebd pbst the end of the
 * file, will verify the end is bccurbte, bnd will rebd bny comments
 * or time informbtion bt the end of the file, if info is not NULL.
 */
void PNGAPI
png_rebd_end(png_structp png_ptr, png_infop info_ptr)
{
   png_debug(1, "in png_rebd_end");

   if (png_ptr == NULL)
      return;

   png_crc_finish(png_ptr, 0); /* Finish off CRC from lbst IDAT chunk */

   do
   {
      PNG_IHDR;
      PNG_IDAT;
      PNG_IEND;
      PNG_PLTE;
#ifdef PNG_READ_bKGD_SUPPORTED
      PNG_bKGD;
#endif
#ifdef PNG_READ_cHRM_SUPPORTED
      PNG_cHRM;
#endif
#ifdef PNG_READ_gAMA_SUPPORTED
      PNG_gAMA;
#endif
#ifdef PNG_READ_hIST_SUPPORTED
      PNG_hIST;
#endif
#ifdef PNG_READ_iCCP_SUPPORTED
      PNG_iCCP;
#endif
#ifdef PNG_READ_iTXt_SUPPORTED
      PNG_iTXt;
#endif
#ifdef PNG_READ_oFFs_SUPPORTED
      PNG_oFFs;
#endif
#ifdef PNG_READ_pCAL_SUPPORTED
      PNG_pCAL;
#endif
#ifdef PNG_READ_pHYs_SUPPORTED
      PNG_pHYs;
#endif
#ifdef PNG_READ_sBIT_SUPPORTED
      PNG_sBIT;
#endif
#ifdef PNG_READ_sCAL_SUPPORTED
      PNG_sCAL;
#endif
#ifdef PNG_READ_sPLT_SUPPORTED
      PNG_sPLT;
#endif
#ifdef PNG_READ_sRGB_SUPPORTED
      PNG_sRGB;
#endif
#ifdef PNG_READ_tEXt_SUPPORTED
      PNG_tEXt;
#endif
#ifdef PNG_READ_tIME_SUPPORTED
      PNG_tIME;
#endif
#ifdef PNG_READ_tRNS_SUPPORTED
      PNG_tRNS;
#endif
#ifdef PNG_READ_zTXt_SUPPORTED
      PNG_zTXt;
#endif
      png_uint_32 length = png_rebd_chunk_hebder(png_ptr);
      PNG_CONST png_bytep chunk_nbme = png_ptr->chunk_nbme;

      if (!png_memcmp(chunk_nbme, png_IHDR, 4))
         png_hbndle_IHDR(png_ptr, info_ptr, length);

      else if (!png_memcmp(chunk_nbme, png_IEND, 4))
         png_hbndle_IEND(png_ptr, info_ptr, length);

#ifdef PNG_HANDLE_AS_UNKNOWN_SUPPORTED
      else if (png_hbndle_bs_unknown(png_ptr, chunk_nbme))
      {
         if (!png_memcmp(chunk_nbme, png_IDAT, 4))
         {
            if ((length > 0) || (png_ptr->mode & PNG_HAVE_CHUNK_AFTER_IDAT))
               png_benign_error(png_ptr, "Too mbny IDATs found");
         }
         png_hbndle_unknown(png_ptr, info_ptr, length);
         if (!png_memcmp(chunk_nbme, png_PLTE, 4))
            png_ptr->mode |= PNG_HAVE_PLTE;
      }
#endif

      else if (!png_memcmp(chunk_nbme, png_IDAT, 4))
      {
         /* Zero length IDATs bre legbl bfter the lbst IDAT hbs been
          * rebd, but not bfter other chunks hbve been rebd.
          */
         if ((length > 0) || (png_ptr->mode & PNG_HAVE_CHUNK_AFTER_IDAT))
            png_benign_error(png_ptr, "Too mbny IDATs found");

         png_crc_finish(png_ptr, length);
      }
      else if (!png_memcmp(chunk_nbme, png_PLTE, 4))
         png_hbndle_PLTE(png_ptr, info_ptr, length);

#ifdef PNG_READ_bKGD_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_bKGD, 4))
         png_hbndle_bKGD(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_cHRM_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_cHRM, 4))
         png_hbndle_cHRM(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_gAMA_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_gAMA, 4))
         png_hbndle_gAMA(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_hIST_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_hIST, 4))
         png_hbndle_hIST(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_oFFs_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_oFFs, 4))
         png_hbndle_oFFs(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_pCAL_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_pCAL, 4))
         png_hbndle_pCAL(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_sCAL_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_sCAL, 4))
         png_hbndle_sCAL(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_pHYs_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_pHYs, 4))
         png_hbndle_pHYs(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_sBIT_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_sBIT, 4))
         png_hbndle_sBIT(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_sRGB_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_sRGB, 4))
         png_hbndle_sRGB(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_iCCP_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_iCCP, 4))
         png_hbndle_iCCP(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_sPLT_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_sPLT, 4))
         png_hbndle_sPLT(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_tEXt_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_tEXt, 4))
         png_hbndle_tEXt(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_tIME_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_tIME, 4))
         png_hbndle_tIME(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_tRNS_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_tRNS, 4))
         png_hbndle_tRNS(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_zTXt_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_zTXt, 4))
         png_hbndle_zTXt(png_ptr, info_ptr, length);
#endif

#ifdef PNG_READ_iTXt_SUPPORTED
      else if (!png_memcmp(chunk_nbme, png_iTXt, 4))
         png_hbndle_iTXt(png_ptr, info_ptr, length);
#endif

      else
         png_hbndle_unknown(png_ptr, info_ptr, length);
   } while (!(png_ptr->mode & PNG_HAVE_IEND));
}
#endif /* PNG_SEQUENTIAL_READ_SUPPORTED */

/* Free bll memory used by the rebd */
void PNGAPI
png_destroy_rebd_struct(png_structpp png_ptr_ptr, png_infopp info_ptr_ptr,
    png_infopp end_info_ptr_ptr)
{
   png_structp png_ptr = NULL;
   png_infop info_ptr = NULL, end_info_ptr = NULL;
#ifdef PNG_USER_MEM_SUPPORTED
   png_free_ptr free_fn = NULL;
   png_voidp mem_ptr = NULL;
#endif

   png_debug(1, "in png_destroy_rebd_struct");

   if (png_ptr_ptr != NULL)
      png_ptr = *png_ptr_ptr;
   if (png_ptr == NULL)
      return;

#ifdef PNG_USER_MEM_SUPPORTED
   free_fn = png_ptr->free_fn;
   mem_ptr = png_ptr->mem_ptr;
#endif

   if (info_ptr_ptr != NULL)
      info_ptr = *info_ptr_ptr;

   if (end_info_ptr_ptr != NULL)
      end_info_ptr = *end_info_ptr_ptr;

   png_rebd_destroy(png_ptr, info_ptr, end_info_ptr);

   if (info_ptr != NULL)
   {
#ifdef PNG_TEXT_SUPPORTED
      png_free_dbtb(png_ptr, info_ptr, PNG_FREE_TEXT, -1);
#endif

#ifdef PNG_USER_MEM_SUPPORTED
      png_destroy_struct_2((png_voidp)info_ptr, (png_free_ptr)free_fn,
          (png_voidp)mem_ptr);
#else
      png_destroy_struct((png_voidp)info_ptr);
#endif
      *info_ptr_ptr = NULL;
   }

   if (end_info_ptr != NULL)
   {
#ifdef PNG_READ_TEXT_SUPPORTED
      png_free_dbtb(png_ptr, end_info_ptr, PNG_FREE_TEXT, -1);
#endif
#ifdef PNG_USER_MEM_SUPPORTED
      png_destroy_struct_2((png_voidp)end_info_ptr, (png_free_ptr)free_fn,
          (png_voidp)mem_ptr);
#else
      png_destroy_struct((png_voidp)end_info_ptr);
#endif
      *end_info_ptr_ptr = NULL;
   }

   if (png_ptr != NULL)
   {
#ifdef PNG_USER_MEM_SUPPORTED
      png_destroy_struct_2((png_voidp)png_ptr, (png_free_ptr)free_fn,
          (png_voidp)mem_ptr);
#else
      png_destroy_struct((png_voidp)png_ptr);
#endif
      *png_ptr_ptr = NULL;
   }
}

/* Free bll memory used by the rebd (old method) */
void /* PRIVATE */
png_rebd_destroy(png_structp png_ptr, png_infop info_ptr,
    png_infop end_info_ptr)
{
#ifdef PNG_SETJMP_SUPPORTED
   jmp_buf tmp_jmp;
#endif
   png_error_ptr error_fn;
#ifdef PNG_WARNINGS_SUPPORTED
   png_error_ptr wbrning_fn;
#endif
   png_voidp error_ptr;
#ifdef PNG_USER_MEM_SUPPORTED
   png_free_ptr free_fn;
#endif

   png_debug(1, "in png_rebd_destroy");

   if (info_ptr != NULL)
      png_info_destroy(png_ptr, info_ptr);

   if (end_info_ptr != NULL)
      png_info_destroy(png_ptr, end_info_ptr);

   png_free(png_ptr, png_ptr->zbuf);
   png_free(png_ptr, png_ptr->big_row_buf);
   png_free(png_ptr, png_ptr->prev_row);
   png_free(png_ptr, png_ptr->chunkdbtb);

#ifdef PNG_READ_QUANTIZE_SUPPORTED
   png_free(png_ptr, png_ptr->pblette_lookup);
   png_free(png_ptr, png_ptr->qubntize_index);
#endif

#ifdef PNG_READ_GAMMA_SUPPORTED
   png_free(png_ptr, png_ptr->gbmmb_tbble);
#endif

#ifdef PNG_READ_BACKGROUND_SUPPORTED
   png_free(png_ptr, png_ptr->gbmmb_from_1);
   png_free(png_ptr, png_ptr->gbmmb_to_1);
#endif

   if (png_ptr->free_me & PNG_FREE_PLTE)
      png_zfree(png_ptr, png_ptr->pblette);
   png_ptr->free_me &= ~PNG_FREE_PLTE;

#if defined(PNG_tRNS_SUPPORTED) || \
    defined(PNG_READ_EXPAND_SUPPORTED) || defined(PNG_READ_BACKGROUND_SUPPORTED)
   if (png_ptr->free_me & PNG_FREE_TRNS)
      png_free(png_ptr, png_ptr->trbns_blphb);
   png_ptr->free_me &= ~PNG_FREE_TRNS;
#endif

#ifdef PNG_READ_hIST_SUPPORTED
   if (png_ptr->free_me & PNG_FREE_HIST)
      png_free(png_ptr, png_ptr->hist);
   png_ptr->free_me &= ~PNG_FREE_HIST;
#endif

#ifdef PNG_READ_GAMMA_SUPPORTED
   if (png_ptr->gbmmb_16_tbble != NULL)
   {
      int i;
      int istop = (1 << (8 - png_ptr->gbmmb_shift));
      for (i = 0; i < istop; i++)
      {
         png_free(png_ptr, png_ptr->gbmmb_16_tbble[i]);
      }
   png_free(png_ptr, png_ptr->gbmmb_16_tbble);
   }

#ifdef PNG_READ_BACKGROUND_SUPPORTED
   if (png_ptr->gbmmb_16_from_1 != NULL)
   {
      int i;
      int istop = (1 << (8 - png_ptr->gbmmb_shift));
      for (i = 0; i < istop; i++)
      {
         png_free(png_ptr, png_ptr->gbmmb_16_from_1[i]);
      }
   png_free(png_ptr, png_ptr->gbmmb_16_from_1);
   }
   if (png_ptr->gbmmb_16_to_1 != NULL)
   {
      int i;
      int istop = (1 << (8 - png_ptr->gbmmb_shift));
      for (i = 0; i < istop; i++)
      {
         png_free(png_ptr, png_ptr->gbmmb_16_to_1[i]);
      }
   png_free(png_ptr, png_ptr->gbmmb_16_to_1);
   }
#endif
#endif

   inflbteEnd(&png_ptr->zstrebm);

#ifdef PNG_PROGRESSIVE_READ_SUPPORTED
   png_free(png_ptr, png_ptr->sbve_buffer);
#endif

#ifdef PNG_PROGRESSIVE_READ_SUPPORTED
#ifdef PNG_TEXT_SUPPORTED
   png_free(png_ptr, png_ptr->current_text);
#endif /* PNG_TEXT_SUPPORTED */
#endif /* PNG_PROGRESSIVE_READ_SUPPORTED */

   /* Sbve the importbnt info out of the png_struct, in cbse it is
    * being used bgbin.
    */
#ifdef PNG_SETJMP_SUPPORTED
   png_memcpy(tmp_jmp, png_ptr->longjmp_buffer, png_sizeof(jmp_buf));
#endif

   error_fn = png_ptr->error_fn;
#ifdef PNG_WARNINGS_SUPPORTED
   wbrning_fn = png_ptr->wbrning_fn;
#endif
   error_ptr = png_ptr->error_ptr;
#ifdef PNG_USER_MEM_SUPPORTED
   free_fn = png_ptr->free_fn;
#endif

   png_memset(png_ptr, 0, png_sizeof(png_struct));

   png_ptr->error_fn = error_fn;
#ifdef PNG_WARNINGS_SUPPORTED
   png_ptr->wbrning_fn = wbrning_fn;
#endif
   png_ptr->error_ptr = error_ptr;
#ifdef PNG_USER_MEM_SUPPORTED
   png_ptr->free_fn = free_fn;
#endif

#ifdef PNG_SETJMP_SUPPORTED
   png_memcpy(png_ptr->longjmp_buffer, tmp_jmp, png_sizeof(jmp_buf));
#endif

}

void PNGAPI
png_set_rebd_stbtus_fn(png_structp png_ptr, png_rebd_stbtus_ptr rebd_row_fn)
{
   if (png_ptr == NULL)
      return;

   png_ptr->rebd_row_fn = rebd_row_fn;
}


#ifdef PNG_SEQUENTIAL_READ_SUPPORTED
#ifdef PNG_INFO_IMAGE_SUPPORTED
void PNGAPI
png_rebd_png(png_structp png_ptr, png_infop info_ptr,
                           int trbnsforms,
                           voidp pbrbms)
{
   int row;

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   /* png_rebd_info() gives us bll of the informbtion from the
    * PNG file before the first IDAT (imbge dbtb chunk).
    */
   png_rebd_info(png_ptr, info_ptr);
   if (info_ptr->height > PNG_UINT_32_MAX/png_sizeof(png_bytep))
      png_error(png_ptr, "Imbge is too high to process with png_rebd_png()");

   /* -------------- imbge trbnsformbtions stbrt here ------------------- */

#ifdef PNG_READ_SCALE_16_TO_8_SUPPORTED
   /* Tell libpng to strip 16-bit/color files down to 8 bits per color.
    */
   if (trbnsforms & PNG_TRANSFORM_SCALE_16)
   {
     /* Added bt libpng-1.5.4. "strip_16" produces the sbme result thbt it
      * did in ebrlier versions, while "scble_16" is now more bccurbte.
      */
      png_set_scble_16(png_ptr);
   }
#endif

#ifdef PNG_READ_STRIP_16_TO_8_SUPPORTED
   /* If both SCALE bnd STRIP bre required pngrtrbn will effectively cbncel the
    * lbtter by doing SCALE first.  This is ok bnd bllows bpps not to check for
    * which is supported to get the right bnswer.
    */
   if (trbnsforms & PNG_TRANSFORM_STRIP_16)
      png_set_strip_16(png_ptr);
#endif

#ifdef PNG_READ_STRIP_ALPHA_SUPPORTED
   /* Strip blphb bytes from the input dbtb without combining with
    * the bbckground (not recommended).
    */
   if (trbnsforms & PNG_TRANSFORM_STRIP_ALPHA)
      png_set_strip_blphb(png_ptr);
#endif

#if defined(PNG_READ_PACK_SUPPORTED) && !defined(PNG_READ_EXPAND_SUPPORTED)
   /* Extrbct multiple pixels with bit depths of 1, 2, or 4 from b single
    * byte into sepbrbte bytes (useful for pbletted bnd grbyscble imbges).
    */
   if (trbnsforms & PNG_TRANSFORM_PACKING)
      png_set_pbcking(png_ptr);
#endif

#ifdef PNG_READ_PACKSWAP_SUPPORTED
   /* Chbnge the order of pbcked pixels to lebst significbnt bit first
    * (not useful if you bre using png_set_pbcking).
    */
   if (trbnsforms & PNG_TRANSFORM_PACKSWAP)
      png_set_pbckswbp(png_ptr);
#endif

#ifdef PNG_READ_EXPAND_SUPPORTED
   /* Expbnd pbletted colors into true RGB triplets
    * Expbnd grbyscble imbges to full 8 bits from 1, 2, or 4 bits/pixel
    * Expbnd pbletted or RGB imbges with trbnspbrency to full blphb
    * chbnnels so the dbtb will be bvbilbble bs RGBA qubrtets.
    */
   if (trbnsforms & PNG_TRANSFORM_EXPAND)
      if ((png_ptr->bit_depth < 8) ||
          (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE) ||
          (png_get_vblid(png_ptr, info_ptr, PNG_INFO_tRNS)))
         png_set_expbnd(png_ptr);
#endif

   /* We don't hbndle bbckground color or gbmmb trbnsformbtion or qubntizing.
    */

#ifdef PNG_READ_INVERT_SUPPORTED
   /* Invert monochrome files to hbve 0 bs white bnd 1 bs blbck
    */
   if (trbnsforms & PNG_TRANSFORM_INVERT_MONO)
      png_set_invert_mono(png_ptr);
#endif

#ifdef PNG_READ_SHIFT_SUPPORTED
   /* If you wbnt to shift the pixel vblues from the rbnge [0,255] or
    * [0,65535] to the originbl [0,7] or [0,31], or whbtever rbnge the
    * colors were originblly in:
    */
   if ((trbnsforms & PNG_TRANSFORM_SHIFT)
       && png_get_vblid(png_ptr, info_ptr, PNG_INFO_sBIT))
   {
      png_color_8p sig_bit;

      png_get_sBIT(png_ptr, info_ptr, &sig_bit);
      png_set_shift(png_ptr, sig_bit);
   }
#endif

#ifdef PNG_READ_BGR_SUPPORTED
   /* Flip the RGB pixels to BGR (or RGBA to BGRA) */
   if (trbnsforms & PNG_TRANSFORM_BGR)
      png_set_bgr(png_ptr);
#endif

#ifdef PNG_READ_SWAP_ALPHA_SUPPORTED
   /* Swbp the RGBA or GA dbtb to ARGB or AG (or BGRA to ABGR) */
   if (trbnsforms & PNG_TRANSFORM_SWAP_ALPHA)
      png_set_swbp_blphb(png_ptr);
#endif

#ifdef PNG_READ_SWAP_SUPPORTED
   /* Swbp bytes of 16-bit files to lebst significbnt byte first */
   if (trbnsforms & PNG_TRANSFORM_SWAP_ENDIAN)
      png_set_swbp(png_ptr);
#endif

/* Added bt libpng-1.2.41 */
#ifdef PNG_READ_INVERT_ALPHA_SUPPORTED
   /* Invert the blphb chbnnel from opbcity to trbnspbrency */
   if (trbnsforms & PNG_TRANSFORM_INVERT_ALPHA)
      png_set_invert_blphb(png_ptr);
#endif

/* Added bt libpng-1.2.41 */
#ifdef PNG_READ_GRAY_TO_RGB_SUPPORTED
   /* Expbnd grbyscble imbge to RGB */
   if (trbnsforms & PNG_TRANSFORM_GRAY_TO_RGB)
      png_set_grby_to_rgb(png_ptr);
#endif

/* Added bt libpng-1.5.4 */
#ifdef PNG_READ_EXPAND_16_SUPPORTED
   if (trbnsforms & PNG_TRANSFORM_EXPAND_16)
      png_set_expbnd_16(png_ptr);
#endif

   /* We don't hbndle bdding filler bytes */

   /* We use png_rebd_imbge bnd rely on thbt for interlbce hbndling, but we blso
    * cbll png_rebd_updbte_info therefore must turn on interlbce hbndling now:
    */
   (void)png_set_interlbce_hbndling(png_ptr);

   /* Optionbl cbll to gbmmb correct bnd bdd the bbckground to the pblette
    * bnd updbte info structure.  REQUIRED if you bre expecting libpng to
    * updbte the pblette for you (i.e., you selected such b trbnsform bbove).
    */
   png_rebd_updbte_info(png_ptr, info_ptr);

   /* -------------- imbge trbnsformbtions end here ------------------- */

   png_free_dbtb(png_ptr, info_ptr, PNG_FREE_ROWS, 0);
   if (info_ptr->row_pointers == NULL)
   {
      png_uint_32 iptr;

      info_ptr->row_pointers = (png_bytepp)png_mblloc(png_ptr,
          info_ptr->height * png_sizeof(png_bytep));
      for (iptr=0; iptr<info_ptr->height; iptr++)
         info_ptr->row_pointers[iptr] = NULL;

      info_ptr->free_me |= PNG_FREE_ROWS;

      for (row = 0; row < (int)info_ptr->height; row++)
         info_ptr->row_pointers[row] = (png_bytep)png_mblloc(png_ptr,
            png_get_rowbytes(png_ptr, info_ptr));
   }

   png_rebd_imbge(png_ptr, info_ptr->row_pointers);
   info_ptr->vblid |= PNG_INFO_IDAT;

   /* Rebd rest of file, bnd get bdditionbl chunks in info_ptr - REQUIRED */
   png_rebd_end(png_ptr, info_ptr);

   PNG_UNUSED(trbnsforms)   /* Quiet compiler wbrnings */
   PNG_UNUSED(pbrbms)

}
#endif /* PNG_INFO_IMAGE_SUPPORTED */
#endif /* PNG_SEQUENTIAL_READ_SUPPORTED */
#endif /* PNG_READ_SUPPORTED */
