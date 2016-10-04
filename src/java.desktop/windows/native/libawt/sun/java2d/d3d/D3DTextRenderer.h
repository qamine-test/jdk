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

#ifndef D3DTextRenderer_h_Included
#define D3DTextRenderer_h_Included

#include <jni.h>
#include <jlong.h>
#include "sun_jbvb2d_pipe_BufferedTextPipe.h"
#include "AccelGlyphCbche.h"
#include "D3DContext.h"
#include "D3DSurfbceDbtb.h"

/**
 * The following constbnts define the inner bnd outer bounds of the
 * bccelerbted glyph cbche.
 */
#define D3DTR_CACHE_WIDTH       512
#define D3DTR_CACHE_HEIGHT      512
#define D3DTR_CACHE_CELL_WIDTH  16
#define D3DTR_CACHE_CELL_HEIGHT 16

/**
 * This constbnt defines the size of the tile to use in the
 * D3DTR_DrbwLCDGlyphNoCbche() method.  See below for more on why we
 * restrict this vblue to b pbrticulbr size.
 */
#define D3DTR_NOCACHE_TILE_SIZE 32

/**
 * These constbnts define the size of the "cbched destinbtion" texture.
 * This texture is only used when rendering LCD-optimized text, bs thbt
 * codepbth needs direct bccess to the destinbtion.  There is no wby to
 * bccess the frbmebuffer directly from b Direct3D shbder, so we need to first
 * copy the destinbtion region corresponding to b pbrticulbr glyph into
 * this cbched texture, bnd then thbt texture will be bccessed inside the
 * shbder.  Copying the destinbtion into this cbched texture cbn be b very
 * expensive operbtion (bccounting for bbout hblf the rendering time for
 * LCD text), so to mitigbte this cost we try to bulk rebd b horizontbl
 * region of the destinbtion bt b time.  (These vblues bre empiricblly
 * derived for the common cbse where text runs horizontblly.)
 *
 * Note: It is bssumed in vbrious cblculbtions below thbt:
 *     (D3DTR_CACHED_DEST_WIDTH  >= D3DTR_CACHE_CELL_WIDTH)  &&
 *     (D3DTR_CACHED_DEST_WIDTH  >= D3DTR_NOCACHE_TILE_SIZE) &&
 *     (D3DTR_CACHED_DEST_HEIGHT >= D3DTR_CACHE_CELL_HEIGHT) &&
 *     (D3DTR_CACHED_DEST_HEIGHT >= D3DTR_NOCACHE_TILE_SIZE)
 */
#define D3DTR_CACHED_DEST_WIDTH  512
#define D3DTR_CACHED_DEST_HEIGHT 32

#define BYTES_PER_GLYPH_IMAGE \
    sun_jbvb2d_pipe_BufferedTextPipe_BYTES_PER_GLYPH_IMAGE
#define BYTES_PER_GLYPH_POSITION \
    sun_jbvb2d_pipe_BufferedTextPipe_BYTES_PER_GLYPH_POSITION
#define BYTES_PER_POSITIONED_GLYPH \
    (BYTES_PER_GLYPH_IMAGE + BYTES_PER_GLYPH_POSITION)

#define OFFSET_CONTRAST  sun_jbvb2d_pipe_BufferedTextPipe_OFFSET_CONTRAST
#define OFFSET_RGBORDER  sun_jbvb2d_pipe_BufferedTextPipe_OFFSET_RGBORDER
#define OFFSET_SUBPIXPOS sun_jbvb2d_pipe_BufferedTextPipe_OFFSET_SUBPIXPOS
#define OFFSET_POSITIONS sun_jbvb2d_pipe_BufferedTextPipe_OFFSET_POSITIONS

HRESULT D3DTR_EnbbleGlyphVertexCbche(D3DContext *d3dc);
HRESULT D3DTR_DisbbleGlyphVertexCbche(D3DContext *d3dc);

HRESULT D3DTR_DrbwGlyphList(D3DContext *d3dc, D3DSDOps *dstOps,
                            jint totblGlyphs, jboolebn usePositions,
                            jboolebn subPixPos, jboolebn rgbOrder,
                            jint lcdContrbst,
                            jflobt glyphListOrigX, jflobt glyphListOrigY,
                            unsigned chbr *imbges, unsigned chbr *positions);

#endif /* D3DTextRenderer_h_Included */
