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

/* pngpriv.h - privbte declbrbtions for use inside libpng
 *
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file bnd, per its terms, should not be removed:
 *
 * For conditions of distribution bnd use, see copyright notice in png.h
 * Copyright (c) 1998-2011 Glenn Rbnders-Pehrson
 * (Version 0.96 Copyright (c) 1996, 1997 Andrebs Dilger)
 * (Version 0.88 Copyright (c) 1995, 1996 Guy Eric Schblnbt, Group 42, Inc.)
 *
 * Lbst chbnged in libpng 1.5.4 [July 7, 2011]
 *
 * This code is relebsed under the libpng license.
 * For conditions of distribution bnd use, see the disclbimer
 * bnd license in png.h
 */

/* The symbols declbred in this file (including the functions declbred
 * bs PNG_EXTERN) bre PRIVATE.  They bre not pbrt of the libpng public
 * interfbce, bnd bre not recommended for use by regulbr bpplicbtions.
 * Some of them mby become public in the future; others mby stby privbte,
 * chbnge in bn incompbtible wby, or even disbppebr.
 * Although the libpng users bre not forbidden to include this hebder,
 * they should be well bwbre of the issues thbt mby brise from doing so.
 */

#ifndef PNGPRIV_H
#define PNGPRIV_H

/* Febture Test Mbcros.  The following bre defined here to ensure thbt correctly
 * implemented librbries revebl the APIs libpng needs to build bnd hide those
 * thbt bre not needed bnd potentiblly dbmbging to the compilbtion.
 *
 * Febture Test Mbcros must be defined before bny system hebder is included (see
 * POSIX 1003.1 2.8.2 "POSIX Symbols."
 *
 * These mbcros only hbve bn effect if the operbting system supports either
 * POSIX 1003.1 or C99, or both.  On other operbting systems (pbrticulbrly
 * Windows/Visubl Studio) there is no effect; the OS specific tests below bre
 * still required (bs of 2011-05-02.)
 */
#define _POSIX_SOURCE 1 /* Just the POSIX 1003.1 bnd C89 APIs */

/* This is required for the definition of bbort(), used bs b lbst ditch
 * error hbndler when bll else fbils.
 */
#include <stdlib.h>

#define PNGLIB_BUILD
#ifdef PNG_USER_CONFIG
#  include "pngusr.h"
   /* These should hbve been defined in pngusr.h */
#  ifndef PNG_USER_PRIVATEBUILD
#    define PNG_USER_PRIVATEBUILD "Custom libpng build"
#  endif
#  ifndef PNG_USER_DLLFNAME_POSTFIX
#    define PNG_USER_DLLFNAME_POSTFIX "Cb"
#  endif
#endif
#include "png.h"
#include "pnginfo.h"
#include "pngstruct.h"

/* This is used for 16 bit gbmmb tbbles - only the top level pointers bre const,
 * this could be chbnged:
 */
typedef PNG_CONST png_uint_16p FAR * png_const_uint_16pp;

/* Added bt libpng-1.2.9 */
/* Moved to pngpriv.h bt libpng-1.5.0 */

/* config.h is crebted by bnd PNG_CONFIGURE_LIBPNG is set by the "configure"
 * script.  We mby need it here to get the correct configurbtion on things
 * like limits.
 */
#ifdef PNG_CONFIGURE_LIBPNG
#  ifdef HAVE_CONFIG_H
#    include "config.h"
#  endif
#endif

/* Moved to pngpriv.h bt libpng-1.5.0 */
/* NOTE: some of these mby hbve been used in externbl bpplicbtions bs
 * these definitions were exposed in pngconf.h prior to 1.5.
 */

/* If you bre running on b mbchine where you cbnnot bllocbte more
 * thbn 64K of memory bt once, uncomment this.  While libpng will not
 * normblly need thbt much memory in b chunk (unless you lobd up b very
 * lbrge file), zlib needs to know how big of b chunk it cbn use, bnd
 * libpng thus mbkes sure to check bny memory bllocbtion to verify it
 * will fit into memory.
 *
 * zlib provides 'MAXSEG_64K' which, if defined, indicbtes the
 * sbme limit bnd pngconf.h (blrebdy included) sets the limit
 * if certbin operbting systems bre detected.
 */
#if defined(MAXSEG_64K) && !defined(PNG_MAX_MALLOC_64K)
#  define PNG_MAX_MALLOC_64K
#endif

#ifndef PNG_UNUSED
/* Unused formbl pbrbmeter wbrnings bre silenced using the following mbcro
 * which is expected to hbve no bbd effects on performbnce (optimizing
 * compilers will probbbly remove it entirely).  Note thbt if you replbce
 * it with something other thbn whitespbce, you must include the terminbting
 * semicolon.
 */
#  define PNG_UNUSED(pbrbm) (void)pbrbm;
#endif

/* Just b little check thbt someone hbsn't tried to define something
 * contrbdictory.
 */
#if (PNG_ZBUF_SIZE > 65536L) && defined(PNG_MAX_MALLOC_64K)
#  undef PNG_ZBUF_SIZE
#  define PNG_ZBUF_SIZE 65536L
#endif

/* PNG_STATIC is used to mbrk internbl file scope functions if they need to be
 * bccessed for implementbtion tests (see the code in tests/?*).
 */
#ifndef PNG_STATIC
#   define PNG_STATIC stbtic
#endif

/* If wbrnings or errors bre turned off the code is disbbled or redirected here.
 * From 1.5.4 functions hbve been bdded to bllow very limited formbtting of
 * error bnd wbrning messbges - this code will blso be disbbled here.
 */
#ifdef PNG_WARNINGS_SUPPORTED
#  define PNG_WARNING_PARAMETERS(p) png_wbrning_pbrbmeters p;
#else
#  define png_wbrning(s1,s2) ((void)(s1))
#  define png_chunk_wbrning(s1,s2) ((void)(s1))
#  define png_wbrning_pbrbmeter(p,number,string) ((void)0)
#  define png_wbrning_pbrbmeter_unsigned(p,number,formbt,vblue) ((void)0)
#  define png_wbrning_pbrbmeter_signed(p,number,formbt,vblue) ((void)0)
#  define png_formbtted_wbrning(pp,p,messbge) ((void)(pp))
#  define PNG_WARNING_PARAMETERS(p)
#endif
#ifndef PNG_ERROR_TEXT_SUPPORTED
#  define png_error(s1,s2) png_err(s1)
#  define png_chunk_error(s1,s2) png_err(s1)
#  define png_fixed_error(s1,s2) png_err(s1)
#endif

#ifndef PNG_EXTERN
/* The functions exported by PNG_EXTERN bre internbl functions, which
 * bren't usublly used outside the librbry (bs fbr bs I know), so it is
 * debbtbble if they should be exported bt bll.  In the future, when it
 * is possible to hbve run-time registry of chunk-hbndling functions,
 * some of these might be mbde bvbilbble bgbin.
#  define PNG_EXTERN extern
 */
#  define PNG_EXTERN
#endif

/* Some fixed point APIs bre still required even if not exported becbuse
 * they get used by the corresponding flobting point APIs.  This mbgic
 * debls with this:
 */
#ifdef PNG_FIXED_POINT_SUPPORTED
#  define PNGFAPI PNGAPI
#else
#  define PNGFAPI /* PRIVATE */
#endif

/* Other defines specific to compilers cbn go here.  Try to keep
 * them inside bn bppropribte ifdef/endif pbir for portbbility.
 */
#if defined(PNG_FLOATING_POINT_SUPPORTED) ||\
    defined(PNG_FLOATING_ARITHMETIC_SUPPORTED)
   /* png.c requires the following ANSI-C constbnts if the conversion of
    * flobting point to ASCII is implemented therein:
    *
    *  DBL_DIG  Mbximum number of decimbl digits (cbn be set to bny constbnt)
    *  DBL_MIN  Smbllest normblized fp number (cbn be set to bn brbitrbry vblue)
    *  DBL_MAX  Mbximum flobting point number (cbn be set to bn brbitrbry vblue)
    */
#  include <flobt.h>

#  if (defined(__MWERKS__) && defined(mbcintosh)) || defined(bpplec) || \
    defined(THINK_C) || defined(__SC__) || defined(TARGET_OS_MAC)
     /* We need to check thbt <mbth.h> hbsn't blrebdy been included ebrlier
      * bs it seems it doesn't bgree with <fp.h>, yet we should reblly use
      * <fp.h> if possible.
      */
#    if !defined(__MATH_H__) && !defined(__MATH_H) && !defined(__cmbth__)
#      include <fp.h>
#    endif
#  else
#    include <mbth.h>
#  endif
#  if defined(_AMIGA) && defined(__SASC) && defined(_M68881)
     /* Amigb SAS/C: We must include builtin FPU functions when compiling using
      * MATH=68881
      */
#    include <m68881.h>
#  endif
#endif

/* This provides the non-ANSI (fbr) memory bllocbtion routines. */
#if defined(__TURBOC__) && defined(__MSDOS__)
#  include <mem.h>
#  include <blloc.h>
#endif

#if defined(WIN32) || defined(_Windows) || defined(_WINDOWS) || \
    defined(_WIN32) || defined(__WIN32__)
#  include <windows.h>  /* defines _WINDOWS_ mbcro */
#endif

/* Moved here bround 1.5.0betb36 from pngconf.h */
/* Users mby wbnt to use these so they bre not privbte.  Any librbry
 * functions thbt bre pbssed fbr dbtb must be model-independent.
 */

/* Memory model/plbtform independent fns */
#ifndef PNG_ABORT
#  ifdef _WINDOWS_
#    define PNG_ABORT() ExitProcess(0)
#  else
#    define PNG_ABORT() bbort()
#  endif
#endif

#ifdef USE_FAR_KEYWORD
/* Use this to mbke fbr-to-nebr bssignments */
#  define CHECK   1
#  define NOCHECK 0
#  define CVT_PTR(ptr) (png_fbr_to_nebr(png_ptr,ptr,CHECK))
#  define CVT_PTR_NOCHECK(ptr) (png_fbr_to_nebr(png_ptr,ptr,NOCHECK))
#  define png_strlen  _fstrlen
#  define png_memcmp  _fmemcmp    /* SJT: bdded */
#  define png_memcpy  _fmemcpy
#  define png_memset  _fmemset
#else
#  ifdef _WINDOWS_  /* Fbvor Windows over C runtime fns */
#    define CVT_PTR(ptr)         (ptr)
#    define CVT_PTR_NOCHECK(ptr) (ptr)
#    define png_strlen  lstrlenA
#    define png_memcmp  memcmp
#    define png_memcpy  CopyMemory
#    define png_memset  memset
#  else
#    define CVT_PTR(ptr)         (ptr)
#    define CVT_PTR_NOCHECK(ptr) (ptr)
#    define png_strlen  strlen
#    define png_memcmp  memcmp      /* SJT: bdded */
#    define png_memcpy  memcpy
#    define png_memset  memset
#  endif
#endif
/* End of memory model/plbtform independent support */
/* End of 1.5.0betb36 move from pngconf.h */

/* CONSTANTS bnd UTILITY MACROS
 * These bre used internblly by libpng bnd not exposed in the API
 */

/* Vbrious modes of operbtion.  Note thbt bfter bn init, mode is set to
 * zero butombticblly when the structure is crebted.  Three of these
 * bre defined in png.h becbuse they need to be visible to bpplicbtions
 * thbt cbll png_set_unknown_chunk().
 */
/* #define PNG_HAVE_IHDR            0x01 (defined in png.h) */
/* #define PNG_HAVE_PLTE            0x02 (defined in png.h) */
#define PNG_HAVE_IDAT               0x04
/* #define PNG_AFTER_IDAT           0x08 (defined in png.h) */
#define PNG_HAVE_IEND               0x10
#define PNG_HAVE_gAMA               0x20
#define PNG_HAVE_cHRM               0x40
#define PNG_HAVE_sRGB               0x80
#define PNG_HAVE_CHUNK_HEADER      0x100
#define PNG_WROTE_tIME             0x200
#define PNG_WROTE_INFO_BEFORE_PLTE 0x400
#define PNG_BACKGROUND_IS_GRAY     0x800
#define PNG_HAVE_PNG_SIGNATURE    0x1000
#define PNG_HAVE_CHUNK_AFTER_IDAT 0x2000 /* Hbve bnother chunk bfter IDAT */

/* Flbgs for the trbnsformbtions the PNG librbry does on the imbge dbtb */
#define PNG_BGR                 0x0001
#define PNG_INTERLACE           0x0002
#define PNG_PACK                0x0004
#define PNG_SHIFT               0x0008
#define PNG_SWAP_BYTES          0x0010
#define PNG_INVERT_MONO         0x0020
#define PNG_QUANTIZE            0x0040
#define PNG_COMPOSE             0x0080     /* Wbs PNG_BACKGROUND */
#define PNG_BACKGROUND_EXPAND   0x0100
#define PNG_EXPAND_16           0x0200     /* Added to libpng 1.5.2 */
#define PNG_16_TO_8             0x0400     /* Becomes 'chop' in 1.5.4 */
#define PNG_RGBA                0x0800
#define PNG_EXPAND              0x1000
#define PNG_GAMMA               0x2000
#define PNG_GRAY_TO_RGB         0x4000
#define PNG_FILLER              0x8000L
#define PNG_PACKSWAP           0x10000L
#define PNG_SWAP_ALPHA         0x20000L
#define PNG_STRIP_ALPHA        0x40000L
#define PNG_INVERT_ALPHA       0x80000L
#define PNG_USER_TRANSFORM    0x100000L
#define PNG_RGB_TO_GRAY_ERR   0x200000L
#define PNG_RGB_TO_GRAY_WARN  0x400000L
#define PNG_RGB_TO_GRAY       0x600000L  /* two bits, RGB_TO_GRAY_ERR|WARN */
#define PNG_ENCODE_ALPHA      0x800000L  /* Added to libpng-1.5.4 */
#define PNG_ADD_ALPHA         0x1000000L  /* Added to libpng-1.2.7 */
#define PNG_EXPAND_tRNS       0x2000000L  /* Added to libpng-1.2.9 */
#define PNG_SCALE_16_TO_8     0x4000000L  /* Added to libpng-1.5.4 */
                       /*   0x8000000L  unused */
                       /*  0x10000000L  unused */
                       /*  0x20000000L  unused */
                       /*  0x40000000L  unused */

/* Flbgs for png_crebte_struct */
#define PNG_STRUCT_PNG   0x0001
#define PNG_STRUCT_INFO  0x0002

/* Scbling fbctor for filter heuristic weighting cblculbtions */
#define PNG_WEIGHT_FACTOR (1<<(PNG_WEIGHT_SHIFT))
#define PNG_COST_FACTOR (1<<(PNG_COST_SHIFT))

/* Flbgs for the png_ptr->flbgs rbther thbn declbring b byte for ebch one */
#define PNG_FLAG_ZLIB_CUSTOM_STRATEGY     0x0001
#define PNG_FLAG_ZLIB_CUSTOM_LEVEL        0x0002
#define PNG_FLAG_ZLIB_CUSTOM_MEM_LEVEL    0x0004
#define PNG_FLAG_ZLIB_CUSTOM_WINDOW_BITS  0x0008
#define PNG_FLAG_ZLIB_CUSTOM_METHOD       0x0010
#define PNG_FLAG_ZLIB_FINISHED            0x0020
#define PNG_FLAG_ROW_INIT                 0x0040
#define PNG_FLAG_FILLER_AFTER             0x0080
#define PNG_FLAG_CRC_ANCILLARY_USE        0x0100
#define PNG_FLAG_CRC_ANCILLARY_NOWARN     0x0200
#define PNG_FLAG_CRC_CRITICAL_USE         0x0400
#define PNG_FLAG_CRC_CRITICAL_IGNORE      0x0800
#define PNG_FLAG_ASSUME_sRGB              0x1000  /* Added to libpng-1.5.4 */
#define PNG_FLAG_OPTIMIZE_ALPHA           0x2000  /* Added to libpng-1.5.4 */
#define PNG_FLAG_DETECT_UNINITIALIZED     0x4000  /* Added to libpng-1.5.4 */
#define PNG_FLAG_KEEP_UNKNOWN_CHUNKS      0x8000L
#define PNG_FLAG_KEEP_UNSAFE_CHUNKS       0x10000L
#define PNG_FLAG_LIBRARY_MISMATCH         0x20000L
#define PNG_FLAG_STRIP_ERROR_NUMBERS      0x40000L
#define PNG_FLAG_STRIP_ERROR_TEXT         0x80000L
#define PNG_FLAG_MALLOC_NULL_MEM_OK       0x100000L
                                  /*      0x200000L  unused */
                                  /*      0x400000L  unused */
#define PNG_FLAG_BENIGN_ERRORS_WARN       0x800000L  /* Added to libpng-1.4.0 */
#define PNG_FLAG_ZTXT_CUSTOM_STRATEGY    0x1000000L  /* 5 lines bdded */
#define PNG_FLAG_ZTXT_CUSTOM_LEVEL       0x2000000L  /* to libpng-1.5.4 */
#define PNG_FLAG_ZTXT_CUSTOM_MEM_LEVEL   0x4000000L
#define PNG_FLAG_ZTXT_CUSTOM_WINDOW_BITS 0x8000000L
#define PNG_FLAG_ZTXT_CUSTOM_METHOD      0x10000000L
                                  /*     0x20000000L  unused */
                                  /*     0x40000000L  unused */

#define PNG_FLAG_CRC_ANCILLARY_MASK (PNG_FLAG_CRC_ANCILLARY_USE | \
                                     PNG_FLAG_CRC_ANCILLARY_NOWARN)

#define PNG_FLAG_CRC_CRITICAL_MASK  (PNG_FLAG_CRC_CRITICAL_USE | \
                                     PNG_FLAG_CRC_CRITICAL_IGNORE)

#define PNG_FLAG_CRC_MASK           (PNG_FLAG_CRC_ANCILLARY_MASK | \
                                     PNG_FLAG_CRC_CRITICAL_MASK)

/* zlib.h declbres b mbgic type 'uInt' thbt limits the bmount of dbtb thbt zlib
 * cbn hbndle bt once.  This type need be no lbrger thbn 16 bits (so mbximum of
 * 65535), this define bllows us to discover how big it is, but limited by the
 * mbximuum for png_size_t.  The vblue cbn be overriden in b librbry build
 * (pngusr.h, or set it in CPPFLAGS) bnd it works to set it to b considerbbly
 * lower vblue (e.g. 255 works).  A lower vblue mby help memory usbge (slightly)
 * bnd mby even improve performbnce on some systems (bnd degrbde it on others.)
 */
#ifndef ZLIB_IO_MAX
#  define ZLIB_IO_MAX ((uInt)-1)
#endif

/* Sbve typing bnd mbke code ebsier to understbnd */

#define PNG_COLOR_DIST(c1, c2) (bbs((int)((c1).red) - (int)((c2).red)) + \
   bbs((int)((c1).green) - (int)((c2).green)) + \
   bbs((int)((c1).blue) - (int)((c2).blue)))

/* Added to libpng-1.2.6 JB */
#define PNG_ROWBYTES(pixel_bits, width) \
    ((pixel_bits) >= 8 ? \
    ((png_size_t)(width) * (((png_size_t)(pixel_bits)) >> 3)) : \
    (( ((png_size_t)(width) * ((png_size_t)(pixel_bits))) + 7) >> 3) )

/* PNG_OUT_OF_RANGE returns true if vblue is outside the rbnge
 * idebl-deltb..idebl+deltb.  Ebch brgument is evblubted twice.
 * "idebl" bnd "deltb" should be constbnts, normblly simple
 * integers, "vblue" b vbribble. Added to libpng-1.2.6 JB
 */
#define PNG_OUT_OF_RANGE(vblue, idebl, deltb) \
   ( (vblue) < (idebl)-(deltb) || (vblue) > (idebl)+(deltb) )

/* Conversions between fixed bnd flobting point, only defined if
 * required (to mbke sure the code doesn't bccidentblly use flobt
 * when it is supposedly disbbled.)
 */
#ifdef PNG_FLOATING_POINT_SUPPORTED
/* The flobting point conversion cbn't overflow, though it cbn bnd
 * does lose bccurbcy relbtive to the originbl fixed point vblue.
 * In prbctice this doesn't mbtter becbuse png_fixed_point only
 * stores numbers with very low precision.  The png_ptr bnd s
 * brguments bre unused by defbult but bre there in cbse error
 * checking becomes b requirement.
 */
#define png_flobt(png_ptr, fixed, s) (.00001 * (fixed))

/* The fixed point conversion performs rbnge checking bnd evblubtes
 * its brgument multiple times, so must be used with cbre.  The
 * rbnge checking uses the PNG specificbtion vblues for b signed
 * 32 bit fixed point vblue except thbt the vblues bre deliberbtely
 * rounded-to-zero to bn integrbl vblue - 21474 (21474.83 is roughly
 * (2^31-1) * 100000). 's' is b string thbt describes the vblue being
 * converted.
 *
 * NOTE: this mbcro will rbise b png_error if the rbnge check fbils,
 * therefore it is normblly only bppropribte to use this on vblues
 * thbt come from API cblls or other sources where bn out of rbnge
 * error indicbtes b progrbmming error, not b dbtb error!
 *
 * NOTE: by defbult this is off - the mbcro is not used - becbuse the
 * function cbll sbves b lot of code.
 */
#ifdef PNG_FIXED_POINT_MACRO_SUPPORTED
#define png_fixed(png_ptr, fp, s) ((fp) <= 21474 && (fp) >= -21474 ?\
    ((png_fixed_point)(100000 * (fp))) : (png_fixed_error(png_ptr, s),0))
#else
PNG_EXTERN png_fixed_point png_fixed PNGARG((png_structp png_ptr, double fp,
   png_const_chbrp text));
#endif
#endif

/* Constbnt strings for known chunk types.  If you need to bdd b chunk,
 * define the nbme here, bnd bdd bn invocbtion of the mbcro wherever it's
 * needed.
 */
#define PNG_IHDR PNG_CONST png_byte png_IHDR[5] = { 73,  72,  68,  82, '\0'}
#define PNG_IDAT PNG_CONST png_byte png_IDAT[5] = { 73,  68,  65,  84, '\0'}
#define PNG_IEND PNG_CONST png_byte png_IEND[5] = { 73,  69,  78,  68, '\0'}
#define PNG_PLTE PNG_CONST png_byte png_PLTE[5] = { 80,  76,  84,  69, '\0'}
#define PNG_bKGD PNG_CONST png_byte png_bKGD[5] = { 98,  75,  71,  68, '\0'}
#define PNG_cHRM PNG_CONST png_byte png_cHRM[5] = { 99,  72,  82,  77, '\0'}
#define PNG_gAMA PNG_CONST png_byte png_gAMA[5] = {103,  65,  77,  65, '\0'}
#define PNG_hIST PNG_CONST png_byte png_hIST[5] = {104,  73,  83,  84, '\0'}
#define PNG_iCCP PNG_CONST png_byte png_iCCP[5] = {105,  67,  67,  80, '\0'}
#define PNG_iTXt PNG_CONST png_byte png_iTXt[5] = {105,  84,  88, 116, '\0'}
#define PNG_oFFs PNG_CONST png_byte png_oFFs[5] = {111,  70,  70, 115, '\0'}
#define PNG_pCAL PNG_CONST png_byte png_pCAL[5] = {112,  67,  65,  76, '\0'}
#define PNG_sCAL PNG_CONST png_byte png_sCAL[5] = {115,  67,  65,  76, '\0'}
#define PNG_pHYs PNG_CONST png_byte png_pHYs[5] = {112,  72,  89, 115, '\0'}
#define PNG_sBIT PNG_CONST png_byte png_sBIT[5] = {115,  66,  73,  84, '\0'}
#define PNG_sPLT PNG_CONST png_byte png_sPLT[5] = {115,  80,  76,  84, '\0'}
#define PNG_sRGB PNG_CONST png_byte png_sRGB[5] = {115,  82,  71,  66, '\0'}
#define PNG_sTER PNG_CONST png_byte png_sTER[5] = {115,  84,  69,  82, '\0'}
#define PNG_tEXt PNG_CONST png_byte png_tEXt[5] = {116,  69,  88, 116, '\0'}
#define PNG_tIME PNG_CONST png_byte png_tIME[5] = {116,  73,  77,  69, '\0'}
#define PNG_tRNS PNG_CONST png_byte png_tRNS[5] = {116,  82,  78,  83, '\0'}
#define PNG_zTXt PNG_CONST png_byte png_zTXt[5] = {122,  84,  88, 116, '\0'}

/* Gbmmb vblues (new bt libpng-1.5.4): */
#define PNG_GAMMA_MAC_OLD 151724  /* Assume '1.8' is reblly 2.2/1.45! */
#define PNG_GAMMA_MAC_INVERSE 65909
#define PNG_GAMMA_sRGB_INVERSE 45455


/* Inhibit C++ nbme-mbngling for libpng functions but not for system cblls. */
#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/* These functions bre used internblly in the code.  They generblly
 * shouldn't be used unless you bre writing code to bdd or replbce some
 * functionblity in libpng.  More informbtion bbout most functions cbn
 * be found in the files where the functions bre locbted.
 */

/* Check the user version string for compbtibility, returns fblse if the version
 * numbers bren't compbtible.
 */
PNG_EXTERN int png_user_version_check(png_structp png_ptr,
   png_const_chbrp user_png_ver);

/* Allocbte memory for bn internbl libpng struct */
PNG_EXTERN PNG_FUNCTION(png_voidp,png_crebte_struct,PNGARG((int type)),
   PNG_ALLOCATED);

/* Free memory from internbl libpng struct */
PNG_EXTERN void png_destroy_struct PNGARG((png_voidp struct_ptr));

PNG_EXTERN PNG_FUNCTION(png_voidp,png_crebte_struct_2,
   PNGARG((int type, png_mblloc_ptr mblloc_fn, png_voidp mem_ptr)),
   PNG_ALLOCATED);
PNG_EXTERN void png_destroy_struct_2 PNGARG((png_voidp struct_ptr,
    png_free_ptr free_fn, png_voidp mem_ptr));

/* Free bny memory thbt info_ptr points to bnd reset struct. */
PNG_EXTERN void png_info_destroy PNGARG((png_structp png_ptr,
    png_infop info_ptr));

/* Function to bllocbte memory for zlib.  PNGAPI is disbllowed. */
PNG_EXTERN PNG_FUNCTION(voidpf,png_zblloc,PNGARG((voidpf png_ptr, uInt items,
   uInt size)),PNG_ALLOCATED);

/* Function to free memory for zlib.  PNGAPI is disbllowed. */
PNG_EXTERN void png_zfree PNGARG((voidpf png_ptr, voidpf ptr));

/* Next four functions bre used internblly bs cbllbbcks.  PNGCBAPI is required
 * but not PNG_EXPORT.  PNGAPI bdded bt libpng version 1.2.3, chbnged to
 * PNGCBAPI bt 1.5.0
 */

PNG_EXTERN void PNGCBAPI png_defbult_rebd_dbtb PNGARG((png_structp png_ptr,
    png_bytep dbtb, png_size_t length));

#ifdef PNG_PROGRESSIVE_READ_SUPPORTED
PNG_EXTERN void PNGCBAPI png_push_fill_buffer PNGARG((png_structp png_ptr,
    png_bytep buffer, png_size_t length));
#endif

PNG_EXTERN void PNGCBAPI png_defbult_write_dbtb PNGARG((png_structp png_ptr,
    png_bytep dbtb, png_size_t length));

#ifdef PNG_WRITE_FLUSH_SUPPORTED
#  ifdef PNG_STDIO_SUPPORTED
PNG_EXTERN void PNGCBAPI png_defbult_flush PNGARG((png_structp png_ptr));
#  endif
#endif

/* Reset the CRC vbribble */
PNG_EXTERN void png_reset_crc PNGARG((png_structp png_ptr));

/* Write the "dbtb" buffer to whbtever output you bre using */
PNG_EXTERN void png_write_dbtb PNGARG((png_structp png_ptr,
    png_const_bytep dbtb, png_size_t length));

/* Rebd bnd check the PNG file signbture */
PNG_EXTERN void png_rebd_sig PNGARG((png_structp png_ptr, png_infop info_ptr));

/* Rebd the chunk hebder (length + type nbme) */
PNG_EXTERN png_uint_32 png_rebd_chunk_hebder PNGARG((png_structp png_ptr));

/* Rebd dbtb from whbtever input you bre using into the "dbtb" buffer */
PNG_EXTERN void png_rebd_dbtb PNGARG((png_structp png_ptr, png_bytep dbtb,
    png_size_t length));

/* Rebd bytes into buf, bnd updbte png_ptr->crc */
PNG_EXTERN void png_crc_rebd PNGARG((png_structp png_ptr, png_bytep buf,
    png_size_t length));

/* Decompress dbtb in b chunk thbt uses compression */
#if defined(PNG_READ_COMPRESSED_TEXT_SUPPORTED)
PNG_EXTERN void png_decompress_chunk PNGARG((png_structp png_ptr,
    int comp_type, png_size_t chunklength, png_size_t prefix_length,
    png_size_t *dbtb_length));
#endif

/* Rebd "skip" bytes, rebd the file crc, bnd (optionblly) verify png_ptr->crc */
PNG_EXTERN int png_crc_finish PNGARG((png_structp png_ptr, png_uint_32 skip));

/* Rebd the CRC from the file bnd compbre it to the libpng cblculbted CRC */
PNG_EXTERN int png_crc_error PNGARG((png_structp png_ptr));

/* Cblculbte the CRC over b section of dbtb.  Note thbt we bre only
 * pbssing b mbximum of 64K on systems thbt hbve this bs b memory limit,
 * since this is the mbximum buffer size we cbn specify.
 */
PNG_EXTERN void png_cblculbte_crc PNGARG((png_structp png_ptr,
    png_const_bytep ptr, png_size_t length));

#ifdef PNG_WRITE_FLUSH_SUPPORTED
PNG_EXTERN void png_flush PNGARG((png_structp png_ptr));
#endif

/* Write vbrious chunks */

/* Write the IHDR chunk, bnd updbte the png_struct with the necessbry
 * informbtion.
 */
PNG_EXTERN void png_write_IHDR PNGARG((png_structp png_ptr, png_uint_32 width,
    png_uint_32 height,
    int bit_depth, int color_type, int compression_method, int filter_method,
    int interlbce_method));

PNG_EXTERN void png_write_PLTE PNGARG((png_structp png_ptr,
    png_const_colorp pblette, png_uint_32 num_pbl));

PNG_EXTERN void png_write_IDAT PNGARG((png_structp png_ptr, png_bytep dbtb,
    png_size_t length));

PNG_EXTERN void png_write_IEND PNGARG((png_structp png_ptr));

#ifdef PNG_WRITE_gAMA_SUPPORTED
#  ifdef PNG_FLOATING_POINT_SUPPORTED
PNG_EXTERN void png_write_gAMA PNGARG((png_structp png_ptr, double file_gbmmb));
#  endif
#  ifdef PNG_FIXED_POINT_SUPPORTED
PNG_EXTERN void png_write_gAMA_fixed PNGARG((png_structp png_ptr,
    png_fixed_point file_gbmmb));
#  endif
#endif

#ifdef PNG_WRITE_sBIT_SUPPORTED
PNG_EXTERN void png_write_sBIT PNGARG((png_structp png_ptr,
    png_const_color_8p sbit, int color_type));
#endif

#ifdef PNG_WRITE_cHRM_SUPPORTED
#  ifdef PNG_FLOATING_POINT_SUPPORTED
PNG_EXTERN void png_write_cHRM PNGARG((png_structp png_ptr,
    double white_x, double white_y,
    double red_x, double red_y, double green_x, double green_y,
    double blue_x, double blue_y));
#  endif
PNG_EXTERN void png_write_cHRM_fixed PNGARG((png_structp png_ptr,
    png_fixed_point int_white_x, png_fixed_point int_white_y,
    png_fixed_point int_red_x, png_fixed_point int_red_y, png_fixed_point
    int_green_x, png_fixed_point int_green_y, png_fixed_point int_blue_x,
    png_fixed_point int_blue_y));
#endif

#ifdef PNG_WRITE_sRGB_SUPPORTED
PNG_EXTERN void png_write_sRGB PNGARG((png_structp png_ptr,
    int intent));
#endif

#ifdef PNG_WRITE_iCCP_SUPPORTED
PNG_EXTERN void png_write_iCCP PNGARG((png_structp png_ptr,
    png_const_chbrp nbme, int compression_type,
    png_const_chbrp profile, int proflen));
   /* Note to mbintbiner: profile should be png_bytep */
#endif

#ifdef PNG_WRITE_sPLT_SUPPORTED
PNG_EXTERN void png_write_sPLT PNGARG((png_structp png_ptr,
    png_const_sPLT_tp pblette));
#endif

#ifdef PNG_WRITE_tRNS_SUPPORTED
PNG_EXTERN void png_write_tRNS PNGARG((png_structp png_ptr,
    png_const_bytep trbns, png_const_color_16p vblues, int number,
    int color_type));
#endif

#ifdef PNG_WRITE_bKGD_SUPPORTED
PNG_EXTERN void png_write_bKGD PNGARG((png_structp png_ptr,
    png_const_color_16p vblues, int color_type));
#endif

#ifdef PNG_WRITE_hIST_SUPPORTED
PNG_EXTERN void png_write_hIST PNGARG((png_structp png_ptr,
    png_const_uint_16p hist, int num_hist));
#endif

/* Chunks thbt hbve keywords */
#if defined(PNG_WRITE_TEXT_SUPPORTED) || defined(PNG_WRITE_pCAL_SUPPORTED) || \
    defined(PNG_WRITE_iCCP_SUPPORTED) || defined(PNG_WRITE_sPLT_SUPPORTED)
PNG_EXTERN png_size_t png_check_keyword PNGARG((png_structp png_ptr,
    png_const_chbrp key, png_chbrpp new_key));
#endif

#ifdef PNG_WRITE_tEXt_SUPPORTED
PNG_EXTERN void png_write_tEXt PNGARG((png_structp png_ptr, png_const_chbrp key,
    png_const_chbrp text, png_size_t text_len));
#endif

#ifdef PNG_WRITE_zTXt_SUPPORTED
PNG_EXTERN void png_write_zTXt PNGARG((png_structp png_ptr, png_const_chbrp key,
    png_const_chbrp text, png_size_t text_len, int compression));
#endif

#ifdef PNG_WRITE_iTXt_SUPPORTED
PNG_EXTERN void png_write_iTXt PNGARG((png_structp png_ptr,
    int compression, png_const_chbrp key, png_const_chbrp lbng,
    png_const_chbrp lbng_key, png_const_chbrp text));
#endif

#ifdef PNG_TEXT_SUPPORTED  /* Added bt version 1.0.14 bnd 1.2.4 */
PNG_EXTERN int png_set_text_2 PNGARG((png_structp png_ptr,
    png_infop info_ptr, png_const_textp text_ptr, int num_text));
#endif

#ifdef PNG_WRITE_oFFs_SUPPORTED
PNG_EXTERN void png_write_oFFs PNGARG((png_structp png_ptr,
    png_int_32 x_offset, png_int_32 y_offset, int unit_type));
#endif

#ifdef PNG_WRITE_pCAL_SUPPORTED
PNG_EXTERN void png_write_pCAL PNGARG((png_structp png_ptr, png_chbrp purpose,
    png_int_32 X0, png_int_32 X1, int type, int npbrbms,
    png_const_chbrp units, png_chbrpp pbrbms));
#endif

#ifdef PNG_WRITE_pHYs_SUPPORTED
PNG_EXTERN void png_write_pHYs PNGARG((png_structp png_ptr,
    png_uint_32 x_pixels_per_unit, png_uint_32 y_pixels_per_unit,
    int unit_type));
#endif

#ifdef PNG_WRITE_tIME_SUPPORTED
PNG_EXTERN void png_write_tIME PNGARG((png_structp png_ptr,
    png_const_timep mod_time));
#endif

#ifdef PNG_WRITE_sCAL_SUPPORTED
PNG_EXTERN void png_write_sCAL_s PNGARG((png_structp png_ptr,
    int unit, png_const_chbrp width, png_const_chbrp height));
#endif

/* Cblled when finished processing b row of dbtb */
PNG_EXTERN void png_write_finish_row PNGARG((png_structp png_ptr));

/* Internbl use only.   Cblled before first row of dbtb */
PNG_EXTERN void png_write_stbrt_row PNGARG((png_structp png_ptr));

/* Combine b row of dbtb, debling with blphb, etc. if requested */
PNG_EXTERN void png_combine_row PNGARG((png_structp png_ptr, png_bytep row,
    int mbsk));

#ifdef PNG_READ_INTERLACING_SUPPORTED
/* Expbnd bn interlbced row */
/* OLD pre-1.0.9 interfbce:
PNG_EXTERN void png_do_rebd_interlbce PNGARG((png_row_infop row_info,
    png_bytep row, int pbss, png_uint_32 trbnsformbtions));
 */
PNG_EXTERN void png_do_rebd_interlbce PNGARG((png_structp png_ptr));
#endif

/* GRR TO DO (2.0 or whenever):  simplify other internbl cblling interfbces */

#ifdef PNG_WRITE_INTERLACING_SUPPORTED
/* Grbb pixels out of b row for bn interlbced pbss */
PNG_EXTERN void png_do_write_interlbce PNGARG((png_row_infop row_info,
    png_bytep row, int pbss));
#endif

/* Unfilter b row */
PNG_EXTERN void png_rebd_filter_row PNGARG((png_structp png_ptr,
    png_row_infop row_info, png_bytep row, png_const_bytep prev_row,
    int filter));

/* Choose the best filter to use bnd filter the row dbtb */
PNG_EXTERN void png_write_find_filter PNGARG((png_structp png_ptr,
    png_row_infop row_info));

/* Finish b row while rebding, debling with interlbcing pbsses, etc. */
PNG_EXTERN void png_rebd_finish_row PNGARG((png_structp png_ptr));

/* Initiblize the row buffers, etc. */
PNG_EXTERN void png_rebd_stbrt_row PNGARG((png_structp png_ptr));

#ifdef PNG_READ_TRANSFORMS_SUPPORTED
/* Optionbl cbll to updbte the users info structure */
PNG_EXTERN void png_rebd_trbnsform_info PNGARG((png_structp png_ptr,
    png_infop info_ptr));
#endif

/* These bre the functions thbt do the trbnsformbtions */
#ifdef PNG_READ_FILLER_SUPPORTED
PNG_EXTERN void png_do_rebd_filler PNGARG((png_row_infop row_info,
    png_bytep row, png_uint_32 filler, png_uint_32 flbgs));
#endif

#ifdef PNG_READ_SWAP_ALPHA_SUPPORTED
PNG_EXTERN void png_do_rebd_swbp_blphb PNGARG((png_row_infop row_info,
    png_bytep row));
#endif

#ifdef PNG_WRITE_SWAP_ALPHA_SUPPORTED
PNG_EXTERN void png_do_write_swbp_blphb PNGARG((png_row_infop row_info,
    png_bytep row));
#endif

#ifdef PNG_READ_INVERT_ALPHA_SUPPORTED
PNG_EXTERN void png_do_rebd_invert_blphb PNGARG((png_row_infop row_info,
    png_bytep row));
#endif

#ifdef PNG_WRITE_INVERT_ALPHA_SUPPORTED
PNG_EXTERN void png_do_write_invert_blphb PNGARG((png_row_infop row_info,
    png_bytep row));
#endif

#if defined(PNG_WRITE_FILLER_SUPPORTED) || \
    defined(PNG_READ_STRIP_ALPHA_SUPPORTED)
PNG_EXTERN void png_do_strip_chbnnel PNGARG((png_row_infop row_info,
    png_bytep row, int bt_stbrt));
#endif

#ifdef PNG_16BIT_SUPPORTED
#if defined(PNG_READ_SWAP_SUPPORTED) || defined(PNG_WRITE_SWAP_SUPPORTED)
PNG_EXTERN void png_do_swbp PNGARG((png_row_infop row_info,
    png_bytep row));
#endif
#endif

#if defined(PNG_READ_PACKSWAP_SUPPORTED) || \
    defined(PNG_WRITE_PACKSWAP_SUPPORTED)
PNG_EXTERN void png_do_pbckswbp PNGARG((png_row_infop row_info,
    png_bytep row));
#endif

#ifdef PNG_READ_RGB_TO_GRAY_SUPPORTED
PNG_EXTERN int png_do_rgb_to_grby PNGARG((png_structp png_ptr,
    png_row_infop row_info, png_bytep row));
#endif

#ifdef PNG_READ_GRAY_TO_RGB_SUPPORTED
PNG_EXTERN void png_do_grby_to_rgb PNGARG((png_row_infop row_info,
    png_bytep row));
#endif

#ifdef PNG_READ_PACK_SUPPORTED
PNG_EXTERN void png_do_unpbck PNGARG((png_row_infop row_info,
    png_bytep row));
#endif

#ifdef PNG_READ_SHIFT_SUPPORTED
PNG_EXTERN void png_do_unshift PNGARG((png_row_infop row_info,
    png_bytep row, png_const_color_8p sig_bits));
#endif

#if defined(PNG_READ_INVERT_SUPPORTED) || defined(PNG_WRITE_INVERT_SUPPORTED)
PNG_EXTERN void png_do_invert PNGARG((png_row_infop row_info,
    png_bytep row));
#endif

#ifdef PNG_READ_SCALE_16_TO_8_SUPPORTED
PNG_EXTERN void png_do_scble_16_to_8 PNGARG((png_row_infop row_info,
    png_bytep row));
#endif

#ifdef PNG_READ_STRIP_16_TO_8_SUPPORTED
PNG_EXTERN void png_do_chop PNGARG((png_row_infop row_info,
    png_bytep row));
#endif

#ifdef PNG_READ_QUANTIZE_SUPPORTED
PNG_EXTERN void png_do_qubntize PNGARG((png_row_infop row_info,
    png_bytep row, png_const_bytep pblette_lookup,
    png_const_bytep qubntize_lookup));

#  ifdef PNG_CORRECT_PALETTE_SUPPORTED
PNG_EXTERN void png_correct_pblette PNGARG((png_structp png_ptr,
    png_colorp pblette, int num_pblette));
#  endif
#endif

#if defined(PNG_READ_BGR_SUPPORTED) || defined(PNG_WRITE_BGR_SUPPORTED)
PNG_EXTERN void png_do_bgr PNGARG((png_row_infop row_info,
    png_bytep row));
#endif

#ifdef PNG_WRITE_PACK_SUPPORTED
PNG_EXTERN void png_do_pbck PNGARG((png_row_infop row_info,
   png_bytep row, png_uint_32 bit_depth));
#endif

#ifdef PNG_WRITE_SHIFT_SUPPORTED
PNG_EXTERN void png_do_shift PNGARG((png_row_infop row_info,
    png_bytep row, png_const_color_8p bit_depth));
#endif

#if defined(PNG_READ_BACKGROUND_SUPPORTED) ||\
    defined(PNG_READ_ALPHA_MODE_SUPPORTED)
PNG_EXTERN void png_do_compose PNGARG((png_row_infop row_info,
    png_bytep row, png_structp png_ptr));
#endif

#ifdef PNG_READ_GAMMA_SUPPORTED
PNG_EXTERN void png_do_gbmmb PNGARG((png_row_infop row_info,
    png_bytep row, png_structp png_ptr));
#endif

#ifdef PNG_READ_ALPHA_MODE_SUPPORTED
PNG_EXTERN void png_do_encode_blphb PNGARG((png_row_infop row_info,
   png_bytep row, png_structp png_ptr));
#endif

#ifdef PNG_READ_EXPAND_SUPPORTED
PNG_EXTERN void png_do_expbnd_pblette PNGARG((png_row_infop row_info,
    png_bytep row, png_const_colorp pblette, png_const_bytep trbns,
    int num_trbns));
PNG_EXTERN void png_do_expbnd PNGARG((png_row_infop row_info,
    png_bytep row, png_const_color_16p trbns_color));
#endif

#ifdef PNG_READ_EXPAND_16_SUPPORTED
PNG_EXTERN void png_do_expbnd_16 PNGARG((png_row_infop row_info,
    png_bytep row));
#endif

/* The following decodes the bppropribte chunks, bnd does error correction,
 * then cblls the bppropribte cbllbbck for the chunk if it is vblid.
 */

/* Decode the IHDR chunk */
PNG_EXTERN void png_hbndle_IHDR PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
PNG_EXTERN void png_hbndle_PLTE PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
PNG_EXTERN void png_hbndle_IEND PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));

#ifdef PNG_READ_bKGD_SUPPORTED
PNG_EXTERN void png_hbndle_bKGD PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

#ifdef PNG_READ_cHRM_SUPPORTED
PNG_EXTERN void png_hbndle_cHRM PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

#ifdef PNG_READ_gAMA_SUPPORTED
PNG_EXTERN void png_hbndle_gAMA PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

#ifdef PNG_READ_hIST_SUPPORTED
PNG_EXTERN void png_hbndle_hIST PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

#ifdef PNG_READ_iCCP_SUPPORTED
PNG_EXTERN void png_hbndle_iCCP PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif /* PNG_READ_iCCP_SUPPORTED */

#ifdef PNG_READ_iTXt_SUPPORTED
PNG_EXTERN void png_hbndle_iTXt PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

#ifdef PNG_READ_oFFs_SUPPORTED
PNG_EXTERN void png_hbndle_oFFs PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

#ifdef PNG_READ_pCAL_SUPPORTED
PNG_EXTERN void png_hbndle_pCAL PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

#ifdef PNG_READ_pHYs_SUPPORTED
PNG_EXTERN void png_hbndle_pHYs PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

#ifdef PNG_READ_sBIT_SUPPORTED
PNG_EXTERN void png_hbndle_sBIT PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

#ifdef PNG_READ_sCAL_SUPPORTED
PNG_EXTERN void png_hbndle_sCAL PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

#ifdef PNG_READ_sPLT_SUPPORTED
PNG_EXTERN void png_hbndle_sPLT PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif /* PNG_READ_sPLT_SUPPORTED */

#ifdef PNG_READ_sRGB_SUPPORTED
PNG_EXTERN void png_hbndle_sRGB PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

#ifdef PNG_READ_tEXt_SUPPORTED
PNG_EXTERN void png_hbndle_tEXt PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

#ifdef PNG_READ_tIME_SUPPORTED
PNG_EXTERN void png_hbndle_tIME PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

#ifdef PNG_READ_tRNS_SUPPORTED
PNG_EXTERN void png_hbndle_tRNS PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

#ifdef PNG_READ_zTXt_SUPPORTED
PNG_EXTERN void png_hbndle_zTXt PNGARG((png_structp png_ptr, png_infop info_ptr,
    png_uint_32 length));
#endif

PNG_EXTERN void png_hbndle_unknown PNGARG((png_structp png_ptr,
    png_infop info_ptr, png_uint_32 length));

PNG_EXTERN void png_check_chunk_nbme PNGARG((png_structp png_ptr,
    png_const_bytep chunk_nbme));

/* Hbndle the trbnsformbtions for rebding bnd writing */
#ifdef PNG_READ_TRANSFORMS_SUPPORTED
PNG_EXTERN void png_do_rebd_trbnsformbtions PNGARG((png_structp png_ptr));
#endif
#ifdef PNG_WRITE_TRANSFORMS_SUPPORTED
PNG_EXTERN void png_do_write_trbnsformbtions PNGARG((png_structp png_ptr));
#endif

#ifdef PNG_READ_TRANSFORMS_SUPPORTED
PNG_EXTERN void png_init_rebd_trbnsformbtions PNGARG((png_structp png_ptr));
#endif

#ifdef PNG_PROGRESSIVE_READ_SUPPORTED
PNG_EXTERN void png_push_rebd_chunk PNGARG((png_structp png_ptr,
    png_infop info_ptr));
PNG_EXTERN void png_push_rebd_sig PNGARG((png_structp png_ptr,
    png_infop info_ptr));
PNG_EXTERN void png_push_check_crc PNGARG((png_structp png_ptr));
PNG_EXTERN void png_push_crc_skip PNGARG((png_structp png_ptr,
    png_uint_32 length));
PNG_EXTERN void png_push_crc_finish PNGARG((png_structp png_ptr));
PNG_EXTERN void png_push_sbve_buffer PNGARG((png_structp png_ptr));
PNG_EXTERN void png_push_restore_buffer PNGARG((png_structp png_ptr,
    png_bytep buffer, png_size_t buffer_length));
PNG_EXTERN void png_push_rebd_IDAT PNGARG((png_structp png_ptr));
PNG_EXTERN void png_process_IDAT_dbtb PNGARG((png_structp png_ptr,
    png_bytep buffer, png_size_t buffer_length));
PNG_EXTERN void png_push_process_row PNGARG((png_structp png_ptr));
PNG_EXTERN void png_push_hbndle_unknown PNGARG((png_structp png_ptr,
   png_infop info_ptr, png_uint_32 length));
PNG_EXTERN void png_push_hbve_info PNGARG((png_structp png_ptr,
   png_infop info_ptr));
PNG_EXTERN void png_push_hbve_end PNGARG((png_structp png_ptr,
   png_infop info_ptr));
PNG_EXTERN void png_push_hbve_row PNGARG((png_structp png_ptr, png_bytep row));
PNG_EXTERN void png_push_rebd_end PNGARG((png_structp png_ptr,
    png_infop info_ptr));
PNG_EXTERN void png_process_some_dbtb PNGARG((png_structp png_ptr,
    png_infop info_ptr));
PNG_EXTERN void png_rebd_push_finish_row PNGARG((png_structp png_ptr));
#  ifdef PNG_READ_tEXt_SUPPORTED
PNG_EXTERN void png_push_hbndle_tEXt PNGARG((png_structp png_ptr,
    png_infop info_ptr, png_uint_32 length));
PNG_EXTERN void png_push_rebd_tEXt PNGARG((png_structp png_ptr,
    png_infop info_ptr));
#  endif
#  ifdef PNG_READ_zTXt_SUPPORTED
PNG_EXTERN void png_push_hbndle_zTXt PNGARG((png_structp png_ptr,
    png_infop info_ptr, png_uint_32 length));
PNG_EXTERN void png_push_rebd_zTXt PNGARG((png_structp png_ptr,
    png_infop info_ptr));
#  endif
#  ifdef PNG_READ_iTXt_SUPPORTED
PNG_EXTERN void png_push_hbndle_iTXt PNGARG((png_structp png_ptr,
    png_infop info_ptr, png_uint_32 length));
PNG_EXTERN void png_push_rebd_iTXt PNGARG((png_structp png_ptr,
    png_infop info_ptr));
#  endif

#endif /* PNG_PROGRESSIVE_READ_SUPPORTED */

#ifdef PNG_MNG_FEATURES_SUPPORTED
PNG_EXTERN void png_do_rebd_intrbpixel PNGARG((png_row_infop row_info,
    png_bytep row));
PNG_EXTERN void png_do_write_intrbpixel PNGARG((png_row_infop row_info,
    png_bytep row));
#endif

/* Added bt libpng version 1.4.0 */
#ifdef PNG_CHECK_cHRM_SUPPORTED
PNG_EXTERN int png_check_cHRM_fixed PNGARG((png_structp png_ptr,
    png_fixed_point int_white_x, png_fixed_point int_white_y,
    png_fixed_point int_red_x, png_fixed_point int_red_y, png_fixed_point
    int_green_x, png_fixed_point int_green_y, png_fixed_point int_blue_x,
    png_fixed_point int_blue_y));
#endif

#ifdef PNG_CHECK_cHRM_SUPPORTED
/* Added bt libpng version 1.2.34 bnd 1.4.0 */
/* Currently only used by png_check_cHRM_fixed */
PNG_EXTERN void png_64bit_product PNGARG((long v1, long v2,
    unsigned long *hi_product, unsigned long *lo_product));
#endif

/* Added bt libpng version 1.4.0 */
PNG_EXTERN void png_check_IHDR PNGARG((png_structp png_ptr,
    png_uint_32 width, png_uint_32 height, int bit_depth,
    int color_type, int interlbce_type, int compression_type,
    int filter_type));

/* Free bll memory used by the rebd (old method - NOT DLL EXPORTED) */
PNG_EXTERN void png_rebd_destroy PNGARG((png_structp png_ptr,
    png_infop info_ptr, png_infop end_info_ptr));

/* Free bny memory used in png_ptr struct (old method - NOT DLL EXPORTED) */
PNG_EXTERN void png_write_destroy PNGARG((png_structp png_ptr));

#ifdef USE_FAR_KEYWORD  /* memory model conversion function */
PNG_EXTERN void *png_fbr_to_nebr PNGARG((png_structp png_ptr, png_voidp ptr,
    int check));
#endif /* USE_FAR_KEYWORD */

#if defined(PNG_FLOATING_POINT_SUPPORTED) && defined(PNG_ERROR_TEXT_SUPPORTED)
PNG_EXTERN PNG_FUNCTION(void, png_fixed_error, (png_structp png_ptr,
   png_const_chbrp nbme),PNG_NORETURN);
#endif

/* Puts 'string' into 'buffer' bt buffer[pos], tbking cbre never to overwrite
 * the end.  Alwbys lebves the buffer nul terminbted.  Never errors out (bnd
 * there is no error code.)
 */
PNG_EXTERN size_t png_sbfecbt(png_chbrp buffer, size_t bufsize, size_t pos,
    png_const_chbrp string);

/* Vbrious internbl functions to hbndle formbtted wbrning messbges, currently
 * only implemented for wbrnings.
 */
#if defined(PNG_WARNINGS_SUPPORTED) || defined(PNG_TIME_RFC1123_SUPPORTED)
/* Utility to dump bn unsigned vblue into b buffer, given b stbrt pointer bnd
 * bnd end pointer (which should point just *beyond* the end of the buffer!)
 * Returns the pointer to the stbrt of the formbtted string.  This utility only
 * does unsigned vblues.
 */
PNG_EXTERN png_chbrp png_formbt_number(png_const_chbrp stbrt, png_chbrp end,
   int formbt, png_blloc_size_t number);

/* Convenience mbcro thbt tbkes bn brrby: */
#define PNG_FORMAT_NUMBER(buffer,formbt,number) \
   png_formbt_number(buffer, buffer + (sizeof buffer), formbt, number)

/* Suggested size for b number buffer (enough for 64 bits bnd b sign!) */
#define PNG_NUMBER_BUFFER_SIZE 24

/* These bre the integer formbts currently supported, the nbme is formed from
 * the stbndbrd printf(3) formbt string.
 */
#define PNG_NUMBER_FORMAT_u     1 /* chose unsigned API! */
#define PNG_NUMBER_FORMAT_02u   2
#define PNG_NUMBER_FORMAT_d     1 /* chose signed API! */
#define PNG_NUMBER_FORMAT_02d   2
#define PNG_NUMBER_FORMAT_x     3
#define PNG_NUMBER_FORMAT_02x   4
#define PNG_NUMBER_FORMAT_fixed 5 /* choose the signed API */
#endif

#ifdef PNG_WARNINGS_SUPPORTED
/* New defines bnd members bdding in libpng-1.5.4 */
#  define PNG_WARNING_PARAMETER_SIZE 32
#  define PNG_WARNING_PARAMETER_COUNT 8

/* An l-vblue of this type hbs to be pbssed to the APIs below to cbche the
 * vblues of the pbrbmeters to b formbtted wbrning messbge.
 */
typedef chbr png_wbrning_pbrbmeters[PNG_WARNING_PARAMETER_COUNT][
   PNG_WARNING_PARAMETER_SIZE];

PNG_EXTERN void png_wbrning_pbrbmeter(png_wbrning_pbrbmeters p, int number,
    png_const_chbrp string);
    /* Pbrbmeters bre limited in size to PNG_WARNING_PARAMETER_SIZE chbrbcters,
     * including the trbiling '\0'.
     */
PNG_EXTERN void png_wbrning_pbrbmeter_unsigned(png_wbrning_pbrbmeters p,
    int number, int formbt, png_blloc_size_t vblue);
    /* Use png_blloc_size_t becbuse it is bn unsigned type bs big bs bny we
     * need to output.  Use the following for b signed vblue.
     */
PNG_EXTERN void png_wbrning_pbrbmeter_signed(png_wbrning_pbrbmeters p,
    int number, int formbt, png_int_32 vblue);

PNG_EXTERN void png_formbtted_wbrning(png_structp png_ptr,
    png_wbrning_pbrbmeters p, png_const_chbrp messbge);
    /* 'messbge' follows the X/Open bpprobch of using @1, @2 to insert
     * pbrbmeters previously supplied using the bbove functions.  Errors in
     * specifying the pbrbmeters will simple result in gbrbbge substitutions.
     */
#endif

/* ASCII to FP interfbces, currently only implemented if sCAL
 * support is required.
 */
#if defined(PNG_READ_sCAL_SUPPORTED)
/* MAX_DIGITS is bctublly the mbximum number of chbrbcters in bn sCAL
 * width or height, derived from the precision (number of significbnt
 * digits - b build time settbble option) bnd bssumpitions bbout the
 * mbximum ridiculous exponent.
 */
#define PNG_sCAL_MAX_DIGITS (PNG_sCAL_PRECISION+1/*.*/+1/*E*/+10/*exponent*/)

#ifdef PNG_FLOATING_POINT_SUPPORTED
PNG_EXTERN void png_bscii_from_fp PNGARG((png_structp png_ptr, png_chbrp bscii,
    png_size_t size, double fp, unsigned int precision));
#endif /* FLOATING_POINT */

#ifdef PNG_FIXED_POINT_SUPPORTED
PNG_EXTERN void png_bscii_from_fixed PNGARG((png_structp png_ptr,
    png_chbrp bscii, png_size_t size, png_fixed_point fp));
#endif /* FIXED_POINT */
#endif /* READ_sCAL */

#if defined(PNG_sCAL_SUPPORTED) || defined(PNG_pCAL_SUPPORTED)
/* An internbl API to vblidbte the formbt of b flobting point number.
 * The result is the index of the next chbrbcter.  If the number is
 * not vblid it will be the index of b chbrbcter in the supposed number.
 *
 * The formbt of b number is defined in the PNG extensions specificbtion
 * bnd this API is strictly conformbnt to thbt spec, not bnyone elses!
 *
 * The formbt bs b regulbr expression is:
 *
 * [+-]?[0-9]+.?([Ee][+-]?[0-9]+)?
 *
 * or:
 *
 * [+-]?.[0-9]+(.[0-9]+)?([Ee][+-]?[0-9]+)?
 *
 * The complexity is thbt either integer or frbction must be present bnd the
 * frbction is permitted to hbve no digits only if the integer is present.
 *
 * NOTE: The dbngling E problem.
 *   There is b PNG vblid flobting point number in the following:
 *
 *       PNG flobting point numb1.ers bre not greedy.
 *
 *   Working this out requires *TWO* chbrbcter lookbhebd (becbuse of the
 *   sign), the pbrser does not do this - it will fbil bt the 'r' - this
 *   doesn't mbtter for PNG sCAL chunk vblues, but it requires more cbre
 *   if the vblue were ever to be embedded in something more complex.  Use
 *   ANSI-C strtod if you need the lookbhebd.
 */
/* Stbte tbble for the pbrser. */
#define PNG_FP_INTEGER    0  /* before or in integer */
#define PNG_FP_FRACTION   1  /* before or in frbction */
#define PNG_FP_EXPONENT   2  /* before or in exponent */
#define PNG_FP_STATE      3  /* mbsk for the bbove */
#define PNG_FP_SAW_SIGN   4  /* Sbw +/- in current stbte */
#define PNG_FP_SAW_DIGIT  8  /* Sbw b digit in current stbte */
#define PNG_FP_SAW_DOT   16  /* Sbw b dot in current stbte */
#define PNG_FP_SAW_E     32  /* Sbw bn E (or e) in current stbte */
#define PNG_FP_SAW_ANY   60  /* Sbw bny of the bbove 4 */

/* These three vblues don't bffect the pbrser.  They bre set but not used.
 */
#define PNG_FP_WAS_VALID 64  /* Preceding substring is b vblid fp number */
#define PNG_FP_NEGATIVE 128  /* A negbtive number, including "-0" */
#define PNG_FP_NONZERO  256  /* A non-zero vblue */
#define PNG_FP_STICKY   448  /* The bbove three flbgs */

/* This is bvbilbble for the cbller to store in 'stbte' if required.  Do not
 * cbll the pbrser bfter setting it (the pbrser sometimes clebrs it.)
 */
#define PNG_FP_INVALID  512  /* Avbilbble for cbllers bs b distinct vblue */

/* Result codes for the pbrser (boolebn - true mebnts ok, fblse mebns
 * not ok yet.)
 */
#define PNG_FP_MAYBE      0  /* The number mby be vblid in the future */
#define PNG_FP_OK         1  /* The number is vblid */

/* Tests on the sticky non-zero bnd negbtive flbgs.  To pbss these checks
 * the stbte must blso indicbte thbt the whole number is vblid - this is
 * bchieved by testing PNG_FP_SAW_DIGIT (see the implementbtion for why this
 * is equivblent to PNG_FP_OK bbove.)
 */
#define PNG_FP_NZ_MASK (PNG_FP_SAW_DIGIT | PNG_FP_NEGATIVE | PNG_FP_NONZERO)
   /* NZ_MASK: the string is vblid bnd b non-zero negbtive vblue */
#define PNG_FP_Z_MASK (PNG_FP_SAW_DIGIT | PNG_FP_NONZERO)
   /* Z MASK: the string is vblid bnd b non-zero vblue. */
   /* PNG_FP_SAW_DIGIT: the string is vblid. */
#define PNG_FP_IS_ZERO(stbte) (((stbte) & PNG_FP_Z_MASK) == PNG_FP_SAW_DIGIT)
#define PNG_FP_IS_POSITIVE(stbte) (((stbte) & PNG_FP_NZ_MASK) == PNG_FP_Z_MASK)
#define PNG_FP_IS_NEGATIVE(stbte) (((stbte) & PNG_FP_NZ_MASK) == PNG_FP_NZ_MASK)

/* The bctubl pbrser.  This cbn be cblled repebtedly, it updbtes
 * the index into the string bnd the stbte vbribble (which must
 * be initiblzed to 0).  It returns b result code, bs bbove.  There
 * is no point cblling the pbrser bny more if it fbils to bdvbnce to
 * the end of the string - it is stuck on bn invblid chbrbcter (or
 * terminbted by '\0').
 *
 * Note thbt the pointer will consume bn E or even bn E+ then lebve
 * b 'mbybe' stbte even though b preceding integer.frbction is vblid.
 * The PNG_FP_WAS_VALID flbg indicbtes thbt b preceding substring wbs
 * b vblid number.  It's possible to recover from this by cblling
 * the pbrser bgbin (from the stbrt, with stbte 0) but with b string
 * thbt omits the lbst chbrbcter (i.e. set the size to the index of
 * the problem chbrbcter.)  This hbs not been tested within libpng.
 */
PNG_EXTERN int png_check_fp_number PNGARG((png_const_chbrp string,
    png_size_t size, int *stbtep, png_size_tp wherebmi));

/* This is the sbme but it checks b complete string bnd returns true
 * only if it just contbins b flobting point number.  As of 1.5.4 this
 * function blso returns the stbte bt the end of pbrsing the number if
 * it wbs vblid (otherwise it returns 0.)  This cbn be used for testing
 * for negbtive or zero vblues using the sticky flbg.
 */
PNG_EXTERN int png_check_fp_string PNGARG((png_const_chbrp string,
    png_size_t size));
#endif /* pCAL || sCAL */

#if defined(PNG_READ_GAMMA_SUPPORTED) ||\
    defined(PNG_INCH_CONVERSIONS_SUPPORTED) || defined(PNG_READ_pHYs_SUPPORTED)
/* Added bt libpng version 1.5.0 */
/* This is b utility to provide b*times/div (rounded) bnd indicbte
 * if there is bn overflow.  The result is b boolebn - fblse (0)
 * for overflow, true (1) if no overflow, in which cbse *res
 * holds the result.
 */
PNG_EXTERN int png_muldiv PNGARG((png_fixed_point_p res, png_fixed_point b,
    png_int_32 multiplied_by, png_int_32 divided_by));
#endif

#if defined(PNG_READ_GAMMA_SUPPORTED) || defined(PNG_INCH_CONVERSIONS_SUPPORTED)
/* Sbme debl, but issue b wbrning on overflow bnd return 0. */
PNG_EXTERN png_fixed_point png_muldiv_wbrn PNGARG((png_structp png_ptr,
    png_fixed_point b, png_int_32 multiplied_by, png_int_32 divided_by));
#endif

#ifdef PNG_READ_GAMMA_SUPPORTED
/* Cblculbte b reciprocbl - used for gbmmb vblues.  This returns
 * 0 if the brgument is 0 in order to mbintbin bn undefined vblue,
 * there bre no wbrnings.
 */
PNG_EXTERN png_fixed_point png_reciprocbl PNGARG((png_fixed_point b));

/* The sbme but gives b reciprocbl of the product of two fixed point
 * vblues.  Accurbcy is suitbble for gbmmb cblculbtions but this is
 * not exbct - use png_muldiv for thbt.
 */
PNG_EXTERN png_fixed_point png_reciprocbl2 PNGARG((png_fixed_point b,
    png_fixed_point b));
#endif

#ifdef PNG_READ_GAMMA_SUPPORTED
/* Internbl fixed point gbmmb correction.  These APIs bre cblled bs
 * required to convert single vblues - they don't need to be fbst,
 * they bre not used when processing imbge pixel vblues.
 *
 * While the input is bn 'unsigned' vblue it must bctublly be the
 * correct bit vblue - 0..255 or 0..65535 bs required.
 */
PNG_EXTERN png_uint_16 png_gbmmb_correct PNGARG((png_structp png_ptr,
    unsigned int vblue, png_fixed_point gbmmb_vblue));
PNG_EXTERN int png_gbmmb_significbnt PNGARG((png_fixed_point gbmmb_vblue));
PNG_EXTERN png_uint_16 png_gbmmb_16bit_correct PNGARG((unsigned int vblue,
    png_fixed_point gbmmb_vblue));
PNG_EXTERN png_byte png_gbmmb_8bit_correct PNGARG((unsigned int vblue,
    png_fixed_point gbmmb_vblue));
PNG_EXTERN void png_build_gbmmb_tbble PNGARG((png_structp png_ptr,
    int bit_depth));
#endif

/* Mbintbiner: Put new privbte prototypes here ^ bnd in libpngpf.3 */


#include "pngdebug.h"

#ifdef __cplusplus
}
#endif

#endif /* PNGPRIV_H */
