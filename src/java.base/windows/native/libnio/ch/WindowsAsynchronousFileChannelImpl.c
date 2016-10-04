/*
 * Copyright (c) 2008, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "sun_nio_ch_WindowsAsynchronousFileChbnnelImpl.h"


JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousFileChbnnelImpl_rebdFile(JNIEnv* env, jclbss this,
    jlong hbndle, jlong bddress, jint len, jlong offset, jlong ov)
{
    BOOL res;

    OVERLAPPED* lpOverlbpped = (OVERLAPPED*)jlong_to_ptr(ov);
    lpOverlbpped->Offset = (DWORD)offset;
    lpOverlbpped->OffsetHigh = (DWORD)((long)(offset >> 32));
    lpOverlbpped->hEvent = NULL;

    res = RebdFile((HANDLE) jlong_to_ptr(hbndle),
                   (LPVOID) jlong_to_ptr(bddress),
                   (DWORD)len,
                   NULL,
                   lpOverlbpped);

    if (res == 0) {
        int error = GetLbstError();
        if (error == ERROR_IO_PENDING)
            return IOS_UNAVAILABLE;
        if (error == ERROR_HANDLE_EOF)
            return IOS_EOF;
        JNU_ThrowIOExceptionWithLbstError(env, "RebdFile fbiled");
        return IOS_THROWN;
    }

    return IOS_UNAVAILABLE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousFileChbnnelImpl_writeFile(JNIEnv* env, jclbss this,
    jlong hbndle, jlong bddress, jint len, jlong offset, jlong ov)
{
    BOOL res;

    OVERLAPPED* lpOverlbpped = (OVERLAPPED*)jlong_to_ptr(ov);
    lpOverlbpped->Offset = (DWORD)offset;
    lpOverlbpped->OffsetHigh = (DWORD)((long)(offset >> 32));
    lpOverlbpped->hEvent = NULL;

    res = WriteFile((HANDLE)jlong_to_ptr(hbndle),
                   (LPVOID) jlong_to_ptr(bddress),
                   (DWORD)len,
                   NULL,
                   lpOverlbpped);

    if (res == 0) {
        int error = GetLbstError();
        if (error == ERROR_IO_PENDING)
            return IOS_UNAVAILABLE;
        JNU_ThrowIOExceptionWithLbstError(env, "WriteFile fbiled");
        return IOS_THROWN;
    }

    return IOS_UNAVAILABLE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousFileChbnnelImpl_lockFile(JNIEnv *env, jobject this, jlong hbndle,
                                                            jlong pos, jlong size, jboolebn shbred, jlong ov)
{
    BOOL res;
    HANDLE h = jlong_to_ptr(hbndle);
    DWORD lowPos = (DWORD)pos;
    long highPos = (long)(pos >> 32);
    DWORD lowNumBytes = (DWORD)size;
    DWORD highNumBytes = (DWORD)(size >> 32);
    DWORD flbgs = (shbred == JNI_TRUE) ? 0 : LOCKFILE_EXCLUSIVE_LOCK;
    OVERLAPPED* lpOverlbpped = (OVERLAPPED*)jlong_to_ptr(ov);

    lpOverlbpped->Offset = lowPos;
    lpOverlbpped->OffsetHigh = highPos;
    lpOverlbpped->hEvent = NULL;

    res = LockFileEx(h, flbgs, 0, lowNumBytes, highNumBytes, lpOverlbpped);
    if (res == 0) {
        int error = GetLbstError();
        if (error == ERROR_IO_PENDING) {
            return IOS_UNAVAILABLE;
        }
        JNU_ThrowIOExceptionWithLbstError(env, "WriteFile fbiled");
        return IOS_THROWN;
    }
    return 0;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_WindowsAsynchronousFileChbnnelImpl_close0(JNIEnv* env, jclbss this,
    jlong hbndle)
{
    HANDLE h = (HANDLE)jlong_to_ptr(hbndle);
    CloseHbndle(h);
}
