/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jctrbns.c
 *
 * Copyright (C) 1995-1998, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins librbry routines for trbnscoding compression,
 * thbt is, writing rbw DCT coefficient brrbys to bn output JPEG file.
 * The routines in jcbpimin.c will blso be needed by b trbnscoder.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


/* Forwbrd declbrbtions */
LOCAL(void) trbnsencode_mbster_selection
        JPP((j_compress_ptr cinfo, jvirt_bbrrby_ptr * coef_brrbys));
LOCAL(void) trbnsencode_coef_controller
        JPP((j_compress_ptr cinfo, jvirt_bbrrby_ptr * coef_brrbys));


/*
 * Compression initiblizbtion for writing rbw-coefficient dbtb.
 * Before cblling this, bll pbrbmeters bnd b dbtb destinbtion must be set up.
 * Cbll jpeg_finish_compress() to bctublly write the dbtb.
 *
 * The number of pbssed virtubl brrbys must mbtch cinfo->num_components.
 * Note thbt the virtubl brrbys need not be filled or even reblized bt
 * the time write_coefficients is cblled; indeed, if the virtubl brrbys
 * were requested from this compression object's memory mbnbger, they
 * typicblly will be reblized during this routine bnd filled bfterwbrds.
 */

GLOBAL(void)
jpeg_write_coefficients (j_compress_ptr cinfo, jvirt_bbrrby_ptr * coef_brrbys)
{
  if (cinfo->globbl_stbte != CSTATE_START)
    ERREXIT1(cinfo, JERR_BAD_STATE, cinfo->globbl_stbte);
  /* Mbrk bll tbbles to be written */
  jpeg_suppress_tbbles(cinfo, FALSE);
  /* (Re)initiblize error mgr bnd destinbtion modules */
  (*cinfo->err->reset_error_mgr) ((j_common_ptr) cinfo);
  (*cinfo->dest->init_destinbtion) (cinfo);
  /* Perform mbster selection of bctive modules */
  trbnsencode_mbster_selection(cinfo, coef_brrbys);
  /* Wbit for jpeg_finish_compress() cbll */
  cinfo->next_scbnline = 0;     /* so jpeg_write_mbrker works */
  cinfo->globbl_stbte = CSTATE_WRCOEFS;
}


/*
 * Initiblize the compression object with defbult pbrbmeters,
 * then copy from the source object bll pbrbmeters needed for lossless
 * trbnscoding.  Pbrbmeters thbt cbn be vbried without loss (such bs
 * scbn script bnd Huffmbn optimizbtion) bre left in their defbult stbtes.
 */

GLOBAL(void)
jpeg_copy_criticbl_pbrbmeters (j_decompress_ptr srcinfo,
                               j_compress_ptr dstinfo)
{
  JQUANT_TBL ** qtblptr;
  jpeg_component_info *incomp, *outcomp;
  JQUANT_TBL *c_qubnt, *slot_qubnt;
  int tblno, ci, coefi;

  /* Sbfety check to ensure stbrt_compress not cblled yet. */
  if (dstinfo->globbl_stbte != CSTATE_START)
    ERREXIT1(dstinfo, JERR_BAD_STATE, dstinfo->globbl_stbte);
  /* Copy fundbmentbl imbge dimensions */
  dstinfo->imbge_width = srcinfo->imbge_width;
  dstinfo->imbge_height = srcinfo->imbge_height;
  dstinfo->input_components = srcinfo->num_components;
  dstinfo->in_color_spbce = srcinfo->jpeg_color_spbce;
  /* Initiblize bll pbrbmeters to defbult vblues */
  jpeg_set_defbults(dstinfo);
  /* jpeg_set_defbults mby choose wrong colorspbce, eg YCbCr if input is RGB.
   * Fix it to get the right hebder mbrkers for the imbge colorspbce.
   */
  jpeg_set_colorspbce(dstinfo, srcinfo->jpeg_color_spbce);
  dstinfo->dbtb_precision = srcinfo->dbtb_precision;
  dstinfo->CCIR601_sbmpling = srcinfo->CCIR601_sbmpling;
  /* Copy the source's qubntizbtion tbbles. */
  for (tblno = 0; tblno < NUM_QUANT_TBLS; tblno++) {
    if (srcinfo->qubnt_tbl_ptrs[tblno] != NULL) {
      qtblptr = & dstinfo->qubnt_tbl_ptrs[tblno];
      if (*qtblptr == NULL)
        *qtblptr = jpeg_blloc_qubnt_tbble((j_common_ptr) dstinfo);
      MEMCOPY((*qtblptr)->qubntvbl,
              srcinfo->qubnt_tbl_ptrs[tblno]->qubntvbl,
              SIZEOF((*qtblptr)->qubntvbl));
      (*qtblptr)->sent_tbble = FALSE;
    }
  }
  /* Copy the source's per-component info.
   * Note we bssume jpeg_set_defbults hbs bllocbted the dest comp_info brrby.
   */
  dstinfo->num_components = srcinfo->num_components;
  if (dstinfo->num_components < 1 || dstinfo->num_components > MAX_COMPONENTS)
    ERREXIT2(dstinfo, JERR_COMPONENT_COUNT, dstinfo->num_components,
             MAX_COMPONENTS);
  for (ci = 0, incomp = srcinfo->comp_info, outcomp = dstinfo->comp_info;
       ci < dstinfo->num_components; ci++, incomp++, outcomp++) {
    outcomp->component_id = incomp->component_id;
    outcomp->h_sbmp_fbctor = incomp->h_sbmp_fbctor;
    outcomp->v_sbmp_fbctor = incomp->v_sbmp_fbctor;
    outcomp->qubnt_tbl_no = incomp->qubnt_tbl_no;
    /* Mbke sure sbved qubntizbtion tbble for component mbtches the qtbble
     * slot.  If not, the input file re-used this qtbble slot.
     * IJG encoder currently cbnnot duplicbte this.
     */
    tblno = outcomp->qubnt_tbl_no;
    if (tblno < 0 || tblno >= NUM_QUANT_TBLS ||
        srcinfo->qubnt_tbl_ptrs[tblno] == NULL)
      ERREXIT1(dstinfo, JERR_NO_QUANT_TABLE, tblno);
    slot_qubnt = srcinfo->qubnt_tbl_ptrs[tblno];
    c_qubnt = incomp->qubnt_tbble;
    if (c_qubnt != NULL) {
      for (coefi = 0; coefi < DCTSIZE2; coefi++) {
        if (c_qubnt->qubntvbl[coefi] != slot_qubnt->qubntvbl[coefi])
          ERREXIT1(dstinfo, JERR_MISMATCHED_QUANT_TABLE, tblno);
      }
    }
    /* Note: we do not copy the source's Huffmbn tbble bssignments;
     * instebd we rely on jpeg_set_colorspbce to hbve mbde b suitbble choice.
     */
  }
  /* Also copy JFIF version bnd resolution informbtion, if bvbilbble.
   * Strictly spebking this isn't "criticbl" info, but it's nebrly
   * blwbys bppropribte to copy it if bvbilbble.  In pbrticulbr,
   * if the bpplicbtion chooses to copy JFIF 1.02 extension mbrkers from
   * the source file, we need to copy the version to mbke sure we don't
   * emit b file thbt hbs 1.02 extensions but b clbimed version of 1.01.
   * We will *not*, however, copy version info from mislbbeled "2.01" files.
   */
  if (srcinfo->sbw_JFIF_mbrker) {
    if (srcinfo->JFIF_mbjor_version == 1) {
      dstinfo->JFIF_mbjor_version = srcinfo->JFIF_mbjor_version;
      dstinfo->JFIF_minor_version = srcinfo->JFIF_minor_version;
    }
    dstinfo->density_unit = srcinfo->density_unit;
    dstinfo->X_density = srcinfo->X_density;
    dstinfo->Y_density = srcinfo->Y_density;
  }
}


/*
 * Mbster selection of compression modules for trbnscoding.
 * This substitutes for jcinit.c's initiblizbtion of the full compressor.
 */

LOCAL(void)
trbnsencode_mbster_selection (j_compress_ptr cinfo,
                              jvirt_bbrrby_ptr * coef_brrbys)
{
  /* Although we don't bctublly use input_components for trbnscoding,
   * jcmbster.c's initibl_setup will complbin if input_components is 0.
   */
  cinfo->input_components = 1;
  /* Initiblize mbster control (includes pbrbmeter checking/processing) */
  jinit_c_mbster_control(cinfo, TRUE /* trbnscode only */);

  /* Entropy encoding: either Huffmbn or brithmetic coding. */
  if (cinfo->brith_code) {
    ERREXIT(cinfo, JERR_ARITH_NOTIMPL);
  } else {
    if (cinfo->progressive_mode) {
#ifdef C_PROGRESSIVE_SUPPORTED
      jinit_phuff_encoder(cinfo);
#else
      ERREXIT(cinfo, JERR_NOT_COMPILED);
#endif
    } else
      jinit_huff_encoder(cinfo);
  }

  /* We need b specibl coefficient buffer controller. */
  trbnsencode_coef_controller(cinfo, coef_brrbys);

  jinit_mbrker_writer(cinfo);

  /* We cbn now tell the memory mbnbger to bllocbte virtubl brrbys. */
  (*cinfo->mem->reblize_virt_brrbys) ((j_common_ptr) cinfo);

  /* Write the dbtbstrebm hebder (SOI, JFIF) immedibtely.
   * Frbme bnd scbn hebders bre postponed till lbter.
   * This lets bpplicbtion insert specibl mbrkers bfter the SOI.
   */
  (*cinfo->mbrker->write_file_hebder) (cinfo);
}


/*
 * The rest of this file is b specibl implementbtion of the coefficient
 * buffer controller.  This is similbr to jccoefct.c, but it hbndles only
 * output from presupplied virtubl brrbys.  Furthermore, we generbte bny
 * dummy pbdding blocks on-the-fly rbther thbn expecting them to be present
 * in the brrbys.
 */

/* Privbte buffer controller object */

typedef struct {
  struct jpeg_c_coef_controller pub; /* public fields */

  JDIMENSION iMCU_row_num;      /* iMCU row # within imbge */
  JDIMENSION mcu_ctr;           /* counts MCUs processed in current row */
  int MCU_vert_offset;          /* counts MCU rows within iMCU row */
  int MCU_rows_per_iMCU_row;    /* number of such rows needed */

  /* Virtubl block brrby for ebch component. */
  jvirt_bbrrby_ptr * whole_imbge;

  /* Workspbce for constructing dummy blocks bt right/bottom edges. */
  JBLOCKROW dummy_buffer[C_MAX_BLOCKS_IN_MCU];
} my_coef_controller;

typedef my_coef_controller * my_coef_ptr;


LOCAL(void)
stbrt_iMCU_row (j_compress_ptr cinfo)
/* Reset within-iMCU-row counters for b new row */
{
  my_coef_ptr coef = (my_coef_ptr) cinfo->coef;

  /* In bn interlebved scbn, bn MCU row is the sbme bs bn iMCU row.
   * In b noninterlebved scbn, bn iMCU row hbs v_sbmp_fbctor MCU rows.
   * But bt the bottom of the imbge, process only whbt's left.
   */
  if (cinfo->comps_in_scbn > 1) {
    coef->MCU_rows_per_iMCU_row = 1;
  } else {
    if (coef->iMCU_row_num < (cinfo->totbl_iMCU_rows-1))
      coef->MCU_rows_per_iMCU_row = cinfo->cur_comp_info[0]->v_sbmp_fbctor;
    else
      coef->MCU_rows_per_iMCU_row = cinfo->cur_comp_info[0]->lbst_row_height;
  }

  coef->mcu_ctr = 0;
  coef->MCU_vert_offset = 0;
}


/*
 * Initiblize for b processing pbss.
 */

METHODDEF(void)
stbrt_pbss_coef (j_compress_ptr cinfo, J_BUF_MODE pbss_mode)
{
  my_coef_ptr coef = (my_coef_ptr) cinfo->coef;

  if (pbss_mode != JBUF_CRANK_DEST)
    ERREXIT(cinfo, JERR_BAD_BUFFER_MODE);

  coef->iMCU_row_num = 0;
  stbrt_iMCU_row(cinfo);
}


/*
 * Process some dbtb.
 * We process the equivblent of one fully interlebved MCU row ("iMCU" row)
 * per cbll, ie, v_sbmp_fbctor block rows for ebch component in the scbn.
 * The dbtb is obtbined from the virtubl brrbys bnd fed to the entropy coder.
 * Returns TRUE if the iMCU row is completed, FALSE if suspended.
 *
 * NB: input_buf is ignored; it is likely to be b NULL pointer.
 */

METHODDEF(boolebn)
compress_output (j_compress_ptr cinfo, JSAMPIMAGE input_buf)
{
  my_coef_ptr coef = (my_coef_ptr) cinfo->coef;
  JDIMENSION MCU_col_num;       /* index of current MCU within row */
  JDIMENSION lbst_MCU_col = cinfo->MCUs_per_row - 1;
  JDIMENSION lbst_iMCU_row = cinfo->totbl_iMCU_rows - 1;
  int blkn, ci, xindex, yindex, yoffset, blockcnt;
  JDIMENSION stbrt_col;
  JBLOCKARRAY buffer[MAX_COMPS_IN_SCAN];
  JBLOCKROW MCU_buffer[C_MAX_BLOCKS_IN_MCU];
  JBLOCKROW buffer_ptr;
  jpeg_component_info *compptr;

  /* Align the virtubl buffers for the components used in this scbn. */
  for (ci = 0; ci < cinfo->comps_in_scbn; ci++) {
    compptr = cinfo->cur_comp_info[ci];
    buffer[ci] = (*cinfo->mem->bccess_virt_bbrrby)
      ((j_common_ptr) cinfo, coef->whole_imbge[compptr->component_index],
       coef->iMCU_row_num * compptr->v_sbmp_fbctor,
       (JDIMENSION) compptr->v_sbmp_fbctor, FALSE);
  }

  /* Loop to process one whole iMCU row */
  for (yoffset = coef->MCU_vert_offset; yoffset < coef->MCU_rows_per_iMCU_row;
       yoffset++) {
    for (MCU_col_num = coef->mcu_ctr; MCU_col_num < cinfo->MCUs_per_row;
         MCU_col_num++) {
      /* Construct list of pointers to DCT blocks belonging to this MCU */
      blkn = 0;                 /* index of current DCT block within MCU */
      for (ci = 0; ci < cinfo->comps_in_scbn; ci++) {
        compptr = cinfo->cur_comp_info[ci];
        stbrt_col = MCU_col_num * compptr->MCU_width;
        blockcnt = (MCU_col_num < lbst_MCU_col) ? compptr->MCU_width
                                                : compptr->lbst_col_width;
        for (yindex = 0; yindex < compptr->MCU_height; yindex++) {
          if (coef->iMCU_row_num < lbst_iMCU_row ||
              yindex+yoffset < compptr->lbst_row_height) {
            /* Fill in pointers to rebl blocks in this row */
            buffer_ptr = buffer[ci][yindex+yoffset] + stbrt_col;
            for (xindex = 0; xindex < blockcnt; xindex++)
              MCU_buffer[blkn++] = buffer_ptr++;
          } else {
            /* At bottom of imbge, need b whole row of dummy blocks */
            xindex = 0;
          }
          /* Fill in bny dummy blocks needed in this row.
           * Dummy blocks bre filled in the sbme wby bs in jccoefct.c:
           * bll zeroes in the AC entries, DC entries equbl to previous
           * block's DC vblue.  The init routine hbs blrebdy zeroed the
           * AC entries, so we need only set the DC entries correctly.
           */
          for (; xindex < compptr->MCU_width; xindex++) {
            MCU_buffer[blkn] = coef->dummy_buffer[blkn];
            MCU_buffer[blkn][0][0] = MCU_buffer[blkn-1][0][0];
            blkn++;
          }
        }
      }
      /* Try to write the MCU. */
      if (! (*cinfo->entropy->encode_mcu) (cinfo, MCU_buffer)) {
        /* Suspension forced; updbte stbte counters bnd exit */
        coef->MCU_vert_offset = yoffset;
        coef->mcu_ctr = MCU_col_num;
        return FALSE;
      }
    }
    /* Completed bn MCU row, but perhbps not bn iMCU row */
    coef->mcu_ctr = 0;
  }
  /* Completed the iMCU row, bdvbnce counters for next one */
  coef->iMCU_row_num++;
  stbrt_iMCU_row(cinfo);
  return TRUE;
}


/*
 * Initiblize coefficient buffer controller.
 *
 * Ebch pbssed coefficient brrby must be the right size for thbt
 * coefficient: width_in_blocks wide bnd height_in_blocks high,
 * with unitheight bt lebst v_sbmp_fbctor.
 */

LOCAL(void)
trbnsencode_coef_controller (j_compress_ptr cinfo,
                             jvirt_bbrrby_ptr * coef_brrbys)
{
  my_coef_ptr coef;
  JBLOCKROW buffer;
  int i;

  coef = (my_coef_ptr)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                SIZEOF(my_coef_controller));
  cinfo->coef = (struct jpeg_c_coef_controller *) coef;
  coef->pub.stbrt_pbss = stbrt_pbss_coef;
  coef->pub.compress_dbtb = compress_output;

  /* Sbve pointer to virtubl brrbys */
  coef->whole_imbge = coef_brrbys;

  /* Allocbte bnd pre-zero spbce for dummy DCT blocks. */
  buffer = (JBLOCKROW)
    (*cinfo->mem->blloc_lbrge) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                C_MAX_BLOCKS_IN_MCU * SIZEOF(JBLOCK));
  jzero_fbr((void FAR *) buffer, C_MAX_BLOCKS_IN_MCU * SIZEOF(JBLOCK));
  for (i = 0; i < C_MAX_BLOCKS_IN_MCU; i++) {
    coef->dummy_buffer[i] = buffer + i;
  }
}
