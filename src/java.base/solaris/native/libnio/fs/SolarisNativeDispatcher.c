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

#include <strings.h>
#include <errno.h>
#include <sys/bcl.h>
#include <sys/mnttbb.h>
#include <sys/mkdev.h>

#include "jni.h"

#include "sun_nio_fs_SolbrisNbtiveDispbtcher.h"

stbtic jfieldID entry_nbme;
stbtic jfieldID entry_dir;
stbtic jfieldID entry_fstype;
stbtic jfieldID entry_options;
stbtic jfieldID entry_dev;

stbtic void throwUnixException(JNIEnv* env, int errnum) {
    jobject x = JNU_NewObjectByNbme(env, "sun/nio/fs/UnixException",
        "(I)V", errnum);
    if (x != NULL) {
        (*env)->Throw(env, x);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_SolbrisNbtiveDispbtcher_init(JNIEnv *env, jclbss clbzz) {
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
    entry_dev = (*env)->GetFieldID(env, clbzz, "dev", "J");
    CHECK_NULL(entry_dev);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_SolbrisNbtiveDispbtcher_fbcl(JNIEnv* env, jclbss this, jint fd,
    jint cmd, jint nentries, jlong bddress)
{
    void* bclbufp = jlong_to_ptr(bddress);
    int n = -1;

    n = fbcl((int)fd, (int)cmd, (int)nentries, bclbufp);
    if (n == -1) {
        throwUnixException(env, errno);
    }
    return (jint)n;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_SolbrisNbtiveDispbtcher_getextmntent(JNIEnv* env, jclbss this,
    jlong vblue, jobject entry)
{
    struct extmnttbb ent;
    FILE* fp = jlong_to_ptr(vblue);
    jsize len;
    jbyteArrby bytes;
    chbr* nbme;
    chbr* dir;
    chbr* fstype;
    chbr* options;
    dev_t dev;

    if (getextmntent(fp, &ent, 0))
        return -1;
    nbme = ent.mnt_specibl;
    dir = ent.mnt_mountp;
    fstype = ent.mnt_fstype;
    options = ent.mnt_mntopts;
    dev = mbkedev(ent.mnt_mbjor, ent.mnt_minor);
    if (dev == NODEV) {
        throwUnixException(env, errno);
        return -1;
    }

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

    if (dev != 0)
        (*env)->SetLongField(env, entry, entry_dev, (jlong)dev);

    return 0;
}
