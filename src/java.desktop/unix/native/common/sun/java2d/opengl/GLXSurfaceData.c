/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <jlong.h>

#include "sun_jbvb2d_opengl_GLXSurfbceDbtb.h"

#include "OGLRenderQueue.h"
#include "GLXGrbphicsConfig.h"
#include "GLXSurfbceDbtb.h"
#include "bwt_Component.h"
#include "bwt_GrbphicsEnv.h"

/**
 * The methods in this file implement the nbtive windowing system specific
 * lbyer (GLX) for the OpenGL-bbsed Jbvb 2D pipeline.
 */

#ifndef HEADLESS

extern LockFunc       OGLSD_Lock;
extern GetRbsInfoFunc OGLSD_GetRbsInfo;
extern UnlockFunc     OGLSD_Unlock;
extern DisposeFunc    OGLSD_Dispose;

extern void
    OGLSD_SetNbtiveDimensions(JNIEnv *env, OGLSDOps *oglsdo, jint w, jint h);

jboolebn surfbceCrebtionFbiled = JNI_FALSE;

#endif /* !HEADLESS */

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opengl_GLXSurfbceDbtb_initOps(JNIEnv *env, jobject glxsd,
                                              jobject peer, jlong bDbtb)
{
#ifndef HEADLESS
    GLXSDOps *glxsdo = (GLXSDOps *)mblloc(sizeof(GLXSDOps));

    if (glxsdo == NULL) {
        JNU_ThrowOutOfMemoryError(env, "crebting nbtive GLX ops");
        return;
    }

    OGLSDOps *oglsdo = (OGLSDOps *)SurfbceDbtb_InitOps(env, glxsd,
                                                       sizeof(OGLSDOps));
    if (oglsdo == NULL) {
        free(glxsdo);
        JNU_ThrowOutOfMemoryError(env, "Initiblizbtion of SurfbceDbtb fbiled.");
        return;
    }

    J2dTrbceLn(J2D_TRACE_INFO, "GLXSurfbceDbtb_initOps");

    oglsdo->privOps = glxsdo;

    oglsdo->sdOps.Lock       = OGLSD_Lock;
    oglsdo->sdOps.GetRbsInfo = OGLSD_GetRbsInfo;
    oglsdo->sdOps.Unlock     = OGLSD_Unlock;
    oglsdo->sdOps.Dispose    = OGLSD_Dispose;

    oglsdo->drbwbbleType = OGLSD_UNDEFINED;
    oglsdo->bctiveBuffer = GL_FRONT;
    oglsdo->needsInit = JNI_TRUE;

    if (peer != NULL) {
        glxsdo->window = JNU_CbllMethodByNbme(env, NULL, peer,
                                              "getContentWindow", "()J").j;
    } else {
        glxsdo->window = 0;
    }
    glxsdo->configDbtb = (AwtGrbphicsConfigDbtbPtr)jlong_to_ptr(bDbtb);
    if (glxsdo->configDbtb == NULL) {
        free(glxsdo);
        JNU_ThrowNullPointerException(env,
                                 "Nbtive GrbphicsConfig dbtb block missing");
        return;
    }

    if (glxsdo->configDbtb->glxInfo == NULL) {
        free(glxsdo);
        JNU_ThrowNullPointerException(env, "GLXGrbphicsConfigInfo missing");
        return;
    }
#endif /* HEADLESS */
}

#ifndef HEADLESS

/**
 * This function disposes of bny nbtive windowing system resources bssocibted
 * with this surfbce.  For instbnce, if the given OGLSDOps is of type
 * OGLSD_PBUFFER, this method implementbtion will destroy the bctubl pbuffer
 * surfbce.
 */
void
OGLSD_DestroyOGLSurfbce(JNIEnv *env, OGLSDOps *oglsdo)
{
    GLXSDOps *glxsdo = (GLXSDOps *)oglsdo->privOps;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_DestroyOGLSurfbce");

    if (oglsdo->drbwbbleType == OGLSD_PBUFFER) {
        if (glxsdo->drbwbble != 0) {
            j2d_glXDestroyPbuffer(bwt_displby, glxsdo->drbwbble);
            glxsdo->drbwbble = 0;
        }
    } else if (oglsdo->drbwbbleType == OGLSD_WINDOW) {
        // X Window is free'd lbter by AWT code...
    }
}

/**
 * Mbkes the given context current to its bssocibted "scrbtch" surfbce.  If
 * the operbtion is successful, this method will return JNI_TRUE; otherwise,
 * returns JNI_FALSE.
 */
stbtic jboolebn
GLXSD_MbkeCurrentToScrbtch(JNIEnv *env, OGLContext *oglc)
{
    GLXCtxInfo *ctxInfo;

    J2dTrbceLn(J2D_TRACE_INFO, "GLXSD_MbkeCurrentToScrbtch");

    if (oglc == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "GLXSD_MbkeCurrentToScrbtch: context is null");
        return JNI_FALSE;
    }

    ctxInfo = (GLXCtxInfo *)oglc->ctxInfo;
    if (!j2d_glXMbkeContextCurrent(bwt_displby,
                                   ctxInfo->scrbtchSurfbce,
                                   ctxInfo->scrbtchSurfbce,
                                   ctxInfo->context))
    {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "GLXSD_MbkeCurrentToScrbtch: could not mbke current");
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

/**
 * Returns b pointer (bs b jlong) to the nbtive GLXGrbphicsConfigInfo
 * bssocibted with the given OGLSDOps.  This method cbn be cblled from
 * shbred code to retrieve the nbtive GrbphicsConfig dbtb in b plbtform-
 * independent mbnner.
 */
jlong
OGLSD_GetNbtiveConfigInfo(OGLSDOps *oglsdo)
{
    GLXSDOps *glxsdo;

    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_GetNbtiveConfigInfo: ops bre null");
        return 0L;
    }

    glxsdo = (GLXSDOps *)oglsdo->privOps;
    if (glxsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_GetNbtiveConfigInfo: glx ops bre null");
        return 0L;
    }

    if (glxsdo->configDbtb == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_GetNbtiveConfigInfo: config dbtb is null");
        return 0L;
    }

    return ptr_to_jlong(glxsdo->configDbtb->glxInfo);
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
    GLXGrbphicsConfigInfo *glxInfo =
        (GLXGrbphicsConfigInfo *)jlong_to_ptr(pConfigInfo);
    OGLContext *oglc;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_SetScrbtchContext");

    if (glxInfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_SetScrbtchContext: glx config info is null");
        return NULL;
    }

    oglc = glxInfo->context;
    if (!GLXSD_MbkeCurrentToScrbtch(env, oglc)) {
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
    GLXSDOps *dstGLXOps = (GLXSDOps *)dstOps->privOps;
    OGLContext *oglc;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_MbkeOGLContextCurrent");

    oglc = dstGLXOps->configDbtb->glxInfo->context;
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
            if (!GLXSD_MbkeCurrentToScrbtch(env, oglc)) {
                return NULL;
            }
        }

        // now bind to the fbobject bssocibted with the destinbtion surfbce;
        // this mebns thbt bll rendering will go into the fbobject destinbtion
        // (note thbt we unbind the currently bound texture first; this is
        // recommended procedure when binding bn fbobject)
        j2d_glBindTexture(dstOps->textureTbrget, 0);
        j2d_glBindFrbmebufferEXT(GL_FRAMEBUFFER_EXT, dstOps->fbobjectID);
    } else {
        GLXSDOps *srcGLXOps = (GLXSDOps *)srcOps->privOps;
        GLXCtxInfo *ctxinfo = (GLXCtxInfo *)oglc->ctxInfo;

        // mbke the context current
        if (!j2d_glXMbkeContextCurrent(bwt_displby,
                                       dstGLXOps->drbwbble,
                                       srcGLXOps->drbwbble,
                                       ctxinfo->context))
        {
            J2dRlsTrbceLn(J2D_TRACE_ERROR,
                "OGLSD_MbkeOGLContextCurrent: could not mbke current");
            return NULL;
        }

        if (OGLC_IS_CAP_PRESENT(oglc, CAPS_EXT_FBOBJECT)) {
            // the GL_EXT_frbmebuffer_object extension is present, so we
            // must bind to the defbult (windowing system provided)
            // frbmebuffer
            j2d_glBindFrbmebufferEXT(GL_FRAMEBUFFER_EXT, 0);
        }
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
    GLXSDOps *glxsdo;
    Window window;
    XWindowAttributes bttr;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_InitOGLWindow");

    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: ops bre null");
        return JNI_FALSE;
    }

    glxsdo = (GLXSDOps *)oglsdo->privOps;
    if (glxsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: glx ops bre null");
        return JNI_FALSE;
    }

    window = glxsdo->window;
    if (window == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: window is invblid");
        return JNI_FALSE;
    }

    XGetWindowAttributes(bwt_displby, window, &bttr);
    oglsdo->width = bttr.width;
    oglsdo->height = bttr.height;

    oglsdo->drbwbbleType = OGLSD_WINDOW;
    oglsdo->isOpbque = JNI_TRUE;
    oglsdo->xOffset = 0;
    oglsdo->yOffset = 0;
    glxsdo->drbwbble = window;
    glxsdo->xdrbwbble = window;

    J2dTrbceLn2(J2D_TRACE_VERBOSE, "  crebted window: w=%d h=%d",
                oglsdo->width, oglsdo->height);

    return JNI_TRUE;
}

stbtic int
GLXSD_BbdAllocXErrHbndler(Displby *displby, XErrorEvent *xerr)
{
    if (xerr->error_code == BbdAlloc) {
        surfbceCrebtionFbiled = JNI_TRUE;
    }
    return 0;
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_opengl_GLXSurfbceDbtb_initPbuffer
    (JNIEnv *env, jobject glxsd,
     jlong pDbtb, jlong pConfigInfo,
     jboolebn isOpbque,
     jint width, jint height)
{
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);
    GLXGrbphicsConfigInfo *glxinfo =
        (GLXGrbphicsConfigInfo *)jlong_to_ptr(pConfigInfo);
    GLXSDOps *glxsdo;
    GLXPbuffer pbuffer;
    int bttrlist[] = {GLX_PBUFFER_WIDTH, 0,
                      GLX_PBUFFER_HEIGHT, 0,
                      GLX_PRESERVED_CONTENTS, GL_FALSE, 0};

    J2dTrbceLn3(J2D_TRACE_INFO,
                "GLXSurfbceDbtb_initPbuffer: w=%d h=%d opq=%d",
                width, height, isOpbque);

    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "GLXSurfbceDbtb_initPbuffer: ops bre null");
        return JNI_FALSE;
    }

    glxsdo = (GLXSDOps *)oglsdo->privOps;
    if (glxsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "GLXSurfbceDbtb_initPbuffer: glx ops bre null");
        return JNI_FALSE;
    }

    if (glxinfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "GLXSurfbceDbtb_initPbuffer: glx config info is null");
        return JNI_FALSE;
    }

    bttrlist[1] = width;
    bttrlist[3] = height;

    surfbceCrebtionFbiled = JNI_FALSE;
    EXEC_WITH_XERROR_HANDLER(
        GLXSD_BbdAllocXErrHbndler,
        pbuffer = j2d_glXCrebtePbuffer(bwt_displby,
                                       glxinfo->fbconfig, bttrlist));
    if ((pbuffer == 0) || surfbceCrebtionFbiled) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "GLXSurfbceDbtb_initPbuffer: could not crebte glx pbuffer");
        return JNI_FALSE;
    }

    oglsdo->drbwbbleType = OGLSD_PBUFFER;
    oglsdo->isOpbque = isOpbque;
    oglsdo->width = width;
    oglsdo->height = height;
    oglsdo->xOffset = 0;
    oglsdo->yOffset = 0;

    glxsdo->drbwbble = pbuffer;
    glxsdo->xdrbwbble = 0;

    OGLSD_SetNbtiveDimensions(env, oglsdo, width, height);

    return JNI_TRUE;
}

void
OGLSD_SwbpBuffers(JNIEnv *env, jlong window)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_SwbpBuffers");

    if (window == 0L) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_SwbpBuffers: window is null");
        return;
    }

    j2d_glXSwbpBuffers(bwt_displby, (Window)window);
}

// needed by Mbc OS X port, no-op on other plbtforms
void
OGLSD_Flush(JNIEnv *env)
{
}

#endif /* !HEADLESS */
