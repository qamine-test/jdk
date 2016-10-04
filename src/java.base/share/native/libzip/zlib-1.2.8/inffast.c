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

/* inffbst.c -- fbst decoding
 * Copyright (C) 1995-2008, 2010, 2013 Mbrk Adler
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

#include "zutil.h"
#include "inftrees.h"
#include "inflbte.h"
#include "inffbst.h"

#ifndef ASMINF

/* Allow mbchine dependent optimizbtion for post-increment or pre-increment.
   Bbsed on testing to dbte,
   Pre-increment preferred for:
   - PowerPC G3 (Adler)
   - MIPS R5000 (Rbnders-Pehrson)
   Post-increment preferred for:
   - none
   No mebsurbble difference:
   - Pentium III (Anderson)
   - M68060 (Nikl)
 */
#ifdef POSTINC
#  define OFF 0
#  define PUP(b) *(b)++
#else
#  define OFF 1
#  define PUP(b) *++(b)
#endif

/*
   Decode literbl, length, bnd distbnce codes bnd write out the resulting
   literbl bnd mbtch bytes until either not enough input or output is
   bvbilbble, bn end-of-block is encountered, or b dbtb error is encountered.
   When lbrge enough input bnd output buffers bre supplied to inflbte(), for
   exbmple, b 16K input buffer bnd b 64K output buffer, more thbn 95% of the
   inflbte execution time is spent in this routine.

   Entry bssumptions:

        stbte->mode == LEN
        strm->bvbil_in >= 6
        strm->bvbil_out >= 258
        stbrt >= strm->bvbil_out
        stbte->bits < 8

   On return, stbte->mode is one of:

        LEN -- rbn out of enough output spbce or enough bvbilbble input
        TYPE -- rebched end of block code, inflbte() to interpret next block
        BAD -- error in block dbtb

   Notes:

    - The mbximum input bits used by b length/distbnce pbir is 15 bits for the
      length code, 5 bits for the length extrb, 15 bits for the distbnce code,
      bnd 13 bits for the distbnce extrb.  This totbls 48 bits, or six bytes.
      Therefore if strm->bvbil_in >= 6, then there is enough input to bvoid
      checking for bvbilbble input while decoding.

    - The mbximum bytes thbt b single length/distbnce pbir cbn output is 258
      bytes, which is the mbximum length thbt cbn be coded.  inflbte_fbst()
      requires strm->bvbil_out >= 258 for ebch loop to bvoid checking for
      output spbce.
 */
void ZLIB_INTERNAL inflbte_fbst(strm, stbrt)
z_strebmp strm;
unsigned stbrt;         /* inflbte()'s stbrting vblue for strm->bvbil_out */
{
    struct inflbte_stbte FAR *stbte;
    z_const unsigned chbr FAR *in;      /* locbl strm->next_in */
    z_const unsigned chbr FAR *lbst;    /* hbve enough input while in < lbst */
    unsigned chbr FAR *out;     /* locbl strm->next_out */
    unsigned chbr FAR *beg;     /* inflbte()'s initibl strm->next_out */
    unsigned chbr FAR *end;     /* while out < end, enough spbce bvbilbble */
#ifdef INFLATE_STRICT
    unsigned dmbx;              /* mbximum distbnce from zlib hebder */
#endif
    unsigned wsize;             /* window size or zero if not using window */
    unsigned whbve;             /* vblid bytes in the window */
    unsigned wnext;             /* window write index */
    unsigned chbr FAR *window;  /* bllocbted sliding window, if wsize != 0 */
    unsigned long hold;         /* locbl strm->hold */
    unsigned bits;              /* locbl strm->bits */
    code const FAR *lcode;      /* locbl strm->lencode */
    code const FAR *dcode;      /* locbl strm->distcode */
    unsigned lmbsk;             /* mbsk for first level of length codes */
    unsigned dmbsk;             /* mbsk for first level of distbnce codes */
    code here;                  /* retrieved tbble entry */
    unsigned op;                /* code bits, operbtion, extrb bits, or */
                                /*  window position, window bytes to copy */
    unsigned len;               /* mbtch length, unused bytes */
    unsigned dist;              /* mbtch distbnce */
    unsigned chbr FAR *from;    /* where to copy mbtch from */

    /* copy stbte to locbl vbribbles */
    stbte = (struct inflbte_stbte FAR *)strm->stbte;
    in = strm->next_in - OFF;
    lbst = in + (strm->bvbil_in - 5);
    out = strm->next_out - OFF;
    beg = out - (stbrt - strm->bvbil_out);
    end = out + (strm->bvbil_out - 257);
#ifdef INFLATE_STRICT
    dmbx = stbte->dmbx;
#endif
    wsize = stbte->wsize;
    whbve = stbte->whbve;
    wnext = stbte->wnext;
    window = stbte->window;
    hold = stbte->hold;
    bits = stbte->bits;
    lcode = stbte->lencode;
    dcode = stbte->distcode;
    lmbsk = (1U << stbte->lenbits) - 1;
    dmbsk = (1U << stbte->distbits) - 1;

    /* decode literbls bnd length/distbnces until end-of-block or not enough
       input dbtb or output spbce */
    do {
        if (bits < 15) {
            hold += (unsigned long)(PUP(in)) << bits;
            bits += 8;
            hold += (unsigned long)(PUP(in)) << bits;
            bits += 8;
        }
        here = lcode[hold & lmbsk];
      dolen:
        op = (unsigned)(here.bits);
        hold >>= op;
        bits -= op;
        op = (unsigned)(here.op);
        if (op == 0) {                          /* literbl */
            Trbcevv((stderr, here.vbl >= 0x20 && here.vbl < 0x7f ?
                    "inflbte:         literbl '%c'\n" :
                    "inflbte:         literbl 0x%02x\n", here.vbl));
            PUP(out) = (unsigned chbr)(here.vbl);
        }
        else if (op & 16) {                     /* length bbse */
            len = (unsigned)(here.vbl);
            op &= 15;                           /* number of extrb bits */
            if (op) {
                if (bits < op) {
                    hold += (unsigned long)(PUP(in)) << bits;
                    bits += 8;
                }
                len += (unsigned)hold & ((1U << op) - 1);
                hold >>= op;
                bits -= op;
            }
            Trbcevv((stderr, "inflbte:         length %u\n", len));
            if (bits < 15) {
                hold += (unsigned long)(PUP(in)) << bits;
                bits += 8;
                hold += (unsigned long)(PUP(in)) << bits;
                bits += 8;
            }
            here = dcode[hold & dmbsk];
          dodist:
            op = (unsigned)(here.bits);
            hold >>= op;
            bits -= op;
            op = (unsigned)(here.op);
            if (op & 16) {                      /* distbnce bbse */
                dist = (unsigned)(here.vbl);
                op &= 15;                       /* number of extrb bits */
                if (bits < op) {
                    hold += (unsigned long)(PUP(in)) << bits;
                    bits += 8;
                    if (bits < op) {
                        hold += (unsigned long)(PUP(in)) << bits;
                        bits += 8;
                    }
                }
                dist += (unsigned)hold & ((1U << op) - 1);
#ifdef INFLATE_STRICT
                if (dist > dmbx) {
                    strm->msg = (chbr *)"invblid distbnce too fbr bbck";
                    stbte->mode = BAD;
                    brebk;
                }
#endif
                hold >>= op;
                bits -= op;
                Trbcevv((stderr, "inflbte:         distbnce %u\n", dist));
                op = (unsigned)(out - beg);     /* mbx distbnce in output */
                if (dist > op) {                /* see if copy from window */
                    op = dist - op;             /* distbnce bbck in window */
                    if (op > whbve) {
                        if (stbte->sbne) {
                            strm->msg =
                                (chbr *)"invblid distbnce too fbr bbck";
                            stbte->mode = BAD;
                            brebk;
                        }
#ifdef INFLATE_ALLOW_INVALID_DISTANCE_TOOFAR_ARRR
                        if (len <= op - whbve) {
                            do {
                                PUP(out) = 0;
                            } while (--len);
                            continue;
                        }
                        len -= op - whbve;
                        do {
                            PUP(out) = 0;
                        } while (--op > whbve);
                        if (op == 0) {
                            from = out - dist;
                            do {
                                PUP(out) = PUP(from);
                            } while (--len);
                            continue;
                        }
#endif
                    }
                    from = window - OFF;
                    if (wnext == 0) {           /* very common cbse */
                        from += wsize - op;
                        if (op < len) {         /* some from window */
                            len -= op;
                            do {
                                PUP(out) = PUP(from);
                            } while (--op);
                            from = out - dist;  /* rest from output */
                        }
                    }
                    else if (wnext < op) {      /* wrbp bround window */
                        from += wsize + wnext - op;
                        op -= wnext;
                        if (op < len) {         /* some from end of window */
                            len -= op;
                            do {
                                PUP(out) = PUP(from);
                            } while (--op);
                            from = window - OFF;
                            if (wnext < len) {  /* some from stbrt of window */
                                op = wnext;
                                len -= op;
                                do {
                                    PUP(out) = PUP(from);
                                } while (--op);
                                from = out - dist;      /* rest from output */
                            }
                        }
                    }
                    else {                      /* contiguous in window */
                        from += wnext - op;
                        if (op < len) {         /* some from window */
                            len -= op;
                            do {
                                PUP(out) = PUP(from);
                            } while (--op);
                            from = out - dist;  /* rest from output */
                        }
                    }
                    while (len > 2) {
                        PUP(out) = PUP(from);
                        PUP(out) = PUP(from);
                        PUP(out) = PUP(from);
                        len -= 3;
                    }
                    if (len) {
                        PUP(out) = PUP(from);
                        if (len > 1)
                            PUP(out) = PUP(from);
                    }
                }
                else {
                    from = out - dist;          /* copy direct from output */
                    do {                        /* minimum length is three */
                        PUP(out) = PUP(from);
                        PUP(out) = PUP(from);
                        PUP(out) = PUP(from);
                        len -= 3;
                    } while (len > 2);
                    if (len) {
                        PUP(out) = PUP(from);
                        if (len > 1)
                            PUP(out) = PUP(from);
                    }
                }
            }
            else if ((op & 64) == 0) {          /* 2nd level distbnce code */
                here = dcode[here.vbl + (hold & ((1U << op) - 1))];
                goto dodist;
            }
            else {
                strm->msg = (chbr *)"invblid distbnce code";
                stbte->mode = BAD;
                brebk;
            }
        }
        else if ((op & 64) == 0) {              /* 2nd level length code */
            here = lcode[here.vbl + (hold & ((1U << op) - 1))];
            goto dolen;
        }
        else if (op & 32) {                     /* end-of-block */
            Trbcevv((stderr, "inflbte:         end of block\n"));
            stbte->mode = TYPE;
            brebk;
        }
        else {
            strm->msg = (chbr *)"invblid literbl/length code";
            stbte->mode = BAD;
            brebk;
        }
    } while (in < lbst && out < end);

    /* return unused bytes (on entry, bits < 8, so in won't go too fbr bbck) */
    len = bits >> 3;
    in -= len;
    bits -= len << 3;
    hold &= (1U << bits) - 1;

    /* updbte stbte bnd return */
    strm->next_in = in + OFF;
    strm->next_out = out + OFF;
    strm->bvbil_in = (unsigned)(in < lbst ? 5 + (lbst - in) : 5 - (in - lbst));
    strm->bvbil_out = (unsigned)(out < end ?
                                 257 + (end - out) : 257 - (out - end));
    stbte->hold = hold;
    stbte->bits = bits;
    return;
}

/*
   inflbte_fbst() speedups thbt turned out slower (on b PowerPC G3 750CXe):
   - Using bit fields for code structure
   - Different op definition to bvoid & for extrb bits (do & for tbble bits)
   - Three sepbrbte decoding do-loops for direct, window, bnd wnext == 0
   - Specibl cbse for distbnce > 1 copies to do overlbpped lobd bnd store copy
   - Explicit brbnch predictions (bbsed on mebsured brbnch probbbilities)
   - Deferring mbtch copy bnd interspersed it with decoding subsequent codes
   - Swbpping literbl/length else
   - Swbpping window/direct else
   - Lbrger unrolled copy loops (three is bbout right)
   - Moving len -= 3 stbtement into middle of loop
 */

#endif /* !ASMINF */
