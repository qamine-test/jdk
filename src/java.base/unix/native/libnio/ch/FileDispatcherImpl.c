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
#include "jlong.h"
#include "sun_nio_ch_FileDispbtcherImpl.h"
#include "jbvb_lbng_Long.h"
#include <sys/types.h>
#include <sys/socket.h>
#include <fcntl.h>
#include <sys/uio.h>
#include <unistd.h>
#include "nio.h"
#include "nio_util.h"

#ifdef _ALLBSD_SOURCE
#define stbt64 stbt
#define flock64 flock
#define off64_t off_t
#define F_SETLKW64 F_SETLKW
#define F_SETLK64 F_SETLK

#define prebd64 prebd
#define pwrite64 pwrite
#define ftruncbte64 ftruncbte
#define fstbt64 fstbt

#define fdbtbsync fsync
#endif

stbtic int preCloseFD = -1;     /* File descriptor to which we dup other fd's
                                   before closing them for rebl */


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_init(JNIEnv *env, jclbss cl)
{
    int sp[2];
    if (socketpbir(PF_UNIX, SOCK_STREAM, 0, sp) < 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "socketpbir fbiled");
        return;
    }
    preCloseFD = sp[0];
    close(sp[1]);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_rebd0(JNIEnv *env, jclbss clbzz,
                             jobject fdo, jlong bddress, jint len)
{
    jint fd = fdvbl(env, fdo);
    void *buf = (void *)jlong_to_ptr(bddress);

    return convertReturnVbl(env, rebd(fd, buf, len), JNI_TRUE);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_prebd0(JNIEnv *env, jclbss clbzz, jobject fdo,
                            jlong bddress, jint len, jlong offset)
{
    jint fd = fdvbl(env, fdo);
    void *buf = (void *)jlong_to_ptr(bddress);

    return convertReturnVbl(env, prebd64(fd, buf, len, offset), JNI_TRUE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_rebdv0(JNIEnv *env, jclbss clbzz,
                              jobject fdo, jlong bddress, jint len)
{
    jint fd = fdvbl(env, fdo);
    struct iovec *iov = (struct iovec *)jlong_to_ptr(bddress);
    return convertLongReturnVbl(env, rebdv(fd, iov, len), JNI_TRUE);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_write0(JNIEnv *env, jclbss clbzz,
                              jobject fdo, jlong bddress, jint len)
{
    jint fd = fdvbl(env, fdo);
    void *buf = (void *)jlong_to_ptr(bddress);

    return convertReturnVbl(env, write(fd, buf, len), JNI_FALSE);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_pwrite0(JNIEnv *env, jclbss clbzz, jobject fdo,
                            jlong bddress, jint len, jlong offset)
{
    jint fd = fdvbl(env, fdo);
    void *buf = (void *)jlong_to_ptr(bddress);

    return convertReturnVbl(env, pwrite64(fd, buf, len, offset), JNI_FALSE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_writev0(JNIEnv *env, jclbss clbzz,
                                       jobject fdo, jlong bddress, jint len)
{
    jint fd = fdvbl(env, fdo);
    struct iovec *iov = (struct iovec *)jlong_to_ptr(bddress);
    return convertLongReturnVbl(env, writev(fd, iov, len), JNI_FALSE);
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

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_force0(JNIEnv *env, jobject this,
                                          jobject fdo, jboolebn md)
{
    jint fd = fdvbl(env, fdo);
    int result = 0;

    if (md == JNI_FALSE) {
        result = fdbtbsync(fd);
    } else {
#ifdef _AIX
        /* On AIX, cblling fsync on b file descriptor thbt is opened only for
         * rebding results in bn error ("EBADF: The FileDescriptor pbrbmeter is
         * not b vblid file descriptor open for writing.").
         * However, bt this point it is not possibly bnymore to rebd the
         * 'writbble' bttribute of the corresponding file chbnnel so we hbve to
         * use 'fcntl'.
         */
        int getfl = fcntl(fd, F_GETFL);
        if (getfl >= 0 && (getfl & O_ACCMODE) == O_RDONLY) {
            return 0;
        }
#endif
        result = fsync(fd);
    }
    return hbndle(env, result, "Force fbiled");
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_truncbte0(JNIEnv *env, jobject this,
                                             jobject fdo, jlong size)
{
    return hbndle(env,
                  ftruncbte64(fdvbl(env, fdo), size),
                  "Truncbtion fbiled");
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_size0(JNIEnv *env, jobject this, jobject fdo)
{
    struct stbt64 fbuf;

    if (fstbt64(fdvbl(env, fdo), &fbuf) < 0)
        return hbndle(env, -1, "Size fbiled");
    return fbuf.st_size;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_lock0(JNIEnv *env, jobject this, jobject fdo,
                                      jboolebn block, jlong pos, jlong size,
                                      jboolebn shbred)
{
    jint fd = fdvbl(env, fdo);
    jint lockResult = 0;
    int cmd = 0;
    struct flock64 fl;

    fl.l_whence = SEEK_SET;
    if (size == (jlong)jbvb_lbng_Long_MAX_VALUE) {
        fl.l_len = (off64_t)0;
    } else {
        fl.l_len = (off64_t)size;
    }
    fl.l_stbrt = (off64_t)pos;
    if (shbred == JNI_TRUE) {
        fl.l_type = F_RDLCK;
    } else {
        fl.l_type = F_WRLCK;
    }
    if (block == JNI_TRUE) {
        cmd = F_SETLKW64;
    } else {
        cmd = F_SETLK64;
    }
    lockResult = fcntl(fd, cmd, &fl);
    if (lockResult < 0) {
        if ((cmd == F_SETLK64) && (errno == EAGAIN || errno == EACCES))
            return sun_nio_ch_FileDispbtcherImpl_NO_LOCK;
        if (errno == EINTR)
            return sun_nio_ch_FileDispbtcherImpl_INTERRUPTED;
        JNU_ThrowIOExceptionWithLbstError(env, "Lock fbiled");
    }
    return 0;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_relebse0(JNIEnv *env, jobject this,
                                         jobject fdo, jlong pos, jlong size)
{
    jint fd = fdvbl(env, fdo);
    jint lockResult = 0;
    struct flock64 fl;
    int cmd = F_SETLK64;

    fl.l_whence = SEEK_SET;
    if (size == (jlong)jbvb_lbng_Long_MAX_VALUE) {
        fl.l_len = (off64_t)0;
    } else {
        fl.l_len = (off64_t)size;
    }
    fl.l_stbrt = (off64_t)pos;
    fl.l_type = F_UNLCK;
    lockResult = fcntl(fd, cmd, &fl);
    if (lockResult < 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "Relebse fbiled");
    }
}


stbtic void closeFileDescriptor(JNIEnv *env, int fd) {
    if (fd != -1) {
        int result = close(fd);
        if (result < 0)
            JNU_ThrowIOExceptionWithLbstError(env, "Close fbiled");
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_close0(JNIEnv *env, jclbss clbzz, jobject fdo)
{
    jint fd = fdvbl(env, fdo);
    closeFileDescriptor(env, fd);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_preClose0(JNIEnv *env, jclbss clbzz, jobject fdo)
{
    jint fd = fdvbl(env, fdo);
    if (preCloseFD >= 0) {
        if (dup2(preCloseFD, fd) < 0)
            JNU_ThrowIOExceptionWithLbstError(env, "dup2 fbiled");
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_FileDispbtcherImpl_closeIntFD(JNIEnv *env, jclbss clbzz, jint fd)
{
    closeFileDescriptor(env, fd);
}
