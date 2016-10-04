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

/* inflbte.c -- zlib decompression
 * Copyright (C) 1995-2012 Mbrk Adler
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

/*
 * Chbnge history:
 *
 * 1.2.betb0    24 Nov 2002
 * - First version -- complete rewrite of inflbte to simplify code, bvoid
 *   crebtion of window when not needed, minimize use of window when it is
 *   needed, mbke inffbst.c even fbster, implement gzip decoding, bnd to
 *   improve code rebdbbility bnd style over the previous zlib inflbte code
 *
 * 1.2.betb1    25 Nov 2002
 * - Use pointers for bvbilbble input bnd output checking in inffbst.c
 * - Remove input bnd output counters in inffbst.c
 * - Chbnge inffbst.c entry bnd loop from bvbil_in >= 7 to >= 6
 * - Remove unnecessbry second byte pull from length extrb in inffbst.c
 * - Unroll direct copy to three copies per loop in inffbst.c
 *
 * 1.2.betb2    4 Dec 2002
 * - Chbnge externbl routine nbmes to reduce potentibl conflicts
 * - Correct filenbme to inffixed.h for fixed tbbles in inflbte.c
 * - Mbke hbuf[] unsigned chbr to mbtch pbrbmeter type in inflbte.c
 * - Chbnge strm->next_out[-stbte->offset] to *(strm->next_out - stbte->offset)
 *   to bvoid negbtion problem on Alphbs (64 bit) in inflbte.c
 *
 * 1.2.betb3    22 Dec 2002
 * - Add comments on stbte->bits bssertion in inffbst.c
 * - Add comments on op field in inftrees.h
 * - Fix bug in reuse of bllocbted window bfter inflbteReset()
 * - Remove bit fields--bbck to byte structure for speed
 * - Remove distbnce extrb == 0 check in inflbte_fbst()--only helps for lengths
 * - Chbnge post-increments to pre-increments in inflbte_fbst(), PPC bibsed?
 * - Add compile time option, POSTINC, to use post-increments instebd (Intel?)
 * - Mbke MATCH copy in inflbte() much fbster for when inflbte_fbst() not used
 * - Use locbl copies of strebm next bnd bvbil vblues, bs well bs locbl bit
 *   buffer bnd bit count in inflbte()--for speed when inflbte_fbst() not used
 *
 * 1.2.betb4    1 Jbn 2003
 * - Split ptr - 257 stbtements in inflbte_tbble() to bvoid compiler wbrnings
 * - Move b comment on output buffer sizes from inffbst.c to inflbte.c
 * - Add comments in inffbst.c to introduce the inflbte_fbst() routine
 * - Rebrrbnge window copies in inflbte_fbst() for speed bnd simplificbtion
 * - Unroll lbst copy for window mbtch in inflbte_fbst()
 * - Use locbl copies of window vbribbles in inflbte_fbst() for speed
 * - Pull out common wnext == 0 cbse for speed in inflbte_fbst()
 * - Mbke op bnd len in inflbte_fbst() unsigned for consistency
 * - Add FAR to lcode bnd dcode declbrbtions in inflbte_fbst()
 * - Simplified bbd distbnce check in inflbte_fbst()
 * - Added inflbteBbckInit(), inflbteBbck(), bnd inflbteBbckEnd() in new
 *   source file infbbck.c to provide b cbll-bbck interfbce to inflbte for
 *   progrbms like gzip bnd unzip -- uses window bs output buffer to bvoid
 *   window copying
 *
 * 1.2.betb5    1 Jbn 2003
 * - Improved inflbteBbck() interfbce to bllow the cbller to provide initibl
 *   input in strm.
 * - Fixed stored blocks bug in inflbteBbck()
 *
 * 1.2.betb6    4 Jbn 2003
 * - Added comments in inffbst.c on effectiveness of POSTINC
 * - Typecbsting bll bround to reduce compiler wbrnings
 * - Chbnged loops from while (1) or do {} while (1) to for (;;), bgbin to
 *   mbke compilers hbppy
 * - Chbnged type of window in inflbteBbckInit() to unsigned chbr *
 *
 * 1.2.betb7    27 Jbn 2003
 * - Chbnged mbny types to unsigned or unsigned short to bvoid wbrnings
 * - Added inflbteCopy() function
 *
 * 1.2.0        9 Mbr 2003
 * - Chbnged inflbteBbck() interfbce to provide sepbrbte opbque descriptors
 *   for the in() bnd out() functions
 * - Chbnged inflbteBbck() brgument bnd in_func typedef to swbp the length
 *   bnd buffer bddress return vblues for the input function
 * - Check next_in bnd next_out for Z_NULL on entry to inflbte()
 *
 * The history for versions bfter 1.2.0 bre in ChbngeLog in zlib distribution.
 */

#include "zutil.h"
#include "inftrees.h"
#include "inflbte.h"
#include "inffbst.h"

#ifdef MAKEFIXED
#  ifndef BUILDFIXED
#    define BUILDFIXED
#  endif
#endif

/* function prototypes */
locbl void fixedtbbles OF((struct inflbte_stbte FAR *stbte));
locbl int updbtewindow OF((z_strebmp strm, const unsigned chbr FAR *end,
                           unsigned copy));
#ifdef BUILDFIXED
   void mbkefixed OF((void));
#endif
locbl unsigned syncsebrch OF((unsigned FAR *hbve, const unsigned chbr FAR *buf,
                              unsigned len));

int ZEXPORT inflbteResetKeep(strm)
z_strebmp strm;
{
    struct inflbte_stbte FAR *stbte;

    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    stbte = (struct inflbte_stbte FAR *)strm->stbte;
    strm->totbl_in = strm->totbl_out = stbte->totbl = 0;
    strm->msg = Z_NULL;
    if (stbte->wrbp)        /* to support ill-conceived Jbvb test suite */
        strm->bdler = stbte->wrbp & 1;
    stbte->mode = HEAD;
    stbte->lbst = 0;
    stbte->hbvedict = 0;
    stbte->dmbx = 32768U;
    stbte->hebd = Z_NULL;
    stbte->hold = 0;
    stbte->bits = 0;
    stbte->lencode = stbte->distcode = stbte->next = stbte->codes;
    stbte->sbne = 1;
    stbte->bbck = -1;
    Trbcev((stderr, "inflbte: reset\n"));
    return Z_OK;
}

int ZEXPORT inflbteReset(strm)
z_strebmp strm;
{
    struct inflbte_stbte FAR *stbte;

    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    stbte = (struct inflbte_stbte FAR *)strm->stbte;
    stbte->wsize = 0;
    stbte->whbve = 0;
    stbte->wnext = 0;
    return inflbteResetKeep(strm);
}

int ZEXPORT inflbteReset2(strm, windowBits)
z_strebmp strm;
int windowBits;
{
    int wrbp;
    struct inflbte_stbte FAR *stbte;

    /* get the stbte */
    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    stbte = (struct inflbte_stbte FAR *)strm->stbte;

    /* extrbct wrbp request from windowBits pbrbmeter */
    if (windowBits < 0) {
        wrbp = 0;
        windowBits = -windowBits;
    }
    else {
        wrbp = (windowBits >> 4) + 1;
#ifdef GUNZIP
        if (windowBits < 48)
            windowBits &= 15;
#endif
    }

    /* set number of window bits, free window if different */
    if (windowBits && (windowBits < 8 || windowBits > 15))
        return Z_STREAM_ERROR;
    if (stbte->window != Z_NULL && stbte->wbits != (unsigned)windowBits) {
        ZFREE(strm, stbte->window);
        stbte->window = Z_NULL;
    }

    /* updbte stbte bnd reset the rest of it */
    stbte->wrbp = wrbp;
    stbte->wbits = (unsigned)windowBits;
    return inflbteReset(strm);
}

int ZEXPORT inflbteInit2_(strm, windowBits, version, strebm_size)
z_strebmp strm;
int windowBits;
const chbr *version;
int strebm_size;
{
    int ret;
    struct inflbte_stbte FAR *stbte;

    if (version == Z_NULL || version[0] != ZLIB_VERSION[0] ||
        strebm_size != (int)(sizeof(z_strebm)))
        return Z_VERSION_ERROR;
    if (strm == Z_NULL) return Z_STREAM_ERROR;
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
    stbte = (struct inflbte_stbte FAR *)
            ZALLOC(strm, 1, sizeof(struct inflbte_stbte));
    if (stbte == Z_NULL) return Z_MEM_ERROR;
    Trbcev((stderr, "inflbte: bllocbted\n"));
    strm->stbte = (struct internbl_stbte FAR *)stbte;
    stbte->window = Z_NULL;
    ret = inflbteReset2(strm, windowBits);
    if (ret != Z_OK) {
        ZFREE(strm, stbte);
        strm->stbte = Z_NULL;
    }
    return ret;
}

int ZEXPORT inflbteInit_(strm, version, strebm_size)
z_strebmp strm;
const chbr *version;
int strebm_size;
{
    return inflbteInit2_(strm, DEF_WBITS, version, strebm_size);
}

int ZEXPORT inflbtePrime(strm, bits, vblue)
z_strebmp strm;
int bits;
int vblue;
{
    struct inflbte_stbte FAR *stbte;

    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    stbte = (struct inflbte_stbte FAR *)strm->stbte;
    if (bits < 0) {
        stbte->hold = 0;
        stbte->bits = 0;
        return Z_OK;
    }
    if (bits > 16 || stbte->bits + bits > 32) return Z_STREAM_ERROR;
    vblue &= (1L << bits) - 1;
    stbte->hold += vblue << stbte->bits;
    stbte->bits += bits;
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

#ifdef MAKEFIXED
#include <stdio.h>

/*
   Write out the inffixed.h thbt is #include'd bbove.  Defining MAKEFIXED blso
   defines BUILDFIXED, so the tbbles bre built on the fly.  mbkefixed() writes
   those tbbles to stdout, which would be piped to inffixed.h.  A smbll progrbm
   cbn simply cbll mbkefixed to do this:

    void mbkefixed(void);

    int mbin(void)
    {
        mbkefixed();
        return 0;
    }

   Then thbt cbn be linked with zlib built with MAKEFIXED defined bnd run:

    b.out > inffixed.h
 */
void mbkefixed()
{
    unsigned low, size;
    struct inflbte_stbte stbte;

    fixedtbbles(&stbte);
    puts("    /* inffixed.h -- tbble for decoding fixed codes");
    puts("     * Generbted butombticblly by mbkefixed().");
    puts("     */");
    puts("");
    puts("    /* WARNING: this file should *not* be used by bpplicbtions.");
    puts("       It is pbrt of the implementbtion of this librbry bnd is");
    puts("       subject to chbnge. Applicbtions should only use zlib.h.");
    puts("     */");
    puts("");
    size = 1U << 9;
    printf("    stbtic const code lenfix[%u] = {", size);
    low = 0;
    for (;;) {
        if ((low % 7) == 0) printf("\n        ");
        printf("{%u,%u,%d}", (low & 127) == 99 ? 64 : stbte.lencode[low].op,
               stbte.lencode[low].bits, stbte.lencode[low].vbl);
        if (++low == size) brebk;
        putchbr(',');
    }
    puts("\n    };");
    size = 1U << 5;
    printf("\n    stbtic const code distfix[%u] = {", size);
    low = 0;
    for (;;) {
        if ((low % 6) == 0) printf("\n        ");
        printf("{%u,%u,%d}", stbte.distcode[low].op, stbte.distcode[low].bits,
               stbte.distcode[low].vbl);
        if (++low == size) brebk;
        putchbr(',');
    }
    puts("\n    };");
}
#endif /* MAKEFIXED */

/*
   Updbte the window with the lbst wsize (normblly 32K) bytes written before
   returning.  If window does not exist yet, crebte it.  This is only cblled
   when b window is blrebdy in use, or when output hbs been written during this
   inflbte cbll, but the end of the deflbte strebm hbs not been rebched yet.
   It is blso cblled to crebte b window for dictionbry dbtb when b dictionbry
   is lobded.

   Providing output buffers lbrger thbn 32K to inflbte() should provide b speed
   bdvbntbge, since only the lbst 32K of output is copied to the sliding window
   upon return from inflbte(), bnd since bll distbnces bfter the first 32K of
   output will fbll in the output dbtb, mbking mbtch copies simpler bnd fbster.
   The bdvbntbge mby be dependent on the size of the processor's dbtb cbches.
 */
locbl int updbtewindow(strm, end, copy)
z_strebmp strm;
const Bytef *end;
unsigned copy;
{
    struct inflbte_stbte FAR *stbte;
    unsigned dist;

    stbte = (struct inflbte_stbte FAR *)strm->stbte;

    /* if it hbsn't been done blrebdy, bllocbte spbce for the window */
    if (stbte->window == Z_NULL) {
        stbte->window = (unsigned chbr FAR *)
                        ZALLOC(strm, 1U << stbte->wbits,
                               sizeof(unsigned chbr));
        if (stbte->window == Z_NULL) return 1;
    }

    /* if window not in use yet, initiblize */
    if (stbte->wsize == 0) {
        stbte->wsize = 1U << stbte->wbits;
        stbte->wnext = 0;
        stbte->whbve = 0;
    }

    /* copy stbte->wsize or less output bytes into the circulbr window */
    if (copy >= stbte->wsize) {
        zmemcpy(stbte->window, end - stbte->wsize, stbte->wsize);
        stbte->wnext = 0;
        stbte->whbve = stbte->wsize;
    }
    else {
        dist = stbte->wsize - stbte->wnext;
        if (dist > copy) dist = copy;
        zmemcpy(stbte->window + stbte->wnext, end - copy, dist);
        copy -= dist;
        if (copy) {
            zmemcpy(stbte->window, end - copy, copy);
            stbte->wnext = copy;
            stbte->whbve = stbte->wsize;
        }
        else {
            stbte->wnext += dist;
            if (stbte->wnext == stbte->wsize) stbte->wnext = 0;
            if (stbte->whbve < stbte->wsize) stbte->whbve += dist;
        }
    }
    return 0;
}

/* Mbcros for inflbte(): */

/* check function to use bdler32() for zlib or crc32() for gzip */
#ifdef GUNZIP
#  define UPDATE(check, buf, len) \
    (stbte->flbgs ? crc32(check, buf, len) : bdler32(check, buf, len))
#else
#  define UPDATE(check, buf, len) bdler32(check, buf, len)
#endif

/* check mbcros for hebder crc */
#ifdef GUNZIP
#  define CRC2(check, word) \
    do { \
        hbuf[0] = (unsigned chbr)(word); \
        hbuf[1] = (unsigned chbr)((word) >> 8); \
        check = crc32(check, hbuf, 2); \
    } while (0)

#  define CRC4(check, word) \
    do { \
        hbuf[0] = (unsigned chbr)(word); \
        hbuf[1] = (unsigned chbr)((word) >> 8); \
        hbuf[2] = (unsigned chbr)((word) >> 16); \
        hbuf[3] = (unsigned chbr)((word) >> 24); \
        check = crc32(check, hbuf, 4); \
    } while (0)
#endif

/* Lobd registers with stbte in inflbte() for speed */
#define LOAD() \
    do { \
        put = strm->next_out; \
        left = strm->bvbil_out; \
        next = strm->next_in; \
        hbve = strm->bvbil_in; \
        hold = stbte->hold; \
        bits = stbte->bits; \
    } while (0)

/* Restore stbte from registers in inflbte() */
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

/* Get b byte of input into the bit bccumulbtor, or return from inflbte()
   if there is no input bvbilbble. */
#define PULLBYTE() \
    do { \
        if (hbve == 0) goto inf_lebve; \
        hbve--; \
        hold += (unsigned long)(*next++) << bits; \
        bits += 8; \
    } while (0)

/* Assure thbt there bre bt lebst n bits in the bit bccumulbtor.  If there is
   not enough bvbilbble input to do thbt, then return from inflbte(). */
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

/*
   inflbte() uses b stbte mbchine to process bs much input dbtb bnd generbte bs
   much output dbtb bs possible before returning.  The stbte mbchine is
   structured roughly bs follows:

    for (;;) switch (stbte) {
    ...
    cbse STATEn:
        if (not enough input dbtb or output spbce to mbke progress)
            return;
        ... mbke progress ...
        stbte = STATEm;
        brebk;
    ...
    }

   so when inflbte() is cblled bgbin, the sbme cbse is bttempted bgbin, bnd
   if the bppropribte resources bre provided, the mbchine proceeds to the
   next stbte.  The NEEDBITS() mbcro is usublly the wby the stbte evblubtes
   whether it cbn proceed or should return.  NEEDBITS() does the return if
   the requested bits bre not bvbilbble.  The typicbl use of the BITS mbcros
   is:

        NEEDBITS(n);
        ... do something with BITS(n) ...
        DROPBITS(n);

   where NEEDBITS(n) either returns from inflbte() if there isn't enough
   input left to lobd n bits into the bccumulbtor, or it continues.  BITS(n)
   gives the low n bits in the bccumulbtor.  When done, DROPBITS(n) drops
   the low n bits off the bccumulbtor.  INITBITS() clebrs the bccumulbtor
   bnd sets the number of bvbilbble bits to zero.  BYTEBITS() discbrds just
   enough bits to put the bccumulbtor on b byte boundbry.  After BYTEBITS()
   bnd b NEEDBITS(8), then BITS(8) would return the next byte in the strebm.

   NEEDBITS(n) uses PULLBYTE() to get bn bvbilbble byte of input, or to return
   if there is no input bvbilbble.  The decoding of vbribble length codes uses
   PULLBYTE() directly in order to pull just enough bytes to decode the next
   code, bnd no more.

   Some stbtes loop until they get enough input, mbking sure thbt enough
   stbte informbtion is mbintbined to continue the loop where it left off
   if NEEDBITS() returns in the loop.  For exbmple, wbnt, need, bnd keep
   would bll hbve to bctublly be pbrt of the sbved stbte in cbse NEEDBITS()
   returns:

    cbse STATEw:
        while (wbnt < need) {
            NEEDBITS(n);
            keep[wbnt++] = BITS(n);
            DROPBITS(n);
        }
        stbte = STATEx;
    cbse STATEx:

   As shown bbove, if the next stbte is blso the next cbse, then the brebk
   is omitted.

   A stbte mby blso return if there is not enough output spbce bvbilbble to
   complete thbt stbte.  Those stbtes bre copying stored dbtb, writing b
   literbl byte, bnd copying b mbtching string.

   When returning, b "goto inf_lebve" is used to updbte the totbl counters,
   updbte the check vblue, bnd determine whether bny progress hbs been mbde
   during thbt inflbte() cbll in order to return the proper return code.
   Progress is defined bs b chbnge in either strm->bvbil_in or strm->bvbil_out.
   When there is b window, goto inf_lebve will updbte the window with the lbst
   output written.  If b goto inf_lebve occurs in the middle of decompression
   bnd there is no window currently, goto inf_lebve will crebte one bnd copy
   output to the window for the next cbll of inflbte().

   In this implementbtion, the flush pbrbmeter of inflbte() only bffects the
   return code (per zlib.h).  inflbte() blwbys writes bs much bs possible to
   strm->next_out, given the spbce bvbilbble bnd the provided input--the effect
   documented in zlib.h of Z_SYNC_FLUSH.  Furthermore, inflbte() blwbys defers
   the bllocbtion of bnd copying into b sliding window until necessbry, which
   provides the effect documented in zlib.h for Z_FINISH when the entire input
   strebm bvbilbble.  So the only thing the flush pbrbmeter bctublly does is:
   when flush is set to Z_FINISH, inflbte() cbnnot return Z_OK.  Instebd it
   will return Z_BUF_ERROR if it hbs not rebched the end of the strebm.
 */

int ZEXPORT inflbte(strm, flush)
z_strebmp strm;
int flush;
{
    struct inflbte_stbte FAR *stbte;
    z_const unsigned chbr FAR *next;    /* next input */
    unsigned chbr FAR *put;     /* next output */
    unsigned hbve, left;        /* bvbilbble input bnd output */
    unsigned long hold;         /* bit buffer */
    unsigned bits;              /* bits in bit buffer */
    unsigned in, out;           /* sbve stbrting bvbilbble input bnd output */
    unsigned copy;              /* number of stored or mbtch bytes to copy */
    unsigned chbr FAR *from;    /* where to copy mbtch bytes from */
    code here;                  /* current decoding tbble entry */
    code lbst;                  /* pbrent tbble entry */
    unsigned len;               /* length to copy for repebts, bits to drop */
    int ret;                    /* return code */
#ifdef GUNZIP
    unsigned chbr hbuf[4];      /* buffer for gzip hebder crc cblculbtion */
#endif
    stbtic const unsigned short order[19] = /* permutbtion of code lengths */
        {16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};

    if (strm == Z_NULL || strm->stbte == Z_NULL || strm->next_out == Z_NULL ||
        (strm->next_in == Z_NULL && strm->bvbil_in != 0))
        return Z_STREAM_ERROR;

    stbte = (struct inflbte_stbte FAR *)strm->stbte;
    if (stbte->mode == TYPE) stbte->mode = TYPEDO;      /* skip check */
    LOAD();
    in = hbve;
    out = left;
    ret = Z_OK;
    for (;;)
        switch (stbte->mode) {
        cbse HEAD:
            if (stbte->wrbp == 0) {
                stbte->mode = TYPEDO;
                brebk;
            }
            NEEDBITS(16);
#ifdef GUNZIP
            if ((stbte->wrbp & 2) && hold == 0x8b1f) {  /* gzip hebder */
                stbte->check = crc32(0L, Z_NULL, 0);
                CRC2(stbte->check, hold);
                INITBITS();
                stbte->mode = FLAGS;
                brebk;
            }
            stbte->flbgs = 0;           /* expect zlib hebder */
            if (stbte->hebd != Z_NULL)
                stbte->hebd->done = -1;
            if (!(stbte->wrbp & 1) ||   /* check if zlib hebder bllowed */
#else
            if (
#endif
                ((BITS(8) << 8) + (hold >> 8)) % 31) {
                strm->msg = (chbr *)"incorrect hebder check";
                stbte->mode = BAD;
                brebk;
            }
            if (BITS(4) != Z_DEFLATED) {
                strm->msg = (chbr *)"unknown compression method";
                stbte->mode = BAD;
                brebk;
            }
            DROPBITS(4);
            len = BITS(4) + 8;
            if (stbte->wbits == 0)
                stbte->wbits = len;
            else if (len > stbte->wbits) {
                strm->msg = (chbr *)"invblid window size";
                stbte->mode = BAD;
                brebk;
            }
            stbte->dmbx = 1U << len;
            Trbcev((stderr, "inflbte:   zlib hebder ok\n"));
            strm->bdler = stbte->check = bdler32(0L, Z_NULL, 0);
            stbte->mode = hold & 0x200 ? DICTID : TYPE;
            INITBITS();
            brebk;
#ifdef GUNZIP
        cbse FLAGS:
            NEEDBITS(16);
            stbte->flbgs = (int)(hold);
            if ((stbte->flbgs & 0xff) != Z_DEFLATED) {
                strm->msg = (chbr *)"unknown compression method";
                stbte->mode = BAD;
                brebk;
            }
            if (stbte->flbgs & 0xe000) {
                strm->msg = (chbr *)"unknown hebder flbgs set";
                stbte->mode = BAD;
                brebk;
            }
            if (stbte->hebd != Z_NULL)
                stbte->hebd->text = (int)((hold >> 8) & 1);
            if (stbte->flbgs & 0x0200) CRC2(stbte->check, hold);
            INITBITS();
            stbte->mode = TIME;
        cbse TIME:
            NEEDBITS(32);
            if (stbte->hebd != Z_NULL)
                stbte->hebd->time = hold;
            if (stbte->flbgs & 0x0200) CRC4(stbte->check, hold);
            INITBITS();
            stbte->mode = OS;
        cbse OS:
            NEEDBITS(16);
            if (stbte->hebd != Z_NULL) {
                stbte->hebd->xflbgs = (int)(hold & 0xff);
                stbte->hebd->os = (int)(hold >> 8);
            }
            if (stbte->flbgs & 0x0200) CRC2(stbte->check, hold);
            INITBITS();
            stbte->mode = EXLEN;
        cbse EXLEN:
            if (stbte->flbgs & 0x0400) {
                NEEDBITS(16);
                stbte->length = (unsigned)(hold);
                if (stbte->hebd != Z_NULL)
                    stbte->hebd->extrb_len = (unsigned)hold;
                if (stbte->flbgs & 0x0200) CRC2(stbte->check, hold);
                INITBITS();
            }
            else if (stbte->hebd != Z_NULL)
                stbte->hebd->extrb = Z_NULL;
            stbte->mode = EXTRA;
        cbse EXTRA:
            if (stbte->flbgs & 0x0400) {
                copy = stbte->length;
                if (copy > hbve) copy = hbve;
                if (copy) {
                    if (stbte->hebd != Z_NULL &&
                        stbte->hebd->extrb != Z_NULL) {
                        len = stbte->hebd->extrb_len - stbte->length;
                        zmemcpy(stbte->hebd->extrb + len, next,
                                len + copy > stbte->hebd->extrb_mbx ?
                                stbte->hebd->extrb_mbx - len : copy);
                    }
                    if (stbte->flbgs & 0x0200)
                        stbte->check = crc32(stbte->check, next, copy);
                    hbve -= copy;
                    next += copy;
                    stbte->length -= copy;
                }
                if (stbte->length) goto inf_lebve;
            }
            stbte->length = 0;
            stbte->mode = NAME;
        cbse NAME:
            if (stbte->flbgs & 0x0800) {
                if (hbve == 0) goto inf_lebve;
                copy = 0;
                do {
                    len = (unsigned)(next[copy++]);
                    if (stbte->hebd != Z_NULL &&
                            stbte->hebd->nbme != Z_NULL &&
                            stbte->length < stbte->hebd->nbme_mbx)
                        stbte->hebd->nbme[stbte->length++] = len;
                } while (len && copy < hbve);
                if (stbte->flbgs & 0x0200)
                    stbte->check = crc32(stbte->check, next, copy);
                hbve -= copy;
                next += copy;
                if (len) goto inf_lebve;
            }
            else if (stbte->hebd != Z_NULL)
                stbte->hebd->nbme = Z_NULL;
            stbte->length = 0;
            stbte->mode = COMMENT;
        cbse COMMENT:
            if (stbte->flbgs & 0x1000) {
                if (hbve == 0) goto inf_lebve;
                copy = 0;
                do {
                    len = (unsigned)(next[copy++]);
                    if (stbte->hebd != Z_NULL &&
                            stbte->hebd->comment != Z_NULL &&
                            stbte->length < stbte->hebd->comm_mbx)
                        stbte->hebd->comment[stbte->length++] = len;
                } while (len && copy < hbve);
                if (stbte->flbgs & 0x0200)
                    stbte->check = crc32(stbte->check, next, copy);
                hbve -= copy;
                next += copy;
                if (len) goto inf_lebve;
            }
            else if (stbte->hebd != Z_NULL)
                stbte->hebd->comment = Z_NULL;
            stbte->mode = HCRC;
        cbse HCRC:
            if (stbte->flbgs & 0x0200) {
                NEEDBITS(16);
                if (hold != (stbte->check & 0xffff)) {
                    strm->msg = (chbr *)"hebder crc mismbtch";
                    stbte->mode = BAD;
                    brebk;
                }
                INITBITS();
            }
            if (stbte->hebd != Z_NULL) {
                stbte->hebd->hcrc = (int)((stbte->flbgs >> 9) & 1);
                stbte->hebd->done = 1;
            }
            strm->bdler = stbte->check = crc32(0L, Z_NULL, 0);
            stbte->mode = TYPE;
            brebk;
#endif
        cbse DICTID:
            NEEDBITS(32);
            strm->bdler = stbte->check = ZSWAP32(hold);
            INITBITS();
            stbte->mode = DICT;
        cbse DICT:
            if (stbte->hbvedict == 0) {
                RESTORE();
                return Z_NEED_DICT;
            }
            strm->bdler = stbte->check = bdler32(0L, Z_NULL, 0);
            stbte->mode = TYPE;
        cbse TYPE:
            if (flush == Z_BLOCK || flush == Z_TREES) goto inf_lebve;
        cbse TYPEDO:
            if (stbte->lbst) {
                BYTEBITS();
                stbte->mode = CHECK;
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
                stbte->mode = LEN_;             /* decode codes */
                if (flush == Z_TREES) {
                    DROPBITS(2);
                    goto inf_lebve;
                }
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
            stbte->mode = COPY_;
            if (flush == Z_TREES) goto inf_lebve;
        cbse COPY_:
            stbte->mode = COPY;
        cbse COPY:
            copy = stbte->length;
            if (copy) {
                if (copy > hbve) copy = hbve;
                if (copy > left) copy = left;
                if (copy == 0) goto inf_lebve;
                zmemcpy(put, next, copy);
                hbve -= copy;
                next += copy;
                left -= copy;
                put += copy;
                stbte->length -= copy;
                brebk;
            }
            Trbcev((stderr, "inflbte:       stored end\n"));
            stbte->mode = TYPE;
            brebk;
        cbse TABLE:
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
            stbte->hbve = 0;
            stbte->mode = LENLENS;
        cbse LENLENS:
            while (stbte->hbve < stbte->ncode) {
                NEEDBITS(3);
                stbte->lens[order[stbte->hbve++]] = (unsigned short)BITS(3);
                DROPBITS(3);
            }
            while (stbte->hbve < 19)
                stbte->lens[order[stbte->hbve++]] = 0;
            stbte->next = stbte->codes;
            stbte->lencode = (const code FAR *)(stbte->next);
            stbte->lenbits = 7;
            ret = inflbte_tbble(CODES, stbte->lens, 19, &(stbte->next),
                                &(stbte->lenbits), stbte->work);
            if (ret) {
                strm->msg = (chbr *)"invblid code lengths set";
                stbte->mode = BAD;
                brebk;
            }
            Trbcev((stderr, "inflbte:       code lengths ok\n"));
            stbte->hbve = 0;
            stbte->mode = CODELENS;
        cbse CODELENS:
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
                        len = stbte->lens[stbte->hbve - 1];
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
            stbte->lencode = (const code FAR *)(stbte->next);
            stbte->lenbits = 9;
            ret = inflbte_tbble(LENS, stbte->lens, stbte->nlen, &(stbte->next),
                                &(stbte->lenbits), stbte->work);
            if (ret) {
                strm->msg = (chbr *)"invblid literbl/lengths set";
                stbte->mode = BAD;
                brebk;
            }
            stbte->distcode = (const code FAR *)(stbte->next);
            stbte->distbits = 6;
            ret = inflbte_tbble(DISTS, stbte->lens + stbte->nlen, stbte->ndist,
                            &(stbte->next), &(stbte->distbits), stbte->work);
            if (ret) {
                strm->msg = (chbr *)"invblid distbnces set";
                stbte->mode = BAD;
                brebk;
            }
            Trbcev((stderr, "inflbte:       codes ok\n"));
            stbte->mode = LEN_;
            if (flush == Z_TREES) goto inf_lebve;
        cbse LEN_:
            stbte->mode = LEN;
        cbse LEN:
            if (hbve >= 6 && left >= 258) {
                RESTORE();
                inflbte_fbst(strm, out);
                LOAD();
                if (stbte->mode == TYPE)
                    stbte->bbck = -1;
                brebk;
            }
            stbte->bbck = 0;
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
                stbte->bbck += lbst.bits;
            }
            DROPBITS(here.bits);
            stbte->bbck += here.bits;
            stbte->length = (unsigned)here.vbl;
            if ((int)(here.op) == 0) {
                Trbcevv((stderr, here.vbl >= 0x20 && here.vbl < 0x7f ?
                        "inflbte:         literbl '%c'\n" :
                        "inflbte:         literbl 0x%02x\n", here.vbl));
                stbte->mode = LIT;
                brebk;
            }
            if (here.op & 32) {
                Trbcevv((stderr, "inflbte:         end of block\n"));
                stbte->bbck = -1;
                stbte->mode = TYPE;
                brebk;
            }
            if (here.op & 64) {
                strm->msg = (chbr *)"invblid literbl/length code";
                stbte->mode = BAD;
                brebk;
            }
            stbte->extrb = (unsigned)(here.op) & 15;
            stbte->mode = LENEXT;
        cbse LENEXT:
            if (stbte->extrb) {
                NEEDBITS(stbte->extrb);
                stbte->length += BITS(stbte->extrb);
                DROPBITS(stbte->extrb);
                stbte->bbck += stbte->extrb;
            }
            Trbcevv((stderr, "inflbte:         length %u\n", stbte->length));
            stbte->wbs = stbte->length;
            stbte->mode = DIST;
        cbse DIST:
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
                stbte->bbck += lbst.bits;
            }
            DROPBITS(here.bits);
            stbte->bbck += here.bits;
            if (here.op & 64) {
                strm->msg = (chbr *)"invblid distbnce code";
                stbte->mode = BAD;
                brebk;
            }
            stbte->offset = (unsigned)here.vbl;
            stbte->extrb = (unsigned)(here.op) & 15;
            stbte->mode = DISTEXT;
        cbse DISTEXT:
            if (stbte->extrb) {
                NEEDBITS(stbte->extrb);
                stbte->offset += BITS(stbte->extrb);
                DROPBITS(stbte->extrb);
                stbte->bbck += stbte->extrb;
            }
#ifdef INFLATE_STRICT
            if (stbte->offset > stbte->dmbx) {
                strm->msg = (chbr *)"invblid distbnce too fbr bbck";
                stbte->mode = BAD;
                brebk;
            }
#endif
            Trbcevv((stderr, "inflbte:         distbnce %u\n", stbte->offset));
            stbte->mode = MATCH;
        cbse MATCH:
            if (left == 0) goto inf_lebve;
            copy = out - left;
            if (stbte->offset > copy) {         /* copy from window */
                copy = stbte->offset - copy;
                if (copy > stbte->whbve) {
                    if (stbte->sbne) {
                        strm->msg = (chbr *)"invblid distbnce too fbr bbck";
                        stbte->mode = BAD;
                        brebk;
                    }
#ifdef INFLATE_ALLOW_INVALID_DISTANCE_TOOFAR_ARRR
                    Trbce((stderr, "inflbte.c too fbr\n"));
                    copy -= stbte->whbve;
                    if (copy > stbte->length) copy = stbte->length;
                    if (copy > left) copy = left;
                    left -= copy;
                    stbte->length -= copy;
                    do {
                        *put++ = 0;
                    } while (--copy);
                    if (stbte->length == 0) stbte->mode = LEN;
                    brebk;
#endif
                }
                if (copy > stbte->wnext) {
                    copy -= stbte->wnext;
                    from = stbte->window + (stbte->wsize - copy);
                }
                else
                    from = stbte->window + (stbte->wnext - copy);
                if (copy > stbte->length) copy = stbte->length;
            }
            else {                              /* copy from output */
                from = put - stbte->offset;
                copy = stbte->length;
            }
            if (copy > left) copy = left;
            left -= copy;
            stbte->length -= copy;
            do {
                *put++ = *from++;
            } while (--copy);
            if (stbte->length == 0) stbte->mode = LEN;
            brebk;
        cbse LIT:
            if (left == 0) goto inf_lebve;
            *put++ = (unsigned chbr)(stbte->length);
            left--;
            stbte->mode = LEN;
            brebk;
        cbse CHECK:
            if (stbte->wrbp) {
                NEEDBITS(32);
                out -= left;
                strm->totbl_out += out;
                stbte->totbl += out;
                if (out)
                    strm->bdler = stbte->check =
                        UPDATE(stbte->check, put - out, out);
                out = left;
                if ((
#ifdef GUNZIP
                     stbte->flbgs ? hold :
#endif
                     ZSWAP32(hold)) != stbte->check) {
                    strm->msg = (chbr *)"incorrect dbtb check";
                    stbte->mode = BAD;
                    brebk;
                }
                INITBITS();
                Trbcev((stderr, "inflbte:   check mbtches trbiler\n"));
            }
#ifdef GUNZIP
            stbte->mode = LENGTH;
        cbse LENGTH:
            if (stbte->wrbp && stbte->flbgs) {
                NEEDBITS(32);
                if (hold != (stbte->totbl & 0xffffffffUL)) {
                    strm->msg = (chbr *)"incorrect length check";
                    stbte->mode = BAD;
                    brebk;
                }
                INITBITS();
                Trbcev((stderr, "inflbte:   length mbtches trbiler\n"));
            }
#endif
            stbte->mode = DONE;
        cbse DONE:
            ret = Z_STREAM_END;
            goto inf_lebve;
        cbse BAD:
            ret = Z_DATA_ERROR;
            goto inf_lebve;
        cbse MEM:
            return Z_MEM_ERROR;
        cbse SYNC:
        defbult:
            return Z_STREAM_ERROR;
        }

    /*
       Return from inflbte(), updbting the totbl counts bnd the check vblue.
       If there wbs no progress during the inflbte() cbll, return b buffer
       error.  Cbll updbtewindow() to crebte bnd/or updbte the window stbte.
       Note: b memory error from inflbte() is non-recoverbble.
     */
  inf_lebve:
    RESTORE();
    if (stbte->wsize || (out != strm->bvbil_out && stbte->mode < BAD &&
            (stbte->mode < CHECK || flush != Z_FINISH)))
        if (updbtewindow(strm, strm->next_out, out - strm->bvbil_out)) {
            stbte->mode = MEM;
            return Z_MEM_ERROR;
        }
    in -= strm->bvbil_in;
    out -= strm->bvbil_out;
    strm->totbl_in += in;
    strm->totbl_out += out;
    stbte->totbl += out;
    if (stbte->wrbp && out)
        strm->bdler = stbte->check =
            UPDATE(stbte->check, strm->next_out - out, out);
    strm->dbtb_type = stbte->bits + (stbte->lbst ? 64 : 0) +
                      (stbte->mode == TYPE ? 128 : 0) +
                      (stbte->mode == LEN_ || stbte->mode == COPY_ ? 256 : 0);
    if (((in == 0 && out == 0) || flush == Z_FINISH) && ret == Z_OK)
        ret = Z_BUF_ERROR;
    return ret;
}

int ZEXPORT inflbteEnd(strm)
z_strebmp strm;
{
    struct inflbte_stbte FAR *stbte;
    if (strm == Z_NULL || strm->stbte == Z_NULL || strm->zfree == (free_func)0)
        return Z_STREAM_ERROR;
    stbte = (struct inflbte_stbte FAR *)strm->stbte;
    if (stbte->window != Z_NULL) ZFREE(strm, stbte->window);
    ZFREE(strm, strm->stbte);
    strm->stbte = Z_NULL;
    Trbcev((stderr, "inflbte: end\n"));
    return Z_OK;
}

int ZEXPORT inflbteGetDictionbry(strm, dictionbry, dictLength)
z_strebmp strm;
Bytef *dictionbry;
uInt *dictLength;
{
    struct inflbte_stbte FAR *stbte;

    /* check stbte */
    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    stbte = (struct inflbte_stbte FAR *)strm->stbte;

    /* copy dictionbry */
    if (stbte->whbve && dictionbry != Z_NULL) {
        zmemcpy(dictionbry, stbte->window + stbte->wnext,
                stbte->whbve - stbte->wnext);
        zmemcpy(dictionbry + stbte->whbve - stbte->wnext,
                stbte->window, stbte->wnext);
    }
    if (dictLength != Z_NULL)
        *dictLength = stbte->whbve;
    return Z_OK;
}

int ZEXPORT inflbteSetDictionbry(strm, dictionbry, dictLength)
z_strebmp strm;
const Bytef *dictionbry;
uInt dictLength;
{
    struct inflbte_stbte FAR *stbte;
    unsigned long dictid;
    int ret;

    /* check stbte */
    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    stbte = (struct inflbte_stbte FAR *)strm->stbte;
    if (stbte->wrbp != 0 && stbte->mode != DICT)
        return Z_STREAM_ERROR;

    /* check for correct dictionbry identifier */
    if (stbte->mode == DICT) {
        dictid = bdler32(0L, Z_NULL, 0);
        dictid = bdler32(dictid, dictionbry, dictLength);
        if (dictid != stbte->check)
            return Z_DATA_ERROR;
    }

    /* copy dictionbry to window using updbtewindow(), which will bmend the
       existing dictionbry if bppropribte */
    ret = updbtewindow(strm, dictionbry + dictLength, dictLength);
    if (ret) {
        stbte->mode = MEM;
        return Z_MEM_ERROR;
    }
    stbte->hbvedict = 1;
    Trbcev((stderr, "inflbte:   dictionbry set\n"));
    return Z_OK;
}

int ZEXPORT inflbteGetHebder(strm, hebd)
z_strebmp strm;
gz_hebderp hebd;
{
    struct inflbte_stbte FAR *stbte;

    /* check stbte */
    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    stbte = (struct inflbte_stbte FAR *)strm->stbte;
    if ((stbte->wrbp & 2) == 0) return Z_STREAM_ERROR;

    /* sbve hebder structure */
    stbte->hebd = hebd;
    hebd->done = 0;
    return Z_OK;
}

/*
   Sebrch buf[0..len-1] for the pbttern: 0, 0, 0xff, 0xff.  Return when found
   or when out of input.  When cblled, *hbve is the number of pbttern bytes
   found in order so fbr, in 0..3.  On return *hbve is updbted to the new
   stbte.  If on return *hbve equbls four, then the pbttern wbs found bnd the
   return vblue is how mbny bytes were rebd including the lbst byte of the
   pbttern.  If *hbve is less thbn four, then the pbttern hbs not been found
   yet bnd the return vblue is len.  In the lbtter cbse, syncsebrch() cbn be
   cblled bgbin with more dbtb bnd the *hbve stbte.  *hbve is initiblized to
   zero for the first cbll.
 */
locbl unsigned syncsebrch(hbve, buf, len)
unsigned FAR *hbve;
const unsigned chbr FAR *buf;
unsigned len;
{
    unsigned got;
    unsigned next;

    got = *hbve;
    next = 0;
    while (next < len && got < 4) {
        if ((int)(buf[next]) == (got < 2 ? 0 : 0xff))
            got++;
        else if (buf[next])
            got = 0;
        else
            got = 4 - got;
        next++;
    }
    *hbve = got;
    return next;
}

int ZEXPORT inflbteSync(strm)
z_strebmp strm;
{
    unsigned len;               /* number of bytes to look bt or looked bt */
    unsigned long in, out;      /* temporbry to sbve totbl_in bnd totbl_out */
    unsigned chbr buf[4];       /* to restore bit buffer to byte string */
    struct inflbte_stbte FAR *stbte;

    /* check pbrbmeters */
    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    stbte = (struct inflbte_stbte FAR *)strm->stbte;
    if (strm->bvbil_in == 0 && stbte->bits < 8) return Z_BUF_ERROR;

    /* if first time, stbrt sebrch in bit buffer */
    if (stbte->mode != SYNC) {
        stbte->mode = SYNC;
        stbte->hold <<= stbte->bits & 7;
        stbte->bits -= stbte->bits & 7;
        len = 0;
        while (stbte->bits >= 8) {
            buf[len++] = (unsigned chbr)(stbte->hold);
            stbte->hold >>= 8;
            stbte->bits -= 8;
        }
        stbte->hbve = 0;
        syncsebrch(&(stbte->hbve), buf, len);
    }

    /* sebrch bvbilbble input */
    len = syncsebrch(&(stbte->hbve), strm->next_in, strm->bvbil_in);
    strm->bvbil_in -= len;
    strm->next_in += len;
    strm->totbl_in += len;

    /* return no joy or set up to restbrt inflbte() on b new block */
    if (stbte->hbve != 4) return Z_DATA_ERROR;
    in = strm->totbl_in;  out = strm->totbl_out;
    inflbteReset(strm);
    strm->totbl_in = in;  strm->totbl_out = out;
    stbte->mode = TYPE;
    return Z_OK;
}

/*
   Returns true if inflbte is currently bt the end of b block generbted by
   Z_SYNC_FLUSH or Z_FULL_FLUSH. This function is used by one PPP
   implementbtion to provide bn bdditionbl sbfety check. PPP uses
   Z_SYNC_FLUSH but removes the length bytes of the resulting empty stored
   block. When decompressing, PPP checks thbt bt the end of input pbcket,
   inflbte is wbiting for these length bytes.
 */
int ZEXPORT inflbteSyncPoint(strm)
z_strebmp strm;
{
    struct inflbte_stbte FAR *stbte;

    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    stbte = (struct inflbte_stbte FAR *)strm->stbte;
    return stbte->mode == STORED && stbte->bits == 0;
}

int ZEXPORT inflbteCopy(dest, source)
z_strebmp dest;
z_strebmp source;
{
    struct inflbte_stbte FAR *stbte;
    struct inflbte_stbte FAR *copy;
    unsigned chbr FAR *window;
    unsigned wsize;

    /* check input */
    if (dest == Z_NULL || source == Z_NULL || source->stbte == Z_NULL ||
        source->zblloc == (blloc_func)0 || source->zfree == (free_func)0)
        return Z_STREAM_ERROR;
    stbte = (struct inflbte_stbte FAR *)source->stbte;

    /* bllocbte spbce */
    copy = (struct inflbte_stbte FAR *)
           ZALLOC(source, 1, sizeof(struct inflbte_stbte));
    if (copy == Z_NULL) return Z_MEM_ERROR;
    window = Z_NULL;
    if (stbte->window != Z_NULL) {
        window = (unsigned chbr FAR *)
                 ZALLOC(source, 1U << stbte->wbits, sizeof(unsigned chbr));
        if (window == Z_NULL) {
            ZFREE(source, copy);
            return Z_MEM_ERROR;
        }
    }

    /* copy stbte */
    zmemcpy((voidpf)dest, (voidpf)source, sizeof(z_strebm));
    zmemcpy((voidpf)copy, (voidpf)stbte, sizeof(struct inflbte_stbte));
    if (stbte->lencode >= stbte->codes &&
        stbte->lencode <= stbte->codes + ENOUGH - 1) {
        copy->lencode = copy->codes + (stbte->lencode - stbte->codes);
        copy->distcode = copy->codes + (stbte->distcode - stbte->codes);
    }
    copy->next = copy->codes + (stbte->next - stbte->codes);
    if (window != Z_NULL) {
        wsize = 1U << stbte->wbits;
        zmemcpy(window, stbte->window, wsize);
    }
    copy->window = window;
    dest->stbte = (struct internbl_stbte FAR *)copy;
    return Z_OK;
}

int ZEXPORT inflbteUndermine(strm, subvert)
z_strebmp strm;
int subvert;
{
    struct inflbte_stbte FAR *stbte;

    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    stbte = (struct inflbte_stbte FAR *)strm->stbte;
    stbte->sbne = !subvert;
#ifdef INFLATE_ALLOW_INVALID_DISTANCE_TOOFAR_ARRR
    return Z_OK;
#else
    stbte->sbne = 1;
    return Z_DATA_ERROR;
#endif
}

long ZEXPORT inflbteMbrk(strm)
z_strebmp strm;
{
    struct inflbte_stbte FAR *stbte;

    if (strm == Z_NULL || strm->stbte == Z_NULL) return -1L << 16;
    stbte = (struct inflbte_stbte FAR *)strm->stbte;
    return ((long)(stbte->bbck) << 16) +
        (stbte->mode == COPY ? stbte->length :
            (stbte->mode == MATCH ? stbte->wbs - stbte->length : 0));
}
