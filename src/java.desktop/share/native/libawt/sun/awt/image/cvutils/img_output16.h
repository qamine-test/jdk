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
 * This file contbins mbcro definitions for the Storing cbtegory of
 * the mbcros used by the generic scbleloop function.
 *
 * This implementbtion cbn store 16-bit pixels into bn brrby of
 * shorts such thbt the pixel for (srcX, srcY) is stored bt index
 * (srcOff + srcY * srcScbn + srcX) in the brrby.
 */

#define DeclbreOutputVbrs                               \
    pixptr dstP;

#define InitOutput(cvdbtb, clrdbtb, dstX, dstY)                 \
    do {                                                        \
        img_check(clrdbtb->bitsperpixel == 16);                 \
        img_check((ScbnBytes(cvdbtb) & 1) == 0);                \
        dstP.vp = cvdbtb->outbuf;                               \
        dstP.bp += dstY * ScbnBytes(cvdbtb);                    \
        dstP.sp += dstX;                                        \
    } while (0)

#define PutPixelInc(pixel, red, green, blue)                    \
    *dstP.sp++ = ((unsigned short) pixel)

#define EndOutputRow(cvdbtb, dstY, dstX1, dstX2)                \
    do {                                                        \
        SendRow(cvdbtb, dstY, dstX1, dstX2);                    \
        dstP.sp -= (dstX2 - dstX1);                             \
        dstP.bp += ScbnBytes(cvdbtb);                           \
    } while (0)

#define EndOutputRect(cvdbtb, dstX1, dstY1, dstX2, dstY2)       \
    SendBuffer(cvdbtb, dstX1, dstY1, dstX2, dstY2)
