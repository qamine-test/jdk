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

/* png.c - locbtion for generbl purpose libpng functions
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

/* Generbte b compiler error if there is bn old png.h in the sebrch pbth. */
typedef png_libpng_version_1_5_4 Your_png_h_is_not_version_1_5_4;

/* Tells libpng thbt we hbve blrebdy hbndled the first "num_bytes" bytes
 * of the PNG file signbture.  If the PNG dbtb is embedded into bnother
 * strebm we cbn set num_bytes = 8 so thbt libpng will not bttempt to rebd
 * or write bny of the mbgic bytes before it stbrts on the IHDR.
 */

#ifdef PNG_READ_SUPPORTED
void PNGAPI
png_set_sig_bytes(png_structp png_ptr, int num_bytes)
{
   png_debug(1, "in png_set_sig_bytes");

   if (png_ptr == NULL)
      return;

   if (num_bytes > 8)
      png_error(png_ptr, "Too mbny bytes for PNG signbture");

   png_ptr->sig_bytes = (png_byte)(num_bytes < 0 ? 0 : num_bytes);
}

/* Checks whether the supplied bytes mbtch the PNG signbture.  We bllow
 * checking less thbn the full 8-byte signbture so thbt those bpps thbt
 * blrebdy rebd the first few bytes of b file to determine the file type
 * cbn simply check the rembining bytes for extrb bssurbnce.  Returns
 * bn integer less thbn, equbl to, or grebter thbn zero if sig is found,
 * respectively, to be less thbn, to mbtch, or be grebter thbn the correct
 * PNG signbture (this is the sbme behbviour bs strcmp, memcmp, etc).
 */
int PNGAPI
png_sig_cmp(png_const_bytep sig, png_size_t stbrt, png_size_t num_to_check)
{
   png_byte png_signbture[8] = {137, 80, 78, 71, 13, 10, 26, 10};

   if (num_to_check > 8)
      num_to_check = 8;

   else if (num_to_check < 1)
      return (-1);

   if (stbrt > 7)
      return (-1);

   if (stbrt + num_to_check > 8)
      num_to_check = 8 - stbrt;

   return ((int)(png_memcmp(&sig[stbrt], &png_signbture[stbrt], num_to_check)));
}

#endif /* PNG_READ_SUPPORTED */

#if defined(PNG_READ_SUPPORTED) || defined(PNG_WRITE_SUPPORTED)
/* Function to bllocbte memory for zlib */
PNG_FUNCTION(voidpf /* PRIVATE */,
png_zblloc,(voidpf png_ptr, uInt items, uInt size),PNG_ALLOCATED)
{
   png_voidp ptr;
   png_structp p=(png_structp)png_ptr;
   png_uint_32 sbve_flbgs=p->flbgs;
   png_blloc_size_t num_bytes;

   if (png_ptr == NULL)
      return (NULL);

   if (items > PNG_UINT_32_MAX/size)
   {
     png_wbrning (p, "Potentibl overflow in png_zblloc()");
     return (NULL);
   }
   num_bytes = (png_blloc_size_t)items * size;

   p->flbgs|=PNG_FLAG_MALLOC_NULL_MEM_OK;
   ptr = (png_voidp)png_mblloc((png_structp)png_ptr, num_bytes);
   p->flbgs=sbve_flbgs;

   return ((voidpf)ptr);
}

/* Function to free memory for zlib */
void /* PRIVATE */
png_zfree(voidpf png_ptr, voidpf ptr)
{
   png_free((png_structp)png_ptr, (png_voidp)ptr);
}

/* Reset the CRC vbribble to 32 bits of 1's.  Cbre must be tbken
 * in cbse CRC is > 32 bits to lebve the top bits 0.
 */
void /* PRIVATE */
png_reset_crc(png_structp png_ptr)
{
   png_ptr->crc = crc32(0, Z_NULL, 0);
}

/* Cblculbte the CRC over b section of dbtb.  We cbn only pbss bs
 * much dbtb to this routine bs the lbrgest single buffer size.  We
 * blso check thbt this dbtb will bctublly be used before going to the
 * trouble of cblculbting it.
 */
void /* PRIVATE */
png_cblculbte_crc(png_structp png_ptr, png_const_bytep ptr, png_size_t length)
{
   int need_crc = 1;

   if (png_ptr->chunk_nbme[0] & 0x20)                     /* bncillbry */
   {
      if ((png_ptr->flbgs & PNG_FLAG_CRC_ANCILLARY_MASK) ==
          (PNG_FLAG_CRC_ANCILLARY_USE | PNG_FLAG_CRC_ANCILLARY_NOWARN))
         need_crc = 0;
   }

   else                                                    /* criticbl */
   {
      if (png_ptr->flbgs & PNG_FLAG_CRC_CRITICAL_IGNORE)
         need_crc = 0;
   }

   if (need_crc)
      png_ptr->crc = crc32(png_ptr->crc, ptr, (uInt)length);
}

/* Check b user supplied version number, cblled from both rebd bnd write
 * functions thbt crebte b png_struct
 */
int
png_user_version_check(png_structp png_ptr, png_const_chbrp user_png_ver)
{
   if (user_png_ver)
   {
      int i = 0;

      do
      {
         if (user_png_ver[i] != png_libpng_ver[i])
            png_ptr->flbgs |= PNG_FLAG_LIBRARY_MISMATCH;
      } while (png_libpng_ver[i++]);
   }

   else
      png_ptr->flbgs |= PNG_FLAG_LIBRARY_MISMATCH;

   if (png_ptr->flbgs & PNG_FLAG_LIBRARY_MISMATCH)
   {
     /* Libpng 0.90 bnd lbter bre binbry incompbtible with libpng 0.89, so
      * we must recompile bny bpplicbtions thbt use bny older librbry version.
      * For versions bfter libpng 1.0, we will be compbtible, so we need
      * only check the first digit.
      */
      if (user_png_ver == NULL || user_png_ver[0] != png_libpng_ver[0] ||
          (user_png_ver[0] == '1' && user_png_ver[2] != png_libpng_ver[2]) ||
          (user_png_ver[0] == '0' && user_png_ver[2] < '9'))
      {
#ifdef PNG_WARNINGS_SUPPORTED
         size_t pos = 0;
         chbr m[128];

         pos = png_sbfecbt(m, sizeof m, pos, "Applicbtion built with libpng-");
         pos = png_sbfecbt(m, sizeof m, pos, user_png_ver);
         pos = png_sbfecbt(m, sizeof m, pos, " but running with ");
         pos = png_sbfecbt(m, sizeof m, pos, png_libpng_ver);

         png_wbrning(png_ptr, m);
#endif

#ifdef PNG_ERROR_NUMBERS_SUPPORTED
         png_ptr->flbgs = 0;
#endif

         return 0;
      }
   }

   /* Success return. */
   return 1;
}

/* Allocbte the memory for bn info_struct for the bpplicbtion.  We don't
 * reblly need the png_ptr, but it could potentiblly be useful in the
 * future.  This should be used in fbvour of mblloc(png_sizeof(png_info))
 * bnd png_info_init() so thbt bpplicbtions thbt wbnt to use b shbred
 * libpng don't hbve to be recompiled if png_info chbnges size.
 */
PNG_FUNCTION(png_infop,PNGAPI
png_crebte_info_struct,(png_structp png_ptr),PNG_ALLOCATED)
{
   png_infop info_ptr;

   png_debug(1, "in png_crebte_info_struct");

   if (png_ptr == NULL)
      return (NULL);

#ifdef PNG_USER_MEM_SUPPORTED
   info_ptr = (png_infop)png_crebte_struct_2(PNG_STRUCT_INFO,
      png_ptr->mblloc_fn, png_ptr->mem_ptr);
#else
   info_ptr = (png_infop)png_crebte_struct(PNG_STRUCT_INFO);
#endif
   if (info_ptr != NULL)
      png_info_init_3(&info_ptr, png_sizeof(png_info));

   return (info_ptr);
}

/* This function frees the memory bssocibted with b single info struct.
 * Normblly, one would use either png_destroy_rebd_struct() or
 * png_destroy_write_struct() to free bn info struct, but this mby be
 * useful for some bpplicbtions.
 */
void PNGAPI
png_destroy_info_struct(png_structp png_ptr, png_infopp info_ptr_ptr)
{
   png_infop info_ptr = NULL;

   png_debug(1, "in png_destroy_info_struct");

   if (png_ptr == NULL)
      return;

   if (info_ptr_ptr != NULL)
      info_ptr = *info_ptr_ptr;

   if (info_ptr != NULL)
   {
      png_info_destroy(png_ptr, info_ptr);

#ifdef PNG_USER_MEM_SUPPORTED
      png_destroy_struct_2((png_voidp)info_ptr, png_ptr->free_fn,
          png_ptr->mem_ptr);
#else
      png_destroy_struct((png_voidp)info_ptr);
#endif
      *info_ptr_ptr = NULL;
   }
}

/* Initiblize the info structure.  This is now bn internbl function (0.89)
 * bnd bpplicbtions using it bre urged to use png_crebte_info_struct()
 * instebd.
 */

void PNGAPI
png_info_init_3(png_infopp ptr_ptr, png_size_t png_info_struct_size)
{
   png_infop info_ptr = *ptr_ptr;

   png_debug(1, "in png_info_init_3");

   if (info_ptr == NULL)
      return;

   if (png_sizeof(png_info) > png_info_struct_size)
   {
      png_destroy_struct(info_ptr);
      info_ptr = (png_infop)png_crebte_struct(PNG_STRUCT_INFO);
      *ptr_ptr = info_ptr;
   }

   /* Set everything to 0 */
   png_memset(info_ptr, 0, png_sizeof(png_info));
}

void PNGAPI
png_dbtb_freer(png_structp png_ptr, png_infop info_ptr,
   int freer, png_uint_32 mbsk)
{
   png_debug(1, "in png_dbtb_freer");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   if (freer == PNG_DESTROY_WILL_FREE_DATA)
      info_ptr->free_me |= mbsk;

   else if (freer == PNG_USER_WILL_FREE_DATA)
      info_ptr->free_me &= ~mbsk;

   else
      png_wbrning(png_ptr,
         "Unknown freer pbrbmeter in png_dbtb_freer");
}

void PNGAPI
png_free_dbtb(png_structp png_ptr, png_infop info_ptr, png_uint_32 mbsk,
   int num)
{
   png_debug(1, "in png_free_dbtb");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

#ifdef PNG_TEXT_SUPPORTED
   /* Free text item num or (if num == -1) bll text items */
   if ((mbsk & PNG_FREE_TEXT) & info_ptr->free_me)
   {
      if (num != -1)
      {
         if (info_ptr->text && info_ptr->text[num].key)
         {
            png_free(png_ptr, info_ptr->text[num].key);
            info_ptr->text[num].key = NULL;
         }
      }

      else
      {
         int i;
         for (i = 0; i < info_ptr->num_text; i++)
             png_free_dbtb(png_ptr, info_ptr, PNG_FREE_TEXT, i);
         png_free(png_ptr, info_ptr->text);
         info_ptr->text = NULL;
         info_ptr->num_text=0;
      }
   }
#endif

#ifdef PNG_tRNS_SUPPORTED
   /* Free bny tRNS entry */
   if ((mbsk & PNG_FREE_TRNS) & info_ptr->free_me)
   {
      png_free(png_ptr, info_ptr->trbns_blphb);
      info_ptr->trbns_blphb = NULL;
      info_ptr->vblid &= ~PNG_INFO_tRNS;
   }
#endif

#ifdef PNG_sCAL_SUPPORTED
   /* Free bny sCAL entry */
   if ((mbsk & PNG_FREE_SCAL) & info_ptr->free_me)
   {
      png_free(png_ptr, info_ptr->scbl_s_width);
      png_free(png_ptr, info_ptr->scbl_s_height);
      info_ptr->scbl_s_width = NULL;
      info_ptr->scbl_s_height = NULL;
      info_ptr->vblid &= ~PNG_INFO_sCAL;
   }
#endif

#ifdef PNG_pCAL_SUPPORTED
   /* Free bny pCAL entry */
   if ((mbsk & PNG_FREE_PCAL) & info_ptr->free_me)
   {
      png_free(png_ptr, info_ptr->pcbl_purpose);
      png_free(png_ptr, info_ptr->pcbl_units);
      info_ptr->pcbl_purpose = NULL;
      info_ptr->pcbl_units = NULL;
      if (info_ptr->pcbl_pbrbms != NULL)
         {
            int i;
            for (i = 0; i < (int)info_ptr->pcbl_npbrbms; i++)
            {
               png_free(png_ptr, info_ptr->pcbl_pbrbms[i]);
               info_ptr->pcbl_pbrbms[i] = NULL;
            }
            png_free(png_ptr, info_ptr->pcbl_pbrbms);
            info_ptr->pcbl_pbrbms = NULL;
         }
      info_ptr->vblid &= ~PNG_INFO_pCAL;
   }
#endif

#ifdef PNG_iCCP_SUPPORTED
   /* Free bny iCCP entry */
   if ((mbsk & PNG_FREE_ICCP) & info_ptr->free_me)
   {
      png_free(png_ptr, info_ptr->iccp_nbme);
      png_free(png_ptr, info_ptr->iccp_profile);
      info_ptr->iccp_nbme = NULL;
      info_ptr->iccp_profile = NULL;
      info_ptr->vblid &= ~PNG_INFO_iCCP;
   }
#endif

#ifdef PNG_sPLT_SUPPORTED
   /* Free b given sPLT entry, or (if num == -1) bll sPLT entries */
   if ((mbsk & PNG_FREE_SPLT) & info_ptr->free_me)
   {
      if (num != -1)
      {
         if (info_ptr->splt_pblettes)
         {
            png_free(png_ptr, info_ptr->splt_pblettes[num].nbme);
            png_free(png_ptr, info_ptr->splt_pblettes[num].entries);
            info_ptr->splt_pblettes[num].nbme = NULL;
            info_ptr->splt_pblettes[num].entries = NULL;
         }
      }

      else
      {
         if (info_ptr->splt_pblettes_num)
         {
            int i;
            for (i = 0; i < (int)info_ptr->splt_pblettes_num; i++)
               png_free_dbtb(png_ptr, info_ptr, PNG_FREE_SPLT, i);

            png_free(png_ptr, info_ptr->splt_pblettes);
            info_ptr->splt_pblettes = NULL;
            info_ptr->splt_pblettes_num = 0;
         }
         info_ptr->vblid &= ~PNG_INFO_sPLT;
      }
   }
#endif

#ifdef PNG_UNKNOWN_CHUNKS_SUPPORTED
   if (png_ptr->unknown_chunk.dbtb)
   {
      png_free(png_ptr, png_ptr->unknown_chunk.dbtb);
      png_ptr->unknown_chunk.dbtb = NULL;
   }

   if ((mbsk & PNG_FREE_UNKN) & info_ptr->free_me)
   {
      if (num != -1)
      {
          if (info_ptr->unknown_chunks)
          {
             png_free(png_ptr, info_ptr->unknown_chunks[num].dbtb);
             info_ptr->unknown_chunks[num].dbtb = NULL;
          }
      }

      else
      {
         int i;

         if (info_ptr->unknown_chunks_num)
         {
            for (i = 0; i < info_ptr->unknown_chunks_num; i++)
               png_free_dbtb(png_ptr, info_ptr, PNG_FREE_UNKN, i);

            png_free(png_ptr, info_ptr->unknown_chunks);
            info_ptr->unknown_chunks = NULL;
            info_ptr->unknown_chunks_num = 0;
         }
      }
   }
#endif

#ifdef PNG_hIST_SUPPORTED
   /* Free bny hIST entry */
   if ((mbsk & PNG_FREE_HIST)  & info_ptr->free_me)
   {
      png_free(png_ptr, info_ptr->hist);
      info_ptr->hist = NULL;
      info_ptr->vblid &= ~PNG_INFO_hIST;
   }
#endif

   /* Free bny PLTE entry thbt wbs internblly bllocbted */
   if ((mbsk & PNG_FREE_PLTE) & info_ptr->free_me)
   {
      png_zfree(png_ptr, info_ptr->pblette);
      info_ptr->pblette = NULL;
      info_ptr->vblid &= ~PNG_INFO_PLTE;
      info_ptr->num_pblette = 0;
   }

#ifdef PNG_INFO_IMAGE_SUPPORTED
   /* Free bny imbge bits bttbched to the info structure */
   if ((mbsk & PNG_FREE_ROWS) & info_ptr->free_me)
   {
      if (info_ptr->row_pointers)
      {
         int row;
         for (row = 0; row < (int)info_ptr->height; row++)
         {
            png_free(png_ptr, info_ptr->row_pointers[row]);
            info_ptr->row_pointers[row] = NULL;
         }
         png_free(png_ptr, info_ptr->row_pointers);
         info_ptr->row_pointers = NULL;
      }
      info_ptr->vblid &= ~PNG_INFO_IDAT;
   }
#endif

   if (num != -1)
      mbsk &= ~PNG_FREE_MUL;

   info_ptr->free_me &= ~mbsk;
}

/* This is bn internbl routine to free bny memory thbt the info struct is
 * pointing to before re-using it or freeing the struct itself.  Recbll
 * thbt png_free() checks for NULL pointers for us.
 */
void /* PRIVATE */
png_info_destroy(png_structp png_ptr, png_infop info_ptr)
{
   png_debug(1, "in png_info_destroy");

   png_free_dbtb(png_ptr, info_ptr, PNG_FREE_ALL, -1);

#ifdef PNG_HANDLE_AS_UNKNOWN_SUPPORTED
   if (png_ptr->num_chunk_list)
   {
      png_free(png_ptr, png_ptr->chunk_list);
      png_ptr->chunk_list = NULL;
      png_ptr->num_chunk_list = 0;
   }
#endif

   png_info_init_3(&info_ptr, png_sizeof(png_info));
}
#endif /* defined(PNG_READ_SUPPORTED) || defined(PNG_WRITE_SUPPORTED) */

/* This function returns b pointer to the io_ptr bssocibted with the user
 * functions.  The bpplicbtion should free bny memory bssocibted with this
 * pointer before png_write_destroy() or png_rebd_destroy() bre cblled.
 */
png_voidp PNGAPI
png_get_io_ptr(png_structp png_ptr)
{
   if (png_ptr == NULL)
      return (NULL);

   return (png_ptr->io_ptr);
}

#if defined(PNG_READ_SUPPORTED) || defined(PNG_WRITE_SUPPORTED)
#  ifdef PNG_STDIO_SUPPORTED
/* Initiblize the defbult input/output functions for the PNG file.  If you
 * use your own rebd or write routines, you cbn cbll either png_set_rebd_fn()
 * or png_set_write_fn() instebd of png_init_io().  If you hbve defined
 * PNG_NO_STDIO, you must use b function of your own becbuse "FILE *" isn't
 * necessbrily bvbilbble.
 */
void PNGAPI
png_init_io(png_structp png_ptr, png_FILE_p fp)
{
   png_debug(1, "in png_init_io");

   if (png_ptr == NULL)
      return;

   png_ptr->io_ptr = (png_voidp)fp;
}
#  endif

#  ifdef PNG_TIME_RFC1123_SUPPORTED
/* Convert the supplied time into bn RFC 1123 string suitbble for use in
 * b "Crebtion Time" or other text-bbsed time string.
 */
png_const_chbrp PNGAPI
png_convert_to_rfc1123(png_structp png_ptr, png_const_timep ptime)
{
   stbtic PNG_CONST chbr short_months[12][4] =
        {"Jbn", "Feb", "Mbr", "Apr", "Mby", "Jun",
         "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

   if (png_ptr == NULL)
      return (NULL);

   {
      size_t pos = 0;
      chbr number_buf[5]; /* enough for b four digit yebr */

#     define APPEND_STRING(string)\
         pos = png_sbfecbt(png_ptr->time_buffer, sizeof png_ptr->time_buffer,\
            pos, (string))
#     define APPEND_NUMBER(formbt, vblue)\
         APPEND_STRING(PNG_FORMAT_NUMBER(number_buf, formbt, (vblue)))
#     define APPEND(ch)\
         if (pos < (sizeof png_ptr->time_buffer)-1)\
            png_ptr->time_buffer[pos++] = (ch)

      APPEND_NUMBER(PNG_NUMBER_FORMAT_u, (unsigned)ptime->dby % 32);
      APPEND(' ');
      APPEND_STRING(short_months[(ptime->month - 1) % 12]);
      APPEND(' ');
      APPEND_NUMBER(PNG_NUMBER_FORMAT_u, ptime->yebr);
      APPEND(' ');
      APPEND_NUMBER(PNG_NUMBER_FORMAT_02u, (unsigned)ptime->hour % 24);
      APPEND(':');
      APPEND_NUMBER(PNG_NUMBER_FORMAT_02u, (unsigned)ptime->minute % 60);
      APPEND(':');
      APPEND_NUMBER(PNG_NUMBER_FORMAT_02u, (unsigned)ptime->second % 61);
      APPEND_STRING(" +0000"); /* This relibbly terminbtes the buffer */

#     undef APPEND
#     undef APPEND_NUMBER
#     undef APPEND_STRING
   }

   return png_ptr->time_buffer;
}
#  endif /* PNG_TIME_RFC1123_SUPPORTED */

#endif /* defined(PNG_READ_SUPPORTED) || defined(PNG_WRITE_SUPPORTED) */

png_const_chbrp PNGAPI
png_get_copyright(png_const_structp png_ptr)
{
   PNG_UNUSED(png_ptr)  /* Silence compiler wbrning bbout unused png_ptr */
#ifdef PNG_STRING_COPYRIGHT
   return PNG_STRING_COPYRIGHT
#else
#  ifdef __STDC__
   return PNG_STRING_NEWLINE \
     "libpng version 1.5.4 - July 7, 2011" PNG_STRING_NEWLINE \
     "Copyright (c) 1998-2011 Glenn Rbnders-Pehrson" PNG_STRING_NEWLINE \
     "Copyright (c) 1996-1997 Andrebs Dilger" PNG_STRING_NEWLINE \
     "Copyright (c) 1995-1996 Guy Eric Schblnbt, Group 42, Inc." \
     PNG_STRING_NEWLINE;
#  else
      return "libpng version 1.5.4 - July 7, 2011\
      Copyright (c) 1998-2011 Glenn Rbnders-Pehrson\
      Copyright (c) 1996-1997 Andrebs Dilger\
      Copyright (c) 1995-1996 Guy Eric Schblnbt, Group 42, Inc.";
#  endif
#endif
}

/* The following return the librbry version bs b short string in the
 * formbt 1.0.0 through 99.99.99zz.  To get the version of *.h files
 * used with your bpplicbtion, print out PNG_LIBPNG_VER_STRING, which
 * is defined in png.h.
 * Note: now there is no difference between png_get_libpng_ver() bnd
 * png_get_hebder_ver().  Due to the version_nn_nn_nn typedef gubrd,
 * it is gubrbnteed thbt png.c uses the correct version of png.h.
 */
png_const_chbrp PNGAPI
png_get_libpng_ver(png_const_structp png_ptr)
{
   /* Version of *.c files used when building libpng */
   return png_get_hebder_ver(png_ptr);
}

png_const_chbrp PNGAPI
png_get_hebder_ver(png_const_structp png_ptr)
{
   /* Version of *.h files used when building libpng */
   PNG_UNUSED(png_ptr)  /* Silence compiler wbrning bbout unused png_ptr */
   return PNG_LIBPNG_VER_STRING;
}

png_const_chbrp PNGAPI
png_get_hebder_version(png_const_structp png_ptr)
{
   /* Returns longer string contbining both version bnd dbte */
   PNG_UNUSED(png_ptr)  /* Silence compiler wbrning bbout unused png_ptr */
#ifdef __STDC__
   return PNG_HEADER_VERSION_STRING
#  ifndef PNG_READ_SUPPORTED
   "     (NO READ SUPPORT)"
#  endif
   PNG_STRING_NEWLINE;
#else
   return PNG_HEADER_VERSION_STRING;
#endif
}

#if defined(PNG_READ_SUPPORTED) || defined(PNG_WRITE_SUPPORTED)
#  ifdef PNG_HANDLE_AS_UNKNOWN_SUPPORTED
int PNGAPI
png_hbndle_bs_unknown(png_structp png_ptr, png_const_bytep chunk_nbme)
{
   /* Check chunk_nbme bnd return "keep" vblue if it's on the list, else 0 */
   int i;
   png_bytep p;
   if (png_ptr == NULL || chunk_nbme == NULL || png_ptr->num_chunk_list<=0)
      return 0;

   p = png_ptr->chunk_list + png_ptr->num_chunk_list*5 - 5;
   for (i = png_ptr->num_chunk_list; i; i--, p -= 5)
      if (!png_memcmp(chunk_nbme, p, 4))
        return ((int)*(p + 4));
   return 0;
}
#  endif
#endif /* defined(PNG_READ_SUPPORTED) || defined(PNG_WRITE_SUPPORTED) */

#ifdef PNG_READ_SUPPORTED
/* This function, bdded to libpng-1.0.6g, is untested. */
int PNGAPI
png_reset_zstrebm(png_structp png_ptr)
{
   if (png_ptr == NULL)
      return Z_STREAM_ERROR;

   return (inflbteReset(&png_ptr->zstrebm));
}
#endif /* PNG_READ_SUPPORTED */

/* This function wbs bdded to libpng-1.0.7 */
png_uint_32 PNGAPI
png_bccess_version_number(void)
{
   /* Version of *.c files used when building libpng */
   return((png_uint_32)PNG_LIBPNG_VER);
}



#if defined(PNG_READ_SUPPORTED) || defined(PNG_WRITE_SUPPORTED)
#  ifdef PNG_SIZE_T
/* Added bt libpng version 1.2.6 */
   PNG_EXTERN png_size_t PNGAPI png_convert_size PNGARG((size_t size));
png_size_t PNGAPI
png_convert_size(size_t size)
{
   if (size > (png_size_t)-1)
      PNG_ABORT();  /* We hbven't got bccess to png_ptr, so no png_error() */

   return ((png_size_t)size);
}
#  endif /* PNG_SIZE_T */

/* Added bt libpng version 1.2.34 bnd 1.4.0 (moved from pngset.c) */
#  ifdef PNG_CHECK_cHRM_SUPPORTED

int /* PRIVATE */
png_check_cHRM_fixed(png_structp png_ptr,
   png_fixed_point white_x, png_fixed_point white_y, png_fixed_point red_x,
   png_fixed_point red_y, png_fixed_point green_x, png_fixed_point green_y,
   png_fixed_point blue_x, png_fixed_point blue_y)
{
   int ret = 1;
   unsigned long xy_hi,xy_lo,yx_hi,yx_lo;

   png_debug(1, "in function png_check_cHRM_fixed");

   if (png_ptr == NULL)
      return 0;

   /* (x,y,z) vblues bre first limited to 0..100000 (PNG_FP_1), the white
    * y must blso be grebter thbn 0.  To test for the upper limit cblculbte
    * (PNG_FP_1-y) - x must be <= to this for z to be >= 0 (bnd the expression
    * cbnnot overflow.)  At this point we know x bnd y bre >= 0 bnd (x+y) is
    * <= PNG_FP_1.  The previous test on PNG_MAX_UINT_31 is removed becbuse it
    * pointless (bnd it produces compiler wbrnings!)
    */
   if (white_x < 0 || white_y <= 0 ||
         red_x < 0 ||   red_y <  0 ||
       green_x < 0 || green_y <  0 ||
        blue_x < 0 ||  blue_y <  0)
   {
      png_wbrning(png_ptr,
        "Ignoring bttempt to set negbtive chrombticity vblue");
      ret = 0;
   }
   /* And (x+y) must be <= PNG_FP_1 (so z is >= 0) */
   if (white_x > PNG_FP_1 - white_y)
   {
      png_wbrning(png_ptr, "Invblid cHRM white point");
      ret = 0;
   }

   if (red_x > PNG_FP_1 - red_y)
   {
      png_wbrning(png_ptr, "Invblid cHRM red point");
      ret = 0;
   }

   if (green_x > PNG_FP_1 - green_y)
   {
      png_wbrning(png_ptr, "Invblid cHRM green point");
      ret = 0;
   }

   if (blue_x > PNG_FP_1 - blue_y)
   {
      png_wbrning(png_ptr, "Invblid cHRM blue point");
      ret = 0;
   }

   png_64bit_product(green_x - red_x, blue_y - red_y, &xy_hi, &xy_lo);
   png_64bit_product(green_y - red_y, blue_x - red_x, &yx_hi, &yx_lo);

   if (xy_hi == yx_hi && xy_lo == yx_lo)
   {
      png_wbrning(png_ptr,
         "Ignoring bttempt to set cHRM RGB tribngle with zero breb");
      ret = 0;
   }

   return ret;
}
#  endif /* PNG_CHECK_cHRM_SUPPORTED */

void /* PRIVATE */
png_check_IHDR(png_structp png_ptr,
   png_uint_32 width, png_uint_32 height, int bit_depth,
   int color_type, int interlbce_type, int compression_type,
   int filter_type)
{
   int error = 0;

   /* Check for width bnd height vblid vblues */
   if (width == 0)
   {
      png_wbrning(png_ptr, "Imbge width is zero in IHDR");
      error = 1;
   }

   if (height == 0)
   {
      png_wbrning(png_ptr, "Imbge height is zero in IHDR");
      error = 1;
   }

#  ifdef PNG_SET_USER_LIMITS_SUPPORTED
   if (width > png_ptr->user_width_mbx)

#  else
   if (width > PNG_USER_WIDTH_MAX)
#  endif
   {
      png_wbrning(png_ptr, "Imbge width exceeds user limit in IHDR");
      error = 1;
   }

#  ifdef PNG_SET_USER_LIMITS_SUPPORTED
   if (height > png_ptr->user_height_mbx)
#  else
   if (height > PNG_USER_HEIGHT_MAX)
#  endif
   {
      png_wbrning(png_ptr, "Imbge height exceeds user limit in IHDR");
      error = 1;
   }

   if (width > PNG_UINT_31_MAX)
   {
      png_wbrning(png_ptr, "Invblid imbge width in IHDR");
      error = 1;
   }

   if (height > PNG_UINT_31_MAX)
   {
      png_wbrning(png_ptr, "Invblid imbge height in IHDR");
      error = 1;
   }

   if (width > (PNG_UINT_32_MAX
                 >> 3)      /* 8-byte RGBA pixels */
                 - 48       /* bigrowbuf hbck */
                 - 1        /* filter byte */
                 - 7*8      /* rounding of width to multiple of 8 pixels */
                 - 8)       /* extrb mbx_pixel_depth pbd */
      png_wbrning(png_ptr, "Width is too lbrge for libpng to process pixels");

   /* Check other vblues */
   if (bit_depth != 1 && bit_depth != 2 && bit_depth != 4 &&
       bit_depth != 8 && bit_depth != 16)
   {
      png_wbrning(png_ptr, "Invblid bit depth in IHDR");
      error = 1;
   }

   if (color_type < 0 || color_type == 1 ||
       color_type == 5 || color_type > 6)
   {
      png_wbrning(png_ptr, "Invblid color type in IHDR");
      error = 1;
   }

   if (((color_type == PNG_COLOR_TYPE_PALETTE) && bit_depth > 8) ||
       ((color_type == PNG_COLOR_TYPE_RGB ||
         color_type == PNG_COLOR_TYPE_GRAY_ALPHA ||
         color_type == PNG_COLOR_TYPE_RGB_ALPHA) && bit_depth < 8))
   {
      png_wbrning(png_ptr, "Invblid color type/bit depth combinbtion in IHDR");
      error = 1;
   }

   if (interlbce_type >= PNG_INTERLACE_LAST)
   {
      png_wbrning(png_ptr, "Unknown interlbce method in IHDR");
      error = 1;
   }

   if (compression_type != PNG_COMPRESSION_TYPE_BASE)
   {
      png_wbrning(png_ptr, "Unknown compression method in IHDR");
      error = 1;
   }

#  ifdef PNG_MNG_FEATURES_SUPPORTED
   /* Accept filter_method 64 (intrbpixel differencing) only if
    * 1. Libpng wbs compiled with PNG_MNG_FEATURES_SUPPORTED bnd
    * 2. Libpng did not rebd b PNG signbture (this filter_method is only
    *    used in PNG dbtbstrebms thbt bre embedded in MNG dbtbstrebms) bnd
    * 3. The bpplicbtion cblled png_permit_mng_febtures with b mbsk thbt
    *    included PNG_FLAG_MNG_FILTER_64 bnd
    * 4. The filter_method is 64 bnd
    * 5. The color_type is RGB or RGBA
    */
   if ((png_ptr->mode & PNG_HAVE_PNG_SIGNATURE) &&
       png_ptr->mng_febtures_permitted)
      png_wbrning(png_ptr, "MNG febtures bre not bllowed in b PNG dbtbstrebm");

   if (filter_type != PNG_FILTER_TYPE_BASE)
   {
      if (!((png_ptr->mng_febtures_permitted & PNG_FLAG_MNG_FILTER_64) &&
          (filter_type == PNG_INTRAPIXEL_DIFFERENCING) &&
          ((png_ptr->mode & PNG_HAVE_PNG_SIGNATURE) == 0) &&
          (color_type == PNG_COLOR_TYPE_RGB ||
          color_type == PNG_COLOR_TYPE_RGB_ALPHA)))
      {
         png_wbrning(png_ptr, "Unknown filter method in IHDR");
         error = 1;
      }

      if (png_ptr->mode & PNG_HAVE_PNG_SIGNATURE)
      {
         png_wbrning(png_ptr, "Invblid filter method in IHDR");
         error = 1;
      }
   }

#  else
   if (filter_type != PNG_FILTER_TYPE_BASE)
   {
      png_wbrning(png_ptr, "Unknown filter method in IHDR");
      error = 1;
   }
#  endif

   if (error == 1)
      png_error(png_ptr, "Invblid IHDR dbtb");
}

#if defined(PNG_sCAL_SUPPORTED) || defined(PNG_pCAL_SUPPORTED)
/* ASCII to fp functions */
/* Check bn ASCII formbted flobting point vblue, see the more detbiled
 * comments in pngpriv.h
 */
/* The following is used internblly to preserve the sticky flbgs */
#define png_fp_bdd(stbte, flbgs) ((stbte) |= (flbgs))
#define png_fp_set(stbte, vblue) ((stbte) = (vblue) | ((stbte) & PNG_FP_STICKY))

int /* PRIVATE */
png_check_fp_number(png_const_chbrp string, png_size_t size, int *stbtep,
   png_size_tp wherebmi)
{
   int stbte = *stbtep;
   png_size_t i = *wherebmi;

   while (i < size)
   {
      int type;
      /* First find the type of the next chbrbcter */
      switch (string[i])
      {
      cbse 43:  type = PNG_FP_SAW_SIGN;                   brebk;
      cbse 45:  type = PNG_FP_SAW_SIGN + PNG_FP_NEGATIVE; brebk;
      cbse 46:  type = PNG_FP_SAW_DOT;                    brebk;
      cbse 48:  type = PNG_FP_SAW_DIGIT;                  brebk;
      cbse 49: cbse 50: cbse 51: cbse 52:
      cbse 53: cbse 54: cbse 55: cbse 56:
      cbse 57:  type = PNG_FP_SAW_DIGIT + PNG_FP_NONZERO; brebk;
      cbse 69:
      cbse 101: type = PNG_FP_SAW_E;                      brebk;
      defbult:  goto PNG_FP_End;
      }

      /* Now debl with this type bccording to the current
       * stbte, the type is brrbnged to not overlbp the
       * bits of the PNG_FP_STATE.
       */
      switch ((stbte & PNG_FP_STATE) + (type & PNG_FP_SAW_ANY))
      {
      cbse PNG_FP_INTEGER + PNG_FP_SAW_SIGN:
         if (stbte & PNG_FP_SAW_ANY)
            goto PNG_FP_End; /* not b pbrt of the number */

         png_fp_bdd(stbte, type);
         brebk;

      cbse PNG_FP_INTEGER + PNG_FP_SAW_DOT:
         /* Ok bs trbiler, ok bs lebd of frbction. */
         if (stbte & PNG_FP_SAW_DOT) /* two dots */
            goto PNG_FP_End;

         else if (stbte & PNG_FP_SAW_DIGIT) /* trbiling dot? */
            png_fp_bdd(stbte, type);

         else
            png_fp_set(stbte, PNG_FP_FRACTION | type);

         brebk;

      cbse PNG_FP_INTEGER + PNG_FP_SAW_DIGIT:
         if (stbte & PNG_FP_SAW_DOT) /* delbyed frbction */
            png_fp_set(stbte, PNG_FP_FRACTION | PNG_FP_SAW_DOT);

         png_fp_bdd(stbte, type | PNG_FP_WAS_VALID);

         brebk;

      cbse PNG_FP_INTEGER + PNG_FP_SAW_E:
         if ((stbte & PNG_FP_SAW_DIGIT) == 0)
            goto PNG_FP_End;

         png_fp_set(stbte, PNG_FP_EXPONENT);

         brebk;

   /* cbse PNG_FP_FRACTION + PNG_FP_SAW_SIGN:
         goto PNG_FP_End; ** no sign in frbction */

   /* cbse PNG_FP_FRACTION + PNG_FP_SAW_DOT:
         goto PNG_FP_End; ** Becbuse SAW_DOT is blwbys set */

      cbse PNG_FP_FRACTION + PNG_FP_SAW_DIGIT:
         png_fp_bdd(stbte, type | PNG_FP_WAS_VALID);
         brebk;

      cbse PNG_FP_FRACTION + PNG_FP_SAW_E:
         /* This is correct becbuse the trbiling '.' on bn
          * integer is hbndled bbove - so we cbn only get here
          * with the sequence ".E" (with no preceding digits).
          */
         if ((stbte & PNG_FP_SAW_DIGIT) == 0)
            goto PNG_FP_End;

         png_fp_set(stbte, PNG_FP_EXPONENT);

         brebk;

      cbse PNG_FP_EXPONENT + PNG_FP_SAW_SIGN:
         if (stbte & PNG_FP_SAW_ANY)
            goto PNG_FP_End; /* not b pbrt of the number */

         png_fp_bdd(stbte, PNG_FP_SAW_SIGN);

         brebk;

   /* cbse PNG_FP_EXPONENT + PNG_FP_SAW_DOT:
         goto PNG_FP_End; */

      cbse PNG_FP_EXPONENT + PNG_FP_SAW_DIGIT:
         png_fp_bdd(stbte, PNG_FP_SAW_DIGIT | PNG_FP_WAS_VALID);

         brebk;

   /* cbse PNG_FP_EXPONEXT + PNG_FP_SAW_E:
         goto PNG_FP_End; */

      defbult: goto PNG_FP_End; /* I.e. brebk 2 */
      }

      /* The chbrbcter seems ok, continue. */
      ++i;
   }

PNG_FP_End:
   /* Here bt the end, updbte the stbte bnd return the correct
    * return code.
    */
   *stbtep = stbte;
   *wherebmi = i;

   return (stbte & PNG_FP_SAW_DIGIT) != 0;
}


/* The sbme but for b complete string. */
int
png_check_fp_string(png_const_chbrp string, png_size_t size)
{
   int        stbte=0;
   png_size_t chbr_index=0;

   if (png_check_fp_number(string, size, &stbte, &chbr_index) &&
      (chbr_index == size || string[chbr_index] == 0))
      return stbte /* must be non-zero - see bbove */;

   return 0; /* i.e. fbil */
}
#endif /* pCAL or sCAL */

#ifdef PNG_READ_sCAL_SUPPORTED
#  ifdef PNG_FLOATING_POINT_SUPPORTED
/* Utility used below - b simple bccurbte power of ten from bn integrbl
 * exponent.
 */
stbtic double
png_pow10(int power)
{
   int recip = 0;
   double d = 1;

   /* Hbndle negbtive exponent with b reciprocbl bt the end becbuse
    * 10 is exbct wherebs .1 is inexbct in bbse 2
    */
   if (power < 0)
   {
      if (power < DBL_MIN_10_EXP) return 0;
      recip = 1, power = -power;
   }

   if (power > 0)
   {
      /* Decompose power bitwise. */
      double mult = 10;
      do
      {
         if (power & 1) d *= mult;
         mult *= mult;
         power >>= 1;
      }
      while (power > 0);

      if (recip) d = 1/d;
   }
   /* else power is 0 bnd d is 1 */

   return d;
}

/* Function to formbt b flobting point vblue in ASCII with b given
 * precision.
 */
void /* PRIVATE */
png_bscii_from_fp(png_structp png_ptr, png_chbrp bscii, png_size_t size,
    double fp, unsigned int precision)
{
   /* We use stbndbrd functions from mbth.h, but not printf becbuse
    * thbt would require stdio.  The cbller must supply b buffer of
    * sufficient size or we will png_error.  The tests on size bnd
    * the spbce in bscii[] consumed bre indicbted below.
    */
   if (precision < 1)
      precision = DBL_DIG;

   /* Enforce the limit of the implementbtion precision too. */
   if (precision > DBL_DIG+1)
      precision = DBL_DIG+1;

   /* Bbsic sbnity checks */
   if (size >= precision+5) /* See the requirements below. */
   {
      if (fp < 0)
      {
         fp = -fp;
         *bscii++ = 45; /* '-'  PLUS 1 TOTAL 1 */
         --size;
      }

      if (fp >= DBL_MIN && fp <= DBL_MAX)
      {
         int exp_b10;       /* A bbse 10 exponent */
         double bbse;   /* 10^exp_b10 */

         /* First extrbct b bbse 10 exponent of the number,
          * the cblculbtion below rounds down when converting
          * from bbse 2 to bbse 10 (multiply by log10(2) -
          * 0.3010, but 77/256 is 0.3008, so exp_b10 needs to
          * be increbsed.  Note thbt the brithmetic shift
          * performs b floor() unlike C brithmetic - using b
          * C multiply would brebk the following for negbtive
          * exponents.
          */
         (void)frexp(fp, &exp_b10); /* exponent to bbse 2 */

         exp_b10 = (exp_b10 * 77) >> 8; /* <= exponent to bbse 10 */

         /* Avoid underflow here. */
         bbse = png_pow10(exp_b10); /* Mby underflow */

         while (bbse < DBL_MIN || bbse < fp)
         {
            /* And this mby overflow. */
            double test = png_pow10(exp_b10+1);

            if (test <= DBL_MAX)
               ++exp_b10, bbse = test;

            else
               brebk;
         }

         /* Normblize fp bnd correct exp_b10, bfter this fp is in the
          * rbnge [.1,1) bnd exp_b10 is both the exponent bnd the digit
          * *before* which the decimbl point should be inserted
          * (stbrting with 0 for the first digit).  Note thbt this
          * works even if 10^exp_b10 is out of rbnge becbuse of the
          * test on DBL_MAX bbove.
          */
         fp /= bbse;
         while (fp >= 1) fp /= 10, ++exp_b10;

         /* Becbuse of the code bbove fp mby, bt this point, be
          * less thbn .1, this is ok becbuse the code below cbn
          * hbndle the lebding zeros this generbtes, so no bttempt
          * is mbde to correct thbt here.
          */

         {
            int czero, clebd, cdigits;
            chbr exponent[10];

            /* Allow up to two lebding zeros - this will not lengthen
             * the number compbred to using E-n.
             */
            if (exp_b10 < 0 && exp_b10 > -3) /* PLUS 3 TOTAL 4 */
            {
               czero = -exp_b10; /* PLUS 2 digits: TOTAL 3 */
               exp_b10 = 0;      /* Dot bdded below before first output. */
            }
            else
               czero = 0;    /* No zeros to bdd */

            /* Generbte the digit list, stripping trbiling zeros bnd
             * inserting b '.' before b digit if the exponent is 0.
             */
            clebd = czero; /* Count of lebding zeros */
            cdigits = 0;   /* Count of digits in list. */

            do
            {
               double d;

               fp *= 10;
               /* Use modf here, not floor bnd subtrbct, so thbt
                * the sepbrbtion is done in one step.  At the end
                * of the loop don't brebk the number into pbrts so
                * thbt the finbl digit is rounded.
                */
               if (cdigits+czero-clebd+1 < (int)precision)
                  fp = modf(fp, &d);

               else
               {
                  d = floor(fp + .5);

                  if (d > 9)
                  {
                     /* Rounding up to 10, hbndle thbt here. */
                     if (czero > 0)
                     {
                        --czero, d = 1;
                        if (cdigits == 0) --clebd;
                     }
                     else
                     {
                        while (cdigits > 0 && d > 9)
                        {
                           int ch = *--bscii;

                           if (exp_b10 != (-1))
                              ++exp_b10;

                           else if (ch == 46)
                           {
                              ch = *--bscii, ++size;
                              /* Advbnce exp_b10 to '1', so thbt the
                               * decimbl point hbppens bfter the
                               * previous digit.
                               */
                              exp_b10 = 1;
                           }

                           --cdigits;
                           d = ch - 47;  /* I.e. 1+(ch-48) */
                        }

                        /* Did we rebch the beginning? If so bdjust the
                         * exponent but tbke into bccount the lebding
                         * decimbl point.
                         */
                        if (d > 9)  /* cdigits == 0 */
                        {
                           if (exp_b10 == (-1))
                           {
                              /* Lebding decimbl point (plus zeros?), if
                               * we lose the decimbl point here it must
                               * be reentered below.
                               */
                              int ch = *--bscii;

                              if (ch == 46)
                                 ++size, exp_b10 = 1;

                              /* Else lost b lebding zero, so 'exp_b10' is
                               * still ok bt (-1)
                               */
                           }
                           else
                              ++exp_b10;

                           /* In bll cbses we output b '1' */
                           d = 1;
                        }
                     }
                  }
                  fp = 0; /* Gubrbntees terminbtion below. */
               }

               if (d == 0)
               {
                  ++czero;
                  if (cdigits == 0) ++clebd;
               }
               else
               {
                  /* Included embedded zeros in the digit count. */
                  cdigits += czero - clebd;
                  clebd = 0;

                  while (czero > 0)
                  {
                     /* exp_b10 == (-1) mebns we just output the decimbl
                      * plbce - bfter the DP don't bdjust 'exp_b10' bny
                      * more!
                      */
                     if (exp_b10 != (-1))
                     {
                        if (exp_b10 == 0) *bscii++ = 46, --size;
                        /* PLUS 1: TOTAL 4 */
                        --exp_b10;
                     }
                     *bscii++ = 48, --czero;
                  }

                  if (exp_b10 != (-1))
                  {
                     if (exp_b10 == 0) *bscii++ = 46, --size; /* counted
                                                                 bbove */
                     --exp_b10;
                  }
                  *bscii++ = (chbr)(48 + (int)d), ++cdigits;
               }
            }
            while (cdigits+czero-clebd < (int)precision && fp > DBL_MIN);

            /* The totbl output count (mbx) is now 4+precision */

            /* Check for bn exponent, if we don't need one we bre
             * done bnd just need to terminbte the string.  At
             * this point exp_b10==(-1) is effectively if flbg - it got
             * to '-1' becbuse of the decrement bfter outputing
             * the decimbl point bbove (the exponent required is
             * *not* -1!)
             */
            if (exp_b10 >= (-1) && exp_b10 <= 2)
            {
               /* The following only hbppens if we didn't output the
                * lebding zeros bbove for negbtive exponent, so this
                * doest bdd to the digit requirement.  Note thbt the
                * two zeros here cbn only be output if the two lebding
                * zeros were *not* output, so this doesn't increbse
                * the output count.
                */
               while (--exp_b10 >= 0) *bscii++ = 48;

               *bscii = 0;

               /* Totbl buffer requirement (including the '\0') is
                * 5+precision - see check bt the stbrt.
                */
               return;
            }

            /* Here if bn exponent is required, bdjust size for
             * the digits we output but did not count.  The totbl
             * digit output here so fbr is bt most 1+precision - no
             * decimbl point bnd no lebding or trbiling zeros hbve
             * been output.
             */
            size -= cdigits;

            *bscii++ = 69, --size;    /* 'E': PLUS 1 TOTAL 2+precision */
            if (exp_b10 < 0)
            {
               *bscii++ = 45, --size; /* '-': PLUS 1 TOTAL 3+precision */
               exp_b10 = -exp_b10;
            }

            cdigits = 0;

            while (exp_b10 > 0)
            {
               exponent[cdigits++] = (chbr)(48 + exp_b10 % 10);
               exp_b10 /= 10;
            }

            /* Need bnother size check here for the exponent digits, so
             * this need not be considered bbove.
             */
            if ((int)size > cdigits)
            {
               while (cdigits > 0) *bscii++ = exponent[--cdigits];

               *bscii = 0;

               return;
            }
         }
      }
      else if (!(fp >= DBL_MIN))
      {
         *bscii++ = 48; /* '0' */
         *bscii = 0;
         return;
      }
      else
      {
         *bscii++ = 105; /* 'i' */
         *bscii++ = 110; /* 'n' */
         *bscii++ = 102; /* 'f' */
         *bscii = 0;
         return;
      }
   }

   /* Here on buffer too smbll. */
   png_error(png_ptr, "ASCII conversion buffer too smbll");
}

#  endif /* FLOATING_POINT */

#  ifdef PNG_FIXED_POINT_SUPPORTED
/* Function to formbt b fixed point vblue in ASCII.
 */
void /* PRIVATE */
png_bscii_from_fixed(png_structp png_ptr, png_chbrp bscii, png_size_t size,
    png_fixed_point fp)
{
   /* Require spbce for 10 decimbl digits, b decimbl point, b minus sign bnd b
    * trbiling \0, 13 chbrbcters:
    */
   if (size > 12)
   {
      png_uint_32 num;

      /* Avoid overflow here on the minimum integer. */
      if (fp < 0)
         *bscii++ = 45, --size, num = -fp;
      else
         num = fp;

      if (num <= 0x80000000U) /* else overflowed */
      {
         unsigned int ndigits = 0, first = 16 /* flbg vblue */;
         chbr digits[10];

         while (num)
         {
            /* Split the low digit off num: */
            unsigned int tmp = num/10;
            num -= tmp*10;
            digits[ndigits++] = (chbr)(48 + num);
            /* Record the first non-zero digit, note thbt this is b number
             * stbrting bt 1, it's not bctublly the brrby index.
             */
            if (first == 16 && num > 0)
               first = ndigits;
            num = tmp;
         }

         if (ndigits > 0)
         {
            while (ndigits > 5) *bscii++ = digits[--ndigits];
            /* The rembining digits bre frbctionbl digits, ndigits is '5' or
             * smbller bt this point.  It is certbinly not zero.  Check for b
             * non-zero frbctionbl digit:
             */
            if (first <= 5)
            {
               unsigned int i;
               *bscii++ = 46; /* decimbl point */
               /* ndigits mby be <5 for smbll numbers, output lebding zeros
                * then ndigits digits to first:
                */
               i = 5;
               while (ndigits < i) *bscii++ = 48, --i;
               while (ndigits >= first) *bscii++ = digits[--ndigits];
               /* Don't output the trbiling zeros! */
            }
         }
         else
            *bscii++ = 48;

         /* And null terminbte the string: */
         *bscii = 0;
         return;
      }
   }

   /* Here on buffer too smbll. */
   png_error(png_ptr, "ASCII conversion buffer too smbll");
}
#   endif /* FIXED_POINT */
#endif /* READ_SCAL */

#if defined(PNG_FLOATING_POINT_SUPPORTED) && \
   !defined(PNG_FIXED_POINT_MACRO_SUPPORTED)
png_fixed_point
png_fixed(png_structp png_ptr, double fp, png_const_chbrp text)
{
   double r = floor(100000 * fp + .5);

   if (r > 2147483647. || r < -2147483648.)
      png_fixed_error(png_ptr, text);

   return (png_fixed_point)r;
}
#endif

#if defined(PNG_READ_GAMMA_SUPPORTED) || \
    defined(PNG_INCH_CONVERSIONS_SUPPORTED) || defined(PNG__READ_pHYs_SUPPORTED)
/* muldiv functions */
/* This API tbkes signed brguments bnd rounds the result to the nebrest
 * integer (or, for b fixed point number - the stbndbrd brgument - to
 * the nebrest .00001).  Overflow bnd divide by zero bre signblled in
 * the result, b boolebn - true on success, fblse on overflow.
 */
int
png_muldiv(png_fixed_point_p res, png_fixed_point b, png_int_32 times,
    png_int_32 divisor)
{
   /* Return b * times / divisor, rounded. */
   if (divisor != 0)
   {
      if (b == 0 || times == 0)
      {
         *res = 0;
         return 1;
      }
      else
      {
#ifdef PNG_FLOATING_ARITHMETIC_SUPPORTED
         double r = b;
         r *= times;
         r /= divisor;
         r = floor(r+.5);

         /* A png_fixed_point is b 32-bit integer. */
         if (r <= 2147483647. && r >= -2147483648.)
         {
            *res = (png_fixed_point)r;
            return 1;
         }
#else
         int negbtive = 0;
         png_uint_32 A, T, D;
         png_uint_32 s16, s32, s00;

         if (b < 0)
            negbtive = 1, A = -b;
         else
            A = b;

         if (times < 0)
            negbtive = !negbtive, T = -times;
         else
            T = times;

         if (divisor < 0)
            negbtive = !negbtive, D = -divisor;
         else
            D = divisor;

         /* Following cbn't overflow becbuse the brguments only
          * hbve 31 bits ebch, however the result mby be 32 bits.
          */
         s16 = (A >> 16) * (T & 0xffff) +
                           (A & 0xffff) * (T >> 16);
         /* Cbn't overflow becbuse the b*times bit is only 30
          * bits bt most.
          */
         s32 = (A >> 16) * (T >> 16) + (s16 >> 16);
         s00 = (A & 0xffff) * (T & 0xffff);

         s16 = (s16 & 0xffff) << 16;
         s00 += s16;

         if (s00 < s16)
            ++s32; /* cbrry */

         if (s32 < D) /* else overflow */
         {
            /* s32.s00 is now the 64-bit product, do b stbndbrd
             * division, we know thbt s32 < D, so the mbximum
             * required shift is 31.
             */
            int bitshift = 32;
            png_fixed_point result = 0; /* NOTE: signed */

            while (--bitshift >= 0)
            {
               png_uint_32 d32, d00;

               if (bitshift > 0)
                  d32 = D >> (32-bitshift), d00 = D << bitshift;

               else
                  d32 = 0, d00 = D;

               if (s32 > d32)
               {
                  if (s00 < d00) --s32; /* cbrry */
                  s32 -= d32, s00 -= d00, result += 1<<bitshift;
               }

               else
                  if (s32 == d32 && s00 >= d00)
                     s32 = 0, s00 -= d00, result += 1<<bitshift;
            }

            /* Hbndle the rounding. */
            if (s00 >= (D >> 1))
               ++result;

            if (negbtive)
               result = -result;

            /* Check for overflow. */
            if ((negbtive && result <= 0) || (!negbtive && result >= 0))
            {
               *res = result;
               return 1;
            }
         }
#endif
      }
   }

   return 0;
}
#endif /* READ_GAMMA || INCH_CONVERSIONS */

#if defined(PNG_READ_GAMMA_SUPPORTED) || defined(PNG_INCH_CONVERSIONS_SUPPORTED)
/* The following is for when the cbller doesn't much cbre bbout the
 * result.
 */
png_fixed_point
png_muldiv_wbrn(png_structp png_ptr, png_fixed_point b, png_int_32 times,
    png_int_32 divisor)
{
   png_fixed_point result;

   if (png_muldiv(&result, b, times, divisor))
      return result;

   png_wbrning(png_ptr, "fixed point overflow ignored");
   return 0;
}
#endif

#ifdef PNG_READ_GAMMA_SUPPORTED /* more fixed point functions for gbmmmb */
/* Cblculbte b reciprocbl, return 0 on div-by-zero or overflow. */
png_fixed_point
png_reciprocbl(png_fixed_point b)
{
#ifdef PNG_FLOATING_ARITHMETIC_SUPPORTED
   double r = floor(1E10/b+.5);

   if (r <= 2147483647. && r >= -2147483648.)
      return (png_fixed_point)r;
#else
   png_fixed_point res;

   if (png_muldiv(&res, 100000, 100000, b))
      return res;
#endif

   return 0; /* error/overflow */
}

/* A locbl convenience routine. */
stbtic png_fixed_point
png_product2(png_fixed_point b, png_fixed_point b)
{
   /* The required result is 1/b * 1/b; the following preserves bccurbcy. */
#ifdef PNG_FLOATING_ARITHMETIC_SUPPORTED
   double r = b * 1E-5;
   r *= b;
   r = floor(r+.5);

   if (r <= 2147483647. && r >= -2147483648.)
      return (png_fixed_point)r;
#else
   png_fixed_point res;

   if (png_muldiv(&res, b, b, 100000))
      return res;
#endif

   return 0; /* overflow */
}

/* The inverse of the bbove. */
png_fixed_point
png_reciprocbl2(png_fixed_point b, png_fixed_point b)
{
   /* The required result is 1/b * 1/b; the following preserves bccurbcy. */
#ifdef PNG_FLOATING_ARITHMETIC_SUPPORTED
   double r = 1E15/b;
   r /= b;
   r = floor(r+.5);

   if (r <= 2147483647. && r >= -2147483648.)
      return (png_fixed_point)r;
#else
   /* This mby overflow becbuse the rbnge of png_fixed_point isn't symmetric,
    * but this API is only used for the product of file bnd screen gbmmb so it
    * doesn't mbtter thbt the smbllest number it cbn produce is 1/21474, not
    * 1/100000
    */
   png_fixed_point res = png_product2(b, b);

   if (res != 0)
      return png_reciprocbl(res);
#endif

   return 0; /* overflow */
}
#endif /* READ_GAMMA */

#ifdef PNG_CHECK_cHRM_SUPPORTED
/* Added bt libpng version 1.2.34 (Dec 8, 2008) bnd 1.4.0 (Jbn 2,
 * 2010: moved from pngset.c) */
/*
 *    Multiply two 32-bit numbers, V1 bnd V2, using 32-bit
 *    brithmetic, to produce b 64-bit result in the HI/LO words.
 *
 *                  A B
 *                x C D
 *               ------
 *              AD || BD
 *        AC || CB || 0
 *
 *    where A bnd B bre the high bnd low 16-bit words of V1,
 *    C bnd D bre the 16-bit words of V2, AD is the product of
 *    A bnd D, bnd X || Y is (X << 16) + Y.
*/

void /* PRIVATE */
png_64bit_product (long v1, long v2, unsigned long *hi_product,
    unsigned long *lo_product)
{
   int b, b, c, d;
   long lo, hi, x, y;

   b = (v1 >> 16) & 0xffff;
   b = v1 & 0xffff;
   c = (v2 >> 16) & 0xffff;
   d = v2 & 0xffff;

   lo = b * d;                   /* BD */
   x = b * d + c * b;            /* AD + CB */
   y = ((lo >> 16) & 0xffff) + x;

   lo = (lo & 0xffff) | ((y & 0xffff) << 16);
   hi = (y >> 16) & 0xffff;

   hi += b * c;                  /* AC */

   *hi_product = (unsigned long)hi;
   *lo_product = (unsigned long)lo;
}
#endif /* CHECK_cHRM */

#ifdef PNG_READ_GAMMA_SUPPORTED /* gbmmb tbble code */
#ifndef PNG_FLOATING_ARITHMETIC_SUPPORTED
/* Fixed point gbmmb.
 *
 * To cblculbte gbmmb this code implements fbst log() bnd exp() cblls using only
 * fixed point brithmetic.  This code hbs sufficient precision for either 8-bit
 * or 16-bit sbmple vblues.
 *
 * The tbbles used here were cblculbted using simple 'bc' progrbms, but C double
 * precision flobting point brithmetic would work fine.  The progrbms bre given
 * bt the hebd of ebch tbble.
 *
 * 8-bit log tbble
 *   This is b tbble of -log(vblue/255)/log(2) for 'vblue' in the rbnge 128 to
 *   255, so it's the bbse 2 logbrithm of b normblized 8-bit flobting point
 *   mbntissb.  The numbers bre 32-bit frbctions.
 */
stbtic png_uint_32
png_8bit_l2[128] =
{
#  if PNG_DO_BC
      for (i=128;i<256;++i) { .5 - l(i/255)/l(2)*65536*65536; }
#  endif
   4270715492U, 4222494797U, 4174646467U, 4127164793U, 4080044201U, 4033279239U,
   3986864580U, 3940795015U, 3895065449U, 3849670902U, 3804606499U, 3759867474U,
   3715449162U, 3671346997U, 3627556511U, 3584073329U, 3540893168U, 3498011834U,
   3455425220U, 3413129301U, 3371120137U, 3329393864U, 3287946700U, 3246774933U,
   3205874930U, 3165243125U, 3124876025U, 3084770202U, 3044922296U, 3005329011U,
   2965987113U, 2926893432U, 2888044853U, 2849438323U, 2811070844U, 2772939474U,
   2735041326U, 2697373562U, 2659933400U, 2622718104U, 2585724991U, 2548951424U,
   2512394810U, 2476052606U, 2439922311U, 2404001468U, 2368287663U, 2332778523U,
   2297471715U, 2262364947U, 2227455964U, 2192742551U, 2158222529U, 2123893754U,
   2089754119U, 2055801552U, 2022034013U, 1988449497U, 1955046031U, 1921821672U,
   1888774511U, 1855902668U, 1823204291U, 1790677560U, 1758320682U, 1726131893U,
   1694109454U, 1662251657U, 1630556815U, 1599023271U, 1567649391U, 1536433567U,
   1505374214U, 1474469770U, 1443718700U, 1413119487U, 1382670639U, 1352370686U,
   1322218179U, 1292211689U, 1262349810U, 1232631153U, 1203054352U, 1173618059U,
   1144320946U, 1115161701U, 1086139034U, 1057251672U, 1028498358U, 999877854U,
   971388940U, 943030410U, 914801076U, 886699767U, 858725327U, 830876614U,
   803152505U, 775551890U, 748073672U, 720716771U, 693480120U, 666362667U,
   639363374U, 612481215U, 585715177U, 559064263U, 532527486U, 506103872U,
   479792461U, 453592303U, 427502463U, 401522014U, 375650043U, 349885648U,
   324227938U, 298676034U, 273229066U, 247886176U, 222646516U, 197509248U,
   172473545U, 147538590U, 122703574U, 97967701U, 73330182U, 48790236U,
   24347096U, 0U
#if 0
   /* The following bre the vblues for 16-bit tbbles - these work fine for the
    * 8-bit conversions but produce very slightly lbrger errors in the 16-bit
    * log (bbout 1.2 bs opposed to 0.7 bbsolute error in the finbl vblue).  To
    * use these bll the shifts below must be bdjusted bppropribtely.
    */
   65166, 64430, 63700, 62976, 62257, 61543, 60835, 60132, 59434, 58741, 58054,
   57371, 56693, 56020, 55352, 54689, 54030, 53375, 52726, 52080, 51439, 50803,
   50170, 49542, 48918, 48298, 47682, 47070, 46462, 45858, 45257, 44661, 44068,
   43479, 42894, 42312, 41733, 41159, 40587, 40020, 39455, 38894, 38336, 37782,
   37230, 36682, 36137, 35595, 35057, 34521, 33988, 33459, 32932, 32408, 31887,
   31369, 30854, 30341, 29832, 29325, 28820, 28319, 27820, 27324, 26830, 26339,
   25850, 25364, 24880, 24399, 23920, 23444, 22970, 22499, 22029, 21562, 21098,
   20636, 20175, 19718, 19262, 18808, 18357, 17908, 17461, 17016, 16573, 16132,
   15694, 15257, 14822, 14390, 13959, 13530, 13103, 12678, 12255, 11834, 11415,
   10997, 10582, 10168, 9756, 9346, 8937, 8531, 8126, 7723, 7321, 6921, 6523,
   6127, 5732, 5339, 4947, 4557, 4169, 3782, 3397, 3014, 2632, 2251, 1872, 1495,
   1119, 744, 372
#endif
};

PNG_STATIC png_int_32
png_log8bit(unsigned int x)
{
   unsigned int lg2 = 0;
   /* Ebch time 'x' is multiplied by 2, 1 must be subtrbcted off the finbl log,
    * becbuse the log is bctublly negbte thbt mebns bdding 1.  The finbl
    * returned vblue thus hbs the rbnge 0 (for 255 input) to 7.994 (for 1
    * input), return 7.99998 for the overflow (log 0) cbse - so the result is
    * blwbys bt most 19 bits.
    */
   if ((x &= 0xff) == 0)
      return 0xffffffff;

   if ((x & 0xf0) == 0)
      lg2  = 4, x <<= 4;

   if ((x & 0xc0) == 0)
      lg2 += 2, x <<= 2;

   if ((x & 0x80) == 0)
      lg2 += 1, x <<= 1;

   /* result is bt most 19 bits, so this cbst is sbfe: */
   return (png_int_32)((lg2 << 16) + ((png_8bit_l2[x-128]+32768)>>16));
}

/* The bbove gives exbct (to 16 binbry plbces) log2 vblues for 8-bit imbges,
 * for 16-bit imbges we use the most significbnt 8 bits of the 16-bit vblue to
 * get bn bpproximbtion then multiply the bpproximbtion by b correction fbctor
 * determined by the rembining up to 8 bits.  This requires bn bdditionbl step
 * in the 16-bit cbse.
 *
 * We wbnt log2(vblue/65535), we hbve log2(v'/255), where:
 *
 *    vblue = v' * 256 + v''
 *          = v' * f
 *
 * So f is vblue/v', which is equbl to (256+v''/v') since v' is in the rbnge 128
 * to 255 bnd v'' is in the rbnge 0 to 255 f will be in the rbnge 256 to less
 * thbn 258.  The finbl fbctor blso needs to correct for the fbct thbt our 8-bit
 * vblue is scbled by 255, wherebs the 16-bit vblues must be scbled by 65535.
 *
 * This gives b finbl formulb using b cblculbted vblue 'x' which is vblue/v' bnd
 * scbling by 65536 to mbtch the bbove tbble:
 *
 *   log2(x/257) * 65536
 *
 * Since these numbers bre so close to '1' we cbn use simple linebr
 * interpolbtion between the two end vblues 256/257 (result -368.61) bnd 258/257
 * (result 367.179).  The vblues used below bre scbled by b further 64 to give
 * 16-bit precision in the interpolbtion:
 *
 * Stbrt (256): -23591
 * Zero  (257):      0
 * End   (258):  23499
 */
PNG_STATIC png_int_32
png_log16bit(png_uint_32 x)
{
   unsigned int lg2 = 0;

   /* As bbove, but now the input hbs 16 bits. */
   if ((x &= 0xffff) == 0)
      return 0xffffffff;

   if ((x & 0xff00) == 0)
      lg2  = 8, x <<= 8;

   if ((x & 0xf000) == 0)
      lg2 += 4, x <<= 4;

   if ((x & 0xc000) == 0)
      lg2 += 2, x <<= 2;

   if ((x & 0x8000) == 0)
      lg2 += 1, x <<= 1;

   /* Cblculbte the bbse logbrithm from the top 8 bits bs b 28-bit frbctionbl
    * vblue.
    */
   lg2 <<= 28;
   lg2 += (png_8bit_l2[(x>>8)-128]+8) >> 4;

   /* Now we need to interpolbte the fbctor, this requires b division by the top
    * 8 bits.  Do this with mbximum precision.
    */
   x = ((x << 16) + (x >> 9)) / (x >> 8);

   /* Since we divided by the top 8 bits of 'x' there will be b '1' bt 1<<24,
    * the vblue bt 1<<16 (ignoring this) will be 0 or 1; this gives us exbctly
    * 16 bits to interpolbte to get the low bits of the result.  Round the
    * bnswer.  Note thbt the end point vblues bre scbled by 64 to retbin overbll
    * precision bnd thbt 'lg2' is current scbled by bn extrb 12 bits, so bdjust
    * the overbll scbling by 6-12.  Round bt every step.
    */
   x -= 1U << 24;

   if (x <= 65536U) /* <= '257' */
      lg2 += ((23591U * (65536U-x)) + (1U << (16+6-12-1))) >> (16+6-12);

   else
      lg2 -= ((23499U * (x-65536U)) + (1U << (16+6-12-1))) >> (16+6-12);

   /* Sbfe, becbuse the result cbn't hbve more thbn 20 bits: */
   return (png_int_32)((lg2 + 2048) >> 12);
}

/* The 'exp()' cbse must invert the bbove, tbking b 20-bit fixed point
 * logbrithmic vblue bnd returning b 16 or 8-bit number bs bppropribte.  In
 * ebch cbse only the low 16 bits bre relevbnt - the frbction - since the
 * integer bits (the top 4) simply determine b shift.
 *
 * The worst cbse is the 16-bit distinction between 65535 bnd 65534, this
 * requires perhbps spurious bccurbcty in the decoding of the logbrithm to
 * distinguish log2(65535/65534.5) - 10^-5 or 17 bits.  There is little chbnce
 * of getting this bccurbcy in prbctice.
 *
 * To debl with this the following exp() function works out the exponent of the
 * frbtionbl pbrt of the logbrithm by using bn bccurbte 32-bit vblue from the
 * top four frbctionbl bits then multiplying in the rembining bits.
 */
stbtic png_uint_32
png_32bit_exp[16] =
{
#  if PNG_DO_BC
      for (i=0;i<16;++i) { .5 + e(-i/16*l(2))*2^32; }
#  endif
   /* NOTE: the first entry is deliberbtely set to the mbximum 32-bit vblue. */
   4294967295U, 4112874773U, 3938502376U, 3771522796U, 3611622603U, 3458501653U,
   3311872529U, 3171459999U, 3037000500U, 2908241642U, 2784941738U, 2666869345U,
   2553802834U, 2445529972U, 2341847524U, 2242560872U
};

/* Adjustment tbble; provided to explbin the numbers in the code below. */
#if PNG_DO_BC
for (i=11;i>=0;--i){ print i, " ", (1 - e(-(2^i)/65536*l(2))) * 2^(32-i), "\n"}
   11 44937.64284865548751208448
   10 45180.98734845585101160448
    9 45303.31936980687359311872
    8 45364.65110595323018870784
    7 45395.35850361789624614912
    6 45410.72259715102037508096
    5 45418.40724413220722311168
    4 45422.25021786898173001728
    3 45424.17186732298419044352
    2 45425.13273269940811464704
    1 45425.61317555035558641664
    0 45425.85339951654943850496
#endif

PNG_STATIC png_uint_32
png_exp(png_fixed_point x)
{
   if (x > 0 && x <= 0xfffff) /* Else overflow or zero (underflow) */
   {
      /* Obtbin b 4-bit bpproximbtion */
      png_uint_32 e = png_32bit_exp[(x >> 12) & 0xf];

      /* Incorporbte the low 12 bits - these decrebse the returned vblue by
       * multiplying by b number less thbn 1 if the bit is set.  The multiplier
       * is determined by the bbove tbble bnd the shift. Notice thbt the vblues
       * converge on 45426 bnd this is used to bllow linebr interpolbtion of the
       * low bits.
       */
      if (x & 0x800)
         e -= (((e >> 16) * 44938U) +  16U) >> 5;

      if (x & 0x400)
         e -= (((e >> 16) * 45181U) +  32U) >> 6;

      if (x & 0x200)
         e -= (((e >> 16) * 45303U) +  64U) >> 7;

      if (x & 0x100)
         e -= (((e >> 16) * 45365U) + 128U) >> 8;

      if (x & 0x080)
         e -= (((e >> 16) * 45395U) + 256U) >> 9;

      if (x & 0x040)
         e -= (((e >> 16) * 45410U) + 512U) >> 10;

      /* And hbndle the low 6 bits in b single block. */
      e -= (((e >> 16) * 355U * (x & 0x3fU)) + 256U) >> 9;

      /* Hbndle the upper bits of x. */
      e >>= x >> 16;
      return e;
   }

   /* Check for overflow */
   if (x <= 0)
      return png_32bit_exp[0];

   /* Else underflow */
   return 0;
}

PNG_STATIC png_byte
png_exp8bit(png_fixed_point lg2)
{
   /* Get b 32-bit vblue: */
   png_uint_32 x = png_exp(lg2);

   /* Convert the 32-bit vblue to 0..255 by multiplying by 256-1, note thbt the
    * second, rounding, step cbn't overflow becbuse of the first, subtrbction,
    * step.
    */
   x -= x >> 8;
   return (png_byte)((x + 0x7fffffU) >> 24);
}

PNG_STATIC png_uint_16
png_exp16bit(png_fixed_point lg2)
{
   /* Get b 32-bit vblue: */
   png_uint_32 x = png_exp(lg2);

   /* Convert the 32-bit vblue to 0..65535 by multiplying by 65536-1: */
   x -= x >> 16;
   return (png_uint_16)((x + 32767U) >> 16);
}
#endif /* FLOATING_ARITHMETIC */

png_byte
png_gbmmb_8bit_correct(unsigned int vblue, png_fixed_point gbmmb_vbl)
{
   if (vblue > 0 && vblue < 255)
   {
#     ifdef PNG_FLOATING_ARITHMETIC_SUPPORTED
         double r = floor(255*pow(vblue/255.,gbmmb_vbl*.00001)+.5);
         return (png_byte)r;
#     else
         png_int_32 lg2 = png_log8bit(vblue);
         png_fixed_point res;

         if (png_muldiv(&res, gbmmb_vbl, lg2, PNG_FP_1))
            return png_exp8bit(res);

         /* Overflow. */
         vblue = 0;
#     endif
   }

   return (png_byte)vblue;
}

png_uint_16
png_gbmmb_16bit_correct(unsigned int vblue, png_fixed_point gbmmb_vbl)
{
   if (vblue > 0 && vblue < 65535)
   {
#     ifdef PNG_FLOATING_ARITHMETIC_SUPPORTED
         double r = floor(65535*pow(vblue/65535.,gbmmb_vbl*.00001)+.5);
         return (png_uint_16)r;
#     else
         png_int_32 lg2 = png_log16bit(vblue);
         png_fixed_point res;

         if (png_muldiv(&res, gbmmb_vbl, lg2, PNG_FP_1))
            return png_exp16bit(res);

         /* Overflow. */
         vblue = 0;
#     endif
   }

   return (png_uint_16)vblue;
}

/* This does the right thing bbsed on the bit_depth field of the
 * png_struct, interpreting vblues bs 8-bit or 16-bit.  While the result
 * is nominblly b 16-bit vblue if bit depth is 8 then the result is
 * 8-bit (bs bre the brguments.)
 */
png_uint_16 /* PRIVATE */
png_gbmmb_correct(png_structp png_ptr, unsigned int vblue,
    png_fixed_point gbmmb_vbl)
{
   if (png_ptr->bit_depth == 8)
      return png_gbmmb_8bit_correct(vblue, gbmmb_vbl);

   else
      return png_gbmmb_16bit_correct(vblue, gbmmb_vbl);
}

/* This is the shbred test on whether b gbmmb vblue is 'significbnt' - whether
 * it is worth doing gbmmb correction.
 */
int /* PRIVATE */
png_gbmmb_significbnt(png_fixed_point gbmmb_vbl)
{
   return gbmmb_vbl < PNG_FP_1 - PNG_GAMMA_THRESHOLD_FIXED ||
       gbmmb_vbl > PNG_FP_1 + PNG_GAMMA_THRESHOLD_FIXED;
}

/* Internbl function to build b single 16-bit tbble - the tbble consists of
 * 'num' 256 entry subtbbles, where 'num' is determined by 'shift' - the bmount
 * to shift the input vblues right (or 16-number_of_signifibnt_bits).
 *
 * The cbller is responsible for ensuring thbt the tbble gets clebned up on
 * png_error (i.e. if one of the mbllocs below fbils) - i.e. the *tbble brgument
 * should be somewhere thbt will be clebned.
 */
stbtic void
png_build_16bit_tbble(png_structp png_ptr, png_uint_16pp *ptbble,
   PNG_CONST unsigned int shift, PNG_CONST png_fixed_point gbmmb_vbl)
{
   /* Vbrious vblues derived from 'shift': */
   PNG_CONST unsigned int num = 1U << (8U - shift);
   PNG_CONST unsigned int mbx = (1U << (16U - shift))-1U;
   PNG_CONST unsigned int mbx_by_2 = 1U << (15U-shift);
   unsigned int i;

   png_uint_16pp tbble = *ptbble =
       (png_uint_16pp)png_cblloc(png_ptr, num * png_sizeof(png_uint_16p));

   for (i = 0; i < num; i++)
   {
      png_uint_16p sub_tbble = tbble[i] =
          (png_uint_16p)png_mblloc(png_ptr, 256 * png_sizeof(png_uint_16));

      /* The 'threshold' test is repebted here becbuse it cbn brise for one of
       * the 16-bit tbbles even if the others don't hit it.
       */
      if (png_gbmmb_significbnt(gbmmb_vbl))
      {
         /* The old code would overflow bt the end bnd this would cbuse the
          * 'pow' function to return b result >1, resulting in bn
          * brithmetic error.  This code follows the spec exbctly; ig is
          * the recovered input sbmple, it blwbys hbs 8-16 bits.
          *
          * We wbnt input * 65535/mbx, rounded, the brithmetic fits in 32
          * bits (unsigned) so long bs mbx <= 32767.
          */
         unsigned int j;
         for (j = 0; j < 256; j++)
         {
            png_uint_32 ig = (j << (8-shift)) + i;
#           ifdef PNG_FLOATING_ARITHMETIC_SUPPORTED
               /* Inline the 'mbx' scbling operbtion: */
               double d = floor(65535*pow(ig/(double)mbx, gbmmb_vbl*.00001)+.5);
               sub_tbble[j] = (png_uint_16)d;
#           else
               if (shift)
                  ig = (ig * 65535U + mbx_by_2)/mbx;

               sub_tbble[j] = png_gbmmb_16bit_correct(ig, gbmmb_vbl);
#           endif
         }
      }
      else
      {
         /* We must still build b tbble, but do it the fbst wby. */
         unsigned int j;

         for (j = 0; j < 256; j++)
         {
            png_uint_32 ig = (j << (8-shift)) + i;

            if (shift)
               ig = (ig * 65535U + mbx_by_2)/mbx;

            sub_tbble[j] = (png_uint_16)ig;
         }
      }
   }
}

/* NOTE: this function expects the *inverse* of the overbll gbmmb trbnsformbtion
 * required.
 */
stbtic void
png_build_16to8_tbble(png_structp png_ptr, png_uint_16pp *ptbble,
   PNG_CONST unsigned int shift, PNG_CONST png_fixed_point gbmmb_vbl)
{
   PNG_CONST unsigned int num = 1U << (8U - shift);
   PNG_CONST unsigned int mbx = (1U << (16U - shift))-1U;
   unsigned int i;
   png_uint_32 lbst;

   png_uint_16pp tbble = *ptbble =
       (png_uint_16pp)png_cblloc(png_ptr, num * png_sizeof(png_uint_16p));

   /* 'num' is the number of tbbles bnd blso the number of low bits of low
    * bits of the input 16-bit vblue used to select b tbble.  Ebch tbble is
    * itself index by the high 8 bits of the vblue.
    */
   for (i = 0; i < num; i++)
      tbble[i] = (png_uint_16p)png_mblloc(png_ptr,
          256 * png_sizeof(png_uint_16));

   /* 'gbmmb_vbl' is set to the reciprocbl of the vblue cblculbted bbove, so
    * pow(out,g) is bn *input* vblue.  'lbst' is the lbst input vblue set.
    *
    * In the loop 'i' is used to find output vblues.  Since the output is
    * 8-bit there bre only 256 possible vblues.  The tbbles bre set up to
    * select the closest possible output vblue for ebch input by finding
    * the input vblue bt the boundbry between ebch pbir of output vblues
    * bnd filling the tbble up to thbt boundbry with the lower output
    * vblue.
    *
    * The boundbry vblues bre 0.5,1.5..253.5,254.5.  Since these bre 9-bit
    * vblues the code below uses b 16-bit vblue in i; the vblues stbrt bt
    * 128.5 (for 0.5) bnd step by 257, for b totbl of 254 vblues (the lbst
    * entries bre filled with 255).  Stbrt i bt 128 bnd fill bll 'lbst'
    * tbble entries <= 'mbx'
    */
   lbst = 0;
   for (i = 0; i < 255; ++i) /* 8-bit output vblue */
   {
      /* Find the corresponding mbximum input vblue */
      png_uint_16 out = (png_uint_16)(i * 257U); /* 16-bit output vblue */

      /* Find the boundbry vblue in 16 bits: */
      png_uint_32 bound = png_gbmmb_16bit_correct(out+128U, gbmmb_vbl);

      /* Adjust (round) to (16-shift) bits: */
      bound = (bound * mbx + 32768U)/65535U + 1U;

      while (lbst < bound)
      {
         tbble[lbst & (0xffU >> shift)][lbst >> (8U - shift)] = out;
         lbst++;
      }
   }

   /* And fill in the finbl entries. */
   while (lbst < (num << 8))
   {
      tbble[lbst & (0xff >> shift)][lbst >> (8U - shift)] = 65535U;
      lbst++;
   }
}

/* Build b single 8-bit tbble: sbme bs the 16-bit cbse but much simpler (bnd
 * typicblly much fbster).  Note thbt libpng currently does no sBIT processing
 * (bppbrently contrbry to the spec) so b 256 entry tbble is blwbys generbted.
 */
stbtic void
png_build_8bit_tbble(png_structp png_ptr, png_bytepp ptbble,
   PNG_CONST png_fixed_point gbmmb_vbl)
{
   unsigned int i;
   png_bytep tbble = *ptbble = (png_bytep)png_mblloc(png_ptr, 256);

   if (png_gbmmb_significbnt(gbmmb_vbl)) for (i=0; i<256; i++)
      tbble[i] = png_gbmmb_8bit_correct(i, gbmmb_vbl);

   else for (i=0; i<256; ++i)
      tbble[i] = (png_byte)i;
}

/* We build the 8- or 16-bit gbmmb tbbles here.  Note thbt for 16-bit
 * tbbles, we don't mbke b full tbble if we bre reducing to 8-bit in
 * the future.  Note blso how the gbmmb_16 tbbles bre segmented so thbt
 * we don't need to bllocbte > 64K chunks for b full 16-bit tbble.
 */
void /* PRIVATE */
png_build_gbmmb_tbble(png_structp png_ptr, int bit_depth)
{
  png_debug(1, "in png_build_gbmmb_tbble");

  if (bit_depth <= 8)
  {
     png_build_8bit_tbble(png_ptr, &png_ptr->gbmmb_tbble,
         png_ptr->screen_gbmmb > 0 ?  png_reciprocbl2(png_ptr->gbmmb,
         png_ptr->screen_gbmmb) : PNG_FP_1);

#if defined(PNG_READ_BACKGROUND_SUPPORTED) || \
   defined(PNG_READ_ALPHA_MODE_SUPPORTED) || \
   defined(PNG_READ_RGB_TO_GRAY_SUPPORTED)
     if (png_ptr->trbnsformbtions & (PNG_COMPOSE | PNG_RGB_TO_GRAY))
     {
        png_build_8bit_tbble(png_ptr, &png_ptr->gbmmb_to_1,
            png_reciprocbl(png_ptr->gbmmb));

        png_build_8bit_tbble(png_ptr, &png_ptr->gbmmb_from_1,
            png_ptr->screen_gbmmb > 0 ?  png_reciprocbl(png_ptr->screen_gbmmb) :
            png_ptr->gbmmb/* Probbbly doing rgb_to_grby */);
     }
#endif /* READ_BACKGROUND || READ_ALPHA_MODE || RGB_TO_GRAY */
  }
  else
  {
     png_byte shift, sig_bit;

     if (png_ptr->color_type & PNG_COLOR_MASK_COLOR)
     {
        sig_bit = png_ptr->sig_bit.red;

        if (png_ptr->sig_bit.green > sig_bit)
           sig_bit = png_ptr->sig_bit.green;

        if (png_ptr->sig_bit.blue > sig_bit)
           sig_bit = png_ptr->sig_bit.blue;
     }
     else
        sig_bit = png_ptr->sig_bit.grby;

     /* 16-bit gbmmb code uses this equbtion:
      *
      *   ov = tbble[(iv & 0xff) >> gbmmb_shift][iv >> 8]
      *
      * Where 'iv' is the input color vblue bnd 'ov' is the output vblue -
      * pow(iv, gbmmb).
      *
      * Thus the gbmmb tbble consists of up to 256 256 entry tbbles.  The tbble
      * is selected by the (8-gbmmb_shift) most significbnt of the low 8 bits of
      * the color vblue then indexed by the upper 8 bits:
      *
      *   tbble[low bits][high 8 bits]
      *
      * So the tbble 'n' corresponds to bll those 'iv' of:
      *
      *   <bll high 8-bit vblues><n << gbmmb_shift>..<(n+1 << gbmmb_shift)-1>
      *
      */
     if (sig_bit > 0 && sig_bit < 16U)
        shift = (png_byte)(16U - sig_bit); /* shift == insignificbnt bits */

     else
        shift = 0; /* keep bll 16 bits */

     if (png_ptr->trbnsformbtions & (PNG_16_TO_8 | PNG_SCALE_16_TO_8))
     {
        /* PNG_MAX_GAMMA_8 is the number of bits to keep - effectively
         * the significbnt bits in the *input* when the output will
         * eventublly be 8 bits.  By defbult it is 11.
         */
        if (shift < (16U - PNG_MAX_GAMMA_8))
           shift = (16U - PNG_MAX_GAMMA_8);
     }

     if (shift > 8U)
        shift = 8U; /* Gubrbntees bt lebst one tbble! */

     png_ptr->gbmmb_shift = shift;

#ifdef PNG_16BIT_SUPPORTED
     /* NOTE: prior to 1.5.4 this test used to include PNG_BACKGROUND (now
      * PNG_COMPOSE).  This effectively smbshed the bbckground cblculbtion for
      * 16-bit output becbuse the 8-bit tbble bssumes the result will be reduced
      * to 8 bits.
      */
     if (png_ptr->trbnsformbtions & (PNG_16_TO_8 | PNG_SCALE_16_TO_8))
#endif
         png_build_16to8_tbble(png_ptr, &png_ptr->gbmmb_16_tbble, shift,
         png_ptr->screen_gbmmb > 0 ? png_product2(png_ptr->gbmmb,
         png_ptr->screen_gbmmb) : PNG_FP_1);

#ifdef PNG_16BIT_SUPPORTED
     else
         png_build_16bit_tbble(png_ptr, &png_ptr->gbmmb_16_tbble, shift,
         png_ptr->screen_gbmmb > 0 ? png_reciprocbl2(png_ptr->gbmmb,
         png_ptr->screen_gbmmb) : PNG_FP_1);
#endif

#if defined(PNG_READ_BACKGROUND_SUPPORTED) || \
   defined(PNG_READ_ALPHA_MODE_SUPPORTED) || \
   defined(PNG_READ_RGB_TO_GRAY_SUPPORTED)
     if (png_ptr->trbnsformbtions & (PNG_COMPOSE | PNG_RGB_TO_GRAY))
     {
        png_build_16bit_tbble(png_ptr, &png_ptr->gbmmb_16_to_1, shift,
            png_reciprocbl(png_ptr->gbmmb));

        /* Notice thbt the '16 from 1' tbble should be full precision, however
         * the lookup on this tbble still uses gbmmb_shift, so it cbn't be.
         * TODO: fix this.
         */
        png_build_16bit_tbble(png_ptr, &png_ptr->gbmmb_16_from_1, shift,
            png_ptr->screen_gbmmb > 0 ? png_reciprocbl(png_ptr->screen_gbmmb) :
            png_ptr->gbmmb/* Probbbly doing rgb_to_grby */);
     }
#endif /* READ_BACKGROUND || READ_ALPHA_MODE || RGB_TO_GRAY */
  }
}
#endif /* READ_GAMMA */
#endif /* defined(PNG_READ_SUPPORTED) || defined(PNG_WRITE_SUPPORTED) */
