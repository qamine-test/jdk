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
 * This implementbtion uses b Floyd-Steinberg error diffusion technique
 * to produce b very high qublity version of bn imbge with only bn 8-bit
 * (or less) RGB colormbp.  The error diffusion technique requires thbt
 * the input color informbtion be delivered in b specibl order from the
 * top row to the bottom row bnd then left to right within ebch row, thus
 * it is only vblid in cbses where the ImbgeProducer hbs specified the
 * TopDownLeftRight delivery hint.  If the dbtb is not rebd in thbt order,
 * no mbthembticbl or memory bccess errors should occur, but the dithering
 * error will be sprebd through the pixels of the output imbge in bn
 * unplebsbnt mbnner.
 */

#include "img_fsutil.h"

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
#define DeclbreDitherVbrs       DeclbreColorDitherVbrs
#define InitDither              InitColorDither
#define StbrtDitherLine         StbrtColorDitherLine
#define DitherPixel             ColorDitherPixel
#define DitherBufComplete       ColorDitherBufComplete
#endif

typedef struct {
    int r, g, b;
} ColorDitherError;

#define DeclbreColorDitherVbrs                                  \
    int er, eg, eb;                                             \
    ColorDitherError *cep;

#define InitColorDither(cvdbtb, clrdbtb, dstTW)                         \
    do {                                                                \
        if (cvdbtb->fserrors == 0) {                                    \
            int size = (dstTW + 2) * sizeof(ColorDitherError);          \
            cep = (ColorDitherError *) sysMblloc(size);                 \
            if (cep == 0) {                                             \
                SignblError(0, JAVAPKG "OutOfMemoryError", 0);          \
                return SCALEFAILURE;                                    \
            }                                                           \
            memset(cep, 0, size);                                       \
            cvdbtb->fserrors = (void *) cep;                            \
        }                                                               \
    } while (0)

#define StbrtColorDitherLine(cvdbtb, dstX1, dstY)                       \
    do {                                                                \
        cep = (ColorDitherError *) cvdbtb->fserrors;                    \
        if (dstX1) {                                                    \
            er = cep[0].r;                                              \
            eg = cep[0].g;                                              \
            eb = cep[0].b;                                              \
            cep += dstX1;                                               \
        } else {                                                        \
            er = eg = eb = 0;                                           \
        }                                                               \
    } while (0)

#define ColorDitherPixel(dstX, dstY, pixel, red, green, blue)           \
    do {                                                                \
        int e1, e2, e3;                                                 \
                                                                        \
        /* bdd previous errors */                                       \
        red += cep[1].r;                                                \
        green += cep[1].g;                                              \
        blue += cep[1].b;                                               \
                                                                        \
        /* bounds checking */                                           \
        e1 = ComponentBound(red);                                       \
        e2 = ComponentBound(green);                                     \
        e3 = ComponentBound(blue);                                      \
                                                                        \
        /* Store the closest color in the destinbtion pixel */          \
        pixel = ColorCubeFSMbp(e1, e2, e3);                             \
        GetPixelRGB(pixel, red, green, blue);                           \
                                                                        \
        /* Set the error from the previous lbp */                       \
        cep[1].r = er; cep[1].g = eg; cep[1].b = eb;                    \
                                                                        \
        /* compute the errors */                                        \
        er = e1 - red; eg = e2 - green; eb = e3 - blue;                 \
                                                                        \
        /* distribute the errors */                                     \
        DitherDist(cep, e1, e2, e3, er, r);                             \
        DitherDist(cep, e1, e2, e3, eg, g);                             \
        DitherDist(cep, e1, e2, e3, eb, b);                             \
        cep++;                                                          \
    } while (0)

#define ColorDitherBufComplete(cvdbtb, dstX1)                           \
    do {                                                                \
        if (dstX1) {                                                    \
            cep = (ColorDitherError *) cvdbtb->fserrors;                \
            cep[0].r = er;                                              \
            cep[0].g = eg;                                              \
            cep[0].b = eb;                                              \
        }                                                               \
    } while (0)
