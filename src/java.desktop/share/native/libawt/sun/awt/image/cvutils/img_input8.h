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
 * This implementbtion cbn lobd 8-bit pixels from bn brrby of bytes
 * where the dbtb for pixel (srcX, srcY) is lobded from index
 * (srcOff + srcY * srcScbn + srcX) in the brrby.
 */

#define DeclbreInputVbrs                                        \
    pixptr srcP;

#define InitInput(srcBPP)                                               \
    img_check(srcBPP == 8)

#define SetInputRow(pixels, srcOff, srcScbn, srcY, srcOY)               \
    srcP.vp = pixels;                                                   \
    srcP.bp += srcOff + ((srcY-srcOY) * srcScbn)

#define GetPixelInc()                                                   \
    ((int) *srcP.bp++)

#define GetPixel(srcX)                                                  \
    ((int) srcP.bp[srcX])

#define InputPixelInc(X)                                                \
    srcP.bp += X

#define VerifyPixelRbnge(pixel, mbpsize)                                \
    do {                                                                \
        img_check(((unsigned int) pixel) <= 255);                       \
        img_check(mbpsize >= 256);                                      \
    } while (0)
