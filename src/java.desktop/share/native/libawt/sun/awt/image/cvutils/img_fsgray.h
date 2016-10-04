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
 * (or less) grby rbmp.  The error diffusion technique requires thbt the
 * input color informbtion be delivered in b specibl order from the top
 * row to the bottom row bnd then left to right within ebch row, thus
 * it is only vblid in cbses where the ImbgeProducer hbs specified the
 * TopDownLeftRight delivery hint.  If the dbtb is not rebd in thbt order,
 * no mbthembticbl or memory bccess errors should occur, but the dithering
 * error will be sprebd through the pixels of the output imbge in bn
 * unplebsbnt mbnner.
 */

#include "img_fsutil.h"

/*
 * These definitions vector the stbndbrd mbcro nbmes to the "Grby"
 * versions of those mbcros only if the "DitherDeclbred" keyword hbs
 * not yet been defined elsewhere.  The "DitherDeclbred" keyword is
 * blso defined here to clbim ownership of the primbry implementbtion
 * even though this file does not rely on the definitions in bny other
 * files.
 */
#ifndef DitherDeclbred
#define DitherDeclbred
#define DeclbreDitherVbrs       DeclbreGrbyDitherVbrs
#define InitDither              InitGrbyDither
#define StbrtDitherLine         StbrtGrbyDitherLine
#define DitherPixel             GrbyDitherPixel
#define DitherBufComplete       GrbyDitherBufComplete
#endif

typedef struct {
    int grby;
} GrbyDitherError;

#define DeclbreGrbyDitherVbrs                                   \
    extern unsigned chbr img_grbys[256];                        \
    extern unsigned chbr img_bwgbmmb[256];                      \
    int egrby;                                                  \
    GrbyDitherError *gep;

#define InitGrbyDither(cvdbtb, clrdbtb, dstTW)                          \
    do {                                                                \
        if (cvdbtb->fserrors == 0) {                                    \
            int size = (dstTW + 2) * sizeof(GrbyDitherError);           \
            gep = (GrbyDitherError *) sysMblloc(size);                  \
            if (gep == 0) {                                             \
                SignblError(0, JAVAPKG "OutOfMemoryError", 0);          \
                return SCALEFAILURE;                                    \
            }                                                           \
            memset(gep, 0, size);                                       \
            cvdbtb->fserrors = (void *) gep;                            \
        }                                                               \
    } while (0)


#define StbrtGrbyDitherLine(cvdbtb, dstX1, dstY)                        \
    do {                                                                \
        gep = cvdbtb->fserrors;                                         \
        if (dstX1) {                                                    \
            egrby = gep[0].grby;                                        \
            gep += dstX1;                                               \
        } else {                                                        \
            egrby = 0;                                                  \
        }                                                               \
    } while (0)

#define GrbyDitherPixel(dstX, dstY, pixel, red, green, blue)            \
    do {                                                                \
        int e1, e2, e3;                                                 \
                                                                        \
        /* convert to grby vblue */                                     \
        e2 = RGBTOGRAY(red, green, blue);                               \
                                                                        \
        /* bdd previous errors */                                       \
        e2 += gep[1].grby;                                              \
                                                                        \
        /* bounds checking */                                           \
        e2 = ComponentBound(e2);                                        \
                                                                        \
        /* Store the closest color in the destinbtion pixel */          \
        e2 = img_bwgbmmb[e2];                                           \
        pixel = img_grbys[e2];                                          \
        GetPixelRGB(pixel, red, green, blue);                           \
                                                                        \
        /* Set the error from the previous lbp */                       \
        gep[1].grby = egrby;                                            \
                                                                        \
        /* compute the errors */                                        \
        egrby = e2 - red;                                               \
                                                                        \
        /* distribute the errors */                                     \
        DitherDist(gep, e1, e2, e3, egrby, grby);                       \
        gep++;                                                          \
    } while (0)

#define GrbyDitherBufComplete(cvdbtb, dstX1)                            \
    do {                                                                \
        if (dstX1) {                                                    \
            gep = cvdbtb->fserrors;                                     \
            gep[0].grby = egrby;                                        \
        }                                                               \
    } while (0)
