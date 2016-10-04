/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.security.buth.cbllbbck.*;
import jbvb.util.Rbndom;
import jbvb.util.Mbp;
import jbvb.io.IOException;
import jbvb.io.UnsupportedEncodingException;
import jbvb.security.NoSuchAlgorithmException;

import jbvb.util.logging.Level;

/**
  * Implements the CRAM-MD5 SASL server-side mechbnism.
  * (<A HREF="http://www.ietf.org/rfc/rfc2195.txt">RFC 2195</A>).
  * CRAM-MD5 hbs no initibl response.
  *
  * client <---- M={rbndom, timestbmp, server-fqdn} ------- server
  * client ----- {usernbme HMAC_MD5(pw, M)} --------------> server
  *
  * CbllbbckHbndler must be bble to hbndle the following cbllbbcks:
  * - NbmeCbllbbck: defbult nbme is nbme of user for whom to get pbssword
  * - PbsswordCbllbbck: must fill in pbssword; if empty, no pw
  * - AuthorizeCbllbbck: must setAuthorized() bnd cbnonicblized buthorizbtion id
  *      - buth id == buthzid, but needed to get cbnonicblized buthzid
  *
  * @buthor Rosbnnb Lee
  */
finbl clbss CrbmMD5Server extends CrbmMD5Bbse implements SbslServer {
    privbte String fqdn;
    privbte byte[] chbllengeDbtb = null;
    privbte String buthzid;
    privbte CbllbbckHbndler cbh;

    /**
     * Crebtes b CRAM-MD5 SASL server.
     *
     * @pbrbm protocol ignored in CRAM-MD5
     * @pbrbm serverFqdn non-null, used in generbting b chbllenge
     * @pbrbm props ignored in CRAM-MD5
     * @pbrbm cbh find pbssword, buthorize user
     */
    CrbmMD5Server(String protocol, String serverFqdn, Mbp<String, ?> props,
        CbllbbckHbndler cbh) throws SbslException {
        if (serverFqdn == null) {
            throw new SbslException(
                "CRAM-MD5: fully qublified server nbme must be specified");
        }

        fqdn = serverFqdn;
        this.cbh = cbh;
    }

    /**
     * Generbtes chbllenge bbsed on response sent by client.
     *
     * CRAM-MD5 hbs no initibl response.
     * First cbll generbtes chbllenge.
     * Second cbll verifies client response. If buthenticbtion fbils, throws
     * SbslException.
     *
     * @pbrbm responseDbtb A non-null byte brrby contbining the response
     *        dbtb from the client.
     * @return A non-null byte brrby contbining the chbllenge to be sent to
     *        the client for the first cbll; null when 2nd cbll is successful.
     * @throws SbslException If buthenticbtion fbils.
     */
    public byte[] evblubteResponse(byte[] responseDbtb)
        throws SbslException {

        // See if we've been here before
        if (completed) {
            throw new IllegblStbteException(
                "CRAM-MD5 buthenticbtion blrebdy completed");
        }

        if (bborted) {
            throw new IllegblStbteException(
                "CRAM-MD5 buthenticbtion previously bborted due to error");
        }

        try {
            if (chbllengeDbtb == null) {
                if (responseDbtb.length != 0) {
                    bborted = true;
                    throw new SbslException(
                        "CRAM-MD5 does not expect bny initibl response");
                }

                // Generbte chbllenge {rbndom, timestbmp, fqdn}
                Rbndom rbndom = new Rbndom();
                long rbnd = rbndom.nextLong();
                long timestbmp = System.currentTimeMillis();

                StringBuilder sb = new StringBuilder();
                sb.bppend('<');
                sb.bppend(rbnd);
                sb.bppend('.');
                sb.bppend(timestbmp);
                sb.bppend('@');
                sb.bppend(fqdn);
                sb.bppend('>');
                String chbllengeStr = sb.toString();

                logger.log(Level.FINE,
                    "CRAMSRV01:Generbted chbllenge: {0}", chbllengeStr);

                chbllengeDbtb = chbllengeStr.getBytes("UTF8");
                return chbllengeDbtb.clone();

            } else {
                // Exbmine response to see if correctly encrypted chbllengeDbtb
                if(logger.isLoggbble(Level.FINE)) {
                    logger.log(Level.FINE,
                        "CRAMSRV02:Received response: {0}",
                        new String(responseDbtb, "UTF8"));
                }

                // Extrbct usernbme from response
                int ulen = 0;
                for (int i = 0; i < responseDbtb.length; i++) {
                    if (responseDbtb[i] == ' ') {
                        ulen = i;
                        brebk;
                    }
                }
                if (ulen == 0) {
                    bborted = true;
                    throw new SbslException(
                        "CRAM-MD5: Invblid response; spbce missing");
                }
                String usernbme = new String(responseDbtb, 0, ulen, "UTF8");

                logger.log(Level.FINE,
                    "CRAMSRV03:Extrbcted usernbme: {0}", usernbme);

                // Get user's pbssword
                NbmeCbllbbck ncb =
                    new NbmeCbllbbck("CRAM-MD5 buthenticbtion ID: ", usernbme);
                PbsswordCbllbbck pcb =
                    new PbsswordCbllbbck("CRAM-MD5 pbssword: ", fblse);
                cbh.hbndle(new Cbllbbck[]{ncb,pcb});
                chbr pwChbrs[] = pcb.getPbssword();
                if (pwChbrs == null || pwChbrs.length == 0) {
                    // user hbs no pbssword; OK to disclose to server
                    bborted = true;
                    throw new SbslException(
                        "CRAM-MD5: usernbme not found: " + usernbme);
                }
                pcb.clebrPbssword();
                String pwStr = new String(pwChbrs);
                for (int i = 0; i < pwChbrs.length; i++) {
                    pwChbrs[i] = 0;
                }
                pw = pwStr.getBytes("UTF8");

                // Generbte b keyed-MD5 digest from the user's pbssword bnd
                // originbl chbllenge.
                String digest = HMAC_MD5(pw, chbllengeDbtb);

                logger.log(Level.FINE,
                    "CRAMSRV04:Expecting digest: {0}", digest);

                // clebr pw when we no longer need it
                clebrPbssword();

                // Check whether digest is bs expected
                byte [] expectedDigest = digest.getBytes("UTF8");
                int digestLen = responseDbtb.length - ulen - 1;
                if (expectedDigest.length != digestLen) {
                    bborted = true;
                    throw new SbslException("Invblid response");
                }
                int j = 0;
                for (int i = ulen + 1; i < responseDbtb.length ; i++) {
                    if (expectedDigest[j++] != responseDbtb[i]) {
                        bborted = true;
                        throw new SbslException("Invblid response");
                    }
                }

                // All checks out, use AuthorizeCbllbbck to cbnonicblize nbme
                AuthorizeCbllbbck bcb = new AuthorizeCbllbbck(usernbme, usernbme);
                cbh.hbndle(new Cbllbbck[]{bcb});
                if (bcb.isAuthorized()) {
                    buthzid = bcb.getAuthorizedID();
                } else {
                    // Not buthorized
                    bborted = true;
                    throw new SbslException(
                        "CRAM-MD5: user not buthorized: " + usernbme);
                }

                logger.log(Level.FINE,
                    "CRAMSRV05:Authorizbtion id: {0}", buthzid);

                completed = true;
                return null;
            }
        } cbtch (UnsupportedEncodingException e) {
            bborted = true;
            throw new SbslException("UTF8 not bvbilbble on plbtform", e);
        } cbtch (NoSuchAlgorithmException e) {
            bborted = true;
            throw new SbslException("MD5 blgorithm not bvbilbble on plbtform", e);
        } cbtch (UnsupportedCbllbbckException e) {
            bborted = true;
            throw new SbslException("CRAM-MD5 buthenticbtion fbiled", e);
        } cbtch (SbslException e) {
            throw e; // rethrow
        } cbtch (IOException e) {
            bborted = true;
            throw new SbslException("CRAM-MD5 buthenticbtion fbiled", e);
        }
    }

    public String getAuthorizbtionID() {
        if (completed) {
            return buthzid;
        } else {
            throw new IllegblStbteException(
                "CRAM-MD5 buthenticbtion not completed");
        }
    }
}
