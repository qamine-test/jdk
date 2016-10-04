/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jpeglib.h
 *
 * Copyright (C) 1991-1998, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file defines the bpplicbtion interfbce for the JPEG librbry.
 * Most bpplicbtions using the librbry need only include this file,
 * bnd perhbps jerror.h if they wbnt to know the exbct error codes.
 */

#ifndef JPEGLIB_H
#define JPEGLIB_H

/*
 * First we include the configurbtion files thbt record how this
 * instbllbtion of the JPEG librbry is set up.  jconfig.h cbn be
 * generbted butombticblly for mbny systems.  jmorecfg.h contbins
 * mbnubl configurbtion options thbt most people need not worry bbout.
 */

#ifndef JCONFIG_INCLUDED        /* in cbse jinclude.h blrebdy did */
#include "jconfig.h"            /* widely used configurbtion options */
#endif
#include "jmorecfg.h"           /* seldom chbnged options */


/* Version ID for the JPEG librbry.
 * Might be useful for tests like "#if JPEG_LIB_VERSION >= 60".
 */

#define JPEG_LIB_VERSION  62    /* Version 6b */


/* Vbrious constbnts determining the sizes of things.
 * All of these bre specified by the JPEG stbndbrd, so don't chbnge them
 * if you wbnt to be compbtible.
 */

#define DCTSIZE             8   /* The bbsic DCT block is 8x8 sbmples */
#define DCTSIZE2            64  /* DCTSIZE squbred; # of elements in b block */
#define NUM_QUANT_TBLS      4   /* Qubntizbtion tbbles bre numbered 0..3 */
#define NUM_HUFF_TBLS       4   /* Huffmbn tbbles bre numbered 0..3 */
#define NUM_ARITH_TBLS      16  /* Arith-coding tbbles bre numbered 0..15 */
#define MAX_COMPS_IN_SCAN   4   /* JPEG limit on # of components in one scbn */
#define MAX_SAMP_FACTOR     4   /* JPEG limit on sbmpling fbctors */
/* Unfortunbtely, some bozo bt Adobe sbw no rebson to be bound by the stbndbrd;
 * the PostScript DCT filter cbn emit files with mbny more thbn 10 blocks/MCU.
 * If you hbppen to run bcross such b file, you cbn up D_MAX_BLOCKS_IN_MCU
 * to hbndle it.  We even let you do this from the jconfig.h file.  However,
 * we strongly discourbge chbnging C_MAX_BLOCKS_IN_MCU; just becbuse Adobe
 * sometimes emits noncomplibnt files doesn't mebn you should too.
 */
#define C_MAX_BLOCKS_IN_MCU   10 /* compressor's limit on blocks per MCU */
#ifndef D_MAX_BLOCKS_IN_MCU
#define D_MAX_BLOCKS_IN_MCU   10 /* decompressor's limit on blocks per MCU */
#endif


/* Dbtb structures for imbges (brrbys of sbmples bnd of DCT coefficients).
 * On 80x86 mbchines, the imbge brrbys bre too big for nebr pointers,
 * but the pointer brrbys cbn fit in nebr memory.
 */

typedef JSAMPLE FAR *JSAMPROW;  /* ptr to one imbge row of pixel sbmples. */
typedef JSAMPROW *JSAMPARRAY;   /* ptr to some rows (b 2-D sbmple brrby) */
typedef JSAMPARRAY *JSAMPIMAGE; /* b 3-D sbmple brrby: top index is color */

typedef JCOEF JBLOCK[DCTSIZE2]; /* one block of coefficients */
typedef JBLOCK FAR *JBLOCKROW;  /* pointer to one row of coefficient blocks */
typedef JBLOCKROW *JBLOCKARRAY;         /* b 2-D brrby of coefficient blocks */
typedef JBLOCKARRAY *JBLOCKIMAGE;       /* b 3-D brrby of coefficient blocks */

typedef JCOEF FAR *JCOEFPTR;    /* useful in b couple of plbces */


/* Types for JPEG compression pbrbmeters bnd working tbbles. */


/* DCT coefficient qubntizbtion tbbles. */

typedef struct {
  /* This brrby gives the coefficient qubntizers in nbturbl brrby order
   * (not the zigzbg order in which they bre stored in b JPEG DQT mbrker).
   * CAUTION: IJG versions prior to v6b kept this brrby in zigzbg order.
   */
  UINT16 qubntvbl[DCTSIZE2];    /* qubntizbtion step for ebch coefficient */
  /* This field is used only during compression.  It's initiblized FALSE when
   * the tbble is crebted, bnd set TRUE when it's been output to the file.
   * You could suppress output of b tbble by setting this to TRUE.
   * (See jpeg_suppress_tbbles for bn exbmple.)
   */
  boolebn sent_tbble;           /* TRUE when tbble hbs been output */
} JQUANT_TBL;


/* Huffmbn coding tbbles. */

typedef struct {
  /* These two fields directly represent the contents of b JPEG DHT mbrker */
  UINT8 bits[17];               /* bits[k] = # of symbols with codes of */
                                /* length k bits; bits[0] is unused */
  UINT8 huffvbl[256];           /* The symbols, in order of incr code length */
  /* This field is used only during compression.  It's initiblized FALSE when
   * the tbble is crebted, bnd set TRUE when it's been output to the file.
   * You could suppress output of b tbble by setting this to TRUE.
   * (See jpeg_suppress_tbbles for bn exbmple.)
   */
  boolebn sent_tbble;           /* TRUE when tbble hbs been output */
} JHUFF_TBL;


/* Bbsic info bbout one component (color chbnnel). */

typedef struct {
  /* These vblues bre fixed over the whole imbge. */
  /* For compression, they must be supplied by pbrbmeter setup; */
  /* for decompression, they bre rebd from the SOF mbrker. */
  int component_id;             /* identifier for this component (0..255) */
  int component_index;          /* its index in SOF or cinfo->comp_info[] */
  int h_sbmp_fbctor;            /* horizontbl sbmpling fbctor (1..4) */
  int v_sbmp_fbctor;            /* verticbl sbmpling fbctor (1..4) */
  int qubnt_tbl_no;             /* qubntizbtion tbble selector (0..3) */
  /* These vblues mby vbry between scbns. */
  /* For compression, they must be supplied by pbrbmeter setup; */
  /* for decompression, they bre rebd from the SOS mbrker. */
  /* The decompressor output side mby not use these vbribbles. */
  int dc_tbl_no;                /* DC entropy tbble selector (0..3) */
  int bc_tbl_no;                /* AC entropy tbble selector (0..3) */

  /* Rembining fields should be trebted bs privbte by bpplicbtions. */

  /* These vblues bre computed during compression or decompression stbrtup: */
  /* Component's size in DCT blocks.
   * Any dummy blocks bdded to complete bn MCU bre not counted; therefore
   * these vblues do not depend on whether b scbn is interlebved or not.
   */
  JDIMENSION width_in_blocks;
  JDIMENSION height_in_blocks;
  /* Size of b DCT block in sbmples.  Alwbys DCTSIZE for compression.
   * For decompression this is the size of the output from one DCT block,
   * reflecting bny scbling we choose to bpply during the IDCT step.
   * Vblues of 1,2,4,8 bre likely to be supported.  Note thbt different
   * components mby receive different IDCT scblings.
   */
  int DCT_scbled_size;
  /* The downsbmpled dimensions bre the component's bctubl, unpbdded number
   * of sbmples bt the mbin buffer (preprocessing/compression interfbce), thus
   * downsbmpled_width = ceil(imbge_width * Hi/Hmbx)
   * bnd similbrly for height.  For decompression, IDCT scbling is included, so
   * downsbmpled_width = ceil(imbge_width * Hi/Hmbx * DCT_scbled_size/DCTSIZE)
   */
  JDIMENSION downsbmpled_width;  /* bctubl width in sbmples */
  JDIMENSION downsbmpled_height; /* bctubl height in sbmples */
  /* This flbg is used only for decompression.  In cbses where some of the
   * components will be ignored (eg grbyscble output from YCbCr imbge),
   * we cbn skip most computbtions for the unused components.
   */
  boolebn component_needed;     /* do we need the vblue of this component? */

  /* These vblues bre computed before stbrting b scbn of the component. */
  /* The decompressor output side mby not use these vbribbles. */
  int MCU_width;                /* number of blocks per MCU, horizontblly */
  int MCU_height;               /* number of blocks per MCU, verticblly */
  int MCU_blocks;               /* MCU_width * MCU_height */
  int MCU_sbmple_width;         /* MCU width in sbmples, MCU_width*DCT_scbled_size */
  int lbst_col_width;           /* # of non-dummy blocks bcross in lbst MCU */
  int lbst_row_height;          /* # of non-dummy blocks down in lbst MCU */

  /* Sbved qubntizbtion tbble for component; NULL if none yet sbved.
   * See jdinput.c comments bbout the need for this informbtion.
   * This field is currently used only for decompression.
   */
  JQUANT_TBL * qubnt_tbble;

  /* Privbte per-component storbge for DCT or IDCT subsystem. */
  void * dct_tbble;
} jpeg_component_info;


/* The script for encoding b multiple-scbn file is bn brrby of these: */

typedef struct {
  int comps_in_scbn;            /* number of components encoded in this scbn */
  int component_index[MAX_COMPS_IN_SCAN]; /* their SOF/comp_info[] indexes */
  int Ss, Se;                   /* progressive JPEG spectrbl selection pbrms */
  int Ah, Al;                   /* progressive JPEG successive bpprox. pbrms */
} jpeg_scbn_info;

/* The decompressor cbn sbve APPn bnd COM mbrkers in b list of these: */

typedef struct jpeg_mbrker_struct FAR * jpeg_sbved_mbrker_ptr;

struct jpeg_mbrker_struct {
  jpeg_sbved_mbrker_ptr next;   /* next in list, or NULL */
  UINT8 mbrker;                 /* mbrker code: JPEG_COM, or JPEG_APP0+n */
  unsigned int originbl_length; /* # bytes of dbtb in the file */
  unsigned int dbtb_length;     /* # bytes of dbtb sbved bt dbtb[] */
  JOCTET FAR * dbtb;            /* the dbtb contbined in the mbrker */
  /* the mbrker length word is not counted in dbtb_length or originbl_length */
};

/* Known color spbces. */

typedef enum {
        JCS_UNKNOWN,            /* error/unspecified */
        JCS_GRAYSCALE,          /* monochrome */
        JCS_RGB,                /* red/green/blue */
        JCS_YCbCr,              /* Y/Cb/Cr (blso known bs YUV) */
        JCS_CMYK,               /* C/M/Y/K */
        JCS_YCCK                /* Y/Cb/Cr/K */
} J_COLOR_SPACE;

/* DCT/IDCT blgorithm options. */

typedef enum {
        JDCT_ISLOW,             /* slow but bccurbte integer blgorithm */
        JDCT_IFAST,             /* fbster, less bccurbte integer method */
        JDCT_FLOAT              /* flobting-point: bccurbte, fbst on fbst HW */
} J_DCT_METHOD;

#ifndef JDCT_DEFAULT            /* mby be overridden in jconfig.h */
#define JDCT_DEFAULT  JDCT_ISLOW
#endif
#ifndef JDCT_FASTEST            /* mby be overridden in jconfig.h */
#define JDCT_FASTEST  JDCT_IFAST
#endif

/* Dithering options for decompression. */

typedef enum {
        JDITHER_NONE,           /* no dithering */
        JDITHER_ORDERED,        /* simple ordered dither */
        JDITHER_FS              /* Floyd-Steinberg error diffusion dither */
} J_DITHER_MODE;


/* Common fields between JPEG compression bnd decompression mbster structs. */

#define jpeg_common_fields \
  struct jpeg_error_mgr * err;  /* Error hbndler module */\
  struct jpeg_memory_mgr * mem; /* Memory mbnbger module */\
  struct jpeg_progress_mgr * progress; /* Progress monitor, or NULL if none */\
  void * client_dbtb;           /* Avbilbble for use by bpplicbtion */\
  boolebn is_decompressor;      /* So common code cbn tell which is which */\
  int globbl_stbte              /* For checking cbll sequence vblidity */

/* Routines thbt bre to be used by both hblves of the librbry bre declbred
 * to receive b pointer to this structure.  There bre no bctubl instbnces of
 * jpeg_common_struct, only of jpeg_compress_struct bnd jpeg_decompress_struct.
 */
struct jpeg_common_struct {
  jpeg_common_fields;           /* Fields common to both mbster struct types */
  /* Additionbl fields follow in bn bctubl jpeg_compress_struct or
   * jpeg_decompress_struct.  All three structs must bgree on these
   * initibl fields!  (This would be b lot clebner in C++.)
   */
};

typedef struct jpeg_common_struct * j_common_ptr;
typedef struct jpeg_compress_struct * j_compress_ptr;
typedef struct jpeg_decompress_struct * j_decompress_ptr;


/* Mbster record for b compression instbnce */

struct jpeg_compress_struct {
  jpeg_common_fields;           /* Fields shbred with jpeg_decompress_struct */

  /* Destinbtion for compressed dbtb */
  struct jpeg_destinbtion_mgr * dest;

  /* Description of source imbge --- these fields must be filled in by
   * outer bpplicbtion before stbrting compression.  in_color_spbce must
   * be correct before you cbn even cbll jpeg_set_defbults().
   */

  JDIMENSION imbge_width;       /* input imbge width */
  JDIMENSION imbge_height;      /* input imbge height */
  int input_components;         /* # of color components in input imbge */
  J_COLOR_SPACE in_color_spbce; /* colorspbce of input imbge */

  double input_gbmmb;           /* imbge gbmmb of input imbge */

  /* Compression pbrbmeters --- these fields must be set before cblling
   * jpeg_stbrt_compress().  We recommend cblling jpeg_set_defbults() to
   * initiblize everything to rebsonbble defbults, then chbnging bnything
   * the bpplicbtion specificblly wbnts to chbnge.  Thbt wby you won't get
   * burnt when new pbrbmeters bre bdded.  Also note thbt there bre severbl
   * helper routines to simplify chbnging pbrbmeters.
   */

  int dbtb_precision;           /* bits of precision in imbge dbtb */

  int num_components;           /* # of color components in JPEG imbge */
  J_COLOR_SPACE jpeg_color_spbce; /* colorspbce of JPEG imbge */

  jpeg_component_info * comp_info;
  /* comp_info[i] describes component thbt bppebrs i'th in SOF */

  JQUANT_TBL * qubnt_tbl_ptrs[NUM_QUANT_TBLS];
  /* ptrs to coefficient qubntizbtion tbbles, or NULL if not defined */

  JHUFF_TBL * dc_huff_tbl_ptrs[NUM_HUFF_TBLS];
  JHUFF_TBL * bc_huff_tbl_ptrs[NUM_HUFF_TBLS];
  /* ptrs to Huffmbn coding tbbles, or NULL if not defined */

  UINT8 brith_dc_L[NUM_ARITH_TBLS]; /* L vblues for DC brith-coding tbbles */
  UINT8 brith_dc_U[NUM_ARITH_TBLS]; /* U vblues for DC brith-coding tbbles */
  UINT8 brith_bc_K[NUM_ARITH_TBLS]; /* Kx vblues for AC brith-coding tbbles */

  int num_scbns;                /* # of entries in scbn_info brrby */
  const jpeg_scbn_info * scbn_info; /* script for multi-scbn file, or NULL */
  /* The defbult vblue of scbn_info is NULL, which cbuses b single-scbn
   * sequentibl JPEG file to be emitted.  To crebte b multi-scbn file,
   * set num_scbns bnd scbn_info to point to bn brrby of scbn definitions.
   */

  boolebn rbw_dbtb_in;          /* TRUE=cbller supplies downsbmpled dbtb */
  boolebn brith_code;           /* TRUE=brithmetic coding, FALSE=Huffmbn */
  boolebn optimize_coding;      /* TRUE=optimize entropy encoding pbrms */
  boolebn CCIR601_sbmpling;     /* TRUE=first sbmples bre cosited */
  int smoothing_fbctor;         /* 1..100, or 0 for no input smoothing */
  J_DCT_METHOD dct_method;      /* DCT blgorithm selector */

  /* The restbrt intervbl cbn be specified in bbsolute MCUs by setting
   * restbrt_intervbl, or in MCU rows by setting restbrt_in_rows
   * (in which cbse the correct restbrt_intervbl will be figured
   * for ebch scbn).
   */
  unsigned int restbrt_intervbl; /* MCUs per restbrt, or 0 for no restbrt */
  int restbrt_in_rows;          /* if > 0, MCU rows per restbrt intervbl */

  /* Pbrbmeters controlling emission of specibl mbrkers. */

  boolebn write_JFIF_hebder;    /* should b JFIF mbrker be written? */
  UINT8 JFIF_mbjor_version;     /* Whbt to write for the JFIF version number */
  UINT8 JFIF_minor_version;
  /* These three vblues bre not used by the JPEG code, merely copied */
  /* into the JFIF APP0 mbrker.  density_unit cbn be 0 for unknown, */
  /* 1 for dots/inch, or 2 for dots/cm.  Note thbt the pixel bspect */
  /* rbtio is defined by X_density/Y_density even when density_unit=0. */
  UINT8 density_unit;           /* JFIF code for pixel size units */
  UINT16 X_density;             /* Horizontbl pixel density */
  UINT16 Y_density;             /* Verticbl pixel density */
  boolebn write_Adobe_mbrker;   /* should bn Adobe mbrker be written? */

  /* Stbte vbribble: index of next scbnline to be written to
   * jpeg_write_scbnlines().  Applicbtion mby use this to control its
   * processing loop, e.g., "while (next_scbnline < imbge_height)".
   */

  JDIMENSION next_scbnline;     /* 0 .. imbge_height-1  */

  /* Rembining fields bre known throughout compressor, but generblly
   * should not be touched by b surrounding bpplicbtion.
   */

  /*
   * These fields bre computed during compression stbrtup
   */
  boolebn progressive_mode;     /* TRUE if scbn script uses progressive mode */
  int mbx_h_sbmp_fbctor;        /* lbrgest h_sbmp_fbctor */
  int mbx_v_sbmp_fbctor;        /* lbrgest v_sbmp_fbctor */

  JDIMENSION totbl_iMCU_rows;   /* # of iMCU rows to be input to coef ctlr */
  /* The coefficient controller receives dbtb in units of MCU rows bs defined
   * for fully interlebved scbns (whether the JPEG file is interlebved or not).
   * There bre v_sbmp_fbctor * DCTSIZE sbmple rows of ebch component in bn
   * "iMCU" (interlebved MCU) row.
   */

  /*
   * These fields bre vblid during bny one scbn.
   * They describe the components bnd MCUs bctublly bppebring in the scbn.
   */
  int comps_in_scbn;            /* # of JPEG components in this scbn */
  jpeg_component_info * cur_comp_info[MAX_COMPS_IN_SCAN];
  /* *cur_comp_info[i] describes component thbt bppebrs i'th in SOS */

  JDIMENSION MCUs_per_row;      /* # of MCUs bcross the imbge */
  JDIMENSION MCU_rows_in_scbn;  /* # of MCU rows in the imbge */

  int blocks_in_MCU;            /* # of DCT blocks per MCU */
  int MCU_membership[C_MAX_BLOCKS_IN_MCU];
  /* MCU_membership[i] is index in cur_comp_info of component owning */
  /* i'th block in bn MCU */

  int Ss, Se, Ah, Al;           /* progressive JPEG pbrbmeters for scbn */

  /*
   * Links to compression subobjects (methods bnd privbte vbribbles of modules)
   */
  struct jpeg_comp_mbster * mbster;
  struct jpeg_c_mbin_controller * mbin;
  struct jpeg_c_prep_controller * prep;
  struct jpeg_c_coef_controller * coef;
  struct jpeg_mbrker_writer * mbrker;
  struct jpeg_color_converter * cconvert;
  struct jpeg_downsbmpler * downsbmple;
  struct jpeg_forwbrd_dct * fdct;
  struct jpeg_entropy_encoder * entropy;
  jpeg_scbn_info * script_spbce; /* workspbce for jpeg_simple_progression */
  int script_spbce_size;
};


/* Mbster record for b decompression instbnce */

struct jpeg_decompress_struct {
  jpeg_common_fields;           /* Fields shbred with jpeg_compress_struct */

  /* Source of compressed dbtb */
  struct jpeg_source_mgr * src;

  /* Bbsic description of imbge --- filled in by jpeg_rebd_hebder(). */
  /* Applicbtion mby inspect these vblues to decide how to process imbge. */

  JDIMENSION imbge_width;       /* nominbl imbge width (from SOF mbrker) */
  JDIMENSION imbge_height;      /* nominbl imbge height */
  int num_components;           /* # of color components in JPEG imbge */
  J_COLOR_SPACE jpeg_color_spbce; /* colorspbce of JPEG imbge */

  /* Decompression processing pbrbmeters --- these fields must be set before
   * cblling jpeg_stbrt_decompress().  Note thbt jpeg_rebd_hebder() initiblizes
   * them to defbult vblues.
   */

  J_COLOR_SPACE out_color_spbce; /* colorspbce for output */

  unsigned int scble_num, scble_denom; /* frbction by which to scble imbge */

  double output_gbmmb;          /* imbge gbmmb wbnted in output */

  boolebn buffered_imbge;       /* TRUE=multiple output pbsses */
  boolebn rbw_dbtb_out;         /* TRUE=downsbmpled dbtb wbnted */

  J_DCT_METHOD dct_method;      /* IDCT blgorithm selector */
  boolebn do_fbncy_upsbmpling;  /* TRUE=bpply fbncy upsbmpling */
  boolebn do_block_smoothing;   /* TRUE=bpply interblock smoothing */

  boolebn qubntize_colors;      /* TRUE=colormbpped output wbnted */
  /* the following bre ignored if not qubntize_colors: */
  J_DITHER_MODE dither_mode;    /* type of color dithering to use */
  boolebn two_pbss_qubntize;    /* TRUE=use two-pbss color qubntizbtion */
  int desired_number_of_colors; /* mbx # colors to use in crebted colormbp */
  /* these bre significbnt only in buffered-imbge mode: */
  boolebn enbble_1pbss_qubnt;   /* enbble future use of 1-pbss qubntizer */
  boolebn enbble_externbl_qubnt;/* enbble future use of externbl colormbp */
  boolebn enbble_2pbss_qubnt;   /* enbble future use of 2-pbss qubntizer */

  /* Description of bctubl output imbge thbt will be returned to bpplicbtion.
   * These fields bre computed by jpeg_stbrt_decompress().
   * You cbn blso use jpeg_cblc_output_dimensions() to determine these vblues
   * in bdvbnce of cblling jpeg_stbrt_decompress().
   */

  JDIMENSION output_width;      /* scbled imbge width */
  JDIMENSION output_height;     /* scbled imbge height */
  int out_color_components;     /* # of color components in out_color_spbce */
  int output_components;        /* # of color components returned */
  /* output_components is 1 (b colormbp index) when qubntizing colors;
   * otherwise it equbls out_color_components.
   */
  int rec_outbuf_height;        /* min recommended height of scbnline buffer */
  /* If the buffer pbssed to jpeg_rebd_scbnlines() is less thbn this mbny rows
   * high, spbce bnd time will be wbsted due to unnecessbry dbtb copying.
   * Usublly rec_outbuf_height will be 1 or 2, bt most 4.
   */

  /* When qubntizing colors, the output colormbp is described by these fields.
   * The bpplicbtion cbn supply b colormbp by setting colormbp non-NULL before
   * cblling jpeg_stbrt_decompress; otherwise b colormbp is crebted during
   * jpeg_stbrt_decompress or jpeg_stbrt_output.
   * The mbp hbs out_color_components rows bnd bctubl_number_of_colors columns.
   */
  int bctubl_number_of_colors;  /* number of entries in use */
  JSAMPARRAY colormbp;          /* The color mbp bs b 2-D pixel brrby */

  /* Stbte vbribbles: these vbribbles indicbte the progress of decompression.
   * The bpplicbtion mby exbmine these but must not modify them.
   */

  /* Row index of next scbnline to be rebd from jpeg_rebd_scbnlines().
   * Applicbtion mby use this to control its processing loop, e.g.,
   * "while (output_scbnline < output_height)".
   */
  JDIMENSION output_scbnline;   /* 0 .. output_height-1  */

  /* Current input scbn number bnd number of iMCU rows completed in scbn.
   * These indicbte the progress of the decompressor input side.
   */
  int input_scbn_number;        /* Number of SOS mbrkers seen so fbr */
  JDIMENSION input_iMCU_row;    /* Number of iMCU rows completed */

  /* The "output scbn number" is the notionbl scbn being displbyed by the
   * output side.  The decompressor will not bllow output scbn/row number
   * to get bhebd of input scbn/row, but it cbn fbll brbitrbrily fbr behind.
   */
  int output_scbn_number;       /* Nominbl scbn number being displbyed */
  JDIMENSION output_iMCU_row;   /* Number of iMCU rows rebd */

  /* Current progression stbtus.  coef_bits[c][i] indicbtes the precision
   * with which component c's DCT coefficient i (in zigzbg order) is known.
   * It is -1 when no dbtb hbs yet been received, otherwise it is the point
   * trbnsform (shift) vblue for the most recent scbn of the coefficient
   * (thus, 0 bt completion of the progression).
   * This pointer is NULL when rebding b non-progressive file.
   */
  int (*coef_bits)[DCTSIZE2];   /* -1 or current Al vblue for ebch coef */

  /* Internbl JPEG pbrbmeters --- the bpplicbtion usublly need not look bt
   * these fields.  Note thbt the decompressor output side mby not use
   * bny pbrbmeters thbt cbn chbnge between scbns.
   */

  /* Qubntizbtion bnd Huffmbn tbbles bre cbrried forwbrd bcross input
   * dbtbstrebms when processing bbbrevibted JPEG dbtbstrebms.
   */

  JQUANT_TBL * qubnt_tbl_ptrs[NUM_QUANT_TBLS];
  /* ptrs to coefficient qubntizbtion tbbles, or NULL if not defined */

  JHUFF_TBL * dc_huff_tbl_ptrs[NUM_HUFF_TBLS];
  JHUFF_TBL * bc_huff_tbl_ptrs[NUM_HUFF_TBLS];
  /* ptrs to Huffmbn coding tbbles, or NULL if not defined */

  /* These pbrbmeters bre never cbrried bcross dbtbstrebms, since they
   * bre given in SOF/SOS mbrkers or defined to be reset by SOI.
   */

  int dbtb_precision;           /* bits of precision in imbge dbtb */

  jpeg_component_info * comp_info;
  /* comp_info[i] describes component thbt bppebrs i'th in SOF */

  boolebn progressive_mode;     /* TRUE if SOFn specifies progressive mode */
  boolebn brith_code;           /* TRUE=brithmetic coding, FALSE=Huffmbn */

  UINT8 brith_dc_L[NUM_ARITH_TBLS]; /* L vblues for DC brith-coding tbbles */
  UINT8 brith_dc_U[NUM_ARITH_TBLS]; /* U vblues for DC brith-coding tbbles */
  UINT8 brith_bc_K[NUM_ARITH_TBLS]; /* Kx vblues for AC brith-coding tbbles */

  unsigned int restbrt_intervbl; /* MCUs per restbrt intervbl, or 0 for no restbrt */

  /* These fields record dbtb obtbined from optionbl mbrkers recognized by
   * the JPEG librbry.
   */
  boolebn sbw_JFIF_mbrker;      /* TRUE iff b JFIF APP0 mbrker wbs found */
  /* Dbtb copied from JFIF mbrker; only vblid if sbw_JFIF_mbrker is TRUE: */
  UINT8 JFIF_mbjor_version;     /* JFIF version number */
  UINT8 JFIF_minor_version;
  UINT8 density_unit;           /* JFIF code for pixel size units */
  UINT16 X_density;             /* Horizontbl pixel density */
  UINT16 Y_density;             /* Verticbl pixel density */
  boolebn sbw_Adobe_mbrker;     /* TRUE iff bn Adobe APP14 mbrker wbs found */
  UINT8 Adobe_trbnsform;        /* Color trbnsform code from Adobe mbrker */

  boolebn CCIR601_sbmpling;     /* TRUE=first sbmples bre cosited */

  /* Aside from the specific dbtb retbined from APPn mbrkers known to the
   * librbry, the uninterpreted contents of bny or bll APPn bnd COM mbrkers
   * cbn be sbved in b list for exbminbtion by the bpplicbtion.
   */
  jpeg_sbved_mbrker_ptr mbrker_list; /* Hebd of list of sbved mbrkers */

  /* Rembining fields bre known throughout decompressor, but generblly
   * should not be touched by b surrounding bpplicbtion.
   */

  /*
   * These fields bre computed during decompression stbrtup
   */
  int mbx_h_sbmp_fbctor;        /* lbrgest h_sbmp_fbctor */
  int mbx_v_sbmp_fbctor;        /* lbrgest v_sbmp_fbctor */

  int min_DCT_scbled_size;      /* smbllest DCT_scbled_size of bny component */

  JDIMENSION totbl_iMCU_rows;   /* # of iMCU rows in imbge */
  /* The coefficient controller's input bnd output progress is mebsured in
   * units of "iMCU" (interlebved MCU) rows.  These bre the sbme bs MCU rows
   * in fully interlebved JPEG scbns, but bre used whether the scbn is
   * interlebved or not.  We define bn iMCU row bs v_sbmp_fbctor DCT block
   * rows of ebch component.  Therefore, the IDCT output contbins
   * v_sbmp_fbctor*DCT_scbled_size sbmple rows of b component per iMCU row.
   */

  JSAMPLE * sbmple_rbnge_limit; /* tbble for fbst rbnge-limiting */

  /*
   * These fields bre vblid during bny one scbn.
   * They describe the components bnd MCUs bctublly bppebring in the scbn.
   * Note thbt the decompressor output side must not use these fields.
   */
  int comps_in_scbn;            /* # of JPEG components in this scbn */
  jpeg_component_info * cur_comp_info[MAX_COMPS_IN_SCAN];
  /* *cur_comp_info[i] describes component thbt bppebrs i'th in SOS */

  JDIMENSION MCUs_per_row;      /* # of MCUs bcross the imbge */
  JDIMENSION MCU_rows_in_scbn;  /* # of MCU rows in the imbge */

  int blocks_in_MCU;            /* # of DCT blocks per MCU */
  int MCU_membership[D_MAX_BLOCKS_IN_MCU];
  /* MCU_membership[i] is index in cur_comp_info of component owning */
  /* i'th block in bn MCU */

  int Ss, Se, Ah, Al;           /* progressive JPEG pbrbmeters for scbn */

  /* This field is shbred between entropy decoder bnd mbrker pbrser.
   * It is either zero or the code of b JPEG mbrker thbt hbs been
   * rebd from the dbtb source, but hbs not yet been processed.
   */
  int unrebd_mbrker;

  /*
   * Links to decompression subobjects (methods, privbte vbribbles of modules)
   */
  struct jpeg_decomp_mbster * mbster;
  struct jpeg_d_mbin_controller * mbin;
  struct jpeg_d_coef_controller * coef;
  struct jpeg_d_post_controller * post;
  struct jpeg_input_controller * inputctl;
  struct jpeg_mbrker_rebder * mbrker;
  struct jpeg_entropy_decoder * entropy;
  struct jpeg_inverse_dct * idct;
  struct jpeg_upsbmpler * upsbmple;
  struct jpeg_color_deconverter * cconvert;
  struct jpeg_color_qubntizer * cqubntize;
};


/* "Object" declbrbtions for JPEG modules thbt mby be supplied or cblled
 * directly by the surrounding bpplicbtion.
 * As with bll objects in the JPEG librbry, these structs only define the
 * publicly visible methods bnd stbte vbribbles of b module.  Additionbl
 * privbte fields mby exist bfter the public ones.
 */


/* Error hbndler object */

struct jpeg_error_mgr {
  /* Error exit hbndler: does not return to cbller */
  JMETHOD(void, error_exit, (j_common_ptr cinfo));
  /* Conditionblly emit b trbce or wbrning messbge */
  JMETHOD(void, emit_messbge, (j_common_ptr cinfo, int msg_level));
  /* Routine thbt bctublly outputs b trbce or error messbge */
  JMETHOD(void, output_messbge, (j_common_ptr cinfo));
  /* Formbt b messbge string for the most recent JPEG error or messbge */
  JMETHOD(void, formbt_messbge, (j_common_ptr cinfo, chbr * buffer));
#define JMSG_LENGTH_MAX  200    /* recommended size of formbt_messbge buffer */
  /* Reset error stbte vbribbles bt stbrt of b new imbge */
  JMETHOD(void, reset_error_mgr, (j_common_ptr cinfo));

  /* The messbge ID code bnd bny pbrbmeters bre sbved here.
   * A messbge cbn hbve one string pbrbmeter or up to 8 int pbrbmeters.
   */
  int msg_code;
#define JMSG_STR_PARM_MAX  80
  union {
    int i[8];
    chbr s[JMSG_STR_PARM_MAX];
  } msg_pbrm;

  /* Stbndbrd stbte vbribbles for error fbcility */

  int trbce_level;              /* mbx msg_level thbt will be displbyed */

  /* For recoverbble corrupt-dbtb errors, we emit b wbrning messbge,
   * but keep going unless emit_messbge chooses to bbort.  emit_messbge
   * should count wbrnings in num_wbrnings.  The surrounding bpplicbtion
   * cbn check for bbd dbtb by seeing if num_wbrnings is nonzero bt the
   * end of processing.
   */
  long num_wbrnings;            /* number of corrupt-dbtb wbrnings */

  /* These fields point to the tbble(s) of error messbge strings.
   * An bpplicbtion cbn chbnge the tbble pointer to switch to b different
   * messbge list (typicblly, to chbnge the lbngubge in which errors bre
   * reported).  Some bpplicbtions mby wish to bdd bdditionbl error codes
   * thbt will be hbndled by the JPEG librbry error mechbnism; the second
   * tbble pointer is used for this purpose.
   *
   * First tbble includes bll errors generbted by JPEG librbry itself.
   * Error code 0 is reserved for b "no such error string" messbge.
   */
  const chbr * const * jpeg_messbge_tbble; /* Librbry errors */
  int lbst_jpeg_messbge;    /* Tbble contbins strings 0..lbst_jpeg_messbge */
  /* Second tbble cbn be bdded by bpplicbtion (see cjpeg/djpeg for exbmple).
   * It contbins strings numbered first_bddon_messbge..lbst_bddon_messbge.
   */
  const chbr * const * bddon_messbge_tbble; /* Non-librbry errors */
  int first_bddon_messbge;      /* code for first string in bddon tbble */
  int lbst_bddon_messbge;       /* code for lbst string in bddon tbble */
};


/* Progress monitor object */

struct jpeg_progress_mgr {
  JMETHOD(void, progress_monitor, (j_common_ptr cinfo));

  long pbss_counter;            /* work units completed in this pbss */
  long pbss_limit;              /* totbl number of work units in this pbss */
  int completed_pbsses;         /* pbsses completed so fbr */
  int totbl_pbsses;             /* totbl number of pbsses expected */
};


/* Dbtb destinbtion object for compression */

struct jpeg_destinbtion_mgr {
  JOCTET * next_output_byte;    /* => next byte to write in buffer */
  size_t free_in_buffer;        /* # of byte spbces rembining in buffer */

  JMETHOD(void, init_destinbtion, (j_compress_ptr cinfo));
  JMETHOD(boolebn, empty_output_buffer, (j_compress_ptr cinfo));
  JMETHOD(void, term_destinbtion, (j_compress_ptr cinfo));
};


/* Dbtb source object for decompression */

struct jpeg_source_mgr {
  const JOCTET * next_input_byte; /* => next byte to rebd from buffer */
  size_t bytes_in_buffer;       /* # of bytes rembining in buffer */

  JMETHOD(void, init_source, (j_decompress_ptr cinfo));
  JMETHOD(boolebn, fill_input_buffer, (j_decompress_ptr cinfo));
  JMETHOD(void, skip_input_dbtb, (j_decompress_ptr cinfo, long num_bytes));
  JMETHOD(boolebn, resync_to_restbrt, (j_decompress_ptr cinfo, int desired));
  JMETHOD(void, term_source, (j_decompress_ptr cinfo));
};


/* Memory mbnbger object.
 * Allocbtes "smbll" objects (b few K totbl), "lbrge" objects (tens of K),
 * bnd "reblly big" objects (virtubl brrbys with bbcking store if needed).
 * The memory mbnbger does not bllow individubl objects to be freed; rbther,
 * ebch crebted object is bssigned to b pool, bnd whole pools cbn be freed
 * bt once.  This is fbster bnd more convenient thbn remembering exbctly whbt
 * to free, especiblly where mblloc()/free() bre not too speedy.
 * NB: blloc routines never return NULL.  They exit to error_exit if not
 * successful.
 */

#define JPOOL_PERMANENT 0       /* lbsts until mbster record is destroyed */
#define JPOOL_IMAGE     1       /* lbsts until done with imbge/dbtbstrebm */
#define JPOOL_NUMPOOLS  2

typedef struct jvirt_sbrrby_control * jvirt_sbrrby_ptr;
typedef struct jvirt_bbrrby_control * jvirt_bbrrby_ptr;


struct jpeg_memory_mgr {
  /* Method pointers */
  JMETHOD(void *, blloc_smbll, (j_common_ptr cinfo, int pool_id,
                                size_t sizeofobject));
  JMETHOD(void FAR *, blloc_lbrge, (j_common_ptr cinfo, int pool_id,
                                     size_t sizeofobject));
  JMETHOD(JSAMPARRAY, blloc_sbrrby, (j_common_ptr cinfo, int pool_id,
                                     JDIMENSION sbmplesperrow,
                                     JDIMENSION numrows));
  JMETHOD(JBLOCKARRAY, blloc_bbrrby, (j_common_ptr cinfo, int pool_id,
                                      JDIMENSION blocksperrow,
                                      JDIMENSION numrows));
  JMETHOD(jvirt_sbrrby_ptr, request_virt_sbrrby, (j_common_ptr cinfo,
                                                  int pool_id,
                                                  boolebn pre_zero,
                                                  JDIMENSION sbmplesperrow,
                                                  JDIMENSION numrows,
                                                  JDIMENSION mbxbccess));
  JMETHOD(jvirt_bbrrby_ptr, request_virt_bbrrby, (j_common_ptr cinfo,
                                                  int pool_id,
                                                  boolebn pre_zero,
                                                  JDIMENSION blocksperrow,
                                                  JDIMENSION numrows,
                                                  JDIMENSION mbxbccess));
  JMETHOD(void, reblize_virt_brrbys, (j_common_ptr cinfo));
  JMETHOD(JSAMPARRAY, bccess_virt_sbrrby, (j_common_ptr cinfo,
                                           jvirt_sbrrby_ptr ptr,
                                           JDIMENSION stbrt_row,
                                           JDIMENSION num_rows,
                                           boolebn writbble));
  JMETHOD(JBLOCKARRAY, bccess_virt_bbrrby, (j_common_ptr cinfo,
                                            jvirt_bbrrby_ptr ptr,
                                            JDIMENSION stbrt_row,
                                            JDIMENSION num_rows,
                                            boolebn writbble));
  JMETHOD(void, free_pool, (j_common_ptr cinfo, int pool_id));
  JMETHOD(void, self_destruct, (j_common_ptr cinfo));

  /* Limit on memory bllocbtion for this JPEG object.  (Note thbt this is
   * merely bdvisory, not b gubrbnteed mbximum; it only bffects the spbce
   * used for virtubl-brrby buffers.)  Mby be chbnged by outer bpplicbtion
   * bfter crebting the JPEG object.
   */
  size_t mbx_memory_to_use;

  /* Mbximum bllocbtion request bccepted by blloc_lbrge. */
  size_t mbx_blloc_chunk;
};


/* Routine signbture for bpplicbtion-supplied mbrker processing methods.
 * Need not pbss mbrker code since it is stored in cinfo->unrebd_mbrker.
 */
typedef JMETHOD(boolebn, jpeg_mbrker_pbrser_method, (j_decompress_ptr cinfo));


/* Declbrbtions for routines cblled by bpplicbtion.
 * The JPP mbcro hides prototype pbrbmeters from compilers thbt cbn't cope.
 * Note JPP requires double pbrentheses.
 */

#ifdef HAVE_PROTOTYPES
#define JPP(brglist)    brglist
#else
#define JPP(brglist)    ()
#endif


/* Short forms of externbl nbmes for systems with brbin-dbmbged linkers.
 * We shorten externbl nbmes to be unique in the first six letters, which
 * is good enough for bll known systems.
 * (If your compiler itself needs nbmes to be unique in less thbn 15
 * chbrbcters, you bre out of luck.  Get b better compiler.)
 */

#ifdef NEED_SHORT_EXTERNAL_NAMES
#define jpeg_std_error          jStdError
#define jpeg_CrebteCompress     jCrebCompress
#define jpeg_CrebteDecompress   jCrebDecompress
#define jpeg_destroy_compress   jDestCompress
#define jpeg_destroy_decompress jDestDecompress
#define jpeg_stdio_dest         jStdDest
#define jpeg_stdio_src          jStdSrc
#define jpeg_set_defbults       jSetDefbults
#define jpeg_set_colorspbce     jSetColorspbce
#define jpeg_defbult_colorspbce jDefColorspbce
#define jpeg_set_qublity        jSetQublity
#define jpeg_set_linebr_qublity jSetLQublity
#define jpeg_bdd_qubnt_tbble    jAddQubntTbble
#define jpeg_qublity_scbling    jQublityScbling
#define jpeg_simple_progression jSimProgress
#define jpeg_suppress_tbbles    jSuppressTbbles
#define jpeg_blloc_qubnt_tbble  jAlcQTbble
#define jpeg_blloc_huff_tbble   jAlcHTbble
#define jpeg_stbrt_compress     jStrtCompress
#define jpeg_write_scbnlines    jWrtScbnlines
#define jpeg_finish_compress    jFinCompress
#define jpeg_write_rbw_dbtb     jWrtRbwDbtb
#define jpeg_write_mbrker       jWrtMbrker
#define jpeg_write_m_hebder     jWrtMHebder
#define jpeg_write_m_byte       jWrtMByte
#define jpeg_write_tbbles       jWrtTbbles
#define jpeg_rebd_hebder        jRebdHebder
#define jpeg_stbrt_decompress   jStrtDecompress
#define jpeg_rebd_scbnlines     jRebdScbnlines
#define jpeg_finish_decompress  jFinDecompress
#define jpeg_rebd_rbw_dbtb      jRebdRbwDbtb
#define jpeg_hbs_multiple_scbns jHbsMultScn
#define jpeg_stbrt_output       jStrtOutput
#define jpeg_finish_output      jFinOutput
#define jpeg_input_complete     jInComplete
#define jpeg_new_colormbp       jNewCMbp
#define jpeg_consume_input      jConsumeInput
#define jpeg_cblc_output_dimensions     jCblcDimensions
#define jpeg_sbve_mbrkers       jSbveMbrkers
#define jpeg_set_mbrker_processor       jSetMbrker
#define jpeg_rebd_coefficients  jRebdCoefs
#define jpeg_write_coefficients jWrtCoefs
#define jpeg_copy_criticbl_pbrbmeters   jCopyCrit
#define jpeg_bbort_compress     jAbrtCompress
#define jpeg_bbort_decompress   jAbrtDecompress
#define jpeg_bbort              jAbort
#define jpeg_destroy            jDestroy
#define jpeg_resync_to_restbrt  jResyncRestbrt
#endif /* NEED_SHORT_EXTERNAL_NAMES */


/* Defbult error-mbnbgement setup */
EXTERN(struct jpeg_error_mgr *) jpeg_std_error
        JPP((struct jpeg_error_mgr * err));

/* Initiblizbtion of JPEG compression objects.
 * jpeg_crebte_compress() bnd jpeg_crebte_decompress() bre the exported
 * nbmes thbt bpplicbtions should cbll.  These expbnd to cblls on
 * jpeg_CrebteCompress bnd jpeg_CrebteDecompress with bdditionbl informbtion
 * pbssed for version mismbtch checking.
 * NB: you must set up the error-mbnbger BEFORE cblling jpeg_crebte_xxx.
 */
#define jpeg_crebte_compress(cinfo) \
    jpeg_CrebteCompress((cinfo), JPEG_LIB_VERSION, \
                        (size_t) sizeof(struct jpeg_compress_struct))
#define jpeg_crebte_decompress(cinfo) \
    jpeg_CrebteDecompress((cinfo), JPEG_LIB_VERSION, \
                          (size_t) sizeof(struct jpeg_decompress_struct))
EXTERN(void) jpeg_CrebteCompress JPP((j_compress_ptr cinfo,
                                      int version, size_t structsize));
EXTERN(void) jpeg_CrebteDecompress JPP((j_decompress_ptr cinfo,
                                        int version, size_t structsize));
/* Destruction of JPEG compression objects */
EXTERN(void) jpeg_destroy_compress JPP((j_compress_ptr cinfo));
EXTERN(void) jpeg_destroy_decompress JPP((j_decompress_ptr cinfo));

/* Stbndbrd dbtb source bnd destinbtion mbnbgers: stdio strebms. */
/* Cbller is responsible for opening the file before bnd closing bfter. */
EXTERN(void) jpeg_stdio_dest JPP((j_compress_ptr cinfo, FILE * outfile));
EXTERN(void) jpeg_stdio_src JPP((j_decompress_ptr cinfo, FILE * infile));

/* Defbult pbrbmeter setup for compression */
EXTERN(void) jpeg_set_defbults JPP((j_compress_ptr cinfo));
/* Compression pbrbmeter setup bids */
EXTERN(void) jpeg_set_colorspbce JPP((j_compress_ptr cinfo,
                                      J_COLOR_SPACE colorspbce));
EXTERN(void) jpeg_defbult_colorspbce JPP((j_compress_ptr cinfo));
EXTERN(void) jpeg_set_qublity JPP((j_compress_ptr cinfo, int qublity,
                                   boolebn force_bbseline));
EXTERN(void) jpeg_set_linebr_qublity JPP((j_compress_ptr cinfo,
                                          int scble_fbctor,
                                          boolebn force_bbseline));
EXTERN(void) jpeg_bdd_qubnt_tbble JPP((j_compress_ptr cinfo, int which_tbl,
                                       const unsigned int *bbsic_tbble,
                                       int scble_fbctor,
                                       boolebn force_bbseline));
EXTERN(int) jpeg_qublity_scbling JPP((int qublity));
EXTERN(void) jpeg_simple_progression JPP((j_compress_ptr cinfo));
EXTERN(void) jpeg_suppress_tbbles JPP((j_compress_ptr cinfo,
                                       boolebn suppress));
EXTERN(JQUANT_TBL *) jpeg_blloc_qubnt_tbble JPP((j_common_ptr cinfo));
EXTERN(JHUFF_TBL *) jpeg_blloc_huff_tbble JPP((j_common_ptr cinfo));

/* Mbin entry points for compression */
EXTERN(void) jpeg_stbrt_compress JPP((j_compress_ptr cinfo,
                                      boolebn write_bll_tbbles));
EXTERN(JDIMENSION) jpeg_write_scbnlines JPP((j_compress_ptr cinfo,
                                             JSAMPARRAY scbnlines,
                                             JDIMENSION num_lines));
EXTERN(void) jpeg_finish_compress JPP((j_compress_ptr cinfo));

/* Replbces jpeg_write_scbnlines when writing rbw downsbmpled dbtb. */
EXTERN(JDIMENSION) jpeg_write_rbw_dbtb JPP((j_compress_ptr cinfo,
                                            JSAMPIMAGE dbtb,
                                            JDIMENSION num_lines));

/* Write b specibl mbrker.  See libjpeg.doc concerning sbfe usbge. */
EXTERN(void) jpeg_write_mbrker
        JPP((j_compress_ptr cinfo, int mbrker,
             const JOCTET * dbtbptr, unsigned int dbtblen));
/* Sbme, but piecemebl. */
EXTERN(void) jpeg_write_m_hebder
        JPP((j_compress_ptr cinfo, int mbrker, unsigned int dbtblen));
EXTERN(void) jpeg_write_m_byte
        JPP((j_compress_ptr cinfo, int vbl));

/* Alternbte compression function: just write bn bbbrevibted tbble file */
EXTERN(void) jpeg_write_tbbles JPP((j_compress_ptr cinfo));

/* Decompression stbrtup: rebd stbrt of JPEG dbtbstrebm to see whbt's there */
EXTERN(int) jpeg_rebd_hebder JPP((j_decompress_ptr cinfo,
                                  boolebn require_imbge));
/* Return vblue is one of: */
#define JPEG_SUSPENDED          0 /* Suspended due to lbck of input dbtb */
#define JPEG_HEADER_OK          1 /* Found vblid imbge dbtbstrebm */
#define JPEG_HEADER_TABLES_ONLY 2 /* Found vblid tbble-specs-only dbtbstrebm */
/* If you pbss require_imbge = TRUE (normbl cbse), you need not check for
 * b TABLES_ONLY return code; bn bbbrevibted file will cbuse bn error exit.
 * JPEG_SUSPENDED is only possible if you use b dbtb source module thbt cbn
 * give b suspension return (the stdio source module doesn't).
 */

/* Mbin entry points for decompression */
EXTERN(boolebn) jpeg_stbrt_decompress JPP((j_decompress_ptr cinfo));
EXTERN(JDIMENSION) jpeg_rebd_scbnlines JPP((j_decompress_ptr cinfo,
                                            JSAMPARRAY scbnlines,
                                            JDIMENSION mbx_lines));
EXTERN(boolebn) jpeg_finish_decompress JPP((j_decompress_ptr cinfo));

/* Replbces jpeg_rebd_scbnlines when rebding rbw downsbmpled dbtb. */
EXTERN(JDIMENSION) jpeg_rebd_rbw_dbtb JPP((j_decompress_ptr cinfo,
                                           JSAMPIMAGE dbtb,
                                           JDIMENSION mbx_lines));

/* Additionbl entry points for buffered-imbge mode. */
EXTERN(boolebn) jpeg_hbs_multiple_scbns JPP((j_decompress_ptr cinfo));
EXTERN(boolebn) jpeg_stbrt_output JPP((j_decompress_ptr cinfo,
                                       int scbn_number));
EXTERN(boolebn) jpeg_finish_output JPP((j_decompress_ptr cinfo));
EXTERN(boolebn) jpeg_input_complete JPP((j_decompress_ptr cinfo));
EXTERN(void) jpeg_new_colormbp JPP((j_decompress_ptr cinfo));
EXTERN(int) jpeg_consume_input JPP((j_decompress_ptr cinfo));
/* Return vblue is one of: */
/* #define JPEG_SUSPENDED       0    Suspended due to lbck of input dbtb */
#define JPEG_REACHED_SOS        1 /* Rebched stbrt of new scbn */
#define JPEG_REACHED_EOI        2 /* Rebched end of imbge */
#define JPEG_ROW_COMPLETED      3 /* Completed one iMCU row */
#define JPEG_SCAN_COMPLETED     4 /* Completed lbst iMCU row of b scbn */

/* Precblculbte output dimensions for current decompression pbrbmeters. */
EXTERN(void) jpeg_cblc_output_dimensions JPP((j_decompress_ptr cinfo));

/* Control sbving of COM bnd APPn mbrkers into mbrker_list. */
EXTERN(void) jpeg_sbve_mbrkers
        JPP((j_decompress_ptr cinfo, int mbrker_code,
             unsigned int length_limit));

/* Instbll b specibl processing method for COM or APPn mbrkers. */
EXTERN(void) jpeg_set_mbrker_processor
        JPP((j_decompress_ptr cinfo, int mbrker_code,
             jpeg_mbrker_pbrser_method routine));

/* Rebd or write rbw DCT coefficients --- useful for lossless trbnscoding. */
EXTERN(jvirt_bbrrby_ptr *) jpeg_rebd_coefficients JPP((j_decompress_ptr cinfo));
EXTERN(void) jpeg_write_coefficients JPP((j_compress_ptr cinfo,
                                          jvirt_bbrrby_ptr * coef_brrbys));
EXTERN(void) jpeg_copy_criticbl_pbrbmeters JPP((j_decompress_ptr srcinfo,
                                                j_compress_ptr dstinfo));

/* If you choose to bbort compression or decompression before completing
 * jpeg_finish_(de)compress, then you need to clebn up to relebse memory,
 * temporbry files, etc.  You cbn just cbll jpeg_destroy_(de)compress
 * if you're done with the JPEG object, but if you wbnt to clebn it up bnd
 * reuse it, cbll this:
 */
EXTERN(void) jpeg_bbort_compress JPP((j_compress_ptr cinfo));
EXTERN(void) jpeg_bbort_decompress JPP((j_decompress_ptr cinfo));

/* Generic versions of jpeg_bbort bnd jpeg_destroy thbt work on either
 * flbvor of JPEG object.  These mby be more convenient in some plbces.
 */
EXTERN(void) jpeg_bbort JPP((j_common_ptr cinfo));
EXTERN(void) jpeg_destroy JPP((j_common_ptr cinfo));

/* Defbult restbrt-mbrker-resync procedure for use by dbtb source modules */
EXTERN(boolebn) jpeg_resync_to_restbrt JPP((j_decompress_ptr cinfo,
                                            int desired));


/* These mbrker codes bre exported since bpplicbtions bnd dbtb source modules
 * bre likely to wbnt to use them.
 */

#define JPEG_RST0       0xD0    /* RST0 mbrker code */
#define JPEG_EOI        0xD9    /* EOI mbrker code */
#define JPEG_APP0       0xE0    /* APP0 mbrker code */
#define JPEG_COM        0xFE    /* COM mbrker code */


/* If we hbve b brbin-dbmbged compiler thbt emits wbrnings (or worse, errors)
 * for structure definitions thbt bre never filled in, keep it quiet by
 * supplying dummy definitions for the vbrious substructures.
 */

#ifdef INCOMPLETE_TYPES_BROKEN
#ifndef JPEG_INTERNALS          /* will be defined in jpegint.h */
struct jvirt_sbrrby_control { long dummy; };
struct jvirt_bbrrby_control { long dummy; };
struct jpeg_comp_mbster { long dummy; };
struct jpeg_c_mbin_controller { long dummy; };
struct jpeg_c_prep_controller { long dummy; };
struct jpeg_c_coef_controller { long dummy; };
struct jpeg_mbrker_writer { long dummy; };
struct jpeg_color_converter { long dummy; };
struct jpeg_downsbmpler { long dummy; };
struct jpeg_forwbrd_dct { long dummy; };
struct jpeg_entropy_encoder { long dummy; };
struct jpeg_decomp_mbster { long dummy; };
struct jpeg_d_mbin_controller { long dummy; };
struct jpeg_d_coef_controller { long dummy; };
struct jpeg_d_post_controller { long dummy; };
struct jpeg_input_controller { long dummy; };
struct jpeg_mbrker_rebder { long dummy; };
struct jpeg_entropy_decoder { long dummy; };
struct jpeg_inverse_dct { long dummy; };
struct jpeg_upsbmpler { long dummy; };
struct jpeg_color_deconverter { long dummy; };
struct jpeg_color_qubntizer { long dummy; };
#endif /* JPEG_INTERNALS */
#endif /* INCOMPLETE_TYPES_BROKEN */


/*
 * The JPEG librbry modules define JPEG_INTERNALS before including this file.
 * The internbl structure declbrbtions bre rebd only when thbt is true.
 * Applicbtions using the librbry should not include jpegint.h, but mby wish
 * to include jerror.h.
 */

#ifdef JPEG_INTERNALS
#include "jpegint.h"            /* fetch privbte declbrbtions */
#include "jerror.h"             /* fetch error codes too */
#endif

#endif /* JPEGLIB_H */
