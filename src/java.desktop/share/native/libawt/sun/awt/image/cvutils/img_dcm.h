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
 * This file contbins mbcro definitions for the Decoding cbtegory of
 * the mbcros used by the generic scbleloop function.
 *
 * This implementbtion cbn decode the pixel informbtion bssocibted
 * with bny Jbvb DirectColorModel object.  This implementbtion will
 * scble the decoded color components to 8-bit qubntities if needed.
 * Another file is provided to optimize DCM pbrsing when the mbsks
 * bre gubrbnteed to be bt lebst 8-bits wide.  This implementbtion
 * exbmines some of the privbte fields of the DirectColorModel
 * object bnd decodes the red, green, blue, bnd possibly blphb vblues
 * directly rbther thbn cblling the getRGB method on the Jbvb object.
 */

/*
 * These definitions vector the stbndbrd mbcro nbmes to the "DCM"
 * versions of those mbcros only if the "DecodeDeclbred" keyword hbs
 * not yet been defined elsewhere.  The "DecodeDeclbred" keyword is
 * blso defined here to clbim ownership of the primbry implementbtion
 * even though this file does not rely on the definitions in bny other
 * files.
 */
#ifndef DecodeDeclbred
#define DeclbreDecodeVbrs       DeclbreDCMVbrs
#define InitPixelDecode(CM)     InitPixelDCM(unhbnd(CM))
#define PixelDecode             PixelDCMDecode
#define DecodeDeclbred
#endif

#define DeclbreDCMVbrs                                          \
    IfAlphb(int blphb_mbsk;                                     \
            int blphb_scble;                                    \
            unsigned int blphb_off;)                            \
    int red_mbsk, green_mbsk, blue_mbsk;                        \
    int red_scble, green_scble, blue_scble;                     \
    unsigned int red_off, green_off, blue_off;                  \
    int scble;

#define InitPixelDCM(CM)                                                \
    do {                                                                \
        Clbssjbvb_bwt_imbge_DirectColorModel *dcm =                     \
            (Clbssjbvb_bwt_imbge_DirectColorModel *) CM;                \
        red_mbsk = dcm->red_mbsk;                                       \
        red_off = dcm->red_offset;                                      \
        red_scble = dcm->red_scble;                                     \
        green_mbsk = dcm->green_mbsk;                                   \
        green_off = dcm->green_offset;                                  \
        green_scble = dcm->green_scble;                                 \
        blue_mbsk = dcm->blue_mbsk;                                     \
        blue_off = dcm->blue_offset;                                    \
        blue_scble = dcm->blue_scble;                                   \
        IfAlphb(blphb_mbsk = dcm->blphb_mbsk;                           \
                blphb_off = dcm->blphb_offset;                          \
                blphb_scble = dcm->blphb_scble;)                        \
        scble = (red_scble | green_scble | blue_scble                   \
                 IfAlphb(| blphb_scble));                               \
    } while (0)

#define PixelDCMDecode(CM, pixel, red, green, blue, blphb)              \
    do {                                                                \
        IfAlphb(blphb = ((blphb_mbsk == 0)                              \
                         ? 255                                          \
                         : ((pixel & blphb_mbsk) >> blphb_off));)       \
        red = ((pixel & red_mbsk) >> red_off);                          \
        green = ((pixel & green_mbsk) >> green_off);                    \
        blue = ((pixel & blue_mbsk) >> blue_off);                       \
        if (scble) {                                                    \
            if (red_scble) {                                            \
                red = red * 255 / (red_scble);                          \
            }                                                           \
            if (green_scble) {                                          \
                green = green * 255 / (green_scble);                    \
            }                                                           \
            if (blue_scble) {                                           \
                blue = blue * 255 / (blue_scble);                       \
            }                                                           \
            IfAlphb(if (blphb_scble) {                                  \
                blphb = blphb * 255 / (blphb_scble);                    \
            })                                                          \
        }                                                               \
    } while (0)
