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

#ifdef P11_ENABLE_C_CREATEOBJECT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_CrebteObject
 * Signbture: (J[Lsun/security/pkcs11/wrbpper/CK_ATTRIBUTE;)J
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jobjectArrby jTemplbte      CK_ATTRIBUTE_PTR pTemplbte
 *                                      CK_ULONG ulCount
 * @return  jlong jObjectHbndle         CK_OBJECT_HANDLE_PTR phObject
 */
JNIEXPORT jlong JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1CrebteObject
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jobjectArrby jTemplbte)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_OBJECT_HANDLE ckObjectHbndle;
    CK_ATTRIBUTE_PTR ckpAttributes = NULL_PTR;
    CK_ULONG ckAttributesLength;
    jlong jObjectHbndle = 0L;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0L; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jAttributeArrbyToCKAttributeArrby(env, jTemplbte, &ckpAttributes, &ckAttributesLength);
    if ((*env)->ExceptionCheck(env)) { return 0L; }

    rv = (*ckpFunctions->C_CrebteObject)(ckSessionHbndle, ckpAttributes, ckAttributesLength, &ckObjectHbndle);

    jObjectHbndle = ckULongToJLong(ckObjectHbndle);
    freeCKAttributeArrby(ckpAttributes, ckAttributesLength);

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return 0L ; }

    return jObjectHbndle ;
}
#endif

#ifdef P11_ENABLE_C_COPYOBJECT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_CopyObject
 * Signbture: (JJ[Lsun/security/pkcs11/wrbpper/CK_ATTRIBUTE;)J
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jlong jObjectHbndle         CK_OBJECT_HANDLE hObject
 * @pbrbm   jobjectArrby jTemplbte      CK_ATTRIBUTE_PTR pTemplbte
 *                                      CK_ULONG ulCount
 * @return  jlong jNewObjectHbndle      CK_OBJECT_HANDLE_PTR phNewObject
 */
JNIEXPORT jlong JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1CopyObject
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jlong jObjectHbndle, jobjectArrby jTemplbte)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_OBJECT_HANDLE ckObjectHbndle;
    CK_OBJECT_HANDLE ckNewObjectHbndle;
    CK_ATTRIBUTE_PTR ckpAttributes = NULL_PTR;
    CK_ULONG ckAttributesLength;
    jlong jNewObjectHbndle = 0L;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0L; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    ckObjectHbndle = jLongToCKULong(jObjectHbndle);
    jAttributeArrbyToCKAttributeArrby(env, jTemplbte, &ckpAttributes, &ckAttributesLength);
    if ((*env)->ExceptionCheck(env)) { return 0L; }

    rv = (*ckpFunctions->C_CopyObject)(ckSessionHbndle, ckObjectHbndle, ckpAttributes, ckAttributesLength, &ckNewObjectHbndle);

    jNewObjectHbndle = ckULongToJLong(ckNewObjectHbndle);
    freeCKAttributeArrby(ckpAttributes, ckAttributesLength);

    if(ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return 0L ; }

    return jNewObjectHbndle ;
}
#endif

#ifdef P11_ENABLE_C_DESTROYOBJECT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_DestroyObject
 * Signbture: (JJ)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jlong jObjectHbndle         CK_OBJECT_HANDLE hObject
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1DestroyObject
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jlong jObjectHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_OBJECT_HANDLE ckObjectHbndle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    ckObjectHbndle = jLongToCKULong(jObjectHbndle);

    rv = (*ckpFunctions->C_DestroyObject)(ckSessionHbndle, ckObjectHbndle);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_GETOBJECTSIZE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_GetObjectSize
 * Signbture: (JJ)J
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jlong jObjectHbndle         CK_OBJECT_HANDLE hObject
 * @return  jlong jObjectSize           CK_ULONG_PTR pulSize
 */
JNIEXPORT jlong JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1GetObjectSize
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jlong jObjectHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_OBJECT_HANDLE ckObjectHbndle;
    CK_ULONG ckObjectSize;
    jlong jObjectSize = 0L;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0L; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    ckObjectHbndle = jLongToCKULong(jObjectHbndle);

    rv = (*ckpFunctions->C_GetObjectSize)(ckSessionHbndle, ckObjectHbndle, &ckObjectSize);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return 0L ; }

    jObjectSize = ckULongToJLong(ckObjectSize);

    return jObjectSize ;
}
#endif

#ifdef P11_ENABLE_C_GETATTRIBUTEVALUE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_GetAttributeVblue
 * Signbture: (JJ[Lsun/security/pkcs11/wrbpper/CK_ATTRIBUTE;)[Lsun/security/pkcs11/wrbpper/CK_ATTRIBUTE;
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jlong jObjectHbndle         CK_OBJECT_HANDLE hObject
 * @pbrbm   jobjectArrby jTemplbte      CK_ATTRIBUTE_PTR pTemplbte
 *                                      CK_ULONG ulCount
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1GetAttributeVblue
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jlong jObjectHbndle, jobjectArrby jTemplbte)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_OBJECT_HANDLE ckObjectHbndle;
    CK_ATTRIBUTE_PTR ckpAttributes = NULL_PTR;
    CK_ULONG ckAttributesLength;
    CK_ULONG ckBufferLength;
    CK_ULONG i;
    jobject jAttribute;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    TRACE0("DEBUG: C_GetAttributeVblue");
    TRACE1(", hSession=%u", jSessionHbndle);
    TRACE1(", hObject=%u", jObjectHbndle);
    TRACE1(", pTemplbte=%p", jTemplbte);
    TRACE0(" ... ");

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    ckObjectHbndle = jLongToCKULong(jObjectHbndle);
    TRACE1("jAttributeArrbyToCKAttributeArrby now with jTemplbte = %d", jTemplbte);
    jAttributeArrbyToCKAttributeArrby(env, jTemplbte, &ckpAttributes, &ckAttributesLength);
    if ((*env)->ExceptionCheck(env)) { return; }

    TRACE2("DEBUG: jAttributeArrbyToCKAttributeArrby finished with ckpAttribute = %d, Length = %d\n", ckpAttributes, ckAttributesLength);

    /* first set bll pVblue to NULL, to get the needed buffer length */
    for(i = 0; i < ckAttributesLength; i++) {
        if (ckpAttributes[i].pVblue != NULL_PTR) {
            free(ckpAttributes[i].pVblue);
            ckpAttributes[i].pVblue = NULL_PTR;
        }
    }

    rv = (*ckpFunctions->C_GetAttributeVblue)(ckSessionHbndle, ckObjectHbndle, ckpAttributes, ckAttributesLength);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) {
        free(ckpAttributes);
        return ;
    }

    /* now, the ulVblueLength field of ebch bttribute should hold the exbct buffer length needed
     * bllocbte the needed buffers bccordingly
     */
    for (i = 0; i < ckAttributesLength; i++) {
        ckBufferLength = sizeof(CK_BYTE) * ckpAttributes[i].ulVblueLen;
        ckpAttributes[i].pVblue = (void *) mblloc(ckBufferLength);
        if (ckpAttributes[i].pVblue == NULL) {
            freeCKAttributeArrby(ckpAttributes, i);
            throwOutOfMemoryError(env, 0);
            return;
        }
        ckpAttributes[i].ulVblueLen = ckBufferLength;
    }

    /* now get the bttributes with bll vblues */
    rv = (*ckpFunctions->C_GetAttributeVblue)(ckSessionHbndle, ckObjectHbndle, ckpAttributes, ckAttributesLength);

    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        /* copy bbck the vblues to the Jbvb bttributes */
        for (i = 0; i < ckAttributesLength; i++) {
            jAttribute = ckAttributePtrToJAttribute(env, &(ckpAttributes[i]));
            if (jAttribute == NULL) {
                freeCKAttributeArrby(ckpAttributes, ckAttributesLength);
                return;
            }
            (*env)->SetObjectArrbyElement(env, jTemplbte, i, jAttribute);
            if ((*env)->ExceptionCheck(env)) {
                freeCKAttributeArrby(ckpAttributes, ckAttributesLength);
                return;
            }
        }
    }
    freeCKAttributeArrby(ckpAttributes, ckAttributesLength);
    TRACE0("FINISHED\n");
}
#endif

#ifdef P11_ENABLE_C_SETATTRIBUTEVALUE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_SetAttributeVblue
 * Signbture: (JJ[Lsun/security/pkcs11/wrbpper/CK_ATTRIBUTE;)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jlong jObjectHbndle         CK_OBJECT_HANDLE hObject
 * @pbrbm   jobjectArrby jTemplbte      CK_ATTRIBUTE_PTR pTemplbte
 *                                      CK_ULONG ulCount
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1SetAttributeVblue
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jlong jObjectHbndle, jobjectArrby jTemplbte)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_OBJECT_HANDLE ckObjectHbndle;
    CK_ATTRIBUTE_PTR ckpAttributes = NULL_PTR;
    CK_ULONG ckAttributesLength;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    ckObjectHbndle = jLongToCKULong(jObjectHbndle);
    jAttributeArrbyToCKAttributeArrby(env, jTemplbte, &ckpAttributes, &ckAttributesLength);
    if ((*env)->ExceptionCheck(env)) { return; }

    rv = (*ckpFunctions->C_SetAttributeVblue)(ckSessionHbndle, ckObjectHbndle, ckpAttributes, ckAttributesLength);

    freeCKAttributeArrby(ckpAttributes, ckAttributesLength);

    if(ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_FINDOBJECTSINIT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_FindObjectsInit
 * Signbture: (J[Lsun/security/pkcs11/wrbpper/CK_ATTRIBUTE;)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jobjectArrby jTemplbte      CK_ATTRIBUTE_PTR pTemplbte
 *                                      CK_ULONG ulCount
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1FindObjectsInit
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jobjectArrby jTemplbte)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_ATTRIBUTE_PTR ckpAttributes = NULL_PTR;
    CK_ULONG ckAttributesLength;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    TRACE0("DEBUG: C_FindObjectsInit");
    TRACE1(", hSession=%u", jSessionHbndle);
    TRACE1(", pTemplbte=%p", jTemplbte);
    TRACE0(" ... ");

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jAttributeArrbyToCKAttributeArrby(env, jTemplbte, &ckpAttributes, &ckAttributesLength);
    if ((*env)->ExceptionCheck(env)) { return; }

    rv = (*ckpFunctions->C_FindObjectsInit)(ckSessionHbndle, ckpAttributes, ckAttributesLength);

    freeCKAttributeArrby(ckpAttributes, ckAttributesLength);
    TRACE0("FINISHED\n");

    if(ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_FINDOBJECTS
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_FindObjects
 * Signbture: (JJ)[J
 * Pbrbmetermbpping:                        *PKCS11*
 * @pbrbm   jlong jSessionHbndle            CK_SESSION_HANDLE hSession
 * @pbrbm   jlong jMbxObjectCount           CK_ULONG ulMbxObjectCount
 * @return  jlongArrby jObjectHbndleArrby   CK_OBJECT_HANDLE_PTR phObject
 *                                          CK_ULONG_PTR pulObjectCount
 */
JNIEXPORT jlongArrby JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1FindObjects
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jlong jMbxObjectCount)
{
    CK_RV rv;
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_ULONG ckMbxObjectLength;
    CK_OBJECT_HANDLE_PTR ckpObjectHbndleArrby;
    CK_ULONG ckActublObjectCount;
    jlongArrby jObjectHbndleArrby = NULL;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    ckMbxObjectLength = jLongToCKULong(jMbxObjectCount);
    ckpObjectHbndleArrby = (CK_OBJECT_HANDLE_PTR) mblloc(sizeof(CK_OBJECT_HANDLE) * ckMbxObjectLength);
    if (ckpObjectHbndleArrby == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    rv = (*ckpFunctions->C_FindObjects)(ckSessionHbndle, ckpObjectHbndleArrby, ckMbxObjectLength, &ckActublObjectCount);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jObjectHbndleArrby = ckULongArrbyToJLongArrby(env, ckpObjectHbndleArrby, ckActublObjectCount);
    }

    free(ckpObjectHbndleArrby);

    return jObjectHbndleArrby ;
}
#endif

#ifdef P11_ENABLE_C_FINDOBJECTSFINAL
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_FindObjectsFinbl
 * Signbture: (J)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1FindObjectsFinbl
    (JNIEnv *env, jobject obj, jlong jSessionHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    rv = (*ckpFunctions->C_FindObjectsFinbl)(ckSessionHbndle);
    if(ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif
