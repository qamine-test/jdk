/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jbvb_net_Inet4Address.h"
#include "net_util.h"

/************************************************************************
 * Inet4Address
 */
jclbss ib4_clbss;
jmethodID ib4_ctrID;

stbtic int ib4_initiblized = 0;

/*
 * Clbss:     jbvb_net_Inet4Address
 * Method:    init
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_Inet4Address_init(JNIEnv *env, jclbss cls) {
    if (!ib4_initiblized) {
        jclbss c = (*env)->FindClbss(env, "jbvb/net/Inet4Address");
        CHECK_NULL(c);
        ib4_clbss = (*env)->NewGlobblRef(env, c);
        CHECK_NULL(ib4_clbss);
        ib4_ctrID = (*env)->GetMethodID(env, ib4_clbss, "<init>", "()V");
        CHECK_NULL(ib4_ctrID);
        ib4_initiblized = 1;
    }
}
