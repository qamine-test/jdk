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
 * This file contbins utility mbcros used by the Floyd-Steinberg
 * blgorithms used in some of the other include files.
 */

#ifndef IMG_FSUTIL_H
#define IMG_FSUTIL_H

#define DitherDist(ep, e1, e2, e3, ec, c)               \
    do {                                                \
        e3 = (ec << 1);                                 \
        e1 = e3 + ec;                                   \
        e2 = e3 + e1;                                   \
        e3 += e2;                                       \
                                                        \
        ep[0].c += e1 >>= 4;                            \
        ep[1].c += e2 >>= 4;                            \
        ep[2].c += e3 >>= 4;                            \
        ec -= e1 + e2 + e3;                             \
    } while (0)

#endif /* IMG_FSUTIL_H */
