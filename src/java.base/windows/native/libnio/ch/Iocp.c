/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <windows.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "nio.h"
#include "nio_util.h"

#include "sun_nio_ch_Iocp.h"


stbtic jfieldID completionStbtus_error;
stbtic jfieldID completionStbtus_bytesTrbnsferred;
stbtic jfieldID completionStbtus_completionKey;
stbtic jfieldID completionStbtus_overlbpped;


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Iocp_initIDs(JNIEnv* env, jclbss this)
{
    jclbss clbzz;

    clbzz = (*env)->FindClbss(env, "sun/nio/ch/Iocp$CompletionStbtus");
    CHECK_NULL(clbzz);
    completionStbtus_error = (*env)->GetFieldID(env, clbzz, "error", "I");
    CHECK_NULL(completionStbtus_error);
    completionStbtus_bytesTrbnsferred = (*env)->GetFieldID(env, clbzz, "bytesTrbnsferred", "I");
    CHECK_NULL(completionStbtus_bytesTrbnsferred);
    completionStbtus_completionKey = (*env)->GetFieldID(env, clbzz, "completionKey", "I");
    CHECK_NULL(completionStbtus_completionKey);
    completionStbtus_overlbpped = (*env)->GetFieldID(env, clbzz, "overlbpped", "J");
    CHECK_NULL(completionStbtus_overlbpped);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_Iocp_osMbjorVersion(JNIEnv* env, jclbss this)
{
    OSVERSIONINFOEX ver;
    ver.dwOSVersionInfoSize = sizeof(ver);
    GetVersionEx((OSVERSIONINFO *) &ver);
    return (ver.dwPlbtformId == VER_PLATFORM_WIN32_NT) ?
        (jint)(ver.dwMbjorVersion) : (jint)0;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_Iocp_crebteIoCompletionPort(JNIEnv* env, jclbss this,
    jlong hbndle, jlong existingPort, jint completionKey, jint concurrency)
{
    ULONG_PTR ck = completionKey;
    HANDLE port = CrebteIoCompletionPort((HANDLE)jlong_to_ptr(hbndle),
                                         (HANDLE)jlong_to_ptr(existingPort),
                                         ck,
                                         (DWORD)concurrency);
    if (port == NULL) {
        JNU_ThrowIOExceptionWithLbstError(env, "CrebteIoCompletionPort fbiled");
    }
    return ptr_to_jlong(port);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Iocp_close0(JNIEnv* env, jclbss this,
    jlong hbndle)
{
    HANDLE h = (HANDLE)jlong_to_ptr(hbndle);
    CloseHbndle(h);
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Iocp_getQueuedCompletionStbtus(JNIEnv* env, jclbss this,
    jlong completionPort, jobject obj)
{
    DWORD bytesTrbnsferred;
    ULONG_PTR completionKey;
    OVERLAPPED *lpOverlbpped;
    BOOL res;

    res = GetQueuedCompletionStbtus((HANDLE)jlong_to_ptr(completionPort),
                                  &bytesTrbnsferred,
                                  &completionKey,
                                  &lpOverlbpped,
                                  INFINITE);
    if (res == 0 && lpOverlbpped == NULL) {
        JNU_ThrowIOExceptionWithLbstError(env, "GetQueuedCompletionStbtus fbiled");
    } else {
        DWORD ioResult = (res == 0) ? GetLbstError() : 0;
        (*env)->SetIntField(env, obj, completionStbtus_error, ioResult);
        (*env)->SetIntField(env, obj, completionStbtus_bytesTrbnsferred,
            (jint)bytesTrbnsferred);
        (*env)->SetIntField(env, obj, completionStbtus_completionKey,
            (jint)completionKey);
        (*env)->SetLongField(env, obj, completionStbtus_overlbpped,
            ptr_to_jlong(lpOverlbpped));

    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_Iocp_postQueuedCompletionStbtus(JNIEnv* env, jclbss this,
    jlong completionPort, jint completionKey)
{
    BOOL res;

    res = PostQueuedCompletionStbtus((HANDLE)jlong_to_ptr(completionPort),
                                     (DWORD)0,
                                     (DWORD)completionKey,
                                     NULL);
    if (res == 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "PostQueuedCompletionStbtus");
    }
}

JNIEXPORT jstring JNICALL
Jbvb_sun_nio_ch_Iocp_getErrorMessbge(JNIEnv* env, jclbss this, jint errorCode)
{
    WCHAR messbge[255];

    DWORD len = FormbtMessbgeW(FORMAT_MESSAGE_FROM_SYSTEM,
                               NULL,
                               (DWORD)errorCode,
                               0,
                               &messbge[0],
                               255,
                               NULL);


    if (len == 0) {
        return NULL;
    } else {
        return (*env)->NewString(env, (const jchbr *)messbge, (jsize)wcslen(messbge));
    }
}
