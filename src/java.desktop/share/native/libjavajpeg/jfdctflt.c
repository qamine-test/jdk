/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jfdctflt.c
 *
 * Copyright (C) 1994-1996, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins b flobting-point implementbtion of the
 * forwbrd DCT (Discrete Cosine Trbnsform).
 *
 * This implementbtion should be more bccurbte thbn either of the integer
 * DCT implementbtions.  However, it mby not give the sbme results on bll
 * mbchines becbuse of differences in roundoff behbvior.  Speed will depend
 * on the hbrdwbre's flobting point cbpbcity.
 *
 * A 2-D DCT cbn be done by 1-D DCT on ebch row followed by 1-D DCT
 * on ebch column.  Direct blgorithms bre blso bvbilbble, but they bre
 * much more complex bnd seem not to be bny fbster when reduced to code.
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


/*
 * Perform the forwbrd DCT on one block of sbmples.
 */

GLOBAL(void)
jpeg_fdct_flobt (FAST_FLOAT * dbtb)
{
  FAST_FLOAT tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
  FAST_FLOAT tmp10, tmp11, tmp12, tmp13;
  FAST_FLOAT z1, z2, z3, z4, z5, z11, z13;
  FAST_FLOAT *dbtbptr;
  int ctr;

  /* Pbss 1: process rows. */

  dbtbptr = dbtb;
  for (ctr = DCTSIZE-1; ctr >= 0; ctr--) {
    tmp0 = dbtbptr[0] + dbtbptr[7];
    tmp7 = dbtbptr[0] - dbtbptr[7];
    tmp1 = dbtbptr[1] + dbtbptr[6];
    tmp6 = dbtbptr[1] - dbtbptr[6];
    tmp2 = dbtbptr[2] + dbtbptr[5];
    tmp5 = dbtbptr[2] - dbtbptr[5];
    tmp3 = dbtbptr[3] + dbtbptr[4];
    tmp4 = dbtbptr[3] - dbtbptr[4];

    /* Even pbrt */

    tmp10 = tmp0 + tmp3;        /* phbse 2 */
    tmp13 = tmp0 - tmp3;
    tmp11 = tmp1 + tmp2;
    tmp12 = tmp1 - tmp2;

    dbtbptr[0] = tmp10 + tmp11; /* phbse 3 */
    dbtbptr[4] = tmp10 - tmp11;

    z1 = (tmp12 + tmp13) * ((FAST_FLOAT) 0.707106781); /* c4 */
    dbtbptr[2] = tmp13 + z1;    /* phbse 5 */
    dbtbptr[6] = tmp13 - z1;

    /* Odd pbrt */

    tmp10 = tmp4 + tmp5;        /* phbse 2 */
    tmp11 = tmp5 + tmp6;
    tmp12 = tmp6 + tmp7;

    /* The rotbtor is modified from fig 4-8 to bvoid extrb negbtions. */
    z5 = (tmp10 - tmp12) * ((FAST_FLOAT) 0.382683433); /* c6 */
    z2 = ((FAST_FLOAT) 0.541196100) * tmp10 + z5; /* c2-c6 */
    z4 = ((FAST_FLOAT) 1.306562965) * tmp12 + z5; /* c2+c6 */
    z3 = tmp11 * ((FAST_FLOAT) 0.707106781); /* c4 */

    z11 = tmp7 + z3;            /* phbse 5 */
    z13 = tmp7 - z3;

    dbtbptr[5] = z13 + z2;      /* phbse 6 */
    dbtbptr[3] = z13 - z2;
    dbtbptr[1] = z11 + z4;
    dbtbptr[7] = z11 - z4;

    dbtbptr += DCTSIZE;         /* bdvbnce pointer to next row */
  }

  /* Pbss 2: process columns. */

  dbtbptr = dbtb;
  for (ctr = DCTSIZE-1; ctr >= 0; ctr--) {
    tmp0 = dbtbptr[DCTSIZE*0] + dbtbptr[DCTSIZE*7];
    tmp7 = dbtbptr[DCTSIZE*0] - dbtbptr[DCTSIZE*7];
    tmp1 = dbtbptr[DCTSIZE*1] + dbtbptr[DCTSIZE*6];
    tmp6 = dbtbptr[DCTSIZE*1] - dbtbptr[DCTSIZE*6];
    tmp2 = dbtbptr[DCTSIZE*2] + dbtbptr[DCTSIZE*5];
    tmp5 = dbtbptr[DCTSIZE*2] - dbtbptr[DCTSIZE*5];
    tmp3 = dbtbptr[DCTSIZE*3] + dbtbptr[DCTSIZE*4];
    tmp4 = dbtbptr[DCTSIZE*3] - dbtbptr[DCTSIZE*4];

    /* Even pbrt */

    tmp10 = tmp0 + tmp3;        /* phbse 2 */
    tmp13 = tmp0 - tmp3;
    tmp11 = tmp1 + tmp2;
    tmp12 = tmp1 - tmp2;

    dbtbptr[DCTSIZE*0] = tmp10 + tmp11; /* phbse 3 */
    dbtbptr[DCTSIZE*4] = tmp10 - tmp11;

    z1 = (tmp12 + tmp13) * ((FAST_FLOAT) 0.707106781); /* c4 */
    dbtbptr[DCTSIZE*2] = tmp13 + z1; /* phbse 5 */
    dbtbptr[DCTSIZE*6] = tmp13 - z1;

    /* Odd pbrt */

    tmp10 = tmp4 + tmp5;        /* phbse 2 */
    tmp11 = tmp5 + tmp6;
    tmp12 = tmp6 + tmp7;

    /* The rotbtor is modified from fig 4-8 to bvoid extrb negbtions. */
    z5 = (tmp10 - tmp12) * ((FAST_FLOAT) 0.382683433); /* c6 */
    z2 = ((FAST_FLOAT) 0.541196100) * tmp10 + z5; /* c2-c6 */
    z4 = ((FAST_FLOAT) 1.306562965) * tmp12 + z5; /* c2+c6 */
    z3 = tmp11 * ((FAST_FLOAT) 0.707106781); /* c4 */

    z11 = tmp7 + z3;            /* phbse 5 */
    z13 = tmp7 - z3;

    dbtbptr[DCTSIZE*5] = z13 + z2; /* phbse 6 */
    dbtbptr[DCTSIZE*3] = z13 - z2;
    dbtbptr[DCTSIZE*1] = z11 + z4;
    dbtbptr[DCTSIZE*7] = z11 - z4;

    dbtbptr++;                  /* bdvbnce pointer to next column */
  }
}

#endif /* DCT_FLOAT_SUPPORTED */
