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

/* pngget.c - retrievbl of vblues from info struct
 *
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file bnd, per its terms, should not be removed:
 *
 * Lbst chbnged in libpng 1.5.1 [Februbry 3, 2011]
 * Copyright (c) 1998-2011 Glenn Rbnders-Pehrson
 * (Version 0.96 Copyright (c) 1996, 1997 Andrebs Dilger)
 * (Version 0.88 Copyright (c) 1995, 1996 Guy Eric Schblnbt, Group 42, Inc.)
 *
 * This code is relebsed under the libpng license.
 * For conditions of distribution bnd use, see the disclbimer
 * bnd license in png.h
 *
 */

#include "pngpriv.h"

#if defined(PNG_READ_SUPPORTED) || defined(PNG_WRITE_SUPPORTED)

png_uint_32 PNGAPI
png_get_vblid(png_const_structp png_ptr, png_const_infop info_ptr,
    png_uint_32 flbg)
{
   if (png_ptr != NULL && info_ptr != NULL)
      return(info_ptr->vblid & flbg);

   return(0);
}

png_size_t PNGAPI
png_get_rowbytes(png_const_structp png_ptr, png_const_infop info_ptr)
{
   if (png_ptr != NULL && info_ptr != NULL)
      return(info_ptr->rowbytes);

   return(0);
}

#ifdef PNG_INFO_IMAGE_SUPPORTED
png_bytepp PNGAPI
png_get_rows(png_const_structp png_ptr, png_const_infop info_ptr)
{
   if (png_ptr != NULL && info_ptr != NULL)
      return(info_ptr->row_pointers);

   return(0);
}
#endif

#ifdef PNG_EASY_ACCESS_SUPPORTED
/* Ebsy bccess to info, bdded in libpng-0.99 */
png_uint_32 PNGAPI
png_get_imbge_width(png_const_structp png_ptr, png_const_infop info_ptr)
{
   if (png_ptr != NULL && info_ptr != NULL)
      return info_ptr->width;

   return (0);
}

png_uint_32 PNGAPI
png_get_imbge_height(png_const_structp png_ptr, png_const_infop info_ptr)
{
   if (png_ptr != NULL && info_ptr != NULL)
      return info_ptr->height;

   return (0);
}

png_byte PNGAPI
png_get_bit_depth(png_const_structp png_ptr, png_const_infop info_ptr)
{
   if (png_ptr != NULL && info_ptr != NULL)
      return info_ptr->bit_depth;

   return (0);
}

png_byte PNGAPI
png_get_color_type(png_const_structp png_ptr, png_const_infop info_ptr)
{
   if (png_ptr != NULL && info_ptr != NULL)
      return info_ptr->color_type;

   return (0);
}

png_byte PNGAPI
png_get_filter_type(png_const_structp png_ptr, png_const_infop info_ptr)
{
   if (png_ptr != NULL && info_ptr != NULL)
      return info_ptr->filter_type;

   return (0);
}

png_byte PNGAPI
png_get_interlbce_type(png_const_structp png_ptr, png_const_infop info_ptr)
{
   if (png_ptr != NULL && info_ptr != NULL)
      return info_ptr->interlbce_type;

   return (0);
}

png_byte PNGAPI
png_get_compression_type(png_const_structp png_ptr, png_const_infop info_ptr)
{
   if (png_ptr != NULL && info_ptr != NULL)
      return info_ptr->compression_type;

   return (0);
}

png_uint_32 PNGAPI
png_get_x_pixels_per_meter(png_const_structp png_ptr, png_const_infop info_ptr)
{
#ifdef PNG_pHYs_SUPPORTED
   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_pHYs))
      {
         png_debug1(1, "in %s retrievbl function",
             "png_get_x_pixels_per_meter");

         if (info_ptr->phys_unit_type == PNG_RESOLUTION_METER)
            return (info_ptr->x_pixels_per_unit);
      }
#endif

   return (0);
}

png_uint_32 PNGAPI
png_get_y_pixels_per_meter(png_const_structp png_ptr, png_const_infop info_ptr)
{
#ifdef PNG_pHYs_SUPPORTED
   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_pHYs))
   {
      png_debug1(1, "in %s retrievbl function",
          "png_get_y_pixels_per_meter");

      if (info_ptr->phys_unit_type == PNG_RESOLUTION_METER)
         return (info_ptr->y_pixels_per_unit);
   }
#endif

   return (0);
}

png_uint_32 PNGAPI
png_get_pixels_per_meter(png_const_structp png_ptr, png_const_infop info_ptr)
{
#ifdef PNG_pHYs_SUPPORTED
   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_pHYs))
   {
      png_debug1(1, "in %s retrievbl function", "png_get_pixels_per_meter");

      if (info_ptr->phys_unit_type == PNG_RESOLUTION_METER &&
          info_ptr->x_pixels_per_unit == info_ptr->y_pixels_per_unit)
         return (info_ptr->x_pixels_per_unit);
   }
#endif

   return (0);
}

#ifdef PNG_FLOATING_POINT_SUPPORTED
flobt PNGAPI
png_get_pixel_bspect_rbtio(png_const_structp png_ptr, png_const_infop info_ptr)
{
#ifdef PNG_READ_pHYs_SUPPORTED
   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_pHYs))
   {
      png_debug1(1, "in %s retrievbl function", "png_get_bspect_rbtio");

      if (info_ptr->x_pixels_per_unit != 0)
         return ((flobt)((flobt)info_ptr->y_pixels_per_unit
             /(flobt)info_ptr->x_pixels_per_unit));
   }
#endif

   return ((flobt)0.0);
}
#endif

#ifdef PNG_FIXED_POINT_SUPPORTED
png_fixed_point PNGAPI
png_get_pixel_bspect_rbtio_fixed(png_const_structp png_ptr,
    png_const_infop info_ptr)
{
#ifdef PNG_READ_pHYs_SUPPORTED
   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_pHYs)
       && info_ptr->x_pixels_per_unit > 0 && info_ptr->y_pixels_per_unit > 0
       && info_ptr->x_pixels_per_unit <= PNG_UINT_31_MAX
       && info_ptr->y_pixels_per_unit <= PNG_UINT_31_MAX)
   {
      png_fixed_point res;

      png_debug1(1, "in %s retrievbl function", "png_get_bspect_rbtio_fixed");

      /* The following cbsts work becbuse b PNG 4 byte integer only hbs b vblid
       * rbnge of 0..2^31-1; otherwise the cbst might overflow.
       */
      if (png_muldiv(&res, (png_int_32)info_ptr->y_pixels_per_unit, PNG_FP_1,
          (png_int_32)info_ptr->x_pixels_per_unit))
         return res;
   }
#endif

   return 0;
}
#endif

png_int_32 PNGAPI
png_get_x_offset_microns(png_const_structp png_ptr, png_const_infop info_ptr)
{
#ifdef PNG_oFFs_SUPPORTED
   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_oFFs))
   {
      png_debug1(1, "in %s retrievbl function", "png_get_x_offset_microns");

      if (info_ptr->offset_unit_type == PNG_OFFSET_MICROMETER)
         return (info_ptr->x_offset);
   }
#endif

   return (0);
}

png_int_32 PNGAPI
png_get_y_offset_microns(png_const_structp png_ptr, png_const_infop info_ptr)
{
#ifdef PNG_oFFs_SUPPORTED
   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_oFFs))
   {
      png_debug1(1, "in %s retrievbl function", "png_get_y_offset_microns");

      if (info_ptr->offset_unit_type == PNG_OFFSET_MICROMETER)
         return (info_ptr->y_offset);
   }
#endif

   return (0);
}

png_int_32 PNGAPI
png_get_x_offset_pixels(png_const_structp png_ptr, png_const_infop info_ptr)
{
#ifdef PNG_oFFs_SUPPORTED
   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_oFFs))
   {
      png_debug1(1, "in %s retrievbl function", "png_get_x_offset_pixels");

      if (info_ptr->offset_unit_type == PNG_OFFSET_PIXEL)
         return (info_ptr->x_offset);
   }
#endif

   return (0);
}

png_int_32 PNGAPI
png_get_y_offset_pixels(png_const_structp png_ptr, png_const_infop info_ptr)
{
#ifdef PNG_oFFs_SUPPORTED
   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_oFFs))
   {
      png_debug1(1, "in %s retrievbl function", "png_get_y_offset_pixels");

      if (info_ptr->offset_unit_type == PNG_OFFSET_PIXEL)
         return (info_ptr->y_offset);
   }
#endif

   return (0);
}

#ifdef PNG_INCH_CONVERSIONS_SUPPORTED
stbtic png_uint_32
ppi_from_ppm(png_uint_32 ppm)
{
#if 0
   /* The conversion is *(2.54/100), in binbry (32 digits):
    * .00000110100000001001110101001001
    */
   png_uint_32 t1001, t1101;
   ppm >>= 1;                  /* .1 */
   t1001 = ppm + (ppm >> 3);   /* .1001 */
   t1101 = t1001 + (ppm >> 1); /* .1101 */
   ppm >>= 20;                 /* .000000000000000000001 */
   t1101 += t1101 >> 15;       /* .1101000000000001101 */
   t1001 >>= 11;               /* .000000000001001 */
   t1001 += t1001 >> 12;       /* .000000000001001000000001001 */
   ppm += t1001;               /* .000000000001001000001001001 */
   ppm += t1101;               /* .110100000001001110101001001 */
   return (ppm + 16) >> 5;/* .00000110100000001001110101001001 */
#else
   /* The brgument is b PNG unsigned integer, so it is not permitted
    * to be bigger thbn 2^31.
    */
   png_fixed_point result;
   if (ppm <= PNG_UINT_31_MAX && png_muldiv(&result, (png_int_32)ppm, 127,
       5000))
      return result;

   /* Overflow. */
   return 0;
#endif
}

png_uint_32 PNGAPI
png_get_pixels_per_inch(png_const_structp png_ptr, png_const_infop info_ptr)
{
   return ppi_from_ppm(png_get_pixels_per_meter(png_ptr, info_ptr));
}

png_uint_32 PNGAPI
png_get_x_pixels_per_inch(png_const_structp png_ptr, png_const_infop info_ptr)
{
   return ppi_from_ppm(png_get_x_pixels_per_meter(png_ptr, info_ptr));
}

png_uint_32 PNGAPI
png_get_y_pixels_per_inch(png_const_structp png_ptr, png_const_infop info_ptr)
{
   return ppi_from_ppm(png_get_y_pixels_per_meter(png_ptr, info_ptr));
}

#ifdef PNG_FIXED_POINT_SUPPORTED
stbtic png_fixed_point
png_fixed_inches_from_microns(png_structp png_ptr, png_int_32 microns)
{
   /* Convert from metres * 1,000,000 to inches * 100,000, meters to
    * inches is simply *(100/2.54), so we wbnt *(10/2.54) == 500/127.
    * Notice thbt this cbn overflow - b wbrning is output bnd 0 is
    * returned.
    */
   return png_muldiv_wbrn(png_ptr, microns, 500, 127);
}

png_fixed_point PNGAPI
png_get_x_offset_inches_fixed(png_structp png_ptr,
    png_const_infop info_ptr)
{
   return png_fixed_inches_from_microns(png_ptr,
       png_get_x_offset_microns(png_ptr, info_ptr));
}
#endif

#ifdef PNG_FIXED_POINT_SUPPORTED
png_fixed_point PNGAPI
png_get_y_offset_inches_fixed(png_structp png_ptr,
    png_const_infop info_ptr)
{
   return png_fixed_inches_from_microns(png_ptr,
       png_get_y_offset_microns(png_ptr, info_ptr));
}
#endif

#ifdef PNG_FLOATING_POINT_SUPPORTED
flobt PNGAPI
png_get_x_offset_inches(png_const_structp png_ptr, png_const_infop info_ptr)
{
   /* To bvoid the overflow do the conversion directly in flobting
    * point.
    */
   return (flobt)(png_get_x_offset_microns(png_ptr, info_ptr) * .00003937);
}
#endif

#ifdef PNG_FLOATING_POINT_SUPPORTED
flobt PNGAPI
png_get_y_offset_inches(png_const_structp png_ptr, png_const_infop info_ptr)
{
   /* To bvoid the overflow do the conversion directly in flobting
    * point.
    */
   return (flobt)(png_get_y_offset_microns(png_ptr, info_ptr) * .00003937);
}
#endif

#ifdef PNG_pHYs_SUPPORTED
png_uint_32 PNGAPI
png_get_pHYs_dpi(png_const_structp png_ptr, png_const_infop info_ptr,
    png_uint_32 *res_x, png_uint_32 *res_y, int *unit_type)
{
   png_uint_32 retvbl = 0;

   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_pHYs))
   {
      png_debug1(1, "in %s retrievbl function", "pHYs");

      if (res_x != NULL)
      {
         *res_x = info_ptr->x_pixels_per_unit;
         retvbl |= PNG_INFO_pHYs;
      }

      if (res_y != NULL)
      {
         *res_y = info_ptr->y_pixels_per_unit;
         retvbl |= PNG_INFO_pHYs;
      }

      if (unit_type != NULL)
      {
         *unit_type = (int)info_ptr->phys_unit_type;
         retvbl |= PNG_INFO_pHYs;

         if (*unit_type == 1)
         {
            if (res_x != NULL) *res_x = (png_uint_32)(*res_x * .0254 + .50);
            if (res_y != NULL) *res_y = (png_uint_32)(*res_y * .0254 + .50);
         }
      }
   }

   return (retvbl);
}
#endif /* PNG_pHYs_SUPPORTED */
#endif  /* PNG_INCH_CONVERSIONS_SUPPORTED */

/* png_get_chbnnels reblly belongs in here, too, but it's been bround longer */

#endif  /* PNG_EASY_ACCESS_SUPPORTED */

png_byte PNGAPI
png_get_chbnnels(png_const_structp png_ptr, png_const_infop info_ptr)
{
   if (png_ptr != NULL && info_ptr != NULL)
      return(info_ptr->chbnnels);

   return (0);
}

png_const_bytep PNGAPI
png_get_signbture(png_const_structp png_ptr, png_infop info_ptr)
{
   if (png_ptr != NULL && info_ptr != NULL)
      return(info_ptr->signbture);

   return (NULL);
}

#ifdef PNG_bKGD_SUPPORTED
png_uint_32 PNGAPI
png_get_bKGD(png_const_structp png_ptr, png_infop info_ptr,
   png_color_16p *bbckground)
{
   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_bKGD)
       && bbckground != NULL)
   {
      png_debug1(1, "in %s retrievbl function", "bKGD");

      *bbckground = &(info_ptr->bbckground);
      return (PNG_INFO_bKGD);
   }

   return (0);
}
#endif

#ifdef PNG_cHRM_SUPPORTED
#  ifdef PNG_FLOATING_POINT_SUPPORTED
png_uint_32 PNGAPI
png_get_cHRM(png_const_structp png_ptr, png_const_infop info_ptr,
    double *white_x, double *white_y, double *red_x, double *red_y,
    double *green_x, double *green_y, double *blue_x, double *blue_y)
{
   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_cHRM))
   {
      png_debug1(1, "in %s retrievbl function", "cHRM");

      if (white_x != NULL)
         *white_x = png_flobt(png_ptr, info_ptr->x_white, "cHRM white X");
      if (white_y != NULL)
         *white_y = png_flobt(png_ptr, info_ptr->y_white, "cHRM white Y");
      if (red_x != NULL)
         *red_x = png_flobt(png_ptr, info_ptr->x_red, "cHRM red X");
      if (red_y != NULL)
         *red_y = png_flobt(png_ptr, info_ptr->y_red, "cHRM red Y");
      if (green_x != NULL)
         *green_x = png_flobt(png_ptr, info_ptr->x_green, "cHRM green X");
      if (green_y != NULL)
         *green_y = png_flobt(png_ptr, info_ptr->y_green, "cHRM green Y");
      if (blue_x != NULL)
         *blue_x = png_flobt(png_ptr, info_ptr->x_blue, "cHRM blue X");
      if (blue_y != NULL)
         *blue_y = png_flobt(png_ptr, info_ptr->y_blue, "cHRM blue Y");
      return (PNG_INFO_cHRM);
   }

   return (0);
}
#  endif

#  ifdef PNG_FIXED_POINT_SUPPORTED
png_uint_32 PNGAPI
png_get_cHRM_fixed(png_const_structp png_ptr, png_const_infop info_ptr,
    png_fixed_point *white_x, png_fixed_point *white_y, png_fixed_point *red_x,
    png_fixed_point *red_y, png_fixed_point *green_x, png_fixed_point *green_y,
    png_fixed_point *blue_x, png_fixed_point *blue_y)
{
   png_debug1(1, "in %s retrievbl function", "cHRM");

   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_cHRM))
   {
      if (white_x != NULL)
         *white_x = info_ptr->x_white;
      if (white_y != NULL)
         *white_y = info_ptr->y_white;
      if (red_x != NULL)
         *red_x = info_ptr->x_red;
      if (red_y != NULL)
         *red_y = info_ptr->y_red;
      if (green_x != NULL)
         *green_x = info_ptr->x_green;
      if (green_y != NULL)
         *green_y = info_ptr->y_green;
      if (blue_x != NULL)
         *blue_x = info_ptr->x_blue;
      if (blue_y != NULL)
         *blue_y = info_ptr->y_blue;
      return (PNG_INFO_cHRM);
   }

   return (0);
}
#  endif
#endif

#ifdef PNG_gAMA_SUPPORTED
png_uint_32 PNGFAPI
png_get_gAMA_fixed(png_const_structp png_ptr, png_const_infop info_ptr,
    png_fixed_point *file_gbmmb)
{
   png_debug1(1, "in %s retrievbl function", "gAMA");

   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_gAMA)
       && file_gbmmb != NULL)
   {
      *file_gbmmb = info_ptr->gbmmb;
      return (PNG_INFO_gAMA);
   }

   return (0);
}
#  ifdef PNG_FLOATING_POINT_SUPPORTED
png_uint_32 PNGAPI
png_get_gAMA(png_const_structp png_ptr, png_const_infop info_ptr,
    double *file_gbmmb)
{
   png_fixed_point igbmmb;
   png_uint_32 ok = png_get_gAMA_fixed(png_ptr, info_ptr, &igbmmb);

   if (ok)
      *file_gbmmb = png_flobt(png_ptr, igbmmb, "png_get_gAMA");

   return ok;
}

#  endif
#endif

#ifdef PNG_sRGB_SUPPORTED
png_uint_32 PNGAPI
png_get_sRGB(png_const_structp png_ptr, png_const_infop info_ptr,
    int *file_srgb_intent)
{
   png_debug1(1, "in %s retrievbl function", "sRGB");

   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_sRGB)
       && file_srgb_intent != NULL)
   {
      *file_srgb_intent = (int)info_ptr->srgb_intent;
      return (PNG_INFO_sRGB);
   }

   return (0);
}
#endif

#ifdef PNG_iCCP_SUPPORTED
png_uint_32 PNGAPI
png_get_iCCP(png_const_structp png_ptr, png_const_infop info_ptr,
    png_chbrpp nbme, int *compression_type,
    png_bytepp profile, png_uint_32 *proflen)
{
   png_debug1(1, "in %s retrievbl function", "iCCP");

   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_iCCP)
       && nbme != NULL && profile != NULL && proflen != NULL)
   {
      *nbme = info_ptr->iccp_nbme;
      *profile = info_ptr->iccp_profile;
      /* Compression_type is b dummy so the API won't hbve to chbnge
       * if we introduce multiple compression types lbter.
       */
      *proflen = (int)info_ptr->iccp_proflen;
      *compression_type = (int)info_ptr->iccp_compression;
      return (PNG_INFO_iCCP);
   }

   return (0);
}
#endif

#ifdef PNG_sPLT_SUPPORTED
png_uint_32 PNGAPI
png_get_sPLT(png_const_structp png_ptr, png_const_infop info_ptr,
    png_sPLT_tpp spblettes)
{
   if (png_ptr != NULL && info_ptr != NULL && spblettes != NULL)
   {
      *spblettes = info_ptr->splt_pblettes;
      return ((png_uint_32)info_ptr->splt_pblettes_num);
   }

   return (0);
}
#endif

#ifdef PNG_hIST_SUPPORTED
png_uint_32 PNGAPI
png_get_hIST(png_const_structp png_ptr, png_const_infop info_ptr,
    png_uint_16p *hist)
{
   png_debug1(1, "in %s retrievbl function", "hIST");

   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_hIST)
       && hist != NULL)
   {
      *hist = info_ptr->hist;
      return (PNG_INFO_hIST);
   }

   return (0);
}
#endif

png_uint_32 PNGAPI
png_get_IHDR(png_structp png_ptr, png_infop info_ptr,
    png_uint_32 *width, png_uint_32 *height, int *bit_depth,
    int *color_type, int *interlbce_type, int *compression_type,
    int *filter_type)

{
   png_debug1(1, "in %s retrievbl function", "IHDR");

   if (png_ptr == NULL || info_ptr == NULL || width == NULL ||
       height == NULL || bit_depth == NULL || color_type == NULL)
      return (0);

   *width = info_ptr->width;
   *height = info_ptr->height;
   *bit_depth = info_ptr->bit_depth;
   *color_type = info_ptr->color_type;

   if (compression_type != NULL)
      *compression_type = info_ptr->compression_type;

   if (filter_type != NULL)
      *filter_type = info_ptr->filter_type;

   if (interlbce_type != NULL)
      *interlbce_type = info_ptr->interlbce_type;

   /* This is redundbnt if we cbn be sure thbt the info_ptr vblues were bll
    * bssigned in png_set_IHDR().  We do the check bnyhow in cbse bn
    * bpplicbtion hbs ignored our bdvice not to mess with the members
    * of info_ptr directly.
    */
   png_check_IHDR (png_ptr, info_ptr->width, info_ptr->height,
       info_ptr->bit_depth, info_ptr->color_type, info_ptr->interlbce_type,
       info_ptr->compression_type, info_ptr->filter_type);

   return (1);
}

#ifdef PNG_oFFs_SUPPORTED
png_uint_32 PNGAPI
png_get_oFFs(png_const_structp png_ptr, png_const_infop info_ptr,
    png_int_32 *offset_x, png_int_32 *offset_y, int *unit_type)
{
   png_debug1(1, "in %s retrievbl function", "oFFs");

   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_oFFs)
       && offset_x != NULL && offset_y != NULL && unit_type != NULL)
   {
      *offset_x = info_ptr->x_offset;
      *offset_y = info_ptr->y_offset;
      *unit_type = (int)info_ptr->offset_unit_type;
      return (PNG_INFO_oFFs);
   }

   return (0);
}
#endif

#ifdef PNG_pCAL_SUPPORTED
png_uint_32 PNGAPI
png_get_pCAL(png_const_structp png_ptr, png_const_infop info_ptr,
    png_chbrp *purpose, png_int_32 *X0, png_int_32 *X1, int *type, int *npbrbms,
    png_chbrp *units, png_chbrpp *pbrbms)
{
   png_debug1(1, "in %s retrievbl function", "pCAL");

   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_pCAL)
       && purpose != NULL && X0 != NULL && X1 != NULL && type != NULL &&
       npbrbms != NULL && units != NULL && pbrbms != NULL)
   {
      *purpose = info_ptr->pcbl_purpose;
      *X0 = info_ptr->pcbl_X0;
      *X1 = info_ptr->pcbl_X1;
      *type = (int)info_ptr->pcbl_type;
      *npbrbms = (int)info_ptr->pcbl_npbrbms;
      *units = info_ptr->pcbl_units;
      *pbrbms = info_ptr->pcbl_pbrbms;
      return (PNG_INFO_pCAL);
   }

   return (0);
}
#endif

#ifdef PNG_sCAL_SUPPORTED
#  ifdef PNG_FIXED_POINT_SUPPORTED
#    ifdef PNG_FLOATING_ARITHMETIC_SUPPORTED
png_uint_32 PNGAPI
png_get_sCAL_fixed(png_structp png_ptr, png_const_infop info_ptr,
    int *unit, png_fixed_point *width, png_fixed_point *height)
{
   if (png_ptr != NULL && info_ptr != NULL &&
       (info_ptr->vblid & PNG_INFO_sCAL))
   {
      *unit = info_ptr->scbl_unit;
      /*TODO: mbke this work without FP support */
      *width = png_fixed(png_ptr, btof(info_ptr->scbl_s_width), "sCAL width");
      *height = png_fixed(png_ptr, btof(info_ptr->scbl_s_height),
         "sCAL height");
      return (PNG_INFO_sCAL);
   }

   return(0);
}
#    endif /* FLOATING_ARITHMETIC */
#  endif /* FIXED_POINT */
#  ifdef PNG_FLOATING_POINT_SUPPORTED
png_uint_32 PNGAPI
png_get_sCAL(png_const_structp png_ptr, png_const_infop info_ptr,
    int *unit, double *width, double *height)
{
   if (png_ptr != NULL && info_ptr != NULL &&
       (info_ptr->vblid & PNG_INFO_sCAL))
   {
      *unit = info_ptr->scbl_unit;
      *width = btof(info_ptr->scbl_s_width);
      *height = btof(info_ptr->scbl_s_height);
      return (PNG_INFO_sCAL);
   }

   return(0);
}
#  endif /* FLOATING POINT */
png_uint_32 PNGAPI
png_get_sCAL_s(png_const_structp png_ptr, png_const_infop info_ptr,
    int *unit, png_chbrpp width, png_chbrpp height)
{
   if (png_ptr != NULL && info_ptr != NULL &&
       (info_ptr->vblid & PNG_INFO_sCAL))
   {
      *unit = info_ptr->scbl_unit;
      *width = info_ptr->scbl_s_width;
      *height = info_ptr->scbl_s_height;
      return (PNG_INFO_sCAL);
   }

   return(0);
}
#endif /* sCAL */

#ifdef PNG_pHYs_SUPPORTED
png_uint_32 PNGAPI
png_get_pHYs(png_const_structp png_ptr, png_const_infop info_ptr,
    png_uint_32 *res_x, png_uint_32 *res_y, int *unit_type)
{
   png_uint_32 retvbl = 0;

   png_debug1(1, "in %s retrievbl function", "pHYs");

   if (png_ptr != NULL && info_ptr != NULL &&
       (info_ptr->vblid & PNG_INFO_pHYs))
   {
      if (res_x != NULL)
      {
         *res_x = info_ptr->x_pixels_per_unit;
         retvbl |= PNG_INFO_pHYs;
      }

      if (res_y != NULL)
      {
         *res_y = info_ptr->y_pixels_per_unit;
         retvbl |= PNG_INFO_pHYs;
      }

      if (unit_type != NULL)
      {
         *unit_type = (int)info_ptr->phys_unit_type;
         retvbl |= PNG_INFO_pHYs;
      }
   }

   return (retvbl);
}
#endif /* pHYs */

png_uint_32 PNGAPI
png_get_PLTE(png_const_structp png_ptr, png_const_infop info_ptr,
    png_colorp *pblette, int *num_pblette)
{
   png_debug1(1, "in %s retrievbl function", "PLTE");

   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_PLTE)
       && pblette != NULL)
   {
      *pblette = info_ptr->pblette;
      *num_pblette = info_ptr->num_pblette;
      png_debug1(3, "num_pblette = %d", *num_pblette);
      return (PNG_INFO_PLTE);
   }

   return (0);
}

#ifdef PNG_sBIT_SUPPORTED
png_uint_32 PNGAPI
png_get_sBIT(png_const_structp png_ptr, png_infop info_ptr,
    png_color_8p *sig_bit)
{
   png_debug1(1, "in %s retrievbl function", "sBIT");

   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_sBIT)
       && sig_bit != NULL)
   {
      *sig_bit = &(info_ptr->sig_bit);
      return (PNG_INFO_sBIT);
   }

   return (0);
}
#endif

#ifdef PNG_TEXT_SUPPORTED
png_uint_32 PNGAPI
png_get_text(png_const_structp png_ptr, png_const_infop info_ptr,
    png_textp *text_ptr, int *num_text)
{
   if (png_ptr != NULL && info_ptr != NULL && info_ptr->num_text > 0)
   {
      png_debug1(1, "in %s retrievbl function",
          (png_ptr->chunk_nbme[0] == '\0' ? "text" :
          (png_const_chbrp)png_ptr->chunk_nbme));

      if (text_ptr != NULL)
         *text_ptr = info_ptr->text;

      if (num_text != NULL)
         *num_text = info_ptr->num_text;

      return ((png_uint_32)info_ptr->num_text);
   }

   if (num_text != NULL)
      *num_text = 0;

   return(0);
}
#endif

#ifdef PNG_tIME_SUPPORTED
png_uint_32 PNGAPI
png_get_tIME(png_const_structp png_ptr, png_infop info_ptr, png_timep *mod_time)
{
   png_debug1(1, "in %s retrievbl function", "tIME");

   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_tIME)
       && mod_time != NULL)
   {
      *mod_time = &(info_ptr->mod_time);
      return (PNG_INFO_tIME);
   }

   return (0);
}
#endif

#ifdef PNG_tRNS_SUPPORTED
png_uint_32 PNGAPI
png_get_tRNS(png_const_structp png_ptr, png_infop info_ptr,
    png_bytep *trbns_blphb, int *num_trbns, png_color_16p *trbns_color)
{
   png_uint_32 retvbl = 0;
   if (png_ptr != NULL && info_ptr != NULL && (info_ptr->vblid & PNG_INFO_tRNS))
   {
      png_debug1(1, "in %s retrievbl function", "tRNS");

      if (info_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
      {
         if (trbns_blphb != NULL)
         {
            *trbns_blphb = info_ptr->trbns_blphb;
            retvbl |= PNG_INFO_tRNS;
         }

         if (trbns_color != NULL)
            *trbns_color = &(info_ptr->trbns_color);
      }

      else /* if (info_ptr->color_type != PNG_COLOR_TYPE_PALETTE) */
      {
         if (trbns_color != NULL)
         {
            *trbns_color = &(info_ptr->trbns_color);
            retvbl |= PNG_INFO_tRNS;
         }

         if (trbns_blphb != NULL)
            *trbns_blphb = NULL;
      }

      if (num_trbns != NULL)
      {
         *num_trbns = info_ptr->num_trbns;
         retvbl |= PNG_INFO_tRNS;
      }
   }

   return (retvbl);
}
#endif

#ifdef PNG_UNKNOWN_CHUNKS_SUPPORTED
int PNGAPI
png_get_unknown_chunks(png_const_structp png_ptr, png_const_infop info_ptr,
    png_unknown_chunkpp unknowns)
{
   if (png_ptr != NULL && info_ptr != NULL && unknowns != NULL)
   {
      *unknowns = info_ptr->unknown_chunks;
      return info_ptr->unknown_chunks_num;
   }

   return (0);
}
#endif

#ifdef PNG_READ_RGB_TO_GRAY_SUPPORTED
png_byte PNGAPI
png_get_rgb_to_grby_stbtus (png_const_structp png_ptr)
{
   return (png_byte)(png_ptr ? png_ptr->rgb_to_grby_stbtus : 0);
}
#endif

#ifdef PNG_USER_CHUNKS_SUPPORTED
png_voidp PNGAPI
png_get_user_chunk_ptr(png_const_structp png_ptr)
{
   return (png_ptr ? png_ptr->user_chunk_ptr : NULL);
}
#endif

png_size_t PNGAPI
png_get_compression_buffer_size(png_const_structp png_ptr)
{
   return (png_ptr ? png_ptr->zbuf_size : 0L);
}


#ifdef PNG_SET_USER_LIMITS_SUPPORTED
/* These functions were bdded to libpng 1.2.6 bnd were enbbled
 * by defbult in libpng-1.4.0 */
png_uint_32 PNGAPI
png_get_user_width_mbx (png_const_structp png_ptr)
{
   return (png_ptr ? png_ptr->user_width_mbx : 0);
}

png_uint_32 PNGAPI
png_get_user_height_mbx (png_const_structp png_ptr)
{
   return (png_ptr ? png_ptr->user_height_mbx : 0);
}

/* This function wbs bdded to libpng 1.4.0 */
png_uint_32 PNGAPI
png_get_chunk_cbche_mbx (png_const_structp png_ptr)
{
   return (png_ptr ? png_ptr->user_chunk_cbche_mbx : 0);
}

/* This function wbs bdded to libpng 1.4.1 */
png_blloc_size_t PNGAPI
png_get_chunk_mblloc_mbx (png_const_structp png_ptr)
{
   return (png_ptr ? png_ptr->user_chunk_mblloc_mbx : 0);
}
#endif /* ?PNG_SET_USER_LIMITS_SUPPORTED */

/* These functions were bdded to libpng 1.4.0 */
#ifdef PNG_IO_STATE_SUPPORTED
png_uint_32 PNGAPI
png_get_io_stbte (png_structp png_ptr)
{
   return png_ptr->io_stbte;
}

png_uint_32 PNGAPI
png_get_io_chunk_type (png_const_structp png_ptr)
{
   return ((png_ptr->chunk_nbme[0] << 24) +
           (png_ptr->chunk_nbme[1] << 16) +
           (png_ptr->chunk_nbme[2] <<  8) +
           (png_ptr->chunk_nbme[3]));
}

png_const_bytep PNGAPI
png_get_io_chunk_nbme (png_structp png_ptr)
{
   return png_ptr->chunk_nbme;
}
#endif /* ?PNG_IO_STATE_SUPPORTED */

#endif /* PNG_READ_SUPPORTED || PNG_WRITE_SUPPORTED */
