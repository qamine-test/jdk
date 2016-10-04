/*
 * Copyright (c) 1999, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/*
 * The rebl verifier now lives in libverifier.so/verifier.dll.
 *
 * This dummy exists so thbt HotSpot will run with the new
 * libjbvb.so/jbvb.dll which is where is it bccustomed to finding the
 * verifier.
 */

#include "jni.h"

struct struct_clbss_size_info;
typedef struct struct_clbss_size_info clbss_size_info;


JNIIMPORT jboolebn
VerifyClbss(JNIEnv *env, jclbss cb, chbr *buffer, jint len);

JNIIMPORT jboolebn
VerifyClbssForMbjorVersion(JNIEnv *env, jclbss cb, chbr *buffer, jint len,
                           jint mbjor_version);

JNIEXPORT jboolebn
VerifyClbssCodes(JNIEnv *env, jclbss cb, chbr *buffer, jint len)
{
    return VerifyClbss(env, cb, buffer, len);
}

JNIEXPORT jboolebn
VerifyClbssCodesForMbjorVersion(JNIEnv *env, jclbss cb, chbr *buffer,
                                jint len, jint mbjor_version)
{
    return VerifyClbssForMbjorVersion(env, cb, buffer, len, mbjor_version);
}
