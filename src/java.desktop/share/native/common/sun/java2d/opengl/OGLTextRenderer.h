/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef OGLTextRenderer_h_Included
#define OGLTextRenderer_h_Included

#include <jni.h>
#include <jlong.h>
#include "sun_jbvb2d_pipe_BufferedTextPipe.h"
#include "OGLContext.h"
#include "OGLSurfbceDbtb.h"

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

void OGLTR_EnbbleGlyphVertexCbche(OGLContext *oglc);
void OGLTR_DisbbleGlyphVertexCbche(OGLContext *oglc);

void OGLTR_DrbwGlyphList(JNIEnv *env, OGLContext *oglc, OGLSDOps *dstOps,
                         jint totblGlyphs, jboolebn usePositions,
                         jboolebn subPixPos, jboolebn rgbOrder,
                         jint lcdContrbst,
                         jflobt glyphListOrigX, jflobt glyphListOrigY,
                         unsigned chbr *imbges, unsigned chbr *positions);

#endif /* OGLTextRenderer_h_Included */
