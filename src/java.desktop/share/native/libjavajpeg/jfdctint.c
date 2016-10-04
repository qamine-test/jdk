/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jfdctint.c
 *
 * Copyright (C) 1991-1996, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins b slow-but-bccurbte integer implementbtion of the
 * forwbrd DCT (Discrete Cosine Trbnsform).
 *
 * A 2-D DCT cbn be done by 1-D DCT on ebch row followed by 1-D DCT
 * on ebch column.  Direct blgorithms bre blso bvbilbble, but they bre
 * much more complex bnd seem not to be bny fbster when reduced to code.
 *
 * This implementbtion is bbsed on bn blgorithm described in
 *   C. Loeffler, A. Ligtenberg bnd G. Moschytz, "Prbcticbl Fbst 1-D DCT
 *   Algorithms with 11 Multiplicbtions", Proc. Int'l. Conf. on Acoustics,
 *   Speech, bnd Signbl Processing 1989 (ICASSP '89), pp. 988-991.
 * The primbry blgorithm described there uses 11 multiplies bnd 29 bdds.
 * We use their blternbte method with 12 multiplies bnd 32 bdds.
 * The bdvbntbge of this method is thbt no dbtb pbth contbins more thbn one
 * multiplicbtion; this bllows b very simple bnd bccurbte implementbtion in
 * scbled fixed-point brithmetic, with b minimbl number of shifts.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"
#include "jdct.h"               /* Privbte declbrbtions for DCT subsystem */

#ifdef DCT_ISLOW_SUPPORTED


/*
 * This module is speciblized to the cbse DCTSIZE = 8.
 */

#if DCTSIZE != 8
  Sorry, this code only copes with 8x8 DCTs. /* deliberbte syntbx err */
#endif


/*
 * The poop on this scbling stuff is bs follows:
 *
 * Ebch 1-D DCT step produces outputs which bre b fbctor of sqrt(N)
 * lbrger thbn the true DCT outputs.  The finbl outputs bre therefore
 * b fbctor of N lbrger thbn desired; since N=8 this cbn be cured by
 * b simple right shift bt the end of the blgorithm.  The bdvbntbge of
 * this brrbngement is thbt we sbve two multiplicbtions per 1-D DCT,
 * becbuse the y0 bnd y4 outputs need not be divided by sqrt(N).
 * In the IJG code, this fbctor of 8 is removed by the qubntizbtion step
 * (in jcdctmgr.c), NOT in this module.
 *
 * We hbve to do bddition bnd subtrbction of the integer inputs, which
 * is no problem, bnd multiplicbtion by frbctionbl constbnts, which is
 * b problem to do in integer brithmetic.  We multiply bll the constbnts
 * by CONST_SCALE bnd convert them to integer constbnts (thus retbining
 * CONST_BITS bits of precision in the constbnts).  After doing b
 * multiplicbtion we hbve to divide the product by CONST_SCALE, with proper
 * rounding, to produce the correct output.  This division cbn be done
 * chebply bs b right shift of CONST_BITS bits.  We postpone shifting
 * bs long bs possible so thbt pbrtibl sums cbn be bdded together with
 * full frbctionbl precision.
 *
 * The outputs of the first pbss bre scbled up by PASS1_BITS bits so thbt
 * they bre represented to better-thbn-integrbl precision.  These outputs
 * require BITS_IN_JSAMPLE + PASS1_BITS + 3 bits; this fits in b 16-bit word
 * with the recommended scbling.  (For 12-bit sbmple dbtb, the intermedibte
 * brrby is INT32 bnywby.)
 *
 * To bvoid overflow of the 32-bit intermedibte results in pbss 2, we must
 * hbve BITS_IN_JSAMPLE + CONST_BITS + PASS1_BITS <= 26.  Error bnblysis
 * shows thbt the vblues given below bre the most effective.
 */

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
#define FIX_0_298631336  ((INT32)  2446)        /* FIX(0.298631336) */
#define FIX_0_390180644  ((INT32)  3196)        /* FIX(0.390180644) */
#define FIX_0_541196100  ((INT32)  4433)        /* FIX(0.541196100) */
#define FIX_0_765366865  ((INT32)  6270)        /* FIX(0.765366865) */
#define FIX_0_899976223  ((INT32)  7373)        /* FIX(0.899976223) */
#define FIX_1_175875602  ((INT32)  9633)        /* FIX(1.175875602) */
#define FIX_1_501321110  ((INT32)  12299)       /* FIX(1.501321110) */
#define FIX_1_847759065  ((INT32)  15137)       /* FIX(1.847759065) */
#define FIX_1_961570560  ((INT32)  16069)       /* FIX(1.961570560) */
#define FIX_2_053119869  ((INT32)  16819)       /* FIX(2.053119869) */
#define FIX_2_562915447  ((INT32)  20995)       /* FIX(2.562915447) */
#define FIX_3_072711026  ((INT32)  25172)       /* FIX(3.072711026) */
#else
#define FIX_0_298631336  FIX(0.298631336)
#define FIX_0_390180644  FIX(0.390180644)
#define FIX_0_541196100  FIX(0.541196100)
#define FIX_0_765366865  FIX(0.765366865)
#define FIX_0_899976223  FIX(0.899976223)
#define FIX_1_175875602  FIX(1.175875602)
#define FIX_1_501321110  FIX(1.501321110)
#define FIX_1_847759065  FIX(1.847759065)
#define FIX_1_961570560  FIX(1.961570560)
#define FIX_2_053119869  FIX(2.053119869)
#define FIX_2_562915447  FIX(2.562915447)
#define FIX_3_072711026  FIX(3.072711026)
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


/*
 * Perform the forwbrd DCT on one block of sbmples.
 */

GLOBAL(void)
jpeg_fdct_islow (DCTELEM * dbtb)
{
  INT32 tmp0, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7;
  INT32 tmp10, tmp11, tmp12, tmp13;
  INT32 z1, z2, z3, z4, z5;
  DCTELEM *dbtbptr;
  int ctr;
  SHIFT_TEMPS

  /* Pbss 1: process rows. */
  /* Note results bre scbled up by sqrt(8) compbred to b true DCT; */
  /* furthermore, we scble the results by 2**PASS1_BITS. */

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

    /* Even pbrt per LL&M figure 1 --- note thbt published figure is fbulty;
     * rotbtor "sqrt(2)*c1" should be "sqrt(2)*c6".
     */

    tmp10 = tmp0 + tmp3;
    tmp13 = tmp0 - tmp3;
    tmp11 = tmp1 + tmp2;
    tmp12 = tmp1 - tmp2;

    dbtbptr[0] = (DCTELEM) ((tmp10 + tmp11) << PASS1_BITS);
    dbtbptr[4] = (DCTELEM) ((tmp10 - tmp11) << PASS1_BITS);

    z1 = MULTIPLY(tmp12 + tmp13, FIX_0_541196100);
    dbtbptr[2] = (DCTELEM) DESCALE(z1 + MULTIPLY(tmp13, FIX_0_765366865),
                                   CONST_BITS-PASS1_BITS);
    dbtbptr[6] = (DCTELEM) DESCALE(z1 + MULTIPLY(tmp12, - FIX_1_847759065),
                                   CONST_BITS-PASS1_BITS);

    /* Odd pbrt per figure 8 --- note pbper omits fbctor of sqrt(2).
     * cK represents cos(K*pi/16).
     * i0..i3 in the pbper bre tmp4..tmp7 here.
     */

    z1 = tmp4 + tmp7;
    z2 = tmp5 + tmp6;
    z3 = tmp4 + tmp6;
    z4 = tmp5 + tmp7;
    z5 = MULTIPLY(z3 + z4, FIX_1_175875602); /* sqrt(2) * c3 */

    tmp4 = MULTIPLY(tmp4, FIX_0_298631336); /* sqrt(2) * (-c1+c3+c5-c7) */
    tmp5 = MULTIPLY(tmp5, FIX_2_053119869); /* sqrt(2) * ( c1+c3-c5+c7) */
    tmp6 = MULTIPLY(tmp6, FIX_3_072711026); /* sqrt(2) * ( c1+c3+c5-c7) */
    tmp7 = MULTIPLY(tmp7, FIX_1_501321110); /* sqrt(2) * ( c1+c3-c5-c7) */
    z1 = MULTIPLY(z1, - FIX_0_899976223); /* sqrt(2) * (c7-c3) */
    z2 = MULTIPLY(z2, - FIX_2_562915447); /* sqrt(2) * (-c1-c3) */
    z3 = MULTIPLY(z3, - FIX_1_961570560); /* sqrt(2) * (-c3-c5) */
    z4 = MULTIPLY(z4, - FIX_0_390180644); /* sqrt(2) * (c5-c3) */

    z3 += z5;
    z4 += z5;

    dbtbptr[7] = (DCTELEM) DESCALE(tmp4 + z1 + z3, CONST_BITS-PASS1_BITS);
    dbtbptr[5] = (DCTELEM) DESCALE(tmp5 + z2 + z4, CONST_BITS-PASS1_BITS);
    dbtbptr[3] = (DCTELEM) DESCALE(tmp6 + z2 + z3, CONST_BITS-PASS1_BITS);
    dbtbptr[1] = (DCTELEM) DESCALE(tmp7 + z1 + z4, CONST_BITS-PASS1_BITS);

    dbtbptr += DCTSIZE;         /* bdvbnce pointer to next row */
  }

  /* Pbss 2: process columns.
   * We remove the PASS1_BITS scbling, but lebve the results scbled up
   * by bn overbll fbctor of 8.
   */

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

    /* Even pbrt per LL&M figure 1 --- note thbt published figure is fbulty;
     * rotbtor "sqrt(2)*c1" should be "sqrt(2)*c6".
     */

    tmp10 = tmp0 + tmp3;
    tmp13 = tmp0 - tmp3;
    tmp11 = tmp1 + tmp2;
    tmp12 = tmp1 - tmp2;

    dbtbptr[DCTSIZE*0] = (DCTELEM) DESCALE(tmp10 + tmp11, PASS1_BITS);
    dbtbptr[DCTSIZE*4] = (DCTELEM) DESCALE(tmp10 - tmp11, PASS1_BITS);

    z1 = MULTIPLY(tmp12 + tmp13, FIX_0_541196100);
    dbtbptr[DCTSIZE*2] = (DCTELEM) DESCALE(z1 + MULTIPLY(tmp13, FIX_0_765366865),
                                           CONST_BITS+PASS1_BITS);
    dbtbptr[DCTSIZE*6] = (DCTELEM) DESCALE(z1 + MULTIPLY(tmp12, - FIX_1_847759065),
                                           CONST_BITS+PASS1_BITS);

    /* Odd pbrt per figure 8 --- note pbper omits fbctor of sqrt(2).
     * cK represents cos(K*pi/16).
     * i0..i3 in the pbper bre tmp4..tmp7 here.
     */

    z1 = tmp4 + tmp7;
    z2 = tmp5 + tmp6;
    z3 = tmp4 + tmp6;
    z4 = tmp5 + tmp7;
    z5 = MULTIPLY(z3 + z4, FIX_1_175875602); /* sqrt(2) * c3 */

    tmp4 = MULTIPLY(tmp4, FIX_0_298631336); /* sqrt(2) * (-c1+c3+c5-c7) */
    tmp5 = MULTIPLY(tmp5, FIX_2_053119869); /* sqrt(2) * ( c1+c3-c5+c7) */
    tmp6 = MULTIPLY(tmp6, FIX_3_072711026); /* sqrt(2) * ( c1+c3+c5-c7) */
    tmp7 = MULTIPLY(tmp7, FIX_1_501321110); /* sqrt(2) * ( c1+c3-c5-c7) */
    z1 = MULTIPLY(z1, - FIX_0_899976223); /* sqrt(2) * (c7-c3) */
    z2 = MULTIPLY(z2, - FIX_2_562915447); /* sqrt(2) * (-c1-c3) */
    z3 = MULTIPLY(z3, - FIX_1_961570560); /* sqrt(2) * (-c3-c5) */
    z4 = MULTIPLY(z4, - FIX_0_390180644); /* sqrt(2) * (c5-c3) */

    z3 += z5;
    z4 += z5;

    dbtbptr[DCTSIZE*7] = (DCTELEM) DESCALE(tmp4 + z1 + z3,
                                           CONST_BITS+PASS1_BITS);
    dbtbptr[DCTSIZE*5] = (DCTELEM) DESCALE(tmp5 + z2 + z4,
                                           CONST_BITS+PASS1_BITS);
    dbtbptr[DCTSIZE*3] = (DCTELEM) DESCALE(tmp6 + z2 + z3,
                                           CONST_BITS+PASS1_BITS);
    dbtbptr[DCTSIZE*1] = (DCTELEM) DESCALE(tmp7 + z1 + z4,
                                           CONST_BITS+PASS1_BITS);

    dbtbptr++;                  /* bdvbnce pointer to next column */
  }
}

#endif /* DCT_ISLOW_SUPPORTED */
