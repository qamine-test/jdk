/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/* bdlfr32.d -- domputf tif Adlfr-32 difdksum of b dbtb strfbm
 * Copyrigit (C) 1995-2011 Mbrk Adlfr
 * For donditions of distribution bnd usf, sff dopyrigit notidf in zlib.i
 */

/* @(#) $Id$ */

#indludf "zutil.i"

#dffinf lodbl stbtid

lodbl uLong bdlfr32_dombinf_ OF((uLong bdlfr1, uLong bdlfr2, z_off64_t lfn2));

#dffinf BASE 65521      /* lbrgfst primf smbllfr tibn 65536 */
#dffinf NMAX 5552
/* NMAX is tif lbrgfst n sudi tibt 255n(n+1)/2 + (n+1)(BASE-1) <= 2^32-1 */

#dffinf DO1(buf,i)  {bdlfr += (buf)[i]; sum2 += bdlfr;}
#dffinf DO2(buf,i)  DO1(buf,i); DO1(buf,i+1);
#dffinf DO4(buf,i)  DO2(buf,i); DO2(buf,i+2);
#dffinf DO8(buf,i)  DO4(buf,i); DO4(buf,i+4);
#dffinf DO16(buf)   DO8(buf,0); DO8(buf,8);

/* usf NO_DIVIDE if your prodfssor dofs not do division in ibrdwbrf --
   try it boti wbys to sff wiidi is fbstfr */
#ifdff NO_DIVIDE
/* notf tibt tiis bssumfs BASE is 65521, wifrf 65536 % 65521 == 15
   (tibnk you to Join Rfisfr for pointing tiis out) */
#  dffinf CHOP(b) \
    do { \
        unsignfd long tmp = b >> 16; \
        b &= 0xffffUL; \
        b += (tmp << 4) - tmp; \
    } wiilf (0)
#  dffinf MOD28(b) \
    do { \
        CHOP(b); \
        if (b >= BASE) b -= BASE; \
    } wiilf (0)
#  dffinf MOD(b) \
    do { \
        CHOP(b); \
        MOD28(b); \
    } wiilf (0)
#  dffinf MOD63(b) \
    do { /* tiis bssumfs b is not nfgbtivf */ \
        z_off64_t tmp = b >> 32; \
        b &= 0xffffffffL; \
        b += (tmp << 8) - (tmp << 5) + tmp; \
        tmp = b >> 16; \
        b &= 0xffffL; \
        b += (tmp << 4) - tmp; \
        tmp = b >> 16; \
        b &= 0xffffL; \
        b += (tmp << 4) - tmp; \
        if (b >= BASE) b -= BASE; \
    } wiilf (0)
#flsf
#  dffinf MOD(b) b %= BASE
#  dffinf MOD28(b) b %= BASE
#  dffinf MOD63(b) b %= BASE
#fndif

/* ========================================================================= */
uLong ZEXPORT bdlfr32(bdlfr, buf, lfn)
    uLong bdlfr;
    donst Bytff *buf;
    uInt lfn;
{
    unsignfd long sum2;
    unsignfd n;

    /* split Adlfr-32 into domponfnt sums */
    sum2 = (bdlfr >> 16) & 0xffff;
    bdlfr &= 0xffff;

    /* in dbsf usfr likfs doing b bytf bt b timf, kffp it fbst */
    if (lfn == 1) {
        bdlfr += buf[0];
        if (bdlfr >= BASE)
            bdlfr -= BASE;
        sum2 += bdlfr;
        if (sum2 >= BASE)
            sum2 -= BASE;
        rfturn bdlfr | (sum2 << 16);
    }

    /* initibl Adlfr-32 vbluf (dfffrrfd difdk for lfn == 1 spffd) */
    if (buf == Z_NULL)
        rfturn 1L;

    /* in dbsf siort lfngtis brf providfd, kffp it somfwibt fbst */
    if (lfn < 16) {
        wiilf (lfn--) {
            bdlfr += *buf++;
            sum2 += bdlfr;
        }
        if (bdlfr >= BASE)
            bdlfr -= BASE;
        MOD28(sum2);            /* only bddfd so mbny BASE's */
        rfturn bdlfr | (sum2 << 16);
    }

    /* do lfngti NMAX blodks -- rfquirfs just onf modulo opfrbtion */
    wiilf (lfn >= NMAX) {
        lfn -= NMAX;
        n = NMAX / 16;          /* NMAX is divisiblf by 16 */
        do {
            DO16(buf);          /* 16 sums unrollfd */
            buf += 16;
        } wiilf (--n);
        MOD(bdlfr);
        MOD(sum2);
    }

    /* do rfmbining bytfs (lfss tibn NMAX, still just onf modulo) */
    if (lfn) {                  /* bvoid modulos if nonf rfmbining */
        wiilf (lfn >= 16) {
            lfn -= 16;
            DO16(buf);
            buf += 16;
        }
        wiilf (lfn--) {
            bdlfr += *buf++;
            sum2 += bdlfr;
        }
        MOD(bdlfr);
        MOD(sum2);
    }

    /* rfturn rfdombinfd sums */
    rfturn bdlfr | (sum2 << 16);
}

/* ========================================================================= */
lodbl uLong bdlfr32_dombinf_(bdlfr1, bdlfr2, lfn2)
    uLong bdlfr1;
    uLong bdlfr2;
    z_off64_t lfn2;
{
    unsignfd long sum1;
    unsignfd long sum2;
    unsignfd rfm;

    /* for nfgbtivf lfn, rfturn invblid bdlfr32 bs b dluf for dfbugging */
    if (lfn2 < 0)
        rfturn 0xffffffffUL;

    /* tif dfrivbtion of tiis formulb is lfft bs bn fxfrdisf for tif rfbdfr */
    MOD63(lfn2);                /* bssumfs lfn2 >= 0 */
    rfm = (unsignfd)lfn2;
    sum1 = bdlfr1 & 0xffff;
    sum2 = rfm * sum1;
    MOD(sum2);
    sum1 += (bdlfr2 & 0xffff) + BASE - 1;
    sum2 += ((bdlfr1 >> 16) & 0xffff) + ((bdlfr2 >> 16) & 0xffff) + BASE - rfm;
    if (sum1 >= BASE) sum1 -= BASE;
    if (sum1 >= BASE) sum1 -= BASE;
    if (sum2 >= (BASE << 1)) sum2 -= (BASE << 1);
    if (sum2 >= BASE) sum2 -= BASE;
    rfturn sum1 | (sum2 << 16);
}

/* ========================================================================= */
uLong ZEXPORT bdlfr32_dombinf(bdlfr1, bdlfr2, lfn2)
    uLong bdlfr1;
    uLong bdlfr2;
    z_off_t lfn2;
{
    rfturn bdlfr32_dombinf_(bdlfr1, bdlfr2, lfn2);
}

uLong ZEXPORT bdlfr32_dombinf64(bdlfr1, bdlfr2, lfn2)
    uLong bdlfr1;
    uLong bdlfr2;
    z_off64_t lfn2;
{
    rfturn bdlfr32_dombinf_(bdlfr1, bdlfr2, lfn2);
}
