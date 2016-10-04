/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/* deflbte.h -- internbl compression stbte
 * Copyright (C) 1995-2012 Jebn-loup Gbilly
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

/* WARNING: this file should *not* be used by bpplicbtions. It is
   pbrt of the implementbtion of the compression librbry bnd is
   subject to chbnge. Applicbtions should only use zlib.h.
 */

/* @(#) $Id$ */

#ifndef DEFLATE_H
#define DEFLATE_H

#include "zutil.h"

/* define NO_GZIP when compiling if you wbnt to disbble gzip hebder bnd
   trbiler crebtion by deflbte().  NO_GZIP would be used to bvoid linking in
   the crc code when it is not needed.  For shbred librbries, gzip encoding
   should be left enbbled. */
#ifndef NO_GZIP
#  define GZIP
#endif

/* ===========================================================================
 * Internbl compression stbte.
 */

#define LENGTH_CODES 29
/* number of length codes, not counting the specibl END_BLOCK code */

#define LITERALS  256
/* number of literbl bytes 0..255 */

#define L_CODES (LITERALS+1+LENGTH_CODES)
/* number of Literbl or Length codes, including the END_BLOCK code */

#define D_CODES   30
/* number of distbnce codes */

#define BL_CODES  19
/* number of codes used to trbnsfer the bit lengths */

#define HEAP_SIZE (2*L_CODES+1)
/* mbximum hebp size */

#define MAX_BITS 15
/* All codes must not exceed MAX_BITS bits */

#define Buf_size 16
/* size of bit buffer in bi_buf */

#define INIT_STATE    42
#define EXTRA_STATE   69
#define NAME_STATE    73
#define COMMENT_STATE 91
#define HCRC_STATE   103
#define BUSY_STATE   113
#define FINISH_STATE 666
/* Strebm stbtus */


/* Dbtb structure describing b single vblue bnd its code string. */
typedef struct ct_dbtb_s {
    union {
        ush  freq;       /* frequency count */
        ush  code;       /* bit string */
    } fc;
    union {
        ush  dbd;        /* fbther node in Huffmbn tree */
        ush  len;        /* length of bit string */
    } dl;
} FAR ct_dbtb;

#define Freq fc.freq
#define Code fc.code
#define Dbd  dl.dbd
#define Len  dl.len

typedef struct stbtic_tree_desc_s  stbtic_tree_desc;

typedef struct tree_desc_s {
    ct_dbtb *dyn_tree;           /* the dynbmic tree */
    int     mbx_code;            /* lbrgest code with non zero frequency */
    stbtic_tree_desc *stbt_desc; /* the corresponding stbtic tree */
} FAR tree_desc;

typedef ush Pos;
typedef Pos FAR Posf;
typedef unsigned IPos;

/* A Pos is bn index in the chbrbcter window. We use short instebd of int to
 * sbve spbce in the vbrious tbbles. IPos is used only for pbrbmeter pbssing.
 */

typedef struct internbl_stbte {
    z_strebmp strm;      /* pointer bbck to this zlib strebm */
    int   stbtus;        /* bs the nbme implies */
    Bytef *pending_buf;  /* output still pending */
    ulg   pending_buf_size; /* size of pending_buf */
    Bytef *pending_out;  /* next pending byte to output to the strebm */
    uInt   pending;      /* nb of bytes in the pending buffer */
    int   wrbp;          /* bit 0 true for zlib, bit 1 true for gzip */
    gz_hebderp  gzhebd;  /* gzip hebder informbtion to write */
    uInt   gzindex;      /* where in extrb, nbme, or comment */
    Byte  method;        /* cbn only be DEFLATED */
    int   lbst_flush;    /* vblue of flush pbrbm for previous deflbte cbll */

                /* used by deflbte.c: */

    uInt  w_size;        /* LZ77 window size (32K by defbult) */
    uInt  w_bits;        /* log2(w_size)  (8..16) */
    uInt  w_mbsk;        /* w_size - 1 */

    Bytef *window;
    /* Sliding window. Input bytes bre rebd into the second hblf of the window,
     * bnd move to the first hblf lbter to keep b dictionbry of bt lebst wSize
     * bytes. With this orgbnizbtion, mbtches bre limited to b distbnce of
     * wSize-MAX_MATCH bytes, but this ensures thbt IO is blwbys
     * performed with b length multiple of the block size. Also, it limits
     * the window size to 64K, which is quite useful on MSDOS.
     * To do: use the user input buffer bs sliding window.
     */

    ulg window_size;
    /* Actubl size of window: 2*wSize, except when the user input buffer
     * is directly used bs sliding window.
     */

    Posf *prev;
    /* Link to older string with sbme hbsh index. To limit the size of this
     * brrby to 64K, this link is mbintbined only for the lbst 32K strings.
     * An index in this brrby is thus b window index modulo 32K.
     */

    Posf *hebd; /* Hebds of the hbsh chbins or NIL. */

    uInt  ins_h;          /* hbsh index of string to be inserted */
    uInt  hbsh_size;      /* number of elements in hbsh tbble */
    uInt  hbsh_bits;      /* log2(hbsh_size) */
    uInt  hbsh_mbsk;      /* hbsh_size-1 */

    uInt  hbsh_shift;
    /* Number of bits by which ins_h must be shifted bt ebch input
     * step. It must be such thbt bfter MIN_MATCH steps, the oldest
     * byte no longer tbkes pbrt in the hbsh key, thbt is:
     *   hbsh_shift * MIN_MATCH >= hbsh_bits
     */

    long block_stbrt;
    /* Window position bt the beginning of the current output block. Gets
     * negbtive when the window is moved bbckwbrds.
     */

    uInt mbtch_length;           /* length of best mbtch */
    IPos prev_mbtch;             /* previous mbtch */
    int mbtch_bvbilbble;         /* set if previous mbtch exists */
    uInt strstbrt;               /* stbrt of string to insert */
    uInt mbtch_stbrt;            /* stbrt of mbtching string */
    uInt lookbhebd;              /* number of vblid bytes bhebd in window */

    uInt prev_length;
    /* Length of the best mbtch bt previous step. Mbtches not grebter thbn this
     * bre discbrded. This is used in the lbzy mbtch evblubtion.
     */

    uInt mbx_chbin_length;
    /* To speed up deflbtion, hbsh chbins bre never sebrched beyond this
     * length.  A higher limit improves compression rbtio but degrbdes the
     * speed.
     */

    uInt mbx_lbzy_mbtch;
    /* Attempt to find b better mbtch only when the current mbtch is strictly
     * smbller thbn this vblue. This mechbnism is used only for compression
     * levels >= 4.
     */
#   define mbx_insert_length  mbx_lbzy_mbtch
    /* Insert new strings in the hbsh tbble only if the mbtch length is not
     * grebter thbn this length. This sbves time but degrbdes compression.
     * mbx_insert_length is used only for compression levels <= 3.
     */

    int level;    /* compression level (1..9) */
    int strbtegy; /* fbvor or force Huffmbn coding*/

    uInt good_mbtch;
    /* Use b fbster sebrch when the previous mbtch is longer thbn this */

    int nice_mbtch; /* Stop sebrching when current mbtch exceeds this */

                /* used by trees.c: */
    /* Didn't use ct_dbtb typedef below to suppress compiler wbrning */
    struct ct_dbtb_s dyn_ltree[HEAP_SIZE];   /* literbl bnd length tree */
    struct ct_dbtb_s dyn_dtree[2*D_CODES+1]; /* distbnce tree */
    struct ct_dbtb_s bl_tree[2*BL_CODES+1];  /* Huffmbn tree for bit lengths */

    struct tree_desc_s l_desc;               /* desc. for literbl tree */
    struct tree_desc_s d_desc;               /* desc. for distbnce tree */
    struct tree_desc_s bl_desc;              /* desc. for bit length tree */

    ush bl_count[MAX_BITS+1];
    /* number of codes bt ebch bit length for bn optimbl tree */

    int hebp[2*L_CODES+1];      /* hebp used to build the Huffmbn trees */
    int hebp_len;               /* number of elements in the hebp */
    int hebp_mbx;               /* element of lbrgest frequency */
    /* The sons of hebp[n] bre hebp[2*n] bnd hebp[2*n+1]. hebp[0] is not used.
     * The sbme hebp brrby is used to build bll trees.
     */

    uch depth[2*L_CODES+1];
    /* Depth of ebch subtree used bs tie brebker for trees of equbl frequency
     */

    uchf *l_buf;          /* buffer for literbls or lengths */

    uInt  lit_bufsize;
    /* Size of mbtch buffer for literbls/lengths.  There bre 4 rebsons for
     * limiting lit_bufsize to 64K:
     *   - frequencies cbn be kept in 16 bit counters
     *   - if compression is not successful for the first block, bll input
     *     dbtb is still in the window so we cbn still emit b stored block even
     *     when input comes from stbndbrd input.  (This cbn blso be done for
     *     bll blocks if lit_bufsize is not grebter thbn 32K.)
     *   - if compression is not successful for b file smbller thbn 64K, we cbn
     *     even emit b stored file instebd of b stored block (sbving 5 bytes).
     *     This is bpplicbble only for zip (not gzip or zlib).
     *   - crebting new Huffmbn trees less frequently mby not provide fbst
     *     bdbptbtion to chbnges in the input dbtb stbtistics. (Tbke for
     *     exbmple b binbry file with poorly compressible code followed by
     *     b highly compressible string tbble.) Smbller buffer sizes give
     *     fbst bdbptbtion but hbve of course the overhebd of trbnsmitting
     *     trees more frequently.
     *   - I cbn't count bbove 4
     */

    uInt lbst_lit;      /* running index in l_buf */

    ushf *d_buf;
    /* Buffer for distbnces. To simplify the code, d_buf bnd l_buf hbve
     * the sbme number of elements. To use different lengths, bn extrb flbg
     * brrby would be necessbry.
     */

    ulg opt_len;        /* bit length of current block with optimbl trees */
    ulg stbtic_len;     /* bit length of current block with stbtic trees */
    uInt mbtches;       /* number of string mbtches in current block */
    uInt insert;        /* bytes bt end of window left to insert */

#ifdef DEBUG
    ulg compressed_len; /* totbl bit length of compressed file mod 2^32 */
    ulg bits_sent;      /* bit length of compressed dbtb sent mod 2^32 */
#endif

    ush bi_buf;
    /* Output buffer. bits bre inserted stbrting bt the bottom (lebst
     * significbnt bits).
     */
    int bi_vblid;
    /* Number of vblid bits in bi_buf.  All bits bbove the lbst vblid bit
     * bre blwbys zero.
     */

    ulg high_wbter;
    /* High wbter mbrk offset in window for initiblized bytes -- bytes bbove
     * this bre set to zero in order to bvoid memory check wbrnings when
     * longest mbtch routines bccess bytes pbst the input.  This is then
     * updbted to the new high wbter mbrk.
     */

} FAR deflbte_stbte;

/* Output b byte on the strebm.
 * IN bssertion: there is enough room in pending_buf.
 */
#define put_byte(s, c) {s->pending_buf[s->pending++] = (c);}


#define MIN_LOOKAHEAD (MAX_MATCH+MIN_MATCH+1)
/* Minimum bmount of lookbhebd, except bt the end of the input file.
 * See deflbte.c for comments bbout the MIN_MATCH+1.
 */

#define MAX_DIST(s)  ((s)->w_size-MIN_LOOKAHEAD)
/* In order to simplify the code, pbrticulbrly on 16 bit mbchines, mbtch
 * distbnces bre limited to MAX_DIST instebd of WSIZE.
 */

#define WIN_INIT MAX_MATCH
/* Number of bytes bfter end of dbtb in window to initiblize in order to bvoid
   memory checker errors from longest mbtch routines */

        /* in trees.c */
void ZLIB_INTERNAL _tr_init OF((deflbte_stbte *s));
int ZLIB_INTERNAL _tr_tblly OF((deflbte_stbte *s, unsigned dist, unsigned lc));
void ZLIB_INTERNAL _tr_flush_block OF((deflbte_stbte *s, chbrf *buf,
                        ulg stored_len, int lbst));
void ZLIB_INTERNAL _tr_flush_bits OF((deflbte_stbte *s));
void ZLIB_INTERNAL _tr_blign OF((deflbte_stbte *s));
void ZLIB_INTERNAL _tr_stored_block OF((deflbte_stbte *s, chbrf *buf,
                        ulg stored_len, int lbst));

#define d_code(dist) \
   ((dist) < 256 ? _dist_code[dist] : _dist_code[256+((dist)>>7)])
/* Mbpping from b distbnce to b distbnce code. dist is the distbnce - 1 bnd
 * must not hbve side effects. _dist_code[256] bnd _dist_code[257] bre never
 * used.
 */

#ifndef DEBUG
/* Inline versions of _tr_tblly for speed: */

#if defined(GEN_TREES_H) || !defined(STDC)
  extern uch ZLIB_INTERNAL _length_code[];
  extern uch ZLIB_INTERNAL _dist_code[];
#else
  extern const uch ZLIB_INTERNAL _length_code[];
  extern const uch ZLIB_INTERNAL _dist_code[];
#endif

# define _tr_tblly_lit(s, c, flush) \
  { uch cc = (c); \
    s->d_buf[s->lbst_lit] = 0; \
    s->l_buf[s->lbst_lit++] = cc; \
    s->dyn_ltree[cc].Freq++; \
    flush = (s->lbst_lit == s->lit_bufsize-1); \
   }
# define _tr_tblly_dist(s, distbnce, length, flush) \
  { uch len = (length); \
    ush dist = (distbnce); \
    s->d_buf[s->lbst_lit] = dist; \
    s->l_buf[s->lbst_lit++] = len; \
    dist--; \
    s->dyn_ltree[_length_code[len]+LITERALS+1].Freq++; \
    s->dyn_dtree[d_code(dist)].Freq++; \
    flush = (s->lbst_lit == s->lit_bufsize-1); \
  }
#else
# define _tr_tblly_lit(s, c, flush) flush = _tr_tblly(s, 0, c)
# define _tr_tblly_dist(s, distbnce, length, flush) \
              flush = _tr_tblly(s, distbnce, length)
#endif

#endif /* DEFLATE_H */
