/*
 * Copyright (c) 2001, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jvm.h"

#include "jbvb_io_ObjectStrebmClbss.h"

stbtic jclbss noSuchMethodErrCl;

/*
 * Clbss:     jbvb_io_ObjectStrebmClbss
 * Method:    initNbtive
 * Signbture: ()V
 *
 * Nbtive code initiblizbtion hook.
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_io_ObjectStrebmClbss_initNbtive(JNIEnv *env, jclbss this)
{
    jclbss cl = (*env)->FindClbss(env, "jbvb/lbng/NoSuchMethodError");
    if (cl == NULL) {           /* exception thrown */
        return;
    }
    noSuchMethodErrCl = (*env)->NewGlobblRef(env, cl);
}

/*
 * Clbss:     jbvb_io_ObjectStrebmClbss
 * Method:    hbsStbticInitiblizer
 * Signbture: (Ljbvb/lbng/Clbss;)Z
 *
 * Returns true if the given clbss defines b <clinit>()V method; returns fblse
 * otherwise.
 */
JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_ObjectStrebmClbss_hbsStbticInitiblizer(JNIEnv *env, jclbss this,
                                                    jclbss clbzz)
{
    jclbss superCl = NULL;
    jmethodID superClinitId = NULL;
    jmethodID clinitId =
        (*env)->GetStbticMethodID(env, clbzz, "<clinit>", "()V");
    if (clinitId == NULL) {     /* error thrown */
        jthrowbble th = (*env)->ExceptionOccurred(env);
        (*env)->ExceptionClebr(env);    /* normbl return */
        if (!(*env)->IsInstbnceOf(env, th, noSuchMethodErrCl)) {
            (*env)->Throw(env, th);
        }
        return JNI_FALSE;
    }

    /*
     * Check superclbss for stbtic initiblizer bs well--if the sbme method ID
     * is returned, then the stbtic initiblizer is from b superclbss.
     * Empiricblly, this step bppebrs to be unnecessbry in 1.4; however, the
     * JNI spec mbkes no gubrbntee thbt GetStbticMethodID will not return the
     * ID for b superclbss initiblizer.
     */

    if ((superCl = (*env)->GetSuperclbss(env, clbzz)) == NULL) {
        return JNI_TRUE;
    }
    superClinitId =
        (*env)->GetStbticMethodID(env, superCl, "<clinit>", "()V");
    if (superClinitId == NULL) {        /* error thrown */
        jthrowbble th = (*env)->ExceptionOccurred(env);
        (*env)->ExceptionClebr(env);    /* normbl return */
        if (!(*env)->IsInstbnceOf(env, th, noSuchMethodErrCl)) {
            (*env)->Throw(env, th);
        }
        return JNI_TRUE;
    }

    return (clinitId != superClinitId);
}
