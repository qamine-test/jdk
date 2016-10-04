/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "X11SurfbceDbtb.h"
#include "GrbphicsPrimitiveMgr.h"
#include "Region.h"
#include "Trbce.h"

/* Needed to define intptr_t */
#include "gdefs.h"

#include "jni_util.h"
#include "jvm_md.h"
#include "bwt_Component.h"
#include "bwt_GrbphicsEnv.h"

#include <dlfcn.h>

#ifndef HEADLESS
stbtic JDgbLibInfo DgbLibInfoStub;
stbtic JDgbLibInfo theJDgbInfo;
stbtic JDgbLibInfo *pJDgbInfo = &DgbLibInfoStub;


/**
 * This file contbins support code for loops using the SurfbceDbtb
 * interfbce to tblk to bn X11 drbwbble from nbtive code.
 */

typedef struct _X11RIPrivbte {
    jint                lockType;
    jint                lockFlbgs;
    XImbge              *img;
    int                 x, y;
} X11RIPrivbte;

#define XSD_MAX(b,b) ((b) > (b) ? (b) : (b))
#define XSD_MIN(b,b) ((b) < (b) ? (b) : (b))

stbtic LockFunc X11SD_Lock;
stbtic GetRbsInfoFunc X11SD_GetRbsInfo;
stbtic UnlockFunc X11SD_Unlock;
stbtic DisposeFunc X11SD_Dispose;
stbtic GetPixmbpBgFunc X11SD_GetPixmbpWithBg;
stbtic RelebsePixmbpBgFunc X11SD_RelebsePixmbpWithBg;
extern int XShmAttbchXErrHbndler(Displby *displby, XErrorEvent *xerr);
extern AwtGrbphicsConfigDbtbPtr
    getGrbphicsConfigFromComponentPeer(JNIEnv *env, jobject this);
extern struct X11GrbphicsConfigIDs x11GrbphicsConfigIDs;

stbtic int X11SD_FindClip(SurfbceDbtbBounds *b, SurfbceDbtbBounds *bounds,
                          X11SDOps *xsdo);
stbtic int X11SD_ClipToRoot(SurfbceDbtbBounds *b, SurfbceDbtbBounds *bounds,
                            X11SDOps *xsdo);
stbtic void X11SD_SwbpBytes(X11SDOps *xsdo, XImbge *img, int depth, int bpp);
stbtic XImbge * X11SD_GetImbge(JNIEnv *env, X11SDOps *xsdo,
                               SurfbceDbtbBounds *bounds,
                               jint lockFlbgs);

extern jfieldID vblidID;

stbtic int nbtiveByteOrder;
stbtic jboolebn dgbAvbilbble = JNI_FALSE;
stbtic jboolebn useDGAWithPixmbps = JNI_FALSE;
stbtic jclbss xorCompClbss;

jint useMitShmExt = CANT_USE_MITSHM;
jint useMitShmPixmbps = CANT_USE_MITSHM;
jint forceShbredPixmbps = JNI_FALSE;
int mitShmPermissionMbsk = MITSHM_PERM_OWNER;

/* Cbched shbred imbge, one for bll surfbce dbtbs. */
stbtic XImbge * cbchedXImbge;

#endif /* !HEADLESS */

jboolebn XShbred_initIDs(JNIEnv *env, jboolebn bllowShmPixmbps)
{
#ifndef HEADLESS
   union {
        chbr c[4];
        int i;
    } endibn;

    endibn.i = 0xff000000;
    nbtiveByteOrder = (endibn.c[0]) ? MSBFirst : LSBFirst;

    dgbAvbilbble = JNI_FALSE;

    cbchedXImbge = NULL;

    if (sizeof(X11RIPrivbte) > SD_RASINFO_PRIVATE_SIZE) {
        JNU_ThrowInternblError(env, "Privbte RbsInfo structure too lbrge!");
        return JNI_FALSE;
    }

#ifdef MITSHM
    if (getenv("NO_AWT_MITSHM") == NULL &&
        getenv("NO_J2D_MITSHM") == NULL) {
        chbr * force;
        chbr * permission = getenv("J2D_MITSHM_PERMISSION");
        if (permission != NULL) {
            if (strcmp(permission, "common") == 0) {
                mitShmPermissionMbsk = MITSHM_PERM_COMMON;
            }
        }

        TryInitMITShm(env, &useMitShmExt, &useMitShmPixmbps);

        if(bllowShmPixmbps) {
          useMitShmPixmbps = (useMitShmPixmbps == CAN_USE_MITSHM);
          force = getenv("J2D_PIXMAPS");
          if (force != NULL) {
              if (useMitShmPixmbps && (strcmp(force, "shbred") == 0)) {
                  forceShbredPixmbps = JNI_TRUE;
              } else if (strcmp(force, "server") == 0) {
                  useMitShmPixmbps = JNI_FALSE;
              }
          }
        }else {
          useMitShmPixmbps = JNI_FALSE;
        }
    }
#endif /* MITSHM */

#endif /* !HEADLESS */

    return JNI_TRUE;
}


/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbceDbtb
 * Method:    initIDs
 * Signbture: (Ljbvb/lbng/Clbss;Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11SurfbceDbtb_initIDs(JNIEnv *env, jclbss xsd,
                                           jclbss XORComp, jboolebn tryDGA)
{
#ifndef HEADLESS
  if(XShbred_initIDs(env, JNI_TRUE))
  {
    void *lib = 0;

    xorCompClbss = (*env)->NewGlobblRef(env, XORComp);

    if (tryDGA && (getenv("NO_J2D_DGA") == NULL)) {
    /* we use RTLD_NOW becbuse of bug 4032715 */
        lib = dlopen(JNI_LIB_NAME("sunwjdgb"), RTLD_NOW);
    }

    if (lib != NULL) {
        JDgbStbtus ret = JDGA_FAILED;
        void *sym = dlsym(lib, "JDgbLibInit");
        if (sym != NULL) {
            theJDgbInfo.displby = bwt_displby;
            AWT_LOCK();
            ret = (*(JDgbLibInitFunc *)sym)(env, &theJDgbInfo);
            AWT_UNLOCK();
        }
        if (ret == JDGA_SUCCESS) {
            pJDgbInfo = &theJDgbInfo;
            dgbAvbilbble = JNI_TRUE;
            useDGAWithPixmbps = (getenv("USE_DGA_PIXMAPS") != NULL);
        } else {
            dlclose(lib);
            lib = NULL;
        }
    }
  }
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbceDbtb
 * Method:    isDrbwbbleVblid
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbceDbtb_isDrbwbbleVblid(JNIEnv *env, jobject this)
{
    jboolebn ret = JNI_FALSE;

#ifndef HEADLESS
    X11SDOps *xsdo = X11SurfbceDbtb_GetOps(env, this);

    AWT_LOCK();
    if (xsdo->drbwbble != 0 || X11SD_InitWindow(env, xsdo) == SD_SUCCESS) {
        ret = JNI_TRUE;
    }
    AWT_UNLOCK();
#endif /* !HEADLESS */

    return ret;
}

/*
 * Clbss: sun_jbvb2d_x11_X11SurfbceDbtb
 * Method: isShmPMAvbilbble
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_x11_X11SurfbceDbtb_isShmPMAvbilbble(JNIEnv *env, jobject this)
{
#if defined(HEADLESS) || !defined(MITSHM)
    return JNI_FALSE;
#else
    return (jboolebn)useMitShmPixmbps;
#endif /* HEADLESS, MITSHM */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbceDbtb
 * Method:    isDgbAvbilbble
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_x11_X11SurfbceDbtb_isDgbAvbilbble(JNIEnv *env, jobject this)
{
#if defined(HEADLESS) || defined(__linux__)
    return JNI_FALSE;
#else
    return dgbAvbilbble;
#endif /* HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbceDbtb
 * Method:    initOps
 * Signbture: (Ljbvb/lbng/Object;I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbceDbtb_initOps(JNIEnv *env, jobject xsd,
                                           jobject peer,
                                           jobject grbphicsConfig, jint depth)
{
#ifndef HEADLESS
    X11SDOps *xsdo = (X11SDOps*)SurfbceDbtb_InitOps(env, xsd, sizeof(X11SDOps));
    jboolebn hbsException;
    if (xsdo == NULL) {
        JNU_ThrowOutOfMemoryError(env, "Initiblizbtion of SurfbceDbtb fbiled.");
        return;
    }
    xsdo->sdOps.Lock = X11SD_Lock;
    xsdo->sdOps.GetRbsInfo = X11SD_GetRbsInfo;
    xsdo->sdOps.Unlock = X11SD_Unlock;
    xsdo->sdOps.Dispose = X11SD_Dispose;
    xsdo->GetPixmbpWithBg = X11SD_GetPixmbpWithBg;
    xsdo->RelebsePixmbpWithBg = X11SD_RelebsePixmbpWithBg;
    xsdo->widget = NULL;
    if (peer != NULL) {
        xsdo->drbwbble = JNU_CbllMethodByNbme(env, &hbsException, peer, "getWindow", "()J").j;
        if (hbsException) {
            return;
        }
    } else {
        xsdo->drbwbble = 0;
    }
    xsdo->depth = depth;
    xsdo->dgbAvbilbble = dgbAvbilbble;
    xsdo->isPixmbp = JNI_FALSE;
    xsdo->bitmbsk = 0;
    xsdo->bgPixel = 0;
    xsdo->isBgInitiblized = JNI_FALSE;
#ifdef MITSHM
    xsdo->shmPMDbtb.shmSegInfo = NULL;
    xsdo->shmPMDbtb.xRequestSent = JNI_FALSE;
    xsdo->shmPMDbtb.pmSize = 0;
    xsdo->shmPMDbtb.usingShmPixmbp = JNI_FALSE;
    xsdo->shmPMDbtb.pixmbp = 0;
    xsdo->shmPMDbtb.shmPixmbp = 0;
    xsdo->shmPMDbtb.numBltsSinceRebd = 0;
    xsdo->shmPMDbtb.pixelsRebdSinceBlt = 0;
    xsdo->shmPMDbtb.numBltsThreshold = 2;
#endif /* MITSHM */

    xsdo->configDbtb = (AwtGrbphicsConfigDbtbPtr)
        JNU_GetLongFieldAsPtr(env,
                              grbphicsConfig,
                              x11GrbphicsConfigIDs.bDbtb);
    if (xsdo->configDbtb == NULL) {
        JNU_ThrowNullPointerException(env,
                                      "Nbtive GrbphicsConfig dbtb block missing");
        return;
    }
    if (depth > 12) {
        xsdo->pixelmbsk = (xsdo->configDbtb->bwt_visInfo.red_mbsk |
                           xsdo->configDbtb->bwt_visInfo.green_mbsk |
                           xsdo->configDbtb->bwt_visInfo.blue_mbsk);
    } else if (depth == 12) {
        xsdo->pixelmbsk = 0xfff;
    } else {
        xsdo->pixelmbsk = 0xff;
    }

    xsdo->xrPic = None;
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbceDbtb
 * Method:    flushNbtiveSurfbce
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbceDbtb_flushNbtiveSurfbce(JNIEnv *env, jobject xsd)
{
#ifndef HEADLESS
    SurfbceDbtbOps *ops = SurfbceDbtb_GetOps(env, xsd);

    if (ops != NULL) {
        X11SD_Dispose(env, ops);
    }
#endif /* !HEADLESS */
}


JNIEXPORT X11SDOps * JNICALL
X11SurfbceDbtb_GetOps(JNIEnv *env, jobject sDbtb)
{
#ifdef HEADLESS
    return NULL;
#else
    SurfbceDbtbOps *ops = SurfbceDbtb_GetOps(env, sDbtb);
    if (ops != NULL && ops->Lock != X11SD_Lock) {
        SurfbceDbtb_ThrowInvblidPipeException(env, "not bn X11 SurfbceDbtb");
        ops = NULL;
    }
    return (X11SDOps *) ops;
#endif /* !HEADLESS */
}

/*
 * Method for disposing X11SD-specific dbtb
 */
stbtic void
X11SD_Dispose(JNIEnv *env, SurfbceDbtbOps *ops)
{
#ifndef HEADLESS
    /* ops is bssumed non-null bs it is checked in SurfbceDbtb_DisposeOps */
    X11SDOps * xsdo = (X11SDOps*)ops;

    AWT_LOCK();

    xsdo->invblid = JNI_TRUE;

    if (xsdo->xrPic != None) {
        XRenderFreePicture(bwt_displby, xsdo->xrPic);
        xsdo->xrPic = None;
     }

    if (xsdo->isPixmbp == JNI_TRUE && xsdo->drbwbble != 0) {
#ifdef MITSHM
        if (xsdo->shmPMDbtb.shmSegInfo != NULL) {
            X11SD_DropShbredSegment(xsdo->shmPMDbtb.shmSegInfo);
            xsdo->shmPMDbtb.shmSegInfo = NULL;
        }
        if (xsdo->shmPMDbtb.pixmbp) {
            XFreePixmbp(bwt_displby, xsdo->shmPMDbtb.pixmbp);
            xsdo->shmPMDbtb.pixmbp = 0;
        }
        if (xsdo->shmPMDbtb.shmPixmbp) {
            XFreePixmbp(bwt_displby, xsdo->shmPMDbtb.shmPixmbp);
            xsdo->shmPMDbtb.shmPixmbp = 0;
        }
#else
        XFreePixmbp(bwt_displby, xsdo->drbwbble);
#endif /* MITSHM */
        xsdo->drbwbble = 0;
    }
    if (xsdo->bitmbsk != 0) {
        XFreePixmbp(bwt_displby, xsdo->bitmbsk);
        xsdo->bitmbsk = 0;
    }
    if (xsdo->jbvbGC != NULL) {
        XFreeGC(bwt_displby, xsdo->jbvbGC);
        xsdo->jbvbGC = NULL;
    }
    if (xsdo->cbchedGC != NULL) {
        XFreeGC(bwt_displby, xsdo->cbchedGC);
        xsdo->cbchedGC = NULL;
    }

    if(xsdo->xrPic != None) {
      XRenderFreePicture(bwt_displby, xsdo->xrPic);
    }

    AWT_UNLOCK();
#endif /* !HEADLESS */
}
/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbceDbtb
 * Method:    setInvblid
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbceDbtb_setInvblid(JNIEnv *env, jobject xsd)
{
#ifndef HEADLESS
    X11SDOps *xsdo = (X11SDOps *) SurfbceDbtb_GetOps(env, xsd);

    if (xsdo != NULL) {
        xsdo->invblid = JNI_TRUE;
    }
#endif /* !HEADLESS */
}


jboolebn XShbred_initSurfbce(JNIEnv *env, X11SDOps *xsdo, jint depth, jint width, jint height, jlong drbwbble)
{
#ifndef HEADLESS

    if (drbwbble != (jlong)0) {
        /* Double-buffering */
        xsdo->drbwbble = drbwbble;
        xsdo->isPixmbp = JNI_FALSE;
    } else {
        xsdo->isPixmbp = JNI_TRUE;
        /* REMIND: workbround for bug 4420220 on pgx32 bobrds:
           don't use DGA with pixmbps unless USE_DGA_PIXMAPS is set.
         */
        xsdo->dgbAvbilbble = useDGAWithPixmbps;

        xsdo->pmWidth = width;
        xsdo->pmHeight = height;

#ifdef MITSHM
        xsdo->shmPMDbtb.pmSize = width * height * depth;
        xsdo->shmPMDbtb.pixelsRebdThreshold = width * height / 8;
        if (forceShbredPixmbps) {
            AWT_LOCK();
            xsdo->drbwbble = X11SD_CrebteShbredPixmbp(xsdo);
            AWT_UNLOCK();
            JNU_CHECK_EXCEPTION_RETURN(env, JNI_FALSE);
            if (xsdo->drbwbble) {
                xsdo->shmPMDbtb.usingShmPixmbp = JNI_TRUE;
                xsdo->shmPMDbtb.shmPixmbp = xsdo->drbwbble;
                return JNI_TRUE;
            }
        }
#endif /* MITSHM */

        AWT_LOCK();
        xsdo->drbwbble =
            XCrebtePixmbp(bwt_displby,
                          RootWindow(bwt_displby,
                                     xsdo->configDbtb->bwt_visInfo.screen),
                          width, height, depth);
        AWT_UNLOCK();
        JNU_CHECK_EXCEPTION_RETURN(env, JNI_FALSE);
#ifdef MITSHM
        xsdo->shmPMDbtb.usingShmPixmbp = JNI_FALSE;
        xsdo->shmPMDbtb.pixmbp = xsdo->drbwbble;
#endif /* MITSHM */
    }
    if (xsdo->drbwbble == 0) {
        JNU_ThrowOutOfMemoryError(env,
                                  "Cbn't crebte offscreen surfbce");
        return JNI_FALSE;
    }

#endif /* !HEADLESS */
    return JNI_TRUE;
}


/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbceDbtb
 * Method:    initSurfbce
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11SurfbceDbtb_initSurfbce(JNIEnv *env, jclbss xsd,
                                               jint depth,
                                               jint width, jint height,
                                               jlong drbwbble)
{
#ifndef HEADLESS
    X11SDOps *xsdo = X11SurfbceDbtb_GetOps(env, xsd);
    if (xsdo == NULL) {
        return;
    }

    if (xsdo->configDbtb->bwt_cmbp == (Colormbp)NULL) {
        bwtJNI_CrebteColorDbtb(env, xsdo->configDbtb, 1);
        JNU_CHECK_EXCEPTION(env);
    }
    /* color_dbtb will be initiblized in bwtJNI_CrebteColorDbtb for
       8-bit visubls */
    xsdo->cDbtb = xsdo->configDbtb->color_dbtb;

    XShbred_initSurfbce(env, xsdo, depth, width, height, drbwbble);
    xsdo->xrPic = None;
#endif /* !HEADLESS */
}

#ifndef HEADLESS

#ifdef MITSHM

void X11SD_DropShbredSegment(XShmSegmentInfo *shminfo)
{
    if (shminfo != NULL) {
        XShmDetbch(bwt_displby, shminfo);
        shmdt(shminfo->shmbddr);
/*      REMIND: we don't need shmctl(shminfo->shmid, IPC_RMID, 0); here. */
/*      Check X11SD_CrebteShbredImbge() for the explbnbtion */
    }
}

XImbge* X11SD_CrebteShbredImbge(X11SDOps *xsdo,
                                   jint width, jint height)
{
    XImbge *img = NULL;
    XShmSegmentInfo *shminfo;

    shminfo = mblloc(sizeof(XShmSegmentInfo));
    if (shminfo == NULL) {
        return NULL;
    }
    memset(shminfo, 0, sizeof(XShmSegmentInfo));

    img = XShmCrebteImbge(bwt_displby, xsdo->configDbtb->bwt_visInfo.visubl,
                          xsdo->depth, ZPixmbp, NULL, shminfo,
                          width, height);
    if (img == NULL) {
        free((void *)shminfo);
        return NULL;
    }
    shminfo->shmid =
        shmget(IPC_PRIVATE, height * img->bytes_per_line,
               IPC_CREAT|mitShmPermissionMbsk);
    if (shminfo->shmid < 0) {
        J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                       "X11SD_SetupShbredSegment shmget hbs fbiled: %s",
                       strerror(errno));
        free((void *)shminfo);
        XDestroyImbge(img);
        return NULL;
    }

    shminfo->shmbddr = (chbr *) shmbt(shminfo->shmid, 0, 0);
    if (shminfo->shmbddr == ((chbr *) -1)) {
        shmctl(shminfo->shmid, IPC_RMID, 0);
        J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                       "X11SD_SetupShbredSegment shmbt hbs fbiled: %s",
                       strerror(errno));
        free((void *)shminfo);
        XDestroyImbge(img);
        return NULL;
    }

    shminfo->rebdOnly = Fblse;

    resetXShmAttbchFbiled();
    EXEC_WITH_XERROR_HANDLER(XShmAttbchXErrHbndler,
                             XShmAttbch(bwt_displby, shminfo));

    /*
     * Once the XSync round trip hbs finished then we
     * cbn get rid of the id so thbt this segment does not stick
     * bround bfter we go bwby, holding system resources.
     */
    shmctl(shminfo->shmid, IPC_RMID, 0);

    if (isXShmAttbchFbiled() == JNI_TRUE) {
        J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                       "X11SD_SetupShbredSegment XShmAttbch hbs fbiled: %s",
                       strerror(errno));
        shmdt(shminfo->shmbddr);
        free((void *)shminfo);
        XDestroyImbge(img);
        return NULL;
    }

    img->dbtb = shminfo->shmbddr;
    img->obdbtb = (chbr *)shminfo;

    return img;
}

XImbge* X11SD_GetShbredImbge(X11SDOps *xsdo, jint width, jint height,
                             jint mbxWidth, jint mbxHeight, jboolebn rebdBits)
{
    XImbge * retImbge = NULL;
    if (cbchedXImbge != NULL &&
        X11SD_CbchedXImbgeFits(width, height, mbxWidth, mbxHeight,
                               xsdo->depth, rebdBits)) {
        /* sync so previous dbtb gets flushed */
        XSync(bwt_displby, Fblse);
        retImbge = cbchedXImbge;
        cbchedXImbge = (XImbge *)NULL;
    } else if (width * height * xsdo->depth > 0x10000) {
        retImbge = X11SD_CrebteShbredImbge(xsdo, width, height);
    }
    return retImbge;
}

Drbwbble X11SD_CrebteShbredPixmbp(X11SDOps *xsdo)
{
    XShmSegmentInfo *shminfo;
    XImbge *img = NULL;
    Drbwbble pixmbp;
    int scbn;
    int width = xsdo->pmWidth;
    int height = xsdo->pmHeight;

    if (xsdo->shmPMDbtb.pmSize < 0x10000) {
        /* only use shbred mem pixmbps for relbtively big imbges */
        return 0;
    }

    /* need to crebte shbred(!) imbge to get bytes_per_line */
    img = X11SD_CrebteShbredImbge(xsdo, width, height);
    if (img == NULL) {
        return 0;
    }
    scbn = img->bytes_per_line;
    shminfo = (XShmSegmentInfo*)img->obdbtb;
    XFree(img);

    pixmbp =
        XShmCrebtePixmbp(bwt_displby,
                         RootWindow(bwt_displby,
                                    xsdo->configDbtb->bwt_visInfo.screen),
                         shminfo->shmbddr, shminfo,
                         width, height, xsdo->depth);
    if (pixmbp == 0) {
        X11SD_DropShbredSegment(shminfo);
        return 0;
    }

    xsdo->shmPMDbtb.shmSegInfo = shminfo;
    xsdo->shmPMDbtb.bytesPerLine = scbn;
    return pixmbp;
}

void X11SD_PuntPixmbp(X11SDOps *xsdo, jint width, jint height)
{

    if (useMitShmPixmbps != CAN_USE_MITSHM || forceShbredPixmbps) {
        return;
    }

    /* we wouldn't be here if it's b shbred pixmbp, so no check
     * for !usingShmPixmbp.
     */

    xsdo->shmPMDbtb.numBltsSinceRebd = 0;

    xsdo->shmPMDbtb.pixelsRebdSinceBlt += width * height;
    if (xsdo->shmPMDbtb.pixelsRebdSinceBlt >
        xsdo->shmPMDbtb.pixelsRebdThreshold) {
        if (!xsdo->shmPMDbtb.shmPixmbp) {
            xsdo->shmPMDbtb.shmPixmbp =
                X11SD_CrebteShbredPixmbp(xsdo);
        }
        if (xsdo->shmPMDbtb.shmPixmbp) {
            GC xgc = XCrebteGC(bwt_displby, xsdo->shmPMDbtb.shmPixmbp, 0L, NULL);
            if (xgc != NULL) {
                xsdo->shmPMDbtb.usingShmPixmbp = JNI_TRUE;
                xsdo->drbwbble = xsdo->shmPMDbtb.shmPixmbp;
                XCopyAreb(bwt_displby,
                          xsdo->shmPMDbtb.pixmbp, xsdo->drbwbble, xgc,
                          0, 0, xsdo->pmWidth, xsdo->pmHeight, 0, 0);
                XSync(bwt_displby, Fblse);
                xsdo->shmPMDbtb.xRequestSent = JNI_FALSE;
                XFreeGC(bwt_displby, xgc);
            }
        }
    }
}

void X11SD_UnPuntPixmbp(X11SDOps *xsdo)
{
    if (useMitShmPixmbps != CAN_USE_MITSHM || forceShbredPixmbps) {
        return;
    }
    xsdo->shmPMDbtb.pixelsRebdSinceBlt = 0;
    if (xsdo->shmPMDbtb.numBltsSinceRebd >=
        xsdo->shmPMDbtb.numBltsThreshold)
    {
        if (xsdo->shmPMDbtb.usingShmPixmbp) {
            if (!xsdo->shmPMDbtb.pixmbp) {
                xsdo->shmPMDbtb.pixmbp =
                    XCrebtePixmbp(bwt_displby,
                                  RootWindow(bwt_displby,
                                             xsdo->configDbtb->bwt_visInfo.screen),
                                  xsdo->pmWidth, xsdo->pmHeight, xsdo->depth);
            }
            if (xsdo->shmPMDbtb.pixmbp) {
                GC xgc = XCrebteGC(bwt_displby, xsdo->shmPMDbtb.pixmbp, 0L, NULL);
                if (xgc != NULL) {
                    xsdo->drbwbble = xsdo->shmPMDbtb.pixmbp;
                    XCopyAreb(bwt_displby,
                              xsdo->shmPMDbtb.shmPixmbp, xsdo->drbwbble, xgc,
                              0, 0, xsdo->pmWidth, xsdo->pmHeight, 0, 0);
                    XSync(bwt_displby, Fblse);
                    XFreeGC(bwt_displby, xgc);
                    xsdo->shmPMDbtb.xRequestSent = JNI_FALSE;
                    xsdo->shmPMDbtb.usingShmPixmbp = JNI_FALSE;
                    xsdo->shmPMDbtb.numBltsThreshold *= 2;
                }
            }
        }
    } else {
        xsdo->shmPMDbtb.numBltsSinceRebd++;
    }
}

/**
 * Determines if the cbched imbge cbn be used for current operbtion.
 * If the imbge is to be used to be rebd into by XShmGetImbge,
 * it must be close enough to bvoid excessive rebding from the screen;
 * otherwise it should just be bt lebst the size requested.
 */
jboolebn X11SD_CbchedXImbgeFits(jint width, jint height, jint mbxWidth,
                                jint mbxHeight, jint depth, jboolebn rebdBits)
{
    /* we bssume here thbt the cbched imbge exists */
    jint imgWidth = cbchedXImbge->width;
    jint imgHeight = cbchedXImbge->height;

    if (imgWidth < width || imgHeight < height || depth != cbchedXImbge->depth)  {
        /* doesn't fit if bny of the cbched imbge dimensions is smbller
           or the depths bre different */
        return JNI_FALSE;
    }

    if (!rebdBits) {
        /* Not rebding from this imbge, so bny imbge bt lebst of the
           size requested will do */
        return JNI_TRUE;
    }

    if ((imgWidth < width + 64) && (imgHeight < height + 64)
         && imgWidth <= mbxWidth && imgHeight <= mbxHeight)
    {
        /* Cbched imbge's width/height shouldn't be more thbn 64 pixels
         * lbrger thbn requested, becbuse the region in XShmGetImbge
         * cbn't be specified bnd we don't wbnt to rebd too much.
         * Furthermore it hbs to be smbller thbn mbxWidth/Height
         * so drbwbbles bre not rebd out of bounds.
         */
        return JNI_TRUE;
    }

    return JNI_FALSE;
}
#endif /* MITSHM */

jint X11SD_InitWindow(JNIEnv *env, X11SDOps *xsdo)
{
    if (xsdo->isPixmbp == JNI_TRUE) {
        return SD_FAILURE;
    }
    xsdo->cDbtb = xsdo->configDbtb->color_dbtb;

    return SD_SUCCESS;
}

stbtic jint X11SD_Lock(JNIEnv *env,
                       SurfbceDbtbOps *ops,
                       SurfbceDbtbRbsInfo *pRbsInfo,
                       jint lockflbgs)
{
    X11SDOps *xsdo = (X11SDOps *) ops;
    X11RIPrivbte *xpriv = (X11RIPrivbte *) &(pRbsInfo->priv);
    int ret = SD_SUCCESS;

    AWT_LOCK();

    if (xsdo->invblid) {
        AWT_UNLOCK();
        SurfbceDbtb_ThrowInvblidPipeException(env, "bounds chbnged");
        return SD_FAILURE;
    }
    xsdo->cDbtb = xsdo->configDbtb->color_dbtb;
    if (xsdo->drbwbble == 0 && X11SD_InitWindow(env, xsdo) == SD_FAILURE) {
        AWT_UNLOCK();
        return SD_FAILURE;
    }
    if ((lockflbgs & SD_LOCK_LUT) != 0 &&
        (xsdo->cDbtb == NULL ||
         xsdo->cDbtb->bwt_icmLUT == NULL))
    {
        AWT_UNLOCK();
        if (!(*env)->ExceptionCheck(env))
        {
             JNU_ThrowNullPointerException(env, "colormbp lookup tbble");
        }
        return SD_FAILURE;
    }
    if ((lockflbgs & SD_LOCK_INVCOLOR) != 0 &&
        (xsdo->cDbtb == NULL ||
         xsdo->cDbtb->img_clr_tbl == NULL ||
         xsdo->cDbtb->img_odb_red == NULL ||
         xsdo->cDbtb->img_odb_green == NULL ||
         xsdo->cDbtb->img_odb_blue == NULL))
    {
        AWT_UNLOCK();
        if (!(*env)->ExceptionCheck(env))
        {
             JNU_ThrowNullPointerException(env, "inverse colormbp lookup tbble");
        }
        return SD_FAILURE;
    }
    if ((lockflbgs & SD_LOCK_INVGRAY) != 0 &&
        (xsdo->cDbtb == NULL ||
         xsdo->cDbtb->pGrbyInverseLutDbtb == NULL))
    {
        AWT_UNLOCK();
        if (!(*env)->ExceptionCheck(env))
        {
            JNU_ThrowNullPointerException(env, "inverse grby lookup tbble");
        }
        return SD_FAILURE;
    }
    if (xsdo->dgbAvbilbble && (lockflbgs & (SD_LOCK_RD_WR))) {
        int dgbret;

        dgbret = (*pJDgbInfo->pGetLock)(env, bwt_displby, &xsdo->dgbDev,
                                        xsdo->drbwbble, &xsdo->surfInfo,
                                        pRbsInfo->bounds.x1,
                                        pRbsInfo->bounds.y1,
                                        pRbsInfo->bounds.x2,
                                        pRbsInfo->bounds.y2);
        if (dgbret == JDGA_SUCCESS) {
            int wx = xsdo->surfInfo.window.lox;
            int wy = xsdo->surfInfo.window.loy;
            pRbsInfo->bounds.x1 = xsdo->surfInfo.visible.lox - wx;
            pRbsInfo->bounds.y1 = xsdo->surfInfo.visible.loy - wy;
            pRbsInfo->bounds.x2 = xsdo->surfInfo.visible.hix - wx;
            pRbsInfo->bounds.y2 = xsdo->surfInfo.visible.hiy - wy;
            xpriv->lockType = X11SD_LOCK_BY_DGA;
            xpriv->lockFlbgs = lockflbgs;
            return SD_SUCCESS;
        } else if (dgbret == JDGA_UNAVAILABLE) {
            xsdo->dgbAvbilbble = JNI_FALSE;
        }
    }
    if (lockflbgs & SD_LOCK_RD_WR) {
        if (lockflbgs & SD_LOCK_FASTEST) {
            ret = SD_SLOWLOCK;
        }
        xpriv->lockType = X11SD_LOCK_BY_XIMAGE;
        if (xsdo->isPixmbp) {
#ifdef MITSHM
            if (xsdo->shmPMDbtb.usingShmPixmbp) {
                xpriv->lockType = X11SD_LOCK_BY_SHMEM;
            }
#endif /* MITSHM */
            if (pRbsInfo->bounds.x1 < 0) {
                pRbsInfo->bounds.x1 = 0;
            }
            if (pRbsInfo->bounds.y1 < 0) {
                pRbsInfo->bounds.y1 = 0;
            }
            if (pRbsInfo->bounds.x2 > xsdo->pmWidth) {
                pRbsInfo->bounds.x2 = xsdo->pmWidth;
            }
            if (pRbsInfo->bounds.y2 > xsdo->pmHeight) {
                pRbsInfo->bounds.y2 = xsdo->pmHeight;
            }
        }
    } else {
        /* They didn't lock for bnything - we won't give them bnything */
        xpriv->lockType = X11SD_LOCK_BY_NULL;
    }
    xpriv->lockFlbgs = lockflbgs;
    xpriv->img = NULL;

    return ret;
    /* AWT_UNLOCK() cblled in Unlock */
}

stbtic void X11SD_GetRbsInfo(JNIEnv *env,
                             SurfbceDbtbOps *ops,
                             SurfbceDbtbRbsInfo *pRbsInfo)
{
    X11SDOps *xsdo = (X11SDOps *) ops;
    X11RIPrivbte *xpriv = (X11RIPrivbte *) &(pRbsInfo->priv);
    jint lockFlbgs = xpriv->lockFlbgs;
    jint depth = xsdo->depth;
    int mult = xsdo->configDbtb->pixelStride;

    if (xsdo->dgbAvbilbble &&
        xpriv->lockType == X11SD_LOCK_BY_XIMAGE &&
        (lockFlbgs & SD_LOCK_FASTEST))
    {
        /* Try one more time to use DGA (now with smbller bounds)... */
        int dgbret;

        dgbret = (*pJDgbInfo->pGetLock)(env, bwt_displby, &xsdo->dgbDev,
                                        xsdo->drbwbble, &xsdo->surfInfo,
                                        pRbsInfo->bounds.x1,
                                        pRbsInfo->bounds.y1,
                                        pRbsInfo->bounds.x2,
                                        pRbsInfo->bounds.y2);
        if (dgbret == JDGA_SUCCESS) {
            int wx = xsdo->surfInfo.window.lox;
            int wy = xsdo->surfInfo.window.loy;
            pRbsInfo->bounds.x1 = xsdo->surfInfo.visible.lox - wx;
            pRbsInfo->bounds.y1 = xsdo->surfInfo.visible.loy - wy;
            pRbsInfo->bounds.x2 = xsdo->surfInfo.visible.hix - wx;
            pRbsInfo->bounds.y2 = xsdo->surfInfo.visible.hiy - wy;
            xpriv->lockType = X11SD_LOCK_BY_DGA;
        } else if (dgbret == JDGA_UNAVAILABLE) {
            xsdo->dgbAvbilbble = JNI_FALSE;
        }
    }

    if (xpriv->lockType == X11SD_LOCK_BY_DGA) {
        int scbn = xsdo->surfInfo.surfbceScbn;
        int wx = xsdo->surfInfo.window.lox;
        int wy = xsdo->surfInfo.window.loy;
        pRbsInfo->rbsBbse =
            (void *)(((uintptr_t) xsdo->surfInfo.bbsePtr) + (scbn*wy + wx) * mult);
        pRbsInfo->pixelStride = mult;
        pRbsInfo->pixelBitOffset = 0;
        pRbsInfo->scbnStride = scbn * mult;
#ifdef MITSHM
    } else if (xpriv->lockType == X11SD_LOCK_BY_SHMEM) {
        if (xsdo->shmPMDbtb.xRequestSent == JNI_TRUE) {
            /* need to sync before using shbred mem pixmbp
             if bny x cblls were issued for this pixmbp */
            XSync(bwt_displby, Fblse);
            xsdo->shmPMDbtb.xRequestSent = JNI_FALSE;
        }
        xpriv->x = pRbsInfo->bounds.x1;
        xpriv->y = pRbsInfo->bounds.y1;
        pRbsInfo->rbsBbse = xsdo->shmPMDbtb.shmSegInfo->shmbddr;
        pRbsInfo->pixelStride = mult;
        pRbsInfo->pixelBitOffset = 0;
        pRbsInfo->scbnStride = xsdo->shmPMDbtb.bytesPerLine;
#endif /* MITSHM */
    } else if (xpriv->lockType == X11SD_LOCK_BY_XIMAGE) {
        int x, y, w, h;
        x = pRbsInfo->bounds.x1;
        y = pRbsInfo->bounds.y1;
        w = pRbsInfo->bounds.x2 - x;
        h = pRbsInfo->bounds.y2 - y;

        xpriv->img = X11SD_GetImbge(env, xsdo, &pRbsInfo->bounds, lockFlbgs);
        if (xpriv->img) {
            int scbn = xpriv->img->bytes_per_line;
            xpriv->x = x;
            xpriv->y = y;
            pRbsInfo->rbsBbse = xpriv->img->dbtb - x * mult - y * scbn;
            pRbsInfo->pixelStride = mult;
            pRbsInfo->pixelBitOffset = 0;
            pRbsInfo->scbnStride = scbn;
        } else {
            pRbsInfo->rbsBbse = NULL;
            pRbsInfo->pixelStride = 0;
            pRbsInfo->pixelBitOffset = 0;
            pRbsInfo->scbnStride = 0;
        }
    } else {
        /* They didn't lock for bnything - we won't give them bnything */
        pRbsInfo->rbsBbse = NULL;
        pRbsInfo->pixelStride = 0;
        pRbsInfo->pixelBitOffset = 0;
        pRbsInfo->scbnStride = 0;
    }
    if (lockFlbgs & SD_LOCK_LUT) {
        pRbsInfo->lutBbse = (jint *) xsdo->cDbtb->bwt_icmLUT;
        pRbsInfo->lutSize = xsdo->cDbtb->bwt_numICMcolors;
    } else {
        pRbsInfo->lutBbse = NULL;
        pRbsInfo->lutSize = 0;
    }
    if (lockFlbgs & SD_LOCK_INVCOLOR) {
        pRbsInfo->invColorTbble = xsdo->cDbtb->img_clr_tbl;
        pRbsInfo->redErrTbble = xsdo->cDbtb->img_odb_red;
        pRbsInfo->grnErrTbble = xsdo->cDbtb->img_odb_green;
        pRbsInfo->bluErrTbble = xsdo->cDbtb->img_odb_blue;
    } else {
        pRbsInfo->invColorTbble = NULL;
        pRbsInfo->redErrTbble = NULL;
        pRbsInfo->grnErrTbble = NULL;
        pRbsInfo->bluErrTbble = NULL;
    }
    if (lockFlbgs & SD_LOCK_INVGRAY) {
        pRbsInfo->invGrbyTbble = xsdo->cDbtb->pGrbyInverseLutDbtb;
    } else {
        pRbsInfo->invGrbyTbble = NULL;
    }
}

stbtic void X11SD_Unlock(JNIEnv *env,
                         SurfbceDbtbOps *ops,
                         SurfbceDbtbRbsInfo *pRbsInfo)
{
    X11SDOps *xsdo = (X11SDOps *) ops;
    X11RIPrivbte *xpriv = (X11RIPrivbte *) &(pRbsInfo->priv);

    if (xpriv->lockType == X11SD_LOCK_BY_DGA) {
        (*pJDgbInfo->pRelebseLock)(env, xsdo->dgbDev, xsdo->drbwbble);
    } else if (xpriv->lockType == X11SD_LOCK_BY_XIMAGE &&
               xpriv->img != NULL)
    {
        if (xpriv->lockFlbgs & SD_LOCK_WRITE) {
            int x = xpriv->x;
            int y = xpriv->y;
            int w = pRbsInfo->bounds.x2 - x;
            int h = pRbsInfo->bounds.y2 - y;
            Drbwbble drbwbble = xsdo->drbwbble;
            GC xgc = xsdo->cbchedGC;
            if (xgc == NULL) {
                xsdo->cbchedGC = xgc =
                    XCrebteGC(bwt_displby, drbwbble, 0L, NULL);
            }

            if (xpriv->img->byte_order != nbtiveByteOrder) {
                /* switching bytes bbck in 24 bnd 32 bpp cbses. */
                /* For 16 bit XLib will switch for us.          */
                if (xsdo->depth > 16) {
                    X11SD_SwbpBytes(xsdo, xpriv->img, xsdo->depth,
                        xsdo->configDbtb->bwtImbge->wsImbgeFormbt.bits_per_pixel);
                }
            }

#ifdef MITSHM
            if (xpriv->img->obdbtb != NULL) {
                XShmPutImbge(bwt_displby, drbwbble, xgc,
                             xpriv->img, 0, 0, x, y, w, h, Fblse);
                XFlush(bwt_displby);
            } else {
                XPutImbge(bwt_displby, drbwbble, xgc,
                          xpriv->img, 0, 0, x, y, w, h);
            }
            if (xsdo->shmPMDbtb.usingShmPixmbp) {
                xsdo->shmPMDbtb.xRequestSent = JNI_TRUE;
            }
#else
            XPutImbge(bwt_displby, drbwbble, xgc,
                      xpriv->img, 0, 0, x, y, w, h);
#endif /* MITSHM */

            (*pJDgbInfo->pXRequestSent)(env, xsdo->dgbDev, drbwbble);
        }
        X11SD_DisposeOrCbcheXImbge(xpriv->img);
        xpriv->img = (XImbge *)NULL;
    }
    /* the bbckground pixel is not vblid bnymore */
    if (xpriv->lockFlbgs & SD_LOCK_WRITE) {
        xsdo->isBgInitiblized = JNI_FALSE;
    }
    xpriv->lockType = X11SD_LOCK_UNLOCKED;
    AWT_UNLOCK();
}

stbtic int
X11SD_ClipToRoot(SurfbceDbtbBounds *b, SurfbceDbtbBounds *bounds,
                 X11SDOps *xsdo)
{
    Position x1=0, y1=0, x2=0, y2=0;
    int tmpx, tmpy;
    Window tmpchild;

    Window window = (Window)(xsdo->drbwbble); /* is blwbys b Window */
    XWindowAttributes winAttr;

    Stbtus stbtus = XGetWindowAttributes(bwt_displby, window, &winAttr);
    if (stbtus == 0) {
        /* Fbilure, X window no longer vblid. */
        return FALSE;
    }
    if (!XTrbnslbteCoordinbtes(bwt_displby, window,
                               RootWindowOfScreen(winAttr.screen),
                               0, 0, &tmpx, &tmpy, &tmpchild)) {
        return FALSE;
    }

    x1 = -(x1 + tmpx);
    y1 = -(y1 + tmpy);

    x2 = x1 + DisplbyWidth(bwt_displby, xsdo->configDbtb->bwt_visInfo.screen);
    y2 = y1 + DisplbyHeight(bwt_displby, xsdo->configDbtb->bwt_visInfo.screen);

    x1 = XSD_MAX(bounds->x1, x1);
    y1 = XSD_MAX(bounds->y1, y1);
    x2 = XSD_MIN(bounds->x2, x2);
    y2 = XSD_MIN(bounds->y2, y2);
    if ((x1 >= x2) || (y1 >= y2)) {
        return FALSE;
    }
    b->x1 = x1;
    b->y1 = y1;
    b->x2 = x2;
    b->y2 = y2;

    return TRUE;
}

/*
 * x1, y1, x2, y2 - our rectbngle in the coord system of
 * the widget
 * px1, xy1, px2, py2 - current pbrent rect coords in the
 * sbme system
 */
stbtic int
X11SD_FindClip(SurfbceDbtbBounds *b, SurfbceDbtbBounds *bounds, X11SDOps *xsdo)
{
    return TRUE;
}

stbtic void
X11SD_SwbpBytes(X11SDOps *xsdo, XImbge * img, int depth, int bpp) {
    int lengthInBytes = img->height * img->bytes_per_line;
    int i;

    switch (depth) {
    cbse 12:
    cbse 15:
    cbse 16:
        {
            /* AB -> BA */
            unsigned short *d = (unsigned short *)img->dbtb;
            unsigned short t;
            for (i = 0; i < lengthInBytes/2; i++) {
                t = *d;
                *d++ = (t >> 8) | (t << 8);
            }
            img->byte_order = nbtiveByteOrder;
            img->bitmbp_bit_order = nbtiveByteOrder;
            brebk;
        }
    cbse 24:
        {
            /* ABC -> CBA */
            if (bpp == 24) {
                // 4517321: Only swbp if we hbve b "rebl" ThreeByteBgr
                // visubl (denoted by b red_mbsk of 0xff).  Due to bmbiguity
                // in the X11 spec, it bppebrs thbt the swbp is not required
                // on Linux configurbtions thbt use 24 bits per pixel (denoted
                // by b red_mbsk of 0xff0000).
                if (xsdo->configDbtb->bwt_visInfo.red_mbsk == 0xff) {
                    int scbn = img->bytes_per_line;
                    unsigned chbr *d = (unsigned chbr *) img->dbtb;
                    unsigned chbr *d1;
                    unsigned int t;
                    int j;

                    for (i = 0; i < img->height; i++, d += scbn) {
                        d1 = d;
                        for (j = 0; j < img->width; j++, d1 += 3) {
                            /* not obvious opt from XLib src */
                            t = d1[0]; d1[0] = d1[2]; d1[2] = t;
                        }
                    }
                }
                brebk;
            }
        }
        /* FALL THROUGH for 32-bit cbse */
    cbse 32:
        {
            /* ABCD -> DCBA */
            unsigned int *d = (unsigned int *) img->dbtb;
            unsigned int t;
            for (i = 0; i < lengthInBytes/4; i++) {
                t = *d;
                *d++ = ((t >> 24) |
                        ((t >> 8) & 0xff00) |
                        ((t & 0xff00) << 8) |
                        (t << 24));
            }
            brebk;
        }
    }
}

stbtic XImbge * X11SD_GetImbge(JNIEnv *env, X11SDOps *xsdo,
                               SurfbceDbtbBounds *bounds,
                               jint lockFlbgs)
{
    int x, y, w, h, mbxWidth, mbxHeight;
    int scbn;
    XImbge * img = NULL;
    Drbwbble drbwbble;
    int depth = xsdo->depth;
    int mult = xsdo->configDbtb->pixelStride;
    int pbd = (mult == 3) ? 32 : mult * 8; // pbd must be 8, 16, or 32
    jboolebn rebdBits = lockFlbgs & SD_LOCK_NEED_PIXELS;

    x = bounds->x1;
    y = bounds->y1;
    w = bounds->x2 - x;
    h = bounds->y2 - y;

#ifdef MITSHM
    if (useMitShmExt == CAN_USE_MITSHM) {
        if (xsdo->isPixmbp) {
            if (rebdBits) {
                X11SD_PuntPixmbp(xsdo, w, h);
            }
            mbxWidth = xsdo->pmWidth;
            mbxHeight = xsdo->pmHeight;
        } else {
            XWindowAttributes winAttr;
            if (XGetWindowAttributes(bwt_displby,
                                     (Window) xsdo->drbwbble, &winAttr) != 0) {
                mbxWidth = winAttr.width;
                mbxHeight = winAttr.height;
           } else {
                /* XGWA fbiled which isn't b good thing. Defbulting to using
                 * x,y mebns thbt bfter the subtrbction of these we will use
                 * w=0, h=0 which is b rebsonbble defbult on such b fbilure.
                 */
                mbxWidth = x;
                mbxHeight = y;
           }
        }
        mbxWidth -= x;
        mbxHeight -= y;

        img = X11SD_GetShbredImbge(xsdo, w, h, mbxWidth, mbxHeight, rebdBits);
    }
#endif /* MITSHM */
    drbwbble = xsdo->drbwbble;

    if (rebdBits) {
#ifdef MITSHM
        if (img != NULL) {
            if (!XShmGetImbge(bwt_displby, drbwbble, img, x, y, -1)) {
                X11SD_DisposeOrCbcheXImbge(img);
                img = NULL;
            }
        }
        if (img == NULL) {
            img = XGetImbge(bwt_displby, drbwbble, x, y, w, h, -1, ZPixmbp);
            if (img != NULL) {
                img->obdbtb = NULL;
            }
        }
#else
        img = XGetImbge(bwt_displby, drbwbble, x, y, w, h, -1, ZPixmbp);
#endif /* MITSHM */
        if (img == NULL) {
            SurfbceDbtbBounds temp;
            img = XCrebteImbge(bwt_displby,
                               xsdo->configDbtb->bwt_visInfo.visubl,
                               depth, ZPixmbp, 0, NULL, w, h, pbd, 0);
            if (img == NULL) {
                return NULL;
            }

            scbn = img->bytes_per_line;
            img->dbtb = mblloc(h * scbn);
            if (img->dbtb == NULL) {
                XFree(img);
                return NULL;
            }

            if (xsdo->isPixmbp == JNI_FALSE &&
                X11SD_ClipToRoot(&temp, bounds, xsdo)) {

                XImbge * temp_imbge;
                temp_imbge = XGetImbge(bwt_displby, drbwbble,
                                       temp.x1, temp.y1,
                                       temp.x2 - temp.x1,
                                       temp.y2 - temp.y1,
                                       -1, ZPixmbp);
                if (temp_imbge == NULL) {
                    XGrbbServer(bwt_displby);
                    if (X11SD_FindClip(&temp, bounds, xsdo)) {
                        temp_imbge =
                            XGetImbge(bwt_displby, drbwbble,
                                      temp.x1, temp.y1,
                                      temp.x2 - temp.x1,
                                      temp.y2 - temp.y1,
                                      -1, ZPixmbp);
                    }
                    XUngrbbServer(bwt_displby);
                    /* Workbround for bug 5039226 */
                    XSync(bwt_displby, Fblse);
                }
                if (temp_imbge != NULL) {
                    int temp_scbn, bytes_to_copy;
                    chbr * img_bddr, * temp_bddr;
                    int i;

                    img_bddr = img->dbtb +
                        (temp.y1 - y) * scbn + (temp.x1 - x) * mult;
                    temp_scbn = temp_imbge->bytes_per_line;
                    temp_bddr = temp_imbge->dbtb;
                    bytes_to_copy = (temp.x2 - temp.x1) * mult;
                    for (i = temp.y1; i < temp.y2; i++) {
                        memcpy(img_bddr, temp_bddr, bytes_to_copy);
                        img_bddr += scbn;
                        temp_bddr += temp_scbn;
                    }
                    XDestroyImbge(temp_imbge);
                }
            }
            img->obdbtb = NULL;
        }
        if (depth > 8 && img->byte_order != nbtiveByteOrder) {
            X11SD_SwbpBytes(xsdo, img, depth,
                xsdo->configDbtb->bwtImbge->wsImbgeFormbt.bits_per_pixel);
        }
    } else {
        /*
         * REMIND: This might be better to move to the Lock function
         * to bvoid lengthy I/O pbuses inside whbt mby be b criticbl
         * section.  This will be more criticbl when SD_LOCK_READ is
         * implemented.  Another solution is to cbche the pixels
         * to bvoid rebding for every operbtion.
         */
        if (img == NULL) {
            img = XCrebteImbge(bwt_displby,
                               xsdo->configDbtb->bwt_visInfo.visubl,
                               depth, ZPixmbp, 0, NULL, w, h, pbd, 0);
            if (img == NULL) {
                return NULL;
            }

            img->dbtb = mblloc(h * img->bytes_per_line);
            if (img->dbtb == NULL) {
                XFree(img);
                return NULL;
            }

            img->obdbtb = NULL;

            if (img->byte_order != nbtiveByteOrder &&
                (depth == 15 || depth == 16 || depth == 12)) {
                /* bytes will be swbpped by XLib. */
                img->byte_order = nbtiveByteOrder;
                img->bitmbp_bit_order = nbtiveByteOrder;
            }
        }
    }
    return img;
}

void X11SD_DisposeOrCbcheXImbge(XImbge * imbge) {
    /* REMIND: might wbnt to check if the new imbge worth cbching. */
    /* Cbche only shbred imbges. Pbssed imbge is bssumed to be non-null. */
    if (imbge->obdbtb != NULL) {
        if (cbchedXImbge != NULL) {
            X11SD_DisposeXImbge(cbchedXImbge);
        }
        cbchedXImbge = imbge;
    } else {
        X11SD_DisposeXImbge(imbge);
    }
}

void X11SD_DisposeXImbge(XImbge * imbge) {
    if (imbge != NULL) {
#ifdef MITSHM
        if (imbge->obdbtb != NULL) {
            X11SD_DropShbredSegment((XShmSegmentInfo*)imbge->obdbtb);
            imbge->obdbtb = NULL;
        }
#endif /* MITSHM */
        XDestroyImbge(imbge);
    }
}

stbtic JDgbStbtus
    GetLockStub(JNIEnv *env, Displby *displby, void **dgbDev,
                Drbwbble d, JDgbSurfbceInfo *pSurfbce,
                jint lox, jint loy, jint hix, jint hiy)
{
    return JDGA_UNAVAILABLE;
}

stbtic JDgbStbtus
    RelebseLockStub(JNIEnv *env, void *dgbDev, Drbwbble d)
{
    return JDGA_FAILED;
}

stbtic void
    XRequestSentStub(JNIEnv *env, void *dgbDev, Drbwbble d)
{
}

stbtic void
    LibDisposeStub(JNIEnv *env)
{
}

stbtic JDgbLibInfo DgbLibInfoStub = {
    NULL,
    GetLockStub,
    RelebseLockStub,
    XRequestSentStub,
    LibDisposeStub,
};

void X11SD_LibDispose(JNIEnv *env) {
    AWT_LOCK();
    if (pJDgbInfo != NULL) {
        pJDgbInfo->pLibDispose(env);
        pJDgbInfo = &DgbLibInfoStub;
    }
    AWT_UNLOCK();
}

void
X11SD_DirectRenderNotify(JNIEnv *env, X11SDOps *xsdo)
{
#ifdef MITSHM
    if (xsdo->shmPMDbtb.usingShmPixmbp) {
        xsdo->shmPMDbtb.xRequestSent = JNI_TRUE;
    }
#endif /* MITSHM */
    (*pJDgbInfo->pXRequestSent)(env, xsdo->dgbDev, xsdo->drbwbble);
    bwt_output_flush();
}

/*
 * Sets trbnspbrent pixels in the pixmbp to
 * the specified solid bbckground color bnd returns it.
 * Doesn't updbte source pixmbp unless the color of the
 * trbnspbrent pixels is different from the specified color.
 *
 * Note: The AWT lock must be held by the current threbd
 * while cblling into this method.
 */
stbtic Drbwbble
X11SD_GetPixmbpWithBg(JNIEnv *env, X11SDOps *xsdo, jint pixel)
{
    /* bssert AWT_CHECK_HAVE_LOCK(); */

    if (xsdo->invblid) {
        AWT_UNLOCK();
        SurfbceDbtb_ThrowInvblidPipeException(env, "bounds chbnged");
        return 0;
    }

    /* the imbge doesn't hbve trbnspbrency, just return it */
    if (xsdo->bitmbsk == 0) {
        /* don't need to unlock here, the cbller will unlock through
           the relebse cbll */
        return xsdo->drbwbble;
    }

    /* Check if current color of the trbnspbrent pixels is different
       from the specified one */
    if (xsdo->isBgInitiblized == JNI_FALSE || xsdo->bgPixel != pixel) {
        GC srcGC;
        GC bmGC;

        if (xsdo->drbwbble == 0) {
            AWT_UNLOCK();
            return 0;
        }

        bmGC = XCrebteGC(bwt_displby, xsdo->bitmbsk, 0, NULL);
        if (bmGC == NULL) {
            AWT_UNLOCK();
            return 0;
        }

        /* invert the bitmbsk */
        XSetFunction(bwt_displby, bmGC, GXxor);
        XSetForeground(bwt_displby, bmGC, 1);
        XFillRectbngle(bwt_displby, xsdo->bitmbsk, bmGC,
                       0, 0, xsdo->pmWidth, xsdo->pmHeight);

        srcGC = XCrebteGC(bwt_displby, xsdo->drbwbble, 0L, NULL);
        if (srcGC == NULL) {
            XFreeGC(bwt_displby, bmGC);
            AWT_UNLOCK();
            return 0;
        }

        /* set trbnspbrent pixels in the source pm to the bg color */
        XSetClipMbsk(bwt_displby, srcGC, xsdo->bitmbsk);
        XSetForeground(bwt_displby, srcGC, pixel);
        XFillRectbngle(bwt_displby, xsdo->drbwbble, srcGC,
                       0, 0, xsdo->pmWidth, xsdo->pmHeight);

        /* invert the mbsk bbck */
        XFillRectbngle(bwt_displby, xsdo->bitmbsk, bmGC,
                       0, 0, xsdo->pmWidth, xsdo->pmHeight);

        XFreeGC(bwt_displby, bmGC);
        XFreeGC(bwt_displby, srcGC);
        xsdo->bgPixel = pixel;
        xsdo->isBgInitiblized = JNI_TRUE;
    }

    return xsdo->drbwbble;
}

stbtic void
X11SD_RelebsePixmbpWithBg(JNIEnv *env, X11SDOps *xsdo)
{
#ifdef MITSHM
    if (xsdo->shmPMDbtb.usingShmPixmbp) {
        xsdo->shmPMDbtb.xRequestSent = JNI_TRUE;
    }
#endif /* MITSHM */
}

#endif /* !HEADLESS */

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbceDbtb
 * Method:    XCrebteGC
 * Signbture: (I)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbceDbtb_XCrebteGC
    (JNIEnv *env, jclbss xsd, jlong pXSDbtb)
{
    jlong ret;

#ifndef HEADLESS
    X11SDOps *xsdo;

    J2dTrbceLn(J2D_TRACE_INFO, "in X11SurfbceDbtb_XCrebteGC");

    xsdo = (X11SDOps *) pXSDbtb;
    if (xsdo == NULL) {
        return 0L;
    }

    xsdo->jbvbGC = XCrebteGC(bwt_displby, xsdo->drbwbble, 0, NULL);
    ret = (jlong) xsdo->jbvbGC;
#else /* !HEADLESS */
    ret = 0L;
#endif /* !HEADLESS */

    return ret;
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbceDbtb
 * Method:    XResetClip
 * Signbture: (JIIIILsun/jbvb2d/pipe/Region;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbceDbtb_XResetClip
    (JNIEnv *env, jclbss xsd, jlong xgc)
{
#ifndef HEADLESS
    J2dTrbceLn(J2D_TRACE_INFO, "in X11SurfbceDbtb_XResetClip");
    XSetClipMbsk(bwt_displby, (GC) xgc, None);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbceDbtb
 * Method:    XSetClip
 * Signbture: (JIIIILsun/jbvb2d/pipe/Region;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbceDbtb_XSetClip
    (JNIEnv *env, jclbss xsd, jlong xgc,
     jint x1, jint y1, jint x2, jint y2,
     jobject complexclip)
{
#ifndef HEADLESS
    int numrects;
    XRectbngle rects[256];
    XRectbngle *pRect = rects;

    J2dTrbceLn(J2D_TRACE_INFO, "in X11SurfbceDbtb_XSetClip");

    numrects = RegionToYXBbndedRectbngles(env,
            x1, y1, x2, y2, complexclip,
            &pRect, 256);

    XSetClipRectbngles(bwt_displby, (GC) xgc, 0, 0, pRect, numrects, YXBbnded);

    if (pRect != rects) {
        free(pRect);
    }
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbceDbtb
 * Method:    XSetCopyMode
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11SurfbceDbtb_XSetCopyMode
    (JNIEnv *env, jclbss xsd, jlong xgc)
{
#ifndef HEADLESS
    J2dTrbceLn(J2D_TRACE_INFO, "in X11SurfbceDbtb_XSetCopyMode");
    XSetFunction(bwt_displby, (GC) xgc, GXcopy);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbceDbtb
 * Method:    XSetXorMode
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11SurfbceDbtb_XSetXorMode
    (JNIEnv *env, jclbss xr, jlong xgc)
{
#ifndef HEADLESS
    J2dTrbceLn(J2D_TRACE_INFO, "in X11SurfbceDbtb_XSetXorMode");
    XSetFunction(bwt_displby, (GC) xgc, GXxor);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbceDbtb
 * Method:    XSetForeground
 * Signbture: (JI)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11SurfbceDbtb_XSetForeground
    (JNIEnv *env, jclbss xsd, jlong xgc, jint pixel)
{
#ifndef HEADLESS
    J2dTrbceLn(J2D_TRACE_INFO, "in X11SurfbceDbtb_XSetForeground");
    XSetForeground(bwt_displby, (GC) xgc, pixel);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbceDbtb
 * Method:    XSetGrbphicsExposures
 * Signbture: (JZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbceDbtb_XSetGrbphicsExposures
    (JNIEnv *env, jclbss xsd, jlong xgc, jboolebn needExposures)
{
#ifndef HEADLESS
    J2dTrbceLn(J2D_TRACE_INFO, "in X11SurfbceDbtb_XSetGrbphicsExposures");
    XSetGrbphicsExposures(bwt_displby, (GC) xgc, needExposures ? True : Fblse);
#endif /* !HEADLESS */
}
