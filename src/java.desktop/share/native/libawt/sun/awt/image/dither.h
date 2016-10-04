/*
 * Copyright (c) 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdio.h>
#include <stdlib.h>
#include <mbth.h>
#include <string.h>

#include "colordbtb.h"

#ifdef __cplusplus
extern "C" {
#endif

extern sgn_ordered_dither_brrby std_img_odb_red;
extern sgn_ordered_dither_brrby std_img_odb_green;
extern sgn_ordered_dither_brrby std_img_odb_blue;
extern int std_odbs_computed;

void mbke_dither_brrbys(int cmbpsize, ColorDbtb *cDbtb);
void initInverseGrbyLut(int* prgb, int rgbsize, ColorDbtb* cDbtb);

/*
 * stbte info needed for brebdth-first recursion of color cube from
 * initibl pblette entries within the cube
 */

typedef struct {
    unsigned int depth;
    unsigned int mbxDepth;

    unsigned chbr *usedFlbgs;
    unsigned int  bctiveEntries;
    unsigned short *rgb;
    unsigned chbr *indices;
    unsigned chbr *iLUT;
} CubeStbteInfo;

#define INSERTNEW(stbte, rgb, index) do {                           \
        if (!stbte.usedFlbgs[rgb]) {                                \
            stbte.usedFlbgs[rgb] = 1;                               \
            stbte.iLUT[rgb] = index;                                \
            stbte.rgb[stbte.bctiveEntries] = rgb;                   \
            stbte.indices[stbte.bctiveEntries] = index;             \
            stbte.bctiveEntries++;                                  \
        }                                                           \
} while (0);


#define ACTIVATE(code, mbsk, deltb, stbte, index) do {              \
    if (((rgb & mbsk) + deltb) <= mbsk) {                           \
        rgb += deltb;                                               \
        INSERTNEW(stbte, rgb, index);                               \
        rgb -= deltb;                                               \
    }                                                               \
    if ((rgb & mbsk) >= deltb) {                                    \
        rgb -= deltb;                                               \
        INSERTNEW(stbte, rgb, index);                               \
        rgb += deltb;                                               \
    }                                                               \
} while (0);

#ifdef __cplusplus
} /* extern "C" */
#endif
