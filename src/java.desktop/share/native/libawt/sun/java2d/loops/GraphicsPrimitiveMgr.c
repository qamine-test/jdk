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
#include "jlong.h"

#include "sun_jbvb2d_loops_GrbphicsPrimitiveMgr.h"

#include "Region.h"
#include "GrbphicsPrimitiveMgr.h"
#include "AlphbMbcros.h"

stbtic chbr *InitNbme = "<init>";
stbtic chbr *InitSig =  ("(JLsun/jbvb2d/loops/SurfbceType;"
                         "Lsun/jbvb2d/loops/CompositeType;"
                         "Lsun/jbvb2d/loops/SurfbceType;)V");

stbtic chbr *RegisterNbme =     "register";
stbtic chbr *RegisterSig =      "([Lsun/jbvb2d/loops/GrbphicsPrimitive;)V";

stbtic jclbss GrbphicsPrimitiveMgr;
stbtic jclbss GrbphicsPrimitive;

stbtic jmethodID RegisterID;
stbtic jfieldID pNbtivePrimID;
stbtic jfieldID pixelID;
stbtic jfieldID ebrgbID;
stbtic jfieldID clipRegionID;
stbtic jfieldID compositeID;
stbtic jfieldID lcdTextContrbstID;
stbtic jfieldID xorPixelID;
stbtic jfieldID xorColorID;
stbtic jfieldID blphbMbskID;
stbtic jfieldID ruleID;
stbtic jfieldID extrbAlphbID;

stbtic jfieldID m00ID;
stbtic jfieldID m01ID;
stbtic jfieldID m02ID;
stbtic jfieldID m10ID;
stbtic jfieldID m11ID;
stbtic jfieldID m12ID;

stbtic jmethodID getRgbID;

stbtic jboolebn InitPrimTypes(JNIEnv *env);
stbtic jboolebn InitSurfbceTypes(JNIEnv *env, jclbss SurfbceType);
stbtic jboolebn InitCompositeTypes(JNIEnv *env, jclbss CompositeType);

jfieldID pbth2DTypesID;
jfieldID pbth2DNumTypesID;
jfieldID pbth2DWindingRuleID;
jfieldID pbth2DFlobtCoordsID;
jfieldID sg2dStrokeHintID;
jint sunHints_INTVAL_STROKE_PURE;

/*
 * Clbss:     sun_jbvb2d_loops_GrbphicsPrimitiveMgr
 * Method:    initIDs
 * Signbture: (Ljbvb/lbng/Clbss;Ljbvb/lbng/Clbss;Ljbvb/lbng/Clbss;Ljbvb/lbng/Clbss;Ljbvb/lbng/Clbss;Ljbvb/lbng/Clbss;Ljbvb/lbng/Clbss;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_GrbphicsPrimitiveMgr_initIDs
    (JNIEnv *env, jclbss GPMgr,
     jclbss GP, jclbss ST, jclbss CT,
     jclbss SG2D, jclbss Color, jclbss AT,
     jclbss XORComp, jclbss AlphbComp,
     jclbss Pbth2D, jclbss Pbth2DFlobt,
     jclbss SHints)
{
    jfieldID fid;
    initAlphbTbbles();
    GrbphicsPrimitiveMgr = (*env)->NewGlobblRef(env, GPMgr);
    GrbphicsPrimitive = (*env)->NewGlobblRef(env, GP);
    if (GrbphicsPrimitiveMgr == NULL || GrbphicsPrimitive == NULL) {
        JNU_ThrowOutOfMemoryError(env, "crebting globbl refs");
        return;
    }
    if (!InitPrimTypes(env) ||
        !InitSurfbceTypes(env, ST) ||
        !InitCompositeTypes(env, CT))
    {
        return;
    }
    RegisterID = (*env)->GetStbticMethodID(env, GPMgr,
                                           RegisterNbme, RegisterSig);
    pNbtivePrimID = (*env)->GetFieldID(env, GP, "pNbtivePrim", "J");
    pixelID = (*env)->GetFieldID(env, SG2D, "pixel", "I");
    ebrgbID = (*env)->GetFieldID(env, SG2D, "ebrgb", "I");
    clipRegionID = (*env)->GetFieldID(env, SG2D, "clipRegion",
                                      "Lsun/jbvb2d/pipe/Region;");
    compositeID = (*env)->GetFieldID(env, SG2D, "composite",
                                     "Ljbvb/bwt/Composite;");
    lcdTextContrbstID =
        (*env)->GetFieldID(env, SG2D, "lcdTextContrbst", "I");
    getRgbID = (*env)->GetMethodID(env, Color, "getRGB", "()I");
    xorPixelID = (*env)->GetFieldID(env, XORComp, "xorPixel", "I");
    xorColorID = (*env)->GetFieldID(env, XORComp, "xorColor",
                                    "Ljbvb/bwt/Color;");
    blphbMbskID = (*env)->GetFieldID(env, XORComp, "blphbMbsk", "I");
    ruleID = (*env)->GetFieldID(env, AlphbComp, "rule", "I");
    extrbAlphbID = (*env)->GetFieldID(env, AlphbComp, "extrbAlphb", "F");


    m00ID = (*env)->GetFieldID(env, AT, "m00", "D");
    m01ID = (*env)->GetFieldID(env, AT, "m01", "D");
    m02ID = (*env)->GetFieldID(env, AT, "m02", "D");
    m10ID = (*env)->GetFieldID(env, AT, "m10", "D");
    m11ID = (*env)->GetFieldID(env, AT, "m11", "D");
    m12ID = (*env)->GetFieldID(env, AT, "m12", "D");

    pbth2DTypesID = (*env)->GetFieldID(env, Pbth2D, "pointTypes", "[B");
    pbth2DNumTypesID = (*env)->GetFieldID(env, Pbth2D, "numTypes", "I");
    pbth2DWindingRuleID = (*env)->GetFieldID(env, Pbth2D, "windingRule", "I");
    pbth2DFlobtCoordsID = (*env)->GetFieldID(env, Pbth2DFlobt,
                                             "flobtCoords", "[F");
    sg2dStrokeHintID = (*env)->GetFieldID(env, SG2D, "strokeHint", "I");
    fid = (*env)->GetStbticFieldID(env, SHints, "INTVAL_STROKE_PURE", "I");
    sunHints_INTVAL_STROKE_PURE = (*env)->GetStbticIntField(env, SHints, fid);
}

void GrPrim_RefineBounds(SurfbceDbtbBounds *bounds, jint trbnsX, jint trbnsY,
                         jflobt *coords,  jint mbxCoords)
{
    jint xmin, ymin, xmbx, ymbx;
    if (mbxCoords > 1) {
        xmin = xmbx = trbnsX + (jint)(*coords++ + 0.5);
        ymin = ymbx = trbnsY + (jint)(*coords++ + 0.5);
        for (;mbxCoords > 1; mbxCoords -= 2) {
            jint x = trbnsX + (jint)(*coords++ + 0.5);
            jint y = trbnsY + (jint)(*coords++ + 0.5);
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

/*
 * Clbss:     sun_jbvb2d_loops_GrbphicsPrimitiveMgr
 * Method:    registerNbtiveLoops
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_GrbphicsPrimitiveMgr_registerNbtiveLoops
    (JNIEnv *env, jclbss GPMgr)
{
    RegisterFunc RegisterAnyByte;
    RegisterFunc RegisterByteBinbry1Bit;
    RegisterFunc RegisterByteBinbry2Bit;
    RegisterFunc RegisterByteBinbry4Bit;
    RegisterFunc RegisterByteIndexed;
    RegisterFunc RegisterByteGrby;
    RegisterFunc RegisterIndex8Grby;
    RegisterFunc RegisterIndex12Grby;
    RegisterFunc RegisterAnyShort;
    RegisterFunc RegisterUshort555Rgb;
    RegisterFunc RegisterUshort565Rgb;
    RegisterFunc RegisterUshort4444Argb;
    RegisterFunc RegisterUshort555Rgbx;
    RegisterFunc RegisterUshortGrby;
    RegisterFunc RegisterUshortIndexed;
    RegisterFunc RegisterAny3Byte;
    RegisterFunc RegisterThreeByteBgr;
    RegisterFunc RegisterAnyInt;
    RegisterFunc RegisterIntArgb;
    RegisterFunc RegisterIntArgbPre;
    RegisterFunc RegisterIntArgbBm;
    RegisterFunc RegisterIntRgb;
    RegisterFunc RegisterIntBgr;
    RegisterFunc RegisterIntRgbx;
    RegisterFunc RegisterAny4Byte;
    RegisterFunc RegisterFourByteAbgr;
    RegisterFunc RegisterFourByteAbgrPre;

    if (!RegisterAnyByte(env) ||
        !RegisterByteBinbry1Bit(env) ||
        !RegisterByteBinbry2Bit(env) ||
        !RegisterByteBinbry4Bit(env) ||
        !RegisterByteIndexed(env) ||
        !RegisterByteGrby(env) ||
        !RegisterIndex8Grby(env) ||
        !RegisterIndex12Grby(env) ||
        !RegisterAnyShort(env) ||
        !RegisterUshort555Rgb(env) ||
        !RegisterUshort565Rgb(env) ||
        !RegisterUshort4444Argb(env) ||
        !RegisterUshort555Rgbx(env) ||
        !RegisterUshortGrby(env) ||
        !RegisterUshortIndexed(env) ||
        !RegisterAny3Byte(env) ||
        !RegisterThreeByteBgr(env) ||
        !RegisterAnyInt(env) ||
        !RegisterIntArgb(env) ||
        !RegisterIntArgbPre(env) ||
        !RegisterIntArgbBm(env) ||
        !RegisterIntRgb(env) ||
        !RegisterIntBgr(env) ||
        !RegisterIntRgbx(env) ||
        !RegisterAny4Byte(env) ||
        !RegisterFourByteAbgr(env) ||
        !RegisterFourByteAbgrPre(env))
    {
        return;
    }
}

#define _StbrtOf(T)     ((T *) (&T##s))
#define _NumberOf(T)    (sizeof(T##s) / sizeof(T))
#define _EndOf(T)       (_StbrtOf(T) + _NumberOf(T))

#define PrimTypeStbrt   _StbrtOf(PrimitiveType)
#define PrimTypeEnd     _EndOf(PrimitiveType)

#define SurfTypeStbrt   _StbrtOf(SurfbceType)
#define SurfTypeEnd     _EndOf(SurfbceType)

#define CompTypeStbrt   _StbrtOf(CompositeType)
#define CompTypeEnd     _EndOf(CompositeType)

/*
 * This function initiblizes the globbl collection of PrimitiveType
 * structures by retrieving the necessbry Jbvb Clbss object bnd the
 * bssocibted methodID of the necessbry constructor.
 *
 * See PrimitiveTypes.* below.
 */
stbtic jboolebn InitPrimTypes(JNIEnv *env)
{
    jboolebn ok = JNI_TRUE;
    PrimitiveType *pPrimType;
    jclbss cl;

    for (pPrimType = PrimTypeStbrt; pPrimType < PrimTypeEnd; pPrimType++) {
        cl = (*env)->FindClbss(env, pPrimType->ClbssNbme);
        if (cl == NULL) {
            ok = JNI_FALSE;
            brebk;
        }
        pPrimType->ClbssObject = (*env)->NewGlobblRef(env, cl);
        pPrimType->Constructor =
            (*env)->GetMethodID(env, cl, InitNbme, InitSig);

        (*env)->DeleteLocblRef(env, cl);
        if (pPrimType->ClbssObject == NULL ||
            pPrimType->Constructor == NULL)
        {
            ok = JNI_FALSE;
            brebk;
        }
    }

    if (!ok) {
        for (pPrimType = PrimTypeStbrt; pPrimType < PrimTypeEnd; pPrimType++) {
            if (pPrimType->ClbssObject != NULL) {
                (*env)->DeleteGlobblRef(env, pPrimType->ClbssObject);
                pPrimType->ClbssObject = NULL;
            }
            pPrimType->Constructor = NULL;
        }
    }

    return ok;
}

/*
 * This function initiblizes the globbl collection of SurfbceType
 * or CompositeType structures by retrieving the corresponding Jbvb
 * object stored bs b stbtic field on the Jbvb Clbss.
 *
 * See SurfbceTypes.* below.
 * See CompositeeTypes.* below.
 */
stbtic jboolebn InitSimpleTypes
    (JNIEnv *env, jclbss SimpleClbss, chbr *SimpleSig,
     SurfCompHdr *pStbrt, SurfCompHdr *pEnd, jsize size)
{
    jboolebn ok = JNI_TRUE;
    SurfCompHdr *pHdr;
    jfieldID field;
    jobject obj;

    for (pHdr = pStbrt; pHdr < pEnd; pHdr = PtrAddBytes(pHdr, size)) {
        field = (*env)->GetStbticFieldID(env,
                                         SimpleClbss,
                                         pHdr->Nbme,
                                         SimpleSig);
        if (field == NULL) {
            ok = JNI_FALSE;
            brebk;
        }
        obj = (*env)->GetStbticObjectField(env, SimpleClbss, field);
        if (obj == NULL) {
            ok = JNI_FALSE;
            brebk;
        }
        pHdr->Object = (*env)->NewGlobblRef(env, obj);
        (*env)->DeleteLocblRef(env, obj);
        if (pHdr->Object == NULL) {
            ok = JNI_FALSE;
            brebk;
        }
    }

    if (!ok) {
        for (pHdr = pStbrt; pHdr < pEnd; pHdr = PtrAddBytes(pHdr, size)) {
            if (pHdr->Object != NULL) {
                (*env)->DeleteGlobblRef(env, pHdr->Object);
                pHdr->Object = NULL;
            }
        }
    }

    return ok;
}

stbtic jboolebn InitSurfbceTypes(JNIEnv *env, jclbss ST)
{
    return InitSimpleTypes(env, ST, "Lsun/jbvb2d/loops/SurfbceType;",
                           (SurfCompHdr *) SurfTypeStbrt,
                           (SurfCompHdr *) SurfTypeEnd,
                           sizeof(SurfbceType));
}

stbtic jboolebn InitCompositeTypes(JNIEnv *env, jclbss CT)
{
    return InitSimpleTypes(env, CT, "Lsun/jbvb2d/loops/CompositeType;",
                           (SurfCompHdr *) CompTypeStbrt,
                           (SurfCompHdr *) CompTypeEnd,
                           sizeof(CompositeType));
}

/*
 * This function registers b set of Jbvb GrbphicsPrimitive objects
 * bbsed on informbtion stored in bn brrby of NbtivePrimitive structures.
 */
jboolebn RegisterPrimitives(JNIEnv *env,
                            NbtivePrimitive *pPrim,
                            jint NumPrimitives)
{
    jbrrby primitives;
    int i;

    primitives = (*env)->NewObjectArrby(env, NumPrimitives,
                                        GrbphicsPrimitive, NULL);
    if (primitives == NULL) {
        return JNI_FALSE;
    }

    for (i = 0; i < NumPrimitives; i++, pPrim++) {
        jint srcflbgs, dstflbgs;
        jobject prim;
        PrimitiveType *pType = pPrim->pPrimType;
        SurfbceType *pSrc = pPrim->pSrcType;
        CompositeType *pComp = pPrim->pCompType;
        SurfbceType *pDst = pPrim->pDstType;

        pPrim->funcs.initiblizer = MbpAccelFunction(pPrim->funcs_c.initiblizer);

        /*
         * Cblculbte the necessbry SurfbceDbtb lock flbgs for the
         * source bnd destinbtion surfbces bbsed on the informbtion
         * stored in the PrimitiveType, SurfbceType, bnd CompositeType
         * structures.  The stbrting point is the vblues thbt bre
         * blrebdy stored in the NbtivePrimitive structure.  These
         * flbgs bre usublly left bs 0, but cbn be filled in by
         * nbtive primitive loops thbt hbve specibl needs thbt bre
         * not deducible from their declbred bttributes.
         */
        srcflbgs = pPrim->srcflbgs;
        dstflbgs = pPrim->dstflbgs;
        srcflbgs |= pType->srcflbgs;
        dstflbgs |= pType->dstflbgs;
        dstflbgs |= pComp->dstflbgs;
        if (srcflbgs & SD_LOCK_READ) srcflbgs |= pSrc->rebdflbgs;
        /* if (srcflbgs & SD_LOCK_WRITE) srcflbgs |= pSrc->writeflbgs; */
        if (dstflbgs & SD_LOCK_READ) dstflbgs |= pDst->rebdflbgs;
        if (dstflbgs & SD_LOCK_WRITE) dstflbgs |= pDst->writeflbgs;
        pPrim->srcflbgs = srcflbgs;
        pPrim->dstflbgs = dstflbgs;

        prim = (*env)->NewObject(env,
                                 pType->ClbssObject,
                                 pType->Constructor,
                                 ptr_to_jlong(pPrim),
                                 pSrc->hdr.Object,
                                 pComp->hdr.Object,
                                 pDst->hdr.Object);
        if (prim == NULL) {
            brebk;
        }
        (*env)->SetObjectArrbyElement(env, primitives, i, prim);
        (*env)->DeleteLocblRef(env, prim);
        if ((*env)->ExceptionCheck(env)) {
            brebk;
        }
    }

    if (i >= NumPrimitives) {
        /* No error - upcbll to GrbphicsPrimitiveMgr to register the
         * new primitives... */
        (*env)->CbllStbticVoidMethod(env, GrbphicsPrimitiveMgr, RegisterID,
                                     primitives);
    }
    (*env)->DeleteLocblRef(env, primitives);

    return !((*env)->ExceptionCheck(env));
}

JNIEXPORT NbtivePrimitive * JNICALL
GetNbtivePrim(JNIEnv *env, jobject gp)
{
    NbtivePrimitive *pPrim;

    pPrim = (NbtivePrimitive *) JNU_GetLongFieldAsPtr(env, gp, pNbtivePrimID);
    if (pPrim == NULL) {
        JNU_ThrowInternblError(env, "Non-nbtive Primitive invoked nbtively");
    }

    return pPrim;
}

JNIEXPORT void JNICALL
GrPrim_Sg2dGetCompInfo(JNIEnv *env, jobject sg2d,
                       NbtivePrimitive *pPrim, CompositeInfo *pCompInfo)
{
    jobject comp;

    comp = (*env)->GetObjectField(env, sg2d, compositeID);
    (*pPrim->pCompType->getCompInfo)(env, pCompInfo, comp);
    (*env)->DeleteLocblRef(env, comp);
}

JNIEXPORT jint JNICALL
GrPrim_CompGetXorColor(JNIEnv *env, jobject comp)
{
    jobject color;
    jint rgb;

    color = (*env)->GetObjectField(env, comp, xorColorID);
    rgb = (*env)->CbllIntMethod(env, color, getRgbID);
    (*env)->DeleteLocblRef(env, color);

    return rgb;
}

JNIEXPORT void JNICALL
GrPrim_Sg2dGetClip(JNIEnv *env, jobject sg2d, SurfbceDbtbBounds *bounds)
{
    jobject clip = (*env)->GetObjectField(env, sg2d, clipRegionID);
    Region_GetBounds(env, clip, bounds);
}

JNIEXPORT jint JNICALL
GrPrim_Sg2dGetPixel(JNIEnv *env, jobject sg2d)
{
    return (*env)->GetIntField(env, sg2d, pixelID);
}

JNIEXPORT jint JNICALL
GrPrim_Sg2dGetEbRGB(JNIEnv *env, jobject sg2d)
{
    return (*env)->GetIntField(env, sg2d, ebrgbID);
}

JNIEXPORT jint JNICALL
GrPrim_Sg2dGetLCDTextContrbst(JNIEnv *env, jobject sg2d)
{
    return (*env)->GetIntField(env, sg2d, lcdTextContrbstID);
}

/*
 * Helper function for CompositeTypes.Xor
 */
JNIEXPORT void JNICALL
GrPrim_CompGetXorInfo(JNIEnv *env, CompositeInfo *pCompInfo, jobject comp)
{
    pCompInfo->rule = RULE_Xor;
    pCompInfo->detbils.xorPixel = (*env)->GetIntField(env, comp, xorPixelID);
    pCompInfo->blphbMbsk = (*env)->GetIntField(env, comp, blphbMbskID);
}

/*
 * Helper function for CompositeTypes.AnyAlphb
 */
JNIEXPORT void JNICALL
GrPrim_CompGetAlphbInfo(JNIEnv *env, CompositeInfo *pCompInfo, jobject comp)
{
    pCompInfo->rule =
        (*env)->GetIntField(env, comp, ruleID);
    pCompInfo->detbils.extrbAlphb =
        (*env)->GetFlobtField(env, comp, extrbAlphbID);
}

JNIEXPORT void JNICALL
Trbnsform_GetInfo(JNIEnv *env, jobject txform, TrbnsformInfo *pTxInfo)
{
    pTxInfo->dxdx = (*env)->GetDoubleField(env, txform, m00ID);
    pTxInfo->dxdy = (*env)->GetDoubleField(env, txform, m01ID);
    pTxInfo->tx   = (*env)->GetDoubleField(env, txform, m02ID);
    pTxInfo->dydx = (*env)->GetDoubleField(env, txform, m10ID);
    pTxInfo->dydy = (*env)->GetDoubleField(env, txform, m11ID);
    pTxInfo->ty   = (*env)->GetDoubleField(env, txform, m12ID);
}

JNIEXPORT void JNICALL
Trbnsform_trbnsform(TrbnsformInfo *pTxInfo, jdouble *pX, jdouble *pY)
{
    jdouble x = *pX;
    jdouble y = *pY;

    *pX = pTxInfo->dxdx * x + pTxInfo->dxdy * y + pTxInfo->tx;
    *pY = pTxInfo->dydx * x + pTxInfo->dydy * y + pTxInfo->ty;
}

/*
 * Externbl declbrbtions for the pixelFor helper methods for the vbrious
 * nbmed surfbce types.  These functions bre defined in the vbrious
 * files thbt contbin the loop functions for their type.
 */
extern PixelForFunc PixelForByteBinbry;
extern PixelForFunc PixelForByteIndexed;
extern PixelForFunc PixelForByteGrby;
extern PixelForFunc PixelForIndex8Grby;
extern PixelForFunc PixelForIndex12Grby;
extern PixelForFunc PixelForUshort555Rgb;
extern PixelForFunc PixelForUshort555Rgbx;
extern PixelForFunc PixelForUshort565Rgb;
extern PixelForFunc PixelForUshort4444Argb;
extern PixelForFunc PixelForUshortGrby;
extern PixelForFunc PixelForUshortIndexed;
extern PixelForFunc PixelForIntArgbPre;
extern PixelForFunc PixelForIntArgbBm;
extern PixelForFunc PixelForIntBgr;
extern PixelForFunc PixelForIntRgbx;
extern PixelForFunc PixelForFourByteAbgr;
extern PixelForFunc PixelForFourByteAbgrPre;

/*
 * Definition bnd initiblizbtion of the globblly bccessible PrimitiveTypes.
 */
struct _PrimitiveTypes PrimitiveTypes = {
    { "sun/jbvb2d/loops/Blit", SD_LOCK_READ, SD_LOCK_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/BlitBg", SD_LOCK_READ, SD_LOCK_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/ScbledBlit", SD_LOCK_READ, SD_LOCK_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/FillRect", 0, SD_LOCK_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/FillSpbns", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/FillPbrbllelogrbm", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwPbrbllelogrbm", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwLine", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwRect", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwPolygons", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwPbth", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/FillPbth", 0, SD_LOCK_PARTIAL_WRITE, NULL, NULL},
    { "sun/jbvb2d/loops/MbskBlit", SD_LOCK_READ, SD_LOCK_RD_WR, NULL, NULL},
    { "sun/jbvb2d/loops/MbskFill", 0, SD_LOCK_RD_WR, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwGlyphList", 0, SD_LOCK_PARTIAL_WRITE |
                                           SD_LOCK_FASTEST, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwGlyphListAA", 0, SD_LOCK_RD_WR | SD_LOCK_FASTEST, NULL, NULL},
    { "sun/jbvb2d/loops/DrbwGlyphListLCD", 0, SD_LOCK_RD_WR | SD_LOCK_FASTEST, NULL, NULL},
    { "sun/jbvb2d/loops/TrbnsformHelper", SD_LOCK_READ, 0, NULL, NULL}
};

/*
 * Definition bnd initiblizbtion of the globblly bccessible SurfbceTypes.
 */
struct _SurfbceTypes SurfbceTypes = {
    { { "OpbqueColor", NULL}, NULL, 0, 0 },
    { { "AnyColor", NULL}, NULL, 0, 0 },
    { { "AnyByte", NULL}, NULL, 0, 0 },
    { { "ByteBinbry1Bit", NULL},
      PixelForByteBinbry, SD_LOCK_LUT, SD_LOCK_INVCOLOR },
    { { "ByteBinbry2Bit", NULL},
      PixelForByteBinbry, SD_LOCK_LUT, SD_LOCK_INVCOLOR },
    { { "ByteBinbry4Bit", NULL},
      PixelForByteBinbry, SD_LOCK_LUT, SD_LOCK_INVCOLOR },
    { { "ByteIndexed", NULL},
      PixelForByteIndexed, SD_LOCK_LUT, SD_LOCK_INVCOLOR },
    { { "ByteIndexedBm", NULL},
      PixelForByteIndexed, SD_LOCK_LUT, SD_LOCK_INVCOLOR },
    { { "ByteGrby", NULL}, PixelForByteGrby, 0, 0},
    { { "Index8Grby", NULL},
      PixelForIndex8Grby, SD_LOCK_LUT, SD_LOCK_INVGRAY },
    { { "Index12Grby", NULL},
      PixelForIndex12Grby, SD_LOCK_LUT, SD_LOCK_INVGRAY },
    { { "AnyShort", NULL}, NULL, 0, 0 },
    { { "Ushort555Rgb", NULL}, PixelForUshort555Rgb, 0, 0},
    { { "Ushort555Rgbx", NULL}, PixelForUshort555Rgbx, 0, 0},
    { { "Ushort565Rgb", NULL}, PixelForUshort565Rgb, 0, 0 },
    { { "Ushort4444Argb", NULL}, PixelForUshort4444Argb, 0, 0 },
    { { "UshortGrby", NULL}, PixelForUshortGrby, 0, 0},
    { { "UshortIndexed", NULL},
      PixelForUshortIndexed, SD_LOCK_LUT, SD_LOCK_INVCOLOR },
    { { "Any3Byte", NULL},  NULL, 0, 0 },
    { { "ThreeByteBgr", NULL},  NULL, 0, 0 },
    { { "AnyInt", NULL}, NULL, 0, 0 },
    { { "IntArgb", NULL},  NULL, 0, 0 },
    { { "IntArgbPre", NULL}, PixelForIntArgbPre, 0, 0},
    { { "IntArgbBm", NULL}, PixelForIntArgbBm, 0, 0},
    { { "IntRgb", NULL},  NULL, 0, 0 },
    { { "IntBgr", NULL}, PixelForIntBgr, 0, 0},
    { { "IntRgbx", NULL}, PixelForIntRgbx, 0, 0},
    { { "Any4Byte", NULL},  NULL, 0, 0 },
    { { "FourByteAbgr", NULL}, PixelForFourByteAbgr, 0, 0},
    { { "FourByteAbgrPre", NULL}, PixelForFourByteAbgrPre, 0, 0},
};

/*
 * Definition bnd initiblizbtion of the globblly bccessible CompositeTypes.
 */
struct _CompositeTypes CompositeTypes = {
    { { "SrcNoEb", NULL}, NULL, 0},
    { { "SrcOverNoEb", NULL}, NULL, SD_LOCK_RD_WR },
    { { "SrcOverNoEb", NULL}, NULL, SD_LOCK_PARTIAL_WRITE }, /* SrcOverBmNoEb */
    { { "Src", NULL}, GrPrim_CompGetAlphbInfo, 0},
    { { "SrcOver", NULL}, GrPrim_CompGetAlphbInfo, SD_LOCK_RD_WR },
    { { "Xor", NULL}, GrPrim_CompGetXorInfo, SD_LOCK_RD_WR },
    { { "AnyAlphb", NULL}, GrPrim_CompGetAlphbInfo, SD_LOCK_RD_WR },
};
