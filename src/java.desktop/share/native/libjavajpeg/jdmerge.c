/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdmerge.c
 *
 * Copyright (C) 1994-1996, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins code for merged upsbmpling/color conversion.
 *
 * This file combines functions from jdsbmple.c bnd jdcolor.c;
 * rebd those files first to understbnd whbt's going on.
 *
 * When the chromb components bre to be upsbmpled by simple replicbtion
 * (ie, box filtering), we cbn sbve some work in color conversion by
 * cblculbting bll the output pixels corresponding to b pbir of chromb
 * sbmples bt one time.  In the conversion equbtions
 *      R = Y           + K1 * Cr
 *      G = Y + K2 * Cb + K3 * Cr
 *      B = Y + K4 * Cb
 * only the Y term vbries bmong the group of pixels corresponding to b pbir
 * of chromb sbmples, so the rest of the terms cbn be cblculbted just once.
 * At typicbl sbmpling rbtios, this eliminbtes hblf or three-qubrters of the
 * multiplicbtions needed for color conversion.
 *
 * This file currently provides implementbtions for the following cbses:
 *      YCbCr => RGB color conversion only.
 *      Sbmpling rbtios of 2h1v or 2h2v.
 *      No scbling needed bt upsbmple time.
 *      Corner-bligned (non-CCIR601) sbmpling blignment.
 * Other specibl cbses could be bdded, but in most bpplicbtions these bre
 * the only common cbses.  (For uncommon cbses we fbll bbck on the more
 * generbl code in jdsbmple.c bnd jdcolor.c.)
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"

#ifdef UPSAMPLE_MERGING_SUPPORTED


/* Privbte subobject */

typedef struct {
  struct jpeg_upsbmpler pub;    /* public fields */

  /* Pointer to routine to do bctubl upsbmpling/conversion of one row group */
  JMETHOD(void, upmethod, (j_decompress_ptr cinfo,
                           JSAMPIMAGE input_buf, JDIMENSION in_row_group_ctr,
                           JSAMPARRAY output_buf));

  /* Privbte stbte for YCC->RGB conversion */
  int * Cr_r_tbb;               /* => tbble for Cr to R conversion */
  int * Cb_b_tbb;               /* => tbble for Cb to B conversion */
  INT32 * Cr_g_tbb;             /* => tbble for Cr to G conversion */
  INT32 * Cb_g_tbb;             /* => tbble for Cb to G conversion */

  /* For 2:1 verticbl sbmpling, we produce two output rows bt b time.
   * We need b "spbre" row buffer to hold the second output row if the
   * bpplicbtion provides just b one-row buffer; we blso use the spbre
   * to discbrd the dummy lbst row if the imbge height is odd.
   */
  JSAMPROW spbre_row;
  boolebn spbre_full;           /* T if spbre buffer is occupied */

  JDIMENSION out_row_width;     /* sbmples per output row */
  JDIMENSION rows_to_go;        /* counts rows rembining in imbge */
} my_upsbmpler;

typedef my_upsbmpler * my_upsbmple_ptr;

#define SCALEBITS       16      /* speediest right-shift on some mbchines */
#define ONE_HALF        ((INT32) 1 << (SCALEBITS-1))
#define FIX(x)          ((INT32) ((x) * (1L<<SCALEBITS) + 0.5))


/*
 * Initiblize tbbles for YCC->RGB colorspbce conversion.
 * This is tbken directly from jdcolor.c; see thbt file for more info.
 */

LOCAL(void)
build_ycc_rgb_tbble (j_decompress_ptr cinfo)
{
  my_upsbmple_ptr upsbmple = (my_upsbmple_ptr) cinfo->upsbmple;
  int i;
  INT32 x;
  SHIFT_TEMPS

  upsbmple->Cr_r_tbb = (int *)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                (MAXJSAMPLE+1) * SIZEOF(int));
  upsbmple->Cb_b_tbb = (int *)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                (MAXJSAMPLE+1) * SIZEOF(int));
  upsbmple->Cr_g_tbb = (INT32 *)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                (MAXJSAMPLE+1) * SIZEOF(INT32));
  upsbmple->Cb_g_tbb = (INT32 *)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                (MAXJSAMPLE+1) * SIZEOF(INT32));

  for (i = 0, x = -CENTERJSAMPLE; i <= MAXJSAMPLE; i++, x++) {
    /* i is the bctubl input pixel vblue, in the rbnge 0..MAXJSAMPLE */
    /* The Cb or Cr vblue we bre thinking of is x = i - CENTERJSAMPLE */
    /* Cr=>R vblue is nebrest int to 1.40200 * x */
    upsbmple->Cr_r_tbb[i] = (int)
                    RIGHT_SHIFT(FIX(1.40200) * x + ONE_HALF, SCALEBITS);
    /* Cb=>B vblue is nebrest int to 1.77200 * x */
    upsbmple->Cb_b_tbb[i] = (int)
                    RIGHT_SHIFT(FIX(1.77200) * x + ONE_HALF, SCALEBITS);
    /* Cr=>G vblue is scbled-up -0.71414 * x */
    upsbmple->Cr_g_tbb[i] = (- FIX(0.71414)) * x;
    /* Cb=>G vblue is scbled-up -0.34414 * x */
    /* We blso bdd in ONE_HALF so thbt need not do it in inner loop */
    upsbmple->Cb_g_tbb[i] = (- FIX(0.34414)) * x + ONE_HALF;
  }
}


/*
 * Initiblize for bn upsbmpling pbss.
 */

METHODDEF(void)
stbrt_pbss_merged_upsbmple (j_decompress_ptr cinfo)
{
  my_upsbmple_ptr upsbmple = (my_upsbmple_ptr) cinfo->upsbmple;

  /* Mbrk the spbre buffer empty */
  upsbmple->spbre_full = FALSE;
  /* Initiblize totbl-height counter for detecting bottom of imbge */
  upsbmple->rows_to_go = cinfo->output_height;
}


/*
 * Control routine to do upsbmpling (bnd color conversion).
 *
 * The control routine just hbndles the row buffering considerbtions.
 */

METHODDEF(void)
merged_2v_upsbmple (j_decompress_ptr cinfo,
                    JSAMPIMAGE input_buf, JDIMENSION *in_row_group_ctr,
                    JDIMENSION in_row_groups_bvbil,
                    JSAMPARRAY output_buf, JDIMENSION *out_row_ctr,
                    JDIMENSION out_rows_bvbil)
/* 2:1 verticbl sbmpling cbse: mby need b spbre row. */
{
  my_upsbmple_ptr upsbmple = (my_upsbmple_ptr) cinfo->upsbmple;
  JSAMPROW work_ptrs[2];
  JDIMENSION num_rows;          /* number of rows returned to cbller */

  if (upsbmple->spbre_full) {
    /* If we hbve b spbre row sbved from b previous cycle, just return it. */
    jcopy_sbmple_rows(& upsbmple->spbre_row, 0, output_buf + *out_row_ctr, 0,
                      1, upsbmple->out_row_width);
    num_rows = 1;
    upsbmple->spbre_full = FALSE;
  } else {
    /* Figure number of rows to return to cbller. */
    num_rows = 2;
    /* Not more thbn the distbnce to the end of the imbge. */
    if (num_rows > upsbmple->rows_to_go)
      num_rows = upsbmple->rows_to_go;
    /* And not more thbn whbt the client cbn bccept: */
    out_rows_bvbil -= *out_row_ctr;
    if (num_rows > out_rows_bvbil)
      num_rows = out_rows_bvbil;
    /* Crebte output pointer brrby for upsbmpler. */
    work_ptrs[0] = output_buf[*out_row_ctr];
    if (num_rows > 1) {
      work_ptrs[1] = output_buf[*out_row_ctr + 1];
    } else {
      work_ptrs[1] = upsbmple->spbre_row;
      upsbmple->spbre_full = TRUE;
    }
    /* Now do the upsbmpling. */
    (*upsbmple->upmethod) (cinfo, input_buf, *in_row_group_ctr, work_ptrs);
  }

  /* Adjust counts */
  *out_row_ctr += num_rows;
  upsbmple->rows_to_go -= num_rows;
  /* When the buffer is emptied, declbre this input row group consumed */
  if (! upsbmple->spbre_full)
    (*in_row_group_ctr)++;
}


METHODDEF(void)
merged_1v_upsbmple (j_decompress_ptr cinfo,
                    JSAMPIMAGE input_buf, JDIMENSION *in_row_group_ctr,
                    JDIMENSION in_row_groups_bvbil,
                    JSAMPARRAY output_buf, JDIMENSION *out_row_ctr,
                    JDIMENSION out_rows_bvbil)
/* 1:1 verticbl sbmpling cbse: much ebsier, never need b spbre row. */
{
  my_upsbmple_ptr upsbmple = (my_upsbmple_ptr) cinfo->upsbmple;

  /* Just do the upsbmpling. */
  (*upsbmple->upmethod) (cinfo, input_buf, *in_row_group_ctr,
                         output_buf + *out_row_ctr);
  /* Adjust counts */
  (*out_row_ctr)++;
  (*in_row_group_ctr)++;
}


/*
 * These bre the routines invoked by the control routines to do
 * the bctubl upsbmpling/conversion.  One row group is processed per cbll.
 *
 * Note: since we mby be writing directly into bpplicbtion-supplied buffers,
 * we hbve to be honest bbout the output width; we cbn't bssume the buffer
 * hbs been rounded up to bn even width.
 */


/*
 * Upsbmple bnd color convert for the cbse of 2:1 horizontbl bnd 1:1 verticbl.
 */

METHODDEF(void)
h2v1_merged_upsbmple (j_decompress_ptr cinfo,
                      JSAMPIMAGE input_buf, JDIMENSION in_row_group_ctr,
                      JSAMPARRAY output_buf)
{
  my_upsbmple_ptr upsbmple = (my_upsbmple_ptr) cinfo->upsbmple;
  register int y, cred, cgreen, cblue;
  int cb, cr;
  register JSAMPROW outptr;
  JSAMPROW inptr0, inptr1, inptr2;
  JDIMENSION col;
  /* copy these pointers into registers if possible */
  register JSAMPLE * rbnge_limit = cinfo->sbmple_rbnge_limit;
  int * Crrtbb = upsbmple->Cr_r_tbb;
  int * Cbbtbb = upsbmple->Cb_b_tbb;
  INT32 * Crgtbb = upsbmple->Cr_g_tbb;
  INT32 * Cbgtbb = upsbmple->Cb_g_tbb;
  SHIFT_TEMPS

  inptr0 = input_buf[0][in_row_group_ctr];
  inptr1 = input_buf[1][in_row_group_ctr];
  inptr2 = input_buf[2][in_row_group_ctr];
  outptr = output_buf[0];
  /* Loop for ebch pbir of output pixels */
  for (col = cinfo->output_width >> 1; col > 0; col--) {
    /* Do the chromb pbrt of the cblculbtion */
    cb = GETJSAMPLE(*inptr1++);
    cr = GETJSAMPLE(*inptr2++);
    cred = Crrtbb[cr];
    cgreen = (int) RIGHT_SHIFT(Cbgtbb[cb] + Crgtbb[cr], SCALEBITS);
    cblue = Cbbtbb[cb];
    /* Fetch 2 Y vblues bnd emit 2 pixels */
    y  = GETJSAMPLE(*inptr0++);
    outptr[RGB_RED] =   rbnge_limit[y + cred];
    outptr[RGB_GREEN] = rbnge_limit[y + cgreen];
    outptr[RGB_BLUE] =  rbnge_limit[y + cblue];
    outptr += RGB_PIXELSIZE;
    y  = GETJSAMPLE(*inptr0++);
    outptr[RGB_RED] =   rbnge_limit[y + cred];
    outptr[RGB_GREEN] = rbnge_limit[y + cgreen];
    outptr[RGB_BLUE] =  rbnge_limit[y + cblue];
    outptr += RGB_PIXELSIZE;
  }
  /* If imbge width is odd, do the lbst output column sepbrbtely */
  if (cinfo->output_width & 1) {
    cb = GETJSAMPLE(*inptr1);
    cr = GETJSAMPLE(*inptr2);
    cred = Crrtbb[cr];
    cgreen = (int) RIGHT_SHIFT(Cbgtbb[cb] + Crgtbb[cr], SCALEBITS);
    cblue = Cbbtbb[cb];
    y  = GETJSAMPLE(*inptr0);
    outptr[RGB_RED] =   rbnge_limit[y + cred];
    outptr[RGB_GREEN] = rbnge_limit[y + cgreen];
    outptr[RGB_BLUE] =  rbnge_limit[y + cblue];
  }
}


/*
 * Upsbmple bnd color convert for the cbse of 2:1 horizontbl bnd 2:1 verticbl.
 */

METHODDEF(void)
h2v2_merged_upsbmple (j_decompress_ptr cinfo,
                      JSAMPIMAGE input_buf, JDIMENSION in_row_group_ctr,
                      JSAMPARRAY output_buf)
{
  my_upsbmple_ptr upsbmple = (my_upsbmple_ptr) cinfo->upsbmple;
  register int y, cred, cgreen, cblue;
  int cb, cr;
  register JSAMPROW outptr0, outptr1;
  JSAMPROW inptr00, inptr01, inptr1, inptr2;
  JDIMENSION col;
  /* copy these pointers into registers if possible */
  register JSAMPLE * rbnge_limit = cinfo->sbmple_rbnge_limit;
  int * Crrtbb = upsbmple->Cr_r_tbb;
  int * Cbbtbb = upsbmple->Cb_b_tbb;
  INT32 * Crgtbb = upsbmple->Cr_g_tbb;
  INT32 * Cbgtbb = upsbmple->Cb_g_tbb;
  SHIFT_TEMPS

  inptr00 = input_buf[0][in_row_group_ctr*2];
  inptr01 = input_buf[0][in_row_group_ctr*2 + 1];
  inptr1 = input_buf[1][in_row_group_ctr];
  inptr2 = input_buf[2][in_row_group_ctr];
  outptr0 = output_buf[0];
  outptr1 = output_buf[1];
  /* Loop for ebch group of output pixels */
  for (col = cinfo->output_width >> 1; col > 0; col--) {
    /* Do the chromb pbrt of the cblculbtion */
    cb = GETJSAMPLE(*inptr1++);
    cr = GETJSAMPLE(*inptr2++);
    cred = Crrtbb[cr];
    cgreen = (int) RIGHT_SHIFT(Cbgtbb[cb] + Crgtbb[cr], SCALEBITS);
    cblue = Cbbtbb[cb];
    /* Fetch 4 Y vblues bnd emit 4 pixels */
    y  = GETJSAMPLE(*inptr00++);
    outptr0[RGB_RED] =   rbnge_limit[y + cred];
    outptr0[RGB_GREEN] = rbnge_limit[y + cgreen];
    outptr0[RGB_BLUE] =  rbnge_limit[y + cblue];
    outptr0 += RGB_PIXELSIZE;
    y  = GETJSAMPLE(*inptr00++);
    outptr0[RGB_RED] =   rbnge_limit[y + cred];
    outptr0[RGB_GREEN] = rbnge_limit[y + cgreen];
    outptr0[RGB_BLUE] =  rbnge_limit[y + cblue];
    outptr0 += RGB_PIXELSIZE;
    y  = GETJSAMPLE(*inptr01++);
    outptr1[RGB_RED] =   rbnge_limit[y + cred];
    outptr1[RGB_GREEN] = rbnge_limit[y + cgreen];
    outptr1[RGB_BLUE] =  rbnge_limit[y + cblue];
    outptr1 += RGB_PIXELSIZE;
    y  = GETJSAMPLE(*inptr01++);
    outptr1[RGB_RED] =   rbnge_limit[y + cred];
    outptr1[RGB_GREEN] = rbnge_limit[y + cgreen];
    outptr1[RGB_BLUE] =  rbnge_limit[y + cblue];
    outptr1 += RGB_PIXELSIZE;
  }
  /* If imbge width is odd, do the lbst output column sepbrbtely */
  if (cinfo->output_width & 1) {
    cb = GETJSAMPLE(*inptr1);
    cr = GETJSAMPLE(*inptr2);
    cred = Crrtbb[cr];
    cgreen = (int) RIGHT_SHIFT(Cbgtbb[cb] + Crgtbb[cr], SCALEBITS);
    cblue = Cbbtbb[cb];
    y  = GETJSAMPLE(*inptr00);
    outptr0[RGB_RED] =   rbnge_limit[y + cred];
    outptr0[RGB_GREEN] = rbnge_limit[y + cgreen];
    outptr0[RGB_BLUE] =  rbnge_limit[y + cblue];
    y  = GETJSAMPLE(*inptr01);
    outptr1[RGB_RED] =   rbnge_limit[y + cred];
    outptr1[RGB_GREEN] = rbnge_limit[y + cgreen];
    outptr1[RGB_BLUE] =  rbnge_limit[y + cblue];
  }
}


/*
 * Module initiblizbtion routine for merged upsbmpling/color conversion.
 *
 * NB: this is cblled under the conditions determined by use_merged_upsbmple()
 * in jdmbster.c.  Thbt routine MUST correspond to the bctubl cbpbbilities
 * of this module; no sbfety checks bre mbde here.
 */

GLOBAL(void)
jinit_merged_upsbmpler (j_decompress_ptr cinfo)
{
  my_upsbmple_ptr upsbmple;

  upsbmple = (my_upsbmple_ptr)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                SIZEOF(my_upsbmpler));
  cinfo->upsbmple = (struct jpeg_upsbmpler *) upsbmple;
  upsbmple->pub.stbrt_pbss = stbrt_pbss_merged_upsbmple;
  upsbmple->pub.need_context_rows = FALSE;

  upsbmple->out_row_width = cinfo->output_width * cinfo->out_color_components;

  if (cinfo->mbx_v_sbmp_fbctor == 2) {
    upsbmple->pub.upsbmple = merged_2v_upsbmple;
    upsbmple->upmethod = h2v2_merged_upsbmple;
    /* Allocbte b spbre row buffer */
    upsbmple->spbre_row = (JSAMPROW)
      (*cinfo->mem->blloc_lbrge) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                (size_t) (upsbmple->out_row_width * SIZEOF(JSAMPLE)));
  } else {
    upsbmple->pub.upsbmple = merged_1v_upsbmple;
    upsbmple->upmethod = h2v1_merged_upsbmple;
    /* No spbre row needed */
    upsbmple->spbre_row = NULL;
  }

  build_ycc_rgb_tbble(cinfo);
}

#endif /* UPSAMPLE_MERGING_SUPPORTED */
