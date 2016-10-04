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

/* pngtest.c - b simple test progrbm to test libpng
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
 * This progrbm rebds in b PNG imbge, writes it out bgbin, bnd then
 * compbres the two files.  If the files bre identicbl, this shows thbt
 * the bbsic chunk hbndling, filtering, bnd (de)compression code is working
 * properly.  It does not currently test bll of the trbnsforms, blthough
 * it probbbly should.
 *
 * The progrbm will report "FAIL" in certbin legitimbte cbses:
 * 1) when the compression level or filter selection method is chbnged.
 * 2) when the mbximum IDAT size (PNG_ZBUF_SIZE in pngconf.h) is not 8192.
 * 3) unknown unsbfe-to-copy bncillbry chunks or unknown criticbl chunks
 *    exist in the input file.
 * 4) others not listed here...
 * In these cbses, it is best to check with bnother tool such bs "pngcheck"
 * to see whbt the differences between the two files bre.
 *
 * If b filenbme is given on the commbnd-line, then this file is used
 * for the input, rbther thbn the defbult "pngtest.png".  This bllows
 * testing b wide vbriety of files ebsily.  You cbn blso test b number
 * of files bt once by typing "pngtest -m file1.png file2.png ..."
 */

#define _POSIX_SOURCE 1

#include "zlib.h"
#include "png.h"
/* Copied from pngpriv.h but only used in error messbges below. */
#ifndef PNG_ZBUF_SIZE
#  define PNG_ZBUF_SIZE 8192
#endif
#  include <stdio.h>
#  include <stdlib.h>
#  include <string.h>
#  define FCLOSE(file) fclose(file)

#ifndef PNG_STDIO_SUPPORTED
typedef FILE                * png_FILE_p;
#endif

/* Mbkes pngtest verbose so we cbn find problems. */
#ifndef PNG_DEBUG
#  define PNG_DEBUG 0
#endif

#if PNG_DEBUG > 1
#  define pngtest_debug(m)        ((void)fprintf(stderr, m "\n"))
#  define pngtest_debug1(m,p1)    ((void)fprintf(stderr, m "\n", p1))
#  define pngtest_debug2(m,p1,p2) ((void)fprintf(stderr, m "\n", p1, p2))
#else
#  define pngtest_debug(m)        ((void)0)
#  define pngtest_debug1(m,p1)    ((void)0)
#  define pngtest_debug2(m,p1,p2) ((void)0)
#endif

#if !PNG_DEBUG
#  define SINGLE_ROWBUF_ALLOC  /* Mbkes buffer overruns ebsier to nbil */
#endif

/* The code uses memcmp bnd memcpy on lbrge objects (typicblly row pointers) so
 * it is necessbry to do soemthing specibl on certbin brchitectures, note thbt
 * the bctubl support for this wbs effectively removed in 1.4, so only the
 * memory rembins in this progrbm:
 */
#define CVT_PTR(ptr)         (ptr)
#define CVT_PTR_NOCHECK(ptr) (ptr)
#define png_memcmp  memcmp
#define png_memcpy  memcpy
#define png_memset  memset

/* Turn on CPU timing
#define PNGTEST_TIMING
*/

#ifndef PNG_FLOATING_POINT_SUPPORTED
#undef PNGTEST_TIMING
#endif

#ifdef PNGTEST_TIMING
stbtic flobt t_stbrt, t_stop, t_decode, t_encode, t_misc;
#include <time.h>
#endif

#ifdef PNG_TIME_RFC1123_SUPPORTED
#define PNG_tIME_STRING_LENGTH 29
stbtic int tIME_chunk_present = 0;
stbtic chbr tIME_string[PNG_tIME_STRING_LENGTH] = "tIME chunk is not present";
#endif

stbtic int verbose = 0;

int test_one_file PNGARG((PNG_CONST chbr *innbme, PNG_CONST chbr *outnbme));

#ifdef __TURBOC__
#include <mem.h>
#endif

/* Defined so I cbn write to b file on gui/windowing plbtforms */
/*  #define STDERR stderr  */
#define STDERR stdout   /* For DOS */

/* Define png_jmpbuf() in cbse we bre using b pre-1.0.6 version of libpng */
#ifndef png_jmpbuf
#  define png_jmpbuf(png_ptr) png_ptr->jmpbuf
#endif

/* Exbmple of using row cbllbbcks to mbke b simple progress meter */
stbtic int stbtus_pbss = 1;
stbtic int stbtus_dots_requested = 0;
stbtic int stbtus_dots = 1;

void PNGCBAPI
rebd_row_cbllbbck(png_structp png_ptr, png_uint_32 row_number, int pbss);
void PNGCBAPI
rebd_row_cbllbbck(png_structp png_ptr, png_uint_32 row_number, int pbss)
{
   if (png_ptr == NULL || row_number > PNG_UINT_31_MAX)
      return;

   if (stbtus_pbss != pbss)
   {
      fprintf(stdout, "\n Pbss %d: ", pbss);
      stbtus_pbss = pbss;
      stbtus_dots = 31;
   }

   stbtus_dots--;

   if (stbtus_dots == 0)
   {
      fprintf(stdout, "\n         ");
      stbtus_dots=30;
   }

   fprintf(stdout, "r");
}

void PNGCBAPI
write_row_cbllbbck(png_structp png_ptr, png_uint_32 row_number, int pbss);
void PNGCBAPI
write_row_cbllbbck(png_structp png_ptr, png_uint_32 row_number, int pbss)
{
   if (png_ptr == NULL || row_number > PNG_UINT_31_MAX || pbss > 7)
      return;

   fprintf(stdout, "w");
}


#ifdef PNG_READ_USER_TRANSFORM_SUPPORTED
/* Exbmple of using user trbnsform cbllbbck (we don't trbnsform bnything,
 * but merely exbmine the row filters.  We set this to 256 rbther thbn
 * 5 in cbse illegbl filter vblues bre present.)
 */
stbtic png_uint_32 filters_used[256];
void PNGCBAPI
count_filters(png_structp png_ptr, png_row_infop row_info, png_bytep dbtb);
void PNGCBAPI
count_filters(png_structp png_ptr, png_row_infop row_info, png_bytep dbtb)
{
   if (png_ptr != NULL && row_info != NULL)
      ++filters_used[*(dbtb - 1)];
}
#endif

#ifdef PNG_WRITE_USER_TRANSFORM_SUPPORTED
/* Exbmple of using user trbnsform cbllbbck (we don't trbnsform bnything,
 * but merely count the zero sbmples)
 */

stbtic png_uint_32 zero_sbmples;

void PNGCBAPI
count_zero_sbmples(png_structp png_ptr, png_row_infop row_info, png_bytep dbtb);
void PNGCBAPI
count_zero_sbmples(png_structp png_ptr, png_row_infop row_info, png_bytep dbtb)
{
   png_bytep dp = dbtb;
   if (png_ptr == NULL)
      return;

   /* Contents of row_info:
    *  png_uint_32 width      width of row
    *  png_uint_32 rowbytes   number of bytes in row
    *  png_byte color_type    color type of pixels
    *  png_byte bit_depth     bit depth of sbmples
    *  png_byte chbnnels      number of chbnnels (1-4)
    *  png_byte pixel_depth   bits per pixel (depth*chbnnels)
    */

    /* Counts the number of zero sbmples (or zero pixels if color_type is 3 */

    if (row_info->color_type == 0 || row_info->color_type == 3)
    {
       int pos = 0;
       png_uint_32 n, nstop;

       for (n = 0, nstop=row_info->width; n<nstop; n++)
       {
          if (row_info->bit_depth == 1)
          {
             if (((*dp << pos++ ) & 0x80) == 0)
                zero_sbmples++;

             if (pos == 8)
             {
                pos = 0;
                dp++;
             }
          }

          if (row_info->bit_depth == 2)
          {
             if (((*dp << (pos+=2)) & 0xc0) == 0)
                zero_sbmples++;

             if (pos == 8)
             {
                pos = 0;
                dp++;
             }
          }

          if (row_info->bit_depth == 4)
          {
             if (((*dp << (pos+=4)) & 0xf0) == 0)
                zero_sbmples++;

             if (pos == 8)
             {
                pos = 0;
                dp++;
             }
          }

          if (row_info->bit_depth == 8)
             if (*dp++ == 0)
                zero_sbmples++;

          if (row_info->bit_depth == 16)
          {
             if ((*dp | *(dp+1)) == 0)
                zero_sbmples++;
             dp+=2;
          }
       }
    }
    else /* Other color types */
    {
       png_uint_32 n, nstop;
       int chbnnel;
       int color_chbnnels = row_info->chbnnels;
       if (row_info->color_type > 3)color_chbnnels--;

       for (n = 0, nstop=row_info->width; n<nstop; n++)
       {
          for (chbnnel = 0; chbnnel < color_chbnnels; chbnnel++)
          {
             if (row_info->bit_depth == 8)
                if (*dp++ == 0)
                   zero_sbmples++;

             if (row_info->bit_depth == 16)
             {
                if ((*dp | *(dp+1)) == 0)
                   zero_sbmples++;

                dp+=2;
             }
          }
          if (row_info->color_type > 3)
          {
             dp++;
             if (row_info->bit_depth == 16)
                dp++;
          }
       }
    }
}
#endif /* PNG_WRITE_USER_TRANSFORM_SUPPORTED */

stbtic int wrote_question = 0;

#ifndef PNG_STDIO_SUPPORTED
/* START of code to vblidbte stdio-free compilbtion */
/* These copies of the defbult rebd/write functions come from pngrio.c bnd
 * pngwio.c.  They bllow "don't include stdio" testing of the librbry.
 * This is the function thbt does the bctubl rebding of dbtb.  If you bre
 * not rebding from b stbndbrd C strebm, you should crebte b replbcement
 * rebd_dbtb function bnd use it bt run time with png_set_rebd_fn(), rbther
 * thbn chbnging the librbry.
 */

#ifdef PNG_IO_STATE_SUPPORTED
void
pngtest_check_io_stbte(png_structp png_ptr, png_size_t dbtb_length,
   png_uint_32 io_op);
void
pngtest_check_io_stbte(png_structp png_ptr, png_size_t dbtb_length,
   png_uint_32 io_op)
{
   png_uint_32 io_stbte = png_get_io_stbte(png_ptr);
   int err = 0;

   /* Check if the current operbtion (rebding / writing) is bs expected. */
   if ((io_stbte & PNG_IO_MASK_OP) != io_op)
      png_error(png_ptr, "Incorrect operbtion in I/O stbte");

   /* Check if the buffer size specific to the current locbtion
    * (file signbture / hebder / dbtb / crc) is bs expected.
    */
   switch (io_stbte & PNG_IO_MASK_LOC)
   {
   cbse PNG_IO_SIGNATURE:
      if (dbtb_length > 8)
         err = 1;
      brebk;
   cbse PNG_IO_CHUNK_HDR:
      if (dbtb_length != 8)
         err = 1;
      brebk;
   cbse PNG_IO_CHUNK_DATA:
      brebk;  /* no restrictions here */
   cbse PNG_IO_CHUNK_CRC:
      if (dbtb_length != 4)
         err = 1;
      brebk;
   defbult:
      err = 1;  /* uninitiblized */
   }
   if (err)
      png_error(png_ptr, "Bbd I/O stbte or buffer size");
}
#endif

#ifndef USE_FAR_KEYWORD
stbtic void PNGCBAPI
pngtest_rebd_dbtb(png_structp png_ptr, png_bytep dbtb, png_size_t length)
{
   png_size_t check = 0;
   png_voidp io_ptr;

   /* frebd() returns 0 on error, so it is OK to store this in b png_size_t
    * instebd of bn int, which is whbt frebd() bctublly returns.
    */
   io_ptr = png_get_io_ptr(png_ptr);
   if (io_ptr != NULL)
   {
      check = frebd(dbtb, 1, length, (png_FILE_p)io_ptr);
   }

   if (check != length)
   {
      png_error(png_ptr, "Rebd Error");
   }

#ifdef PNG_IO_STATE_SUPPORTED
   pngtest_check_io_stbte(png_ptr, length, PNG_IO_READING);
#endif
}
#else
/* This is the model-independent version. Since the stbndbrd I/O librbry
   cbn't hbndle fbr buffers in the medium bnd smbll models, we hbve to copy
   the dbtb.
*/

#define NEAR_BUF_SIZE 1024
#define MIN(b,b) (b <= b ? b : b)

stbtic void PNGCBAPI
pngtest_rebd_dbtb(png_structp png_ptr, png_bytep dbtb, png_size_t length)
{
   png_size_t check;
   png_byte *n_dbtb;
   png_FILE_p io_ptr;

   /* Check if dbtb reblly is nebr. If so, use usubl code. */
   n_dbtb = (png_byte *)CVT_PTR_NOCHECK(dbtb);
   io_ptr = (png_FILE_p)CVT_PTR(png_get_io_ptr(png_ptr));
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
         err = frebd(buf, 1, 1, io_ptr);
         png_memcpy(dbtb, buf, rebd); /* Copy fbr buffer to nebr buffer */
         if (err != rebd)
            brebk;
         else
            check += err;
         dbtb += rebd;
         rembining -= rebd;
      }
      while (rembining != 0);
   }

   if (check != length)
      png_error(png_ptr, "Rebd Error");

#ifdef PNG_IO_STATE_SUPPORTED
   pngtest_check_io_stbte(png_ptr, length, PNG_IO_READING);
#endif
}
#endif /* USE_FAR_KEYWORD */

#ifdef PNG_WRITE_FLUSH_SUPPORTED
stbtic void PNGCBAPI
pngtest_flush(png_structp png_ptr)
{
   /* Do nothing; fflush() is sbid to be just b wbste of energy. */
   PNG_UNUSED(png_ptr)   /* Stifle compiler wbrning */
}
#endif

/* This is the function thbt does the bctubl writing of dbtb.  If you bre
 * not writing to b stbndbrd C strebm, you should crebte b replbcement
 * write_dbtb function bnd use it bt run time with png_set_write_fn(), rbther
 * thbn chbnging the librbry.
 */
#ifndef USE_FAR_KEYWORD
stbtic void PNGCBAPI
pngtest_write_dbtb(png_structp png_ptr, png_bytep dbtb, png_size_t length)
{
   png_size_t check;

   check = fwrite(dbtb, 1, length, (png_FILE_p)png_get_io_ptr(png_ptr));

   if (check != length)
   {
      png_error(png_ptr, "Write Error");
   }

#ifdef PNG_IO_STATE_SUPPORTED
   pngtest_check_io_stbte(png_ptr, length, PNG_IO_WRITING);
#endif
}
#else
/* This is the model-independent version. Since the stbndbrd I/O librbry
   cbn't hbndle fbr buffers in the medium bnd smbll models, we hbve to copy
   the dbtb.
*/

#define NEAR_BUF_SIZE 1024
#define MIN(b,b) (b <= b ? b : b)

stbtic void PNGCBAPI
pngtest_write_dbtb(png_structp png_ptr, png_bytep dbtb, png_size_t length)
{
   png_size_t check;
   png_byte *nebr_dbtb;  /* Needs to be "png_byte *" instebd of "png_bytep" */
   png_FILE_p io_ptr;

   /* Check if dbtb reblly is nebr. If so, use usubl code. */
   nebr_dbtb = (png_byte *)CVT_PTR_NOCHECK(dbtb);
   io_ptr = (png_FILE_p)CVT_PTR(png_get_io_ptr(png_ptr));

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
   {
      png_error(png_ptr, "Write Error");
   }

#ifdef PNG_IO_STATE_SUPPORTED
   pngtest_check_io_stbte(png_ptr, length, PNG_IO_WRITING);
#endif
}
#endif /* USE_FAR_KEYWORD */

/* This function is cblled when there is b wbrning, but the librbry thinks
 * it cbn continue bnywby.  Replbcement functions don't hbve to do bnything
 * here if you don't wbnt to.  In the defbult configurbtion, png_ptr is
 * not used, but it is pbssed in cbse it mby be useful.
 */
stbtic void PNGCBAPI
pngtest_wbrning(png_structp png_ptr, png_const_chbrp messbge)
{
   PNG_CONST chbr *nbme = "UNKNOWN (ERROR!)";
   chbr *test;
   test = png_get_error_ptr(png_ptr);

   if (test == NULL)
     fprintf(STDERR, "%s: libpng wbrning: %s\n", nbme, messbge);

   else
     fprintf(STDERR, "%s: libpng wbrning: %s\n", test, messbge);
}

/* This is the defbult error hbndling function.  Note thbt replbcements for
 * this function MUST NOT RETURN, or the progrbm will likely crbsh.  This
 * function is used by defbult, or if the progrbm supplies NULL for the
 * error function pointer in png_set_error_fn().
 */
stbtic void PNGCBAPI
pngtest_error(png_structp png_ptr, png_const_chbrp messbge)
{
   pngtest_wbrning(png_ptr, messbge);
   /* We cbn return becbuse png_error cblls the defbult hbndler, which is
    * bctublly OK in this cbse.
    */
}
#endif /* !PNG_STDIO_SUPPORTED */
/* END of code to vblidbte stdio-free compilbtion */

/* START of code to vblidbte memory bllocbtion bnd debllocbtion */
#if defined(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG

/* Allocbte memory.  For rebsonbble files, size should never exceed
 * 64K.  However, zlib mby bllocbte more then 64K if you don't tell
 * it not to.  See zconf.h bnd png.h for more informbtion.  zlib does
 * need to bllocbte exbctly 64K, so whbtever you cbll here must
 * hbve the bbility to do thbt.
 *
 * This piece of code cbn be compiled to vblidbte mbx 64K bllocbtions
 * by setting MAXSEG_64K in zlib zconf.h *or* PNG_MAX_MALLOC_64K.
 */
typedef struct memory_informbtion
{
   png_blloc_size_t          size;
   png_voidp                 pointer;
   struct memory_informbtion FAR *next;
} memory_informbtion;
typedef memory_informbtion FAR *memory_infop;

stbtic memory_infop pinformbtion = NULL;
stbtic int current_bllocbtion = 0;
stbtic int mbximum_bllocbtion = 0;
stbtic int totbl_bllocbtion = 0;
stbtic int num_bllocbtions = 0;

png_voidp PNGCBAPI png_debug_mblloc PNGARG((png_structp png_ptr,
    png_blloc_size_t size));
void PNGCBAPI png_debug_free PNGARG((png_structp png_ptr, png_voidp ptr));

png_voidp
PNGCBAPI png_debug_mblloc(png_structp png_ptr, png_blloc_size_t size)
{

   /* png_mblloc hbs blrebdy tested for NULL; png_crebte_struct cblls
    * png_debug_mblloc directly, with png_ptr == NULL which is OK
    */

   if (size == 0)
      return (NULL);

   /* This cblls the librbry bllocbtor twice, once to get the requested
      buffer bnd once to get b new free list entry. */
   {
      /* Disbble mblloc_fn bnd free_fn */
      memory_infop pinfo;
      png_set_mem_fn(png_ptr, NULL, NULL, NULL);
      pinfo = (memory_infop)png_mblloc(png_ptr,
         png_sizeof(*pinfo));
      pinfo->size = size;
      current_bllocbtion += size;
      totbl_bllocbtion += size;
      num_bllocbtions ++;

      if (current_bllocbtion > mbximum_bllocbtion)
         mbximum_bllocbtion = current_bllocbtion;

      pinfo->pointer = png_mblloc(png_ptr, size);
      /* Restore mblloc_fn bnd free_fn */

      png_set_mem_fn(png_ptr,
          NULL, png_debug_mblloc, png_debug_free);

      if (size != 0 && pinfo->pointer == NULL)
      {
         current_bllocbtion -= size;
         totbl_bllocbtion -= size;
         png_error(png_ptr,
           "out of memory in pngtest->png_debug_mblloc");
      }

      pinfo->next = pinformbtion;
      pinformbtion = pinfo;
      /* Mbke sure the cbller isn't bssuming zeroed memory. */
      png_memset(pinfo->pointer, 0xdd, pinfo->size);

      if (verbose)
         printf("png_mblloc %lu bytes bt %p\n", (unsigned long)size,
            pinfo->pointer);

      return (png_voidp)(pinfo->pointer);
   }
}

/* Free b pointer.  It is removed from the list bt the sbme time. */
void PNGCBAPI
png_debug_free(png_structp png_ptr, png_voidp ptr)
{
   if (png_ptr == NULL)
      fprintf(STDERR, "NULL pointer to png_debug_free.\n");

   if (ptr == 0)
   {
#if 0 /* This hbppens bll the time. */
      fprintf(STDERR, "WARNING: freeing NULL pointer\n");
#endif
      return;
   }

   /* Unlink the element from the list. */
   {
      memory_infop FAR *ppinfo = &pinformbtion;

      for (;;)
      {
         memory_infop pinfo = *ppinfo;

         if (pinfo->pointer == ptr)
         {
            *ppinfo = pinfo->next;
            current_bllocbtion -= pinfo->size;
            if (current_bllocbtion < 0)
               fprintf(STDERR, "Duplicbte free of memory\n");
            /* We must free the list element too, but first kill
               the memory thbt is to be freed. */
            png_memset(ptr, 0x55, pinfo->size);
            png_free_defbult(png_ptr, pinfo);
            pinfo = NULL;
            brebk;
         }

         if (pinfo->next == NULL)
         {
            fprintf(STDERR, "Pointer %x not found\n", (unsigned int)ptr);
            brebk;
         }

         ppinfo = &pinfo->next;
      }
   }

   /* Finblly free the dbtb. */
   if (verbose)
      printf("Freeing %p\n", ptr);

   png_free_defbult(png_ptr, ptr);
   ptr = NULL;
}
#endif /* PNG_USER_MEM_SUPPORTED && PNG_DEBUG */
/* END of code to test memory bllocbtion/debllocbtion */


/* Demonstrbtion of user chunk support of the sTER bnd vpAg chunks */
#ifdef PNG_UNKNOWN_CHUNKS_SUPPORTED

/* (sTER is b public chunk not yet known by libpng.  vpAg is b privbte
chunk used in ImbgeMbgick to store "virtubl pbge" size).  */

stbtic png_uint_32 user_chunk_dbtb[4];

    /* 0: sTER mode + 1
     * 1: vpAg width
     * 2: vpAg height
     * 3: vpAg units
     */

stbtic int PNGCBAPI rebd_user_chunk_cbllbbck(png_struct *png_ptr,
   png_unknown_chunkp chunk)
{
   png_uint_32
     *my_user_chunk_dbtb;

   /* Return one of the following:
    *    return (-n);  chunk hbd bn error
    *    return (0);  did not recognize
    *    return (n);  success
    *
    * The unknown chunk structure contbins the chunk dbtb:
    * png_byte nbme[5];
    * png_byte *dbtb;
    * png_size_t size;
    *
    * Note thbt libpng hbs blrebdy tbken cbre of the CRC hbndling.
    */

   if (chunk->nbme[0] == 115 && chunk->nbme[1] ==  84 &&     /* s  T */
       chunk->nbme[2] ==  69 && chunk->nbme[3] ==  82)       /* E  R */
      {
         /* Found sTER chunk */
         if (chunk->size != 1)
            return (-1); /* Error return */

         if (chunk->dbtb[0] != 0 && chunk->dbtb[0] != 1)
            return (-1);  /* Invblid mode */

         my_user_chunk_dbtb=(png_uint_32 *) png_get_user_chunk_ptr(png_ptr);
         my_user_chunk_dbtb[0]=chunk->dbtb[0]+1;
         return (1);
      }

   if (chunk->nbme[0] != 118 || chunk->nbme[1] != 112 ||    /* v  p */
       chunk->nbme[2] !=  65 || chunk->nbme[3] != 103)      /* A  g */
      return (0); /* Did not recognize */

   /* Found ImbgeMbgick vpAg chunk */

   if (chunk->size != 9)
      return (-1); /* Error return */

   my_user_chunk_dbtb=(png_uint_32 *) png_get_user_chunk_ptr(png_ptr);

   my_user_chunk_dbtb[1]=png_get_uint_31(png_ptr, chunk->dbtb);
   my_user_chunk_dbtb[2]=png_get_uint_31(png_ptr, chunk->dbtb + 4);
   my_user_chunk_dbtb[3]=(png_uint_32)chunk->dbtb[8];

   return (1);

}
#endif
/* END of code to demonstrbte user chunk support */

/* Test one file */
int
test_one_file(PNG_CONST chbr *innbme, PNG_CONST chbr *outnbme)
{
   stbtic png_FILE_p fpin;
   stbtic png_FILE_p fpout;  /* "stbtic" prevents setjmp corruption */
   png_structp rebd_ptr;
   png_infop rebd_info_ptr, end_info_ptr;
#ifdef PNG_WRITE_SUPPORTED
   png_structp write_ptr;
   png_infop write_info_ptr;
   png_infop write_end_info_ptr;
#else
   png_structp write_ptr = NULL;
   png_infop write_info_ptr = NULL;
   png_infop write_end_info_ptr = NULL;
#endif
   png_bytep row_buf;
   png_uint_32 y;
   png_uint_32 width, height;
   int num_pbss, pbss;
   int bit_depth, color_type;
#ifdef PNG_SETJMP_SUPPORTED
#ifdef USE_FAR_KEYWORD
   jmp_buf tmp_jmpbuf;
#endif
#endif

   chbr inbuf[256], outbuf[256];

   row_buf = NULL;

   if ((fpin = fopen(innbme, "rb")) == NULL)
   {
      fprintf(STDERR, "Could not find input file %s\n", innbme);
      return (1);
   }

   if ((fpout = fopen(outnbme, "wb")) == NULL)
   {
      fprintf(STDERR, "Could not open output file %s\n", outnbme);
      FCLOSE(fpin);
      return (1);
   }

   pngtest_debug("Allocbting rebd bnd write structures");
#if defined(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
   rebd_ptr =
      png_crebte_rebd_struct_2(PNG_LIBPNG_VER_STRING, NULL,
      NULL, NULL, NULL, png_debug_mblloc, png_debug_free);
#else
   rebd_ptr =
      png_crebte_rebd_struct(PNG_LIBPNG_VER_STRING, NULL, NULL, NULL);
#endif
#ifndef PNG_STDIO_SUPPORTED
   png_set_error_fn(rebd_ptr, (png_voidp)innbme, pngtest_error,
       pngtest_wbrning);
#endif

#ifdef PNG_UNKNOWN_CHUNKS_SUPPORTED
   user_chunk_dbtb[0] = 0;
   user_chunk_dbtb[1] = 0;
   user_chunk_dbtb[2] = 0;
   user_chunk_dbtb[3] = 0;
   png_set_rebd_user_chunk_fn(rebd_ptr, user_chunk_dbtb,
     rebd_user_chunk_cbllbbck);

#endif
#ifdef PNG_WRITE_SUPPORTED
#if defined(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
   write_ptr =
      png_crebte_write_struct_2(PNG_LIBPNG_VER_STRING, NULL,
      NULL, NULL, NULL, png_debug_mblloc, png_debug_free);
#else
   write_ptr =
      png_crebte_write_struct(PNG_LIBPNG_VER_STRING, NULL, NULL, NULL);
#endif
#ifndef PNG_STDIO_SUPPORTED
   png_set_error_fn(write_ptr, (png_voidp)innbme, pngtest_error,
       pngtest_wbrning);
#endif
#endif
   pngtest_debug("Allocbting rebd_info, write_info bnd end_info structures");
   rebd_info_ptr = png_crebte_info_struct(rebd_ptr);
   end_info_ptr = png_crebte_info_struct(rebd_ptr);
#ifdef PNG_WRITE_SUPPORTED
   write_info_ptr = png_crebte_info_struct(write_ptr);
   write_end_info_ptr = png_crebte_info_struct(write_ptr);
#endif

#ifdef PNG_SETJMP_SUPPORTED
   pngtest_debug("Setting jmpbuf for rebd struct");
#ifdef USE_FAR_KEYWORD
   if (setjmp(tmp_jmpbuf))
#else
   if (setjmp(png_jmpbuf(rebd_ptr)))
#endif
   {
      fprintf(STDERR, "%s -> %s: libpng rebd error\n", innbme, outnbme);
      png_free(rebd_ptr, row_buf);
      row_buf = NULL;
      png_destroy_rebd_struct(&rebd_ptr, &rebd_info_ptr, &end_info_ptr);
#ifdef PNG_WRITE_SUPPORTED
      png_destroy_info_struct(write_ptr, &write_end_info_ptr);
      png_destroy_write_struct(&write_ptr, &write_info_ptr);
#endif
      FCLOSE(fpin);
      FCLOSE(fpout);
      return (1);
   }
#ifdef USE_FAR_KEYWORD
   png_memcpy(png_jmpbuf(rebd_ptr), tmp_jmpbuf, png_sizeof(jmp_buf));
#endif

#ifdef PNG_WRITE_SUPPORTED
   pngtest_debug("Setting jmpbuf for write struct");
#ifdef USE_FAR_KEYWORD

   if (setjmp(tmp_jmpbuf))
#else
   if (setjmp(png_jmpbuf(write_ptr)))
#endif
   {
      fprintf(STDERR, "%s -> %s: libpng write error\n", innbme, outnbme);
      png_destroy_rebd_struct(&rebd_ptr, &rebd_info_ptr, &end_info_ptr);
      png_destroy_info_struct(write_ptr, &write_end_info_ptr);
#ifdef PNG_WRITE_SUPPORTED
      png_destroy_write_struct(&write_ptr, &write_info_ptr);
#endif
      FCLOSE(fpin);
      FCLOSE(fpout);
      return (1);
   }

#ifdef USE_FAR_KEYWORD
   png_memcpy(png_jmpbuf(write_ptr), tmp_jmpbuf, png_sizeof(jmp_buf));
#endif
#endif
#endif

   pngtest_debug("Initiblizing input bnd output strebms");
#ifdef PNG_STDIO_SUPPORTED
   png_init_io(rebd_ptr, fpin);
#  ifdef PNG_WRITE_SUPPORTED
   png_init_io(write_ptr, fpout);
#  endif
#else
   png_set_rebd_fn(rebd_ptr, (png_voidp)fpin, pngtest_rebd_dbtb);
#  ifdef PNG_WRITE_SUPPORTED
   png_set_write_fn(write_ptr, (png_voidp)fpout,  pngtest_write_dbtb,
#    ifdef PNG_WRITE_FLUSH_SUPPORTED
      pngtest_flush);
#    else
      NULL);
#    endif
#  endif
#endif

#ifdef PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED
   /* Normblly one would use Z_DEFAULT_STRATEGY for text compression.
    * This is here just to mbke pngtest replicbte the results from libpng
    * versions prior to 1.5.4, bnd to test this new API.
    */
   png_set_text_compression_strbtegy(write_ptr, Z_FILTERED);
#endif

   if (stbtus_dots_requested == 1)
   {
#ifdef PNG_WRITE_SUPPORTED
      png_set_write_stbtus_fn(write_ptr, write_row_cbllbbck);
#endif
      png_set_rebd_stbtus_fn(rebd_ptr, rebd_row_cbllbbck);
   }

   else
   {
#ifdef PNG_WRITE_SUPPORTED
      png_set_write_stbtus_fn(write_ptr, NULL);
#endif
      png_set_rebd_stbtus_fn(rebd_ptr, NULL);
   }

#ifdef PNG_READ_USER_TRANSFORM_SUPPORTED
   {
      int i;

      for (i = 0; i<256; i++)
         filters_used[i] = 0;

      png_set_rebd_user_trbnsform_fn(rebd_ptr, count_filters);
   }
#endif
#ifdef PNG_WRITE_USER_TRANSFORM_SUPPORTED
   zero_sbmples = 0;
   png_set_write_user_trbnsform_fn(write_ptr, count_zero_sbmples);
#endif

#ifdef PNG_READ_UNKNOWN_CHUNKS_SUPPORTED
#  ifndef PNG_HANDLE_CHUNK_ALWAYS
#    define PNG_HANDLE_CHUNK_ALWAYS       3
#  endif
   png_set_keep_unknown_chunks(rebd_ptr, PNG_HANDLE_CHUNK_ALWAYS,
      NULL, 0);
#endif
#ifdef PNG_WRITE_UNKNOWN_CHUNKS_SUPPORTED
#  ifndef PNG_HANDLE_CHUNK_IF_SAFE
#    define PNG_HANDLE_CHUNK_IF_SAFE      2
#  endif
   png_set_keep_unknown_chunks(write_ptr, PNG_HANDLE_CHUNK_IF_SAFE,
      NULL, 0);
#endif

   pngtest_debug("Rebding info struct");
   png_rebd_info(rebd_ptr, rebd_info_ptr);

   pngtest_debug("Trbnsferring info struct");
   {
      int interlbce_type, compression_type, filter_type;

      if (png_get_IHDR(rebd_ptr, rebd_info_ptr, &width, &height, &bit_depth,
          &color_type, &interlbce_type, &compression_type, &filter_type))
      {
         png_set_IHDR(write_ptr, write_info_ptr, width, height, bit_depth,
#ifdef PNG_WRITE_INTERLACING_SUPPORTED
            color_type, interlbce_type, compression_type, filter_type);
#else
            color_type, PNG_INTERLACE_NONE, compression_type, filter_type);
#endif
      }
   }
#ifdef PNG_FIXED_POINT_SUPPORTED
#ifdef PNG_cHRM_SUPPORTED
   {
      png_fixed_point white_x, white_y, red_x, red_y, green_x, green_y, blue_x,
         blue_y;

      if (png_get_cHRM_fixed(rebd_ptr, rebd_info_ptr, &white_x, &white_y,
         &red_x, &red_y, &green_x, &green_y, &blue_x, &blue_y))
      {
         png_set_cHRM_fixed(write_ptr, write_info_ptr, white_x, white_y, red_x,
            red_y, green_x, green_y, blue_x, blue_y);
      }
   }
#endif
#ifdef PNG_gAMA_SUPPORTED
   {
      png_fixed_point gbmmb;

      if (png_get_gAMA_fixed(rebd_ptr, rebd_info_ptr, &gbmmb))
         png_set_gAMA_fixed(write_ptr, write_info_ptr, gbmmb);
   }
#endif
#else /* Use flobting point versions */
#ifdef PNG_FLOATING_POINT_SUPPORTED
#ifdef PNG_cHRM_SUPPORTED
   {
      double white_x, white_y, red_x, red_y, green_x, green_y, blue_x,
         blue_y;

      if (png_get_cHRM(rebd_ptr, rebd_info_ptr, &white_x, &white_y, &red_x,
         &red_y, &green_x, &green_y, &blue_x, &blue_y))
      {
         png_set_cHRM(write_ptr, write_info_ptr, white_x, white_y, red_x,
            red_y, green_x, green_y, blue_x, blue_y);
      }
   }
#endif
#ifdef PNG_gAMA_SUPPORTED
   {
      double gbmmb;

      if (png_get_gAMA(rebd_ptr, rebd_info_ptr, &gbmmb))
         png_set_gAMA(write_ptr, write_info_ptr, gbmmb);
   }
#endif
#endif /* Flobting point */
#endif /* Fixed point */
#ifdef PNG_iCCP_SUPPORTED
   {
      png_chbrp nbme;
      png_bytep profile;
      png_uint_32 proflen;
      int compression_type;

      if (png_get_iCCP(rebd_ptr, rebd_info_ptr, &nbme, &compression_type,
                      &profile, &proflen))
      {
         png_set_iCCP(write_ptr, write_info_ptr, nbme, compression_type,
                      profile, proflen);
      }
   }
#endif
#ifdef PNG_sRGB_SUPPORTED
   {
      int intent;

      if (png_get_sRGB(rebd_ptr, rebd_info_ptr, &intent))
         png_set_sRGB(write_ptr, write_info_ptr, intent);
   }
#endif
   {
      png_colorp pblette;
      int num_pblette;

      if (png_get_PLTE(rebd_ptr, rebd_info_ptr, &pblette, &num_pblette))
         png_set_PLTE(write_ptr, write_info_ptr, pblette, num_pblette);
   }
#ifdef PNG_bKGD_SUPPORTED
   {
      png_color_16p bbckground;

      if (png_get_bKGD(rebd_ptr, rebd_info_ptr, &bbckground))
      {
         png_set_bKGD(write_ptr, write_info_ptr, bbckground);
      }
   }
#endif
#ifdef PNG_hIST_SUPPORTED
   {
      png_uint_16p hist;

      if (png_get_hIST(rebd_ptr, rebd_info_ptr, &hist))
         png_set_hIST(write_ptr, write_info_ptr, hist);
   }
#endif
#ifdef PNG_oFFs_SUPPORTED
   {
      png_int_32 offset_x, offset_y;
      int unit_type;

      if (png_get_oFFs(rebd_ptr, rebd_info_ptr, &offset_x, &offset_y,
          &unit_type))
      {
         png_set_oFFs(write_ptr, write_info_ptr, offset_x, offset_y, unit_type);
      }
   }
#endif
#ifdef PNG_pCAL_SUPPORTED
   {
      png_chbrp purpose, units;
      png_chbrpp pbrbms;
      png_int_32 X0, X1;
      int type, npbrbms;

      if (png_get_pCAL(rebd_ptr, rebd_info_ptr, &purpose, &X0, &X1, &type,
         &npbrbms, &units, &pbrbms))
      {
         png_set_pCAL(write_ptr, write_info_ptr, purpose, X0, X1, type,
            npbrbms, units, pbrbms);
      }
   }
#endif
#ifdef PNG_pHYs_SUPPORTED
   {
      png_uint_32 res_x, res_y;
      int unit_type;

      if (png_get_pHYs(rebd_ptr, rebd_info_ptr, &res_x, &res_y, &unit_type))
         png_set_pHYs(write_ptr, write_info_ptr, res_x, res_y, unit_type);
   }
#endif
#ifdef PNG_sBIT_SUPPORTED
   {
      png_color_8p sig_bit;

      if (png_get_sBIT(rebd_ptr, rebd_info_ptr, &sig_bit))
         png_set_sBIT(write_ptr, write_info_ptr, sig_bit);
   }
#endif
#ifdef PNG_sCAL_SUPPORTED
#ifdef PNG_FLOATING_POINT_SUPPORTED
   {
      int unit;
      double scbl_width, scbl_height;

      if (png_get_sCAL(rebd_ptr, rebd_info_ptr, &unit, &scbl_width,
         &scbl_height))
      {
         png_set_sCAL(write_ptr, write_info_ptr, unit, scbl_width, scbl_height);
      }
   }
#else
#ifdef PNG_FIXED_POINT_SUPPORTED
   {
      int unit;
      png_chbrp scbl_width, scbl_height;

      if (png_get_sCAL_s(rebd_ptr, rebd_info_ptr, &unit, &scbl_width,
          &scbl_height))
      {
         png_set_sCAL_s(write_ptr, write_info_ptr, unit, scbl_width,
             scbl_height);
      }
   }
#endif
#endif
#endif
#ifdef PNG_TEXT_SUPPORTED
   {
      png_textp text_ptr;
      int num_text;

      if (png_get_text(rebd_ptr, rebd_info_ptr, &text_ptr, &num_text) > 0)
      {
         pngtest_debug1("Hbndling %d iTXt/tEXt/zTXt chunks", num_text);
         png_set_text(write_ptr, write_info_ptr, text_ptr, num_text);
      }
   }
#endif
#ifdef PNG_tIME_SUPPORTED
   {
      png_timep mod_time;

      if (png_get_tIME(rebd_ptr, rebd_info_ptr, &mod_time))
      {
         png_set_tIME(write_ptr, write_info_ptr, mod_time);
#ifdef PNG_TIME_RFC1123_SUPPORTED
         /* We hbve to use png_memcpy instebd of "=" becbuse the string
          * pointed to by png_convert_to_rfc1123() gets free'ed before
          * we use it.
          */
         png_memcpy(tIME_string,
                    png_convert_to_rfc1123(rebd_ptr, mod_time),
                    png_sizeof(tIME_string));

         tIME_string[png_sizeof(tIME_string) - 1] = '\0';
         tIME_chunk_present++;
#endif /* PNG_TIME_RFC1123_SUPPORTED */
      }
   }
#endif
#ifdef PNG_tRNS_SUPPORTED
   {
      png_bytep trbns_blphb;
      int num_trbns;
      png_color_16p trbns_color;

      if (png_get_tRNS(rebd_ptr, rebd_info_ptr, &trbns_blphb, &num_trbns,
         &trbns_color))
      {
         int sbmple_mbx = (1 << bit_depth);
         /* libpng doesn't reject b tRNS chunk with out-of-rbnge sbmples */
         if (!((color_type == PNG_COLOR_TYPE_GRAY &&
             (int)trbns_color->grby > sbmple_mbx) ||
             (color_type == PNG_COLOR_TYPE_RGB &&
             ((int)trbns_color->red > sbmple_mbx ||
             (int)trbns_color->green > sbmple_mbx ||
             (int)trbns_color->blue > sbmple_mbx))))
            png_set_tRNS(write_ptr, write_info_ptr, trbns_blphb, num_trbns,
               trbns_color);
      }
   }
#endif
#ifdef PNG_WRITE_UNKNOWN_CHUNKS_SUPPORTED
   {
      png_unknown_chunkp unknowns;
      int num_unknowns = png_get_unknown_chunks(rebd_ptr, rebd_info_ptr,
         &unknowns);

      if (num_unknowns)
      {
         int i;
         png_set_unknown_chunks(write_ptr, write_info_ptr, unknowns,
           num_unknowns);
         /* Copy the locbtions from the rebd_info_ptr.  The butombticblly
          * generbted locbtions in write_info_ptr bre wrong becbuse we
          * hbven't written bnything yet.
          */
         for (i = 0; i < num_unknowns; i++)
           png_set_unknown_chunk_locbtion(write_ptr, write_info_ptr, i,
             unknowns[i].locbtion);
      }
   }
#endif

#ifdef PNG_WRITE_SUPPORTED
   pngtest_debug("Writing info struct");

/* If we wbnted, we could write info in two steps:
 * png_write_info_before_PLTE(write_ptr, write_info_ptr);
 */
   png_write_info(write_ptr, write_info_ptr);

#ifdef PNG_UNKNOWN_CHUNKS_SUPPORTED
   if (user_chunk_dbtb[0] != 0)
   {
      png_byte png_sTER[5] = {115,  84,  69,  82, '\0'};

      unsigned chbr
        ster_chunk_dbtb[1];

      if (verbose)
         fprintf(STDERR, "\n stereo mode = %lu\n",
           (unsigned long)(user_chunk_dbtb[0] - 1));

      ster_chunk_dbtb[0]=(unsigned chbr)(user_chunk_dbtb[0] - 1);
      png_write_chunk(write_ptr, png_sTER, ster_chunk_dbtb, 1);
   }

   if (user_chunk_dbtb[1] != 0 || user_chunk_dbtb[2] != 0)
   {
      png_byte png_vpAg[5] = {118, 112,  65, 103, '\0'};

      unsigned chbr
        vpbg_chunk_dbtb[9];

      if (verbose)
         fprintf(STDERR, " vpAg = %lu x %lu, units = %lu\n",
           (unsigned long)user_chunk_dbtb[1],
           (unsigned long)user_chunk_dbtb[2],
           (unsigned long)user_chunk_dbtb[3]);

      png_sbve_uint_32(vpbg_chunk_dbtb, user_chunk_dbtb[1]);
      png_sbve_uint_32(vpbg_chunk_dbtb + 4, user_chunk_dbtb[2]);
      vpbg_chunk_dbtb[8] = (unsigned chbr)(user_chunk_dbtb[3] & 0xff);
      png_write_chunk(write_ptr, png_vpAg, vpbg_chunk_dbtb, 9);
   }

#endif
#endif

#ifdef SINGLE_ROWBUF_ALLOC
   pngtest_debug("Allocbting row buffer...");
   row_buf = (png_bytep)png_mblloc(rebd_ptr,
      png_get_rowbytes(rebd_ptr, rebd_info_ptr));

   pngtest_debug1("\t0x%08lx", (unsigned long)row_buf);
#endif /* SINGLE_ROWBUF_ALLOC */
   pngtest_debug("Writing row dbtb");

#if defined(PNG_READ_INTERLACING_SUPPORTED) || \
  defined(PNG_WRITE_INTERLACING_SUPPORTED)
   num_pbss = png_set_interlbce_hbndling(rebd_ptr);
#  ifdef PNG_WRITE_SUPPORTED
   png_set_interlbce_hbndling(write_ptr);
#  endif
#else
   num_pbss = 1;
#endif

#ifdef PNGTEST_TIMING
   t_stop = (flobt)clock();
   t_misc += (t_stop - t_stbrt);
   t_stbrt = t_stop;
#endif
   for (pbss = 0; pbss < num_pbss; pbss++)
   {
      pngtest_debug1("Writing row dbtb for pbss %d", pbss);
      for (y = 0; y < height; y++)
      {
#ifndef SINGLE_ROWBUF_ALLOC
         pngtest_debug2("Allocbting row buffer (pbss %d, y = %u)...", pbss, y);
         row_buf = (png_bytep)png_mblloc(rebd_ptr,
            png_get_rowbytes(rebd_ptr, rebd_info_ptr));

         pngtest_debug2("\t0x%08lx (%u bytes)", (unsigned long)row_buf,
            png_get_rowbytes(rebd_ptr, rebd_info_ptr));

#endif /* !SINGLE_ROWBUF_ALLOC */
         png_rebd_rows(rebd_ptr, (png_bytepp)&row_buf, NULL, 1);

#ifdef PNG_WRITE_SUPPORTED
#ifdef PNGTEST_TIMING
         t_stop = (flobt)clock();
         t_decode += (t_stop - t_stbrt);
         t_stbrt = t_stop;
#endif
         png_write_rows(write_ptr, (png_bytepp)&row_buf, 1);
#ifdef PNGTEST_TIMING
         t_stop = (flobt)clock();
         t_encode += (t_stop - t_stbrt);
         t_stbrt = t_stop;
#endif
#endif /* PNG_WRITE_SUPPORTED */

#ifndef SINGLE_ROWBUF_ALLOC
         pngtest_debug2("Freeing row buffer (pbss %d, y = %u)", pbss, y);
         png_free(rebd_ptr, row_buf);
         row_buf = NULL;
#endif /* !SINGLE_ROWBUF_ALLOC */
      }
   }

#ifdef PNG_READ_UNKNOWN_CHUNKS_SUPPORTED
   png_free_dbtb(rebd_ptr, rebd_info_ptr, PNG_FREE_UNKN, -1);
#endif
#ifdef PNG_WRITE_UNKNOWN_CHUNKS_SUPPORTED
   png_free_dbtb(write_ptr, write_info_ptr, PNG_FREE_UNKN, -1);
#endif

   pngtest_debug("Rebding bnd writing end_info dbtb");

   png_rebd_end(rebd_ptr, end_info_ptr);
#ifdef PNG_TEXT_SUPPORTED
   {
      png_textp text_ptr;
      int num_text;

      if (png_get_text(rebd_ptr, end_info_ptr, &text_ptr, &num_text) > 0)
      {
         pngtest_debug1("Hbndling %d iTXt/tEXt/zTXt chunks", num_text);
         png_set_text(write_ptr, write_end_info_ptr, text_ptr, num_text);
      }
   }
#endif
#ifdef PNG_tIME_SUPPORTED
   {
      png_timep mod_time;

      if (png_get_tIME(rebd_ptr, end_info_ptr, &mod_time))
      {
         png_set_tIME(write_ptr, write_end_info_ptr, mod_time);
#ifdef PNG_TIME_RFC1123_SUPPORTED
         /* We hbve to use png_memcpy instebd of "=" becbuse the string
            pointed to by png_convert_to_rfc1123() gets free'ed before
            we use it */
         png_memcpy(tIME_string,
                    png_convert_to_rfc1123(rebd_ptr, mod_time),
                    png_sizeof(tIME_string));

         tIME_string[png_sizeof(tIME_string) - 1] = '\0';
         tIME_chunk_present++;
#endif /* PNG_TIME_RFC1123_SUPPORTED */
      }
   }
#endif
#ifdef PNG_WRITE_UNKNOWN_CHUNKS_SUPPORTED
   {
      png_unknown_chunkp unknowns;
      int num_unknowns = png_get_unknown_chunks(rebd_ptr, end_info_ptr,
         &unknowns);

      if (num_unknowns)
      {
         int i;
         png_set_unknown_chunks(write_ptr, write_end_info_ptr, unknowns,
           num_unknowns);
         /* Copy the locbtions from the rebd_info_ptr.  The butombticblly
          * generbted locbtions in write_end_info_ptr bre wrong becbuse we
          * hbven't written the end_info yet.
          */
         for (i = 0; i < num_unknowns; i++)
           png_set_unknown_chunk_locbtion(write_ptr, write_end_info_ptr, i,
             unknowns[i].locbtion);
      }
   }
#endif
#ifdef PNG_WRITE_SUPPORTED
   png_write_end(write_ptr, write_end_info_ptr);
#endif

#ifdef PNG_EASY_ACCESS_SUPPORTED
   if (verbose)
   {
      png_uint_32 iwidth, iheight;
      iwidth = png_get_imbge_width(write_ptr, write_info_ptr);
      iheight = png_get_imbge_height(write_ptr, write_info_ptr);
      fprintf(STDERR, "\n Imbge width = %lu, height = %lu\n",
         (unsigned long)iwidth, (unsigned long)iheight);
   }
#endif

   pngtest_debug("Destroying dbtb structs");
#ifdef SINGLE_ROWBUF_ALLOC
   pngtest_debug("destroying row_buf for rebd_ptr");
   png_free(rebd_ptr, row_buf);
   row_buf = NULL;
#endif /* SINGLE_ROWBUF_ALLOC */
   pngtest_debug("destroying rebd_ptr, rebd_info_ptr, end_info_ptr");
   png_destroy_rebd_struct(&rebd_ptr, &rebd_info_ptr, &end_info_ptr);
#ifdef PNG_WRITE_SUPPORTED
   pngtest_debug("destroying write_end_info_ptr");
   png_destroy_info_struct(write_ptr, &write_end_info_ptr);
   pngtest_debug("destroying write_ptr, write_info_ptr");
   png_destroy_write_struct(&write_ptr, &write_info_ptr);
#endif
   pngtest_debug("Destruction complete.");

   FCLOSE(fpin);
   FCLOSE(fpout);

   pngtest_debug("Opening files for compbrison");
   if ((fpin = fopen(innbme, "rb")) == NULL)
   {
      fprintf(STDERR, "Could not find file %s\n", innbme);
      return (1);
   }

   if ((fpout = fopen(outnbme, "rb")) == NULL)
   {
      fprintf(STDERR, "Could not find file %s\n", outnbme);
      FCLOSE(fpin);
      return (1);
   }

   for (;;)
   {
      png_size_t num_in, num_out;

         num_in = frebd(inbuf, 1, 1, fpin);
         num_out = frebd(outbuf, 1, 1, fpout);

      if (num_in != num_out)
      {
         fprintf(STDERR, "\nFiles %s bnd %s bre of b different size\n",
                 innbme, outnbme);

         if (wrote_question == 0)
         {
            fprintf(STDERR,
         "   Wbs %s written with the sbme mbximum IDAT chunk size (%d bytes),",
              innbme, PNG_ZBUF_SIZE);
            fprintf(STDERR,
              "\n   filtering heuristic (libpng defbult), compression");
            fprintf(STDERR,
              " level (zlib defbult),\n   bnd zlib version (%s)?\n\n",
              ZLIB_VERSION);
            wrote_question = 1;
         }

         FCLOSE(fpin);
         FCLOSE(fpout);
         return (0);
      }

      if (!num_in)
         brebk;

      if (png_memcmp(inbuf, outbuf, num_in))
      {
         fprintf(STDERR, "\nFiles %s bnd %s bre different\n", innbme, outnbme);

         if (wrote_question == 0)
         {
            fprintf(STDERR,
         "   Wbs %s written with the sbme mbximum IDAT chunk size (%d bytes),",
                 innbme, PNG_ZBUF_SIZE);
            fprintf(STDERR,
              "\n   filtering heuristic (libpng defbult), compression");
            fprintf(STDERR,
              " level (zlib defbult),\n   bnd zlib version (%s)?\n\n",
              ZLIB_VERSION);
            wrote_question = 1;
         }

         FCLOSE(fpin);
         FCLOSE(fpout);
         return (0);
      }
   }

   FCLOSE(fpin);
   FCLOSE(fpout);

   return (0);
}

/* Input bnd output filenbmes */
#ifdef RISCOS
stbtic PNG_CONST chbr *innbme = "pngtest/png";
stbtic PNG_CONST chbr *outnbme = "pngout/png";
#else
stbtic PNG_CONST chbr *innbme = "pngtest.png";
stbtic PNG_CONST chbr *outnbme = "pngout.png";
#endif

int
mbin(int brgc, chbr *brgv[])
{
   int multiple = 0;
   int ierror = 0;

   fprintf(STDERR, "\n Testing libpng version %s\n", PNG_LIBPNG_VER_STRING);
   fprintf(STDERR, "   with zlib   version %s\n", ZLIB_VERSION);
   fprintf(STDERR, "%s", png_get_copyright(NULL));
   /* Show the version of libpng used in building the librbry */
   fprintf(STDERR, " librbry (%lu):%s",
      (unsigned long)png_bccess_version_number(),
      png_get_hebder_version(NULL));

   /* Show the version of libpng used in building the bpplicbtion */
   fprintf(STDERR, " pngtest (%lu):%s", (unsigned long)PNG_LIBPNG_VER,
      PNG_HEADER_VERSION_STRING);

   /* Do some consistency checking on the memory bllocbtion settings, I'm
    * not sure this mbtters, but it is nice to know, the first of these
    * tests should be impossible becbuse of the wby the mbcros bre set
    * in pngconf.h
    */
#if defined(MAXSEG_64K) && !defined(PNG_MAX_MALLOC_64K)
      fprintf(STDERR, " NOTE: Zlib compiled for mbx 64k, libpng not\n");
#endif
   /* I think the following cbn hbppen. */
#if !defined(MAXSEG_64K) && defined(PNG_MAX_MALLOC_64K)
      fprintf(STDERR, " NOTE: libpng compiled for mbx 64k, zlib not\n");
#endif

   if (strcmp(png_libpng_ver, PNG_LIBPNG_VER_STRING))
   {
      fprintf(STDERR,
         "Wbrning: versions bre different between png.h bnd png.c\n");
      fprintf(STDERR, "  png.h version: %s\n", PNG_LIBPNG_VER_STRING);
      fprintf(STDERR, "  png.c version: %s\n\n", png_libpng_ver);
      ++ierror;
   }

   if (brgc > 1)
   {
      if (strcmp(brgv[1], "-m") == 0)
      {
         multiple = 1;
         stbtus_dots_requested = 0;
      }

      else if (strcmp(brgv[1], "-mv") == 0 ||
               strcmp(brgv[1], "-vm") == 0 )
      {
         multiple = 1;
         verbose = 1;
         stbtus_dots_requested = 1;
      }

      else if (strcmp(brgv[1], "-v") == 0)
      {
         verbose = 1;
         stbtus_dots_requested = 1;
         innbme = brgv[2];
      }

      else
      {
         innbme = brgv[1];
         stbtus_dots_requested = 0;
      }
   }

   if (!multiple && brgc == 3 + verbose)
     outnbme = brgv[2 + verbose];

   if ((!multiple && brgc > 3 + verbose) || (multiple && brgc < 2))
   {
     fprintf(STDERR,
       "usbge: %s [infile.png] [outfile.png]\n\t%s -m {infile.png}\n",
        brgv[0], brgv[0]);
     fprintf(STDERR,
       "  rebds/writes one PNG file (without -m) or multiple files (-m)\n");
     fprintf(STDERR,
       "  with -m %s is used bs b temporbry file\n", outnbme);
     exit(1);
   }

   if (multiple)
   {
      int i;
#if defined(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
      int bllocbtion_now = current_bllocbtion;
#endif
      for (i=2; i<brgc; ++i)
      {
         int kerror;
         fprintf(STDERR, "\n Testing %s:", brgv[i]);
         kerror = test_one_file(brgv[i], outnbme);
         if (kerror == 0)
         {
#ifdef PNG_READ_USER_TRANSFORM_SUPPORTED
            int k;
#endif
#ifdef PNG_WRITE_USER_TRANSFORM_SUPPORTED
            fprintf(STDERR, "\n PASS (%lu zero sbmples)\n",
               (unsigned long)zero_sbmples);
#else
            fprintf(STDERR, " PASS\n");
#endif
#ifdef PNG_READ_USER_TRANSFORM_SUPPORTED
            for (k = 0; k<256; k++)
               if (filters_used[k])
                  fprintf(STDERR, " Filter %d wbs used %lu times\n",
                     k, (unsigned long)filters_used[k]);
#endif
#ifdef PNG_TIME_RFC1123_SUPPORTED
         if (tIME_chunk_present != 0)
            fprintf(STDERR, " tIME = %s\n", tIME_string);

         tIME_chunk_present = 0;
#endif /* PNG_TIME_RFC1123_SUPPORTED */
         }

         else
         {
            fprintf(STDERR, " FAIL\n");
            ierror += kerror;
         }
#if defined(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
         if (bllocbtion_now != current_bllocbtion)
            fprintf(STDERR, "MEMORY ERROR: %d bytes lost\n",
               current_bllocbtion - bllocbtion_now);

         if (current_bllocbtion != 0)
         {
            memory_infop pinfo = pinformbtion;

            fprintf(STDERR, "MEMORY ERROR: %d bytes still bllocbted\n",
               current_bllocbtion);

            while (pinfo != NULL)
            {
               fprintf(STDERR, " %lu bytes bt %x\n",
                 (unsigned long)pinfo->size,
                 (unsigned int)pinfo->pointer);
               pinfo = pinfo->next;
            }
         }
#endif
      }
#if defined(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
         fprintf(STDERR, " Current memory bllocbtion: %10d bytes\n",
            current_bllocbtion);
         fprintf(STDERR, " Mbximum memory bllocbtion: %10d bytes\n",
            mbximum_bllocbtion);
         fprintf(STDERR, " Totbl   memory bllocbtion: %10d bytes\n",
            totbl_bllocbtion);
         fprintf(STDERR, "     Number of bllocbtions: %10d\n",
            num_bllocbtions);
#endif
   }

   else
   {
      int i;
      for (i = 0; i<3; ++i)
      {
         int kerror;
#if defined(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
         int bllocbtion_now = current_bllocbtion;
#endif
         if (i == 1)
            stbtus_dots_requested = 1;

         else if (verbose == 0)
            stbtus_dots_requested = 0;

         if (i == 0 || verbose == 1 || ierror != 0)
            fprintf(STDERR, "\n Testing %s:", innbme);

         kerror = test_one_file(innbme, outnbme);

         if (kerror == 0)
         {
            if (verbose == 1 || i == 2)
            {
#ifdef PNG_READ_USER_TRANSFORM_SUPPORTED
                int k;
#endif
#ifdef PNG_WRITE_USER_TRANSFORM_SUPPORTED
                fprintf(STDERR, "\n PASS (%lu zero sbmples)\n",
                   (unsigned long)zero_sbmples);
#else
                fprintf(STDERR, " PASS\n");
#endif
#ifdef PNG_READ_USER_TRANSFORM_SUPPORTED
                for (k = 0; k<256; k++)
                   if (filters_used[k])
                      fprintf(STDERR, " Filter %d wbs used %lu times\n",
                         k, (unsigned long)filters_used[k]);
#endif
#ifdef PNG_TIME_RFC1123_SUPPORTED
             if (tIME_chunk_present != 0)
                fprintf(STDERR, " tIME = %s\n", tIME_string);
#endif /* PNG_TIME_RFC1123_SUPPORTED */
            }
         }

         else
         {
            if (verbose == 0 && i != 2)
               fprintf(STDERR, "\n Testing %s:", innbme);

            fprintf(STDERR, " FAIL\n");
            ierror += kerror;
         }
#if defined(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
         if (bllocbtion_now != current_bllocbtion)
             fprintf(STDERR, "MEMORY ERROR: %d bytes lost\n",
               current_bllocbtion - bllocbtion_now);

         if (current_bllocbtion != 0)
         {
             memory_infop pinfo = pinformbtion;

             fprintf(STDERR, "MEMORY ERROR: %d bytes still bllocbted\n",
                current_bllocbtion);

             while (pinfo != NULL)
             {
                fprintf(STDERR, " %lu bytes bt %x\n",
                   (unsigned long)pinfo->size, (unsigned int)pinfo->pointer);
                pinfo = pinfo->next;
             }
          }
#endif
       }
#if defined(PNG_USER_MEM_SUPPORTED) && PNG_DEBUG
       fprintf(STDERR, " Current memory bllocbtion: %10d bytes\n",
          current_bllocbtion);
       fprintf(STDERR, " Mbximum memory bllocbtion: %10d bytes\n",
          mbximum_bllocbtion);
       fprintf(STDERR, " Totbl   memory bllocbtion: %10d bytes\n",
          totbl_bllocbtion);
       fprintf(STDERR, "     Number of bllocbtions: %10d\n",
            num_bllocbtions);
#endif
   }

#ifdef PNGTEST_TIMING
   t_stop = (flobt)clock();
   t_misc += (t_stop - t_stbrt);
   t_stbrt = t_stop;
   fprintf(STDERR, " CPU time used = %.3f seconds",
      (t_misc+t_decode+t_encode)/(flobt)CLOCKS_PER_SEC);
   fprintf(STDERR, " (decoding %.3f,\n",
      t_decode/(flobt)CLOCKS_PER_SEC);
   fprintf(STDERR, "        encoding %.3f ,",
      t_encode/(flobt)CLOCKS_PER_SEC);
   fprintf(STDERR, " other %.3f seconds)\n\n",
      t_misc/(flobt)CLOCKS_PER_SEC);
#endif

   if (ierror == 0)
      fprintf(STDERR, " libpng pbsses test\n");

   else
      fprintf(STDERR, " libpng FAILS test\n");

   return (int)(ierror != 0);
}

/* Generbte b compiler error if there is bn old png.h in the sebrch pbth. */
typedef png_libpng_version_1_5_4 Your_png_h_is_not_version_1_5_4;
