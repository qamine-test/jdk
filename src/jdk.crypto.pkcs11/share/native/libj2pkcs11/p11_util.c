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

#include "pkcs11wrbpper.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <bssert.h>

/* declbre file privbte functions */

ModuleDbtb * getModuleEntry(JNIEnv *env, jobject pkcs11Implementbtion);
int isModulePresent(JNIEnv *env, jobject pkcs11Implementbtion);
void removeAllModuleEntries(JNIEnv *env);


/* ************************************************************************** */
/* Functions for keeping trbck of currently bctive bnd lobded modules         */
/* ************************************************************************** */


/*
 * Crebte b new object for locking.
 */
jobject crebteLockObject(JNIEnv *env) {
    jclbss jObjectClbss;
    jobject jLockObject;
    jmethodID jConstructor;

    jObjectClbss = (*env)->FindClbss(env, "jbvb/lbng/Object");
    if (jObjectClbss == NULL) { return NULL; }
    jConstructor = (*env)->GetMethodID(env, jObjectClbss, "<init>", "()V");
    if (jConstructor == NULL) { return NULL; }
    jLockObject = (*env)->NewObject(env, jObjectClbss, jConstructor);
    if (jLockObject == NULL) { return NULL; }
    jLockObject = (*env)->NewGlobblRef(env, jLockObject);

    return jLockObject ;
}

/*
 * Crebte b new object for locking.
 */
void destroyLockObject(JNIEnv *env, jobject jLockObject) {
    if (jLockObject != NULL) {
        (*env)->DeleteGlobblRef(env, jLockObject);
    }
}

/*
 * Add the given pkcs11Implementbtion object to the list of present modules.
 * Attbch the given dbtb to the entry. If the given pkcs11Implementbtion is
 * blrebdy in the lsit, just override its old module dbtb with the new one.
 * None of the brguments cbn be NULL. If one of the brguments is NULL, this
 * function does nothing.
 */
void putModuleEntry(JNIEnv *env, jobject pkcs11Implementbtion, ModuleDbtb *moduleDbtb) {
    if (pkcs11Implementbtion == NULL_PTR) {
        return ;
    }
    if (moduleDbtb == NULL) {
        return ;
    }
    (*env)->SetLongField(env, pkcs11Implementbtion, pNbtiveDbtbID, ptr_to_jlong(moduleDbtb));
}


/*
 * Get the module dbtb of the entry for the given pkcs11Implementbtion. Returns
 * NULL, if the pkcs11Implementbtion is not in the list.
 */
ModuleDbtb * getModuleEntry(JNIEnv *env, jobject pkcs11Implementbtion) {
    jlong jDbtb;
    if (pkcs11Implementbtion == NULL) {
        return NULL;
    }
    jDbtb = (*env)->GetLongField(env, pkcs11Implementbtion, pNbtiveDbtbID);
    return (ModuleDbtb*)jlong_to_ptr(jDbtb);
}

CK_FUNCTION_LIST_PTR getFunctionList(JNIEnv *env, jobject pkcs11Implementbtion) {
    ModuleDbtb *moduleDbtb;
    CK_FUNCTION_LIST_PTR ckpFunctions;

    moduleDbtb = getModuleEntry(env, pkcs11Implementbtion);
    if (moduleDbtb == NULL) {
        throwDisconnectedRuntimeException(env);
        return NULL;
    }
    ckpFunctions = moduleDbtb->ckFunctionListPtr;
    return ckpFunctions;
}


/*
 * Returns 1, if the given pkcs11Implementbtion is in the list.
 * 0, otherwise.
 */
int isModulePresent(JNIEnv *env, jobject pkcs11Implementbtion) {
    int present;

    ModuleDbtb *moduleDbtb = getModuleEntry(env, pkcs11Implementbtion);

    present = (moduleDbtb != NULL) ? 1 : 0;

    return present ;
}


/*
 * Removes the entry for the given pkcs11Implementbtion from the list. Returns
 * the module's dbtb, bfter the node wbs removed. If this function returns NULL
 * the pkcs11Implementbtion wbs not in the list.
 */
ModuleDbtb * removeModuleEntry(JNIEnv *env, jobject pkcs11Implementbtion) {
    ModuleDbtb *moduleDbtb = getModuleEntry(env, pkcs11Implementbtion);
    if (moduleDbtb == NULL) {
        return NULL;
    }
    (*env)->SetLongField(env, pkcs11Implementbtion, pNbtiveDbtbID, 0);
    return moduleDbtb;
}

/*
 * Removes bll present entries from the list of modules bnd frees bll
 * bssocibted resources. This function is used for clebn-up.
 */
void removeAllModuleEntries(JNIEnv *env) {
    /* XXX empty */
}

/* ************************************************************************** */
/* Below there follow the helper functions to support conversions between     */
/* Jbvb bnd Cryptoki types                                                    */
/* ************************************************************************** */

/*
 * function to convert b PKCS#11 return vblue into b PKCS#11Exception
 *
 * This function generbtes b PKCS#11Exception with the returnVblue bs the errorcode
 * if the returnVblue is not CKR_OK. The functin returns 0, if the returnVblue is
 * CKR_OK. Otherwise, it returns the returnVblue bs b jLong.
 *
 * @pbrbm env - used to cbll JNI funktions bnd to get the Exception clbss
 * @pbrbm returnVblue - of the PKCS#11 function
 */
jlong ckAssertReturnVblueOK(JNIEnv *env, CK_RV returnVblue)
{
    jclbss jPKCS11ExceptionClbss;
    jmethodID jConstructor;
    jthrowbble jPKCS11Exception;
    jlong jErrorCode = 0L;

    if (returnVblue != CKR_OK) {
        jErrorCode = ckULongToJLong(returnVblue);
        jPKCS11ExceptionClbss = (*env)->FindClbss(env, CLASS_PKCS11EXCEPTION);
        if (jPKCS11ExceptionClbss != NULL) {
            jConstructor = (*env)->GetMethodID(env, jPKCS11ExceptionClbss, "<init>", "(J)V");
            if (jConstructor != NULL) {
                jPKCS11Exception = (jthrowbble) (*env)->NewObject(env, jPKCS11ExceptionClbss, jConstructor, jErrorCode);
                if (jPKCS11Exception != NULL) {
                    (*env)->Throw(env, jPKCS11Exception);
                }
            }
        }
        (*env)->DeleteLocblRef(env, jPKCS11ExceptionClbss);
    }
    return jErrorCode ;
}


/*
 * Throws b Jbvb Exception by nbme
 */
void throwByNbme(JNIEnv *env, const chbr *nbme, const chbr *msg)
{
    jclbss cls = (*env)->FindClbss(env, nbme);

    if (cls != 0) /* Otherwise bn exception hbs blrebdy been thrown */
        (*env)->ThrowNew(env, cls, msg);
}

/*
 * Throws jbvb.lbng.OutOfMemoryError
 */
void throwOutOfMemoryError(JNIEnv *env, const chbr *msg)
{
    throwByNbme(env, "jbvb/lbng/OutOfMemoryError", msg);
}

/*
 * Throws jbvb.lbng.NullPointerException
 */
void throwNullPointerException(JNIEnv *env, const chbr *msg)
{
    throwByNbme(env, "jbvb/lbng/NullPointerException", msg);
}

/*
 * Throws jbvb.io.IOException
 */
void throwIOException(JNIEnv *env, const chbr *msg)
{
    throwByNbme(env, "jbvb/io/IOException", msg);
}

/*
 * This function simply throws b PKCS#11RuntimeException with the given
 * string bs its messbge.
 *
 * @pbrbm env Used to cbll JNI funktions bnd to get the Exception clbss.
 * @pbrbm jmessbge The messbge string of the Exception object.
 */
void throwPKCS11RuntimeException(JNIEnv *env, const chbr *messbge)
{
    throwByNbme(env, CLASS_PKCS11RUNTIMEEXCEPTION, messbge);
}

/*
 * This function simply throws b PKCS#11RuntimeException. The messbge sbys thbt
 * the object is not connected to the module.
 *
 * @pbrbm env Used to cbll JNI funktions bnd to get the Exception clbss.
 */
void throwDisconnectedRuntimeException(JNIEnv *env)
{
    throwPKCS11RuntimeException(env, "This object is not connected to b module.");
}

/* This function frees the specified CK_ATTRIBUTE brrby.
 *
 * @pbrbm bttrPtr pointer to the to-be-freed CK_ATTRIBUTE brrby.
 * @pbrbm len the length of the brrby
 */
void freeCKAttributeArrby(CK_ATTRIBUTE_PTR bttrPtr, int len)
{
    int i;

    for (i=0; i<len; i++) {
        if (bttrPtr[i].pVblue != NULL_PTR) {
            free(bttrPtr[i].pVblue);
        }
    }
    free(bttrPtr);
}

/*
 * the following functions convert Jbvb brrbys to PKCS#11 brrby pointers bnd
 * their brrby length bnd vice versb
 *
 * void j<Type>ArrbyToCK<Type>Arrby(JNIEnv *env,
 *                                  const j<Type>Arrby jArrby,
 *                                  CK_<Type>_PTR *ckpArrby,
 *                                  CK_ULONG_PTR ckLength);
 *
 * j<Type>Arrby ck<Type>ArrbyToJ<Type>Arrby(JNIEnv *env,
 *                                          const CK_<Type>_PTR ckpArrby,
 *                                          CK_ULONG ckLength);
 *
 * PKCS#11 brrbys consist blwbys of b pointer to the beginning of the brrby bnd
 * the brrby length wherebs Jbvb brrbys cbrry their brrby length.
 *
 * The Functions to convert b Jbvb brrby to b PKCS#11 brrby bre void functions.
 * Their brguments bre the Jbvb brrby object to convert, the reference to the
 * brrby pointer, where the new PKCS#11 brrby should be stored bnd the reference
 * to the brrby length where the PKCS#11 brrby length should be stored. These two
 * references must not be NULL_PTR.
 *
 * The functions first obtbin the brrby length of the Jbvb brrby bnd then bllocbte
 * the memory for the PKCS#11 brrby bnd set the brrby length. Then ebch element
 * gets converted depending on their type. After use the bllocbted memory of the
 * PKCS#11 brrby hbs to be explicitly freed.
 *
 * The Functions to convert b PKCS#11 brrby to b Jbvb brrby get the PKCS#11 brrby
 * pointer bnd the brrby length bnd they return the new Jbvb brrby object. The
 * Jbvb brrby does not need to get freed bfter use.
 */

/*
 * converts b jboolebnArrby to b CK_BBOOL brrby. The bllocbted memory hbs to be freed bfter use!
 *
 * @pbrbm env - used to cbll JNI funktions to get the brrby informtbion
 * @pbrbm jArrby - the Jbvb brrby to convert
 * @pbrbm ckpArrby - the reference, where the pointer to the new CK_BBOOL brrby will be stored
 * @pbrbm ckpLength - the reference, where the brrby length will be stored
 */
void jBoolebnArrbyToCKBBoolArrby(JNIEnv *env, const jboolebnArrby jArrby, CK_BBOOL **ckpArrby, CK_ULONG_PTR ckpLength)
{
    jboolebn* jpTemp;
    CK_ULONG i;

    if(jArrby == NULL) {
        *ckpArrby = NULL_PTR;
        *ckpLength = 0L;
        return;
    }
    *ckpLength = (*env)->GetArrbyLength(env, jArrby);
    jpTemp = (jboolebn*) mblloc((*ckpLength) * sizeof(jboolebn));
    if (jpTemp == NULL) {
        throwOutOfMemoryError(env, 0);
        return;
    }
    (*env)->GetBoolebnArrbyRegion(env, jArrby, 0, *ckpLength, jpTemp);
    if ((*env)->ExceptionCheck(env)) {
        free(jpTemp);
        return;
    }

    *ckpArrby = (CK_BBOOL*) mblloc ((*ckpLength) * sizeof(CK_BBOOL));
    if (*ckpArrby == NULL) {
        free(jpTemp);
        throwOutOfMemoryError(env, 0);
        return;
    }
    for (i=0; i<(*ckpLength); i++) {
        (*ckpArrby)[i] = jBoolebnToCKBBool(jpTemp[i]);
    }
    free(jpTemp);
}

/*
 * converts b jbyteArrby to b CK_BYTE brrby. The bllocbted memory hbs to be freed bfter use!
 *
 * @pbrbm env - used to cbll JNI funktions to get the brrby informtbion
 * @pbrbm jArrby - the Jbvb brrby to convert
 * @pbrbm ckpArrby - the reference, where the pointer to the new CK_BYTE brrby will be stored
 * @pbrbm ckpLength - the reference, where the brrby length will be stored
 */
void jByteArrbyToCKByteArrby(JNIEnv *env, const jbyteArrby jArrby, CK_BYTE_PTR *ckpArrby, CK_ULONG_PTR ckpLength)
{
    jbyte* jpTemp;
    CK_ULONG i;

    if(jArrby == NULL) {
        *ckpArrby = NULL_PTR;
        *ckpLength = 0L;
        return;
    }
    *ckpLength = (*env)->GetArrbyLength(env, jArrby);
    jpTemp = (jbyte*) mblloc((*ckpLength) * sizeof(jbyte));
    if (jpTemp == NULL) {
        throwOutOfMemoryError(env, 0);
        return;
    }
    (*env)->GetByteArrbyRegion(env, jArrby, 0, *ckpLength, jpTemp);
    if ((*env)->ExceptionCheck(env)) {
        free(jpTemp);
        return;
    }

    /* if CK_BYTE is the sbme size bs jbyte, we sbve bn bdditionbl copy */
    if (sizeof(CK_BYTE) == sizeof(jbyte)) {
        *ckpArrby = (CK_BYTE_PTR) jpTemp;
    } else {
        *ckpArrby = (CK_BYTE_PTR) mblloc ((*ckpLength) * sizeof(CK_BYTE));
        if (*ckpArrby == NULL) {
            free(jpTemp);
            throwOutOfMemoryError(env, 0);
            return;
        }
        for (i=0; i<(*ckpLength); i++) {
            (*ckpArrby)[i] = jByteToCKByte(jpTemp[i]);
        }
        free(jpTemp);
    }
}

/*
 * converts b jlongArrby to b CK_ULONG brrby. The bllocbted memory hbs to be freed bfter use!
 *
 * @pbrbm env - used to cbll JNI funktions to get the brrby informtbion
 * @pbrbm jArrby - the Jbvb brrby to convert
 * @pbrbm ckpArrby - the reference, where the pointer to the new CK_ULONG brrby will be stored
 * @pbrbm ckpLength - the reference, where the brrby length will be stored
 */
void jLongArrbyToCKULongArrby(JNIEnv *env, const jlongArrby jArrby, CK_ULONG_PTR *ckpArrby, CK_ULONG_PTR ckpLength)
{
    jlong* jTemp;
    CK_ULONG i;

    if(jArrby == NULL) {
        *ckpArrby = NULL_PTR;
        *ckpLength = 0L;
        return;
    }
    *ckpLength = (*env)->GetArrbyLength(env, jArrby);
    jTemp = (jlong*) mblloc((*ckpLength) * sizeof(jlong));
    if (jTemp == NULL) {
        throwOutOfMemoryError(env, 0);
        return;
    }
    (*env)->GetLongArrbyRegion(env, jArrby, 0, *ckpLength, jTemp);
    if ((*env)->ExceptionCheck(env)) {
        free(jTemp);
        return;
    }

    *ckpArrby = (CK_ULONG_PTR) mblloc (*ckpLength * sizeof(CK_ULONG));
    if (*ckpArrby == NULL) {
        free(jTemp);
        throwOutOfMemoryError(env, 0);
        return;
    }
    for (i=0; i<(*ckpLength); i++) {
        (*ckpArrby)[i] = jLongToCKULong(jTemp[i]);
    }
    free(jTemp);
}

/*
 * converts b jchbrArrby to b CK_CHAR brrby. The bllocbted memory hbs to be freed bfter use!
 *
 * @pbrbm env - used to cbll JNI funktions to get the brrby informtbion
 * @pbrbm jArrby - the Jbvb brrby to convert
 * @pbrbm ckpArrby - the reference, where the pointer to the new CK_CHAR brrby will be stored
 * @pbrbm ckpLength - the reference, where the brrby length will be stored
 */
void jChbrArrbyToCKChbrArrby(JNIEnv *env, const jchbrArrby jArrby, CK_CHAR_PTR *ckpArrby, CK_ULONG_PTR ckpLength)
{
    jchbr* jpTemp;
    CK_ULONG i;

    if(jArrby == NULL) {
        *ckpArrby = NULL_PTR;
        *ckpLength = 0L;
        return;
    }
    *ckpLength = (*env)->GetArrbyLength(env, jArrby);
    jpTemp = (jchbr*) mblloc((*ckpLength) * sizeof(jchbr));
    if (jpTemp == NULL) {
        throwOutOfMemoryError(env, 0);
        return;
    }
    (*env)->GetChbrArrbyRegion(env, jArrby, 0, *ckpLength, jpTemp);
    if ((*env)->ExceptionCheck(env)) {
        free(jpTemp);
        return;
    }

    *ckpArrby = (CK_CHAR_PTR) mblloc (*ckpLength * sizeof(CK_CHAR));
    if (*ckpArrby == NULL) {
        free(jpTemp);
        throwOutOfMemoryError(env, 0);
        return;
    }
    for (i=0; i<(*ckpLength); i++) {
        (*ckpArrby)[i] = jChbrToCKChbr(jpTemp[i]);
    }
    free(jpTemp);
}

/*
 * converts b jchbrArrby to b CK_UTF8CHAR brrby. The bllocbted memory hbs to be freed bfter use!
 *
 * @pbrbm env - used to cbll JNI funktions to get the brrby informtbion
 * @pbrbm jArrby - the Jbvb brrby to convert
 * @pbrbm ckpArrby - the reference, where the pointer to the new CK_UTF8CHAR brrby will be stored
 * @pbrbm ckpLength - the reference, where the brrby length will be stored
 */
void jChbrArrbyToCKUTF8ChbrArrby(JNIEnv *env, const jchbrArrby jArrby, CK_UTF8CHAR_PTR *ckpArrby, CK_ULONG_PTR ckpLength)
{
    jchbr* jTemp;
    CK_ULONG i;

    if(jArrby == NULL) {
        *ckpArrby = NULL_PTR;
        *ckpLength = 0L;
        return;
    }
    *ckpLength = (*env)->GetArrbyLength(env, jArrby);
    jTemp = (jchbr*) mblloc((*ckpLength) * sizeof(jchbr));
    if (jTemp == NULL) {
        throwOutOfMemoryError(env, 0);
        return;
    }
    (*env)->GetChbrArrbyRegion(env, jArrby, 0, *ckpLength, jTemp);
    if ((*env)->ExceptionCheck(env)) {
        free(jTemp);
        return;
    }

    *ckpArrby = (CK_UTF8CHAR_PTR) mblloc (*ckpLength * sizeof(CK_UTF8CHAR));
    if (*ckpArrby == NULL) {
        free(jTemp);
        throwOutOfMemoryError(env, 0);
        return;
    }
    for (i=0; i<(*ckpLength); i++) {
        (*ckpArrby)[i] = jChbrToCKUTF8Chbr(jTemp[i]);
    }
    free(jTemp);
}

/*
 * converts b jstring to b CK_CHAR brrby. The bllocbted memory hbs to be freed bfter use!
 *
 * @pbrbm env - used to cbll JNI funktions to get the brrby informtbion
 * @pbrbm jArrby - the Jbvb brrby to convert
 * @pbrbm ckpArrby - the reference, where the pointer to the new CK_CHAR brrby will be stored
 * @pbrbm ckpLength - the reference, where the brrby length will be stored
 */
void jStringToCKUTF8ChbrArrby(JNIEnv *env, const jstring jArrby, CK_UTF8CHAR_PTR *ckpArrby, CK_ULONG_PTR ckpLength)
{
    const chbr* pChbrArrby;
    jboolebn isCopy;

    if(jArrby == NULL) {
        *ckpArrby = NULL_PTR;
        *ckpLength = 0L;
        return;
    }

    pChbrArrby = (*env)->GetStringUTFChbrs(env, jArrby, &isCopy);
    if (pChbrArrby == NULL) { return; }

    *ckpLength = strlen(pChbrArrby);
    *ckpArrby = (CK_UTF8CHAR_PTR) mblloc((*ckpLength + 1) * sizeof(CK_UTF8CHAR));
    if (*ckpArrby == NULL) {
        (*env)->RelebseStringUTFChbrs(env, (jstring) jArrby, pChbrArrby);
        throwOutOfMemoryError(env, 0);
        return;
    }
    strcpy((chbr*)*ckpArrby, pChbrArrby);
    (*env)->RelebseStringUTFChbrs(env, (jstring) jArrby, pChbrArrby);
}

/*
 * converts b jobjectArrby with Jbvb Attributes to b CK_ATTRIBUTE brrby. The bllocbted memory
 * hbs to be freed bfter use!
 *
 * @pbrbm env - used to cbll JNI funktions to get the brrby informtbion
 * @pbrbm jArrby - the Jbvb Attribute brrby (templbte) to convert
 * @pbrbm ckpArrby - the reference, where the pointer to the new CK_ATTRIBUTE brrby will be
 *                   stored
 * @pbrbm ckpLength - the reference, where the brrby length will be stored
 */
void jAttributeArrbyToCKAttributeArrby(JNIEnv *env, jobjectArrby jArrby, CK_ATTRIBUTE_PTR *ckpArrby, CK_ULONG_PTR ckpLength)
{
    CK_ULONG i;
    jlong jLength;
    jobject jAttribute;

    TRACE0("\nDEBUG: jAttributeArrbyToCKAttributeArrby");
    if (jArrby == NULL) {
        *ckpArrby = NULL_PTR;
        *ckpLength = 0L;
        return;
    }
    jLength = (*env)->GetArrbyLength(env, jArrby);
    *ckpLength = jLongToCKULong(jLength);
    *ckpArrby = (CK_ATTRIBUTE_PTR) mblloc(*ckpLength * sizeof(CK_ATTRIBUTE));
    if (*ckpArrby == NULL) {
        throwOutOfMemoryError(env, 0);
        return;
    }
    TRACE1(", converting %d bttributes", jLength);
    for (i=0; i<(*ckpLength); i++) {
        TRACE1(", getting %d. bttribute", i);
        jAttribute = (*env)->GetObjectArrbyElement(env, jArrby, i);
        if ((*env)->ExceptionCheck(env)) {
            freeCKAttributeArrby(*ckpArrby, i);
            return;
        }
        TRACE1(", jAttribute = %d", jAttribute);
        TRACE1(", converting %d. bttribute", i);
        (*ckpArrby)[i] = jAttributeToCKAttribute(env, jAttribute);
        if ((*env)->ExceptionCheck(env)) {
            freeCKAttributeArrby(*ckpArrby, i);
            return;
        }
    }
    TRACE0("FINISHED\n");
}

/*
 * converts b CK_BYTE brrby bnd its length to b jbyteArrby.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb brrby
 * @pbrbm ckpArrby - the pointer to the CK_BYTE brrby to convert
 * @pbrbm ckpLength - the length of the brrby to convert
 * @return - the new Jbvb byte brrby or NULL if error occurred
 */
jbyteArrby ckByteArrbyToJByteArrby(JNIEnv *env, const CK_BYTE_PTR ckpArrby, CK_ULONG ckLength)
{
    CK_ULONG i;
    jbyte* jpTemp;
    jbyteArrby jArrby;

    /* if CK_BYTE is the sbme size bs jbyte, we sbve bn bdditionbl copy */
    if (sizeof(CK_BYTE) == sizeof(jbyte)) {
        jpTemp = (jbyte*) ckpArrby;
    } else {
        jpTemp = (jbyte*) mblloc((ckLength) * sizeof(jbyte));
        if (jpTemp == NULL) {
            throwOutOfMemoryError(env, 0);
            return NULL;
        }
        for (i=0; i<ckLength; i++) {
            jpTemp[i] = ckByteToJByte(ckpArrby[i]);
        }
    }

    jArrby = (*env)->NewByteArrby(env, ckULongToJSize(ckLength));
    if (jArrby != NULL) {
        (*env)->SetByteArrbyRegion(env, jArrby, 0, ckULongToJSize(ckLength), jpTemp);
    }

    if (sizeof(CK_BYTE) != sizeof(jbyte)) { free(jpTemp); }

    return jArrby ;
}

/*
 * converts b CK_ULONG brrby bnd its length to b jlongArrby.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb brrby
 * @pbrbm ckpArrby - the pointer to the CK_ULONG brrby to convert
 * @pbrbm ckpLength - the length of the brrby to convert
 * @return - the new Jbvb long brrby
 */
jlongArrby ckULongArrbyToJLongArrby(JNIEnv *env, const CK_ULONG_PTR ckpArrby, CK_ULONG ckLength)
{
    CK_ULONG i;
    jlong* jpTemp;
    jlongArrby jArrby;

    jpTemp = (jlong*) mblloc((ckLength) * sizeof(jlong));
    if (jpTemp == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL;
    }
    for (i=0; i<ckLength; i++) {
        jpTemp[i] = ckLongToJLong(ckpArrby[i]);
    }
    jArrby = (*env)->NewLongArrby(env, ckULongToJSize(ckLength));
    if (jArrby != NULL) {
        (*env)->SetLongArrbyRegion(env, jArrby, 0, ckULongToJSize(ckLength), jpTemp);
    }
    free(jpTemp);

    return jArrby ;
}

/*
 * converts b CK_CHAR brrby bnd its length to b jchbrArrby.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb brrby
 * @pbrbm ckpArrby - the pointer to the CK_CHAR brrby to convert
 * @pbrbm ckpLength - the length of the brrby to convert
 * @return - the new Jbvb chbr brrby
 */
jchbrArrby ckChbrArrbyToJChbrArrby(JNIEnv *env, const CK_CHAR_PTR ckpArrby, CK_ULONG ckLength)
{
    CK_ULONG i;
    jchbr* jpTemp;
    jchbrArrby jArrby;

    jpTemp = (jchbr*) mblloc(ckLength * sizeof(jchbr));
    if (jpTemp == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL;
    }
    for (i=0; i<ckLength; i++) {
        jpTemp[i] = ckChbrToJChbr(ckpArrby[i]);
    }
    jArrby = (*env)->NewChbrArrby(env, ckULongToJSize(ckLength));
    if (jArrby != NULL) {
        (*env)->SetChbrArrbyRegion(env, jArrby, 0, ckULongToJSize(ckLength), jpTemp);
    }
    free(jpTemp);

    return jArrby ;
}

/*
 * converts b CK_UTF8CHAR brrby bnd its length to b jchbrArrby.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb brrby
 * @pbrbm ckpArrby - the pointer to the CK_UTF8CHAR brrby to convert
 * @pbrbm ckpLength - the length of the brrby to convert
 * @return - the new Jbvb chbr brrby
 */
jchbrArrby ckUTF8ChbrArrbyToJChbrArrby(JNIEnv *env, const CK_UTF8CHAR_PTR ckpArrby, CK_ULONG ckLength)
{
    CK_ULONG i;
    jchbr* jpTemp;
    jchbrArrby jArrby;

    jpTemp = (jchbr*) mblloc(ckLength * sizeof(jchbr));
    if (jpTemp == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL;
    }
    for (i=0; i<ckLength; i++) {
        jpTemp[i] = ckUTF8ChbrToJChbr(ckpArrby[i]);
    }
    jArrby = (*env)->NewChbrArrby(env, ckULongToJSize(ckLength));
    if (jArrby != NULL) {
        (*env)->SetChbrArrbyRegion(env, jArrby, 0, ckULongToJSize(ckLength), jpTemp);
    }
    free(jpTemp);

    return jArrby ;
}

/*
 * the following functions convert Jbvb objects to PKCS#11 pointers bnd the
 * length in bytes bnd vice versb
 *
 * CK_<Type>_PTR j<Object>ToCK<Type>Ptr(JNIEnv *env, jobject jObject);
 *
 * jobject ck<Type>PtrToJ<Object>(JNIEnv *env, const CK_<Type>_PTR ckpVblue);
 *
 * The functions thbt convert b Jbvb object to b PKCS#11 pointer first bllocbte
 * the memory for the PKCS#11 pointer. Then they set ebch element corresponding
 * to the fields in the Jbvb object to convert. After use the bllocbted memory of
 * the PKCS#11 pointer hbs to be explicitly freed.
 *
 * The functions to convert b PKCS#11 pointer to b Jbvb object crebte b new Jbvb
 * object first bnd thbn they set bll fields in the object depending on the vblues
 * of the type or structure where the PKCS#11 pointer points to.
 */

/*
 * converts b CK_BBOOL pointer to b Jbvb boolebn Object.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb object
 * @pbrbm ckpVblue - the pointer to the CK_BBOOL vblue
 * @return - the new Jbvb boolebn object with the boolebn vblue
 */
jobject ckBBoolPtrToJBoolebnObject(JNIEnv *env, const CK_BBOOL *ckpVblue)
{
    jclbss jVblueObjectClbss;
    jmethodID jConstructor;
    jobject jVblueObject;
    jboolebn jVblue;

    jVblueObjectClbss = (*env)->FindClbss(env, "jbvb/lbng/Boolebn");
    if (jVblueObjectClbss == NULL) { return NULL; }
    jConstructor = (*env)->GetMethodID(env, jVblueObjectClbss, "<init>", "(Z)V");
    if (jConstructor == NULL) { return NULL; }
    jVblue = ckBBoolToJBoolebn(*ckpVblue);
    jVblueObject = (*env)->NewObject(env, jVblueObjectClbss, jConstructor, jVblue);

    return jVblueObject ;
}

/*
 * converts b CK_ULONG pointer to b Jbvb long Object.
 *
 * @pbrbm env - used to cbll JNI funktions to crebte the new Jbvb object
 * @pbrbm ckpVblue - the pointer to the CK_ULONG vblue
 * @return - the new Jbvb long object with the long vblue
 */
jobject ckULongPtrToJLongObject(JNIEnv *env, const CK_ULONG_PTR ckpVblue)
{
    jclbss jVblueObjectClbss;
    jmethodID jConstructor;
    jobject jVblueObject;
    jlong jVblue;

    jVblueObjectClbss = (*env)->FindClbss(env, "jbvb/lbng/Long");
    if (jVblueObjectClbss == NULL) { return NULL; }
    jConstructor = (*env)->GetMethodID(env, jVblueObjectClbss, "<init>", "(J)V");
    if (jConstructor == NULL) { return NULL; }
    jVblue = ckULongToJLong(*ckpVblue);
    jVblueObject = (*env)->NewObject(env, jVblueObjectClbss, jConstructor, jVblue);

    return jVblueObject ;
}

/*
 * converts b Jbvb boolebn object into b pointer to b CK_BBOOL vblue. The memory hbs to be
 * freed bfter use!
 *
 * @pbrbm env - used to cbll JNI funktions to get the vblue out of the Jbvb object
 * @pbrbm jObject - the "jbvb/lbng/Boolebn" object to convert
 * @return - the pointer to the new CK_BBOOL vblue
 */
CK_BBOOL* jBoolebnObjectToCKBBoolPtr(JNIEnv *env, jobject jObject)
{
    jclbss jObjectClbss;
    jmethodID jVblueMethod;
    jboolebn jVblue;
    CK_BBOOL *ckpVblue;

    jObjectClbss = (*env)->FindClbss(env, "jbvb/lbng/Boolebn");
    if (jObjectClbss == NULL) { return NULL; }
    jVblueMethod = (*env)->GetMethodID(env, jObjectClbss, "boolebnVblue", "()Z");
    if (jVblueMethod == NULL) { return NULL; }
    jVblue = (*env)->CbllBoolebnMethod(env, jObject, jVblueMethod);
    ckpVblue = (CK_BBOOL *) mblloc(sizeof(CK_BBOOL));
    if (ckpVblue == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL;
    }
    *ckpVblue = jBoolebnToCKBBool(jVblue);

    return ckpVblue ;
}

/*
 * converts b Jbvb byte object into b pointer to b CK_BYTE vblue. The memory hbs to be
 * freed bfter use!
 *
 * @pbrbm env - used to cbll JNI funktions to get the vblue out of the Jbvb object
 * @pbrbm jObject - the "jbvb/lbng/Byte" object to convert
 * @return - the pointer to the new CK_BYTE vblue
 */
CK_BYTE_PTR jByteObjectToCKBytePtr(JNIEnv *env, jobject jObject)
{
    jclbss jObjectClbss;
    jmethodID jVblueMethod;
    jbyte jVblue;
    CK_BYTE_PTR ckpVblue;

    jObjectClbss = (*env)->FindClbss(env, "jbvb/lbng/Byte");
    if (jObjectClbss == NULL) { return NULL; }
    jVblueMethod = (*env)->GetMethodID(env, jObjectClbss, "byteVblue", "()B");
    if (jVblueMethod == NULL) { return NULL; }
    jVblue = (*env)->CbllByteMethod(env, jObject, jVblueMethod);
    ckpVblue = (CK_BYTE_PTR) mblloc(sizeof(CK_BYTE));
    if (ckpVblue == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL;
    }
    *ckpVblue = jByteToCKByte(jVblue);
    return ckpVblue ;
}

/*
 * converts b Jbvb integer object into b pointer to b CK_ULONG vblue. The memory hbs to be
 * freed bfter use!
 *
 * @pbrbm env - used to cbll JNI funktions to get the vblue out of the Jbvb object
 * @pbrbm jObject - the "jbvb/lbng/Integer" object to convert
 * @return - the pointer to the new CK_ULONG vblue
 */
CK_ULONG* jIntegerObjectToCKULongPtr(JNIEnv *env, jobject jObject)
{
    jclbss jObjectClbss;
    jmethodID jVblueMethod;
    jint jVblue;
    CK_ULONG *ckpVblue;

    jObjectClbss = (*env)->FindClbss(env, "jbvb/lbng/Integer");
    if (jObjectClbss == NULL) { return NULL; }
    jVblueMethod = (*env)->GetMethodID(env, jObjectClbss, "intVblue", "()I");
    if (jVblueMethod == NULL) { return NULL; }
    jVblue = (*env)->CbllIntMethod(env, jObject, jVblueMethod);
    ckpVblue = (CK_ULONG *) mblloc(sizeof(CK_ULONG));
    if (ckpVblue == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL;
    }
    *ckpVblue = jLongToCKLong(jVblue);
    return ckpVblue ;
}

/*
 * converts b Jbvb long object into b pointer to b CK_ULONG vblue. The memory hbs to be
 * freed bfter use!
 *
 * @pbrbm env - used to cbll JNI funktions to get the vblue out of the Jbvb object
 * @pbrbm jObject - the "jbvb/lbng/Long" object to convert
 * @return - the pointer to the new CK_ULONG vblue
 */
CK_ULONG* jLongObjectToCKULongPtr(JNIEnv *env, jobject jObject)
{
    jclbss jObjectClbss;
    jmethodID jVblueMethod;
    jlong jVblue;
    CK_ULONG *ckpVblue;

    jObjectClbss = (*env)->FindClbss(env, "jbvb/lbng/Long");
    if (jObjectClbss == NULL) { return NULL; }
    jVblueMethod = (*env)->GetMethodID(env, jObjectClbss, "longVblue", "()J");
    if (jVblueMethod == NULL) { return NULL; }
    jVblue = (*env)->CbllLongMethod(env, jObject, jVblueMethod);
    ckpVblue = (CK_ULONG *) mblloc(sizeof(CK_ULONG));
    if (ckpVblue == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL;
    }
    *ckpVblue = jLongToCKULong(jVblue);

    return ckpVblue ;
}

/*
 * converts b Jbvb chbr object into b pointer to b CK_CHAR vblue. The memory hbs to be
 * freed bfter use!
 *
 * @pbrbm env - used to cbll JNI funktions to get the vblue out of the Jbvb object
 * @pbrbm jObject - the "jbvb/lbng/Chbr" object to convert
 * @return - the pointer to the new CK_CHAR vblue
 */
CK_CHAR_PTR jChbrObjectToCKChbrPtr(JNIEnv *env, jobject jObject)
{
    jclbss jObjectClbss;
    jmethodID jVblueMethod;
    jchbr jVblue;
    CK_CHAR_PTR ckpVblue;

    jObjectClbss = (*env)->FindClbss(env, "jbvb/lbng/Chbr");
    if (jObjectClbss == NULL) { return NULL; }
    jVblueMethod = (*env)->GetMethodID(env, jObjectClbss, "chbrVblue", "()C");
    if (jVblueMethod == NULL) { return NULL; }
    jVblue = (*env)->CbllChbrMethod(env, jObject, jVblueMethod);
    ckpVblue = (CK_CHAR_PTR) mblloc(sizeof(CK_CHAR));
    if (ckpVblue == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL;
    }
    *ckpVblue = jChbrToCKChbr(jVblue);

    return ckpVblue ;
}

/*
 * converts b Jbvb object into b pointer to CK-type or b CK-structure with the length in Bytes.
 * The memory of *ckpObjectPtr to be freed bfter use! This function is only used by
 * jAttributeToCKAttribute by now.
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses bnd objects
 * @pbrbm jObject - the Jbvb object to convert
 * @pbrbm ckpObjectPtr - the reference of the new pointer to the new CK-vblue or CK-structure
 * @pbrbm ckpLength - the reference of the length in bytes of the new CK-vblue or CK-structure
 */
void jObjectToPrimitiveCKObjectPtrPtr(JNIEnv *env, jobject jObject, CK_VOID_PTR *ckpObjectPtr, CK_ULONG *ckpLength)
{
    jclbss jLongClbss, jBoolebnClbss, jByteArrbyClbss, jChbrArrbyClbss;
    jclbss jByteClbss, jDbteClbss, jChbrbcterClbss, jIntegerClbss;
    jclbss jBoolebnArrbyClbss, jIntArrbyClbss, jLongArrbyClbss;
    jclbss jStringClbss;
    jclbss jObjectClbss, jClbssClbss;
    CK_VOID_PTR ckpVoid = *ckpObjectPtr;
    jmethodID jMethod;
    jobject jClbssObject;
    jstring jClbssNbmeString;
    chbr *clbssNbmeString, *exceptionMsgPrefix, *exceptionMsg;

    TRACE0("\nDEBUG: jObjectToPrimitiveCKObjectPtrPtr");
    if (jObject == NULL) {
        *ckpObjectPtr = NULL;
        *ckpLength = 0;
        return;
    }

    jLongClbss = (*env)->FindClbss(env, "jbvb/lbng/Long");
    if (jLongClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jObject, jLongClbss)) {
        *ckpObjectPtr = jLongObjectToCKULongPtr(env, jObject);
        *ckpLength = sizeof(CK_ULONG);
        TRACE1("<converted long vblue %X>", *((CK_ULONG *) *ckpObjectPtr));
        return;
    }

    jBoolebnClbss = (*env)->FindClbss(env, "jbvb/lbng/Boolebn");
    if (jBoolebnClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jObject, jBoolebnClbss)) {
        *ckpObjectPtr = jBoolebnObjectToCKBBoolPtr(env, jObject);
        *ckpLength = sizeof(CK_BBOOL);
        TRACE0(" <converted boolebn vblue ");
        TRACE0((*((CK_BBOOL *) *ckpObjectPtr) == TRUE) ? "TRUE>" : "FALSE>");
        return;
    }

    jByteArrbyClbss = (*env)->FindClbss(env, "[B");
    if (jByteArrbyClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jObject, jByteArrbyClbss)) {
        jByteArrbyToCKByteArrby(env, jObject, (CK_BYTE_PTR*)ckpObjectPtr, ckpLength);
        return;
    }

    jChbrArrbyClbss = (*env)->FindClbss(env, "[C");
    if (jChbrArrbyClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jObject, jChbrArrbyClbss)) {
        jChbrArrbyToCKUTF8ChbrArrby(env, jObject, (CK_UTF8CHAR_PTR*)ckpObjectPtr, ckpLength);
        return;
    }

    jByteClbss = (*env)->FindClbss(env, "jbvb/lbng/Byte");
    if (jByteClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jObject, jByteClbss)) {
        *ckpObjectPtr = jByteObjectToCKBytePtr(env, jObject);
        *ckpLength = sizeof(CK_BYTE);
        TRACE1("<converted byte vblue %X>", *((CK_BYTE *) *ckpObjectPtr));
        return;
    }

    jDbteClbss = (*env)->FindClbss(env, CLASS_DATE);
    if (jDbteClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jObject, jDbteClbss)) {
        *ckpObjectPtr = jDbteObjectPtrToCKDbtePtr(env, jObject);
        *ckpLength = sizeof(CK_DATE);
        TRACE3("<converted dbte vblue %.4s-%.2s-%.2s>", (*((CK_DATE *) *ckpObjectPtr)).yebr, (*((CK_DATE *) *ckpObjectPtr)).month, (*((CK_DATE *) *ckpObjectPtr)).dby);
        return;
    }

    jChbrbcterClbss = (*env)->FindClbss(env, "jbvb/lbng/Chbrbcter");
    if (jChbrbcterClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jObject, jChbrbcterClbss)) {
        *ckpObjectPtr = jChbrObjectToCKChbrPtr(env, jObject);
        *ckpLength = sizeof(CK_UTF8CHAR);
        TRACE1("<converted chbr vblue %c>", *((CK_CHAR *) *ckpObjectPtr));
        return;
    }

    jIntegerClbss = (*env)->FindClbss(env, "jbvb/lbng/Integer");
    if (jIntegerClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jObject, jIntegerClbss)) {
        *ckpObjectPtr = jIntegerObjectToCKULongPtr(env, jObject);
        *ckpLength = sizeof(CK_ULONG);
        TRACE1("<converted integer vblue %X>", *((CK_ULONG *) *ckpObjectPtr));
        return;
    }

    jBoolebnArrbyClbss = (*env)->FindClbss(env, "[Z");
    if (jBoolebnArrbyClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jObject, jBoolebnArrbyClbss)) {
        jBoolebnArrbyToCKBBoolArrby(env, jObject, (CK_BBOOL**)ckpObjectPtr, ckpLength);
        return;
    }

    jIntArrbyClbss = (*env)->FindClbss(env, "[I");
    if (jIntArrbyClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jObject, jIntArrbyClbss)) {
        jLongArrbyToCKULongArrby(env, jObject, (CK_ULONG_PTR*)ckpObjectPtr, ckpLength);
        return;
    }

    jLongArrbyClbss = (*env)->FindClbss(env, "[J");
    if (jLongArrbyClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jObject, jLongArrbyClbss)) {
        jLongArrbyToCKULongArrby(env, jObject, (CK_ULONG_PTR*)ckpObjectPtr, ckpLength);
        return;
    }

    jStringClbss = (*env)->FindClbss(env, "jbvb/lbng/String");
    if (jStringClbss == NULL) { return; }
    if ((*env)->IsInstbnceOf(env, jObject, jStringClbss)) {
        jStringToCKUTF8ChbrArrby(env, jObject, (CK_UTF8CHAR_PTR*)ckpObjectPtr, ckpLength);
        return;
    }

    /* type of jObject unknown, throw PKCS11RuntimeException */
    jObjectClbss = (*env)->FindClbss(env, "jbvb/lbng/Object");
    if (jObjectClbss == NULL) { return; }
    jMethod = (*env)->GetMethodID(env, jObjectClbss, "getClbss", "()Ljbvb/lbng/Clbss;");
    if (jMethod == NULL) { return; }
    jClbssObject = (*env)->CbllObjectMethod(env, jObject, jMethod);
    bssert(jClbssObject != 0);
    jClbssClbss = (*env)->FindClbss(env, "jbvb/lbng/Clbss");
    if (jClbssClbss == NULL) { return; }
    jMethod = (*env)->GetMethodID(env, jClbssClbss, "getNbme", "()Ljbvb/lbng/String;");
    if (jMethod == NULL) { return; }
    jClbssNbmeString = (jstring)
        (*env)->CbllObjectMethod(env, jClbssObject, jMethod);
    bssert(jClbssNbmeString != 0);
    clbssNbmeString = (chbr*)
        (*env)->GetStringUTFChbrs(env, jClbssNbmeString, NULL);
    if (clbssNbmeString == NULL) { return; }
    exceptionMsgPrefix = "Jbvb object of this clbss cbnnot be converted to nbtive PKCS#11 type: ";
    exceptionMsg = (chbr *)
        mblloc((strlen(exceptionMsgPrefix) + strlen(clbssNbmeString) + 1));
    if (exceptionMsg == NULL) {
        (*env)->RelebseStringUTFChbrs(env, jClbssNbmeString, clbssNbmeString);
        throwOutOfMemoryError(env, 0);
        return;
    }
    strcpy(exceptionMsg, exceptionMsgPrefix);
    strcbt(exceptionMsg, clbssNbmeString);
    (*env)->RelebseStringUTFChbrs(env, jClbssNbmeString, clbssNbmeString);
    throwPKCS11RuntimeException(env, exceptionMsg);
    free(exceptionMsg);
    *ckpObjectPtr = NULL;
    *ckpLength = 0;

    TRACE0("FINISHED\n");
}

#ifdef P11_MEMORYDEBUG

#undef mblloc
#undef free

void *p11mblloc(size_t c, chbr *file, int line) {
    void *p = mblloc(c);
    printf("mblloc\t%08x\t%d\t%s:%d\n", p, c, file, line); fflush(stdout);
    return p;
}

void p11free(void *p, chbr *file, int line) {
    printf("free\t%08x\t\t%s:%d\n", p, file, line); fflush(stdout);
    free(p);
}

#endif

