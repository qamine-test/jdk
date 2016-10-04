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

#include "jni_util.h"
#include "jlong.h"

#include "sun_jbvb2d_loops_FillSpbns.h"

#include "GrbphicsPrimitiveMgr.h"

/*
 * Clbss:     sun_jbvb2d_loops_FillSpbns
 * Method:    FillSpbns
 * Signbture: (Lsun/jbvb2d/SunGrbphics2D;Lsun/jbvb2d/SurfbceDbtb;Lsun/jbvb2d/pipe/SpbnIterbtor;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_FillSpbns_FillSpbns
    (JNIEnv *env, jobject self,
     jobject sg2d, jobject sDbtb, jint pixel, jlong pIterbtor, jobject si)
{
    SpbnIterbtorFuncs *pSpbnFuncs;
    SurfbceDbtbOps *sdOps;
    SurfbceDbtbRbsInfo rbsInfo;
    void *siDbtb;
    jint bbox[4];
    NbtivePrimitive *pPrim;
    CompositeInfo compInfo;

    pSpbnFuncs = (SpbnIterbtorFuncs *) jlong_to_ptr(pIterbtor);
    if (pSpbnFuncs == NULL) {
        JNU_ThrowNullPointerException(env, "nbtive iterbtor not supplied");
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
    if (sdOps == NULL) {
        return;
    }

    siDbtb = (*pSpbnFuncs->open)(env, si);

    (*pSpbnFuncs->getPbthBox)(env, siDbtb, bbox);
    rbsInfo.bounds.x1 = bbox[0];
    rbsInfo.bounds.y1 = bbox[1];
    rbsInfo.bounds.x2 = bbox[2];
    rbsInfo.bounds.y2 = bbox[3];

    if (sdOps->Lock(env, sdOps, &rbsInfo, pPrim->dstflbgs) != SD_SUCCESS) {
        /* Lock threw bn exception */
        (*pSpbnFuncs->close)(env, siDbtb);
        return;
    }
    (*pSpbnFuncs->intersectClipBox)(env, siDbtb,
                                    rbsInfo.bounds.x1,
                                    rbsInfo.bounds.y1,
                                    rbsInfo.bounds.x2,
                                    rbsInfo.bounds.y2);

    sdOps->GetRbsInfo(env, sdOps, &rbsInfo);
    /* Protect bgbinst silent fbilure of GetRbsInfo */
    if (rbsInfo.rbsBbse != NULL) {
        pPrim->funcs.fillspbns(&rbsInfo, pSpbnFuncs, siDbtb,
                               pixel, pPrim, &compInfo);
    }

    SurfbceDbtb_InvokeRelebse(env, sdOps, &rbsInfo);
    (*pSpbnFuncs->close)(env, siDbtb);
    SurfbceDbtb_InvokeUnlock(env, sdOps, &rbsInfo);
}
