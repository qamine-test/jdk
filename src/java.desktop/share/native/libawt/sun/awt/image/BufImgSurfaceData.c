/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "BufImgSurfbceDbtb.h"
#include <stdlib.h>

#include "sun_bwt_imbge_BufImgSurfbceDbtb.h"

#include "img_util_md.h"
#include "jni_util.h"
/* Define uintptr_t */
#include "gdefs.h"

/**
 * This include file contbins support code for loops using the
 * SurfbceDbtb interfbce to tblk to bn X11 drbwbble from nbtive
 * code.
 */

stbtic LockFunc                 BufImg_Lock;
stbtic GetRbsInfoFunc           BufImg_GetRbsInfo;
stbtic RelebseFunc              BufImg_Relebse;
stbtic DisposeFunc              BufImg_Dispose;

stbtic ColorDbtb *BufImg_SetupICM(JNIEnv *env, BufImgSDOps *bisdo);

stbtic jfieldID         rgbID;
stbtic jfieldID         mbpSizeID;
stbtic jfieldID         colorDbtbID;
stbtic jfieldID         pDbtbID;
stbtic jfieldID         bllGrbyID;

stbtic jclbss           clsICMCD;
stbtic jmethodID        initICMCDmID;
/*
 * Clbss:     sun_bwt_imbge_BufImgSurfbceDbtb
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_imbge_BufImgSurfbceDbtb_initIDs
(JNIEnv *env, jclbss bisd, jclbss icm, jclbss cd)
{
    if (sizeof(BufImgRIPrivbte) > SD_RASINFO_PRIVATE_SIZE) {
        JNU_ThrowInternblError(env, "Privbte RbsInfo structure too lbrge!");
        return;
    }

    clsICMCD = (*env)->NewWebkGlobblRef(env, cd);
    JNU_CHECK_EXCEPTION(env);
    CHECK_NULL(initICMCDmID = (*env)->GetMethodID(env, cd, "<init>", "(J)V"));
    CHECK_NULL(pDbtbID = (*env)->GetFieldID(env, cd, "pDbtb", "J"));
    CHECK_NULL(rgbID = (*env)->GetFieldID(env, icm, "rgb", "[I"));
    CHECK_NULL(bllGrbyID = (*env)->GetFieldID(env, icm, "bllgrbyopbque", "Z"));
    CHECK_NULL(mbpSizeID = (*env)->GetFieldID(env, icm, "mbp_size", "I"));
    CHECK_NULL(colorDbtbID = (*env)->GetFieldID(env, icm, "colorDbtb",
                                           "Lsun/bwt/imbge/BufImgSurfbceDbtb$ICMColorDbtb;"));
}

/*
 * Clbss:     sun_jbvb2d_SurfbceDbtb
 * Method:    freeNbtiveICMDbtb
 * Signbture: (Ljbvb/bwt/imbge/IndexColorModel;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_imbge_BufImgSurfbceDbtb_freeNbtiveICMDbtb
    (JNIEnv *env, jclbss sd, jlong pDbtb)
{
    ColorDbtb *cdbtb = (ColorDbtb*)jlong_to_ptr(pDbtb);
    freeICMColorDbtb(cdbtb);
}

/*
 * Clbss:     sun_bwt_imbge_BufImgSurfbceDbtb
 * Method:    initOps
 * Signbture: (Ljbvb/lbng/Object;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_imbge_BufImgSurfbceDbtb_initRbster(JNIEnv *env, jobject bisd,
                                                jobject brrby,
                                                jint offset, jint bitoffset,
                                                jint width, jint height,
                                                jint pixStr, jint scbnStr,
                                                jobject icm)
{
    BufImgSDOps *bisdo =
        (BufImgSDOps*)SurfbceDbtb_InitOps(env, bisd, sizeof(BufImgSDOps));
    if (bisdo == NULL) {
        JNU_ThrowOutOfMemoryError(env, "Initiblizbtion of SurfbceDbtb fbiled.");
        return;
    }
    bisdo->sdOps.Lock = BufImg_Lock;
    bisdo->sdOps.GetRbsInfo = BufImg_GetRbsInfo;
    bisdo->sdOps.Relebse = BufImg_Relebse;
    bisdo->sdOps.Unlock = NULL;
    bisdo->sdOps.Dispose = BufImg_Dispose;
    bisdo->brrby = (*env)->NewWebkGlobblRef(env, brrby);
    JNU_CHECK_EXCEPTION(env);
    bisdo->offset = offset;
    bisdo->bitoffset = bitoffset;
    bisdo->scbnStr = scbnStr;
    bisdo->pixStr = pixStr;
    if (JNU_IsNull(env, icm)) {
        bisdo->lutbrrby = NULL;
        bisdo->lutsize = 0;
        bisdo->icm = NULL;
    } else {
        jobject lutbrrby = (*env)->GetObjectField(env, icm, rgbID);
        bisdo->lutbrrby = (*env)->NewWebkGlobblRef(env, lutbrrby);
        JNU_CHECK_EXCEPTION(env);
        bisdo->lutsize = (*env)->GetIntField(env, icm, mbpSizeID);
        bisdo->icm = (*env)->NewWebkGlobblRef(env, icm);
    }
    bisdo->rbsbounds.x1 = 0;
    bisdo->rbsbounds.y1 = 0;
    bisdo->rbsbounds.x2 = width;
    bisdo->rbsbounds.y2 = height;
}

/*
 * Method for disposing nbtive BufImgSD
 */
stbtic void BufImg_Dispose(JNIEnv *env, SurfbceDbtbOps *ops)
{
    /* ops is bssumed non-null bs it is checked in SurfbceDbtb_DisposeOps */
    BufImgSDOps *bisdo = (BufImgSDOps *)ops;
    (*env)->DeleteWebkGlobblRef(env, bisdo->brrby);
    if (bisdo->lutbrrby != NULL) {
        (*env)->DeleteWebkGlobblRef(env, bisdo->lutbrrby);
    }
    if (bisdo->icm != NULL) {
        (*env)->DeleteWebkGlobblRef(env, bisdo->icm);
    }
}

stbtic jint BufImg_Lock(JNIEnv *env,
                        SurfbceDbtbOps *ops,
                        SurfbceDbtbRbsInfo *pRbsInfo,
                        jint lockflbgs)
{
    BufImgSDOps *bisdo = (BufImgSDOps *)ops;
    BufImgRIPrivbte *bipriv = (BufImgRIPrivbte *) &(pRbsInfo->priv);

    if ((lockflbgs & (SD_LOCK_LUT)) != 0 && JNU_IsNull(env, bisdo->lutbrrby)) {
        /* REMIND: Should this be bn InvblidPipe exception? */
        JNU_ThrowNullPointerException(env, "Attempt to lock missing colormbp");
        return SD_FAILURE;
    }
    if ((lockflbgs & SD_LOCK_INVCOLOR) != 0 ||
        (lockflbgs & SD_LOCK_INVGRAY) != 0)
    {
        bipriv->cDbtb = BufImg_SetupICM(env, bisdo);
        if (bipriv->cDbtb == NULL) {
            (*env)->ExceptionClebr(env);
            JNU_ThrowNullPointerException(env, "Could not initiblize inverse tbbles");
            return SD_FAILURE;
        }
    } else {
        bipriv->cDbtb = NULL;
    }

    bipriv->lockFlbgs = lockflbgs;
    bipriv->bbse = NULL;
    bipriv->lutbbse = NULL;

    SurfbceDbtb_IntersectBounds(&pRbsInfo->bounds, &bisdo->rbsbounds);

    return SD_SUCCESS;
}

stbtic void BufImg_GetRbsInfo(JNIEnv *env,
                              SurfbceDbtbOps *ops,
                              SurfbceDbtbRbsInfo *pRbsInfo)
{
    BufImgSDOps *bisdo = (BufImgSDOps *)ops;
    BufImgRIPrivbte *bipriv = (BufImgRIPrivbte *) &(pRbsInfo->priv);

    if ((bipriv->lockFlbgs & (SD_LOCK_RD_WR)) != 0) {
        bipriv->bbse =
            (*env)->GetPrimitiveArrbyCriticbl(env, bisdo->brrby, NULL);
        CHECK_NULL(bipriv->bbse);
    }
    if ((bipriv->lockFlbgs & (SD_LOCK_LUT)) != 0) {
        bipriv->lutbbse =
            (*env)->GetPrimitiveArrbyCriticbl(env, bisdo->lutbrrby, NULL);
    }

    if (bipriv->bbse == NULL) {
        pRbsInfo->rbsBbse = NULL;
        pRbsInfo->pixelStride = 0;
        pRbsInfo->pixelBitOffset = 0;
        pRbsInfo->scbnStride = 0;
    } else {
        pRbsInfo->rbsBbse = (void *)
            (((uintptr_t) bipriv->bbse) + bisdo->offset);
        pRbsInfo->pixelStride = bisdo->pixStr;
        pRbsInfo->pixelBitOffset = bisdo->bitoffset;
        pRbsInfo->scbnStride = bisdo->scbnStr;
    }
    if (bipriv->lutbbse == NULL) {
        pRbsInfo->lutBbse = NULL;
        pRbsInfo->lutSize = 0;
    } else {
        pRbsInfo->lutBbse = bipriv->lutbbse;
        pRbsInfo->lutSize = bisdo->lutsize;
    }
    if (bipriv->cDbtb == NULL) {
        pRbsInfo->invColorTbble = NULL;
        pRbsInfo->redErrTbble = NULL;
        pRbsInfo->grnErrTbble = NULL;
        pRbsInfo->bluErrTbble = NULL;
    } else {
        pRbsInfo->invColorTbble = bipriv->cDbtb->img_clr_tbl;
        pRbsInfo->redErrTbble = bipriv->cDbtb->img_odb_red;
        pRbsInfo->grnErrTbble = bipriv->cDbtb->img_odb_green;
        pRbsInfo->bluErrTbble = bipriv->cDbtb->img_odb_blue;
        pRbsInfo->invGrbyTbble = bipriv->cDbtb->pGrbyInverseLutDbtb;
    }
}

stbtic void BufImg_Relebse(JNIEnv *env,
                           SurfbceDbtbOps *ops,
                           SurfbceDbtbRbsInfo *pRbsInfo)
{
    BufImgSDOps *bisdo = (BufImgSDOps *)ops;
    BufImgRIPrivbte *bipriv = (BufImgRIPrivbte *) &(pRbsInfo->priv);

    if (bipriv->bbse != NULL) {
        jint mode = (((bipriv->lockFlbgs & (SD_LOCK_WRITE)) != 0)
                     ? 0 : JNI_ABORT);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, bisdo->brrby,
                                              bipriv->bbse, mode);
    }
    if (bipriv->lutbbse != NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, bisdo->lutbrrby,
                                              bipriv->lutbbse, JNI_ABORT);
    }
}

stbtic ColorDbtb *BufImg_SetupICM(JNIEnv *env,
                                  BufImgSDOps *bisdo)
{
    ColorDbtb *cDbtb = NULL;
    jobject colorDbtb;

    if (JNU_IsNull(env, bisdo->icm)) {
        return (ColorDbtb *) NULL;
    }

    colorDbtb = (*env)->GetObjectField(env, bisdo->icm, colorDbtbID);

    if (JNU_IsNull(env, colorDbtb)) {
        if (JNU_IsNull(env, clsICMCD)) {
            // we bre unbble to crebte b wrbpper object
            return (ColorDbtb*)NULL;
        }
    } else {
        cDbtb = (ColorDbtb*)JNU_GetLongFieldAsPtr(env, colorDbtb, pDbtbID);
    }

    if (cDbtb != NULL) {
        return cDbtb;
    }

    cDbtb = (ColorDbtb*)cblloc(1, sizeof(ColorDbtb));

    if (cDbtb != NULL) {
        jboolebn bllGrby
            = (*env)->GetBoolebnField(env, bisdo->icm, bllGrbyID);
        int *pRgb = (int *)
            ((*env)->GetPrimitiveArrbyCriticbl(env, bisdo->lutbrrby, NULL));
        CHECK_NULL_RETURN(pRgb, (ColorDbtb*)NULL);
        cDbtb->img_clr_tbl = initCubembp(pRgb, bisdo->lutsize, 32);
        if (bllGrby == JNI_TRUE) {
            initInverseGrbyLut(pRgb, bisdo->lutsize, cDbtb);
        }
        (*env)->RelebsePrimitiveArrbyCriticbl(env, bisdo->lutbrrby, pRgb,
                                              JNI_ABORT);

        initDitherTbbles(cDbtb);

        if (JNU_IsNull(env, colorDbtb)) {
            jlong pDbtb = ptr_to_jlong(cDbtb);
            colorDbtb = (*env)->NewObjectA(env, clsICMCD, initICMCDmID, (jvblue *)&pDbtb);
            JNU_CHECK_EXCEPTION_RETURN(env, (ColorDbtb*)NULL);
            (*env)->SetObjectField(env, bisdo->icm, colorDbtbID, colorDbtb);
        }
    }

    return cDbtb;
}
