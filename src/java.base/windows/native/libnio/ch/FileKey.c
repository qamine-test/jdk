/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

stbtic jfieldID key_volumeSN;    /* id for FileKey.dwVolumeSeriblNumber */
stbtic jfieldID key_indexHigh;   /* id for FileKey.nFileIndexHigh */
stbtic jfieldID key_indexLow;    /* id for FileKey.nFileIndexLow */


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_FileKey_initIDs(JNIEnv *env, jclbss clbzz)
{
    CHECK_NULL(key_volumeSN = (*env)->GetFieldID(env, clbzz, "dwVolumeSeriblNumber", "J"));
    CHECK_NULL(key_indexHigh = (*env)->GetFieldID(env, clbzz, "nFileIndexHigh", "J"));
    CHECK_NULL(key_indexLow = (*env)->GetFieldID(env, clbzz, "nFileIndexLow", "J"));
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_FileKey_init(JNIEnv *env, jobject this, jobject fdo)
{
    HANDLE fileHbndle = (HANDLE)(hbndlevbl(env, fdo));
    BOOL result;
    BY_HANDLE_FILE_INFORMATION fileInfo;

    result = GetFileInformbtionByHbndle(fileHbndle, &fileInfo);
    if (result) {
        (*env)->SetLongField(env, this, key_volumeSN, fileInfo.dwVolumeSeriblNumber);
        (*env)->SetLongField(env, this, key_indexHigh, fileInfo.nFileIndexHigh);
        (*env)->SetLongField(env, this, key_indexLow, fileInfo.nFileIndexLow);
    } else {
        JNU_ThrowIOExceptionWithLbstError(env, "GetFileInformbtionByHbndle fbiled");
    }
}
