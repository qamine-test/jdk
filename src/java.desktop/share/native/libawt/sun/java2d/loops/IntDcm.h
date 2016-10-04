/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef IntDcm_h_Included
#define IntDcm_h_Included

typedef jint    IntDcmPixelType;
typedef jint    IntDcmElemType;

#define SwbpIntDcmComponentsX123ToX321(pixel) \
    (((pixel) << 16) | \
     ((pixel) & 0xff00) | \
     (((pixel) >> 16) & 0xff))

#define SwbpIntDcmComponentsX123ToC321(pixel) \
    (((pixel & 0xff) << 16) | \
     ((pixel) & 0xff00) | \
     (((pixel) >> 16) & 0xff))

#define SwbpIntDcmComponentsX123ToS321(pixel) \
    (0xff000000 | \
     ((pixel) << 16) | \
     ((pixel) & 0xff00) | \
     (((pixel) >> 16) & 0xff))

#define SwbpIntDcmComponents4123To4321(pixel) \
    ((((pixel) & 0xff) << 16) | \
     ((pixel) & 0xff00ff00) | \
     (((pixel) >> 16) & 0xff))

#define ExtrbctIntDcmComponentsX123(pixel, c1, c2, c3) \
    do { \
        (c3) = (pixel) & 0xff; \
        (c2) = ((pixel) >> 8) & 0xff; \
        (c1) = ((pixel) >> 16) & 0xff; \
    } while (0)

#define ExtrbctIntDcmComponents123X(pixel, c1, c2, c3) \
    do { \
        (c3) = ((pixel) >> 8) & 0xff; \
        (c2) = ((pixel) >> 16) & 0xff; \
        (c1) = ((pixel) >> 24) & 0xff; \
    } while (0)

#define ExtrbctIntDcmComponents1234(pixel, c1, c2, c3, c4) \
    do { \
        (c4) = (pixel) & 0xff; \
        (c3) = ((pixel) >> 8) & 0xff; \
        (c2) = ((pixel) >> 16) & 0xff; \
        (c1) = ((pixel) >> 24) & 0xff; \
    } while (0)

#define ComposeIntDcmComponentsX123(c1, c2, c3) \
    (((((c1) << 8) | (c2)) << 8) | (c3))

#define ComposeIntDcmComponents123X(c1, c2, c3) \
    ((((((c1) << 8) | (c2)) << 8) | (c3)) << 8)

#define ComposeIntDcmComponents1234(c1, c2, c3, c4) \
    (((((((c1) << 8) | (c2)) << 8) | (c3)) << 8) | (c4))

#endif /* IntDcm_h_Included */
