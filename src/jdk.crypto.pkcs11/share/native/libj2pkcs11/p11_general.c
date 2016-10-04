/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "pkcs11wrbpper.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <bssert.h>

#include "sun_security_pkcs11_wrbpper_PKCS11.h"

/* declbre file privbte functions */

void prefetchFields(JNIEnv *env, jclbss thisClbss);
jobject ckInfoPtrToJInfo(JNIEnv *env, const CK_INFO_PTR ckpInfo);
jobject ckSlotInfoPtrToJSlotInfo(JNIEnv *env, const CK_SLOT_INFO_PTR ckpSlotInfo);
jobject ckTokenInfoPtrToJTokenInfo(JNIEnv *env, const CK_TOKEN_INFO_PTR ckpTokenInfo);
jobject ckMechbnismInfoPtrToJMechbnismInfo(JNIEnv *env, const CK_MECHANISM_INFO_PTR ckpMechbnismInfo);

/* define vbribbles */

jfieldID pNbtiveDbtbID;
jfieldID mech_mechbnismID;
jfieldID mech_pPbrbmeterID;

jclbss jByteArrbyClbss;
jclbss jLongClbss;

JbvbVM* jvm = NULL;

JNIEXPORT jint JNICALL JNI_OnLobd(JbvbVM *vm, void *reserved) {
    jvm = vm;
    return JNI_VERSION_1_4;
}

/* ************************************************************************** */
/* The nbtive implementbtion of the methods of the PKCS11Implementbtion clbss */
/* ************************************************************************** */

/*
 * This method is used to do stbtic initiblizbtion. This method is stbtic bnd
 * synchronized. Summbry: use this method like b stbtic initiblizbtion block.
 *
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    initiblizeLibrbry
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_initiblizeLibrbry
(JNIEnv *env, jclbss thisClbss)
{
#ifndef NO_CALLBACKS
    if (notifyListLock == NULL) {
        notifyListLock = crebteLockObject(env);
    }
#endif

    prefetchFields(env, thisClbss);
}

jclbss fetchClbss(JNIEnv *env, const chbr *nbme) {
    jclbss tmpClbss = (*env)->FindClbss(env, nbme);
    if (tmpClbss == NULL) { return NULL; }
    return (*env)->NewGlobblRef(env, tmpClbss);
}

void prefetchFields(JNIEnv *env, jclbss thisClbss) {
    jclbss tmpClbss;

    /* PKCS11 */
    pNbtiveDbtbID = (*env)->GetFieldID(env, thisClbss, "pNbtiveDbtb", "J");
    if (pNbtiveDbtbID == NULL) { return; }

    /* CK_MECHANISM */
    tmpClbss = (*env)->FindClbss(env, CLASS_MECHANISM);
    if (tmpClbss == NULL) { return; }
    mech_mechbnismID = (*env)->GetFieldID(env, tmpClbss, "mechbnism", "J");
    if (mech_mechbnismID == NULL) { return; }
    mech_pPbrbmeterID = (*env)->GetFieldID(env, tmpClbss, "pPbrbmeter",
                                           "Ljbvb/lbng/Object;");
    if (mech_pPbrbmeterID == NULL) { return; }
    jByteArrbyClbss = fetchClbss(env, "[B");
    if (jByteArrbyClbss == NULL) { return; }
    jLongClbss = fetchClbss(env, "jbvb/lbng/Long");
}

/* This method is designed to do b clebn-up. It relebses bll globbl resources
 * of this librbry. By now, this function is not cblled. Cblling from
 * JNI_OnUnlobd would be bn option, but some VMs do not support JNI_OnUnlobd.
 *
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    finblizeLibrbry
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_finblizeLibrbry
(JNIEnv *env, jclbss thisClbss)
{
/* XXX
    * remove bll left lists bnd relebse the resources bnd the lock
     * objects thbt synchroniz bccess to these lists.
     *
    removeAllModuleEntries(env);
    if (moduleListHebd == NULL) { * check, if we removed the lbst bctive module *
        * remove blso the moduleListLock, it is no longer used *
        if (moduleListLock != NULL) {
            destroyLockObject(env, moduleListLock);
            moduleListLock = NULL;
        }
#ifndef NO_CALLBACKS
        * remove bll left notify cbllbbck entries *
        while (removeFirstNotifyEntry(env));
        * remove blso the notifyListLock, it is no longer used *
        if (notifyListLock != NULL) {
            destroyLockObject(env, notifyListLock);
            notifyListLock = NULL;
        }
        if (jInitArgsObject != NULL) {
            (*env)->DeleteGlobblRef(env, jInitArgsObject);
        }
        if (ckpGlobblInitArgs != NULL_PTR) {
            free(ckpGlobblInitArgs);
        }
#endif * NO_CALLBACKS *
    }
*/
}

#ifdef P11_ENABLE_C_INITIALIZE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_Initiblize
 * Signbture: (Ljbvb/lbng/Object;)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jobject jInitArgs           CK_VOID_PTR pInitArgs
 */
JNIEXPORT void JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1Initiblize
(JNIEnv *env, jobject obj, jobject jInitArgs)
{
    /*
     * Initblize Cryptoki
     */
    CK_C_INITIALIZE_ARGS_PTR ckpInitArgs;
    CK_RV rv;
    CK_FUNCTION_LIST_PTR ckpFunctions;

    TRACE0("DEBUG: initiblizing module... ");

    ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) {
        TRACE0("fbiled getting module entry");
        return;
    }

    ckpInitArgs = (jInitArgs != NULL)
                ? mbkeCKInitArgsAdbpter(env, jInitArgs)
                : NULL_PTR;

    rv = (*ckpFunctions->C_Initiblize)(ckpInitArgs);

    free(ckpInitArgs);

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }

    TRACE0("FINISHED\n");
}
#endif

#ifdef P11_ENABLE_C_FINALIZE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_Finblize
 * Signbture: (Ljbvb/lbng/Object;)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jobject jReserved           CK_VOID_PTR pReserved
 */
JNIEXPORT void JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1Finblize
(JNIEnv *env, jobject obj, jobject jReserved)
{
    /*
     * Finblize Cryptoki
     */
    CK_VOID_PTR ckpReserved;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckpReserved = jObjectToCKVoidPtr(jReserved);

    rv = (*ckpFunctions->C_Finblize)(ckpReserved);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_GETINFO
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_GetInfo
 * Signbture: ()Lsun/security/pkcs11/wrbpper/CK_INFO;
 * Pbrbmetermbpping:                    *PKCS11*
 * @return  jobject jInfoObject         CK_INFO_PTR pInfo
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1GetInfo
(JNIEnv *env, jobject obj)
{
    CK_INFO ckLibInfo;
    jobject jInfoObject=NULL;
    CK_RV rv;
    CK_FUNCTION_LIST_PTR ckpFunctions;
    memset(&ckLibInfo, 0, sizeof(CK_INFO));

    ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    rv = (*ckpFunctions->C_GetInfo)(&ckLibInfo);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jInfoObject = ckInfoPtrToJInfo(env, &ckLibInfo);
    }
    return jInfoObject ;
}

/*
 * converts b pointer to b CK_INFO structure into b Jbvb CK_INFO Object.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb object
 * @pbrbm ckpInfo - the pointer to the CK_INFO structure
 * @return - the new Jbvb CK_INFO object
 */
jobject ckInfoPtrToJInfo(JNIEnv *env, const CK_INFO_PTR ckpInfo)
{
    jclbss jInfoClbss;
    jmethodID jCtrId;
    jobject jInfoObject;
    jobject jCryptokiVer;
    jchbrArrby jVendor;
    jlong jFlbgs;
    jchbrArrby jLibrbryDesc;
    jobject jLibrbryVer;

    /* lobd CK_INFO clbss */
    jInfoClbss = (*env)->FindClbss(env, CLASS_INFO);
    if (jInfoClbss == NULL) { return NULL; };

    /* lobd CK_INFO constructor */
    jCtrId = (*env)->GetMethodID
      (env, jInfoClbss, "<init>",
       "(Lsun/security/pkcs11/wrbpper/CK_VERSION;[CJ[CLsun/security/pkcs11/wrbpper/CK_VERSION;)V");
    if (jCtrId == NULL) { return NULL; }

    /* prep bll fields */
    jCryptokiVer = ckVersionPtrToJVersion(env, &(ckpInfo->cryptokiVersion));
    if (jCryptokiVer == NULL) { return NULL; }
    jVendor =
      ckUTF8ChbrArrbyToJChbrArrby(env, &(ckpInfo->mbnufbcturerID[0]), 32);
    if (jVendor == NULL) { return NULL; }
    jFlbgs = ckULongToJLong(ckpInfo->flbgs);
    jLibrbryDesc =
      ckUTF8ChbrArrbyToJChbrArrby(env, &(ckpInfo->librbryDescription[0]), 32);
    if (jLibrbryDesc == NULL) { return NULL; }
    jLibrbryVer = ckVersionPtrToJVersion(env, &(ckpInfo->librbryVersion));
    if (jLibrbryVer == NULL) { return NULL; }

    /* crebte new CK_INFO object */
    jInfoObject = (*env)->NewObject(env, jInfoClbss, jCtrId, jCryptokiVer,
                                    jVendor, jFlbgs, jLibrbryDesc, jLibrbryVer);
    if (jInfoObject == NULL) { return NULL; }

    /* free locbl references */
    (*env)->DeleteLocblRef(env, jInfoClbss);
    (*env)->DeleteLocblRef(env, jCryptokiVer);
    (*env)->DeleteLocblRef(env, jVendor);
    (*env)->DeleteLocblRef(env, jLibrbryDesc);
    (*env)->DeleteLocblRef(env, jLibrbryVer);

    return jInfoObject ;
}
#endif

#ifdef P11_ENABLE_C_GETSLOTLIST
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_GetSlotList
 * Signbture: (Z)[J
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jboolebn jTokenPresent      CK_BBOOL tokenPresent
 * @return  jlongArrby jSlotList        CK_SLOT_ID_PTR pSlotList
 *                                      CK_ULONG_PTR pulCount
 */
JNIEXPORT jlongArrby JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1GetSlotList
(JNIEnv *env, jobject obj, jboolebn jTokenPresent)
{
    CK_ULONG ckTokenNumber;
    CK_SLOT_ID_PTR ckpSlotList;
    CK_BBOOL ckTokenPresent;
    jlongArrby jSlotList = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckTokenPresent = jBoolebnToCKBBool(jTokenPresent);

    rv = (*ckpFunctions->C_GetSlotList)(ckTokenPresent, NULL_PTR,
                                        &ckTokenNumber);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return NULL ; }

    ckpSlotList = (CK_SLOT_ID_PTR) mblloc(ckTokenNumber * sizeof(CK_SLOT_ID));
    if (ckpSlotList == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    rv = (*ckpFunctions->C_GetSlotList)(ckTokenPresent, ckpSlotList,
                                        &ckTokenNumber);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jSlotList = ckULongArrbyToJLongArrby(env, ckpSlotList, ckTokenNumber);
    }
    free(ckpSlotList);

    return jSlotList ;
}
#endif

#ifdef P11_ENABLE_C_GETSLOTINFO
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_GetSlotInfo
 * Signbture: (J)Lsun/security/pkcs11/wrbpper/CK_SLOT_INFO;
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSlotID               CK_SLOT_ID slotID
 * @return  jobject jSlotInfoObject     CK_SLOT_INFO_PTR pInfo
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1GetSlotInfo
(JNIEnv *env, jobject obj, jlong jSlotID)
{
    CK_SLOT_ID ckSlotID;
    CK_SLOT_INFO ckSlotInfo;
    jobject jSlotInfoObject=NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSlotID = jLongToCKULong(jSlotID);

    rv = (*ckpFunctions->C_GetSlotInfo)(ckSlotID, &ckSlotInfo);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jSlotInfoObject = ckSlotInfoPtrToJSlotInfo(env, &ckSlotInfo);
    }
    return jSlotInfoObject;
}

/*
 * converts b pointer to b CK_SLOT_INFO structure into b Jbvb CK_SLOT_INFO
 * Object.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb object
 * @pbrbm ckpSlotInfo - the pointer to the CK_SLOT_INFO structure
 * @return - the new Jbvb CK_SLOT_INFO object
 */
jobject
ckSlotInfoPtrToJSlotInfo
(JNIEnv *env, const CK_SLOT_INFO_PTR ckpSlotInfo)
{
    jclbss jSlotInfoClbss;
    jmethodID jCtrId;
    jobject jSlotInfoObject;
    jchbrArrby jSlotDesc;
    jchbrArrby jVendor;
    jlong jFlbgs;
    jobject jHbrdwbreVer;
    jobject jFirmwbreVer;

    /* lobd CK_SLOT_INFO clbss */
    jSlotInfoClbss = (*env)->FindClbss(env, CLASS_SLOT_INFO);
    if (jSlotInfoClbss == NULL) { return NULL; };

    /* lobd CK_SLOT_INFO constructor */
    jCtrId = (*env)->GetMethodID
      (env, jSlotInfoClbss, "<init>",
       "([C[CJLsun/security/pkcs11/wrbpper/CK_VERSION;Lsun/security/pkcs11/wrbpper/CK_VERSION;)V");
    if (jCtrId == NULL) { return NULL; }

    /* prep bll fields */
    jSlotDesc =
      ckUTF8ChbrArrbyToJChbrArrby(env, &(ckpSlotInfo->slotDescription[0]), 64);
    if (jSlotDesc == NULL) { return NULL; }
    jVendor =
      ckUTF8ChbrArrbyToJChbrArrby(env, &(ckpSlotInfo->mbnufbcturerID[0]), 32);
    if (jVendor == NULL) { return NULL; }
    jFlbgs = ckULongToJLong(ckpSlotInfo->flbgs);
    jHbrdwbreVer = ckVersionPtrToJVersion(env, &(ckpSlotInfo->hbrdwbreVersion));
    if (jHbrdwbreVer == NULL) { return NULL; }
    jFirmwbreVer = ckVersionPtrToJVersion(env, &(ckpSlotInfo->firmwbreVersion));
    if (jFirmwbreVer == NULL) { return NULL; }

    /* crebte new CK_SLOT_INFO object */
    jSlotInfoObject = (*env)->NewObject
      (env, jSlotInfoClbss, jCtrId, jSlotDesc, jVendor, jFlbgs,
       jHbrdwbreVer, jFirmwbreVer);
    if (jSlotInfoObject == NULL) { return NULL; }

    /* free locbl references */
    (*env)->DeleteLocblRef(env, jSlotInfoClbss);
    (*env)->DeleteLocblRef(env, jSlotDesc);
    (*env)->DeleteLocblRef(env, jVendor);
    (*env)->DeleteLocblRef(env, jHbrdwbreVer);
    (*env)->DeleteLocblRef(env, jFirmwbreVer);

    return jSlotInfoObject ;
}

#endif

#ifdef P11_ENABLE_C_GETTOKENINFO
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_GetTokenInfo
 * Signbture: (J)Lsun/security/pkcs11/wrbpper/CK_TOKEN_INFO;
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSlotID               CK_SLOT_ID slotID
 * @return  jobject jInfoTokenObject    CK_TOKEN_INFO_PTR pInfo
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1GetTokenInfo
(JNIEnv *env, jobject obj, jlong jSlotID)
{
    CK_SLOT_ID ckSlotID;
    CK_TOKEN_INFO ckTokenInfo;
    jobject jInfoTokenObject = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSlotID = jLongToCKULong(jSlotID);

    rv = (*ckpFunctions->C_GetTokenInfo)(ckSlotID, &ckTokenInfo);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jInfoTokenObject = ckTokenInfoPtrToJTokenInfo(env, &ckTokenInfo);
    }
    return jInfoTokenObject ;
}

/*
 * converts b pointer to b CK_TOKEN_INFO structure into b Jbvb CK_TOKEN_INFO
 * Object.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb object
 * @pbrbm ckpTokenInfo - the pointer to the CK_TOKEN_INFO structure
 * @return - the new Jbvb CK_TOKEN_INFO object
 */
jobject
ckTokenInfoPtrToJTokenInfo
(JNIEnv *env, const CK_TOKEN_INFO_PTR ckpTokenInfo)
{
    jclbss jTokenInfoClbss;
    jmethodID jCtrId;
    jobject jTokenInfoObject;
    jchbrArrby jLbbel;
    jchbrArrby jVendor;
    jchbrArrby jModel;
    jchbrArrby jSeriblNo;
    jlong jFlbgs;
    jlong jMbxSnCnt;
    jlong jSnCnt;
    jlong jMbxRwSnCnt;
    jlong jRwSnCnt;
    jlong jMbxPinLen;
    jlong jMinPinLen;
    jlong jTotblPubMem;
    jlong jFreePubMem;
    jlong jTotblPrivMem;
    jlong jFreePrivMem;
    jobject jHbrdwbreVer;
    jobject jFirmwbreVer;
    jchbrArrby jUtcTime;

    /* lobd CK_TOKEN_INFO clbss */
    jTokenInfoClbss = (*env)->FindClbss(env, CLASS_TOKEN_INFO);
    if (jTokenInfoClbss == NULL)  { return NULL; };

    /* lobd CK_TOKEN_INFO constructor */
    jCtrId = (*env)->GetMethodID
      (env, jTokenInfoClbss, "<init>",
       "([C[C[C[CJJJJJJJJJJJLsun/security/pkcs11/wrbpper/CK_VERSION;Lsun/security/pkcs11/wrbpper/CK_VERSION;[C)V");
    if (jCtrId == NULL)  { return NULL; };

    /* prep bll fields */
    jLbbel = ckUTF8ChbrArrbyToJChbrArrby(env, &(ckpTokenInfo->lbbel[0]), 32);
    if (jLbbel == NULL)  { return NULL; };
    jVendor =
      ckUTF8ChbrArrbyToJChbrArrby(env, &(ckpTokenInfo->mbnufbcturerID[0]), 32);
    if (jVendor == NULL)  { return NULL; };
    jModel = ckUTF8ChbrArrbyToJChbrArrby(env, &(ckpTokenInfo->model[0]), 16);
    if (jModel == NULL)  { return NULL; };
    jSeriblNo =
      ckUTF8ChbrArrbyToJChbrArrby(env, &(ckpTokenInfo->seriblNumber[0]), 16);
    if (jSeriblNo == NULL)  { return NULL; };
    jFlbgs = ckULongToJLong(ckpTokenInfo->flbgs);
    jMbxSnCnt = ckULongSpeciblToJLong(ckpTokenInfo->ulMbxSessionCount);
    jSnCnt = ckULongSpeciblToJLong(ckpTokenInfo->ulSessionCount);
    jMbxRwSnCnt = ckULongSpeciblToJLong(ckpTokenInfo->ulMbxRwSessionCount);
    jRwSnCnt = ckULongSpeciblToJLong(ckpTokenInfo->ulRwSessionCount);
    jMbxPinLen = ckULongToJLong(ckpTokenInfo->ulMbxPinLen);
    jMinPinLen = ckULongToJLong(ckpTokenInfo->ulMinPinLen);
    jTotblPubMem = ckULongSpeciblToJLong(ckpTokenInfo->ulTotblPublicMemory);
    jFreePubMem = ckULongSpeciblToJLong(ckpTokenInfo->ulFreePublicMemory);
    jTotblPrivMem = ckULongSpeciblToJLong(ckpTokenInfo->ulTotblPrivbteMemory);
    jFreePrivMem = ckULongSpeciblToJLong(ckpTokenInfo->ulFreePrivbteMemory);
    jHbrdwbreVer =
      ckVersionPtrToJVersion(env, &(ckpTokenInfo->hbrdwbreVersion));
    if (jHbrdwbreVer == NULL) { return NULL; }
    jFirmwbreVer =
      ckVersionPtrToJVersion(env, &(ckpTokenInfo->firmwbreVersion));
    if (jFirmwbreVer == NULL) { return NULL; }
    jUtcTime =
      ckUTF8ChbrArrbyToJChbrArrby(env, &(ckpTokenInfo->utcTime[0]), 16);
    if (jUtcTime == NULL) { return NULL; }

    /* crebte new CK_TOKEN_INFO object */
    jTokenInfoObject =
      (*env)->NewObject(env, jTokenInfoClbss, jCtrId, jLbbel, jVendor, jModel,
                        jSeriblNo, jFlbgs,
                        jMbxSnCnt, jSnCnt, jMbxRwSnCnt, jRwSnCnt,
                        jMbxPinLen, jMinPinLen,
                        jTotblPubMem, jFreePubMem, jTotblPrivMem, jFreePrivMem,
                        jHbrdwbreVer, jFirmwbreVer, jUtcTime);
    if (jTokenInfoObject == NULL) { return NULL; }

    /* free locbl references */
    (*env)->DeleteLocblRef(env, jTokenInfoClbss);
    (*env)->DeleteLocblRef(env, jLbbel);
    (*env)->DeleteLocblRef(env, jVendor);
    (*env)->DeleteLocblRef(env, jModel);
    (*env)->DeleteLocblRef(env, jSeriblNo);
    (*env)->DeleteLocblRef(env, jHbrdwbreVer);
    (*env)->DeleteLocblRef(env, jFirmwbreVer);

    return jTokenInfoObject ;
}
#endif

#ifdef P11_ENABLE_C_WAITFORSLOTEVENT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_WbitForSlotEvent
 * Signbture: (JLjbvb/lbng/Object;)J
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jFlbgs                CK_FLAGS flbgs
 * @pbrbm   jobject jReserved           CK_VOID_PTR pReserved
 * @return  jlong jSlotID               CK_SLOT_ID_PTR pSlot
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1WbitForSlotEvent
(JNIEnv *env, jobject obj, jlong jFlbgs, jobject jReserved)
{
    CK_FLAGS ckFlbgs;
    CK_SLOT_ID ckSlotID;
    jlong jSlotID = 0L;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0L; }

    ckFlbgs = jLongToCKULong(jFlbgs);

    rv = (*ckpFunctions->C_WbitForSlotEvent)(ckFlbgs, &ckSlotID, NULL_PTR);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jSlotID = ckULongToJLong(ckSlotID);
    }

    return jSlotID ;
}
#endif

#ifdef P11_ENABLE_C_GETMECHANISMLIST
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_GetMechbnismList
 * Signbture: (J)[J
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSlotID               CK_SLOT_ID slotID
 * @return  jlongArrby jMechbnismList   CK_MECHANISM_TYPE_PTR pMechbnismList
 *                                      CK_ULONG_PTR pulCount
 */
JNIEXPORT jlongArrby JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1GetMechbnismList
(JNIEnv *env, jobject obj, jlong jSlotID)
{
    CK_SLOT_ID ckSlotID;
    CK_ULONG ckMechbnismNumber;
    CK_MECHANISM_TYPE_PTR ckpMechbnismList;
    jlongArrby jMechbnismList = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSlotID = jLongToCKULong(jSlotID);

    rv = (*ckpFunctions->C_GetMechbnismList)(ckSlotID, NULL_PTR,
                                             &ckMechbnismNumber);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return NULL ; }

    ckpMechbnismList = (CK_MECHANISM_TYPE_PTR)
      mblloc(ckMechbnismNumber * sizeof(CK_MECHANISM_TYPE));
    if (ckpMechbnismList == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    rv = (*ckpFunctions->C_GetMechbnismList)(ckSlotID, ckpMechbnismList,
                                             &ckMechbnismNumber);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jMechbnismList = ckULongArrbyToJLongArrby(env, ckpMechbnismList,
                                                  ckMechbnismNumber);
    }
    free(ckpMechbnismList);

    return jMechbnismList ;
}
#endif

#ifdef P11_ENABLE_C_GETMECHANISMINFO
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_GetMechbnismInfo
 * Signbture: (JJ)Lsun/security/pkcs11/wrbpper/CK_MECHANISM_INFO;
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSlotID               CK_SLOT_ID slotID
 * @pbrbm   jlong jType                 CK_MECHANISM_TYPE type
 * @return  jobject jMechbnismInfo      CK_MECHANISM_INFO_PTR pInfo
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1GetMechbnismInfo
(JNIEnv *env, jobject obj, jlong jSlotID, jlong jType)
{
    CK_SLOT_ID ckSlotID;
    CK_MECHANISM_TYPE ckMechbnismType;
    CK_MECHANISM_INFO ckMechbnismInfo;
    jobject jMechbnismInfo = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSlotID = jLongToCKULong(jSlotID);
    ckMechbnismType = jLongToCKULong(jType);

    rv = (*ckpFunctions->C_GetMechbnismInfo)(ckSlotID, ckMechbnismType,
                                             &ckMechbnismInfo);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jMechbnismInfo = ckMechbnismInfoPtrToJMechbnismInfo(env, &ckMechbnismInfo);
    }
    return jMechbnismInfo ;
}

/*
 * converts b pointer to b CK_MECHANISM_INFO structure into b Jbvb
 * CK_MECHANISM_INFO Object.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb object
 * @pbrbm ckpMechbnismInfo - the pointer to the CK_MECHANISM_INFO structure
 * @return - the new Jbvb CK_MECHANISM_INFO object
 */
jobject
ckMechbnismInfoPtrToJMechbnismInfo
(JNIEnv *env, const CK_MECHANISM_INFO_PTR ckpMechbnismInfo)
{

    jclbss jMechbnismInfoClbss;
    jmethodID jCtrId;
    jobject jMechbnismInfoObject;
    jlong jMinKeySize;
    jlong jMbxKeySize;
    jlong jFlbgs;

    /* lobd CK_MECHANISM_INFO clbss */
    jMechbnismInfoClbss = (*env)->FindClbss(env, CLASS_MECHANISM_INFO);
    if (jMechbnismInfoClbss == NULL) { return NULL; };

    /* lobd CK_MECHANISM_INFO constructor */
    jCtrId = (*env)->GetMethodID(env, jMechbnismInfoClbss, "<init>", "(JJJ)V");
    if (jCtrId == NULL) { return NULL; };

    /* prep bll fields */
    jMinKeySize = ckULongToJLong(ckpMechbnismInfo->ulMinKeySize);
    jMbxKeySize = ckULongToJLong(ckpMechbnismInfo->ulMbxKeySize);
    jFlbgs = ckULongToJLong(ckpMechbnismInfo->flbgs);

    /* crebte new CK_MECHANISM_INFO object */
    jMechbnismInfoObject = (*env)->NewObject(env, jMechbnismInfoClbss, jCtrId,
                                             jMinKeySize, jMbxKeySize, jFlbgs);
    if (jMechbnismInfoObject == NULL) { return NULL; };

    /* free locbl references */
    (*env)->DeleteLocblRef(env, jMechbnismInfoClbss);

    return jMechbnismInfoObject ;
}
#endif

#ifdef P11_ENABLE_C_INITTOKEN
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_InitToken
 * Signbture: (J[C[C)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSlotID               CK_SLOT_ID slotID
 * @pbrbm   jchbrArrby jPin             CK_CHAR_PTR pPin
 *                                      CK_ULONG ulPinLen
 * @pbrbm   jchbrArrby jLbbel           CK_UTF8CHAR_PTR pLbbel
 */
JNIEXPORT void JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1InitToken
(JNIEnv *env, jobject obj, jlong jSlotID, jchbrArrby jPin, jchbrArrby jLbbel)
{
    CK_SLOT_ID ckSlotID;
    CK_CHAR_PTR ckpPin = NULL_PTR;
    CK_UTF8CHAR_PTR ckpLbbel = NULL_PTR;
    CK_ULONG ckPinLength;
    CK_ULONG ckLbbelLength;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSlotID = jLongToCKULong(jSlotID);
    jChbrArrbyToCKChbrArrby(env, jPin, &ckpPin, &ckPinLength);
    if ((*env)->ExceptionCheck(env)) { return; }
    /* ckLbbelLength <= 32 !!! */
    jChbrArrbyToCKUTF8ChbrArrby(env, jLbbel, &ckpLbbel, &ckLbbelLength);
    if ((*env)->ExceptionCheck(env)) {
        free(ckpPin);
        return;
    }

    rv = (*ckpFunctions->C_InitToken)(ckSlotID, ckpPin, ckPinLength, ckpLbbel);
    TRACE1("InitToken return code: %d", rv);

    free(ckpPin);
    free(ckpLbbel);

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_INITPIN
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_InitPIN
 * Signbture: (J[C)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE
 * @pbrbm   jchbrArrby jPin             CK_CHAR_PTR pPin
 *                                      CK_ULONG ulPinLen
 */
JNIEXPORT void JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1InitPIN
(JNIEnv *env, jobject obj, jlong jSessionHbndle, jchbrArrby jPin)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_CHAR_PTR ckpPin = NULL_PTR;
    CK_ULONG ckPinLength;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jChbrArrbyToCKChbrArrby(env, jPin, &ckpPin, &ckPinLength);
    if ((*env)->ExceptionCheck(env)) { return; }

    rv = (*ckpFunctions->C_InitPIN)(ckSessionHbndle, ckpPin, ckPinLength);

    free(ckpPin);

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_SETPIN
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_SetPIN
 * Signbture: (J[C[C)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jchbrArrby jOldPin          CK_CHAR_PTR pOldPin
 *                                      CK_ULONG ulOldLen
 * @pbrbm   jchbrArrby jNewPin          CK_CHAR_PTR pNewPin
 *                                      CK_ULONG ulNewLen
 */
JNIEXPORT void JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1SetPIN
(JNIEnv *env, jobject obj, jlong jSessionHbndle, jchbrArrby jOldPin,
jchbrArrby jNewPin)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_CHAR_PTR ckpOldPin = NULL_PTR;
    CK_CHAR_PTR ckpNewPin = NULL_PTR;
    CK_ULONG ckOldPinLength;
    CK_ULONG ckNewPinLength;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jChbrArrbyToCKChbrArrby(env, jOldPin, &ckpOldPin, &ckOldPinLength);
    if ((*env)->ExceptionCheck(env)) { return; }
    jChbrArrbyToCKChbrArrby(env, jNewPin, &ckpNewPin, &ckNewPinLength);
    if ((*env)->ExceptionCheck(env)) {
        free(ckpOldPin);
        return;
    }

    rv = (*ckpFunctions->C_SetPIN)(ckSessionHbndle, ckpOldPin, ckOldPinLength,
                                   ckpNewPin, ckNewPinLength);

    free(ckpOldPin);
    free(ckpNewPin);

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif
