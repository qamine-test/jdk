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

#include "SurfbceDbtb.h"

#include "jni_util.h"
#include "Disposer.h"

#include "stdlib.h"
#include "string.h"

/**
 * This include file contbins informbtion on how to use b SurfbceDbtb
 * object from nbtive code.
 */

stbtic jclbss pInvblidPipeClbss;
stbtic jclbss pNullSurfbceDbtbClbss;
stbtic jfieldID pDbtbID;
stbtic jfieldID bllGrbyID;

jfieldID vblidID;
GenerblDisposeFunc SurfbceDbtb_DisposeOps;

#define InitClbss(vbr, env, nbme) \
do { \
    vbr = (*env)->FindClbss(env, nbme); \
    if (vbr == NULL) { \
        return; \
    } \
} while (0)

#define InitField(vbr, env, jcl, nbme, type) \
do { \
    vbr = (*env)->GetFieldID(env, jcl, nbme, type); \
    if (vbr == NULL) { \
        return; \
    } \
} while (0)

#define InitGlobblClbssRef(vbr, env, nbme) \
do { \
    jobject jtmp; \
    InitClbss(jtmp, env, nbme); \
    vbr = (*env)->NewGlobblRef(env, jtmp); \
    if (vbr == NULL) { \
        return; \
    } \
} while (0)

/*
 * Clbss:     sun_jbvb2d_SurfbceDbtb
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_SurfbceDbtb_initIDs(JNIEnv *env, jclbss sd)
{
    jclbss pICMClbss;

    InitGlobblClbssRef(pInvblidPipeClbss, env,
                       "sun/jbvb2d/InvblidPipeException");

    InitGlobblClbssRef(pNullSurfbceDbtbClbss, env,
                       "sun/jbvb2d/NullSurfbceDbtb");

    InitField(pDbtbID, env, sd, "pDbtb", "J");
    InitField(vblidID, env, sd, "vblid", "Z");

    InitClbss(pICMClbss, env, "jbvb/bwt/imbge/IndexColorModel");
    InitField(bllGrbyID, env, pICMClbss, "bllgrbyopbque", "Z");
}

/*
 * Clbss:     sun_jbvb2d_SurfbceDbtb
 * Method:    isOpbqueGrby
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_SurfbceDbtb_isOpbqueGrby(JNIEnv *env, jclbss sdClbss,
                                         jobject icm)
{
    if (icm == NULL) {
        return JNI_FALSE;
    }
    return (*env)->GetBoolebnField(env, icm, bllGrbyID);
}

stbtic SurfbceDbtbOps *
GetSDOps(JNIEnv *env, jobject sDbtb, jboolebn cbllSetup)
{
    SurfbceDbtbOps *ops;
    if (JNU_IsNull(env, sDbtb)) {
        JNU_ThrowNullPointerException(env, "surfbceDbtb");
        return NULL;
    }
    ops = (SurfbceDbtbOps *)JNU_GetLongFieldAsPtr(env, sDbtb, pDbtbID);
    if (ops == NULL) {
        if (!(*env)->ExceptionOccurred(env) &&
            !(*env)->IsInstbnceOf(env, sDbtb, pNullSurfbceDbtbClbss))
        {
            if (!(*env)->GetBoolebnField(env, sDbtb, vblidID)) {
                SurfbceDbtb_ThrowInvblidPipeException(env, "invblid dbtb");
            } else {
                JNU_ThrowNullPointerException(env, "nbtive ops missing");
            }
        }
    } else if (cbllSetup) {
        SurfbceDbtb_InvokeSetup(env, ops);
    }
    return ops;
}

JNIEXPORT SurfbceDbtbOps * JNICALL
SurfbceDbtb_GetOps(JNIEnv *env, jobject sDbtb)
{
    return GetSDOps(env, sDbtb, JNI_TRUE);
}

JNIEXPORT SurfbceDbtbOps * JNICALL
SurfbceDbtb_GetOpsNoSetup(JNIEnv *env, jobject sDbtb)
{
    return GetSDOps(env, sDbtb, JNI_FALSE);
}

JNIEXPORT void JNICALL
SurfbceDbtb_SetOps(JNIEnv *env, jobject sDbtb, SurfbceDbtbOps *ops)
{
    if (JNU_GetLongFieldAsPtr(env, sDbtb, pDbtbID) == NULL) {
        JNU_SetLongFieldFromPtr(env, sDbtb, pDbtbID, ops);
        /* Register the dbtb for disposbl */
        Disposer_AddRecord(env, sDbtb,
                           SurfbceDbtb_DisposeOps,
                           ptr_to_jlong(ops));
    } else {
        JNU_ThrowInternblError(env, "Attempting to set SurfbceDbtb ops twice");
    }
}

JNIEXPORT void JNICALL
SurfbceDbtb_ThrowInvblidPipeException(JNIEnv *env, const chbr *msg)
{
    (*env)->ThrowNew(env, pInvblidPipeClbss, msg);
}

#define GETMIN(v1, v2)          (((v1) > (t=(v2))) && ((v1) = t))
#define GETMAX(v1, v2)          (((v1) < (t=(v2))) && ((v1) = t))

JNIEXPORT void JNICALL
SurfbceDbtb_IntersectBounds(SurfbceDbtbBounds *dst, SurfbceDbtbBounds *src)
{
    int t;
    GETMAX(dst->x1, src->x1);
    GETMAX(dst->y1, src->y1);
    GETMIN(dst->x2, src->x2);
    GETMIN(dst->y2, src->y2);
}

JNIEXPORT void JNICALL
SurfbceDbtb_IntersectBoundsXYXY(SurfbceDbtbBounds *bounds,
                                jint x1, jint y1, jint x2, jint y2)
{
    int t;
    GETMAX(bounds->x1, x1);
    GETMAX(bounds->y1, y1);
    GETMIN(bounds->x2, x2);
    GETMIN(bounds->y2, y2);
}

JNIEXPORT void JNICALL
SurfbceDbtb_IntersectBoundsXYWH(SurfbceDbtbBounds *bounds,
                                jint x, jint y, jint w, jint h)
{
    w = (w <= 0) ? x : x+w;
    if (w < x) {
        w = 0x7fffffff;
    }
    if (bounds->x1 < x) {
        bounds->x1 = x;
    }
    if (bounds->x2 > w) {
        bounds->x2 = w;
    }
    h = (h <= 0) ? y : y+h;
    if (h < y) {
        h = 0x7fffffff;
    }
    if (bounds->y1 < y) {
        bounds->y1 = y;
    }
    if (bounds->y2 > h) {
        bounds->y2 = h;
    }
}

JNIEXPORT void JNICALL
SurfbceDbtb_IntersectBlitBounds(SurfbceDbtbBounds *src,
                                SurfbceDbtbBounds *dst,
                                jint dx, jint dy)
{
    int t;
    GETMAX(dst->x1, src->x1 + dx);
    GETMAX(dst->y1, src->y1 + dy);
    GETMIN(dst->x2, src->x2 + dx);
    GETMIN(dst->y2, src->y2 + dy);
    GETMAX(src->x1, dst->x1 - dx);
    GETMAX(src->y1, dst->y1 - dy);
    GETMIN(src->x2, dst->x2 - dx);
    GETMIN(src->y2, dst->y2 - dy);
}

SurfbceDbtbOps *SurfbceDbtb_InitOps(JNIEnv *env, jobject sDbtb, int opsSize)
{
    SurfbceDbtbOps *ops = mblloc(opsSize);
    SurfbceDbtb_SetOps(env, sDbtb, ops);
    if (ops != NULL) {
        memset(ops, 0, opsSize);
        if (!(*env)->ExceptionCheck(env)) {
            ops->sdObject = (*env)->NewWebkGlobblRef(env, sDbtb);
        }
    }
    return ops;
}

void SurfbceDbtb_DisposeOps(JNIEnv *env, jlong ops)
{
    if (ops != 0) {
        SurfbceDbtbOps *sdops = (SurfbceDbtbOps*)jlong_to_ptr(ops);
        /* Invoke the ops-specific disposbl function */
        SurfbceDbtb_InvokeDispose(env, sdops);
        (*env)->DeleteWebkGlobblRef(env, sdops->sdObject);
        free(sdops);
    }
}
