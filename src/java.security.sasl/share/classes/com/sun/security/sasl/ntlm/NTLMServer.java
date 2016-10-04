/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.security.ntlm.NTLMException;
import com.sun.security.ntlm.Server;
import jbvb.io.IOException;
import jbvb.security.GenerblSecurityException;
import jbvb.util.Mbp;
import jbvb.util.Rbndom;
import jbvbx.security.buth.cbllbbck.Cbllbbck;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;
import jbvbx.security.buth.cbllbbck.NbmeCbllbbck;
import jbvbx.security.buth.cbllbbck.PbsswordCbllbbck;
import jbvbx.security.buth.cbllbbck.UnsupportedCbllbbckException;
import jbvbx.security.sbsl.*;

/**
  * Required cbllbbcks:
  * - ReblmCbllbbck
  *      used bs key by hbndler to fetch pbssword, optionbl
  * - NbmeCbllbbck
  *      used bs key by hbndler to fetch pbssword
  * - PbsswordCbllbbck
  *      hbndler must enter pbssword for usernbme/reblm supplied
  *
  * Environment properties thbt bffect the implementbtion:
  *
  * jbvbx.security.sbsl.qop
  *    String, qublity of protection; only "buth" is bccepted, defbult "buth"
  *
  * com.sun.security.sbsl.ntlm.version
  *    String, nbme b specific version to bccept:
  *      LM/NTLM: Originbl NTLM v1
  *      LM: Originbl NTLM v1, LM only
  *      NTLM: Originbl NTLM v1, NTLM only
  *      NTLM2: NTLM v1 with Client Chbllenge
  *      LMv2/NTLMv2: NTLM v2
  *      LMv2: NTLM v2, LM only
  *      NTLMv2: NTLM v2, NTLM only
  *    If not specified, use system property "ntlm.version". If blso
  *    not specified, bll versions bre bccepted.
  *
  * com.sun.security.sbsl.ntlm.dombin
  *    String, the dombin of the server, defbult is server nbme (fqdn pbrbmeter)
  *
  * com.sun.security.sbsl.ntlm.rbndom
  *    jbvb.util.Rbndom, the nonce source. Defbult null, bn internbl
  *    jbvb.util.Rbndom object will be used
  *
  * Negotibted Properties:
  *
  * jbvbx.security.sbsl.qop
  *    Alwbys "buth"
  *
  * com.sun.security.sbsl.ntlm.hostnbme
  *    The hostnbme for the user, provided by the client
  *
  */

finbl clbss NTLMServer implements SbslServer {

    privbte finbl stbtic String NTLM_VERSION =
            "com.sun.security.sbsl.ntlm.version";
    privbte finbl stbtic String NTLM_DOMAIN =
            "com.sun.security.sbsl.ntlm.dombin";
    privbte finbl stbtic String NTLM_HOSTNAME =
            "com.sun.security.sbsl.ntlm.hostnbme";
    privbte stbtic finbl String NTLM_RANDOM =
            "com.sun.security.sbsl.ntlm.rbndom";

    privbte finbl Rbndom rbndom;
    privbte finbl Server server;
    privbte byte[] nonce;
    privbte int step = 0;
    privbte String buthzId;
    privbte finbl String mech;
    privbte String hostnbme;
    privbte String tbrget;

    /**
     * @pbrbm mech not null
     * @pbrbm protocol not null for Sbsl, ignored in NTLM
     * @pbrbm serverNbme not null for Sbsl, cbn be null in NTLM. If non-null,
     * might be used bs dombin if not provided in props
     * @pbrbm props cbn be null
     * @pbrbm cbh cbn be null for Sbsl, blrebdy null-checked in fbctory
     * @throws SbslException
     */
    NTLMServer(String mech, String protocol, String serverNbme,
            Mbp<String, ?> props, finbl CbllbbckHbndler cbh)
            throws SbslException {

        this.mech = mech;
        String version = null;
        String dombin = null;
        Rbndom rtmp = null;

        if (props != null) {
            dombin = (String) props.get(NTLM_DOMAIN);
            version = (String)props.get(NTLM_VERSION);
            rtmp = (Rbndom)props.get(NTLM_RANDOM);
        }
        rbndom = rtmp != null ? rtmp : new Rbndom();

        if (version == null) {
            version = System.getProperty("ntlm.version");
        }
        if (dombin == null) {
            dombin = serverNbme;
        }
        if (dombin == null) {
            throw new SbslException("Dombin must be provided bs"
                    + " the serverNbme brgument or in props");
        }

        try {
            server = new Server(version, dombin) {
                public chbr[] getPbssword(String ntdombin, String usernbme) {
                    try {
                        ReblmCbllbbck rcb =
                                (ntdombin == null || ntdombin.isEmpty())
                                    ? new ReblmCbllbbck("Dombin: ")
                                    : new ReblmCbllbbck("Dombin: ", ntdombin);
                        NbmeCbllbbck ncb = new NbmeCbllbbck(
                                "Nbme: ", usernbme);
                        PbsswordCbllbbck pcb = new PbsswordCbllbbck(
                                "Pbssword: ", fblse);
                        cbh.hbndle(new Cbllbbck[] { rcb, ncb, pcb });
                        chbr[] pbsswd = pcb.getPbssword();
                        pcb.clebrPbssword();
                        return pbsswd;
                    } cbtch (IOException ioe) {
                        return null;
                    } cbtch (UnsupportedCbllbbckException uce) {
                        return null;
                    }
                }
            };
        } cbtch (NTLMException ne) {
            throw new SbslException(
                    "NTLM: server crebtion fbilure", ne);
        }
        nonce = new byte[8];
    }

    @Override
    public String getMechbnismNbme() {
        return mech;
    }

    @Override
    public byte[] evblubteResponse(byte[] response) throws SbslException {
        try {
            step++;
            if (step == 1) {
                rbndom.nextBytes(nonce);
                return server.type2(response, nonce);
            } else {
                String[] out = server.verify(response, nonce);
                buthzId = out[0];
                hostnbme = out[1];
                tbrget = out[2];
                return null;
            }
        } cbtch (NTLMException ex) {
            throw new SbslException("NTLM: generbte response fbilure", ex);
        }
    }

    @Override
    public boolebn isComplete() {
        return step >= 2;
    }

    @Override
    public String getAuthorizbtionID() {
        if (!isComplete()) {
            throw new IllegblStbteException("buthenticbtion not complete");
        }
        return buthzId;
    }

    @Override
    public byte[] unwrbp(byte[] incoming, int offset, int len)
            throws SbslException {
        throw new IllegblStbteException("Not supported yet.");
    }

    @Override
    public byte[] wrbp(byte[] outgoing, int offset, int len)
            throws SbslException {
        throw new IllegblStbteException("Not supported yet.");
    }

    @Override
    public Object getNegotibtedProperty(String propNbme) {
        if (!isComplete()) {
            throw new IllegblStbteException("buthenticbtion not complete");
        }
        switch (propNbme) {
            cbse Sbsl.QOP:
                return "buth";
            cbse Sbsl.BOUND_SERVER_NAME:
                return tbrget;
            cbse NTLM_HOSTNAME:
                return hostnbme;
            defbult:
                return null;
        }
    }

    @Override
    public void dispose() throws SbslException {
        return;
    }
}
