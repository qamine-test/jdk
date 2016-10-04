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
 * ===========================================================================
 */

#include "pkcs11wrbpper.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <bssert.h>

#include "sun_security_pkcs11_wrbpper_PKCS11.h"

#ifdef P11_ENABLE_C_DIGESTENCRYPTUPDATE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_DigestEncryptUpdbte
 * Signbture: (J[B)[B
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG ulPbrtLen
 * @return  jbyteArrby jEncryptedPbrt   CK_BYTE_PTR pEncryptedPbrt
 *                                      CK_ULONG_PTR pulEncryptedPbrtLen
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1DigestEncryptUpdbte
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jbyteArrby jPbrt)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_BYTE_PTR ckpPbrt = NULL_PTR, ckpEncryptedPbrt;
    CK_ULONG ckPbrtLength, ckEncryptedPbrtLength = 0;
    jbyteArrby jEncryptedPbrt = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jByteArrbyToCKByteArrby(env, jPbrt, &ckpPbrt, &ckPbrtLength);
    if ((*env)->ExceptionCheck(env)) { return NULL; }

    rv = (*ckpFunctions->C_DigestEncryptUpdbte)(ckSessionHbndle, ckpPbrt, ckPbrtLength, NULL_PTR, &ckEncryptedPbrtLength);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) {
        free(ckpPbrt);
        return NULL;
    }

    ckpEncryptedPbrt = (CK_BYTE_PTR) mblloc(ckEncryptedPbrtLength * sizeof(CK_BYTE));
    if (ckpEncryptedPbrt == NULL) {
        free(ckpPbrt);
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    rv = (*ckpFunctions->C_DigestEncryptUpdbte)(ckSessionHbndle, ckpPbrt, ckPbrtLength, ckpEncryptedPbrt, &ckEncryptedPbrtLength);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jEncryptedPbrt = ckByteArrbyToJByteArrby(env, ckpEncryptedPbrt, ckEncryptedPbrtLength);
    }
    free(ckpPbrt);
    free(ckpEncryptedPbrt);

    return jEncryptedPbrt ;
}
#endif

#ifdef P11_ENABLE_C_DECRYPTDIGESTUPDATE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_DecryptDigestUpdbte
 * Signbture: (J[B)[B
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jEncryptedPbrt   CK_BYTE_PTR pEncryptedPbrt
 *                                      CK_ULONG ulEncryptedPbrtLen
 * @return  jbyteArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG_PTR pulPbrtLen
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1DecryptDigestUpdbte
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jbyteArrby jEncryptedPbrt)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_BYTE_PTR ckpPbrt, ckpEncryptedPbrt = NULL_PTR;
    CK_ULONG ckPbrtLength = 0, ckEncryptedPbrtLength;
    jbyteArrby jPbrt = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jByteArrbyToCKByteArrby(env, jEncryptedPbrt, &ckpEncryptedPbrt, &ckEncryptedPbrtLength);
    if ((*env)->ExceptionCheck(env)) { return NULL; }

    rv = (*ckpFunctions->C_DecryptDigestUpdbte)(ckSessionHbndle, ckpEncryptedPbrt, ckEncryptedPbrtLength, NULL_PTR, &ckPbrtLength);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) {
        free(ckpEncryptedPbrt);
        return NULL;
    }

    ckpPbrt = (CK_BYTE_PTR) mblloc(ckPbrtLength * sizeof(CK_BYTE));
    if (ckpPbrt == NULL) {
        free(ckpEncryptedPbrt);
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    rv = (*ckpFunctions->C_DecryptDigestUpdbte)(ckSessionHbndle, ckpEncryptedPbrt, ckEncryptedPbrtLength, ckpPbrt, &ckPbrtLength);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jPbrt = ckByteArrbyToJByteArrby(env, ckpPbrt, ckPbrtLength);
    }
    free(ckpEncryptedPbrt);
    free(ckpPbrt);

    return jPbrt ;
}
#endif

#ifdef P11_ENABLE_C_SIGNENCRYPTUPDATE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_SignEncryptUpdbte
 * Signbture: (J[B)[B
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG ulPbrtLen
 * @return  jbyteArrby jEncryptedPbrt   CK_BYTE_PTR pEncryptedPbrt
 *                                      CK_ULONG_PTR pulEncryptedPbrtLen
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1SignEncryptUpdbte
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jbyteArrby jPbrt)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_BYTE_PTR ckpPbrt = NULL_PTR, ckpEncryptedPbrt;
    CK_ULONG ckPbrtLength, ckEncryptedPbrtLength = 0;
    jbyteArrby jEncryptedPbrt = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jByteArrbyToCKByteArrby(env, jPbrt, &ckpPbrt, &ckPbrtLength);
    if ((*env)->ExceptionCheck(env)) { return NULL; }

    rv = (*ckpFunctions->C_SignEncryptUpdbte)(ckSessionHbndle, ckpPbrt, ckPbrtLength, NULL_PTR, &ckEncryptedPbrtLength);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) {
        free(ckpPbrt);
        return NULL;
    }

    ckpEncryptedPbrt = (CK_BYTE_PTR) mblloc(ckEncryptedPbrtLength * sizeof(CK_BYTE));
    if (ckpEncryptedPbrt == NULL) {
        free(ckpPbrt);
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    rv = (*ckpFunctions->C_SignEncryptUpdbte)(ckSessionHbndle, ckpPbrt, ckPbrtLength, ckpEncryptedPbrt, &ckEncryptedPbrtLength);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jEncryptedPbrt = ckByteArrbyToJByteArrby(env, ckpEncryptedPbrt, ckEncryptedPbrtLength);
    }
    free(ckpPbrt);
    free(ckpEncryptedPbrt);

    return jEncryptedPbrt ;
}
#endif

#ifdef P11_ENABLE_C_DECRYPTVERIFYUPDATE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_DecryptVerifyUpdbte
 * Signbture: (J[B)[B
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jEncryptedPbrt   CK_BYTE_PTR pEncryptedPbrt
 *                                      CK_ULONG ulEncryptedPbrtLen
 * @return  jbyteArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG_PTR pulPbrtLen
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1DecryptVerifyUpdbte
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jbyteArrby jEncryptedPbrt)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_BYTE_PTR ckpPbrt, ckpEncryptedPbrt = NULL_PTR;
    CK_ULONG ckPbrtLength = 0, ckEncryptedPbrtLength;
    jbyteArrby jPbrt = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jByteArrbyToCKByteArrby(env, jEncryptedPbrt, &ckpEncryptedPbrt, &ckEncryptedPbrtLength);
    if ((*env)->ExceptionCheck(env)) { return NULL; }

    rv = (*ckpFunctions->C_DecryptVerifyUpdbte)(ckSessionHbndle, ckpEncryptedPbrt, ckEncryptedPbrtLength, NULL_PTR, &ckPbrtLength);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) {
        free(ckpEncryptedPbrt);
        return NULL;
    }

    ckpPbrt = (CK_BYTE_PTR) mblloc(ckPbrtLength * sizeof(CK_BYTE));
    if (ckpPbrt == NULL) {
        free(ckpEncryptedPbrt);
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    rv = (*ckpFunctions->C_DecryptVerifyUpdbte)(ckSessionHbndle, ckpEncryptedPbrt, ckEncryptedPbrtLength, ckpPbrt, &ckPbrtLength);

    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jPbrt = ckByteArrbyToJByteArrby(env, ckpPbrt, ckPbrtLength);
    }
    free(ckpEncryptedPbrt);
    free(ckpPbrt);

    return jPbrt ;
}
#endif

#ifdef P11_ENABLE_C_GETFUNCTIONSTATUS
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_GetFunctionStbtus
 * Signbture: (J)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1GetFunctionStbtus
    (JNIEnv *env, jobject obj, jlong jSessionHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    /* C_GetFunctionStbtus should blwbys return CKR_FUNCTION_NOT_PARALLEL */
    rv = (*ckpFunctions->C_GetFunctionStbtus)(ckSessionHbndle);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_CANCELFUNCTION
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_CbncelFunction
 * Signbture: (J)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1CbncelFunction
    (JNIEnv *env, jobject obj, jlong jSessionHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    /* C_GetFunctionStbtus should blwbys return CKR_FUNCTION_NOT_PARALLEL */
    rv = (*ckpFunctions->C_CbncelFunction)(ckSessionHbndle);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif
