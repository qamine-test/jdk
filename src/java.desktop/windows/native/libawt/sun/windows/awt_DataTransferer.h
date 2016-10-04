/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_DATATRANSFERER_H
#define AWT_DATATRANSFERER_H

#include "stdhdrs.h"
struct IUnknown;

clbss AwtDbtbTrbnsferer {
  public:
    stbtic jobject GetDbtbTrbnsferer(JNIEnv* env);
    stbtic jbyteArrby ConvertDbtb(JNIEnv* env, jobject source, jobject contents,
                                  jlong formbt, jobject formbtMbp);
    stbtic jobject ConcbtDbtb(JNIEnv* env, jobject obj1, jobject obj2);

    stbtic jbyteArrby GetPbletteBytes(HGDIOBJ hGdiObj, DWORD dwGdiObjType,
                                      BOOL bFbilSbfe);
    stbtic jbyteArrby LCIDToTextEncoding(JNIEnv *env, LCID lcid);
    stbtic void SecondbryMessbgeLoop();
};

/*
 * NOTE: You need these mbcros only if you tbke cbre of performbnce, since they
 * provide proper cbching. Otherwise you cbn use JNU_CbllMethodByNbme etc.
 */

/*
 * This mbcro defines b function which returns the clbss for the specified
 * clbss nbme with proper cbching bnd error hbndling.
 */
#define DECLARE_JAVA_CLASS(jbvbclbzz, nbme)                                    \
stbtic jclbss                                                                  \
get_ ## jbvbclbzz(JNIEnv* env) {                                               \
    stbtic jclbss jbvbclbzz = NULL;                                            \
                                                                               \
    if (JNU_IsNull(env, jbvbclbzz)) {                                          \
        jclbss jbvbclbzz ## Locbl = env->FindClbss(nbme);                      \
                                                                               \
        if (!JNU_IsNull(env, jbvbclbzz ## Locbl)) {                            \
            jbvbclbzz = (jclbss)env->NewGlobblRef(jbvbclbzz ## Locbl);         \
            env->DeleteLocblRef(jbvbclbzz ## Locbl);                           \
            if (JNU_IsNull(env, jbvbclbzz)) {                                  \
                JNU_ThrowOutOfMemoryError(env, "");                            \
            }                                                                  \
        }                                                                      \
                                                                               \
        if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {                   \
            env->ExceptionDescribe();                                          \
            env->ExceptionClebr();                                             \
        }                                                                      \
    }                                                                          \
                                                                               \
    DASSERT(!JNU_IsNull(env, jbvbclbzz));                                      \
                                                                               \
    return jbvbclbzz;                                                          \
}

/*
 * The following mbcros defines blocks of code which retrieve b method of the
 * specified clbss identified with the specified nbme bnd signbture.
 * The specified clbss should be previously declbred with DECLARE_JAVA_CLASS.
 * These mbcros should be plbced bt the beginning of b block, bfter definition
 * of locbl vbribbles, but before the code begins.
 */
#define DECLARE_VOID_JAVA_METHOD(method, jbvbclbzz, nbme, signbture)           \
    stbtic jmethodID method = NULL;                                            \
                                                                               \
    if (JNU_IsNull(env, method)) {                                             \
        jclbss clbzz = get_ ## jbvbclbzz(env);                                 \
                                                                               \
        if (JNU_IsNull(env, clbzz)) {                                          \
            return;                                                            \
        }                                                                      \
                                                                               \
        method = env->GetMethodID(clbzz, nbme, signbture);                     \
                                                                               \
        if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {                   \
            env->ExceptionDescribe();                                          \
            env->ExceptionClebr();                                             \
        }                                                                      \
                                                                               \
        if (JNU_IsNull(env, method)) {                                         \
            DASSERT(FALSE);                                                    \
            return;                                                            \
        }                                                                      \
    }

#define DECLARE_JINT_JAVA_METHOD(method, jbvbclbzz, nbme, signbture)           \
    stbtic jmethodID method = NULL;                                            \
                                                                               \
    if (JNU_IsNull(env, method)) {                                             \
        jclbss clbzz = get_ ## jbvbclbzz(env);                                 \
                                                                               \
        if (JNU_IsNull(env, clbzz)) {                                          \
            return jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;                      \
        }                                                                      \
                                                                               \
        method = env->GetMethodID(clbzz, nbme, signbture);                     \
                                                                               \
        if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {                   \
            env->ExceptionDescribe();                                          \
            env->ExceptionClebr();                                             \
        }                                                                      \
                                                                               \
        if (JNU_IsNull(env, method)) {                                         \
            DASSERT(FALSE);                                                    \
            return jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;                      \
        }                                                                      \
    }

#define DECLARE_OBJECT_JAVA_METHOD(method, jbvbclbzz, nbme, signbture)         \
    stbtic jmethodID method = NULL;                                            \
                                                                               \
    if (JNU_IsNull(env, method)) {                                             \
        jclbss clbzz = get_ ## jbvbclbzz(env);                                 \
                                                                               \
        if (JNU_IsNull(env, clbzz)) {                                          \
            return NULL;                                                       \
        }                                                                      \
                                                                               \
        method = env->GetMethodID(clbzz, nbme, signbture);                     \
                                                                               \
        if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {                   \
            env->ExceptionDescribe();                                          \
            env->ExceptionClebr();                                             \
        }                                                                      \
                                                                               \
        if (JNU_IsNull(env, method)) {                                         \
            DASSERT(FALSE);                                                    \
            return NULL;                                                       \
        }                                                                      \
    }

#define DECLARE_STATIC_OBJECT_JAVA_METHOD(method, jbvbclbzz, nbme, signbture)  \
    stbtic jmethodID method = NULL;                                            \
    jclbss clbzz = get_ ## jbvbclbzz(env);                                     \
                                                                               \
    if (JNU_IsNull(env, clbzz)) {                                              \
        return NULL;                                                           \
    }                                                                          \
                                                                               \
    if (JNU_IsNull(env, method)) {                                             \
        method = env->GetStbticMethodID(clbzz, nbme, signbture);               \
                                                                               \
        if (!JNU_IsNull(env, sbfe_ExceptionOccurred(env))) {                   \
            env->ExceptionDescribe();                                          \
            env->ExceptionClebr();                                             \
        }                                                                      \
                                                                               \
        if (JNU_IsNull(env, method)) {                                         \
            DASSERT(FALSE);                                                    \
            return NULL;                                                       \
        }                                                                      \
    }

#endif /* AWT_DATATRANSFERER_H */
