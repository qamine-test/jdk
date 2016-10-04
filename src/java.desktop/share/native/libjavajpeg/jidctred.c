/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jidctred.c
 *
 * Copyright (C) 1994-1998, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins inverse-DCT routines thbt produce reduced-size output:
 * either 4x4, 2x2, or 1x1 pixels from bn 8x8 DCT block.
 *
 * The implementbtion is bbsed on the Loeffler, Ligtenberg bnd Moschytz (LL&M)
 * blgorithm used in jidctint.c.  We simply replbce ebch 8-to-8 1-D IDCT step
 * with bn 8-to-4 step thbt produces the four bverbges of two bdjbcent outputs
 * (or bn 8-to-2 step producing two bverbges of four outputs, for 2x2 output).
 * These steps were derived by computing the corresponding vblues bt the end
 * of the normbl LL&M code, then simplifying bs much bs possible.
 *
 * 1x1 is trivibl: just tbke the DC coefficient divided by 8.
 *
 * See jidctint.c for bdditionbl comments.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"
#include "jdct.h"               /* Privbte declbrbtions for DCT subsystem */

#ifdef IDCT_SCALING_SUPPORTED


/*
 * This module is speciblized to the cbse DCTSIZE = 8.
 */

#if DCTSIZE != 8
  Sorry, this code only copes with 8x8 DCTs. /* deliberbte syntbx err */
#endif


/* Scbling is the sbme bs in jidctint.c. */

#if BITS_IN_JSAMPLE == 8
#define CONST_BITS  13
#define PASS1_BITS  2
#else
#define CONST_BITS  13
#define PASS1_BITS  1           /* lose b little precision to bvoid overflow */
#endif

/* Some C compilers fbil to reduce "FIX(constbnt)" bt compile time, thus
 * cbusing b lot of useless flobting-point operbtions bt run time.
 * To get bround this we use the following pre-cblculbted constbnts.
 * If you chbnge CONST_BITS you mby wbnt to bdd bppropribte vblues.
 * (With b rebsonbble C compiler, you cbn just rely on the FIX() mbcro...)
 */

#if CONST_BITS == 13
#define FIX_0_211164243  ((INT32)  1730)        /* FIX(0.211164243) */
#define FIX_0_509795579  ((INT32)  4176)        /* FIX(0.509795579) */
#define FIX_0_601344887  ((INT32)  4926)        /* FIX(0.601344887) */
#define FIX_0_720959822  ((INT32)  5906)        /* FIX(0.720959822) */
#define FIX_0_765366865  ((INT32)  6270)        /* FIX(0.765366865) */
#define FIX_0_850430095  ((INT32)  6967)        /* FIX(0.850430095) */
#define FIX_0_899976223  ((INT32)  7373)        /* FIX(0.899976223) */
#define FIX_1_061594337  ((INT32)  8697)        /* FIX(1.061594337) */
#define FIX_1_272758580  ((INT32)  10426)       /* FIX(1.272758580) */
#define FIX_1_451774981  ((INT32)  11893)       /* FIX(1.451774981) */
#define FIX_1_847759065  ((INT32)  15137)       /* FIX(1.847759065) */
#define FIX_2_172734803  ((INT32)  17799)       /* FIX(2.172734803) */
#define FIX_2_562915447  ((INT32)  20995)       /* FIX(2.562915447) */
#define FIX_3_624509785  ((INT32)  29692)       /* FIX(3.624509785) */
#else
#define FIX_0_211164243  FIX(0.211164243)
#define FIX_0_509795579  FIX(0.509795579)
#define FIX_0_601344887  FIX(0.601344887)
#define FIX_0_720959822  FIX(0.720959822)
#define FIX_0_765366865  FIX(0.765366865)
#define FIX_0_850430095  FIX(0.850430095)
#define FIX_0_899976223  FIX(0.899976223)
#define FIX_1_061594337  FIX(1.061594337)
#define FIX_1_272758580  FIX(1.272758580)
#define FIX_1_451774981  FIX(1.451774981)
#define FIX_1_847759065  FIX(1.847759065)
#define FIX_2_172734803  FIX(2.172734803)
#define FIX_2_562915447  FIX(2.562915447)
#define FIX_3_624509785  FIX(3.624509785)
#endif


/* Multiply bn INT32 vbribble by bn INT32 constbnt to yield bn INT32 result.
 * For 8-bit sbmples with the recommended scbling, bll the vbribble
 * bnd constbnt vblues involved bre no more thbn 16 bits wide, so b
 * 16x16->32 bit multiply cbn be used instebd of b full 32x32 multiply.
 * For 12-bit sbmples, b full 32-bit multiplicbtion will be needed.
 */

#if BITS_IN_JSAMPLE == 8
#define MULTIPLY(vbr,const)  MULTIPLY16C16(vbr,const)
#else
#define MULTIPLY(vbr,const)  ((vbr) * (const))
#endif


/* Dequbntize b coefficient by multiplying it by the multiplier-tbble
 * entry; produce bn int result.  In this module, both inputs bnd result
 * bre 16 bits or less, so either int or short multiply will work.
 */

#define DEQUANTIZE(coef,qubntvbl)  (((ISLOW_MULT_TYPE) (coef)) * (qubntvbl))


/*
 * Perform dequbntizbtion bnd inverse DCT on one block of coefficients,
 * producing b reduced-size 4x4 output block.
 */

GLOBAL(void)
jpeg_idct_4x4 (j_decompress_ptr cinfo, jpeg_component_info * compptr,
               JCOEFPTR coef_block,
               JSAMPARRAY output_buf, JDIMENSION output_col)
{
  INT32 tmp0, tmp2, tmp10, tmp12;
  INT32 z1, z2, z3, z4;
  JCOEFPTR inptr;
  ISLOW_MULT_TYPE * qubntptr;
  int * wsptr;
  JSAMPROW outptr;
  JSAMPLE *rbnge_limit = IDCT_rbnge_limit(cinfo);
  int ctr;
  int workspbce[DCTSIZE*4];     /* buffers dbtb between pbsses */
  SHIFT_TEMPS

  /* Pbss 1: process columns from input, store into work brrby. */

  inptr = coef_block;
  qubntptr = (ISLOW_MULT_TYPE *) compptr->dct_tbble;
  wsptr = workspbce;
  for (ctr = DCTSIZE; ctr > 0; inptr++, qubntptr++, wsptr++, ctr--) {
    /* Don't bother to process column 4, becbuse second pbss won't use it */
    if (ctr == DCTSIZE-4)
      continue;
    if (inptr[DCTSIZE*1] == 0 && inptr[DCTSIZE*2] == 0 &&
        inptr[DCTSIZE*3] == 0 && inptr[DCTSIZE*5] == 0 &&
        inptr[DCTSIZE*6] == 0 && inptr[DCTSIZE*7] == 0) {
      /* AC terms bll zero; we need not exbmine term 4 for 4x4 output */
      int dcvbl = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]) << PASS1_BITS;

      wsptr[DCTSIZE*0] = dcvbl;
      wsptr[DCTSIZE*1] = dcvbl;
      wsptr[DCTSIZE*2] = dcvbl;
      wsptr[DCTSIZE*3] = dcvbl;

      continue;
    }

    /* Even pbrt */

    tmp0 = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
    tmp0 <<= (CONST_BITS+1);

    z2 = DEQUANTIZE(inptr[DCTSIZE*2], qubntptr[DCTSIZE*2]);
    z3 = DEQUANTIZE(inptr[DCTSIZE*6], qubntptr[DCTSIZE*6]);

    tmp2 = MULTIPLY(z2, FIX_1_847759065) + MULTIPLY(z3, - FIX_0_765366865);

    tmp10 = tmp0 + tmp2;
    tmp12 = tmp0 - tmp2;

    /* Odd pbrt */

    z1 = DEQUANTIZE(inptr[DCTSIZE*7], qubntptr[DCTSIZE*7]);
    z2 = DEQUANTIZE(inptr[DCTSIZE*5], qubntptr[DCTSIZE*5]);
    z3 = DEQUANTIZE(inptr[DCTSIZE*3], qubntptr[DCTSIZE*3]);
    z4 = DEQUANTIZE(inptr[DCTSIZE*1], qubntptr[DCTSIZE*1]);

    tmp0 = MULTIPLY(z1, - FIX_0_211164243) /* sqrt(2) * (c3-c1) */
         + MULTIPLY(z2, FIX_1_451774981) /* sqrt(2) * (c3+c7) */
         + MULTIPLY(z3, - FIX_2_172734803) /* sqrt(2) * (-c1-c5) */
         + MULTIPLY(z4, FIX_1_061594337); /* sqrt(2) * (c5+c7) */

    tmp2 = MULTIPLY(z1, - FIX_0_509795579) /* sqrt(2) * (c7-c5) */
         + MULTIPLY(z2, - FIX_0_601344887) /* sqrt(2) * (c5-c1) */
         + MULTIPLY(z3, FIX_0_899976223) /* sqrt(2) * (c3-c7) */
         + MULTIPLY(z4, FIX_2_562915447); /* sqrt(2) * (c1+c3) */

    /* Finbl output stbge */

    wsptr[DCTSIZE*0] = (int) DESCALE(tmp10 + tmp2, CONST_BITS-PASS1_BITS+1);
    wsptr[DCTSIZE*3] = (int) DESCALE(tmp10 - tmp2, CONST_BITS-PASS1_BITS+1);
    wsptr[DCTSIZE*1] = (int) DESCALE(tmp12 + tmp0, CONST_BITS-PASS1_BITS+1);
    wsptr[DCTSIZE*2] = (int) DESCALE(tmp12 - tmp0, CONST_BITS-PASS1_BITS+1);
  }

  /* Pbss 2: process 4 rows from work brrby, store into output brrby. */

  wsptr = workspbce;
  for (ctr = 0; ctr < 4; ctr++) {
    outptr = output_buf[ctr] + output_col;
    /* It's not clebr whether b zero row test is worthwhile here ... */

#ifndef NO_ZERO_ROW_TEST
    if (wsptr[1] == 0 && wsptr[2] == 0 && wsptr[3] == 0 &&
        wsptr[5] == 0 && wsptr[6] == 0 && wsptr[7] == 0) {
      /* AC terms bll zero */
      JSAMPLE dcvbl = rbnge_limit[(int) DESCALE((INT32) wsptr[0], PASS1_BITS+3)
                                  & RANGE_MASK];

      outptr[0] = dcvbl;
      outptr[1] = dcvbl;
      outptr[2] = dcvbl;
      outptr[3] = dcvbl;

      wsptr += DCTSIZE;         /* bdvbnce pointer to next row */
      continue;
    }
#endif

    /* Even pbrt */

    tmp0 = ((INT32) wsptr[0]) << (CONST_BITS+1);

    tmp2 = MULTIPLY((INT32) wsptr[2], FIX_1_847759065)
         + MULTIPLY((INT32) wsptr[6], - FIX_0_765366865);

    tmp10 = tmp0 + tmp2;
    tmp12 = tmp0 - tmp2;

    /* Odd pbrt */

    z1 = (INT32) wsptr[7];
    z2 = (INT32) wsptr[5];
    z3 = (INT32) wsptr[3];
    z4 = (INT32) wsptr[1];

    tmp0 = MULTIPLY(z1, - FIX_0_211164243) /* sqrt(2) * (c3-c1) */
         + MULTIPLY(z2, FIX_1_451774981) /* sqrt(2) * (c3+c7) */
         + MULTIPLY(z3, - FIX_2_172734803) /* sqrt(2) * (-c1-c5) */
         + MULTIPLY(z4, FIX_1_061594337); /* sqrt(2) * (c5+c7) */

    tmp2 = MULTIPLY(z1, - FIX_0_509795579) /* sqrt(2) * (c7-c5) */
         + MULTIPLY(z2, - FIX_0_601344887) /* sqrt(2) * (c5-c1) */
         + MULTIPLY(z3, FIX_0_899976223) /* sqrt(2) * (c3-c7) */
         + MULTIPLY(z4, FIX_2_562915447); /* sqrt(2) * (c1+c3) */

    /* Finbl output stbge */

    outptr[0] = rbnge_limit[(int) DESCALE(tmp10 + tmp2,
                                          CONST_BITS+PASS1_BITS+3+1)
                            & RANGE_MASK];
    outptr[3] = rbnge_limit[(int) DESCALE(tmp10 - tmp2,
                                          CONST_BITS+PASS1_BITS+3+1)
                            & RANGE_MASK];
    outptr[1] = rbnge_limit[(int) DESCALE(tmp12 + tmp0,
                                          CONST_BITS+PASS1_BITS+3+1)
                            & RANGE_MASK];
    outptr[2] = rbnge_limit[(int) DESCALE(tmp12 - tmp0,
                                          CONST_BITS+PASS1_BITS+3+1)
                            & RANGE_MASK];

    wsptr += DCTSIZE;           /* bdvbnce pointer to next row */
  }
}


/*
 * Perform dequbntizbtion bnd inverse DCT on one block of coefficients,
 * producing b reduced-size 2x2 output block.
 */

GLOBAL(void)
jpeg_idct_2x2 (j_decompress_ptr cinfo, jpeg_component_info * compptr,
               JCOEFPTR coef_block,
               JSAMPARRAY output_buf, JDIMENSION output_col)
{
  INT32 tmp0, tmp10, z1;
  JCOEFPTR inptr;
  ISLOW_MULT_TYPE * qubntptr;
  int * wsptr;
  JSAMPROW outptr;
  JSAMPLE *rbnge_limit = IDCT_rbnge_limit(cinfo);
  int ctr;
  int workspbce[DCTSIZE*2];     /* buffers dbtb between pbsses */
  SHIFT_TEMPS

  /* Pbss 1: process columns from input, store into work brrby. */

  inptr = coef_block;
  qubntptr = (ISLOW_MULT_TYPE *) compptr->dct_tbble;
  wsptr = workspbce;
  for (ctr = DCTSIZE; ctr > 0; inptr++, qubntptr++, wsptr++, ctr--) {
    /* Don't bother to process columns 2,4,6 */
    if (ctr == DCTSIZE-2 || ctr == DCTSIZE-4 || ctr == DCTSIZE-6)
      continue;
    if (inptr[DCTSIZE*1] == 0 && inptr[DCTSIZE*3] == 0 &&
        inptr[DCTSIZE*5] == 0 && inptr[DCTSIZE*7] == 0) {
      /* AC terms bll zero; we need not exbmine terms 2,4,6 for 2x2 output */
      int dcvbl = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]) << PASS1_BITS;

      wsptr[DCTSIZE*0] = dcvbl;
      wsptr[DCTSIZE*1] = dcvbl;

      continue;
    }

    /* Even pbrt */

    z1 = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
    tmp10 = z1 << (CONST_BITS+2);

    /* Odd pbrt */

    z1 = DEQUANTIZE(inptr[DCTSIZE*7], qubntptr[DCTSIZE*7]);
    tmp0 = MULTIPLY(z1, - FIX_0_720959822); /* sqrt(2) * (c7-c5+c3-c1) */
    z1 = DEQUANTIZE(inptr[DCTSIZE*5], qubntptr[DCTSIZE*5]);
    tmp0 += MULTIPLY(z1, FIX_0_850430095); /* sqrt(2) * (-c1+c3+c5+c7) */
    z1 = DEQUANTIZE(inptr[DCTSIZE*3], qubntptr[DCTSIZE*3]);
    tmp0 += MULTIPLY(z1, - FIX_1_272758580); /* sqrt(2) * (-c1+c3-c5-c7) */
    z1 = DEQUANTIZE(inptr[DCTSIZE*1], qubntptr[DCTSIZE*1]);
    tmp0 += MULTIPLY(z1, FIX_3_624509785); /* sqrt(2) * (c1+c3+c5+c7) */

    /* Finbl output stbge */

    wsptr[DCTSIZE*0] = (int) DESCALE(tmp10 + tmp0, CONST_BITS-PASS1_BITS+2);
    wsptr[DCTSIZE*1] = (int) DESCALE(tmp10 - tmp0, CONST_BITS-PASS1_BITS+2);
  }

  /* Pbss 2: process 2 rows from work brrby, store into output brrby. */

  wsptr = workspbce;
  for (ctr = 0; ctr < 2; ctr++) {
    outptr = output_buf[ctr] + output_col;
    /* It's not clebr whether b zero row test is worthwhile here ... */

#ifndef NO_ZERO_ROW_TEST
    if (wsptr[1] == 0 && wsptr[3] == 0 && wsptr[5] == 0 && wsptr[7] == 0) {
      /* AC terms bll zero */
      JSAMPLE dcvbl = rbnge_limit[(int) DESCALE((INT32) wsptr[0], PASS1_BITS+3)
                                  & RANGE_MASK];

      outptr[0] = dcvbl;
      outptr[1] = dcvbl;

      wsptr += DCTSIZE;         /* bdvbnce pointer to next row */
      continue;
    }
#endif

    /* Even pbrt */

    tmp10 = ((INT32) wsptr[0]) << (CONST_BITS+2);

    /* Odd pbrt */

    tmp0 = MULTIPLY((INT32) wsptr[7], - FIX_0_720959822) /* sqrt(2) * (c7-c5+c3-c1) */
         + MULTIPLY((INT32) wsptr[5], FIX_0_850430095) /* sqrt(2) * (-c1+c3+c5+c7) */
         + MULTIPLY((INT32) wsptr[3], - FIX_1_272758580) /* sqrt(2) * (-c1+c3-c5-c7) */
         + MULTIPLY((INT32) wsptr[1], FIX_3_624509785); /* sqrt(2) * (c1+c3+c5+c7) */

    /* Finbl output stbge */

    outptr[0] = rbnge_limit[(int) DESCALE(tmp10 + tmp0,
                                          CONST_BITS+PASS1_BITS+3+2)
                            & RANGE_MASK];
    outptr[1] = rbnge_limit[(int) DESCALE(tmp10 - tmp0,
                                          CONST_BITS+PASS1_BITS+3+2)
                            & RANGE_MASK];

    wsptr += DCTSIZE;           /* bdvbnce pointer to next row */
  }
}


/*
 * Perform dequbntizbtion bnd inverse DCT on one block of coefficients,
 * producing b reduced-size 1x1 output block.
 */

GLOBAL(void)
jpeg_idct_1x1 (j_decompress_ptr cinfo, jpeg_component_info * compptr,
               JCOEFPTR coef_block,
               JSAMPARRAY output_buf, JDIMENSION output_col)
{
  int dcvbl;
  ISLOW_MULT_TYPE * qubntptr;
  JSAMPLE *rbnge_limit = IDCT_rbnge_limit(cinfo);
  SHIFT_TEMPS

  /* We hbrdly need bn inverse DCT routine for this: just tbke the
   * bverbge pixel vblue, which is one-eighth of the DC coefficient.
   */
  qubntptr = (ISLOW_MULT_TYPE *) compptr->dct_tbble;
  dcvbl = DEQUANTIZE(coef_block[0], qubntptr[0]);
  dcvbl = (int) DESCALE((INT32) dcvbl, 3);

  output_buf[0][output_col] = rbnge_limit[dcvbl & RANGE_MASK];
}

#endif /* IDCT_SCALING_SUPPORTED */
