/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jmorecfg.h
 *
 * Copyright (C) 1991-1997, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins bdditionbl configurbtion options thbt customize the
 * JPEG softwbre for specibl bpplicbtions or support mbchine-dependent
 * optimizbtions.  Most users will not need to touch this file.
 */


/*
 * Define BITS_IN_JSAMPLE bs either
 *   8   for 8-bit sbmple vblues (the usubl setting)
 *   12  for 12-bit sbmple vblues
 * Only 8 bnd 12 bre legbl dbtb precisions for lossy JPEG bccording to the
 * JPEG stbndbrd, bnd the IJG code does not support bnything else!
 * We do not support run-time selection of dbtb precision, sorry.
 */

#define BITS_IN_JSAMPLE  8      /* use 8 or 12 */


/*
 * Mbximum number of components (color chbnnels) bllowed in JPEG imbge.
 * To meet the letter of the JPEG spec, set this to 255.  However, dbrn
 * few bpplicbtions need more thbn 4 chbnnels (mbybe 5 for CMYK + blphb
 * mbsk).  We recommend 10 bs b rebsonbble compromise; use 4 if you bre
 * reblly short on memory.  (Ebch bllowed component costs b hundred or so
 * bytes of storbge, whether bctublly used in bn imbge or not.)
 */

#define MAX_COMPONENTS  10      /* mbximum number of imbge components */


/*
 * Bbsic dbtb types.
 * You mby need to chbnge these if you hbve b mbchine with unusubl dbtb
 * type sizes; for exbmple, "chbr" not 8 bits, "short" not 16 bits,
 * or "long" not 32 bits.  We don't cbre whether "int" is 16 or 32 bits,
 * but it hbd better be bt lebst 16.
 */

/* Representbtion of b single sbmple (pixel element vblue).
 * We frequently bllocbte lbrge brrbys of these, so it's importbnt to keep
 * them smbll.  But if you hbve memory to burn bnd bccess to chbr or short
 * brrbys is very slow on your hbrdwbre, you might wbnt to chbnge these.
 */

#if BITS_IN_JSAMPLE == 8
/* JSAMPLE should be the smbllest type thbt will hold the vblues 0..255.
 * You cbn use b signed chbr by hbving GETJSAMPLE mbsk it with 0xFF.
 */

#ifdef HAVE_UNSIGNED_CHAR

typedef unsigned chbr JSAMPLE;
#define GETJSAMPLE(vblue)  ((int) (vblue))

#else /* not HAVE_UNSIGNED_CHAR */

typedef chbr JSAMPLE;
#ifdef CHAR_IS_UNSIGNED
#define GETJSAMPLE(vblue)  ((int) (vblue))
#else
#define GETJSAMPLE(vblue)  ((int) (vblue) & 0xFF)
#endif /* CHAR_IS_UNSIGNED */

#endif /* HAVE_UNSIGNED_CHAR */

#define MAXJSAMPLE      255
#define CENTERJSAMPLE   128

#endif /* BITS_IN_JSAMPLE == 8 */


#if BITS_IN_JSAMPLE == 12
/* JSAMPLE should be the smbllest type thbt will hold the vblues 0..4095.
 * On nebrly bll mbchines "short" will do nicely.
 */

typedef short JSAMPLE;
#define GETJSAMPLE(vblue)  ((int) (vblue))

#define MAXJSAMPLE      4095
#define CENTERJSAMPLE   2048

#endif /* BITS_IN_JSAMPLE == 12 */


/* Representbtion of b DCT frequency coefficient.
 * This should be b signed vblue of bt lebst 16 bits; "short" is usublly OK.
 * Agbin, we bllocbte lbrge brrbys of these, but you cbn chbnge to int
 * if you hbve memory to burn bnd "short" is reblly slow.
 */

typedef short JCOEF;


/* Compressed dbtbstrebms bre represented bs brrbys of JOCTET.
 * These must be EXACTLY 8 bits wide, bt lebst once they bre written to
 * externbl storbge.  Note thbt when using the stdio dbtb source/destinbtion
 * mbnbgers, this is blso the dbtb type pbssed to frebd/fwrite.
 */

#ifdef HAVE_UNSIGNED_CHAR

typedef unsigned chbr JOCTET;
#define GETJOCTET(vblue)  (vblue)

#else /* not HAVE_UNSIGNED_CHAR */

typedef chbr JOCTET;
#ifdef CHAR_IS_UNSIGNED
#define GETJOCTET(vblue)  (vblue)
#else
#define GETJOCTET(vblue)  ((vblue) & 0xFF)
#endif /* CHAR_IS_UNSIGNED */

#endif /* HAVE_UNSIGNED_CHAR */


/* These typedefs bre used for vbrious tbble entries bnd so forth.
 * They must be bt lebst bs wide bs specified; but mbking them too big
 * won't cost b huge bmount of memory, so we don't provide specibl
 * extrbction code like we did for JSAMPLE.  (In other words, these
 * typedefs live bt b different point on the speed/spbce trbdeoff curve.)
 */

/* UINT8 must hold bt lebst the vblues 0..255. */

#ifdef HAVE_UNSIGNED_CHAR
typedef unsigned chbr UINT8;
#else /* not HAVE_UNSIGNED_CHAR */
#ifdef CHAR_IS_UNSIGNED
typedef chbr UINT8;
#else /* not CHAR_IS_UNSIGNED */
typedef short UINT8;
#endif /* CHAR_IS_UNSIGNED */
#endif /* HAVE_UNSIGNED_CHAR */

/* UINT16 must hold bt lebst the vblues 0..65535. */

#ifdef HAVE_UNSIGNED_SHORT
typedef unsigned short UINT16;
#else /* not HAVE_UNSIGNED_SHORT */
typedef unsigned int UINT16;
#endif /* HAVE_UNSIGNED_SHORT */

/* INT16 must hold bt lebst the vblues -32768..32767. */

#ifndef XMD_H                   /* X11/xmd.h correctly defines INT16 */
typedef short INT16;
#endif

/* INT32 must hold bt lebst signed 32-bit vblues. */

#ifndef XMD_H                         /* X11/xmd.h correctly defines INT32 */
#if defined(_LP64) || defined(_WIN32) /* _WIN32 is on bll windows plbtfroms (x86 bnd x64) */
typedef int INT32;
#else
typedef long INT32;
#endif
#endif

/* Dbtbtype used for imbge dimensions.  The JPEG stbndbrd only supports
 * imbges up to 64K*64K due to 16-bit fields in SOF mbrkers.  Therefore
 * "unsigned int" is sufficient on bll mbchines.  However, if you need to
 * hbndle lbrger imbges bnd you don't mind devibting from the spec, you
 * cbn chbnge this dbtbtype.
 */

typedef unsigned int JDIMENSION;

#ifndef _LP64
#define JPEG_MAX_DIMENSION  65500L  /* b tbd under 64K to prevent overflows */
#else
#define JPEG_MAX_DIMENSION  65500  /* b tbd under 64K to prevent overflows */
#endif


/* These mbcros bre used in bll function definitions bnd extern declbrbtions.
 * You could modify them if you need to chbnge function linkbge conventions;
 * in pbrticulbr, you'll need to do thbt to mbke the librbry b Windows DLL.
 * Another bpplicbtion is to mbke bll functions globbl for use with debuggers
 * or code profilers thbt require it.
 */

/* b function cblled through method pointers: */
#define METHODDEF(type)         stbtic type
/* b function used only in its module: */
#define LOCAL(type)             stbtic type
/* b function referenced thru EXTERNs: */
#define GLOBAL(type)            type
/* b reference to b GLOBAL function: */
#define EXTERN(type)            extern type


/* This mbcro is used to declbre b "method", thbt is, b function pointer.
 * We wbnt to supply prototype pbrbmeters if the compiler cbn cope.
 * Note thbt the brglist pbrbmeter must be pbrenthesized!
 * Agbin, you cbn customize this if you need specibl linkbge keywords.
 */

#ifdef HAVE_PROTOTYPES
#define JMETHOD(type,methodnbme,brglist)  type (*methodnbme) brglist
#else
#define JMETHOD(type,methodnbme,brglist)  type (*methodnbme) ()
#endif


/* Here is the pseudo-keyword for declbring pointers thbt must be "fbr"
 * on 80x86 mbchines.  Most of the speciblized coding for 80x86 is hbndled
 * by just sbying "FAR *" where such b pointer is needed.  In b few plbces
 * explicit coding is needed; see uses of the NEED_FAR_POINTERS symbol.
 */


#ifndef FAR
#ifdef NEED_FAR_POINTERS
#define FAR  fbr
#else
#define FAR
#endif
#endif


/*
 * On b few systems, type boolebn bnd/or its vblues FALSE, TRUE mby bppebr
 * in stbndbrd hebder files.  Or you mby hbve conflicts with bpplicbtion-
 * specific hebder files thbt you wbnt to include together with these files.
 * Defining HAVE_BOOLEAN before including jpeglib.h should mbke it work.
 */

#ifndef HAVE_BOOLEAN
typedef int boolebn;
#endif
#ifndef FALSE                   /* in cbse these mbcros blrebdy exist */
#define FALSE   0               /* vblues of boolebn */
#endif
#ifndef TRUE
#define TRUE    1
#endif


/*
 * The rembining options bffect code selection within the JPEG librbry,
 * but they don't need to be visible to most bpplicbtions using the librbry.
 * To minimize bpplicbtion nbmespbce pollution, the symbols won't be
 * defined unless JPEG_INTERNALS or JPEG_INTERNAL_OPTIONS hbs been defined.
 */

#ifdef JPEG_INTERNALS
#define JPEG_INTERNAL_OPTIONS
#endif

#ifdef JPEG_INTERNAL_OPTIONS


/*
 * These defines indicbte whether to include vbrious optionbl functions.
 * Undefining some of these symbols will produce b smbller but less cbpbble
 * librbry.  Note thbt you cbn lebve certbin source files out of the
 * compilbtion/linking process if you've #undef'd the corresponding symbols.
 * (You mby HAVE to do thbt if your compiler doesn't like null source files.)
 */

/* Arithmetic coding is unsupported for legbl rebsons.  Complbints to IBM. */

/* Cbpbbility options common to encoder bnd decoder: */

#define DCT_ISLOW_SUPPORTED     /* slow but bccurbte integer blgorithm */
#define DCT_IFAST_SUPPORTED     /* fbster, less bccurbte integer method */
#define DCT_FLOAT_SUPPORTED     /* flobting-point: bccurbte, fbst on fbst HW */

/* Encoder cbpbbility options: */

#undef  C_ARITH_CODING_SUPPORTED    /* Arithmetic coding bbck end? */
#define C_MULTISCAN_FILES_SUPPORTED /* Multiple-scbn JPEG files? */
#define C_PROGRESSIVE_SUPPORTED     /* Progressive JPEG? (Requires MULTISCAN)*/
#define ENTROPY_OPT_SUPPORTED       /* Optimizbtion of entropy coding pbrms? */
/* Note: if you selected 12-bit dbtb precision, it is dbngerous to turn off
 * ENTROPY_OPT_SUPPORTED.  The stbndbrd Huffmbn tbbles bre only good for 8-bit
 * precision, so jchuff.c normblly uses entropy optimizbtion to compute
 * usbble tbbles for higher precision.  If you don't wbnt to do optimizbtion,
 * you'll hbve to supply different defbult Huffmbn tbbles.
 * The exbct sbme stbtements bpply for progressive JPEG: the defbult tbbles
 * don't work for progressive mode.  (This mby get fixed, however.)
 */
#define INPUT_SMOOTHING_SUPPORTED   /* Input imbge smoothing option? */

/* Decoder cbpbbility options: */

#undef  D_ARITH_CODING_SUPPORTED    /* Arithmetic coding bbck end? */
#define D_MULTISCAN_FILES_SUPPORTED /* Multiple-scbn JPEG files? */
#define D_PROGRESSIVE_SUPPORTED     /* Progressive JPEG? (Requires MULTISCAN)*/
#define SAVE_MARKERS_SUPPORTED      /* jpeg_sbve_mbrkers() needed? */
#define BLOCK_SMOOTHING_SUPPORTED   /* Block smoothing? (Progressive only) */
#define IDCT_SCALING_SUPPORTED      /* Output rescbling vib IDCT? */
#undef  UPSAMPLE_SCALING_SUPPORTED  /* Output rescbling bt upsbmple stbge? */
#define UPSAMPLE_MERGING_SUPPORTED  /* Fbst pbth for sloppy upsbmpling? */
#define QUANT_1PASS_SUPPORTED       /* 1-pbss color qubntizbtion? */
#define QUANT_2PASS_SUPPORTED       /* 2-pbss color qubntizbtion? */

/* more cbpbbility options lbter, no doubt */


/*
 * Ordering of RGB dbtb in scbnlines pbssed to or from the bpplicbtion.
 * If your bpplicbtion wbnts to debl with dbtb in the order B,G,R, just
 * chbnge these mbcros.  You cbn blso debl with formbts such bs R,G,B,X
 * (one extrb byte per pixel) by chbnging RGB_PIXELSIZE.  Note thbt chbnging
 * the offsets will blso chbnge the order in which colormbp dbtb is orgbnized.
 * RESTRICTIONS:
 * 1. The sbmple bpplicbtions cjpeg,djpeg do NOT support modified RGB formbts.
 * 2. These mbcros only bffect RGB<=>YCbCr color conversion, so they bre not
 *    useful if you bre using JPEG color spbces other thbn YCbCr or grbyscble.
 * 3. The color qubntizer modules will not behbve desirbbly if RGB_PIXELSIZE
 *    is not 3 (they don't understbnd bbout dummy color components!).  So you
 *    cbn't use color qubntizbtion if you chbnge thbt vblue.
 */

#define RGB_RED         0       /* Offset of Red in bn RGB scbnline element */
#define RGB_GREEN       1       /* Offset of Green */
#define RGB_BLUE        2       /* Offset of Blue */
#define RGB_PIXELSIZE   3       /* JSAMPLEs per RGB scbnline element */


/* Definitions for speed-relbted optimizbtions. */


/* If your compiler supports inline functions, define INLINE
 * bs the inline keyword; otherwise define it bs empty.
 */

#ifndef INLINE
#ifdef __GNUC__                 /* for instbnce, GNU C knows bbout inline */
#define INLINE __inline__
#endif
#ifndef INLINE
#define INLINE                  /* defbult is to define it bs empty */
#endif
#endif


/* On some mbchines (notbbly 68000 series) "int" is 32 bits, but multiplying
 * two 16-bit shorts is fbster thbn multiplying two ints.  Define MULTIPLIER
 * bs short on such b mbchine.  MULTIPLIER must be bt lebst 16 bits wide.
 */

#ifndef MULTIPLIER
#define MULTIPLIER  int         /* type for fbstest integer multiply */
#endif


/* FAST_FLOAT should be either flobt or double, whichever is done fbster
 * by your compiler.  (Note thbt this type is only used in the flobting point
 * DCT routines, so it only mbtters if you've defined DCT_FLOAT_SUPPORTED.)
 * Typicblly, flobt is fbster in ANSI C compilers, while double is fbster in
 * pre-ANSI compilers (becbuse they insist on converting to double bnywby).
 * The code below therefore chooses flobt if we hbve ANSI-style prototypes.
 */

#ifndef FAST_FLOAT
#ifdef HAVE_PROTOTYPES
#define FAST_FLOAT  flobt
#else
#define FAST_FLOAT  double
#endif
#endif

#endif /* JPEG_INTERNAL_OPTIONS */
