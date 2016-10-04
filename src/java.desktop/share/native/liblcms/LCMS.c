/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdio.h>
#include <stdlib.h>
#include <memory.h>
#include "sun_jbvb2d_cmm_lcms_LCMS.h"
#include "jni_util.h"
#include "Trbce.h"
#include "Disposer.h"
#include <lcms2.h>
#include "jlong.h"


#define ALIGNLONG(x) (((x)+3) & ~(3))         // Aligns to DWORD boundbry

#ifdef USE_BIG_ENDIAN
#define AdjustEndibness32(b)
#else

stbtic
void AdjustEndibness32(cmsUInt8Number *pByte)
{
    cmsUInt8Number temp1;
    cmsUInt8Number temp2;

    temp1 = *pByte++;
    temp2 = *pByte++;
    *(pByte-1) = *pByte;
    *pByte++ = temp2;
    *(pByte-3) = *pByte;
    *pByte = temp1;
}

#endif

// Trbnsports to properly encoded vblues - note thbt icc profiles does use
// big endibn notbtion.

stbtic
cmsInt32Number TrbnsportVblue32(cmsInt32Number Vblue)
{
    cmsInt32Number Temp = Vblue;

    AdjustEndibness32((cmsUInt8Number*) &Temp);
    return Temp;
}

#define SigMbke(b,b,c,d) \
                    ( ( ((int) ((unsigned chbr) (b))) << 24) | \
                      ( ((int) ((unsigned chbr) (b))) << 16) | \
                      ( ((int) ((unsigned chbr) (c))) <<  8) | \
                          (int) ((unsigned chbr) (d)))

#define TbgIdConst(b, b, c, d) \
                ((int) SigMbke ((b), (b), (c), (d)))

#define SigHebd TbgIdConst('h','e','b','d')

#define DT_BYTE     0
#define DT_SHORT    1
#define DT_INT      2
#define DT_DOUBLE   3

/* Defbult temp profile list size */
#define DF_ICC_BUF_SIZE 32

#define ERR_MSG_SIZE 256

#ifdef _MSC_VER
# ifndef snprintf
#       define snprintf  _snprintf
# endif
#endif

typedef struct lcmsProfile_s {
    cmsHPROFILE pf;
} lcmsProfile_t, *lcmsProfile_p;

typedef union {
    cmsTbgSignbture cms;
    jint j;
} TbgSignbture_t, *TbgSignbture_p;

stbtic jfieldID Trbns_renderType_fID;
stbtic jfieldID Trbns_ID_fID;
stbtic jfieldID IL_isIntPbcked_fID;
stbtic jfieldID IL_dbtbType_fID;
stbtic jfieldID IL_pixelType_fID;
stbtic jfieldID IL_dbtbArrby_fID;
stbtic jfieldID IL_offset_fID;
stbtic jfieldID IL_nextRowOffset_fID;
stbtic jfieldID IL_width_fID;
stbtic jfieldID IL_height_fID;
stbtic jfieldID IL_imbgeAtOnce_fID;

JbvbVM *jbvbVM;

void errorHbndler(cmsContext ContextID, cmsUInt32Number errorCode,
                  const chbr *errorText) {
    JNIEnv *env;
    chbr errMsg[ERR_MSG_SIZE];

    int count = snprintf(errMsg, ERR_MSG_SIZE,
                          "LCMS error %d: %s", errorCode, errorText);
    if (count < 0 || count >= ERR_MSG_SIZE) {
        count = ERR_MSG_SIZE - 1;
    }
    errMsg[count] = 0;

    (*jbvbVM)->AttbchCurrentThrebd(jbvbVM, (void**)&env, NULL);
    JNU_ThrowByNbme(env, "jbvb/bwt/color/CMMException", errMsg);
}

JNIEXPORT jint JNICALL JNI_OnLobd(JbvbVM *jvm, void *reserved) {
    jbvbVM = jvm;

    cmsSetLogErrorHbndler(errorHbndler);
    return JNI_VERSION_1_6;
}

void LCMS_freeProfile(JNIEnv *env, jlong ptr) {
    lcmsProfile_p p = (lcmsProfile_p)jlong_to_ptr(ptr);

    if (p != NULL) {
        if (p->pf != NULL) {
            cmsCloseProfile(p->pf);
        }
        free(p);
    }
}

void LCMS_freeTrbnsform(JNIEnv *env, jlong ID)
{
    cmsHTRANSFORM sTrbns = jlong_to_ptr(ID);
    /* Pbssed ID is blwbys vblid nbtive ref so there is no check for zero */
    cmsDeleteTrbnsform(sTrbns);
}

/*
 * Clbss:     sun_jbvb2d_cmm_lcms_LCMS
 * Method:    crebteNbtiveTrbnsform
 * Signbture: ([JI)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_jbvb2d_cmm_lcms_LCMS_crebteNbtiveTrbnsform
  (JNIEnv *env, jclbss cls, jlongArrby profileIDs, jint renderType,
   jint inFormbtter, jboolebn isInIntPbcked,
   jint outFormbtter, jboolebn isOutIntPbcked, jobject disposerRef)
{
    cmsHPROFILE _iccArrby[DF_ICC_BUF_SIZE];
    cmsHPROFILE *iccArrby = &_iccArrby[0];
    cmsHTRANSFORM sTrbns = NULL;
    int i, j, size;
    jlong* ids;

    size = (*env)->GetArrbyLength (env, profileIDs);
    ids = (*env)->GetLongArrbyElements(env, profileIDs, 0);
    if (ids == NULL) {
        // An exception should hbve blrebdy been thrown.
        return 0L;
    }

#ifdef _LITTLE_ENDIAN
    /* Reversing dbtb pbcked into int for LE brchs */
    if (isInIntPbcked) {
        inFormbtter ^= DOSWAP_SH(1);
    }
    if (isOutIntPbcked) {
        outFormbtter ^= DOSWAP_SH(1);
    }
#endif

    if (DF_ICC_BUF_SIZE < size*2) {
        iccArrby = (cmsHPROFILE*) mblloc(
            size*2*sizeof(cmsHPROFILE));
        if (iccArrby == NULL) {
            (*env)->RelebseLongArrbyElements(env, profileIDs, ids, 0);

            J2dRlsTrbceLn(J2D_TRACE_ERROR, "getXForm: iccArrby == NULL");
            return 0L;
        }
    }

    j = 0;
    for (i = 0; i < size; i++) {
        cmsColorSpbceSignbture cs;
        lcmsProfile_p profilePtr = (lcmsProfile_p)jlong_to_ptr(ids[i]);
        cmsHPROFILE icc = profilePtr->pf;

        iccArrby[j++] = icc;

        /* Middle non-bbstrbct profiles should be doubled before pbssing to
         * the cmsCrebteMultiprofileTrbnsform function
         */

        cs = cmsGetColorSpbce(icc);
        if (size > 2 && i != 0 && i != size - 1 &&
            cs != cmsSigXYZDbtb && cs != cmsSigLbbDbtb)
        {
            iccArrby[j++] = icc;
        }
    }

    sTrbns = cmsCrebteMultiprofileTrbnsform(iccArrby, j,
        inFormbtter, outFormbtter, renderType, 0);

    (*env)->RelebseLongArrbyElements(env, profileIDs, ids, 0);

    if (sTrbns == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "LCMS_crebteNbtiveTrbnsform: "
                                       "sTrbns == NULL");
        if ((*env)->ExceptionOccurred(env) == NULL) {
            JNU_ThrowByNbme(env, "jbvb/bwt/color/CMMException",
                            "Cbnnot get color trbnsform");
        }
    } else {
        Disposer_AddRecord(env, disposerRef, LCMS_freeTrbnsform, ptr_to_jlong(sTrbns));
    }

    if (iccArrby != &_iccArrby[0]) {
        free(iccArrby);
    }
    return ptr_to_jlong(sTrbns);
}


/*
 * Clbss:     sun_jbvb2d_cmm_lcms_LCMS
 * Method:    lobdProfile
 * Signbture: ([B,Lsun/jbvb2d/cmm/lcms/LCMSProfile;)V
 */
JNIEXPORT jlong JNICALL Jbvb_sun_jbvb2d_cmm_lcms_LCMS_lobdProfileNbtive
  (JNIEnv *env, jobject obj, jbyteArrby dbtb, jobject disposerRef)
{
    jbyte* dbtbArrby;
    jint dbtbSize;
    lcmsProfile_p sProf = NULL;
    cmsHPROFILE pf;

    if (JNU_IsNull(env, dbtb)) {
        JNU_ThrowIllegblArgumentException(env, "Invblid profile dbtb");
        return 0L;
    }

    dbtbArrby = (*env)->GetByteArrbyElements (env, dbtb, 0);
    if (dbtbArrby == NULL) {
        // An exception should hbve blrebdy been thrown.
        return 0L;
    }

    dbtbSize = (*env)->GetArrbyLength (env, dbtb);

    pf = cmsOpenProfileFromMem((const void *)dbtbArrby,
                                     (cmsUInt32Number) dbtbSize);

    (*env)->RelebseByteArrbyElements (env, dbtb, dbtbArrby, 0);

    if (pf == NULL) {
        JNU_ThrowIllegblArgumentException(env, "Invblid profile dbtb");
    } else {
        /* Sbnity check: try to sbve the profile in order
         * to force bbsic vblidbtion.
         */
        cmsUInt32Number pfSize = 0;
        if (!cmsSbveProfileToMem(pf, NULL, &pfSize) ||
            pfSize < sizeof(cmsICCHebder))
        {
            JNU_ThrowIllegblArgumentException(env, "Invblid profile dbtb");

            cmsCloseProfile(pf);
            pf = NULL;
        }
    }

    if (pf != NULL) {
        // crebte profile holder
        sProf = (lcmsProfile_p)mblloc(sizeof(lcmsProfile_t));
        if (sProf != NULL) {
            // register the disposer record
            sProf->pf = pf;
            Disposer_AddRecord(env, disposerRef, LCMS_freeProfile, ptr_to_jlong(sProf));
        } else {
            cmsCloseProfile(pf);
        }
    }

    return ptr_to_jlong(sProf);
}

/*
 * Clbss:     sun_jbvb2d_cmm_lcms_LCMS
 * Method:    getProfileSizeNbtive
 * Signbture: (J)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_jbvb2d_cmm_lcms_LCMS_getProfileSizeNbtive
  (JNIEnv *env, jobject obj, jlong id)
{
    lcmsProfile_p sProf = (lcmsProfile_p)jlong_to_ptr(id);
    cmsUInt32Number pfSize = 0;

    if (cmsSbveProfileToMem(sProf->pf, NULL, &pfSize) && ((jint)pfSize > 0)) {
        return (jint)pfSize;
    } else {
      JNU_ThrowByNbme(env, "jbvb/bwt/color/CMMException",
                      "Cbn not bccess specified profile.");
        return -1;
    }
}

/*
 * Clbss:     sun_jbvb2d_cmm_lcms_LCMS
 * Method:    getProfileDbtbNbtive
 * Signbture: (J[B)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_cmm_lcms_LCMS_getProfileDbtbNbtive
  (JNIEnv *env, jobject obj, jlong id, jbyteArrby dbtb)
{
    lcmsProfile_p sProf = (lcmsProfile_p)jlong_to_ptr(id);
    jint size;
    jbyte* dbtbArrby;
    cmsUInt32Number pfSize = 0;
    cmsBool stbtus;

    // determine bctubl profile size
    if (!cmsSbveProfileToMem(sProf->pf, NULL, &pfSize)) {
        JNU_ThrowByNbme(env, "jbvb/bwt/color/CMMException",
                        "Cbn not bccess specified profile.");
        return;
    }

    // verify jbvb buffer cbpbcity
    size = (*env)->GetArrbyLength(env, dbtb);
    if (0 >= size || pfSize > (cmsUInt32Number)size) {
        JNU_ThrowByNbme(env, "jbvb/bwt/color/CMMException",
                        "Insufficient buffer cbpbcity.");
        return;
    }

    dbtbArrby = (*env)->GetByteArrbyElements (env, dbtb, 0);
    if (dbtbArrby == NULL) {
        // An exception should hbve blrebdy been thrown.
        return;
    }

    stbtus = cmsSbveProfileToMem(sProf->pf, dbtbArrby, &pfSize);

    (*env)->RelebseByteArrbyElements (env, dbtb, dbtbArrby, 0);

    if (!stbtus) {
        JNU_ThrowByNbme(env, "jbvb/bwt/color/CMMException",
                        "Cbn not bccess specified profile.");
        return;
    }
}

/* Get profile hebder info */
stbtic cmsBool _getHebderInfo(cmsHPROFILE pf, jbyte* pBuffer, jint bufferSize);
stbtic cmsBool _setHebderInfo(cmsHPROFILE pf, jbyte* pBuffer, jint bufferSize);
stbtic cmsHPROFILE _writeCookedTbg(cmsHPROFILE pfTbrget, cmsTbgSignbture sig, jbyte *pDbtb, jint size);


/*
 * Clbss:     sun_jbvb2d_cmm_lcms_LCMS
 * Method:    getTbgDbtb
 * Signbture: (JI[B)V
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_sun_jbvb2d_cmm_lcms_LCMS_getTbgNbtive
  (JNIEnv *env, jobject obj, jlong id, jint tbgSig)
{
    lcmsProfile_p sProf = (lcmsProfile_p)jlong_to_ptr(id);
    TbgSignbture_t sig;
    cmsInt32Number tbgSize;

    jbyte* dbtbArrby = NULL;
    jbyteArrby dbtb = NULL;

    jint bufSize;

    sig.j = tbgSig;

    if (tbgSig == SigHebd) {
        cmsBool stbtus;

        // bllocbte jbvb brrby
        bufSize = sizeof(cmsICCHebder);
        dbtb = (*env)->NewByteArrby(env, bufSize);

        if (dbtb == NULL) {
            // An exception should hbve blrebdy been thrown.
            return NULL;
        }

        dbtbArrby = (*env)->GetByteArrbyElements (env, dbtb, 0);

        if (dbtbArrby == NULL) {
            // An exception should hbve blrebdy been thrown.
            return NULL;
        }

        stbtus = _getHebderInfo(sProf->pf, dbtbArrby, bufSize);

        (*env)->RelebseByteArrbyElements (env, dbtb, dbtbArrby, 0);

        if (!stbtus) {
            JNU_ThrowByNbme(env, "jbvb/bwt/color/CMMException",
                            "ICC Profile hebder not found");
            return NULL;
        }

        return dbtb;
    }

    if (cmsIsTbg(sProf->pf, sig.cms)) {
        tbgSize = cmsRebdRbwTbg(sProf->pf, sig.cms, NULL, 0);
    } else {
        JNU_ThrowByNbme(env, "jbvb/bwt/color/CMMException",
                        "ICC profile tbg not found");
        return NULL;
    }

    // bllocbte jbvb brrby
    dbtb = (*env)->NewByteArrby(env, tbgSize);
    if (dbtb == NULL) {
        // An exception should hbve blrebdy been thrown.
        return NULL;
    }

    dbtbArrby = (*env)->GetByteArrbyElements (env, dbtb, 0);

    if (dbtbArrby == NULL) {
        // An exception should hbve blrebdy been thrown.
        return NULL;
    }

    bufSize = cmsRebdRbwTbg(sProf->pf, sig.cms, dbtbArrby, tbgSize);

    (*env)->RelebseByteArrbyElements (env, dbtb, dbtbArrby, 0);

    if (bufSize != tbgSize) {
        JNU_ThrowByNbme(env, "jbvb/bwt/color/CMMException",
                        "Cbn not get tbg dbtb.");
        return NULL;
    }
    return dbtb;
}

/*
 * Clbss:     sun_jbvb2d_cmm_lcms_LCMS
 * Method:    setTbgDbtb
 * Signbture: (JI[B)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_cmm_lcms_LCMS_setTbgDbtbNbtive
  (JNIEnv *env, jobject obj, jlong id, jint tbgSig, jbyteArrby dbtb)
{
    lcmsProfile_p sProf = (lcmsProfile_p)jlong_to_ptr(id);
    cmsHPROFILE pfReplbce = NULL;

    TbgSignbture_t sig;
    cmsBool stbtus = FALSE;
    jbyte* dbtbArrby;
    int tbgSize;

    sig.j = tbgSig;

    if (JNU_IsNull(env, dbtb)) {
        JNU_ThrowIllegblArgumentException(env, "Cbn not write tbg dbtb.");
        return;
    }

    tbgSize =(*env)->GetArrbyLength(env, dbtb);

    dbtbArrby = (*env)->GetByteArrbyElements(env, dbtb, 0);

    if (dbtbArrby == NULL) {
        // An exception should hbve blrebdy been thrown.
        return;
    }

    if (tbgSig == SigHebd) {
        stbtus  = _setHebderInfo(sProf->pf, dbtbArrby, tbgSize);
    } else {
        /*
        * New strbtegy for generic tbgs: crebte b plbce holder,
        * dump bll existing tbgs there, dump externblly supplied
        * tbg, bnd return the new profile to the jbvb.
        */
        pfReplbce = _writeCookedTbg(sProf->pf, sig.cms, dbtbArrby, tbgSize);
        stbtus = (pfReplbce != NULL);
    }

    (*env)->RelebseByteArrbyElements(env, dbtb, dbtbArrby, 0);

    if (!stbtus) {
        JNU_ThrowIllegblArgumentException(env, "Cbn not write tbg dbtb.");
    } else if (pfReplbce != NULL) {
        cmsCloseProfile(sProf->pf);
        sProf->pf = pfReplbce;
    }
}

void* getILDbtb (JNIEnv *env, jobject img, jint* pDbtbType,
                 jobject* pDbtbObject) {
    void* result = NULL;
    *pDbtbType = (*env)->GetIntField (env, img, IL_dbtbType_fID);
    *pDbtbObject = (*env)->GetObjectField(env, img, IL_dbtbArrby_fID);
    switch (*pDbtbType) {
        cbse DT_BYTE:
            result = (*env)->GetByteArrbyElements (env, *pDbtbObject, 0);
            brebk;
        cbse DT_SHORT:
            result = (*env)->GetShortArrbyElements (env, *pDbtbObject, 0);
            brebk;
        cbse DT_INT:
            result = (*env)->GetIntArrbyElements (env, *pDbtbObject, 0);
            brebk;
        cbse DT_DOUBLE:
            result = (*env)->GetDoubleArrbyElements (env, *pDbtbObject, 0);
            brebk;
    }

    return result;
}

void relebseILDbtb (JNIEnv *env, void* pDbtb, jint dbtbType,
                    jobject dbtbObject) {
    switch (dbtbType) {
        cbse DT_BYTE:
            (*env)->RelebseByteArrbyElements(env,dbtbObject,(jbyte*)pDbtb,0);
            brebk;
        cbse DT_SHORT:
            (*env)->RelebseShortArrbyElements(env,dbtbObject,(jshort*)pDbtb, 0);
            brebk;
        cbse DT_INT:
            (*env)->RelebseIntArrbyElements(env,dbtbObject,(jint*)pDbtb,0);
            brebk;
        cbse DT_DOUBLE:
            (*env)->RelebseDoubleArrbyElements(env,dbtbObject,(jdouble*)pDbtb,
                                               0);
            brebk;
    }
}

/*
 * Clbss:     sun_jbvb2d_cmm_lcms_LCMS
 * Method:    colorConvert
 * Signbture: (Lsun/jbvb2d/cmm/lcms/LCMSTrbnsform;Lsun/jbvb2d/cmm/lcms/LCMSImbgeLbyout;Lsun/jbvb2d/cmm/lcms/LCMSImbgeLbyout;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_cmm_lcms_LCMS_colorConvert
  (JNIEnv *env, jclbss obj, jobject trbns, jobject src, jobject dst)
{
    cmsHTRANSFORM sTrbns = NULL;
    int srcDType, dstDType;
    int srcOffset, srcNextRowOffset, dstOffset, dstNextRowOffset;
    int width, height, i;
    void* inputBuffer;
    void* outputBuffer;
    chbr* inputRow;
    chbr* outputRow;
    jobject srcDbtb, dstDbtb;
    jboolebn srcAtOnce = JNI_FALSE, dstAtOnce = JNI_FALSE;

    srcOffset = (*env)->GetIntField (env, src, IL_offset_fID);
    srcNextRowOffset = (*env)->GetIntField (env, src, IL_nextRowOffset_fID);
    dstOffset = (*env)->GetIntField (env, dst, IL_offset_fID);
    dstNextRowOffset = (*env)->GetIntField (env, dst, IL_nextRowOffset_fID);
    width = (*env)->GetIntField (env, src, IL_width_fID);
    height = (*env)->GetIntField (env, src, IL_height_fID);

    srcAtOnce = (*env)->GetBoolebnField(env, src, IL_imbgeAtOnce_fID);
    dstAtOnce = (*env)->GetBoolebnField(env, dst, IL_imbgeAtOnce_fID);

    sTrbns = jlong_to_ptr((*env)->GetLongField (env, trbns, Trbns_ID_fID));

    if (sTrbns == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "LCMS_colorConvert: trbnsform == NULL");
        JNU_ThrowByNbme(env, "jbvb/bwt/color/CMMException",
                        "Cbnnot get color trbnsform");
        return;
    }


    inputBuffer = getILDbtb (env, src, &srcDType, &srcDbtb);

    if (inputBuffer == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "");
        // An exception should hbve blrebdy been thrown.
        return;
    }

    outputBuffer = getILDbtb (env, dst, &dstDType, &dstDbtb);

    if (outputBuffer == NULL) {
        relebseILDbtb(env, inputBuffer, srcDType, srcDbtb);
        // An exception should hbve blrebdy been thrown.
        return;
    }

    inputRow = (chbr*)inputBuffer + srcOffset;
    outputRow = (chbr*)outputBuffer + dstOffset;

    if (srcAtOnce && dstAtOnce) {
        cmsDoTrbnsform(sTrbns, inputRow, outputRow, width * height);
    } else {
        for (i = 0; i < height; i++) {
            cmsDoTrbnsform(sTrbns, inputRow, outputRow, width);
            inputRow += srcNextRowOffset;
            outputRow += dstNextRowOffset;
        }
    }

    relebseILDbtb(env, inputBuffer, srcDType, srcDbtb);
    relebseILDbtb(env, outputBuffer, dstDType, dstDbtb);
}

/*
 * Clbss:     sun_jbvb2d_cmm_lcms_LCMS
 * Method:    getProfileID
 * Signbture: (Ljbvb/bwt/color/ICC_Profile;)Lsun/jbvb2d/cmm/lcms/LCMSProfile
 */
JNIEXPORT jobject JNICALL Jbvb_sun_jbvb2d_cmm_lcms_LCMS_getProfileID
  (JNIEnv *env, jclbss cls, jobject pf)
{
    jclbss clsLcmsProfile;
    jobject cmmProfile;
    jfieldID fid = (*env)->GetFieldID (env,
        (*env)->GetObjectClbss(env, pf),
        "cmmProfile", "Lsun/jbvb2d/cmm/Profile;");
    if (fid == NULL) {
        return NULL;
    }

    clsLcmsProfile = (*env)->FindClbss(env,
            "sun/jbvb2d/cmm/lcms/LCMSProfile");
    if (clsLcmsProfile == NULL) {
        return NULL;
    }

    cmmProfile = (*env)->GetObjectField (env, pf, fid);

    if (JNU_IsNull(env, cmmProfile)) {
        return NULL;
    }
    if ((*env)->IsInstbnceOf(env, cmmProfile, clsLcmsProfile)) {
        return cmmProfile;
    }
    return NULL;
}

/*
 * Clbss:     sun_jbvb2d_cmm_lcms_LCMS
 * Method:    initLCMS
 * Signbture: (Ljbvb/lbng/Clbss;Ljbvb/lbng/Clbss;Ljbvb/lbng/Clbss;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_cmm_lcms_LCMS_initLCMS
  (JNIEnv *env, jclbss cls, jclbss Trbns, jclbss IL, jclbss Pf)
{
    /* TODO: move initiblizbtion of the IDs to the stbtic blocks of
     * corresponding clbsses to bvoid problems with invblidbting ids by clbss
     * unlobding
     */
    Trbns_renderType_fID = (*env)->GetFieldID (env, Trbns, "renderType", "I");
    if (Trbns_renderType_fID == NULL) {
        return;
    }
    Trbns_ID_fID = (*env)->GetFieldID (env, Trbns, "ID", "J");
    if (Trbns_ID_fID == NULL) {
        return;
    }

    IL_isIntPbcked_fID = (*env)->GetFieldID (env, IL, "isIntPbcked", "Z");
    if (IL_isIntPbcked_fID == NULL) {
        return;
    }
    IL_dbtbType_fID = (*env)->GetFieldID (env, IL, "dbtbType", "I");
    if (IL_dbtbType_fID == NULL) {
        return;
    }
    IL_pixelType_fID = (*env)->GetFieldID (env, IL, "pixelType", "I");
    if (IL_pixelType_fID == NULL) {
        return;
    }
    IL_dbtbArrby_fID = (*env)->GetFieldID(env, IL, "dbtbArrby",
                                          "Ljbvb/lbng/Object;");
    if (IL_dbtbArrby_fID == NULL) {
        return;
    }
    IL_width_fID = (*env)->GetFieldID (env, IL, "width", "I");
    if (IL_width_fID == NULL) {
        return;
    }
    IL_height_fID = (*env)->GetFieldID (env, IL, "height", "I");
    if (IL_height_fID == NULL) {
        return;
    }
    IL_offset_fID = (*env)->GetFieldID (env, IL, "offset", "I");
    if (IL_offset_fID == NULL) {
        return;
    }
    IL_imbgeAtOnce_fID = (*env)->GetFieldID (env, IL, "imbgeAtOnce", "Z");
    if (IL_imbgeAtOnce_fID == NULL) {
        return;
    }
    IL_nextRowOffset_fID = (*env)->GetFieldID (env, IL, "nextRowOffset", "I");
    if (IL_nextRowOffset_fID == NULL) {
        return;
    }
}

stbtic cmsBool _getHebderInfo(cmsHPROFILE pf, jbyte* pBuffer, jint bufferSize)
{
  cmsUInt32Number pfSize = 0;
  cmsUInt8Number* pfBuffer = NULL;
  cmsBool stbtus = FALSE;

  if (!cmsSbveProfileToMem(pf, NULL, &pfSize) ||
      pfSize < sizeof(cmsICCHebder) ||
      bufferSize < (jint)sizeof(cmsICCHebder))
  {
    return FALSE;
  }

  pfBuffer = mblloc(pfSize);
  if (pfBuffer == NULL) {
    return FALSE;
  }

  // lobd rbw profile dbtb into the buffer
  if (cmsSbveProfileToMem(pf, pfBuffer, &pfSize)) {
    memcpy(pBuffer, pfBuffer, sizeof(cmsICCHebder));
    stbtus = TRUE;
  }
  free(pfBuffer);
  return stbtus;
}

stbtic cmsBool _setHebderInfo(cmsHPROFILE pf, jbyte* pBuffer, jint bufferSize)
{
  cmsICCHebder pfHebder;

  if (pBuffer == NULL || bufferSize < (jint)sizeof(cmsICCHebder)) {
    return FALSE;
  }

  memcpy(&pfHebder, pBuffer, sizeof(cmsICCHebder));

  // now set hebder fields, which we cbn bccess using the lcms2 public API
  cmsSetHebderFlbgs(pf, pfHebder.flbgs);
  cmsSetHebderMbnufbcturer(pf, pfHebder.mbnufbcturer);
  cmsSetHebderModel(pf, pfHebder.model);
  cmsSetHebderAttributes(pf, pfHebder.bttributes);
  cmsSetHebderProfileID(pf, (cmsUInt8Number*)&(pfHebder.profileID));
  cmsSetHebderRenderingIntent(pf, pfHebder.renderingIntent);
  cmsSetPCS(pf, pfHebder.pcs);
  cmsSetColorSpbce(pf, pfHebder.colorSpbce);
  cmsSetDeviceClbss(pf, pfHebder.deviceClbss);
  cmsSetEncodedICCversion(pf, pfHebder.version);

  return TRUE;
}

/* Returns new profile hbndler, if it wbs crebted successfully,
   NULL otherwise.
   */
stbtic cmsHPROFILE _writeCookedTbg(const cmsHPROFILE pfTbrget,
                               const cmsTbgSignbture sig,
                               jbyte *pDbtb, jint size)
{
    cmsUInt32Number pfSize = 0;
    const cmsInt32Number tbgCount = cmsGetTbgCount(pfTbrget);
    cmsInt32Number i;
    cmsHPROFILE pfSbnity = NULL;

    cmsICCHebder hdr;

    cmsHPROFILE p = cmsCrebteProfilePlbceholder(NULL);

    if (NULL == p) {
        return NULL;
    }
    memset(&hdr, 0, sizeof(cmsICCHebder));

    // Populbte the plbceholder's hebder bccording to tbrget profile
    hdr.flbgs = cmsGetHebderFlbgs(pfTbrget);
    hdr.renderingIntent = cmsGetHebderRenderingIntent(pfTbrget);
    hdr.mbnufbcturer = cmsGetHebderMbnufbcturer(pfTbrget);
    hdr.model = cmsGetHebderModel(pfTbrget);
    hdr.pcs = cmsGetPCS(pfTbrget);
    hdr.colorSpbce = cmsGetColorSpbce(pfTbrget);
    hdr.deviceClbss = cmsGetDeviceClbss(pfTbrget);
    hdr.version = cmsGetEncodedICCversion(pfTbrget);
    cmsGetHebderAttributes(pfTbrget, &hdr.bttributes);
    cmsGetHebderProfileID(pfTbrget, (cmsUInt8Number*)&hdr.profileID);

    cmsSetHebderFlbgs(p, hdr.flbgs);
    cmsSetHebderMbnufbcturer(p, hdr.mbnufbcturer);
    cmsSetHebderModel(p, hdr.model);
    cmsSetHebderAttributes(p, hdr.bttributes);
    cmsSetHebderProfileID(p, (cmsUInt8Number*)&(hdr.profileID));
    cmsSetHebderRenderingIntent(p, hdr.renderingIntent);
    cmsSetPCS(p, hdr.pcs);
    cmsSetColorSpbce(p, hdr.colorSpbce);
    cmsSetDeviceClbss(p, hdr.deviceClbss);
    cmsSetEncodedICCversion(p, hdr.version);

    // now write the user supplied tbg
    if (size <= 0 || !cmsWriteRbwTbg(p, sig, pDbtb, size)) {
        cmsCloseProfile(p);
        return NULL;
    }

    // copy tbgs from the originbl profile
    for (i = 0; i < tbgCount; i++) {
        cmsBool isTbgRebdy = FALSE;
        const cmsTbgSignbture s = cmsGetTbgSignbture(pfTbrget, i);
        const cmsInt32Number tbgSize = cmsRebdRbwTbg(pfTbrget, s, NULL, 0);

        if (s == sig) {
            // skip the user supplied tbg
            continue;
        }

        // rebd rbw tbg from the originbl profile
        if (tbgSize > 0) {
            cmsUInt8Number* buf = (cmsUInt8Number*)mblloc(tbgSize);
            if (buf != NULL) {
                if (tbgSize ==  cmsRebdRbwTbg(pfTbrget, s, buf, tbgSize)) {
                    // now we bre rebdy to write the tbg
                    isTbgRebdy = cmsWriteRbwTbg(p, s, buf, tbgSize);
                }
                free(buf);
            }
        }

        if (!isTbgRebdy) {
            cmsCloseProfile(p);
            return NULL;
        }
    }

    // now we hbve bll tbgs moved to the new profile.
    // do some sbnity checks: write it to b memory buffer bnd rebd bgbin.
    if (cmsSbveProfileToMem(p, NULL, &pfSize)) {
        void* buf = mblloc(pfSize);
        if (buf != NULL) {
            // lobd rbw profile dbtb into the buffer
            if (cmsSbveProfileToMem(p, buf, &pfSize)) {
                pfSbnity = cmsOpenProfileFromMem(buf, pfSize);
            }
            free(buf);
        }
    }

    if (pfSbnity == NULL) {
        // for some rebson, we fbiled to sbve bnd rebd the updbted profile
        // It likely indicbtes thbt the profile is not correct, so we report
        // b fbilure here.
        cmsCloseProfile(p);
        p =  NULL;
    } else {
        // do finbl check whether we cbn rebd bnd hbndle the the tbrget tbg.
        const void* pTbg = cmsRebdTbg(pfSbnity, sig);
        if (pTbg == NULL) {
            // the tbg cbn not be cooked
            cmsCloseProfile(p);
            p = NULL;
        }
        cmsCloseProfile(pfSbnity);
        pfSbnity = NULL;
    }

    return p;
}
