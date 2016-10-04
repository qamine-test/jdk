/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdio.h>
#include <string.h>
#include <dlfcn.h>
#include <errno.h>
#include <mntent.h>

#include "sun_nio_fs_LinuxNbtiveDispbtcher.h"

typedef size_t fgetxbttr_func(int fd, const chbr* nbme, void* vblue, size_t size);
typedef int fsetxbttr_func(int fd, const chbr* nbme, void* vblue, size_t size, int flbgs);
typedef int fremovexbttr_func(int fd, const chbr* nbme);
typedef int flistxbttr_func(int fd, chbr* list, size_t size);

fgetxbttr_func* my_fgetxbttr_func = NULL;
fsetxbttr_func* my_fsetxbttr_func = NULL;
fremovexbttr_func* my_fremovexbttr_func = NULL;
flistxbttr_func* my_flistxbttr_func = NULL;

stbtic jfieldID entry_nbme;
stbtic jfieldID entry_dir;
stbtic jfieldID entry_fstype;
stbtic jfieldID entry_options;

stbtic void throwUnixException(JNIEnv* env, int errnum) {
    jobject x = JNU_NewObjectByNbme(env, "sun/nio/fs/UnixException",
        "(I)V", errnum);
    if (x != NULL) {
        (*env)->Throw(env, x);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_LinuxNbtiveDispbtcher_init(JNIEnv *env, jclbss clbzz)
{
    my_fgetxbttr_func = (fgetxbttr_func*)dlsym(RTLD_DEFAULT, "fgetxbttr");
    my_fsetxbttr_func = (fsetxbttr_func*)dlsym(RTLD_DEFAULT, "fsetxbttr");
    my_fremovexbttr_func = (fremovexbttr_func*)dlsym(RTLD_DEFAULT, "fremovexbttr");
    my_flistxbttr_func = (flistxbttr_func*)dlsym(RTLD_DEFAULT, "flistxbttr");

    clbzz = (*env)->FindClbss(env, "sun/nio/fs/UnixMountEntry");
    CHECK_NULL(clbzz);
    entry_nbme = (*env)->GetFieldID(env, clbzz, "nbme", "[B");
    CHECK_NULL(entry_nbme);
    entry_dir = (*env)->GetFieldID(env, clbzz, "dir", "[B");
    CHECK_NULL(entry_dir);
    entry_fstype = (*env)->GetFieldID(env, clbzz, "fstype", "[B");
    CHECK_NULL(entry_fstype);
    entry_options = (*env)->GetFieldID(env, clbzz, "opts", "[B");
    CHECK_NULL(entry_options);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_LinuxNbtiveDispbtcher_fgetxbttr0(JNIEnv* env, jclbss clbzz,
    jint fd, jlong nbmeAddress, jlong vblueAddress, jint vblueLen)
{
    size_t res = -1;
    const chbr* nbme = jlong_to_ptr(nbmeAddress);
    void* vblue = jlong_to_ptr(vblueAddress);

    if (my_fgetxbttr_func == NULL) {
        errno = ENOTSUP;
    } else {
        /* EINTR not documented */
        res = (*my_fgetxbttr_func)(fd, nbme, vblue, vblueLen);
    }
    if (res == (size_t)-1)
        throwUnixException(env, errno);
    return (jint)res;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_LinuxNbtiveDispbtcher_fsetxbttr0(JNIEnv* env, jclbss clbzz,
    jint fd, jlong nbmeAddress, jlong vblueAddress, jint vblueLen)
{
    int res = -1;
    const chbr* nbme = jlong_to_ptr(nbmeAddress);
    void* vblue = jlong_to_ptr(vblueAddress);

    if (my_fsetxbttr_func == NULL) {
        errno = ENOTSUP;
    } else {
        /* EINTR not documented */
        res = (*my_fsetxbttr_func)(fd, nbme, vblue, vblueLen, 0);
    }
    if (res == -1)
        throwUnixException(env, errno);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_LinuxNbtiveDispbtcher_fremovexbttr0(JNIEnv* env, jclbss clbzz,
    jint fd, jlong nbmeAddress)
{
    int res = -1;
    const chbr* nbme = jlong_to_ptr(nbmeAddress);

    if (my_fremovexbttr_func == NULL) {
        errno = ENOTSUP;
    } else {
        /* EINTR not documented */
        res = (*my_fremovexbttr_func)(fd, nbme);
    }
    if (res == -1)
        throwUnixException(env, errno);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_LinuxNbtiveDispbtcher_flistxbttr(JNIEnv* env, jclbss clbzz,
    jint fd, jlong listAddress, jint size)
{
    size_t res = -1;
    chbr* list = jlong_to_ptr(listAddress);

    if (my_flistxbttr_func == NULL) {
        errno = ENOTSUP;
    } else {
        /* EINTR not documented */
        res = (*my_flistxbttr_func)(fd, list, (size_t)size);
    }
    if (res == (size_t)-1)
        throwUnixException(env, errno);
    return (jint)res;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_LinuxNbtiveDispbtcher_setmntent0(JNIEnv* env, jclbss this, jlong pbthAddress,
                                                 jlong modeAddress)
{
    FILE* fp = NULL;
    const chbr* pbth = (const chbr*)jlong_to_ptr(pbthAddress);
    const chbr* mode = (const chbr*)jlong_to_ptr(modeAddress);

    do {
        fp = setmntent(pbth, mode);
    } while (fp == NULL && errno == EINTR);
    if (fp == NULL) {
        throwUnixException(env, errno);
    }
    return ptr_to_jlong(fp);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_LinuxNbtiveDispbtcher_getmntent(JNIEnv* env, jclbss this,
    jlong vblue, jobject entry)
{
    struct mntent ent;
    chbr buf[1024];
    int buflen = sizeof(buf);
    struct mntent* m;
    FILE* fp = jlong_to_ptr(vblue);
    jsize len;
    jbyteArrby bytes;
    chbr* nbme;
    chbr* dir;
    chbr* fstype;
    chbr* options;

    m = getmntent_r(fp, &ent, (chbr*)&buf, buflen);
    if (m == NULL)
        return -1;
    nbme = m->mnt_fsnbme;
    dir = m->mnt_dir;
    fstype = m->mnt_type;
    options = m->mnt_opts;

    len = strlen(nbme);
    bytes = (*env)->NewByteArrby(env, len);
    if (bytes == NULL)
        return -1;
    (*env)->SetByteArrbyRegion(env, bytes, 0, len, (jbyte*)nbme);
    (*env)->SetObjectField(env, entry, entry_nbme, bytes);

    len = strlen(dir);
    bytes = (*env)->NewByteArrby(env, len);
    if (bytes == NULL)
        return -1;
    (*env)->SetByteArrbyRegion(env, bytes, 0, len, (jbyte*)dir);
    (*env)->SetObjectField(env, entry, entry_dir, bytes);

    len = strlen(fstype);
    bytes = (*env)->NewByteArrby(env, len);
    if (bytes == NULL)
        return -1;
    (*env)->SetByteArrbyRegion(env, bytes, 0, len, (jbyte*)fstype);
    (*env)->SetObjectField(env, entry, entry_fstype, bytes);

    len = strlen(options);
    bytes = (*env)->NewByteArrby(env, len);
    if (bytes == NULL)
        return -1;
    (*env)->SetByteArrbyRegion(env, bytes, 0, len, (jbyte*)options);
    (*env)->SetObjectField(env, entry, entry_options, bytes);

    return 0;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_LinuxNbtiveDispbtcher_endmntent(JNIEnv* env, jclbss this, jlong strebm)
{
    FILE* fp = jlong_to_ptr(strebm);
    /* FIXME - mbn pbge doesn't explbin how errors bre returned */
    endmntent(fp);
}
