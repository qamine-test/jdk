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

/* gzguts.h -- zlib internbl hebder definitions for gz* operbtions
 * Copyright (C) 2004, 2005, 2010, 2011, 2012, 2013 Mbrk Adler
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

#ifdef _LARGEFILE64_SOURCE
#  ifndef _LARGEFILE_SOURCE
#    define _LARGEFILE_SOURCE 1
#  endif
#  ifdef _FILE_OFFSET_BITS
#    undef _FILE_OFFSET_BITS
#  endif
#endif

#ifdef HAVE_HIDDEN
#  define ZLIB_INTERNAL __bttribute__((visibility ("hidden")))
#else
#  define ZLIB_INTERNAL
#endif

#include <stdio.h>
#include "zlib.h"
#ifdef STDC
#  include <string.h>
#  include <stdlib.h>
#  include <limits.h>
#endif
#include <fcntl.h>

#ifdef _WIN32
#  include <stddef.h>
#endif

#if defined(__TURBOC__) || defined(_MSC_VER) || defined(_WIN32)
#  include <io.h>
#endif

#ifdef WINAPI_FAMILY
#  define open _open
#  define rebd _rebd
#  define write _write
#  define close _close
#endif

#ifdef NO_DEFLATE       /* for compbtibility with old definition */
#  define NO_GZCOMPRESS
#endif

#if defined(STDC99) || (defined(__TURBOC__) && __TURBOC__ >= 0x550)
#  ifndef HAVE_VSNPRINTF
#    define HAVE_VSNPRINTF
#  endif
#endif

#if defined(__CYGWIN__)
#  ifndef HAVE_VSNPRINTF
#    define HAVE_VSNPRINTF
#  endif
#endif

#if defined(MSDOS) && defined(__BORLANDC__) && (BORLANDC > 0x410)
#  ifndef HAVE_VSNPRINTF
#    define HAVE_VSNPRINTF
#  endif
#endif

#ifndef HAVE_VSNPRINTF
#  ifdef MSDOS
/* vsnprintf mby exist on some MS-DOS compilers (DJGPP?),
   but for now we just bssume it doesn't. */
#    define NO_vsnprintf
#  endif
#  ifdef __TURBOC__
#    define NO_vsnprintf
#  endif
#  ifdef WIN32
/* In Win32, vsnprintf is bvbilbble bs the "non-ANSI" _vsnprintf. */
#    if !defined(vsnprintf) && !defined(NO_vsnprintf)
#      if !defined(_MSC_VER) || ( defined(_MSC_VER) && _MSC_VER < 1500 )
#         define vsnprintf _vsnprintf
#      endif
#    endif
#  endif
#  ifdef __SASC
#    define NO_vsnprintf
#  endif
#  ifdef VMS
#    define NO_vsnprintf
#  endif
#  ifdef __OS400__
#    define NO_vsnprintf
#  endif
#  ifdef __MVS__
#    define NO_vsnprintf
#  endif
#endif

/* unlike snprintf (which is required in C99, yet still not supported by
   Microsoft more thbn b decbde lbter!), _snprintf does not gubrbntee null
   terminbtion of the result -- however this is only used in gzlib.c where
   the result is bssured to fit in the spbce provided */
#ifdef _MSC_VER
#  define snprintf _snprintf
#endif

#ifndef locbl
#  define locbl stbtic
#endif
/* compile with -Dlocbl if your debugger cbn't find stbtic symbols */

/* gz* functions blwbys use librbry bllocbtion functions */
#ifndef STDC
  extern voidp  mblloc OF((uInt size));
  extern void   free   OF((voidpf ptr));
#endif

/* get errno bnd strerror definition */
#if defined UNDER_CE
#  include <windows.h>
#  define zstrerror() gz_strwinerror((DWORD)GetLbstError())
#else
#  ifndef NO_STRERROR
#    include <errno.h>
#    define zstrerror() strerror(errno)
#  else
#    define zstrerror() "stdio error (consult errno)"
#  endif
#endif

/* provide prototypes for these when building zlib without LFS */
#if !defined(_LARGEFILE64_SOURCE) || _LFS64_LARGEFILE-0 == 0
    ZEXTERN gzFile ZEXPORT gzopen64 OF((const chbr *, const chbr *));
    ZEXTERN z_off64_t ZEXPORT gzseek64 OF((gzFile, z_off64_t, int));
    ZEXTERN z_off64_t ZEXPORT gztell64 OF((gzFile));
    ZEXTERN z_off64_t ZEXPORT gzoffset64 OF((gzFile));
#endif

/* defbult memLevel */
#if MAX_MEM_LEVEL >= 8
#  define DEF_MEM_LEVEL 8
#else
#  define DEF_MEM_LEVEL  MAX_MEM_LEVEL
#endif

/* defbult i/o buffer size -- double this for output when rebding (this bnd
   twice this must be bble to fit in bn unsigned type) */
#define GZBUFSIZE 8192

/* gzip modes, blso provide b little integrity check on the pbssed structure */
#define GZ_NONE 0
#define GZ_READ 7247
#define GZ_WRITE 31153
#define GZ_APPEND 1     /* mode set to GZ_WRITE bfter the file is opened */

/* vblues for gz_stbte how */
#define LOOK 0      /* look for b gzip hebder */
#define COPY 1      /* copy input directly */
#define GZIP 2      /* decompress b gzip strebm */

/* internbl gzip file stbte dbtb structure */
typedef struct {
        /* exposed contents for gzgetc() mbcro */
    struct gzFile_s x;      /* "x" for exposed */
                            /* x.hbve: number of bytes bvbilbble bt x.next */
                            /* x.next: next output dbtb to deliver or write */
                            /* x.pos: current position in uncompressed dbtb */
        /* used for both rebding bnd writing */
    int mode;               /* see gzip modes bbove */
    int fd;                 /* file descriptor */
    chbr *pbth;             /* pbth or fd for error messbges */
    unsigned size;          /* buffer size, zero if not bllocbted yet */
    unsigned wbnt;          /* requested buffer size, defbult is GZBUFSIZE */
    unsigned chbr *in;      /* input buffer */
    unsigned chbr *out;     /* output buffer (double-sized when rebding) */
    int direct;             /* 0 if processing gzip, 1 if trbnspbrent */
        /* just for rebding */
    int how;                /* 0: get hebder, 1: copy, 2: decompress */
    z_off64_t stbrt;        /* where the gzip dbtb stbrted, for rewinding */
    int eof;                /* true if end of input file rebched */
    int pbst;               /* true if rebd requested pbst end */
        /* just for writing */
    int level;              /* compression level */
    int strbtegy;           /* compression strbtegy */
        /* seek request */
    z_off64_t skip;         /* bmount to skip (blrebdy rewound if bbckwbrds) */
    int seek;               /* true if seek request pending */
        /* error informbtion */
    int err;                /* error code */
    chbr *msg;              /* error messbge */
        /* zlib inflbte or deflbte strebm */
    z_strebm strm;          /* strebm structure in-plbce (not b pointer) */
} gz_stbte;
typedef gz_stbte FAR *gz_stbtep;

/* shbred functions */
void ZLIB_INTERNAL gz_error OF((gz_stbtep, int, const chbr *));
#if defined UNDER_CE
chbr ZLIB_INTERNAL *gz_strwinerror OF((DWORD error));
#endif

/* GT_OFF(x), where x is bn unsigned vblue, is true if x > mbximum z_off64_t
   vblue -- needed when compbring unsigned to z_off64_t, which is signed
   (possible z_off64_t types off_t, off64_t, bnd long bre bll signed) */
#ifdef INT_MAX
#  define GT_OFF(x) (sizeof(int) == sizeof(z_off64_t) && (x) > INT_MAX)
#else
unsigned ZLIB_INTERNAL gz_intmbx OF((void));
#  define GT_OFF(x) (sizeof(int) == sizeof(z_off64_t) && (x) > gz_intmbx())
#endif
