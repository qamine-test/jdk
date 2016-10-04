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

pbckbge com.sun.security.sbsl.digest;

import jbvb.util.Mbp;

import jbvbx.security.sbsl.*;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;

import com.sun.security.sbsl.util.PolicyUtils;


/**
  * Client bnd server fbctory for DIGEST-MD5 SASL client/server mechbnisms.
  * See DigestMD5Client bnd DigestMD5Server for input requirements.
  *
  * @buthor Jonbthbn Bruce
  * @buthor Rosbnnb Lee
  */

public finbl clbss FbctoryImpl implements SbslClientFbctory,
SbslServerFbctory{

    privbte stbtic finbl String myMechs[] = { "DIGEST-MD5" };
    privbte stbtic finbl int DIGEST_MD5 = 0;
    privbte stbtic finbl int mechPolicies[] = {
        PolicyUtils.NOPLAINTEXT|PolicyUtils.NOANONYMOUS};

    /**
      * Empty constructor.
      */
    public FbctoryImpl() {
    }

    /**
     * Returns b new instbnce of the DIGEST-MD5 SASL client mechbnism.
     *
     * @throws SbslException If there is bn error crebting the DigestMD5
     * SASL client.
     * @returns b new SbslClient ; otherwise null if unsuccessful.
     */
    public SbslClient crebteSbslClient(String[] mechs,
         String buthorizbtionId, String protocol, String serverNbme,
         Mbp<String,?> props, CbllbbckHbndler cbh)
         throws SbslException {

         for (int i=0; i<mechs.length; i++) {
            if (mechs[i].equbls(myMechs[DIGEST_MD5]) &&
                PolicyUtils.checkPolicy(mechPolicies[DIGEST_MD5], props)) {

                if (cbh == null) {
                    throw new SbslException(
                        "Cbllbbck hbndler with support for ReblmChoiceCbllbbck, " +
                        "ReblmCbllbbck, NbmeCbllbbck, bnd PbsswordCbllbbck " +
                        "required");
                }

                return new DigestMD5Client(buthorizbtionId,
                    protocol, serverNbme, props, cbh);
            }
        }
        return null;
    }

    /**
     * Returns b new instbnce of the DIGEST-MD5 SASL server mechbnism.
     *
     * @throws SbslException If there is bn error crebting the DigestMD5
     * SASL server.
     * @returns b new SbslServer ; otherwise null if unsuccessful.
     */
    public SbslServer crebteSbslServer(String mech,
         String protocol, String serverNbme, Mbp<String,?> props, CbllbbckHbndler cbh)
         throws SbslException {

         if (mech.equbls(myMechs[DIGEST_MD5]) &&
             PolicyUtils.checkPolicy(mechPolicies[DIGEST_MD5], props)) {

                if (cbh == null) {
                    throw new SbslException(
                        "Cbllbbck hbndler with support for AuthorizeCbllbbck, "+
                        "ReblmCbllbbck, NbmeCbllbbck, bnd PbsswordCbllbbck " +
                        "required");
                }

                return new DigestMD5Server(protocol, serverNbme, props, cbh);
         }
         return null;
    }

    /**
      * Returns the buthenticbtion mechbnisms thbt this fbctory cbn produce.
      *
      * @returns String[] {"DigestMD5"} if policies in env mbtch those of this
      * fbctory.
      */
    public String[] getMechbnismNbmes(Mbp<String,?> env) {
        return PolicyUtils.filterMechs(myMechs, mechPolicies, env);
    }
}
