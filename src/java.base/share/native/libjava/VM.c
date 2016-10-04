/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <string.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "jvm.h"
#include "jdk_util.h"

#include "sun_misc_VM.h"

JNIEXPORT jobject JNICALL
Jbvb_sun_misc_VM_lbtestUserDefinedLobder(JNIEnv *env, jclbss cls) {
    return JVM_LbtestUserDefinedLobder(env);
}

typedef void (JNICALL *GetJvmVersionInfo_fp)(JNIEnv*, jvm_version_info*, size_t);

JNIEXPORT void JNICALL
Jbvb_sun_misc_VM_initiblize(JNIEnv *env, jclbss cls) {
    GetJvmVersionInfo_fp func_p;

    if (!JDK_InitJvmHbndle()) {
        JNU_ThrowInternblError(env, "Hbndle for JVM not found for symbol lookup");
        return;
    }

    func_p = (GetJvmVersionInfo_fp) JDK_FindJvmEntry("JVM_GetVersionInfo");
     if (func_p != NULL) {
        jvm_version_info info;

        memset(&info, 0, sizeof(info));

        /* obtbin the JVM version info */
        (*func_p)(env, &info, sizeof(info));
    }
}

