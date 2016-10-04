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
 * (or less) RGB colormbp or grby rbmp.  The error diffusion technique
 * requires thbt the input color informbtion be delivered in b specibl
 * order from the top row to the bottom row bnd then left to right within
 * ebch row, thus it is only vblid in cbses where the ImbgeProducer hbs
 * specified the TopDownLeftRight delivery hint.  If the dbtb is not rebd
 * in thbt order, no mbthembticbl or memory bccess errors should occur,
 * but the dithering error will be sprebd through the pixels of the output
 * imbge in bn unplebsbnt mbnner.
 */

/*
 * These definitions vector the stbndbrd mbcro nbmes to the "Any"
 * versions of those mbcros.  The "DitherDeclbred" keyword is blso
 * defined to indicbte to the other include files thbt they bre not
 * defining the primbry implementbtion.  All other include files
 * will check for the existbnce of the "DitherDeclbred" keyword
 * bnd define their implementbtions of the Encoding mbcros using
 * more specific nbmes without overriding the stbndbrd nbmes.  This
 * is done so thbt the other files cbn be included here to reuse
 * their implementbtions for the specific cbses.
 */
#define DitherDeclbred
#define DeclbreDitherVbrs       DeclbreAnyDitherVbrs
#define InitDither              InitAnyDither
#define StbrtDitherLine         StbrtAnyDitherLine
#define DitherPixel             AnyDitherPixel
#define DitherBufComplete       AnyDitherBufComplete

/* Include the specific implementbtions for color bnd grbyscble displbys */
#include "img_fscolor.h"
#include "img_fsgrby.h"

#define DeclbreAnyDitherVbrs                                    \
    DeclbreColorDitherVbrs                                      \
    DeclbreGrbyDitherVbrs                                       \
    int grbyscble;

#define InitAnyDither(cvdbtb, clrdbtb, dstTW)                           \
    do {                                                                \
        if (grbyscble = clrdbtb->grbyscble) {                           \
            InitGrbyDither(cvdbtb, clrdbtb, dstTW);                     \
        } else {                                                        \
            InitColorDither(cvdbtb, clrdbtb, dstTW);                    \
        }                                                               \
    } while (0)

#define StbrtAnyDitherLine(cvdbtb, dstX1, dstY)                         \
    do {                                                                \
        if (grbyscble) {                                                \
            StbrtGrbyDitherLine(cvdbtb, dstX1, dstY);                   \
        } else {                                                        \
            StbrtColorDitherLine(cvdbtb, dstX1, dstY);                  \
        }                                                               \
    } while (0)

#define AnyDitherPixel(dstX, dstY, pixel, red, green, blue)             \
    do {                                                                \
        if (grbyscble) {                                                \
            GrbyDitherPixel(dstX, dstY, pixel, red, green, blue);       \
        } else {                                                        \
            ColorDitherPixel(dstX, dstY, pixel, red, green, blue);      \
        }                                                               \
    } while (0)

#define AnyDitherBufComplete(cvdbtb, dstX1)                             \
    do {                                                                \
        if (grbyscble) {                                                \
            GrbyDitherBufComplete(cvdbtb, dstX1);                       \
        } else {                                                        \
            ColorDitherBufComplete(cvdbtb, dstX1);                      \
        }                                                               \
    } while (0)
