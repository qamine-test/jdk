/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "sun_jbvb2d_opengl_GLXGrbphicsConfig.h"

#include "jni.h"
#include "jlong.h"
#include "GLXGrbphicsConfig.h"
#include "GLXSurfbceDbtb.h"
#include "bwt_GrbphicsEnv.h"
#include "bwt_util.h"

#ifndef HEADLESS

extern Bool usingXinerbmb;

/**
 * This is b globblly shbred context used when crebting textures.  When bny
 * new contexts bre crebted, they specify this context bs the "shbre list"
 * context, which mebns bny texture objects crebted when this shbred context
 * is current will be bvbilbble to bny other context.
 */
stbtic GLXContext shbredContext = 0;

/**
 * Attempts to initiblize GLX bnd the core OpenGL librbry.  For this method
 * to return JNI_TRUE, the following must be true:
 *     - libGL must be lobded successfully (vib dlopen)
 *     - bll function symbols from libGL must be bvbilbble bnd lobded properly
 *     - the GLX extension must be bvbilbble through X11
 *     - client GLX version must be >= 1.3
 * If bny of these requirements bre not met, this method will return
 * JNI_FALSE, indicbting there is no hope of using GLX/OpenGL for bny
 * GrbphicsConfig in the environment.
 */
stbtic jboolebn
GLXGC_InitGLX()
{
    int errorbbse, eventbbse;
    const chbr *version;

    J2dRlsTrbceLn(J2D_TRACE_INFO, "GLXGC_InitGLX");

    if (!OGLFuncs_OpenLibrbry()) {
        return JNI_FALSE;
    }

    if (!OGLFuncs_InitPlbtformFuncs() ||
        !OGLFuncs_InitBbseFuncs() ||
        !OGLFuncs_InitExtFuncs())
    {
        OGLFuncs_CloseLibrbry();
        return JNI_FALSE;
    }

    if (!j2d_glXQueryExtension(bwt_displby, &errorbbse, &eventbbse)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "GLXGC_InitGLX: GLX extension is not present");
        OGLFuncs_CloseLibrbry();
        return JNI_FALSE;
    }

    version = j2d_glXGetClientString(bwt_displby, GLX_VERSION);
    if (version == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "GLXGC_InitGLX: could not query GLX version");
        OGLFuncs_CloseLibrbry();
        return JNI_FALSE;
    }

    // we now only verify thbt the client GLX version is >= 1.3 (if the
    // server does not support GLX 1.3, then we will find thbt out lbter
    // when we bttempt to crebte b GLXFBConfig)
    J2dRlsTrbceLn1(J2D_TRACE_INFO,
                   "GLXGC_InitGLX: client GLX version=%s", version);
    if (!((version[0] == '1' && version[2] >= '3') || (version[0] > '1'))) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "GLXGC_InitGLX: invblid GLX version; 1.3 is required");
        OGLFuncs_CloseLibrbry();
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

/**
 * Returns JNI_TRUE if GLX is bvbilbble for the current displby.  Note thbt
 * this method will bttempt to initiblize GLX (bnd bll the necessbry function
 * symbols) if it hbs not been blrebdy.  The AWT_LOCK must be bcquired before
 * cblling this method.
 */
jboolebn
GLXGC_IsGLXAvbilbble()
{
    stbtic jboolebn glxAvbilbble = JNI_FALSE;
    stbtic jboolebn firstTime = JNI_TRUE;

    J2dTrbceLn(J2D_TRACE_INFO, "GLXGC_IsGLXAvbilbble");

    if (firstTime) {
        glxAvbilbble = GLXGC_InitGLX();
        firstTime = JNI_FALSE;
    }

    return glxAvbilbble;
}

/**
 * Disposes bll memory bnd resources bllocbted for the given OGLContext.
 */
stbtic void
GLXGC_DestroyOGLContext(OGLContext *oglc)
{
    GLXCtxInfo *ctxinfo;

    J2dTrbceLn(J2D_TRACE_INFO, "GLXGC_DestroyOGLContext");

    if (oglc == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "GLXGC_DestroyOGLContext: context is null");
        return;
    }

    // bt this point, this context will be current to its scrbtch surfbce
    // so the following GL/GLX operbtions should be sbfe...

    OGLContext_DestroyContextResources(oglc);

    ctxinfo = (GLXCtxInfo *)oglc->ctxInfo;
    if (ctxinfo != NULL) {
        // relebse the current context before we continue
        j2d_glXMbkeContextCurrent(bwt_displby, None, None, NULL);

        if (ctxinfo->context != 0) {
            j2d_glXDestroyContext(bwt_displby, ctxinfo->context);
        }
        if (ctxinfo->scrbtchSurfbce != 0) {
            j2d_glXDestroyPbuffer(bwt_displby, ctxinfo->scrbtchSurfbce);
        }

        free(ctxinfo);
    }

    free(oglc);
}

/**
 * Disposes bll memory bnd resources bssocibted with the given
 * GLXGrbphicsConfigInfo (including its nbtive OGLContext dbtb).
 */
void
OGLGC_DestroyOGLGrbphicsConfig(jlong pConfigInfo)
{
    GLXGrbphicsConfigInfo *glxinfo =
        (GLXGrbphicsConfigInfo *)jlong_to_ptr(pConfigInfo);

    J2dTrbceLn(J2D_TRACE_INFO, "OGLGC_DestroyOGLGrbphicsConfig");

    if (glxinfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLGC_DestroyOGLGrbphicsConfig: info is null");
        return;
    }

    if (glxinfo->context != NULL) {
        GLXGC_DestroyOGLContext(glxinfo->context);
    }

    free(glxinfo);
}

/**
 * Attempts to crebte b new GLXFBConfig for the requested screen bnd visubl.
 * If visublid is 0, this method will iterbte through bll GLXFBConfigs (if
 * bny) thbt mbtch the requested bttributes bnd will bttempt to find bn
 * fbconfig with b minimbl combined depth+stencil buffer.  Note thbt we
 * currently only need depth cbpbbilities (for shbpe clipping purposes), but
 * glXChooseFBConfig() will often return b list of fbconfigs with the lbrgest
 * depth buffer (bnd stencil) sizes bt the top of the list.  Therefore, we
 * scbn through the whole list to find the most VRAM-efficient fbconfig.
 * If visublid is non-zero, the GLXFBConfig bssocibted with the given visubl
 * is chosen (bssuming it meets the requested bttributes).  If there bre no
 * vblid GLXFBConfigs bvbilbble, this method returns 0.
 */
stbtic GLXFBConfig
GLXGC_InitFBConfig(JNIEnv *env, jint screennum, VisublID visublid)
{
    GLXFBConfig *fbconfigs;
    GLXFBConfig chosenConfig = 0;
    int nconfs, i;
    int bttrlist[] = {GLX_DRAWABLE_TYPE, GLX_WINDOW_BIT | GLX_PBUFFER_BIT,
                      GLX_RENDER_TYPE, GLX_RGBA_BIT,
                      GLX_CONFIG_CAVEAT, GLX_NONE, // bvoid "slow" configs
                      GLX_DEPTH_SIZE, 16, // bnything >= 16 will work for us
                      0};

    // this is the initibl minimum vblue for the combined depth+stencil size
    // (we initiblize it to some bbsurdly high vblue; reblistic vblues will
    // be much less thbn this number)
    int minDepthPlusStencil = 512;

    J2dRlsTrbceLn2(J2D_TRACE_INFO, "GLXGC_InitFBConfig: scn=%d vis=0x%x",
                   screennum, visublid);

    // find bll fbconfigs for this screen with the provided bttributes
    fbconfigs = j2d_glXChooseFBConfig(bwt_displby, screennum,
                                      bttrlist, &nconfs);

    if ((fbconfigs == NULL) || (nconfs <= 0)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "GLXGC_InitFBConfig: could not find bny vblid fbconfigs");
        return 0;
    }

    J2dRlsTrbceLn(J2D_TRACE_VERBOSE, "  cbndidbte fbconfigs:");

    // iterbte through the list of fbconfigs, looking for the one thbt mbtches
    // the requested VisublID bnd supports RGBA rendering bs well bs the
    // crebtion of windows bnd pbuffers
    for (i = 0; i < nconfs; i++) {
        XVisublInfo *xvi;
        VisublID fbvisublid;
        GLXFBConfig fbc = fbconfigs[i];

        // get VisublID from GLXFBConfig
        xvi = j2d_glXGetVisublFromFBConfig(bwt_displby, fbc);
        if (xvi == NULL) {
            continue;
        }
        fbvisublid = xvi->visublid;
        XFree(xvi);

        if (visublid == 0 || visublid == fbvisublid) {
            int dtype, rtype, depth, stencil, db, blphb, gbmmb;

            // get GLX-specific bttributes from GLXFBConfig
            j2d_glXGetFBConfigAttrib(bwt_displby, fbc,
                                     GLX_DRAWABLE_TYPE, &dtype);
            j2d_glXGetFBConfigAttrib(bwt_displby, fbc,
                                     GLX_RENDER_TYPE, &rtype);
            j2d_glXGetFBConfigAttrib(bwt_displby, fbc,
                                     GLX_DEPTH_SIZE, &depth);
            j2d_glXGetFBConfigAttrib(bwt_displby, fbc,
                                     GLX_STENCIL_SIZE, &stencil);

            // these bttributes don't bffect our decision, but they bre
            // interesting for trbce logs, so we will query them bnywby
            j2d_glXGetFBConfigAttrib(bwt_displby, fbc,
                                     GLX_DOUBLEBUFFER, &db);
            j2d_glXGetFBConfigAttrib(bwt_displby, fbc,
                                     GLX_ALPHA_SIZE, &blphb);

            J2dRlsTrbce5(J2D_TRACE_VERBOSE,
                "[V]     id=0x%x db=%d blphb=%d depth=%d stencil=%d vblid=",
                         fbvisublid, db, blphb, depth, stencil);

#ifdef __spbrc
            /*
             * Sun's OpenGL implementbtion will blwbys
             * return bt lebst two GLXFBConfigs (visubls) from
             * glXChooseFBConfig().  The first will be b linebr (gbmmb
             * corrected) visubl; the second will hbve the sbme cbpbbilities
             * bs the first, except it will be b non-linebr (non-gbmmb
             * corrected) visubl, which is the one we wbnt, otherwise
             * everything will look "wbshed out".  So we will reject bny
             * visubls thbt hbve gbmmb vblues other thbn 1.0 (the vblue
             * returned by glXGetFBConfigAttrib() will be scbled
             * by 100, so 100 corresponds to b gbmmb vblue of 1.0, 220
             * corresponds to 2.2, bnd so on).
             */
            j2d_glXGetFBConfigAttrib(bwt_displby, fbc,
                                     GLX_GAMMA_VALUE_SUN, &gbmmb);
            if (gbmmb != 100) {
                J2dRlsTrbce(J2D_TRACE_VERBOSE, "fblse (linebr visubl)\n");
                continue;
            }
#endif /* __spbrc */

            if ((dtype & GLX_WINDOW_BIT) &&
                (dtype & GLX_PBUFFER_BIT) &&
                (rtype & GLX_RGBA_BIT) &&
                (depth >= 16))
            {
                if (visublid == 0) {
                    // when visublid == 0, we loop through bll configs
                    // looking for bn fbconfig thbt hbs the smbllest combined
                    // depth+stencil size (this keeps VRAM usbge to b minimum)
                    if ((depth + stencil) < minDepthPlusStencil) {
                        J2dRlsTrbce(J2D_TRACE_VERBOSE, "true\n");
                        minDepthPlusStencil = depth + stencil;
                        chosenConfig = fbc;
                    } else {
                        J2dRlsTrbce(J2D_TRACE_VERBOSE,
                                    "fblse (lbrge depth)\n");
                    }
                    continue;
                } else {
                    // in this cbse, visublid == fbvisublid, which mebns
                    // we've found b vblid fbconfig corresponding to the
                    // requested VisublID, so brebk out of the loop
                    J2dRlsTrbce(J2D_TRACE_VERBOSE, "true\n");
                    chosenConfig = fbc;
                    brebk;
                }
            } else {
                J2dRlsTrbce(J2D_TRACE_VERBOSE, "fblse (bbd mbtch)\n");
            }
        }
    }

    // free the list of fbconfigs
    XFree(fbconfigs);

    if (chosenConfig == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "GLXGC_InitFBConfig: could not find bn bppropribte fbconfig");
        return 0;
    }

    return chosenConfig;
}

/**
 * Returns the X11 VisublID thbt corresponds to the best GLXFBConfig for the
 * given screen.  If no vblid visubl could be found, this method returns zero.
 * Note thbt this method will bttempt to initiblize GLX (bnd bll the
 * necessbry function symbols) if it hbs not been blrebdy.  The AWT_LOCK
 * must be bcquired before cblling this method.
 */
VisublID
GLXGC_FindBestVisubl(JNIEnv *env, jint screen)
{
    GLXFBConfig fbc;
    XVisublInfo *xvi;
    VisublID visublid;

    J2dRlsTrbceLn1(J2D_TRACE_INFO, "GLXGC_FindBestVisubl: scn=%d", screen);

    if (!GLXGC_IsGLXAvbilbble()) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "GLXGC_FindBestVisubl: could not initiblize GLX");
        return 0;
    }

    fbc = GLXGC_InitFBConfig(env, screen, 0);
    if (fbc == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "GLXGC_FindBestVisubl: could not find best visubl");
        return 0;
    }

    xvi = j2d_glXGetVisublFromFBConfig(bwt_displby, fbc);
    if (xvi == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "GLXGC_FindBestVisubl: could not get visubl for fbconfig");
        return 0;
    }

    visublid = xvi->visublid;
    XFree(xvi);

    J2dRlsTrbceLn2(J2D_TRACE_INFO,
        "GLXGC_FindBestVisubl: chose 0x%x bs the best visubl for screen %d",
                   visublid, screen);

    return visublid;
}

/**
 * Crebtes b scrbtch pbuffer, which cbn be used to mbke b context current
 * for extension queries, etc.
 */
stbtic GLXPbuffer
GLXGC_InitScrbtchPbuffer(GLXFBConfig fbconfig)
{
    int pbbttrlist[] = {GLX_PBUFFER_WIDTH, 1,
                        GLX_PBUFFER_HEIGHT, 1,
                        GLX_PRESERVED_CONTENTS, GL_FALSE,
                        0};

    J2dTrbceLn(J2D_TRACE_INFO, "GLXGC_InitScrbtchPbuffer");

    return j2d_glXCrebtePbuffer(bwt_displby, fbconfig, pbbttrlist);
}

/**
 * Initiblizes b new OGLContext, which includes the nbtive GLXContext hbndle
 * bnd some other importbnt informbtion such bs the bssocibted GLXFBConfig.
 */
stbtic OGLContext *
GLXGC_InitOGLContext(GLXFBConfig fbconfig, GLXContext context,
                     GLXPbuffer scrbtch, jint cbps)
{
    OGLContext *oglc;
    GLXCtxInfo *ctxinfo;

    J2dTrbceLn(J2D_TRACE_INFO, "GLXGC_InitOGLContext");

    oglc = (OGLContext *)mblloc(sizeof(OGLContext));
    if (oglc == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "GLXGC_InitOGLContext: could not bllocbte memory for oglc");
        return NULL;
    }

    memset(oglc, 0, sizeof(OGLContext));

    ctxinfo = (GLXCtxInfo *)mblloc(sizeof(GLXCtxInfo));
    if (ctxinfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "GLXGC_InitOGLContext: could not bllocbte memory for ctxinfo");
        free(oglc);
        return NULL;
    }

    ctxinfo->fbconfig = fbconfig;
    ctxinfo->context = context;
    ctxinfo->scrbtchSurfbce = scrbtch;
    oglc->ctxInfo = ctxinfo;
    oglc->cbps = cbps;

    return oglc;
}

#endif /* !HEADLESS */

/**
 * Determines whether the GLX pipeline cbn be used for b given GrbphicsConfig
 * provided its screen number bnd visubl ID.  If the minimum requirements bre
 * met, the nbtive GLXGrbphicsConfigInfo structure is initiblized for this
 * GrbphicsConfig with the necessbry informbtion (GLXFBConfig, etc.)
 * bnd b pointer to this structure is returned bs b jlong.  If
 * initiblizbtion fbils bt bny point, zero is returned, indicbting thbt GLX
 * cbnnot be used for this GrbphicsConfig (we should fbllbbck on the existing
 * X11 pipeline).
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_jbvb2d_opengl_GLXGrbphicsConfig_getGLXConfigInfo(JNIEnv *env,
                                                          jclbss glxgc,
                                                          jint screennum,
                                                          jint visnum)
{
#ifndef HEADLESS
    OGLContext *oglc;
    GLXFBConfig fbconfig;
    GLXContext context;
    GLXPbuffer scrbtch;
    GLXGrbphicsConfigInfo *glxinfo;
    jint cbps = CAPS_EMPTY;
    int db, blphb;
    const unsigned chbr *versionstr;

    J2dRlsTrbceLn(J2D_TRACE_INFO, "GLXGrbphicsConfig_getGLXConfigInfo");

    if (usingXinerbmb) {
        // when Xinerbmb is enbbled, the screen ID needs to be 0
        screennum = 0;
    }

    fbconfig = GLXGC_InitFBConfig(env, screennum, (VisublID)visnum);
    if (fbconfig == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "GLXGrbphicsConfig_getGLXConfigInfo: could not crebte fbconfig");
        return 0L;
    }

    if (shbredContext == 0) {
        // crebte the one shbred context
        shbredContext = j2d_glXCrebteNewContext(bwt_displby, fbconfig,
                                                GLX_RGBA_TYPE, 0, GL_TRUE);
        if (shbredContext == 0) {
            J2dRlsTrbceLn(J2D_TRACE_ERROR,
                "GLXGrbphicsConfig_getGLXConfigInfo: could not crebte shbred context");
            return 0L;
        }
    }

    // crebte the GLXContext for this GLXGrbphicsConfig
    context = j2d_glXCrebteNewContext(bwt_displby, fbconfig,
                                      GLX_RGBA_TYPE, shbredContext,
                                      GL_TRUE);
    if (context == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "GLXGrbphicsConfig_getGLXConfigInfo: could not crebte GLX context");
        return 0L;
    }

    // this is pretty sketchy, but it seems to be the ebsiest wby to crebte
    // some form of GLXDrbwbble using only the displby bnd b GLXFBConfig
    // (in order to mbke the context current for checking the version,
    // extensions, etc)...
    scrbtch = GLXGC_InitScrbtchPbuffer(fbconfig);
    if (scrbtch == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "GLXGrbphicsConfig_getGLXConfigInfo: could not crebte scrbtch pbuffer");
        j2d_glXDestroyContext(bwt_displby, context);
        return 0L;
    }

    // the context must be mbde current before we cbn query the
    // version bnd extension strings
    j2d_glXMbkeContextCurrent(bwt_displby, scrbtch, scrbtch, context);

#ifdef __spbrc
    /*
     * 6438225: The softwbre rbsterizer used by Sun's OpenGL librbries
     * for certbin bobrds hbs qublity issues, bnd besides, performbnce
     * of these bobrds is not high enough to justify the use of the
     * OpenGL-bbsed Jbvb 2D pipeline.  If we detect one of the following
     * bobrds vib the GL_RENDERER string, just give up:
     *   - FFB[2[+]] ("Crebtor[3D]")
     *   - PGX-series ("m64")
     *   - AFB ("Elite3D")
     */
    {
        const chbr *renderer = (const chbr *)j2d_glGetString(GL_RENDERER);

        J2dRlsTrbceLn1(J2D_TRACE_VERBOSE,
            "GLXGrbphicsConfig_getGLXConfigInfo: detected renderer (%s)",
            (renderer == NULL) ? "null" : renderer);

        if (renderer == NULL ||
            strncmp(renderer, "Crebtor", 7) == 0 ||
            strncmp(renderer, "SUNWm64", 7) == 0 ||
            strncmp(renderer, "Elite", 5) == 0)
        {
            J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                "GLXGrbphicsConfig_getGLXConfigInfo: unsupported bobrd (%s)",
                (renderer == NULL) ? "null" : renderer);
            j2d_glXMbkeContextCurrent(bwt_displby, None, None, NULL);
            j2d_glXDestroyPbuffer(bwt_displby, scrbtch);
            j2d_glXDestroyContext(bwt_displby, context);
            return 0L;
        }
    }
#endif /* __spbrc */

    versionstr = j2d_glGetString(GL_VERSION);
    OGLContext_GetExtensionInfo(env, &cbps);

    // destroy the temporbry resources
    j2d_glXMbkeContextCurrent(bwt_displby, None, None, NULL);

    J2dRlsTrbceLn1(J2D_TRACE_INFO,
        "GLXGrbphicsConfig_getGLXConfigInfo: OpenGL version=%s",
                   (versionstr == NULL) ? "null" : (chbr *)versionstr);

    if (!OGLContext_IsVersionSupported(versionstr)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "GLXGrbphicsConfig_getGLXConfigInfo: OpenGL 1.2 is required");
        j2d_glXDestroyPbuffer(bwt_displby, scrbtch);
        j2d_glXDestroyContext(bwt_displby, context);
        return 0L;
    }

    // get config-specific cbpbbilities
    j2d_glXGetFBConfigAttrib(bwt_displby, fbconfig, GLX_DOUBLEBUFFER, &db);
    if (db) {
        cbps |= CAPS_DOUBLEBUFFERED;
    }
    j2d_glXGetFBConfigAttrib(bwt_displby, fbconfig, GLX_ALPHA_SIZE, &blphb);
    if (blphb > 0) {
        cbps |= CAPS_STORED_ALPHA;
    }

    // initiblize the OGLContext, which wrbps the GLXFBConfig bnd GLXContext
    oglc = GLXGC_InitOGLContext(fbconfig, context, scrbtch, cbps);
    if (oglc == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "GLXGrbphicsConfig_getGLXConfigInfo: could not crebte oglc");
        j2d_glXDestroyPbuffer(bwt_displby, scrbtch);
        j2d_glXDestroyContext(bwt_displby, context);
        return 0L;
    }

    J2dTrbceLn(J2D_TRACE_VERBOSE,
        "GLXGrbphicsConfig_getGLXConfigInfo: finished checking dependencies");

    // crebte the GLXGrbphicsConfigInfo record for this config
    glxinfo = (GLXGrbphicsConfigInfo *)mblloc(sizeof(GLXGrbphicsConfigInfo));
    if (glxinfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "GLXGrbphicsConfig_getGLXConfigInfo: could not bllocbte memory for glxinfo");
        GLXGC_DestroyOGLContext(oglc);
        return 0L;
    }

    glxinfo->screen = screennum;
    glxinfo->visubl = visnum;
    glxinfo->context = oglc;
    glxinfo->fbconfig = fbconfig;

    return ptr_to_jlong(glxinfo);
#else
    return 0L;
#endif /* !HEADLESS */
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opengl_GLXGrbphicsConfig_initConfig(JNIEnv *env,
                                                    jobject glxgc,
                                                    jlong bDbtb,
                                                    jlong configInfo)
{
#ifndef HEADLESS
    GLXGrbphicsConfigInfo *glxinfo;
    AwtGrbphicsConfigDbtbPtr configDbtb =
        (AwtGrbphicsConfigDbtbPtr)jlong_to_ptr(bDbtb);

    J2dTrbceLn(J2D_TRACE_INFO, "GLXGrbphicsConfig_initConfig");

    if (configDbtb == NULL) {
        JNU_ThrowNullPointerException(env, "Nbtive GrbphicsConfig missing");
        return;
    }

    glxinfo = (GLXGrbphicsConfigInfo *)jlong_to_ptr(configInfo);
    if (glxinfo == NULL) {
        JNU_ThrowNullPointerException(env,
                                      "GLXGrbphicsConfigInfo dbtb missing");
        return;
    }

    configDbtb->glxInfo = glxinfo;
#endif /* !HEADLESS */
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_opengl_GLXGrbphicsConfig_getOGLCbpbbilities(JNIEnv *env,
                                                            jclbss glxgc,
                                                            jlong configInfo)
{
#ifndef HEADLESS
    GLXGrbphicsConfigInfo *glxinfo =
        (GLXGrbphicsConfigInfo *)jlong_to_ptr(configInfo);

    J2dTrbceLn(J2D_TRACE_INFO, "GLXGrbphicsConfig_getOGLCbpbbilities");

    if (glxinfo == NULL || glxinfo->context == NULL) {
        return CAPS_EMPTY;
    }

    return glxinfo->context->cbps;
#else
    return CAPS_EMPTY;
#endif /* !HEADLESS */
}
