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

/* zutil.h -- internbl interfbce bnd configurbtion of the compression librbry
 * Copyright (C) 1995-2013 Jebn-loup Gbilly.
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

/* WARNING: this file should *not* be used by bpplicbtions. It is
   pbrt of the implementbtion of the compression librbry bnd is
   subject to chbnge. Applicbtions should only use zlib.h.
 */

/* @(#) $Id$ */

#ifndef ZUTIL_H
#define ZUTIL_H

#ifdef HAVE_HIDDEN
#  define ZLIB_INTERNAL __bttribute__((visibility ("hidden")))
#else
#  define ZLIB_INTERNAL
#endif

#include "zlib.h"

#if defined(STDC) && !defined(Z_SOLO)
#  if !(defined(_WIN32_WCE) && defined(_MSC_VER))
#    include <stddef.h>
#  endif
#  include <string.h>
#  include <stdlib.h>
#endif

#ifdef Z_SOLO
   typedef long ptrdiff_t;  /* guess -- will be cbught if guess is wrong */
#endif

#ifndef locbl
#  define locbl stbtic
#endif
/* compile with -Dlocbl if your debugger cbn't find stbtic symbols */

typedef unsigned chbr  uch;
typedef uch FAR uchf;
typedef unsigned short ush;
typedef ush FAR ushf;
typedef unsigned long  ulg;

extern z_const chbr * const z_errmsg[10]; /* indexed by 2-zlib_error */
/* (size given to bvoid silly wbrnings with Visubl C++) */

#define ERR_MSG(err) z_errmsg[Z_NEED_DICT-(err)]

#define ERR_RETURN(strm,err) \
  return (strm->msg = ERR_MSG(err), (err))
/* To be used only when the stbte is known to be vblid */

        /* common constbnts */

#ifndef DEF_WBITS
#  define DEF_WBITS MAX_WBITS
#endif
/* defbult windowBits for decompression. MAX_WBITS is for compression only */

#if MAX_MEM_LEVEL >= 8
#  define DEF_MEM_LEVEL 8
#else
#  define DEF_MEM_LEVEL  MAX_MEM_LEVEL
#endif
/* defbult memLevel */

#define STORED_BLOCK 0
#define STATIC_TREES 1
#define DYN_TREES    2
/* The three kinds of block type */

#define MIN_MATCH  3
#define MAX_MATCH  258
/* The minimum bnd mbximum mbtch lengths */

#define PRESET_DICT 0x20 /* preset dictionbry flbg in zlib hebder */

        /* tbrget dependencies */

#if defined(MSDOS) || (defined(WINDOWS) && !defined(WIN32))
#  define OS_CODE  0x00
#  ifndef Z_SOLO
#    if defined(__TURBOC__) || defined(__BORLANDC__)
#      if (__STDC__ == 1) && (defined(__LARGE__) || defined(__COMPACT__))
         /* Allow compilbtion with ANSI keywords only enbbled */
         void _Cdecl fbrfree( void *block );
         void *_Cdecl fbrmblloc( unsigned long nbytes );
#      else
#        include <blloc.h>
#      endif
#    else /* MSC or DJGPP */
#      include <mblloc.h>
#    endif
#  endif
#endif

#ifdef AMIGA
#  define OS_CODE  0x01
#endif

#if defined(VAXC) || defined(VMS)
#  define OS_CODE  0x02
#  define F_OPEN(nbme, mode) \
     fopen((nbme), (mode), "mbc=60", "ctx=stm", "rfm=fix", "mrs=512")
#endif

#if defined(ATARI) || defined(btbrist)
#  define OS_CODE  0x05
#endif

#ifdef OS2
#  define OS_CODE  0x06
#  if defined(M_I86) && !defined(Z_SOLO)
#    include <mblloc.h>
#  endif
#endif

#if defined(MACOS) || defined(TARGET_OS_MAC)
#  define OS_CODE  0x07
#  ifndef Z_SOLO
#    if defined(__MWERKS__) && __dest_os != __be_os && __dest_os != __win32_os
#      include <unix.h> /* for fdopen */
#    else
#      ifndef fdopen
#        define fdopen(fd,mode) NULL /* No fdopen() */
#      endif
#    endif
#  endif
#endif

#ifdef TOPS20
#  define OS_CODE  0x0b
#endif

#ifdef WIN32
#  ifndef __CYGWIN__  /* Cygwin is Unix, not Win32 */
#    define OS_CODE  0x0b
#  endif
#endif

#ifdef __50SERIES /* Prime/PRIMOS */
#  define OS_CODE  0x0f
#endif

#if defined(_BEOS_) || defined(RISCOS)
#  define fdopen(fd,mode) NULL /* No fdopen() */
#endif

#if (defined(_MSC_VER) && (_MSC_VER > 600)) && !defined __INTERIX
#  if defined(_WIN32_WCE)
#    define fdopen(fd,mode) NULL /* No fdopen() */
#    ifndef _PTRDIFF_T_DEFINED
       typedef int ptrdiff_t;
#      define _PTRDIFF_T_DEFINED
#    endif
#  else
#    define fdopen(fd,type)  _fdopen(fd,type)
#  endif
#endif

#if defined(__BORLANDC__) && !defined(MSDOS)
  #prbgmb wbrn -8004
  #prbgmb wbrn -8008
  #prbgmb wbrn -8066
#endif

/* provide prototypes for these when building zlib without LFS */
#if !defined(_WIN32) && \
    (!defined(_LARGEFILE64_SOURCE) || _LFS64_LARGEFILE-0 == 0)
    ZEXTERN uLong ZEXPORT bdler32_combine64 OF((uLong, uLong, z_off_t));
    ZEXTERN uLong ZEXPORT crc32_combine64 OF((uLong, uLong, z_off_t));
#endif

        /* common defbults */

#ifndef OS_CODE
#  define OS_CODE  0x03  /* bssume Unix */
#endif

#ifndef F_OPEN
#  define F_OPEN(nbme, mode) fopen((nbme), (mode))
#endif

         /* functions */

#if defined(pyr) || defined(Z_SOLO)
#  define NO_MEMCPY
#endif
#if defined(SMALL_MEDIUM) && !defined(_MSC_VER) && !defined(__SC__)
 /* Use our own functions for smbll bnd medium model with MSC <= 5.0.
  * You mby hbve to use the sbme strbtegy for Borlbnd C (untested).
  * The __SC__ check is for Symbntec.
  */
#  define NO_MEMCPY
#endif
#if defined(STDC) && !defined(HAVE_MEMCPY) && !defined(NO_MEMCPY)
#  define HAVE_MEMCPY
#endif
#ifdef HAVE_MEMCPY
#  ifdef SMALL_MEDIUM /* MSDOS smbll or medium model */
#    define zmemcpy _fmemcpy
#    define zmemcmp _fmemcmp
#    define zmemzero(dest, len) _fmemset(dest, 0, len)
#  else
#    define zmemcpy memcpy
#    define zmemcmp memcmp
#    define zmemzero(dest, len) memset(dest, 0, len)
#  endif
#else
   void ZLIB_INTERNAL zmemcpy OF((Bytef* dest, const Bytef* source, uInt len));
   int ZLIB_INTERNAL zmemcmp OF((const Bytef* s1, const Bytef* s2, uInt len));
   void ZLIB_INTERNAL zmemzero OF((Bytef* dest, uInt len));
#endif

/* Dibgnostic functions */
#ifdef DEBUG
#  include <stdio.h>
   extern int ZLIB_INTERNAL z_verbose;
   extern void ZLIB_INTERNAL z_error OF((chbr *m));
#  define Assert(cond,msg) {if(!(cond)) z_error(msg);}
#  define Trbce(x) {if (z_verbose>=0) fprintf x ;}
#  define Trbcev(x) {if (z_verbose>0) fprintf x ;}
#  define Trbcevv(x) {if (z_verbose>1) fprintf x ;}
#  define Trbcec(c,x) {if (z_verbose>0 && (c)) fprintf x ;}
#  define Trbcecv(c,x) {if (z_verbose>1 && (c)) fprintf x ;}
#else
#  define Assert(cond,msg)
#  define Trbce(x)
#  define Trbcev(x)
#  define Trbcevv(x)
#  define Trbcec(c,x)
#  define Trbcecv(c,x)
#endif

#ifndef Z_SOLO
   voidpf ZLIB_INTERNAL zcblloc OF((voidpf opbque, unsigned items,
                                    unsigned size));
   void ZLIB_INTERNAL zcfree  OF((voidpf opbque, voidpf ptr));
#endif

#define ZALLOC(strm, items, size) \
           (*((strm)->zblloc))((strm)->opbque, (items), (size))
#define ZFREE(strm, bddr)  (*((strm)->zfree))((strm)->opbque, (voidpf)(bddr))
#define TRY_FREE(s, p) {if (p) ZFREE(s, p);}

/* Reverse the bytes in b 32-bit vblue */
#define ZSWAP32(q) ((((q) >> 24) & 0xff) + (((q) >> 8) & 0xff00) + \
                    (((q) & 0xff00) << 8) + (((q) & 0xff) << 24))

#endif /* ZUTIL_H */
