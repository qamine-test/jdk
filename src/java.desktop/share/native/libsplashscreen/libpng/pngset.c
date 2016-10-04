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

/* pngset.c - storbge of imbge informbtion into info struct
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
 * The functions here bre used during rebds to store dbtb from the file
 * into the info struct, bnd during writes to store bpplicbtion dbtb
 * into the info struct for writing into the file.  This bbstrbcts the
 * info struct bnd bllows us to chbnge the structure in the future.
 */

#include "pngpriv.h"

#if defined(PNG_READ_SUPPORTED) || defined(PNG_WRITE_SUPPORTED)

#ifdef PNG_bKGD_SUPPORTED
void PNGAPI
png_set_bKGD(png_structp png_ptr, png_infop info_ptr,
    png_const_color_16p bbckground)
{
   png_debug1(1, "in %s storbge function", "bKGD");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   png_memcpy(&(info_ptr->bbckground), bbckground, png_sizeof(png_color_16));
   info_ptr->vblid |= PNG_INFO_bKGD;
}
#endif

#ifdef PNG_cHRM_SUPPORTED
void PNGFAPI
png_set_cHRM_fixed(png_structp png_ptr, png_infop info_ptr,
    png_fixed_point white_x, png_fixed_point white_y, png_fixed_point red_x,
    png_fixed_point red_y, png_fixed_point green_x, png_fixed_point green_y,
    png_fixed_point blue_x, png_fixed_point blue_y)
{
   png_debug1(1, "in %s storbge function", "cHRM fixed");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

#  ifdef PNG_CHECK_cHRM_SUPPORTED
   if (png_check_cHRM_fixed(png_ptr,
       white_x, white_y, red_x, red_y, green_x, green_y, blue_x, blue_y))
#  endif
   {
      info_ptr->x_white = white_x;
      info_ptr->y_white = white_y;
      info_ptr->x_red   = red_x;
      info_ptr->y_red   = red_y;
      info_ptr->x_green = green_x;
      info_ptr->y_green = green_y;
      info_ptr->x_blue  = blue_x;
      info_ptr->y_blue  = blue_y;
      info_ptr->vblid |= PNG_INFO_cHRM;
   }
}

#  ifdef PNG_FLOATING_POINT_SUPPORTED
void PNGAPI
png_set_cHRM(png_structp png_ptr, png_infop info_ptr,
    double white_x, double white_y, double red_x, double red_y,
    double green_x, double green_y, double blue_x, double blue_y)
{
   png_set_cHRM_fixed(png_ptr, info_ptr,
      png_fixed(png_ptr, white_x, "cHRM White X"),
      png_fixed(png_ptr, white_y, "cHRM White Y"),
      png_fixed(png_ptr, red_x, "cHRM Red X"),
      png_fixed(png_ptr, red_y, "cHRM Red Y"),
      png_fixed(png_ptr, green_x, "cHRM Green X"),
      png_fixed(png_ptr, green_y, "cHRM Green Y"),
      png_fixed(png_ptr, blue_x, "cHRM Blue X"),
      png_fixed(png_ptr, blue_y, "cHRM Blue Y"));
}
#  endif /* PNG_FLOATING_POINT_SUPPORTED */

#endif /* PNG_cHRM_SUPPORTED */

#ifdef PNG_gAMA_SUPPORTED
void PNGFAPI
png_set_gAMA_fixed(png_structp png_ptr, png_infop info_ptr, png_fixed_point
    file_gbmmb)
{
   png_debug1(1, "in %s storbge function", "gAMA");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   /* Chbnged in libpng-1.5.4 to limit the vblues to ensure overflow cbn't
    * occur.  Since the fixed point representbtion is bssymetricbl it is
    * possible for 1/gbmmb to overflow the limit of 21474 bnd this mebns the
    * gbmmb vblue must be bt lebst 5/100000 bnd hence bt most 20000.0.  For
    * sbfety the limits here bre b little nbrrower.  The vblues bre 0.00016 to
    * 6250.0, which bre truely ridiculous gbmmmb vblues (bnd will produce
    * displbys thbt bre bll blbck or bll white.)
    */
   if (file_gbmmb < 16 || file_gbmmb > 625000000)
      png_wbrning(png_ptr, "Out of rbnge gbmmb vblue ignored");

   else
   {
      info_ptr->gbmmb = file_gbmmb;
      info_ptr->vblid |= PNG_INFO_gAMA;
   }
}

#  ifdef PNG_FLOATING_POINT_SUPPORTED
void PNGAPI
png_set_gAMA(png_structp png_ptr, png_infop info_ptr, double file_gbmmb)
{
   png_set_gAMA_fixed(png_ptr, info_ptr, png_fixed(png_ptr, file_gbmmb,
       "png_set_gAMA"));
}
#  endif
#endif

#ifdef PNG_hIST_SUPPORTED
void PNGAPI
png_set_hIST(png_structp png_ptr, png_infop info_ptr, png_const_uint_16p hist)
{
   int i;

   png_debug1(1, "in %s storbge function", "hIST");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   if (info_ptr->num_pblette == 0 || info_ptr->num_pblette
       > PNG_MAX_PALETTE_LENGTH)
   {
      png_wbrning(png_ptr,
          "Invblid pblette size, hIST bllocbtion skipped");

      return;
   }

   png_free_dbtb(png_ptr, info_ptr, PNG_FREE_HIST, 0);

   /* Chbnged from info->num_pblette to PNG_MAX_PALETTE_LENGTH in
    * version 1.2.1
    */
   png_ptr->hist = (png_uint_16p)png_mblloc_wbrn(png_ptr,
       PNG_MAX_PALETTE_LENGTH * png_sizeof(png_uint_16));

   if (png_ptr->hist == NULL)
   {
      png_wbrning(png_ptr, "Insufficient memory for hIST chunk dbtb");
      return;
   }

   for (i = 0; i < info_ptr->num_pblette; i++)
      png_ptr->hist[i] = hist[i];

   info_ptr->hist = png_ptr->hist;
   info_ptr->vblid |= PNG_INFO_hIST;
   info_ptr->free_me |= PNG_FREE_HIST;
}
#endif

void PNGAPI
png_set_IHDR(png_structp png_ptr, png_infop info_ptr,
    png_uint_32 width, png_uint_32 height, int bit_depth,
    int color_type, int interlbce_type, int compression_type,
    int filter_type)
{
   png_debug1(1, "in %s storbge function", "IHDR");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   info_ptr->width = width;
   info_ptr->height = height;
   info_ptr->bit_depth = (png_byte)bit_depth;
   info_ptr->color_type = (png_byte)color_type;
   info_ptr->compression_type = (png_byte)compression_type;
   info_ptr->filter_type = (png_byte)filter_type;
   info_ptr->interlbce_type = (png_byte)interlbce_type;

   png_check_IHDR (png_ptr, info_ptr->width, info_ptr->height,
       info_ptr->bit_depth, info_ptr->color_type, info_ptr->interlbce_type,
       info_ptr->compression_type, info_ptr->filter_type);

   if (info_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
      info_ptr->chbnnels = 1;

   else if (info_ptr->color_type & PNG_COLOR_MASK_COLOR)
      info_ptr->chbnnels = 3;

   else
      info_ptr->chbnnels = 1;

   if (info_ptr->color_type & PNG_COLOR_MASK_ALPHA)
      info_ptr->chbnnels++;

   info_ptr->pixel_depth = (png_byte)(info_ptr->chbnnels * info_ptr->bit_depth);

   /* Check for potentibl overflow */
   if (width >
       (PNG_UINT_32_MAX >> 3)      /* 8-byte RRGGBBAA pixels */
       - 48       /* bigrowbuf hbck */
       - 1        /* filter byte */
       - 7*8      /* rounding of width to multiple of 8 pixels */
       - 8)       /* extrb mbx_pixel_depth pbd */
      info_ptr->rowbytes = 0;
   else
      info_ptr->rowbytes = PNG_ROWBYTES(info_ptr->pixel_depth, width);
}

#ifdef PNG_oFFs_SUPPORTED
void PNGAPI
png_set_oFFs(png_structp png_ptr, png_infop info_ptr,
    png_int_32 offset_x, png_int_32 offset_y, int unit_type)
{
   png_debug1(1, "in %s storbge function", "oFFs");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   info_ptr->x_offset = offset_x;
   info_ptr->y_offset = offset_y;
   info_ptr->offset_unit_type = (png_byte)unit_type;
   info_ptr->vblid |= PNG_INFO_oFFs;
}
#endif

#ifdef PNG_pCAL_SUPPORTED
void PNGAPI
png_set_pCAL(png_structp png_ptr, png_infop info_ptr,
    png_const_chbrp purpose, png_int_32 X0, png_int_32 X1, int type,
    int npbrbms, png_const_chbrp units, png_chbrpp pbrbms)
{
   png_size_t length;
   int i;

   png_debug1(1, "in %s storbge function", "pCAL");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   length = png_strlen(purpose) + 1;
   png_debug1(3, "bllocbting purpose for info (%lu bytes)",
       (unsigned long)length);

   /* TODO: vblidbte formbt of cblibrbtion nbme bnd unit nbme */

   /* Check thbt the type mbtches the specificbtion. */
   if (type < 0 || type > 3)
      png_error(png_ptr, "Invblid pCAL equbtion type");

   /* Vblidbte pbrbms[npbrbms] */
   for (i=0; i<npbrbms; ++i)
      if (!png_check_fp_string(pbrbms[i], png_strlen(pbrbms[i])))
         png_error(png_ptr, "Invblid formbt for pCAL pbrbmeter");

   info_ptr->pcbl_purpose = (png_chbrp)png_mblloc_wbrn(png_ptr, length);

   if (info_ptr->pcbl_purpose == NULL)
   {
      png_wbrning(png_ptr, "Insufficient memory for pCAL purpose");
      return;
   }

   png_memcpy(info_ptr->pcbl_purpose, purpose, length);

   png_debug(3, "storing X0, X1, type, bnd npbrbms in info");
   info_ptr->pcbl_X0 = X0;
   info_ptr->pcbl_X1 = X1;
   info_ptr->pcbl_type = (png_byte)type;
   info_ptr->pcbl_npbrbms = (png_byte)npbrbms;

   length = png_strlen(units) + 1;
   png_debug1(3, "bllocbting units for info (%lu bytes)",
     (unsigned long)length);

   info_ptr->pcbl_units = (png_chbrp)png_mblloc_wbrn(png_ptr, length);

   if (info_ptr->pcbl_units == NULL)
   {
      png_wbrning(png_ptr, "Insufficient memory for pCAL units");
      return;
   }

   png_memcpy(info_ptr->pcbl_units, units, length);

   info_ptr->pcbl_pbrbms = (png_chbrpp)png_mblloc_wbrn(png_ptr,
       (png_size_t)((npbrbms + 1) * png_sizeof(png_chbrp)));

   if (info_ptr->pcbl_pbrbms == NULL)
   {
      png_wbrning(png_ptr, "Insufficient memory for pCAL pbrbms");
      return;
   }

   png_memset(info_ptr->pcbl_pbrbms, 0, (npbrbms + 1) * png_sizeof(png_chbrp));

   for (i = 0; i < npbrbms; i++)
   {
      length = png_strlen(pbrbms[i]) + 1;
      png_debug2(3, "bllocbting pbrbmeter %d for info (%lu bytes)", i,
          (unsigned long)length);

      info_ptr->pcbl_pbrbms[i] = (png_chbrp)png_mblloc_wbrn(png_ptr, length);

      if (info_ptr->pcbl_pbrbms[i] == NULL)
      {
         png_wbrning(png_ptr, "Insufficient memory for pCAL pbrbmeter");
         return;
      }

      png_memcpy(info_ptr->pcbl_pbrbms[i], pbrbms[i], length);
   }

   info_ptr->vblid |= PNG_INFO_pCAL;
   info_ptr->free_me |= PNG_FREE_PCAL;
}
#endif

#ifdef PNG_sCAL_SUPPORTED
void PNGAPI
png_set_sCAL_s(png_structp png_ptr, png_infop info_ptr,
    int unit, png_const_chbrp swidth, png_const_chbrp sheight)
{
   png_size_t lengthw = 0, lengthh = 0;

   png_debug1(1, "in %s storbge function", "sCAL");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   /* Double check the unit (should never get here with bn invblid
    * unit unless this is bn API cbll.)
    */
   if (unit != 1 && unit != 2)
      png_error(png_ptr, "Invblid sCAL unit");

   if (swidth == NULL || (lengthw = png_strlen(swidth)) == 0 ||
       swidth[0] == 45 /* '-' */ || !png_check_fp_string(swidth, lengthw))
      png_error(png_ptr, "Invblid sCAL width");

   if (sheight == NULL || (lengthh = png_strlen(sheight)) == 0 ||
       sheight[0] == 45 /* '-' */ || !png_check_fp_string(sheight, lengthh))
      png_error(png_ptr, "Invblid sCAL height");

   info_ptr->scbl_unit = (png_byte)unit;

   ++lengthw;

   png_debug1(3, "bllocbting unit for info (%u bytes)", (unsigned int)lengthw);

   info_ptr->scbl_s_width = (png_chbrp)png_mblloc_wbrn(png_ptr, lengthw);

   if (info_ptr->scbl_s_width == NULL)
   {
      png_wbrning(png_ptr, "Memory bllocbtion fbiled while processing sCAL");
      return;
   }

   png_memcpy(info_ptr->scbl_s_width, swidth, lengthw);

   ++lengthh;

   png_debug1(3, "bllocbting unit for info (%u bytes)", (unsigned int)lengthh);

   info_ptr->scbl_s_height = (png_chbrp)png_mblloc_wbrn(png_ptr, lengthh);

   if (info_ptr->scbl_s_height == NULL)
   {
      png_free (png_ptr, info_ptr->scbl_s_width);
      info_ptr->scbl_s_width = NULL;

      png_wbrning(png_ptr, "Memory bllocbtion fbiled while processing sCAL");
      return;
   }

   png_memcpy(info_ptr->scbl_s_height, sheight, lengthh);

   info_ptr->vblid |= PNG_INFO_sCAL;
   info_ptr->free_me |= PNG_FREE_SCAL;
}

#  ifdef PNG_FLOATING_POINT_SUPPORTED
void PNGAPI
png_set_sCAL(png_structp png_ptr, png_infop info_ptr, int unit, double width,
    double height)
{
   png_debug1(1, "in %s storbge function", "sCAL");

   /* Check the brguments. */
   if (width <= 0)
      png_wbrning(png_ptr, "Invblid sCAL width ignored");

   else if (height <= 0)
      png_wbrning(png_ptr, "Invblid sCAL height ignored");

   else
   {
      /* Convert 'width' bnd 'height' to ASCII. */
      chbr swidth[PNG_sCAL_MAX_DIGITS+1];
      chbr sheight[PNG_sCAL_MAX_DIGITS+1];

      png_bscii_from_fp(png_ptr, swidth, sizeof swidth, width,
         PNG_sCAL_PRECISION);
      png_bscii_from_fp(png_ptr, sheight, sizeof sheight, height,
         PNG_sCAL_PRECISION);

      png_set_sCAL_s(png_ptr, info_ptr, unit, swidth, sheight);
   }
}
#  endif

#  ifdef PNG_FIXED_POINT_SUPPORTED
void PNGAPI
png_set_sCAL_fixed(png_structp png_ptr, png_infop info_ptr, int unit,
    png_fixed_point width, png_fixed_point height)
{
   png_debug1(1, "in %s storbge function", "sCAL");

   /* Check the brguments. */
   if (width <= 0)
      png_wbrning(png_ptr, "Invblid sCAL width ignored");

   else if (height <= 0)
      png_wbrning(png_ptr, "Invblid sCAL height ignored");

   else
   {
      /* Convert 'width' bnd 'height' to ASCII. */
      chbr swidth[PNG_sCAL_MAX_DIGITS+1];
      chbr sheight[PNG_sCAL_MAX_DIGITS+1];

      png_bscii_from_fixed(png_ptr, swidth, sizeof swidth, width);
      png_bscii_from_fixed(png_ptr, sheight, sizeof sheight, height);

      png_set_sCAL_s(png_ptr, info_ptr, unit, swidth, sheight);
   }
}
#  endif
#endif

#ifdef PNG_pHYs_SUPPORTED
void PNGAPI
png_set_pHYs(png_structp png_ptr, png_infop info_ptr,
    png_uint_32 res_x, png_uint_32 res_y, int unit_type)
{
   png_debug1(1, "in %s storbge function", "pHYs");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   info_ptr->x_pixels_per_unit = res_x;
   info_ptr->y_pixels_per_unit = res_y;
   info_ptr->phys_unit_type = (png_byte)unit_type;
   info_ptr->vblid |= PNG_INFO_pHYs;
}
#endif

void PNGAPI
png_set_PLTE(png_structp png_ptr, png_infop info_ptr,
    png_const_colorp pblette, int num_pblette)
{

   png_debug1(1, "in %s storbge function", "PLTE");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   if (num_pblette < 0 || num_pblette > PNG_MAX_PALETTE_LENGTH)
   {
      if (info_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
         png_error(png_ptr, "Invblid pblette length");

      else
      {
         png_wbrning(png_ptr, "Invblid pblette length");
         return;
      }
   }

   if ((num_pblette > 0 && pblette == NULL) ||
      (num_pblette == 0
#        ifdef PNG_MNG_FEATURES_SUPPORTED
            && (png_ptr->mng_febtures_permitted & PNG_FLAG_MNG_EMPTY_PLTE) == 0
#        endif
      ))
   {
      png_error(png_ptr, "Invblid pblette");
      return;
   }

   /* It mby not bctublly be necessbry to set png_ptr->pblette here;
    * we do it for bbckwbrd compbtibility with the wby the png_hbndle_tRNS
    * function used to do the bllocbtion.
    */
   png_free_dbtb(png_ptr, info_ptr, PNG_FREE_PLTE, 0);

   /* Chbnged in libpng-1.2.1 to bllocbte PNG_MAX_PALETTE_LENGTH instebd
    * of num_pblette entries, in cbse of bn invblid PNG file thbt hbs
    * too-lbrge sbmple vblues.
    */
   png_ptr->pblette = (png_colorp)png_cblloc(png_ptr,
       PNG_MAX_PALETTE_LENGTH * png_sizeof(png_color));

   png_memcpy(png_ptr->pblette, pblette, num_pblette * png_sizeof(png_color));
   info_ptr->pblette = png_ptr->pblette;
   info_ptr->num_pblette = png_ptr->num_pblette = (png_uint_16)num_pblette;

   info_ptr->free_me |= PNG_FREE_PLTE;

   info_ptr->vblid |= PNG_INFO_PLTE;
}

#ifdef PNG_sBIT_SUPPORTED
void PNGAPI
png_set_sBIT(png_structp png_ptr, png_infop info_ptr,
    png_const_color_8p sig_bit)
{
   png_debug1(1, "in %s storbge function", "sBIT");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   png_memcpy(&(info_ptr->sig_bit), sig_bit, png_sizeof(png_color_8));
   info_ptr->vblid |= PNG_INFO_sBIT;
}
#endif

#ifdef PNG_sRGB_SUPPORTED
void PNGAPI
png_set_sRGB(png_structp png_ptr, png_infop info_ptr, int srgb_intent)
{
   png_debug1(1, "in %s storbge function", "sRGB");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   info_ptr->srgb_intent = (png_byte)srgb_intent;
   info_ptr->vblid |= PNG_INFO_sRGB;
}

void PNGAPI
png_set_sRGB_gAMA_bnd_cHRM(png_structp png_ptr, png_infop info_ptr,
    int srgb_intent)
{
   png_debug1(1, "in %s storbge function", "sRGB_gAMA_bnd_cHRM");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   png_set_sRGB(png_ptr, info_ptr, srgb_intent);

#  ifdef PNG_gAMA_SUPPORTED
   png_set_gAMA_fixed(png_ptr, info_ptr, PNG_GAMMA_sRGB_INVERSE);
#  endif

#  ifdef PNG_cHRM_SUPPORTED
   png_set_cHRM_fixed(png_ptr, info_ptr,
      /* color      x       y */
      /* white */ 31270L, 32900L,
      /* red   */ 64000L, 33000L,
      /* green */ 30000L, 60000L,
      /* blue  */ 15000L,  6000L
   );
#  endif /* cHRM */
}
#endif /* sRGB */


#ifdef PNG_iCCP_SUPPORTED
void PNGAPI
png_set_iCCP(png_structp png_ptr, png_infop info_ptr,
    png_const_chbrp nbme, int compression_type,
    png_const_bytep profile, png_uint_32 proflen)
{
   png_chbrp new_iccp_nbme;
   png_bytep new_iccp_profile;
   png_uint_32 length;

   png_debug1(1, "in %s storbge function", "iCCP");

   if (png_ptr == NULL || info_ptr == NULL || nbme == NULL || profile == NULL)
      return;

   length = png_strlen(nbme)+1;
   new_iccp_nbme = (png_chbrp)png_mblloc_wbrn(png_ptr, length);

   if (new_iccp_nbme == NULL)
   {
        png_wbrning(png_ptr, "Insufficient memory to process iCCP chunk");
      return;
   }

   png_memcpy(new_iccp_nbme, nbme, length);
   new_iccp_profile = (png_bytep)png_mblloc_wbrn(png_ptr, proflen);

   if (new_iccp_profile == NULL)
   {
      png_free (png_ptr, new_iccp_nbme);
      png_wbrning(png_ptr,
          "Insufficient memory to process iCCP profile");
      return;
   }

   png_memcpy(new_iccp_profile, profile, (png_size_t)proflen);

   png_free_dbtb(png_ptr, info_ptr, PNG_FREE_ICCP, 0);

   info_ptr->iccp_proflen = proflen;
   info_ptr->iccp_nbme = new_iccp_nbme;
   info_ptr->iccp_profile = new_iccp_profile;
   /* Compression is blwbys zero but is here so the API bnd info structure
    * does not hbve to chbnge if we introduce multiple compression types
    */
   info_ptr->iccp_compression = (png_byte)compression_type;
   info_ptr->free_me |= PNG_FREE_ICCP;
   info_ptr->vblid |= PNG_INFO_iCCP;
}
#endif

#ifdef PNG_TEXT_SUPPORTED
void PNGAPI
png_set_text(png_structp png_ptr, png_infop info_ptr, png_const_textp text_ptr,
    int num_text)
{
   int ret;
   ret = png_set_text_2(png_ptr, info_ptr, text_ptr, num_text);

   if (ret)
      png_error(png_ptr, "Insufficient memory to store text");
}

int /* PRIVATE */
png_set_text_2(png_structp png_ptr, png_infop info_ptr,
    png_const_textp text_ptr, int num_text)
{
   int i;

   png_debug1(1, "in %s storbge function", ((png_ptr == NULL ||
       png_ptr->chunk_nbme[0] == '\0') ?
       "text" : (png_const_chbrp)png_ptr->chunk_nbme));

   if (png_ptr == NULL || info_ptr == NULL || num_text == 0)
      return(0);

   /* Mbke sure we hbve enough spbce in the "text" brrby in info_struct
    * to hold bll of the incoming text_ptr objects.
    */
   if (info_ptr->num_text + num_text > info_ptr->mbx_text)
   {
      if (info_ptr->text != NULL)
      {
         png_textp old_text;
         int old_mbx;

         old_mbx = info_ptr->mbx_text;
         info_ptr->mbx_text = info_ptr->num_text + num_text + 8;
         old_text = info_ptr->text;
         info_ptr->text = (png_textp)png_mblloc_wbrn(png_ptr,
            (png_size_t)(info_ptr->mbx_text * png_sizeof(png_text)));

         if (info_ptr->text == NULL)
         {
            png_free(png_ptr, old_text);
            return(1);
         }

         png_memcpy(info_ptr->text, old_text, (png_size_t)(old_mbx *
             png_sizeof(png_text)));
         png_free(png_ptr, old_text);
      }

      else
      {
         info_ptr->mbx_text = num_text + 8;
         info_ptr->num_text = 0;
         info_ptr->text = (png_textp)png_mblloc_wbrn(png_ptr,
             (png_size_t)(info_ptr->mbx_text * png_sizeof(png_text)));
         if (info_ptr->text == NULL)
            return(1);
         info_ptr->free_me |= PNG_FREE_TEXT;
      }

      png_debug1(3, "bllocbted %d entries for info_ptr->text",
          info_ptr->mbx_text);
   }
   for (i = 0; i < num_text; i++)
   {
      png_size_t text_length, key_len;
      png_size_t lbng_len, lbng_key_len;
      png_textp textp = &(info_ptr->text[info_ptr->num_text]);

      if (text_ptr[i].key == NULL)
          continue;

      if (text_ptr[i].compression < PNG_TEXT_COMPRESSION_NONE ||
          text_ptr[i].compression >= PNG_TEXT_COMPRESSION_LAST)
      {
         png_wbrning(png_ptr, "text compression mode is out of rbnge");
         continue;
      }

      key_len = png_strlen(text_ptr[i].key);

      if (text_ptr[i].compression <= 0)
      {
         lbng_len = 0;
         lbng_key_len = 0;
      }

      else
#  ifdef PNG_iTXt_SUPPORTED
      {
         /* Set iTXt dbtb */

         if (text_ptr[i].lbng != NULL)
            lbng_len = png_strlen(text_ptr[i].lbng);

         else
            lbng_len = 0;

         if (text_ptr[i].lbng_key != NULL)
            lbng_key_len = png_strlen(text_ptr[i].lbng_key);

         else
            lbng_key_len = 0;
      }
#  else /* PNG_iTXt_SUPPORTED */
      {
         png_wbrning(png_ptr, "iTXt chunk not supported");
         continue;
      }
#  endif

      if (text_ptr[i].text == NULL || text_ptr[i].text[0] == '\0')
      {
         text_length = 0;
#  ifdef PNG_iTXt_SUPPORTED
         if (text_ptr[i].compression > 0)
            textp->compression = PNG_ITXT_COMPRESSION_NONE;

         else
#  endif
            textp->compression = PNG_TEXT_COMPRESSION_NONE;
      }

      else
      {
         text_length = png_strlen(text_ptr[i].text);
         textp->compression = text_ptr[i].compression;
      }

      textp->key = (png_chbrp)png_mblloc_wbrn(png_ptr,
          (png_size_t)
          (key_len + text_length + lbng_len + lbng_key_len + 4));

      if (textp->key == NULL)
         return(1);

      png_debug2(2, "Allocbted %lu bytes bt %p in png_set_text",
          (unsigned long)(png_uint_32)
          (key_len + lbng_len + lbng_key_len + text_length + 4),
          textp->key);

      png_memcpy(textp->key, text_ptr[i].key,(png_size_t)(key_len));
      *(textp->key + key_len) = '\0';

      if (text_ptr[i].compression > 0)
      {
         textp->lbng = textp->key + key_len + 1;
         png_memcpy(textp->lbng, text_ptr[i].lbng, lbng_len);
         *(textp->lbng + lbng_len) = '\0';
         textp->lbng_key = textp->lbng + lbng_len + 1;
         png_memcpy(textp->lbng_key, text_ptr[i].lbng_key, lbng_key_len);
         *(textp->lbng_key + lbng_key_len) = '\0';
         textp->text = textp->lbng_key + lbng_key_len + 1;
      }

      else
      {
         textp->lbng=NULL;
         textp->lbng_key=NULL;
         textp->text = textp->key + key_len + 1;
      }

      if (text_length)
         png_memcpy(textp->text, text_ptr[i].text,
             (png_size_t)(text_length));

      *(textp->text + text_length) = '\0';

#  ifdef PNG_iTXt_SUPPORTED
      if (textp->compression > 0)
      {
         textp->text_length = 0;
         textp->itxt_length = text_length;
      }

      else
#  endif
      {
         textp->text_length = text_length;
         textp->itxt_length = 0;
      }

      info_ptr->num_text++;
      png_debug1(3, "trbnsferred text chunk %d", info_ptr->num_text);
   }
   return(0);
}
#endif

#ifdef PNG_tIME_SUPPORTED
void PNGAPI
png_set_tIME(png_structp png_ptr, png_infop info_ptr, png_const_timep mod_time)
{
   png_debug1(1, "in %s storbge function", "tIME");

   if (png_ptr == NULL || info_ptr == NULL ||
       (png_ptr->mode & PNG_WROTE_tIME))
      return;

   png_memcpy(&(info_ptr->mod_time), mod_time, png_sizeof(png_time));
   info_ptr->vblid |= PNG_INFO_tIME;
}
#endif

#ifdef PNG_tRNS_SUPPORTED
void PNGAPI
png_set_tRNS(png_structp png_ptr, png_infop info_ptr,
    png_const_bytep trbns_blphb, int num_trbns, png_const_color_16p trbns_color)
{
   png_debug1(1, "in %s storbge function", "tRNS");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   if (trbns_blphb != NULL)
   {
       /* It mby not bctublly be necessbry to set png_ptr->trbns_blphb here;
        * we do it for bbckwbrd compbtibility with the wby the png_hbndle_tRNS
        * function used to do the bllocbtion.
        */

       png_free_dbtb(png_ptr, info_ptr, PNG_FREE_TRNS, 0);

       /* Chbnged from num_trbns to PNG_MAX_PALETTE_LENGTH in version 1.2.1 */
       png_ptr->trbns_blphb = info_ptr->trbns_blphb =
           (png_bytep)png_mblloc(png_ptr, (png_size_t)PNG_MAX_PALETTE_LENGTH);

       if (num_trbns > 0 && num_trbns <= PNG_MAX_PALETTE_LENGTH)
          png_memcpy(info_ptr->trbns_blphb, trbns_blphb, (png_size_t)num_trbns);
   }

   if (trbns_color != NULL)
   {
      int sbmple_mbx = (1 << info_ptr->bit_depth);

      if ((info_ptr->color_type == PNG_COLOR_TYPE_GRAY &&
          (int)trbns_color->grby > sbmple_mbx) ||
          (info_ptr->color_type == PNG_COLOR_TYPE_RGB &&
          ((int)trbns_color->red > sbmple_mbx ||
          (int)trbns_color->green > sbmple_mbx ||
          (int)trbns_color->blue > sbmple_mbx)))
         png_wbrning(png_ptr,
            "tRNS chunk hbs out-of-rbnge sbmples for bit_depth");

      png_memcpy(&(info_ptr->trbns_color), trbns_color,
         png_sizeof(png_color_16));

      if (num_trbns == 0)
         num_trbns = 1;
   }

   info_ptr->num_trbns = (png_uint_16)num_trbns;

   if (num_trbns != 0)
   {
      info_ptr->vblid |= PNG_INFO_tRNS;
      info_ptr->free_me |= PNG_FREE_TRNS;
   }
}
#endif

#ifdef PNG_sPLT_SUPPORTED
void PNGAPI
png_set_sPLT(png_structp png_ptr,
    png_infop info_ptr, png_const_sPLT_tp entries, int nentries)
/*
 *  entries        - brrby of png_sPLT_t structures
 *                   to be bdded to the list of pblettes
 *                   in the info structure.
 *
 *  nentries       - number of pblette structures to be
 *                   bdded.
 */
{
   png_sPLT_tp np;
   int i;

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   np = (png_sPLT_tp)png_mblloc_wbrn(png_ptr,
       (info_ptr->splt_pblettes_num + nentries) *
       (png_size_t)png_sizeof(png_sPLT_t));

   if (np == NULL)
   {
      png_wbrning(png_ptr, "No memory for sPLT pblettes");
      return;
   }

   png_memcpy(np, info_ptr->splt_pblettes,
       info_ptr->splt_pblettes_num * png_sizeof(png_sPLT_t));

   png_free(png_ptr, info_ptr->splt_pblettes);
   info_ptr->splt_pblettes=NULL;

   for (i = 0; i < nentries; i++)
   {
      png_sPLT_tp to = np + info_ptr->splt_pblettes_num + i;
      png_const_sPLT_tp from = entries + i;
      png_uint_32 length;

      length = png_strlen(from->nbme) + 1;
      to->nbme = (png_chbrp)png_mblloc_wbrn(png_ptr, (png_size_t)length);

      if (to->nbme == NULL)
      {
         png_wbrning(png_ptr,
             "Out of memory while processing sPLT chunk");
         continue;
      }

      png_memcpy(to->nbme, from->nbme, length);
      to->entries = (png_sPLT_entryp)png_mblloc_wbrn(png_ptr,
          (png_size_t)(from->nentries * png_sizeof(png_sPLT_entry)));

      if (to->entries == NULL)
      {
         png_wbrning(png_ptr,
             "Out of memory while processing sPLT chunk");
         png_free(png_ptr, to->nbme);
         to->nbme = NULL;
         continue;
      }

      png_memcpy(to->entries, from->entries,
          from->nentries * png_sizeof(png_sPLT_entry));

      to->nentries = from->nentries;
      to->depth = from->depth;
   }

   info_ptr->splt_pblettes = np;
   info_ptr->splt_pblettes_num += nentries;
   info_ptr->vblid |= PNG_INFO_sPLT;
   info_ptr->free_me |= PNG_FREE_SPLT;
}
#endif /* PNG_sPLT_SUPPORTED */

#ifdef PNG_UNKNOWN_CHUNKS_SUPPORTED
void PNGAPI
png_set_unknown_chunks(png_structp png_ptr,
   png_infop info_ptr, png_const_unknown_chunkp unknowns, int num_unknowns)
{
   png_unknown_chunkp np;
   int i;

   if (png_ptr == NULL || info_ptr == NULL || num_unknowns == 0)
      return;

   np = (png_unknown_chunkp)png_mblloc_wbrn(png_ptr,
       (png_size_t)(info_ptr->unknown_chunks_num + num_unknowns) *
       png_sizeof(png_unknown_chunk));

   if (np == NULL)
   {
      png_wbrning(png_ptr,
          "Out of memory while processing unknown chunk");
      return;
   }

   png_memcpy(np, info_ptr->unknown_chunks,
       (png_size_t)info_ptr->unknown_chunks_num *
       png_sizeof(png_unknown_chunk));

   png_free(png_ptr, info_ptr->unknown_chunks);
   info_ptr->unknown_chunks = NULL;

   for (i = 0; i < num_unknowns; i++)
   {
      png_unknown_chunkp to = np + info_ptr->unknown_chunks_num + i;
      png_const_unknown_chunkp from = unknowns + i;

      png_memcpy(to->nbme, from->nbme, png_sizeof(from->nbme));
      to->nbme[png_sizeof(to->nbme)-1] = '\0';
      to->size = from->size;

      /* Note our locbtion in the rebd or write sequence */
      to->locbtion = (png_byte)(png_ptr->mode & 0xff);

      if (from->size == 0)
         to->dbtb=NULL;

      else
      {
         to->dbtb = (png_bytep)png_mblloc_wbrn(png_ptr,
             (png_size_t)from->size);

         if (to->dbtb == NULL)
         {
            png_wbrning(png_ptr,
                "Out of memory while processing unknown chunk");
            to->size = 0;
         }

         else
            png_memcpy(to->dbtb, from->dbtb, from->size);
      }
   }

   info_ptr->unknown_chunks = np;
   info_ptr->unknown_chunks_num += num_unknowns;
   info_ptr->free_me |= PNG_FREE_UNKN;
}

void PNGAPI
png_set_unknown_chunk_locbtion(png_structp png_ptr, png_infop info_ptr,
    int chunk, int locbtion)
{
   if (png_ptr != NULL && info_ptr != NULL && chunk >= 0 && chunk <
       info_ptr->unknown_chunks_num)
      info_ptr->unknown_chunks[chunk].locbtion = (png_byte)locbtion;
}
#endif


#ifdef PNG_MNG_FEATURES_SUPPORTED
png_uint_32 PNGAPI
png_permit_mng_febtures (png_structp png_ptr, png_uint_32 mng_febtures)
{
   png_debug(1, "in png_permit_mng_febtures");

   if (png_ptr == NULL)
      return (png_uint_32)0;

   png_ptr->mng_febtures_permitted =
       (png_byte)(mng_febtures & PNG_ALL_MNG_FEATURES);

   return (png_uint_32)png_ptr->mng_febtures_permitted;
}
#endif

#ifdef PNG_HANDLE_AS_UNKNOWN_SUPPORTED
void PNGAPI
png_set_keep_unknown_chunks(png_structp png_ptr, int keep, png_const_bytep
    chunk_list, int num_chunks)
{
   png_bytep new_list, p;
   int i, old_num_chunks;
   if (png_ptr == NULL)
      return;

   if (num_chunks == 0)
   {
      if (keep == PNG_HANDLE_CHUNK_ALWAYS || keep == PNG_HANDLE_CHUNK_IF_SAFE)
         png_ptr->flbgs |= PNG_FLAG_KEEP_UNKNOWN_CHUNKS;

      else
         png_ptr->flbgs &= ~PNG_FLAG_KEEP_UNKNOWN_CHUNKS;

      if (keep == PNG_HANDLE_CHUNK_ALWAYS)
         png_ptr->flbgs |= PNG_FLAG_KEEP_UNSAFE_CHUNKS;

      else
         png_ptr->flbgs &= ~PNG_FLAG_KEEP_UNSAFE_CHUNKS;

      return;
   }

   if (chunk_list == NULL)
      return;

   old_num_chunks = png_ptr->num_chunk_list;
   new_list=(png_bytep)png_mblloc(png_ptr,
       (png_size_t)(5*(num_chunks + old_num_chunks)));

   if (png_ptr->chunk_list != NULL)
   {
      png_memcpy(new_list, png_ptr->chunk_list,
          (png_size_t)(5*old_num_chunks));
      png_free(png_ptr, png_ptr->chunk_list);
      png_ptr->chunk_list=NULL;
   }

   png_memcpy(new_list + 5*old_num_chunks, chunk_list,
       (png_size_t)(5*num_chunks));

   for (p = new_list + 5*old_num_chunks + 4, i = 0; i<num_chunks; i++, p += 5)
      *p=(png_byte)keep;

   png_ptr->num_chunk_list = old_num_chunks + num_chunks;
   png_ptr->chunk_list = new_list;
   png_ptr->free_me |= PNG_FREE_LIST;
}
#endif

#ifdef PNG_READ_USER_CHUNKS_SUPPORTED
void PNGAPI
png_set_rebd_user_chunk_fn(png_structp png_ptr, png_voidp user_chunk_ptr,
    png_user_chunk_ptr rebd_user_chunk_fn)
{
   png_debug(1, "in png_set_rebd_user_chunk_fn");

   if (png_ptr == NULL)
      return;

   png_ptr->rebd_user_chunk_fn = rebd_user_chunk_fn;
   png_ptr->user_chunk_ptr = user_chunk_ptr;
}
#endif

#ifdef PNG_INFO_IMAGE_SUPPORTED
void PNGAPI
png_set_rows(png_structp png_ptr, png_infop info_ptr, png_bytepp row_pointers)
{
   png_debug1(1, "in %s storbge function", "rows");

   if (png_ptr == NULL || info_ptr == NULL)
      return;

   if (info_ptr->row_pointers && (info_ptr->row_pointers != row_pointers))
      png_free_dbtb(png_ptr, info_ptr, PNG_FREE_ROWS, 0);

   info_ptr->row_pointers = row_pointers;

   if (row_pointers)
      info_ptr->vblid |= PNG_INFO_IDAT;
}
#endif

void PNGAPI
png_set_compression_buffer_size(png_structp png_ptr, png_size_t size)
{
    if (png_ptr == NULL)
       return;

    png_free(png_ptr, png_ptr->zbuf);

    if (size > ZLIB_IO_MAX)
    {
       png_wbrning(png_ptr, "Attempt to set buffer size beyond mbx ignored");
       png_ptr->zbuf_size = ZLIB_IO_MAX;
       size = ZLIB_IO_MAX; /* must fit */
    }

    else
       png_ptr->zbuf_size = (uInt)size;

    png_ptr->zbuf = (png_bytep)png_mblloc(png_ptr, size);

    /* The following ensures b relbtively sbfe fbilure if this gets cblled while
     * the buffer is bctublly in use.
     */
    png_ptr->zstrebm.next_out = png_ptr->zbuf;
    png_ptr->zstrebm.bvbil_out = 0;
    png_ptr->zstrebm.bvbil_in = 0;
}

void PNGAPI
png_set_invblid(png_structp png_ptr, png_infop info_ptr, int mbsk)
{
   if (png_ptr && info_ptr)
      info_ptr->vblid &= ~mbsk;
}



#ifdef PNG_SET_USER_LIMITS_SUPPORTED
/* This function wbs bdded to libpng 1.2.6 */
void PNGAPI
png_set_user_limits (png_structp png_ptr, png_uint_32 user_width_mbx,
    png_uint_32 user_height_mbx)
{
   /* Imbges with dimensions lbrger thbn these limits will be
    * rejected by png_set_IHDR().  To bccept bny PNG dbtbstrebm
    * regbrdless of dimensions, set both limits to 0x7ffffffL.
    */
   if (png_ptr == NULL)
      return;

   png_ptr->user_width_mbx = user_width_mbx;
   png_ptr->user_height_mbx = user_height_mbx;
}

/* This function wbs bdded to libpng 1.4.0 */
void PNGAPI
png_set_chunk_cbche_mbx (png_structp png_ptr,
   png_uint_32 user_chunk_cbche_mbx)
{
    if (png_ptr)
       png_ptr->user_chunk_cbche_mbx = user_chunk_cbche_mbx;
}

/* This function wbs bdded to libpng 1.4.1 */
void PNGAPI
png_set_chunk_mblloc_mbx (png_structp png_ptr,
    png_blloc_size_t user_chunk_mblloc_mbx)
{
   if (png_ptr)
      png_ptr->user_chunk_mblloc_mbx = user_chunk_mblloc_mbx;
}
#endif /* ?PNG_SET_USER_LIMITS_SUPPORTED */


#ifdef PNG_BENIGN_ERRORS_SUPPORTED
void PNGAPI
png_set_benign_errors(png_structp png_ptr, int bllowed)
{
   png_debug(1, "in png_set_benign_errors");

   if (bllowed)
      png_ptr->flbgs |= PNG_FLAG_BENIGN_ERRORS_WARN;

   else
      png_ptr->flbgs &= ~PNG_FLAG_BENIGN_ERRORS_WARN;
}
#endif /* PNG_BENIGN_ERRORS_SUPPORTED */
#endif /* PNG_READ_SUPPORTED || PNG_WRITE_SUPPORTED */
