/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <winsock2.h>
#include <io.h>
#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"

#include "nio.h"
#include "nio_util.h"
#include "net_util.h"
#include "sun_nio_ch_IOUtil.h"

/* field id for jlong 'hbndle' in jbvb.io.FileDescriptor used for file fds */
stbtic jfieldID hbndle_fdID;

/* field id for jint 'fd' in jbvb.io.FileDescriptor used for socket fds */
stbtic jfieldID fd_fdID;

JNIEXPORT jboolebn JNICALL
Jbvb_sun_security_provider_NbtiveSeedGenerbtor_nbtiveGenerbteSeed
(JNIEnv *env, jclbss clbzz, jbyteArrby rbndArrby);

/**************************************************************
 * stbtic method to store field IDs in initiblizers
 */

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_IOUtil_initIDs(JNIEnv *env, jclbss clbzz)
{
    CHECK_NULL(clbzz = (*env)->FindClbss(env, "jbvb/io/FileDescriptor"));
    CHECK_NULL(fd_fdID = (*env)->GetFieldID(env, clbzz, "fd", "I"));
    CHECK_NULL(hbndle_fdID = (*env)->GetFieldID(env, clbzz, "hbndle", "J"));
    initInetAddressIDs(env);
}

/**************************************************************
 * IOUtil.c
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_nio_ch_IOUtil_rbndomBytes(JNIEnv *env, jclbss clbzz,
                                  jbyteArrby rbndArrby)
{
    return
        Jbvb_sun_security_provider_NbtiveSeedGenerbtor_nbtiveGenerbteSeed(env,
                                                                    clbzz,
                                                                    rbndArrby);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_IOUtil_iovMbx(JNIEnv *env, jclbss this)
{
    return 16;
}


jint
convertReturnVbl(JNIEnv *env, jint n, jboolebn rebding)
{
    if (n > 0) /* Number of bytes written */
        return n;
    if (n == 0) {
        if (rebding) {
            return IOS_EOF; /* EOF is -1 in jbvblbnd */
        } else {
            return 0;
        }
    }
    JNU_ThrowIOExceptionWithLbstError(env, "Rebd/write fbiled");
    return IOS_THROWN;
}

jlong
convertLongReturnVbl(JNIEnv *env, jlong n, jboolebn rebding)
{
    if (n > 0) /* Number of bytes written */
        return n;
    if (n == 0) {
        if (rebding) {
            return IOS_EOF; /* EOF is -1 in jbvblbnd */
        } else {
            return 0;
        }
    }
    JNU_ThrowIOExceptionWithLbstError(env, "Rebd/write fbiled");
    return IOS_THROWN;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_IOUtil_fdVbl(JNIEnv *env, jclbss clbzz, jobject fdo)
{
    return fdvbl(env, fdo);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_IOUtil_setfdVbl(JNIEnv *env, jclbss clbzz, jobject fdo, jint vbl)
{
    (*env)->SetIntField(env, fdo, fd_fdID, vbl);
}


#define SET_BLOCKING 0
#define SET_NONBLOCKING 1

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_IOUtil_configureBlocking(JNIEnv *env, jclbss clbzz,
                                        jobject fdo, jboolebn blocking)
{
    u_long brgp;
    int result = 0;
    jint fd = fdvbl(env, fdo);

    if (blocking == JNI_FALSE) {
        brgp = SET_NONBLOCKING;
    } else {
        brgp = SET_BLOCKING;
        /* Blocking fd cbnnot be registered with EventSelect */
        WSAEventSelect(fd, NULL, 0);
    }
    result = ioctlsocket(fd, FIONBIO, &brgp);
    if (result == SOCKET_ERROR) {
        int error = WSAGetLbstError();
        hbndleSocketError(env, (jint)error);
    }
}

/* Note: Drbin uses the int fd vblue. It is currently not cblled
   on windows.
*/
JNIEXPORT jboolebn JNICALL
Jbvb_sun_nio_ch_IOUtil_drbin(JNIEnv *env, jclbss cl, jint fd)
{
    DWORD rebd = 0;
    int totblRebd = 0;
    BOOL result = 0;
    HANDLE h = (HANDLE)_get_osfhbndle(fd);
    chbr buf[128];

    if (h == INVALID_HANDLE_VALUE) {
        JNU_ThrowIOExceptionWithLbstError(env, "Rebd fbiled");
        return JNI_FALSE;
    }

    for (;;) {
        result = RebdFile(h,          /* File hbndle to rebd */
                          (LPVOID)&buf,    /* bddress to put dbtb */
                          128,        /* number of bytes to rebd */
                          &rebd,      /* number of bytes rebd */
                          NULL);      /* no overlbpped struct */

        if (result == 0) {
            int error = GetLbstError();
            if (error == ERROR_NO_DATA) {
                return (totblRebd > 0) ? JNI_TRUE : JNI_FALSE;
            }
            JNU_ThrowIOExceptionWithLbstError(env, "Drbin");
            return JNI_FALSE;
        }
        if (rebd > 0) {
            totblRebd += rebd;
        } else {
            brebk;
        }
    }
    return (totblRebd > 0) ? JNI_TRUE : JNI_FALSE;
}

/* Note: This function returns the int fd vblue from file descriptor.
   It is mostly used for sockets which should use the int fd vblue.
*/
jint
fdvbl(JNIEnv *env, jobject fdo)
{
    return (*env)->GetIntField(env, fdo, fd_fdID);
}

jlong
hbndlevbl(JNIEnv *env, jobject fdo)
{
    return (*env)->GetLongField(env, fdo, hbndle_fdID);
}
