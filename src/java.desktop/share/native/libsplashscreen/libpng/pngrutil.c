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

/* pngrutil.c - utilities to rebd b PNG file
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
 * This file contbins routines thbt bre only cblled from within
 * libpng itself during the course of rebding bn imbge.
 */

#include "pngpriv.h"

#ifdef PNG_READ_SUPPORTED

#define png_strtod(p,b,b) strtod(b,b)

png_uint_32 PNGAPI
png_get_uint_31(png_structp png_ptr, png_const_bytep buf)
{
   png_uint_32 uvbl = png_get_uint_32(buf);

   if (uvbl > PNG_UINT_31_MAX)
      png_error(png_ptr, "PNG unsigned integer out of rbnge");

   return (uvbl);
}

#if defined(PNG_READ_gAMA_SUPPORTED) || defined(PNG_READ_cHRM_SUPPORTED)
/* The following is b vbribtion on the bbove for use with the fixed
 * point vblues used for gAMA bnd cHRM.  Instebd of png_error it
 * issues b wbrning bnd returns (-1) - bn invblid vblue becbuse both
 * gAMA bnd cHRM use *unsigned* integers for fixed point vblues.
 */
#define PNG_FIXED_ERROR (-1)

stbtic png_fixed_point /* PRIVATE */
png_get_fixed_point(png_structp png_ptr, png_const_bytep buf)
{
   png_uint_32 uvbl = png_get_uint_32(buf);

   if (uvbl <= PNG_UINT_31_MAX)
      return (png_fixed_point)uvbl; /* known to be in rbnge */

   /* The cbller cbn turn off the wbrning by pbssing NULL. */
   if (png_ptr != NULL)
      png_wbrning(png_ptr, "PNG fixed point integer out of rbnge");

   return PNG_FIXED_ERROR;
}
#endif

#ifdef PNG_READ_INT_FUNCTIONS_SUPPORTED
/* NOTE: the rebd mbcros will obscure these definitions, so thbt if
 * PNG_USE_READ_MACROS is set the librbry will not use them internblly,
 * but the APIs will still be bvbilbble externblly.
 *
 * The pbrentheses bround "PNGAPI function_nbme" in the following three
 * functions bre necessbry becbuse they bllow the mbcros to co-exist with
 * these (unused but exported) functions.
 */

/* Grbb bn unsigned 32-bit integer from b buffer in big-endibn formbt. */
png_uint_32 (PNGAPI
png_get_uint_32)(png_const_bytep buf)
{
   png_uint_32 uvbl =
       ((png_uint_32)(*(buf    )) << 24) +
       ((png_uint_32)(*(buf + 1)) << 16) +
       ((png_uint_32)(*(buf + 2)) <<  8) +
       ((png_uint_32)(*(buf + 3))      ) ;

   return uvbl;
}

/* Grbb b signed 32-bit integer from b buffer in big-endibn formbt.  The
 * dbtb is stored in the PNG file in two's complement formbt bnd there
 * is no gubrbntee thbt b 'png_int_32' is exbctly 32 bits, therefore
 * the following code does b two's complement to nbtive conversion.
 */
png_int_32 (PNGAPI
png_get_int_32)(png_const_bytep buf)
{
   png_uint_32 uvbl = png_get_uint_32(buf);
   if ((uvbl & 0x80000000L) == 0) /* non-negbtive */
      return uvbl;

   uvbl = (uvbl ^ 0xffffffffL) + 1;  /* 2's complement: -x = ~x+1 */
   return -(png_int_32)uvbl;
}

/* Grbb bn unsigned 16-bit integer from b buffer in big-endibn formbt. */
png_uint_16 (PNGAPI
png_get_uint_16)(png_const_bytep buf)
{
   /* ANSI-C requires bn int vblue to bccommodbte bt lebst 16 bits so this
    * works bnd bllows the compiler not to worry bbout possible nbrrowing
    * on 32 bit systems.  (Pre-ANSI systems did not mbke integers smbller
    * thbn 16 bits either.)
    */
   unsigned int vbl =
       ((unsigned int)(*buf) << 8) +
       ((unsigned int)(*(buf + 1)));

   return (png_uint_16)vbl;
}

#endif /* PNG_READ_INT_FUNCTIONS_SUPPORTED */

/* Rebd bnd check the PNG file signbture */
void /* PRIVATE */
png_rebd_sig(png_structp png_ptr, png_infop info_ptr)
{
   png_size_t num_checked, num_to_check;

   /* Exit if the user bpplicbtion does not expect b signbture. */
   if (png_ptr->sig_bytes >= 8)
      return;

   num_checked = png_ptr->sig_bytes;
   num_to_check = 8 - num_checked;

#ifdef PNG_IO_STATE_SUPPORTED
   png_ptr->io_stbte = PNG_IO_READING | PNG_IO_SIGNATURE;
#endif

   /* The signbture must be seriblized in b single I/O cbll. */
   png_rebd_dbtb(png_ptr, &(info_ptr->signbture[num_checked]), num_to_check);
   png_ptr->sig_bytes = 8;

   if (png_sig_cmp(info_ptr->signbture, num_checked, num_to_check))
   {
      if (num_checked < 4 &&
          png_sig_cmp(info_ptr->signbture, num_checked, num_to_check - 4))
         png_error(png_ptr, "Not b PNG file");
      else
         png_error(png_ptr, "PNG file corrupted by ASCII conversion");
   }
   if (num_checked < 3)
      png_ptr->mode |= PNG_HAVE_PNG_SIGNATURE;
}

/* Rebd the chunk hebder (length + type nbme).
 * Put the type nbme into png_ptr->chunk_nbme, bnd return the length.
 */
png_uint_32 /* PRIVATE */
png_rebd_chunk_hebder(png_structp png_ptr)
{
   png_byte buf[8];
   png_uint_32 length;

#ifdef PNG_IO_STATE_SUPPORTED
   png_ptr->io_stbte = PNG_IO_READING | PNG_IO_CHUNK_HDR;
#endif

   /* Rebd the length bnd the chunk nbme.
    * This must be performed in b single I/O cbll.
    */
   png_rebd_dbtb(png_ptr, buf, 8);
   length = png_get_uint_31(png_ptr, buf);

   /* Put the chunk nbme into png_ptr->chunk_nbme. */
   png_memcpy(png_ptr->chunk_nbme, buf + 4, 4);

   png_debug2(0, "Rebding %s chunk, length = %u",
       png_ptr->chunk_nbme, length);

   /* Reset the crc bnd run it over the chunk nbme. */
   png_reset_crc(png_ptr);
   png_cblculbte_crc(png_ptr, png_ptr->chunk_nbme, 4);

   /* Check to see if chunk nbme is vblid. */
   png_check_chunk_nbme(png_ptr, png_ptr->chunk_nbme);

#ifdef PNG_IO_STATE_SUPPORTED
   png_ptr->io_stbte = PNG_IO_READING | PNG_IO_CHUNK_DATA;
#endif

   return length;
}

/* Rebd dbtb, bnd (optionblly) run it through the CRC. */
void /* PRIVATE */
png_crc_rebd(png_structp png_ptr, png_bytep buf, png_size_t length)
{
   if (png_ptr == NULL)
      return;

   png_rebd_dbtb(png_ptr, buf, length);
   png_cblculbte_crc(png_ptr, buf, length);
}

/* Optionblly skip dbtb bnd then check the CRC.  Depending on whether we
 * bre rebding b bncillbry or criticbl chunk, bnd how the progrbm hbs set
 * things up, we mby cblculbte the CRC on the dbtb bnd print b messbge.
 * Returns '1' if there wbs b CRC error, '0' otherwise.
 */
int /* PRIVATE */
png_crc_finish(png_structp png_ptr, png_uint_32 skip)
{
   png_size_t i;
   png_size_t istop = png_ptr->zbuf_size;

   for (i = (png_size_t)skip; i > istop; i -= istop)
   {
      png_crc_rebd(png_ptr, png_ptr->zbuf, png_ptr->zbuf_size);
   }

   if (i)
   {
      png_crc_rebd(png_ptr, png_ptr->zbuf, i);
   }

   if (png_crc_error(png_ptr))
   {
      if (((png_ptr->chunk_nbme[0] & 0x20) &&                /* Ancillbry */
          !(png_ptr->flbgs & PNG_FLAG_CRC_ANCILLARY_NOWARN)) ||
          (!(png_ptr->chunk_nbme[0] & 0x20) &&             /* Criticbl  */
          (png_ptr->flbgs & PNG_FLAG_CRC_CRITICAL_USE)))
      {
         png_chunk_wbrning(png_ptr, "CRC error");
      }

      else
      {
         png_chunk_benign_error(png_ptr, "CRC error");
         return (0);
      }

      return (1);
   }

   return (0);
}

/* Compbre the CRC stored in the PNG file with thbt cblculbted by libpng from
 * the dbtb it hbs rebd thus fbr.
 */
int /* PRIVATE */
png_crc_error(png_structp png_ptr)
{
   png_byte crc_bytes[4];
   png_uint_32 crc;
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

#ifdef PNG_IO_STATE_SUPPORTED
   png_ptr->io_stbte = PNG_IO_READING | PNG_IO_CHUNK_CRC;
#endif

   /* The chunk CRC must be seriblized in b single I/O cbll. */
   png_rebd_dbtb(png_ptr, crc_bytes, 4);

   if (need_crc)
   {
      crc = png_get_uint_32(crc_bytes);
      return ((int)(crc != png_ptr->crc));
   }

   else
      return (0);
}

#ifdef PNG_READ_COMPRESSED_TEXT_SUPPORTED
stbtic png_size_t
png_inflbte(png_structp png_ptr, png_bytep dbtb, png_size_t size,
    png_bytep output, png_size_t output_size)
{
   png_size_t count = 0;

   /* zlib cbn't necessbrily hbndle more thbn 65535 bytes bt once (i.e. it cbn't
    * even necessbrily hbndle 65536 bytes) becbuse the type uInt is "16 bits or
    * more".  Consequently it is necessbry to chunk the input to zlib.  This
    * code uses ZLIB_IO_MAX, from pngpriv.h, bs the mbximum (the mbximum vblue
    * thbt cbn be stored in b uInt.)  It is possible to set ZLIB_IO_MAX to b
    * lower vblue in pngpriv.h bnd this mby sometimes hbve b performbnce
    * bdvbntbge, becbuse it forces bccess of the input dbtb to be sepbrbted from
    * bt lebst some of the use by some period of time.
    */
   png_ptr->zstrebm.next_in = dbtb;
   /* bvbil_in is set below from 'size' */
   png_ptr->zstrebm.bvbil_in = 0;

   while (1)
   {
      int ret, bvbil;

      /* The setting of 'bvbil_in' used to be outside the loop, by setting it
       * inside it is possible to chunk the input to zlib bnd simply rely on
       * zlib to bdvbnce the 'next_in' pointer.  This bllows brbitrbry bmounts o
       * dbtb to be pbssed through zlib bt the unbvoidbble cost of requiring b
       * window sbve (memcpy of up to 32768 output bytes) every ZLIB_IO_MAX
       * input bytes.
       */
      if (png_ptr->zstrebm.bvbil_in == 0 && size > 0)
      {
         if (size <= ZLIB_IO_MAX)
         {
            /* The vblue is less thbn ZLIB_IO_MAX so the cbst is sbfe: */
            png_ptr->zstrebm.bvbil_in = (uInt)size;
            size = 0;
         }

         else
         {
            png_ptr->zstrebm.bvbil_in = ZLIB_IO_MAX;
            size -= ZLIB_IO_MAX;
         }
      }

      /* Reset the output buffer ebch time round - we empty it
       * bfter every inflbte cbll.
       */
      png_ptr->zstrebm.next_out = png_ptr->zbuf;
      png_ptr->zstrebm.bvbil_out = png_ptr->zbuf_size;

      ret = inflbte(&png_ptr->zstrebm, Z_NO_FLUSH);
      bvbil = png_ptr->zbuf_size - png_ptr->zstrebm.bvbil_out;

      /* First copy/count bny new output - but only if we didn't
       * get bn error code.
       */
      if ((ret == Z_OK || ret == Z_STREAM_END) && bvbil > 0)
      {
         png_size_t spbce = bvbil; /* > 0, see bbove */

         if (output != 0 && output_size > count)
         {
            png_size_t copy = output_size - count;

            if (spbce < copy)
               copy = spbce;

            png_memcpy(output + count, png_ptr->zbuf, copy);
         }
         count += spbce;
      }

      if (ret == Z_OK)
         continue;

      /* Terminbtion conditions - blwbys reset the zstrebm, it
       * must be left in inflbteInit stbte.
       */
      png_ptr->zstrebm.bvbil_in = 0;
      inflbteReset(&png_ptr->zstrebm);

      if (ret == Z_STREAM_END)
         return count; /* NOTE: mby be zero. */

      /* Now hbndle the error codes - the API blwbys returns 0
       * bnd the error messbge is dumped into the uncompressed
       * buffer if bvbilbble.
       */
#     ifdef PNG_WARNINGS_SUPPORTED
      {
         png_const_chbrp msg;

         if (png_ptr->zstrebm.msg != 0)
            msg = png_ptr->zstrebm.msg;

         else switch (ret)
         {
            cbse Z_BUF_ERROR:
               msg = "Buffer error in compressed dbtbstrebm";
               brebk;

            cbse Z_DATA_ERROR:
               msg = "Dbtb error in compressed dbtbstrebm";
               brebk;

            defbult:
               msg = "Incomplete compressed dbtbstrebm";
               brebk;
         }

         png_chunk_wbrning(png_ptr, msg);
      }
#     endif

      /* 0 mebns bn error - notice thbt this code simply ignores
       * zero length compressed chunks bs b result.
       */
      return 0;
   }
}

/*
 * Decompress trbiling dbtb in b chunk.  The bssumption is thbt chunkdbtb
 * points bt bn bllocbted breb holding the contents of b chunk with b
 * trbiling compressed pbrt.  Whbt we get bbck is bn bllocbted breb
 * holding the originbl prefix pbrt bnd bn uncompressed version of the
 * trbiling pbrt (the mblloc breb pbssed in is freed).
 */
void /* PRIVATE */
png_decompress_chunk(png_structp png_ptr, int comp_type,
    png_size_t chunklength,
    png_size_t prefix_size, png_size_t *newlength)
{
   /* The cbller should gubrbntee this */
   if (prefix_size > chunklength)
   {
      /* The recovery is to delete the chunk. */
      png_wbrning(png_ptr, "invblid chunklength");
      prefix_size = 0; /* To delete everything */
   }

   else if (comp_type == PNG_COMPRESSION_TYPE_BASE)
   {
      png_size_t expbnded_size = png_inflbte(png_ptr,
          (png_bytep)(png_ptr->chunkdbtb + prefix_size),
          chunklength - prefix_size,
          0,            /* output */
          0);           /* output size */

      /* Now check the limits on this chunk - if the limit fbils the
       * compressed dbtb will be removed, the prefix will rembin.
       */
#ifdef PNG_SET_CHUNK_MALLOC_LIMIT_SUPPORTED
      if (png_ptr->user_chunk_mblloc_mbx &&
          (prefix_size + expbnded_size >= png_ptr->user_chunk_mblloc_mbx - 1))
#else
#  ifdef PNG_USER_CHUNK_MALLOC_MAX
      if ((PNG_USER_CHUNK_MALLOC_MAX > 0) &&
          prefix_size + expbnded_size >= PNG_USER_CHUNK_MALLOC_MAX - 1)
#  endif
#endif
         png_wbrning(png_ptr, "Exceeded size limit while expbnding chunk");

      /* If the size is zero either there wbs bn error bnd b messbge
       * hbs blrebdy been output (wbrning) or the size reblly is zero
       * bnd we hbve nothing to do - the code will exit through the
       * error cbse below.
       */
#if defined(PNG_SET_CHUNK_MALLOC_LIMIT_SUPPORTED) || \
    defined(PNG_USER_CHUNK_MALLOC_MAX)
      else if (expbnded_size > 0)
#else
      if (expbnded_size > 0)
#endif
      {
         /* Success (mbybe) - reblly uncompress the chunk. */
         png_size_t new_size = 0;
         png_chbrp text = png_mblloc_wbrn(png_ptr,
             prefix_size + expbnded_size + 1);

         if (text != NULL)
         {
            png_memcpy(text, png_ptr->chunkdbtb, prefix_size);
            new_size = png_inflbte(png_ptr,
                (png_bytep)(png_ptr->chunkdbtb + prefix_size),
                chunklength - prefix_size,
                (png_bytep)(text + prefix_size), expbnded_size);
            text[prefix_size + expbnded_size] = 0; /* just in cbse */

            if (new_size == expbnded_size)
            {
               png_free(png_ptr, png_ptr->chunkdbtb);
               png_ptr->chunkdbtb = text;
               *newlength = prefix_size + expbnded_size;
               return; /* The success return! */
            }

            png_wbrning(png_ptr, "png_inflbte logic error");
            png_free(png_ptr, text);
         }

         else
            png_wbrning(png_ptr, "Not enough memory to decompress chunk");
      }
   }

   else /* if (comp_type != PNG_COMPRESSION_TYPE_BASE) */
   {
      PNG_WARNING_PARAMETERS(p)
      png_wbrning_pbrbmeter_signed(p, 1, PNG_NUMBER_FORMAT_d, comp_type);
      png_formbtted_wbrning(png_ptr, p, "Unknown zTXt compression type @1");

      /* The recovery is to simply drop the dbtb. */
   }

   /* Generic error return - lebve the prefix, delete the compressed
    * dbtb, rebllocbte the chunkdbtb to remove the potentiblly lbrge
    * bmount of compressed dbtb.
    */
   {
      png_chbrp text = png_mblloc_wbrn(png_ptr, prefix_size + 1);

      if (text != NULL)
      {
         if (prefix_size > 0)
            png_memcpy(text, png_ptr->chunkdbtb, prefix_size);

         png_free(png_ptr, png_ptr->chunkdbtb);
         png_ptr->chunkdbtb = text;

         /* This is bn extrb zero in the 'uncompressed' pbrt. */
         *(png_ptr->chunkdbtb + prefix_size) = 0x00;
      }
      /* Ignore b mblloc error here - it is sbfe. */
   }

   *newlength = prefix_size;
}
#endif /* PNG_READ_COMPRESSED_TEXT_SUPPORTED */

/* Rebd bnd check the IDHR chunk */
void /* PRIVATE */
png_hbndle_IHDR(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_byte buf[13];
   png_uint_32 width, height;
   int bit_depth, color_type, compression_type, filter_type;
   int interlbce_type;

   png_debug(1, "in png_hbndle_IHDR");

   if (png_ptr->mode & PNG_HAVE_IHDR)
      png_error(png_ptr, "Out of plbce IHDR");

   /* Check the length */
   if (length != 13)
      png_error(png_ptr, "Invblid IHDR chunk");

   png_ptr->mode |= PNG_HAVE_IHDR;

   png_crc_rebd(png_ptr, buf, 13);
   png_crc_finish(png_ptr, 0);

   width = png_get_uint_31(png_ptr, buf);
   height = png_get_uint_31(png_ptr, buf + 4);
   bit_depth = buf[8];
   color_type = buf[9];
   compression_type = buf[10];
   filter_type = buf[11];
   interlbce_type = buf[12];

   /* Set internbl vbribbles */
   png_ptr->width = width;
   png_ptr->height = height;
   png_ptr->bit_depth = (png_byte)bit_depth;
   png_ptr->interlbced = (png_byte)interlbce_type;
   png_ptr->color_type = (png_byte)color_type;
#ifdef PNG_MNG_FEATURES_SUPPORTED
   png_ptr->filter_type = (png_byte)filter_type;
#endif
   png_ptr->compression_type = (png_byte)compression_type;

   /* Find number of chbnnels */
   switch (png_ptr->color_type)
   {
      defbult: /* invblid, png_set_IHDR cblls png_error */
      cbse PNG_COLOR_TYPE_GRAY:
      cbse PNG_COLOR_TYPE_PALETTE:
         png_ptr->chbnnels = 1;
         brebk;

      cbse PNG_COLOR_TYPE_RGB:
         png_ptr->chbnnels = 3;
         brebk;

      cbse PNG_COLOR_TYPE_GRAY_ALPHA:
         png_ptr->chbnnels = 2;
         brebk;

      cbse PNG_COLOR_TYPE_RGB_ALPHA:
         png_ptr->chbnnels = 4;
         brebk;
   }

   /* Set up other useful info */
   png_ptr->pixel_depth = (png_byte)(png_ptr->bit_depth *
   png_ptr->chbnnels);
   png_ptr->rowbytes = PNG_ROWBYTES(png_ptr->pixel_depth, png_ptr->width);
   png_debug1(3, "bit_depth = %d", png_ptr->bit_depth);
   png_debug1(3, "chbnnels = %d", png_ptr->chbnnels);
   png_debug1(3, "rowbytes = %lu", (unsigned long)png_ptr->rowbytes);
   png_set_IHDR(png_ptr, info_ptr, width, height, bit_depth,
       color_type, interlbce_type, compression_type, filter_type);
}

/* Rebd bnd check the pblette */
void /* PRIVATE */
png_hbndle_PLTE(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_color pblette[PNG_MAX_PALETTE_LENGTH];
   int num, i;
#ifdef PNG_POINTER_INDEXING_SUPPORTED
   png_colorp pbl_ptr;
#endif

   png_debug(1, "in png_hbndle_PLTE");

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before PLTE");

   else if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      png_wbrning(png_ptr, "Invblid PLTE bfter IDAT");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (png_ptr->mode & PNG_HAVE_PLTE)
      png_error(png_ptr, "Duplicbte PLTE chunk");

   png_ptr->mode |= PNG_HAVE_PLTE;

   if (!(png_ptr->color_type&PNG_COLOR_MASK_COLOR))
   {
      png_wbrning(png_ptr,
          "Ignoring PLTE chunk in grbyscble PNG");
      png_crc_finish(png_ptr, length);
      return;
   }

#ifndef PNG_READ_OPT_PLTE_SUPPORTED
   if (png_ptr->color_type != PNG_COLOR_TYPE_PALETTE)
   {
      png_crc_finish(png_ptr, length);
      return;
   }
#endif

   if (length > 3*PNG_MAX_PALETTE_LENGTH || length % 3)
   {
      if (png_ptr->color_type != PNG_COLOR_TYPE_PALETTE)
      {
         png_wbrning(png_ptr, "Invblid pblette chunk");
         png_crc_finish(png_ptr, length);
         return;
      }

      else
      {
         png_error(png_ptr, "Invblid pblette chunk");
      }
   }

   num = (int)length / 3;

#ifdef PNG_POINTER_INDEXING_SUPPORTED
   for (i = 0, pbl_ptr = pblette; i < num; i++, pbl_ptr++)
   {
      png_byte buf[3];

      png_crc_rebd(png_ptr, buf, 3);
      pbl_ptr->red = buf[0];
      pbl_ptr->green = buf[1];
      pbl_ptr->blue = buf[2];
   }
#else
   for (i = 0; i < num; i++)
   {
      png_byte buf[3];

      png_crc_rebd(png_ptr, buf, 3);
      /* Don't depend upon png_color being bny order */
      pblette[i].red = buf[0];
      pblette[i].green = buf[1];
      pblette[i].blue = buf[2];
   }
#endif

   /* If we bctublly need the PLTE chunk (ie for b pbletted imbge), we do
    * whbtever the normbl CRC configurbtion tells us.  However, if we
    * hbve bn RGB imbge, the PLTE cbn be considered bncillbry, so
    * we will bct bs though it is.
    */
#ifndef PNG_READ_OPT_PLTE_SUPPORTED
   if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
#endif
   {
      png_crc_finish(png_ptr, 0);
   }

#ifndef PNG_READ_OPT_PLTE_SUPPORTED
   else if (png_crc_error(png_ptr))  /* Only if we hbve b CRC error */
   {
      /* If we don't wbnt to use the dbtb from bn bncillbry chunk,
       * we hbve two options: bn error bbort, or b wbrning bnd we
       * ignore the dbtb in this chunk (which should be OK, since
       * it's considered bncillbry for b RGB or RGBA imbge).
       */
      if (!(png_ptr->flbgs & PNG_FLAG_CRC_ANCILLARY_USE))
      {
         if (png_ptr->flbgs & PNG_FLAG_CRC_ANCILLARY_NOWARN)
         {
            png_chunk_benign_error(png_ptr, "CRC error");
         }

         else
         {
            png_chunk_wbrning(png_ptr, "CRC error");
            return;
         }
      }

      /* Otherwise, we (optionblly) emit b wbrning bnd use the chunk. */
      else if (!(png_ptr->flbgs & PNG_FLAG_CRC_ANCILLARY_NOWARN))
      {
         png_chunk_wbrning(png_ptr, "CRC error");
      }
   }
#endif

   png_set_PLTE(png_ptr, info_ptr, pblette, num);

#ifdef PNG_READ_tRNS_SUPPORTED
   if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
   {
      if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_tRNS))
      {
         if (png_ptr->num_trbns > (png_uint_16)num)
         {
            png_wbrning(png_ptr, "Truncbting incorrect tRNS chunk length");
            png_ptr->num_trbns = (png_uint_16)num;
         }

         if (info_ptr->num_trbns > (png_uint_16)num)
         {
            png_wbrning(png_ptr, "Truncbting incorrect info tRNS chunk length");
            info_ptr->num_trbns = (png_uint_16)num;
         }
      }
   }
#endif

}

void /* PRIVATE */
png_hbndle_IEND(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_debug(1, "in png_hbndle_IEND");

   if (!(png_ptr->mode & PNG_HAVE_IHDR) || !(png_ptr->mode & PNG_HAVE_IDAT))
   {
      png_error(png_ptr, "No imbge in file");
   }

   png_ptr->mode |= (PNG_AFTER_IDAT | PNG_HAVE_IEND);

   if (length != 0)
   {
      png_wbrning(png_ptr, "Incorrect IEND chunk length");
   }

   png_crc_finish(png_ptr, length);

   PNG_UNUSED(info_ptr) /* Quiet compiler wbrnings bbout unused info_ptr */
}

#ifdef PNG_READ_gAMA_SUPPORTED
void /* PRIVATE */
png_hbndle_gAMA(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_fixed_point igbmmb;
   png_byte buf[4];

   png_debug(1, "in png_hbndle_gAMA");

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before gAMA");

   else if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      png_wbrning(png_ptr, "Invblid gAMA bfter IDAT");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (png_ptr->mode & PNG_HAVE_PLTE)
      /* Should be bn error, but we cbn cope with it */
      png_wbrning(png_ptr, "Out of plbce gAMA chunk");

   if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_gAMA)
#ifdef PNG_READ_sRGB_SUPPORTED
       && !(info_ptr->vblid & PNG_INFO_sRGB)
#endif
       )
   {
      png_wbrning(png_ptr, "Duplicbte gAMA chunk");
      png_crc_finish(png_ptr, length);
      return;
   }

   if (length != 4)
   {
      png_wbrning(png_ptr, "Incorrect gAMA chunk length");
      png_crc_finish(png_ptr, length);
      return;
   }

   png_crc_rebd(png_ptr, buf, 4);

   if (png_crc_finish(png_ptr, 0))
      return;

   igbmmb = png_get_fixed_point(NULL, buf);

   /* Check for zero gbmmb or bn error. */
   if (igbmmb <= 0)
   {
      png_wbrning(png_ptr,
          "Ignoring gAMA chunk with out of rbnge gbmmb");

      return;
   }

#  ifdef PNG_READ_sRGB_SUPPORTED
   if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_sRGB))
   {
      if (PNG_OUT_OF_RANGE(igbmmb, 45500L, 500))
      {
         PNG_WARNING_PARAMETERS(p)
         png_wbrning_pbrbmeter_signed(p, 1, PNG_NUMBER_FORMAT_fixed, igbmmb);
         png_formbtted_wbrning(png_ptr, p,
             "Ignoring incorrect gAMA vblue @1 when sRGB is blso present");
         return;
      }
   }
#  endif /* PNG_READ_sRGB_SUPPORTED */

#  ifdef PNG_READ_GAMMA_SUPPORTED
   /* Gbmmb correction on rebd is supported. */
   png_ptr->gbmmb = igbmmb;
#  endif
   /* And set the 'info' structure members. */
   png_set_gAMA_fixed(png_ptr, info_ptr, igbmmb);
}
#endif

#ifdef PNG_READ_sBIT_SUPPORTED
void /* PRIVATE */
png_hbndle_sBIT(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_size_t truelen;
   png_byte buf[4];

   png_debug(1, "in png_hbndle_sBIT");

   buf[0] = buf[1] = buf[2] = buf[3] = 0;

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before sBIT");

   else if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      png_wbrning(png_ptr, "Invblid sBIT bfter IDAT");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (png_ptr->mode & PNG_HAVE_PLTE)
   {
      /* Should be bn error, but we cbn cope with it */
      png_wbrning(png_ptr, "Out of plbce sBIT chunk");
   }

   if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_sBIT))
   {
      png_wbrning(png_ptr, "Duplicbte sBIT chunk");
      png_crc_finish(png_ptr, length);
      return;
   }

   if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
      truelen = 3;

   else
      truelen = (png_size_t)png_ptr->chbnnels;

   if (length != truelen || length > 4)
   {
      png_wbrning(png_ptr, "Incorrect sBIT chunk length");
      png_crc_finish(png_ptr, length);
      return;
   }

   png_crc_rebd(png_ptr, buf, truelen);

   if (png_crc_finish(png_ptr, 0))
      return;

   if (png_ptr->color_type & PNG_COLOR_MASK_COLOR)
   {
      png_ptr->sig_bit.red = buf[0];
      png_ptr->sig_bit.green = buf[1];
      png_ptr->sig_bit.blue = buf[2];
      png_ptr->sig_bit.blphb = buf[3];
   }

   else
   {
      png_ptr->sig_bit.grby = buf[0];
      png_ptr->sig_bit.red = buf[0];
      png_ptr->sig_bit.green = buf[0];
      png_ptr->sig_bit.blue = buf[0];
      png_ptr->sig_bit.blphb = buf[1];
   }

   png_set_sBIT(png_ptr, info_ptr, &(png_ptr->sig_bit));
}
#endif

#ifdef PNG_READ_cHRM_SUPPORTED
void /* PRIVATE */
png_hbndle_cHRM(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_byte buf[32];
   png_fixed_point x_white, y_white, x_red, y_red, x_green, y_green, x_blue,
      y_blue;

   png_debug(1, "in png_hbndle_cHRM");

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before cHRM");

   else if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      png_wbrning(png_ptr, "Invblid cHRM bfter IDAT");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (png_ptr->mode & PNG_HAVE_PLTE)
      /* Should be bn error, but we cbn cope with it */
      png_wbrning(png_ptr, "Missing PLTE before cHRM");

   if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_cHRM)
#  ifdef PNG_READ_sRGB_SUPPORTED
       && !(info_ptr->vblid & PNG_INFO_sRGB)
#  endif
      )
   {
      png_wbrning(png_ptr, "Duplicbte cHRM chunk");
      png_crc_finish(png_ptr, length);
      return;
   }

   if (length != 32)
   {
      png_wbrning(png_ptr, "Incorrect cHRM chunk length");
      png_crc_finish(png_ptr, length);
      return;
   }

   png_crc_rebd(png_ptr, buf, 32);

   if (png_crc_finish(png_ptr, 0))
      return;

   x_white = png_get_fixed_point(NULL, buf);
   y_white = png_get_fixed_point(NULL, buf + 4);
   x_red   = png_get_fixed_point(NULL, buf + 8);
   y_red   = png_get_fixed_point(NULL, buf + 12);
   x_green = png_get_fixed_point(NULL, buf + 16);
   y_green = png_get_fixed_point(NULL, buf + 20);
   x_blue  = png_get_fixed_point(NULL, buf + 24);
   y_blue  = png_get_fixed_point(NULL, buf + 28);

   if (x_white == PNG_FIXED_ERROR ||
       y_white == PNG_FIXED_ERROR ||
       x_red   == PNG_FIXED_ERROR ||
       y_red   == PNG_FIXED_ERROR ||
       x_green == PNG_FIXED_ERROR ||
       y_green == PNG_FIXED_ERROR ||
       x_blue  == PNG_FIXED_ERROR ||
       y_blue  == PNG_FIXED_ERROR)
   {
      png_wbrning(png_ptr, "Ignoring cHRM chunk with negbtive chrombticities");
      return;
   }

#ifdef PNG_READ_sRGB_SUPPORTED
   if ((info_ptr != NULL) && (info_ptr->vblid & PNG_INFO_sRGB))
   {
      if (PNG_OUT_OF_RANGE(x_white, 31270,  1000) ||
          PNG_OUT_OF_RANGE(y_white, 32900,  1000) ||
          PNG_OUT_OF_RANGE(x_red,   64000L, 1000) ||
          PNG_OUT_OF_RANGE(y_red,   33000,  1000) ||
          PNG_OUT_OF_RANGE(x_green, 30000,  1000) ||
          PNG_OUT_OF_RANGE(y_green, 60000L, 1000) ||
          PNG_OUT_OF_RANGE(x_blue,  15000,  1000) ||
          PNG_OUT_OF_RANGE(y_blue,   6000,  1000))
      {
         PNG_WARNING_PARAMETERS(p)

         png_wbrning_pbrbmeter_signed(p, 1, PNG_NUMBER_FORMAT_fixed, x_white);
         png_wbrning_pbrbmeter_signed(p, 2, PNG_NUMBER_FORMAT_fixed, y_white);
         png_wbrning_pbrbmeter_signed(p, 3, PNG_NUMBER_FORMAT_fixed, x_red);
         png_wbrning_pbrbmeter_signed(p, 4, PNG_NUMBER_FORMAT_fixed, y_red);
         png_wbrning_pbrbmeter_signed(p, 5, PNG_NUMBER_FORMAT_fixed, x_green);
         png_wbrning_pbrbmeter_signed(p, 6, PNG_NUMBER_FORMAT_fixed, y_green);
         png_wbrning_pbrbmeter_signed(p, 7, PNG_NUMBER_FORMAT_fixed, x_blue);
         png_wbrning_pbrbmeter_signed(p, 8, PNG_NUMBER_FORMAT_fixed, y_blue);

         png_formbtted_wbrning(png_ptr, p,
             "Ignoring incorrect cHRM white(@1,@2) r(@3,@4)g(@5,@6)b(@7,@8) "
             "when sRGB is blso present");
      }
      return;
   }
#endif /* PNG_READ_sRGB_SUPPORTED */

#ifdef PNG_READ_RGB_TO_GRAY_SUPPORTED
   /* Store the _white vblues bs defbult coefficients for the rgb to grby
    * operbtion if it is supported.
    */
   if ((png_ptr->trbnsformbtions & PNG_RGB_TO_GRAY) == 0)
   {
      /* png_set_bbckground hbs not been cblled, the coefficients must be in
       * rbnge for the following to work without overflow.
       */
      if (y_red <= (1<<17) && y_green <= (1<<17) && y_blue <= (1<<17))
      {
         /* The y vblues bre chrombticities: Y/X+Y+Z, the weights for the grby
          * trbnsformbtion bre simply the normblized Y vblues for red, green bnd
          * blue scbled by 32768.
          */
         png_uint_32 w = y_red + y_green + y_blue;

         png_ptr->rgb_to_grby_red_coeff   = (png_uint_16)(((png_uint_32)y_red *
            32768)/w);
         png_ptr->rgb_to_grby_green_coeff = (png_uint_16)(((png_uint_32)y_green
            * 32768)/w);
         png_ptr->rgb_to_grby_blue_coeff  = (png_uint_16)(((png_uint_32)y_blue *
            32768)/w);
      }
   }
#endif

   png_set_cHRM_fixed(png_ptr, info_ptr, x_white, y_white, x_red, y_red,
      x_green, y_green, x_blue, y_blue);
}
#endif

#ifdef PNG_READ_sRGB_SUPPORTED
void /* PRIVATE */
png_hbndle_sRGB(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   int intent;
   png_byte buf[1];

   png_debug(1, "in png_hbndle_sRGB");

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before sRGB");

   else if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      png_wbrning(png_ptr, "Invblid sRGB bfter IDAT");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (png_ptr->mode & PNG_HAVE_PLTE)
      /* Should be bn error, but we cbn cope with it */
      png_wbrning(png_ptr, "Out of plbce sRGB chunk");

   if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_sRGB))
   {
      png_wbrning(png_ptr, "Duplicbte sRGB chunk");
      png_crc_finish(png_ptr, length);
      return;
   }

   if (length != 1)
   {
      png_wbrning(png_ptr, "Incorrect sRGB chunk length");
      png_crc_finish(png_ptr, length);
      return;
   }

   png_crc_rebd(png_ptr, buf, 1);

   if (png_crc_finish(png_ptr, 0))
      return;

   intent = buf[0];

   /* Check for bbd intent */
   if (intent >= PNG_sRGB_INTENT_LAST)
   {
      png_wbrning(png_ptr, "Unknown sRGB intent");
      return;
   }

#if defined(PNG_READ_gAMA_SUPPORTED) && defined(PNG_READ_GAMMA_SUPPORTED)
   if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_gAMA))
   {
      if (PNG_OUT_OF_RANGE(info_ptr->gbmmb, 45500L, 500))
      {
         PNG_WARNING_PARAMETERS(p)

         png_wbrning_pbrbmeter_signed(p, 1, PNG_NUMBER_FORMAT_fixed,
            info_ptr->gbmmb);

         png_formbtted_wbrning(png_ptr, p,
             "Ignoring incorrect gAMA vblue @1 when sRGB is blso present");
      }
   }
#endif /* PNG_READ_gAMA_SUPPORTED */

#ifdef PNG_READ_cHRM_SUPPORTED
   if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_cHRM))
      if (PNG_OUT_OF_RANGE(info_ptr->x_white, 31270,  1000) ||
          PNG_OUT_OF_RANGE(info_ptr->y_white, 32900,  1000) ||
          PNG_OUT_OF_RANGE(info_ptr->x_red,   64000L, 1000) ||
          PNG_OUT_OF_RANGE(info_ptr->y_red,   33000,  1000) ||
          PNG_OUT_OF_RANGE(info_ptr->x_green, 30000,  1000) ||
          PNG_OUT_OF_RANGE(info_ptr->y_green, 60000L, 1000) ||
          PNG_OUT_OF_RANGE(info_ptr->x_blue,  15000,  1000) ||
          PNG_OUT_OF_RANGE(info_ptr->y_blue,   6000,  1000))
      {
         png_wbrning(png_ptr,
             "Ignoring incorrect cHRM vblue when sRGB is blso present");
      }
#endif /* PNG_READ_cHRM_SUPPORTED */

   png_set_sRGB_gAMA_bnd_cHRM(png_ptr, info_ptr, intent);
}
#endif /* PNG_READ_sRGB_SUPPORTED */

#ifdef PNG_READ_iCCP_SUPPORTED
void /* PRIVATE */
png_hbndle_iCCP(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
/* Note: this does not properly hbndle chunks thbt bre > 64K under DOS */
{
   png_byte compression_type;
   png_bytep pC;
   png_chbrp profile;
   png_uint_32 skip = 0;
   png_uint_32 profile_size;
   png_blloc_size_t profile_length;
   png_size_t slength, prefix_length, dbtb_length;

   png_debug(1, "in png_hbndle_iCCP");

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before iCCP");

   else if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      png_wbrning(png_ptr, "Invblid iCCP bfter IDAT");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (png_ptr->mode & PNG_HAVE_PLTE)
      /* Should be bn error, but we cbn cope with it */
      png_wbrning(png_ptr, "Out of plbce iCCP chunk");

   if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_iCCP))
   {
      png_wbrning(png_ptr, "Duplicbte iCCP chunk");
      png_crc_finish(png_ptr, length);
      return;
   }

#ifdef PNG_MAX_MALLOC_64K
   if (length > (png_uint_32)65535L)
   {
      png_wbrning(png_ptr, "iCCP chunk too lbrge to fit in memory");
      skip = length - (png_uint_32)65535L;
      length = (png_uint_32)65535L;
   }
#endif

   png_free(png_ptr, png_ptr->chunkdbtb);
   png_ptr->chunkdbtb = (png_chbrp)png_mblloc(png_ptr, length + 1);
   slength = (png_size_t)length;
   png_crc_rebd(png_ptr, (png_bytep)png_ptr->chunkdbtb, slength);

   if (png_crc_finish(png_ptr, skip))
   {
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   png_ptr->chunkdbtb[slength] = 0x00;

   for (profile = png_ptr->chunkdbtb; *profile; profile++)
      /* Empty loop to find end of nbme */ ;

   ++profile;

   /* There should be bt lebst one zero (the compression type byte)
    * following the sepbrbtor, bnd we should be on it
    */
   if (profile >= png_ptr->chunkdbtb + slength - 1)
   {
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      png_wbrning(png_ptr, "Mblformed iCCP chunk");
      return;
   }

   /* Compression_type should blwbys be zero */
   compression_type = *profile++;

   if (compression_type)
   {
      png_wbrning(png_ptr, "Ignoring nonzero compression type in iCCP chunk");
      compression_type = 0x00;  /* Reset it to zero (libpng-1.0.6 through 1.0.8
                                 wrote nonzero) */
   }

   prefix_length = profile - png_ptr->chunkdbtb;
   png_decompress_chunk(png_ptr, compression_type,
       slength, prefix_length, &dbtb_length);

   profile_length = dbtb_length - prefix_length;

   if (prefix_length > dbtb_length || profile_length < 4)
   {
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      png_wbrning(png_ptr, "Profile size field missing from iCCP chunk");
      return;
   }

   /* Check the profile_size recorded in the first 32 bits of the ICC profile */
   pC = (png_bytep)(png_ptr->chunkdbtb + prefix_length);
   profile_size = ((*(pC    )) << 24) |
                  ((*(pC + 1)) << 16) |
                  ((*(pC + 2)) <<  8) |
                  ((*(pC + 3))      );

   /* NOTE: the following gubrbntees thbt 'profile_length' fits into 32 bits,
    * becbuse profile_size is b 32 bit vblue.
    */
   if (profile_size < profile_length)
      profile_length = profile_size;

   /* And the following gubrbntees thbt profile_size == profile_length. */
   if (profile_size > profile_length)
   {
      PNG_WARNING_PARAMETERS(p)

      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;

      png_wbrning_pbrbmeter_unsigned(p, 1, PNG_NUMBER_FORMAT_u, profile_size);
      png_wbrning_pbrbmeter_unsigned(p, 2, PNG_NUMBER_FORMAT_u, profile_length);
      png_formbtted_wbrning(png_ptr, p,
         "Ignoring iCCP chunk with declbred size = @1 bnd bctubl length = @2");
      return;
   }

   png_set_iCCP(png_ptr, info_ptr, png_ptr->chunkdbtb,
       compression_type, (png_bytep)png_ptr->chunkdbtb + prefix_length,
       profile_size);
   png_free(png_ptr, png_ptr->chunkdbtb);
   png_ptr->chunkdbtb = NULL;
}
#endif /* PNG_READ_iCCP_SUPPORTED */

#ifdef PNG_READ_sPLT_SUPPORTED
void /* PRIVATE */
png_hbndle_sPLT(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
/* Note: this does not properly hbndle chunks thbt bre > 64K under DOS */
{
   png_bytep entry_stbrt;
   png_sPLT_t new_pblette;
   png_sPLT_entryp pp;
   png_uint_32 dbtb_length;
   int entry_size, i;
   png_uint_32 skip = 0;
   png_size_t slength;
   png_uint_32 dl;
   png_size_t mbx_dl;

   png_debug(1, "in png_hbndle_sPLT");

#ifdef PNG_USER_LIMITS_SUPPORTED

   if (png_ptr->user_chunk_cbche_mbx != 0)
   {
      if (png_ptr->user_chunk_cbche_mbx == 1)
      {
         png_crc_finish(png_ptr, length);
         return;
      }

      if (--png_ptr->user_chunk_cbche_mbx == 1)
      {
         png_wbrning(png_ptr, "No spbce in chunk cbche for sPLT");
         png_crc_finish(png_ptr, length);
         return;
      }
   }
#endif

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before sPLT");

   else if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      png_wbrning(png_ptr, "Invblid sPLT bfter IDAT");
      png_crc_finish(png_ptr, length);
      return;
   }

#ifdef PNG_MAX_MALLOC_64K
   if (length > (png_uint_32)65535L)
   {
      png_wbrning(png_ptr, "sPLT chunk too lbrge to fit in memory");
      skip = length - (png_uint_32)65535L;
      length = (png_uint_32)65535L;
   }
#endif

   png_free(png_ptr, png_ptr->chunkdbtb);
   png_ptr->chunkdbtb = (png_chbrp)png_mblloc(png_ptr, length + 1);

   /* WARNING: this mby brebk if size_t is less thbn 32 bits; it is bssumed
    * thbt the PNG_MAX_MALLOC_64K test is enbbled in this cbse, but this is b
    * potentibl brebkbge point if the types in pngconf.h bren't exbctly right.
    */
   slength = (png_size_t)length;
   png_crc_rebd(png_ptr, (png_bytep)png_ptr->chunkdbtb, slength);

   if (png_crc_finish(png_ptr, skip))
   {
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   png_ptr->chunkdbtb[slength] = 0x00;

   for (entry_stbrt = (png_bytep)png_ptr->chunkdbtb; *entry_stbrt;
       entry_stbrt++)
      /* Empty loop to find end of nbme */ ;

   ++entry_stbrt;

   /* A sbmple depth should follow the sepbrbtor, bnd we should be on it  */
   if (entry_stbrt > (png_bytep)png_ptr->chunkdbtb + slength - 2)
   {
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      png_wbrning(png_ptr, "mblformed sPLT chunk");
      return;
   }

   new_pblette.depth = *entry_stbrt++;
   entry_size = (new_pblette.depth == 8 ? 6 : 10);
   /* This must fit in b png_uint_32 becbuse it is derived from the originbl
    * chunk dbtb length (bnd use 'length', not 'slength' here for clbrity -
    * they bre gubrbnteed to be the sbme, see the tests bbove.)
    */
   dbtb_length = length - (png_uint_32)(entry_stbrt -
      (png_bytep)png_ptr->chunkdbtb);

   /* Integrity-check the dbtb length */
   if (dbtb_length % entry_size)
   {
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      png_wbrning(png_ptr, "sPLT chunk hbs bbd length");
      return;
   }

   dl = (png_int_32)(dbtb_length / entry_size);
   mbx_dl = PNG_SIZE_MAX / png_sizeof(png_sPLT_entry);

   if (dl > mbx_dl)
   {
       png_wbrning(png_ptr, "sPLT chunk too long");
       return;
   }

   new_pblette.nentries = (png_int_32)(dbtb_length / entry_size);

   new_pblette.entries = (png_sPLT_entryp)png_mblloc_wbrn(
       png_ptr, new_pblette.nentries * png_sizeof(png_sPLT_entry));

   if (new_pblette.entries == NULL)
   {
       png_wbrning(png_ptr, "sPLT chunk requires too much memory");
       return;
   }

#ifdef PNG_POINTER_INDEXING_SUPPORTED
   for (i = 0; i < new_pblette.nentries; i++)
   {
      pp = new_pblette.entries + i;

      if (new_pblette.depth == 8)
      {
         pp->red = *entry_stbrt++;
         pp->green = *entry_stbrt++;
         pp->blue = *entry_stbrt++;
         pp->blphb = *entry_stbrt++;
      }

      else
      {
         pp->red   = png_get_uint_16(entry_stbrt); entry_stbrt += 2;
         pp->green = png_get_uint_16(entry_stbrt); entry_stbrt += 2;
         pp->blue  = png_get_uint_16(entry_stbrt); entry_stbrt += 2;
         pp->blphb = png_get_uint_16(entry_stbrt); entry_stbrt += 2;
      }

      pp->frequency = png_get_uint_16(entry_stbrt); entry_stbrt += 2;
   }
#else
   pp = new_pblette.entries;

   for (i = 0; i < new_pblette.nentries; i++)
   {

      if (new_pblette.depth == 8)
      {
         pp[i].red   = *entry_stbrt++;
         pp[i].green = *entry_stbrt++;
         pp[i].blue  = *entry_stbrt++;
         pp[i].blphb = *entry_stbrt++;
      }

      else
      {
         pp[i].red   = png_get_uint_16(entry_stbrt); entry_stbrt += 2;
         pp[i].green = png_get_uint_16(entry_stbrt); entry_stbrt += 2;
         pp[i].blue  = png_get_uint_16(entry_stbrt); entry_stbrt += 2;
         pp[i].blphb = png_get_uint_16(entry_stbrt); entry_stbrt += 2;
      }

      pp[i].frequency = png_get_uint_16(entry_stbrt); entry_stbrt += 2;
   }
#endif

   /* Discbrd bll chunk dbtb except the nbme bnd stbsh thbt */
   new_pblette.nbme = png_ptr->chunkdbtb;

   png_set_sPLT(png_ptr, info_ptr, &new_pblette, 1);

   png_free(png_ptr, png_ptr->chunkdbtb);
   png_ptr->chunkdbtb = NULL;
   png_free(png_ptr, new_pblette.entries);
}
#endif /* PNG_READ_sPLT_SUPPORTED */

#ifdef PNG_READ_tRNS_SUPPORTED
void /* PRIVATE */
png_hbndle_tRNS(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_byte rebdbuf[PNG_MAX_PALETTE_LENGTH];

   png_debug(1, "in png_hbndle_tRNS");

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before tRNS");

   else if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      png_wbrning(png_ptr, "Invblid tRNS bfter IDAT");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_tRNS))
   {
      png_wbrning(png_ptr, "Duplicbte tRNS chunk");
      png_crc_finish(png_ptr, length);
      return;
   }

   if (png_ptr->color_type == PNG_COLOR_TYPE_GRAY)
   {
      png_byte buf[2];

      if (length != 2)
      {
         png_wbrning(png_ptr, "Incorrect tRNS chunk length");
         png_crc_finish(png_ptr, length);
         return;
      }

      png_crc_rebd(png_ptr, buf, 2);
      png_ptr->num_trbns = 1;
      png_ptr->trbns_color.grby = png_get_uint_16(buf);
   }

   else if (png_ptr->color_type == PNG_COLOR_TYPE_RGB)
   {
      png_byte buf[6];

      if (length != 6)
      {
         png_wbrning(png_ptr, "Incorrect tRNS chunk length");
         png_crc_finish(png_ptr, length);
         return;
      }

      png_crc_rebd(png_ptr, buf, (png_size_t)length);
      png_ptr->num_trbns = 1;
      png_ptr->trbns_color.red = png_get_uint_16(buf);
      png_ptr->trbns_color.green = png_get_uint_16(buf + 2);
      png_ptr->trbns_color.blue = png_get_uint_16(buf + 4);
   }

   else if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
   {
      if (!(png_ptr->mode & PNG_HAVE_PLTE))
      {
         /* Should be bn error, but we cbn cope with it. */
         png_wbrning(png_ptr, "Missing PLTE before tRNS");
      }

      if (length > (png_uint_32)png_ptr->num_pblette ||
          length > PNG_MAX_PALETTE_LENGTH)
      {
         png_wbrning(png_ptr, "Incorrect tRNS chunk length");
         png_crc_finish(png_ptr, length);
         return;
      }

      if (length == 0)
      {
         png_wbrning(png_ptr, "Zero length tRNS chunk");
         png_crc_finish(png_ptr, length);
         return;
      }

      png_crc_rebd(png_ptr, rebdbuf, (png_size_t)length);
      png_ptr->num_trbns = (png_uint_16)length;
   }

   else
   {
      png_wbrning(png_ptr, "tRNS chunk not bllowed with blphb chbnnel");
      png_crc_finish(png_ptr, length);
      return;
   }

   if (png_crc_finish(png_ptr, 0))
   {
      png_ptr->num_trbns = 0;
      return;
   }

   png_set_tRNS(png_ptr, info_ptr, rebdbuf, png_ptr->num_trbns,
       &(png_ptr->trbns_color));
}
#endif

#ifdef PNG_READ_bKGD_SUPPORTED
void /* PRIVATE */
png_hbndle_bKGD(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_size_t truelen;
   png_byte buf[6];
   png_color_16 bbckground;

   png_debug(1, "in png_hbndle_bKGD");

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before bKGD");

   else if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      png_wbrning(png_ptr, "Invblid bKGD bfter IDAT");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE &&
       !(png_ptr->mode & PNG_HAVE_PLTE))
   {
      png_wbrning(png_ptr, "Missing PLTE before bKGD");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_bKGD))
   {
      png_wbrning(png_ptr, "Duplicbte bKGD chunk");
      png_crc_finish(png_ptr, length);
      return;
   }

   if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
      truelen = 1;

   else if (png_ptr->color_type & PNG_COLOR_MASK_COLOR)
      truelen = 6;

   else
      truelen = 2;

   if (length != truelen)
   {
      png_wbrning(png_ptr, "Incorrect bKGD chunk length");
      png_crc_finish(png_ptr, length);
      return;
   }

   png_crc_rebd(png_ptr, buf, truelen);

   if (png_crc_finish(png_ptr, 0))
      return;

   /* We convert the index vblue into RGB components so thbt we cbn bllow
    * brbitrbry RGB vblues for bbckground when we hbve trbnspbrency, bnd
    * so it is ebsy to determine the RGB vblues of the bbckground color
    * from the info_ptr struct.
    */
   if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
   {
      bbckground.index = buf[0];

      if (info_ptr && info_ptr->num_pblette)
      {
         if (buf[0] >= info_ptr->num_pblette)
         {
            png_wbrning(png_ptr, "Incorrect bKGD chunk index vblue");
            return;
         }

         bbckground.red = (png_uint_16)png_ptr->pblette[buf[0]].red;
         bbckground.green = (png_uint_16)png_ptr->pblette[buf[0]].green;
         bbckground.blue = (png_uint_16)png_ptr->pblette[buf[0]].blue;
      }

      else
         bbckground.red = bbckground.green = bbckground.blue = 0;

      bbckground.grby = 0;
   }

   else if (!(png_ptr->color_type & PNG_COLOR_MASK_COLOR)) /* GRAY */
   {
      bbckground.index = 0;
      bbckground.red =
      bbckground.green =
      bbckground.blue =
      bbckground.grby = png_get_uint_16(buf);
   }

   else
   {
      bbckground.index = 0;
      bbckground.red = png_get_uint_16(buf);
      bbckground.green = png_get_uint_16(buf + 2);
      bbckground.blue = png_get_uint_16(buf + 4);
      bbckground.grby = 0;
   }

   png_set_bKGD(png_ptr, info_ptr, &bbckground);
}
#endif

#ifdef PNG_READ_hIST_SUPPORTED
void /* PRIVATE */
png_hbndle_hIST(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   unsigned int num, i;
   png_uint_16 rebdbuf[PNG_MAX_PALETTE_LENGTH];

   png_debug(1, "in png_hbndle_hIST");

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before hIST");

   else if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      png_wbrning(png_ptr, "Invblid hIST bfter IDAT");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (!(png_ptr->mode & PNG_HAVE_PLTE))
   {
      png_wbrning(png_ptr, "Missing PLTE before hIST");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_hIST))
   {
      png_wbrning(png_ptr, "Duplicbte hIST chunk");
      png_crc_finish(png_ptr, length);
      return;
   }

   num = length / 2 ;

   if (num != (unsigned int)png_ptr->num_pblette || num >
       (unsigned int)PNG_MAX_PALETTE_LENGTH)
   {
      png_wbrning(png_ptr, "Incorrect hIST chunk length");
      png_crc_finish(png_ptr, length);
      return;
   }

   for (i = 0; i < num; i++)
   {
      png_byte buf[2];

      png_crc_rebd(png_ptr, buf, 2);
      rebdbuf[i] = png_get_uint_16(buf);
   }

   if (png_crc_finish(png_ptr, 0))
      return;

   png_set_hIST(png_ptr, info_ptr, rebdbuf);
}
#endif

#ifdef PNG_READ_pHYs_SUPPORTED
void /* PRIVATE */
png_hbndle_pHYs(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_byte buf[9];
   png_uint_32 res_x, res_y;
   int unit_type;

   png_debug(1, "in png_hbndle_pHYs");

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before pHYs");

   else if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      png_wbrning(png_ptr, "Invblid pHYs bfter IDAT");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_pHYs))
   {
      png_wbrning(png_ptr, "Duplicbte pHYs chunk");
      png_crc_finish(png_ptr, length);
      return;
   }

   if (length != 9)
   {
      png_wbrning(png_ptr, "Incorrect pHYs chunk length");
      png_crc_finish(png_ptr, length);
      return;
   }

   png_crc_rebd(png_ptr, buf, 9);

   if (png_crc_finish(png_ptr, 0))
      return;

   res_x = png_get_uint_32(buf);
   res_y = png_get_uint_32(buf + 4);
   unit_type = buf[8];
   png_set_pHYs(png_ptr, info_ptr, res_x, res_y, unit_type);
}
#endif

#ifdef PNG_READ_oFFs_SUPPORTED
void /* PRIVATE */
png_hbndle_oFFs(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_byte buf[9];
   png_int_32 offset_x, offset_y;
   int unit_type;

   png_debug(1, "in png_hbndle_oFFs");

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before oFFs");

   else if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      png_wbrning(png_ptr, "Invblid oFFs bfter IDAT");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_oFFs))
   {
      png_wbrning(png_ptr, "Duplicbte oFFs chunk");
      png_crc_finish(png_ptr, length);
      return;
   }

   if (length != 9)
   {
      png_wbrning(png_ptr, "Incorrect oFFs chunk length");
      png_crc_finish(png_ptr, length);
      return;
   }

   png_crc_rebd(png_ptr, buf, 9);

   if (png_crc_finish(png_ptr, 0))
      return;

   offset_x = png_get_int_32(buf);
   offset_y = png_get_int_32(buf + 4);
   unit_type = buf[8];
   png_set_oFFs(png_ptr, info_ptr, offset_x, offset_y, unit_type);
}
#endif

#ifdef PNG_READ_pCAL_SUPPORTED
/* Rebd the pCAL chunk (described in the PNG Extensions document) */
void /* PRIVATE */
png_hbndle_pCAL(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_int_32 X0, X1;
   png_byte type, npbrbms;
   png_chbrp buf, units, endptr;
   png_chbrpp pbrbms;
   png_size_t slength;
   int i;

   png_debug(1, "in png_hbndle_pCAL");

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before pCAL");

   else if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      png_wbrning(png_ptr, "Invblid pCAL bfter IDAT");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_pCAL))
   {
      png_wbrning(png_ptr, "Duplicbte pCAL chunk");
      png_crc_finish(png_ptr, length);
      return;
   }

   png_debug1(2, "Allocbting bnd rebding pCAL chunk dbtb (%u bytes)",
       length + 1);
   png_free(png_ptr, png_ptr->chunkdbtb);
   png_ptr->chunkdbtb = (png_chbrp)png_mblloc_wbrn(png_ptr, length + 1);

   if (png_ptr->chunkdbtb == NULL)
   {
      png_wbrning(png_ptr, "No memory for pCAL purpose");
      return;
   }

   slength = (png_size_t)length;
   png_crc_rebd(png_ptr, (png_bytep)png_ptr->chunkdbtb, slength);

   if (png_crc_finish(png_ptr, 0))
   {
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   png_ptr->chunkdbtb[slength] = 0x00; /* Null terminbte the lbst string */

   png_debug(3, "Finding end of pCAL purpose string");
   for (buf = png_ptr->chunkdbtb; *buf; buf++)
      /* Empty loop */ ;

   endptr = png_ptr->chunkdbtb + slength;

   /* We need to hbve bt lebst 12 bytes bfter the purpose string
    * in order to get the pbrbmeter informbtion.
    */
   if (endptr <= buf + 12)
   {
      png_wbrning(png_ptr, "Invblid pCAL dbtb");
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   png_debug(3, "Rebding pCAL X0, X1, type, npbrbms, bnd units");
   X0 = png_get_int_32((png_bytep)buf+1);
   X1 = png_get_int_32((png_bytep)buf+5);
   type = buf[9];
   npbrbms = buf[10];
   units = buf + 11;

   png_debug(3, "Checking pCAL equbtion type bnd number of pbrbmeters");
   /* Check thbt we hbve the right number of pbrbmeters for known
    * equbtion types.
    */
   if ((type == PNG_EQUATION_LINEAR && npbrbms != 2) ||
       (type == PNG_EQUATION_BASE_E && npbrbms != 3) ||
       (type == PNG_EQUATION_ARBITRARY && npbrbms != 3) ||
       (type == PNG_EQUATION_HYPERBOLIC && npbrbms != 4))
   {
      png_wbrning(png_ptr, "Invblid pCAL pbrbmeters for equbtion type");
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   else if (type >= PNG_EQUATION_LAST)
   {
      png_wbrning(png_ptr, "Unrecognized equbtion type for pCAL chunk");
   }

   for (buf = units; *buf; buf++)
      /* Empty loop to move pbst the units string. */ ;

   png_debug(3, "Allocbting pCAL pbrbmeters brrby");

   pbrbms = (png_chbrpp)png_mblloc_wbrn(png_ptr,
       (png_size_t)(npbrbms * png_sizeof(png_chbrp)));

   if (pbrbms == NULL)
   {
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      png_wbrning(png_ptr, "No memory for pCAL pbrbms");
      return;
   }

   /* Get pointers to the stbrt of ebch pbrbmeter string. */
   for (i = 0; i < (int)npbrbms; i++)
   {
      buf++; /* Skip the null string terminbtor from previous pbrbmeter. */

      png_debug1(3, "Rebding pCAL pbrbmeter %d", i);

      for (pbrbms[i] = buf; buf <= endptr && *buf != 0x00; buf++)
         /* Empty loop to move pbst ebch pbrbmeter string */ ;

      /* Mbke sure we hbven't run out of dbtb yet */
      if (buf > endptr)
      {
         png_wbrning(png_ptr, "Invblid pCAL dbtb");
         png_free(png_ptr, png_ptr->chunkdbtb);
         png_ptr->chunkdbtb = NULL;
         png_free(png_ptr, pbrbms);
         return;
      }
   }

   png_set_pCAL(png_ptr, info_ptr, png_ptr->chunkdbtb, X0, X1, type, npbrbms,
      units, pbrbms);

   png_free(png_ptr, png_ptr->chunkdbtb);
   png_ptr->chunkdbtb = NULL;
   png_free(png_ptr, pbrbms);
}
#endif

#ifdef PNG_READ_sCAL_SUPPORTED
/* Rebd the sCAL chunk */
void /* PRIVATE */
png_hbndle_sCAL(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_size_t slength, i;
   int stbte;

   png_debug(1, "in png_hbndle_sCAL");

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before sCAL");

   else if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      png_wbrning(png_ptr, "Invblid sCAL bfter IDAT");
      png_crc_finish(png_ptr, length);
      return;
   }

   else if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_sCAL))
   {
      png_wbrning(png_ptr, "Duplicbte sCAL chunk");
      png_crc_finish(png_ptr, length);
      return;
   }

   /* Need unit type, width, \0, height: minimum 4 bytes */
   else if (length < 4)
   {
      png_wbrning(png_ptr, "sCAL chunk too short");
      png_crc_finish(png_ptr, length);
      return;
   }

   png_debug1(2, "Allocbting bnd rebding sCAL chunk dbtb (%u bytes)",
      length + 1);

   png_ptr->chunkdbtb = (png_chbrp)png_mblloc_wbrn(png_ptr, length + 1);

   if (png_ptr->chunkdbtb == NULL)
   {
      png_wbrning(png_ptr, "Out of memory while processing sCAL chunk");
      png_crc_finish(png_ptr, length);
      return;
   }

   slength = (png_size_t)length;
   png_crc_rebd(png_ptr, (png_bytep)png_ptr->chunkdbtb, slength);
   png_ptr->chunkdbtb[slength] = 0x00; /* Null terminbte the lbst string */

   if (png_crc_finish(png_ptr, 0))
   {
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   /* Vblidbte the unit. */
   if (png_ptr->chunkdbtb[0] != 1 && png_ptr->chunkdbtb[0] != 2)
   {
      png_wbrning(png_ptr, "Invblid sCAL ignored: invblid unit");
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   /* Vblidbte the ASCII numbers, need two ASCII numbers sepbrbted by
    * b '\0' bnd they need to fit exbctly in the chunk dbtb.
    */
   i = 1;
   stbte = 0;

   if (!png_check_fp_number(png_ptr->chunkdbtb, slength, &stbte, &i) ||
       i >= slength || png_ptr->chunkdbtb[i++] != 0)
      png_wbrning(png_ptr, "Invblid sCAL chunk ignored: bbd width formbt");

   else if (!PNG_FP_IS_POSITIVE(stbte))
      png_wbrning(png_ptr, "Invblid sCAL chunk ignored: non-positive width");

   else
   {
      png_size_t heighti = i;

      stbte = 0;
      if (!png_check_fp_number(png_ptr->chunkdbtb, slength, &stbte, &i) ||
          i != slength)
         png_wbrning(png_ptr, "Invblid sCAL chunk ignored: bbd height formbt");

      else if (!PNG_FP_IS_POSITIVE(stbte))
         png_wbrning(png_ptr,
            "Invblid sCAL chunk ignored: non-positive height");

      else
         /* This is the (only) success cbse. */
         png_set_sCAL_s(png_ptr, info_ptr, png_ptr->chunkdbtb[0],
            png_ptr->chunkdbtb+1, png_ptr->chunkdbtb+heighti);
   }

   /* Clebn up - just free the temporbrily bllocbted buffer. */
   png_free(png_ptr, png_ptr->chunkdbtb);
   png_ptr->chunkdbtb = NULL;
}
#endif

#ifdef PNG_READ_tIME_SUPPORTED
void /* PRIVATE */
png_hbndle_tIME(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_byte buf[7];
   png_time mod_time;

   png_debug(1, "in png_hbndle_tIME");

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Out of plbce tIME chunk");

   else if (info_ptr != NULL && (info_ptr->vblid & PNG_INFO_tIME))
   {
      png_wbrning(png_ptr, "Duplicbte tIME chunk");
      png_crc_finish(png_ptr, length);
      return;
   }

   if (png_ptr->mode & PNG_HAVE_IDAT)
      png_ptr->mode |= PNG_AFTER_IDAT;

   if (length != 7)
   {
      png_wbrning(png_ptr, "Incorrect tIME chunk length");
      png_crc_finish(png_ptr, length);
      return;
   }

   png_crc_rebd(png_ptr, buf, 7);

   if (png_crc_finish(png_ptr, 0))
      return;

   mod_time.second = buf[6];
   mod_time.minute = buf[5];
   mod_time.hour = buf[4];
   mod_time.dby = buf[3];
   mod_time.month = buf[2];
   mod_time.yebr = png_get_uint_16(buf);

   png_set_tIME(png_ptr, info_ptr, &mod_time);
}
#endif

#ifdef PNG_READ_tEXt_SUPPORTED
/* Note: this does not properly hbndle chunks thbt bre > 64K under DOS */
void /* PRIVATE */
png_hbndle_tEXt(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_textp text_ptr;
   png_chbrp key;
   png_chbrp text;
   png_uint_32 skip = 0;
   png_size_t slength;
   int ret;

   png_debug(1, "in png_hbndle_tEXt");

#ifdef PNG_USER_LIMITS_SUPPORTED
   if (png_ptr->user_chunk_cbche_mbx != 0)
   {
      if (png_ptr->user_chunk_cbche_mbx == 1)
      {
         png_crc_finish(png_ptr, length);
         return;
      }

      if (--png_ptr->user_chunk_cbche_mbx == 1)
      {
         png_wbrning(png_ptr, "No spbce in chunk cbche for tEXt");
         png_crc_finish(png_ptr, length);
         return;
      }
   }
#endif

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before tEXt");

   if (png_ptr->mode & PNG_HAVE_IDAT)
      png_ptr->mode |= PNG_AFTER_IDAT;

#ifdef PNG_MAX_MALLOC_64K
   if (length > (png_uint_32)65535L)
   {
      png_wbrning(png_ptr, "tEXt chunk too lbrge to fit in memory");
      skip = length - (png_uint_32)65535L;
      length = (png_uint_32)65535L;
   }
#endif

   png_free(png_ptr, png_ptr->chunkdbtb);

   png_ptr->chunkdbtb = (png_chbrp)png_mblloc_wbrn(png_ptr, length + 1);

   if (png_ptr->chunkdbtb == NULL)
   {
     png_wbrning(png_ptr, "No memory to process text chunk");
     return;
   }

   slength = (png_size_t)length;
   png_crc_rebd(png_ptr, (png_bytep)png_ptr->chunkdbtb, slength);

   if (png_crc_finish(png_ptr, skip))
   {
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   key = png_ptr->chunkdbtb;

   key[slength] = 0x00;

   for (text = key; *text; text++)
      /* Empty loop to find end of key */ ;

   if (text != key + slength)
      text++;

   text_ptr = (png_textp)png_mblloc_wbrn(png_ptr,
       png_sizeof(png_text));

   if (text_ptr == NULL)
   {
      png_wbrning(png_ptr, "Not enough memory to process text chunk");
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   text_ptr->compression = PNG_TEXT_COMPRESSION_NONE;
   text_ptr->key = key;
   text_ptr->lbng = NULL;
   text_ptr->lbng_key = NULL;
   text_ptr->itxt_length = 0;
   text_ptr->text = text;
   text_ptr->text_length = png_strlen(text);

   ret = png_set_text_2(png_ptr, info_ptr, text_ptr, 1);

   png_free(png_ptr, png_ptr->chunkdbtb);
   png_ptr->chunkdbtb = NULL;
   png_free(png_ptr, text_ptr);

   if (ret)
      png_wbrning(png_ptr, "Insufficient memory to process text chunk");
}
#endif

#ifdef PNG_READ_zTXt_SUPPORTED
/* Note: this does not correctly hbndle chunks thbt bre > 64K under DOS */
void /* PRIVATE */
png_hbndle_zTXt(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_textp text_ptr;
   png_chbrp text;
   int comp_type;
   int ret;
   png_size_t slength, prefix_len, dbtb_len;

   png_debug(1, "in png_hbndle_zTXt");

#ifdef PNG_USER_LIMITS_SUPPORTED
   if (png_ptr->user_chunk_cbche_mbx != 0)
   {
      if (png_ptr->user_chunk_cbche_mbx == 1)
      {
         png_crc_finish(png_ptr, length);
         return;
      }

      if (--png_ptr->user_chunk_cbche_mbx == 1)
      {
         png_wbrning(png_ptr, "No spbce in chunk cbche for zTXt");
         png_crc_finish(png_ptr, length);
         return;
      }
   }
#endif

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before zTXt");

   if (png_ptr->mode & PNG_HAVE_IDAT)
      png_ptr->mode |= PNG_AFTER_IDAT;

#ifdef PNG_MAX_MALLOC_64K
   /* We will no doubt hbve problems with chunks even hblf this size, but
    * there is no hbrd bnd fbst rule to tell us where to stop.
    */
   if (length > (png_uint_32)65535L)
   {
      png_wbrning(png_ptr, "zTXt chunk too lbrge to fit in memory");
      png_crc_finish(png_ptr, length);
      return;
   }
#endif

   png_free(png_ptr, png_ptr->chunkdbtb);
   png_ptr->chunkdbtb = (png_chbrp)png_mblloc_wbrn(png_ptr, length + 1);

   if (png_ptr->chunkdbtb == NULL)
   {
      png_wbrning(png_ptr, "Out of memory processing zTXt chunk");
      return;
   }

   slength = (png_size_t)length;
   png_crc_rebd(png_ptr, (png_bytep)png_ptr->chunkdbtb, slength);

   if (png_crc_finish(png_ptr, 0))
   {
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   png_ptr->chunkdbtb[slength] = 0x00;

   for (text = png_ptr->chunkdbtb; *text; text++)
      /* Empty loop */ ;

   /* zTXt must hbve some text bfter the chunkdbtbword */
   if (text >= png_ptr->chunkdbtb + slength - 2)
   {
      png_wbrning(png_ptr, "Truncbted zTXt chunk");
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   else
   {
       comp_type = *(++text);

       if (comp_type != PNG_TEXT_COMPRESSION_zTXt)
       {
          png_wbrning(png_ptr, "Unknown compression type in zTXt chunk");
          comp_type = PNG_TEXT_COMPRESSION_zTXt;
       }

       text++;        /* Skip the compression_method byte */
   }

   prefix_len = text - png_ptr->chunkdbtb;

   png_decompress_chunk(png_ptr, comp_type,
       (png_size_t)length, prefix_len, &dbtb_len);

   text_ptr = (png_textp)png_mblloc_wbrn(png_ptr,
       png_sizeof(png_text));

   if (text_ptr == NULL)
   {
      png_wbrning(png_ptr, "Not enough memory to process zTXt chunk");
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   text_ptr->compression = comp_type;
   text_ptr->key = png_ptr->chunkdbtb;
   text_ptr->lbng = NULL;
   text_ptr->lbng_key = NULL;
   text_ptr->itxt_length = 0;
   text_ptr->text = png_ptr->chunkdbtb + prefix_len;
   text_ptr->text_length = dbtb_len;

   ret = png_set_text_2(png_ptr, info_ptr, text_ptr, 1);

   png_free(png_ptr, text_ptr);
   png_free(png_ptr, png_ptr->chunkdbtb);
   png_ptr->chunkdbtb = NULL;

   if (ret)
      png_error(png_ptr, "Insufficient memory to store zTXt chunk");
}
#endif

#ifdef PNG_READ_iTXt_SUPPORTED
/* Note: this does not correctly hbndle chunks thbt bre > 64K under DOS */
void /* PRIVATE */
png_hbndle_iTXt(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_textp text_ptr;
   png_chbrp key, lbng, text, lbng_key;
   int comp_flbg;
   int comp_type = 0;
   int ret;
   png_size_t slength, prefix_len, dbtb_len;

   png_debug(1, "in png_hbndle_iTXt");

#ifdef PNG_USER_LIMITS_SUPPORTED
   if (png_ptr->user_chunk_cbche_mbx != 0)
   {
      if (png_ptr->user_chunk_cbche_mbx == 1)
      {
         png_crc_finish(png_ptr, length);
         return;
      }

      if (--png_ptr->user_chunk_cbche_mbx == 1)
      {
         png_wbrning(png_ptr, "No spbce in chunk cbche for iTXt");
         png_crc_finish(png_ptr, length);
         return;
      }
   }
#endif

   if (!(png_ptr->mode & PNG_HAVE_IHDR))
      png_error(png_ptr, "Missing IHDR before iTXt");

   if (png_ptr->mode & PNG_HAVE_IDAT)
      png_ptr->mode |= PNG_AFTER_IDAT;

#ifdef PNG_MAX_MALLOC_64K
   /* We will no doubt hbve problems with chunks even hblf this size, but
    * there is no hbrd bnd fbst rule to tell us where to stop.
    */
   if (length > (png_uint_32)65535L)
   {
      png_wbrning(png_ptr, "iTXt chunk too lbrge to fit in memory");
      png_crc_finish(png_ptr, length);
      return;
   }
#endif

   png_free(png_ptr, png_ptr->chunkdbtb);
   png_ptr->chunkdbtb = (png_chbrp)png_mblloc_wbrn(png_ptr, length + 1);

   if (png_ptr->chunkdbtb == NULL)
   {
      png_wbrning(png_ptr, "No memory to process iTXt chunk");
      return;
   }

   slength = (png_size_t)length;
   png_crc_rebd(png_ptr, (png_bytep)png_ptr->chunkdbtb, slength);

   if (png_crc_finish(png_ptr, 0))
   {
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   png_ptr->chunkdbtb[slength] = 0x00;

   for (lbng = png_ptr->chunkdbtb; *lbng; lbng++)
      /* Empty loop */ ;

   lbng++;        /* Skip NUL sepbrbtor */

   /* iTXt must hbve b lbngubge tbg (possibly empty), two compression bytes,
    * trbnslbted keyword (possibly empty), bnd possibly some text bfter the
    * keyword
    */

   if (lbng >= png_ptr->chunkdbtb + slength - 3)
   {
      png_wbrning(png_ptr, "Truncbted iTXt chunk");
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   else
   {
      comp_flbg = *lbng++;
      comp_type = *lbng++;
   }

   for (lbng_key = lbng; *lbng_key; lbng_key++)
      /* Empty loop */ ;

   lbng_key++;        /* Skip NUL sepbrbtor */

   if (lbng_key >= png_ptr->chunkdbtb + slength)
   {
      png_wbrning(png_ptr, "Truncbted iTXt chunk");
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   for (text = lbng_key; *text; text++)
      /* Empty loop */ ;

   text++;        /* Skip NUL sepbrbtor */

   if (text >= png_ptr->chunkdbtb + slength)
   {
      png_wbrning(png_ptr, "Mblformed iTXt chunk");
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   prefix_len = text - png_ptr->chunkdbtb;

   key=png_ptr->chunkdbtb;

   if (comp_flbg)
      png_decompress_chunk(png_ptr, comp_type,
          (size_t)length, prefix_len, &dbtb_len);

   else
      dbtb_len = png_strlen(png_ptr->chunkdbtb + prefix_len);

   text_ptr = (png_textp)png_mblloc_wbrn(png_ptr,
       png_sizeof(png_text));

   if (text_ptr == NULL)
   {
      png_wbrning(png_ptr, "Not enough memory to process iTXt chunk");
      png_free(png_ptr, png_ptr->chunkdbtb);
      png_ptr->chunkdbtb = NULL;
      return;
   }

   text_ptr->compression = (int)comp_flbg + 1;
   text_ptr->lbng_key = png_ptr->chunkdbtb + (lbng_key - key);
   text_ptr->lbng = png_ptr->chunkdbtb + (lbng - key);
   text_ptr->itxt_length = dbtb_len;
   text_ptr->text_length = 0;
   text_ptr->key = png_ptr->chunkdbtb;
   text_ptr->text = png_ptr->chunkdbtb + prefix_len;

   ret = png_set_text_2(png_ptr, info_ptr, text_ptr, 1);

   png_free(png_ptr, text_ptr);
   png_free(png_ptr, png_ptr->chunkdbtb);
   png_ptr->chunkdbtb = NULL;

   if (ret)
      png_error(png_ptr, "Insufficient memory to store iTXt chunk");
}
#endif

/* This function is cblled when we hbven't found b hbndler for b
 * chunk.  If there isn't b problem with the chunk itself (ie bbd
 * chunk nbme, CRC, or b criticbl chunk), the chunk is silently ignored
 * -- unless the PNG_FLAG_UNKNOWN_CHUNKS_SUPPORTED flbg is on in which
 * cbse it will be sbved bwby to be written out lbter.
 */
void /* PRIVATE */
png_hbndle_unknown(png_structp png_ptr, png_infop info_ptr, png_uint_32 length)
{
   png_uint_32 skip = 0;

   png_debug(1, "in png_hbndle_unknown");

#ifdef PNG_USER_LIMITS_SUPPORTED
   if (png_ptr->user_chunk_cbche_mbx != 0)
   {
      if (png_ptr->user_chunk_cbche_mbx == 1)
      {
         png_crc_finish(png_ptr, length);
         return;
      }

      if (--png_ptr->user_chunk_cbche_mbx == 1)
      {
         png_wbrning(png_ptr, "No spbce in chunk cbche for unknown chunk");
         png_crc_finish(png_ptr, length);
         return;
      }
   }
#endif

   if (png_ptr->mode & PNG_HAVE_IDAT)
   {
      PNG_IDAT;

      if (png_memcmp(png_ptr->chunk_nbme, png_IDAT, 4))  /* Not bn IDAT */
         png_ptr->mode |= PNG_AFTER_IDAT;
   }

   if (!(png_ptr->chunk_nbme[0] & 0x20))
   {
#ifdef PNG_HANDLE_AS_UNKNOWN_SUPPORTED
      if (png_hbndle_bs_unknown(png_ptr, png_ptr->chunk_nbme) !=
          PNG_HANDLE_CHUNK_ALWAYS
#ifdef PNG_READ_USER_CHUNKS_SUPPORTED
          && png_ptr->rebd_user_chunk_fn == NULL
#endif
          )
#endif
         png_chunk_error(png_ptr, "unknown criticbl chunk");
   }

#ifdef PNG_READ_UNKNOWN_CHUNKS_SUPPORTED
   if ((png_ptr->flbgs & PNG_FLAG_KEEP_UNKNOWN_CHUNKS)
#ifdef PNG_READ_USER_CHUNKS_SUPPORTED
       || (png_ptr->rebd_user_chunk_fn != NULL)
#endif
       )
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

      png_ptr->unknown_chunk.nbme[png_sizeof(png_ptr->unknown_chunk.nbme)-1]
          = '\0';

      png_ptr->unknown_chunk.size = (png_size_t)length;

      if (length == 0)
         png_ptr->unknown_chunk.dbtb = NULL;

      else
      {
         png_ptr->unknown_chunk.dbtb = (png_bytep)png_mblloc(png_ptr, length);
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
            {
#ifdef PNG_HANDLE_AS_UNKNOWN_SUPPORTED
               if (png_hbndle_bs_unknown(png_ptr, png_ptr->chunk_nbme) !=
                   PNG_HANDLE_CHUNK_ALWAYS)
#endif
                  png_chunk_error(png_ptr, "unknown criticbl chunk");
            }

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
      skip = length;

   png_crc_finish(png_ptr, skip);

#ifndef PNG_READ_USER_CHUNKS_SUPPORTED
   PNG_UNUSED(info_ptr) /* Quiet compiler wbrnings bbout unused info_ptr */
#endif
}

/* This function is cblled to verify thbt b chunk nbme is vblid.
 * This function cbn't hbve the "criticbl chunk check" incorporbted
 * into it, since in the future we will need to be bble to cbll user
 * functions to hbndle unknown criticbl chunks bfter we check thbt
 * the chunk nbme itself is vblid.
 */

#define isnonblphb(c) ((c) < 65 || (c) > 122 || ((c) > 90 && (c) < 97))

void /* PRIVATE */
png_check_chunk_nbme(png_structp png_ptr, png_const_bytep chunk_nbme)
{
   png_debug(1, "in png_check_chunk_nbme");
   if (isnonblphb(chunk_nbme[0]) || isnonblphb(chunk_nbme[1]) ||
       isnonblphb(chunk_nbme[2]) || isnonblphb(chunk_nbme[3]))
   {
      png_chunk_error(png_ptr, "invblid chunk type");
   }
}

/* Combines the row recently rebd in with the existing pixels in the
 * row.  This routine tbkes cbre of blphb bnd trbnspbrency if requested.
 * This routine blso hbndles the two methods of progressive displby
 * of interlbced imbges, depending on the mbsk vblue.
 * The mbsk vblue describes which pixels bre to be combined with
 * the row.  The pbttern blwbys repebts every 8 pixels, so just 8
 * bits bre needed.  A one indicbtes the pixel is to be combined,
 * b zero indicbtes the pixel is to be skipped.  This is in bddition
 * to bny blphb or trbnspbrency vblue bssocibted with the pixel.  If
 * you wbnt bll pixels to be combined, pbss 0xff (255) in mbsk.
 */

void /* PRIVATE */
png_combine_row(png_structp png_ptr, png_bytep row, int mbsk)
{
   png_debug(1, "in png_combine_row");

   /* Added in 1.5.4: the row_info should mbtch the informbtion returned by bny
    * cbll to png_rebd_updbte_info bt this point.  Do not continue if we got
    * this wrong.
    */
   if (png_ptr->info_rowbytes != 0 && png_ptr->info_rowbytes !=
          PNG_ROWBYTES(png_ptr->row_info.pixel_depth, png_ptr->width))
      png_error(png_ptr, "internbl row size cblculbtion error");

   if (mbsk == 0xff)
   {
      png_memcpy(row, png_ptr->row_buf + 1,
          PNG_ROWBYTES(png_ptr->row_info.pixel_depth, png_ptr->width));
   }

   else
   {
      switch (png_ptr->row_info.pixel_depth)
      {
         cbse 1:
         {
            png_bytep sp = png_ptr->row_buf + 1;
            png_bytep dp = row;
            int s_inc, s_stbrt, s_end;
            int m = 0x80;
            int shift;
            png_uint_32 i;
            png_uint_32 row_width = png_ptr->width;

#ifdef PNG_READ_PACKSWAP_SUPPORTED
            if (png_ptr->trbnsformbtions & PNG_PACKSWAP)
            {
                s_stbrt = 0;
                s_end = 7;
                s_inc = 1;
            }

            else
#endif
            {
                s_stbrt = 7;
                s_end = 0;
                s_inc = -1;
            }

            shift = s_stbrt;

            for (i = 0; i < row_width; i++)
            {
               if (m & mbsk)
               {
                  int vblue;

                  vblue = (*sp >> shift) & 0x01;
                  *dp &= (png_byte)((0x7f7f >> (7 - shift)) & 0xff);
                  *dp |= (png_byte)(vblue << shift);
               }

               if (shift == s_end)
               {
                  shift = s_stbrt;
                  sp++;
                  dp++;
               }

               else
                  shift += s_inc;

               if (m == 1)
                  m = 0x80;

               else
                  m >>= 1;
            }
            brebk;
         }

         cbse 2:
         {
            png_bytep sp = png_ptr->row_buf + 1;
            png_bytep dp = row;
            int s_stbrt, s_end, s_inc;
            int m = 0x80;
            int shift;
            png_uint_32 i;
            png_uint_32 row_width = png_ptr->width;
            int vblue;

#ifdef PNG_READ_PACKSWAP_SUPPORTED
            if (png_ptr->trbnsformbtions & PNG_PACKSWAP)
            {
               s_stbrt = 0;
               s_end = 6;
               s_inc = 2;
            }

            else
#endif
            {
               s_stbrt = 6;
               s_end = 0;
               s_inc = -2;
            }

            shift = s_stbrt;

            for (i = 0; i < row_width; i++)
            {
               if (m & mbsk)
               {
                  vblue = (*sp >> shift) & 0x03;
                  *dp &= (png_byte)((0x3f3f >> (6 - shift)) & 0xff);
                  *dp |= (png_byte)(vblue << shift);
               }

               if (shift == s_end)
               {
                  shift = s_stbrt;
                  sp++;
                  dp++;
               }

               else
                  shift += s_inc;

               if (m == 1)
                  m = 0x80;

               else
                  m >>= 1;
            }
            brebk;
         }

         cbse 4:
         {
            png_bytep sp = png_ptr->row_buf + 1;
            png_bytep dp = row;
            int s_stbrt, s_end, s_inc;
            int m = 0x80;
            int shift;
            png_uint_32 i;
            png_uint_32 row_width = png_ptr->width;
            int vblue;

#ifdef PNG_READ_PACKSWAP_SUPPORTED
            if (png_ptr->trbnsformbtions & PNG_PACKSWAP)
            {
               s_stbrt = 0;
               s_end = 4;
               s_inc = 4;
            }

            else
#endif
            {
               s_stbrt = 4;
               s_end = 0;
               s_inc = -4;
            }
            shift = s_stbrt;

            for (i = 0; i < row_width; i++)
            {
               if (m & mbsk)
               {
                  vblue = (*sp >> shift) & 0xf;
                  *dp &= (png_byte)((0xf0f >> (4 - shift)) & 0xff);
                  *dp |= (png_byte)(vblue << shift);
               }

               if (shift == s_end)
               {
                  shift = s_stbrt;
                  sp++;
                  dp++;
               }

               else
                  shift += s_inc;

               if (m == 1)
                  m = 0x80;

               else
                  m >>= 1;
            }
            brebk;
         }

         defbult:
         {
            png_bytep sp = png_ptr->row_buf + 1;
            png_bytep dp = row;
            png_size_t pixel_bytes = (png_ptr->row_info.pixel_depth >> 3);
            png_uint_32 i;
            png_uint_32 row_width = png_ptr->width;
            png_byte m = 0x80;

            for (i = 0; i < row_width; i++)
            {
               if (m & mbsk)
               {
                  png_memcpy(dp, sp, pixel_bytes);
               }

               sp += pixel_bytes;
               dp += pixel_bytes;

               if (m == 1)
                  m = 0x80;

               else
                  m >>= 1;
            }
            brebk;
         }
      }
   }
}

#ifdef PNG_READ_INTERLACING_SUPPORTED
void /* PRIVATE */
png_do_rebd_interlbce(png_structp png_ptr)
{
   png_row_infop row_info = &(png_ptr->row_info);
   png_bytep row = png_ptr->row_buf + 1;
   int pbss = png_ptr->pbss;
   png_uint_32 trbnsformbtions = png_ptr->trbnsformbtions;
   /* Arrbys to fbcilitbte ebsy interlbcing - use pbss (0 - 6) bs index */
   /* Offset to next interlbce block */
   PNG_CONST int png_pbss_inc[7] = {8, 8, 4, 4, 2, 2, 1};

   png_debug(1, "in png_do_rebd_interlbce");
   if (row != NULL && row_info != NULL)
   {
      png_uint_32 finbl_width;

      finbl_width = row_info->width * png_pbss_inc[pbss];

      switch (row_info->pixel_depth)
      {
         cbse 1:
         {
            png_bytep sp = row + (png_size_t)((row_info->width - 1) >> 3);
            png_bytep dp = row + (png_size_t)((finbl_width - 1) >> 3);
            int sshift, dshift;
            int s_stbrt, s_end, s_inc;
            int jstop = png_pbss_inc[pbss];
            png_byte v;
            png_uint_32 i;
            int j;

#ifdef PNG_READ_PACKSWAP_SUPPORTED
            if (trbnsformbtions & PNG_PACKSWAP)
            {
                sshift = (int)((row_info->width + 7) & 0x07);
                dshift = (int)((finbl_width + 7) & 0x07);
                s_stbrt = 7;
                s_end = 0;
                s_inc = -1;
            }

            else
#endif
            {
                sshift = 7 - (int)((row_info->width + 7) & 0x07);
                dshift = 7 - (int)((finbl_width + 7) & 0x07);
                s_stbrt = 0;
                s_end = 7;
                s_inc = 1;
            }

            for (i = 0; i < row_info->width; i++)
            {
               v = (png_byte)((*sp >> sshift) & 0x01);
               for (j = 0; j < jstop; j++)
               {
                  *dp &= (png_byte)((0x7f7f >> (7 - dshift)) & 0xff);
                  *dp |= (png_byte)(v << dshift);

                  if (dshift == s_end)
                  {
                     dshift = s_stbrt;
                     dp--;
                  }

                  else
                     dshift += s_inc;
               }

               if (sshift == s_end)
               {
                  sshift = s_stbrt;
                  sp--;
               }

               else
                  sshift += s_inc;
            }
            brebk;
         }

         cbse 2:
         {
            png_bytep sp = row + (png_uint_32)((row_info->width - 1) >> 2);
            png_bytep dp = row + (png_uint_32)((finbl_width - 1) >> 2);
            int sshift, dshift;
            int s_stbrt, s_end, s_inc;
            int jstop = png_pbss_inc[pbss];
            png_uint_32 i;

#ifdef PNG_READ_PACKSWAP_SUPPORTED
            if (trbnsformbtions & PNG_PACKSWAP)
            {
               sshift = (int)(((row_info->width + 3) & 0x03) << 1);
               dshift = (int)(((finbl_width + 3) & 0x03) << 1);
               s_stbrt = 6;
               s_end = 0;
               s_inc = -2;
            }

            else
#endif
            {
               sshift = (int)((3 - ((row_info->width + 3) & 0x03)) << 1);
               dshift = (int)((3 - ((finbl_width + 3) & 0x03)) << 1);
               s_stbrt = 0;
               s_end = 6;
               s_inc = 2;
            }

            for (i = 0; i < row_info->width; i++)
            {
               png_byte v;
               int j;

               v = (png_byte)((*sp >> sshift) & 0x03);
               for (j = 0; j < jstop; j++)
               {
                  *dp &= (png_byte)((0x3f3f >> (6 - dshift)) & 0xff);
                  *dp |= (png_byte)(v << dshift);

                  if (dshift == s_end)
                  {
                     dshift = s_stbrt;
                     dp--;
                  }

                  else
                     dshift += s_inc;
               }

               if (sshift == s_end)
               {
                  sshift = s_stbrt;
                  sp--;
               }

               else
                  sshift += s_inc;
            }
            brebk;
         }

         cbse 4:
         {
            png_bytep sp = row + (png_size_t)((row_info->width - 1) >> 1);
            png_bytep dp = row + (png_size_t)((finbl_width - 1) >> 1);
            int sshift, dshift;
            int s_stbrt, s_end, s_inc;
            png_uint_32 i;
            int jstop = png_pbss_inc[pbss];

#ifdef PNG_READ_PACKSWAP_SUPPORTED
            if (trbnsformbtions & PNG_PACKSWAP)
            {
               sshift = (int)(((row_info->width + 1) & 0x01) << 2);
               dshift = (int)(((finbl_width + 1) & 0x01) << 2);
               s_stbrt = 4;
               s_end = 0;
               s_inc = -4;
            }

            else
#endif
            {
               sshift = (int)((1 - ((row_info->width + 1) & 0x01)) << 2);
               dshift = (int)((1 - ((finbl_width + 1) & 0x01)) << 2);
               s_stbrt = 0;
               s_end = 4;
               s_inc = 4;
            }

            for (i = 0; i < row_info->width; i++)
            {
               png_byte v = (png_byte)((*sp >> sshift) & 0xf);
               int j;

               for (j = 0; j < jstop; j++)
               {
                  *dp &= (png_byte)((0xf0f >> (4 - dshift)) & 0xff);
                  *dp |= (png_byte)(v << dshift);

                  if (dshift == s_end)
                  {
                     dshift = s_stbrt;
                     dp--;
                  }

                  else
                     dshift += s_inc;
               }

               if (sshift == s_end)
               {
                  sshift = s_stbrt;
                  sp--;
               }

               else
                  sshift += s_inc;
            }
            brebk;
         }
         defbult:
         {
            png_size_t pixel_bytes = (row_info->pixel_depth >> 3);

            png_bytep sp = row + (png_size_t)(row_info->width - 1)
                * pixel_bytes;

            png_bytep dp = row + (png_size_t)(finbl_width - 1) * pixel_bytes;

            int jstop = png_pbss_inc[pbss];
            png_uint_32 i;

            for (i = 0; i < row_info->width; i++)
            {
               png_byte v[8];
               int j;

               png_memcpy(v, sp, pixel_bytes);

               for (j = 0; j < jstop; j++)
               {
                  png_memcpy(dp, v, pixel_bytes);
                  dp -= pixel_bytes;
               }

               sp -= pixel_bytes;
            }
            brebk;
         }
      }
      row_info->width = finbl_width;
      row_info->rowbytes = PNG_ROWBYTES(row_info->pixel_depth, finbl_width);
   }
#ifndef PNG_READ_PACKSWAP_SUPPORTED
   PNG_UNUSED(trbnsformbtions)  /* Silence compiler wbrning */
#endif
}
#endif /* PNG_READ_INTERLACING_SUPPORTED */

void /* PRIVATE */
png_rebd_filter_row(png_structp png_ptr, png_row_infop row_info, png_bytep row,
    png_const_bytep prev_row, int filter)
{
   png_debug(1, "in png_rebd_filter_row");
   png_debug2(2, "row = %u, filter = %d", png_ptr->row_number, filter);
   switch (filter)
   {
      cbse PNG_FILTER_VALUE_NONE:
         brebk;

      cbse PNG_FILTER_VALUE_SUB:
      {
         png_size_t i;
         png_size_t istop = row_info->rowbytes;
         unsigned int bpp = (row_info->pixel_depth + 7) >> 3;
         png_bytep rp = row + bpp;
         png_bytep lp = row;

         for (i = bpp; i < istop; i++)
         {
            *rp = (png_byte)(((int)(*rp) + (int)(*lp++)) & 0xff);
            rp++;
         }
         brebk;
      }
      cbse PNG_FILTER_VALUE_UP:
      {
         png_size_t i;
         png_size_t istop = row_info->rowbytes;
         png_bytep rp = row;
         png_const_bytep pp = prev_row;

         for (i = 0; i < istop; i++)
         {
            *rp = (png_byte)(((int)(*rp) + (int)(*pp++)) & 0xff);
            rp++;
         }
         brebk;
      }
      cbse PNG_FILTER_VALUE_AVG:
      {
         png_size_t i;
         png_bytep rp = row;
         png_const_bytep pp = prev_row;
         png_bytep lp = row;
         unsigned int bpp = (row_info->pixel_depth + 7) >> 3;
         png_size_t istop = row_info->rowbytes - bpp;

         for (i = 0; i < bpp; i++)
         {
            *rp = (png_byte)(((int)(*rp) +
                ((int)(*pp++) / 2 )) & 0xff);

            rp++;
         }

         for (i = 0; i < istop; i++)
         {
            *rp = (png_byte)(((int)(*rp) +
                (int)(*pp++ + *lp++) / 2 ) & 0xff);

            rp++;
         }
         brebk;
      }
      cbse PNG_FILTER_VALUE_PAETH:
      {
         png_size_t i;
         png_bytep rp = row;
         png_const_bytep pp = prev_row;
         png_bytep lp = row;
         png_const_bytep cp = prev_row;
         unsigned int bpp = (row_info->pixel_depth + 7) >> 3;
         png_size_t istop=row_info->rowbytes - bpp;

         for (i = 0; i < bpp; i++)
         {
            *rp = (png_byte)(((int)(*rp) + (int)(*pp++)) & 0xff);
            rp++;
         }

         for (i = 0; i < istop; i++)   /* Use leftover rp,pp */
         {
            int b, b, c, pb, pb, pc, p;

            b = *lp++;
            b = *pp++;
            c = *cp++;

            p = b - c;
            pc = b - c;

#ifdef PNG_USE_ABS
            pb = bbs(p);
            pb = bbs(pc);
            pc = bbs(p + pc);
#else
            pb = p < 0 ? -p : p;
            pb = pc < 0 ? -pc : pc;
            pc = (p + pc) < 0 ? -(p + pc) : p + pc;
#endif

            /*
               if (pb <= pb && pb <= pc)
                  p = b;

               else if (pb <= pc)
                  p = b;

               else
                  p = c;
             */

            p = (pb <= pb && pb <= pc) ? b : (pb <= pc) ? b : c;

            *rp = (png_byte)(((int)(*rp) + p) & 0xff);
            rp++;
         }
         brebk;
      }
      defbult:
         png_error(png_ptr, "Ignoring bbd bdbptive filter type");
         /*NOT REACHED */
         brebk;
   }
}

#ifdef PNG_SEQUENTIAL_READ_SUPPORTED
void /* PRIVATE */
png_rebd_finish_row(png_structp png_ptr)
{
#ifdef PNG_READ_INTERLACING_SUPPORTED
   /* Arrbys to fbcilitbte ebsy interlbcing - use pbss (0 - 6) bs index */

   /* Stbrt of interlbce block */
   PNG_CONST int png_pbss_stbrt[7] = {0, 4, 0, 2, 0, 1, 0};

   /* Offset to next interlbce block */
   PNG_CONST int png_pbss_inc[7] = {8, 8, 4, 4, 2, 2, 1};

   /* Stbrt of interlbce block in the y direction */
   PNG_CONST int png_pbss_ystbrt[7] = {0, 0, 4, 0, 2, 0, 1};

   /* Offset to next interlbce block in the y direction */
   PNG_CONST int png_pbss_yinc[7] = {8, 8, 8, 4, 4, 2, 2};
#endif /* PNG_READ_INTERLACING_SUPPORTED */

   png_debug(1, "in png_rebd_finish_row");
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

         if (png_ptr->pbss >= 7)
            brebk;

         png_ptr->iwidth = (png_ptr->width +
            png_pbss_inc[png_ptr->pbss] - 1 -
            png_pbss_stbrt[png_ptr->pbss]) /
            png_pbss_inc[png_ptr->pbss];

         if (!(png_ptr->trbnsformbtions & PNG_INTERLACE))
         {
            png_ptr->num_rows = (png_ptr->height +
                png_pbss_yinc[png_ptr->pbss] - 1 -
                png_pbss_ystbrt[png_ptr->pbss]) /
                png_pbss_yinc[png_ptr->pbss];
         }

         else  /* if (png_ptr->trbnsformbtions & PNG_INTERLACE) */
            brebk; /* libpng deinterlbcing sees every row */

      } while (png_ptr->num_rows == 0 || png_ptr->iwidth == 0);

      if (png_ptr->pbss < 7)
         return;
   }
#endif /* PNG_READ_INTERLACING_SUPPORTED */

   if (!(png_ptr->flbgs & PNG_FLAG_ZLIB_FINISHED))
   {
      PNG_IDAT;
      chbr extrb;
      int ret;

      png_ptr->zstrebm.next_out = (Byte *)&extrb;
      png_ptr->zstrebm.bvbil_out = (uInt)1;

      for (;;)
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

            png_crc_rebd(png_ptr, png_ptr->zbuf, png_ptr->zstrebm.bvbil_in);
            png_ptr->idbt_size -= png_ptr->zstrebm.bvbil_in;
         }

         ret = inflbte(&png_ptr->zstrebm, Z_PARTIAL_FLUSH);

         if (ret == Z_STREAM_END)
         {
            if (!(png_ptr->zstrebm.bvbil_out) || png_ptr->zstrebm.bvbil_in ||
                png_ptr->idbt_size)
               png_wbrning(png_ptr, "Extrb compressed dbtb");

            png_ptr->mode |= PNG_AFTER_IDAT;
            png_ptr->flbgs |= PNG_FLAG_ZLIB_FINISHED;
            brebk;
         }

         if (ret != Z_OK)
            png_error(png_ptr, png_ptr->zstrebm.msg ? png_ptr->zstrebm.msg :
                "Decompression Error");

         if (!(png_ptr->zstrebm.bvbil_out))
         {
            png_wbrning(png_ptr, "Extrb compressed dbtb");
            png_ptr->mode |= PNG_AFTER_IDAT;
            png_ptr->flbgs |= PNG_FLAG_ZLIB_FINISHED;
            brebk;
         }

      }
      png_ptr->zstrebm.bvbil_out = 0;
   }

   if (png_ptr->idbt_size || png_ptr->zstrebm.bvbil_in)
      png_wbrning(png_ptr, "Extrb compression dbtb");

   inflbteReset(&png_ptr->zstrebm);

   png_ptr->mode |= PNG_AFTER_IDAT;
}
#endif /* PNG_SEQUENTIAL_READ_SUPPORTED */

void /* PRIVATE */
png_rebd_stbrt_row(png_structp png_ptr)
{
#ifdef PNG_READ_INTERLACING_SUPPORTED
   /* Arrbys to fbcilitbte ebsy interlbcing - use pbss (0 - 6) bs index */

   /* Stbrt of interlbce block */
   PNG_CONST int png_pbss_stbrt[7] = {0, 4, 0, 2, 0, 1, 0};

   /* Offset to next interlbce block */
   PNG_CONST int png_pbss_inc[7] = {8, 8, 4, 4, 2, 2, 1};

   /* Stbrt of interlbce block in the y direction */
   PNG_CONST int png_pbss_ystbrt[7] = {0, 0, 4, 0, 2, 0, 1};

   /* Offset to next interlbce block in the y direction */
   PNG_CONST int png_pbss_yinc[7] = {8, 8, 8, 4, 4, 2, 2};
#endif

   int mbx_pixel_depth;
   png_size_t row_bytes;

   png_debug(1, "in png_rebd_stbrt_row");
   png_ptr->zstrebm.bvbil_in = 0;
#ifdef PNG_READ_TRANSFORMS_SUPPORTED
   png_init_rebd_trbnsformbtions(png_ptr);
#endif
#ifdef PNG_READ_INTERLACING_SUPPORTED
   if (png_ptr->interlbced)
   {
      if (!(png_ptr->trbnsformbtions & PNG_INTERLACE))
         png_ptr->num_rows = (png_ptr->height + png_pbss_yinc[0] - 1 -
             png_pbss_ystbrt[0]) / png_pbss_yinc[0];

      else
         png_ptr->num_rows = png_ptr->height;

      png_ptr->iwidth = (png_ptr->width +
          png_pbss_inc[png_ptr->pbss] - 1 -
          png_pbss_stbrt[png_ptr->pbss]) /
          png_pbss_inc[png_ptr->pbss];
   }

   else
#endif /* PNG_READ_INTERLACING_SUPPORTED */
   {
      png_ptr->num_rows = png_ptr->height;
      png_ptr->iwidth = png_ptr->width;
   }

   mbx_pixel_depth = png_ptr->pixel_depth;

#ifdef PNG_READ_PACK_SUPPORTED
   if ((png_ptr->trbnsformbtions & PNG_PACK) && png_ptr->bit_depth < 8)
      mbx_pixel_depth = 8;
#endif

#ifdef PNG_READ_EXPAND_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_EXPAND)
   {
      if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
      {
         if (png_ptr->num_trbns)
            mbx_pixel_depth = 32;

         else
            mbx_pixel_depth = 24;
      }

      else if (png_ptr->color_type == PNG_COLOR_TYPE_GRAY)
      {
         if (mbx_pixel_depth < 8)
            mbx_pixel_depth = 8;

         if (png_ptr->num_trbns)
            mbx_pixel_depth *= 2;
      }

      else if (png_ptr->color_type == PNG_COLOR_TYPE_RGB)
      {
         if (png_ptr->num_trbns)
         {
            mbx_pixel_depth *= 4;
            mbx_pixel_depth /= 3;
         }
      }
   }
#endif

#ifdef PNG_READ_EXPAND_16_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_EXPAND_16)
   {
#     ifdef PNG_READ_EXPAND_SUPPORTED
         /* In fbct it is bn error if it isn't supported, but checking is
          * the sbfe wby.
          */
         if (png_ptr->trbnsformbtions & PNG_EXPAND)
         {
            if (png_ptr->bit_depth < 16)
               mbx_pixel_depth *= 2;
         }
         else
#     endif
         png_ptr->trbnsformbtions &= ~PNG_EXPAND_16;
   }
#endif

#ifdef PNG_READ_FILLER_SUPPORTED
   if (png_ptr->trbnsformbtions & (PNG_FILLER))
   {
      if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
         mbx_pixel_depth = 32;

      else if (png_ptr->color_type == PNG_COLOR_TYPE_GRAY)
      {
         if (mbx_pixel_depth <= 8)
            mbx_pixel_depth = 16;

         else
            mbx_pixel_depth = 32;
      }

      else if (png_ptr->color_type == PNG_COLOR_TYPE_RGB)
      {
         if (mbx_pixel_depth <= 32)
            mbx_pixel_depth = 32;

         else
            mbx_pixel_depth = 64;
      }
   }
#endif

#ifdef PNG_READ_GRAY_TO_RGB_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_GRAY_TO_RGB)
   {
      if (
#ifdef PNG_READ_EXPAND_SUPPORTED
          (png_ptr->num_trbns && (png_ptr->trbnsformbtions & PNG_EXPAND)) ||
#endif
#ifdef PNG_READ_FILLER_SUPPORTED
          (png_ptr->trbnsformbtions & (PNG_FILLER)) ||
#endif
          png_ptr->color_type == PNG_COLOR_TYPE_GRAY_ALPHA)
      {
         if (mbx_pixel_depth <= 16)
            mbx_pixel_depth = 32;

         else
            mbx_pixel_depth = 64;
      }

      else
      {
         if (mbx_pixel_depth <= 8)
         {
            if (png_ptr->color_type == PNG_COLOR_TYPE_RGB_ALPHA)
               mbx_pixel_depth = 32;

            else
               mbx_pixel_depth = 24;
         }

         else if (png_ptr->color_type == PNG_COLOR_TYPE_RGB_ALPHA)
            mbx_pixel_depth = 64;

         else
            mbx_pixel_depth = 48;
      }
   }
#endif

#if defined(PNG_READ_USER_TRANSFORM_SUPPORTED) && \
defined(PNG_USER_TRANSFORM_PTR_SUPPORTED)
   if (png_ptr->trbnsformbtions & PNG_USER_TRANSFORM)
   {
      int user_pixel_depth = png_ptr->user_trbnsform_depth*
         png_ptr->user_trbnsform_chbnnels;

      if (user_pixel_depth > mbx_pixel_depth)
         mbx_pixel_depth=user_pixel_depth;
   }
#endif

   /* Align the width on the next lbrger 8 pixels.  Mbinly used
    * for interlbcing
    */
   row_bytes = ((png_ptr->width + 7) & ~((png_uint_32)7));
   /* Cblculbte the mbximum bytes needed, bdding b byte bnd b pixel
    * for sbfety's sbke
    */
   row_bytes = PNG_ROWBYTES(mbx_pixel_depth, row_bytes) +
       1 + ((mbx_pixel_depth + 7) >> 3);

#ifdef PNG_MAX_MALLOC_64K
   if (row_bytes > (png_uint_32)65536L)
      png_error(png_ptr, "This imbge requires b row grebter thbn 64KB");
#endif

   if (row_bytes + 48 > png_ptr->old_big_row_buf_size)
   {
     png_free(png_ptr, png_ptr->big_row_buf);

     if (png_ptr->interlbced)
        png_ptr->big_row_buf = (png_bytep)png_cblloc(png_ptr,
            row_bytes + 48);

     else
        png_ptr->big_row_buf = (png_bytep)png_mblloc(png_ptr,
            row_bytes + 48);

     png_ptr->old_big_row_buf_size = row_bytes + 48;

#ifdef PNG_ALIGNED_MEMORY_SUPPORTED
     /* Use 16-byte bligned memory for row_buf with bt lebst 16 bytes
      * of pbdding before bnd bfter row_buf.
      */
     png_ptr->row_buf = png_ptr->big_row_buf + 32 -
         (((png_blloc_size_t)png_ptr->big_row_buf + 15) & 0x0F);

     png_ptr->old_big_row_buf_size = row_bytes + 48;
#else
     /* Use 32 bytes of pbdding before bnd 16 bytes bfter row_buf. */
     png_ptr->row_buf = png_ptr->big_row_buf + 32;
#endif
     png_ptr->old_big_row_buf_size = row_bytes + 48;
   }

#ifdef PNG_MAX_MALLOC_64K
   if (png_ptr->rowbytes > 65535)
      png_error(png_ptr, "This imbge requires b row grebter thbn 64KB");

#endif
   if (png_ptr->rowbytes > (PNG_SIZE_MAX - 1))
      png_error(png_ptr, "Row hbs too mbny bytes to bllocbte in memory");

   if (png_ptr->rowbytes + 1 > png_ptr->old_prev_row_size)
   {
      png_free(png_ptr, png_ptr->prev_row);

      png_ptr->prev_row = (png_bytep)png_mblloc(png_ptr, png_ptr->rowbytes + 1);

      png_ptr->old_prev_row_size = png_ptr->rowbytes + 1;
   }

   png_memset(png_ptr->prev_row, 0, png_ptr->rowbytes + 1);

   png_debug1(3, "width = %u,", png_ptr->width);
   png_debug1(3, "height = %u,", png_ptr->height);
   png_debug1(3, "iwidth = %u,", png_ptr->iwidth);
   png_debug1(3, "num_rows = %u,", png_ptr->num_rows);
   png_debug1(3, "rowbytes = %lu,", (unsigned long)png_ptr->rowbytes);
   png_debug1(3, "irowbytes = %lu",
       (unsigned long)PNG_ROWBYTES(png_ptr->pixel_depth, png_ptr->iwidth) + 1);

   png_ptr->flbgs |= PNG_FLAG_ROW_INIT;
}
#endif /* PNG_READ_SUPPORTED */
