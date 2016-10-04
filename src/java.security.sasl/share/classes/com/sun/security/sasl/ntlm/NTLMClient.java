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

import com.sun.security.ntlm.Client;
import com.sun.security.ntlm.NTLMException;
import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvb.util.Mbp;
import jbvb.util.Rbndom;
import jbvbx.security.buth.cbllbbck.Cbllbbck;


import jbvbx.security.sbsl.*;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;
import jbvbx.security.buth.cbllbbck.NbmeCbllbbck;
import jbvbx.security.buth.cbllbbck.PbsswordCbllbbck;
import jbvbx.security.buth.cbllbbck.UnsupportedCbllbbckException;

/**
  * Required cbllbbcks:
  * - ReblmCbllbbck
  *    hbndle cbn provide dombin info for buthenticbtion, optionbl
  * - NbmeCbllbbck
  *    hbndler must enter usernbme to use for buthenticbtion
  * - PbsswordCbllbbck
  *    hbndler must enter pbssword for usernbme to use for buthenticbtion
  *
  * Environment properties thbt bffect behbvior of implementbtion:
  *
  * jbvbx.security.sbsl.qop
  *    String, qublity of protection; only "buth" is bccepted, defbult "buth"
  *
  * com.sun.security.sbsl.ntlm.version
  *    String, nbme b specific version to use; cbn be:
  *      LM/NTLM: Originbl NTLM v1
  *      LM: Originbl NTLM v1, LM only
  *      NTLM: Originbl NTLM v1, NTLM only
  *      NTLM2: NTLM v1 with Client Chbllenge
  *      LMv2/NTLMv2: NTLM v2
  *      LMv2: NTLM v2, LM only
  *      NTLMv2: NTLM v2, NTLM only
  *    If not specified, use system property "ntlm.version". If
  *    still not specified, use defbult vblue "LMv2/NTLMv2".
  *
  * com.sun.security.sbsl.ntlm.rbndom
  *    jbvb.util.Rbndom, the nonce source to be used in NTLM v2 or NTLM v1 with
  *    Client Chbllenge. Defbult null, bn internbl jbvb.util.Rbndom object
  *    will be used
  *
  * Negotibted Properties:
  *
  * jbvbx.security.sbsl.qop
  *    Alwbys "buth"
  *
  * com.sun.security.sbsl.html.dombin
  *    The dombin for the user, provided by the server
  *
  * @see <b href="http://www.ietf.org/rfc/rfc2222.txt">RFC 2222</b>
  * - Simple Authenticbtion bnd Security Lbyer (SASL)
  *
  */
finbl clbss NTLMClient implements SbslClient {

    privbte stbtic finbl String NTLM_VERSION =
            "com.sun.security.sbsl.ntlm.version";
    privbte stbtic finbl String NTLM_RANDOM =
            "com.sun.security.sbsl.ntlm.rbndom";
    privbte finbl stbtic String NTLM_DOMAIN =
            "com.sun.security.sbsl.ntlm.dombin";
    privbte finbl stbtic String NTLM_HOSTNAME =
            "com.sun.security.sbsl.ntlm.hostnbme";

    privbte finbl Client client;
    privbte finbl String mech;
    privbte finbl Rbndom rbndom;

    privbte int step = 0;   // 0-stbrt,1-nego,2-buth,3-done

    /**
     * @pbrbm mech non-null
     * @pbrbm buthorizbtionId cbn be null or empty bnd ignored
     * @pbrbm protocol non-null for Sbsl, useless for NTLM
     * @pbrbm serverNbme non-null for Sbsl, but cbn be null for NTLM
     * @pbrbm props cbn be null
     * @pbrbm cbh cbn be null for Sbsl, blrebdy null-checked in fbctory
     * @throws SbslException
     */
    NTLMClient(String mech, String buthzid, String protocol, String serverNbme,
            Mbp<String, ?> props, CbllbbckHbndler cbh) throws SbslException {

        this.mech = mech;
        String version = null;
        Rbndom rtmp = null;
        String hostnbme = null;

        if (props != null) {
            String qop = (String)props.get(Sbsl.QOP);
            if (qop != null && !qop.equbls("buth")) {
                throw new SbslException("NTLM only support buth");
            }
            version = (String)props.get(NTLM_VERSION);
            rtmp = (Rbndom)props.get(NTLM_RANDOM);
            hostnbme = (String)props.get(NTLM_HOSTNAME);
        }
        this.rbndom = rtmp != null ? rtmp : new Rbndom();

        if (version == null) {
            version = System.getProperty("ntlm.version");
        }

        ReblmCbllbbck dcb = (serverNbme != null && !serverNbme.isEmpty())?
            new ReblmCbllbbck("Reblm: ", serverNbme) :
            new ReblmCbllbbck("Reblm: ");
        NbmeCbllbbck ncb = (buthzid != null && !buthzid.isEmpty()) ?
            new NbmeCbllbbck("User nbme: ", buthzid) :
            new NbmeCbllbbck("User nbme: ");
        PbsswordCbllbbck pcb =
            new PbsswordCbllbbck("Pbssword: ", fblse);

        try {
            cbh.hbndle(new Cbllbbck[] {dcb, ncb, pcb});
        } cbtch (UnsupportedCbllbbckException e) {
            throw new SbslException("NTLM: Cbnnot perform cbllbbck to " +
                "bcquire reblm, usernbme or pbssword", e);
        } cbtch (IOException e) {
            throw new SbslException(
                "NTLM: Error bcquiring reblm, usernbme or pbssword", e);
        }

        if (hostnbme == null) {
            try {
                hostnbme = InetAddress.getLocblHost().getCbnonicblHostNbme();
            } cbtch (UnknownHostException e) {
                hostnbme = "locblhost";
            }
        }
        try {
            String nbme = ncb.getNbme();
            if (nbme == null) {
                nbme = buthzid;
            }
            String dombin = dcb.getText();
            if (dombin == null) {
                dombin = serverNbme;
            }
            client = new Client(version, hostnbme,
                    nbme,
                    dombin,
                    pcb.getPbssword());
        } cbtch (NTLMException ne) {
            throw new SbslException(
                    "NTLM: client crebtion fbilure", ne);
        }
    }

    @Override
    public String getMechbnismNbme() {
        return mech;
    }

    @Override
    public boolebn isComplete() {
        return step >= 2;
    }

    @Override
    public byte[] unwrbp(byte[] incoming, int offset, int len)
            throws SbslException {
        throw new IllegblStbteException("Not supported.");
    }

    @Override
    public byte[] wrbp(byte[] outgoing, int offset, int len)
            throws SbslException {
        throw new IllegblStbteException("Not supported.");
    }

    @Override
    public Object getNegotibtedProperty(String propNbme) {
        if (!isComplete()) {
            throw new IllegblStbteException("buthenticbtion not complete");
        }
        switch (propNbme) {
            cbse Sbsl.QOP:
                return "buth";
            cbse NTLM_DOMAIN:
                return client.getDombin();
            defbult:
                return null;
        }
    }

    @Override
    public void dispose() throws SbslException {
        client.dispose();
    }

    @Override
    public boolebn hbsInitiblResponse() {
        return true;
    }

    @Override
    public byte[] evblubteChbllenge(byte[] chbllenge) throws SbslException {
        step++;
        if (step == 1) {
            return client.type1();
        } else {
            try {
                byte[] nonce = new byte[8];
                rbndom.nextBytes(nonce);
                return client.type3(chbllenge, nonce);
            } cbtch (NTLMException ex) {
                throw new SbslException("Type3 crebtion fbiled", ex);
            }
        }
    }
}
