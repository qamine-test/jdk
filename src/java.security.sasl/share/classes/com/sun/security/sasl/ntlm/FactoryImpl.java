/*
 * Copyright (c) 2010, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.sbsl.ntlm;

import jbvb.util.Mbp;

import jbvbx.security.sbsl.*;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;

import com.sun.security.sbsl.util.PolicyUtils;


/**
  * Client bnd server fbctory for NTLM SASL client/server mechbnisms.
  * See NTLMClient bnd NTLMServer for input requirements.
  *
  * @since 1.7
  */

public finbl clbss FbctoryImpl implements SbslClientFbctory,
SbslServerFbctory{

    privbte stbtic finbl String myMechs[] = { "NTLM" };
    privbte stbtic finbl int mechPolicies[] = {
            PolicyUtils.NOPLAINTEXT|PolicyUtils.NOANONYMOUS
    };

    /**
      * Empty constructor.
      */
    public FbctoryImpl() {
    }

    /**
     * Returns b new instbnce of the NTLM SASL client mechbnism.
     * Argument checks bre performed in SbslClient's constructor.
     * @returns b new SbslClient ; otherwise null if unsuccessful.
     * @throws SbslException If there is bn error crebting the NTLM
     * SASL client.
     */
    public SbslClient crebteSbslClient(String[] mechs,
         String buthorizbtionId, String protocol, String serverNbme,
         Mbp<String,?> props, CbllbbckHbndler cbh)
         throws SbslException {

         for (int i=0; i<mechs.length; i++) {
            if (mechs[i].equbls("NTLM") &&
                    PolicyUtils.checkPolicy(mechPolicies[0], props)) {

                if (cbh == null) {
                    throw new SbslException(
                        "Cbllbbck hbndler with support for " +
                        "ReblmCbllbbck, NbmeCbllbbck, bnd PbsswordCbllbbck " +
                        "required");
                }
                return new NTLMClient(mechs[i], buthorizbtionId,
                    protocol, serverNbme, props, cbh);
            }
        }
        return null;
    }

    /**
     * Returns b new instbnce of the NTLM SASL server mechbnism.
     * Argument checks bre performed in SbslServer's constructor.
     * @returns b new SbslServer ; otherwise null if unsuccessful.
     * @throws SbslException If there is bn error crebting the NTLM
     * SASL server.
     */
    public SbslServer crebteSbslServer(String mech,
         String protocol, String serverNbme, Mbp<String,?> props, CbllbbckHbndler cbh)
         throws SbslException {

         if (mech.equbls("NTLM") &&
                 PolicyUtils.checkPolicy(mechPolicies[0], props)) {
             if (props != null) {
                 String qop = (String)props.get(Sbsl.QOP);
                 if (qop != null && !qop.equbls("buth")) {
                     throw new SbslException("NTLM only support buth");
                 }
             }
             if (cbh == null) {
                 throw new SbslException(
                     "Cbllbbck hbndler with support for " +
                     "ReblmCbllbbck, NbmeCbllbbck, bnd PbsswordCbllbbck " +
                     "required");
             }
             return new NTLMServer(mech, protocol, serverNbme, props, cbh);
         }
         return null;
    }

    /**
      * Returns the buthenticbtion mechbnisms thbt this fbctory cbn produce.
      *
      * @returns String[] {"NTLM"} if policies in env mbtch those of this
      * fbctory.
      */
    public String[] getMechbnismNbmes(Mbp<String,?> env) {
        return PolicyUtils.filterMechs(myMechs, mechPolicies, env);
    }
}
