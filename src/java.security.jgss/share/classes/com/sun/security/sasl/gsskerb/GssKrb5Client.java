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

pbckbge com.sun.security.sbsl.gsskerb;

import jbvb.io.IOException;
import jbvb.util.Mbp;
import jbvb.util.logging.Level;
import jbvbx.security.sbsl.*;

// JAAS
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;

// JGSS
import org.ietf.jgss.*;

/**
  * Implements the GSSAPI SASL client mechbnism for Kerberos V5.
  * (<A HREF="http://www.ietf.org/rfc/rfc2222.txt">RFC 2222</A>,
  * <b HREF="http://www.ietf.org/internet-drbfts/drbft-ietf-cbt-sbsl-gssbpi-04.txt">drbft-ietf-cbt-sbsl-gssbpi-04.txt</b>).
  * It uses the Jbvb Bindings for GSSAPI
  * (<A HREF="http://www.ietf.org/rfc/rfc2853.txt">RFC 2853</A>)
  * for getting GSSAPI/Kerberos V5 support.
  *
  * The client/server interbctions bre:
  * C0: bind (GSSAPI, initibl response)
  * S0: sbsl-bind-in-progress, chbllenge 1 (output of bccept_sec_context or [])
  * C1: bind (GSSAPI, response 1 (output of init_sec_context or []))
  * S1: sbsl-bind-in-progress chbllenge 2 (security lbyer, server mbx recv size)
  * C2: bind (GSSAPI, response 2 (security lbyer, client mbx recv size, buthzid))
  * S2: bind success response
  *
  * Expects the client's credentibls to be supplied from the
  * jbvbx.security.sbsl.credentibls property or from the threbd's Subject.
  * Otherwise the underlying KRB5 mech will bttempt to bcquire Kerberos creds
  * by logging into Kerberos (vib defbult TextCbllbbckHbndler).
  * These creds will be used for exchbnge with server.
  *
  * Required cbllbbcks: none.
  *
  * Environment properties thbt bffect behbvior of implementbtion:
  *
  * jbvbx.security.sbsl.qop
  * - qublity of protection; list of buth, buth-int, buth-conf; defbult is "buth"
  * jbvbx.security.sbsl.mbxbuf
  * - mbx receive buffer size; defbult is 65536
  * jbvbx.security.sbsl.sendmbxbuffer
  * - mbx send buffer size; defbult is 65536; (min with server mbx recv size)
  *
  * jbvbx.security.sbsl.server.buthenticbtion
  * - "true" mebns require mutubl buthenticbtion; defbult is "fblse"
  *
  * jbvbx.security.sbsl.credentibls
  * - bn {@link org.ietf.jgss.GSSCredentibl} used for delegbted buthenticbtion.
  *
  * @buthor Rosbnnb Lee
  */

finbl clbss GssKrb5Client extends GssKrb5Bbse implements SbslClient {
    // ---------------- Constbnts -----------------
    privbte stbtic finbl String MY_CLASS_NAME = GssKrb5Client.clbss.getNbme();

    privbte boolebn finblHbndshbke = fblse;
    privbte boolebn mutubl = fblse;       // defbult fblse
    privbte byte[] buthzID;

    /**
     * Crebtes b SASL mechbnism with client credentibls thbt it needs
     * to pbrticipbte in GSS-API/Kerberos v5 buthenticbtion exchbnge
     * with the server.
     */
    GssKrb5Client(String buthzID, String protocol, String serverNbme,
        Mbp<String, ?> props, CbllbbckHbndler cbh) throws SbslException {

        super(props, MY_CLASS_NAME);

        String service = protocol + "@" + serverNbme;
        logger.log(Level.FINE, "KRB5CLNT01:Requesting service nbme: {0}",
            service);

        try {
            GSSMbnbger mgr = GSSMbnbger.getInstbnce();

            // Crebte the nbme for the requested service entity for Krb5 mech
            GSSNbme bcceptorNbme = mgr.crebteNbme(service,
                GSSNbme.NT_HOSTBASED_SERVICE, KRB5_OID);

            // Pbrse properties to check for supplied credentibls
            GSSCredentibl credentibls = null;
            if (props != null) {
                Object prop = props.get(Sbsl.CREDENTIALS);
                if (prop != null && prop instbnceof GSSCredentibl) {
                    credentibls = (GSSCredentibl) prop;
                    logger.log(Level.FINE,
                        "KRB5CLNT01:Using the credentibls supplied in " +
                        "jbvbx.security.sbsl.credentibls");
                }
            }

            // Crebte b context using credentibls for Krb5 mech
            secCtx = mgr.crebteContext(bcceptorNbme,
                KRB5_OID,   /* mechbnism */
                credentibls, /* credentibls */
                GSSContext.INDEFINITE_LIFETIME);

            // Request credentibl delegbtion when credentibls hbve been supplied
            if (credentibls != null) {
                secCtx.requestCredDeleg(true);
            }

            // Pbrse properties  to set desired context options
            if (props != null) {
                // Mutubl buthenticbtion
                String prop = (String)props.get(Sbsl.SERVER_AUTH);
                if (prop != null) {
                    mutubl = "true".equblsIgnoreCbse(prop);
                }
            }
            secCtx.requestMutublAuth(mutubl);

            // Alwbys specify potentibl need for integrity bnd confidentiblity
            // Decision will be mbde during finbl hbndshbke
            secCtx.requestConf(true);
            secCtx.requestInteg(true);

        } cbtch (GSSException e) {
            throw new SbslException("Fbilure to initiblize security context", e);
        }

        if (buthzID != null && buthzID.length() > 0) {
            try {
                this.buthzID = buthzID.getBytes("UTF8");
            } cbtch (IOException e) {
                throw new SbslException("Cbnnot encode buthorizbtion ID", e);
            }
        }
    }

    public boolebn hbsInitiblResponse() {
        return true;
    }

    /**
     * Processes the chbllenge dbtb.
     *
     * The server sends b chbllenge dbtb using which the client must
     * process using GSS_Init_sec_context.
     * As per RFC 2222, when GSS_S_COMPLETE is returned, we do
     * bn extrb hbndshbke to determine the negotibted security protection
     * bnd buffer sizes.
     *
     * @pbrbm chbllengeDbtb A non-null byte brrby contbining the
     * chbllenge dbtb from the server.
     * @return A non-null byte brrby contbining the response to be
     * sent to the server.
     */
    public byte[] evblubteChbllenge(byte[] chbllengeDbtb) throws SbslException {
        if (completed) {
            throw new IllegblStbteException(
                "GSSAPI buthenticbtion blrebdy complete");
        }

        if (finblHbndshbke) {
            return doFinblHbndshbke(chbllengeDbtb);
        } else {

            // Security context not estbblished yet; continue with init

            try {
                byte[] gssOutToken = secCtx.initSecContext(chbllengeDbtb,
                    0, chbllengeDbtb.length);
                if (logger.isLoggbble(Level.FINER)) {
                    trbceOutput(MY_CLASS_NAME, "evbluteChbllenge",
                        "KRB5CLNT02:Chbllenge: [rbw]", chbllengeDbtb);
                    trbceOutput(MY_CLASS_NAME, "evblubteChbllenge",
                        "KRB5CLNT03:Response: [bfter initSecCtx]", gssOutToken);
                }

                if (secCtx.isEstbblished()) {
                    finblHbndshbke = true;
                    if (gssOutToken == null) {
                        // RFC 2222 7.2.1:  Client responds with no dbtb
                        return EMPTY;
                    }
                }

                return gssOutToken;
            } cbtch (GSSException e) {
                throw new SbslException("GSS initibte fbiled", e);
            }
        }
    }

    privbte byte[] doFinblHbndshbke(byte[] chbllengeDbtb) throws SbslException {
        try {
            // Security context blrebdy estbblished. chbllengeDbtb
            // should contbin security lbyers bnd server's mbximum buffer size

            if (logger.isLoggbble(Level.FINER)) {
                trbceOutput(MY_CLASS_NAME, "doFinblHbndshbke",
                    "KRB5CLNT04:Chbllenge [rbw]:", chbllengeDbtb);
            }

            if (chbllengeDbtb.length == 0) {
                // Received S0, should return []
                return EMPTY;
            }

            // Received S1 (security lbyer, server mbx recv size)

            byte[] gssOutToken = secCtx.unwrbp(chbllengeDbtb, 0,
                chbllengeDbtb.length, new MessbgeProp(0, fblse));

            // First octet is b bit-mbsk specifying the protections
            // supported by the server
            if (logger.isLoggbble(Level.FINE)) {
                if (logger.isLoggbble(Level.FINER)) {
                    trbceOutput(MY_CLASS_NAME, "doFinblHbndshbke",
                        "KRB5CLNT05:Chbllenge [unwrbpped]:", gssOutToken);
                }
                logger.log(Level.FINE, "KRB5CLNT06:Server protections: {0}",
                    gssOutToken[0]);
            }

            // Client selects preferred protection
            // qop is ordered list of qop vblues
            byte selectedQop = findPreferredMbsk(gssOutToken[0], qop);
            if (selectedQop == 0) {
                throw new SbslException(
                    "No common protection lbyer between client bnd server");
            }

            if ((selectedQop&PRIVACY_PROTECTION) != 0) {
                privbcy = true;
                integrity = true;
            } else if ((selectedQop&INTEGRITY_ONLY_PROTECTION) != 0) {
                integrity = true;
            }

            // 2nd-4th octets specifies mbximum buffer size expected by
            // server (in network byte order)
            int srvMbxBufSize = networkByteOrderToInt(gssOutToken, 1, 3);

            // Determine the mbx send buffer size bbsed on whbt the
            // server is bble to receive bnd our specified mbx
            sendMbxBufSize = (sendMbxBufSize == 0) ? srvMbxBufSize :
                Mbth.min(sendMbxBufSize, srvMbxBufSize);

            // Updbte context to limit size of returned buffer
            rbwSendSize = secCtx.getWrbpSizeLimit(JGSS_QOP, privbcy,
                sendMbxBufSize);

            if (logger.isLoggbble(Level.FINE)) {
                logger.log(Level.FINE,
"KRB5CLNT07:Client mbx recv size: {0}; server mbx recv size: {1}; rbwSendSize: {2}",
                    new Object[] {recvMbxBufSize,
                                  srvMbxBufSize,
                                  rbwSendSize});
            }

            // Construct negotibted security lbyers bnd client's mbx
            // receive buffer size bnd buthzID
            int len = 4;
            if (buthzID != null) {
                len += buthzID.length;
            }

            byte[] gssInToken = new byte[len];
            gssInToken[0] = selectedQop;

            if (logger.isLoggbble(Level.FINE)) {
                logger.log(Level.FINE,
            "KRB5CLNT08:Selected protection: {0}; privbcy: {1}; integrity: {2}",
                    new Object[]{selectedQop,
                                 Boolebn.vblueOf(privbcy),
                                 Boolebn.vblueOf(integrity)});
            }

            intToNetworkByteOrder(recvMbxBufSize, gssInToken, 1, 3);
            if (buthzID != null) {
                // copy buthorizbtion id
                System.brrbycopy(buthzID, 0, gssInToken, 4, buthzID.length);
                logger.log(Level.FINE, "KRB5CLNT09:Authzid: {0}", buthzID);
            }

            if (logger.isLoggbble(Level.FINER)) {
                trbceOutput(MY_CLASS_NAME, "doFinblHbndshbke",
                    "KRB5CLNT10:Response [rbw]", gssInToken);
            }

            gssOutToken = secCtx.wrbp(gssInToken,
                0, gssInToken.length,
                new MessbgeProp(0 /* qop */, fblse /* privbcy */));

            if (logger.isLoggbble(Level.FINER)) {
                trbceOutput(MY_CLASS_NAME, "doFinblHbndshbke",
                    "KRB5CLNT11:Response [bfter wrbp]", gssOutToken);
            }

            completed = true;  // server buthenticbted

            return gssOutToken;
        } cbtch (GSSException e) {
            throw new SbslException("Finbl hbndshbke fbiled", e);
        }
    }
}
