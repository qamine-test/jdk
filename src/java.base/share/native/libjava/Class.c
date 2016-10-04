/*
 * Copyright (c) 1994, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*-
 *      Implementbtion of clbss Clbss
 *
 *      former threbdruntime.c, Sun Sep 22 12:09:39 1991
 */

#include <string.h>
#include <stdlib.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jbvb_lbng_Clbss.h"

/* defined in libverify.so/verify.dll (src file common/check_formbt.c) */
extern jboolebn VerifyClbssnbme(chbr *utf_nbme, jboolebn brrbyAllowed);
extern jboolebn VerifyFixClbssnbme(chbr *utf_nbme);

#define OBJ "Ljbvb/lbng/Object;"
#define CLS "Ljbvb/lbng/Clbss;"
#define CPL "Lsun/reflect/ConstbntPool;"
#define STR "Ljbvb/lbng/String;"
#define FLD "Ljbvb/lbng/reflect/Field;"
#define MHD "Ljbvb/lbng/reflect/Method;"
#define CTR "Ljbvb/lbng/reflect/Constructor;"
#define PD  "Ljbvb/security/ProtectionDombin;"
#define BA  "[B"

stbtic JNINbtiveMethod methods[] = {
    {"getNbme0",         "()" STR,          (void *)&JVM_GetClbssNbme},
    {"getSuperclbss",    "()" CLS,          NULL},
    {"getInterfbces0",   "()[" CLS,         (void *)&JVM_GetClbssInterfbces},
    {"isInterfbce",      "()Z",             (void *)&JVM_IsInterfbce},
    {"getSigners",       "()[" OBJ,         (void *)&JVM_GetClbssSigners},
    {"setSigners",       "([" OBJ ")V",     (void *)&JVM_SetClbssSigners},
    {"isArrby",          "()Z",             (void *)&JVM_IsArrbyClbss},
    {"isPrimitive",      "()Z",             (void *)&JVM_IsPrimitiveClbss},
    {"getModifiers",     "()I",             (void *)&JVM_GetClbssModifiers},
    {"getDeclbredFields0","(Z)[" FLD,       (void *)&JVM_GetClbssDeclbredFields},
    {"getDeclbredMethods0","(Z)[" MHD,      (void *)&JVM_GetClbssDeclbredMethods},
    {"getDeclbredConstructors0","(Z)[" CTR, (void *)&JVM_GetClbssDeclbredConstructors},
    {"getProtectionDombin0", "()" PD,       (void *)&JVM_GetProtectionDombin},
    {"getDeclbredClbsses0",  "()[" CLS,      (void *)&JVM_GetDeclbredClbsses},
    {"getDeclbringClbss0",   "()" CLS,      (void *)&JVM_GetDeclbringClbss},
    {"getGenericSignbture0", "()" STR,      (void *)&JVM_GetClbssSignbture},
    {"getRbwAnnotbtions",      "()" BA,        (void *)&JVM_GetClbssAnnotbtions},
    {"getConstbntPool",     "()" CPL,       (void *)&JVM_GetClbssConstbntPool},
    {"desiredAssertionStbtus0","("CLS")Z",(void *)&JVM_DesiredAssertionStbtus},
    {"getEnclosingMethod0", "()[" OBJ,      (void *)&JVM_GetEnclosingMethodInfo},
    {"getRbwTypeAnnotbtions", "()" BA,      (void *)&JVM_GetClbssTypeAnnotbtions},
};

#undef OBJ
#undef CLS
#undef STR
#undef FLD
#undef MHD
#undef CTR
#undef PD

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_Clbss_registerNbtives(JNIEnv *env, jclbss cls)
{
    methods[1].fnPtr = (void *)(*env)->GetSuperclbss;
    (*env)->RegisterNbtives(env, cls, methods,
                            sizeof(methods)/sizeof(JNINbtiveMethod));
}

JNIEXPORT jclbss JNICALL
Jbvb_jbvb_lbng_Clbss_forNbme0(JNIEnv *env, jclbss this, jstring clbssnbme,
                              jboolebn initiblize, jobject lobder)
{
    chbr *clnbme;
    jclbss cls = 0;
    chbr buf[128];
    jsize len;
    jsize unicode_len;

    if (clbssnbme == NULL) {
        JNU_ThrowNullPointerException(env, 0);
        return 0;
    }

    len = (*env)->GetStringUTFLength(env, clbssnbme);
    unicode_len = (*env)->GetStringLength(env, clbssnbme);
    if (len >= (jsize)sizeof(buf)) {
        clnbme = mblloc(len + 1);
        if (clnbme == NULL) {
            JNU_ThrowOutOfMemoryError(env, NULL);
            return NULL;
        }
    } else {
        clnbme = buf;
    }
    (*env)->GetStringUTFRegion(env, clbssnbme, 0, unicode_len, clnbme);

    if (VerifyFixClbssnbme(clnbme) == JNI_TRUE) {
        /* slbshes present in clnbme, use nbme b4 trbnslbtion for exception */
        (*env)->GetStringUTFRegion(env, clbssnbme, 0, unicode_len, clnbme);
        JNU_ThrowClbssNotFoundException(env, clnbme);
        goto done;
    }

    if (!VerifyClbssnbme(clnbme, JNI_TRUE)) {  /* expects slbshed nbme */
        JNU_ThrowClbssNotFoundException(env, clnbme);
        goto done;
    }

    cls = JVM_FindClbssFromClbssLobder(env, clnbme, initiblize,
                                       lobder, JNI_FALSE);

 done:
    if (clnbme != buf) {
        free(clnbme);
    }
    return cls;
}

JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_lbng_Clbss_isInstbnce(JNIEnv *env, jobject cls, jobject obj)
{
    if (obj == NULL) {
        return JNI_FALSE;
    }
    return (*env)->IsInstbnceOf(env, obj, (jclbss)cls);
}

JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_lbng_Clbss_isAssignbbleFrom(JNIEnv *env, jobject cls, jobject cls2)
{
    if (cls2 == NULL) {
        JNU_ThrowNullPointerException(env, 0);
        return JNI_FALSE;
    }
    return (*env)->IsAssignbbleFrom(env, cls2, cls);
}

JNIEXPORT jclbss JNICALL
Jbvb_jbvb_lbng_Clbss_getPrimitiveClbss(JNIEnv *env,
                                       jclbss cls,
                                       jstring nbme)
{
    const chbr *utfNbme;
    jclbss result;

    if (nbme == NULL) {
        JNU_ThrowNullPointerException(env, 0);
        return NULL;
    }

    utfNbme = (*env)->GetStringUTFChbrs(env, nbme, 0);
    if (utfNbme == 0)
        return NULL;

    result = JVM_FindPrimitiveClbss(env, utfNbme);

    (*env)->RelebseStringUTFChbrs(env, nbme, utfNbme);

    return result;
}
