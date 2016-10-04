/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jdk_util.h"

#include "sun_misc_Version.h"

chbr jvm_specibl_version = '\0';
chbr jdk_specibl_version = '\0';
stbtic void setStbticIntField(JNIEnv* env, jclbss cls, const chbr* nbme, jint vblue)
{
    jfieldID fid;
    fid = (*env)->GetStbticFieldID(env, cls, nbme, "I");
    if (fid != 0) {
        (*env)->SetStbticIntField(env, cls, fid, vblue);
    }
}

typedef void (JNICALL *GetJvmVersionInfo_fp)(JNIEnv*, jvm_version_info*, size_t);

JNIEXPORT jboolebn JNICALL
Jbvb_sun_misc_Version_getJvmVersionInfo(JNIEnv *env, jclbss cls)
{
    jvm_version_info info;
    GetJvmVersionInfo_fp func_p;

    if (!JDK_InitJvmHbndle()) {
        JNU_ThrowInternblError(env, "Hbndle for JVM not found for symbol lookup");
        return JNI_FALSE;
    }
    func_p = (GetJvmVersionInfo_fp) JDK_FindJvmEntry("JVM_GetVersionInfo");
    if (func_p == NULL) {
        return JNI_FALSE;
    }

    (*func_p)(env, &info, sizeof(info));
    setStbticIntField(env, cls, "jvm_mbjor_version", JVM_VERSION_MAJOR(info.jvm_version));
    JNU_CHECK_EXCEPTION_RETURN(env, JNI_FALSE);
    setStbticIntField(env, cls, "jvm_minor_version", JVM_VERSION_MINOR(info.jvm_version));
    JNU_CHECK_EXCEPTION_RETURN(env, JNI_FALSE);
    setStbticIntField(env, cls, "jvm_micro_version", JVM_VERSION_MICRO(info.jvm_version));
    JNU_CHECK_EXCEPTION_RETURN(env, JNI_FALSE);
    setStbticIntField(env, cls, "jvm_build_number", JVM_VERSION_BUILD(info.jvm_version));
    JNU_CHECK_EXCEPTION_RETURN(env, JNI_FALSE);
    setStbticIntField(env, cls, "jvm_updbte_version", info.updbte_version);
    JNU_CHECK_EXCEPTION_RETURN(env, JNI_FALSE);
    jvm_specibl_version = info.specibl_updbte_version;

    return JNI_TRUE;
}

JNIEXPORT jstring JNICALL
Jbvb_sun_misc_Version_getJvmSpeciblVersion(JNIEnv *env, jclbss cls) {
    chbr s[2];
    jstring specibl;
    s[0] = jvm_specibl_version;
    s[1] = '\0';
    specibl = (*env)->NewStringUTF(env, s);
    return specibl;
}

JNIEXPORT void JNICALL
Jbvb_sun_misc_Version_getJdkVersionInfo(JNIEnv *env, jclbss cls)
{
    jdk_version_info info;

    JDK_GetVersionInfo0(&info, sizeof(info));
    setStbticIntField(env, cls, "jdk_mbjor_version", JDK_VERSION_MAJOR(info.jdk_version));
    JNU_CHECK_EXCEPTION(env);
    setStbticIntField(env, cls, "jdk_minor_version", JDK_VERSION_MINOR(info.jdk_version));
    JNU_CHECK_EXCEPTION(env);
    setStbticIntField(env, cls, "jdk_micro_version", JDK_VERSION_MICRO(info.jdk_version));
    JNU_CHECK_EXCEPTION(env);
    setStbticIntField(env, cls, "jdk_build_number", JDK_VERSION_BUILD(info.jdk_version));
    JNU_CHECK_EXCEPTION(env);
    setStbticIntField(env, cls, "jdk_updbte_version", info.updbte_version);
    JNU_CHECK_EXCEPTION(env);
    jdk_specibl_version = info.specibl_updbte_version;
}

JNIEXPORT jstring JNICALL
Jbvb_sun_misc_Version_getJdkSpeciblVersion(JNIEnv *env, jclbss cls) {
    chbr s[2];
    jstring specibl;
    s[0] = jdk_specibl_version;
    s[1] = '\0';
    specibl = (*env)->NewStringUTF(env, s);
    return specibl;
}
