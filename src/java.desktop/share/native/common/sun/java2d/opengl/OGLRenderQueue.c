/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "sun_jbvb2d_pipe_BufferedOpCodes.h"

#include "jlong.h"
#include "OGLBlitLoops.h"
#include "OGLBufImgOps.h"
#include "OGLContext.h"
#include "OGLMbskBlit.h"
#include "OGLMbskFill.h"
#include "OGLPbints.h"
#include "OGLRenderQueue.h"
#include "OGLRenderer.h"
#include "OGLSurfbceDbtb.h"
#include "OGLTextRenderer.h"
#include "OGLVertexCbche.h"

/**
 * Used to trbck whether we bre in b series of b simple primitive operbtions
 * or texturing operbtions.  This vbribble should be controlled only vib
 * the INIT/CHECK/RESET_PREVIOUS_OP() mbcros.  See the
 * OGLRenderQueue_CheckPreviousOp() method below for more informbtion.
 */
jint previousOp;

/**
 * References to the "current" context bnd destinbtion surfbce.
 */
stbtic OGLContext *oglc = NULL;
stbtic OGLSDOps *dstOps = NULL;

/**
 * The following methods bre implemented in the windowing system (i.e. GLX
 * bnd WGL) source files.
 */
extern OGLContext *OGLSD_SetScrbtchSurfbce(JNIEnv *env, jlong pConfigInfo);
extern void OGLGC_DestroyOGLGrbphicsConfig(jlong pConfigInfo);
extern void OGLSD_SwbpBuffers(JNIEnv *env, jlong window);
extern void OGLSD_Flush(JNIEnv *env);

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opengl_OGLRenderQueue_flushBuffer
    (JNIEnv *env, jobject oglrq,
     jlong buf, jint limit)
{
    jboolebn sync = JNI_FALSE;
    unsigned chbr *b, *end;

    J2dTrbceLn1(J2D_TRACE_INFO,
                "OGLRenderQueue_flushBuffer: limit=%d", limit);

    b = (unsigned chbr *)jlong_to_ptr(buf);
    if (b == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLRenderQueue_flushBuffer: cbnnot get direct buffer bddress");
        return;
    }

    INIT_PREVIOUS_OP();
    end = b + limit;

    while (b < end) {
        jint opcode = NEXT_INT(b);

        J2dTrbceLn2(J2D_TRACE_VERBOSE,
                    "OGLRenderQueue_flushBuffer: opcode=%d, rem=%d",
                    opcode, (end-b));

        switch (opcode) {

        // drbw ops
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DRAW_LINE:
            {
                jint x1 = NEXT_INT(b);
                jint y1 = NEXT_INT(b);
                jint x2 = NEXT_INT(b);
                jint y2 = NEXT_INT(b);
                OGLRenderer_DrbwLine(oglc, x1, y1, x2, y2);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DRAW_RECT:
            {
                jint x = NEXT_INT(b);
                jint y = NEXT_INT(b);
                jint w = NEXT_INT(b);
                jint h = NEXT_INT(b);
                OGLRenderer_DrbwRect(oglc, x, y, w, h);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DRAW_POLY:
            {
                jint nPoints      = NEXT_INT(b);
                jboolebn isClosed = NEXT_BOOLEAN(b);
                jint trbnsX       = NEXT_INT(b);
                jint trbnsY       = NEXT_INT(b);
                jint *xPoints = (jint *)b;
                jint *yPoints = ((jint *)b) + nPoints;
                OGLRenderer_DrbwPoly(oglc, nPoints, isClosed,
                                     trbnsX, trbnsY,
                                     xPoints, yPoints);
                SKIP_BYTES(b, nPoints * BYTES_PER_POLY_POINT);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DRAW_PIXEL:
            {
                jint x = NEXT_INT(b);
                jint y = NEXT_INT(b);
                // Note thbt we could use GL_POINTS here, but the common
                // use cbse for DRAW_PIXEL is when rendering b Pbth2D,
                // which will consist of b mix of DRAW_PIXEL bnd DRAW_LINE
                // cblls.  So to improve bbtching we use GL_LINES here,
                // even though it requires bn extrb vertex per pixel.
                CONTINUE_IF_NULL(oglc);
                CHECK_PREVIOUS_OP(GL_LINES);
                j2d_glVertex2i(x, y);
                j2d_glVertex2i(x+1, y+1);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DRAW_SCANLINES:
            {
                jint count = NEXT_INT(b);
                OGLRenderer_DrbwScbnlines(oglc, count, (jint *)b);
                SKIP_BYTES(b, count * BYTES_PER_SCANLINE);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DRAW_PARALLELOGRAM:
            {
                jflobt x11 = NEXT_FLOAT(b);
                jflobt y11 = NEXT_FLOAT(b);
                jflobt dx21 = NEXT_FLOAT(b);
                jflobt dy21 = NEXT_FLOAT(b);
                jflobt dx12 = NEXT_FLOAT(b);
                jflobt dy12 = NEXT_FLOAT(b);
                jflobt lwr21 = NEXT_FLOAT(b);
                jflobt lwr12 = NEXT_FLOAT(b);
                OGLRenderer_DrbwPbrbllelogrbm(oglc,
                                              x11, y11,
                                              dx21, dy21,
                                              dx12, dy12,
                                              lwr21, lwr12);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DRAW_AAPARALLELOGRAM:
            {
                jflobt x11 = NEXT_FLOAT(b);
                jflobt y11 = NEXT_FLOAT(b);
                jflobt dx21 = NEXT_FLOAT(b);
                jflobt dy21 = NEXT_FLOAT(b);
                jflobt dx12 = NEXT_FLOAT(b);
                jflobt dy12 = NEXT_FLOAT(b);
                jflobt lwr21 = NEXT_FLOAT(b);
                jflobt lwr12 = NEXT_FLOAT(b);
                OGLRenderer_DrbwAAPbrbllelogrbm(oglc, dstOps,
                                                x11, y11,
                                                dx21, dy21,
                                                dx12, dy12,
                                                lwr21, lwr12);
            }
            brebk;

        // fill ops
        cbse sun_jbvb2d_pipe_BufferedOpCodes_FILL_RECT:
            {
                jint x = NEXT_INT(b);
                jint y = NEXT_INT(b);
                jint w = NEXT_INT(b);
                jint h = NEXT_INT(b);
                OGLRenderer_FillRect(oglc, x, y, w, h);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_FILL_SPANS:
            {
                jint count = NEXT_INT(b);
                OGLRenderer_FillSpbns(oglc, count, (jint *)b);
                SKIP_BYTES(b, count * BYTES_PER_SPAN);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_FILL_PARALLELOGRAM:
            {
                jflobt x11 = NEXT_FLOAT(b);
                jflobt y11 = NEXT_FLOAT(b);
                jflobt dx21 = NEXT_FLOAT(b);
                jflobt dy21 = NEXT_FLOAT(b);
                jflobt dx12 = NEXT_FLOAT(b);
                jflobt dy12 = NEXT_FLOAT(b);
                OGLRenderer_FillPbrbllelogrbm(oglc,
                                              x11, y11,
                                              dx21, dy21,
                                              dx12, dy12);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_FILL_AAPARALLELOGRAM:
            {
                jflobt x11 = NEXT_FLOAT(b);
                jflobt y11 = NEXT_FLOAT(b);
                jflobt dx21 = NEXT_FLOAT(b);
                jflobt dy21 = NEXT_FLOAT(b);
                jflobt dx12 = NEXT_FLOAT(b);
                jflobt dy12 = NEXT_FLOAT(b);
                OGLRenderer_FillAAPbrbllelogrbm(oglc, dstOps,
                                                x11, y11,
                                                dx21, dy21,
                                                dx12, dy12);
            }
            brebk;

        // text-relbted ops
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DRAW_GLYPH_LIST:
            {
                jint numGlyphs        = NEXT_INT(b);
                jint pbckedPbrbms     = NEXT_INT(b);
                jflobt glyphListOrigX = NEXT_FLOAT(b);
                jflobt glyphListOrigY = NEXT_FLOAT(b);
                jboolebn usePositions = EXTRACT_BOOLEAN(pbckedPbrbms,
                                                        OFFSET_POSITIONS);
                jboolebn subPixPos    = EXTRACT_BOOLEAN(pbckedPbrbms,
                                                        OFFSET_SUBPIXPOS);
                jboolebn rgbOrder     = EXTRACT_BOOLEAN(pbckedPbrbms,
                                                        OFFSET_RGBORDER);
                jint lcdContrbst      = EXTRACT_BYTE(pbckedPbrbms,
                                                     OFFSET_CONTRAST);
                unsigned chbr *imbges = b;
                unsigned chbr *positions;
                jint bytesPerGlyph;
                if (usePositions) {
                    positions = (b + numGlyphs * BYTES_PER_GLYPH_IMAGE);
                    bytesPerGlyph = BYTES_PER_POSITIONED_GLYPH;
                } else {
                    positions = NULL;
                    bytesPerGlyph = BYTES_PER_GLYPH_IMAGE;
                }
                OGLTR_DrbwGlyphList(env, oglc, dstOps,
                                    numGlyphs, usePositions,
                                    subPixPos, rgbOrder, lcdContrbst,
                                    glyphListOrigX, glyphListOrigY,
                                    imbges, positions);
                SKIP_BYTES(b, numGlyphs * bytesPerGlyph);
            }
            brebk;

        // copy-relbted ops
        cbse sun_jbvb2d_pipe_BufferedOpCodes_COPY_AREA:
            {
                jint x  = NEXT_INT(b);
                jint y  = NEXT_INT(b);
                jint w  = NEXT_INT(b);
                jint h  = NEXT_INT(b);
                jint dx = NEXT_INT(b);
                jint dy = NEXT_INT(b);
                OGLBlitLoops_CopyAreb(env, oglc, dstOps,
                                      x, y, w, h, dx, dy);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_BLIT:
            {
                jint pbckedPbrbms = NEXT_INT(b);
                jint sx1          = NEXT_INT(b);
                jint sy1          = NEXT_INT(b);
                jint sx2          = NEXT_INT(b);
                jint sy2          = NEXT_INT(b);
                jdouble dx1       = NEXT_DOUBLE(b);
                jdouble dy1       = NEXT_DOUBLE(b);
                jdouble dx2       = NEXT_DOUBLE(b);
                jdouble dy2       = NEXT_DOUBLE(b);
                jlong pSrc        = NEXT_LONG(b);
                jlong pDst        = NEXT_LONG(b);
                jint hint         = EXTRACT_BYTE(pbckedPbrbms, OFFSET_HINT);
                jboolebn texture  = EXTRACT_BOOLEAN(pbckedPbrbms,
                                                    OFFSET_TEXTURE);
                jboolebn rtt      = EXTRACT_BOOLEAN(pbckedPbrbms,
                                                    OFFSET_RTT);
                jboolebn xform    = EXTRACT_BOOLEAN(pbckedPbrbms,
                                                    OFFSET_XFORM);
                jboolebn isoblit  = EXTRACT_BOOLEAN(pbckedPbrbms,
                                                    OFFSET_ISOBLIT);
                if (isoblit) {
                    OGLBlitLoops_IsoBlit(env, oglc, pSrc, pDst,
                                         xform, hint, texture, rtt,
                                         sx1, sy1, sx2, sy2,
                                         dx1, dy1, dx2, dy2);
                } else {
                    jint srctype = EXTRACT_BYTE(pbckedPbrbms, OFFSET_SRCTYPE);
                    OGLBlitLoops_Blit(env, oglc, pSrc, pDst,
                                      xform, hint, srctype, texture,
                                      sx1, sy1, sx2, sy2,
                                      dx1, dy1, dx2, dy2);
                }
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SURFACE_TO_SW_BLIT:
            {
                jint sx      = NEXT_INT(b);
                jint sy      = NEXT_INT(b);
                jint dx      = NEXT_INT(b);
                jint dy      = NEXT_INT(b);
                jint w       = NEXT_INT(b);
                jint h       = NEXT_INT(b);
                jint dsttype = NEXT_INT(b);
                jlong pSrc   = NEXT_LONG(b);
                jlong pDst   = NEXT_LONG(b);
                OGLBlitLoops_SurfbceToSwBlit(env, oglc,
                                             pSrc, pDst, dsttype,
                                             sx, sy, dx, dy, w, h);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_MASK_FILL:
            {
                jint x        = NEXT_INT(b);
                jint y        = NEXT_INT(b);
                jint w        = NEXT_INT(b);
                jint h        = NEXT_INT(b);
                jint mbskoff  = NEXT_INT(b);
                jint mbskscbn = NEXT_INT(b);
                jint mbsklen  = NEXT_INT(b);
                unsigned chbr *pMbsk = (mbsklen > 0) ? b : NULL;
                OGLMbskFill_MbskFill(oglc, x, y, w, h,
                                     mbskoff, mbskscbn, mbsklen, pMbsk);
                SKIP_BYTES(b, mbsklen);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_MASK_BLIT:
            {
                jint dstx     = NEXT_INT(b);
                jint dsty     = NEXT_INT(b);
                jint width    = NEXT_INT(b);
                jint height   = NEXT_INT(b);
                jint mbsklen  = width * height * sizeof(jint);
                OGLMbskBlit_MbskBlit(env, oglc,
                                     dstx, dsty, width, height, b);
                SKIP_BYTES(b, mbsklen);
            }
            brebk;

        // stbte-relbted ops
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_RECT_CLIP:
            {
                jint x1 = NEXT_INT(b);
                jint y1 = NEXT_INT(b);
                jint x2 = NEXT_INT(b);
                jint y2 = NEXT_INT(b);
                OGLContext_SetRectClip(oglc, dstOps, x1, y1, x2, y2);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_BEGIN_SHAPE_CLIP:
            {
                OGLContext_BeginShbpeClip(oglc);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_SHAPE_CLIP_SPANS:
            {
                jint count = NEXT_INT(b);
                OGLRenderer_FillSpbns(oglc, count, (jint *)b);
                SKIP_BYTES(b, count * BYTES_PER_SPAN);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_END_SHAPE_CLIP:
            {
                OGLContext_EndShbpeClip(oglc, dstOps);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_RESET_CLIP:
            {
                OGLContext_ResetClip(oglc);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_ALPHA_COMPOSITE:
            {
                jint rule         = NEXT_INT(b);
                jflobt extrbAlphb = NEXT_FLOAT(b);
                jint flbgs        = NEXT_INT(b);
                OGLContext_SetAlphbComposite(oglc, rule, extrbAlphb, flbgs);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_XOR_COMPOSITE:
            {
                jint xorPixel = NEXT_INT(b);
                OGLContext_SetXorComposite(oglc, xorPixel);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_RESET_COMPOSITE:
            {
                OGLContext_ResetComposite(oglc);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_TRANSFORM:
            {
                jdouble m00 = NEXT_DOUBLE(b);
                jdouble m10 = NEXT_DOUBLE(b);
                jdouble m01 = NEXT_DOUBLE(b);
                jdouble m11 = NEXT_DOUBLE(b);
                jdouble m02 = NEXT_DOUBLE(b);
                jdouble m12 = NEXT_DOUBLE(b);
                OGLContext_SetTrbnsform(oglc, m00, m10, m01, m11, m02, m12);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_RESET_TRANSFORM:
            {
                OGLContext_ResetTrbnsform(oglc);
            }
            brebk;

        // context-relbted ops
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_SURFACES:
            {
                jlong pSrc = NEXT_LONG(b);
                jlong pDst = NEXT_LONG(b);
                if (oglc != NULL) {
                    RESET_PREVIOUS_OP();
                }
                oglc = OGLContext_SetSurfbces(env, pSrc, pDst);
                dstOps = (OGLSDOps *)jlong_to_ptr(pDst);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_SCRATCH_SURFACE:
            {
                jlong pConfigInfo = NEXT_LONG(b);
                if (oglc != NULL) {
                    RESET_PREVIOUS_OP();
                }
                oglc = OGLSD_SetScrbtchSurfbce(env, pConfigInfo);
                dstOps = NULL;
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_FLUSH_SURFACE:
            {
                jlong pDbtb = NEXT_LONG(b);
                OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);
                if (oglsdo != NULL) {
                    CONTINUE_IF_NULL(oglc);
                    RESET_PREVIOUS_OP();
                    OGLSD_Delete(env, oglsdo);
                }
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DISPOSE_SURFACE:
            {
                jlong pDbtb = NEXT_LONG(b);
                OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);
                if (oglsdo != NULL) {
                    CONTINUE_IF_NULL(oglc);
                    RESET_PREVIOUS_OP();
                    OGLSD_Delete(env, oglsdo);
                    if (oglsdo->privOps != NULL) {
                        free(oglsdo->privOps);
                    }
                }
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DISPOSE_CONFIG:
            {
                jlong pConfigInfo = NEXT_LONG(b);
                CONTINUE_IF_NULL(oglc);
                RESET_PREVIOUS_OP();
                OGLGC_DestroyOGLGrbphicsConfig(pConfigInfo);

                // the previous method will cbll glX/wglMbkeCurrent(None),
                // so we should nullify the current oglc bnd dstOps to bvoid
                // cblling glFlush() (or similbr) while no context is current
                oglc = NULL;
                dstOps = NULL;
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_INVALIDATE_CONTEXT:
            {
                // flush just in cbse there bre bny pending operbtions in
                // the hbrdwbre pipe
                if (oglc != NULL) {
                    RESET_PREVIOUS_OP();
                    j2d_glFlush();
                }

                // invblidbte the references to the current context bnd
                // destinbtion surfbce thbt bre mbintbined bt the nbtive level
                oglc = NULL;
                dstOps = NULL;
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SAVE_STATE:
            {
                j2d_glPushAttrib(GL_ALL_ATTRIB_BITS);
                j2d_glPushClientAttrib(GL_CLIENT_ALL_ATTRIB_BITS);
                j2d_glMbtrixMode(GL_MODELVIEW);
                j2d_glPushMbtrix();
                j2d_glMbtrixMode(GL_PROJECTION);
                j2d_glPushMbtrix();
                j2d_glMbtrixMode(GL_TEXTURE);
                j2d_glPushMbtrix();
            }
            brebk;

        cbse sun_jbvb2d_pipe_BufferedOpCodes_RESTORE_STATE:
            {
                j2d_glPopAttrib();
                j2d_glPopClientAttrib();
                j2d_glMbtrixMode(GL_MODELVIEW);
                j2d_glPopMbtrix();
                j2d_glMbtrixMode(GL_PROJECTION);
                j2d_glPopMbtrix();
                j2d_glMbtrixMode(GL_TEXTURE);
                j2d_glPopMbtrix();
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SYNC:
            {
                sync = JNI_TRUE;
            }
            brebk;

        // multibuffering ops
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SWAP_BUFFERS:
            {
                jlong window = NEXT_LONG(b);
                if (oglc != NULL) {
                    RESET_PREVIOUS_OP();
                }
                OGLSD_SwbpBuffers(env, window);
            }
            brebk;

        // specibl no-op (mbinly used for bchieving 8-byte blignment)
        cbse sun_jbvb2d_pipe_BufferedOpCodes_NOOP:
            brebk;

        // pbint-relbted ops
        cbse sun_jbvb2d_pipe_BufferedOpCodes_RESET_PAINT:
            {
                OGLPbints_ResetPbint(oglc);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_COLOR:
            {
                jint pixel = NEXT_INT(b);
                OGLPbints_SetColor(oglc, pixel);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_GRADIENT_PAINT:
            {
                jboolebn useMbsk= NEXT_BOOLEAN(b);
                jboolebn cyclic = NEXT_BOOLEAN(b);
                jdouble p0      = NEXT_DOUBLE(b);
                jdouble p1      = NEXT_DOUBLE(b);
                jdouble p3      = NEXT_DOUBLE(b);
                jint pixel1     = NEXT_INT(b);
                jint pixel2     = NEXT_INT(b);
                OGLPbints_SetGrbdientPbint(oglc, useMbsk, cyclic,
                                           p0, p1, p3,
                                           pixel1, pixel2);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_LINEAR_GRADIENT_PAINT:
            {
                jboolebn useMbsk = NEXT_BOOLEAN(b);
                jboolebn linebr  = NEXT_BOOLEAN(b);
                jint cycleMethod = NEXT_INT(b);
                jint numStops    = NEXT_INT(b);
                jflobt p0        = NEXT_FLOAT(b);
                jflobt p1        = NEXT_FLOAT(b);
                jflobt p3        = NEXT_FLOAT(b);
                void *frbctions, *pixels;
                frbctions = b; SKIP_BYTES(b, numStops * sizeof(jflobt));
                pixels    = b; SKIP_BYTES(b, numStops * sizeof(jint));
                OGLPbints_SetLinebrGrbdientPbint(oglc, dstOps,
                                                 useMbsk, linebr,
                                                 cycleMethod, numStops,
                                                 p0, p1, p3,
                                                 frbctions, pixels);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_RADIAL_GRADIENT_PAINT:
            {
                jboolebn useMbsk = NEXT_BOOLEAN(b);
                jboolebn linebr  = NEXT_BOOLEAN(b);
                jint numStops    = NEXT_INT(b);
                jint cycleMethod = NEXT_INT(b);
                jflobt m00       = NEXT_FLOAT(b);
                jflobt m01       = NEXT_FLOAT(b);
                jflobt m02       = NEXT_FLOAT(b);
                jflobt m10       = NEXT_FLOAT(b);
                jflobt m11       = NEXT_FLOAT(b);
                jflobt m12       = NEXT_FLOAT(b);
                jflobt focusX    = NEXT_FLOAT(b);
                void *frbctions, *pixels;
                frbctions = b; SKIP_BYTES(b, numStops * sizeof(jflobt));
                pixels    = b; SKIP_BYTES(b, numStops * sizeof(jint));
                OGLPbints_SetRbdiblGrbdientPbint(oglc, dstOps,
                                                 useMbsk, linebr,
                                                 cycleMethod, numStops,
                                                 m00, m01, m02,
                                                 m10, m11, m12,
                                                 focusX,
                                                 frbctions, pixels);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_TEXTURE_PAINT:
            {
                jboolebn useMbsk= NEXT_BOOLEAN(b);
                jboolebn filter = NEXT_BOOLEAN(b);
                jlong pSrc      = NEXT_LONG(b);
                jdouble xp0     = NEXT_DOUBLE(b);
                jdouble xp1     = NEXT_DOUBLE(b);
                jdouble xp3     = NEXT_DOUBLE(b);
                jdouble yp0     = NEXT_DOUBLE(b);
                jdouble yp1     = NEXT_DOUBLE(b);
                jdouble yp3     = NEXT_DOUBLE(b);
                OGLPbints_SetTexturePbint(oglc, useMbsk, pSrc, filter,
                                          xp0, xp1, xp3,
                                          yp0, yp1, yp3);
            }
            brebk;

        // BufferedImbgeOp-relbted ops
        cbse sun_jbvb2d_pipe_BufferedOpCodes_ENABLE_CONVOLVE_OP:
            {
                jlong pSrc        = NEXT_LONG(b);
                jboolebn edgeZero = NEXT_BOOLEAN(b);
                jint kernelWidth  = NEXT_INT(b);
                jint kernelHeight = NEXT_INT(b);
                OGLBufImgOps_EnbbleConvolveOp(oglc, pSrc, edgeZero,
                                              kernelWidth, kernelHeight, b);
                SKIP_BYTES(b, kernelWidth * kernelHeight * sizeof(jflobt));
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DISABLE_CONVOLVE_OP:
            {
                OGLBufImgOps_DisbbleConvolveOp(oglc);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_ENABLE_RESCALE_OP:
            {
                jlong pSrc          = NEXT_LONG(b);
                jboolebn nonPremult = NEXT_BOOLEAN(b);
                jint numFbctors     = 4;
                unsigned chbr *scbleFbctors = b;
                unsigned chbr *offsets = (b + numFbctors * sizeof(jflobt));
                OGLBufImgOps_EnbbleRescbleOp(oglc, pSrc, nonPremult,
                                             scbleFbctors, offsets);
                SKIP_BYTES(b, numFbctors * sizeof(jflobt) * 2);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DISABLE_RESCALE_OP:
            {
                OGLBufImgOps_DisbbleRescbleOp(oglc);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_ENABLE_LOOKUP_OP:
            {
                jlong pSrc          = NEXT_LONG(b);
                jboolebn nonPremult = NEXT_BOOLEAN(b);
                jboolebn shortDbtb  = NEXT_BOOLEAN(b);
                jint numBbnds       = NEXT_INT(b);
                jint bbndLength     = NEXT_INT(b);
                jint offset         = NEXT_INT(b);
                jint bytesPerElem = shortDbtb ? sizeof(jshort):sizeof(jbyte);
                void *tbbleVblues = b;
                OGLBufImgOps_EnbbleLookupOp(oglc, pSrc, nonPremult, shortDbtb,
                                            numBbnds, bbndLength, offset,
                                            tbbleVblues);
                SKIP_BYTES(b, numBbnds * bbndLength * bytesPerElem);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DISABLE_LOOKUP_OP:
            {
                OGLBufImgOps_DisbbleLookupOp(oglc);
            }
            brebk;

        defbult:
            J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                "OGLRenderQueue_flushBuffer: invblid opcode=%d", opcode);
            if (oglc != NULL) {
                RESET_PREVIOUS_OP();
            }
            return;
        }
    }

    if (oglc != NULL) {
        RESET_PREVIOUS_OP();
        if (sync) {
            j2d_glFinish();
        } else {
            j2d_glFlush();
        }
        OGLSD_Flush(env);
    }
}

/**
 * Returns b pointer to the "current" context, bs set by the lbst SET_SURFACES
 * or SET_SCRATCH_SURFACE operbtion.
 */
OGLContext *
OGLRenderQueue_GetCurrentContext()
{
    return oglc;
}

/**
 * Returns b pointer to the "current" destinbtion surfbce, bs set by the lbst
 * SET_SURFACES operbtion.
 */
OGLSDOps *
OGLRenderQueue_GetCurrentDestinbtion()
{
    return dstOps;
}

/**
 * Used to trbck whether we bre within b series of simple primitive operbtions
 * or texturing operbtions.  The op pbrbmeter determines the nbture of the
 * operbtion thbt is to follow.  Vblid vblues for this op pbrbmeter bre:
 *
 *     GL_QUADS
 *     GL_LINES
 *     GL_LINE_LOOP
 *     GL_LINE_STRIP
 *     (bbsicblly bny of the vblid pbrbmeters for glBegin())
 *
 *     GL_TEXTURE_2D
 *     GL_TEXTURE_RECTANGLE_ARB
 *
 *     OGL_STATE_RESET
 *     OGL_STATE_CHANGE
 *     OGL_STATE_MASK_OP
 *     OGL_STATE_GLYPH_OP
 *
 * Note thbt the bbove constbnts bre gubrbnteed to be unique vblues.  The
 * lbst few bre defined to be negbtive vblues to differentibte them from
 * the core GL* constbnts, which bre defined to be non-negbtive.
 *
 * For simple primitives, this method bllows us to bbtch similbr primitives
 * within the sbme glBegin()/glEnd() pbir.  For exbmple, if we hbve 100
 * consecutive FILL_RECT operbtions, we only hbve to cbll glBegin(GL_QUADS)
 * for the first op, bnd then subsequent operbtions will consist only of
 * glVertex*() cblls, which helps improve performbnce.  The glEnd() cbll
 * only needs to be issued before bn operbtion thbt cbnnot hbppen within b
 * glBegin()/glEnd() pbir (e.g. updbting the clip), or one thbt requires b
 * different primitive mode (e.g. GL_LINES).
 *
 * For operbtions thbt involve texturing, this method helps us to bvoid
 * cblling glEnbble(GL_TEXTURE_2D) bnd glDisbble(GL_TEXTURE_2D) bround ebch
 * operbtion.  For exbmple, if we hbve bn blternbting series of ISO_BLIT
 * bnd MASK_BLIT operbtions (both of which involve texturing), we need
 * only to cbll glEnbble(GL_TEXTURE_2D) before the first ISO_BLIT operbtion.
 * The glDisbble(GL_TEXTURE_2D) cbll only needs to be issued before bn
 * operbtion thbt cbnnot (or should not) hbppen while texturing is enbbled
 * (e.g. b context chbnge, or b simple primitive operbtion like GL_QUADS).
 */
void
OGLRenderQueue_CheckPreviousOp(jint op)
{
    if (previousOp == op) {
        // The op is the sbme bs lbst time, so we cbn return immedibtely.
        return;
    }

    J2dTrbceLn1(J2D_TRACE_VERBOSE,
                "OGLRenderQueue_CheckPreviousOp: new op=%d", op);

    switch (previousOp) {
    cbse GL_TEXTURE_2D:
    cbse GL_TEXTURE_RECTANGLE_ARB:
        if (op == OGL_STATE_CHANGE) {
            // Optimizbtion: Certbin stbte chbnges (those mbrked bs
            // OGL_STATE_CHANGE) bre bllowed while texturing is enbbled.
            // In this cbse, we cbn bllow previousOp to rembin bs it is bnd
            // then return ebrly.
            return;
        } else {
            // Otherwise, op must be b primitive operbtion, or b reset, so
            // we will disbble texturing.
            j2d_glDisbble(previousOp);
            // This next step of binding to zero should not be strictly
            // necessbry, but on some older Nvidib bobrds (e.g. GeForce 2)
            // problems will brise if GL_TEXTURE_2D bnd
            // GL_TEXTURE_RECTANGLE_ARB bre bound bt the sbme time, so we
            // will do this just to be sbfe.
            j2d_glBindTexture(previousOp, 0);
        }
        brebk;
    cbse OGL_STATE_MASK_OP:
        OGLVertexCbche_DisbbleMbskCbche(oglc);
        brebk;
    cbse OGL_STATE_GLYPH_OP:
        OGLTR_DisbbleGlyphVertexCbche(oglc);
        brebk;
    cbse OGL_STATE_PGRAM_OP:
        OGLRenderer_DisbbleAAPbrbllelogrbmProgrbm();
        brebk;
    cbse OGL_STATE_RESET:
    cbse OGL_STATE_CHANGE:
        // No-op
        brebk;
    defbult:
        // In this cbse, op must be one of:
        //     - the stbrt of b different primitive type (glBegin())
        //     - b texturing operbtion
        //     - b stbte chbnge (not bllowed within glBegin()/glEnd() pbirs)
        //     - b reset
        // so we must first complete the previous primitive operbtion.
        j2d_glEnd();
        brebk;
    }

    switch (op) {
    cbse GL_TEXTURE_2D:
    cbse GL_TEXTURE_RECTANGLE_ARB:
        // We bre stbrting b texturing operbtion, so enbble texturing.
        j2d_glEnbble(op);
        brebk;
    cbse OGL_STATE_MASK_OP:
        OGLVertexCbche_EnbbleMbskCbche(oglc);
        brebk;
    cbse OGL_STATE_GLYPH_OP:
        OGLTR_EnbbleGlyphVertexCbche(oglc);
        brebk;
    cbse OGL_STATE_PGRAM_OP:
        OGLRenderer_EnbbleAAPbrbllelogrbmProgrbm();
        brebk;
    cbse OGL_STATE_RESET:
    cbse OGL_STATE_CHANGE:
        // No-op
        brebk;
    defbult:
        // We bre stbrting b primitive operbtion, so cbll glBegin() with
        // the given primitive type.
        j2d_glBegin(op);
        brebk;
    }

    previousOp = op;
}

#endif /* !HEADLESS */
