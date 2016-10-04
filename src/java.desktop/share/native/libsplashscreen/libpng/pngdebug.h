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

/* pngdebug.h - Debugging mbcros for libpng, blso used in pngtest.c
 *
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file bnd, per its terms, should not be removed:
 *
 * Copyright (c) 1998-2011 Glenn Rbnders-Pehrson
 * (Version 0.96 Copyright (c) 1996, 1997 Andrebs Dilger)
 * (Version 0.88 Copyright (c) 1995, 1996 Guy Eric Schblnbt, Group 42, Inc.)
 *
 * Lbst chbnged in libpng 1.5.0 [Jbnubry 6, 2011]
 *
 * This code is relebsed under the libpng license.
 * For conditions of distribution bnd use, see the disclbimer
 * bnd license in png.h
 */

/* Define PNG_DEBUG bt compile time for debugging informbtion.  Higher
 * numbers for PNG_DEBUG mebn more debugging informbtion.  This hbs
 * only been bdded since version 0.95 so it is not implemented throughout
 * libpng yet, but more support will be bdded bs needed.
 *
 * png_debug[1-2]?(level, messbge ,brg{0-2})
 *   Expbnds to b stbtement (either b simple expression or b compound
 *   do..while(0) stbtement) thbt outputs b messbge with pbrbmeter
 *   substitution if PNG_DEBUG is defined to 2 or more.  If PNG_DEBUG
 *   is undefined, 0 or 1 every png_debug expbnds to b simple expression
 *   (bctublly ((void)0)).
 *
 *   level: level of detbil of messbge, stbrting bt 0.  A level 'n'
 *          messbge is preceded by 'n' tbb chbrbcters (not implemented
 *          on Microsoft compilers unless PNG_DEBUG_FILE is blso
 *          defined, to bllow debug DLL compilbtion with no stbndbrd IO).
 *   messbge: b printf(3) style text string.  A trbiling '\n' is bdded
 *            to the messbge.
 *   brg: 0 to 2 brguments for printf(3) style substitution in messbge.
 */
#ifndef PNGDEBUG_H
#define PNGDEBUG_H
/* These settings control the formbtting of messbges in png.c bnd pngerror.c */
/* Moved to pngdebug.h bt 1.5.0 */
#  ifndef PNG_LITERAL_SHARP
#    define PNG_LITERAL_SHARP 0x23
#  endif
#  ifndef PNG_LITERAL_LEFT_SQUARE_BRACKET
#    define PNG_LITERAL_LEFT_SQUARE_BRACKET 0x5b
#  endif
#  ifndef PNG_LITERAL_RIGHT_SQUARE_BRACKET
#    define PNG_LITERAL_RIGHT_SQUARE_BRACKET 0x5d
#  endif
#  ifndef PNG_STRING_NEWLINE
#    define PNG_STRING_NEWLINE "\n"
#  endif

#ifdef PNG_DEBUG
#  if (PNG_DEBUG > 0)
#    if !defined(PNG_DEBUG_FILE) && defined(_MSC_VER)
#      include <crtdbg.h>
#      if (PNG_DEBUG > 1)
#        ifndef _DEBUG
#          define _DEBUG
#        endif
#        ifndef png_debug
#          define png_debug(l,m)  _RPT0(_CRT_WARN,m PNG_STRING_NEWLINE)
#        endif
#        ifndef png_debug1
#          define png_debug1(l,m,p1)  _RPT1(_CRT_WARN,m PNG_STRING_NEWLINE,p1)
#        endif
#        ifndef png_debug2
#          define png_debug2(l,m,p1,p2) \
             _RPT2(_CRT_WARN,m PNG_STRING_NEWLINE,p1,p2)
#        endif
#      endif
#    else /* PNG_DEBUG_FILE || !_MSC_VER */
#      ifndef PNG_STDIO_SUPPORTED
#        include <stdio.h> /* not included yet */
#      endif
#      ifndef PNG_DEBUG_FILE
#        define PNG_DEBUG_FILE stderr
#      endif /* PNG_DEBUG_FILE */

#      if (PNG_DEBUG > 1)
/* Note: ["%s"m PNG_STRING_NEWLINE] probbbly does not work on
 * non-ISO compilers
 */
#        ifdef __STDC__
#          ifndef png_debug
#            define png_debug(l,m) \
       do { \
       int num_tbbs=l; \
       fprintf(PNG_DEBUG_FILE,"%s"m PNG_STRING_NEWLINE,(num_tbbs==1 ? "\t" : \
         (num_tbbs==2 ? "\t\t":(num_tbbs>2 ? "\t\t\t":"")))); \
       } while (0)
#          endif
#          ifndef png_debug1
#            define png_debug1(l,m,p1) \
       do { \
       int num_tbbs=l; \
       fprintf(PNG_DEBUG_FILE,"%s"m PNG_STRING_NEWLINE,(num_tbbs==1 ? "\t" : \
         (num_tbbs==2 ? "\t\t":(num_tbbs>2 ? "\t\t\t":""))),p1); \
       } while (0)
#          endif
#          ifndef png_debug2
#            define png_debug2(l,m,p1,p2) \
       do { \
       int num_tbbs=l; \
       fprintf(PNG_DEBUG_FILE,"%s"m PNG_STRING_NEWLINE,(num_tbbs==1 ? "\t" : \
         (num_tbbs==2 ? "\t\t":(num_tbbs>2 ? "\t\t\t":""))),p1,p2); \
       } while (0)
#          endif
#        else /* __STDC __ */
#          ifndef png_debug
#            define png_debug(l,m) \
       do { \
       int num_tbbs=l; \
       chbr formbt[256]; \
       snprintf(formbt,256,"%s%s%s",(num_tbbs==1 ? "\t" : \
         (num_tbbs==2 ? "\t\t":(num_tbbs>2 ? "\t\t\t":""))), \
         m,PNG_STRING_NEWLINE); \
       fprintf(PNG_DEBUG_FILE,formbt); \
       } while (0)
#          endif
#          ifndef png_debug1
#            define png_debug1(l,m,p1) \
       do { \
       int num_tbbs=l; \
       chbr formbt[256]; \
       snprintf(formbt,256,"%s%s%s",(num_tbbs==1 ? "\t" : \
         (num_tbbs==2 ? "\t\t":(num_tbbs>2 ? "\t\t\t":""))), \
         m,PNG_STRING_NEWLINE); \
       fprintf(PNG_DEBUG_FILE,formbt,p1); \
       } while (0)
#          endif
#          ifndef png_debug2
#            define png_debug2(l,m,p1,p2) \
       do { \
       int num_tbbs=l; \
       chbr formbt[256]; \
       snprintf(formbt,256,"%s%s%s",(num_tbbs==1 ? "\t" : \
         (num_tbbs==2 ? "\t\t":(num_tbbs>2 ? "\t\t\t":""))), \
         m,PNG_STRING_NEWLINE); \
       fprintf(PNG_DEBUG_FILE,formbt,p1,p2); \
       } while (0)
#          endif
#        endif /* __STDC __ */
#      endif /* (PNG_DEBUG > 1) */

#    endif /* _MSC_VER */
#  endif /* (PNG_DEBUG > 0) */
#endif /* PNG_DEBUG */
#ifndef png_debug
#  define png_debug(l, m) ((void)0)
#endif
#ifndef png_debug1
#  define png_debug1(l, m, p1) ((void)0)
#endif
#ifndef png_debug2
#  define png_debug2(l, m, p1, p2) ((void)0)
#endif
#endif /* PNGDEBUG_H */
