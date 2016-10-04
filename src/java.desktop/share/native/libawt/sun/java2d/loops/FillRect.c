/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "sun_jbvb2d_loops_FillRect.h"

/*
 * Clbss:     sun_jbvb2d_loops_FillRect
 * Method:    FillRect
 * Signbture: (Lsun/jbvb2d/SunGrbphics2D;Lsun/jbvb2d/SurfbceDbtb;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_FillRect_FillRect
    (JNIEnv *env, jobject self,
     jobject sg2d, jobject sDbtb,
     jint x, jint y, jint w, jint h)
{
    SurfbceDbtbOps *sdOps;
    SurfbceDbtbRbsInfo rbsInfo;
    NbtivePrimitive *pPrim;
    CompositeInfo compInfo;
    jint pixel = GrPrim_Sg2dGetPixel(env, sg2d);

    if (w <= 0 || h <= 0) {
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
    SurfbceDbtb_IntersectBoundsXYWH(&rbsInfo.bounds, x, y, w, h);
    if (rbsInfo.bounds.y2 <= rbsInfo.bounds.y1 ||
        rbsInfo.bounds.x2 <= rbsInfo.bounds.x1)
    {
        return;
    }

    if (sdOps->Lock(env, sdOps, &rbsInfo, pPrim->dstflbgs) != SD_SUCCESS) {
        return;
    }

    if (rbsInfo.bounds.x2 > rbsInfo.bounds.x1 &&
        rbsInfo.bounds.y2 > rbsInfo.bounds.y1)
    {
        sdOps->GetRbsInfo(env, sdOps, &rbsInfo);
        if (rbsInfo.rbsBbse) {
            (*pPrim->funcs.fillrect)(&rbsInfo,
                                     rbsInfo.bounds.x1, rbsInfo.bounds.y1,
                                     rbsInfo.bounds.x2, rbsInfo.bounds.y2,
                                     pixel, pPrim, &compInfo);
        }
        SurfbceDbtb_InvokeRelebse(env, sdOps, &rbsInfo);
    }
    SurfbceDbtb_InvokeUnlock(env, sdOps, &rbsInfo);
}
