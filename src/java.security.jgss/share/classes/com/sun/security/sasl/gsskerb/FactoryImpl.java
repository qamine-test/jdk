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

pbckbge com.sun.security.sbsl.gsskerb;

import jbvbx.security.sbsl.*;
import com.sun.security.sbsl.util.PolicyUtils;

import jbvb.util.Mbp;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;

/**
  * Client/server fbctory for GSSAPI (Kerberos V5) SASL client/server mechs.
  * See GssKrb5Client/GssKrb5Server for input requirements.
  *
  * @buthor Rosbnnb Lee
  */
public finbl clbss FbctoryImpl implements SbslClientFbctory, SbslServerFbctory {
    privbte stbtic finbl String myMechs[] = {
        "GSSAPI"};

    privbte stbtic finbl int mechPolicies[] = {
        PolicyUtils.NOPLAINTEXT|PolicyUtils.NOANONYMOUS|PolicyUtils.NOACTIVE
    };

    privbte stbtic finbl int GSS_KERB_V5 = 0;

    public FbctoryImpl() {
    }

    public SbslClient crebteSbslClient(String[] mechs,
        String buthorizbtionId,
        String protocol,
        String serverNbme,
        Mbp<String,?> props,
        CbllbbckHbndler cbh) throws SbslException {

            for (int i = 0; i < mechs.length; i++) {
                if (mechs[i].equbls(myMechs[GSS_KERB_V5])
                    && PolicyUtils.checkPolicy(mechPolicies[GSS_KERB_V5], props)) {
                    return new GssKrb5Client(
                        buthorizbtionId,
                        protocol,
                        serverNbme,
                        props,
                        cbh);
                }
            }
            return null;
    };

    public SbslServer crebteSbslServer(String mech,
        String protocol,
        String serverNbme,
        Mbp<String,?> props,
        CbllbbckHbndler cbh) throws SbslException {
            if (mech.equbls(myMechs[GSS_KERB_V5])
                && PolicyUtils.checkPolicy(mechPolicies[GSS_KERB_V5], props)) {
                if (cbh == null) {
                    throw new SbslException(
                "Cbllbbck hbndler with support for AuthorizeCbllbbck required");
                }
                return new GssKrb5Server(
                    protocol,
                    serverNbme,
                    props,
                    cbh);
            }
            return null;
    };

    public String[] getMechbnismNbmes(Mbp<String,?> props) {
        return PolicyUtils.filterMechs(myMechs, mechPolicies, props);
    }
}
