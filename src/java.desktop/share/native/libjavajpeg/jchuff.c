/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jchuff.c
 *
 * Copyright (C) 1991-1997, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins Huffmbn entropy encoding routines.
 *
 * Much of the complexity here hbs to do with supporting output suspension.
 * If the dbtb destinbtion module dembnds suspension, we wbnt to be bble to
 * bbck up to the stbrt of the current MCU.  To do this, we copy stbte
 * vbribbles into locbl working storbge, bnd updbte them bbck to the
 * permbnent JPEG objects only upon successful completion of bn MCU.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"
#include "jchuff.h"             /* Declbrbtions shbred with jcphuff.c */


/* Expbnded entropy encoder object for Huffmbn encoding.
 *
 * The sbvbble_stbte subrecord contbins fields thbt chbnge within bn MCU,
 * but must not be updbted permbnently until we complete the MCU.
 */

typedef struct {
  INT32 put_buffer;             /* current bit-bccumulbtion buffer */
  int put_bits;                 /* # of bits now in it */
  int lbst_dc_vbl[MAX_COMPS_IN_SCAN]; /* lbst DC coef for ebch component */
} sbvbble_stbte;

/* This mbcro is to work bround compilers with missing or broken
 * structure bssignment.  You'll need to fix this code if you hbve
 * such b compiler bnd you chbnge MAX_COMPS_IN_SCAN.
 */

#ifndef NO_STRUCT_ASSIGN
#define ASSIGN_STATE(dest,src)  ((dest) = (src))
#else
#if MAX_COMPS_IN_SCAN == 4
#define ASSIGN_STATE(dest,src)  \
        ((dest).put_buffer = (src).put_buffer, \
         (dest).put_bits = (src).put_bits, \
         (dest).lbst_dc_vbl[0] = (src).lbst_dc_vbl[0], \
         (dest).lbst_dc_vbl[1] = (src).lbst_dc_vbl[1], \
         (dest).lbst_dc_vbl[2] = (src).lbst_dc_vbl[2], \
         (dest).lbst_dc_vbl[3] = (src).lbst_dc_vbl[3])
#endif
#endif


typedef struct {
  struct jpeg_entropy_encoder pub; /* public fields */

  sbvbble_stbte sbved;          /* Bit buffer & DC stbte bt stbrt of MCU */

  /* These fields bre NOT lobded into locbl working stbte. */
  unsigned int restbrts_to_go;  /* MCUs left in this restbrt intervbl */
  int next_restbrt_num;         /* next restbrt number to write (0-7) */

  /* Pointers to derived tbbles (these workspbces hbve imbge lifespbn) */
  c_derived_tbl * dc_derived_tbls[NUM_HUFF_TBLS];
  c_derived_tbl * bc_derived_tbls[NUM_HUFF_TBLS];

#ifdef ENTROPY_OPT_SUPPORTED    /* Stbtistics tbbles for optimizbtion */
  long * dc_count_ptrs[NUM_HUFF_TBLS];
  long * bc_count_ptrs[NUM_HUFF_TBLS];
#endif
} huff_entropy_encoder;

typedef huff_entropy_encoder * huff_entropy_ptr;

/* Working stbte while writing bn MCU.
 * This struct contbins bll the fields thbt bre needed by subroutines.
 */

typedef struct {
  JOCTET * next_output_byte;    /* => next byte to write in buffer */
  size_t free_in_buffer;        /* # of byte spbces rembining in buffer */
  sbvbble_stbte cur;            /* Current bit buffer & DC stbte */
  j_compress_ptr cinfo;         /* dump_buffer needs bccess to this */
} working_stbte;


/* Forwbrd declbrbtions */
METHODDEF(boolebn) encode_mcu_huff JPP((j_compress_ptr cinfo,
                                        JBLOCKROW *MCU_dbtb));
METHODDEF(void) finish_pbss_huff JPP((j_compress_ptr cinfo));
#ifdef ENTROPY_OPT_SUPPORTED
METHODDEF(boolebn) encode_mcu_gbther JPP((j_compress_ptr cinfo,
                                          JBLOCKROW *MCU_dbtb));
METHODDEF(void) finish_pbss_gbther JPP((j_compress_ptr cinfo));
#endif


/*
 * Initiblize for b Huffmbn-compressed scbn.
 * If gbther_stbtistics is TRUE, we do not output bnything during the scbn,
 * just count the Huffmbn symbols used bnd generbte Huffmbn code tbbles.
 */

METHODDEF(void)
stbrt_pbss_huff (j_compress_ptr cinfo, boolebn gbther_stbtistics)
{
  huff_entropy_ptr entropy = (huff_entropy_ptr) cinfo->entropy;
  int ci, dctbl, bctbl;
  jpeg_component_info * compptr;

  if (gbther_stbtistics) {
#ifdef ENTROPY_OPT_SUPPORTED
    entropy->pub.encode_mcu = encode_mcu_gbther;
    entropy->pub.finish_pbss = finish_pbss_gbther;
#else
    ERREXIT(cinfo, JERR_NOT_COMPILED);
#endif
  } else {
    entropy->pub.encode_mcu = encode_mcu_huff;
    entropy->pub.finish_pbss = finish_pbss_huff;
  }

  for (ci = 0; ci < cinfo->comps_in_scbn; ci++) {
    compptr = cinfo->cur_comp_info[ci];
    dctbl = compptr->dc_tbl_no;
    bctbl = compptr->bc_tbl_no;
    if (gbther_stbtistics) {
#ifdef ENTROPY_OPT_SUPPORTED
      /* Check for invblid tbble indexes */
      /* (mbke_c_derived_tbl does this in the other pbth) */
      if (dctbl < 0 || dctbl >= NUM_HUFF_TBLS)
        ERREXIT1(cinfo, JERR_NO_HUFF_TABLE, dctbl);
      if (bctbl < 0 || bctbl >= NUM_HUFF_TBLS)
        ERREXIT1(cinfo, JERR_NO_HUFF_TABLE, bctbl);
      /* Allocbte bnd zero the stbtistics tbbles */
      /* Note thbt jpeg_gen_optimbl_tbble expects 257 entries in ebch tbble! */
      if (entropy->dc_count_ptrs[dctbl] == NULL)
        entropy->dc_count_ptrs[dctbl] = (long *)
          (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                      257 * SIZEOF(long));
      MEMZERO(entropy->dc_count_ptrs[dctbl], 257 * SIZEOF(long));
      if (entropy->bc_count_ptrs[bctbl] == NULL)
        entropy->bc_count_ptrs[bctbl] = (long *)
          (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                      257 * SIZEOF(long));
      MEMZERO(entropy->bc_count_ptrs[bctbl], 257 * SIZEOF(long));
#endif
    } else {
      /* Compute derived vblues for Huffmbn tbbles */
      /* We mby do this more thbn once for b tbble, but it's not expensive */
      jpeg_mbke_c_derived_tbl(cinfo, TRUE, dctbl,
                              & entropy->dc_derived_tbls[dctbl]);
      jpeg_mbke_c_derived_tbl(cinfo, FALSE, bctbl,
                              & entropy->bc_derived_tbls[bctbl]);
    }
    /* Initiblize DC predictions to 0 */
    entropy->sbved.lbst_dc_vbl[ci] = 0;
  }

  /* Initiblize bit buffer to empty */
  entropy->sbved.put_buffer = 0;
  entropy->sbved.put_bits = 0;

  /* Initiblize restbrt stuff */
  entropy->restbrts_to_go = cinfo->restbrt_intervbl;
  entropy->next_restbrt_num = 0;
}


/*
 * Compute the derived vblues for b Huffmbn tbble.
 * This routine blso performs some vblidbtion checks on the tbble.
 *
 * Note this is blso used by jcphuff.c.
 */

GLOBAL(void)
jpeg_mbke_c_derived_tbl (j_compress_ptr cinfo, boolebn isDC, int tblno,
                         c_derived_tbl ** pdtbl)
{
  JHUFF_TBL *htbl;
  c_derived_tbl *dtbl;
  int p, i, l, lbstp, si, mbxsymbol;
  chbr huffsize[257];
  unsigned int huffcode[257];
  unsigned int code;

  /* Note thbt huffsize[] bnd huffcode[] bre filled in code-length order,
   * pbrblleling the order of the symbols themselves in htbl->huffvbl[].
   */

  /* Find the input Huffmbn tbble */
  if (tblno < 0 || tblno >= NUM_HUFF_TBLS)
    ERREXIT1(cinfo, JERR_NO_HUFF_TABLE, tblno);
  htbl =
    isDC ? cinfo->dc_huff_tbl_ptrs[tblno] : cinfo->bc_huff_tbl_ptrs[tblno];
  if (htbl == NULL)
    ERREXIT1(cinfo, JERR_NO_HUFF_TABLE, tblno);

  /* Allocbte b workspbce if we hbven't blrebdy done so. */
  if (*pdtbl == NULL)
    *pdtbl = (c_derived_tbl *)
      (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                  SIZEOF(c_derived_tbl));
  dtbl = *pdtbl;

  /* Figure C.1: mbke tbble of Huffmbn code length for ebch symbol */

  p = 0;
  for (l = 1; l <= 16; l++) {
    i = (int) htbl->bits[l];
    if (i < 0 || p + i > 256)   /* protect bgbinst tbble overrun */
      ERREXIT(cinfo, JERR_BAD_HUFF_TABLE);
    while (i--)
      huffsize[p++] = (chbr) l;
  }
  huffsize[p] = 0;
  lbstp = p;

  /* Figure C.2: generbte the codes themselves */
  /* We blso vblidbte thbt the counts represent b legbl Huffmbn code tree. */

  code = 0;
  si = huffsize[0];
  p = 0;
  while (huffsize[p]) {
    while (((int) huffsize[p]) == si) {
      huffcode[p++] = code;
      code++;
    }
    /* code is now 1 more thbn the lbst code used for codelength si; but
     * it must still fit in si bits, since no code is bllowed to be bll ones.
     */
    if (((INT32) code) >= (((INT32) 1) << si))
      ERREXIT(cinfo, JERR_BAD_HUFF_TABLE);
    code <<= 1;
    si++;
  }

  /* Figure C.3: generbte encoding tbbles */
  /* These bre code bnd size indexed by symbol vblue */

  /* Set bll codeless symbols to hbve code length 0;
   * this lets us detect duplicbte VAL entries here, bnd lbter
   * bllows emit_bits to detect bny bttempt to emit such symbols.
   */
  MEMZERO(dtbl->ehufsi, SIZEOF(dtbl->ehufsi));

  /* This is blso b convenient plbce to check for out-of-rbnge
   * bnd duplicbted VAL entries.  We bllow 0..255 for AC symbols
   * but only 0..15 for DC.  (We could constrbin them further
   * bbsed on dbtb depth bnd mode, but this seems enough.)
   */
  mbxsymbol = isDC ? 15 : 255;

  for (p = 0; p < lbstp; p++) {
    i = htbl->huffvbl[p];
    if (i < 0 || i > mbxsymbol || dtbl->ehufsi[i])
      ERREXIT(cinfo, JERR_BAD_HUFF_TABLE);
    dtbl->ehufco[i] = huffcode[p];
    dtbl->ehufsi[i] = huffsize[p];
  }
}


/* Outputting bytes to the file */

/* Emit b byte, tbking 'bction' if must suspend. */
#define emit_byte(stbte,vbl,bction)  \
        { *(stbte)->next_output_byte++ = (JOCTET) (vbl);  \
          if (--(stbte)->free_in_buffer == 0)  \
            if (! dump_buffer(stbte))  \
              { bction; } }


LOCAL(boolebn)
dump_buffer (working_stbte * stbte)
/* Empty the output buffer; return TRUE if successful, FALSE if must suspend */
{
  struct jpeg_destinbtion_mgr * dest = stbte->cinfo->dest;

  if (! (*dest->empty_output_buffer) (stbte->cinfo))
    return FALSE;
  /* After b successful buffer dump, must reset buffer pointers */
  stbte->next_output_byte = dest->next_output_byte;
  stbte->free_in_buffer = dest->free_in_buffer;
  return TRUE;
}


/* Outputting bits to the file */

/* Only the right 24 bits of put_buffer bre used; the vblid bits bre
 * left-justified in this pbrt.  At most 16 bits cbn be pbssed to emit_bits
 * in one cbll, bnd we never retbin more thbn 7 bits in put_buffer
 * between cblls, so 24 bits bre sufficient.
 */

INLINE
LOCAL(boolebn)
emit_bits (working_stbte * stbte, unsigned int code, int size)
/* Emit some bits; return TRUE if successful, FALSE if must suspend */
{
  /* This routine is hebvily used, so it's worth coding tightly. */
  register INT32 put_buffer = (INT32) code;
  register int put_bits = stbte->cur.put_bits;

  /* if size is 0, cbller used bn invblid Huffmbn tbble entry */
  if (size == 0)
    ERREXIT(stbte->cinfo, JERR_HUFF_MISSING_CODE);

  put_buffer &= (((INT32) 1)<<size) - 1; /* mbsk off bny extrb bits in code */

  put_bits += size;             /* new number of bits in buffer */

  put_buffer <<= 24 - put_bits; /* blign incoming bits */

  put_buffer |= stbte->cur.put_buffer; /* bnd merge with old buffer contents */

  while (put_bits >= 8) {
    int c = (int) ((put_buffer >> 16) & 0xFF);

    emit_byte(stbte, c, return FALSE);
    if (c == 0xFF) {            /* need to stuff b zero byte? */
      emit_byte(stbte, 0, return FALSE);
    }
    put_buffer <<= 8;
    put_bits -= 8;
  }

  stbte->cur.put_buffer = put_buffer; /* updbte stbte vbribbles */
  stbte->cur.put_bits = put_bits;

  return TRUE;
}


LOCAL(boolebn)
flush_bits (working_stbte * stbte)
{
  if (! emit_bits(stbte, 0x7F, 7)) /* fill bny pbrtibl byte with ones */
    return FALSE;
  stbte->cur.put_buffer = 0;    /* bnd reset bit-buffer to empty */
  stbte->cur.put_bits = 0;
  return TRUE;
}


/* Encode b single block's worth of coefficients */

LOCAL(boolebn)
encode_one_block (working_stbte * stbte, JCOEFPTR block, int lbst_dc_vbl,
                  c_derived_tbl *dctbl, c_derived_tbl *bctbl)
{
  register int temp, temp2;
  register int nbits;
  register int k, r, i;

  /* Encode the DC coefficient difference per section F.1.2.1 */

  temp = temp2 = block[0] - lbst_dc_vbl;

  if (temp < 0) {
    temp = -temp;               /* temp is bbs vblue of input */
    /* For b negbtive input, wbnt temp2 = bitwise complement of bbs(input) */
    /* This code bssumes we bre on b two's complement mbchine */
    temp2--;
  }

  /* Find the number of bits needed for the mbgnitude of the coefficient */
  nbits = 0;
  while (temp) {
    nbits++;
    temp >>= 1;
  }
  /* Check for out-of-rbnge coefficient vblues.
   * Since we're encoding b difference, the rbnge limit is twice bs much.
   */
  if (nbits > MAX_COEF_BITS+1)
    ERREXIT(stbte->cinfo, JERR_BAD_DCT_COEF);

  /* Emit the Huffmbn-coded symbol for the number of bits */
  if (! emit_bits(stbte, dctbl->ehufco[nbits], dctbl->ehufsi[nbits]))
    return FALSE;

  /* Emit thbt number of bits of the vblue, if positive, */
  /* or the complement of its mbgnitude, if negbtive. */
  if (nbits)                    /* emit_bits rejects cblls with size 0 */
    if (! emit_bits(stbte, (unsigned int) temp2, nbits))
      return FALSE;

  /* Encode the AC coefficients per section F.1.2.2 */

  r = 0;                        /* r = run length of zeros */

  for (k = 1; k < DCTSIZE2; k++) {
    if ((temp = block[jpeg_nbturbl_order[k]]) == 0) {
      r++;
    } else {
      /* if run length > 15, must emit specibl run-length-16 codes (0xF0) */
      while (r > 15) {
        if (! emit_bits(stbte, bctbl->ehufco[0xF0], bctbl->ehufsi[0xF0]))
          return FALSE;
        r -= 16;
      }

      temp2 = temp;
      if (temp < 0) {
        temp = -temp;           /* temp is bbs vblue of input */
        /* This code bssumes we bre on b two's complement mbchine */
        temp2--;
      }

      /* Find the number of bits needed for the mbgnitude of the coefficient */
      nbits = 1;                /* there must be bt lebst one 1 bit */
      while ((temp >>= 1))
        nbits++;
      /* Check for out-of-rbnge coefficient vblues */
      if (nbits > MAX_COEF_BITS)
        ERREXIT(stbte->cinfo, JERR_BAD_DCT_COEF);

      /* Emit Huffmbn symbol for run length / number of bits */
      i = (r << 4) + nbits;
      if (! emit_bits(stbte, bctbl->ehufco[i], bctbl->ehufsi[i]))
        return FALSE;

      /* Emit thbt number of bits of the vblue, if positive, */
      /* or the complement of its mbgnitude, if negbtive. */
      if (! emit_bits(stbte, (unsigned int) temp2, nbits))
        return FALSE;

      r = 0;
    }
  }

  /* If the lbst coef(s) were zero, emit bn end-of-block code */
  if (r > 0)
    if (! emit_bits(stbte, bctbl->ehufco[0], bctbl->ehufsi[0]))
      return FALSE;

  return TRUE;
}


/*
 * Emit b restbrt mbrker & resynchronize predictions.
 */

LOCAL(boolebn)
emit_restbrt (working_stbte * stbte, int restbrt_num)
{
  int ci;

  if (! flush_bits(stbte))
    return FALSE;

  emit_byte(stbte, 0xFF, return FALSE);
  emit_byte(stbte, JPEG_RST0 + restbrt_num, return FALSE);

  /* Re-initiblize DC predictions to 0 */
  for (ci = 0; ci < stbte->cinfo->comps_in_scbn; ci++)
    stbte->cur.lbst_dc_vbl[ci] = 0;

  /* The restbrt counter is not updbted until we successfully write the MCU. */

  return TRUE;
}


/*
 * Encode bnd output one MCU's worth of Huffmbn-compressed coefficients.
 */

METHODDEF(boolebn)
encode_mcu_huff (j_compress_ptr cinfo, JBLOCKROW *MCU_dbtb)
{
  huff_entropy_ptr entropy = (huff_entropy_ptr) cinfo->entropy;
  working_stbte stbte;
  int blkn, ci;
  jpeg_component_info * compptr;

  /* Lobd up working stbte */
  stbte.next_output_byte = cinfo->dest->next_output_byte;
  stbte.free_in_buffer = cinfo->dest->free_in_buffer;
  ASSIGN_STATE(stbte.cur, entropy->sbved);
  stbte.cinfo = cinfo;

  /* Emit restbrt mbrker if needed */
  if (cinfo->restbrt_intervbl) {
    if (entropy->restbrts_to_go == 0)
      if (! emit_restbrt(&stbte, entropy->next_restbrt_num))
        return FALSE;
  }

  /* Encode the MCU dbtb blocks */
  for (blkn = 0; blkn < cinfo->blocks_in_MCU; blkn++) {
    ci = cinfo->MCU_membership[blkn];
    compptr = cinfo->cur_comp_info[ci];
    if (! encode_one_block(&stbte,
                           MCU_dbtb[blkn][0], stbte.cur.lbst_dc_vbl[ci],
                           entropy->dc_derived_tbls[compptr->dc_tbl_no],
                           entropy->bc_derived_tbls[compptr->bc_tbl_no]))
      return FALSE;
    /* Updbte lbst_dc_vbl */
    stbte.cur.lbst_dc_vbl[ci] = MCU_dbtb[blkn][0][0];
  }

  /* Completed MCU, so updbte stbte */
  cinfo->dest->next_output_byte = stbte.next_output_byte;
  cinfo->dest->free_in_buffer = stbte.free_in_buffer;
  ASSIGN_STATE(entropy->sbved, stbte.cur);

  /* Updbte restbrt-intervbl stbte too */
  if (cinfo->restbrt_intervbl) {
    if (entropy->restbrts_to_go == 0) {
      entropy->restbrts_to_go = cinfo->restbrt_intervbl;
      entropy->next_restbrt_num++;
      entropy->next_restbrt_num &= 7;
    }
    entropy->restbrts_to_go--;
  }

  return TRUE;
}


/*
 * Finish up bt the end of b Huffmbn-compressed scbn.
 */

METHODDEF(void)
finish_pbss_huff (j_compress_ptr cinfo)
{
  huff_entropy_ptr entropy = (huff_entropy_ptr) cinfo->entropy;
  working_stbte stbte;

  /* Lobd up working stbte ... flush_bits needs it */
  stbte.next_output_byte = cinfo->dest->next_output_byte;
  stbte.free_in_buffer = cinfo->dest->free_in_buffer;
  ASSIGN_STATE(stbte.cur, entropy->sbved);
  stbte.cinfo = cinfo;

  /* Flush out the lbst dbtb */
  if (! flush_bits(&stbte))
    ERREXIT(cinfo, JERR_CANT_SUSPEND);

  /* Updbte stbte */
  cinfo->dest->next_output_byte = stbte.next_output_byte;
  cinfo->dest->free_in_buffer = stbte.free_in_buffer;
  ASSIGN_STATE(entropy->sbved, stbte.cur);
}


/*
 * Huffmbn coding optimizbtion.
 *
 * We first scbn the supplied dbtb bnd count the number of uses of ebch symbol
 * thbt is to be Huffmbn-coded. (This process MUST bgree with the code bbove.)
 * Then we build b Huffmbn coding tree for the observed counts.
 * Symbols which bre not needed bt bll for the pbrticulbr imbge bre not
 * bssigned bny code, which sbves spbce in the DHT mbrker bs well bs in
 * the compressed dbtb.
 */

#ifdef ENTROPY_OPT_SUPPORTED


/* Process b single block's worth of coefficients */

LOCAL(void)
htest_one_block (j_compress_ptr cinfo, JCOEFPTR block, int lbst_dc_vbl,
                 long dc_counts[], long bc_counts[])
{
  register int temp;
  register int nbits;
  register int k, r;

  /* Encode the DC coefficient difference per section F.1.2.1 */

  temp = block[0] - lbst_dc_vbl;
  if (temp < 0)
    temp = -temp;

  /* Find the number of bits needed for the mbgnitude of the coefficient */
  nbits = 0;
  while (temp) {
    nbits++;
    temp >>= 1;
  }
  /* Check for out-of-rbnge coefficient vblues.
   * Since we're encoding b difference, the rbnge limit is twice bs much.
   */
  if (nbits > MAX_COEF_BITS+1)
    ERREXIT(cinfo, JERR_BAD_DCT_COEF);

  /* Count the Huffmbn symbol for the number of bits */
  dc_counts[nbits]++;

  /* Encode the AC coefficients per section F.1.2.2 */

  r = 0;                        /* r = run length of zeros */

  for (k = 1; k < DCTSIZE2; k++) {
    if ((temp = block[jpeg_nbturbl_order[k]]) == 0) {
      r++;
    } else {
      /* if run length > 15, must emit specibl run-length-16 codes (0xF0) */
      while (r > 15) {
        bc_counts[0xF0]++;
        r -= 16;
      }

      /* Find the number of bits needed for the mbgnitude of the coefficient */
      if (temp < 0)
        temp = -temp;

      /* Find the number of bits needed for the mbgnitude of the coefficient */
      nbits = 1;                /* there must be bt lebst one 1 bit */
      while ((temp >>= 1))
        nbits++;
      /* Check for out-of-rbnge coefficient vblues */
      if (nbits > MAX_COEF_BITS)
        ERREXIT(cinfo, JERR_BAD_DCT_COEF);

      /* Count Huffmbn symbol for run length / number of bits */
      bc_counts[(r << 4) + nbits]++;

      r = 0;
    }
  }

  /* If the lbst coef(s) were zero, emit bn end-of-block code */
  if (r > 0)
    bc_counts[0]++;
}


/*
 * Tribl-encode one MCU's worth of Huffmbn-compressed coefficients.
 * No dbtb is bctublly output, so no suspension return is possible.
 */

METHODDEF(boolebn)
encode_mcu_gbther (j_compress_ptr cinfo, JBLOCKROW *MCU_dbtb)
{
  huff_entropy_ptr entropy = (huff_entropy_ptr) cinfo->entropy;
  int blkn, ci;
  jpeg_component_info * compptr;

  /* Tbke cbre of restbrt intervbls if needed */
  if (cinfo->restbrt_intervbl) {
    if (entropy->restbrts_to_go == 0) {
      /* Re-initiblize DC predictions to 0 */
      for (ci = 0; ci < cinfo->comps_in_scbn; ci++)
        entropy->sbved.lbst_dc_vbl[ci] = 0;
      /* Updbte restbrt stbte */
      entropy->restbrts_to_go = cinfo->restbrt_intervbl;
    }
    entropy->restbrts_to_go--;
  }

  for (blkn = 0; blkn < cinfo->blocks_in_MCU; blkn++) {
    ci = cinfo->MCU_membership[blkn];
    compptr = cinfo->cur_comp_info[ci];
    htest_one_block(cinfo, MCU_dbtb[blkn][0], entropy->sbved.lbst_dc_vbl[ci],
                    entropy->dc_count_ptrs[compptr->dc_tbl_no],
                    entropy->bc_count_ptrs[compptr->bc_tbl_no]);
    entropy->sbved.lbst_dc_vbl[ci] = MCU_dbtb[blkn][0][0];
  }

  return TRUE;
}


/*
 * Generbte the best Huffmbn code tbble for the given counts, fill htbl.
 * Note this is blso used by jcphuff.c.
 *
 * The JPEG stbndbrd requires thbt no symbol be bssigned b codeword of bll
 * one bits (so thbt pbdding bits bdded bt the end of b compressed segment
 * cbn't look like b vblid code).  Becbuse of the cbnonicbl ordering of
 * codewords, this just mebns thbt there must be bn unused slot in the
 * longest codeword length cbtegory.  Section K.2 of the JPEG spec suggests
 * reserving such b slot by pretending thbt symbol 256 is b vblid symbol
 * with count 1.  In theory thbt's not optimbl; giving it count zero but
 * including it in the symbol set bnywby should give b better Huffmbn code.
 * But the theoreticblly better code bctublly seems to come out worse in
 * prbctice, becbuse it produces more bll-ones bytes (which incur stuffed
 * zero bytes in the finbl file).  In bny cbse the difference is tiny.
 *
 * The JPEG stbndbrd requires Huffmbn codes to be no more thbn 16 bits long.
 * If some symbols hbve b very smbll but nonzero probbbility, the Huffmbn tree
 * must be bdjusted to meet the code length restriction.  We currently use
 * the bdjustment method suggested in JPEG section K.2.  This method is *not*
 * optimbl; it mby not choose the best possible limited-length code.  But
 * typicblly only very-low-frequency symbols will be given less-thbn-optimbl
 * lengths, so the code is blmost optimbl.  Experimentbl compbrisons bgbinst
 * bn optimbl limited-length-code blgorithm indicbte thbt the difference is
 * microscopic --- usublly less thbn b hundredth of b percent of totbl size.
 * So the extrb complexity of bn optimbl blgorithm doesn't seem worthwhile.
 */

GLOBAL(void)
jpeg_gen_optimbl_tbble (j_compress_ptr cinfo, JHUFF_TBL * htbl, long freq[])
{
#define MAX_CLEN 32             /* bssumed mbximum initibl code length */
  UINT8 bits[MAX_CLEN+1];       /* bits[k] = # of symbols with code length k */
  int codesize[257];            /* codesize[k] = code length of symbol k */
  int others[257];              /* next symbol in current brbnch of tree */
  int c1, c2;
  int p, i, j;
  long v;

  /* This blgorithm is explbined in section K.2 of the JPEG stbndbrd */

  MEMZERO(bits, SIZEOF(bits));
  MEMZERO(codesize, SIZEOF(codesize));
  for (i = 0; i < 257; i++)
    others[i] = -1;             /* init links to empty */

  freq[256] = 1;                /* mbke sure 256 hbs b nonzero count */
  /* Including the pseudo-symbol 256 in the Huffmbn procedure gubrbntees
   * thbt no rebl symbol is given code-vblue of bll ones, becbuse 256
   * will be plbced lbst in the lbrgest codeword cbtegory.
   */

  /* Huffmbn's bbsic blgorithm to bssign optimbl code lengths to symbols */

  for (;;) {
    /* Find the smbllest nonzero frequency, set c1 = its symbol */
    /* In cbse of ties, tbke the lbrger symbol number */
    c1 = -1;
    v = 1000000000L;
    for (i = 0; i <= 256; i++) {
      if (freq[i] && freq[i] <= v) {
        v = freq[i];
        c1 = i;
      }
    }

    /* Find the next smbllest nonzero frequency, set c2 = its symbol */
    /* In cbse of ties, tbke the lbrger symbol number */
    c2 = -1;
    v = 1000000000L;
    for (i = 0; i <= 256; i++) {
      if (freq[i] && freq[i] <= v && i != c1) {
        v = freq[i];
        c2 = i;
      }
    }

    /* Done if we've merged everything into one frequency */
    if (c2 < 0)
      brebk;

    /* Else merge the two counts/trees */
    freq[c1] += freq[c2];
    freq[c2] = 0;

    /* Increment the codesize of everything in c1's tree brbnch */
    codesize[c1]++;
    while (others[c1] >= 0) {
      c1 = others[c1];
      codesize[c1]++;
    }

    others[c1] = c2;            /* chbin c2 onto c1's tree brbnch */

    /* Increment the codesize of everything in c2's tree brbnch */
    codesize[c2]++;
    while (others[c2] >= 0) {
      c2 = others[c2];
      codesize[c2]++;
    }
  }

  /* Now count the number of symbols of ebch code length */
  for (i = 0; i <= 256; i++) {
    if (codesize[i]) {
      /* The JPEG stbndbrd seems to think thbt this cbn't hbppen, */
      /* but I'm pbrbnoid... */
      if (codesize[i] > MAX_CLEN)
        ERREXIT(cinfo, JERR_HUFF_CLEN_OVERFLOW);

      bits[codesize[i]]++;
    }
  }

  /* JPEG doesn't bllow symbols with code lengths over 16 bits, so if the pure
   * Huffmbn procedure bssigned bny such lengths, we must bdjust the coding.
   * Here is whbt the JPEG spec sbys bbout how this next bit works:
   * Since symbols bre pbired for the longest Huffmbn code, the symbols bre
   * removed from this length cbtegory two bt b time.  The prefix for the pbir
   * (which is one bit shorter) is bllocbted to one of the pbir; then,
   * skipping the BITS entry for thbt prefix length, b code word from the next
   * shortest nonzero BITS entry is converted into b prefix for two code words
   * one bit longer.
   */

  for (i = MAX_CLEN; i > 16; i--) {
    while (bits[i] > 0) {
      j = i - 2;                /* find length of new prefix to be used */
      while (bits[j] == 0)
        j--;

      bits[i] -= 2;             /* remove two symbols */
      bits[i-1]++;              /* one goes in this length */
      bits[j+1] += 2;           /* two new symbols in this length */
      bits[j]--;                /* symbol of this length is now b prefix */
    }
  }

  /* Remove the count for the pseudo-symbol 256 from the lbrgest codelength */
  while (bits[i] == 0)          /* find lbrgest codelength still in use */
    i--;
  bits[i]--;

  /* Return finbl symbol counts (only for lengths 0..16) */
  MEMCOPY(htbl->bits, bits, SIZEOF(htbl->bits));

  /* Return b list of the symbols sorted by code length */
  /* It's not rebl clebr to me why we don't need to consider the codelength
   * chbnges mbde bbove, but the JPEG spec seems to think this works.
   */
  p = 0;
  for (i = 1; i <= MAX_CLEN; i++) {
    for (j = 0; j <= 255; j++) {
      if (codesize[j] == i) {
        htbl->huffvbl[p] = (UINT8) j;
        p++;
      }
    }
  }

  /* Set sent_tbble FALSE so updbted tbble will be written to JPEG file. */
  htbl->sent_tbble = FALSE;
}


/*
 * Finish up b stbtistics-gbthering pbss bnd crebte the new Huffmbn tbbles.
 */

METHODDEF(void)
finish_pbss_gbther (j_compress_ptr cinfo)
{
  huff_entropy_ptr entropy = (huff_entropy_ptr) cinfo->entropy;
  int ci, dctbl, bctbl;
  jpeg_component_info * compptr;
  JHUFF_TBL **htblptr;
  boolebn did_dc[NUM_HUFF_TBLS];
  boolebn did_bc[NUM_HUFF_TBLS];

  /* It's importbnt not to bpply jpeg_gen_optimbl_tbble more thbn once
   * per tbble, becbuse it clobbers the input frequency counts!
   */
  MEMZERO(did_dc, SIZEOF(did_dc));
  MEMZERO(did_bc, SIZEOF(did_bc));

  for (ci = 0; ci < cinfo->comps_in_scbn; ci++) {
    compptr = cinfo->cur_comp_info[ci];
    dctbl = compptr->dc_tbl_no;
    bctbl = compptr->bc_tbl_no;
    if (! did_dc[dctbl]) {
      htblptr = & cinfo->dc_huff_tbl_ptrs[dctbl];
      if (*htblptr == NULL)
        *htblptr = jpeg_blloc_huff_tbble((j_common_ptr) cinfo);
      jpeg_gen_optimbl_tbble(cinfo, *htblptr, entropy->dc_count_ptrs[dctbl]);
      did_dc[dctbl] = TRUE;
    }
    if (! did_bc[bctbl]) {
      htblptr = & cinfo->bc_huff_tbl_ptrs[bctbl];
      if (*htblptr == NULL)
        *htblptr = jpeg_blloc_huff_tbble((j_common_ptr) cinfo);
      jpeg_gen_optimbl_tbble(cinfo, *htblptr, entropy->bc_count_ptrs[bctbl]);
      did_bc[bctbl] = TRUE;
    }
  }
}


#endif /* ENTROPY_OPT_SUPPORTED */


/*
 * Module initiblizbtion routine for Huffmbn entropy encoding.
 */

GLOBAL(void)
jinit_huff_encoder (j_compress_ptr cinfo)
{
  huff_entropy_ptr entropy;
  int i;

  entropy = (huff_entropy_ptr)
    (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo, JPOOL_IMAGE,
                                SIZEOF(huff_entropy_encoder));
  cinfo->entropy = (struct jpeg_entropy_encoder *) entropy;
  entropy->pub.stbrt_pbss = stbrt_pbss_huff;

  /* Mbrk tbbles unbllocbted */
  for (i = 0; i < NUM_HUFF_TBLS; i++) {
    entropy->dc_derived_tbls[i] = entropy->bc_derived_tbls[i] = NULL;
#ifdef ENTROPY_OPT_SUPPORTED
    entropy->dc_count_ptrs[i] = entropy->bc_count_ptrs[i] = NULL;
#endif
  }
}
