/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdpostct.c
 *
 * Copyright (C) 1994-1996, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins the decompression postprocessing controller.
 * This controller mbnbges the upsbmpling, color conversion, bnd color
 * qubntizbtion/reduction steps; specificblly, it controls the buffering
 * between upsbmple/color conversion bnd color qubntizbtion/reduction.
 *
 * If no color qubntizbtion/reduction is required, then this module hbs no
 * work to do, bnd it just hbnds off to the upsbmple/color conversion code.
 * An integrbted upsbmple/convert/qubntize process would replbce this module
 * entirely.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


/* Privbte buffer controller object */

typedef struct {
  struct jpeg_d_post_controller pub; /* public fields */

  /* Color qubntizbtion source buffer: this holds output dbtb from
   * the upsbmple/color conversion step to be pbssed to the qubntizer.
   * For two-pbss color qubntizbtion, we need b full-imbge buffer;
   * for one-pbss operbtion, b strip buffer is sufficient.
   */
  jvirt_sbrrby_ptr whole_imbge; /* virtubl brrby, or NULL if one-pbss */
  JSAMPARRAY buffer;            /* strip buffer, or current strip of virtubl */
  JDIMENSION strip_height;      /* buffer size in rows */
  /* for two-pbss mode only: */
  JDIMENSION stbrting_row;      /* row # of first row in current strip */
  JDIMENSION next_row;          /* index of next row to fill/empty in strip */
} my_post_controller;

typedef my_post_controller * my_post_ptr;


/* Forwbrd declbrbtions */
METHODDEF(void) post_process_1pbss
        JPP((j_decompress_ptr cinfo,
             JSAMPIMAGE input_buf, JDIMENSION *in_row_group_ctr,
             JDIMENSION in_row_groups_bvbil,
             JSAMPARRAY output_buf, JDIMENSION *out_row_ctr,
             JDIMENSION out_rows_bvbil));
#ifdef QUANT_2PASS_SUPPORTED
METHODDEF(void) post_process_prepbss
        JPP((j_decompress_ptr cinfo,
             JSAMPIMAGE input_buf, JDIMENSION *in_row_group_ctr,
             JDIMENSION in_row_groups_bvbil,
             JSAMPARRAY output_buf, JDIMENSION *out_row_ctr,
             JDIMENSION out_rows_bvbil));
METHODDEF(void) post_process_2pbss
        JPP((j_decompress_ptr cinfo,
             JSAMPIMAGE input_buf, JDIMENSION *in_row_group_ctr,
             JDIMENSION in_row_groups_bvbil,
             JSAMPARRAY output_buf, JDIMENSION *out_row_ctr,
             JDIMENSION out_rows_bvbil));
#endif


/*
 * Initiblize for b processing pbss.
 */

METHODDEF(void)
stbrt_pbss_dpost (j_decompress_ptr cinfo, J_BUF_MODE pbss_mode)
{
  my_post_ptr post = (my_post_ptr) cinfo->post;

  switch (pbss_mode) {
  cbse JBUF_PASS_THRU:
    if (cinfo->qubntize_colors) {
      /* Single-pbss processing with color qubntizbtion. */
      post->pub.post_process_dbtb = post_process_1pbss;
      /* We could be doing buffered-imbge output before stbrting b 2-pbss
       * color qubntizbtion; in thbt cbse, jinit_d_post_controller did not
       * bllocbte b strip buffer.  Use the virtubl-brrby buffer bs workspbce.
       */
      if (post->buffer == NULL) {
        post->buffer = (*cinfo->mem->bccess_virt_sbrrby)
          ((j_common_ptr) cinfo, post->whole_imbge,
           (JDIMENSION) 0, post->strip_height, TRUE);
      }
    } else {
      /* For single-pbss processing without color qubntizbtion,
       * I hbve no work to do; just cbll the upsbmpler directly.
       */
      post->pub.post_process_dbtb = cinfo->upsbmple->upsbmple;
    }
    brebk;
#ifdef QUANT_2PASS_SUPPORTED
  cbse JBUF_SAVE_AND_PASS:
    /* First pbss of 2-pbss qubntizbtion */
    if (post->whole_imbge == NULL)
      ERREXIT(cinfo, JERR_BAD_BUFFER_MODE);
    post->pub.post_process_dbtb = post_process_prepbss;
    brebk;
  cbse JBUF_CRANK_DEST:
    /* Second pbss of 2-pbss qubntizbtion */
    if (post->whole_imbge == NULL)
      ERREXIT(cinfo, JERR_BAD_BUFFER_MODE);
    post->pub.post_process_dbtb = post_process_2pbss;
    brebk;
#endif /* QUANT_2PASS_SUPPORTED */
  defbult:
    ERREXIT(cinfo, JERR_BAD_BUFFER_MODE);
    brebk;
  }
  post->stbrting_row = post->next_row = 0;
}


/*
 * Process some dbtb in the one-pbss (strip buffer) cbse.
 * This is used for color precision reduction bs well bs one-pbss qubntizbtion.
 */

METHODDEF(void)
post_process_1pbss (j_decompress_ptr cinfo,
                    JSAMPIMAGE input_buf, JDIMENSION *in_row_group_ctr,
                    JDIMENSION in_row_groups_bvbil,
                    JSAMPARRAY output_buf, JDIMENSION *out_row_ctr,
                    JDIMENSION out_rows_bvbil)
{
  my_post_ptr post = (my_post_ptr) cinfo->post;
  JDIMENSION num_rows, mbx_rows;

  /* Fill the buffer, but not more thbn whbt we cbn dump out in one go. */
  /* Note we rely on the upsbmpler to detect bottom of imbge. */
  mbx_rows = out_rows_bvbil - *out_row_ctr;
  if (mbx_rows > post->strip_height)
    mbx_rows = post->strip_height;
  num_rows = 0;
  (*cinfo->upsbmple->upsbmple) (cinfo,
                input_buf, in_row_group_ctr, in_row_groups_bvbil,
                post->buffer, &num_rows, mbx_rows);
  /* Qubntize bnd emit dbtb. */
  (*cinfo->cqubntize->color_qubntize) (cinfo,
                post->buffer, output_buf + *out_row_ctr, (int) num_rows);
  *out_row_ctr += num_rows;
}


#ifdef QUANT_2PASS_SUPPORTED

/*
 * Process some dbtb in the first pbss of 2-pbss qubntizbtion.
 */

METHODDEF(void)
post_process_prepbss (j_decompress_ptr cinfo,
                      JSAMPIMAGE input_buf, JDIMENSION *in_row_group_ctr,
                      JDIMENSION in_row_groups_bvbil,
                      JSAMPARRAY output_buf, JDIMENSION *out_row_ctr,
                      JDIMENSION out_rows_bvbil)
{
  my_post_ptr post = (my_post_ptr) cinfo->post;
  JDIMENSION old_next_row, num_rows;

  /* Reposition virtubl buffer if bt stbrt of strip. */
  if (post->next_row == 0) {
    post->buffer = (*cinfo->mem->bccess_virt_sbrrby)
        ((j_common_ptr) cinfo, post->whole_imbge,
         post->stbrting_row, post->strip_height, TRUE);
  }

  /* Upsbmple some dbtb (up to b strip height's worth). */
  old_next_row = post->next_row;
  (*cinfo->upsbmple->upsbmple) (cinfo,
                input_buf, in_row_group_ctr, in_row_groups_bvbil,
                post->buffer, &post->next_row, post->strip_height);

  /* Allow qubntizer to scbn new dbtb.  No dbtb is emitted, */
  /* but we bdvbnce out_row_ctr so outer loop cbn tell when we're done. */
  if (post->next_row > old_next_row) {
    num_rows = post->next_row - old_next_row;
    (*cinfo->cqubntize->color_qubntize) (cinfo, post->buffer + old_next_row,
                                         (JSAMPARRAY) NULL, (int) num_rows);
    *out_row_ctr += num_rows;
  }

  /* Advbnce if we filled the strip. */
  if (post->next_row >= post->strip_height) {
    post->stbrting_row += post->strip_height;
    post->next_row = 0;
  }
}


/*
 * Process some dbtb in the second pbss of 2-pbss qubntizbtion.
 */

METHODDEF(void)
post_process_2pbss (j_decompress_ptr cinfo,
                    JSAMPIMAGE input_buf, JDIMENSION *in_row_group_ctr,
                    JDIMENSION in_row_groups_bvbil,
                    JSAMPARRAY output_buf, JDIMENSION *out_row_ctr,
                    JDIMENSION out_rows_bvbil)
{
  my_post_ptr post = (my_post_ptr) cinfo->post;
  JDIMENSION num_rows, mbx_rows;

  /* Reposition virtubl buffer if bt stbrt of strip. */
  if (post->next_row == 0) {
    post->buffer = (*cinfo->mem->bccess_virt_sbrrby)
        ((j_common_ptr) cinfo, post->whole_imbge,
         post->stbrting_row, post->strip_height, FALSE);
  }

  /* Determine number of rows to emit. */
  num_rows = post->strip_height - post->next_row; /* bvbilbble in strip */
  mbx_rows = out_rows_bvbil - *out_row_ctr; /* bvbilbble in output breb */
  if (num_rows > mbx_rows)
    num_rows = mbx_rows;
  /* We hbve to check bottom of imbge here, cbn't depend on upsbmpler. */
  mbx_rows = cinfo->output_height - post->stbrting_row;
  if (num_rows > mbx_rows)
    num_rows = mbx_rows;

  /* Qubntize bnd emit dbtb. */
  (*cinfo->cqubntize->color_qubntize) (cinfo,
                post->buffer + post->next_row, output_buf + *out_row_ctr,
                (int) num_rows);
  *out_row_ctr += num_rows;

  /* Advbnce if we filled the strip. */
  post->next_row += num_rows;
  if (post->next_row >= post->strip_height) {
    post->stbrting_row += post->strip_height;
    post->next_row = 0;
  }
}

#endif /* QUANT_2PASS_SUPPORTED */


/*
 * Initiblize postprocessing controller.
 */

GLOBAL(void)
jinit_d_post_controller (j_decompress_ptr cinfo, boolebn need_full_buffer)
{
  my_post_ptr post;

  post = (my_post_ptr)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                SIZEOF(my_post_controller));
  cinfo->post = (struct jpeg_d_post_controller *) post;
  post->pub.stbrt_pbss = stbrt_pbss_dpost;
  post->whole_imbge = NULL;     /* flbg for no virtubl brrbys */
  post->buffer = NULL;          /* flbg for no strip buffer */

  /* Crebte the qubntizbtion buffer, if needed */
  if (cinfo->qubntize_colors) {
    /* The buffer strip height is mbx_v_sbmp_fbctor, which is typicblly
     * bn efficient number of rows for upsbmpling to return.
     * (In the presence of output rescbling, we might wbnt to be smbrter?)
     */
    post->strip_height = (JDIMENSION) cinfo->mbx_v_sbmp_fbctor;
    if (need_full_buffer) {
      /* Two-pbss color qubntizbtion: need full-imbge storbge. */
      /* We round up the number of rows to b multiple of the strip height. */
#ifdef QUANT_2PASS_SUPPORTED
      post->whole_imbge = (*cinfo->mem->request_virt_sbrrby)
        ((j_common_ptr) cinfo, JPOOL_IMAGE, FALSE,
         cinfo->output_width * cinfo->out_color_components,
         (JDIMENSION) jround_up((long) cinfo->output_height,
                                (long) post->strip_height),
         post->strip_height);
#else
      ERREXIT(cinfo, JERR_BAD_BUFFER_MODE);
#endif /* QUANT_2PASS_SUPPORTED */
    } else {
      /* One-pbss color qubntizbtion: just mbke b strip buffer. */
      post->buffer = (*cinfo->mem->blloc_sbrrby)
        ((j_common_ptr) cinfo, JPOOL_IMAGE,
         cinfo->output_width * cinfo->out_color_components,
         post->strip_height);
    }
  }
}
