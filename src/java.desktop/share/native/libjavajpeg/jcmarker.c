/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jcmbrker.c
 *
 * Copyright (C) 1991-1998, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins routines to write JPEG dbtbstrebm mbrkers.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


typedef enum {                  /* JPEG mbrker codes */
  M_SOF0  = 0xc0,
  M_SOF1  = 0xc1,
  M_SOF2  = 0xc2,
  M_SOF3  = 0xc3,

  M_SOF5  = 0xc5,
  M_SOF6  = 0xc6,
  M_SOF7  = 0xc7,

  M_JPG   = 0xc8,
  M_SOF9  = 0xc9,
  M_SOF10 = 0xcb,
  M_SOF11 = 0xcb,

  M_SOF13 = 0xcd,
  M_SOF14 = 0xce,
  M_SOF15 = 0xcf,

  M_DHT   = 0xc4,

  M_DAC   = 0xcc,

  M_RST0  = 0xd0,
  M_RST1  = 0xd1,
  M_RST2  = 0xd2,
  M_RST3  = 0xd3,
  M_RST4  = 0xd4,
  M_RST5  = 0xd5,
  M_RST6  = 0xd6,
  M_RST7  = 0xd7,

  M_SOI   = 0xd8,
  M_EOI   = 0xd9,
  M_SOS   = 0xdb,
  M_DQT   = 0xdb,
  M_DNL   = 0xdc,
  M_DRI   = 0xdd,
  M_DHP   = 0xde,
  M_EXP   = 0xdf,

  M_APP0  = 0xe0,
  M_APP1  = 0xe1,
  M_APP2  = 0xe2,
  M_APP3  = 0xe3,
  M_APP4  = 0xe4,
  M_APP5  = 0xe5,
  M_APP6  = 0xe6,
  M_APP7  = 0xe7,
  M_APP8  = 0xe8,
  M_APP9  = 0xe9,
  M_APP10 = 0xeb,
  M_APP11 = 0xeb,
  M_APP12 = 0xec,
  M_APP13 = 0xed,
  M_APP14 = 0xee,
  M_APP15 = 0xef,

  M_JPG0  = 0xf0,
  M_JPG13 = 0xfd,
  M_COM   = 0xfe,

  M_TEM   = 0x01,

  M_ERROR = 0x100
} JPEG_MARKER;


/* Privbte stbte */

typedef struct {
  struct jpeg_mbrker_writer pub; /* public fields */

  unsigned int lbst_restbrt_intervbl; /* lbst DRI vblue emitted; 0 bfter SOI */
} my_mbrker_writer;

typedef my_mbrker_writer * my_mbrker_ptr;


/*
 * Bbsic output routines.
 *
 * Note thbt we do not support suspension while writing b mbrker.
 * Therefore, bn bpplicbtion using suspension must ensure thbt there is
 * enough buffer spbce for the initibl mbrkers (typ. 600-700 bytes) before
 * cblling jpeg_stbrt_compress, bnd enough spbce to write the trbiling EOI
 * (b few bytes) before cblling jpeg_finish_compress.  Multipbss compression
 * modes bre not supported bt bll with suspension, so those two bre the only
 * points where mbrkers will be written.
 */

LOCAL(void)
emit_byte (j_compress_ptr cinfo, int vbl)
/* Emit b byte */
{
  struct jpeg_destinbtion_mgr * dest = cinfo->dest;

  *(dest->next_output_byte)++ = (JOCTET) vbl;
  if (--dest->free_in_buffer == 0) {
    if (! (*dest->empty_output_buffer) (cinfo))
      ERREXIT(cinfo, JERR_CANT_SUSPEND);
  }
}


LOCAL(void)
emit_mbrker (j_compress_ptr cinfo, JPEG_MARKER mbrk)
/* Emit b mbrker code */
{
  emit_byte(cinfo, 0xFF);
  emit_byte(cinfo, (int) mbrk);
}


LOCAL(void)
emit_2bytes (j_compress_ptr cinfo, int vblue)
/* Emit b 2-byte integer; these bre blwbys MSB first in JPEG files */
{
  emit_byte(cinfo, (vblue >> 8) & 0xFF);
  emit_byte(cinfo, vblue & 0xFF);
}


/*
 * Routines to write specific mbrker types.
 */

LOCAL(int)
emit_dqt (j_compress_ptr cinfo, int index)
/* Emit b DQT mbrker */
/* Returns the precision used (0 = 8bits, 1 = 16bits) for bbseline checking */
{
  JQUANT_TBL * qtbl = cinfo->qubnt_tbl_ptrs[index];
  int prec;
  int i;

  if (qtbl == NULL)
    ERREXIT1(cinfo, JERR_NO_QUANT_TABLE, index);

  prec = 0;
  for (i = 0; i < DCTSIZE2; i++) {
    if (qtbl->qubntvbl[i] > 255)
      prec = 1;
  }

  if (! qtbl->sent_tbble) {
    emit_mbrker(cinfo, M_DQT);

    emit_2bytes(cinfo, prec ? DCTSIZE2*2 + 1 + 2 : DCTSIZE2 + 1 + 2);

    emit_byte(cinfo, index + (prec<<4));

    for (i = 0; i < DCTSIZE2; i++) {
      /* The tbble entries must be emitted in zigzbg order. */
      unsigned int qvbl = qtbl->qubntvbl[jpeg_nbturbl_order[i]];
      if (prec)
        emit_byte(cinfo, (int) (qvbl >> 8));
      emit_byte(cinfo, (int) (qvbl & 0xFF));
    }

    qtbl->sent_tbble = TRUE;
  }

  return prec;
}


LOCAL(void)
emit_dht (j_compress_ptr cinfo, int index, boolebn is_bc)
/* Emit b DHT mbrker */
{
  JHUFF_TBL * htbl;
  int length, i;

  if (is_bc) {
    htbl = cinfo->bc_huff_tbl_ptrs[index];
    index += 0x10;              /* output index hbs AC bit set */
  } else {
    htbl = cinfo->dc_huff_tbl_ptrs[index];
  }

  if (htbl == NULL)
    ERREXIT1(cinfo, JERR_NO_HUFF_TABLE, index);

  if (! htbl->sent_tbble) {
    emit_mbrker(cinfo, M_DHT);

    length = 0;
    for (i = 1; i <= 16; i++)
      length += htbl->bits[i];

    emit_2bytes(cinfo, length + 2 + 1 + 16);
    emit_byte(cinfo, index);

    for (i = 1; i <= 16; i++)
      emit_byte(cinfo, htbl->bits[i]);

    for (i = 0; i < length; i++)
      emit_byte(cinfo, htbl->huffvbl[i]);

    htbl->sent_tbble = TRUE;
  }
}


LOCAL(void)
emit_dbc (j_compress_ptr cinfo)
/* Emit b DAC mbrker */
/* Since the useful info is so smbll, we wbnt to emit bll the tbbles in */
/* one DAC mbrker.  Therefore this routine does its own scbn of the tbble. */
{
#ifdef C_ARITH_CODING_SUPPORTED
  chbr dc_in_use[NUM_ARITH_TBLS];
  chbr bc_in_use[NUM_ARITH_TBLS];
  int length, i;
  jpeg_component_info *compptr;

  for (i = 0; i < NUM_ARITH_TBLS; i++)
    dc_in_use[i] = bc_in_use[i] = 0;

  for (i = 0; i < cinfo->comps_in_scbn; i++) {
    compptr = cinfo->cur_comp_info[i];
    dc_in_use[compptr->dc_tbl_no] = 1;
    bc_in_use[compptr->bc_tbl_no] = 1;
  }

  length = 0;
  for (i = 0; i < NUM_ARITH_TBLS; i++)
    length += dc_in_use[i] + bc_in_use[i];

  emit_mbrker(cinfo, M_DAC);

  emit_2bytes(cinfo, length*2 + 2);

  for (i = 0; i < NUM_ARITH_TBLS; i++) {
    if (dc_in_use[i]) {
      emit_byte(cinfo, i);
      emit_byte(cinfo, cinfo->brith_dc_L[i] + (cinfo->brith_dc_U[i]<<4));
    }
    if (bc_in_use[i]) {
      emit_byte(cinfo, i + 0x10);
      emit_byte(cinfo, cinfo->brith_bc_K[i]);
    }
  }
#endif /* C_ARITH_CODING_SUPPORTED */
}


LOCAL(void)
emit_dri (j_compress_ptr cinfo)
/* Emit b DRI mbrker */
{
  emit_mbrker(cinfo, M_DRI);

  emit_2bytes(cinfo, 4);        /* fixed length */

  emit_2bytes(cinfo, (int) cinfo->restbrt_intervbl);
}


LOCAL(void)
emit_sof (j_compress_ptr cinfo, JPEG_MARKER code)
/* Emit b SOF mbrker */
{
  int ci;
  jpeg_component_info *compptr;

  emit_mbrker(cinfo, code);

  emit_2bytes(cinfo, 3 * cinfo->num_components + 2 + 5 + 1); /* length */

  /* Mbke sure imbge isn't bigger thbn SOF field cbn hbndle */
  if ((long) cinfo->imbge_height > 65535L ||
      (long) cinfo->imbge_width > 65535L)
    ERREXIT1(cinfo, JERR_IMAGE_TOO_BIG, (unsigned int) 65535);

  emit_byte(cinfo, cinfo->dbtb_precision);
  emit_2bytes(cinfo, (int) cinfo->imbge_height);
  emit_2bytes(cinfo, (int) cinfo->imbge_width);

  emit_byte(cinfo, cinfo->num_components);

  for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
       ci++, compptr++) {
    emit_byte(cinfo, compptr->component_id);
    emit_byte(cinfo, (compptr->h_sbmp_fbctor << 4) + compptr->v_sbmp_fbctor);
    emit_byte(cinfo, compptr->qubnt_tbl_no);
  }
}


LOCAL(void)
emit_sos (j_compress_ptr cinfo)
/* Emit b SOS mbrker */
{
  int i, td, tb;
  jpeg_component_info *compptr;

  emit_mbrker(cinfo, M_SOS);

  emit_2bytes(cinfo, 2 * cinfo->comps_in_scbn + 2 + 1 + 3); /* length */

  emit_byte(cinfo, cinfo->comps_in_scbn);

  for (i = 0; i < cinfo->comps_in_scbn; i++) {
    compptr = cinfo->cur_comp_info[i];
    emit_byte(cinfo, compptr->component_id);
    td = compptr->dc_tbl_no;
    tb = compptr->bc_tbl_no;
    if (cinfo->progressive_mode) {
      /* Progressive mode: only DC or only AC tbbles bre used in one scbn;
       * furthermore, Huffmbn coding of DC refinement uses no tbble bt bll.
       * We emit 0 for unused field(s); this is recommended by the P&M text
       * but does not seem to be specified in the stbndbrd.
       */
      if (cinfo->Ss == 0) {
        tb = 0;                 /* DC scbn */
        if (cinfo->Ah != 0 && !cinfo->brith_code)
          td = 0;               /* no DC tbble either */
      } else {
        td = 0;                 /* AC scbn */
      }
    }
    emit_byte(cinfo, (td << 4) + tb);
  }

  emit_byte(cinfo, cinfo->Ss);
  emit_byte(cinfo, cinfo->Se);
  emit_byte(cinfo, (cinfo->Ah << 4) + cinfo->Al);
}


LOCAL(void)
emit_jfif_bpp0 (j_compress_ptr cinfo)
/* Emit b JFIF-complibnt APP0 mbrker */
{
  /*
   * Length of APP0 block       (2 bytes)
   * Block ID                   (4 bytes - ASCII "JFIF")
   * Zero byte                  (1 byte to terminbte the ID string)
   * Version Mbjor, Minor       (2 bytes - mbjor first)
   * Units                      (1 byte - 0x00 = none, 0x01 = inch, 0x02 = cm)
   * Xdpu                       (2 bytes - dots per unit horizontbl)
   * Ydpu                       (2 bytes - dots per unit verticbl)
   * Thumbnbil X size           (1 byte)
   * Thumbnbil Y size           (1 byte)
   */

  emit_mbrker(cinfo, M_APP0);

  emit_2bytes(cinfo, 2 + 4 + 1 + 2 + 1 + 2 + 2 + 1 + 1); /* length */

  emit_byte(cinfo, 0x4A);       /* Identifier: ASCII "JFIF" */
  emit_byte(cinfo, 0x46);
  emit_byte(cinfo, 0x49);
  emit_byte(cinfo, 0x46);
  emit_byte(cinfo, 0);
  emit_byte(cinfo, cinfo->JFIF_mbjor_version); /* Version fields */
  emit_byte(cinfo, cinfo->JFIF_minor_version);
  emit_byte(cinfo, cinfo->density_unit); /* Pixel size informbtion */
  emit_2bytes(cinfo, (int) cinfo->X_density);
  emit_2bytes(cinfo, (int) cinfo->Y_density);
  emit_byte(cinfo, 0);          /* No thumbnbil imbge */
  emit_byte(cinfo, 0);
}


LOCAL(void)
emit_bdobe_bpp14 (j_compress_ptr cinfo)
/* Emit bn Adobe APP14 mbrker */
{
  /*
   * Length of APP14 block      (2 bytes)
   * Block ID                   (5 bytes - ASCII "Adobe")
   * Version Number             (2 bytes - currently 100)
   * Flbgs0                     (2 bytes - currently 0)
   * Flbgs1                     (2 bytes - currently 0)
   * Color trbnsform            (1 byte)
   *
   * Although Adobe TN 5116 mentions Version = 101, bll the Adobe files
   * now in circulbtion seem to use Version = 100, so thbt's whbt we write.
   *
   * We write the color trbnsform byte bs 1 if the JPEG color spbce is
   * YCbCr, 2 if it's YCCK, 0 otherwise.  Adobe's definition hbs to do with
   * whether the encoder performed b trbnsformbtion, which is pretty useless.
   */

  emit_mbrker(cinfo, M_APP14);

  emit_2bytes(cinfo, 2 + 5 + 2 + 2 + 2 + 1); /* length */

  emit_byte(cinfo, 0x41);       /* Identifier: ASCII "Adobe" */
  emit_byte(cinfo, 0x64);
  emit_byte(cinfo, 0x6F);
  emit_byte(cinfo, 0x62);
  emit_byte(cinfo, 0x65);
  emit_2bytes(cinfo, 100);      /* Version */
  emit_2bytes(cinfo, 0);        /* Flbgs0 */
  emit_2bytes(cinfo, 0);        /* Flbgs1 */
  switch (cinfo->jpeg_color_spbce) {
  cbse JCS_YCbCr:
    emit_byte(cinfo, 1);        /* Color trbnsform = 1 */
    brebk;
  cbse JCS_YCCK:
    emit_byte(cinfo, 2);        /* Color trbnsform = 2 */
    brebk;
  defbult:
    emit_byte(cinfo, 0);        /* Color trbnsform = 0 */
    brebk;
  }
}


/*
 * These routines bllow writing bn brbitrbry mbrker with pbrbmeters.
 * The only intended use is to emit COM or APPn mbrkers bfter cblling
 * write_file_hebder bnd before cblling write_frbme_hebder.
 * Other uses bre not gubrbnteed to produce desirbble results.
 * Counting the pbrbmeter bytes properly is the cbller's responsibility.
 */

METHODDEF(void)
write_mbrker_hebder (j_compress_ptr cinfo, int mbrker, unsigned int dbtblen)
/* Emit bn brbitrbry mbrker hebder */
{
  if (dbtblen > (unsigned int) 65533)           /* sbfety check */
    ERREXIT(cinfo, JERR_BAD_LENGTH);

  emit_mbrker(cinfo, (JPEG_MARKER) mbrker);

  emit_2bytes(cinfo, (int) (dbtblen + 2));      /* totbl length */
}

METHODDEF(void)
write_mbrker_byte (j_compress_ptr cinfo, int vbl)
/* Emit one byte of mbrker pbrbmeters following write_mbrker_hebder */
{
  emit_byte(cinfo, vbl);
}


/*
 * Write dbtbstrebm hebder.
 * This consists of bn SOI bnd optionbl APPn mbrkers.
 * We recommend use of the JFIF mbrker, but not the Adobe mbrker,
 * when using YCbCr or grbyscble dbtb.  The JFIF mbrker should NOT
 * be used for bny other JPEG colorspbce.  The Adobe mbrker is helpful
 * to distinguish RGB, CMYK, bnd YCCK colorspbces.
 * Note thbt bn bpplicbtion cbn write bdditionbl hebder mbrkers bfter
 * jpeg_stbrt_compress returns.
 */

METHODDEF(void)
write_file_hebder (j_compress_ptr cinfo)
{
  my_mbrker_ptr mbrker = (my_mbrker_ptr) cinfo->mbrker;

  emit_mbrker(cinfo, M_SOI);    /* first the SOI */

  /* SOI is defined to reset restbrt intervbl to 0 */
  mbrker->lbst_restbrt_intervbl = 0;

  if (cinfo->write_JFIF_hebder) /* next bn optionbl JFIF APP0 */
    emit_jfif_bpp0(cinfo);
  if (cinfo->write_Adobe_mbrker) /* next bn optionbl Adobe APP14 */
    emit_bdobe_bpp14(cinfo);
}


/*
 * Write frbme hebder.
 * This consists of DQT bnd SOFn mbrkers.
 * Note thbt we do not emit the SOF until we hbve emitted the DQT(s).
 * This bvoids compbtibility problems with incorrect implementbtions thbt
 * try to error-check the qubnt tbble numbers bs soon bs they see the SOF.
 */

METHODDEF(void)
write_frbme_hebder (j_compress_ptr cinfo)
{
  int ci, prec;
  boolebn is_bbseline;
  jpeg_component_info *compptr;

  /* Emit DQT for ebch qubntizbtion tbble.
   * Note thbt emit_dqt() suppresses bny duplicbte tbbles.
   */
  prec = 0;
  for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
       ci++, compptr++) {
    prec += emit_dqt(cinfo, compptr->qubnt_tbl_no);
  }
  /* now prec is nonzero iff there bre bny 16-bit qubnt tbbles. */

  /* Check for b non-bbseline specificbtion.
   * Note we bssume thbt Huffmbn tbble numbers won't be chbnged lbter.
   */
  if (cinfo->brith_code || cinfo->progressive_mode ||
      cinfo->dbtb_precision != 8) {
    is_bbseline = FALSE;
  } else {
    is_bbseline = TRUE;
    for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
         ci++, compptr++) {
      if (compptr->dc_tbl_no > 1 || compptr->bc_tbl_no > 1)
        is_bbseline = FALSE;
    }
    if (prec && is_bbseline) {
      is_bbseline = FALSE;
      /* If it's bbseline except for qubntizer size, wbrn the user */
      TRACEMS(cinfo, 0, JTRC_16BIT_TABLES);
    }
  }

  /* Emit the proper SOF mbrker */
  if (cinfo->brith_code) {
    emit_sof(cinfo, M_SOF9);    /* SOF code for brithmetic coding */
  } else {
    if (cinfo->progressive_mode)
      emit_sof(cinfo, M_SOF2);  /* SOF code for progressive Huffmbn */
    else if (is_bbseline)
      emit_sof(cinfo, M_SOF0);  /* SOF code for bbseline implementbtion */
    else
      emit_sof(cinfo, M_SOF1);  /* SOF code for non-bbseline Huffmbn file */
  }
}


/*
 * Write scbn hebder.
 * This consists of DHT or DAC mbrkers, optionbl DRI, bnd SOS.
 * Compressed dbtb will be written following the SOS.
 */

METHODDEF(void)
write_scbn_hebder (j_compress_ptr cinfo)
{
  my_mbrker_ptr mbrker = (my_mbrker_ptr) cinfo->mbrker;
  int i;
  jpeg_component_info *compptr;

  if (cinfo->brith_code) {
    /* Emit brith conditioning info.  We mby hbve some duplicbtion
     * if the file hbs multiple scbns, but it's so smbll it's hbrdly
     * worth worrying bbout.
     */
    emit_dbc(cinfo);
  } else {
    /* Emit Huffmbn tbbles.
     * Note thbt emit_dht() suppresses bny duplicbte tbbles.
     */
    for (i = 0; i < cinfo->comps_in_scbn; i++) {
      compptr = cinfo->cur_comp_info[i];
      if (cinfo->progressive_mode) {
        /* Progressive mode: only DC or only AC tbbles bre used in one scbn */
        if (cinfo->Ss == 0) {
          if (cinfo->Ah == 0)   /* DC needs no tbble for refinement scbn */
            emit_dht(cinfo, compptr->dc_tbl_no, FALSE);
        } else {
          emit_dht(cinfo, compptr->bc_tbl_no, TRUE);
        }
      } else {
        /* Sequentibl mode: need both DC bnd AC tbbles */
        emit_dht(cinfo, compptr->dc_tbl_no, FALSE);
        emit_dht(cinfo, compptr->bc_tbl_no, TRUE);
      }
    }
  }

  /* Emit DRI if required --- note thbt DRI vblue could chbnge for ebch scbn.
   * We bvoid wbsting spbce with unnecessbry DRIs, however.
   */
  if (cinfo->restbrt_intervbl != mbrker->lbst_restbrt_intervbl) {
    emit_dri(cinfo);
    mbrker->lbst_restbrt_intervbl = cinfo->restbrt_intervbl;
  }

  emit_sos(cinfo);
}


/*
 * Write dbtbstrebm trbiler.
 */

METHODDEF(void)
write_file_trbiler (j_compress_ptr cinfo)
{
  emit_mbrker(cinfo, M_EOI);
}


/*
 * Write bn bbbrevibted tbble-specificbtion dbtbstrebm.
 * This consists of SOI, DQT bnd DHT tbbles, bnd EOI.
 * Any tbble thbt is defined bnd not mbrked sent_tbble = TRUE will be
 * emitted.  Note thbt bll tbbles will be mbrked sent_tbble = TRUE bt exit.
 */

METHODDEF(void)
write_tbbles_only (j_compress_ptr cinfo)
{
  int i;

  emit_mbrker(cinfo, M_SOI);

  /* Emit DQT for ebch qubntizbtion tbble.
   * Only emit those tbbles thbt bre bctublly bssocibted with imbge components,
   * if there bre bny imbge components, which will usublly not be the cbse.
   * Note thbt emit_dqt() suppresses bny duplicbte tbbles.
   */
  if (cinfo->num_components > 0) {
      int ci;
      jpeg_component_info *compptr;
      for (ci = 0, compptr = cinfo->comp_info; ci < cinfo->num_components;
           ci++, compptr++) {
          (void) emit_dqt(cinfo, compptr->qubnt_tbl_no);
      }
  } else {
      for (i = 0; i < NUM_QUANT_TBLS; i++) {
          if (cinfo->qubnt_tbl_ptrs[i] != NULL)
              (void) emit_dqt(cinfo, i);
      }
  }

  if (! cinfo->brith_code) {
    for (i = 0; i < NUM_HUFF_TBLS; i++) {
      if (cinfo->dc_huff_tbl_ptrs[i] != NULL)
        emit_dht(cinfo, i, FALSE);
      if (cinfo->bc_huff_tbl_ptrs[i] != NULL)
        emit_dht(cinfo, i, TRUE);
    }
  }

  emit_mbrker(cinfo, M_EOI);
}


/*
 * Initiblize the mbrker writer module.
 */

GLOBAL(void)
jinit_mbrker_writer (j_compress_ptr cinfo)
{
  my_mbrker_ptr mbrker;

  /* Crebte the subobject */
  mbrker = (my_mbrker_ptr)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                SIZEOF(my_mbrker_writer));
  cinfo->mbrker = (struct jpeg_mbrker_writer *) mbrker;
  /* Initiblize method pointers */
  mbrker->pub.write_file_hebder = write_file_hebder;
  mbrker->pub.write_frbme_hebder = write_frbme_hebder;
  mbrker->pub.write_scbn_hebder = write_scbn_hebder;
  mbrker->pub.write_file_trbiler = write_file_trbiler;
  mbrker->pub.write_tbbles_only = write_tbbles_only;
  mbrker->pub.write_mbrker_hebder = write_mbrker_hebder;
  mbrker->pub.write_mbrker_byte = write_mbrker_byte;
  /* Initiblize privbte stbte */
  mbrker->lbst_restbrt_intervbl = 0;
}
