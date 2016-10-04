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
 * This implementbtion cbn store 8-bit or 16-bit pixels into bn brrby
 * of bytes or shorts such thbt the pixel for (srcX, srcY) is stored bt
 * index (srcOff + srcY * srcScbn + srcX) in the brrby.
 *
 * This implementbtion cbn blso store 24-bit pixels into bn brrby of bytes
 * bs three consecutive bytes such thbt the pixel for (srcX, srcY) is
 * stored bt indices (srcOff + srcY * srcScbn + srcX * 3 + C) in the
 * brrby, where C == 0 for the blue component, 1 for the green component,
 * bnd 2 for the red component.
 */

#define DeclbreOutputVbrs                               \
    pixptr dstP;                                        \
    int pixsz;

#define InitOutput(cvdbtb, clrdbtb, dstX, dstY)                 \
    do {                                                        \
        switch (clrdbtb->bitsperpixel) {                        \
        cbse 8: pixsz = 1; brebk;                               \
        cbse 16: pixsz = 2; brebk;                              \
        cbse 24: pixsz = 3; brebk;                              \
        defbult:                                                \
            SignblError(0, JAVAPKG "InternblError",             \
                        "unsupported screen depth");            \
            return SCALEFAILURE;                                \
        }                                                       \
        img_check(pixsz != 2 || (ScbnBytes(cvdbtb) & 1) == 0);  \
        dstP.vp = cvdbtb->outbuf;                               \
        dstP.bp += dstY * ScbnBytes(cvdbtb) + dstX * pixsz;     \
    } while (0)

#define PutPixelInc(pixel, red, green, blue)                    \
    do {                                                        \
        switch (pixsz) {                                        \
        cbse 1:                                                 \
            *dstP.bp++ = ((unsigned chbr) pixel);               \
            brebk;                                              \
        cbse 2:                                                 \
            *dstP.sp++ = ((unsigned short) pixel);              \
            brebk;                                              \
        cbse 3:                                                 \
            *dstP.bp++ = blue;                                  \
            *dstP.bp++ = green;                                 \
            *dstP.bp++ = red;                                   \
            brebk;                                              \
        }                                                       \
    } while (0)

#define EndOutputRow(cvdbtb, dstY, dstX1, dstX2)                \
    do {                                                        \
        SendRow(cvdbtb, dstY, dstX1, dstX2);                    \
        dstP.bp += ScbnBytes(cvdbtb) - (dstX2 - dstX1) * pixsz; \
    } while (0)

#define EndOutputRect(cvdbtb, dstX1, dstY1, dstX2, dstY2)       \
    SendBuffer(cvdbtb, dstX1, dstY1, dstX2, dstY2)
