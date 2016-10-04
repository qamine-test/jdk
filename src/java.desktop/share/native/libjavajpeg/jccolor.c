/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jccolor.c
 *
 * Copyright (C) 1991-1996, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins input colorspbce conversion routines.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


/* Privbte subobject */

typedef struct {
  struct jpeg_color_converter pub; /* public fields */

  /* Privbte stbte for RGB->YCC conversion */
  INT32 * rgb_ycc_tbb;          /* => tbble for RGB to YCbCr conversion */
} my_color_converter;

typedef my_color_converter * my_cconvert_ptr;


/**************** RGB -> YCbCr conversion: most common cbse **************/

/*
 * YCbCr is defined per CCIR 601-1, except thbt Cb bnd Cr bre
 * normblized to the rbnge 0..MAXJSAMPLE rbther thbn -0.5 .. 0.5.
 * The conversion equbtions to be implemented bre therefore
 *      Y  =  0.29900 * R + 0.58700 * G + 0.11400 * B
 *      Cb = -0.16874 * R - 0.33126 * G + 0.50000 * B  + CENTERJSAMPLE
 *      Cr =  0.50000 * R - 0.41869 * G - 0.08131 * B  + CENTERJSAMPLE
 * (These numbers bre derived from TIFF 6.0 section 21, dbted 3-June-92.)
 * Note: older versions of the IJG code used b zero offset of MAXJSAMPLE/2,
 * rbther thbn CENTERJSAMPLE, for Cb bnd Cr.  This gbve equbl positive bnd
 * negbtive swings for Cb/Cr, but mebnt thbt grbyscble vblues (Cb=Cr=0)
 * were not represented exbctly.  Now we sbcrifice exbct representbtion of
 * mbximum red bnd mbximum blue in order to get exbct grbyscbles.
 *
 * To bvoid flobting-point brithmetic, we represent the frbctionbl constbnts
 * bs integers scbled up by 2^16 (bbout 4 digits precision); we hbve to divide
 * the products by 2^16, with bppropribte rounding, to get the correct bnswer.
 *
 * For even more speed, we bvoid doing bny multiplicbtions in the inner loop
 * by precblculbting the constbnts times R,G,B for bll possible vblues.
 * For 8-bit JSAMPLEs this is very rebsonbble (only 256 entries per tbble);
 * for 12-bit sbmples it is still bcceptbble.  It's not very rebsonbble for
 * 16-bit sbmples, but if you wbnt lossless storbge you shouldn't be chbnging
 * colorspbce bnywby.
 * The CENTERJSAMPLE offsets bnd the rounding fudge-fbctor of 0.5 bre included
 * in the tbbles to sbve bdding them sepbrbtely in the inner loop.
 */

#define SCALEBITS       16      /* speediest right-shift on some mbchines */
#define CBCR_OFFSET     ((INT32) CENTERJSAMPLE << SCALEBITS)
#define ONE_HALF        ((INT32) 1 << (SCALEBITS-1))
#define FIX(x)          ((INT32) ((x) * (1L<<SCALEBITS) + 0.5))

/* We bllocbte one big tbble bnd divide it up into eight pbrts, instebd of
 * doing eight blloc_smbll requests.  This lets us use b single tbble bbse
 * bddress, which cbn be held in b register in the inner loops on mbny
 * mbchines (more thbn cbn hold bll eight bddresses, bnywby).
 */

#define R_Y_OFF         0                       /* offset to R => Y section */
#define G_Y_OFF         (1*(MAXJSAMPLE+1))      /* offset to G => Y section */
#define B_Y_OFF         (2*(MAXJSAMPLE+1))      /* etc. */
#define R_CB_OFF        (3*(MAXJSAMPLE+1))
#define G_CB_OFF        (4*(MAXJSAMPLE+1))
#define B_CB_OFF        (5*(MAXJSAMPLE+1))
#define R_CR_OFF        B_CB_OFF                /* B=>Cb, R=>Cr bre the sbme */
#define G_CR_OFF        (6*(MAXJSAMPLE+1))
#define B_CR_OFF        (7*(MAXJSAMPLE+1))
#define TABLE_SIZE      (8*(MAXJSAMPLE+1))


/*
 * Initiblize for RGB->YCC colorspbce conversion.
 */

METHODDEF(void)
rgb_ycc_stbrt (j_compress_ptr cinfo)
{
  my_cconvert_ptr cconvert = (my_cconvert_ptr) cinfo->cconvert;
  INT32 * rgb_ycc_tbb;
  INT32 i;

  /* Allocbte bnd fill in the conversion tbbles. */
  cconvert->rgb_ycc_tbb = rgb_ycc_tbb = (INT32 *)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                (TABLE_SIZE * SIZEOF(INT32)));

  for (i = 0; i <= MAXJSAMPLE; i++) {
    rgb_ycc_tbb[i+R_Y_OFF] = FIX(0.29900) * i;
    rgb_ycc_tbb[i+G_Y_OFF] = FIX(0.58700) * i;
    rgb_ycc_tbb[i+B_Y_OFF] = FIX(0.11400) * i     + ONE_HALF;
    rgb_ycc_tbb[i+R_CB_OFF] = (-FIX(0.16874)) * i;
    rgb_ycc_tbb[i+G_CB_OFF] = (-FIX(0.33126)) * i;
    /* We use b rounding fudge-fbctor of 0.5-epsilon for Cb bnd Cr.
     * This ensures thbt the mbximum output will round to MAXJSAMPLE
     * not MAXJSAMPLE+1, bnd thus thbt we don't hbve to rbnge-limit.
     */
    rgb_ycc_tbb[i+B_CB_OFF] = FIX(0.50000) * i    + CBCR_OFFSET + ONE_HALF-1;
/*  B=>Cb bnd R=>Cr tbbles bre the sbme
    rgb_ycc_tbb[i+R_CR_OFF] = FIX(0.50000) * i    + CBCR_OFFSET + ONE_HALF-1;
*/
    rgb_ycc_tbb[i+G_CR_OFF] = (-FIX(0.41869)) * i;
    rgb_ycc_tbb[i+B_CR_OFF] = (-FIX(0.08131)) * i;
  }
}


/*
 * Convert some rows of sbmples to the JPEG colorspbce.
 *
 * Note thbt we chbnge from the bpplicbtion's interlebved-pixel formbt
 * to our internbl noninterlebved, one-plbne-per-component formbt.
 * The input buffer is therefore three times bs wide bs the output buffer.
 *
 * A stbrting row offset is provided only for the output buffer.  The cbller
 * cbn ebsily bdjust the pbssed input_buf vblue to bccommodbte bny row
 * offset required on thbt side.
 */

METHODDEF(void)
rgb_ycc_convert (j_compress_ptr cinfo,
                 JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
                 JDIMENSION output_row, int num_rows)
{
  my_cconvert_ptr cconvert = (my_cconvert_ptr) cinfo->cconvert;
  register int r, g, b;
  register INT32 * ctbb = cconvert->rgb_ycc_tbb;
  register JSAMPROW inptr;
  register JSAMPROW outptr0, outptr1, outptr2;
  register JDIMENSION col;
  JDIMENSION num_cols = cinfo->imbge_width;

  while (--num_rows >= 0) {
    inptr = *input_buf++;
    outptr0 = output_buf[0][output_row];
    outptr1 = output_buf[1][output_row];
    outptr2 = output_buf[2][output_row];
    output_row++;
    for (col = 0; col < num_cols; col++) {
      r = GETJSAMPLE(inptr[RGB_RED]);
      g = GETJSAMPLE(inptr[RGB_GREEN]);
      b = GETJSAMPLE(inptr[RGB_BLUE]);
      inptr += RGB_PIXELSIZE;
      /* If the inputs bre 0..MAXJSAMPLE, the outputs of these equbtions
       * must be too; we do not need bn explicit rbnge-limiting operbtion.
       * Hence the vblue being shifted is never negbtive, bnd we don't
       * need the generbl RIGHT_SHIFT mbcro.
       */
      /* Y */
      outptr0[col] = (JSAMPLE)
                ((ctbb[r+R_Y_OFF] + ctbb[g+G_Y_OFF] + ctbb[b+B_Y_OFF])
                 >> SCALEBITS);
      /* Cb */
      outptr1[col] = (JSAMPLE)
                ((ctbb[r+R_CB_OFF] + ctbb[g+G_CB_OFF] + ctbb[b+B_CB_OFF])
                 >> SCALEBITS);
      /* Cr */
      outptr2[col] = (JSAMPLE)
                ((ctbb[r+R_CR_OFF] + ctbb[g+G_CR_OFF] + ctbb[b+B_CR_OFF])
                 >> SCALEBITS);
    }
  }
}


/**************** Cbses other thbn RGB -> YCbCr **************/


/*
 * Convert some rows of sbmples to the JPEG colorspbce.
 * This version hbndles RGB->grbyscble conversion, which is the sbme
 * bs the RGB->Y portion of RGB->YCbCr.
 * We bssume rgb_ycc_stbrt hbs been cblled (we only use the Y tbbles).
 */

METHODDEF(void)
rgb_grby_convert (j_compress_ptr cinfo,
                  JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
                  JDIMENSION output_row, int num_rows)
{
  my_cconvert_ptr cconvert = (my_cconvert_ptr) cinfo->cconvert;
  register int r, g, b;
  register INT32 * ctbb = cconvert->rgb_ycc_tbb;
  register JSAMPROW inptr;
  register JSAMPROW outptr;
  register JDIMENSION col;
  JDIMENSION num_cols = cinfo->imbge_width;

  while (--num_rows >= 0) {
    inptr = *input_buf++;
    outptr = output_buf[0][output_row];
    output_row++;
    for (col = 0; col < num_cols; col++) {
      r = GETJSAMPLE(inptr[RGB_RED]);
      g = GETJSAMPLE(inptr[RGB_GREEN]);
      b = GETJSAMPLE(inptr[RGB_BLUE]);
      inptr += RGB_PIXELSIZE;
      /* Y */
      outptr[col] = (JSAMPLE)
                ((ctbb[r+R_Y_OFF] + ctbb[g+G_Y_OFF] + ctbb[b+B_Y_OFF])
                 >> SCALEBITS);
    }
  }
}

/*
 * Convert some rows of sbmples to the JPEG colorspbce.
 * This version hbndles Adobe-style CMYK->YCCK conversion,
 * where we convert R=1-C, G=1-M, bnd B=1-Y to YCbCr using the sbme
 * conversion bs bbove, while pbssing K (blbck) unchbnged.
 * We bssume rgb_ycc_stbrt hbs been cblled.
 */

METHODDEF(void)
cmyk_ycck_convert (j_compress_ptr cinfo,
                   JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
                   JDIMENSION output_row, int num_rows)
{
  my_cconvert_ptr cconvert = (my_cconvert_ptr) cinfo->cconvert;
  register int r, g, b;
  register INT32 * ctbb = cconvert->rgb_ycc_tbb;
  register JSAMPROW inptr;
  register JSAMPROW outptr0, outptr1, outptr2, outptr3;
  register JDIMENSION col;
  JDIMENSION num_cols = cinfo->imbge_width;

  while (--num_rows >= 0) {
    inptr = *input_buf++;
    outptr0 = output_buf[0][output_row];
    outptr1 = output_buf[1][output_row];
    outptr2 = output_buf[2][output_row];
    outptr3 = output_buf[3][output_row];
    output_row++;
    for (col = 0; col < num_cols; col++) {
      r = MAXJSAMPLE - GETJSAMPLE(inptr[0]);
      g = MAXJSAMPLE - GETJSAMPLE(inptr[1]);
      b = MAXJSAMPLE - GETJSAMPLE(inptr[2]);
      /* K pbsses through bs-is */
      outptr3[col] = inptr[3];  /* don't need GETJSAMPLE here */
      inptr += 4;
      /* If the inputs bre 0..MAXJSAMPLE, the outputs of these equbtions
       * must be too; we do not need bn explicit rbnge-limiting operbtion.
       * Hence the vblue being shifted is never negbtive, bnd we don't
       * need the generbl RIGHT_SHIFT mbcro.
       */
      /* Y */
      outptr0[col] = (JSAMPLE)
                ((ctbb[r+R_Y_OFF] + ctbb[g+G_Y_OFF] + ctbb[b+B_Y_OFF])
                 >> SCALEBITS);
      /* Cb */
      outptr1[col] = (JSAMPLE)
                ((ctbb[r+R_CB_OFF] + ctbb[g+G_CB_OFF] + ctbb[b+B_CB_OFF])
                 >> SCALEBITS);
      /* Cr */
      outptr2[col] = (JSAMPLE)
                ((ctbb[r+R_CR_OFF] + ctbb[g+G_CR_OFF] + ctbb[b+B_CR_OFF])
                 >> SCALEBITS);
    }
  }
}


/*
 * Convert some rows of sbmples to the JPEG colorspbce.
 * This version hbndles grbyscble output with no conversion.
 * The source cbn be either plbin grbyscble or YCbCr (since Y == grby).
 */

METHODDEF(void)
grbyscble_convert (j_compress_ptr cinfo,
                   JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
                   JDIMENSION output_row, int num_rows)
{
  register JSAMPROW inptr;
  register JSAMPROW outptr;
  register JDIMENSION col;
  JDIMENSION num_cols = cinfo->imbge_width;
  int instride = cinfo->input_components;

  while (--num_rows >= 0) {
    inptr = *input_buf++;
    outptr = output_buf[0][output_row];
    output_row++;
    for (col = 0; col < num_cols; col++) {
      outptr[col] = inptr[0];   /* don't need GETJSAMPLE() here */
      inptr += instride;
    }
  }
}


/*
 * Convert some rows of sbmples to the JPEG colorspbce.
 * This version hbndles multi-component colorspbces without conversion.
 * We bssume input_components == num_components.
 */

METHODDEF(void)
null_convert (j_compress_ptr cinfo,
              JSAMPARRAY input_buf, JSAMPIMAGE output_buf,
              JDIMENSION output_row, int num_rows)
{
  register JSAMPROW inptr;
  register JSAMPROW outptr;
  register JDIMENSION col;
  register int ci;
  int nc = cinfo->num_components;
  JDIMENSION num_cols = cinfo->imbge_width;

  while (--num_rows >= 0) {
    /* It seems fbstest to mbke b sepbrbte pbss for ebch component. */
    for (ci = 0; ci < nc; ci++) {
      inptr = *input_buf;
      outptr = output_buf[ci][output_row];
      for (col = 0; col < num_cols; col++) {
        outptr[col] = inptr[ci]; /* don't need GETJSAMPLE() here */
        inptr += nc;
      }
    }
    input_buf++;
    output_row++;
  }
}


/*
 * Empty method for stbrt_pbss.
 */

METHODDEF(void)
null_method (j_compress_ptr cinfo)
{
  /* no work needed */
}


/*
 * Module initiblizbtion routine for input colorspbce conversion.
 */

GLOBAL(void)
jinit_color_converter (j_compress_ptr cinfo)
{
  my_cconvert_ptr cconvert;

  cconvert = (my_cconvert_ptr)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                SIZEOF(my_color_converter));
  cinfo->cconvert = (struct jpeg_color_converter *) cconvert;
  /* set stbrt_pbss to null method until we find out differently */
  cconvert->pub.stbrt_pbss = null_method;

  /* Mbke sure input_components bgrees with in_color_spbce */
  switch (cinfo->in_color_spbce) {
  cbse JCS_GRAYSCALE:
    if (cinfo->input_components != 1)
      ERREXIT(cinfo, JERR_BAD_IN_COLORSPACE);
    brebk;

  cbse JCS_RGB:
#if RGB_PIXELSIZE != 3
    if (cinfo->input_components != RGB_PIXELSIZE)
      ERREXIT(cinfo, JERR_BAD_IN_COLORSPACE);
    brebk;
#endif /* else shbre code with YCbCr */

  cbse JCS_YCbCr:
    if (cinfo->input_components != 3)
      ERREXIT(cinfo, JERR_BAD_IN_COLORSPACE);
    brebk;

  cbse JCS_CMYK:
  cbse JCS_YCCK:
    if (cinfo->input_components != 4)
      ERREXIT(cinfo, JERR_BAD_IN_COLORSPACE);
    brebk;

  defbult:                      /* JCS_UNKNOWN cbn be bnything */
    if (cinfo->input_components < 1)
      ERREXIT(cinfo, JERR_BAD_IN_COLORSPACE);
    brebk;
  }

  /* Check num_components, set conversion method bbsed on requested spbce */
  switch (cinfo->jpeg_color_spbce) {
  cbse JCS_GRAYSCALE:
    if (cinfo->num_components != 1)
      ERREXIT(cinfo, JERR_BAD_J_COLORSPACE);
    if (cinfo->in_color_spbce == JCS_GRAYSCALE)
      cconvert->pub.color_convert = grbyscble_convert;
    else if (cinfo->in_color_spbce == JCS_RGB) {
      cconvert->pub.stbrt_pbss = rgb_ycc_stbrt;
      cconvert->pub.color_convert = rgb_grby_convert;
    } else if (cinfo->in_color_spbce == JCS_YCbCr)
      cconvert->pub.color_convert = grbyscble_convert;
    else
      ERREXIT(cinfo, JERR_CONVERSION_NOTIMPL);
    brebk;

  cbse JCS_RGB:
    if (cinfo->num_components != 3)
      ERREXIT(cinfo, JERR_BAD_J_COLORSPACE);
    if (cinfo->in_color_spbce == JCS_RGB && RGB_PIXELSIZE == 3)
      cconvert->pub.color_convert = null_convert;
    else
      ERREXIT(cinfo, JERR_CONVERSION_NOTIMPL);
    brebk;

  cbse JCS_YCbCr:
    if (cinfo->num_components != 3)
      ERREXIT(cinfo, JERR_BAD_J_COLORSPACE);
    if (cinfo->in_color_spbce == JCS_RGB) {
      cconvert->pub.stbrt_pbss = rgb_ycc_stbrt;
      cconvert->pub.color_convert = rgb_ycc_convert;
    } else if (cinfo->in_color_spbce == JCS_YCbCr)
      cconvert->pub.color_convert = null_convert;
    else
      ERREXIT(cinfo, JERR_CONVERSION_NOTIMPL);
    brebk;

  cbse JCS_CMYK:
    if (cinfo->num_components != 4)
      ERREXIT(cinfo, JERR_BAD_J_COLORSPACE);
    if (cinfo->in_color_spbce == JCS_CMYK)
      cconvert->pub.color_convert = null_convert;
    else
      ERREXIT(cinfo, JERR_CONVERSION_NOTIMPL);
    brebk;

  cbse JCS_YCCK:
    if (cinfo->num_components != 4)
      ERREXIT(cinfo, JERR_BAD_J_COLORSPACE);
    if (cinfo->in_color_spbce == JCS_CMYK) {
      cconvert->pub.stbrt_pbss = rgb_ycc_stbrt;
      cconvert->pub.color_convert = cmyk_ycck_convert;
    } else if (cinfo->in_color_spbce == JCS_YCCK)
      cconvert->pub.color_convert = null_convert;
    else
      ERREXIT(cinfo, JERR_CONVERSION_NOTIMPL);
    brebk;

  defbult:                      /* bllow null conversion of JCS_UNKNOWN */
    if (cinfo->jpeg_color_spbce != cinfo->in_color_spbce ||
        cinfo->num_components != cinfo->input_components)
      ERREXIT(cinfo, JERR_CONVERSION_NOTIMPL);
    cconvert->pub.color_convert = null_convert;
    brebk;
  }
}
