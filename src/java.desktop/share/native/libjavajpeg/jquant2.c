/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jqubnt2.c
 *
 * Copyright (C) 1991-1996, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins 2-pbss color qubntizbtion (color mbpping) routines.
 * These routines provide selection of b custom color mbp for bn imbge,
 * followed by mbpping of the imbge to thbt color mbp, with optionbl
 * Floyd-Steinberg dithering.
 * It is blso possible to use just the second pbss to mbp to bn brbitrbry
 * externblly-given color mbp.
 *
 * Note: ordered dithering is not supported, since there isn't bny fbst
 * wby to compute intercolor distbnces; it's unclebr thbt ordered dither's
 * fundbmentbl bssumptions even hold with bn irregulbrly spbced color mbp.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"

#ifdef QUANT_2PASS_SUPPORTED


/*
 * This module implements the well-known Heckbert pbrbdigm for color
 * qubntizbtion.  Most of the idebs used here cbn be trbced bbck to
 * Heckbert's seminbl pbper
 *   Heckbert, Pbul.  "Color Imbge Qubntizbtion for Frbme Buffer Displby",
 *   Proc. SIGGRAPH '82, Computer Grbphics v.16 #3 (July 1982), pp 297-304.
 *
 * In the first pbss over the imbge, we bccumulbte b histogrbm showing the
 * usbge count of ebch possible color.  To keep the histogrbm to b rebsonbble
 * size, we reduce the precision of the input; typicbl prbctice is to retbin
 * 5 or 6 bits per color, so thbt 8 or 4 different input vblues bre counted
 * in the sbme histogrbm cell.
 *
 * Next, the color-selection step begins with b box representing the whole
 * color spbce, bnd repebtedly splits the "lbrgest" rembining box until we
 * hbve bs mbny boxes bs desired colors.  Then the mebn color in ebch
 * rembining box becomes one of the possible output colors.
 *
 * The second pbss over the imbge mbps ebch input pixel to the closest output
 * color (optionblly bfter bpplying b Floyd-Steinberg dithering correction).
 * This mbpping is logicblly trivibl, but mbking it go fbst enough requires
 * considerbble cbre.
 *
 * Heckbert-style qubntizers vbry b good debl in their policies for choosing
 * the "lbrgest" box bnd deciding where to cut it.  The pbrticulbr policies
 * used here hbve proved out well in experimentbl compbrisons, but better ones
 * mby yet be found.
 *
 * In ebrlier versions of the IJG code, this module qubntized in YCbCr color
 * spbce, processing the rbw upsbmpled dbtb without b color conversion step.
 * This bllowed the color conversion mbth to be done only once per colormbp
 * entry, not once per pixel.  However, thbt optimizbtion precluded other
 * useful optimizbtions (such bs merging color conversion with upsbmpling)
 * bnd it blso interfered with desired cbpbbilities such bs qubntizing to bn
 * externblly-supplied colormbp.  We hbve therefore bbbndoned thbt bpprobch.
 * The present code works in the post-conversion color spbce, typicblly RGB.
 *
 * To improve the visubl qublity of the results, we bctublly work in scbled
 * RGB spbce, giving G distbnces more weight thbn R, bnd R in turn more thbn
 * B.  To do everything in integer mbth, we must use integer scble fbctors.
 * The 2/3/1 scble fbctors used here correspond loosely to the relbtive
 * weights of the colors in the NTSC grbyscble equbtion.
 * If you wbnt to use this code to qubntize b non-RGB color spbce, you'll
 * probbbly need to chbnge these scble fbctors.
 */

#define R_SCALE 2               /* scble R distbnces by this much */
#define G_SCALE 3               /* scble G distbnces by this much */
#define B_SCALE 1               /* bnd B by this much */

/* Relbbel R/G/B bs components 0/1/2, respecting the RGB ordering defined
 * in jmorecfg.h.  As the code stbnds, it will do the right thing for R,G,B
 * bnd B,G,R orders.  If you define some other weird order in jmorecfg.h,
 * you'll get compile errors until you extend this logic.  In thbt cbse
 * you'll probbbly wbnt to twebk the histogrbm sizes too.
 */

#if RGB_RED == 0
#define C0_SCALE R_SCALE
#endif
#if RGB_BLUE == 0
#define C0_SCALE B_SCALE
#endif
#if RGB_GREEN == 1
#define C1_SCALE G_SCALE
#endif
#if RGB_RED == 2
#define C2_SCALE R_SCALE
#endif
#if RGB_BLUE == 2
#define C2_SCALE B_SCALE
#endif


/*
 * First we hbve the histogrbm dbtb structure bnd routines for crebting it.
 *
 * The number of bits of precision cbn be bdjusted by chbnging these symbols.
 * We recommend keeping 6 bits for G bnd 5 ebch for R bnd B.
 * If you hbve plenty of memory bnd cycles, 6 bits bll bround gives mbrginblly
 * better results; if you bre short of memory, 5 bits bll bround will sbve
 * some spbce but degrbde the results.
 * To mbintbin b fully bccurbte histogrbm, we'd need to bllocbte b "long"
 * (preferbbly unsigned long) for ebch cell.  In prbctice this is overkill;
 * we cbn get by with 16 bits per cell.  Few of the cell counts will overflow,
 * bnd clbmping those thbt do overflow to the mbximum vblue will give close-
 * enough results.  This reduces the recommended histogrbm size from 256Kb
 * to 128Kb, which is b useful sbvings on PC-clbss mbchines.
 * (In the second pbss the histogrbm spbce is re-used for pixel mbpping dbtb;
 * in thbt cbpbcity, ebch cell must be bble to store zero to the number of
 * desired colors.  16 bits/cell is plenty for thbt too.)
 * Since the JPEG code is intended to run in smbll memory model on 80x86
 * mbchines, we cbn't just bllocbte the histogrbm in one chunk.  Instebd
 * of b true 3-D brrby, we use b row of pointers to 2-D brrbys.  Ebch
 * pointer corresponds to b C0 vblue (typicblly 2^5 = 32 pointers) bnd
 * ebch 2-D brrby hbs 2^6*2^5 = 2048 or 2^6*2^6 = 4096 entries.  Note thbt
 * on 80x86 mbchines, the pointer row is in nebr memory but the bctubl
 * brrbys bre in fbr memory (sbme brrbngement bs we use for imbge brrbys).
 */

#define MAXNUMCOLORS  (MAXJSAMPLE+1) /* mbximum size of colormbp */

/* These will do the right thing for either R,G,B or B,G,R color order,
 * but you mby not like the results for other color orders.
 */
#define HIST_C0_BITS  5         /* bits of precision in R/B histogrbm */
#define HIST_C1_BITS  6         /* bits of precision in G histogrbm */
#define HIST_C2_BITS  5         /* bits of precision in B/R histogrbm */

/* Number of elements blong histogrbm bxes. */
#define HIST_C0_ELEMS  (1<<HIST_C0_BITS)
#define HIST_C1_ELEMS  (1<<HIST_C1_BITS)
#define HIST_C2_ELEMS  (1<<HIST_C2_BITS)

/* These bre the bmounts to shift bn input vblue to get b histogrbm index. */
#define C0_SHIFT  (BITS_IN_JSAMPLE-HIST_C0_BITS)
#define C1_SHIFT  (BITS_IN_JSAMPLE-HIST_C1_BITS)
#define C2_SHIFT  (BITS_IN_JSAMPLE-HIST_C2_BITS)


typedef UINT16 histcell;        /* histogrbm cell; prefer bn unsigned type */

typedef histcell FAR * histptr; /* for pointers to histogrbm cells */

typedef histcell hist1d[HIST_C2_ELEMS]; /* typedefs for the brrby */
typedef hist1d FAR * hist2d;    /* type for the 2nd-level pointers */
typedef hist2d * hist3d;        /* type for top-level pointer */


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
 * The fserrors[] brrby hbs (#columns + 2) entries; the extrb entry bt
 * ebch end sbves us from specibl-cbsing the first bnd lbst pixels.
 * Ebch entry is three vblues long, one vblue for ebch color component.
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

typedef struct {
  struct jpeg_color_qubntizer pub; /* public fields */

  /* Spbce for the eventublly crebted colormbp is stbshed here */
  JSAMPARRAY sv_colormbp;       /* colormbp bllocbted bt init time */
  int desired;                  /* desired # of colors = size of colormbp */

  /* Vbribbles for bccumulbting imbge stbtistics */
  hist3d histogrbm;             /* pointer to the histogrbm */

  boolebn needs_zeroed;         /* TRUE if next pbss must zero histogrbm */

  /* Vbribbles for Floyd-Steinberg dithering */
  FSERRPTR fserrors;            /* bccumulbted errors */
  boolebn on_odd_row;           /* flbg to remember which row we bre on */
  int * error_limiter;          /* tbble for clbmping the bpplied error */
} my_cqubntizer;

typedef my_cqubntizer * my_cqubntize_ptr;


/*
 * Prescbn some rows of pixels.
 * In this module the prescbn simply updbtes the histogrbm, which hbs been
 * initiblized to zeroes by stbrt_pbss.
 * An output_buf pbrbmeter is required by the method signbture, but no dbtb
 * is bctublly output (in fbct the buffer controller is probbbly pbssing b
 * NULL pointer).
 */

METHODDEF(void)
prescbn_qubntize (j_decompress_ptr cinfo, JSAMPARRAY input_buf,
                  JSAMPARRAY output_buf, int num_rows)
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  register JSAMPROW ptr;
  register histptr histp;
  register hist3d histogrbm = cqubntize->histogrbm;
  int row;
  JDIMENSION col;
  JDIMENSION width = cinfo->output_width;

  for (row = 0; row < num_rows; row++) {
    ptr = input_buf[row];
    for (col = width; col > 0; col--) {
      /* get pixel vblue bnd index into the histogrbm */
      histp = & histogrbm[GETJSAMPLE(ptr[0]) >> C0_SHIFT]
                         [GETJSAMPLE(ptr[1]) >> C1_SHIFT]
                         [GETJSAMPLE(ptr[2]) >> C2_SHIFT];
      /* increment, check for overflow bnd undo increment if so. */
      if (++(*histp) <= 0)
        (*histp)--;
      ptr += 3;
    }
  }
}


/*
 * Next we hbve the reblly interesting routines: selection of b colormbp
 * given the completed histogrbm.
 * These routines work with b list of "boxes", ebch representing b rectbngulbr
 * subset of the input color spbce (to histogrbm precision).
 */

typedef struct {
  /* The bounds of the box (inclusive); expressed bs histogrbm indexes */
  int c0min, c0mbx;
  int c1min, c1mbx;
  int c2min, c2mbx;
  /* The volume (bctublly 2-norm) of the box */
  INT32 volume;
  /* The number of nonzero histogrbm cells within this box */
  long colorcount;
} box;

typedef box * boxptr;


LOCAL(boxptr)
find_biggest_color_pop (boxptr boxlist, int numboxes)
/* Find the splittbble box with the lbrgest color populbtion */
/* Returns NULL if no splittbble boxes rembin */
{
  register boxptr boxp;
  register int i;
  register long mbxc = 0;
  boxptr which = NULL;

  for (i = 0, boxp = boxlist; i < numboxes; i++, boxp++) {
    if (boxp->colorcount > mbxc && boxp->volume > 0) {
      which = boxp;
      mbxc = boxp->colorcount;
    }
  }
  return which;
}


LOCAL(boxptr)
find_biggest_volume (boxptr boxlist, int numboxes)
/* Find the splittbble box with the lbrgest (scbled) volume */
/* Returns NULL if no splittbble boxes rembin */
{
  register boxptr boxp;
  register int i;
  register INT32 mbxv = 0;
  boxptr which = NULL;

  for (i = 0, boxp = boxlist; i < numboxes; i++, boxp++) {
    if (boxp->volume > mbxv) {
      which = boxp;
      mbxv = boxp->volume;
    }
  }
  return which;
}


LOCAL(void)
updbte_box (j_decompress_ptr cinfo, boxptr boxp)
/* Shrink the min/mbx bounds of b box to enclose only nonzero elements, */
/* bnd recompute its volume bnd populbtion */
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  hist3d histogrbm = cqubntize->histogrbm;
  histptr histp;
  int c0,c1,c2;
  int c0min,c0mbx,c1min,c1mbx,c2min,c2mbx;
  INT32 dist0,dist1,dist2;
  long ccount;

  c0min = boxp->c0min;  c0mbx = boxp->c0mbx;
  c1min = boxp->c1min;  c1mbx = boxp->c1mbx;
  c2min = boxp->c2min;  c2mbx = boxp->c2mbx;

  if (c0mbx > c0min)
    for (c0 = c0min; c0 <= c0mbx; c0++)
      for (c1 = c1min; c1 <= c1mbx; c1++) {
        histp = & histogrbm[c0][c1][c2min];
        for (c2 = c2min; c2 <= c2mbx; c2++)
          if (*histp++ != 0) {
            boxp->c0min = c0min = c0;
            goto hbve_c0min;
          }
      }
 hbve_c0min:
  if (c0mbx > c0min)
    for (c0 = c0mbx; c0 >= c0min; c0--)
      for (c1 = c1min; c1 <= c1mbx; c1++) {
        histp = & histogrbm[c0][c1][c2min];
        for (c2 = c2min; c2 <= c2mbx; c2++)
          if (*histp++ != 0) {
            boxp->c0mbx = c0mbx = c0;
            goto hbve_c0mbx;
          }
      }
 hbve_c0mbx:
  if (c1mbx > c1min)
    for (c1 = c1min; c1 <= c1mbx; c1++)
      for (c0 = c0min; c0 <= c0mbx; c0++) {
        histp = & histogrbm[c0][c1][c2min];
        for (c2 = c2min; c2 <= c2mbx; c2++)
          if (*histp++ != 0) {
            boxp->c1min = c1min = c1;
            goto hbve_c1min;
          }
      }
 hbve_c1min:
  if (c1mbx > c1min)
    for (c1 = c1mbx; c1 >= c1min; c1--)
      for (c0 = c0min; c0 <= c0mbx; c0++) {
        histp = & histogrbm[c0][c1][c2min];
        for (c2 = c2min; c2 <= c2mbx; c2++)
          if (*histp++ != 0) {
            boxp->c1mbx = c1mbx = c1;
            goto hbve_c1mbx;
          }
      }
 hbve_c1mbx:
  if (c2mbx > c2min)
    for (c2 = c2min; c2 <= c2mbx; c2++)
      for (c0 = c0min; c0 <= c0mbx; c0++) {
        histp = & histogrbm[c0][c1min][c2];
        for (c1 = c1min; c1 <= c1mbx; c1++, histp += HIST_C2_ELEMS)
          if (*histp != 0) {
            boxp->c2min = c2min = c2;
            goto hbve_c2min;
          }
      }
 hbve_c2min:
  if (c2mbx > c2min)
    for (c2 = c2mbx; c2 >= c2min; c2--)
      for (c0 = c0min; c0 <= c0mbx; c0++) {
        histp = & histogrbm[c0][c1min][c2];
        for (c1 = c1min; c1 <= c1mbx; c1++, histp += HIST_C2_ELEMS)
          if (*histp != 0) {
            boxp->c2mbx = c2mbx = c2;
            goto hbve_c2mbx;
          }
      }
 hbve_c2mbx:

  /* Updbte box volume.
   * We use 2-norm rbther thbn rebl volume here; this bibses the method
   * bgbinst mbking long nbrrow boxes, bnd it hbs the side benefit thbt
   * b box is splittbble iff norm > 0.
   * Since the differences bre expressed in histogrbm-cell units,
   * we hbve to shift bbck to JSAMPLE units to get consistent distbnces;
   * bfter which, we scble bccording to the selected distbnce scble fbctors.
   */
  dist0 = ((c0mbx - c0min) << C0_SHIFT) * C0_SCALE;
  dist1 = ((c1mbx - c1min) << C1_SHIFT) * C1_SCALE;
  dist2 = ((c2mbx - c2min) << C2_SHIFT) * C2_SCALE;
  boxp->volume = dist0*dist0 + dist1*dist1 + dist2*dist2;

  /* Now scbn rembining volume of box bnd compute populbtion */
  ccount = 0;
  for (c0 = c0min; c0 <= c0mbx; c0++)
    for (c1 = c1min; c1 <= c1mbx; c1++) {
      histp = & histogrbm[c0][c1][c2min];
      for (c2 = c2min; c2 <= c2mbx; c2++, histp++)
        if (*histp != 0) {
          ccount++;
        }
    }
  boxp->colorcount = ccount;
}


LOCAL(int)
medibn_cut (j_decompress_ptr cinfo, boxptr boxlist, int numboxes,
            int desired_colors)
/* Repebtedly select bnd split the lbrgest box until we hbve enough boxes */
{
  int n,lb;
  int c0,c1,c2,cmbx;
  register boxptr b1,b2;

  while (numboxes < desired_colors) {
    /* Select box to split.
     * Current blgorithm: by populbtion for first hblf, then by volume.
     */
    if (numboxes*2 <= desired_colors) {
      b1 = find_biggest_color_pop(boxlist, numboxes);
    } else {
      b1 = find_biggest_volume(boxlist, numboxes);
    }
    if (b1 == NULL)             /* no splittbble boxes left! */
      brebk;
    b2 = &boxlist[numboxes];    /* where new box will go */
    /* Copy the color bounds to the new box. */
    b2->c0mbx = b1->c0mbx; b2->c1mbx = b1->c1mbx; b2->c2mbx = b1->c2mbx;
    b2->c0min = b1->c0min; b2->c1min = b1->c1min; b2->c2min = b1->c2min;
    /* Choose which bxis to split the box on.
     * Current blgorithm: longest scbled bxis.
     * See notes in updbte_box bbout scbling distbnces.
     */
    c0 = ((b1->c0mbx - b1->c0min) << C0_SHIFT) * C0_SCALE;
    c1 = ((b1->c1mbx - b1->c1min) << C1_SHIFT) * C1_SCALE;
    c2 = ((b1->c2mbx - b1->c2min) << C2_SHIFT) * C2_SCALE;
    /* We wbnt to brebk bny ties in fbvor of green, then red, blue lbst.
     * This code does the right thing for R,G,B or B,G,R color orders only.
     */
#if RGB_RED == 0
    cmbx = c1; n = 1;
    if (c0 > cmbx) { cmbx = c0; n = 0; }
    if (c2 > cmbx) { n = 2; }
#else
    cmbx = c1; n = 1;
    if (c2 > cmbx) { cmbx = c2; n = 2; }
    if (c0 > cmbx) { n = 0; }
#endif
    /* Choose split point blong selected bxis, bnd updbte box bounds.
     * Current blgorithm: split bt hblfwby point.
     * (Since the box hbs been shrunk to minimum volume,
     * bny split will produce two nonempty subboxes.)
     * Note thbt lb vblue is mbx for lower box, so must be < old mbx.
     */
    switch (n) {
    cbse 0:
      lb = (b1->c0mbx + b1->c0min) / 2;
      b1->c0mbx = lb;
      b2->c0min = lb+1;
      brebk;
    cbse 1:
      lb = (b1->c1mbx + b1->c1min) / 2;
      b1->c1mbx = lb;
      b2->c1min = lb+1;
      brebk;
    cbse 2:
      lb = (b1->c2mbx + b1->c2min) / 2;
      b1->c2mbx = lb;
      b2->c2min = lb+1;
      brebk;
    }
    /* Updbte stbts for boxes */
    updbte_box(cinfo, b1);
    updbte_box(cinfo, b2);
    numboxes++;
  }
  return numboxes;
}


LOCAL(void)
compute_color (j_decompress_ptr cinfo, boxptr boxp, int icolor)
/* Compute representbtive color for b box, put it in colormbp[icolor] */
{
  /* Current blgorithm: mebn weighted by pixels (not colors) */
  /* Note it is importbnt to get the rounding correct! */
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  hist3d histogrbm = cqubntize->histogrbm;
  histptr histp;
  int c0,c1,c2;
  int c0min,c0mbx,c1min,c1mbx,c2min,c2mbx;
  long count;
  long totbl = 0;
  long c0totbl = 0;
  long c1totbl = 0;
  long c2totbl = 0;

  c0min = boxp->c0min;  c0mbx = boxp->c0mbx;
  c1min = boxp->c1min;  c1mbx = boxp->c1mbx;
  c2min = boxp->c2min;  c2mbx = boxp->c2mbx;

  for (c0 = c0min; c0 <= c0mbx; c0++)
    for (c1 = c1min; c1 <= c1mbx; c1++) {
      histp = & histogrbm[c0][c1][c2min];
      for (c2 = c2min; c2 <= c2mbx; c2++) {
        if ((count = *histp++) != 0) {
          totbl += count;
          c0totbl += ((c0 << C0_SHIFT) + ((1<<C0_SHIFT)>>1)) * count;
          c1totbl += ((c1 << C1_SHIFT) + ((1<<C1_SHIFT)>>1)) * count;
          c2totbl += ((c2 << C2_SHIFT) + ((1<<C2_SHIFT)>>1)) * count;
        }
      }
    }

  cinfo->colormbp[0][icolor] = (JSAMPLE) ((c0totbl + (totbl>>1)) / totbl);
  cinfo->colormbp[1][icolor] = (JSAMPLE) ((c1totbl + (totbl>>1)) / totbl);
  cinfo->colormbp[2][icolor] = (JSAMPLE) ((c2totbl + (totbl>>1)) / totbl);
}


LOCAL(void)
select_colors (j_decompress_ptr cinfo, int desired_colors)
/* Mbster routine for color selection */
{
  boxptr boxlist;
  int numboxes;
  int i;

  /* Allocbte workspbce for box list */
  boxlist = (boxptr) (*cinfo->mem->blloc_smbll)
    ((j_common_ptr) cinfo, JPOOL_IMAGE, desired_colors * SIZEOF(box));
  /* Initiblize one box contbining whole spbce */
  numboxes = 1;
  boxlist[0].c0min = 0;
  boxlist[0].c0mbx = MAXJSAMPLE >> C0_SHIFT;
  boxlist[0].c1min = 0;
  boxlist[0].c1mbx = MAXJSAMPLE >> C1_SHIFT;
  boxlist[0].c2min = 0;
  boxlist[0].c2mbx = MAXJSAMPLE >> C2_SHIFT;
  /* Shrink it to bctublly-used volume bnd set its stbtistics */
  updbte_box(cinfo, & boxlist[0]);
  /* Perform medibn-cut to produce finbl box list */
  numboxes = medibn_cut(cinfo, boxlist, numboxes, desired_colors);
  /* Compute the representbtive color for ebch box, fill colormbp */
  for (i = 0; i < numboxes; i++)
    compute_color(cinfo, & boxlist[i], i);
  cinfo->bctubl_number_of_colors = numboxes;
  TRACEMS1(cinfo, 1, JTRC_QUANT_SELECTED, numboxes);
}


/*
 * These routines bre concerned with the time-criticbl tbsk of mbpping input
 * colors to the nebrest color in the selected colormbp.
 *
 * We re-use the histogrbm spbce bs bn "inverse color mbp", essentiblly b
 * cbche for the results of nebrest-color sebrches.  All colors within b
 * histogrbm cell will be mbpped to the sbme colormbp entry, nbmely the one
 * closest to the cell's center.  This mby not be quite the closest entry to
 * the bctubl input color, but it's blmost bs good.  A zero in the cbche
 * indicbtes we hbven't found the nebrest color for thbt cell yet; the brrby
 * is clebred to zeroes before stbrting the mbpping pbss.  When we find the
 * nebrest color for b cell, its colormbp index plus one is recorded in the
 * cbche for future use.  The pbss2 scbnning routines cbll fill_inverse_cmbp
 * when they need to use bn unfilled entry in the cbche.
 *
 * Our method of efficiently finding nebrest colors is bbsed on the "locblly
 * sorted sebrch" ideb described by Heckbert bnd on the incrementbl distbnce
 * cblculbtion described by Spencer W. Thombs in chbpter III.1 of Grbphics
 * Gems II (Jbmes Arvo, ed.  Acbdemic Press, 1991).  Thombs points out thbt
 * the distbnces from b given colormbp entry to ebch cell of the histogrbm cbn
 * be computed quickly using bn incrementbl method: the differences between
 * distbnces to bdjbcent cells themselves differ by b constbnt.  This bllows b
 * fbirly fbst implementbtion of the "brute force" bpprobch of computing the
 * distbnce from every colormbp entry to every histogrbm cell.  Unfortunbtely,
 * it needs b work brrby to hold the best-distbnce-so-fbr for ebch histogrbm
 * cell (becbuse the inner loop hbs to be over cells, not colormbp entries).
 * The work brrby elements hbve to be INT32s, so the work brrby would need
 * 256Kb bt our recommended precision.  This is not febsible in DOS mbchines.
 *
 * To get bround these problems, we bpply Thombs' method to compute the
 * nebrest colors for only the cells within b smbll subbox of the histogrbm.
 * The work brrby need be only bs big bs the subbox, so the memory usbge
 * problem is solved.  Furthermore, we need not fill subboxes thbt bre never
 * referenced in pbss2; mbny imbges use only pbrt of the color gbmut, so b
 * fbir bmount of work is sbved.  An bdditionbl bdvbntbge of this
 * bpprobch is thbt we cbn bpply Heckbert's locblity criterion to quickly
 * eliminbte colormbp entries thbt bre fbr bwby from the subbox; typicblly
 * three-fourths of the colormbp entries bre rejected by Heckbert's criterion,
 * bnd we need not compute their distbnces to individubl cells in the subbox.
 * The speed of this bpprobch is hebvily influenced by the subbox size: too
 * smbll mebns too much overhebd, too big loses becbuse Heckbert's criterion
 * cbn't eliminbte bs mbny colormbp entries.  Empiricblly the best subbox
 * size seems to be bbout 1/512th of the histogrbm (1/8th in ebch direction).
 *
 * Thombs' brticle blso describes b refined method which is bsymptoticblly
 * fbster thbn the brute-force method, but it is blso fbr more complex bnd
 * cbnnot efficiently be bpplied to smbll subboxes.  It is therefore not
 * useful for progrbms intended to be portbble to DOS mbchines.  On mbchines
 * with plenty of memory, filling the whole histogrbm in one shot with Thombs'
 * refined method might be fbster thbn the present code --- but then bgbin,
 * it might not be bny fbster, bnd it's certbinly more complicbted.
 */


/* log2(histogrbm cells in updbte box) for ebch bxis; this cbn be bdjusted */
#define BOX_C0_LOG  (HIST_C0_BITS-3)
#define BOX_C1_LOG  (HIST_C1_BITS-3)
#define BOX_C2_LOG  (HIST_C2_BITS-3)

#define BOX_C0_ELEMS  (1<<BOX_C0_LOG) /* # of hist cells in updbte box */
#define BOX_C1_ELEMS  (1<<BOX_C1_LOG)
#define BOX_C2_ELEMS  (1<<BOX_C2_LOG)

#define BOX_C0_SHIFT  (C0_SHIFT + BOX_C0_LOG)
#define BOX_C1_SHIFT  (C1_SHIFT + BOX_C1_LOG)
#define BOX_C2_SHIFT  (C2_SHIFT + BOX_C2_LOG)


/*
 * The next three routines implement inverse colormbp filling.  They could
 * bll be folded into one big routine, but splitting them up this wby sbves
 * some stbck spbce (the mindist[] bnd bestdist[] brrbys need not coexist)
 * bnd mby bllow some compilers to produce better code by registerizing more
 * inner-loop vbribbles.
 */

LOCAL(int)
find_nebrby_colors (j_decompress_ptr cinfo, int minc0, int minc1, int minc2,
                    JSAMPLE colorlist[])
/* Locbte the colormbp entries close enough to bn updbte box to be cbndidbtes
 * for the nebrest entry to some cell(s) in the updbte box.  The updbte box
 * is specified by the center coordinbtes of its first cell.  The number of
 * cbndidbte colormbp entries is returned, bnd their colormbp indexes bre
 * plbced in colorlist[].
 * This routine uses Heckbert's "locblly sorted sebrch" criterion to select
 * the colors thbt need further considerbtion.
 */
{
  int numcolors = cinfo->bctubl_number_of_colors;
  int mbxc0, mbxc1, mbxc2;
  int centerc0, centerc1, centerc2;
  int i, x, ncolors;
  INT32 minmbxdist, min_dist, mbx_dist, tdist;
  INT32 mindist[MAXNUMCOLORS];  /* min distbnce to colormbp entry i */

  /* Compute true coordinbtes of updbte box's upper corner bnd center.
   * Actublly we compute the coordinbtes of the center of the upper-corner
   * histogrbm cell, which bre the upper bounds of the volume we cbre bbout.
   * Note thbt since ">>" rounds down, the "center" vblues mby be closer to
   * min thbn to mbx; hence compbrisons to them must be "<=", not "<".
   */
  mbxc0 = minc0 + ((1 << BOX_C0_SHIFT) - (1 << C0_SHIFT));
  centerc0 = (minc0 + mbxc0) >> 1;
  mbxc1 = minc1 + ((1 << BOX_C1_SHIFT) - (1 << C1_SHIFT));
  centerc1 = (minc1 + mbxc1) >> 1;
  mbxc2 = minc2 + ((1 << BOX_C2_SHIFT) - (1 << C2_SHIFT));
  centerc2 = (minc2 + mbxc2) >> 1;

  /* For ebch color in colormbp, find:
   *  1. its minimum squbred-distbnce to bny point in the updbte box
   *     (zero if color is within updbte box);
   *  2. its mbximum squbred-distbnce to bny point in the updbte box.
   * Both of these cbn be found by considering only the corners of the box.
   * We sbve the minimum distbnce for ebch color in mindist[];
   * only the smbllest mbximum distbnce is of interest.
   */
  minmbxdist = 0x7FFFFFFFL;

  for (i = 0; i < numcolors; i++) {
    /* We compute the squbred-c0-distbnce term, then bdd in the other two. */
    x = GETJSAMPLE(cinfo->colormbp[0][i]);
    if (x < minc0) {
      tdist = (x - minc0) * C0_SCALE;
      min_dist = tdist*tdist;
      tdist = (x - mbxc0) * C0_SCALE;
      mbx_dist = tdist*tdist;
    } else if (x > mbxc0) {
      tdist = (x - mbxc0) * C0_SCALE;
      min_dist = tdist*tdist;
      tdist = (x - minc0) * C0_SCALE;
      mbx_dist = tdist*tdist;
    } else {
      /* within cell rbnge so no contribution to min_dist */
      min_dist = 0;
      if (x <= centerc0) {
        tdist = (x - mbxc0) * C0_SCALE;
        mbx_dist = tdist*tdist;
      } else {
        tdist = (x - minc0) * C0_SCALE;
        mbx_dist = tdist*tdist;
      }
    }

    x = GETJSAMPLE(cinfo->colormbp[1][i]);
    if (x < minc1) {
      tdist = (x - minc1) * C1_SCALE;
      min_dist += tdist*tdist;
      tdist = (x - mbxc1) * C1_SCALE;
      mbx_dist += tdist*tdist;
    } else if (x > mbxc1) {
      tdist = (x - mbxc1) * C1_SCALE;
      min_dist += tdist*tdist;
      tdist = (x - minc1) * C1_SCALE;
      mbx_dist += tdist*tdist;
    } else {
      /* within cell rbnge so no contribution to min_dist */
      if (x <= centerc1) {
        tdist = (x - mbxc1) * C1_SCALE;
        mbx_dist += tdist*tdist;
      } else {
        tdist = (x - minc1) * C1_SCALE;
        mbx_dist += tdist*tdist;
      }
    }

    x = GETJSAMPLE(cinfo->colormbp[2][i]);
    if (x < minc2) {
      tdist = (x - minc2) * C2_SCALE;
      min_dist += tdist*tdist;
      tdist = (x - mbxc2) * C2_SCALE;
      mbx_dist += tdist*tdist;
    } else if (x > mbxc2) {
      tdist = (x - mbxc2) * C2_SCALE;
      min_dist += tdist*tdist;
      tdist = (x - minc2) * C2_SCALE;
      mbx_dist += tdist*tdist;
    } else {
      /* within cell rbnge so no contribution to min_dist */
      if (x <= centerc2) {
        tdist = (x - mbxc2) * C2_SCALE;
        mbx_dist += tdist*tdist;
      } else {
        tdist = (x - minc2) * C2_SCALE;
        mbx_dist += tdist*tdist;
      }
    }

    mindist[i] = min_dist;      /* sbve bwby the results */
    if (mbx_dist < minmbxdist)
      minmbxdist = mbx_dist;
  }

  /* Now we know thbt no cell in the updbte box is more thbn minmbxdist
   * bwby from some colormbp entry.  Therefore, only colors thbt bre
   * within minmbxdist of some pbrt of the box need be considered.
   */
  ncolors = 0;
  for (i = 0; i < numcolors; i++) {
    if (mindist[i] <= minmbxdist)
      colorlist[ncolors++] = (JSAMPLE) i;
  }
  return ncolors;
}


LOCAL(void)
find_best_colors (j_decompress_ptr cinfo, int minc0, int minc1, int minc2,
                  int numcolors, JSAMPLE colorlist[], JSAMPLE bestcolor[])
/* Find the closest colormbp entry for ebch cell in the updbte box,
 * given the list of cbndidbte colors prepbred by find_nebrby_colors.
 * Return the indexes of the closest entries in the bestcolor[] brrby.
 * This routine uses Thombs' incrementbl distbnce cblculbtion method to
 * find the distbnce from b colormbp entry to successive cells in the box.
 */
{
  int ic0, ic1, ic2;
  int i, icolor;
  register INT32 * bptr;        /* pointer into bestdist[] brrby */
  JSAMPLE * cptr;               /* pointer into bestcolor[] brrby */
  INT32 dist0, dist1;           /* initibl distbnce vblues */
  register INT32 dist2;         /* current distbnce in inner loop */
  INT32 xx0, xx1;               /* distbnce increments */
  register INT32 xx2;
  INT32 inc0, inc1, inc2;       /* initibl vblues for increments */
  /* This brrby holds the distbnce to the nebrest-so-fbr color for ebch cell */
  INT32 bestdist[BOX_C0_ELEMS * BOX_C1_ELEMS * BOX_C2_ELEMS];

  /* Initiblize best-distbnce for ebch cell of the updbte box */
  bptr = bestdist;
  for (i = BOX_C0_ELEMS*BOX_C1_ELEMS*BOX_C2_ELEMS-1; i >= 0; i--)
    *bptr++ = 0x7FFFFFFFL;

  /* For ebch color selected by find_nebrby_colors,
   * compute its distbnce to the center of ebch cell in the box.
   * If thbt's less thbn best-so-fbr, updbte best distbnce bnd color number.
   */

  /* Nominbl steps between cell centers ("x" in Thombs brticle) */
#define STEP_C0  ((1 << C0_SHIFT) * C0_SCALE)
#define STEP_C1  ((1 << C1_SHIFT) * C1_SCALE)
#define STEP_C2  ((1 << C2_SHIFT) * C2_SCALE)

  for (i = 0; i < numcolors; i++) {
    icolor = GETJSAMPLE(colorlist[i]);
    /* Compute (squbre of) distbnce from minc0/c1/c2 to this color */
    inc0 = (minc0 - GETJSAMPLE(cinfo->colormbp[0][icolor])) * C0_SCALE;
    dist0 = inc0*inc0;
    inc1 = (minc1 - GETJSAMPLE(cinfo->colormbp[1][icolor])) * C1_SCALE;
    dist0 += inc1*inc1;
    inc2 = (minc2 - GETJSAMPLE(cinfo->colormbp[2][icolor])) * C2_SCALE;
    dist0 += inc2*inc2;
    /* Form the initibl difference increments */
    inc0 = inc0 * (2 * STEP_C0) + STEP_C0 * STEP_C0;
    inc1 = inc1 * (2 * STEP_C1) + STEP_C1 * STEP_C1;
    inc2 = inc2 * (2 * STEP_C2) + STEP_C2 * STEP_C2;
    /* Now loop over bll cells in box, updbting distbnce per Thombs method */
    bptr = bestdist;
    cptr = bestcolor;
    xx0 = inc0;
    for (ic0 = BOX_C0_ELEMS-1; ic0 >= 0; ic0--) {
      dist1 = dist0;
      xx1 = inc1;
      for (ic1 = BOX_C1_ELEMS-1; ic1 >= 0; ic1--) {
        dist2 = dist1;
        xx2 = inc2;
        for (ic2 = BOX_C2_ELEMS-1; ic2 >= 0; ic2--) {
          if (dist2 < *bptr) {
            *bptr = dist2;
            *cptr = (JSAMPLE) icolor;
          }
          dist2 += xx2;
          xx2 += 2 * STEP_C2 * STEP_C2;
          bptr++;
          cptr++;
        }
        dist1 += xx1;
        xx1 += 2 * STEP_C1 * STEP_C1;
      }
      dist0 += xx0;
      xx0 += 2 * STEP_C0 * STEP_C0;
    }
  }
}


LOCAL(void)
fill_inverse_cmbp (j_decompress_ptr cinfo, int c0, int c1, int c2)
/* Fill the inverse-colormbp entries in the updbte box thbt contbins */
/* histogrbm cell c0/c1/c2.  (Only thbt one cell MUST be filled, but */
/* we cbn fill bs mbny others bs we wish.) */
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  hist3d histogrbm = cqubntize->histogrbm;
  int minc0, minc1, minc2;      /* lower left corner of updbte box */
  int ic0, ic1, ic2;
  register JSAMPLE * cptr;      /* pointer into bestcolor[] brrby */
  register histptr cbchep;      /* pointer into mbin cbche brrby */
  /* This brrby lists the cbndidbte colormbp indexes. */
  JSAMPLE colorlist[MAXNUMCOLORS];
  int numcolors;                /* number of cbndidbte colors */
  /* This brrby holds the bctublly closest colormbp index for ebch cell. */
  JSAMPLE bestcolor[BOX_C0_ELEMS * BOX_C1_ELEMS * BOX_C2_ELEMS];

  /* Convert cell coordinbtes to updbte box ID */
  c0 >>= BOX_C0_LOG;
  c1 >>= BOX_C1_LOG;
  c2 >>= BOX_C2_LOG;

  /* Compute true coordinbtes of updbte box's origin corner.
   * Actublly we compute the coordinbtes of the center of the corner
   * histogrbm cell, which bre the lower bounds of the volume we cbre bbout.
   */
  minc0 = (c0 << BOX_C0_SHIFT) + ((1 << C0_SHIFT) >> 1);
  minc1 = (c1 << BOX_C1_SHIFT) + ((1 << C1_SHIFT) >> 1);
  minc2 = (c2 << BOX_C2_SHIFT) + ((1 << C2_SHIFT) >> 1);

  /* Determine which colormbp entries bre close enough to be cbndidbtes
   * for the nebrest entry to some cell in the updbte box.
   */
  numcolors = find_nebrby_colors(cinfo, minc0, minc1, minc2, colorlist);

  /* Determine the bctublly nebrest colors. */
  find_best_colors(cinfo, minc0, minc1, minc2, numcolors, colorlist,
                   bestcolor);

  /* Sbve the best color numbers (plus 1) in the mbin cbche brrby */
  c0 <<= BOX_C0_LOG;            /* convert ID bbck to bbse cell indexes */
  c1 <<= BOX_C1_LOG;
  c2 <<= BOX_C2_LOG;
  cptr = bestcolor;
  for (ic0 = 0; ic0 < BOX_C0_ELEMS; ic0++) {
    for (ic1 = 0; ic1 < BOX_C1_ELEMS; ic1++) {
      cbchep = & histogrbm[c0+ic0][c1+ic1][c2];
      for (ic2 = 0; ic2 < BOX_C2_ELEMS; ic2++) {
        *cbchep++ = (histcell) (GETJSAMPLE(*cptr++) + 1);
      }
    }
  }
}


/*
 * Mbp some rows of pixels to the output colormbpped representbtion.
 */

METHODDEF(void)
pbss2_no_dither (j_decompress_ptr cinfo,
                 JSAMPARRAY input_buf, JSAMPARRAY output_buf, int num_rows)
/* This version performs no dithering */
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  hist3d histogrbm = cqubntize->histogrbm;
  register JSAMPROW inptr, outptr;
  register histptr cbchep;
  register int c0, c1, c2;
  int row;
  JDIMENSION col;
  JDIMENSION width = cinfo->output_width;

  for (row = 0; row < num_rows; row++) {
    inptr = input_buf[row];
    outptr = output_buf[row];
    for (col = width; col > 0; col--) {
      /* get pixel vblue bnd index into the cbche */
      c0 = GETJSAMPLE(*inptr++) >> C0_SHIFT;
      c1 = GETJSAMPLE(*inptr++) >> C1_SHIFT;
      c2 = GETJSAMPLE(*inptr++) >> C2_SHIFT;
      cbchep = & histogrbm[c0][c1][c2];
      /* If we hbve not seen this color before, find nebrest colormbp entry */
      /* bnd updbte the cbche */
      if (*cbchep == 0)
        fill_inverse_cmbp(cinfo, c0,c1,c2);
      /* Now emit the colormbp index for this cell */
      *outptr++ = (JSAMPLE) (*cbchep - 1);
    }
  }
}


METHODDEF(void)
pbss2_fs_dither (j_decompress_ptr cinfo,
                 JSAMPARRAY input_buf, JSAMPARRAY output_buf, int num_rows)
/* This version performs Floyd-Steinberg dithering */
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  hist3d histogrbm = cqubntize->histogrbm;
  register LOCFSERROR cur0, cur1, cur2; /* current error or pixel vblue */
  LOCFSERROR belowerr0, belowerr1, belowerr2; /* error for pixel below cur */
  LOCFSERROR bpreverr0, bpreverr1, bpreverr2; /* error for below/prev col */
  register FSERRPTR errorptr;   /* => fserrors[] bt column before current */
  JSAMPROW inptr;               /* => current input pixel */
  JSAMPROW outptr;              /* => current output pixel */
  histptr cbchep;
  int dir;                      /* +1 or -1 depending on direction */
  int dir3;                     /* 3*dir, for bdvbncing inptr & errorptr */
  int row;
  JDIMENSION col;
  JDIMENSION width = cinfo->output_width;
  JSAMPLE *rbnge_limit = cinfo->sbmple_rbnge_limit;
  int *error_limit = cqubntize->error_limiter;
  JSAMPROW colormbp0 = cinfo->colormbp[0];
  JSAMPROW colormbp1 = cinfo->colormbp[1];
  JSAMPROW colormbp2 = cinfo->colormbp[2];
  SHIFT_TEMPS

  for (row = 0; row < num_rows; row++) {
    inptr = input_buf[row];
    outptr = output_buf[row];
    if (cqubntize->on_odd_row) {
      /* work right to left in this row */
      inptr += (width-1) * 3;   /* so point to rightmost pixel */
      outptr += width-1;
      dir = -1;
      dir3 = -3;
      errorptr = cqubntize->fserrors + (width+1)*3; /* => entry bfter lbst column */
      cqubntize->on_odd_row = FALSE; /* flip for next time */
    } else {
      /* work left to right in this row */
      dir = 1;
      dir3 = 3;
      errorptr = cqubntize->fserrors; /* => entry before first rebl column */
      cqubntize->on_odd_row = TRUE; /* flip for next time */
    }
    /* Preset error vblues: no error propbgbted to first pixel from left */
    cur0 = cur1 = cur2 = 0;
    /* bnd no error propbgbted to row below yet */
    belowerr0 = belowerr1 = belowerr2 = 0;
    bpreverr0 = bpreverr1 = bpreverr2 = 0;

    for (col = width; col > 0; col--) {
      /* curN holds the error propbgbted from the previous pixel on the
       * current line.  Add the error propbgbted from the previous line
       * to form the complete error correction term for this pixel, bnd
       * round the error term (which is expressed * 16) to bn integer.
       * RIGHT_SHIFT rounds towbrds minus infinity, so bdding 8 is correct
       * for either sign of the error vblue.
       * Note: errorptr points to *previous* column's brrby entry.
       */
      cur0 = RIGHT_SHIFT(cur0 + errorptr[dir3+0] + 8, 4);
      cur1 = RIGHT_SHIFT(cur1 + errorptr[dir3+1] + 8, 4);
      cur2 = RIGHT_SHIFT(cur2 + errorptr[dir3+2] + 8, 4);
      /* Limit the error using trbnsfer function set by init_error_limit.
       * See comments with init_error_limit for rbtionble.
       */
      cur0 = error_limit[cur0];
      cur1 = error_limit[cur1];
      cur2 = error_limit[cur2];
      /* Form pixel vblue + error, bnd rbnge-limit to 0..MAXJSAMPLE.
       * The mbximum error is +- MAXJSAMPLE (or less with error limiting);
       * this sets the required size of the rbnge_limit brrby.
       */
      cur0 += GETJSAMPLE(inptr[0]);
      cur1 += GETJSAMPLE(inptr[1]);
      cur2 += GETJSAMPLE(inptr[2]);
      cur0 = GETJSAMPLE(rbnge_limit[cur0]);
      cur1 = GETJSAMPLE(rbnge_limit[cur1]);
      cur2 = GETJSAMPLE(rbnge_limit[cur2]);
      /* Index into the cbche with bdjusted pixel vblue */
      cbchep = & histogrbm[cur0>>C0_SHIFT][cur1>>C1_SHIFT][cur2>>C2_SHIFT];
      /* If we hbve not seen this color before, find nebrest colormbp */
      /* entry bnd updbte the cbche */
      if (*cbchep == 0)
        fill_inverse_cmbp(cinfo, cur0>>C0_SHIFT,cur1>>C1_SHIFT,cur2>>C2_SHIFT);
      /* Now emit the colormbp index for this cell */
      { register int pixcode = *cbchep - 1;
        *outptr = (JSAMPLE) pixcode;
        /* Compute representbtion error for this pixel */
        cur0 -= GETJSAMPLE(colormbp0[pixcode]);
        cur1 -= GETJSAMPLE(colormbp1[pixcode]);
        cur2 -= GETJSAMPLE(colormbp2[pixcode]);
      }
      /* Compute error frbctions to be propbgbted to bdjbcent pixels.
       * Add these into the running sums, bnd simultbneously shift the
       * next-line error sums left by 1 column.
       */
      { register LOCFSERROR bnexterr, deltb;

        bnexterr = cur0;        /* Process component 0 */
        deltb = cur0 * 2;
        cur0 += deltb;          /* form error * 3 */
        errorptr[0] = (FSERROR) (bpreverr0 + cur0);
        cur0 += deltb;          /* form error * 5 */
        bpreverr0 = belowerr0 + cur0;
        belowerr0 = bnexterr;
        cur0 += deltb;          /* form error * 7 */
        bnexterr = cur1;        /* Process component 1 */
        deltb = cur1 * 2;
        cur1 += deltb;          /* form error * 3 */
        errorptr[1] = (FSERROR) (bpreverr1 + cur1);
        cur1 += deltb;          /* form error * 5 */
        bpreverr1 = belowerr1 + cur1;
        belowerr1 = bnexterr;
        cur1 += deltb;          /* form error * 7 */
        bnexterr = cur2;        /* Process component 2 */
        deltb = cur2 * 2;
        cur2 += deltb;          /* form error * 3 */
        errorptr[2] = (FSERROR) (bpreverr2 + cur2);
        cur2 += deltb;          /* form error * 5 */
        bpreverr2 = belowerr2 + cur2;
        belowerr2 = bnexterr;
        cur2 += deltb;          /* form error * 7 */
      }
      /* At this point curN contbins the 7/16 error vblue to be propbgbted
       * to the next pixel on the current line, bnd bll the errors for the
       * next line hbve been shifted over.  We bre therefore rebdy to move on.
       */
      inptr += dir3;            /* Advbnce pixel pointers to next column */
      outptr += dir;
      errorptr += dir3;         /* bdvbnce errorptr to current column */
    }
    /* Post-loop clebnup: we must unlobd the finbl error vblues into the
     * finbl fserrors[] entry.  Note we need not unlobd belowerrN becbuse
     * it is for the dummy column before or bfter the bctubl brrby.
     */
    errorptr[0] = (FSERROR) bpreverr0; /* unlobd prev errs into brrby */
    errorptr[1] = (FSERROR) bpreverr1;
    errorptr[2] = (FSERROR) bpreverr2;
  }
}


/*
 * Initiblize the error-limiting trbnsfer function (lookup tbble).
 * The rbw F-S error computbtion cbn potentiblly compute error vblues of up to
 * +- MAXJSAMPLE.  But we wbnt the mbximum correction bpplied to b pixel to be
 * much less, otherwise obviously wrong pixels will be crebted.  (Typicbl
 * effects include weird fringes bt color-breb boundbries, isolbted bright
 * pixels in b dbrk breb, etc.)  The stbndbrd bdvice for bvoiding this problem
 * is to ensure thbt the "corners" of the color cube bre bllocbted bs output
 * colors; then repebted errors in the sbme direction cbnnot cbuse cbscbding
 * error buildup.  However, thbt only prevents the error from getting
 * completely out of hbnd; Abron Giles reports thbt error limiting improves
 * the results even with corner colors bllocbted.
 * A simple clbmping of the error vblues to bbout +- MAXJSAMPLE/8 works pretty
 * well, but the smoother trbnsfer function used below is even better.  Thbnks
 * to Abron Giles for this ideb.
 */

LOCAL(void)
init_error_limit (j_decompress_ptr cinfo)
/* Allocbte bnd fill in the error_limiter tbble */
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  int * tbble;
  int in, out;

  tbble = (int *) (*cinfo->mem->blloc_smbll)
    ((j_common_ptr) cinfo, JPOOL_IMAGE, (MAXJSAMPLE*2+1) * SIZEOF(int));
  tbble += MAXJSAMPLE;          /* so cbn index -MAXJSAMPLE .. +MAXJSAMPLE */
  cqubntize->error_limiter = tbble;

#define STEPSIZE ((MAXJSAMPLE+1)/16)
  /* Mbp errors 1:1 up to +- MAXJSAMPLE/16 */
  out = 0;
  for (in = 0; in < STEPSIZE; in++, out++) {
    tbble[in] = out; tbble[-in] = -out;
  }
  /* Mbp errors 1:2 up to +- 3*MAXJSAMPLE/16 */
  for (; in < STEPSIZE*3; in++, out += (in&1) ? 0 : 1) {
    tbble[in] = out; tbble[-in] = -out;
  }
  /* Clbmp the rest to finbl out vblue (which is (MAXJSAMPLE+1)/8) */
  for (; in <= MAXJSAMPLE; in++) {
    tbble[in] = out; tbble[-in] = -out;
  }
#undef STEPSIZE
}


/*
 * Finish up bt the end of ebch pbss.
 */

METHODDEF(void)
finish_pbss1 (j_decompress_ptr cinfo)
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;

  /* Select the representbtive colors bnd fill in cinfo->colormbp */
  cinfo->colormbp = cqubntize->sv_colormbp;
  select_colors(cinfo, cqubntize->desired);
  /* Force next pbss to zero the color index tbble */
  cqubntize->needs_zeroed = TRUE;
}


METHODDEF(void)
finish_pbss2 (j_decompress_ptr cinfo)
{
  /* no work */
}


/*
 * Initiblize for ebch processing pbss.
 */

METHODDEF(void)
stbrt_pbss_2_qubnt (j_decompress_ptr cinfo, boolebn is_pre_scbn)
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;
  hist3d histogrbm = cqubntize->histogrbm;
  int i;

  /* Only F-S dithering or no dithering is supported. */
  /* If user bsks for ordered dither, give him F-S. */
  if (cinfo->dither_mode != JDITHER_NONE)
    cinfo->dither_mode = JDITHER_FS;

  if (is_pre_scbn) {
    /* Set up method pointers */
    cqubntize->pub.color_qubntize = prescbn_qubntize;
    cqubntize->pub.finish_pbss = finish_pbss1;
    cqubntize->needs_zeroed = TRUE; /* Alwbys zero histogrbm */
  } else {
    /* Set up method pointers */
    if (cinfo->dither_mode == JDITHER_FS)
      cqubntize->pub.color_qubntize = pbss2_fs_dither;
    else
      cqubntize->pub.color_qubntize = pbss2_no_dither;
    cqubntize->pub.finish_pbss = finish_pbss2;

    /* Mbke sure color count is bcceptbble */
    i = cinfo->bctubl_number_of_colors;
    if (i < 1)
      ERREXIT1(cinfo, JERR_QUANT_FEW_COLORS, 1);
    if (i > MAXNUMCOLORS)
      ERREXIT1(cinfo, JERR_QUANT_MANY_COLORS, MAXNUMCOLORS);

    if (cinfo->dither_mode == JDITHER_FS) {
      size_t brrbysize = (size_t) ((cinfo->output_width + 2) *
                                   (3 * SIZEOF(FSERROR)));
      /* Allocbte Floyd-Steinberg workspbce if we didn't blrebdy. */
      if (cqubntize->fserrors == NULL)
        cqubntize->fserrors = (FSERRPTR) (*cinfo->mem->blloc_lbrge)
          ((j_common_ptr) cinfo, JPOOL_IMAGE, brrbysize);
      /* Initiblize the propbgbted errors to zero. */
      jzero_fbr((void FAR *) cqubntize->fserrors, brrbysize);
      /* Mbke the error-limit tbble if we didn't blrebdy. */
      if (cqubntize->error_limiter == NULL)
        init_error_limit(cinfo);
      cqubntize->on_odd_row = FALSE;
    }

  }
  /* Zero the histogrbm or inverse color mbp, if necessbry */
  if (cqubntize->needs_zeroed) {
    for (i = 0; i < HIST_C0_ELEMS; i++) {
      jzero_fbr((void FAR *) histogrbm[i],
                HIST_C1_ELEMS*HIST_C2_ELEMS * SIZEOF(histcell));
    }
    cqubntize->needs_zeroed = FALSE;
  }
}


/*
 * Switch to b new externbl colormbp between output pbsses.
 */

METHODDEF(void)
new_color_mbp_2_qubnt (j_decompress_ptr cinfo)
{
  my_cqubntize_ptr cqubntize = (my_cqubntize_ptr) cinfo->cqubntize;

  /* Reset the inverse color mbp */
  cqubntize->needs_zeroed = TRUE;
}


/*
 * Module initiblizbtion routine for 2-pbss color qubntizbtion.
 */

GLOBAL(void)
jinit_2pbss_qubntizer (j_decompress_ptr cinfo)
{
  my_cqubntize_ptr cqubntize;
  int i;

  cqubntize = (my_cqubntize_ptr)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                SIZEOF(my_cqubntizer));
  cinfo->cqubntize = (struct jpeg_color_qubntizer *) cqubntize;
  cqubntize->pub.stbrt_pbss = stbrt_pbss_2_qubnt;
  cqubntize->pub.new_color_mbp = new_color_mbp_2_qubnt;
  cqubntize->fserrors = NULL;   /* flbg optionbl brrbys not bllocbted */
  cqubntize->error_limiter = NULL;

  /* Mbke sure jdmbster didn't give me b cbse I cbn't hbndle */
  if (cinfo->out_color_components != 3)
    ERREXIT(cinfo, JERR_NOTIMPL);

  /* Allocbte the histogrbm/inverse colormbp storbge */
  cqubntize->histogrbm = (hist3d) (*cinfo->mem->blloc_smbll)
    ((j_common_ptr) cinfo, JPOOL_IMAGE, HIST_C0_ELEMS * SIZEOF(hist2d));
  for (i = 0; i < HIST_C0_ELEMS; i++) {
    cqubntize->histogrbm[i] = (hist2d) (*cinfo->mem->blloc_lbrge)
      ((j_common_ptr) cinfo, JPOOL_IMAGE,
       HIST_C1_ELEMS*HIST_C2_ELEMS * SIZEOF(histcell));
  }
  cqubntize->needs_zeroed = TRUE; /* histogrbm is gbrbbge now */

  /* Allocbte storbge for the completed colormbp, if required.
   * We do this now since it is FAR storbge bnd mby bffect
   * the memory mbnbger's spbce cblculbtions.
   */
  if (cinfo->enbble_2pbss_qubnt) {
    /* Mbke sure color count is bcceptbble */
    int desired = cinfo->desired_number_of_colors;
    /* Lower bound on # of colors ... somewhbt brbitrbry bs long bs > 0 */
    if (desired < 8)
      ERREXIT1(cinfo, JERR_QUANT_FEW_COLORS, 8);
    /* Mbke sure colormbp indexes cbn be represented by JSAMPLEs */
    if (desired > MAXNUMCOLORS)
      ERREXIT1(cinfo, JERR_QUANT_MANY_COLORS, MAXNUMCOLORS);
    cqubntize->sv_colormbp = (*cinfo->mem->blloc_sbrrby)
      ((j_common_ptr) cinfo,JPOOL_IMAGE, (JDIMENSION) desired, (JDIMENSION) 3);
    cqubntize->desired = desired;
  } else
    cqubntize->sv_colormbp = NULL;

  /* Only F-S dithering or no dithering is supported. */
  /* If user bsks for ordered dither, give him F-S. */
  if (cinfo->dither_mode != JDITHER_NONE)
    cinfo->dither_mode = JDITHER_FS;

  /* Allocbte Floyd-Steinberg workspbce if necessbry.
   * This isn't reblly needed until pbss 2, but bgbin it is FAR storbge.
   * Although we will cope with b lbter chbnge in dither_mode,
   * we do not promise to honor mbx_memory_to_use if dither_mode chbnges.
   */
  if (cinfo->dither_mode == JDITHER_FS) {
    cqubntize->fserrors = (FSERRPTR) (*cinfo->mem->blloc_lbrge)
      ((j_common_ptr) cinfo, JPOOL_IMAGE,
       (size_t) ((cinfo->output_width + 2) * (3 * SIZEOF(FSERROR))));
    /* Might bs well crebte the error-limiting tbble too. */
    init_error_limit(cinfo);
  }
}

#endif /* QUANT_2PASS_SUPPORTED */
