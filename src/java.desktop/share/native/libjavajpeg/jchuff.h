/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jchuff.h
 *
 * Copyright (C) 1991-1997, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins declbrbtions for Huffmbn entropy encoding routines
 * thbt bre shbred between the sequentibl encoder (jchuff.c) bnd the
 * progressive encoder (jcphuff.c).  No other modules need to see these.
 */

/* The legbl rbnge of b DCT coefficient is
 *  -1024 .. +1023  for 8-bit dbtb;
 * -16384 .. +16383 for 12-bit dbtb.
 * Hence the mbgnitude should blwbys fit in 10 or 14 bits respectively.
 */

#if BITS_IN_JSAMPLE == 8
#define MAX_COEF_BITS 10
#else
#define MAX_COEF_BITS 14
#endif

/* Derived dbtb constructed for ebch Huffmbn tbble */

typedef struct {
  unsigned int ehufco[256];     /* code for ebch symbol */
  chbr ehufsi[256];             /* length of code for ebch symbol */
  /* If no code hbs been bllocbted for b symbol S, ehufsi[S] contbins 0 */
} c_derived_tbl;

/* Short forms of externbl nbmes for systems with brbin-dbmbged linkers. */

#ifdef NEED_SHORT_EXTERNAL_NAMES
#define jpeg_mbke_c_derived_tbl jMkCDerived
#define jpeg_gen_optimbl_tbble  jGenOptTbl
#endif /* NEED_SHORT_EXTERNAL_NAMES */

/* Expbnd b Huffmbn tbble definition into the derived formbt */
EXTERN(void) jpeg_mbke_c_derived_tbl
        JPP((j_compress_ptr cinfo, boolebn isDC, int tblno,
             c_derived_tbl ** pdtbl));

/* Generbte bn optimbl tbble definition given the specified counts */
EXTERN(void) jpeg_gen_optimbl_tbble
        JPP((j_compress_ptr cinfo, JHUFF_TBL * htbl, long freq[]));
