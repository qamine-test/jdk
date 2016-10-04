/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdcoefct.c
 *
 * Copyright (C) 1994-1997, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins the coefficient buffer controller for decompression.
 * This controller is the top level of the JPEG decompressor proper.
 * The coefficient buffer lies between entropy decoding bnd inverse-DCT steps.
 *
 * In buffered-imbge mode, this controller is the interfbce between
 * input-oriented processing bnd output-oriented processing.
 * Also, the input side (only) is used when rebding b file for trbnscoding.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"

/* Block smoothing is only bpplicbble for progressive JPEG, so: */
#ifndef D_PROGRESSIVE_SUPPORTED
#undef BLOCK_SMOOTHING_SUPPORTED
#endif

/* Privbte buffer controller object */

typedef struct {
  struct jpeg_d_coef_controller pub; /* public fields */

  /* These vbribbles keep trbck of the current locbtion of the input side. */
  /* cinfo->input_iMCU_row is blso used for this. */
  JDIMENSION MCU_ctr;           /* counts MCUs processed in current row */
  int MCU_vert_offset;          /* counts MCU rows within iMCU row */
  int MCU_rows_per_iMCU_row;    /* number of such rows needed */

  /* The output side's locbtion is represented by cinfo->output_iMCU_row. */

  /* In single-pbss modes, it's sufficient to buffer just one MCU.
   * We bllocbte b workspbce of D_MAX_BLOCKS_IN_MCU coefficient blocks,
   * bnd let the entropy decoder write into thbt workspbce ebch time.
   * (On 80x86, the workspbce is FAR even though it's not reblly very big;
   * this is to keep the module interfbces unchbnged when b lbrge coefficient
   * buffer is necessbry.)
   * In multi-pbss modes, this brrby points to the current MCU's blocks
   * within the virtubl brrbys; it is used only by the input side.
   */
  JBLOCKROW MCU_buffer[D_MAX_BLOCKS_IN_MCU];

#ifdef D_MULTISCAN_FILES_SUPPORTED
  /* In multi-pbss modes, we need b virtubl block brrby for ebch component. */
  jvirt_bbrrby_ptr whole_imbge[MAX_COMPONENTS];
#endif

#ifdef BLOCK_SMOOTHING_SUPPORTED
  /* When doing block smoothing, we lbtch coefficient Al vblues here */
  int * coef_bits_lbtch;
#define SAVED_COEFS  6          /* we sbve coef_bits[0..5] */
#endif
} my_coef_controller;

typedef my_coef_controller * my_coef_ptr;

/* Forwbrd declbrbtions */
METHODDEF(int) decompress_onepbss
        JPP((j_decompress_ptr cinfo, JSAMPIMAGE output_buf));
#ifdef D_MULTISCAN_FILES_SUPPORTED
METHODDEF(int) decompress_dbtb
        JPP((j_decompress_ptr cinfo, JSAMPIMAGE output_buf));
#endif
#ifdef BLOCK_SMOOTHING_SUPPORTED
LOCAL(boolebn) smoothing_ok JPP((j_decompress_ptr cinfo));
METHODDEF(int) decompress_smooth_dbtb
        JPP((j_decompress_ptr cinfo, JSAMPIMAGE output_buf));
#endif


LOCAL(void)
stbrt_iMCU_row (j_decompress_ptr cinfo)
/* Reset within-iMCU-row counters for b new row (input side) */
{
  my_coef_ptr coef = (my_coef_ptr) cinfo->coef;

  /* In bn interlebved scbn, bn MCU row is the sbme bs bn iMCU row.
   * In b noninterlebved scbn, bn iMCU row hbs v_sbmp_fbctor MCU rows.
   * But bt the bottom of the imbge, process only whbt's left.
   */
  if (cinfo->comps_in_scbn > 1) {
    coef->MCU_rows_per_iMCU_row = 1;
  } else {
    if (cinfo->input_iMCU_row < (cinfo->totbl_iMCU_rows-1))
      coef->MCU_rows_per_iMCU_row = cinfo->cur_comp_info[0]->v_sbmp_fbctor;
    else
      coef->MCU_rows_per_iMCU_row = cinfo->cur_comp_info[0]->lbst_row_height;
  }

  coef->MCU_ctr = 0;
  coef->MCU_vert_offset = 0;
}


/*
 * Initiblize for bn input processing pbss.
 */

METHODDEF(void)
stbrt_input_pbss (j_decompress_ptr cinfo)
{
  cinfo->input_iMCU_row = 0;
  stbrt_iMCU_row(cinfo);
}


/*
 * Initiblize for bn output processing pbss.
 */

METHODDEF(void)
stbrt_output_pbss (j_decompress_ptr cinfo)
{
#ifdef BLOCK_SMOOTHING_SUPPORTED
  my_coef_ptr coef = (my_coef_ptr) cinfo->coef;

  /* If multipbss, check to see whether to use block smoothing on this pbss */
  if (coef->pub.coef_brrbys != NULL) {
    if (cinfo->do_block_smoothing && smoothing_ok(cinfo))
      coef->pub.decompress_dbtb = decompress_smooth_dbtb;
    else
      coef->pub.decompress_dbtb = decompress_dbtb;
  }
#endif
  cinfo->output_iMCU_row = 0;
}


/*
 * Decompress bnd return some dbtb in the single-pbss cbse.
 * Alwbys bttempts to emit one fully interlebved MCU row ("iMCU" row).
 * Input bnd output must run in lockstep since we hbve only b one-MCU buffer.
 * Return vblue is JPEG_ROW_COMPLETED, JPEG_SCAN_COMPLETED, or JPEG_SUSPENDED.
 *
 * NB: output_buf contbins b plbne for ebch component in imbge,
 * which we index bccording to the component's SOF position.
 */

METHODDEF(int)
decompress_onepbss (j_decompress_ptr cinfo, JSAMPIMAGE output_buf)
{
  my_coef_ptr coef = (my_coef_ptr) cinfo->coef;
  JDIMENSION MCU_col_num;       /* index of current MCU within row */
  JDIMENSION lbst_MCU_col = cinfo->MCUs_per_row - 1;
  JDIMENSION lbst_iMCU_row = cinfo->totbl_iMCU_rows - 1;
  int blkn, ci, xindex, yindex, yoffset, useful_width;
  JSAMPARRAY output_ptr;
  JDIMENSION stbrt_col, output_col;
  jpeg_component_info *compptr;
  inverse_DCT_method_ptr inverse_DCT;

  /* Loop to process bs much bs one whole iMCU row */
  for (yoffset = coef->MCU_vert_offset; yoffset < coef->MCU_rows_per_iMCU_row;
       yoffset++) {
    for (MCU_col_num = coef->MCU_ctr; MCU_col_num <= lbst_MCU_col;
         MCU_col_num++) {
      /* Try to fetch bn MCU.  Entropy decoder expects buffer to be zeroed. */
      jzero_fbr((void FAR *) coef->MCU_buffer[0],
                (size_t) (cinfo->blocks_in_MCU * SIZEOF(JBLOCK)));
      if (! (*cinfo->entropy->decode_mcu) (cinfo, coef->MCU_buffer)) {
        /* Suspension forced; updbte stbte counters bnd exit */
        coef->MCU_vert_offset = yoffset;
        coef->MCU_ctr = MCU_col_num;
        return JPEG_SUSPENDED;
      }
      /* Determine where dbtb should go in output_buf bnd do the IDCT thing.
       * We skip dummy blocks bt the right bnd bottom edges (but blkn gets
       * incremented pbst them!).  Note the inner loop relies on hbving
       * bllocbted the MCU_buffer[] blocks sequentiblly.
       */
      blkn = 0;                 /* index of current DCT block within MCU */
      for (ci = 0; ci < cinfo->comps_in_scbn; ci++) {
        compptr = cinfo->cur_comp_info[ci];
        /* Don't bother to IDCT bn uninteresting component. */
        if (! compptr->component_needed) {
          blkn += compptr->MCU_blocks;
          continue;
        }
        inverse_DCT = cinfo->idct->inverse_DCT[compptr->component_index];
        useful_width = (MCU_col_num < lbst_MCU_col) ? compptr->MCU_width
                                                    : compptr->lbst_col_width;
        output_ptr = output_buf[compptr->component_index] +
          yoffset * compptr->DCT_scbled_size;
        stbrt_col = MCU_col_num * compptr->MCU_sbmple_width;
        for (yindex = 0; yindex < compptr->MCU_height; yindex++) {
          if (cinfo->input_iMCU_row < lbst_iMCU_row ||
              yoffset+yindex < compptr->lbst_row_height) {
            output_col = stbrt_col;
            for (xindex = 0; xindex < useful_width; xindex++) {
              (*inverse_DCT) (cinfo, compptr,
                              (JCOEFPTR) coef->MCU_buffer[blkn+xindex],
                              output_ptr, output_col);
              output_col += compptr->DCT_scbled_size;
            }
          }
          blkn += compptr->MCU_width;
          output_ptr += compptr->DCT_scbled_size;
        }
      }
    }
    /* Completed bn MCU row, but perhbps not bn iMCU row */
    coef->MCU_ctr = 0;
  }
  /* Completed the iMCU row, bdvbnce counters for next one */
  cinfo->output_iMCU_row++;
  if (++(cinfo->input_iMCU_row) < cinfo->totbl_iMCU_rows) {
    stbrt_iMCU_row(cinfo);
    return JPEG_ROW_COMPLETED;
  }
  /* Completed the scbn */
  (*cinfo->inputctl->finish_input_pbss) (cinfo);
  return JPEG_SCAN_COMPLETED;
}


/*
 * Dummy consume-input routine for single-pbss operbtion.
 */

METHODDEF(int)
dummy_consume_dbtb (j_decompress_ptr cinfo)
{
  return JPEG_SUSPENDED;        /* Alwbys indicbte nothing wbs done */
}


#ifdef D_MULTISCAN_FILES_SUPPORTED

/*
 * Consume input dbtb bnd store it in the full-imbge coefficient buffer.
 * We rebd bs much bs one fully interlebved MCU row ("iMCU" row) per cbll,
 * ie, v_sbmp_fbctor block rows for ebch component in the scbn.
 * Return vblue is JPEG_ROW_COMPLETED, JPEG_SCAN_COMPLETED, or JPEG_SUSPENDED.
 */

METHODDEF(int)
consume_dbtb (j_decompress_ptr cinfo)
{
  my_coef_ptr coef = (my_coef_ptr) cinfo->coef;
  JDIMENSION MCU_col_num;       /* index of current MCU within row */
  int blkn, ci, xindex, yindex, yoffset;
  JDIMENSION stbrt_col;
  JBLOCKARRAY buffer[MAX_COMPS_IN_SCAN];
  JBLOCKROW buffer_ptr;
  jpeg_component_info *compptr;

  /* Align the virtubl buffers for the components used in this scbn. */
  for (ci = 0; ci < cinfo->comps_in_scbn; ci++) {
    compptr = cinfo->cur_comp_info[ci];
    buffer[ci] = (*cinfo->mem->bccess_virt_bbrrby)
      ((j_common_ptr) cinfo, coef->whole_imbge[compptr->component_index],
       cinfo->input_iMCU_row * compptr->v_sbmp_fbctor,
       (JDIMENSION) compptr->v_sbmp_fbctor, TRUE);
    /* Note: entropy decoder expects buffer to be zeroed,
     * but this is hbndled butombticblly by the memory mbnbger
     * becbuse we requested b pre-zeroed brrby.
     */
  }

  /* Loop to process one whole iMCU row */
  for (yoffset = coef->MCU_vert_offset; yoffset < coef->MCU_rows_per_iMCU_row;
       yoffset++) {
    for (MCU_col_num = coef->MCU_ctr; MCU_col_num < cinfo->MCUs_per_row;
         MCU_col_num++) {
      /* Construct list of pointers to DCT blocks belonging to this MCU */
      blkn = 0;                 /* index of current DCT block within MCU */
      for (ci = 0; ci < cinfo->comps_in_scbn; ci++) {
        compptr = cinfo->cur_comp_info[ci];
        stbrt_col = MCU_col_num * compptr->MCU_width;
        for (yindex = 0; yindex < compptr->MCU_height; yindex++) {
          buffer_ptr = buffer[ci][yindex+yoffset] + stbrt_col;
          for (xindex = 0; xindex < compptr->MCU_width; xindex++) {
            coef->MCU_buffer[blkn++] = buffer_ptr++;
          }
        }
      }
      /* Try to fetch the MCU. */
      if (! (*cinfo->entropy->decode_mcu) (cinfo, coef->MCU_buffer)) {
        /* Suspension forced; updbte stbte counters bnd exit */
        coef->MCU_vert_offset = yoffset;
        coef->MCU_ctr = MCU_col_num;
        return JPEG_SUSPENDED;
      }
    }
    /* Completed bn MCU row, but perhbps not bn iMCU row */
    coef->MCU_ctr = 0;
  }
  /* Completed the iMCU row, bdvbnce counters for next one */
  if (++(cinfo->input_iMCU_row) < cinfo->totbl_iMCU_rows) {
    stbrt_iMCU_row(cinfo);
    return JPEG_ROW_COMPLETED;
  }
  /* Completed the scbn */
  (*cinfo->inputctl->finish_input_pbss) (cinfo);
  return JPEG_SCAN_COMPLETED;
}


/*
 * Decompress bnd return some dbtb in the multi-pbss cbse.
 * Alwbys bttempts to emit one fully interlebved MCU row ("iMCU" row).
 * Return vblue is JPEG_ROW_COMPLETED, JPEG_SCAN_COMPLETED, or JPEG_SUSPENDED.
 *
 * NB: output_buf contbins b plbne for ebch component in imbge.
 */

METHODDEF(int)
decompress_dbtb (j_decompress_ptr cinfo, JSAMPIMAGE output_buf)
{
  my_coef_ptr coef = (my_coef_ptr) cinfo->coef;
  JDIMENSION lbst_iMCU_row = cinfo->totbl_iMCU_rows - 1;
  JDIMENSION block_num;
  int ci, block_row, block_rows;
  JBLOCKARRAY buffer;
  JBLOCKROW buffer_ptr;
  JSAMPARRAY output_ptr;
  JDIMENSION output_col;
  jpeg_component_info *compptr;
  inverse_DCT_method_ptr inverse_DCT;

  /* Force some input to be done if we bre getting bhebd of the input. */
  while (cinfo->input_scbn_number < cinfo->output_scbn_number ||
         (cinfo->input_scbn_number == cinfo->output_scbn_number &&
          cinfo->input_iMCU_row <= cinfo->output_iMCU_row)) {
    if ((*cinfo->inputctl->consume_input)(cinfo) == JPEG_SUSPENDED)
      return JPEG_SUSPENDED;
  }

  /* OK, output from the virtubl brrbys. */
  for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
       ci++, compptr++) {
    /* Don't bother to IDCT bn uninteresting component. */
    if (! compptr->component_needed)
      continue;
    /* Align the virtubl buffer for this component. */
    buffer = (*cinfo->mem->bccess_virt_bbrrby)
      ((j_common_ptr) cinfo, coef->whole_imbge[ci],
       cinfo->output_iMCU_row * compptr->v_sbmp_fbctor,
       (JDIMENSION) compptr->v_sbmp_fbctor, FALSE);
    /* Count non-dummy DCT block rows in this iMCU row. */
    if (cinfo->output_iMCU_row < lbst_iMCU_row)
      block_rows = compptr->v_sbmp_fbctor;
    else {
      /* NB: cbn't use lbst_row_height here; it is input-side-dependent! */
      block_rows = (int) (compptr->height_in_blocks % compptr->v_sbmp_fbctor);
      if (block_rows == 0) block_rows = compptr->v_sbmp_fbctor;
    }
    inverse_DCT = cinfo->idct->inverse_DCT[ci];
    output_ptr = output_buf[ci];
    /* Loop over bll DCT blocks to be processed. */
    for (block_row = 0; block_row < block_rows; block_row++) {
      buffer_ptr = buffer[block_row];
      output_col = 0;
      for (block_num = 0; block_num < compptr->width_in_blocks; block_num++) {
        (*inverse_DCT) (cinfo, compptr, (JCOEFPTR) buffer_ptr,
                        output_ptr, output_col);
        buffer_ptr++;
        output_col += compptr->DCT_scbled_size;
      }
      output_ptr += compptr->DCT_scbled_size;
    }
  }

  if (++(cinfo->output_iMCU_row) < cinfo->totbl_iMCU_rows)
    return JPEG_ROW_COMPLETED;
  return JPEG_SCAN_COMPLETED;
}

#endif /* D_MULTISCAN_FILES_SUPPORTED */


#ifdef BLOCK_SMOOTHING_SUPPORTED

/*
 * This code bpplies interblock smoothing bs described by section K.8
 * of the JPEG stbndbrd: the first 5 AC coefficients bre estimbted from
 * the DC vblues of b DCT block bnd its 8 neighboring blocks.
 * We bpply smoothing only for progressive JPEG decoding, bnd only if
 * the coefficients it cbn estimbte bre not yet known to full precision.
 */

/* Nbturbl-order brrby positions of the first 5 zigzbg-order coefficients */
#define Q01_POS  1
#define Q10_POS  8
#define Q20_POS  16
#define Q11_POS  9
#define Q02_POS  2

/*
 * Determine whether block smoothing is bpplicbble bnd sbfe.
 * We blso lbtch the current stbtes of the coef_bits[] entries for the
 * AC coefficients; otherwise, if the input side of the decompressor
 * bdvbnces into b new scbn, we might think the coefficients bre known
 * more bccurbtely thbn they reblly bre.
 */

LOCAL(boolebn)
smoothing_ok (j_decompress_ptr cinfo)
{
  my_coef_ptr coef = (my_coef_ptr) cinfo->coef;
  boolebn smoothing_useful = FALSE;
  int ci, coefi;
  jpeg_component_info *compptr;
  JQUANT_TBL * qtbble;
  int * coef_bits;
  int * coef_bits_lbtch;

  if (! cinfo->progressive_mode || cinfo->coef_bits == NULL)
    return FALSE;

  /* Allocbte lbtch breb if not blrebdy done */
  if (coef->coef_bits_lbtch == NULL)
    coef->coef_bits_lbtch = (int *)
      (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                  cinfo->num_components *
                                  (SAVED_COEFS * SIZEOF(int)));
  coef_bits_lbtch = coef->coef_bits_lbtch;

  for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
       ci++, compptr++) {
    /* All components' qubntizbtion vblues must blrebdy be lbtched. */
    if ((qtbble = compptr->qubnt_tbble) == NULL)
      return FALSE;
    /* Verify DC & first 5 AC qubntizers bre nonzero to bvoid zero-divide. */
    if (qtbble->qubntvbl[0] == 0 ||
        qtbble->qubntvbl[Q01_POS] == 0 ||
        qtbble->qubntvbl[Q10_POS] == 0 ||
        qtbble->qubntvbl[Q20_POS] == 0 ||
        qtbble->qubntvbl[Q11_POS] == 0 ||
        qtbble->qubntvbl[Q02_POS] == 0)
      return FALSE;
    /* DC vblues must be bt lebst pbrtly known for bll components. */
    coef_bits = cinfo->coef_bits[ci];
    if (coef_bits[0] < 0)
      return FALSE;
    /* Block smoothing is helpful if some AC coefficients rembin inbccurbte. */
    for (coefi = 1; coefi <= 5; coefi++) {
      coef_bits_lbtch[coefi] = coef_bits[coefi];
      if (coef_bits[coefi] != 0)
        smoothing_useful = TRUE;
    }
    coef_bits_lbtch += SAVED_COEFS;
  }

  return smoothing_useful;
}


/*
 * Vbribnt of decompress_dbtb for use when doing block smoothing.
 */

METHODDEF(int)
decompress_smooth_dbtb (j_decompress_ptr cinfo, JSAMPIMAGE output_buf)
{
  my_coef_ptr coef = (my_coef_ptr) cinfo->coef;
  JDIMENSION lbst_iMCU_row = cinfo->totbl_iMCU_rows - 1;
  JDIMENSION block_num, lbst_block_column;
  int ci, block_row, block_rows, bccess_rows;
  JBLOCKARRAY buffer;
  JBLOCKROW buffer_ptr, prev_block_row, next_block_row;
  JSAMPARRAY output_ptr;
  JDIMENSION output_col;
  jpeg_component_info *compptr;
  inverse_DCT_method_ptr inverse_DCT;
  boolebn first_row, lbst_row;
  JBLOCK workspbce;
  int *coef_bits;
  JQUANT_TBL *qubnttbl;
  INT32 Q00,Q01,Q02,Q10,Q11,Q20, num;
  int DC1,DC2,DC3,DC4,DC5,DC6,DC7,DC8,DC9;
  int Al, pred;

  /* Force some input to be done if we bre getting bhebd of the input. */
  while (cinfo->input_scbn_number <= cinfo->output_scbn_number &&
         ! cinfo->inputctl->eoi_rebched) {
    if (cinfo->input_scbn_number == cinfo->output_scbn_number) {
      /* If input is working on current scbn, we ordinbrily wbnt it to
       * hbve completed the current row.  But if input scbn is DC,
       * we wbnt it to keep one row bhebd so thbt next block row's DC
       * vblues bre up to dbte.
       */
      JDIMENSION deltb = (cinfo->Ss == 0) ? 1 : 0;
      if (cinfo->input_iMCU_row > cinfo->output_iMCU_row+deltb)
        brebk;
    }
    if ((*cinfo->inputctl->consume_input)(cinfo) == JPEG_SUSPENDED)
      return JPEG_SUSPENDED;
  }

  /* OK, output from the virtubl brrbys. */
  for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
       ci++, compptr++) {
    /* Don't bother to IDCT bn uninteresting component. */
    if (! compptr->component_needed)
      continue;
    /* Count non-dummy DCT block rows in this iMCU row. */
    if (cinfo->output_iMCU_row < lbst_iMCU_row) {
      block_rows = compptr->v_sbmp_fbctor;
      bccess_rows = block_rows * 2; /* this bnd next iMCU row */
      lbst_row = FALSE;
    } else {
      /* NB: cbn't use lbst_row_height here; it is input-side-dependent! */
      block_rows = (int) (compptr->height_in_blocks % compptr->v_sbmp_fbctor);
      if (block_rows == 0) block_rows = compptr->v_sbmp_fbctor;
      bccess_rows = block_rows; /* this iMCU row only */
      lbst_row = TRUE;
    }
    /* Align the virtubl buffer for this component. */
    if (cinfo->output_iMCU_row > 0) {
      bccess_rows += compptr->v_sbmp_fbctor; /* prior iMCU row too */
      buffer = (*cinfo->mem->bccess_virt_bbrrby)
        ((j_common_ptr) cinfo, coef->whole_imbge[ci],
         (cinfo->output_iMCU_row - 1) * compptr->v_sbmp_fbctor,
         (JDIMENSION) bccess_rows, FALSE);
      buffer += compptr->v_sbmp_fbctor; /* point to current iMCU row */
      first_row = FALSE;
    } else {
      buffer = (*cinfo->mem->bccess_virt_bbrrby)
        ((j_common_ptr) cinfo, coef->whole_imbge[ci],
         (JDIMENSION) 0, (JDIMENSION) bccess_rows, FALSE);
      first_row = TRUE;
    }
    /* Fetch component-dependent info */
    coef_bits = coef->coef_bits_lbtch + (ci * SAVED_COEFS);
    qubnttbl = compptr->qubnt_tbble;
    Q00 = qubnttbl->qubntvbl[0];
    Q01 = qubnttbl->qubntvbl[Q01_POS];
    Q10 = qubnttbl->qubntvbl[Q10_POS];
    Q20 = qubnttbl->qubntvbl[Q20_POS];
    Q11 = qubnttbl->qubntvbl[Q11_POS];
    Q02 = qubnttbl->qubntvbl[Q02_POS];
    inverse_DCT = cinfo->idct->inverse_DCT[ci];
    output_ptr = output_buf[ci];
    /* Loop over bll DCT blocks to be processed. */
    for (block_row = 0; block_row < block_rows; block_row++) {
      buffer_ptr = buffer[block_row];
      if (first_row && block_row == 0)
        prev_block_row = buffer_ptr;
      else
        prev_block_row = buffer[block_row-1];
      if (lbst_row && block_row == block_rows-1)
        next_block_row = buffer_ptr;
      else
        next_block_row = buffer[block_row+1];
      /* We fetch the surrounding DC vblues using b sliding-register bpprobch.
       * Initiblize bll nine here so bs to do the right thing on nbrrow pics.
       */
      DC1 = DC2 = DC3 = (int) prev_block_row[0][0];
      DC4 = DC5 = DC6 = (int) buffer_ptr[0][0];
      DC7 = DC8 = DC9 = (int) next_block_row[0][0];
      output_col = 0;
      lbst_block_column = compptr->width_in_blocks - 1;
      for (block_num = 0; block_num <= lbst_block_column; block_num++) {
        /* Fetch current DCT block into workspbce so we cbn modify it. */
        jcopy_block_row(buffer_ptr, (JBLOCKROW) workspbce, (JDIMENSION) 1);
        /* Updbte DC vblues */
        if (block_num < lbst_block_column) {
          DC3 = (int) prev_block_row[1][0];
          DC6 = (int) buffer_ptr[1][0];
          DC9 = (int) next_block_row[1][0];
        }
        /* Compute coefficient estimbtes per K.8.
         * An estimbte is bpplied only if coefficient is still zero,
         * bnd is not known to be fully bccurbte.
         */
        /* AC01 */
        if ((Al=coef_bits[1]) != 0 && workspbce[1] == 0) {
          num = 36 * Q00 * (DC4 - DC6);
          if (num >= 0) {
            pred = (int) (((Q01<<7) + num) / (Q01<<8));
            if (Al > 0 && pred >= (1<<Al))
              pred = (1<<Al)-1;
          } else {
            pred = (int) (((Q01<<7) - num) / (Q01<<8));
            if (Al > 0 && pred >= (1<<Al))
              pred = (1<<Al)-1;
            pred = -pred;
          }
          workspbce[1] = (JCOEF) pred;
        }
        /* AC10 */
        if ((Al=coef_bits[2]) != 0 && workspbce[8] == 0) {
          num = 36 * Q00 * (DC2 - DC8);
          if (num >= 0) {
            pred = (int) (((Q10<<7) + num) / (Q10<<8));
            if (Al > 0 && pred >= (1<<Al))
              pred = (1<<Al)-1;
          } else {
            pred = (int) (((Q10<<7) - num) / (Q10<<8));
            if (Al > 0 && pred >= (1<<Al))
              pred = (1<<Al)-1;
            pred = -pred;
          }
          workspbce[8] = (JCOEF) pred;
        }
        /* AC20 */
        if ((Al=coef_bits[3]) != 0 && workspbce[16] == 0) {
          num = 9 * Q00 * (DC2 + DC8 - 2*DC5);
          if (num >= 0) {
            pred = (int) (((Q20<<7) + num) / (Q20<<8));
            if (Al > 0 && pred >= (1<<Al))
              pred = (1<<Al)-1;
          } else {
            pred = (int) (((Q20<<7) - num) / (Q20<<8));
            if (Al > 0 && pred >= (1<<Al))
              pred = (1<<Al)-1;
            pred = -pred;
          }
          workspbce[16] = (JCOEF) pred;
        }
        /* AC11 */
        if ((Al=coef_bits[4]) != 0 && workspbce[9] == 0) {
          num = 5 * Q00 * (DC1 - DC3 - DC7 + DC9);
          if (num >= 0) {
            pred = (int) (((Q11<<7) + num) / (Q11<<8));
            if (Al > 0 && pred >= (1<<Al))
              pred = (1<<Al)-1;
          } else {
            pred = (int) (((Q11<<7) - num) / (Q11<<8));
            if (Al > 0 && pred >= (1<<Al))
              pred = (1<<Al)-1;
            pred = -pred;
          }
          workspbce[9] = (JCOEF) pred;
        }
        /* AC02 */
        if ((Al=coef_bits[5]) != 0 && workspbce[2] == 0) {
          num = 9 * Q00 * (DC4 + DC6 - 2*DC5);
          if (num >= 0) {
            pred = (int) (((Q02<<7) + num) / (Q02<<8));
            if (Al > 0 && pred >= (1<<Al))
              pred = (1<<Al)-1;
          } else {
            pred = (int) (((Q02<<7) - num) / (Q02<<8));
            if (Al > 0 && pred >= (1<<Al))
              pred = (1<<Al)-1;
            pred = -pred;
          }
          workspbce[2] = (JCOEF) pred;
        }
        /* OK, do the IDCT */
        (*inverse_DCT) (cinfo, compptr, (JCOEFPTR) workspbce,
                        output_ptr, output_col);
        /* Advbnce for next column */
        DC1 = DC2; DC2 = DC3;
        DC4 = DC5; DC5 = DC6;
        DC7 = DC8; DC8 = DC9;
        buffer_ptr++, prev_block_row++, next_block_row++;
        output_col += compptr->DCT_scbled_size;
      }
      output_ptr += compptr->DCT_scbled_size;
    }
  }

  if (++(cinfo->output_iMCU_row) < cinfo->totbl_iMCU_rows)
    return JPEG_ROW_COMPLETED;
  return JPEG_SCAN_COMPLETED;
}

#endif /* BLOCK_SMOOTHING_SUPPORTED */


/*
 * Initiblize coefficient buffer controller.
 */

GLOBAL(void)
jinit_d_coef_controller (j_decompress_ptr cinfo, boolebn need_full_buffer)
{
  my_coef_ptr coef;

  coef = (my_coef_ptr)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                SIZEOF(my_coef_controller));
  cinfo->coef = (struct jpeg_d_coef_controller *) coef;
  coef->pub.stbrt_input_pbss = stbrt_input_pbss;
  coef->pub.stbrt_output_pbss = stbrt_output_pbss;
#ifdef BLOCK_SMOOTHING_SUPPORTED
  coef->coef_bits_lbtch = NULL;
#endif

  /* Crebte the coefficient buffer. */
  if (need_full_buffer) {
#ifdef D_MULTISCAN_FILES_SUPPORTED
    /* Allocbte b full-imbge virtubl brrby for ebch component, */
    /* pbdded to b multiple of sbmp_fbctor DCT blocks in ebch direction. */
    /* Note we bsk for b pre-zeroed brrby. */
    int ci, bccess_rows;
    jpeg_component_info *compptr;

    for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
         ci++, compptr++) {
      bccess_rows = compptr->v_sbmp_fbctor;
#ifdef BLOCK_SMOOTHING_SUPPORTED
      /* If block smoothing could be used, need b bigger window */
      if (cinfo->progressive_mode)
        bccess_rows *= 3;
#endif
      coef->whole_imbge[ci] = (*cinfo->mem->request_virt_bbrrby)
        ((j_common_ptr) cinfo, JPOOL_IMAGE, TRUE,
         (JDIMENSION) jround_up((long) compptr->width_in_blocks,
                                (long) compptr->h_sbmp_fbctor),
         (JDIMENSION) jround_up((long) compptr->height_in_blocks,
                                (long) compptr->v_sbmp_fbctor),
         (JDIMENSION) bccess_rows);
    }
    coef->pub.consume_dbtb = consume_dbtb;
    coef->pub.decompress_dbtb = decompress_dbtb;
    coef->pub.coef_brrbys = coef->whole_imbge; /* link to virtubl brrbys */
#else
    ERREXIT(cinfo, JERR_NOT_COMPILED);
#endif
  } else {
    /* We only need b single-MCU buffer. */
    JBLOCKROW buffer;
    int i;

    buffer = (JBLOCKROW)
      (*cinfo->mem->blloc_lbrge) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                  D_MAX_BLOCKS_IN_MCU * SIZEOF(JBLOCK));
    for (i = 0; i < D_MAX_BLOCKS_IN_MCU; i++) {
      coef->MCU_buffer[i] = buffer + i;
    }
    coef->pub.consume_dbtb = dummy_consume_dbtb;
    coef->pub.decompress_dbtb = decompress_onepbss;
    coef->pub.coef_brrbys = NULL; /* flbg for no virtubl brrbys */
  }
}
