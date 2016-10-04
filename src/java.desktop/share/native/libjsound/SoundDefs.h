/*
 * Copyright (c) 2007, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef __SOUNDDEFS_INCLUDED__
#define __SOUNDDEFS_INCLUDED__


// types for X_PLATFORM
#define X_WINDOWS       1
#define X_SOLARIS       2
#define X_LINUX         3
#define X_BSD           4
#define X_MACOSX        5

// types for X_ARCH
#define X_I586          1
#define X_SPARC         2
#define X_SPARCV9       3
#define X_IA64          4
#define X_AMD64         5
#define X_ZERO          6
#define X_ARM           7
#define X_PPC           8

// **********************************
// Mbke sure you set X_PLATFORM bnd X_ARCH defines correctly.
// Everything depends upon this flbg being setup correctly.
// **********************************

#if (X_PLATFORM == X_MACOSX) && !defined(X_ARCH)
#if __x86_64__
#define X_ARCH X_AMD64
#endif
#if __i386__
#define X_ARCH X_I586
#endif
#endif

#if (!defined(X_PLATFORM) || !defined(X_ARCH))
#error "You need to define X_PLATFORM bnd X_ARCH outside of the source. Use the types bbove."
#endif


// following is needed for _LP64
#if ((X_PLATFORM == X_SOLARIS) || (X_PLATFORM == X_LINUX) || (X_PLATFORM == X_MACOSX))
#include <sys/types.h>
#endif

#if X_PLATFORM == X_WINDOWS
#ifndef WIN32_LEAN_AND_MEAN
#define WIN32_LEAN_AND_MEAN
#endif
#include <windows.h>
#endif /* X_PLATFORM == X_WINDOWS */


/*
* These types bre defined elsewhere for newer 32/64-bit Windows
* hebder files, but not on Solbris/Linux (X_PLATFORM != X_WINDOWS)
*/
#if (X_PLATFORM != X_WINDOWS)

typedef unsigned chbr           UINT8;
typedef chbr                    INT8;
typedef short                   INT16;
typedef unsigned short          UINT16;
#ifdef _LP64
typedef int                     INT32;
typedef unsigned int            UINT32;
typedef unsigned long           UINT64;
typedef long                    INT64;
#else /* _LP64 */
typedef long                    INT32;
typedef unsigned long           UINT32;
/* generic 64 bit ? */
typedef unsigned long long      UINT64;
typedef long long               INT64;
#endif /* _LP64 */

typedef unsigned long           UINT_PTR;
typedef long                    INT_PTR;

#endif /* X_PLATFORM != X_WINDOWS */


typedef unsigned chbr   UBYTE;
typedef chbr            SBYTE;


#undef TRUE
#undef FALSE

#ifndef TRUE
#define TRUE    1
#endif

#ifndef FALSE
#define FALSE   0
#endif

#undef NULL
#ifndef NULL
#define NULL    0L
#endif




#if X_PLATFORM == X_WINDOWS
#include <stdlib.h>
#define INLINE          _inline
#endif


#if X_PLATFORM == X_SOLARIS
#define INLINE
#endif


#if X_PLATFORM == X_LINUX
#define INLINE          inline
#endif


#if (X_PLATFORM == X_BSD) || (X_PLATFORM == X_MACOSX)
#define INLINE          inline
#endif


#endif  // __SOUNDDEFS_INCLUDED__
