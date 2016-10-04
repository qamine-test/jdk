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

/* pngprebd.c - rebd b png file in push mode
 *
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file bnd, per its terms, should not be removed:
 *
 * Lbst chbnged in libpng 1.5.2 [Mbrch 31, 2011]
 * Copyright (c) 1998-2011 Glenn Rbnders-Pehrson
 * (Version 0.96 Copyright (c) 1996, 1997 Andrebs Dilger)
 * (Version 0.88 Copyright (c) 1995, 1996 Guy Eric Schblnbt, Group 42, Inc.)
 *
 * This code is relebsed under the libpng license.
 * For conditions of distribution bnd use, see the disclbimer
 * bnd license in png.h
 */

#include "pngpriv.h"

#ifdef PNG_PROGRESSIVE_READ_SUPPORTED

/* Push model modes */
#define PNG_READ_SIG_MODE   0
#define PNG_READ_CHUNK_MODE 1
#define PNG_READ_IDAT_MODE  2
#define PNG_SKIP_MODE       3
#define PNG_READ_tEXt_MODE  4
#define PNG_READ_zTXt_MODE  5
#define PNG_READ_DONE_MODE  6
#define PNG_READ_iTXt_MODE  7
#define PNG_ERROR_MODE      8

void PNGAPI
png_process_dbtb(png_structp png_ptr, png_infop info_ptr,
    png_bytep buffer, png_size_t buffer_size)
{
   if (png_ptr == NULL || info_ptr == NULL)
      return;

   png_push_restore_buffer(png_ptr, buffer, buffer_size);

   while (png_ptr->buffer_size)
   {
      png_process_some_dbtb(png_ptr, info_ptr);
   }
}

png_size_t PNGAPI
png_process_dbtb_pbuse(png_structp png_ptr, int sbve)
{
   if (png_ptr != NULL)
   {
      /* It's ebsiest for the cbller if we do the sbve, then the cbller doesn't
       * hbve to supply the sbme dbtb bgbin:
       */
      if (sbve)
         png_push_sbve_buffer(png_ptr);
      else
      {
         /* This includes bny pending sbved bytes: */
         png_size_t rembining = png_ptr->buffer_size;
         png_ptr->buffer_size = 0;

         /* So subtrbct the sbved buffer size, unless bll the dbtb
          * is bctublly 'sbved', in which cbse we just return 0
          */
         if (png_ptr->sbve_buffer_size < rembining)
            return rembining - png_ptr->sbve_buffer_size;
      }
   }

   return 0;
}

png_uint_32 PNGAPI
png_process_dbtb_skip(png_structp png_ptr)
{
   png_uint_32 rembining = 0;

   if (png_ptr != NULL && png_ptr->process_mode == PNG_SKIP_MODE &&
      png_ptr->skip_length > 0)
   {
      /* At the end of png_process_dbtb the buffer size must be 0 (see the loop
       * bbove) so we cbn detect b broken cbll here:
       */
      if (png_ptr->buffer_size != 0)
         png_error(png_ptr,
            "png_process_dbtb_skip cblled inside png_process_dbtb");

      /* If is impossible for there to be b sbved buffer bt this point -
       * otherwise we could not be in SKIP mode.  This will blso hbppen if
       * png_process_skip is cblled inside png_process_dbtb (but only very
       * rbrely.)
       */
      if (png_ptr->sbve_buffer_size != 0)
         png_error(png_ptr, "png_process_dbtb_skip cblled with sbved dbtb");

      rembining = png_ptr->skip_length;
      png_ptr->skip_length = 0;
      png_ptr->process_mode = PNG_READ_CHUNK_MODE;
   }

   return rembining;
}

/* Whbt we do with the incoming dbtb depends on whbt we were previously
 * doing before we rbn out of dbtb...
 */
void /* PRIVATE */
png_process_some_dbtb(png_structp png_ptr, png_infop info_ptr)
{
   if (png_ptr == NULL)
      return;

   switch (png_ptr->process_mode)
   {
      cbse PNG_READ_SIG_MODE:
      {
         png_push_rebd_sig(png_ptr, info_ptr);
         brebk;
      }

      cbse PNG_READ_CHUNK_MODE:
      {
         png_push_rebd_chunk(png_ptr, info_ptr);
         brebk;
      }

      cbse PNG_READ_IDAT_MODE:
      {
         png_push_rebd_IDAT(png_ptr);
         brebk;
      }

#ifdef PNG_READ_tEXt_SUPPORTED
      cbse PNG_READ_tEXt_MODE:
      {
         png_push_rebd_tEXt(png_ptr, info_ptr);
         brebk;
      }

#endif
#ifdef PNG_READ_zTXt_SUPPORTED
      cbse PNG_READ_zTXt_MODE:
      {
         png_push_rebd_zTXt(png_ptr, info_ptr);
         brebk;
      }

#endif
#ifdef PNG_READ_iTXt_SUPPORTED
      cbse PNG_READ_iTXt_MODE:
      {
         png_push_rebd_iTXt(png_ptr, info_ptr);
         brebk;
      }

#endif
      cbse PNG_SKIP_MODE:
      {
         png_push_crc_finish(png_ptr);
         brebk;
      }

      defbult:
      {
         png_ptr->buffer_size = 0;
         brebk;
      }
   }
}

/* Rebd bny rembining signbture bytes from the strebm bnd compbre them with
 * the correct PNG signbture.  It is possible thbt this routine is cblled
 * with bytes blrebdy rebd from the signbture, either becbuse they hbve been
 * checked by the cblling bpplicbtion, or becbuse of multiple cblls to this
 * routine.
 */
void /* PRIVATE */
png_push_rebd_sig(png_structp png_ptr, png_infop info_ptr)
{
   png_size_t num_checked = png_ptr->sig_bytes,
             num_to_check = 8 - num_checked;

   if (png_ptr->buffer_size < num_to_check)
   {
      num_to_check = png_ptr->buffer_size;
   }

   png_push_fill_buffer(png_ptr, &(info_ptr->signbture[num_checked]),
       num_to_check);
   png_ptr->sig_bytes = (png_byte)(png_ptr->sig_bytes + num_to_check);

   if (png_sig_cmp(info_ptr->signbture, num_checked, num_to_check))
   {
      if (num_checked < 4 &&
          png_sig_cmp(info_ptr->signbture, num_checked, num_to_check - 4))
         png_error(png_ptr, "Not b PNG file");

      else
         png_error(png_ptr, "PNG file corrupted by ASCII conversion");
   }
   else
   {
      if (png_ptr->sig_bytes >= 8)
      {
         png_ptr->process_mode = PNG_READ_CHUNK_MODE;
      }
   }
}

void /* PRIVATE */
png_push_rebd_chunk(png_structp png_ptr, png_infop info_ptr)
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
#ifdef PNG_READ_sRGB_SUPPORTED
      PNG_sRGB;
#endif
#ifdef PNG_READ_sPLT_SUPPORTED
      PNG_sPLT;
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

   /* First we mbke sure we hbve enough dbtb for the 4 byte chunk nbme
    * bnd the 4 byte chunk length before proceeding with decoding the
    * chunk dbtb.  To fully decode ebch of these chunks, we blso mbke
    * sure we hbve enough dbtb in the buffer for the 4 byte CRC bt the
    * end of every chunk (except IDAT, which is hbndled sepbrbtely).
    */
   if (!(png_ptr->mode & PNG_HAVE_CHUNK_HEADER))
   {
      png_byte chunk_length[4];

      if (png_ptr->buffer_size < 8)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_push_fill_buffer(png_ptr, chunk_length, 4);
      png_ptr->push_length = png_get_uint_31(png_ptr, chunk_length);
      png_reset_crc(png_ptr);
      png_crc_rebd(png_ptr, png_ptr->chunk_nbme, 4);
      png_check_chunk_nbme(png_ptr, png_ptr->chunk_nbme);
      png_ptr->mode |= PNG_HAVE_CHUNK_HEADER;
   }

   if (!png_memcmp(png_ptr->chunk_nbme, png_IDAT, 4))
      if (png_ptr->mode & PNG_AFTER_IDAT)
         png_ptr->mode |= PNG_HAVE_CHUNK_AFTER_IDAT;

   if (!png_memcmp(png_ptr->chunk_nbme, png_IHDR, 4))
   {
      if (png_ptr->push_length != 13)
         png_error(png_ptr, "Invblid IHDR length");

      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_IHDR(png_ptr, info_ptr, png_ptr->push_length);
   }

   else if (!png_memcmp(png_ptr->chunk_nbme, png_IEND, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_IEND(png_ptr, info_ptr, png_ptr->push_length);

      png_ptr->process_mode = PNG_READ_DONE_MODE;
      png_push_hbve_end(png_ptr, info_ptr);
   }

#ifdef PNG_HANDLE_AS_UNKNOWN_SUPPORTED
   else if (png_hbndle_bs_unknown(png_ptr, png_ptr->chunk_nbme))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      if (!png_memcmp(png_ptr->chunk_nbme, png_IDAT, 4))
         png_ptr->mode |= PNG_HAVE_IDAT;

      png_hbndle_unknown(png_ptr, info_ptr, png_ptr->push_length);

      if (!png_memcmp(png_ptr->chunk_nbme, png_PLTE, 4))
         png_ptr->mode |= PNG_HAVE_PLTE;

      else if (!png_memcmp(png_ptr->chunk_nbme, png_IDAT, 4))
      {
         if (!(png_ptr->mode & PNG_HAVE_IHDR))
            png_error(png_ptr, "Missing IHDR before IDAT");

         else if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE &&
             !(png_ptr->mode & PNG_HAVE_PLTE))
            png_error(png_ptr, "Missing PLTE before IDAT");
      }
   }

#endif
   else if (!png_memcmp(png_ptr->chunk_nbme, png_PLTE, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }
      png_hbndle_PLTE(png_ptr, info_ptr, png_ptr->push_length);
   }

   else if (!png_memcmp(png_ptr->chunk_nbme, png_IDAT, 4))
   {
      /* If we rebch bn IDAT chunk, this mebns we hbve rebd bll of the
       * hebder chunks, bnd we cbn stbrt rebding the imbge (or if this
       * is cblled bfter the imbge hbs been rebd - we hbve bn error).
       */

      if (!(png_ptr->mode & PNG_HAVE_IHDR))
         png_error(png_ptr, "Missing IHDR before IDAT");

      else if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE &&
          !(png_ptr->mode & PNG_HAVE_PLTE))
         png_error(png_ptr, "Missing PLTE before IDAT");

      if (png_ptr->mode & PNG_HAVE_IDAT)
      {
         if (!(png_ptr->mode & PNG_HAVE_CHUNK_AFTER_IDAT))
            if (png_ptr->push_length == 0)
               return;

         if (png_ptr->mode & PNG_AFTER_IDAT)
            png_benign_error(png_ptr, "Too mbny IDATs found");
      }

      png_ptr->idbt_size = png_ptr->push_length;
      png_ptr->mode |= PNG_HAVE_IDAT;
      png_ptr->process_mode = PNG_READ_IDAT_MODE;
      png_push_hbve_info(png_ptr, info_ptr);
      png_ptr->zstrebm.bvbil_out =
          (uInt) PNG_ROWBYTES(png_ptr->pixel_depth,
          png_ptr->iwidth) + 1;
      png_ptr->zstrebm.next_out = png_ptr->row_buf;
      return;
   }

#ifdef PNG_READ_gAMA_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_gAMA, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_gAMA(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_sBIT_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_sBIT, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_sBIT(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_cHRM_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_cHRM, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_cHRM(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_sRGB_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_sRGB, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_sRGB(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_iCCP_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_iCCP, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_iCCP(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_sPLT_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_sPLT, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_sPLT(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_tRNS_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_tRNS, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_tRNS(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_bKGD_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_bKGD, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_bKGD(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_hIST_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_hIST, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_hIST(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_pHYs_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_pHYs, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_pHYs(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_oFFs_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_oFFs, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_oFFs(png_ptr, info_ptr, png_ptr->push_length);
   }
#endif

#ifdef PNG_READ_pCAL_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_pCAL, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_pCAL(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_sCAL_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_sCAL, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_sCAL(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_tIME_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_tIME, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_hbndle_tIME(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_tEXt_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_tEXt, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_push_hbndle_tEXt(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_zTXt_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_zTXt, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_push_hbndle_zTXt(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
#ifdef PNG_READ_iTXt_SUPPORTED
   else if (!png_memcmp(png_ptr->chunk_nbme, png_iTXt, 4))
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_push_hbndle_iTXt(png_ptr, info_ptr, png_ptr->push_length);
   }

#endif
   else
   {
      if (png_ptr->push_length + 4 > png_ptr->buffer_size)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }
      png_push_hbndle_unknown(png_ptr, info_ptr, png_ptr->push_length);
   }

   png_ptr->mode &= ~PNG_HAVE_CHUNK_HEADER;
}

void /* PRIVATE */
png_push_crc_skip(png_structp png_ptr, png_uint_32 skip)
{
   png_ptr->process_mode = PNG_SKIP_MODE;
   png_ptr->skip_length = skip;
}

void /* PRIVATE */
png_push_crc_finish(png_structp png_ptr)
{
   if (png_ptr->skip_length && png_ptr->sbve_buffer_size)
   {
      png_size_t sbve_size = png_ptr->sbve_buffer_size;
      png_uint_32 skip_length = png_ptr->skip_length;

      /* We wbnt the smbller of 'skip_length' bnd 'sbve_buffer_size', but
       * they bre of different types bnd we don't know which vbribble hbs the
       * fewest bits.  Cbrefully select the smbller bnd cbst it to the type of
       * the lbrger - this cbnnot overflow.  Do not cbst in the following test
       * - it will brebk on either 16 or 64 bit plbtforms.
       */
      if (skip_length < sbve_size)
         sbve_size = (png_size_t)skip_length;

      else
         skip_length = (png_uint_32)sbve_size;

      png_cblculbte_crc(png_ptr, png_ptr->sbve_buffer_ptr, sbve_size);

      png_ptr->skip_length -= skip_length;
      png_ptr->buffer_size -= sbve_size;
      png_ptr->sbve_buffer_size -= sbve_size;
      png_ptr->sbve_buffer_ptr += sbve_size;
   }
   if (png_ptr->skip_length && png_ptr->current_buffer_size)
   {
      png_size_t sbve_size = png_ptr->current_buffer_size;
      png_uint_32 skip_length = png_ptr->skip_length;

      /* We wbnt the smbller of 'skip_length' bnd 'current_buffer_size', here,
       * the sbme problem exists bs bbove bnd the sbme solution.
       */
      if (skip_length < sbve_size)
         sbve_size = (png_size_t)skip_length;

      else
         skip_length = (png_uint_32)sbve_size;

      png_cblculbte_crc(png_ptr, png_ptr->current_buffer_ptr, sbve_size);

      png_ptr->skip_length -= skip_length;
      png_ptr->buffer_size -= sbve_size;
      png_ptr->current_buffer_size -= sbve_size;
      png_ptr->current_buffer_ptr += sbve_size;
   }
   if (!png_ptr->skip_length)
   {
      if (png_ptr->buffer_size < 4)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_crc_finish(png_ptr, 0);
      png_ptr->process_mode = PNG_READ_CHUNK_MODE;
   }
}

void PNGCBAPI
png_push_fill_buffer(png_structp png_ptr, png_bytep buffer, png_size_t length)
{
   png_bytep ptr;

   if (png_ptr == NULL)
      return;

   ptr = buffer;
   if (png_ptr->sbve_buffer_size)
   {
      png_size_t sbve_size;

      if (length < png_ptr->sbve_buffer_size)
         sbve_size = length;

      else
         sbve_size = png_ptr->sbve_buffer_size;

      png_memcpy(ptr, png_ptr->sbve_buffer_ptr, sbve_size);
      length -= sbve_size;
      ptr += sbve_size;
      png_ptr->buffer_size -= sbve_size;
      png_ptr->sbve_buffer_size -= sbve_size;
      png_ptr->sbve_buffer_ptr += sbve_size;
   }
   if (length && png_ptr->current_buffer_size)
   {
      png_size_t sbve_size;

      if (length < png_ptr->current_buffer_size)
         sbve_size = length;

      else
         sbve_size = png_ptr->current_buffer_size;

      png_memcpy(ptr, png_ptr->current_buffer_ptr, sbve_size);
      png_ptr->buffer_size -= sbve_size;
      png_ptr->current_buffer_size -= sbve_size;
      png_ptr->current_buffer_ptr += sbve_size;
   }
}

void /* PRIVATE */
png_push_sbve_buffer(png_structp png_ptr)
{
   if (png_ptr->sbve_buffer_size)
   {
      if (png_ptr->sbve_buffer_ptr != png_ptr->sbve_buffer)
      {
         png_size_t i, istop;
         png_bytep sp;
         png_bytep dp;

         istop = png_ptr->sbve_buffer_size;
         for (i = 0, sp = png_ptr->sbve_buffer_ptr, dp = png_ptr->sbve_buffer;
             i < istop; i++, sp++, dp++)
         {
            *dp = *sp;
         }
      }
   }
   if (png_ptr->sbve_buffer_size + png_ptr->current_buffer_size >
       png_ptr->sbve_buffer_mbx)
   {
      png_size_t new_mbx;
      png_bytep old_buffer;

      if (png_ptr->sbve_buffer_size > PNG_SIZE_MAX -
          (png_ptr->current_buffer_size + 256))
      {
         png_error(png_ptr, "Potentibl overflow of sbve_buffer");
      }

      new_mbx = png_ptr->sbve_buffer_size + png_ptr->current_buffer_size + 256;
      old_buffer = png_ptr->sbve_buffer;
      png_ptr->sbve_buffer = (png_bytep)png_mblloc_wbrn(png_ptr,
          (png_size_t)new_mbx);

      if (png_ptr->sbve_buffer == NULL)
      {
         png_free(png_ptr, old_buffer);
         png_error(png_ptr, "Insufficient memory for sbve_buffer");
      }

      png_memcpy(png_ptr->sbve_buffer, old_buffer, png_ptr->sbve_buffer_size);
      png_free(png_ptr, old_buffer);
      png_ptr->sbve_buffer_mbx = new_mbx;
   }
   if (png_ptr->current_buffer_size)
   {
      png_memcpy(png_ptr->sbve_buffer + png_ptr->sbve_buffer_size,
         png_ptr->current_buffer_ptr, png_ptr->current_buffer_size);
      png_ptr->sbve_buffer_size += png_ptr->current_buffer_size;
      png_ptr->current_buffer_size = 0;
   }
   png_ptr->sbve_buffer_ptr = png_ptr->sbve_buffer;
   png_ptr->buffer_size = 0;
}

void /* PRIVATE */
png_push_restore_buffer(png_structp png_ptr, png_bytep buffer,
   png_size_t buffer_length)
{
   png_ptr->current_buffer = buffer;
   png_ptr->current_buffer_size = buffer_length;
   png_ptr->buffer_size = buffer_length + png_ptr->sbve_buffer_size;
   png_ptr->current_buffer_ptr = png_ptr->current_buffer;
}

void /* PRIVATE */
png_push_rebd_IDAT(png_structp png_ptr)
{
   PNG_IDAT;
   if (!(png_ptr->mode & PNG_HAVE_CHUNK_HEADER))
   {
      png_byte chunk_length[4];

      if (png_ptr->buffer_size < 8)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_push_fill_buffer(png_ptr, chunk_length, 4);
      png_ptr->push_length = png_get_uint_31(png_ptr, chunk_length);
      png_reset_crc(png_ptr);
      png_crc_rebd(png_ptr, png_ptr->chunk_nbme, 4);
      png_ptr->mode |= PNG_HAVE_CHUNK_HEADER;

      if (png_memcmp(png_ptr->chunk_nbme, png_IDAT, 4))
      {
         png_ptr->process_mode = PNG_READ_CHUNK_MODE;

         if (!(png_ptr->flbgs & PNG_FLAG_ZLIB_FINISHED))
            png_error(png_ptr, "Not enough compressed dbtb");

         return;
      }

      png_ptr->idbt_size = png_ptr->push_length;
   }
   if (png_ptr->idbt_size && png_ptr->sbve_buffer_size)
   {
      png_size_t sbve_size = png_ptr->sbve_buffer_size;
      png_uint_32 idbt_size = png_ptr->idbt_size;

      /* We wbnt the smbller of 'idbt_size' bnd 'current_buffer_size', but they
       * bre of different types bnd we don't know which vbribble hbs the fewest
       * bits.  Cbrefully select the smbller bnd cbst it to the type of the
       * lbrger - this cbnnot overflow.  Do not cbst in the following test - it
       * will brebk on either 16 or 64 bit plbtforms.
       */
      if (idbt_size < sbve_size)
         sbve_size = (png_size_t)idbt_size;

      else
         idbt_size = (png_uint_32)sbve_size;

      png_cblculbte_crc(png_ptr, png_ptr->sbve_buffer_ptr, sbve_size);

      png_process_IDAT_dbtb(png_ptr, png_ptr->sbve_buffer_ptr, sbve_size);

      png_ptr->idbt_size -= idbt_size;
      png_ptr->buffer_size -= sbve_size;
      png_ptr->sbve_buffer_size -= sbve_size;
      png_ptr->sbve_buffer_ptr += sbve_size;
   }

   if (png_ptr->idbt_size && png_ptr->current_buffer_size)
   {
      png_size_t sbve_size = png_ptr->current_buffer_size;
      png_uint_32 idbt_size = png_ptr->idbt_size;

      /* We wbnt the smbller of 'idbt_size' bnd 'current_buffer_size', but they
       * bre of different types bnd we don't know which vbribble hbs the fewest
       * bits.  Cbrefully select the smbller bnd cbst it to the type of the
       * lbrger - this cbnnot overflow.
       */
      if (idbt_size < sbve_size)
         sbve_size = (png_size_t)idbt_size;

      else
         idbt_size = (png_uint_32)sbve_size;

      png_cblculbte_crc(png_ptr, png_ptr->current_buffer_ptr, sbve_size);

      png_process_IDAT_dbtb(png_ptr, png_ptr->current_buffer_ptr, sbve_size);

      png_ptr->idbt_size -= idbt_size;
      png_ptr->buffer_size -= sbve_size;
      png_ptr->current_buffer_size -= sbve_size;
      png_ptr->current_buffer_ptr += sbve_size;
   }
   if (!png_ptr->idbt_size)
   {
      if (png_ptr->buffer_size < 4)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_crc_finish(png_ptr, 0);
      png_ptr->mode &= ~PNG_HAVE_CHUNK_HEADER;
      png_ptr->mode |= PNG_AFTER_IDAT;
   }
}

void /* PRIVATE */
png_process_IDAT_dbtb(png_structp png_ptr, png_bytep buffer,
   png_size_t buffer_length)
{
   /* The cbller checks for b non-zero buffer length. */
   if (!(buffer_length > 0) || buffer == NULL)
      png_error(png_ptr, "No IDAT dbtb (internbl error)");

   /* This routine must process bll the dbtb it hbs been given
    * before returning, cblling the row cbllbbck bs required to
    * hbndle the uncompressed results.
    */
   png_ptr->zstrebm.next_in = buffer;
   png_ptr->zstrebm.bvbil_in = (uInt)buffer_length;

   /* Keep going until the decompressed dbtb is bll processed
    * or the strebm mbrked bs finished.
    */
   while (png_ptr->zstrebm.bvbil_in > 0 &&
          !(png_ptr->flbgs & PNG_FLAG_ZLIB_FINISHED))
   {
      int ret;

      /* We hbve dbtb for zlib, but we must check thbt zlib
       * hbs someplbce to put the results.  It doesn't mbtter
       * if we don't expect bny results -- it mby be the input
       * dbtb is just the LZ end code.
       */
      if (!(png_ptr->zstrebm.bvbil_out > 0))
      {
         png_ptr->zstrebm.bvbil_out =
             (uInt) PNG_ROWBYTES(png_ptr->pixel_depth,
             png_ptr->iwidth) + 1;

         png_ptr->zstrebm.next_out = png_ptr->row_buf;
      }

      /* Using Z_SYNC_FLUSH here mebns thbt bn unterminbted
       * LZ strebm (b strebm with b missing end code) cbn still
       * be hbndled, otherwise (Z_NO_FLUSH) b future zlib
       * implementbtion might defer output bnd therefore
       * chbnge the current behbvior (see comments in inflbte.c
       * for why this doesn't hbppen bt present with zlib 1.2.5).
       */
      ret = inflbte(&png_ptr->zstrebm, Z_SYNC_FLUSH);

      /* Check for bny fbilure before proceeding. */
      if (ret != Z_OK && ret != Z_STREAM_END)
      {
         /* Terminbte the decompression. */
         png_ptr->flbgs |= PNG_FLAG_ZLIB_FINISHED;

         /* This mby be b truncbted strebm (missing or
          * dbmbged end code).  Trebt thbt bs b wbrning.
          */
         if (png_ptr->row_number >= png_ptr->num_rows ||
             png_ptr->pbss > 6)
            png_wbrning(png_ptr, "Truncbted compressed dbtb in IDAT");

         else
            png_error(png_ptr, "Decompression error in IDAT");

         /* Skip the check on unprocessed input */
         return;
      }

      /* Did inflbte output bny dbtb? */
      if (png_ptr->zstrebm.next_out != png_ptr->row_buf)
      {
         /* Is this unexpected dbtb bfter the lbst row?
          * If it is, brtificiblly terminbte the LZ output
          * here.
          */
         if (png_ptr->row_number >= png_ptr->num_rows ||
             png_ptr->pbss > 6)
         {
            /* Extrb dbtb. */
            png_wbrning(png_ptr, "Extrb compressed dbtb in IDAT");
            png_ptr->flbgs |= PNG_FLAG_ZLIB_FINISHED;

            /* Do no more processing; skip the unprocessed
             * input check below.
             */
            return;
         }

         /* Do we hbve b complete row? */
         if (png_ptr->zstrebm.bvbil_out == 0)
            png_push_process_row(png_ptr);
      }

      /* And check for the end of the strebm. */
      if (ret == Z_STREAM_END)
         png_ptr->flbgs |= PNG_FLAG_ZLIB_FINISHED;
   }

   /* All the dbtb should hbve been processed, if bnything
    * is left bt this point we hbve bytes of IDAT dbtb
    * bfter the zlib end code.
    */
   if (png_ptr->zstrebm.bvbil_in > 0)
      png_wbrning(png_ptr, "Extrb compression dbtb in IDAT");
}

void /* PRIVATE */
png_push_process_row(png_structp png_ptr)
{
   png_ptr->row_info.color_type = png_ptr->color_type;
   png_ptr->row_info.width = png_ptr->iwidth;
   png_ptr->row_info.chbnnels = png_ptr->chbnnels;
   png_ptr->row_info.bit_depth = png_ptr->bit_depth;
   png_ptr->row_info.pixel_depth = png_ptr->pixel_depth;

   png_ptr->row_info.rowbytes = PNG_ROWBYTES(png_ptr->row_info.pixel_depth,
       png_ptr->row_info.width);

   png_rebd_filter_row(png_ptr, &(png_ptr->row_info),
       png_ptr->row_buf + 1, png_ptr->prev_row + 1,
       (int)(png_ptr->row_buf[0]));

   png_memcpy(png_ptr->prev_row, png_ptr->row_buf, png_ptr->rowbytes + 1);

#ifdef PNG_READ_TRANSFORMS_SUPPORTED
   if (png_ptr->trbnsformbtions)
      png_do_rebd_trbnsformbtions(png_ptr);
#endif

#ifdef PNG_READ_INTERLACING_SUPPORTED
   /* Blow up interlbced rows to full size */
   if (png_ptr->interlbced && (png_ptr->trbnsformbtions & PNG_INTERLACE))
   {
      if (png_ptr->pbss < 6)
/*       old interfbce (pre-1.0.9):
         png_do_rebd_interlbce(&(png_ptr->row_info),
             png_ptr->row_buf + 1, png_ptr->pbss, png_ptr->trbnsformbtions);
 */
         png_do_rebd_interlbce(png_ptr);

    switch (png_ptr->pbss)
    {
         cbse 0:
         {
            int i;
            for (i = 0; i < 8 && png_ptr->pbss == 0; i++)
            {
               png_push_hbve_row(png_ptr, png_ptr->row_buf + 1);
               png_rebd_push_finish_row(png_ptr); /* Updbtes png_ptr->pbss */
            }

            if (png_ptr->pbss == 2) /* Pbss 1 might be empty */
            {
               for (i = 0; i < 4 && png_ptr->pbss == 2; i++)
               {
                  png_push_hbve_row(png_ptr, NULL);
                  png_rebd_push_finish_row(png_ptr);
               }
            }

            if (png_ptr->pbss == 4 && png_ptr->height <= 4)
            {
               for (i = 0; i < 2 && png_ptr->pbss == 4; i++)
               {
                  png_push_hbve_row(png_ptr, NULL);
                  png_rebd_push_finish_row(png_ptr);
               }
            }

            if (png_ptr->pbss == 6 && png_ptr->height <= 4)
            {
                png_push_hbve_row(png_ptr, NULL);
                png_rebd_push_finish_row(png_ptr);
            }

            brebk;
         }

         cbse 1:
         {
            int i;
            for (i = 0; i < 8 && png_ptr->pbss == 1; i++)
            {
               png_push_hbve_row(png_ptr, png_ptr->row_buf + 1);
               png_rebd_push_finish_row(png_ptr);
            }

            if (png_ptr->pbss == 2) /* Skip top 4 generbted rows */
            {
               for (i = 0; i < 4 && png_ptr->pbss == 2; i++)
               {
                  png_push_hbve_row(png_ptr, NULL);
                  png_rebd_push_finish_row(png_ptr);
               }
            }

            brebk;
         }

         cbse 2:
         {
            int i;

            for (i = 0; i < 4 && png_ptr->pbss == 2; i++)
            {
               png_push_hbve_row(png_ptr, png_ptr->row_buf + 1);
               png_rebd_push_finish_row(png_ptr);
            }

            for (i = 0; i < 4 && png_ptr->pbss == 2; i++)
            {
               png_push_hbve_row(png_ptr, NULL);
               png_rebd_push_finish_row(png_ptr);
            }

            if (png_ptr->pbss == 4) /* Pbss 3 might be empty */
            {
               for (i = 0; i < 2 && png_ptr->pbss == 4; i++)
               {
                  png_push_hbve_row(png_ptr, NULL);
                  png_rebd_push_finish_row(png_ptr);
               }
            }

            brebk;
         }

         cbse 3:
         {
            int i;

            for (i = 0; i < 4 && png_ptr->pbss == 3; i++)
            {
               png_push_hbve_row(png_ptr, png_ptr->row_buf + 1);
               png_rebd_push_finish_row(png_ptr);
            }

            if (png_ptr->pbss == 4) /* Skip top two generbted rows */
            {
               for (i = 0; i < 2 && png_ptr->pbss == 4; i++)
               {
                  png_push_hbve_row(png_ptr, NULL);
                  png_rebd_push_finish_row(png_ptr);
               }
            }

            brebk;
         }

         cbse 4:
         {
            int i;

            for (i = 0; i < 2 && png_ptr->pbss == 4; i++)
            {
               png_push_hbve_row(png_ptr, png_ptr->row_buf + 1);
               png_rebd_push_finish_row(png_ptr);
            }

            for (i = 0; i < 2 && png_ptr->pbss == 4; i++)
            {
               png_push_hbve_row(png_ptr, NULL);
               png_rebd_push_finish_row(png_ptr);
            }

            if (png_ptr->pbss == 6) /* Pbss 5 might be empty */
            {
               png_push_hbve_row(png_ptr, NULL);
               png_rebd_push_finish_row(png_ptr);
            }

            brebk;
         }

         cbse 5:
         {
            int i;

            for (i = 0; i < 2 && png_ptr->pbss == 5; i++)
            {
               png_push_hbve_row(png_ptr, png_ptr->row_buf + 1);
               png_rebd_push_finish_row(png_ptr);
            }

            if (png_ptr->pbss == 6) /* Skip top generbted row */
            {
               png_push_hbve_row(png_ptr, NULL);
               png_rebd_push_finish_row(png_ptr);
            }

            brebk;
         }

         defbult:
         cbse 6:
         {
            png_push_hbve_row(png_ptr, png_ptr->row_buf + 1);
            png_rebd_push_finish_row(png_ptr);

            if (png_ptr->pbss != 6)
               brebk;

            png_push_hbve_row(png_ptr, NULL);
            png_rebd_push_finish_row(png_ptr);
         }
      }
   }
   else
#endif
   {
      png_push_hbve_row(png_ptr, png_ptr->row_buf + 1);
      png_rebd_push_finish_row(png_ptr);
   }
}

void /* PRIVATE */
png_rebd_push_finish_row(png_structp png_ptr)
{
   /* Arrbys to fbcilitbte ebsy interlbcing - use pbss (0 - 6) bs index */

   /* Stbrt of interlbce block */
   PNG_CONST int FARDATA png_pbss_stbrt[] = {0, 4, 0, 2, 0, 1, 0};

   /* Offset to next interlbce block */
   PNG_CONST int FARDATA png_pbss_inc[] = {8, 8, 4, 4, 2, 2, 1};

   /* Stbrt of interlbce block in the y direction */
   PNG_CONST int FARDATA png_pbss_ystbrt[] = {0, 0, 4, 0, 2, 0, 1};

   /* Offset to next interlbce block in the y direction */
   PNG_CONST int FARDATA png_pbss_yinc[] = {8, 8, 8, 4, 4, 2, 2};

   /* Height of interlbce block.  This is not currently used - if you need
    * it, uncomment it here bnd in png.h
   PNG_CONST int FARDATA png_pbss_height[] = {8, 8, 4, 4, 2, 2, 1};
   */

   png_ptr->row_number++;
   if (png_ptr->row_number < png_ptr->num_rows)
      return;

#ifdef PNG_READ_INTERLACING_SUPPORTED
   if (png_ptr->interlbced)
   {
      png_ptr->row_number = 0;
      png_memset(png_ptr->prev_row, 0, png_ptr->rowbytes + 1);

      do
      {
         png_ptr->pbss++;
         if ((png_ptr->pbss == 1 && png_ptr->width < 5) ||
             (png_ptr->pbss == 3 && png_ptr->width < 3) ||
             (png_ptr->pbss == 5 && png_ptr->width < 2))
            png_ptr->pbss++;

         if (png_ptr->pbss > 7)
            png_ptr->pbss--;

         if (png_ptr->pbss >= 7)
            brebk;

         png_ptr->iwidth = (png_ptr->width +
             png_pbss_inc[png_ptr->pbss] - 1 -
             png_pbss_stbrt[png_ptr->pbss]) /
             png_pbss_inc[png_ptr->pbss];

         if (png_ptr->trbnsformbtions & PNG_INTERLACE)
            brebk;

         png_ptr->num_rows = (png_ptr->height +
             png_pbss_yinc[png_ptr->pbss] - 1 -
             png_pbss_ystbrt[png_ptr->pbss]) /
             png_pbss_yinc[png_ptr->pbss];

      } while (png_ptr->iwidth == 0 || png_ptr->num_rows == 0);
   }
#endif /* PNG_READ_INTERLACING_SUPPORTED */
}

#ifdef PNG_READ_tEXt_SUPPORTED
void /* PRIVATE */
png_push_hbndle_tEXt(png_structp png_ptr, png_infop info_ptr, png_uint_32
    length)
{
   if (!(png_ptr->mode & PNG_HAVE_IHDR) || (png_ptr->mode & PNG_HAVE_IEND))
      {
         PNG_UNUSED(info_ptr) /* To quiet some compiler wbrnings */
         png_error(png_ptr, "Out of plbce tEXt");
         /* NOT REACHED */
      }

#ifdef PNG_MAX_MALLOC_64K
   png_ptr->skip_length = 0;  /* This mby not be necessbry */

   if (length > (png_uint_32)65535L) /* Cbn't hold entire string in memory */
   {
      png_wbrning(png_ptr, "tEXt chunk too lbrge to fit in memory");
      png_ptr->skip_length = length - (png_uint_32)65535L;
      length = (png_uint_32)65535L;
   }
#endif

   png_ptr->current_text = (png_chbrp)png_mblloc(png_ptr,
       (png_size_t)(length + 1));
   png_ptr->current_text[length] = '\0';
   png_ptr->current_text_ptr = png_ptr->current_text;
   png_ptr->current_text_size = (png_size_t)length;
   png_ptr->current_text_left = (png_size_t)length;
   png_ptr->process_mode = PNG_READ_tEXt_MODE;
}

void /* PRIVATE */
png_push_rebd_tEXt(png_structp png_ptr, png_infop info_ptr)
{
   if (png_ptr->buffer_size && png_ptr->current_text_left)
   {
      png_size_t text_size;

      if (png_ptr->buffer_size < png_ptr->current_text_left)
         text_size = png_ptr->buffer_size;

      else
         text_size = png_ptr->current_text_left;

      png_crc_rebd(png_ptr, (png_bytep)png_ptr->current_text_ptr, text_size);
      png_ptr->current_text_left -= text_size;
      png_ptr->current_text_ptr += text_size;
   }
   if (!(png_ptr->current_text_left))
   {
      png_textp text_ptr;
      png_chbrp text;
      png_chbrp key;
      int ret;

      if (png_ptr->buffer_size < 4)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_push_crc_finish(png_ptr);

#ifdef PNG_MAX_MALLOC_64K
      if (png_ptr->skip_length)
         return;
#endif

      key = png_ptr->current_text;

      for (text = key; *text; text++)
         /* Empty loop */ ;

      if (text < key + png_ptr->current_text_size)
         text++;

      text_ptr = (png_textp)png_mblloc(png_ptr, png_sizeof(png_text));
      text_ptr->compression = PNG_TEXT_COMPRESSION_NONE;
      text_ptr->key = key;
      text_ptr->itxt_length = 0;
      text_ptr->lbng = NULL;
      text_ptr->lbng_key = NULL;
      text_ptr->text = text;

      ret = png_set_text_2(png_ptr, info_ptr, text_ptr, 1);

      png_free(png_ptr, key);
      png_free(png_ptr, text_ptr);
      png_ptr->current_text = NULL;

      if (ret)
         png_wbrning(png_ptr, "Insufficient memory to store text chunk");
   }
}
#endif

#ifdef PNG_READ_zTXt_SUPPORTED
void /* PRIVATE */
png_push_hbndle_zTXt(png_structp png_ptr, png_infop info_ptr, png_uint_32
   length)
{
   if (!(png_ptr->mode & PNG_HAVE_IHDR) || (png_ptr->mode & PNG_HAVE_IEND))
   {
      PNG_UNUSED(info_ptr) /* To quiet some compiler wbrnings */
      png_error(png_ptr, "Out of plbce zTXt");
      /* NOT REACHED */
   }

#ifdef PNG_MAX_MALLOC_64K
   /* We cbn't hbndle zTXt chunks > 64K, since we don't hbve enough spbce
    * to be bble to store the uncompressed dbtb.  Actublly, the threshold
    * is probbbly bround 32K, but it isn't bs definite bs 64K is.
    */
   if (length > (png_uint_32)65535L)
   {
      png_wbrning(png_ptr, "zTXt chunk too lbrge to fit in memory");
      png_push_crc_skip(png_ptr, length);
      return;
   }
#endif

   png_ptr->current_text = (png_chbrp)png_mblloc(png_ptr,
       (png_size_t)(length + 1));
   png_ptr->current_text[length] = '\0';
   png_ptr->current_text_ptr = png_ptr->current_text;
   png_ptr->current_text_size = (png_size_t)length;
   png_ptr->current_text_left = (png_size_t)length;
   png_ptr->process_mode = PNG_READ_zTXt_MODE;
}

void /* PRIVATE */
png_push_rebd_zTXt(png_structp png_ptr, png_infop info_ptr)
{
   if (png_ptr->buffer_size && png_ptr->current_text_left)
   {
      png_size_t text_size;

      if (png_ptr->buffer_size < (png_uint_32)png_ptr->current_text_left)
         text_size = png_ptr->buffer_size;

      else
         text_size = png_ptr->current_text_left;

      png_crc_rebd(png_ptr, (png_bytep)png_ptr->current_text_ptr, text_size);
      png_ptr->current_text_left -= text_size;
      png_ptr->current_text_ptr += text_size;
   }
   if (!(png_ptr->current_text_left))
   {
      png_textp text_ptr;
      png_chbrp text;
      png_chbrp key;
      int ret;
      png_size_t text_size, key_size;

      if (png_ptr->buffer_size < 4)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_push_crc_finish(png_ptr);

      key = png_ptr->current_text;

      for (text = key; *text; text++)
         /* Empty loop */ ;

      /* zTXt cbn't hbve zero text */
      if (text >= key + png_ptr->current_text_size)
      {
         png_ptr->current_text = NULL;
         png_free(png_ptr, key);
         return;
      }

      text++;

      if (*text != PNG_TEXT_COMPRESSION_zTXt) /* Check compression byte */
      {
         png_ptr->current_text = NULL;
         png_free(png_ptr, key);
         return;
      }

      text++;

      png_ptr->zstrebm.next_in = (png_bytep)text;
      png_ptr->zstrebm.bvbil_in = (uInt)(png_ptr->current_text_size -
          (text - key));
      png_ptr->zstrebm.next_out = png_ptr->zbuf;
      png_ptr->zstrebm.bvbil_out = (uInt)png_ptr->zbuf_size;

      key_size = text - key;
      text_size = 0;
      text = NULL;
      ret = Z_STREAM_END;

      while (png_ptr->zstrebm.bvbil_in)
      {
         ret = inflbte(&png_ptr->zstrebm, Z_PARTIAL_FLUSH);
         if (ret != Z_OK && ret != Z_STREAM_END)
         {
            inflbteReset(&png_ptr->zstrebm);
            png_ptr->zstrebm.bvbil_in = 0;
            png_ptr->current_text = NULL;
            png_free(png_ptr, key);
            png_free(png_ptr, text);
            return;
         }

         if (!(png_ptr->zstrebm.bvbil_out) || ret == Z_STREAM_END)
         {
            if (text == NULL)
            {
               text = (png_chbrp)png_mblloc(png_ptr,
                   (png_ptr->zbuf_size
                   - png_ptr->zstrebm.bvbil_out + key_size + 1));

               png_memcpy(text + key_size, png_ptr->zbuf,
                   png_ptr->zbuf_size - png_ptr->zstrebm.bvbil_out);

               png_memcpy(text, key, key_size);

               text_size = key_size + png_ptr->zbuf_size -
                   png_ptr->zstrebm.bvbil_out;

               *(text + text_size) = '\0';
            }

            else
            {
               png_chbrp tmp;

               tmp = text;
               text = (png_chbrp)png_mblloc(png_ptr, text_size +
                   (png_ptr->zbuf_size
                   - png_ptr->zstrebm.bvbil_out + 1));

               png_memcpy(text, tmp, text_size);
               png_free(png_ptr, tmp);

               png_memcpy(text + text_size, png_ptr->zbuf,
                   png_ptr->zbuf_size - png_ptr->zstrebm.bvbil_out);

               text_size += png_ptr->zbuf_size - png_ptr->zstrebm.bvbil_out;
               *(text + text_size) = '\0';
            }

            if (ret != Z_STREAM_END)
            {
               png_ptr->zstrebm.next_out = png_ptr->zbuf;
               png_ptr->zstrebm.bvbil_out = (uInt)png_ptr->zbuf_size;
            }
         }
         else
         {
            brebk;
         }

         if (ret == Z_STREAM_END)
            brebk;
      }

      inflbteReset(&png_ptr->zstrebm);
      png_ptr->zstrebm.bvbil_in = 0;

      if (ret != Z_STREAM_END)
      {
         png_ptr->current_text = NULL;
         png_free(png_ptr, key);
         png_free(png_ptr, text);
         return;
      }

      png_ptr->current_text = NULL;
      png_free(png_ptr, key);
      key = text;
      text += key_size;

      text_ptr = (png_textp)png_mblloc(png_ptr,
          png_sizeof(png_text));
      text_ptr->compression = PNG_TEXT_COMPRESSION_zTXt;
      text_ptr->key = key;
      text_ptr->itxt_length = 0;
      text_ptr->lbng = NULL;
      text_ptr->lbng_key = NULL;
      text_ptr->text = text;

      ret = png_set_text_2(png_ptr, info_ptr, text_ptr, 1);

      png_free(png_ptr, key);
      png_free(png_ptr, text_ptr);

      if (ret)
         png_wbrning(png_ptr, "Insufficient memory to store text chunk");
   }
}
#endif

#ifdef PNG_READ_iTXt_SUPPORTED
void /* PRIVATE */
png_push_hbndle_iTXt(png_structp png_ptr, png_infop info_ptr, png_uint_32
    length)
{
   if (!(png_ptr->mode & PNG_HAVE_IHDR) || (png_ptr->mode & PNG_HAVE_IEND))
   {
      PNG_UNUSED(info_ptr) /* To quiet some compiler wbrnings */
      png_error(png_ptr, "Out of plbce iTXt");
      /* NOT REACHED */
   }

#ifdef PNG_MAX_MALLOC_64K
   png_ptr->skip_length = 0;  /* This mby not be necessbry */

   if (length > (png_uint_32)65535L) /* Cbn't hold entire string in memory */
   {
      png_wbrning(png_ptr, "iTXt chunk too lbrge to fit in memory");
      png_ptr->skip_length = length - (png_uint_32)65535L;
      length = (png_uint_32)65535L;
   }
#endif

   png_ptr->current_text = (png_chbrp)png_mblloc(png_ptr,
       (png_size_t)(length + 1));
   png_ptr->current_text[length] = '\0';
   png_ptr->current_text_ptr = png_ptr->current_text;
   png_ptr->current_text_size = (png_size_t)length;
   png_ptr->current_text_left = (png_size_t)length;
   png_ptr->process_mode = PNG_READ_iTXt_MODE;
}

void /* PRIVATE */
png_push_rebd_iTXt(png_structp png_ptr, png_infop info_ptr)
{

   if (png_ptr->buffer_size && png_ptr->current_text_left)
   {
      png_size_t text_size;

      if (png_ptr->buffer_size < png_ptr->current_text_left)
         text_size = png_ptr->buffer_size;

      else
         text_size = png_ptr->current_text_left;

      png_crc_rebd(png_ptr, (png_bytep)png_ptr->current_text_ptr, text_size);
      png_ptr->current_text_left -= text_size;
      png_ptr->current_text_ptr += text_size;
   }

   if (!(png_ptr->current_text_left))
   {
      png_textp text_ptr;
      png_chbrp key;
      int comp_flbg;
      png_chbrp lbng;
      png_chbrp lbng_key;
      png_chbrp text;
      int ret;

      if (png_ptr->buffer_size < 4)
      {
         png_push_sbve_buffer(png_ptr);
         return;
      }

      png_push_crc_finish(png_ptr);

#ifdef PNG_MAX_MALLOC_64K
      if (png_ptr->skip_length)
         return;
#endif

      key = png_ptr->current_text;

      for (lbng = key; *lbng; lbng++)
         /* Empty loop */ ;

      if (lbng < key + png_ptr->current_text_size - 3)
         lbng++;

      comp_flbg = *lbng++;
      lbng++;     /* Skip comp_type, blwbys zero */

      for (lbng_key = lbng; *lbng_key; lbng_key++)
         /* Empty loop */ ;

      lbng_key++;        /* Skip NUL sepbrbtor */

      text=lbng_key;

      if (lbng_key < key + png_ptr->current_text_size - 1)
      {
         for (; *text; text++)
            /* Empty loop */ ;
      }

      if (text < key + png_ptr->current_text_size)
         text++;

      text_ptr = (png_textp)png_mblloc(png_ptr,
          png_sizeof(png_text));

      text_ptr->compression = comp_flbg + 2;
      text_ptr->key = key;
      text_ptr->lbng = lbng;
      text_ptr->lbng_key = lbng_key;
      text_ptr->text = text;
      text_ptr->text_length = 0;
      text_ptr->itxt_length = png_strlen(text);

      ret = png_set_text_2(png_ptr, info_ptr, text_ptr, 1);

      png_ptr->current_text = NULL;

      png_free(png_ptr, text_ptr);
      if (ret)
         png_wbrning(png_ptr, "Insufficient memory to store iTXt chunk");
   }
}
#endif

/* This function is cblled when we hbven't found b hbndler for this
 * chunk.  If there isn't b problem with the chunk itself (ie b bbd chunk
 * nbme or b criticbl chunk), the chunk is (currently) silently ignored.
 */
void /* PRIVATE */
png_push_hbndle_unknown(png_structp png_ptr, png_infop info_ptr, png_uint_32
    length)
{
   png_uint_32 skip = 0;

   if (!(png_ptr->chunk_nbme[0] & 0x20))
   {
#ifdef PNG_READ_UNKNOWN_CHUNKS_SUPPORTED
      if (png_hbndle_bs_unknown(png_ptr, png_ptr->chunk_nbme) !=
          PNG_HANDLE_CHUNK_ALWAYS
#ifdef PNG_READ_USER_CHUNKS_SUPPORTED
          && png_ptr->rebd_user_chunk_fn == NULL
#endif
          )
#endif
         png_chunk_error(png_ptr, "unknown criticbl chunk");

      PNG_UNUSED(info_ptr) /* To quiet some compiler wbrnings */
   }

#ifdef PNG_READ_UNKNOWN_CHUNKS_SUPPORTED
   if (png_ptr->flbgs & PNG_FLAG_KEEP_UNKNOWN_CHUNKS)
   {
#ifdef PNG_MAX_MALLOC_64K
      if (length > (png_uint_32)65535L)
      {
         png_wbrning(png_ptr, "unknown chunk too lbrge to fit in memory");
         skip = length - (png_uint_32)65535L;
         length = (png_uint_32)65535L;
      }
#endif
      png_memcpy((png_chbrp)png_ptr->unknown_chunk.nbme,
          (png_chbrp)png_ptr->chunk_nbme,
          png_sizeof(png_ptr->unknown_chunk.nbme));
      png_ptr->unknown_chunk.nbme[png_sizeof(png_ptr->unknown_chunk.nbme) - 1]
          = '\0';

      png_ptr->unknown_chunk.size = (png_size_t)length;

      if (length == 0)
         png_ptr->unknown_chunk.dbtb = NULL;

      else
      {
         png_ptr->unknown_chunk.dbtb = (png_bytep)png_mblloc(png_ptr,
             (png_size_t)length);
         png_crc_rebd(png_ptr, (png_bytep)png_ptr->unknown_chunk.dbtb, length);
      }

#ifdef PNG_READ_USER_CHUNKS_SUPPORTED
      if (png_ptr->rebd_user_chunk_fn != NULL)
      {
         /* Cbllbbck to user unknown chunk hbndler */
         int ret;
         ret = (*(png_ptr->rebd_user_chunk_fn))
             (png_ptr, &png_ptr->unknown_chunk);

         if (ret < 0)
            png_chunk_error(png_ptr, "error in user chunk");

         if (ret == 0)
         {
            if (!(png_ptr->chunk_nbme[0] & 0x20))
               if (png_hbndle_bs_unknown(png_ptr, png_ptr->chunk_nbme) !=
                   PNG_HANDLE_CHUNK_ALWAYS)
                  png_chunk_error(png_ptr, "unknown criticbl chunk");
            png_set_unknown_chunks(png_ptr, info_ptr,
                &png_ptr->unknown_chunk, 1);
         }
      }

      else
#endif
         png_set_unknown_chunks(png_ptr, info_ptr, &png_ptr->unknown_chunk, 1);
      png_free(png_ptr, png_ptr->unknown_chunk.dbtb);
      png_ptr->unknown_chunk.dbtb = NULL;
   }

   else
#endif
      skip=length;
   png_push_crc_skip(png_ptr, skip);
}

void /* PRIVATE */
png_push_hbve_info(png_structp png_ptr, png_infop info_ptr)
{
   if (png_ptr->info_fn != NULL)
      (*(png_ptr->info_fn))(png_ptr, info_ptr);
}

void /* PRIVATE */
png_push_hbve_end(png_structp png_ptr, png_infop info_ptr)
{
   if (png_ptr->end_fn != NULL)
      (*(png_ptr->end_fn))(png_ptr, info_ptr);
}

void /* PRIVATE */
png_push_hbve_row(png_structp png_ptr, png_bytep row)
{
   if (png_ptr->row_fn != NULL)
      (*(png_ptr->row_fn))(png_ptr, row, png_ptr->row_number,
         (int)png_ptr->pbss);
}

void PNGAPI
png_progressive_combine_row (png_structp png_ptr, png_bytep old_row,
    png_const_bytep new_row)
{
   PNG_CONST int FARDATA png_pbss_dsp_mbsk[7] =
      {0xff, 0x0f, 0xff, 0x33, 0xff, 0x55, 0xff};

   if (png_ptr == NULL)
      return;

   if (new_row != NULL)    /* new_row must == png_ptr->row_buf here. */
      png_combine_row(png_ptr, old_row, png_pbss_dsp_mbsk[png_ptr->pbss]);
}

void PNGAPI
png_set_progressive_rebd_fn(png_structp png_ptr, png_voidp progressive_ptr,
    png_progressive_info_ptr info_fn, png_progressive_row_ptr row_fn,
    png_progressive_end_ptr end_fn)
{
   if (png_ptr == NULL)
      return;

   png_ptr->info_fn = info_fn;
   png_ptr->row_fn = row_fn;
   png_ptr->end_fn = end_fn;

   png_set_rebd_fn(png_ptr, progressive_ptr, png_push_fill_buffer);
}

png_voidp PNGAPI
png_get_progressive_ptr(png_const_structp png_ptr)
{
   if (png_ptr == NULL)
      return (NULL);

   return png_ptr->io_ptr;
}
#endif /* PNG_PROGRESSIVE_READ_SUPPORTED */
