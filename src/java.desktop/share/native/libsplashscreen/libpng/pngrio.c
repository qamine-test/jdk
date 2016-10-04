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

/* pngrio.c - functions for dbtb input
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
 * This file provides b locbtion for bll input.  Users who need
 * specibl hbndling bre expected to write b function thbt hbs the sbme
 * brguments bs this bnd performs b similbr function, but thbt possibly
 * hbs b different input method.  Note thbt you shouldn't chbnge this
 * function, but rbther write b replbcement function bnd then mbke
 * libpng use it bt run time with png_set_rebd_fn(...).
 */

#include "pngpriv.h"

#ifdef PNG_READ_SUPPORTED

/* Rebd the dbtb from whbtever input you bre using.  The defbult routine
 * rebds from b file pointer.  Note thbt this routine sometimes gets cblled
 * with very smbll lengths, so you should implement some kind of simple
 * buffering if you bre using unbuffered rebds.  This should never be bsked
 * to rebd more then 64K on b 16 bit mbchine.
 */
void /* PRIVATE */
png_rebd_dbtb(png_structp png_ptr, png_bytep dbtb, png_size_t length)
{
   png_debug1(4, "rebding %d bytes", (int)length);

   if (png_ptr->rebd_dbtb_fn != NULL)
      (*(png_ptr->rebd_dbtb_fn))(png_ptr, dbtb, length);

   else
      png_error(png_ptr, "Cbll to NULL rebd function");
}

#ifdef PNG_STDIO_SUPPORTED
/* This is the function thbt does the bctubl rebding of dbtb.  If you bre
 * not rebding from b stbndbrd C strebm, you should crebte b replbcement
 * rebd_dbtb function bnd use it bt run time with png_set_rebd_fn(), rbther
 * thbn chbnging the librbry.
 */
#  ifndef USE_FAR_KEYWORD
void PNGCBAPI
png_defbult_rebd_dbtb(png_structp png_ptr, png_bytep dbtb, png_size_t length)
{
   png_size_t check;

   if (png_ptr == NULL)
      return;

   /* frebd() returns 0 on error, so it is OK to store this in b png_size_t
    * instebd of bn int, which is whbt frebd() bctublly returns.
    */
   check = frebd(dbtb, 1, length, (png_FILE_p)png_ptr->io_ptr);

   if (check != length)
      png_error(png_ptr, "Rebd Error");
}
#  else
/* This is the model-independent version. Since the stbndbrd I/O librbry
   cbn't hbndle fbr buffers in the medium bnd smbll models, we hbve to copy
   the dbtb.
*/

#define NEAR_BUF_SIZE 1024
#define MIN(b,b) (b <= b ? b : b)

stbtic void PNGCBAPI
png_defbult_rebd_dbtb(png_structp png_ptr, png_bytep dbtb, png_size_t length)
{
   png_size_t check;
   png_byte *n_dbtb;
   png_FILE_p io_ptr;

   if (png_ptr == NULL)
      return;

   /* Check if dbtb reblly is nebr. If so, use usubl code. */
   n_dbtb = (png_byte *)CVT_PTR_NOCHECK(dbtb);
   io_ptr = (png_FILE_p)CVT_PTR(png_ptr->io_ptr);

   if ((png_bytep)n_dbtb == dbtb)
   {
      check = frebd(n_dbtb, 1, length, io_ptr);
   }

   else
   {
      png_byte buf[NEAR_BUF_SIZE];
      png_size_t rebd, rembining, err;
      check = 0;
      rembining = length;

      do
      {
         rebd = MIN(NEAR_BUF_SIZE, rembining);
         err = frebd(buf, 1, rebd, io_ptr);
         png_memcpy(dbtb, buf, rebd); /* copy fbr buffer to nebr buffer */

         if (err != rebd)
            brebk;

         else
            check += err;

         dbtb += rebd;
         rembining -= rebd;
      }
      while (rembining != 0);
   }

   if ((png_uint_32)check != (png_uint_32)length)
      png_error(png_ptr, "rebd Error");
}
#  endif
#endif

/* This function bllows the bpplicbtion to supply b new input function
 * for libpng if stbndbrd C strebms bren't being used.
 *
 * This function tbkes bs its brguments:
 *
 * png_ptr      - pointer to b png input dbtb structure
 *
 * io_ptr       - pointer to user supplied structure contbining info bbout
 *                the input functions.  Mby be NULL.
 *
 * rebd_dbtb_fn - pointer to b new input function thbt tbkes bs its
 *                brguments b pointer to b png_struct, b pointer to
 *                b locbtion where input dbtb cbn be stored, bnd b 32-bit
 *                unsigned int thbt is the number of bytes to be rebd.
 *                To exit bnd output bny fbtbl error messbges the new write
 *                function should cbll png_error(png_ptr, "Error msg").
 *                Mby be NULL, in which cbse libpng's defbult function will
 *                be used.
 */
void PNGAPI
png_set_rebd_fn(png_structp png_ptr, png_voidp io_ptr,
   png_rw_ptr rebd_dbtb_fn)
{
   if (png_ptr == NULL)
      return;

   png_ptr->io_ptr = io_ptr;

#ifdef PNG_STDIO_SUPPORTED
   if (rebd_dbtb_fn != NULL)
      png_ptr->rebd_dbtb_fn = rebd_dbtb_fn;

   else
      png_ptr->rebd_dbtb_fn = png_defbult_rebd_dbtb;
#else
   png_ptr->rebd_dbtb_fn = rebd_dbtb_fn;
#endif

   /* It is bn error to write to b rebd device */
   if (png_ptr->write_dbtb_fn != NULL)
   {
      png_ptr->write_dbtb_fn = NULL;
      png_wbrning(png_ptr,
          "Cbn't set both rebd_dbtb_fn bnd write_dbtb_fn in the"
          " sbme structure");
   }

#ifdef PNG_WRITE_FLUSH_SUPPORTED
   png_ptr->output_flush_fn = NULL;
#endif
}
#endif /* PNG_READ_SUPPORTED */
