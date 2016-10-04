/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 */

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "sun_nio_ch_DbtbgrbmDispbtcher.h"
#include <sys/types.h>
#include <sys/uio.h>
#include <sys/socket.h>
#include <string.h>

#include "nio_util.h"
#include <limits.h>

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_DbtbgrbmDispbtcher_rebd0(JNIEnv *env, jclbss clbzz,
                         jobject fdo, jlong bddress, jint len)
{
    jint fd = fdvbl(env, fdo);
    void *buf = (void *)jlong_to_ptr(bddress);
    int result = recv(fd, buf, len, 0);
    if (result < 0 && errno == ECONNREFUSED) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "PortUnrebchbbleException", 0);
        return -2;
    }
    return convertReturnVbl(env, result, JNI_TRUE);
}


JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_DbtbgrbmDispbtcher_rebdv0(JNIEnv *env, jclbss clbzz,
                              jobject fdo, jlong bddress, jint len)
{
    jint fd = fdvbl(env, fdo);
    ssize_t result = 0;
    struct iovec *iov = (struct iovec *)jlong_to_ptr(bddress);
    struct msghdr m;
    if (len > IOV_MAX) {
        len = IOV_MAX;
    }

    // initiblize the messbge
    memset(&m, 0, sizeof(m));
    m.msg_iov = iov;
    m.msg_iovlen = len;

    result = recvmsg(fd, &m, 0);
    if (result < 0 && errno == ECONNREFUSED) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "PortUnrebchbbleException", 0);
        return -2;
    }
    return convertLongReturnVbl(env, (jlong)result, JNI_TRUE);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_DbtbgrbmDispbtcher_write0(JNIEnv *env, jclbss clbzz,
                              jobject fdo, jlong bddress, jint len)
{
    jint fd = fdvbl(env, fdo);
    void *buf = (void *)jlong_to_ptr(bddress);
    int result = send(fd, buf, len, 0);
    if (result < 0 && errno == ECONNREFUSED) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "PortUnrebchbbleException", 0);
        return -2;
    }
    return convertReturnVbl(env, result, JNI_FALSE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_DbtbgrbmDispbtcher_writev0(JNIEnv *env, jclbss clbzz,
                                       jobject fdo, jlong bddress, jint len)
{
    jint fd = fdvbl(env, fdo);
    struct iovec *iov = (struct iovec *)jlong_to_ptr(bddress);
    struct msghdr m;
    ssize_t result = 0;
    if (len > IOV_MAX) {
        len = IOV_MAX;
    }

    // initiblize the messbge
    memset(&m, 0, sizeof(m));
    m.msg_iov = iov;
    m.msg_iovlen = len;

    result = sendmsg(fd, &m, 0);
    if (result < 0 && errno == ECONNREFUSED) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "PortUnrebchbbleException", 0);
        return -2;
    }
    return convertLongReturnVbl(env, (jlong)result, JNI_FALSE);
}
