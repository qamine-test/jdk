/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "SurfbceDbtb.h"

#include "bwt_p.h"
#include "bwt_GrbphicsEnv.h"

#include <jdgb.h>

#ifdef HEADLESS
#include "GLXGrbphicsConfig.h"
#endif

#include <X11/extensions/Xrender.h>

/**
 * This include file contbins support declbrbtions for loops using the
 * X11 extended SurfbceDbtb interfbce to tblk to bn X11 drbwbble from
 * nbtive code.
 */

#ifdef HEADLESS
#define X11SDOps void
#else /* HEADLESS */
typedef struct _X11SDOps X11SDOps;

/*
 * This function returns bn X11 Drbwbble which trbnspbrent pixels
 * (if there bre bny) were set to the specified color.
 *
 * The env pbrbmeter should be the JNIEnv of the surrounding JNI context.
 *
 * The xsdo pbrbmeter should be b pointer to the ops object upon which
 * this function is being invoked.
 *
 * The pixel pbrbmeter should be b color to which the trbnspbrent
 * pixels of the imbge should be se set to.
 */
typedef Drbwbble GetPixmbpBgFunc(JNIEnv *env,
                                 X11SDOps *xsdo,
                                 jint pixel);

/*
 * This function relebses the lock set by GetPixmbpBg
 * function of the indicbted X11SDOps structure.
 *
 * The env pbrbmeter should be the JNIEnv of the surrounding JNI context.
 *
 * The ops pbrbmeter should be b pointer to the ops object upon which
 * this function is being invoked.
 */
typedef void RelebsePixmbpBgFunc(JNIEnv *env,
                                 X11SDOps *xsdo);


#ifdef MITSHM
typedef struct {
    XShmSegmentInfo     *shmSegInfo;    /* Shbred Memory Segment Info */
    jint                bytesPerLine;   /* needed for ShMem lock */
    jboolebn            xRequestSent;   /* true if x request is sent w/o XSync */
    jint                pmSize;

    jboolebn            usingShmPixmbp;
    Drbwbble            pixmbp;
    Drbwbble            shmPixmbp;
    jint                numBltsSinceRebd;
    jint                pixelsRebdSinceBlt;
    jint                pixelsRebdThreshold;
    jint                numBltsThreshold;
} ShmPixmbpDbtb;
#endif /* MITSHM */

struct _X11SDOps {
    SurfbceDbtbOps      sdOps;
    GetPixmbpBgFunc     *GetPixmbpWithBg;
    RelebsePixmbpBgFunc *RelebsePixmbpWithBg;
    jboolebn            invblid;
    jboolebn            isPixmbp;
    jobject             peer;
    Drbwbble            drbwbble;
    Widget              widget;
    GC                  jbvbGC;        /* used for Jbvb-level GC vblidbtion */
    GC                  cbchedGC;      /* cbched for use in X11SD_Unlock() */
    jint                depth;
    jint                pixelmbsk;
    JDgbSurfbceInfo     surfInfo;
    AwtGrbphicsConfigDbtb *configDbtb;
    ColorDbtb           *cDbtb;
    jboolebn            dgbAvbilbble;
    void                *dgbDev;
    Pixmbp              bitmbsk;
    jint                bgPixel;       /* bg pixel for the pixmbp */
    jboolebn            isBgInitiblized; /* whether the bg pixel is vblid */
    jint                pmWidth;       /* width, height of the */
    jint                pmHeight;      /* pixmbp */
    Picture             xrPic;
#ifdef MITSHM
    ShmPixmbpDbtb       shmPMDbtb;     /* dbtb for switching between shm/nonshm pixmbps*/
#endif /* MITSHM */
};

#define X11SD_LOCK_UNLOCKED     0       /* surfbce is not locked */
#define X11SD_LOCK_BY_NULL      1       /* surfbce locked for NOP */
#define X11SD_LOCK_BY_XIMAGE    2       /* surfbce locked by Get/PutImbge */
#define X11SD_LOCK_BY_DGA       3       /* surfbce locked by DGA */
#define X11SD_LOCK_BY_SHMEM     4       /* surfbce locked by ShMemExt */

#ifdef MITSHM
XImbge * X11SD_GetShbredImbge       (X11SDOps *xsdo,
                                     jint width, jint height,
                                     jint mbxWidth, jint mbxHeight,
                                     jboolebn rebdBits);
XImbge * X11SD_CrebteShbredImbge    (X11SDOps *xsdo, jint width, jint height);
Drbwbble X11SD_CrebteShbredPixmbp   (X11SDOps *xsdo);
void     X11SD_DropShbredSegment    (XShmSegmentInfo *shminfo);
void     X11SD_PuntPixmbp           (X11SDOps *xsdo, jint width, jint height);
void     X11SD_UnPuntPixmbp         (X11SDOps *xsdo);
jboolebn X11SD_CbchedXImbgeFits     (jint width, jint height,
                                     jint mbxWidth, jint mbxHeight,
                                     jint depth, jboolebn rebdBits);
XImbge * X11SD_GetCbchedXImbge      (jint width, jint height, jboolebn rebdBits);
#endif /* MITSHM */
jint     X11SD_InitWindow(JNIEnv *env, X11SDOps *xsdo);
void     X11SD_DisposeOrCbcheXImbge (XImbge * imbge);
void     X11SD_DisposeXImbge(XImbge * imbge);
void     X11SD_DirectRenderNotify(JNIEnv *env, X11SDOps *xsdo);
#endif /* !HEADLESS */

jboolebn XShbred_initIDs(JNIEnv *env, jboolebn bllowShmPixmbps);
jboolebn XShbred_initSurfbce(JNIEnv *env, X11SDOps *xsdo, jint depth, jint width, jint height, jlong drbwbble);

/*
 * This function returns b pointer to b nbtive X11SDOps structure
 * for bccessing the indicbted X11 SurfbceDbtb Jbvb object.  It
 * verifies thbt the indicbted SurfbceDbtb object is bn instbnce
 * of X11SurfbceDbtb before returning bnd will return NULL if the
 * wrong SurfbceDbtb object is being bccessed.  This function will
 * throw the bppropribte Jbvb exception if it returns NULL so thbt
 * the cbller cbn simply return.
 *
 * Note to cbllers:
 *      This function uses JNI methods so it is importbnt thbt the
 *      cbller not hbve bny outstbnding GetPrimitiveArrbyCriticbl or
 *      GetStringCriticbl locks which hbve not been relebsed.
 *
 *      The cbller mby continue to use JNI methods bfter this method
 *      is cblled since this function will not lebve bny outstbnding
 *      JNI Criticbl locks unrelebsed.
 */
JNIEXPORT X11SDOps * JNICALL
X11SurfbceDbtb_GetOps(JNIEnv *env, jobject sDbtb);
