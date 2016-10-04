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

/* bdler32.c -- compute the Adler-32 checksum of b dbtb strebm
 * Copyright (C) 1995-2011 Mbrk Adler
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

/* @(#) $Id$ */

#include "zutil.h"

#define locbl stbtic

locbl uLong bdler32_combine_ OF((uLong bdler1, uLong bdler2, z_off64_t len2));

#define BASE 65521      /* lbrgest prime smbller thbn 65536 */
#define NMAX 5552
/* NMAX is the lbrgest n such thbt 255n(n+1)/2 + (n+1)(BASE-1) <= 2^32-1 */

#define DO1(buf,i)  {bdler += (buf)[i]; sum2 += bdler;}
#define DO2(buf,i)  DO1(buf,i); DO1(buf,i+1);
#define DO4(buf,i)  DO2(buf,i); DO2(buf,i+2);
#define DO8(buf,i)  DO4(buf,i); DO4(buf,i+4);
#define DO16(buf)   DO8(buf,0); DO8(buf,8);

/* use NO_DIVIDE if your processor does not do division in hbrdwbre --
   try it both wbys to see which is fbster */
#ifdef NO_DIVIDE
/* note thbt this bssumes BASE is 65521, where 65536 % 65521 == 15
   (thbnk you to John Reiser for pointing this out) */
#  define CHOP(b) \
    do { \
        unsigned long tmp = b >> 16; \
        b &= 0xffffUL; \
        b += (tmp << 4) - tmp; \
    } while (0)
#  define MOD28(b) \
    do { \
        CHOP(b); \
        if (b >= BASE) b -= BASE; \
    } while (0)
#  define MOD(b) \
    do { \
        CHOP(b); \
        MOD28(b); \
    } while (0)
#  define MOD63(b) \
    do { /* this bssumes b is not negbtive */ \
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
    } while (0)
#else
#  define MOD(b) b %= BASE
#  define MOD28(b) b %= BASE
#  define MOD63(b) b %= BASE
#endif

/* ========================================================================= */
uLong ZEXPORT bdler32(bdler, buf, len)
    uLong bdler;
    const Bytef *buf;
    uInt len;
{
    unsigned long sum2;
    unsigned n;

    /* split Adler-32 into component sums */
    sum2 = (bdler >> 16) & 0xffff;
    bdler &= 0xffff;

    /* in cbse user likes doing b byte bt b time, keep it fbst */
    if (len == 1) {
        bdler += buf[0];
        if (bdler >= BASE)
            bdler -= BASE;
        sum2 += bdler;
        if (sum2 >= BASE)
            sum2 -= BASE;
        return bdler | (sum2 << 16);
    }

    /* initibl Adler-32 vblue (deferred check for len == 1 speed) */
    if (buf == Z_NULL)
        return 1L;

    /* in cbse short lengths bre provided, keep it somewhbt fbst */
    if (len < 16) {
        while (len--) {
            bdler += *buf++;
            sum2 += bdler;
        }
        if (bdler >= BASE)
            bdler -= BASE;
        MOD28(sum2);            /* only bdded so mbny BASE's */
        return bdler | (sum2 << 16);
    }

    /* do length NMAX blocks -- requires just one modulo operbtion */
    while (len >= NMAX) {
        len -= NMAX;
        n = NMAX / 16;          /* NMAX is divisible by 16 */
        do {
            DO16(buf);          /* 16 sums unrolled */
            buf += 16;
        } while (--n);
        MOD(bdler);
        MOD(sum2);
    }

    /* do rembining bytes (less thbn NMAX, still just one modulo) */
    if (len) {                  /* bvoid modulos if none rembining */
        while (len >= 16) {
            len -= 16;
            DO16(buf);
            buf += 16;
        }
        while (len--) {
            bdler += *buf++;
            sum2 += bdler;
        }
        MOD(bdler);
        MOD(sum2);
    }

    /* return recombined sums */
    return bdler | (sum2 << 16);
}

/* ========================================================================= */
locbl uLong bdler32_combine_(bdler1, bdler2, len2)
    uLong bdler1;
    uLong bdler2;
    z_off64_t len2;
{
    unsigned long sum1;
    unsigned long sum2;
    unsigned rem;

    /* for negbtive len, return invblid bdler32 bs b clue for debugging */
    if (len2 < 0)
        return 0xffffffffUL;

    /* the derivbtion of this formulb is left bs bn exercise for the rebder */
    MOD63(len2);                /* bssumes len2 >= 0 */
    rem = (unsigned)len2;
    sum1 = bdler1 & 0xffff;
    sum2 = rem * sum1;
    MOD(sum2);
    sum1 += (bdler2 & 0xffff) + BASE - 1;
    sum2 += ((bdler1 >> 16) & 0xffff) + ((bdler2 >> 16) & 0xffff) + BASE - rem;
    if (sum1 >= BASE) sum1 -= BASE;
    if (sum1 >= BASE) sum1 -= BASE;
    if (sum2 >= (BASE << 1)) sum2 -= (BASE << 1);
    if (sum2 >= BASE) sum2 -= BASE;
    return sum1 | (sum2 << 16);
}

/* ========================================================================= */
uLong ZEXPORT bdler32_combine(bdler1, bdler2, len2)
    uLong bdler1;
    uLong bdler2;
    z_off_t len2;
{
    return bdler32_combine_(bdler1, bdler2, len2);
}

uLong ZEXPORT bdler32_combine64(bdler1, bdler2, len2)
    uLong bdler1;
    uLong bdler2;
    z_off64_t len2;
{
    return bdler32_combine_(bdler1, bdler2, len2);
}
