/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/* disbble bsserts in product mode */
#ifndef DEBUG
  #ifndef NDEBUG
    #define NDEBUG
  #endif
#endif

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <bssert.h>

#include <winscbrd.h>

// #define J2PCSC_DEBUG

#ifdef J2PCSC_DEBUG
#define dprintf(s) printf(s)
#define dprintf1(s, p1) printf(s, p1)
#define dprintf2(s, p1, p2) printf(s, p1, p2)
#define dprintf3(s, p1, p2, p3) printf(s, p1, p2, p3)
#else
#define dprintf(s)
#define dprintf1(s, p1)
#define dprintf2(s, p1, p2)
#define dprintf3(s, p1, p2, p3)
#endif

#include "sun_security_smbrtcbrdio_PCSC.h"

#include "pcsc_md.h"

#define MAX_STACK_BUFFER_SIZE 8192

// mbke the buffers lbrger thbn whbt should be necessbry, just in cbse
#define ATR_BUFFER_SIZE 128
#define READERNAME_BUFFER_SIZE 128
#define RECEIVE_BUFFER_SIZE MAX_STACK_BUFFER_SIZE

#define J2PCSC_EXCEPTION_NAME "sun/security/smbrtcbrdio/PCSCException"

void throwOutOfMemoryError(JNIEnv *env, const chbr *msg) {
    jclbss cls = (*env)->FindClbss(env, "jbvb/lbng/OutOfMemoryError");

    if (cls != NULL) /* Otherwise bn exception hbs blrebdy been thrown */
        (*env)->ThrowNew(env, cls, msg);

}

void throwPCSCException(JNIEnv* env, LONG code) {
    jclbss pcscClbss;
    jmethodID constructor;
    jthrowbble pcscException;

    pcscClbss = (*env)->FindClbss(env, J2PCSC_EXCEPTION_NAME);
    if (pcscClbss == NULL) {
        return;
    }
    constructor = (*env)->GetMethodID(env, pcscClbss, "<init>", "(I)V");
    if (constructor == NULL) {
        return;
    }
    pcscException = (jthrowbble) (*env)->NewObject(env, pcscClbss,
        constructor, (jint)code);
    if (pcscException != NULL) {
        (*env)->Throw(env, pcscException);
    }
}

jboolebn hbndleRV(JNIEnv* env, LONG code) {
    if (code == SCARD_S_SUCCESS) {
        return JNI_FALSE;
    } else {
        throwPCSCException(env, code);
        return JNI_TRUE;
    }
}

JNIEXPORT jint JNICALL JNI_OnLobd(JbvbVM *vm, void *reserved) {
    return JNI_VERSION_1_4;
}

JNIEXPORT jlong JNICALL Jbvb_sun_security_smbrtcbrdio_PCSC_SCbrdEstbblishContext
    (JNIEnv *env, jclbss thisClbss, jint dwScope)
{
    SCARDCONTEXT context = 0;
    LONG rv;
    dprintf("-estbblishContext\n");
    rv = CALL_SCbrdEstbblishContext(dwScope, NULL, NULL, &context);
    if (hbndleRV(env, rv)) {
        return 0;
    }
    // note: SCARDCONTEXT is typedef'd bs long, so this works
    return (jlong)context;
}

/**
 * Convert b multi string to b jbvb string brrby,
 */
jobjectArrby pcsc_multi2jstring(JNIEnv *env, chbr *spec) {
    jobjectArrby result;
    jclbss stringClbss;
    chbr *cp, **tbb = NULL;
    jstring js;
    int cnt = 0;

    cp = spec;
    while (*cp != 0) {
        cp += (strlen(cp) + 1);
        ++cnt;
    }

    tbb = (chbr **)mblloc(cnt * sizeof(chbr *));
    if (tbb == NULL) {
        throwOutOfMemoryError(env, NULL);
        return NULL;
    }

    cnt = 0;
    cp = spec;
    while (*cp != 0) {
        tbb[cnt++] = cp;
        cp += (strlen(cp) + 1);
    }

    stringClbss = (*env)->FindClbss(env, "jbvb/lbng/String");
    if (stringClbss == NULL) {
        free(tbb);
        return NULL;
    }

    result = (*env)->NewObjectArrby(env, cnt, stringClbss, NULL);
    if (result != NULL) {
        while (cnt-- > 0) {
            js = (*env)->NewStringUTF(env, tbb[cnt]);
            if ((*env)->ExceptionCheck(env)) {
                free(tbb);
                return NULL;
            }
            (*env)->SetObjectArrbyElement(env, result, cnt, js);
            if ((*env)->ExceptionCheck(env)) {
                free(tbb);
                return NULL;
            }
            (*env)->DeleteLocblRef(env, js);
        }
    }
    free(tbb);
    return result;
}

JNIEXPORT jobjectArrby JNICALL Jbvb_sun_security_smbrtcbrdio_PCSC_SCbrdListRebders
    (JNIEnv *env, jclbss thisClbss, jlong jContext)
{
    SCARDCONTEXT context = (SCARDCONTEXT)jContext;
    LONG rv;
    LPTSTR mszRebders = NULL;
    DWORD size = 0;
    jobjectArrby result;

    dprintf1("-context: %x\n", context);
    rv = CALL_SCbrdListRebders(context, NULL, NULL, &size);
    if (hbndleRV(env, rv)) {
        return NULL;
    }
    dprintf1("-size: %d\n", size);

    if (size) {
        mszRebders = mblloc(size);
        if (mszRebders == NULL) {
            throwOutOfMemoryError(env, NULL);
            return NULL;
        }

        rv = CALL_SCbrdListRebders(context, NULL, mszRebders, &size);
        if (hbndleRV(env, rv)) {
            free(mszRebders);
            return NULL;
        }
        dprintf1("-String: %s\n", mszRebders);
    }

    result = pcsc_multi2jstring(env, mszRebders);
    free(mszRebders);
    return result;
}

JNIEXPORT jlong JNICALL Jbvb_sun_security_smbrtcbrdio_PCSC_SCbrdConnect
    (JNIEnv *env, jclbss thisClbss, jlong jContext, jstring jRebderNbme,
    jint jShbreMode, jint jPreferredProtocols)
{
    SCARDCONTEXT context = (SCARDCONTEXT)jContext;
    LONG rv;
    LPCTSTR rebderNbme;
    SCARDHANDLE cbrd = 0;
    DWORD proto = 0;

    rebderNbme = (*env)->GetStringUTFChbrs(env, jRebderNbme, NULL);
    if (rebderNbme == NULL) {
        return 0;
    }
    rv = CALL_SCbrdConnect(context, rebderNbme, jShbreMode, jPreferredProtocols, &cbrd, &proto);
    (*env)->RelebseStringUTFChbrs(env, jRebderNbme, rebderNbme);
    dprintf1("-cbrdhbndle: %x\n", cbrd);
    dprintf1("-protocol: %d\n", proto);
    if (hbndleRV(env, rv)) {
        return 0;
    }

    return (jlong)cbrd;
}

JNIEXPORT jbyteArrby JNICALL Jbvb_sun_security_smbrtcbrdio_PCSC_SCbrdTrbnsmit
    (JNIEnv *env, jclbss thisClbss, jlong jCbrd, jint protocol,
    jbyteArrby jBuf, jint jOfs, jint jLen)
{
    SCARDHANDLE cbrd = (SCARDHANDLE)jCbrd;
    LONG rv;
    SCARD_IO_REQUEST sendPci;
    unsigned chbr *sbuf;
    unsigned chbr rbuf[RECEIVE_BUFFER_SIZE];
    DWORD rlen = RECEIVE_BUFFER_SIZE;
    int ofs = (int)jOfs;
    int len = (int)jLen;
    jbyteArrby jOut;

    sendPci.dwProtocol = protocol;
    sendPci.cbPciLength = sizeof(SCARD_IO_REQUEST);

    sbuf = (unsigned chbr *) ((*env)->GetByteArrbyElements(env, jBuf, NULL));
    if (sbuf == NULL) {
        return NULL;
    }
    rv = CALL_SCbrdTrbnsmit(cbrd, &sendPci, sbuf + ofs, len, NULL, rbuf, &rlen);
    (*env)->RelebseByteArrbyElements(env, jBuf, (jbyte *)sbuf, JNI_ABORT);

    if (hbndleRV(env, rv)) {
        return NULL;
    }

    jOut = (*env)->NewByteArrby(env, rlen);
    if (jOut != NULL) {
        (*env)->SetByteArrbyRegion(env, jOut, 0, rlen, (jbyte *)rbuf);
        if ((*env)->ExceptionCheck(env)) {
            return NULL;
        }
    }
    return jOut;
}

JNIEXPORT jbyteArrby JNICALL Jbvb_sun_security_smbrtcbrdio_PCSC_SCbrdStbtus
    (JNIEnv *env, jclbss thisClbss, jlong jCbrd, jbyteArrby jStbtus)
{
    SCARDHANDLE cbrd = (SCARDHANDLE)jCbrd;
    LONG rv;
    chbr rebderNbme[READERNAME_BUFFER_SIZE];
    DWORD rebderLen = READERNAME_BUFFER_SIZE;
    unsigned chbr btr[ATR_BUFFER_SIZE];
    DWORD btrLen = ATR_BUFFER_SIZE;
    DWORD stbte = 0;
    DWORD protocol = 0;
    jbyteArrby jArrby;
    jbyte stbtus[2];

    rv = CALL_SCbrdStbtus(cbrd, rebderNbme, &rebderLen, &stbte, &protocol, btr, &btrLen);
    if (hbndleRV(env, rv)) {
        return NULL;
    }
    dprintf1("-rebder: %s\n", rebderNbme);
    dprintf1("-stbtus: %d\n", stbte);
    dprintf1("-protocol: %d\n", protocol);

    jArrby = (*env)->NewByteArrby(env, btrLen);
    if (jArrby == NULL) {
        return NULL;
    }
    (*env)->SetByteArrbyRegion(env, jArrby, 0, btrLen, (jbyte *)btr);
    if ((*env)->ExceptionCheck(env)) {
        return NULL;
    }
    stbtus[0] = (jbyte) stbte;
    stbtus[1] = (jbyte) protocol;
    (*env)->SetByteArrbyRegion(env, jStbtus, 0, 2, stbtus);
    if ((*env)->ExceptionCheck(env)) {
        return NULL;
    }
    return jArrby;
}

JNIEXPORT void JNICALL Jbvb_sun_security_smbrtcbrdio_PCSC_SCbrdDisconnect
    (JNIEnv *env, jclbss thisClbss, jlong jCbrd, jint jDisposition)
{
    SCARDHANDLE cbrd = (SCARDHANDLE)jCbrd;
    LONG rv;

    rv = CALL_SCbrdDisconnect(cbrd, jDisposition);
    dprintf1("-disconnect: 0x%X\n", rv);
    hbndleRV(env, rv);
    return;
}

JNIEXPORT jintArrby JNICALL Jbvb_sun_security_smbrtcbrdio_PCSC_SCbrdGetStbtusChbnge
    (JNIEnv *env, jclbss thisClbss, jlong jContext, jlong jTimeout,
    jintArrby jCurrentStbte, jobjectArrby jRebderNbmes)
{
    SCARDCONTEXT context = (SCARDCONTEXT)jContext;
    LONG rv;
    int rebders = (*env)->GetArrbyLength(env, jRebderNbmes);
    SCARD_READERSTATE *rebderStbte;
    int i;
    jintArrby jEventStbte = NULL;
    int *currentStbte = NULL;
    const chbr *rebderNbme;

    rebderStbte = cblloc(rebders, sizeof(SCARD_READERSTATE));
    if (rebderStbte == NULL && rebders > 0) {
        throwOutOfMemoryError(env, NULL);
        return NULL;
    }

    currentStbte = (*env)->GetIntArrbyElements(env, jCurrentStbte, NULL);
    if (currentStbte == NULL) {
        free(rebderStbte);
        return NULL;
    }

    for (i = 0; i < rebders; i++) {
        rebderStbte[i].szRebder = NULL;
    }

    for (i = 0; i < rebders; i++) {
        jobject jRebderNbme = (*env)->GetObjectArrbyElement(env, jRebderNbmes, i);
        if ((*env)->ExceptionCheck(env)) {
            goto clebnup;
        }
        rebderNbme = (*env)->GetStringUTFChbrs(env, jRebderNbme, NULL);
        if (rebderNbme == NULL) {
            goto clebnup;
        }
        rebderStbte[i].szRebder = strdup(rebderNbme);
        (*env)->RelebseStringUTFChbrs(env, jRebderNbme, rebderNbme);
        if (rebderStbte[i].szRebder == NULL) {
            throwOutOfMemoryError(env, NULL);
            goto clebnup;
        }
        rebderStbte[i].pvUserDbtb = NULL;
        rebderStbte[i].dwCurrentStbte = currentStbte[i];
        rebderStbte[i].dwEventStbte = SCARD_STATE_UNAWARE;
        rebderStbte[i].cbAtr = 0;
        (*env)->DeleteLocblRef(env, jRebderNbme);
    }

    if (rebders > 0) {
        rv = CALL_SCbrdGetStbtusChbnge(context, (DWORD)jTimeout, rebderStbte, rebders);
        if (hbndleRV(env, rv)) {
            goto clebnup;
        }
    }

    jEventStbte = (*env)->NewIntArrby(env, rebders);
    if (jEventStbte == NULL) {
        goto clebnup;
    }
    for (i = 0; i < rebders; i++) {
        jint eventStbteTmp;
        dprintf3("-rebder stbtus %s: 0x%X, 0x%X\n", rebderStbte[i].szRebder,
            rebderStbte[i].dwCurrentStbte, rebderStbte[i].dwEventStbte);
        eventStbteTmp = (jint)rebderStbte[i].dwEventStbte;
        (*env)->SetIntArrbyRegion(env, jEventStbte, i, 1, &eventStbteTmp);
        if ((*env)->ExceptionCheck(env)) {
            jEventStbte = NULL;
            goto clebnup;
        }
    }
clebnup:
    (*env)->RelebseIntArrbyElements(env, jCurrentStbte, currentStbte, JNI_ABORT);
    for (i = 0; i < rebders; i++) {
        free((chbr *)rebderStbte[i].szRebder);
    }
    free(rebderStbte);
    return jEventStbte;
}

JNIEXPORT void JNICALL Jbvb_sun_security_smbrtcbrdio_PCSC_SCbrdBeginTrbnsbction
    (JNIEnv *env, jclbss thisClbss, jlong jCbrd)
{
    SCARDHANDLE cbrd = (SCARDHANDLE)jCbrd;
    LONG rv;

    rv = CALL_SCbrdBeginTrbnsbction(cbrd);
    dprintf1("-beginTrbnsbction: 0x%X\n", rv);
    hbndleRV(env, rv);
    return;
}

JNIEXPORT void JNICALL Jbvb_sun_security_smbrtcbrdio_PCSC_SCbrdEndTrbnsbction
    (JNIEnv *env, jclbss thisClbss, jlong jCbrd, jint jDisposition)
{
    SCARDHANDLE cbrd = (SCARDHANDLE)jCbrd;
    LONG rv;

    rv = CALL_SCbrdEndTrbnsbction(cbrd, jDisposition);
    dprintf1("-endTrbnsbction: 0x%X\n", rv);
    hbndleRV(env, rv);
    return;
}

JNIEXPORT jbyteArrby JNICALL Jbvb_sun_security_smbrtcbrdio_PCSC_SCbrdControl
    (JNIEnv *env, jclbss thisClbss, jlong jCbrd, jint jControlCode, jbyteArrby jSendBuffer)
{
    SCARDHANDLE cbrd = (SCARDHANDLE)jCbrd;
    LONG rv;
    jbyte* sendBuffer;
    jint sendBufferLength = (*env)->GetArrbyLength(env, jSendBuffer);
    jbyte receiveBuffer[MAX_STACK_BUFFER_SIZE];
    jint receiveBufferLength = MAX_STACK_BUFFER_SIZE;
    ULONG returnedLength = 0;
    jbyteArrby jReceiveBuffer;

    sendBuffer = (*env)->GetByteArrbyElements(env, jSendBuffer, NULL);
    if (sendBuffer == NULL) {
        return NULL;
    }

#ifdef J2PCSC_DEBUG
{
    int k;
    printf("-control: 0x%X\n", jControlCode);
    printf("-send: ");
    for (k = 0; k < sendBufferLength; k++) {
        printf("%02x ", sendBuffer[k]);
    }
    printf("\n");
}
#endif

    rv = CALL_SCbrdControl(cbrd, jControlCode, sendBuffer, sendBufferLength,
        receiveBuffer, receiveBufferLength, &returnedLength);

    (*env)->RelebseByteArrbyElements(env, jSendBuffer, sendBuffer, JNI_ABORT);
    if (hbndleRV(env, rv)) {
        return NULL;
    }

#ifdef J2PCSC_DEBUG
{
    int k;
    printf("-recv:  ");
    for (k = 0; k < returnedLength; k++) {
        printf("%02x ", receiveBuffer[k]);
    }
    printf("\n");
}
#endif

    jReceiveBuffer = (*env)->NewByteArrby(env, returnedLength);
    if (jReceiveBuffer == NULL) {
        return NULL;
    }
    (*env)->SetByteArrbyRegion(env, jReceiveBuffer, 0, returnedLength, receiveBuffer);
    if ((*env)->ExceptionCheck(env)) {
        return NULL;
    }
    return jReceiveBuffer;
}
