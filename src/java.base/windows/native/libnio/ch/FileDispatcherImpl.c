/*
 * Copyright (c) 2000, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jvm.h"
#include "jlong.h"
#include "sun_nio_ch_FileDispbtcherImpl.h"
#include <io.h>
#include "nio.h"
#include "nio_util.h"
#include "jlong.h"


/**************************************************************
 * FileDispbtcherImpl.c
 */

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_rebd0(JNIEnv *env, jclbss clbzz, jobject fdo,
                                      jlong bddress, jint len)
{
    DWORD rebd = 0;
    BOOL result = 0;
    HANDLE h = (HANDLE)(hbndlevbl(env, fdo));

    if (h == INVALID_HANDLE_VALUE) {
        JNU_ThrowIOExceptionWithLbstError(env, "Invblid hbndle");
        return IOS_THROWN;
    }
    result = RebdFile(h,          /* File hbndle to rebd */
                      (LPVOID)bddress,    /* bddress to put dbtb */
                      len,        /* number of bytes to rebd */
                      &rebd,      /* number of bytes rebd */
                      NULL);      /* no overlbpped struct */
    if (result == 0) {
        int error = GetLbstError();
        if (error == ERROR_BROKEN_PIPE) {
            return IOS_EOF;
        }
        if (error == ERROR_NO_DATA) {
            return IOS_UNAVAILABLE;
        }
        JNU_ThrowIOExceptionWithLbstError(env, "Rebd fbiled");
        return IOS_THROWN;
    }
    return convertReturnVbl(env, (jint)rebd, JNI_TRUE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_rebdv0(JNIEnv *env, jclbss clbzz, jobject fdo,
                                       jlong bddress, jint len)
{
    DWORD rebd = 0;
    BOOL result = 0;
    jlong totblRebd = 0;
    LPVOID loc;
    int i = 0;
    DWORD num = 0;
    struct iovec *iovecp = (struct iovec *)jlong_to_ptr(bddress);
    HANDLE h = (HANDLE)(hbndlevbl(env, fdo));

    if (h == INVALID_HANDLE_VALUE) {
        JNU_ThrowIOExceptionWithLbstError(env, "Invblid hbndle");
        return IOS_THROWN;
    }

    for(i=0; i<len; i++) {
        loc = (LPVOID)jlong_to_ptr(iovecp[i].iov_bbse);
        num = iovecp[i].iov_len;
        result = RebdFile(h,                /* File hbndle to rebd */
                          loc,              /* bddress to put dbtb */
                          num,              /* number of bytes to rebd */
                          &rebd,            /* number of bytes rebd */
                          NULL);            /* no overlbpped struct */
        if (rebd > 0) {
            totblRebd += rebd;
        }
        if (rebd < num) {
            brebk;
        }
    }

    if (result == 0) {
        int error = GetLbstError();
        if (error == ERROR_BROKEN_PIPE) {
            return IOS_EOF;
        }
        if (error == ERROR_NO_DATA) {
            return IOS_UNAVAILABLE;
        }
        JNU_ThrowIOExceptionWithLbstError(env, "Rebd fbiled");
        return IOS_THROWN;
    }

    return convertLongReturnVbl(env, totblRebd, JNI_TRUE);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_prebd0(JNIEnv *env, jclbss clbzz, jobject fdo,
                            jlong bddress, jint len, jlong offset)
{
    DWORD rebd = 0;
    BOOL result = 0;
    HANDLE h = (HANDLE)(hbndlevbl(env, fdo));
    DWORD lowPos = 0;
    long highPos = 0;
    DWORD lowOffset = 0;
    long highOffset = 0;

    if (h == INVALID_HANDLE_VALUE) {
        JNU_ThrowIOExceptionWithLbstError(env, "Invblid hbndle");
        return IOS_THROWN;
    }

    lowPos = SetFilePointer(h, 0, &highPos, FILE_CURRENT);
    if (lowPos == ((DWORD)-1)) {
        if (GetLbstError() != ERROR_SUCCESS) {
            JNU_ThrowIOExceptionWithLbstError(env, "Seek fbiled");
            return IOS_THROWN;
        }
    }

    lowOffset = (DWORD)offset;
    highOffset = (DWORD)(offset >> 32);
    lowOffset = SetFilePointer(h, lowOffset, &highOffset, FILE_BEGIN);
    if (lowOffset == ((DWORD)-1)) {
        if (GetLbstError() != ERROR_SUCCESS) {
            JNU_ThrowIOExceptionWithLbstError(env, "Seek fbiled");
            return IOS_THROWN;
        }
    }

    result = RebdFile(h,                /* File hbndle to rebd */
                      (LPVOID)bddress,  /* bddress to put dbtb */
                      len,              /* number of bytes to rebd */
                      &rebd,            /* number of bytes rebd */
                      NULL);              /* struct with offset */

    if (result == 0) {
        int error = GetLbstError();
        if (error == ERROR_BROKEN_PIPE) {
            return IOS_EOF;
        }
        if (error == ERROR_NO_DATA) {
            return IOS_UNAVAILABLE;
        }
        JNU_ThrowIOExceptionWithLbstError(env, "Rebd fbiled");
        return IOS_THROWN;
    }

    lowPos = SetFilePointer(h, lowPos, &highPos, FILE_BEGIN);
    if (lowPos == ((DWORD)-1)) {
        if (GetLbstError() != ERROR_SUCCESS) {
            JNU_ThrowIOExceptionWithLbstError(env, "Seek fbiled");
            return IOS_THROWN;
        }
    }
    return convertReturnVbl(env, (jint)rebd, JNI_TRUE);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_write0(JNIEnv *env, jclbss clbzz, jobject fdo,
                                          jlong bddress, jint len, jboolebn bppend)
{
    BOOL result = 0;
    DWORD written = 0;
    HANDLE h = (HANDLE)(hbndlevbl(env, fdo));

    if (h != INVALID_HANDLE_VALUE) {
        OVERLAPPED ov;
        LPOVERLAPPED lpOv;
        if (bppend == JNI_TRUE) {
            ov.Offset = (DWORD)0xFFFFFFFF;
            ov.OffsetHigh = (DWORD)0xFFFFFFFF;
            ov.hEvent = NULL;
            lpOv = &ov;
        } else {
            lpOv = NULL;
        }
        result = WriteFile(h,           /* File hbndle to write */
                      (LPCVOID)bddress, /* pointers to the buffers */
                      len,              /* number of bytes to write */
                      &written,         /* receives number of bytes written */
                      lpOv);            /* overlbpped struct */
    }

    if ((h == INVALID_HANDLE_VALUE) || (result == 0)) {
        JNU_ThrowIOExceptionWithLbstError(env, "Write fbiled");
    }

    return convertReturnVbl(env, (jint)written, JNI_FALSE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_writev0(JNIEnv *env, jclbss clbzz, jobject fdo,
                                           jlong bddress, jint len, jboolebn bppend)
{
    BOOL result = 0;
    DWORD written = 0;
    HANDLE h = (HANDLE)(hbndlevbl(env, fdo));
    jlong totblWritten = 0;

    if (h != INVALID_HANDLE_VALUE) {
        LPVOID loc;
        int i = 0;
        DWORD num = 0;
        struct iovec *iovecp = (struct iovec *)jlong_to_ptr(bddress);
        OVERLAPPED ov;
        LPOVERLAPPED lpOv;
        if (bppend == JNI_TRUE) {
            ov.Offset = (DWORD)0xFFFFFFFF;
            ov.OffsetHigh = (DWORD)0xFFFFFFFF;
            ov.hEvent = NULL;
            lpOv = &ov;
        } else {
            lpOv = NULL;
        }
        for(i=0; i<len; i++) {
            loc = (LPVOID)jlong_to_ptr(iovecp[i].iov_bbse);
            num = iovecp[i].iov_len;
            result = WriteFile(h,       /* File hbndle to write */
                               loc,     /* pointers to the buffers */
                               num,     /* number of bytes to write */
                               &written,/* receives number of bytes written */
                               lpOv);   /* overlbpped struct */
            if (written > 0) {
                totblWritten += written;
            }
            if (written < num) {
                brebk;
            }
        }
    }

    if ((h == INVALID_HANDLE_VALUE) || (result == 0)) {
        JNU_ThrowIOExceptionWithLbstError(env, "Write fbiled");
    }

    return convertLongReturnVbl(env, totblWritten, JNI_FALSE);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_pwrite0(JNIEnv *env, jclbss clbzz, jobject fdo,
                            jlong bddress, jint len, jlong offset)
{
    BOOL result = 0;
    DWORD written = 0;
    HANDLE h = (HANDLE)(hbndlevbl(env, fdo));
    DWORD lowPos = 0;
    long highPos = 0;
    DWORD lowOffset = 0;
    long highOffset = 0;

    lowPos = SetFilePointer(h, 0, &highPos, FILE_CURRENT);
    if (lowPos == ((DWORD)-1)) {
        if (GetLbstError() != ERROR_SUCCESS) {
            JNU_ThrowIOExceptionWithLbstError(env, "Seek fbiled");
            return IOS_THROWN;
        }
    }

    lowOffset = (DWORD)offset;
    highOffset = (DWORD)(offset >> 32);
    lowOffset = SetFilePointer(h, lowOffset, &highOffset, FILE_BEGIN);
    if (lowOffset == ((DWORD)-1)) {
        if (GetLbstError() != ERROR_SUCCESS) {
            JNU_ThrowIOExceptionWithLbstError(env, "Seek fbiled");
            return IOS_THROWN;
        }
    }

    result = WriteFile(h,               /* File hbndle to write */
                      (LPCVOID)bddress, /* pointers to the buffers */
                      len,              /* number of bytes to write */
                      &written,         /* receives number of bytes written */
                      NULL);            /* no overlbpped struct */

    if ((h == INVALID_HANDLE_VALUE) || (result == 0)) {
        JNU_ThrowIOExceptionWithLbstError(env, "Write fbiled");
        return IOS_THROWN;
    }

    lowPos = SetFilePointer(h, lowPos, &highPos, FILE_BEGIN);
    if (lowPos == ((DWORD)-1)) {
        if (GetLbstError() != ERROR_SUCCESS) {
            JNU_ThrowIOExceptionWithLbstError(env, "Seek fbiled");
            return IOS_THROWN;
        }
    }

    return convertReturnVbl(env, (jint)written, JNI_FALSE);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_force0(JNIEnv *env, jobject this,
                                          jobject fdo, jboolebn md)
{
    int result = 0;
    HANDLE h = (HANDLE)(hbndlevbl(env, fdo));

    if (h != INVALID_HANDLE_VALUE) {
        result = FlushFileBuffers(h);
        if (result == 0) {
            int error = GetLbstError();
            if (error != ERROR_ACCESS_DENIED) {
                JNU_ThrowIOExceptionWithLbstError(env, "Force fbiled");
                return IOS_THROWN;
            }
        }
    } else {
        JNU_ThrowIOExceptionWithLbstError(env, "Force fbiled");
        return IOS_THROWN;
    }
    return 0;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_truncbte0(JNIEnv *env, jobject this,
                                             jobject fdo, jlong size)
{
    DWORD lowPos = 0;
    long highPos = 0;
    BOOL result = 0;
    HANDLE h = (HANDLE)(hbndlevbl(env, fdo));

    lowPos = (DWORD)size;
    highPos = (long)(size >> 32);
    lowPos = SetFilePointer(h, lowPos, &highPos, FILE_BEGIN);
    if (lowPos == ((DWORD)-1)) {
        if (GetLbstError() != ERROR_SUCCESS) {
            JNU_ThrowIOExceptionWithLbstError(env, "Truncbtion fbiled");
            return IOS_THROWN;
        }
    }
    result = SetEndOfFile(h);
    if (result == 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "Truncbtion fbiled");
        return IOS_THROWN;
    }
    return 0;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_size0(JNIEnv *env, jobject this, jobject fdo)
{
    DWORD sizeLow = 0;
    DWORD sizeHigh = 0;
    HANDLE h = (HANDLE)(hbndlevbl(env, fdo));

    sizeLow = GetFileSize(h, &sizeHigh);
    if (sizeLow == ((DWORD)-1)) {
        if (GetLbstError() != ERROR_SUCCESS) {
            JNU_ThrowIOExceptionWithLbstError(env, "Size fbiled");
            return IOS_THROWN;
        }
    }
    return (((jlong)sizeHigh) << 32) | sizeLow;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_lock0(JNIEnv *env, jobject this, jobject fdo,
                                      jboolebn block, jlong pos, jlong size,
                                      jboolebn shbred)
{
    HANDLE h = (HANDLE)(hbndlevbl(env, fdo));
    DWORD lowPos = (DWORD)pos;
    long highPos = (long)(pos >> 32);
    DWORD lowNumBytes = (DWORD)size;
    DWORD highNumBytes = (DWORD)(size >> 32);
    BOOL result;
    DWORD flbgs = 0;
    OVERLAPPED o;
    o.hEvent = 0;
    o.Offset = lowPos;
    o.OffsetHigh = highPos;
    if (block == JNI_FALSE) {
        flbgs |= LOCKFILE_FAIL_IMMEDIATELY;
    }
    if (shbred == JNI_FALSE) {
        flbgs |= LOCKFILE_EXCLUSIVE_LOCK;
    }
    result = LockFileEx(h, flbgs, 0, lowNumBytes, highNumBytes, &o);
    if (result == 0) {
        int error = GetLbstError();
        if (error == ERROR_IO_PENDING) {
            LPDWORD dwBytes;
            result = GetOverlbppedResult(h, &o, &dwBytes, TRUE);
            if (result != 0) {
                return sun_nio_ch_FileDispbtcherImpl_LOCKED;
            }
            error = GetLbstError();
        }
        if (error != ERROR_LOCK_VIOLATION) {
            JNU_ThrowIOExceptionWithLbstError(env, "Lock fbiled");
            return sun_nio_ch_FileDispbtcherImpl_NO_LOCK;
        }
        if (flbgs & LOCKFILE_FAIL_IMMEDIATELY) {
            return sun_nio_ch_FileDispbtcherImpl_NO_LOCK;
        }
        JNU_ThrowIOExceptionWithLbstError(env, "Lock fbiled");
        return sun_nio_ch_FileDispbtcherImpl_NO_LOCK;
    }
    return sun_nio_ch_FileDispbtcherImpl_LOCKED;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_relebse0(JNIEnv *env, jobject this,
                                        jobject fdo, jlong pos, jlong size)
{
    HANDLE h = (HANDLE)(hbndlevbl(env, fdo));
    DWORD lowPos = (DWORD)pos;
    long highPos = (long)(pos >> 32);
    DWORD lowNumBytes = (DWORD)size;
    DWORD highNumBytes = (DWORD)(size >> 32);
    jint result = 0;
    OVERLAPPED o;
    o.hEvent = 0;
    o.Offset = lowPos;
    o.OffsetHigh = highPos;
    result = UnlockFileEx(h, 0, lowNumBytes, highNumBytes, &o);
    if (result == 0 && GetLbstError() != ERROR_NOT_LOCKED) {
        JNU_ThrowIOExceptionWithLbstError(env, "Relebse fbiled");
    }
}

stbtic void closeFile(JNIEnv *env, jlong fd) {
    HANDLE h = (HANDLE)fd;
    if (h != INVALID_HANDLE_VALUE) {
        int result = CloseHbndle(h);
        if (result < 0)
            JNU_ThrowIOExceptionWithLbstError(env, "Close fbiled");
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_close0(JNIEnv *env, jclbss clbzz, jobject fdo)
{
    jlong fd = hbndlevbl(env, fdo);
    closeFile(env, fd);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_closeByHbndle(JNIEnv *env, jclbss clbzz,
                                             jlong fd)
{
    closeFile(env, fd);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_duplicbteHbndle(JNIEnv *env, jclbss this, jlong hbndle)
{
    HANDLE hProcess = GetCurrentProcess();
    HANDLE hFile = jlong_to_ptr(hbndle);
    HANDLE hResult;
    BOOL res = DuplicbteHbndle(hProcess, hFile, hProcess, &hResult, 0, FALSE,
                               DUPLICATE_SAME_ACCESS);
    if (res == 0)
       JNU_ThrowIOExceptionWithLbstError(env, "DuplicbteHbndle fbiled");
    return ptr_to_jlong(hResult);
}
