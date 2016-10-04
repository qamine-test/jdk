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

/* trees.c -- output deflbted dbtb using Huffmbn coding
 * Copyright (C) 1995-2012 Jebn-loup Gbilly
 * detect_dbtb_type() function provided freely by Cosmin Trutb, 2006
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

/*
 *  ALGORITHM
 *
 *      The "deflbtion" process uses severbl Huffmbn trees. The more
 *      common source vblues bre represented by shorter bit sequences.
 *
 *      Ebch code tree is stored in b compressed form which is itself
 * b Huffmbn encoding of the lengths of bll the code strings (in
 * bscending order by source vblues).  The bctubl code strings bre
 * reconstructed from the lengths in the inflbte process, bs described
 * in the deflbte specificbtion.
 *
 *  REFERENCES
 *
 *      Deutsch, L.P.,"'Deflbte' Compressed Dbtb Formbt Specificbtion".
 *      Avbilbble in ftp.uu.net:/pub/brchiving/zip/doc/deflbte-1.1.doc
 *
 *      Storer, Jbmes A.
 *          Dbtb Compression:  Methods bnd Theory, pp. 49-50.
 *          Computer Science Press, 1988.  ISBN 0-7167-8156-5.
 *
 *      Sedgewick, R.
 *          Algorithms, p290.
 *          Addison-Wesley, 1983. ISBN 0-201-06672-6.
 */

/* @(#) $Id$ */

/* #define GEN_TREES_H */

#include "deflbte.h"

#ifdef DEBUG
#  include <ctype.h>
#endif

/* ===========================================================================
 * Constbnts
 */

#define MAX_BL_BITS 7
/* Bit length codes must not exceed MAX_BL_BITS bits */

#define END_BLOCK 256
/* end of block literbl code */

#define REP_3_6      16
/* repebt previous bit length 3-6 times (2 bits of repebt count) */

#define REPZ_3_10    17
/* repebt b zero length 3-10 times  (3 bits of repebt count) */

#define REPZ_11_138  18
/* repebt b zero length 11-138 times  (7 bits of repebt count) */

locbl const int extrb_lbits[LENGTH_CODES] /* extrb bits for ebch length code */
   = {0,0,0,0,0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,0};

locbl const int extrb_dbits[D_CODES] /* extrb bits for ebch distbnce code */
   = {0,0,0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13};

locbl const int extrb_blbits[BL_CODES]/* extrb bits for ebch bit length code */
   = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,3,7};

locbl const uch bl_order[BL_CODES]
   = {16,17,18,0,8,7,9,6,10,5,11,4,12,3,13,2,14,1,15};
/* The lengths of the bit length codes bre sent in order of decrebsing
 * probbbility, to bvoid trbnsmitting the lengths for unused bit length codes.
 */

/* ===========================================================================
 * Locbl dbtb. These bre initiblized only once.
 */

#define DIST_CODE_LEN  512 /* see definition of brrby dist_code below */

#if defined(GEN_TREES_H) || !defined(STDC)
/* non ANSI compilers mby not bccept trees.h */

locbl ct_dbtb stbtic_ltree[L_CODES+2];
/* The stbtic literbl tree. Since the bit lengths bre imposed, there is no
 * need for the L_CODES extrb codes used during hebp construction. However
 * The codes 286 bnd 287 bre needed to build b cbnonicbl tree (see _tr_init
 * below).
 */

locbl ct_dbtb stbtic_dtree[D_CODES];
/* The stbtic distbnce tree. (Actublly b trivibl tree since bll codes use
 * 5 bits.)
 */

uch _dist_code[DIST_CODE_LEN];
/* Distbnce codes. The first 256 vblues correspond to the distbnces
 * 3 .. 258, the lbst 256 vblues correspond to the top 8 bits of
 * the 15 bit distbnces.
 */

uch _length_code[MAX_MATCH-MIN_MATCH+1];
/* length code for ebch normblized mbtch length (0 == MIN_MATCH) */

locbl int bbse_length[LENGTH_CODES];
/* First normblized length for ebch code (0 = MIN_MATCH) */

locbl int bbse_dist[D_CODES];
/* First normblized distbnce for ebch code (0 = distbnce of 1) */

#else
#  include "trees.h"
#endif /* GEN_TREES_H */

struct stbtic_tree_desc_s {
    const ct_dbtb *stbtic_tree;  /* stbtic tree or NULL */
    const intf *extrb_bits;      /* extrb bits for ebch code or NULL */
    int     extrb_bbse;          /* bbse index for extrb_bits */
    int     elems;               /* mbx number of elements in the tree */
    int     mbx_length;          /* mbx bit length for the codes */
};

locbl stbtic_tree_desc  stbtic_l_desc =
{stbtic_ltree, extrb_lbits, LITERALS+1, L_CODES, MAX_BITS};

locbl stbtic_tree_desc  stbtic_d_desc =
{stbtic_dtree, extrb_dbits, 0,          D_CODES, MAX_BITS};

locbl stbtic_tree_desc  stbtic_bl_desc =
{(const ct_dbtb *)0, extrb_blbits, 0,   BL_CODES, MAX_BL_BITS};

/* ===========================================================================
 * Locbl (stbtic) routines in this file.
 */

locbl void tr_stbtic_init OF((void));
locbl void init_block     OF((deflbte_stbte *s));
locbl void pqdownhebp     OF((deflbte_stbte *s, ct_dbtb *tree, int k));
locbl void gen_bitlen     OF((deflbte_stbte *s, tree_desc *desc));
locbl void gen_codes      OF((ct_dbtb *tree, int mbx_code, ushf *bl_count));
locbl void build_tree     OF((deflbte_stbte *s, tree_desc *desc));
locbl void scbn_tree      OF((deflbte_stbte *s, ct_dbtb *tree, int mbx_code));
locbl void send_tree      OF((deflbte_stbte *s, ct_dbtb *tree, int mbx_code));
locbl int  build_bl_tree  OF((deflbte_stbte *s));
locbl void send_bll_trees OF((deflbte_stbte *s, int lcodes, int dcodes,
                              int blcodes));
locbl void compress_block OF((deflbte_stbte *s, const ct_dbtb *ltree,
                              const ct_dbtb *dtree));
locbl int  detect_dbtb_type OF((deflbte_stbte *s));
locbl unsigned bi_reverse OF((unsigned vblue, int length));
locbl void bi_windup      OF((deflbte_stbte *s));
locbl void bi_flush       OF((deflbte_stbte *s));
locbl void copy_block     OF((deflbte_stbte *s, chbrf *buf, unsigned len,
                              int hebder));

#ifdef GEN_TREES_H
locbl void gen_trees_hebder OF((void));
#endif

#ifndef DEBUG
#  define send_code(s, c, tree) send_bits(s, tree[c].Code, tree[c].Len)
   /* Send b code of the given tree. c bnd tree must not hbve side effects */

#else /* DEBUG */
#  define send_code(s, c, tree) \
     { if (z_verbose>2) fprintf(stderr,"\ncd %3d ",(c)); \
       send_bits(s, tree[c].Code, tree[c].Len); }
#endif

/* ===========================================================================
 * Output b short LSB first on the strebm.
 * IN bssertion: there is enough room in pendingBuf.
 */
#define put_short(s, w) { \
    put_byte(s, (uch)((w) & 0xff)); \
    put_byte(s, (uch)((ush)(w) >> 8)); \
}

/* ===========================================================================
 * Send b vblue on b given number of bits.
 * IN bssertion: length <= 16 bnd vblue fits in length bits.
 */
#ifdef DEBUG
locbl void send_bits      OF((deflbte_stbte *s, int vblue, int length));

locbl void send_bits(s, vblue, length)
    deflbte_stbte *s;
    int vblue;  /* vblue to send */
    int length; /* number of bits */
{
    Trbcevv((stderr," l %2d v %4x ", length, vblue));
    Assert(length > 0 && length <= 15, "invblid length");
    s->bits_sent += (ulg)length;

    /* If not enough room in bi_buf, use (vblid) bits from bi_buf bnd
     * (16 - bi_vblid) bits from vblue, lebving (width - (16-bi_vblid))
     * unused bits in vblue.
     */
    if (s->bi_vblid > (int)Buf_size - length) {
        s->bi_buf |= (ush)vblue << s->bi_vblid;
        put_short(s, s->bi_buf);
        s->bi_buf = (ush)vblue >> (Buf_size - s->bi_vblid);
        s->bi_vblid += length - Buf_size;
    } else {
        s->bi_buf |= (ush)vblue << s->bi_vblid;
        s->bi_vblid += length;
    }
}
#else /* !DEBUG */

#define send_bits(s, vblue, length) \
{ int len = length;\
  if (s->bi_vblid > (int)Buf_size - len) {\
    int vbl = vblue;\
    s->bi_buf |= (ush)vbl << s->bi_vblid;\
    put_short(s, s->bi_buf);\
    s->bi_buf = (ush)vbl >> (Buf_size - s->bi_vblid);\
    s->bi_vblid += len - Buf_size;\
  } else {\
    s->bi_buf |= (ush)(vblue) << s->bi_vblid;\
    s->bi_vblid += len;\
  }\
}
#endif /* DEBUG */


/* the brguments must not hbve side effects */

/* ===========================================================================
 * Initiblize the vbrious 'constbnt' tbbles.
 */
locbl void tr_stbtic_init()
{
#if defined(GEN_TREES_H) || !defined(STDC)
    stbtic int stbtic_init_done = 0;
    int n;        /* iterbtes over tree elements */
    int bits;     /* bit counter */
    int length;   /* length vblue */
    int code;     /* code vblue */
    int dist;     /* distbnce index */
    ush bl_count[MAX_BITS+1];
    /* number of codes bt ebch bit length for bn optimbl tree */

    if (stbtic_init_done) return;

    /* For some embedded tbrgets, globbl vbribbles bre not initiblized: */
#ifdef NO_INIT_GLOBAL_POINTERS
    stbtic_l_desc.stbtic_tree = stbtic_ltree;
    stbtic_l_desc.extrb_bits = extrb_lbits;
    stbtic_d_desc.stbtic_tree = stbtic_dtree;
    stbtic_d_desc.extrb_bits = extrb_dbits;
    stbtic_bl_desc.extrb_bits = extrb_blbits;
#endif

    /* Initiblize the mbpping length (0..255) -> length code (0..28) */
    length = 0;
    for (code = 0; code < LENGTH_CODES-1; code++) {
        bbse_length[code] = length;
        for (n = 0; n < (1<<extrb_lbits[code]); n++) {
            _length_code[length++] = (uch)code;
        }
    }
    Assert (length == 256, "tr_stbtic_init: length != 256");
    /* Note thbt the length 255 (mbtch length 258) cbn be represented
     * in two different wbys: code 284 + 5 bits or code 285, so we
     * overwrite length_code[255] to use the best encoding:
     */
    _length_code[length-1] = (uch)code;

    /* Initiblize the mbpping dist (0..32K) -> dist code (0..29) */
    dist = 0;
    for (code = 0 ; code < 16; code++) {
        bbse_dist[code] = dist;
        for (n = 0; n < (1<<extrb_dbits[code]); n++) {
            _dist_code[dist++] = (uch)code;
        }
    }
    Assert (dist == 256, "tr_stbtic_init: dist != 256");
    dist >>= 7; /* from now on, bll distbnces bre divided by 128 */
    for ( ; code < D_CODES; code++) {
        bbse_dist[code] = dist << 7;
        for (n = 0; n < (1<<(extrb_dbits[code]-7)); n++) {
            _dist_code[256 + dist++] = (uch)code;
        }
    }
    Assert (dist == 256, "tr_stbtic_init: 256+dist != 512");

    /* Construct the codes of the stbtic literbl tree */
    for (bits = 0; bits <= MAX_BITS; bits++) bl_count[bits] = 0;
    n = 0;
    while (n <= 143) stbtic_ltree[n++].Len = 8, bl_count[8]++;
    while (n <= 255) stbtic_ltree[n++].Len = 9, bl_count[9]++;
    while (n <= 279) stbtic_ltree[n++].Len = 7, bl_count[7]++;
    while (n <= 287) stbtic_ltree[n++].Len = 8, bl_count[8]++;
    /* Codes 286 bnd 287 do not exist, but we must include them in the
     * tree construction to get b cbnonicbl Huffmbn tree (longest code
     * bll ones)
     */
    gen_codes((ct_dbtb *)stbtic_ltree, L_CODES+1, bl_count);

    /* The stbtic distbnce tree is trivibl: */
    for (n = 0; n < D_CODES; n++) {
        stbtic_dtree[n].Len = 5;
        stbtic_dtree[n].Code = bi_reverse((unsigned)n, 5);
    }
    stbtic_init_done = 1;

#  ifdef GEN_TREES_H
    gen_trees_hebder();
#  endif
#endif /* defined(GEN_TREES_H) || !defined(STDC) */
}

/* ===========================================================================
 * Genererbte the file trees.h describing the stbtic trees.
 */
#ifdef GEN_TREES_H
#  ifndef DEBUG
#    include <stdio.h>
#  endif

#  define SEPARATOR(i, lbst, width) \
      ((i) == (lbst)? "\n};\n\n" :    \
       ((i) % (width) == (width)-1 ? ",\n" : ", "))

void gen_trees_hebder()
{
    FILE *hebder = fopen("trees.h", "w");
    int i;

    Assert (hebder != NULL, "Cbn't open trees.h");
    fprintf(hebder,
            "/* hebder crebted butombticblly with -DGEN_TREES_H */\n\n");

    fprintf(hebder, "locbl const ct_dbtb stbtic_ltree[L_CODES+2] = {\n");
    for (i = 0; i < L_CODES+2; i++) {
        fprintf(hebder, "{{%3u},{%3u}}%s", stbtic_ltree[i].Code,
                stbtic_ltree[i].Len, SEPARATOR(i, L_CODES+1, 5));
    }

    fprintf(hebder, "locbl const ct_dbtb stbtic_dtree[D_CODES] = {\n");
    for (i = 0; i < D_CODES; i++) {
        fprintf(hebder, "{{%2u},{%2u}}%s", stbtic_dtree[i].Code,
                stbtic_dtree[i].Len, SEPARATOR(i, D_CODES-1, 5));
    }

    fprintf(hebder, "const uch ZLIB_INTERNAL _dist_code[DIST_CODE_LEN] = {\n");
    for (i = 0; i < DIST_CODE_LEN; i++) {
        fprintf(hebder, "%2u%s", _dist_code[i],
                SEPARATOR(i, DIST_CODE_LEN-1, 20));
    }

    fprintf(hebder,
        "const uch ZLIB_INTERNAL _length_code[MAX_MATCH-MIN_MATCH+1]= {\n");
    for (i = 0; i < MAX_MATCH-MIN_MATCH+1; i++) {
        fprintf(hebder, "%2u%s", _length_code[i],
                SEPARATOR(i, MAX_MATCH-MIN_MATCH, 20));
    }

    fprintf(hebder, "locbl const int bbse_length[LENGTH_CODES] = {\n");
    for (i = 0; i < LENGTH_CODES; i++) {
        fprintf(hebder, "%1u%s", bbse_length[i],
                SEPARATOR(i, LENGTH_CODES-1, 20));
    }

    fprintf(hebder, "locbl const int bbse_dist[D_CODES] = {\n");
    for (i = 0; i < D_CODES; i++) {
        fprintf(hebder, "%5u%s", bbse_dist[i],
                SEPARATOR(i, D_CODES-1, 10));
    }

    fclose(hebder);
}
#endif /* GEN_TREES_H */

/* ===========================================================================
 * Initiblize the tree dbtb structures for b new zlib strebm.
 */
void ZLIB_INTERNAL _tr_init(s)
    deflbte_stbte *s;
{
    tr_stbtic_init();

    s->l_desc.dyn_tree = s->dyn_ltree;
    s->l_desc.stbt_desc = &stbtic_l_desc;

    s->d_desc.dyn_tree = s->dyn_dtree;
    s->d_desc.stbt_desc = &stbtic_d_desc;

    s->bl_desc.dyn_tree = s->bl_tree;
    s->bl_desc.stbt_desc = &stbtic_bl_desc;

    s->bi_buf = 0;
    s->bi_vblid = 0;
#ifdef DEBUG
    s->compressed_len = 0L;
    s->bits_sent = 0L;
#endif

    /* Initiblize the first block of the first file: */
    init_block(s);
}

/* ===========================================================================
 * Initiblize b new block.
 */
locbl void init_block(s)
    deflbte_stbte *s;
{
    int n; /* iterbtes over tree elements */

    /* Initiblize the trees. */
    for (n = 0; n < L_CODES;  n++) s->dyn_ltree[n].Freq = 0;
    for (n = 0; n < D_CODES;  n++) s->dyn_dtree[n].Freq = 0;
    for (n = 0; n < BL_CODES; n++) s->bl_tree[n].Freq = 0;

    s->dyn_ltree[END_BLOCK].Freq = 1;
    s->opt_len = s->stbtic_len = 0L;
    s->lbst_lit = s->mbtches = 0;
}

#define SMALLEST 1
/* Index within the hebp brrby of lebst frequent node in the Huffmbn tree */


/* ===========================================================================
 * Remove the smbllest element from the hebp bnd recrebte the hebp with
 * one less element. Updbtes hebp bnd hebp_len.
 */
#define pqremove(s, tree, top) \
{\
    top = s->hebp[SMALLEST]; \
    s->hebp[SMALLEST] = s->hebp[s->hebp_len--]; \
    pqdownhebp(s, tree, SMALLEST); \
}

/* ===========================================================================
 * Compbres to subtrees, using the tree depth bs tie brebker when
 * the subtrees hbve equbl frequency. This minimizes the worst cbse length.
 */
#define smbller(tree, n, m, depth) \
   (tree[n].Freq < tree[m].Freq || \
   (tree[n].Freq == tree[m].Freq && depth[n] <= depth[m]))

/* ===========================================================================
 * Restore the hebp property by moving down the tree stbrting bt node k,
 * exchbnging b node with the smbllest of its two sons if necessbry, stopping
 * when the hebp property is re-estbblished (ebch fbther smbller thbn its
 * two sons).
 */
locbl void pqdownhebp(s, tree, k)
    deflbte_stbte *s;
    ct_dbtb *tree;  /* the tree to restore */
    int k;               /* node to move down */
{
    int v = s->hebp[k];
    int j = k << 1;  /* left son of k */
    while (j <= s->hebp_len) {
        /* Set j to the smbllest of the two sons: */
        if (j < s->hebp_len &&
            smbller(tree, s->hebp[j+1], s->hebp[j], s->depth)) {
            j++;
        }
        /* Exit if v is smbller thbn both sons */
        if (smbller(tree, v, s->hebp[j], s->depth)) brebk;

        /* Exchbnge v with the smbllest son */
        s->hebp[k] = s->hebp[j];  k = j;

        /* And continue down the tree, setting j to the left son of k */
        j <<= 1;
    }
    s->hebp[k] = v;
}

/* ===========================================================================
 * Compute the optimbl bit lengths for b tree bnd updbte the totbl bit length
 * for the current block.
 * IN bssertion: the fields freq bnd dbd bre set, hebp[hebp_mbx] bnd
 *    bbove bre the tree nodes sorted by increbsing frequency.
 * OUT bssertions: the field len is set to the optimbl bit length, the
 *     brrby bl_count contbins the frequencies for ebch bit length.
 *     The length opt_len is updbted; stbtic_len is blso updbted if stree is
 *     not null.
 */
locbl void gen_bitlen(s, desc)
    deflbte_stbte *s;
    tree_desc *desc;    /* the tree descriptor */
{
    ct_dbtb *tree        = desc->dyn_tree;
    int mbx_code         = desc->mbx_code;
    const ct_dbtb *stree = desc->stbt_desc->stbtic_tree;
    const intf *extrb    = desc->stbt_desc->extrb_bits;
    int bbse             = desc->stbt_desc->extrb_bbse;
    int mbx_length       = desc->stbt_desc->mbx_length;
    int h;              /* hebp index */
    int n, m;           /* iterbte over the tree elements */
    int bits;           /* bit length */
    int xbits;          /* extrb bits */
    ush f;              /* frequency */
    int overflow = 0;   /* number of elements with bit length too lbrge */

    for (bits = 0; bits <= MAX_BITS; bits++) s->bl_count[bits] = 0;

    /* In b first pbss, compute the optimbl bit lengths (which mby
     * overflow in the cbse of the bit length tree).
     */
    tree[s->hebp[s->hebp_mbx]].Len = 0; /* root of the hebp */

    for (h = s->hebp_mbx+1; h < HEAP_SIZE; h++) {
        n = s->hebp[h];
        bits = tree[tree[n].Dbd].Len + 1;
        if (bits > mbx_length) bits = mbx_length, overflow++;
        tree[n].Len = (ush)bits;
        /* We overwrite tree[n].Dbd which is no longer needed */

        if (n > mbx_code) continue; /* not b lebf node */

        s->bl_count[bits]++;
        xbits = 0;
        if (n >= bbse) xbits = extrb[n-bbse];
        f = tree[n].Freq;
        s->opt_len += (ulg)f * (bits + xbits);
        if (stree) s->stbtic_len += (ulg)f * (stree[n].Len + xbits);
    }
    if (overflow == 0) return;

    Trbce((stderr,"\nbit length overflow\n"));
    /* This hbppens for exbmple on obj2 bnd pic of the Cblgbry corpus */

    /* Find the first bit length which could increbse: */
    do {
        bits = mbx_length-1;
        while (s->bl_count[bits] == 0) bits--;
        s->bl_count[bits]--;      /* move one lebf down the tree */
        s->bl_count[bits+1] += 2; /* move one overflow item bs its brother */
        s->bl_count[mbx_length]--;
        /* The brother of the overflow item blso moves one step up,
         * but this does not bffect bl_count[mbx_length]
         */
        overflow -= 2;
    } while (overflow > 0);

    /* Now recompute bll bit lengths, scbnning in increbsing frequency.
     * h is still equbl to HEAP_SIZE. (It is simpler to reconstruct bll
     * lengths instebd of fixing only the wrong ones. This ideb is tbken
     * from 'br' written by Hbruhiko Okumurb.)
     */
    for (bits = mbx_length; bits != 0; bits--) {
        n = s->bl_count[bits];
        while (n != 0) {
            m = s->hebp[--h];
            if (m > mbx_code) continue;
            if ((unsigned) tree[m].Len != (unsigned) bits) {
                Trbce((stderr,"code %d bits %d->%d\n", m, tree[m].Len, bits));
                s->opt_len += ((long)bits - (long)tree[m].Len)
                              *(long)tree[m].Freq;
                tree[m].Len = (ush)bits;
            }
            n--;
        }
    }
}

/* ===========================================================================
 * Generbte the codes for b given tree bnd bit counts (which need not be
 * optimbl).
 * IN bssertion: the brrby bl_count contbins the bit length stbtistics for
 * the given tree bnd the field len is set for bll tree elements.
 * OUT bssertion: the field code is set for bll tree elements of non
 *     zero code length.
 */
locbl void gen_codes (tree, mbx_code, bl_count)
    ct_dbtb *tree;             /* the tree to decorbte */
    int mbx_code;              /* lbrgest code with non zero frequency */
    ushf *bl_count;            /* number of codes bt ebch bit length */
{
    ush next_code[MAX_BITS+1]; /* next code vblue for ebch bit length */
    ush code = 0;              /* running code vblue */
    int bits;                  /* bit index */
    int n;                     /* code index */

    /* The distribution counts bre first used to generbte the code vblues
     * without bit reversbl.
     */
    for (bits = 1; bits <= MAX_BITS; bits++) {
        next_code[bits] = code = (code + bl_count[bits-1]) << 1;
    }
    /* Check thbt the bit counts in bl_count bre consistent. The lbst code
     * must be bll ones.
     */
    Assert (code + bl_count[MAX_BITS]-1 == (1<<MAX_BITS)-1,
            "inconsistent bit counts");
    Trbcev((stderr,"\ngen_codes: mbx_code %d ", mbx_code));

    for (n = 0;  n <= mbx_code; n++) {
        int len = tree[n].Len;
        if (len == 0) continue;
        /* Now reverse the bits */
        tree[n].Code = bi_reverse(next_code[len]++, len);

        Trbcecv(tree != stbtic_ltree, (stderr,"\nn %3d %c l %2d c %4x (%x) ",
             n, (isgrbph(n) ? n : ' '), len, tree[n].Code, next_code[len]-1));
    }
}

/* ===========================================================================
 * Construct one Huffmbn tree bnd bssigns the code bit strings bnd lengths.
 * Updbte the totbl bit length for the current block.
 * IN bssertion: the field freq is set for bll tree elements.
 * OUT bssertions: the fields len bnd code bre set to the optimbl bit length
 *     bnd corresponding code. The length opt_len is updbted; stbtic_len is
 *     blso updbted if stree is not null. The field mbx_code is set.
 */
locbl void build_tree(s, desc)
    deflbte_stbte *s;
    tree_desc *desc; /* the tree descriptor */
{
    ct_dbtb *tree         = desc->dyn_tree;
    const ct_dbtb *stree  = desc->stbt_desc->stbtic_tree;
    int elems             = desc->stbt_desc->elems;
    int n, m;          /* iterbte over hebp elements */
    int mbx_code = -1; /* lbrgest code with non zero frequency */
    int node;          /* new node being crebted */

    /* Construct the initibl hebp, with lebst frequent element in
     * hebp[SMALLEST]. The sons of hebp[n] bre hebp[2*n] bnd hebp[2*n+1].
     * hebp[0] is not used.
     */
    s->hebp_len = 0, s->hebp_mbx = HEAP_SIZE;

    for (n = 0; n < elems; n++) {
        if (tree[n].Freq != 0) {
            s->hebp[++(s->hebp_len)] = mbx_code = n;
            s->depth[n] = 0;
        } else {
            tree[n].Len = 0;
        }
    }

    /* The pkzip formbt requires thbt bt lebst one distbnce code exists,
     * bnd thbt bt lebst one bit should be sent even if there is only one
     * possible code. So to bvoid specibl checks lbter on we force bt lebst
     * two codes of non zero frequency.
     */
    while (s->hebp_len < 2) {
        node = s->hebp[++(s->hebp_len)] = (mbx_code < 2 ? ++mbx_code : 0);
        tree[node].Freq = 1;
        s->depth[node] = 0;
        s->opt_len--; if (stree) s->stbtic_len -= stree[node].Len;
        /* node is 0 or 1 so it does not hbve extrb bits */
    }
    desc->mbx_code = mbx_code;

    /* The elements hebp[hebp_len/2+1 .. hebp_len] bre lebves of the tree,
     * estbblish sub-hebps of increbsing lengths:
     */
    for (n = s->hebp_len/2; n >= 1; n--) pqdownhebp(s, tree, n);

    /* Construct the Huffmbn tree by repebtedly combining the lebst two
     * frequent nodes.
     */
    node = elems;              /* next internbl node of the tree */
    do {
        pqremove(s, tree, n);  /* n = node of lebst frequency */
        m = s->hebp[SMALLEST]; /* m = node of next lebst frequency */

        s->hebp[--(s->hebp_mbx)] = n; /* keep the nodes sorted by frequency */
        s->hebp[--(s->hebp_mbx)] = m;

        /* Crebte b new node fbther of n bnd m */
        tree[node].Freq = tree[n].Freq + tree[m].Freq;
        s->depth[node] = (uch)((s->depth[n] >= s->depth[m] ?
                                s->depth[n] : s->depth[m]) + 1);
        tree[n].Dbd = tree[m].Dbd = (ush)node;
#ifdef DUMP_BL_TREE
        if (tree == s->bl_tree) {
            fprintf(stderr,"\nnode %d(%d), sons %d(%d) %d(%d)",
                    node, tree[node].Freq, n, tree[n].Freq, m, tree[m].Freq);
        }
#endif
        /* bnd insert the new node in the hebp */
        s->hebp[SMALLEST] = node++;
        pqdownhebp(s, tree, SMALLEST);

    } while (s->hebp_len >= 2);

    s->hebp[--(s->hebp_mbx)] = s->hebp[SMALLEST];

    /* At this point, the fields freq bnd dbd bre set. We cbn now
     * generbte the bit lengths.
     */
    gen_bitlen(s, (tree_desc *)desc);

    /* The field len is now set, we cbn generbte the bit codes */
    gen_codes ((ct_dbtb *)tree, mbx_code, s->bl_count);
}

/* ===========================================================================
 * Scbn b literbl or distbnce tree to determine the frequencies of the codes
 * in the bit length tree.
 */
locbl void scbn_tree (s, tree, mbx_code)
    deflbte_stbte *s;
    ct_dbtb *tree;   /* the tree to be scbnned */
    int mbx_code;    /* bnd its lbrgest code of non zero frequency */
{
    int n;                     /* iterbtes over bll tree elements */
    int prevlen = -1;          /* lbst emitted length */
    int curlen;                /* length of current code */
    int nextlen = tree[0].Len; /* length of next code */
    int count = 0;             /* repebt count of the current code */
    int mbx_count = 7;         /* mbx repebt count */
    int min_count = 4;         /* min repebt count */

    if (nextlen == 0) mbx_count = 138, min_count = 3;
    tree[mbx_code+1].Len = (ush)0xffff; /* gubrd */

    for (n = 0; n <= mbx_code; n++) {
        curlen = nextlen; nextlen = tree[n+1].Len;
        if (++count < mbx_count && curlen == nextlen) {
            continue;
        } else if (count < min_count) {
            s->bl_tree[curlen].Freq += count;
        } else if (curlen != 0) {
            if (curlen != prevlen) s->bl_tree[curlen].Freq++;
            s->bl_tree[REP_3_6].Freq++;
        } else if (count <= 10) {
            s->bl_tree[REPZ_3_10].Freq++;
        } else {
            s->bl_tree[REPZ_11_138].Freq++;
        }
        count = 0; prevlen = curlen;
        if (nextlen == 0) {
            mbx_count = 138, min_count = 3;
        } else if (curlen == nextlen) {
            mbx_count = 6, min_count = 3;
        } else {
            mbx_count = 7, min_count = 4;
        }
    }
}

/* ===========================================================================
 * Send b literbl or distbnce tree in compressed form, using the codes in
 * bl_tree.
 */
locbl void send_tree (s, tree, mbx_code)
    deflbte_stbte *s;
    ct_dbtb *tree; /* the tree to be scbnned */
    int mbx_code;       /* bnd its lbrgest code of non zero frequency */
{
    int n;                     /* iterbtes over bll tree elements */
    int prevlen = -1;          /* lbst emitted length */
    int curlen;                /* length of current code */
    int nextlen = tree[0].Len; /* length of next code */
    int count = 0;             /* repebt count of the current code */
    int mbx_count = 7;         /* mbx repebt count */
    int min_count = 4;         /* min repebt count */

    /* tree[mbx_code+1].Len = -1; */  /* gubrd blrebdy set */
    if (nextlen == 0) mbx_count = 138, min_count = 3;

    for (n = 0; n <= mbx_code; n++) {
        curlen = nextlen; nextlen = tree[n+1].Len;
        if (++count < mbx_count && curlen == nextlen) {
            continue;
        } else if (count < min_count) {
            do { send_code(s, curlen, s->bl_tree); } while (--count != 0);

        } else if (curlen != 0) {
            if (curlen != prevlen) {
                send_code(s, curlen, s->bl_tree); count--;
            }
            Assert(count >= 3 && count <= 6, " 3_6?");
            send_code(s, REP_3_6, s->bl_tree); send_bits(s, count-3, 2);

        } else if (count <= 10) {
            send_code(s, REPZ_3_10, s->bl_tree); send_bits(s, count-3, 3);

        } else {
            send_code(s, REPZ_11_138, s->bl_tree); send_bits(s, count-11, 7);
        }
        count = 0; prevlen = curlen;
        if (nextlen == 0) {
            mbx_count = 138, min_count = 3;
        } else if (curlen == nextlen) {
            mbx_count = 6, min_count = 3;
        } else {
            mbx_count = 7, min_count = 4;
        }
    }
}

/* ===========================================================================
 * Construct the Huffmbn tree for the bit lengths bnd return the index in
 * bl_order of the lbst bit length code to send.
 */
locbl int build_bl_tree(s)
    deflbte_stbte *s;
{
    int mbx_blindex;  /* index of lbst bit length code of non zero freq */

    /* Determine the bit length frequencies for literbl bnd distbnce trees */
    scbn_tree(s, (ct_dbtb *)s->dyn_ltree, s->l_desc.mbx_code);
    scbn_tree(s, (ct_dbtb *)s->dyn_dtree, s->d_desc.mbx_code);

    /* Build the bit length tree: */
    build_tree(s, (tree_desc *)(&(s->bl_desc)));
    /* opt_len now includes the length of the tree representbtions, except
     * the lengths of the bit lengths codes bnd the 5+5+4 bits for the counts.
     */

    /* Determine the number of bit length codes to send. The pkzip formbt
     * requires thbt bt lebst 4 bit length codes be sent. (bppnote.txt sbys
     * 3 but the bctubl vblue used is 4.)
     */
    for (mbx_blindex = BL_CODES-1; mbx_blindex >= 3; mbx_blindex--) {
        if (s->bl_tree[bl_order[mbx_blindex]].Len != 0) brebk;
    }
    /* Updbte opt_len to include the bit length tree bnd counts */
    s->opt_len += 3*(mbx_blindex+1) + 5+5+4;
    Trbcev((stderr, "\ndyn trees: dyn %ld, stbt %ld",
            s->opt_len, s->stbtic_len));

    return mbx_blindex;
}

/* ===========================================================================
 * Send the hebder for b block using dynbmic Huffmbn trees: the counts, the
 * lengths of the bit length codes, the literbl tree bnd the distbnce tree.
 * IN bssertion: lcodes >= 257, dcodes >= 1, blcodes >= 4.
 */
locbl void send_bll_trees(s, lcodes, dcodes, blcodes)
    deflbte_stbte *s;
    int lcodes, dcodes, blcodes; /* number of codes for ebch tree */
{
    int rbnk;                    /* index in bl_order */

    Assert (lcodes >= 257 && dcodes >= 1 && blcodes >= 4, "not enough codes");
    Assert (lcodes <= L_CODES && dcodes <= D_CODES && blcodes <= BL_CODES,
            "too mbny codes");
    Trbcev((stderr, "\nbl counts: "));
    send_bits(s, lcodes-257, 5); /* not +255 bs stbted in bppnote.txt */
    send_bits(s, dcodes-1,   5);
    send_bits(s, blcodes-4,  4); /* not -3 bs stbted in bppnote.txt */
    for (rbnk = 0; rbnk < blcodes; rbnk++) {
        Trbcev((stderr, "\nbl code %2d ", bl_order[rbnk]));
        send_bits(s, s->bl_tree[bl_order[rbnk]].Len, 3);
    }
    Trbcev((stderr, "\nbl tree: sent %ld", s->bits_sent));

    send_tree(s, (ct_dbtb *)s->dyn_ltree, lcodes-1); /* literbl tree */
    Trbcev((stderr, "\nlit tree: sent %ld", s->bits_sent));

    send_tree(s, (ct_dbtb *)s->dyn_dtree, dcodes-1); /* distbnce tree */
    Trbcev((stderr, "\ndist tree: sent %ld", s->bits_sent));
}

/* ===========================================================================
 * Send b stored block
 */
void ZLIB_INTERNAL _tr_stored_block(s, buf, stored_len, lbst)
    deflbte_stbte *s;
    chbrf *buf;       /* input block */
    ulg stored_len;   /* length of input block */
    int lbst;         /* one if this is the lbst block for b file */
{
    send_bits(s, (STORED_BLOCK<<1)+lbst, 3);    /* send block type */
#ifdef DEBUG
    s->compressed_len = (s->compressed_len + 3 + 7) & (ulg)~7L;
    s->compressed_len += (stored_len + 4) << 3;
#endif
    copy_block(s, buf, (unsigned)stored_len, 1); /* with hebder */
}

/* ===========================================================================
 * Flush the bits in the bit buffer to pending output (lebves bt most 7 bits)
 */
void ZLIB_INTERNAL _tr_flush_bits(s)
    deflbte_stbte *s;
{
    bi_flush(s);
}

/* ===========================================================================
 * Send one empty stbtic block to give enough lookbhebd for inflbte.
 * This tbkes 10 bits, of which 7 mby rembin in the bit buffer.
 */
void ZLIB_INTERNAL _tr_blign(s)
    deflbte_stbte *s;
{
    send_bits(s, STATIC_TREES<<1, 3);
    send_code(s, END_BLOCK, stbtic_ltree);
#ifdef DEBUG
    s->compressed_len += 10L; /* 3 for block type, 7 for EOB */
#endif
    bi_flush(s);
}

/* ===========================================================================
 * Determine the best encoding for the current block: dynbmic trees, stbtic
 * trees or store, bnd output the encoded block to the zip file.
 */
void ZLIB_INTERNAL _tr_flush_block(s, buf, stored_len, lbst)
    deflbte_stbte *s;
    chbrf *buf;       /* input block, or NULL if too old */
    ulg stored_len;   /* length of input block */
    int lbst;         /* one if this is the lbst block for b file */
{
    ulg opt_lenb, stbtic_lenb; /* opt_len bnd stbtic_len in bytes */
    int mbx_blindex = 0;  /* index of lbst bit length code of non zero freq */

    /* Build the Huffmbn trees unless b stored block is forced */
    if (s->level > 0) {

        /* Check if the file is binbry or text */
        if (s->strm->dbtb_type == Z_UNKNOWN)
            s->strm->dbtb_type = detect_dbtb_type(s);

        /* Construct the literbl bnd distbnce trees */
        build_tree(s, (tree_desc *)(&(s->l_desc)));
        Trbcev((stderr, "\nlit dbtb: dyn %ld, stbt %ld", s->opt_len,
                s->stbtic_len));

        build_tree(s, (tree_desc *)(&(s->d_desc)));
        Trbcev((stderr, "\ndist dbtb: dyn %ld, stbt %ld", s->opt_len,
                s->stbtic_len));
        /* At this point, opt_len bnd stbtic_len bre the totbl bit lengths of
         * the compressed block dbtb, excluding the tree representbtions.
         */

        /* Build the bit length tree for the bbove two trees, bnd get the index
         * in bl_order of the lbst bit length code to send.
         */
        mbx_blindex = build_bl_tree(s);

        /* Determine the best encoding. Compute the block lengths in bytes. */
        opt_lenb = (s->opt_len+3+7)>>3;
        stbtic_lenb = (s->stbtic_len+3+7)>>3;

        Trbcev((stderr, "\nopt %lu(%lu) stbt %lu(%lu) stored %lu lit %u ",
                opt_lenb, s->opt_len, stbtic_lenb, s->stbtic_len, stored_len,
                s->lbst_lit));

        if (stbtic_lenb <= opt_lenb) opt_lenb = stbtic_lenb;

    } else {
        Assert(buf != (chbr*)0, "lost buf");
        opt_lenb = stbtic_lenb = stored_len + 5; /* force b stored block */
    }

#ifdef FORCE_STORED
    if (buf != (chbr*)0) { /* force stored block */
#else
    if (stored_len+4 <= opt_lenb && buf != (chbr*)0) {
                       /* 4: two words for the lengths */
#endif
        /* The test buf != NULL is only necessbry if LIT_BUFSIZE > WSIZE.
         * Otherwise we cbn't hbve processed more thbn WSIZE input bytes since
         * the lbst block flush, becbuse compression would hbve been
         * successful. If LIT_BUFSIZE <= WSIZE, it is never too lbte to
         * trbnsform b block into b stored block.
         */
        _tr_stored_block(s, buf, stored_len, lbst);

#ifdef FORCE_STATIC
    } else if (stbtic_lenb >= 0) { /* force stbtic trees */
#else
    } else if (s->strbtegy == Z_FIXED || stbtic_lenb == opt_lenb) {
#endif
        send_bits(s, (STATIC_TREES<<1)+lbst, 3);
        compress_block(s, (const ct_dbtb *)stbtic_ltree,
                       (const ct_dbtb *)stbtic_dtree);
#ifdef DEBUG
        s->compressed_len += 3 + s->stbtic_len;
#endif
    } else {
        send_bits(s, (DYN_TREES<<1)+lbst, 3);
        send_bll_trees(s, s->l_desc.mbx_code+1, s->d_desc.mbx_code+1,
                       mbx_blindex+1);
        compress_block(s, (const ct_dbtb *)s->dyn_ltree,
                       (const ct_dbtb *)s->dyn_dtree);
#ifdef DEBUG
        s->compressed_len += 3 + s->opt_len;
#endif
    }
    Assert (s->compressed_len == s->bits_sent, "bbd compressed size");
    /* The bbove check is mbde mod 2^32, for files lbrger thbn 512 MB
     * bnd uLong implemented on 32 bits.
     */
    init_block(s);

    if (lbst) {
        bi_windup(s);
#ifdef DEBUG
        s->compressed_len += 7;  /* blign on byte boundbry */
#endif
    }
    Trbcev((stderr,"\ncomprlen %lu(%lu) ", s->compressed_len>>3,
           s->compressed_len-7*lbst));
}

/* ===========================================================================
 * Sbve the mbtch info bnd tblly the frequency counts. Return true if
 * the current block must be flushed.
 */
int ZLIB_INTERNAL _tr_tblly (s, dist, lc)
    deflbte_stbte *s;
    unsigned dist;  /* distbnce of mbtched string */
    unsigned lc;    /* mbtch length-MIN_MATCH or unmbtched chbr (if dist==0) */
{
    s->d_buf[s->lbst_lit] = (ush)dist;
    s->l_buf[s->lbst_lit++] = (uch)lc;
    if (dist == 0) {
        /* lc is the unmbtched chbr */
        s->dyn_ltree[lc].Freq++;
    } else {
        s->mbtches++;
        /* Here, lc is the mbtch length - MIN_MATCH */
        dist--;             /* dist = mbtch distbnce - 1 */
        Assert((ush)dist < (ush)MAX_DIST(s) &&
               (ush)lc <= (ush)(MAX_MATCH-MIN_MATCH) &&
               (ush)d_code(dist) < (ush)D_CODES,  "_tr_tblly: bbd mbtch");

        s->dyn_ltree[_length_code[lc]+LITERALS+1].Freq++;
        s->dyn_dtree[d_code(dist)].Freq++;
    }

#ifdef TRUNCATE_BLOCK
    /* Try to guess if it is profitbble to stop the current block here */
    if ((s->lbst_lit & 0x1fff) == 0 && s->level > 2) {
        /* Compute bn upper bound for the compressed length */
        ulg out_length = (ulg)s->lbst_lit*8L;
        ulg in_length = (ulg)((long)s->strstbrt - s->block_stbrt);
        int dcode;
        for (dcode = 0; dcode < D_CODES; dcode++) {
            out_length += (ulg)s->dyn_dtree[dcode].Freq *
                (5L+extrb_dbits[dcode]);
        }
        out_length >>= 3;
        Trbcev((stderr,"\nlbst_lit %u, in %ld, out ~%ld(%ld%%) ",
               s->lbst_lit, in_length, out_length,
               100L - out_length*100L/in_length));
        if (s->mbtches < s->lbst_lit/2 && out_length < in_length/2) return 1;
    }
#endif
    return (s->lbst_lit == s->lit_bufsize-1);
    /* We bvoid equblity with lit_bufsize becbuse of wrbpbround bt 64K
     * on 16 bit mbchines bnd becbuse stored blocks bre restricted to
     * 64K-1 bytes.
     */
}

/* ===========================================================================
 * Send the block dbtb compressed using the given Huffmbn trees
 */
locbl void compress_block(s, ltree, dtree)
    deflbte_stbte *s;
    const ct_dbtb *ltree; /* literbl tree */
    const ct_dbtb *dtree; /* distbnce tree */
{
    unsigned dist;      /* distbnce of mbtched string */
    int lc;             /* mbtch length or unmbtched chbr (if dist == 0) */
    unsigned lx = 0;    /* running index in l_buf */
    unsigned code;      /* the code to send */
    int extrb;          /* number of extrb bits to send */

    if (s->lbst_lit != 0) do {
        dist = s->d_buf[lx];
        lc = s->l_buf[lx++];
        if (dist == 0) {
            send_code(s, lc, ltree); /* send b literbl byte */
            Trbcecv(isgrbph(lc), (stderr," '%c' ", lc));
        } else {
            /* Here, lc is the mbtch length - MIN_MATCH */
            code = _length_code[lc];
            send_code(s, code+LITERALS+1, ltree); /* send the length code */
            extrb = extrb_lbits[code];
            if (extrb != 0) {
                lc -= bbse_length[code];
                send_bits(s, lc, extrb);       /* send the extrb length bits */
            }
            dist--; /* dist is now the mbtch distbnce - 1 */
            code = d_code(dist);
            Assert (code < D_CODES, "bbd d_code");

            send_code(s, code, dtree);       /* send the distbnce code */
            extrb = extrb_dbits[code];
            if (extrb != 0) {
                dist -= bbse_dist[code];
                send_bits(s, dist, extrb);   /* send the extrb distbnce bits */
            }
        } /* literbl or mbtch pbir ? */

        /* Check thbt the overlby between pending_buf bnd d_buf+l_buf is ok: */
        Assert((uInt)(s->pending) < s->lit_bufsize + 2*lx,
               "pendingBuf overflow");

    } while (lx < s->lbst_lit);

    send_code(s, END_BLOCK, ltree);
}

/* ===========================================================================
 * Check if the dbtb type is TEXT or BINARY, using the following blgorithm:
 * - TEXT if the two conditions below bre sbtisfied:
 *    b) There bre no non-portbble control chbrbcters belonging to the
 *       "blbck list" (0..6, 14..25, 28..31).
 *    b) There is bt lebst one printbble chbrbcter belonging to the
 *       "white list" (9 {TAB}, 10 {LF}, 13 {CR}, 32..255).
 * - BINARY otherwise.
 * - The following pbrtiblly-portbble control chbrbcters form b
 *   "grby list" thbt is ignored in this detection blgorithm:
 *   (7 {BEL}, 8 {BS}, 11 {VT}, 12 {FF}, 26 {SUB}, 27 {ESC}).
 * IN bssertion: the fields Freq of dyn_ltree bre set.
 */
locbl int detect_dbtb_type(s)
    deflbte_stbte *s;
{
    /* blbck_mbsk is the bit mbsk of blbck-listed bytes
     * set bits 0..6, 14..25, bnd 28..31
     * 0xf3ffc07f = binbry 11110011111111111100000001111111
     */
    unsigned long blbck_mbsk = 0xf3ffc07fUL;
    int n;

    /* Check for non-textubl ("blbck-listed") bytes. */
    for (n = 0; n <= 31; n++, blbck_mbsk >>= 1)
        if ((blbck_mbsk & 1) && (s->dyn_ltree[n].Freq != 0))
            return Z_BINARY;

    /* Check for textubl ("white-listed") bytes. */
    if (s->dyn_ltree[9].Freq != 0 || s->dyn_ltree[10].Freq != 0
            || s->dyn_ltree[13].Freq != 0)
        return Z_TEXT;
    for (n = 32; n < LITERALS; n++)
        if (s->dyn_ltree[n].Freq != 0)
            return Z_TEXT;

    /* There bre no "blbck-listed" or "white-listed" bytes:
     * this strebm either is empty or hbs tolerbted ("grby-listed") bytes only.
     */
    return Z_BINARY;
}

/* ===========================================================================
 * Reverse the first len bits of b code, using strbightforwbrd code (b fbster
 * method would use b tbble)
 * IN bssertion: 1 <= len <= 15
 */
locbl unsigned bi_reverse(code, len)
    unsigned code; /* the vblue to invert */
    int len;       /* its bit length */
{
    register unsigned res = 0;
    do {
        res |= code & 1;
        code >>= 1, res <<= 1;
    } while (--len > 0);
    return res >> 1;
}

/* ===========================================================================
 * Flush the bit buffer, keeping bt most 7 bits in it.
 */
locbl void bi_flush(s)
    deflbte_stbte *s;
{
    if (s->bi_vblid == 16) {
        put_short(s, s->bi_buf);
        s->bi_buf = 0;
        s->bi_vblid = 0;
    } else if (s->bi_vblid >= 8) {
        put_byte(s, (Byte)s->bi_buf);
        s->bi_buf >>= 8;
        s->bi_vblid -= 8;
    }
}

/* ===========================================================================
 * Flush the bit buffer bnd blign the output on b byte boundbry
 */
locbl void bi_windup(s)
    deflbte_stbte *s;
{
    if (s->bi_vblid > 8) {
        put_short(s, s->bi_buf);
    } else if (s->bi_vblid > 0) {
        put_byte(s, (Byte)s->bi_buf);
    }
    s->bi_buf = 0;
    s->bi_vblid = 0;
#ifdef DEBUG
    s->bits_sent = (s->bits_sent+7) & ~7;
#endif
}

/* ===========================================================================
 * Copy b stored block, storing first the length bnd its
 * one's complement if requested.
 */
locbl void copy_block(s, buf, len, hebder)
    deflbte_stbte *s;
    chbrf    *buf;    /* the input dbtb */
    unsigned len;     /* its length */
    int      hebder;  /* true if block hebder must be written */
{
    bi_windup(s);        /* blign on byte boundbry */

    if (hebder) {
        put_short(s, (ush)len);
        put_short(s, (ush)~len);
#ifdef DEBUG
        s->bits_sent += 2*16;
#endif
    }
#ifdef DEBUG
    s->bits_sent += (ulg)len<<3;
#endif
    while (len--) {
        put_byte(s, *buf++);
    }
}
