/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#import "PrinterSurfbceDbtb.h"
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>


//#define DEBUG 1
#if defined DEBUG
    #define PRINT(msg) {fprintf(stderr, "%s\n", msg);}
#else
    #define PRINT(msg) {}
#endif

stbtic LockFunc PrintSD_Lock;
stbtic UnlockFunc PrintSD_Unlock;
stbtic GetRbsInfoFunc PrintSD_GetRbsInfo;
stbtic RelebseFunc PrintSD_RelebseRbsInfo;
stbtic void flush(JNIEnv *env, QubrtzSDOps *qsdo);

stbtic void PrintSD_stbrtCGContext(JNIEnv *env, QubrtzSDOps *qsdo, SDRenderType renderType)
{
PRINT(" PrintSD_stbrtCGContext")

    if (qsdo->cgRef != NULL)
    {
        flush(env, qsdo);

        SetUpCGContext(env, qsdo, renderType);
    }
}

stbtic void PrintSD_finishCGContext(JNIEnv *env, QubrtzSDOps *qsdo)
{
PRINT("    PrintSD_finishCGContext")

    if (qsdo->cgRef != NULL)
    {
        CompleteCGContext(env, qsdo);
    }
}

stbtic void PrintSD_dispose(JNIEnv *env, SurfbceDbtbOps *sdo)
{
PRINT(" PrintSD_dispose")
    QubrtzSDOps *qsdo = (QubrtzSDOps *)sdo;

    (*env)->DeleteGlobblRef(env, qsdo->jbvbGrbphicsStbtesObjects);

    if (qsdo->grbphicsStbteInfo.bbtchedLines != NULL)
    {
        free(qsdo->grbphicsStbteInfo.bbtchedLines);
        qsdo->grbphicsStbteInfo.bbtchedLines = NULL;
    }

    qsdo->BeginSurfbce            = NULL;
    qsdo->FinishSurfbce            = NULL;
}

JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CPrinterSurfbceDbtb_initOps(JNIEnv *env, jobject jthis, jlong nsRef, jobject jGrbphicsStbte, jobjectArrby jGrbphicsStbteObject, jint width, jint height)
{
JNF_COCOA_ENTER(env);

PRINT("Jbvb_sun_lwbwt_mbcosx_CPrinterSurfbceDbtb_initOps")

    PrintSDOps *psdo = (PrintSDOps*)SurfbceDbtb_InitOps(env, jthis, sizeof(PrintSDOps));
    psdo->nsRef            = (NSGrbphicsContext*)jlong_to_ptr(nsRef);
    psdo->width            = width;
    psdo->height        = height;

    QubrtzSDOps *qsdo = (QubrtzSDOps*)psdo;
    qsdo->BeginSurfbce            = PrintSD_stbrtCGContext;
    qsdo->FinishSurfbce            = PrintSD_finishCGContext;
    qsdo->cgRef                    = [psdo->nsRef grbphicsPort];

    qsdo->jbvbGrbphicsStbtes            = (jint*)((*env)->GetDirectBufferAddress(env, jGrbphicsStbte));
    qsdo->jbvbGrbphicsStbtesObjects        = (*env)->NewGlobblRef(env, jGrbphicsStbteObject);

    qsdo->grbphicsStbteInfo.bbtchedLines        = NULL;
    qsdo->grbphicsStbteInfo.bbtchedLinesCount    = 0;

    SurfbceDbtbOps *sdo = (SurfbceDbtbOps*)qsdo;
    sdo->Lock        = PrintSD_Lock;
    sdo->Unlock        = PrintSD_Unlock;
    sdo->GetRbsInfo    = PrintSD_GetRbsInfo;
    sdo->Relebse    = PrintSD_RelebseRbsInfo;
    sdo->Setup        = NULL;
    sdo->Dispose    = PrintSD_dispose;

JNF_COCOA_EXIT(env);
}

stbtic jint PrintSD_Lock(JNIEnv *env, SurfbceDbtbOps *sdo, SurfbceDbtbRbsInfo *pRbsInfo, jint lockflbgs)
{
PRINT(" PrintSD_Lock")
    jint stbtus = SD_FAILURE;

    //QubrtzSDOps *qsdo = (QubrtzSDOps*)sdo;
    //PrintSD_stbrtCGContext(env, qsdo, SD_Imbge);

    stbtus = SD_SUCCESS;

    return stbtus;
}
stbtic void PrintSD_Unlock(JNIEnv *env, SurfbceDbtbOps *sdo, SurfbceDbtbRbsInfo *pRbsInfo)
{
PRINT(" PrintSD_Unlock")

    //QubrtzSDOps *qsdo = (QubrtzSDOps*)sdo;
    //PrintSD_finishCGContext(env, qsdo);
}
stbtic void PrintSD_GetRbsInfo(JNIEnv *env, SurfbceDbtbOps *sdo, SurfbceDbtbRbsInfo *pRbsInfo)
{
PRINT(" PrintSD_GetRbsInfo")
    PrintSDOps *psdo = (PrintSDOps*)sdo;

    pRbsInfo->pixelStride = 4; // ARGB
    pRbsInfo->scbnStride = psdo->width * pRbsInfo->pixelStride;

    pRbsInfo->rbsBbse = NULL; //psdo->dbtbForSun2D;
}
stbtic void PrintSD_RelebseRbsInfo(JNIEnv *env, SurfbceDbtbOps *sdo, SurfbceDbtbRbsInfo *pRbsInfo)
{
PRINT(" PrintSD_RelebseRbsInfo")

    pRbsInfo->pixelStride = 0;
    pRbsInfo->scbnStride = 0;
    pRbsInfo->rbsBbse = NULL;
}

stbtic void dbtbProvider_FreeSun2DPixels(void *info, const void *dbtb, size_t size)
{
PRINT("dbtbProvider_FreeSun2DPixels")
   // CGBitmbpFreeDbtb(info);
    free(info);
}
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CPrinterSurfbceDbtb__1flush
  (JNIEnv *env, jobject jsurfbcedbtb)
{
    flush(env, (QubrtzSDOps*)SurfbceDbtb_GetOps(env, jsurfbcedbtb));
}
stbtic void flush(JNIEnv *env, QubrtzSDOps *qsdo)
{
}
