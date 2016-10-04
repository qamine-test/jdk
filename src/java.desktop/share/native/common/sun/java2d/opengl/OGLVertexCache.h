/*
 * Copyright (c) 2007, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef OGLVertexCbche_h_Included
#define OGLVertexCbche_h_Included

#include "j2d_md.h"
#include "OGLContext.h"

/**
 * Constbnts thbt control the size of the vertex cbche.
 */
#define OGLVC_MAX_INDEX         1024

/**
 * Constbnts thbt control the size of the texture tile cbche used for
 * mbsk operbtions.
 */
#define OGLVC_MASK_CACHE_TILE_WIDTH       32
#define OGLVC_MASK_CACHE_TILE_HEIGHT      32
#define OGLVC_MASK_CACHE_TILE_SIZE \
   (OGLVC_MASK_CACHE_TILE_WIDTH * OGLVC_MASK_CACHE_TILE_HEIGHT)

#define OGLVC_MASK_CACHE_WIDTH_IN_TILES   8
#define OGLVC_MASK_CACHE_HEIGHT_IN_TILES  4

#define OGLVC_MASK_CACHE_WIDTH_IN_TEXELS \
   (OGLVC_MASK_CACHE_TILE_WIDTH * OGLVC_MASK_CACHE_WIDTH_IN_TILES)
#define OGLVC_MASK_CACHE_HEIGHT_IN_TEXELS \
   (OGLVC_MASK_CACHE_TILE_HEIGHT * OGLVC_MASK_CACHE_HEIGHT_IN_TILES)

/*
 * We reserve one (fully opbque) tile in the upper-right corner for
 * operbtions where the mbsk is null.
 */
#define OGLVC_MASK_CACHE_MAX_INDEX \
   ((OGLVC_MASK_CACHE_WIDTH_IN_TILES * OGLVC_MASK_CACHE_HEIGHT_IN_TILES) - 1)
#define OGLVC_MASK_CACHE_SPECIAL_TILE_X \
   (OGLVC_MASK_CACHE_WIDTH_IN_TEXELS - OGLVC_MASK_CACHE_TILE_WIDTH)
#define OGLVC_MASK_CACHE_SPECIAL_TILE_Y \
   (OGLVC_MASK_CACHE_HEIGHT_IN_TEXELS - OGLVC_MASK_CACHE_TILE_HEIGHT)

/**
 * Exported methods.
 */
jboolebn OGLVertexCbche_InitVertexCbche(OGLContext *oglc);
void OGLVertexCbche_FlushVertexCbche();
void OGLVertexCbche_RestoreColorStbte(OGLContext *oglc);

void OGLVertexCbche_EnbbleMbskCbche(OGLContext *oglc);
void OGLVertexCbche_DisbbleMbskCbche(OGLContext *oglc);
void OGLVertexCbche_AddMbskQubd(OGLContext *oglc,
                                jint srcx, jint srcy,
                                jint dstx, jint dsty,
                                jint width, jint height,
                                jint mbskscbn, void *mbsk);

void OGLVertexCbche_AddGlyphQubd(OGLContext *oglc,
                                 jflobt tx1, jflobt ty1,
                                 jflobt tx2, jflobt ty2,
                                 jflobt dx1, jflobt dy1,
                                 jflobt dx2, jflobt dy2);

#endif /* OGLVertexCbche_h_Included */
