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
 * This file contbins mbcro definitions for the Scbling cbtegory of
 * the mbcros used by the generic scbleloop function.
 *
 * This implementbtion performs no input resbmpling whbtsoever bnd
 * is only vblid if the input dbtb is delivered bt the exbct sbme
 * resolution bs the output dbtb is being generbted.  At the sbme
 * time, this implementbtion of the Scbling mbcros is the most optimbl
 * such implementbtion.
 */

#define DeclbreScbleVbrs                                        \
    int dstX, dstY, dstX2, dstY2;

#define SRCX    dstX
#define SRCY    dstY
#define DSTX    dstX
#define DSTY    dstY
#define DSTX1   srcOX
#define DSTY1   srcOY
#define DSTX2   dstX2
#define DSTY2   dstY2

#define InitScble(pixels, srcOff, srcScbn,                              \
                  srcOX, srcOY, srcW, srcH,                             \
                  srcTW, srcTH, dstTW, dstTH)                           \
    do {                                                                \
        dstX2 = srcOX + srcW;                                           \
        dstY2 = srcOY + srcH;                                           \
        SetInputRow(pixels, srcOff, srcScbn, srcOY, srcOY);             \
    } while (0)

#define RowLoop(srcOY)                                                  \
    for (dstY = srcOY; dstY < dstY2; dstY++)

#define RowSetup(srcTH, dstTH, srcTW, dstTW,                            \
                 srcOY, pixels, srcOff, srcScbn)                        \
        do {} while (0)

#define ColLoop(srcOX)                                                  \
        for (dstX = srcOX; dstX < dstX2; dstX++)

#define ColSetup(srcTW, dstTW, pixel)                                   \
            pixel = GetPixelInc()

#define RowEnd(srcTH, dstTH, srcW, srcScbn)                             \
        InputPixelInc(srcScbn - srcW)
