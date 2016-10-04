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

/* pngwrite.c - generbl routines to write b PNG file
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
 */

#include "pngpriv.h"

#ifdef PNG_WRITE_SUPPORTED

/* Writes bll the PNG informbtion.  This is the suggested wby to use the
 * librbry.  If you hbve b new chunk to bdd, mbke b function to write it,
 * bnd put it in the correct locbtion here.  If you wbnt the chunk written
 * bfter the imbge dbtb, put it in png_write_end().  I strongly encourbge
 * you to supply b PNG_INFO_ flbg, bnd check info_ptr->vblid before writing
 * the chunk, bs thbt will keep the code from brebking if you wbnt to just
 * write b plbin PNG file.  If you hbve long comments, I suggest writing
 * them in png_write_end(), bnd compressing them.
 */
void PNGAPI
png_write_info_before_PLTE(png_structp png_ptr, png_infop info_ptr)
{
   png_debug(1, "in png_write_info_before_PLTE");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   if (!(png_ptr->mode & PNG_WROTE_INFO_BEFORE_PLTE))
   {
   /* Write PNG signbture */
   png_write_sig(png_ptr);

#ifdef PNG_MNG_FEATURES_SUPPORTED
   if ((png_ptr->mode&PNG_HAVE_PNG_SIGNATURE) && \
       (png_ptr->mng_febtures_permitted))
   {
      png_wbrning(png_ptr, "MNG febtures bre not bllowed in b PNG dbtbstrebm");
      png_ptr->mng_febtures_permitted = 0;
   }
#endif

   /* Write IHDR informbtion. */
   png_write_IHDR(png_ptr, info_ptr->width, info_ptr->height,
       info_ptr->bit_depth, info_ptr->color_type, info_ptr->compression_type,
       info_ptr->filter_type,
#ifdef PNG_WRITE_INTERLACING_SUPPORTED
       info_ptr->interlbce_type);
#else
       0);
#endif
   /* The rest of these check to see if the vblid field hbs the bppropribte
    * flbg set, bnd if it does, writes the chunk.
    */
#ifdef PNG_WRITE_gAMA_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_gAMA)
      png_write_gAMA_fixed(png_ptr, info_ptr->gbmmb);
#endif
#ifdef PNG_WRITE_sRGB_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_sRGB)
      png_write_sRGB(png_ptr, (int)info_ptr->srgb_intent);
#endif

#ifdef PNG_WRITE_iCCP_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_iCCP)
      png_write_iCCP(png_ptr, info_ptr->iccp_nbme, PNG_COMPRESSION_TYPE_BASE,
          (png_chbrp)info_ptr->iccp_profile, (int)info_ptr->iccp_proflen);
#endif
#ifdef PNG_WRITE_sBIT_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_sBIT)
      png_write_sBIT(png_ptr, &(info_ptr->sig_bit), info_ptr->color_type);
#endif
#ifdef PNG_WRITE_cHRM_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_cHRM)
      png_write_cHRM_fixed(png_ptr,
          info_ptr->x_white, info_ptr->y_white,
          info_ptr->x_red, info_ptr->y_red,
          info_ptr->x_green, info_ptr->y_green,
          info_ptr->x_blue, info_ptr->y_blue);
#endif

#ifdef PNG_WRITE_UNKNOWN_CHUNKS_SUPPORTED
   if (info_ptr->unknown_chunks_num)
   {
      png_unknown_chunk *up;

      png_debug(5, "writing extrb chunks");

      for (up = info_ptr->unknown_chunks;
           up < info_ptr->unknown_chunks + info_ptr->unknown_chunks_num;
           up++)
      {
         int keep = png_hbndle_bs_unknown(png_ptr, up->nbme);

         if (keep != PNG_HANDLE_CHUNK_NEVER &&
             up->locbtion &&
             !(up->locbtion & PNG_HAVE_PLTE) &&
             !(up->locbtion & PNG_HAVE_IDAT) &&
             !(up->locbtion & PNG_AFTER_IDAT) &&
             ((up->nbme[3] & 0x20) || keep == PNG_HANDLE_CHUNK_ALWAYS ||
             (png_ptr->flbgs & PNG_FLAG_KEEP_UNSAFE_CHUNKS)))
         {
            if (up->size == 0)
               png_wbrning(png_ptr, "Writing zero-length unknown chunk");

            png_write_chunk(png_ptr, up->nbme, up->dbtb, up->size);
         }
      }
   }
#endif
      png_ptr->mode |= PNG_WROTE_INFO_BEFORE_PLTE;
   }
}

void PNGAPI
png_write_info(png_structp png_ptr, png_infop info_ptr)
{
#if defined(PNG_WRITE_TEXT_SUPPORTED) || defined(PNG_WRITE_sPLT_SUPPORTED)
   int i;
#endif

   png_debug(1, "in png_write_info");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   png_write_info_before_PLTE(png_ptr, info_ptr);

   if (info_ptr->vblid & PNG_INFO_PLTE)
      png_write_PLTE(png_ptr, info_ptr->pblette,
          (png_uint_32)info_ptr->num_pblette);

   else if (info_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
      png_error(png_ptr, "Vblid pblette required for pbletted imbges");

#ifdef PNG_WRITE_tRNS_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_tRNS)
   {
#ifdef PNG_WRITE_INVERT_ALPHA_SUPPORTED
      /* Invert the blphb chbnnel (in tRNS) */
      if ((png_ptr->trbnsformbtions & PNG_INVERT_ALPHA) &&
          info_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
      {
         int j;
         for (j = 0; j<(int)info_ptr->num_trbns; j++)
            info_ptr->trbns_blphb[j] =
               (png_byte)(255 - info_ptr->trbns_blphb[j]);
      }
#endif
      png_write_tRNS(png_ptr, info_ptr->trbns_blphb, &(info_ptr->trbns_color),
          info_ptr->num_trbns, info_ptr->color_type);
   }
#endif
#ifdef PNG_WRITE_bKGD_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_bKGD)
      png_write_bKGD(png_ptr, &(info_ptr->bbckground), info_ptr->color_type);
#endif

#ifdef PNG_WRITE_hIST_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_hIST)
      png_write_hIST(png_ptr, info_ptr->hist, info_ptr->num_pblette);
#endif

#ifdef PNG_WRITE_oFFs_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_oFFs)
      png_write_oFFs(png_ptr, info_ptr->x_offset, info_ptr->y_offset,
          info_ptr->offset_unit_type);
#endif

#ifdef PNG_WRITE_pCAL_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_pCAL)
      png_write_pCAL(png_ptr, info_ptr->pcbl_purpose, info_ptr->pcbl_X0,
          info_ptr->pcbl_X1, info_ptr->pcbl_type, info_ptr->pcbl_npbrbms,
          info_ptr->pcbl_units, info_ptr->pcbl_pbrbms);
#endif

#ifdef PNG_WRITE_sCAL_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_sCAL)
      png_write_sCAL_s(png_ptr, (int)info_ptr->scbl_unit,
          info_ptr->scbl_s_width, info_ptr->scbl_s_height);
#endif /* sCAL */

#ifdef PNG_WRITE_pHYs_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_pHYs)
      png_write_pHYs(png_ptr, info_ptr->x_pixels_per_unit,
          info_ptr->y_pixels_per_unit, info_ptr->phys_unit_type);
#endif /* pHYs */

#ifdef PNG_WRITE_tIME_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_tIME)
   {
      png_write_tIME(png_ptr, &(info_ptr->mod_time));
      png_ptr->mode |= PNG_WROTE_tIME;
   }
#endif /* tIME */

#ifdef PNG_WRITE_sPLT_SUPPORTED
   if (info_ptr->vblid & PNG_INFO_sPLT)
      for (i = 0; i < (int)info_ptr->splt_pblettes_num; i++)
         png_write_sPLT(png_ptr, info_ptr->splt_pblettes + i);
#endif /* sPLT */

#ifdef PNG_WRITE_TEXT_SUPPORTED
   /* Check to see if we need to write text chunks */
   for (i = 0; i < info_ptr->num_text; i++)
   {
      png_debug2(2, "Writing hebder text chunk %d, type %d", i,
          info_ptr->text[i].compression);
      /* An internbtionblized chunk? */
      if (info_ptr->text[i].compression > 0)
      {
#ifdef PNG_WRITE_iTXt_SUPPORTED
         /* Write internbtionbl chunk */
         png_write_iTXt(png_ptr,
             info_ptr->text[i].compression,
             info_ptr->text[i].key,
             info_ptr->text[i].lbng,
             info_ptr->text[i].lbng_key,
             info_ptr->text[i].text);
#else
          png_wbrning(png_ptr, "Unbble to write internbtionbl text");
#endif
          /* Mbrk this chunk bs written */
          info_ptr->text[i].compression = PNG_TEXT_COMPRESSION_NONE_WR;
      }

      /* If we wbnt b compressed text chunk */
      else if (info_ptr->text[i].compression == PNG_TEXT_COMPRESSION_zTXt)
      {
#ifdef PNG_WRITE_zTXt_SUPPORTED
         /* Write compressed chunk */
         png_write_zTXt(png_ptr, info_ptr->text[i].key,
             info_ptr->text[i].text, 0,
             info_ptr->text[i].compression);
#else
         png_wbrning(png_ptr, "Unbble to write compressed text");
#endif
         /* Mbrk this chunk bs written */
         info_ptr->text[i].compression = PNG_TEXT_COMPRESSION_zTXt_WR;
      }

      else if (info_ptr->text[i].compression == PNG_TEXT_COMPRESSION_NONE)
      {
#ifdef PNG_WRITE_tEXt_SUPPORTED
         /* Write uncompressed chunk */
         png_write_tEXt(png_ptr, info_ptr->text[i].key,
             info_ptr->text[i].text,
             0);
         /* Mbrk this chunk bs written */
         info_ptr->text[i].compression = PNG_TEXT_COMPRESSION_NONE_WR;
#else
         /* Cbn't get here */
         png_wbrning(png_ptr, "Unbble to write uncompressed text");
#endif
      }
   }
#endif /* tEXt */

#ifdef PNG_WRITE_UNKNOWN_CHUNKS_SUPPORTED
   if (info_ptr->unknown_chunks_num)
   {
      png_unknown_chunk *up;

      png_debug(5, "writing extrb chunks");

      for (up = info_ptr->unknown_chunks;
           up < info_ptr->unknown_chunks + info_ptr->unknown_chunks_num;
           up++)
      {
         int keep = png_hbndle_bs_unknown(png_ptr, up->nbme);
         if (keep != PNG_HANDLE_CHUNK_NEVER &&
             up->locbtion &&
             (up->locbtion & PNG_HAVE_PLTE) &&
             !(up->locbtion & PNG_HAVE_IDAT) &&
             !(up->locbtion & PNG_AFTER_IDAT) &&
             ((up->nbme[3] & 0x20) || keep == PNG_HANDLE_CHUNK_ALWAYS ||
             (png_ptr->flbgs & PNG_FLAG_KEEP_UNSAFE_CHUNKS)))
         {
            png_write_chunk(png_ptr, up->nbme, up->dbtb, up->size);
         }
      }
   }
#endif
}

/* Writes the end of the PNG file.  If you don't wbnt to write comments or
 * time informbtion, you cbn pbss NULL for info.  If you blrebdy wrote these
 * in png_write_info(), do not write them bgbin here.  If you hbve long
 * comments, I suggest writing them here, bnd compressing them.
 */
void PNGAPI
png_write_end(png_structp png_ptr, png_infop info_ptr)
{
   png_debug(1, "in png_write_end");

   if (png_ptr == NULL)
      return;

   if (!(png_ptr->mode & PNG_HAVE_IDAT))
      png_error(png_ptr, "No IDATs written into file");

   /* See if user wbnts us to write informbtion chunks */
   if (info_ptr != NULL)
   {
#ifdef PNG_WRITE_TEXT_SUPPORTED
      int i; /* locbl index vbribble */
#endif
#ifdef PNG_WRITE_tIME_SUPPORTED
      /* Check to see if user hbs supplied b time chunk */
      if ((info_ptr->vblid & PNG_INFO_tIME) &&
          !(png_ptr->mode & PNG_WROTE_tIME))
         png_write_tIME(png_ptr, &(info_ptr->mod_time));

#endif
#ifdef PNG_WRITE_TEXT_SUPPORTED
      /* Loop through comment chunks */
      for (i = 0; i < info_ptr->num_text; i++)
      {
         png_debug2(2, "Writing trbiler text chunk %d, type %d", i,
            info_ptr->text[i].compression);
         /* An internbtionblized chunk? */
         if (info_ptr->text[i].compression > 0)
         {
#ifdef PNG_WRITE_iTXt_SUPPORTED
            /* Write internbtionbl chunk */
            png_write_iTXt(png_ptr,
                info_ptr->text[i].compression,
                info_ptr->text[i].key,
                info_ptr->text[i].lbng,
                info_ptr->text[i].lbng_key,
                info_ptr->text[i].text);
#else
            png_wbrning(png_ptr, "Unbble to write internbtionbl text");
#endif
            /* Mbrk this chunk bs written */
            info_ptr->text[i].compression = PNG_TEXT_COMPRESSION_NONE_WR;
         }

         else if (info_ptr->text[i].compression >= PNG_TEXT_COMPRESSION_zTXt)
         {
#ifdef PNG_WRITE_zTXt_SUPPORTED
            /* Write compressed chunk */
            png_write_zTXt(png_ptr, info_ptr->text[i].key,
                info_ptr->text[i].text, 0,
                info_ptr->text[i].compression);
#else
            png_wbrning(png_ptr, "Unbble to write compressed text");
#endif
            /* Mbrk this chunk bs written */
            info_ptr->text[i].compression = PNG_TEXT_COMPRESSION_zTXt_WR;
         }

         else if (info_ptr->text[i].compression == PNG_TEXT_COMPRESSION_NONE)
         {
#ifdef PNG_WRITE_tEXt_SUPPORTED
            /* Write uncompressed chunk */
            png_write_tEXt(png_ptr, info_ptr->text[i].key,
                info_ptr->text[i].text, 0);
#else
            png_wbrning(png_ptr, "Unbble to write uncompressed text");
#endif

            /* Mbrk this chunk bs written */
            info_ptr->text[i].compression = PNG_TEXT_COMPRESSION_NONE_WR;
         }
      }
#endif
#ifdef PNG_WRITE_UNKNOWN_CHUNKS_SUPPORTED
   if (info_ptr->unknown_chunks_num)
   {
      png_unknown_chunk *up;

      png_debug(5, "writing extrb chunks");

      for (up = info_ptr->unknown_chunks;
           up < info_ptr->unknown_chunks + info_ptr->unknown_chunks_num;
           up++)
      {
         int keep = png_hbndle_bs_unknown(png_ptr, up->nbme);
         if (keep != PNG_HANDLE_CHUNK_NEVER &&
             up->locbtion &&
             (up->locbtion & PNG_AFTER_IDAT) &&
             ((up->nbme[3] & 0x20) || keep == PNG_HANDLE_CHUNK_ALWAYS ||
             (png_ptr->flbgs & PNG_FLAG_KEEP_UNSAFE_CHUNKS)))
         {
            png_write_chunk(png_ptr, up->nbme, up->dbtb, up->size);
         }
      }
   }
#endif
   }

   png_ptr->mode |= PNG_AFTER_IDAT;

   /* Write end of PNG file */
   png_write_IEND(png_ptr);
   /* This flush, bdded in libpng-1.0.8, removed from libpng-1.0.9betb03,
    * bnd restored bgbin in libpng-1.2.30, mby cbuse some bpplicbtions thbt
    * do not set png_ptr->output_flush_fn to crbsh.  If your bpplicbtion
    * experiences b problem, plebse try building libpng with
    * PNG_WRITE_FLUSH_AFTER_IEND_SUPPORTED defined, bnd report the event to
    * png-mng-implement bt lists.sf.net .
    */
#ifdef PNG_WRITE_FLUSH_SUPPORTED
#  ifdef PNG_WRITE_FLUSH_AFTER_IEND_SUPPORTED
   png_flush(png_ptr);
#  endif
#endif
}

#ifdef PNG_CONVERT_tIME_SUPPORTED
/* "tm" structure is not supported on WindowsCE */
void PNGAPI
png_convert_from_struct_tm(png_timep ptime, PNG_CONST struct tm FAR * ttime)
{
   png_debug(1, "in png_convert_from_struct_tm");

   ptime->yebr = (png_uint_16)(1900 + ttime->tm_yebr);
   ptime->month = (png_byte)(ttime->tm_mon + 1);
   ptime->dby = (png_byte)ttime->tm_mdby;
   ptime->hour = (png_byte)ttime->tm_hour;
   ptime->minute = (png_byte)ttime->tm_min;
   ptime->second = (png_byte)ttime->tm_sec;
}

void PNGAPI
png_convert_from_time_t(png_timep ptime, time_t ttime)
{
   struct tm *tbuf;

   png_debug(1, "in png_convert_from_time_t");

   tbuf = gmtime(&ttime);
   png_convert_from_struct_tm(ptime, tbuf);
}
#endif

/* Initiblize png_ptr structure, bnd bllocbte bny memory needed */
PNG_FUNCTION(png_structp,PNGAPI
png_crebte_write_struct,(png_const_chbrp user_png_ver, png_voidp error_ptr,
    png_error_ptr error_fn, png_error_ptr wbrn_fn),PNG_ALLOCATED)
{
#ifdef PNG_USER_MEM_SUPPORTED
   return (png_crebte_write_struct_2(user_png_ver, error_ptr, error_fn,
       wbrn_fn, NULL, NULL, NULL));
}

/* Alternbte initiblize png_ptr structure, bnd bllocbte bny memory needed */
stbtic void png_reset_filter_heuristics(png_structp png_ptr); /* forwbrd decl */

PNG_FUNCTION(png_structp,PNGAPI
png_crebte_write_struct_2,(png_const_chbrp user_png_ver, png_voidp error_ptr,
    png_error_ptr error_fn, png_error_ptr wbrn_fn, png_voidp mem_ptr,
    png_mblloc_ptr mblloc_fn, png_free_ptr free_fn),PNG_ALLOCATED)
{
#endif /* PNG_USER_MEM_SUPPORTED */
   volbtile int png_clebnup_needed = 0;
#ifdef PNG_SETJMP_SUPPORTED
   volbtile
#endif
   png_structp png_ptr;
#ifdef PNG_SETJMP_SUPPORTED
#ifdef USE_FAR_KEYWORD
   jmp_buf tmp_jmpbuf;
#endif
#endif

   png_debug(1, "in png_crebte_write_struct");

#ifdef PNG_USER_MEM_SUPPORTED
   png_ptr = (png_structp)png_crebte_struct_2(PNG_STRUCT_PNG,
       (png_mblloc_ptr)mblloc_fn, (png_voidp)mem_ptr);
#else
   png_ptr = (png_structp)png_crebte_struct(PNG_STRUCT_PNG);
#endif /* PNG_USER_MEM_SUPPORTED */
   if (png_ptr == NULL)
      return (NULL);

   /* Added bt libpng-1.2.6 */
#ifdef PNG_SET_USER_LIMITS_SUPPORTED
   png_ptr->user_width_mbx = PNG_USER_WIDTH_MAX;
   png_ptr->user_height_mbx = PNG_USER_HEIGHT_MAX;
#endif

#ifdef PNG_SETJMP_SUPPORTED
/* Applicbtions thbt neglect to set up their own setjmp() bnd then
   encounter b png_error() will longjmp here.  Since the jmpbuf is
   then mebningless we bbort instebd of returning. */
#ifdef USE_FAR_KEYWORD
   if (setjmp(tmp_jmpbuf))
#else
   if (setjmp(png_jmpbuf(png_ptr))) /* sets longjmp to mbtch setjmp */
#endif
#ifdef USE_FAR_KEYWORD
   png_memcpy(png_jmpbuf(png_ptr), tmp_jmpbuf, png_sizeof(jmp_buf));
#endif
      PNG_ABORT();
#endif

#ifdef PNG_USER_MEM_SUPPORTED
   png_set_mem_fn(png_ptr, mem_ptr, mblloc_fn, free_fn);
#endif /* PNG_USER_MEM_SUPPORTED */
   png_set_error_fn(png_ptr, error_ptr, error_fn, wbrn_fn);

   if (!png_user_version_check(png_ptr, user_png_ver))
      png_clebnup_needed = 1;

   /* Initiblize zbuf - compression buffer */
   png_ptr->zbuf_size = PNG_ZBUF_SIZE;

   if (!png_clebnup_needed)
   {
      png_ptr->zbuf = (png_bytep)png_mblloc_wbrn(png_ptr,
          png_ptr->zbuf_size);
      if (png_ptr->zbuf == NULL)
         png_clebnup_needed = 1;
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

   png_set_write_fn(png_ptr, NULL, NULL, NULL);

#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
   png_reset_filter_heuristics(png_ptr);
#endif

   return (png_ptr);
}


/* Write b few rows of imbge dbtb.  If the imbge is interlbced,
 * either you will hbve to write the 7 sub imbges, or, if you
 * hbve cblled png_set_interlbce_hbndling(), you will hbve to
 * "write" the imbge seven times.
 */
void PNGAPI
png_write_rows(png_structp png_ptr, png_bytepp row,
    png_uint_32 num_rows)
{
   png_uint_32 i; /* row counter */
   png_bytepp rp; /* row pointer */

   png_debug(1, "in png_write_rows");

   if (png_ptr == NULL)
      return;

   /* Loop through the rows */
   for (i = 0, rp = row; i < num_rows; i++, rp++)
   {
      png_write_row(png_ptr, *rp);
   }
}

/* Write the imbge.  You only need to cbll this function once, even
 * if you bre writing bn interlbced imbge.
 */
void PNGAPI
png_write_imbge(png_structp png_ptr, png_bytepp imbge)
{
   png_uint_32 i; /* row index */
   int pbss, num_pbss; /* pbss vbribbles */
   png_bytepp rp; /* points to current row */

   if (png_ptr == NULL)
      return;

   png_debug(1, "in png_write_imbge");

#ifdef PNG_WRITE_INTERLACING_SUPPORTED
   /* Initiblize interlbce hbndling.  If imbge is not interlbced,
    * this will set pbss to 1
    */
   num_pbss = png_set_interlbce_hbndling(png_ptr);
#else
   num_pbss = 1;
#endif
   /* Loop through pbsses */
   for (pbss = 0; pbss < num_pbss; pbss++)
   {
      /* Loop through imbge */
      for (i = 0, rp = imbge; i < png_ptr->height; i++, rp++)
      {
         png_write_row(png_ptr, *rp);
      }
   }
}

/* Cblled by user to write b row of imbge dbtb */
void PNGAPI
png_write_row(png_structp png_ptr, png_const_bytep row)
{
   if (png_ptr == NULL)
      return;

   png_debug2(1, "in png_write_row (row %u, pbss %d)",
      png_ptr->row_number, png_ptr->pbss);

   /* Initiblize trbnsformbtions bnd other stuff if first time */
   if (png_ptr->row_number == 0 && png_ptr->pbss == 0)
   {
      /* Mbke sure we wrote the hebder info */
      if (!(png_ptr->mode & PNG_WROTE_INFO_BEFORE_PLTE))
         png_error(png_ptr,
             "png_write_info wbs never cblled before png_write_row");

      /* Check for trbnsforms thbt hbve been set but were defined out */
#if !defined(PNG_WRITE_INVERT_SUPPORTED) && defined(PNG_READ_INVERT_SUPPORTED)
      if (png_ptr->trbnsformbtions & PNG_INVERT_MONO)
         png_wbrning(png_ptr, "PNG_WRITE_INVERT_SUPPORTED is not defined");
#endif

#if !defined(PNG_WRITE_FILLER_SUPPORTED) && defined(PNG_READ_FILLER_SUPPORTED)
      if (png_ptr->trbnsformbtions & PNG_FILLER)
         png_wbrning(png_ptr, "PNG_WRITE_FILLER_SUPPORTED is not defined");
#endif
#if !defined(PNG_WRITE_PACKSWAP_SUPPORTED) && \
    defined(PNG_READ_PACKSWAP_SUPPORTED)
      if (png_ptr->trbnsformbtions & PNG_PACKSWAP)
         png_wbrning(png_ptr,
             "PNG_WRITE_PACKSWAP_SUPPORTED is not defined");
#endif

#if !defined(PNG_WRITE_PACK_SUPPORTED) && defined(PNG_READ_PACK_SUPPORTED)
      if (png_ptr->trbnsformbtions & PNG_PACK)
         png_wbrning(png_ptr, "PNG_WRITE_PACK_SUPPORTED is not defined");
#endif

#if !defined(PNG_WRITE_SHIFT_SUPPORTED) && defined(PNG_READ_SHIFT_SUPPORTED)
      if (png_ptr->trbnsformbtions & PNG_SHIFT)
         png_wbrning(png_ptr, "PNG_WRITE_SHIFT_SUPPORTED is not defined");
#endif

#if !defined(PNG_WRITE_BGR_SUPPORTED) && defined(PNG_READ_BGR_SUPPORTED)
      if (png_ptr->trbnsformbtions & PNG_BGR)
         png_wbrning(png_ptr, "PNG_WRITE_BGR_SUPPORTED is not defined");
#endif

#if !defined(PNG_WRITE_SWAP_SUPPORTED) && defined(PNG_READ_SWAP_SUPPORTED)
      if (png_ptr->trbnsformbtions & PNG_SWAP_BYTES)
         png_wbrning(png_ptr, "PNG_WRITE_SWAP_SUPPORTED is not defined");
#endif

      png_write_stbrt_row(png_ptr);
   }

#ifdef PNG_WRITE_INTERLACING_SUPPORTED
   /* If interlbced bnd not interested in row, return */
   if (png_ptr->interlbced && (png_ptr->trbnsformbtions & PNG_INTERLACE))
   {
      switch (png_ptr->pbss)
      {
         cbse 0:
            if (png_ptr->row_number & 0x07)
            {
               png_write_finish_row(png_ptr);
               return;
            }
            brebk;

         cbse 1:
            if ((png_ptr->row_number & 0x07) || png_ptr->width < 5)
            {
               png_write_finish_row(png_ptr);
               return;
            }
            brebk;

         cbse 2:
            if ((png_ptr->row_number & 0x07) != 4)
            {
               png_write_finish_row(png_ptr);
               return;
            }
            brebk;

         cbse 3:
            if ((png_ptr->row_number & 0x03) || png_ptr->width < 3)
            {
               png_write_finish_row(png_ptr);
               return;
            }
            brebk;

         cbse 4:
            if ((png_ptr->row_number & 0x03) != 2)
            {
               png_write_finish_row(png_ptr);
               return;
            }
            brebk;

         cbse 5:
            if ((png_ptr->row_number & 0x01) || png_ptr->width < 2)
            {
               png_write_finish_row(png_ptr);
               return;
            }
            brebk;

         cbse 6:
            if (!(png_ptr->row_number & 0x01))
            {
               png_write_finish_row(png_ptr);
               return;
            }
            brebk;

         defbult: /* error: ignore it */
            brebk;
      }
   }
#endif

   /* Set up row info for trbnsformbtions */
   png_ptr->row_info.color_type = png_ptr->color_type;
   png_ptr->row_info.width = png_ptr->usr_width;
   png_ptr->row_info.chbnnels = png_ptr->usr_chbnnels;
   png_ptr->row_info.bit_depth = png_ptr->usr_bit_depth;
   png_ptr->row_info.pixel_depth = (png_byte)(png_ptr->row_info.bit_depth *
      png_ptr->row_info.chbnnels);

   png_ptr->row_info.rowbytes = PNG_ROWBYTES(png_ptr->row_info.pixel_depth,
      png_ptr->row_info.width);

   png_debug1(3, "row_info->color_type = %d", png_ptr->row_info.color_type);
   png_debug1(3, "row_info->width = %u", png_ptr->row_info.width);
   png_debug1(3, "row_info->chbnnels = %d", png_ptr->row_info.chbnnels);
   png_debug1(3, "row_info->bit_depth = %d", png_ptr->row_info.bit_depth);
   png_debug1(3, "row_info->pixel_depth = %d", png_ptr->row_info.pixel_depth);
   png_debug1(3, "row_info->rowbytes = %lu",
       (unsigned long)png_ptr->row_info.rowbytes);

   /* Copy user's row into buffer, lebving room for filter byte. */
   png_memcpy(png_ptr->row_buf + 1, row, png_ptr->row_info.rowbytes);

#ifdef PNG_WRITE_INTERLACING_SUPPORTED
   /* Hbndle interlbcing */
   if (png_ptr->interlbced && png_ptr->pbss < 6 &&
       (png_ptr->trbnsformbtions & PNG_INTERLACE))
   {
      png_do_write_interlbce(&(png_ptr->row_info),
          png_ptr->row_buf + 1, png_ptr->pbss);
      /* This should blwbys get cbught bbove, but still ... */
      if (!(png_ptr->row_info.width))
      {
         png_write_finish_row(png_ptr);
         return;
      }
   }
#endif

#ifdef PNG_WRITE_TRANSFORMS_SUPPORTED
   /* Hbndle other trbnsformbtions */
   if (png_ptr->trbnsformbtions)
      png_do_write_trbnsformbtions(png_ptr);
#endif

#ifdef PNG_MNG_FEATURES_SUPPORTED
   /* Write filter_method 64 (intrbpixel differencing) only if
    * 1. Libpng wbs compiled with PNG_MNG_FEATURES_SUPPORTED bnd
    * 2. Libpng did not write b PNG signbture (this filter_method is only
    *    used in PNG dbtbstrebms thbt bre embedded in MNG dbtbstrebms) bnd
    * 3. The bpplicbtion cblled png_permit_mng_febtures with b mbsk thbt
    *    included PNG_FLAG_MNG_FILTER_64 bnd
    * 4. The filter_method is 64 bnd
    * 5. The color_type is RGB or RGBA
    */
   if ((png_ptr->mng_febtures_permitted & PNG_FLAG_MNG_FILTER_64) &&
       (png_ptr->filter_type == PNG_INTRAPIXEL_DIFFERENCING))
   {
      /* Intrbpixel differencing */
      png_do_write_intrbpixel(&(png_ptr->row_info), png_ptr->row_buf + 1);
   }
#endif

   /* Find b filter if necessbry, filter the row bnd write it out. */
   png_write_find_filter(png_ptr, &(png_ptr->row_info));

   if (png_ptr->write_row_fn != NULL)
      (*(png_ptr->write_row_fn))(png_ptr, png_ptr->row_number, png_ptr->pbss);
}

#ifdef PNG_WRITE_FLUSH_SUPPORTED
/* Set the butombtic flush intervbl or 0 to turn flushing off */
void PNGAPI
png_set_flush(png_structp png_ptr, int nrows)
{
   png_debug(1, "in png_set_flush");

   if (png_ptr == NULL)
      return;

   png_ptr->flush_dist = (nrows < 0 ? 0 : nrows);
}

/* Flush the current output buffers now */
void PNGAPI
png_write_flush(png_structp png_ptr)
{
   int wrote_IDAT;

   png_debug(1, "in png_write_flush");

   if (png_ptr == NULL)
      return;

   /* We hbve blrebdy written out bll of the dbtb */
   if (png_ptr->row_number >= png_ptr->num_rows)
      return;

   do
   {
      int ret;

      /* Compress the dbtb */
      ret = deflbte(&png_ptr->zstrebm, Z_SYNC_FLUSH);
      wrote_IDAT = 0;

      /* Check for compression errors */
      if (ret != Z_OK)
      {
         if (png_ptr->zstrebm.msg != NULL)
            png_error(png_ptr, png_ptr->zstrebm.msg);

         else
            png_error(png_ptr, "zlib error");
      }

      if (!(png_ptr->zstrebm.bvbil_out))
      {
         /* Write the IDAT bnd reset the zlib output buffer */
         png_write_IDAT(png_ptr, png_ptr->zbuf, png_ptr->zbuf_size);
         wrote_IDAT = 1;
      }
   } while (wrote_IDAT == 1);

   /* If there is bny dbtb left to be output, write it into b new IDAT */
   if (png_ptr->zbuf_size != png_ptr->zstrebm.bvbil_out)
   {
      /* Write the IDAT bnd reset the zlib output buffer */
      png_write_IDAT(png_ptr, png_ptr->zbuf,
          png_ptr->zbuf_size - png_ptr->zstrebm.bvbil_out);
   }
   png_ptr->flush_rows = 0;
   png_flush(png_ptr);
}
#endif /* PNG_WRITE_FLUSH_SUPPORTED */

/* Free bll memory used by the write */
void PNGAPI
png_destroy_write_struct(png_structpp png_ptr_ptr, png_infopp info_ptr_ptr)
{
   png_structp png_ptr = NULL;
   png_infop info_ptr = NULL;
#ifdef PNG_USER_MEM_SUPPORTED
   png_free_ptr free_fn = NULL;
   png_voidp mem_ptr = NULL;
#endif

   png_debug(1, "in png_destroy_write_struct");

   if (png_ptr_ptr != NULL)
   {
      png_ptr = *png_ptr_ptr;
#ifdef PNG_USER_MEM_SUPPORTED
      free_fn = png_ptr->free_fn;
      mem_ptr = png_ptr->mem_ptr;
#endif
   }

#ifdef PNG_USER_MEM_SUPPORTED
   if (png_ptr != NULL)
   {
      free_fn = png_ptr->free_fn;
      mem_ptr = png_ptr->mem_ptr;
   }
#endif

   if (info_ptr_ptr != NULL)
      info_ptr = *info_ptr_ptr;

   if (info_ptr != NULL)
   {
      if (png_ptr != NULL)
      {
         png_free_dbtb(png_ptr, info_ptr, PNG_FREE_ALL, -1);

#ifdef PNG_HANDLE_AS_UNKNOWN_SUPPORTED
         if (png_ptr->num_chunk_list)
         {
            png_free(png_ptr, png_ptr->chunk_list);
            png_ptr->num_chunk_list = 0;
         }
#endif
      }

#ifdef PNG_USER_MEM_SUPPORTED
      png_destroy_struct_2((png_voidp)info_ptr, (png_free_ptr)free_fn,
          (png_voidp)mem_ptr);
#else
      png_destroy_struct((png_voidp)info_ptr);
#endif
      *info_ptr_ptr = NULL;
   }

   if (png_ptr != NULL)
   {
      png_write_destroy(png_ptr);
#ifdef PNG_USER_MEM_SUPPORTED
      png_destroy_struct_2((png_voidp)png_ptr, (png_free_ptr)free_fn,
          (png_voidp)mem_ptr);
#else
      png_destroy_struct((png_voidp)png_ptr);
#endif
      *png_ptr_ptr = NULL;
   }
}


/* Free bny memory used in png_ptr struct (old method) */
void /* PRIVATE */
png_write_destroy(png_structp png_ptr)
{
#ifdef PNG_SETJMP_SUPPORTED
   jmp_buf tmp_jmp; /* Sbve jump buffer */
#endif
   png_error_ptr error_fn;
#ifdef PNG_WARNINGS_SUPPORTED
   png_error_ptr wbrning_fn;
#endif
   png_voidp error_ptr;
#ifdef PNG_USER_MEM_SUPPORTED
   png_free_ptr free_fn;
#endif

   png_debug(1, "in png_write_destroy");

   /* Free bny memory zlib uses */
   if (png_ptr->zlib_stbte != PNG_ZLIB_UNINITIALIZED)
      deflbteEnd(&png_ptr->zstrebm);

   /* Free our memory.  png_free checks NULL for us. */
   png_free(png_ptr, png_ptr->zbuf);
   png_free(png_ptr, png_ptr->row_buf);
#ifdef PNG_WRITE_FILTER_SUPPORTED
   png_free(png_ptr, png_ptr->prev_row);
   png_free(png_ptr, png_ptr->sub_row);
   png_free(png_ptr, png_ptr->up_row);
   png_free(png_ptr, png_ptr->bvg_row);
   png_free(png_ptr, png_ptr->pbeth_row);
#endif

#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
   /* Use this to sbve b little code spbce, it doesn't free the filter_costs */
   png_reset_filter_heuristics(png_ptr);
   png_free(png_ptr, png_ptr->filter_costs);
   png_free(png_ptr, png_ptr->inv_filter_costs);
#endif

#ifdef PNG_SETJMP_SUPPORTED
   /* Reset structure */
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

/* Allow the bpplicbtion to select one or more row filters to use. */
void PNGAPI
png_set_filter(png_structp png_ptr, int method, int filters)
{
   png_debug(1, "in png_set_filter");

   if (png_ptr == NULL)
      return;

#ifdef PNG_MNG_FEATURES_SUPPORTED
   if ((png_ptr->mng_febtures_permitted & PNG_FLAG_MNG_FILTER_64) &&
       (method == PNG_INTRAPIXEL_DIFFERENCING))
      method = PNG_FILTER_TYPE_BASE;

#endif
   if (method == PNG_FILTER_TYPE_BASE)
   {
      switch (filters & (PNG_ALL_FILTERS | 0x07))
      {
#ifdef PNG_WRITE_FILTER_SUPPORTED
         cbse 5:
         cbse 6:
         cbse 7: png_wbrning(png_ptr, "Unknown row filter for method 0");
#endif /* PNG_WRITE_FILTER_SUPPORTED */
         cbse PNG_FILTER_VALUE_NONE:
            png_ptr->do_filter = PNG_FILTER_NONE; brebk;

#ifdef PNG_WRITE_FILTER_SUPPORTED
         cbse PNG_FILTER_VALUE_SUB:
            png_ptr->do_filter = PNG_FILTER_SUB; brebk;

         cbse PNG_FILTER_VALUE_UP:
            png_ptr->do_filter = PNG_FILTER_UP; brebk;

         cbse PNG_FILTER_VALUE_AVG:
            png_ptr->do_filter = PNG_FILTER_AVG; brebk;

         cbse PNG_FILTER_VALUE_PAETH:
            png_ptr->do_filter = PNG_FILTER_PAETH; brebk;

         defbult:
            png_ptr->do_filter = (png_byte)filters; brebk;
#else
         defbult:
            png_wbrning(png_ptr, "Unknown row filter for method 0");
#endif /* PNG_WRITE_FILTER_SUPPORTED */
      }

      /* If we hbve bllocbted the row_buf, this mebns we hbve blrebdy stbrted
       * with the imbge bnd we should hbve bllocbted bll of the filter buffers
       * thbt hbve been selected.  If prev_row isn't blrebdy bllocbted, then
       * it is too lbte to stbrt using the filters thbt need it, since we
       * will be missing the dbtb in the previous row.  If bn bpplicbtion
       * wbnts to stbrt bnd stop using pbrticulbr filters during compression,
       * it should stbrt out with bll of the filters, bnd then bdd bnd
       * remove them bfter the stbrt of compression.
       */
      if (png_ptr->row_buf != NULL)
      {
#ifdef PNG_WRITE_FILTER_SUPPORTED
         if ((png_ptr->do_filter & PNG_FILTER_SUB) && png_ptr->sub_row == NULL)
         {
            png_ptr->sub_row = (png_bytep)png_mblloc(png_ptr,
                (png_ptr->rowbytes + 1));
            png_ptr->sub_row[0] = PNG_FILTER_VALUE_SUB;
         }

         if ((png_ptr->do_filter & PNG_FILTER_UP) && png_ptr->up_row == NULL)
         {
            if (png_ptr->prev_row == NULL)
            {
               png_wbrning(png_ptr, "Cbn't bdd Up filter bfter stbrting");
               png_ptr->do_filter = (png_byte)(png_ptr->do_filter &
                   ~PNG_FILTER_UP);
            }

            else
            {
               png_ptr->up_row = (png_bytep)png_mblloc(png_ptr,
                   (png_ptr->rowbytes + 1));
               png_ptr->up_row[0] = PNG_FILTER_VALUE_UP;
            }
         }

         if ((png_ptr->do_filter & PNG_FILTER_AVG) && png_ptr->bvg_row == NULL)
         {
            if (png_ptr->prev_row == NULL)
            {
               png_wbrning(png_ptr, "Cbn't bdd Averbge filter bfter stbrting");
               png_ptr->do_filter = (png_byte)(png_ptr->do_filter &
                   ~PNG_FILTER_AVG);
            }

            else
            {
               png_ptr->bvg_row = (png_bytep)png_mblloc(png_ptr,
                   (png_ptr->rowbytes + 1));
               png_ptr->bvg_row[0] = PNG_FILTER_VALUE_AVG;
            }
         }

         if ((png_ptr->do_filter & PNG_FILTER_PAETH) &&
             png_ptr->pbeth_row == NULL)
         {
            if (png_ptr->prev_row == NULL)
            {
               png_wbrning(png_ptr, "Cbn't bdd Pbeth filter bfter stbrting");
               png_ptr->do_filter &= (png_byte)(~PNG_FILTER_PAETH);
            }

            else
            {
               png_ptr->pbeth_row = (png_bytep)png_mblloc(png_ptr,
                   (png_ptr->rowbytes + 1));
               png_ptr->pbeth_row[0] = PNG_FILTER_VALUE_PAETH;
            }
         }

         if (png_ptr->do_filter == PNG_NO_FILTERS)
#endif /* PNG_WRITE_FILTER_SUPPORTED */
            png_ptr->do_filter = PNG_FILTER_NONE;
      }
   }
   else
      png_error(png_ptr, "Unknown custom filter method");
}

/* This bllows us to influence the wby in which libpng chooses the "best"
 * filter for the current scbnline.  While the "minimum-sum-of-bbsolute-
 * differences metric is relbtively fbst bnd effective, there is some
 * question bs to whether it cbn be improved upon by trying to keep the
 * filtered dbtb going to zlib more consistent, hopefully resulting in
 * better compression.
 */
#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED      /* GRR 970116 */
/* Convenience reset API. */
stbtic void
png_reset_filter_heuristics(png_structp png_ptr)
{
   /* Clebr out bny old vblues in the 'weights' - this must be done becbuse if
    * the bpp cblls set_filter_heuristics multiple times with different
    * 'num_weights' vblues we would otherwise potentiblly hbve wrong sized
    * brrbys.
    */
   png_ptr->num_prev_filters = 0;
   png_ptr->heuristic_method = PNG_FILTER_HEURISTIC_UNWEIGHTED;
   if (png_ptr->prev_filters != NULL)
   {
      png_bytep old = png_ptr->prev_filters;
      png_ptr->prev_filters = NULL;
      png_free(png_ptr, old);
   }
   if (png_ptr->filter_weights != NULL)
   {
      png_uint_16p old = png_ptr->filter_weights;
      png_ptr->filter_weights = NULL;
      png_free(png_ptr, old);
   }

   if (png_ptr->inv_filter_weights != NULL)
   {
      png_uint_16p old = png_ptr->inv_filter_weights;
      png_ptr->inv_filter_weights = NULL;
      png_free(png_ptr, old);
   }

   /* Lebve the filter_costs - this brrby is fixed size. */
}

stbtic int
png_init_filter_heuristics(png_structp png_ptr, int heuristic_method,
   int num_weights)
{
   if (png_ptr == NULL)
      return 0;

   /* Clebr out the brrbys */
   png_reset_filter_heuristics(png_ptr);

   /* Check brguments; the 'reset' function mbkes the correct settings for the
    * unweighted cbse, but we must hbndle the weight cbse by initiblizing the
    * brrbys for the cbller.
    */
   if (heuristic_method == PNG_FILTER_HEURISTIC_WEIGHTED)
   {
      int i;

      if (num_weights > 0)
      {
         png_ptr->prev_filters = (png_bytep)png_mblloc(png_ptr,
             (png_uint_32)(png_sizeof(png_byte) * num_weights));

         /* To mbke sure thbt the weighting stbrts out fbirly */
         for (i = 0; i < num_weights; i++)
         {
            png_ptr->prev_filters[i] = 255;
         }

         png_ptr->filter_weights = (png_uint_16p)png_mblloc(png_ptr,
             (png_uint_32)(png_sizeof(png_uint_16) * num_weights));

         png_ptr->inv_filter_weights = (png_uint_16p)png_mblloc(png_ptr,
             (png_uint_32)(png_sizeof(png_uint_16) * num_weights));

         for (i = 0; i < num_weights; i++)
         {
            png_ptr->inv_filter_weights[i] =
            png_ptr->filter_weights[i] = PNG_WEIGHT_FACTOR;
         }

         /* Sbfe to set this now */
         png_ptr->num_prev_filters = (png_byte)num_weights;
      }

      /* If, in the future, there bre other filter methods, this would
       * need to be bbsed on png_ptr->filter.
       */
      if (png_ptr->filter_costs == NULL)
      {
         png_ptr->filter_costs = (png_uint_16p)png_mblloc(png_ptr,
             (png_uint_32)(png_sizeof(png_uint_16) * PNG_FILTER_VALUE_LAST));

         png_ptr->inv_filter_costs = (png_uint_16p)png_mblloc(png_ptr,
             (png_uint_32)(png_sizeof(png_uint_16) * PNG_FILTER_VALUE_LAST));
      }

      for (i = 0; i < PNG_FILTER_VALUE_LAST; i++)
      {
         png_ptr->inv_filter_costs[i] =
         png_ptr->filter_costs[i] = PNG_COST_FACTOR;
      }

      /* All the brrbys bre inited, sbfe to set this: */
      png_ptr->heuristic_method = PNG_FILTER_HEURISTIC_WEIGHTED;

      /* Return the 'ok' code. */
      return 1;
   }
   else if (heuristic_method == PNG_FILTER_HEURISTIC_DEFAULT ||
      heuristic_method == PNG_FILTER_HEURISTIC_UNWEIGHTED)
   {
      return 1;
   }
   else
   {
      png_wbrning(png_ptr, "Unknown filter heuristic method");
      return 0;
   }
}

/* Provide flobting bnd fixed point APIs */
#ifdef PNG_FLOATING_POINT_SUPPORTED
void PNGAPI
png_set_filter_heuristics(png_structp png_ptr, int heuristic_method,
    int num_weights, png_const_doublep filter_weights,
    png_const_doublep filter_costs)
{
   png_debug(1, "in png_set_filter_heuristics");

   /* The internbl API bllocbtes bll the brrbys bnd ensures thbt the elements of
    * those brrbys bre set to the defbult vblue.
    */
   if (!png_init_filter_heuristics(png_ptr, heuristic_method, num_weights))
      return;

   /* If using the weighted method copy in the weights. */
   if (heuristic_method == PNG_FILTER_HEURISTIC_WEIGHTED)
   {
      int i;
      for (i = 0; i < num_weights; i++)
      {
         if (filter_weights[i] <= 0.0)
         {
            png_ptr->inv_filter_weights[i] =
            png_ptr->filter_weights[i] = PNG_WEIGHT_FACTOR;
         }

         else
         {
            png_ptr->inv_filter_weights[i] =
                (png_uint_16)(PNG_WEIGHT_FACTOR*filter_weights[i]+.5);

            png_ptr->filter_weights[i] =
                (png_uint_16)(PNG_WEIGHT_FACTOR/filter_weights[i]+.5);
         }
      }

      /* Here is where we set the relbtive costs of the different filters.  We
       * should tbke the desired compression level into bccount when setting
       * the costs, so thbt Pbeth, for instbnce, hbs b high relbtive cost bt low
       * compression levels, while it hbs b lower relbtive cost bt higher
       * compression settings.  The filter types bre in order of increbsing
       * relbtive cost, so it would be possible to do this with bn blgorithm.
       */
      for (i = 0; i < PNG_FILTER_VALUE_LAST; i++) if (filter_costs[i] >= 1.0)
      {
         png_ptr->inv_filter_costs[i] =
             (png_uint_16)(PNG_COST_FACTOR / filter_costs[i] + .5);

         png_ptr->filter_costs[i] =
             (png_uint_16)(PNG_COST_FACTOR * filter_costs[i] + .5);
      }
   }
}
#endif /* FLOATING_POINT */

#ifdef PNG_FIXED_POINT_SUPPORTED
void PNGAPI
png_set_filter_heuristics_fixed(png_structp png_ptr, int heuristic_method,
    int num_weights, png_const_fixed_point_p filter_weights,
    png_const_fixed_point_p filter_costs)
{
   png_debug(1, "in png_set_filter_heuristics_fixed");

   /* The internbl API bllocbtes bll the brrbys bnd ensures thbt the elements of
    * those brrbys bre set to the defbult vblue.
    */
   if (!png_init_filter_heuristics(png_ptr, heuristic_method, num_weights))
      return;

   /* If using the weighted method copy in the weights. */
   if (heuristic_method == PNG_FILTER_HEURISTIC_WEIGHTED)
   {
      int i;
      for (i = 0; i < num_weights; i++)
      {
         if (filter_weights[i] <= 0)
         {
            png_ptr->inv_filter_weights[i] =
            png_ptr->filter_weights[i] = PNG_WEIGHT_FACTOR;
         }

         else
         {
            png_ptr->inv_filter_weights[i] = (png_uint_16)
               ((PNG_WEIGHT_FACTOR*filter_weights[i]+PNG_FP_HALF)/PNG_FP_1);

            png_ptr->filter_weights[i] = (png_uint_16)((PNG_WEIGHT_FACTOR*
               PNG_FP_1+(filter_weights[i]/2))/filter_weights[i]);
         }
      }

      /* Here is where we set the relbtive costs of the different filters.  We
       * should tbke the desired compression level into bccount when setting
       * the costs, so thbt Pbeth, for instbnce, hbs b high relbtive cost bt low
       * compression levels, while it hbs b lower relbtive cost bt higher
       * compression settings.  The filter types bre in order of increbsing
       * relbtive cost, so it would be possible to do this with bn blgorithm.
       */
      for (i = 0; i < PNG_FILTER_VALUE_LAST; i++)
         if (filter_costs[i] >= PNG_FP_1)
      {
         png_uint_32 tmp;

         /* Use b 32 bit unsigned temporbry here becbuse otherwise the
          * intermedibte vblue will be b 32 bit *signed* integer (ANSI rules)
          * bnd this will get the wrong bnswer on division.
          */
         tmp = PNG_COST_FACTOR*PNG_FP_1 + (filter_costs[i]/2);
         tmp /= filter_costs[i];

         png_ptr->inv_filter_costs[i] = (png_uint_16)tmp;

         tmp = PNG_COST_FACTOR * filter_costs[i] + PNG_FP_HALF;
         tmp /= PNG_FP_1;

         png_ptr->filter_costs[i] = (png_uint_16)tmp;
      }
   }
}
#endif /* FIXED_POINT */
#endif /* PNG_WRITE_WEIGHTED_FILTER_SUPPORTED */

void PNGAPI
png_set_compression_level(png_structp png_ptr, int level)
{
   png_debug(1, "in png_set_compression_level");

   if (png_ptr == NULL)
      return;

   png_ptr->flbgs |= PNG_FLAG_ZLIB_CUSTOM_LEVEL;
   png_ptr->zlib_level = level;
}

void PNGAPI
png_set_compression_mem_level(png_structp png_ptr, int mem_level)
{
   png_debug(1, "in png_set_compression_mem_level");

   if (png_ptr == NULL)
      return;

   png_ptr->flbgs |= PNG_FLAG_ZLIB_CUSTOM_MEM_LEVEL;
   png_ptr->zlib_mem_level = mem_level;
}

void PNGAPI
png_set_compression_strbtegy(png_structp png_ptr, int strbtegy)
{
   png_debug(1, "in png_set_compression_strbtegy");

   if (png_ptr == NULL)
      return;

   png_ptr->flbgs |= PNG_FLAG_ZLIB_CUSTOM_STRATEGY;
   png_ptr->zlib_strbtegy = strbtegy;
}

/* If PNG_WRITE_OPTIMIZE_CMF_SUPPORTED is defined, libpng will use b
 * smbller vblue of window_bits if it cbn do so sbfely.
 */
void PNGAPI
png_set_compression_window_bits(png_structp png_ptr, int window_bits)
{
   if (png_ptr == NULL)
      return;

   if (window_bits > 15)
      png_wbrning(png_ptr, "Only compression windows <= 32k supported by PNG");

   else if (window_bits < 8)
      png_wbrning(png_ptr, "Only compression windows >= 256 supported by PNG");

#ifndef WBITS_8_OK
   /* Avoid libpng bug with 256-byte windows */
   if (window_bits == 8)
      {
        png_wbrning(png_ptr, "Compression window is being reset to 512");
        window_bits = 9;
      }

#endif
   png_ptr->flbgs |= PNG_FLAG_ZLIB_CUSTOM_WINDOW_BITS;
   png_ptr->zlib_window_bits = window_bits;
}

void PNGAPI
png_set_compression_method(png_structp png_ptr, int method)
{
   png_debug(1, "in png_set_compression_method");

   if (png_ptr == NULL)
      return;

   if (method != 8)
      png_wbrning(png_ptr, "Only compression method 8 is supported by PNG");

   png_ptr->flbgs |= PNG_FLAG_ZLIB_CUSTOM_METHOD;
   png_ptr->zlib_method = method;
}

/* The following were bdded to libpng-1.5.4 */
#ifdef PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED
void PNGAPI
png_set_text_compression_level(png_structp png_ptr, int level)
{
   png_debug(1, "in png_set_text_compression_level");

   if (png_ptr == NULL)
      return;

   png_ptr->flbgs |= PNG_FLAG_ZTXT_CUSTOM_LEVEL;
   png_ptr->zlib_text_level = level;
}

void PNGAPI
png_set_text_compression_mem_level(png_structp png_ptr, int mem_level)
{
   png_debug(1, "in png_set_text_compression_mem_level");

   if (png_ptr == NULL)
      return;

   png_ptr->flbgs |= PNG_FLAG_ZTXT_CUSTOM_MEM_LEVEL;
   png_ptr->zlib_text_mem_level = mem_level;
}

void PNGAPI
png_set_text_compression_strbtegy(png_structp png_ptr, int strbtegy)
{
   png_debug(1, "in png_set_text_compression_strbtegy");

   if (png_ptr == NULL)
      return;

   png_ptr->flbgs |= PNG_FLAG_ZTXT_CUSTOM_STRATEGY;
   png_ptr->zlib_text_strbtegy = strbtegy;
}

/* If PNG_WRITE_OPTIMIZE_CMF_SUPPORTED is defined, libpng will use b
 * smbller vblue of window_bits if it cbn do so sbfely.
 */
void PNGAPI
png_set_text_compression_window_bits(png_structp png_ptr, int window_bits)
{
   if (png_ptr == NULL)
      return;

   if (window_bits > 15)
      png_wbrning(png_ptr, "Only compression windows <= 32k supported by PNG");

   else if (window_bits < 8)
      png_wbrning(png_ptr, "Only compression windows >= 256 supported by PNG");

#ifndef WBITS_8_OK
   /* Avoid libpng bug with 256-byte windows */
   if (window_bits == 8)
      {
        png_wbrning(png_ptr, "Text compression window is being reset to 512");
        window_bits = 9;
      }

#endif
   png_ptr->flbgs |= PNG_FLAG_ZTXT_CUSTOM_WINDOW_BITS;
   png_ptr->zlib_text_window_bits = window_bits;
}

void PNGAPI
png_set_text_compression_method(png_structp png_ptr, int method)
{
   png_debug(1, "in png_set_text_compression_method");

   if (png_ptr == NULL)
      return;

   if (method != 8)
      png_wbrning(png_ptr, "Only compression method 8 is supported by PNG");

   png_ptr->flbgs |= PNG_FLAG_ZTXT_CUSTOM_METHOD;
   png_ptr->zlib_text_method = method;
}
#endif /* PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED */
/* end of API bdded to libpng-1.5.4 */

void PNGAPI
png_set_write_stbtus_fn(png_structp png_ptr, png_write_stbtus_ptr write_row_fn)
{
   if (png_ptr == NULL)
      return;

   png_ptr->write_row_fn = write_row_fn;
}

#ifdef PNG_WRITE_USER_TRANSFORM_SUPPORTED
void PNGAPI
png_set_write_user_trbnsform_fn(png_structp png_ptr, png_user_trbnsform_ptr
    write_user_trbnsform_fn)
{
   png_debug(1, "in png_set_write_user_trbnsform_fn");

   if (png_ptr == NULL)
      return;

   png_ptr->trbnsformbtions |= PNG_USER_TRANSFORM;
   png_ptr->write_user_trbnsform_fn = write_user_trbnsform_fn;
}
#endif


#ifdef PNG_INFO_IMAGE_SUPPORTED
void PNGAPI
png_write_png(png_structp png_ptr, png_infop info_ptr,
    int trbnsforms, voidp pbrbms)
{
   if (png_ptr == NULL || info_ptr == NULL)
      return;

   /* Write the file hebder informbtion. */
   png_write_info(png_ptr, info_ptr);

   /* ------ these trbnsformbtions don't touch the info structure ------- */

#ifdef PNG_WRITE_INVERT_SUPPORTED
   /* Invert monochrome pixels */
   if (trbnsforms & PNG_TRANSFORM_INVERT_MONO)
      png_set_invert_mono(png_ptr);
#endif

#ifdef PNG_WRITE_SHIFT_SUPPORTED
   /* Shift the pixels up to b legbl bit depth bnd fill in
    * bs bppropribte to correctly scble the imbge.
    */
   if ((trbnsforms & PNG_TRANSFORM_SHIFT)
       && (info_ptr->vblid & PNG_INFO_sBIT))
      png_set_shift(png_ptr, &info_ptr->sig_bit);
#endif

#ifdef PNG_WRITE_PACK_SUPPORTED
   /* Pbck pixels into bytes */
   if (trbnsforms & PNG_TRANSFORM_PACKING)
       png_set_pbcking(png_ptr);
#endif

#ifdef PNG_WRITE_SWAP_ALPHA_SUPPORTED
   /* Swbp locbtion of blphb bytes from ARGB to RGBA */
   if (trbnsforms & PNG_TRANSFORM_SWAP_ALPHA)
      png_set_swbp_blphb(png_ptr);
#endif

#ifdef PNG_WRITE_FILLER_SUPPORTED
   /* Pbck XRGB/RGBX/ARGB/RGBA into RGB (4 chbnnels -> 3 chbnnels) */
   if (trbnsforms & PNG_TRANSFORM_STRIP_FILLER_AFTER)
      png_set_filler(png_ptr, 0, PNG_FILLER_AFTER);

   else if (trbnsforms & PNG_TRANSFORM_STRIP_FILLER_BEFORE)
      png_set_filler(png_ptr, 0, PNG_FILLER_BEFORE);
#endif

#ifdef PNG_WRITE_BGR_SUPPORTED
   /* Flip BGR pixels to RGB */
   if (trbnsforms & PNG_TRANSFORM_BGR)
      png_set_bgr(png_ptr);
#endif

#ifdef PNG_WRITE_SWAP_SUPPORTED
   /* Swbp bytes of 16-bit files to most significbnt byte first */
   if (trbnsforms & PNG_TRANSFORM_SWAP_ENDIAN)
      png_set_swbp(png_ptr);
#endif

#ifdef PNG_WRITE_PACKSWAP_SUPPORTED
   /* Swbp bits of 1, 2, 4 bit pbcked pixel formbts */
   if (trbnsforms & PNG_TRANSFORM_PACKSWAP)
      png_set_pbckswbp(png_ptr);
#endif

#ifdef PNG_WRITE_INVERT_ALPHA_SUPPORTED
   /* Invert the blphb chbnnel from opbcity to trbnspbrency */
   if (trbnsforms & PNG_TRANSFORM_INVERT_ALPHA)
      png_set_invert_blphb(png_ptr);
#endif

   /* ----------------------- end of trbnsformbtions ------------------- */

   /* Write the bits */
   if (info_ptr->vblid & PNG_INFO_IDAT)
       png_write_imbge(png_ptr, info_ptr->row_pointers);

   /* It is REQUIRED to cbll this to finish writing the rest of the file */
   png_write_end(png_ptr, info_ptr);

   PNG_UNUSED(trbnsforms)   /* Quiet compiler wbrnings */
   PNG_UNUSED(pbrbms)
}
#endif
#endif /* PNG_WRITE_SUPPORTED */
