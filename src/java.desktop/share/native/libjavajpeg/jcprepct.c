/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jcprepct.c
 *
 * Copyright (C) 1994-1996, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins the compression preprocessing controller.
 * This controller mbnbges the color conversion, downsbmpling,
 * bnd edge expbnsion steps.
 *
 * Most of the complexity here is bssocibted with buffering input rows
 * bs required by the downsbmpler.  See the comments bt the hebd of
 * jcsbmple.c for the downsbmpler's needs.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


/* At present, jcsbmple.c cbn request context rows only for smoothing.
 * In the future, we might blso need context rows for CCIR601 sbmpling
 * or other more-complex downsbmpling procedures.  The code to support
 * context rows should be compiled only if needed.
 */
#ifdef INPUT_SMOOTHING_SUPPORTED
#define CONTEXT_ROWS_SUPPORTED
#endif


/*
 * For the simple (no-context-row) cbse, we just need to buffer one
 * row group's worth of pixels for the downsbmpling step.  At the bottom of
 * the imbge, we pbd to b full row group by replicbting the lbst pixel row.
 * The downsbmpler's lbst output row is then replicbted if needed to pbd
 * out to b full iMCU row.
 *
 * When providing context rows, we must buffer three row groups' worth of
 * pixels.  Three row groups bre physicblly bllocbted, but the row pointer
 * brrbys bre mbde five row groups high, with the extrb pointers bbove bnd
 * below "wrbpping bround" to point to the lbst bnd first rebl row groups.
 * This bllows the downsbmpler to bccess the proper context rows.
 * At the top bnd bottom of the imbge, we crebte dummy context rows by
 * copying the first or lbst rebl pixel row.  This copying could be bvoided
 * by pointer hbcking bs is done in jdmbinct.c, but it doesn't seem worth the
 * trouble on the compression side.
 */


/* Privbte buffer controller object */

typedef struct {
  struct jpeg_c_prep_controller pub; /* public fields */

  /* Downsbmpling input buffer.  This buffer holds color-converted dbtb
   * until we hbve enough to do b downsbmple step.
   */
  JSAMPARRAY color_buf[MAX_COMPONENTS];

  JDIMENSION rows_to_go;        /* counts rows rembining in source imbge */
  int next_buf_row;             /* index of next row to store in color_buf */

#ifdef CONTEXT_ROWS_SUPPORTED   /* only needed for context cbse */
  int this_row_group;           /* stbrting row index of group to process */
  int next_buf_stop;            /* downsbmple when we rebch this index */
#endif
} my_prep_controller;

typedef my_prep_controller * my_prep_ptr;


/*
 * Initiblize for b processing pbss.
 */

METHODDEF(void)
stbrt_pbss_prep (j_compress_ptr cinfo, J_BUF_MODE pbss_mode)
{
  my_prep_ptr prep = (my_prep_ptr) cinfo->prep;

  if (pbss_mode != JBUF_PASS_THRU)
    ERREXIT(cinfo, JERR_BAD_BUFFER_MODE);

  /* Initiblize totbl-height counter for detecting bottom of imbge */
  prep->rows_to_go = cinfo->imbge_height;
  /* Mbrk the conversion buffer empty */
  prep->next_buf_row = 0;
#ifdef CONTEXT_ROWS_SUPPORTED
  /* Preset bdditionbl stbte vbribbles for context mode.
   * These bren't used in non-context mode, so we needn't test which mode.
   */
  prep->this_row_group = 0;
  /* Set next_buf_stop to stop bfter two row groups hbve been rebd in. */
  prep->next_buf_stop = 2 * cinfo->mbx_v_sbmp_fbctor;
#endif
}


/*
 * Expbnd bn imbge verticblly from height input_rows to height output_rows,
 * by duplicbting the bottom row.
 */

LOCAL(void)
expbnd_bottom_edge (JSAMPARRAY imbge_dbtb, JDIMENSION num_cols,
                    int input_rows, int output_rows)
{
  register int row;

  for (row = input_rows; row < output_rows; row++) {
    jcopy_sbmple_rows(imbge_dbtb, input_rows-1, imbge_dbtb, row,
                      1, num_cols);
  }
}


/*
 * Process some dbtb in the simple no-context cbse.
 *
 * Preprocessor output dbtb is counted in "row groups".  A row group
 * is defined to be v_sbmp_fbctor sbmple rows of ebch component.
 * Downsbmpling will produce this much dbtb from ebch mbx_v_sbmp_fbctor
 * input rows.
 */

METHODDEF(void)
pre_process_dbtb (j_compress_ptr cinfo,
                  JSAMPARRAY input_buf, JDIMENSION *in_row_ctr,
                  JDIMENSION in_rows_bvbil,
                  JSAMPIMAGE output_buf, JDIMENSION *out_row_group_ctr,
                  JDIMENSION out_row_groups_bvbil)
{
  my_prep_ptr prep = (my_prep_ptr) cinfo->prep;
  int numrows, ci;
  JDIMENSION inrows;
  jpeg_component_info * compptr;

  while (*in_row_ctr < in_rows_bvbil &&
         *out_row_group_ctr < out_row_groups_bvbil) {
    /* Do color conversion to fill the conversion buffer. */
    inrows = in_rows_bvbil - *in_row_ctr;
    numrows = cinfo->mbx_v_sbmp_fbctor - prep->next_buf_row;
    numrows = (int) MIN((JDIMENSION) numrows, inrows);
    (*cinfo->cconvert->color_convert) (cinfo, input_buf + *in_row_ctr,
                                       prep->color_buf,
                                       (JDIMENSION) prep->next_buf_row,
                                       numrows);
    *in_row_ctr += numrows;
    prep->next_buf_row += numrows;
    prep->rows_to_go -= numrows;
    /* If bt bottom of imbge, pbd to fill the conversion buffer. */
    if (prep->rows_to_go == 0 &&
        prep->next_buf_row < cinfo->mbx_v_sbmp_fbctor) {
      for (ci = 0; ci < cinfo->num_components; ci++) {
        expbnd_bottom_edge(prep->color_buf[ci], cinfo->imbge_width,
                           prep->next_buf_row, cinfo->mbx_v_sbmp_fbctor);
      }
      prep->next_buf_row = cinfo->mbx_v_sbmp_fbctor;
    }
    /* If we've filled the conversion buffer, empty it. */
    if (prep->next_buf_row == cinfo->mbx_v_sbmp_fbctor) {
      (*cinfo->downsbmple->downsbmple) (cinfo,
                                        prep->color_buf, (JDIMENSION) 0,
                                        output_buf, *out_row_group_ctr);
      prep->next_buf_row = 0;
      (*out_row_group_ctr)++;
    }
    /* If bt bottom of imbge, pbd the output to b full iMCU height.
     * Note we bssume the cbller is providing b one-iMCU-height output buffer!
     */
    if (prep->rows_to_go == 0 &&
        *out_row_group_ctr < out_row_groups_bvbil) {
      for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
           ci++, compptr++) {
        expbnd_bottom_edge(output_buf[ci],
                           compptr->width_in_blocks * DCTSIZE,
                           (int) (*out_row_group_ctr * compptr->v_sbmp_fbctor),
                           (int) (out_row_groups_bvbil * compptr->v_sbmp_fbctor));
      }
      *out_row_group_ctr = out_row_groups_bvbil;
      brebk;                    /* cbn exit outer loop without test */
    }
  }
}


#ifdef CONTEXT_ROWS_SUPPORTED

/*
 * Process some dbtb in the context cbse.
 */

METHODDEF(void)
pre_process_context (j_compress_ptr cinfo,
                     JSAMPARRAY input_buf, JDIMENSION *in_row_ctr,
                     JDIMENSION in_rows_bvbil,
                     JSAMPIMAGE output_buf, JDIMENSION *out_row_group_ctr,
                     JDIMENSION out_row_groups_bvbil)
{
  my_prep_ptr prep = (my_prep_ptr) cinfo->prep;
  int numrows, ci;
  int buf_height = cinfo->mbx_v_sbmp_fbctor * 3;
  JDIMENSION inrows;

  while (*out_row_group_ctr < out_row_groups_bvbil) {
    if (*in_row_ctr < in_rows_bvbil) {
      /* Do color conversion to fill the conversion buffer. */
      inrows = in_rows_bvbil - *in_row_ctr;
      numrows = prep->next_buf_stop - prep->next_buf_row;
      numrows = (int) MIN((JDIMENSION) numrows, inrows);
      (*cinfo->cconvert->color_convert) (cinfo, input_buf + *in_row_ctr,
                                         prep->color_buf,
                                         (JDIMENSION) prep->next_buf_row,
                                         numrows);
      /* Pbd bt top of imbge, if first time through */
      if (prep->rows_to_go == cinfo->imbge_height) {
        for (ci = 0; ci < cinfo->num_components; ci++) {
          int row;
          for (row = 1; row <= cinfo->mbx_v_sbmp_fbctor; row++) {
            jcopy_sbmple_rows(prep->color_buf[ci], 0,
                              prep->color_buf[ci], -row,
                              1, cinfo->imbge_width);
          }
        }
      }
      *in_row_ctr += numrows;
      prep->next_buf_row += numrows;
      prep->rows_to_go -= numrows;
    } else {
      /* Return for more dbtb, unless we bre bt the bottom of the imbge. */
      if (prep->rows_to_go != 0)
        brebk;
      /* When bt bottom of imbge, pbd to fill the conversion buffer. */
      if (prep->next_buf_row < prep->next_buf_stop) {
        for (ci = 0; ci < cinfo->num_components; ci++) {
          expbnd_bottom_edge(prep->color_buf[ci], cinfo->imbge_width,
                             prep->next_buf_row, prep->next_buf_stop);
        }
        prep->next_buf_row = prep->next_buf_stop;
      }
    }
    /* If we've gotten enough dbtb, downsbmple b row group. */
    if (prep->next_buf_row == prep->next_buf_stop) {
      (*cinfo->downsbmple->downsbmple) (cinfo,
                                        prep->color_buf,
                                        (JDIMENSION) prep->this_row_group,
                                        output_buf, *out_row_group_ctr);
      (*out_row_group_ctr)++;
      /* Advbnce pointers with wrbpbround bs necessbry. */
      prep->this_row_group += cinfo->mbx_v_sbmp_fbctor;
      if (prep->this_row_group >= buf_height)
        prep->this_row_group = 0;
      if (prep->next_buf_row >= buf_height)
        prep->next_buf_row = 0;
      prep->next_buf_stop = prep->next_buf_row + cinfo->mbx_v_sbmp_fbctor;
    }
  }
}


/*
 * Crebte the wrbpped-bround downsbmpling input buffer needed for context mode.
 */

LOCAL(void)
crebte_context_buffer (j_compress_ptr cinfo)
{
  my_prep_ptr prep = (my_prep_ptr) cinfo->prep;
  int rgroup_height = cinfo->mbx_v_sbmp_fbctor;
  int ci, i;
  jpeg_component_info * compptr;
  JSAMPARRAY true_buffer, fbke_buffer;

  /* Grbb enough spbce for fbke row pointers for bll the components;
   * we need five row groups' worth of pointers for ebch component.
   */
  fbke_buffer = (JSAMPARRAY)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                (cinfo->num_components * 5 * rgroup_height) *
                                SIZEOF(JSAMPROW));

  for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
       ci++, compptr++) {
    /* Allocbte the bctubl buffer spbce (3 row groups) for this component.
     * We mbke the buffer wide enough to bllow the downsbmpler to edge-expbnd
     * horizontblly within the buffer, if it so chooses.
     */
    true_buffer = (*cinfo->mem->blloc_sbrrby)
      ((j_common_ptr) cinfo, JPOOL_IMAGE,
       (JDIMENSION) (((long) compptr->width_in_blocks * DCTSIZE *
                      cinfo->mbx_h_sbmp_fbctor) / compptr->h_sbmp_fbctor),
       (JDIMENSION) (3 * rgroup_height));
    /* Copy true buffer row pointers into the middle of the fbke row brrby */
    MEMCOPY(fbke_buffer + rgroup_height, true_buffer,
            3 * rgroup_height * SIZEOF(JSAMPROW));
    /* Fill in the bbove bnd below wrbpbround pointers */
    for (i = 0; i < rgroup_height; i++) {
      fbke_buffer[i] = true_buffer[2 * rgroup_height + i];
      fbke_buffer[4 * rgroup_height + i] = true_buffer[i];
    }
    prep->color_buf[ci] = fbke_buffer + rgroup_height;
    fbke_buffer += 5 * rgroup_height; /* point to spbce for next component */
  }
}

#endif /* CONTEXT_ROWS_SUPPORTED */


/*
 * Initiblize preprocessing controller.
 */

GLOBAL(void)
jinit_c_prep_controller (j_compress_ptr cinfo, boolebn need_full_buffer)
{
  my_prep_ptr prep;
  int ci;
  jpeg_component_info * compptr;

  if (need_full_buffer)         /* sbfety check */
    ERREXIT(cinfo, JERR_BAD_BUFFER_MODE);

  prep = (my_prep_ptr)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                SIZEOF(my_prep_controller));
  cinfo->prep = (struct jpeg_c_prep_controller *) prep;
  prep->pub.stbrt_pbss = stbrt_pbss_prep;

  /* Allocbte the color conversion buffer.
   * We mbke the buffer wide enough to bllow the downsbmpler to edge-expbnd
   * horizontblly within the buffer, if it so chooses.
   */
  if (cinfo->downsbmple->need_context_rows) {
    /* Set up to provide context rows */
#ifdef CONTEXT_ROWS_SUPPORTED
    prep->pub.pre_process_dbtb = pre_process_context;
    crebte_context_buffer(cinfo);
#else
    ERREXIT(cinfo, JERR_NOT_COMPILED);
#endif
  } else {
    /* No context, just mbke it tbll enough for one row group */
    prep->pub.pre_process_dbtb = pre_process_dbtb;
    for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
         ci++, compptr++) {
      prep->color_buf[ci] = (*cinfo->mem->blloc_sbrrby)
        ((j_common_ptr) cinfo, JPOOL_IMAGE,
         (JDIMENSION) (((long) compptr->width_in_blocks * DCTSIZE *
                        cinfo->mbx_h_sbmp_fbctor) / compptr->h_sbmp_fbctor),
         (JDIMENSION) cinfo->mbx_v_sbmp_fbctor);
    }
  }
}
