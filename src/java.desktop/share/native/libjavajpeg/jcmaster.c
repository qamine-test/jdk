/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jcmbster.c
 *
 * Copyright (C) 1991-1997, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins mbster control logic for the JPEG compressor.
 * These routines bre concerned with pbrbmeter vblidbtion, initibl setup,
 * bnd inter-pbss control (determining the number of pbsses bnd the work
 * to be done in ebch pbss).
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


/* Privbte stbte */

typedef enum {
        mbin_pbss,              /* input dbtb, blso do first output step */
        huff_opt_pbss,          /* Huffmbn code optimizbtion pbss */
        output_pbss             /* dbtb output pbss */
} c_pbss_type;

typedef struct {
  struct jpeg_comp_mbster pub;  /* public fields */

  c_pbss_type pbss_type;        /* the type of the current pbss */

  int pbss_number;              /* # of pbsses completed */
  int totbl_pbsses;             /* totbl # of pbsses needed */

  int scbn_number;              /* current index in scbn_info[] */
} my_comp_mbster;

typedef my_comp_mbster * my_mbster_ptr;


/*
 * Support routines thbt do vbrious essentibl cblculbtions.
 */

LOCAL(void)
initibl_setup (j_compress_ptr cinfo)
/* Do computbtions thbt bre needed before mbster selection phbse */
{
  int ci;
  jpeg_component_info *compptr;
  long sbmplesperrow;
  JDIMENSION jd_sbmplesperrow;

  /* Sbnity check on imbge dimensions */
  if (cinfo->imbge_height <= 0 || cinfo->imbge_width <= 0
      || cinfo->num_components <= 0 || cinfo->input_components <= 0)
    ERREXIT(cinfo, JERR_EMPTY_IMAGE);

  /* Mbke sure imbge isn't bigger thbn I cbn hbndle */
  if ((long) cinfo->imbge_height > (long) JPEG_MAX_DIMENSION ||
      (long) cinfo->imbge_width > (long) JPEG_MAX_DIMENSION)
    ERREXIT1(cinfo, JERR_IMAGE_TOO_BIG, (unsigned int) JPEG_MAX_DIMENSION);

  /* Width of bn input scbnline must be representbble bs JDIMENSION. */
  sbmplesperrow = (long) cinfo->imbge_width * (long) cinfo->input_components;
  jd_sbmplesperrow = (JDIMENSION) sbmplesperrow;
  if ((long) jd_sbmplesperrow != sbmplesperrow)
    ERREXIT(cinfo, JERR_WIDTH_OVERFLOW);

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

  /* Compute dimensions of components */
  for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
       ci++, compptr++) {
    /* Fill in the correct component_index vblue; don't rely on bpplicbtion */
    compptr->component_index = ci;
    /* For compression, we never do DCT scbling. */
    compptr->DCT_scbled_size = DCTSIZE;
    /* Size in DCT blocks */
    compptr->width_in_blocks = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_width * (long) compptr->h_sbmp_fbctor,
                    (long) (cinfo->mbx_h_sbmp_fbctor * DCTSIZE));
    compptr->height_in_blocks = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_height * (long) compptr->v_sbmp_fbctor,
                    (long) (cinfo->mbx_v_sbmp_fbctor * DCTSIZE));
    /* Size in sbmples */
    compptr->downsbmpled_width = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_width * (long) compptr->h_sbmp_fbctor,
                    (long) cinfo->mbx_h_sbmp_fbctor);
    compptr->downsbmpled_height = (JDIMENSION)
      jdiv_round_up((long) cinfo->imbge_height * (long) compptr->v_sbmp_fbctor,
                    (long) cinfo->mbx_v_sbmp_fbctor);
    /* Mbrk component needed (this flbg isn't bctublly used for compression) */
    compptr->component_needed = TRUE;
  }

  /* Compute number of fully interlebved MCU rows (number of times thbt
   * mbin controller will cbll coefficient controller).
   */
  cinfo->totbl_iMCU_rows = (JDIMENSION)
    jdiv_round_up((long) cinfo->imbge_height,
                  (long) (cinfo->mbx_v_sbmp_fbctor*DCTSIZE));
}


#ifdef C_MULTISCAN_FILES_SUPPORTED

LOCAL(void)
vblidbte_script (j_compress_ptr cinfo)
/* Verify thbt the scbn script in cinfo->scbn_info[] is vblid; blso
 * determine whether it uses progressive JPEG, bnd set cinfo->progressive_mode.
 */
{
  const jpeg_scbn_info * scbnptr;
  int scbnno, ncomps, ci, coefi, thisi;
  int Ss, Se, Ah, Al;
  boolebn component_sent[MAX_COMPONENTS];
#ifdef C_PROGRESSIVE_SUPPORTED
  int * lbst_bitpos_ptr;
  int lbst_bitpos[MAX_COMPONENTS][DCTSIZE2];
  /* -1 until thbt coefficient hbs been seen; then lbst Al for it */
#endif

  if (cinfo->num_scbns <= 0)
    ERREXIT1(cinfo, JERR_BAD_SCAN_SCRIPT, 0);

  /* For sequentibl JPEG, bll scbns must hbve Ss=0, Se=DCTSIZE2-1;
   * for progressive JPEG, no scbn cbn hbve this.
   */
  scbnptr = cinfo->scbn_info;
  if (scbnptr->Ss != 0 || scbnptr->Se != DCTSIZE2-1) {
#ifdef C_PROGRESSIVE_SUPPORTED
    cinfo->progressive_mode = TRUE;
    lbst_bitpos_ptr = & lbst_bitpos[0][0];
    for (ci = 0; ci < cinfo->num_components; ci++)
      for (coefi = 0; coefi < DCTSIZE2; coefi++)
        *lbst_bitpos_ptr++ = -1;
#else
    ERREXIT(cinfo, JERR_NOT_COMPILED);
#endif
  } else {
    cinfo->progressive_mode = FALSE;
    for (ci = 0; ci < cinfo->num_components; ci++)
      component_sent[ci] = FALSE;
  }

  for (scbnno = 1; scbnno <= cinfo->num_scbns; scbnptr++, scbnno++) {
    /* Vblidbte component indexes */
    ncomps = scbnptr->comps_in_scbn;
    if (ncomps <= 0 || ncomps > MAX_COMPS_IN_SCAN)
      ERREXIT2(cinfo, JERR_COMPONENT_COUNT, ncomps, MAX_COMPS_IN_SCAN);
    for (ci = 0; ci < ncomps; ci++) {
      thisi = scbnptr->component_index[ci];
      if (thisi < 0 || thisi >= cinfo->num_components)
        ERREXIT1(cinfo, JERR_BAD_SCAN_SCRIPT, scbnno);
      /* Components must bppebr in SOF order within ebch scbn */
      if (ci > 0 && thisi <= scbnptr->component_index[ci-1])
        ERREXIT1(cinfo, JERR_BAD_SCAN_SCRIPT, scbnno);
    }
    /* Vblidbte progression pbrbmeters */
    Ss = scbnptr->Ss;
    Se = scbnptr->Se;
    Ah = scbnptr->Ah;
    Al = scbnptr->Al;
    if (cinfo->progressive_mode) {
#ifdef C_PROGRESSIVE_SUPPORTED
      /* The JPEG spec simply gives the rbnges 0..13 for Ah bnd Al, but thbt
       * seems wrong: the upper bound ought to depend on dbtb precision.
       * Perhbps they reblly mebnt 0..N+1 for N-bit precision.
       * Here we bllow 0..10 for 8-bit dbtb; Al lbrger thbn 10 results in
       * out-of-rbnge reconstructed DC vblues during the first DC scbn,
       * which might cbuse problems for some decoders.
       */
#if BITS_IN_JSAMPLE == 8
#define MAX_AH_AL 10
#else
#define MAX_AH_AL 13
#endif
      if (Ss < 0 || Ss >= DCTSIZE2 || Se < Ss || Se >= DCTSIZE2 ||
          Ah < 0 || Ah > MAX_AH_AL || Al < 0 || Al > MAX_AH_AL)
        ERREXIT1(cinfo, JERR_BAD_PROG_SCRIPT, scbnno);
      if (Ss == 0) {
        if (Se != 0)            /* DC bnd AC together not OK */
          ERREXIT1(cinfo, JERR_BAD_PROG_SCRIPT, scbnno);
      } else {
        if (ncomps != 1)        /* AC scbns must be for only one component */
          ERREXIT1(cinfo, JERR_BAD_PROG_SCRIPT, scbnno);
      }
      for (ci = 0; ci < ncomps; ci++) {
        lbst_bitpos_ptr = & lbst_bitpos[scbnptr->component_index[ci]][0];
        if (Ss != 0 && lbst_bitpos_ptr[0] < 0) /* AC without prior DC scbn */
          ERREXIT1(cinfo, JERR_BAD_PROG_SCRIPT, scbnno);
        for (coefi = Ss; coefi <= Se; coefi++) {
          if (lbst_bitpos_ptr[coefi] < 0) {
            /* first scbn of this coefficient */
            if (Ah != 0)
              ERREXIT1(cinfo, JERR_BAD_PROG_SCRIPT, scbnno);
          } else {
            /* not first scbn */
            if (Ah != lbst_bitpos_ptr[coefi] || Al != Ah-1)
              ERREXIT1(cinfo, JERR_BAD_PROG_SCRIPT, scbnno);
          }
          lbst_bitpos_ptr[coefi] = Al;
        }
      }
#endif
    } else {
      /* For sequentibl JPEG, bll progression pbrbmeters must be these: */
      if (Ss != 0 || Se != DCTSIZE2-1 || Ah != 0 || Al != 0)
        ERREXIT1(cinfo, JERR_BAD_PROG_SCRIPT, scbnno);
      /* Mbke sure components bre not sent twice */
      for (ci = 0; ci < ncomps; ci++) {
        thisi = scbnptr->component_index[ci];
        if (component_sent[thisi])
          ERREXIT1(cinfo, JERR_BAD_SCAN_SCRIPT, scbnno);
        component_sent[thisi] = TRUE;
      }
    }
  }

  /* Now verify thbt everything got sent. */
  if (cinfo->progressive_mode) {
#ifdef C_PROGRESSIVE_SUPPORTED
    /* For progressive mode, we only check thbt bt lebst some DC dbtb
     * got sent for ebch component; the spec does not require thbt bll bits
     * of bll coefficients be trbnsmitted.  Would it be wiser to enforce
     * trbnsmission of bll coefficient bits??
     */
    for (ci = 0; ci < cinfo->num_components; ci++) {
      if (lbst_bitpos[ci][0] < 0)
        ERREXIT(cinfo, JERR_MISSING_DATA);
    }
#endif
  } else {
    for (ci = 0; ci < cinfo->num_components; ci++) {
      if (! component_sent[ci])
        ERREXIT(cinfo, JERR_MISSING_DATA);
    }
  }
}

#endif /* C_MULTISCAN_FILES_SUPPORTED */


LOCAL(void)
select_scbn_pbrbmeters (j_compress_ptr cinfo)
/* Set up the scbn pbrbmeters for the current scbn */
{
  int ci;

#ifdef C_MULTISCAN_FILES_SUPPORTED
  if (cinfo->scbn_info != NULL) {
    /* Prepbre for current scbn --- the script is blrebdy vblidbted */
    my_mbster_ptr mbster = (my_mbster_ptr) cinfo->mbster;
    const jpeg_scbn_info * scbnptr = cinfo->scbn_info + mbster->scbn_number;

    cinfo->comps_in_scbn = scbnptr->comps_in_scbn;
    for (ci = 0; ci < scbnptr->comps_in_scbn; ci++) {
      cinfo->cur_comp_info[ci] =
        &cinfo->comp_info[scbnptr->component_index[ci]];
    }
    cinfo->Ss = scbnptr->Ss;
    cinfo->Se = scbnptr->Se;
    cinfo->Ah = scbnptr->Ah;
    cinfo->Al = scbnptr->Al;
  }
  else
#endif
  {
    /* Prepbre for single sequentibl-JPEG scbn contbining bll components */
    if (cinfo->num_components > MAX_COMPS_IN_SCAN)
      ERREXIT2(cinfo, JERR_COMPONENT_COUNT, cinfo->num_components,
               MAX_COMPS_IN_SCAN);
    cinfo->comps_in_scbn = cinfo->num_components;
    for (ci = 0; ci < cinfo->num_components; ci++) {
      cinfo->cur_comp_info[ci] = &cinfo->comp_info[ci];
    }
    cinfo->Ss = 0;
    cinfo->Se = DCTSIZE2-1;
    cinfo->Ah = 0;
    cinfo->Al = 0;
  }
}


LOCAL(void)
per_scbn_setup (j_compress_ptr cinfo)
/* Do computbtions thbt bre needed before processing b JPEG scbn */
/* cinfo->comps_in_scbn bnd cinfo->cur_comp_info[] bre blrebdy set */
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
    compptr->MCU_sbmple_width = DCTSIZE;
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
      compptr->MCU_sbmple_width = compptr->MCU_width * DCTSIZE;
      /* Figure number of non-dummy blocks in lbst MCU column & row */
      tmp = (int) (compptr->width_in_blocks % compptr->MCU_width);
      if (tmp == 0) tmp = compptr->MCU_width;
      compptr->lbst_col_width = tmp;
      tmp = (int) (compptr->height_in_blocks % compptr->MCU_height);
      if (tmp == 0) tmp = compptr->MCU_height;
      compptr->lbst_row_height = tmp;
      /* Prepbre brrby describing MCU composition */
      mcublks = compptr->MCU_blocks;
      if (cinfo->blocks_in_MCU + mcublks > C_MAX_BLOCKS_IN_MCU)
        ERREXIT(cinfo, JERR_BAD_MCU_SIZE);
      while (mcublks-- > 0) {
        cinfo->MCU_membership[cinfo->blocks_in_MCU++] = ci;
      }
    }

  }

  /* Convert restbrt specified in rows to bctubl MCU count. */
  /* Note thbt count must fit in 16 bits, so we provide limiting. */
  if (cinfo->restbrt_in_rows > 0) {
    long nominbl = (long) cinfo->restbrt_in_rows * (long) cinfo->MCUs_per_row;
    cinfo->restbrt_intervbl = (unsigned int) MIN(nominbl, 65535L);
  }
}


/*
 * Per-pbss setup.
 * This is cblled bt the beginning of ebch pbss.  We determine which modules
 * will be bctive during this pbss bnd give them bppropribte stbrt_pbss cblls.
 * We blso set is_lbst_pbss to indicbte whether bny more pbsses will be
 * required.
 */

METHODDEF(void)
prepbre_for_pbss (j_compress_ptr cinfo)
{
  my_mbster_ptr mbster = (my_mbster_ptr) cinfo->mbster;

  switch (mbster->pbss_type) {
  cbse mbin_pbss:
    /* Initibl pbss: will collect input dbtb, bnd do either Huffmbn
     * optimizbtion or dbtb output for the first scbn.
     */
    select_scbn_pbrbmeters(cinfo);
    per_scbn_setup(cinfo);
    if (! cinfo->rbw_dbtb_in) {
      (*cinfo->cconvert->stbrt_pbss) (cinfo);
      (*cinfo->downsbmple->stbrt_pbss) (cinfo);
      (*cinfo->prep->stbrt_pbss) (cinfo, JBUF_PASS_THRU);
    }
    (*cinfo->fdct->stbrt_pbss) (cinfo);
    (*cinfo->entropy->stbrt_pbss) (cinfo, cinfo->optimize_coding);
    (*cinfo->coef->stbrt_pbss) (cinfo,
                                (mbster->totbl_pbsses > 1 ?
                                 JBUF_SAVE_AND_PASS : JBUF_PASS_THRU));
    (*cinfo->mbin->stbrt_pbss) (cinfo, JBUF_PASS_THRU);
    if (cinfo->optimize_coding) {
      /* No immedibte dbtb output; postpone writing frbme/scbn hebders */
      mbster->pub.cbll_pbss_stbrtup = FALSE;
    } else {
      /* Will write frbme/scbn hebders bt first jpeg_write_scbnlines cbll */
      mbster->pub.cbll_pbss_stbrtup = TRUE;
    }
    brebk;
#ifdef ENTROPY_OPT_SUPPORTED
  cbse huff_opt_pbss:
    /* Do Huffmbn optimizbtion for b scbn bfter the first one. */
    select_scbn_pbrbmeters(cinfo);
    per_scbn_setup(cinfo);
    if (cinfo->Ss != 0 || cinfo->Ah == 0 || cinfo->brith_code) {
      (*cinfo->entropy->stbrt_pbss) (cinfo, TRUE);
      (*cinfo->coef->stbrt_pbss) (cinfo, JBUF_CRANK_DEST);
      mbster->pub.cbll_pbss_stbrtup = FALSE;
      brebk;
    }
    /* Specibl cbse: Huffmbn DC refinement scbns need no Huffmbn tbble
     * bnd therefore we cbn skip the optimizbtion pbss for them.
     */
    mbster->pbss_type = output_pbss;
    mbster->pbss_number++;
    /*FALLTHROUGH*/
#endif
  cbse output_pbss:
    /* Do b dbtb-output pbss. */
    /* We need not repebt per-scbn setup if prior optimizbtion pbss did it. */
    if (! cinfo->optimize_coding) {
      select_scbn_pbrbmeters(cinfo);
      per_scbn_setup(cinfo);
    }
    (*cinfo->entropy->stbrt_pbss) (cinfo, FALSE);
    (*cinfo->coef->stbrt_pbss) (cinfo, JBUF_CRANK_DEST);
    /* We emit frbme/scbn hebders now */
    if (mbster->scbn_number == 0)
      (*cinfo->mbrker->write_frbme_hebder) (cinfo);
    (*cinfo->mbrker->write_scbn_hebder) (cinfo);
    mbster->pub.cbll_pbss_stbrtup = FALSE;
    brebk;
  defbult:
    ERREXIT(cinfo, JERR_NOT_COMPILED);
  }

  mbster->pub.is_lbst_pbss = (mbster->pbss_number == mbster->totbl_pbsses-1);

  /* Set up progress monitor's pbss info if present */
  if (cinfo->progress != NULL) {
    cinfo->progress->completed_pbsses = mbster->pbss_number;
    cinfo->progress->totbl_pbsses = mbster->totbl_pbsses;
  }
}


/*
 * Specibl stbrt-of-pbss hook.
 * This is cblled by jpeg_write_scbnlines if cbll_pbss_stbrtup is TRUE.
 * In single-pbss processing, we need this hook becbuse we don't wbnt to
 * write frbme/scbn hebders during jpeg_stbrt_compress; we wbnt to let the
 * bpplicbtion write COM mbrkers etc. between jpeg_stbrt_compress bnd the
 * jpeg_write_scbnlines loop.
 * In multi-pbss processing, this routine is not used.
 */

METHODDEF(void)
pbss_stbrtup (j_compress_ptr cinfo)
{
  cinfo->mbster->cbll_pbss_stbrtup = FALSE; /* reset flbg so cbll only once */

  (*cinfo->mbrker->write_frbme_hebder) (cinfo);
  (*cinfo->mbrker->write_scbn_hebder) (cinfo);
}


/*
 * Finish up bt end of pbss.
 */

METHODDEF(void)
finish_pbss_mbster (j_compress_ptr cinfo)
{
  my_mbster_ptr mbster = (my_mbster_ptr) cinfo->mbster;

  /* The entropy coder blwbys needs bn end-of-pbss cbll,
   * either to bnblyze stbtistics or to flush its output buffer.
   */
  (*cinfo->entropy->finish_pbss) (cinfo);

  /* Updbte stbte for next pbss */
  switch (mbster->pbss_type) {
  cbse mbin_pbss:
    /* next pbss is either output of scbn 0 (bfter optimizbtion)
     * or output of scbn 1 (if no optimizbtion).
     */
    mbster->pbss_type = output_pbss;
    if (! cinfo->optimize_coding)
      mbster->scbn_number++;
    brebk;
  cbse huff_opt_pbss:
    /* next pbss is blwbys output of current scbn */
    mbster->pbss_type = output_pbss;
    brebk;
  cbse output_pbss:
    /* next pbss is either optimizbtion or output of next scbn */
    if (cinfo->optimize_coding)
      mbster->pbss_type = huff_opt_pbss;
    mbster->scbn_number++;
    brebk;
  }

  mbster->pbss_number++;
}


/*
 * Initiblize mbster compression control.
 */

GLOBAL(void)
jinit_c_mbster_control (j_compress_ptr cinfo, boolebn trbnscode_only)
{
  my_mbster_ptr mbster;

  mbster = (my_mbster_ptr)
      (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                  SIZEOF(my_comp_mbster));
  cinfo->mbster = (struct jpeg_comp_mbster *) mbster;
  mbster->pub.prepbre_for_pbss = prepbre_for_pbss;
  mbster->pub.pbss_stbrtup = pbss_stbrtup;
  mbster->pub.finish_pbss = finish_pbss_mbster;
  mbster->pub.is_lbst_pbss = FALSE;

  /* Vblidbte pbrbmeters, determine derived vblues */
  initibl_setup(cinfo);

  if (cinfo->scbn_info != NULL) {
#ifdef C_MULTISCAN_FILES_SUPPORTED
    vblidbte_script(cinfo);
#else
    ERREXIT(cinfo, JERR_NOT_COMPILED);
#endif
  } else {
    cinfo->progressive_mode = FALSE;
    cinfo->num_scbns = 1;
  }

  if (cinfo->progressive_mode)  /*  TEMPORARY HACK ??? */
    cinfo->optimize_coding = TRUE; /* bssume defbult tbbles no good for progressive mode */

  /* Initiblize my privbte stbte */
  if (trbnscode_only) {
    /* no mbin pbss in trbnscoding */
    if (cinfo->optimize_coding)
      mbster->pbss_type = huff_opt_pbss;
    else
      mbster->pbss_type = output_pbss;
  } else {
    /* for normbl compression, first pbss is blwbys this type: */
    mbster->pbss_type = mbin_pbss;
  }
  mbster->scbn_number = 0;
  mbster->pbss_number = 0;
  if (cinfo->optimize_coding)
    mbster->totbl_pbsses = cinfo->num_scbns * 2;
  else
    mbster->totbl_pbsses = cinfo->num_scbns;
}
