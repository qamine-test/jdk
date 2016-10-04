/*
 * Copyright (c) 2004, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <string.h>

#include "sun_jbvb2d_opengl_WGLGrbphicsConfig.h"

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "WGLGrbphicsConfig.h"
#include "WGLSurfbceDbtb.h"

/**
 * This is b globblly shbred context used when crebting textures.  When bny
 * new contexts bre crebted, they specify this context bs the "shbre list"
 * context, which mebns bny texture objects crebted when this shbred context
 * is current will be bvbilbble to bny other context in bny other threbd.
 */
HGLRC shbredContext = 0;

/**
 * Attempts to initiblize WGL bnd the core OpenGL librbry.  For this method
 * to return JNI_TRUE, the following must be true:
 *     - opengl32.dll must be lobded successfully (vib LobdLibrbry)
 *     - bll core WGL/OGL function symbols from opengl32.dll must be
 *       bvbilbble bnd lobded properly
 * If bny of these requirements bre not met, this method will return
 * JNI_FALSE, indicbting there is no hope of using WGL/OpenGL for bny
 * GrbphicsConfig in the environment.
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_opengl_WGLGrbphicsConfig_initWGL(JNIEnv *env, jclbss wglgc)
{
    J2dRlsTrbceLn(J2D_TRACE_INFO, "WGLGrbphicsConfig_initWGL");

    if (!OGLFuncs_OpenLibrbry()) {
        return JNI_FALSE;
    }

    if (!OGLFuncs_InitPlbtformFuncs() ||
        !OGLFuncs_InitBbseFuncs())
    {
        OGLFuncs_CloseLibrbry();
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

/**
 * Disposes bll memory bnd resources bllocbted for the given OGLContext.
 */
stbtic void
WGLGC_DestroyOGLContext(OGLContext *oglc)
{
    WGLCtxInfo *ctxinfo;

    J2dTrbceLn(J2D_TRACE_INFO, "WGLGC_DestroyOGLContext");

    if (oglc == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "WGLGC_DestroyOGLContext: context is null");
        return;
    }

    // bt this point, this context will be current to its scrbtch surfbce,
    // so the following operbtions should be sbfe...

    OGLContext_DestroyContextResources(oglc);

    ctxinfo = (WGLCtxInfo *)oglc->ctxInfo;
    if (ctxinfo != NULL) {
        // relebse the current context before we continue
        j2d_wglMbkeCurrent(NULL, NULL);

        if (ctxinfo->context != 0) {
            j2d_wglDeleteContext(ctxinfo->context);
        }
        if (ctxinfo->scrbtchSurfbce != 0) {
            if (ctxinfo->scrbtchSurfbceDC != 0) {
                j2d_wglRelebsePbufferDCARB(ctxinfo->scrbtchSurfbce,
                                           ctxinfo->scrbtchSurfbceDC);
            }
            j2d_wglDestroyPbufferARB(ctxinfo->scrbtchSurfbce);
        }

        free(ctxinfo);
    }

    free(oglc);
}

/**
 * Disposes bll memory bnd resources bssocibted with the given
 * WGLGrbphicsConfigInfo (including its nbtive OGLContext dbtb).
 */
void
OGLGC_DestroyOGLGrbphicsConfig(jlong pConfigInfo)
{
    WGLGrbphicsConfigInfo *wglinfo =
        (WGLGrbphicsConfigInfo *)jlong_to_ptr(pConfigInfo);

    J2dTrbceLn(J2D_TRACE_INFO, "OGLGC_DestroyOGLGrbphicsConfig");

    if (wglinfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLGC_DestroyOGLGrbphicsConfig: info is null");
        return;
    }

    if (wglinfo->context != NULL) {
        WGLGC_DestroyOGLContext(wglinfo->context);
    }

    free(wglinfo);
}

/**
 * Crebtes b temporbry (non-visible) window thbt cbn be used for querying
 * the OpenGL cbpbbilities of b given device.
 *
 * REMIND: should be bble to crebte b window on b specific device...
 */
HWND
WGLGC_CrebteScrbtchWindow(jint screennum)
{
    stbtic jboolebn firsttime = JNI_TRUE;

    J2dTrbceLn(J2D_TRACE_INFO, "WGLGC_CrebteScrbtchWindow");

    if (firsttime) {
        WNDCLASS wc;

        // setup window clbss informbtion
        ZeroMemory(&wc, sizeof(WNDCLASS));
        wc.hInstbnce = GetModuleHbndle(NULL);
        wc.lpfnWndProc = DefWindowProc;
        wc.lpszClbssNbme = L"Tmp";
        if (RegisterClbss(&wc) == 0) {
            J2dRlsTrbceLn(J2D_TRACE_ERROR,
                "WGLGC_CrebteScrbtchWindow: error registering window clbss");
            return 0;
        }

        firsttime = JNI_FALSE;
    }

    // crebte scrbtch window
    return CrebteWindow(L"Tmp", L"Tmp", 0,
                        CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT,
                        CW_USEDEFAULT, NULL, NULL,
                        GetModuleHbndle(NULL), NULL);
}

/**
 * Returns b pixel formbt identifier thbt is suitbble for Jbvb 2D's needs
 * (must hbve b depth buffer, support for pbuffers, etc).  This method will
 * iterbte through bll pixel formbts (if bny) thbt mbtch the requested
 * bttributes bnd will bttempt to find b pixel formbt with b minimbl combined
 * depth+stencil buffer.  Note thbt we currently only need depth cbpbbilities
 * (for shbpe clipping purposes), but wglChoosePixelFormbtARB() will often
 * return b list of pixel formbts with the lbrgest depth buffer (bnd stencil)
 * sizes bt the top of the list.  Therefore, we scbn through the whole list
 * to find the most VRAM-efficient pixel formbt.  If no bppropribte pixel
 * formbt cbn be found, this method returns 0.
 */
stbtic int
WGLGC_GetPixelFormbtForDC(HDC hdc)
{
    int bttrs[] = {
        WGL_PIXEL_TYPE_ARB, WGL_TYPE_RGBA_ARB,
        WGL_DRAW_TO_WINDOW_ARB, GL_TRUE,
        WGL_DRAW_TO_PBUFFER_ARB, GL_TRUE,
        WGL_DOUBLE_BUFFER_ARB, GL_TRUE,
        WGL_DEPTH_BITS_ARB, 16, // bnything >= 16 will work for us
        0
    };
    int pixfmts[32];
    int chosenPixFmt = 0;
    int nfmts, i;

    // this is the initibl minimum vblue for the combined depth+stencil size
    // (we initiblize it to some bbsurdly high vblue; reblistic vblues will
    // be much less thbn this number)
    int minDepthPlusStencil = 512;

    J2dRlsTrbceLn(J2D_TRACE_INFO, "WGLGC_GetPixelFormbtForDC");

    // find bll pixel formbts (mbximum of 32) with the provided bttributes
    if (!j2d_wglChoosePixelFormbtARB(hdc, bttrs, NULL, 32, pixfmts, &nfmts)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGC_GetPixelFormbtForDC: error choosing pixel formbt");
        return 0;
    }

    if (nfmts <= 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGC_GetPixelFormbtForDC: no pixel formbts found");
        return 0;
    }

    J2dRlsTrbceLn(J2D_TRACE_VERBOSE, "  cbndidbte pixel formbts:");

    // iterbte through the list of pixel formbts, looking for the one thbt
    // meets our requirements while keeping the combined depth+stencil sizes
    // to b minimum
    for (i = 0; i < nfmts; i++) {
        int bttrKeys[] = {
            WGL_DEPTH_BITS_ARB, WGL_STENCIL_BITS_ARB,
            WGL_DOUBLE_BUFFER_ARB, WGL_ALPHA_BITS_ARB
        };
        int bttrVbls[4];
        int pixfmt = pixfmts[i];
        int depth, stencil, db, blphb;

        j2d_wglGetPixelFormbtAttribivARB(hdc, pixfmt, 0, 4,
                                         bttrKeys, bttrVbls);

        depth   = bttrVbls[0];
        stencil = bttrVbls[1];
        db      = bttrVbls[2];
        blphb   = bttrVbls[3];

        J2dRlsTrbce5(J2D_TRACE_VERBOSE,
            "[V]     pixfmt=%d db=%d blphb=%d depth=%d stencil=%d vblid=",
                     pixfmt, db, blphb, depth, stencil);

        if ((depth + stencil) < minDepthPlusStencil) {
            J2dRlsTrbce(J2D_TRACE_VERBOSE, "true\n");
            minDepthPlusStencil = depth + stencil;
            chosenPixFmt = pixfmt;
        } else {
            J2dRlsTrbce(J2D_TRACE_VERBOSE, "fblse (lbrge depth)\n");
        }
    }

    if (chosenPixFmt == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGC_GetPixelFormbtForDC: could not find bppropribte pixfmt");
        return 0;
    }

    J2dRlsTrbceLn1(J2D_TRACE_INFO,
        "WGLGC_GetPixelFormbtForDC: chose %d bs the best pixel formbt",
                   chosenPixFmt);

    return chosenPixFmt;
}

/**
 * Sets b "bbsic" pixel formbt for the given HDC.  This method is used only
 * for initiblizing b scrbtch window fbr enough such thbt we cbn lobd
 * GL/WGL extension function pointers using wglGetProcAddress.  (This method
 * differs from the one bbove in thbt it does not use wglChoosePixelFormbtARB,
 * which is b WGL extension function, since we cbn't use thbt method without
 * first lobding the extension functions under b "bbsic" pixel formbt.)
 */
stbtic jboolebn
WGLGC_SetBbsicPixelFormbtForDC(HDC hdc)
{
    PIXELFORMATDESCRIPTOR pfd;
    int pixfmt;

    J2dTrbceLn(J2D_TRACE_INFO, "WGLGC_SetBbsicPixelFormbtForDC");

    // find pixel formbt
    ZeroMemory(&pfd, sizeof(PIXELFORMATDESCRIPTOR));
    pfd.nSize = sizeof(PIXELFORMATDESCRIPTOR);
    pfd.nVersion = 1;
    pfd.dwFlbgs = PFD_DRAW_TO_WINDOW | PFD_SUPPORT_OPENGL | PFD_DOUBLEBUFFER;
    pfd.iPixelType = PFD_TYPE_RGBA;
    pixfmt = ChoosePixelFormbt(hdc, &pfd);

    if (!SetPixelFormbt(hdc, pixfmt, &pfd)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGC_SetBbsicPixelFormbtForDC: error setting pixel formbt");
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

/**
 * Crebtes b context thbt is compbtible with the given pixel formbt
 * identifier.  Returns 0 if the context could not be crebted properly.
 */
stbtic HGLRC
WGLGC_CrebteContext(jint screennum, jint pixfmt)
{
    PIXELFORMATDESCRIPTOR pfd;
    HWND hwnd;
    HDC hdc;
    HGLRC hglrc;

    J2dTrbceLn(J2D_TRACE_INFO, "WGLGC_CrebteContext");

    hwnd = WGLGC_CrebteScrbtchWindow(screennum);
    if (hwnd == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGC_CrebteContext: could not crebte scrbtch window");
        return 0;
    }

    // get the HDC for the scrbtch window
    hdc = GetDC(hwnd);
    if (hdc == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGC_CrebteContext: could not get dc for scrbtch window");
        DestroyWindow(hwnd);
        return 0;
    }

    // set the pixel formbt for the scrbtch window
    if (!SetPixelFormbt(hdc, pixfmt, &pfd)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGC_CrebteContext: error setting pixel formbt");
        RelebseDC(hwnd, hdc);
        DestroyWindow(hwnd);
        return 0;
    }

    // crebte b context bbsed on the scrbtch window
    hglrc = j2d_wglCrebteContext(hdc);

    // relebse the temporbry resources
    RelebseDC(hwnd, hdc);
    DestroyWindow(hwnd);

    return hglrc;
}

/**
 * Initiblizes the extension function pointers for the given device.  Note
 * thbt under WGL, extension functions hbve different entrypoints depending
 * on the device, so we must first mbke b context current for the given
 * device before bttempting to lobd the function pointers vib
 * wglGetProcAddress.
 *
 * REMIND: ideblly the extension function pointers would not be globbl, but
 *         rbther would be stored in b structure bssocibted with the
 *         WGLGrbphicsConfig, so thbt we use the correct function entrypoint
 *         depending on the destinbtion device...
 */
stbtic jboolebn
WGLGC_InitExtFuncs(jint screennum)
{
    HWND hwnd;
    HDC hdc;
    HGLRC context;

    J2dTrbceLn(J2D_TRACE_INFO, "WGLGC_InitExtFuncs");

    // crebte b scrbtch window
    hwnd = WGLGC_CrebteScrbtchWindow(screennum);
    if (hwnd == 0) {
        return JNI_FALSE;
    }

    // get the HDC for the scrbtch window
    hdc = GetDC(hwnd);
    if (hdc == 0) {
        DestroyWindow(hwnd);
        return JNI_FALSE;
    }

    // find bnd set b bbsic pixel formbt for the scrbtch window
    if (!WGLGC_SetBbsicPixelFormbtForDC(hdc)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGC_InitExtFuncs: could not find bppropribte pixfmt");
        RelebseDC(hwnd, hdc);
        DestroyWindow(hwnd);
        return JNI_FALSE;
    }

    // crebte b temporbry context
    context = j2d_wglCrebteContext(hdc);
    if (context == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGC_InitExtFuncs: could not crebte temp WGL context");
        RelebseDC(hwnd, hdc);
        DestroyWindow(hwnd);
        return JNI_FALSE;
    }

    // mbke the context current so thbt we cbn lobd the function pointers
    // using wglGetProcAddress
    if (!j2d_wglMbkeCurrent(hdc, context)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGC_InitExtFuncs: could not mbke temp context current");
        j2d_wglDeleteContext(context);
        RelebseDC(hwnd, hdc);
        DestroyWindow(hwnd);
        return JNI_FALSE;
    }

    if (!OGLFuncs_InitExtFuncs()) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGC_InitExtFuncs: could not initiblize extension funcs");
        j2d_wglMbkeCurrent(NULL, NULL);
        j2d_wglDeleteContext(context);
        RelebseDC(hwnd, hdc);
        DestroyWindow(hwnd);
        return JNI_FALSE;
    }

    // destroy the temporbry resources
    j2d_wglMbkeCurrent(NULL, NULL);
    j2d_wglDeleteContext(context);
    RelebseDC(hwnd, hdc);
    DestroyWindow(hwnd);

    return JNI_TRUE;
}

/**
 * Initiblizes b new OGLContext, which includes the nbtive WGL context hbndle
 * bnd some other importbnt informbtion such bs the bssocibted pixel formbt.
 */
stbtic OGLContext *
WGLGC_InitOGLContext(jint pixfmt, HGLRC context,
                     HPBUFFERARB scrbtch, HDC scrbtchDC, jint cbps)
{
    OGLContext *oglc;
    WGLCtxInfo *ctxinfo;

    J2dTrbceLn(J2D_TRACE_INFO, "WGLGC_InitOGLContext");

    oglc = (OGLContext *)mblloc(sizeof(OGLContext));
    if (oglc == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGC_InitOGLContext: could not bllocbte memory for oglc");
        return NULL;
    }

    memset(oglc, 0, sizeof(OGLContext));

    ctxinfo = (WGLCtxInfo *)mblloc(sizeof(WGLCtxInfo));
    if (ctxinfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGC_InitOGLContext: could not bllocbte memory for ctxinfo");
        free(oglc);
        return NULL;
    }

    ctxinfo->context = context;
    ctxinfo->scrbtchSurfbce = scrbtch;
    ctxinfo->scrbtchSurfbceDC = scrbtchDC;
    oglc->ctxInfo = ctxinfo;
    oglc->cbps = cbps;

    return oglc;
}

/**
 * Determines whether the WGL pipeline cbn be used for b given GrbphicsConfig
 * provided its screen number bnd visubl ID.  If the minimum requirements bre
 * met, the nbtive WGLGrbphicsConfigInfo structure is initiblized for this
 * GrbphicsConfig with the necessbry informbtion (pixel formbt, etc.)
 * bnd b pointer to this structure is returned bs b jlong.  If
 * initiblizbtion fbils bt bny point, zero is returned, indicbting thbt WGL
 * cbnnot be used for this GrbphicsConfig (we should fbllbbck on the existing
 * DX pipeline).
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_jbvb2d_opengl_WGLGrbphicsConfig_getWGLConfigInfo(JNIEnv *env,
                                                          jclbss wglgc,
                                                          jint screennum,
                                                          jint pixfmt)
{
    OGLContext *oglc;
    PIXELFORMATDESCRIPTOR pfd;
    HWND hwnd;
    HDC hdc;
    HGLRC context;
    HPBUFFERARB scrbtch;
    HDC scrbtchDC;
    WGLGrbphicsConfigInfo *wglinfo;
    const unsigned chbr *versionstr;
    const chbr *extstr;
    jint cbps = CAPS_EMPTY;
    int bttrKeys[] = { WGL_DOUBLE_BUFFER_ARB, WGL_ALPHA_BITS_ARB };
    int bttrVbls[2];

    J2dRlsTrbceLn(J2D_TRACE_INFO, "WGLGrbphicsConfig_getWGLConfigInfo");

    // initiblize GL/WGL extension functions
    if (!WGLGC_InitExtFuncs(screennum)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGrbphicsConfig_getWGLConfigInfo: could not init ext funcs");
        return 0L;
    }

    // crebte b scrbtch window
    hwnd = WGLGC_CrebteScrbtchWindow(screennum);
    if (hwnd == 0) {
        return 0L;
    }

    // get the HDC for the scrbtch window
    hdc = GetDC(hwnd);
    if (hdc == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGrbphicsConfig_getWGLConfigInfo: could not get dc for scrbtch window");
        DestroyWindow(hwnd);
        return 0L;
    }

    if (pixfmt == 0) {
        // find bn bppropribte pixel formbt
        pixfmt = WGLGC_GetPixelFormbtForDC(hdc);
        if (pixfmt == 0) {
            J2dRlsTrbceLn(J2D_TRACE_ERROR,
                "WGLGrbphicsConfig_getWGLConfigInfo: could not find bppropribte pixfmt");
            RelebseDC(hwnd, hdc);
            DestroyWindow(hwnd);
            return 0L;
        }
    }

    if (shbredContext == 0) {
        // crebte the one shbred context
        shbredContext = WGLGC_CrebteContext(screennum, pixfmt);
        if (shbredContext == 0) {
            J2dRlsTrbceLn(J2D_TRACE_ERROR,
                "WGLGrbphicsConfig_getWGLConfigInfo: could not crebte shbred context");
            RelebseDC(hwnd, hdc);
            DestroyWindow(hwnd);
            return 0L;
        }
    }

    // set the pixel formbt for the scrbtch window
    if (!SetPixelFormbt(hdc, pixfmt, &pfd)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGrbphicsconfig_getWGLConfigInfo: error setting pixel formbt");
        RelebseDC(hwnd, hdc);
        DestroyWindow(hwnd);
        return 0L;
    }

    // crebte the HGLRC (context) for this WGLGrbphicsConfig
    context = j2d_wglCrebteContext(hdc);
    if (context == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGrbphicsConfig_getWGLConfigInfo: could not crebte WGL context");
        RelebseDC(hwnd, hdc);
        DestroyWindow(hwnd);
        return 0L;
    }

    // REMIND: when using wglShbreLists, the two contexts must use bn
    //         identicbl pixel formbt...
    if (!j2d_wglShbreLists(shbredContext, context)) {
        J2dRlsTrbceLn(J2D_TRACE_WARNING,
            "WGLGrbphicsConfig_getWGLConfigInfo: unbble to shbre lists");
    }

    // mbke the context current so thbt we cbn query the OpenGL version
    // bnd extension strings
    if (!j2d_wglMbkeCurrent(hdc, context)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGrbphicsConfig_getWGLConfigInfo: could not mbke temp context current");
        j2d_wglDeleteContext(context);
        RelebseDC(hwnd, hdc);
        DestroyWindow(hwnd);
        return 0L;
    }

    // get version bnd extension strings
    versionstr = j2d_glGetString(GL_VERSION);
    extstr = j2d_wglGetExtensionsStringARB(hdc);
    OGLContext_GetExtensionInfo(env, &cbps);

    J2dRlsTrbceLn1(J2D_TRACE_INFO,
        "WGLGrbphicsConfig_getWGLConfigInfo: OpenGL version=%s",
                   (versionstr == NULL) ? "null" : (chbr *)versionstr);

    if (!OGLContext_IsVersionSupported(versionstr)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGrbphicsConfig_getWGLConfigInfo: OpenGL 1.2 is required");
        j2d_wglMbkeCurrent(NULL, NULL);
        j2d_wglDeleteContext(context);
        RelebseDC(hwnd, hdc);
        DestroyWindow(hwnd);
        return 0L;
    }

    // check for required WGL extensions
    if (!OGLContext_IsExtensionAvbilbble(extstr, "WGL_ARB_pbuffer") ||
        !OGLContext_IsExtensionAvbilbble(extstr, "WGL_ARB_mbke_current_rebd")||
        !OGLContext_IsExtensionAvbilbble(extstr, "WGL_ARB_pixel_formbt"))
    {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGrbphicsConfig_getWGLConfigInfo: required ext(s) unbvbilbble");
        j2d_wglMbkeCurrent(NULL, NULL);
        j2d_wglDeleteContext(context);
        RelebseDC(hwnd, hdc);
        DestroyWindow(hwnd);
        return 0L;
    }

    // get config-specific cbpbbilities
    j2d_wglGetPixelFormbtAttribivARB(hdc, pixfmt, 0, 2, bttrKeys, bttrVbls);
    if (bttrVbls[0]) {
        cbps |= CAPS_DOUBLEBUFFERED;
    }
    if (bttrVbls[1] > 0) {
        cbps |= CAPS_STORED_ALPHA;
    }

    // crebte the scrbtch pbuffer
    scrbtch = j2d_wglCrebtePbufferARB(hdc, pixfmt, 1, 1, NULL);

    // destroy the temporbry resources
    j2d_wglMbkeCurrent(NULL, NULL);
    RelebseDC(hwnd, hdc);
    DestroyWindow(hwnd);

    if (scrbtch == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGrbphicsConfig_getWGLConfigInfo: could not crebte scrbtch surfbce");
        j2d_wglDeleteContext(context);
        return 0L;
    }

    // get the HDC for the scrbtch pbuffer
    scrbtchDC = j2d_wglGetPbufferDCARB(scrbtch);
    if (scrbtchDC == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGrbphicsConfig_getWGLConfigInfo: could not get hdc for scrbtch surfbce");
        j2d_wglDeleteContext(context);
        j2d_wglDestroyPbufferARB(scrbtch);
        return 0L;
    }

    // initiblize the OGLContext, which wrbps the pixfmt bnd HGLRC (context)
    oglc = WGLGC_InitOGLContext(pixfmt, context, scrbtch, scrbtchDC, cbps);
    if (oglc == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGrbphicsConfig_getWGLConfigInfo: could not crebte oglc");
        j2d_wglDeleteContext(context);
        j2d_wglRelebsePbufferDCARB(scrbtch, scrbtchDC);
        j2d_wglDestroyPbufferARB(scrbtch);
        return 0L;
    }

    J2dTrbceLn(J2D_TRACE_VERBOSE,
        "WGLGrbphicsConfig_getWGLConfigInfo: finished checking dependencies");

    // crebte the WGLGrbphicsConfigInfo record for this config
    wglinfo = (WGLGrbphicsConfigInfo *)mblloc(sizeof(WGLGrbphicsConfigInfo));
    if (wglinfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "WGLGrbphicsConfig_getWGLConfigInfo: could not bllocbte memory for wglinfo");
        WGLGC_DestroyOGLContext(oglc);
        return 0L;
    }

    wglinfo->screen = screennum;
    wglinfo->pixfmt = pixfmt;
    wglinfo->context = oglc;

    return ptr_to_jlong(wglinfo);
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_opengl_WGLGrbphicsConfig_getDefbultPixFmt(JNIEnv *env,
                                                          jclbss wglgc,
                                                          jint screennum)
{
    J2dTrbceLn(J2D_TRACE_INFO, "WGLGrbphicsConfig_getDefbultPixFmt");

    // REMIND: eventublly we should implement this method so thbt it finds
    //         the most bppropribte defbult pixel formbt for the given
    //         device; for now, we'll just return 0, bnd then we'll find
    //         bn bppropribte pixel formbt in WGLGC_GetWGLConfigInfo()...
    return 0;
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_opengl_WGLGrbphicsConfig_getOGLCbpbbilities(JNIEnv *env,
                                                            jclbss wglgc,
                                                            jlong configInfo)
{
    WGLGrbphicsConfigInfo *wglinfo =
        (WGLGrbphicsConfigInfo *)jlong_to_ptr(configInfo);

    J2dTrbceLn(J2D_TRACE_INFO, "WGLGrbphicsConfig_getOGLCbpbbilities");

    if (wglinfo == NULL || wglinfo->context == NULL) {
        return CAPS_EMPTY;
    }

    return wglinfo->context->cbps;
}
