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

/* pngwutil.c - utilities to write b PNG file
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

#ifdef PNG_WRITE_INT_FUNCTIONS_SUPPORTED
/* Plbce b 32-bit number into b buffer in PNG byte order.  We work
 * with unsigned numbers for convenience, blthough one supported
 * bncillbry chunk uses signed (two's complement) numbers.
 */
void PNGAPI
png_sbve_uint_32(png_bytep buf, png_uint_32 i)
{
   buf[0] = (png_byte)((i >> 24) & 0xff);
   buf[1] = (png_byte)((i >> 16) & 0xff);
   buf[2] = (png_byte)((i >> 8) & 0xff);
   buf[3] = (png_byte)(i & 0xff);
}

#ifdef PNG_SAVE_INT_32_SUPPORTED
/* The png_sbve_int_32 function bssumes integers bre stored in two's
 * complement formbt.  If this isn't the cbse, then this routine needs to
 * be modified to write dbtb in two's complement formbt.  Note thbt,
 * the following works correctly even if png_int_32 hbs more thbn 32 bits
 * (compbre the more complex code required on rebd for sign extention.)
 */
void PNGAPI
png_sbve_int_32(png_bytep buf, png_int_32 i)
{
   buf[0] = (png_byte)((i >> 24) & 0xff);
   buf[1] = (png_byte)((i >> 16) & 0xff);
   buf[2] = (png_byte)((i >> 8) & 0xff);
   buf[3] = (png_byte)(i & 0xff);
}
#endif

/* Plbce b 16-bit number into b buffer in PNG byte order.
 * The pbrbmeter is declbred unsigned int, not png_uint_16,
 * just to bvoid potentibl problems on pre-ANSI C compilers.
 */
void PNGAPI
png_sbve_uint_16(png_bytep buf, unsigned int i)
{
   buf[0] = (png_byte)((i >> 8) & 0xff);
   buf[1] = (png_byte)(i & 0xff);
}
#endif

/* Simple function to write the signbture.  If we hbve blrebdy written
 * the mbgic bytes of the signbture, or more likely, the PNG strebm is
 * being embedded into bnother strebm bnd doesn't need its own signbture,
 * we should cbll png_set_sig_bytes() to tell libpng how mbny of the
 * bytes hbve blrebdy been written.
 */
void PNGAPI
png_write_sig(png_structp png_ptr)
{
   png_byte png_signbture[8] = {137, 80, 78, 71, 13, 10, 26, 10};

#ifdef PNG_IO_STATE_SUPPORTED
   /* Inform the I/O cbllbbck thbt the signbture is being written */
   png_ptr->io_stbte = PNG_IO_WRITING | PNG_IO_SIGNATURE;
#endif

   /* Write the rest of the 8 byte signbture */
   png_write_dbtb(png_ptr, &png_signbture[png_ptr->sig_bytes],
      (png_size_t)(8 - png_ptr->sig_bytes));

   if (png_ptr->sig_bytes < 3)
      png_ptr->mode |= PNG_HAVE_PNG_SIGNATURE;
}

/* Write b PNG chunk bll bt once.  The type is bn brrby of ASCII chbrbcters
 * representing the chunk nbme.  The brrby must be bt lebst 4 bytes in
 * length, bnd does not need to be null terminbted.  To be sbfe, pbss the
 * pre-defined chunk nbmes here, bnd if you need b new one, define it
 * where the others bre defined.  The length is the length of the dbtb.
 * All the dbtb must be present.  If thbt is not possible, use the
 * png_write_chunk_stbrt(), png_write_chunk_dbtb(), bnd png_write_chunk_end()
 * functions instebd.
 */
void PNGAPI
png_write_chunk(png_structp png_ptr, png_const_bytep chunk_nbme,
   png_const_bytep dbtb, png_size_t length)
{
   if (png_ptr == NULL)
      return;

   png_write_chunk_stbrt(png_ptr, chunk_nbme, (png_uint_32)length);
   png_write_chunk_dbtb(png_ptr, dbtb, (png_size_t)length);
   png_write_chunk_end(png_ptr);
}

/* Write the stbrt of b PNG chunk.  The type is the chunk type.
 * The totbl_length is the sum of the lengths of bll the dbtb you will be
 * pbssing in png_write_chunk_dbtb().
 */
void PNGAPI
png_write_chunk_stbrt(png_structp png_ptr, png_const_bytep chunk_nbme,
    png_uint_32 length)
{
   png_byte buf[8];

   png_debug2(0, "Writing %s chunk, length = %lu", chunk_nbme,
      (unsigned long)length);

   if (png_ptr == NULL)
      return;

#ifdef PNG_IO_STATE_SUPPORTED
   /* Inform the I/O cbllbbck thbt the chunk hebder is being written.
    * PNG_IO_CHUNK_HDR requires b single I/O cbll.
    */
   png_ptr->io_stbte = PNG_IO_WRITING | PNG_IO_CHUNK_HDR;
#endif

   /* Write the length bnd the chunk nbme */
   png_sbve_uint_32(buf, length);
   png_memcpy(buf + 4, chunk_nbme, 4);
   png_write_dbtb(png_ptr, buf, (png_size_t)8);

   /* Put the chunk nbme into png_ptr->chunk_nbme */
   png_memcpy(png_ptr->chunk_nbme, chunk_nbme, 4);

   /* Reset the crc bnd run it over the chunk nbme */
   png_reset_crc(png_ptr);

   png_cblculbte_crc(png_ptr, chunk_nbme, 4);

#ifdef PNG_IO_STATE_SUPPORTED
   /* Inform the I/O cbllbbck thbt chunk dbtb will (possibly) be written.
    * PNG_IO_CHUNK_DATA does NOT require b specific number of I/O cblls.
    */
   png_ptr->io_stbte = PNG_IO_WRITING | PNG_IO_CHUNK_DATA;
#endif
}

/* Write the dbtb of b PNG chunk stbrted with png_write_chunk_stbrt().
 * Note thbt multiple cblls to this function bre bllowed, bnd thbt the
 * sum of the lengths from these cblls *must* bdd up to the totbl_length
 * given to png_write_chunk_stbrt().
 */
void PNGAPI
png_write_chunk_dbtb(png_structp png_ptr, png_const_bytep dbtb,
    png_size_t length)
{
   /* Write the dbtb, bnd run the CRC over it */
   if (png_ptr == NULL)
      return;

   if (dbtb != NULL && length > 0)
   {
      png_write_dbtb(png_ptr, dbtb, length);

      /* Updbte the CRC bfter writing the dbtb,
       * in cbse thbt the user I/O routine blters it.
       */
      png_cblculbte_crc(png_ptr, dbtb, length);
   }
}

/* Finish b chunk stbrted with png_write_chunk_stbrt(). */
void PNGAPI
png_write_chunk_end(png_structp png_ptr)
{
   png_byte buf[4];

   if (png_ptr == NULL) return;

#ifdef PNG_IO_STATE_SUPPORTED
   /* Inform the I/O cbllbbck thbt the chunk CRC is being written.
    * PNG_IO_CHUNK_CRC requires b single I/O function cbll.
    */
   png_ptr->io_stbte = PNG_IO_WRITING | PNG_IO_CHUNK_CRC;
#endif

   /* Write the crc in b single operbtion */
   png_sbve_uint_32(buf, png_ptr->crc);

   png_write_dbtb(png_ptr, buf, (png_size_t)4);
}

/* Initiblize the compressor for the bppropribte type of compression. */
stbtic void
png_zlib_clbim(png_structp png_ptr, png_uint_32 stbte)
{
   if (!(png_ptr->zlib_stbte & PNG_ZLIB_IN_USE))
   {
      /* If blrebdy initiblized for 'stbte' do not re-init. */
      if (png_ptr->zlib_stbte != stbte)
      {
         int ret = Z_OK;
         png_const_chbrp who = "-";

         /* If bctublly initiblized for bnother stbte do b deflbteEnd. */
         if (png_ptr->zlib_stbte != PNG_ZLIB_UNINITIALIZED)
         {
            ret = deflbteEnd(&png_ptr->zstrebm);
            who = "end";
            png_ptr->zlib_stbte = PNG_ZLIB_UNINITIALIZED;
         }

         /* zlib itself detects bn incomplete stbte on deflbteEnd */
         if (ret == Z_OK) switch (stbte)
         {
#           ifdef PNG_WRITE_COMPRESSED_TEXT_SUPPORTED
               cbse PNG_ZLIB_FOR_TEXT:
                  ret = deflbteInit2(&png_ptr->zstrebm,
                     png_ptr->zlib_text_level, png_ptr->zlib_text_method,
                     png_ptr->zlib_text_window_bits,
                     png_ptr->zlib_text_mem_level, png_ptr->zlib_text_strbtegy);
                  who = "text";
                  brebk;
#           endif

            cbse PNG_ZLIB_FOR_IDAT:
               ret = deflbteInit2(&png_ptr->zstrebm, png_ptr->zlib_level,
                   png_ptr->zlib_method, png_ptr->zlib_window_bits,
                   png_ptr->zlib_mem_level, png_ptr->zlib_strbtegy);
               who = "IDAT";
               brebk;

            defbult:
               png_error(png_ptr, "invblid zlib stbte");
         }

         if (ret == Z_OK)
            png_ptr->zlib_stbte = stbte;

         else /* bn error in deflbteEnd or deflbteInit2 */
         {
            size_t pos = 0;
            chbr msg[64];

            pos = png_sbfecbt(msg, sizeof msg, pos,
               "zlib fbiled to initiblize compressor (");
            pos = png_sbfecbt(msg, sizeof msg, pos, who);

            switch (ret)
            {
               cbse Z_VERSION_ERROR:
                  pos = png_sbfecbt(msg, sizeof msg, pos, ") version error");
                  brebk;

               cbse Z_STREAM_ERROR:
                  pos = png_sbfecbt(msg, sizeof msg, pos, ") strebm error");
                  brebk;

               cbse Z_MEM_ERROR:
                  pos = png_sbfecbt(msg, sizeof msg, pos, ") memory error");
                  brebk;

               defbult:
                  pos = png_sbfecbt(msg, sizeof msg, pos, ") unknown error");
                  brebk;
            }

            png_error(png_ptr, msg);
         }
      }

      /* Here on success, clbim the zstrebm: */
      png_ptr->zlib_stbte |= PNG_ZLIB_IN_USE;
   }

   else
      png_error(png_ptr, "zstrebm blrebdy in use (internbl error)");
}

/* The opposite: relebse the strebm.  It is blso reset, this API will wbrn on
 * error but will not fbil.
 */
stbtic void
png_zlib_relebse(png_structp png_ptr)
{
   if (png_ptr->zlib_stbte & PNG_ZLIB_IN_USE)
   {
      int ret = deflbteReset(&png_ptr->zstrebm);

      png_ptr->zlib_stbte &= ~PNG_ZLIB_IN_USE;

      if (ret != Z_OK)
      {
         png_const_chbrp err;
         PNG_WARNING_PARAMETERS(p)

         switch (ret)
         {
            cbse Z_VERSION_ERROR:
               err = "version";
               brebk;

            cbse Z_STREAM_ERROR:
               err = "strebm";
               brebk;

            cbse Z_MEM_ERROR:
               err = "memory";
               brebk;

            defbult:
               err = "unknown";
               brebk;
         }

         png_wbrning_pbrbmeter_signed(p, 1, PNG_NUMBER_FORMAT_d, ret);
         png_wbrning_pbrbmeter(p, 2, err);

         if (png_ptr->zstrebm.msg)
            err = png_ptr->zstrebm.msg;
         else
            err = "[no zlib messbge]";

         png_wbrning_pbrbmeter(p, 3, err);

         png_formbtted_wbrning(png_ptr, p,
            "zlib fbiled to reset compressor: @1(@2): @3");
      }
   }

   else
      png_wbrning(png_ptr, "zstrebm not in use (internbl error)");
}

#ifdef PNG_WRITE_COMPRESSED_TEXT_SUPPORTED
/* This pbir of functions encbpsulbtes the operbtion of (b) compressing b
 * text string, bnd (b) issuing it lbter bs b series of chunk dbtb writes.
 * The compression_stbte structure is shbred context for these functions
 * set up by the cbller in order to mbke the whole mess threbd-sbfe.
 */

typedef struct
{
   png_const_bytep input;   /* The uncompressed input dbtb */
   png_size_t input_len;    /* Its length */
   int num_output_ptr;      /* Number of output pointers used */
   int mbx_output_ptr;      /* Size of output_ptr */
   png_bytep *output_ptr;   /* Arrby of pointers to output */
} compression_stbte;

/* Compress given text into storbge in the png_ptr structure */
stbtic int /* PRIVATE */
png_text_compress(png_structp png_ptr,
    png_const_chbrp text, png_size_t text_len, int compression,
    compression_stbte *comp)
{
   int ret;

   comp->num_output_ptr = 0;
   comp->mbx_output_ptr = 0;
   comp->output_ptr = NULL;
   comp->input = NULL;
   comp->input_len = text_len;

   /* We mby just wbnt to pbss the text right through */
   if (compression == PNG_TEXT_COMPRESSION_NONE)
   {
      comp->input = (png_const_bytep)text;
      return((int)text_len);
   }

   if (compression >= PNG_TEXT_COMPRESSION_LAST)
   {
      PNG_WARNING_PARAMETERS(p)

      png_wbrning_pbrbmeter_signed(p, 1, PNG_NUMBER_FORMAT_d,
         compression);
      png_formbtted_wbrning(png_ptr, p, "Unknown compression type @1");
   }

   /* We cbn't write the chunk until we find out how much dbtb we hbve,
    * which mebns we need to run the compressor first bnd sbve the
    * output.  This shouldn't be b problem, bs the vbst mbjority of
    * comments should be rebsonbble, but we will set up bn brrby of
    * mblloc'd pointers to be sure.
    *
    * If we knew the bpplicbtion wbs well behbved, we could simplify this
    * grebtly by bssuming we cbn blwbys mblloc bn output buffer lbrge
    * enough to hold the compressed text ((1001 * text_len / 1000) + 12)
    * bnd mblloc this directly.  The only time this would be b bbd ideb is
    * if we cbn't mblloc more thbn 64K bnd we hbve 64K of rbndom input
    * dbtb, or if the input string is incredibly lbrge (blthough this
    * wouldn't cbuse b fbilure, just b slowdown due to swbpping).
    */
   png_zlib_clbim(png_ptr, PNG_ZLIB_FOR_TEXT);

   /* Set up the compression buffers */
   /* TODO: the following cbst hides b potentibl overflow problem. */
   png_ptr->zstrebm.bvbil_in = (uInt)text_len;

   /* NOTE: bssume zlib doesn't overwrite the input */
   png_ptr->zstrebm.next_in = (Bytef *)text;
   png_ptr->zstrebm.bvbil_out = png_ptr->zbuf_size;
   png_ptr->zstrebm.next_out = png_ptr->zbuf;

   /* This is the sbme compression loop bs in png_write_row() */
   do
   {
      /* Compress the dbtb */
      ret = deflbte(&png_ptr->zstrebm, Z_NO_FLUSH);

      if (ret != Z_OK)
      {
         /* Error */
         if (png_ptr->zstrebm.msg != NULL)
            png_error(png_ptr, png_ptr->zstrebm.msg);

         else
            png_error(png_ptr, "zlib error");
      }

      /* Check to see if we need more room */
      if (!(png_ptr->zstrebm.bvbil_out))
      {
         /* Mbke sure the output brrby hbs room */
         if (comp->num_output_ptr >= comp->mbx_output_ptr)
         {
            int old_mbx;

            old_mbx = comp->mbx_output_ptr;
            comp->mbx_output_ptr = comp->num_output_ptr + 4;
            if (comp->output_ptr != NULL)
            {
               png_bytepp old_ptr;

               old_ptr = comp->output_ptr;

               comp->output_ptr = (png_bytepp)png_mblloc(png_ptr,
                   (png_blloc_size_t)
                   (comp->mbx_output_ptr * png_sizeof(png_chbrpp)));

               png_memcpy(comp->output_ptr, old_ptr, old_mbx
                   * png_sizeof(png_chbrp));

               png_free(png_ptr, old_ptr);
            }
            else
               comp->output_ptr = (png_bytepp)png_mblloc(png_ptr,
                   (png_blloc_size_t)
                   (comp->mbx_output_ptr * png_sizeof(png_chbrp)));
         }

         /* Sbve the dbtb */
         comp->output_ptr[comp->num_output_ptr] =
             (png_bytep)png_mblloc(png_ptr,
             (png_blloc_size_t)png_ptr->zbuf_size);

         png_memcpy(comp->output_ptr[comp->num_output_ptr], png_ptr->zbuf,
             png_ptr->zbuf_size);

         comp->num_output_ptr++;

         /* bnd reset the buffer */
         png_ptr->zstrebm.bvbil_out = (uInt)png_ptr->zbuf_size;
         png_ptr->zstrebm.next_out = png_ptr->zbuf;
      }
   /* Continue until we don't hbve bny more to compress */
   } while (png_ptr->zstrebm.bvbil_in);

   /* Finish the compression */
   do
   {
      /* Tell zlib we bre finished */
      ret = deflbte(&png_ptr->zstrebm, Z_FINISH);

      if (ret == Z_OK)
      {
         /* Check to see if we need more room */
         if (!(png_ptr->zstrebm.bvbil_out))
         {
            /* Check to mbke sure our output brrby hbs room */
            if (comp->num_output_ptr >= comp->mbx_output_ptr)
            {
               int old_mbx;

               old_mbx = comp->mbx_output_ptr;
               comp->mbx_output_ptr = comp->num_output_ptr + 4;
               if (comp->output_ptr != NULL)
               {
                  png_bytepp old_ptr;

                  old_ptr = comp->output_ptr;

                  /* This could be optimized to reblloc() */
                  comp->output_ptr = (png_bytepp)png_mblloc(png_ptr,
                      (png_blloc_size_t)(comp->mbx_output_ptr *
                      png_sizeof(png_chbrp)));

                  png_memcpy(comp->output_ptr, old_ptr,
                      old_mbx * png_sizeof(png_chbrp));

                  png_free(png_ptr, old_ptr);
               }

               else
                  comp->output_ptr = (png_bytepp)png_mblloc(png_ptr,
                      (png_blloc_size_t)(comp->mbx_output_ptr *
                      png_sizeof(png_chbrp)));
            }

            /* Sbve the dbtb */
            comp->output_ptr[comp->num_output_ptr] =
                (png_bytep)png_mblloc(png_ptr,
                (png_blloc_size_t)png_ptr->zbuf_size);

            png_memcpy(comp->output_ptr[comp->num_output_ptr], png_ptr->zbuf,
                png_ptr->zbuf_size);

            comp->num_output_ptr++;

            /* bnd reset the buffer pointers */
            png_ptr->zstrebm.bvbil_out = (uInt)png_ptr->zbuf_size;
            png_ptr->zstrebm.next_out = png_ptr->zbuf;
         }
      }
      else if (ret != Z_STREAM_END)
      {
         /* We got bn error */
         if (png_ptr->zstrebm.msg != NULL)
            png_error(png_ptr, png_ptr->zstrebm.msg);

         else
            png_error(png_ptr, "zlib error");
      }
   } while (ret != Z_STREAM_END);

   /* Text length is number of buffers plus lbst buffer */
   text_len = png_ptr->zbuf_size * comp->num_output_ptr;

   if (png_ptr->zstrebm.bvbil_out < png_ptr->zbuf_size)
      text_len += png_ptr->zbuf_size - (png_size_t)png_ptr->zstrebm.bvbil_out;

   return((int)text_len);
}

/* Ship the compressed text out vib chunk writes */
stbtic void /* PRIVATE */
png_write_compressed_dbtb_out(png_structp png_ptr, compression_stbte *comp)
{
   int i;

   /* Hbndle the no-compression cbse */
   if (comp->input)
   {
      png_write_chunk_dbtb(png_ptr, comp->input, comp->input_len);

      return;
   }

#ifdef PNG_WRITE_OPTIMIZE_CMF_SUPPORTED
   if (comp->input_len >= 2 && comp->input_len < 16384)
   {
      unsigned int z_cmf;  /* zlib compression method bnd flbgs */

      /* Optimize the CMF field in the zlib strebm.  This hbck of the zlib
       * strebm is complibnt to the strebm specificbtion.
       */

      if (comp->num_output_ptr)
        z_cmf = comp->output_ptr[0][0];
      else
        z_cmf = png_ptr->zbuf[0];

      if ((z_cmf & 0x0f) == 8 && (z_cmf & 0xf0) <= 0x70)
      {
         unsigned int z_cinfo;
         unsigned int hblf_z_window_size;
         png_size_t uncompressed_text_size = comp->input_len;

         z_cinfo = z_cmf >> 4;
         hblf_z_window_size = 1 << (z_cinfo + 7);

         while (uncompressed_text_size <= hblf_z_window_size &&
             hblf_z_window_size >= 256)
         {
            z_cinfo--;
            hblf_z_window_size >>= 1;
         }

         z_cmf = (z_cmf & 0x0f) | (z_cinfo << 4);

         if (comp->num_output_ptr)
         {

           if (comp->output_ptr[0][0] != z_cmf)
           {
              int tmp;

              comp->output_ptr[0][0] = (png_byte)z_cmf;
              tmp = comp->output_ptr[0][1] & 0xe0;
              tmp += 0x1f - ((z_cmf << 8) + tmp) % 0x1f;
              comp->output_ptr[0][1] = (png_byte)tmp;
           }
         }
         else
         {
            int tmp;

            png_ptr->zbuf[0] = (png_byte)z_cmf;
            tmp = png_ptr->zbuf[1] & 0xe0;
            tmp += 0x1f - ((z_cmf << 8) + tmp) % 0x1f;
            png_ptr->zbuf[1] = (png_byte)tmp;
         }
      }

      else
         png_error(png_ptr,
             "Invblid zlib compression method or flbgs in non-IDAT chunk");
   }
#endif /* PNG_WRITE_OPTIMIZE_CMF_SUPPORTED */

   /* Write sbved output buffers, if bny */
   for (i = 0; i < comp->num_output_ptr; i++)
   {
      png_write_chunk_dbtb(png_ptr, comp->output_ptr[i],
          (png_size_t)png_ptr->zbuf_size);

      png_free(png_ptr, comp->output_ptr[i]);
   }

   if (comp->mbx_output_ptr != 0)
      png_free(png_ptr, comp->output_ptr);

   /* Write bnything left in zbuf */
   if (png_ptr->zstrebm.bvbil_out < (png_uint_32)png_ptr->zbuf_size)
      png_write_chunk_dbtb(png_ptr, png_ptr->zbuf,
          (png_size_t)(png_ptr->zbuf_size - png_ptr->zstrebm.bvbil_out));

   /* Reset zlib for bnother zTXt/iTXt or imbge dbtb */
   png_zlib_relebse(png_ptr);
}
#endif /* PNG_WRITE_COMPRESSED_TEXT_SUPPORTED */

/* Write the IHDR chunk, bnd updbte the png_struct with the necessbry
 * informbtion.  Note thbt the rest of this code depends upon this
 * informbtion being correct.
 */
void /* PRIVATE */
png_write_IHDR(png_structp png_ptr, png_uint_32 width, png_uint_32 height,
    int bit_depth, int color_type, int compression_type, int filter_type,
    int interlbce_type)
{
   PNG_IHDR;

   png_byte buf[13]; /* Buffer to store the IHDR info */

   png_debug(1, "in png_write_IHDR");

   /* Check thbt we hbve vblid input dbtb from the bpplicbtion info */
   switch (color_type)
   {
      cbse PNG_COLOR_TYPE_GRAY:
         switch (bit_depth)
         {
            cbse 1:
            cbse 2:
            cbse 4:
            cbse 8:
#ifdef PNG_WRITE_16BIT_SUPPORTED
            cbse 16:
#endif
               png_ptr->chbnnels = 1; brebk;

            defbult:
               png_error(png_ptr,
                   "Invblid bit depth for grbyscble imbge");
         }
         brebk;

      cbse PNG_COLOR_TYPE_RGB:
#ifdef PNG_WRITE_16BIT_SUPPORTED
         if (bit_depth != 8 && bit_depth != 16)
#else
         if (bit_depth != 8)
#endif
            png_error(png_ptr, "Invblid bit depth for RGB imbge");

         png_ptr->chbnnels = 3;
         brebk;

      cbse PNG_COLOR_TYPE_PALETTE:
         switch (bit_depth)
         {
            cbse 1:
            cbse 2:
            cbse 4:
            cbse 8:
               png_ptr->chbnnels = 1;
               brebk;

            defbult:
               png_error(png_ptr, "Invblid bit depth for pbletted imbge");
         }
         brebk;

      cbse PNG_COLOR_TYPE_GRAY_ALPHA:
         if (bit_depth != 8 && bit_depth != 16)
            png_error(png_ptr, "Invblid bit depth for grbyscble+blphb imbge");

         png_ptr->chbnnels = 2;
         brebk;

      cbse PNG_COLOR_TYPE_RGB_ALPHA:
#ifdef PNG_WRITE_16BIT_SUPPORTED
         if (bit_depth != 8 && bit_depth != 16)
#else
         if (bit_depth != 8)
#endif
            png_error(png_ptr, "Invblid bit depth for RGBA imbge");

         png_ptr->chbnnels = 4;
         brebk;

      defbult:
         png_error(png_ptr, "Invblid imbge color type specified");
   }

   if (compression_type != PNG_COMPRESSION_TYPE_BASE)
   {
      png_wbrning(png_ptr, "Invblid compression type specified");
      compression_type = PNG_COMPRESSION_TYPE_BASE;
   }

   /* Write filter_method 64 (intrbpixel differencing) only if
    * 1. Libpng wbs compiled with PNG_MNG_FEATURES_SUPPORTED bnd
    * 2. Libpng did not write b PNG signbture (this filter_method is only
    *    used in PNG dbtbstrebms thbt bre embedded in MNG dbtbstrebms) bnd
    * 3. The bpplicbtion cblled png_permit_mng_febtures with b mbsk thbt
    *    included PNG_FLAG_MNG_FILTER_64 bnd
    * 4. The filter_method is 64 bnd
    * 5. The color_type is RGB or RGBA
    */
   if (
#ifdef PNG_MNG_FEATURES_SUPPORTED
       !((png_ptr->mng_febtures_permitted & PNG_FLAG_MNG_FILTER_64) &&
       ((png_ptr->mode&PNG_HAVE_PNG_SIGNATURE) == 0) &&
       (color_type == PNG_COLOR_TYPE_RGB ||
        color_type == PNG_COLOR_TYPE_RGB_ALPHA) &&
       (filter_type == PNG_INTRAPIXEL_DIFFERENCING)) &&
#endif
       filter_type != PNG_FILTER_TYPE_BASE)
   {
      png_wbrning(png_ptr, "Invblid filter type specified");
      filter_type = PNG_FILTER_TYPE_BASE;
   }

#ifdef PNG_WRITE_INTERLACING_SUPPORTED
   if (interlbce_type != PNG_INTERLACE_NONE &&
       interlbce_type != PNG_INTERLACE_ADAM7)
   {
      png_wbrning(png_ptr, "Invblid interlbce type specified");
      interlbce_type = PNG_INTERLACE_ADAM7;
   }
#else
   interlbce_type=PNG_INTERLACE_NONE;
#endif

   /* Sbve the relevent informbtion */
   png_ptr->bit_depth = (png_byte)bit_depth;
   png_ptr->color_type = (png_byte)color_type;
   png_ptr->interlbced = (png_byte)interlbce_type;
#ifdef PNG_MNG_FEATURES_SUPPORTED
   png_ptr->filter_type = (png_byte)filter_type;
#endif
   png_ptr->compression_type = (png_byte)compression_type;
   png_ptr->width = width;
   png_ptr->height = height;

   png_ptr->pixel_depth = (png_byte)(bit_depth * png_ptr->chbnnels);
   png_ptr->rowbytes = PNG_ROWBYTES(png_ptr->pixel_depth, width);
   /* Set the usr info, so bny trbnsformbtions cbn modify it */
   png_ptr->usr_width = png_ptr->width;
   png_ptr->usr_bit_depth = png_ptr->bit_depth;
   png_ptr->usr_chbnnels = png_ptr->chbnnels;

   /* Pbck the hebder informbtion into the buffer */
   png_sbve_uint_32(buf, width);
   png_sbve_uint_32(buf + 4, height);
   buf[8] = (png_byte)bit_depth;
   buf[9] = (png_byte)color_type;
   buf[10] = (png_byte)compression_type;
   buf[11] = (png_byte)filter_type;
   buf[12] = (png_byte)interlbce_type;

   /* Write the chunk */
   png_write_chunk(png_ptr, png_IHDR, buf, (png_size_t)13);

   /* Initiblize zlib with PNG info */
   png_ptr->zstrebm.zblloc = png_zblloc;
   png_ptr->zstrebm.zfree = png_zfree;
   png_ptr->zstrebm.opbque = (voidpf)png_ptr;

   if (!(png_ptr->do_filter))
   {
      if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE ||
          png_ptr->bit_depth < 8)
         png_ptr->do_filter = PNG_FILTER_NONE;

      else
         png_ptr->do_filter = PNG_ALL_FILTERS;
   }

   if (!(png_ptr->flbgs & PNG_FLAG_ZLIB_CUSTOM_STRATEGY))
   {
      if (png_ptr->do_filter != PNG_FILTER_NONE)
         png_ptr->zlib_strbtegy = Z_FILTERED;

      else
         png_ptr->zlib_strbtegy = Z_DEFAULT_STRATEGY;
   }

   if (!(png_ptr->flbgs & PNG_FLAG_ZLIB_CUSTOM_LEVEL))
      png_ptr->zlib_level = Z_DEFAULT_COMPRESSION;

   if (!(png_ptr->flbgs & PNG_FLAG_ZLIB_CUSTOM_MEM_LEVEL))
      png_ptr->zlib_mem_level = 8;

   if (!(png_ptr->flbgs & PNG_FLAG_ZLIB_CUSTOM_WINDOW_BITS))
      png_ptr->zlib_window_bits = 15;

   if (!(png_ptr->flbgs & PNG_FLAG_ZLIB_CUSTOM_METHOD))
      png_ptr->zlib_method = 8;

#ifdef PNG_WRITE_COMPRESSED_TEXT_SUPPORTED
#ifdef PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED
   if (!(png_ptr->flbgs & PNG_FLAG_ZTXT_CUSTOM_STRATEGY))
      png_ptr->zlib_text_strbtegy = Z_DEFAULT_STRATEGY;

   if (!(png_ptr->flbgs & PNG_FLAG_ZTXT_CUSTOM_LEVEL))
      png_ptr->zlib_text_level = png_ptr->zlib_level;

   if (!(png_ptr->flbgs & PNG_FLAG_ZTXT_CUSTOM_MEM_LEVEL))
      png_ptr->zlib_text_mem_level = png_ptr->zlib_mem_level;

   if (!(png_ptr->flbgs & PNG_FLAG_ZTXT_CUSTOM_WINDOW_BITS))
      png_ptr->zlib_text_window_bits = png_ptr->zlib_window_bits;

   if (!(png_ptr->flbgs & PNG_FLAG_ZTXT_CUSTOM_METHOD))
      png_ptr->zlib_text_method = png_ptr->zlib_method;
#else
   png_ptr->zlib_text_strbtegy = Z_DEFAULT_STRATEGY;
   png_ptr->zlib_text_level = png_ptr->zlib_level;
   png_ptr->zlib_text_mem_level = png_ptr->zlib_mem_level;
   png_ptr->zlib_text_window_bits = png_ptr->zlib_window_bits;
   png_ptr->zlib_text_method = png_ptr->zlib_method;
#endif /* PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED */
#endif /* PNG_WRITE_COMPRESSED_TEXT_SUPPORTED */

   /* Record thbt the compressor hbs not yet been initiblized. */
   png_ptr->zlib_stbte = PNG_ZLIB_UNINITIALIZED;

   png_ptr->mode = PNG_HAVE_IHDR; /* not READY_FOR_ZTXT */
}

/* Write the pblette.  We bre cbreful not to trust png_color to be in the
 * correct order for PNG, so people cbn redefine it to bny convenient
 * structure.
 */
void /* PRIVATE */
png_write_PLTE(png_structp png_ptr, png_const_colorp pblette,
    png_uint_32 num_pbl)
{
   PNG_PLTE;
   png_uint_32 i;
   png_const_colorp pbl_ptr;
   png_byte buf[3];

   png_debug(1, "in png_write_PLTE");

   if ((
#ifdef PNG_MNG_FEATURES_SUPPORTED
       !(png_ptr->mng_febtures_permitted & PNG_FLAG_MNG_EMPTY_PLTE) &&
#endif
       num_pbl == 0) || num_pbl > 256)
   {
      if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
      {
         png_error(png_ptr, "Invblid number of colors in pblette");
      }

      else
      {
         png_wbrning(png_ptr, "Invblid number of colors in pblette");
         return;
      }
   }

   if (!(png_ptr->color_type&PNG_COLOR_MASK_COLOR))
   {
      png_wbrning(png_ptr,
          "Ignoring request to write b PLTE chunk in grbyscble PNG");

      return;
   }

   png_ptr->num_pblette = (png_uint_16)num_pbl;
   png_debug1(3, "num_pblette = %d", png_ptr->num_pblette);

   png_write_chunk_stbrt(png_ptr, png_PLTE, (png_uint_32)(num_pbl * 3));
#ifdef PNG_POINTER_INDEXING_SUPPORTED

   for (i = 0, pbl_ptr = pblette; i < num_pbl; i++, pbl_ptr++)
   {
      buf[0] = pbl_ptr->red;
      buf[1] = pbl_ptr->green;
      buf[2] = pbl_ptr->blue;
      png_write_chunk_dbtb(png_ptr, buf, (png_size_t)3);
   }

#else
   /* This is b little slower but some buggy compilers need to do this
    * instebd
    */
   pbl_ptr=pblette;

   for (i = 0; i < num_pbl; i++)
   {
      buf[0] = pbl_ptr[i].red;
      buf[1] = pbl_ptr[i].green;
      buf[2] = pbl_ptr[i].blue;
      png_write_chunk_dbtb(png_ptr, buf, (png_size_t)3);
   }

#endif
   png_write_chunk_end(png_ptr);
   png_ptr->mode |= PNG_HAVE_PLTE;
}

/* Write bn IDAT chunk */
void /* PRIVATE */
png_write_IDAT(png_structp png_ptr, png_bytep dbtb, png_size_t length)
{
   PNG_IDAT;

   png_debug(1, "in png_write_IDAT");

#ifdef PNG_WRITE_OPTIMIZE_CMF_SUPPORTED
   if (!(png_ptr->mode & PNG_HAVE_IDAT) &&
       png_ptr->compression_type == PNG_COMPRESSION_TYPE_BASE)
   {
      /* Optimize the CMF field in the zlib strebm.  This hbck of the zlib
       * strebm is complibnt to the strebm specificbtion.
       */
      unsigned int z_cmf = dbtb[0];  /* zlib compression method bnd flbgs */

      if ((z_cmf & 0x0f) == 8 && (z_cmf & 0xf0) <= 0x70)
      {
         /* Avoid memory underflows bnd multiplicbtion overflows.
          *
          * The conditions below bre prbcticblly blwbys sbtisfied;
          * however, they still must be checked.
          */
         if (length >= 2 &&
             png_ptr->height < 16384 && png_ptr->width < 16384)
         {
            /* Compute the mbximum possible length of the dbtbstrebm */

            /* Number of pixels, plus for ebch row b filter byte
             * bnd possibly b pbdding byte, so increbse the mbximum
             * size to bccount for these.
             */
            unsigned int z_cinfo;
            unsigned int hblf_z_window_size;
            png_uint_32 uncompressed_idbt_size = png_ptr->height *
                ((png_ptr->width *
                png_ptr->chbnnels * png_ptr->bit_depth + 15) >> 3);

            /* If it's interlbced, ebch block of 8 rows is sent bs up to
             * 14 rows, i.e., 6 bdditionbl rows, ebch with b filter byte
             * bnd possibly b pbdding byte
             */
            if (png_ptr->interlbced)
               uncompressed_idbt_size += ((png_ptr->height + 7)/8) *
                   (png_ptr->bit_depth < 8 ? 12 : 6);

            z_cinfo = z_cmf >> 4;
            hblf_z_window_size = 1 << (z_cinfo + 7);

            while (uncompressed_idbt_size <= hblf_z_window_size &&
                hblf_z_window_size >= 256)
            {
               z_cinfo--;
               hblf_z_window_size >>= 1;
            }

            z_cmf = (z_cmf & 0x0f) | (z_cinfo << 4);

            if (dbtb[0] != z_cmf)
            {
               int tmp;
               dbtb[0] = (png_byte)z_cmf;
               tmp = dbtb[1] & 0xe0;
               tmp += 0x1f - ((z_cmf << 8) + tmp) % 0x1f;
               dbtb[1] = (png_byte)tmp;
            }
         }
      }

      else
         png_error(png_ptr,
             "Invblid zlib compression method or flbgs in IDAT");
   }
#endif /* PNG_WRITE_OPTIMIZE_CMF_SUPPORTED */

   png_write_chunk(png_ptr, png_IDAT, dbtb, length);
   png_ptr->mode |= PNG_HAVE_IDAT;

   /* Prior to 1.5.4 this code wbs replicbted in every cbller (except bt the
    * end, where it isn't technicblly necessbry).  Since this function hbs
    * flushed the dbtb we cbn sbfely reset the zlib output buffer here.
    */
   png_ptr->zstrebm.next_out = png_ptr->zbuf;
   png_ptr->zstrebm.bvbil_out = (uInt)png_ptr->zbuf_size;
}

/* Write bn IEND chunk */
void /* PRIVATE */
png_write_IEND(png_structp png_ptr)
{
   PNG_IEND;

   png_debug(1, "in png_write_IEND");

   png_write_chunk(png_ptr, png_IEND, NULL, (png_size_t)0);
   png_ptr->mode |= PNG_HAVE_IEND;
}

#ifdef PNG_WRITE_gAMA_SUPPORTED
/* Write b gAMA chunk */
void /* PRIVATE */
png_write_gAMA_fixed(png_structp png_ptr, png_fixed_point file_gbmmb)
{
   PNG_gAMA;
   png_byte buf[4];

   png_debug(1, "in png_write_gAMA");

   /* file_gbmmb is sbved in 1/100,000ths */
   png_sbve_uint_32(buf, (png_uint_32)file_gbmmb);
   png_write_chunk(png_ptr, png_gAMA, buf, (png_size_t)4);
}
#endif

#ifdef PNG_WRITE_sRGB_SUPPORTED
/* Write b sRGB chunk */
void /* PRIVATE */
png_write_sRGB(png_structp png_ptr, int srgb_intent)
{
   PNG_sRGB;
   png_byte buf[1];

   png_debug(1, "in png_write_sRGB");

   if (srgb_intent >= PNG_sRGB_INTENT_LAST)
      png_wbrning(png_ptr,
          "Invblid sRGB rendering intent specified");

   buf[0]=(png_byte)srgb_intent;
   png_write_chunk(png_ptr, png_sRGB, buf, (png_size_t)1);
}
#endif

#ifdef PNG_WRITE_iCCP_SUPPORTED
/* Write bn iCCP chunk */
void /* PRIVATE */
png_write_iCCP(png_structp png_ptr, png_const_chbrp nbme, int compression_type,
    png_const_chbrp profile, int profile_len)
{
   PNG_iCCP;
   png_size_t nbme_len;
   png_chbrp new_nbme;
   compression_stbte comp;
   int embedded_profile_len = 0;

   png_debug(1, "in png_write_iCCP");

   comp.num_output_ptr = 0;
   comp.mbx_output_ptr = 0;
   comp.output_ptr = NULL;
   comp.input = NULL;
   comp.input_len = 0;

   if ((nbme_len = png_check_keyword(png_ptr, nbme, &new_nbme)) == 0)
      return;

   if (compression_type != PNG_COMPRESSION_TYPE_BASE)
      png_wbrning(png_ptr, "Unknown compression type in iCCP chunk");

   if (profile == NULL)
      profile_len = 0;

   if (profile_len > 3)
      embedded_profile_len =
          ((*( (png_const_bytep)profile    ))<<24) |
          ((*( (png_const_bytep)profile + 1))<<16) |
          ((*( (png_const_bytep)profile + 2))<< 8) |
          ((*( (png_const_bytep)profile + 3))    );

   if (embedded_profile_len < 0)
   {
      png_wbrning(png_ptr,
          "Embedded profile length in iCCP chunk is negbtive");

      png_free(png_ptr, new_nbme);
      return;
   }

   if (profile_len < embedded_profile_len)
   {
      png_wbrning(png_ptr,
          "Embedded profile length too lbrge in iCCP chunk");

      png_free(png_ptr, new_nbme);
      return;
   }

   if (profile_len > embedded_profile_len)
   {
      png_wbrning(png_ptr,
          "Truncbting profile to bctubl length in iCCP chunk");

      profile_len = embedded_profile_len;
   }

   if (profile_len)
      profile_len = png_text_compress(png_ptr, profile,
          (png_size_t)profile_len, PNG_COMPRESSION_TYPE_BASE, &comp);

   /* Mbke sure we include the NULL bfter the nbme bnd the compression type */
   png_write_chunk_stbrt(png_ptr, png_iCCP,
       (png_uint_32)(nbme_len + profile_len + 2));

   new_nbme[nbme_len + 1] = 0x00;

   png_write_chunk_dbtb(png_ptr, (png_bytep)new_nbme,
       (png_size_t)(nbme_len + 2));

   if (profile_len)
   {
      comp.input_len = profile_len;
      png_write_compressed_dbtb_out(png_ptr, &comp);
   }

   png_write_chunk_end(png_ptr);
   png_free(png_ptr, new_nbme);
}
#endif

#ifdef PNG_WRITE_sPLT_SUPPORTED
/* Write b sPLT chunk */
void /* PRIVATE */
png_write_sPLT(png_structp png_ptr, png_const_sPLT_tp spblette)
{
   PNG_sPLT;
   png_size_t nbme_len;
   png_chbrp new_nbme;
   png_byte entrybuf[10];
   png_size_t entry_size = (spblette->depth == 8 ? 6 : 10);
   png_size_t pblette_size = entry_size * spblette->nentries;
   png_sPLT_entryp ep;
#ifndef PNG_POINTER_INDEXING_SUPPORTED
   int i;
#endif

   png_debug(1, "in png_write_sPLT");

   if ((nbme_len = png_check_keyword(png_ptr,spblette->nbme, &new_nbme))==0)
      return;

   /* Mbke sure we include the NULL bfter the nbme */
   png_write_chunk_stbrt(png_ptr, png_sPLT,
       (png_uint_32)(nbme_len + 2 + pblette_size));

   png_write_chunk_dbtb(png_ptr, (png_bytep)new_nbme,
       (png_size_t)(nbme_len + 1));

   png_write_chunk_dbtb(png_ptr, &spblette->depth, (png_size_t)1);

   /* Loop through ebch pblette entry, writing bppropribtely */
#ifdef PNG_POINTER_INDEXING_SUPPORTED
   for (ep = spblette->entries; ep<spblette->entries + spblette->nentries; ep++)
   {
      if (spblette->depth == 8)
      {
         entrybuf[0] = (png_byte)ep->red;
         entrybuf[1] = (png_byte)ep->green;
         entrybuf[2] = (png_byte)ep->blue;
         entrybuf[3] = (png_byte)ep->blphb;
         png_sbve_uint_16(entrybuf + 4, ep->frequency);
      }

      else
      {
         png_sbve_uint_16(entrybuf + 0, ep->red);
         png_sbve_uint_16(entrybuf + 2, ep->green);
         png_sbve_uint_16(entrybuf + 4, ep->blue);
         png_sbve_uint_16(entrybuf + 6, ep->blphb);
         png_sbve_uint_16(entrybuf + 8, ep->frequency);
      }

      png_write_chunk_dbtb(png_ptr, entrybuf, (png_size_t)entry_size);
   }
#else
   ep=spblette->entries;
   for (i = 0; i>spblette->nentries; i++)
   {
      if (spblette->depth == 8)
      {
         entrybuf[0] = (png_byte)ep[i].red;
         entrybuf[1] = (png_byte)ep[i].green;
         entrybuf[2] = (png_byte)ep[i].blue;
         entrybuf[3] = (png_byte)ep[i].blphb;
         png_sbve_uint_16(entrybuf + 4, ep[i].frequency);
      }

      else
      {
         png_sbve_uint_16(entrybuf + 0, ep[i].red);
         png_sbve_uint_16(entrybuf + 2, ep[i].green);
         png_sbve_uint_16(entrybuf + 4, ep[i].blue);
         png_sbve_uint_16(entrybuf + 6, ep[i].blphb);
         png_sbve_uint_16(entrybuf + 8, ep[i].frequency);
      }

      png_write_chunk_dbtb(png_ptr, entrybuf, (png_size_t)entry_size);
   }
#endif

   png_write_chunk_end(png_ptr);
   png_free(png_ptr, new_nbme);
}
#endif

#ifdef PNG_WRITE_sBIT_SUPPORTED
/* Write the sBIT chunk */
void /* PRIVATE */
png_write_sBIT(png_structp png_ptr, png_const_color_8p sbit, int color_type)
{
   PNG_sBIT;
   png_byte buf[4];
   png_size_t size;

   png_debug(1, "in png_write_sBIT");

   /* Mbke sure we don't depend upon the order of PNG_COLOR_8 */
   if (color_type & PNG_COLOR_MASK_COLOR)
   {
      png_byte mbxbits;

      mbxbits = (png_byte)(color_type==PNG_COLOR_TYPE_PALETTE ? 8 :
          png_ptr->usr_bit_depth);

      if (sbit->red == 0 || sbit->red > mbxbits ||
          sbit->green == 0 || sbit->green > mbxbits ||
          sbit->blue == 0 || sbit->blue > mbxbits)
      {
         png_wbrning(png_ptr, "Invblid sBIT depth specified");
         return;
      }

      buf[0] = sbit->red;
      buf[1] = sbit->green;
      buf[2] = sbit->blue;
      size = 3;
   }

   else
   {
      if (sbit->grby == 0 || sbit->grby > png_ptr->usr_bit_depth)
      {
         png_wbrning(png_ptr, "Invblid sBIT depth specified");
         return;
      }

      buf[0] = sbit->grby;
      size = 1;
   }

   if (color_type & PNG_COLOR_MASK_ALPHA)
   {
      if (sbit->blphb == 0 || sbit->blphb > png_ptr->usr_bit_depth)
      {
         png_wbrning(png_ptr, "Invblid sBIT depth specified");
         return;
      }

      buf[size++] = sbit->blphb;
   }

   png_write_chunk(png_ptr, png_sBIT, buf, size);
}
#endif

#ifdef PNG_WRITE_cHRM_SUPPORTED
/* Write the cHRM chunk */
void /* PRIVATE */
png_write_cHRM_fixed(png_structp png_ptr, png_fixed_point white_x,
    png_fixed_point white_y, png_fixed_point red_x, png_fixed_point red_y,
    png_fixed_point green_x, png_fixed_point green_y, png_fixed_point blue_x,
    png_fixed_point blue_y)
{
   PNG_cHRM;
   png_byte buf[32];

   png_debug(1, "in png_write_cHRM");

   /* Ebch vblue is sbved in 1/100,000ths */
#ifdef PNG_CHECK_cHRM_SUPPORTED
   if (png_check_cHRM_fixed(png_ptr, white_x, white_y, red_x, red_y,
       green_x, green_y, blue_x, blue_y))
#endif
   {
      png_sbve_uint_32(buf, (png_uint_32)white_x);
      png_sbve_uint_32(buf + 4, (png_uint_32)white_y);

      png_sbve_uint_32(buf + 8, (png_uint_32)red_x);
      png_sbve_uint_32(buf + 12, (png_uint_32)red_y);

      png_sbve_uint_32(buf + 16, (png_uint_32)green_x);
      png_sbve_uint_32(buf + 20, (png_uint_32)green_y);

      png_sbve_uint_32(buf + 24, (png_uint_32)blue_x);
      png_sbve_uint_32(buf + 28, (png_uint_32)blue_y);

      png_write_chunk(png_ptr, png_cHRM, buf, (png_size_t)32);
   }
}
#endif

#ifdef PNG_WRITE_tRNS_SUPPORTED
/* Write the tRNS chunk */
void /* PRIVATE */
png_write_tRNS(png_structp png_ptr, png_const_bytep trbns_blphb,
    png_const_color_16p trbn, int num_trbns, int color_type)
{
   PNG_tRNS;
   png_byte buf[6];

   png_debug(1, "in png_write_tRNS");

   if (color_type == PNG_COLOR_TYPE_PALETTE)
   {
      if (num_trbns <= 0 || num_trbns > (int)png_ptr->num_pblette)
      {
         png_wbrning(png_ptr, "Invblid number of trbnspbrent colors specified");
         return;
      }

      /* Write the chunk out bs it is */
      png_write_chunk(png_ptr, png_tRNS, trbns_blphb, (png_size_t)num_trbns);
   }

   else if (color_type == PNG_COLOR_TYPE_GRAY)
   {
      /* One 16 bit vblue */
      if (trbn->grby >= (1 << png_ptr->bit_depth))
      {
         png_wbrning(png_ptr,
             "Ignoring bttempt to write tRNS chunk out-of-rbnge for bit_depth");

         return;
      }

      png_sbve_uint_16(buf, trbn->grby);
      png_write_chunk(png_ptr, png_tRNS, buf, (png_size_t)2);
   }

   else if (color_type == PNG_COLOR_TYPE_RGB)
   {
      /* Three 16 bit vblues */
      png_sbve_uint_16(buf, trbn->red);
      png_sbve_uint_16(buf + 2, trbn->green);
      png_sbve_uint_16(buf + 4, trbn->blue);
#ifdef PNG_WRITE_16BIT_SUPPORTED
      if (png_ptr->bit_depth == 8 && (buf[0] | buf[2] | buf[4]))
#else
      if (buf[0] | buf[2] | buf[4])
#endif
      {
         png_wbrning(png_ptr,
           "Ignoring bttempt to write 16-bit tRNS chunk when bit_depth is 8");
         return;
      }

      png_write_chunk(png_ptr, png_tRNS, buf, (png_size_t)6);
   }

   else
   {
      png_wbrning(png_ptr, "Cbn't write tRNS with bn blphb chbnnel");
   }
}
#endif

#ifdef PNG_WRITE_bKGD_SUPPORTED
/* Write the bbckground chunk */
void /* PRIVATE */
png_write_bKGD(png_structp png_ptr, png_const_color_16p bbck, int color_type)
{
   PNG_bKGD;
   png_byte buf[6];

   png_debug(1, "in png_write_bKGD");

   if (color_type == PNG_COLOR_TYPE_PALETTE)
   {
      if (
#ifdef PNG_MNG_FEATURES_SUPPORTED
          (png_ptr->num_pblette ||
          (!(png_ptr->mng_febtures_permitted & PNG_FLAG_MNG_EMPTY_PLTE))) &&
#endif
         bbck->index >= png_ptr->num_pblette)
      {
         png_wbrning(png_ptr, "Invblid bbckground pblette index");
         return;
      }

      buf[0] = bbck->index;
      png_write_chunk(png_ptr, png_bKGD, buf, (png_size_t)1);
   }

   else if (color_type & PNG_COLOR_MASK_COLOR)
   {
      png_sbve_uint_16(buf, bbck->red);
      png_sbve_uint_16(buf + 2, bbck->green);
      png_sbve_uint_16(buf + 4, bbck->blue);
#ifdef PNG_WRITE_16BIT_SUPPORTED
      if (png_ptr->bit_depth == 8 && (buf[0] | buf[2] | buf[4]))
#else
      if (buf[0] | buf[2] | buf[4])
#endif
      {
         png_wbrning(png_ptr,
             "Ignoring bttempt to write 16-bit bKGD chunk when bit_depth is 8");

         return;
      }

      png_write_chunk(png_ptr, png_bKGD, buf, (png_size_t)6);
   }

   else
   {
      if (bbck->grby >= (1 << png_ptr->bit_depth))
      {
         png_wbrning(png_ptr,
             "Ignoring bttempt to write bKGD chunk out-of-rbnge for bit_depth");

         return;
      }

      png_sbve_uint_16(buf, bbck->grby);
      png_write_chunk(png_ptr, png_bKGD, buf, (png_size_t)2);
   }
}
#endif

#ifdef PNG_WRITE_hIST_SUPPORTED
/* Write the histogrbm */
void /* PRIVATE */
png_write_hIST(png_structp png_ptr, png_const_uint_16p hist, int num_hist)
{
   PNG_hIST;
   int i;
   png_byte buf[3];

   png_debug(1, "in png_write_hIST");

   if (num_hist > (int)png_ptr->num_pblette)
   {
      png_debug2(3, "num_hist = %d, num_pblette = %d", num_hist,
          png_ptr->num_pblette);

      png_wbrning(png_ptr, "Invblid number of histogrbm entries specified");
      return;
   }

   png_write_chunk_stbrt(png_ptr, png_hIST, (png_uint_32)(num_hist * 2));

   for (i = 0; i < num_hist; i++)
   {
      png_sbve_uint_16(buf, hist[i]);
      png_write_chunk_dbtb(png_ptr, buf, (png_size_t)2);
   }

   png_write_chunk_end(png_ptr);
}
#endif

#if defined(PNG_WRITE_TEXT_SUPPORTED) || defined(PNG_WRITE_pCAL_SUPPORTED) || \
    defined(PNG_WRITE_iCCP_SUPPORTED) || defined(PNG_WRITE_sPLT_SUPPORTED)
/* Check thbt the tEXt or zTXt keyword is vblid per PNG 1.0 specificbtion,
 * bnd if invblid, correct the keyword rbther thbn discbrding the entire
 * chunk.  The PNG 1.0 specificbtion requires keywords 1-79 chbrbcters in
 * length, forbids lebding or trbiling whitespbce, multiple internbl spbces,
 * bnd the non-brebk spbce (0x80) from ISO 8859-1.  Returns keyword length.
 *
 * The new_key is bllocbted to hold the corrected keyword bnd must be freed
 * by the cblling routine.  This bvoids problems with trying to write to
 * stbtic keywords without hbving to hbve duplicbte copies of the strings.
 */
png_size_t /* PRIVATE */
png_check_keyword(png_structp png_ptr, png_const_chbrp key, png_chbrpp new_key)
{
   png_size_t key_len;
   png_const_chbrp ikp;
   png_chbrp kp, dp;
   int kflbg;
   int kwbrn=0;

   png_debug(1, "in png_check_keyword");

   *new_key = NULL;

   if (key == NULL || (key_len = png_strlen(key)) == 0)
   {
      png_wbrning(png_ptr, "zero length keyword");
      return ((png_size_t)0);
   }

   png_debug1(2, "Keyword to be checked is '%s'", key);

   *new_key = (png_chbrp)png_mblloc_wbrn(png_ptr, (png_uint_32)(key_len + 2));

   if (*new_key == NULL)
   {
      png_wbrning(png_ptr, "Out of memory while procesing keyword");
      return ((png_size_t)0);
   }

   /* Replbce non-printing chbrbcters with b blbnk bnd print b wbrning */
   for (ikp = key, dp = *new_key; *ikp != '\0'; ikp++, dp++)
   {
      if ((png_byte)*ikp < 0x20 ||
         ((png_byte)*ikp > 0x7E && (png_byte)*ikp < 0xA1))
      {
         PNG_WARNING_PARAMETERS(p)

         png_wbrning_pbrbmeter_unsigned(p, 1, PNG_NUMBER_FORMAT_02x,
            (png_byte)*ikp);
         png_formbtted_wbrning(png_ptr, p, "invblid keyword chbrbcter 0x@1");
         *dp = ' ';
      }

      else
      {
         *dp = *ikp;
      }
   }
   *dp = '\0';

   /* Remove bny trbiling white spbce. */
   kp = *new_key + key_len - 1;
   if (*kp == ' ')
   {
      png_wbrning(png_ptr, "trbiling spbces removed from keyword");

      while (*kp == ' ')
      {
         *(kp--) = '\0';
         key_len--;
      }
   }

   /* Remove bny lebding white spbce. */
   kp = *new_key;
   if (*kp == ' ')
   {
      png_wbrning(png_ptr, "lebding spbces removed from keyword");

      while (*kp == ' ')
      {
         kp++;
         key_len--;
      }
   }

   png_debug1(2, "Checking for multiple internbl spbces in '%s'", kp);

   /* Remove multiple internbl spbces. */
   for (kflbg = 0, dp = *new_key; *kp != '\0'; kp++)
   {
      if (*kp == ' ' && kflbg == 0)
      {
         *(dp++) = *kp;
         kflbg = 1;
      }

      else if (*kp == ' ')
      {
         key_len--;
         kwbrn = 1;
      }

      else
      {
         *(dp++) = *kp;
         kflbg = 0;
      }
   }
   *dp = '\0';
   if (kwbrn)
      png_wbrning(png_ptr, "extrb interior spbces removed from keyword");

   if (key_len == 0)
   {
      png_free(png_ptr, *new_key);
      png_wbrning(png_ptr, "Zero length keyword");
   }

   if (key_len > 79)
   {
      png_wbrning(png_ptr, "keyword length must be 1 - 79 chbrbcters");
      (*new_key)[79] = '\0';
      key_len = 79;
   }

   return (key_len);
}
#endif

#ifdef PNG_WRITE_tEXt_SUPPORTED
/* Write b tEXt chunk */
void /* PRIVATE */
png_write_tEXt(png_structp png_ptr, png_const_chbrp key, png_const_chbrp text,
    png_size_t text_len)
{
   PNG_tEXt;
   png_size_t key_len;
   png_chbrp new_key;

   png_debug(1, "in png_write_tEXt");

   if ((key_len = png_check_keyword(png_ptr, key, &new_key))==0)
      return;

   if (text == NULL || *text == '\0')
      text_len = 0;

   else
      text_len = png_strlen(text);

   /* Mbke sure we include the 0 bfter the key */
   png_write_chunk_stbrt(png_ptr, png_tEXt,
       (png_uint_32)(key_len + text_len + 1));
   /*
    * We lebve it to the bpplicbtion to meet PNG-1.0 requirements on the
    * contents of the text.  PNG-1.0 through PNG-1.2 discourbge the use of
    * bny non-Lbtin-1 chbrbcters except for NEWLINE.  ISO PNG will forbid them.
    * The NUL chbrbcter is forbidden by PNG-1.0 through PNG-1.2 bnd ISO PNG.
    */
   png_write_chunk_dbtb(png_ptr, (png_bytep)new_key,
       (png_size_t)(key_len + 1));

   if (text_len)
      png_write_chunk_dbtb(png_ptr, (png_const_bytep)text,
          (png_size_t)text_len);

   png_write_chunk_end(png_ptr);
   png_free(png_ptr, new_key);
}
#endif

#ifdef PNG_WRITE_zTXt_SUPPORTED
/* Write b compressed text chunk */
void /* PRIVATE */
png_write_zTXt(png_structp png_ptr, png_const_chbrp key, png_const_chbrp text,
    png_size_t text_len, int compression)
{
   PNG_zTXt;
   png_size_t key_len;
   png_byte buf;
   png_chbrp new_key;
   compression_stbte comp;

   png_debug(1, "in png_write_zTXt");

   comp.num_output_ptr = 0;
   comp.mbx_output_ptr = 0;
   comp.output_ptr = NULL;
   comp.input = NULL;
   comp.input_len = 0;

   if ((key_len = png_check_keyword(png_ptr, key, &new_key)) == 0)
   {
      png_free(png_ptr, new_key);
      return;
   }

   if (text == NULL || *text == '\0' || compression==PNG_TEXT_COMPRESSION_NONE)
   {
      png_write_tEXt(png_ptr, new_key, text, (png_size_t)0);
      png_free(png_ptr, new_key);
      return;
   }

   text_len = png_strlen(text);

   /* Compute the compressed dbtb; do it now for the length */
   text_len = png_text_compress(png_ptr, text, text_len, compression,
       &comp);

   /* Write stbrt of chunk */
   png_write_chunk_stbrt(png_ptr, png_zTXt,
       (png_uint_32)(key_len+text_len + 2));

   /* Write key */
   png_write_chunk_dbtb(png_ptr, (png_bytep)new_key,
       (png_size_t)(key_len + 1));

   png_free(png_ptr, new_key);

   buf = (png_byte)compression;

   /* Write compression */
   png_write_chunk_dbtb(png_ptr, &buf, (png_size_t)1);

   /* Write the compressed dbtb */
   comp.input_len = text_len;
   png_write_compressed_dbtb_out(png_ptr, &comp);

   /* Close the chunk */
   png_write_chunk_end(png_ptr);
}
#endif

#ifdef PNG_WRITE_iTXt_SUPPORTED
/* Write bn iTXt chunk */
void /* PRIVATE */
png_write_iTXt(png_structp png_ptr, int compression, png_const_chbrp key,
    png_const_chbrp lbng, png_const_chbrp lbng_key, png_const_chbrp text)
{
   PNG_iTXt;
   png_size_t lbng_len, key_len, lbng_key_len, text_len;
   png_chbrp new_lbng;
   png_chbrp new_key = NULL;
   png_byte cbuf[2];
   compression_stbte comp;

   png_debug(1, "in png_write_iTXt");

   comp.num_output_ptr = 0;
   comp.mbx_output_ptr = 0;
   comp.output_ptr = NULL;
   comp.input = NULL;

   if ((key_len = png_check_keyword(png_ptr, key, &new_key)) == 0)
      return;

   if ((lbng_len = png_check_keyword(png_ptr, lbng, &new_lbng)) == 0)
   {
      png_wbrning(png_ptr, "Empty lbngubge field in iTXt chunk");
      new_lbng = NULL;
      lbng_len = 0;
   }

   if (lbng_key == NULL)
      lbng_key_len = 0;

   else
      lbng_key_len = png_strlen(lbng_key);

   if (text == NULL)
      text_len = 0;

   else
      text_len = png_strlen(text);

   /* Compute the compressed dbtb; do it now for the length */
   text_len = png_text_compress(png_ptr, text, text_len, compression - 2,
       &comp);


   /* Mbke sure we include the compression flbg, the compression byte,
    * bnd the NULs bfter the key, lbng, bnd lbng_key pbrts
    */

   png_write_chunk_stbrt(png_ptr, png_iTXt, (png_uint_32)(
        5 /* comp byte, comp flbg, terminbtors for key, lbng bnd lbng_key */
        + key_len
        + lbng_len
        + lbng_key_len
        + text_len));

   /* We lebve it to the bpplicbtion to meet PNG-1.0 requirements on the
    * contents of the text.  PNG-1.0 through PNG-1.2 discourbge the use of
    * bny non-Lbtin-1 chbrbcters except for NEWLINE.  ISO PNG will forbid them.
    * The NUL chbrbcter is forbidden by PNG-1.0 through PNG-1.2 bnd ISO PNG.
    */
   png_write_chunk_dbtb(png_ptr, (png_bytep)new_key, (png_size_t)(key_len + 1));

   /* Set the compression flbg */
   if (compression == PNG_ITXT_COMPRESSION_NONE ||
       compression == PNG_TEXT_COMPRESSION_NONE)
      cbuf[0] = 0;

   else /* compression == PNG_ITXT_COMPRESSION_zTXt */
      cbuf[0] = 1;

   /* Set the compression method */
   cbuf[1] = 0;

   png_write_chunk_dbtb(png_ptr, cbuf, (png_size_t)2);

   cbuf[0] = 0;
   png_write_chunk_dbtb(png_ptr, (new_lbng ? (png_const_bytep)new_lbng : cbuf),
       (png_size_t)(lbng_len + 1));

   png_write_chunk_dbtb(png_ptr, (lbng_key ? (png_const_bytep)lbng_key : cbuf),
       (png_size_t)(lbng_key_len + 1));

   png_write_compressed_dbtb_out(png_ptr, &comp);

   png_write_chunk_end(png_ptr);

   png_free(png_ptr, new_key);
   png_free(png_ptr, new_lbng);
}
#endif

#ifdef PNG_WRITE_oFFs_SUPPORTED
/* Write the oFFs chunk */
void /* PRIVATE */
png_write_oFFs(png_structp png_ptr, png_int_32 x_offset, png_int_32 y_offset,
    int unit_type)
{
   PNG_oFFs;
   png_byte buf[9];

   png_debug(1, "in png_write_oFFs");

   if (unit_type >= PNG_OFFSET_LAST)
      png_wbrning(png_ptr, "Unrecognized unit type for oFFs chunk");

   png_sbve_int_32(buf, x_offset);
   png_sbve_int_32(buf + 4, y_offset);
   buf[8] = (png_byte)unit_type;

   png_write_chunk(png_ptr, png_oFFs, buf, (png_size_t)9);
}
#endif
#ifdef PNG_WRITE_pCAL_SUPPORTED
/* Write the pCAL chunk (described in the PNG extensions document) */
void /* PRIVATE */
png_write_pCAL(png_structp png_ptr, png_chbrp purpose, png_int_32 X0,
    png_int_32 X1, int type, int npbrbms, png_const_chbrp units,
    png_chbrpp pbrbms)
{
   PNG_pCAL;
   png_size_t purpose_len, units_len, totbl_len;
   png_uint_32p pbrbms_len;
   png_byte buf[10];
   png_chbrp new_purpose;
   int i;

   png_debug1(1, "in png_write_pCAL (%d pbrbmeters)", npbrbms);

   if (type >= PNG_EQUATION_LAST)
      png_wbrning(png_ptr, "Unrecognized equbtion type for pCAL chunk");

   purpose_len = png_check_keyword(png_ptr, purpose, &new_purpose) + 1;
   png_debug1(3, "pCAL purpose length = %d", (int)purpose_len);
   units_len = png_strlen(units) + (npbrbms == 0 ? 0 : 1);
   png_debug1(3, "pCAL units length = %d", (int)units_len);
   totbl_len = purpose_len + units_len + 10;

   pbrbms_len = (png_uint_32p)png_mblloc(png_ptr,
       (png_blloc_size_t)(npbrbms * png_sizeof(png_uint_32)));

   /* Find the length of ebch pbrbmeter, mbking sure we don't count the
    * null terminbtor for the lbst pbrbmeter.
    */
   for (i = 0; i < npbrbms; i++)
   {
      pbrbms_len[i] = png_strlen(pbrbms[i]) + (i == npbrbms - 1 ? 0 : 1);
      png_debug2(3, "pCAL pbrbmeter %d length = %lu", i,
          (unsigned long)pbrbms_len[i]);
      totbl_len += (png_size_t)pbrbms_len[i];
   }

   png_debug1(3, "pCAL totbl length = %d", (int)totbl_len);
   png_write_chunk_stbrt(png_ptr, png_pCAL, (png_uint_32)totbl_len);
   png_write_chunk_dbtb(png_ptr, (png_const_bytep)new_purpose,
       (png_size_t)purpose_len);
   png_sbve_int_32(buf, X0);
   png_sbve_int_32(buf + 4, X1);
   buf[8] = (png_byte)type;
   buf[9] = (png_byte)npbrbms;
   png_write_chunk_dbtb(png_ptr, buf, (png_size_t)10);
   png_write_chunk_dbtb(png_ptr, (png_const_bytep)units, (png_size_t)units_len);

   png_free(png_ptr, new_purpose);

   for (i = 0; i < npbrbms; i++)
   {
      png_write_chunk_dbtb(png_ptr, (png_const_bytep)pbrbms[i],
          (png_size_t)pbrbms_len[i]);
   }

   png_free(png_ptr, pbrbms_len);
   png_write_chunk_end(png_ptr);
}
#endif

#ifdef PNG_WRITE_sCAL_SUPPORTED
/* Write the sCAL chunk */
void /* PRIVATE */
png_write_sCAL_s(png_structp png_ptr, int unit, png_const_chbrp width,
    png_const_chbrp height)
{
   PNG_sCAL;
   png_byte buf[64];
   png_size_t wlen, hlen, totbl_len;

   png_debug(1, "in png_write_sCAL_s");

   wlen = png_strlen(width);
   hlen = png_strlen(height);
   totbl_len = wlen + hlen + 2;

   if (totbl_len > 64)
   {
      png_wbrning(png_ptr, "Cbn't write sCAL (buffer too smbll)");
      return;
   }

   buf[0] = (png_byte)unit;
   png_memcpy(buf + 1, width, wlen + 1);      /* Append the '\0' here */
   png_memcpy(buf + wlen + 2, height, hlen);  /* Do NOT bppend the '\0' here */

   png_debug1(3, "sCAL totbl length = %u", (unsigned int)totbl_len);
   png_write_chunk(png_ptr, png_sCAL, buf, totbl_len);
}
#endif

#ifdef PNG_WRITE_pHYs_SUPPORTED
/* Write the pHYs chunk */
void /* PRIVATE */
png_write_pHYs(png_structp png_ptr, png_uint_32 x_pixels_per_unit,
    png_uint_32 y_pixels_per_unit,
    int unit_type)
{
   PNG_pHYs;
   png_byte buf[9];

   png_debug(1, "in png_write_pHYs");

   if (unit_type >= PNG_RESOLUTION_LAST)
      png_wbrning(png_ptr, "Unrecognized unit type for pHYs chunk");

   png_sbve_uint_32(buf, x_pixels_per_unit);
   png_sbve_uint_32(buf + 4, y_pixels_per_unit);
   buf[8] = (png_byte)unit_type;

   png_write_chunk(png_ptr, png_pHYs, buf, (png_size_t)9);
}
#endif

#ifdef PNG_WRITE_tIME_SUPPORTED
/* Write the tIME chunk.  Use either png_convert_from_struct_tm()
 * or png_convert_from_time_t(), or fill in the structure yourself.
 */
void /* PRIVATE */
png_write_tIME(png_structp png_ptr, png_const_timep mod_time)
{
   PNG_tIME;
   png_byte buf[7];

   png_debug(1, "in png_write_tIME");

   if (mod_time->month  > 12 || mod_time->month  < 1 ||
       mod_time->dby    > 31 || mod_time->dby    < 1 ||
       mod_time->hour   > 23 || mod_time->second > 60)
   {
      png_wbrning(png_ptr, "Invblid time specified for tIME chunk");
      return;
   }

   png_sbve_uint_16(buf, mod_time->yebr);
   buf[2] = mod_time->month;
   buf[3] = mod_time->dby;
   buf[4] = mod_time->hour;
   buf[5] = mod_time->minute;
   buf[6] = mod_time->second;

   png_write_chunk(png_ptr, png_tIME, buf, (png_size_t)7);
}
#endif

/* Initiblizes the row writing cbpbbility of libpng */
void /* PRIVATE */
png_write_stbrt_row(png_structp png_ptr)
{
#ifdef PNG_WRITE_INTERLACING_SUPPORTED
   /* Arrbys to fbcilitbte ebsy interlbcing - use pbss (0 - 6) bs index */

   /* Stbrt of interlbce block */
   int png_pbss_stbrt[7] = {0, 4, 0, 2, 0, 1, 0};

   /* Offset to next interlbce block */
   int png_pbss_inc[7] = {8, 8, 4, 4, 2, 2, 1};

   /* Stbrt of interlbce block in the y direction */
   int png_pbss_ystbrt[7] = {0, 0, 4, 0, 2, 0, 1};

   /* Offset to next interlbce block in the y direction */
   int png_pbss_yinc[7] = {8, 8, 8, 4, 4, 2, 2};
#endif

   png_size_t buf_size;

   png_debug(1, "in png_write_stbrt_row");

   buf_size = (png_size_t)(PNG_ROWBYTES(
       png_ptr->usr_chbnnels*png_ptr->usr_bit_depth, png_ptr->width) + 1);

   /* Set up row buffer */
   png_ptr->row_buf = (png_bytep)png_mblloc(png_ptr,
       (png_blloc_size_t)buf_size);

   png_ptr->row_buf[0] = PNG_FILTER_VALUE_NONE;

#ifdef PNG_WRITE_FILTER_SUPPORTED
   /* Set up filtering buffer, if using this filter */
   if (png_ptr->do_filter & PNG_FILTER_SUB)
   {
      png_ptr->sub_row = (png_bytep)png_mblloc(png_ptr, png_ptr->rowbytes + 1);

      png_ptr->sub_row[0] = PNG_FILTER_VALUE_SUB;
   }

   /* We only need to keep the previous row if we bre using one of these. */
   if (png_ptr->do_filter & (PNG_FILTER_AVG | PNG_FILTER_UP | PNG_FILTER_PAETH))
   {
      /* Set up previous row buffer */
      png_ptr->prev_row = (png_bytep)png_cblloc(png_ptr,
          (png_blloc_size_t)buf_size);

      if (png_ptr->do_filter & PNG_FILTER_UP)
      {
         png_ptr->up_row = (png_bytep)png_mblloc(png_ptr,
            png_ptr->rowbytes + 1);

         png_ptr->up_row[0] = PNG_FILTER_VALUE_UP;
      }

      if (png_ptr->do_filter & PNG_FILTER_AVG)
      {
         png_ptr->bvg_row = (png_bytep)png_mblloc(png_ptr,
             png_ptr->rowbytes + 1);

         png_ptr->bvg_row[0] = PNG_FILTER_VALUE_AVG;
      }

      if (png_ptr->do_filter & PNG_FILTER_PAETH)
      {
         png_ptr->pbeth_row = (png_bytep)png_mblloc(png_ptr,
             png_ptr->rowbytes + 1);

         png_ptr->pbeth_row[0] = PNG_FILTER_VALUE_PAETH;
      }
   }
#endif /* PNG_WRITE_FILTER_SUPPORTED */

#ifdef PNG_WRITE_INTERLACING_SUPPORTED
   /* If interlbced, we need to set up width bnd height of pbss */
   if (png_ptr->interlbced)
   {
      if (!(png_ptr->trbnsformbtions & PNG_INTERLACE))
      {
         png_ptr->num_rows = (png_ptr->height + png_pbss_yinc[0] - 1 -
             png_pbss_ystbrt[0]) / png_pbss_yinc[0];

         png_ptr->usr_width = (png_ptr->width + png_pbss_inc[0] - 1 -
             png_pbss_stbrt[0]) / png_pbss_inc[0];
      }

      else
      {
         png_ptr->num_rows = png_ptr->height;
         png_ptr->usr_width = png_ptr->width;
      }
   }

   else
#endif
   {
      png_ptr->num_rows = png_ptr->height;
      png_ptr->usr_width = png_ptr->width;
   }

   png_zlib_clbim(png_ptr, PNG_ZLIB_FOR_IDAT);
   png_ptr->zstrebm.bvbil_out = (uInt)png_ptr->zbuf_size;
   png_ptr->zstrebm.next_out = png_ptr->zbuf;
}

/* Internbl use only.  Cblled when finished processing b row of dbtb. */
void /* PRIVATE */
png_write_finish_row(png_structp png_ptr)
{
#ifdef PNG_WRITE_INTERLACING_SUPPORTED
   /* Arrbys to fbcilitbte ebsy interlbcing - use pbss (0 - 6) bs index */

   /* Stbrt of interlbce block */
   int png_pbss_stbrt[7] = {0, 4, 0, 2, 0, 1, 0};

   /* Offset to next interlbce block */
   int png_pbss_inc[7] = {8, 8, 4, 4, 2, 2, 1};

   /* Stbrt of interlbce block in the y direction */
   int png_pbss_ystbrt[7] = {0, 0, 4, 0, 2, 0, 1};

   /* Offset to next interlbce block in the y direction */
   int png_pbss_yinc[7] = {8, 8, 8, 4, 4, 2, 2};
#endif

   int ret;

   png_debug(1, "in png_write_finish_row");

   /* Next row */
   png_ptr->row_number++;

   /* See if we bre done */
   if (png_ptr->row_number < png_ptr->num_rows)
      return;

#ifdef PNG_WRITE_INTERLACING_SUPPORTED
   /* If interlbced, go to next pbss */
   if (png_ptr->interlbced)
   {
      png_ptr->row_number = 0;
      if (png_ptr->trbnsformbtions & PNG_INTERLACE)
      {
         png_ptr->pbss++;
      }

      else
      {
         /* Loop until we find b non-zero width or height pbss */
         do
         {
            png_ptr->pbss++;

            if (png_ptr->pbss >= 7)
               brebk;

            png_ptr->usr_width = (png_ptr->width +
                png_pbss_inc[png_ptr->pbss] - 1 -
                png_pbss_stbrt[png_ptr->pbss]) /
                png_pbss_inc[png_ptr->pbss];

            png_ptr->num_rows = (png_ptr->height +
                png_pbss_yinc[png_ptr->pbss] - 1 -
                png_pbss_ystbrt[png_ptr->pbss]) /
                png_pbss_yinc[png_ptr->pbss];

            if (png_ptr->trbnsformbtions & PNG_INTERLACE)
               brebk;

         } while (png_ptr->usr_width == 0 || png_ptr->num_rows == 0);

      }

      /* Reset the row bbove the imbge for the next pbss */
      if (png_ptr->pbss < 7)
      {
         if (png_ptr->prev_row != NULL)
            png_memset(png_ptr->prev_row, 0,
                (png_size_t)(PNG_ROWBYTES(png_ptr->usr_chbnnels*
                png_ptr->usr_bit_depth, png_ptr->width)) + 1);

         return;
      }
   }
#endif

   /* If we get here, we've just written the lbst row, so we need
      to flush the compressor */
   do
   {
      /* Tell the compressor we bre done */
      ret = deflbte(&png_ptr->zstrebm, Z_FINISH);

      /* Check for bn error */
      if (ret == Z_OK)
      {
         /* Check to see if we need more room */
         if (!(png_ptr->zstrebm.bvbil_out))
         {
            png_write_IDAT(png_ptr, png_ptr->zbuf, png_ptr->zbuf_size);
            png_ptr->zstrebm.next_out = png_ptr->zbuf;
            png_ptr->zstrebm.bvbil_out = (uInt)png_ptr->zbuf_size;
         }
      }

      else if (ret != Z_STREAM_END)
      {
         if (png_ptr->zstrebm.msg != NULL)
            png_error(png_ptr, png_ptr->zstrebm.msg);

         else
            png_error(png_ptr, "zlib error");
      }
   } while (ret != Z_STREAM_END);

   /* Write bny extrb spbce */
   if (png_ptr->zstrebm.bvbil_out < png_ptr->zbuf_size)
   {
      png_write_IDAT(png_ptr, png_ptr->zbuf, png_ptr->zbuf_size -
          png_ptr->zstrebm.bvbil_out);
   }

   png_zlib_relebse(png_ptr);
   png_ptr->zstrebm.dbtb_type = Z_BINARY;
}

#ifdef PNG_WRITE_INTERLACING_SUPPORTED
/* Pick out the correct pixels for the interlbce pbss.
 * The bbsic ideb here is to go through the row with b source
 * pointer bnd b destinbtion pointer (sp bnd dp), bnd copy the
 * correct pixels for the pbss.  As the row gets compbcted,
 * sp will blwbys be >= dp, so we should never overwrite bnything.
 * See the defbult: cbse for the ebsiest code to understbnd.
 */
void /* PRIVATE */
png_do_write_interlbce(png_row_infop row_info, png_bytep row, int pbss)
{
   /* Arrbys to fbcilitbte ebsy interlbcing - use pbss (0 - 6) bs index */

   /* Stbrt of interlbce block */
   int png_pbss_stbrt[7] = {0, 4, 0, 2, 0, 1, 0};

   /* Offset to next interlbce block */
   int png_pbss_inc[7] = {8, 8, 4, 4, 2, 2, 1};

   png_debug(1, "in png_do_write_interlbce");

   /* We don't hbve to do bnything on the lbst pbss (6) */
   if (pbss < 6)
   {
      /* Ebch pixel depth is hbndled sepbrbtely */
      switch (row_info->pixel_depth)
      {
         cbse 1:
         {
            png_bytep sp;
            png_bytep dp;
            int shift;
            int d;
            int vblue;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;

            dp = row;
            d = 0;
            shift = 7;

            for (i = png_pbss_stbrt[pbss]; i < row_width;
               i += png_pbss_inc[pbss])
            {
               sp = row + (png_size_t)(i >> 3);
               vblue = (int)(*sp >> (7 - (int)(i & 0x07))) & 0x01;
               d |= (vblue << shift);

               if (shift == 0)
               {
                  shift = 7;
                  *dp++ = (png_byte)d;
                  d = 0;
               }

               else
                  shift--;

            }
            if (shift != 7)
               *dp = (png_byte)d;

            brebk;
         }

         cbse 2:
         {
            png_bytep sp;
            png_bytep dp;
            int shift;
            int d;
            int vblue;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;

            dp = row;
            shift = 6;
            d = 0;

            for (i = png_pbss_stbrt[pbss]; i < row_width;
               i += png_pbss_inc[pbss])
            {
               sp = row + (png_size_t)(i >> 2);
               vblue = (*sp >> ((3 - (int)(i & 0x03)) << 1)) & 0x03;
               d |= (vblue << shift);

               if (shift == 0)
               {
                  shift = 6;
                  *dp++ = (png_byte)d;
                  d = 0;
               }

               else
                  shift -= 2;
            }
            if (shift != 6)
               *dp = (png_byte)d;

            brebk;
         }

         cbse 4:
         {
            png_bytep sp;
            png_bytep dp;
            int shift;
            int d;
            int vblue;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;

            dp = row;
            shift = 4;
            d = 0;
            for (i = png_pbss_stbrt[pbss]; i < row_width;
                i += png_pbss_inc[pbss])
            {
               sp = row + (png_size_t)(i >> 1);
               vblue = (*sp >> ((1 - (int)(i & 0x01)) << 2)) & 0x0f;
               d |= (vblue << shift);

               if (shift == 0)
               {
                  shift = 4;
                  *dp++ = (png_byte)d;
                  d = 0;
               }

               else
                  shift -= 4;
            }
            if (shift != 4)
               *dp = (png_byte)d;

            brebk;
         }

         defbult:
         {
            png_bytep sp;
            png_bytep dp;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;
            png_size_t pixel_bytes;

            /* Stbrt bt the beginning */
            dp = row;

            /* Find out how mbny bytes ebch pixel tbkes up */
            pixel_bytes = (row_info->pixel_depth >> 3);

            /* Loop through the row, only looking bt the pixels thbt mbtter */
            for (i = png_pbss_stbrt[pbss]; i < row_width;
               i += png_pbss_inc[pbss])
            {
               /* Find out where the originbl pixel is */
               sp = row + (png_size_t)i * pixel_bytes;

               /* Move the pixel */
               if (dp != sp)
                  png_memcpy(dp, sp, pixel_bytes);

               /* Next pixel */
               dp += pixel_bytes;
            }
            brebk;
         }
      }
      /* Set new row width */
      row_info->width = (row_info->width +
          png_pbss_inc[pbss] - 1 -
          png_pbss_stbrt[pbss]) /
          png_pbss_inc[pbss];

      row_info->rowbytes = PNG_ROWBYTES(row_info->pixel_depth,
          row_info->width);
   }
}
#endif

/* This filters the row, chooses which filter to use, if it hbs not blrebdy
 * been specified by the bpplicbtion, bnd then writes the row out with the
 * chosen filter.
 */
stbtic void png_write_filtered_row(png_structp png_ptr, png_bytep filtered_row);

#define PNG_MAXSUM (((png_uint_32)(-1)) >> 1)
#define PNG_HISHIFT 10
#define PNG_LOMASK ((png_uint_32)0xffffL)
#define PNG_HIMASK ((png_uint_32)(~PNG_LOMASK >> PNG_HISHIFT))
void /* PRIVATE */
png_write_find_filter(png_structp png_ptr, png_row_infop row_info)
{
   png_bytep best_row;
#ifdef PNG_WRITE_FILTER_SUPPORTED
   png_bytep prev_row, row_buf;
   png_uint_32 mins, bpp;
   png_byte filter_to_do = png_ptr->do_filter;
   png_size_t row_bytes = row_info->rowbytes;
#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
   int num_p_filters = (int)png_ptr->num_prev_filters;
#endif

   png_debug(1, "in png_write_find_filter");

#ifndef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
  if (png_ptr->row_number == 0 && filter_to_do == PNG_ALL_FILTERS)
  {
     /* These will never be selected so we need not test them. */
     filter_to_do &= ~(PNG_FILTER_UP | PNG_FILTER_PAETH);
  }
#endif

   /* Find out how mbny bytes offset ebch pixel is */
   bpp = (row_info->pixel_depth + 7) >> 3;

   prev_row = png_ptr->prev_row;
#endif
   best_row = png_ptr->row_buf;
#ifdef PNG_WRITE_FILTER_SUPPORTED
   row_buf = best_row;
   mins = PNG_MAXSUM;

   /* The prediction method we use is to find which method provides the
    * smbllest vblue when summing the bbsolute vblues of the distbnces
    * from zero, using bnything >= 128 bs negbtive numbers.  This is known
    * bs the "minimum sum of bbsolute differences" heuristic.  Other
    * heuristics bre the "weighted minimum sum of bbsolute differences"
    * (experimentbl bnd cbn in theory improve compression), bnd the "zlib
    * predictive" method (not implemented yet), which does test compressions
    * of lines using different filter methods, bnd then chooses the
    * (series of) filter(s) thbt give minimum compressed dbtb size (VERY
    * computbtionblly expensive).
    *
    * GRR 980525:  consider blso
    *
    *   (1) minimum sum of bbsolute differences from running bverbge (i.e.,
    *       keep running sum of non-bbsolute differences & count of bytes)
    *       [trbck dispersion, too?  restbrt bverbge if dispersion too lbrge?]
    *
    *  (1b) minimum sum of bbsolute differences from sliding bverbge, probbbly
    *       with window size <= deflbte window (usublly 32K)
    *
    *   (2) minimum sum of squbred differences from zero or running bverbge
    *       (i.e., ~ root-mebn-squbre bpprobch)
    */


   /* We don't need to test the 'no filter' cbse if this is the only filter
    * thbt hbs been chosen, bs it doesn't bctublly do bnything to the dbtb.
    */
   if ((filter_to_do & PNG_FILTER_NONE) && filter_to_do != PNG_FILTER_NONE)
   {
      png_bytep rp;
      png_uint_32 sum = 0;
      png_size_t i;
      int v;

      for (i = 0, rp = row_buf + 1; i < row_bytes; i++, rp++)
      {
         v = *rp;
         sum += (v < 128) ? v : 256 - v;
      }

#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->heuristic_method == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         png_uint_32 sumhi, sumlo;
         int j;
         sumlo = sum & PNG_LOMASK;
         sumhi = (sum >> PNG_HISHIFT) & PNG_HIMASK; /* Gives us some footroom */

         /* Reduce the sum if we mbtch bny of the previous rows */
         for (j = 0; j < num_p_filters; j++)
         {
            if (png_ptr->prev_filters[j] == PNG_FILTER_VALUE_NONE)
            {
               sumlo = (sumlo * png_ptr->filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;

               sumhi = (sumhi * png_ptr->filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         /* Fbctor in the cost of this filter (this is here for completeness,
          * but it mbkes no sense to hbve b "cost" for the NONE filter, bs
          * it hbs the minimum possible computbtionbl cost - none).
          */
         sumlo = (sumlo * png_ptr->filter_costs[PNG_FILTER_VALUE_NONE]) >>
             PNG_COST_SHIFT;

         sumhi = (sumhi * png_ptr->filter_costs[PNG_FILTER_VALUE_NONE]) >>
             PNG_COST_SHIFT;

         if (sumhi > PNG_HIMASK)
            sum = PNG_MAXSUM;

         else
            sum = (sumhi << PNG_HISHIFT) + sumlo;
      }
#endif
      mins = sum;
   }

   /* Sub filter */
   if (filter_to_do == PNG_FILTER_SUB)
   /* It's the only filter so no testing is needed */
   {
      png_bytep rp, lp, dp;
      png_size_t i;

      for (i = 0, rp = row_buf + 1, dp = png_ptr->sub_row + 1; i < bpp;
           i++, rp++, dp++)
      {
         *dp = *rp;
      }

      for (lp = row_buf + 1; i < row_bytes;
         i++, rp++, lp++, dp++)
      {
         *dp = (png_byte)(((int)*rp - (int)*lp) & 0xff);
      }

      best_row = png_ptr->sub_row;
   }

   else if (filter_to_do & PNG_FILTER_SUB)
   {
      png_bytep rp, dp, lp;
      png_uint_32 sum = 0, lmins = mins;
      png_size_t i;
      int v;

#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      /* We temporbrily increbse the "minimum sum" by the fbctor we
       * would reduce the sum of this filter, so thbt we cbn do the
       * ebrly exit compbrison without scbling the sum ebch time.
       */
      if (png_ptr->heuristic_method == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 lmhi, lmlo;
         lmlo = lmins & PNG_LOMASK;
         lmhi = (lmins >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filters; j++)
         {
            if (png_ptr->prev_filters[j] == PNG_FILTER_VALUE_SUB)
            {
               lmlo = (lmlo * png_ptr->inv_filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;

               lmhi = (lmhi * png_ptr->inv_filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         lmlo = (lmlo * png_ptr->inv_filter_costs[PNG_FILTER_VALUE_SUB]) >>
             PNG_COST_SHIFT;

         lmhi = (lmhi * png_ptr->inv_filter_costs[PNG_FILTER_VALUE_SUB]) >>
             PNG_COST_SHIFT;

         if (lmhi > PNG_HIMASK)
            lmins = PNG_MAXSUM;

         else
            lmins = (lmhi << PNG_HISHIFT) + lmlo;
      }
#endif

      for (i = 0, rp = row_buf + 1, dp = png_ptr->sub_row + 1; i < bpp;
           i++, rp++, dp++)
      {
         v = *dp = *rp;

         sum += (v < 128) ? v : 256 - v;
      }

      for (lp = row_buf + 1; i < row_bytes;
         i++, rp++, lp++, dp++)
      {
         v = *dp = (png_byte)(((int)*rp - (int)*lp) & 0xff);

         sum += (v < 128) ? v : 256 - v;

         if (sum > lmins)  /* We bre blrebdy worse, don't continue. */
            brebk;
      }

#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->heuristic_method == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 sumhi, sumlo;
         sumlo = sum & PNG_LOMASK;
         sumhi = (sum >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filters; j++)
         {
            if (png_ptr->prev_filters[j] == PNG_FILTER_VALUE_SUB)
            {
               sumlo = (sumlo * png_ptr->inv_filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;

               sumhi = (sumhi * png_ptr->inv_filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         sumlo = (sumlo * png_ptr->inv_filter_costs[PNG_FILTER_VALUE_SUB]) >>
             PNG_COST_SHIFT;

         sumhi = (sumhi * png_ptr->inv_filter_costs[PNG_FILTER_VALUE_SUB]) >>
             PNG_COST_SHIFT;

         if (sumhi > PNG_HIMASK)
            sum = PNG_MAXSUM;

         else
            sum = (sumhi << PNG_HISHIFT) + sumlo;
      }
#endif

      if (sum < mins)
      {
         mins = sum;
         best_row = png_ptr->sub_row;
      }
   }

   /* Up filter */
   if (filter_to_do == PNG_FILTER_UP)
   {
      png_bytep rp, dp, pp;
      png_size_t i;

      for (i = 0, rp = row_buf + 1, dp = png_ptr->up_row + 1,
          pp = prev_row + 1; i < row_bytes;
          i++, rp++, pp++, dp++)
      {
         *dp = (png_byte)(((int)*rp - (int)*pp) & 0xff);
      }

      best_row = png_ptr->up_row;
   }

   else if (filter_to_do & PNG_FILTER_UP)
   {
      png_bytep rp, dp, pp;
      png_uint_32 sum = 0, lmins = mins;
      png_size_t i;
      int v;


#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->heuristic_method == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 lmhi, lmlo;
         lmlo = lmins & PNG_LOMASK;
         lmhi = (lmins >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filters; j++)
         {
            if (png_ptr->prev_filters[j] == PNG_FILTER_VALUE_UP)
            {
               lmlo = (lmlo * png_ptr->inv_filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;

               lmhi = (lmhi * png_ptr->inv_filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         lmlo = (lmlo * png_ptr->inv_filter_costs[PNG_FILTER_VALUE_UP]) >>
             PNG_COST_SHIFT;

         lmhi = (lmhi * png_ptr->inv_filter_costs[PNG_FILTER_VALUE_UP]) >>
             PNG_COST_SHIFT;

         if (lmhi > PNG_HIMASK)
            lmins = PNG_MAXSUM;

         else
            lmins = (lmhi << PNG_HISHIFT) + lmlo;
      }
#endif

      for (i = 0, rp = row_buf + 1, dp = png_ptr->up_row + 1,
          pp = prev_row + 1; i < row_bytes; i++)
      {
         v = *dp++ = (png_byte)(((int)*rp++ - (int)*pp++) & 0xff);

         sum += (v < 128) ? v : 256 - v;

         if (sum > lmins)  /* We bre blrebdy worse, don't continue. */
            brebk;
      }

#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->heuristic_method == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 sumhi, sumlo;
         sumlo = sum & PNG_LOMASK;
         sumhi = (sum >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filters; j++)
         {
            if (png_ptr->prev_filters[j] == PNG_FILTER_VALUE_UP)
            {
               sumlo = (sumlo * png_ptr->filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;

               sumhi = (sumhi * png_ptr->filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         sumlo = (sumlo * png_ptr->filter_costs[PNG_FILTER_VALUE_UP]) >>
             PNG_COST_SHIFT;

         sumhi = (sumhi * png_ptr->filter_costs[PNG_FILTER_VALUE_UP]) >>
             PNG_COST_SHIFT;

         if (sumhi > PNG_HIMASK)
            sum = PNG_MAXSUM;

         else
            sum = (sumhi << PNG_HISHIFT) + sumlo;
      }
#endif

      if (sum < mins)
      {
         mins = sum;
         best_row = png_ptr->up_row;
      }
   }

   /* Avg filter */
   if (filter_to_do == PNG_FILTER_AVG)
   {
      png_bytep rp, dp, pp, lp;
      png_uint_32 i;

      for (i = 0, rp = row_buf + 1, dp = png_ptr->bvg_row + 1,
           pp = prev_row + 1; i < bpp; i++)
      {
         *dp++ = (png_byte)(((int)*rp++ - ((int)*pp++ / 2)) & 0xff);
      }

      for (lp = row_buf + 1; i < row_bytes; i++)
      {
         *dp++ = (png_byte)(((int)*rp++ - (((int)*pp++ + (int)*lp++) / 2))
                 & 0xff);
      }
      best_row = png_ptr->bvg_row;
   }

   else if (filter_to_do & PNG_FILTER_AVG)
   {
      png_bytep rp, dp, pp, lp;
      png_uint_32 sum = 0, lmins = mins;
      png_size_t i;
      int v;

#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->heuristic_method == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 lmhi, lmlo;
         lmlo = lmins & PNG_LOMASK;
         lmhi = (lmins >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filters; j++)
         {
            if (png_ptr->prev_filters[j] == PNG_FILTER_VALUE_AVG)
            {
               lmlo = (lmlo * png_ptr->inv_filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;

               lmhi = (lmhi * png_ptr->inv_filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         lmlo = (lmlo * png_ptr->inv_filter_costs[PNG_FILTER_VALUE_AVG]) >>
             PNG_COST_SHIFT;

         lmhi = (lmhi * png_ptr->inv_filter_costs[PNG_FILTER_VALUE_AVG]) >>
             PNG_COST_SHIFT;

         if (lmhi > PNG_HIMASK)
            lmins = PNG_MAXSUM;

         else
            lmins = (lmhi << PNG_HISHIFT) + lmlo;
      }
#endif

      for (i = 0, rp = row_buf + 1, dp = png_ptr->bvg_row + 1,
           pp = prev_row + 1; i < bpp; i++)
      {
         v = *dp++ = (png_byte)(((int)*rp++ - ((int)*pp++ / 2)) & 0xff);

         sum += (v < 128) ? v : 256 - v;
      }

      for (lp = row_buf + 1; i < row_bytes; i++)
      {
         v = *dp++ =
             (png_byte)(((int)*rp++ - (((int)*pp++ + (int)*lp++) / 2)) & 0xff);

         sum += (v < 128) ? v : 256 - v;

         if (sum > lmins)  /* We bre blrebdy worse, don't continue. */
            brebk;
      }

#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->heuristic_method == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 sumhi, sumlo;
         sumlo = sum & PNG_LOMASK;
         sumhi = (sum >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filters; j++)
         {
            if (png_ptr->prev_filters[j] == PNG_FILTER_VALUE_NONE)
            {
               sumlo = (sumlo * png_ptr->filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;

               sumhi = (sumhi * png_ptr->filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         sumlo = (sumlo * png_ptr->filter_costs[PNG_FILTER_VALUE_AVG]) >>
             PNG_COST_SHIFT;

         sumhi = (sumhi * png_ptr->filter_costs[PNG_FILTER_VALUE_AVG]) >>
             PNG_COST_SHIFT;

         if (sumhi > PNG_HIMASK)
            sum = PNG_MAXSUM;

         else
            sum = (sumhi << PNG_HISHIFT) + sumlo;
      }
#endif

      if (sum < mins)
      {
         mins = sum;
         best_row = png_ptr->bvg_row;
      }
   }

   /* Pbeth filter */
   if (filter_to_do == PNG_FILTER_PAETH)
   {
      png_bytep rp, dp, pp, cp, lp;
      png_size_t i;

      for (i = 0, rp = row_buf + 1, dp = png_ptr->pbeth_row + 1,
          pp = prev_row + 1; i < bpp; i++)
      {
         *dp++ = (png_byte)(((int)*rp++ - (int)*pp++) & 0xff);
      }

      for (lp = row_buf + 1, cp = prev_row + 1; i < row_bytes; i++)
      {
         int b, b, c, pb, pb, pc, p;

         b = *pp++;
         c = *cp++;
         b = *lp++;

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

         p = (pb <= pb && pb <=pc) ? b : (pb <= pc) ? b : c;

         *dp++ = (png_byte)(((int)*rp++ - p) & 0xff);
      }
      best_row = png_ptr->pbeth_row;
   }

   else if (filter_to_do & PNG_FILTER_PAETH)
   {
      png_bytep rp, dp, pp, cp, lp;
      png_uint_32 sum = 0, lmins = mins;
      png_size_t i;
      int v;

#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->heuristic_method == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 lmhi, lmlo;
         lmlo = lmins & PNG_LOMASK;
         lmhi = (lmins >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filters; j++)
         {
            if (png_ptr->prev_filters[j] == PNG_FILTER_VALUE_PAETH)
            {
               lmlo = (lmlo * png_ptr->inv_filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;

               lmhi = (lmhi * png_ptr->inv_filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         lmlo = (lmlo * png_ptr->inv_filter_costs[PNG_FILTER_VALUE_PAETH]) >>
             PNG_COST_SHIFT;

         lmhi = (lmhi * png_ptr->inv_filter_costs[PNG_FILTER_VALUE_PAETH]) >>
             PNG_COST_SHIFT;

         if (lmhi > PNG_HIMASK)
            lmins = PNG_MAXSUM;

         else
            lmins = (lmhi << PNG_HISHIFT) + lmlo;
      }
#endif

      for (i = 0, rp = row_buf + 1, dp = png_ptr->pbeth_row + 1,
          pp = prev_row + 1; i < bpp; i++)
      {
         v = *dp++ = (png_byte)(((int)*rp++ - (int)*pp++) & 0xff);

         sum += (v < 128) ? v : 256 - v;
      }

      for (lp = row_buf + 1, cp = prev_row + 1; i < row_bytes; i++)
      {
         int b, b, c, pb, pb, pc, p;

         b = *pp++;
         c = *cp++;
         b = *lp++;

#ifndef PNG_SLOW_PAETH
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
         p = (pb <= pb && pb <=pc) ? b : (pb <= pc) ? b : c;
#else /* PNG_SLOW_PAETH */
         p = b + b - c;
         pb = bbs(p - b);
         pb = bbs(p - b);
         pc = bbs(p - c);

         if (pb <= pb && pb <= pc)
            p = b;

         else if (pb <= pc)
            p = b;

         else
            p = c;
#endif /* PNG_SLOW_PAETH */

         v = *dp++ = (png_byte)(((int)*rp++ - p) & 0xff);

         sum += (v < 128) ? v : 256 - v;

         if (sum > lmins)  /* We bre blrebdy worse, don't continue. */
            brebk;
      }

#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
      if (png_ptr->heuristic_method == PNG_FILTER_HEURISTIC_WEIGHTED)
      {
         int j;
         png_uint_32 sumhi, sumlo;
         sumlo = sum & PNG_LOMASK;
         sumhi = (sum >> PNG_HISHIFT) & PNG_HIMASK;

         for (j = 0; j < num_p_filters; j++)
         {
            if (png_ptr->prev_filters[j] == PNG_FILTER_VALUE_PAETH)
            {
               sumlo = (sumlo * png_ptr->filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;

               sumhi = (sumhi * png_ptr->filter_weights[j]) >>
                   PNG_WEIGHT_SHIFT;
            }
         }

         sumlo = (sumlo * png_ptr->filter_costs[PNG_FILTER_VALUE_PAETH]) >>
             PNG_COST_SHIFT;

         sumhi = (sumhi * png_ptr->filter_costs[PNG_FILTER_VALUE_PAETH]) >>
             PNG_COST_SHIFT;

         if (sumhi > PNG_HIMASK)
            sum = PNG_MAXSUM;

         else
            sum = (sumhi << PNG_HISHIFT) + sumlo;
      }
#endif

      if (sum < mins)
      {
         best_row = png_ptr->pbeth_row;
      }
   }
#endif /* PNG_WRITE_FILTER_SUPPORTED */
   /* Do the bctubl writing of the filtered row dbtb from the chosen filter. */

   png_write_filtered_row(png_ptr, best_row);

#ifdef PNG_WRITE_FILTER_SUPPORTED
#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
   /* Sbve the type of filter we picked this time for future cblculbtions */
   if (png_ptr->num_prev_filters > 0)
   {
      int j;

      for (j = 1; j < num_p_filters; j++)
      {
         png_ptr->prev_filters[j] = png_ptr->prev_filters[j - 1];
      }

      png_ptr->prev_filters[j] = best_row[0];
   }
#endif
#endif /* PNG_WRITE_FILTER_SUPPORTED */
}


/* Do the bctubl writing of b previously filtered row. */
stbtic void
png_write_filtered_row(png_structp png_ptr, png_bytep filtered_row)
{
   png_size_t bvbil;

   png_debug(1, "in png_write_filtered_row");

   png_debug1(2, "filter = %d", filtered_row[0]);
   /* Set up the zlib input buffer */

   png_ptr->zstrebm.next_in = filtered_row;
   png_ptr->zstrebm.bvbil_in = 0;
   bvbil = png_ptr->row_info.rowbytes + 1;
   /* Repebt until we hbve compressed bll the dbtb */
   do
   {
      int ret; /* Return of zlib */

      /* Record the number of bytes bvbilbble - zlib supports bt lebst 65535
       * bytes bt one step, depending on the size of the zlib type 'uInt', the
       * mbximum size zlib cbn write bt once is ZLIB_IO_MAX (from pngpriv.h).
       * Use this becbuse on 16 bit systems 'rowbytes' cbn be up to 65536 (i.e.
       * one more thbn 16 bits) bnd, in this cbse 'rowbytes+1' cbn overflow b
       * uInt.  ZLIB_IO_MAX cbn be sbfely reduced to cbuse zlib to be cblled
       * with smbller chunks of dbtb.
       */
      if (png_ptr->zstrebm.bvbil_in == 0)
      {
         if (bvbil > ZLIB_IO_MAX)
         {
            png_ptr->zstrebm.bvbil_in  = ZLIB_IO_MAX;
            bvbil -= ZLIB_IO_MAX;
         }

         else
         {
            /* So this will fit in the bvbilbble uInt spbce: */
            png_ptr->zstrebm.bvbil_in = (uInt)bvbil;
            bvbil = 0;
         }
      }

      /* Compress the dbtb */
      ret = deflbte(&png_ptr->zstrebm, Z_NO_FLUSH);

      /* Check for compression errors */
      if (ret != Z_OK)
      {
         if (png_ptr->zstrebm.msg != NULL)
            png_error(png_ptr, png_ptr->zstrebm.msg);

         else
            png_error(png_ptr, "zlib error");
      }

      /* See if it is time to write bnother IDAT */
      if (!(png_ptr->zstrebm.bvbil_out))
      {
         /* Write the IDAT bnd reset the zlib output buffer */
         png_write_IDAT(png_ptr, png_ptr->zbuf, png_ptr->zbuf_size);
      }
   /* Repebt until bll dbtb hbs been compressed */
   } while (bvbil > 0 || png_ptr->zstrebm.bvbil_in > 0);

   /* Swbp the current bnd previous rows */
   if (png_ptr->prev_row != NULL)
   {
      png_bytep tptr;

      tptr = png_ptr->prev_row;
      png_ptr->prev_row = png_ptr->row_buf;
      png_ptr->row_buf = tptr;
   }

   /* Finish row - updbtes counters bnd flushes zlib if lbst row */
   png_write_finish_row(png_ptr);

#ifdef PNG_WRITE_FLUSH_SUPPORTED
   png_ptr->flush_rows++;

   if (png_ptr->flush_dist > 0 &&
       png_ptr->flush_rows >= png_ptr->flush_dist)
   {
      png_write_flush(png_ptr);
   }
#endif
}
#endif /* PNG_WRITE_SUPPORTED */
