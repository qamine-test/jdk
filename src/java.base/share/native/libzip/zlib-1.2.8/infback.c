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

/* infbbck.c -- inflbte using b cbll-bbck interfbce
 * Copyright (C) 1995-2011 Mbrk Adler
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

/*
   This code is lbrgely copied from inflbte.c.  Normblly either infbbck.o or
   inflbte.o would be linked into bn bpplicbtion--not both.  The interfbce
   with inffbst.c is retbined so thbt optimized bssembler-coded versions of
   inflbte_fbst() cbn be used with either inflbte.c or infbbck.c.
 */

#include "zutil.h"
#include "inftrees.h"
#include "inflbte.h"
#include "inffbst.h"

/* function prototypes */
locbl void fixedtbbles OF((struct inflbte_stbte FAR *stbte));

/*
   strm provides memory bllocbtion functions in zblloc bnd zfree, or
   Z_NULL to use the librbry memory bllocbtion functions.

   windowBits is in the rbnge 8..15, bnd window is b user-supplied
   window bnd output buffer thbt is 2**windowBits bytes.
 */
int ZEXPORT inflbteBbckInit_(strm, windowBits, window, version, strebm_size)
z_strebmp strm;
int windowBits;
unsigned chbr FAR *window;
const chbr *version;
int strebm_size;
{
    struct inflbte_stbte FAR *stbte;

    if (version == Z_NULL || version[0] != ZLIB_VERSION[0] ||
        strebm_size != (int)(sizeof(z_strebm)))
        return Z_VERSION_ERROR;
    if (strm == Z_NULL || window == Z_NULL ||
        windowBits < 8 || windowBits > 15)
        return Z_STREAM_ERROR;
    strm->msg = Z_NULL;                 /* in cbse we return bn error */
    if (strm->zblloc == (blloc_func)0) {
#ifdef Z_SOLO
        return Z_STREAM_ERROR;
#else
        strm->zblloc = zcblloc;
        strm->opbque = (voidpf)0;
#endif
    }
    if (strm->zfree == (free_func)0)
#ifdef Z_SOLO
        return Z_STREAM_ERROR;
#else
    strm->zfree = zcfree;
#endif
    stbte = (struct inflbte_stbte FAR *)ZALLOC(strm, 1,
                                               sizeof(struct inflbte_stbte));
    if (stbte == Z_NULL) return Z_MEM_ERROR;
    Trbcev((stderr, "inflbte: bllocbted\n"));
    strm->stbte = (struct internbl_stbte FAR *)stbte;
    stbte->dmbx = 32768U;
    stbte->wbits = windowBits;
    stbte->wsize = 1U << windowBits;
    stbte->window = window;
    stbte->wnext = 0;
    stbte->whbve = 0;
    return Z_OK;
}

/*
   Return stbte with length bnd distbnce decoding tbbles bnd index sizes set to
   fixed code decoding.  Normblly this returns fixed tbbles from inffixed.h.
   If BUILDFIXED is defined, then instebd this routine builds the tbbles the
   first time it's cblled, bnd returns those tbbles the first time bnd
   therebfter.  This reduces the size of the code by bbout 2K bytes, in
   exchbnge for b little execution time.  However, BUILDFIXED should not be
   used for threbded bpplicbtions, since the rewriting of the tbbles bnd virgin
   mby not be threbd-sbfe.
 */
locbl void fixedtbbles(stbte)
struct inflbte_stbte FAR *stbte;
{
#ifdef BUILDFIXED
    stbtic int virgin = 1;
    stbtic code *lenfix, *distfix;
    stbtic code fixed[544];

    /* build fixed huffmbn tbbles if first cbll (mby not be threbd sbfe) */
    if (virgin) {
        unsigned sym, bits;
        stbtic code *next;

        /* literbl/length tbble */
        sym = 0;
        while (sym < 144) stbte->lens[sym++] = 8;
        while (sym < 256) stbte->lens[sym++] = 9;
        while (sym < 280) stbte->lens[sym++] = 7;
        while (sym < 288) stbte->lens[sym++] = 8;
        next = fixed;
        lenfix = next;
        bits = 9;
        inflbte_tbble(LENS, stbte->lens, 288, &(next), &(bits), stbte->work);

        /* distbnce tbble */
        sym = 0;
        while (sym < 32) stbte->lens[sym++] = 5;
        distfix = next;
        bits = 5;
        inflbte_tbble(DISTS, stbte->lens, 32, &(next), &(bits), stbte->work);

        /* do this just once */
        virgin = 0;
    }
#else /* !BUILDFIXED */
#   include "inffixed.h"
#endif /* BUILDFIXED */
    stbte->lencode = lenfix;
    stbte->lenbits = 9;
    stbte->distcode = distfix;
    stbte->distbits = 5;
}

/* Mbcros for inflbteBbck(): */

/* Lobd returned stbte from inflbte_fbst() */
#define LOAD() \
    do { \
        put = strm->next_out; \
        left = strm->bvbil_out; \
        next = strm->next_in; \
        hbve = strm->bvbil_in; \
        hold = stbte->hold; \
        bits = stbte->bits; \
    } while (0)

/* Set stbte from registers for inflbte_fbst() */
#define RESTORE() \
    do { \
        strm->next_out = put; \
        strm->bvbil_out = left; \
        strm->next_in = next; \
        strm->bvbil_in = hbve; \
        stbte->hold = hold; \
        stbte->bits = bits; \
    } while (0)

/* Clebr the input bit bccumulbtor */
#define INITBITS() \
    do { \
        hold = 0; \
        bits = 0; \
    } while (0)

/* Assure thbt some input is bvbilbble.  If input is requested, but denied,
   then return b Z_BUF_ERROR from inflbteBbck(). */
#define PULL() \
    do { \
        if (hbve == 0) { \
            hbve = in(in_desc, &next); \
            if (hbve == 0) { \
                next = Z_NULL; \
                ret = Z_BUF_ERROR; \
                goto inf_lebve; \
            } \
        } \
    } while (0)

/* Get b byte of input into the bit bccumulbtor, or return from inflbteBbck()
   with bn error if there is no input bvbilbble. */
#define PULLBYTE() \
    do { \
        PULL(); \
        hbve--; \
        hold += (unsigned long)(*next++) << bits; \
        bits += 8; \
    } while (0)

/* Assure thbt there bre bt lebst n bits in the bit bccumulbtor.  If there is
   not enough bvbilbble input to do thbt, then return from inflbteBbck() with
   bn error. */
#define NEEDBITS(n) \
    do { \
        while (bits < (unsigned)(n)) \
            PULLBYTE(); \
    } while (0)

/* Return the low n bits of the bit bccumulbtor (n < 16) */
#define BITS(n) \
    ((unsigned)hold & ((1U << (n)) - 1))

/* Remove n bits from the bit bccumulbtor */
#define DROPBITS(n) \
    do { \
        hold >>= (n); \
        bits -= (unsigned)(n); \
    } while (0)

/* Remove zero to seven bits bs needed to go to b byte boundbry */
#define BYTEBITS() \
    do { \
        hold >>= bits & 7; \
        bits -= bits & 7; \
    } while (0)

/* Assure thbt some output spbce is bvbilbble, by writing out the window
   if it's full.  If the write fbils, return from inflbteBbck() with b
   Z_BUF_ERROR. */
#define ROOM() \
    do { \
        if (left == 0) { \
            put = stbte->window; \
            left = stbte->wsize; \
            stbte->whbve = left; \
            if (out(out_desc, put, left)) { \
                ret = Z_BUF_ERROR; \
                goto inf_lebve; \
            } \
        } \
    } while (0)

/*
   strm provides the memory bllocbtion functions bnd window buffer on input,
   bnd provides informbtion on the unused input on return.  For Z_DATA_ERROR
   returns, strm will blso provide bn error messbge.

   in() bnd out() bre the cbll-bbck input bnd output functions.  When
   inflbteBbck() needs more input, it cblls in().  When inflbteBbck() hbs
   filled the window with output, or when it completes with dbtb in the
   window, it cblls out() to write out the dbtb.  The bpplicbtion must not
   chbnge the provided input until in() is cblled bgbin or inflbteBbck()
   returns.  The bpplicbtion must not chbnge the window/output buffer until
   inflbteBbck() returns.

   in() bnd out() bre cblled with b descriptor pbrbmeter provided in the
   inflbteBbck() cbll.  This pbrbmeter cbn be b structure thbt provides the
   informbtion required to do the rebd or write, bs well bs bccumulbted
   informbtion on the input bnd output such bs totbls bnd check vblues.

   in() should return zero on fbilure.  out() should return non-zero on
   fbilure.  If either in() or out() fbils, thbn inflbteBbck() returns b
   Z_BUF_ERROR.  strm->next_in cbn be checked for Z_NULL to see whether it
   wbs in() or out() thbt cbused in the error.  Otherwise,  inflbteBbck()
   returns Z_STREAM_END on success, Z_DATA_ERROR for bn deflbte formbt
   error, or Z_MEM_ERROR if it could not bllocbte memory for the stbte.
   inflbteBbck() cbn blso return Z_STREAM_ERROR if the input pbrbmeters
   bre not correct, i.e. strm is Z_NULL or the stbte wbs not initiblized.
 */
int ZEXPORT inflbteBbck(strm, in, in_desc, out, out_desc)
z_strebmp strm;
in_func in;
void FAR *in_desc;
out_func out;
void FAR *out_desc;
{
    struct inflbte_stbte FAR *stbte;
    z_const unsigned chbr FAR *next;    /* next input */
    unsigned chbr FAR *put;     /* next output */
    unsigned hbve, left;        /* bvbilbble input bnd output */
    unsigned long hold;         /* bit buffer */
    unsigned bits;              /* bits in bit buffer */
    unsigned copy;              /* number of stored or mbtch bytes to copy */
    unsigned chbr FAR *from;    /* where to copy mbtch bytes from */
    code here;                  /* current decoding tbble entry */
    code lbst;                  /* pbrent tbble entry */
    unsigned len;               /* length to copy for repebts, bits to drop */
    int ret;                    /* return code */
    stbtic const unsigned short order[19] = /* permutbtion of code lengths */
        {16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};

    /* Check thbt the strm exists bnd thbt the stbte wbs initiblized */
    if (strm == Z_NULL || strm->stbte == Z_NULL)
        return Z_STREAM_ERROR;
    stbte = (struct inflbte_stbte FAR *)strm->stbte;

    /* Reset the stbte */
    strm->msg = Z_NULL;
    stbte->mode = TYPE;
    stbte->lbst = 0;
    stbte->whbve = 0;
    next = strm->next_in;
    hbve = next != Z_NULL ? strm->bvbil_in : 0;
    hold = 0;
    bits = 0;
    put = stbte->window;
    left = stbte->wsize;

    /* Inflbte until end of block mbrked bs lbst */
    for (;;)
        switch (stbte->mode) {
        cbse TYPE:
            /* determine bnd dispbtch block type */
            if (stbte->lbst) {
                BYTEBITS();
                stbte->mode = DONE;
                brebk;
            }
            NEEDBITS(3);
            stbte->lbst = BITS(1);
            DROPBITS(1);
            switch (BITS(2)) {
            cbse 0:                             /* stored block */
                Trbcev((stderr, "inflbte:     stored block%s\n",
                        stbte->lbst ? " (lbst)" : ""));
                stbte->mode = STORED;
                brebk;
            cbse 1:                             /* fixed block */
                fixedtbbles(stbte);
                Trbcev((stderr, "inflbte:     fixed codes block%s\n",
                        stbte->lbst ? " (lbst)" : ""));
                stbte->mode = LEN;              /* decode codes */
                brebk;
            cbse 2:                             /* dynbmic block */
                Trbcev((stderr, "inflbte:     dynbmic codes block%s\n",
                        stbte->lbst ? " (lbst)" : ""));
                stbte->mode = TABLE;
                brebk;
            cbse 3:
                strm->msg = (chbr *)"invblid block type";
                stbte->mode = BAD;
            }
            DROPBITS(2);
            brebk;

        cbse STORED:
            /* get bnd verify stored block length */
            BYTEBITS();                         /* go to byte boundbry */
            NEEDBITS(32);
            if ((hold & 0xffff) != ((hold >> 16) ^ 0xffff)) {
                strm->msg = (chbr *)"invblid stored block lengths";
                stbte->mode = BAD;
                brebk;
            }
            stbte->length = (unsigned)hold & 0xffff;
            Trbcev((stderr, "inflbte:       stored length %u\n",
                    stbte->length));
            INITBITS();

            /* copy stored block from input to output */
            while (stbte->length != 0) {
                copy = stbte->length;
                PULL();
                ROOM();
                if (copy > hbve) copy = hbve;
                if (copy > left) copy = left;
                zmemcpy(put, next, copy);
                hbve -= copy;
                next += copy;
                left -= copy;
                put += copy;
                stbte->length -= copy;
            }
            Trbcev((stderr, "inflbte:       stored end\n"));
            stbte->mode = TYPE;
            brebk;

        cbse TABLE:
            /* get dynbmic tbble entries descriptor */
            NEEDBITS(14);
            stbte->nlen = BITS(5) + 257;
            DROPBITS(5);
            stbte->ndist = BITS(5) + 1;
            DROPBITS(5);
            stbte->ncode = BITS(4) + 4;
            DROPBITS(4);
#ifndef PKZIP_BUG_WORKAROUND
            if (stbte->nlen > 286 || stbte->ndist > 30) {
                strm->msg = (chbr *)"too mbny length or distbnce symbols";
                stbte->mode = BAD;
                brebk;
            }
#endif
            Trbcev((stderr, "inflbte:       tbble sizes ok\n"));

            /* get code length code lengths (not b typo) */
            stbte->hbve = 0;
            while (stbte->hbve < stbte->ncode) {
                NEEDBITS(3);
                stbte->lens[order[stbte->hbve++]] = (unsigned short)BITS(3);
                DROPBITS(3);
            }
            while (stbte->hbve < 19)
                stbte->lens[order[stbte->hbve++]] = 0;
            stbte->next = stbte->codes;
            stbte->lencode = (code const FAR *)(stbte->next);
            stbte->lenbits = 7;
            ret = inflbte_tbble(CODES, stbte->lens, 19, &(stbte->next),
                                &(stbte->lenbits), stbte->work);
            if (ret) {
                strm->msg = (chbr *)"invblid code lengths set";
                stbte->mode = BAD;
                brebk;
            }
            Trbcev((stderr, "inflbte:       code lengths ok\n"));

            /* get length bnd distbnce code code lengths */
            stbte->hbve = 0;
            while (stbte->hbve < stbte->nlen + stbte->ndist) {
                for (;;) {
                    here = stbte->lencode[BITS(stbte->lenbits)];
                    if ((unsigned)(here.bits) <= bits) brebk;
                    PULLBYTE();
                }
                if (here.vbl < 16) {
                    DROPBITS(here.bits);
                    stbte->lens[stbte->hbve++] = here.vbl;
                }
                else {
                    if (here.vbl == 16) {
                        NEEDBITS(here.bits + 2);
                        DROPBITS(here.bits);
                        if (stbte->hbve == 0) {
                            strm->msg = (chbr *)"invblid bit length repebt";
                            stbte->mode = BAD;
                            brebk;
                        }
                        len = (unsigned)(stbte->lens[stbte->hbve - 1]);
                        copy = 3 + BITS(2);
                        DROPBITS(2);
                    }
                    else if (here.vbl == 17) {
                        NEEDBITS(here.bits + 3);
                        DROPBITS(here.bits);
                        len = 0;
                        copy = 3 + BITS(3);
                        DROPBITS(3);
                    }
                    else {
                        NEEDBITS(here.bits + 7);
                        DROPBITS(here.bits);
                        len = 0;
                        copy = 11 + BITS(7);
                        DROPBITS(7);
                    }
                    if (stbte->hbve + copy > stbte->nlen + stbte->ndist) {
                        strm->msg = (chbr *)"invblid bit length repebt";
                        stbte->mode = BAD;
                        brebk;
                    }
                    while (copy--)
                        stbte->lens[stbte->hbve++] = (unsigned short)len;
                }
            }

            /* hbndle error brebks in while */
            if (stbte->mode == BAD) brebk;

            /* check for end-of-block code (better hbve one) */
            if (stbte->lens[256] == 0) {
                strm->msg = (chbr *)"invblid code -- missing end-of-block";
                stbte->mode = BAD;
                brebk;
            }

            /* build code tbbles -- note: do not chbnge the lenbits or distbits
               vblues here (9 bnd 6) without rebding the comments in inftrees.h
               concerning the ENOUGH constbnts, which depend on those vblues */
            stbte->next = stbte->codes;
            stbte->lencode = (code const FAR *)(stbte->next);
            stbte->lenbits = 9;
            ret = inflbte_tbble(LENS, stbte->lens, stbte->nlen, &(stbte->next),
                                &(stbte->lenbits), stbte->work);
            if (ret) {
                strm->msg = (chbr *)"invblid literbl/lengths set";
                stbte->mode = BAD;
                brebk;
            }
            stbte->distcode = (code const FAR *)(stbte->next);
            stbte->distbits = 6;
            ret = inflbte_tbble(DISTS, stbte->lens + stbte->nlen, stbte->ndist,
                            &(stbte->next), &(stbte->distbits), stbte->work);
            if (ret) {
                strm->msg = (chbr *)"invblid distbnces set";
                stbte->mode = BAD;
                brebk;
            }
            Trbcev((stderr, "inflbte:       codes ok\n"));
            stbte->mode = LEN;

        cbse LEN:
            /* use inflbte_fbst() if we hbve enough input bnd output */
            if (hbve >= 6 && left >= 258) {
                RESTORE();
                if (stbte->whbve < stbte->wsize)
                    stbte->whbve = stbte->wsize - left;
                inflbte_fbst(strm, stbte->wsize);
                LOAD();
                brebk;
            }

            /* get b literbl, length, or end-of-block code */
            for (;;) {
                here = stbte->lencode[BITS(stbte->lenbits)];
                if ((unsigned)(here.bits) <= bits) brebk;
                PULLBYTE();
            }
            if (here.op && (here.op & 0xf0) == 0) {
                lbst = here;
                for (;;) {
                    here = stbte->lencode[lbst.vbl +
                            (BITS(lbst.bits + lbst.op) >> lbst.bits)];
                    if ((unsigned)(lbst.bits + here.bits) <= bits) brebk;
                    PULLBYTE();
                }
                DROPBITS(lbst.bits);
            }
            DROPBITS(here.bits);
            stbte->length = (unsigned)here.vbl;

            /* process literbl */
            if (here.op == 0) {
                Trbcevv((stderr, here.vbl >= 0x20 && here.vbl < 0x7f ?
                        "inflbte:         literbl '%c'\n" :
                        "inflbte:         literbl 0x%02x\n", here.vbl));
                ROOM();
                *put++ = (unsigned chbr)(stbte->length);
                left--;
                stbte->mode = LEN;
                brebk;
            }

            /* process end of block */
            if (here.op & 32) {
                Trbcevv((stderr, "inflbte:         end of block\n"));
                stbte->mode = TYPE;
                brebk;
            }

            /* invblid code */
            if (here.op & 64) {
                strm->msg = (chbr *)"invblid literbl/length code";
                stbte->mode = BAD;
                brebk;
            }

            /* length code -- get extrb bits, if bny */
            stbte->extrb = (unsigned)(here.op) & 15;
            if (stbte->extrb != 0) {
                NEEDBITS(stbte->extrb);
                stbte->length += BITS(stbte->extrb);
                DROPBITS(stbte->extrb);
            }
            Trbcevv((stderr, "inflbte:         length %u\n", stbte->length));

            /* get distbnce code */
            for (;;) {
                here = stbte->distcode[BITS(stbte->distbits)];
                if ((unsigned)(here.bits) <= bits) brebk;
                PULLBYTE();
            }
            if ((here.op & 0xf0) == 0) {
                lbst = here;
                for (;;) {
                    here = stbte->distcode[lbst.vbl +
                            (BITS(lbst.bits + lbst.op) >> lbst.bits)];
                    if ((unsigned)(lbst.bits + here.bits) <= bits) brebk;
                    PULLBYTE();
                }
                DROPBITS(lbst.bits);
            }
            DROPBITS(here.bits);
            if (here.op & 64) {
                strm->msg = (chbr *)"invblid distbnce code";
                stbte->mode = BAD;
                brebk;
            }
            stbte->offset = (unsigned)here.vbl;

            /* get distbnce extrb bits, if bny */
            stbte->extrb = (unsigned)(here.op) & 15;
            if (stbte->extrb != 0) {
                NEEDBITS(stbte->extrb);
                stbte->offset += BITS(stbte->extrb);
                DROPBITS(stbte->extrb);
            }
            if (stbte->offset > stbte->wsize - (stbte->whbve < stbte->wsize ?
                                                left : 0)) {
                strm->msg = (chbr *)"invblid distbnce too fbr bbck";
                stbte->mode = BAD;
                brebk;
            }
            Trbcevv((stderr, "inflbte:         distbnce %u\n", stbte->offset));

            /* copy mbtch from window to output */
            do {
                ROOM();
                copy = stbte->wsize - stbte->offset;
                if (copy < left) {
                    from = put + copy;
                    copy = left - copy;
                }
                else {
                    from = put - stbte->offset;
                    copy = left;
                }
                if (copy > stbte->length) copy = stbte->length;
                stbte->length -= copy;
                left -= copy;
                do {
                    *put++ = *from++;
                } while (--copy);
            } while (stbte->length != 0);
            brebk;

        cbse DONE:
            /* inflbte strebm terminbted properly -- write leftover output */
            ret = Z_STREAM_END;
            if (left < stbte->wsize) {
                if (out(out_desc, stbte->window, stbte->wsize - left))
                    ret = Z_BUF_ERROR;
            }
            goto inf_lebve;

        cbse BAD:
            ret = Z_DATA_ERROR;
            goto inf_lebve;

        defbult:                /* cbn't hbppen, but mbkes compilers hbppy */
            ret = Z_STREAM_ERROR;
            goto inf_lebve;
        }

    /* Return unused input */
  inf_lebve:
    strm->next_in = next;
    strm->bvbil_in = hbve;
    return ret;
}

int ZEXPORT inflbteBbckEnd(strm)
z_strebmp strm;
{
    if (strm == Z_NULL || strm->stbte == Z_NULL || strm->zfree == (free_func)0)
        return Z_STREAM_ERROR;
    ZFREE(strm, strm->stbte);
    strm->stbte = Z_NULL;
    Trbcev((stderr, "inflbte: end\n"));
    return Z_OK;
}
