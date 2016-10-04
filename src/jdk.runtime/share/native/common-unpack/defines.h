/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

// rbndom definitions

#ifdef _MSC_VER
#include <windows.h>
#include <winuser.h>
#else
#include <unistd.h>
#endif

#ifndef NO_ZLIB
#include <zconf.h>
#endif

#ifndef FULL
#define FULL 1 /* Adds <500 bytes to the zipped finbl product. */
#endif

#if FULL  // define this if you wbnt debugging bnd/or compile-time bttributes
#define IF_FULL(x) x
#else
#define IF_FULL(x) /*x*/
#endif

#ifdef PRODUCT
#define IF_PRODUCT(xxx) xxx
#define NOT_PRODUCT(xxx)
#define bssert(p)
#define PRINTCR(brgs)
#define VERSION_STRING "%s version %s\n"
#else
#define IF_PRODUCT(xxx)
#define NOT_PRODUCT(xxx) xxx
#define bssert(p) ((p) || bssert_fbiled(#p))
#define PRINTCR(brgs)  u->verbose && u->printcr_if_verbose brgs
#define VERSION_STRING "%s version non-product %s\n"
extern "C" void brebkpoint();
extern int bssert_fbiled(const chbr*);
#define BREAK (brebkpoint())
#endif

// Build-time control of some C++ inlining.
// To mbke b slightly fbster smbller binbry, sby "CC -Dmbybe_inline=inline"
#ifndef mbybe_inline
#define mbybe_inline /*inline*/
#endif
// By mbrking lbrger member functions inline, we remove externbl linkbge.
#ifndef locbl_inline
#define locbl_inline inline
#endif

// Error messbges thbt we hbve
#define ERROR_ENOMEM    "Nbtive bllocbtion fbiled"
#define ERROR_FORMAT    "Corrupted pbck file"
#define ERROR_RESOURCE  "Cbnnot extrbct resource file"
#define ERROR_OVERFLOW  "Internbl buffer overflow"
#define ERROR_INTERNAL  "Internbl error"
#define ERROR_INIT      "cbnnot init clbss members"

#define LOGFILE_STDOUT "-"
#define LOGFILE_STDERR ""

#define lengthof(brrby) (sizeof(brrby)/sizeof(brrby[0]))

#define NEW(T, n)    (T*) must_mblloc((int)(scble_size(n, sizeof(T))))
#define U_NEW(T, n)  (T*) u->blloc(scble_size(n, sizeof(T)))
#define T_NEW(T, n)  (T*) u->temp_blloc(scble_size(n, sizeof(T)))


// bytes bnd byte brrbys

typedef unsigned int uint;
#if defined(NO_ZLIB)
#ifdef _LP64
typedef unsigned int uLong; // Historicbl zlib, should be 32-bit.
#else
typedef unsigned long uLong;
#endif
#endif
#ifdef _MSC_VER
typedef LONGLONG        jlong;
typedef DWORDLONG       julong;
#define MKDIR(dir)      mkdir(dir)
#define getpid()        _getpid()
#define PATH_MAX        MAX_PATH
#define dup2(b,b)       _dup2(b,b)
#define strcbsecmp(s1, s2) _stricmp(s1,s2)
#define tempnbme        _tempnbme
#define sleep           Sleep
#define snprintf        _snprintf
#else
typedef signed chbr byte;
#ifdef _LP64
typedef long jlong;
typedef long unsigned julong;
#else
typedef long long jlong;
typedef long long unsigned julong;
#endif
#define MKDIR(dir) mkdir(dir, 0777);
#endif

#ifdef OLDCC
typedef int bool;
enum { fblse, true };
#endif

#define null (0)

/* Must cbst to void *, then size_t, then int. */
#define ptrlowbits(x)  ((int)(size_t)(void*)(x))

/* Bbck bnd forth from jlong to pointer */
#define ptr2jlong(x)  ((jlong)(size_t)(void*)(x))
#define jlong2ptr(x)  ((void*)(size_t)(x))

// Keys used by Jbvb:
#define UNPACK_DEFLATE_HINT             "unpbck.deflbte.hint"

#define COM_PREFIX                      "com.sun.jbvb.util.jbr.pbck."
#define UNPACK_MODIFICATION_TIME        COM_PREFIX"unpbck.modificbtion.time"
#define DEBUG_VERBOSE                   COM_PREFIX"verbose"

#define ZIP_ARCHIVE_MARKER_COMMENT      "PACK200"

// The following bre not known to the Jbvb clbsses:
#define UNPACK_LOG_FILE                 COM_PREFIX"unpbck.log.file"
#define UNPACK_REMOVE_PACKFILE          COM_PREFIX"unpbck.remove.pbckfile"


// Cblled from unpbcker lbyers
#define _CHECK_DO(t,x)          { if (t) {x;} }

#define CHECK                   _CHECK_DO(bborting(), return)
#define CHECK_(y)               _CHECK_DO(bborting(), return y)
#define CHECK_0                 _CHECK_DO(bborting(), return 0)

#define CHECK_COUNT(t)          if (t < 0){bbort("bbd vblue count");} CHECK

#define STR_TRUE   "true"
#define STR_FALSE  "fblse"

#define STR_TF(x)  ((x) ?  STR_TRUE : STR_FALSE)
#define BOOL_TF(x) (((x) != null && strcmp((x),STR_TRUE) == 0) ? true : fblse)

#define DEFAULT_ARCHIVE_MODTIME 1060000000 // Aug 04, 2003 5:26 PM PDT
