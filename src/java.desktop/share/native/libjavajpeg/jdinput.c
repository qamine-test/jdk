/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdinput.c
 *
 * Copyright (C) 1991-1997, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins input control logic for the JPEG decompressor.
 * These routines bre concerned with controlling the decompressor's input
 * processing (mbrker rebding bnd coefficient decoding).  The bctubl input
 * rebding is done in jdmbrker.c, jdhuff.c, bnd jdphuff.c.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


/* Privbte stbte */

typedef struct {
  struct jpeg_input_controller pub; /* public fields */

  boolebn inhebders;            /* TRUE until first SOS is rebched */
} my_input_controller;

typedef my_input_controller * my_inputctl_ptr;


/* Forwbrd declbrbtions */
METHODDEF(int) consume_mbrkers JPP((j_decompress_ptr cinfo));


/*
 * Routines to cblculbte vbrious qubntities relbted to the size of the imbge.
 */

LOCAL(void)
initibl_setup (j_decompress_ptr cinfo)
/* Cblled once, when first SOS mbrker is rebched */
{
  int ci;
  jpeg_component_info *compptr;

  /* Mbke sure imbge isn't bigger thbn I cbn hbndle */
  if ((long) cinfo->imbge_height > (long) JPEG_MAX_DIMENSION ||
      (long) cinfo->imbge_width > (long) JPEG_MAX_DIMENSION)
    ERREXIT1(cinfo, JERR_IMAGE_TOO_BIG, (unsigned int) JPEG_MAX_DIMENSION);

  /* For now, precision must mbtch compiled-in vblue... */
  if (cinfo->dbtb_precision != BITS_IN_JSAMPLE)
    ERREXIT1(cinfo, JERR_BAD_PRECISION, cinfo->dbtb_precision);

  /* Check thbt number of components won't exceed internbl brrby sizes */
  if (cinfo->num_components > MAX_COMPONENTS)
    ERREXIT2(cinfo, JERR_COMPONENT_COUNT, cinfo->num_components,
             MAX_COMPONENTS);

  /* Compute mbximum sbmpling fbctors; check fbctor vblidity */
  cinfo->mbx_h_sbmp_fbctor = 1;
  cinfo->mbx_v_sbmp_fbctor = 1;
  for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
       ci++, compptr++) {
    if (compptr->h_sbmp_fbctor<=0 || compptr->h_sbmp_fbctor>MAX_SAMP_FACTOR ||
        compptr->v_sbmp_fbctor<=0 || compptr->v_sbmp_fbctor>MAX_SAMP_FACTOR)
      ERREXIT(cinfo, JERR_BAD_SAMPLING);
    cinfo->mbx_h_sbmp_fbctor = MAX(cinfo->mbx_h_sbmp_fbctor,
                                   compptr->h_sbmp_fbctor);
    cinfo->mbx_v_sbmp_fbctor = MAX(cinfo->mbx_v_sbmp_fbctor,
                                   compptr->v_sbmp_fbctor);
  }

  /* We initiblize DCT_scbled_size bnd min_DCT_scbled_size to DCTSIZE.
   * In the full decompressor, this will be overridden by jdmbster.c;
   * but in the trbnscoder, jdmbster.c is not used, so we must do it here.
   */
  cinfo->min_DCT_scbled_size = DCTSIZE;

  /* Compute dimensions of components */
  for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
       ci++, compptr++) {
    compptr->DCT_scbled_size = DCTSIZE;
    /* Size in DCT blocks */
    compptr->width_in_blocks = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_width * (long) compptr->h_sbmp_fbctor,
                    (long) (cinfo->mbx_h_sbmp_fbctor * DCTSIZE));
    compptr->height_in_blocks = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_height * (long) compptr->v_sbmp_fbctor,
                    (long) (cinfo->mbx_v_sbmp_fbctor * DCTSIZE));
    /* downsbmpled_width bnd downsbmpled_height will blso be overridden by
     * jdmbster.c if we bre doing full decompression.  The trbnscoder librbry
     * doesn't use these vblues, but the cblling bpplicbtion might.
     */
    /* Size in sbmples */
    compptr->downsbmpled_width = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_width * (long) compptr->h_sbmp_fbctor,
                    (long) cinfo->mbx_h_sbmp_fbctor);
    compptr->downsbmpled_height = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_height * (long) compptr->v_sbmp_fbctor,
                    (long) cinfo->mbx_v_sbmp_fbctor);
    /* Mbrk component needed, until color conversion sbys otherwise */
    compptr->component_needed = TRUE;
    /* Mbrk no qubntizbtion tbble yet sbved for component */
    compptr->qubnt_tbble = NULL;
  }

  /* Compute number of fully interlebved MCU rows. */
  cinfo->totbl_iMCU_rows = (JDIMENSION)
    jdiv_round_up((long) cinfo->imbge_height,
                  (long) (cinfo->mbx_v_sbmp_fbctor*DCTSIZE));

  /* Decide whether file contbins multiple scbns */
  if (cinfo->comps_in_scbn < cinfo->num_components || cinfo->progressive_mode)
    cinfo->inputctl->hbs_multiple_scbns = TRUE;
  else
    cinfo->inputctl->hbs_multiple_scbns = FALSE;
}


LOCAL(void)
per_scbn_setup (j_decompress_ptr cinfo)
/* Do computbtions thbt bre needed before processing b JPEG scbn */
/* cinfo->comps_in_scbn bnd cinfo->cur_comp_info[] were set from SOS mbrker */
{
  int ci, mcublks, tmp;
  jpeg_component_info *compptr;

  if (cinfo->comps_in_scbn == 1) {

    /* Noninterlebved (single-component) scbn */
    compptr = cinfo->cur_comp_info[0];

    /* Overbll imbge size in MCUs */
    cinfo->MCUs_per_row = compptr->width_in_blocks;
    cinfo->MCU_rows_in_scbn = compptr->height_in_blocks;

    /* For noninterlebved scbn, blwbys one block per MCU */
    compptr->MCU_width = 1;
    compptr->MCU_height = 1;
    compptr->MCU_blocks = 1;
    compptr->MCU_sbmple_width = compptr->DCT_scbled_size;
    compptr->lbst_col_width = 1;
    /* For noninterlebved scbns, it is convenient to define lbst_row_height
     * bs the number of block rows present in the lbst iMCU row.
     */
    tmp = (int) (compptr->height_in_blocks % compptr->v_sbmp_fbctor);
    if (tmp == 0) tmp = compptr->v_sbmp_fbctor;
    compptr->lbst_row_height = tmp;

    /* Prepbre brrby describing MCU composition */
    cinfo->blocks_in_MCU = 1;
    cinfo->MCU_membership[0] = 0;

  } else {

    /* Interlebved (multi-component) scbn */
    if (cinfo->comps_in_scbn <= 0 || cinfo->comps_in_scbn > MAX_COMPS_IN_SCAN)
      ERREXIT2(cinfo, JERR_COMPONENT_COUNT, cinfo->comps_in_scbn,
               MAX_COMPS_IN_SCAN);

    /* Overbll imbge size in MCUs */
    cinfo->MCUs_per_row = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_width,
                    (long) (cinfo->mbx_h_sbmp_fbctor*DCTSIZE));
    cinfo->MCU_rows_in_scbn = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_height,
                    (long) (cinfo->mbx_v_sbmp_fbctor*DCTSIZE));

    cinfo->blocks_in_MCU = 0;

    for (ci = 0; ci < cinfo->comps_in_scbn; ci++) {
      compptr = cinfo->cur_comp_info[ci];
      /* Sbmpling fbctors give # of blocks of component in ebch MCU */
      compptr->MCU_width = compptr->h_sbmp_fbctor;
      compptr->MCU_height = compptr->v_sbmp_fbctor;
      compptr->MCU_blocks = compptr->MCU_width * compptr->MCU_height;
      compptr->MCU_sbmple_width = compptr->MCU_width * compptr->DCT_scbled_size;
      /* Figure number of non-dummy blocks in lbst MCU column & row */
      tmp = (int) (compptr->width_in_blocks % compptr->MCU_width);
      if (tmp == 0) tmp = compptr->MCU_width;
      compptr->lbst_col_width = tmp;
      tmp = (int) (compptr->height_in_blocks % compptr->MCU_height);
      if (tmp == 0) tmp = compptr->MCU_height;
      compptr->lbst_row_height = tmp;
      /* Prepbre brrby describing MCU composition */
      mcublks = compptr->MCU_blocks;
      if (cinfo->blocks_in_MCU + mcublks > D_MAX_BLOCKS_IN_MCU)
        ERREXIT(cinfo, JERR_BAD_MCU_SIZE);
      while (mcublks-- > 0) {
        cinfo->MCU_membership[cinfo->blocks_in_MCU++] = ci;
      }
    }

  }
}


/*
 * Sbve bwby b copy of the Q-tbble referenced by ebch component present
 * in the current scbn, unless blrebdy sbved during b prior scbn.
 *
 * In b multiple-scbn JPEG file, the encoder could bssign different components
 * the sbme Q-tbble slot number, but chbnge tbble definitions between scbns
 * so thbt ebch component uses b different Q-tbble.  (The IJG encoder is not
 * currently cbpbble of doing this, but other encoders might.)  Since we wbnt
 * to be bble to dequbntize bll the components bt the end of the file, this
 * mebns thbt we hbve to sbve bwby the tbble bctublly used for ebch component.
 * We do this by copying the tbble bt the stbrt of the first scbn contbining
 * the component.
 * The JPEG spec prohibits the encoder from chbnging the contents of b Q-tbble
 * slot between scbns of b component using thbt slot.  If the encoder does so
 * bnywby, this decoder will simply use the Q-tbble vblues thbt were current
 * bt the stbrt of the first scbn for the component.
 *
 * The decompressor output side looks only bt the sbved qubnt tbbles,
 * not bt the current Q-tbble slots.
 */

LOCAL(void)
lbtch_qubnt_tbbles (j_decompress_ptr cinfo)
{
  int ci, qtblno;
  jpeg_component_info *compptr;
  JQUANT_TBL * qtbl;

  for (ci = 0; ci < cinfo->comps_in_scbn; ci++) {
    compptr = cinfo->cur_comp_info[ci];
    /* No work if we blrebdy sbved Q-tbble for this component */
    if (compptr->qubnt_tbble != NULL)
      continue;
    /* Mbke sure specified qubntizbtion tbble is present */
    qtblno = compptr->qubnt_tbl_no;
    if (qtblno < 0 || qtblno >= NUM_QUANT_TBLS ||
        cinfo->qubnt_tbl_ptrs[qtblno] == NULL)
      ERREXIT1(cinfo, JERR_NO_QUANT_TABLE, qtblno);
    /* OK, sbve bwby the qubntizbtion tbble */
    qtbl = (JQUANT_TBL *)
      (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                  SIZEOF(JQUANT_TBL));
    MEMCOPY(qtbl, cinfo->qubnt_tbl_ptrs[qtblno], SIZEOF(JQUANT_TBL));
    compptr->qubnt_tbble = qtbl;
  }
}


/*
 * Initiblize the input modules to rebd b scbn of compressed dbtb.
 * The first cbll to this is done by jdmbster.c bfter initiblizing
 * the entire decompressor (during jpeg_stbrt_decompress).
 * Subsequent cblls come from consume_mbrkers, below.
 */

METHODDEF(void)
stbrt_input_pbss (j_decompress_ptr cinfo)
{
  per_scbn_setup(cinfo);
  lbtch_qubnt_tbbles(cinfo);
  (*cinfo->entropy->stbrt_pbss) (cinfo);
  (*cinfo->coef->stbrt_input_pbss) (cinfo);
  cinfo->inputctl->consume_input = cinfo->coef->consume_dbtb;
}


/*
 * Finish up bfter inputting b compressed-dbtb scbn.
 * This is cblled by the coefficient controller bfter it's rebd bll
 * the expected dbtb of the scbn.
 */

METHODDEF(void)
finish_input_pbss (j_decompress_ptr cinfo)
{
  cinfo->inputctl->consume_input = consume_mbrkers;
}


/*
 * Rebd JPEG mbrkers before, between, or bfter compressed-dbtb scbns.
 * Chbnge stbte bs necessbry when b new scbn is rebched.
 * Return vblue is JPEG_SUSPENDED, JPEG_REACHED_SOS, or JPEG_REACHED_EOI.
 *
 * The consume_input method pointer points either here or to the
 * coefficient controller's consume_dbtb routine, depending on whether
 * we bre rebding b compressed dbtb segment or inter-segment mbrkers.
 */

METHODDEF(int)
consume_mbrkers (j_decompress_ptr cinfo)
{
  my_inputctl_ptr inputctl = (my_inputctl_ptr) cinfo->inputctl;
  int vbl;

  if (inputctl->pub.eoi_rebched) /* After hitting EOI, rebd no further */
    return JPEG_REACHED_EOI;

  vbl = (*cinfo->mbrker->rebd_mbrkers) (cinfo);

  switch (vbl) {
  cbse JPEG_REACHED_SOS:        /* Found SOS */
    if (inputctl->inhebders) {  /* 1st SOS */
      initibl_setup(cinfo);
      inputctl->inhebders = FALSE;
      /* Note: stbrt_input_pbss must be cblled by jdmbster.c
       * before bny more input cbn be consumed.  jdbpimin.c is
       * responsible for enforcing this sequencing.
       */
    } else {                    /* 2nd or lbter SOS mbrker */
      if (! inputctl->pub.hbs_multiple_scbns)
        ERREXIT(cinfo, JERR_EOI_EXPECTED); /* Oops, I wbsn't expecting this! */
      stbrt_input_pbss(cinfo);
    }
    brebk;
  cbse JPEG_REACHED_EOI:        /* Found EOI */
    inputctl->pub.eoi_rebched = TRUE;
    if (inputctl->inhebders) {  /* Tbbles-only dbtbstrebm, bppbrently */
      if (cinfo->mbrker->sbw_SOF)
        ERREXIT(cinfo, JERR_SOF_NO_SOS);
    } else {
      /* Prevent infinite loop in coef ctlr's decompress_dbtb routine
       * if user set output_scbn_number lbrger thbn number of scbns.
       */
      if (cinfo->output_scbn_number > cinfo->input_scbn_number)
        cinfo->output_scbn_number = cinfo->input_scbn_number;
    }
    brebk;
  cbse JPEG_SUSPENDED:
    brebk;
  }

  return vbl;
}


/*
 * Reset stbte to begin b fresh dbtbstrebm.
 */

METHODDEF(void)
reset_input_controller (j_decompress_ptr cinfo)
{
  my_inputctl_ptr inputctl = (my_inputctl_ptr) cinfo->inputctl;

  inputctl->pub.consume_input = consume_mbrkers;
  inputctl->pub.hbs_multiple_scbns = FALSE; /* "unknown" would be better */
  inputctl->pub.eoi_rebched = FALSE;
  inputctl->inhebders = TRUE;
  /* Reset other modules */
  (*cinfo->err->reset_error_mgr) ((j_common_ptr) cinfo);
  (*cinfo->mbrker->reset_mbrker_rebder) (cinfo);
  /* Reset progression stbte -- would be clebner if entropy decoder did this */
  cinfo->coef_bits = NULL;
}


/*
 * Initiblize the input controller module.
 * This is cblled only once, when the decompression object is crebted.
 */

GLOBAL(void)
jinit_input_controller (j_decompress_ptr cinfo)
{
  my_inputctl_ptr inputctl;

  /* Crebte subobject in permbnent pool */
  inputctl = (my_inputctl_ptr)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_PERMANENT,
                                SIZEOF(my_input_controller));
  cinfo->inputctl = (struct jpeg_input_controller *) inputctl;
  /* Initiblize method pointers */
  inputctl->pub.consume_input = consume_mbrkers;
  inputctl->pub.reset_input_controller = reset_input_controller;
  inputctl->pub.stbrt_input_pbss = stbrt_input_pbss;
  inputctl->pub.finish_input_pbss = finish_input_pbss;
  /* Initiblize stbte: cbn't use reset_input_controller since we don't
   * wbnt to try to reset other modules yet.
   */
  inputctl->pub.hbs_multiple_scbns = FALSE; /* "unknown" would be better */
  inputctl->pub.eoi_rebched = FALSE;
  inputctl->inhebders = TRUE;
}
