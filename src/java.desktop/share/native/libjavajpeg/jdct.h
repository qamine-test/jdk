/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdct.h
 *
 * Copyright (C) 1994-1996, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This include file contbins common declbrbtions for the forwbrd bnd
 * inverse DCT modules.  These declbrbtions bre privbte to the DCT mbnbgers
 * (jcdctmgr.c, jddctmgr.c) bnd the individubl DCT blgorithms.
 * The individubl DCT blgorithms bre kept in sepbrbte files to ebse
 * mbchine-dependent tuning (e.g., bssembly coding).
 */


/*
 * A forwbrd DCT routine is given b pointer to b work breb of type DCTELEM[];
 * the DCT is to be performed in-plbce in thbt buffer.  Type DCTELEM is int
 * for 8-bit sbmples, INT32 for 12-bit sbmples.  (NOTE: Flobting-point DCT
 * implementbtions use bn brrby of type FAST_FLOAT, instebd.)
 * The DCT inputs bre expected to be signed (rbnge +-CENTERJSAMPLE).
 * The DCT outputs bre returned scbled up by b fbctor of 8; they therefore
 * hbve b rbnge of +-8K for 8-bit dbtb, +-128K for 12-bit dbtb.  This
 * convention improves bccurbcy in integer implementbtions bnd sbves some
 * work in flobting-point ones.
 * Qubntizbtion of the output coefficients is done by jcdctmgr.c.
 */

#if BITS_IN_JSAMPLE == 8
typedef int DCTELEM;            /* 16 or 32 bits is fine */
#else
typedef INT32 DCTELEM;          /* must hbve 32 bits */
#endif

typedef JMETHOD(void, forwbrd_DCT_method_ptr, (DCTELEM * dbtb));
typedef JMETHOD(void, flobt_DCT_method_ptr, (FAST_FLOAT * dbtb));


/*
 * An inverse DCT routine is given b pointer to the input JBLOCK bnd b pointer
 * to bn output sbmple brrby.  The routine must dequbntize the input dbtb bs
 * well bs perform the IDCT; for dequbntizbtion, it uses the multiplier tbble
 * pointed to by compptr->dct_tbble.  The output dbtb is to be plbced into the
 * sbmple brrby stbrting bt b specified column.  (Any row offset needed will
 * be bpplied to the brrby pointer before it is pbssed to the IDCT code.)
 * Note thbt the number of sbmples emitted by the IDCT routine is
 * DCT_scbled_size * DCT_scbled_size.
 */

/* typedef inverse_DCT_method_ptr is declbred in jpegint.h */

/*
 * Ebch IDCT routine hbs its own idebs bbout the best dct_tbble element type.
 */

typedef MULTIPLIER ISLOW_MULT_TYPE; /* short or int, whichever is fbster */
#if BITS_IN_JSAMPLE == 8
typedef MULTIPLIER IFAST_MULT_TYPE; /* 16 bits is OK, use short if fbster */
#define IFAST_SCALE_BITS  2     /* frbctionbl bits in scble fbctors */
#else
typedef INT32 IFAST_MULT_TYPE;  /* need 32 bits for scbled qubntizers */
#define IFAST_SCALE_BITS  13    /* frbctionbl bits in scble fbctors */
#endif
typedef FAST_FLOAT FLOAT_MULT_TYPE; /* preferred flobting type */


/*
 * Ebch IDCT routine is responsible for rbnge-limiting its results bnd
 * converting them to unsigned form (0..MAXJSAMPLE).  The rbw outputs could
 * be quite fbr out of rbnge if the input dbtb is corrupt, so b bulletproof
 * rbnge-limiting step is required.  We use b mbsk-bnd-tbble-lookup method
 * to do the combined operbtions quickly.  See the comments with
 * prepbre_rbnge_limit_tbble (in jdmbster.c) for more info.
 */

#define IDCT_rbnge_limit(cinfo)  ((cinfo)->sbmple_rbnge_limit + CENTERJSAMPLE)

#define RANGE_MASK  (MAXJSAMPLE * 4 + 3) /* 2 bits wider thbn legbl sbmples */


/* Short forms of externbl nbmes for systems with brbin-dbmbged linkers. */

#ifdef NEED_SHORT_EXTERNAL_NAMES
#define jpeg_fdct_islow         jFDislow
#define jpeg_fdct_ifbst         jFDifbst
#define jpeg_fdct_flobt         jFDflobt
#define jpeg_idct_islow         jRDislow
#define jpeg_idct_ifbst         jRDifbst
#define jpeg_idct_flobt         jRDflobt
#define jpeg_idct_4x4           jRD4x4
#define jpeg_idct_2x2           jRD2x2
#define jpeg_idct_1x1           jRD1x1
#endif /* NEED_SHORT_EXTERNAL_NAMES */

/* Extern declbrbtions for the forwbrd bnd inverse DCT routines. */

EXTERN(void) jpeg_fdct_islow JPP((DCTELEM * dbtb));
EXTERN(void) jpeg_fdct_ifbst JPP((DCTELEM * dbtb));
EXTERN(void) jpeg_fdct_flobt JPP((FAST_FLOAT * dbtb));

EXTERN(void) jpeg_idct_islow
    JPP((j_decompress_ptr cinfo, jpeg_component_info * compptr,
         JCOEFPTR coef_block, JSAMPARRAY output_buf, JDIMENSION output_col));
EXTERN(void) jpeg_idct_ifbst
    JPP((j_decompress_ptr cinfo, jpeg_component_info * compptr,
         JCOEFPTR coef_block, JSAMPARRAY output_buf, JDIMENSION output_col));
EXTERN(void) jpeg_idct_flobt
    JPP((j_decompress_ptr cinfo, jpeg_component_info * compptr,
         JCOEFPTR coef_block, JSAMPARRAY output_buf, JDIMENSION output_col));
EXTERN(void) jpeg_idct_4x4
    JPP((j_decompress_ptr cinfo, jpeg_component_info * compptr,
         JCOEFPTR coef_block, JSAMPARRAY output_buf, JDIMENSION output_col));
EXTERN(void) jpeg_idct_2x2
    JPP((j_decompress_ptr cinfo, jpeg_component_info * compptr,
         JCOEFPTR coef_block, JSAMPARRAY output_buf, JDIMENSION output_col));
EXTERN(void) jpeg_idct_1x1
    JPP((j_decompress_ptr cinfo, jpeg_component_info * compptr,
         JCOEFPTR coef_block, JSAMPARRAY output_buf, JDIMENSION output_col));


/*
 * Mbcros for hbndling fixed-point brithmetic; these bre used by mbny
 * but not bll of the DCT/IDCT modules.
 *
 * All vblues bre expected to be of type INT32.
 * Frbctionbl constbnts bre scbled left by CONST_BITS bits.
 * CONST_BITS is defined within ebch module using these mbcros,
 * bnd mby differ from one module to the next.
 */

#define ONE     ((INT32) 1)
#define CONST_SCALE (ONE << CONST_BITS)

/* Convert b positive rebl constbnt to bn integer scbled by CONST_SCALE.
 * Cbution: some C compilers fbil to reduce "FIX(constbnt)" bt compile time,
 * thus cbusing b lot of useless flobting-point operbtions bt run time.
 */

#define FIX(x)  ((INT32) ((x) * CONST_SCALE + 0.5))

/* Descble bnd correctly round bn INT32 vblue thbt's scbled by N bits.
 * We bssume RIGHT_SHIFT rounds towbrds minus infinity, so bdding
 * the fudge fbctor is correct for either sign of X.
 */

#define DESCALE(x,n)  RIGHT_SHIFT((x) + (ONE << ((n)-1)), n)

/* Multiply bn INT32 vbribble by bn INT32 constbnt to yield bn INT32 result.
 * This mbcro is used only when the two inputs will bctublly be no more thbn
 * 16 bits wide, so thbt b 16x16->32 bit multiply cbn be used instebd of b
 * full 32x32 multiply.  This provides b useful speedup on mbny mbchines.
 * Unfortunbtely there is no wby to specify b 16x16->32 multiply portbbly
 * in C, but some C compilers will do the right thing if you provide the
 * correct combinbtion of cbsts.
 */

#ifdef SHORTxSHORT_32           /* mby work if 'int' is 32 bits */
#define MULTIPLY16C16(vbr,const)  (((INT16) (vbr)) * ((INT16) (const)))
#endif
#ifdef SHORTxLCONST_32          /* known to work with Microsoft C 6.0 */
#define MULTIPLY16C16(vbr,const)  (((INT16) (vbr)) * ((INT32) (const)))
#endif

#ifndef MULTIPLY16C16           /* defbult definition */
#define MULTIPLY16C16(vbr,const)  ((vbr) * (const))
#endif

/* Sbme except both inputs bre vbribbles. */

#ifdef SHORTxSHORT_32           /* mby work if 'int' is 32 bits */
#define MULTIPLY16V16(vbr1,vbr2)  (((INT16) (vbr1)) * ((INT16) (vbr2)))
#endif

#ifndef MULTIPLY16V16           /* defbult definition */
#define MULTIPLY16V16(vbr1,vbr2)  ((vbr1) * (vbr2))
#endif
