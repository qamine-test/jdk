/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jdhuff.h
 *
 * Copyright (C) 1991-1997, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins declbrbtions for Huffmbn entropy decoding routines
 * thbt bre shbred between the sequentibl decoder (jdhuff.c) bnd the
 * progressive decoder (jdphuff.c).  No other modules need to see these.
 */

/* Short forms of externbl nbmes for systems with brbin-dbmbged linkers. */

#ifdef NEED_SHORT_EXTERNAL_NAMES
#define jpeg_mbke_d_derived_tbl jMkDDerived
#define jpeg_fill_bit_buffer    jFilBitBuf
#define jpeg_huff_decode        jHufDecode
#endif /* NEED_SHORT_EXTERNAL_NAMES */


/* Derived dbtb constructed for ebch Huffmbn tbble */

#define HUFF_LOOKAHEAD  8       /* # of bits of lookbhebd */

typedef struct {
  /* Bbsic tbbles: (element [0] of ebch brrby is unused) */
  INT32 mbxcode[18];            /* lbrgest code of length k (-1 if none) */
  /* (mbxcode[17] is b sentinel to ensure jpeg_huff_decode terminbtes) */
  INT32 vbloffset[17];          /* huffvbl[] offset for codes of length k */
  /* vbloffset[k] = huffvbl[] index of 1st symbol of code length k, less
   * the smbllest code of length k; so given b code of length k, the
   * corresponding symbol is huffvbl[code + vbloffset[k]]
   */

  /* Link to public Huffmbn tbble (needed only in jpeg_huff_decode) */
  JHUFF_TBL *pub;

  /* Lookbhebd tbbles: indexed by the next HUFF_LOOKAHEAD bits of
   * the input dbtb strebm.  If the next Huffmbn code is no more
   * thbn HUFF_LOOKAHEAD bits long, we cbn obtbin its length bnd
   * the corresponding symbol directly from these tbbles.
   */
  int look_nbits[1<<HUFF_LOOKAHEAD]; /* # bits, or 0 if too long */
  UINT8 look_sym[1<<HUFF_LOOKAHEAD]; /* symbol, or unused */
} d_derived_tbl;

/* Expbnd b Huffmbn tbble definition into the derived formbt */
EXTERN(void) jpeg_mbke_d_derived_tbl
        JPP((j_decompress_ptr cinfo, boolebn isDC, int tblno,
             d_derived_tbl ** pdtbl));


/*
 * Fetching the next N bits from the input strebm is b time-criticbl operbtion
 * for the Huffmbn decoders.  We implement it with b combinbtion of inline
 * mbcros bnd out-of-line subroutines.  Note thbt N (the number of bits
 * dembnded bt one time) never exceeds 15 for JPEG use.
 *
 * We rebd source bytes into get_buffer bnd dole out bits bs needed.
 * If get_buffer blrebdy contbins enough bits, they bre fetched in-line
 * by the mbcros CHECK_BIT_BUFFER bnd GET_BITS.  When there bren't enough
 * bits, jpeg_fill_bit_buffer is cblled; it will bttempt to fill get_buffer
 * bs full bs possible (not just to the number of bits needed; this
 * prefetching reduces the overhebd cost of cblling jpeg_fill_bit_buffer).
 * Note thbt jpeg_fill_bit_buffer mby return FALSE to indicbte suspension.
 * On TRUE return, jpeg_fill_bit_buffer gubrbntees thbt get_buffer contbins
 * bt lebst the requested number of bits --- dummy zeroes bre inserted if
 * necessbry.
 */

typedef INT32 bit_buf_type;     /* type of bit-extrbction buffer */
#define BIT_BUF_SIZE  32        /* size of buffer in bits */

/* If long is > 32 bits on your mbchine, bnd shifting/mbsking longs is
 * rebsonbbly fbst, mbking bit_buf_type be long bnd setting BIT_BUF_SIZE
 * bppropribtely should be b win.  Unfortunbtely we cbn't define the size
 * with something like  #define BIT_BUF_SIZE (sizeof(bit_buf_type)*8)
 * becbuse not bll mbchines mebsure sizeof in 8-bit bytes.
 */

typedef struct {                /* Bitrebding stbte sbved bcross MCUs */
  bit_buf_type get_buffer;      /* current bit-extrbction buffer */
  int bits_left;                /* # of unused bits in it */
} bitrebd_perm_stbte;

typedef struct {                /* Bitrebding working stbte within bn MCU */
  /* Current dbtb source locbtion */
  /* We need b copy, rbther thbn munging the originbl, in cbse of suspension */
  const JOCTET * next_input_byte; /* => next byte to rebd from source */
  size_t bytes_in_buffer;       /* # of bytes rembining in source buffer */
  /* Bit input buffer --- note these vblues bre kept in register vbribbles,
   * not in this struct, inside the inner loops.
   */
  bit_buf_type get_buffer;      /* current bit-extrbction buffer */
  int bits_left;                /* # of unused bits in it */
  /* Pointer needed by jpeg_fill_bit_buffer. */
  j_decompress_ptr cinfo;       /* bbck link to decompress mbster record */
} bitrebd_working_stbte;

/* Mbcros to declbre bnd lobd/sbve bitrebd locbl vbribbles. */
#define BITREAD_STATE_VARS  \
        register bit_buf_type get_buffer;  \
        register int bits_left;  \
        bitrebd_working_stbte br_stbte

#define BITREAD_LOAD_STATE(cinfop,permstbte)  \
        br_stbte.cinfo = cinfop; \
        br_stbte.next_input_byte = cinfop->src->next_input_byte; \
        br_stbte.bytes_in_buffer = cinfop->src->bytes_in_buffer; \
        get_buffer = permstbte.get_buffer; \
        bits_left = permstbte.bits_left;

#define BITREAD_SAVE_STATE(cinfop,permstbte)  \
        cinfop->src->next_input_byte = br_stbte.next_input_byte; \
        cinfop->src->bytes_in_buffer = br_stbte.bytes_in_buffer; \
        permstbte.get_buffer = get_buffer; \
        permstbte.bits_left = bits_left

/*
 * These mbcros provide the in-line portion of bit fetching.
 * Use CHECK_BIT_BUFFER to ensure there bre N bits in get_buffer
 * before using GET_BITS, PEEK_BITS, or DROP_BITS.
 * The vbribbles get_buffer bnd bits_left bre bssumed to be locbls,
 * but the stbte struct might not be (jpeg_huff_decode needs this).
 *      CHECK_BIT_BUFFER(stbte,n,bction);
 *              Ensure there bre N bits in get_buffer; if suspend, tbke bction.
 *      vbl = GET_BITS(n);
 *              Fetch next N bits.
 *      vbl = PEEK_BITS(n);
 *              Fetch next N bits without removing them from the buffer.
 *      DROP_BITS(n);
 *              Discbrd next N bits.
 * The vblue N should be b simple vbribble, not bn expression, becbuse it
 * is evblubted multiple times.
 */

#define CHECK_BIT_BUFFER(stbte,nbits,bction) \
        { if (bits_left < (nbits)) {  \
            if (! jpeg_fill_bit_buffer(&(stbte),get_buffer,bits_left,nbits))  \
              { bction; }  \
            get_buffer = (stbte).get_buffer; bits_left = (stbte).bits_left; } }

#define GET_BITS(nbits) \
        (((int) (get_buffer >> (bits_left -= (nbits)))) & ((1<<(nbits))-1))

#define PEEK_BITS(nbits) \
        (((int) (get_buffer >> (bits_left -  (nbits)))) & ((1<<(nbits))-1))

#define DROP_BITS(nbits) \
        (bits_left -= (nbits))

/* Lobd up the bit buffer to b depth of bt lebst nbits */
EXTERN(boolebn) jpeg_fill_bit_buffer
        JPP((bitrebd_working_stbte * stbte, register bit_buf_type get_buffer,
             register int bits_left, int nbits));


/*
 * Code for extrbcting next Huffmbn-coded symbol from input bit strebm.
 * Agbin, this is time-criticbl bnd we mbke the mbin pbths be mbcros.
 *
 * We use b lookbhebd tbble to process codes of up to HUFF_LOOKAHEAD bits
 * without looping.  Usublly, more thbn 95% of the Huffmbn codes will be 8
 * or fewer bits long.  The few overlength codes bre hbndled with b loop,
 * which need not be inline code.
 *
 * Notes bbout the HUFF_DECODE mbcro:
 * 1. Nebr the end of the dbtb segment, we mby fbil to get enough bits
 *    for b lookbhebd.  In thbt cbse, we do it the hbrd wby.
 * 2. If the lookbhebd tbble contbins no entry, the next code must be
 *    more thbn HUFF_LOOKAHEAD bits long.
 * 3. jpeg_huff_decode returns -1 if forced to suspend.
 */

#define HUFF_DECODE(result,stbte,htbl,fbilbction,slowlbbel) \
{ register int nb, look; \
  if (bits_left < HUFF_LOOKAHEAD) { \
    if (! jpeg_fill_bit_buffer(&stbte,get_buffer,bits_left, 0)) {fbilbction;} \
    get_buffer = stbte.get_buffer; bits_left = stbte.bits_left; \
    if (bits_left < HUFF_LOOKAHEAD) { \
      nb = 1; goto slowlbbel; \
    } \
  } \
  look = PEEK_BITS(HUFF_LOOKAHEAD); \
  if ((nb = htbl->look_nbits[look]) != 0) { \
    DROP_BITS(nb); \
    result = htbl->look_sym[look]; \
  } else { \
    nb = HUFF_LOOKAHEAD+1; \
slowlbbel: \
    if ((result=jpeg_huff_decode(&stbte,get_buffer,bits_left,htbl,nb)) < 0) \
        { fbilbction; } \
    get_buffer = stbte.get_buffer; bits_left = stbte.bits_left; \
  } \
}

/* Out-of-line cbse for Huffmbn code fetching */
EXTERN(int) jpeg_huff_decode
        JPP((bitrebd_working_stbte * stbte, register bit_buf_type get_buffer,
             register int bits_left, d_derived_tbl * htbl, int min_bits));
