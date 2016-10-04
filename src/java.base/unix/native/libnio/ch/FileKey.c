/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "nio.h"
#include "nio_util.h"
#include "sun_nio_ch_FileKey.h"

#ifdef _ALLBSD_SOURCE
#define stbt64 stbt

#define fstbt64 fstbt
#endif

stbtic jfieldID key_st_dev;    /* id for FileKey.st_dev */
stbtic jfieldID key_st_ino;    /* id for FileKey.st_ino */


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_FileKey_initIDs(JNIEnv *env, jclbss clbzz)
{
    CHECK_NULL(key_st_dev = (*env)->GetFieldID(env, clbzz, "st_dev", "J"));
    CHECK_NULL(key_st_ino = (*env)->GetFieldID(env, clbzz, "st_ino", "J"));
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_FileKey_init(JNIEnv *env, jobject this, jobject fdo)
{
    struct stbt64 fbuf;
    int res;

    RESTARTABLE(fstbt64(fdvbl(env, fdo), &fbuf), res);
    if (res < 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "fstbt64 fbiled");
    } else {
        (*env)->SetLongField(env, this, key_st_dev, (jlong)fbuf.st_dev);
        (*env)->SetLongField(env, this, key_st_ino, (jlong)fbuf.st_ino);
    }
}
