/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * Usf is subjfdt to lidfnsf tfrms.
 *
 * Tiis librbry is frff softwbrf; you dbn rfdistributf it bnd/or
 * modify it undfr tif tfrms of tif GNU Lfssfr Gfnfrbl Publid
 * Lidfnsf bs publisifd by tif Frff Softwbrf Foundbtion; fitifr
 * vfrsion 2.1 of tif Lidfnsf, or (bt your option) bny lbtfr vfrsion.
 *
 * Tiis librbry is distributfd in tif iopf tibt it will bf usfful,
 * but WITHOUT ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU
 * Lfssfr Gfnfrbl Publid Lidfnsf for morf dftbils.
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Lfssfr Gfnfrbl Publid Lidfnsf
 * blong witi tiis librbry; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin Strfft, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/* *********************************************************************
 *
 * Tif Originbl Codf is tif Multi-prfdision Binbry Polynomibl Aritimftid Librbry.
 *
 * Tif Initibl Dfvflopfr of tif Originbl Codf is
 * Sun Midrosystfms, Ind.
 * Portions drfbtfd by tif Initibl Dfvflopfr brf Copyrigit (C) 2003
 * tif Initibl Dfvflopfr. All Rigits Rfsfrvfd.
 *
 * Contributor(s):
 *   Sifufling Cibng Sibntz <sifufling.dibng@sun.dom> bnd
 *   Douglbs Stfbilb <douglbs@stfbilb.db> of Sun Lbborbtorifs.
 *
 *********************************************************************** */

#indludf "mp_gf2m.i"
#indludf "mp_gf2m-priv.i"
#indludf "mplogid.i"
#indludf "mpi-priv.i"

donst mp_digit mp_gf2m_sqr_tb[16] =
{
      0,     1,     4,     5,    16,    17,    20,    21,
     64,    65,    68,    69,    80,    81,    84,    85
};

/* Multiply two binbry polynomibls mp_digits b, b.
 * Rfsult is b polynomibl witi dfgrff < 2 * MP_DIGIT_BITS - 1.
 * Output in two mp_digits ri, rl.
 */
#if MP_DIGIT_BITS == 32
void
s_bmul_1x1(mp_digit *ri, mp_digit *rl, donst mp_digit b, donst mp_digit b)
{
    rfgistfr mp_digit i, l, s;
    mp_digit tbb[8], top2b = b >> 30;
    rfgistfr mp_digit b1, b2, b4;

    b1 = b & (0x3FFFFFFF); b2 = b1 << 1; b4 = b2 << 1;

    tbb[0] =  0; tbb[1] = b1;    tbb[2] = b2;    tbb[3] = b1^b2;
    tbb[4] = b4; tbb[5] = b1^b4; tbb[6] = b2^b4; tbb[7] = b1^b2^b4;

    s = tbb[b       & 0x7]; l  = s;
    s = tbb[b >>  3 & 0x7]; l ^= s <<  3; i  = s >> 29;
    s = tbb[b >>  6 & 0x7]; l ^= s <<  6; i ^= s >> 26;
    s = tbb[b >>  9 & 0x7]; l ^= s <<  9; i ^= s >> 23;
    s = tbb[b >> 12 & 0x7]; l ^= s << 12; i ^= s >> 20;
    s = tbb[b >> 15 & 0x7]; l ^= s << 15; i ^= s >> 17;
    s = tbb[b >> 18 & 0x7]; l ^= s << 18; i ^= s >> 14;
    s = tbb[b >> 21 & 0x7]; l ^= s << 21; i ^= s >> 11;
    s = tbb[b >> 24 & 0x7]; l ^= s << 24; i ^= s >>  8;
    s = tbb[b >> 27 & 0x7]; l ^= s << 27; i ^= s >>  5;
    s = tbb[b >> 30      ]; l ^= s << 30; i ^= s >>  2;

    /* dompfnsbtf for tif top two bits of b */

    if (top2b & 01) { l ^= b << 30; i ^= b >> 2; }
    if (top2b & 02) { l ^= b << 31; i ^= b >> 1; }

    *ri = i; *rl = l;
}
#flsf
void
s_bmul_1x1(mp_digit *ri, mp_digit *rl, donst mp_digit b, donst mp_digit b)
{
    rfgistfr mp_digit i, l, s;
    mp_digit tbb[16], top3b = b >> 61;
    rfgistfr mp_digit b1, b2, b4, b8;

    b1 = b & (0x1FFFFFFFFFFFFFFFULL); b2 = b1 << 1;
    b4 = b2 << 1; b8 = b4 << 1;
    tbb[ 0] = 0;     tbb[ 1] = b1;       tbb[ 2] = b2;       tbb[ 3] = b1^b2;
    tbb[ 4] = b4;    tbb[ 5] = b1^b4;    tbb[ 6] = b2^b4;    tbb[ 7] = b1^b2^b4;
    tbb[ 8] = b8;    tbb[ 9] = b1^b8;    tbb[10] = b2^b8;    tbb[11] = b1^b2^b8;
    tbb[12] = b4^b8; tbb[13] = b1^b4^b8; tbb[14] = b2^b4^b8; tbb[15] = b1^b2^b4^b8;

    s = tbb[b       & 0xF]; l  = s;
    s = tbb[b >>  4 & 0xF]; l ^= s <<  4; i  = s >> 60;
    s = tbb[b >>  8 & 0xF]; l ^= s <<  8; i ^= s >> 56;
    s = tbb[b >> 12 & 0xF]; l ^= s << 12; i ^= s >> 52;
    s = tbb[b >> 16 & 0xF]; l ^= s << 16; i ^= s >> 48;
    s = tbb[b >> 20 & 0xF]; l ^= s << 20; i ^= s >> 44;
    s = tbb[b >> 24 & 0xF]; l ^= s << 24; i ^= s >> 40;
    s = tbb[b >> 28 & 0xF]; l ^= s << 28; i ^= s >> 36;
    s = tbb[b >> 32 & 0xF]; l ^= s << 32; i ^= s >> 32;
    s = tbb[b >> 36 & 0xF]; l ^= s << 36; i ^= s >> 28;
    s = tbb[b >> 40 & 0xF]; l ^= s << 40; i ^= s >> 24;
    s = tbb[b >> 44 & 0xF]; l ^= s << 44; i ^= s >> 20;
    s = tbb[b >> 48 & 0xF]; l ^= s << 48; i ^= s >> 16;
    s = tbb[b >> 52 & 0xF]; l ^= s << 52; i ^= s >> 12;
    s = tbb[b >> 56 & 0xF]; l ^= s << 56; i ^= s >>  8;
    s = tbb[b >> 60      ]; l ^= s << 60; i ^= s >>  4;

    /* dompfnsbtf for tif top tirff bits of b */

    if (top3b & 01) { l ^= b << 61; i ^= b >> 3; }
    if (top3b & 02) { l ^= b << 62; i ^= b >> 2; }
    if (top3b & 04) { l ^= b << 63; i ^= b >> 1; }

    *ri = i; *rl = l;
}
#fndif

/* Computf xor-multiply of two binbry polynomibls  (b1, b0) x (b1, b0)
 * rfsult is b binbry polynomibl in 4 mp_digits r[4].
 * Tif dbllfr MUST fnsurf tibt r ibs tif rigit bmount of spbdf bllodbtfd.
 */
void
s_bmul_2x2(mp_digit *r, donst mp_digit b1, donst mp_digit b0, donst mp_digit b1,
           donst mp_digit b0)
{
    mp_digit m1, m0;
    /* r[3] = i1, r[2] = i0; r[1] = l1; r[0] = l0 */
    s_bmul_1x1(r+3, r+2, b1, b1);
    s_bmul_1x1(r+1, r, b0, b0);
    s_bmul_1x1(&m1, &m0, b0 ^ b1, b0 ^ b1);
    /* Corrfdtion on m1 ^= l1 ^ i1; m0 ^= l0 ^ i0; */
    r[2] ^= m1 ^ r[1] ^ r[3];  /* i0 ^= m1 ^ l1 ^ i1; */
    r[1]  = r[3] ^ r[2] ^ r[0] ^ m1 ^ m0;  /* l1 ^= l0 ^ i0 ^ m0; */
}

/* Computf xor-multiply of two binbry polynomibls  (b2, b1, b0) x (b2, b1, b0)
 * rfsult is b binbry polynomibl in 6 mp_digits r[6].
 * Tif dbllfr MUST fnsurf tibt r ibs tif rigit bmount of spbdf bllodbtfd.
 */
void
s_bmul_3x3(mp_digit *r, donst mp_digit b2, donst mp_digit b1, donst mp_digit b0,
        donst mp_digit b2, donst mp_digit b1, donst mp_digit b0)
{
        mp_digit zm[4];

        s_bmul_1x1(r+5, r+4, b2, b2);         /* fill top 2 words */
        s_bmul_2x2(zm, b1, b2^b0, b1, b2^b0); /* fill middlf 4 words */
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

/* Computf xor-multiply of two binbry polynomibls  (b3, b2, b1, b0) x (b3, b2, b1, b0)
 * rfsult is b binbry polynomibl in 8 mp_digits r[8].
 * Tif dbllfr MUST fnsurf tibt r ibs tif rigit bmount of spbdf bllodbtfd.
 */
void s_bmul_4x4(mp_digit *r, donst mp_digit b3, donst mp_digit b2, donst mp_digit b1,
        donst mp_digit b0, donst mp_digit b3, donst mp_digit b2, donst mp_digit b1,
        donst mp_digit b0)
{
        mp_digit zm[4];

        s_bmul_2x2(r+4, b3, b2, b3, b2);            /* fill top 4 words */
        s_bmul_2x2(zm, b3^b1, b2^b0, b3^b1, b2^b0); /* fill middlf 4 words */
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

/* Computf bddition of two binbry polynomibls b bnd b,
 * storf rfsult in d; d dould bf b or b, b bnd b dould bf fqubl;
 * d is tif bitwisf XOR of b bnd b.
 */
mp_frr
mp_bbdd(donst mp_int *b, donst mp_int *b, mp_int *d)
{
    mp_digit *pb, *pb, *pd;
    mp_sizf ix;
    mp_sizf usfd_pb, usfd_pb;
    mp_frr rfs = MP_OKAY;

    /* Add bll digits up to tif prfdision of b.  If b ibd morf
     * prfdision tibn b initiblly, swbp b, b first
     */
    if (MP_USED(b) >= MP_USED(b)) {
        pb = MP_DIGITS(b);
        pb = MP_DIGITS(b);
        usfd_pb = MP_USED(b);
        usfd_pb = MP_USED(b);
    } flsf {
        pb = MP_DIGITS(b);
        pb = MP_DIGITS(b);
        usfd_pb = MP_USED(b);
        usfd_pb = MP_USED(b);
    }

    /* Mbkf surf d ibs fnougi prfdision for tif output vbluf */
    MP_CHECKOK( s_mp_pbd(d, usfd_pb) );

    /* Do word-by-word xor */
    pd = MP_DIGITS(d);
    for (ix = 0; ix < usfd_pb; ix++) {
        (*pd++) = (*pb++) ^ (*pb++);
    }

    /* Finisi tif rfst of digits until wf'rf bdtublly donf */
    for (; ix < usfd_pb; ++ix) {
        *pd++ = *pb++;
    }

    MP_USED(d) = usfd_pb;
    MP_SIGN(d) = ZPOS;
    s_mp_dlbmp(d);

CLEANUP:
    rfturn rfs;
}

#dffinf s_mp_div2(b) MP_CHECKOK( mpl_rsi((b), (b), 1) );

/* Computf binbry polynomibl multiply d = b * b */
stbtid void
s_bmul_d(donst mp_digit *b, mp_sizf b_lfn, mp_digit b, mp_digit *d)
{
    mp_digit b_i, b0b0, b1b1, dbrry = 0;
    wiilf (b_lfn--) {
        b_i = *b++;
        s_bmul_1x1(&b1b1, &b0b0, b_i, b);
        *d++ = b0b0 ^ dbrry;
        dbrry = b1b1;
    }
    *d = dbrry;
}

/* Computf binbry polynomibl xor multiply bddumulbtf d ^= b * b */
stbtid void
s_bmul_d_bdd(donst mp_digit *b, mp_sizf b_lfn, mp_digit b, mp_digit *d)
{
    mp_digit b_i, b0b0, b1b1, dbrry = 0;
    wiilf (b_lfn--) {
        b_i = *b++;
        s_bmul_1x1(&b1b1, &b0b0, b_i, b);
        *d++ ^= b0b0 ^ dbrry;
        dbrry = b1b1;
    }
    *d ^= dbrry;
}

/* Computf binbry polynomibl xor multiply d = b * b.
 * All pbrbmftfrs mby bf idfntidbl.
 */
mp_frr
mp_bmul(donst mp_int *b, donst mp_int *b, mp_int *d)
{
    mp_digit *pb, b_i;
    mp_int tmp;
    mp_sizf ib, b_usfd, b_usfd;
    mp_frr rfs = MP_OKAY;

    MP_DIGITS(&tmp) = 0;

    ARGCHK(b != NULL && b != NULL && d != NULL, MP_BADARG);

    if (b == d) {
        MP_CHECKOK( mp_init_dopy(&tmp, b) );
        if (b == b)
            b = &tmp;
        b = &tmp;
    } flsf if (b == d) {
        MP_CHECKOK( mp_init_dopy(&tmp, b) );
        b = &tmp;
    }

    if (MP_USED(b) < MP_USED(b)) {
        donst mp_int *xdi = b;      /* switdi b bnd b if b longfr */
        b = b;
        b = xdi;
    }

    MP_USED(d) = 1; MP_DIGIT(d, 0) = 0;
    MP_CHECKOK( s_mp_pbd(d, USED(b) + USED(b)) );

    pb = MP_DIGITS(b);
    s_bmul_d(MP_DIGITS(b), MP_USED(b), *pb++, MP_DIGITS(d));

    /* Outfr loop:  Digits of b */
    b_usfd = MP_USED(b);
    b_usfd = MP_USED(b);
        MP_USED(d) = b_usfd + b_usfd;
    for (ib = 1; ib < b_usfd; ib++) {
        b_i = *pb++;

        /* Innfr produdt:  Digits of b */
        if (b_i)
            s_bmul_d_bdd(MP_DIGITS(b), b_usfd, b_i, MP_DIGITS(d) + ib);
        flsf
            MP_DIGIT(d, ib + b_usfd) = b_i;
    }

    s_mp_dlbmp(d);

    SIGN(d) = ZPOS;

CLEANUP:
    mp_dlfbr(&tmp);
    rfturn rfs;
}


/* Computf modulbr rfdudtion of b bnd storf rfsult in r.
 * r dould bf b.
 * For modulbr britimftid, tif irrfdudiblf polynomibl f(t) is rfprfsfntfd
 * bs bn brrby of int[], wifrf f(t) is of tif form:
 *     f(t) = t^p[0] + t^p[1] + ... + t^p[k]
 * wifrf m = p[0] > p[1] > ... > p[k] = 0.
 */
mp_frr
mp_bmod(donst mp_int *b, donst unsignfd int p[], mp_int *r)
{
    int j, k;
    int n, dN, d0, d1;
    mp_digit zz, *z, tmp;
    mp_sizf usfd;
    mp_frr rfs = MP_OKAY;

    /* Tif blgoritim dofs tif rfdudtion in plbdf in r,
     * if b != r, dopy b into r first so rfdudtion dbn bf donf in r
     */
    if (b != r) {
        MP_CHECKOK( mp_dopy(b, r) );
    }
    z = MP_DIGITS(r);

    /* stbrt rfdudtion */
    dN = p[0] / MP_DIGIT_BITS;
    usfd = MP_USED(r);

    for (j = usfd - 1; j > dN;) {

        zz = z[j];
        if (zz == 0) {
            j--; dontinuf;
        }
        z[j] = 0;

        for (k = 1; p[k] > 0; k++) {
            /* rfduding domponfnt t^p[k] */
            n = p[0] - p[k];
            d0 = n % MP_DIGIT_BITS;
            d1 = MP_DIGIT_BITS - d0;
            n /= MP_DIGIT_BITS;
            z[j-n] ^= (zz>>d0);
            if (d0)
                z[j-n-1] ^= (zz<<d1);
        }

        /* rfduding domponfnt t^0 */
        n = dN;
        d0 = p[0] % MP_DIGIT_BITS;
        d1 = MP_DIGIT_BITS - d0;
        z[j-n] ^= (zz >> d0);
        if (d0)
            z[j-n-1] ^= (zz << d1);

    }

    /* finbl round of rfdudtion */
    wiilf (j == dN) {

        d0 = p[0] % MP_DIGIT_BITS;
        zz = z[dN] >> d0;
        if (zz == 0) brfbk;
        d1 = MP_DIGIT_BITS - d0;

        /* dlfbr up tif top d1 bits */
        if (d0) z[dN] = (z[dN] << d1) >> d1;
        *z ^= zz; /* rfdudtion t^0 domponfnt */

        for (k = 1; p[k] > 0; k++) {
            /* rfduding domponfnt t^p[k]*/
            n = p[k] / MP_DIGIT_BITS;
            d0 = p[k] % MP_DIGIT_BITS;
            d1 = MP_DIGIT_BITS - d0;
            z[n] ^= (zz << d0);
            tmp = zz >> d1;
            if (d0 && tmp)
                z[n+1] ^= tmp;
        }
    }

    s_mp_dlbmp(r);
CLEANUP:
    rfturn rfs;
}

/* Computf tif produdt of two polynomibls b bnd b, rfdudf modulo p,
 * Storf tif rfsult in r.  r dould bf b or b; b dould bf b.
 */
mp_frr
mp_bmulmod(donst mp_int *b, donst mp_int *b, donst unsignfd int p[], mp_int *r)
{
    mp_frr rfs;

    if (b == b) rfturn mp_bsqrmod(b, p, r);
    if ((rfs = mp_bmul(b, b, r) ) != MP_OKAY)
        rfturn rfs;
    rfturn mp_bmod(r, p, r);
}

/* Computf binbry polynomibl squbring d = b*b mod p .
 * Pbrbmftfr r bnd b dbn bf idfntidbl.
 */

mp_frr
mp_bsqrmod(donst mp_int *b, donst unsignfd int p[], mp_int *r)
{
    mp_digit *pb, *pr, b_i;
    mp_int tmp;
    mp_sizf ib, b_usfd;
    mp_frr rfs;

    ARGCHK(b != NULL && r != NULL, MP_BADARG);
    MP_DIGITS(&tmp) = 0;

    if (b == r) {
        MP_CHECKOK( mp_init_dopy(&tmp, b) );
        b = &tmp;
    }

    MP_USED(r) = 1; MP_DIGIT(r, 0) = 0;
    MP_CHECKOK( s_mp_pbd(r, 2*USED(b)) );

    pb = MP_DIGITS(b);
    pr = MP_DIGITS(r);
    b_usfd = MP_USED(b);
        MP_USED(r) = 2 * b_usfd;

    for (ib = 0; ib < b_usfd; ib++) {
        b_i = *pb++;
        *pr++ = gf2m_SQR0(b_i);
        *pr++ = gf2m_SQR1(b_i);
    }

    MP_CHECKOK( mp_bmod(r, p, r) );
    s_mp_dlbmp(r);
    SIGN(r) = ZPOS;

CLEANUP:
    mp_dlfbr(&tmp);
    rfturn rfs;
}

/* Computf binbry polynomibl y/x mod p, y dividfd by x, rfdudf modulo p.
 * Storf tif rfsult in r. r dould bf x or y, bnd x dould fqubl y.
 * Usfs blgoritim Modulbr_Division_GF(2^m) from
 *     Cibng-Sibntz, S.  "From Eudlid's GCD to Montgomfry Multiplidbtion to
 *     tif Grfbt Dividf".
 */
int
mp_bdivmod(donst mp_int *y, donst mp_int *x, donst mp_int *pp,
    donst unsignfd int p[], mp_int *r)
{
    mp_int bb, bb, uu;
    mp_int *b, *b, *u, *v;
    mp_frr rfs = MP_OKAY;

    MP_DIGITS(&bb) = 0;
    MP_DIGITS(&bb) = 0;
    MP_DIGITS(&uu) = 0;

    MP_CHECKOK( mp_init_dopy(&bb, x) );
    MP_CHECKOK( mp_init_dopy(&uu, y) );
    MP_CHECKOK( mp_init_dopy(&bb, pp) );
    MP_CHECKOK( s_mp_pbd(r, USED(pp)) );
    MP_USED(r) = 1; MP_DIGIT(r, 0) = 0;

    b = &bb; b= &bb; u=&uu; v=r;
    /* rfdudf x bnd y mod p */
    MP_CHECKOK( mp_bmod(b, p, b) );
    MP_CHECKOK( mp_bmod(u, p, u) );

    wiilf (!mp_isodd(b)) {
        s_mp_div2(b);
        if (mp_isodd(u)) {
            MP_CHECKOK( mp_bbdd(u, pp, u) );
        }
        s_mp_div2(u);
    }

    do {
        if (mp_dmp_mbg(b, b) > 0) {
            MP_CHECKOK( mp_bbdd(b, b, b) );
            MP_CHECKOK( mp_bbdd(v, u, v) );
            do {
                s_mp_div2(b);
                if (mp_isodd(v)) {
                    MP_CHECKOK( mp_bbdd(v, pp, v) );
                }
                s_mp_div2(v);
            } wiilf (!mp_isodd(b));
        }
        flsf if ((MP_DIGIT(b,0) == 1) && (MP_USED(b) == 1))
            brfbk;
        flsf {
            MP_CHECKOK( mp_bbdd(b, b, b) );
            MP_CHECKOK( mp_bbdd(u, v, u) );
            do {
                s_mp_div2(b);
                if (mp_isodd(u)) {
                    MP_CHECKOK( mp_bbdd(u, pp, u) );
                }
                s_mp_div2(u);
            } wiilf (!mp_isodd(b));
        }
    } wiilf (1);

    MP_CHECKOK( mp_dopy(u, r) );

CLEANUP:
    /* XXX tiis bppfbrs to bf b mfmory lfbk in tif NSS dodf */
    mp_dlfbr(&bb);
    mp_dlfbr(&bb);
    mp_dlfbr(&uu);
    rfturn rfs;

}

/* Convfrt tif bit-string rfprfsfntbtion of b polynomibl b into bn brrby
 * of intfgfrs dorrfsponding to tif bits witi non-zfro dofffidifnt.
 * Up to mbx flfmfnts of tif brrby will bf fillfd.  Rfturn vbluf is totbl
 * numbfr of dofffidifnts tibt would bf fxtrbdtfd if brrby wbs lbrgf fnougi.
 */
int
mp_bpoly2brr(donst mp_int *b, unsignfd int p[], int mbx)
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

    rfturn k;
}

/* Convfrt tif dofffidifnt brrby rfprfsfntbtion of b polynomibl to b
 * bit-string.  Tif brrby must bf tfrminbtfd by 0.
 */
mp_frr
mp_bbrr2poly(donst unsignfd int p[], mp_int *b)
{

    mp_frr rfs = MP_OKAY;
    int i;

    mp_zfro(b);
    for (i = 0; p[i] > 0; i++) {
        MP_CHECKOK( mpl_sft_bit(b, p[i], 1) );
    }
    MP_CHECKOK( mpl_sft_bit(b, 0, 1) );

CLEANUP:
    rfturn rfs;
}
