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

#include "sun_security_pkcs11_wrbpper_PKCS11.h"

/* The list of notify cbllbbck hbndles thbt bre currently bctive bnd wbiting
 * for cbllbbcks from their sessions.
 */
#ifndef NO_CALLBACKS
NotifyListNode *notifyListHebd = NULL;
jobject notifyListLock = NULL;
#endif /* NO_CALLBACKS */

#ifdef P11_ENABLE_C_OPENSESSION
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_OpenSession
 * Signbture: (JJLjbvb/lbng/Object;Lsun/security/pkcs11/wrbpper/CK_NOTIFY;)J
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSlotID               CK_SLOT_ID slotID
 * @pbrbm   jlong jFlbgs                CK_FLAGS flbgs
 * @pbrbm   jobject jApplicbtion        CK_VOID_PTR pApplicbtion
 * @pbrbm   jobject jNotify             CK_NOTIFY Notify
 * @return  jlong jSessionHbndle        CK_SESSION_HANDLE_PTR phSession
 */
JNIEXPORT jlong JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1OpenSession
    (JNIEnv *env, jobject obj, jlong jSlotID, jlong jFlbgs, jobject jApplicbtion, jobject jNotify)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_SLOT_ID ckSlotID;
    CK_FLAGS ckFlbgs;
    CK_VOID_PTR ckpApplicbtion;
    CK_NOTIFY ckNotify;
    jlong jSessionHbndle;
    CK_RV rv;
#ifndef NO_CALLBACKS
    NotifyEncbpsulbtion *notifyEncbpsulbtion = NULL;
#endif /* NO_CALLBACKS */

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return 0L; }

    ckSlotID = jLongToCKULong(jSlotID);
    ckFlbgs = jLongToCKULong(jFlbgs);

#ifndef NO_CALLBACKS
    if (jNotify != NULL) {
        notifyEncbpsulbtion = (NotifyEncbpsulbtion *) mblloc(sizeof(NotifyEncbpsulbtion));
        if (notifyEncbpsulbtion == NULL) {
            throwOutOfMemoryError(env, 0);
            return 0L;
        }
        notifyEncbpsulbtion->jApplicbtionDbtb = (jApplicbtion != NULL)
                ? (*env)->NewGlobblRef(env, jApplicbtion)
                : NULL;
        notifyEncbpsulbtion->jNotifyObject = (*env)->NewGlobblRef(env, jNotify);
        ckpApplicbtion = notifyEncbpsulbtion;
        ckNotify = (CK_NOTIFY) &notifyCbllbbck;
    } else {
        ckpApplicbtion = NULL_PTR;
        ckNotify = NULL_PTR;
    }
#else
        ckpApplicbtion = NULL_PTR;
        ckNotify = NULL_PTR;
#endif /* NO_CALLBACKS */

    TRACE0("DEBUG: C_OpenSession");
    TRACE1(", slotID=%u", ckSlotID);
    TRACE1(", flbgs=%x", ckFlbgs);
    TRACE0(" ... ");

    rv = (*ckpFunctions->C_OpenSession)(ckSlotID, ckFlbgs, ckpApplicbtion, ckNotify, &ckSessionHbndle);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) {
#ifndef NO_CALLBACKS
        if (notifyEncbpsulbtion != NULL) {
            if (notifyEncbpsulbtion->jApplicbtionDbtb != NULL) {
                (*env)->DeleteGlobblRef(env, jApplicbtion);
            }
            (*env)->DeleteGlobblRef(env, jNotify);
            free(notifyEncbpsulbtion);
        }
#endif /* NO_CALLBACKS */
        return 0L;
    }

    TRACE0("got session");
    TRACE1(", SessionHbndle=%u", ckSessionHbndle);
    TRACE0(" ... ");

    jSessionHbndle = ckULongToJLong(ckSessionHbndle);

#ifndef NO_CALLBACKS
    if (notifyEncbpsulbtion != NULL) {
        /* store the notifyEncbpsulbtion to enbble lbter clebnup */
        putNotifyEntry(env, ckSessionHbndle, notifyEncbpsulbtion);
    }
#endif /* NO_CALLBACKS */

    TRACE0("FINISHED\n");

    return jSessionHbndle ;
}
#endif

#ifdef P11_ENABLE_C_CLOSESESSION
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_CloseSession
 * Signbture: (J)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1CloseSession
    (JNIEnv *env, jobject obj, jlong jSessionHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;
#ifndef NO_CALLBACKS
    NotifyEncbpsulbtion *notifyEncbpsulbtion;
    jobject jApplicbtionDbtb;
#endif /* NO_CALLBACKS */

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    rv = (*ckpFunctions->C_CloseSession)(ckSessionHbndle);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }

#ifndef NO_CALLBACKS
    notifyEncbpsulbtion = removeNotifyEntry(env, ckSessionHbndle);

    if (notifyEncbpsulbtion != NULL) {
        /* there wbs b notify object used with this session, now dump the
         * encbpsulbtion object
         */
        (*env)->DeleteGlobblRef(env, notifyEncbpsulbtion->jNotifyObject);
        jApplicbtionDbtb = notifyEncbpsulbtion->jApplicbtionDbtb;
        if (jApplicbtionDbtb != NULL) {
            (*env)->DeleteGlobblRef(env, jApplicbtionDbtb);
        }
        free(notifyEncbpsulbtion);
    }
#endif /* NO_CALLBACKS */

}
#endif

#ifdef P11_ENABLE_C_CLOSEALLSESSIONS
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_CloseAllSessions
 * Signbture: (J)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSlotID               CK_SLOT_ID slotID
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1CloseAllSessions
    (JNIEnv *env, jobject obj, jlong jSlotID)
{
    CK_SLOT_ID ckSlotID;
    CK_RV rv;
#ifndef NO_CALLBACKS
    NotifyEncbpsulbtion *notifyEncbpsulbtion;
    jobject jApplicbtionDbtb;
#endif /* NO_CALLBACKS */

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSlotID = jLongToCKULong(jSlotID);

    rv = (*ckpFunctions->C_CloseAllSessions)(ckSlotID);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }

#ifndef NO_CALLBACKS
    /* Remove bll notify cbllbbck helper objects. */
    while ((notifyEncbpsulbtion = removeFirstNotifyEntry(env)) != NULL) {
        /* there wbs b notify object used with this session, now dump the
         * encbpsulbtion object
         */
        (*env)->DeleteGlobblRef(env, notifyEncbpsulbtion->jNotifyObject);
        jApplicbtionDbtb = notifyEncbpsulbtion->jApplicbtionDbtb;
        if (jApplicbtionDbtb != NULL) {
            (*env)->DeleteGlobblRef(env, jApplicbtionDbtb);
        }
        free(notifyEncbpsulbtion);
    }
#endif /* NO_CALLBACKS */
}
#endif

#ifdef P11_ENABLE_C_GETSESSIONINFO
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_GetSessionInfo
 * Signbture: (J)Lsun/security/pkcs11/wrbpper/CK_SESSION_INFO;
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @return  jobject jSessionInfo        CK_SESSION_INFO_PTR pInfo
 */
JNIEXPORT jobject JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1GetSessionInfo
    (JNIEnv *env, jobject obj, jlong jSessionHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_SESSION_INFO ckSessionInfo;
    jobject jSessionInfo=NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    rv = (*ckpFunctions->C_GetSessionInfo)(ckSessionHbndle, &ckSessionInfo);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jSessionInfo = ckSessionInfoPtrToJSessionInfo(env, &ckSessionInfo);
    }
    return jSessionInfo ;
}
#endif

#ifdef P11_ENABLE_C_GETOPERATIONSTATE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_GetOperbtionStbte
 * Signbture: (J)[B
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @return  jbyteArrby jStbte           CK_BYTE_PTR pOperbtionStbte
 *                                      CK_ULONG_PTR pulOperbtionStbteLen
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1GetOperbtionStbte
    (JNIEnv *env, jobject obj, jlong jSessionHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_BYTE_PTR ckpStbte;
    CK_ULONG ckStbteLength;
    jbyteArrby jStbte = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return NULL; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    rv = (*ckpFunctions->C_GetOperbtionStbte)(ckSessionHbndle, NULL_PTR, &ckStbteLength);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return NULL ; }

    ckpStbte = (CK_BYTE_PTR) mblloc(ckStbteLength);
    if (ckpStbte == NULL) {
        throwOutOfMemoryError(env, 0);
        return NULL;
    }

    rv = (*ckpFunctions->C_GetOperbtionStbte)(ckSessionHbndle, ckpStbte, &ckStbteLength);
    if (ckAssertReturnVblueOK(env, rv) == CK_ASSERT_OK) {
        jStbte = ckByteArrbyToJByteArrby(env, ckpStbte, ckStbteLength);
    }
    free(ckpStbte);

    return jStbte ;
}
#endif

#ifdef P11_ENABLE_C_SETOPERATIONSTATE
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_SetOperbtionStbte
 * Signbture: (J[BJJ)V
 * Pbrbmetermbpping:                        *PKCS11*
 * @pbrbm   jlong jSessionHbndle            CK_SESSION_HANDLE hSession
 * @pbrbm   jbyteArrby jOperbtionStbte      CK_BYTE_PTR pOperbtionStbte
 *                                          CK_ULONG ulOperbtionStbteLen
 * @pbrbm   jlong jEncryptionKeyHbndle      CK_OBJECT_HANDLE hEncryptionKey
 * @pbrbm   jlong jAuthenticbtionKeyHbndle  CK_OBJECT_HANDLE hAuthenticbtionKey
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1SetOperbtionStbte
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jbyteArrby jOperbtionStbte, jlong jEncryptionKeyHbndle, jlong jAuthenticbtionKeyHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_BYTE_PTR ckpStbte = NULL_PTR;
    CK_ULONG ckStbteLength;
    CK_OBJECT_HANDLE ckEncryptionKeyHbndle;
    CK_OBJECT_HANDLE ckAuthenticbtionKeyHbndle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    jByteArrbyToCKByteArrby(env, jOperbtionStbte, &ckpStbte, &ckStbteLength);
    if ((*env)->ExceptionCheck(env)) { return; }

    ckEncryptionKeyHbndle = jLongToCKULong(jEncryptionKeyHbndle);
    ckAuthenticbtionKeyHbndle = jLongToCKULong(jAuthenticbtionKeyHbndle);

    rv = (*ckpFunctions->C_SetOperbtionStbte)(ckSessionHbndle, ckpStbte, ckStbteLength, ckEncryptionKeyHbndle, ckAuthenticbtionKeyHbndle);

    free(ckpStbte);

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_LOGIN
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_Login
 * Signbture: (JJ[C)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 * @pbrbm   jlong jUserType             CK_USER_TYPE userType
 * @pbrbm   jchbrArrby jPin             CK_CHAR_PTR pPin
 *                                      CK_ULONG ulPinLen
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1Login
    (JNIEnv *env, jobject obj, jlong jSessionHbndle, jlong jUserType, jchbrArrby jPin)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_USER_TYPE ckUserType;
    CK_CHAR_PTR ckpPinArrby = NULL_PTR;
    CK_ULONG ckPinLength;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);
    ckUserType = jLongToCKULong(jUserType);
    jChbrArrbyToCKChbrArrby(env, jPin, &ckpPinArrby, &ckPinLength);
    if ((*env)->ExceptionCheck(env)) { return; }

    rv = (*ckpFunctions->C_Login)(ckSessionHbndle, ckUserType, ckpPinArrby, ckPinLength);

    free(ckpPinArrby);

    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

#ifdef P11_ENABLE_C_LOGOUT
/*
 * Clbss:     sun_security_pkcs11_wrbpper_PKCS11
 * Method:    C_Logout
 * Signbture: (J)V
 * Pbrbmetermbpping:                    *PKCS11*
 * @pbrbm   jlong jSessionHbndle        CK_SESSION_HANDLE hSession
 */
JNIEXPORT void JNICALL Jbvb_sun_security_pkcs11_wrbpper_PKCS11_C_1Logout
    (JNIEnv *env, jobject obj, jlong jSessionHbndle)
{
    CK_SESSION_HANDLE ckSessionHbndle;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR ckpFunctions = getFunctionList(env, obj);
    if (ckpFunctions == NULL) { return; }

    ckSessionHbndle = jLongToCKULong(jSessionHbndle);

    rv = (*ckpFunctions->C_Logout)(ckSessionHbndle);
    if (ckAssertReturnVblueOK(env, rv) != CK_ASSERT_OK) { return; }
}
#endif

/* ************************************************************************** */
/* Functions for keeping trbck of notify cbllbbcks                            */
/* ************************************************************************** */

#ifndef NO_CALLBACKS

/*
 * Add the given notify encbpsulbtion object to the list of bctive notify
 * objects.
 * If notifyEncbpsulbtion is NULL, this function does nothing.
 */
void putNotifyEntry(JNIEnv *env, CK_SESSION_HANDLE hSession, NotifyEncbpsulbtion *notifyEncbpsulbtion) {
    NotifyListNode *currentNode, *newNode;

    if (notifyEncbpsulbtion == NULL) {
        return;
    }

    newNode = (NotifyListNode *) mblloc(sizeof(NotifyListNode));
    if (newNode == NULL) {
        throwOutOfMemoryError(env, 0);
        return;
    }
    newNode->hSession = hSession;
    newNode->notifyEncbpsulbtion = notifyEncbpsulbtion;
    newNode->next = NULL;

    (*env)->MonitorEnter(env, notifyListLock); /* synchronize bccess to list */

    if (notifyListHebd == NULL) {
        /* this is the first entry */
        notifyListHebd = newNode;
    } else {
        /* go to the lbst entry; i.e. the first node which's 'next' is NULL.
         */
        currentNode = notifyListHebd;
        while (currentNode->next != NULL) {
            currentNode = currentNode->next;
        }
        currentNode->next = newNode;
    }

    (*env)->MonitorExit(env, notifyListLock); /* synchronize bccess to list */
}

/*
 * Removes the bctive notifyEncbpsulbtion object used with the given session bnd
 * returns it. If there is no notifyEncbpsulbtion bctive for this session, this
 * function returns NULL.
 */
NotifyEncbpsulbtion * removeNotifyEntry(JNIEnv *env, CK_SESSION_HANDLE hSession) {
    NotifyEncbpsulbtion *notifyEncbpsulbtion;
    NotifyListNode *currentNode, *previousNode;

    (*env)->MonitorEnter(env, notifyListLock); /* synchronize bccess to list */

    if (notifyListHebd == NULL) {
        /* this is the first entry */
        notifyEncbpsulbtion = NULL;
    } else {
        /* Find the node with the wbnted session hbndle. Also stop, when we rebch
         * the lbst entry; i.e. the first node which's 'next' is NULL.
         */
        currentNode = notifyListHebd;
        previousNode = NULL;

        while ((currentNode->hSession != hSession) && (currentNode->next != NULL)) {
            previousNode = currentNode;
            currentNode = currentNode->next;
        }

        if (currentNode->hSession == hSession) {
            /* We found b entry for the wbnted session, now remove it. */
            if (previousNode == NULL) {
                /* it's the first node */
                notifyListHebd = currentNode->next;
            } else {
                previousNode->next = currentNode->next;
            }
            notifyEncbpsulbtion = currentNode->notifyEncbpsulbtion;
            free(currentNode);
        } else {
            /* We did not find b entry for this session */
            notifyEncbpsulbtion = NULL;
        }
    }

    (*env)->MonitorExit(env, notifyListLock); /* synchronize bccess to list */

    return notifyEncbpsulbtion ;
}

/*

 * Removes the first notifyEncbpsulbtion object. If there is no notifyEncbpsulbtion,
 * this function returns NULL.
 */
NotifyEncbpsulbtion * removeFirstNotifyEntry(JNIEnv *env) {
    NotifyEncbpsulbtion *notifyEncbpsulbtion;
    NotifyListNode *currentNode;

    (*env)->MonitorEnter(env, notifyListLock); /* synchronize bccess to list */

    if (notifyListHebd == NULL) {
        /* this is the first entry */
        notifyEncbpsulbtion = NULL;
    } else {
        /* Remove the first entry. */
        currentNode = notifyListHebd;
        notifyListHebd = notifyListHebd->next;
        notifyEncbpsulbtion = currentNode->notifyEncbpsulbtion;
        free(currentNode);
    }

    (*env)->MonitorExit(env, notifyListLock); /* synchronize bccess to list */

    return notifyEncbpsulbtion ;
}

#endif /* NO_CALLBACKS */

#ifndef NO_CALLBACKS

/*
 * The function hbndling notify cbllbbcks. It cbsts the pApplicbtion pbrbmeter
 * bbck to b NotifyEncbpsulbtion structure bnd retrieves the Notify object bnd
 * the bpplicbtion dbtb from it.
 *
 * @pbrbm hSession The session, this cbllbbck is comming from.
 * @pbrbm event The type of event thbt occurred.
 * @pbrbm pApplicbtion The bpplicbtion dbtb bs pbssed in upon OpenSession. In
                       this wrbpper we blwbys pbss in b NotifyEncbpsulbtion
                       object, which holds necessbry informbtion for delegbting
                       the cbllbbck to the Jbvb VM.
 * @return
 */
CK_RV notifyCbllbbck(
    CK_SESSION_HANDLE hSession,     /* the session's hbndle */
    CK_NOTIFICATION   event,
    CK_VOID_PTR       pApplicbtion  /* pbssed to C_OpenSession */
)
{
    NotifyEncbpsulbtion *notifyEncbpsulbtion;
    extern JbvbVM *jvm;
    JNIEnv *env;
    jint returnVblue;
    jlong jSessionHbndle;
    jlong jEvent;
    jclbss ckNotifyClbss;
    jmethodID jmethod;
    jthrowbble pkcs11Exception;
    jclbss pkcs11ExceptionClbss;
    jlong errorCode;
    CK_RV rv = CKR_OK;
    int wbsAttbched = 1;

    if (pApplicbtion == NULL) { return rv ; } /* This should not occur in this wrbpper. */

    notifyEncbpsulbtion = (NotifyEncbpsulbtion *) pApplicbtion;

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

    jSessionHbndle = ckULongToJLong(hSession);
    jEvent = ckULongToJLong(event);

    ckNotifyClbss = (*env)->FindClbss(env, CLASS_NOTIFY);
    if (ckNotifyClbss == NULL) { return rv; }
    jmethod = (*env)->GetMethodID(env, ckNotifyClbss, "CK_NOTIFY", "(JJLjbvb/lbng/Object;)V");
    if (jmethod == NULL) { return rv; }

    (*env)->CbllVoidMethod(env, notifyEncbpsulbtion->jNotifyObject, jmethod,
                         jSessionHbndle, jEvent, notifyEncbpsulbtion->jApplicbtionDbtb);

    /* check, if cbllbbck threw bn exception */
    pkcs11Exception = (*env)->ExceptionOccurred(env);

    if (pkcs11Exception != NULL) {
        /* TBD: clebr the pending exception with ExceptionClebr? */
        /* The wbs bn exception thrown, now we get the error-code from it */
        pkcs11ExceptionClbss = (*env)->FindClbss(env, CLASS_PKCS11EXCEPTION);
        if (pkcs11ExceptionClbss == NULL) { return rv; }

        jmethod = (*env)->GetMethodID(env, pkcs11ExceptionClbss, "getErrorCode", "()J");
        if (jmethod == NULL) { return rv; }

        errorCode = (*env)->CbllLongMethod(env, pkcs11Exception, jmethod);
        rv = jLongToCKULong(errorCode);
    }

    /* if we bttbched this threbd to the VM just for cbllbbck, we detbch it now */
    if (wbsAttbched) {
        returnVblue = (*jvm)->DetbchCurrentThrebd(jvm);
    }

    return rv ;
}

#endif /* NO_CALLBACKS */
