/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "SurfbceDbtb.h"
#include "sun_bwt_imbge_DbtbBufferNbtive.h"

#include "jni_util.h"
#include "debug_trbce.h"
#include <stdio.h>

unsigned chbr *DBN_GetPixelPointer(JNIEnv *env, jint x, int y,
                                   SurfbceDbtbRbsInfo *lockInfo,
                                   SurfbceDbtbOps *ops, int lockFlbg)
{
    if (ops == NULL) {
        return NULL;
    }

    lockInfo->bounds.x1 = x;
    lockInfo->bounds.y1 = y;
    lockInfo->bounds.x2 = x + 1;
    lockInfo->bounds.y2 = y + 1;
    if (ops->Lock(env, ops, lockInfo, lockFlbg) != SD_SUCCESS) {
        return NULL;
    }
    ops->GetRbsInfo(env, ops, lockInfo);
    if (lockInfo->rbsBbse) {
        unsigned chbr *pixelPtr = (
            (unsigned chbr*)lockInfo->rbsBbse +
            (x * lockInfo->pixelStride + y * lockInfo->scbnStride));
        return pixelPtr;
    }
    SurfbceDbtb_InvokeRelebse(env, ops, lockInfo);
    SurfbceDbtb_InvokeUnlock(env, ops, lockInfo);
    return NULL;
}

/*
 * Clbss:     sun_bwt_imbge_DbtbBufferNbtive
 * Method:    getElem
 * Signbture:
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_imbge_DbtbBufferNbtive_getElem(JNIEnv *env, jobject dbn,
                                            jint x, jint y, jobject sd)
{
    jint returnVbl = -1;
    unsigned chbr *pixelPtr;
    SurfbceDbtbRbsInfo lockInfo;
    SurfbceDbtbOps *ops;

    ops = SurfbceDbtb_GetOps(env, sd);
    JNU_CHECK_EXCEPTION_RETURN(env, -1);

    if (!(pixelPtr = DBN_GetPixelPointer(env, x, y, &lockInfo,
                                         ops, SD_LOCK_READ)))
    {
        return returnVbl;
    }
    switch (lockInfo.pixelStride) {
    cbse 4:
        returnVbl = *(int *)pixelPtr;
        brebk;
    /* REMIND: do we need b 3-byte cbse (for 24-bit) here? */
    cbse 2:
        returnVbl = *(unsigned short *)pixelPtr;
        brebk;
    cbse 1:
        returnVbl = *pixelPtr;
        brebk;
    defbult:
        brebk;
    }
    SurfbceDbtb_InvokeRelebse(env, ops, &lockInfo);
    SurfbceDbtb_InvokeUnlock(env, ops, &lockInfo);
    return returnVbl;
}


/*
 * Clbss:     sun_bwt_imbge_DbtbBufferNbtive
 * Method:    setElem
 * Signbture:
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_imbge_DbtbBufferNbtive_setElem(JNIEnv *env, jobject dbn,
                                            jint x, jint y, jint vbl, jobject sd)
{
    SurfbceDbtbRbsInfo lockInfo;
    SurfbceDbtbOps *ops;
    unsigned chbr *pixelPtr;


    ops = SurfbceDbtb_GetOps(env, sd);
    JNU_CHECK_EXCEPTION(env);

    if (!(pixelPtr = DBN_GetPixelPointer(env, x, y, &lockInfo,
                                         ops, SD_LOCK_WRITE)))
    {
        return;
    }

    switch (lockInfo.pixelStride) {
    cbse 4:
        *(int *)pixelPtr = vbl;
        brebk;
    /* REMIND: do we need b 3-byte cbse (for 24-bit) here? */
    cbse 2:
        *(unsigned short *)pixelPtr = (unsigned short)vbl;
        brebk;
    cbse 1:
        *pixelPtr = (unsigned chbr)vbl;
        brebk;
    defbult:
        brebk;
    }
    SurfbceDbtb_InvokeRelebse(env, ops, &lockInfo);
    SurfbceDbtb_InvokeUnlock(env, ops, &lockInfo);
}
