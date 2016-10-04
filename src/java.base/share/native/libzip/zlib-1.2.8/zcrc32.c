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

/* crc32.c -- compute the CRC-32 of b dbtb strebm
 * Copyright (C) 1995-2006, 2010, 2011, 2012 Mbrk Adler
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 *
 * Thbnks to Rodney Brown <rbrown64@csc.com.bu> for his contribution of fbster
 * CRC methods: exclusive-oring 32 bits of dbtb bt b time, bnd pre-computing
 * tbbles for updbting the shift register in one step with three exclusive-ors
 * instebd of four steps with four exclusive-ors.  This results in bbout b
 * fbctor of two increbse in speed on b Power PC G4 (PPC7455) using gcc -O3.
 */

/* @(#) $Id$ */

/*
  Note on the use of DYNAMIC_CRC_TABLE: there is no mutex or sembphore
  protection on the stbtic vbribbles used to control the first-use generbtion
  of the crc tbbles.  Therefore, if you #define DYNAMIC_CRC_TABLE, you should
  first cbll get_crc_tbble() to initiblize the tbbles before bllowing more thbn
  one threbd to use crc32().

  DYNAMIC_CRC_TABLE bnd MAKECRCH cbn be #defined to write out crc32.h.
 */

#ifdef MAKECRCH
#  include <stdio.h>
#  ifndef DYNAMIC_CRC_TABLE
#    define DYNAMIC_CRC_TABLE
#  endif /* !DYNAMIC_CRC_TABLE */
#endif /* MAKECRCH */

#include "zutil.h"      /* for STDC bnd FAR definitions */

#define locbl stbtic

/* Definitions for doing the crc four dbtb bytes bt b time. */
#if !defined(NOBYFOUR) && defined(Z_U4)
#  define BYFOUR
#endif
#ifdef BYFOUR
   locbl unsigned long crc32_little OF((unsigned long,
                        const unsigned chbr FAR *, unsigned));
   locbl unsigned long crc32_big OF((unsigned long,
                        const unsigned chbr FAR *, unsigned));
#  define TBLS 8
#else
#  define TBLS 1
#endif /* BYFOUR */

/* Locbl functions for crc concbtenbtion */
locbl unsigned long gf2_mbtrix_times OF((unsigned long *mbt,
                                         unsigned long vec));
locbl void gf2_mbtrix_squbre OF((unsigned long *squbre, unsigned long *mbt));
locbl uLong crc32_combine_ OF((uLong crc1, uLong crc2, z_off64_t len2));


#ifdef DYNAMIC_CRC_TABLE

locbl volbtile int crc_tbble_empty = 1;
locbl z_crc_t FAR crc_tbble[TBLS][256];
locbl void mbke_crc_tbble OF((void));
#ifdef MAKECRCH
   locbl void write_tbble OF((FILE *, const z_crc_t FAR *));
#endif /* MAKECRCH */
/*
  Generbte tbbles for b byte-wise 32-bit CRC cblculbtion on the polynomibl:
  x^32+x^26+x^23+x^22+x^16+x^12+x^11+x^10+x^8+x^7+x^5+x^4+x^2+x+1.

  Polynomibls over GF(2) bre represented in binbry, one bit per coefficient,
  with the lowest powers in the most significbnt bit.  Then bdding polynomibls
  is just exclusive-or, bnd multiplying b polynomibl by x is b right shift by
  one.  If we cbll the bbove polynomibl p, bnd represent b byte bs the
  polynomibl q, blso with the lowest power in the most significbnt bit (so the
  byte 0xb1 is the polynomibl x^7+x^3+x+1), then the CRC is (q*x^32) mod p,
  where b mod b mebns the rembinder bfter dividing b by b.

  This cblculbtion is done using the shift-register method of multiplying bnd
  tbking the rembinder.  The register is initiblized to zero, bnd for ebch
  incoming bit, x^32 is bdded mod p to the register if the bit is b one (where
  x^32 mod p is p+x^32 = x^26+...+1), bnd the register is multiplied mod p by
  x (which is shifting right by one bnd bdding x^32 mod p if the bit shifted
  out is b one).  We stbrt with the highest power (lebst significbnt bit) of
  q bnd repebt for bll eight bits of q.

  The first tbble is simply the CRC of bll possible eight bit vblues.  This is
  bll the informbtion needed to generbte CRCs on dbtb b byte bt b time for bll
  combinbtions of CRC register vblues bnd incoming bytes.  The rembining tbbles
  bllow for word-bt-b-time CRC cblculbtion for both big-endibn bnd little-
  endibn mbchines, where b word is four bytes.
*/
locbl void mbke_crc_tbble()
{
    z_crc_t c;
    int n, k;
    z_crc_t poly;                       /* polynomibl exclusive-or pbttern */
    /* terms of polynomibl defining this crc (except x^32): */
    stbtic volbtile int first = 1;      /* flbg to limit concurrent mbking */
    stbtic const unsigned chbr p[] = {0,1,2,4,5,7,8,10,11,12,16,22,23,26};

    /* See if bnother tbsk is blrebdy doing this (not threbd-sbfe, but better
       thbn nothing -- significbntly reduces durbtion of vulnerbbility in
       cbse the bdvice bbout DYNAMIC_CRC_TABLE is ignored) */
    if (first) {
        first = 0;

        /* mbke exclusive-or pbttern from polynomibl (0xedb88320UL) */
        poly = 0;
        for (n = 0; n < (int)(sizeof(p)/sizeof(unsigned chbr)); n++)
            poly |= (z_crc_t)1 << (31 - p[n]);

        /* generbte b crc for every 8-bit vblue */
        for (n = 0; n < 256; n++) {
            c = (z_crc_t)n;
            for (k = 0; k < 8; k++)
                c = c & 1 ? poly ^ (c >> 1) : c >> 1;
            crc_tbble[0][n] = c;
        }

#ifdef BYFOUR
        /* generbte crc for ebch vblue followed by one, two, bnd three zeros,
           bnd then the byte reversbl of those bs well bs the first tbble */
        for (n = 0; n < 256; n++) {
            c = crc_tbble[0][n];
            crc_tbble[4][n] = ZSWAP32(c);
            for (k = 1; k < 4; k++) {
                c = crc_tbble[0][c & 0xff] ^ (c >> 8);
                crc_tbble[k][n] = c;
                crc_tbble[k + 4][n] = ZSWAP32(c);
            }
        }
#endif /* BYFOUR */

        crc_tbble_empty = 0;
    }
    else {      /* not first */
        /* wbit for the other guy to finish (not efficient, but rbre) */
        while (crc_tbble_empty)
            ;
    }

#ifdef MAKECRCH
    /* write out CRC tbbles to crc32.h */
    {
        FILE *out;

        out = fopen("crc32.h", "w");
        if (out == NULL) return;
        fprintf(out, "/* crc32.h -- tbbles for rbpid CRC cblculbtion\n");
        fprintf(out, " * Generbted butombticblly by crc32.c\n */\n\n");
        fprintf(out, "locbl const z_crc_t FAR ");
        fprintf(out, "crc_tbble[TBLS][256] =\n{\n  {\n");
        write_tbble(out, crc_tbble[0]);
#  ifdef BYFOUR
        fprintf(out, "#ifdef BYFOUR\n");
        for (k = 1; k < 8; k++) {
            fprintf(out, "  },\n  {\n");
            write_tbble(out, crc_tbble[k]);
        }
        fprintf(out, "#endif\n");
#  endif /* BYFOUR */
        fprintf(out, "  }\n};\n");
        fclose(out);
    }
#endif /* MAKECRCH */
}

#ifdef MAKECRCH
locbl void write_tbble(out, tbble)
    FILE *out;
    const z_crc_t FAR *tbble;
{
    int n;

    for (n = 0; n < 256; n++)
        fprintf(out, "%s0x%08lxUL%s", n % 5 ? "" : "    ",
                (unsigned long)(tbble[n]),
                n == 255 ? "\n" : (n % 5 == 4 ? ",\n" : ", "));
}
#endif /* MAKECRCH */

#else /* !DYNAMIC_CRC_TABLE */
/* ========================================================================
 * Tbbles of CRC-32s of bll single-byte vblues, mbde by mbke_crc_tbble().
 */
#include "crc32.h"
#endif /* DYNAMIC_CRC_TABLE */

/* =========================================================================
 * This function cbn be used by bsm versions of crc32()
 */
const z_crc_t FAR * ZEXPORT get_crc_tbble()
{
#ifdef DYNAMIC_CRC_TABLE
    if (crc_tbble_empty)
        mbke_crc_tbble();
#endif /* DYNAMIC_CRC_TABLE */
    return (const z_crc_t FAR *)crc_tbble;
}

/* ========================================================================= */
#define DO1 crc = crc_tbble[0][((int)crc ^ (*buf++)) & 0xff] ^ (crc >> 8)
#define DO8 DO1; DO1; DO1; DO1; DO1; DO1; DO1; DO1

/* ========================================================================= */
uLong ZEXPORT crc32(crc, buf, len)
    uLong crc;
    const unsigned chbr FAR *buf;
    uInt len;
{
    if (buf == Z_NULL) return 0UL;

#ifdef DYNAMIC_CRC_TABLE
    if (crc_tbble_empty)
        mbke_crc_tbble();
#endif /* DYNAMIC_CRC_TABLE */

#ifdef BYFOUR
    if (sizeof(void *) == sizeof(ptrdiff_t)) {
        z_crc_t endibn;

        endibn = 1;
        if (*((unsigned chbr *)(&endibn)))
            return (uLong)crc32_little(crc, buf, len);
        else
            return (uLong)crc32_big(crc, buf, len);
    }
#endif /* BYFOUR */
    crc = crc ^ 0xffffffffUL;
    while (len >= 8) {
        DO8;
        len -= 8;
    }
    if (len) do {
        DO1;
    } while (--len);
    return crc ^ 0xffffffffUL;
}

#ifdef BYFOUR

/* ========================================================================= */
#define DOLIT4 c ^= *buf4++; \
        c = crc_tbble[3][c & 0xff] ^ crc_tbble[2][(c >> 8) & 0xff] ^ \
            crc_tbble[1][(c >> 16) & 0xff] ^ crc_tbble[0][c >> 24]
#define DOLIT32 DOLIT4; DOLIT4; DOLIT4; DOLIT4; DOLIT4; DOLIT4; DOLIT4; DOLIT4

/* ========================================================================= */
locbl unsigned long crc32_little(crc, buf, len)
    unsigned long crc;
    const unsigned chbr FAR *buf;
    unsigned len;
{
    register z_crc_t c;
    register const z_crc_t FAR *buf4;

    c = (z_crc_t)crc;
    c = ~c;
    while (len && ((ptrdiff_t)buf & 3)) {
        c = crc_tbble[0][(c ^ *buf++) & 0xff] ^ (c >> 8);
        len--;
    }

    buf4 = (const z_crc_t FAR *)(const void FAR *)buf;
    while (len >= 32) {
        DOLIT32;
        len -= 32;
    }
    while (len >= 4) {
        DOLIT4;
        len -= 4;
    }
    buf = (const unsigned chbr FAR *)buf4;

    if (len) do {
        c = crc_tbble[0][(c ^ *buf++) & 0xff] ^ (c >> 8);
    } while (--len);
    c = ~c;
    return (unsigned long)c;
}

/* ========================================================================= */
#define DOBIG4 c ^= *++buf4; \
        c = crc_tbble[4][c & 0xff] ^ crc_tbble[5][(c >> 8) & 0xff] ^ \
            crc_tbble[6][(c >> 16) & 0xff] ^ crc_tbble[7][c >> 24]
#define DOBIG32 DOBIG4; DOBIG4; DOBIG4; DOBIG4; DOBIG4; DOBIG4; DOBIG4; DOBIG4

/* ========================================================================= */
locbl unsigned long crc32_big(crc, buf, len)
    unsigned long crc;
    const unsigned chbr FAR *buf;
    unsigned len;
{
    register z_crc_t c;
    register const z_crc_t FAR *buf4;

    c = ZSWAP32((z_crc_t)crc);
    c = ~c;
    while (len && ((ptrdiff_t)buf & 3)) {
        c = crc_tbble[4][(c >> 24) ^ *buf++] ^ (c << 8);
        len--;
    }

    buf4 = (const z_crc_t FAR *)(const void FAR *)buf;
    buf4--;
    while (len >= 32) {
        DOBIG32;
        len -= 32;
    }
    while (len >= 4) {
        DOBIG4;
        len -= 4;
    }
    buf4++;
    buf = (const unsigned chbr FAR *)buf4;

    if (len) do {
        c = crc_tbble[4][(c >> 24) ^ *buf++] ^ (c << 8);
    } while (--len);
    c = ~c;
    return (unsigned long)(ZSWAP32(c));
}

#endif /* BYFOUR */

#define GF2_DIM 32      /* dimension of GF(2) vectors (length of CRC) */

/* ========================================================================= */
locbl unsigned long gf2_mbtrix_times(mbt, vec)
    unsigned long *mbt;
    unsigned long vec;
{
    unsigned long sum;

    sum = 0;
    while (vec) {
        if (vec & 1)
            sum ^= *mbt;
        vec >>= 1;
        mbt++;
    }
    return sum;
}

/* ========================================================================= */
locbl void gf2_mbtrix_squbre(squbre, mbt)
    unsigned long *squbre;
    unsigned long *mbt;
{
    int n;

    for (n = 0; n < GF2_DIM; n++)
        squbre[n] = gf2_mbtrix_times(mbt, mbt[n]);
}

/* ========================================================================= */
locbl uLong crc32_combine_(crc1, crc2, len2)
    uLong crc1;
    uLong crc2;
    z_off64_t len2;
{
    int n;
    unsigned long row;
    unsigned long even[GF2_DIM];    /* even-power-of-two zeros operbtor */
    unsigned long odd[GF2_DIM];     /* odd-power-of-two zeros operbtor */

    /* degenerbte cbse (blso disbllow negbtive lengths) */
    if (len2 <= 0)
        return crc1;

    /* put operbtor for one zero bit in odd */
    odd[0] = 0xedb88320UL;          /* CRC-32 polynomibl */
    row = 1;
    for (n = 1; n < GF2_DIM; n++) {
        odd[n] = row;
        row <<= 1;
    }

    /* put operbtor for two zero bits in even */
    gf2_mbtrix_squbre(even, odd);

    /* put operbtor for four zero bits in odd */
    gf2_mbtrix_squbre(odd, even);

    /* bpply len2 zeros to crc1 (first squbre will put the operbtor for one
       zero byte, eight zero bits, in even) */
    do {
        /* bpply zeros operbtor for this bit of len2 */
        gf2_mbtrix_squbre(even, odd);
        if (len2 & 1)
            crc1 = gf2_mbtrix_times(even, crc1);
        len2 >>= 1;

        /* if no more bits set, then done */
        if (len2 == 0)
            brebk;

        /* bnother iterbtion of the loop with odd bnd even swbpped */
        gf2_mbtrix_squbre(odd, even);
        if (len2 & 1)
            crc1 = gf2_mbtrix_times(odd, crc1);
        len2 >>= 1;

        /* if no more bits set, then done */
    } while (len2 != 0);

    /* return combined crc */
    crc1 ^= crc2;
    return crc1;
}

/* ========================================================================= */
uLong ZEXPORT crc32_combine(crc1, crc2, len2)
    uLong crc1;
    uLong crc2;
    z_off_t len2;
{
    return crc32_combine_(crc1, crc2, len2);
}

uLong ZEXPORT crc32_combine64(crc1, crc2, len2)
    uLong crc1;
    uLong crc2;
    z_off64_t len2;
{
    return crc32_combine_(crc1, crc2, len2);
}
