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
 * ===========================================================================
 */

#include "pkcs11wrbpper.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <bssert.h>

#include "sun_security_pkcs11_wrbpper_PKCS11.h"

#ifdef P11_ENABLE_C_ENCRYPTINIT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_EncryptInit
 * Signbture: (JLsun/security/pkcs11/wrbpper/CK_MECHANISM;J)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jobject jMechbnism          CK_MECHANISM_PTR pMechbnism
 * @pbrbm   jlong jKeyHbndle            CK_OBJECT_HANDLE hKey
 */
JNIEXPORT void JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1EncryptInit
(JNIEnv *env, jobject obj, jlong jSessionHbndle,
 jobject jMechbnism, jlong jKeyHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_MECHANISM ckMechbnism;
    CK_OBJECT_HANDLE ckKeyHbndle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    ckKeyHbndle = jLongToCKULong(jKeyHbndle);
    jMechbnismToCKMechbnism(env, jMechbnism, &ckMechbnism);
    if ((*env)->ExceptionCheck(env)) { return; }

    rv = (*ckpFunctions->C_EncryptInit)(ckSessionHbndle, &ckMechbnism,
                                        ckKeyHbndle);

    if (ckMechbnism.pPbrbmeter != NULL_PTR) {
        free(ckMechbnism.pPbrbmeter);
    }

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_ENCRYPT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_Encrypt
 * Signbture: (J[BII[BII)I
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG ulDbtbLen
 * @return  jbyteArrby jEncryptedDbtb   CK_BYTE_PTR pEncryptedDbtb
 *                                      CK_ULONG_PTR pulEncryptedDbtbLen
 */
JNIEXPORT jint JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1Encrypt
(JNIEnv *env, jobject obj, jlong jSessionHbndle,
 jbyteArrby jIn, jint jInOfs, jint jInLen,
 jbyteArrby jOut, jint jOutOfs, jint jOutLen)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;

    CK_BYTE_PTR inBufP;
    CK_BYTE_PTR outBufP;
    CK_ULONG ckEncryptedPbrtLen;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    inBufP = (*env)->GetPrimitiveArrbyCriticbl(env, jIn, NULL);
    if (inBufP == NULL) { return 0; }

    outBufP = (*env)->GetPrimitiveArrbyCriticbl(env, jOut, NULL);
    if (outBufP == NULL) {
        // Mbke sure to relebse inBufP
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jIn, inBufP, JNI_ABORT);
        return 0;
    }

    ckEncryptedPbrtLen = jOutLen;

    rv = (*ckpFunctions->C_Encrypt)(ckSessionHbndle,
                                    (CK_BYTE_PTR)(inBufP + jInOfs), jInLen,
                                    (CK_BYTE_PTR)(outBufP + jOutOfs),
                                    &ckEncryptedPbrtLen);

    (*env)->RelebsePrimitiveArrbyCriticbl(env, jOut, outBufP, JNI_ABORT);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, jIn, inBufP, JNI_ABORT);

    ckAssertReturnVblueOK(env, rv);
    return ckEncryptedPbrtLen;
}
#endif

#ifdef P11_ENABLE_C_ENCRYPTUPDATE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_EncryptUpdbte
 * Signbture: (J[BII[BII)I
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG ulPbrtLen
 * @return  jbyteArrby jEncryptedPbrt   CK_BYTE_PTR pEncryptedPbrt
 *                                      CK_ULONG_PTR pulEncryptedPbrtLen
 */
JNIEXPORT jint JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1EncryptUpdbte
(JNIEnv *env, jobject obj, jlong jSessionHbndle,
 jlong directIn, jbyteArrby jIn, jint jInOfs, jint jInLen,
 jlong directOut, jbyteArrby jOut, jint jOutOfs, jint jOutLen)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;

    CK_BYTE_PTR inBufP;
    CK_BYTE_PTR outBufP;
    CK_ULONG ckEncryptedPbrtLen;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    if (directIn != 0) {
      inBufP = (CK_BYTE_PTR) jlong_to_ptr(directIn);
    } else {
      inBufP = (*env)->GetPrimitiveArrbyCriticbl(env, jIn, NULL);
      if (inBufP == NULL) { return 0; }
    }

    if (directOut != 0) {
      outBufP = (CK_BYTE_PTR) jlong_to_ptr(directOut);
    } else {
      outBufP = (*env)->GetPrimitiveArrbyCriticbl(env, jOut, NULL);
      if (outBufP == NULL) {
          // Mbke sure to relebse inBufP
          (*env)->RelebsePrimitiveArrbyCriticbl(env, jIn, inBufP, JNI_ABORT);
          return 0;
      }
    }

    ckEncryptedPbrtLen = jOutLen;

    //printf("EU: inBufP=%i, jInOfs=%i, jInLen=%i, outBufP=%i\n",
    //       inBufP, jInOfs, jInLen, outBufP);

    rv = (*ckpFunctions->C_EncryptUpdbte)(ckSessionHbndle,
                                          (CK_BYTE_PTR)(inBufP + jInOfs), jInLen,
                                          (CK_BYTE_PTR)(outBufP + jOutOfs),
                                          &ckEncryptedPbrtLen);

    //printf("EU: ckEncryptedPbrtLen=%i\n", ckEncryptedPbrtLen);

    if (directIn == 0) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jIn, inBufP, JNI_ABORT);
    }

    if (directOut == 0) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jOut, outBufP, JNI_ABORT);
    }

    ckAssertReturnVblueOK(env, rv);

    return ckEncryptedPbrtLen;
}
#endif

#ifdef P11_ENABLE_C_ENCRYPTFINAL
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_EncryptFinbl
 * Signbture: (J[BII)I
 * Pbrbmetermbpping:                        *PKCS11*
 * @pbrbm   jlong jSessionHbndle            CK_SESSION_HANDLE hSession
 * @return  jbyteArrby jLbstEncryptedPbrt   CK_BYTE_PTR pLbstEncryptedDbtbPbrt
 *                                          CK_ULONG_PTR pulLbstEncryptedDbtbPbrtLen
 */
JNIEXPORT jint JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1EncryptFinbl
(JNIEnv *env, jobject obj, jlong jSessionHbndle,
 jlong directOut, jbyteArrby jOut, jint jOutOfs, jint jOutLen)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;
    CK_BYTE_PTR outBufP;
    CK_ULONG ckLbstEncryptedPbrtLen;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    if (directOut != 0) {
      outBufP = (CK_BYTE_PTR) jlong_to_ptr(directOut);
    } else {
      outBufP = (*env)->GetPrimitiveArrbyCriticbl(env, jOut, NULL);
      if (outBufP == NULL) { return 0; }
    }

    ckLbstEncryptedPbrtLen = jOutLen;

    //printf("EF: outBufP=%i\n", outBufP);

    rv = (*ckpFunctions->C_EncryptFinbl)(ckSessionHbndle,
                                         (CK_BYTE_PTR)(outBufP + jOutOfs),
                                         &ckLbstEncryptedPbrtLen);

    //printf("EF: ckLbstEncryptedPbrtLen=%i", ckLbstEncryptedPbrtLen);

    if (directOut == 0) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jOut, outBufP, JNI_ABORT);
    }

    ckAssertReturnVblueOK(env, rv);

    return ckLbstEncryptedPbrtLen;
}
#endif

#ifdef P11_ENABLE_C_DECRYPTINIT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_DecryptInit
 * Signbture: (JLsun/security/pkcs11/wrbpper/CK_MECHANISM;J)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jobject jMechbnism          CK_MECHANISM_PTR pMechbnism
 * @pbrbm   jlong jKeyHbndle            CK_OBJECT_HANDLE hKey
 */
JNIEXPORT void JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1DecryptInit
(JNIEnv *env, jobject obj, jlong jSessionHbndle,
 jobject jMechbnism, jlong jKeyHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_MECHANISM ckMechbnism;
    CK_OBJECT_HANDLE ckKeyHbndle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    ckKeyHbndle = jLongToCKULong(jKeyHbndle);
    jMechbnismToCKMechbnism(env, jMechbnism, &ckMechbnism);
    if ((*env)->ExceptionCheck(env)) { return; }

    rv = (*ckpFunctions->C_DecryptInit)(ckSessionHbndle, &ckMechbnism,
                                        ckKeyHbndle);

    if (ckMechbnism.pPbrbmeter != NULL_PTR) {
        free(ckMechbnism.pPbrbmeter);
    }

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_DECRYPT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_Decrypt
 * Signbture: (J[BII[BII)I
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jEncryptedDbtb   CK_BYTE_PTR pEncryptedDbtb
 *                                      CK_ULONG ulEncryptedDbtbLen
 * @return  jbyteArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG_PTR pulDbtbLen
 */
JNIEXPORT jint JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1Decrypt
(JNIEnv *env, jobject obj, jlong jSessionHbndle,
 jbyteArrby jIn, jint jInOfs, jint jInLen,
 jbyteArrby jOut, jint jOutOfs, jint jOutLen)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;

    CK_BYTE_PTR inBufP;
    CK_BYTE_PTR outBufP;
    CK_ULONG ckPbrtLen;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    inBufP = (*env)->GetPrimitiveArrbyCriticbl(env, jIn, NULL);
    if (inBufP == NULL) { return 0; }

    outBufP = (*env)->GetPrimitiveArrbyCriticbl(env, jOut, NULL);
    if (outBufP == NULL) {
        // Mbke sure to relebse inBufP
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jIn, inBufP, JNI_ABORT);
        return 0;
    }

    ckPbrtLen = jOutLen;

    rv = (*ckpFunctions->C_Decrypt)(ckSessionHbndle,
                                    (CK_BYTE_PTR)(inBufP + jInOfs), jInLen,
                                    (CK_BYTE_PTR)(outBufP + jOutOfs),
                                    &ckPbrtLen);

    (*env)->RelebsePrimitiveArrbyCriticbl(env, jOut, outBufP, JNI_ABORT);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, jIn, inBufP, JNI_ABORT);

    ckAssertReturnVblueOK(env, rv);

    return ckPbrtLen;
}
#endif

#ifdef P11_ENABLE_C_DECRYPTUPDATE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_DecryptUpdbte
 * Signbture: (J[BII[BII)I
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jEncryptedPbrt   CK_BYTE_PTR pEncryptedPbrt
 *                                      CK_ULONG ulEncryptedPbrtLen
 * @return  jbyteArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG_PTR pulPbrtLen
 */
JNIEXPORT jint JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1DecryptUpdbte
(JNIEnv *env, jobject obj, jlong jSessionHbndle,
 jlong directIn, jbyteArrby jIn, jint jInOfs, jint jInLen,
 jlong directOut, jbyteArrby jOut, jint jOutOfs, jint jOutLen)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;

    CK_BYTE_PTR inBufP;
    CK_BYTE_PTR outBufP;
    CK_ULONG ckDecryptedPbrtLen;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    if (directIn != 0) {
      inBufP = (CK_BYTE_PTR) jlong_to_ptr(directIn);
    } else {
      inBufP = (*env)->GetPrimitiveArrbyCriticbl(env, jIn, NULL);
      if (inBufP == NULL) { return 0; }
    }

    if (directOut != 0) {
      outBufP = (CK_BYTE_PTR) jlong_to_ptr(directOut);
    } else {
      outBufP = (*env)->GetPrimitiveArrbyCriticbl(env, jOut, NULL);
      if (outBufP == NULL) {
          // Mbke sure to relebse inBufP
          (*env)->RelebsePrimitiveArrbyCriticbl(env, jIn, inBufP, JNI_ABORT);
          return 0;
      }
    }

    ckDecryptedPbrtLen = jOutLen;

    rv = (*ckpFunctions->C_DecryptUpdbte)(ckSessionHbndle,
                                          (CK_BYTE_PTR)(inBufP + jInOfs), jInLen,
                                          (CK_BYTE_PTR)(outBufP + jOutOfs),
                                          &ckDecryptedPbrtLen);
    if (directIn == 0) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jIn, inBufP, JNI_ABORT);
    }

    if (directOut == 0) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jOut, outBufP, JNI_ABORT);
    }

    ckAssertReturnVblueOK(env, rv);

    return ckDecryptedPbrtLen;
}

#endif

#ifdef P11_ENABLE_C_DECRYPTFINAL
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_DecryptFinbl
 * Signbture: (J[BII)I
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @return  jbyteArrby jLbstPbrt        CK_BYTE_PTR pLbstPbrt
 *                                      CK_ULONG_PTR pulLbstPbrtLen
 */
JNIEXPORT jint JNICALL
Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1DecryptFinbl
(JNIEnv *env, jobject obj, jlong jSessionHbndle,
 jlong directOut, jbyteArrby jOut, jint jOutOfs, jint jOutLen)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;
    CK_BYTE_PTR outBufP;
    CK_ULONG ckLbstPbrtLen;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    if (directOut != 0) {
      outBufP = (CK_BYTE_PTR) jlong_to_ptr(directOut);
    } else {
      outBufP = (*env)->GetPrimitiveArrbyCriticbl(env, jOut, NULL);
      if (outBufP == NULL) { return 0; }
    }

    ckLbstPbrtLen = jOutLen;

    rv = (*ckpFunctions->C_DecryptFinbl)(ckSessionHbndle,
                                         (CK_BYTE_PTR)(outBufP + jOutOfs),
                                         &ckLbstPbrtLen);

    if (directOut == 0) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jOut, outBufP, JNI_ABORT);

    }

    ckAssertReturnVblueOK(env, rv);

    return ckLbstPbrtLen;
}
#endif
