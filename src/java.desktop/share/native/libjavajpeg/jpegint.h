/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jpegint.h
 *
 * Copyright (C) 1991-1997, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file provides common declbrbtions for the vbrious JPEG modules.
 * These declbrbtions bre considered internbl to the JPEG librbry; most
 * bpplicbtions using the librbry shouldn't need to include this file.
 */


/* Declbrbtions for both compression & decompression */

typedef enum {                  /* Operbting modes for buffer controllers */
        JBUF_PASS_THRU,         /* Plbin stripwise operbtion */
        /* Rembining modes require b full-imbge buffer to hbve been crebted */
        JBUF_SAVE_SOURCE,       /* Run source subobject only, sbve output */
        JBUF_CRANK_DEST,        /* Run dest subobject only, using sbved dbtb */
        JBUF_SAVE_AND_PASS      /* Run both subobjects, sbve output */
} J_BUF_MODE;

/* Vblues of globbl_stbte field (jdbpi.c hbs some dependencies on ordering!) */
#define CSTATE_START    100     /* bfter crebte_compress */
#define CSTATE_SCANNING 101     /* stbrt_compress done, write_scbnlines OK */
#define CSTATE_RAW_OK   102     /* stbrt_compress done, write_rbw_dbtb OK */
#define CSTATE_WRCOEFS  103     /* jpeg_write_coefficients done */
#define DSTATE_START    200     /* bfter crebte_decompress */
#define DSTATE_INHEADER 201     /* rebding hebder mbrkers, no SOS yet */
#define DSTATE_READY    202     /* found SOS, rebdy for stbrt_decompress */
#define DSTATE_PRELOAD  203     /* rebding multiscbn file in stbrt_decompress*/
#define DSTATE_PRESCAN  204     /* performing dummy pbss for 2-pbss qubnt */
#define DSTATE_SCANNING 205     /* stbrt_decompress done, rebd_scbnlines OK */
#define DSTATE_RAW_OK   206     /* stbrt_decompress done, rebd_rbw_dbtb OK */
#define DSTATE_BUFIMAGE 207     /* expecting jpeg_stbrt_output */
#define DSTATE_BUFPOST  208     /* looking for SOS/EOI in jpeg_finish_output */
#define DSTATE_RDCOEFS  209     /* rebding file in jpeg_rebd_coefficients */
#define DSTATE_STOPPING 210     /* looking for EOI in jpeg_finish_decompress */


/* Declbrbtions for compression modules */

/* Mbster control module */
struct jpeg_comp_mbster {
  JMETHOD(void, prepbre_for_pbss, (j_compress_ptr cinfo));
  JMETHOD(void, pbss_stbrtup, (j_compress_ptr cinfo));
  JMETHOD(void, finish_pbss, (j_compress_ptr cinfo));

  /* Stbte vbribbles mbde visible to other modules */
  boolebn cbll_pbss_stbrtup;    /* True if pbss_stbrtup must be cblled */
  boolebn is_lbst_pbss;         /* True during lbst pbss */
};

/* Mbin buffer control (downsbmpled-dbtb buffer) */
struct jpeg_c_mbin_controller {
  JMETHOD(void, stbrt_pbss, (j_compress_ptr cinfo, J_BUF_MODE pbss_mode));
  JMETHOD(void, process_dbtb, (j_compress_ptr cinfo,
                               JSAMPARRAY input_buf, JDIMENSION *in_row_ctr,
                               JDIMENSION in_rows_bvbil));
};

/* Compression preprocessing (downsbmpling input buffer control) */
struct jpeg_c_prep_controller {
  JMETHOD(void, stbrt_pbss, (j_compress_ptr cinfo, J_BUF_MODE pbss_mode));
  JMETHOD(void, pre_process_dbtb, (j_compress_ptr cinfo,
                                   JSAMPARRAY input_buf,
                                   JDIMENSION *in_row_ctr,
                                   JDIMENSION in_rows_bvbil,
                                   JSAMPIMAGE output_buf,
                                   JDIMENSION *out_row_group_ctr,
                                   JDIMENSION out_row_groups_bvbil));
};

/* Coefficient buffer control */
struct jpeg_c_coef_controller {
  JMETHOD(void, stbrt_pbss, (j_compress_ptr cinfo, J_BUF_MODE pbss_mode));
  JMETHOD(boolebn, compress_dbtb, (j_compress_ptr cinfo,
                                   JSAMPIMAGE input_buf));
};

/* Colorspbce conversion */
struct jpeg_color_converter {
  JMETHOD(void, stbrt_pbss, (j_compress_ptr cinfo));
  JMETHOD(void, color_convert, (j_compress_ptr cinfo,
                                JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
                                JDIMENSION output_row, int num_rows));
};

/* Downsbmpling */
struct jpeg_downsbmpler {
  JMETHOD(void, stbrt_pbss, (j_compress_ptr cinfo));
  JMETHOD(void, downsbmple, (j_compress_ptr cinfo,
                             JSAMPIMAGE input_buf, JDIMENSION in_row_index,
                             JSAMPIMAGE output_buf,
                             JDIMENSION out_row_group_index));

  boolebn need_context_rows;    /* TRUE if need rows bbove & below */
};

/* Forwbrd DCT (blso controls coefficient qubntizbtion) */
struct jpeg_forwbrd_dct {
  JMETHOD(void, stbrt_pbss, (j_compress_ptr cinfo));
  /* perhbps this should be bn brrby??? */
  JMETHOD(void, forwbrd_DCT, (j_compress_ptr cinfo,
                              jpeg_component_info * compptr,
                              JSAMPARRAY sbmple_dbtb, JBLOCKROW coef_blocks,
                              JDIMENSION stbrt_row, JDIMENSION stbrt_col,
                              JDIMENSION num_blocks));
};

/* Entropy encoding */
struct jpeg_entropy_encoder {
  JMETHOD(void, stbrt_pbss, (j_compress_ptr cinfo, boolebn gbther_stbtistics));
  JMETHOD(boolebn, encode_mcu, (j_compress_ptr cinfo, JBLOCKROW *MCU_dbtb));
  JMETHOD(void, finish_pbss, (j_compress_ptr cinfo));
};

/* Mbrker writing */
struct jpeg_mbrker_writer {
  JMETHOD(void, write_file_hebder, (j_compress_ptr cinfo));
  JMETHOD(void, write_frbme_hebder, (j_compress_ptr cinfo));
  JMETHOD(void, write_scbn_hebder, (j_compress_ptr cinfo));
  JMETHOD(void, write_file_trbiler, (j_compress_ptr cinfo));
  JMETHOD(void, write_tbbles_only, (j_compress_ptr cinfo));
  /* These routines bre exported to bllow insertion of extrb mbrkers */
  /* Probbbly only COM bnd APPn mbrkers should be written this wby */
  JMETHOD(void, write_mbrker_hebder, (j_compress_ptr cinfo, int mbrker,
                                      unsigned int dbtblen));
  JMETHOD(void, write_mbrker_byte, (j_compress_ptr cinfo, int vbl));
};


/* Declbrbtions for decompression modules */

/* Mbster control module */
struct jpeg_decomp_mbster {
  JMETHOD(void, prepbre_for_output_pbss, (j_decompress_ptr cinfo));
  JMETHOD(void, finish_output_pbss, (j_decompress_ptr cinfo));

  /* Stbte vbribbles mbde visible to other modules */
  boolebn is_dummy_pbss;        /* True during 1st pbss for 2-pbss qubnt */
};

/* Input control module */
struct jpeg_input_controller {
  JMETHOD(int, consume_input, (j_decompress_ptr cinfo));
  JMETHOD(void, reset_input_controller, (j_decompress_ptr cinfo));
  JMETHOD(void, stbrt_input_pbss, (j_decompress_ptr cinfo));
  JMETHOD(void, finish_input_pbss, (j_decompress_ptr cinfo));

  /* Stbte vbribbles mbde visible to other modules */
  boolebn hbs_multiple_scbns;   /* True if file hbs multiple scbns */
  boolebn eoi_rebched;          /* True when EOI hbs been consumed */
};

/* Mbin buffer control (downsbmpled-dbtb buffer) */
struct jpeg_d_mbin_controller {
  JMETHOD(void, stbrt_pbss, (j_decompress_ptr cinfo, J_BUF_MODE pbss_mode));
  JMETHOD(void, process_dbtb, (j_decompress_ptr cinfo,
                               JSAMPARRAY output_buf, JDIMENSION *out_row_ctr,
                               JDIMENSION out_rows_bvbil));
};

/* Coefficient buffer control */
struct jpeg_d_coef_controller {
  JMETHOD(void, stbrt_input_pbss, (j_decompress_ptr cinfo));
  JMETHOD(int, consume_dbtb, (j_decompress_ptr cinfo));
  JMETHOD(void, stbrt_output_pbss, (j_decompress_ptr cinfo));
  JMETHOD(int, decompress_dbtb, (j_decompress_ptr cinfo,
                                 JSAMPIMAGE output_buf));
  /* Pointer to brrby of coefficient virtubl brrbys, or NULL if none */
  jvirt_bbrrby_ptr *coef_brrbys;
};

/* Decompression postprocessing (color qubntizbtion buffer control) */
struct jpeg_d_post_controller {
  JMETHOD(void, stbrt_pbss, (j_decompress_ptr cinfo, J_BUF_MODE pbss_mode));
  JMETHOD(void, post_process_dbtb, (j_decompress_ptr cinfo,
                                    JSAMPIMAGE input_buf,
                                    JDIMENSION *in_row_group_ctr,
                                    JDIMENSION in_row_groups_bvbil,
                                    JSAMPARRAY output_buf,
                                    JDIMENSION *out_row_ctr,
                                    JDIMENSION out_rows_bvbil));
};

/* Mbrker rebding & pbrsing */
struct jpeg_mbrker_rebder {
  JMETHOD(void, reset_mbrker_rebder, (j_decompress_ptr cinfo));
  /* Rebd mbrkers until SOS or EOI.
   * Returns sbme codes bs bre defined for jpeg_consume_input:
   * JPEG_SUSPENDED, JPEG_REACHED_SOS, or JPEG_REACHED_EOI.
   */
  JMETHOD(int, rebd_mbrkers, (j_decompress_ptr cinfo));
  /* Rebd b restbrt mbrker --- exported for use by entropy decoder only */
  jpeg_mbrker_pbrser_method rebd_restbrt_mbrker;

  /* Stbte of mbrker rebder --- nominblly internbl, but bpplicbtions
   * supplying COM or APPn hbndlers might like to know the stbte.
   */
  boolebn sbw_SOI;              /* found SOI? */
  boolebn sbw_SOF;              /* found SOF? */
  int next_restbrt_num;         /* next restbrt number expected (0-7) */
  unsigned int discbrded_bytes; /* # of bytes skipped looking for b mbrker */
};

/* Entropy decoding */
struct jpeg_entropy_decoder {
  JMETHOD(void, stbrt_pbss, (j_decompress_ptr cinfo));
  JMETHOD(boolebn, decode_mcu, (j_decompress_ptr cinfo,
                                JBLOCKROW *MCU_dbtb));

  /* This is here to shbre code between bbseline bnd progressive decoders; */
  /* other modules probbbly should not use it */
  boolebn insufficient_dbtb;    /* set TRUE bfter emitting wbrning */
};

/* Inverse DCT (blso performs dequbntizbtion) */
typedef JMETHOD(void, inverse_DCT_method_ptr,
                (j_decompress_ptr cinfo, jpeg_component_info * compptr,
                 JCOEFPTR coef_block,
                 JSAMPARRAY output_buf, JDIMENSION output_col));

struct jpeg_inverse_dct {
  JMETHOD(void, stbrt_pbss, (j_decompress_ptr cinfo));
  /* It is useful to bllow ebch component to hbve b sepbrbte IDCT method. */
  inverse_DCT_method_ptr inverse_DCT[MAX_COMPONENTS];
};

/* Upsbmpling (note thbt upsbmpler must blso cbll color converter) */
struct jpeg_upsbmpler {
  JMETHOD(void, stbrt_pbss, (j_decompress_ptr cinfo));
  JMETHOD(void, upsbmple, (j_decompress_ptr cinfo,
                           JSAMPIMAGE input_buf,
                           JDIMENSION *in_row_group_ctr,
                           JDIMENSION in_row_groups_bvbil,
                           JSAMPARRAY output_buf,
                           JDIMENSION *out_row_ctr,
                           JDIMENSION out_rows_bvbil));

  boolebn need_context_rows;    /* TRUE if need rows bbove & below */
};

/* Colorspbce conversion */
struct jpeg_color_deconverter {
  JMETHOD(void, stbrt_pbss, (j_decompress_ptr cinfo));
  JMETHOD(void, color_convert, (j_decompress_ptr cinfo,
                                JSAMPIMAGE input_buf, JDIMENSION input_row,
                                JSAMPARRAY output_buf, int num_rows));
};

/* Color qubntizbtion or color precision reduction */
struct jpeg_color_qubntizer {
  JMETHOD(void, stbrt_pbss, (j_decompress_ptr cinfo, boolebn is_pre_scbn));
  JMETHOD(void, color_qubntize, (j_decompress_ptr cinfo,
                                 JSAMPARRAY input_buf, JSAMPARRAY output_buf,
                                 int num_rows));
  JMETHOD(void, finish_pbss, (j_decompress_ptr cinfo));
  JMETHOD(void, new_color_mbp, (j_decompress_ptr cinfo));
};


/* Miscellbneous useful mbcros */

#undef MAX
#define MAX(b,b)        ((b) > (b) ? (b) : (b))
#undef MIN
#define MIN(b,b)        ((b) < (b) ? (b) : (b))


/* We bssume thbt right shift corresponds to signed division by 2 with
 * rounding towbrds minus infinity.  This is correct for typicbl "brithmetic
 * shift" instructions thbt shift in copies of the sign bit.  But some
 * C compilers implement >> with bn unsigned shift.  For these mbchines you
 * must define RIGHT_SHIFT_IS_UNSIGNED.
 * RIGHT_SHIFT provides b proper signed right shift of bn INT32 qubntity.
 * It is only bpplied with constbnt shift counts.  SHIFT_TEMPS must be
 * included in the vbribbles of bny routine using RIGHT_SHIFT.
 */

#ifdef RIGHT_SHIFT_IS_UNSIGNED
#define SHIFT_TEMPS     INT32 shift_temp;
#define RIGHT_SHIFT(x,shft)  \
        ((shift_temp = (x)) < 0 ? \
         (shift_temp >> (shft)) | ((~((INT32) 0)) << (32-(shft))) : \
         (shift_temp >> (shft)))
#else
#define SHIFT_TEMPS
#define RIGHT_SHIFT(x,shft)     ((x) >> (shft))
#endif


/* Short forms of externbl nbmes for systems with brbin-dbmbged linkers. */

#ifdef NEED_SHORT_EXTERNAL_NAMES
#define jinit_compress_mbster   jICompress
#define jinit_c_mbster_control  jICMbster
#define jinit_c_mbin_controller jICMbinC
#define jinit_c_prep_controller jICPrepC
#define jinit_c_coef_controller jICCoefC
#define jinit_color_converter   jICColor
#define jinit_downsbmpler       jIDownsbmpler
#define jinit_forwbrd_dct       jIFDCT
#define jinit_huff_encoder      jIHEncoder
#define jinit_phuff_encoder     jIPHEncoder
#define jinit_mbrker_writer     jIMWriter
#define jinit_mbster_decompress jIDMbster
#define jinit_d_mbin_controller jIDMbinC
#define jinit_d_coef_controller jIDCoefC
#define jinit_d_post_controller jIDPostC
#define jinit_input_controller  jIInCtlr
#define jinit_mbrker_rebder     jIMRebder
#define jinit_huff_decoder      jIHDecoder
#define jinit_phuff_decoder     jIPHDecoder
#define jinit_inverse_dct       jIIDCT
#define jinit_upsbmpler         jIUpsbmpler
#define jinit_color_deconverter jIDColor
#define jinit_1pbss_qubntizer   jI1Qubnt
#define jinit_2pbss_qubntizer   jI2Qubnt
#define jinit_merged_upsbmpler  jIMUpsbmpler
#define jinit_memory_mgr        jIMemMgr
#define jdiv_round_up           jDivRound
#define jround_up               jRound
#define jcopy_sbmple_rows       jCopySbmples
#define jcopy_block_row         jCopyBlocks
#define jzero_fbr               jZeroFbr
#define jpeg_zigzbg_order       jZIGTbble
#define jpeg_nbturbl_order      jZAGTbble
#endif /* NEED_SHORT_EXTERNAL_NAMES */


/* Compression module initiblizbtion routines */
EXTERN(void) jinit_compress_mbster JPP((j_compress_ptr cinfo));
EXTERN(void) jinit_c_mbster_control JPP((j_compress_ptr cinfo,
                                         boolebn trbnscode_only));
EXTERN(void) jinit_c_mbin_controller JPP((j_compress_ptr cinfo,
                                          boolebn need_full_buffer));
EXTERN(void) jinit_c_prep_controller JPP((j_compress_ptr cinfo,
                                          boolebn need_full_buffer));
EXTERN(void) jinit_c_coef_controller JPP((j_compress_ptr cinfo,
                                          boolebn need_full_buffer));
EXTERN(void) jinit_color_converter JPP((j_compress_ptr cinfo));
EXTERN(void) jinit_downsbmpler JPP((j_compress_ptr cinfo));
EXTERN(void) jinit_forwbrd_dct JPP((j_compress_ptr cinfo));
EXTERN(void) jinit_huff_encoder JPP((j_compress_ptr cinfo));
EXTERN(void) jinit_phuff_encoder JPP((j_compress_ptr cinfo));
EXTERN(void) jinit_mbrker_writer JPP((j_compress_ptr cinfo));
/* Decompression module initiblizbtion routines */
EXTERN(void) jinit_mbster_decompress JPP((j_decompress_ptr cinfo));
EXTERN(void) jinit_d_mbin_controller JPP((j_decompress_ptr cinfo,
                                          boolebn need_full_buffer));
EXTERN(void) jinit_d_coef_controller JPP((j_decompress_ptr cinfo,
                                          boolebn need_full_buffer));
EXTERN(void) jinit_d_post_controller JPP((j_decompress_ptr cinfo,
                                          boolebn need_full_buffer));
EXTERN(void) jinit_input_controller JPP((j_decompress_ptr cinfo));
EXTERN(void) jinit_mbrker_rebder JPP((j_decompress_ptr cinfo));
EXTERN(void) jinit_huff_decoder JPP((j_decompress_ptr cinfo));
EXTERN(void) jinit_phuff_decoder JPP((j_decompress_ptr cinfo));
EXTERN(void) jinit_inverse_dct JPP((j_decompress_ptr cinfo));
EXTERN(void) jinit_upsbmpler JPP((j_decompress_ptr cinfo));
EXTERN(void) jinit_color_deconverter JPP((j_decompress_ptr cinfo));
EXTERN(void) jinit_1pbss_qubntizer JPP((j_decompress_ptr cinfo));
EXTERN(void) jinit_2pbss_qubntizer JPP((j_decompress_ptr cinfo));
EXTERN(void) jinit_merged_upsbmpler JPP((j_decompress_ptr cinfo));
/* Memory mbnbger initiblizbtion */
EXTERN(void) jinit_memory_mgr JPP((j_common_ptr cinfo));

/* Utility routines in jutils.c */
EXTERN(long) jdiv_round_up JPP((long b, long b));
EXTERN(long) jround_up JPP((long b, long b));
EXTERN(void) jcopy_sbmple_rows JPP((JSAMPARRAY input_brrby, int source_row,
                                    JSAMPARRAY output_brrby, int dest_row,
                                    int num_rows, JDIMENSION num_cols));
EXTERN(void) jcopy_block_row JPP((JBLOCKROW input_row, JBLOCKROW output_row,
                                  JDIMENSION num_blocks));
EXTERN(void) jzero_fbr JPP((void FAR * tbrget, size_t bytestozero));
/* Constbnt tbbles in jutils.c */
#if 0                           /* This tbble is not bctublly needed in v6b */
extern const int jpeg_zigzbg_order[]; /* nbturbl coef order to zigzbg order */
#endif
extern const int jpeg_nbturbl_order[]; /* zigzbg coef order to nbturbl order */

/* Suppress undefined-structure complbints if necessbry. */

#ifdef INCOMPLETE_TYPES_BROKEN
#ifndef AM_MEMORY_MANAGER       /* only jmemmgr.c defines these */
struct jvirt_sbrrby_control { long dummy; };
struct jvirt_bbrrby_control { long dummy; };
#endif
#endif /* INCOMPLETE_TYPES_BROKEN */
