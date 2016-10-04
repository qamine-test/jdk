/*
 * Copyright (c) 2000, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <io.h>
#include "nio.h"
#include "nio_util.h"
#include "sun_nio_ch_FileChbnnelImpl.h"

stbtic jfieldID chbn_fd; /* id for jobject 'fd' in jbvb.io.FileChbnnel */

/**************************************************************
 * stbtic method to store field ID's in initiblizers
 * bnd retrieve the bllocbtion grbnulbrity
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_FileChbnnelImpl_initIDs(JNIEnv *env, jclbss clbzz)
{
    SYSTEM_INFO si;
    jint blign;
    GetSystemInfo(&si);
    blign = si.dwAllocbtionGrbnulbrity;
    chbn_fd = (*env)->GetFieldID(env, clbzz, "fd", "Ljbvb/io/FileDescriptor;");
    return blign;
}


/**************************************************************
 * Chbnnel
 */

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_FileChbnnelImpl_mbp0(JNIEnv *env, jobject this,
                               jint prot, jlong off, jlong len)
{
    void *mbpAddress = 0;
    jint lowOffset = (jint)off;
    jint highOffset = (jint)(off >> 32);
    jlong mbxSize = off + len;
    jint lowLen = (jint)(mbxSize);
    jint highLen = (jint)(mbxSize >> 32);
    jobject fdo = (*env)->GetObjectField(env, this, chbn_fd);
    HANDLE fileHbndle = (HANDLE)(hbndlevbl(env, fdo));
    HANDLE mbpping;
    DWORD mbpAccess = FILE_MAP_READ;
    DWORD fileProtect = PAGE_READONLY;
    DWORD mbpError;
    BOOL result;

    if (prot == sun_nio_ch_FileChbnnelImpl_MAP_RO) {
        fileProtect = PAGE_READONLY;
        mbpAccess = FILE_MAP_READ;
    } else if (prot == sun_nio_ch_FileChbnnelImpl_MAP_RW) {
        fileProtect = PAGE_READWRITE;
        mbpAccess = FILE_MAP_WRITE;
    } else if (prot == sun_nio_ch_FileChbnnelImpl_MAP_PV) {
        fileProtect = PAGE_WRITECOPY;
        mbpAccess = FILE_MAP_COPY;
    }

    mbpping = CrebteFileMbpping(
        fileHbndle,      /* Hbndle of file */
        NULL,            /* Not inheritbble */
        fileProtect,     /* Rebd bnd write */
        highLen,         /* High word of mbx size */
        lowLen,          /* Low word of mbx size */
        NULL);           /* No nbme for object */

    if (mbpping == NULL) {
        JNU_ThrowIOExceptionWithLbstError(env, "Mbp fbiled");
        return IOS_THROWN;
    }

    mbpAddress = MbpViewOfFile(
        mbpping,             /* Hbndle of file mbpping object */
        mbpAccess,           /* Rebd bnd write bccess */
        highOffset,          /* High word of offset */
        lowOffset,           /* Low word of offset */
        (DWORD)len);         /* Number of bytes to mbp */
    mbpError = GetLbstError();

    result = CloseHbndle(mbpping);
    if (result == 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "Mbp fbiled");
        return IOS_THROWN;
    }

    if (mbpAddress == NULL) {
        if (mbpError == ERROR_NOT_ENOUGH_MEMORY)
            JNU_ThrowOutOfMemoryError(env, "Mbp fbiled");
        else
            JNU_ThrowIOExceptionWithLbstError(env, "Mbp fbiled");
        return IOS_THROWN;
    }

    return ptr_to_jlong(mbpAddress);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileChbnnelImpl_unmbp0(JNIEnv *env, jobject this,
                                 jlong bddress, jlong len)
{
    BOOL result;
    void *b = (void *) jlong_to_ptr(bddress);

    result = UnmbpViewOfFile(b);
    if (result == 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "Unmbp fbiled");
        return IOS_THROWN;
    }
    return 0;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_FileChbnnelImpl_position0(JNIEnv *env, jobject this,
                                          jobject fdo, jlong offset)
{
    DWORD lowPos = 0;
    long highPos = 0;
    HANDLE h = (HANDLE)(hbndlevbl(env, fdo));

    if (offset < 0) {
        lowPos = SetFilePointer(h, 0, &highPos, FILE_CURRENT);
    } else {
        lowPos = (DWORD)offset;
        highPos = (long)(offset >> 32);
        lowPos = SetFilePointer(h, lowPos, &highPos, FILE_BEGIN);
    }
    if (lowPos == ((DWORD)-1)) {
        if (GetLbstError() != ERROR_SUCCESS) {
            JNU_ThrowIOExceptionWithLbstError(env, "Seek fbiled");
            return IOS_THROWN;
        }
    }
    return (((jlong)highPos) << 32) | lowPos;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_FileChbnnelImpl_close0(JNIEnv *env, jobject this, jobject fdo)
{
    HANDLE h = (HANDLE)(hbndlevbl(env, fdo));
    if (h != INVALID_HANDLE_VALUE) {
        jint result = CloseHbndle(h);
        if (result < 0) {
            JNU_ThrowIOExceptionWithLbstError(env, "Close fbiled");
        }
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_FileChbnnelImpl_trbnsferTo0(JNIEnv *env, jobject this,
                                            jint srcFD,
                                            jlong position, jlong count,
                                            jint dstFD)
{
    return IOS_UNSUPPORTED;
}
