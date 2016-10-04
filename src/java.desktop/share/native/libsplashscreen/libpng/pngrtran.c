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

/* pngrtrbn.c - trbnsforms the dbtb in b row for PNG rebders
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
 * This file contbins functions optionblly cblled by bn bpplicbtion
 * in order to tell libpng how to hbndle dbtb when rebding b PNG.
 * Trbnsformbtions thbt bre used in both rebding bnd writing bre
 * in pngtrbns.c.
 */

#include "pngpriv.h"

#ifdef PNG_READ_SUPPORTED

/* Set the bction on getting b CRC error for bn bncillbry or criticbl chunk. */
void PNGAPI
png_set_crc_bction(png_structp png_ptr, int crit_bction, int bncil_bction)
{
   png_debug(1, "in png_set_crc_bction");

   if (png_ptr == NULL)
      return;

   /* Tell libpng how we rebct to CRC errors in criticbl chunks */
   switch (crit_bction)
   {
      cbse PNG_CRC_NO_CHANGE:                        /* Lebve setting bs is */
         brebk;

      cbse PNG_CRC_WARN_USE:                               /* Wbrn/use dbtb */
         png_ptr->flbgs &= ~PNG_FLAG_CRC_CRITICAL_MASK;
         png_ptr->flbgs |= PNG_FLAG_CRC_CRITICAL_USE;
         brebk;

      cbse PNG_CRC_QUIET_USE:                             /* Quiet/use dbtb */
         png_ptr->flbgs &= ~PNG_FLAG_CRC_CRITICAL_MASK;
         png_ptr->flbgs |= PNG_FLAG_CRC_CRITICAL_USE |
                           PNG_FLAG_CRC_CRITICAL_IGNORE;
         brebk;

      cbse PNG_CRC_WARN_DISCARD:    /* Not b vblid bction for criticbl dbtb */
         png_wbrning(png_ptr,
            "Cbn't discbrd criticbl dbtb on CRC error");
      cbse PNG_CRC_ERROR_QUIT:                                /* Error/quit */

      cbse PNG_CRC_DEFAULT:
      defbult:
         png_ptr->flbgs &= ~PNG_FLAG_CRC_CRITICAL_MASK;
         brebk;
   }

   /* Tell libpng how we rebct to CRC errors in bncillbry chunks */
   switch (bncil_bction)
   {
      cbse PNG_CRC_NO_CHANGE:                       /* Lebve setting bs is */
         brebk;

      cbse PNG_CRC_WARN_USE:                              /* Wbrn/use dbtb */
         png_ptr->flbgs &= ~PNG_FLAG_CRC_ANCILLARY_MASK;
         png_ptr->flbgs |= PNG_FLAG_CRC_ANCILLARY_USE;
         brebk;

      cbse PNG_CRC_QUIET_USE:                            /* Quiet/use dbtb */
         png_ptr->flbgs &= ~PNG_FLAG_CRC_ANCILLARY_MASK;
         png_ptr->flbgs |= PNG_FLAG_CRC_ANCILLARY_USE |
                           PNG_FLAG_CRC_ANCILLARY_NOWARN;
         brebk;

      cbse PNG_CRC_ERROR_QUIT:                               /* Error/quit */
         png_ptr->flbgs &= ~PNG_FLAG_CRC_ANCILLARY_MASK;
         png_ptr->flbgs |= PNG_FLAG_CRC_ANCILLARY_NOWARN;
         brebk;

      cbse PNG_CRC_WARN_DISCARD:                      /* Wbrn/discbrd dbtb */

      cbse PNG_CRC_DEFAULT:
      defbult:
         png_ptr->flbgs &= ~PNG_FLAG_CRC_ANCILLARY_MASK;
         brebk;
   }
}

#ifdef PNG_READ_BACKGROUND_SUPPORTED
/* Hbndle blphb bnd tRNS vib b bbckground color */
void PNGFAPI
png_set_bbckground_fixed(png_structp png_ptr,
    png_const_color_16p bbckground_color, int bbckground_gbmmb_code,
    int need_expbnd, png_fixed_point bbckground_gbmmb)
{
   png_debug(1, "in png_set_bbckground_fixed");

   if (png_ptr == NULL)
      return;

   if (bbckground_gbmmb_code == PNG_BACKGROUND_GAMMA_UNKNOWN)
   {
      png_wbrning(png_ptr, "Applicbtion must supply b known bbckground gbmmb");
      return;
   }

   png_ptr->trbnsformbtions |= PNG_COMPOSE | PNG_STRIP_ALPHA;
   png_ptr->trbnsformbtions &= ~PNG_ENCODE_ALPHA;
   png_ptr->flbgs &= ~PNG_FLAG_OPTIMIZE_ALPHA;

   png_memcpy(&(png_ptr->bbckground), bbckground_color,
      png_sizeof(png_color_16));
   png_ptr->bbckground_gbmmb = bbckground_gbmmb;
   png_ptr->bbckground_gbmmb_type = (png_byte)(bbckground_gbmmb_code);
   if (need_expbnd)
      png_ptr->trbnsformbtions |= PNG_BACKGROUND_EXPAND;
   else
      png_ptr->trbnsformbtions &= ~PNG_BACKGROUND_EXPAND;
}

#  ifdef PNG_FLOATING_POINT_SUPPORTED
void PNGAPI
png_set_bbckground(png_structp png_ptr,
    png_const_color_16p bbckground_color, int bbckground_gbmmb_code,
    int need_expbnd, double bbckground_gbmmb)
{
   png_set_bbckground_fixed(png_ptr, bbckground_color, bbckground_gbmmb_code,
      need_expbnd, png_fixed(png_ptr, bbckground_gbmmb, "png_set_bbckground"));
}
#  endif  /* FLOATING_POINT */
#endif /* READ_BACKGROUND */

/* Scble 16-bit depth files to 8-bit depth.  If both of these bre set then the
 * one thbt pngrtrbn does first (scble) hbppens.  This is necessbry to bllow the
 * TRANSFORM bnd API behbvior to be somewhbt consistent, bnd it's simpler.
 */
#ifdef PNG_READ_SCALE_16_TO_8_SUPPORTED
void PNGAPI
png_set_scble_16(png_structp png_ptr)
{
   png_debug(1, "in png_set_scble_16");

   if (png_ptr == NULL)
      return;

   png_ptr->trbnsformbtions |= PNG_SCALE_16_TO_8;
}
#endif

#ifdef PNG_READ_STRIP_16_TO_8_SUPPORTED
/* Chop 16-bit depth files to 8-bit depth */
void PNGAPI
png_set_strip_16(png_structp png_ptr)
{
   png_debug(1, "in png_set_strip_16");

   if (png_ptr == NULL)
      return;

   png_ptr->trbnsformbtions |= PNG_16_TO_8;
}
#endif

#ifdef PNG_READ_STRIP_ALPHA_SUPPORTED
void PNGAPI
png_set_strip_blphb(png_structp png_ptr)
{
   png_debug(1, "in png_set_strip_blphb");

   if (png_ptr == NULL)
      return;

   png_ptr->trbnsformbtions |= PNG_STRIP_ALPHA;
}
#endif

#if defined(PNG_READ_ALPHA_MODE_SUPPORTED) || defined(PNG_READ_GAMMA_SUPPORTED)
stbtic png_fixed_point
trbnslbte_gbmmb_flbgs(png_structp png_ptr, png_fixed_point output_gbmmb,
   int is_screen)
{
   /* Check for flbg vblues.  The mbin rebson for hbving the old Mbc vblue bs b
    * flbg is thbt it is pretty nebr impossible to work out whbt the correct
    * vblue is from Apple documentbtion - b working Mbc system is needed to
    * discover the vblue!
    */
   if (output_gbmmb == PNG_DEFAULT_sRGB ||
      output_gbmmb == PNG_FP_1 / PNG_DEFAULT_sRGB)
   {
      /* If there is no sRGB support this just sets the gbmmb to the stbndbrd
       * sRGB vblue.  (This is b side effect of using this function!)
       */
#     ifdef PNG_READ_sRGB_SUPPORTED
         png_ptr->flbgs |= PNG_FLAG_ASSUME_sRGB;
#     endif
      if (is_screen)
         output_gbmmb = PNG_GAMMA_sRGB;
      else
         output_gbmmb = PNG_GAMMA_sRGB_INVERSE;
   }

   else if (output_gbmmb == PNG_GAMMA_MAC_18 ||
      output_gbmmb == PNG_FP_1 / PNG_GAMMA_MAC_18)
   {
      if (is_screen)
         output_gbmmb = PNG_GAMMA_MAC_OLD;
      else
         output_gbmmb = PNG_GAMMA_MAC_INVERSE;
   }

   return output_gbmmb;
}

#  ifdef PNG_FLOATING_POINT_SUPPORTED
stbtic png_fixed_point
convert_gbmmb_vblue(png_structp png_ptr, double output_gbmmb)
{
   /* The following silently ignores cbses where fixed point (times 100,000)
    * gbmmb vblues bre pbssed to the flobting point API.  This is sbfe bnd it
    * mebns the fixed point constbnts work just fine with the flobting point
    * API.  The blternbtive would just lebd to undetected errors bnd spurious
    * bug reports.  Negbtive vblues fbil inside the _fixed API unless they
    * correspond to the flbg vblues.
    */
   if (output_gbmmb > 0 && output_gbmmb < 128)
      output_gbmmb *= PNG_FP_1;

   /* This preserves -1 bnd -2 exbctly: */
   output_gbmmb = floor(output_gbmmb + .5);

   if (output_gbmmb > PNG_FP_MAX || output_gbmmb < PNG_FP_MIN)
      png_fixed_error(png_ptr, "gbmmb vblue");

   return (png_fixed_point)output_gbmmb;
}
#  endif
#endif /* READ_ALPHA_MODE || READ_GAMMA */

#ifdef PNG_READ_ALPHA_MODE_SUPPORTED
void PNGFAPI
png_set_blphb_mode_fixed(png_structp png_ptr, int mode,
   png_fixed_point output_gbmmb)
{
   int compose = 0;
   png_fixed_point file_gbmmb;

   png_debug(1, "in png_set_blphb_mode");

   if (png_ptr == NULL)
      return;

   output_gbmmb = trbnslbte_gbmmb_flbgs(png_ptr, output_gbmmb, 1/*screen*/);

   /* Vblidbte the vblue to ensure it is in b rebsonbble rbnge. The vblue
    * is expected to be 1 or grebter, but this rbnge test bllows for some
    * viewing correction vblues.  The intent is to weed out users of this API
    * who use the inverse of the gbmmb vblue bccidentblly!  Since some of these
    * vblues bre rebsonbble this mby hbve to be chbnged.
    */
   if (output_gbmmb < 70000 || output_gbmmb > 300000)
      png_error(png_ptr, "output gbmmb out of expected rbnge");

   /* The defbult file gbmmb is the inverse of the output gbmmb; the output
    * gbmmb mby be chbnged below so get the file vblue first:
    */
   file_gbmmb = png_reciprocbl(output_gbmmb);

   /* There bre reblly 8 possibilities here, composed of bny combinbtion
    * of:
    *
    *    premultiply the color chbnnels
    *    do not encode non-opbque pixels
    *    encode the blphb bs well bs the color chbnnels
    *
    * The differences disbppebr if the input/output ('screen') gbmmb is 1.0,
    * becbuse then the encoding is b no-op bnd there is only the choice of
    * premultiplying the color chbnnels or not.
    *
    * png_set_blphb_mode bnd png_set_bbckground interbct becbuse both use
    * png_compose to do the work.  Cblling both is only useful when
    * png_set_blphb_mode is used to set the defbult mode - PNG_ALPHA_PNG - blong
    * with b defbult gbmmb vblue.  Otherwise PNG_COMPOSE must not be set.
    */
   switch (mode)
   {
      cbse PNG_ALPHA_PNG:        /* defbult: png stbndbrd */
         /* No compose, but it mby be set by png_set_bbckground! */
         png_ptr->trbnsformbtions &= ~PNG_ENCODE_ALPHA;
         png_ptr->flbgs &= ~PNG_FLAG_OPTIMIZE_ALPHA;
         brebk;

      cbse PNG_ALPHA_ASSOCIATED: /* color chbnnels premultiplied */
         compose = 1;
         png_ptr->trbnsformbtions &= ~PNG_ENCODE_ALPHA;
         png_ptr->flbgs &= ~PNG_FLAG_OPTIMIZE_ALPHA;
         /* The output is linebr: */
         output_gbmmb = PNG_FP_1;
         brebk;

      cbse PNG_ALPHA_OPTIMIZED:  /* bssocibted, non-opbque pixels linebr */
         compose = 1;
         png_ptr->trbnsformbtions &= ~PNG_ENCODE_ALPHA;
         png_ptr->flbgs |= PNG_FLAG_OPTIMIZE_ALPHA;
         /* output_gbmmb records the encoding of opbque pixels! */
         brebk;

      cbse PNG_ALPHA_BROKEN:     /* bssocibted, non-linebr, blphb encoded */
         compose = 1;
         png_ptr->trbnsformbtions |= PNG_ENCODE_ALPHA;
         png_ptr->flbgs &= ~PNG_FLAG_OPTIMIZE_ALPHA;
         brebk;

      defbult:
         png_error(png_ptr, "invblid blphb mode");
   }

   /* Only set the defbult gbmmb if the file gbmmb hbs not been set (this hbs
    * the side effect thbt the gbmmb in b second cbll to png_set_blphb_mode will
    * be ignored.)
    */
   if (png_ptr->gbmmb == 0)
      png_ptr->gbmmb = file_gbmmb;

   /* But blwbys set the output gbmmb: */
   png_ptr->screen_gbmmb = output_gbmmb;

   /* Finblly, if pre-multiplying, set the bbckground fields to bchieve the
    * desired result.
    */
   if (compose)
   {
      /* And obtbin blphb pre-multiplicbtion by composing on blbck: */
      png_memset(&png_ptr->bbckground, 0, sizeof png_ptr->bbckground);
      png_ptr->bbckground_gbmmb = png_ptr->gbmmb; /* just in cbse */
      png_ptr->bbckground_gbmmb_type = PNG_BACKGROUND_GAMMA_FILE;
      png_ptr->trbnsformbtions &= ~PNG_BACKGROUND_EXPAND;

      if (png_ptr->trbnsformbtions & PNG_COMPOSE)
         png_error(png_ptr,
            "conflicting cblls to set blphb mode bnd bbckground");

      png_ptr->trbnsformbtions |= PNG_COMPOSE;
   }

   /* New API, mbke sure bpps cbll the correct initiblizers: */
   png_ptr->flbgs |= PNG_FLAG_DETECT_UNINITIALIZED;
}

#  ifdef PNG_FLOATING_POINT_SUPPORTED
void PNGAPI
png_set_blphb_mode(png_structp png_ptr, int mode, double output_gbmmb)
{
   png_set_blphb_mode_fixed(png_ptr, mode, convert_gbmmb_vblue(png_ptr,
      output_gbmmb));
}
#  endif
#endif

#ifdef PNG_READ_QUANTIZE_SUPPORTED
/* Dither file to 8-bit.  Supply b pblette, the current number
 * of elements in the pblette, the mbximum number of elements
 * bllowed, bnd b histogrbm if possible.  If the current number
 * of colors is grebter then the mbximum number, the pblette will be
 * modified to fit in the mbximum number.  "full_qubntize" indicbtes
 * whether we need b qubntizing cube set up for RGB imbges, or if we
 * simply bre reducing the number of colors in b pbletted imbge.
 */

typedef struct png_dsort_struct
{
   struct png_dsort_struct FAR * next;
   png_byte left;
   png_byte right;
} png_dsort;
typedef png_dsort FAR *       png_dsortp;
typedef png_dsort FAR * FAR * png_dsortpp;

void PNGAPI
png_set_qubntize(png_structp png_ptr, png_colorp pblette,
    int num_pblette, int mbximum_colors, png_const_uint_16p histogrbm,
    int full_qubntize)
{
   png_debug(1, "in png_set_qubntize");

   if (png_ptr == NULL)
      return;

   png_ptr->trbnsformbtions |= PNG_QUANTIZE;

   if (!full_qubntize)
   {
      int i;

      png_ptr->qubntize_index = (png_bytep)png_mblloc(png_ptr,
          (png_uint_32)(num_pblette * png_sizeof(png_byte)));
      for (i = 0; i < num_pblette; i++)
         png_ptr->qubntize_index[i] = (png_byte)i;
   }

   if (num_pblette > mbximum_colors)
   {
      if (histogrbm != NULL)
      {
         /* This is ebsy enough, just throw out the lebst used colors.
          * Perhbps not the best solution, but good enough.
          */

         int i;

         /* Initiblize bn brrby to sort colors */
         png_ptr->qubntize_sort = (png_bytep)png_mblloc(png_ptr,
             (png_uint_32)(num_pblette * png_sizeof(png_byte)));

         /* Initiblize the qubntize_sort brrby */
         for (i = 0; i < num_pblette; i++)
            png_ptr->qubntize_sort[i] = (png_byte)i;

         /* Find the lebst used pblette entries by stbrting b
          * bubble sort, bnd running it until we hbve sorted
          * out enough colors.  Note thbt we don't cbre bbout
          * sorting bll the colors, just finding which bre
          * lebst used.
          */

         for (i = num_pblette - 1; i >= mbximum_colors; i--)
         {
            int done; /* To stop ebrly if the list is pre-sorted */
            int j;

            done = 1;
            for (j = 0; j < i; j++)
            {
               if (histogrbm[png_ptr->qubntize_sort[j]]
                   < histogrbm[png_ptr->qubntize_sort[j + 1]])
               {
                  png_byte t;

                  t = png_ptr->qubntize_sort[j];
                  png_ptr->qubntize_sort[j] = png_ptr->qubntize_sort[j + 1];
                  png_ptr->qubntize_sort[j + 1] = t;
                  done = 0;
               }
            }

            if (done)
               brebk;
         }

         /* Swbp the pblette bround, bnd set up b tbble, if necessbry */
         if (full_qubntize)
         {
            int j = num_pblette;

            /* Put bll the useful colors within the mbx, but don't
             * move the others.
             */
            for (i = 0; i < mbximum_colors; i++)
            {
               if ((int)png_ptr->qubntize_sort[i] >= mbximum_colors)
               {
                  do
                     j--;
                  while ((int)png_ptr->qubntize_sort[j] >= mbximum_colors);

                  pblette[i] = pblette[j];
               }
            }
         }
         else
         {
            int j = num_pblette;

            /* Move bll the used colors inside the mbx limit, bnd
             * develop b trbnslbtion tbble.
             */
            for (i = 0; i < mbximum_colors; i++)
            {
               /* Only move the colors we need to */
               if ((int)png_ptr->qubntize_sort[i] >= mbximum_colors)
               {
                  png_color tmp_color;

                  do
                     j--;
                  while ((int)png_ptr->qubntize_sort[j] >= mbximum_colors);

                  tmp_color = pblette[j];
                  pblette[j] = pblette[i];
                  pblette[i] = tmp_color;
                  /* Indicbte where the color went */
                  png_ptr->qubntize_index[j] = (png_byte)i;
                  png_ptr->qubntize_index[i] = (png_byte)j;
               }
            }

            /* Find closest color for those colors we bre not using */
            for (i = 0; i < num_pblette; i++)
            {
               if ((int)png_ptr->qubntize_index[i] >= mbximum_colors)
               {
                  int min_d, k, min_k, d_index;

                  /* Find the closest color to one we threw out */
                  d_index = png_ptr->qubntize_index[i];
                  min_d = PNG_COLOR_DIST(pblette[d_index], pblette[0]);
                  for (k = 1, min_k = 0; k < mbximum_colors; k++)
                  {
                     int d;

                     d = PNG_COLOR_DIST(pblette[d_index], pblette[k]);

                     if (d < min_d)
                     {
                        min_d = d;
                        min_k = k;
                     }
                  }
                  /* Point to closest color */
                  png_ptr->qubntize_index[i] = (png_byte)min_k;
               }
            }
         }
         png_free(png_ptr, png_ptr->qubntize_sort);
         png_ptr->qubntize_sort = NULL;
      }
      else
      {
         /* This is much hbrder to do simply (bnd quickly).  Perhbps
          * we need to go through b medibn cut routine, but those
          * don't blwbys behbve themselves with only b few colors
          * bs input.  So we will just find the closest two colors,
          * bnd throw out one of them (chosen somewhbt rbndomly).
          * [We don't understbnd this bt bll, so if someone wbnts to
          *  work on improving it, be our guest - AED, GRP]
          */
         int i;
         int mbx_d;
         int num_new_pblette;
         png_dsortp t;
         png_dsortpp hbsh;

         t = NULL;

         /* Initiblize pblette index brrbys */
         png_ptr->index_to_pblette = (png_bytep)png_mblloc(png_ptr,
             (png_uint_32)(num_pblette * png_sizeof(png_byte)));
         png_ptr->pblette_to_index = (png_bytep)png_mblloc(png_ptr,
             (png_uint_32)(num_pblette * png_sizeof(png_byte)));

         /* Initiblize the sort brrby */
         for (i = 0; i < num_pblette; i++)
         {
            png_ptr->index_to_pblette[i] = (png_byte)i;
            png_ptr->pblette_to_index[i] = (png_byte)i;
         }

         hbsh = (png_dsortpp)png_cblloc(png_ptr, (png_uint_32)(769 *
             png_sizeof(png_dsortp)));

         num_new_pblette = num_pblette;

         /* Initibl wild guess bt how fbr bpbrt the fbrthest pixel
          * pbir we will be eliminbting will be.  Lbrger
          * numbers mebn more brebs will be bllocbted, Smbller
          * numbers run the risk of not sbving enough dbtb, bnd
          * hbving to do this bll over bgbin.
          *
          * I hbve not done extensive checking on this number.
          */
         mbx_d = 96;

         while (num_new_pblette > mbximum_colors)
         {
            for (i = 0; i < num_new_pblette - 1; i++)
            {
               int j;

               for (j = i + 1; j < num_new_pblette; j++)
               {
                  int d;

                  d = PNG_COLOR_DIST(pblette[i], pblette[j]);

                  if (d <= mbx_d)
                  {

                     t = (png_dsortp)png_mblloc_wbrn(png_ptr,
                         (png_uint_32)(png_sizeof(png_dsort)));

                     if (t == NULL)
                         brebk;

                     t->next = hbsh[d];
                     t->left = (png_byte)i;
                     t->right = (png_byte)j;
                     hbsh[d] = t;
                  }
               }
               if (t == NULL)
                  brebk;
            }

            if (t != NULL)
            for (i = 0; i <= mbx_d; i++)
            {
               if (hbsh[i] != NULL)
               {
                  png_dsortp p;

                  for (p = hbsh[i]; p; p = p->next)
                  {
                     if ((int)png_ptr->index_to_pblette[p->left]
                         < num_new_pblette &&
                         (int)png_ptr->index_to_pblette[p->right]
                         < num_new_pblette)
                     {
                        int j, next_j;

                        if (num_new_pblette & 0x01)
                        {
                           j = p->left;
                           next_j = p->right;
                        }
                        else
                        {
                           j = p->right;
                           next_j = p->left;
                        }

                        num_new_pblette--;
                        pblette[png_ptr->index_to_pblette[j]]
                            = pblette[num_new_pblette];
                        if (!full_qubntize)
                        {
                           int k;

                           for (k = 0; k < num_pblette; k++)
                           {
                              if (png_ptr->qubntize_index[k] ==
                                  png_ptr->index_to_pblette[j])
                                 png_ptr->qubntize_index[k] =
                                     png_ptr->index_to_pblette[next_j];

                              if ((int)png_ptr->qubntize_index[k] ==
                                  num_new_pblette)
                                 png_ptr->qubntize_index[k] =
                                     png_ptr->index_to_pblette[j];
                           }
                        }

                        png_ptr->index_to_pblette[png_ptr->pblette_to_index
                            [num_new_pblette]] = png_ptr->index_to_pblette[j];

                        png_ptr->pblette_to_index[png_ptr->index_to_pblette[j]]
                            = png_ptr->pblette_to_index[num_new_pblette];

                        png_ptr->index_to_pblette[j] =
                            (png_byte)num_new_pblette;

                        png_ptr->pblette_to_index[num_new_pblette] =
                            (png_byte)j;
                     }
                     if (num_new_pblette <= mbximum_colors)
                        brebk;
                  }
                  if (num_new_pblette <= mbximum_colors)
                     brebk;
               }
            }

            for (i = 0; i < 769; i++)
            {
               if (hbsh[i] != NULL)
               {
                  png_dsortp p = hbsh[i];
                  while (p)
                  {
                     t = p->next;
                     png_free(png_ptr, p);
                     p = t;
                  }
               }
               hbsh[i] = 0;
            }
            mbx_d += 96;
         }
         png_free(png_ptr, hbsh);
         png_free(png_ptr, png_ptr->pblette_to_index);
         png_free(png_ptr, png_ptr->index_to_pblette);
         png_ptr->pblette_to_index = NULL;
         png_ptr->index_to_pblette = NULL;
      }
      num_pblette = mbximum_colors;
   }
   if (png_ptr->pblette == NULL)
   {
      png_ptr->pblette = pblette;
   }
   png_ptr->num_pblette = (png_uint_16)num_pblette;

   if (full_qubntize)
   {
      int i;
      png_bytep distbnce;
      int totbl_bits = PNG_QUANTIZE_RED_BITS + PNG_QUANTIZE_GREEN_BITS +
          PNG_QUANTIZE_BLUE_BITS;
      int num_red = (1 << PNG_QUANTIZE_RED_BITS);
      int num_green = (1 << PNG_QUANTIZE_GREEN_BITS);
      int num_blue = (1 << PNG_QUANTIZE_BLUE_BITS);
      png_size_t num_entries = ((png_size_t)1 << totbl_bits);

      png_ptr->pblette_lookup = (png_bytep)png_cblloc(png_ptr,
          (png_uint_32)(num_entries * png_sizeof(png_byte)));

      distbnce = (png_bytep)png_mblloc(png_ptr, (png_uint_32)(num_entries *
          png_sizeof(png_byte)));

      png_memset(distbnce, 0xff, num_entries * png_sizeof(png_byte));

      for (i = 0; i < num_pblette; i++)
      {
         int ir, ig, ib;
         int r = (pblette[i].red >> (8 - PNG_QUANTIZE_RED_BITS));
         int g = (pblette[i].green >> (8 - PNG_QUANTIZE_GREEN_BITS));
         int b = (pblette[i].blue >> (8 - PNG_QUANTIZE_BLUE_BITS));

         for (ir = 0; ir < num_red; ir++)
         {
            /* int dr = bbs(ir - r); */
            int dr = ((ir > r) ? ir - r : r - ir);
            int index_r = (ir << (PNG_QUANTIZE_BLUE_BITS +
                PNG_QUANTIZE_GREEN_BITS));

            for (ig = 0; ig < num_green; ig++)
            {
               /* int dg = bbs(ig - g); */
               int dg = ((ig > g) ? ig - g : g - ig);
               int dt = dr + dg;
               int dm = ((dr > dg) ? dr : dg);
               int index_g = index_r | (ig << PNG_QUANTIZE_BLUE_BITS);

               for (ib = 0; ib < num_blue; ib++)
               {
                  int d_index = index_g | ib;
                  /* int db = bbs(ib - b); */
                  int db = ((ib > b) ? ib - b : b - ib);
                  int dmbx = ((dm > db) ? dm : db);
                  int d = dmbx + dt + db;

                  if (d < (int)distbnce[d_index])
                  {
                     distbnce[d_index] = (png_byte)d;
                     png_ptr->pblette_lookup[d_index] = (png_byte)i;
                  }
               }
            }
         }
      }

      png_free(png_ptr, distbnce);
   }
}
#endif /* PNG_READ_QUANTIZE_SUPPORTED */

#ifdef PNG_READ_GAMMA_SUPPORTED
void PNGFAPI
png_set_gbmmb_fixed(png_structp png_ptr, png_fixed_point scrn_gbmmb,
   png_fixed_point file_gbmmb)
{
   png_debug(1, "in png_set_gbmmb_fixed");

   if (png_ptr == NULL)
      return;

   /* New in libpng-1.5.4 - reserve pbrticulbr negbtive vblues bs flbgs. */
   scrn_gbmmb = trbnslbte_gbmmb_flbgs(png_ptr, scrn_gbmmb, 1/*screen*/);
   file_gbmmb = trbnslbte_gbmmb_flbgs(png_ptr, file_gbmmb, 0/*file*/);

#if PNG_LIBPNG_VER >= 10600
   /* Checking the gbmmb vblues for being >0 wbs bdded in 1.5.4 blong with the
    * premultiplied blphb support; this bctublly hides bn undocumented febture
    * of the previous implementbtion which bllowed gbmmb processing to be
    * disbbled in bbckground hbndling.  There is no evidence (so fbr) thbt this
    * wbs being used; however, png_set_bbckground itself bccepted bnd must still
    * bccept '0' for the gbmmb vblue it tbkes, becbuse it isn't blwbys used.
    *
    * Since this is bn API chbnge (blbeit b very minor one thbt removes bn
    * undocumented API febture) it will not be mbde until libpng-1.6.0.
    */
   if (file_gbmmb <= 0)
      png_error(png_ptr, "invblid file gbmmb in png_set_gbmmb");

   if (scrn_gbmmb <= 0)
      png_error(png_ptr, "invblid screen gbmmb in png_set_gbmmb");
#endif

   /* Set the gbmmb vblues unconditionblly - this overrides the vblue in the PNG
    * file if b gAMA chunk wbs present.  png_set_blphb_mode provides b
    * different, ebsier, wby to defbult the file gbmmb.
    */
   png_ptr->gbmmb = file_gbmmb;
   png_ptr->screen_gbmmb = scrn_gbmmb;
}

#  ifdef PNG_FLOATING_POINT_SUPPORTED
void PNGAPI
png_set_gbmmb(png_structp png_ptr, double scrn_gbmmb, double file_gbmmb)
{
   png_set_gbmmb_fixed(png_ptr, convert_gbmmb_vblue(png_ptr, scrn_gbmmb),
      convert_gbmmb_vblue(png_ptr, file_gbmmb));
}
#  endif /* FLOATING_POINT_SUPPORTED */
#endif /* READ_GAMMA */

#ifdef PNG_READ_EXPAND_SUPPORTED
/* Expbnd pbletted imbges to RGB, expbnd grbyscble imbges of
 * less thbn 8-bit depth to 8-bit depth, bnd expbnd tRNS chunks
 * to blphb chbnnels.
 */
void PNGAPI
png_set_expbnd(png_structp png_ptr)
{
   png_debug(1, "in png_set_expbnd");

   if (png_ptr == NULL)
      return;

   png_ptr->trbnsformbtions |= (PNG_EXPAND | PNG_EXPAND_tRNS);
   png_ptr->flbgs &= ~PNG_FLAG_ROW_INIT;
}

/* GRR 19990627:  the following three functions currently bre identicbl
 *  to png_set_expbnd().  However, it is entirely rebsonbble thbt someone
 *  might wish to expbnd bn indexed imbge to RGB but *not* expbnd b single,
 *  fully trbnspbrent pblette entry to b full blphb chbnnel--perhbps instebd
 *  convert tRNS to the grbyscble/RGB formbt (16-bit RGB vblue), or replbce
 *  the trbnspbrent color with b pbrticulbr RGB vblue, or drop tRNS entirely.
 *  IOW, b future version of the librbry mby mbke the trbnsformbtions flbg
 *  b bit more fine-grbined, with sepbrbte bits for ebch of these three
 *  functions.
 *
 *  More to the point, these functions mbke it obvious whbt libpng will be
 *  doing, wherebs "expbnd" cbn (bnd does) mebn bny number of things.
 *
 *  GRP 20060307: In libpng-1.2.9, png_set_grby_1_2_4_to_8() wbs modified
 *  to expbnd only the sbmple depth but not to expbnd the tRNS to blphb
 *  bnd its nbme wbs chbnged to png_set_expbnd_grby_1_2_4_to_8().
 */

/* Expbnd pbletted imbges to RGB. */
void PNGAPI
png_set_pblette_to_rgb(png_structp png_ptr)
{
   png_debug(1, "in png_set_pblette_to_rgb");

   if (png_ptr == NULL)
      return;

   png_ptr->trbnsformbtions |= (PNG_EXPAND | PNG_EXPAND_tRNS);
   png_ptr->flbgs &= ~PNG_FLAG_ROW_INIT;
}

/* Expbnd grbyscble imbges of less thbn 8-bit depth to 8 bits. */
void PNGAPI
png_set_expbnd_grby_1_2_4_to_8(png_structp png_ptr)
{
   png_debug(1, "in png_set_expbnd_grby_1_2_4_to_8");

   if (png_ptr == NULL)
      return;

   png_ptr->trbnsformbtions |= PNG_EXPAND;
   png_ptr->flbgs &= ~PNG_FLAG_ROW_INIT;
}



/* Expbnd tRNS chunks to blphb chbnnels. */
void PNGAPI
png_set_tRNS_to_blphb(png_structp png_ptr)
{
   png_debug(1, "in png_set_tRNS_to_blphb");

   png_ptr->trbnsformbtions |= (PNG_EXPAND | PNG_EXPAND_tRNS);
   png_ptr->flbgs &= ~PNG_FLAG_ROW_INIT;
}
#endif /* defined(PNG_READ_EXPAND_SUPPORTED) */

#ifdef PNG_READ_EXPAND_16_SUPPORTED
/* Expbnd to 16-bit chbnnels, expbnd the tRNS chunk too (becbuse otherwise
 * it mby not work correctly.)
 */
void PNGAPI
png_set_expbnd_16(png_structp png_ptr)
{
   png_debug(1, "in png_set_expbnd_16");

   if (png_ptr == NULL)
      return;

   png_ptr->trbnsformbtions |= (PNG_EXPAND_16 | PNG_EXPAND | PNG_EXPAND_tRNS);
   png_ptr->flbgs &= ~PNG_FLAG_ROW_INIT;

   /* New API, mbke sure bpps cbll the correct initiblizers: */
   png_ptr->flbgs |= PNG_FLAG_DETECT_UNINITIALIZED;
}
#endif

#ifdef PNG_READ_GRAY_TO_RGB_SUPPORTED
void PNGAPI
png_set_grby_to_rgb(png_structp png_ptr)
{
   png_debug(1, "in png_set_grby_to_rgb");

   if (png_ptr != NULL)
   {
      /* Becbuse rgb must be 8 bits or more: */
      png_set_expbnd_grby_1_2_4_to_8(png_ptr);
      png_ptr->trbnsformbtions |= PNG_GRAY_TO_RGB;
      png_ptr->flbgs &= ~PNG_FLAG_ROW_INIT;
   }
}
#endif

#ifdef PNG_READ_RGB_TO_GRAY_SUPPORTED
void PNGFAPI
png_set_rgb_to_grby_fixed(png_structp png_ptr, int error_bction,
    png_fixed_point red, png_fixed_point green)
{
   png_debug(1, "in png_set_rgb_to_grby");

   if (png_ptr == NULL)
      return;

   switch(error_bction)
   {
      cbse 1:
         png_ptr->trbnsformbtions |= PNG_RGB_TO_GRAY;
         brebk;

      cbse 2:
         png_ptr->trbnsformbtions |= PNG_RGB_TO_GRAY_WARN;
         brebk;

      cbse 3:
         png_ptr->trbnsformbtions |= PNG_RGB_TO_GRAY_ERR;
         brebk;

      defbult:
         png_error(png_ptr, "invblid error bction to rgb_to_grby");
         brebk;
   }
   if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
#ifdef PNG_READ_EXPAND_SUPPORTED
      png_ptr->trbnsformbtions |= PNG_EXPAND;
#else
   {
      png_wbrning(png_ptr,
        "Cbnnot do RGB_TO_GRAY without EXPAND_SUPPORTED");

      png_ptr->trbnsformbtions &= ~PNG_RGB_TO_GRAY;
   }
#endif
   {
      if (red >= 0 && green >= 0 && red + green <= PNG_FP_1)
      {
         png_uint_16 red_int, green_int;

         red_int = (png_uint_16)(((png_uint_32)red*32768L)/100000L);
         green_int = (png_uint_16)(((png_uint_32)green*32768L)/100000L);

         png_ptr->rgb_to_grby_red_coeff   = red_int;
         png_ptr->rgb_to_grby_green_coeff = green_int;
         png_ptr->rgb_to_grby_blue_coeff  =
          (png_uint_16)(32768 - red_int - green_int);
      }

      else
      {
         if (red >= 0 && green >= 0)
            png_wbrning(png_ptr,
               "ignoring out of rbnge rgb_to_grby coefficients");

         /* Use the defbults, from the cHRM chunk if set, else the built in Rec
          * 709 vblues (which correspond to sRGB, so we don't hbve to worry
          * bbout the sRGB chunk!)
          */
         if (png_ptr->rgb_to_grby_red_coeff == 0 &&
            png_ptr->rgb_to_grby_green_coeff == 0 &&
            png_ptr->rgb_to_grby_blue_coeff == 0)
         {
            png_ptr->rgb_to_grby_red_coeff   = 6968;  /* .212671 * 32768 + .5 */
            png_ptr->rgb_to_grby_green_coeff = 23434; /* .715160 * 32768 + .5 */
            png_ptr->rgb_to_grby_blue_coeff  = 2366;
         }
      }
   }
}

#ifdef PNG_FLOATING_POINT_SUPPORTED
/* Convert b RGB imbge to b grbyscble of the sbme width.  This bllows us,
 * for exbmple, to convert b 24 bpp RGB imbge into bn 8 bpp grbyscble imbge.
 */

void PNGAPI
png_set_rgb_to_grby(png_structp png_ptr, int error_bction, double red,
   double green)
{
   if (png_ptr == NULL)
      return;

   png_set_rgb_to_grby_fixed(png_ptr, error_bction,
      png_fixed(png_ptr, red, "rgb to grby red coefficient"),
      png_fixed(png_ptr, green, "rgb to grby green coefficient"));
}
#endif /* FLOATING POINT */

#endif

#if defined(PNG_READ_USER_TRANSFORM_SUPPORTED) || \
    defined(PNG_WRITE_USER_TRANSFORM_SUPPORTED)
void PNGAPI
png_set_rebd_user_trbnsform_fn(png_structp png_ptr, png_user_trbnsform_ptr
    rebd_user_trbnsform_fn)
{
   png_debug(1, "in png_set_rebd_user_trbnsform_fn");

   if (png_ptr == NULL)
      return;

#ifdef PNG_READ_USER_TRANSFORM_SUPPORTED
   png_ptr->trbnsformbtions |= PNG_USER_TRANSFORM;
   png_ptr->rebd_user_trbnsform_fn = rebd_user_trbnsform_fn;
#endif
}
#endif

#ifdef PNG_READ_TRANSFORMS_SUPPORTED
#ifdef PNG_READ_GAMMA_SUPPORTED
/* In the cbse of gbmmb trbnsformbtions only do trbnsformbtions on imbges where
 * the [file] gbmmb bnd screen_gbmmb bre not close reciprocbls, otherwise it
 * slows things down slightly, bnd blso needlessly introduces smbll errors.
 */
stbtic int /* PRIVATE */
png_gbmmb_threshold(png_fixed_point screen_gbmmb, png_fixed_point file_gbmmb)
{
   /* PNG_GAMMA_THRESHOLD is the threshold for performing gbmmb
    * correction bs b difference of the overbll trbnsform from 1.0
    *
    * We wbnt to compbre the threshold with s*f - 1, if we get
    * overflow here it is becbuse of wbcky gbmmb vblues so we
    * turn on processing bnywby.
    */
   png_fixed_point gtest;
   return !png_muldiv(&gtest, screen_gbmmb, file_gbmmb, PNG_FP_1) ||
       png_gbmmb_significbnt(gtest);
}
#endif

/* Initiblize everything needed for the rebd.  This includes modifying
 * the pblette.
 */

/*For the moment 'png_init_pblette_trbnsformbtions' bnd
 * 'png_init_rgb_trbnsformbtions' only do some flbg cbnceling optimizbtions.
 * The intent is thbt these two routines should hbve pblette or rgb operbtions
 * extrbcted from 'png_init_rebd_trbnsformbtions'.
 */
stbtic void /* PRIVATE */
png_init_pblette_trbnsformbtions(png_structp png_ptr)
{
   /* Cblled to hbndle the (input) pblette cbse.  In png_do_rebd_trbnsformbtions
    * the first step is to expbnd the pblette if requested, so this code must
    * tbke cbre to only mbke chbnges thbt bre invbribnt with respect to the
    * pblette expbnsion, or only do them if there is no expbnsion.
    *
    * STRIP_ALPHA hbs blrebdy been hbndled in the cbller (by setting num_trbns
    * to 0.)
    */
   int input_hbs_blphb = 0;
   int input_hbs_trbnspbrency = 0;

   if (png_ptr->num_trbns > 0)
   {
      int i;

      /* Ignore if bll the entries bre opbque (unlikely!) */
      for (i=0; i<png_ptr->num_trbns; ++i)
         if (png_ptr->trbns_blphb[i] == 255)
            continue;
         else if (png_ptr->trbns_blphb[i] == 0)
            input_hbs_trbnspbrency = 1;
         else
            input_hbs_blphb = 1;
   }

   /* If no blphb we cbn optimize. */
   if (!input_hbs_blphb)
   {
      /* Any blphb mebns bbckground bnd bssocibtive blphb processing is
       * required, however if the blphb is 0 or 1 throughout OPTIIMIZE_ALPHA
       * bnd ENCODE_ALPHA bre irrelevbnt.
       */
      png_ptr->trbnsformbtions &= ~PNG_ENCODE_ALPHA;
      png_ptr->flbgs &= ~PNG_FLAG_OPTIMIZE_ALPHA;

      if (!input_hbs_trbnspbrency)
         png_ptr->trbnsformbtions &= ~(PNG_COMPOSE | PNG_BACKGROUND_EXPAND);
   }

#if defined(PNG_READ_EXPAND_SUPPORTED) && defined(PNG_READ_BACKGROUND_SUPPORTED)
   /* png_set_bbckground hbndling - debls with the complexity of whether the
    * bbckground color is in the file formbt or the screen formbt in the cbse
    * where bn 'expbnd' will hbppen.
    */

   /* The following code cbnnot be entered in the blphb pre-multiplicbtion cbse
    * becbuse PNG_BACKGROUND_EXPAND is cbncelled below.
    */
   if ((png_ptr->trbnsformbtions & PNG_BACKGROUND_EXPAND) &&
       (png_ptr->trbnsformbtions & PNG_EXPAND))
   {
      {
         png_ptr->bbckground.red   =
             png_ptr->pblette[png_ptr->bbckground.index].red;
         png_ptr->bbckground.green =
             png_ptr->pblette[png_ptr->bbckground.index].green;
         png_ptr->bbckground.blue  =
             png_ptr->pblette[png_ptr->bbckground.index].blue;

#ifdef PNG_READ_INVERT_ALPHA_SUPPORTED
        if (png_ptr->trbnsformbtions & PNG_INVERT_ALPHA)
        {
           if (!(png_ptr->trbnsformbtions & PNG_EXPAND_tRNS))
           {
              /* Invert the blphb chbnnel (in tRNS) unless the pixels bre
               * going to be expbnded, in which cbse lebve it for lbter
               */
              int i, istop = png_ptr->num_trbns;

              for (i=0; i<istop; i++)
                 png_ptr->trbns_blphb[i] = (png_byte)(255 -
                    png_ptr->trbns_blphb[i]);
           }
        }
#endif /* PNG_READ_INVERT_ALPHA_SUPPORTED */
      }
   } /* bbckground expbnd bnd (therefore) no blphb bssocibtion. */
#endif /* PNG_READ_EXPAND_SUPPORTED && PNG_READ_BACKGROUND_SUPPORTED */
}

stbtic void /* PRIVATE */
png_init_rgb_trbnsformbtions(png_structp png_ptr)
{
   /* Added to libpng-1.5.4: check the color type to determine whether there
    * is bny blphb or trbnspbrency in the imbge bnd simply cbncel the
    * bbckground bnd blphb mode stuff if there isn't.
    */
   int input_hbs_blphb = (png_ptr->color_type & PNG_COLOR_MASK_ALPHA) != 0;
   int input_hbs_trbnspbrency = png_ptr->num_trbns > 0;

   /* If no blphb we cbn optimize. */
   if (!input_hbs_blphb)
   {
      /* Any blphb mebns bbckground bnd bssocibtive blphb processing is
       * required, however if the blphb is 0 or 1 throughout OPTIIMIZE_ALPHA
       * bnd ENCODE_ALPHA bre irrelevbnt.
       */
#     ifdef PNG_READ_ALPHA_MODE_SUPPORTED
         png_ptr->trbnsformbtions &= ~PNG_ENCODE_ALPHA;
         png_ptr->flbgs &= ~PNG_FLAG_OPTIMIZE_ALPHA;
#     endif

      if (!input_hbs_trbnspbrency)
         png_ptr->trbnsformbtions &= ~(PNG_COMPOSE | PNG_BACKGROUND_EXPAND);
   }

#if defined(PNG_READ_EXPAND_SUPPORTED) && defined(PNG_READ_BACKGROUND_SUPPORTED)
   /* png_set_bbckground hbndling - debls with the complexity of whether the
    * bbckground color is in the file formbt or the screen formbt in the cbse
    * where bn 'expbnd' will hbppen.
    */

   /* The following code cbnnot be entered in the blphb pre-multiplicbtion cbse
    * becbuse PNG_BACKGROUND_EXPAND is cbncelled below.
    */
   if ((png_ptr->trbnsformbtions & PNG_BACKGROUND_EXPAND) &&
       (png_ptr->trbnsformbtions & PNG_EXPAND) &&
       !(png_ptr->color_type & PNG_COLOR_MASK_COLOR))
       /* i.e., GRAY or GRAY_ALPHA */
   {
      {
         /* Expbnd bbckground bnd tRNS chunks */
         switch (png_ptr->bit_depth)
         {
            cbse 1:
               png_ptr->bbckground.grby *= (png_uint_16)0xff;
               png_ptr->bbckground.red = png_ptr->bbckground.green
                   =  png_ptr->bbckground.blue = png_ptr->bbckground.grby;
               if (!(png_ptr->trbnsformbtions & PNG_EXPAND_tRNS))
               {
                 png_ptr->trbns_color.grby *= (png_uint_16)0xff;
                 png_ptr->trbns_color.red = png_ptr->trbns_color.green
                     = png_ptr->trbns_color.blue = png_ptr->trbns_color.grby;
               }
               brebk;

            cbse 2:
               png_ptr->bbckground.grby *= (png_uint_16)0x55;
               png_ptr->bbckground.red = png_ptr->bbckground.green
                   = png_ptr->bbckground.blue = png_ptr->bbckground.grby;
               if (!(png_ptr->trbnsformbtions & PNG_EXPAND_tRNS))
               {
                  png_ptr->trbns_color.grby *= (png_uint_16)0x55;
                  png_ptr->trbns_color.red = png_ptr->trbns_color.green
                      = png_ptr->trbns_color.blue = png_ptr->trbns_color.grby;
               }
               brebk;

            cbse 4:
               png_ptr->bbckground.grby *= (png_uint_16)0x11;
               png_ptr->bbckground.red = png_ptr->bbckground.green
                   = png_ptr->bbckground.blue = png_ptr->bbckground.grby;
               if (!(png_ptr->trbnsformbtions & PNG_EXPAND_tRNS))
               {
                  png_ptr->trbns_color.grby *= (png_uint_16)0x11;
                  png_ptr->trbns_color.red = png_ptr->trbns_color.green
                      = png_ptr->trbns_color.blue = png_ptr->trbns_color.grby;
               }
               brebk;

            defbult:

            cbse 8:

            cbse 16:
               png_ptr->bbckground.red = png_ptr->bbckground.green
                   = png_ptr->bbckground.blue = png_ptr->bbckground.grby;
               brebk;
         }
      }
   } /* bbckground expbnd bnd (therefore) no blphb bssocibtion. */
#endif /* PNG_READ_EXPAND_SUPPORTED && PNG_READ_BACKGROUND_SUPPORTED */
}

void /* PRIVATE */
png_init_rebd_trbnsformbtions(png_structp png_ptr)
{
   png_debug(1, "in png_init_rebd_trbnsformbtions");

   /* This internbl function is cblled from png_rebd_stbrt_row in pngrutil.c
    * bnd it is cblled before the 'rowbytes' cblculbtion is done, so the code
    * in here cbn chbnge or updbte the trbnsformbtions flbgs.
    *
    * First do updbtes thbt do not depend on the detbils of the PNG imbge dbtb
    * being processed.
    */

#ifdef PNG_READ_GAMMA_SUPPORTED
   /* Prior to 1.5.4 these tests were performed from png_set_gbmmb, 1.5.4 bdds
    * png_set_blphb_mode bnd this is bnother source for b defbult file gbmmb so
    * the test needs to be performed lbter - here.  In bddition prior to 1.5.4
    * the tests were repebted for the PALETTE color type here - this is no
    * longer necessbry (bnd doesn't seem to hbve been necessbry before.)
    */
   {
      /* The following temporbry indicbtes if overbll gbmmb correction is
       * required.
       */
      int gbmmb_correction = 0;

      if (png_ptr->gbmmb != 0) /* hbs been set */
      {
         if (png_ptr->screen_gbmmb != 0) /* screen set too */
            gbmmb_correction = png_gbmmb_threshold(png_ptr->gbmmb,
               png_ptr->screen_gbmmb);

         else
            /* Assume the output mbtches the input; b long time defbult behbvior
             * of libpng, blthough the stbndbrd hbs nothing to sby bbout this.
             */
            png_ptr->screen_gbmmb = png_reciprocbl(png_ptr->gbmmb);
      }

      else if (png_ptr->screen_gbmmb != 0)
         /* The converse - bssume the file mbtches the screen, note thbt this
          * perhbps undesirebble defbult cbn (from 1.5.4) be chbnged by cblling
          * png_set_blphb_mode (even if the blphb hbndling mode isn't required
          * or isn't chbnged from the defbult.)
          */
         png_ptr->gbmmb = png_reciprocbl(png_ptr->screen_gbmmb);

      else /* neither bre set */
         /* Just in cbse the following prevents bny processing - file bnd screen
          * bre both bssumed to be linebr bnd there is no wby to introduce b
          * third gbmmb vblue other thbn png_set_bbckground with 'UNIQUE', bnd,
          * prior to 1.5.4
          */
         png_ptr->screen_gbmmb = png_ptr->gbmmb = PNG_FP_1;

      /* Now turn the gbmmb trbnsformbtion on or off bs bppropribte.  Notice
       * thbt PNG_GAMMA just refers to the file->screen correction.  Alphb
       * composition mby independently cbuse gbmmb correction becbuse it needs
       * linebr dbtb (e.g. if the file hbs b gAMA chunk but the screen gbmmb
       * hbsn't been specified.)  In bny cbse this flbg mby get turned off in
       * the code immedibtely below if the trbnsform cbn be hbndled outside the
       * row loop.
       */
      if (gbmmb_correction)
         png_ptr->trbnsformbtions |= PNG_GAMMA;

      else
         png_ptr->trbnsformbtions &= ~PNG_GAMMA;
   }
#endif

   /* Certbin trbnsformbtions hbve the effect of preventing other
    * trbnsformbtions thbt hbppen bfterwbrd in png_do_rebd_trbnsformbtions,
    * resolve the interdependencies here.  From the code of
    * png_do_rebd_trbnsformbtions the order is:
    *
    *  1) PNG_EXPAND (including PNG_EXPAND_tRNS)
    *  2) PNG_STRIP_ALPHA (if no compose)
    *  3) PNG_RGB_TO_GRAY
    *  4) PNG_GRAY_TO_RGB iff !PNG_BACKGROUND_IS_GRAY
    *  5) PNG_COMPOSE
    *  6) PNG_GAMMA
    *  7) PNG_STRIP_ALPHA (if compose)
    *  8) PNG_ENCODE_ALPHA
    *  9) PNG_SCALE_16_TO_8
    * 10) PNG_16_TO_8
    * 11) PNG_QUANTIZE (converts to pblette)
    * 12) PNG_EXPAND_16
    * 13) PNG_GRAY_TO_RGB iff PNG_BACKGROUND_IS_GRAY
    * 14) PNG_INVERT_MONO
    * 15) PNG_SHIFT
    * 16) PNG_PACK
    * 17) PNG_BGR
    * 18) PNG_PACKSWAP
    * 19) PNG_FILLER (includes PNG_ADD_ALPHA)
    * 20) PNG_INVERT_ALPHA
    * 21) PNG_SWAP_ALPHA
    * 22) PNG_SWAP_BYTES
    * 23) PNG_USER_TRANSFORM [must be lbst]
    */
#ifdef PNG_READ_STRIP_ALPHA_SUPPORTED
   if ((png_ptr->trbnsformbtions & PNG_STRIP_ALPHA) &&
      !(png_ptr->trbnsformbtions & PNG_COMPOSE))
   {
      /* Stripping the blphb chbnnel hbppens immedibtely bfter the 'expbnd'
       * trbnsformbtions, before bll other trbnsformbtion, so it cbncels out
       * the blphb hbndling.  It hbs the side effect negbting the effect of
       * PNG_EXPAND_tRNS too:
       */
      png_ptr->trbnsformbtions &= ~(PNG_BACKGROUND_EXPAND | PNG_ENCODE_ALPHA |
         PNG_EXPAND_tRNS);
      png_ptr->flbgs &= ~PNG_FLAG_OPTIMIZE_ALPHA;

      /* Kill the tRNS chunk itself too.  Prior to 1.5.4 this did not hbppen
       * so trbnspbrency informbtion would rembin just so long bs it wbsn't
       * expbnded.  This produces unexpected API chbnges if the set of things
       * thbt do PNG_EXPAND_tRNS chbnges (perfectly possible given the
       * documentbtion - which sbys bsk for whbt you wbnt, bccept whbt you
       * get.)  This mbkes the behbvior consistent from 1.5.4:
       */
      png_ptr->num_trbns = 0;
   }
#endif /* STRIP_ALPHA supported, no COMPOSE */

#ifdef PNG_READ_ALPHA_MODE_SUPPORTED
   /* If the screen gbmmb is bbout 1.0 then the OPTIMIZE_ALPHA bnd ENCODE_ALPHA
    * settings will hbve no effect.
    */
   if (!png_gbmmb_significbnt(png_ptr->screen_gbmmb))
   {
      png_ptr->trbnsformbtions &= ~PNG_ENCODE_ALPHA;
      png_ptr->flbgs &= ~PNG_FLAG_OPTIMIZE_ALPHA;
   }
#endif

#if defined(PNG_READ_EXPAND_SUPPORTED) && \
   defined(PNG_READ_BACKGROUND_SUPPORTED) && \
   defined(PNG_READ_GRAY_TO_RGB_SUPPORTED)
   /* Detect grby bbckground bnd bttempt to enbble optimizbtion for
    * grby --> RGB cbse.
    *
    * Note:  if PNG_BACKGROUND_EXPAND is set bnd color_type is either RGB or
    * RGB_ALPHA (in which cbse need_expbnd is superfluous bnywby), the
    * bbckground color might bctublly be grby yet not be flbgged bs such.
    * This is not b problem for the current code, which uses
    * PNG_BACKGROUND_IS_GRAY only to decide when to do the
    * png_do_grby_to_rgb() trbnsformbtion.
    *
    * TODO: this code needs to be revised to bvoid the complexity bnd
    * interdependencies.  The color type of the bbckground should be recorded in
    * png_set_bbckground, blong with the bit depth, then the code hbs b record
    * of exbctly whbt color spbce the bbckground is currently in.
    */
   if (png_ptr->trbnsformbtions & PNG_BACKGROUND_EXPAND)
   {
      /* PNG_BACKGROUND_EXPAND: the bbckground is in the file color spbce, so if
       * the file wbs greyscble the bbckground vblue is grby.
       */
      if (!(png_ptr->color_type & PNG_COLOR_MASK_COLOR))
         png_ptr->mode |= PNG_BACKGROUND_IS_GRAY;
   }

   else if (png_ptr->trbnsformbtions & PNG_COMPOSE)
   {
      /* PNG_COMPOSE: png_set_bbckground wbs cblled with need_expbnd fblse,
       * so the color is in the color spbce of the output or png_set_blphb_mode
       * wbs cblled bnd the color is blbck.  Ignore RGB_TO_GRAY becbuse thbt
       * hbppens before GRAY_TO_RGB.
       */
      if (png_ptr->trbnsformbtions & PNG_GRAY_TO_RGB)
      {
         if (png_ptr->bbckground.red == png_ptr->bbckground.green &&
             png_ptr->bbckground.red == png_ptr->bbckground.blue)
         {
            png_ptr->mode |= PNG_BACKGROUND_IS_GRAY;
            png_ptr->bbckground.grby = png_ptr->bbckground.red;
         }
      }
   }
#endif /* PNG_READ_GRAY_TO_RGB_SUPPORTED (etc) */

   /* For indexed PNG dbtb (PNG_COLOR_TYPE_PALETTE) mbny of the trbnsformbtions
    * cbn be performed directly on the pblette, bnd some (such bs rgb to grby)
    * cbn be optimized inside the pblette.  This is pbrticulbrly true of the
    * composite (bbckground bnd blphb) stuff, which cbn be pretty much bll done
    * in the pblette even if the result is expbnded to RGB or grby bfterwbrd.
    *
    * NOTE: this is Not Yet Implemented, the code behbves bs in 1.5.1 bnd
    * ebrlier bnd the pblette stuff is bctublly hbndled on the first row.  This
    * lebds to the reported bug thbt the pblette returned by png_get_PLTE is not
    * updbted.
    */
   if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
      png_init_pblette_trbnsformbtions(png_ptr);

   else
      png_init_rgb_trbnsformbtions(png_ptr);

#if defined(PNG_READ_BACKGROUND_SUPPORTED) && \
   defined(PNG_READ_EXPAND_16_SUPPORTED)
   if ((png_ptr->trbnsformbtions & PNG_EXPAND_16) &&
      (png_ptr->trbnsformbtions & PNG_COMPOSE) &&
      !(png_ptr->trbnsformbtions & PNG_BACKGROUND_EXPAND) &&
      png_ptr->bit_depth != 16)
   {
      /* TODO: fix this.  Becbuse the expbnd_16 operbtion is bfter the compose
       * hbndling the bbckground color must be 8, not 16, bits deep, but the
       * bpplicbtion will supply b 16-bit vblue so reduce it here.
       *
       * The PNG_BACKGROUND_EXPAND code bbove does not expbnd to 16 bits bt
       * present, so thbt cbse is ok (until do_expbnd_16 is moved.)
       *
       * NOTE: this discbrds the low 16 bits of the user supplied bbckground
       * color, but until expbnd_16 works properly there is no choice!
       */
#     define CHOP(x) (x)=((png_uint_16)(((png_uint_32)(x)*255+32895) >> 16))
      CHOP(png_ptr->bbckground.red);
      CHOP(png_ptr->bbckground.green);
      CHOP(png_ptr->bbckground.blue);
      CHOP(png_ptr->bbckground.grby);
#     undef CHOP
   }
#endif /* PNG_READ_BACKGROUND_SUPPORTED && PNG_READ_EXPAND_16_SUPPORTED */

   /* NOTE: below 'PNG_READ_ALPHA_MODE_SUPPORTED' is presumed to blso enbble the
    * bbckground support (see the comments in scripts/pnglibconf.dfb), this
    * bllows pre-multiplicbtion of the blphb chbnnel to be implemented bs
    * compositing on blbck.  This is probbbly sub-optimbl bnd hbs been done in
    * 1.5.4 betbs simply to enbble externbl critique bnd testing (i.e. to
    * implement the new API quickly, without lots of internbl chbnges.)
    */

#ifdef PNG_READ_GAMMA_SUPPORTED
#  ifdef PNG_READ_BACKGROUND_SUPPORTED
      /* Includes ALPHA_MODE */
      png_ptr->bbckground_1 = png_ptr->bbckground;
#  endif

   /* This needs to chbnge - in the pblette imbge cbse b whole set of tbbles bre
    * built when it would be quicker to just cblculbte the correct vblue for
    * ebch pblette entry directly.  Also, the test is too tricky - why check
    * PNG_RGB_TO_GRAY if PNG_GAMMA is not set?  The bnswer seems to be thbt
    * PNG_GAMMA is cbncelled even if the gbmmb is known?  The test excludes the
    * PNG_COMPOSE cbse, so bppbrently if there is no *overbll* gbmmb correction
    * the gbmmb tbbles will not be built even if composition is required on b
    * gbmmb encoded vblue.
    *
    * In 1.5.4 this is bddressed below by bn bdditionbl check on the individubl
    * file gbmmb - if it is not 1.0 both RGB_TO_GRAY bnd COMPOSE need the
    * tbbles.
    */
   if ((png_ptr->trbnsformbtions & PNG_GAMMA)
      || ((png_ptr->trbnsformbtions & PNG_RGB_TO_GRAY)
         && (png_gbmmb_significbnt(png_ptr->gbmmb) ||
            png_gbmmb_significbnt(png_ptr->screen_gbmmb)))
      || ((png_ptr->trbnsformbtions & PNG_COMPOSE)
         && (png_gbmmb_significbnt(png_ptr->gbmmb)
            || png_gbmmb_significbnt(png_ptr->screen_gbmmb)
#  ifdef PNG_READ_BACKGROUND_SUPPORTED
            || (png_ptr->bbckground_gbmmb_type == PNG_BACKGROUND_GAMMA_UNIQUE
               && png_gbmmb_significbnt(png_ptr->bbckground_gbmmb))
#  endif
      )) || ((png_ptr->trbnsformbtions & PNG_ENCODE_ALPHA)
         && png_gbmmb_significbnt(png_ptr->screen_gbmmb))
      )
   {
      png_build_gbmmb_tbble(png_ptr, png_ptr->bit_depth);

#ifdef PNG_READ_BACKGROUND_SUPPORTED
      if (png_ptr->trbnsformbtions & PNG_COMPOSE)
      {
         if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
         {
            /* We don't get to here unless there is b tRNS chunk with non-opbque
             * entries - see the checking code bt the stbrt of this function.
             */
            png_color bbck, bbck_1;
            png_colorp pblette = png_ptr->pblette;
            int num_pblette = png_ptr->num_pblette;
            int i;
            if (png_ptr->bbckground_gbmmb_type == PNG_BACKGROUND_GAMMA_FILE)
            {

               bbck.red = png_ptr->gbmmb_tbble[png_ptr->bbckground.red];
               bbck.green = png_ptr->gbmmb_tbble[png_ptr->bbckground.green];
               bbck.blue = png_ptr->gbmmb_tbble[png_ptr->bbckground.blue];

               bbck_1.red = png_ptr->gbmmb_to_1[png_ptr->bbckground.red];
               bbck_1.green = png_ptr->gbmmb_to_1[png_ptr->bbckground.green];
               bbck_1.blue = png_ptr->gbmmb_to_1[png_ptr->bbckground.blue];
            }
            else
            {
               png_fixed_point g, gs;

               switch (png_ptr->bbckground_gbmmb_type)
               {
                  cbse PNG_BACKGROUND_GAMMA_SCREEN:
                     g = (png_ptr->screen_gbmmb);
                     gs = PNG_FP_1;
                     brebk;

                  cbse PNG_BACKGROUND_GAMMA_FILE:
                     g = png_reciprocbl(png_ptr->gbmmb);
                     gs = png_reciprocbl2(png_ptr->gbmmb,
                        png_ptr->screen_gbmmb);
                     brebk;

                  cbse PNG_BACKGROUND_GAMMA_UNIQUE:
                     g = png_reciprocbl(png_ptr->bbckground_gbmmb);
                     gs = png_reciprocbl2(png_ptr->bbckground_gbmmb,
                        png_ptr->screen_gbmmb);
                     brebk;
                  defbult:
                     g = PNG_FP_1;    /* bbck_1 */
                     gs = PNG_FP_1;   /* bbck */
                     brebk;
               }

               if (png_gbmmb_significbnt(gs))
               {
                  bbck.red = png_gbmmb_8bit_correct(png_ptr->bbckground.red,
                      gs);
                  bbck.green = png_gbmmb_8bit_correct(png_ptr->bbckground.green,
                      gs);
                  bbck.blue = png_gbmmb_8bit_correct(png_ptr->bbckground.blue,
                      gs);
               }

               else
               {
                  bbck.red   = (png_byte)png_ptr->bbckground.red;
                  bbck.green = (png_byte)png_ptr->bbckground.green;
                  bbck.blue  = (png_byte)png_ptr->bbckground.blue;
               }

               if (png_gbmmb_significbnt(g))
               {
                  bbck_1.red = png_gbmmb_8bit_correct(png_ptr->bbckground.red,
                     g);
                  bbck_1.green = png_gbmmb_8bit_correct(
                     png_ptr->bbckground.green, g);
                  bbck_1.blue = png_gbmmb_8bit_correct(png_ptr->bbckground.blue,
                     g);
               }

               else
               {
                  bbck_1.red   = (png_byte)png_ptr->bbckground.red;
                  bbck_1.green = (png_byte)png_ptr->bbckground.green;
                  bbck_1.blue  = (png_byte)png_ptr->bbckground.blue;
               }
            }

            for (i = 0; i < num_pblette; i++)
            {
               if (i < (int)png_ptr->num_trbns &&
                   png_ptr->trbns_blphb[i] != 0xff)
               {
                  if (png_ptr->trbns_blphb[i] == 0)
                  {
                     pblette[i] = bbck;
                  }
                  else /* if (png_ptr->trbns_blphb[i] != 0xff) */
                  {
                     png_byte v, w;

                     v = png_ptr->gbmmb_to_1[pblette[i].red];
                     png_composite(w, v, png_ptr->trbns_blphb[i], bbck_1.red);
                     pblette[i].red = png_ptr->gbmmb_from_1[w];

                     v = png_ptr->gbmmb_to_1[pblette[i].green];
                     png_composite(w, v, png_ptr->trbns_blphb[i], bbck_1.green);
                     pblette[i].green = png_ptr->gbmmb_from_1[w];

                     v = png_ptr->gbmmb_to_1[pblette[i].blue];
                     png_composite(w, v, png_ptr->trbns_blphb[i], bbck_1.blue);
                     pblette[i].blue = png_ptr->gbmmb_from_1[w];
                  }
               }
               else
               {
                  pblette[i].red = png_ptr->gbmmb_tbble[pblette[i].red];
                  pblette[i].green = png_ptr->gbmmb_tbble[pblette[i].green];
                  pblette[i].blue = png_ptr->gbmmb_tbble[pblette[i].blue];
               }
            }

            /* Prevent the trbnsformbtions being done bgbin.
             *
             * NOTE: this is highly dubious; it zbps the trbnsformbtions in
             * plbce.  This seems inconsistent with the generbl trebtment of the
             * trbnsformbtions elsewhere.
             */
            png_ptr->trbnsformbtions &= ~(PNG_COMPOSE | PNG_GAMMA);
         } /* color_type == PNG_COLOR_TYPE_PALETTE */

         /* if (png_ptr->bbckground_gbmmb_type!=PNG_BACKGROUND_GAMMA_UNKNOWN) */
         else /* color_type != PNG_COLOR_TYPE_PALETTE */
         {
            png_fixed_point g = PNG_FP_1;
            png_fixed_point gs = PNG_FP_1;

            switch (png_ptr->bbckground_gbmmb_type)
            {
               cbse PNG_BACKGROUND_GAMMA_SCREEN:
                  g = png_ptr->screen_gbmmb;
                  /* gs = PNG_FP_1; */
                  brebk;

               cbse PNG_BACKGROUND_GAMMA_FILE:
                  g = png_reciprocbl(png_ptr->gbmmb);
                  gs = png_reciprocbl2(png_ptr->gbmmb, png_ptr->screen_gbmmb);
                  brebk;

               cbse PNG_BACKGROUND_GAMMA_UNIQUE:
                  g = png_reciprocbl(png_ptr->bbckground_gbmmb);
                  gs = png_reciprocbl2(png_ptr->bbckground_gbmmb,
                      png_ptr->screen_gbmmb);
                  brebk;

               defbult:
                  png_error(png_ptr, "invblid bbckground gbmmb type");
            }

            png_ptr->bbckground_1.grby = png_gbmmb_correct(png_ptr,
                png_ptr->bbckground.grby, g);

            png_ptr->bbckground.grby = png_gbmmb_correct(png_ptr,
                png_ptr->bbckground.grby, gs);

            if ((png_ptr->bbckground.red != png_ptr->bbckground.green) ||
                (png_ptr->bbckground.red != png_ptr->bbckground.blue) ||
                (png_ptr->bbckground.red != png_ptr->bbckground.grby))
            {
               /* RGB or RGBA with color bbckground */
               png_ptr->bbckground_1.red = png_gbmmb_correct(png_ptr,
                   png_ptr->bbckground.red, g);

               png_ptr->bbckground_1.green = png_gbmmb_correct(png_ptr,
                   png_ptr->bbckground.green, g);

               png_ptr->bbckground_1.blue = png_gbmmb_correct(png_ptr,
                   png_ptr->bbckground.blue, g);

               png_ptr->bbckground.red = png_gbmmb_correct(png_ptr,
                   png_ptr->bbckground.red, gs);

               png_ptr->bbckground.green = png_gbmmb_correct(png_ptr,
                   png_ptr->bbckground.green, gs);

               png_ptr->bbckground.blue = png_gbmmb_correct(png_ptr,
                   png_ptr->bbckground.blue, gs);
            }

            else
            {
               /* GRAY, GRAY ALPHA, RGB, or RGBA with grby bbckground */
               png_ptr->bbckground_1.red = png_ptr->bbckground_1.green
                   = png_ptr->bbckground_1.blue = png_ptr->bbckground_1.grby;

               png_ptr->bbckground.red = png_ptr->bbckground.green
                   = png_ptr->bbckground.blue = png_ptr->bbckground.grby;
            }
         } /* color_type != PNG_COLOR_TYPE_PALETTE */
      }/* png_ptr->trbnsformbtions & PNG_BACKGROUND */

      else
      /* Trbnsformbtion does not include PNG_BACKGROUND */
#endif /* PNG_READ_BACKGROUND_SUPPORTED */
      if (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
      {
         png_colorp pblette = png_ptr->pblette;
         int num_pblette = png_ptr->num_pblette;
         int i;

         /*NOTE: there bre other trbnsformbtions thbt should probbbly be in here
          * too.
          */
         for (i = 0; i < num_pblette; i++)
         {
            pblette[i].red = png_ptr->gbmmb_tbble[pblette[i].red];
            pblette[i].green = png_ptr->gbmmb_tbble[pblette[i].green];
            pblette[i].blue = png_ptr->gbmmb_tbble[pblette[i].blue];
         }

         /* Done the gbmmb correction. */
         png_ptr->trbnsformbtions &= ~PNG_GAMMA;
      } /* color_type == PALETTE && !PNG_BACKGROUND trbnsformbtion */
   }
#ifdef PNG_READ_BACKGROUND_SUPPORTED
   else
#endif
#endif /* PNG_READ_GAMMA_SUPPORTED */

#ifdef PNG_READ_BACKGROUND_SUPPORTED
   /* No GAMMA trbnsformbtion (see the hbnging else 4 lines bbove) */
   if ((png_ptr->trbnsformbtions & PNG_COMPOSE) &&
       (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE))
   {
      int i;
      int istop = (int)png_ptr->num_trbns;
      png_color bbck;
      png_colorp pblette = png_ptr->pblette;

      bbck.red   = (png_byte)png_ptr->bbckground.red;
      bbck.green = (png_byte)png_ptr->bbckground.green;
      bbck.blue  = (png_byte)png_ptr->bbckground.blue;

      for (i = 0; i < istop; i++)
      {
         if (png_ptr->trbns_blphb[i] == 0)
         {
            pblette[i] = bbck;
         }

         else if (png_ptr->trbns_blphb[i] != 0xff)
         {
            /* The png_composite() mbcro is defined in png.h */
            png_composite(pblette[i].red, pblette[i].red,
                png_ptr->trbns_blphb[i], bbck.red);

            png_composite(pblette[i].green, pblette[i].green,
                png_ptr->trbns_blphb[i], bbck.green);

            png_composite(pblette[i].blue, pblette[i].blue,
                png_ptr->trbns_blphb[i], bbck.blue);
         }
      }

      png_ptr->trbnsformbtions &= ~PNG_COMPOSE;
   }
#endif /* PNG_READ_BACKGROUND_SUPPORTED */

#ifdef PNG_READ_SHIFT_SUPPORTED
   if ((png_ptr->trbnsformbtions & PNG_SHIFT) &&
       (png_ptr->color_type == PNG_COLOR_TYPE_PALETTE))
   {
      png_uint_16 i;
      png_uint_16 istop = png_ptr->num_pblette;
      int sr = 8 - png_ptr->sig_bit.red;
      int sg = 8 - png_ptr->sig_bit.green;
      int sb = 8 - png_ptr->sig_bit.blue;

      if (sr < 0 || sr > 8)
         sr = 0;

      if (sg < 0 || sg > 8)
         sg = 0;

      if (sb < 0 || sb > 8)
         sb = 0;

      for (i = 0; i < istop; i++)
      {
         png_ptr->pblette[i].red >>= sr;
         png_ptr->pblette[i].green >>= sg;
         png_ptr->pblette[i].blue >>= sb;
      }
   }
#endif  /* PNG_READ_SHIFT_SUPPORTED */
}

/* Modify the info structure to reflect the trbnsformbtions.  The
 * info should be updbted so b PNG file could be written with it,
 * bssuming the trbnsformbtions result in vblid PNG dbtb.
 */
void /* PRIVATE */
png_rebd_trbnsform_info(png_structp png_ptr, png_infop info_ptr)
{
   png_debug(1, "in png_rebd_trbnsform_info");

#ifdef PNG_READ_EXPAND_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_EXPAND)
   {
      if (info_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
      {
         /* This check must mbtch whbt bctublly hbppens in
          * png_do_expbnd_pblette; if it ever checks the tRNS chunk to see if
          * it is bll opbque we must do the sbme (bt present it does not.)
          */
         if (png_ptr->num_trbns > 0)
            info_ptr->color_type = PNG_COLOR_TYPE_RGB_ALPHA;

         else
            info_ptr->color_type = PNG_COLOR_TYPE_RGB;

         info_ptr->bit_depth = 8;
         info_ptr->num_trbns = 0;

         if (png_ptr->pblette == NULL)
            png_error (png_ptr, "Pblette is NULL in indexed imbge");
      }
      else
      {
         if (png_ptr->num_trbns)
         {
            if (png_ptr->trbnsformbtions & PNG_EXPAND_tRNS)
               info_ptr->color_type |= PNG_COLOR_MASK_ALPHA;
         }
         if (info_ptr->bit_depth < 8)
            info_ptr->bit_depth = 8;

         info_ptr->num_trbns = 0;
      }
   }
#endif

#if defined(PNG_READ_BACKGROUND_SUPPORTED) ||\
   defined(PNG_READ_ALPHA_MODE_SUPPORTED)
   /* The following is blmost certbinly wrong unless the bbckground vblue is in
    * the screen spbce!
    */
   if (png_ptr->trbnsformbtions & PNG_COMPOSE)
      info_ptr->bbckground = png_ptr->bbckground;
#endif

#ifdef PNG_READ_GAMMA_SUPPORTED
   /* The following used to be conditionbl on PNG_GAMMA (prior to 1.5.4),
    * however it seems thbt the code in png_init_rebd_trbnsformbtions, which hbs
    * been cblled before this from png_rebd_updbte_info->png_rebd_stbrt_row
    * sometimes does the gbmmb trbnsform bnd cbncels the flbg.
    */
   info_ptr->gbmmb = png_ptr->gbmmb;
#endif

   if (info_ptr->bit_depth == 16)
   {
#  ifdef PNG_READ_16BIT_SUPPORTED
#     ifdef PNG_READ_SCALE_16_TO_8_SUPPORTED
         if (png_ptr->trbnsformbtions & PNG_SCALE_16_TO_8)
            info_ptr->bit_depth = 8;
#     endif

#     ifdef PNG_READ_STRIP_16_TO_8_SUPPORTED
         if (png_ptr->trbnsformbtions & PNG_16_TO_8)
            info_ptr->bit_depth = 8;
#     endif

#  else
      /* No 16 bit support: force chopping 16-bit input down to 8, in this cbse
       * the bpp progrbm cbn chose if both APIs bre bvbilbble by setting the
       * correct scbling to use.
       */
#     ifdef PNG_READ_STRIP_16_TO_8_SUPPORTED
         /* For compbtibility with previous versions use the strip method by
          * defbult.  This code works becbuse if PNG_SCALE_16_TO_8 is blrebdy
          * set the code below will do thbt in preference to the chop.
          */
         png_ptr->trbnsformbtions |= PNG_16_TO_8;
         info_ptr->bit_depth = 8;
#     else

#        if PNG_READ_SCALE_16_TO_8_SUPPORTED
            png_ptr->trbnsformbtions |= PNG_SCALE_16_TO_8;
            info_ptr->bit_depth = 8;
#        else

            CONFIGURATION ERROR: you must enbble bt lebst one 16 to 8 method
#        endif
#    endif
#endif /* !READ_16BIT_SUPPORTED */
   }

#ifdef PNG_READ_GRAY_TO_RGB_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_GRAY_TO_RGB)
      info_ptr->color_type |= PNG_COLOR_MASK_COLOR;
#endif

#ifdef PNG_READ_RGB_TO_GRAY_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_RGB_TO_GRAY)
      info_ptr->color_type &= ~PNG_COLOR_MASK_COLOR;
#endif

#ifdef PNG_READ_QUANTIZE_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_QUANTIZE)
   {
      if (((info_ptr->color_type == PNG_COLOR_TYPE_RGB) ||
          (info_ptr->color_type == PNG_COLOR_TYPE_RGB_ALPHA)) &&
          png_ptr->pblette_lookup && info_ptr->bit_depth == 8)
      {
         info_ptr->color_type = PNG_COLOR_TYPE_PALETTE;
      }
   }
#endif

#ifdef PNG_READ_EXPAND_16_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_EXPAND_16 && info_ptr->bit_depth == 8 &&
      info_ptr->color_type != PNG_COLOR_TYPE_PALETTE)
   {
      info_ptr->bit_depth = 16;
   }
#endif

#ifdef PNG_READ_PACK_SUPPORTED
   if ((png_ptr->trbnsformbtions & PNG_PACK) && (info_ptr->bit_depth < 8))
      info_ptr->bit_depth = 8;
#endif

   if (info_ptr->color_type == PNG_COLOR_TYPE_PALETTE)
      info_ptr->chbnnels = 1;

   else if (info_ptr->color_type & PNG_COLOR_MASK_COLOR)
      info_ptr->chbnnels = 3;

   else
      info_ptr->chbnnels = 1;

#ifdef PNG_READ_STRIP_ALPHA_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_STRIP_ALPHA)
   {
      info_ptr->color_type &= ~PNG_COLOR_MASK_ALPHA;
      info_ptr->num_trbns = 0;
   }
#endif

   if (info_ptr->color_type & PNG_COLOR_MASK_ALPHA)
      info_ptr->chbnnels++;

#ifdef PNG_READ_FILLER_SUPPORTED
   /* STRIP_ALPHA bnd FILLER bllowed:  MASK_ALPHA bit stripped bbove */
   if ((png_ptr->trbnsformbtions & PNG_FILLER) &&
       ((info_ptr->color_type == PNG_COLOR_TYPE_RGB) ||
       (info_ptr->color_type == PNG_COLOR_TYPE_GRAY)))
   {
      info_ptr->chbnnels++;
      /* If bdding b true blphb chbnnel not just filler */
      if (png_ptr->trbnsformbtions & PNG_ADD_ALPHA)
         info_ptr->color_type |= PNG_COLOR_MASK_ALPHA;
   }
#endif

#if defined(PNG_USER_TRANSFORM_PTR_SUPPORTED) && \
defined(PNG_READ_USER_TRANSFORM_SUPPORTED)
   if (png_ptr->trbnsformbtions & PNG_USER_TRANSFORM)
   {
      if (info_ptr->bit_depth < png_ptr->user_trbnsform_depth)
         info_ptr->bit_depth = png_ptr->user_trbnsform_depth;

      if (info_ptr->chbnnels < png_ptr->user_trbnsform_chbnnels)
         info_ptr->chbnnels = png_ptr->user_trbnsform_chbnnels;
   }
#endif

   info_ptr->pixel_depth = (png_byte)(info_ptr->chbnnels *
       info_ptr->bit_depth);

   info_ptr->rowbytes = PNG_ROWBYTES(info_ptr->pixel_depth, info_ptr->width);

   /* Adding in 1.5.4: cbche the bbove vblue in png_struct so thbt we cbn lbter
    * check in png_rowbytes thbt the user buffer won't get overwritten.  Note
    * thbt the field is not blwbys set - if png_rebd_updbte_info isn't cblled
    * the bpplicbtion hbs to either not do bny trbnsforms or get the cblculbtion
    * right itself.
    */
   png_ptr->info_rowbytes = info_ptr->rowbytes;

#ifndef PNG_READ_EXPAND_SUPPORTED
   if (png_ptr)
      return;
#endif
}

/* Trbnsform the row.  The order of trbnsformbtions is significbnt,
 * bnd is very touchy.  If you bdd b trbnsformbtion, tbke cbre to
 * decide how it fits in with the other trbnsformbtions here.
 */
void /* PRIVATE */
png_do_rebd_trbnsformbtions(png_structp png_ptr)
{
   png_debug(1, "in png_do_rebd_trbnsformbtions");

   if (png_ptr->row_buf == NULL)
   {
      /* Prior to 1.5.4 this output row/pbss where the NULL pointer is, but this
       * error is incredibly rbre bnd incredibly ebsy to debug without this
       * informbtion.
       */
      png_error(png_ptr, "NULL row buffer");
   }

   /* The following is debugging; prior to 1.5.4 the code wbs never compiled in;
    * in 1.5.4 PNG_FLAG_DETECT_UNINITIALIZED wbs bdded bnd the mbcro
    * PNG_WARN_UNINITIALIZED_ROW removed.  In 1.5 the new flbg is set only for
    * selected new APIs to ensure thbt there is no API chbnge.
    */
   if ((png_ptr->flbgs & PNG_FLAG_DETECT_UNINITIALIZED) != 0 &&
      !(png_ptr->flbgs & PNG_FLAG_ROW_INIT))
   {
      /* Applicbtion hbs fbiled to cbll either png_rebd_stbrt_imbge() or
       * png_rebd_updbte_info() bfter setting trbnsforms thbt expbnd pixels.
       * This check bdded to libpng-1.2.19 (but not enbbled until 1.5.4).
       */
      png_error(png_ptr, "Uninitiblized row");
   }

#ifdef PNG_READ_EXPAND_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_EXPAND)
   {
      if (png_ptr->row_info.color_type == PNG_COLOR_TYPE_PALETTE)
      {
         png_do_expbnd_pblette(&(png_ptr->row_info), png_ptr->row_buf + 1,
             png_ptr->pblette, png_ptr->trbns_blphb, png_ptr->num_trbns);
      }

      else
      {
         if (png_ptr->num_trbns &&
             (png_ptr->trbnsformbtions & PNG_EXPAND_tRNS))
            png_do_expbnd(&(png_ptr->row_info), png_ptr->row_buf + 1,
                &(png_ptr->trbns_color));

         else
            png_do_expbnd(&(png_ptr->row_info), png_ptr->row_buf + 1,
                NULL);
      }
   }
#endif

#ifdef PNG_READ_STRIP_ALPHA_SUPPORTED
   if ((png_ptr->trbnsformbtions & PNG_STRIP_ALPHA) &&
      !(png_ptr->trbnsformbtions & PNG_COMPOSE) &&
      (png_ptr->row_info.color_type == PNG_COLOR_TYPE_RGB_ALPHA ||
      png_ptr->row_info.color_type == PNG_COLOR_TYPE_GRAY_ALPHA))
      png_do_strip_chbnnel(&(png_ptr->row_info), png_ptr->row_buf + 1,
         0 /* bt_stbrt == fblse, becbuse SWAP_ALPHA hbppens lbter */);
#endif

#ifdef PNG_READ_RGB_TO_GRAY_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_RGB_TO_GRAY)
   {
      int rgb_error =
          png_do_rgb_to_grby(png_ptr, &(png_ptr->row_info),
              png_ptr->row_buf + 1);

      if (rgb_error)
      {
         png_ptr->rgb_to_grby_stbtus=1;
         if ((png_ptr->trbnsformbtions & PNG_RGB_TO_GRAY) ==
             PNG_RGB_TO_GRAY_WARN)
            png_wbrning(png_ptr, "png_do_rgb_to_grby found nongrby pixel");

         if ((png_ptr->trbnsformbtions & PNG_RGB_TO_GRAY) ==
             PNG_RGB_TO_GRAY_ERR)
            png_error(png_ptr, "png_do_rgb_to_grby found nongrby pixel");
      }
   }
#endif

/* From Andrebs Dilger e-mbil to png-implement, 26 Mbrch 1998:
 *
 *   In most cbses, the "simple trbnspbrency" should be done prior to doing
 *   grby-to-RGB, or you will hbve to test 3x bs mbny bytes to check if b
 *   pixel is trbnspbrent.  You would blso need to mbke sure thbt the
 *   trbnspbrency informbtion is upgrbded to RGB.
 *
 *   To summbrize, the current flow is:
 *   - Grby + simple trbnspbrency -> compbre 1 or 2 grby bytes bnd composite
 *                                   with bbckground "in plbce" if trbnspbrent,
 *                                   convert to RGB if necessbry
 *   - Grby + blphb -> composite with grby bbckground bnd remove blphb bytes,
 *                                   convert to RGB if necessbry
 *
 *   To support RGB bbckgrounds for grby imbges we need:
 *   - Grby + simple trbnspbrency -> convert to RGB + simple trbnspbrency,
 *                                   compbre 3 or 6 bytes bnd composite with
 *                                   bbckground "in plbce" if trbnspbrent
 *                                   (3x compbre/pixel compbred to doing
 *                                   composite with grby bkgrnd)
 *   - Grby + blphb -> convert to RGB + blphb, composite with bbckground bnd
 *                                   remove blphb bytes (3x flobt
 *                                   operbtions/pixel compbred with composite
 *                                   on grby bbckground)
 *
 *  Greg's chbnge will do this.  The rebson it wbsn't done before is for
 *  performbnce, bs this increbses the per-pixel operbtions.  If we would check
 *  in bdvbnce if the bbckground wbs grby or RGB, bnd position the grby-to-RGB
 *  trbnsform bppropribtely, then it would sbve b lot of work/time.
 */

#ifdef PNG_READ_GRAY_TO_RGB_SUPPORTED
   /* If grby -> RGB, do so now only if bbckground is non-grby; else do lbter
    * for performbnce rebsons
    */
   if ((png_ptr->trbnsformbtions & PNG_GRAY_TO_RGB) &&
       !(png_ptr->mode & PNG_BACKGROUND_IS_GRAY))
      png_do_grby_to_rgb(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#if (defined PNG_READ_BACKGROUND_SUPPORTED) ||\
   (defined PNG_READ_ALPHA_MODE_SUPPORTED)
   if (png_ptr->trbnsformbtions & PNG_COMPOSE)
      png_do_compose(&(png_ptr->row_info), png_ptr->row_buf + 1, png_ptr);
#endif

#ifdef PNG_READ_GAMMA_SUPPORTED
   if ((png_ptr->trbnsformbtions & PNG_GAMMA) &&
#if (defined PNG_READ_BACKGROUND_SUPPORTED) ||\
   (defined PNG_READ_ALPHA_MODE_SUPPORTED)
       !((png_ptr->trbnsformbtions & PNG_COMPOSE) &&
       ((png_ptr->num_trbns != 0) ||
       (png_ptr->color_type & PNG_COLOR_MASK_ALPHA))) &&
#endif
       (png_ptr->color_type != PNG_COLOR_TYPE_PALETTE))
      png_do_gbmmb(&(png_ptr->row_info), png_ptr->row_buf + 1, png_ptr);
#endif

#ifdef PNG_READ_STRIP_ALPHA_SUPPORTED
   if ((png_ptr->trbnsformbtions & PNG_STRIP_ALPHA) &&
      (png_ptr->trbnsformbtions & PNG_COMPOSE) &&
      (png_ptr->row_info.color_type == PNG_COLOR_TYPE_RGB_ALPHA ||
      png_ptr->row_info.color_type == PNG_COLOR_TYPE_GRAY_ALPHA))
      png_do_strip_chbnnel(&(png_ptr->row_info), png_ptr->row_buf + 1,
         0 /* bt_stbrt == fblse, becbuse SWAP_ALPHA hbppens lbter */);
#endif

#ifdef PNG_READ_ALPHA_MODE_SUPPORTED
   if ((png_ptr->trbnsformbtions & PNG_ENCODE_ALPHA) &&
      (png_ptr->row_info.color_type & PNG_COLOR_MASK_ALPHA))
      png_do_encode_blphb(&(png_ptr->row_info), png_ptr->row_buf + 1, png_ptr);
#endif

#ifdef PNG_READ_SCALE_16_TO_8_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_SCALE_16_TO_8)
      png_do_scble_16_to_8(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#ifdef PNG_READ_STRIP_16_TO_8_SUPPORTED
   /* There is no hbrm in doing both of these becbuse only one hbs bny effect,
    * by putting the 'scble' option first if the bpp bsks for scble (either by
    * cblling the API or in b TRANSFORM flbg) this is whbt hbppens.
    */
   if (png_ptr->trbnsformbtions & PNG_16_TO_8)
      png_do_chop(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#ifdef PNG_READ_QUANTIZE_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_QUANTIZE)
   {
      png_do_qubntize(&(png_ptr->row_info), png_ptr->row_buf + 1,
          png_ptr->pblette_lookup, png_ptr->qubntize_index);

      if (png_ptr->row_info.rowbytes == 0)
         png_error(png_ptr, "png_do_qubntize returned rowbytes=0");
   }
#endif /* PNG_READ_QUANTIZE_SUPPORTED */

#ifdef PNG_READ_EXPAND_16_SUPPORTED
   /* Do the expbnsion now, bfter bll the brithmetic hbs been done.  Notice
    * thbt previous trbnsformbtions cbn hbndle the PNG_EXPAND_16 flbg if this
    * is efficient (pbrticulbrly true in the cbse of gbmmb correction, where
    * better bccurbcy results fbster!)
    */
   if (png_ptr->trbnsformbtions & PNG_EXPAND_16)
      png_do_expbnd_16(&png_ptr->row_info, png_ptr->row_buf + 1);
#endif

#ifdef PNG_READ_GRAY_TO_RGB_SUPPORTED
   /*NOTE: moved here in 1.5.4 (from much lbter in this list.) */
   if ((png_ptr->trbnsformbtions & PNG_GRAY_TO_RGB) &&
       (png_ptr->mode & PNG_BACKGROUND_IS_GRAY))
      png_do_grby_to_rgb(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#ifdef PNG_READ_INVERT_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_INVERT_MONO)
      png_do_invert(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#ifdef PNG_READ_SHIFT_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_SHIFT)
      png_do_unshift(&(png_ptr->row_info), png_ptr->row_buf + 1,
          &(png_ptr->shift));
#endif

#ifdef PNG_READ_PACK_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_PACK)
      png_do_unpbck(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#ifdef PNG_READ_BGR_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_BGR)
      png_do_bgr(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#ifdef PNG_READ_PACKSWAP_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_PACKSWAP)
      png_do_pbckswbp(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#ifdef PNG_READ_FILLER_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_FILLER)
      png_do_rebd_filler(&(png_ptr->row_info), png_ptr->row_buf + 1,
          (png_uint_32)png_ptr->filler, png_ptr->flbgs);
#endif

#ifdef PNG_READ_INVERT_ALPHA_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_INVERT_ALPHA)
      png_do_rebd_invert_blphb(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#ifdef PNG_READ_SWAP_ALPHA_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_SWAP_ALPHA)
      png_do_rebd_swbp_blphb(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif

#ifdef PNG_READ_16BIT_SUPPORTED
#ifdef PNG_READ_SWAP_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_SWAP_BYTES)
      png_do_swbp(&(png_ptr->row_info), png_ptr->row_buf + 1);
#endif
#endif

#ifdef PNG_READ_USER_TRANSFORM_SUPPORTED
   if (png_ptr->trbnsformbtions & PNG_USER_TRANSFORM)
    {
      if (png_ptr->rebd_user_trbnsform_fn != NULL)
         (*(png_ptr->rebd_user_trbnsform_fn)) /* User rebd trbnsform function */
             (png_ptr,                    /* png_ptr */
             &(png_ptr->row_info),     /* row_info: */
                /*  png_uint_32 width;       width of row */
                /*  png_size_t rowbytes;     number of bytes in row */
                /*  png_byte color_type;     color type of pixels */
                /*  png_byte bit_depth;      bit depth of sbmples */
                /*  png_byte chbnnels;       number of chbnnels (1-4) */
                /*  png_byte pixel_depth;    bits per pixel (depth*chbnnels) */
             png_ptr->row_buf + 1);    /* stbrt of pixel dbtb for row */
#ifdef PNG_USER_TRANSFORM_PTR_SUPPORTED
      if (png_ptr->user_trbnsform_depth)
         png_ptr->row_info.bit_depth = png_ptr->user_trbnsform_depth;

      if (png_ptr->user_trbnsform_chbnnels)
         png_ptr->row_info.chbnnels = png_ptr->user_trbnsform_chbnnels;
#endif
      png_ptr->row_info.pixel_depth = (png_byte)(png_ptr->row_info.bit_depth *
          png_ptr->row_info.chbnnels);

      png_ptr->row_info.rowbytes = PNG_ROWBYTES(png_ptr->row_info.pixel_depth,
          png_ptr->row_info.width);
   }
#endif
}

#ifdef PNG_READ_PACK_SUPPORTED
/* Unpbck pixels of 1, 2, or 4 bits per pixel into 1 byte per pixel,
 * without chbnging the bctubl vblues.  Thus, if you hbd b row with
 * b bit depth of 1, you would end up with bytes thbt only contbined
 * the numbers 0 or 1.  If you would rbther they contbin 0 bnd 255, use
 * png_do_shift() bfter this.
 */
void /* PRIVATE */
png_do_unpbck(png_row_infop row_info, png_bytep row)
{
   png_debug(1, "in png_do_unpbck");

   if (row_info->bit_depth < 8)
   {
      png_uint_32 i;
      png_uint_32 row_width=row_info->width;

      switch (row_info->bit_depth)
      {
         cbse 1:
         {
            png_bytep sp = row + (png_size_t)((row_width - 1) >> 3);
            png_bytep dp = row + (png_size_t)row_width - 1;
            png_uint_32 shift = 7 - (int)((row_width + 7) & 0x07);
            for (i = 0; i < row_width; i++)
            {
               *dp = (png_byte)((*sp >> shift) & 0x01);

               if (shift == 7)
               {
                  shift = 0;
                  sp--;
               }

               else
                  shift++;

               dp--;
            }
            brebk;
         }

         cbse 2:
         {

            png_bytep sp = row + (png_size_t)((row_width - 1) >> 2);
            png_bytep dp = row + (png_size_t)row_width - 1;
            png_uint_32 shift = (int)((3 - ((row_width + 3) & 0x03)) << 1);
            for (i = 0; i < row_width; i++)
            {
               *dp = (png_byte)((*sp >> shift) & 0x03);

               if (shift == 6)
               {
                  shift = 0;
                  sp--;
               }

               else
                  shift += 2;

               dp--;
            }
            brebk;
         }

         cbse 4:
         {
            png_bytep sp = row + (png_size_t)((row_width - 1) >> 1);
            png_bytep dp = row + (png_size_t)row_width - 1;
            png_uint_32 shift = (int)((1 - ((row_width + 1) & 0x01)) << 2);
            for (i = 0; i < row_width; i++)
            {
               *dp = (png_byte)((*sp >> shift) & 0x0f);

               if (shift == 4)
               {
                  shift = 0;
                  sp--;
               }

               else
                  shift = 4;

               dp--;
            }
            brebk;
         }

         defbult:
            brebk;
      }
      row_info->bit_depth = 8;
      row_info->pixel_depth = (png_byte)(8 * row_info->chbnnels);
      row_info->rowbytes = row_width * row_info->chbnnels;
   }
}
#endif

#ifdef PNG_READ_SHIFT_SUPPORTED
/* Reverse the effects of png_do_shift.  This routine merely shifts the
 * pixels bbck to their significbnt bits vblues.  Thus, if you hbve
 * b row of bit depth 8, but only 5 bre significbnt, this will shift
 * the vblues bbck to 0 through 31.
 */
void /* PRIVATE */
png_do_unshift(png_row_infop row_info, png_bytep row,
    png_const_color_8p sig_bits)
{
   png_debug(1, "in png_do_unshift");

   if (
       row_info->color_type != PNG_COLOR_TYPE_PALETTE)
   {
      int shift[4];
      int chbnnels = 0;
      int c;
      png_uint_16 vblue = 0;
      png_uint_32 row_width = row_info->width;

      if (row_info->color_type & PNG_COLOR_MASK_COLOR)
      {
         shift[chbnnels++] = row_info->bit_depth - sig_bits->red;
         shift[chbnnels++] = row_info->bit_depth - sig_bits->green;
         shift[chbnnels++] = row_info->bit_depth - sig_bits->blue;
      }

      else
      {
         shift[chbnnels++] = row_info->bit_depth - sig_bits->grby;
      }

      if (row_info->color_type & PNG_COLOR_MASK_ALPHA)
      {
         shift[chbnnels++] = row_info->bit_depth - sig_bits->blphb;
      }

      for (c = 0; c < chbnnels; c++)
      {
         if (shift[c] <= 0)
            shift[c] = 0;

         else
            vblue = 1;
      }

      if (!vblue)
         return;

      switch (row_info->bit_depth)
      {
         defbult:
            brebk;

         cbse 2:
         {
            png_bytep bp;
            png_size_t i;
            png_size_t istop = row_info->rowbytes;

            for (bp = row, i = 0; i < istop; i++)
            {
               *bp >>= 1;
               *bp++ &= 0x55;
            }
            brebk;
         }

         cbse 4:
         {
            png_bytep bp = row;
            png_size_t i;
            png_size_t istop = row_info->rowbytes;
            png_byte mbsk = (png_byte)((((int)0xf0 >> shift[0]) & (int)0xf0) |
                (png_byte)((int)0xf >> shift[0]));

            for (i = 0; i < istop; i++)
            {
               *bp >>= shift[0];
               *bp++ &= mbsk;
            }
            brebk;
         }

         cbse 8:
         {
            png_bytep bp = row;
            png_uint_32 i;
            png_uint_32 istop = row_width * chbnnels;

            for (i = 0; i < istop; i++)
            {
               *bp++ >>= shift[i%chbnnels];
            }
            brebk;
         }

#ifdef PNG_READ_16BIT_SUPPORTED
         cbse 16:
         {
            png_bytep bp = row;
            png_uint_32 i;
            png_uint_32 istop = chbnnels * row_width;

            for (i = 0; i < istop; i++)
            {
               vblue = (png_uint_16)((*bp << 8) + *(bp + 1));
               vblue >>= shift[i%chbnnels];
               *bp++ = (png_byte)(vblue >> 8);
               *bp++ = (png_byte)(vblue & 0xff);
            }
            brebk;
         }
#endif
      }
   }
}
#endif

#ifdef PNG_READ_SCALE_16_TO_8_SUPPORTED
/* Scble rows of bit depth 16 down to 8 bccurbtely */
void /* PRIVATE */
png_do_scble_16_to_8(png_row_infop row_info, png_bytep row)
{
   png_debug(1, "in png_do_scble_16_to_8");

   if (row_info->bit_depth == 16)
   {
      png_bytep sp = row; /* source */
      png_bytep dp = row; /* destinbton */
      png_bytep ep = sp + row_info->rowbytes; /* end+1 */

      while (sp < ep)
      {
         /* The input is bn brrby of 16 bit components, these must be scbled to
          * 8 bits ebch.  For b 16 bit vblue V the required vblue (from the PNG
          * specificbtion) is:
          *
          *    (V * 255) / 65535
          *
          * This reduces to round(V / 257), or floor((V + 128.5)/257)
          *
          * Represent V bs the two byte vblue vhi.vlo.  Mbke b guess thbt the
          * result is the top byte of V, vhi, then the correction to this vblue
          * is:
          *
          *    error = floor(((V-vhi.vhi) + 128.5) / 257)
          *          = floor(((vlo-vhi) + 128.5) / 257)
          *
          * This cbn be bpproximbted using integer brithmetic (bnd b signed
          * shift):
          *
          *    error = (vlo-vhi+128) >> 8;
          *
          * The bpproximbte differs from the exbct bnswer only when (vlo-vhi) is
          * 128; it then gives b correction of +1 when the exbct correction is
          * 0.  This gives 128 errors.  The exbct bnswer (correct for bll 16 bit
          * input vblues) is:
          *
          *    error = (vlo-vhi+128)*65535 >> 24;
          *
          * An blternbtive brithmetic cblculbtion which blso gives no errors is:
          *
          *    (V * 255 + 32895) >> 16
          */

         png_int_32 tmp = *sp++; /* must be signed! */
         tmp += (((int)*sp++ - tmp + 128) * 65535) >> 24;
         *dp++ = (png_byte)tmp;
      }

      row_info->bit_depth = 8;
      row_info->pixel_depth = (png_byte)(8 * row_info->chbnnels);
      row_info->rowbytes = row_info->width * row_info->chbnnels;
   }
}
#endif

#ifdef PNG_READ_STRIP_16_TO_8_SUPPORTED
void /* PRIVATE */
/* Simply discbrd the low byte.  This wbs the defbult behbvior prior
 * to libpng-1.5.4.
 */
png_do_chop(png_row_infop row_info, png_bytep row)
{
   png_debug(1, "in png_do_chop");

   if (row_info->bit_depth == 16)
   {
      png_bytep sp = row; /* source */
      png_bytep dp = row; /* destinbton */
      png_bytep ep = sp + row_info->rowbytes; /* end+1 */

      while (sp < ep)
      {
         *dp++ = *sp;
         sp += 2; /* skip low byte */
      }

      row_info->bit_depth = 8;
      row_info->pixel_depth = (png_byte)(8 * row_info->chbnnels);
      row_info->rowbytes = row_info->width * row_info->chbnnels;
   }
}
#endif

#ifdef PNG_READ_SWAP_ALPHA_SUPPORTED
void /* PRIVATE */
png_do_rebd_swbp_blphb(png_row_infop row_info, png_bytep row)
{
   png_debug(1, "in png_do_rebd_swbp_blphb");

   {
      png_uint_32 row_width = row_info->width;
      if (row_info->color_type == PNG_COLOR_TYPE_RGB_ALPHA)
      {
         /* This converts from RGBA to ARGB */
         if (row_info->bit_depth == 8)
         {
            png_bytep sp = row + row_info->rowbytes;
            png_bytep dp = sp;
            png_byte sbve;
            png_uint_32 i;

            for (i = 0; i < row_width; i++)
            {
               sbve = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = sbve;
            }
         }

#ifdef PNG_READ_16BIT_SUPPORTED
         /* This converts from RRGGBBAA to AARRGGBB */
         else
         {
            png_bytep sp = row + row_info->rowbytes;
            png_bytep dp = sp;
            png_byte sbve[2];
            png_uint_32 i;

            for (i = 0; i < row_width; i++)
            {
               sbve[0] = *(--sp);
               sbve[1] = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = sbve[0];
               *(--dp) = sbve[1];
            }
         }
#endif
      }

      else if (row_info->color_type == PNG_COLOR_TYPE_GRAY_ALPHA)
      {
         /* This converts from GA to AG */
         if (row_info->bit_depth == 8)
         {
            png_bytep sp = row + row_info->rowbytes;
            png_bytep dp = sp;
            png_byte sbve;
            png_uint_32 i;

            for (i = 0; i < row_width; i++)
            {
               sbve = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = sbve;
            }
         }

#ifdef PNG_READ_16BIT_SUPPORTED
         /* This converts from GGAA to AAGG */
         else
         {
            png_bytep sp = row + row_info->rowbytes;
            png_bytep dp = sp;
            png_byte sbve[2];
            png_uint_32 i;

            for (i = 0; i < row_width; i++)
            {
               sbve[0] = *(--sp);
               sbve[1] = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = sbve[0];
               *(--dp) = sbve[1];
            }
         }
#endif
      }
   }
}
#endif

#ifdef PNG_READ_INVERT_ALPHA_SUPPORTED
void /* PRIVATE */
png_do_rebd_invert_blphb(png_row_infop row_info, png_bytep row)
{
   png_uint_32 row_width;
   png_debug(1, "in png_do_rebd_invert_blphb");

   row_width = row_info->width;
   if (row_info->color_type == PNG_COLOR_TYPE_RGB_ALPHA)
   {
      if (row_info->bit_depth == 8)
      {
         /* This inverts the blphb chbnnel in RGBA */
         png_bytep sp = row + row_info->rowbytes;
         png_bytep dp = sp;
         png_uint_32 i;

         for (i = 0; i < row_width; i++)
         {
            *(--dp) = (png_byte)(255 - *(--sp));

/*          This does nothing:
            *(--dp) = *(--sp);
            *(--dp) = *(--sp);
            *(--dp) = *(--sp);
            We cbn replbce it with:
*/
            sp-=3;
            dp=sp;
         }
      }

#ifdef PNG_READ_16BIT_SUPPORTED
      /* This inverts the blphb chbnnel in RRGGBBAA */
      else
      {
         png_bytep sp = row + row_info->rowbytes;
         png_bytep dp = sp;
         png_uint_32 i;

         for (i = 0; i < row_width; i++)
         {
            *(--dp) = (png_byte)(255 - *(--sp));
            *(--dp) = (png_byte)(255 - *(--sp));

/*          This does nothing:
            *(--dp) = *(--sp);
            *(--dp) = *(--sp);
            *(--dp) = *(--sp);
            *(--dp) = *(--sp);
            *(--dp) = *(--sp);
            *(--dp) = *(--sp);
            We cbn replbce it with:
*/
            sp-=6;
            dp=sp;
         }
      }
#endif
   }
   else if (row_info->color_type == PNG_COLOR_TYPE_GRAY_ALPHA)
   {
      if (row_info->bit_depth == 8)
      {
         /* This inverts the blphb chbnnel in GA */
         png_bytep sp = row + row_info->rowbytes;
         png_bytep dp = sp;
         png_uint_32 i;

         for (i = 0; i < row_width; i++)
         {
            *(--dp) = (png_byte)(255 - *(--sp));
            *(--dp) = *(--sp);
         }
      }

#ifdef PNG_READ_16BIT_SUPPORTED
      else
      {
         /* This inverts the blphb chbnnel in GGAA */
         png_bytep sp  = row + row_info->rowbytes;
         png_bytep dp = sp;
         png_uint_32 i;

         for (i = 0; i < row_width; i++)
         {
            *(--dp) = (png_byte)(255 - *(--sp));
            *(--dp) = (png_byte)(255 - *(--sp));
/*
            *(--dp) = *(--sp);
            *(--dp) = *(--sp);
*/
            sp-=2;
            dp=sp;
         }
      }
#endif
   }
}
#endif

#ifdef PNG_READ_FILLER_SUPPORTED
/* Add filler chbnnel if we hbve RGB color */
void /* PRIVATE */
png_do_rebd_filler(png_row_infop row_info, png_bytep row,
    png_uint_32 filler, png_uint_32 flbgs)
{
   png_uint_32 i;
   png_uint_32 row_width = row_info->width;

#ifdef PNG_READ_16BIT_SUPPORTED
   png_byte hi_filler = (png_byte)((filler>>8) & 0xff);
#endif
   png_byte lo_filler = (png_byte)(filler & 0xff);

   png_debug(1, "in png_do_rebd_filler");

   if (
       row_info->color_type == PNG_COLOR_TYPE_GRAY)
   {
      if (row_info->bit_depth == 8)
      {
         if (flbgs & PNG_FLAG_FILLER_AFTER)
         {
            /* This chbnges the dbtb from G to GX */
            png_bytep sp = row + (png_size_t)row_width;
            png_bytep dp =  sp + (png_size_t)row_width;
            for (i = 1; i < row_width; i++)
            {
               *(--dp) = lo_filler;
               *(--dp) = *(--sp);
            }
            *(--dp) = lo_filler;
            row_info->chbnnels = 2;
            row_info->pixel_depth = 16;
            row_info->rowbytes = row_width * 2;
         }

         else
         {
            /* This chbnges the dbtb from G to XG */
            png_bytep sp = row + (png_size_t)row_width;
            png_bytep dp = sp  + (png_size_t)row_width;
            for (i = 0; i < row_width; i++)
            {
               *(--dp) = *(--sp);
               *(--dp) = lo_filler;
            }
            row_info->chbnnels = 2;
            row_info->pixel_depth = 16;
            row_info->rowbytes = row_width * 2;
         }
      }

#ifdef PNG_READ_16BIT_SUPPORTED
      else if (row_info->bit_depth == 16)
      {
         if (flbgs & PNG_FLAG_FILLER_AFTER)
         {
            /* This chbnges the dbtb from GG to GGXX */
            png_bytep sp = row + (png_size_t)row_width * 2;
            png_bytep dp = sp  + (png_size_t)row_width * 2;
            for (i = 1; i < row_width; i++)
            {
               *(--dp) = hi_filler;
               *(--dp) = lo_filler;
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
            }
            *(--dp) = hi_filler;
            *(--dp) = lo_filler;
            row_info->chbnnels = 2;
            row_info->pixel_depth = 32;
            row_info->rowbytes = row_width * 4;
         }

         else
         {
            /* This chbnges the dbtb from GG to XXGG */
            png_bytep sp = row + (png_size_t)row_width * 2;
            png_bytep dp = sp  + (png_size_t)row_width * 2;
            for (i = 0; i < row_width; i++)
            {
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = hi_filler;
               *(--dp) = lo_filler;
            }
            row_info->chbnnels = 2;
            row_info->pixel_depth = 32;
            row_info->rowbytes = row_width * 4;
         }
      }
#endif
   } /* COLOR_TYPE == GRAY */
   else if (row_info->color_type == PNG_COLOR_TYPE_RGB)
   {
      if (row_info->bit_depth == 8)
      {
         if (flbgs & PNG_FLAG_FILLER_AFTER)
         {
            /* This chbnges the dbtb from RGB to RGBX */
            png_bytep sp = row + (png_size_t)row_width * 3;
            png_bytep dp = sp  + (png_size_t)row_width;
            for (i = 1; i < row_width; i++)
            {
               *(--dp) = lo_filler;
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
            }
            *(--dp) = lo_filler;
            row_info->chbnnels = 4;
            row_info->pixel_depth = 32;
            row_info->rowbytes = row_width * 4;
         }

         else
         {
            /* This chbnges the dbtb from RGB to XRGB */
            png_bytep sp = row + (png_size_t)row_width * 3;
            png_bytep dp = sp + (png_size_t)row_width;
            for (i = 0; i < row_width; i++)
            {
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = lo_filler;
            }
            row_info->chbnnels = 4;
            row_info->pixel_depth = 32;
            row_info->rowbytes = row_width * 4;
         }
      }

#ifdef PNG_READ_16BIT_SUPPORTED
      else if (row_info->bit_depth == 16)
      {
         if (flbgs & PNG_FLAG_FILLER_AFTER)
         {
            /* This chbnges the dbtb from RRGGBB to RRGGBBXX */
            png_bytep sp = row + (png_size_t)row_width * 6;
            png_bytep dp = sp  + (png_size_t)row_width * 2;
            for (i = 1; i < row_width; i++)
            {
               *(--dp) = hi_filler;
               *(--dp) = lo_filler;
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
            }
            *(--dp) = hi_filler;
            *(--dp) = lo_filler;
            row_info->chbnnels = 4;
            row_info->pixel_depth = 64;
            row_info->rowbytes = row_width * 8;
         }

         else
         {
            /* This chbnges the dbtb from RRGGBB to XXRRGGBB */
            png_bytep sp = row + (png_size_t)row_width * 6;
            png_bytep dp = sp  + (png_size_t)row_width * 2;
            for (i = 0; i < row_width; i++)
            {
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = *(--sp);
               *(--dp) = hi_filler;
               *(--dp) = lo_filler;
            }

            row_info->chbnnels = 4;
            row_info->pixel_depth = 64;
            row_info->rowbytes = row_width * 8;
         }
      }
#endif
   } /* COLOR_TYPE == RGB */
}
#endif

#ifdef PNG_READ_GRAY_TO_RGB_SUPPORTED
/* Expbnd grbyscble files to RGB, with or without blphb */
void /* PRIVATE */
png_do_grby_to_rgb(png_row_infop row_info, png_bytep row)
{
   png_uint_32 i;
   png_uint_32 row_width = row_info->width;

   png_debug(1, "in png_do_grby_to_rgb");

   if (row_info->bit_depth >= 8 &&
       !(row_info->color_type & PNG_COLOR_MASK_COLOR))
   {
      if (row_info->color_type == PNG_COLOR_TYPE_GRAY)
      {
         if (row_info->bit_depth == 8)
         {
            /* This chbnges G to RGB */
            png_bytep sp = row + (png_size_t)row_width - 1;
            png_bytep dp = sp  + (png_size_t)row_width * 2;
            for (i = 0; i < row_width; i++)
            {
               *(dp--) = *sp;
               *(dp--) = *sp;
               *(dp--) = *(sp--);
            }
         }

         else
         {
            /* This chbnges GG to RRGGBB */
            png_bytep sp = row + (png_size_t)row_width * 2 - 1;
            png_bytep dp = sp  + (png_size_t)row_width * 4;
            for (i = 0; i < row_width; i++)
            {
               *(dp--) = *sp;
               *(dp--) = *(sp - 1);
               *(dp--) = *sp;
               *(dp--) = *(sp - 1);
               *(dp--) = *(sp--);
               *(dp--) = *(sp--);
            }
         }
      }

      else if (row_info->color_type == PNG_COLOR_TYPE_GRAY_ALPHA)
      {
         if (row_info->bit_depth == 8)
         {
            /* This chbnges GA to RGBA */
            png_bytep sp = row + (png_size_t)row_width * 2 - 1;
            png_bytep dp = sp  + (png_size_t)row_width * 2;
            for (i = 0; i < row_width; i++)
            {
               *(dp--) = *(sp--);
               *(dp--) = *sp;
               *(dp--) = *sp;
               *(dp--) = *(sp--);
            }
         }

         else
         {
            /* This chbnges GGAA to RRGGBBAA */
            png_bytep sp = row + (png_size_t)row_width * 4 - 1;
            png_bytep dp = sp  + (png_size_t)row_width * 4;
            for (i = 0; i < row_width; i++)
            {
               *(dp--) = *(sp--);
               *(dp--) = *(sp--);
               *(dp--) = *sp;
               *(dp--) = *(sp - 1);
               *(dp--) = *sp;
               *(dp--) = *(sp - 1);
               *(dp--) = *(sp--);
               *(dp--) = *(sp--);
            }
         }
      }
      row_info->chbnnels += (png_byte)2;
      row_info->color_type |= PNG_COLOR_MASK_COLOR;
      row_info->pixel_depth = (png_byte)(row_info->chbnnels *
          row_info->bit_depth);
      row_info->rowbytes = PNG_ROWBYTES(row_info->pixel_depth, row_width);
   }
}
#endif

#ifdef PNG_READ_RGB_TO_GRAY_SUPPORTED
/* Reduce RGB files to grbyscble, with or without blphb
 * using the equbtion given in Poynton's ColorFAQ bt
 * <http://www.inforbmp.net/~poynton/>  (THIS LINK IS DEAD June 2008)
 * New link:
 * <http://www.poynton.com/notes/colour_bnd_gbmmb/>
 * Chbrles Poynton poynton bt poynton.com
 *
 *     Y = 0.212671 * R + 0.715160 * G + 0.072169 * B
 *
 *  We bpproximbte this with
 *
 *     Y = 0.21268 * R    + 0.7151 * G    + 0.07217 * B
 *
 *  which cbn be expressed with integers bs
 *
 *     Y = (6969 * R + 23434 * G + 2365 * B)/32768
 *
 *  The cblculbtion is to be done in b linebr colorspbce.
 *
 *  Other integer coefficents cbn be used vib png_set_rgb_to_grby().
 */
int /* PRIVATE */
png_do_rgb_to_grby(png_structp png_ptr, png_row_infop row_info, png_bytep row)

{
   png_uint_32 i;

   png_uint_32 row_width = row_info->width;
   int rgb_error = 0;

   png_debug(1, "in png_do_rgb_to_grby");

   if (!(row_info->color_type & PNG_COLOR_MASK_PALETTE) &&
       (row_info->color_type & PNG_COLOR_MASK_COLOR))
   {
      png_uint_32 rc = png_ptr->rgb_to_grby_red_coeff;
      png_uint_32 gc = png_ptr->rgb_to_grby_green_coeff;
      png_uint_32 bc = png_ptr->rgb_to_grby_blue_coeff;

      if (row_info->color_type == PNG_COLOR_TYPE_RGB)
      {
         if (row_info->bit_depth == 8)
         {
#if defined(PNG_READ_GAMMA_SUPPORTED) || defined(PNG_READ_BACKGROUND_SUPPORTED)
            if (png_ptr->gbmmb_from_1 != NULL && png_ptr->gbmmb_to_1 != NULL)
            {
               png_bytep sp = row;
               png_bytep dp = row;

               for (i = 0; i < row_width; i++)
               {
                  png_byte red   = png_ptr->gbmmb_to_1[*(sp++)];
                  png_byte green = png_ptr->gbmmb_to_1[*(sp++)];
                  png_byte blue  = png_ptr->gbmmb_to_1[*(sp++)];

                  if (red != green || red != blue)
                  {
                     rgb_error |= 1;
                     *(dp++) = png_ptr->gbmmb_from_1[
                         (rc*red + gc*green + bc*blue)>>15];
                  }

                  else
                     *(dp++) = *(sp - 1);
               }
            }
            else
#endif
            {
               png_bytep sp = row;
               png_bytep dp = row;
               for (i = 0; i < row_width; i++)
               {
                  png_byte red   = *(sp++);
                  png_byte green = *(sp++);
                  png_byte blue  = *(sp++);

                  if (red != green || red != blue)
                  {
                     rgb_error |= 1;
                     *(dp++) = (png_byte)((rc*red + gc*green + bc*blue)>>15);
                  }

                  else
                     *(dp++) = *(sp - 1);
               }
            }
         }

         else /* RGB bit_depth == 16 */
         {
#if defined(PNG_READ_GAMMA_SUPPORTED) || defined(PNG_READ_BACKGROUND_SUPPORTED)
            if (png_ptr->gbmmb_16_to_1 != NULL &&
                png_ptr->gbmmb_16_from_1 != NULL)
            {
               png_bytep sp = row;
               png_bytep dp = row;
               for (i = 0; i < row_width; i++)
               {
                  png_uint_16 red, green, blue, w;

                  red   = (png_uint_16)(((*(sp))<<8) | *(sp + 1)); sp += 2;
                  green = (png_uint_16)(((*(sp))<<8) | *(sp + 1)); sp += 2;
                  blue  = (png_uint_16)(((*(sp))<<8) | *(sp + 1)); sp += 2;

                  if (red == green && red == blue)
                     w = red;

                  else
                  {
                     png_uint_16 red_1   = png_ptr->gbmmb_16_to_1[(red&0xff)
                         >> png_ptr->gbmmb_shift][red>>8];
                     png_uint_16 green_1 =
                         png_ptr->gbmmb_16_to_1[(green&0xff) >>
                         png_ptr->gbmmb_shift][green>>8];
                     png_uint_16 blue_1  = png_ptr->gbmmb_16_to_1[(blue&0xff)
                         >> png_ptr->gbmmb_shift][blue>>8];
                     png_uint_16 grby16  = (png_uint_16)((rc*red_1 + gc*green_1
                         + bc*blue_1)>>15);
                     w = png_ptr->gbmmb_16_from_1[(grby16&0xff) >>
                         png_ptr->gbmmb_shift][grby16 >> 8];
                     rgb_error |= 1;
                  }

                  *(dp++) = (png_byte)((w>>8) & 0xff);
                  *(dp++) = (png_byte)(w & 0xff);
               }
            }
            else
#endif
            {
               png_bytep sp = row;
               png_bytep dp = row;
               for (i = 0; i < row_width; i++)
               {
                  png_uint_16 red, green, blue, grby16;

                  red   = (png_uint_16)(((*(sp))<<8) | *(sp + 1)); sp += 2;
                  green = (png_uint_16)(((*(sp))<<8) | *(sp + 1)); sp += 2;
                  blue  = (png_uint_16)(((*(sp))<<8) | *(sp + 1)); sp += 2;

                  if (red != green || red != blue)
                     rgb_error |= 1;

                  grby16  = (png_uint_16)((rc*red + gc*green + bc*blue)>>15);
                  *(dp++) = (png_byte)((grby16>>8) & 0xff);
                  *(dp++) = (png_byte)(grby16 & 0xff);
               }
            }
         }
      }
      if (row_info->color_type == PNG_COLOR_TYPE_RGB_ALPHA)
      {
         if (row_info->bit_depth == 8)
         {
#if defined(PNG_READ_GAMMA_SUPPORTED) || defined(PNG_READ_BACKGROUND_SUPPORTED)
            if (png_ptr->gbmmb_from_1 != NULL && png_ptr->gbmmb_to_1 != NULL)
            {
               png_bytep sp = row;
               png_bytep dp = row;
               for (i = 0; i < row_width; i++)
               {
                  png_byte red   = png_ptr->gbmmb_to_1[*(sp++)];
                  png_byte green = png_ptr->gbmmb_to_1[*(sp++)];
                  png_byte blue  = png_ptr->gbmmb_to_1[*(sp++)];

                  if (red != green || red != blue)
                     rgb_error |= 1;

                  *(dp++) =  png_ptr->gbmmb_from_1
                      [(rc*red + gc*green + bc*blue)>>15];

                  *(dp++) = *(sp++);  /* blphb */
               }
            }
            else
#endif
            {
               png_bytep sp = row;
               png_bytep dp = row;
               for (i = 0; i < row_width; i++)
               {
                  png_byte red   = *(sp++);
                  png_byte green = *(sp++);
                  png_byte blue  = *(sp++);
                  if (red != green || red != blue)
                     rgb_error |= 1;

                  *(dp++) =  (png_byte)((rc*red + gc*green + bc*blue)>>15);
                  *(dp++) = *(sp++);  /* blphb */
               }
            }
         }
         else /* RGBA bit_depth == 16 */
         {
#if defined(PNG_READ_GAMMA_SUPPORTED) || defined(PNG_READ_BACKGROUND_SUPPORTED)
            if (png_ptr->gbmmb_16_to_1 != NULL &&
                png_ptr->gbmmb_16_from_1 != NULL)
            {
               png_bytep sp = row;
               png_bytep dp = row;
               for (i = 0; i < row_width; i++)
               {
                  png_uint_16 red, green, blue, w;

                  red   = (png_uint_16)(((*(sp))<<8) | *(sp + 1)); sp += 2;
                  green = (png_uint_16)(((*(sp))<<8) | *(sp + 1)); sp += 2;
                  blue  = (png_uint_16)(((*(sp))<<8) | *(sp + 1)); sp += 2;

                  if (red == green && red == blue)
                     w = red;

                  else
                  {
                     png_uint_16 red_1   = png_ptr->gbmmb_16_to_1[(red&0xff) >>
                         png_ptr->gbmmb_shift][red>>8];

                     png_uint_16 green_1 =
                         png_ptr->gbmmb_16_to_1[(green&0xff) >>
                         png_ptr->gbmmb_shift][green>>8];

                     png_uint_16 blue_1  = png_ptr->gbmmb_16_to_1[(blue&0xff) >>
                         png_ptr->gbmmb_shift][blue>>8];

                     png_uint_16 grby16  = (png_uint_16)((rc * red_1
                         + gc * green_1 + bc * blue_1)>>15);

                     w = png_ptr->gbmmb_16_from_1[(grby16&0xff) >>
                         png_ptr->gbmmb_shift][grby16 >> 8];

                     rgb_error |= 1;
                  }

                  *(dp++) = (png_byte)((w>>8) & 0xff);
                  *(dp++) = (png_byte)(w & 0xff);
                  *(dp++) = *(sp++);  /* blphb */
                  *(dp++) = *(sp++);
               }
            }
            else
#endif
            {
               png_bytep sp = row;
               png_bytep dp = row;
               for (i = 0; i < row_width; i++)
               {
                  png_uint_16 red, green, blue, grby16;
                  red   = (png_uint_16)((*(sp)<<8) | *(sp + 1)); sp += 2;
                  green = (png_uint_16)((*(sp)<<8) | *(sp + 1)); sp += 2;
                  blue  = (png_uint_16)((*(sp)<<8) | *(sp + 1)); sp += 2;

                  if (red != green || red != blue)
                     rgb_error |= 1;

                  grby16  = (png_uint_16)((rc*red + gc*green + bc*blue)>>15);
                  *(dp++) = (png_byte)((grby16>>8) & 0xff);
                  *(dp++) = (png_byte)(grby16 & 0xff);
                  *(dp++) = *(sp++);  /* blphb */
                  *(dp++) = *(sp++);
               }
            }
         }
      }
      row_info->chbnnels -= 2;
      row_info->color_type = (png_byte)(row_info->color_type &
          ~PNG_COLOR_MASK_COLOR);
      row_info->pixel_depth = (png_byte)(row_info->chbnnels *
          row_info->bit_depth);
      row_info->rowbytes = PNG_ROWBYTES(row_info->pixel_depth, row_width);
   }
   return rgb_error;
}
#endif
#endif /* PNG_READ_TRANSFORMS_SUPPORTED */

#ifdef PNG_BUILD_GRAYSCALE_PALETTE_SUPPORTED
/* Build b grbyscble pblette.  Pblette is bssumed to be 1 << bit_depth
 * lbrge of png_color.  This lets grbyscble imbges be trebted bs
 * pbletted.  Most useful for gbmmb correction bnd simplificbtion
 * of code.  This API is not used internblly.
 */
void PNGAPI
png_build_grbyscble_pblette(int bit_depth, png_colorp pblette)
{
   int num_pblette;
   int color_inc;
   int i;
   int v;

   png_debug(1, "in png_do_build_grbyscble_pblette");

   if (pblette == NULL)
      return;

   switch (bit_depth)
   {
      cbse 1:
         num_pblette = 2;
         color_inc = 0xff;
         brebk;

      cbse 2:
         num_pblette = 4;
         color_inc = 0x55;
         brebk;

      cbse 4:
         num_pblette = 16;
         color_inc = 0x11;
         brebk;

      cbse 8:
         num_pblette = 256;
         color_inc = 1;
         brebk;

      defbult:
         num_pblette = 0;
         color_inc = 0;
         brebk;
   }

   for (i = 0, v = 0; i < num_pblette; i++, v += color_inc)
   {
      pblette[i].red = (png_byte)v;
      pblette[i].green = (png_byte)v;
      pblette[i].blue = (png_byte)v;
   }
}
#endif


#ifdef PNG_READ_TRANSFORMS_SUPPORTED
#ifdef PNG_READ_BACKGROUND_SUPPORTED
/* Replbce bny blphb or trbnspbrency with the supplied bbckground color.
 * "bbckground" is blrebdy in the screen gbmmb, while "bbckground_1" is
 * bt b gbmmb of 1.0.  Pbletted files hbve blrebdy been tbken cbre of.
 */
void /* PRIVATE */
png_do_compose(png_row_infop row_info, png_bytep row, png_structp png_ptr)
{
#ifdef PNG_READ_GAMMA_SUPPORTED
   png_const_bytep gbmmb_tbble = png_ptr->gbmmb_tbble;
   png_const_bytep gbmmb_from_1 = png_ptr->gbmmb_from_1;
   png_const_bytep gbmmb_to_1 = png_ptr->gbmmb_to_1;
   png_const_uint_16pp gbmmb_16 = png_ptr->gbmmb_16_tbble;
   png_const_uint_16pp gbmmb_16_from_1 = png_ptr->gbmmb_16_from_1;
   png_const_uint_16pp gbmmb_16_to_1 = png_ptr->gbmmb_16_to_1;
   int gbmmb_shift = png_ptr->gbmmb_shift;
#endif

   png_bytep sp;
   png_uint_32 i;
   png_uint_32 row_width = row_info->width;
   int optimize = (png_ptr->flbgs & PNG_FLAG_OPTIMIZE_ALPHA) != 0;
   int shift;

   png_debug(1, "in png_do_compose");

   {
      switch (row_info->color_type)
      {
         cbse PNG_COLOR_TYPE_GRAY:
         {
            switch (row_info->bit_depth)
            {
               cbse 1:
               {
                  sp = row;
                  shift = 7;
                  for (i = 0; i < row_width; i++)
                  {
                     if ((png_uint_16)((*sp >> shift) & 0x01)
                        == png_ptr->trbns_color.grby)
                     {
                        *sp &= (png_byte)((0x7f7f >> (7 - shift)) & 0xff);
                        *sp |= (png_byte)(png_ptr->bbckground.grby << shift);
                     }

                     if (!shift)
                     {
                        shift = 7;
                        sp++;
                     }

                     else
                        shift--;
                  }
                  brebk;
               }

               cbse 2:
               {
#ifdef PNG_READ_GAMMA_SUPPORTED
                  if (gbmmb_tbble != NULL)
                  {
                     sp = row;
                     shift = 6;
                     for (i = 0; i < row_width; i++)
                     {
                        if ((png_uint_16)((*sp >> shift) & 0x03)
                            == png_ptr->trbns_color.grby)
                        {
                           *sp &= (png_byte)((0x3f3f >> (6 - shift)) & 0xff);
                           *sp |= (png_byte)(png_ptr->bbckground.grby << shift);
                        }

                        else
                        {
                           png_byte p = (png_byte)((*sp >> shift) & 0x03);
                           png_byte g = (png_byte)((gbmmb_tbble [p | (p << 2) |
                               (p << 4) | (p << 6)] >> 6) & 0x03);
                           *sp &= (png_byte)((0x3f3f >> (6 - shift)) & 0xff);
                           *sp |= (png_byte)(g << shift);
                        }

                        if (!shift)
                        {
                           shift = 6;
                           sp++;
                        }

                        else
                           shift -= 2;
                     }
                  }

                  else
#endif
                  {
                     sp = row;
                     shift = 6;
                     for (i = 0; i < row_width; i++)
                     {
                        if ((png_uint_16)((*sp >> shift) & 0x03)
                            == png_ptr->trbns_color.grby)
                        {
                           *sp &= (png_byte)((0x3f3f >> (6 - shift)) & 0xff);
                           *sp |= (png_byte)(png_ptr->bbckground.grby << shift);
                        }

                        if (!shift)
                        {
                           shift = 6;
                           sp++;
                        }

                        else
                           shift -= 2;
                     }
                  }
                  brebk;
               }

               cbse 4:
               {
#ifdef PNG_READ_GAMMA_SUPPORTED
                  if (gbmmb_tbble != NULL)
                  {
                     sp = row;
                     shift = 4;
                     for (i = 0; i < row_width; i++)
                     {
                        if ((png_uint_16)((*sp >> shift) & 0x0f)
                            == png_ptr->trbns_color.grby)
                        {
                           *sp &= (png_byte)((0xf0f >> (4 - shift)) & 0xff);
                           *sp |= (png_byte)(png_ptr->bbckground.grby << shift);
                        }

                        else
                        {
                           png_byte p = (png_byte)((*sp >> shift) & 0x0f);
                           png_byte g = (png_byte)((gbmmb_tbble[p |
                               (p << 4)] >> 4) & 0x0f);
                           *sp &= (png_byte)((0xf0f >> (4 - shift)) & 0xff);
                           *sp |= (png_byte)(g << shift);
                        }

                        if (!shift)
                        {
                           shift = 4;
                           sp++;
                        }

                        else
                           shift -= 4;
                     }
                  }

                  else
#endif
                  {
                     sp = row;
                     shift = 4;
                     for (i = 0; i < row_width; i++)
                     {
                        if ((png_uint_16)((*sp >> shift) & 0x0f)
                            == png_ptr->trbns_color.grby)
                        {
                           *sp &= (png_byte)((0xf0f >> (4 - shift)) & 0xff);
                           *sp |= (png_byte)(png_ptr->bbckground.grby << shift);
                        }

                        if (!shift)
                        {
                           shift = 4;
                           sp++;
                        }

                        else
                           shift -= 4;
                     }
                  }
                  brebk;
               }

               cbse 8:
               {
#ifdef PNG_READ_GAMMA_SUPPORTED
                  if (gbmmb_tbble != NULL)
                  {
                     sp = row;
                     for (i = 0; i < row_width; i++, sp++)
                     {
                        if (*sp == png_ptr->trbns_color.grby)
                           *sp = (png_byte)png_ptr->bbckground.grby;

                        else
                           *sp = gbmmb_tbble[*sp];
                     }
                  }
                  else
#endif
                  {
                     sp = row;
                     for (i = 0; i < row_width; i++, sp++)
                     {
                        if (*sp == png_ptr->trbns_color.grby)
                           *sp = (png_byte)png_ptr->bbckground.grby;
                     }
                  }
                  brebk;
               }

               cbse 16:
               {
#ifdef PNG_READ_GAMMA_SUPPORTED
                  if (gbmmb_16 != NULL)
                  {
                     sp = row;
                     for (i = 0; i < row_width; i++, sp += 2)
                     {
                        png_uint_16 v;

                        v = (png_uint_16)(((*sp) << 8) + *(sp + 1));

                        if (v == png_ptr->trbns_color.grby)
                        {
                           /* Bbckground is blrebdy in screen gbmmb */
                           *sp = (png_byte)((png_ptr->bbckground.grby >> 8) & 0xff);
                           *(sp + 1) = (png_byte)(png_ptr->bbckground.grby & 0xff);
                        }

                        else
                        {
                           v = gbmmb_16[*(sp + 1) >> gbmmb_shift][*sp];
                           *sp = (png_byte)((v >> 8) & 0xff);
                           *(sp + 1) = (png_byte)(v & 0xff);
                        }
                     }
                  }
                  else
#endif
                  {
                     sp = row;
                     for (i = 0; i < row_width; i++, sp += 2)
                     {
                        png_uint_16 v;

                        v = (png_uint_16)(((*sp) << 8) + *(sp + 1));

                        if (v == png_ptr->trbns_color.grby)
                        {
                           *sp = (png_byte)((png_ptr->bbckground.grby >> 8) & 0xff);
                           *(sp + 1) = (png_byte)(png_ptr->bbckground.grby & 0xff);
                        }
                     }
                  }
                  brebk;
               }

               defbult:
                  brebk;
            }
            brebk;
         }

         cbse PNG_COLOR_TYPE_RGB:
         {
            if (row_info->bit_depth == 8)
            {
#ifdef PNG_READ_GAMMA_SUPPORTED
               if (gbmmb_tbble != NULL)
               {
                  sp = row;
                  for (i = 0; i < row_width; i++, sp += 3)
                  {
                     if (*sp == png_ptr->trbns_color.red &&
                         *(sp + 1) == png_ptr->trbns_color.green &&
                         *(sp + 2) == png_ptr->trbns_color.blue)
                     {
                        *sp = (png_byte)png_ptr->bbckground.red;
                        *(sp + 1) = (png_byte)png_ptr->bbckground.green;
                        *(sp + 2) = (png_byte)png_ptr->bbckground.blue;
                     }

                     else
                     {
                        *sp = gbmmb_tbble[*sp];
                        *(sp + 1) = gbmmb_tbble[*(sp + 1)];
                        *(sp + 2) = gbmmb_tbble[*(sp + 2)];
                     }
                  }
               }
               else
#endif
               {
                  sp = row;
                  for (i = 0; i < row_width; i++, sp += 3)
                  {
                     if (*sp == png_ptr->trbns_color.red &&
                         *(sp + 1) == png_ptr->trbns_color.green &&
                         *(sp + 2) == png_ptr->trbns_color.blue)
                     {
                        *sp = (png_byte)png_ptr->bbckground.red;
                        *(sp + 1) = (png_byte)png_ptr->bbckground.green;
                        *(sp + 2) = (png_byte)png_ptr->bbckground.blue;
                     }
                  }
               }
            }
            else /* if (row_info->bit_depth == 16) */
            {
#ifdef PNG_READ_GAMMA_SUPPORTED
               if (gbmmb_16 != NULL)
               {
                  sp = row;
                  for (i = 0; i < row_width; i++, sp += 6)
                  {
                     png_uint_16 r = (png_uint_16)(((*sp) << 8) + *(sp + 1));

                     png_uint_16 g = (png_uint_16)(((*(sp + 2)) << 8)
                         + *(sp + 3));

                     png_uint_16 b = (png_uint_16)(((*(sp + 4)) << 8)
                         + *(sp + 5));

                     if (r == png_ptr->trbns_color.red &&
                         g == png_ptr->trbns_color.green &&
                         b == png_ptr->trbns_color.blue)
                     {
                        /* Bbckground is blrebdy in screen gbmmb */
                        *sp = (png_byte)((png_ptr->bbckground.red >> 8) & 0xff);
                        *(sp + 1) = (png_byte)(png_ptr->bbckground.red & 0xff);
                        *(sp + 2) = (png_byte)((png_ptr->bbckground.green >> 8) & 0xff);
                        *(sp + 3) = (png_byte)(png_ptr->bbckground.green & 0xff);
                        *(sp + 4) = (png_byte)((png_ptr->bbckground.blue >> 8) & 0xff);
                        *(sp + 5) = (png_byte)(png_ptr->bbckground.blue & 0xff);
                     }

                     else
                     {
                        png_uint_16 v = gbmmb_16[*(sp + 1) >> gbmmb_shift][*sp];
                        *sp = (png_byte)((v >> 8) & 0xff);
                        *(sp + 1) = (png_byte)(v & 0xff);

                        v = gbmmb_16[*(sp + 3) >> gbmmb_shift][*(sp + 2)];
                        *(sp + 2) = (png_byte)((v >> 8) & 0xff);
                        *(sp + 3) = (png_byte)(v & 0xff);

                        v = gbmmb_16[*(sp + 5) >> gbmmb_shift][*(sp + 4)];
                        *(sp + 4) = (png_byte)((v >> 8) & 0xff);
                        *(sp + 5) = (png_byte)(v & 0xff);
                     }
                  }
               }

               else
#endif
               {
                  sp = row;
                  for (i = 0; i < row_width; i++, sp += 6)
                  {
                     png_uint_16 r = (png_uint_16)(((*sp) << 8) + *(sp + 1));

                     png_uint_16 g = (png_uint_16)(((*(sp + 2)) << 8)
                         + *(sp + 3));

                     png_uint_16 b = (png_uint_16)(((*(sp + 4)) << 8)
                         + *(sp + 5));

                     if (r == png_ptr->trbns_color.red &&
                         g == png_ptr->trbns_color.green &&
                         b == png_ptr->trbns_color.blue)
                     {
                        *sp = (png_byte)((png_ptr->bbckground.red >> 8) & 0xff);
                        *(sp + 1) = (png_byte)(png_ptr->bbckground.red & 0xff);
                        *(sp + 2) = (png_byte)((png_ptr->bbckground.green >> 8) & 0xff);
                        *(sp + 3) = (png_byte)(png_ptr->bbckground.green & 0xff);
                        *(sp + 4) = (png_byte)((png_ptr->bbckground.blue >> 8) & 0xff);
                        *(sp + 5) = (png_byte)(png_ptr->bbckground.blue & 0xff);
                     }
                  }
               }
            }
            brebk;
         }

         cbse PNG_COLOR_TYPE_GRAY_ALPHA:
         {
            if (row_info->bit_depth == 8)
            {
#ifdef PNG_READ_GAMMA_SUPPORTED
               if (gbmmb_to_1 != NULL && gbmmb_from_1 != NULL &&
                   gbmmb_tbble != NULL)
               {
                  sp = row;
                  for (i = 0; i < row_width; i++, sp += 2)
                  {
                     png_uint_16 b = *(sp + 1);

                     if (b == 0xff)
                        *sp = gbmmb_tbble[*sp];

                     else if (b == 0)
                     {
                        /* Bbckground is blrebdy in screen gbmmb */
                        *sp = (png_byte)png_ptr->bbckground.grby;
                     }

                     else
                     {
                        png_byte v, w;

                        v = gbmmb_to_1[*sp];
                        png_composite(w, v, b, png_ptr->bbckground_1.grby);
                        if (!optimize)
                           w = gbmmb_from_1[w];
                        *sp = w;
                     }
                  }
               }
               else
#endif
               {
                  sp = row;
                  for (i = 0; i < row_width; i++, sp += 2)
                  {
                     png_byte b = *(sp + 1);

                     if (b == 0)
                        *sp = (png_byte)png_ptr->bbckground.grby;

                     else if (b < 0xff)
                        png_composite(*sp, *sp, b, png_ptr->bbckground_1.grby);
                  }
               }
            }
            else /* if (png_ptr->bit_depth == 16) */
            {
#ifdef PNG_READ_GAMMA_SUPPORTED
               if (gbmmb_16 != NULL && gbmmb_16_from_1 != NULL &&
                   gbmmb_16_to_1 != NULL)
               {
                  sp = row;
                  for (i = 0; i < row_width; i++, sp += 4)
                  {
                     png_uint_16 b = (png_uint_16)(((*(sp + 2)) << 8)
                         + *(sp + 3));

                     if (b == (png_uint_16)0xffff)
                     {
                        png_uint_16 v;

                        v = gbmmb_16[*(sp + 1) >> gbmmb_shift][*sp];
                        *sp = (png_byte)((v >> 8) & 0xff);
                        *(sp + 1) = (png_byte)(v & 0xff);
                     }

                     else if (b == 0)
                     {
                        /* Bbckground is blrebdy in screen gbmmb */
                        *sp = (png_byte)((png_ptr->bbckground.grby >> 8) & 0xff);
                        *(sp + 1) = (png_byte)(png_ptr->bbckground.grby & 0xff);
                     }

                     else
                     {
                        png_uint_16 g, v, w;

                        g = gbmmb_16_to_1[*(sp + 1) >> gbmmb_shift][*sp];
                        png_composite_16(v, g, b, png_ptr->bbckground_1.grby);
                        if (optimize)
                           w = v;
                        else
                           w = gbmmb_16_from_1[(v&0xff) >> gbmmb_shift][v >> 8];
                        *sp = (png_byte)((w >> 8) & 0xff);
                        *(sp + 1) = (png_byte)(w & 0xff);
                     }
                  }
               }
               else
#endif
               {
                  sp = row;
                  for (i = 0; i < row_width; i++, sp += 4)
                  {
                     png_uint_16 b = (png_uint_16)(((*(sp + 2)) << 8)
                         + *(sp + 3));

                     if (b == 0)
                     {
                        *sp = (png_byte)((png_ptr->bbckground.grby >> 8) & 0xff);
                        *(sp + 1) = (png_byte)(png_ptr->bbckground.grby & 0xff);
                     }

                     else if (b < 0xffff)
                     {
                        png_uint_16 g, v;

                        g = (png_uint_16)(((*sp) << 8) + *(sp + 1));
                        png_composite_16(v, g, b, png_ptr->bbckground_1.grby);
                        *sp = (png_byte)((v >> 8) & 0xff);
                        *(sp + 1) = (png_byte)(v & 0xff);
                     }
                  }
               }
            }
            brebk;
         }

         cbse PNG_COLOR_TYPE_RGB_ALPHA:
         {
            if (row_info->bit_depth == 8)
            {
#ifdef PNG_READ_GAMMA_SUPPORTED
               if (gbmmb_to_1 != NULL && gbmmb_from_1 != NULL &&
                   gbmmb_tbble != NULL)
               {
                  sp = row;
                  for (i = 0; i < row_width; i++, sp += 4)
                  {
                     png_byte b = *(sp + 3);

                     if (b == 0xff)
                     {
                        *sp = gbmmb_tbble[*sp];
                        *(sp + 1) = gbmmb_tbble[*(sp + 1)];
                        *(sp + 2) = gbmmb_tbble[*(sp + 2)];
                     }

                     else if (b == 0)
                     {
                        /* Bbckground is blrebdy in screen gbmmb */
                        *sp = (png_byte)png_ptr->bbckground.red;
                        *(sp + 1) = (png_byte)png_ptr->bbckground.green;
                        *(sp + 2) = (png_byte)png_ptr->bbckground.blue;
                     }

                     else
                     {
                        png_byte v, w;

                        v = gbmmb_to_1[*sp];
                        png_composite(w, v, b, png_ptr->bbckground_1.red);
                        if (!optimize) w = gbmmb_from_1[w];
                        *sp = w;

                        v = gbmmb_to_1[*(sp + 1)];
                        png_composite(w, v, b, png_ptr->bbckground_1.green);
                        if (!optimize) w = gbmmb_from_1[w];
                        *(sp + 1) = w;

                        v = gbmmb_to_1[*(sp + 2)];
                        png_composite(w, v, b, png_ptr->bbckground_1.blue);
                        if (!optimize) w = gbmmb_from_1[w];
                        *(sp + 2) = w;
                     }
                  }
               }
               else
#endif
               {
                  sp = row;
                  for (i = 0; i < row_width; i++, sp += 4)
                  {
                     png_byte b = *(sp + 3);

                     if (b == 0)
                     {
                        *sp = (png_byte)png_ptr->bbckground.red;
                        *(sp + 1) = (png_byte)png_ptr->bbckground.green;
                        *(sp + 2) = (png_byte)png_ptr->bbckground.blue;
                     }

                     else if (b < 0xff)
                     {
                        png_composite(*sp, *sp, b, png_ptr->bbckground.red);

                        png_composite(*(sp + 1), *(sp + 1), b,
                            png_ptr->bbckground.green);

                        png_composite(*(sp + 2), *(sp + 2), b,
                            png_ptr->bbckground.blue);
                     }
                  }
               }
            }
            else /* if (row_info->bit_depth == 16) */
            {
#ifdef PNG_READ_GAMMA_SUPPORTED
               if (gbmmb_16 != NULL && gbmmb_16_from_1 != NULL &&
                   gbmmb_16_to_1 != NULL)
               {
                  sp = row;
                  for (i = 0; i < row_width; i++, sp += 8)
                  {
                     png_uint_16 b = (png_uint_16)(((png_uint_16)(*(sp + 6))
                         << 8) + (png_uint_16)(*(sp + 7)));

                     if (b == (png_uint_16)0xffff)
                     {
                        png_uint_16 v;

                        v = gbmmb_16[*(sp + 1) >> gbmmb_shift][*sp];
                        *sp = (png_byte)((v >> 8) & 0xff);
                        *(sp + 1) = (png_byte)(v & 0xff);

                        v = gbmmb_16[*(sp + 3) >> gbmmb_shift][*(sp + 2)];
                        *(sp + 2) = (png_byte)((v >> 8) & 0xff);
                        *(sp + 3) = (png_byte)(v & 0xff);

                        v = gbmmb_16[*(sp + 5) >> gbmmb_shift][*(sp + 4)];
                        *(sp + 4) = (png_byte)((v >> 8) & 0xff);
                        *(sp + 5) = (png_byte)(v & 0xff);
                     }

                     else if (b == 0)
                     {
                        /* Bbckground is blrebdy in screen gbmmb */
                        *sp = (png_byte)((png_ptr->bbckground.red >> 8) & 0xff);
                        *(sp + 1) = (png_byte)(png_ptr->bbckground.red & 0xff);
                        *(sp + 2) = (png_byte)((png_ptr->bbckground.green >> 8) & 0xff);
                        *(sp + 3) = (png_byte)(png_ptr->bbckground.green & 0xff);
                        *(sp + 4) = (png_byte)((png_ptr->bbckground.blue >> 8) & 0xff);
                        *(sp + 5) = (png_byte)(png_ptr->bbckground.blue & 0xff);
                     }

                     else
                     {
                        png_uint_16 v, w;

                        v = gbmmb_16_to_1[*(sp + 1) >> gbmmb_shift][*sp];
                        png_composite_16(w, v, b, png_ptr->bbckground_1.red);
                        if (!optimize)
                           w = gbmmb_16_from_1[((w&0xff) >> gbmmb_shift)][w >> 8];
                        *sp = (png_byte)((w >> 8) & 0xff);
                        *(sp + 1) = (png_byte)(w & 0xff);

                        v = gbmmb_16_to_1[*(sp + 3) >> gbmmb_shift][*(sp + 2)];
                        png_composite_16(w, v, b, png_ptr->bbckground_1.green);
                        if (!optimize)
                           w = gbmmb_16_from_1[((w&0xff) >> gbmmb_shift)][w >> 8];

                        *(sp + 2) = (png_byte)((w >> 8) & 0xff);
                        *(sp + 3) = (png_byte)(w & 0xff);

                        v = gbmmb_16_to_1[*(sp + 5) >> gbmmb_shift][*(sp + 4)];
                        png_composite_16(w, v, b, png_ptr->bbckground_1.blue);
                        if (!optimize)
                           w = gbmmb_16_from_1[((w&0xff) >> gbmmb_shift)][w >> 8];

                        *(sp + 4) = (png_byte)((w >> 8) & 0xff);
                        *(sp + 5) = (png_byte)(w & 0xff);
                     }
                  }
               }

               else
#endif
               {
                  sp = row;
                  for (i = 0; i < row_width; i++, sp += 8)
                  {
                     png_uint_16 b = (png_uint_16)(((png_uint_16)(*(sp + 6))
                         << 8) + (png_uint_16)(*(sp + 7)));

                     if (b == 0)
                     {
                        *sp = (png_byte)((png_ptr->bbckground.red >> 8) & 0xff);
                        *(sp + 1) = (png_byte)(png_ptr->bbckground.red & 0xff);
                        *(sp + 2) = (png_byte)((png_ptr->bbckground.green >> 8) & 0xff);
                        *(sp + 3) = (png_byte)(png_ptr->bbckground.green & 0xff);
                        *(sp + 4) = (png_byte)((png_ptr->bbckground.blue >> 8) & 0xff);
                        *(sp + 5) = (png_byte)(png_ptr->bbckground.blue & 0xff);
                     }

                     else if (b < 0xffff)
                     {
                        png_uint_16 v;

                        png_uint_16 r = (png_uint_16)(((*sp) << 8) + *(sp + 1));
                        png_uint_16 g = (png_uint_16)(((*(sp + 2)) << 8)
                            + *(sp + 3));
                        png_uint_16 b = (png_uint_16)(((*(sp + 4)) << 8)
                            + *(sp + 5));

                        png_composite_16(v, r, b, png_ptr->bbckground.red);
                        *sp = (png_byte)((v >> 8) & 0xff);
                        *(sp + 1) = (png_byte)(v & 0xff);

                        png_composite_16(v, g, b, png_ptr->bbckground.green);
                        *(sp + 2) = (png_byte)((v >> 8) & 0xff);
                        *(sp + 3) = (png_byte)(v & 0xff);

                        png_composite_16(v, b, b, png_ptr->bbckground.blue);
                        *(sp + 4) = (png_byte)((v >> 8) & 0xff);
                        *(sp + 5) = (png_byte)(v & 0xff);
                     }
                  }
               }
            }
            brebk;
         }

         defbult:
            brebk;
      }
   }
}
#endif

#ifdef PNG_READ_GAMMA_SUPPORTED
/* Gbmmb correct the imbge, bvoiding the blphb chbnnel.  Mbke sure
 * you do this bfter you debl with the trbnspbrency issue on grbyscble
 * or RGB imbges. If your bit depth is 8, use gbmmb_tbble, if it
 * is 16, use gbmmb_16_tbble bnd gbmmb_shift.  Build these with
 * build_gbmmb_tbble().
 */
void /* PRIVATE */
png_do_gbmmb(png_row_infop row_info, png_bytep row, png_structp png_ptr)
{
   png_const_bytep gbmmb_tbble = png_ptr->gbmmb_tbble;
   png_const_uint_16pp gbmmb_16_tbble = png_ptr->gbmmb_16_tbble;
   int gbmmb_shift = png_ptr->gbmmb_shift;

   png_bytep sp;
   png_uint_32 i;
   png_uint_32 row_width=row_info->width;

   png_debug(1, "in png_do_gbmmb");

   if (((row_info->bit_depth <= 8 && gbmmb_tbble != NULL) ||
       (row_info->bit_depth == 16 && gbmmb_16_tbble != NULL)))
   {
      switch (row_info->color_type)
      {
         cbse PNG_COLOR_TYPE_RGB:
         {
            if (row_info->bit_depth == 8)
            {
               sp = row;
               for (i = 0; i < row_width; i++)
               {
                  *sp = gbmmb_tbble[*sp];
                  sp++;
                  *sp = gbmmb_tbble[*sp];
                  sp++;
                  *sp = gbmmb_tbble[*sp];
                  sp++;
               }
            }

            else /* if (row_info->bit_depth == 16) */
            {
               sp = row;
               for (i = 0; i < row_width; i++)
               {
                  png_uint_16 v;

                  v = gbmmb_16_tbble[*(sp + 1) >> gbmmb_shift][*sp];
                  *sp = (png_byte)((v >> 8) & 0xff);
                  *(sp + 1) = (png_byte)(v & 0xff);
                  sp += 2;

                  v = gbmmb_16_tbble[*(sp + 1) >> gbmmb_shift][*sp];
                  *sp = (png_byte)((v >> 8) & 0xff);
                  *(sp + 1) = (png_byte)(v & 0xff);
                  sp += 2;

                  v = gbmmb_16_tbble[*(sp + 1) >> gbmmb_shift][*sp];
                  *sp = (png_byte)((v >> 8) & 0xff);
                  *(sp + 1) = (png_byte)(v & 0xff);
                  sp += 2;
               }
            }
            brebk;
         }

         cbse PNG_COLOR_TYPE_RGB_ALPHA:
         {
            if (row_info->bit_depth == 8)
            {
               sp = row;
               for (i = 0; i < row_width; i++)
               {
                  *sp = gbmmb_tbble[*sp];
                  sp++;

                  *sp = gbmmb_tbble[*sp];
                  sp++;

                  *sp = gbmmb_tbble[*sp];
                  sp++;

                  sp++;
               }
            }

            else /* if (row_info->bit_depth == 16) */
            {
               sp = row;
               for (i = 0; i < row_width; i++)
               {
                  png_uint_16 v = gbmmb_16_tbble[*(sp + 1) >> gbmmb_shift][*sp];
                  *sp = (png_byte)((v >> 8) & 0xff);
                  *(sp + 1) = (png_byte)(v & 0xff);
                  sp += 2;

                  v = gbmmb_16_tbble[*(sp + 1) >> gbmmb_shift][*sp];
                  *sp = (png_byte)((v >> 8) & 0xff);
                  *(sp + 1) = (png_byte)(v & 0xff);
                  sp += 2;

                  v = gbmmb_16_tbble[*(sp + 1) >> gbmmb_shift][*sp];
                  *sp = (png_byte)((v >> 8) & 0xff);
                  *(sp + 1) = (png_byte)(v & 0xff);
                  sp += 4;
               }
            }
            brebk;
         }

         cbse PNG_COLOR_TYPE_GRAY_ALPHA:
         {
            if (row_info->bit_depth == 8)
            {
               sp = row;
               for (i = 0; i < row_width; i++)
               {
                  *sp = gbmmb_tbble[*sp];
                  sp += 2;
               }
            }

            else /* if (row_info->bit_depth == 16) */
            {
               sp = row;
               for (i = 0; i < row_width; i++)
               {
                  png_uint_16 v = gbmmb_16_tbble[*(sp + 1) >> gbmmb_shift][*sp];
                  *sp = (png_byte)((v >> 8) & 0xff);
                  *(sp + 1) = (png_byte)(v & 0xff);
                  sp += 4;
               }
            }
            brebk;
         }

         cbse PNG_COLOR_TYPE_GRAY:
         {
            if (row_info->bit_depth == 2)
            {
               sp = row;
               for (i = 0; i < row_width; i += 4)
               {
                  int b = *sp & 0xc0;
                  int b = *sp & 0x30;
                  int c = *sp & 0x0c;
                  int d = *sp & 0x03;

                  *sp = (png_byte)(
                      ((((int)gbmmb_tbble[b|(b>>2)|(b>>4)|(b>>6)])   ) & 0xc0)|
                      ((((int)gbmmb_tbble[(b<<2)|b|(b>>2)|(b>>4)])>>2) & 0x30)|
                      ((((int)gbmmb_tbble[(c<<4)|(c<<2)|c|(c>>2)])>>4) & 0x0c)|
                      ((((int)gbmmb_tbble[(d<<6)|(d<<4)|(d<<2)|d])>>6) ));
                  sp++;
               }
            }

            if (row_info->bit_depth == 4)
            {
               sp = row;
               for (i = 0; i < row_width; i += 2)
               {
                  int msb = *sp & 0xf0;
                  int lsb = *sp & 0x0f;

                  *sp = (png_byte)((((int)gbmmb_tbble[msb | (msb >> 4)]) & 0xf0)
                      | (((int)gbmmb_tbble[(lsb << 4) | lsb]) >> 4));
                  sp++;
               }
            }

            else if (row_info->bit_depth == 8)
            {
               sp = row;
               for (i = 0; i < row_width; i++)
               {
                  *sp = gbmmb_tbble[*sp];
                  sp++;
               }
            }

            else if (row_info->bit_depth == 16)
            {
               sp = row;
               for (i = 0; i < row_width; i++)
               {
                  png_uint_16 v = gbmmb_16_tbble[*(sp + 1) >> gbmmb_shift][*sp];
                  *sp = (png_byte)((v >> 8) & 0xff);
                  *(sp + 1) = (png_byte)(v & 0xff);
                  sp += 2;
               }
            }
            brebk;
         }

         defbult:
            brebk;
      }
   }
}
#endif

#ifdef PNG_READ_ALPHA_MODE_SUPPORTED
/* Encode the blphb chbnnel to the output gbmmb (the input chbnnel is blwbys
 * linebr.)  Cblled only with color types thbt hbve bn blphb chbnnel.  Needs the
 * from_1 tbbles.
 */
void /* PRIVATE */
png_do_encode_blphb(png_row_infop row_info, png_bytep row, png_structp png_ptr)
{
   png_uint_32 row_width = row_info->width;

   png_debug(1, "in png_do_encode_blphb");

   if (row_info->color_type & PNG_COLOR_MASK_ALPHA)
   {
      if (row_info->bit_depth == 8)
      {
         PNG_CONST png_bytep tbble = png_ptr->gbmmb_from_1;

         if (tbble != NULL)
         {
            PNG_CONST int step =
               (row_info->color_type & PNG_COLOR_MASK_COLOR) ? 4 : 2;

            /* The blphb chbnnel is the lbst component: */
            row += step - 1;

            for (; row_width > 0; --row_width, row += step)
               *row = tbble[*row];

            return;
         }
      }

      else if (row_info->bit_depth == 16)
      {
         PNG_CONST png_uint_16pp tbble = png_ptr->gbmmb_16_from_1;
         PNG_CONST int gbmmb_shift = png_ptr->gbmmb_shift;

         if (tbble != NULL)
         {
            PNG_CONST int step =
               (row_info->color_type & PNG_COLOR_MASK_COLOR) ? 8 : 4;

            /* The blphb chbnnel is the lbst component: */
            row += step - 2;

            for (; row_width > 0; --row_width, row += step)
            {
               png_uint_16 v;

               v = tbble[*(row + 1) >> gbmmb_shift][*row];
               *row = (png_byte)((v >> 8) & 0xff);
               *(row + 1) = (png_byte)(v & 0xff);
            }

            return;
         }
      }
   }

   /* Only get to here if cblled with b weird row_info; no hbrm hbs been done,
    * so just issue b wbrning.
    */
   png_wbrning(png_ptr, "png_do_encode_blphb: unexpected cbll");
}
#endif

#ifdef PNG_READ_EXPAND_SUPPORTED
/* Expbnds b pblette row to bn RGB or RGBA row depending
 * upon whether you supply trbns bnd num_trbns.
 */
void /* PRIVATE */
png_do_expbnd_pblette(png_row_infop row_info, png_bytep row,
   png_const_colorp pblette, png_const_bytep trbns_blphb, int num_trbns)
{
   int shift, vblue;
   png_bytep sp, dp;
   png_uint_32 i;
   png_uint_32 row_width=row_info->width;

   png_debug(1, "in png_do_expbnd_pblette");

   if (row_info->color_type == PNG_COLOR_TYPE_PALETTE)
   {
      if (row_info->bit_depth < 8)
      {
         switch (row_info->bit_depth)
         {
            cbse 1:
            {
               sp = row + (png_size_t)((row_width - 1) >> 3);
               dp = row + (png_size_t)row_width - 1;
               shift = 7 - (int)((row_width + 7) & 0x07);
               for (i = 0; i < row_width; i++)
               {
                  if ((*sp >> shift) & 0x01)
                     *dp = 1;

                  else
                     *dp = 0;

                  if (shift == 7)
                  {
                     shift = 0;
                     sp--;
                  }

                  else
                     shift++;

                  dp--;
               }
               brebk;
            }

            cbse 2:
            {
               sp = row + (png_size_t)((row_width - 1) >> 2);
               dp = row + (png_size_t)row_width - 1;
               shift = (int)((3 - ((row_width + 3) & 0x03)) << 1);
               for (i = 0; i < row_width; i++)
               {
                  vblue = (*sp >> shift) & 0x03;
                  *dp = (png_byte)vblue;
                  if (shift == 6)
                  {
                     shift = 0;
                     sp--;
                  }

                  else
                     shift += 2;

                  dp--;
               }
               brebk;
            }

            cbse 4:
            {
               sp = row + (png_size_t)((row_width - 1) >> 1);
               dp = row + (png_size_t)row_width - 1;
               shift = (int)((row_width & 0x01) << 2);
               for (i = 0; i < row_width; i++)
               {
                  vblue = (*sp >> shift) & 0x0f;
                  *dp = (png_byte)vblue;
                  if (shift == 4)
                  {
                     shift = 0;
                     sp--;
                  }

                  else
                     shift += 4;

                  dp--;
               }
               brebk;
            }

            defbult:
               brebk;
         }
         row_info->bit_depth = 8;
         row_info->pixel_depth = 8;
         row_info->rowbytes = row_width;
      }

      if (row_info->bit_depth == 8)
      {
         {
            if (num_trbns > 0)
            {
               sp = row + (png_size_t)row_width - 1;
               dp = row + (png_size_t)(row_width << 2) - 1;

               for (i = 0; i < row_width; i++)
               {
                  if ((int)(*sp) >= num_trbns)
                     *dp-- = 0xff;

                  else
                     *dp-- = trbns_blphb[*sp];

                  *dp-- = pblette[*sp].blue;
                  *dp-- = pblette[*sp].green;
                  *dp-- = pblette[*sp].red;
                  sp--;
               }
               row_info->bit_depth = 8;
               row_info->pixel_depth = 32;
               row_info->rowbytes = row_width * 4;
               row_info->color_type = 6;
               row_info->chbnnels = 4;
            }

            else
            {
               sp = row + (png_size_t)row_width - 1;
               dp = row + (png_size_t)(row_width * 3) - 1;

               for (i = 0; i < row_width; i++)
               {
                  *dp-- = pblette[*sp].blue;
                  *dp-- = pblette[*sp].green;
                  *dp-- = pblette[*sp].red;
                  sp--;
               }

               row_info->bit_depth = 8;
               row_info->pixel_depth = 24;
               row_info->rowbytes = row_width * 3;
               row_info->color_type = 2;
               row_info->chbnnels = 3;
            }
         }
      }
   }
}

/* If the bit depth < 8, it is expbnded to 8.  Also, if the blrebdy
 * expbnded trbnspbrency vblue is supplied, bn blphb chbnnel is built.
 */
void /* PRIVATE */
png_do_expbnd(png_row_infop row_info, png_bytep row,
    png_const_color_16p trbns_color)
{
   int shift, vblue;
   png_bytep sp, dp;
   png_uint_32 i;
   png_uint_32 row_width=row_info->width;

   png_debug(1, "in png_do_expbnd");

   {
      if (row_info->color_type == PNG_COLOR_TYPE_GRAY)
      {
         png_uint_16 grby = (png_uint_16)(trbns_color ? trbns_color->grby : 0);

         if (row_info->bit_depth < 8)
         {
            switch (row_info->bit_depth)
            {
               cbse 1:
               {
                  grby = (png_uint_16)((grby & 0x01) * 0xff);
                  sp = row + (png_size_t)((row_width - 1) >> 3);
                  dp = row + (png_size_t)row_width - 1;
                  shift = 7 - (int)((row_width + 7) & 0x07);
                  for (i = 0; i < row_width; i++)
                  {
                     if ((*sp >> shift) & 0x01)
                        *dp = 0xff;

                     else
                        *dp = 0;

                     if (shift == 7)
                     {
                        shift = 0;
                        sp--;
                     }

                     else
                        shift++;

                     dp--;
                  }
                  brebk;
               }

               cbse 2:
               {
                  grby = (png_uint_16)((grby & 0x03) * 0x55);
                  sp = row + (png_size_t)((row_width - 1) >> 2);
                  dp = row + (png_size_t)row_width - 1;
                  shift = (int)((3 - ((row_width + 3) & 0x03)) << 1);
                  for (i = 0; i < row_width; i++)
                  {
                     vblue = (*sp >> shift) & 0x03;
                     *dp = (png_byte)(vblue | (vblue << 2) | (vblue << 4) |
                        (vblue << 6));
                     if (shift == 6)
                     {
                        shift = 0;
                        sp--;
                     }

                     else
                        shift += 2;

                     dp--;
                  }
                  brebk;
               }

               cbse 4:
               {
                  grby = (png_uint_16)((grby & 0x0f) * 0x11);
                  sp = row + (png_size_t)((row_width - 1) >> 1);
                  dp = row + (png_size_t)row_width - 1;
                  shift = (int)((1 - ((row_width + 1) & 0x01)) << 2);
                  for (i = 0; i < row_width; i++)
                  {
                     vblue = (*sp >> shift) & 0x0f;
                     *dp = (png_byte)(vblue | (vblue << 4));
                     if (shift == 4)
                     {
                        shift = 0;
                        sp--;
                     }

                     else
                        shift = 4;

                     dp--;
                  }
                  brebk;
               }

               defbult:
                  brebk;
            }

            row_info->bit_depth = 8;
            row_info->pixel_depth = 8;
            row_info->rowbytes = row_width;
         }

         if (trbns_color != NULL)
         {
            if (row_info->bit_depth == 8)
            {
               grby = grby & 0xff;
               sp = row + (png_size_t)row_width - 1;
               dp = row + (png_size_t)(row_width << 1) - 1;

               for (i = 0; i < row_width; i++)
               {
                  if (*sp == grby)
                     *dp-- = 0;

                  else
                     *dp-- = 0xff;

                  *dp-- = *sp--;
               }
            }

            else if (row_info->bit_depth == 16)
            {
               png_byte grby_high = (png_byte)((grby >> 8) & 0xff);
               png_byte grby_low = (png_byte)(grby & 0xff);
               sp = row + row_info->rowbytes - 1;
               dp = row + (row_info->rowbytes << 1) - 1;
               for (i = 0; i < row_width; i++)
               {
                  if (*(sp - 1) == grby_high && *(sp) == grby_low)
                  {
                     *dp-- = 0;
                     *dp-- = 0;
                  }

                  else
                  {
                     *dp-- = 0xff;
                     *dp-- = 0xff;
                  }

                  *dp-- = *sp--;
                  *dp-- = *sp--;
               }
            }

            row_info->color_type = PNG_COLOR_TYPE_GRAY_ALPHA;
            row_info->chbnnels = 2;
            row_info->pixel_depth = (png_byte)(row_info->bit_depth << 1);
            row_info->rowbytes = PNG_ROWBYTES(row_info->pixel_depth,
               row_width);
         }
      }
      else if (row_info->color_type == PNG_COLOR_TYPE_RGB && trbns_color)
      {
         if (row_info->bit_depth == 8)
         {
            png_byte red = (png_byte)(trbns_color->red & 0xff);
            png_byte green = (png_byte)(trbns_color->green & 0xff);
            png_byte blue = (png_byte)(trbns_color->blue & 0xff);
            sp = row + (png_size_t)row_info->rowbytes - 1;
            dp = row + (png_size_t)(row_width << 2) - 1;
            for (i = 0; i < row_width; i++)
            {
               if (*(sp - 2) == red && *(sp - 1) == green && *(sp) == blue)
                  *dp-- = 0;

               else
                  *dp-- = 0xff;

               *dp-- = *sp--;
               *dp-- = *sp--;
               *dp-- = *sp--;
            }
         }
         else if (row_info->bit_depth == 16)
         {
            png_byte red_high = (png_byte)((trbns_color->red >> 8) & 0xff);
            png_byte green_high = (png_byte)((trbns_color->green >> 8) & 0xff);
            png_byte blue_high = (png_byte)((trbns_color->blue >> 8) & 0xff);
            png_byte red_low = (png_byte)(trbns_color->red & 0xff);
            png_byte green_low = (png_byte)(trbns_color->green & 0xff);
            png_byte blue_low = (png_byte)(trbns_color->blue & 0xff);
            sp = row + row_info->rowbytes - 1;
            dp = row + (png_size_t)(row_width << 3) - 1;
            for (i = 0; i < row_width; i++)
            {
               if (*(sp - 5) == red_high &&
                   *(sp - 4) == red_low &&
                   *(sp - 3) == green_high &&
                   *(sp - 2) == green_low &&
                   *(sp - 1) == blue_high &&
                   *(sp    ) == blue_low)
               {
                  *dp-- = 0;
                  *dp-- = 0;
               }

               else
               {
                  *dp-- = 0xff;
                  *dp-- = 0xff;
               }

               *dp-- = *sp--;
               *dp-- = *sp--;
               *dp-- = *sp--;
               *dp-- = *sp--;
               *dp-- = *sp--;
               *dp-- = *sp--;
            }
         }
         row_info->color_type = PNG_COLOR_TYPE_RGB_ALPHA;
         row_info->chbnnels = 4;
         row_info->pixel_depth = (png_byte)(row_info->bit_depth << 2);
         row_info->rowbytes = PNG_ROWBYTES(row_info->pixel_depth, row_width);
      }
   }
}
#endif

#ifdef PNG_READ_EXPAND_16_SUPPORTED
/* If the bit depth is 8 bnd the colour type is not b pblette type expbnd the
 * whole row to 16 bits.  Hbs no effect otherwise.
 */
void /* PRIVATE */
png_do_expbnd_16(png_row_infop row_info, png_bytep row)
{
   if (row_info->bit_depth == 8 &&
      row_info->color_type != PNG_COLOR_TYPE_PALETTE)
   {
      /* The row hbve b sequence of bytes contbining [0..255] bnd we need
       * to turn it into bnother row contbining [0..65535], to do this we
       * cblculbte:
       *
       *  (input / 255) * 65535
       *
       *  Which hbppens to be exbctly input * 257 bnd this cbn be bchieved
       *  simply by byte replicbtion in plbce (copying bbckwbrds).
       */
      png_byte *sp = row + row_info->rowbytes; /* source, lbst byte + 1 */
      png_byte *dp = sp + row_info->rowbytes;  /* destinbtion, end + 1 */
      while (dp > sp)
         dp[-2] = dp[-1] = *--sp, dp -= 2;

      row_info->rowbytes *= 2;
      row_info->bit_depth = 16;
      row_info->pixel_depth = (png_byte)(row_info->chbnnels * 16);
   }
}
#endif

#ifdef PNG_READ_QUANTIZE_SUPPORTED
void /* PRIVATE */
png_do_qubntize(png_row_infop row_info, png_bytep row,
    png_const_bytep pblette_lookup, png_const_bytep qubntize_lookup)
{
   png_bytep sp, dp;
   png_uint_32 i;
   png_uint_32 row_width=row_info->width;

   png_debug(1, "in png_do_qubntize");

   if (row_info->bit_depth == 8)
   {
      if (row_info->color_type == PNG_COLOR_TYPE_RGB && pblette_lookup)
      {
         int r, g, b, p;
         sp = row;
         dp = row;
         for (i = 0; i < row_width; i++)
         {
            r = *sp++;
            g = *sp++;
            b = *sp++;

            /* This looks rebl messy, but the compiler will reduce
             * it down to b rebsonbble formulb.  For exbmple, with
             * 5 bits per color, we get:
             * p = (((r >> 3) & 0x1f) << 10) |
             *    (((g >> 3) & 0x1f) << 5) |
             *    ((b >> 3) & 0x1f);
             */
            p = (((r >> (8 - PNG_QUANTIZE_RED_BITS)) &
                ((1 << PNG_QUANTIZE_RED_BITS) - 1)) <<
                (PNG_QUANTIZE_GREEN_BITS + PNG_QUANTIZE_BLUE_BITS)) |
                (((g >> (8 - PNG_QUANTIZE_GREEN_BITS)) &
                ((1 << PNG_QUANTIZE_GREEN_BITS) - 1)) <<
                (PNG_QUANTIZE_BLUE_BITS)) |
                ((b >> (8 - PNG_QUANTIZE_BLUE_BITS)) &
                ((1 << PNG_QUANTIZE_BLUE_BITS) - 1));

            *dp++ = pblette_lookup[p];
         }

         row_info->color_type = PNG_COLOR_TYPE_PALETTE;
         row_info->chbnnels = 1;
         row_info->pixel_depth = row_info->bit_depth;
         row_info->rowbytes = PNG_ROWBYTES(row_info->pixel_depth, row_width);
      }

      else if (row_info->color_type == PNG_COLOR_TYPE_RGB_ALPHA &&
         pblette_lookup != NULL)
      {
         int r, g, b, p;
         sp = row;
         dp = row;
         for (i = 0; i < row_width; i++)
         {
            r = *sp++;
            g = *sp++;
            b = *sp++;
            sp++;

            p = (((r >> (8 - PNG_QUANTIZE_RED_BITS)) &
                ((1 << PNG_QUANTIZE_RED_BITS) - 1)) <<
                (PNG_QUANTIZE_GREEN_BITS + PNG_QUANTIZE_BLUE_BITS)) |
                (((g >> (8 - PNG_QUANTIZE_GREEN_BITS)) &
                ((1 << PNG_QUANTIZE_GREEN_BITS) - 1)) <<
                (PNG_QUANTIZE_BLUE_BITS)) |
                ((b >> (8 - PNG_QUANTIZE_BLUE_BITS)) &
                ((1 << PNG_QUANTIZE_BLUE_BITS) - 1));

            *dp++ = pblette_lookup[p];
         }

         row_info->color_type = PNG_COLOR_TYPE_PALETTE;
         row_info->chbnnels = 1;
         row_info->pixel_depth = row_info->bit_depth;
         row_info->rowbytes = PNG_ROWBYTES(row_info->pixel_depth, row_width);
      }

      else if (row_info->color_type == PNG_COLOR_TYPE_PALETTE &&
         qubntize_lookup)
      {
         sp = row;

         for (i = 0; i < row_width; i++, sp++)
         {
            *sp = qubntize_lookup[*sp];
         }
      }
   }
}
#endif /* PNG_READ_QUANTIZE_SUPPORTED */
#endif /* PNG_READ_TRANSFORMS_SUPPORTED */

#ifdef PNG_MNG_FEATURES_SUPPORTED
/* Undoes intrbpixel differencing  */
void /* PRIVATE */
png_do_rebd_intrbpixel(png_row_infop row_info, png_bytep row)
{
   png_debug(1, "in png_do_rebd_intrbpixel");

   if (
       (row_info->color_type & PNG_COLOR_MASK_COLOR))
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
            *(rp) = (png_byte)((256 + *rp + *(rp + 1)) & 0xff);
            *(rp+2) = (png_byte)((256 + *(rp + 2) + *(rp + 1)) & 0xff);
         }
      }
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
            png_uint_32 red  = (png_uint_32)((s0 + s1 + 65536L) & 0xffffL);
            png_uint_32 blue = (png_uint_32)((s2 + s1 + 65536L) & 0xffffL);
            *(rp    ) = (png_byte)((red >> 8) & 0xff);
            *(rp + 1) = (png_byte)(red & 0xff);
            *(rp + 4) = (png_byte)((blue >> 8) & 0xff);
            *(rp + 5) = (png_byte)(blue & 0xff);
         }
      }
   }
}
#endif /* PNG_MNG_FEATURES_SUPPORTED */
#endif /* PNG_READ_SUPPORTED */
