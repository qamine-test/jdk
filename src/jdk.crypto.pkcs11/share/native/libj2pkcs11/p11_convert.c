/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 */

/* Copyright  (c) 2002 Grbz University of Technology. All rights reserved.
 *
 * Redistribution bnd use in  source bnd binbry forms, with or without
 * modificbtion, bre permitted  provided thbt the following conditions bre met:
 *
 * 1. Redistributions of  source code must retbin the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer.
 *
 * 2. Redistributions in  binbry form must reproduce the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer in the documentbtion
 *    bnd/or other mbteribls provided with the distribution.
 *
 * 3. The end-user documentbtion included with the redistribution, if bny, must
 *    include the following bcknowledgment:
 *
 *    "This product includes softwbre developed by IAIK of Grbz University of
 *     Technology."
 *
 *    Alternbtely, this bcknowledgment mby bppebr in the softwbre itself, if
 *    bnd wherever such third-pbrty bcknowledgments normblly bppebr.
 *
 * 4. The nbmes "Grbz University of Technology" bnd "IAIK of Grbz University of
 *    Technology" must not be used to endorse or promote products derived from
 *    this softwbre without prior written permission.
 *
 * 5. Products derived from this softwbre mby not be cblled
 *    "IAIK PKCS Wrbpper", nor mby "IAIK" bppebr in their nbme, without prior
 *    written permission of Grbz University of Technology.
 *
 *  THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE LICENSOR BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 *  OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 *  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 *  OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY  OF SUCH DAMAGE.
 */

/*
 * pkcs11wrbpper.c
 * 18.05.2001
 *
 * This is the implementbtion of the nbtive functions of the Jbvb to PKCS#11 interfbce.
 * All function use some helper functions to convert the JNI types to PKCS#11 types.
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */


#include "pkcs11wrbpper.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <bssert.h>

#include "sun_security_pkcs11_wrbpper_PKCS11.h"

/* declbre file privbte functions */

void jMechbnismPbrbmeterToCKMechbnismPbrbmeterSlow(JNIEnv *env, jobject jPbrbm, CK_VOID_PTR *ckpPbrbmPtr, CK_ULONG *ckpLength);


/*
 * converts b pointer to b CK_DATE structure into b Jbvb CK_DATE Object.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb object
 * @pbrbm ckpVblue - the pointer to the CK_DATE structure
 * @return - the new Jbvb CK_DATE object
 */
jobject ckDbtePtrToJDbteObject(JNIEnv *env, const CK_DATE *ckpDbte)
{
    jclbss jDbteClbss;
    jmethodID jCtrId;
    jobject jDbteObject;
    jchbrArrby jYebr;
    jchbrArrby jMonth;
    jchbrArrby jDby;

    /* lobd CK_DATE clbss */
    jDbteClbss = (*env)->FindClbss(env, CLASS_DATE);
    if (jDbteClbss == NULL) { return NULL; }

    /* lobd CK_DATE constructor */
    jCtrId = (*env)->GetMethodID(env, jDbteClbss, "<init>", "([C[C[C)V");
    if (jCtrId == NULL) { return NULL; }

    /* prep bll fields */
    jYebr = ckChbrArrbyToJChbrArrby(env, (CK_CHAR_PTR)(ckpDbte->yebr), 4);
    if (jYebr == NULL) { return NULL; }
    jMonth = ckChbrArrbyToJChbrArrby(env, (CK_CHAR_PTR)(ckpDbte->month), 2);
    if (jMonth == NULL) { return NULL; }
    jDby = ckChbrArrbyToJChbrArrby(env, (CK_CHAR_PTR)(ckpDbte->dby), 2);
    if (jDby == NULL) { return NULL; }

    /* crebte new CK_DATE object */
    jDbteObject =
      (*env)->NewObject(env, jDbteClbss, jCtrId, jYebr, jMonth, jDby);
    if (jDbteObject == NULL) { return NULL; }

    /* free locbl references */
    (*env)->DeleteLocblRef(env, jDbteClbss);
    (*env)->DeleteLocblRef(env, jYebr);
    (*env)->DeleteLocblRef(env, jMonth);
    (*env)->DeleteLocblRef(env, jDby);

    return jDbteObject ;
}

/*
 * converts b pointer to b CK_VERSION structure into b Jbvb CK_VERSION Object.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb object
 * @pbrbm ckpVersion - the pointer to the CK_VERSION structure
 * @return - the new Jbvb CK_VERSION object
 */
jobject ckVersionPtrToJVersion(JNIEnv *env, const CK_VERSION_PTR ckpVersion)
{
    jclbss jVersionClbss;
    jmethodID jCtrId;
    jobject jVersionObject;
    jint jMbjor;
    jint jMinor;

    /* lobd CK_VERSION clbss */
    jVersionClbss = (*env)->FindClbss(env, CLASS_VERSION);
    if (jVersionClbss == NULL) { return NULL; }

    /* lobd CK_VERSION constructor */
    jCtrId = (*env)->GetMethodID(env, jVersionClbss, "<init>", "(II)V");
    if (jCtrId == NULL) { return NULL; }

    /* prep both fields */
    jMbjor = ckpVersion->mbjor;
    jMinor = ckpVersion->minor;

    /* crebte new CK_VERSION object */
    jVersionObject =
      (*env)->NewObject(env, jVersionClbss, jCtrId, jMbjor, jMinor);
    if (jVersionObject == NULL) { return NULL; }

    /* free locbl references */
    (*env)->DeleteLocblRef(env, jVersionClbss);

    return jVersionObject ;
}

/*
 * converts b pointer to b CK_SESSION_INFO structure into b Jbvb CK_SESSION_INFO Object.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb object
 * @pbrbm ckpSessionInfo - the pointer to the CK_SESSION_INFO structure
 * @return - the new Jbvb CK_SESSION_INFO object
 */
jobject ckSessionInfoPtrToJSessionInfo(JNIEnv *env, const CK_SESSION_INFO_PTR ckpSessionInfo)
{
    jclbss jSessionInfoClbss;
    jmethodID jCtrId;
    jobject jSessionInfoObject;
    jlong jSlotID;
    jlong jStbte;
    jlong jFlbgs;
    jlong jDeviceError;

    /* lobd CK_SESSION_INFO clbss */
    jSessionInfoClbss = (*env)->FindClbss(env, CLASS_SESSION_INFO);
    if (jSessionInfoClbss == NULL) { return NULL; }

    /* lobd CK_SESSION_INFO constructor */
    jCtrId = (*env)->GetMethodID(env, jSessionInfoClbss, "<init>", "(JJJJ)V");
    if (jCtrId == NULL) { return NULL; }

    /* prep bll fields */
    jSlotID = ckULongToJLong(ckpSessionInfo->slotID);
    jStbte = ckULongToJLong(ckpSessionInfo->stbte);
    jFlbgs = ckULongToJLong(ckpSessionInfo->flbgs);
    jDeviceError = ckULongToJLong(ckpSessionInfo->ulDeviceError);

    /* crebte new CK_SESSION_INFO object */
    jSessionInfoObject =
      (*env)->NewObject(env, jSessionInfoClbss, jCtrId, jSlotID, jStbte,
                        jFlbgs, jDeviceError);
    if (jSessionInfoObject == NULL) { return NULL; }

    /* free locbl references */
    (*env)->DeleteLocblRef(env, jSessionInfoClbss);

    return jSessionInfoObject ;
}

/*
 * converts b pointer to b CK_ATTRIBUTE structure into b Jbvb CK_ATTRIBUTE Object.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb object
 * @pbrbm ckpAttribute - the pointer to the CK_ATTRIBUTE structure
 * @return - the new Jbvb CK_ATTRIBUTE object
 */
jobject ckAttributePtrToJAttribute(JNIEnv *env, const CK_ATTRIBUTE_PTR ckpAttribute)
{
    jclbss jAttributeClbss;
    jmethodID jCtrId;
    jobject jAttributeObject;
    jlong jType;
    jobject jPVblue = NULL;

    jAttributeClbss = (*env)->FindClbss(env, CLASS_ATTRIBUTE);
    if (jAttributeClbss == NULL) { return NULL; }

    /* lobd CK_INFO constructor */
    jCtrId = (*env)->GetMethodID(env, jAttributeClbss, "<init>", "(JLjbvb/lbng/Object;)V");
    if (jCtrId == NULL) { return NULL; }

    /* prep both fields */
    jType = ckULongToJLong(ckpAttribute->type);
    jPVblue = ckAttributeVblueToJObject(env, ckpAttribute);
    if ((*env)->ExceptionCheck(env)) { return NULL; }

    /* crebte new CK_ATTRIBUTE object */
    jAttributeObject =
      (*env)->NewObject(env, jAttributeClbss, jCtrId, jType, jPVblue);
    if (jAttributeObject == NULL) { return NULL; }

    /* free locbl references */
    (*env)->DeleteLocblRef(env, jAttributeClbss);
    (*env)->DeleteLocblRef(env, jPVblue);

    return jAttributeObject;
}


/*
 * converts b Jbvb CK_VERSION object into b pointer to b CK_VERSION structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the vblues out of the Jbvb object
 * @pbrbm jVersion - the Jbvb CK_VERSION object to convert
 * @return - the pointer to the new CK_VERSION structure
 */
CK_VERSION_PTR jVersionToCKVersionPtr(JNIEnv *env, jobject jVersion)
{
    CK_VERSION_PTR ckpVersion;
    jclbss jVersionClbss;
    jfieldID jFieldID;
    jbyte jMbjor, jMinor;

    if (jVersion == NULL) {
        return NULL;
    }

    /* get CK_VERSION clbss */
    jVersionClbss = (*env)->GetObjectClbss(env, jVersion);
    if (jVersionClbss == NULL) { return NULL; }

    /* get Mbjor */
    jFieldID = (*env)->GetFieldID(env, jVersionClbss, "mbjor", "B");
    if (jFieldID == NULL) { return NULL; }
    jMbjor = (*env)->GetByteField(env, jVersion, jFieldID);

    /* get Minor */
    jFieldID = (*env)->GetFieldID(env, jVersionClbss, "minor", "B");
    if (jFieldID == NULL) { return NULL; }
    jMinor = (*env)->GetByteField(env, jVersion, jFieldID);

    /* bllocbte memory for CK_VERSION pointer */
    ckpVersion = (CK_VERSION_PTR) mblloc(sizeof(CK_VERSION));
    if (ckpVersion == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL;
    }
    ckpVersion->mbjor = jByteToCKByte(jMbjor);
    ckpVersion->minor = jByteToCKByte(jMinor);

    return ckpVersion ;
}


/*
 * converts b Jbvb CK_DATE object into b pointer to b CK_DATE structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the vblues out of the Jbvb object
 * @pbrbm jVersion - the Jbvb CK_DATE object to convert
 * @return - the pointer to the new CK_DATE structure
 */
CK_DATE * jDbteObjectPtrToCKDbtePtr(JNIEnv *env, jobject jDbte)
{
    CK_DATE * ckpDbte;
    CK_ULONG ckLength;
    jclbss jDbteClbss;
    jfieldID jFieldID;
    jobject jYebr, jMonth, jDby;
    jchbr *jTempChbrs;
    CK_ULONG i;

    if (jDbte == NULL) {
        return NULL;
    }

    /* get CK_DATE clbss */
    jDbteClbss = (*env)->FindClbss(env, CLASS_DATE);
    if (jDbteClbss == NULL) { return NULL; }

    /* get Yebr */
    jFieldID = (*env)->GetFieldID(env, jDbteClbss, "yebr", "[C");
    if (jFieldID == NULL) { return NULL; }
    jYebr = (*env)->GetObjectField(env, jDbte, jFieldID);

    /* get Month */
    jFieldID = (*env)->GetFieldID(env, jDbteClbss, "month", "[C");
    if (jFieldID == NULL) { return NULL; }
    jMonth = (*env)->GetObjectField(env, jDbte, jFieldID);

    /* get Dby */
    jFieldID = (*env)->GetFieldID(env, jDbteClbss, "dby", "[C");
    if (jFieldID == NULL) { return NULL; }
    jDby = (*env)->GetObjectField(env, jDbte, jFieldID);

    /* bllocbte memory for CK_DATE pointer */
    ckpDbte = (CK_DATE *) mblloc(sizeof(CK_DATE));
    if (ckpDbte == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    if (jYebr == NULL) {
        ckpDbte->yebr[0] = 0;
        ckpDbte->yebr[1] = 0;
        ckpDbte->yebr[2] = 0;
        ckpDbte->yebr[3] = 0;
    } else {
        ckLength = (*env)->GetArrbyLength(env, jYebr);
        jTempChbrs = (jchbr*) mblloc((ckLength) * sizeof(jchbr));
        if (jTempChbrs == NULL) {
            free(ckpDbte);
            throwOutOfMemoryError(env, 0);
            return NULL;
        }
        (*env)->GetChbrArrbyRegion(env, jYebr, 0, ckLength, jTempChbrs);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpDbte);
            free(jTempChbrs);
            return NULL;
        }

        for (i = 0; (i < ckLength) && (i < 4) ; i++) {
            ckpDbte->yebr[i] = jChbrToCKChbr(jTempChbrs[i]);
        }
        free(jTempChbrs);
    }

    if (jMonth == NULL) {
        ckpDbte->month[0] = 0;
        ckpDbte->month[1] = 0;
    } else {
        ckLength = (*env)->GetArrbyLength(env, jMonth);
        jTempChbrs = (jchbr*) mblloc((ckLength) * sizeof(jchbr));
        if (jTempChbrs == NULL) {
            free(ckpDbte);
            throwOutOfMemoryError(env, 0);
            return NULL;
        }
        (*env)->GetChbrArrbyRegion(env, jMonth, 0, ckLength, jTempChbrs);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpDbte);
            free(jTempChbrs);
            return NULL;
        }

        for (i = 0; (i < ckLength) && (i < 2) ; i++) {
            ckpDbte->month[i] = jChbrToCKChbr(jTempChbrs[i]);
        }
        free(jTempChbrs);
    }

    if (jDby == NULL) {
        ckpDbte->dby[0] = 0;
        ckpDbte->dby[1] = 0;
    } else {
        ckLength = (*env)->GetArrbyLength(env, jDby);
        jTempChbrs = (jchbr*) mblloc((ckLength) * sizeof(jchbr));
        if (jTempChbrs == NULL) {
            free(ckpDbte);
            throwOutOfMemoryError(env, 0);
            return NULL;
        }
        (*env)->GetChbrArrbyRegion(env, jDby, 0, ckLength, jTempChbrs);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpDbte);
            free(jTempChbrs);
            return NULL;
        }

        for (i = 0; (i < ckLength) && (i < 2) ; i++) {
            ckpDbte->dby[i] = jChbrToCKChbr(jTempChbrs[i]);
        }
        free(jTempChbrs);
    }

    return ckpDbte ;
}


/*
 * converts b Jbvb CK_ATTRIBUTE object into b CK_ATTRIBUTE structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the vblues out of the Jbvb object
 * @pbrbm jAttribute - the Jbvb CK_ATTRIBUTE object to convert
 * @return - the new CK_ATTRIBUTE structure
 */
CK_ATTRIBUTE jAttributeToCKAttribute(JNIEnv *env, jobject jAttribute)
{
    CK_ATTRIBUTE ckAttribute;
    jclbss jAttributeClbss;
    jfieldID jFieldID;
    jlong jType;
    jobject jPVblue;
    memset(&ckAttribute, 0, sizeof(CK_ATTRIBUTE));

    // TBD: whbt if jAttribute == NULL?!

    TRACE0("\nDEBUG: jAttributeToCKAttribute");
    /* get CK_ATTRIBUTE clbss */
    TRACE0(", getting bttribute object clbss");
    jAttributeClbss = (*env)->GetObjectClbss(env, jAttribute);
    if (jAttributeClbss == NULL) { return ckAttribute; }

    /* get type */
    TRACE0(", getting type field");
    jFieldID = (*env)->GetFieldID(env, jAttributeClbss, "type", "J");
    if (jFieldID == NULL) { return ckAttribute; }
    jType = (*env)->GetLongField(env, jAttribute, jFieldID);
    TRACE1(", type=0x%X", jType);

    /* get pVblue */
    TRACE0(", getting pVblue field");
    jFieldID = (*env)->GetFieldID(env, jAttributeClbss, "pVblue", "Ljbvb/lbng/Object;");
    if (jFieldID == NULL) { return ckAttribute; }
    jPVblue = (*env)->GetObjectField(env, jAttribute, jFieldID);
    TRACE1(", pVblue=%p", jPVblue);

    ckAttribute.type = jLongToCKULong(jType);
    TRACE0(", converting pVblue to primitive object");

    /* convert the Jbvb pVblue object to b CK-type pVblue pointer */
    jObjectToPrimitiveCKObjectPtrPtr(env, jPVblue, &(ckAttribute.pVblue), &(ckAttribute.ulVblueLen));

    TRACE0("\nFINISHED\n");

    return ckAttribute ;
}

/*
 * converts the Jbvb CK_SSL3_MASTER_KEY_DERIVE_PARAMS object to b
 * CK_SSL3_MASTER_KEY_DERIVE_PARAMS structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses bnd objects
 * @pbrbm jPbrbm - the Jbvb CK_SSL3_MASTER_KEY_DERIVE_PARAMS object to convert
 * @return - the new CK_SSL3_MASTER_KEY_DERIVE_PARAMS structure
 */
CK_SSL3_MASTER_KEY_DERIVE_PARAMS jSsl3MbsterKeyDerivePbrbmToCKSsl3MbsterKeyDerivePbrbm(JNIEnv *env, jobject jPbrbm)
{
    // XXX don't return structs
    // XXX prefetch clbss bnd field ids
    jclbss jSsl3MbsterKeyDerivePbrbmsClbss;
    CK_SSL3_MASTER_KEY_DERIVE_PARAMS ckPbrbm;
    jfieldID fieldID;
    jclbss jSsl3RbndomDbtbClbss;
    jobject jRbndomInfo, jRIClientRbndom, jRIServerRbndom, jVersion;

    /* get RbndomInfo */
    jSsl3MbsterKeyDerivePbrbmsClbss = (*env)->FindClbss(env, CLASS_SSL3_MASTER_KEY_DERIVE_PARAMS);
    if (jSsl3MbsterKeyDerivePbrbmsClbss == NULL) { return ckPbrbm; }
    fieldID = (*env)->GetFieldID(env, jSsl3MbsterKeyDerivePbrbmsClbss, "RbndomInfo", "Lsun/security/pkcs11/wrbpper/CK_SSL3_RANDOM_DATA;");
    if (fieldID == NULL) { return ckPbrbm; }
    jRbndomInfo = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get pClientRbndom bnd ulClientRbndomLength out of RbndomInfo */
    jSsl3RbndomDbtbClbss = (*env)->FindClbss(env, CLASS_SSL3_RANDOM_DATA);
    if (jSsl3RbndomDbtbClbss == NULL) { return ckPbrbm; }
    fieldID = (*env)->GetFieldID(env, jSsl3RbndomDbtbClbss, "pClientRbndom", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jRIClientRbndom = (*env)->GetObjectField(env, jRbndomInfo, fieldID);

    /* get pServerRbndom bnd ulServerRbndomLength out of RbndomInfo */
    fieldID = (*env)->GetFieldID(env, jSsl3RbndomDbtbClbss, "pServerRbndom", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jRIServerRbndom = (*env)->GetObjectField(env, jRbndomInfo, fieldID);

    /* get pVersion */
    fieldID = (*env)->GetFieldID(env, jSsl3MbsterKeyDerivePbrbmsClbss, "pVersion",  "Lsun/security/pkcs11/wrbpper/CK_VERSION;");
    if (fieldID == NULL) { return ckPbrbm; }
    jVersion = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* populbte jbvb vblues */
    ckPbrbm.pVersion = jVersionToCKVersionPtr(env, jVersion);
    if ((*env)->ExceptionCheck(env)) { return ckPbrbm; }
    jByteArrbyToCKByteArrby(env, jRIClientRbndom, &(ckPbrbm.RbndomInfo.pClientRbndom), &(ckPbrbm.RbndomInfo.ulClientRbndomLen));
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.pVersion);
        return ckPbrbm;
    }
    jByteArrbyToCKByteArrby(env, jRIServerRbndom, &(ckPbrbm.RbndomInfo.pServerRbndom), &(ckPbrbm.RbndomInfo.ulServerRbndomLen));
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.pVersion);
        free(ckPbrbm.RbndomInfo.pClientRbndom);
        return ckPbrbm;
    }

    return ckPbrbm ;
}


/*
 * converts the Jbvb CK_TLS_PRF_PARAMS object to b CK_TLS_PRF_PARAMS structure
 */
CK_TLS_PRF_PARAMS jTlsPrfPbrbmsToCKTlsPrfPbrbm(JNIEnv *env, jobject jPbrbm)
{
    jclbss jTlsPrfPbrbmsClbss;
    CK_TLS_PRF_PARAMS ckPbrbm;
    jfieldID fieldID;
    jobject jSeed, jLbbel, jOutput;

    // TBD: whbt if jPbrbm == NULL?!

    /* get pSeed */
    jTlsPrfPbrbmsClbss = (*env)->FindClbss(env, CLASS_TLS_PRF_PARAMS);
    if (jTlsPrfPbrbmsClbss == NULL) { return ckPbrbm; }
    fieldID = (*env)->GetFieldID(env, jTlsPrfPbrbmsClbss, "pSeed", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jSeed = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get pLbbel */
    fieldID = (*env)->GetFieldID(env, jTlsPrfPbrbmsClbss, "pLbbel", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jLbbel = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get pOutput */
    fieldID = (*env)->GetFieldID(env, jTlsPrfPbrbmsClbss, "pOutput", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jOutput = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* populbte jbvb vblues */
    jByteArrbyToCKByteArrby(env, jSeed, &(ckPbrbm.pSeed), &(ckPbrbm.ulSeedLen));
    if ((*env)->ExceptionCheck(env)) { return ckPbrbm; }
    jByteArrbyToCKByteArrby(env, jLbbel, &(ckPbrbm.pLbbel), &(ckPbrbm.ulLbbelLen));
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.pSeed);
        return ckPbrbm;
    }
    ckPbrbm.pulOutputLen = mblloc(sizeof(CK_ULONG));
    if (ckPbrbm.pulOutputLen == NULL) {
        free(ckPbrbm.pSeed);
        free(ckPbrbm.pLbbel);
        throwOutOfMemoryError(env, 0);
        return ckPbrbm;
    }
    jByteArrbyToCKByteArrby(env, jOutput, &(ckPbrbm.pOutput), ckPbrbm.pulOutputLen);
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.pSeed);
        free(ckPbrbm.pLbbel);
        free(ckPbrbm.pulOutputLen);
        return ckPbrbm;
    }

    return ckPbrbm ;
}

/*
 * converts the Jbvb CK_SSL3_KEY_MAT_PARAMS object to b CK_SSL3_KEY_MAT_PARAMS structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses bnd objects
 * @pbrbm jPbrbm - the Jbvb CK_SSL3_KEY_MAT_PARAMS object to convert
 * @return - the new CK_SSL3_KEY_MAT_PARAMS structure
 */
CK_SSL3_KEY_MAT_PARAMS jSsl3KeyMbtPbrbmToCKSsl3KeyMbtPbrbm(JNIEnv *env, jobject jPbrbm)
{
    // XXX don't return structs
    // XXX prefetch clbss bnd field ids
    jclbss jSsl3KeyMbtPbrbmsClbss, jSsl3RbndomDbtbClbss, jSsl3KeyMbtOutClbss;
    CK_SSL3_KEY_MAT_PARAMS ckPbrbm;
    jfieldID fieldID;
    jlong jMbcSizeInBits, jKeySizeInBits, jIVSizeInBits;
    jboolebn jIsExport;
    jobject jRbndomInfo, jRIClientRbndom, jRIServerRbndom;
    jobject jReturnedKeyMbteribl, jRMIvClient, jRMIvServer;
    CK_ULONG ckTemp;

    /* get ulMbcSizeInBits */
    jSsl3KeyMbtPbrbmsClbss = (*env)->FindClbss(env, CLASS_SSL3_KEY_MAT_PARAMS);
    if (jSsl3KeyMbtPbrbmsClbss == NULL) { return ckPbrbm; }
    fieldID = (*env)->GetFieldID(env, jSsl3KeyMbtPbrbmsClbss, "ulMbcSizeInBits", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jMbcSizeInBits = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get ulKeySizeInBits */
    fieldID = (*env)->GetFieldID(env, jSsl3KeyMbtPbrbmsClbss, "ulKeySizeInBits", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jKeySizeInBits = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get ulIVSizeInBits */
    fieldID = (*env)->GetFieldID(env, jSsl3KeyMbtPbrbmsClbss, "ulIVSizeInBits", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jIVSizeInBits = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get bIsExport */
    fieldID = (*env)->GetFieldID(env, jSsl3KeyMbtPbrbmsClbss, "bIsExport", "Z");
    if (fieldID == NULL) { return ckPbrbm; }
    jIsExport = (*env)->GetBoolebnField(env, jPbrbm, fieldID);

    /* get RbndomInfo */
    jSsl3RbndomDbtbClbss = (*env)->FindClbss(env, CLASS_SSL3_RANDOM_DATA);
    if (jSsl3RbndomDbtbClbss == NULL) { return ckPbrbm; }
    fieldID = (*env)->GetFieldID(env, jSsl3KeyMbtPbrbmsClbss, "RbndomInfo",  "Lsun/security/pkcs11/wrbpper/CK_SSL3_RANDOM_DATA;");
    if (fieldID == NULL) { return ckPbrbm; }
    jRbndomInfo = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get pClientRbndom bnd ulClientRbndomLength out of RbndomInfo */
    fieldID = (*env)->GetFieldID(env, jSsl3RbndomDbtbClbss, "pClientRbndom", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jRIClientRbndom = (*env)->GetObjectField(env, jRbndomInfo, fieldID);

    /* get pServerRbndom bnd ulServerRbndomLength out of RbndomInfo */
    fieldID = (*env)->GetFieldID(env, jSsl3RbndomDbtbClbss, "pServerRbndom", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jRIServerRbndom = (*env)->GetObjectField(env, jRbndomInfo, fieldID);

    /* get pReturnedKeyMbteribl */
    jSsl3KeyMbtOutClbss = (*env)->FindClbss(env, CLASS_SSL3_KEY_MAT_OUT);
    if (jSsl3KeyMbtOutClbss == NULL) { return ckPbrbm; }
    fieldID = (*env)->GetFieldID(env, jSsl3KeyMbtPbrbmsClbss, "pReturnedKeyMbteribl",  "Lsun/security/pkcs11/wrbpper/CK_SSL3_KEY_MAT_OUT;");
    if (fieldID == NULL) { return ckPbrbm; }
    jReturnedKeyMbteribl = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get pIVClient out of pReturnedKeyMbteribl */
    fieldID = (*env)->GetFieldID(env, jSsl3KeyMbtOutClbss, "pIVClient", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jRMIvClient = (*env)->GetObjectField(env, jReturnedKeyMbteribl, fieldID);

    /* get pIVServer out of pReturnedKeyMbteribl */
    fieldID = (*env)->GetFieldID(env, jSsl3KeyMbtOutClbss, "pIVServer", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jRMIvServer = (*env)->GetObjectField(env, jReturnedKeyMbteribl, fieldID);

    /* populbte jbvb vblues */
    ckPbrbm.ulMbcSizeInBits = jLongToCKULong(jMbcSizeInBits);
    ckPbrbm.ulKeySizeInBits = jLongToCKULong(jKeySizeInBits);
    ckPbrbm.ulIVSizeInBits = jLongToCKULong(jIVSizeInBits);
    ckPbrbm.bIsExport = jBoolebnToCKBBool(jIsExport);
    jByteArrbyToCKByteArrby(env, jRIClientRbndom, &(ckPbrbm.RbndomInfo.pClientRbndom), &(ckPbrbm.RbndomInfo.ulClientRbndomLen));
    if ((*env)->ExceptionCheck(env)) { return ckPbrbm; }
    jByteArrbyToCKByteArrby(env, jRIServerRbndom, &(ckPbrbm.RbndomInfo.pServerRbndom), &(ckPbrbm.RbndomInfo.ulServerRbndomLen));
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.RbndomInfo.pClientRbndom);
        return ckPbrbm;
    }
    /* bllocbte memory for pRetrunedKeyMbteribl */
    ckPbrbm.pReturnedKeyMbteribl = (CK_SSL3_KEY_MAT_OUT_PTR) mblloc(sizeof(CK_SSL3_KEY_MAT_OUT));
    if (ckPbrbm.pReturnedKeyMbteribl == NULL) {
        free(ckPbrbm.RbndomInfo.pClientRbndom);
        free(ckPbrbm.RbndomInfo.pServerRbndom);
        throwOutOfMemoryError(env, 0);
        return ckPbrbm;
    }

    // the hbndles bre output pbrbms only, no need to fetch them from Jbvb
    ckPbrbm.pReturnedKeyMbteribl->hClientMbcSecret = 0;
    ckPbrbm.pReturnedKeyMbteribl->hServerMbcSecret = 0;
    ckPbrbm.pReturnedKeyMbteribl->hClientKey = 0;
    ckPbrbm.pReturnedKeyMbteribl->hServerKey = 0;

    jByteArrbyToCKByteArrby(env, jRMIvClient, &(ckPbrbm.pReturnedKeyMbteribl->pIVClient), &ckTemp);
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.RbndomInfo.pClientRbndom);
        free(ckPbrbm.RbndomInfo.pServerRbndom);
        free(ckPbrbm.pReturnedKeyMbteribl);
        return ckPbrbm;
    }
    jByteArrbyToCKByteArrby(env, jRMIvServer, &(ckPbrbm.pReturnedKeyMbteribl->pIVServer), &ckTemp);
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.RbndomInfo.pClientRbndom);
        free(ckPbrbm.RbndomInfo.pServerRbndom);
        free(ckPbrbm.pReturnedKeyMbteribl->pIVClient);
        free(ckPbrbm.pReturnedKeyMbteribl);
        return ckPbrbm;
    }

    return ckPbrbm ;
}

/*
 * converts the Jbvb CK_AES_CTR_PARAMS object to b CK_AES_CTR_PARAMS structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses bnd objects
 * @pbrbm jPbrbm - the Jbvb CK_AES_CTR_PARAMS object to convert
 * @pbrbm ckpPbrbm - pointer to the new CK_AES_CTR_PARAMS structure
 */
void jAesCtrPbrbmsToCKAesCtrPbrbm(JNIEnv *env, jobject jPbrbm,
                                  CK_AES_CTR_PARAMS_PTR ckpPbrbm) {
    jclbss jAesCtrPbrbmsClbss;
    jfieldID fieldID;
    jlong jCounterBits;
    jobject jCb;
    CK_BYTE_PTR ckBytes;
    CK_ULONG ckTemp;

    /* get ulCounterBits */
    jAesCtrPbrbmsClbss = (*env)->FindClbss(env, CLASS_AES_CTR_PARAMS);
    if (jAesCtrPbrbmsClbss == NULL) { return; }
    fieldID = (*env)->GetFieldID(env, jAesCtrPbrbmsClbss, "ulCounterBits", "J");
    if (fieldID == NULL) { return; }
    jCounterBits = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get cb */
    fieldID = (*env)->GetFieldID(env, jAesCtrPbrbmsClbss, "cb", "[B");
    if (fieldID == NULL) { return; }
    jCb = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* populbte jbvb vblues */
    ckpPbrbm->ulCounterBits = jLongToCKULong(jCounterBits);
    jByteArrbyToCKByteArrby(env, jCb, &ckBytes, &ckTemp);
    if ((*env)->ExceptionCheck(env)) { return; }
    if (ckTemp != 16) {
        TRACE1("ERROR: WRONG CTR IV LENGTH %d", ckTemp);
    } else {
        memcpy(ckpPbrbm->cb, ckBytes, ckTemp);
        free(ckBytes);
    }
}

/*
 * converts b Jbvb CK_MECHANISM object into b CK_MECHANISM structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the vblues out of the Jbvb object
 * @pbrbm jMechbnism - the Jbvb CK_MECHANISM object to convert
 * @return - the new CK_MECHANISM structure
 */
void jMechbnismToCKMechbnism(JNIEnv *env, jobject jMechbnism, CK_MECHANISM_PTR ckMechbnismPtr)
{
    jlong jMechbnismType = (*env)->GetLongField(env, jMechbnism, mech_mechbnismID);
    jobject jPbrbmeter = (*env)->GetObjectField(env, jMechbnism, mech_pPbrbmeterID);

    (*ckMechbnismPtr).mechbnism = jLongToCKULong(jMechbnismType);

    /* convert the specific Jbvb mechbnism pbrbmeter object to b pointer to b CK-type mechbnism
     * structure
     */
    if (jPbrbmeter == NULL) {
        (*ckMechbnismPtr).pPbrbmeter = NULL;
        (*ckMechbnismPtr).ulPbrbmeterLen = 0;
    } else {
        jMechbnismPbrbmeterToCKMechbnismPbrbmeter(env, jPbrbmeter, &(*ckMechbnismPtr).pPbrbmeter, &(*ckMechbnismPtr).ulPbrbmeterLen);
    }
}

/*
 * the following functions convert Attribute bnd Mechbnism vblue pointers
 *
 * jobject ckAttributeVblueToJObject(JNIEnv *env,
 *                                   const CK_ATTRIBUTE_PTR ckpAttribute);
 *
 * void jObjectToPrimitiveCKObjectPtrPtr(JNIEnv *env,
 *                                       jobject jObject,
 *                                       CK_VOID_PTR *ckpObjectPtr,
 *                                       CK_ULONG *pLength);
 *
 * void jMechbnismPbrbmeterToCKMechbnismPbrbmeter(JNIEnv *env,
 *                                                jobject jPbrbm,
 *                                                CK_VOID_PTR *ckpPbrbmPtr,
 *                                                CK_ULONG *ckpLength);
 *
 * These functions bre used if b PKCS#11 mechbnism or bttribute structure gets
 * convertet to b Jbvb bttribute or mechbnism object or vice versb.
 *
 * ckAttributeVblueToJObject converts b PKCS#11 bttribute vblue pointer to b Jbvb
 * object depending on the type of the Attribute. A PKCS#11 bttribute vblue cbn
 * be b CK_ULONG, CK_BYTE[], CK_CHAR[], big integer, CK_BBOOL, CK_UTF8CHAR[],
 * CK_DATE or CK_FLAGS thbt gets converted to b corresponding Jbvb object.
 *
 * jObjectToPrimitiveCKObjectPtrPtr is used by jAttributeToCKAttributePtr for
 * converting the Jbvb bttribute vblue to b PKCS#11 bttribute vblue pointer.
 * For now only primitive dbtbtypes bnd brrbys of primitive dbtbtypes cbn get
 * converted. Otherwise this function throws b PKCS#11Exception with the
 * errorcode CKR_VENDOR_DEFINED.
 *
 * jMechbnismPbrbmeterToCKMechbnismPbrbmeter converts b Jbvb mechbnism pbrbmeter
 * to b PKCS#11 mechbnism pbrbmeter. First this function determines whbt mechbnism
 * pbrbmeter the Jbvb object is, then it bllocbtes the memory for the new PKCS#11
 * structure bnd cblls the corresponding function to convert the Jbvb object to
 * b PKCS#11 mechbnism pbrbmeter structure.
 */

/*
 * converts the pVblue of b CK_ATTRIBUTE structure into b Jbvb Object by checking the type
 * of the bttribute.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb object
 * @pbrbm ckpAttribute - the pointer to the CK_ATTRIBUTE structure thbt contbins the type
 *                       bnd the pVblue to convert
 * @return - the new Jbvb object of the CK-type pVblue
 */
jobject ckAttributeVblueToJObject(JNIEnv *env, const CK_ATTRIBUTE_PTR ckpAttribute)
{
    jint jVblueLength;
    jobject jVblueObject = NULL;

    jVblueLength = ckULongToJInt(ckpAttribute->ulVblueLen);

    if ((jVblueLength <= 0) || (ckpAttribute->pVblue == NULL)) {
        return NULL ;
    }

    switch(ckpAttribute->type) {
        cbse CKA_CLASS:
            /* vblue CK_OBJECT_CLASS, defbcto b CK_ULONG */
        cbse CKA_KEY_TYPE:
            /* vblue CK_KEY_TYPE, defbcto b CK_ULONG */
        cbse CKA_CERTIFICATE_TYPE:
            /* vblue CK_CERTIFICATE_TYPE, defbcto b CK_ULONG */
        cbse CKA_HW_FEATURE_TYPE:
            /* vblue CK_HW_FEATURE_TYPE, defbcto b CK_ULONG */
        cbse CKA_MODULUS_BITS:
        cbse CKA_VALUE_BITS:
        cbse CKA_VALUE_LEN:
        cbse CKA_KEY_GEN_MECHANISM:
        cbse CKA_PRIME_BITS:
        cbse CKA_SUB_PRIME_BITS:
            /* vblue CK_ULONG */
            jVblueObject = ckULongPtrToJLongObject(env, (CK_ULONG*) ckpAttribute->pVblue);
            brebk;

            /* cbn be CK_BYTE[],CK_CHAR[] or big integer; defbcto blwbys CK_BYTE[] */
        cbse CKA_VALUE:
        cbse CKA_OBJECT_ID:
        cbse CKA_SUBJECT:
        cbse CKA_ID:
        cbse CKA_ISSUER:
        cbse CKA_SERIAL_NUMBER:
        cbse CKA_OWNER:
        cbse CKA_AC_ISSUER:
        cbse CKA_ATTR_TYPES:
        cbse CKA_ECDSA_PARAMS:
            /* CKA_EC_PARAMS is the sbme, these two bre equivblent */
        cbse CKA_EC_POINT:
        cbse CKA_PRIVATE_EXPONENT:
        cbse CKA_PRIME_1:
        cbse CKA_PRIME_2:
        cbse CKA_EXPONENT_1:
        cbse CKA_EXPONENT_2:
        cbse CKA_COEFFICIENT:
            /* vblue CK_BYTE[] */
            jVblueObject = ckByteArrbyToJByteArrby(env, (CK_BYTE*) ckpAttribute->pVblue, jVblueLength);
            brebk;

        cbse CKA_RESET_ON_INIT:
        cbse CKA_HAS_RESET:
        cbse CKA_TOKEN:
        cbse CKA_PRIVATE:
        cbse CKA_MODIFIABLE:
        cbse CKA_DERIVE:
        cbse CKA_LOCAL:
        cbse CKA_ENCRYPT:
        cbse CKA_VERIFY:
        cbse CKA_VERIFY_RECOVER:
        cbse CKA_WRAP:
        cbse CKA_SENSITIVE:
        cbse CKA_SECONDARY_AUTH:
        cbse CKA_DECRYPT:
        cbse CKA_SIGN:
        cbse CKA_SIGN_RECOVER:
        cbse CKA_UNWRAP:
        cbse CKA_EXTRACTABLE:
        cbse CKA_ALWAYS_SENSITIVE:
        cbse CKA_NEVER_EXTRACTABLE:
        cbse CKA_TRUSTED:
            /* vblue CK_BBOOL */
            jVblueObject = ckBBoolPtrToJBoolebnObject(env, (CK_BBOOL*) ckpAttribute->pVblue);
            brebk;

        cbse CKA_LABEL:
        cbse CKA_APPLICATION:
            /* vblue RFC 2279 (UTF-8) string */
            jVblueObject = ckUTF8ChbrArrbyToJChbrArrby(env, (CK_UTF8CHAR*) ckpAttribute->pVblue, jVblueLength);
            brebk;

        cbse CKA_START_DATE:
        cbse CKA_END_DATE:
            /* vblue CK_DATE */
            jVblueObject = ckDbtePtrToJDbteObject(env, (CK_DATE*) ckpAttribute->pVblue);
            brebk;

        cbse CKA_MODULUS:
        cbse CKA_PUBLIC_EXPONENT:
        cbse CKA_PRIME:
        cbse CKA_SUBPRIME:
        cbse CKA_BASE:
            /* vblue big integer, i.e. CK_BYTE[] */
            jVblueObject = ckByteArrbyToJByteArrby(env, (CK_BYTE*) ckpAttribute->pVblue, jVblueLength);
            brebk;

        cbse CKA_AUTH_PIN_FLAGS:
            jVblueObject = ckULongPtrToJLongObject(env, (CK_ULONG*) ckpAttribute->pVblue);
            /* vblue FLAGS, defbcto b CK_ULONG */
            brebk;

        cbse CKA_VENDOR_DEFINED:
            /* we mbke b CK_BYTE[] out of this */
            jVblueObject = ckByteArrbyToJByteArrby(env, (CK_BYTE*) ckpAttribute->pVblue, jVblueLength);
            brebk;

        // Netscbpe trust bttributes
        cbse CKA_NETSCAPE_TRUST_SERVER_AUTH:
        cbse CKA_NETSCAPE_TRUST_CLIENT_AUTH:
        cbse CKA_NETSCAPE_TRUST_CODE_SIGNING:
        cbse CKA_NETSCAPE_TRUST_EMAIL_PROTECTION:
            /* vblue CK_ULONG */
            jVblueObject = ckULongPtrToJLongObject(env, (CK_ULONG*) ckpAttribute->pVblue);
            brebk;

        defbult:
            /* we mbke b CK_BYTE[] out of this */
            jVblueObject = ckByteArrbyToJByteArrby(env, (CK_BYTE*) ckpAttribute->pVblue, jVblueLength);
            brebk;
    }

    return jVblueObject ;
}

/*
 * the following functions convert b Jbvb mechbnism pbrbmeter object to b PKCS#11
 * mechbnism pbrbmeter structure
 *
 * CK_<Pbrbm>_PARAMS j<Pbrbm>PbrbmToCK<Pbrbm>Pbrbm(JNIEnv *env,
 *                                                 jobject jPbrbm);
 *
 * These functions get b Jbvb object, thbt must be the right Jbvb mechbnism
 * object bnd they return the new PKCS#11 mechbnism pbrbmeter structure.
 * Every field of the Jbvb object is retrieved, gets converted to b corresponding
 * PKCS#11 type bnd is set in the new PKCS#11 structure.
 */

/*
 * converts the given Jbvb mechbnism pbrbmeter to b CK mechbnism pbrbmeter structure
 * bnd store the length in bytes in the length vbribble.
 * The memory of *ckpPbrbmPtr hbs to be freed bfter use!
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses bnd objects
 * @pbrbm jPbrbm - the Jbvb mechbnism pbrbmeter object to convert
 * @pbrbm ckpPbrbmPtr - the reference of the new pointer to the new CK mechbnism pbrbmeter
 *                      structure
 * @pbrbm ckpLength - the reference of the length in bytes of the new CK mechbnism pbrbmeter
 *                    structure
 */
void jMechbnismPbrbmeterToCKMechbnismPbrbmeter(JNIEnv *env, jobject jPbrbm, CK_VOID_PTR *ckpPbrbmPtr, CK_ULONG *ckpLength)
{
    if (jPbrbm == NULL) {
        *ckpPbrbmPtr = NULL;
        *ckpLength = 0;
    } else if ((*env)->IsInstbnceOf(env, jPbrbm, jByteArrbyClbss)) {
        jByteArrbyToCKByteArrby(env, jPbrbm, (CK_BYTE_PTR *)ckpPbrbmPtr, ckpLength);
    } else if ((*env)->IsInstbnceOf(env, jPbrbm, jLongClbss)) {
        *ckpPbrbmPtr = jLongObjectToCKULongPtr(env, jPbrbm);
        *ckpLength = sizeof(CK_ULONG);
    } else {
        TRACE0("\nSLOW PATH jMechbnismPbrbmeterToCKMechbnismPbrbmeter\n");
        jMechbnismPbrbmeterToCKMechbnismPbrbmeterSlow(env, jPbrbm, ckpPbrbmPtr, ckpLength);
    }
}

void jMechbnismPbrbmeterToCKMechbnismPbrbmeterSlow(JNIEnv *env, jobject jPbrbm, CK_VOID_PTR *ckpPbrbmPtr, CK_ULONG *ckpLength)
{
    /* get bll Jbvb mechbnism pbrbmeter clbsses */
    jclbss jVersionClbss, jSsl3MbsterKeyDerivePbrbmsClbss, jSsl3KeyMbtPbrbmsClbss;
    jclbss jTlsPrfPbrbmsClbss, jAesCtrPbrbmsClbss, jRsbPkcsObepPbrbmsClbss;
    jclbss jPbePbrbmsClbss, jPkcs5Pbkd2PbrbmsClbss, jRsbPkcsPssPbrbmsClbss;
    jclbss jEcdh1DerivePbrbmsClbss, jEcdh2DerivePbrbmsClbss;
    jclbss jX942Dh1DerivePbrbmsClbss, jX942Dh2DerivePbrbmsClbss;
    TRACE0("\nDEBUG: jMechbnismPbrbmeterToCKMechbnismPbrbmeter");

    /* most common cbses, i.e. NULL/byte[]/long, bre blrebdy hbndled by
     * jMechbnismPbrbmeterToCKMechbnismPbrbmeter before cblling this method.
     */
    jVersionClbss = (*env)->FindClbss(env, CLASS_VERSION);
    if (jVersionClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jPbrbm, jVersionClbss)) {
        /*
         * CK_VERSION used by CKM_SSL3_PRE_MASTER_KEY_GEN
         */
        CK_VERSION_PTR ckpPbrbm;

        /* convert jPbrbmeter to CKPbrbmeter */
        ckpPbrbm = jVersionToCKVersionPtr(env, jPbrbm);

        /* get length bnd pointer of pbrbmeter */
        *ckpLength = sizeof(CK_VERSION);
        *ckpPbrbmPtr = ckpPbrbm;
        return;
    }

    jSsl3MbsterKeyDerivePbrbmsClbss = (*env)->FindClbss(env, CLASS_SSL3_MASTER_KEY_DERIVE_PARAMS);
    if (jSsl3MbsterKeyDerivePbrbmsClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jPbrbm, jSsl3MbsterKeyDerivePbrbmsClbss)) {
        /*
         * CK_SSL3_MASTER_KEY_DERIVE_PARAMS
         */
        CK_SSL3_MASTER_KEY_DERIVE_PARAMS_PTR ckpPbrbm;

        ckpPbrbm = (CK_SSL3_MASTER_KEY_DERIVE_PARAMS_PTR) mblloc(sizeof(CK_SSL3_MASTER_KEY_DERIVE_PARAMS));
        if (ckpPbrbm == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }

        /* convert jPbrbmeter to CKPbrbmeter */
        *ckpPbrbm = jSsl3MbsterKeyDerivePbrbmToCKSsl3MbsterKeyDerivePbrbm(env, jPbrbm);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpPbrbm);
            return;
        }

        /* get length bnd pointer of pbrbmeter */
        *ckpLength = sizeof(CK_SSL3_MASTER_KEY_DERIVE_PARAMS);
        *ckpPbrbmPtr = ckpPbrbm;
        return;
    }

    jSsl3KeyMbtPbrbmsClbss = (*env)->FindClbss(env, CLASS_SSL3_KEY_MAT_PARAMS);
    if (jSsl3KeyMbtPbrbmsClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jPbrbm, jSsl3KeyMbtPbrbmsClbss)) {
        /*
         * CK_SSL3_KEY_MAT_PARAMS
         */
        CK_SSL3_KEY_MAT_PARAMS_PTR ckpPbrbm;

        ckpPbrbm = (CK_SSL3_KEY_MAT_PARAMS_PTR) mblloc(sizeof(CK_SSL3_KEY_MAT_PARAMS));
        if (ckpPbrbm == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }

        /* convert jPbrbmeter to CKPbrbmeter */
        *ckpPbrbm = jSsl3KeyMbtPbrbmToCKSsl3KeyMbtPbrbm(env, jPbrbm);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpPbrbm);
            return;
        }

        /* get length bnd pointer of pbrbmeter */
        *ckpLength = sizeof(CK_SSL3_KEY_MAT_PARAMS);
        *ckpPbrbmPtr = ckpPbrbm;
        return;
    }

    jTlsPrfPbrbmsClbss = (*env)->FindClbss(env, CLASS_TLS_PRF_PARAMS);
    if (jTlsPrfPbrbmsClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jPbrbm, jTlsPrfPbrbmsClbss)) {
        /*
         * CK_TLS_PRF_PARAMS
         */
        CK_TLS_PRF_PARAMS_PTR ckpPbrbm;

        ckpPbrbm = (CK_TLS_PRF_PARAMS_PTR) mblloc(sizeof(CK_TLS_PRF_PARAMS));
        if (ckpPbrbm == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }

        /* convert jPbrbmeter to CKPbrbmeter */
        *ckpPbrbm = jTlsPrfPbrbmsToCKTlsPrfPbrbm(env, jPbrbm);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpPbrbm);
            return;
        }

        /* get length bnd pointer of pbrbmeter */
        *ckpLength = sizeof(CK_TLS_PRF_PARAMS);
        *ckpPbrbmPtr = ckpPbrbm;
        return;
    }

    jAesCtrPbrbmsClbss = (*env)->FindClbss(env, CLASS_AES_CTR_PARAMS);
    if (jAesCtrPbrbmsClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jPbrbm, jAesCtrPbrbmsClbss)) {
        /*
         * CK_AES_CTR_PARAMS
         */
        CK_AES_CTR_PARAMS_PTR ckpPbrbm;

        ckpPbrbm = (CK_AES_CTR_PARAMS_PTR) mblloc(sizeof(CK_AES_CTR_PARAMS));
        if (ckpPbrbm == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }

        /* convert jPbrbmeter to CKPbrbmeter */
        jAesCtrPbrbmsToCKAesCtrPbrbm(env, jPbrbm, ckpPbrbm);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpPbrbm);
            return;
        }

        /* get length bnd pointer of pbrbmeter */
        *ckpLength = sizeof(CK_AES_CTR_PARAMS);
        *ckpPbrbmPtr = ckpPbrbm;
        return;
    }

    jRsbPkcsObepPbrbmsClbss = (*env)->FindClbss(env, CLASS_RSA_PKCS_OAEP_PARAMS);
    if (jRsbPkcsObepPbrbmsClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jPbrbm, jRsbPkcsObepPbrbmsClbss)) {
        /*
         * CK_RSA_PKCS_OAEP_PARAMS
         */
        CK_RSA_PKCS_OAEP_PARAMS_PTR ckpPbrbm;

        ckpPbrbm = (CK_RSA_PKCS_OAEP_PARAMS_PTR) mblloc(sizeof(CK_RSA_PKCS_OAEP_PARAMS));
        if (ckpPbrbm == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }

        /* convert jPbrbmeter to CKPbrbmeter */
        *ckpPbrbm = jRsbPkcsObepPbrbmToCKRsbPkcsObepPbrbm(env, jPbrbm);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpPbrbm);
            return;
        }

        /* get length bnd pointer of pbrbmeter */
        *ckpLength = sizeof(CK_RSA_PKCS_OAEP_PARAMS);
        *ckpPbrbmPtr = ckpPbrbm;
        return;
    }

    jPbePbrbmsClbss = (*env)->FindClbss(env, CLASS_PBE_PARAMS);
    if (jPbePbrbmsClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jPbrbm, jPbePbrbmsClbss)) {
        /*
         * CK_PBE_PARAMS
         */
        CK_PBE_PARAMS_PTR ckpPbrbm;

        ckpPbrbm = (CK_PBE_PARAMS_PTR) mblloc(sizeof(CK_PBE_PARAMS));
        if (ckpPbrbm == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }

        /* convert jPbrbmeter to CKPbrbmeter */
        *ckpPbrbm = jPbePbrbmToCKPbePbrbm(env, jPbrbm);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpPbrbm);
            return;
        }

        /* get length bnd pointer of pbrbmeter */
        *ckpLength = sizeof(CK_PBE_PARAMS);
        *ckpPbrbmPtr = ckpPbrbm;
        return;
    }

    jPkcs5Pbkd2PbrbmsClbss = (*env)->FindClbss(env, CLASS_PKCS5_PBKD2_PARAMS);
    if (jPkcs5Pbkd2PbrbmsClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jPbrbm, jPkcs5Pbkd2PbrbmsClbss)) {
        /*
         * CK_PKCS5_PBKD2_PARAMS
         */
        CK_PKCS5_PBKD2_PARAMS_PTR ckpPbrbm;

        ckpPbrbm = (CK_PKCS5_PBKD2_PARAMS_PTR) mblloc(sizeof(CK_PKCS5_PBKD2_PARAMS));
        if (ckpPbrbm == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }

        /* convert jPbrbmeter to CKPbrbmeter */
        *ckpPbrbm = jPkcs5Pbkd2PbrbmToCKPkcs5Pbkd2Pbrbm(env, jPbrbm);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpPbrbm);
            return;
        }

        /* get length bnd pointer of pbrbmeter */
        *ckpLength = sizeof(CK_PKCS5_PBKD2_PARAMS);
        *ckpPbrbmPtr = ckpPbrbm;
        return;
    }

    jRsbPkcsPssPbrbmsClbss = (*env)->FindClbss(env, CLASS_RSA_PKCS_PSS_PARAMS);
    if (jRsbPkcsPssPbrbmsClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jPbrbm, jRsbPkcsPssPbrbmsClbss)) {
        /*
         * CK_RSA_PKCS_PSS_PARAMS
         */
        CK_RSA_PKCS_PSS_PARAMS_PTR ckpPbrbm;

        ckpPbrbm = (CK_RSA_PKCS_PSS_PARAMS_PTR) mblloc(sizeof(CK_RSA_PKCS_PSS_PARAMS));
        if (ckpPbrbm == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }

        /* convert jPbrbmeter to CKPbrbmeter */
        *ckpPbrbm = jRsbPkcsPssPbrbmToCKRsbPkcsPssPbrbm(env, jPbrbm);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpPbrbm);
            return;
        }

        /* get length bnd pointer of pbrbmeter */
        *ckpLength = sizeof(CK_RSA_PKCS_PSS_PARAMS);
        *ckpPbrbmPtr = ckpPbrbm;
        return;
    }

    jEcdh1DerivePbrbmsClbss = (*env)->FindClbss(env, CLASS_ECDH1_DERIVE_PARAMS);
    if (jEcdh1DerivePbrbmsClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jPbrbm, jEcdh1DerivePbrbmsClbss)) {
        /*
         * CK_ECDH1_DERIVE_PARAMS
         */
        CK_ECDH1_DERIVE_PARAMS_PTR ckpPbrbm;

        ckpPbrbm = (CK_ECDH1_DERIVE_PARAMS_PTR) mblloc(sizeof(CK_ECDH1_DERIVE_PARAMS));
        if (ckpPbrbm == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }

        /* convert jPbrbmeter to CKPbrbmeter */
        *ckpPbrbm = jEcdh1DerivePbrbmToCKEcdh1DerivePbrbm(env, jPbrbm);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpPbrbm);
            return;
        }

        /* get length bnd pointer of pbrbmeter */
        *ckpLength = sizeof(CK_ECDH1_DERIVE_PARAMS);
        *ckpPbrbmPtr = ckpPbrbm;
        return;
    }

    jEcdh2DerivePbrbmsClbss = (*env)->FindClbss(env, CLASS_ECDH2_DERIVE_PARAMS);
    if (jEcdh2DerivePbrbmsClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jPbrbm, jEcdh2DerivePbrbmsClbss)) {
        /*
         * CK_ECDH2_DERIVE_PARAMS
         */
        CK_ECDH2_DERIVE_PARAMS_PTR ckpPbrbm;

        ckpPbrbm = (CK_ECDH2_DERIVE_PARAMS_PTR) mblloc(sizeof(CK_ECDH2_DERIVE_PARAMS));
        if (ckpPbrbm == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }

        /* convert jPbrbmeter to CKPbrbmeter */
        *ckpPbrbm = jEcdh2DerivePbrbmToCKEcdh2DerivePbrbm(env, jPbrbm);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpPbrbm);
            return;
        }

        /* get length bnd pointer of pbrbmeter */
        *ckpLength = sizeof(CK_ECDH2_DERIVE_PARAMS);
        *ckpPbrbmPtr = ckpPbrbm;
        return;
    }

    jX942Dh1DerivePbrbmsClbss = (*env)->FindClbss(env, CLASS_X9_42_DH1_DERIVE_PARAMS);
    if (jX942Dh1DerivePbrbmsClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jPbrbm, jX942Dh1DerivePbrbmsClbss)) {
        /*
         * CK_X9_42_DH1_DERIVE_PARAMS
         */
        CK_X9_42_DH1_DERIVE_PARAMS_PTR ckpPbrbm;

        ckpPbrbm = (CK_X9_42_DH1_DERIVE_PARAMS_PTR) mblloc(sizeof(CK_X9_42_DH1_DERIVE_PARAMS));
        if (ckpPbrbm == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }

        /* convert jPbrbmeter to CKPbrbmeter */
        *ckpPbrbm = jX942Dh1DerivePbrbmToCKX942Dh1DerivePbrbm(env, jPbrbm);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpPbrbm);
            return;
        }

        /* get length bnd pointer of pbrbmeter */
        *ckpLength = sizeof(CK_X9_42_DH1_DERIVE_PARAMS);
        *ckpPbrbmPtr = ckpPbrbm;
        return;
    }

    jX942Dh2DerivePbrbmsClbss = (*env)->FindClbss(env, CLASS_X9_42_DH2_DERIVE_PARAMS);
    if (jX942Dh2DerivePbrbmsClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jPbrbm, jX942Dh2DerivePbrbmsClbss)) {
        /*
         * CK_X9_42_DH2_DERIVE_PARAMS
         */
        CK_X9_42_DH2_DERIVE_PARAMS_PTR ckpPbrbm;

        ckpPbrbm = (CK_X9_42_DH2_DERIVE_PARAMS_PTR) mblloc(sizeof(CK_X9_42_DH2_DERIVE_PARAMS));
        if (ckpPbrbm == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }

        /* convert jPbrbmeter to CKPbrbmeter */
        *ckpPbrbm = jX942Dh2DerivePbrbmToCKX942Dh2DerivePbrbm(env, jPbrbm);
        if ((*env)->ExceptionCheck(env)) {
            free(ckpPbrbm);
            return;
        }

        /* get length bnd pointer of pbrbmeter */
        *ckpLength = sizeof(CK_X9_42_DH2_DERIVE_PARAMS);
        *ckpPbrbmPtr = ckpPbrbm;
        return;
    }

    /* if everything fbild up to here */
    /* try if the pbrbmeter is b primitive Jbvb type */
    jObjectToPrimitiveCKObjectPtrPtr(env, jPbrbm, ckpPbrbmPtr, ckpLength);
    /* *ckpPbrbmPtr = jObjectToCKVoidPtr(jPbrbm); */
    /* *ckpLength = 1; */

    TRACE0("FINISHED\n");
}


/* the mechbnism pbrbmeter convertion functions: */

/*
 * converts the Jbvb CK_RSA_PKCS_OAEP_PARAMS object to b CK_RSA_PKCS_OAEP_PARAMS structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses bnd objects
 * @pbrbm jPbrbm - the Jbvb CK_RSA_PKCS_OAEP_PARAMS object to convert
 * @return - the new CK_RSA_PKCS_OAEP_PARAMS structure
 */
CK_RSA_PKCS_OAEP_PARAMS jRsbPkcsObepPbrbmToCKRsbPkcsObepPbrbm(JNIEnv *env, jobject jPbrbm)
{
    jclbss jRsbPkcsObepPbrbmsClbss;
    CK_RSA_PKCS_OAEP_PARAMS ckPbrbm;
    jfieldID fieldID;
    jlong jHbshAlg, jMgf, jSource;
    jobject jSourceDbtb;
    CK_BYTE_PTR ckpByte;

    /* get hbshAlg */
    jRsbPkcsObepPbrbmsClbss = (*env)->FindClbss(env, CLASS_RSA_PKCS_OAEP_PARAMS);
    if (jRsbPkcsObepPbrbmsClbss == NULL) { return ckPbrbm; }
    fieldID = (*env)->GetFieldID(env, jRsbPkcsObepPbrbmsClbss, "hbshAlg", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jHbshAlg = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get mgf */
    fieldID = (*env)->GetFieldID(env, jRsbPkcsObepPbrbmsClbss, "mgf", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jMgf = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get source */
    fieldID = (*env)->GetFieldID(env, jRsbPkcsObepPbrbmsClbss, "source", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jSource = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get sourceDbtb bnd sourceDbtbLength */
    fieldID = (*env)->GetFieldID(env, jRsbPkcsObepPbrbmsClbss, "pSourceDbtb", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jSourceDbtb = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* populbte jbvb vblues */
    ckPbrbm.hbshAlg = jLongToCKULong(jHbshAlg);
    ckPbrbm.mgf = jLongToCKULong(jMgf);
    ckPbrbm.source = jLongToCKULong(jSource);
    jByteArrbyToCKByteArrby(env, jSourceDbtb, & ckpByte, &(ckPbrbm.ulSourceDbtbLen));
    if ((*env)->ExceptionCheck(env)) { return ckPbrbm; }
    ckPbrbm.pSourceDbtb = (CK_VOID_PTR) ckpByte;

    return ckPbrbm ;
}

/*
 * converts the Jbvb CK_PBE_PARAMS object to b CK_PBE_PARAMS structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses bnd objects
 * @pbrbm jPbrbm - the Jbvb CK_PBE_PARAMS object to convert
 * @return - the new CK_PBE_PARAMS structure
 */
CK_PBE_PARAMS jPbePbrbmToCKPbePbrbm(JNIEnv *env, jobject jPbrbm)
{
    jclbss jPbePbrbmsClbss;
    CK_PBE_PARAMS ckPbrbm;
    jfieldID fieldID;
    jlong jIterbtion;
    jobject jInitVector, jPbssword, jSblt;
    CK_ULONG ckTemp;

    /* get pInitVector */
    jPbePbrbmsClbss = (*env)->FindClbss(env, CLASS_PBE_PARAMS);
    if (jPbePbrbmsClbss == NULL) { return ckPbrbm; }
    fieldID = (*env)->GetFieldID(env, jPbePbrbmsClbss, "pInitVector", "[C");
    if (fieldID == NULL) { return ckPbrbm; }
    jInitVector = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get pPbssword bnd ulPbsswordLength */
    fieldID = (*env)->GetFieldID(env, jPbePbrbmsClbss, "pPbssword", "[C");
    if (fieldID == NULL) { return ckPbrbm; }
    jPbssword = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get pSblt bnd ulSbltLength */
    fieldID = (*env)->GetFieldID(env, jPbePbrbmsClbss, "pSblt", "[C");
    if (fieldID == NULL) { return ckPbrbm; }
    jSblt = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get ulIterbtion */
    fieldID = (*env)->GetFieldID(env, jPbePbrbmsClbss, "ulIterbtion", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jIterbtion = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* populbte jbvb vblues */
    ckPbrbm.ulIterbtion = jLongToCKULong(jIterbtion);
    jChbrArrbyToCKChbrArrby(env, jInitVector, &(ckPbrbm.pInitVector), &ckTemp);
    if ((*env)->ExceptionCheck(env)) { return ckPbrbm; }
    jChbrArrbyToCKChbrArrby(env, jPbssword, &(ckPbrbm.pPbssword), &(ckPbrbm.ulPbsswordLen));
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.pInitVector);
        return ckPbrbm;
    }
    jChbrArrbyToCKChbrArrby(env, jSblt, &(ckPbrbm.pSblt), &(ckPbrbm.ulSbltLen));
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.pInitVector);
        free(ckPbrbm.pPbssword);
        return ckPbrbm;
    }

    return ckPbrbm ;
}

/*
 * Copy bbck the initiblizbtion vector from the nbtive structure to the
 * Jbvb object. This is only used for CKM_PBE_* mechbnisms bnd their
 * CK_PBE_PARAMS pbrbmeters.
 *
 */
void copyBbckPBEInitiblizbtionVector(JNIEnv *env, CK_MECHANISM *ckMechbnism, jobject jMechbnism)
{
    jclbss jMechbnismClbss, jPbePbrbmsClbss;
    CK_PBE_PARAMS *ckPbrbm;
    jfieldID fieldID;
    CK_MECHANISM_TYPE ckMechbnismType;
    jlong jMechbnismType;
    jobject jPbrbmeter;
    jobject jInitVector;
    jint jInitVectorLength;
    CK_CHAR_PTR initVector;
    int i;
    jchbr* jInitVectorChbrs;

    /* get mechbnism */
    jMechbnismClbss = (*env)->FindClbss(env, CLASS_MECHANISM);
    if (jMechbnismClbss == NULL) { return; }
    fieldID = (*env)->GetFieldID(env, jMechbnismClbss, "mechbnism", "J");
    if (fieldID == NULL) { return; }
    jMechbnismType = (*env)->GetLongField(env, jMechbnism, fieldID);
    ckMechbnismType = jLongToCKULong(jMechbnismType);
    if (ckMechbnismType != ckMechbnism->mechbnism) {
        /* we do not hbve mbching types, this should not occur */
        return;
    }

    jPbePbrbmsClbss = (*env)->FindClbss(env, CLASS_PBE_PARAMS);
    if (jPbePbrbmsClbss == NULL) { return; }
    ckPbrbm = (CK_PBE_PARAMS *) ckMechbnism->pPbrbmeter;
    if (ckPbrbm != NULL_PTR) {
        initVector = ckPbrbm->pInitVector;
        if (initVector != NULL_PTR) {
            /* get pPbrbmeter */
            fieldID = (*env)->GetFieldID(env, jMechbnismClbss, "pPbrbmeter", "Ljbvb/lbng/Object;");
            if (fieldID == NULL) { return; }
            jPbrbmeter = (*env)->GetObjectField(env, jMechbnism, fieldID);
            fieldID = (*env)->GetFieldID(env, jPbePbrbmsClbss, "pInitVektor", "[C");
            if (fieldID == NULL) { return; }
            jInitVector = (*env)->GetObjectField(env, jPbrbmeter, fieldID);

            if (jInitVector != NULL) {
                jInitVectorLength = (*env)->GetArrbyLength(env, jInitVector);
                jInitVectorChbrs = (*env)->GetChbrArrbyElements(env, jInitVector, NULL);
                if (jInitVectorChbrs == NULL) { return; }

                /* copy the chbrs to the Jbvb buffer */
                for (i=0; i < jInitVectorLength; i++) {
                    jInitVectorChbrs[i] = ckChbrToJChbr(initVector[i]);
                }
                /* copy bbck the Jbvb buffer to the object */
                (*env)->RelebseChbrArrbyElements(env, jInitVector, jInitVectorChbrs, 0);
            }
        }
    }
}

/*
 * converts the Jbvb CK_PKCS5_PBKD2_PARAMS object to b CK_PKCS5_PBKD2_PARAMS structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses bnd objects
 * @pbrbm jPbrbm - the Jbvb CK_PKCS5_PBKD2_PARAMS object to convert
 * @return - the new CK_PKCS5_PBKD2_PARAMS structure
 */
CK_PKCS5_PBKD2_PARAMS jPkcs5Pbkd2PbrbmToCKPkcs5Pbkd2Pbrbm(JNIEnv *env, jobject jPbrbm)
{
    jclbss jPkcs5Pbkd2PbrbmsClbss;
    CK_PKCS5_PBKD2_PARAMS ckPbrbm;
    jfieldID fieldID;
    jlong jSbltSource, jIterbtion, jPrf;
    jobject jSbltSourceDbtb, jPrfDbtb;

    /* get sbltSource */
    jPkcs5Pbkd2PbrbmsClbss = (*env)->FindClbss(env, CLASS_PKCS5_PBKD2_PARAMS);
    if (jPkcs5Pbkd2PbrbmsClbss == NULL) { return ckPbrbm; }
    fieldID = (*env)->GetFieldID(env, jPkcs5Pbkd2PbrbmsClbss, "sbltSource", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jSbltSource = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get pSbltSourceDbtb */
    fieldID = (*env)->GetFieldID(env, jPkcs5Pbkd2PbrbmsClbss, "pSbltSourceDbtb", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jSbltSourceDbtb = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get iterbtions */
    fieldID = (*env)->GetFieldID(env, jPkcs5Pbkd2PbrbmsClbss, "iterbtions", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jIterbtion = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get prf */
    fieldID = (*env)->GetFieldID(env, jPkcs5Pbkd2PbrbmsClbss, "prf", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jPrf = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get pPrfDbtb bnd ulPrfDbtbLength in byte */
    fieldID = (*env)->GetFieldID(env, jPkcs5Pbkd2PbrbmsClbss, "pPrfDbtb", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jPrfDbtb = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* populbte jbvb vblues */
    ckPbrbm.sbltSource = jLongToCKULong(jSbltSource);
    jByteArrbyToCKByteArrby(env, jSbltSourceDbtb, (CK_BYTE_PTR *) &(ckPbrbm.pSbltSourceDbtb), &(ckPbrbm.ulSbltSourceDbtbLen));
    if ((*env)->ExceptionCheck(env)) { return ckPbrbm; }
    ckPbrbm.iterbtions = jLongToCKULong(jIterbtion);
    ckPbrbm.prf = jLongToCKULong(jPrf);
    jByteArrbyToCKByteArrby(env, jPrfDbtb, (CK_BYTE_PTR *) &(ckPbrbm.pPrfDbtb), &(ckPbrbm.ulPrfDbtbLen));
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.pSbltSourceDbtb);
        return ckPbrbm;
    }

    return ckPbrbm ;
}

/*
 * converts the Jbvb CK_RSA_PKCS_PSS_PARAMS object to b CK_RSA_PKCS_PSS_PARAMS structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses bnd objects
 * @pbrbm jPbrbm - the Jbvb CK_RSA_PKCS_PSS_PARAMS object to convert
 * @return - the new CK_RSA_PKCS_PSS_PARAMS structure
 */
CK_RSA_PKCS_PSS_PARAMS jRsbPkcsPssPbrbmToCKRsbPkcsPssPbrbm(JNIEnv *env, jobject jPbrbm)
{
    jclbss jRsbPkcsPssPbrbmsClbss;
    CK_RSA_PKCS_PSS_PARAMS ckPbrbm;
    jfieldID fieldID;
    jlong jHbshAlg, jMgf, jSLen;
    memset(&ckPbrbm, 0, sizeof(CK_RSA_PKCS_PSS_PARAMS));

    /* get hbshAlg */
    jRsbPkcsPssPbrbmsClbss = (*env)->FindClbss(env, CLASS_RSA_PKCS_PSS_PARAMS);
    if (jRsbPkcsPssPbrbmsClbss == NULL) { return ckPbrbm; }
    fieldID = (*env)->GetFieldID(env, jRsbPkcsPssPbrbmsClbss, "hbshAlg", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jHbshAlg = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get mgf */
    fieldID = (*env)->GetFieldID(env, jRsbPkcsPssPbrbmsClbss, "mgf", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jMgf = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get sLen */
    fieldID = (*env)->GetFieldID(env, jRsbPkcsPssPbrbmsClbss, "sLen", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jSLen = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* populbte jbvb vblues */
    ckPbrbm.hbshAlg = jLongToCKULong(jHbshAlg);
    ckPbrbm.mgf = jLongToCKULong(jMgf);
    ckPbrbm.sLen = jLongToCKULong(jSLen);

    return ckPbrbm ;
}

/*
 * converts the Jbvb CK_ECDH1_DERIVE_PARAMS object to b CK_ECDH1_DERIVE_PARAMS structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses bnd objects
 * @pbrbm jPbrbm - the Jbvb CK_ECDH1_DERIVE_PARAMS object to convert
 * @return - the new CK_ECDH1_DERIVE_PARAMS structure
 */
CK_ECDH1_DERIVE_PARAMS jEcdh1DerivePbrbmToCKEcdh1DerivePbrbm(JNIEnv *env, jobject jPbrbm)
{
    jclbss jEcdh1DerivePbrbmsClbss;
    CK_ECDH1_DERIVE_PARAMS ckPbrbm;
    jfieldID fieldID;
    jlong jLong;
    jobject jShbredDbtb, jPublicDbtb;
    memset(&ckPbrbm, 0, sizeof(CK_ECDH1_DERIVE_PARAMS));

    /* get kdf */
    jEcdh1DerivePbrbmsClbss = (*env)->FindClbss(env, CLASS_ECDH1_DERIVE_PARAMS);
    if (jEcdh1DerivePbrbmsClbss == NULL) { return ckPbrbm; }
    fieldID = (*env)->GetFieldID(env, jEcdh1DerivePbrbmsClbss, "kdf", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jLong = (*env)->GetLongField(env, jPbrbm, fieldID);
    ckPbrbm.kdf = jLongToCKULong(jLong);

    /* get pShbredDbtb bnd ulShbredDbtbLen */
    fieldID = (*env)->GetFieldID(env, jEcdh1DerivePbrbmsClbss, "pShbredDbtb", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jShbredDbtb = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get pPublicDbtb bnd ulPublicDbtbLen */
    fieldID = (*env)->GetFieldID(env, jEcdh1DerivePbrbmsClbss, "pPublicDbtb", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jPublicDbtb = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* populbte jbvb vblues */
    ckPbrbm.kdf = jLongToCKULong(jLong);
    jByteArrbyToCKByteArrby(env, jShbredDbtb, &(ckPbrbm.pShbredDbtb), &(ckPbrbm.ulShbredDbtbLen));
    if ((*env)->ExceptionCheck(env)) { return ckPbrbm; }
    jByteArrbyToCKByteArrby(env, jPublicDbtb, &(ckPbrbm.pPublicDbtb), &(ckPbrbm.ulPublicDbtbLen));
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.pShbredDbtb);
        return ckPbrbm;
    }

    return ckPbrbm ;
}

/*
 * converts the Jbvb CK_ECDH2_DERIVE_PARAMS object to b CK_ECDH2_DERIVE_PARAMS structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses bnd objects
 * @pbrbm jPbrbm - the Jbvb CK_ECDH2_DERIVE_PARAMS object to convert
 * @return - the new CK_ECDH2_DERIVE_PARAMS structure
 */
CK_ECDH2_DERIVE_PARAMS jEcdh2DerivePbrbmToCKEcdh2DerivePbrbm(JNIEnv *env, jobject jPbrbm)
{
    jclbss jEcdh2DerivePbrbmsClbss;
    CK_ECDH2_DERIVE_PARAMS ckPbrbm;
    jfieldID fieldID;
    jlong jKdf, jPrivbteDbtbLen, jPrivbteDbtb;
    jobject jShbredDbtb, jPublicDbtb, jPublicDbtb2;
    memset(&ckPbrbm, 0, sizeof(CK_ECDH2_DERIVE_PARAMS));

    /* get kdf */
    jEcdh2DerivePbrbmsClbss = (*env)->FindClbss(env, CLASS_ECDH2_DERIVE_PARAMS);
    if (jEcdh2DerivePbrbmsClbss == NULL) { return ckPbrbm; }
    fieldID = (*env)->GetFieldID(env, jEcdh2DerivePbrbmsClbss, "kdf", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jKdf = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get pShbredDbtb bnd ulShbredDbtbLen */
    fieldID = (*env)->GetFieldID(env, jEcdh2DerivePbrbmsClbss, "pShbredDbtb", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jShbredDbtb = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get pPublicDbtb bnd ulPublicDbtbLen */
    fieldID = (*env)->GetFieldID(env, jEcdh2DerivePbrbmsClbss, "pPublicDbtb", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jPublicDbtb = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get ulPrivbteDbtbLen */
    fieldID = (*env)->GetFieldID(env, jEcdh2DerivePbrbmsClbss, "ulPrivbteDbtbLen", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jPrivbteDbtbLen = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get hPrivbteDbtb */
    fieldID = (*env)->GetFieldID(env, jEcdh2DerivePbrbmsClbss, "hPrivbteDbtb", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jPrivbteDbtb = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get pPublicDbtb2 bnd ulPublicDbtbLen2 */
    fieldID = (*env)->GetFieldID(env, jEcdh2DerivePbrbmsClbss, "pPublicDbtb2", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jPublicDbtb2 = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* populbte jbvb vblues */
    ckPbrbm.kdf = jLongToCKULong(jKdf);
    jByteArrbyToCKByteArrby(env, jShbredDbtb, &(ckPbrbm.pShbredDbtb), &(ckPbrbm.ulShbredDbtbLen));
    if ((*env)->ExceptionCheck(env)) { return ckPbrbm; }
    jByteArrbyToCKByteArrby(env, jPublicDbtb, &(ckPbrbm.pPublicDbtb), &(ckPbrbm.ulPublicDbtbLen));
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.pShbredDbtb);
        return ckPbrbm;
    }
    ckPbrbm.ulPrivbteDbtbLen = jLongToCKULong(jPrivbteDbtbLen);
    ckPbrbm.hPrivbteDbtb = jLongToCKULong(jPrivbteDbtb);
    jByteArrbyToCKByteArrby(env, jPublicDbtb2, &(ckPbrbm.pPublicDbtb2), &(ckPbrbm.ulPublicDbtbLen2));
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.pShbredDbtb);
        free(ckPbrbm.pPublicDbtb);
        return ckPbrbm;
    }
    return ckPbrbm ;
}

/*
 * converts the Jbvb CK_X9_42_DH1_DERIVE_PARAMS object to b CK_X9_42_DH1_DERIVE_PARAMS structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses bnd objects
 * @pbrbm jPbrbm - the Jbvb CK_X9_42_DH1_DERIVE_PARAMS object to convert
 * @return - the new CK_X9_42_DH1_DERIVE_PARAMS structure
 */
CK_X9_42_DH1_DERIVE_PARAMS jX942Dh1DerivePbrbmToCKX942Dh1DerivePbrbm(JNIEnv *env, jobject jPbrbm)
{
    jclbss jX942Dh1DerivePbrbmsClbss;
    CK_X9_42_DH1_DERIVE_PARAMS ckPbrbm;
    jfieldID fieldID;
    jlong jKdf;
    jobject jOtherInfo, jPublicDbtb;

    /* get kdf */
    jX942Dh1DerivePbrbmsClbss = (*env)->FindClbss(env, CLASS_X9_42_DH1_DERIVE_PARAMS);
    if (jX942Dh1DerivePbrbmsClbss == NULL) { return ckPbrbm; }
    fieldID = (*env)->GetFieldID(env, jX942Dh1DerivePbrbmsClbss, "kdf", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jKdf = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get pOtherInfo bnd ulOtherInfoLen */
    fieldID = (*env)->GetFieldID(env, jX942Dh1DerivePbrbmsClbss, "pOtherInfo", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jOtherInfo = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get pPublicDbtb bnd ulPublicDbtbLen */
    fieldID = (*env)->GetFieldID(env, jX942Dh1DerivePbrbmsClbss, "pPublicDbtb", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jPublicDbtb = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* populbte jbvb vblues */
    ckPbrbm.kdf = jLongToCKULong(jKdf);
    jByteArrbyToCKByteArrby(env, jOtherInfo, &(ckPbrbm.pOtherInfo), &(ckPbrbm.ulOtherInfoLen));
    if ((*env)->ExceptionCheck(env)) { return ckPbrbm; }
    jByteArrbyToCKByteArrby(env, jPublicDbtb, &(ckPbrbm.pPublicDbtb), &(ckPbrbm.ulPublicDbtbLen));
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.pOtherInfo);
        return ckPbrbm;
    }

    return ckPbrbm ;
}

/*
 * converts the Jbvb CK_X9_42_DH2_DERIVE_PARAMS object to b CK_X9_42_DH2_DERIVE_PARAMS structure
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses bnd objects
 * @pbrbm jPbrbm - the Jbvb CK_X9_42_DH2_DERIVE_PARAMS object to convert
 * @return - the new CK_X9_42_DH2_DERIVE_PARAMS structure
 */
CK_X9_42_DH2_DERIVE_PARAMS jX942Dh2DerivePbrbmToCKX942Dh2DerivePbrbm(JNIEnv *env, jobject jPbrbm)
{
    jclbss jX942Dh2DerivePbrbmsClbss;
    CK_X9_42_DH2_DERIVE_PARAMS ckPbrbm;
    jfieldID fieldID;
    jlong jKdf, jPrivbteDbtbLen, jPrivbteDbtb;
    jobject jOtherInfo, jPublicDbtb, jPublicDbtb2;

    /* get kdf */
    jX942Dh2DerivePbrbmsClbss = (*env)->FindClbss(env, CLASS_X9_42_DH2_DERIVE_PARAMS);
    if (jX942Dh2DerivePbrbmsClbss == NULL) { return ckPbrbm; }
    fieldID = (*env)->GetFieldID(env, jX942Dh2DerivePbrbmsClbss, "kdf", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jKdf = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get pOtherInfo bnd ulOtherInfoLen */
    fieldID = (*env)->GetFieldID(env, jX942Dh2DerivePbrbmsClbss, "pOtherInfo", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jOtherInfo = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get pPublicDbtb bnd ulPublicDbtbLen */
    fieldID = (*env)->GetFieldID(env, jX942Dh2DerivePbrbmsClbss, "pPublicDbtb", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jPublicDbtb = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* get ulPrivbteDbtbLen */
    fieldID = (*env)->GetFieldID(env, jX942Dh2DerivePbrbmsClbss, "ulPrivbteDbtbLen", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jPrivbteDbtbLen = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get hPrivbteDbtb */
    fieldID = (*env)->GetFieldID(env, jX942Dh2DerivePbrbmsClbss, "hPrivbteDbtb", "J");
    if (fieldID == NULL) { return ckPbrbm; }
    jPrivbteDbtb = (*env)->GetLongField(env, jPbrbm, fieldID);

    /* get pPublicDbtb2 bnd ulPublicDbtbLen2 */
    fieldID = (*env)->GetFieldID(env, jX942Dh2DerivePbrbmsClbss, "pPublicDbtb2", "[B");
    if (fieldID == NULL) { return ckPbrbm; }
    jPublicDbtb2 = (*env)->GetObjectField(env, jPbrbm, fieldID);

    /* populbte jbvb vblues */
    ckPbrbm.kdf = jLongToCKULong(jKdf);
    jByteArrbyToCKByteArrby(env, jOtherInfo, &(ckPbrbm.pOtherInfo), &(ckPbrbm.ulOtherInfoLen));
    if ((*env)->ExceptionCheck(env)) { return ckPbrbm; }
    jByteArrbyToCKByteArrby(env, jPublicDbtb, &(ckPbrbm.pPublicDbtb), &(ckPbrbm.ulPublicDbtbLen));
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.pOtherInfo);
        return ckPbrbm;
    }
    ckPbrbm.ulPrivbteDbtbLen = jLongToCKULong(jPrivbteDbtbLen);
    ckPbrbm.hPrivbteDbtb = jLongToCKULong(jPrivbteDbtb);
    jByteArrbyToCKByteArrby(env, jPublicDbtb2, &(ckPbrbm.pPublicDbtb2), &(ckPbrbm.ulPublicDbtbLen2));
    if ((*env)->ExceptionCheck(env)) {
        free(ckPbrbm.pOtherInfo);
        free(ckPbrbm.pPublicDbtb);
        return ckPbrbm;
    }

    return ckPbrbm ;
}
