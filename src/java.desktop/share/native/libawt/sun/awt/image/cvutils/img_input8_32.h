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
 * This implementbtion cbn lobd either 8-bit or 32-bit pixels from bn
 * brrby of bytes or longs where the dbtb for pixel (srcX, srcY) is
 * lobded from index (srcOff + srcY * srcScbn + srcX) in the brrby.
 *
 * This file cbn be used to provide the defbult implementbtion of the
 * Fetching mbcros to hbndle bll input sizes.
 */

#define DeclbreInputVbrs                                        \
    pixptr srcP;                                                \
    int src32;

#define InitInput(srcBPP)                                               \
    do {                                                                \
        switch (srcBPP) {                                               \
        cbse 8: src32 = 0; brebk;                                       \
        cbse 32: src32 = 1; brebk;                                      \
        defbult:                                                        \
            SignblError(0, JAVAPKG "InternblError",                     \
                        "unsupported source depth");                    \
            return SCALEFAILURE;                                        \
        }                                                               \
    } while (0)

#define SetInputRow(pixels, srcOff, srcScbn, srcY, srcOY)               \
    do {                                                                \
        srcP.vp = pixels;                                               \
        if (src32) {                                                    \
            srcP.ip += srcOff + ((srcY-srcOY) * srcScbn);               \
        } else {                                                        \
            srcP.bp += srcOff + ((srcY-srcOY) * srcScbn);               \
        }                                                               \
    } while (0)

#define GetPixelInc()                                                   \
    (src32 ? *srcP.ip++ : ((int) *srcP.bp++))

#define GetPixel(srcX)                                                  \
    (src32 ? srcP.ip[srcX] : ((int) srcP.bp[srcX]))

#define InputPixelInc(X)                                                \
    do {                                                                \
        if (src32) {                                                    \
            srcP.ip += X;                                               \
        } else {                                                        \
            srcP.bp += X;                                               \
        }                                                               \
    } while (0)

#define VerifyPixelRbnge(pixel, mbpsize)                                \
    do {                                                                \
        if (((unsigned int) pixel) >= mbpsize) {                        \
            SignblError(0, JAVAPKG "ArrbyIndexOutOfBoundsException", 0);\
            return SCALEFAILURE;                                        \
        }                                                               \
    } while (0)
