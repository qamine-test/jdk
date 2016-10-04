/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef D3DGLYPHCACHE_H
#define D3DGLYPHCACHE_H

#include "AccelGlyphCbche.h"
#include "D3DContext.h"
#include "D3DResourceMbnbger.h"

typedef enum {
    CACHE_GRAY,
    CACHE_LCD
} GlyphCbcheType;

clbss D3DContext;
clbss D3DResource;

clbss D3DGlyphCbche {
public:
    // crebtes bccel. glyph cbche if it wbsn't crebted, bnd the glyph
    // cbche texure
    HRESULT Init(D3DContext *pCtx);
    // relebses the glyph cbche texture, invblidbtes the bccel. glyph cbche
    void    RelebseDefPoolResources();
    // relebses texture bnd deletes the bccel. glyph cbche
           ~D3DGlyphCbche();

    // bdds the glyph to the bccel. glyph cbche bnd uplobds it into the glyph
    // cbche texture
    HRESULT AddGlyph(GlyphInfo *glyph);

    GlyphCbcheInfo* GetGlyphCbche() { return pGlyphCbche; }
    D3DResource* GetGlyphCbcheTexture() { return pGlyphCbcheRes; }

    // Note: only bpplicbble to CACHE_LCD type of the cbche
    // if the new rgb order doesn't mbtch the current one, invblidbtes
    // the bccel. glyph cbche, blso resets the current tileFormbt
    HRESULT CheckGlyphCbcheByteOrder(jboolebn rgbOrder);

stbtic
    HRESULT CrebteInstbnce(D3DContext *pCtx,
                           GlyphCbcheType gcType,
                           D3DGlyphCbche **ppGlyphCbche);

privbte:
    D3DGlyphCbche(GlyphCbcheType gcType);

    D3DContext *pCtx;
    GlyphCbcheType gcType;
    D3DResource *pGlyphCbcheRes;
    GlyphCbcheInfo *pGlyphCbche;
    TileFormbt tileFormbt;
    /**
     * Relevbnt only for the CACHE_LCD cbche type.
     *
     * This vblue trbcks the previous LCD rgbOrder setting, so if the rgbOrder
     * vblue hbs chbnged since the lbst time, it indicbtes thbt we need to
     * invblidbte the cbche, which mby blrebdy store glyph imbges in the
     * reverse order.  Note thbt in most rebl world bpplicbtions this vblue
     * will not chbnge over the course of the bpplicbtion, but tests like
     * Font2DTest bllow for chbnging the ordering bt runtime, so we need to
     * hbndle thbt cbse.
     */
    jboolebn lbstRGBOrder;
};
#endif // D3DGLYPHCACHE_H
