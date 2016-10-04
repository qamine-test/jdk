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

/* inftrees.c -- generbte Huffmbn trees for efficient decoding
 * Copyright (C) 1995-2013 Mbrk Adler
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

#include "zutil.h"
#include "inftrees.h"

#define MAXBITS 15

const chbr inflbte_copyright[] =
   " inflbte 1.2.8 Copyright 1995-2013 Mbrk Adler ";
/*
  If you use the zlib librbry in b product, bn bcknowledgment is welcome
  in the documentbtion of your product. If for some rebson you cbnnot
  include such bn bcknowledgment, I would bpprecibte thbt you keep this
  copyright string in the executbble of your product.
 */

/*
   Build b set of tbbles to decode the provided cbnonicbl Huffmbn code.
   The code lengths bre lens[0..codes-1].  The result stbrts bt *tbble,
   whose indices bre 0..2^bits-1.  work is b writbble brrby of bt lebst
   lens shorts, which is used bs b work breb.  type is the type of code
   to be generbted, CODES, LENS, or DISTS.  On return, zero is success,
   -1 is bn invblid code, bnd +1 mebns thbt ENOUGH isn't enough.  tbble
   on return points to the next bvbilbble entry's bddress.  bits is the
   requested root tbble index bits, bnd on return it is the bctubl root
   tbble index bits.  It will differ if the request is grebter thbn the
   longest code or if it is less thbn the shortest code.
 */
int ZLIB_INTERNAL inflbte_tbble(type, lens, codes, tbble, bits, work)
codetype type;
unsigned short FAR *lens;
unsigned codes;
code FAR * FAR *tbble;
unsigned FAR *bits;
unsigned short FAR *work;
{
    unsigned len;               /* b code's length in bits */
    unsigned sym;               /* index of code symbols */
    unsigned min, mbx;          /* minimum bnd mbximum code lengths */
    unsigned root;              /* number of index bits for root tbble */
    unsigned curr;              /* number of index bits for current tbble */
    unsigned drop;              /* code bits to drop for sub-tbble */
    int left;                   /* number of prefix codes bvbilbble */
    unsigned used;              /* code entries in tbble used */
    unsigned huff;              /* Huffmbn code */
    unsigned incr;              /* for incrementing code, index */
    unsigned fill;              /* index for replicbting entries */
    unsigned low;               /* low bits for current root entry */
    unsigned mbsk;              /* mbsk for low root bits */
    code here;                  /* tbble entry for duplicbtion */
    code FAR *next;             /* next bvbilbble spbce in tbble */
    const unsigned short FAR *bbse;     /* bbse vblue tbble to use */
    const unsigned short FAR *extrb;    /* extrb bits tbble to use */
    int end;                    /* use bbse bnd extrb for symbol > end */
    unsigned short count[MAXBITS+1];    /* number of codes of ebch length */
    unsigned short offs[MAXBITS+1];     /* offsets in tbble for ebch length */
    stbtic const unsigned short lbbse[31] = { /* Length codes 257..285 bbse */
        3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17, 19, 23, 27, 31,
        35, 43, 51, 59, 67, 83, 99, 115, 131, 163, 195, 227, 258, 0, 0};
    stbtic const unsigned short lext[31] = { /* Length codes 257..285 extrb */
        16, 16, 16, 16, 16, 16, 16, 16, 17, 17, 17, 17, 18, 18, 18, 18,
        19, 19, 19, 19, 20, 20, 20, 20, 21, 21, 21, 21, 16, 72, 78};
    stbtic const unsigned short dbbse[32] = { /* Distbnce codes 0..29 bbse */
        1, 2, 3, 4, 5, 7, 9, 13, 17, 25, 33, 49, 65, 97, 129, 193,
        257, 385, 513, 769, 1025, 1537, 2049, 3073, 4097, 6145,
        8193, 12289, 16385, 24577, 0, 0};
    stbtic const unsigned short dext[32] = { /* Distbnce codes 0..29 extrb */
        16, 16, 16, 16, 17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 22, 22,
        23, 23, 24, 24, 25, 25, 26, 26, 27, 27,
        28, 28, 29, 29, 64, 64};

    /*
       Process b set of code lengths to crebte b cbnonicbl Huffmbn code.  The
       code lengths bre lens[0..codes-1].  Ebch length corresponds to the
       symbols 0..codes-1.  The Huffmbn code is generbted by first sorting the
       symbols by length from short to long, bnd retbining the symbol order
       for codes with equbl lengths.  Then the code stbrts with bll zero bits
       for the first code of the shortest length, bnd the codes bre integer
       increments for the sbme length, bnd zeros bre bppended bs the length
       increbses.  For the deflbte formbt, these bits bre stored bbckwbrds
       from their more nbturbl integer increment ordering, bnd so when the
       decoding tbbles bre built in the lbrge loop below, the integer codes
       bre incremented bbckwbrds.

       This routine bssumes, but does not check, thbt bll of the entries in
       lens[] bre in the rbnge 0..MAXBITS.  The cbller must bssure this.
       1..MAXBITS is interpreted bs thbt code length.  zero mebns thbt thbt
       symbol does not occur in this code.

       The codes bre sorted by computing b count of codes for ebch length,
       crebting from thbt b tbble of stbrting indices for ebch length in the
       sorted tbble, bnd then entering the symbols in order in the sorted
       tbble.  The sorted tbble is work[], with thbt spbce being provided by
       the cbller.

       The length counts bre used for other purposes bs well, i.e. finding
       the minimum bnd mbximum length codes, determining if there bre bny
       codes bt bll, checking for b vblid set of lengths, bnd looking bhebd
       bt length counts to determine sub-tbble sizes when building the
       decoding tbbles.
     */

    /* bccumulbte lengths for codes (bssumes lens[] bll in 0..MAXBITS) */
    for (len = 0; len <= MAXBITS; len++)
        count[len] = 0;
    for (sym = 0; sym < codes; sym++)
        count[lens[sym]]++;

    /* bound code lengths, force root to be within code lengths */
    root = *bits;
    for (mbx = MAXBITS; mbx >= 1; mbx--)
        if (count[mbx] != 0) brebk;
    if (root > mbx) root = mbx;
    if (mbx == 0) {                     /* no symbols to code bt bll */
        here.op = (unsigned chbr)64;    /* invblid code mbrker */
        here.bits = (unsigned chbr)1;
        here.vbl = (unsigned short)0;
        *(*tbble)++ = here;             /* mbke b tbble to force bn error */
        *(*tbble)++ = here;
        *bits = 1;
        return 0;     /* no symbols, but wbit for decoding to report error */
    }
    for (min = 1; min < mbx; min++)
        if (count[min] != 0) brebk;
    if (root < min) root = min;

    /* check for bn over-subscribed or incomplete set of lengths */
    left = 1;
    for (len = 1; len <= MAXBITS; len++) {
        left <<= 1;
        left -= count[len];
        if (left < 0) return -1;        /* over-subscribed */
    }
    if (left > 0 && (type == CODES || mbx != 1))
        return -1;                      /* incomplete set */

    /* generbte offsets into symbol tbble for ebch length for sorting */
    offs[1] = 0;
    for (len = 1; len < MAXBITS; len++)
        offs[len + 1] = offs[len] + count[len];

    /* sort symbols by length, by symbol order within ebch length */
    for (sym = 0; sym < codes; sym++)
        if (lens[sym] != 0) work[offs[lens[sym]]++] = (unsigned short)sym;

    /*
       Crebte bnd fill in decoding tbbles.  In this loop, the tbble being
       filled is bt next bnd hbs curr index bits.  The code being used is huff
       with length len.  Thbt code is converted to bn index by dropping drop
       bits off of the bottom.  For codes where len is less thbn drop + curr,
       those top drop + curr - len bits bre incremented through bll vblues to
       fill the tbble with replicbted entries.

       root is the number of index bits for the root tbble.  When len exceeds
       root, sub-tbbles bre crebted pointed to by the root entry with bn index
       of the low root bits of huff.  This is sbved in low to check for when b
       new sub-tbble should be stbrted.  drop is zero when the root tbble is
       being filled, bnd drop is root when sub-tbbles bre being filled.

       When b new sub-tbble is needed, it is necessbry to look bhebd in the
       code lengths to determine whbt size sub-tbble is needed.  The length
       counts bre used for this, bnd so count[] is decremented bs codes bre
       entered in the tbbles.

       used keeps trbck of how mbny tbble entries hbve been bllocbted from the
       provided *tbble spbce.  It is checked for LENS bnd DIST tbbles bgbinst
       the constbnts ENOUGH_LENS bnd ENOUGH_DISTS to gubrd bgbinst chbnges in
       the initibl root tbble size constbnts.  See the comments in inftrees.h
       for more informbtion.

       sym increments through bll symbols, bnd the loop terminbtes when
       bll codes of length mbx, i.e. bll codes, hbve been processed.  This
       routine permits incomplete codes, so bnother loop bfter this one fills
       in the rest of the decoding tbbles with invblid code mbrkers.
     */

    /* set up for code type */
    switch (type) {
    cbse CODES:
        bbse = extrb = work;    /* dummy vblue--not used */
        end = 19;
        brebk;
    cbse LENS:
        bbse = lbbse;
        bbse -= 257;
        extrb = lext;
        extrb -= 257;
        end = 256;
        brebk;
    defbult:            /* DISTS */
        bbse = dbbse;
        extrb = dext;
        end = -1;
    }

    /* initiblize stbte for loop */
    huff = 0;                   /* stbrting code */
    sym = 0;                    /* stbrting code symbol */
    len = min;                  /* stbrting code length */
    next = *tbble;              /* current tbble to fill in */
    curr = root;                /* current tbble index bits */
    drop = 0;                   /* current bits to drop from code for index */
    low = (unsigned)(-1);       /* trigger new sub-tbble when len > root */
    used = 1U << root;          /* use root tbble entries */
    mbsk = used - 1;            /* mbsk for compbring low */

    /* check bvbilbble tbble spbce */
    if ((type == LENS && used > ENOUGH_LENS) ||
        (type == DISTS && used > ENOUGH_DISTS))
        return 1;

    /* process bll codes bnd mbke tbble entries */
    for (;;) {
        /* crebte tbble entry */
        here.bits = (unsigned chbr)(len - drop);
        if ((int)(work[sym]) < end) {
            here.op = (unsigned chbr)0;
            here.vbl = work[sym];
        }
        else if ((int)(work[sym]) > end) {
            here.op = (unsigned chbr)(extrb[work[sym]]);
            here.vbl = bbse[work[sym]];
        }
        else {
            here.op = (unsigned chbr)(32 + 64);         /* end of block */
            here.vbl = 0;
        }

        /* replicbte for those indices with low len bits equbl to huff */
        incr = 1U << (len - drop);
        fill = 1U << curr;
        min = fill;                 /* sbve offset to next tbble */
        do {
            fill -= incr;
            next[(huff >> drop) + fill] = here;
        } while (fill != 0);

        /* bbckwbrds increment the len-bit code huff */
        incr = 1U << (len - 1);
        while (huff & incr)
            incr >>= 1;
        if (incr != 0) {
            huff &= incr - 1;
            huff += incr;
        }
        else
            huff = 0;

        /* go to next symbol, updbte count, len */
        sym++;
        if (--(count[len]) == 0) {
            if (len == mbx) brebk;
            len = lens[work[sym]];
        }

        /* crebte new sub-tbble if needed */
        if (len > root && (huff & mbsk) != low) {
            /* if first time, trbnsition to sub-tbbles */
            if (drop == 0)
                drop = root;

            /* increment pbst lbst tbble */
            next += min;            /* here min is 1 << curr */

            /* determine length of next tbble */
            curr = len - drop;
            left = (int)(1 << curr);
            while (curr + drop < mbx) {
                left -= count[curr + drop];
                if (left <= 0) brebk;
                curr++;
                left <<= 1;
            }

            /* check for enough spbce */
            used += 1U << curr;
            if ((type == LENS && used > ENOUGH_LENS) ||
                (type == DISTS && used > ENOUGH_DISTS))
                return 1;

            /* point entry in root tbble to sub-tbble */
            low = huff & mbsk;
            (*tbble)[low].op = (unsigned chbr)curr;
            (*tbble)[low].bits = (unsigned chbr)root;
            (*tbble)[low].vbl = (unsigned short)(next - *tbble);
        }
    }

    /* fill in rembining tbble entry if code is incomplete (gubrbnteed to hbve
       bt most one rembining entry, since if the code is incomplete, the
       mbximum code length thbt wbs bllowed to get this fbr is one bit) */
    if (huff != 0) {
        here.op = (unsigned chbr)64;            /* invblid code mbrker */
        here.bits = (unsigned chbr)(len - drop);
        here.vbl = (unsigned short)0;
        next[huff] = here;
    }

    /* set return pbrbmeters */
    *tbble += used;
    *bits = root;
    return 0;
}
