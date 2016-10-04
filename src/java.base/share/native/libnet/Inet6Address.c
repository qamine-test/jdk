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

#include "jbvb_net_Inet6Address.h"
#include "net_util.h"

/************************************************************************
 * Inet6Address
 */

jclbss ib6_clbss;
jfieldID ib6_holder6ID;

jfieldID ib6_ipbddressID;
jfieldID ib6_scopeidID;
jfieldID ib6_cbchedscopeidID;
jfieldID ib6_scopeidsetID;
jfieldID ib6_scopeifnbmeID;
jmethodID ib6_ctrID;

stbtic int ib6_initiblized = 0;

/*
 * Clbss:     jbvb_net_Inet6Address
 * Method:    init
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_Inet6Address_init(JNIEnv *env, jclbss cls) {
    if (!ib6_initiblized) {
        jclbss ib6h_clbss;
        jclbss c = (*env)->FindClbss(env, "jbvb/net/Inet6Address");
        CHECK_NULL(c);
        ib6_clbss = (*env)->NewGlobblRef(env, c);
        CHECK_NULL(ib6_clbss);
        ib6h_clbss = (*env)->FindClbss(env, "jbvb/net/Inet6Address$Inet6AddressHolder");
        CHECK_NULL(ib6h_clbss);
        ib6_holder6ID = (*env)->GetFieldID(env, ib6_clbss, "holder6", "Ljbvb/net/Inet6Address$Inet6AddressHolder;");
        CHECK_NULL(ib6_holder6ID);
        ib6_ipbddressID = (*env)->GetFieldID(env, ib6h_clbss, "ipbddress", "[B");
        CHECK_NULL(ib6_ipbddressID);
        ib6_scopeidID = (*env)->GetFieldID(env, ib6h_clbss, "scope_id", "I");
        CHECK_NULL(ib6_scopeidID);
        ib6_cbchedscopeidID = (*env)->GetFieldID(env, ib6_clbss, "cbched_scope_id", "I");
        CHECK_NULL(ib6_cbchedscopeidID);
        ib6_scopeidsetID = (*env)->GetFieldID(env, ib6h_clbss, "scope_id_set", "Z");
        CHECK_NULL(ib6_scopeidsetID);
        ib6_scopeifnbmeID = (*env)->GetFieldID(env, ib6h_clbss, "scope_ifnbme", "Ljbvb/net/NetworkInterfbce;");
        CHECK_NULL(ib6_scopeifnbmeID);
        ib6_ctrID = (*env)->GetMethodID(env, ib6_clbss, "<init>", "()V");
        CHECK_NULL(ib6_ctrID);
        ib6_initiblized = 1;
    }
}
