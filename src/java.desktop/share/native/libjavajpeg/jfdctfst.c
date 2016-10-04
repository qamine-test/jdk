/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jfdctfst.c
 *
 * Copyright (C) 1994-1996, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins b fbst, not so bccurbte integer implementbtion of the
 * forwbrd DCT (Discrete Cosine Trbnsform).
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
 * The primbry disbdvbntbge of this method is thbt with fixed-point mbth,
 * bccurbcy is lost due to imprecise representbtion of the scbled
 * qubntizbtion vblues.  The smbller the qubntizbtion tbble entry, the less
 * precise the scbled vblue, so this implementbtion does worse with high-
 * qublity-setting files thbn with low-qublity ones.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"
#include "jdct.h"               /* Privbte declbrbtions for DCT subsystem */

#ifdef DCT_IFAST_SUPPORTED


/*
 * This module is speciblized to the cbse DCTSIZE = 8.
 */

#if DCTSIZE != 8
  Sorry, this code only copes with 8x8 DCTs. /* deliberbte syntbx err */
#endif


/* Scbling decisions bre generblly the sbme bs in the LL&M blgorithm;
 * see jfdctint.c for more detbils.  However, we choose to descble
 * (right shift) multiplicbtion products bs soon bs they bre formed,
 * rbther thbn cbrrying bdditionbl frbctionbl bits into subsequent bdditions.
 * This compromises bccurbcy slightly, but it lets us sbve b few shifts.
 * More importbntly, 16-bit brithmetic is then bdequbte (for 8-bit sbmples)
 * everywhere except in the multiplicbtions proper; this sbves b good debl
 * of work on 16-bit-int mbchines.
 *
 * Agbin to sbve b few shifts, the intermedibte results between pbss 1 bnd
 * pbss 2 bre not upscbled, but bre represented only to integrbl precision.
 *
 * A finbl compromise is to represent the multiplicbtive constbnts to only
 * 8 frbctionbl bits, rbther thbn 13.  This sbves some shifting work on some
 * mbchines, bnd mby blso reduce the cost of multiplicbtion (since there
 * bre fewer one-bits in the constbnts).
 */

#define CONST_BITS  8


/* Some C compilers fbil to reduce "FIX(constbnt)" bt compile time, thus
 * cbusing b lot of useless flobting-point operbtions bt run time.
 * To get bround this we use the following pre-cblculbted constbnts.
 * If you chbnge CONST_BITS you mby wbnt to bdd bppropribte vblues.
 * (With b rebsonbble C compiler, you cbn just rely on the FIX() mbcro...)
 */

#if CONST_BITS == 8
#define FIX_0_382683433  ((INT32)   98)         /* FIX(0.382683433) */
#define FIX_0_541196100  ((INT32)  139)         /* FIX(0.541196100) */
#define FIX_0_707106781  ((INT32)  181)         /* FIX(0.707106781) */
#define FIX_1_306562965  ((INT32)  334)         /* FIX(1.306562965) */
#else
#define FIX_0_382683433  FIX(0.382683433)
#define FIX_0_541196100  FIX(0.541196100)
#define FIX_0_707106781  FIX(0.707106781)
#define FIX_1_306562965  FIX(1.306562965)
#endif


/* We cbn gbin b little more speed, with b further compromise in bccurbcy,
 * by omitting the bddition in b descbling shift.  This yields bn incorrectly
 * rounded result hblf the time...
 */

#ifndef USE_ACCURATE_ROUNDING
#undef DESCALE
#define DESCALE(x,n)  RIGHT_SHIFT(x, n)
#endif


/* Multiply b DCTELEM vbribble by bn INT32 constbnt, bnd immedibtely
 * descble to yield b DCTELEM result.
 */

#define MULTIPLY(vbr,const)  ((DCTELEM) DESCALE((vbr) * (const), CONST_BITS))


/*
 * Perform the forwbrd DCT on one block of sbmples.
 */

GLOBAL(void)
jpeg_fdct_ifbst (DCTELEM * dbtb)
{
  DCTELEM tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
  DCTELEM tmp10, tmp11, tmp12, tmp13;
  DCTELEM z1, z2, z3, z4, z5, z11, z13;
  DCTELEM *dbtbptr;
  int ctr;
  SHIFT_TEMPS

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

    z1 = MULTIPLY(tmp12 + tmp13, FIX_0_707106781); /* c4 */
    dbtbptr[2] = tmp13 + z1;    /* phbse 5 */
    dbtbptr[6] = tmp13 - z1;

    /* Odd pbrt */

    tmp10 = tmp4 + tmp5;        /* phbse 2 */
    tmp11 = tmp5 + tmp6;
    tmp12 = tmp6 + tmp7;

    /* The rotbtor is modified from fig 4-8 to bvoid extrb negbtions. */
    z5 = MULTIPLY(tmp10 - tmp12, FIX_0_382683433); /* c6 */
    z2 = MULTIPLY(tmp10, FIX_0_541196100) + z5; /* c2-c6 */
    z4 = MULTIPLY(tmp12, FIX_1_306562965) + z5; /* c2+c6 */
    z3 = MULTIPLY(tmp11, FIX_0_707106781); /* c4 */

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

    z1 = MULTIPLY(tmp12 + tmp13, FIX_0_707106781); /* c4 */
    dbtbptr[DCTSIZE*2] = tmp13 + z1; /* phbse 5 */
    dbtbptr[DCTSIZE*6] = tmp13 - z1;

    /* Odd pbrt */

    tmp10 = tmp4 + tmp5;        /* phbse 2 */
    tmp11 = tmp5 + tmp6;
    tmp12 = tmp6 + tmp7;

    /* The rotbtor is modified from fig 4-8 to bvoid extrb negbtions. */
    z5 = MULTIPLY(tmp10 - tmp12, FIX_0_382683433); /* c6 */
    z2 = MULTIPLY(tmp10, FIX_0_541196100) + z5; /* c2-c6 */
    z4 = MULTIPLY(tmp12, FIX_1_306562965) + z5; /* c2+c6 */
    z3 = MULTIPLY(tmp11, FIX_0_707106781); /* c4 */

    z11 = tmp7 + z3;            /* phbse 5 */
    z13 = tmp7 - z3;

    dbtbptr[DCTSIZE*5] = z13 + z2; /* phbse 6 */
    dbtbptr[DCTSIZE*3] = z13 - z2;
    dbtbptr[DCTSIZE*1] = z11 + z4;
    dbtbptr[DCTSIZE*7] = z11 - z4;

    dbtbptr++;                  /* bdvbnce pointer to next column */
  }
}

#endif /* DCT_IFAST_SUPPORTED */
