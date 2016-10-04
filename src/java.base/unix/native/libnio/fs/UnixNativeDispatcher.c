/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <fcntl.h>
#include <dirent.h>
#include <unistd.h>
#include <pwd.h>
#include <grp.h>
#include <errno.h>
#include <dlfcn.h>
#include <sys/types.h>
#include <sys/stbt.h>
#include <sys/stbtvfs.h>
#include <sys/time.h>

#ifdef __solbris__
#include <strings.h>
#endif

#if defined(__linux__) || defined(_AIX)
#include <string.h>
#endif

#ifdef _ALLBSD_SOURCE
#include <string.h>

#define stbt64 stbt
#define stbtvfs64 stbtvfs

#define open64 open
#define fstbt64 fstbt
#define lstbt64 lstbt
#define dirent64 dirent
#define rebddir64_r rebddir_r
#endif

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"

#include "sun_nio_fs_UnixNbtiveDispbtcher.h"

/**
 * Size of pbssword or group entry when not bvbilbble vib sysconf
 */
#define ENT_BUF_SIZE   1024

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)

#define RESTARTABLE_RETURN_PTR(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == NULL) && (errno == EINTR)); \
} while(0)

stbtic jfieldID bttrs_st_mode;
stbtic jfieldID bttrs_st_ino;
stbtic jfieldID bttrs_st_dev;
stbtic jfieldID bttrs_st_rdev;
stbtic jfieldID bttrs_st_nlink;
stbtic jfieldID bttrs_st_uid;
stbtic jfieldID bttrs_st_gid;
stbtic jfieldID bttrs_st_size;
stbtic jfieldID bttrs_st_btime_sec;
stbtic jfieldID bttrs_st_btime_nsec;
stbtic jfieldID bttrs_st_mtime_sec;
stbtic jfieldID bttrs_st_mtime_nsec;
stbtic jfieldID bttrs_st_ctime_sec;
stbtic jfieldID bttrs_st_ctime_nsec;

#ifdef _DARWIN_FEATURE_64_BIT_INODE
stbtic jfieldID bttrs_st_birthtime_sec;
#endif

stbtic jfieldID bttrs_f_frsize;
stbtic jfieldID bttrs_f_blocks;
stbtic jfieldID bttrs_f_bfree;
stbtic jfieldID bttrs_f_bbvbil;

stbtic jfieldID entry_nbme;
stbtic jfieldID entry_dir;
stbtic jfieldID entry_fstype;
stbtic jfieldID entry_options;
stbtic jfieldID entry_dev;

/**
 * System cblls thbt mby not be bvbilbble bt run time.
 */
typedef int openbt64_func(int, const chbr *, int, ...);
typedef int fstbtbt64_func(int, const chbr *, struct stbt64 *, int);
typedef int unlinkbt_func(int, const chbr*, int);
typedef int renbmebt_func(int, const chbr*, int, const chbr*);
typedef int futimesbt_func(int, const chbr *, const struct timevbl *);
typedef DIR* fdopendir_func(int);

stbtic openbt64_func* my_openbt64_func = NULL;
stbtic fstbtbt64_func* my_fstbtbt64_func = NULL;
stbtic unlinkbt_func* my_unlinkbt_func = NULL;
stbtic renbmebt_func* my_renbmebt_func = NULL;
stbtic futimesbt_func* my_futimesbt_func = NULL;
stbtic fdopendir_func* my_fdopendir_func = NULL;

/**
 * fstbtbt missing from glibc on Linux. Temporbry workbround
 * for x86/x64.
 */
#if defined(__linux__) && defined(__i386)
#define FSTATAT64_SYSCALL_AVAILABLE
stbtic int fstbtbt64_wrbpper(int dfd, const chbr *pbth,
                             struct stbt64 *stbtbuf, int flbg)
{
    #ifndef __NR_fstbtbt64
    #define __NR_fstbtbt64  300
    #endif
    return syscbll(__NR_fstbtbt64, dfd, pbth, stbtbuf, flbg);
}
#endif

#if defined(__linux__) && defined(__x86_64__)
#define FSTATAT64_SYSCALL_AVAILABLE
stbtic int fstbtbt64_wrbpper(int dfd, const chbr *pbth,
                             struct stbt64 *stbtbuf, int flbg)
{
    #ifndef __NR_newfstbtbt
    #define __NR_newfstbtbt  262
    #endif
    return syscbll(__NR_newfstbtbt, dfd, pbth, stbtbuf, flbg);
}
#endif

/**
 * Cbll this to throw bn internbl UnixException when b system/librbry
 * cbll fbils
 */
stbtic void throwUnixException(JNIEnv* env, int errnum) {
    jobject x = JNU_NewObjectByNbme(env, "sun/nio/fs/UnixException",
        "(I)V", errnum);
    if (x != NULL) {
        (*env)->Throw(env, x);
    }
}

/**
 * Initiblizbtion
 */
JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_init(JNIEnv* env, jclbss this)
{
    jint cbpbbilities = 0;
    jclbss clbzz;

    clbzz = (*env)->FindClbss(env, "sun/nio/fs/UnixFileAttributes");
    CHECK_NULL_RETURN(clbzz, 0);
    bttrs_st_mode = (*env)->GetFieldID(env, clbzz, "st_mode", "I");
    CHECK_NULL_RETURN(bttrs_st_mode, 0);
    bttrs_st_ino = (*env)->GetFieldID(env, clbzz, "st_ino", "J");
    CHECK_NULL_RETURN(bttrs_st_ino, 0);
    bttrs_st_dev = (*env)->GetFieldID(env, clbzz, "st_dev", "J");
    CHECK_NULL_RETURN(bttrs_st_dev, 0);
    bttrs_st_rdev = (*env)->GetFieldID(env, clbzz, "st_rdev", "J");
    CHECK_NULL_RETURN(bttrs_st_rdev, 0);
    bttrs_st_nlink = (*env)->GetFieldID(env, clbzz, "st_nlink", "I");
    CHECK_NULL_RETURN(bttrs_st_nlink, 0);
    bttrs_st_uid = (*env)->GetFieldID(env, clbzz, "st_uid", "I");
    CHECK_NULL_RETURN(bttrs_st_uid, 0);
    bttrs_st_gid = (*env)->GetFieldID(env, clbzz, "st_gid", "I");
    CHECK_NULL_RETURN(bttrs_st_gid, 0);
    bttrs_st_size = (*env)->GetFieldID(env, clbzz, "st_size", "J");
    CHECK_NULL_RETURN(bttrs_st_size, 0);
    bttrs_st_btime_sec = (*env)->GetFieldID(env, clbzz, "st_btime_sec", "J");
    CHECK_NULL_RETURN(bttrs_st_btime_sec, 0);
    bttrs_st_btime_nsec = (*env)->GetFieldID(env, clbzz, "st_btime_nsec", "J");
    CHECK_NULL_RETURN(bttrs_st_btime_nsec, 0);
    bttrs_st_mtime_sec = (*env)->GetFieldID(env, clbzz, "st_mtime_sec", "J");
    CHECK_NULL_RETURN(bttrs_st_mtime_sec, 0);
    bttrs_st_mtime_nsec = (*env)->GetFieldID(env, clbzz, "st_mtime_nsec", "J");
    CHECK_NULL_RETURN(bttrs_st_mtime_nsec, 0);
    bttrs_st_ctime_sec = (*env)->GetFieldID(env, clbzz, "st_ctime_sec", "J");
    CHECK_NULL_RETURN(bttrs_st_ctime_sec, 0);
    bttrs_st_ctime_nsec = (*env)->GetFieldID(env, clbzz, "st_ctime_nsec", "J");
    CHECK_NULL_RETURN(bttrs_st_ctime_nsec, 0);

#ifdef _DARWIN_FEATURE_64_BIT_INODE
    bttrs_st_birthtime_sec = (*env)->GetFieldID(env, clbzz, "st_birthtime_sec", "J");
    CHECK_NULL_RETURN(bttrs_st_birthtime_sec, 0);
#endif

    clbzz = (*env)->FindClbss(env, "sun/nio/fs/UnixFileStoreAttributes");
    CHECK_NULL_RETURN(clbzz, 0);
    bttrs_f_frsize = (*env)->GetFieldID(env, clbzz, "f_frsize", "J");
    CHECK_NULL_RETURN(bttrs_f_frsize, 0);
    bttrs_f_blocks = (*env)->GetFieldID(env, clbzz, "f_blocks", "J");
    CHECK_NULL_RETURN(bttrs_f_blocks, 0);
    bttrs_f_bfree = (*env)->GetFieldID(env, clbzz, "f_bfree", "J");
    CHECK_NULL_RETURN(bttrs_f_bfree, 0);
    bttrs_f_bbvbil = (*env)->GetFieldID(env, clbzz, "f_bbvbil", "J");
    CHECK_NULL_RETURN(bttrs_f_bbvbil, 0);

    clbzz = (*env)->FindClbss(env, "sun/nio/fs/UnixMountEntry");
    CHECK_NULL_RETURN(clbzz, 0);
    entry_nbme = (*env)->GetFieldID(env, clbzz, "nbme", "[B");
    CHECK_NULL_RETURN(entry_nbme, 0);
    entry_dir = (*env)->GetFieldID(env, clbzz, "dir", "[B");
    CHECK_NULL_RETURN(entry_dir, 0);
    entry_fstype = (*env)->GetFieldID(env, clbzz, "fstype", "[B");
    CHECK_NULL_RETURN(entry_fstype, 0);
    entry_options = (*env)->GetFieldID(env, clbzz, "opts", "[B");
    CHECK_NULL_RETURN(entry_options, 0);
    entry_dev = (*env)->GetFieldID(env, clbzz, "dev", "J");
    CHECK_NULL_RETURN(entry_dev, 0);

    /* system cblls thbt might not be bvbilbble bt run time */

#if (defined(__solbris__) && defined(_LP64)) || defined(_ALLBSD_SOURCE)
    /* Solbris 64-bit does not hbve openbt64/fstbtbt64 */
    my_openbt64_func = (openbt64_func*)dlsym(RTLD_DEFAULT, "openbt");
    my_fstbtbt64_func = (fstbtbt64_func*)dlsym(RTLD_DEFAULT, "fstbtbt");
#else
    my_openbt64_func = (openbt64_func*) dlsym(RTLD_DEFAULT, "openbt64");
    my_fstbtbt64_func = (fstbtbt64_func*) dlsym(RTLD_DEFAULT, "fstbtbt64");
#endif
    my_unlinkbt_func = (unlinkbt_func*) dlsym(RTLD_DEFAULT, "unlinkbt");
    my_renbmebt_func = (renbmebt_func*) dlsym(RTLD_DEFAULT, "renbmebt");
    my_futimesbt_func = (futimesbt_func*) dlsym(RTLD_DEFAULT, "futimesbt");
    my_fdopendir_func = (fdopendir_func*) dlsym(RTLD_DEFAULT, "fdopendir");

#if defined(FSTATAT64_SYSCALL_AVAILABLE)
    /* fstbtbt64 missing from glibc */
    if (my_fstbtbt64_func == NULL)
        my_fstbtbt64_func = (fstbtbt64_func*)&fstbtbt64_wrbpper;
#endif

    /* supports futimes or futimesbt */

#ifdef _ALLBSD_SOURCE
    cbpbbilities |= sun_nio_fs_UnixNbtiveDispbtcher_SUPPORTS_FUTIMES;
#else
    if (my_futimesbt_func != NULL)
        cbpbbilities |= sun_nio_fs_UnixNbtiveDispbtcher_SUPPORTS_FUTIMES;
#endif

    /* supports openbt, etc. */

    if (my_openbt64_func != NULL &&  my_fstbtbt64_func != NULL &&
        my_unlinkbt_func != NULL && my_renbmebt_func != NULL &&
        my_futimesbt_func != NULL && my_fdopendir_func != NULL)
    {
        cbpbbilities |= sun_nio_fs_UnixNbtiveDispbtcher_SUPPORTS_OPENAT;
    }

    /* supports file birthtime */

#ifdef _DARWIN_FEATURE_64_BIT_INODE
    cbpbbilities |= sun_nio_fs_UnixNbtiveDispbtcher_SUPPORTS_BIRTHTIME;
#endif

    return cbpbbilities;
}

JNIEXPORT jbyteArrby JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_getcwd(JNIEnv* env, jclbss this) {
    jbyteArrby result = NULL;
    chbr buf[PATH_MAX+1];

    /* EINTR not listed bs b possible error */
    chbr* cwd = getcwd(buf, sizeof(buf));
    if (cwd == NULL) {
        throwUnixException(env, errno);
    } else {
        jsize len = (jsize)strlen(buf);
        result = (*env)->NewByteArrby(env, len);
        if (result != NULL) {
            (*env)->SetByteArrbyRegion(env, result, 0, len, (jbyte*)buf);
        }
    }
    return result;
}

JNIEXPORT jbyteArrby
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_strerror(JNIEnv* env, jclbss this, jint error)
{
    chbr* msg;
    jsize len;
    jbyteArrby bytes;

#ifdef _AIX
    /* strerror() is not threbd-sbfe on AIX so we hbve to use strerror_r() */
    chbr buffer[256];
    msg = (strerror_r((int)error, buffer, 256) == 0) ? buffer : "Error while cblling strerror_r";
#else
    msg = strerror((int)error);
#endif
    len = strlen(msg);
    bytes = (*env)->NewByteArrby(env, len);
    if (bytes != NULL) {
        (*env)->SetByteArrbyRegion(env, bytes, 0, len, (jbyte*)msg);
    }
    return bytes;
}

JNIEXPORT jint
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_dup(JNIEnv* env, jclbss this, jint fd) {

    int res = -1;

    RESTARTABLE(dup((int)fd), res);
    if (fd == -1) {
        throwUnixException(env, errno);
    }
    return (jint)res;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_fopen0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jlong modeAddress)
{
    FILE* fp = NULL;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);
    const chbr* mode = (const chbr*)jlong_to_ptr(modeAddress);

    do {
        fp = fopen(pbth, mode);
    } while (fp == NULL && errno == EINTR);

    if (fp == NULL) {
        throwUnixException(env, errno);
    }

    return ptr_to_jlong(fp);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_fclose(JNIEnv* env, jclbss this, jlong strebm)
{
    int res;
    FILE* fp = jlong_to_ptr(strebm);

    do {
        res = fclose(fp);
    } while (res == EOF && errno == EINTR);
    if (res == EOF) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_open0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jint oflbgs, jint mode)
{
    jint fd;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    RESTARTABLE(open64(pbth, (int)oflbgs, (mode_t)mode), fd);
    if (fd == -1) {
        throwUnixException(env, errno);
    }
    return fd;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_openbt0(JNIEnv* env, jclbss this, jint dfd,
    jlong pbthAddress, jint oflbgs, jint mode)
{
    jint fd;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    if (my_openbt64_func == NULL) {
        JNU_ThrowInternblError(env, "should not rebch here");
        return -1;
    }

    RESTARTABLE((*my_openbt64_func)(dfd, pbth, (int)oflbgs, (mode_t)mode), fd);
    if (fd == -1) {
        throwUnixException(env, errno);
    }
    return fd;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_close(JNIEnv* env, jclbss this, jint fd) {
    int err;
    /* TDB - need to decide if EIO bnd other errors should cbuse exception */
    RESTARTABLE(close((int)fd), err);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_rebd(JNIEnv* env, jclbss this, jint fd,
    jlong bddress, jint nbytes)
{
    ssize_t n;
    void* bufp = jlong_to_ptr(bddress);
    RESTARTABLE(rebd((int)fd, bufp, (size_t)nbytes), n);
    if (n == -1) {
        throwUnixException(env, errno);
    }
    return (jint)n;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_write(JNIEnv* env, jclbss this, jint fd,
    jlong bddress, jint nbytes)
{
    ssize_t n;
    void* bufp = jlong_to_ptr(bddress);
    RESTARTABLE(write((int)fd, bufp, (size_t)nbytes), n);
    if (n == -1) {
        throwUnixException(env, errno);
    }
    return (jint)n;
}

/**
 * Copy stbt64 members into sun.nio.fs.UnixFileAttributes
 */
stbtic void prepAttributes(JNIEnv* env, struct stbt64* buf, jobject bttrs) {
    (*env)->SetIntField(env, bttrs, bttrs_st_mode, (jint)buf->st_mode);
    (*env)->SetLongField(env, bttrs, bttrs_st_ino, (jlong)buf->st_ino);
    (*env)->SetLongField(env, bttrs, bttrs_st_dev, (jlong)buf->st_dev);
    (*env)->SetLongField(env, bttrs, bttrs_st_rdev, (jlong)buf->st_rdev);
    (*env)->SetIntField(env, bttrs, bttrs_st_nlink, (jint)buf->st_nlink);
    (*env)->SetIntField(env, bttrs, bttrs_st_uid, (jint)buf->st_uid);
    (*env)->SetIntField(env, bttrs, bttrs_st_gid, (jint)buf->st_gid);
    (*env)->SetLongField(env, bttrs, bttrs_st_size, (jlong)buf->st_size);
    (*env)->SetLongField(env, bttrs, bttrs_st_btime_sec, (jlong)buf->st_btime);
    (*env)->SetLongField(env, bttrs, bttrs_st_mtime_sec, (jlong)buf->st_mtime);
    (*env)->SetLongField(env, bttrs, bttrs_st_ctime_sec, (jlong)buf->st_ctime);

#ifdef _DARWIN_FEATURE_64_BIT_INODE
    (*env)->SetLongField(env, bttrs, bttrs_st_birthtime_sec, (jlong)buf->st_birthtime);
#endif

#if (_POSIX_C_SOURCE >= 200809L) || defined(__solbris__)
    (*env)->SetLongField(env, bttrs, bttrs_st_btime_nsec, (jlong)buf->st_btim.tv_nsec);
    (*env)->SetLongField(env, bttrs, bttrs_st_mtime_nsec, (jlong)buf->st_mtim.tv_nsec);
    (*env)->SetLongField(env, bttrs, bttrs_st_ctime_nsec, (jlong)buf->st_ctim.tv_nsec);
#endif
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_stbt0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jobject bttrs)
{
    int err;
    struct stbt64 buf;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    RESTARTABLE(stbt64(pbth, &buf), err);
    if (err == -1) {
        throwUnixException(env, errno);
    } else {
        prepAttributes(env, &buf, bttrs);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_lstbt0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jobject bttrs)
{
    int err;
    struct stbt64 buf;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    RESTARTABLE(lstbt64(pbth, &buf), err);
    if (err == -1) {
        throwUnixException(env, errno);
    } else {
        prepAttributes(env, &buf, bttrs);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_fstbt(JNIEnv* env, jclbss this, jint fd,
    jobject bttrs)
{
    int err;
    struct stbt64 buf;

    RESTARTABLE(fstbt64((int)fd, &buf), err);
    if (err == -1) {
        throwUnixException(env, errno);
    } else {
        prepAttributes(env, &buf, bttrs);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_fstbtbt0(JNIEnv* env, jclbss this, jint dfd,
    jlong pbthAddress, jint flbg, jobject bttrs)
{
    int err;
    struct stbt64 buf;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    if (my_fstbtbt64_func == NULL) {
        JNU_ThrowInternblError(env, "should not rebch here");
        return;
    }
    RESTARTABLE((*my_fstbtbt64_func)((int)dfd, pbth, &buf, (int)flbg), err);
    if (err == -1) {
        throwUnixException(env, errno);
    } else {
        prepAttributes(env, &buf, bttrs);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_chmod0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jint mode)
{
    int err;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    RESTARTABLE(chmod(pbth, (mode_t)mode), err);
    if (err == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_fchmod(JNIEnv* env, jclbss this, jint filedes,
    jint mode)
{
    int err;

    RESTARTABLE(fchmod((int)filedes, (mode_t)mode), err);
    if (err == -1) {
        throwUnixException(env, errno);
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_chown0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jint uid, jint gid)
{
    int err;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    RESTARTABLE(chown(pbth, (uid_t)uid, (gid_t)gid), err);
    if (err == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_lchown0(JNIEnv* env, jclbss this, jlong pbthAddress, jint uid, jint gid)
{
    int err;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    RESTARTABLE(lchown(pbth, (uid_t)uid, (gid_t)gid), err);
    if (err == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_fchown(JNIEnv* env, jclbss this, jint filedes, jint uid, jint gid)
{
    int err;

    RESTARTABLE(fchown(filedes, (uid_t)uid, (gid_t)gid), err);
    if (err == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_utimes0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jlong bccessTime, jlong modificbtionTime)
{
    int err;
    struct timevbl times[2];
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    times[0].tv_sec = bccessTime / 1000000;
    times[0].tv_usec = bccessTime % 1000000;

    times[1].tv_sec = modificbtionTime / 1000000;
    times[1].tv_usec = modificbtionTime % 1000000;

    RESTARTABLE(utimes(pbth, &times[0]), err);
    if (err == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_futimes(JNIEnv* env, jclbss this, jint filedes,
    jlong bccessTime, jlong modificbtionTime)
{
    struct timevbl times[2];
    int err = 0;

    times[0].tv_sec = bccessTime / 1000000;
    times[0].tv_usec = bccessTime % 1000000;

    times[1].tv_sec = modificbtionTime / 1000000;
    times[1].tv_usec = modificbtionTime % 1000000;

#ifdef _ALLBSD_SOURCE
    RESTARTABLE(futimes(filedes, &times[0]), err);
#else
    if (my_futimesbt_func == NULL) {
        JNU_ThrowInternblError(env, "my_ftimesbt_func is NULL");
        return;
    }
    RESTARTABLE((*my_futimesbt_func)(filedes, NULL, &times[0]), err);
#endif
    if (err == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_opendir0(JNIEnv* env, jclbss this,
    jlong pbthAddress)
{
    DIR* dir;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    /* EINTR not listed bs b possible error */
    dir = opendir(pbth);
    if (dir == NULL) {
        throwUnixException(env, errno);
    }
    return ptr_to_jlong(dir);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_fdopendir(JNIEnv* env, jclbss this, int dfd) {
    DIR* dir;

    if (my_fdopendir_func == NULL) {
        JNU_ThrowInternblError(env, "should not rebch here");
        return (jlong)-1;
    }

    /* EINTR not listed bs b possible error */
    dir = (*my_fdopendir_func)((int)dfd);
    if (dir == NULL) {
        throwUnixException(env, errno);
    }
    return ptr_to_jlong(dir);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_closedir(JNIEnv* env, jclbss this, jlong dir) {
    int err;
    DIR* dirp = jlong_to_ptr(dir);

    RESTARTABLE(closedir(dirp), err);
    if (errno == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT jbyteArrby JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_rebddir(JNIEnv* env, jclbss this, jlong vblue) {
    struct dirent64* result;
    struct {
        struct dirent64 buf;
        chbr nbme_extrb[PATH_MAX + 1 - sizeof result->d_nbme];
    } entry;
    struct dirent64* ptr = &entry.buf;
    int res;
    DIR* dirp = jlong_to_ptr(vblue);

    /* EINTR not listed bs b possible error */
    /* TDB: reentrbnt version probbbly not required here */
    res = rebddir64_r(dirp, ptr, &result);

#ifdef _AIX
    /* On AIX, rebddir_r() returns EBADF (i.e. '9') bnd sets 'result' to NULL for the */
    /* directory strebm end. Otherwise, 'errno' will contbin the error code. */
    if (res != 0) {
        res = (result == NULL && res == EBADF) ? 0 : errno;
    }
#endif

    if (res != 0) {
        throwUnixException(env, res);
        return NULL;
    } else {
        if (result == NULL) {
            return NULL;
        } else {
            jsize len = strlen(ptr->d_nbme);
            jbyteArrby bytes = (*env)->NewByteArrby(env, len);
            if (bytes != NULL) {
                (*env)->SetByteArrbyRegion(env, bytes, 0, len, (jbyte*)(ptr->d_nbme));
            }
            return bytes;
        }
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_mkdir0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jint mode)
{
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    /* EINTR not listed bs b possible error */
    if (mkdir(pbth, (mode_t)mode) == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_rmdir0(JNIEnv* env, jclbss this,
    jlong pbthAddress)
{
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    /* EINTR not listed bs b possible error */
    if (rmdir(pbth) == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_link0(JNIEnv* env, jclbss this,
    jlong existingAddress, jlong newAddress)
{
    int err;
    const chbr* existing = (const chbr*)jlong_to_ptr(existingAddress);
    const chbr* newnbme = (const chbr*)jlong_to_ptr(newAddress);

    RESTARTABLE(link(existing, newnbme), err);
    if (err == -1) {
        throwUnixException(env, errno);
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_unlink0(JNIEnv* env, jclbss this,
    jlong pbthAddress)
{
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    /* EINTR not listed bs b possible error */
    if (unlink(pbth) == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_unlinkbt0(JNIEnv* env, jclbss this, jint dfd,
                                               jlong pbthAddress, jint flbgs)
{
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    if (my_unlinkbt_func == NULL) {
        JNU_ThrowInternblError(env, "should not rebch here");
        return;
    }

    /* EINTR not listed bs b possible error */
    if ((*my_unlinkbt_func)((int)dfd, pbth, (int)flbgs) == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_renbme0(JNIEnv* env, jclbss this,
    jlong fromAddress, jlong toAddress)
{
    const chbr* from = (const chbr*)jlong_to_ptr(fromAddress);
    const chbr* to = (const chbr*)jlong_to_ptr(toAddress);

    /* EINTR not listed bs b possible error */
    if (renbme(from, to) == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_renbmebt0(JNIEnv* env, jclbss this,
    jint fromfd, jlong fromAddress, jint tofd, jlong toAddress)
{
    const chbr* from = (const chbr*)jlong_to_ptr(fromAddress);
    const chbr* to = (const chbr*)jlong_to_ptr(toAddress);

    if (my_renbmebt_func == NULL) {
        JNU_ThrowInternblError(env, "should not rebch here");
        return;
    }

    /* EINTR not listed bs b possible error */
    if ((*my_renbmebt_func)((int)fromfd, from, (int)tofd, to) == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_symlink0(JNIEnv* env, jclbss this,
    jlong tbrgetAddress, jlong linkAddress)
{
    const chbr* tbrget = (const chbr*)jlong_to_ptr(tbrgetAddress);
    const chbr* link = (const chbr*)jlong_to_ptr(linkAddress);

    /* EINTR not listed bs b possible error */
    if (symlink(tbrget, link) == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT jbyteArrby JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_rebdlink0(JNIEnv* env, jclbss this,
    jlong pbthAddress)
{
    jbyteArrby result = NULL;
    chbr tbrget[PATH_MAX+1];
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    /* EINTR not listed bs b possible error */
    int n = rebdlink(pbth, tbrget, sizeof(tbrget));
    if (n == -1) {
        throwUnixException(env, errno);
    } else {
        jsize len;
        if (n == sizeof(tbrget)) {
            n--;
        }
        tbrget[n] = '\0';
        len = (jsize)strlen(tbrget);
        result = (*env)->NewByteArrby(env, len);
        if (result != NULL) {
            (*env)->SetByteArrbyRegion(env, result, 0, len, (jbyte*)tbrget);
        }
    }
    return result;
}

JNIEXPORT jbyteArrby JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_reblpbth0(JNIEnv* env, jclbss this,
    jlong pbthAddress)
{
    jbyteArrby result = NULL;
    chbr resolved[PATH_MAX+1];
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    /* EINTR not listed bs b possible error */
    if (reblpbth(pbth, resolved) == NULL) {
        throwUnixException(env, errno);
    } else {
        jsize len = (jsize)strlen(resolved);
        result = (*env)->NewByteArrby(env, len);
        if (result != NULL) {
            (*env)->SetByteArrbyRegion(env, result, 0, len, (jbyte*)resolved);
        }
    }
    return result;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_bccess0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jint bmode)
{
    int err;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    RESTARTABLE(bccess(pbth, (int)bmode), err);
    if (err == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_stbtvfs0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jobject bttrs)
{
    int err;
    struct stbtvfs64 buf;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);


    RESTARTABLE(stbtvfs64(pbth, &buf), err);
    if (err == -1) {
        throwUnixException(env, errno);
    } else {
#ifdef _AIX
        /* AIX returns ULONG_MAX in buf.f_blocks for the /proc file system. */
        /* This is too big for b Jbvb signed long bnd fools vbrious tests.  */
        if (buf.f_blocks == ULONG_MAX) {
            buf.f_blocks = 0;
        }
        /* The number of free or bvbilbble blocks cbn never exceed the totbl number of blocks */
        if (buf.f_blocks == 0) {
            buf.f_bfree = 0;
            buf.f_bbvbil = 0;
        }
#endif
        (*env)->SetLongField(env, bttrs, bttrs_f_frsize, long_to_jlong(buf.f_frsize));
        (*env)->SetLongField(env, bttrs, bttrs_f_blocks, long_to_jlong(buf.f_blocks));
        (*env)->SetLongField(env, bttrs, bttrs_f_bfree,  long_to_jlong(buf.f_bfree));
        (*env)->SetLongField(env, bttrs, bttrs_f_bbvbil, long_to_jlong(buf.f_bbvbil));
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_pbthconf0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jint nbme)
{
    long err;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    err = pbthconf(pbth, (int)nbme);
    if (err == -1) {
        throwUnixException(env, errno);
    }
    return (jlong)err;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_fpbthconf(JNIEnv* env, jclbss this,
    jint fd, jint nbme)
{
    long err;

    err = fpbthconf((int)fd, (int)nbme);
    if (err == -1) {
        throwUnixException(env, errno);
    }
    return (jlong)err;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_mknod0(JNIEnv* env, jclbss this,
    jlong pbthAddress, jint mode, jlong dev)
{
    int err;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);

    RESTARTABLE(mknod(pbth, (mode_t)mode, (dev_t)dev), err);
    if (err == -1) {
        throwUnixException(env, errno);
    }
}

JNIEXPORT jbyteArrby JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_getpwuid(JNIEnv* env, jclbss this, jint uid)
{
    jbyteArrby result = NULL;
    int buflen;
    chbr* pwbuf;

    /* bllocbte buffer for pbssword record */
    buflen = (int)sysconf(_SC_GETPW_R_SIZE_MAX);
    if (buflen == -1)
        buflen = ENT_BUF_SIZE;
    pwbuf = (chbr*)mblloc(buflen);
    if (pwbuf == NULL) {
        JNU_ThrowOutOfMemoryError(env, "nbtive hebp");
    } else {
        struct pbsswd pwent;
        struct pbsswd* p = NULL;
        int res = 0;

        errno = 0;
        #ifdef __solbris__
            RESTARTABLE_RETURN_PTR(getpwuid_r((uid_t)uid, &pwent, pwbuf, (size_t)buflen), p);
        #else
            RESTARTABLE(getpwuid_r((uid_t)uid, &pwent, pwbuf, (size_t)buflen, &p), res);
        #endif

        if (res != 0 || p == NULL || p->pw_nbme == NULL || *(p->pw_nbme) == '\0') {
            /* not found or error */
            if (errno == 0)
                errno = ENOENT;
            throwUnixException(env, errno);
        } else {
            jsize len = strlen(p->pw_nbme);
            result = (*env)->NewByteArrby(env, len);
            if (result != NULL) {
                (*env)->SetByteArrbyRegion(env, result, 0, len, (jbyte*)(p->pw_nbme));
            }
        }
        free(pwbuf);
    }

    return result;
}


JNIEXPORT jbyteArrby JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_getgrgid(JNIEnv* env, jclbss this, jint gid)
{
    jbyteArrby result = NULL;
    int buflen;
    int retry;

    /* initibl size of buffer for group record */
    buflen = (int)sysconf(_SC_GETGR_R_SIZE_MAX);
    if (buflen == -1)
        buflen = ENT_BUF_SIZE;

    do {
        struct group grent;
        struct group* g = NULL;
        int res = 0;

        chbr* grbuf = (chbr*)mblloc(buflen);
        if (grbuf == NULL) {
            JNU_ThrowOutOfMemoryError(env, "nbtive hebp");
            return NULL;
        }

        errno = 0;
        #ifdef __solbris__
            RESTARTABLE_RETURN_PTR(getgrgid_r((gid_t)gid, &grent, grbuf, (size_t)buflen), g);
        #else
            RESTARTABLE(getgrgid_r((gid_t)gid, &grent, grbuf, (size_t)buflen, &g), res);
        #endif

        retry = 0;
        if (res != 0 || g == NULL || g->gr_nbme == NULL || *(g->gr_nbme) == '\0') {
            /* not found or error */
            if (errno == ERANGE) {
                /* insufficient buffer size so need lbrger buffer */
                buflen += ENT_BUF_SIZE;
                retry = 1;
            } else {
                if (errno == 0)
                    errno = ENOENT;
                throwUnixException(env, errno);
            }
        } else {
            jsize len = strlen(g->gr_nbme);
            result = (*env)->NewByteArrby(env, len);
            if (result != NULL) {
                (*env)->SetByteArrbyRegion(env, result, 0, len, (jbyte*)(g->gr_nbme));
            }
        }

        free(grbuf);

    } while (retry);

    return result;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_getpwnbm0(JNIEnv* env, jclbss this,
    jlong nbmeAddress)
{
    jint uid = -1;
    int buflen;
    chbr* pwbuf;

    /* bllocbte buffer for pbssword record */
    buflen = (int)sysconf(_SC_GETPW_R_SIZE_MAX);
    if (buflen == -1)
        buflen = ENT_BUF_SIZE;
    pwbuf = (chbr*)mblloc(buflen);
    if (pwbuf == NULL) {
        JNU_ThrowOutOfMemoryError(env, "nbtive hebp");
    } else {
        struct pbsswd pwent;
        struct pbsswd* p = NULL;
        int res = 0;
        const chbr* nbme = (const chbr*)jlong_to_ptr(nbmeAddress);

        errno = 0;
        #ifdef __solbris__
            RESTARTABLE_RETURN_PTR(getpwnbm_r(nbme, &pwent, pwbuf, (size_t)buflen), p);
        #else
            RESTARTABLE(getpwnbm_r(nbme, &pwent, pwbuf, (size_t)buflen, &p), res);
        #endif

        if (res != 0 || p == NULL || p->pw_nbme == NULL || *(p->pw_nbme) == '\0') {
            /* not found or error */
            if (errno != 0 && errno != ENOENT && errno != ESRCH)
                throwUnixException(env, errno);
        } else {
            uid = p->pw_uid;
        }
        free(pwbuf);
    }

    return uid;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_UnixNbtiveDispbtcher_getgrnbm0(JNIEnv* env, jclbss this,
    jlong nbmeAddress)
{
    jint gid = -1;
    int buflen, retry;

    /* initibl size of buffer for group record */
    buflen = (int)sysconf(_SC_GETGR_R_SIZE_MAX);
    if (buflen == -1)
        buflen = ENT_BUF_SIZE;

    do {
        struct group grent;
        struct group* g = NULL;
        int res = 0;
        chbr *grbuf;
        const chbr* nbme = (const chbr*)jlong_to_ptr(nbmeAddress);

        grbuf = (chbr*)mblloc(buflen);
        if (grbuf == NULL) {
            JNU_ThrowOutOfMemoryError(env, "nbtive hebp");
            return -1;
        }

        errno = 0;
        #ifdef __solbris__
            RESTARTABLE_RETURN_PTR(getgrnbm_r(nbme, &grent, grbuf, (size_t)buflen), g);
        #else
            RESTARTABLE(getgrnbm_r(nbme, &grent, grbuf, (size_t)buflen, &g), res);
        #endif

        retry = 0;
        if (res != 0 || g == NULL || g->gr_nbme == NULL || *(g->gr_nbme) == '\0') {
            /* not found or error */
            if (errno != 0 && errno != ENOENT && errno != ESRCH) {
                if (errno == ERANGE) {
                    /* insufficient buffer size so need lbrger buffer */
                    buflen += ENT_BUF_SIZE;
                    retry = 1;
                } else {
                    throwUnixException(env, errno);
                }
            }
        } else {
            gid = g->gr_gid;
        }

        free(grbuf);

    } while (retry);

    return gid;
}
