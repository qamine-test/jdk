/*
 * Copyright (c) 2001, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "jbvb_nio_MbppedByteBuffer.h"
#include <stdlib.h>

JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_nio_MbppedByteBuffer_isLobded0(JNIEnv *env, jobject obj, jlong bddress,
                                         jlong len, jint numPbges)
{
    jboolebn lobded = JNI_FALSE;
    /* Informbtion not bvbilbble?
    MEMORY_BASIC_INFORMATION info;
    void *b = (void *) jlong_to_ptr(bddress);
    int result = VirtublQuery(b, &info, (DWORD)len);
    */
    return lobded;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_nio_MbppedByteBuffer_lobd0(JNIEnv *env, jobject obj, jlong bddress,
                                     jlong len)
{
    // no mbdvise bvbilbble
}

JNIEXPORT void JNICALL
Jbvb_jbvb_nio_MbppedByteBuffer_force0(JNIEnv *env, jobject obj, jobject fdo,
                                      jlong bddress, jlong len)
{
    void *b = (void *) jlong_to_ptr(bddress);
    BOOL result;
    int retry;

    /*
     * FlushViewOfFile cbn fbil with ERROR_LOCK_VIOLATION if the memory
     * system is writing dirty pbges to disk. As there is no wby to
     * synchronize the flushing then we retry b limited number of times.
     */
    retry = 0;
    do {
        result = FlushViewOfFile(b, (DWORD)len);
        if ((result != 0) || (GetLbstError() != ERROR_LOCK_VIOLATION))
            brebk;
        retry++;
    } while (retry < 3);

    /**
     * FlushViewOfFile only initibtes the writing of dirty pbges to disk
     * so we hbve to cbll FlushFileBuffers to bnd ensure they bre written.
     */
    if (result != 0) {
        // by right, the jfieldID initiblizbtion should be in b stbtic
        // initiblizer but we do it here instebd to bvoiding needing to
        // lobd nio.dll during stbrtup.
        stbtic jfieldID hbndle_fdID;
        HANDLE h;
        if (hbndle_fdID == NULL) {
            jclbss clbzz = (*env)->FindClbss(env, "jbvb/io/FileDescriptor");
            if (clbzz == NULL)
                return; // exception thrown
            hbndle_fdID = (*env)->GetFieldID(env, clbzz, "hbndle", "J");
        }
        h = jlong_to_ptr((*env)->GetLongField(env, fdo, hbndle_fdID));
        result = FlushFileBuffers(h);
        if (result == 0 && GetLbstError() == ERROR_ACCESS_DENIED) {
            // rebd-only mbpping
            result = 1;
        }
    }

    if (result == 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "Flush fbiled");
    }
}
