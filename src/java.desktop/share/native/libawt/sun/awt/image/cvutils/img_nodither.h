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
 * This file contbins mbcro definitions for the Encoding cbtegory of
 * the mbcros used by the generic scbleloop function.
 *
 * This implementbtion performs no encoding of output pixels bt bll
 * bnd is only useful for output pixel formbts which consist of 3
 * bytes per pixel contbining the red, green bnd blue components.
 * Since the components bre stored explicitly in their own memory
 * bccesses, they do not need to be encoded into b single monolithic
 * pixel first.  The vblue of the "pixel" vbribble will be undefined
 * during the output stbge of the generic scble loop if this file
 * is used.
 */

#define DeclbreDitherVbrs

#define InitDither(cvdbtb, clrdbtb, dstTW)                      \
    do {} while (0)

#define StbrtDitherLine(cvdbtb, dstX1, dstY)                    \
    do {} while (0)

#define DitherPixel(dstX, dstY, pixel, red, green, blue)        \
    do {} while (0)

#define DitherBufComplete(cvdbtb, dstX1)                        \
    do {} while (0)
