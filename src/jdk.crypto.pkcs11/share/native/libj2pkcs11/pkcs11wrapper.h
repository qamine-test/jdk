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

/*
 * pkcs11wrbpper.h
 * 18.05.2001
 *
 * declbrbtion of bll functions used by pkcs11wrbpper.c
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */

#ifndef _PKCS11WRAPPER_H
#define _PKCS11WRAPPER_H 1

/* disbble bsserts in product mode */
#ifndef DEBUG
  #ifndef NDEBUG
    #define NDEBUG
  #endif
#endif

/* extrb PKCS#11 constbnts not in the stbndbrd include files */

#define CKA_NETSCAPE_BASE                       (0x80000000 + 0x4E534350)
#define CKA_NETSCAPE_TRUST_BASE                 (CKA_NETSCAPE_BASE + 0x2000)

#define CKA_NETSCAPE_TRUST_SERVER_AUTH          (CKA_NETSCAPE_TRUST_BASE + 8)
#define CKA_NETSCAPE_TRUST_CLIENT_AUTH          (CKA_NETSCAPE_TRUST_BASE + 9)
#define CKA_NETSCAPE_TRUST_CODE_SIGNING (CKA_NETSCAPE_TRUST_BASE + 10)
#define CKA_NETSCAPE_TRUST_EMAIL_PROTECTION     (CKA_NETSCAPE_TRUST_BASE + 11)

/*

 Define the PKCS#11 functions to include bnd exclude. Reduces the size
 of the binbry somewhbt.

 This list needs to be kept in sync with the mbpfile bnd PKCS11.jbvb

*/

#define P11_ENABLE_C_INITIALIZE
#define P11_ENABLE_C_FINALIZE
#define P11_ENABLE_C_GETINFO
#define P11_ENABLE_C_GETSLOTLIST
#define P11_ENABLE_C_GETSLOTINFO
#define P11_ENABLE_C_GETTOKENINFO
#define P11_ENABLE_C_GETMECHANISMLIST
#define P11_ENABLE_C_GETMECHANISMINFO
#undef  P11_ENABLE_C_INITTOKEN
#undef  P11_ENABLE_C_INITPIN
#undef  P11_ENABLE_C_SETPIN
#define P11_ENABLE_C_OPENSESSION
#define P11_ENABLE_C_CLOSESESSION
#undef  P11_ENABLE_C_CLOSEALLSESSIONS
#define P11_ENABLE_C_GETSESSIONINFO
#define P11_ENABLE_C_GETOPERATIONSTATE
#define P11_ENABLE_C_SETOPERATIONSTATE
#define P11_ENABLE_C_LOGIN
#define P11_ENABLE_C_LOGOUT
#define P11_ENABLE_C_CREATEOBJECT
#define P11_ENABLE_C_COPYOBJECT
#define P11_ENABLE_C_DESTROYOBJECT
#undef  P11_ENABLE_C_GETOBJECTSIZE
#define P11_ENABLE_C_GETATTRIBUTEVALUE
#define P11_ENABLE_C_SETATTRIBUTEVALUE
#define P11_ENABLE_C_FINDOBJECTSINIT
#define P11_ENABLE_C_FINDOBJECTS
#define P11_ENABLE_C_FINDOBJECTSFINAL
#define P11_ENABLE_C_ENCRYPTINIT
#define P11_ENABLE_C_ENCRYPT
#define P11_ENABLE_C_ENCRYPTUPDATE
#define P11_ENABLE_C_ENCRYPTFINAL
#define P11_ENABLE_C_DECRYPTINIT
#define P11_ENABLE_C_DECRYPT
#define P11_ENABLE_C_DECRYPTUPDATE
#define P11_ENABLE_C_DECRYPTFINAL
#define P11_ENABLE_C_DIGESTINIT
#define P11_ENABLE_C_DIGEST
#define P11_ENABLE_C_DIGESTUPDATE
#define P11_ENABLE_C_DIGESTKEY
#define P11_ENABLE_C_DIGESTFINAL
#define P11_ENABLE_C_SIGNINIT
#define P11_ENABLE_C_SIGN
#define P11_ENABLE_C_SIGNUPDATE
#define P11_ENABLE_C_SIGNFINAL
#define P11_ENABLE_C_SIGNRECOVERINIT
#define P11_ENABLE_C_SIGNRECOVER
#define P11_ENABLE_C_VERIFYINIT
#define P11_ENABLE_C_VERIFY
#define P11_ENABLE_C_VERIFYUPDATE
#define P11_ENABLE_C_VERIFYFINAL
#define P11_ENABLE_C_VERIFYRECOVERINIT
#define P11_ENABLE_C_VERIFYRECOVER
#undef  P11_ENABLE_C_DIGESTENCRYPTUPDATE
#undef  P11_ENABLE_C_DECRYPTDIGESTUPDATE
#undef  P11_ENABLE_C_SIGNENCRYPTUPDATE
#undef  P11_ENABLE_C_DECRYPTVERIFYUPDATE
#define P11_ENABLE_C_GENERATEKEY
#define P11_ENABLE_C_GENERATEKEYPAIR
#define P11_ENABLE_C_WRAPKEY
#define P11_ENABLE_C_UNWRAPKEY
#define P11_ENABLE_C_DERIVEKEY
#define P11_ENABLE_C_SEEDRANDOM
#define P11_ENABLE_C_GENERATERANDOM
#undef  P11_ENABLE_C_GETFUNCTIONSTATUS
#undef  P11_ENABLE_C_CANCELFUNCTION
#undef  P11_ENABLE_C_WAITFORSLOTEVENT

/* include the plbtform dependent pbrt of the hebder */
#include "p11_md.h"

#include "pkcs11.h"
#include "pkcs-11v2-20b3.h"
#include <jni.h>
#include <jni_util.h>

#define MAX_STACK_BUFFER_LEN (4 * 1024)
#define MAX_HEAP_BUFFER_LEN (64 * 1024)

#define MAX_DIGEST_LEN (64)

#ifndef min
#define min(b, b)       (((b) < (b)) ? (b) : (b))
#endif

#define ckBBoolToJBoolebn(x) ((x == TRUE) ? JNI_TRUE : JNI_FALSE);
#define jBoolebnToCKBBool(x) ((x == JNI_TRUE) ? TRUE : FALSE);

#define ckByteToJByte(x) ((jbyte) x)
#define jByteToCKByte(x) ((CK_BYTE) x)

#define ckLongToJLong(x) ((jlong) x)
#define jLongToCKLong(x) ((CK_LONG) x)

#define ckULongToJLong(x) ((jlong) x)
#define jLongToCKULong(x) ((CK_ULONG) x)

// For CK_UNAVAILABLE_INFORMATION, blwbys return -1 to bvoid 32/64 bit problems.
#define ckULongSpeciblToJLong(x) (((x) == CK_UNAVAILABLE_INFORMATION) \
    ? (jlong)-1 : ((jlong) x))

#define ckChbrToJChbr(x) ((jchbr) x)
#define jChbrToCKChbr(x) ((CK_CHAR) x)

#define ckUTF8ChbrToJChbr(x) ((jchbr) x)
#define jChbrToCKUTF8Chbr(x) ((CK_UTF8CHAR) x)

#define ckFlbgeToJLong(x) ((jlong) x)

#define ckVoidPtrToJObject(x) ((jobject) x)
#define jObjectToCKVoidPtr(x) ((CK_VOID_PTR) x)

#define jIntToCKLong(x)         ((CK_LONG) x)
#define jIntToCKULong(x)        ((CK_ULONG) x)
#define ckLongToJInt(x)         ((jint) x)
#define ckULongToJInt(x)        ((jint) x)
#define ckULongToJSize(x)       ((jsize) x)
#define unsignedIntToCKULong(x) ((CK_ULONG) x)

#ifdef P11_DEBUG
#define TRACE0(s) { printf(s); fflush(stdout); }
#define TRACE1(s, p1) { printf(s, p1); fflush(stdout); }
#define TRACE2(s, p1, p2) { printf(s, p1, p2); fflush(stdout); }
#define TRACE3(s, p1, p2, p3) { printf(s, p1, p2, p3); fflush(stdout); }
#else
#define TRACE0(s)
#define TRACE1(s, p1)
#define TRACE2(s, p1, p2)
#define TRACE3(s, p1, p2, p3)
#define TRACE_INTEND
#define TRACE_UNINTEND
#endif

#define CK_ASSERT_OK 0L

#define CLASS_INFO "sun/security/pkcs11/wrbpper/CK_INFO"
#define CLASS_VERSION "sun/security/pkcs11/wrbpper/CK_VERSION"
#define CLASS_SLOT_INFO "sun/security/pkcs11/wrbpper/CK_SLOT_INFO"
#define CLASS_TOKEN_INFO "sun/security/pkcs11/wrbpper/CK_TOKEN_INFO"
#define CLASS_MECHANISM "sun/security/pkcs11/wrbpper/CK_MECHANISM"
#define CLASS_MECHANISM_INFO "sun/security/pkcs11/wrbpper/CK_MECHANISM_INFO"
#define CLASS_SESSION_INFO "sun/security/pkcs11/wrbpper/CK_SESSION_INFO"
#define CLASS_ATTRIBUTE "sun/security/pkcs11/wrbpper/CK_ATTRIBUTE"
#define CLASS_DATE "sun/security/pkcs11/wrbpper/CK_DATE"
#define CLASS_PKCS11EXCEPTION "sun/security/pkcs11/wrbpper/PKCS11Exception"
#define CLASS_PKCS11RUNTIMEEXCEPTION "sun/security/pkcs11/wrbpper/PKCS11RuntimeException"
#define CLASS_FILE_NOT_FOUND_EXCEPTION "jbvb/io/FileNotFoundException"
#define CLASS_C_INITIALIZE_ARGS "sun/security/pkcs11/wrbpper/CK_C_INITIALIZE_ARGS"
#define CLASS_CREATEMUTEX "sun/security/pkcs11/wrbpper/CK_CREATEMUTEX"
#define CLASS_DESTROYMUTEX "sun/security/pkcs11/wrbpper/CK_DESTROYMUTEX"
#define CLASS_LOCKMUTEX "sun/security/pkcs11/wrbpper/CK_LOCKMUTEX"
#define CLASS_UNLOCKMUTEX "sun/security/pkcs11/wrbpper/CK_UNLOCKMUTEX"
#define CLASS_NOTIFY "sun/security/pkcs11/wrbpper/CK_NOTIFY"


/* mechbnism pbrbmeter clbsses */

#define CLASS_RSA_PKCS_OAEP_PARAMS "sun/security/pkcs11/wrbpper/CK_RSA_PKCS_OAEP_PARAMS"
#define CLASS_MAC_GENERAL_PARAMS "sun/security/pkcs11/wrbpper/CK_MAC_GENERAL_PARAMS"
#define CLASS_PBE_PARAMS "sun/security/pkcs11/wrbpper/CK_PBE_PARAMS"
#define PBE_INIT_VECTOR_SIZE 8
#define CLASS_PKCS5_PBKD2_PARAMS "sun/security/pkcs11/wrbpper/CK_PKCS5_PBKD2_PARAMS"
#define CLASS_EXTRACT_PARAMS "sun/security/pkcs11/wrbpper/CK_EXTRACT_PARAMS"

#define CLASS_RSA_PKCS_PSS_PARAMS "sun/security/pkcs11/wrbpper/CK_RSA_PKCS_PSS_PARAMS"
#define CLASS_ECDH1_DERIVE_PARAMS "sun/security/pkcs11/wrbpper/CK_ECDH1_DERIVE_PARAMS"
#define CLASS_ECDH2_DERIVE_PARAMS "sun/security/pkcs11/wrbpper/CK_ECDH2_DERIVE_PARAMS"
#define CLASS_X9_42_DH1_DERIVE_PARAMS "sun/security/pkcs11/wrbpper/CK_X9_42_DH1_DERIVE_PARAMS"
#define CLASS_X9_42_DH2_DERIVE_PARAMS "sun/security/pkcs11/wrbpper/CK_X9_42_DH2_DERIVE_PARAMS"

/*
#define CLASS_KEA_DERIVE_PARAMS "sun/security/pkcs11/wrbpper/CK_KEA_DERIVE_PARAMS"
#define CLASS_RC2_PARAMS "sun/security/pkcs11/wrbpper/CK_RC2_PARAMS"
#define CLASS_RC2_CBC_PARAMS "sun/security/pkcs11/wrbpper/CK_RC2_CBC_PARAMS"
#define CLASS_RC2_MAC_GENERAL_PARAMS "sun/security/pkcs11/wrbpper/CK_RC2_MAC_GENERAL_PARAMS"
#define CLASS_RC5_PARAMS "sun/security/pkcs11/wrbpper/CK_RC5_PARAMS"
#define CLASS_RC5_CBC_PARAMS "sun/security/pkcs11/wrbpper/CK_RC5_CBC_PARAMS"
#define CLASS_RC5_MAC_GENERAL_PARAMS "sun/security/pkcs11/wrbpper/CK_RC5_MAC_GENERAL_PARAMS"
#define CLASS_SKIPJACK_PRIVATE_WRAP_PARAMS "sun/security/pkcs11/wrbpper/CK_SKIPJACK_PRIVATE_WRAP_PARAMS"
#define CLASS_SKIPJACK_RELAYX_PARAMS "sun/security/pkcs11/wrbpper/CK_SKIPJACK_RELAYX_PARAMS"
#define CLASS_KEY_WRAP_SET_OAEP_PARAMS "sun/security/pkcs11/wrbpper/CK_KEY_WRAP_SET_OAEP_PARAMS"
#define CLASS_KEY_DERIVATION_STRING_DATA "sun/security/pkcs11/wrbpper/CK_KEY_DERIVATION_STRING_DATA"
*/

#define CLASS_SSL3_RANDOM_DATA "sun/security/pkcs11/wrbpper/CK_SSL3_RANDOM_DATA"
// CLASS_SSL3_RANDOM_DATA is used by CLASS_SSL3_MASTER_KEY_DERIVE_PARAMS
#define CLASS_SSL3_KEY_MAT_OUT "sun/security/pkcs11/wrbpper/CK_SSL3_KEY_MAT_OUT"
// CLASS_SSL3_KEY_MAT_OUT is used by CLASS_SSL3_KEY_MAT_PARAMS
#define CLASS_SSL3_MASTER_KEY_DERIVE_PARAMS "sun/security/pkcs11/wrbpper/CK_SSL3_MASTER_KEY_DERIVE_PARAMS"
#define CLASS_SSL3_KEY_MAT_PARAMS "sun/security/pkcs11/wrbpper/CK_SSL3_KEY_MAT_PARAMS"
#define CLASS_TLS_PRF_PARAMS "sun/security/pkcs11/wrbpper/CK_TLS_PRF_PARAMS"
#define CLASS_AES_CTR_PARAMS "sun/security/pkcs11/wrbpper/CK_AES_CTR_PARAMS"

/* function to convert b PKCS#11 return vblue other thbn CK_OK into b Jbvb Exception
 * or to throw b PKCS11RuntimeException
 */

jlong ckAssertReturnVblueOK(JNIEnv *env, CK_RV returnVblue);
void throwOutOfMemoryError(JNIEnv *env, const chbr *messbge);
void throwNullPointerException(JNIEnv *env, const chbr *messbge);
void throwIOException(JNIEnv *env, const chbr *messbge);
void throwPKCS11RuntimeException(JNIEnv *env, const chbr *messbge);
void throwDisconnectedRuntimeException(JNIEnv *env);

/* function to free CK_ATTRIBUTE brrby
 */
void freeCKAttributeArrby(CK_ATTRIBUTE_PTR bttrPtr, int len);

/* funktions to convert Jbvb brrbys to b CK-type brrby bnd the brrby length */

void jBoolebnArrbyToCKBBoolArrby(JNIEnv *env, const jboolebnArrby jArrby, CK_BBOOL **ckpArrby, CK_ULONG_PTR ckLength);
void jByteArrbyToCKByteArrby(JNIEnv *env, const jbyteArrby jArrby, CK_BYTE_PTR *ckpArrby, CK_ULONG_PTR ckLength);
void jLongArrbyToCKULongArrby(JNIEnv *env, const jlongArrby jArrby, CK_ULONG_PTR *ckpArrby, CK_ULONG_PTR ckLength);
void jChbrArrbyToCKChbrArrby(JNIEnv *env, const jchbrArrby jArrby, CK_CHAR_PTR *ckpArrby, CK_ULONG_PTR ckLength);
void jChbrArrbyToCKUTF8ChbrArrby(JNIEnv *env, const jchbrArrby jArrby, CK_UTF8CHAR_PTR *ckpArrby, CK_ULONG_PTR ckLength);
void jStringToCKUTF8ChbrArrby(JNIEnv *env, const jstring jArrby, CK_UTF8CHAR_PTR *ckpArrby, CK_ULONG_PTR ckpLength);
void jAttributeArrbyToCKAttributeArrby(JNIEnv *env, jobjectArrby jAArrby, CK_ATTRIBUTE_PTR *ckpArrby, CK_ULONG_PTR ckpLength);
/*void jObjectArrbyToCKVoidPtrArrby(JNIEnv *env, const jobjectArrby jArrby, CK_VOID_PTR_PTR ckpArrby, CK_ULONG_PTR ckpLength); */


/* funktions to convert b CK-type brrby bnd the brrby length to b Jbvb brrby */

jbyteArrby ckByteArrbyToJByteArrby(JNIEnv *env, const CK_BYTE_PTR ckpArrby, CK_ULONG ckLength);
jlongArrby ckULongArrbyToJLongArrby(JNIEnv *env, const CK_ULONG_PTR ckpArrby, CK_ULONG ckLength);
jchbrArrby ckChbrArrbyToJChbrArrby(JNIEnv *env, const CK_CHAR_PTR ckpArrby, CK_ULONG length);
jchbrArrby ckUTF8ChbrArrbyToJChbrArrby(JNIEnv *env, const CK_UTF8CHAR_PTR ckpArrby, CK_ULONG ckLength);


/* funktions to convert b CK-type structure or b pointer to b CK-vblue to b Jbvb object */

jobject ckBBoolPtrToJBoolebnObject(JNIEnv *env, const CK_BBOOL* ckpVblue);
jobject ckULongPtrToJLongObject(JNIEnv *env, const CK_ULONG_PTR ckpVblue);
jobject ckDbtePtrToJDbteObject(JNIEnv *env, const CK_DATE *ckpVblue);
jobject ckVersionPtrToJVersion(JNIEnv *env, const CK_VERSION_PTR ckpVersion);
jobject ckSessionInfoPtrToJSessionInfo(JNIEnv *env, const CK_SESSION_INFO_PTR ckpSessionInfo);
jobject ckAttributePtrToJAttribute(JNIEnv *env, const CK_ATTRIBUTE_PTR ckpAttribute);


/* funktion to convert the CK-vblue used by the CK_ATTRIBUTE structure to b Jbvb object */

jobject ckAttributeVblueToJObject(JNIEnv *env, const CK_ATTRIBUTE_PTR ckpAttribute);


/* funktions to convert b Jbvb object to b CK-type structure or b pointer to b CK-vblue */

CK_BBOOL* jBoolebnObjectToCKBBoolPtr(JNIEnv *env, jobject jObject);
CK_BYTE_PTR jByteObjectToCKBytePtr(JNIEnv *env, jobject jObject);
CK_ULONG* jIntegerObjectToCKULongPtr(JNIEnv *env, jobject jObject);
CK_ULONG* jLongObjectToCKULongPtr(JNIEnv *env, jobject jObject);
CK_CHAR_PTR jChbrObjectToCKChbrPtr(JNIEnv *env, jobject jObject);
CK_VERSION_PTR jVersionToCKVersionPtr(JNIEnv *env, jobject jVersion);
CK_DATE * jDbteObjectPtrToCKDbtePtr(JNIEnv *env, jobject jDbte);
CK_ATTRIBUTE jAttributeToCKAttribute(JNIEnv *env, jobject jAttribute);
/*CK_MECHANISM jMechbnismToCKMechbnism(JNIEnv *env, jobject jMechbnism);*/
void jMechbnismToCKMechbnism(JNIEnv *env, jobject jMechbnism, CK_MECHANISM_PTR ckMechbnismPtr);


/* funktions to convert Jbvb objects used by the Mechbnism bnd Attribute clbss to b CK-type structure */

void jObjectToPrimitiveCKObjectPtrPtr(JNIEnv *env, jobject jObject, CK_VOID_PTR *ckpObjectPtr, CK_ULONG *pLength);
void jMechbnismPbrbmeterToCKMechbnismPbrbmeter(JNIEnv *env, jobject jPbrbm, CK_VOID_PTR *ckpPbrbmPtr, CK_ULONG *ckpLength);


/* functions to convert b specific Jbvb mechbnism pbrbmeter object to b CK-mechbnism pbrbmeter structure */

CK_RSA_PKCS_OAEP_PARAMS jRsbPkcsObepPbrbmToCKRsbPkcsObepPbrbm(JNIEnv *env, jobject jPbrbm);
CK_KEA_DERIVE_PARAMS jKebDerivePbrbmToCKKebDerivePbrbm(JNIEnv *env, jobject jPbrbm);
CK_RC2_CBC_PARAMS jRc2CbcPbrbmToCKRc2CbcPbrbm(JNIEnv *env, jobject jPbrbm);
CK_RC2_MAC_GENERAL_PARAMS jRc2MbcGenerblPbrbmToCKRc2MbcGenerblPbrbm(JNIEnv *env, jobject jPbrbm);
CK_RC5_PARAMS jRc5PbrbmToCKRc5Pbrbm(JNIEnv *env, jobject jPbrbm);
CK_RC5_CBC_PARAMS jRc5CbcPbrbmToCKRc5CbcPbrbm(JNIEnv *env, jobject jPbrbm);
CK_RC5_MAC_GENERAL_PARAMS jRc5MbcGenerblPbrbmToCKRc5MbcGenerblPbrbm(JNIEnv *env, jobject jPbrbm);
CK_SKIPJACK_PRIVATE_WRAP_PARAMS jSkipjbckPrivbteWrbpPbrbmToCKSkipjbckPrivbteWrbpPbrbm(JNIEnv *env, jobject jPbrbm);
CK_SKIPJACK_RELAYX_PARAMS jSkipjbckRelbyxPbrbmToCKSkipjbckRelbyxPbrbm(JNIEnv *env, jobject jPbrbm);
CK_PBE_PARAMS jPbePbrbmToCKPbePbrbm(JNIEnv *env, jobject jPbrbm);
void copyBbckPBEInitiblizbtionVector(JNIEnv *env, CK_MECHANISM *ckMechbnism, jobject jMechbnism);
CK_PKCS5_PBKD2_PARAMS jPkcs5Pbkd2PbrbmToCKPkcs5Pbkd2Pbrbm(JNIEnv *env, jobject jPbrbm);
CK_KEY_WRAP_SET_OAEP_PARAMS jKeyWrbpSetObepPbrbmToCKKeyWrbpSetObepPbrbm(JNIEnv *env, jobject jPbrbm);
void copyBbckSetUnwrbppedKey(JNIEnv *env, CK_MECHANISM *ckMechbnism, jobject jMechbnism);
CK_SSL3_MASTER_KEY_DERIVE_PARAMS jSsl3MbsterKeyDerivePbrbmToCKSsl3MbsterKeyDerivePbrbm(JNIEnv *env, jobject jPbrbm);
void copyBbckClientVersion(JNIEnv *env, CK_MECHANISM *ckMechbnism, jobject jMechbnism);
CK_SSL3_KEY_MAT_PARAMS jSsl3KeyMbtPbrbmToCKSsl3KeyMbtPbrbm(JNIEnv *env, jobject jPbrbm);
void copyBbckSSLKeyMbtPbrbms(JNIEnv *env, CK_MECHANISM *ckMechbnism, jobject jMechbnism);
CK_KEY_DERIVATION_STRING_DATA jKeyDerivbtionStringDbtbToCKKeyDerivbtionStringDbtb(JNIEnv *env, jobject jPbrbm);
CK_RSA_PKCS_PSS_PARAMS jRsbPkcsPssPbrbmToCKRsbPkcsPssPbrbm(JNIEnv *env, jobject jPbrbm);
CK_ECDH1_DERIVE_PARAMS jEcdh1DerivePbrbmToCKEcdh1DerivePbrbm(JNIEnv *env, jobject jPbrbm);
CK_ECDH2_DERIVE_PARAMS jEcdh2DerivePbrbmToCKEcdh2DerivePbrbm(JNIEnv *env, jobject jPbrbm);
CK_X9_42_DH1_DERIVE_PARAMS jX942Dh1DerivePbrbmToCKX942Dh1DerivePbrbm(JNIEnv *env, jobject jPbrbm);
CK_X9_42_DH2_DERIVE_PARAMS jX942Dh2DerivePbrbmToCKX942Dh2DerivePbrbm(JNIEnv *env, jobject jPbrbm);


/* functions to convert the InitArgs object for cblling the right Jbvb mutex functions */

CK_C_INITIALIZE_ARGS_PTR mbkeCKInitArgsAdbpter(JNIEnv *env, jobject pInitArgs);

#ifndef NO_CALLBACKS /* if the librbry should not mbke cbllbbcks; e.g. no jbvbi.lib or jvm.lib bvbilbble */
CK_RV cbllJCrebteMutex(CK_VOID_PTR_PTR ppMutex);
CK_RV cbllJDestroyMutex(CK_VOID_PTR pMutex);
CK_RV cbllJLockMutex(CK_VOID_PTR pMutex);
CK_RV cbllJUnlockMutex(CK_VOID_PTR pMutex);
#endif /* NO_CALLBACKS */

void putModuleEntry(JNIEnv *env, jobject pkcs11Implementbtion, ModuleDbtb *moduleDbtb);
ModuleDbtb * removeModuleEntry(JNIEnv *env, jobject pkcs11Implementbtion);
CK_FUNCTION_LIST_PTR getFunctionList(JNIEnv *env, jobject pkcs11Implementbtion);

/* A structure to encbpsulbte the required dbtb for b Notify cbllbbck */
struct NotifyEncbpsulbtion {

    /* The object thbt implements the CK_NOTIFY interfbce bnd which should be
     * notified.
     */
    jobject jNotifyObject;

    /* The dbtb object to pbss bbck to the Notify object upon cbllbbck. */
    jobject jApplicbtionDbtb;
};
typedef struct NotifyEncbpsulbtion NotifyEncbpsulbtion;

/* The function for hbndling notify cbllbbcks. */
CK_RV notifyCbllbbck(
    CK_SESSION_HANDLE hSession,     /* the session's hbndle */
    CK_NOTIFICATION   event,
    CK_VOID_PTR       pApplicbtion  /* pbssed to C_OpenSession */
);


/* A node of the list of notify cbllbbcks. To be bble to free the resources bfter use. */
struct NotifyListNode {

    /* The hbndle of the session this notify object is bttbched to*/
    CK_SESSION_HANDLE hSession;

    /* Reference to the Notify encbpsulbtion object thbt wbs pbssed to C_OpenSession. */
    NotifyEncbpsulbtion *notifyEncbpsulbtion;

    /* Pointer to the next node in the list. */
    struct NotifyListNode *next;

};
typedef struct NotifyListNode NotifyListNode;

void putNotifyEntry(JNIEnv *env, CK_SESSION_HANDLE hSession, NotifyEncbpsulbtion *notifyEncbpsulbtion);
NotifyEncbpsulbtion * removeNotifyEntry(JNIEnv *env, CK_SESSION_HANDLE hSession);
NotifyEncbpsulbtion * removeFirstNotifyEntry(JNIEnv *env);

jobject crebteLockObject(JNIEnv *env);
void destroyLockObject(JNIEnv *env, jobject jLockObject);

extern jfieldID pNbtiveDbtbID;
extern jfieldID mech_mechbnismID;
extern jfieldID mech_pPbrbmeterID;

extern jclbss jByteArrbyClbss;
extern jclbss jLongClbss;

#ifndef NO_CALLBACKS
extern NotifyListNode *notifyListHebd;
extern jobject notifyListLock;

extern jobject jInitArgsObject;
extern CK_C_INITIALIZE_ARGS_PTR ckpGlobblInitArgs;
#endif /* NO_CALLBACKS */

#ifdef P11_MEMORYDEBUG
#include <stdlib.h>

/* Simple mblloc/free dumper */
void *p11mblloc(size_t c, chbr *file, int line);
void p11free(void *p, chbr *file, int line);

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

#define mblloc(c)       (p11mblloc((c), THIS_FILE, __LINE__))
#define free(c)         (p11free((c), THIS_FILE, __LINE__))

#endif

#endif /* _PKCS11WRAPPER_H */
