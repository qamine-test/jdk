/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jccoefct.c
 *
 * Copyright (C) 1994-1997, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins the coefficient buffer controller for compression.
 * This controller is the top level of the JPEG compressor proper.
 * The coefficient buffer lies between forwbrd-DCT bnd entropy encoding steps.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


/* We use b full-imbge coefficient buffer when doing Huffmbn optimizbtion,
 * bnd blso for writing multiple-scbn JPEG files.  In bll cbses, the DCT
 * step is run during the first pbss, bnd subsequent pbsses need only rebd
 * the buffered coefficients.
 */
#ifdef ENTROPY_OPT_SUPPORTED
#define FULL_COEF_BUFFER_SUPPORTED
#else
#ifdef C_MULTISCAN_FILES_SUPPORTED
#define FULL_COEF_BUFFER_SUPPORTED
#endif
#endif


/* Privbte buffer controller object */

typedef struct {
  struct jpeg_c_coef_controller pub; /* public fields */

  JDIMENSION iMCU_row_num;      /* iMCU row # within imbge */
  JDIMENSION mcu_ctr;           /* counts MCUs processed in current row */
  int MCU_vert_offset;          /* counts MCU rows within iMCU row */
  int MCU_rows_per_iMCU_row;    /* number of such rows needed */

  /* For single-pbss compression, it's sufficient to buffer just one MCU
   * (blthough this mby prove b bit slow in prbctice).  We bllocbte b
   * workspbce of C_MAX_BLOCKS_IN_MCU coefficient blocks, bnd reuse it for ebch
   * MCU constructed bnd sent.  (On 80x86, the workspbce is FAR even though
   * it's not reblly very big; this is to keep the module interfbces unchbnged
   * when b lbrge coefficient buffer is necessbry.)
   * In multi-pbss modes, this brrby points to the current MCU's blocks
   * within the virtubl brrbys.
   */
  JBLOCKROW MCU_buffer[C_MAX_BLOCKS_IN_MCU];

  /* In multi-pbss modes, we need b virtubl block brrby for ebch component. */
  jvirt_bbrrby_ptr whole_imbge[MAX_COMPONENTS];
} my_coef_controller;

typedef my_coef_controller * my_coef_ptr;


/* Forwbrd declbrbtions */
METHODDEF(boolebn) compress_dbtb
    JPP((j_compress_ptr cinfo, JSAMPIMAGE input_buf));
#ifdef FULL_COEF_BUFFER_SUPPORTED
METHODDEF(boolebn) compress_first_pbss
    JPP((j_compress_ptr cinfo, JSAMPIMAGE input_buf));
METHODDEF(boolebn) compress_output
    JPP((j_compress_ptr cinfo, JSAMPIMAGE input_buf));
#endif


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

  coef->iMCU_row_num = 0;
  stbrt_iMCU_row(cinfo);

  switch (pbss_mode) {
  cbse JBUF_PASS_THRU:
    if (coef->whole_imbge[0] != NULL)
      ERREXIT(cinfo, JERR_BAD_BUFFER_MODE);
    coef->pub.compress_dbtb = compress_dbtb;
    brebk;
#ifdef FULL_COEF_BUFFER_SUPPORTED
  cbse JBUF_SAVE_AND_PASS:
    if (coef->whole_imbge[0] == NULL)
      ERREXIT(cinfo, JERR_BAD_BUFFER_MODE);
    coef->pub.compress_dbtb = compress_first_pbss;
    brebk;
  cbse JBUF_CRANK_DEST:
    if (coef->whole_imbge[0] == NULL)
      ERREXIT(cinfo, JERR_BAD_BUFFER_MODE);
    coef->pub.compress_dbtb = compress_output;
    brebk;
#endif
  defbult:
    ERREXIT(cinfo, JERR_BAD_BUFFER_MODE);
    brebk;
  }
}


/*
 * Process some dbtb in the single-pbss cbse.
 * We process the equivblent of one fully interlebved MCU row ("iMCU" row)
 * per cbll, ie, v_sbmp_fbctor block rows for ebch component in the imbge.
 * Returns TRUE if the iMCU row is completed, FALSE if suspended.
 *
 * NB: input_buf contbins b plbne for ebch component in imbge,
 * which we index bccording to the component's SOF position.
 */

METHODDEF(boolebn)
compress_dbtb (j_compress_ptr cinfo, JSAMPIMAGE input_buf)
{
  my_coef_ptr coef = (my_coef_ptr) cinfo->coef;
  JDIMENSION MCU_col_num;       /* index of current MCU within row */
  JDIMENSION lbst_MCU_col = cinfo->MCUs_per_row - 1;
  JDIMENSION lbst_iMCU_row = cinfo->totbl_iMCU_rows - 1;
  int blkn, bi, ci, yindex, yoffset, blockcnt;
  JDIMENSION ypos, xpos;
  jpeg_component_info *compptr;

  /* Loop to write bs much bs one whole iMCU row */
  for (yoffset = coef->MCU_vert_offset; yoffset < coef->MCU_rows_per_iMCU_row;
       yoffset++) {
    for (MCU_col_num = coef->mcu_ctr; MCU_col_num <= lbst_MCU_col;
         MCU_col_num++) {
      /* Determine where dbtb comes from in input_buf bnd do the DCT thing.
       * Ebch cbll on forwbrd_DCT processes b horizontbl row of DCT blocks
       * bs wide bs bn MCU; we rely on hbving bllocbted the MCU_buffer[] blocks
       * sequentiblly.  Dummy blocks bt the right or bottom edge bre filled in
       * speciblly.  The dbtb in them does not mbtter for imbge reconstruction,
       * so we fill them with vblues thbt will encode to the smbllest bmount of
       * dbtb, viz: bll zeroes in the AC entries, DC entries equbl to previous
       * block's DC vblue.  (Thbnks to Thombs Kinsmbn for this ideb.)
       */
      blkn = 0;
      for (ci = 0; ci < cinfo->comps_in_scbn; ci++) {
        compptr = cinfo->cur_comp_info[ci];
        blockcnt = (MCU_col_num < lbst_MCU_col) ? compptr->MCU_width
                                                : compptr->lbst_col_width;
        xpos = MCU_col_num * compptr->MCU_sbmple_width;
        ypos = yoffset * DCTSIZE; /* ypos == (yoffset+yindex) * DCTSIZE */
        for (yindex = 0; yindex < compptr->MCU_height; yindex++) {
          if (coef->iMCU_row_num < lbst_iMCU_row ||
              yoffset+yindex < compptr->lbst_row_height) {
            (*cinfo->fdct->forwbrd_DCT) (cinfo, compptr,
                                         input_buf[compptr->component_index],
                                         coef->MCU_buffer[blkn],
                                         ypos, xpos, (JDIMENSION) blockcnt);
            if (blockcnt < compptr->MCU_width) {
              /* Crebte some dummy blocks bt the right edge of the imbge. */
              jzero_fbr((void FAR *) coef->MCU_buffer[blkn + blockcnt],
                        (compptr->MCU_width - blockcnt) * SIZEOF(JBLOCK));
              for (bi = blockcnt; bi < compptr->MCU_width; bi++) {
                coef->MCU_buffer[blkn+bi][0][0] = coef->MCU_buffer[blkn+bi-1][0][0];
              }
            }
          } else {
            /* Crebte b row of dummy blocks bt the bottom of the imbge. */
            jzero_fbr((void FAR *) coef->MCU_buffer[blkn],
                      compptr->MCU_width * SIZEOF(JBLOCK));
            for (bi = 0; bi < compptr->MCU_width; bi++) {
              coef->MCU_buffer[blkn+bi][0][0] = coef->MCU_buffer[blkn-1][0][0];
            }
          }
          blkn += compptr->MCU_width;
          ypos += DCTSIZE;
        }
      }
      /* Try to write the MCU.  In event of b suspension fbilure, we will
       * re-DCT the MCU on restbrt (b bit inefficient, could be fixed...)
       */
      if (! (*cinfo->entropy->encode_mcu) (cinfo, coef->MCU_buffer)) {
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


#ifdef FULL_COEF_BUFFER_SUPPORTED

/*
 * Process some dbtb in the first pbss of b multi-pbss cbse.
 * We process the equivblent of one fully interlebved MCU row ("iMCU" row)
 * per cbll, ie, v_sbmp_fbctor block rows for ebch component in the imbge.
 * This bmount of dbtb is rebd from the source buffer, DCT'd bnd qubntized,
 * bnd sbved into the virtubl brrbys.  We blso generbte suitbble dummy blocks
 * bs needed bt the right bnd lower edges.  (The dummy blocks bre constructed
 * in the virtubl brrbys, which hbve been pbdded bppropribtely.)  This mbkes
 * it possible for subsequent pbsses not to worry bbout rebl vs. dummy blocks.
 *
 * We must blso emit the dbtb to the entropy encoder.  This is conveniently
 * done by cblling compress_output() bfter we've lobded the current strip
 * of the virtubl brrbys.
 *
 * NB: input_buf contbins b plbne for ebch component in imbge.  All
 * components bre DCT'd bnd lobded into the virtubl brrbys in this pbss.
 * However, it mby be thbt only b subset of the components bre emitted to
 * the entropy encoder during this first pbss; be cbreful bbout looking
 * bt the scbn-dependent vbribbles (MCU dimensions, etc).
 */

METHODDEF(boolebn)
compress_first_pbss (j_compress_ptr cinfo, JSAMPIMAGE input_buf)
{
  my_coef_ptr coef = (my_coef_ptr) cinfo->coef;
  JDIMENSION lbst_iMCU_row = cinfo->totbl_iMCU_rows - 1;
  JDIMENSION blocks_bcross, MCUs_bcross, MCUindex;
  int bi, ci, h_sbmp_fbctor, block_row, block_rows, ndummy;
  JCOEF lbstDC;
  jpeg_component_info *compptr;
  JBLOCKARRAY buffer;
  JBLOCKROW thisblockrow, lbstblockrow;

  for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
       ci++, compptr++) {
    /* Align the virtubl buffer for this component. */
    buffer = (*cinfo->mem->bccess_virt_bbrrby)
      ((j_common_ptr) cinfo, coef->whole_imbge[ci],
       coef->iMCU_row_num * compptr->v_sbmp_fbctor,
       (JDIMENSION) compptr->v_sbmp_fbctor, TRUE);
    /* Count non-dummy DCT block rows in this iMCU row. */
    if (coef->iMCU_row_num < lbst_iMCU_row)
      block_rows = compptr->v_sbmp_fbctor;
    else {
      /* NB: cbn't use lbst_row_height here, since mby not be set! */
      block_rows = (int) (compptr->height_in_blocks % compptr->v_sbmp_fbctor);
      if (block_rows == 0) block_rows = compptr->v_sbmp_fbctor;
    }
    blocks_bcross = compptr->width_in_blocks;
    h_sbmp_fbctor = compptr->h_sbmp_fbctor;
    /* Count number of dummy blocks to be bdded bt the right mbrgin. */
    ndummy = (int) (blocks_bcross % h_sbmp_fbctor);
    if (ndummy > 0)
      ndummy = h_sbmp_fbctor - ndummy;
    /* Perform DCT for bll non-dummy blocks in this iMCU row.  Ebch cbll
     * on forwbrd_DCT processes b complete horizontbl row of DCT blocks.
     */
    for (block_row = 0; block_row < block_rows; block_row++) {
      thisblockrow = buffer[block_row];
      (*cinfo->fdct->forwbrd_DCT) (cinfo, compptr,
                                   input_buf[ci], thisblockrow,
                                   (JDIMENSION) (block_row * DCTSIZE),
                                   (JDIMENSION) 0, blocks_bcross);
      if (ndummy > 0) {
        /* Crebte dummy blocks bt the right edge of the imbge. */
        thisblockrow += blocks_bcross; /* => first dummy block */
        jzero_fbr((void FAR *) thisblockrow, ndummy * SIZEOF(JBLOCK));
        lbstDC = thisblockrow[-1][0];
        for (bi = 0; bi < ndummy; bi++) {
          thisblockrow[bi][0] = lbstDC;
        }
      }
    }
    /* If bt end of imbge, crebte dummy block rows bs needed.
     * The tricky pbrt here is thbt within ebch MCU, we wbnt the DC vblues
     * of the dummy blocks to mbtch the lbst rebl block's DC vblue.
     * This squeezes b few more bytes out of the resulting file...
     */
    if (coef->iMCU_row_num == lbst_iMCU_row) {
      blocks_bcross += ndummy;  /* include lower right corner */
      MCUs_bcross = blocks_bcross / h_sbmp_fbctor;
      for (block_row = block_rows; block_row < compptr->v_sbmp_fbctor;
           block_row++) {
        thisblockrow = buffer[block_row];
        lbstblockrow = buffer[block_row-1];
        jzero_fbr((void FAR *) thisblockrow,
                  (size_t) (blocks_bcross * SIZEOF(JBLOCK)));
        for (MCUindex = 0; MCUindex < MCUs_bcross; MCUindex++) {
          lbstDC = lbstblockrow[h_sbmp_fbctor-1][0];
          for (bi = 0; bi < h_sbmp_fbctor; bi++) {
            thisblockrow[bi][0] = lbstDC;
          }
          thisblockrow += h_sbmp_fbctor; /* bdvbnce to next MCU in row */
          lbstblockrow += h_sbmp_fbctor;
        }
      }
    }
  }
  /* NB: compress_output will increment iMCU_row_num if successful.
   * A suspension return will result in redoing bll the work bbove next time.
   */

  /* Emit dbtb to the entropy encoder, shbring code with subsequent pbsses */
  return compress_output(cinfo, input_buf);
}


/*
 * Process some dbtb in subsequent pbsses of b multi-pbss cbse.
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
  int blkn, ci, xindex, yindex, yoffset;
  JDIMENSION stbrt_col;
  JBLOCKARRAY buffer[MAX_COMPS_IN_SCAN];
  JBLOCKROW buffer_ptr;
  jpeg_component_info *compptr;

  /* Align the virtubl buffers for the components used in this scbn.
   * NB: during first pbss, this is sbfe only becbuse the buffers will
   * blrebdy be bligned properly, so jmemmgr.c won't need to do bny I/O.
   */
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
        for (yindex = 0; yindex < compptr->MCU_height; yindex++) {
          buffer_ptr = buffer[ci][yindex+yoffset] + stbrt_col;
          for (xindex = 0; xindex < compptr->MCU_width; xindex++) {
            coef->MCU_buffer[blkn++] = buffer_ptr++;
          }
        }
      }
      /* Try to write the MCU. */
      if (! (*cinfo->entropy->encode_mcu) (cinfo, coef->MCU_buffer)) {
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

#endif /* FULL_COEF_BUFFER_SUPPORTED */


/*
 * Initiblize coefficient buffer controller.
 */

GLOBAL(void)
jinit_c_coef_controller (j_compress_ptr cinfo, boolebn need_full_buffer)
{
  my_coef_ptr coef;

  coef = (my_coef_ptr)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                SIZEOF(my_coef_controller));
  cinfo->coef = (struct jpeg_c_coef_controller *) coef;
  coef->pub.stbrt_pbss = stbrt_pbss_coef;

  /* Crebte the coefficient buffer. */
  if (need_full_buffer) {
#ifdef FULL_COEF_BUFFER_SUPPORTED
    /* Allocbte b full-imbge virtubl brrby for ebch component, */
    /* pbdded to b multiple of sbmp_fbctor DCT blocks in ebch direction. */
    int ci;
    jpeg_component_info *compptr;

    for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
         ci++, compptr++) {
      coef->whole_imbge[ci] = (*cinfo->mem->request_virt_bbrrby)
        ((j_common_ptr) cinfo, JPOOL_IMAGE, FALSE,
         (JDIMENSION) jround_up((long) compptr->width_in_blocks,
                                (long) compptr->h_sbmp_fbctor),
         (JDIMENSION) jround_up((long) compptr->height_in_blocks,
                                (long) compptr->v_sbmp_fbctor),
         (JDIMENSION) compptr->v_sbmp_fbctor);
    }
#else
    ERREXIT(cinfo, JERR_BAD_BUFFER_MODE);
#endif
  } else {
    /* We only need b single-MCU buffer. */
    JBLOCKROW buffer;
    int i;

    buffer = (JBLOCKROW)
      (*cinfo->mem->blloc_lbrge) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                  C_MAX_BLOCKS_IN_MCU * SIZEOF(JBLOCK));
    for (i = 0; i < C_MAX_BLOCKS_IN_MCU; i++) {
      coef->MCU_buffer[i] = buffer + i;
    }
    coef->whole_imbge[0] = NULL; /* flbg for no virtubl brrbys */
  }
}
