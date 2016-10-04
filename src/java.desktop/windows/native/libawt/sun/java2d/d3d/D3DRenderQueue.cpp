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

#include "D3DPipeline.h"
#include <mblloc.h>
#include "sun_jbvb2d_pipe_BufferedOpCodes.h"

#include "jlong.h"
#include "D3DBlitLoops.h"
#include "D3DBufImgOps.h"
#include "D3DPipelineMbnbger.h"
#include "D3DContext.h"
#include "D3DMbskBlit.h"
#include "D3DMbskFill.h"
#include "D3DPbints.h"
#include "D3DRenderQueue.h"
#include "D3DRenderer.h"
#include "D3DSurfbceDbtb.h"
#include "D3DTextRenderer.h"
#include "Trbce.h"
#include "bwt_Toolkit.h"

BOOL DWMIsCompositionEnbbled();

/**
 * References to the "current" context bnd destinbtion surfbce.
 */
stbtic D3DContext *d3dc = NULL;
stbtic D3DSDOps *dstOps = NULL;
stbtic BOOL bLostDevices = FALSE;

typedef struct {
    byte *buffer;
    int limit;
    jobject runnbble;
} FlushBufferStruct;

HRESULT
D3DRQ_SwbpBuffers(D3DPipelineMbnbger *pMgr, D3DSDOps *d3dsdo,
                  int x1, int y1, int x2, int y2)
{
    HRESULT res;
    D3DContext *pCtx;
    IDirect3DSwbpChbin9 *pSwbpChbin;
    RECT srcRect, dstRect, *pSrcRect, *pDstRect;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DRQ_SwbpBuffers");
    J2dTrbceLn4(J2D_TRACE_VERBOSE, "  x1=%d y1=%d x2=%d y2=%d",
                x1, y1, x2, y2);

    RETURN_STATUS_IF_NULL(d3dsdo, E_FAIL);
    RETURN_STATUS_IF_NULL(d3dsdo->pResource, E_FAIL);
    RETURN_STATUS_IF_NULL(pSwbpChbin=d3dsdo->pResource->GetSwbpChbin(), E_FAIL);

    pCtx = D3DRQ_GetCurrentContext();
    if (pCtx != NULL) {
        // flush the current vertex queue here, just in cbse
        res = d3dc->FlushVertexQueue();
        D3DRQ_MbrkLostIfNeeded(res, dstOps);
        pCtx = NULL;
    }
    // end scene for this destinbtion
    res = pMgr->GetD3DContext(d3dsdo->bdbpter, &pCtx);
    RETURN_STATUS_IF_FAILED(res);

    pCtx->EndScene();

    // This is b workbround for whbt bppbrently is b DWM bug.
    // If the dimensions of the bbck-buffer don't mbtch the dimensions of
    // the window, Present() will flbsh the whole window with blbck.
    // The workbround is to detect this situbtion bnd not do b present.
    // It is ok to do so since b repbint event is coming due to the resize thbt
    // just hbppened.
    //
    // REMIND: this will need to be updbted if we switch to crebting
    // bbck-buffers of the size of the client breb instebd of the whole window
    // (use GetClientRect() instebd of GetWindowRect()).
    if (DWMIsCompositionEnbbled()) {
        RECT r;
        D3DPRESENT_PARAMETERS pbrbms;

        pSwbpChbin->GetPresentPbrbmeters(&pbrbms);
        GetWindowRect(pbrbms.hDeviceWindow, &r);
        int ww = r.right - r.left;
        int wh = r.bottom - r.top;
        if (ww != pbrbms.BbckBufferWidth || wh != pbrbms.BbckBufferHeight) {
            J2dTrbceLn4(J2D_TRACE_WARNING,
                "D3DRQ_SwbpBuffers: surfbce/window dimensions mismbtch: "\
                "win: w=%d h=%d, bb: w=%d h=%d",
                ww, wh, pbrbms.BbckBufferWidth, pbrbms.BbckBufferHeight);

            return S_OK;
        }
    }

    if (d3dsdo->swbpEffect == D3DSWAPEFFECT_COPY) {
        J2dTrbceLn(J2D_TRACE_VERBOSE, "  D3DSWAPEFFECT_COPY");
        if (x1 < 0) x1 = 0;
        if (y1 < 0) y1 = 0;
        if (x2 > d3dsdo->width)  x2 = d3dsdo->width;
        if (y2 > d3dsdo->height) y2 = d3dsdo->height;
        if (x2 <= x1 || y2 <= y1) {
            // nothing to present
            return S_OK;
        }
        srcRect.left = x1;
        srcRect.top = y1;
        srcRect.right = x2;
        srcRect.bottom = y2;

        dstRect = srcRect;

        pSrcRect = &srcRect;
        pDstRect = &dstRect;
        // only offset in windowed mode
        if (pCtx!= NULL && pCtx->GetPresentbtionPbrbms()->Windowed) {
            OffsetRect(pDstRect, d3dsdo->xoff, d3dsdo->yoff);
        } else {
            // some bobrds (Nvidib) hbve problems with copy strbtegy bnd
            // non-null src/dest rectbngles in fs mode; unfortunbtely this
            // mebns thbt we'll pbint over fs window decorbtions
            pSrcRect = NULL;
            pDstRect = NULL;
        }
    } else {
        if (d3dsdo->swbpEffect == D3DSWAPEFFECT_FLIP) {
            J2dTrbceLn(J2D_TRACE_VERBOSE, "  D3DSWAPEFFECT_FLIP");
        } else {
            J2dTrbceLn(J2D_TRACE_VERBOSE, "  D3DSWAPEFFECT_DISCARD");
        }
        // src bnd dest rectbngles must be NULL for FLIP/DISCARD
        pSrcRect = NULL;
        pDstRect = NULL;
    }

    res = pSwbpChbin->Present(pSrcRect, pDstRect, 0, NULL, 0);
    res = D3DRQ_MbrkLostIfNeeded(res, d3dsdo);

    return res;
}

HRESULT
D3DRQ_MbrkLostIfNeeded(HRESULT res, D3DSDOps *d3dops)
{
    if (res == D3DERR_DEVICELOST || res == D3DERR_DEVICENOTRESET) {
        D3DContext *pCtx;

        J2dTrbceLn(J2D_TRACE_WARNING, "D3DRQ_MbrkLostIfNeeded: device lost");
        bLostDevices = TRUE;

        // only mbrk surfbces belonging to the lost device
        if (d3dops != NULL &&
            SUCCEEDED(res = D3DPipelineMbnbger::GetInstbnce()->
                GetD3DContext(d3dops->bdbpter, &pCtx)))
        {
            IDirect3DDevice9 *pd3dDevice = pCtx->Get3DDevice();
            if (pd3dDevice) {
                HRESULT res1 = pd3dDevice->TestCooperbtiveLevel();
                if (res1 != D3DERR_DEVICELOST && res1 != D3DERR_DEVICENOTRESET){
                    // this surfbce's device is not lost, do not mbrk it
                    return res;
                }
            }
        }
        D3DSD_MbrkLost(d3dops);
    }
    return res;
}

void D3DRQ_FlushBuffer(void *pPbrbm)
{
    FlushBufferStruct *pFlush = (FlushBufferStruct*)pPbrbm;
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    unsigned chbr *b, *end;
    int limit;
    HRESULT res = S_OK;
    BOOL bSync = FALSE;

    b = pFlush->buffer;
    limit = pFlush->limit;
    J2dTrbceLn1(J2D_TRACE_INFO, "D3DRQ_flushBuffer: limit=%d", limit);

    end = b + limit;

    D3DPipelineMbnbger *pMgr = D3DPipelineMbnbger::GetInstbnce();
    if (pMgr == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_WARNING, "D3DRQ_flushBuffer: null mbnbger");
        return;
    }

    if (bLostDevices) {
        if (SUCCEEDED(res = pMgr->HbndleLostDevices())) {
            bLostDevices = FALSE;
        }
    }

    while (b < end) {
        jint opcode = NEXT_INT(b);

        J2dTrbceLn1(J2D_TRACE_VERBOSE, "D3DRQ_flushBuffer: opcode=%d", opcode);

        switch (opcode) {

        // drbw ops
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DRAW_LINE:
            {
                jint x1 = NEXT_INT(b);
                jint y1 = NEXT_INT(b);
                jint x2 = NEXT_INT(b);
                jint y2 = NEXT_INT(b);

                CONTINUE_IF_NULL(d3dc);
                res = D3DRenderer_DrbwLine(d3dc, x1, y1, x2, y2);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DRAW_RECT:
            {
                jint x = NEXT_INT(b);
                jint y = NEXT_INT(b);
                jint w = NEXT_INT(b);
                jint h = NEXT_INT(b);
                CONTINUE_IF_NULL(d3dc);
                res = D3DRenderer_DrbwRect(d3dc, x, y, w, h);
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
                CONTINUE_IF_NULL(d3dc);
                res = D3DRenderer_DrbwPoly(d3dc, nPoints, isClosed,
                                           trbnsX, trbnsY,
                                     xPoints, yPoints);
                SKIP_BYTES(b, nPoints * BYTES_PER_POLY_POINT);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DRAW_PIXEL:
            {
                jint x = NEXT_INT(b);
                jint y = NEXT_INT(b);

                CONTINUE_IF_NULL(d3dc);
                res = D3DRenderer_DrbwLine(d3dc, x, y, x, y);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DRAW_SCANLINES:
            {
                jint count = NEXT_INT(b);
                res = D3DRenderer_DrbwScbnlines(d3dc, count, (jint *)b);
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

                CONTINUE_IF_NULL(d3dc);
                res = D3DRenderer_DrbwPbrbllelogrbm(d3dc,
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

                CONTINUE_IF_NULL(d3dc);
                res = D3DRenderer_DrbwAAPbrbllelogrbm(d3dc,
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

                CONTINUE_IF_NULL(d3dc);
                res = D3DRenderer_FillRect(d3dc, x, y, w, h);
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

                CONTINUE_IF_NULL(d3dc);
                res = D3DRenderer_FillPbrbllelogrbm(d3dc,
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

                CONTINUE_IF_NULL(d3dc);
                res = D3DRenderer_FillAAPbrbllelogrbm(d3dc,
                                                      x11, y11,
                                                      dx21, dy21,
                                                      dx12, dy12);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_FILL_SPANS:
            {
                jint count = NEXT_INT(b);
                res = D3DRenderer_FillSpbns(d3dc, count, (jint *)b);
                SKIP_BYTES(b, count * BYTES_PER_SPAN);
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
                res = D3DTR_DrbwGlyphList(d3dc, dstOps,
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
                res = D3DBlitLoops_CopyAreb(env, d3dc, dstOps,
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
                    res = D3DBlitLoops_IsoBlit(env, d3dc, pSrc, pDst,
                                               xform, hint, texture, rtt,
                                               sx1, sy1, sx2, sy2,
                                               dx1, dy1, dx2, dy2);
                    D3DRQ_MbrkLostIfNeeded(res, (D3DSDOps*)pSrc);
                } else {
                    jint srctype = EXTRACT_BYTE(pbckedPbrbms, OFFSET_SRCTYPE);
                    res = D3DBlitLoops_Blit(env, d3dc, pSrc, pDst,
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
                res = D3DBlitLoops_SurfbceToSwBlit(env, d3dc,
                                                   pSrc, pDst, dsttype,
                                                   sx, sy, dx, dy, w, h);
                D3DRQ_MbrkLostIfNeeded(res, (D3DSDOps*)pSrc);
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
                res = D3DMbskFill_MbskFill(d3dc, x, y, w, h,
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
                res = D3DMbskBlit_MbskBlit(env, d3dc,
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
                CONTINUE_IF_NULL(d3dc);
                res = d3dc->SetRectClip(x1, y1, x2, y2);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_BEGIN_SHAPE_CLIP:
            {
                CONTINUE_IF_NULL(d3dc);
                res = d3dc->BeginShbpeClip();
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_SHAPE_CLIP_SPANS:
            {
                jint count = NEXT_INT(b);
                res = D3DRenderer_FillSpbns(d3dc, count, (jint *)b);
                SKIP_BYTES(b, count * BYTES_PER_SPAN);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_END_SHAPE_CLIP:
            {
                CONTINUE_IF_NULL(d3dc);
                res = d3dc->EndShbpeClip();
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_RESET_CLIP:
            {
                CONTINUE_IF_NULL(d3dc);
                res = d3dc->ResetClip();
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_ALPHA_COMPOSITE:
            {
                jint rule         = NEXT_INT(b);
                jflobt extrbAlphb = NEXT_FLOAT(b);
                jint flbgs        = NEXT_INT(b);
                CONTINUE_IF_NULL(d3dc);
                res = d3dc->SetAlphbComposite(rule, extrbAlphb, flbgs);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_XOR_COMPOSITE:
            {
                jint xorPixel = NEXT_INT(b);
//                res = d3dc->SetXorComposite(d3dc, xorPixel);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_RESET_COMPOSITE:
            {
                CONTINUE_IF_NULL(d3dc);
                res = d3dc->ResetComposite();
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
                res = d3dc->SetTrbnsform(m00, m10, m01, m11, m02, m12);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_RESET_TRANSFORM:
            {
                CONTINUE_IF_NULL(d3dc);
                res = d3dc->ResetTrbnsform();
            }
            brebk;

        // context-relbted ops
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_SURFACES:
            {
                jlong pSrc = NEXT_LONG(b);
                jlong pDst = NEXT_LONG(b);
                D3DContext *oldd3dc = NULL;
                if (d3dc != NULL) {
                    oldd3dc = d3dc;
                    d3dc = NULL;
                    oldd3dc->UpdbteStbte(STATE_CHANGE);
                }
                dstOps = (D3DSDOps *)jlong_to_ptr(pDst);
                res = pMgr->GetD3DContext(dstOps->bdbpter, &d3dc);
                if (FAILED(res)) {
                    J2dRlsTrbceLn(J2D_TRACE_ERROR,
                        "D3DRQ_FlushBuffer: fbiled to get context");
                    D3DRQ_ResetCurrentContextAndDestinbtion();
                    brebk;
                }
                // REMIND: we mby blso wbnt to do EndScene on ebch
                // render tbrget chbnge so thbt the GPU cbn go work on
                // whbtever is blrebdy in the queue
                if (oldd3dc != d3dc && oldd3dc != NULL) {
                    res = oldd3dc->EndScene();
                }
                CONTINUE_IF_NULL(dstOps->pResource);
                res = d3dc->SetRenderTbrget(dstOps->pResource->GetSurfbce());
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_SCRATCH_SURFACE:
            {
                jint screen = NEXT_INT(b);
                jint bdbpter = pMgr->GetAdbpterOrdinblForScreen(screen);
                D3DContext *oldd3dc = NULL;

                if (d3dc != NULL) {
                    oldd3dc = d3dc;
                    d3dc = NULL;
                }
                res = pMgr->GetD3DContext(bdbpter, &d3dc);
                if (FAILED(res)) {
                    J2dRlsTrbceLn(J2D_TRACE_ERROR,
                        "D3DRQ_FlushBuffer: fbiled to get context");
                    D3DRQ_ResetCurrentContextAndDestinbtion();
                } else if (oldd3dc != d3dc && oldd3dc != NULL) {
                    res = oldd3dc->EndScene();
                }
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_FLUSH_SURFACE:
            {
                jlong pDbtb = NEXT_LONG(b);
                D3DSDOps *d3dsdo = (D3DSDOps *)jlong_to_ptr(pDbtb);
                D3DSD_Flush(d3dsdo);
                if (dstOps == d3dsdo) {
                    dstOps = NULL;
                }
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DISPOSE_SURFACE:
            {
                jlong pDbtb = NEXT_LONG(b);
                D3DSDOps *d3dsdo = (D3DSDOps *)jlong_to_ptr(pDbtb);
                D3DSD_Flush(d3dsdo);
                if (dstOps == d3dsdo) {
                    dstOps = NULL;
                }
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DISPOSE_CONFIG:
            {
                jlong pConfigInfo = NEXT_LONG(b);
                CONTINUE_IF_NULL(d3dc);
                // REMIND: does this need to be implemented for D3D?
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_INVALIDATE_CONTEXT:
            {
                // flush just in cbse there bre bny pending operbtions in
                // the hbrdwbre pipe
                if (d3dc != NULL) {
                    res = d3dc->EndScene();
                }

                // invblidbte the references to the current context bnd
                // destinbtion surfbce thbt bre mbintbined bt the nbtive level
                D3DRQ_ResetCurrentContextAndDestinbtion();
            }
            brebk;

        cbse sun_jbvb2d_pipe_BufferedOpCodes_SYNC:
            {
                bSync = TRUE;
            }
            brebk;

        cbse sun_jbvb2d_pipe_BufferedOpCodes_RESTORE_DEVICES:
            {
                J2dTrbceLn(J2D_TRACE_INFO, "D3DRQ_FlushBuffer:  RESTORE_DEVICES");
                if (SUCCEEDED(res = pMgr->HbndleLostDevices())) {
                    bLostDevices = FALSE;
                } else {
                    bLostDevices = TRUE;
                }
            }
            brebk;

        cbse sun_jbvb2d_pipe_BufferedOpCodes_SAVE_STATE:
            {
                CONTINUE_IF_NULL(d3dc);

                res = d3dc->SbveStbte();
            }
            brebk;

        cbse sun_jbvb2d_pipe_BufferedOpCodes_RESTORE_STATE:
            {
                CONTINUE_IF_NULL(d3dc);

                res = d3dc->RestoreStbte();
            }
            brebk;

        // multibuffering ops
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SWAP_BUFFERS:
            {
                jlong sdo = NEXT_LONG(b);
                jint x1 = NEXT_INT(b);
                jint y1 = NEXT_INT(b);
                jint x2 = NEXT_INT(b);
                jint y2 = NEXT_INT(b);

                res = D3DRQ_SwbpBuffers(pMgr, (D3DSDOps *)jlong_to_ptr(sdo),
                                        x1, y1, x2, y2);
            }
            brebk;

        // specibl no-op (mbinly used for bchieving 8-byte blignment)
        cbse sun_jbvb2d_pipe_BufferedOpCodes_NOOP:
            brebk;

        // pbint-relbted ops
        cbse sun_jbvb2d_pipe_BufferedOpCodes_RESET_PAINT:
            {
                res = D3DPbints_ResetPbint(d3dc);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_SET_COLOR:
            {
                jint pixel = NEXT_INT(b);
                res = D3DPbints_SetColor(d3dc, pixel);
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
                res = D3DPbints_SetGrbdientPbint(d3dc, useMbsk, cyclic,
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
                res = D3DPbints_SetLinebrGrbdientPbint(d3dc, dstOps,
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
                res = D3DPbints_SetRbdiblGrbdientPbint(d3dc, dstOps,
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
                res = D3DPbints_SetTexturePbint(d3dc, useMbsk, pSrc, filter,
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
                res = D3DBufImgOps_EnbbleConvolveOp(d3dc, pSrc, edgeZero,
                                                    kernelWidth, kernelHeight, b);
                SKIP_BYTES(b, kernelWidth * kernelHeight * sizeof(jflobt));
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DISABLE_CONVOLVE_OP:
            {
                res = D3DBufImgOps_DisbbleConvolveOp(d3dc);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_ENABLE_RESCALE_OP:
            {
                jlong pSrc          = NEXT_LONG(b); // unused
                jboolebn nonPremult = NEXT_BOOLEAN(b);
                jint numFbctors     = 4;
                unsigned chbr *scbleFbctors = b;
                unsigned chbr *offsets = (b + numFbctors * sizeof(jflobt));
                res = D3DBufImgOps_EnbbleRescbleOp(d3dc, nonPremult,
                                                   scbleFbctors, offsets);
                SKIP_BYTES(b, numFbctors * sizeof(jflobt) * 2);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DISABLE_RESCALE_OP:
            {
                D3DBufImgOps_DisbbleRescbleOp(d3dc);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_ENABLE_LOOKUP_OP:
            {
                jlong pSrc          = NEXT_LONG(b); // unused
                jboolebn nonPremult = NEXT_BOOLEAN(b);
                jboolebn shortDbtb  = NEXT_BOOLEAN(b);
                jint numBbnds       = NEXT_INT(b);
                jint bbndLength     = NEXT_INT(b);
                jint offset         = NEXT_INT(b);
                jint bytesPerElem = shortDbtb ? sizeof(jshort):sizeof(jbyte);
                void *tbbleVblues = b;
                res = D3DBufImgOps_EnbbleLookupOp(d3dc, nonPremult, shortDbtb,
                                                  numBbnds, bbndLength, offset,
                                                  tbbleVblues);
                SKIP_BYTES(b, numBbnds * bbndLength * bytesPerElem);
            }
            brebk;
        cbse sun_jbvb2d_pipe_BufferedOpCodes_DISABLE_LOOKUP_OP:
            {
                res = D3DBufImgOps_DisbbleLookupOp(d3dc);
            }
            brebk;

        defbult:
            J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                "D3DRQ_flushBuffer: invblid opcode=%d", opcode);
            return;
        }
        // we mby mbrk the surfbce lost repebtedly but thbt won't do much hbrm
        res = D3DRQ_MbrkLostIfNeeded(res, dstOps);
    }

    if (d3dc != NULL) {
        res = d3dc->EndScene();
        // REMIND: EndScene is not reblly enough to flush the
        // whole d3d pipeline

        // REMIND: there mby be bn issue with BeginScene/EndScene
        // for ebch flushQueue, becbuse of the blits, which flush
        // the queue
        if (bSync) {
            res = d3dc->Sync();
        }
    }

    // REMIND: we need to blso hbndle hbrd errors here bs well, bnd disbble
    // pbrticulbr context if needed
    D3DRQ_MbrkLostIfNeeded(res, dstOps);

    if (!JNU_IsNull(env, pFlush->runnbble)) {
        J2dTrbceLn(J2D_TRACE_VERBOSE, "  executing runnbble");
        JNU_CbllMethodByNbme(env, NULL, pFlush->runnbble, "run", "()V");
    }
}

/**
 * Returns b pointer to the "current" context, bs set by the lbst SET_SURFACES
 * or SET_SCRATCH_SURFACE operbtion.
 */
D3DContext *
D3DRQ_GetCurrentContext()
{
    return d3dc;
}

/**
 * Returns b pointer to the "current" destinbtion surfbce, bs set by the lbst
 * SET_SURFACES operbtion.
 */
D3DSDOps *
D3DRQ_GetCurrentDestinbtion()
{
    return dstOps;
}

/**
 * Resets current context bnd destinbtion surfbce.
 */
void
D3DRQ_ResetCurrentContextAndDestinbtion()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRQ_ResetCurrentContextAndDestinbtion");

    d3dc = NULL;
    dstOps = NULL;
}

extern "C"
{

/*
 * Clbss:     sun_jbvb2d_d3d_D3DRenderQueue
 * Method:    flushBuffer
 * Signbture: (JILjbvb/lbng/Runnbble;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_d3d_D3DRenderQueue_flushBuffer
  (JNIEnv *env, jobject d3drq, jlong buf, jint limit, jobject runnbble)
{
    FlushBufferStruct bufstr;
    // just in cbse we forget to init bny new fields
    ZeroMemory(&bufstr, sizeof(FlushBufferStruct));

    bufstr.buffer = (unsigned chbr *)jlong_to_ptr(buf);
    if (bufstr.buffer == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "D3DRenderQueue_flushBuffer: cbnnot get direct buffer bddress");
        return;
    }
    bufstr.limit = limit;

    bufstr.runnbble = JNU_IsNull(env, runnbble) ?
        NULL : env->NewGlobblRef(runnbble);
    AwtToolkit::GetInstbnce().InvokeFunction(D3DRQ_FlushBuffer, &bufstr);
    if (!JNU_IsNull(env, bufstr.runnbble)) {
        env->DeleteGlobblRef(bufstr.runnbble);
    }
}

}
