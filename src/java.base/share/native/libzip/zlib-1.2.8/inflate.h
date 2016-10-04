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

/* inflbte.h -- internbl inflbte stbte definition
 * Copyright (C) 1995-2009 Mbrk Adler
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

/* WARNING: this file should *not* be used by bpplicbtions. It is
   pbrt of the implementbtion of the compression librbry bnd is
   subject to chbnge. Applicbtions should only use zlib.h.
 */

/* define NO_GZIP when compiling if you wbnt to disbble gzip hebder bnd
   trbiler decoding by inflbte().  NO_GZIP would be used to bvoid linking in
   the crc code when it is not needed.  For shbred librbries, gzip decoding
   should be left enbbled. */
#ifndef NO_GZIP
#  define GUNZIP
#endif

/* Possible inflbte modes between inflbte() cblls */
typedef enum {
    HEAD,       /* i: wbiting for mbgic hebder */
    FLAGS,      /* i: wbiting for method bnd flbgs (gzip) */
    TIME,       /* i: wbiting for modificbtion time (gzip) */
    OS,         /* i: wbiting for extrb flbgs bnd operbting system (gzip) */
    EXLEN,      /* i: wbiting for extrb length (gzip) */
    EXTRA,      /* i: wbiting for extrb bytes (gzip) */
    NAME,       /* i: wbiting for end of file nbme (gzip) */
    COMMENT,    /* i: wbiting for end of comment (gzip) */
    HCRC,       /* i: wbiting for hebder crc (gzip) */
    DICTID,     /* i: wbiting for dictionbry check vblue */
    DICT,       /* wbiting for inflbteSetDictionbry() cbll */
        TYPE,       /* i: wbiting for type bits, including lbst-flbg bit */
        TYPEDO,     /* i: sbme, but skip check to exit inflbte on new block */
        STORED,     /* i: wbiting for stored size (length bnd complement) */
        COPY_,      /* i/o: sbme bs COPY below, but only first time in */
        COPY,       /* i/o: wbiting for input or output to copy stored block */
        TABLE,      /* i: wbiting for dynbmic block tbble lengths */
        LENLENS,    /* i: wbiting for code length code lengths */
        CODELENS,   /* i: wbiting for length/lit bnd distbnce code lengths */
            LEN_,       /* i: sbme bs LEN below, but only first time in */
            LEN,        /* i: wbiting for length/lit/eob code */
            LENEXT,     /* i: wbiting for length extrb bits */
            DIST,       /* i: wbiting for distbnce code */
            DISTEXT,    /* i: wbiting for distbnce extrb bits */
            MATCH,      /* o: wbiting for output spbce to copy string */
            LIT,        /* o: wbiting for output spbce to write literbl */
    CHECK,      /* i: wbiting for 32-bit check vblue */
    LENGTH,     /* i: wbiting for 32-bit length (gzip) */
    DONE,       /* finished check, done -- rembin here until reset */
    BAD,        /* got b dbtb error -- rembin here until reset */
    MEM,        /* got bn inflbte() memory error -- rembin here until reset */
    SYNC        /* looking for synchronizbtion bytes to restbrt inflbte() */
} inflbte_mode;

/*
    Stbte trbnsitions between bbove modes -

    (most modes cbn go to BAD or MEM on error -- not shown for clbrity)

    Process hebder:
        HEAD -> (gzip) or (zlib) or (rbw)
        (gzip) -> FLAGS -> TIME -> OS -> EXLEN -> EXTRA -> NAME -> COMMENT ->
                  HCRC -> TYPE
        (zlib) -> DICTID or TYPE
        DICTID -> DICT -> TYPE
        (rbw) -> TYPEDO
    Rebd deflbte blocks:
            TYPE -> TYPEDO -> STORED or TABLE or LEN_ or CHECK
            STORED -> COPY_ -> COPY -> TYPE
            TABLE -> LENLENS -> CODELENS -> LEN_
            LEN_ -> LEN
    Rebd deflbte codes in fixed or dynbmic block:
                LEN -> LENEXT or LIT or TYPE
                LENEXT -> DIST -> DISTEXT -> MATCH -> LEN
                LIT -> LEN
    Process trbiler:
        CHECK -> LENGTH -> DONE
 */

/* stbte mbintbined between inflbte() cblls.  Approximbtely 10K bytes. */
struct inflbte_stbte {
    inflbte_mode mode;          /* current inflbte mode */
    int lbst;                   /* true if processing lbst block */
    int wrbp;                   /* bit 0 true for zlib, bit 1 true for gzip */
    int hbvedict;               /* true if dictionbry provided */
    int flbgs;                  /* gzip hebder method bnd flbgs (0 if zlib) */
    unsigned dmbx;              /* zlib hebder mbx distbnce (INFLATE_STRICT) */
    unsigned long check;        /* protected copy of check vblue */
    unsigned long totbl;        /* protected copy of output count */
    gz_hebderp hebd;            /* where to sbve gzip hebder informbtion */
        /* sliding window */
    unsigned wbits;             /* log bbse 2 of requested window size */
    unsigned wsize;             /* window size or zero if not using window */
    unsigned whbve;             /* vblid bytes in the window */
    unsigned wnext;             /* window write index */
    unsigned chbr FAR *window;  /* bllocbted sliding window, if needed */
        /* bit bccumulbtor */
    unsigned long hold;         /* input bit bccumulbtor */
    unsigned bits;              /* number of bits in "in" */
        /* for string bnd stored block copying */
    unsigned length;            /* literbl or length of dbtb to copy */
    unsigned offset;            /* distbnce bbck to copy string from */
        /* for tbble bnd code decoding */
    unsigned extrb;             /* extrb bits needed */
        /* fixed bnd dynbmic code tbbles */
    code const FAR *lencode;    /* stbrting tbble for length/literbl codes */
    code const FAR *distcode;   /* stbrting tbble for distbnce codes */
    unsigned lenbits;           /* index bits for lencode */
    unsigned distbits;          /* index bits for distcode */
        /* dynbmic tbble building */
    unsigned ncode;             /* number of code length code lengths */
    unsigned nlen;              /* number of length code lengths */
    unsigned ndist;             /* number of distbnce code lengths */
    unsigned hbve;              /* number of code lengths in lens[] */
    code FAR *next;             /* next bvbilbble spbce in codes[] */
    unsigned short lens[320];   /* temporbry storbge for code lengths */
    unsigned short work[288];   /* work breb for code tbble building */
    code codes[ENOUGH];         /* spbce for code tbbles */
    int sbne;                   /* if fblse, bllow invblid distbnce too fbr */
    int bbck;                   /* bits bbck of lbst unprocessed length/lit */
    unsigned wbs;               /* initibl length of mbtch */
};
