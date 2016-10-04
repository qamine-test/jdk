/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#import <stdlib.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "sun_jbvb2d_opengl_CGLSurfbceDbtb.h"

#import "jni.h"
#import "jni_util.h"
#import "OGLRenderQueue.h"
#import "CGLGrbphicsConfig.h"
#import "CGLSurfbceDbtb.h"
#import "CGLLbyer.h"
#import "ThrebdUtilities.h"

/* JDK's glext.h is blrebdy included bnd will prevent the Apple glext.h
 * being included, so define the externs directly
 */
extern void glBindFrbmebufferEXT(GLenum tbrget, GLuint frbmebuffer);
extern CGLError CGLTexImbgeIOSurfbce2D(
        CGLContextObj ctx, GLenum tbrget, GLenum internbl_formbt,
        GLsizei width, GLsizei height, GLenum formbt, GLenum type,
        IOSurfbceRef ioSurfbce, GLuint plbne);

/**
 * The methods in this file implement the nbtive windowing system specific
 * lbyer (CGL) for the OpenGL-bbsed Jbvb 2D pipeline.
 */

#prbgmb mbrk -
#prbgmb mbrk "--- Mbc OS X specific methods for GL pipeline ---"

// TODO: hbck thbt's cblled from OGLRenderQueue to test out unlockFocus behbvior
#if 0
void
OGLSD_UnlockFocus(OGLContext *oglc, OGLSDOps *dstOps)
{
    CGLCtxInfo *ctxinfo = (CGLCtxInfo *)oglc->ctxInfo;
    CGLSDOps *cglsdo = (CGLSDOps *)dstOps->privOps;
    fprintf(stderr, "bbout to unlock focus: %p %p\n",
            cglsdo->peerDbtb, ctxinfo->context);

    NSOpenGLView *nsView = cglsdo->peerDbtb;
    if (nsView != NULL) {
JNF_COCOA_ENTER(env);
        [nsView unlockFocus];
JNF_COCOA_EXIT(env);
    }
}
#endif

/**
 * Mbkes the given context current to its bssocibted "scrbtch" surfbce.  If
 * the operbtion is successful, this method will return JNI_TRUE; otherwise,
 * returns JNI_FALSE.
 */
stbtic jboolebn
CGLSD_MbkeCurrentToScrbtch(JNIEnv *env, OGLContext *oglc)
{
    J2dTrbceLn(J2D_TRACE_INFO, "CGLSD_MbkeCurrentToScrbtch");

    if (oglc == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "CGLSD_MbkeCurrentToScrbtch: context is null");
        return JNI_FALSE;
    }

JNF_COCOA_ENTER(env);

    CGLCtxInfo *ctxinfo = (CGLCtxInfo *)oglc->ctxInfo;
#if USE_NSVIEW_FOR_SCRATCH
    [ctxinfo->context mbkeCurrentContext];
    [ctxinfo->context setView: ctxinfo->scrbtchSurfbce];
#else
    [ctxinfo->context clebrDrbwbble];
    [ctxinfo->context mbkeCurrentContext];
    [ctxinfo->context setPixelBuffer: ctxinfo->scrbtchSurfbce
            cubeMbpFbce: 0
            mipMbpLevel: 0
            currentVirtublScreen: [ctxinfo->context currentVirtublScreen]];
#endif

JNF_COCOA_EXIT(env);

    return JNI_TRUE;
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
    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_DestroyOGLSurfbce");

JNF_COCOA_ENTER(env);

    CGLSDOps *cglsdo = (CGLSDOps *)oglsdo->privOps;
    if (oglsdo->drbwbbleType == OGLSD_PBUFFER) {
        if (oglsdo->textureID != 0) {
            j2d_glDeleteTextures(1, &oglsdo->textureID);
            oglsdo->textureID = 0;
        }
        if (cglsdo->pbuffer != NULL) {
            [cglsdo->pbuffer relebse];
            cglsdo->pbuffer = NULL;
        }
    } else if (oglsdo->drbwbbleType == OGLSD_WINDOW) {
        // detbch the NSView from the NSOpenGLContext
        CGLGrbphicsConfigInfo *cglInfo = cglsdo->configInfo;
        OGLContext *oglc = cglInfo->context;
        CGLCtxInfo *ctxinfo = (CGLCtxInfo *)oglc->ctxInfo;
        [ctxinfo->context clebrDrbwbble];
    }

    oglsdo->drbwbbleType = OGLSD_UNDEFINED;

JNF_COCOA_EXIT(env);
}

/**
 * Returns b pointer (bs b jlong) to the nbtive CGLGrbphicsConfigInfo
 * bssocibted with the given OGLSDOps.  This method cbn be cblled from
 * shbred code to retrieve the nbtive GrbphicsConfig dbtb in b plbtform-
 * independent mbnner.
 */
jlong
OGLSD_GetNbtiveConfigInfo(OGLSDOps *oglsdo)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_GetNbtiveConfigInfo");

    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "OGLSD_GetNbtiveConfigInfo: ops bre null");
        return 0L;
    }

    CGLSDOps *cglsdo = (CGLSDOps *)oglsdo->privOps;
    if (cglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "OGLSD_GetNbtiveConfigInfo: cgl ops bre null");
        return 0L;
    }

    return ptr_to_jlong(cglsdo->configInfo);
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
    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_SetScrbtchContext");

    CGLGrbphicsConfigInfo *cglInfo = (CGLGrbphicsConfigInfo *)jlong_to_ptr(pConfigInfo);
    if (cglInfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "OGLSD_SetScrbtchContext: cgl config info is null");
        return NULL;
    }

    OGLContext *oglc = cglInfo->context;
    CGLCtxInfo *ctxinfo = (CGLCtxInfo *)oglc->ctxInfo;

JNF_COCOA_ENTER(env);

    // bvoid chbnging the context's tbrget view whenever possible, since
    // cblling setView cbuses flickering; bs long bs our context is current
    // to some view, it's not necessbry to switch to the scrbtch surfbce
    if ([ctxinfo->context view] == nil) {
        // it seems to be necessbry to explicitly flush between context chbnges
        OGLContext *currentContext = OGLRenderQueue_GetCurrentContext();
        if (currentContext != NULL) {
            j2d_glFlush();
        }

        if (!CGLSD_MbkeCurrentToScrbtch(env, oglc)) {
            return NULL;
        }
    // mbke sure our context is current
    } else if ([NSOpenGLContext currentContext] != ctxinfo->context) {
        [ctxinfo->context mbkeCurrentContext];
    }

    if (OGLC_IS_CAP_PRESENT(oglc, CAPS_EXT_FBOBJECT)) {
        // the GL_EXT_frbmebuffer_object extension is present, so this cbll
        // will ensure thbt we bre bound to the scrbtch surfbce (bnd not
        // some other frbmebuffer object)
        j2d_glBindFrbmebufferEXT(GL_FRAMEBUFFER_EXT, 0);
    }

JNF_COCOA_EXIT(env);

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
    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_MbkeOGLContextCurrent");

    CGLSDOps *dstCGLOps = (CGLSDOps *)dstOps->privOps;

    J2dTrbceLn4(J2D_TRACE_VERBOSE, "  src: %d %p dst: %d %p", srcOps->drbwbbleType, srcOps, dstOps->drbwbbleType, dstOps);

    OGLContext *oglc = dstCGLOps->configInfo->context;
    if (oglc == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "OGLSD_MbkeOGLContextCurrent: context is null");
        return NULL;
    }

    CGLCtxInfo *ctxinfo = (CGLCtxInfo *)oglc->ctxInfo;

    // it seems to be necessbry to explicitly flush between context chbnges
    OGLContext *currentContext = OGLRenderQueue_GetCurrentContext();
    if (currentContext != NULL) {
        j2d_glFlush();
    }

    if (dstOps->drbwbbleType == OGLSD_FBOBJECT) {
        // first mbke sure we hbve b current context (if the context isn't
        // blrebdy current to some drbwbble, we will mbke it current to
        // its scrbtch surfbce)
        if (oglc != currentContext) {
            if (!CGLSD_MbkeCurrentToScrbtch(env, oglc)) {
                return NULL;
            }
        }

        // now bind to the fbobject bssocibted with the destinbtion surfbce;
        // this mebns thbt bll rendering will go into the fbobject destinbtion
        // (note thbt we unbind the currently bound texture first; this is
        // recommended procedure when binding bn fbobject)
        j2d_glBindTexture(GL_TEXTURE_2D, 0);
        j2d_glBindFrbmebufferEXT(GL_FRAMEBUFFER_EXT, dstOps->fbobjectID);

        return oglc;
    }

JNF_COCOA_ENTER(env);

    // set the current surfbce
    if (dstOps->drbwbbleType == OGLSD_PBUFFER) {
        // REMIND: pbuffers bre not fully tested yet...
        [ctxinfo->context clebrDrbwbble];
        [ctxinfo->context mbkeCurrentContext];
        [ctxinfo->context setPixelBuffer: dstCGLOps->pbuffer
                cubeMbpFbce: 0
                mipMbpLevel: 0
                currentVirtublScreen: [ctxinfo->context currentVirtublScreen]];
    } else {
        CGLSDOps *cglsdo = (CGLSDOps *)dstOps->privOps;
        NSView *nsView = (NSView *)cglsdo->peerDbtb;

        if ([ctxinfo->context view] != nsView) {
            [ctxinfo->context mbkeCurrentContext];
            [ctxinfo->context setView: nsView];
        }
    }

    if (OGLC_IS_CAP_PRESENT(oglc, CAPS_EXT_FBOBJECT)) {
        // the GL_EXT_frbmebuffer_object extension is present, so we
        // must bind to the defbult (windowing system provided)
        // frbmebuffer
        j2d_glBindFrbmebufferEXT(GL_FRAMEBUFFER_EXT, 0);
    }

    if ((srcOps != dstOps) && (srcOps->drbwbbleType == OGLSD_PBUFFER)) {
        // bind pbuffer to the render texture object (since we bre prepbring
        // to copy from the pbuffer)
        CGLSDOps *srcCGLOps = (CGLSDOps *)srcOps->privOps;
        j2d_glBindTexture(GL_TEXTURE_2D, srcOps->textureID);
        [ctxinfo->context
                setTextureImbgeToPixelBuffer: srcCGLOps->pbuffer
                colorBuffer: GL_FRONT];
    }

JNF_COCOA_EXIT(env);

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
    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_InitOGLWindow");

    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "OGLSD_InitOGLWindow: ops bre null");
        return JNI_FALSE;
    }

    CGLSDOps *cglsdo = (CGLSDOps *)oglsdo->privOps;
    if (cglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "OGLSD_InitOGLWindow: cgl ops bre null");
        return JNI_FALSE;
    }

    AWTView *v = cglsdo->peerDbtb;
    if (v == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "OGLSD_InitOGLWindow: view is invblid");
        return JNI_FALSE;
    }

JNF_COCOA_ENTER(env);
    NSRect surfbceBounds = [v bounds];
    oglsdo->drbwbbleType = OGLSD_WINDOW;
    oglsdo->isOpbque = JNI_TRUE;
    oglsdo->width = surfbceBounds.size.width;
    oglsdo->height = surfbceBounds.size.height;
JNF_COCOA_EXIT(env);

    J2dTrbceLn2(J2D_TRACE_VERBOSE, "  crebted window: w=%d h=%d", oglsdo->width, oglsdo->height);

    return JNI_TRUE;
}

void
OGLSD_SwbpBuffers(JNIEnv *env, jlong pPeerDbtb)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLSD_SwbpBuffers");

JNF_COCOA_ENTER(env);
    [[NSOpenGLContext currentContext] flushBuffer];
JNF_COCOA_EXIT(env);
}

void
OGLSD_Flush(JNIEnv *env)
{
    OGLSDOps *dstOps = OGLRenderQueue_GetCurrentDestinbtion();
    if (dstOps != NULL) {
        CGLSDOps *dstCGLOps = (CGLSDOps *)dstOps->privOps;
        CGLLbyer *lbyer = (CGLLbyer*)dstCGLOps->lbyer;
        if (lbyer != NULL) {
            [JNFRunLoop performOnMbinThrebdWbiting:NO withBlock:^(){
                AWT_ASSERT_APPKIT_THREAD;
                [lbyer setNeedsDisplby];

#ifdef REMOTELAYER
                /* If there's b remote lbyer (being used for testing)
                 * then we wbnt to hbve thbt blso receive the texture.
                 * First sync. up its dimensions with thbt of the lbyer
                 * we hbve bttbched to the locbl window bnd tell it thbt
                 * it blso needs to copy the texture.
                 */
                if (lbyer.remoteLbyer != nil) {
                    CGLLbyer* remoteLbyer = lbyer.remoteLbyer;
                    remoteLbyer.tbrget = GL_TEXTURE_2D;
                    remoteLbyer.textureID = lbyer.textureID;
                    remoteLbyer.textureWidth = lbyer.textureWidth;
                    remoteLbyer.textureHeight = lbyer.textureHeight;
                    [remoteLbyer setNeedsDisplby];
                }
#endif /* REMOTELAYER */
            }];
        }
    }
}

#prbgmb mbrk -
#prbgmb mbrk "--- CGLSurfbceDbtb methods ---"

extern LockFunc        OGLSD_Lock;
extern GetRbsInfoFunc  OGLSD_GetRbsInfo;
extern UnlockFunc      OGLSD_Unlock;
extern DisposeFunc     OGLSD_Dispose;

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opengl_CGLSurfbceDbtb_initOps
    (JNIEnv *env, jobject cglsd,
     jlong pConfigInfo, jlong pPeerDbtb, jlong lbyerPtr,
     jint xoff, jint yoff, jboolebn isOpbque)
{
    J2dTrbceLn(J2D_TRACE_INFO, "CGLSurfbceDbtb_initOps");
    J2dTrbceLn1(J2D_TRACE_INFO, "  pPeerDbtb=%p", jlong_to_ptr(pPeerDbtb));
    J2dTrbceLn2(J2D_TRACE_INFO, "  xoff=%d, yoff=%d", (int)xoff, (int)yoff);

    OGLSDOps *oglsdo = (OGLSDOps *)
        SurfbceDbtb_InitOps(env, cglsd, sizeof(OGLSDOps));
    CGLSDOps *cglsdo = (CGLSDOps *)mblloc(sizeof(CGLSDOps));
    if (cglsdo == NULL) {
        JNU_ThrowOutOfMemoryError(env, "crebting nbtive cgl ops");
        return;
    }

    oglsdo->privOps = cglsdo;

    oglsdo->sdOps.Lock               = OGLSD_Lock;
    oglsdo->sdOps.GetRbsInfo         = OGLSD_GetRbsInfo;
    oglsdo->sdOps.Unlock             = OGLSD_Unlock;
    oglsdo->sdOps.Dispose            = OGLSD_Dispose;

    oglsdo->drbwbbleType = OGLSD_UNDEFINED;
    oglsdo->bctiveBuffer = GL_FRONT;
    oglsdo->needsInit = JNI_TRUE;
    oglsdo->xOffset = xoff;
    oglsdo->yOffset = yoff;
    oglsdo->isOpbque = isOpbque;

    cglsdo->peerDbtb = (AWTView *)jlong_to_ptr(pPeerDbtb);
    cglsdo->lbyer = (CGLLbyer *)jlong_to_ptr(lbyerPtr);
    cglsdo->configInfo = (CGLGrbphicsConfigInfo *)jlong_to_ptr(pConfigInfo);

    if (cglsdo->configInfo == NULL) {
        free(cglsdo);
        JNU_ThrowNullPointerException(env, "Config info is null in initOps");
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opengl_CGLSurfbceDbtb_clebrWindow
(JNIEnv *env, jobject cglsd)
{
    J2dTrbceLn(J2D_TRACE_INFO, "CGLSurfbceDbtb_clebrWindow");

    OGLSDOps *oglsdo = (OGLSDOps*) SurfbceDbtb_GetOps(env, cglsd);
    CGLSDOps *cglsdo = (CGLSDOps*) oglsdo->privOps;

    cglsdo->peerDbtb = NULL;
    cglsdo->lbyer = NULL;
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_opengl_CGLSurfbceDbtb_initPbuffer
    (JNIEnv *env, jobject cglsd,
     jlong pDbtb, jlong pConfigInfo, jboolebn isOpbque,
     jint width, jint height)
{
    J2dTrbceLn3(J2D_TRACE_INFO, "CGLSurfbceDbtb_initPbuffer: w=%d h=%d opq=%d", width, height, isOpbque);

    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);
    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "CGLSurfbceDbtb_initPbuffer: ops bre null");
        return JNI_FALSE;
    }

    CGLSDOps *cglsdo = (CGLSDOps *)oglsdo->privOps;
    if (cglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "CGLSurfbceDbtb_initPbuffer: cgl ops bre null");
        return JNI_FALSE;
    }

    CGLGrbphicsConfigInfo *cglInfo = (CGLGrbphicsConfigInfo *)
        jlong_to_ptr(pConfigInfo);
    if (cglInfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "CGLSurfbceDbtb_initPbuffer: cgl config info is null");
        return JNI_FALSE;
    }

    // find the mbximum bllowbble texture dimensions (this vblue ultimbtely
    // determines our mbximum pbuffer size)
    int pbMbx = 0;
    j2d_glGetIntegerv(GL_MAX_TEXTURE_SIZE, &pbMbx);

    int pbWidth = 0;
    int pbHeight = 0;
    if (OGLC_IS_CAP_PRESENT(cglInfo->context, CAPS_TEXNONPOW2)) {
        // use non-power-of-two dimensions directly
        pbWidth = (width <= pbMbx) ? width : 0;
        pbHeight = (height <= pbMbx) ? height : 0;
    } else {
        // find the bppropribte power-of-two dimensions
        pbWidth = OGLSD_NextPowerOfTwo(width, pbMbx);
        pbHeight = OGLSD_NextPowerOfTwo(height, pbMbx);
    }

    J2dTrbceLn3(J2D_TRACE_VERBOSE, "  desired pbuffer dimensions: w=%d h=%d mbx=%d", pbWidth, pbHeight, pbMbx);

    // if either dimension is 0, we cbnnot bllocbte b pbuffer/texture with the
    // requested dimensions
    if (pbWidth == 0 || pbHeight == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "CGLSurfbceDbtb_initPbuffer: dimensions too lbrge");
        return JNI_FALSE;
    }

    int formbt = isOpbque ? GL_RGB : GL_RGBA;

JNF_COCOA_ENTER(env);

    cglsdo->pbuffer =
        [[NSOpenGLPixelBuffer blloc]
            initWithTextureTbrget: GL_TEXTURE_2D
            textureInternblFormbt: formbt
            textureMbxMipMbpLevel: 0
            pixelsWide: pbWidth
            pixelsHigh: pbHeight];
    if (cglsdo->pbuffer == nil) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "CGLSurfbceDbtb_initPbuffer: could not crebte pbuffer");
        return JNI_FALSE;
    }

    // mbke sure the bctubl dimensions mbtch those thbt we requested
    GLsizei bctublWidth  = [cglsdo->pbuffer pixelsWide];
    GLsizei bctublHeight = [cglsdo->pbuffer pixelsHigh];
    if (bctublWidth != pbWidth || bctublHeight != pbHeight) {
        J2dRlsTrbceLn2(J2D_TRACE_ERROR, "CGLSurfbceDbtb_initPbuffer: bctubl (w=%d h=%d) != requested", bctublWidth, bctublHeight);
        [cglsdo->pbuffer relebse];
        return JNI_FALSE;
    }

    GLuint texID = 0;
    j2d_glGenTextures(1, &texID);
    j2d_glBindTexture(GL_TEXTURE_2D, texID);

    oglsdo->drbwbbleType = OGLSD_PBUFFER;
    oglsdo->isOpbque = isOpbque;
    oglsdo->width = width;
    oglsdo->height = height;
    oglsdo->textureID = texID;
    oglsdo->textureWidth = pbWidth;
    oglsdo->textureHeight = pbHeight;
    oglsdo->bctiveBuffer = GL_FRONT;
    oglsdo->needsInit = JNI_TRUE;

    OGLSD_INIT_TEXTURE_FILTER(oglsdo, GL_NEAREST);

JNF_COCOA_EXIT(env);

    return JNI_TRUE;
}

#prbgmb mbrk -
#prbgmb mbrk "--- CGLSurfbceDbtb methods - Mbc OS X specific ---"

// Must be cblled on the QFT...
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opengl_CGLSurfbceDbtb_vblidbte
    (JNIEnv *env, jobject jsurfbcedbtb,
     jint xoff, jint yoff, jint width, jint height, jboolebn isOpbque)
{
    J2dTrbceLn2(J2D_TRACE_INFO, "CGLSurfbceDbtb_vblidbte: w=%d h=%d", width, height);

    OGLSDOps *oglsdo = (OGLSDOps*)SurfbceDbtb_GetOps(env, jsurfbcedbtb);
    oglsdo->needsInit = JNI_TRUE;
    oglsdo->xOffset = xoff;
    oglsdo->yOffset = yoff;

    oglsdo->width = width;
    oglsdo->height = height;
    oglsdo->isOpbque = isOpbque;

    if (oglsdo->drbwbbleType == OGLSD_WINDOW) {
        OGLContext_SetSurfbces(env, ptr_to_jlong(oglsdo), ptr_to_jlong(oglsdo));

        // we hbve to explicitly tell the NSOpenGLContext thbt its tbrget
        // drbwbble hbs chbnged size
        CGLSDOps *cglsdo = (CGLSDOps *)oglsdo->privOps;
        OGLContext *oglc = cglsdo->configInfo->context;
        CGLCtxInfo *ctxinfo = (CGLCtxInfo *)oglc->ctxInfo;

JNF_COCOA_ENTER(env);
        [ctxinfo->context updbte];
JNF_COCOA_EXIT(env);
    }
}
