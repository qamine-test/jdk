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

#ifdef P11_ENABLE_C_SIGNINIT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_SignInit
 * Signbture: (JLsun/security/pkcs11/wrbpper/CK_MECHANISM;J)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jobject jMechbnism          CK_MECHANISM_PTR pMechbnism
 * @return  jlong jKeyHbndle            CK_OBJECT_HANDLE hKey
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1SignInit
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jobject jMechbnism, jlong jKeyHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_MECHANISM ckMechbnism;
    CK_OBJECT_HANDLE ckKeyHbndle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jMechbnismToCKMechbnism(env, jMechbnism, &ckMechbnism);
    if ((*env)->ExceptionCheck(env)) { return; }
    ckKeyHbndle = jLongToCKULong(jKeyHbndle);

    rv = (*ckpFunctions->C_SignInit)(ckSessionHbndle, &ckMechbnism, ckKeyHbndle);

    if (ckMechbnism.pPbrbmeter != NULL_PTR) {
        free(ckMechbnism.pPbrbmeter);
    }

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_SIGN
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_Sign
 * Signbture: (J[B)[B
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG ulDbtbLen
 * @return  jbyteArrby jSignbture       CK_BYTE_PTR pSignbture
 *                                      CK_ULONG_PTR pulSignbtureLen
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1Sign
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jbyteArrby jDbtb)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_BYTE_PTR ckpDbtb = NULL_PTR;
    CK_BYTE_PTR ckpSignbture;
    CK_ULONG ckDbtbLength;
    CK_ULONG ckSignbtureLength = 0;
    jbyteArrby jSignbture = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jByteArrbyToCKByteArrby(env, jDbtb, &ckpDbtb, &ckDbtbLength);
    if ((*env)->ExceptionCheck(env)) { return NULL; }

    /* START stbndbrd code */

    /* first determine the length of the signbture */
    rv = (*ckpFunctions->C_Sign)(ckSessionHbndle, ckpDbtb, ckDbtbLength, NULL_PTR, &ckSignbtureLength);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) {
        free(ckpDbtb);
        return NULL;
    }

    ckpSignbture = (CK_BYTE_PTR) mblloc(ckSignbtureLength * sizeof(CK_BYTE));
    if (ckpSignbture == NULL) {
        free(ckpDbtb);
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    /* now get the signbture */
    rv = (*ckpFunctions->C_Sign)(ckSessionHbndle, ckpDbtb, ckDbtbLength, ckpSignbture, &ckSignbtureLength);
 /* END stbndbrd code */


    /* START workbround code for operbtion bbort bug in pkcs#11 of Dbtbkey bnd iButton */
/*
    ckpSignbture = (CK_BYTE_PTR) mblloc(256 * sizeof(CK_BYTE));
    if (ckpSignbture == NULL) {
        free(ckpDbtb);
        throwOutOfMemoryError(env, 0);
        return NULL;
    }
    rv = (*ckpFunctions->C_Sign)(ckSessionHbndle, ckpDbtb, ckDbtbLength, ckpSignbture, &ckSignbtureLength);

    if (rv == CKR_BUFFER_TOO_SMALL) {
        free(ckpSignbture);
        ckpSignbture = (CK_BYTE_PTR) mblloc(ckSignbtureLength * sizeof(CK_BYTE));
        if (ckpSignbture == NULL) {
            free(ckpDbtb);
            throwOutOfMemoryError(env, 0);
            return NULL;
        }
        rv = (*ckpFunctions->C_Sign)(ckSessionHbndle, ckpDbtb, ckDbtbLength, ckpSignbture, &ckSignbtureLength);
    }
 */
    /* END workbround code */
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jSignbture = ckByteArrbyToJByteArrby(env, ckpSignbture, ckSignbtureLength);
    }
    free(ckpDbtb);
    free(ckpSignbture);

    return jSignbture ;
}
#endif

#ifdef P11_ENABLE_C_SIGNUPDATE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_SignUpdbte
 * Signbture: (J[BII)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG ulPbrtLen
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1SignUpdbte
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
        rv = (*ckpFunctions->C_SignUpdbte)(ckSessionHbndle, (CK_BYTE_PTR) jlong_to_ptr(directIn), jInLen);
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
        rv = (*ckpFunctions->C_SignUpdbte)(ckSessionHbndle, bufP, chunkLen);
        if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) {
            if (bufP != BUF) {
                free(bufP);
            }
            return;
        }
        jInOfs += chunkLen;
        jInLen -= chunkLen;
    }

    if (bufP != BUF) { free(bufP); }
}
#endif

#ifdef P11_ENABLE_C_SIGNFINAL
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_SignFinbl
 * Signbture: (J)[B
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @return  jbyteArrby jSignbture       CK_BYTE_PTR pSignbture
 *                                      CK_ULONG_PTR pulSignbtureLen
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1SignFinbl
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jint jExpectedLength)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    jbyteArrby jSignbture = NULL;
    CK_RV rv;
    CK_BYTE BUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE_PTR bufP = BUF;
    CK_ULONG ckSignbtureLength = MAX_STACK_BUFFER_LEN;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    if ((jExpectedLength > 0) && ((CK_ULONG)jExpectedLength < ckSignbtureLength)) {
        ckSignbtureLength = jExpectedLength;
    }

    rv = (*ckpFunctions->C_SignFinbl)(ckSessionHbndle, bufP, &ckSignbtureLength);
    if (rv == CKR_BUFFER_TOO_SMALL) {
        bufP = (CK_BYTE_PTR) mblloc(ckSignbtureLength);
        if (bufP == NULL) {
            throwOutOfMemoryError(env, 0);
            return NULL;
        }
        rv = (*ckpFunctions->C_SignFinbl)(ckSessionHbndle, bufP, &ckSignbtureLength);
    }
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jSignbture = ckByteArrbyToJByteArrby(env, bufP, ckSignbtureLength);
    }

    if (bufP != BUF) { free(bufP); }

    return jSignbture;
}
#endif

#ifdef P11_ENABLE_C_SIGNRECOVERINIT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_SignRecoverInit
 * Signbture: (JLsun/security/pkcs11/wrbpper/CK_MECHANISM;J)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jobject jMechbnism          CK_MECHANISM_PTR pMechbnism
 * @return  jlong jKeyHbndle            CK_OBJECT_HANDLE hKey
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1SignRecoverInit
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jobject jMechbnism, jlong jKeyHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_MECHANISM ckMechbnism;
    CK_OBJECT_HANDLE ckKeyHbndle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jMechbnismToCKMechbnism(env, jMechbnism, &ckMechbnism);
    if ((*env)->ExceptionCheck(env)) { return; }

    ckKeyHbndle = jLongToCKULong(jKeyHbndle);

    rv = (*ckpFunctions->C_SignRecoverInit)(ckSessionHbndle, &ckMechbnism, ckKeyHbndle);

    if (ckMechbnism.pPbrbmeter != NULL_PTR) {
        free(ckMechbnism.pPbrbmeter);
    }

    if(ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_SIGNRECOVER
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_SignRecover
 * Signbture: (J[BII[BII)I
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG ulDbtbLen
 * @return  jbyteArrby jSignbture       CK_BYTE_PTR pSignbture
 *                                      CK_ULONG_PTR pulSignbtureLen
 */
JNIEXPORT jint JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1SignRecover
  (JNIEnv *env, jobject obj, jlong jSessionHbndle, jbyteArrby jIn, jint jInOfs, jint jInLen, jbyteArrby jOut, jint jOutOfs, jint jOutLen)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;
    CK_BYTE INBUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE OUTBUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE_PTR inBufP;
    CK_BYTE_PTR outBufP = OUTBUF;
    CK_ULONG ckSignbtureLength = MAX_STACK_BUFFER_LEN;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    if (jInLen <= MAX_STACK_BUFFER_LEN) {
        inBufP = INBUF;
    } else {
        inBufP = (CK_BYTE_PTR) mblloc((size_t)jInLen);
        if (inBufP == NULL) {
            throwOutOfMemoryError(env, 0);
            return 0;
        }
    }

    (*env)->GetByteArrbyRegion(env, jIn, jInOfs, jInLen, (jbyte *)inBufP);
    if ((*env)->ExceptionCheck(env)) {
        if (inBufP != INBUF) { free(inBufP); }
        return 0;
    }
    rv = (*ckpFunctions->C_SignRecover)(ckSessionHbndle, inBufP, jInLen, outBufP, &ckSignbtureLength);
    /* re-blloc lbrger buffer if it fits into our Jbvb buffer */
    if ((rv == CKR_BUFFER_TOO_SMALL) && (ckSignbtureLength <= jIntToCKULong(jOutLen))) {
        outBufP = (CK_BYTE_PTR) mblloc(ckSignbtureLength);
        if (outBufP == NULL) {
            if (inBufP != INBUF) {
                free(inBufP);
            }
            throwOutOfMemoryError(env, 0);
            return 0;
        }
        rv = (*ckpFunctions->C_SignRecover)(ckSessionHbndle, inBufP, jInLen, outBufP, &ckSignbtureLength);
    }
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        (*env)->SetByteArrbyRegion(env, jOut, jOutOfs, ckSignbtureLength, (jbyte *)outBufP);
    }

    if (inBufP != INBUF) { free(inBufP); }
    if (outBufP != OUTBUF) { free(outBufP); }

    return ckSignbtureLength;
}
#endif

#ifdef P11_ENABLE_C_VERIFYINIT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_VerifyInit
 * Signbture: (JLsun/security/pkcs11/wrbpper/CK_MECHANISM;J)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jobject jMechbnism          CK_MECHANISM_PTR pMechbnism
 * @return  jlong jKeyHbndle            CK_OBJECT_HANDLE hKey
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1VerifyInit
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jobject jMechbnism, jlong jKeyHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_MECHANISM ckMechbnism;
    CK_OBJECT_HANDLE ckKeyHbndle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jMechbnismToCKMechbnism(env, jMechbnism, &ckMechbnism);
    if ((*env)->ExceptionCheck(env)) { return; }

    ckKeyHbndle = jLongToCKULong(jKeyHbndle);

    rv = (*ckpFunctions->C_VerifyInit)(ckSessionHbndle, &ckMechbnism, ckKeyHbndle);

    if(ckMechbnism.pPbrbmeter != NULL_PTR) {
        free(ckMechbnism.pPbrbmeter);
    }

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_VERIFY
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_Verify
 * Signbture: (J[B[B)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG ulDbtbLen
 * @pbrbm   jbyteArrby jSignbture       CK_BYTE_PTR pSignbture
 *                                      CK_ULONG_PTR pulSignbtureLen
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1Verify
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jbyteArrby jDbtb, jbyteArrby jSignbture)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_BYTE_PTR ckpDbtb = NULL_PTR;
    CK_BYTE_PTR ckpSignbture = NULL_PTR;
    CK_ULONG ckDbtbLength;
    CK_ULONG ckSignbtureLength;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jByteArrbyToCKByteArrby(env, jDbtb, &ckpDbtb, &ckDbtbLength);
    if ((*env)->ExceptionCheck(env)) { return; }

    jByteArrbyToCKByteArrby(env, jSignbture, &ckpSignbture, &ckSignbtureLength);
    if ((*env)->ExceptionCheck(env)) {
        free(ckpDbtb);
        return;
    }

    /* verify the signbture */
    rv = (*ckpFunctions->C_Verify)(ckSessionHbndle, ckpDbtb, ckDbtbLength, ckpSignbture, ckSignbtureLength);

    free(ckpDbtb);
    free(ckpSignbture);

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_VERIFYUPDATE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_VerifyUpdbte
 * Signbture: (J[BII)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG ulPbrtLen
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1VerifyUpdbte
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
        rv = (*ckpFunctions->C_VerifyUpdbte)(ckSessionHbndle, (CK_BYTE_PTR)jlong_to_ptr(directIn), jInLen);
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

        rv = (*ckpFunctions->C_VerifyUpdbte)(ckSessionHbndle, bufP, chunkLen);
        if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) {
            if (bufP != BUF) { free(bufP); }
            return;
        }
        jInOfs += chunkLen;
        jInLen -= chunkLen;
    }

    if (bufP != BUF) { free(bufP); }
}
#endif

#ifdef P11_ENABLE_C_VERIFYFINAL
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_VerifyFinbl
 * Signbture: (J[B)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jSignbture       CK_BYTE_PTR pSignbture
 *                                      CK_ULONG ulSignbtureLen
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1VerifyFinbl
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jbyteArrby jSignbture)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_BYTE_PTR ckpSignbture = NULL_PTR;
    CK_ULONG ckSignbtureLength;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jByteArrbyToCKByteArrby(env, jSignbture, &ckpSignbture, &ckSignbtureLength);
    if ((*env)->ExceptionCheck(env)) { return; }

    /* verify the signbture */
    rv = (*ckpFunctions->C_VerifyFinbl)(ckSessionHbndle, ckpSignbture, ckSignbtureLength);

    free(ckpSignbture);

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_VERIFYRECOVERINIT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_VerifyRecoverInit
 * Signbture: (JLsun/security/pkcs11/wrbpper/CK_MECHANISM;J)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jobject jMechbnism          CK_MECHANISM_PTR pMechbnism
 * @return  jlong jKeyHbndle            CK_OBJECT_HANDLE hKey
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1VerifyRecoverInit
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jobject jMechbnism, jlong jKeyHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_MECHANISM ckMechbnism;
    CK_OBJECT_HANDLE ckKeyHbndle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jMechbnismToCKMechbnism(env, jMechbnism, &ckMechbnism);
    if ((*env)->ExceptionCheck(env)) { return; }

    ckKeyHbndle = jLongToCKULong(jKeyHbndle);

    rv = (*ckpFunctions->C_VerifyRecoverInit)(ckSessionHbndle, &ckMechbnism, ckKeyHbndle);

    if (ckMechbnism.pPbrbmeter != NULL_PTR) {
        free(ckMechbnism.pPbrbmeter);
    }

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_VERIFYRECOVER
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_VerifyRecover
 * Signbture: (J[BII[BII)I
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jSignbture       CK_BYTE_PTR pSignbture
 *                                      CK_ULONG ulSignbtureLen
 * @return  jbyteArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG_PTR pulDbtbLen
 */
JNIEXPORT jint JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1VerifyRecover
  (JNIEnv *env, jobject obj, jlong jSessionHbndle, jbyteArrby jIn, jint jInOfs, jint jInLen, jbyteArrby jOut, jint jOutOfs, jint jOutLen)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;
    CK_BYTE INBUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE OUTBUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE_PTR inBufP;
    CK_BYTE_PTR outBufP = OUTBUF;
    CK_ULONG ckDbtbLength = MAX_STACK_BUFFER_LEN;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    if (jInLen <= MAX_STACK_BUFFER_LEN) {
        inBufP = INBUF;
    } else {
        inBufP = (CK_BYTE_PTR) mblloc((size_t)jInLen);
        if (inBufP == NULL) {
            throwOutOfMemoryError(env, 0);
            return 0;
        }
    }

    (*env)->GetByteArrbyRegion(env, jIn, jInOfs, jInLen, (jbyte *)inBufP);
    if ((*env)->ExceptionCheck(env)) {
        if (inBufP != INBUF) { free(inBufP); }
        return 0;
    }

    rv = (*ckpFunctions->C_VerifyRecover)(ckSessionHbndle, inBufP, jInLen, outBufP, &ckDbtbLength);

    /* re-blloc lbrger buffer if it fits into our Jbvb buffer */
    if ((rv == CKR_BUFFER_TOO_SMALL) && (ckDbtbLength <= jIntToCKULong(jOutLen))) {
        outBufP = (CK_BYTE_PTR) mblloc(ckDbtbLength);
        if (outBufP == NULL) {
            if (inBufP != INBUF) { free(inBufP); }
            throwOutOfMemoryError(env, 0);
            return 0;
        }
        rv = (*ckpFunctions->C_VerifyRecover)(ckSessionHbndle, inBufP, jInLen, outBufP, &ckDbtbLength);
    }
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        (*env)->SetByteArrbyRegion(env, jOut, jOutOfs, ckDbtbLength, (jbyte *)outBufP);
    }

    if (inBufP != INBUF) { free(inBufP); }
    if (outBufP != OUTBUF) { free(outBufP); }

    return ckDbtbLength;
}
#endif
