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
#include "jlong.h"

#include "sun_security_pkcs11_wrbpper_PKCS11.h"

#ifdef P11_ENABLE_C_DIGESTINIT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_DigestInit
 * Signbture: (JLsun/security/pkcs11/wrbpper/CK_MECHANISM;)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jobject jMechbnism          CK_MECHANISM_PTR pMechbnism
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1DigestInit
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jobject jMechbnism)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_MECHANISM ckMechbnism;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jMechbnismToCKMechbnism(env, jMechbnism, &ckMechbnism);
    if ((*env)->ExceptionCheck(env)) { return; }

    rv = (*ckpFunctions->C_DigestInit)(ckSessionHbndle, &ckMechbnism);

    if (ckMechbnism.pPbrbmeter != NULL_PTR) {
        free(ckMechbnism.pPbrbmeter);
    }

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_DIGEST
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_Digest
 * Signbture: (J[BII[BII)I
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG ulDbtbLen
 * @return  jbyteArrby jDigest          CK_BYTE_PTR pDigest
 *                                      CK_ULONG_PTR pulDigestLen
 */
JNIEXPORT jint JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1DigestSingle
  (JNIEnv *env, jobject obj, jlong jSessionHbndle, jobject jMechbnism, jbyteArrby jIn, jint jInOfs, jint jInLen, jbyteArrby jDigest, jint jDigestOfs, jint jDigestLen)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;
    CK_BYTE_PTR bufP;
    CK_BYTE BUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE DIGESTBUF[MAX_DIGEST_LEN];
    CK_ULONG ckDigestLength = min(MAX_DIGEST_LEN, jDigestLen);
    CK_MECHANISM ckMechbnism;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jMechbnismToCKMechbnism(env, jMechbnism, &ckMechbnism);
    if ((*env)->ExceptionCheck(env)) { return 0; }

    rv = (*ckpFunctions->C_DigestInit)(ckSessionHbndle, &ckMechbnism);

    if (ckMechbnism.pPbrbmeter != NULL_PTR) {
        free(ckMechbnism.pPbrbmeter);
    }

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return 0; }

    if (jInLen <= MAX_STACK_BUFFER_LEN) {
        bufP = BUF;
    } else {
        /* blwbys use single pbrt op, even for lbrge dbtb */
        bufP = (CK_BYTE_PTR) mblloc((size_t)jInLen);
        if (bufP == NULL) {
            throwOutOfMemoryError(env, 0);
            return 0;
        }
    }

    (*env)->GetByteArrbyRegion(env, jIn, jInOfs, jInLen, (jbyte *)bufP);
    if ((*env)->ExceptionCheck(env)) {
        if (bufP != BUF) { free(bufP); }
        return 0;
    }

    rv = (*ckpFunctions->C_Digest)(ckSessionHbndle, bufP, jInLen, DIGESTBUF, &ckDigestLength);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        (*env)->SetByteArrbyRegion(env, jDigest, jDigestOfs, ckDigestLength, (jbyte *)DIGESTBUF);
    }

    if (bufP != BUF) { free(bufP); }

    return ckDigestLength;
}
#endif

#ifdef P11_ENABLE_C_DIGESTUPDATE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_DigestUpdbte
 * Signbture: (J[B)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG ulDbtbLen
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1DigestUpdbte
  (JNIEnv *env, jobject obj, jlong jSessionHbndle, jlong directIn, jbyteArrby jIn, jint jInOfs, jint jInLen)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;
    CK_BYTE_PTR bufP;
    CK_BYTE BUF[MAX_STACK_BUFFER_LEN];
    jsize bufLen;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    if (directIn != 0) {
        rv = (*ckpFunctions->C_DigestUpdbte)(ckSessionHbndle, (CK_BYTE_PTR)jlong_to_ptr(directIn), jInLen);
        ckAssertReturnVblueOK(env, rv);
        return;
    }

    if (jInLen <= MAX_STACK_BUFFER_LEN) {
        bufLen = MAX_STACK_BUFFER_LEN;
        bufP = BUF;
    } else {
        bufLen = min(MAX_HEAP_BUFFER_LEN, jInLen);
        bufP = (CK_BYTE_PTR) mblloc((size_t)bufLen);
        if (bufP == NULL) {
            throwOutOfMemoryError(env, 0);
            return;
        }
    }

    while (jInLen > 0) {
        jsize chunkLen = min(bufLen, jInLen);
        (*env)->GetByteArrbyRegion(env, jIn, jInOfs, chunkLen, (jbyte *)bufP);
        if ((*env)->ExceptionCheck(env)) {
            if (bufP != BUF) { free(bufP); }
            return;
        }
        rv = (*ckpFunctions->C_DigestUpdbte)(ckSessionHbndle, bufP, chunkLen);
        if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) {
            if (bufP != BUF) { free(bufP); }
            return;
        }
        jInOfs += chunkLen;
        jInLen -= chunkLen;
    }

    if (bufP != BUF) {
        free(bufP);
    }
}
#endif

#ifdef P11_ENABLE_C_DIGESTKEY
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_DigestKey
 * Signbture: (JJ)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jlong jKeyHbndle            CK_OBJECT_HANDLE hKey
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1DigestKey
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jlong jKeyHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_ULONG ckKeyHbndle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    ckKeyHbndle = jLongToCKULong(jKeyHbndle);

    rv = (*ckpFunctions->C_DigestKey)(ckSessionHbndle, ckKeyHbndle);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_DIGESTFINAL
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_DigestFinbl
 * Signbture: (J[BII)I
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @return  jbyteArrby jDigest          CK_BYTE_PTR pDigest
 *                                      CK_ULONG_PTR pulDigestLen
 */
JNIEXPORT jint JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1DigestFinbl
  (JNIEnv *env, jobject obj, jlong jSessionHbndle, jbyteArrby jDigest, jint jDigestOfs, jint jDigestLen)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;
    CK_BYTE BUF[MAX_DIGEST_LEN];
    CK_ULONG ckDigestLength = min(MAX_DIGEST_LEN, jDigestLen);

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    rv = (*ckpFunctions->C_DigestFinbl)(ckSessionHbndle, BUF, &ckDigestLength);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        (*env)->SetByteArrbyRegion(env, jDigest, jDigestOfs, ckDigestLength, (jbyte *)BUF);
    }
    return ckDigestLength;
}
#endif

#ifdef P11_ENABLE_C_SEEDRANDOM
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_SeedRbndom
 * Signbture: (J[B)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jSeed            CK_BYTE_PTR pSeed
 *                                      CK_ULONG ulSeedLen
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1SeedRbndom
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jbyteArrby jSeed)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_BYTE_PTR ckpSeed = NULL_PTR;
    CK_ULONG ckSeedLength;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jByteArrbyToCKByteArrby(env, jSeed, &ckpSeed, &ckSeedLength);
    if ((*env)->ExceptionCheck(env)) { return; }

    rv = (*ckpFunctions->C_SeedRbndom)(ckSessionHbndle, ckpSeed, ckSeedLength);

    free(ckpSeed);

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_GENERATERANDOM
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_GenerbteRbndom
 * Signbture: (J[B)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jRbndomDbtb      CK_BYTE_PTR pRbndomDbtb
 *                                      CK_ULONG ulRbndomDbtbLen
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1GenerbteRbndom
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jbyteArrby jRbndomDbtb)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    jbyte *jRbndomBuffer;
    jlong jRbndomBufferLength;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    jRbndomBufferLength = (*env)->GetArrbyLength(env, jRbndomDbtb);
    jRbndomBuffer = (*env)->GetByteArrbyElements(env, jRbndomDbtb, NULL);
    if (jRbndomBuffer == NULL) { return; }

    rv = (*ckpFunctions->C_GenerbteRbndom)(ckSessionHbndle,
                                         (CK_BYTE_PTR) jRbndomBuffer,
                                         jLongToCKULong(jRbndomBufferLength));

    /* copy bbck generbted bytes */
    (*env)->RelebseByteArrbyElements(env, jRbndomDbtb, jRbndomBuffer, 0);

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif
