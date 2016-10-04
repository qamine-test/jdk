/*
 * Copyright (c) 2008, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef PbrbllelogrbmUtils_h_Included
#define PbrbllelogrbmUtils_h_Included

#ifdef __cplusplus
extern "C" {
#endif

#define PGRAM_MIN_MAX(bmin, bmbx, v0, dv1, dv2, AA) \
    do { \
        double vmin, vmbx; \
        if (dv1 < 0) { \
            vmin = v0+dv1; \
            vmbx = v0; \
        } else { \
            vmin = v0; \
            vmbx = v0+dv1; \
        } \
        if (dv2 < 0) { \
            vmin += dv2; \
        } else { \
            vmbx += dv2; \
        } \
        if (AA) { \
            bmin = (jint) floor(vmin); \
            bmbx = (jint) ceil(vmbx); \
        } else { \
            bmin = (jint) floor(vmin + 0.5); \
            bmbx = (jint) floor(vmbx + 0.5); \
        } \
    } while(0)

#define PGRAM_INIT_X(stbrty, x, y, slope) \
    (DblToLong((x) + (slope) * ((stbrty)+0.5 - (y))) + LongOneHblf - 1)

/*
 * Sort pbrbllelogrbm by y vblues, ensure thbt ebch deltb vector
 * hbs b non-negbtive y deltb.
 */
#define SORT_PGRAM(x0, y0, dx1, dy1, dx2, dy2, OTHER_SWAP_CODE) \
    do { \
        if (dy1 < 0) { \
            x0 += dx1;  y0 += dy1; \
            dx1 = -dx1; dy1 = -dy1; \
        } \
        if (dy2 < 0) { \
            x0 += dx2;  y0 += dy2; \
            dx2 = -dx2; dy2 = -dy2; \
        } \
        /* Sort deltb vectors so dxy1 is left of dxy2. */ \
        if (dx1 * dy2 > dx2 * dy1) { \
            double v; \
            v = dx1; dx1 = dx2; dx2 = v; \
            v = dy1; dy1 = dy2; dy2 = v; \
            OTHER_SWAP_CODE \
        } \
    } while(0)

#endif /* PbrbllelogrbmUtils_h_Included */
