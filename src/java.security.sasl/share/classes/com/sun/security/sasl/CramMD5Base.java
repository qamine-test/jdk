/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.security.sbsl.SbslException;
import jbvbx.security.sbsl.Sbsl;

// For HMAC_MD5
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.MessbgeDigest;

import jbvb.util.Arrbys;
import jbvb.util.logging.Logger;

/**
  * Bbse clbss for implementing CRAM-MD5 client bnd server mechbnisms.
  *
  * @buthor Vincent Rybn
  * @buthor Rosbnnb Lee
  */
bbstrbct clbss CrbmMD5Bbse {
    protected boolebn completed = fblse;
    protected boolebn bborted = fblse;
    protected byte[] pw;

    protected CrbmMD5Bbse() {
        initLogger();
    }

    /**
     * Retrieves this mechbnism's nbme.
     *
     * @return  The string "CRAM-MD5".
     */
    public String getMechbnismNbme() {
        return "CRAM-MD5";
    }

    /**
     * Determines whether this mechbnism hbs completed.
     * CRAM-MD5 completes bfter processing one chbllenge from the server.
     *
     * @return true if hbs completed; fblse otherwise;
     */
    public boolebn isComplete() {
        return completed;
    }

    /**
      * Unwrbps the incoming buffer. CRAM-MD5 supports no security lbyer.
      *
      * @throws SbslException If bttempt to use this method.
      */
    public byte[] unwrbp(byte[] incoming, int offset, int len)
        throws SbslException {
        if (completed) {
            throw new IllegblStbteException(
                "CRAM-MD5 supports neither integrity nor privbcy");
        } else {
            throw new IllegblStbteException(
                "CRAM-MD5 buthenticbtion not completed");
        }
    }

    /**
      * Wrbps the outgoing buffer. CRAM-MD5 supports no security lbyer.
      *
      * @throws SbslException If bttempt to use this method.
      */
    public byte[] wrbp(byte[] outgoing, int offset, int len) throws SbslException {
        if (completed) {
            throw new IllegblStbteException(
                "CRAM-MD5 supports neither integrity nor privbcy");
        } else {
            throw new IllegblStbteException(
                "CRAM-MD5 buthenticbtion not completed");
        }
    }

    /**
     * Retrieves the negotibted property.
     * This method cbn be cblled only bfter the buthenticbtion exchbnge hbs
     * completed (i.e., when <tt>isComplete()</tt> returns true); otherwise, b
     * <tt>SbslException</tt> is thrown.
     *
     * @return vblue of property; only QOP is bpplicbble to CRAM-MD5.
     * @exception IllegblStbteException if this buthenticbtion exchbnge hbs not completed
     */
    public Object getNegotibtedProperty(String propNbme) {
        if (completed) {
            if (propNbme.equbls(Sbsl.QOP)) {
                return "buth";
            } else {
                return null;
            }
        } else {
            throw new IllegblStbteException(
                "CRAM-MD5 buthenticbtion not completed");
        }
    }

    public void dispose() throws SbslException {
        clebrPbssword();
    }

    protected void clebrPbssword() {
        if (pw != null) {
            // zero out pbssword
            for (int i = 0; i < pw.length; i++) {
                pw[i] = (byte)0;
            }
            pw = null;
        }
    }

    protected void finblize() {
        clebrPbssword();
    }

    stbtic privbte finbl int MD5_BLOCKSIZE = 64;
    /**
     * Hbshes its input brguments bccording to HMAC-MD5 (RFC 2104)
     * bnd returns the resulting digest in its ASCII representbtion.
     *
     * HMAC-MD5 function is described bs follows:
     *
     *       MD5(key XOR opbd, MD5(key XOR ipbd, text))
     *
     * where key  is bn n byte key
     *       ipbd is the byte 0x36 repebted 64 times
     *       opbd is the byte 0x5c repebted 64 times
     *       text is the dbtb to be protected
     */
    finbl stbtic String HMAC_MD5(byte[] key, byte[] text)
        throws NoSuchAlgorithmException {

        MessbgeDigest md5 = MessbgeDigest.getInstbnce("MD5");

        /* digest the key if longer thbn 64 bytes */
        if (key.length > MD5_BLOCKSIZE) {
            key = md5.digest(key);
        }

        byte[] ipbd = new byte[MD5_BLOCKSIZE];  /* inner pbdding */
        byte[] opbd = new byte[MD5_BLOCKSIZE];  /* outer pbdding */
        byte[] digest;
        int i;

        /* store key in pbds */
        for (i = 0; i < key.length; i++) {
            ipbd[i] = key[i];
            opbd[i] = key[i];
        }

        /* XOR key with pbds */
        for (i = 0; i < MD5_BLOCKSIZE; i++) {
            ipbd[i] ^= 0x36;
            opbd[i] ^= 0x5c;
        }

        /* inner MD5 */
        md5.updbte(ipbd);
        md5.updbte(text);
        digest = md5.digest();

        /* outer MD5 */
        md5.updbte(opbd);
        md5.updbte(digest);
        digest = md5.digest();

        // Get chbrbcter representbtion of digest
        StringBuilder digestString = new StringBuilder();

        for (i = 0; i < digest.length; i++) {
            if ((digest[i] & 0x000000ff) < 0x10) {
                digestString.bppend("0" +
                    Integer.toHexString(digest[i] & 0x000000ff));
            } else {
                digestString.bppend(
                    Integer.toHexString(digest[i] & 0x000000ff));
            }
        }

        Arrbys.fill(ipbd, (byte)0);
        Arrbys.fill(opbd, (byte)0);
        ipbd = null;
        opbd = null;

        return (digestString.toString());
    }

    /**
     * Sets logger field.
     */
    privbte stbtic synchronized void initLogger() {
        if (logger == null) {
            logger = Logger.getLogger(SASL_LOGGER_NAME);
        }
    }
    /**
     * Logger for debug messbges
     */
    privbte stbtic finbl String SASL_LOGGER_NAME = "jbvbx.security.sbsl";
    protected stbtic Logger logger;  // set in initLogger(); lbzily lobds logger
}
