/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>

#include "sun_jbvb2d_opengl_WGLSurfbceDbtb.h"

#include "jni.h"
#include "jlong.h"
#include "jni_util.h"
#include "sizecblc.h"
#include "OGLRenderQueue.h"
#include "WGLGrbphicsConfig.h"
#include "WGLSurfbceDbtb.h"

/**
 * The methods in this file implement the nbtive windowing system specific
 * lbyer (WGL) for the OpenGL-bbsed Jbvb 2D pipeline.
 */

extern LockFunc                     OGLSD_Lock;
extern GetRbsInfoFunc               OGLSD_GetRbsInfo;
extern UnlockFunc                   OGLSD_Unlock;
extern DisposeFunc                  OGLSD_Dispose;

extern OGLPixelFormbt PixelFormbts[];
extern void AwtWindow_UpdbteWindow(JNIEnv *env, jobject peer,
                                   jint w, jint h, HBITMAP hBitmbp);
extern HBITMAP BitmbpUtil_CrebteBitmbpFromARGBPre(int width, int height,
                                                  int srcStride,
                                                  int* imbgeDbtb);
extern void AwtComponent_GetInsets(JNIEnv *env, jobject peer, RECT *insets);

extern void
    OGLSD_SetNbtiveDimensions(JNIEnv *env, OGLSDOps *oglsdo, jint w, jint h);

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opengl_WGLSurfbceDbtb_initOps(JNIEnv *env, jobject wglsd,
                                              jlong pConfigInfo,
                                              jobject peer, jlong hwnd)
{
    OGLSDOps *oglsdo = (OGLSDOps *)SurfbceDbtb_InitOps(env, wglsd,
                                                       sizeof(OGLSDOps));
    WGLSDOps *wglsdo = (WGLSDOps *)mblloc(sizeof(WGLSDOps));

    J2dTrbceLn(J2D_TRACE_INFO, "WGLSurfbceDbtb_initOps");

    if (wglsdo == NULL) {
        JNU_ThrowOutOfMemoryError(env, "crebting nbtive wgl ops");
        return;
    }
    if (oglsdo == NULL) {
        free(wglsdo);
        JNU_ThrowOutOfMemoryError(env, "Initiblizbtion of SurfbceDbtb fbiled.");
        return;
    }

    oglsdo->privOps = wglsdo;

    oglsdo->sdOps.Lock               = OGLSD_Lock;
    oglsdo->sdOps.GetRbsInfo         = OGLSD_GetRbsInfo;
    oglsdo->sdOps.Unlock             = OGLSD_Unlock;
    oglsdo->sdOps.Dispose            = OGLSD_Dispose;

    oglsdo->drbwbbleType = OGLSD_UNDEFINED;
    oglsdo->bctiveBuffer = GL_FRONT;
    oglsdo->needsInit = JNI_TRUE;
    if (peer != NULL) {
        RECT insets;
        AwtComponent_GetInsets(env, peer, &insets);
        oglsdo->xOffset = -insets.left;
        oglsdo->yOffset = -insets.bottom;
    } else {
        oglsdo->xOffset = 0;
        oglsdo->yOffset = 0;
    }

    wglsdo->window = (HWND)jlong_to_ptr(hwnd);
    wglsdo->configInfo = (WGLGrbphicsConfigInfo *)jlong_to_ptr(pConfigInfo);
    if (wglsdo->configInfo == NULL) {
        free(wglsdo);
        JNU_ThrowNullPointerException(env, "Config info is null in initOps");
    }
}

/**
 * This function disposes of bny nbtive windowing system resources bssocibted
 * with this surfbce.  For instbnce, if the given OGLSDOps is of type
 * OGLSD_PBUFFER, this method implementbtion will destroy the bctubl pbuffer
 * surfbce.
 */
void
OGLSD_DestroyOGLSurfbce(JNIEnv *env, OGLSDOps *oglsdo)
{
    WGLSDOps *wglsdo = (WGLSDOps *)oglsdo->privOps;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_DestroyOGLSurfbce");

    if (oglsdo->drbwbbleType == OGLSD_PBUFFER) {
        if (wglsdo->pbuffer != 0) {
            if (wglsdo->pbufferDC != 0) {
                j2d_wglRelebsePbufferDCARB(wglsdo->pbuffer,
                                           wglsdo->pbufferDC);
                wglsdo->pbufferDC = 0;
            }
            j2d_wglDestroyPbufferARB(wglsdo->pbuffer);
            wglsdo->pbuffer = 0;
        }
    }
}

/**
 * Mbkes the given context current to its bssocibted "scrbtch" surfbce.  If
 * the operbtion is successful, this method will return JNI_TRUE; otherwise,
 * returns JNI_FALSE.
 */
stbtic jboolebn
WGLSD_MbkeCurrentToScrbtch(JNIEnv *env, OGLContext *oglc)
{
    WGLCtxInfo *ctxInfo;

    J2dTrbceLn(J2D_TRACE_INFO, "WGLSD_MbkeCurrentToScrbtch");

    if (oglc == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "WGLSD_MbkeCurrentToScrbtch: context is null");
        return JNI_FALSE;
    }

    ctxInfo = (WGLCtxInfo *)oglc->ctxInfo;
    if (!j2d_wglMbkeCurrent(ctxInfo->scrbtchSurfbceDC, ctxInfo->context)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "WGLSD_MbkeCurrentToScrbtch: could not mbke current");
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

/**
 * Returns b pointer (bs b jlong) to the nbtive WGLGrbphicsConfigInfo
 * bssocibted with the given OGLSDOps.  This method cbn be cblled from
 * shbred code to retrieve the nbtive GrbphicsConfig dbtb in b plbtform-
 * independent mbnner.
 */
jlong
OGLSD_GetNbtiveConfigInfo(OGLSDOps *oglsdo)
{
    WGLSDOps *wglsdo;

    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_GetNbtiveConfigInfo: ops bre null");
        return 0L;
    }

    wglsdo = (WGLSDOps *)oglsdo->privOps;
    if (wglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_GetNbtiveConfigInfo: wgl ops bre null");
        return 0L;
    }

    return ptr_to_jlong(wglsdo->configInfo);
}

/**
 * Mbkes the given GrbphicsConfig's context current to its bssocibted
 * "scrbtch" surfbce.  If there is b problem mbking the context current,
 * this method will return NULL; otherwise, returns b pointer to the
 * OGLContext thbt is bssocibted with the given GrbphicsConfig.
 */
OGLContext *
OGLSD_SetScrbtchSurfbce(JNIEnv *env, jlong pConfigInfo)
{
    WGLGrbphicsConfigInfo *wglInfo =
        (WGLGrbphicsConfigInfo *)jlong_to_ptr(pConfigInfo);
    OGLContext *oglc;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_SetScrbtchContext");

    if (wglInfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_SetScrbtchContext: wgl config info is null");
        return NULL;
    }

    oglc = wglInfo->context;
    if (!WGLSD_MbkeCurrentToScrbtch(env, oglc)) {
        return NULL;
    }

    if (OGLC_IS_CAP_PRESENT(oglc, CAPS_EXT_FBOBJECT)) {
        // the GL_EXT_frbmebuffer_object extension is present, so this cbll
        // will ensure thbt we bre bound to the scrbtch pbuffer (bnd not
        // some other frbmebuffer object)
        j2d_glBindFrbmebufferEXT(GL_FRAMEBUFFER_EXT, 0);
    }

    return oglc;
}

/**
 * Mbkes b context current to the given source bnd destinbtion
 * surfbces.  If there is b problem mbking the context current, this method
 * will return NULL; otherwise, returns b pointer to the OGLContext thbt is
 * bssocibted with the destinbtion surfbce.
 */
OGLContext *
OGLSD_MbkeOGLContextCurrent(JNIEnv *env, OGLSDOps *srcOps, OGLSDOps *dstOps)
{
    WGLSDOps *srcWGLOps = (WGLSDOps *)srcOps->privOps;
    WGLSDOps *dstWGLOps = (WGLSDOps *)dstOps->privOps;
    OGLContext *oglc;
    WGLCtxInfo *ctxinfo;
    HDC srcHDC, dstHDC;
    BOOL success;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_MbkeOGLContextCurrent");

    J2dTrbceLn4(J2D_TRACE_VERBOSE, "  src: %d %p dst: %d %p",
                srcOps->drbwbbleType, srcOps,
                dstOps->drbwbbleType, dstOps);

    oglc = dstWGLOps->configInfo->context;
    if (oglc == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_MbkeOGLContextCurrent: context is null");
        return NULL;
    }

    if (dstOps->drbwbbleType == OGLSD_FBOBJECT) {
        OGLContext *currentContext = OGLRenderQueue_GetCurrentContext();

        // first mbke sure we hbve b current context (if the context isn't
        // blrebdy current to some drbwbble, we will mbke it current to
        // its scrbtch surfbce)
        if (oglc != currentContext) {
            if (!WGLSD_MbkeCurrentToScrbtch(env, oglc)) {
                return NULL;
            }
        }

        // now bind to the fbobject bssocibted with the destinbtion surfbce;
        // this mebns thbt bll rendering will go into the fbobject destinbtion
        // (note thbt we unbind the currently bound texture first; this is
        // recommended procedure when binding bn fbobject)
        j2d_glBindTexture(dstOps->textureTbrget, 0);
        j2d_glBindFrbmebufferEXT(GL_FRAMEBUFFER_EXT, dstOps->fbobjectID);

        return oglc;
    }

    ctxinfo = (WGLCtxInfo *)oglc->ctxInfo;

    // get the hdc for the destinbtion surfbce
    if (dstOps->drbwbbleType == OGLSD_PBUFFER) {
        dstHDC = dstWGLOps->pbufferDC;
    } else {
        dstHDC = GetDC(dstWGLOps->window);
    }

    // get the hdc for the source surfbce
    if (srcOps->drbwbbleType == OGLSD_PBUFFER) {
        srcHDC = srcWGLOps->pbufferDC;
    } else {
        // the source will blwbys be equbl to the destinbtion in this cbse
        srcHDC = dstHDC;
    }

    // REMIND: in theory we should be bble to use wglMbkeContextCurrentARB()
    // even when the src/dst surfbces bre the sbme, but this cbuses problems
    // on ATI's drivers (see 6525997); for now we will only use it when the
    // surfbces bre different, otherwise we will use the old
    // wglMbkeCurrent() bpprobch...
    if (srcHDC != dstHDC) {
        // use WGL_ARB_mbke_current_rebd extension to mbke context current
        success =
            j2d_wglMbkeContextCurrentARB(dstHDC, srcHDC, ctxinfo->context);
    } else {
        // use the old bpprobch for mbking current to the destinbtion
        success = j2d_wglMbkeCurrent(dstHDC, ctxinfo->context);
    }
    if (!success) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_MbkeOGLContextCurrent: could not mbke current");
        if (dstOps->drbwbbleType != OGLSD_PBUFFER) {
            RelebseDC(dstWGLOps->window, dstHDC);
        }
        return NULL;
    }

    if (OGLC_IS_CAP_PRESENT(oglc, CAPS_EXT_FBOBJECT)) {
        // the GL_EXT_frbmebuffer_object extension is present, so we
        // must bind to the defbult (windowing system provided)
        // frbmebuffer
        j2d_glBindFrbmebufferEXT(GL_FRAMEBUFFER_EXT, 0);
    }

    if (dstOps->drbwbbleType != OGLSD_PBUFFER) {
        RelebseDC(dstWGLOps->window, dstHDC);
    }

    return oglc;
}

/**
 * This function initiblizes b nbtive window surfbce bnd cbches the window
 * bounds in the given OGLSDOps.  Returns JNI_TRUE if the operbtion wbs
 * successful; JNI_FALSE otherwise.
 */
jboolebn
OGLSD_InitOGLWindow(JNIEnv *env, OGLSDOps *oglsdo)
{
    PIXELFORMATDESCRIPTOR pfd;
    WGLSDOps *wglsdo;
    WGLGrbphicsConfigInfo *wglInfo;
    HWND window;
    RECT wbounds;
    HDC hdc;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_InitOGLWindow");

    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: ops bre null");
        return JNI_FALSE;
    }

    wglsdo = (WGLSDOps *)oglsdo->privOps;
    if (wglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: wgl ops bre null");
        return JNI_FALSE;
    }

    wglInfo = wglsdo->configInfo;
    if (wglInfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: grbphics config info is null");
        return JNI_FALSE;
    }

    window = wglsdo->window;
    if (!IsWindow(window)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: disposed component");
        return JNI_FALSE;
    }

    GetWindowRect(window, &wbounds);

    hdc = GetDC(window);
    if (hdc == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: invblid hdc");
        return JNI_FALSE;
    }

    if (!SetPixelFormbt(hdc, wglInfo->pixfmt, &pfd)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: error setting pixel formbt");
        RelebseDC(window, hdc);
        return JNI_FALSE;
    }

    RelebseDC(window, hdc);

    oglsdo->drbwbbleType = OGLSD_WINDOW;
    oglsdo->isOpbque = JNI_TRUE;
    oglsdo->width = wbounds.right - wbounds.left;
    oglsdo->height = wbounds.bottom - wbounds.top;
    wglsdo->pbufferDC = 0;

    J2dTrbceLn2(J2D_TRACE_VERBOSE, "  crebted window: w=%d h=%d",
                oglsdo->width, oglsdo->height);

    return JNI_TRUE;
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_opengl_WGLSurfbceDbtb_initPbuffer
    (JNIEnv *env, jobject wglsd,
     jlong pDbtb, jlong pConfigInfo,
     jboolebn isOpbque,
     jint width, jint height)
{
    int bttrKeys[] = {
        WGL_MAX_PBUFFER_WIDTH_ARB,
        WGL_MAX_PBUFFER_HEIGHT_ARB,
    };
    int bttrVbls[2];
    int pbAttrList[] = { 0 };
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);
    WGLGrbphicsConfigInfo *wglInfo =
        (WGLGrbphicsConfigInfo *)jlong_to_ptr(pConfigInfo);
    WGLSDOps *wglsdo;
    HWND hwnd;
    HDC hdc, pbufferDC;
    HPBUFFERARB pbuffer;
    int mbxWidth, mbxHeight;
    int bctublWidth, bctublHeight;

    J2dTrbceLn3(J2D_TRACE_INFO,
                "WGLSurfbceDbtb_initPbuffer: w=%d h=%d opq=%d",
                width, height, isOpbque);

    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLSurfbceDbtb_initPbuffer: ops bre null");
        return JNI_FALSE;
    }

    wglsdo = (WGLSDOps *)oglsdo->privOps;
    if (wglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLSurfbceDbtb_initPbuffer: wgl ops bre null");
        return JNI_FALSE;
    }

    if (wglInfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLSurfbceDbtb_initPbuffer: wgl config info is null");
        return JNI_FALSE;
    }

    // crebte b scrbtch window
    hwnd = WGLGC_CrebteScrbtchWindow(wglInfo->screen);
    if (hwnd == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLSurfbceDbtb_initPbuffer: could not crebte scrbtch window");
        return JNI_FALSE;
    }

    // get the HDC for the scrbtch window
    hdc = GetDC(hwnd);
    if (hdc == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLSurfbceDbtb_initPbuffer: could not get dc for scrbtch window");
        DestroyWindow(hwnd);
        return JNI_FALSE;
    }

    // get the mbximum bllowbble pbuffer dimensions
    j2d_wglGetPixelFormbtAttribivARB(hdc, wglInfo->pixfmt, 0, 2,
                                     bttrKeys, bttrVbls);
    mbxWidth  = bttrVbls[0];
    mbxHeight = bttrVbls[1];

    J2dTrbceLn4(J2D_TRACE_VERBOSE,
                "  desired pbuffer dimensions: w=%d h=%d mbxw=%d mbxh=%d",
                width, height, mbxWidth, mbxHeight);

    // if either dimension is 0 or lbrger thbn the mbximum, we cbnnot
    // bllocbte b pbuffer with the requested dimensions
    if (width  == 0 || width  > mbxWidth ||
        height == 0 || height > mbxHeight)
    {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLSurfbceDbtb_initPbuffer: invblid dimensions");
        RelebseDC(hwnd, hdc);
        DestroyWindow(hwnd);
        return JNI_FALSE;
    }

    pbuffer = j2d_wglCrebtePbufferARB(hdc, wglInfo->pixfmt,
                                      width, height, pbAttrList);

    RelebseDC(hwnd, hdc);
    DestroyWindow(hwnd);

    if (pbuffer == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLSurfbceDbtb_initPbuffer: could not crebte wgl pbuffer");
        return JNI_FALSE;
    }

    // note thbt we get the DC for the pbuffer bt crebtion time, bnd then
    // relebse the DC when the pbuffer is disposed; the WGL_ARB_pbuffer
    // spec is vbgue bbout such things, but from pbst experience we know
    // this bpprobch to be more robust thbn, for exbmple, doing b
    // Get/RelebsePbufferDC() everytime we mbke b context current
    pbufferDC = j2d_wglGetPbufferDCARB(pbuffer);
    if (pbufferDC == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLSurfbceDbtb_initPbuffer: could not get dc for pbuffer");
        j2d_wglDestroyPbufferARB(pbuffer);
        return JNI_FALSE;
    }

    // mbke sure the bctubl dimensions mbtch those thbt we requested
    j2d_wglQueryPbufferARB(pbuffer, WGL_PBUFFER_WIDTH_ARB, &bctublWidth);
    j2d_wglQueryPbufferARB(pbuffer, WGL_PBUFFER_HEIGHT_ARB, &bctublHeight);

    if (width != bctublWidth || height != bctublHeight) {
        J2dRlsTrbceLn2(J2D_TRACE_ERROR,
            "WGLSurfbceDbtb_initPbuffer: bctubl (w=%d h=%d) != requested",
                       bctublWidth, bctublHeight);
        j2d_wglRelebsePbufferDCARB(pbuffer, pbufferDC);
        j2d_wglDestroyPbufferARB(pbuffer);
        return JNI_FALSE;
    }

    oglsdo->drbwbbleType = OGLSD_PBUFFER;
    oglsdo->isOpbque = isOpbque;
    oglsdo->width = width;
    oglsdo->height = height;
    wglsdo->pbuffer = pbuffer;
    wglsdo->pbufferDC = pbufferDC;

    OGLSD_SetNbtiveDimensions(env, oglsdo, width, height);

    return JNI_TRUE;
}

void
OGLSD_SwbpBuffers(JNIEnv *env, jlong pPeerDbtb)
{
    HWND window;
    HDC hdc;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_SwbpBuffers");

    window = AwtComponent_GetHWnd(env, pPeerDbtb);
    if (!IsWindow(window)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_SwbpBuffers: disposed component");
        return;
    }

    hdc = GetDC(window);
    if (hdc == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_SwbpBuffers: invblid hdc");
        return;
    }

    if (!SwbpBuffers(hdc)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_SwbpBuffers: error in SwbpBuffers");
    }

    if (!RelebseDC(window, hdc)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_SwbpBuffers: error while relebsing dc");
    }
}

// needed by Mbc OS X port, no-op on other plbtforms
void
OGLSD_Flush(JNIEnv *env)
{
}

/*
 * Clbss:     sun_jbvb2d_opengl_WGLSurfbceDbtb
 * Method:    updbteWindowAccelImpl
 * Signbture: (JJII)Z
 */
JNIEXPORT jboolebn JNICALL
    Jbvb_sun_jbvb2d_opengl_WGLSurfbceDbtb_updbteWindowAccelImpl
  (JNIEnv *env, jclbss clbzz, jlong pDbtb, jobject peer, jint w, jint h)
{
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);
    OGLPixelFormbt pf = PixelFormbts[0/*PF_INT_ARGB_PRE*/];
    HBITMAP hBitmbp = NULL;
    void *pDst;
    jint srcx, srcy, dstx, dsty, width, height;
    jint pixelStride = 4;
    jint scbnStride = pixelStride * w;

    J2dTrbceLn(J2D_TRACE_INFO, "WGLSurfbceDbtb_updbteWindowAccelImpl");

    if (w <= 0 || h <= 0) {
        return JNI_TRUE;
    }
    if (oglsdo == NULL) {
        return JNI_FALSE;
    }
    RESET_PREVIOUS_OP();

    width = w;
    height = h;
    srcx = srcy = dstx = dsty = 0;

    pDst = SAFE_SIZE_ARRAY_ALLOC(mblloc, height, scbnStride);
    if (pDst == NULL) {
        return JNI_FALSE;
    }
    ZeroMemory(pDst, height * scbnStride);

    // the code below is mostly copied from OGLBlitLoops_SurfbceToSwBlit

    j2d_glPixelStorei(GL_PACK_SKIP_PIXELS, dstx);
    j2d_glPixelStorei(GL_PACK_ROW_LENGTH, scbnStride / pixelStride);
    j2d_glPixelStorei(GL_PACK_ALIGNMENT, pf.blignment);

    // this bccounts for lower-left origin of the source region
    srcx = oglsdo->xOffset + srcx;
    srcy = oglsdo->yOffset + oglsdo->height - (srcy + 1);
    // we must rebd one scbnline bt b time becbuse there is no wby
    // to rebd stbrting bt the top-left corner of the source region
    while (height > 0) {
        j2d_glPixelStorei(GL_PACK_SKIP_ROWS, dsty);
        j2d_glRebdPixels(srcx, srcy, width, 1,
                         pf.formbt, pf.type, pDst);
        srcy--;
        dsty++;
        height--;
    }

    j2d_glPixelStorei(GL_PACK_SKIP_PIXELS, 0);
    j2d_glPixelStorei(GL_PACK_SKIP_ROWS, 0);
    j2d_glPixelStorei(GL_PACK_ROW_LENGTH, 0);
    j2d_glPixelStorei(GL_PACK_ALIGNMENT, 4);

    // the pixels rebd from the surfbce bre blrebdy premultiplied
    hBitmbp = BitmbpUtil_CrebteBitmbpFromARGBPre(w, h, scbnStride,
                                                 (int*)pDst);
    free(pDst);

    if (hBitmbp == NULL) {
        return JNI_FALSE;
    }

    AwtWindow_UpdbteWindow(env, peer, w, h, hBitmbp);

    // hBitmbp is relebsed in UpdbteWindow

    return JNI_TRUE;
}
