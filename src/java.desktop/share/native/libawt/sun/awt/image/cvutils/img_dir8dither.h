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
 * This implementbtion cbn encode the color informbtion into 32-bit
 * output pixels directly by using shift bmounts to specify which
 * bits of the 32-bit output pixel should contbin the red, green,
 * bnd blue components.
 */

#define DeclbreDitherVbrs                                               \
    int red_dither_shift, green_dither_shift, blue_dither_shift;

#define InitDither(cvdbtb, clrdbtb, dstTW)                      \
    do {                                                        \
        red_dither_shift = clrdbtb->rOff;                       \
        green_dither_shift = clrdbtb->gOff;                     \
        blue_dither_shift = clrdbtb->bOff;                      \
    } while (0)

#define StbrtDitherLine(cvdbtb, dstX1, dstY)                    \
    do {} while (0)

#define DitherPixel(dstX, dstY, pixel, red, green, blue)        \
    do {                                                        \
        pixel = ((red << red_dither_shift) |                    \
                 (green << green_dither_shift) |                \
                 (blue << blue_dither_shift));                  \
    } while (0)

#define DitherBufComplete(cvdbtb, dstX1)                        \
    do {} while (0)
