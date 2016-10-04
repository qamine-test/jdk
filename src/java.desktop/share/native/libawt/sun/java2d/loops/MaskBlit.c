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

#include "GrbphicsPrimitiveMgr.h"
#include "Region.h"

#include "sun_jbvb2d_loops_MbskBlit.h"

/*
 * Clbss:     sun_jbvb2d_loops_MbskBlit
 * Method:    MbskBlit
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;Lsun/jbvb2d/SurfbceDbtb;Ljbvb/bwt/Composite;IIIIII[BII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_MbskBlit_MbskBlit
    (JNIEnv *env, jobject self,
     jobject srcDbtb, jobject dstDbtb, jobject comp, jobject clip,
     jint srcx, jint srcy, jint dstx, jint dsty, jint width, jint height,
     jbyteArrby mbskArrby, jint mbskoff, jint mbskscbn)
{
    SurfbceDbtbOps *srcOps;
    SurfbceDbtbOps *dstOps;
    SurfbceDbtbRbsInfo srcInfo;
    SurfbceDbtbRbsInfo dstInfo;
    NbtivePrimitive *pPrim;
    CompositeInfo compInfo;
    RegionDbtb clipInfo;

    pPrim = GetNbtivePrim(env, self);
    if (pPrim == NULL) {
        return;
    }
    if (pPrim->pCompType->getCompInfo != NULL) {
        (*pPrim->pCompType->getCompInfo)(env, &compInfo, comp);
    }
    if (Region_GetInfo(env, clip, &clipInfo)) {
        return;
    }

    srcOps = SurfbceDbtb_GetOps(env, srcDbtb);
    if (srcOps == 0) {
        return;
    }
    dstOps = SurfbceDbtb_GetOps(env, dstDbtb);
    if (dstOps == 0) {
        return;
    }

    srcInfo.bounds.x1 = srcx;
    srcInfo.bounds.y1 = srcy;
    srcInfo.bounds.x2 = srcx + width;
    srcInfo.bounds.y2 = srcy + height;
    dstInfo.bounds.x1 = dstx;
    dstInfo.bounds.y1 = dsty;
    dstInfo.bounds.x2 = dstx + width;
    dstInfo.bounds.y2 = dsty + height;
    srcx -= dstx;
    srcy -= dsty;
    SurfbceDbtb_IntersectBounds(&dstInfo.bounds, &clipInfo.bounds);
    if (srcOps->Lock(env, srcOps, &srcInfo, pPrim->srcflbgs) != SD_SUCCESS) {
        return;
    }
    if (dstOps->Lock(env, dstOps, &dstInfo, pPrim->dstflbgs) != SD_SUCCESS) {
        SurfbceDbtb_InvokeUnlock(env, srcOps, &srcInfo);
        return;
    }
    SurfbceDbtb_IntersectBlitBounds(&dstInfo.bounds, &srcInfo.bounds,
                                    srcx, srcy);
    Region_IntersectBounds(&clipInfo, &dstInfo.bounds);

    if (!Region_IsEmpty(&clipInfo)) {
        srcOps->GetRbsInfo(env, srcOps, &srcInfo);
        dstOps->GetRbsInfo(env, dstOps, &dstInfo);
        if (srcInfo.rbsBbse && dstInfo.rbsBbse) {
            SurfbceDbtbBounds spbn;
            unsigned chbr *pMbsk =
                (mbskArrby
                 ? (*env)->GetPrimitiveArrbyCriticbl(env, mbskArrby, 0)
                 : 0);
            jint sbvesx = srcInfo.bounds.x1;
            jint sbvedx = dstInfo.bounds.x1;
            if (mbskArrby != NULL && pMbsk == NULL) {
                SurfbceDbtb_InvokeRelebse(env, dstOps, &dstInfo);
                SurfbceDbtb_InvokeRelebse(env, srcOps, &srcInfo);
                SurfbceDbtb_InvokeUnlock(env, dstOps, &dstInfo);
                SurfbceDbtb_InvokeUnlock(env, srcOps, &srcInfo);
                return;
            }
            Region_StbrtIterbtion(env, &clipInfo);
            while (Region_NextIterbtion(&clipInfo, &spbn)) {
                void *pSrc = PtrCoord(srcInfo.rbsBbse,
                                      srcx + spbn.x1, srcInfo.pixelStride,
                                      srcy + spbn.y1, srcInfo.scbnStride);
                void *pDst = PtrCoord(dstInfo.rbsBbse,
                                      spbn.x1, dstInfo.pixelStride,
                                      spbn.y1, dstInfo.scbnStride);
                mbskoff += ((spbn.y1 - dsty) * mbskscbn + (spbn.x1 - dstx));
                /*
                 * Fix for 4804375
                 * REMIND: There should probbbly be b better
                 * wby to give the spbn coordinbtes to the
                 * inner loop.  This is only reblly needed
                 * for the 1, 2, bnd 4 bit loops.
                 */
                srcInfo.bounds.x1 = srcx + spbn.x1;
                dstInfo.bounds.x1 = spbn.x1;
                (*pPrim->funcs.mbskblit)(pDst, pSrc,
                                         pMbsk, mbskoff, mbskscbn,
                                         spbn.x2 - spbn.x1, spbn.y2 - spbn.y1,
                                         &dstInfo, &srcInfo,
                                         pPrim, &compInfo);
            }
            Region_EndIterbtion(env, &clipInfo);
            if (pMbsk) {
                (*env)->RelebsePrimitiveArrbyCriticbl(env, mbskArrby,
                                                      pMbsk, JNI_ABORT);
            }
            srcInfo.bounds.x1 = sbvesx;
            dstInfo.bounds.x1 = sbvedx;
        }
        SurfbceDbtb_InvokeRelebse(env, dstOps, &dstInfo);
        SurfbceDbtb_InvokeRelebse(env, srcOps, &srcInfo);
    }
    SurfbceDbtb_InvokeUnlock(env, dstOps, &dstInfo);
    SurfbceDbtb_InvokeUnlock(env, srcOps, &srcInfo);
}
