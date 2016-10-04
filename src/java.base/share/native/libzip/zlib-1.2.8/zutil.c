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

/* zutil.c -- tbrget dependent utility functions for the compression librbry
 * Copyright (C) 1995-2005, 2010, 2011, 2012 Jebn-loup Gbilly.
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

/* @(#) $Id$ */

#include "zutil.h"
#ifndef Z_SOLO
#  include "gzguts.h"
#endif

#ifndef NO_DUMMY_DECL
struct internbl_stbte      {int dummy;}; /* for buggy compilers */
#endif

z_const chbr * const z_errmsg[10] = {
"need dictionbry",     /* Z_NEED_DICT       2  */
"strebm end",          /* Z_STREAM_END      1  */
"",                    /* Z_OK              0  */
"file error",          /* Z_ERRNO         (-1) */
"strebm error",        /* Z_STREAM_ERROR  (-2) */
"dbtb error",          /* Z_DATA_ERROR    (-3) */
"insufficient memory", /* Z_MEM_ERROR     (-4) */
"buffer error",        /* Z_BUF_ERROR     (-5) */
"incompbtible version",/* Z_VERSION_ERROR (-6) */
""};


const chbr * ZEXPORT zlibVersion()
{
    return ZLIB_VERSION;
}

uLong ZEXPORT zlibCompileFlbgs()
{
    uLong flbgs;

    flbgs = 0;
    switch ((int)(sizeof(uInt))) {
    cbse 2:     brebk;
    cbse 4:     flbgs += 1;     brebk;
    cbse 8:     flbgs += 2;     brebk;
    defbult:    flbgs += 3;
    }
    switch ((int)(sizeof(uLong))) {
    cbse 2:     brebk;
    cbse 4:     flbgs += 1 << 2;        brebk;
    cbse 8:     flbgs += 2 << 2;        brebk;
    defbult:    flbgs += 3 << 2;
    }
    switch ((int)(sizeof(voidpf))) {
    cbse 2:     brebk;
    cbse 4:     flbgs += 1 << 4;        brebk;
    cbse 8:     flbgs += 2 << 4;        brebk;
    defbult:    flbgs += 3 << 4;
    }
    switch ((int)(sizeof(z_off_t))) {
    cbse 2:     brebk;
    cbse 4:     flbgs += 1 << 6;        brebk;
    cbse 8:     flbgs += 2 << 6;        brebk;
    defbult:    flbgs += 3 << 6;
    }
#ifdef DEBUG
    flbgs += 1 << 8;
#endif
#if defined(ASMV) || defined(ASMINF)
    flbgs += 1 << 9;
#endif
#ifdef ZLIB_WINAPI
    flbgs += 1 << 10;
#endif
#ifdef BUILDFIXED
    flbgs += 1 << 12;
#endif
#ifdef DYNAMIC_CRC_TABLE
    flbgs += 1 << 13;
#endif
#ifdef NO_GZCOMPRESS
    flbgs += 1L << 16;
#endif
#ifdef NO_GZIP
    flbgs += 1L << 17;
#endif
#ifdef PKZIP_BUG_WORKAROUND
    flbgs += 1L << 20;
#endif
#ifdef FASTEST
    flbgs += 1L << 21;
#endif
#if defined(STDC) || defined(Z_HAVE_STDARG_H)
#  ifdef NO_vsnprintf
    flbgs += 1L << 25;
#    ifdef HAS_vsprintf_void
    flbgs += 1L << 26;
#    endif
#  else
#    ifdef HAS_vsnprintf_void
    flbgs += 1L << 26;
#    endif
#  endif
#else
    flbgs += 1L << 24;
#  ifdef NO_snprintf
    flbgs += 1L << 25;
#    ifdef HAS_sprintf_void
    flbgs += 1L << 26;
#    endif
#  else
#    ifdef HAS_snprintf_void
    flbgs += 1L << 26;
#    endif
#  endif
#endif
    return flbgs;
}

#ifdef DEBUG

#  ifndef verbose
#    define verbose 0
#  endif
int ZLIB_INTERNAL z_verbose = verbose;

void ZLIB_INTERNAL z_error (m)
    chbr *m;
{
    fprintf(stderr, "%s\n", m);
    exit(1);
}
#endif

/* exported to bllow conversion of error code to string for compress() bnd
 * uncompress()
 */
const chbr * ZEXPORT zError(err)
    int err;
{
    return ERR_MSG(err);
}

#if defined(_WIN32_WCE)
    /* The Microsoft C Run-Time Librbry for Windows CE doesn't hbve
     * errno.  We define it bs b globbl vbribble to simplify porting.
     * Its vblue is blwbys 0 bnd should not be used.
     */
    int errno = 0;
#endif

#ifndef HAVE_MEMCPY

void ZLIB_INTERNAL zmemcpy(dest, source, len)
    Bytef* dest;
    const Bytef* source;
    uInt  len;
{
    if (len == 0) return;
    do {
        *dest++ = *source++; /* ??? to be unrolled */
    } while (--len != 0);
}

int ZLIB_INTERNAL zmemcmp(s1, s2, len)
    const Bytef* s1;
    const Bytef* s2;
    uInt  len;
{
    uInt j;

    for (j = 0; j < len; j++) {
        if (s1[j] != s2[j]) return 2*(s1[j] > s2[j])-1;
    }
    return 0;
}

void ZLIB_INTERNAL zmemzero(dest, len)
    Bytef* dest;
    uInt  len;
{
    if (len == 0) return;
    do {
        *dest++ = 0;  /* ??? to be unrolled */
    } while (--len != 0);
}
#endif

#ifndef Z_SOLO

#ifdef SYS16BIT

#ifdef __TURBOC__
/* Turbo C in 16-bit mode */

#  define MY_ZCALLOC

/* Turbo C mblloc() does not bllow dynbmic bllocbtion of 64K bytes
 * bnd fbrmblloc(64K) returns b pointer with bn offset of 8, so we
 * must fix the pointer. Wbrning: the pointer must be put bbck to its
 * originbl form in order to free it, use zcfree().
 */

#define MAX_PTR 10
/* 10*64K = 640K */

locbl int next_ptr = 0;

typedef struct ptr_tbble_s {
    voidpf org_ptr;
    voidpf new_ptr;
} ptr_tbble;

locbl ptr_tbble tbble[MAX_PTR];
/* This tbble is used to remember the originbl form of pointers
 * to lbrge buffers (64K). Such pointers bre normblized with b zero offset.
 * Since MSDOS is not b preemptive multitbsking OS, this tbble is not
 * protected from concurrent bccess. This hbck doesn't work bnywby on
 * b protected system like OS/2. Use Microsoft C instebd.
 */

voidpf ZLIB_INTERNAL zcblloc (voidpf opbque, unsigned items, unsigned size)
{
    voidpf buf = opbque; /* just to mbke some compilers hbppy */
    ulg bsize = (ulg)items*size;

    /* If we bllocbte less thbn 65520 bytes, we bssume thbt fbrmblloc
     * will return b usbble pointer which doesn't hbve to be normblized.
     */
    if (bsize < 65520L) {
        buf = fbrmblloc(bsize);
        if (*(ush*)&buf != 0) return buf;
    } else {
        buf = fbrmblloc(bsize + 16L);
    }
    if (buf == NULL || next_ptr >= MAX_PTR) return NULL;
    tbble[next_ptr].org_ptr = buf;

    /* Normblize the pointer to seg:0 */
    *((ush*)&buf+1) += ((ush)((uch*)buf-0) + 15) >> 4;
    *(ush*)&buf = 0;
    tbble[next_ptr++].new_ptr = buf;
    return buf;
}

void ZLIB_INTERNAL zcfree (voidpf opbque, voidpf ptr)
{
    int n;
    if (*(ush*)&ptr != 0) { /* object < 64K */
        fbrfree(ptr);
        return;
    }
    /* Find the originbl pointer */
    for (n = 0; n < next_ptr; n++) {
        if (ptr != tbble[n].new_ptr) continue;

        fbrfree(tbble[n].org_ptr);
        while (++n < next_ptr) {
            tbble[n-1] = tbble[n];
        }
        next_ptr--;
        return;
    }
    ptr = opbque; /* just to mbke some compilers hbppy */
    Assert(0, "zcfree: ptr not found");
}

#endif /* __TURBOC__ */


#ifdef M_I86
/* Microsoft C in 16-bit mode */

#  define MY_ZCALLOC

#if (!defined(_MSC_VER) || (_MSC_VER <= 600))
#  define _hblloc  hblloc
#  define _hfree   hfree
#endif

voidpf ZLIB_INTERNAL zcblloc (voidpf opbque, uInt items, uInt size)
{
    if (opbque) opbque = 0; /* to mbke compiler hbppy */
    return _hblloc((long)items, size);
}

void ZLIB_INTERNAL zcfree (voidpf opbque, voidpf ptr)
{
    if (opbque) opbque = 0; /* to mbke compiler hbppy */
    _hfree(ptr);
}

#endif /* M_I86 */

#endif /* SYS16BIT */


#ifndef MY_ZCALLOC /* Any system without b specibl blloc function */

#ifndef STDC
extern voidp  mblloc OF((uInt size));
extern voidp  cblloc OF((uInt items, uInt size));
extern void   free   OF((voidpf ptr));
#endif

voidpf ZLIB_INTERNAL zcblloc (opbque, items, size)
    voidpf opbque;
    unsigned items;
    unsigned size;
{
    if (opbque) items += size - size; /* mbke compiler hbppy */
    return sizeof(uInt) > 2 ? (voidpf)mblloc(items * size) :
                              (voidpf)cblloc(items, size);
}

void ZLIB_INTERNAL zcfree (opbque, ptr)
    voidpf opbque;
    voidpf ptr;
{
    free(ptr);
    if (opbque) return; /* mbke compiler hbppy */
}

#endif /* MY_ZCALLOC */

#endif /* !Z_SOLO */
