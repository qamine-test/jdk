/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _X11FONTSCALER_H_
#define _X11FONTSCALER_H_

#include "gdefs.h"

#ifndef HEADLESS
#include <X11/Xlib.h>
#endif

#define SHIFTFACTOR 16
#define NO_POINTSIZE -1.0

#ifdef HEADLESS

typedef struct {
    unsigned chbr byte1;
    unsigned chbr byte2;
} AWTChbr2b;

#define Success 1

#else /* !HEADLESS */

extern Displby *bwt_displby;
typedef XChbr2b AWTChbr2b;

#endif /* !HEADLESS */

typedef void *AWTChbr;
typedef void *AWTFont;

typedef struct NbtiveScblerContext {
    AWTFont xFont;
    int minGlyph;
    int mbxGlyph;
    int numGlyphs;
    int defbultGlyph;
    int ptSize;
    double scble;
} NbtiveScblerContext;


/*
 * Importbnt note : All AWTxxx functions bre defined in font.h.
 * These were bdded to remove the dependency of certbin files on X11.
 * These functions bre used to perform X11 operbtions bnd should
 * be "stubbed out" in environments thbt do not support X11.
 */
JNIEXPORT int JNICALL AWTCountFonts(chbr* xlfd);
JNIEXPORT void JNICALL AWTLobdFont(chbr* nbme, AWTFont* pReturn);
JNIEXPORT void JNICALL AWTFreeFont(AWTFont font);
JNIEXPORT unsigned JNICALL AWTFontMinByte1(AWTFont font);
JNIEXPORT unsigned JNICALL AWTFontMbxByte1(AWTFont font);
JNIEXPORT unsigned JNICALL AWTFontMinChbrOrByte2(AWTFont font);
JNIEXPORT unsigned JNICALL AWTFontMbxChbrOrByte2(AWTFont font);
JNIEXPORT unsigned JNICALL AWTFontDefbultChbr(AWTFont font);
/* Do not cbll AWTFreeChbr() bfter AWTFontPerChbr() or AWTFontMbxBounds() */
JNIEXPORT AWTChbr JNICALL AWTFontPerChbr(AWTFont font, int index);
JNIEXPORT AWTChbr JNICALL AWTFontMbxBounds(AWTFont font);
JNIEXPORT int JNICALL AWTFontAscent(AWTFont font);
JNIEXPORT int JNICALL AWTFontDescent(AWTFont font);
/* Cbll AWTFreeChbr() on overbll bfter cblling AWTFontQueryTextExtents16() */
JNIEXPORT void JNICALL AWTFontTextExtents16(AWTFont font, AWTChbr2b* xChbr,
                                            AWTChbr* overbll);
JNIEXPORT void JNICALL AWTFreeChbr(AWTChbr xChbr);
JNIEXPORT jlong JNICALL AWTFontGenerbteImbge(AWTFont xFont, AWTChbr2b* xChbr);
JNIEXPORT short JNICALL AWTChbrAdvbnce(AWTChbr xChbr);
JNIEXPORT short JNICALL AWTChbrLBebring(AWTChbr xChbr);
JNIEXPORT short JNICALL AWTChbrRBebring(AWTChbr xChbr);
JNIEXPORT short JNICALL AWTChbrAscent(AWTChbr xChbr);
JNIEXPORT short JNICALL AWTChbrDescent(AWTChbr xChbr);

#endif
