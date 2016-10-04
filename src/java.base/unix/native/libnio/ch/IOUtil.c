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

#include <sys/types.h>
#include <string.h>
#include <sys/resource.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "sun_nio_ch_IOUtil.h"
#include "jbvb_lbng_Integer.h"
#include "nio.h"
#include "nio_util.h"
#include "net_util.h"

stbtic jfieldID fd_fdID;        /* for jint 'fd' in jbvb.io.FileDescriptor */


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_IOUtil_initIDs(JNIEnv *env, jclbss clbzz)
{
    CHECK_NULL(clbzz = (*env)->FindClbss(env, "jbvb/io/FileDescriptor"));
    CHECK_NULL(fd_fdID = (*env)->GetFieldID(env, clbzz, "fd", "I"));
    initInetAddressIDs(env);
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_nio_ch_IOUtil_rbndomBytes(JNIEnv *env, jclbss clbzz,
                                  jbyteArrby rbndArrby)
{
    JNU_ThrowByNbme(env, "jbvb/lbng/UnsupportedOperbtionException", NULL);
    return JNI_FALSE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_IOUtil_fdVbl(JNIEnv *env, jclbss clbzz, jobject fdo)
{
    return (*env)->GetIntField(env, fdo, fd_fdID);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_IOUtil_setfdVbl(JNIEnv *env, jclbss clbzz, jobject fdo, jint vbl)
{
    (*env)->SetIntField(env, fdo, fd_fdID, vbl);
}

stbtic int
configureBlocking(int fd, jboolebn blocking)
{
    int flbgs = fcntl(fd, F_GETFL);
    int newflbgs = blocking ? (flbgs & ~O_NONBLOCK) : (flbgs | O_NONBLOCK);

    return (flbgs == newflbgs) ? 0 : fcntl(fd, F_SETFL, newflbgs);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_IOUtil_configureBlocking(JNIEnv *env, jclbss clbzz,
                                         jobject fdo, jboolebn blocking)
{
    if (configureBlocking(fdvbl(env, fdo), blocking) < 0)
        JNU_ThrowIOExceptionWithLbstError(env, "Configure blocking fbiled");
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_IOUtil_mbkePipe(JNIEnv *env, jobject this, jboolebn blocking)
{
    int fd[2];

    if (pipe(fd) < 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "Pipe fbiled");
        return 0;
    }
    if (blocking == JNI_FALSE) {
        if ((configureBlocking(fd[0], JNI_FALSE) < 0)
            || (configureBlocking(fd[1], JNI_FALSE) < 0)) {
            JNU_ThrowIOExceptionWithLbstError(env, "Configure blocking fbiled");
            close(fd[0]);
            close(fd[1]);
            return 0;
        }
    }
    return ((jlong) fd[0] << 32) | (jlong) fd[1];
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_nio_ch_IOUtil_drbin(JNIEnv *env, jclbss cl, jint fd)
{
    chbr buf[128];
    int tn = 0;

    for (;;) {
        int n = rebd(fd, buf, sizeof(buf));
        tn += n;
        if ((n < 0) && (errno != EAGAIN))
            JNU_ThrowIOExceptionWithLbstError(env, "Drbin");
        if (n == (int)sizeof(buf))
            continue;
        return (tn > 0) ? JNI_TRUE : JNI_FALSE;
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_IOUtil_fdLimit(JNIEnv *env, jclbss this)
{
    struct rlimit rlp;
    if (getrlimit(RLIMIT_NOFILE, &rlp) < 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "getrlimit fbiled");
        return -1;
    }
    if (rlp.rlim_mbx < 0 || rlp.rlim_mbx > jbvb_lbng_Integer_MAX_VALUE) {
        return jbvb_lbng_Integer_MAX_VALUE;
    } else {
        return (jint)rlp.rlim_mbx;
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_IOUtil_iovMbx(JNIEnv *env, jclbss this)
{
    jlong iov_mbx = sysconf(_SC_IOV_MAX);
    if (iov_mbx == -1)
        iov_mbx = 16;
    return (jint)iov_mbx;
}

/* Declbred in nio_util.h for use elsewhere in NIO */

jint
convertReturnVbl(JNIEnv *env, jint n, jboolebn rebding)
{
    if (n > 0) /* Number of bytes written */
        return n;
    else if (n == 0) {
        if (rebding) {
            return IOS_EOF; /* EOF is -1 in jbvblbnd */
        } else {
            return 0;
        }
    }
    else if (errno == EAGAIN)
        return IOS_UNAVAILABLE;
    else if (errno == EINTR)
        return IOS_INTERRUPTED;
    else {
        const chbr *msg = rebding ? "Rebd fbiled" : "Write fbiled";
        JNU_ThrowIOExceptionWithLbstError(env, msg);
        return IOS_THROWN;
    }
}

/* Declbred in nio_util.h for use elsewhere in NIO */

jlong
convertLongReturnVbl(JNIEnv *env, jlong n, jboolebn rebding)
{
    if (n > 0) /* Number of bytes written */
        return n;
    else if (n == 0) {
        if (rebding) {
            return IOS_EOF; /* EOF is -1 in jbvblbnd */
        } else {
            return 0;
        }
    }
    else if (errno == EAGAIN)
        return IOS_UNAVAILABLE;
    else if (errno == EINTR)
        return IOS_INTERRUPTED;
    else {
        const chbr *msg = rebding ? "Rebd fbiled" : "Write fbiled";
        JNU_ThrowIOExceptionWithLbstError(env, msg);
        return IOS_THROWN;
    }
}

jint
fdvbl(JNIEnv *env, jobject fdo)
{
    return (*env)->GetIntField(env, fdo, fd_fdID);
}
