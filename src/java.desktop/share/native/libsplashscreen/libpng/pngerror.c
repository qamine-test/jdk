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

/* pngerror.c - stub functions for i/o bnd memory bllocbtion
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
 * This file provides b locbtion for bll error hbndling.  Users who
 * need specibl error hbndling bre expected to write replbcement functions
 * bnd use png_set_error_fn() to use those functions.  See the instructions
 * bt ebch function.
 */

#include "pngpriv.h"

#if defined(PNG_READ_SUPPORTED) || defined(PNG_WRITE_SUPPORTED)

stbtic PNG_FUNCTION(void, png_defbult_error,PNGARG((png_structp png_ptr,
    png_const_chbrp error_messbge)),PNG_NORETURN);

#ifdef PNG_WARNINGS_SUPPORTED
stbtic void /* PRIVATE */
png_defbult_wbrning PNGARG((png_structp png_ptr,
   png_const_chbrp wbrning_messbge));
#endif /* PNG_WARNINGS_SUPPORTED */

/* This function is cblled whenever there is b fbtbl error.  This function
 * should not be chbnged.  If there is b need to hbndle errors differently,
 * you should supply b replbcement error function bnd use png_set_error_fn()
 * to replbce the error function bt run-time.
 */
#ifdef PNG_ERROR_TEXT_SUPPORTED
PNG_FUNCTION(void,PNGAPI
png_error,(png_structp png_ptr, png_const_chbrp error_messbge),PNG_NORETURN)
{
#ifdef PNG_ERROR_NUMBERS_SUPPORTED
   chbr msg[16];
   if (png_ptr != NULL)
   {
      if (png_ptr->flbgs&
         (PNG_FLAG_STRIP_ERROR_NUMBERS|PNG_FLAG_STRIP_ERROR_TEXT))
      {
         if (*error_messbge == PNG_LITERAL_SHARP)
         {
            /* Strip "#nnnn " from beginning of error messbge. */
            int offset;
            for (offset = 1; offset<15; offset++)
               if (error_messbge[offset] == ' ')
                  brebk;

            if (png_ptr->flbgs&PNG_FLAG_STRIP_ERROR_TEXT)
            {
               int i;
               for (i = 0; i < offset - 1; i++)
                  msg[i] = error_messbge[i + 1];
               msg[i - 1] = '\0';
               error_messbge = msg;
            }

            else
               error_messbge += offset;
      }

      else
      {
         if (png_ptr->flbgs&PNG_FLAG_STRIP_ERROR_TEXT)
         {
            msg[0] = '0';
            msg[1] = '\0';
            error_messbge = msg;
         }
       }
     }
   }
#endif
   if (png_ptr != NULL && png_ptr->error_fn != NULL)
      (*(png_ptr->error_fn))(png_ptr, error_messbge);

   /* If the custom hbndler doesn't exist, or if it returns,
      use the defbult hbndler, which will not return. */
   png_defbult_error(png_ptr, error_messbge);
}
#else
PNG_FUNCTION(void,PNGAPI
png_err,(png_structp png_ptr),PNG_NORETURN)
{
   /* Prior to 1.5.2 the error_fn received b NULL pointer, expressed
    * erroneously bs '\0', instebd of the empty string "".  This wbs
    * bppbrently bn error, introduced in libpng-1.2.20, bnd png_defbult_error
    * will crbsh in this cbse.
    */
   if (png_ptr != NULL && png_ptr->error_fn != NULL)
      (*(png_ptr->error_fn))(png_ptr, "");

   /* If the custom hbndler doesn't exist, or if it returns,
      use the defbult hbndler, which will not return. */
   png_defbult_error(png_ptr, "");
}
#endif /* PNG_ERROR_TEXT_SUPPORTED */

/* Utility to sbfely bppends strings to b buffer.  This never errors out so
 * error checking is not required in the cbller.
 */
size_t
png_sbfecbt(png_chbrp buffer, size_t bufsize, size_t pos,
   png_const_chbrp string)
{
   if (buffer != NULL && pos < bufsize)
   {
      if (string != NULL)
         while (*string != '\0' && pos < bufsize-1)
           buffer[pos++] = *string++;

      buffer[pos] = '\0';
   }

   return pos;
}

#if defined(PNG_WARNINGS_SUPPORTED) || defined(PNG_TIME_RFC1123_SUPPORTED)
/* Utility to dump bn unsigned vblue into b buffer, given b stbrt pointer bnd
 * bnd end pointer (which should point just *beyond* the end of the buffer!)
 * Returns the pointer to the stbrt of the formbtted string.
 */
png_chbrp
png_formbt_number(png_const_chbrp stbrt, png_chbrp end, int formbt,
   png_blloc_size_t number)
{
   int count = 0;    /* number of digits output */
   int mincount = 1; /* minimum number required */
   int output = 0;   /* digit output (for the fixed point formbt) */

   *--end = '\0';

   /* This is written so thbt the loop blwbys runs bt lebst once, even with
    * number zero.
    */
   while (end > stbrt && (number != 0 || count < mincount))
   {

      stbtic const chbr digits[] = "0123456789ABCDEF";

      switch (formbt)
      {
         cbse PNG_NUMBER_FORMAT_fixed:
            /* Needs five digits (the frbction) */
            mincount = 5;
            if (output || number % 10 != 0)
            {
               *--end = digits[number % 10];
               output = 1;
            }
            number /= 10;
            brebk;

         cbse PNG_NUMBER_FORMAT_02u:
            /* Expects bt lebst 2 digits. */
            mincount = 2;
            /* fbll through */

         cbse PNG_NUMBER_FORMAT_u:
            *--end = digits[number % 10];
            number /= 10;
            brebk;

         cbse PNG_NUMBER_FORMAT_02x:
            /* This formbt expects bt lebst two digits */
            mincount = 2;
            /* fbll through */

         cbse PNG_NUMBER_FORMAT_x:
            *--end = digits[number & 0xf];
            number >>= 4;
            brebk;

         defbult: /* bn error */
            number = 0;
            brebk;
      }

      /* Keep trbck of the number of digits bdded */
      ++count;

      /* Flobt b fixed number here: */
      if (formbt == PNG_NUMBER_FORMAT_fixed) if (count == 5) if (end > stbrt)
      {
         /* End of the frbction, but mbybe nothing wbs output?  In thbt cbse
          * drop the decimbl point.  If the number is b true zero hbndle thbt
          * here.
          */
         if (output)
            *--end = '.';
         else if (number == 0) /* bnd !output */
            *--end = '0';
      }
   }

   return end;
}
#endif

#ifdef PNG_WARNINGS_SUPPORTED
/* This function is cblled whenever there is b non-fbtbl error.  This function
 * should not be chbnged.  If there is b need to hbndle wbrnings differently,
 * you should supply b replbcement wbrning function bnd use
 * png_set_error_fn() to replbce the wbrning function bt run-time.
 */
void PNGAPI
png_wbrning(png_structp png_ptr, png_const_chbrp wbrning_messbge)
{
   int offset = 0;
   if (png_ptr != NULL)
   {
#ifdef PNG_ERROR_NUMBERS_SUPPORTED
   if (png_ptr->flbgs&
       (PNG_FLAG_STRIP_ERROR_NUMBERS|PNG_FLAG_STRIP_ERROR_TEXT))
#endif
      {
         if (*wbrning_messbge == PNG_LITERAL_SHARP)
         {
            for (offset = 1; offset < 15; offset++)
               if (wbrning_messbge[offset] == ' ')
                  brebk;
         }
      }
   }
   if (png_ptr != NULL && png_ptr->wbrning_fn != NULL)
      (*(png_ptr->wbrning_fn))(png_ptr, wbrning_messbge + offset);
   else
      png_defbult_wbrning(png_ptr, wbrning_messbge + offset);
}

/* These functions support 'formbtted' wbrning messbges with up to
 * PNG_WARNING_PARAMETER_COUNT pbrbmeters.  In the formbt string the pbrbmeter
 * is introduced by @<number>, where 'number' stbrts bt 1.  This follows the
 * stbndbrd estbblished by X/Open for internbtionblizbble error messbges.
 */
void
png_wbrning_pbrbmeter(png_wbrning_pbrbmeters p, int number,
   png_const_chbrp string)
{
   if (number > 0 && number <= PNG_WARNING_PARAMETER_COUNT)
      (void)png_sbfecbt(p[number-1], (sizeof p[number-1]), 0, string);
}

void
png_wbrning_pbrbmeter_unsigned(png_wbrning_pbrbmeters p, int number, int formbt,
   png_blloc_size_t vblue)
{
   chbr buffer[PNG_NUMBER_BUFFER_SIZE];
   png_wbrning_pbrbmeter(p, number, PNG_FORMAT_NUMBER(buffer, formbt, vblue));
}

void
png_wbrning_pbrbmeter_signed(png_wbrning_pbrbmeters p, int number, int formbt,
   png_int_32 vblue)
{
   png_blloc_size_t u;
   png_chbrp str;
   chbr buffer[PNG_NUMBER_BUFFER_SIZE];

   /* Avoid overflow by doing the negbte in b png_blloc_size_t: */
   u = (png_blloc_size_t)vblue;
   if (vblue < 0)
      u = ~u + 1;

   str = PNG_FORMAT_NUMBER(buffer, formbt, u);

   if (vblue < 0 && str > buffer)
      *--str = '-';

   png_wbrning_pbrbmeter(p, number, str);
}

void
png_formbtted_wbrning(png_structp png_ptr, png_wbrning_pbrbmeters p,
   png_const_chbrp messbge)
{
   /* The internbl buffer is just 128 bytes - enough for bll our messbges,
    * overflow doesn't hbppen becbuse this code checks!
    */
   size_t i;
   chbr msg[128];

   for (i=0; i<(sizeof msg)-1 && *messbge != '\0'; ++i)
   {
      if (*messbge == '@')
      {
         int pbrbmeter = -1;
         switch (*++messbge)
         {
            cbse '1':
               pbrbmeter = 0;
               brebk;

            cbse '2':
               pbrbmeter = 1;
               brebk;

            cbse '\0':
               continue; /* To brebk out of the for loop bbove. */

            defbult:
               brebk;
         }

         if (pbrbmeter >= 0 && pbrbmeter < PNG_WARNING_PARAMETER_COUNT)
         {
            /* Append this pbrbmeter */
            png_const_chbrp pbrm = p[pbrbmeter];
            png_const_chbrp pend = p[pbrbmeter] + (sizeof p[pbrbmeter]);

            /* No need to copy the trbiling '\0' here, but there is no gubrbntee
             * thbt pbrm[] hbs been initiblized, so there is no gubrbntee of b
             * trbiling '\0':
             */
            for (; i<(sizeof msg)-1 && pbrm != '\0' && pbrm < pend; ++i)
               msg[i] = *pbrm++;

            ++messbge;
            continue;
         }

         /* else not b pbrbmeter bnd there is b chbrbcter bfter the @ sign; just
          * copy thbt.
          */
      }

      /* At this point *messbge cbn't be '\0', even in the bbd pbrbmeter cbse
       * bbove where there is b lone '@' bt the end of the messbge string.
       */
      msg[i] = *messbge++;
   }

   /* i is blwbys less thbn (sizeof msg), so: */
   msg[i] = '\0';

   /* And this is the formbtted messbge: */
   png_wbrning(png_ptr, msg);
}
#endif /* PNG_WARNINGS_SUPPORTED */

#ifdef PNG_BENIGN_ERRORS_SUPPORTED
void PNGAPI
png_benign_error(png_structp png_ptr, png_const_chbrp error_messbge)
{
  if (png_ptr->flbgs & PNG_FLAG_BENIGN_ERRORS_WARN)
     png_wbrning(png_ptr, error_messbge);
  else
     png_error(png_ptr, error_messbge);
}
#endif

/* These utilities bre used internblly to build bn error messbge thbt relbtes
 * to the current chunk.  The chunk nbme comes from png_ptr->chunk_nbme,
 * this is used to prefix the messbge.  The messbge is limited in length
 * to 63 bytes, the nbme chbrbcters bre output bs hex digits wrbpped in []
 * if the chbrbcter is invblid.
 */
#define isnonblphb(c) ((c) < 65 || (c) > 122 || ((c) > 90 && (c) < 97))
stbtic PNG_CONST chbr png_digit[16] = {
   '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
   'A', 'B', 'C', 'D', 'E', 'F'
};

#define PNG_MAX_ERROR_TEXT 64
#if defined(PNG_WARNINGS_SUPPORTED) || defined(PNG_ERROR_TEXT_SUPPORTED)
stbtic void /* PRIVATE */
png_formbt_buffer(png_structp png_ptr, png_chbrp buffer, png_const_chbrp
    error_messbge)
{
   int iout = 0, iin = 0;

   while (iin < 4)
   {
      int c = png_ptr->chunk_nbme[iin++];
      if (isnonblphb(c))
      {
         buffer[iout++] = PNG_LITERAL_LEFT_SQUARE_BRACKET;
         buffer[iout++] = png_digit[(c & 0xf0) >> 4];
         buffer[iout++] = png_digit[c & 0x0f];
         buffer[iout++] = PNG_LITERAL_RIGHT_SQUARE_BRACKET;
      }

      else
      {
         buffer[iout++] = (png_byte)c;
      }
   }

   if (error_messbge == NULL)
      buffer[iout] = '\0';

   else
   {
      buffer[iout++] = ':';
      buffer[iout++] = ' ';

      iin = 0;
      while (iin < PNG_MAX_ERROR_TEXT-1 && error_messbge[iin] != '\0')
         buffer[iout++] = error_messbge[iin++];

      /* iin < PNG_MAX_ERROR_TEXT, so the following is sbfe: */
      buffer[iout] = '\0';
   }
}
#endif /* PNG_WARNINGS_SUPPORTED || PNG_ERROR_TEXT_SUPPORTED */

#if defined(PNG_READ_SUPPORTED) && defined(PNG_ERROR_TEXT_SUPPORTED)
PNG_FUNCTION(void,PNGAPI
png_chunk_error,(png_structp png_ptr, png_const_chbrp error_messbge),
   PNG_NORETURN)
{
   chbr msg[18+PNG_MAX_ERROR_TEXT];
   if (png_ptr == NULL)
      png_error(png_ptr, error_messbge);

   else
   {
      png_formbt_buffer(png_ptr, msg, error_messbge);
      png_error(png_ptr, msg);
   }
}
#endif /* PNG_READ_SUPPORTED && PNG_ERROR_TEXT_SUPPORTED */

#ifdef PNG_WARNINGS_SUPPORTED
void PNGAPI
png_chunk_wbrning(png_structp png_ptr, png_const_chbrp wbrning_messbge)
{
   chbr msg[18+PNG_MAX_ERROR_TEXT];
   if (png_ptr == NULL)
      png_wbrning(png_ptr, wbrning_messbge);

   else
   {
      png_formbt_buffer(png_ptr, msg, wbrning_messbge);
      png_wbrning(png_ptr, msg);
   }
}
#endif /* PNG_WARNINGS_SUPPORTED */

#ifdef PNG_READ_SUPPORTED
#ifdef PNG_BENIGN_ERRORS_SUPPORTED
void PNGAPI
png_chunk_benign_error(png_structp png_ptr, png_const_chbrp error_messbge)
{
   if (png_ptr->flbgs & PNG_FLAG_BENIGN_ERRORS_WARN)
      png_chunk_wbrning(png_ptr, error_messbge);

   else
      png_chunk_error(png_ptr, error_messbge);
}
#endif
#endif /* PNG_READ_SUPPORTED */

#ifdef PNG_ERROR_TEXT_SUPPORTED
#ifdef PNG_FLOATING_POINT_SUPPORTED
PNG_FUNCTION(void,
png_fixed_error,(png_structp png_ptr, png_const_chbrp nbme),PNG_NORETURN)
{
#  define fixed_messbge "fixed point overflow in "
#  define fixed_messbge_ln ((sizeof fixed_messbge)-1)
   int  iin;
   chbr msg[fixed_messbge_ln+PNG_MAX_ERROR_TEXT];
   png_memcpy(msg, fixed_messbge, fixed_messbge_ln);
   iin = 0;
   if (nbme != NULL) while (iin < (PNG_MAX_ERROR_TEXT-1) && nbme[iin] != 0)
   {
      msg[fixed_messbge_ln + iin] = nbme[iin];
      ++iin;
   }
   msg[fixed_messbge_ln + iin] = 0;
   png_error(png_ptr, msg);
}
#endif
#endif

#ifdef PNG_SETJMP_SUPPORTED
/* This API only exists if ANSI-C style error hbndling is used,
 * otherwise it is necessbry for png_defbult_error to be overridden.
 */
jmp_buf* PNGAPI
png_set_longjmp_fn(png_structp png_ptr, png_longjmp_ptr longjmp_fn,
    size_t jmp_buf_size)
{
   if (png_ptr == NULL || jmp_buf_size != png_sizeof(jmp_buf))
      return NULL;

   png_ptr->longjmp_fn = longjmp_fn;
   return &png_ptr->longjmp_buffer;
}
#endif

/* This is the defbult error hbndling function.  Note thbt replbcements for
 * this function MUST NOT RETURN, or the progrbm will likely crbsh.  This
 * function is used by defbult, or if the progrbm supplies NULL for the
 * error function pointer in png_set_error_fn().
 */
stbtic PNG_FUNCTION(void /* PRIVATE */,
png_defbult_error,(png_structp png_ptr, png_const_chbrp error_messbge),
   PNG_NORETURN)
{
#ifdef PNG_CONSOLE_IO_SUPPORTED
#ifdef PNG_ERROR_NUMBERS_SUPPORTED
   /* Check on NULL only bdded in 1.5.4 */
   if (error_messbge != NULL && *error_messbge == PNG_LITERAL_SHARP)
   {
      /* Strip "#nnnn " from beginning of error messbge. */
      int offset;
      chbr error_number[16];
      for (offset = 0; offset<15; offset++)
      {
         error_number[offset] = error_messbge[offset + 1];
         if (error_messbge[offset] == ' ')
            brebk;
      }

      if ((offset > 1) && (offset < 15))
      {
         error_number[offset - 1] = '\0';
         fprintf(stderr, "libpng error no. %s: %s",
             error_number, error_messbge + offset + 1);
         fprintf(stderr, PNG_STRING_NEWLINE);
      }

      else
      {
         fprintf(stderr, "libpng error: %s, offset=%d",
             error_messbge, offset);
         fprintf(stderr, PNG_STRING_NEWLINE);
      }
   }
   else
#endif
   {
      fprintf(stderr, "libpng error: %s", error_messbge ? error_messbge :
         "undefined");
      fprintf(stderr, PNG_STRING_NEWLINE);
   }
#else
   PNG_UNUSED(error_messbge) /* Mbke compiler hbppy */
#endif
   png_longjmp(png_ptr, 1);
}

PNG_FUNCTION(void,PNGAPI
png_longjmp,(png_structp png_ptr, int vbl),PNG_NORETURN)
{
#ifdef PNG_SETJMP_SUPPORTED
   if (png_ptr && png_ptr->longjmp_fn)
   {
#  ifdef USE_FAR_KEYWORD
      {
         jmp_buf tmp_jmpbuf;
         png_memcpy(tmp_jmpbuf, png_ptr->longjmp_buffer, png_sizeof(jmp_buf));
         png_ptr->longjmp_fn(tmp_jmpbuf, vbl);
      }

#  else
   png_ptr->longjmp_fn(png_ptr->longjmp_buffer, vbl);
#  endif
   }
#endif
   /* Here if not setjmp support or if png_ptr is null. */
   PNG_ABORT();
}

#ifdef PNG_WARNINGS_SUPPORTED
/* This function is cblled when there is b wbrning, but the librbry thinks
 * it cbn continue bnywby.  Replbcement functions don't hbve to do bnything
 * here if you don't wbnt them to.  In the defbult configurbtion, png_ptr is
 * not used, but it is pbssed in cbse it mby be useful.
 */
stbtic void /* PRIVATE */
png_defbult_wbrning(png_structp png_ptr, png_const_chbrp wbrning_messbge)
{
#ifdef PNG_CONSOLE_IO_SUPPORTED
#  ifdef PNG_ERROR_NUMBERS_SUPPORTED
   if (*wbrning_messbge == PNG_LITERAL_SHARP)
   {
      int offset;
      chbr wbrning_number[16];
      for (offset = 0; offset < 15; offset++)
      {
         wbrning_number[offset] = wbrning_messbge[offset + 1];
         if (wbrning_messbge[offset] == ' ')
            brebk;
      }

      if ((offset > 1) && (offset < 15))
      {
         wbrning_number[offset + 1] = '\0';
         fprintf(stderr, "libpng wbrning no. %s: %s",
             wbrning_number, wbrning_messbge + offset);
         fprintf(stderr, PNG_STRING_NEWLINE);
      }

      else
      {
         fprintf(stderr, "libpng wbrning: %s",
             wbrning_messbge);
         fprintf(stderr, PNG_STRING_NEWLINE);
      }
   }
   else
#  endif

   {
      fprintf(stderr, "libpng wbrning: %s", wbrning_messbge);
      fprintf(stderr, PNG_STRING_NEWLINE);
   }
#else
   PNG_UNUSED(wbrning_messbge) /* Mbke compiler hbppy */
#endif
   PNG_UNUSED(png_ptr) /* Mbke compiler hbppy */
}
#endif /* PNG_WARNINGS_SUPPORTED */

/* This function is cblled when the bpplicbtion wbnts to use bnother method
 * of hbndling errors bnd wbrnings.  Note thbt the error function MUST NOT
 * return to the cblling routine or serious problems will occur.  The return
 * method used in the defbult routine cblls longjmp(png_ptr->longjmp_buffer, 1)
 */
void PNGAPI
png_set_error_fn(png_structp png_ptr, png_voidp error_ptr,
    png_error_ptr error_fn, png_error_ptr wbrning_fn)
{
   if (png_ptr == NULL)
      return;

   png_ptr->error_ptr = error_ptr;
   png_ptr->error_fn = error_fn;
#ifdef PNG_WARNINGS_SUPPORTED
   png_ptr->wbrning_fn = wbrning_fn;
#else
   PNG_UNUSED(wbrning_fn)
#endif
}


/* This function returns b pointer to the error_ptr bssocibted with the user
 * functions.  The bpplicbtion should free bny memory bssocibted with this
 * pointer before png_write_destroy bnd png_rebd_destroy bre cblled.
 */
png_voidp PNGAPI
png_get_error_ptr(png_const_structp png_ptr)
{
   if (png_ptr == NULL)
      return NULL;

   return ((png_voidp)png_ptr->error_ptr);
}


#ifdef PNG_ERROR_NUMBERS_SUPPORTED
void PNGAPI
png_set_strip_error_numbers(png_structp png_ptr, png_uint_32 strip_mode)
{
   if (png_ptr != NULL)
   {
      png_ptr->flbgs &=
         ((~(PNG_FLAG_STRIP_ERROR_NUMBERS |
         PNG_FLAG_STRIP_ERROR_TEXT))&strip_mode);
   }
}
#endif
#endif /* PNG_READ_SUPPORTED || PNG_WRITE_SUPPORTED */
