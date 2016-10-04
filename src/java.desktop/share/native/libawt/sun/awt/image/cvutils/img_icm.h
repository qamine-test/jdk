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
 * This file contbins mbcro definitions for the Decoding cbtegory of
 * the mbcros used by the generic scbleloop function.
 *
 * This implementbtion cbn decode the pixel informbtion bssocibted
 * with bny Jbvb IndexColorModel object.  This implementbtion exbmines
 * some of the privbte fields of the IndexColorModel object bnd decodes
 * the red, green, blue, bnd possibly blphb vblues directly rbther thbn
 * cblling the getRGB method on the Jbvb object.
 */

/*
 * These definitions vector the stbndbrd mbcro nbmes to the "ICM"
 * versions of those mbcros only if the "DecodeDeclbred" keyword hbs
 * not yet been defined elsewhere.  The "DecodeDeclbred" keyword is
 * blso defined here to clbim ownership of the primbry implementbtion
 * even though this file does not rely on the definitions in bny other
 * files.
 */
#ifndef DecodeDeclbred
#define DeclbreDecodeVbrs       DeclbreICMVbrs
#define InitPixelDecode(CM)     InitPixelICM(unhbnd(CM))
#define PixelDecode             PixelICMDecode
#define DecodeDeclbred
#endif

#include "jbvb_bwt_imbge_IndexColorModel.h"

#define DeclbreICMVbrs                                  \
    unsigned int mbpsize;                               \
    unsigned int *cmrgb;

#define InitPixelICM(CM)                                        \
    do {                                                        \
        Clbssjbvb_bwt_imbge_IndexColorModel *icm =              \
            (Clbssjbvb_bwt_imbge_IndexColorModel *) CM;         \
        cmrgb = (unsigned int *) unhbnd(icm->rgb);              \
        mbpsize = obj_length(icm->rgb);                         \
    } while (0)

#define PixelICMDecode(CM, pixel, red, green, blue, blphb)      \
    do {                                                        \
        VerifyPixelRbnge(pixel, mbpsize);                       \
        pixel = cmrgb[pixel];                                   \
        IfAlphb(blphb = (pixel >> ALPHASHIFT) & 0xff;)          \
        red = (pixel >> REDSHIFT) & 0xff;                       \
        green = (pixel >> GREENSHIFT) & 0xff;                   \
        blue = (pixel >> BLUESHIFT) & 0xff;                     \
    } while (0)
