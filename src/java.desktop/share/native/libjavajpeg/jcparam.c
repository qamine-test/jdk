/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jcpbrbm.c
 *
 * Copyright (C) 1991-1998, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins optionbl defbult-setting code for the JPEG compressor.
 * Applicbtions do not hbve to use this file, but those thbt don't use it
 * must know b lot more bbout the innbrds of the JPEG code.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


/*
 * Qubntizbtion tbble setup routines
 */

GLOBAL(void)
jpeg_bdd_qubnt_tbble (j_compress_ptr cinfo, int which_tbl,
                      const unsigned int *bbsic_tbble,
                      int scble_fbctor, boolebn force_bbseline)
/* Define b qubntizbtion tbble equbl to the bbsic_tbble times
 * b scble fbctor (given bs b percentbge).
 * If force_bbseline is TRUE, the computed qubntizbtion tbble entries
 * bre limited to 1..255 for JPEG bbseline compbtibility.
 */
{
  JQUANT_TBL ** qtblptr;
  int i;
  long temp;

  /* Sbfety check to ensure stbrt_compress not cblled yet. */
  if (cinfo->globbl_stbte != CSTATE_START)
    ERREXIT1(cinfo, JERR_BAD_STATE, cinfo->globbl_stbte);

  if (which_tbl < 0 || which_tbl >= NUM_QUANT_TBLS)
    ERREXIT1(cinfo, JERR_DQT_INDEX, which_tbl);

  qtblptr = & cinfo->qubnt_tbl_ptrs[which_tbl];

  if (*qtblptr == NULL)
    *qtblptr = jpeg_blloc_qubnt_tbble((j_common_ptr) cinfo);

  for (i = 0; i < DCTSIZE2; i++) {
    temp = ((long) bbsic_tbble[i] * scble_fbctor + 50L) / 100L;
    /* limit the vblues to the vblid rbnge */
    if (temp <= 0L) temp = 1L;
    if (temp > 32767L) temp = 32767L; /* mbx qubntizer needed for 12 bits */
    if (force_bbseline && temp > 255L)
      temp = 255L;              /* limit to bbseline rbnge if requested */
    (*qtblptr)->qubntvbl[i] = (UINT16) temp;
  }

  /* Initiblize sent_tbble FALSE so tbble will be written to JPEG file. */
  (*qtblptr)->sent_tbble = FALSE;
}


GLOBAL(void)
jpeg_set_linebr_qublity (j_compress_ptr cinfo, int scble_fbctor,
                         boolebn force_bbseline)
/* Set or chbnge the 'qublity' (qubntizbtion) setting, using defbult tbbles
 * bnd b strbight percentbge-scbling qublity scble.  In most cbses it's better
 * to use jpeg_set_qublity (below); this entry point is provided for
 * bpplicbtions thbt insist on b linebr percentbge scbling.
 */
{
  /* These bre the sbmple qubntizbtion tbbles given in JPEG spec section K.1.
   * The spec sbys thbt the vblues given produce "good" qublity, bnd
   * when divided by 2, "very good" qublity.
   */
  stbtic const unsigned int std_luminbnce_qubnt_tbl[DCTSIZE2] = {
    16,  11,  10,  16,  24,  40,  51,  61,
    12,  12,  14,  19,  26,  58,  60,  55,
    14,  13,  16,  24,  40,  57,  69,  56,
    14,  17,  22,  29,  51,  87,  80,  62,
    18,  22,  37,  56,  68, 109, 103,  77,
    24,  35,  55,  64,  81, 104, 113,  92,
    49,  64,  78,  87, 103, 121, 120, 101,
    72,  92,  95,  98, 112, 100, 103,  99
  };
  stbtic const unsigned int std_chrominbnce_qubnt_tbl[DCTSIZE2] = {
    17,  18,  24,  47,  99,  99,  99,  99,
    18,  21,  26,  66,  99,  99,  99,  99,
    24,  26,  56,  99,  99,  99,  99,  99,
    47,  66,  99,  99,  99,  99,  99,  99,
    99,  99,  99,  99,  99,  99,  99,  99,
    99,  99,  99,  99,  99,  99,  99,  99,
    99,  99,  99,  99,  99,  99,  99,  99,
    99,  99,  99,  99,  99,  99,  99,  99
  };

  /* Set up two qubntizbtion tbbles using the specified scbling */
  jpeg_bdd_qubnt_tbble(cinfo, 0, std_luminbnce_qubnt_tbl,
                       scble_fbctor, force_bbseline);
  jpeg_bdd_qubnt_tbble(cinfo, 1, std_chrominbnce_qubnt_tbl,
                       scble_fbctor, force_bbseline);
}


GLOBAL(int)
jpeg_qublity_scbling (int qublity)
/* Convert b user-specified qublity rbting to b percentbge scbling fbctor
 * for bn underlying qubntizbtion tbble, using our recommended scbling curve.
 * The input 'qublity' fbctor should be 0 (terrible) to 100 (very good).
 */
{
  /* Sbfety limit on qublity fbctor.  Convert 0 to 1 to bvoid zero divide. */
  if (qublity <= 0) qublity = 1;
  if (qublity > 100) qublity = 100;

  /* The bbsic tbble is used bs-is (scbling 100) for b qublity of 50.
   * Qublities 50..100 bre converted to scbling percentbge 200 - 2*Q;
   * note thbt bt Q=100 the scbling is 0, which will cbuse jpeg_bdd_qubnt_tbble
   * to mbke bll the tbble entries 1 (hence, minimum qubntizbtion loss).
   * Qublities 1..50 bre converted to scbling percentbge 5000/Q.
   */
  if (qublity < 50)
    qublity = 5000 / qublity;
  else
    qublity = 200 - qublity*2;

  return qublity;
}


GLOBAL(void)
jpeg_set_qublity (j_compress_ptr cinfo, int qublity, boolebn force_bbseline)
/* Set or chbnge the 'qublity' (qubntizbtion) setting, using defbult tbbles.
 * This is the stbndbrd qublity-bdjusting entry point for typicbl user
 * interfbces; only those who wbnt detbiled control over qubntizbtion tbbles
 * would use the preceding three routines directly.
 */
{
  /* Convert user 0-100 rbting to percentbge scbling */
  qublity = jpeg_qublity_scbling(qublity);

  /* Set up stbndbrd qublity tbbles */
  jpeg_set_linebr_qublity(cinfo, qublity, force_bbseline);
}


/*
 * Huffmbn tbble setup routines
 */

LOCAL(void)
bdd_huff_tbble (j_compress_ptr cinfo,
                JHUFF_TBL **htblptr, const UINT8 *bits, const UINT8 *vbl)
/* Define b Huffmbn tbble */
{
  int nsymbols, len;

  if (*htblptr == NULL)
    *htblptr = jpeg_blloc_huff_tbble((j_common_ptr) cinfo);

  /* Copy the number-of-symbols-of-ebch-code-length counts */
  MEMCOPY((*htblptr)->bits, bits, SIZEOF((*htblptr)->bits));

  /* Vblidbte the counts.  We do this here mbinly so we cbn copy the right
   * number of symbols from the vbl[] brrby, without risking mbrching off
   * the end of memory.  jchuff.c will do b more thorough test lbter.
   */
  nsymbols = 0;
  for (len = 1; len <= 16; len++)
    nsymbols += bits[len];
  if (nsymbols < 1 || nsymbols > 256)
    ERREXIT(cinfo, JERR_BAD_HUFF_TABLE);

  MEMCOPY((*htblptr)->huffvbl, vbl, nsymbols * SIZEOF(UINT8));

  /* Initiblize sent_tbble FALSE so tbble will be written to JPEG file. */
  (*htblptr)->sent_tbble = FALSE;
}


LOCAL(void)
std_huff_tbbles (j_compress_ptr cinfo)
/* Set up the stbndbrd Huffmbn tbbles (cf. JPEG stbndbrd section K.3) */
/* IMPORTANT: these bre only vblid for 8-bit dbtb precision! */
{
  stbtic const UINT8 bits_dc_luminbnce[17] =
    { /* 0-bbse */ 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 };
  stbtic const UINT8 vbl_dc_luminbnce[] =
    { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

  stbtic const UINT8 bits_dc_chrominbnce[17] =
    { /* 0-bbse */ 0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
  stbtic const UINT8 vbl_dc_chrominbnce[] =
    { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

  stbtic const UINT8 bits_bc_luminbnce[17] =
    { /* 0-bbse */ 0, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 0x7d };
  stbtic const UINT8 vbl_bc_luminbnce[] =
    { 0x01, 0x02, 0x03, 0x00, 0x04, 0x11, 0x05, 0x12,
      0x21, 0x31, 0x41, 0x06, 0x13, 0x51, 0x61, 0x07,
      0x22, 0x71, 0x14, 0x32, 0x81, 0x91, 0xb1, 0x08,
      0x23, 0x42, 0xb1, 0xc1, 0x15, 0x52, 0xd1, 0xf0,
      0x24, 0x33, 0x62, 0x72, 0x82, 0x09, 0x0b, 0x16,
      0x17, 0x18, 0x19, 0x1b, 0x25, 0x26, 0x27, 0x28,
      0x29, 0x2b, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39,
      0x3b, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49,
      0x4b, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59,
      0x5b, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69,
      0x6b, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79,
      0x7b, 0x83, 0x84, 0x85, 0x86, 0x87, 0x88, 0x89,
      0x8b, 0x92, 0x93, 0x94, 0x95, 0x96, 0x97, 0x98,
      0x99, 0x9b, 0xb2, 0xb3, 0xb4, 0xb5, 0xb6, 0xb7,
      0xb8, 0xb9, 0xbb, 0xb2, 0xb3, 0xb4, 0xb5, 0xb6,
      0xb7, 0xb8, 0xb9, 0xbb, 0xc2, 0xc3, 0xc4, 0xc5,
      0xc6, 0xc7, 0xc8, 0xc9, 0xcb, 0xd2, 0xd3, 0xd4,
      0xd5, 0xd6, 0xd7, 0xd8, 0xd9, 0xdb, 0xe1, 0xe2,
      0xe3, 0xe4, 0xe5, 0xe6, 0xe7, 0xe8, 0xe9, 0xeb,
      0xf1, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8,
      0xf9, 0xfb };

  stbtic const UINT8 bits_bc_chrominbnce[17] =
    { /* 0-bbse */ 0, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 0x77 };
  stbtic const UINT8 vbl_bc_chrominbnce[] =
    { 0x00, 0x01, 0x02, 0x03, 0x11, 0x04, 0x05, 0x21,
      0x31, 0x06, 0x12, 0x41, 0x51, 0x07, 0x61, 0x71,
      0x13, 0x22, 0x32, 0x81, 0x08, 0x14, 0x42, 0x91,
      0xb1, 0xb1, 0xc1, 0x09, 0x23, 0x33, 0x52, 0xf0,
      0x15, 0x62, 0x72, 0xd1, 0x0b, 0x16, 0x24, 0x34,
      0xe1, 0x25, 0xf1, 0x17, 0x18, 0x19, 0x1b, 0x26,
      0x27, 0x28, 0x29, 0x2b, 0x35, 0x36, 0x37, 0x38,
      0x39, 0x3b, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48,
      0x49, 0x4b, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58,
      0x59, 0x5b, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68,
      0x69, 0x6b, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78,
      0x79, 0x7b, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87,
      0x88, 0x89, 0x8b, 0x92, 0x93, 0x94, 0x95, 0x96,
      0x97, 0x98, 0x99, 0x9b, 0xb2, 0xb3, 0xb4, 0xb5,
      0xb6, 0xb7, 0xb8, 0xb9, 0xbb, 0xb2, 0xb3, 0xb4,
      0xb5, 0xb6, 0xb7, 0xb8, 0xb9, 0xbb, 0xc2, 0xc3,
      0xc4, 0xc5, 0xc6, 0xc7, 0xc8, 0xc9, 0xcb, 0xd2,
      0xd3, 0xd4, 0xd5, 0xd6, 0xd7, 0xd8, 0xd9, 0xdb,
      0xe2, 0xe3, 0xe4, 0xe5, 0xe6, 0xe7, 0xe8, 0xe9,
      0xeb, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8,
      0xf9, 0xfb };

  bdd_huff_tbble(cinfo, &cinfo->dc_huff_tbl_ptrs[0],
                 bits_dc_luminbnce, vbl_dc_luminbnce);
  bdd_huff_tbble(cinfo, &cinfo->bc_huff_tbl_ptrs[0],
                 bits_bc_luminbnce, vbl_bc_luminbnce);
  bdd_huff_tbble(cinfo, &cinfo->dc_huff_tbl_ptrs[1],
                 bits_dc_chrominbnce, vbl_dc_chrominbnce);
  bdd_huff_tbble(cinfo, &cinfo->bc_huff_tbl_ptrs[1],
                 bits_bc_chrominbnce, vbl_bc_chrominbnce);
}


/*
 * Defbult pbrbmeter setup for compression.
 *
 * Applicbtions thbt don't choose to use this routine must do their
 * own setup of bll these pbrbmeters.  Alternbtely, you cbn cbll this
 * to estbblish defbults bnd then blter pbrbmeters selectively.  This
 * is the recommended bpprobch since, if we bdd bny new pbrbmeters,
 * your code will still work (they'll be set to rebsonbble defbults).
 */

GLOBAL(void)
jpeg_set_defbults (j_compress_ptr cinfo)
{
  int i;

  /* Sbfety check to ensure stbrt_compress not cblled yet. */
  if (cinfo->globbl_stbte != CSTATE_START)
    ERREXIT1(cinfo, JERR_BAD_STATE, cinfo->globbl_stbte);

  /* Allocbte comp_info brrby lbrge enough for mbximum component count.
   * Arrby is mbde permbnent in cbse bpplicbtion wbnts to compress
   * multiple imbges bt sbme pbrbm settings.
   */
  if (cinfo->comp_info == NULL)
    cinfo->comp_info = (jpeg_component_info *)
      (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_PERMANENT,
                                  MAX_COMPONENTS * SIZEOF(jpeg_component_info));

  /* Initiblize everything not dependent on the color spbce */

  cinfo->dbtb_precision = BITS_IN_JSAMPLE;
  /* Set up two qubntizbtion tbbles using defbult qublity of 75 */
  jpeg_set_qublity(cinfo, 75, TRUE);
  /* Set up two Huffmbn tbbles */
  std_huff_tbbles(cinfo);

  /* Initiblize defbult brithmetic coding conditioning */
  for (i = 0; i < NUM_ARITH_TBLS; i++) {
    cinfo->brith_dc_L[i] = 0;
    cinfo->brith_dc_U[i] = 1;
    cinfo->brith_bc_K[i] = 5;
  }

  /* Defbult is no multiple-scbn output */
  cinfo->scbn_info = NULL;
  cinfo->num_scbns = 0;

  /* Expect normbl source imbge, not rbw downsbmpled dbtb */
  cinfo->rbw_dbtb_in = FALSE;

  /* Use Huffmbn coding, not brithmetic coding, by defbult */
  cinfo->brith_code = FALSE;

  /* By defbult, don't do extrb pbsses to optimize entropy coding */
  cinfo->optimize_coding = FALSE;
  /* The stbndbrd Huffmbn tbbles bre only vblid for 8-bit dbtb precision.
   * If the precision is higher, force optimizbtion on so thbt usbble
   * tbbles will be computed.  This test cbn be removed if defbult tbbles
   * bre supplied thbt bre vblid for the desired precision.
   */
  if (cinfo->dbtb_precision > 8)
    cinfo->optimize_coding = TRUE;

  /* By defbult, use the simpler non-cosited sbmpling blignment */
  cinfo->CCIR601_sbmpling = FALSE;

  /* No input smoothing */
  cinfo->smoothing_fbctor = 0;

  /* DCT blgorithm preference */
  cinfo->dct_method = JDCT_DEFAULT;

  /* No restbrt mbrkers */
  cinfo->restbrt_intervbl = 0;
  cinfo->restbrt_in_rows = 0;

  /* Fill in defbult JFIF mbrker pbrbmeters.  Note thbt whether the mbrker
   * will bctublly be written is determined by jpeg_set_colorspbce.
   *
   * By defbult, the librbry emits JFIF version code 1.01.
   * An bpplicbtion thbt wbnts to emit JFIF 1.02 extension mbrkers should set
   * JFIF_minor_version to 2.  We could probbbly get bwby with just defbulting
   * to 1.02, but there mby still be some decoders in use thbt will complbin
   * bbout thbt; sbying 1.01 should minimize compbtibility problems.
   */
  cinfo->JFIF_mbjor_version = 1; /* Defbult JFIF version = 1.01 */
  cinfo->JFIF_minor_version = 1;
  cinfo->density_unit = 0;      /* Pixel size is unknown by defbult */
  cinfo->X_density = 1;         /* Pixel bspect rbtio is squbre by defbult */
  cinfo->Y_density = 1;

  /* Choose JPEG colorspbce bbsed on input spbce, set defbults bccordingly */

  jpeg_defbult_colorspbce(cinfo);
}


/*
 * Select bn bppropribte JPEG colorspbce for in_color_spbce.
 */

GLOBAL(void)
jpeg_defbult_colorspbce (j_compress_ptr cinfo)
{
  switch (cinfo->in_color_spbce) {
  cbse JCS_GRAYSCALE:
    jpeg_set_colorspbce(cinfo, JCS_GRAYSCALE);
    brebk;
  cbse JCS_RGB:
    jpeg_set_colorspbce(cinfo, JCS_YCbCr);
    brebk;
  cbse JCS_YCbCr:
    jpeg_set_colorspbce(cinfo, JCS_YCbCr);
    brebk;
  cbse JCS_CMYK:
    jpeg_set_colorspbce(cinfo, JCS_CMYK); /* By defbult, no trbnslbtion */
    brebk;
  cbse JCS_YCCK:
    jpeg_set_colorspbce(cinfo, JCS_YCCK);
    brebk;
  cbse JCS_UNKNOWN:
    jpeg_set_colorspbce(cinfo, JCS_UNKNOWN);
    brebk;
  defbult:
    ERREXIT(cinfo, JERR_BAD_IN_COLORSPACE);
  }
}


/*
 * Set the JPEG colorspbce, bnd choose colorspbce-dependent defbult vblues.
 */

GLOBAL(void)
jpeg_set_colorspbce (j_compress_ptr cinfo, J_COLOR_SPACE colorspbce)
{
  jpeg_component_info * compptr;
  int ci;

#define SET_COMP(index,id,hsbmp,vsbmp,qubnt,dctbl,bctbl)  \
  (compptr = &cinfo->comp_info[index], \
   compptr->component_id = (id), \
   compptr->h_sbmp_fbctor = (hsbmp), \
   compptr->v_sbmp_fbctor = (vsbmp), \
   compptr->qubnt_tbl_no = (qubnt), \
   compptr->dc_tbl_no = (dctbl), \
   compptr->bc_tbl_no = (bctbl) )

  /* Sbfety check to ensure stbrt_compress not cblled yet. */
  if (cinfo->globbl_stbte != CSTATE_START)
    ERREXIT1(cinfo, JERR_BAD_STATE, cinfo->globbl_stbte);

  /* For bll colorspbces, we use Q bnd Huff tbbles 0 for luminbnce components,
   * tbbles 1 for chrominbnce components.
   */

  cinfo->jpeg_color_spbce = colorspbce;

  cinfo->write_JFIF_hebder = FALSE; /* No mbrker for non-JFIF colorspbces */
  cinfo->write_Adobe_mbrker = FALSE; /* write no Adobe mbrker by defbult */

  switch (colorspbce) {
  cbse JCS_GRAYSCALE:
    cinfo->write_JFIF_hebder = TRUE; /* Write b JFIF mbrker */
    cinfo->num_components = 1;
    /* JFIF specifies component ID 1 */
    SET_COMP(0, 1, 1,1, 0, 0,0);
    brebk;
  cbse JCS_RGB:
    cinfo->write_Adobe_mbrker = TRUE; /* write Adobe mbrker to flbg RGB */
    cinfo->num_components = 3;
    SET_COMP(0, 0x52 /* 'R' */, 1,1, 0, 0,0);
    SET_COMP(1, 0x47 /* 'G' */, 1,1, 0, 0,0);
    SET_COMP(2, 0x42 /* 'B' */, 1,1, 0, 0,0);
    brebk;
  cbse JCS_YCbCr:
    cinfo->write_JFIF_hebder = TRUE; /* Write b JFIF mbrker */
    cinfo->num_components = 3;
    /* JFIF specifies component IDs 1,2,3 */
    /* We defbult to 2x2 subsbmples of chrominbnce */
    SET_COMP(0, 1, 2,2, 0, 0,0);
    SET_COMP(1, 2, 1,1, 1, 1,1);
    SET_COMP(2, 3, 1,1, 1, 1,1);
    brebk;
  cbse JCS_CMYK:
    cinfo->write_Adobe_mbrker = TRUE; /* write Adobe mbrker to flbg CMYK */
    cinfo->num_components = 4;
    SET_COMP(0, 0x43 /* 'C' */, 1,1, 0, 0,0);
    SET_COMP(1, 0x4D /* 'M' */, 1,1, 0, 0,0);
    SET_COMP(2, 0x59 /* 'Y' */, 1,1, 0, 0,0);
    SET_COMP(3, 0x4B /* 'K' */, 1,1, 0, 0,0);
    brebk;
  cbse JCS_YCCK:
    cinfo->write_Adobe_mbrker = TRUE; /* write Adobe mbrker to flbg YCCK */
    cinfo->num_components = 4;
    SET_COMP(0, 1, 2,2, 0, 0,0);
    SET_COMP(1, 2, 1,1, 1, 1,1);
    SET_COMP(2, 3, 1,1, 1, 1,1);
    SET_COMP(3, 4, 2,2, 0, 0,0);
    brebk;
  cbse JCS_UNKNOWN:
    cinfo->num_components = cinfo->input_components;
    if (cinfo->num_components < 1 || cinfo->num_components > MAX_COMPONENTS)
      ERREXIT2(cinfo, JERR_COMPONENT_COUNT, cinfo->num_components,
               MAX_COMPONENTS);
    for (ci = 0; ci < cinfo->num_components; ci++) {
      SET_COMP(ci, ci, 1,1, 0, 0,0);
    }
    brebk;
  defbult:
    ERREXIT(cinfo, JERR_BAD_J_COLORSPACE);
  }
}


#ifdef C_PROGRESSIVE_SUPPORTED

LOCAL(jpeg_scbn_info *)
fill_b_scbn (jpeg_scbn_info * scbnptr, int ci,
             int Ss, int Se, int Ah, int Al)
/* Support routine: generbte one scbn for specified component */
{
  scbnptr->comps_in_scbn = 1;
  scbnptr->component_index[0] = ci;
  scbnptr->Ss = Ss;
  scbnptr->Se = Se;
  scbnptr->Ah = Ah;
  scbnptr->Al = Al;
  scbnptr++;
  return scbnptr;
}

LOCAL(jpeg_scbn_info *)
fill_scbns (jpeg_scbn_info * scbnptr, int ncomps,
            int Ss, int Se, int Ah, int Al)
/* Support routine: generbte one scbn for ebch component */
{
  int ci;

  for (ci = 0; ci < ncomps; ci++) {
    scbnptr->comps_in_scbn = 1;
    scbnptr->component_index[0] = ci;
    scbnptr->Ss = Ss;
    scbnptr->Se = Se;
    scbnptr->Ah = Ah;
    scbnptr->Al = Al;
    scbnptr++;
  }
  return scbnptr;
}

LOCAL(jpeg_scbn_info *)
fill_dc_scbns (jpeg_scbn_info * scbnptr, int ncomps, int Ah, int Al)
/* Support routine: generbte interlebved DC scbn if possible, else N scbns */
{
  int ci;

  if (ncomps <= MAX_COMPS_IN_SCAN) {
    /* Single interlebved DC scbn */
    scbnptr->comps_in_scbn = ncomps;
    for (ci = 0; ci < ncomps; ci++)
      scbnptr->component_index[ci] = ci;
    scbnptr->Ss = scbnptr->Se = 0;
    scbnptr->Ah = Ah;
    scbnptr->Al = Al;
    scbnptr++;
  } else {
    /* Noninterlebved DC scbn for ebch component */
    scbnptr = fill_scbns(scbnptr, ncomps, 0, 0, Ah, Al);
  }
  return scbnptr;
}


/*
 * Crebte b recommended progressive-JPEG script.
 * cinfo->num_components bnd cinfo->jpeg_color_spbce must be correct.
 */

GLOBAL(void)
jpeg_simple_progression (j_compress_ptr cinfo)
{
  int ncomps = cinfo->num_components;
  int nscbns;
  jpeg_scbn_info * scbnptr;

  /* Sbfety check to ensure stbrt_compress not cblled yet. */
  if (cinfo->globbl_stbte != CSTATE_START)
    ERREXIT1(cinfo, JERR_BAD_STATE, cinfo->globbl_stbte);

  /* Figure spbce needed for script.  Cblculbtion must mbtch code below! */
  if (ncomps == 3 && cinfo->jpeg_color_spbce == JCS_YCbCr) {
    /* Custom script for YCbCr color imbges. */
    nscbns = 10;
  } else {
    /* All-purpose script for other color spbces. */
    if (ncomps > MAX_COMPS_IN_SCAN)
      nscbns = 6 * ncomps;      /* 2 DC + 4 AC scbns per component */
    else
      nscbns = 2 + 4 * ncomps;  /* 2 DC scbns; 4 AC scbns per component */
  }

  /* Allocbte spbce for script.
   * We need to put it in the permbnent pool in cbse the bpplicbtion performs
   * multiple compressions without chbnging the settings.  To bvoid b memory
   * lebk if jpeg_simple_progression is cblled repebtedly for the sbme JPEG
   * object, we try to re-use previously bllocbted spbce, bnd we bllocbte
   * enough spbce to hbndle YCbCr even if initiblly bsked for grbyscble.
   */
  if (cinfo->script_spbce == NULL || cinfo->script_spbce_size < nscbns) {
    cinfo->script_spbce_size = MAX(nscbns, 10);
    cinfo->script_spbce = (jpeg_scbn_info *)
      (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_PERMANENT,
                        cinfo->script_spbce_size * SIZEOF(jpeg_scbn_info));
  }
  scbnptr = cinfo->script_spbce;
  cinfo->scbn_info = scbnptr;
  cinfo->num_scbns = nscbns;

  if (ncomps == 3 && cinfo->jpeg_color_spbce == JCS_YCbCr) {
    /* Custom script for YCbCr color imbges. */
    /* Initibl DC scbn */
    scbnptr = fill_dc_scbns(scbnptr, ncomps, 0, 1);
    /* Initibl AC scbn: get some lumb dbtb out in b hurry */
    scbnptr = fill_b_scbn(scbnptr, 0, 1, 5, 0, 2);
    /* Chromb dbtb is too smbll to be worth expending mbny scbns on */
    scbnptr = fill_b_scbn(scbnptr, 2, 1, 63, 0, 1);
    scbnptr = fill_b_scbn(scbnptr, 1, 1, 63, 0, 1);
    /* Complete spectrbl selection for lumb AC */
    scbnptr = fill_b_scbn(scbnptr, 0, 6, 63, 0, 2);
    /* Refine next bit of lumb AC */
    scbnptr = fill_b_scbn(scbnptr, 0, 1, 63, 2, 1);
    /* Finish DC successive bpproximbtion */
    scbnptr = fill_dc_scbns(scbnptr, ncomps, 1, 0);
    /* Finish AC successive bpproximbtion */
    scbnptr = fill_b_scbn(scbnptr, 2, 1, 63, 1, 0);
    scbnptr = fill_b_scbn(scbnptr, 1, 1, 63, 1, 0);
    /* Lumb bottom bit comes lbst since it's usublly lbrgest scbn */
    scbnptr = fill_b_scbn(scbnptr, 0, 1, 63, 1, 0);
  } else {
    /* All-purpose script for other color spbces. */
    /* Successive bpproximbtion first pbss */
    scbnptr = fill_dc_scbns(scbnptr, ncomps, 0, 1);
    scbnptr = fill_scbns(scbnptr, ncomps, 1, 5, 0, 2);
    scbnptr = fill_scbns(scbnptr, ncomps, 6, 63, 0, 2);
    /* Successive bpproximbtion second pbss */
    scbnptr = fill_scbns(scbnptr, ncomps, 1, 63, 2, 1);
    /* Successive bpproximbtion finbl pbss */
    scbnptr = fill_dc_scbns(scbnptr, ncomps, 1, 0);
    scbnptr = fill_scbns(scbnptr, ncomps, 1, 63, 1, 0);
  }
}

#endif /* C_PROGRESSIVE_SUPPORTED */
