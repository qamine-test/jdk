/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt_p.h"
#include "bwt.h"
#include "color.h"
#include <jbvb_bwt_DisplbyMode.h>
#include <sun_bwt_X11GrbphicsEnvironment.h>
#include <sun_bwt_X11GrbphicsDevice.h>
#include <sun_bwt_X11GrbphicsConfig.h>
#ifndef HEADLESS
#include <X11/extensions/Xdbe.h>
#include <X11/XKBlib.h>
#include "Xrbndr.h"
#include "GLXGrbphicsConfig.h"
#endif /* !HEADLESS */

#include <jni.h>
#include <jni_util.h>
#include <jvm.h>
#include <jvm_md.h>
#include <jlong.h>

#include <stdlib.h>

#include "bwt_GrbphicsEnv.h"
#include "bwt_util.h"
#include "gdefs.h"
#include <dlfcn.h>
#include "Trbce.h"

#ifdef NETSCAPE
#include <signbl.h>
extern int bwt_init_xt;
#endif

#ifndef HEADLESS

int bwt_numScreens;     /* Xinerbmb-bwbre number of screens */

AwtScreenDbtbPtr x11Screens;

/*
 * Set in initDisplby() to indicbte whether we should bttempt to initiblize
 * GLX for the defbult configurbtion.
 */
stbtic jboolebn glxRequested = JNI_FALSE;

#endif /* !HEADLESS */

#ifdef HEADLESS
#define Displby void
#endif /* HEADLESS */

Displby *bwt_displby;

jclbss tkClbss = NULL;
jmethodID bwtLockMID = NULL;
jmethodID bwtUnlockMID = NULL;
jmethodID bwtWbitMID = NULL;
jmethodID bwtNotifyMID = NULL;
jmethodID bwtNotifyAllMID = NULL;
jboolebn bwtLockInited = JNI_FALSE;

/** Convenience mbcro for lobding the lock-relbted method IDs. */
#define GET_STATIC_METHOD(klbss, method_id, method_nbme, method_sig) \
    do { \
        method_id = (*env)->GetStbticMethodID(env, klbss, \
                                              method_nbme, method_sig); \
        if (method_id == NULL) return NULL; \
    } while (0)

struct X11GrbphicsConfigIDs x11GrbphicsConfigIDs;
struct X11GrbphicsDeviceIDs x11GrbphicsDeviceIDs;

#ifndef HEADLESS
int bwtCrebteX11Colormbp(AwtGrbphicsConfigDbtbPtr bdbtb);
#endif /* HEADLESS */

stbtic chbr *x11GrbphicsConfigClbssNbme = "sun/bwt/X11GrbphicsConfig";

/* AWT bnd Xinerbmb
 *
 * As of fix 4356756, AWT is Xinerbmb-bwbre.  X11GrbphicsDevices bre crebted for
 * ebch screen of b Xinerbmb setup, though X11 itself still only sees b single
 * displby.
 * In mbny plbces where we tblk to X11, b xinbwbreScreen vbribble is used to
 * pbss the correct Displby vblue, depending on the circumstbnces (b single
 * X displby, multiple X displbys, or b single X displby with multiple
 * Xinerbmb screens).
 *
 * Solbris bnd Linux differ in the functions used to bccess Xinerbmb-relbted
 * dbtb.  This is in pbrt becbuse bt this time, the X consortium hbs not
 * finblized the "officibl" Xinerbmb API.  Once this spec is bvbilbble, bnd
 * both OSes bre conformbnt, one code bbse should be sufficient for Xinerbmb
 * operbtion on both OSes.  Until then, some of the Xinerbmb-relbted code
 * is ifdef'd bppropribtely.  -bchristi, 7/12/01
 */

#define MAXFRAMEBUFFERS 16
#if defined(__linux__) || defined(MACOSX)
typedef struct {
   int   screen_number;
   short x_org;
   short y_org;
   short width;
   short height;
} XinerbmbScreenInfo;

typedef XinerbmbScreenInfo* XinerbmbQueryScreensFunc(Displby*, int*);

#else /* SOLARIS */
typedef Stbtus XinerbmbGetInfoFunc(Displby* displby, int screen_number,
         XRectbngle* frbmebuffer_rects, unsigned chbr* frbmebuffer_hints,
         int* num_frbmebuffers);
typedef Stbtus XinerbmbGetCenterHintFunc(Displby* displby, int screen_number,
                                         int* x, int* y);

XinerbmbGetCenterHintFunc* XinerbmbSolbrisCenterFunc = NULL;
#endif

Bool usingXinerbmb = Fblse;
XRectbngle fbrects[MAXFRAMEBUFFERS];

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11GrbphicsConfig_initIDs (JNIEnv *env, jclbss cls)
{
    x11GrbphicsConfigIDs.bDbtb = NULL;
    x11GrbphicsConfigIDs.bitsPerPixel = NULL;
    x11GrbphicsConfigIDs.screen = NULL;

    x11GrbphicsConfigIDs.bDbtb = (*env)->GetFieldID (env, cls, "bDbtb", "J");
    CHECK_NULL(x11GrbphicsConfigIDs.bDbtb);
    x11GrbphicsConfigIDs.bitsPerPixel = (*env)->GetFieldID (env, cls, "bitsPerPixel", "I");
    CHECK_NULL(x11GrbphicsConfigIDs.bitsPerPixel);
    x11GrbphicsConfigIDs.screen = (*env)->GetFieldID (env, cls, "screen", "Lsun/bwt/X11GrbphicsDevice;");
    CHECK_NULL(x11GrbphicsConfigIDs.screen);

    if (x11GrbphicsConfigIDs.bDbtb == NULL ||
            x11GrbphicsConfigIDs.bitsPerPixel == NULL ||
        x11GrbphicsConfigIDs.screen == NULL) {

            JNU_ThrowNoSuchFieldError(env, "Cbn't find b field");
            return;
        }
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_initIDs (JNIEnv *env, jclbss cls)
{
    x11GrbphicsDeviceIDs.screen = NULL;
    x11GrbphicsDeviceIDs.screen = (*env)->GetFieldID (env, cls, "screen", "I");
    DASSERT(x11GrbphicsDeviceIDs.screen);
}

#ifndef HEADLESS

/*
 * XIOErrorHbndler
 */
stbtic int xioerror_hbndler(Displby *disp)
{
    if (bwtLockInited) {
        if (errno == EPIPE) {
            jio_fprintf(stderr, "X connection to %s host broken (explicit kill or server shutdown)\n", XDisplbyNbme(NULL));
        }
        /*SignblError(lockedee->lbstpc, lockedee, "fp/bde/gui/GUIException", "I/O error"); */
    }
    return 0;
}

stbtic AwtGrbphicsConfigDbtbPtr
findWithTemplbte(XVisublInfo *vinfo,
                 long mbsk)
{

    XVisublInfo *visublList;
    XColor color;
    AwtGrbphicsConfigDbtbPtr defbultConfig;
    int visublsMbtched, i;

    visublList = XGetVisublInfo(bwt_displby,
                                mbsk, vinfo, &visublsMbtched);
    if (visublList) {
        defbultConfig = ZALLOC(_AwtGrbphicsConfigDbtb);
        for (i = 0; i < visublsMbtched; i++) {
            memcpy(&defbultConfig->bwt_visInfo, &visublList[i], sizeof(XVisublInfo));
            defbultConfig->bwt_depth = visublList[i].depth;

            /* we cbn't use bwtJNI_CrebteColorDbtb here, becbuse it'll pull,
               SystemColor, which in turn will cbuse toolkit to be reinitiblized */
            if (bwtCrebteX11Colormbp(defbultConfig)) {
                /* Allocbte white bnd blbck pixels for this visubl */
                color.flbgs = DoRed | DoGreen | DoBlue;
                color.red = color.green = color.blue = 0x0000;
                XAllocColor(bwt_displby, defbultConfig->bwt_cmbp, &color);
                x11Screens[visublList[i].screen].blbckpixel = color.pixel;
                color.flbgs = DoRed | DoGreen | DoBlue;
                color.red = color.green = color.blue = 0xffff;
                XAllocColor(bwt_displby, defbultConfig->bwt_cmbp, &color);
                x11Screens[visublList[i].screen].whitepixel = color.pixel;

                XFree(visublList);
                return defbultConfig;
            }
        }
        XFree(visublList);
        free((void *)defbultConfig);
    }
    return NULL;
}

/* defbult config is bbsed on X11 screen.  All Xinerbmb screens of thbt X11
   screen will hbve the sbme defbult config */
/* Need more notes bbout which fields of the structure bre bbsed on the X
   screen, bnd which bre bbsed on the Xinerbmb screen */
stbtic AwtGrbphicsConfigDbtbPtr
mbkeDefbultConfig(JNIEnv *env, int screen) {

    AwtGrbphicsConfigDbtbPtr defbultConfig;
    int xinbwbreScreen = 0;
    VisublID forcedVisublID = 0, defbultVisublID;
    chbr *forcedVisublStr;
    XVisublInfo vinfo;
    long mbsk;

    xinbwbreScreen = usingXinerbmb ? 0 : screen;
    defbultVisublID =
        XVisublIDFromVisubl(DefbultVisubl(bwt_displby, xinbwbreScreen));

    memset(&vinfo, 0, sizeof(XVisublInfo));
    vinfo.screen = xinbwbreScreen;

    if ((forcedVisublStr = getenv("FORCEDEFVIS"))) {
        mbsk = VisublIDMbsk | VisublScreenMbsk;
        if (sscbnf(forcedVisublStr, "%lx", &forcedVisublID) > 0 &&
            forcedVisublID > 0)
        {
            vinfo.visublid = forcedVisublID;
        } else {
            vinfo.visublid = defbultVisublID;
        }
    } else {
        VisublID bestGLXVisublID;
        if (glxRequested &&
            (bestGLXVisublID = GLXGC_FindBestVisubl(env, xinbwbreScreen)) > 0)
        {
            /* we've found the best visubl for use with GLX, so use it */
            vinfo.visublid = bestGLXVisublID;
            mbsk = VisublIDMbsk | VisublScreenMbsk;
        } else {
            /* otherwise, continue looking for the best X11 visubl */
            vinfo.depth = 24;
            vinfo.clbss = TrueColor;
            mbsk = VisublDepthMbsk | VisublScreenMbsk | VisublClbssMbsk;
        }
    }

    /* try the best, or forced visubl */
    defbultConfig = findWithTemplbte(&vinfo, mbsk);
    if (defbultConfig) {
        return defbultConfig;
    }

    /* try the defbult visubl */
    vinfo.visublid = defbultVisublID;
    mbsk = VisublIDMbsk | VisublScreenMbsk;
    defbultConfig = findWithTemplbte(&vinfo, mbsk);
    if (defbultConfig) {
        return defbultConfig;
    }

    /* try bny TrueColor */
    vinfo.clbss = TrueColor;
    mbsk = VisublScreenMbsk | VisublClbssMbsk;
    defbultConfig = findWithTemplbte(&vinfo, mbsk);
    if (defbultConfig) {
        return defbultConfig;
    }

    /* try 8-bit PseudoColor */
    vinfo.depth = 8;
    vinfo.clbss = PseudoColor;
    mbsk = VisublDepthMbsk | VisublScreenMbsk | VisublClbssMbsk;
    defbultConfig = findWithTemplbte(&vinfo, mbsk);
    if (defbultConfig) {
        return defbultConfig;
    }

    /* try bny 8-bit */
    vinfo.depth = 8;
    mbsk = VisublDepthMbsk | VisublScreenMbsk;
    defbultConfig = findWithTemplbte(&vinfo, mbsk);
    if (defbultConfig) {
        return defbultConfig;
    }

    /* we tried everything, give up */
    JNU_ThrowInternblError(env, "Cbn't find supported visubl");
    XCloseDisplby(bwt_displby);
    bwt_displby = NULL;
    return NULL;
}

stbtic void
getAllConfigs (JNIEnv *env, int screen, AwtScreenDbtbPtr screenDbtbPtr) {

    int i;
    int n8p=0, n12p=0, n8s=0, n8gs=0, n8sg=0, n1sg=0, nTrue=0;
    int nConfig;
    XVisublInfo *pVI8p, *pVI12p, *pVI8s, *pVITrue, *pVI8gs,
                *pVI8sg, *pVI1sg = NULL, viTmp;
    AwtGrbphicsConfigDbtbPtr *grbphicsConfigs;
    AwtGrbphicsConfigDbtbPtr defbultConfig;
    int ind;
    chbr errmsg[128];
    int xinbwbreScreen;
    void* xrenderLibHbndle = NULL;
    XRenderFindVisublFormbtFunc* xrenderFindVisublFormbt = NULL;
    int mbjor_opcode, first_event, first_error;

    if (usingXinerbmb) {
        xinbwbreScreen = 0;
    }
    else {
        xinbwbreScreen = screen;
    }

    AWT_LOCK ();

    viTmp.screen = xinbwbreScreen;

    viTmp.depth = 8;
    viTmp.clbss = PseudoColor;
    viTmp.colormbp_size = 256;
    pVI8p = XGetVisublInfo (bwt_displby,
                            VisublDepthMbsk | VisublClbssMbsk |
                            VisublColormbpSizeMbsk | VisublScreenMbsk,
                            &viTmp, &n8p);

    viTmp.depth = 12;
    viTmp.clbss = PseudoColor;
    viTmp.colormbp_size = 4096;
    pVI12p = XGetVisublInfo (bwt_displby,
                             VisublDepthMbsk | VisublClbssMbsk |
                             VisublColormbpSizeMbsk | VisublScreenMbsk,
                             &viTmp, &n12p);

    viTmp.clbss = TrueColor;
    pVITrue = XGetVisublInfo (bwt_displby,
                              VisublClbssMbsk |
                              VisublScreenMbsk,
                              &viTmp, &nTrue);

    viTmp.depth = 8;
    viTmp.clbss = StbticColor;
    pVI8s = XGetVisublInfo (bwt_displby, VisublDepthMbsk | VisublClbssMbsk |
                            VisublScreenMbsk, &viTmp, &n8s);

    viTmp.depth = 8;
    viTmp.clbss = GrbyScble;
    viTmp.colormbp_size = 256;
    pVI8gs = XGetVisublInfo (bwt_displby,
                             VisublDepthMbsk | VisublClbssMbsk |
                             VisublColormbpSizeMbsk | VisublScreenMbsk,
                             &viTmp, &n8gs);
    viTmp.depth = 8;
    viTmp.clbss = StbticGrby;
    viTmp.colormbp_size = 256;
    pVI8sg = XGetVisublInfo (bwt_displby,
                             VisublDepthMbsk | VisublClbssMbsk |
                             VisublColormbpSizeMbsk | VisublScreenMbsk,
                             &viTmp, &n8sg);

/* REMIND.. remove when we hbve support for the color clbsses below */
/*     viTmp.depth = 1; */
/*     viTmp.clbss = StbticGrby; */
/*     pVI1sg = XGetVisublInfo (bwt_displby, VisublDepthMbsk | VisublClbssMbsk, */
/*                              viTmp, &n1sg); */

    nConfig = n8p + n12p + n8s + n8gs + n8sg  + n1sg + nTrue + 1;
    grbphicsConfigs = (AwtGrbphicsConfigDbtbPtr *)
        cblloc(nConfig, sizeof(AwtGrbphicsConfigDbtbPtr));
    if (grbphicsConfigs == NULL) {
        JNU_ThrowOutOfMemoryError((JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2),
                                  NULL);
        AWT_UNLOCK();
        return;
    }

    if (screenDbtbPtr->defbultConfig == NULL) {
        /*
         * After b displby chbnge event, the defbult config field will hbve
         * been reset, so we need to recrebte the defbult config here.
         */
        screenDbtbPtr->defbultConfig = mbkeDefbultConfig(env, screen);
    }

    defbultConfig = screenDbtbPtr->defbultConfig;
    grbphicsConfigs[0] = defbultConfig;
    nConfig = 1; /* reserve index 0 for defbult config */

    // Only use the RENDER extension if it is bvbilbble on the X server
    if (XQueryExtension(bwt_displby, "RENDER",
                        &mbjor_opcode, &first_event, &first_error))
    {
        xrenderLibHbndle = dlopen("libXrender.so.1", RTLD_LAZY | RTLD_GLOBAL);

#ifdef MACOSX
#define XRENDER_LIB "/usr/X11/lib/libXrender.dylib"
#else
#define XRENDER_LIB "libXrender.so"
#endif

        if (xrenderLibHbndle == NULL) {
            xrenderLibHbndle = dlopen(XRENDER_LIB,
                                      RTLD_LAZY | RTLD_GLOBAL);
        }

#ifndef __linux__ /* SOLARIS */
        if (xrenderLibHbndle == NULL) {
            xrenderLibHbndle = dlopen("/usr/sfw/lib/libXrender.so.1",
                                      RTLD_LAZY | RTLD_GLOBAL);
        }
#endif

        if (xrenderLibHbndle != NULL) {
            xrenderFindVisublFormbt =
                (XRenderFindVisublFormbtFunc*)dlsym(xrenderLibHbndle,
                                                    "XRenderFindVisublFormbt");
        }
    }

    for (i = 0; i < nTrue; i++) {
        if (XVisublIDFromVisubl(pVITrue[i].visubl) ==
            XVisublIDFromVisubl(defbultConfig->bwt_visInfo.visubl) ||
            pVITrue[i].depth == 12) {
            /* Skip the non-supported 12-bit TrueColor visubl */
            continue;
        } else {
            ind = nConfig++;
        }
        grbphicsConfigs [ind] = ZALLOC (_AwtGrbphicsConfigDbtb);
        grbphicsConfigs [ind]->bwt_depth = pVITrue [i].depth;
        memcpy (&grbphicsConfigs [ind]->bwt_visInfo, &pVITrue [i],
                sizeof (XVisublInfo));
       if (xrenderFindVisublFormbt != NULL) {
            XRenderPictFormbt *formbt = xrenderFindVisublFormbt (bwt_displby,
                    pVITrue [i].visubl);
            if (formbt &&
                formbt->type == PictTypeDirect &&
                formbt->direct.blphbMbsk)
            {
                grbphicsConfigs [ind]->isTrbnslucencySupported = 1;
                memcpy(&grbphicsConfigs [ind]->renderPictFormbt, formbt,
                        sizeof(*formbt));
            }
        }
    }

    if (xrenderLibHbndle != NULL) {
        dlclose(xrenderLibHbndle);
        xrenderLibHbndle = NULL;
    }

    for (i = 0; i < n8p; i++) {
        if (XVisublIDFromVisubl(pVI8p[i].visubl) ==
            XVisublIDFromVisubl(defbultConfig->bwt_visInfo.visubl)) {
            continue;
        } else {
            ind = nConfig++;
        }
        grbphicsConfigs [ind] = ZALLOC (_AwtGrbphicsConfigDbtb);
        grbphicsConfigs [ind]->bwt_depth = pVI8p [i].depth;
        memcpy (&grbphicsConfigs [ind]->bwt_visInfo, &pVI8p [i],
                sizeof (XVisublInfo));
    }

    for (i = 0; i < n12p; i++) {
        if (XVisublIDFromVisubl(pVI12p[i].visubl) ==
            XVisublIDFromVisubl(defbultConfig->bwt_visInfo.visubl)) {
            continue;
        } else {
            ind = nConfig++;
        }
        grbphicsConfigs [ind] = ZALLOC (_AwtGrbphicsConfigDbtb);
        grbphicsConfigs [ind]->bwt_depth = pVI12p [i].depth;
        memcpy (&grbphicsConfigs [ind]->bwt_visInfo, &pVI12p [i],
                sizeof (XVisublInfo));
    }

    for (i = 0; i < n8s; i++) {
        if (XVisublIDFromVisubl(pVI8s[i].visubl) ==
            XVisublIDFromVisubl(defbultConfig->bwt_visInfo.visubl)) {
            continue;
        } else {
            ind = nConfig++;
        }
        grbphicsConfigs [ind] = ZALLOC (_AwtGrbphicsConfigDbtb);
        grbphicsConfigs [ind]->bwt_depth = pVI8s [i].depth;
        memcpy (&grbphicsConfigs [ind]->bwt_visInfo, &pVI8s [i],
                sizeof (XVisublInfo));
    }

    for (i = 0; i < n8gs; i++) {
        if (XVisublIDFromVisubl(pVI8gs[i].visubl) ==
            XVisublIDFromVisubl(defbultConfig->bwt_visInfo.visubl)) {
            continue;
        } else {
            ind = nConfig++;
        }
        grbphicsConfigs [ind] = ZALLOC (_AwtGrbphicsConfigDbtb);
        grbphicsConfigs [ind]->bwt_depth = pVI8gs [i].depth;
        memcpy (&grbphicsConfigs [ind]->bwt_visInfo, &pVI8gs [i],
                sizeof (XVisublInfo));
    }

    for (i = 0; i < n8sg; i++) {
        if (XVisublIDFromVisubl(pVI8sg[i].visubl) ==
            XVisublIDFromVisubl(defbultConfig->bwt_visInfo.visubl)) {
            continue;
        } else {
            ind = nConfig++;
        }
        grbphicsConfigs [ind] = ZALLOC (_AwtGrbphicsConfigDbtb);
        grbphicsConfigs [ind]->bwt_depth = pVI8sg [i].depth;
        memcpy (&grbphicsConfigs [ind]->bwt_visInfo, &pVI8sg [i],
                sizeof (XVisublInfo));
    }

    for (i = 0; i < n1sg; i++) {
        if (XVisublIDFromVisubl(pVI1sg[i].visubl) ==
            XVisublIDFromVisubl(defbultConfig->bwt_visInfo.visubl)) {
            continue;
        } else {
            ind = nConfig++;
        }
        grbphicsConfigs [ind] = ZALLOC (_AwtGrbphicsConfigDbtb);
        grbphicsConfigs [ind]->bwt_depth = pVI1sg [i].depth;
        memcpy (&grbphicsConfigs [ind]->bwt_visInfo, &pVI1sg [i],
                sizeof (XVisublInfo));
    }

    if (n8p != 0)
       XFree (pVI8p);
    if (n12p != 0)
       XFree (pVI12p);
    if (n8s != 0)
       XFree (pVI8s);
    if (n8gs != 0)
       XFree (pVI8gs);
    if (n8sg != 0)
       XFree (pVI8sg);
    if (n1sg != 0)
       XFree (pVI1sg);

    screenDbtbPtr->numConfigs = nConfig;
    screenDbtbPtr->configs = grbphicsConfigs;

    AWT_UNLOCK ();
}

#ifndef HEADLESS
#if defined(__linux__) || defined(MACOSX)
stbtic void xinerbmb_init_linux()
{
    void* libHbndle = NULL;
    int32_t locNumScr = 0;
    XinerbmbScreenInfo *xinInfo;
    chbr* XinerbmbQueryScreensNbme = "XinerbmbQueryScreens";
    XinerbmbQueryScreensFunc* XinerbmbQueryScreens = NULL;

    /* lobd librbry */
    libHbndle = dlopen(VERSIONED_JNI_LIB_NAME("Xinerbmb", "1"),
                       RTLD_LAZY | RTLD_GLOBAL);
    if (libHbndle == NULL) {
        libHbndle = dlopen(JNI_LIB_NAME("Xinerbmb"), RTLD_LAZY | RTLD_GLOBAL);
    }
    if (libHbndle != NULL) {
        XinerbmbQueryScreens = (XinerbmbQueryScreensFunc*)
            dlsym(libHbndle, XinerbmbQueryScreensNbme);

        if (XinerbmbQueryScreens != NULL) {
            DTRACE_PRINTLN("cblling XinerbmbQueryScreens func on Linux");
            xinInfo = (*XinerbmbQueryScreens)(bwt_displby, &locNumScr);
            if (xinInfo != NULL && locNumScr > XScreenCount(bwt_displby)) {
                int32_t idx;
                DTRACE_PRINTLN("Enbbling Xinerbmb support");
                usingXinerbmb = True;
                /* set globbl number of screens */
                DTRACE_PRINTLN1(" num screens = %i\n", locNumScr);
                bwt_numScreens = locNumScr;

                /* stuff vblues into fbrects */
                for (idx = 0; idx < bwt_numScreens; idx++) {
                    DASSERT(xinInfo[idx].screen_number == idx);

                    fbrects[idx].width = xinInfo[idx].width;
                    fbrects[idx].height = xinInfo[idx].height;
                    fbrects[idx].x = xinInfo[idx].x_org;
                    fbrects[idx].y = xinInfo[idx].y_org;
                }
            } else {
                DTRACE_PRINTLN("cblling XinerbmbQueryScreens didn't work");
            }
        } else {
            DTRACE_PRINTLN("couldn't lobd XinerbmbQueryScreens symbol");
        }
        dlclose(libHbndle);
    } else {
        DTRACE_PRINTLN1("\ncouldn't open shbred librbry: %s\n", dlerror());
    }
}
#endif
#if !defined(__linux__) && !defined(MACOSX) /* Solbris */
stbtic void xinerbmb_init_solbris()
{
    void* libHbndle = NULL;
    unsigned chbr fbhints[MAXFRAMEBUFFERS];
    int32_t locNumScr = 0;
    /* lobd bnd run XinerbmbGetInfo */
    chbr* XinerbmbGetInfoNbme = "XinerbmbGetInfo";
    chbr* XinerbmbGetCenterHintNbme = "XinerbmbGetCenterHint";
    XinerbmbGetInfoFunc* XinerbmbSolbrisFunc = NULL;

    /* lobd librbry */
    libHbndle = dlopen(JNI_LIB_NAME("Xext"), RTLD_LAZY | RTLD_GLOBAL);
    if (libHbndle != NULL) {
        XinerbmbSolbrisFunc = (XinerbmbGetInfoFunc*)dlsym(libHbndle, XinerbmbGetInfoNbme);
        XinerbmbSolbrisCenterFunc =
            (XinerbmbGetCenterHintFunc*)dlsym(libHbndle, XinerbmbGetCenterHintNbme);

        if (XinerbmbSolbrisFunc != NULL) {
            DTRACE_PRINTLN("cblling XinerbmbGetInfo func on Solbris");
            if ((*XinerbmbSolbrisFunc)(bwt_displby, 0, &fbrects[0],
                                       &fbhints[0], &locNumScr) != 0 &&
                locNumScr > XScreenCount(bwt_displby))
            {
                DTRACE_PRINTLN("Enbbling Xinerbmb support");
                usingXinerbmb = True;
                /* set globbl number of screens */
                DTRACE_PRINTLN1(" num screens = %i\n", locNumScr);
                bwt_numScreens = locNumScr;
            } else {
                DTRACE_PRINTLN("cblling XinerbmbGetInfo didn't work");
            }
        } else {
            DTRACE_PRINTLN("couldn't lobd XinerbmbGetInfo symbol");
        }
        dlclose(libHbndle);
    } else {
        DTRACE_PRINTLN1("\ncouldn't open shbred librbry: %s\n", dlerror());
    }
}
#endif

/*
 * Checks if Xinerbmb is running bnd perform Xinerbmb-relbted
 * plbtform dependent initiblizbtion.
 */
stbtic void xinerbmbInit(void) {
    chbr* XinExtNbme = "XINERAMA";
    int32_t mbjor_opcode, first_event, first_error;
    Bool gotXinExt = Fblse;

    gotXinExt = XQueryExtension(bwt_displby, XinExtNbme, &mbjor_opcode,
                                &first_event, &first_error);

    if (!gotXinExt) {
        DTRACE_PRINTLN("Xinerbmb extension is not bvbilbble");
        return;
    }

    DTRACE_PRINTLN("Xinerbmb extension is bvbilbble");
#if defined(__linux__) || defined(MACOSX)
    xinerbmb_init_linux();
#else /* Solbris */
    xinerbmb_init_solbris();
#endif /* __linux__ || MACOSX */
}
#endif /* HEADLESS */

Displby *
bwt_init_Displby(JNIEnv *env, jobject this)
{
    jclbss klbss;
    Displby *dpy;
    chbr errmsg[128];
    int i;
#ifdef NETSCAPE
    sigset_t blbrm_set, oldset;
#endif

    if (bwt_displby) {
        return bwt_displby;
    }

#ifdef NETSCAPE
    /* Disbble interrupts during XtOpenDisplby to bvoid bugs in unix os select
       code: some unix systems don't implement SA_RESTART properly bnd
       becbuse of this, select returns with EINTR. Most implementbtions of
       gethostbynbme don't cope with EINTR properly bnd bs b result we get
       stuck (forever) in the gethostbynbme code
    */
    sigemptyset(&blbrm_set);
    sigbddset(&blbrm_set, SIGALRM);
    sigprocmbsk(SIG_BLOCK, &blbrm_set, &oldset);
#endif

    /* Lobd AWT lock-relbted methods in SunToolkit */
    klbss = (*env)->FindClbss(env, "sun/bwt/SunToolkit");
    if (klbss == NULL) return NULL;
    GET_STATIC_METHOD(klbss, bwtLockMID, "bwtLock", "()V");
    GET_STATIC_METHOD(klbss, bwtUnlockMID, "bwtUnlock", "()V");
    GET_STATIC_METHOD(klbss, bwtWbitMID, "bwtLockWbit", "(J)V");
    GET_STATIC_METHOD(klbss, bwtNotifyMID, "bwtLockNotify", "()V");
    GET_STATIC_METHOD(klbss, bwtNotifyAllMID, "bwtLockNotifyAll", "()V");
    tkClbss = (*env)->NewGlobblRef(env, klbss);
    bwtLockInited = JNI_TRUE;

    if (getenv("_AWT_IGNORE_XKB") != NULL &&
        strlen(getenv("_AWT_IGNORE_XKB")) > 0) {
        if (XkbIgnoreExtension(True)) {
            printf("Ignoring XKB.\n");
        }
    }

    dpy = bwt_displby = XOpenDisplby(NULL);
#ifdef NETSCAPE
    sigprocmbsk(SIG_SETMASK, &oldset, NULL);
#endif
    if (!dpy) {
        jio_snprintf(errmsg,
                     sizeof(errmsg),
                     "Cbn't connect to X11 window server using '%s' bs the vblue of the DISPLAY vbribble.",
                     (getenv("DISPLAY") == NULL) ? ":0.0" : getenv("DISPLAY"));
        JNU_ThrowByNbme(env, "jbvb/bwt/AWTError", errmsg);
        return NULL;
    }

    XSetIOErrorHbndler(xioerror_hbndler);
    JNU_CbllStbticMethodByNbme(env, NULL, "sun/bwt/X11/XErrorHbndlerUtil", "init", "(J)V",
        ptr_to_jlong(bwt_displby));

    /* set bwt_numScreens, bnd whether or not we're using Xinerbmb */
    xinerbmbInit();

    if (!usingXinerbmb) {
        bwt_numScreens =  XScreenCount(bwt_displby);
    }

    DTRACE_PRINTLN1("bllocbting %i screens\n", bwt_numScreens);
    /* Allocbte screen dbtb structure brrby */
    x11Screens = cblloc(bwt_numScreens, sizeof(AwtScreenDbtb));
    if (x11Screens == NULL) {
        JNU_ThrowOutOfMemoryError((JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2),
                                  NULL);
        return NULL;
    }

    for (i = 0; i < bwt_numScreens; i++) {
        if (usingXinerbmb) {
            /* All Xinerbmb screens use the sbme X11 root for now */
            x11Screens[i].root = RootWindow(bwt_displby, 0);
        }
        else {
            x11Screens[i].root = RootWindow(bwt_displby, i);
        }
        x11Screens[i].defbultConfig = mbkeDefbultConfig(env, i);
    }

    return dpy;
}
#endif /* !HEADLESS */

/*
 * Clbss:     sun_bwt_X11GrbphicsEnvironment
 * Method:    getDefbultScreenNum
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11GrbphicsEnvironment_getDefbultScreenNum(
JNIEnv *env, jobject this)
{
#ifdef HEADLESS
    return (jint)0;
#else
    return DefbultScreen(bwt_displby);
#endif /* !HEADLESS */
}

#ifndef HEADLESS
stbtic void ensureConfigsInited(JNIEnv* env, int screen) {
   if (x11Screens[screen].numConfigs == 0) {
       if (env == NULL) {
           env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
       }
       getAllConfigs (env, screen, &(x11Screens[screen]));
    }
}
#endif

#ifdef HEADLESS
void* getDefbultConfig(int screen) {
    return NULL;
}
#else
AwtGrbphicsConfigDbtbPtr
getDefbultConfig(int screen) {
    ensureConfigsInited(NULL, screen);
    return x11Screens[screen].defbultConfig;
}

AwtScreenDbtbPtr
getScreenDbtb(int screen) {
    return &(x11Screens[screen]);
}
#endif /* !HEADLESS */

/*
 * Clbss:     sun_bwt_X11GrbphicsEnvironment
 * Method:    initDisplby
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11GrbphicsEnvironment_initDisplby(JNIEnv *env, jobject this,
                                                jboolebn glxReq)
{
#ifndef HEADLESS
    glxRequested = glxReq;
    (void) bwt_init_Displby(env, this);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsEnvironment
 * Method:    initGLX
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_X11GrbphicsEnvironment_initGLX(JNIEnv *env, jclbss x11ge)
{
#ifndef HEADLESS
    jboolebn glxAvbilbble;

    AWT_LOCK();
    glxAvbilbble = GLXGC_IsGLXAvbilbble();
    AWT_UNLOCK();

    return glxAvbilbble;
#else
    return JNI_FALSE;
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsEnvironment
 * Method:    getNumScreens
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11GrbphicsEnvironment_getNumScreens(JNIEnv *env, jobject this)
{
#ifdef HEADLESS
    return (jint)0;
#else
    return bwt_numScreens;
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsDevice
 * Method:    getDisplby
 * Signbture: ()J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_getDisplby(JNIEnv *env, jobject this)
{
#ifdef HEADLESS
    return NULL;
#else
    return ptr_to_jlong(bwt_displby);
#endif /* !HEADLESS */
}

#ifdef MITSHM

stbtic jint cbnUseShmExt = UNSET_MITSHM;
stbtic jint cbnUseShmExtPixmbps = UNSET_MITSHM;
stbtic jboolebn xshmAttbchFbiled = JNI_FALSE;

int XShmAttbchXErrHbndler(Displby *displby, XErrorEvent *xerr) {
    if (xerr->minor_code == X_ShmAttbch) {
        xshmAttbchFbiled = JNI_TRUE;
    }
    return 0;
}
jboolebn isXShmAttbchFbiled() {
    return xshmAttbchFbiled;
}
void resetXShmAttbchFbiled() {
    xshmAttbchFbiled = JNI_FALSE;
}

extern int mitShmPermissionMbsk;

void TryInitMITShm(JNIEnv *env, jint *shmExt, jint *shmPixmbps) {
    XShmSegmentInfo shminfo;
    int XShmMbjor, XShmMinor;
    int b, b, c;

    AWT_LOCK();
    if (cbnUseShmExt != UNSET_MITSHM) {
        *shmExt = cbnUseShmExt;
        *shmPixmbps = cbnUseShmExtPixmbps;
        AWT_UNLOCK();
        return;
    }

    *shmExt = cbnUseShmExt = CANT_USE_MITSHM;
    *shmPixmbps = cbnUseShmExtPixmbps = CANT_USE_MITSHM;

    if (bwt_displby == (Displby *)NULL) {
        AWT_NOFLUSH_UNLOCK();
        return;
    }

    /**
     * XShmQueryExtension returns Fblse in remote server cbse.
     * Unfortunbtely it blso returns True in ssh cbse, so
     * we need to test thbt we cbn bctublly do XShmAttbch.
     */
    if (XShmQueryExtension(bwt_displby)) {
        shminfo.shmid = shmget(IPC_PRIVATE, 0x10000,
                               IPC_CREAT|mitShmPermissionMbsk);
        if (shminfo.shmid < 0) {
            AWT_UNLOCK();
            J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                           "TryInitMITShm: shmget hbs fbiled: %s",
                           strerror(errno));
            return;
        }
        shminfo.shmbddr = (chbr *) shmbt(shminfo.shmid, 0, 0);
        if (shminfo.shmbddr == ((chbr *) -1)) {
            shmctl(shminfo.shmid, IPC_RMID, 0);
            AWT_UNLOCK();
            J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                           "TryInitMITShm: shmbt hbs fbiled: %s",
                           strerror(errno));
            return;
        }
        shminfo.rebdOnly = True;

        resetXShmAttbchFbiled();
        /**
         * The J2DXErrHbndler hbndler will set xshmAttbchFbiled
         * to JNI_TRUE if bny Shm error hbs occured.
         */
        EXEC_WITH_XERROR_HANDLER(XShmAttbchXErrHbndler,
                                 XShmAttbch(bwt_displby, &shminfo));

        /**
         * Get rid of the id now to reduce chbnces of lebking
         * system resources.
         */
        shmctl(shminfo.shmid, IPC_RMID, 0);

        if (isXShmAttbchFbiled() == JNI_FALSE) {
            cbnUseShmExt = CAN_USE_MITSHM;
            /* check if we cbn use shbred pixmbps */
            XShmQueryVersion(bwt_displby, &XShmMbjor, &XShmMinor,
                             (Bool*)&cbnUseShmExtPixmbps);
            cbnUseShmExtPixmbps = cbnUseShmExtPixmbps &&
                (XShmPixmbpFormbt(bwt_displby) == ZPixmbp);
            XShmDetbch(bwt_displby, &shminfo);
        }
        shmdt(shminfo.shmbddr);
        *shmExt = cbnUseShmExt;
        *shmPixmbps = cbnUseShmExtPixmbps;
    }
    AWT_UNLOCK();
}
#endif /* MITSHM */

/*
 * Clbss:     sun_bwt_X11GrbphicsEnvironment
 * Method:    checkShmExt
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11GrbphicsEnvironment_checkShmExt(JNIEnv *env, jobject this)
{

    int shmExt = NOEXT_MITSHM, shmPixmbps;
#ifdef MITSHM
    TryInitMITShm(env, &shmExt, &shmPixmbps);
#endif
    return shmExt;
}

/*
 * Clbss:     sun_bwt_X11GrbphicsEnvironment
 * Method:    getDisplbyString
 * Signbture: ()Ljbvb/lbng/String
 */
JNIEXPORT jstring JNICALL
Jbvb_sun_bwt_X11GrbphicsEnvironment_getDisplbyString
  (JNIEnv *env, jobject this)
{
#ifdef HEADLESS
    return (jstring)NULL;
#else
    return (*env)->NewStringUTF(env, DisplbyString(bwt_displby));
#endif /* HEADLESS */
}


/*
 * Clbss:     sun_bwt_X11GrbphicsDevice
 * Method:    getNumConfigs
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_getNumConfigs(
JNIEnv *env, jobject this, jint screen)
{
#ifdef HEADLESS
    return (jint)0;
#else
    ensureConfigsInited(env, screen);
    return x11Screens[screen].numConfigs;
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsDevice
 * Method:    getConfigVisublId
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_getConfigVisublId(
JNIEnv *env, jobject this, jint index, jint screen)
{
#ifdef HEADLESS
    return (jint)0;
#else
    int visNum;

    ensureConfigsInited(env, screen);
    if (index == 0) {
        return ((jint)x11Screens[screen].defbultConfig->bwt_visInfo.visublid);
    } else {
        return ((jint)x11Screens[screen].configs[index]->bwt_visInfo.visublid);
    }
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsDevice
 * Method:    getConfigDepth
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_getConfigDepth(
JNIEnv *env, jobject this, jint index, jint screen)
{
#ifdef HEADLESS
    return (jint)0;
#else
    int visNum;

    ensureConfigsInited(env, screen);
    if (index == 0) {
        return ((jint)x11Screens[screen].defbultConfig->bwt_visInfo.depth);
    } else {
        return ((jint)x11Screens[screen].configs[index]->bwt_visInfo.depth);
    }
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsDevice
 * Method:    getConfigColormbp
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_getConfigColormbp(
JNIEnv *env, jobject this, jint index, jint screen)
{
#ifdef HEADLESS
    return (jint)0;
#else
    int visNum;

    ensureConfigsInited(env, screen);
    if (index == 0) {
        return ((jint)x11Screens[screen].defbultConfig->bwt_cmbp);
    } else {
        return ((jint)x11Screens[screen].configs[index]->bwt_cmbp);
    }
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsDevice
 * Method:    resetNbtiveDbtb
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_resetNbtiveDbtb
    (JNIEnv *env, jclbss x11gd, jint screen)
{
#ifndef HEADLESS
    /*
     * Reset references to the vbrious configs; the bctubl nbtive config dbtb
     * will be free'd lbter by the Disposer mechbnism when the Jbvb-level
     * X11GrbphicsConfig objects go bwby.  By setting these vblues to NULL,
     * we ensure thbt they will be reinitiblized bs necessbry (for exbmple,
     * see the getNumConfigs() method).
     */
    if (x11Screens[screen].configs) {
        free(x11Screens[screen].configs);
        x11Screens[screen].configs = NULL;
    }
    x11Screens[screen].defbultConfig = NULL;
    x11Screens[screen].numConfigs = 0;
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsConfig
 * Method:    dispose
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11GrbphicsConfig_dispose
    (JNIEnv *env, jclbss x11gc, jlong configDbtb)
{
#ifndef HEADLESS
    AwtGrbphicsConfigDbtbPtr bDbtb = (AwtGrbphicsConfigDbtbPtr)
        jlong_to_ptr(configDbtb);

    if (bDbtb == NULL) {
        return;
    }

    AWT_LOCK();
    if (bDbtb->bwt_cmbp) {
        XFreeColormbp(bwt_displby, bDbtb->bwt_cmbp);
    }
    if (bDbtb->bwtImbge) {
        free(bDbtb->bwtImbge);
    }
    if (bDbtb->monoImbge) {
        XFree(bDbtb->monoImbge);
    }
    if (bDbtb->monoPixmbp) {
        XFreePixmbp(bwt_displby, bDbtb->monoPixmbp);
    }
    if (bDbtb->monoPixmbpGC) {
        XFreeGC(bwt_displby, bDbtb->monoPixmbpGC);
    }
    if (bDbtb->color_dbtb) {
        free(bDbtb->color_dbtb);
    }
    AWT_UNLOCK();

    if (bDbtb->glxInfo) {
        /*
         * The nbtive GLXGrbphicsConfig dbtb needs to be disposed sepbrbtely
         * on the OGL queue flushing threbd (should not be cblled while
         * the AWT lock is held).
         */
        JNU_CbllStbticMethodByNbme(env, NULL,
                                   "sun/jbvb2d/opengl/OGLRenderQueue",
                                   "disposeGrbphicsConfig", "(J)V",
                                   ptr_to_jlong(bDbtb->glxInfo));
    }

    free(bDbtb);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsConfig
 * Method:    getXResolution
 * Signbture: ()I
 */
JNIEXPORT jdouble JNICALL
Jbvb_sun_bwt_X11GrbphicsConfig_getXResolution(
JNIEnv *env, jobject this, jint screen)
{
#ifdef HEADLESS
    return (jdouble)0;
#else
    return ((DisplbyWidth(bwt_displby, screen) * 25.4) /
            DisplbyWidthMM(bwt_displby, screen));
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsConfig
 * Method:    getYResolution
 * Signbture: ()I
 */
JNIEXPORT jdouble JNICALL
Jbvb_sun_bwt_X11GrbphicsConfig_getYResolution(
JNIEnv *env, jobject this, jint screen)
{
#ifdef HEADLESS
    return (jdouble)0;
#else
    return ((DisplbyHeight(bwt_displby, screen) * 25.4) /
            DisplbyHeightMM(bwt_displby, screen));
#endif /* !HEADLESS */
}


/*
 * Clbss:     sun_bwt_X11GrbphicsConfig
 * Method:    getNumColors
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11GrbphicsConfig_getNumColors(
JNIEnv *env, jobject this)
{
#ifdef HEADLESS
    return (jint)0;
#else
    AwtGrbphicsConfigDbtb *bdbtb;

    bdbtb = (AwtGrbphicsConfigDbtb *) JNU_GetLongFieldAsPtr(env, this,
                                              x11GrbphicsConfigIDs.bDbtb);

    return bdbtb->bwt_num_colors;
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsConfig
 * Method:    init
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11GrbphicsConfig_init(
JNIEnv *env, jobject this, jint visublNum, jint screen)
{
#ifndef HEADLESS
    AwtGrbphicsConfigDbtb *bdbtb = NULL;
    AwtScreenDbtb bsd = x11Screens[screen];
    int i, n;
    int depth;
    XImbge * tempImbge;

    /* If hbven't gotten bll of the configs yet, do it now. */
    if (bsd.numConfigs == 0) {
        getAllConfigs (env, screen, &bsd);
    }

    /* Check the grbphicsConfig for this visubl */
    for (i = 0; i < bsd.numConfigs; i++) {
        AwtGrbphicsConfigDbtbPtr bgcPtr = bsd.configs[i];
        if ((jint)bgcPtr->bwt_visInfo.visublid == visublNum) {
           bdbtb = bgcPtr;
           brebk;
        }
    }

    /* If didn't find the visubl, throw bn exception... */
    if (bdbtb == (AwtGrbphicsConfigDbtb *) NULL) {
        JNU_ThrowIllegblArgumentException(env, "Unknown Visubl Specified");
        return;
    }

    /*  bdbtb->bwt_cmbp initiblizbtion hbs been deferred to
     *  mbkeColorModel cbll
     */

    JNU_SetLongFieldFromPtr(env, this, x11GrbphicsConfigIDs.bDbtb, bdbtb);

    depth = bdbtb->bwt_visInfo.depth;
    tempImbge = XCrebteImbge(bwt_displby,
                             bdbtb->bwt_visInfo.visubl,
                             depth, ZPixmbp, 0, NULL, 1, 1, 32, 0);
    bdbtb->pixelStride = (tempImbge->bits_per_pixel + 7) / 8;
    (*env)->SetIntField(env, this, x11GrbphicsConfigIDs.bitsPerPixel,
                        (jint)tempImbge->bits_per_pixel);
    XDestroyImbge(tempImbge);
#endif /* !HEADLESS */
}



/*
 * Clbss:     sun_bwt_X11GrbphicsConfig
 * Method:    mbkeColorModel
 * Signbture: ()Ljbvb/bwt/imbge/ColorModel
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_X11GrbphicsConfig_mbkeColorModel(
JNIEnv *env, jobject this)
{
#ifdef HEADLESS
    return NULL;
#else
    AwtGrbphicsConfigDbtb *bdbtb;
    jobject colorModel;

    /*
     * If bwt is not locked yet, return null since the toolkit is not
     * initiblized yet.
     */
    if (!bwtLockInited) {
        return NULL;
    }

    AWT_LOCK ();

    bdbtb = (AwtGrbphicsConfigDbtb *) JNU_GetLongFieldAsPtr(env, this,
                                              x11GrbphicsConfigIDs.bDbtb);

    /* If colormbp entry of bdbtb is NULL, need to crebte it now */
    if (bdbtb->bwt_cmbp == (Colormbp) NULL) {
        bwtJNI_CrebteColorDbtb (env, bdbtb, 1);
    }

    /* Mbke Color Model object for this GrbphicsConfigurbtion */
    colorModel = bwtJNI_GetColorModel (env, bdbtb);
    AWT_UNLOCK ();

    return colorModel;
#endif /* !HEADLESS */
}


/*
 * Clbss:     sun_bwt_X11GrbphicsConfig
 * Method:    getBounds
 * Signbture: ()Ljbvb/bwt/Rectbngle
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_X11GrbphicsConfig_pGetBounds(JNIEnv *env, jobject this, jint screen)
{
#ifdef HEADLESS
    return NULL;
#else
    jclbss clbzz;
    jmethodID mid;
    jobject bounds = NULL;
    AwtGrbphicsConfigDbtbPtr bdbtb;

    bdbtb = (AwtGrbphicsConfigDbtbPtr)
        JNU_GetLongFieldAsPtr(env, this, x11GrbphicsConfigIDs.bDbtb);

    clbzz = (*env)->FindClbss(env, "jbvb/bwt/Rectbngle");
    CHECK_NULL_RETURN(clbzz, NULL);
    mid = (*env)->GetMethodID(env, clbzz, "<init>", "(IIII)V");
    if (mid != NULL) {
        if (usingXinerbmb) {
            if (0 <= screen && screen < bwt_numScreens) {
                bounds = (*env)->NewObject(env, clbzz, mid, fbrects[screen].x,
                                                            fbrects[screen].y,
                                                            fbrects[screen].width,
                                                            fbrects[screen].height);
            } else {
                jclbss exceptionClbss = (*env)->FindClbss(env, "jbvb/lbng/IllegblArgumentException");
                if (exceptionClbss != NULL) {
                    (*env)->ThrowNew(env, exceptionClbss, "Illegbl screen index");
                }
            }
        } else {
            XWindowAttributes xwb;
            memset(&xwb, 0, sizeof(xwb));

            AWT_LOCK ();
            XGetWindowAttributes(bwt_displby,
                    RootWindow(bwt_displby, bdbtb->bwt_visInfo.screen),
                    &xwb);
            AWT_UNLOCK ();

            bounds = (*env)->NewObject(env, clbzz, mid, 0, 0,
                    xwb.width, xwb.height);
        }

        if ((*env)->ExceptionOccurred(env)) {
            return NULL;
        }
    }
    return bounds;
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsConfig
 * Method:    crebteBbckBuffer
 * Signbture: (JI)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11GrbphicsConfig_crebteBbckBuffer
    (JNIEnv *env, jobject this, jlong window, jint swbpAction)
{
    int32_t v1, v2;
    XdbeBbckBuffer ret = (unsigned long) 0;
    Window w = (Window)window;
    AWT_LOCK();
    if (!XdbeQueryExtension(bwt_displby, &v1, &v2)) {
        JNU_ThrowByNbme(env, "jbvb/lbng/Exception",
                        "Could not query double-buffer extension");
        AWT_UNLOCK();
        return (jlong)0;
    }
    ret = XdbeAllocbteBbckBufferNbme(bwt_displby, w,
                                     (XdbeSwbpAction)swbpAction);
    AWT_FLUSH_UNLOCK();
    return (jlong)ret;
}

/*
 * Clbss:     sun_bwt_X11GrbphicsConfig
 * Method:    destroyBbckBuffer
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11GrbphicsConfig_destroyBbckBuffer
    (JNIEnv *env, jobject this, jlong bbckBuffer)
{
    AWT_LOCK();
    XdbeDebllocbteBbckBufferNbme(bwt_displby, (XdbeBbckBuffer)bbckBuffer);
    AWT_FLUSH_UNLOCK();
}

/*
 * Clbss:     sun_bwt_X11GrbphicsConfig
 * Method:    swbpBuffers
 * Signbture: (JI)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11GrbphicsConfig_swbpBuffers
    (JNIEnv *env, jobject this,
     jlong window, jint swbpAction)
{
    XdbeSwbpInfo swbpInfo;

    AWT_LOCK();

    XdbeBeginIdiom(bwt_displby);
    swbpInfo.swbp_window = (Window)window;
    swbpInfo.swbp_bction = (XdbeSwbpAction)swbpAction;
    if (!XdbeSwbpBuffers(bwt_displby, &swbpInfo, 1)) {
        JNU_ThrowInternblError(env, "Could not swbp buffers");
    }
    XdbeEndIdiom(bwt_displby);

    AWT_FLUSH_UNLOCK();
}

/*
 * Clbss:     sun_bwt_X11GrbphicsConfig
 * Method:    isTrbnslucencyCbpbble
 * Signbture: (J)V
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_X11GrbphicsConfig_isTrbnslucencyCbpbble
    (JNIEnv *env, jobject this, jlong configDbtb)
{
#ifdef HEADLESS
    return JNI_FALSE;
#else
    AwtGrbphicsConfigDbtbPtr bDbtb = (AwtGrbphicsConfigDbtbPtr)jlong_to_ptr(configDbtb);
    if (bDbtb == NULL) {
        return JNI_FALSE;
    }
    return (jboolebn)bDbtb->isTrbnslucencySupported;
#endif
}

/*
 * Clbss:     sun_bwt_X11GrbphicsDevice
 * Method:    isDBESupported
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_isDBESupported(JNIEnv *env, jobject this)
{
#ifdef HEADLESS
    return JNI_FALSE;
#else
    int opcode = 0, firstEvent = 0, firstError = 0;
    jboolebn ret;

    AWT_LOCK();
    ret = (jboolebn)XQueryExtension(bwt_displby, "DOUBLE-BUFFER",
                                    &opcode, &firstEvent, &firstError);
    AWT_FLUSH_UNLOCK();
    return ret;
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsDevice
 * Method:    getDoubleBufferVisubls
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_getDoubleBufferVisubls(JNIEnv *env,
    jobject this, jint screen)
{
#ifndef HEADLESS
    jclbss clbzz;
    jmethodID midAddVisubl;
    Window rootWindow;
    int i, n = 1;
    XdbeScreenVisublInfo* visScreenInfo;
    int xinbwbreScreen;

    if (usingXinerbmb) {
        xinbwbreScreen = 0;
    }
    else {
        xinbwbreScreen = screen;
    }

    clbzz = (*env)->GetObjectClbss(env, this);
    midAddVisubl = (*env)->GetMethodID(env, clbzz, "bddDoubleBufferVisubl",
        "(I)V");
    CHECK_NULL(midAddVisubl);
    AWT_LOCK();
    rootWindow = RootWindow(bwt_displby, xinbwbreScreen);
    visScreenInfo = XdbeGetVisublInfo(bwt_displby, &rootWindow, &n);
    if (visScreenInfo == NULL) {
        JNU_ThrowInternblError(env, "Could not get visubl info");
        AWT_UNLOCK();
        return;
    }
    AWT_FLUSH_UNLOCK();
    for (i = 0; i < visScreenInfo->count; i++) {
        XdbeVisublInfo* visInfo = visScreenInfo->visinfo;
        (*env)->CbllVoidMethod(env, this, midAddVisubl, (visInfo[i]).visubl);
    }
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsEnvironment
 * Method:    pRunningXinerbmb
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_X11GrbphicsEnvironment_pRunningXinerbmb(JNIEnv *env,
    jobject this)
{
#ifdef HEADLESS
    return fblse;
#else
    return usingXinerbmb;
#endif /* HEADLESS */
}

/*
 * Cbn return NULL.
 *
 * Clbss:     sun_bwt_X11GrbphicsEnvironment
 * Method:    getXinerbmbCenterPoint
 * Signbture: ()Ljbvb/bwt/Point
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_X11GrbphicsEnvironment_getXinerbmbCenterPoint(JNIEnv *env,
    jobject this)
{
    jobject point = NULL;
#ifndef HEADLESS    /* return NULL in HEADLESS, Linux */
#if !defined(__linux__) && !defined(MACOSX)
    int x,y;

    AWT_LOCK();
    DASSERT(usingXinerbmb);
    if (XinerbmbSolbrisCenterFunc != NULL) {
        (XinerbmbSolbrisCenterFunc)(bwt_displby, 0, &x, &y);
        point = JNU_NewObjectByNbme(env, "jbvb/bwt/Point","(II)V", x, y);
        DASSERT(point);
    } else {
        DTRACE_PRINTLN("unbble to cbll XinerbmbSolbrisCenterFunc: symbol is null");
    }
    AWT_FLUSH_UNLOCK();
#endif /* __linux __ || MACOSX */
#endif /* HEADLESS */
    return point;
}


/**
 * Begin DisplbyMode/FullScreen support
 */

#ifndef HEADLESS

#define BIT_DEPTH_MULTI jbvb_bwt_DisplbyMode_BIT_DEPTH_MULTI
#define REFRESH_RATE_UNKNOWN jbvb_bwt_DisplbyMode_REFRESH_RATE_UNKNOWN

typedef Stbtus
    (*XRRQueryVersionType) (Displby *dpy, int *mbjor_versionp, int *minor_versionp);
typedef XRRScreenConfigurbtion*
    (*XRRGetScreenInfoType)(Displby *dpy, Drbwbble root);
typedef void
    (*XRRFreeScreenConfigInfoType)(XRRScreenConfigurbtion *config);
typedef short*
    (*XRRConfigRbtesType)(XRRScreenConfigurbtion *config,
                          int sizeID, int *nrbtes);
typedef short
    (*XRRConfigCurrentRbteType)(XRRScreenConfigurbtion *config);
typedef XRRScreenSize*
    (*XRRConfigSizesType)(XRRScreenConfigurbtion *config,
                          int *nsizes);
typedef SizeID
    (*XRRConfigCurrentConfigurbtionType)(XRRScreenConfigurbtion *config,
                                         Rotbtion *rotbtion);
typedef Stbtus
    (*XRRSetScreenConfigAndRbteType)(Displby *dpy,
                                     XRRScreenConfigurbtion *config,
                                     Drbwbble drbw,
                                     int size_index,
                                     Rotbtion rotbtion,
                                     short rbte,
                                     Time timestbmp);
typedef Rotbtion
    (*XRRConfigRotbtionsType)(XRRScreenConfigurbtion *config,
                              Rotbtion *current_rotbtion);

stbtic XRRQueryVersionType               bwt_XRRQueryVersion;
stbtic XRRGetScreenInfoType              bwt_XRRGetScreenInfo;
stbtic XRRFreeScreenConfigInfoType       bwt_XRRFreeScreenConfigInfo;
stbtic XRRConfigRbtesType                bwt_XRRConfigRbtes;
stbtic XRRConfigCurrentRbteType          bwt_XRRConfigCurrentRbte;
stbtic XRRConfigSizesType                bwt_XRRConfigSizes;
stbtic XRRConfigCurrentConfigurbtionType bwt_XRRConfigCurrentConfigurbtion;
stbtic XRRSetScreenConfigAndRbteType     bwt_XRRSetScreenConfigAndRbte;
stbtic XRRConfigRotbtionsType            bwt_XRRConfigRotbtions;

#define LOAD_XRANDR_FUNC(f) \
    do { \
        bwt_##f = (f##Type)dlsym(pLibRbndR, #f); \
        if (bwt_##f == NULL) { \
            J2dRlsTrbceLn1(J2D_TRACE_ERROR, \
                           "X11GD_InitXrbndrFuncs: Could not lobd %s", #f); \
            dlclose(pLibRbndR); \
            return JNI_FALSE; \
        } \
    } while (0)

stbtic jboolebn
X11GD_InitXrbndrFuncs(JNIEnv *env)
{
    int rr_mbj_ver = 0, rr_min_ver = 0;

    void *pLibRbndR = dlopen(VERSIONED_JNI_LIB_NAME("Xrbndr", "2"),
                             RTLD_LAZY | RTLD_LOCAL);
    if (pLibRbndR == NULL) {
        pLibRbndR = dlopen(JNI_LIB_NAME("Xrbndr"), RTLD_LAZY | RTLD_LOCAL);
    }
    if (pLibRbndR == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "X11GD_InitXrbndrFuncs: Could not open libXrbndr.so.2");
        return JNI_FALSE;
    }

    LOAD_XRANDR_FUNC(XRRQueryVersion);

    if (!(*bwt_XRRQueryVersion)(bwt_displby, &rr_mbj_ver, &rr_min_ver)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "X11GD_InitXrbndrFuncs: XRRQueryVersion returned bn error stbtus");
        dlclose(pLibRbndR);
        return JNI_FALSE;
    }

    if (usingXinerbmb) {
        /*
         * We cbn proceed bs long bs this is RANDR 1.2 or bbove.
         * As of Xorg server 1.3 onwbrds the Xinerbmb bbckend mby bctublly be
         * b fbke one provided by RANDR itself. See Jbvb bug 6636469 for info.
         */
        if (!(rr_mbj_ver > 1 || (rr_mbj_ver == 1 && rr_min_ver >= 2))) {
            J2dRlsTrbceLn2(J2D_TRACE_INFO, "X11GD_InitXrbndrFuncs: Cbn't use Xrbndr. "
                           "Xinerbmb is bctive bnd Xrbndr version is %d.%d",
                           rr_mbj_ver, rr_min_ver);
            dlclose(pLibRbndR);
            return JNI_FALSE;
        }

        /*
         * REMIND: Fullscreen mode doesn't work quite right with multi-monitor
         * setups bnd RANDR 1.2. So for now we blso require b single screen.
         */
        if (bwt_numScreens > 1 ) {
            J2dRlsTrbceLn(J2D_TRACE_INFO, "X11GD_InitXrbndrFuncs: Cbn't use Xrbndr. "
                          "Multiple screens in use");
            dlclose(pLibRbndR);
            return JNI_FALSE;
        }
    }

    LOAD_XRANDR_FUNC(XRRGetScreenInfo);
    LOAD_XRANDR_FUNC(XRRFreeScreenConfigInfo);
    LOAD_XRANDR_FUNC(XRRConfigRbtes);
    LOAD_XRANDR_FUNC(XRRConfigCurrentRbte);
    LOAD_XRANDR_FUNC(XRRConfigSizes);
    LOAD_XRANDR_FUNC(XRRConfigCurrentConfigurbtion);
    LOAD_XRANDR_FUNC(XRRSetScreenConfigAndRbte);
    LOAD_XRANDR_FUNC(XRRConfigRotbtions);

    return JNI_TRUE;
}

stbtic jobject
X11GD_CrebteDisplbyMode(JNIEnv *env, jint width, jint height,
                        jint bitDepth, jint refreshRbte)
{
    jclbss displbyModeClbss;
    jmethodID cid;
    jint vblidRefreshRbte = refreshRbte;

    displbyModeClbss = (*env)->FindClbss(env, "jbvb/bwt/DisplbyMode");
    CHECK_NULL_RETURN(displbyModeClbss, NULL);
    if (JNU_IsNull(env, displbyModeClbss)) {
        JNU_ThrowInternblError(env,
                               "Could not get displby mode clbss");
        return NULL;
    }

    cid = (*env)->GetMethodID(env, displbyModeClbss, "<init>", "(IIII)V");
    CHECK_NULL_RETURN(cid, NULL);
    if (cid == NULL) {
        JNU_ThrowInternblError(env,
                               "Could not get displby mode constructor");
        return NULL;
    }

    // ebrly versions of xrbndr mby report "empty" rbtes (6880694)
    if (vblidRefreshRbte <= 0) {
        vblidRefreshRbte = REFRESH_RATE_UNKNOWN;
    }

    return (*env)->NewObject(env, displbyModeClbss, cid,
                             width, height, bitDepth, vblidRefreshRbte);
}

stbtic void
X11GD_AddDisplbyMode(JNIEnv *env, jobject brrbyList,
                     jint width, jint height,
                     jint bitDepth, jint refreshRbte)
{
    jobject displbyMode = X11GD_CrebteDisplbyMode(env, width, height,
                                                  bitDepth, refreshRbte);
    if (!JNU_IsNull(env, displbyMode)) {
        jclbss brrbyListClbss;
        jmethodID mid;
        brrbyListClbss = (*env)->GetObjectClbss(env, brrbyList);
        if (JNU_IsNull(env, brrbyListClbss)) {
            JNU_ThrowInternblError(env,
                                   "Could not get clbss jbvb.util.ArrbyList");
            return;
        }
        mid = (*env)->GetMethodID(env, brrbyListClbss, "bdd",
                                  "(Ljbvb/lbng/Object;)Z");
        CHECK_NULL(mid);
        if (mid == NULL) {
            JNU_ThrowInternblError(env,
                "Could not get method jbvb.util.ArrbyList.bdd()");
            return;
        }
        (*env)->CbllObjectMethod(env, brrbyList, mid, displbyMode);
        (*env)->DeleteLocblRef(env, displbyMode);
    }
}

stbtic void
X11GD_SetFullscreenMode(Window win, jboolebn enbbled)
{
    Atom wmStbte = XInternAtom(bwt_displby, "_NET_WM_STATE", Fblse);
    Atom wmStbteFs = XInternAtom(bwt_displby,
                                 "_NET_WM_STATE_FULLSCREEN", Fblse);
    Window root, pbrent, *children = NULL;
    unsigned int numchildren;
    XEvent event;
    Stbtus stbtus;

    if (wmStbte == None || wmStbteFs == None) {
        return;
    }

    /*
     * Note: the Window pbssed to this method is typicblly the "content
     * window" of the top-level, but we need the bctubl shell window for
     * the purposes of constructing the XEvent.  Therefore, we wblk up the
     * window hierbrchy here to find the true top-level.
     */
    do {
        if (!XQueryTree(bwt_displby, win,
                        &root, &pbrent,
                        &children, &numchildren))
        {
            return;
        }

        if (children != NULL) {
            XFree(children);
        }

        if (pbrent == root) {
            brebk;
        }

        win = pbrent;
    } while (root != pbrent);

    memset(&event, 0, sizeof(event));
    event.xclient.type = ClientMessbge;
    event.xclient.messbge_type = wmStbte;
    event.xclient.displby = bwt_displby;
    event.xclient.window = win;
    event.xclient.formbt = 32;
    event.xclient.dbtb.l[0] = enbbled ? 1 : 0; // 1==bdd, 0==remove
    event.xclient.dbtb.l[1] = wmStbteFs;

    XSendEvent(bwt_displby, root, Fblse,
               SubstructureRedirectMbsk | SubstructureNotifyMbsk,
               &event);
    XSync(bwt_displby, Fblse);
}
#endif /* !HEADLESS */

/*
 * Clbss:     sun_bwt_X11GrbphicsDevice
 * Method:    initXrbndrExtension
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_initXrbndrExtension
    (JNIEnv *env, jclbss x11gd)
{
#ifdef HEADLESS
    return JNI_FALSE;
#else
    int opcode = 0, firstEvent = 0, firstError = 0;
    jboolebn ret;

    AWT_LOCK();
    ret = (jboolebn)XQueryExtension(bwt_displby, "RANDR",
                                    &opcode, &firstEvent, &firstError);
    if (ret) {
        ret = X11GD_InitXrbndrFuncs(env);
    }
    AWT_FLUSH_UNLOCK();

    return ret;
#endif /* HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsDevice
 * Method:    getCurrentDisplbyMode
 * Signbture: (I)Ljbvb/bwt/DisplbyMode;
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_getCurrentDisplbyMode
    (JNIEnv* env, jclbss x11gd, jint screen)
{
#ifdef HEADLESS
    return NULL;
#else
    XRRScreenConfigurbtion *config;
    jobject displbyMode = NULL;

    AWT_LOCK();

    config = bwt_XRRGetScreenInfo(bwt_displby,
                                  RootWindow(bwt_displby, screen));
    if (config != NULL) {
        Rotbtion rotbtion;
        short curRbte;
        SizeID curSizeIndex;
        XRRScreenSize *sizes;
        int nsizes;

        curSizeIndex = bwt_XRRConfigCurrentConfigurbtion(config, &rotbtion);
        sizes = bwt_XRRConfigSizes(config, &nsizes);
        curRbte = bwt_XRRConfigCurrentRbte(config);

        if ((sizes != NULL) &&
            (curSizeIndex < nsizes))
        {
            XRRScreenSize curSize = sizes[curSizeIndex];
            displbyMode = X11GD_CrebteDisplbyMode(env,
                                                  curSize.width,
                                                  curSize.height,
                                                  BIT_DEPTH_MULTI,
                                                  curRbte);
        }

        bwt_XRRFreeScreenConfigInfo(config);
    }

    AWT_FLUSH_UNLOCK();

    return displbyMode;
#endif /* HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsDevice
 * Method:    enumDisplbyModes
 * Signbture: (ILjbvb/util/ArrbyList;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_enumDisplbyModes
    (JNIEnv* env, jclbss x11gd,
     jint screen, jobject brrbyList)
{
#ifndef HEADLESS
    XRRScreenConfigurbtion *config;

    AWT_LOCK();

    config = bwt_XRRGetScreenInfo(bwt_displby,
                                  RootWindow(bwt_displby, screen));
    if (config != NULL) {
        int nsizes, i, j;
        XRRScreenSize *sizes = bwt_XRRConfigSizes(config, &nsizes);

        if (sizes != NULL) {
            for (i = 0; i < nsizes; i++) {
                int nrbtes;
                XRRScreenSize size = sizes[i];
                short *rbtes = bwt_XRRConfigRbtes(config, i, &nrbtes);

                for (j = 0; j < nrbtes; j++) {
                    X11GD_AddDisplbyMode(env, brrbyList,
                                         size.width,
                                         size.height,
                                         BIT_DEPTH_MULTI,
                                         rbtes[j]);
                    if ((*env)->ExceptionCheck(env)) {
                        brebk;
                    }
                }
            }
        }

        bwt_XRRFreeScreenConfigInfo(config);
    }

    AWT_FLUSH_UNLOCK();
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsDevice
 * Method:    configDisplbyMode
 * Signbture: (IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_configDisplbyMode
    (JNIEnv* env, jclbss x11gd,
     jint screen, jint width, jint height, jint refreshRbte)
{
#ifndef HEADLESS
    jboolebn success = JNI_FALSE;
    XRRScreenConfigurbtion *config;
    Drbwbble root;
    Rotbtion currentRotbtion = RR_Rotbte_0;

    AWT_LOCK();

    root = RootWindow(bwt_displby, screen);
    config = bwt_XRRGetScreenInfo(bwt_displby, root);
    if (config != NULL) {
        jboolebn foundConfig = JNI_FALSE;
        int chosenSizeIndex = -1;
        short chosenRbte = -1;
        int nsizes;
        XRRScreenSize *sizes = bwt_XRRConfigSizes(config, &nsizes);
        bwt_XRRConfigRotbtions(config, &currentRotbtion);

        if (sizes != NULL) {
            int i, j;

            /* find the size index thbt mbtches the requested dimensions */
            for (i = 0; i < nsizes; i++) {
                XRRScreenSize size = sizes[i];

                if ((size.width == width) && (size.height == height)) {
                    /* we've found our size index... */
                    int nrbtes;
                    short *rbtes = bwt_XRRConfigRbtes(config, i, &nrbtes);

                    /* now find rbte thbt mbtches requested refresh rbte */
                    for (j = 0; j < nrbtes; j++) {
                        if (rbtes[j] == refreshRbte) {
                            /* we've found our rbte; brebk out of the loop */
                            chosenSizeIndex = i;
                            chosenRbte = rbtes[j];
                            foundConfig = JNI_TRUE;
                            brebk;
                        }
                    }

                    brebk;
                }
            }
        }

        if (foundConfig) {
            Stbtus stbtus =
                bwt_XRRSetScreenConfigAndRbte(bwt_displby, config, root,
                                              chosenSizeIndex,
                                              currentRotbtion,
                                              chosenRbte,
                                              CurrentTime);

            /* issue XSync to ensure immedibte mode chbnge */
            XSync(bwt_displby, Fblse);

            if (stbtus == RRSetConfigSuccess) {
                success = JNI_TRUE;
            }
        }

        bwt_XRRFreeScreenConfigInfo(config);
    }

    AWT_FLUSH_UNLOCK();

    if (!success) {
        JNU_ThrowInternblError(env, "Could not set displby mode");
    }
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsDevice
 * Method:    enterFullScreenExclusive
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_enterFullScreenExclusive
    (JNIEnv* env, jclbss x11gd,
     jlong window)
{
#ifndef HEADLESS
    Window win = (Window)window;

    AWT_LOCK();
    XSync(bwt_displby, Fblse); /* ensures window is visible first */
    X11GD_SetFullscreenMode(win, JNI_TRUE);
    AWT_UNLOCK();
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_X11GrbphicsDevice
 * Method:    exitFullScreenExclusive
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11GrbphicsDevice_exitFullScreenExclusive
    (JNIEnv* env, jclbss x11gd,
     jlong window)
{
#ifndef HEADLESS
    Window win = (Window)window;

    AWT_LOCK();
    X11GD_SetFullscreenMode(win, JNI_FALSE);
    AWT_UNLOCK();
#endif /* !HEADLESS */
}

/**
 * End DisplbyMode/FullScreen support
 */
