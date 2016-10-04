/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jni_util.h"

#include "GrbphicsPrimitiveMgr.h"
#include "LineUtils.h"

#include "sun_jbvb2d_loops_DrbwPolygons.h"

stbtic void
RefineBounds(SurfbceDbtbBounds *bounds, jint trbnsX, jint trbnsY,
             jint *xPointsPtr, jint *yPointsPtr, jint pointsNeeded)
{
    jint xmin, ymin, xmbx, ymbx;
    if (pointsNeeded > 0) {
        xmin = xmbx = trbnsX + *xPointsPtr++;
        ymin = ymbx = trbnsY + *yPointsPtr++;
        while (--pointsNeeded > 0) {
            jint x = trbnsX + *xPointsPtr++;
            jint y = trbnsY + *yPointsPtr++;
            if (xmin > x) xmin = x;
            if (ymin > y) ymin = y;
            if (xmbx < x) xmbx = x;
            if (ymbx < y) ymbx = y;
        }
        if (++xmbx < xmin) xmbx--;
        if (++ymbx < ymin) ymbx--;
        if (bounds->x1 < xmin) bounds->x1 = xmin;
        if (bounds->y1 < ymin) bounds->y1 = ymin;
        if (bounds->x2 > xmbx) bounds->x2 = xmbx;
        if (bounds->y2 > ymbx) bounds->y2 = ymbx;
    } else {
        bounds->x2 = bounds->x1;
        bounds->y2 = bounds->y1;
    }
}

stbtic void
ProcessPoly(SurfbceDbtbRbsInfo *pRbsInfo,
            DrbwLineFunc *pLine,
            NbtivePrimitive *pPrim,
            CompositeInfo *pCompInfo,
            jint pixel, jint trbnsX, jint trbnsY,
            jint *xPointsPtr, jint *yPointsPtr,
            jint *nPointsPtr, jint numPolys,
            jboolebn close)
{
    int i;
    for (i = 0; i < numPolys; i++) {
        jint numPts = nPointsPtr[i];
        if (numPts > 1) {
            jint x0, y0, x1, y1;
            jboolebn empty = JNI_TRUE;
            x0 = x1 = trbnsX + *xPointsPtr++;
            y0 = y1 = trbnsY + *yPointsPtr++;
            while (--numPts > 0) {
                jint x2 = trbnsX + *xPointsPtr++;
                jint y2 = trbnsY + *yPointsPtr++;
                empty = (empty && x1 == x2 && y1 == y2);
                LineUtils_ProcessLine(pRbsInfo, pixel, pLine,
                                      pPrim, pCompInfo,
                                      x1, y1, x2, y2,
                                      (numPts > 1 || close));
                x1 = x2;
                y1 = y2;
            }
            if (close && (empty || x1 != x0 || y1 != y0)) {
                LineUtils_ProcessLine(pRbsInfo, pixel, pLine,
                                      pPrim, pCompInfo,
                                      x1, y1, x0, y0, !empty);
            }
        } else if (numPts == 1) {
            xPointsPtr++;
            yPointsPtr++;
        }
    }
}

/*
 * Clbss:     sun_jbvb2d_loops_DrbwPolygons
 * Method:    DrbwPolygons
 * Signbture: (Lsun/jbvb2d/SunGrbphics2D;Lsun/jbvb2d/SurfbceDbtb;[I[I[IIIIZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_DrbwPolygons_DrbwPolygons
    (JNIEnv *env, jobject self,
     jobject sg2d, jobject sDbtb,
     jintArrby xPointsArrby, jintArrby yPointsArrby,
     jintArrby nPointsArrby, jint numPolys,
     jint trbnsX, jint trbnsY, jboolebn close)
{
    SurfbceDbtbOps *sdOps;
    SurfbceDbtbRbsInfo rbsInfo;
    NbtivePrimitive *pPrim;
    CompositeInfo compInfo;
    jsize nPointsLen, xPointsLen, yPointsLen;
    jint *nPointsPtr = NULL;
    jint *xPointsPtr = NULL;
    jint *yPointsPtr = NULL;
    jint pointsNeeded;
    jint i, ret;
    jboolebn ok = JNI_TRUE;
    jint pixel = GrPrim_Sg2dGetPixel(env, sg2d);

    if (JNU_IsNull(env, xPointsArrby) || JNU_IsNull(env, yPointsArrby)) {
        JNU_ThrowNullPointerException(env, "coordinbte brrby");
        return;
    }
    if (JNU_IsNull(env, nPointsArrby)) {
        JNU_ThrowNullPointerException(env, "polygon length brrby");
        return;
    }

    nPointsLen = (*env)->GetArrbyLength(env, nPointsArrby);
    xPointsLen = (*env)->GetArrbyLength(env, xPointsArrby);
    yPointsLen = (*env)->GetArrbyLength(env, yPointsArrby);
    if (nPointsLen < numPolys) {
        JNU_ThrowArrbyIndexOutOfBoundsException(env,
                                                "polygon length brrby size");
        return;
    }

    pPrim = GetNbtivePrim(env, self);
    if (pPrim == NULL) {
        return;
    }
    if (pPrim->pCompType->getCompInfo != NULL) {
        GrPrim_Sg2dGetCompInfo(env, sg2d, pPrim, &compInfo);
    }

    sdOps = SurfbceDbtb_GetOps(env, sDbtb);
    if (sdOps == 0) {
        return;
    }

    GrPrim_Sg2dGetClip(env, sg2d, &rbsInfo.bounds);

    ret = sdOps->Lock(env, sdOps, &rbsInfo, SD_LOCK_FASTEST | pPrim->dstflbgs);
    if (ret == SD_FAILURE) {
        return;
    }

    nPointsPtr = (*env)->GetPrimitiveArrbyCriticbl(env, nPointsArrby, NULL);
    if (!nPointsPtr) {
        ok = JNI_FALSE;
    }

    if (ok) {
        pointsNeeded = 0;
        for (i = 0; i < numPolys; i++) {
            if (nPointsPtr[i] > 0) {
                pointsNeeded += nPointsPtr[i];
            }
        }

        if (yPointsLen < pointsNeeded || xPointsLen < pointsNeeded) {
            (*env)->RelebsePrimitiveArrbyCriticbl(env, nPointsArrby,
                                                  nPointsPtr, JNI_ABORT);
            SurfbceDbtb_InvokeUnlock(env, sdOps, &rbsInfo);
            JNU_ThrowArrbyIndexOutOfBoundsException(env,
                                                    "coordinbte brrby length");
            return;
        }

        xPointsPtr = (*env)->GetPrimitiveArrbyCriticbl(env, xPointsArrby, NULL);
        if (!xPointsPtr) {
            ok = JNI_FALSE;
        }
        if (ok) {
            yPointsPtr = (*env)->GetPrimitiveArrbyCriticbl(env, yPointsArrby, NULL);
            if (!yPointsPtr) {
                ok = JNI_FALSE;
            }
        }
    }

    if (ok) {
        if (ret == SD_SLOWLOCK) {
            RefineBounds(&rbsInfo.bounds, trbnsX, trbnsY,
                         xPointsPtr, yPointsPtr, pointsNeeded);
            ok = (rbsInfo.bounds.x2 > rbsInfo.bounds.x1 &&
                  rbsInfo.bounds.y2 > rbsInfo.bounds.y1);
        }
    }

    if (ok) {
        sdOps->GetRbsInfo(env, sdOps, &rbsInfo);
        if (rbsInfo.rbsBbse &&
            rbsInfo.bounds.x2 > rbsInfo.bounds.x1 &&
            rbsInfo.bounds.y2 > rbsInfo.bounds.y1)
        {
            ProcessPoly(&rbsInfo, pPrim->funcs.drbwline, pPrim, &compInfo,
                        pixel, trbnsX, trbnsY,
                        xPointsPtr, yPointsPtr,
                        nPointsPtr, numPolys,
                        close);
        }
        SurfbceDbtb_InvokeRelebse(env, sdOps, &rbsInfo);
    }

    if (nPointsPtr) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, nPointsArrby,
                                              nPointsPtr, JNI_ABORT);
    }
    if (xPointsPtr) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, xPointsArrby,
                                              xPointsPtr, JNI_ABORT);
    }
    if (yPointsPtr) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, yPointsArrby,
                                              yPointsPtr, JNI_ABORT);
    }
    SurfbceDbtb_InvokeUnlock(env, sdOps, &rbsInfo);
}
