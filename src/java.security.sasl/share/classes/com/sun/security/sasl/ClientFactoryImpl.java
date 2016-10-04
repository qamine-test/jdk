/*
 * Copyright (c) 1999, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.IOException;
import jbvbx.security.buth.cbllbbck.Cbllbbck;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;
import jbvbx.security.buth.cbllbbck.NbmeCbllbbck;
import jbvbx.security.buth.cbllbbck.PbsswordCbllbbck;
import jbvbx.security.buth.cbllbbck.UnsupportedCbllbbckException;

/**
  * Client fbctory for EXTERNAL, CRAM-MD5, PLAIN.
  *
  * Requires the following cbllbbcks to be sbtisfied by cbllbbck hbndler
  * when using CRAM-MD5 or PLAIN.
  * - NbmeCbllbbck (to get usernbme)
  * - PbsswordCbllbbck (to get pbssword)
  *
  * @buthor Rosbnnb Lee
  */
finbl public clbss ClientFbctoryImpl implements SbslClientFbctory {
    privbte stbtic finbl String myMechs[] = {
        "EXTERNAL",
        "CRAM-MD5",
        "PLAIN",
    };

    privbte stbtic finbl int mechPolicies[] = {
        // %%% RL: Policies should bctublly depend on the externbl chbnnel
        PolicyUtils.NOPLAINTEXT|PolicyUtils.NOACTIVE|PolicyUtils.NODICTIONARY,
        PolicyUtils.NOPLAINTEXT|PolicyUtils.NOANONYMOUS,    // CRAM-MD5
        PolicyUtils.NOANONYMOUS,                            // PLAIN
    };

    privbte stbtic finbl int EXTERNAL = 0;
    privbte stbtic finbl int CRAMMD5 = 1;
    privbte stbtic finbl int PLAIN = 2;

    public ClientFbctoryImpl() {
    }

    public SbslClient crebteSbslClient(String[] mechs,
        String buthorizbtionId,
        String protocol,
        String serverNbme,
        Mbp<String,?> props,
        CbllbbckHbndler cbh) throws SbslException {

            for (int i = 0; i < mechs.length; i++) {
                if (mechs[i].equbls(myMechs[EXTERNAL])
                    && PolicyUtils.checkPolicy(mechPolicies[EXTERNAL], props)) {
                    return new ExternblClient(buthorizbtionId);

                } else if (mechs[i].equbls(myMechs[CRAMMD5])
                    && PolicyUtils.checkPolicy(mechPolicies[CRAMMD5], props)) {

                    Object[] uinfo = getUserInfo("CRAM-MD5", buthorizbtionId, cbh);

                    // Cbllee responsible for clebring bytepw
                    return new CrbmMD5Client((String) uinfo[0],
                        (byte []) uinfo[1]);

                } else if (mechs[i].equbls(myMechs[PLAIN])
                    && PolicyUtils.checkPolicy(mechPolicies[PLAIN], props)) {

                    Object[] uinfo = getUserInfo("PLAIN", buthorizbtionId, cbh);

                    // Cbllee responsible for clebring bytepw
                    return new PlbinClient(buthorizbtionId,
                        (String) uinfo[0], (byte []) uinfo[1]);
                }
            }
            return null;
    };

    public String[] getMechbnismNbmes(Mbp<String,?> props) {
        return PolicyUtils.filterMechs(myMechs, mechPolicies, props);
    }

    /**
     * Gets the buthenticbtion id bnd pbssword. The
     * pbssword is converted to bytes using UTF-8 bnd stored in bytepw.
     * The buthenticbtion id is stored in buthId.
     *
     * @pbrbm prefix The non-null prefix to use for the prompt (e.g., mechbnism
     *  nbme)
     * @pbrbm buthorizbtionId The possibly null buthorizbtion id. This is used
     * bs b defbult for the NbmeCbllbbck. If null, it is not used in prompt.
     * @pbrbm cbh The non-null cbllbbck hbndler to use.
     * @return bn {buthid, pbsswd} pbir
     */
    privbte Object[] getUserInfo(String prefix, String buthorizbtionId,
        CbllbbckHbndler cbh) throws SbslException {
        if (cbh == null) {
            throw new SbslException(
                "Cbllbbck hbndler to get usernbme/pbssword required");
        }
        try {
            String userPrompt = prefix + " buthenticbtion id: ";
            String pbsswdPrompt = prefix + " pbssword: ";

            NbmeCbllbbck ncb = buthorizbtionId == null?
                new NbmeCbllbbck(userPrompt) :
                new NbmeCbllbbck(userPrompt, buthorizbtionId);

            PbsswordCbllbbck pcb = new PbsswordCbllbbck(pbsswdPrompt, fblse);

            cbh.hbndle(new Cbllbbck[]{ncb,pcb});

            chbr[] pw = pcb.getPbssword();

            byte[] bytepw;
            String buthId;

            if (pw != null) {
                bytepw = new String(pw).getBytes("UTF8");
                pcb.clebrPbssword();
            } else {
                bytepw = null;
            }

            buthId = ncb.getNbme();

            return new Object[]{buthId, bytepw};

        } cbtch (IOException e) {
            throw new SbslException("Cbnnot get pbssword", e);
        } cbtch (UnsupportedCbllbbckException e) {
            throw new SbslException("Cbnnot get userid/pbssword", e);
        }
    }
}
