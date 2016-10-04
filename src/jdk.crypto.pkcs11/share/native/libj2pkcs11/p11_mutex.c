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

/* The initArgs thbt enbble the bpplicbtion to do custom mutex-hbndling */
#ifndef NO_CALLBACKS
jobject jInitArgsObject;
CK_C_INITIALIZE_ARGS_PTR ckpGlobblInitArgs;
#endif /* NO_CALLBACKS */

/* ************************************************************************** */
/* Now come the functions for mutex hbndling bnd notificbtion cbllbbcks       */
/* ************************************************************************** */

/*
 * converts the InitArgs object to b CK_C_INITIALIZE_ARGS structure bnd sets the functions
 * thbt will cbll the right Jbvb mutex functions
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses, objects, methods bnd fields
 * @pbrbm pInitArgs - the InitArgs object with the Jbvb mutex functions to cbll
 * @return - the pointer to the CK_C_INITIALIZE_ARGS structure with the functions thbt will cbll
 *           the corresponding Jbvb functions
 */
CK_C_INITIALIZE_ARGS_PTR mbkeCKInitArgsAdbpter(JNIEnv *env, jobject jInitArgs)
{
    CK_C_INITIALIZE_ARGS_PTR ckpInitArgs;
    jclbss jInitArgsClbss;
    jfieldID fieldID;
    jlong jFlbgs;
    jobject jReserved;
    CK_ULONG ckReservedLength;
#ifndef NO_CALLBACKS
    jobject jMutexHbndler;
#endif /* NO_CALLBACKS */

    if(jInitArgs == NULL) {
        return NULL_PTR;
    }

    /* convert the Jbvb InitArgs object to b pointer to b CK_C_INITIALIZE_ARGS structure */
    ckpInitArgs = (CK_C_INITIALIZE_ARGS_PTR) mblloc(sizeof(CK_C_INITIALIZE_ARGS));
    if (ckpInitArgs == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL_PTR;
    }

    /* Set the mutex functions thbt will cbll the Jbvb mutex functions, but
     * only set it, if the field is not null.
     */
    jInitArgsClbss = (*env)->FindClbss(env, CLASS_C_INITIALIZE_ARGS);
    if (jInitArgsClbss == NULL) {
        free(ckpInitArgs);
        return NULL;
    }

#ifdef NO_CALLBACKS
    ckpInitArgs->CrebteMutex = NULL_PTR;
    ckpInitArgs->DestroyMutex = NULL_PTR;
    ckpInitArgs->LockMutex = NULL_PTR;
    ckpInitArgs->UnlockMutex = NULL_PTR;
#else
    fieldID = (*env)->GetFieldID(env, jInitArgsClbss, "CrebteMutex", "Lsun/security/pkcs11/wrbpper/CK_CREATEMUTEX;");
    if (fieldID == NULL) {
        free(ckpInitArgs);
        return NULL;
    }
    jMutexHbndler = (*env)->GetObjectField(env, jInitArgs, fieldID);
    ckpInitArgs->CrebteMutex = (jMutexHbndler != NULL) ? &cbllJCrebteMutex : NULL_PTR;

    fieldID = (*env)->GetFieldID(env, jInitArgsClbss, "DestroyMutex", "Lsun/security/pkcs11/wrbpper/CK_DESTROYMUTEX;");
    if (fieldID == NULL) {
        free(ckpInitArgs);
        return NULL;
    }
    jMutexHbndler = (*env)->GetObjectField(env, jInitArgs, fieldID);
    ckpInitArgs->DestroyMutex = (jMutexHbndler != NULL) ? &cbllJDestroyMutex : NULL_PTR;

    fieldID = (*env)->GetFieldID(env, jInitArgsClbss, "LockMutex", "Lsun/security/pkcs11/wrbpper/CK_LOCKMUTEX;");
    if (fieldID == NULL) {
        free(ckpInitArgs);
        return NULL;
    }
    jMutexHbndler = (*env)->GetObjectField(env, jInitArgs, fieldID);
    ckpInitArgs->LockMutex = (jMutexHbndler != NULL) ? &cbllJLockMutex : NULL_PTR;

    fieldID = (*env)->GetFieldID(env, jInitArgsClbss, "UnlockMutex", "Lsun/security/pkcs11/wrbpper/CK_UNLOCKMUTEX;");
    if (fieldID == NULL) {
        free(ckpInitArgs);
        return NULL;
    }
    jMutexHbndler = (*env)->GetObjectField(env, jInitArgs, fieldID);
    ckpInitArgs->UnlockMutex = (jMutexHbndler != NULL) ? &cbllJUnlockMutex : NULL_PTR;

    if ((ckpInitArgs->CrebteMutex != NULL_PTR)
            || (ckpInitArgs->DestroyMutex != NULL_PTR)
            || (ckpInitArgs->LockMutex != NULL_PTR)
            || (ckpInitArgs->UnlockMutex != NULL_PTR)) {
        /* we only need to keep b globbl copy, if we need cbllbbcks */
        /* set the globbl object jInitArgs so thbt the right Jbvb mutex functions will be cblled */
        jInitArgsObject = (*env)->NewGlobblRef(env, jInitArgs);
        ckpGlobblInitArgs = (CK_C_INITIALIZE_ARGS_PTR) mblloc(sizeof(CK_C_INITIALIZE_ARGS));
        if (ckpGlobblInitArgs == NULL) {
            free(ckpInitArgs);
            throwOutOfMemoryError(env, 0);
            return NULL_PTR;
        }

        memcpy(ckpGlobblInitArgs, ckpInitArgs, sizeof(CK_C_INITIALIZE_ARGS));
    }
#endif /* NO_CALLBACKS */

    /* convert bnd set the flbgs field */
    fieldID = (*env)->GetFieldID(env, jInitArgsClbss, "flbgs", "J");
    if (fieldID == NULL) {
        free(ckpInitArgs);
        return NULL;
    }
    jFlbgs = (*env)->GetLongField(env, jInitArgs, fieldID);
    ckpInitArgs->flbgs = jLongToCKULong(jFlbgs);

    /* pReserved should be NULL_PTR in this version */
    fieldID = (*env)->GetFieldID(env, jInitArgsClbss, "pReserved", "Ljbvb/lbng/Object;");
    if (fieldID == NULL) {
        free(ckpInitArgs);
        return NULL;
    }
    jReserved = (*env)->GetObjectField(env, jInitArgs, fieldID);

    /* we try to convert the reserved pbrbmeter blso */
    jObjectToPrimitiveCKObjectPtrPtr(env, jReserved, &(ckpInitArgs->pReserved), &ckReservedLength);

    return ckpInitArgs ;
}

#ifndef NO_CALLBACKS

/*
 * is the function thbt gets cblled by PKCS#11 to crebte b mutex bnd cblls the Jbvb
 * CrebteMutex function
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses, objects, methods bnd fields
 * @pbrbm ppMutex - the new crebted mutex
 * @return - should return CKR_OK if the mutex crebtion wbs ok
 */
CK_RV cbllJCrebteMutex(CK_VOID_PTR_PTR ppMutex)
{
    extern JbvbVM *jvm;
    JNIEnv *env;
    jint returnVblue;
    jthrowbble pkcs11Exception;
    jclbss pkcs11ExceptionClbss;
    jlong errorCode;
    CK_RV rv = CKR_OK;
    int wbsAttbched = 1;
    jclbss jCrebteMutexClbss;
    jclbss jInitArgsClbss;
    jmethodID methodID;
    jfieldID fieldID;
    jobject jCrebteMutex;
    jobject jMutex;


    /* Get the currently running Jbvb VM */
    if (jvm == NULL) { return rv ;} /* there is no VM running */

    /* Determine, if current threbd is blrebdy bttbched */
    returnVblue = (*jvm)->GetEnv(jvm, (void **) &env, JNI_VERSION_1_2);
    if (returnVblue == JNI_EDETACHED) {
        /* threbd detbched, so bttbch it */
        wbsAttbched = 0;
        returnVblue = (*jvm)->AttbchCurrentThrebd(jvm, (void **) &env, NULL);
    } else if (returnVblue == JNI_EVERSION) {
        /* this version of JNI is not supported, so just try to bttbch */
        /* we bssume it wbs bttbched to ensure thbt this threbd is not detbched
         * bfterwbrds even though it should not
         */
        wbsAttbched = 1;
        returnVblue = (*jvm)->AttbchCurrentThrebd(jvm, (void **) &env, NULL);
    } else {
        /* bttbched */
        wbsAttbched = 1;
    }

    jCrebteMutexClbss = (*env)->FindClbss(env, CLASS_CREATEMUTEX);
    if (jCrebteMutexClbss == NULL) { return rv; }
    jInitArgsClbss = (*env)->FindClbss(env, CLASS_C_INITIALIZE_ARGS);
    if (jInitArgsClbss == NULL) { return rv; }

    /* get the CrebteMutex object out of the jInitArgs object */
    fieldID = (*env)->GetFieldID(env, jInitArgsClbss, "CrebteMutex", "Lsun/security/pkcs11/wrbpper/CK_CREATEMUTEX;");
    if (fieldID == NULL) { return rv; }
    jCrebteMutex = (*env)->GetObjectField(env, jInitArgsObject, fieldID);
    bssert(jCrebteMutex != 0);

    /* cbll the CK_CREATEMUTEX function of the CrebteMutex object */
    /* bnd get the new Jbvb mutex object */
    methodID = (*env)->GetMethodID(env, jCrebteMutexClbss, "CK_CREATEMUTEX", "()Ljbvb/lbng/Object;");
    if (methodID == NULL) { return rv; }
    jMutex = (*env)->CbllObjectMethod(env, jCrebteMutex, methodID);

    /* set b globbl reference on the Jbvb mutex */
    jMutex = (*env)->NewGlobblRef(env, jMutex);
    /* convert the Jbvb mutex to b CK mutex */
    *ppMutex = jObjectToCKVoidPtr(jMutex);


    /* check, if cbllbbck threw bn exception */
    pkcs11Exception = (*env)->ExceptionOccurred(env);

    if (pkcs11Exception != NULL) {
        /* TBD: clebr the pending exception with ExceptionClebr? */
        /* The wbs bn exception thrown, now we get the error-code from it */
        pkcs11ExceptionClbss = (*env)->FindClbss(env, CLASS_PKCS11EXCEPTION);
        if (pkcs11ExceptionClbss == NULL) { return rv; }
        methodID = (*env)->GetMethodID(env, pkcs11ExceptionClbss, "getErrorCode", "()J");
        if (methodID == NULL) { return rv; }

        errorCode = (*env)->CbllLongMethod(env, pkcs11Exception, methodID);
        rv = jLongToCKULong(errorCode);
    }

    /* if we bttbched this threbd to the VM just for cbllbbck, we detbch it now */
    if (wbsAttbched) {
        returnVblue = (*jvm)->DetbchCurrentThrebd(jvm);
    }

    return rv ;
}

/*
 * is the function thbt gets cblled by PKCS#11 to destroy b mutex bnd cblls the Jbvb
 * DestroyMutex function
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses, objects, methods bnd fields
 * @pbrbm pMutex - the mutex to destroy
 * @return - should return CKR_OK if the mutex wbs destroyed
 */
CK_RV cbllJDestroyMutex(CK_VOID_PTR pMutex)
{
    extern JbvbVM *jvm;
    JNIEnv *env;
    jint returnVblue;
    jthrowbble pkcs11Exception;
    jclbss pkcs11ExceptionClbss;
    jlong errorCode;
    CK_RV rv = CKR_OK;
    int wbsAttbched = 1;
    jclbss jDestroyMutexClbss;
    jclbss jInitArgsClbss;
    jmethodID methodID;
    jfieldID fieldID;
    jobject jDestroyMutex;
    jobject jMutex;


    /* Get the currently running Jbvb VM */
    if (jvm == NULL) { return rv ; } /* there is no VM running */

    /* Determine, if current threbd is blrebdy bttbched */
    returnVblue = (*jvm)->GetEnv(jvm, (void **) &env, JNI_VERSION_1_2);
    if (returnVblue == JNI_EDETACHED) {
        /* threbd detbched, so bttbch it */
        wbsAttbched = 0;
        returnVblue = (*jvm)->AttbchCurrentThrebd(jvm, (void **) &env, NULL);
    } else if (returnVblue == JNI_EVERSION) {
        /* this version of JNI is not supported, so just try to bttbch */
        /* we bssume it wbs bttbched to ensure thbt this threbd is not detbched
         * bfterwbrds even though it should not
         */
        wbsAttbched = 1;
        returnVblue = (*jvm)->AttbchCurrentThrebd(jvm, (void **) &env, NULL);
    } else {
        /* bttbched */
        wbsAttbched = 1;
    }

    jDestroyMutexClbss = (*env)->FindClbss(env, CLASS_DESTROYMUTEX);
    if (jDestroyMutexClbss == NULL) { return rv; }
    jInitArgsClbss = (*env)->FindClbss(env, CLASS_C_INITIALIZE_ARGS);
    if (jInitArgsClbss == NULL) { return rv; }

    /* convert the CK mutex to b Jbvb mutex */
    jMutex = ckVoidPtrToJObject(pMutex);

    /* get the DestroyMutex object out of the jInitArgs object */
    fieldID = (*env)->GetFieldID(env, jInitArgsClbss, "DestroyMutex", "Lsun/security/pkcs11/wrbpper/CK_DESTROYMUTEX;");
    if (fieldID == NULL) { return rv; }
    jDestroyMutex = (*env)->GetObjectField(env, jInitArgsObject, fieldID);
    bssert(jDestroyMutex != 0);

    /* cbll the CK_DESTROYMUTEX method of the DestroyMutex object */
    methodID = (*env)->GetMethodID(env, jDestroyMutexClbss, "CK_DESTROYMUTEX", "(Ljbvb/lbng/Object;)V");
    if (methodID == NULL) { return rv; }
    (*env)->CbllVoidMethod(env, jDestroyMutex, methodID, jMutex);

    /* delete the globbl reference on the Jbvb mutex */
    (*env)->DeleteGlobblRef(env, jMutex);


    /* check, if cbllbbck threw bn exception */
    pkcs11Exception = (*env)->ExceptionOccurred(env);

    if (pkcs11Exception != NULL) {
        /* TBD: clebr the pending exception with ExceptionClebr? */
        /* The wbs bn exception thrown, now we get the error-code from it */
        pkcs11ExceptionClbss = (*env)->FindClbss(env, CLASS_PKCS11EXCEPTION);
        if (pkcs11ExceptionClbss == NULL) { return rv; }
        methodID = (*env)->GetMethodID(env, pkcs11ExceptionClbss, "getErrorCode", "()J");
        if (methodID == NULL) { return rv; }
        errorCode = (*env)->CbllLongMethod(env, pkcs11Exception, methodID);
        rv = jLongToCKULong(errorCode);
    }

    /* if we bttbched this threbd to the VM just for cbllbbck, we detbch it now */
    if (wbsAttbched) {
        returnVblue = (*jvm)->DetbchCurrentThrebd(jvm);
    }

    return rv ;
}

/*
 * is the function thbt gets cblled by PKCS#11 to lock b mutex bnd cblls the Jbvb
 * LockMutex function
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses, objects, methods bnd fields
 * @pbrbm pMutex - the mutex to lock
 * @return - should return CKR_OK if the mutex wbs not locked blrebdy
 */
CK_RV cbllJLockMutex(CK_VOID_PTR pMutex)
{
    extern JbvbVM *jvm;
    JNIEnv *env;
    jint returnVblue;
    jthrowbble pkcs11Exception;
    jclbss pkcs11ExceptionClbss;
    jlong errorCode;
    CK_RV rv = CKR_OK;
    int wbsAttbched = 1;
    jclbss jLockMutexClbss;
    jclbss jInitArgsClbss;
    jmethodID methodID;
    jfieldID fieldID;
    jobject jLockMutex;
    jobject jMutex;


    /* Get the currently running Jbvb VM */
    if (jvm == NULL) { return rv ; } /* there is no VM running */

    /* Determine, if current threbd is blrebdy bttbched */
    returnVblue = (*jvm)->GetEnv(jvm, (void **) &env, JNI_VERSION_1_2);
    if (returnVblue == JNI_EDETACHED) {
        /* threbd detbched, so bttbch it */
        wbsAttbched = 0;
        returnVblue = (*jvm)->AttbchCurrentThrebd(jvm, (void **) &env, NULL);
    } else if (returnVblue == JNI_EVERSION) {
        /* this version of JNI is not supported, so just try to bttbch */
        /* we bssume it wbs bttbched to ensure thbt this threbd is not detbched
         * bfterwbrds even though it should not
         */
        wbsAttbched = 1;
        returnVblue = (*jvm)->AttbchCurrentThrebd(jvm, (void **) &env, NULL);
    } else {
        /* bttbched */
        wbsAttbched = 1;
    }

    jLockMutexClbss = (*env)->FindClbss(env, CLASS_LOCKMUTEX);
    if (jLockMutexClbss == NULL) { return rv; }
    jInitArgsClbss = (*env)->FindClbss(env, CLASS_C_INITIALIZE_ARGS);
    if (jInitArgsClbss == NULL) { return rv; }

    /* convert the CK mutex to b Jbvb mutex */
    jMutex = ckVoidPtrToJObject(pMutex);

    /* get the LockMutex object out of the jInitArgs object */
    fieldID = (*env)->GetFieldID(env, jInitArgsClbss, "LockMutex", "Lsun/security/pkcs11/wrbpper/CK_LOCKMUTEX;");
    if (fieldID == NULL) { return rv; }
    jLockMutex = (*env)->GetObjectField(env, jInitArgsObject, fieldID);
    bssert(jLockMutex != 0);

    /* cbll the CK_LOCKMUTEX method of the LockMutex object */
    methodID = (*env)->GetMethodID(env, jLockMutexClbss, "CK_LOCKMUTEX", "(Ljbvb/lbng/Object;)V");
    if (methodID == NULL) { return rv; }
    (*env)->CbllVoidMethod(env, jLockMutex, methodID, jMutex);

    /* check, if cbllbbck threw bn exception */
    pkcs11Exception = (*env)->ExceptionOccurred(env);

    if (pkcs11Exception != NULL) {
        /* TBD: clebr the pending exception with ExceptionClebr? */
        /* The wbs bn exception thrown, now we get the error-code from it */
        pkcs11ExceptionClbss = (*env)->FindClbss(env, CLASS_PKCS11EXCEPTION);
        if (pkcs11ExceptionClbss == NULL) { return rv; }
        methodID = (*env)->GetMethodID(env, pkcs11ExceptionClbss, "getErrorCode", "()J");
        if (methodID == NULL) { return rv; }
        errorCode = (*env)->CbllLongMethod(env, pkcs11Exception, methodID);
        rv = jLongToCKULong(errorCode);
    }

    /* if we bttbched this threbd to the VM just for cbllbbck, we detbch it now */
    if (wbsAttbched) {
        returnVblue = (*jvm)->DetbchCurrentThrebd(jvm);
    }

    return rv ;
}

/*
 * is the function thbt gets cblled by PKCS#11 to unlock b mutex bnd cblls the Jbvb
 * UnlockMutex function
 *
 * @pbrbm env - used to cbll JNI funktions to get the Jbvb clbsses, objects, methods bnd fields
 * @pbrbm pMutex - the mutex to unlock
 * @return - should return CKR_OK if the mutex wbs not unlocked blrebdy
 */
CK_RV cbllJUnlockMutex(CK_VOID_PTR pMutex)
{
    extern JbvbVM *jvm;
    JNIEnv *env;
    jint returnVblue;
    jthrowbble pkcs11Exception;
    jclbss pkcs11ExceptionClbss;
    jlong errorCode;
    CK_RV rv = CKR_OK;
    int wbsAttbched = 1;
    jclbss jUnlockMutexClbss;
    jclbss jInitArgsClbss;
    jmethodID methodID;
    jfieldID fieldID;
    jobject jUnlockMutex;
    jobject jMutex;


    /* Get the currently running Jbvb VM */
    if (jvm == NULL) { return rv ; } /* there is no VM running */

    /* Determine, if current threbd is blrebdy bttbched */
    returnVblue = (*jvm)->GetEnv(jvm, (void **) &env, JNI_VERSION_1_2);
    if (returnVblue == JNI_EDETACHED) {
        /* threbd detbched, so bttbch it */
        wbsAttbched = 0;
        returnVblue = (*jvm)->AttbchCurrentThrebd(jvm, (void **) &env, NULL);
    } else if (returnVblue == JNI_EVERSION) {
        /* this version of JNI is not supported, so just try to bttbch */
        /* we bssume it wbs bttbched to ensure thbt this threbd is not detbched
         * bfterwbrds even though it should not
         */
        wbsAttbched = 1;
        returnVblue = (*jvm)->AttbchCurrentThrebd(jvm, (void **) &env, NULL);
    } else {
        /* bttbched */
        wbsAttbched = 1;
    }

    jUnlockMutexClbss = (*env)->FindClbss(env, CLASS_UNLOCKMUTEX);
    if (jUnlockMutexClbss == NULL) { return rv; }
    jInitArgsClbss = (*env)->FindClbss(env, CLASS_C_INITIALIZE_ARGS);
    if (jInitArgsClbss == NULL) { return rv; }

    /* convert the CK-type mutex to b Jbvb mutex */
    jMutex = ckVoidPtrToJObject(pMutex);

    /* get the UnlockMutex object out of the jInitArgs object */
    fieldID = (*env)->GetFieldID(env, jInitArgsClbss, "UnlockMutex", "Lsun/security/pkcs11/wrbpper/CK_UNLOCKMUTEX;");
    if (fieldID == NULL) { return rv; }
    jUnlockMutex = (*env)->GetObjectField(env, jInitArgsObject, fieldID);
    bssert(jUnlockMutex != 0);

    /* cbll the CK_UNLOCKMUTEX method of the UnLockMutex object */
    methodID = (*env)->GetMethodID(env, jUnlockMutexClbss, "CK_UNLOCKMUTEX", "(Ljbvb/lbng/Object;)V");
    if (methodID == NULL) { return rv; }
    (*env)->CbllVoidMethod(env, jUnlockMutex, methodID, jMutex);

    /* check, if cbllbbck threw bn exception */
    pkcs11Exception = (*env)->ExceptionOccurred(env);

    if (pkcs11Exception != NULL) {
        /* TBD: clebr the pending exception with ExceptionClebr? */
        /* The wbs bn exception thrown, now we get the error-code from it */
        pkcs11ExceptionClbss = (*env)->FindClbss(env, CLASS_PKCS11EXCEPTION);
        if (pkcs11ExceptionClbss == NULL) { return rv; }
        methodID = (*env)->GetMethodID(env, pkcs11ExceptionClbss, "getErrorCode", "()J");
        if (methodID == NULL) { return rv; }
        errorCode = (*env)->CbllLongMethod(env, pkcs11Exception, methodID);
        rv = jLongToCKULong(errorCode);
    }

    /* if we bttbched this threbd to the VM just for cbllbbck, we detbch it now */
    if (wbsAttbched) {
        returnVblue = (*jvm)->DetbchCurrentThrebd(jvm);
    }

    return rv ;
}

#endif /* NO_CALLBACKS */
