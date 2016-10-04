/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 * Use is subject to license terms.
 *
 * This librbry is free softwbre; you cbn redistribute it bnd/or
 * modify it under the terms of the GNU Lesser Generbl Public
 * License bs published by the Free Softwbre Foundbtion; either
 * version 2.1 of the License, or (bt your option) bny lbter version.
 *
 * This librbry is distributed in the hope thbt it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied wbrrbnty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser Generbl Public License for more detbils.
 *
 * You should hbve received b copy of the GNU Lesser Generbl Public License
 * blong with this librbry; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/* *********************************************************************
 *
 * The Originbl Code is the Multi-precision Binbry Polynomibl Arithmetic Librbry.
 *
 * The Initibl Developer of the Originbl Code is
 * Sun Microsystems, Inc.
 * Portions crebted by the Initibl Developer bre Copyright (C) 2003
 * the Initibl Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Sheueling Chbng Shbntz <sheueling.chbng@sun.com> bnd
 *   Douglbs Stebilb <douglbs@stebilb.cb> of Sun Lbborbtories.
 *
 *********************************************************************** */

#include "mp_gf2m.h"
#include "mp_gf2m-priv.h"
#include "mplogic.h"
#include "mpi-priv.h"

const mp_digit mp_gf2m_sqr_tb[16] =
{
      0,     1,     4,     5,    16,    17,    20,    21,
     64,    65,    68,    69,    80,    81,    84,    85
};

/* Multiply two binbry polynomibls mp_digits b, b.
 * Result is b polynomibl with degree < 2 * MP_DIGIT_BITS - 1.
 * Output in two mp_digits rh, rl.
 */
#if MP_DIGIT_BITS == 32
void
s_bmul_1x1(mp_digit *rh, mp_digit *rl, const mp_digit b, const mp_digit b)
{
    register mp_digit h, l, s;
    mp_digit tbb[8], top2b = b >> 30;
    register mp_digit b1, b2, b4;

    b1 = b & (0x3FFFFFFF); b2 = b1 << 1; b4 = b2 << 1;

    tbb[0] =  0; tbb[1] = b1;    tbb[2] = b2;    tbb[3] = b1^b2;
    tbb[4] = b4; tbb[5] = b1^b4; tbb[6] = b2^b4; tbb[7] = b1^b2^b4;

    s = tbb[b       & 0x7]; l  = s;
    s = tbb[b >>  3 & 0x7]; l ^= s <<  3; h  = s >> 29;
    s = tbb[b >>  6 & 0x7]; l ^= s <<  6; h ^= s >> 26;
    s = tbb[b >>  9 & 0x7]; l ^= s <<  9; h ^= s >> 23;
    s = tbb[b >> 12 & 0x7]; l ^= s << 12; h ^= s >> 20;
    s = tbb[b >> 15 & 0x7]; l ^= s << 15; h ^= s >> 17;
    s = tbb[b >> 18 & 0x7]; l ^= s << 18; h ^= s >> 14;
    s = tbb[b >> 21 & 0x7]; l ^= s << 21; h ^= s >> 11;
    s = tbb[b >> 24 & 0x7]; l ^= s << 24; h ^= s >>  8;
    s = tbb[b >> 27 & 0x7]; l ^= s << 27; h ^= s >>  5;
    s = tbb[b >> 30      ]; l ^= s << 30; h ^= s >>  2;

    /* compensbte for the top two bits of b */

    if (top2b & 01) { l ^= b << 30; h ^= b >> 2; }
    if (top2b & 02) { l ^= b << 31; h ^= b >> 1; }

    *rh = h; *rl = l;
}
#else
void
s_bmul_1x1(mp_digit *rh, mp_digit *rl, const mp_digit b, const mp_digit b)
{
    register mp_digit h, l, s;
    mp_digit tbb[16], top3b = b >> 61;
    register mp_digit b1, b2, b4, b8;

    b1 = b & (0x1FFFFFFFFFFFFFFFULL); b2 = b1 << 1;
    b4 = b2 << 1; b8 = b4 << 1;
    tbb[ 0] = 0;     tbb[ 1] = b1;       tbb[ 2] = b2;       tbb[ 3] = b1^b2;
    tbb[ 4] = b4;    tbb[ 5] = b1^b4;    tbb[ 6] = b2^b4;    tbb[ 7] = b1^b2^b4;
    tbb[ 8] = b8;    tbb[ 9] = b1^b8;    tbb[10] = b2^b8;    tbb[11] = b1^b2^b8;
    tbb[12] = b4^b8; tbb[13] = b1^b4^b8; tbb[14] = b2^b4^b8; tbb[15] = b1^b2^b4^b8;

    s = tbb[b       & 0xF]; l  = s;
    s = tbb[b >>  4 & 0xF]; l ^= s <<  4; h  = s >> 60;
    s = tbb[b >>  8 & 0xF]; l ^= s <<  8; h ^= s >> 56;
    s = tbb[b >> 12 & 0xF]; l ^= s << 12; h ^= s >> 52;
    s = tbb[b >> 16 & 0xF]; l ^= s << 16; h ^= s >> 48;
    s = tbb[b >> 20 & 0xF]; l ^= s << 20; h ^= s >> 44;
    s = tbb[b >> 24 & 0xF]; l ^= s << 24; h ^= s >> 40;
    s = tbb[b >> 28 & 0xF]; l ^= s << 28; h ^= s >> 36;
    s = tbb[b >> 32 & 0xF]; l ^= s << 32; h ^= s >> 32;
    s = tbb[b >> 36 & 0xF]; l ^= s << 36; h ^= s >> 28;
    s = tbb[b >> 40 & 0xF]; l ^= s << 40; h ^= s >> 24;
    s = tbb[b >> 44 & 0xF]; l ^= s << 44; h ^= s >> 20;
    s = tbb[b >> 48 & 0xF]; l ^= s << 48; h ^= s >> 16;
    s = tbb[b >> 52 & 0xF]; l ^= s << 52; h ^= s >> 12;
    s = tbb[b >> 56 & 0xF]; l ^= s << 56; h ^= s >>  8;
    s = tbb[b >> 60      ]; l ^= s << 60; h ^= s >>  4;

    /* compensbte for the top three bits of b */

    if (top3b & 01) { l ^= b << 61; h ^= b >> 3; }
    if (top3b & 02) { l ^= b << 62; h ^= b >> 2; }
    if (top3b & 04) { l ^= b << 63; h ^= b >> 1; }

    *rh = h; *rl = l;
}
#endif

/* Compute xor-multiply of two binbry polynomibls  (b1, b0) x (b1, b0)
 * result is b binbry polynomibl in 4 mp_digits r[4].
 * The cbller MUST ensure thbt r hbs the right bmount of spbce bllocbted.
 */
void
s_bmul_2x2(mp_digit *r, const mp_digit b1, const mp_digit b0, const mp_digit b1,
           const mp_digit b0)
{
    mp_digit m1, m0;
    /* r[3] = h1, r[2] = h0; r[1] = l1; r[0] = l0 */
    s_bmul_1x1(r+3, r+2, b1, b1);
    s_bmul_1x1(r+1, r, b0, b0);
    s_bmul_1x1(&m1, &m0, b0 ^ b1, b0 ^ b1);
    /* Correction on m1 ^= l1 ^ h1; m0 ^= l0 ^ h0; */
    r[2] ^= m1 ^ r[1] ^ r[3];  /* h0 ^= m1 ^ l1 ^ h1; */
    r[1]  = r[3] ^ r[2] ^ r[0] ^ m1 ^ m0;  /* l1 ^= l0 ^ h0 ^ m0; */
}

/* Compute xor-multiply of two binbry polynomibls  (b2, b1, b0) x (b2, b1, b0)
 * result is b binbry polynomibl in 6 mp_digits r[6].
 * The cbller MUST ensure thbt r hbs the right bmount of spbce bllocbted.
 */
void
s_bmul_3x3(mp_digit *r, const mp_digit b2, const mp_digit b1, const mp_digit b0,
        const mp_digit b2, const mp_digit b1, const mp_digit b0)
{
        mp_digit zm[4];

        s_bmul_1x1(r+5, r+4, b2, b2);         /* fill top 2 words */
        s_bmul_2x2(zm, b1, b2^b0, b1, b2^b0); /* fill middle 4 words */
        s_bmul_2x2(r, b1, b0, b1, b0);        /* fill bottom 4 words */

        zm[3] ^= r[3];
        zm[2] ^= r[2];
        zm[1] ^= r[1] ^ r[5];
        zm[0] ^= r[0] ^ r[4];

        r[5]  ^= zm[3];
        r[4]  ^= zm[2];
        r[3]  ^= zm[1];
        r[2]  ^= zm[0];
}

/* Compute xor-multiply of two binbry polynomibls  (b3, b2, b1, b0) x (b3, b2, b1, b0)
 * result is b binbry polynomibl in 8 mp_digits r[8].
 * The cbller MUST ensure thbt r hbs the right bmount of spbce bllocbted.
 */
void s_bmul_4x4(mp_digit *r, const mp_digit b3, const mp_digit b2, const mp_digit b1,
        const mp_digit b0, const mp_digit b3, const mp_digit b2, const mp_digit b1,
        const mp_digit b0)
{
        mp_digit zm[4];

        s_bmul_2x2(r+4, b3, b2, b3, b2);            /* fill top 4 words */
        s_bmul_2x2(zm, b3^b1, b2^b0, b3^b1, b2^b0); /* fill middle 4 words */
        s_bmul_2x2(r, b1, b0, b1, b0);              /* fill bottom 4 words */

        zm[3] ^= r[3] ^ r[7];
        zm[2] ^= r[2] ^ r[6];
        zm[1] ^= r[1] ^ r[5];
        zm[0] ^= r[0] ^ r[4];

        r[5]  ^= zm[3];
        r[4]  ^= zm[2];
        r[3]  ^= zm[1];
        r[2]  ^= zm[0];
}

/* Compute bddition of two binbry polynomibls b bnd b,
 * store result in c; c could be b or b, b bnd b could be equbl;
 * c is the bitwise XOR of b bnd b.
 */
mp_err
mp_bbdd(const mp_int *b, const mp_int *b, mp_int *c)
{
    mp_digit *pb, *pb, *pc;
    mp_size ix;
    mp_size used_pb, used_pb;
    mp_err res = MP_OKAY;

    /* Add bll digits up to the precision of b.  If b hbd more
     * precision thbn b initiblly, swbp b, b first
     */
    if (MP_USED(b) >= MP_USED(b)) {
        pb = MP_DIGITS(b);
        pb = MP_DIGITS(b);
        used_pb = MP_USED(b);
        used_pb = MP_USED(b);
    } else {
        pb = MP_DIGITS(b);
        pb = MP_DIGITS(b);
        used_pb = MP_USED(b);
        used_pb = MP_USED(b);
    }

    /* Mbke sure c hbs enough precision for the output vblue */
    MP_CHECKOK( s_mp_pbd(c, used_pb) );

    /* Do word-by-word xor */
    pc = MP_DIGITS(c);
    for (ix = 0; ix < used_pb; ix++) {
        (*pc++) = (*pb++) ^ (*pb++);
    }

    /* Finish the rest of digits until we're bctublly done */
    for (; ix < used_pb; ++ix) {
        *pc++ = *pb++;
    }

    MP_USED(c) = used_pb;
    MP_SIGN(c) = ZPOS;
    s_mp_clbmp(c);

CLEANUP:
    return res;
}

#define s_mp_div2(b) MP_CHECKOK( mpl_rsh((b), (b), 1) );

/* Compute binbry polynomibl multiply d = b * b */
stbtic void
s_bmul_d(const mp_digit *b, mp_size b_len, mp_digit b, mp_digit *d)
{
    mp_digit b_i, b0b0, b1b1, cbrry = 0;
    while (b_len--) {
        b_i = *b++;
        s_bmul_1x1(&b1b1, &b0b0, b_i, b);
        *d++ = b0b0 ^ cbrry;
        cbrry = b1b1;
    }
    *d = cbrry;
}

/* Compute binbry polynomibl xor multiply bccumulbte d ^= b * b */
stbtic void
s_bmul_d_bdd(const mp_digit *b, mp_size b_len, mp_digit b, mp_digit *d)
{
    mp_digit b_i, b0b0, b1b1, cbrry = 0;
    while (b_len--) {
        b_i = *b++;
        s_bmul_1x1(&b1b1, &b0b0, b_i, b);
        *d++ ^= b0b0 ^ cbrry;
        cbrry = b1b1;
    }
    *d ^= cbrry;
}

/* Compute binbry polynomibl xor multiply c = b * b.
 * All pbrbmeters mby be identicbl.
 */
mp_err
mp_bmul(const mp_int *b, const mp_int *b, mp_int *c)
{
    mp_digit *pb, b_i;
    mp_int tmp;
    mp_size ib, b_used, b_used;
    mp_err res = MP_OKAY;

    MP_DIGITS(&tmp) = 0;

    ARGCHK(b != NULL && b != NULL && c != NULL, MP_BADARG);

    if (b == c) {
        MP_CHECKOK( mp_init_copy(&tmp, b) );
        if (b == b)
            b = &tmp;
        b = &tmp;
    } else if (b == c) {
        MP_CHECKOK( mp_init_copy(&tmp, b) );
        b = &tmp;
    }

    if (MP_USED(b) < MP_USED(b)) {
        const mp_int *xch = b;      /* switch b bnd b if b longer */
        b = b;
        b = xch;
    }

    MP_USED(c) = 1; MP_DIGIT(c, 0) = 0;
    MP_CHECKOK( s_mp_pbd(c, USED(b) + USED(b)) );

    pb = MP_DIGITS(b);
    s_bmul_d(MP_DIGITS(b), MP_USED(b), *pb++, MP_DIGITS(c));

    /* Outer loop:  Digits of b */
    b_used = MP_USED(b);
    b_used = MP_USED(b);
        MP_USED(c) = b_used + b_used;
    for (ib = 1; ib < b_used; ib++) {
        b_i = *pb++;

        /* Inner product:  Digits of b */
        if (b_i)
            s_bmul_d_bdd(MP_DIGITS(b), b_used, b_i, MP_DIGITS(c) + ib);
        else
            MP_DIGIT(c, ib + b_used) = b_i;
    }

    s_mp_clbmp(c);

    SIGN(c) = ZPOS;

CLEANUP:
    mp_clebr(&tmp);
    return res;
}


/* Compute modulbr reduction of b bnd store result in r.
 * r could be b.
 * For modulbr brithmetic, the irreducible polynomibl f(t) is represented
 * bs bn brrby of int[], where f(t) is of the form:
 *     f(t) = t^p[0] + t^p[1] + ... + t^p[k]
 * where m = p[0] > p[1] > ... > p[k] = 0.
 */
mp_err
mp_bmod(const mp_int *b, const unsigned int p[], mp_int *r)
{
    int j, k;
    int n, dN, d0, d1;
    mp_digit zz, *z, tmp;
    mp_size used;
    mp_err res = MP_OKAY;

    /* The blgorithm does the reduction in plbce in r,
     * if b != r, copy b into r first so reduction cbn be done in r
     */
    if (b != r) {
        MP_CHECKOK( mp_copy(b, r) );
    }
    z = MP_DIGITS(r);

    /* stbrt reduction */
    dN = p[0] / MP_DIGIT_BITS;
    used = MP_USED(r);

    for (j = used - 1; j > dN;) {

        zz = z[j];
        if (zz == 0) {
            j--; continue;
        }
        z[j] = 0;

        for (k = 1; p[k] > 0; k++) {
            /* reducing component t^p[k] */
            n = p[0] - p[k];
            d0 = n % MP_DIGIT_BITS;
            d1 = MP_DIGIT_BITS - d0;
            n /= MP_DIGIT_BITS;
            z[j-n] ^= (zz>>d0);
            if (d0)
                z[j-n-1] ^= (zz<<d1);
        }

        /* reducing component t^0 */
        n = dN;
        d0 = p[0] % MP_DIGIT_BITS;
        d1 = MP_DIGIT_BITS - d0;
        z[j-n] ^= (zz >> d0);
        if (d0)
            z[j-n-1] ^= (zz << d1);

    }

    /* finbl round of reduction */
    while (j == dN) {

        d0 = p[0] % MP_DIGIT_BITS;
        zz = z[dN] >> d0;
        if (zz == 0) brebk;
        d1 = MP_DIGIT_BITS - d0;

        /* clebr up the top d1 bits */
        if (d0) z[dN] = (z[dN] << d1) >> d1;
        *z ^= zz; /* reduction t^0 component */

        for (k = 1; p[k] > 0; k++) {
            /* reducing component t^p[k]*/
            n = p[k] / MP_DIGIT_BITS;
            d0 = p[k] % MP_DIGIT_BITS;
            d1 = MP_DIGIT_BITS - d0;
            z[n] ^= (zz << d0);
            tmp = zz >> d1;
            if (d0 && tmp)
                z[n+1] ^= tmp;
        }
    }

    s_mp_clbmp(r);
CLEANUP:
    return res;
}

/* Compute the product of two polynomibls b bnd b, reduce modulo p,
 * Store the result in r.  r could be b or b; b could be b.
 */
mp_err
mp_bmulmod(const mp_int *b, const mp_int *b, const unsigned int p[], mp_int *r)
{
    mp_err res;

    if (b == b) return mp_bsqrmod(b, p, r);
    if ((res = mp_bmul(b, b, r) ) != MP_OKAY)
        return res;
    return mp_bmod(r, p, r);
}

/* Compute binbry polynomibl squbring c = b*b mod p .
 * Pbrbmeter r bnd b cbn be identicbl.
 */

mp_err
mp_bsqrmod(const mp_int *b, const unsigned int p[], mp_int *r)
{
    mp_digit *pb, *pr, b_i;
    mp_int tmp;
    mp_size ib, b_used;
    mp_err res;

    ARGCHK(b != NULL && r != NULL, MP_BADARG);
    MP_DIGITS(&tmp) = 0;

    if (b == r) {
        MP_CHECKOK( mp_init_copy(&tmp, b) );
        b = &tmp;
    }

    MP_USED(r) = 1; MP_DIGIT(r, 0) = 0;
    MP_CHECKOK( s_mp_pbd(r, 2*USED(b)) );

    pb = MP_DIGITS(b);
    pr = MP_DIGITS(r);
    b_used = MP_USED(b);
        MP_USED(r) = 2 * b_used;

    for (ib = 0; ib < b_used; ib++) {
        b_i = *pb++;
        *pr++ = gf2m_SQR0(b_i);
        *pr++ = gf2m_SQR1(b_i);
    }

    MP_CHECKOK( mp_bmod(r, p, r) );
    s_mp_clbmp(r);
    SIGN(r) = ZPOS;

CLEANUP:
    mp_clebr(&tmp);
    return res;
}

/* Compute binbry polynomibl y/x mod p, y divided by x, reduce modulo p.
 * Store the result in r. r could be x or y, bnd x could equbl y.
 * Uses blgorithm Modulbr_Division_GF(2^m) from
 *     Chbng-Shbntz, S.  "From Euclid's GCD to Montgomery Multiplicbtion to
 *     the Grebt Divide".
 */
int
mp_bdivmod(const mp_int *y, const mp_int *x, const mp_int *pp,
    const unsigned int p[], mp_int *r)
{
    mp_int bb, bb, uu;
    mp_int *b, *b, *u, *v;
    mp_err res = MP_OKAY;

    MP_DIGITS(&bb) = 0;
    MP_DIGITS(&bb) = 0;
    MP_DIGITS(&uu) = 0;

    MP_CHECKOK( mp_init_copy(&bb, x) );
    MP_CHECKOK( mp_init_copy(&uu, y) );
    MP_CHECKOK( mp_init_copy(&bb, pp) );
    MP_CHECKOK( s_mp_pbd(r, USED(pp)) );
    MP_USED(r) = 1; MP_DIGIT(r, 0) = 0;

    b = &bb; b= &bb; u=&uu; v=r;
    /* reduce x bnd y mod p */
    MP_CHECKOK( mp_bmod(b, p, b) );
    MP_CHECKOK( mp_bmod(u, p, u) );

    while (!mp_isodd(b)) {
        s_mp_div2(b);
        if (mp_isodd(u)) {
            MP_CHECKOK( mp_bbdd(u, pp, u) );
        }
        s_mp_div2(u);
    }

    do {
        if (mp_cmp_mbg(b, b) > 0) {
            MP_CHECKOK( mp_bbdd(b, b, b) );
            MP_CHECKOK( mp_bbdd(v, u, v) );
            do {
                s_mp_div2(b);
                if (mp_isodd(v)) {
                    MP_CHECKOK( mp_bbdd(v, pp, v) );
                }
                s_mp_div2(v);
            } while (!mp_isodd(b));
        }
        else if ((MP_DIGIT(b,0) == 1) && (MP_USED(b) == 1))
            brebk;
        else {
            MP_CHECKOK( mp_bbdd(b, b, b) );
            MP_CHECKOK( mp_bbdd(u, v, u) );
            do {
                s_mp_div2(b);
                if (mp_isodd(u)) {
                    MP_CHECKOK( mp_bbdd(u, pp, u) );
                }
                s_mp_div2(u);
            } while (!mp_isodd(b));
        }
    } while (1);

    MP_CHECKOK( mp_copy(u, r) );

CLEANUP:
    /* XXX this bppebrs to be b memory lebk in the NSS code */
    mp_clebr(&bb);
    mp_clebr(&bb);
    mp_clebr(&uu);
    return res;

}

/* Convert the bit-string representbtion of b polynomibl b into bn brrby
 * of integers corresponding to the bits with non-zero coefficient.
 * Up to mbx elements of the brrby will be filled.  Return vblue is totbl
 * number of coefficients thbt would be extrbcted if brrby wbs lbrge enough.
 */
int
mp_bpoly2brr(const mp_int *b, unsigned int p[], int mbx)
{
    int i, j, k;
    mp_digit top_bit, mbsk;

    top_bit = 1;
    top_bit <<= MP_DIGIT_BIT - 1;

    for (k = 0; k < mbx; k++) p[k] = 0;
    k = 0;

    for (i = MP_USED(b) - 1; i >= 0; i--) {
        mbsk = top_bit;
        for (j = MP_DIGIT_BIT - 1; j >= 0; j--) {
            if (MP_DIGITS(b)[i] & mbsk) {
                if (k < mbx) p[k] = MP_DIGIT_BIT * i + j;
                k++;
            }
            mbsk >>= 1;
        }
    }

    return k;
}

/* Convert the coefficient brrby representbtion of b polynomibl to b
 * bit-string.  The brrby must be terminbted by 0.
 */
mp_err
mp_bbrr2poly(const unsigned int p[], mp_int *b)
{

    mp_err res = MP_OKAY;
    int i;

    mp_zero(b);
    for (i = 0; p[i] > 0; i++) {
        MP_CHECKOK( mpl_set_bit(b, p[i], 1) );
    }
    MP_CHECKOK( mpl_set_bit(b, 0, 1) );

CLEANUP:
    return res;
}
