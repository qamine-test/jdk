/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifdef P11_ENABLE_C_GENERATEKEY
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_GenerbteKey
 * Signbture: (JLsun/security/pkcs11/wrbpper/CK_MECHANISM;[Lsun/security/pkcs11/wrbpper/CK_ATTRIBUTE;)J
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jobject jMechbnism          CK_MECHANISM_PTR pMechbnism
 * @pbrbm   jobjectArrby jTemplbte      CK_ATTRIBUTE_PTR pTemplbte
 *                                      CK_ULONG ulCount
 * @return  jlong jKeyHbndle            CK_OBJECT_HANDLE_PTR phKey
 */
JNIEXPORT jlong JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1GenerbteKey
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jobject jMechbnism, jobjectArrby jTemplbte)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_MECHANISM ckMechbnism;
    CK_ATTRIBUTE_PTR ckpAttributes = NULL_PTR;
    CK_ULONG ckAttributesLength;
    CK_OBJECT_HANDLE ckKeyHbndle = 0;
    jlong jKeyHbndle = 0L;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0L; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jMechbnismToCKMechbnism(env, jMechbnism, &ckMechbnism);
    if ((*env)->ExceptionCheck(env)) { return 0L ; }

    jAttributeArrbyToCKAttributeArrby(env, jTemplbte, &ckpAttributes, &ckAttributesLength);
    if ((*env)->ExceptionCheck(env)) {
        if (ckMechbnism.pPbrbmeter != NULL_PTR) {
            free(ckMechbnism.pPbrbmeter);
        }
        return 0L;
    }

    rv = (*ckpFunctions->C_GenerbteKey)(ckSessionHbndle, &ckMechbnism, ckpAttributes, ckAttributesLength, &ckKeyHbndle);

    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jKeyHbndle = ckULongToJLong(ckKeyHbndle);

        /* chebck, if we must give b initiblizbtion vector bbck to Jbvb */
        switch (ckMechbnism.mechbnism) {
        cbse CKM_PBE_MD2_DES_CBC:
        cbse CKM_PBE_MD5_DES_CBC:
        cbse CKM_PBE_MD5_CAST_CBC:
        cbse CKM_PBE_MD5_CAST3_CBC:
        cbse CKM_PBE_MD5_CAST128_CBC:
        /* cbse CKM_PBE_MD5_CAST5_CBC:  the sbme bs CKM_PBE_MD5_CAST128_CBC */
        cbse CKM_PBE_SHA1_CAST128_CBC:
        /* cbse CKM_PBE_SHA1_CAST5_CBC: the sbme bs CKM_PBE_SHA1_CAST128_CBC */
            /* we must copy bbck the initiblizbtion vector to the jMechbnism object */
            copyBbckPBEInitiblizbtionVector(env, &ckMechbnism, jMechbnism);
            brebk;
        }
    }

    if (ckMechbnism.pPbrbmeter != NULL_PTR) {
        free(ckMechbnism.pPbrbmeter);
    }
    freeCKAttributeArrby(ckpAttributes, ckAttributesLength);

    return jKeyHbndle ;
}
#endif

#ifdef P11_ENABLE_C_GENERATEKEYPAIR
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_GenerbteKeyPbir
 * Signbture: (JLsun/security/pkcs11/wrbpper/CK_MECHANISM;[Lsun/security/pkcs11/wrbpper/CK_ATTRIBUTE;[Lsun/security/pkcs11/wrbpper/CK_ATTRIBUTE;)[J
 * Pbrbmetermbpping:                          *PKCS11*
 * @pbrbm   jlong jSessionHbndle              CK_SESSION_HANDLE hSession
 * @pbrbm   jobject jMechbnism                CK_MECHANISM_PTR pMechbnism
 * @pbrbm   jobjectArrby jPublicKeyTemplbte   CK_ATTRIBUTE_PTR pPublicKeyTemplbte
 *                                            CK_ULONG ulPublicKeyAttributeCount
 * @pbrbm   jobjectArrby jPrivbteKeyTemplbte  CK_ATTRIBUTE_PTR pPrivbteKeyTemplbte
 *                                            CK_ULONG ulPrivbteKeyAttributeCount
 * @return  jlongArrby jKeyHbndles            CK_OBJECT_HANDLE_PTR phPublicKey
 *                                            CK_OBJECT_HANDLE_PTR phPublicKey
 */
JNIEXPORT jlongArrby JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1GenerbteKeyPbir
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jobject jMechbnism,
     jobjectArrby jPublicKeyTemplbte, jobjectArrby jPrivbteKeyTemplbte)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_MECHANISM ckMechbnism;
    CK_ATTRIBUTE_PTR ckpPublicKeyAttributes = NULL_PTR;
    CK_ATTRIBUTE_PTR ckpPrivbteKeyAttributes = NULL_PTR;
    CK_ULONG ckPublicKeyAttributesLength;
    CK_ULONG ckPrivbteKeyAttributesLength;
    CK_OBJECT_HANDLE_PTR ckpPublicKeyHbndle;  /* pointer to Public Key */
    CK_OBJECT_HANDLE_PTR ckpPrivbteKeyHbndle; /* pointer to Privbte Key */
    CK_OBJECT_HANDLE_PTR ckpKeyHbndles;     /* pointer to brrby with Public bnd Privbte Key */
    jlongArrby jKeyHbndles = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jMechbnismToCKMechbnism(env, jMechbnism, &ckMechbnism);
    if ((*env)->ExceptionCheck(env)) { return NULL; }

    ckpKeyHbndles = (CK_OBJECT_HANDLE_PTR) mblloc(2 * sizeof(CK_OBJECT_HANDLE));
    if (ckpKeyHbndles == NULL) {
        if (ckMechbnism.pPbrbmeter != NULL_PTR) {
            free(ckMechbnism.pPbrbmeter);
        }
        throwOutOfMemoryError(env, 0);
        return NULL;
    }
    ckpPublicKeyHbndle = ckpKeyHbndles;   /* first element of brrby is Public Key */
    ckpPrivbteKeyHbndle = (ckpKeyHbndles + 1);  /* second element of brrby is Privbte Key */

    jAttributeArrbyToCKAttributeArrby(env, jPublicKeyTemplbte, &ckpPublicKeyAttributes, &ckPublicKeyAttributesLength);
    if ((*env)->ExceptionCheck(env)) {
        if (ckMechbnism.pPbrbmeter != NULL_PTR) {
            free(ckMechbnism.pPbrbmeter);
        }
        free(ckpKeyHbndles);
        return NULL;
    }

    jAttributeArrbyToCKAttributeArrby(env, jPrivbteKeyTemplbte, &ckpPrivbteKeyAttributes, &ckPrivbteKeyAttributesLength);
    if ((*env)->ExceptionCheck(env)) {
        if (ckMechbnism.pPbrbmeter != NULL_PTR) {
            free(ckMechbnism.pPbrbmeter);
        }
        free(ckpKeyHbndles);
        freeCKAttributeArrby(ckpPublicKeyAttributes, ckPublicKeyAttributesLength);
        return NULL;
    }

    rv = (*ckpFunctions->C_GenerbteKeyPbir)(ckSessionHbndle, &ckMechbnism,
                     ckpPublicKeyAttributes, ckPublicKeyAttributesLength,
                     ckpPrivbteKeyAttributes, ckPrivbteKeyAttributesLength,
                     ckpPublicKeyHbndle, ckpPrivbteKeyHbndle);

    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jKeyHbndles = ckULongArrbyToJLongArrby(env, ckpKeyHbndles, 2);
    }

    if(ckMechbnism.pPbrbmeter != NULL_PTR) {
        free(ckMechbnism.pPbrbmeter);
    }
    free(ckpKeyHbndles);
    freeCKAttributeArrby(ckpPublicKeyAttributes, ckPublicKeyAttributesLength);
    freeCKAttributeArrby(ckpPrivbteKeyAttributes, ckPrivbteKeyAttributesLength);

    return jKeyHbndles ;
}
#endif

#ifdef P11_ENABLE_C_WRAPKEY
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_WrbpKey
 * Signbture: (JLsun/security/pkcs11/wrbpper/CK_MECHANISM;JJ)[B
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jobject jMechbnism          CK_MECHANISM_PTR pMechbnism
 * @pbrbm   jlong jWrbppingKeyHbndle    CK_OBJECT_HANDLE hWrbppingKey
 * @pbrbm   jlong jKeyHbndle            CK_OBJECT_HANDLE hKey
 * @return  jbyteArrby jWrbppedKey      CK_BYTE_PTR pWrbppedKey
 *                                      CK_ULONG_PTR pulWrbppedKeyLen
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1WrbpKey
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jobject jMechbnism, jlong jWrbppingKeyHbndle, jlong jKeyHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_MECHANISM ckMechbnism;
    CK_OBJECT_HANDLE ckWrbppingKeyHbndle;
    CK_OBJECT_HANDLE ckKeyHbndle;
    jbyteArrby jWrbppedKey = NULL;
    CK_RV rv;
    CK_BYTE BUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE_PTR ckpWrbppedKey = BUF;
    CK_ULONG ckWrbppedKeyLength = MAX_STACK_BUFFER_LEN;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jMechbnismToCKMechbnism(env, jMechbnism, &ckMechbnism);
    if ((*env)->ExceptionCheck(env)) { return NULL; }

    ckWrbppingKeyHbndle = jLongToCKULong(jWrbppingKeyHbndle);
    ckKeyHbndle = jLongToCKULong(jKeyHbndle);

    rv = (*ckpFunctions->C_WrbpKey)(ckSessionHbndle, &ckMechbnism, ckWrbppingKeyHbndle, ckKeyHbndle, ckpWrbppedKey, &ckWrbppedKeyLength);
    if (rv == CKR_BUFFER_TOO_SMALL) {
        ckpWrbppedKey = (CK_BYTE_PTR) mblloc(ckWrbppedKeyLength);
        if (ckpWrbppedKey == NULL) {
            if (ckMechbnism.pPbrbmeter != NULL_PTR) {
                free(ckMechbnism.pPbrbmeter);
            }
            throwOutOfMemoryError(env, 0);
            return NULL;
        }

        rv = (*ckpFunctions->C_WrbpKey)(ckSessionHbndle, &ckMechbnism, ckWrbppingKeyHbndle, ckKeyHbndle, ckpWrbppedKey, &ckWrbppedKeyLength);
    }
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jWrbppedKey = ckByteArrbyToJByteArrby(env, ckpWrbppedKey, ckWrbppedKeyLength);
    }

    if (ckpWrbppedKey != BUF) { free(ckpWrbppedKey); }
    if (ckMechbnism.pPbrbmeter != NULL_PTR) {
        free(ckMechbnism.pPbrbmeter);
    }
    return jWrbppedKey ;
}
#endif

#ifdef P11_ENABLE_C_UNWRAPKEY
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_UnwrbpKey
 * Signbture: (JLsun/security/pkcs11/wrbpper/CK_MECHANISM;J[B[Lsun/security/pkcs11/wrbpper/CK_ATTRIBUTE;)J
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jobject jMechbnism          CK_MECHANISM_PTR pMechbnism
 * @pbrbm   jlong jUnwrbppingKeyHbndle  CK_OBJECT_HANDLE hUnwrbppingKey
 * @pbrbm   jbyteArrby jWrbppedKey      CK_BYTE_PTR pWrbppedKey
 *                                      CK_ULONG_PTR pulWrbppedKeyLen
 * @pbrbm   jobjectArrby jTemplbte      CK_ATTRIBUTE_PTR pTemplbte
 *                                      CK_ULONG ulCount
 * @return  jlong jKeyHbndle            CK_OBJECT_HANDLE_PTR phKey
 */
JNIEXPORT jlong JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1UnwrbpKey
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jobject jMechbnism, jlong jUnwrbppingKeyHbndle,
     jbyteArrby jWrbppedKey, jobjectArrby jTemplbte)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_MECHANISM ckMechbnism;
    CK_OBJECT_HANDLE ckUnwrbppingKeyHbndle;
    CK_BYTE_PTR ckpWrbppedKey = NULL_PTR;
    CK_ULONG ckWrbppedKeyLength;
    CK_ATTRIBUTE_PTR ckpAttributes = NULL_PTR;
    CK_ULONG ckAttributesLength;
    CK_OBJECT_HANDLE ckKeyHbndle = 0;
    jlong jKeyHbndle = 0L;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0L; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jMechbnismToCKMechbnism(env, jMechbnism, &ckMechbnism);
    if ((*env)->ExceptionCheck(env)) { return 0L; }

    ckUnwrbppingKeyHbndle = jLongToCKULong(jUnwrbppingKeyHbndle);
    jByteArrbyToCKByteArrby(env, jWrbppedKey, &ckpWrbppedKey, &ckWrbppedKeyLength);
    if ((*env)->ExceptionCheck(env)) {
        if (ckMechbnism.pPbrbmeter != NULL_PTR) {
            free(ckMechbnism.pPbrbmeter);
        }
        return 0L;
    }

    jAttributeArrbyToCKAttributeArrby(env, jTemplbte, &ckpAttributes, &ckAttributesLength);
    if ((*env)->ExceptionCheck(env)) {
        if (ckMechbnism.pPbrbmeter != NULL_PTR) {
            free(ckMechbnism.pPbrbmeter);
        }
        free(ckpWrbppedKey);
        return 0L;
    }


    rv = (*ckpFunctions->C_UnwrbpKey)(ckSessionHbndle, &ckMechbnism, ckUnwrbppingKeyHbndle,
                 ckpWrbppedKey, ckWrbppedKeyLength,
                 ckpAttributes, ckAttributesLength, &ckKeyHbndle);

    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jKeyHbndle = ckLongToJLong(ckKeyHbndle);

#if 0
        /* chebck, if we must give b initiblizbtion vector bbck to Jbvb */
        if (ckMechbnism.mechbnism == CKM_KEY_WRAP_SET_OAEP) {
            /* we must copy bbck the unwrbpped key info to the jMechbnism object */
            copyBbckSetUnwrbppedKey(env, &ckMechbnism, jMechbnism);
        }
#endif
    }

    if (ckMechbnism.pPbrbmeter != NULL_PTR) {
        free(ckMechbnism.pPbrbmeter);
    }
    freeCKAttributeArrby(ckpAttributes, ckAttributesLength);
    free(ckpWrbppedKey);

    return jKeyHbndle ;
}
#endif

#ifdef P11_ENABLE_C_DERIVEKEY

void freeMbsterKeyDerivePbrbms(CK_MECHANISM_PTR ckMechbnism) {
    CK_SSL3_MASTER_KEY_DERIVE_PARAMS *pbrbms = (CK_SSL3_MASTER_KEY_DERIVE_PARAMS *) ckMechbnism->pPbrbmeter;
    if (pbrbms == NULL) {
        return;
    }

    if (pbrbms->RbndomInfo.pClientRbndom != NULL) {
        free(pbrbms->RbndomInfo.pClientRbndom);
    }
    if (pbrbms->RbndomInfo.pServerRbndom != NULL) {
        free(pbrbms->RbndomInfo.pServerRbndom);
    }
    if (pbrbms->pVersion != NULL) {
        free(pbrbms->pVersion);
    }
}

void freeEcdh1DerivePbrbms(CK_MECHANISM_PTR ckMechbnism) {
    CK_ECDH1_DERIVE_PARAMS *pbrbms = (CK_ECDH1_DERIVE_PARAMS *) ckMechbnism->pPbrbmeter;
    if (pbrbms == NULL) {
        return;
    }

    if (pbrbms->pShbredDbtb != NULL) {
        free(pbrbms->pShbredDbtb);
    }
    if (pbrbms->pPublicDbtb != NULL) {
        free(pbrbms->pPublicDbtb);
    }
}

/*
 * Copy bbck the PRF output to Jbvb.
 */
void copyBbckTLSPrfPbrbms(JNIEnv *env, CK_MECHANISM *ckMechbnism, jobject jMechbnism)
{
    jclbss jMechbnismClbss, jTLSPrfPbrbmsClbss;
    CK_TLS_PRF_PARAMS *ckTLSPrfPbrbms;
    jobject jTLSPrfPbrbms;
    jfieldID fieldID;
    CK_MECHANISM_TYPE ckMechbnismType;
    jlong jMechbnismType;
    CK_BYTE_PTR output;
    jobject jOutput;
    jint jLength;
    jbyte* jBytes;
    int i;

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

    /* get the nbtive CK_TLS_PRF_PARAMS */
    ckTLSPrfPbrbms = (CK_TLS_PRF_PARAMS *) ckMechbnism->pPbrbmeter;
    if (ckTLSPrfPbrbms != NULL_PTR) {
        /* get the Jbvb CK_TLS_PRF_PARAMS object (pPbrbmeter) */
        fieldID = (*env)->GetFieldID(env, jMechbnismClbss, "pPbrbmeter", "Ljbvb/lbng/Object;");
        if (fieldID == NULL) { return; }
        jTLSPrfPbrbms = (*env)->GetObjectField(env, jMechbnism, fieldID);

        /* copy bbck the client IV */
        jTLSPrfPbrbmsClbss = (*env)->FindClbss(env, CLASS_TLS_PRF_PARAMS);
        if (jTLSPrfPbrbmsClbss == NULL) { return; }
        fieldID = (*env)->GetFieldID(env, jTLSPrfPbrbmsClbss, "pOutput", "[B");
        if (fieldID == NULL) { return; }
        jOutput = (*env)->GetObjectField(env, jTLSPrfPbrbms, fieldID);
        output = ckTLSPrfPbrbms->pOutput;

        // Note: we bssume thbt the token returned exbctly bs mbny bytes bs we
        // requested. Anything else would not mbke sense.
        if (jOutput != NULL) {
            jLength = (*env)->GetArrbyLength(env, jOutput);
            jBytes = (*env)->GetByteArrbyElements(env, jOutput, NULL);
            if (jBytes == NULL) { return; }

            /* copy the bytes to the Jbvb buffer */
            for (i=0; i < jLength; i++) {
                jBytes[i] = ckByteToJByte(output[i]);
            }
            /* copy bbck the Jbvb buffer to the object */
            (*env)->RelebseByteArrbyElements(env, jOutput, jBytes, 0);
        }

        // free mblloc'd dbtb
        free(ckTLSPrfPbrbms->pSeed);
        free(ckTLSPrfPbrbms->pLbbel);
        free(ckTLSPrfPbrbms->pulOutputLen);
        free(ckTLSPrfPbrbms->pOutput);
    }
}

/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_DeriveKey
 * Signbture: (JLsun/security/pkcs11/wrbpper/CK_MECHANISM;J[Lsun/security/pkcs11/wrbpper/CK_ATTRIBUTE;)J
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jobject jMechbnism          CK_MECHANISM_PTR pMechbnism
 * @pbrbm   jlong jBbseKeyHbndle        CK_OBJECT_HANDLE hBbseKey
 * @pbrbm   jobjectArrby jTemplbte      CK_ATTRIBUTE_PTR pTemplbte
 *                                      CK_ULONG ulCount
 * @return  jlong jKeyHbndle            CK_OBJECT_HANDLE_PTR phKey
 */
JNIEXPORT jlong JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1DeriveKey
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jobject jMechbnism, jlong jBbseKeyHbndle, jobjectArrby jTemplbte)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_MECHANISM ckMechbnism;
    CK_OBJECT_HANDLE ckBbseKeyHbndle;
    CK_ATTRIBUTE_PTR ckpAttributes = NULL_PTR;
    CK_ULONG ckAttributesLength;
    CK_OBJECT_HANDLE ckKeyHbndle = 0;
    jlong jKeyHbndle = 0L;
    CK_RV rv;
    CK_OBJECT_HANDLE_PTR phKey = &ckKeyHbndle;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0L; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jMechbnismToCKMechbnism(env, jMechbnism, &ckMechbnism);
    if ((*env)->ExceptionCheck(env)) { return 0L; }

    ckBbseKeyHbndle = jLongToCKULong(jBbseKeyHbndle);
    jAttributeArrbyToCKAttributeArrby(env, jTemplbte, &ckpAttributes, &ckAttributesLength);
    if ((*env)->ExceptionCheck(env)) {
        if (ckMechbnism.pPbrbmeter != NULL_PTR) {
            free(ckMechbnism.pPbrbmeter);
        }
        return 0L;
    }

    switch (ckMechbnism.mechbnism) {
    cbse CKM_SSL3_KEY_AND_MAC_DERIVE:
    cbse CKM_TLS_KEY_AND_MAC_DERIVE:
    cbse CKM_TLS_PRF:
        // these mechbnism do not return b key hbndle vib phKey
        // set to NULL in cbse pedbntic implementbtions check for it
        phKey = NULL;
        brebk;
    defbult:
        // empty
        brebk;
    }

    rv = (*ckpFunctions->C_DeriveKey)(ckSessionHbndle, &ckMechbnism, ckBbseKeyHbndle,
                 ckpAttributes, ckAttributesLength, phKey);

    jKeyHbndle = ckLongToJLong(ckKeyHbndle);

    freeCKAttributeArrby(ckpAttributes, ckAttributesLength);

    switch (ckMechbnism.mechbnism) {
    cbse CKM_SSL3_MASTER_KEY_DERIVE:
    cbse CKM_TLS_MASTER_KEY_DERIVE:
        /* we must copy bbck the client version */
        copyBbckClientVersion(env, &ckMechbnism, jMechbnism);
        freeMbsterKeyDerivePbrbms(&ckMechbnism);
        brebk;
    cbse CKM_SSL3_MASTER_KEY_DERIVE_DH:
    cbse CKM_TLS_MASTER_KEY_DERIVE_DH:
        freeMbsterKeyDerivePbrbms(&ckMechbnism);
        brebk;
    cbse CKM_SSL3_KEY_AND_MAC_DERIVE:
    cbse CKM_TLS_KEY_AND_MAC_DERIVE:
        /* we must copy bbck the unwrbpped key info to the jMechbnism object */
        copyBbckSSLKeyMbtPbrbms(env, &ckMechbnism, jMechbnism);
        brebk;
    cbse CKM_TLS_PRF:
        copyBbckTLSPrfPbrbms(env, &ckMechbnism, jMechbnism);
        brebk;
    cbse CKM_ECDH1_DERIVE:
        freeEcdh1DerivePbrbms(&ckMechbnism);
        brebk;
    defbult:
        // empty
        brebk;
    }

    if (ckMechbnism.pPbrbmeter != NULL_PTR) {
        free(ckMechbnism.pPbrbmeter);
    }
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return 0L ; }

    return jKeyHbndle ;
}

/*
 * Copy bbck the client version informbtion from the nbtive
 * structure to the Jbvb object. This is only used for the
 * CKM_SSL3_MASTER_KEY_DERIVE mechbnism when used for deriving b key.
 *
 */
void copyBbckClientVersion(JNIEnv *env, CK_MECHANISM *ckMechbnism, jobject jMechbnism)
{
  jclbss jMechbnismClbss, jSSL3MbsterKeyDerivePbrbmsClbss, jVersionClbss;
  CK_SSL3_MASTER_KEY_DERIVE_PARAMS *ckSSL3MbsterKeyDerivePbrbms;
  CK_VERSION *ckVersion;
  jfieldID fieldID;
  CK_MECHANISM_TYPE ckMechbnismType;
  jlong jMechbnismType;
  jobject jSSL3MbsterKeyDerivePbrbms;
  jobject jVersion;

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

  /* get the nbtive CK_SSL3_MASTER_KEY_DERIVE_PARAMS */
  ckSSL3MbsterKeyDerivePbrbms = (CK_SSL3_MASTER_KEY_DERIVE_PARAMS *) ckMechbnism->pPbrbmeter;
  if (ckSSL3MbsterKeyDerivePbrbms != NULL_PTR) {
    /* get the nbtive CK_VERSION */
    ckVersion = ckSSL3MbsterKeyDerivePbrbms->pVersion;
    if (ckVersion != NULL_PTR) {
      /* get the Jbvb CK_SSL3_MASTER_KEY_DERIVE_PARAMS (pPbrbmeter) */
      fieldID = (*env)->GetFieldID(env, jMechbnismClbss, "pPbrbmeter", "Ljbvb/lbng/Object;");
      if (fieldID == NULL) { return; }

      jSSL3MbsterKeyDerivePbrbms = (*env)->GetObjectField(env, jMechbnism, fieldID);

      /* get the Jbvb CK_VERSION */
      jSSL3MbsterKeyDerivePbrbmsClbss = (*env)->FindClbss(env, CLASS_SSL3_MASTER_KEY_DERIVE_PARAMS);
      if (jSSL3MbsterKeyDerivePbrbmsClbss == NULL) { return; }
      fieldID = (*env)->GetFieldID(env, jSSL3MbsterKeyDerivePbrbmsClbss, "pVersion", "L"CLASS_VERSION";");
      if (fieldID == NULL) { return; }
      jVersion = (*env)->GetObjectField(env, jSSL3MbsterKeyDerivePbrbms, fieldID);

      /* now copy bbck the version from the nbtive structure to the Jbvb structure */

      /* copy bbck the mbjor version */
      jVersionClbss = (*env)->FindClbss(env, CLASS_VERSION);
      if (jVersionClbss == NULL) { return; }
      fieldID = (*env)->GetFieldID(env, jVersionClbss, "mbjor", "B");
      if (fieldID == NULL) { return; }
      (*env)->SetByteField(env, jVersion, fieldID, ckByteToJByte(ckVersion->mbjor));

      /* copy bbck the minor version */
      fieldID = (*env)->GetFieldID(env, jVersionClbss, "minor", "B");
      if (fieldID == NULL) { return; }
      (*env)->SetByteField(env, jVersion, fieldID, ckByteToJByte(ckVersion->minor));
    }
  }
}


/*
 * Copy bbck the derived keys bnd initiblizbtion vectors from the nbtive
 * structure to the Jbvb object. This is only used for the
 * CKM_SSL3_KEY_AND_MAC_DERIVE mechbnism when used for deriving b key.
 *
 */
void copyBbckSSLKeyMbtPbrbms(JNIEnv *env, CK_MECHANISM *ckMechbnism, jobject jMechbnism)
{
  jclbss jMechbnismClbss, jSSL3KeyMbtPbrbmsClbss, jSSL3KeyMbtOutClbss;
  CK_SSL3_KEY_MAT_PARAMS *ckSSL3KeyMbtPbrbm;
  CK_SSL3_KEY_MAT_OUT *ckSSL3KeyMbtOut;
  jfieldID fieldID;
  CK_MECHANISM_TYPE ckMechbnismType;
  jlong jMechbnismType;
  CK_BYTE_PTR iv;
  jobject jSSL3KeyMbtPbrbm;
  jobject jSSL3KeyMbtOut;
  jobject jIV;
  jint jLength;
  jbyte* jBytes;
  int i;

  /* get mechbnism */
  jMechbnismClbss= (*env)->FindClbss(env, CLASS_MECHANISM);
  if (jMechbnismClbss == NULL) { return; }
  fieldID = (*env)->GetFieldID(env, jMechbnismClbss, "mechbnism", "J");
  if (fieldID == NULL) { return; }
  jMechbnismType = (*env)->GetLongField(env, jMechbnism, fieldID);
  ckMechbnismType = jLongToCKULong(jMechbnismType);
  if (ckMechbnismType != ckMechbnism->mechbnism) {
    /* we do not hbve mbching types, this should not occur */
    return;
  }

  /* get the nbtive CK_SSL3_KEY_MAT_PARAMS */
  ckSSL3KeyMbtPbrbm = (CK_SSL3_KEY_MAT_PARAMS *) ckMechbnism->pPbrbmeter;
  if (ckSSL3KeyMbtPbrbm != NULL_PTR) {
    // free mblloc'd dbtb
    if (ckSSL3KeyMbtPbrbm->RbndomInfo.pClientRbndom != NULL) {
        free(ckSSL3KeyMbtPbrbm->RbndomInfo.pClientRbndom);
    }
    if (ckSSL3KeyMbtPbrbm->RbndomInfo.pServerRbndom != NULL) {
        free(ckSSL3KeyMbtPbrbm->RbndomInfo.pServerRbndom);
    }

    /* get the nbtive CK_SSL3_KEY_MAT_OUT */
    ckSSL3KeyMbtOut = ckSSL3KeyMbtPbrbm->pReturnedKeyMbteribl;
    if (ckSSL3KeyMbtOut != NULL_PTR) {
      /* get the Jbvb CK_SSL3_KEY_MAT_PARAMS (pPbrbmeter) */
      fieldID = (*env)->GetFieldID(env, jMechbnismClbss, "pPbrbmeter", "Ljbvb/lbng/Object;");
      if (fieldID == NULL) { return; }
      jSSL3KeyMbtPbrbm = (*env)->GetObjectField(env, jMechbnism, fieldID);

      /* get the Jbvb CK_SSL3_KEY_MAT_OUT */
      jSSL3KeyMbtPbrbmsClbss = (*env)->FindClbss(env, CLASS_SSL3_KEY_MAT_PARAMS);
      if (jSSL3KeyMbtPbrbmsClbss == NULL) { return; }
      fieldID = (*env)->GetFieldID(env, jSSL3KeyMbtPbrbmsClbss, "pReturnedKeyMbteribl", "L"CLASS_SSL3_KEY_MAT_OUT";");
      if (fieldID == NULL) { return; }
      jSSL3KeyMbtOut = (*env)->GetObjectField(env, jSSL3KeyMbtPbrbm, fieldID);

      /* now copy bbck bll the key hbndles bnd the initiblizbtion vectors */
      /* copy bbck client MAC secret hbndle */
      jSSL3KeyMbtOutClbss = (*env)->FindClbss(env, CLASS_SSL3_KEY_MAT_OUT);
      if (jSSL3KeyMbtOutClbss == NULL) { return; }
      fieldID = (*env)->GetFieldID(env, jSSL3KeyMbtOutClbss, "hClientMbcSecret", "J");
      if (fieldID == NULL) { return; }
      (*env)->SetLongField(env, jSSL3KeyMbtOut, fieldID, ckULongToJLong(ckSSL3KeyMbtOut->hClientMbcSecret));

      /* copy bbck server MAC secret hbndle */
      fieldID = (*env)->GetFieldID(env, jSSL3KeyMbtOutClbss, "hServerMbcSecret", "J");
      if (fieldID == NULL) { return; }
      (*env)->SetLongField(env, jSSL3KeyMbtOut, fieldID, ckULongToJLong(ckSSL3KeyMbtOut->hServerMbcSecret));

      /* copy bbck client secret key hbndle */
      fieldID = (*env)->GetFieldID(env, jSSL3KeyMbtOutClbss, "hClientKey", "J");
      if (fieldID == NULL) { return; }
      (*env)->SetLongField(env, jSSL3KeyMbtOut, fieldID, ckULongToJLong(ckSSL3KeyMbtOut->hClientKey));

      /* copy bbck server secret key hbndle */
      fieldID = (*env)->GetFieldID(env, jSSL3KeyMbtOutClbss, "hServerKey", "J");
      if (fieldID == NULL) { return; }
      (*env)->SetLongField(env, jSSL3KeyMbtOut, fieldID, ckULongToJLong(ckSSL3KeyMbtOut->hServerKey));

      /* copy bbck the client IV */
      fieldID = (*env)->GetFieldID(env, jSSL3KeyMbtOutClbss, "pIVClient", "[B");
      if (fieldID == NULL) { return; }
      jIV = (*env)->GetObjectField(env, jSSL3KeyMbtOut, fieldID);
      iv = ckSSL3KeyMbtOut->pIVClient;

      if (jIV != NULL) {
        jLength = (*env)->GetArrbyLength(env, jIV);
        jBytes = (*env)->GetByteArrbyElements(env, jIV, NULL);
        if (jBytes == NULL) { return; }
        /* copy the bytes to the Jbvb buffer */
        for (i=0; i < jLength; i++) {
          jBytes[i] = ckByteToJByte(iv[i]);
        }
        /* copy bbck the Jbvb buffer to the object */
        (*env)->RelebseByteArrbyElements(env, jIV, jBytes, 0);
      }
      // free mblloc'd dbtb
      free(ckSSL3KeyMbtOut->pIVClient);

      /* copy bbck the server IV */
      fieldID = (*env)->GetFieldID(env, jSSL3KeyMbtOutClbss, "pIVServer", "[B");
      if (fieldID == NULL) { return; }
      jIV = (*env)->GetObjectField(env, jSSL3KeyMbtOut, fieldID);
      iv = ckSSL3KeyMbtOut->pIVServer;

      if (jIV != NULL) {
        jLength = (*env)->GetArrbyLength(env, jIV);
        jBytes = (*env)->GetByteArrbyElements(env, jIV, NULL);
        if (jBytes == NULL) { return; }
        /* copy the bytes to the Jbvb buffer */
        for (i=0; i < jLength; i++) {
          jBytes[i] = ckByteToJByte(iv[i]);
        }
        /* copy bbck the Jbvb buffer to the object */
        (*env)->RelebseByteArrbyElements(env, jIV, jBytes, 0);
      }
      // free mblloc'd dbtb
      free(ckSSL3KeyMbtOut->pIVServer);
      free(ckSSL3KeyMbtOut);
    }
  }
}

#endif
