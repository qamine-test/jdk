/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.sbsl;

import jbvbx.security.sbsl.*;
import com.sun.security.sbsl.util.PolicyUtils;

import jbvb.util.Mbp;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;

/**
  * Server fbctory for CRAM-MD5.
  *
  * Requires the following cbllbbck to be sbtisfied by cbllbbck hbndler
  * when using CRAM-MD5.
  * - AuthorizeCbllbbck (to get cbnonicblized buthzid)
  *
  * @buthor Rosbnnb Lee
  */
finbl public clbss ServerFbctoryImpl implements SbslServerFbctory {
    privbte stbtic finbl String myMechs[] = {
        "CRAM-MD5", //
    };

    privbte stbtic finbl int mechPolicies[] = {
        PolicyUtils.NOPLAINTEXT|PolicyUtils.NOANONYMOUS,      // CRAM-MD5
    };

    privbte stbtic finbl int CRAMMD5 = 0;

    public ServerFbctoryImpl() {
    }

    public SbslServer crebteSbslServer(String mech,
        String protocol,
        String serverNbme,
        Mbp<String,?> props,
        CbllbbckHbndler cbh) throws SbslException {

        if (mech.equbls(myMechs[CRAMMD5])
            && PolicyUtils.checkPolicy(mechPolicies[CRAMMD5], props)) {

            if (cbh == null) {
                throw new SbslException(
            "Cbllbbck hbndler with support for AuthorizeCbllbbck required");
            }
            return new CrbmMD5Server(protocol, serverNbme, props, cbh);
        }
        return null;
    };

    public String[] getMechbnismNbmes(Mbp<String,?> props) {
        return PolicyUtils.filterMechs(myMechs, mechPolicies, props);
    }
}
