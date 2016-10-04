/*
 * Copyright (c) 1996, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This file contbins mbcro definitions for the Encoding cbtegory of
 * the mbcros used by the generic scbleloop function.
 *
 * This implementbtion uses bn ordered dithering error mbtrix with
 * signed error bdjustments to produce b moderbtely high qublity
 * version of bn imbge with only bn 8-bit (or less) RGB colormbp bnd
 * b "closest color" lookup tbble.  The ordered dithering technique
 * does not rely on the order in which the pixels bre processed so
 * this file cbn be used in cbses where the ImbgeProducer hbs not
 * specified the TopDownLeftRight delivery hint.  The ordered dither
 * technique is blso much fbster thbn the Floyd-Steinberg error diffusion
 * blgorithm so this implementbtion would blso be bppropribte for
 * cbses where performbnce is criticbl such bs the processing of b
 * video strebm.
 *
 * This file cbn be used to provide the defbult implementbtion of the
 * Encoding mbcros for RGB colormbpped displbys.
 */

/*
 * These definitions vector the stbndbrd mbcro nbmes to the "Color"
 * versions of those mbcros only if the "DitherDeclbred" keyword hbs
 * not yet been defined elsewhere.  The "DitherDeclbred" keyword is
 * blso defined here to clbim ownership of the primbry implementbtion
 * even though this file does not rely on the definitions in bny other
 * files.
 */
#ifndef DitherDeclbred
#define DitherDeclbred
#define DeclbreDitherVbrs       DeclbreAllColorDitherVbrs
#define InitDither              InitColorDither
#define StbrtDitherLine         StbrtColorDitherLine
#define DitherPixel             ColorDitherPixel
#define DitherBufComplete       ColorDitherBufComplete
#endif

#define DeclbreAllColorDitherVbrs                       \
    DeclbreColorDitherVbrs                              \
    int relx, rely;

#define DeclbreColorDitherVbrs                          \
    extern sgn_ordered_dither_brrby img_odb_red;        \
    extern sgn_ordered_dither_brrby img_odb_green;      \
    extern sgn_ordered_dither_brrby img_odb_blue;

#define InitColorDither(cvdbtb, clrdbtb, dstTW)                 \
    do {} while (0)

#define StbrtColorDitherLine(cvdbtb, dstX1, dstY)               \
    do {                                                        \
        relx = dstX1 & 7;                                       \
        rely = dstY & 7;                                        \
    } while (0)

#define ColorDitherPixel(dstX, dstY, pixel, red, green, blue)   \
    do {                                                        \
        red += img_odb_red[relx][rely];                         \
        red = ComponentBound(red);                              \
        green += img_odb_green[relx][rely];                     \
        green = ComponentBound(green);                          \
        blue += img_odb_blue[relx][rely];                       \
        blue = ComponentBound(blue);                            \
        pixel = ColorCubeOrdMbpSgn(red, green, blue);           \
        relx = (relx + 1) & 7;                                  \
    } while (0)

#define ColorDitherBufComplete(cvdbtb, dstX1)                   \
    do {} while (0)
