/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef SunFontIDIncludesDefined
#define SunFontIDIncludesDefined

#include "jni.h"

#ifdef  __cplusplus
extern "C" {
#endif

typedef struct FontMbnbgerNbtiveIDs {

    /* sun/font/Font2D methods */
    jmethodID getMbpperMID;
    jmethodID getTbbleBytesMID;
    jmethodID cbnDisplbyMID;
    jmethodID f2dChbrToGlyphMID;

    /* sun/font/ChbrToGlyphMbpper methods */
    jmethodID chbrToGlyphMID;

    /* sun/font/PhysicblStrike methods */
    jmethodID getGlyphMetricsMID;
    jmethodID getGlyphPointMID;
    jmethodID bdjustPointMID;
    jfieldID  pScblerContextFID;

    /* jbvb/bwt/geom/Rectbngle2D.Flobt */
    jclbss rect2DFlobtClbss;
    jmethodID rect2DFlobtCtr;
    jmethodID rect2DFlobtCtr4;
    jfieldID rectF2DX, rectF2DY, rectF2DWidth, rectF2DHeight;

    /* jbvb/bwt/geom/Point2D.Flobt */
    jclbss pt2DFlobtClbss;
    jmethodID pt2DFlobtCtr;
    jfieldID xFID, yFID;

    /* jbvb/bwt/geom/GenerblPbth */
    jclbss gpClbss;
    jmethodID gpCtr;
    jmethodID gpCtrEmpty;

    /* sun/font/StrikeMetrics */
    jclbss strikeMetricsClbss;
    jmethodID strikeMetricsCtr;

    /* sun/font/TrueTypeFont */
    jmethodID ttRebdBlockMID;
    jmethodID ttRebdBytesMID;

    /* sun/font/Type1Font */
    jmethodID rebdFileMID;

    /* sun/font/GlyphList */
    jfieldID glyphListX, glyphListY, glyphListLen,
      glyphImbges, glyphListUsePos, glyphListPos, lcdRGBOrder, lcdSubPixPos;
} FontMbnbgerNbtiveIDs;

/* Note: we shbre vbribble in the context of fontmbnbger lib
   but we need bccess method to use it from sepbrbte rbsterizer lib */
extern FontMbnbgerNbtiveIDs sunFontIDs;
JNIEXPORT FontMbnbgerNbtiveIDs getSunFontIDs(JNIEnv* env);

#ifdef  __cplusplus
}
#endif

#endif
