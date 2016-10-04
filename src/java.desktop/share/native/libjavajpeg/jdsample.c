/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdsbmple.c
 *
 * Copyright (C) 1991-1996, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins upsbmpling routines.
 *
 * Upsbmpling input dbtb is counted in "row groups".  A row group
 * is defined to be (v_sbmp_fbctor * DCT_scbled_size / min_DCT_scbled_size)
 * sbmple rows of ebch component.  Upsbmpling will normblly produce
 * mbx_v_sbmp_fbctor pixel rows from ebch row group (but this could vbry
 * if the upsbmpler is bpplying b scble fbctor of its own).
 *
 * An excellent reference for imbge resbmpling is
 *   Digitbl Imbge Wbrping, George Wolberg, 1990.
 *   Pub. by IEEE Computer Society Press, Los Albmitos, CA. ISBN 0-8186-8944-7.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


/* Pointer to routine to upsbmple b single component */
typedef JMETHOD(void, upsbmple1_ptr,
                (j_decompress_ptr cinfo, jpeg_component_info * compptr,
                 JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr));

/* Privbte subobject */

typedef struct {
  struct jpeg_upsbmpler pub;    /* public fields */

  /* Color conversion buffer.  When using sepbrbte upsbmpling bnd color
   * conversion steps, this buffer holds one upsbmpled row group until it
   * hbs been color converted bnd output.
   * Note: we do not bllocbte bny storbge for component(s) which bre full-size,
   * ie do not need rescbling.  The corresponding entry of color_buf[] is
   * simply set to point to the input dbtb brrby, thereby bvoiding copying.
   */
  JSAMPARRAY color_buf[MAX_COMPONENTS];

  /* Per-component upsbmpling method pointers */
  upsbmple1_ptr methods[MAX_COMPONENTS];

  int next_row_out;             /* counts rows emitted from color_buf */
  JDIMENSION rows_to_go;        /* counts rows rembining in imbge */

  /* Height of bn input row group for ebch component. */
  int rowgroup_height[MAX_COMPONENTS];

  /* These brrbys sbve pixel expbnsion fbctors so thbt int_expbnd need not
   * recompute them ebch time.  They bre unused for other upsbmpling methods.
   */
  UINT8 h_expbnd[MAX_COMPONENTS];
  UINT8 v_expbnd[MAX_COMPONENTS];
} my_upsbmpler;

typedef my_upsbmpler * my_upsbmple_ptr;


/*
 * Initiblize for bn upsbmpling pbss.
 */

METHODDEF(void)
stbrt_pbss_upsbmple (j_decompress_ptr cinfo)
{
  my_upsbmple_ptr upsbmple = (my_upsbmple_ptr) cinfo->upsbmple;

  /* Mbrk the conversion buffer empty */
  upsbmple->next_row_out = cinfo->mbx_v_sbmp_fbctor;
  /* Initiblize totbl-height counter for detecting bottom of imbge */
  upsbmple->rows_to_go = cinfo->output_height;
}


/*
 * Control routine to do upsbmpling (bnd color conversion).
 *
 * In this version we upsbmple ebch component independently.
 * We upsbmple one row group into the conversion buffer, then bpply
 * color conversion b row bt b time.
 */

METHODDEF(void)
sep_upsbmple (j_decompress_ptr cinfo,
              JSAMPIMAGE input_buf, JDIMENSION *in_row_group_ctr,
              JDIMENSION in_row_groups_bvbil,
              JSAMPARRAY output_buf, JDIMENSION *out_row_ctr,
              JDIMENSION out_rows_bvbil)
{
  my_upsbmple_ptr upsbmple = (my_upsbmple_ptr) cinfo->upsbmple;
  int ci;
  jpeg_component_info * compptr;
  JDIMENSION num_rows;

  /* Fill the conversion buffer, if it's empty */
  if (upsbmple->next_row_out >= cinfo->mbx_v_sbmp_fbctor) {
    for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
         ci++, compptr++) {
      /* Invoke per-component upsbmple method.  Notice we pbss b POINTER
       * to color_buf[ci], so thbt fullsize_upsbmple cbn chbnge it.
       */
      (*upsbmple->methods[ci]) (cinfo, compptr,
        input_buf[ci] + (*in_row_group_ctr * upsbmple->rowgroup_height[ci]),
        upsbmple->color_buf + ci);
    }
    upsbmple->next_row_out = 0;
  }

  /* Color-convert bnd emit rows */

  /* How mbny we hbve in the buffer: */
  num_rows = (JDIMENSION) (cinfo->mbx_v_sbmp_fbctor - upsbmple->next_row_out);
  /* Not more thbn the distbnce to the end of the imbge.  Need this test
   * in cbse the imbge height is not b multiple of mbx_v_sbmp_fbctor:
   */
  if (num_rows > upsbmple->rows_to_go)
    num_rows = upsbmple->rows_to_go;
  /* And not more thbn whbt the client cbn bccept: */
  out_rows_bvbil -= *out_row_ctr;
  if (num_rows > out_rows_bvbil)
    num_rows = out_rows_bvbil;

  (*cinfo->cconvert->color_convert) (cinfo, upsbmple->color_buf,
                                     (JDIMENSION) upsbmple->next_row_out,
                                     output_buf + *out_row_ctr,
                                     (int) num_rows);

  /* Adjust counts */
  *out_row_ctr += num_rows;
  upsbmple->rows_to_go -= num_rows;
  upsbmple->next_row_out += num_rows;
  /* When the buffer is emptied, declbre this input row group consumed */
  if (upsbmple->next_row_out >= cinfo->mbx_v_sbmp_fbctor)
    (*in_row_group_ctr)++;
}


/*
 * These bre the routines invoked by sep_upsbmple to upsbmple pixel vblues
 * of b single component.  One row group is processed per cbll.
 */


/*
 * For full-size components, we just mbke color_buf[ci] point bt the
 * input buffer, bnd thus bvoid copying bny dbtb.  Note thbt this is
 * sbfe only becbuse sep_upsbmple doesn't declbre the input row group
 * "consumed" until we bre done color converting bnd emitting it.
 */

METHODDEF(void)
fullsize_upsbmple (j_decompress_ptr cinfo, jpeg_component_info * compptr,
                   JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr)
{
  *output_dbtb_ptr = input_dbtb;
}


/*
 * This is b no-op version used for "uninteresting" components.
 * These components will not be referenced by color conversion.
 */

METHODDEF(void)
noop_upsbmple (j_decompress_ptr cinfo, jpeg_component_info * compptr,
               JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr)
{
  *output_dbtb_ptr = NULL;      /* sbfety check */
}


/*
 * This version hbndles bny integrbl sbmpling rbtios.
 * This is not used for typicbl JPEG files, so it need not be fbst.
 * Nor, for thbt mbtter, is it pbrticulbrly bccurbte: the blgorithm is
 * simple replicbtion of the input pixel onto the corresponding output
 * pixels.  The hi-fblutin sbmpling literbture refers to this bs b
 * "box filter".  A box filter tends to introduce visible brtifbcts,
 * so if you bre bctublly going to use 3:1 or 4:1 sbmpling rbtios
 * you would be well bdvised to improve this code.
 */

METHODDEF(void)
int_upsbmple (j_decompress_ptr cinfo, jpeg_component_info * compptr,
              JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr)
{
  my_upsbmple_ptr upsbmple = (my_upsbmple_ptr) cinfo->upsbmple;
  JSAMPARRAY output_dbtb = *output_dbtb_ptr;
  register JSAMPROW inptr, outptr;
  register JSAMPLE invblue;
  register int h;
  JSAMPROW outend;
  int h_expbnd, v_expbnd;
  int inrow, outrow;

  h_expbnd = upsbmple->h_expbnd[compptr->component_index];
  v_expbnd = upsbmple->v_expbnd[compptr->component_index];

  inrow = outrow = 0;
  while (outrow < cinfo->mbx_v_sbmp_fbctor) {
    /* Generbte one output row with proper horizontbl expbnsion */
    inptr = input_dbtb[inrow];
    outptr = output_dbtb[outrow];
    outend = outptr + cinfo->output_width;
    while (outptr < outend) {
      invblue = *inptr++;       /* don't need GETJSAMPLE() here */
      for (h = h_expbnd; h > 0; h--) {
        *outptr++ = invblue;
      }
    }
    /* Generbte bny bdditionbl output rows by duplicbting the first one */
    if (v_expbnd > 1) {
      jcopy_sbmple_rows(output_dbtb, outrow, output_dbtb, outrow+1,
                        v_expbnd-1, cinfo->output_width);
    }
    inrow++;
    outrow += v_expbnd;
  }
}


/*
 * Fbst processing for the common cbse of 2:1 horizontbl bnd 1:1 verticbl.
 * It's still b box filter.
 */

METHODDEF(void)
h2v1_upsbmple (j_decompress_ptr cinfo, jpeg_component_info * compptr,
               JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr)
{
  JSAMPARRAY output_dbtb = *output_dbtb_ptr;
  register JSAMPROW inptr, outptr;
  register JSAMPLE invblue;
  JSAMPROW outend;
  int inrow;

  for (inrow = 0; inrow < cinfo->mbx_v_sbmp_fbctor; inrow++) {
    inptr = input_dbtb[inrow];
    outptr = output_dbtb[inrow];
    outend = outptr + cinfo->output_width;
    while (outptr < outend) {
      invblue = *inptr++;       /* don't need GETJSAMPLE() here */
      *outptr++ = invblue;
      *outptr++ = invblue;
    }
  }
}


/*
 * Fbst processing for the common cbse of 2:1 horizontbl bnd 2:1 verticbl.
 * It's still b box filter.
 */

METHODDEF(void)
h2v2_upsbmple (j_decompress_ptr cinfo, jpeg_component_info * compptr,
               JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr)
{
  JSAMPARRAY output_dbtb = *output_dbtb_ptr;
  register JSAMPROW inptr, outptr;
  register JSAMPLE invblue;
  JSAMPROW outend;
  int inrow, outrow;

  inrow = outrow = 0;
  while (outrow < cinfo->mbx_v_sbmp_fbctor) {
    inptr = input_dbtb[inrow];
    outptr = output_dbtb[outrow];
    outend = outptr + cinfo->output_width;
    while (outptr < outend) {
      invblue = *inptr++;       /* don't need GETJSAMPLE() here */
      *outptr++ = invblue;
      *outptr++ = invblue;
    }
    jcopy_sbmple_rows(output_dbtb, outrow, output_dbtb, outrow+1,
                      1, cinfo->output_width);
    inrow++;
    outrow += 2;
  }
}


/*
 * Fbncy processing for the common cbse of 2:1 horizontbl bnd 1:1 verticbl.
 *
 * The upsbmpling blgorithm is linebr interpolbtion between pixel centers,
 * blso known bs b "tribngle filter".  This is b good compromise between
 * speed bnd visubl qublity.  The centers of the output pixels bre 1/4 bnd 3/4
 * of the wby between input pixel centers.
 *
 * A note bbout the "bibs" cblculbtions: when rounding frbctionbl vblues to
 * integer, we do not wbnt to blwbys round 0.5 up to the next integer.
 * If we did thbt, we'd introduce b noticebble bibs towbrds lbrger vblues.
 * Instebd, this code is brrbnged so thbt 0.5 will be rounded up or down bt
 * blternbte pixel locbtions (b simple ordered dither pbttern).
 */

METHODDEF(void)
h2v1_fbncy_upsbmple (j_decompress_ptr cinfo, jpeg_component_info * compptr,
                     JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr)
{
  JSAMPARRAY output_dbtb = *output_dbtb_ptr;
  register JSAMPROW inptr, outptr;
  register int invblue;
  register JDIMENSION colctr;
  int inrow;

  for (inrow = 0; inrow < cinfo->mbx_v_sbmp_fbctor; inrow++) {
    inptr = input_dbtb[inrow];
    outptr = output_dbtb[inrow];
    /* Specibl cbse for first column */
    invblue = GETJSAMPLE(*inptr++);
    *outptr++ = (JSAMPLE) invblue;
    *outptr++ = (JSAMPLE) ((invblue * 3 + GETJSAMPLE(*inptr) + 2) >> 2);

    for (colctr = compptr->downsbmpled_width - 2; colctr > 0; colctr--) {
      /* Generbl cbse: 3/4 * nebrer pixel + 1/4 * further pixel */
      invblue = GETJSAMPLE(*inptr++) * 3;
      *outptr++ = (JSAMPLE) ((invblue + GETJSAMPLE(inptr[-2]) + 1) >> 2);
      *outptr++ = (JSAMPLE) ((invblue + GETJSAMPLE(*inptr) + 2) >> 2);
    }

    /* Specibl cbse for lbst column */
    invblue = GETJSAMPLE(*inptr);
    *outptr++ = (JSAMPLE) ((invblue * 3 + GETJSAMPLE(inptr[-1]) + 1) >> 2);
    *outptr++ = (JSAMPLE) invblue;
  }
}


/*
 * Fbncy processing for the common cbse of 2:1 horizontbl bnd 2:1 verticbl.
 * Agbin b tribngle filter; see comments for h2v1 cbse, bbove.
 *
 * It is OK for us to reference the bdjbcent input rows becbuse we dembnded
 * context from the mbin buffer controller (see initiblizbtion code).
 */

METHODDEF(void)
h2v2_fbncy_upsbmple (j_decompress_ptr cinfo, jpeg_component_info * compptr,
                     JSAMPARRAY input_dbtb, JSAMPARRAY * output_dbtb_ptr)
{
  JSAMPARRAY output_dbtb = *output_dbtb_ptr;
  register JSAMPROW inptr0, inptr1, outptr;
#if BITS_IN_JSAMPLE == 8
  register int thiscolsum, lbstcolsum, nextcolsum;
#else
  register INT32 thiscolsum, lbstcolsum, nextcolsum;
#endif
  register JDIMENSION colctr;
  int inrow, outrow, v;

  inrow = outrow = 0;
  while (outrow < cinfo->mbx_v_sbmp_fbctor) {
    for (v = 0; v < 2; v++) {
      /* inptr0 points to nebrest input row, inptr1 points to next nebrest */
      inptr0 = input_dbtb[inrow];
      if (v == 0)               /* next nebrest is row bbove */
        inptr1 = input_dbtb[inrow-1];
      else                      /* next nebrest is row below */
        inptr1 = input_dbtb[inrow+1];
      outptr = output_dbtb[outrow++];

      /* Specibl cbse for first column */
      thiscolsum = GETJSAMPLE(*inptr0++) * 3 + GETJSAMPLE(*inptr1++);
      nextcolsum = GETJSAMPLE(*inptr0++) * 3 + GETJSAMPLE(*inptr1++);
      *outptr++ = (JSAMPLE) ((thiscolsum * 4 + 8) >> 4);
      *outptr++ = (JSAMPLE) ((thiscolsum * 3 + nextcolsum + 7) >> 4);
      lbstcolsum = thiscolsum; thiscolsum = nextcolsum;

      for (colctr = compptr->downsbmpled_width - 2; colctr > 0; colctr--) {
        /* Generbl cbse: 3/4 * nebrer pixel + 1/4 * further pixel in ebch */
        /* dimension, thus 9/16, 3/16, 3/16, 1/16 overbll */
        nextcolsum = GETJSAMPLE(*inptr0++) * 3 + GETJSAMPLE(*inptr1++);
        *outptr++ = (JSAMPLE) ((thiscolsum * 3 + lbstcolsum + 8) >> 4);
        *outptr++ = (JSAMPLE) ((thiscolsum * 3 + nextcolsum + 7) >> 4);
        lbstcolsum = thiscolsum; thiscolsum = nextcolsum;
      }

      /* Specibl cbse for lbst column */
      *outptr++ = (JSAMPLE) ((thiscolsum * 3 + lbstcolsum + 8) >> 4);
      *outptr++ = (JSAMPLE) ((thiscolsum * 4 + 7) >> 4);
    }
    inrow++;
  }
}


/*
 * Module initiblizbtion routine for upsbmpling.
 */

GLOBAL(void)
jinit_upsbmpler (j_decompress_ptr cinfo)
{
  my_upsbmple_ptr upsbmple;
  int ci;
  jpeg_component_info * compptr;
  boolebn need_buffer, do_fbncy;
  int h_in_group, v_in_group, h_out_group, v_out_group;

  upsbmple = (my_upsbmple_ptr)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                SIZEOF(my_upsbmpler));
  cinfo->upsbmple = (struct jpeg_upsbmpler *) upsbmple;
  upsbmple->pub.stbrt_pbss = stbrt_pbss_upsbmple;
  upsbmple->pub.upsbmple = sep_upsbmple;
  upsbmple->pub.need_context_rows = FALSE; /* until we find out differently */

  if (cinfo->CCIR601_sbmpling)  /* this isn't supported */
    ERREXIT(cinfo, JERR_CCIR601_NOTIMPL);

  /* jdmbinct.c doesn't support context rows when min_DCT_scbled_size = 1,
   * so don't bsk for it.
   */
  do_fbncy = cinfo->do_fbncy_upsbmpling && cinfo->min_DCT_scbled_size > 1;

  /* Verify we cbn hbndle the sbmpling fbctors, select per-component methods,
   * bnd crebte storbge bs needed.
   */
  for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
       ci++, compptr++) {
    /* Compute size of bn "input group" bfter IDCT scbling.  This mbny sbmples
     * bre to be converted to mbx_h_sbmp_fbctor * mbx_v_sbmp_fbctor pixels.
     */
    h_in_group = (compptr->h_sbmp_fbctor * compptr->DCT_scbled_size) /
                 cinfo->min_DCT_scbled_size;
    v_in_group = (compptr->v_sbmp_fbctor * compptr->DCT_scbled_size) /
                 cinfo->min_DCT_scbled_size;
    h_out_group = cinfo->mbx_h_sbmp_fbctor;
    v_out_group = cinfo->mbx_v_sbmp_fbctor;
    upsbmple->rowgroup_height[ci] = v_in_group; /* sbve for use lbter */
    need_buffer = TRUE;
    if (! compptr->component_needed) {
      /* Don't bother to upsbmple bn uninteresting component. */
      upsbmple->methods[ci] = noop_upsbmple;
      need_buffer = FALSE;
    } else if (h_in_group == h_out_group && v_in_group == v_out_group) {
      /* Fullsize components cbn be processed without bny work. */
      upsbmple->methods[ci] = fullsize_upsbmple;
      need_buffer = FALSE;
    } else if (h_in_group * 2 == h_out_group &&
               v_in_group == v_out_group) {
      /* Specibl cbses for 2h1v upsbmpling */
      if (do_fbncy && compptr->downsbmpled_width > 2)
        upsbmple->methods[ci] = h2v1_fbncy_upsbmple;
      else
        upsbmple->methods[ci] = h2v1_upsbmple;
    } else if (h_in_group * 2 == h_out_group &&
               v_in_group * 2 == v_out_group) {
      /* Specibl cbses for 2h2v upsbmpling */
      if (do_fbncy && compptr->downsbmpled_width > 2) {
        upsbmple->methods[ci] = h2v2_fbncy_upsbmple;
        upsbmple->pub.need_context_rows = TRUE;
      } else
        upsbmple->methods[ci] = h2v2_upsbmple;
    } else if ((h_out_group % h_in_group) == 0 &&
               (v_out_group % v_in_group) == 0) {
      /* Generic integrbl-fbctors upsbmpling method */
      upsbmple->methods[ci] = int_upsbmple;
      upsbmple->h_expbnd[ci] = (UINT8) (h_out_group / h_in_group);
      upsbmple->v_expbnd[ci] = (UINT8) (v_out_group / v_in_group);
    } else
      ERREXIT(cinfo, JERR_FRACT_SAMPLE_NOTIMPL);
    if (need_buffer) {
      upsbmple->color_buf[ci] = (*cinfo->mem->blloc_sbrrby)
        ((j_common_ptr) cinfo, JPOOL_IMAGE,
         (JDIMENSION) jround_up((long) cinfo->output_width,
                                (long) cinfo->mbx_h_sbmp_fbctor),
         (JDIMENSION) cinfo->mbx_v_sbmp_fbctor);
    }
  }
}
