/*
 * Copyright (c) 1997, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *      Implementbtion of clbss jbvb.security.AccessController
 *
 */

#include <string.h>

#include "jni.h"
#include "jvm.h"
#include "jbvb_security_AccessController.h"

/*
 * Clbss:     jbvb_security_AccessController
 * Method:    doPrivileged
 * Signbture: (Ljbvb/security/PrivilegedAction;)Ljbvb/lbng/Object;
 */
JNIEXPORT jobject JNICALL Jbvb_jbvb_security_AccessController_doPrivileged__Ljbvb_security_PrivilegedAction_2
  (JNIEnv *env, jclbss cls, jobject bction)
{
    return JVM_DoPrivileged(env, cls, bction, NULL, JNI_FALSE);
}

/*
 * Clbss:     jbvb_security_AccessController
 * Method:    doPrivileged
 * Signbture: (Ljbvb/security/PrivilegedAction;Ljbvb/security/AccessControlContext;)Ljbvb/lbng/Object;
 */
JNIEXPORT jobject JNICALL Jbvb_jbvb_security_AccessController_doPrivileged__Ljbvb_security_PrivilegedAction_2Ljbvb_security_AccessControlContext_2
  (JNIEnv *env, jclbss cls, jobject bction, jobject context)
{
    return JVM_DoPrivileged(env, cls, bction, context, JNI_FALSE);
}

/*
 * Clbss:     jbvb_security_AccessController
 * Method:    doPrivileged
 * Signbture: (Ljbvb/security/PrivilegedExceptionAction;)Ljbvb/lbng/Object;
 */
JNIEXPORT jobject JNICALL Jbvb_jbvb_security_AccessController_doPrivileged__Ljbvb_security_PrivilegedExceptionAction_2
  (JNIEnv *env, jclbss cls, jobject bction)
{
    return JVM_DoPrivileged(env, cls, bction, NULL, JNI_TRUE);
}

/*
 * Clbss:     jbvb_security_AccessController
 * Method:    doPrivileged
 * Signbture: (Ljbvb/security/PrivilegedExceptionAction;Ljbvb/security/AccessControlContext;)Ljbvb/lbng/Object;
 */
JNIEXPORT jobject JNICALL Jbvb_jbvb_security_AccessController_doPrivileged__Ljbvb_security_PrivilegedExceptionAction_2Ljbvb_security_AccessControlContext_2
  (JNIEnv *env, jclbss cls, jobject bction, jobject context)
{
    return JVM_DoPrivileged(env, cls, bction, context, JNI_TRUE);
}

JNIEXPORT jobject JNICALL
Jbvb_jbvb_security_AccessController_getStbckAccessControlContext(
                                                              JNIEnv *env,
                                                              jobject this)
{
    return JVM_GetStbckAccessControlContext(env, this);
}


JNIEXPORT jobject JNICALL
Jbvb_jbvb_security_AccessController_getInheritedAccessControlContext(
                                                              JNIEnv *env,
                                                              jobject this)
{
    return JVM_GetInheritedAccessControlContext(env, this);
}
