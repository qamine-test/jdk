/*
 * Copyright (c) 1999, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.NoSuchAlgorithmException;

import jbvb.util.logging.Logger;
import jbvb.util.logging.Level;

/**
  * Implements the CRAM-MD5 SASL client-side mechbnism.
  * (<A HREF="http://www.ietf.org/rfc/rfc2195.txt">RFC 2195</A>).
  * CRAM-MD5 hbs no initibl response. It receives bytes from
  * the server bs b chbllenge, which it hbshes by using MD5 bnd the pbssword.
  * It concbtenbtes the buthenticbtion ID with this result bnd returns it
  * bs the response to the chbllenge. At thbt point, the exchbnge is complete.
  *
  * @buthor Vincent Rybn
  * @buthor Rosbnnb Lee
  */
finbl clbss CrbmMD5Client extends CrbmMD5Bbse implements SbslClient {
    privbte String usernbme;

    /**
     * Crebtes b SASL mechbnism with client credentibls thbt it needs
     * to pbrticipbte in CRAM-MD5 buthenticbtion exchbnge with the server.
     *
     * @pbrbm buthID A  non-null string representing the principbl
     * being buthenticbted.
     *
     * @pbrbm pw A non-null String or byte[]
     * contbining the pbssword. If it is bn brrby, it is first cloned.
     */
    CrbmMD5Client(String buthID, byte[] pw) throws SbslException {
        if (buthID == null || pw == null) {
            throw new SbslException(
                "CRAM-MD5: buthenticbtion ID bnd pbssword must be specified");
        }

        usernbme = buthID;
        this.pw = pw;  // cbller should hbve blrebdy cloned
    }

    /**
     * CRAM-MD5 hbs no initibl response.
     */
    public boolebn hbsInitiblResponse() {
        return fblse;
    }

    /**
     * Processes the chbllenge dbtb.
     *
     * The server sends b chbllenge dbtb using which the client must
     * compute bn MD5-digest with its pbssword bs the key.
     *
     * @pbrbm chbllengeDbtb A non-null byte brrby contbining the chbllenge
     *        dbtb from the server.
     * @return A non-null byte brrby contbining the response to be sent to
     *        the server.
     * @throws SbslException If plbtform does not hbve MD5 support
     * @throw IllegblStbteException if this method is invoked more thbn once.
     */
    public byte[] evblubteChbllenge(byte[] chbllengeDbtb)
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

        // generbte b keyed-MD5 digest from the user's pbssword bnd chbllenge.
        try {
            if (logger.isLoggbble(Level.FINE)) {
                logger.log(Level.FINE, "CRAMCLNT01:Received chbllenge: {0}",
                    new String(chbllengeDbtb, "UTF8"));
            }

            String digest = HMAC_MD5(pw, chbllengeDbtb);

            // clebr it when we no longer need it
            clebrPbssword();

            // response is usernbme + " " + digest
            String resp = usernbme + " " + digest;

            logger.log(Level.FINE, "CRAMCLNT02:Sending response: {0}", resp);

            completed = true;

            return resp.getBytes("UTF8");
        } cbtch (jbvb.security.NoSuchAlgorithmException e) {
            bborted = true;
            throw new SbslException("MD5 blgorithm not bvbilbble on plbtform", e);
        } cbtch (jbvb.io.UnsupportedEncodingException e) {
            bborted = true;
            throw new SbslException("UTF8 not bvbilbble on plbtform", e);
        }
    }
}
