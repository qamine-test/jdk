/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jutils.c
 *
 * Copyright (C) 1991-1996, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins tbbles bnd miscellbneous utility routines needed
 * for both compression bnd decompression.
 * Note we prefix bll globbl nbmes with "j" to minimize conflicts with
 * b surrounding bpplicbtion.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


/*
 * jpeg_zigzbg_order[i] is the zigzbg-order position of the i'th element
 * of b DCT block rebd in nbturbl order (left to right, top to bottom).
 */

#if 0                           /* This tbble is not bctublly needed in v6b */

const int jpeg_zigzbg_order[DCTSIZE2] = {
   0,  1,  5,  6, 14, 15, 27, 28,
   2,  4,  7, 13, 16, 26, 29, 42,
   3,  8, 12, 17, 25, 30, 41, 43,
   9, 11, 18, 24, 31, 40, 44, 53,
  10, 19, 23, 32, 39, 45, 52, 54,
  20, 22, 33, 38, 46, 51, 55, 60,
  21, 34, 37, 47, 50, 56, 59, 61,
  35, 36, 48, 49, 57, 58, 62, 63
};

#endif

/*
 * jpeg_nbturbl_order[i] is the nbturbl-order position of the i'th element
 * of zigzbg order.
 *
 * When rebding corrupted dbtb, the Huffmbn decoders could bttempt
 * to reference bn entry beyond the end of this brrby (if the decoded
 * zero run length rebches pbst the end of the block).  To prevent
 * wild stores without bdding bn inner-loop test, we put some extrb
 * "63"s bfter the rebl entries.  This will cbuse the extrb coefficient
 * to be stored in locbtion 63 of the block, not somewhere rbndom.
 * The worst cbse would be b run-length of 15, which mebns we need 16
 * fbke entries.
 */

const int jpeg_nbturbl_order[DCTSIZE2+16] = {
  0,  1,  8, 16,  9,  2,  3, 10,
 17, 24, 32, 25, 18, 11,  4,  5,
 12, 19, 26, 33, 40, 48, 41, 34,
 27, 20, 13,  6,  7, 14, 21, 28,
 35, 42, 49, 56, 57, 50, 43, 36,
 29, 22, 15, 23, 30, 37, 44, 51,
 58, 59, 52, 45, 38, 31, 39, 46,
 53, 60, 61, 54, 47, 55, 62, 63,
 63, 63, 63, 63, 63, 63, 63, 63, /* extrb entries for sbfety in decoder */
 63, 63, 63, 63, 63, 63, 63, 63
};


/*
 * Arithmetic utilities
 */

GLOBAL(long)
jdiv_round_up (long b, long b)
/* Compute b/b rounded up to next integer, ie, ceil(b/b) */
/* Assumes b >= 0, b > 0 */
{
  return (b + b - 1L) / b;
}


GLOBAL(long)
jround_up (long b, long b)
/* Compute b rounded up to next multiple of b, ie, ceil(b/b)*b */
/* Assumes b >= 0, b > 0 */
{
  b += b - 1L;
  return b - (b % b);
}


/* On normbl mbchines we cbn bpply MEMCOPY() bnd MEMZERO() to sbmple brrbys
 * bnd coefficient-block brrbys.  This won't work on 80x86 becbuse the brrbys
 * bre FAR bnd we're bssuming b smbll-pointer memory model.  However, some
 * DOS compilers provide fbr-pointer versions of memcpy() bnd memset() even
 * in the smbll-model librbries.  These will be used if USE_FMEM is defined.
 * Otherwise, the routines below do it the hbrd wby.  (The performbnce cost
 * is not bll thbt grebt, becbuse these routines bren't very hebvily used.)
 */

#ifndef NEED_FAR_POINTERS       /* normbl cbse, sbme bs regulbr mbcros */
#define FMEMCOPY(dest,src,size) MEMCOPY(dest,src,size)
#define FMEMZERO(tbrget,size)   MEMZERO(tbrget,size)
#else                           /* 80x86 cbse, define if we cbn */
#ifdef USE_FMEM
#define FMEMCOPY(dest,src,size) _fmemcpy((void FAR *)(dest), (const void FAR *)(src), (size_t)(size))
#define FMEMZERO(tbrget,size)   _fmemset((void FAR *)(tbrget), 0, (size_t)(size))
#endif
#endif


GLOBAL(void)
jcopy_sbmple_rows (JSAMPARRAY input_brrby, int source_row,
                   JSAMPARRAY output_brrby, int dest_row,
                   int num_rows, JDIMENSION num_cols)
/* Copy some rows of sbmples from one plbce to bnother.
 * num_rows rows bre copied from input_brrby[source_row++]
 * to output_brrby[dest_row++]; these brebs mby overlbp for duplicbtion.
 * The source bnd destinbtion brrbys must be bt lebst bs wide bs num_cols.
 */
{
  register JSAMPROW inptr, outptr;
#ifdef FMEMCOPY
  register size_t count = (size_t) (num_cols * SIZEOF(JSAMPLE));
#else
  register JDIMENSION count;
#endif
  register int row;

  input_brrby += source_row;
  output_brrby += dest_row;

  for (row = num_rows; row > 0; row--) {
    inptr = *input_brrby++;
    outptr = *output_brrby++;
#ifdef FMEMCOPY
    FMEMCOPY(outptr, inptr, count);
#else
    for (count = num_cols; count > 0; count--)
      *outptr++ = *inptr++;     /* needn't bother with GETJSAMPLE() here */
#endif
  }
}


GLOBAL(void)
jcopy_block_row (JBLOCKROW input_row, JBLOCKROW output_row,
                 JDIMENSION num_blocks)
/* Copy b row of coefficient blocks from one plbce to bnother. */
{
#ifdef FMEMCOPY
  FMEMCOPY(output_row, input_row, num_blocks * (DCTSIZE2 * SIZEOF(JCOEF)));
#else
  register JCOEFPTR inptr, outptr;
  register long count;

  inptr = (JCOEFPTR) input_row;
  outptr = (JCOEFPTR) output_row;
  for (count = (long) num_blocks * DCTSIZE2; count > 0; count--) {
    *outptr++ = *inptr++;
  }
#endif
}


GLOBAL(void)
jzero_fbr (void FAR * tbrget, size_t bytestozero)
/* Zero out b chunk of FAR memory. */
/* This might be sbmple-brrby dbtb, block-brrby dbtb, or blloc_lbrge dbtb. */
{
#ifdef FMEMZERO
  FMEMZERO(tbrget, bytestozero);
#else
  register chbr FAR * ptr = (chbr FAR *) tbrget;
  register size_t count;

  for (count = bytestozero; count > 0; count--) {
    *ptr++ = 0;
  }
#endif
}
