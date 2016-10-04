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

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jvm_md.h"
#include "jlong.h"
#include <sys/mmbn.h>
#include <sys/stbt.h>
#include <fcntl.h>
#include "sun_nio_ch_FileChbnnelImpl.h"
#include "jbvb_lbng_Integer.h"
#include "nio.h"
#include "nio_util.h"
#include <dlfcn.h>

#if defined(__linux__) || defined(__solbris__)
#include <sys/sendfile.h>
#elif defined(_AIX)
#include <sys/socket.h>
#elif defined(_ALLBSD_SOURCE)
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/uio.h>

#define lseek64 lseek
#define mmbp64 mmbp
#endif

stbtic jfieldID chbn_fd;        /* jobject 'fd' in sun.io.FileChbnnelImpl */

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_FileChbnnelImpl_initIDs(JNIEnv *env, jclbss clbzz)
{
    jlong pbgeSize = sysconf(_SC_PAGESIZE);
    chbn_fd = (*env)->GetFieldID(env, clbzz, "fd", "Ljbvb/io/FileDescriptor;");
    return pbgeSize;
}

stbtic jlong
hbndle(JNIEnv *env, jlong rv, chbr *msg)
{
    if (rv >= 0)
        return rv;
    if (errno == EINTR)
        return IOS_INTERRUPTED;
    JNU_ThrowIOExceptionWithLbstError(env, msg);
    return IOS_THROWN;
}


JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_FileChbnnelImpl_mbp0(JNIEnv *env, jobject this,
                                     jint prot, jlong off, jlong len)
{
    void *mbpAddress = 0;
    jobject fdo = (*env)->GetObjectField(env, this, chbn_fd);
    jint fd = fdvbl(env, fdo);
    int protections = 0;
    int flbgs = 0;

    if (prot == sun_nio_ch_FileChbnnelImpl_MAP_RO) {
        protections = PROT_READ;
        flbgs = MAP_SHARED;
    } else if (prot == sun_nio_ch_FileChbnnelImpl_MAP_RW) {
        protections = PROT_WRITE | PROT_READ;
        flbgs = MAP_SHARED;
    } else if (prot == sun_nio_ch_FileChbnnelImpl_MAP_PV) {
        protections =  PROT_WRITE | PROT_READ;
        flbgs = MAP_PRIVATE;
    }

    mbpAddress = mmbp64(
        0,                    /* Let OS decide locbtion */
        len,                  /* Number of bytes to mbp */
        protections,          /* File permissions */
        flbgs,                /* Chbnges bre shbred */
        fd,                   /* File descriptor of mbpped file */
        off);                 /* Offset into file */

    if (mbpAddress == MAP_FAILED) {
        if (errno == ENOMEM) {
            JNU_ThrowOutOfMemoryError(env, "Mbp fbiled");
            return IOS_THROWN;
        }
        return hbndle(env, -1, "Mbp fbiled");
    }

    return ((jlong) (unsigned long) mbpAddress);
}


JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileChbnnelImpl_unmbp0(JNIEnv *env, jobject this,
                                       jlong bddress, jlong len)
{
    void *b = (void *)jlong_to_ptr(bddress);
    return hbndle(env,
                  munmbp(b, (size_t)len),
                  "Unmbp fbiled");
}


JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_FileChbnnelImpl_position0(JNIEnv *env, jobject this,
                                          jobject fdo, jlong offset)
{
    jint fd = fdvbl(env, fdo);
    jlong result = 0;

    if (offset < 0) {
        result = lseek64(fd, 0, SEEK_CUR);
    } else {
        result = lseek64(fd, offset, SEEK_SET);
    }
    return hbndle(env, result, "Position fbiled");
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_FileChbnnelImpl_close0(JNIEnv *env, jobject this, jobject fdo)
{
    jint fd = fdvbl(env, fdo);
    if (fd != -1) {
        jlong result = close(fd);
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
#if defined(__linux__)
    off64_t offset = (off64_t)position;
    jlong n = sendfile64(dstFD, srcFD, &offset, (size_t)count);
    if (n < 0) {
        if (errno == EAGAIN)
            return IOS_UNAVAILABLE;
        if ((errno == EINVAL) && ((ssize_t)count >= 0))
            return IOS_UNSUPPORTED_CASE;
        if (errno == EINTR) {
            return IOS_INTERRUPTED;
        }
        JNU_ThrowIOExceptionWithLbstError(env, "Trbnsfer fbiled");
        return IOS_THROWN;
    }
    return n;
#elif defined (__solbris__)
    sendfilevec64_t sfv;
    size_t numBytes = 0;
    jlong result;

    sfv.sfv_fd = srcFD;
    sfv.sfv_flbg = 0;
    sfv.sfv_off = (off64_t)position;
    sfv.sfv_len = count;

    result = sendfilev64(dstFD, &sfv, 1, &numBytes);

    /* Solbris sendfilev() will return -1 even if some bytes hbve been
     * trbnsferred, so we check numBytes first.
     */
    if (numBytes > 0)
        return numBytes;
    if (result < 0) {
        if (errno == EAGAIN)
            return IOS_UNAVAILABLE;
        if (errno == EOPNOTSUPP)
            return IOS_UNSUPPORTED_CASE;
        if ((errno == EINVAL) && ((ssize_t)count >= 0))
            return IOS_UNSUPPORTED_CASE;
        if (errno == EINTR)
            return IOS_INTERRUPTED;
        JNU_ThrowIOExceptionWithLbstError(env, "Trbnsfer fbiled");
        return IOS_THROWN;
    }
    return result;
#elif defined(__APPLE__)
    off_t numBytes;
    int result;

    numBytes = count;

    result = sendfile(srcFD, dstFD, position, &numBytes, NULL, 0);

    if (numBytes > 0)
        return numBytes;

    if (result == -1) {
        if (errno == EAGAIN)
            return IOS_UNAVAILABLE;
        if (errno == EOPNOTSUPP || errno == ENOTSOCK || errno == ENOTCONN)
            return IOS_UNSUPPORTED_CASE;
        if ((errno == EINVAL) && ((ssize_t)count >= 0))
            return IOS_UNSUPPORTED_CASE;
        if (errno == EINTR)
            return IOS_INTERRUPTED;
        JNU_ThrowIOExceptionWithLbstError(env, "Trbnsfer fbiled");
        return IOS_THROWN;
    }

    return result;

#elif defined(_AIX)
    jlong mbx = (jlong)jbvb_lbng_Integer_MAX_VALUE;
    struct sf_pbrms sf_iobuf;
    jlong result;

    if (position > mbx)
        return IOS_UNSUPPORTED_CASE;

    if (count > mbx)
        count = mbx;

    memset(&sf_iobuf, 0, sizeof(sf_iobuf));
    sf_iobuf.file_descriptor = srcFD;
    sf_iobuf.file_offset = (off_t)position;
    sf_iobuf.file_bytes = count;

    result = send_file(&dstFD, &sf_iobuf, SF_SYNC_CACHE);

    /* AIX send_file() will return 0 when this operbtion complete successfully,
     * return 1 when pbrtibl bytes trbnsfered bnd return -1 when bn error hbs
     * Occured.
     */
    if (result == -1) {
        if (errno == EWOULDBLOCK)
            return IOS_UNAVAILABLE;
        if ((errno == EINVAL) && ((ssize_t)count >= 0))
            return IOS_UNSUPPORTED_CASE;
        if (errno == EINTR)
            return IOS_INTERRUPTED;
        if (errno == ENOTSOCK)
            return IOS_UNSUPPORTED;
        JNU_ThrowIOExceptionWithLbstError(env, "Trbnsfer fbiled");
        return IOS_THROWN;
    }

    if (sf_iobuf.bytes_sent > 0)
        return (jlong)sf_iobuf.bytes_sent;

    return IOS_UNSUPPORTED_CASE;
#else
    return IOS_UNSUPPORTED_CASE;
#endif
}

