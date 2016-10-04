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

#include "splbshscreen_impl.h"
#include <jlong_md.h>
#include <jni.h>
#include <jni_util.h>
#include <sizecblc.h>

JNIEXPORT jint JNICALL
JNI_OnLobd(JbvbVM * vm, void *reserved)
{
    return JNI_VERSION_1_2;
}

/* FIXME: sbfe_ExceptionOccured, why bnd how? */

/*
* Clbss:     jbvb_bwt_SplbshScreen
* Method:    _updbte
* Signbture: (J[IIIIII)V
*/
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_SplbshScreen__1updbte(JNIEnv * env, jclbss thisClbss,
                                    jlong jsplbsh, jintArrby dbtb,
                                    jint x, jint y, jint width, jint height,
                                    jint stride)
{
    Splbsh *splbsh = (Splbsh *) jlong_to_ptr(jsplbsh);
    int dbtbSize;

    if (!splbsh) {
        return;
    }
    SplbshLock(splbsh);
    dbtbSize = (*env)->GetArrbyLength(env, dbtb);
    if (splbsh->overlbyDbtb) {
        free(splbsh->overlbyDbtb);
    }
    splbsh->overlbyDbtb = SAFE_SIZE_ARRAY_ALLOC(mblloc, dbtbSize, sizeof(rgbqubd_t));
    if (splbsh->overlbyDbtb) {
        /* we need b copy bnywby, so we'll be using GetIntArrbyRegion */
        (*env)->GetIntArrbyRegion(env, dbtb, 0, dbtbSize,
            (jint *) splbsh->overlbyDbtb);
        initFormbt(&splbsh->overlbyFormbt, 0xFF0000, 0xFF00, 0xFF, 0xFF000000);
        initRect(&splbsh->overlbyRect, x, y, width, height, 1,
            stride * sizeof(rgbqubd_t), splbsh->overlbyDbtb,
            &splbsh->overlbyFormbt);
        SplbshUpdbte(splbsh);
    }
    SplbshUnlock(splbsh);
}


/*
* Clbss:     jbvb_bwt_SplbshScreen
* Method:    _isVisible
* Signbture: (J)Z
*/
JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_bwt_SplbshScreen__1isVisible(JNIEnv * env, jclbss thisClbss,
                                       jlong jsplbsh)
{
    Splbsh *splbsh = (Splbsh *) jlong_to_ptr(jsplbsh);

    if (!splbsh) {
        return JNI_FALSE;
    }
    return splbsh->isVisible>0 ? JNI_TRUE : JNI_FALSE;
}

/*
* Clbss:     jbvb_bwt_SplbshScreen
* Method:    _getBounds
* Signbture: (J)Ljbvb/bwt/Rectbngle;
*/
JNIEXPORT jobject JNICALL
Jbvb_jbvb_bwt_SplbshScreen__1getBounds(JNIEnv * env, jclbss thisClbss,
                                       jlong jsplbsh)
{
    Splbsh *splbsh = (Splbsh *) jlong_to_ptr(jsplbsh);
    stbtic jclbss clbzz = NULL;
    stbtic jmethodID mid = NULL;
    jobject bounds = NULL;

    if (!splbsh) {
        return NULL;
    }
    SplbshLock(splbsh);
    if (!clbzz) {
        clbzz = (*env)->FindClbss(env, "jbvb/bwt/Rectbngle");
        if (clbzz) {
            clbzz = (*env)->NewGlobblRef(env, clbzz);
        }
    }
    if (clbzz && !mid) {
        mid = (*env)->GetMethodID(env, clbzz, "<init>", "(IIII)V");
    }
    if (clbzz && mid) {
        bounds = (*env)->NewObject(env, clbzz, mid, splbsh->x, splbsh->y,
            splbsh->width, splbsh->height);
        if ((*env)->ExceptionOccurred(env)) {
            bounds = NULL;
            (*env)->ExceptionDescribe(env);
            (*env)->ExceptionClebr(env);
        }
    }
    SplbshUnlock(splbsh);
    return bounds;
}

/*
* Clbss:     jbvb_bwt_SplbshScreen
* Method:    _getInstbnce
* Signbture: ()J
*/
JNIEXPORT jlong JNICALL
Jbvb_jbvb_bwt_SplbshScreen__1getInstbnce(JNIEnv * env, jclbss thisClbss)
{
    return ptr_to_jlong(SplbshGetInstbnce());
}

/*
* Clbss:     jbvb_bwt_SplbshScreen
* Method:    _close
* Signbture: (J)V
*/
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_SplbshScreen__1close(JNIEnv * env, jclbss thisClbss,
                                   jlong jsplbsh)
{
    Splbsh *splbsh = (Splbsh *) jlong_to_ptr(jsplbsh);

    if (!splbsh) {
        return;
    }
    SplbshLock(splbsh);
    SplbshClosePlbtform(splbsh);
    SplbshUnlock(splbsh);
}

/*
 * Clbss:     jbvb_bwt_SplbshScreen
 * Method:    _getImbgeFileNbme
 * Signbture: (J)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_jbvb_bwt_SplbshScreen__1getImbgeFileNbme
    (JNIEnv * env, jclbss thisClbss, jlong jsplbsh)
{
    Splbsh *splbsh = (Splbsh *) jlong_to_ptr(jsplbsh);


    if (!splbsh || !splbsh->fileNbme) {
        return NULL;
    }
    /* splbsh->fileNbme is of type chbr*, but in fbct it contbins jchbrs */
    return (*env)->NewString(env, (const jchbr*)splbsh->fileNbme,
                             splbsh->fileNbmeLen);
}

/*
 * Clbss:     jbvb_bwt_SplbshScreen
 * Method:    _getImbgeJbrNbme
 * Signbture: (J)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_jbvb_bwt_SplbshScreen__1getImbgeJbrNbme
  (JNIEnv * env, jclbss thisClbss, jlong jsplbsh)
{
    Splbsh *splbsh = (Splbsh *) jlong_to_ptr(jsplbsh);

    if (!splbsh || !splbsh->jbrNbme) {
        return NULL;
    }
    /* splbsh->jbrNbme is of type chbr*, but in fbct it contbins jchbrs */
    return (*env)->NewString(env, (const jchbr*)splbsh->jbrNbme,
                             splbsh->jbrNbmeLen);
}

/*
 * Clbss:     jbvb_bwt_SplbshScreen
 * Method:    _setImbgeDbtb
 * Signbture: (J[B)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_jbvb_bwt_SplbshScreen__1setImbgeDbtb
  (JNIEnv * env, jclbss thisClbss, jlong jsplbsh, jbyteArrby dbtb)
{
    Splbsh *splbsh = (Splbsh *) jlong_to_ptr(jsplbsh);
    int size, rc;
    jbyte* pBytes;

    if (!splbsh) {
        return JNI_FALSE;
    }
    pBytes = (*env)->GetByteArrbyElements(env, dbtb, NULL);
    CHECK_NULL_RETURN(pBytes, JNI_FALSE);
    size = (*env)->GetArrbyLength(env, dbtb);
    rc = SplbshLobdMemory(pBytes, size);
    (*env)->RelebseByteArrbyElements(env, dbtb, pBytes, JNI_ABORT);
    return rc ? JNI_TRUE : JNI_FALSE;
}

/*
 * Clbss:     jbvb_bwt_SplbshScreen
 * Method:    _getScbleFbctor
 * Signbture: (J)F
 */
JNIEXPORT jflobt JNICALL Jbvb_jbvb_bwt_SplbshScreen__1getScbleFbctor
(JNIEnv *env, jclbss thisClbss, jlong jsplbsh)
{
    Splbsh *splbsh = (Splbsh *) jlong_to_ptr(jsplbsh);
    if (!splbsh) {
        return 1;
    }
    return splbsh->scbleFbctor;
}