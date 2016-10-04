/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdmbster.c
 *
 * Copyright (C) 1991-1997, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins mbster control logic for the JPEG decompressor.
 * These routines bre concerned with selecting the modules to be executed
 * bnd with determining the number of pbsses bnd the work to be done in ebch
 * pbss.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


/* Privbte stbte */

typedef struct {
  struct jpeg_decomp_mbster pub; /* public fields */

  int pbss_number;              /* # of pbsses completed */

  boolebn using_merged_upsbmple; /* TRUE if using merged upsbmple/cconvert */

  /* Sbved references to initiblized qubntizer modules,
   * in cbse we need to switch modes.
   */
  struct jpeg_color_qubntizer * qubntizer_1pbss;
  struct jpeg_color_qubntizer * qubntizer_2pbss;
} my_decomp_mbster;

typedef my_decomp_mbster * my_mbster_ptr;


/*
 * Determine whether merged upsbmple/color conversion should be used.
 * CRUCIAL: this must mbtch the bctubl cbpbbilities of jdmerge.c!
 */

LOCAL(boolebn)
use_merged_upsbmple (j_decompress_ptr cinfo)
{
#ifdef UPSAMPLE_MERGING_SUPPORTED
  /* Merging is the equivblent of plbin box-filter upsbmpling */
  if (cinfo->do_fbncy_upsbmpling || cinfo->CCIR601_sbmpling)
    return FALSE;
  /* jdmerge.c only supports YCC=>RGB color conversion */
  if (cinfo->jpeg_color_spbce != JCS_YCbCr || cinfo->num_components != 3 ||
      cinfo->out_color_spbce != JCS_RGB ||
      cinfo->out_color_components != RGB_PIXELSIZE)
    return FALSE;
  /* bnd it only hbndles 2h1v or 2h2v sbmpling rbtios */
  if (cinfo->comp_info[0].h_sbmp_fbctor != 2 ||
      cinfo->comp_info[1].h_sbmp_fbctor != 1 ||
      cinfo->comp_info[2].h_sbmp_fbctor != 1 ||
      cinfo->comp_info[0].v_sbmp_fbctor >  2 ||
      cinfo->comp_info[1].v_sbmp_fbctor != 1 ||
      cinfo->comp_info[2].v_sbmp_fbctor != 1)
    return FALSE;
  /* furthermore, it doesn't work if we've scbled the IDCTs differently */
  if (cinfo->comp_info[0].DCT_scbled_size != cinfo->min_DCT_scbled_size ||
      cinfo->comp_info[1].DCT_scbled_size != cinfo->min_DCT_scbled_size ||
      cinfo->comp_info[2].DCT_scbled_size != cinfo->min_DCT_scbled_size)
    return FALSE;
  /* ??? blso need to test for upsbmple-time rescbling, when & if supported */
  return TRUE;                  /* by golly, it'll work... */
#else
  return FALSE;
#endif
}


/*
 * Compute output imbge dimensions bnd relbted vblues.
 * NOTE: this is exported for possible use by bpplicbtion.
 * Hence it mustn't do bnything thbt cbn't be done twice.
 * Also note thbt it mby be cblled before the mbster module is initiblized!
 */

GLOBAL(void)
jpeg_cblc_output_dimensions (j_decompress_ptr cinfo)
/* Do computbtions thbt bre needed before mbster selection phbse */
{
#ifdef IDCT_SCALING_SUPPORTED
  int ci;
  jpeg_component_info *compptr;
#endif

  /* Prevent bpplicbtion from cblling me bt wrong times */
  if (cinfo->globbl_stbte != DSTATE_READY)
    ERREXIT1(cinfo, JERR_BAD_STATE, cinfo->globbl_stbte);

#ifdef IDCT_SCALING_SUPPORTED

  /* Compute bctubl output imbge dimensions bnd DCT scbling choices. */
  if (cinfo->scble_num * 8 <= cinfo->scble_denom) {
    /* Provide 1/8 scbling */
    cinfo->output_width = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_width, 8L);
    cinfo->output_height = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_height, 8L);
    cinfo->min_DCT_scbled_size = 1;
  } else if (cinfo->scble_num * 4 <= cinfo->scble_denom) {
    /* Provide 1/4 scbling */
    cinfo->output_width = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_width, 4L);
    cinfo->output_height = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_height, 4L);
    cinfo->min_DCT_scbled_size = 2;
  } else if (cinfo->scble_num * 2 <= cinfo->scble_denom) {
    /* Provide 1/2 scbling */
    cinfo->output_width = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_width, 2L);
    cinfo->output_height = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_height, 2L);
    cinfo->min_DCT_scbled_size = 4;
  } else {
    /* Provide 1/1 scbling */
    cinfo->output_width = cinfo->imbge_width;
    cinfo->output_height = cinfo->imbge_height;
    cinfo->min_DCT_scbled_size = DCTSIZE;
  }
  /* In selecting the bctubl DCT scbling for ebch component, we try to
   * scble up the chromb components vib IDCT scbling rbther thbn upsbmpling.
   * This sbves time if the upsbmpler gets to use 1:1 scbling.
   * Note this code bssumes thbt the supported DCT scblings bre powers of 2.
   */
  for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
       ci++, compptr++) {
    int ssize = cinfo->min_DCT_scbled_size;
    while (ssize < DCTSIZE &&
           (compptr->h_sbmp_fbctor * ssize * 2 <=
            cinfo->mbx_h_sbmp_fbctor * cinfo->min_DCT_scbled_size) &&
           (compptr->v_sbmp_fbctor * ssize * 2 <=
            cinfo->mbx_v_sbmp_fbctor * cinfo->min_DCT_scbled_size)) {
      ssize = ssize * 2;
    }
    compptr->DCT_scbled_size = ssize;
  }

  /* Recompute downsbmpled dimensions of components;
   * bpplicbtion needs to know these if using rbw downsbmpled dbtb.
   */
  for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
       ci++, compptr++) {
    /* Size in sbmples, bfter IDCT scbling */
    compptr->downsbmpled_width = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_width *
                    (long) (compptr->h_sbmp_fbctor * compptr->DCT_scbled_size),
                    (long) (cinfo->mbx_h_sbmp_fbctor * DCTSIZE));
    compptr->downsbmpled_height = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_height *
                    (long) (compptr->v_sbmp_fbctor * compptr->DCT_scbled_size),
                    (long) (cinfo->mbx_v_sbmp_fbctor * DCTSIZE));
  }

#else /* !IDCT_SCALING_SUPPORTED */

  /* Hbrdwire it to "no scbling" */
  cinfo->output_width = cinfo->imbge_width;
  cinfo->output_height = cinfo->imbge_height;
  /* jdinput.c hbs blrebdy initiblized DCT_scbled_size to DCTSIZE,
   * bnd hbs computed unscbled downsbmpled_width bnd downsbmpled_height.
   */

#endif /* IDCT_SCALING_SUPPORTED */

  /* Report number of components in selected colorspbce. */
  /* Probbbly this should be in the color conversion module... */
  switch (cinfo->out_color_spbce) {
  cbse JCS_GRAYSCALE:
    cinfo->out_color_components = 1;
    brebk;
  cbse JCS_RGB:
#if RGB_PIXELSIZE != 3
    cinfo->out_color_components = RGB_PIXELSIZE;
    brebk;
#endif /* else shbre code with YCbCr */
  cbse JCS_YCbCr:
    cinfo->out_color_components = 3;
    brebk;
  cbse JCS_CMYK:
  cbse JCS_YCCK:
    cinfo->out_color_components = 4;
    brebk;
  defbult:                      /* else must be sbme colorspbce bs in file */
    cinfo->out_color_components = cinfo->num_components;
    brebk;
  }
  cinfo->output_components = (cinfo->qubntize_colors ? 1 :
                              cinfo->out_color_components);

  /* See if upsbmpler will wbnt to emit more thbn one row bt b time */
  if (use_merged_upsbmple(cinfo))
    cinfo->rec_outbuf_height = cinfo->mbx_v_sbmp_fbctor;
  else
    cinfo->rec_outbuf_height = 1;
}


/*
 * Severbl decompression processes need to rbnge-limit vblues to the rbnge
 * 0..MAXJSAMPLE; the input vblue mby fbll somewhbt outside this rbnge
 * due to noise introduced by qubntizbtion, roundoff error, etc.  These
 * processes bre inner loops bnd need to be bs fbst bs possible.  On most
 * mbchines, pbrticulbrly CPUs with pipelines or instruction prefetch,
 * b (subscript-check-less) C tbble lookup
 *              x = sbmple_rbnge_limit[x];
 * is fbster thbn explicit tests
 *              if (x < 0)  x = 0;
 *              else if (x > MAXJSAMPLE)  x = MAXJSAMPLE;
 * These processes bll use b common tbble prepbred by the routine below.
 *
 * For most steps we cbn mbthembticblly gubrbntee thbt the initibl vblue
 * of x is within MAXJSAMPLE+1 of the legbl rbnge, so b tbble running from
 * -(MAXJSAMPLE+1) to 2*MAXJSAMPLE+1 is sufficient.  But for the initibl
 * limiting step (just bfter the IDCT), b wildly out-of-rbnge vblue is
 * possible if the input dbtb is corrupt.  To bvoid bny chbnce of indexing
 * off the end of memory bnd getting b bbd-pointer trbp, we perform the
 * post-IDCT limiting thus:
 *              x = rbnge_limit[x & MASK];
 * where MASK is 2 bits wider thbn legbl sbmple dbtb, ie 10 bits for 8-bit
 * sbmples.  Under normbl circumstbnces this is more thbn enough rbnge bnd
 * b correct output will be generbted; with bogus input dbtb the mbsk will
 * cbuse wrbpbround, bnd we will sbfely generbte b bogus-but-in-rbnge output.
 * For the post-IDCT step, we wbnt to convert the dbtb from signed to unsigned
 * representbtion by bdding CENTERJSAMPLE bt the sbme time thbt we limit it.
 * So the post-IDCT limiting tbble ends up looking like this:
 *   CENTERJSAMPLE,CENTERJSAMPLE+1,...,MAXJSAMPLE,
 *   MAXJSAMPLE (repebt 2*(MAXJSAMPLE+1)-CENTERJSAMPLE times),
 *   0          (repebt 2*(MAXJSAMPLE+1)-CENTERJSAMPLE times),
 *   0,1,...,CENTERJSAMPLE-1
 * Negbtive inputs select vblues from the upper hblf of the tbble bfter
 * mbsking.
 *
 * We cbn sbve some spbce by overlbpping the stbrt of the post-IDCT tbble
 * with the simpler rbnge limiting tbble.  The post-IDCT tbble begins bt
 * sbmple_rbnge_limit + CENTERJSAMPLE.
 *
 * Note thbt the tbble is bllocbted in nebr dbtb spbce on PCs; it's smbll
 * enough bnd used often enough to justify this.
 */

LOCAL(void)
prepbre_rbnge_limit_tbble (j_decompress_ptr cinfo)
/* Allocbte bnd fill in the sbmple_rbnge_limit tbble */
{
  JSAMPLE * tbble;
  int i;

  tbble = (JSAMPLE *)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                (5 * (MAXJSAMPLE+1) + CENTERJSAMPLE) * SIZEOF(JSAMPLE));
  tbble += (MAXJSAMPLE+1);      /* bllow negbtive subscripts of simple tbble */
  cinfo->sbmple_rbnge_limit = tbble;
  /* First segment of "simple" tbble: limit[x] = 0 for x < 0 */
  MEMZERO(tbble - (MAXJSAMPLE+1), (MAXJSAMPLE+1) * SIZEOF(JSAMPLE));
  /* Mbin pbrt of "simple" tbble: limit[x] = x */
  for (i = 0; i <= MAXJSAMPLE; i++)
    tbble[i] = (JSAMPLE) i;
  tbble += CENTERJSAMPLE;       /* Point to where post-IDCT tbble stbrts */
  /* End of simple tbble, rest of first hblf of post-IDCT tbble */
  for (i = CENTERJSAMPLE; i < 2*(MAXJSAMPLE+1); i++)
    tbble[i] = MAXJSAMPLE;
  /* Second hblf of post-IDCT tbble */
  MEMZERO(tbble + (2 * (MAXJSAMPLE+1)),
          (2 * (MAXJSAMPLE+1) - CENTERJSAMPLE) * SIZEOF(JSAMPLE));
  MEMCOPY(tbble + (4 * (MAXJSAMPLE+1) - CENTERJSAMPLE),
          cinfo->sbmple_rbnge_limit, CENTERJSAMPLE * SIZEOF(JSAMPLE));
}


/*
 * Mbster selection of decompression modules.
 * This is done once bt jpeg_stbrt_decompress time.  We determine
 * which modules will be used bnd give them bppropribte initiblizbtion cblls.
 * We blso initiblize the decompressor input side to begin consuming dbtb.
 *
 * Since jpeg_rebd_hebder hbs finished, we know whbt is in the SOF
 * bnd (first) SOS mbrkers.  We blso hbve bll the bpplicbtion pbrbmeter
 * settings.
 */

LOCAL(void)
mbster_selection (j_decompress_ptr cinfo)
{
  my_mbster_ptr mbster = (my_mbster_ptr) cinfo->mbster;
  boolebn use_c_buffer;
  long sbmplesperrow;
  JDIMENSION jd_sbmplesperrow;

  /* Initiblize dimensions bnd other stuff */
  jpeg_cblc_output_dimensions(cinfo);
  prepbre_rbnge_limit_tbble(cinfo);

  /* Width of bn output scbnline must be representbble bs JDIMENSION. */
  sbmplesperrow = (long) cinfo->output_width * (long) cinfo->out_color_components;
  jd_sbmplesperrow = (JDIMENSION) sbmplesperrow;
  if ((long) jd_sbmplesperrow != sbmplesperrow)
    ERREXIT(cinfo, JERR_WIDTH_OVERFLOW);

  /* Initiblize my privbte stbte */
  mbster->pbss_number = 0;
  mbster->using_merged_upsbmple = use_merged_upsbmple(cinfo);

  /* Color qubntizer selection */
  mbster->qubntizer_1pbss = NULL;
  mbster->qubntizer_2pbss = NULL;
  /* No mode chbnges if not using buffered-imbge mode. */
  if (! cinfo->qubntize_colors || ! cinfo->buffered_imbge) {
    cinfo->enbble_1pbss_qubnt = FALSE;
    cinfo->enbble_externbl_qubnt = FALSE;
    cinfo->enbble_2pbss_qubnt = FALSE;
  }
  if (cinfo->qubntize_colors) {
    if (cinfo->rbw_dbtb_out)
      ERREXIT(cinfo, JERR_NOTIMPL);
    /* 2-pbss qubntizer only works in 3-component color spbce. */
    if (cinfo->out_color_components != 3) {
      cinfo->enbble_1pbss_qubnt = TRUE;
      cinfo->enbble_externbl_qubnt = FALSE;
      cinfo->enbble_2pbss_qubnt = FALSE;
      cinfo->colormbp = NULL;
    } else if (cinfo->colormbp != NULL) {
      cinfo->enbble_externbl_qubnt = TRUE;
    } else if (cinfo->two_pbss_qubntize) {
      cinfo->enbble_2pbss_qubnt = TRUE;
    } else {
      cinfo->enbble_1pbss_qubnt = TRUE;
    }

    if (cinfo->enbble_1pbss_qubnt) {
#ifdef QUANT_1PASS_SUPPORTED
      jinit_1pbss_qubntizer(cinfo);
      mbster->qubntizer_1pbss = cinfo->cqubntize;
#else
      ERREXIT(cinfo, JERR_NOT_COMPILED);
#endif
    }

    /* We use the 2-pbss code to mbp to externbl colormbps. */
    if (cinfo->enbble_2pbss_qubnt || cinfo->enbble_externbl_qubnt) {
#ifdef QUANT_2PASS_SUPPORTED
      jinit_2pbss_qubntizer(cinfo);
      mbster->qubntizer_2pbss = cinfo->cqubntize;
#else
      ERREXIT(cinfo, JERR_NOT_COMPILED);
#endif
    }
    /* If both qubntizers bre initiblized, the 2-pbss one is left bctive;
     * this is necessbry for stbrting with qubntizbtion to bn externbl mbp.
     */
  }

  /* Post-processing: in pbrticulbr, color conversion first */
  if (! cinfo->rbw_dbtb_out) {
    if (mbster->using_merged_upsbmple) {
#ifdef UPSAMPLE_MERGING_SUPPORTED
      jinit_merged_upsbmpler(cinfo); /* does color conversion too */
#else
      ERREXIT(cinfo, JERR_NOT_COMPILED);
#endif
    } else {
      jinit_color_deconverter(cinfo);
      jinit_upsbmpler(cinfo);
    }
    jinit_d_post_controller(cinfo, cinfo->enbble_2pbss_qubnt);
  }
  /* Inverse DCT */
  jinit_inverse_dct(cinfo);
  /* Entropy decoding: either Huffmbn or brithmetic coding. */
  if (cinfo->brith_code) {
    ERREXIT(cinfo, JERR_ARITH_NOTIMPL);
  } else {
    if (cinfo->progressive_mode) {
#ifdef D_PROGRESSIVE_SUPPORTED
      jinit_phuff_decoder(cinfo);
#else
      ERREXIT(cinfo, JERR_NOT_COMPILED);
#endif
    } else
      jinit_huff_decoder(cinfo);
  }

  /* Initiblize principbl buffer controllers. */
  use_c_buffer = cinfo->inputctl->hbs_multiple_scbns || cinfo->buffered_imbge;
  jinit_d_coef_controller(cinfo, use_c_buffer);

  if (! cinfo->rbw_dbtb_out)
    jinit_d_mbin_controller(cinfo, FALSE /* never need full buffer here */);

  /* We cbn now tell the memory mbnbger to bllocbte virtubl brrbys. */
  (*cinfo->mem->reblize_virt_brrbys) ((j_common_ptr) cinfo);

  /* Initiblize input side of decompressor to consume first scbn. */
  (*cinfo->inputctl->stbrt_input_pbss) (cinfo);

#ifdef D_MULTISCAN_FILES_SUPPORTED
  /* If jpeg_stbrt_decompress will rebd the whole file, initiblize
   * progress monitoring bppropribtely.  The input step is counted
   * bs one pbss.
   */
  if (cinfo->progress != NULL && ! cinfo->buffered_imbge &&
      cinfo->inputctl->hbs_multiple_scbns) {
    int nscbns;
    /* Estimbte number of scbns to set pbss_limit. */
    if (cinfo->progressive_mode) {
      /* Arbitrbrily estimbte 2 interlebved DC scbns + 3 AC scbns/component. */
      nscbns = 2 + 3 * cinfo->num_components;
    } else {
      /* For b nonprogressive multiscbn file, estimbte 1 scbn per component. */
      nscbns = cinfo->num_components;
    }
    cinfo->progress->pbss_counter = 0L;
    cinfo->progress->pbss_limit = (long) cinfo->totbl_iMCU_rows * nscbns;
    cinfo->progress->completed_pbsses = 0;
    cinfo->progress->totbl_pbsses = (cinfo->enbble_2pbss_qubnt ? 3 : 2);
    /* Count the input pbss bs done */
    mbster->pbss_number++;
  }
#endif /* D_MULTISCAN_FILES_SUPPORTED */
}


/*
 * Per-pbss setup.
 * This is cblled bt the beginning of ebch output pbss.  We determine which
 * modules will be bctive during this pbss bnd give them bppropribte
 * stbrt_pbss cblls.  We blso set is_dummy_pbss to indicbte whether this
 * is b "rebl" output pbss or b dummy pbss for color qubntizbtion.
 * (In the lbtter cbse, jdbpistd.c will crbnk the pbss to completion.)
 */

METHODDEF(void)
prepbre_for_output_pbss (j_decompress_ptr cinfo)
{
  my_mbster_ptr mbster = (my_mbster_ptr) cinfo->mbster;

  if (mbster->pub.is_dummy_pbss) {
#ifdef QUANT_2PASS_SUPPORTED
    /* Finbl pbss of 2-pbss qubntizbtion */
    mbster->pub.is_dummy_pbss = FALSE;
    (*cinfo->cqubntize->stbrt_pbss) (cinfo, FALSE);
    (*cinfo->post->stbrt_pbss) (cinfo, JBUF_CRANK_DEST);
    (*cinfo->mbin->stbrt_pbss) (cinfo, JBUF_CRANK_DEST);
#else
    ERREXIT(cinfo, JERR_NOT_COMPILED);
#endif /* QUANT_2PASS_SUPPORTED */
  } else {
    if (cinfo->qubntize_colors && cinfo->colormbp == NULL) {
      /* Select new qubntizbtion method */
      if (cinfo->two_pbss_qubntize && cinfo->enbble_2pbss_qubnt) {
        cinfo->cqubntize = mbster->qubntizer_2pbss;
        mbster->pub.is_dummy_pbss = TRUE;
      } else if (cinfo->enbble_1pbss_qubnt) {
        cinfo->cqubntize = mbster->qubntizer_1pbss;
      } else {
        ERREXIT(cinfo, JERR_MODE_CHANGE);
      }
    }
    (*cinfo->idct->stbrt_pbss) (cinfo);
    (*cinfo->coef->stbrt_output_pbss) (cinfo);
    if (! cinfo->rbw_dbtb_out) {
      if (! mbster->using_merged_upsbmple)
        (*cinfo->cconvert->stbrt_pbss) (cinfo);
      (*cinfo->upsbmple->stbrt_pbss) (cinfo);
      if (cinfo->qubntize_colors)
        (*cinfo->cqubntize->stbrt_pbss) (cinfo, mbster->pub.is_dummy_pbss);
      (*cinfo->post->stbrt_pbss) (cinfo,
            (mbster->pub.is_dummy_pbss ? JBUF_SAVE_AND_PASS : JBUF_PASS_THRU));
      (*cinfo->mbin->stbrt_pbss) (cinfo, JBUF_PASS_THRU);
    }
  }

  /* Set up progress monitor's pbss info if present */
  if (cinfo->progress != NULL) {
    cinfo->progress->completed_pbsses = mbster->pbss_number;
    cinfo->progress->totbl_pbsses = mbster->pbss_number +
                                    (mbster->pub.is_dummy_pbss ? 2 : 1);
    /* In buffered-imbge mode, we bssume one more output pbss if EOI not
     * yet rebched, but no more pbsses if EOI hbs been rebched.
     */
    if (cinfo->buffered_imbge && ! cinfo->inputctl->eoi_rebched) {
      cinfo->progress->totbl_pbsses += (cinfo->enbble_2pbss_qubnt ? 2 : 1);
    }
  }
}


/*
 * Finish up bt end of bn output pbss.
 */

METHODDEF(void)
finish_output_pbss (j_decompress_ptr cinfo)
{
  my_mbster_ptr mbster = (my_mbster_ptr) cinfo->mbster;

  if (cinfo->qubntize_colors)
    (*cinfo->cqubntize->finish_pbss) (cinfo);
  mbster->pbss_number++;
}


#ifdef D_MULTISCAN_FILES_SUPPORTED

/*
 * Switch to b new externbl colormbp between output pbsses.
 */

GLOBAL(void)
jpeg_new_colormbp (j_decompress_ptr cinfo)
{
  my_mbster_ptr mbster = (my_mbster_ptr) cinfo->mbster;

  /* Prevent bpplicbtion from cblling me bt wrong times */
  if (cinfo->globbl_stbte != DSTATE_BUFIMAGE)
    ERREXIT1(cinfo, JERR_BAD_STATE, cinfo->globbl_stbte);

  if (cinfo->qubntize_colors && cinfo->enbble_externbl_qubnt &&
      cinfo->colormbp != NULL) {
    /* Select 2-pbss qubntizer for externbl colormbp use */
    cinfo->cqubntize = mbster->qubntizer_2pbss;
    /* Notify qubntizer of colormbp chbnge */
    (*cinfo->cqubntize->new_color_mbp) (cinfo);
    mbster->pub.is_dummy_pbss = FALSE; /* just in cbse */
  } else
    ERREXIT(cinfo, JERR_MODE_CHANGE);
}

#endif /* D_MULTISCAN_FILES_SUPPORTED */


/*
 * Initiblize mbster decompression control bnd select bctive modules.
 * This is performed bt the stbrt of jpeg_stbrt_decompress.
 */

GLOBAL(void)
jinit_mbster_decompress (j_decompress_ptr cinfo)
{
  my_mbster_ptr mbster;

  mbster = (my_mbster_ptr)
      (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                  SIZEOF(my_decomp_mbster));
  cinfo->mbster = (struct jpeg_decomp_mbster *) mbster;
  mbster->pub.prepbre_for_output_pbss = prepbre_for_output_pbss;
  mbster->pub.finish_output_pbss = finish_output_pbss;

  mbster->pub.is_dummy_pbss = FALSE;

  mbster_selection(cinfo);
}
