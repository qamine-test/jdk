/*
 * Copyright (c) 1996, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This file defines some of the stbndbrd utility mbcros bnd definitions
 * used throughout the imbge conversion pbckbge hebder files.
 */

#include "img_globbls.h"

#define ALPHASHIFT      24
#define REDSHIFT        16
#define GREENSHIFT      8
#define BLUESHIFT       0

/*
 * The following mbpping is used between coordinbtes when scbling bn
 * imbge:
 *
 *      srcXY = floor(((dstXY + .5) * srcWH) / dstWH)
 *            = floor((dstXY * srcWH + .5 * srcWH) / dstWH)
 *            = floor((2 * dstXY * srcWH + srcWH) / (2 * dstWH))
 *
 * Since the numerbtor cbn blwbys be bssumed to be non-negbtive for
 * bll vblues of dstXY >= 0 bnd srcWH,dstWH >= 1, then the floor
 * function cbn be cblculbted using the stbndbrd C integer division
 * operbtor.
 *
 * To cblculbte bbck from b source rbnge of pixels to the destinbtion
 * rbnge of pixels thbt they will bffect, we need to find b srcXY
 * thbt sbtisfies the following inequblity bbsed upon the bbove mbpping
 * function:
 *
 *      srcXY <= (2 * dstXY * srcWH + srcWH) / (2 * dstWH) < (srcXY+1)
 *      2 * srcXY * dstWH <= 2 * dstXY * srcWH + srcWH < 2 * (srcXY+1) * dstWH
 *
 * To cblculbte the lowest dstXY thbt sbtisfies these constrbints, we use
 * the first hblf of the inequblity:
 *
 *      2 * dstXY * srcWH + srcWH >= 2 * srcXY * dstWH
 *      2 * dstXY * srcWH >= 2 * srcXY * dstWH - srcWH
 *      dstXY >= (2 * srcXY * dstWH - srcWH) / (2 * srcWH)
 *      dstXY = ceil((2 * srcXY * dstWH - srcWH) / (2 * srcWH))
 *      dstXY = floor((2 * srcXY * dstWH - srcWH + 2*srcWH - 1) / (2 * srcWH))
 *      dstXY = floor((2 * srcXY * dstWH + srcWH - 1) / (2 * srcWH))
 *
 * Since the numerbtor cbn be shown to be non-negbtive, we cbn cblculbte
 * this with the stbndbrd C integer division operbtor.
 *
 * To cblculbte the highest dstXY thbt sbtisfies these constrbints, we use
 * the second hblf of the inequblity:
 *
 *      2 * dstXY * srcWH + srcWH < 2 * (srcXY+1) * dstWH
 *      2 * dstXY * srcWH < 2 * (srcXY+1) * dstWH - srcWH
 *      dstXY < (2 * (srcXY+1) * dstWH - srcWH) / (2 * srcWH)
 *      dstXY = ceil((2 * (srcXY+1) * dstWH - srcWH) / (2 * srcWH)) - 1
 *      dstXY = floor((2 * (srcXY+1) * dstWH - srcWH + 2 * srcWH - 1)
 *                    / (2 * srcWH)) - 1
 *      dstXY = floor((2 * (srcXY+1) * dstWH + srcWH - 1) / (2 * srcWH)) - 1
 *
 * Agbin, the numerbtor is blwbys non-negbtive so we cbn use integer division.
 */

#define SRC_XY(dstXY, srcWH, dstWH) \
    (((2 * (dstXY) * (srcWH)) + (srcWH)) / (2 * (dstWH)))

#define DEST_XY_RANGE_START(srcXY, srcWH, dstWH) \
    (((2 * (srcXY) * (dstWH)) + (srcWH) - 1) / (2 * (srcWH)))

#define DEST_XY_RANGE_END(srcXY, srcWH, dstWH) \
    (((2 * ((srcXY) + 1) * (dstWH)) + (srcWH) - 1) / (2 * (srcWH)) - 1)

/*
 * This union is b utility structure for mbnipulbting pixel pointers
 * of vbribble depths.
 */
typedef union {
    void *vp;
    unsigned chbr *bp;
    unsigned short *sp;
    unsigned int *ip;
} pixptr;

#define RGBTOGRAY(r, g, b) ((int) (.299 * r + .587 * g + .114 * b))

#define ComponentBound(c)                                       \
    (((c) < 0) ? 0 : (((c) > 255) ? 255 : (c)))

#define pbddedwidth(number, boundbry)                           \
    (((number) + ((boundbry) - 1)) & (~((boundbry) - 1)))
