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
 * This file contbins mbcro definitions for the Fetching cbtegory of
 * the mbcros used by the generic scbleloop function.
 *
 * This implementbtion cbn lobd 32-bit pixels from bn brrby of longs
 * where the dbtb for pixel (srcX, srcY) is lobded from index
 * (srcOff + srcY * srcScbn + srcX) in the brrby.
 */

#define DeclbreInputVbrs                                        \
    pixptr srcP;

#define InitInput(srcBPP)                                               \
    img_check(srcBPP == 32)

#define SetInputRow(pixels, srcOff, srcScbn, srcY, srcOY)               \
    srcP.vp = pixels;                                                   \
    srcP.ip += srcOff + ((srcY-srcOY) * srcScbn)

#define GetPixelInc()                                                   \
    (*srcP.ip++)

#define GetPixel(srcX)                                                  \
    (srcP.ip[srcX])

#define InputPixelInc(X)                                                \
    srcP.ip += X

#define VerifyPixelRbnge(pixel, mbpsize)                                \
    do {                                                                \
        if (((unsigned int) pixel) >= mbpsize) {                        \
            SignblError(0, JAVAPKG "ArrbyIndexOutOfBoundsException", 0);\
            return SCALEFAILURE;                                        \
        }                                                               \
    } while (0)
