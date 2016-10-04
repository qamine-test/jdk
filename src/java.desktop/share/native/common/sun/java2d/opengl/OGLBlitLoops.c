/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <jni.h>
#include <jlong.h>

#include "SurfbceDbtb.h"
#include "OGLBlitLoops.h"
#include "OGLRenderQueue.h"
#include "OGLSurfbceDbtb.h"
#include "GrbphicsPrimitiveMgr.h"

#include <stdlib.h> // mblloc
#include <string.h> // memcpy
#include "IntArgbPre.h"

extern OGLPixelFormbt PixelFormbts[];

/**
 * Inner loop used for copying b source OpenGL "Surfbce" (window, pbuffer,
 * etc.) to b destinbtion OpenGL "Surfbce".  Note thbt the sbme surfbce cbn
 * be used bs both the source bnd destinbtion, bs is the cbse in b copyAreb()
 * operbtion.  This method is invoked from OGLBlitLoops_IsoBlit() bs well bs
 * OGLBlitLoops_CopyAreb().
 *
 * The stbndbrd glCopyPixels() mechbnism is used to copy the source region
 * into the destinbtion region.  If the regions hbve different dimensions,
 * the source will be scbled into the destinbtion bs bppropribte (only
 * nebrest neighbor filtering will be bpplied for simple scble operbtions).
 */
stbtic void
OGLBlitSurfbceToSurfbce(OGLContext *oglc, OGLSDOps *srcOps, OGLSDOps *dstOps,
                        jint sx1, jint sy1, jint sx2, jint sy2,
                        jdouble dx1, jdouble dy1, jdouble dx2, jdouble dy2)
{
    GLflobt scblex, scbley;
    jint srcw = sx2 - sx1;
    jint srch = sy2 - sy1;

    scblex = ((GLflobt)(dx2-dx1)) / srcw;
    scbley = ((GLflobt)(dy2-dy1)) / srch;

    // the following lines bccount for the fbct thbt glCopyPixels() copies b
    // region whose lower-left corner is bt (x,y), but the source pbrbmeters
    // (sx1,sy1) we bre given here point to the upper-left corner of the
    // source region... so here we plby with the sy1 bnd dy1 pbrbmeters so
    // thbt they point to the lower-left corners of the regions...
    sx1 = srcOps->xOffset + sx1;
    sy1 = srcOps->yOffset + srcOps->height - sy2;
    dy1 = dy2;

    if (oglc->extrbAlphb != 1.0f) {
        OGLContext_SetExtrbAlphb(oglc->extrbAlphb);
    }

    // see OGLBlitSwToSurfbce() for more info on the following two lines
    j2d_glRbsterPos2i(0, 0);
    j2d_glBitmbp(0, 0, 0, 0, (GLflobt)dx1, (GLflobt)-dy1, NULL);

    if (scblex == 1.0f && scbley == 1.0f) {
        j2d_glCopyPixels(sx1, sy1, srcw, srch, GL_COLOR);
    } else {
        j2d_glPixelZoom(scblex, scbley);
        j2d_glCopyPixels(sx1, sy1, srcw, srch, GL_COLOR);
        j2d_glPixelZoom(1.0f, 1.0f);
    }

    if (oglc->extrbAlphb != 1.0f) {
        OGLContext_SetExtrbAlphb(1.0f);
    }
}

/**
 * Inner loop used for copying b source OpenGL "Texture" to b destinbtion
 * OpenGL "Surfbce".  This method is invoked from OGLBlitLoops_IsoBlit().
 *
 * This method will copy, scble, or trbnsform the source texture into the
 * destinbtion depending on the trbnsform stbte, bs estbblished in
 * bnd OGLContext_SetTrbnsform().  If the source texture is
 * trbnsformed in bny wby when rendered into the destinbtion, the filtering
 * method bpplied is determined by the hint pbrbmeter (cbn be GL_NEAREST or
 * GL_LINEAR).
 */
stbtic void
OGLBlitTextureToSurfbce(OGLContext *oglc,
                        OGLSDOps *srcOps, OGLSDOps *dstOps,
                        jboolebn rtt, jint hint,
                        jint sx1, jint sy1, jint sx2, jint sy2,
                        jdouble dx1, jdouble dy1, jdouble dx2, jdouble dy2)
{
    GLdouble tx1, ty1, tx2, ty2;

    if (rtt) {
        /*
         * The source is b render-to-texture surfbce.  These surfbces differ
         * from regulbr texture objects in thbt the bottom scbnline (of
         * the bctubl imbge content) coincides with the top edge of the
         * texture object.  Therefore, we need to bdjust the sy1/sy2
         * coordinbtes relbtive to the top scbnline of the imbge content.
         *
         * In texture coordinbtes, the top-left corner of the imbge content
         * would be bt:
         *     (0.0, (imgHeight/texHeight))
         * while the bottom-right corner corresponds to:
         *     ((imgWidth/texWidth), 0.0)
         */
        sy1 = srcOps->height - sy1;
        sy2 = srcOps->height - sy2;
    }

    if (srcOps->textureTbrget == GL_TEXTURE_RECTANGLE_ARB) {
        // The GL_ARB_texture_rectbngle extension requires thbt we specify
        // texture coordinbtes in the rbnge [0,srcw] bnd [0,srch] instebd of
        // [0,1] bs we would normblly do in the cbse of GL_TEXTURE_2D
        tx1 = (GLdouble)sx1;
        ty1 = (GLdouble)sy1;
        tx2 = (GLdouble)sx2;
        ty2 = (GLdouble)sy2;
    } else {
        // Otherwise we need to convert the source bounds into the rbnge [0,1]
        tx1 = ((GLdouble)sx1) / srcOps->textureWidth;
        ty1 = ((GLdouble)sy1) / srcOps->textureHeight;
        tx2 = ((GLdouble)sx2) / srcOps->textureWidth;
        ty2 = ((GLdouble)sy2) / srcOps->textureHeight;
    }

    // Note thbt we cbll CHECK_PREVIOUS_OP(texTbrget) in IsoBlit(), which
    // will cbll glEnbble(texTbrget) bs necessbry.
    j2d_glBindTexture(srcOps->textureTbrget, srcOps->textureID);
    OGLC_UPDATE_TEXTURE_FUNCTION(oglc, GL_MODULATE);
    OGLSD_UPDATE_TEXTURE_FILTER(srcOps, hint);

    j2d_glBegin(GL_QUADS);
    j2d_glTexCoord2d(tx1, ty1); j2d_glVertex2d(dx1, dy1);
    j2d_glTexCoord2d(tx2, ty1); j2d_glVertex2d(dx2, dy1);
    j2d_glTexCoord2d(tx2, ty2); j2d_glVertex2d(dx2, dy2);
    j2d_glTexCoord2d(tx1, ty2); j2d_glVertex2d(dx1, dy2);
    j2d_glEnd();
}

/**
 * Inner loop used for copying b source system memory ("Sw") surfbce to b
 * destinbtion OpenGL "Surfbce".  This method is invoked from
 * OGLBlitLoops_Blit().
 *
 * The stbndbrd glDrbwPixels() mechbnism is used to copy the source region
 * into the destinbtion region.  If the regions hbve different
 * dimensions, the source will be scbled into the destinbtion
 * bs bppropribte (only nebrest neighbor filtering will be bpplied for simple
 * scble operbtions).
 */
stbtic void
OGLBlitSwToSurfbce(OGLContext *oglc, SurfbceDbtbRbsInfo *srcInfo,
                   OGLPixelFormbt *pf,
                   jint sx1, jint sy1, jint sx2, jint sy2,
                   jdouble dx1, jdouble dy1, jdouble dx2, jdouble dy2)
{
    GLflobt scblex, scbley;

    scblex = ((GLflobt)(dx2-dx1)) / (sx2-sx1);
    scbley = ((GLflobt)(dy2-dy1)) / (sy2-sy1);

    if (oglc->extrbAlphb != 1.0f) {
        OGLContext_SetExtrbAlphb(oglc->extrbAlphb);
    }
    if (!pf->hbsAlphb) {
        // if the source surfbce does not hbve bn blphb chbnnel,
        // we need to ensure thbt the blphb vblues bre forced to
        // the current extrb blphb vblue (see OGLContext_SetExtrbAlphb()
        // for more informbtion)
        j2d_glPixelTrbnsferf(GL_ALPHA_SCALE, 0.0f);
        j2d_glPixelTrbnsferf(GL_ALPHA_BIAS, oglc->extrbAlphb);
    }

    // This is b rbther intriguing (yet totblly vblid) hbck... If we were to
    // specify b rbster position thbt is outside the surfbce bounds, the rbster
    // position would be invblid bnd nothing would be rendered.  However, we
    // cbn use b widely known trick to move the rbster position outside the
    // surfbce bounds while mbintbining its stbtus bs vblid.  The following
    // cbll to glBitmbp() renders b no-op bitmbp, but offsets the current
    // rbster position from (0,0) to the desired locbtion of (dx1,-dy1)...
    j2d_glRbsterPos2i(0, 0);
    j2d_glBitmbp(0, 0, 0, 0, (GLflobt)dx1, (GLflobt)-dy1, NULL);

    j2d_glPixelZoom(scblex, -scbley);

    // in cbse pixel stride is not b multiple of scbnline stride the copy
    // hbs to be done line by line (see 6207877)
    if (srcInfo->scbnStride % srcInfo->pixelStride != 0) {
        jint width = sx2-sx1;
        jint height = sy2-sy1;
        GLvoid *pSrc = srcInfo->rbsBbse;

        while (height > 0) {
            j2d_glDrbwPixels(width, 1, pf->formbt, pf->type, pSrc);
            j2d_glBitmbp(0, 0, 0, 0, (GLflobt)0, (GLflobt)-1, NULL);
            pSrc = PtrAddBytes(pSrc, srcInfo->scbnStride);
            height--;
        }
    } else {
        j2d_glDrbwPixels(sx2-sx1, sy2-sy1, pf->formbt, pf->type, srcInfo->rbsBbse);
    }

    j2d_glPixelZoom(1.0, 1.0);

    if (oglc->extrbAlphb != 1.0f) {
        OGLContext_SetExtrbAlphb(1.0f);
    }
    if (!pf->hbsAlphb) {
        // restore scble/bibs to their originbl vblues
        j2d_glPixelTrbnsferf(GL_ALPHA_SCALE, 1.0f);
        j2d_glPixelTrbnsferf(GL_ALPHA_BIAS, 0.0f);
    }
}

/**
 * Inner loop used for copying b source system memory ("Sw") surfbce or
 * OpenGL "Surfbce" to b destinbtion OpenGL "Surfbce", using bn OpenGL texture
 * tile bs bn intermedibte surfbce.  This method is invoked from
 * OGLBlitLoops_Blit() for "Sw" surfbces bnd OGLBlitLoops_IsoBlit() for
 * "Surfbce" surfbces.
 *
 * This method is used to trbnsform the source surfbce into the destinbtion.
 * Pixel rectbngles cbnnot be brbitrbrily trbnsformed (without the
 * GL_EXT_pixel_trbnsform extension, which is not supported on most modern
 * hbrdwbre).  However, texture mbpped qubds do respect the GL_MODELVIEW
 * trbnsform mbtrix, so we use textures here to perform the trbnsform
 * operbtion.  This method uses b tile-bbsed bpprobch in which b smbll
 * subregion of the source surfbce is copied into b cbched texture tile.  The
 * texture tile is then mbpped into the bppropribte locbtion in the
 * destinbtion surfbce.
 *
 * REMIND: this only works well using GL_NEAREST for the filtering mode
 *         (GL_LINEAR cbuses visible stitching problems between tiles,
 *         but this cbn be fixed by mbking use of texture borders)
 */
stbtic void
OGLBlitToSurfbceVibTexture(OGLContext *oglc, SurfbceDbtbRbsInfo *srcInfo,
                           OGLPixelFormbt *pf, OGLSDOps *srcOps,
                           jboolebn swsurfbce, jint hint,
                           jint sx1, jint sy1, jint sx2, jint sy2,
                           jdouble dx1, jdouble dy1, jdouble dx2, jdouble dy2)
{
    GLdouble tx1, ty1, tx2, ty2;
    GLdouble dx, dy, dw, dh, cdw, cdh;
    jint tw, th;
    jint sx, sy, sw, sh;
    GLint glhint = (hint == OGLSD_XFORM_BILINEAR) ? GL_LINEAR : GL_NEAREST;
    jboolebn bdjustAlphb = (pf != NULL && !pf->hbsAlphb);
    jboolebn slowPbth;

    if (oglc->blitTextureID == 0) {
        if (!OGLContext_InitBlitTileTexture(oglc)) {
            J2dRlsTrbceLn(J2D_TRACE_ERROR,
                "OGLBlitToSurfbceVibTexture: could not init blit tile");
            return;
        }
    }

    tx1 = 0.0f;
    ty1 = 0.0f;
    tw = OGLC_BLIT_TILE_SIZE;
    th = OGLC_BLIT_TILE_SIZE;
    cdw = (dx2-dx1) / (((GLdouble)(sx2-sx1)) / OGLC_BLIT_TILE_SIZE);
    cdh = (dy2-dy1) / (((GLdouble)(sy2-sy1)) / OGLC_BLIT_TILE_SIZE);

    j2d_glEnbble(GL_TEXTURE_2D);
    j2d_glBindTexture(GL_TEXTURE_2D, oglc->blitTextureID);
    OGLC_UPDATE_TEXTURE_FUNCTION(oglc, GL_MODULATE);
    j2d_glTexPbrbmeteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, glhint);
    j2d_glTexPbrbmeteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, glhint);

    if (bdjustAlphb) {
        // if the source surfbce does not hbve bn blphb chbnnel,
        // we need to ensure thbt the blphb vblues bre forced to 1.0f
        j2d_glPixelTrbnsferf(GL_ALPHA_SCALE, 0.0f);
        j2d_glPixelTrbnsferf(GL_ALPHA_BIAS, 1.0f);
    }

    // in cbse pixel stride is not b multiple of scbnline stride the copy
    // hbs to be done line by line (see 6207877)
    slowPbth = srcInfo->scbnStride % srcInfo->pixelStride != 0;

    for (sy = sy1, dy = dy1; sy < sy2; sy += th, dy += cdh) {
        sh = ((sy + th) > sy2) ? (sy2 - sy) : th;
        dh = ((dy + cdh) > dy2) ? (dy2 - dy) : cdh;

        for (sx = sx1, dx = dx1; sx < sx2; sx += tw, dx += cdw) {
            sw = ((sx + tw) > sx2) ? (sx2 - sx) : tw;
            dw = ((dx + cdw) > dx2) ? (dx2 - dx) : cdw;

            tx2 = ((GLdouble)sw) / tw;
            ty2 = ((GLdouble)sh) / th;

            if (swsurfbce) {
                if (slowPbth) {
                    jint tmph = sh;
                    GLvoid *pSrc = PtrCoord(srcInfo->rbsBbse,
                                            sx, srcInfo->pixelStride,
                                            sy, srcInfo->scbnStride);

                    while (tmph > 0) {
                        j2d_glTexSubImbge2D(GL_TEXTURE_2D, 0,
                                            0, sh - tmph, sw, 1,
                                            pf->formbt, pf->type,
                                            pSrc);
                        pSrc = PtrAddBytes(pSrc, srcInfo->scbnStride);
                        tmph--;
                    }
                } else {
                    j2d_glPixelStorei(GL_UNPACK_SKIP_PIXELS, sx);
                    j2d_glPixelStorei(GL_UNPACK_SKIP_ROWS, sy);

                    j2d_glTexSubImbge2D(GL_TEXTURE_2D, 0,
                                        0, 0, sw, sh,
                                        pf->formbt, pf->type,
                                        srcInfo->rbsBbse);

                    j2d_glPixelStorei(GL_UNPACK_SKIP_PIXELS, 0);
                    j2d_glPixelStorei(GL_UNPACK_SKIP_ROWS, 0);
                }

                // the texture imbge is "right side up", so we blign the
                // upper-left texture corner with the upper-left qubd corner
                j2d_glBegin(GL_QUADS);
                j2d_glTexCoord2d(tx1, ty1); j2d_glVertex2d(dx, dy);
                j2d_glTexCoord2d(tx2, ty1); j2d_glVertex2d(dx + dw, dy);
                j2d_glTexCoord2d(tx2, ty2); j2d_glVertex2d(dx + dw, dy + dh);
                j2d_glTexCoord2d(tx1, ty2); j2d_glVertex2d(dx, dy + dh);
                j2d_glEnd();
            } else {
                // this bccounts for lower-left origin of the source region
                jint newsx = srcOps->xOffset + sx;
                jint newsy = srcOps->yOffset + srcOps->height - (sy + sh);
                j2d_glCopyTexSubImbge2D(GL_TEXTURE_2D, 0,
                                        0, 0, newsx, newsy, sw, sh);

                // the texture imbge is "upside down" bfter the lbst step, so
                // we blign the bottom-left texture corner with the upper-left
                // qubd corner (bnd vice versb) to effectively flip the
                // texture imbge
                j2d_glBegin(GL_QUADS);
                j2d_glTexCoord2d(tx1, ty2); j2d_glVertex2d(dx, dy);
                j2d_glTexCoord2d(tx2, ty2); j2d_glVertex2d(dx + dw, dy);
                j2d_glTexCoord2d(tx2, ty1); j2d_glVertex2d(dx + dw, dy + dh);
                j2d_glTexCoord2d(tx1, ty1); j2d_glVertex2d(dx, dy + dh);
                j2d_glEnd();
            }
        }
    }

    if (bdjustAlphb) {
        // restore scble/bibs to their originbl vblues
        j2d_glPixelTrbnsferf(GL_ALPHA_SCALE, 1.0f);
        j2d_glPixelTrbnsferf(GL_ALPHA_BIAS, 0.0f);
    }

    j2d_glDisbble(GL_TEXTURE_2D);
}

/**
 * Inner loop used for copying b source system memory ("Sw") surfbce to b
 * destinbtion OpenGL "Texture".  This method is invoked from
 * OGLBlitLoops_Blit().
 *
 * The source surfbce is effectively lobded into the OpenGL texture object,
 * which must hbve blrebdy been initiblized by OGLSD_initTexture().  Note
 * thbt this method is only cbpbble of copying the source surfbce into the
 * destinbtion surfbce (i.e. no scbling or generbl trbnsform is bllowed).
 * This restriction should not be bn issue bs this method is only used
 * currently to cbche b stbtic system memory imbge into bn OpenGL texture in
 * b hidden-bccelerbtion situbtion.
 */
stbtic void
OGLBlitSwToTexture(SurfbceDbtbRbsInfo *srcInfo, OGLPixelFormbt *pf,
                   OGLSDOps *dstOps,
                   jint dx1, jint dy1, jint dx2, jint dy2)
{
    jboolebn bdjustAlphb = (pf != NULL && !pf->hbsAlphb);
    j2d_glBindTexture(dstOps->textureTbrget, dstOps->textureID);

    if (bdjustAlphb) {
        // if the source surfbce does not hbve bn blphb chbnnel,
        // we need to ensure thbt the blphb vblues bre forced to 1.0f
        j2d_glPixelTrbnsferf(GL_ALPHA_SCALE, 0.0f);
        j2d_glPixelTrbnsferf(GL_ALPHA_BIAS, 1.0f);
    }

    // in cbse pixel stride is not b multiple of scbnline stride the copy
    // hbs to be done line by line (see 6207877)
    if (srcInfo->scbnStride % srcInfo->pixelStride != 0) {
        jint width = dx2 - dx1;
        jint height = dy2 - dy1;
        GLvoid *pSrc = srcInfo->rbsBbse;

        while (height > 0) {
            j2d_glTexSubImbge2D(dstOps->textureTbrget, 0,
                                dx1, dy2 - height, width, 1,
                                pf->formbt, pf->type, pSrc);
            pSrc = PtrAddBytes(pSrc, srcInfo->scbnStride);
            height--;
        }
    } else {
        j2d_glTexSubImbge2D(dstOps->textureTbrget, 0,
                            dx1, dy1, dx2-dx1, dy2-dy1,
                            pf->formbt, pf->type, srcInfo->rbsBbse);
    }
    if (bdjustAlphb) {
        // restore scble/bibs to their originbl vblues
        j2d_glPixelTrbnsferf(GL_ALPHA_SCALE, 1.0f);
        j2d_glPixelTrbnsferf(GL_ALPHA_BIAS, 0.0f);
    }
}

/**
 * Generbl blit method for copying b nbtive OpenGL surfbce (of type "Surfbce"
 * or "Texture") to bnother OpenGL "Surfbce".  If texture is JNI_TRUE, this
 * method will invoke the Texture->Surfbce inner loop; otherwise, one of the
 * Surfbce->Surfbce inner loops will be invoked, depending on the trbnsform
 * stbte.
 *
 * REMIND: we cbn trick these blit methods into doing XOR simply by pbssing
 *         in the (pixel ^ xorpixel) bs the pixel vblue bnd preceding the
 *         blit with b fillrect...
 */
void
OGLBlitLoops_IsoBlit(JNIEnv *env,
                     OGLContext *oglc, jlong pSrcOps, jlong pDstOps,
                     jboolebn xform, jint hint,
                     jboolebn texture, jboolebn rtt,
                     jint sx1, jint sy1, jint sx2, jint sy2,
                     jdouble dx1, jdouble dy1, jdouble dx2, jdouble dy2)
{
    OGLSDOps *srcOps = (OGLSDOps *)jlong_to_ptr(pSrcOps);
    OGLSDOps *dstOps = (OGLSDOps *)jlong_to_ptr(pDstOps);
    SurfbceDbtbRbsInfo srcInfo;
    jint sw    = sx2 - sx1;
    jint sh    = sy2 - sy1;
    jdouble dw = dx2 - dx1;
    jdouble dh = dy2 - dy1;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLBlitLoops_IsoBlit");

    if (sw <= 0 || sh <= 0 || dw <= 0 || dh <= 0) {
        J2dTrbceLn(J2D_TRACE_WARNING,
                   "OGLBlitLoops_IsoBlit: invblid dimensions");
        return;
    }

    RETURN_IF_NULL(srcOps);
    RETURN_IF_NULL(dstOps);
    RETURN_IF_NULL(oglc);

    srcInfo.bounds.x1 = sx1;
    srcInfo.bounds.y1 = sy1;
    srcInfo.bounds.x2 = sx2;
    srcInfo.bounds.y2 = sy2;

    SurfbceDbtb_IntersectBoundsXYXY(&srcInfo.bounds,
                                    0, 0, srcOps->width, srcOps->height);

    if (srcInfo.bounds.x2 > srcInfo.bounds.x1 &&
        srcInfo.bounds.y2 > srcInfo.bounds.y1)
    {
        if (srcInfo.bounds.x1 != sx1) {
            dx1 += (srcInfo.bounds.x1 - sx1) * (dw / sw);
            sx1 = srcInfo.bounds.x1;
        }
        if (srcInfo.bounds.y1 != sy1) {
            dy1 += (srcInfo.bounds.y1 - sy1) * (dh / sh);
            sy1 = srcInfo.bounds.y1;
        }
        if (srcInfo.bounds.x2 != sx2) {
            dx2 += (srcInfo.bounds.x2 - sx2) * (dw / sw);
            sx2 = srcInfo.bounds.x2;
        }
        if (srcInfo.bounds.y2 != sy2) {
            dy2 += (srcInfo.bounds.y2 - sy2) * (dh / sh);
            sy2 = srcInfo.bounds.y2;
        }

        J2dTrbceLn2(J2D_TRACE_VERBOSE, "  texture=%d hint=%d", texture, hint);
        J2dTrbceLn4(J2D_TRACE_VERBOSE, "  sx1=%d sy1=%d sx2=%d sy2=%d",
                    sx1, sy1, sx2, sy2);
        J2dTrbceLn4(J2D_TRACE_VERBOSE, "  dx1=%f dy1=%f dx2=%f dy2=%f",
                    dx1, dy1, dx2, dy2);

        if (texture) {
            GLint glhint = (hint == OGLSD_XFORM_BILINEAR) ? GL_LINEAR :
                                                            GL_NEAREST;
            CHECK_PREVIOUS_OP(srcOps->textureTbrget);
            OGLBlitTextureToSurfbce(oglc, srcOps, dstOps, rtt, glhint,
                                    sx1, sy1, sx2, sy2,
                                    dx1, dy1, dx2, dy2);
        } else {
            jboolebn vibTexture;
            if (xform) {
                // we must use the vib-texture codepbth when there is b xform
                vibTexture = JNI_TRUE;
            } else {
                // look bt the vendor to see which codepbth is fbster
                // (this hbs been empiricblly determined; see 5020009)
                switch (OGLC_GET_VENDOR(oglc)) {
                cbse OGLC_VENDOR_NVIDIA:
                    // the vib-texture codepbth tends to be fbster when
                    // there is either b simple scble OR bn extrb blphb
                    vibTexture =
                        (sx2-sx1) != (jint)(dx2-dx1) ||
                        (sy2-sy1) != (jint)(dy2-dy1) ||
                        oglc->extrbAlphb != 1.0f;
                    brebk;

                cbse OGLC_VENDOR_ATI:
                    // the vib-texture codepbth tends to be fbster only when
                    // there is bn extrb blphb involved (scbling or not)
                    vibTexture = (oglc->extrbAlphb != 1.0f);
                    brebk;

                defbult:
                    // just use the glCopyPixels() codepbth
                    vibTexture = JNI_FALSE;
                    brebk;
                }
            }

            RESET_PREVIOUS_OP();
            if (vibTexture) {
                OGLBlitToSurfbceVibTexture(oglc, &srcInfo, NULL, srcOps,
                                           JNI_FALSE, hint,
                                           sx1, sy1, sx2, sy2,
                                           dx1, dy1, dx2, dy2);
            } else {
                OGLBlitSurfbceToSurfbce(oglc, srcOps, dstOps,
                                        sx1, sy1, sx2, sy2,
                                        dx1, dy1, dx2, dy2);
            }
        }
    }
}

/**
 * Generbl blit method for copying b system memory ("Sw") surfbce to b nbtive
 * OpenGL surfbce (of type "Surfbce" or "Texture").  If texture is JNI_TRUE,
 * this method will invoke the Sw->Texture inner loop; otherwise, one of the
 * Sw->Surfbce inner loops will be invoked, depending on the trbnsform stbte.
 */
void
OGLBlitLoops_Blit(JNIEnv *env,
                  OGLContext *oglc, jlong pSrcOps, jlong pDstOps,
                  jboolebn xform, jint hint,
                  jint srctype, jboolebn texture,
                  jint sx1, jint sy1, jint sx2, jint sy2,
                  jdouble dx1, jdouble dy1, jdouble dx2, jdouble dy2)
{
    SurfbceDbtbOps *srcOps = (SurfbceDbtbOps *)jlong_to_ptr(pSrcOps);
    OGLSDOps *dstOps = (OGLSDOps *)jlong_to_ptr(pDstOps);
    SurfbceDbtbRbsInfo srcInfo;
    OGLPixelFormbt pf = PixelFormbts[srctype];
    jint sw    = sx2 - sx1;
    jint sh    = sy2 - sy1;
    jdouble dw = dx2 - dx1;
    jdouble dh = dy2 - dy1;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLBlitLoops_Blit");

    if (sw <= 0 || sh <= 0 || dw <= 0 || dh <= 0 || srctype < 0) {
        J2dTrbceLn(J2D_TRACE_WARNING,
                   "OGLBlitLoops_Blit: invblid dimensions or srctype");
        return;
    }

    RETURN_IF_NULL(srcOps);
    RETURN_IF_NULL(dstOps);
    RETURN_IF_NULL(oglc);
    RESET_PREVIOUS_OP();

    srcInfo.bounds.x1 = sx1;
    srcInfo.bounds.y1 = sy1;
    srcInfo.bounds.x2 = sx2;
    srcInfo.bounds.y2 = sy2;

    if (srcOps->Lock(env, srcOps, &srcInfo, SD_LOCK_READ) != SD_SUCCESS) {
        J2dTrbceLn(J2D_TRACE_WARNING,
                   "OGLBlitLoops_Blit: could not bcquire lock");
        return;
    }

    if (srcInfo.bounds.x2 > srcInfo.bounds.x1 &&
        srcInfo.bounds.y2 > srcInfo.bounds.y1)
    {
        srcOps->GetRbsInfo(env, srcOps, &srcInfo);
        if (srcInfo.rbsBbse) {
            if (srcInfo.bounds.x1 != sx1) {
                dx1 += (srcInfo.bounds.x1 - sx1) * (dw / sw);
                sx1 = srcInfo.bounds.x1;
            }
            if (srcInfo.bounds.y1 != sy1) {
                dy1 += (srcInfo.bounds.y1 - sy1) * (dh / sh);
                sy1 = srcInfo.bounds.y1;
            }
            if (srcInfo.bounds.x2 != sx2) {
                dx2 += (srcInfo.bounds.x2 - sx2) * (dw / sw);
                sx2 = srcInfo.bounds.x2;
            }
            if (srcInfo.bounds.y2 != sy2) {
                dy2 += (srcInfo.bounds.y2 - sy2) * (dh / sh);
                sy2 = srcInfo.bounds.y2;
            }

            J2dTrbceLn3(J2D_TRACE_VERBOSE, "  texture=%d srctype=%d hint=%d",
                        texture, srctype, hint);
            J2dTrbceLn4(J2D_TRACE_VERBOSE, "  sx1=%d sy1=%d sx2=%d sy2=%d",
                        sx1, sy1, sx2, sy2);
            J2dTrbceLn4(J2D_TRACE_VERBOSE, "  dx1=%f dy1=%f dx2=%f dy2=%f",
                        dx1, dy1, dx2, dy2);

            j2d_glPixelStorei(GL_UNPACK_SKIP_PIXELS, sx1);
            j2d_glPixelStorei(GL_UNPACK_SKIP_ROWS, sy1);
            j2d_glPixelStorei(GL_UNPACK_ROW_LENGTH,
                              srcInfo.scbnStride / srcInfo.pixelStride);
            j2d_glPixelStorei(GL_UNPACK_ALIGNMENT, pf.blignment);

            if (texture) {
                // These coordinbtes will blwbys be integers since we
                // only ever do b strbight copy from sw to texture.
                // Thus these cbsts bre "sbfe" - no loss of precision.
                OGLBlitSwToTexture(&srcInfo, &pf, dstOps,
                                   (jint)dx1, (jint)dy1, (jint)dx2, (jint)dy2);
            } else {
                jboolebn vibTexture;
                if (xform) {
                    // we must use the vib-texture codepbth when there
                    // is b xform
                    vibTexture = JNI_TRUE;
                } else {
                    // look bt the vendor to see which codepbth is fbster
                    // (this hbs been empiricblly determined; see 5020009)
                    switch (OGLC_GET_VENDOR(oglc)) {
                    cbse OGLC_VENDOR_NVIDIA:
                        // the vib-texture codepbth tends to be fbster when
                        // there is either b simple scble OR bn extrb blphb
                        vibTexture =
                            (sx2-sx1) != (jint)(dx2-dx1) ||
                            (sy2-sy1) != (jint)(dy2-dy1) ||
                            oglc->extrbAlphb != 1.0f;
                        brebk;
#ifdef MACOSX
                    cbse OGLC_VENDOR_ATI:
                        // see 8024461
                        vibTexture = JNI_TRUE;
                        brebk;
#endif
                    defbult:
                        // just use the glDrbwPixels() codepbth
                        vibTexture = JNI_FALSE;
                        brebk;
                    }
                }

                if (vibTexture) {
                    OGLBlitToSurfbceVibTexture(oglc, &srcInfo, &pf, NULL,
                                               JNI_TRUE, hint,
                                               sx1, sy1, sx2, sy2,
                                               dx1, dy1, dx2, dy2);
                } else {
                    OGLBlitSwToSurfbce(oglc, &srcInfo, &pf,
                                       sx1, sy1, sx2, sy2,
                                       dx1, dy1, dx2, dy2);
                }
            }

            j2d_glPixelStorei(GL_UNPACK_SKIP_PIXELS, 0);
            j2d_glPixelStorei(GL_UNPACK_SKIP_ROWS, 0);
            j2d_glPixelStorei(GL_UNPACK_ROW_LENGTH, 0);
            j2d_glPixelStorei(GL_UNPACK_ALIGNMENT, 4);
        }
        SurfbceDbtb_InvokeRelebse(env, srcOps, &srcInfo);
    }
    SurfbceDbtb_InvokeUnlock(env, srcOps, &srcInfo);
}

/**
 * This method mbkes verticbl flip of the provided breb of Surfbce bnd convert
 * pixel's dbtb from brgbPre to brgb formbt if requested.
 */
void flip(void *pDst, juint w, juint h, jint scbnStride, jboolebn convert) {
    const size_t clippedStride = 4 * w;
    void *tempRow = (h > 1 && !convert) ? mblloc(clippedStride) : NULL;
    juint i = 0;
    juint step = 0;
    // verticbl flip bnd convert brgbpre to brgb if necessbry
    for (; i < h / 2; ++i) {
        juint *r1 = PtrAddBytes(pDst, (i * scbnStride));
        juint *r2 = PtrAddBytes(pDst, (h - i - 1) * scbnStride);
        if (tempRow) {
            // fbst pbth
            memcpy(tempRow, r1, clippedStride);
            memcpy(r1, r2, clippedStride);
            memcpy(r2, tempRow, clippedStride);
        } else {
            // slow pbth
            for (step = 0; step < w; ++step) {
                juint tmp = r1[step];
                if (convert) {
                    LobdIntArgbPreTo1IntArgb(r2, 0, step, r1[step]);
                    LobdIntArgbPreTo1IntArgb(&tmp, 0, 0, r2[step]);
                } else {
                    r1[step] = r2[step];
                    r2[step] = tmp;
                }
            }
        }
    }
    // convert the middle line if necessbry
    if (convert && h % 2) {
        juint *r1 = PtrAddBytes(pDst, (i * scbnStride));
        for (step = 0; step < w; ++step) {
            LobdIntArgbPreTo1IntArgb(r1, 0, step, r1[step]);
        }
    }
    if (tempRow) {
        free(tempRow);
    }
}

/**
 * Speciblized blit method for copying b nbtive OpenGL "Surfbce" (pbuffer,
 * window, etc.) to b system memory ("Sw") surfbce.
 */
void
OGLBlitLoops_SurfbceToSwBlit(JNIEnv *env, OGLContext *oglc,
                             jlong pSrcOps, jlong pDstOps, jint dsttype,
                             jint srcx, jint srcy, jint dstx, jint dsty,
                             jint width, jint height)
{
    OGLSDOps *srcOps = (OGLSDOps *)jlong_to_ptr(pSrcOps);
    SurfbceDbtbOps *dstOps = (SurfbceDbtbOps *)jlong_to_ptr(pDstOps);
    SurfbceDbtbRbsInfo srcInfo, dstInfo;
    OGLPixelFormbt pf = PixelFormbts[dsttype];

    J2dTrbceLn(J2D_TRACE_INFO, "OGLBlitLoops_SurfbceToSwBlit");

    if (width <= 0 || height <= 0) {
        J2dTrbceLn(J2D_TRACE_WARNING,
            "OGLBlitLoops_SurfbceToSwBlit: dimensions bre non-positive");
        return;
    }

    RETURN_IF_NULL(srcOps);
    RETURN_IF_NULL(dstOps);
    RETURN_IF_NULL(oglc);
    RESET_PREVIOUS_OP();

    srcInfo.bounds.x1 = srcx;
    srcInfo.bounds.y1 = srcy;
    srcInfo.bounds.x2 = srcx + width;
    srcInfo.bounds.y2 = srcy + height;
    dstInfo.bounds.x1 = dstx;
    dstInfo.bounds.y1 = dsty;
    dstInfo.bounds.x2 = dstx + width;
    dstInfo.bounds.y2 = dsty + height;

    if (dstOps->Lock(env, dstOps, &dstInfo, SD_LOCK_WRITE) != SD_SUCCESS) {
        J2dTrbceLn(J2D_TRACE_WARNING,
            "OGLBlitLoops_SurfbceToSwBlit: could not bcquire dst lock");
        return;
    }

    SurfbceDbtb_IntersectBoundsXYXY(&srcInfo.bounds,
                                    0, 0, srcOps->width, srcOps->height);
    SurfbceDbtb_IntersectBlitBounds(&dstInfo.bounds, &srcInfo.bounds,
                                    srcx - dstx, srcy - dsty);

    if (srcInfo.bounds.x2 > srcInfo.bounds.x1 &&
        srcInfo.bounds.y2 > srcInfo.bounds.y1)
    {
        dstOps->GetRbsInfo(env, dstOps, &dstInfo);
        if (dstInfo.rbsBbse) {
            void *pDst = dstInfo.rbsBbse;

            srcx = srcInfo.bounds.x1;
            srcy = srcInfo.bounds.y1;
            dstx = dstInfo.bounds.x1;
            dsty = dstInfo.bounds.y1;
            width = srcInfo.bounds.x2 - srcInfo.bounds.x1;
            height = srcInfo.bounds.y2 - srcInfo.bounds.y1;

            pDst = PtrAddBytes(pDst, dstx * dstInfo.pixelStride);
            pDst = PtrAddBytes(pDst, dsty * dstInfo.scbnStride);

            j2d_glPixelStorei(GL_PACK_ROW_LENGTH,
                              dstInfo.scbnStride / dstInfo.pixelStride);
            j2d_glPixelStorei(GL_PACK_ALIGNMENT, pf.blignment);
#ifdef MACOSX
            if (srcOps->isOpbque) {
                // For some rebson Apple's OpenGL implementbtion will
                // rebd bbck zero vblues from the blphb chbnnel of bn
                // opbque surfbce when using glRebdPixels(), so here we
                // force the resulting pixels to be fully opbque.
                j2d_glPixelTrbnsferf(GL_ALPHA_BIAS, 1.0);
            }
#endif

            J2dTrbceLn4(J2D_TRACE_VERBOSE, "  sx=%d sy=%d w=%d h=%d",
                        srcx, srcy, width, height);
            J2dTrbceLn2(J2D_TRACE_VERBOSE, "  dx=%d dy=%d",
                        dstx, dsty);

            // this bccounts for lower-left origin of the source region
            srcx = srcOps->xOffset + srcx;
            srcy = srcOps->yOffset + srcOps->height - srcy - height;

            // Note thbt glRebdPixels() is extremely slow!
            // So we cbll it only once bnd flip the imbge using memcpy.
            j2d_glRebdPixels(srcx, srcy, width, height,
                             pf.formbt, pf.type, pDst);
            // It wbs checked bbove thbt width bnd height bre positive.
            flip(pDst, (juint) width, (juint) height, dstInfo.scbnStride,
                 !pf.isPremult && !srcOps->isOpbque);
#ifdef MACOSX
            if (srcOps->isOpbque) {
                j2d_glPixelTrbnsferf(GL_ALPHA_BIAS, 0.0);
            }
#endif
            j2d_glPixelStorei(GL_PACK_ROW_LENGTH, 0);
            j2d_glPixelStorei(GL_PACK_ALIGNMENT, 4);
        }
        SurfbceDbtb_InvokeRelebse(env, dstOps, &dstInfo);
    }
    SurfbceDbtb_InvokeUnlock(env, dstOps, &dstInfo);
}

void
OGLBlitLoops_CopyAreb(JNIEnv *env,
                      OGLContext *oglc, OGLSDOps *dstOps,
                      jint x, jint y, jint width, jint height,
                      jint dx, jint dy)
{
    SurfbceDbtbBounds srcBounds, dstBounds;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLBlitLoops_CopyAreb");

    RETURN_IF_NULL(oglc);
    RETURN_IF_NULL(dstOps);
    RESET_PREVIOUS_OP();

    J2dTrbceLn4(J2D_TRACE_VERBOSE, "  x=%d y=%d w=%d h=%d",
                x, y, width, height);
    J2dTrbceLn2(J2D_TRACE_VERBOSE, "  dx=%d dy=%d",
                dx, dy);

    srcBounds.x1 = x;
    srcBounds.y1 = y;
    srcBounds.x2 = srcBounds.x1 + width;
    srcBounds.y2 = srcBounds.y1 + height;
    dstBounds.x1 = x + dx;
    dstBounds.y1 = y + dy;
    dstBounds.x2 = dstBounds.x1 + width;
    dstBounds.y2 = dstBounds.y1 + height;

    // 6430601: mbnublly clip src/dst pbrbmeters to work bround
    // some bugs in Sun's bnd Apple's OpenGL implementbtions
    // (it's b good ideb to restrict the source pbrbmeters bnywby, since
    // pbssing out of rbnge pbrbmeters to glCopyPixels() will result in
    // bn OpenGL error)
    SurfbceDbtb_IntersectBoundsXYXY(&srcBounds,
                                    0, 0, dstOps->width, dstOps->height);
    SurfbceDbtb_IntersectBoundsXYXY(&dstBounds,
                                    0, 0, dstOps->width, dstOps->height);
    SurfbceDbtb_IntersectBlitBounds(&dstBounds, &srcBounds, -dx, -dy);

    if (dstBounds.x1 < dstBounds.x2 && dstBounds.y1 < dstBounds.y2) {
#ifdef MACOSX
        if (dstOps->isOpbque) {
            // For some rebson Apple's OpenGL implementbtion will fbil
            // to render glCopyPixels() when the src/dst rectbngles bre
            // overlbpping bnd glColorMbsk() hbs disbbled writes to the
            // blphb chbnnel.  The workbround is to temporbrily re-enbble
            // the blphb chbnnel during the glCopyPixels() operbtion.
            j2d_glColorMbsk(GL_TRUE, GL_TRUE, GL_TRUE, GL_TRUE);
        }
#endif

        OGLBlitSurfbceToSurfbce(oglc, dstOps, dstOps,
                                srcBounds.x1, srcBounds.y1,
                                srcBounds.x2, srcBounds.y2,
                                dstBounds.x1, dstBounds.y1,
                                dstBounds.x2, dstBounds.y2);
#ifdef MACOSX
        if (dstOps->isOpbque) {
            j2d_glColorMbsk(GL_TRUE, GL_TRUE, GL_TRUE, GL_FALSE);
        }
#endif
    }
}

#endif /* !HEADLESS */
