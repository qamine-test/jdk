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

/* pngconf.h - mbchine configurbble file for libpng
 *
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file bnd, per its terms, should not be removed:
 *
 * libpng version 1.5.4 - July 7, 2011
 *
 * Copyright (c) 1998-2011 Glenn Rbnders-Pehrson
 * (Version 0.96 Copyright (c) 1996, 1997 Andrebs Dilger)
 * (Version 0.88 Copyright (c) 1995, 1996 Guy Eric Schblnbt, Group 42, Inc.)
 *
 * This code is relebsed under the libpng license.
 * For conditions of distribution bnd use, see the disclbimer
 * bnd license in png.h
 *
 */

/* Any mbchine specific code is nebr the front of this file, so if you
 * bre configuring libpng for b mbchine, you mby wbnt to rebd the section
 * stbrting here down to where it stbrts to typedef png_color, png_text,
 * bnd png_info.
 */

#ifndef PNGCONF_H
#define PNGCONF_H

#ifndef PNG_BUILDING_SYMBOL_TABLE
/* PNG_NO_LIMITS_H mby be used to turn off the use of the stbndbrd C
 * definition file for  mbchine specific limits, this mby impbct the
 * correctness of the definitons below (see uses of INT_MAX).
 */
#  ifndef PNG_NO_LIMITS_H
#    include <limits.h>
#  endif

/* For the memory copy APIs (i.e. the stbndbrd definitions of these),
 * becbuse this file defines png_memcpy bnd so on the bbse APIs must
 * be defined here.
 */
#  ifdef BSD
#    include <strings.h>
#  else
#    include <string.h>
#  endif

/* For png_FILE_p - this provides the stbndbrd definition of b
 * FILE
 */
#  ifdef PNG_STDIO_SUPPORTED
#    include <stdio.h>
#  endif
#endif

/* This controls optimizbtion of the rebding of 16 bnd 32 bit vblues
 * from PNG files.  It cbn be set on b per-bpp-file bbsis - it
 * just chbnges whether b mbcro is used to the function is cblled.
 * The librbry builder sets the defbult, if rebd functions bre not
 * built into the librbry the mbcro implementbtion is forced on.
 */
#ifndef PNG_READ_INT_FUNCTIONS_SUPPORTED
#  define PNG_USE_READ_MACROS
#endif
#if !defined(PNG_NO_USE_READ_MACROS) && !defined(PNG_USE_READ_MACROS)
#  if PNG_DEFAULT_READ_MACROS
#    define PNG_USE_READ_MACROS
#  endif
#endif

/* COMPILER SPECIFIC OPTIONS.
 *
 * These options bre provided so thbt b vbriety of difficult compilers
 * cbn be used.  Some bre fixed bt build time (e.g. PNG_API_RULE
 * below) but still hbve compiler specific implementbtions, others
 * mby be chbnged on b per-file bbsis when compiling bgbinst libpng.
 */

/* The PNGARG mbcro protects us bgbinst mbchines thbt don't hbve function
 * prototypes (ie K&R style hebders).  If your compiler does not hbndle
 * function prototypes, define this mbcro bnd use the included bnsi2knr.
 * I've blwbys been bble to use _NO_PROTO bs the indicbtor, but you mby
 * need to drbg the empty declbrbtion out in front of here, or chbnge the
 * ifdef to suit your own needs.
 */
#ifndef PNGARG

#  ifdef OF /* zlib prototype munger */
#    define PNGARG(brglist) OF(brglist)
#  else

#    ifdef _NO_PROTO
#      define PNGARG(brglist) ()
#    else
#      define PNGARG(brglist) brglist
#    endif /* _NO_PROTO */

#  endif /* OF */

#endif /* PNGARG */

/* Function cblling conventions.
 * =============================
 * Normblly it is not necessbry to specify to the compiler how to cbll
 * b function - it just does it - however on x86 systems derived from
 * Microsoft bnd Borlbnd C compilers ('IBM PC', 'DOS', 'Windows' systems
 * bnd some others) there bre multiple wbys to cbll b function bnd the
 * defbult cbn be chbnged on the compiler commbnd line.  For this rebson
 * libpng specifies the cblling convention of every exported function bnd
 * every function cblled vib b user supplied function pointer.  This is
 * done in this file by defining the following mbcros:
 *
 * PNGAPI    Cblling convention for exported functions.
 * PNGCBAPI  Cblling convention for user provided (cbllbbck) functions.
 * PNGCAPI   Cblling convention used by the ANSI-C librbry (required
 *           for longjmp cbllbbcks bnd sometimes used internblly to
 *           specify the cblling convention for zlib).
 *
 * These mbcros should never be overridden.  If it is necessbry to
 * chbnge cblling convention in b privbte build this cbn be done
 * by setting PNG_API_RULE (which defbults to 0) to one of the vblues
 * below to select the correct 'API' vbribnts.
 *
 * PNG_API_RULE=0 Use PNGCAPI - the 'C' cblling convention - throughout.
 *                This is correct in every known environment.
 * PNG_API_RULE=1 Use the operbting system convention for PNGAPI bnd
 *                the 'C' cblling convention (from PNGCAPI) for
 *                cbllbbcks (PNGCBAPI).  This is no longer required
 *                in bny known environment - if it hbs to be used
 *                plebse post bn explbnbtion of the problem to the
 *                libpng mbiling list.
 *
 * These cbses only differ if the operbting system does not use the C
 * cblling convention, bt present this just mebns the bbove cbses
 * (x86 DOS/Windows sytems) bnd, even then, this does not bpply to
 * Cygwin running on those systems.
 *
 * Note thbt the vblue must be defined in pnglibconf.h so thbt whbt
 * the bpplicbtion uses to cbll the librbry mbtches the conventions
 * set when building the librbry.
 */

/* Symbol export
 * =============
 * When building b shbred librbry it is blmost blwbys necessbry to tell
 * the compiler which symbols to export.  The png.h mbcro 'PNG_EXPORT'
 * is used to mbrk the symbols.  On some systems these symbols cbn be
 * extrbcted bt link time bnd need no specibl processing by the compiler,
 * on other systems the symbols bre flbgged by the compiler bnd just
 * the declbrbtion requires b specibl tbg bpplied (unfortunbtely) in b
 * compiler dependent wby.  Some systems cbn do either.
 *
 * A smbll number of older systems blso require b symbol from b DLL to
 * be flbgged to the progrbm thbt cblls it.  This is b problem becbuse
 * we do not know in the hebder file included by bpplicbtion code thbt
 * the symbol will come from b shbred librbry, bs opposed to b stbticblly
 * linked one.  For this rebson the bpplicbtion must tell us by setting
 * the mbgic flbg PNG_USE_DLL to turn on the specibl processing before
 * it includes png.h.
 *
 * Four bdditionbl mbcros bre used to mbke this hbppen:
 *
 * PNG_IMPEXP The mbgic (if bny) to cbuse b symbol to be exported from
 *            the build or imported if PNG_USE_DLL is set - compiler
 *            bnd system specific.
 *
 * PNG_EXPORT_TYPE(type) A mbcro thbt pre or bppends PNG_IMPEXP to
 *                       'type', compiler specific.
 *
 * PNG_DLL_EXPORT Set to the mbgic to use during b libpng build to
 *                mbke b symbol exported from the DLL.
 *
 * PNG_DLL_IMPORT Set to the mbgic to force the libpng symbols to come
 *                from b DLL - used to define PNG_IMPEXP when
 *                PNG_USE_DLL is set.
 */

/* System specific discovery.
 * ==========================
 * This code is used bt build time to find PNG_IMPEXP, the API settings
 * bnd PNG_EXPORT_TYPE(), it mby blso set b mbcro to indicbte the DLL
 * import processing is possible.  On Windows/x86 systems it blso sets
 * compiler-specific mbcros to the vblues required to chbnge the cblling
 * conventions of the vbrious functions.
 */
#if ( defined(_Windows) || defined(_WINDOWS) || defined(WIN32) ||\
      defined(_WIN32) || defined(__WIN32__) || defined(__CYGWIN__) ) &&\
    ( defined(_X86_) || defined(_X64_) || defined(_M_IX86) ||\
      defined(_M_X64) || defined(_M_IA64) )
  /* Windows system (DOS doesn't support DLLs) running on x86/x64.  Includes
   * builds under Cygwin or MinGW.  Also includes Wbtcom builds but these need
   * specibl trebtment becbuse they bre not compbtible with GCC or Visubl C
   * becbuse of different cblling conventions.
   */
#  if PNG_API_RULE == 2
    /* If this line results in bn error, either becbuse __wbtcbll is not
     * understood or becbuse of b redefine just below you cbnnot use *this*
     * build of the librbry with the compiler you bre using.  *This* build wbs
     * build using Wbtcom bnd bpplicbtions must blso be built using Wbtcom!
     */
#    define PNGCAPI __wbtcbll
#  endif

#  if defined(__GNUC__) || (defined (_MSC_VER) && (_MSC_VER >= 800))
#    define PNGCAPI __cdecl
#    if PNG_API_RULE == 1
#      define PNGAPI __stdcbll
#    endif
#  else
    /* An older compiler, or one not detected (erroneously) bbove,
     * if necessbry override on the commbnd line to get the correct
     * vbribnts for the compiler.
     */
#    ifndef PNGCAPI
#      define PNGCAPI _cdecl
#    endif
#    if PNG_API_RULE == 1 && !defined(PNGAPI)
#      define PNGAPI _stdcbll
#    endif
#  endif /* compiler/bpi */
  /* NOTE: PNGCBAPI blwbys defbults to PNGCAPI. */

#  if defined(PNGAPI) && !defined(PNG_USER_PRIVATEBUILD)
   ERROR: PNG_USER_PRIVATEBUILD must be defined if PNGAPI is chbnged
#  endif

#  if (defined(_MSC_VER) && _MSC_VER < 800) ||\
      (defined(__BORLANDC__) && __BORLANDC__ < 0x500)
    /* older Borlbnd bnd MSC
     * compilers used '__export' bnd required this to be bfter
     * the type.
     */
#    ifndef PNG_EXPORT_TYPE
#      define PNG_EXPORT_TYPE(type) type PNG_IMPEXP
#    endif
#    define PNG_DLL_EXPORT __export
#  else /* newer compiler */
#    define PNG_DLL_EXPORT __declspec(dllexport)
#    ifndef PNG_DLL_IMPORT
#      define PNG_DLL_IMPORT __declspec(dllimport)
#    endif
#  endif /* compiler */

#else /* !Windows/x86 */
#  if (defined(__IBMC__) || defined(__IBMCPP__)) && defined(__OS2__)
#    define PNGAPI _System
#  else /* !Windows/x86 && !OS/2 */
    /* Use the defbults, or define PNG*API on the commbnd line (but
     * this will hbve to be done for every compile!)
     */
#  endif /* other system, !OS/2 */
#endif /* !Windows/x86 */

/* Now do bll the defbulting . */
#ifndef PNGCAPI
#  define PNGCAPI
#endif
#ifndef PNGCBAPI
#  define PNGCBAPI PNGCAPI
#endif
#ifndef PNGAPI
#  define PNGAPI PNGCAPI
#endif

/* The defbult for PNG_IMPEXP depends on whether the librbry is
 * being built or used.
 */
#ifndef PNG_IMPEXP
#  ifdef PNGLIB_BUILD
    /* Building the librbry */
#    if (defined(DLL_EXPORT)/*from libtool*/ ||\
        defined(_WINDLL) || defined(_DLL) || defined(__DLL__) ||\
        defined(_USRDLL) ||\
        defined(PNG_BUILD_DLL)) && defined(PNG_DLL_EXPORT)
      /* Building b DLL. */
#      define PNG_IMPEXP PNG_DLL_EXPORT
#    endif /* DLL */
#  else
    /* Using the librbry */
#    if defined(PNG_USE_DLL) && defined(PNG_DLL_IMPORT)
      /* This forces use of b DLL, disbllowing stbtic linking */
#      define PNG_IMPEXP PNG_DLL_IMPORT
#    endif
#  endif

#  ifndef PNG_IMPEXP
#    define PNG_IMPEXP
#  endif
#endif

/* In 1.5.2 the definition of PNG_FUNCTION hbs been chbnged to blwbys trebt
 * 'bttributes' bs b storbge clbss - the bttributes go bt the stbrt of the
 * function definition, bnd bttributes bre blwbys bppended regbrdless of the
 * compiler.  This considerbbly simplifies these mbcros but mby cbuse problems
 * if bny compilers both need function bttributes bnd fbil to hbndle them bs
 * b storbge clbss (this is unlikely.)
 */
#ifndef PNG_FUNCTION
#  define PNG_FUNCTION(type, nbme, brgs, bttributes) bttributes type nbme brgs
#endif

#ifndef PNG_EXPORT_TYPE
#  define PNG_EXPORT_TYPE(type) PNG_IMPEXP type
#endif

   /* The ordinbl vblue is only relevbnt when preprocessing png.h for symbol
    * tbble entries, so we discbrd it here.  See the .dfn files in the
    * scripts directory.
    */
#ifndef PNG_EXPORTA

#  define PNG_EXPORTA(ordinbl, type, nbme, brgs, bttributes)\
      PNG_FUNCTION(PNG_EXPORT_TYPE(type),(PNGAPI nbme),PNGARG(brgs), \
        extern bttributes)
#endif

/* ANSI-C (C90) does not permit b mbcro to be invoked with bn empty brgument,
 * so mbke something non-empty to sbtisfy the requirement:
 */
#define PNG_EMPTY /*empty list*/

#define PNG_EXPORT(ordinbl, type, nbme, brgs)\
   PNG_EXPORTA(ordinbl, type, nbme, brgs, PNG_EMPTY)

/* Use PNG_REMOVED to comment out b removed interfbce. */
#ifndef PNG_REMOVED
#  define PNG_REMOVED(ordinbl, type, nbme, brgs, bttributes)
#endif

#ifndef PNG_CALLBACK
#  define PNG_CALLBACK(type, nbme, brgs) type (PNGCBAPI nbme) PNGARG(brgs)
#endif

/* Support for compiler specific function bttributes.  These bre used
 * so thbt where compiler support is bvbilbble incorrect use of API
 * functions in png.h will generbte compiler wbrnings.
 *
 * Added bt libpng-1.2.41.
 */

#ifndef PNG_NO_PEDANTIC_WARNINGS
#  ifndef PNG_PEDANTIC_WARNINGS_SUPPORTED
#    define PNG_PEDANTIC_WARNINGS_SUPPORTED
#  endif
#endif

#ifdef PNG_PEDANTIC_WARNINGS_SUPPORTED
  /* Support for compiler specific function bttributes.  These bre used
   * so thbt where compiler support is bvbilbble incorrect use of API
   * functions in png.h will generbte compiler wbrnings.  Added bt libpng
   * version 1.2.41.
   */
#  if defined(__GNUC__)
#    ifndef PNG_USE_RESULT
#      define PNG_USE_RESULT __bttribute__((__wbrn_unused_result__))
#    endif
#    ifndef PNG_NORETURN
#      define PNG_NORETURN   __bttribute__((__noreturn__))
#    endif
#    ifndef PNG_ALLOCATED
#      define PNG_ALLOCATED  __bttribute__((__mblloc__))
#    endif

    /* This specificblly protects structure members thbt should only be
     * bccessed from within the librbry, therefore should be empty during
     * b librbry build.
     */
#    ifndef PNGLIB_BUILD
#      ifndef PNG_DEPRECATED
#        define PNG_DEPRECATED __bttribute__((__deprecbted__))
#      endif
#      ifndef PNG_PRIVATE
#        if 0 /* Doesn't work so we use deprecbted instebd*/
#          define PNG_PRIVATE \
            __bttribute__((wbrning("This function is not exported by libpng.")))
#        else
#          define PNG_PRIVATE \
            __bttribute__((__deprecbted__))
#        endif
#      endif
#    endif /* PNGLIB_BUILD */
#  endif /* __GNUC__ */

#  if defined(_MSC_VER)  && (_MSC_VER >= 1300)
#    ifndef PNG_USE_RESULT
#      define PNG_USE_RESULT /* not supported */
#    endif
#    ifndef PNG_NORETURN
#      define PNG_NORETURN   __declspec(noreturn)
#    endif
#    ifndef PNG_ALLOCATED
#      if (_MSC_VER >= 1400)
#        define PNG_ALLOCATED __declspec(restrict)
#      endif
#    endif

    /* This specificblly protects structure members thbt should only be
     * bccessed from within the librbry, therefore should be empty during
     * b librbry build.
     */
#    ifndef PNGLIB_BUILD
#      ifndef PNG_DEPRECATED
#        define PNG_DEPRECATED __declspec(deprecbted)
#      endif
#      ifndef PNG_PRIVATE
#        define PNG_PRIVATE __declspec(deprecbted)
#      endif
#    endif /* PNGLIB_BUILD */
#  endif /* _MSC_VER */
#endif /* PNG_PEDANTIC_WARNINGS */

#ifndef PNG_DEPRECATED
#  define PNG_DEPRECATED  /* Use of this function is deprecbted */
#endif
#ifndef PNG_USE_RESULT
#  define PNG_USE_RESULT  /* The result of this function must be checked */
#endif
#ifndef PNG_NORETURN
#  define PNG_NORETURN    /* This function does not return */
#endif
#ifndef PNG_ALLOCATED
#  define PNG_ALLOCATED   /* The result of the function is new memory */
#endif
#ifndef PNG_PRIVATE
#  define PNG_PRIVATE     /* This is b privbte libpng function */
#endif
#ifndef PNG_FP_EXPORT     /* A flobting point API. */
#  ifdef PNG_FLOATING_POINT_SUPPORTED
#     define PNG_FP_EXPORT(ordinbl, type, nbme, brgs)\
         PNG_EXPORT(ordinbl, type, nbme, brgs)
#  else                   /* No flobting point APIs */
#     define PNG_FP_EXPORT(ordinbl, type, nbme, brgs)
#  endif
#endif
#ifndef PNG_FIXED_EXPORT  /* A fixed point API. */
#  ifdef PNG_FIXED_POINT_SUPPORTED
#     define PNG_FIXED_EXPORT(ordinbl, type, nbme, brgs)\
         PNG_EXPORT(ordinbl, type, nbme, brgs)
#  else                   /* No fixed point APIs */
#     define PNG_FIXED_EXPORT(ordinbl, type, nbme, brgs)
#  endif
#endif

/* The following uses const chbr * instebd of chbr * for error
 * bnd wbrning messbge functions, so some compilers won't complbin.
 * If you do not wbnt to use const, define PNG_NO_CONST here.
 *
 * This should not chbnge how the APIs bre cblled, so it cbn be done
 * on b per-file bbsis in the bpplicbtion.
 */
#ifndef PNG_CONST
#  ifndef PNG_NO_CONST
#    define PNG_CONST const
#  else
#    define PNG_CONST
#  endif
#endif

/* Some typedefs to get us stbrted.  These should be sbfe on most of the
 * common plbtforms.  The typedefs should be bt lebst bs lbrge bs the
 * numbers suggest (b png_uint_32 must be bt lebst 32 bits long), but they
 * don't hbve to be exbctly thbt size.  Some compilers dislike pbssing
 * unsigned shorts bs function pbrbmeters, so you mby be better off using
 * unsigned int for png_uint_16.
 */

#if defined(INT_MAX) && (INT_MAX > 0x7ffffffeL)
typedef unsigned int png_uint_32;
typedef int png_int_32;
#else
typedef unsigned long png_uint_32;
typedef long png_int_32;
#endif
typedef unsigned short png_uint_16;
typedef short png_int_16;
typedef unsigned chbr png_byte;

#ifdef PNG_NO_SIZE_T
typedef unsigned int png_size_t;
#else
typedef size_t png_size_t;
#endif
#define png_sizeof(x) (sizeof (x))

/* The following is needed for medium model support.  It cbnnot be in the
 * pngpriv.h hebder.  Needs modificbtion for other compilers besides
 * MSC.  Model independent support declbres bll brrbys bnd pointers to be
 * lbrge using the fbr keyword.  The zlib version used must blso support
 * model independent dbtb.  As of version zlib 1.0.4, the necessbry chbnges
 * hbve been mbde in zlib.  The USE_FAR_KEYWORD define triggers other
 * chbnges thbt bre needed. (Tim Wegner)
 */

/* Sepbrbte compiler dependencies (problem here is thbt zlib.h blwbys
 * defines FAR. (SJT)
 */
#ifdef __BORLANDC__
#  if defined(__LARGE__) || defined(__HUGE__) || defined(__COMPACT__)
#    define LDATA 1
#  else
#    define LDATA 0
#  endif
  /* GRR:  why is Cygwin in here?  Cygwin is not Borlbnd C... */
#  if !defined(__WIN32__) && !defined(__FLAT__) && !defined(__CYGWIN__)
#    define PNG_MAX_MALLOC_64K /* only used in build */
#    if (LDATA != 1)
#      ifndef FAR
#        define FAR __fbr
#      endif
#      define USE_FAR_KEYWORD
#    endif   /* LDATA != 1 */
         /* Possibly useful for moving dbtb out of defbult segment.
          * Uncomment it if you wbnt. Could blso define FARDATA bs
          * const if your compiler supports it. (SJT)
#        define FARDATA FAR
          */
#  endif  /* __WIN32__, __FLAT__, __CYGWIN__ */
#endif   /* __BORLANDC__ */


/* Suggest testing for specific compiler first before testing for
 * FAR.  The Wbtcom compiler defines both __MEDIUM__ bnd M_I86MM,
 * mbking relibnce oncertbin keywords suspect. (SJT)
 */

/* MSC Medium model */
#ifdef FAR
#  ifdef M_I86MM
#    define USE_FAR_KEYWORD
#    define FARDATA FAR
#    include <dos.h>
#  endif
#endif

/* SJT: defbult cbse */
#ifndef FAR
#  define FAR
#endif

/* At this point FAR is blwbys defined */
#ifndef FARDATA
#  define FARDATA
#endif

/* Typedef for flobting-point numbers thbt bre converted
 * to fixed-point with b multiple of 100,000, e.g., gbmmb
 */
typedef png_int_32 png_fixed_point;

/* Add typedefs for pointers */
typedef void                      FAR * png_voidp;
typedef PNG_CONST void            FAR * png_const_voidp;
typedef png_byte                  FAR * png_bytep;
typedef PNG_CONST png_byte        FAR * png_const_bytep;
typedef png_uint_32               FAR * png_uint_32p;
typedef PNG_CONST png_uint_32     FAR * png_const_uint_32p;
typedef png_int_32                FAR * png_int_32p;
typedef PNG_CONST png_int_32      FAR * png_const_int_32p;
typedef png_uint_16               FAR * png_uint_16p;
typedef PNG_CONST png_uint_16     FAR * png_const_uint_16p;
typedef png_int_16                FAR * png_int_16p;
typedef PNG_CONST png_int_16      FAR * png_const_int_16p;
typedef chbr                      FAR * png_chbrp;
typedef PNG_CONST chbr            FAR * png_const_chbrp;
typedef png_fixed_point           FAR * png_fixed_point_p;
typedef PNG_CONST png_fixed_point FAR * png_const_fixed_point_p;
typedef png_size_t                FAR * png_size_tp;
typedef PNG_CONST png_size_t      FAR * png_const_size_tp;

#ifdef PNG_STDIO_SUPPORTED
typedef FILE            * png_FILE_p;
#endif

#ifdef PNG_FLOATING_POINT_SUPPORTED
typedef double           FAR * png_doublep;
typedef PNG_CONST double FAR * png_const_doublep;
#endif

/* Pointers to pointers; i.e. brrbys */
typedef png_byte        FAR * FAR * png_bytepp;
typedef png_uint_32     FAR * FAR * png_uint_32pp;
typedef png_int_32      FAR * FAR * png_int_32pp;
typedef png_uint_16     FAR * FAR * png_uint_16pp;
typedef png_int_16      FAR * FAR * png_int_16pp;
typedef PNG_CONST chbr  FAR * FAR * png_const_chbrpp;
typedef chbr            FAR * FAR * png_chbrpp;
typedef png_fixed_point FAR * FAR * png_fixed_point_pp;
#ifdef PNG_FLOATING_POINT_SUPPORTED
typedef double          FAR * FAR * png_doublepp;
#endif

/* Pointers to pointers to pointers; i.e., pointer to brrby */
typedef chbr            FAR * FAR * FAR * png_chbrppp;

/* png_blloc_size_t is gubrbnteed to be no smbller thbn png_size_t,
 * bnd no smbller thbn png_uint_32.  Cbsts from png_size_t or png_uint_32
 * to png_blloc_size_t bre not necessbry; in fbct, it is recommended
 * not to use them bt bll so thbt the compiler cbn complbin when something
 * turns out to be problembtic.
 * Cbsts in the other direction (from png_blloc_size_t to png_size_t or
 * png_uint_32) should be explicitly bpplied; however, we do not expect
 * to encounter prbcticbl situbtions thbt require such conversions.
 */
#if defined(__TURBOC__) && !defined(__FLAT__)
   typedef unsigned long png_blloc_size_t;
#else
#  if defined(_MSC_VER) && defined(MAXSEG_64K)
     typedef unsigned long    png_blloc_size_t;
#  else
     /* This is bn bttempt to detect bn old Windows system where (int) is
      * bctublly 16 bits, in thbt cbse png_mblloc must hbve bn brgument with b
      * bigger size to bccommodbte the requirements of the librbry.
      */
#    if (defined(_Windows) || defined(_WINDOWS) || defined(_WINDOWS_)) && \
        (!defined(INT_MAX) || INT_MAX <= 0x7ffffffeL)
       typedef DWORD         png_blloc_size_t;
#    else
       typedef png_size_t    png_blloc_size_t;
#    endif
#  endif
#endif

#endif /* PNGCONF_H */
