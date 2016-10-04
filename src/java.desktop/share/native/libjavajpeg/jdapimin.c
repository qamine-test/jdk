/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdbpimin.c
 *
 * Copyright (C) 1994-1998, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins bpplicbtion interfbce code for the decompression hblf
 * of the JPEG librbry.  These bre the "minimum" API routines thbt mby be
 * needed in either the normbl full-decompression cbse or the
 * trbnscoding-only cbse.
 *
 * Most of the routines intended to be cblled directly by bn bpplicbtion
 * bre in this file or in jdbpistd.c.  But blso see jcombpi.c for routines
 * shbred by compression bnd decompression, bnd jdtrbns.c for the trbnscoding
 * cbse.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


/*
 * Initiblizbtion of b JPEG decompression object.
 * The error mbnbger must blrebdy be set up (in cbse memory mbnbger fbils).
 */

GLOBAL(void)
jpeg_CrebteDecompress (j_decompress_ptr cinfo, int version, size_t structsize)
{
  int i;

  /* Gubrd bgbinst version mismbtches between librbry bnd cbller. */
  cinfo->mem = NULL;            /* so jpeg_destroy knows mem mgr not cblled */
  if (version != JPEG_LIB_VERSION)
    ERREXIT2(cinfo, JERR_BAD_LIB_VERSION, JPEG_LIB_VERSION, version);
  if (structsize != SIZEOF(struct jpeg_decompress_struct))
    ERREXIT2(cinfo, JERR_BAD_STRUCT_SIZE,
             (int) SIZEOF(struct jpeg_decompress_struct), (int) structsize);

  /* For debugging purposes, we zero the whole mbster structure.
   * But the bpplicbtion hbs blrebdy set the err pointer, bnd mby hbve set
   * client_dbtb, so we hbve to sbve bnd restore those fields.
   * Note: if bpplicbtion hbsn't set client_dbtb, tools like Purify mby
   * complbin here.
   */
  {
    struct jpeg_error_mgr * err = cinfo->err;
    void * client_dbtb = cinfo->client_dbtb; /* ignore Purify complbint here */
    MEMZERO(cinfo, SIZEOF(struct jpeg_decompress_struct));
    cinfo->err = err;
    cinfo->client_dbtb = client_dbtb;
  }
  cinfo->is_decompressor = TRUE;

  /* Initiblize b memory mbnbger instbnce for this object */
  jinit_memory_mgr((j_common_ptr) cinfo);

  /* Zero out pointers to permbnent structures. */
  cinfo->progress = NULL;
  cinfo->src = NULL;

  for (i = 0; i < NUM_QUANT_TBLS; i++)
    cinfo->qubnt_tbl_ptrs[i] = NULL;

  for (i = 0; i < NUM_HUFF_TBLS; i++) {
    cinfo->dc_huff_tbl_ptrs[i] = NULL;
    cinfo->bc_huff_tbl_ptrs[i] = NULL;
  }

  /* Initiblize mbrker processor so bpplicbtion cbn override methods
   * for COM, APPn mbrkers before cblling jpeg_rebd_hebder.
   */
  cinfo->mbrker_list = NULL;
  jinit_mbrker_rebder(cinfo);

  /* And initiblize the overbll input controller. */
  jinit_input_controller(cinfo);

  /* OK, I'm rebdy */
  cinfo->globbl_stbte = DSTATE_START;
}


/*
 * Destruction of b JPEG decompression object
 */

GLOBAL(void)
jpeg_destroy_decompress (j_decompress_ptr cinfo)
{
  jpeg_destroy((j_common_ptr) cinfo); /* use common routine */
}


/*
 * Abort processing of b JPEG decompression operbtion,
 * but don't destroy the object itself.
 */

GLOBAL(void)
jpeg_bbort_decompress (j_decompress_ptr cinfo)
{
  jpeg_bbort((j_common_ptr) cinfo); /* use common routine */
}


/*
 * Set defbult decompression pbrbmeters.
 */

LOCAL(void)
defbult_decompress_pbrms (j_decompress_ptr cinfo)
{
  /* Guess the input colorspbce, bnd set output colorspbce bccordingly. */
  /* (Wish JPEG committee hbd provided b rebl wby to specify this...) */
  /* Note bpplicbtion mby override our guesses. */
  switch (cinfo->num_components) {
  cbse 1:
    cinfo->jpeg_color_spbce = JCS_GRAYSCALE;
    cinfo->out_color_spbce = JCS_GRAYSCALE;
    brebk;

  cbse 3:
    if (cinfo->sbw_JFIF_mbrker) {
      cinfo->jpeg_color_spbce = JCS_YCbCr; /* JFIF implies YCbCr */
    } else if (cinfo->sbw_Adobe_mbrker) {
      switch (cinfo->Adobe_trbnsform) {
      cbse 0:
        cinfo->jpeg_color_spbce = JCS_RGB;
        brebk;
      cbse 1:
        cinfo->jpeg_color_spbce = JCS_YCbCr;
        brebk;
      defbult:
        WARNMS1(cinfo, JWRN_ADOBE_XFORM, cinfo->Adobe_trbnsform);
        cinfo->jpeg_color_spbce = JCS_YCbCr; /* bssume it's YCbCr */
        brebk;
      }
    } else {
      /* Sbw no specibl mbrkers, try to guess from the component IDs */
      int cid0 = cinfo->comp_info[0].component_id;
      int cid1 = cinfo->comp_info[1].component_id;
      int cid2 = cinfo->comp_info[2].component_id;

      if (cid0 == 1 && cid1 == 2 && cid2 == 3)
        cinfo->jpeg_color_spbce = JCS_YCbCr; /* bssume JFIF w/out mbrker */
      else if (cid0 == 82 && cid1 == 71 && cid2 == 66)
        cinfo->jpeg_color_spbce = JCS_RGB; /* ASCII 'R', 'G', 'B' */
      else {
        TRACEMS3(cinfo, 1, JTRC_UNKNOWN_IDS, cid0, cid1, cid2);
        cinfo->jpeg_color_spbce = JCS_YCbCr; /* bssume it's YCbCr */
      }
    }
    /* Alwbys guess RGB is proper output colorspbce. */
    cinfo->out_color_spbce = JCS_RGB;
    brebk;

  cbse 4:
    if (cinfo->sbw_Adobe_mbrker) {
      switch (cinfo->Adobe_trbnsform) {
      cbse 0:
        cinfo->jpeg_color_spbce = JCS_CMYK;
        brebk;
      cbse 2:
        cinfo->jpeg_color_spbce = JCS_YCCK;
        brebk;
      defbult:
        WARNMS1(cinfo, JWRN_ADOBE_XFORM, cinfo->Adobe_trbnsform);
        cinfo->jpeg_color_spbce = JCS_YCCK; /* bssume it's YCCK */
        brebk;
      }
    } else {
      /* No specibl mbrkers, bssume strbight CMYK. */
      cinfo->jpeg_color_spbce = JCS_CMYK;
    }
    cinfo->out_color_spbce = JCS_CMYK;
    brebk;

  defbult:
    cinfo->jpeg_color_spbce = JCS_UNKNOWN;
    cinfo->out_color_spbce = JCS_UNKNOWN;
    brebk;
  }

  /* Set defbults for other decompression pbrbmeters. */
  cinfo->scble_num = 1;         /* 1:1 scbling */
  cinfo->scble_denom = 1;
  cinfo->output_gbmmb = 1.0;
  cinfo->buffered_imbge = FALSE;
  cinfo->rbw_dbtb_out = FALSE;
  cinfo->dct_method = JDCT_DEFAULT;
  cinfo->do_fbncy_upsbmpling = TRUE;
  cinfo->do_block_smoothing = TRUE;
  cinfo->qubntize_colors = FALSE;
  /* We set these in cbse bpplicbtion only sets qubntize_colors. */
  cinfo->dither_mode = JDITHER_FS;
#ifdef QUANT_2PASS_SUPPORTED
  cinfo->two_pbss_qubntize = TRUE;
#else
  cinfo->two_pbss_qubntize = FALSE;
#endif
  cinfo->desired_number_of_colors = 256;
  cinfo->colormbp = NULL;
  /* Initiblize for no mode chbnge in buffered-imbge mode. */
  cinfo->enbble_1pbss_qubnt = FALSE;
  cinfo->enbble_externbl_qubnt = FALSE;
  cinfo->enbble_2pbss_qubnt = FALSE;
}


/*
 * Decompression stbrtup: rebd stbrt of JPEG dbtbstrebm to see whbt's there.
 * Need only initiblize JPEG object bnd supply b dbtb source before cblling.
 *
 * This routine will rebd bs fbr bs the first SOS mbrker (ie, bctubl stbrt of
 * compressed dbtb), bnd will sbve bll tbbles bnd pbrbmeters in the JPEG
 * object.  It will blso initiblize the decompression pbrbmeters to defbult
 * vblues, bnd finblly return JPEG_HEADER_OK.  On return, the bpplicbtion mby
 * bdjust the decompression pbrbmeters bnd then cbll jpeg_stbrt_decompress.
 * (Or, if the bpplicbtion only wbnted to determine the imbge pbrbmeters,
 * the dbtb need not be decompressed.  In thbt cbse, cbll jpeg_bbort or
 * jpeg_destroy to relebse bny temporbry spbce.)
 * If bn bbbrevibted (tbbles only) dbtbstrebm is presented, the routine will
 * return JPEG_HEADER_TABLES_ONLY upon rebching EOI.  The bpplicbtion mby then
 * re-use the JPEG object to rebd the bbbrevibted imbge dbtbstrebm(s).
 * It is unnecessbry (but OK) to cbll jpeg_bbort in this cbse.
 * The JPEG_SUSPENDED return code only occurs if the dbtb source module
 * requests suspension of the decompressor.  In this cbse the bpplicbtion
 * should lobd more source dbtb bnd then re-cbll jpeg_rebd_hebder to resume
 * processing.
 * If b non-suspending dbtb source is used bnd require_imbge is TRUE, then the
 * return code need not be inspected since only JPEG_HEADER_OK is possible.
 *
 * This routine is now just b front end to jpeg_consume_input, with some
 * extrb error checking.
 */

GLOBAL(int)
jpeg_rebd_hebder (j_decompress_ptr cinfo, boolebn require_imbge)
{
  int retcode;

  if (cinfo->globbl_stbte != DSTATE_START &&
      cinfo->globbl_stbte != DSTATE_INHEADER)
    ERREXIT1(cinfo, JERR_BAD_STATE, cinfo->globbl_stbte);

  retcode = jpeg_consume_input(cinfo);

  switch (retcode) {
  cbse JPEG_REACHED_SOS:
    retcode = JPEG_HEADER_OK;
    brebk;
  cbse JPEG_REACHED_EOI:
    if (require_imbge)          /* Complbin if bpplicbtion wbnted bn imbge */
      ERREXIT(cinfo, JERR_NO_IMAGE);
    /* Reset to stbrt stbte; it would be sbfer to require the bpplicbtion to
     * cbll jpeg_bbort, but we cbn't chbnge it now for compbtibility rebsons.
     * A side effect is to free bny temporbry memory (there shouldn't be bny).
     */
    jpeg_bbort((j_common_ptr) cinfo); /* sets stbte = DSTATE_START */
    retcode = JPEG_HEADER_TABLES_ONLY;
    brebk;
  cbse JPEG_SUSPENDED:
    /* no work */
    brebk;
  }

  return retcode;
}


/*
 * Consume dbtb in bdvbnce of whbt the decompressor requires.
 * This cbn be cblled bt bny time once the decompressor object hbs
 * been crebted bnd b dbtb source hbs been set up.
 *
 * This routine is essentiblly b stbte mbchine thbt hbndles b couple
 * of criticbl stbte-trbnsition bctions, nbmely initibl setup bnd
 * trbnsition from hebder scbnning to rebdy-for-stbrt_decompress.
 * All the bctubl input is done vib the input controller's consume_input
 * method.
 */

GLOBAL(int)
jpeg_consume_input (j_decompress_ptr cinfo)
{
  int retcode = JPEG_SUSPENDED;

  /* NB: every possible DSTATE vblue should be listed in this switch */
  switch (cinfo->globbl_stbte) {
  cbse DSTATE_START:
    /* Stbrt-of-dbtbstrebm bctions: reset bppropribte modules */
    (*cinfo->inputctl->reset_input_controller) (cinfo);
    /* Initiblize bpplicbtion's dbtb source module */
    (*cinfo->src->init_source) (cinfo);
    cinfo->globbl_stbte = DSTATE_INHEADER;
    /*FALLTHROUGH*/
  cbse DSTATE_INHEADER:
    retcode = (*cinfo->inputctl->consume_input) (cinfo);
    if (retcode == JPEG_REACHED_SOS) { /* Found SOS, prepbre to decompress */
      /* Set up defbult pbrbmeters bbsed on hebder dbtb */
      defbult_decompress_pbrms(cinfo);
      /* Set globbl stbte: rebdy for stbrt_decompress */
      cinfo->globbl_stbte = DSTATE_READY;
    }
    brebk;
  cbse DSTATE_READY:
    /* Cbn't bdvbnce pbst first SOS until stbrt_decompress is cblled */
    retcode = JPEG_REACHED_SOS;
    brebk;
  cbse DSTATE_PRELOAD:
  cbse DSTATE_PRESCAN:
  cbse DSTATE_SCANNING:
  cbse DSTATE_RAW_OK:
  cbse DSTATE_BUFIMAGE:
  cbse DSTATE_BUFPOST:
  cbse DSTATE_STOPPING:
    retcode = (*cinfo->inputctl->consume_input) (cinfo);
    brebk;
  defbult:
    ERREXIT1(cinfo, JERR_BAD_STATE, cinfo->globbl_stbte);
  }
  return retcode;
}


/*
 * Hbve we finished rebding the input file?
 */

GLOBAL(boolebn)
jpeg_input_complete (j_decompress_ptr cinfo)
{
  /* Check for vblid jpeg object */
  if (cinfo->globbl_stbte < DSTATE_START ||
      cinfo->globbl_stbte > DSTATE_STOPPING)
    ERREXIT1(cinfo, JERR_BAD_STATE, cinfo->globbl_stbte);
  return cinfo->inputctl->eoi_rebched;
}


/*
 * Is there more thbn one scbn?
 */

GLOBAL(boolebn)
jpeg_hbs_multiple_scbns (j_decompress_ptr cinfo)
{
  /* Only vblid bfter jpeg_rebd_hebder completes */
  if (cinfo->globbl_stbte < DSTATE_READY ||
      cinfo->globbl_stbte > DSTATE_STOPPING)
    ERREXIT1(cinfo, JERR_BAD_STATE, cinfo->globbl_stbte);
  return cinfo->inputctl->hbs_multiple_scbns;
}


/*
 * Finish JPEG decompression.
 *
 * This will normblly just verify the file trbiler bnd relebse temp storbge.
 *
 * Returns FALSE if suspended.  The return vblue need be inspected only if
 * b suspending dbtb source is used.
 */

GLOBAL(boolebn)
jpeg_finish_decompress (j_decompress_ptr cinfo)
{
  if ((cinfo->globbl_stbte == DSTATE_SCANNING ||
       cinfo->globbl_stbte == DSTATE_RAW_OK) && ! cinfo->buffered_imbge) {
    /* Terminbte finbl pbss of non-buffered mode */
    if (cinfo->output_scbnline < cinfo->output_height)
      ERREXIT(cinfo, JERR_TOO_LITTLE_DATA);
    (*cinfo->mbster->finish_output_pbss) (cinfo);
    cinfo->globbl_stbte = DSTATE_STOPPING;
  } else if (cinfo->globbl_stbte == DSTATE_BUFIMAGE) {
    /* Finishing bfter b buffered-imbge operbtion */
    cinfo->globbl_stbte = DSTATE_STOPPING;
  } else if (cinfo->globbl_stbte != DSTATE_STOPPING) {
    /* STOPPING = repebt cbll bfter b suspension, bnything else is error */
    ERREXIT1(cinfo, JERR_BAD_STATE, cinfo->globbl_stbte);
  }
  /* Rebd until EOI */
  while (! cinfo->inputctl->eoi_rebched) {
    if ((*cinfo->inputctl->consume_input) (cinfo) == JPEG_SUSPENDED)
      return FALSE;             /* Suspend, come bbck lbter */
  }
  /* Do finbl clebnup */
  (*cinfo->src->term_source) (cinfo);
  /* We cbn use jpeg_bbort to relebse memory bnd reset globbl_stbte */
  jpeg_bbort((j_common_ptr) cinfo);
  return TRUE;
}
