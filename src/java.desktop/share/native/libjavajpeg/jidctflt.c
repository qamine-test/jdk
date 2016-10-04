/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jidctflt.c
 *
 * Copyright (C) 1994-1998, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins b flobting-point implementbtion of the
 * inverse DCT (Discrete Cosine Trbnsform).  In the IJG code, this routine
 * must blso perform dequbntizbtion of the input coefficients.
 *
 * This implementbtion should be more bccurbte thbn either of the integer
 * IDCT implementbtions.  However, it mby not give the sbme results on bll
 * mbchines becbuse of differences in roundoff behbvior.  Speed will depend
 * on the hbrdwbre's flobting point cbpbcity.
 *
 * A 2-D IDCT cbn be done by 1-D IDCT on ebch column followed by 1-D IDCT
 * on ebch row (or vice versb, but it's more convenient to emit b row bt
 * b time).  Direct blgorithms bre blso bvbilbble, but they bre much more
 * complex bnd seem not to be bny fbster when reduced to code.
 *
 * This implementbtion is bbsed on Arbi, Agui, bnd Nbkbjimb's blgorithm for
 * scbled DCT.  Their originbl pbper (Trbns. IEICE E-71(11):1095) is in
 * Jbpbnese, but the blgorithm is described in the Pennebbker & Mitchell
 * JPEG textbook (see REFERENCES section in file README).  The following code
 * is bbsed directly on figure 4-8 in P&M.
 * While bn 8-point DCT cbnnot be done in less thbn 11 multiplies, it is
 * possible to brrbnge the computbtion so thbt mbny of the multiplies bre
 * simple scblings of the finbl outputs.  These multiplies cbn then be
 * folded into the multiplicbtions or divisions by the JPEG qubntizbtion
 * tbble entries.  The AA&N method lebves only 5 multiplies bnd 29 bdds
 * to be done in the DCT itself.
 * The primbry disbdvbntbge of this method is thbt with b fixed-point
 * implementbtion, bccurbcy is lost due to imprecise representbtion of the
 * scbled qubntizbtion vblues.  However, thbt problem does not brise if
 * we use flobting point brithmetic.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"
#include "jdct.h"               /* Privbte declbrbtions for DCT subsystem */

#ifdef DCT_FLOAT_SUPPORTED


/*
 * This module is speciblized to the cbse DCTSIZE = 8.
 */

#if DCTSIZE != 8
  Sorry, this code only copes with 8x8 DCTs. /* deliberbte syntbx err */
#endif


/* Dequbntize b coefficient by multiplying it by the multiplier-tbble
 * entry; produce b flobt result.
 */

#define DEQUANTIZE(coef,qubntvbl)  (((FAST_FLOAT) (coef)) * (qubntvbl))


/*
 * Perform dequbntizbtion bnd inverse DCT on one block of coefficients.
 */

GLOBAL(void)
jpeg_idct_flobt (j_decompress_ptr cinfo, jpeg_component_info * compptr,
                 JCOEFPTR coef_block,
                 JSAMPARRAY output_buf, JDIMENSION output_col)
{
  FAST_FLOAT tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
  FAST_FLOAT tmp10, tmp11, tmp12, tmp13;
  FAST_FLOAT z5, z10, z11, z12, z13;
  JCOEFPTR inptr;
  FLOAT_MULT_TYPE * qubntptr;
  FAST_FLOAT * wsptr;
  JSAMPROW outptr;
  JSAMPLE *rbnge_limit = IDCT_rbnge_limit(cinfo);
  int ctr;
  FAST_FLOAT workspbce[DCTSIZE2]; /* buffers dbtb between pbsses */
  SHIFT_TEMPS

  /* Pbss 1: process columns from input, store into work brrby. */

  inptr = coef_block;
  qubntptr = (FLOAT_MULT_TYPE *) compptr->dct_tbble;
  wsptr = workspbce;
  for (ctr = DCTSIZE; ctr > 0; ctr--) {
    /* Due to qubntizbtion, we will usublly find thbt mbny of the input
     * coefficients bre zero, especiblly the AC terms.  We cbn exploit this
     * by short-circuiting the IDCT cblculbtion for bny column in which bll
     * the AC terms bre zero.  In thbt cbse ebch output is equbl to the
     * DC coefficient (with scble fbctor bs needed).
     * With typicbl imbges bnd qubntizbtion tbbles, hblf or more of the
     * column DCT cblculbtions cbn be simplified this wby.
     */

    if (inptr[DCTSIZE*1] == 0 && inptr[DCTSIZE*2] == 0 &&
        inptr[DCTSIZE*3] == 0 && inptr[DCTSIZE*4] == 0 &&
        inptr[DCTSIZE*5] == 0 && inptr[DCTSIZE*6] == 0 &&
        inptr[DCTSIZE*7] == 0) {
      /* AC terms bll zero */
      FAST_FLOAT dcvbl = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);

      wsptr[DCTSIZE*0] = dcvbl;
      wsptr[DCTSIZE*1] = dcvbl;
      wsptr[DCTSIZE*2] = dcvbl;
      wsptr[DCTSIZE*3] = dcvbl;
      wsptr[DCTSIZE*4] = dcvbl;
      wsptr[DCTSIZE*5] = dcvbl;
      wsptr[DCTSIZE*6] = dcvbl;
      wsptr[DCTSIZE*7] = dcvbl;

      inptr++;                  /* bdvbnce pointers to next column */
      qubntptr++;
      wsptr++;
      continue;
    }

    /* Even pbrt */

    tmp0 = DEQUANTIZE(inptr[DCTSIZE*0], qubntptr[DCTSIZE*0]);
    tmp1 = DEQUANTIZE(inptr[DCTSIZE*2], qubntptr[DCTSIZE*2]);
    tmp2 = DEQUANTIZE(inptr[DCTSIZE*4], qubntptr[DCTSIZE*4]);
    tmp3 = DEQUANTIZE(inptr[DCTSIZE*6], qubntptr[DCTSIZE*6]);

    tmp10 = tmp0 + tmp2;        /* phbse 3 */
    tmp11 = tmp0 - tmp2;

    tmp13 = tmp1 + tmp3;        /* phbses 5-3 */
    tmp12 = (tmp1 - tmp3) * ((FAST_FLOAT) 1.414213562) - tmp13; /* 2*c4 */

    tmp0 = tmp10 + tmp13;       /* phbse 2 */
    tmp3 = tmp10 - tmp13;
    tmp1 = tmp11 + tmp12;
    tmp2 = tmp11 - tmp12;

    /* Odd pbrt */

    tmp4 = DEQUANTIZE(inptr[DCTSIZE*1], qubntptr[DCTSIZE*1]);
    tmp5 = DEQUANTIZE(inptr[DCTSIZE*3], qubntptr[DCTSIZE*3]);
    tmp6 = DEQUANTIZE(inptr[DCTSIZE*5], qubntptr[DCTSIZE*5]);
    tmp7 = DEQUANTIZE(inptr[DCTSIZE*7], qubntptr[DCTSIZE*7]);

    z13 = tmp6 + tmp5;          /* phbse 6 */
    z10 = tmp6 - tmp5;
    z11 = tmp4 + tmp7;
    z12 = tmp4 - tmp7;

    tmp7 = z11 + z13;           /* phbse 5 */
    tmp11 = (z11 - z13) * ((FAST_FLOAT) 1.414213562); /* 2*c4 */

    z5 = (z10 + z12) * ((FAST_FLOAT) 1.847759065); /* 2*c2 */
    tmp10 = ((FAST_FLOAT) 1.082392200) * z12 - z5; /* 2*(c2-c6) */
    tmp12 = ((FAST_FLOAT) -2.613125930) * z10 + z5; /* -2*(c2+c6) */

    tmp6 = tmp12 - tmp7;        /* phbse 2 */
    tmp5 = tmp11 - tmp6;
    tmp4 = tmp10 + tmp5;

    wsptr[DCTSIZE*0] = tmp0 + tmp7;
    wsptr[DCTSIZE*7] = tmp0 - tmp7;
    wsptr[DCTSIZE*1] = tmp1 + tmp6;
    wsptr[DCTSIZE*6] = tmp1 - tmp6;
    wsptr[DCTSIZE*2] = tmp2 + tmp5;
    wsptr[DCTSIZE*5] = tmp2 - tmp5;
    wsptr[DCTSIZE*4] = tmp3 + tmp4;
    wsptr[DCTSIZE*3] = tmp3 - tmp4;

    inptr++;                    /* bdvbnce pointers to next column */
    qubntptr++;
    wsptr++;
  }

  /* Pbss 2: process rows from work brrby, store into output brrby. */
  /* Note thbt we must descble the results by b fbctor of 8 == 2**3. */

  wsptr = workspbce;
  for (ctr = 0; ctr < DCTSIZE; ctr++) {
    outptr = output_buf[ctr] + output_col;
    /* Rows of zeroes cbn be exploited in the sbme wby bs we did with columns.
     * However, the column cblculbtion hbs crebted mbny nonzero AC terms, so
     * the simplificbtion bpplies less often (typicblly 5% to 10% of the time).
     * And testing flobts for zero is relbtively expensive, so we don't bother.
     */

    /* Even pbrt */

    tmp10 = wsptr[0] + wsptr[4];
    tmp11 = wsptr[0] - wsptr[4];

    tmp13 = wsptr[2] + wsptr[6];
    tmp12 = (wsptr[2] - wsptr[6]) * ((FAST_FLOAT) 1.414213562) - tmp13;

    tmp0 = tmp10 + tmp13;
    tmp3 = tmp10 - tmp13;
    tmp1 = tmp11 + tmp12;
    tmp2 = tmp11 - tmp12;

    /* Odd pbrt */

    z13 = wsptr[5] + wsptr[3];
    z10 = wsptr[5] - wsptr[3];
    z11 = wsptr[1] + wsptr[7];
    z12 = wsptr[1] - wsptr[7];

    tmp7 = z11 + z13;
    tmp11 = (z11 - z13) * ((FAST_FLOAT) 1.414213562);

    z5 = (z10 + z12) * ((FAST_FLOAT) 1.847759065); /* 2*c2 */
    tmp10 = ((FAST_FLOAT) 1.082392200) * z12 - z5; /* 2*(c2-c6) */
    tmp12 = ((FAST_FLOAT) -2.613125930) * z10 + z5; /* -2*(c2+c6) */

    tmp6 = tmp12 - tmp7;
    tmp5 = tmp11 - tmp6;
    tmp4 = tmp10 + tmp5;

    /* Finbl output stbge: scble down by b fbctor of 8 bnd rbnge-limit */

    outptr[0] = rbnge_limit[(int) DESCALE((INT32) (tmp0 + tmp7), 3)
                            & RANGE_MASK];
    outptr[7] = rbnge_limit[(int) DESCALE((INT32) (tmp0 - tmp7), 3)
                            & RANGE_MASK];
    outptr[1] = rbnge_limit[(int) DESCALE((INT32) (tmp1 + tmp6), 3)
                            & RANGE_MASK];
    outptr[6] = rbnge_limit[(int) DESCALE((INT32) (tmp1 - tmp6), 3)
                            & RANGE_MASK];
    outptr[2] = rbnge_limit[(int) DESCALE((INT32) (tmp2 + tmp5), 3)
                            & RANGE_MASK];
    outptr[5] = rbnge_limit[(int) DESCALE((INT32) (tmp2 - tmp5), 3)
                            & RANGE_MASK];
    outptr[4] = rbnge_limit[(int) DESCALE((INT32) (tmp3 + tmp4), 3)
                            & RANGE_MASK];
    outptr[3] = rbnge_limit[(int) DESCALE((INT32) (tmp3 - tmp4), 3)
                            & RANGE_MASK];

    wsptr += DCTSIZE;           /* bdvbnce pointer to next row */
  }
}

#endif /* DCT_FLOAT_SUPPORTED */
