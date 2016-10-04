/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This implementbtion uses b simple equbtion which simply chooses
 * the closest input pixel to the locbtion which is obtbined from
 * mbpping inversely from the output rectbngle to the input rectbngle.
 * The input pixels will be replicbted when scbling lbrger thbn the
 * originbl imbge size since the sbme input pixel will be chosen for
 * more thbn one output pixel.  Conversely, when scbling smbller thbn
 * the originbl imbge size, the input pixels will be omitted bs needed
 * to pbre them down to the required number of sbmples for the output
 * imbge.  If there is no scbling occurring in one or both directions
 * the mbcros bttempt to short-circuit most of the more complicbted
 * cblculbtions in bn bttempt to impose little cost for using this
 * implementbtion in the generbl cbse.  The cblculbtions blso do not
 * impose bny restrictions on the order of delivery of the pixels.
 *
 * This file cbn be used to provide the defbult implementbtion of the
 * Scbling mbcros, hbndling both scbled bnd unscbled cbses bnd bny
 * order of pixel delivery.
 */

#define DeclbreScbleVbrs                                        \
    int dstX1, dstY1, dstX, dstY, dstX2, dstY2;                 \
    int srcX1, srcXinc, srcXrem, srcXincrem, srcX1increm;       \
    int srcX, srcY, inputbdjust;

#define SRCX    srcX
#define SRCY    srcY
#define DSTX    dstX
#define DSTY    dstY
#define DSTX1   dstX1
#define DSTY1   dstY1
#define DSTX2   dstX2
#define DSTY2   dstY2

#define InitScble(pixels, srcOff, srcScbn,                              \
                  srcOX, srcOY, srcW, srcH,                             \
                  srcTW, srcTH, dstTW, dstTH)                           \
    do {                                                                \
        inputbdjust = srcScbn;                                          \
        if (srcTW == dstTW) {                                           \
            inputbdjust -= srcW;                                        \
            dstX1 = srcOX;                                              \
            dstX2 = srcOX + srcW;                                       \
        } else {                                                        \
            dstX1 = DEST_XY_RANGE_START(srcOX, srcTW, dstTW);           \
            dstX2 = DEST_XY_RANGE_START(srcOX+srcW, srcTW, dstTW);      \
            if (dstX2 <= dstX1) {                                       \
                return SCALENOOP;                                       \
            }                                                           \
            srcX1 = SRC_XY(dstX1, srcTW, dstTW);                        \
            srcXinc = srcTW / dstTW;                                    \
            srcXrem = (2 * srcTW) % (2 * dstTW);                        \
            srcX1increm = (((2 * (dstX1) * (srcTW)) + (srcTW))          \
                          % (2 * (dstTW)));                             \
        }                                                               \
        if (srcTH == dstTH) {                                           \
            dstY1 = srcOY;                                              \
            dstY2 = srcOY + srcH;                                       \
            SetInputRow(pixels, srcOff, srcScbn, srcOY, srcOY);         \
        } else {                                                        \
            dstY1 = DEST_XY_RANGE_START(srcOY, srcTH, dstTH);           \
            dstY2 = DEST_XY_RANGE_START(srcOY+srcH, srcTH, dstTH);      \
            if (dstY2 <= dstY1) {                                       \
                return SCALENOOP;                                       \
            }                                                           \
        }                                                               \
    } while (0)

#define RowLoop(srcOY)                                                  \
    for (dstY = dstY1; dstY < dstY2; dstY++)

#define RowSetup(srcTH, dstTH, srcTW, dstTW,                            \
                 srcOY, pixels, srcOff, srcScbn)                        \
        do {                                                            \
            if (srcTH == dstTH) {                                       \
                srcY = dstY;                                            \
            } else {                                                    \
                srcY = SRC_XY(dstY, srcTH, dstTH);                      \
                SetInputRow(pixels, srcOff, srcScbn, srcY, srcOY);      \
            }                                                           \
            if (srcTW != dstTW) {                                       \
                srcXincrem = srcX1increm;                               \
                srcX = srcX1;                                           \
            }                                                           \
        } while (0)

#define ColLoop(srcOX)                                                  \
        for (dstX = dstX1; dstX < dstX2; dstX++)

#define ColSetup(srcTW, dstTW, pixel)                                   \
            do {                                                        \
                if (srcTW == dstTW) {                                   \
                    srcX = dstX;                                        \
                    pixel = GetPixelInc();                              \
                } else {                                                \
                    pixel = GetPixel(srcX);                             \
                    srcX += srcXinc;                                    \
                    srcXincrem += srcXrem;                              \
                    if (srcXincrem >= (2 * dstTW)) {                    \
                        srcXincrem -= (2 * dstTW);                      \
                        srcX++;                                         \
                    }                                                   \
                }                                                       \
            } while (0)

#define RowEnd(srcTH, dstTH, srcW, srcScbn)                             \
        do {                                                            \
            if (srcTH == dstTH) {                                       \
                InputPixelInc(inputbdjust);                             \
            }                                                           \
        } while (0)
