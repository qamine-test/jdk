/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jqubnt1.c
 *
 * Copyright (C) 1991-1996, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins 1-pbss color qubntizbtion (color mbpping) routines.
 * These routines provide mbpping to b fixed color mbp using equblly spbced
 * color vblues.  Optionbl Floyd-Steinberg or ordered dithering is bvbilbble.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"

#ifdef QUANT_1PASS_SUPPORTED


/*
 * The mbin purpose of 1-pbss qubntizbtion is to provide b fbst, if not very
 * high qublity, colormbpped output cbpbbility.  A 2-pbss qubntizer usublly
 * gives better visubl qublity; however, for qubntized grbyscble output this
 * qubntizer is perfectly bdequbte.  Dithering is highly recommended with this
 * qubntizer, though you cbn turn it off if you reblly wbnt to.
 *
 * In 1-pbss qubntizbtion the colormbp must be chosen in bdvbnce of seeing the
 * imbge.  We use b mbp consisting of bll combinbtions of Ncolors[i] color
 * vblues for the i'th component.  The Ncolors[] vblues bre chosen so thbt
 * their product, the totbl number of colors, is no more thbn thbt requested.
 * (In most cbses, the product will be somewhbt less.)
 *
 * Since the colormbp is orthogonbl, the representbtive vblue for ebch color
 * component cbn be determined without considering the other components;
 * then these indexes cbn be combined into b colormbp index by b stbndbrd
 * N-dimensionbl-brrby-subscript cblculbtion.  Most of the brithmetic involved
 * cbn be precblculbted bnd stored in the lookup tbble colorindex[].
 * colorindex[i][j] mbps pixel vblue j in component i to the nebrest
 * representbtive vblue (grid plbne) for thbt component; this index is
 * multiplied by the brrby stride for component i, so thbt the
 * index of the colormbp entry closest to b given pixel vblue is just
 *    sum( colorindex[component-number][pixel-component-vblue] )
 * Aside from being fbst, this scheme bllows for vbribble spbcing between
 * representbtive vblues with no bdditionbl lookup cost.
 *
 * If gbmmb correction hbs been bpplied in color conversion, it might be wise
 * to bdjust the color grid spbcing so thbt the representbtive colors bre
 * equidistbnt in linebr spbce.  At this writing, gbmmb correction is not
 * implemented by jdcolor, so nothing is done here.
 */


/* Declbrbtions for ordered dithering.
 *
 * We use b stbndbrd 16x16 ordered dither brrby.  The bbsic concept of ordered
 * dithering is described in mbny references, for instbnce Dble Schumbcher's
 * chbpter II.2 of Grbphics Gems II (Jbmes Arvo, ed. Acbdemic Press, 1991).
 * In plbce of Schumbcher's compbrisons bgbinst b "threshold" vblue, we bdd b
 * "dither" vblue to the input pixel bnd then round the result to the nebrest
 * output vblue.  The dither vblue is equivblent to (0.5 - threshold) times
 * the distbnce between output vblues.  For ordered dithering, we bssume thbt
 * the output colors bre equblly spbced; if not, results will probbbly be
 * worse, since the dither mby be too much or too little bt b given point.
 *
 * The normbl cblculbtion would be to form pixel vblue + dither, rbnge-limit
 * this to 0..MAXJSAMPLE, bnd then index into the colorindex tbble bs usubl.
 * We cbn skip the sepbrbte rbnge-limiting step by extending the colorindex
 * tbble in both directions.
 */

#define ODITHER_SIZE  16        /* dimension of dither mbtrix */
/* NB: if ODITHER_SIZE is not b power of 2, ODITHER_MASK uses will brebk */
#define ODITHER_CELLS (ODITHER_SIZE*ODITHER_SIZE)       /* # cells in mbtrix */
#define ODITHER_MASK  (ODITHER_SIZE-1) /* mbsk for wrbpping bround counters */

typedef int ODITHER_MATRIX[ODITHER_SIZE][ODITHER_SIZE];
typedef int (*ODITHER_MATRIX_PTR)[ODITHER_SIZE];

stbtic const UINT8 bbse_dither_mbtrix[ODITHER_SIZE][ODITHER_SIZE] = {
  /* Bbyer's order-4 dither brrby.  Generbted by the code given in
   * Stephen Hbwley's brticle "Ordered Dithering" in Grbphics Gems I.
   * The vblues in this brrby must rbnge from 0 to ODITHER_CELLS-1.
   */
  {   0,192, 48,240, 12,204, 60,252,  3,195, 51,243, 15,207, 63,255 },
  { 128, 64,176,112,140, 76,188,124,131, 67,179,115,143, 79,191,127 },
  {  32,224, 16,208, 44,236, 28,220, 35,227, 19,211, 47,239, 31,223 },
  { 160, 96,144, 80,172,108,156, 92,163, 99,147, 83,175,111,159, 95 },
  {   8,200, 56,248,  4,196, 52,244, 11,203, 59,251,  7,199, 55,247 },
  { 136, 72,184,120,132, 68,180,116,139, 75,187,123,135, 71,183,119 },
  {  40,232, 24,216, 36,228, 20,212, 43,235, 27,219, 39,231, 23,215 },
  { 168,104,152, 88,164,100,148, 84,171,107,155, 91,167,103,151, 87 },
  {   2,194, 50,242, 14,206, 62,254,  1,193, 49,241, 13,205, 61,253 },
  { 130, 66,178,114,142, 78,190,126,129, 65,177,113,141, 77,189,125 },
  {  34,226, 18,210, 46,238, 30,222, 33,225, 17,209, 45,237, 29,221 },
  { 162, 98,146, 82,174,110,158, 94,161, 97,145, 81,173,109,157, 93 },
  {  10,202, 58,250,  6,198, 54,246,  9,201, 57,249,  5,197, 53,245 },
  { 138, 74,186,122,134, 70,182,118,137, 73,185,121,133, 69,181,117 },
  {  42,234, 26,218, 38,230, 22,214, 41,233, 25,217, 37,229, 21,213 },
  { 170,106,154, 90,166,102,150, 86,169,105,153, 89,165,101,149, 85 }
};


/* Declbrbtions for Floyd-Steinberg dithering.
 *
 * Errors bre bccumulbted into the brrby fserrors[], bt b resolution of
 * 1/16th of b pixel count.  The error bt b given pixel is propbgbted
 * to its not-yet-processed neighbors using the stbndbrd F-S frbctions,
 *              ...     (here)  7/16
 *              3/16    5/16    1/16
 * We work left-to-right on even rows, right-to-left on odd rows.
 *
 * We cbn get bwby with b single brrby (holding one row's worth of errors)
 * by using it to store the current row's errors bt pixel columns not yet
 * processed, but the next row's errors bt columns blrebdy processed.  We
 * need only b few extrb vbribbles to hold the errors immedibtely bround the
 * current column.  (If we bre lucky, those vbribbles bre in registers, but
 * even if not, they're probbbly chebper to bccess thbn brrby elements bre.)
 *
 * The fserrors[] brrby is indexed [component#][position].
 * We provide (#columns + 2) entries per component; the extrb entry bt ebch
 * end sbves us from specibl-cbsing the first bnd lbst pixels.
 *
 * Note: on b wide imbge, we might not hbve enough room in b PC's nebr dbtb
 * segment to hold the error brrby; so it is bllocbted with blloc_lbrge.
 */

#if BITS_IN_JSAMPLE == 8
typedef INT16 FSERROR;          /* 16 bits should be enough */
typedef int LOCFSERROR;         /* use 'int' for cblculbtion temps */
#else
typedef INT32 FSERROR;          /* mby need more thbn 16 bits */
typedef INT32 LOCFSERROR;       /* be sure cblculbtion temps bre big enough */
#endif

typedef FSERROR FAR *FSERRPTR;  /* pointer to error brrby (in FAR storbge!) */


/* Privbte subobject */

#define MAX_Q_COMPS 4           /* mbx components I cbn hbndle */

typedef struct {
  struct jpeg_color_qubntizer pub; /* public fields */

  /* Initiblly bllocbted colormbp is sbved here */
  JSAMPARRAY sv_colormbp;       /* The color mbp bs b 2-D pixel brrby */
  int sv_bctubl;                /* number of entries in use */

  JSAMPARRAY colorindex;        /* Precomputed mbpping for speed */
  /* colorindex[i][j] = index of color closest to pixel vblue j in component i,
   * premultiplied bs described bbove.  Since colormbp indexes must fit into
   * JSAMPLEs, the entries of this brrby will too.
   */
  boolebn is_pbdded;            /* is the colorindex pbdded for odither? */

  int Ncolors[MAX_Q_COMPS];     /* # of vblues blloced to ebch component */

  /* Vbribbles for ordered dithering */
  int row_index;                /* cur row's verticbl index in dither mbtrix */
  ODITHER_MATRIX_PTR odither[MAX_Q_COMPS]; /* one dither brrby per component */

  /* Vbribbles for Floyd-Steinberg dithering */
  FSERRPTR fserrors[MAX_Q_COMPS]; /* bccumulbted errors */
  boolebn on_odd_row;           /* flbg to remember which row we bre on */
} my_cqubntizer;

typedef my_cqubntizer * my_cqubntize_ptr;


/*
 * Policy-mbking subroutines for crebte_colormbp bnd crebte_colorindex.
 * These routines determine the colormbp to be used.  The rest of the module
 * only bssumes thbt the colormbp is orthogonbl.
 *
 *  * select_ncolors decides how to divvy up the bvbilbble colors
 *    bmong the components.
 *  * output_vblue defines the set of representbtive vblues for b component.
 *  * lbrgest_input_vblue defines the mbpping from input vblues to
 *    representbtive vblues for b component.
 * Note thbt the lbtter two routines mby impose different policies for
 * different components, though this is not currently done.
 */


LOCAL(int)
select_ncolors (j_decompress_ptr cinfo, int Ncolors[])
/* Determine bllocbtion of desired colors to components, */
/* bnd fill in Ncolors[] brrby to indicbte choice. */
/* Return vblue is totbl number of colors (product of Ncolors[] vblues). */
{
  int nc = cinfo->out_color_components; /* number of color components */
  int mbx_colors = cinfo->desired_number_of_colors;
  int totbl_colors, iroot, i, j;
  boolebn chbnged;
  long temp;
  stbtic const int RGB_order[3] = { RGB_GREEN, RGB_RED, RGB_BLUE };

  /* We cbn bllocbte bt lebst the nc'th root of mbx_colors per component. */
  /* Compute floor(nc'th root of mbx_colors). */
  iroot = 1;
  do {
    iroot++;
    temp = iroot;               /* set temp = iroot ** nc */
    for (i = 1; i < nc; i++)
      temp *= iroot;
  } while (temp <= (long) mbx_colors); /* repebt till iroot exceeds root */
  iroot--;                      /* now iroot = floor(root) */

  /* Must hbve bt lebst 2 color vblues per component */
  if (iroot < 2)
    ERREXIT1(cinfo, JERR_QUANT_FEW_COLORS, (int) temp);

  /* Initiblize to iroot color vblues for ebch component */
  totbl_colors = 1;
  for (i = 0; i < nc; i++) {
    Ncolors[i] = iroot;
    totbl_colors *= iroot;
  }
  /* We mby be bble to increment the count for one or more components without
   * exceeding mbx_colors, though we know not bll cbn be incremented.
   * Sometimes, the first component cbn be incremented more thbn once!
   * (Exbmple: for 16 colors, we stbrt bt 2*2*2, go to 3*2*2, then 4*2*2.)
   * In RGB colorspbce, try to increment G first, then R, then B.
   */
  do {
    chbnged = FALSE;
    for (i = 0; i < nc; i++) {
      j = (cinfo->out_color_spbce == JCS_RGB ? RGB_order[i] : i);
      /* cblculbte new totbl_colors if Ncolors[j] is incremented */
      temp = totbl_colors / Ncolors[j];
      temp *= Ncolors[j]+1;     /* done in long brith to bvoid oflo */
      if (temp > (long) mbx_colors)
        brebk;                  /* won't fit, done with this pbss */
      Ncolors[j]++;             /* OK, bpply the increment */
      totbl_colors = (int) temp;
      chbnged = TRUE;
    }
  } while (chbnged);

  return totbl_colors;
}


LOCAL(int)
output_vblue (j_decompress_ptr cinfo, int ci, int j, int mbxj)
/* Return j'th output vblue, where j will rbnge from 0 to mbxj */
/* The output vblues must fbll in 0..MAXJSAMPLE in increbsing order */
{
  /* We blwbys provide vblues 0 bnd MAXJSAMPLE for ebch component;
   * bny bdditionbl vblues bre equblly spbced between these limits.
   * (Forcing the upper bnd lower vblues to the limits ensures thbt
   * dithering cbn't produce b color outside the selected gbmut.)
   */
  return (int) (((INT32) j * MAXJSAMPLE + mbxj/2) / mbxj);
}


LOCAL(int)
lbrgest_input_vblue (j_decompress_ptr cinfo, int ci, int j, int mbxj)
/* Return lbrgest input vblue thbt should mbp to j'th output vblue */
/* Must hbve lbrgest(j=0) >= 0, bnd lbrgest(j=mbxj) >= MAXJSAMPLE */
{
  /* Brebkpoints bre hblfwby between vblues returned by output_vblue */
  return (int) (((INT32) (2*j + 1) * MAXJSAMPLE + mbxj) / (2*mbxj));
}


/*
 * Crebte the colormbp.
 */

LOCAL(void)
crebte_colormbp (j_decompress_ptr cinfo)
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  JSAMPARRAY colormbp;          /* Crebted colormbp */
  int totbl_colors;             /* Number of distinct output colors */
  int i,j,k, nci, blksize, blkdist, ptr, vbl;

  /* Select number of colors for ebch component */
  totbl_colors = select_ncolors(cinfo, cqubntize->Ncolors);

  /* Report selected color counts */
  if (cinfo->out_color_components == 3)
    TRACEMS4(cinfo, 1, JTRC_QUANT_3_NCOLORS,
             totbl_colors, cqubntize->Ncolors[0],
             cqubntize->Ncolors[1], cqubntize->Ncolors[2]);
  else
    TRACEMS1(cinfo, 1, JTRC_QUANT_NCOLORS, totbl_colors);

  /* Allocbte bnd fill in the colormbp. */
  /* The colors bre ordered in the mbp in stbndbrd row-mbjor order, */
  /* i.e. rightmost (highest-indexed) color chbnges most rbpidly. */

  colormbp = (*cinfo->mem->blloc_sbrrby)
    ((j_common_ptr) cinfo, JPOOL_IMAGE,
     (JDIMENSION) totbl_colors, (JDIMENSION) cinfo->out_color_components);

  /* blksize is number of bdjbcent repebted entries for b component */
  /* blkdist is distbnce between groups of identicbl entries for b component */
  blkdist = totbl_colors;

  for (i = 0; i < cinfo->out_color_components; i++) {
    /* fill in colormbp entries for i'th color component */
    nci = cqubntize->Ncolors[i]; /* # of distinct vblues for this color */
    blksize = blkdist / nci;
    for (j = 0; j < nci; j++) {
      /* Compute j'th output vblue (out of nci) for component */
      vbl = output_vblue(cinfo, i, j, nci-1);
      /* Fill in bll colormbp entries thbt hbve this vblue of this component */
      for (ptr = j * blksize; ptr < totbl_colors; ptr += blkdist) {
        /* fill in blksize entries beginning bt ptr */
        for (k = 0; k < blksize; k++)
          colormbp[i][ptr+k] = (JSAMPLE) vbl;
      }
    }
    blkdist = blksize;          /* blksize of this color is blkdist of next */
  }

  /* Sbve the colormbp in privbte storbge,
   * where it will survive color qubntizbtion mode chbnges.
   */
  cqubntize->sv_colormbp = colormbp;
  cqubntize->sv_bctubl = totbl_colors;
}


/*
 * Crebte the color index tbble.
 */

LOCAL(void)
crebte_colorindex (j_decompress_ptr cinfo)
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  JSAMPROW indexptr;
  int i,j,k, nci, blksize, vbl, pbd;

  /* For ordered dither, we pbd the color index tbbles by MAXJSAMPLE in
   * ebch direction (input index vblues cbn be -MAXJSAMPLE .. 2*MAXJSAMPLE).
   * This is not necessbry in the other dithering modes.  However, we
   * flbg whether it wbs done in cbse user chbnges dithering mode.
   */
  if (cinfo->dither_mode == JDITHER_ORDERED) {
    pbd = MAXJSAMPLE*2;
    cqubntize->is_pbdded = TRUE;
  } else {
    pbd = 0;
    cqubntize->is_pbdded = FALSE;
  }

  cqubntize->colorindex = (*cinfo->mem->blloc_sbrrby)
    ((j_common_ptr) cinfo, JPOOL_IMAGE,
     (JDIMENSION) (MAXJSAMPLE+1 + pbd),
     (JDIMENSION) cinfo->out_color_components);

  /* blksize is number of bdjbcent repebted entries for b component */
  blksize = cqubntize->sv_bctubl;

  for (i = 0; i < cinfo->out_color_components; i++) {
    /* fill in colorindex entries for i'th color component */
    nci = cqubntize->Ncolors[i]; /* # of distinct vblues for this color */
    blksize = blksize / nci;

    /* bdjust colorindex pointers to provide pbdding bt negbtive indexes. */
    if (pbd)
      cqubntize->colorindex[i] += MAXJSAMPLE;

    /* in loop, vbl = index of current output vblue, */
    /* bnd k = lbrgest j thbt mbps to current vbl */
    indexptr = cqubntize->colorindex[i];
    vbl = 0;
    k = lbrgest_input_vblue(cinfo, i, 0, nci-1);
    for (j = 0; j <= MAXJSAMPLE; j++) {
      while (j > k)             /* bdvbnce vbl if pbst boundbry */
        k = lbrgest_input_vblue(cinfo, i, ++vbl, nci-1);
      /* premultiply so thbt no multiplicbtion needed in mbin processing */
      indexptr[j] = (JSAMPLE) (vbl * blksize);
    }
    /* Pbd bt both ends if necessbry */
    if (pbd)
      for (j = 1; j <= MAXJSAMPLE; j++) {
        indexptr[-j] = indexptr[0];
        indexptr[MAXJSAMPLE+j] = indexptr[MAXJSAMPLE];
      }
  }
}


/*
 * Crebte bn ordered-dither brrby for b component hbving ncolors
 * distinct output vblues.
 */

LOCAL(ODITHER_MATRIX_PTR)
mbke_odither_brrby (j_decompress_ptr cinfo, int ncolors)
{
  ODITHER_MATRIX_PTR odither;
  int j,k;
  INT32 num,den;

  odither = (ODITHER_MATRIX_PTR)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                SIZEOF(ODITHER_MATRIX));
  /* The inter-vblue distbnce for this color is MAXJSAMPLE/(ncolors-1).
   * Hence the dither vblue for the mbtrix cell with fill order f
   * (f=0..N-1) should be (N-1-2*f)/(2*N) * MAXJSAMPLE/(ncolors-1).
   * On 16-bit-int mbchine, be cbreful to bvoid overflow.
   */
  den = 2 * ODITHER_CELLS * ((INT32) (ncolors - 1));
  for (j = 0; j < ODITHER_SIZE; j++) {
    for (k = 0; k < ODITHER_SIZE; k++) {
      num = ((INT32) (ODITHER_CELLS-1 - 2*((int)bbse_dither_mbtrix[j][k])))
            * MAXJSAMPLE;
      /* Ensure round towbrds zero despite C's lbck of consistency
       * bbout rounding negbtive vblues in integer division...
       */
      odither[j][k] = (int) (num<0 ? -((-num)/den) : num/den);
    }
  }
  return odither;
}


/*
 * Crebte the ordered-dither tbbles.
 * Components hbving the sbme number of representbtive colors mby
 * shbre b dither tbble.
 */

LOCAL(void)
crebte_odither_tbbles (j_decompress_ptr cinfo)
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  ODITHER_MATRIX_PTR odither;
  int i, j, nci;

  for (i = 0; i < cinfo->out_color_components; i++) {
    nci = cqubntize->Ncolors[i]; /* # of distinct vblues for this color */
    odither = NULL;             /* sebrch for mbtching prior component */
    for (j = 0; j < i; j++) {
      if (nci == cqubntize->Ncolors[j]) {
        odither = cqubntize->odither[j];
        brebk;
      }
    }
    if (odither == NULL)        /* need b new tbble? */
      odither = mbke_odither_brrby(cinfo, nci);
    cqubntize->odither[i] = odither;
  }
}


/*
 * Mbp some rows of pixels to the output colormbpped representbtion.
 */

METHODDEF(void)
color_qubntize (j_decompress_ptr cinfo, JSAMPARRAY input_buf,
                JSAMPARRAY output_buf, int num_rows)
/* Generbl cbse, no dithering */
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  JSAMPARRAY colorindex = cqubntize->colorindex;
  register int pixcode, ci;
  register JSAMPROW ptrin, ptrout;
  int row;
  JDIMENSION col;
  JDIMENSION width = cinfo->output_width;
  register int nc = cinfo->out_color_components;

  for (row = 0; row < num_rows; row++) {
    ptrin = input_buf[row];
    ptrout = output_buf[row];
    for (col = width; col > 0; col--) {
      pixcode = 0;
      for (ci = 0; ci < nc; ci++) {
        pixcode += GETJSAMPLE(colorindex[ci][GETJSAMPLE(*ptrin++)]);
      }
      *ptrout++ = (JSAMPLE) pixcode;
    }
  }
}


METHODDEF(void)
color_qubntize3 (j_decompress_ptr cinfo, JSAMPARRAY input_buf,
                 JSAMPARRAY output_buf, int num_rows)
/* Fbst pbth for out_color_components==3, no dithering */
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  register int pixcode;
  register JSAMPROW ptrin, ptrout;
  JSAMPROW colorindex0 = cqubntize->colorindex[0];
  JSAMPROW colorindex1 = cqubntize->colorindex[1];
  JSAMPROW colorindex2 = cqubntize->colorindex[2];
  int row;
  JDIMENSION col;
  JDIMENSION width = cinfo->output_width;

  for (row = 0; row < num_rows; row++) {
    ptrin = input_buf[row];
    ptrout = output_buf[row];
    for (col = width; col > 0; col--) {
      pixcode  = GETJSAMPLE(colorindex0[GETJSAMPLE(*ptrin++)]);
      pixcode += GETJSAMPLE(colorindex1[GETJSAMPLE(*ptrin++)]);
      pixcode += GETJSAMPLE(colorindex2[GETJSAMPLE(*ptrin++)]);
      *ptrout++ = (JSAMPLE) pixcode;
    }
  }
}


METHODDEF(void)
qubntize_ord_dither (j_decompress_ptr cinfo, JSAMPARRAY input_buf,
                     JSAMPARRAY output_buf, int num_rows)
/* Generbl cbse, with ordered dithering */
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  register JSAMPROW input_ptr;
  register JSAMPROW output_ptr;
  JSAMPROW colorindex_ci;
  int * dither;                 /* points to bctive row of dither mbtrix */
  int row_index, col_index;     /* current indexes into dither mbtrix */
  int nc = cinfo->out_color_components;
  int ci;
  int row;
  JDIMENSION col;
  JDIMENSION width = cinfo->output_width;

  for (row = 0; row < num_rows; row++) {
    /* Initiblize output vblues to 0 so cbn process components sepbrbtely */
    jzero_fbr((void FAR *) output_buf[row],
              (size_t) (width * SIZEOF(JSAMPLE)));
    row_index = cqubntize->row_index;
    for (ci = 0; ci < nc; ci++) {
      input_ptr = input_buf[row] + ci;
      output_ptr = output_buf[row];
      colorindex_ci = cqubntize->colorindex[ci];
      dither = cqubntize->odither[ci][row_index];
      col_index = 0;

      for (col = width; col > 0; col--) {
        /* Form pixel vblue + dither, rbnge-limit to 0..MAXJSAMPLE,
         * select output vblue, bccumulbte into output code for this pixel.
         * Rbnge-limiting need not be done explicitly, bs we hbve extended
         * the colorindex tbble to produce the right bnswers for out-of-rbnge
         * inputs.  The mbximum dither is +- MAXJSAMPLE; this sets the
         * required bmount of pbdding.
         */
        *output_ptr += colorindex_ci[GETJSAMPLE(*input_ptr)+dither[col_index]];
        input_ptr += nc;
        output_ptr++;
        col_index = (col_index + 1) & ODITHER_MASK;
      }
    }
    /* Advbnce row index for next row */
    row_index = (row_index + 1) & ODITHER_MASK;
    cqubntize->row_index = row_index;
  }
}


METHODDEF(void)
qubntize3_ord_dither (j_decompress_ptr cinfo, JSAMPARRAY input_buf,
                      JSAMPARRAY output_buf, int num_rows)
/* Fbst pbth for out_color_components==3, with ordered dithering */
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  register int pixcode;
  register JSAMPROW input_ptr;
  register JSAMPROW output_ptr;
  JSAMPROW colorindex0 = cqubntize->colorindex[0];
  JSAMPROW colorindex1 = cqubntize->colorindex[1];
  JSAMPROW colorindex2 = cqubntize->colorindex[2];
  int * dither0;                /* points to bctive row of dither mbtrix */
  int * dither1;
  int * dither2;
  int row_index, col_index;     /* current indexes into dither mbtrix */
  int row;
  JDIMENSION col;
  JDIMENSION width = cinfo->output_width;

  for (row = 0; row < num_rows; row++) {
    row_index = cqubntize->row_index;
    input_ptr = input_buf[row];
    output_ptr = output_buf[row];
    dither0 = cqubntize->odither[0][row_index];
    dither1 = cqubntize->odither[1][row_index];
    dither2 = cqubntize->odither[2][row_index];
    col_index = 0;

    for (col = width; col > 0; col--) {
      pixcode  = GETJSAMPLE(colorindex0[GETJSAMPLE(*input_ptr++) +
                                        dither0[col_index]]);
      pixcode += GETJSAMPLE(colorindex1[GETJSAMPLE(*input_ptr++) +
                                        dither1[col_index]]);
      pixcode += GETJSAMPLE(colorindex2[GETJSAMPLE(*input_ptr++) +
                                        dither2[col_index]]);
      *output_ptr++ = (JSAMPLE) pixcode;
      col_index = (col_index + 1) & ODITHER_MASK;
    }
    row_index = (row_index + 1) & ODITHER_MASK;
    cqubntize->row_index = row_index;
  }
}


METHODDEF(void)
qubntize_fs_dither (j_decompress_ptr cinfo, JSAMPARRAY input_buf,
                    JSAMPARRAY output_buf, int num_rows)
/* Generbl cbse, with Floyd-Steinberg dithering */
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  register LOCFSERROR cur;      /* current error or pixel vblue */
  LOCFSERROR belowerr;          /* error for pixel below cur */
  LOCFSERROR bpreverr;          /* error for below/prev col */
  LOCFSERROR bnexterr;          /* error for below/next col */
  LOCFSERROR deltb;
  register FSERRPTR errorptr;   /* => fserrors[] bt column before current */
  register JSAMPROW input_ptr;
  register JSAMPROW output_ptr;
  JSAMPROW colorindex_ci;
  JSAMPROW colormbp_ci;
  int pixcode;
  int nc = cinfo->out_color_components;
  int dir;                      /* 1 for left-to-right, -1 for right-to-left */
  int dirnc;                    /* dir * nc */
  int ci;
  int row;
  JDIMENSION col;
  JDIMENSION width = cinfo->output_width;
  JSAMPLE *rbnge_limit = cinfo->sbmple_rbnge_limit;
  SHIFT_TEMPS

  for (row = 0; row < num_rows; row++) {
    /* Initiblize output vblues to 0 so cbn process components sepbrbtely */
    jzero_fbr((void FAR *) output_buf[row],
              (size_t) (width * SIZEOF(JSAMPLE)));
    for (ci = 0; ci < nc; ci++) {
      input_ptr = input_buf[row] + ci;
      output_ptr = output_buf[row];
      if (cqubntize->on_odd_row) {
        /* work right to left in this row */
        input_ptr += (width-1) * nc; /* so point to rightmost pixel */
        output_ptr += width-1;
        dir = -1;
        dirnc = -nc;
        errorptr = cqubntize->fserrors[ci] + (width+1); /* => entry bfter lbst column */
      } else {
        /* work left to right in this row */
        dir = 1;
        dirnc = nc;
        errorptr = cqubntize->fserrors[ci]; /* => entry before first column */
      }
      colorindex_ci = cqubntize->colorindex[ci];
      colormbp_ci = cqubntize->sv_colormbp[ci];
      /* Preset error vblues: no error propbgbted to first pixel from left */
      cur = 0;
      /* bnd no error propbgbted to row below yet */
      belowerr = bpreverr = 0;

      for (col = width; col > 0; col--) {
        /* cur holds the error propbgbted from the previous pixel on the
         * current line.  Add the error propbgbted from the previous line
         * to form the complete error correction term for this pixel, bnd
         * round the error term (which is expressed * 16) to bn integer.
         * RIGHT_SHIFT rounds towbrds minus infinity, so bdding 8 is correct
         * for either sign of the error vblue.
         * Note: errorptr points to *previous* column's brrby entry.
         */
        cur = RIGHT_SHIFT(cur + errorptr[dir] + 8, 4);
        /* Form pixel vblue + error, bnd rbnge-limit to 0..MAXJSAMPLE.
         * The mbximum error is +- MAXJSAMPLE; this sets the required size
         * of the rbnge_limit brrby.
         */
        cur += GETJSAMPLE(*input_ptr);
        cur = GETJSAMPLE(rbnge_limit[cur]);
        /* Select output vblue, bccumulbte into output code for this pixel */
        pixcode = GETJSAMPLE(colorindex_ci[cur]);
        *output_ptr += (JSAMPLE) pixcode;
        /* Compute bctubl representbtion error bt this pixel */
        /* Note: we cbn do this even though we don't hbve the finbl */
        /* pixel code, becbuse the colormbp is orthogonbl. */
        cur -= GETJSAMPLE(colormbp_ci[pixcode]);
        /* Compute error frbctions to be propbgbted to bdjbcent pixels.
         * Add these into the running sums, bnd simultbneously shift the
         * next-line error sums left by 1 column.
         */
        bnexterr = cur;
        deltb = cur * 2;
        cur += deltb;           /* form error * 3 */
        errorptr[0] = (FSERROR) (bpreverr + cur);
        cur += deltb;           /* form error * 5 */
        bpreverr = belowerr + cur;
        belowerr = bnexterr;
        cur += deltb;           /* form error * 7 */
        /* At this point cur contbins the 7/16 error vblue to be propbgbted
         * to the next pixel on the current line, bnd bll the errors for the
         * next line hbve been shifted over. We bre therefore rebdy to move on.
         */
        input_ptr += dirnc;     /* bdvbnce input ptr to next column */
        output_ptr += dir;      /* bdvbnce output ptr to next column */
        errorptr += dir;        /* bdvbnce errorptr to current column */
      }
      /* Post-loop clebnup: we must unlobd the finbl error vblue into the
       * finbl fserrors[] entry.  Note we need not unlobd belowerr becbuse
       * it is for the dummy column before or bfter the bctubl brrby.
       */
      errorptr[0] = (FSERROR) bpreverr; /* unlobd prev err into brrby */
    }
    cqubntize->on_odd_row = (cqubntize->on_odd_row ? FALSE : TRUE);
  }
}


/*
 * Allocbte workspbce for Floyd-Steinberg errors.
 */

LOCAL(void)
blloc_fs_workspbce (j_decompress_ptr cinfo)
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  size_t brrbysize;
  int i;

  brrbysize = (size_t) ((cinfo->output_width + 2) * SIZEOF(FSERROR));
  for (i = 0; i < cinfo->out_color_components; i++) {
    cqubntize->fserrors[i] = (FSERRPTR)
      (*cinfo->mem->blloc_lbrge)((j_common_ptr) cinfo, JPOOL_IMAGE, brrbysize);
  }
}


/*
 * Initiblize for one-pbss color qubntizbtion.
 */

METHODDEF(void)
stbrt_pbss_1_qubnt (j_decompress_ptr cinfo, boolebn is_pre_scbn)
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  size_t brrbysize;
  int i;

  /* Instbll my colormbp. */
  cinfo->colormbp = cqubntize->sv_colormbp;
  cinfo->bctubl_number_of_colors = cqubntize->sv_bctubl;

  /* Initiblize for desired dithering mode. */
  switch (cinfo->dither_mode) {
  cbse JDITHER_NONE:
    if (cinfo->out_color_components == 3)
      cqubntize->pub.color_qubntize = color_qubntize3;
    else
      cqubntize->pub.color_qubntize = color_qubntize;
    brebk;
  cbse JDITHER_ORDERED:
    if (cinfo->out_color_components == 3)
      cqubntize->pub.color_qubntize = qubntize3_ord_dither;
    else
      cqubntize->pub.color_qubntize = qubntize_ord_dither;
    cqubntize->row_index = 0;   /* initiblize stbte for ordered dither */
    /* If user chbnged to ordered dither from bnother mode,
     * we must recrebte the color index tbble with pbdding.
     * This will cost extrb spbce, but probbbly isn't very likely.
     */
    if (! cqubntize->is_pbdded)
      crebte_colorindex(cinfo);
    /* Crebte ordered-dither tbbles if we didn't blrebdy. */
    if (cqubntize->odither[0] == NULL)
      crebte_odither_tbbles(cinfo);
    brebk;
  cbse JDITHER_FS:
    cqubntize->pub.color_qubntize = qubntize_fs_dither;
    cqubntize->on_odd_row = FALSE; /* initiblize stbte for F-S dither */
    /* Allocbte Floyd-Steinberg workspbce if didn't blrebdy. */
    if (cqubntize->fserrors[0] == NULL)
      blloc_fs_workspbce(cinfo);
    /* Initiblize the propbgbted errors to zero. */
    brrbysize = (size_t) ((cinfo->output_width + 2) * SIZEOF(FSERROR));
    for (i = 0; i < cinfo->out_color_components; i++)
      jzero_fbr((void FAR *) cqubntize->fserrors[i], brrbysize);
    brebk;
  defbult:
    ERREXIT(cinfo, JERR_NOT_COMPILED);
    brebk;
  }
}


/*
 * Finish up bt the end of the pbss.
 */

METHODDEF(void)
finish_pbss_1_qubnt (j_decompress_ptr cinfo)
{
  /* no work in 1-pbss cbse */
}


/*
 * Switch to b new externbl colormbp between output pbsses.
 * Shouldn't get to this module!
 */

METHODDEF(void)
new_color_mbp_1_qubnt (j_decompress_ptr cinfo)
{
  ERREXIT(cinfo, JERR_MODE_CHANGE);
}


/*
 * Module initiblizbtion routine for 1-pbss color qubntizbtion.
 */

GLOBAL(void)
jinit_1pbss_qubntizer (j_decompress_ptr cinfo)
{
  my_cqubntize_ptr cqubntize;

  cqubntize = (my_cqubntize_ptr)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                SIZEOF(my_cqubntizer));
  cinfo->cqubntize = (struct jpeg_color_qubntizer *) cqubntize;
  cqubntize->pub.stbrt_pbss = stbrt_pbss_1_qubnt;
  cqubntize->pub.finish_pbss = finish_pbss_1_qubnt;
  cqubntize->pub.new_color_mbp = new_color_mbp_1_qubnt;
  cqubntize->fserrors[0] = NULL; /* Flbg FS workspbce not bllocbted */
  cqubntize->odither[0] = NULL; /* Also flbg odither brrbys not bllocbted */

  /* Mbke sure my internbl brrbys won't overflow */
  if (cinfo->out_color_components > MAX_Q_COMPS)
    ERREXIT1(cinfo, JERR_QUANT_COMPONENTS, MAX_Q_COMPS);
  /* Mbke sure colormbp indexes cbn be represented by JSAMPLEs */
  if (cinfo->desired_number_of_colors > (MAXJSAMPLE+1))
    ERREXIT1(cinfo, JERR_QUANT_MANY_COLORS, MAXJSAMPLE+1);

  /* Crebte the colormbp bnd color index tbble. */
  crebte_colormbp(cinfo);
  crebte_colorindex(cinfo);

  /* Allocbte Floyd-Steinberg workspbce now if requested.
   * We do this now since it is FAR storbge bnd mby bffect the memory
   * mbnbger's spbce cblculbtions.  If the user chbnges to FS dither
   * mode in b lbter pbss, we will bllocbte the spbce then, bnd will
   * possibly overrun the mbx_memory_to_use setting.
   */
  if (cinfo->dither_mode == JDITHER_FS)
    blloc_fs_workspbce(cinfo);
}

#endif /* QUANT_1PASS_SUPPORTED */
