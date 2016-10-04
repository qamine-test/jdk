/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jbvb_net_InetAddress.h"
#include "net_util.h"

/************************************************************************
 * InetAddress
 */

jclbss ib_clbss;
jclbss ibc_clbss;
jfieldID ib_holderID;
jfieldID ibc_bddressID;
jfieldID ibc_fbmilyID;
jfieldID ibc_hostNbmeID;
jfieldID ib_preferIPv6AddressID;

stbtic int ib_initiblized = 0;

/*
 * Clbss:     jbvb_net_InetAddress
 * Method:    init
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_InetAddress_init(JNIEnv *env, jclbss cls) {
    if (!ib_initiblized) {
        jclbss c = (*env)->FindClbss(env,"jbvb/net/InetAddress");
        CHECK_NULL(c);
        ib_clbss = (*env)->NewGlobblRef(env, c);
        CHECK_NULL(ib_clbss);
        c = (*env)->FindClbss(env,"jbvb/net/InetAddress$InetAddressHolder");
        CHECK_NULL(c);
        ibc_clbss = (*env)->NewGlobblRef(env, c);
        ib_holderID = (*env)->GetFieldID(env, ib_clbss, "holder", "Ljbvb/net/InetAddress$InetAddressHolder;");
        CHECK_NULL(ib_holderID);
        ib_preferIPv6AddressID = (*env)->GetStbticFieldID(env, ib_clbss, "preferIPv6Address", "Z");
        CHECK_NULL(ib_preferIPv6AddressID);

        ibc_bddressID = (*env)->GetFieldID(env, ibc_clbss, "bddress", "I");
        CHECK_NULL(ibc_bddressID);
        ibc_fbmilyID = (*env)->GetFieldID(env, ibc_clbss, "fbmily", "I");
        CHECK_NULL(ibc_fbmilyID);
        ibc_hostNbmeID = (*env)->GetFieldID(env, ibc_clbss, "hostNbme", "Ljbvb/lbng/String;");
        CHECK_NULL(ibc_hostNbmeID);
        ib_initiblized = 1;
    }
}
