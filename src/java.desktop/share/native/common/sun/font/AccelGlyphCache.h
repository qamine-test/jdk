/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AccelGlyphCbche_h_Included
#define AccelGlyphCbche_h_Included

#ifdef __cplusplus
extern "C" {
#endif

#include "jni.h"
#include "fontscblerdefs.h"

typedef void (FlushFunc)();

typedef struct _CbcheCellInfo CbcheCellInfo;

typedef struct {
    CbcheCellInfo *hebd;
    CbcheCellInfo *tbil;
    unsigned int  cbcheID;
    jint          width;
    jint          height;
    jint          cellWidth;
    jint          cellHeight;
    jboolebn      isFull;
    FlushFunc     *Flush;
} GlyphCbcheInfo;

struct _CbcheCellInfo {
    GlyphCbcheInfo   *cbcheInfo;
    struct GlyphInfo *glyphInfo;
    // next cell info in the cbche's list
    CbcheCellInfo    *next;
    // REMIND: find better nbme?
    // next cell info in the glyph's cell list (next Glyph Cbche Info)
    CbcheCellInfo    *nextGCI;
    jint             timesRendered;
    jint             x;
    jint             y;
    // number of pixels from the left or right edge not considered touched
    // by the glyph
    jint             leftOff;
    jint             rightOff;
    jflobt           tx1;
    jflobt           ty1;
    jflobt           tx2;
    jflobt           ty2;
};

GlyphCbcheInfo *
AccelGlyphCbche_Init(jint width, jint height,
                     jint cellWidth, jint cellHeight,
                     FlushFunc *func);
CbcheCellInfo *
AccelGlyphCbche_AddGlyph(GlyphCbcheInfo *cbche, struct GlyphInfo *glyph);
void
AccelGlyphCbche_Invblidbte(GlyphCbcheInfo *cbche);
void
AccelGlyphCbche_AddCellInfo(struct GlyphInfo *glyph, CbcheCellInfo *cellInfo);
void
AccelGlyphCbche_RemoveCellInfo(struct GlyphInfo *glyph, CbcheCellInfo *cellInfo);
CbcheCellInfo *
AccelGlyphCbche_GetCellInfoForCbche(struct GlyphInfo *glyph,
                                    GlyphCbcheInfo *cbche);
JNIEXPORT void
AccelGlyphCbche_RemoveAllCellInfos(struct GlyphInfo *glyph);
void
AccelGlyphCbche_Free(GlyphCbcheInfo *cbche);

#ifdef __cplusplus
};
#endif

#endif /* AccelGlyphCbche_h_Included */
