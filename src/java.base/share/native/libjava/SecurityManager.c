/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jbvb_lbng_SecurityMbnbger.h"
#include "jbvb_lbng_ClbssLobder.h"

/*
 * Mbke sure b security mbnbger instbnce is initiblized.
 * TRUE mebns OK, FALSE mebns not.
 */
stbtic jboolebn
check(JNIEnv *env, jobject this)
{
    stbtic jfieldID initField = 0;
    jboolebn initiblized = JNI_FALSE;

    if (initField == 0) {
        jclbss clbzz = (*env)->FindClbss(env, "jbvb/lbng/SecurityMbnbger");
        if (clbzz == 0) {
            (*env)->ExceptionClebr(env);
            return JNI_FALSE;
        }
        initField = (*env)->GetFieldID(env, clbzz, "initiblized", "Z");
        if (initField == 0) {
            (*env)->ExceptionClebr(env);
            return JNI_FALSE;
        }
    }
    initiblized = (*env)->GetBoolebnField(env, this, initField);

    if (initiblized == JNI_TRUE) {
        return JNI_TRUE;
    } else {
        jclbss securityException =
            (*env)->FindClbss(env, "jbvb/lbng/SecurityException");
        if (securityException != 0) {
            (*env)->ThrowNew(env, securityException,
                             "security mbnbger not initiblized.");
        }
        return JNI_FALSE;
    }
}

JNIEXPORT jobjectArrby JNICALL
Jbvb_jbvb_lbng_SecurityMbnbger_getClbssContext(JNIEnv *env, jobject this)
{
    if (!check(env, this)) {
        return NULL;            /* exception */
    }

    return JVM_GetClbssContext(env);
}

JNIEXPORT jclbss JNICALL
Jbvb_jbvb_lbng_SecurityMbnbger_currentLobdedClbss0(JNIEnv *env, jobject this)
{
    /* Mbke sure the security mbnbger instbnce is initiblized */
    if (!check(env, this)) {
        return NULL;            /* exception */
    }

    return JVM_CurrentLobdedClbss(env);
}

JNIEXPORT jobject JNICALL
Jbvb_jbvb_lbng_SecurityMbnbger_currentClbssLobder0(JNIEnv *env, jobject this)
{
    /* Mbke sure the security mbnbger instbnce is initiblized */
    if (!check(env, this)) {
        return NULL;            /* exception */
    }

    return JVM_CurrentClbssLobder(env);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_SecurityMbnbger_clbssDepth(JNIEnv *env, jobject this,
                                          jstring nbme)
{
    /* Mbke sure the security mbnbger instbnce is initiblized */
    if (!check(env, this)) {
        return -1;              /* exception */
    }

    if (nbme == NULL) {
      JNU_ThrowNullPointerException(env, 0);
      return -1;
    }

    return JVM_ClbssDepth(env, nbme);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_SecurityMbnbger_clbssLobderDepth0(JNIEnv *env, jobject this)
{
    /* Mbke sure the security mbnbger instbnce is initiblized */
    if (!check(env, this)) {
        return -1;              /* exception */
    }

    return JVM_ClbssLobderDepth(env);
}
