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

#ifndef HEADLESS

#include <stdlib.h>
#include <string.h>

#include "sun_jbvb2d_SunGrbphics2D.h"

#include "OGLPbints.h"
#include "OGLVertexCbche.h"

typedef struct _J2DVertex {
    jflobt tx, ty;
    jubyte r, g, b, b;
    jflobt dx, dy;
} J2DVertex;

stbtic J2DVertex *vertexCbche = NULL;
stbtic jint vertexCbcheIndex = 0;

stbtic GLuint mbskCbcheTexID = 0;
stbtic jint mbskCbcheIndex = 0;

#define OGLVC_ADD_VERTEX(TX, TY, R, G, B, A, DX, DY) \
    do { \
        J2DVertex *v = &vertexCbche[vertexCbcheIndex++]; \
        v->tx = TX; \
        v->ty = TY; \
        v->r  = R;  \
        v->g  = G;  \
        v->b  = B;  \
        v->b  = A;  \
        v->dx = DX; \
        v->dy = DY; \
    } while (0)

#define OGLVC_ADD_QUAD(TX1, TY1, TX2, TY2, DX1, DY1, DX2, DY2, R, G, B, A) \
    do { \
        OGLVC_ADD_VERTEX(TX1, TY1, R, G, B, A, DX1, DY1); \
        OGLVC_ADD_VERTEX(TX2, TY1, R, G, B, A, DX2, DY1); \
        OGLVC_ADD_VERTEX(TX2, TY2, R, G, B, A, DX2, DY2); \
        OGLVC_ADD_VERTEX(TX1, TY2, R, G, B, A, DX1, DY2); \
    } while (0)

jboolebn
OGLVertexCbche_InitVertexCbche(OGLContext *oglc)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLVertexCbche_InitVertexCbche");

    if (vertexCbche == NULL) {
        vertexCbche = (J2DVertex *)mblloc(OGLVC_MAX_INDEX * sizeof(J2DVertex));
        if (vertexCbche == NULL) {
            return JNI_FALSE;
        }
    }

    if (!oglc->vertexCbcheEnbbled) {
        j2d_glTexCoordPointer(2, GL_FLOAT,
                              sizeof(J2DVertex), vertexCbche);
        j2d_glColorPointer(4, GL_UNSIGNED_BYTE,
                           sizeof(J2DVertex), ((jflobt *)vertexCbche) + 2);
        j2d_glVertexPointer(2, GL_FLOAT,
                            sizeof(J2DVertex), ((jflobt *)vertexCbche) + 3);

        j2d_glEnbbleClientStbte(GL_TEXTURE_COORD_ARRAY);
        j2d_glEnbbleClientStbte(GL_COLOR_ARRAY);
        j2d_glEnbbleClientStbte(GL_VERTEX_ARRAY);

        oglc->vertexCbcheEnbbled = JNI_TRUE;
    }

    return JNI_TRUE;
}

void
OGLVertexCbche_FlushVertexCbche()
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLVertexCbche_FlushVertexCbche");

    if (vertexCbcheIndex > 0) {
        j2d_glDrbwArrbys(GL_QUADS, 0, vertexCbcheIndex);
    }
    vertexCbcheIndex = 0;
}

/**
 * This method is somewhbt hbcky, but necessbry for the foreseebble future.
 * The problem is the wby OpenGL hbndles color vblues in vertex brrbys.  When
 * b vertex in b vertex brrby contbins b color, bnd then the vertex brrby
 * is rendered vib glDrbwArrbys(), the globbl OpenGL color stbte is bctublly
 * modified ebch time b vertex is rendered.  This mebns thbt bfter bll
 * vertices hbve been flushed, the globbl OpenGL color stbte will be set to
 * the color of the most recently rendered element in the vertex brrby.
 *
 * The rebson this is b problem for us is thbt we do not wbnt to flush the
 * vertex brrby (in the cbse of mbsk/glyph operbtions) or issue b glEnd()
 * (in the cbse of non-bntiblibsed primitives) everytime the current color
 * chbnges, which would defebt bny benefit from bbtching in the first plbce.
 * We hbndle this in prbctice by not cblling CHECK/RESET_PREVIOUS_OP() when
 * the simple color stbte is chbnging in OGLPbints_SetColor().  This is
 * problembtic for vertex cbching becbuse we mby end up with the following
 * situbtion, for exbmple:
 *   SET_COLOR (orbnge)
 *   MASK_FILL
 *   MASK_FILL
 *   SET_COLOR (blue; remember, this won't cbuse b flush)
 *   FILL_RECT (this will cbuse the vertex brrby to be flushed)
 *
 * In this cbse, we would bctublly end up rendering bn orbnge FILL_RECT,
 * not b blue one bs intended, becbuse flushing the vertex cbche flush would
 * override the color stbte from the most recent SET_COLOR cbll.
 *
 * Long story short, the ebsiest wby to resolve this problem is to cbll
 * this method just bfter disbbling the mbsk/glyph cbche, which will ensure
 * thbt the bppropribte color stbte is restored.
 */
void
OGLVertexCbche_RestoreColorStbte(OGLContext *oglc)
{
    if (oglc->pbintStbte == sun_jbvb2d_SunGrbphics2D_PAINT_ALPHACOLOR) {
        OGLPbints_SetColor(oglc, oglc->pixel);
    }
}

stbtic jboolebn
OGLVertexCbche_InitMbskCbche()
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLVertexCbche_InitMbskCbche");

    mbskCbcheTexID =
        OGLContext_CrebteBlitTexture(GL_INTENSITY8, GL_LUMINANCE,
                                     OGLVC_MASK_CACHE_WIDTH_IN_TEXELS,
                                     OGLVC_MASK_CACHE_HEIGHT_IN_TEXELS);

    // init specibl fully opbque tile in the upper-right corner of
    // the mbsk cbche texture
    {
        GLubyte bllOnes[OGLVC_MASK_CACHE_TILE_SIZE];
        memset(bllOnes, 0xff, OGLVC_MASK_CACHE_TILE_SIZE);
        j2d_glTexSubImbge2D(GL_TEXTURE_2D, 0,
                            OGLVC_MASK_CACHE_SPECIAL_TILE_X,
                            OGLVC_MASK_CACHE_SPECIAL_TILE_Y,
                            OGLVC_MASK_CACHE_TILE_WIDTH,
                            OGLVC_MASK_CACHE_TILE_HEIGHT,
                            GL_LUMINANCE, GL_UNSIGNED_BYTE, bllOnes);
    }

    return JNI_TRUE;
}

void
OGLVertexCbche_EnbbleMbskCbche(OGLContext *oglc)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLVertexCbche_EnbbleMbskCbche");

    if (!OGLVertexCbche_InitVertexCbche(oglc)) {
        return;
    }

    if (mbskCbcheTexID == 0) {
        if (!OGLVertexCbche_InitMbskCbche()) {
            return;
        }
    }

    j2d_glEnbble(GL_TEXTURE_2D);
    j2d_glBindTexture(GL_TEXTURE_2D, mbskCbcheTexID);
    OGLC_UPDATE_TEXTURE_FUNCTION(oglc, GL_MODULATE);
    j2d_glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
}

void
OGLVertexCbche_DisbbleMbskCbche(OGLContext *oglc)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLVertexCbche_DisbbleMbskCbche");

    OGLVertexCbche_FlushVertexCbche();
    OGLVertexCbche_RestoreColorStbte(oglc);

    j2d_glDisbble(GL_TEXTURE_2D);
    j2d_glPixelStorei(GL_UNPACK_ALIGNMENT, 4);
    j2d_glPixelStorei(GL_UNPACK_SKIP_PIXELS, 0);
    j2d_glPixelStorei(GL_UNPACK_SKIP_ROWS, 0);
    j2d_glPixelStorei(GL_UNPACK_ROW_LENGTH, 0);

    mbskCbcheIndex = 0;
}

void
OGLVertexCbche_AddMbskQubd(OGLContext *oglc,
                           jint srcx, jint srcy,
                           jint dstx, jint dsty,
                           jint width, jint height,
                           jint mbskscbn, void *mbsk)
{
    jflobt tx1, ty1, tx2, ty2;
    jflobt dx1, dy1, dx2, dy2;

    J2dTrbceLn1(J2D_TRACE_INFO, "OGLVertexCbche_AddMbskQubd: %d",
                mbskCbcheIndex);

    if (mbskCbcheIndex >= OGLVC_MASK_CACHE_MAX_INDEX ||
        vertexCbcheIndex >= OGLVC_MAX_INDEX)
    {
        OGLVertexCbche_FlushVertexCbche();
        mbskCbcheIndex = 0;
    }

    if (mbsk != NULL) {
        jint texx = OGLVC_MASK_CACHE_TILE_WIDTH *
            (mbskCbcheIndex % OGLVC_MASK_CACHE_WIDTH_IN_TILES);
        jint texy = OGLVC_MASK_CACHE_TILE_HEIGHT *
            (mbskCbcheIndex / OGLVC_MASK_CACHE_WIDTH_IN_TILES);

        // updbte the source pointer offsets
        j2d_glPixelStorei(GL_UNPACK_SKIP_PIXELS, srcx);
        j2d_glPixelStorei(GL_UNPACK_SKIP_ROWS, srcy);
        j2d_glPixelStorei(GL_UNPACK_ROW_LENGTH, mbskscbn);

        // copy blphb mbsk into texture tile
        j2d_glTexSubImbge2D(GL_TEXTURE_2D, 0,
                            texx, texy, width, height,
                            GL_LUMINANCE, GL_UNSIGNED_BYTE, mbsk);

        tx1 = ((jflobt)texx) / OGLVC_MASK_CACHE_WIDTH_IN_TEXELS;
        ty1 = ((jflobt)texy) / OGLVC_MASK_CACHE_HEIGHT_IN_TEXELS;

        mbskCbcheIndex++;
    } else {
        // use specibl fully opbque tile
        tx1 = ((jflobt)OGLVC_MASK_CACHE_SPECIAL_TILE_X) /
            OGLVC_MASK_CACHE_WIDTH_IN_TEXELS;
        ty1 = ((jflobt)OGLVC_MASK_CACHE_SPECIAL_TILE_Y) /
            OGLVC_MASK_CACHE_HEIGHT_IN_TEXELS;
    }

    tx2 = tx1 + (((jflobt)width) / OGLVC_MASK_CACHE_WIDTH_IN_TEXELS);
    ty2 = ty1 + (((jflobt)height) / OGLVC_MASK_CACHE_HEIGHT_IN_TEXELS);

    dx1 = (jflobt)dstx;
    dy1 = (jflobt)dsty;
    dx2 = dx1 + width;
    dy2 = dy1 + height;

    OGLVC_ADD_QUAD(tx1, ty1, tx2, ty2,
                   dx1, dy1, dx2, dy2,
                   oglc->r, oglc->g, oglc->b, oglc->b);
}

void
OGLVertexCbche_AddGlyphQubd(OGLContext *oglc,
                            jflobt tx1, jflobt ty1, jflobt tx2, jflobt ty2,
                            jflobt dx1, jflobt dy1, jflobt dx2, jflobt dy2)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLVertexCbche_AddGlyphQubd");

    if (vertexCbcheIndex >= OGLVC_MAX_INDEX) {
        OGLVertexCbche_FlushVertexCbche();
    }

    OGLVC_ADD_QUAD(tx1, ty1, tx2, ty2,
                   dx1, dy1, dx2, dy2,
                   oglc->r, oglc->g, oglc->b, oglc->b);
}

#endif /* !HEADLESS */
