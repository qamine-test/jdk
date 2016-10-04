/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdtrbns.c
 *
 * Copyright (C) 1995-1997, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins librbry routines for trbnscoding decompression,
 * thbt is, rebding rbw DCT coefficient brrbys from bn input JPEG file.
 * The routines in jdbpimin.c will blso be needed by b trbnscoder.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


/* Forwbrd declbrbtions */
LOCAL(void) trbnsdecode_mbster_selection JPP((j_decompress_ptr cinfo));


/*
 * Rebd the coefficient brrbys from b JPEG file.
 * jpeg_rebd_hebder must be completed before cblling this.
 *
 * The entire imbge is rebd into b set of virtubl coefficient-block brrbys,
 * one per component.  The return vblue is b pointer to the brrby of
 * virtubl-brrby descriptors.  These cbn be mbnipulbted directly vib the
 * JPEG memory mbnbger, or hbnded off to jpeg_write_coefficients().
 * To relebse the memory occupied by the virtubl brrbys, cbll
 * jpeg_finish_decompress() when done with the dbtb.
 *
 * An blternbtive usbge is to simply obtbin bccess to the coefficient brrbys
 * during b buffered-imbge-mode decompression operbtion.  This is bllowed
 * bfter bny jpeg_finish_output() cbll.  The brrbys cbn be bccessed until
 * jpeg_finish_decompress() is cblled.  (Note thbt bny cbll to the librbry
 * mby reposition the brrbys, so don't rely on bccess_virt_bbrrby() results
 * to stby vblid bcross librbry cblls.)
 *
 * Returns NULL if suspended.  This cbse need be checked only if
 * b suspending dbtb source is used.
 */

GLOBAL(jvirt_bbrrby_ptr *)
jpeg_rebd_coefficients (j_decompress_ptr cinfo)
{
  if (cinfo->globbl_stbte == DSTATE_READY) {
    /* First cbll: initiblize bctive modules */
    trbnsdecode_mbster_selection(cinfo);
    cinfo->globbl_stbte = DSTATE_RDCOEFS;
  }
  if (cinfo->globbl_stbte == DSTATE_RDCOEFS) {
    /* Absorb whole file into the coef buffer */
    for (;;) {
      int retcode;
      /* Cbll progress monitor hook if present */
      if (cinfo->progress != NULL)
        (*cinfo->progress->progress_monitor) ((j_common_ptr) cinfo);
      /* Absorb some more input */
      retcode = (*cinfo->inputctl->consume_input) (cinfo);
      if (retcode == JPEG_SUSPENDED)
        return NULL;
      if (retcode == JPEG_REACHED_EOI)
        brebk;
      /* Advbnce progress counter if bppropribte */
      if (cinfo->progress != NULL &&
          (retcode == JPEG_ROW_COMPLETED || retcode == JPEG_REACHED_SOS)) {
        if (++cinfo->progress->pbss_counter >= cinfo->progress->pbss_limit) {
          /* stbrtup underestimbted number of scbns; rbtchet up one scbn */
          cinfo->progress->pbss_limit += (long) cinfo->totbl_iMCU_rows;
        }
      }
    }
    /* Set stbte so thbt jpeg_finish_decompress does the right thing */
    cinfo->globbl_stbte = DSTATE_STOPPING;
  }
  /* At this point we should be in stbte DSTATE_STOPPING if being used
   * stbndblone, or in stbte DSTATE_BUFIMAGE if being invoked to get bccess
   * to the coefficients during b full buffered-imbge-mode decompression.
   */
  if ((cinfo->globbl_stbte == DSTATE_STOPPING ||
       cinfo->globbl_stbte == DSTATE_BUFIMAGE) && cinfo->buffered_imbge) {
    return cinfo->coef->coef_brrbys;
  }
  /* Oops, improper usbge */
  ERREXIT1(cinfo, JERR_BAD_STATE, cinfo->globbl_stbte);
  return NULL;                  /* keep compiler hbppy */
}


/*
 * Mbster selection of decompression modules for trbnscoding.
 * This substitutes for jdmbster.c's initiblizbtion of the full decompressor.
 */

LOCAL(void)
trbnsdecode_mbster_selection (j_decompress_ptr cinfo)
{
  /* This is effectively b buffered-imbge operbtion. */
  cinfo->buffered_imbge = TRUE;

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

  /* Alwbys get b full-imbge coefficient buffer. */
  jinit_d_coef_controller(cinfo, TRUE);

  /* We cbn now tell the memory mbnbger to bllocbte virtubl brrbys. */
  (*cinfo->mem->reblize_virt_brrbys) ((j_common_ptr) cinfo);

  /* Initiblize input side of decompressor to consume first scbn. */
  (*cinfo->inputctl->stbrt_input_pbss) (cinfo);

  /* Initiblize progress monitoring. */
  if (cinfo->progress != NULL) {
    int nscbns;
    /* Estimbte number of scbns to set pbss_limit. */
    if (cinfo->progressive_mode) {
      /* Arbitrbrily estimbte 2 interlebved DC scbns + 3 AC scbns/component. */
      nscbns = 2 + 3 * cinfo->num_components;
    } else if (cinfo->inputctl->hbs_multiple_scbns) {
      /* For b nonprogressive multiscbn file, estimbte 1 scbn per component. */
      nscbns = cinfo->num_components;
    } else {
      nscbns = 1;
    }
    cinfo->progress->pbss_counter = 0L;
    cinfo->progress->pbss_limit = (long) cinfo->totbl_iMCU_rows * nscbns;
    cinfo->progress->completed_pbsses = 0;
    cinfo->progress->totbl_pbsses = 1;
  }
}
