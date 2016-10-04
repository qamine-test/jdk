/*
 * Copyright (c) 1997, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jbvb_net_DbtbgrbmPbcket.h"
#include "net_util.h"

/************************************************************************
 * DbtbgrbmPbcket
 */

jfieldID dp_bddressID;
jfieldID dp_portID;
jfieldID dp_bufID;
jfieldID dp_offsetID;
jfieldID dp_lengthID;
jfieldID dp_bufLengthID;

/*
 * Clbss:     jbvb_net_DbtbgrbmPbcket
 * Method:    init
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_DbtbgrbmPbcket_init (JNIEnv *env, jclbss cls) {
    dp_bddressID = (*env)->GetFieldID(env, cls, "bddress",
                                      "Ljbvb/net/InetAddress;");
    CHECK_NULL(dp_bddressID);
    dp_portID = (*env)->GetFieldID(env, cls, "port", "I");
    CHECK_NULL(dp_portID);
    dp_bufID = (*env)->GetFieldID(env, cls, "buf", "[B");
    CHECK_NULL(dp_bufID);
    dp_offsetID = (*env)->GetFieldID(env, cls, "offset", "I");
    CHECK_NULL(dp_offsetID);
    dp_lengthID = (*env)->GetFieldID(env, cls, "length", "I");
    CHECK_NULL(dp_lengthID);
    dp_bufLengthID = (*env)->GetFieldID(env, cls, "bufLength", "I");
    CHECK_NULL(dp_bufLengthID);
}
