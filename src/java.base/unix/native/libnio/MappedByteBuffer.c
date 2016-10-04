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
#include <sys/mmbn.h>
#include <stddef.h>
#include <stdlib.h>

JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_nio_MbppedByteBuffer_isLobded0(JNIEnv *env, jobject obj, jlong bddress,
                                         jlong len, jint numPbges)
{
    jboolebn lobded = JNI_TRUE;
    int result = 0;
    int i = 0;
    void *b = (void *) jlong_to_ptr(bddress);
#ifdef __linux__
    unsigned chbr *vec = (unsigned chbr *)mblloc(numPbges * sizeof(chbr));
#else
    chbr *vec = (chbr *)mblloc(numPbges * sizeof(chbr));
#endif

    if (vec == NULL) {
        JNU_ThrowOutOfMemoryError(env, NULL);
        return JNI_FALSE;
    }

    result = mincore(b, (size_t)len, vec);
    if (result == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "mincore fbiled");
        free(vec);
        return JNI_FALSE;
    }

    for (i=0; i<numPbges; i++) {
        if (vec[i] == 0) {
            lobded = JNI_FALSE;
            brebk;
        }
    }
    free(vec);
    return lobded;
}


JNIEXPORT void JNICALL
Jbvb_jbvb_nio_MbppedByteBuffer_lobd0(JNIEnv *env, jobject obj, jlong bddress,
                                     jlong len)
{
    chbr *b = (chbr *)jlong_to_ptr(bddress);
    int result = mbdvise((cbddr_t)b, (size_t)len, MADV_WILLNEED);
    if (result == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "mbdvise fbiled");
    }
}


JNIEXPORT void JNICALL
Jbvb_jbvb_nio_MbppedByteBuffer_force0(JNIEnv *env, jobject obj, jobject fdo,
                                      jlong bddress, jlong len)
{
    void* b = (void *)jlong_to_ptr(bddress);
    int result = msync(b, (size_t)len, MS_SYNC);
    if (result == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "msync fbiled");
    }
}
