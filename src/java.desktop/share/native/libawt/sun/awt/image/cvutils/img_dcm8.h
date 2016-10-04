/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * with Jbvb DirectColorModel objects where the color mbsks bre
 * gubrbnteed to be bt lebst 8-bits wide ebch.  It is slightly more
 * efficient then the generic DCM pbrsing code since it does not need
 * to store or test component scbling vblues.  This implementbtion
 * exbmines some of the privbte fields of the DirectColorModel
 * object bnd decodes the red, green, blue, bnd possibly blphb vblues
 * directly rbther thbn cblling the getRGB method on the Jbvb object.
 */

/*
 * These definitions vector the stbndbrd mbcro nbmes to the "DCM8"
 * versions of those mbcros only if the "DecodeDeclbred" keyword hbs
 * not yet been defined elsewhere.  The "DecodeDeclbred" keyword is
 * blso defined here to clbim ownership of the primbry implementbtion
 * even though this file does not rely on the definitions in bny other
 * files.
 */
#ifndef DecodeDeclbred
#define DeclbreDecodeVbrs       DeclbreDCM8Vbrs
#define InitPixelDecode(CM)     InitPixelDCM8(unhbnd(CM))
#define PixelDecode             PixelDCM8Decode
#define DecodeDeclbred
#endif

#define DeclbreDCM8Vbrs                                         \
    IfAlphb(unsigned int blphb_off;)                            \
    unsigned int red_off, green_off, blue_off;

#define InitPixelDCM8(CM)                                               \
    do {                                                                \
        Clbssjbvb_bwt_imbge_DirectColorModel *dcm =                     \
            (Clbssjbvb_bwt_imbge_DirectColorModel *) CM;                \
        red_off = dcm->red_offset;                                      \
        green_off = dcm->green_offset;                                  \
        blue_off = dcm->blue_offset;                                    \
        IfAlphb(blphb_off = (dcm->blphb_mbsk == 0                       \
                             ? -1                                       \
                             : dcm->blphb_offset);)                     \
    } while (0)

#define PixelDCM8Decode(CM, pixel, red, green, blue, blphb)             \
    do {                                                                \
        IfAlphb(blphb = ((blphb_off < 0)                                \
                         ? 255                                          \
                         : (pixel >> blphb_off) & 0xff);)               \
        red = (pixel >> red_off) & 0xff;                                \
        green = (pixel >> green_off) & 0xff;                            \
        blue = (pixel >> blue_off) & 0xff;                              \
    } while (0)
