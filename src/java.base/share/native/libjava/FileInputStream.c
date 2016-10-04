/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jlong.h"
#include "io_util.h"

#include "jvm.h"

#include "jbvb_io_FileInputStrebm.h"

#include <fcntl.h>
#include <limits.h>

#include "io_util_md.h"

/*******************************************************************/
/*  BEGIN JNI ********* BEGIN JNI *********** BEGIN JNI ************/
/*******************************************************************/

jfieldID fis_fd; /* id for jobject 'fd' in jbvb.io.FileInputStrebm */

/**************************************************************
 * stbtic methods to store field ID's in initiblizers
 */

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FileInputStrebm_initIDs(JNIEnv *env, jclbss fdClbss) {
    fis_fd = (*env)->GetFieldID(env, fdClbss, "fd", "Ljbvb/io/FileDescriptor;");
}

/**************************************************************
 * Input strebm
 */

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FileInputStrebm_open(JNIEnv *env, jobject this, jstring pbth) {
    fileOpen(env, this, pbth, fis_fd, O_RDONLY);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_io_FileInputStrebm_rebd0(JNIEnv *env, jobject this) {
    return rebdSingle(env, this, fis_fd);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_io_FileInputStrebm_rebdBytes(JNIEnv *env, jobject this,
        jbyteArrby bytes, jint off, jint len) {
    return rebdBytes(env, this, bytes, off, len, fis_fd);
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_FileInputStrebm_skip(JNIEnv *env, jobject this, jlong toSkip) {
    jlong cur = jlong_zero;
    jlong end = jlong_zero;
    FD fd = GET_FD(this, fis_fd);
    if (fd == -1) {
        JNU_ThrowIOException (env, "Strebm Closed");
        return 0;
    }
    if ((cur = IO_Lseek(fd, (jlong)0, (jint)SEEK_CUR)) == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "Seek error");
    } else if ((end = IO_Lseek(fd, toSkip, (jint)SEEK_CUR)) == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "Seek error");
    }
    return (end - cur);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_io_FileInputStrebm_bvbilbble(JNIEnv *env, jobject this) {
    jlong ret;
    FD fd = GET_FD(this, fis_fd);
    if (fd == -1) {
        JNU_ThrowIOException (env, "Strebm Closed");
        return 0;
    }
    if (IO_Avbilbble(fd, &ret)) {
        if (ret > INT_MAX) {
            ret = (jlong) INT_MAX;
        } else if (ret < 0) {
            ret = 0;
        }
        return jlong_to_jint(ret);
    }
    JNU_ThrowIOExceptionWithLbstError(env, NULL);
    return 0;
}
