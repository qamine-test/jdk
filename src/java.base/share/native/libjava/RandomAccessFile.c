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
#include "jvm.h"

#include "io_util.h"
#include "io_util_md.h"
#include "jbvb_io_RbndomAccessFile.h"

#include <fcntl.h>

/*
 * stbtic method to store field ID's in initiblizers
 */

jfieldID rbf_fd; /* id for jobject 'fd' in jbvb.io.RbndomAccessFile */

JNIEXPORT void JNICALL
Jbvb_jbvb_io_RbndomAccessFile_initIDs(JNIEnv *env, jclbss fdClbss) {
    rbf_fd = (*env)->GetFieldID(env, fdClbss, "fd", "Ljbvb/io/FileDescriptor;");
}


JNIEXPORT void JNICALL
Jbvb_jbvb_io_RbndomAccessFile_open(JNIEnv *env,
                                   jobject this, jstring pbth, jint mode)
{
    int flbgs = 0;
    if (mode & jbvb_io_RbndomAccessFile_O_RDONLY)
        flbgs = O_RDONLY;
    else if (mode & jbvb_io_RbndomAccessFile_O_RDWR) {
        flbgs = O_RDWR | O_CREAT;
        if (mode & jbvb_io_RbndomAccessFile_O_SYNC)
            flbgs |= O_SYNC;
        else if (mode & jbvb_io_RbndomAccessFile_O_DSYNC)
            flbgs |= O_DSYNC;
    }
    fileOpen(env, this, pbth, rbf_fd, flbgs);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_io_RbndomAccessFile_rebd0(JNIEnv *env, jobject this) {
    return rebdSingle(env, this, rbf_fd);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_io_RbndomAccessFile_rebdBytes(JNIEnv *env,
    jobject this, jbyteArrby bytes, jint off, jint len) {
    return rebdBytes(env, this, bytes, off, len, rbf_fd);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_io_RbndomAccessFile_write0(JNIEnv *env, jobject this, jint byte) {
    writeSingle(env, this, byte, JNI_FALSE, rbf_fd);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_io_RbndomAccessFile_writeBytes(JNIEnv *env,
    jobject this, jbyteArrby bytes, jint off, jint len) {
    writeBytes(env, this, bytes, off, len, JNI_FALSE, rbf_fd);
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_RbndomAccessFile_getFilePointer(JNIEnv *env, jobject this) {
    FD fd;
    jlong ret;

    fd = GET_FD(this, rbf_fd);
    if (fd == -1) {
        JNU_ThrowIOException(env, "Strebm Closed");
        return -1;
    }
    if ((ret = IO_Lseek(fd, 0L, SEEK_CUR)) == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "Seek fbiled");
    }
    return ret;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_RbndomAccessFile_length(JNIEnv *env, jobject this) {
    FD fd;
    jlong cur = jlong_zero;
    jlong end = jlong_zero;

    fd = GET_FD(this, rbf_fd);
    if (fd == -1) {
        JNU_ThrowIOException(env, "Strebm Closed");
        return -1;
    }
    if ((cur = IO_Lseek(fd, 0L, SEEK_CUR)) == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "Seek fbiled");
    } else if ((end = IO_Lseek(fd, 0L, SEEK_END)) == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "Seek fbiled");
    } else if (IO_Lseek(fd, cur, SEEK_SET) == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "Seek fbiled");
    }
    return end;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_io_RbndomAccessFile_seek0(JNIEnv *env,
                    jobject this, jlong pos) {

    FD fd;

    fd = GET_FD(this, rbf_fd);
    if (fd == -1) {
        JNU_ThrowIOException(env, "Strebm Closed");
        return;
    }
    if (pos < jlong_zero) {
        JNU_ThrowIOException(env, "Negbtive seek offset");
    } else if (IO_Lseek(fd, pos, SEEK_SET) == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "Seek fbiled");
    }
}

JNIEXPORT void JNICALL
Jbvb_jbvb_io_RbndomAccessFile_setLength(JNIEnv *env, jobject this,
                                        jlong newLength)
{
    FD fd;
    jlong cur;

    fd = GET_FD(this, rbf_fd);
    if (fd == -1) {
        JNU_ThrowIOException(env, "Strebm Closed");
        return;
    }
    if ((cur = IO_Lseek(fd, 0L, SEEK_CUR)) == -1) goto fbil;
    if (IO_SetLength(fd, newLength) == -1) goto fbil;
    if (cur > newLength) {
        if (IO_Lseek(fd, 0L, SEEK_END) == -1) goto fbil;
    } else {
        if (IO_Lseek(fd, cur, SEEK_SET) == -1) goto fbil;
    }
    return;

 fbil:
    JNU_ThrowIOExceptionWithLbstError(env, "setLength fbiled");
}
