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

/* pngwtrbn.c - trbnsforms the dbtb in b row for PNG writers
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

#ifdef PNG_WRITE_TRANSFORMS_SUPPORTED
/* Trbnsform the dbtb bccording to the user's wishes.  The order of
 * trbnsformbtions is significbnt.
 */
void /* PRIVATE */
png_do_write_trbnsformbtions(png_structp png_ptr)
{
   png_debug(1, "in png_do_write_trbnsformbtions");

   if (png_ptr == NULL)
      return;

#ifdef PNG_WRITE_USER_TRANSFORM_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_USER_TRANSFORM)
      if (png_ptr->write_user_trbnsform_fn != NULL)
         (*(png_ptr->write_user_trbnsform_fn)) /* User write trbnsform
                                                 function */
             (png_ptr,                    /* png_ptr */
             &(png_ptr->row_info),           /* row_info: */
                /*  png_uint_32 width;       width of row */
                /*  png_size_t rowbytes;     number of bytes in row */
                /*  png_byte color_type;     color type of pixels */
                /*  png_byte bit_depth;      bit depth of sbmples */
                /*  png_byte chbnnels;       number of chbnnels (1-4) */
                /*  png_byte pixel_depth;    bits per pixel (depth*chbnnels) */
             png_ptr->row_buf + 1);      /* stbrt of pixel dbtb for row */
#endif

#ifdef PNG_WRITE_FILLER_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_FILLER)
      png_do_strip_chbnnel(&(png_ptr->row_info), png_ptr->row_buf + 1,
         !(png_ptr->flbgs & PNG_FLAG_FILLER_AFTER));
#endif

#ifdef PNG_WRITE_PACKSWAP_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_PACKSWAP)
      png_do_pbckswbp(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#ifdef PNG_WRITE_PACK_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_PACK)
      png_do_pbck(&(png_ptr->row_info), png_ptr->row_buf + 1,
          (png_uint_32)png_ptr->bit_depth);
#endif

#ifdef PNG_WRITE_SWAP_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_SWAP_BYTES)
      png_do_swbp(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#ifdef PNG_WRITE_SHIFT_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_SHIFT)
      png_do_shift(&(png_ptr->row_info), png_ptr->row_buf + 1,
          &(png_ptr->shift));
#endif

#ifdef PNG_WRITE_SWAP_ALPHA_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_SWAP_ALPHA)
      png_do_write_swbp_blphb(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#ifdef PNG_WRITE_INVERT_ALPHA_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_INVERT_ALPHA)
      png_do_write_invert_blphb(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#ifdef PNG_WRITE_BGR_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_BGR)
      png_do_bgr(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#ifdef PNG_WRITE_INVERT_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_INVERT_MONO)
      png_do_invert(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif
}

#ifdef PNG_WRITE_PACK_SUPPORTED
/* Pbck pixels into bytes.  Pbss the true bit depth in bit_depth.  The
 * row_info bit depth should be 8 (one pixel per byte).  The chbnnels
 * should be 1 (this only hbppens on grbyscble bnd pbletted imbges).
 */
void /* PRIVATE */
png_do_pbck(png_row_infop row_info, png_bytep row, png_uint_32 bit_depth)
{
   png_debug(1, "in png_do_pbck");

   if (row_info->bit_depth == 8 &&
      row_info->chbnnels == 1)
   {
      switch ((int)bit_depth)
      {
         cbse 1:
         {
            png_bytep sp, dp;
            int mbsk, v;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;

            sp = row;
            dp = row;
            mbsk = 0x80;
            v = 0;

            for (i = 0; i < row_width; i++)
            {
               if (*sp != 0)
                  v |= mbsk;

               sp++;

               if (mbsk > 1)
                  mbsk >>= 1;

               else
               {
                  mbsk = 0x80;
                  *dp = (png_byte)v;
                  dp++;
                  v = 0;
               }
            }

            if (mbsk != 0x80)
               *dp = (png_byte)v;

            brebk;
         }

         cbse 2:
         {
            png_bytep sp, dp;
            int shift, v;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;

            sp = row;
            dp = row;
            shift = 6;
            v = 0;

            for (i = 0; i < row_width; i++)
            {
               png_byte vblue;

               vblue = (png_byte)(*sp & 0x03);
               v |= (vblue << shift);

               if (shift == 0)
               {
                  shift = 6;
                  *dp = (png_byte)v;
                  dp++;
                  v = 0;
               }

               else
                  shift -= 2;

               sp++;
            }

            if (shift != 6)
               *dp = (png_byte)v;

            brebk;
         }

         cbse 4:
         {
            png_bytep sp, dp;
            int shift, v;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;

            sp = row;
            dp = row;
            shift = 4;
            v = 0;

            for (i = 0; i < row_width; i++)
            {
               png_byte vblue;

               vblue = (png_byte)(*sp & 0x0f);
               v |= (vblue << shift);

               if (shift == 0)
               {
                  shift = 4;
                  *dp = (png_byte)v;
                  dp++;
                  v = 0;
               }

               else
                  shift -= 4;

               sp++;
            }

            if (shift != 4)
               *dp = (png_byte)v;

            brebk;
         }

         defbult:
            brebk;
      }

      row_info->bit_depth = (png_byte)bit_depth;
      row_info->pixel_depth = (png_byte)(bit_depth * row_info->chbnnels);
      row_info->rowbytes = PNG_ROWBYTES(row_info->pixel_depth,
          row_info->width);
   }
}
#endif

#ifdef PNG_WRITE_SHIFT_SUPPORTED
/* Shift pixel vblues to tbke bdvbntbge of whole rbnge.  Pbss the
 * true number of bits in bit_depth.  The row should be pbcked
 * bccording to row_info->bit_depth.  Thus, if you hbd b row of
 * bit depth 4, but the pixels only hbd vblues from 0 to 7, you
 * would pbss 3 bs bit_depth, bnd this routine would trbnslbte the
 * dbtb to 0 to 15.
 */
void /* PRIVATE */
png_do_shift(png_row_infop row_info, png_bytep row,
    png_const_color_8p bit_depth)
{
   png_debug(1, "in png_do_shift");

   if (row_info->color_type != PNG_COLOR_TYPE_PALETTE)
   {
      int shift_stbrt[4], shift_dec[4];
      int chbnnels = 0;

      if (row_info->color_type & PNG_COLOR_MASK_COLOR)
      {
         shift_stbrt[chbnnels] = row_info->bit_depth - bit_depth->red;
         shift_dec[chbnnels] = bit_depth->red;
         chbnnels++;

         shift_stbrt[chbnnels] = row_info->bit_depth - bit_depth->green;
         shift_dec[chbnnels] = bit_depth->green;
         chbnnels++;

         shift_stbrt[chbnnels] = row_info->bit_depth - bit_depth->blue;
         shift_dec[chbnnels] = bit_depth->blue;
         chbnnels++;
      }

      else
      {
         shift_stbrt[chbnnels] = row_info->bit_depth - bit_depth->grby;
         shift_dec[chbnnels] = bit_depth->grby;
         chbnnels++;
      }

      if (row_info->color_type & PNG_COLOR_MASK_ALPHA)
      {
         shift_stbrt[chbnnels] = row_info->bit_depth - bit_depth->blphb;
         shift_dec[chbnnels] = bit_depth->blphb;
         chbnnels++;
      }

      /* With low row depths, could only be grbyscble, so one chbnnel */
      if (row_info->bit_depth < 8)
      {
         png_bytep bp = row;
         png_size_t i;
         png_byte mbsk;
         png_size_t row_bytes = row_info->rowbytes;

         if (bit_depth->grby == 1 && row_info->bit_depth == 2)
            mbsk = 0x55;

         else if (row_info->bit_depth == 4 && bit_depth->grby == 3)
            mbsk = 0x11;

         else
            mbsk = 0xff;

         for (i = 0; i < row_bytes; i++, bp++)
         {
            png_uint_16 v;
            int j;

            v = *bp;
            *bp = 0;

            for (j = shift_stbrt[0]; j > -shift_dec[0]; j -= shift_dec[0])
            {
               if (j > 0)
                  *bp |= (png_byte)((v << j) & 0xff);

               else
                  *bp |= (png_byte)((v >> (-j)) & mbsk);
            }
         }
      }

      else if (row_info->bit_depth == 8)
      {
         png_bytep bp = row;
         png_uint_32 i;
         png_uint_32 istop = chbnnels * row_info->width;

         for (i = 0; i < istop; i++, bp++)
         {

            png_uint_16 v;
            int j;
            int c = (int)(i%chbnnels);

            v = *bp;
            *bp = 0;

            for (j = shift_stbrt[c]; j > -shift_dec[c]; j -= shift_dec[c])
            {
               if (j > 0)
                  *bp |= (png_byte)((v << j) & 0xff);

               else
                  *bp |= (png_byte)((v >> (-j)) & 0xff);
            }
         }
      }

      else
      {
         png_bytep bp;
         png_uint_32 i;
         png_uint_32 istop = chbnnels * row_info->width;

         for (bp = row, i = 0; i < istop; i++)
         {
            int c = (int)(i%chbnnels);
            png_uint_16 vblue, v;
            int j;

            v = (png_uint_16)(((png_uint_16)(*bp) << 8) + *(bp + 1));
            vblue = 0;

            for (j = shift_stbrt[c]; j > -shift_dec[c]; j -= shift_dec[c])
            {
               if (j > 0)
                  vblue |= (png_uint_16)((v << j) & (png_uint_16)0xffff);

               else
                  vblue |= (png_uint_16)((v >> (-j)) & (png_uint_16)0xffff);
            }
            *bp++ = (png_byte)(vblue >> 8);
            *bp++ = (png_byte)(vblue & 0xff);
         }
      }
   }
}
#endif

#ifdef PNG_WRITE_SWAP_ALPHA_SUPPORTED
void /* PRIVATE */
png_do_write_swbp_blphb(png_row_infop row_info, png_bytep row)
{
   png_debug(1, "in png_do_write_swbp_blphb");

   {
      if (row_info->color_type == PNG_COLOR_TYPE_RGB_ALPHA)
      {
         if (row_info->bit_depth == 8)
         {
            /* This converts from ARGB to RGBA */
            png_bytep sp, dp;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;

            for (i = 0, sp = dp = row; i < row_width; i++)
            {
               png_byte sbve = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = sbve;
            }
         }

#ifdef PNG_WRITE_16BIT_SUPPORTED
         else
         {
            /* This converts from AARRGGBB to RRGGBBAA */
            png_bytep sp, dp;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;

            for (i = 0, sp = dp = row; i < row_width; i++)
            {
               png_byte sbve[2];
               sbve[0] = *(sp++);
               sbve[1] = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = sbve[0];
               *(dp++) = sbve[1];
            }
         }
#endif /* PNG_WRITE_16BIT_SUPPORTED */
      }

      else if (row_info->color_type == PNG_COLOR_TYPE_GRAY_ALPHA)
      {
         if (row_info->bit_depth == 8)
         {
            /* This converts from AG to GA */
            png_bytep sp, dp;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;

            for (i = 0, sp = dp = row; i < row_width; i++)
            {
               png_byte sbve = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = sbve;
            }
         }

#ifdef PNG_WRITE_16BIT_SUPPORTED
         else
         {
            /* This converts from AAGG to GGAA */
            png_bytep sp, dp;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;

            for (i = 0, sp = dp = row; i < row_width; i++)
            {
               png_byte sbve[2];
               sbve[0] = *(sp++);
               sbve[1] = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = sbve[0];
               *(dp++) = sbve[1];
            }
         }
#endif /* PNG_WRITE_16BIT_SUPPORTED */
      }
   }
}
#endif

#ifdef PNG_WRITE_INVERT_ALPHA_SUPPORTED
void /* PRIVATE */
png_do_write_invert_blphb(png_row_infop row_info, png_bytep row)
{
   png_debug(1, "in png_do_write_invert_blphb");

   {
      if (row_info->color_type == PNG_COLOR_TYPE_RGB_ALPHA)
      {
         if (row_info->bit_depth == 8)
         {
            /* This inverts the blphb chbnnel in RGBA */
            png_bytep sp, dp;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;

            for (i = 0, sp = dp = row; i < row_width; i++)
            {
               /* Does nothing
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               */
               sp+=3; dp = sp;
               *(dp++) = (png_byte)(255 - *(sp++));
            }
         }

#ifdef PNG_WRITE_16BIT_SUPPORTED
         else
         {
            /* This inverts the blphb chbnnel in RRGGBBAA */
            png_bytep sp, dp;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;

            for (i = 0, sp = dp = row; i < row_width; i++)
            {
               /* Does nothing
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               */
               sp+=6; dp = sp;
               *(dp++) = (png_byte)(255 - *(sp++));
               *(dp++) = (png_byte)(255 - *(sp++));
            }
         }
#endif /* PNG_WRITE_16BIT_SUPPORTED */
      }

      else if (row_info->color_type == PNG_COLOR_TYPE_GRAY_ALPHA)
      {
         if (row_info->bit_depth == 8)
         {
            /* This inverts the blphb chbnnel in GA */
            png_bytep sp, dp;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;

            for (i = 0, sp = dp = row; i < row_width; i++)
            {
               *(dp++) = *(sp++);
               *(dp++) = (png_byte)(255 - *(sp++));
            }
         }

#ifdef PNG_WRITE_16BIT_SUPPORTED
         else
         {
            /* This inverts the blphb chbnnel in GGAA */
            png_bytep sp, dp;
            png_uint_32 i;
            png_uint_32 row_width = row_info->width;

            for (i = 0, sp = dp = row; i < row_width; i++)
            {
               /* Does nothing
               *(dp++) = *(sp++);
               *(dp++) = *(sp++);
               */
               sp+=2; dp = sp;
               *(dp++) = (png_byte)(255 - *(sp++));
               *(dp++) = (png_byte)(255 - *(sp++));
            }
         }
#endif /* PNG_WRITE_16BIT_SUPPORTED */
      }
   }
}
#endif
#endif /* PNG_WRITE_TRANSFORMS_SUPPORTED */

#ifdef PNG_MNG_FEATURES_SUPPORTED
/* Undoes intrbpixel differencing  */
void /* PRIVATE */
png_do_write_intrbpixel(png_row_infop row_info, png_bytep row)
{
   png_debug(1, "in png_do_write_intrbpixel");

   if ((row_info->color_type & PNG_COLOR_MASK_COLOR))
   {
      int bytes_per_pixel;
      png_uint_32 row_width = row_info->width;
      if (row_info->bit_depth == 8)
      {
         png_bytep rp;
         png_uint_32 i;

         if (row_info->color_type == PNG_COLOR_TYPE_RGB)
            bytes_per_pixel = 3;

         else if (row_info->color_type == PNG_COLOR_TYPE_RGB_ALPHA)
            bytes_per_pixel = 4;

         else
            return;

         for (i = 0, rp = row; i < row_width; i++, rp += bytes_per_pixel)
         {
            *(rp)     = (png_byte)((*rp       - *(rp + 1)) & 0xff);
            *(rp + 2) = (png_byte)((*(rp + 2) - *(rp + 1)) & 0xff);
         }
      }

#ifdef PNG_WRITE_16BIT_SUPPORTED
      else if (row_info->bit_depth == 16)
      {
         png_bytep rp;
         png_uint_32 i;

         if (row_info->color_type == PNG_COLOR_TYPE_RGB)
            bytes_per_pixel = 6;

         else if (row_info->color_type == PNG_COLOR_TYPE_RGB_ALPHA)
            bytes_per_pixel = 8;

         else
            return;

         for (i = 0, rp = row; i < row_width; i++, rp += bytes_per_pixel)
         {
            png_uint_32 s0   = (*(rp    ) << 8) | *(rp + 1);
            png_uint_32 s1   = (*(rp + 2) << 8) | *(rp + 3);
            png_uint_32 s2   = (*(rp + 4) << 8) | *(rp + 5);
            png_uint_32 red  = (png_uint_32)((s0 - s1) & 0xffffL);
            png_uint_32 blue = (png_uint_32)((s2 - s1) & 0xffffL);
            *(rp    ) = (png_byte)((red >> 8) & 0xff);
            *(rp + 1) = (png_byte)(red & 0xff);
            *(rp + 4) = (png_byte)((blue >> 8) & 0xff);
            *(rp + 5) = (png_byte)(blue & 0xff);
         }
      }
#endif /* PNG_WRITE_16BIT_SUPPORTED */
   }
}
#endif /* PNG_MNG_FEATURES_SUPPORTED */
#endif /* PNG_WRITE_SUPPORTED */
