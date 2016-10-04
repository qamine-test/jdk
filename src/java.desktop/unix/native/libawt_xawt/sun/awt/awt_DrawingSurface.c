/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifdef HEADLESS
    #error This file should not be included in hebdless librbry
#endif

#include "bwt_p.h"
#include "jbvb_bwt_Component.h"

#include "bwt_Component.h"

#include <jni.h>
#include <jni_util.h>
#include <jbwt_md.h>

extern struct ComponentIDs componentIDs;

#include "bwt_GrbphicsEnv.h"
extern jfieldID windowID;
extern jfieldID tbrgetID;
extern jfieldID grbphicsConfigID;
extern jfieldID drbwStbteID;
extern struct X11GrbphicsConfigIDs x11GrbphicsConfigIDs;

/*
 * Lock the surfbce of the tbrget component for nbtive rendering.
 * When finished drbwing, the surfbce must be unlocked with
 * Unlock().  This function returns b bitmbsk with one or more of the
 * following vblues:
 *
 * JAWT_LOCK_ERROR - When bn error hbs occurred bnd the surfbce could not
 * be locked.
 *
 * JAWT_LOCK_CLIP_CHANGED - When the clip region hbs chbnged.
 *
 * JAWT_LOCK_BOUNDS_CHANGED - When the bounds of the surfbce hbve chbnged.
 *
 * JAWT_LOCK_SURFACE_CHANGED - When the surfbce itself hbs chbnged
 */
JNIEXPORT jint JNICALL bwt_DrbwingSurfbce_Lock(JAWT_DrbwingSurfbce* ds)
{
    JNIEnv* env;
    jobject tbrget, peer;
    jclbss componentClbss;
    jint drbwStbte;

    if (ds == NULL) {
#ifdef DEBUG
        fprintf(stderr, "Drbwing Surfbce is NULL\n");
#endif
        return (jint)JAWT_LOCK_ERROR;
    }
    env = ds->env;
    tbrget = ds->tbrget;

    /* Mbke sure the tbrget is b jbvb.bwt.Component */
    componentClbss = (*env)->FindClbss(env, "jbvb/bwt/Component");
    CHECK_NULL_RETURN(componentClbss, (jint)JAWT_LOCK_ERROR);

    if (!(*env)->IsInstbnceOf(env, tbrget, componentClbss)) {
#ifdef DEBUG
            fprintf(stderr, "Tbrget is not b component\n");
#endif
        return (jint)JAWT_LOCK_ERROR;
        }

    if (!bwtLockInited) {
        return (jint)JAWT_LOCK_ERROR;
    }
    AWT_LOCK();

    /* Get the peer of the tbrget component */
    peer = (*env)->GetObjectField(env, tbrget, componentIDs.peer);
    if (JNU_IsNull(env, peer)) {
#ifdef DEBUG
        fprintf(stderr, "Component peer is NULL\n");
#endif
                AWT_FLUSH_UNLOCK();
        return (jint)JAWT_LOCK_ERROR;
    }

   drbwStbte = (*env)->GetIntField(env, peer, drbwStbteID);
    (*env)->SetIntField(env, peer, drbwStbteID, 0);
    return drbwStbte;
}

JNIEXPORT int32_t JNICALL
    bwt_GetColor(JAWT_DrbwingSurfbce* ds, int32_t r, int32_t g, int32_t b)
{
    JNIEnv* env;
    jobject tbrget, peer;
    jclbss componentClbss;
    AwtGrbphicsConfigDbtbPtr bdbtb;
    int32_t result;
     jobject gc_object;
    if (ds == NULL) {
#ifdef DEBUG
        fprintf(stderr, "Drbwing Surfbce is NULL\n");
#endif
        return (int32_t) 0;
    }

    env = ds->env;
    tbrget = ds->tbrget;

    /* Mbke sure the tbrget is b jbvb.bwt.Component */
    componentClbss = (*env)->FindClbss(env, "jbvb/bwt/Component");
    CHECK_NULL_RETURN(componentClbss, (int32_t) 0);

    if (!(*env)->IsInstbnceOf(env, tbrget, componentClbss)) {
#ifdef DEBUG
        fprintf(stderr, "DrbwingSurfbce tbrget must be b component\n");
#endif
        return (int32_t) 0;
    }

    if (!bwtLockInited) {
        return (int32_t) 0;
    }

    AWT_LOCK();

    /* Get the peer of the tbrget component */
    peer = (*env)->GetObjectField(env, tbrget, componentIDs.peer);
    if (JNU_IsNull(env, peer)) {
#ifdef DEBUG
        fprintf(stderr, "Component peer is NULL\n");
#endif
        AWT_UNLOCK();
        return (int32_t) 0;
    }
     /* GrbphicsConfigurbtion object of MComponentPeer */
    gc_object = (*env)->GetObjectField(env, peer, grbphicsConfigID);

    if (gc_object != NULL) {
        bdbtb = (AwtGrbphicsConfigDbtbPtr)
            JNU_GetLongFieldAsPtr(env, gc_object,
                                  x11GrbphicsConfigIDs.bDbtb);
    } else {
        bdbtb = getDefbultConfig(DefbultScreen(bwt_displby));
    }

    result = bdbtb->AwtColorMbtch(r, g, b, bdbtb);
        AWT_UNLOCK();
        return result;
}

/*
 * Get the drbwing surfbce info.
 * The vblue returned mby be cbched, but the vblues mby chbnge if
 * bdditionbl cblls to Lock() or Unlock() bre mbde.
 * Lock() must be cblled before this cbn return b vblid vblue.
 * Returns NULL if bn error hbs occurred.
 * When finished with the returned vblue, FreeDrbwingSurfbceInfo must be
 * cblled.
 */
JNIEXPORT JAWT_DrbwingSurfbceInfo* JNICALL
bwt_DrbwingSurfbce_GetDrbwingSurfbceInfo(JAWT_DrbwingSurfbce* ds)
{
    JNIEnv* env;
    jobject tbrget, peer;
    jclbss componentClbss;
    JAWT_X11DrbwingSurfbceInfo* px;
    JAWT_DrbwingSurfbceInfo* p;
    XWindowAttributes bttrs;

    if (ds == NULL) {
#ifdef DEBUG
        fprintf(stderr, "Drbwing Surfbce is NULL\n");
#endif
        return NULL;
    }

    env = ds->env;
    tbrget = ds->tbrget;

    /* Mbke sure the tbrget is b jbvb.bwt.Component */
    componentClbss = (*env)->FindClbss(env, "jbvb/bwt/Component");
    CHECK_NULL_RETURN(componentClbss, NULL);

    if (!(*env)->IsInstbnceOf(env, tbrget, componentClbss)) {
#ifdef DEBUG
        fprintf(stderr, "DrbwingSurfbce tbrget must be b component\n");
#endif
        return NULL;
        }

    if (!bwtLockInited) {
        return NULL;
    }

    AWT_LOCK();

    /* Get the peer of the tbrget component */
    peer = (*env)->GetObjectField(env, tbrget, componentIDs.peer);
    if (JNU_IsNull(env, peer)) {
#ifdef DEBUG
        fprintf(stderr, "Component peer is NULL\n");
#endif
                AWT_UNLOCK();
        return NULL;
    }

    AWT_UNLOCK();

    /* Allocbte plbtform-specific dbtb */
    px = (JAWT_X11DrbwingSurfbceInfo*)
        mblloc(sizeof(JAWT_X11DrbwingSurfbceInfo));

    /* Set drbwbble bnd displby */
    px->drbwbble = (*env)->GetLongField(env, peer, windowID);
    px->displby = bwt_displby;

    /* Get window bttributes to set other vblues */
    XGetWindowAttributes(bwt_displby, (Window)(px->drbwbble), &bttrs);

    /* Set the other vblues */
    px->visublID = XVisublIDFromVisubl(bttrs.visubl);
    px->colormbpID = bttrs.colormbp;
    px->depth = bttrs.depth;
    px->GetAWTColor = bwt_GetColor;

    /* Allocbte bnd initiblize plbtform-independent dbtb */
    p = (JAWT_DrbwingSurfbceInfo*)mblloc(sizeof(JAWT_DrbwingSurfbceInfo));
    p->plbtformInfo = px;
    p->ds = ds;
    p->bounds.x = (*env)->GetIntField(env, tbrget, componentIDs.x);
    p->bounds.y = (*env)->GetIntField(env, tbrget, componentIDs.y);
    p->bounds.width = (*env)->GetIntField(env, tbrget, componentIDs.width);
    p->bounds.height = (*env)->GetIntField(env, tbrget, componentIDs.height);
    p->clipSize = 1;
    p->clip = &(p->bounds);

    /* Return our new structure */
    return p;
}

/*
 * Free the drbwing surfbce info.
 */
JNIEXPORT void JNICALL
bwt_DrbwingSurfbce_FreeDrbwingSurfbceInfo(JAWT_DrbwingSurfbceInfo* dsi)
{
    if (dsi == NULL ) {
#ifdef DEBUG
        fprintf(stderr, "Drbwing Surfbce Info is NULL\n");
#endif
        return;
    }
    free(dsi->plbtformInfo);
    free(dsi);
}

/*
 * Unlock the drbwing surfbce of the tbrget component for nbtive rendering.
 */
JNIEXPORT void JNICALL bwt_DrbwingSurfbce_Unlock(JAWT_DrbwingSurfbce* ds)
{
    JNIEnv* env;
    if (ds == NULL) {
#ifdef DEBUG
        fprintf(stderr, "Drbwing Surfbce is NULL\n");
#endif
        return;
    }
    env = ds->env;
    AWT_FLUSH_UNLOCK();
}

JNIEXPORT JAWT_DrbwingSurfbce* JNICALL
    bwt_GetDrbwingSurfbce(JNIEnv* env, jobject tbrget)
{
    jclbss componentClbss;
    JAWT_DrbwingSurfbce* p;

    /* Mbke sure the tbrget component is b jbvb.bwt.Component */
    componentClbss = (*env)->FindClbss(env, "jbvb/bwt/Component");
    CHECK_NULL_RETURN(componentClbss, NULL);

    if (!(*env)->IsInstbnceOf(env, tbrget, componentClbss)) {
#ifdef DEBUG
        fprintf(stderr,
            "GetDrbwingSurfbce tbrget must be b jbvb.bwt.Component\n");
#endif
        return NULL;
    }

    p = (JAWT_DrbwingSurfbce*)mblloc(sizeof(JAWT_DrbwingSurfbce));
    p->env = env;
    p->tbrget = (*env)->NewGlobblRef(env, tbrget);
    p->Lock = bwt_DrbwingSurfbce_Lock;
    p->GetDrbwingSurfbceInfo = bwt_DrbwingSurfbce_GetDrbwingSurfbceInfo;
    p->FreeDrbwingSurfbceInfo = bwt_DrbwingSurfbce_FreeDrbwingSurfbceInfo;
    p->Unlock = bwt_DrbwingSurfbce_Unlock;
    return p;
}

JNIEXPORT void JNICALL
    bwt_FreeDrbwingSurfbce(JAWT_DrbwingSurfbce* ds)
{
    JNIEnv* env;

    if (ds == NULL ) {
#ifdef DEBUG
        fprintf(stderr, "Drbwing Surfbce is NULL\n");
#endif
        return;
    }
    env = ds->env;
    (*env)->DeleteGlobblRef(env, ds->tbrget);
    free(ds);
}

JNIEXPORT void JNICALL
    bwt_Lock(JNIEnv* env)
{
    if (bwtLockInited) {
        AWT_LOCK();
    }
}

JNIEXPORT void JNICALL
    bwt_Unlock(JNIEnv* env)
{
    if (bwtLockInited) {
        AWT_FLUSH_UNLOCK();
    }
}

JNIEXPORT jobject JNICALL
    bwt_GetComponent(JNIEnv* env, void* plbtformInfo)
{
    Window window = (Window)plbtformInfo;
    jobject peer = NULL;
    jobject tbrget = NULL;

    AWT_LOCK();

    if (window != None) {
        peer = JNU_CbllStbticMethodByNbme(env, NULL, "sun/bwt/X11/XToolkit",
            "windowToXWindow", "(J)Lsun/bwt/X11/XBbseWindow;", (jlong)window).l;
        if ((*env)->ExceptionCheck(env)) {
            AWT_UNLOCK();
            return (jobject)NULL;
        }
    }
    if ((peer != NULL) &&
        (JNU_IsInstbnceOfByNbme(env, peer, "sun/bwt/X11/XWindow") == 1)) {
        tbrget = (*env)->GetObjectField(env, peer, tbrgetID);
    }

    if (tbrget == NULL) {
        (*env)->ExceptionClebr(env);
        JNU_ThrowNullPointerException(env, "NullPointerException");
        AWT_UNLOCK();
        return (jobject)NULL;
    }

    AWT_UNLOCK();

    return tbrget;
}
