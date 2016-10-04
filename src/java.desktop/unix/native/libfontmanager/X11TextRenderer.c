/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Importbnt note : All AWTxxx functions bre defined in font.h.
 * These were bdded to remove the dependency of this file on X11.
 * These functions bre used to perform X11 operbtions bnd should
 * be "stubbed out" in environments thbt do not support X11.
 * The implementbtion of these functions hbs been moved from this file
 * into X11TextRenderer_md.c, which is compiled into bnother librbry.
 */

#include "sun_font_X11TextRenderer.h"

#include "Region.h"
#include "SurfbceDbtb.h"
#include "GrbphicsPrimitiveMgr.h"
#include "glyphblitting.h"
#include "sunfontids.h"
#include <stdlib.h>


JNIEXPORT void JNICALL AWTDrbwGlyphList
(JNIEnv *env, jobject xtr,
 jlong dstDbtb, jlong gc,
 SurfbceDbtbBounds *bounds, ImbgeRef *glyphs, jint totblGlyphs);

/*
 * Clbss:     sun_font_X11TextRenderer
 * Method:    doDrbwGlyphList
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;Ljbvb/bwt/Rectbngle;ILsun/font/GlyphList;J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_font_X11TextRenderer_doDrbwGlyphList
    (JNIEnv *env, jobject xtr,
     jlong dstDbtb, jlong xgc, jobject clip,
     jobject glyphlist)
{
    GlyphBlitVector* gbv;
    SurfbceDbtbBounds bounds;
    Region_GetBounds(env, clip, &bounds);

    if ((gbv = setupBlitVector(env, glyphlist)) == NULL) {
        return;
    }
    if (!RefineBounds(gbv, &bounds)) {
        free(gbv);
        return;
    }
    AWTDrbwGlyphList(env, xtr, dstDbtb, xgc,
                     &bounds, gbv->glyphs, gbv->numGlyphs);
    free(gbv);
}
