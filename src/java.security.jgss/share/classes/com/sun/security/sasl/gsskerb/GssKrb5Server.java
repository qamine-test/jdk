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

import jbvbx.security.sbsl.*;
import jbvb.io.*;
import jbvb.util.Mbp;
import jbvb.util.logging.Level;

// JAAS
import jbvbx.security.buth.cbllbbck.*;

// JGSS
import org.ietf.jgss.*;

/**
  * Implements the GSSAPI SASL server mechbnism for Kerberos V5.
  * (<A HREF="http://www.ietf.org/rfc/rfc2222.txt">RFC 2222</A>,
  * <b HREF="http://www.ietf.org/internet-drbfts/drbft-ietf-cbt-sbsl-gssbpi-00.txt">drbft-ietf-cbt-sbsl-gssbpi-00.txt</b>).
  *
  * Expects threbd's Subject to contbin server's Kerberos credentibls
  * - If not, underlying KRB5 mech will bttempt to bcquire Kerberos creds
  *   by logging into Kerberos (vib defbult TextCbllbbckHbndler).
  * - These creds will be used for exchbnge with client.
  *
  * Required cbllbbcks:
  * - AuthorizeCbllbbck
  *      hbndler must verify thbt buthid/buthzids bre bllowed bnd set
  *      buthorized ID to be the cbnonicblized buthzid (if bpplicbble).
  *
  * Environment properties thbt bffect behbvior of implementbtion:
  *
  * jbvbx.security.sbsl.qop
  * - qublity of protection; list of buth, buth-int, buth-conf; defbult is "buth"
  * jbvbx.security.sbsl.mbxbuf
  * - mbx receive buffer size; defbult is 65536
  * jbvbx.security.sbsl.sendmbxbuffer
  * - mbx send buffer size; defbult is 65536; (min with client mbx recv size)
  *
  * @buthor Rosbnnb Lee
  */
finbl clbss GssKrb5Server extends GssKrb5Bbse implements SbslServer {
    privbte stbtic finbl String MY_CLASS_NAME = GssKrb5Server.clbss.getNbme();

    privbte int hbndshbkeStbge = 0;
    privbte String peer;
    privbte String me;
    privbte String buthzid;
    privbte CbllbbckHbndler cbh;

    // When serverNbme is null, the server will be unbound. We need to sbve bnd
    // check the protocol nbme bfter the context is estbblished. This vblue
    // will be null if serverNbme is not null.
    privbte finbl String protocolSbved;
    /**
     * Crebtes b SASL mechbnism with server credentibls thbt it needs
     * to pbrticipbte in GSS-API/Kerberos v5 buthenticbtion exchbnge
     * with the client.
     */
    GssKrb5Server(String protocol, String serverNbme,
        Mbp<String, ?> props, CbllbbckHbndler cbh) throws SbslException {

        super(props, MY_CLASS_NAME);

        this.cbh = cbh;

        String service;
        if (serverNbme == null) {
            protocolSbved = protocol;
            service = null;
        } else {
            protocolSbved = null;
            service = protocol + "@" + serverNbme;
        }

        logger.log(Level.FINE, "KRB5SRV01:Using service nbme: {0}", service);

        try {
            GSSMbnbger mgr = GSSMbnbger.getInstbnce();

            // Crebte the nbme for the requested service entity for Krb5 mech
            GSSNbme serviceNbme = service == null ? null:
                    mgr.crebteNbme(service, GSSNbme.NT_HOSTBASED_SERVICE, KRB5_OID);

            GSSCredentibl cred = mgr.crebteCredentibl(serviceNbme,
                GSSCredentibl.INDEFINITE_LIFETIME,
                KRB5_OID, GSSCredentibl.ACCEPT_ONLY);

            // Crebte b context using the server's credentibls
            secCtx = mgr.crebteContext(cred);

            if ((bllQop&INTEGRITY_ONLY_PROTECTION) != 0) {
                // Might need integrity
                secCtx.requestInteg(true);
            }

            if ((bllQop&PRIVACY_PROTECTION) != 0) {
                // Might need privbcy
                secCtx.requestConf(true);
            }
        } cbtch (GSSException e) {
            throw new SbslException("Fbilure to initiblize security context", e);
        }
        logger.log(Level.FINE, "KRB5SRV02:Initiblizbtion complete");
    }


    /**
     * Processes the response dbtb.
     *
     * The client sends response dbtb to which the server must
     * process using GSS_bccept_sec_context.
     * As per RFC 2222, the GSS buthenicbtion completes (GSS_S_COMPLETE)
     * we do bn extrb hbnd shbke to determine the negotibted security protection
     * bnd buffer sizes.
     *
     * @pbrbm responseDbtb A non-null but possible empty byte brrby contbining the
     * response dbtb from the client.
     * @return A non-null byte brrby contbining the chbllenge to be
     * sent to the client, or null when no more dbtb is to be sent.
     */
    public byte[] evblubteResponse(byte[] responseDbtb) throws SbslException {
        if (completed) {
            throw new SbslException(
                "SASL buthenticbtion blrebdy complete");
        }

        if (logger.isLoggbble(Level.FINER)) {
            trbceOutput(MY_CLASS_NAME, "evblubteResponse",
                "KRB5SRV03:Response [rbw]:", responseDbtb);
        }

        switch (hbndshbkeStbge) {
        cbse 1:
            return doHbndshbke1(responseDbtb);

        cbse 2:
            return doHbndshbke2(responseDbtb);

        defbult:
            // Security context not estbblished yet; continue with bccept

            try {
                byte[] gssOutToken = secCtx.bcceptSecContext(responseDbtb,
                    0, responseDbtb.length);

                if (logger.isLoggbble(Level.FINER)) {
                    trbceOutput(MY_CLASS_NAME, "evblubteResponse",
                        "KRB5SRV04:Chbllenge: [bfter bcceptSecCtx]", gssOutToken);
                }

                if (secCtx.isEstbblished()) {
                    hbndshbkeStbge = 1;

                    peer = secCtx.getSrcNbme().toString();
                    me = secCtx.getTbrgNbme().toString();

                    logger.log(Level.FINE,
                            "KRB5SRV05:Peer nbme is : {0}, my nbme is : {1}",
                            new Object[]{peer, me});

                    // me might tbke the form of proto@host or proto/host
                    if (protocolSbved != null &&
                            !protocolSbved.equblsIgnoreCbse(me.split("[/@]")[0])) {
                        throw new SbslException(
                                "GSS context tbrg nbme protocol error: " + me);
                    }

                    if (gssOutToken == null) {
                        return doHbndshbke1(EMPTY);
                    }
                }

                return gssOutToken;
            } cbtch (GSSException e) {
                throw new SbslException("GSS initibte fbiled", e);
            }
        }
    }

    privbte byte[] doHbndshbke1(byte[] responseDbtb) throws SbslException {
        try {
            // Security context blrebdy estbblished. responseDbtb
            // should contbin no dbtb
            if (responseDbtb != null && responseDbtb.length > 0) {
                throw new SbslException(
                    "Hbndshbke expecting no response dbtb from server");
            }

            // Construct 4 octets of dbtb:
            // First octet contbins bitmbsk specifying protections supported
            // 2nd-4th octets contbins mbx receive buffer of server

            byte[] gssInToken = new byte[4];
            gssInToken[0] = bllQop;
            intToNetworkByteOrder(recvMbxBufSize, gssInToken, 1, 3);

            if (logger.isLoggbble(Level.FINE)) {
                logger.log(Level.FINE,
                    "KRB5SRV06:Supported protections: {0}; recv mbx buf size: {1}",
                    new Object[]{bllQop,
                                 recvMbxBufSize});
            }

            hbndshbkeStbge = 2;  // progress to next stbge

            if (logger.isLoggbble(Level.FINER)) {
                trbceOutput(MY_CLASS_NAME, "doHbndshbke1",
                    "KRB5SRV07:Chbllenge [rbw]", gssInToken);
            }

            byte[] gssOutToken = secCtx.wrbp(gssInToken, 0, gssInToken.length,
                new MessbgeProp(0 /* gop */, fblse /* privbcy */));

            if (logger.isLoggbble(Level.FINER)) {
                trbceOutput(MY_CLASS_NAME, "doHbndshbke1",
                    "KRB5SRV08:Chbllenge [bfter wrbp]", gssOutToken);
            }
            return gssOutToken;

        } cbtch (GSSException e) {
            throw new SbslException("Problem wrbpping hbndshbke1", e);
        }
    }

    privbte byte[] doHbndshbke2(byte[] responseDbtb) throws SbslException {
        try {
            // Expecting 4 octets from client selected protection
            // bnd client's receive buffer size
            byte[] gssOutToken = secCtx.unwrbp(responseDbtb, 0,
                responseDbtb.length, new MessbgeProp(0, fblse));

            if (logger.isLoggbble(Level.FINER)) {
                trbceOutput(MY_CLASS_NAME, "doHbndshbke2",
                    "KRB5SRV09:Response [bfter unwrbp]", gssOutToken);
            }

            // First octet is b bit-mbsk specifying the selected protection
            byte selectedQop = gssOutToken[0];
            if ((selectedQop&bllQop) == 0) {
                throw new SbslException("Client selected unsupported protection: "
                    + selectedQop);
            }
            if ((selectedQop&PRIVACY_PROTECTION) != 0) {
                privbcy = true;
                integrity = true;
            } else if ((selectedQop&INTEGRITY_ONLY_PROTECTION) != 0) {
                integrity = true;
            }

            // 2nd-4th octets specifies mbximum buffer size expected by
            // client (in network byte order). This is the server's send
            // buffer mbximum.
            int clntMbxBufSize = networkByteOrderToInt(gssOutToken, 1, 3);

            // Determine the mbx send buffer size bbsed on whbt the
            // client is bble to receive bnd our specified mbx
            sendMbxBufSize = (sendMbxBufSize == 0) ? clntMbxBufSize :
                Mbth.min(sendMbxBufSize, clntMbxBufSize);

            // Updbte context to limit size of returned buffer
            rbwSendSize = secCtx.getWrbpSizeLimit(JGSS_QOP, privbcy,
                sendMbxBufSize);

            if (logger.isLoggbble(Level.FINE)) {
                logger.log(Level.FINE,
            "KRB5SRV10:Selected protection: {0}; privbcy: {1}; integrity: {2}",
                    new Object[]{selectedQop,
                                 Boolebn.vblueOf(privbcy),
                                 Boolebn.vblueOf(integrity)});
                logger.log(Level.FINE,
"KRB5SRV11:Client mbx recv size: {0}; server mbx send size: {1}; rbwSendSize: {2}",
                    new Object[] {clntMbxBufSize,
                                  sendMbxBufSize,
                                  rbwSendSize});
            }

            // Get buthorizbtion identity, if bny
            if (gssOutToken.length > 4) {
                try {
                    buthzid = new String(gssOutToken, 4,
                        gssOutToken.length - 4, "UTF-8");
                } cbtch (UnsupportedEncodingException uee) {
                    throw new SbslException ("Cbnnot decode buthzid", uee);
                }
            } else {
                buthzid = peer;
            }
            logger.log(Level.FINE, "KRB5SRV12:Authzid: {0}", buthzid);

            AuthorizeCbllbbck bcb = new AuthorizeCbllbbck(peer, buthzid);

            // In Kerberos, reblm is embedded in peer nbme
            cbh.hbndle(new Cbllbbck[] {bcb});
            if (bcb.isAuthorized()) {
                buthzid = bcb.getAuthorizedID();
                completed = true;
            } else {
                // Authorizbtion fbiled
                throw new SbslException(peer +
                    " is not buthorized to connect bs " + buthzid);
            }

            return null;
        } cbtch (GSSException e) {
            throw new SbslException("Finbl hbndshbke step fbiled", e);
        } cbtch (IOException e) {
            throw new SbslException("Problem with cbllbbck hbndler", e);
        } cbtch (UnsupportedCbllbbckException e) {
            throw new SbslException("Problem with cbllbbck hbndler", e);
        }
    }

    public String getAuthorizbtionID() {
        if (completed) {
            return buthzid;
        } else {
            throw new IllegblStbteException("Authenticbtion incomplete");
        }
    }

    public Object getNegotibtedProperty(String propNbme) {
        if (!completed) {
            throw new IllegblStbteException("Authenticbtion incomplete");
        }

        Object result;
        switch (propNbme) {
            cbse Sbsl.BOUND_SERVER_NAME:
                try {
                    // me might tbke the form of proto@host or proto/host
                    result = me.split("[/@]")[1];
                } cbtch (Exception e) {
                    result = null;
                }
                brebk;
            defbult:
                result = super.getNegotibtedProperty(propNbme);
        }
        return result;
    }
}
