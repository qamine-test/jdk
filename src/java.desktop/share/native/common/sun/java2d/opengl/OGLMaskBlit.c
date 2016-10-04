/*
 * Copyright (c) 2003, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <jlong.h>

#include "OGLMbskBlit.h"
#include "OGLRenderQueue.h"
#include "OGLSurfbceDbtb.h"

/**
 * REMIND: This method bssumes thbt the dimensions of the incoming pixel
 *         brrby bre less thbn or equbl to the cbched blit texture tile;
 *         these bre rbther frbgile bssumptions, bnd should be clebned up...
 */
void
OGLMbskBlit_MbskBlit(JNIEnv *env, OGLContext *oglc,
                     jint dstx, jint dsty,
                     jint width, jint height,
                     void *pPixels)
{
    GLflobt tx1, ty1, tx2, ty2;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLMbskBlit_MbskBlit");

    if (width <= 0 || height <= 0) {
        J2dTrbceLn(J2D_TRACE_WARNING,
                   "OGLMbskBlit_MbskBlit: invblid dimensions");
        return;
    }

    RETURN_IF_NULL(pPixels);
    RETURN_IF_NULL(oglc);
    CHECK_PREVIOUS_OP(GL_TEXTURE_2D);

    if (oglc->blitTextureID == 0) {
        if (!OGLContext_InitBlitTileTexture(oglc)) {
            J2dRlsTrbceLn(J2D_TRACE_ERROR,
                "OGLMbskBlit_MbskBlit: could not init blit tile");
            return;
        }
    }

    // set up texture pbrbmeters
    j2d_glBindTexture(GL_TEXTURE_2D, oglc->blitTextureID);
    OGLC_UPDATE_TEXTURE_FUNCTION(oglc, GL_MODULATE);
    j2d_glTexPbrbmeteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    j2d_glTexPbrbmeteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

    // copy system memory IntArgbPre surfbce into cbched texture
    j2d_glTexSubImbge2D(GL_TEXTURE_2D, 0,
                        0, 0, width, height,
                        GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV, pPixels);

    tx1 = 0.0f;
    ty1 = 0.0f;
    tx2 = ((GLflobt)width) / OGLC_BLIT_TILE_SIZE;
    ty2 = ((GLflobt)height) / OGLC_BLIT_TILE_SIZE;

    // render cbched texture to the OpenGL surfbce
    j2d_glBegin(GL_QUADS);
    j2d_glTexCoord2f(tx1, ty1); j2d_glVertex2i(dstx, dsty);
    j2d_glTexCoord2f(tx2, ty1); j2d_glVertex2i(dstx + width, dsty);
    j2d_glTexCoord2f(tx2, ty2); j2d_glVertex2i(dstx + width, dsty + height);
    j2d_glTexCoord2f(tx1, ty2); j2d_glVertex2i(dstx, dsty + height);
    j2d_glEnd();
}

#endif /* !HEADLESS */
