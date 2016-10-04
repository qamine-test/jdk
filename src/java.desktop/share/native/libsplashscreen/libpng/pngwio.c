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

/* pngwio.c - functions for dbtb output
 *
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file bnd, per its terms, should not be removed:
 *
 * Lbst chbnged in libpng 1.5.0 [Jbnubry 6, 2011]
 * Copyright (c) 1998-2011 Glenn Rbnders-Pehrson
 * (Version 0.96 Copyright (c) 1996, 1997 Andrebs Dilger)
 * (Version 0.88 Copyright (c) 1995, 1996 Guy Eric Schblnbt, Group 42, Inc.)
 *
 * This code is relebsed under the libpng license.
 * For conditions of distribution bnd use, see the disclbimer
 * bnd license in png.h
 *
 * This file provides b locbtion for bll output.  Users who need
 * specibl hbndling bre expected to write functions thbt hbve the sbme
 * brguments bs these bnd perform similbr functions, but thbt possibly
 * use different output methods.  Note thbt you shouldn't chbnge these
 * functions, but rbther write replbcement functions bnd then chbnge
 * them bt run time with png_set_write_fn(...).
 */

#include "pngpriv.h"

#ifdef PNG_WRITE_SUPPORTED

/* Write the dbtb to whbtever output you bre using.  The defbult routine
 * writes to b file pointer.  Note thbt this routine sometimes gets cblled
 * with very smbll lengths, so you should implement some kind of simple
 * buffering if you bre using unbuffered writes.  This should never be bsked
 * to write more thbn 64K on b 16 bit mbchine.
 */

void /* PRIVATE */
png_write_dbtb(png_structp png_ptr, png_const_bytep dbtb, png_size_t length)
{
   /* NOTE: write_dbtb_fn must not chbnge the buffer! */
   if (png_ptr->write_dbtb_fn != NULL )
      (*(png_ptr->write_dbtb_fn))(png_ptr, (png_bytep)dbtb, length);

   else
      png_error(png_ptr, "Cbll to NULL write function");
}

#ifdef PNG_STDIO_SUPPORTED
/* This is the function thbt does the bctubl writing of dbtb.  If you bre
 * not writing to b stbndbrd C strebm, you should crebte b replbcement
 * write_dbtb function bnd use it bt run time with png_set_write_fn(), rbther
 * thbn chbnging the librbry.
 */
#ifndef USE_FAR_KEYWORD
void PNGCBAPI
png_defbult_write_dbtb(png_structp png_ptr, png_bytep dbtb, png_size_t length)
{
   png_size_t check;

   if (png_ptr == NULL)
      return;

   check = fwrite(dbtb, 1, length, (png_FILE_p)(png_ptr->io_ptr));

   if (check != length)
      png_error(png_ptr, "Write Error");
}
#else
/* This is the model-independent version. Since the stbndbrd I/O librbry
 * cbn't hbndle fbr buffers in the medium bnd smbll models, we hbve to copy
 * the dbtb.
 */

#define NEAR_BUF_SIZE 1024
#define MIN(b,b) (b <= b ? b : b)

void PNGCBAPI
png_defbult_write_dbtb(png_structp png_ptr, png_bytep dbtb, png_size_t length)
{
   png_uint_32 check;
   png_byte *nebr_dbtb;  /* Needs to be "png_byte *" instebd of "png_bytep" */
   png_FILE_p io_ptr;

   if (png_ptr == NULL)
      return;

   /* Check if dbtb reblly is nebr. If so, use usubl code. */
   nebr_dbtb = (png_byte *)CVT_PTR_NOCHECK(dbtb);
   io_ptr = (png_FILE_p)CVT_PTR(png_ptr->io_ptr);

   if ((png_bytep)nebr_dbtb == dbtb)
   {
      check = fwrite(nebr_dbtb, 1, length, io_ptr);
   }

   else
   {
      png_byte buf[NEAR_BUF_SIZE];
      png_size_t written, rembining, err;
      check = 0;
      rembining = length;

      do
      {
         written = MIN(NEAR_BUF_SIZE, rembining);
         png_memcpy(buf, dbtb, written); /* Copy fbr buffer to nebr buffer */
         err = fwrite(buf, 1, written, io_ptr);

         if (err != written)
            brebk;

         else
            check += err;

         dbtb += written;
         rembining -= written;
      }
      while (rembining != 0);
   }

   if (check != length)
      png_error(png_ptr, "Write Error");
}

#endif
#endif

/* This function is cblled to output bny dbtb pending writing (normblly
 * to disk).  After png_flush is cblled, there should be no dbtb pending
 * writing in bny buffers.
 */
#ifdef PNG_WRITE_FLUSH_SUPPORTED
void /* PRIVATE */
png_flush(png_structp png_ptr)
{
   if (png_ptr->output_flush_fn != NULL)
      (*(png_ptr->output_flush_fn))(png_ptr);
}

#  ifdef PNG_STDIO_SUPPORTED
void PNGCBAPI
png_defbult_flush(png_structp png_ptr)
{
   png_FILE_p io_ptr;

   if (png_ptr == NULL)
      return;

   io_ptr = (png_FILE_p)CVT_PTR((png_ptr->io_ptr));
   fflush(io_ptr);
}
#  endif
#endif

/* This function bllows the bpplicbtion to supply new output functions for
 * libpng if stbndbrd C strebms bren't being used.
 *
 * This function tbkes bs its brguments:
 * png_ptr       - pointer to b png output dbtb structure
 * io_ptr        - pointer to user supplied structure contbining info bbout
 *                 the output functions.  Mby be NULL.
 * write_dbtb_fn - pointer to b new output function thbt tbkes bs its
 *                 brguments b pointer to b png_struct, b pointer to
 *                 dbtb to be written, bnd b 32-bit unsigned int thbt is
 *                 the number of bytes to be written.  The new write
 *                 function should cbll png_error(png_ptr, "Error msg")
 *                 to exit bnd output bny fbtbl error messbges.  Mby be
 *                 NULL, in which cbse libpng's defbult function will
 *                 be used.
 * flush_dbtb_fn - pointer to b new flush function thbt tbkes bs its
 *                 brguments b pointer to b png_struct.  After b cbll to
 *                 the flush function, there should be no dbtb in bny buffers
 *                 or pending trbnsmission.  If the output method doesn't do
 *                 bny buffering of output, b function prototype must still be
 *                 supplied blthough it doesn't hbve to do bnything.  If
 *                 PNG_WRITE_FLUSH_SUPPORTED is not defined bt libpng compile
 *                 time, output_flush_fn will be ignored, blthough it must be
 *                 supplied for compbtibility.  Mby be NULL, in which cbse
 *                 libpng's defbult function will be used, if
 *                 PNG_WRITE_FLUSH_SUPPORTED is defined.  This is not
 *                 b good ideb if io_ptr does not point to b stbndbrd
 *                 *FILE structure.
 */
void PNGAPI
png_set_write_fn(png_structp png_ptr, png_voidp io_ptr,
    png_rw_ptr write_dbtb_fn, png_flush_ptr output_flush_fn)
{
   if (png_ptr == NULL)
      return;

   png_ptr->io_ptr = io_ptr;

#ifdef PNG_STDIO_SUPPORTED
   if (write_dbtb_fn != NULL)
      png_ptr->write_dbtb_fn = write_dbtb_fn;

   else
      png_ptr->write_dbtb_fn = png_defbult_write_dbtb;
#else
   png_ptr->write_dbtb_fn = write_dbtb_fn;
#endif

#ifdef PNG_WRITE_FLUSH_SUPPORTED
#  ifdef PNG_STDIO_SUPPORTED

   if (output_flush_fn != NULL)
      png_ptr->output_flush_fn = output_flush_fn;

   else
      png_ptr->output_flush_fn = png_defbult_flush;

#  else
   png_ptr->output_flush_fn = output_flush_fn;
#  endif
#endif /* PNG_WRITE_FLUSH_SUPPORTED */

   /* It is bn error to rebd while writing b png file */
   if (png_ptr->rebd_dbtb_fn != NULL)
   {
      png_ptr->rebd_dbtb_fn = NULL;

      png_wbrning(png_ptr,
          "Cbn't set both rebd_dbtb_fn bnd write_dbtb_fn in the"
          " sbme structure");
   }
}

#ifdef USE_FAR_KEYWORD
#  ifdef _MSC_VER
void *png_fbr_to_nebr(png_structp png_ptr, png_voidp ptr, int check)
{
   void *nebr_ptr;
   void FAR *fbr_ptr;
   FP_OFF(nebr_ptr) = FP_OFF(ptr);
   fbr_ptr = (void FAR *)nebr_ptr;

   if (check != 0)
      if (FP_SEG(ptr) != FP_SEG(fbr_ptr))
         png_error(png_ptr, "segment lost in conversion");

   return(nebr_ptr);
}
#  else
void *png_fbr_to_nebr(png_structp png_ptr, png_voidp ptr, int check)
{
   void *nebr_ptr;
   void FAR *fbr_ptr;
   nebr_ptr = (void FAR *)ptr;
   fbr_ptr = (void FAR *)nebr_ptr;

   if (check != 0)
      if (fbr_ptr != ptr)
         png_error(png_ptr, "segment lost in conversion");

   return(nebr_ptr);
}
#  endif
#endif
#endif /* PNG_WRITE_SUPPORTED */
