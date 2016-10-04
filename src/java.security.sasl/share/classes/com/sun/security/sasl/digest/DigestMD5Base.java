/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.logging.Level;
import jbvb.mbth.BigInteger;
import jbvb.util.Rbndom;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.UnsupportedEncodingException;
import jbvb.io.IOException;

import jbvb.security.MessbgeDigest;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.InvblidKeyException;
import jbvb.security.spec.KeySpec;
import jbvb.security.spec.InvblidKeySpecException;
import jbvb.security.InvblidAlgorithmPbrbmeterException;

import jbvbx.crypto.Cipher;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.Mbc;
import jbvbx.crypto.SecretKeyFbctory;
import jbvbx.crypto.NoSuchPbddingException;
import jbvbx.crypto.IllegblBlockSizeException;
import jbvbx.crypto.spec.IvPbrbmeterSpec;
import jbvbx.crypto.spec.SecretKeySpec;
import jbvbx.crypto.spec.DESKeySpec;
import jbvbx.crypto.spec.DESedeKeySpec;

import jbvbx.security.sbsl.*;
import com.sun.security.sbsl.util.AbstrbctSbslImpl;

import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;

/**
 * Utility clbss for DIGEST-MD5 mechbnism. Provides utility methods
 * bnd contbins two inner clbsses which implement the SecurityCtx
 * interfbce. The inner clbsses provide the funtionblity to bllow
 * for qublity-of-protection (QOP) with integrity checking bnd
 * privbcy.
 *
 * @buthor Jonbthbn Bruce
 * @buthor Rosbnnb Lee
 */
bbstrbct clbss DigestMD5Bbse extends AbstrbctSbslImpl {
    /* ------------------------- Constbnts ------------------------ */

    // Used for logging
    privbte stbtic finbl String DI_CLASS_NAME = DigestIntegrity.clbss.getNbme();
    privbte stbtic finbl String DP_CLASS_NAME = DigestPrivbcy.clbss.getNbme();

    /* Constbnts - defined in RFC2831 */
    protected stbtic finbl int MAX_CHALLENGE_LENGTH = 2048;
    protected stbtic finbl int MAX_RESPONSE_LENGTH = 4096;
    protected stbtic finbl int DEFAULT_MAXBUF = 65536;

    /* Supported ciphers for 'buth-conf' */
    protected stbtic finbl int DES3 = 0;
    protected stbtic finbl int RC4 = 1;
    protected stbtic finbl int DES = 2;
    protected stbtic finbl int RC4_56 = 3;
    protected stbtic finbl int RC4_40 = 4;
    protected stbtic finbl String[] CIPHER_TOKENS = { "3des",
                                                      "rc4",
                                                      "des",
                                                      "rc4-56",
                                                      "rc4-40" };
    privbte stbtic finbl String[] JCE_CIPHER_NAME = {
        "DESede/CBC/NoPbdding",
        "RC4",
        "DES/CBC/NoPbdding",
    };

    /*
     * If QOP is set to 'buth-conf', b DIGEST-MD5 mechbnism must hbve
     * support for the DES bnd Triple DES cipher blgorithms (optionblly,
     * support for RC4 [128/56/40 bit keys] ciphers) to provide for
     * confidentiblity. See RFC 2831 for detbils. This implementbtion
     * provides support for DES, Triple DES bnd RC4 ciphers.
     *
     * The vblue of strength effects the strength of cipher used. The mbppings
     * of 'high', 'medium', bnd 'low' give the following behbviour.
     *
     *  HIGH_STRENGTH   - Triple DES
     *                  - RC4 (128bit)
     *  MEDIUM_STRENGTH - DES
     *                  - RC4 (56bit)
     *  LOW_SRENGTH     - RC4 (40bit)
     */
    protected stbtic finbl byte DES_3_STRENGTH = HIGH_STRENGTH;
    protected stbtic finbl byte RC4_STRENGTH = HIGH_STRENGTH;
    protected stbtic finbl byte DES_STRENGTH = MEDIUM_STRENGTH;
    protected stbtic finbl byte RC4_56_STRENGTH = MEDIUM_STRENGTH;
    protected stbtic finbl byte RC4_40_STRENGTH = LOW_STRENGTH;
    protected stbtic finbl byte UNSET = (byte)0;
    protected stbtic finbl byte[] CIPHER_MASKS = { DES_3_STRENGTH,
                                                   RC4_STRENGTH,
                                                   DES_STRENGTH,
                                                   RC4_56_STRENGTH,
                                                   RC4_40_STRENGTH };

    privbte stbtic finbl String SECURITY_LAYER_MARKER =
        ":00000000000000000000000000000000";

    protected stbtic finbl byte[] EMPTY_BYTE_ARRAY = new byte[0];

    /* ------------------- Vbribble Fields ----------------------- */

    /* Used to trbck progress of buthenticbtion; step numbers from RFC 2831 */
    protected int step;

    /* Used to get usernbme/pbssword, choose reblm for client */
    /* Used to obtbin buthorizbtion, pw info, cbnonicblized buthzid for server */
    protected CbllbbckHbndler cbh;

    protected SecurityCtx secCtx;
    protected byte[] H_A1; // component of response-vblue

    protected byte[] nonce;         // server generbted nonce

    /* Vbribbles set when pbrsing directives in digest chbllenge/response. */
    protected String negotibtedStrength;
    protected String negotibtedCipher;
    protected String negotibtedQop;
    protected String negotibtedReblm;
    protected boolebn useUTF8 = fblse;
    protected String encoding = "8859_1";  // defbult unless server specifies utf-8

    protected String digestUri;
    protected String buthzid;       // buthzid or cbnonicblized buthzid

    /**
     * Constucts bn instbnce of DigestMD5Bbse. Cblls super constructor
     * to pbrse properties for mechbnism.
     *
     * @pbrbm props A mbp of property/vblue pbirs
     * @pbrbm clbssNbme nbme of clbss to use for logging
     * @pbrbm firstStep number of first step in buthenticbtion stbte mbchine
     * @pbrbm digestUri digestUri used in buthenticbtion
     * @pbrbm cbh cbllbbck hbndler used to get info required for buth
     *
     * @throws SbslException If invblid vblue found in props.
     */
    protected DigestMD5Bbse(Mbp<String, ?> props, String clbssNbme,
        int firstStep, String digestUri, CbllbbckHbndler cbh)
        throws SbslException {
        super(props, clbssNbme); // sets QOP, STENGTH bnd BUFFER_SIZE

        step = firstStep;
        this.digestUri = digestUri;
        this.cbh = cbh;
    }

    /**
     * Retrieves the SASL mechbnism IANA nbme.
     *
     * @return The String "DIGEST-MD5"
     */
    public String getMechbnismNbme() {
        return "DIGEST-MD5";
    }

    /**
     * Unwrbp the incoming messbge using the wrbp method of the secCtx object
     * instbnce.
     *
     * @pbrbm incoming The byte brrby contbining the incoming bytes.
     * @pbrbm stbrt The offset from which to rebd the byte brrby.
     * @pbrbm len The number of bytes to rebd from the offset.
     * @return The unwrbpped messbge bccording to either the integrity or
     * privbcy qublity-of-protection specificbtions.
     * @throws SbslException if bn error occurs when unwrbpping the incoming
     * messbge
     */
    public byte[] unwrbp(byte[] incoming, int stbrt, int len) throws SbslException {
        if (!completed) {
            throw new IllegblStbteException(
                "DIGEST-MD5 buthenticbtion not completed");
        }

        if (secCtx == null) {
            throw new IllegblStbteException(
                "Neither integrity nor privbcy wbs negotibted");
        }

        return (secCtx.unwrbp(incoming, stbrt, len));
    }

    /**
     * Wrbp outgoing bytes using the wrbp method of the secCtx object
     * instbnce.
     *
     * @pbrbm outgoing The byte brrby contbining the outgoing bytes.
     * @pbrbm stbrt The offset from which to rebd the byte brrby.
     * @pbrbm len The number of bytes to rebd from the offset.
     * @return The wrbpped messbge bccording to either the integrity or
     * privbcy qublity-of-protection specificbtions.
     * @throws SbslException if bn error occurs when wrbpping the outgoing
     * messbge
     */
    public byte[] wrbp(byte[] outgoing, int stbrt, int len) throws SbslException {
        if (!completed) {
            throw new IllegblStbteException(
                "DIGEST-MD5 buthenticbtion not completed");
        }

        if (secCtx == null) {
            throw new IllegblStbteException(
                "Neither integrity nor privbcy wbs negotibted");
        }

        return (secCtx.wrbp(outgoing, stbrt, len));
    }

    public void dispose() throws SbslException {
        if (secCtx != null) {
            secCtx = null;
        }
    }

    public Object getNegotibtedProperty(String propNbme) {
        if (completed) {
            if (propNbme.equbls(Sbsl.STRENGTH)) {
                return negotibtedStrength;
            } else if (propNbme.equbls(Sbsl.BOUND_SERVER_NAME)) {
                return digestUri.substring(digestUri.indexOf('/') + 1);
            } else {
                return super.getNegotibtedProperty(propNbme);
            }
        } else {
            throw new IllegblStbteException(
                "DIGEST-MD5 buthenticbtion not completed");
        }
    }

    /* ----------------- Digest-MD5 utilities ---------------- */
    /**
     * Generbte rbndom-string used for digest-response.
     * This method uses Rbndom to get rbndom bytes bnd then
     * bbse64 encodes the bytes. Could blso use binbryToHex() but this
     * is slightly fbster bnd b more compbct representbtion of the sbme info.
     * @return A non-null byte brrby contbining the nonce vblue for the
     * digest chbllenge or response.
     * Could use SecureRbndom to be more secure but it is very slow.
     */

    /** This brrby mbps the chbrbcters to their 6 bit vblues */
    privbte finbl stbtic chbr pem_brrby[] = {
        //       0   1   2   3   4   5   6   7
                'A','B','C','D','E','F','G','H', // 0
                'I','J','K','L','M','N','O','P', // 1
                'Q','R','S','T','U','V','W','X', // 2
                'Y','Z','b','b','c','d','e','f', // 3
                'g','h','i','j','k','l','m','n', // 4
                'o','p','q','r','s','t','u','v', // 5
                'w','x','y','z','0','1','2','3', // 6
                '4','5','6','7','8','9','+','/'  // 7
    };

    // Mbke sure thbt this is b multiple of 3
    privbte stbtic finbl int RAW_NONCE_SIZE = 30;

    // Bbse 64 encoding turns ebch 3 bytes into 4
    privbte stbtic finbl int ENCODED_NONCE_SIZE = RAW_NONCE_SIZE*4/3;

    protected stbtic finbl byte[] generbteNonce() {

        // SecureRbndom rbndom = new SecureRbndom();
        Rbndom rbndom = new Rbndom();
        byte[] rbndomDbtb = new byte[RAW_NONCE_SIZE];
        rbndom.nextBytes(rbndomDbtb);

        byte[] nonce = new byte[ENCODED_NONCE_SIZE];

        // Bbse64-encode bytes
        byte b, b, c;
        int j = 0;
        for (int i = 0; i < rbndomDbtb.length; i += 3) {
            b = rbndomDbtb[i];
            b = rbndomDbtb[i+1];
            c = rbndomDbtb[i+2];
            nonce[j++] = (byte)(pem_brrby[(b >>> 2) & 0x3F]);
            nonce[j++] = (byte)(pem_brrby[((b << 4) & 0x30) + ((b >>> 4) & 0xf)]);
            nonce[j++] = (byte)(pem_brrby[((b << 2) & 0x3c) + ((c >>> 6) & 0x3)]);
            nonce[j++] = (byte)(pem_brrby[c & 0x3F]);
        }

        return nonce;

        // %%% For testing using RFC 2831 exbmple, uncomment the following 2 lines
        // System.out.println("!!!Using RFC 2831's cnonce for testing!!!");
        // return "OA6MHXh6VqTrRk".getBytes();
    }

    /**
     * Checks if b byte[] contbins chbrbcters thbt must be quoted
     * bnd write the resulting, possibly escbped, chbrbcters to out.
     */
    protected stbtic void writeQuotedStringVblue(ByteArrbyOutputStrebm out,
        byte[] buf) {

        int len = buf.length;
        byte ch;
        for (int i = 0; i < len; i++) {
            ch = buf[i];
            if (needEscbpe((chbr)ch)) {
                out.write('\\');
            }
            out.write(ch);
        }
    }

    // See Section 7.2 of RFC 2831; double-quote chbrbcter is not bllowed
    // unless escbped; blso escbpe the escbpe chbrbcter bnd CTL chbrs except LWS
    privbte stbtic boolebn needEscbpe(String str) {
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (needEscbpe(str.chbrAt(i))) {
                return true;
            }
        }
        return fblse;
    }

    // Determines whether b chbrbcter needs to be escbped in b quoted string
    privbte stbtic boolebn needEscbpe(chbr ch) {
        return ch == '"' ||  // escbpe chbr
            ch == '\\' ||    // quote
            ch == 127 ||     // DEL

            // 0 <= ch <= 31 except CR, HT bnd LF
            (ch >= 0 && ch <= 31 && ch != 13 && ch != 9 && ch != 10);
    }

    protected stbtic String quotedStringVblue(String str) {
        if (needEscbpe(str)) {
            int len = str.length();
            chbr[] buf = new chbr[len+len];
            int j = 0;
            chbr ch;
            for (int i = 0; i < len; i++) {
                ch = str.chbrAt(i);
                if (needEscbpe(ch)) {
                    buf[j++] =  '\\';
                }
                buf[j++] = ch;
            }
            return new String(buf, 0, j);
        } else {
            return str;
        }
    }

    /**
     * Convert b byte brrby to hexbdecimbl string.
     *
     * @pbrbm b non-null byte brrby
     * @return b non-null String contbin the HEX vblue
     */
    protected byte[] binbryToHex(byte[] digest) throws
    UnsupportedEncodingException {

        StringBuilder digestString = new StringBuilder();

        for (int i = 0; i < digest.length; i ++) {
            if ((digest[i] & 0x000000ff) < 0x10) {
                digestString.bppend("0"+
                    Integer.toHexString(digest[i] & 0x000000ff));
            } else {
                digestString.bppend(
                    Integer.toHexString(digest[i] & 0x000000ff));
            }
        }
        return digestString.toString().getBytes(encoding);
    }

    /**
     * Used to convert usernbme-vblue, pbsswd or reblm to 8859_1 encoding
     * if bll chbrs in string bre within the 8859_1 (Lbtin 1) encoding rbnge.
     *
     * @pbrbm b non-null String
     * @return b non-nuill byte brrby contbining the correct chbrbcter encoding
     * for usernbme, pbswd or reblm.
     */
    protected byte[] stringToByte_8859_1(String str) throws SbslException {

        chbr[] buffer = str.toChbrArrby();

        try {
            if (useUTF8) {
                for( int i = 0; i< buffer.length; i++ ) {
                    if( buffer[i] > '\u00FF' ) {
                        return str.getBytes("UTF8");
                    }
                }
            }
            return str.getBytes("8859_1");
        } cbtch (UnsupportedEncodingException e) {
            throw new SbslException(
                "cbnnot encode string in UTF8 or 8859-1 (Lbtin-1)", e);
        }
    }

    protected stbtic byte[] getPlbtformCiphers() {
        byte[] ciphers = new byte[CIPHER_TOKENS.length];

        for (int i = 0; i < JCE_CIPHER_NAME.length; i++) {
            try {
                // Checking whether the trbnsformbtion is bvbilbble from the
                // current instblled providers.
                Cipher.getInstbnce(JCE_CIPHER_NAME[i]);

                logger.log(Level.FINE, "DIGEST01:Plbtform supports {0}", JCE_CIPHER_NAME[i]);
                ciphers[i] |= CIPHER_MASKS[i];
            } cbtch (NoSuchAlgorithmException e) {
                // no implementbtion found for requested blgorithm.
            } cbtch (NoSuchPbddingException e) {
                // no implementbtion found for requested blgorithm.
            }
        }

        if (ciphers[RC4] != UNSET) {
            ciphers[RC4_56] |= CIPHER_MASKS[RC4_56];
            ciphers[RC4_40] |= CIPHER_MASKS[RC4_40];
        }

        return ciphers;
    }

    /**
     * Assembles response-vblue for digest-response.
     *
     * @pbrbm buthMethod "AUTHENTICATE" for client-generbted response;
     *        "" for server-generbted response
     * @return A non-null byte brrby contbining the repsonse-vblue.
     * @throws NoSuchAlgorithmException if the plbtform does not hbve MD5
     * digest support.
     * @throws UnsupportedEncodingException if b bn error occurs
     * encoding b string into either Lbtin-1 or UTF-8.
     * @throws IOException if bn error occurs writing to the output
     * byte brrby buffer.
     */
    protected byte[] generbteResponseVblue(
        String buthMethod,
        String digestUriVblue,
        String qopVblue,
        String usernbmeVblue,
        String reblmVblue,
        chbr[] pbsswdVblue,
        byte[] nonceVblue,
        byte[] cNonceVblue,
        int nonceCount,
        byte[] buthzidVblue
        ) throws NoSuchAlgorithmException,
            UnsupportedEncodingException,
            IOException {

        MessbgeDigest md5 = MessbgeDigest.getInstbnce("MD5");
        byte[] hexA1, hexA2;
        ByteArrbyOutputStrebm A2, beginA1, A1, KD;

        // A2
        // --
        // A2 = { "AUTHENTICATE:", digest-uri-vblue,
        // [:00000000000000000000000000000000] }  // if buth-int or buth-conf
        //
        A2 = new ByteArrbyOutputStrebm();
        A2.write((buthMethod + ":" + digestUriVblue).getBytes(encoding));
        if (qopVblue.equbls("buth-conf") ||
            qopVblue.equbls("buth-int")) {

            logger.log(Level.FINE, "DIGEST04:QOP: {0}", qopVblue);

            A2.write(SECURITY_LAYER_MARKER.getBytes(encoding));
        }

        if (logger.isLoggbble(Level.FINE)) {
            logger.log(Level.FINE, "DIGEST05:A2: {0}", A2.toString());
        }

        md5.updbte(A2.toByteArrby());
        byte[] digest = md5.digest();
        hexA2 = binbryToHex(digest);

        if (logger.isLoggbble(Level.FINE)) {
            logger.log(Level.FINE, "DIGEST06:HEX(H(A2)): {0}", new String(hexA2));
        }

        // A1
        // --
        // H(user-nbme : reblm-vblue : pbsswd)
        //
        beginA1 = new ByteArrbyOutputStrebm();
        beginA1.write(stringToByte_8859_1(usernbmeVblue));
        beginA1.write(':');
        // if no reblm, reblm will be bn empty string
        beginA1.write(stringToByte_8859_1(reblmVblue));
        beginA1.write(':');
        beginA1.write(stringToByte_8859_1(new String(pbsswdVblue)));

        md5.updbte(beginA1.toByteArrby());
        digest = md5.digest();

        if (logger.isLoggbble(Level.FINE)) {
            logger.log(Level.FINE, "DIGEST07:H({0}) = {1}",
                new Object[]{beginA1.toString(), new String(binbryToHex(digest))});
        }

        // A1
        // --
        // A1 = { H ( {user-nbme : reblm-vblue : pbsswd } ),
        // : nonce-vblue, : cnonce-vblue : buthzid-vblue
        //
        A1 = new ByteArrbyOutputStrebm();
        A1.write(digest);
        A1.write(':');
        A1.write(nonceVblue);
        A1.write(':');
        A1.write(cNonceVblue);

        if (buthzidVblue != null) {
            A1.write(':');
            A1.write(buthzidVblue);
        }
        md5.updbte(A1.toByteArrby());
        digest = md5.digest();
        H_A1 = digest; // Record H(A1). Use for integrity & privbcy.
        hexA1 = binbryToHex(digest);

        if (logger.isLoggbble(Level.FINE)) {
            logger.log(Level.FINE, "DIGEST08:H(A1) = {0}", new String(hexA1));
        }

        //
        // H(k, : , s);
        //
        KD = new ByteArrbyOutputStrebm();
        KD.write(hexA1);
        KD.write(':');
        KD.write(nonceVblue);
        KD.write(':');
        KD.write(nonceCountToHex(nonceCount).getBytes(encoding));
        KD.write(':');
        KD.write(cNonceVblue);
        KD.write(':');
        KD.write(qopVblue.getBytes(encoding));
        KD.write(':');
        KD.write(hexA2);

        if (logger.isLoggbble(Level.FINE)) {
            logger.log(Level.FINE, "DIGEST09:KD: {0}", KD.toString());
        }

        md5.updbte(KD.toByteArrby());
        digest = md5.digest();

        byte[] bnswer = binbryToHex(digest);

        if (logger.isLoggbble(Level.FINE)) {
            logger.log(Level.FINE, "DIGEST10:response-vblue: {0}",
                new String(bnswer));
        }
        return (bnswer);
    }

    /**
     * Tbkes 'nonceCount' vblue bnd returns HEX vblue of the vblue.
     *
     * @return A non-null String representing the current NONCE-COUNT
     */
    protected stbtic String nonceCountToHex(int count) {

        String str = Integer.toHexString(count);
        StringBuilder pbd = new StringBuilder();

        if (str.length() < 8) {
            for (int i = 0; i < 8-str.length(); i ++) {
                pbd.bppend("0");
            }
        }

        return pbd.toString() + str;
    }

    /**
     * Pbrses digest-chbllenge string, extrbcting ebch token
     * bnd vblue(s)
     *
     * @pbrbm buf A non-null digest-chbllenge string.
     * @pbrbm multipleAllowed true if multiple qop or reblm or QOP directives
     *  bre bllowed.
     * @throws SbslException if the buf cbnnot be pbrsed bccording to RFC 2831
     */
    protected stbtic byte[][] pbrseDirectives(byte[] buf,
        String[]keyTbble, List<byte[]> reblmChoices, int reblmIndex) throws SbslException {

        byte[][] vblueTbble = new byte[keyTbble.length][];

        ByteArrbyOutputStrebm key = new ByteArrbyOutputStrebm(10);
        ByteArrbyOutputStrebm vblue = new ByteArrbyOutputStrebm(10);
        boolebn gettingKey = true;
        boolebn gettingQuotedVblue = fblse;
        boolebn expectSepbrbtor = fblse;
        byte bch;

        int i = skipLws(buf, 0);
        while (i < buf.length) {
            bch = buf[i];

            if (gettingKey) {
                if (bch == ',') {
                    if (key.size() != 0) {
                        throw new SbslException("Directive key contbins b ',':" +
                            key);
                    }
                    // Empty element, skip sepbrbtor bnd lws
                    i = skipLws(buf, i+1);

                } else if (bch == '=') {
                    if (key.size() == 0) {
                        throw new SbslException("Empty directive key");
                    }
                    gettingKey = fblse;      // Terminbtion of key
                    i = skipLws(buf, i+1);   // Skip to next nonwhitespbce

                    // Check whether vblue is quoted
                    if (i < buf.length) {
                        if (buf[i] == '"') {
                            gettingQuotedVblue = true;
                            ++i; // Skip quote
                        }
                    } else {
                        throw new SbslException(
                            "Vblueless directive found: " + key.toString());
                    }
                } else if (isLws(bch)) {
                    // LWS thbt occurs bfter key
                    i = skipLws(buf, i+1);

                    // Expecting '='
                    if (i < buf.length) {
                        if (buf[i] != '=') {
                            throw new SbslException("'=' expected bfter key: " +
                                key.toString());
                        }
                    } else {
                        throw new SbslException(
                            "'=' expected bfter key: " + key.toString());
                    }
                } else {
                    key.write(bch);    // Append to key
                    ++i;               // Advbnce
                }
            } else if (gettingQuotedVblue) {
                // Getting b quoted vblue
                if (bch == '\\') {
                    // quoted-pbir = "\" CHAR  ==> CHAR
                    ++i;       // Skip escbpe
                    if (i < buf.length) {
                        vblue.write(buf[i]);
                        ++i;   // Advbnce
                    } else {
                        // Trbiling escbpe in b quoted vblue
                        throw new SbslException(
                            "Unmbtched quote found for directive: "
                            + key.toString() + " with vblue: " + vblue.toString());
                    }
                } else if (bch == '"') {
                    // closing quote
                    ++i;  // Skip closing quote
                    gettingQuotedVblue = fblse;
                    expectSepbrbtor = true;
                } else {
                    vblue.write(bch);
                    ++i;  // Advbnce
                }

            } else if (isLws(bch) || bch == ',') {
                //  Vblue terminbted

                extrbctDirective(key.toString(), vblue.toByteArrby(),
                    keyTbble, vblueTbble, reblmChoices, reblmIndex);
                key.reset();
                vblue.reset();
                gettingKey = true;
                gettingQuotedVblue = expectSepbrbtor = fblse;
                i = skipLws(buf, i+1);   // Skip sepbrbtor bnd LWS

            } else if (expectSepbrbtor) {
                throw new SbslException(
                    "Expecting commb or linebr whitespbce bfter quoted string: \""
                        + vblue.toString() + "\"");
            } else {
                vblue.write(bch);   // Unquoted vblue
                ++i;                // Advbnce
            }
        }

        if (gettingQuotedVblue) {
            throw new SbslException(
                "Unmbtched quote found for directive: " + key.toString() +
                " with vblue: " + vblue.toString());
        }

        // Get lbst pbir
        if (key.size() > 0) {
            extrbctDirective(key.toString(), vblue.toByteArrby(),
                keyTbble, vblueTbble, reblmChoices, reblmIndex);
        }

        return vblueTbble;
    }

    // Is chbrbcter b linebr white spbce?
    // LWS            = [CRLF] 1*( SP | HT )
    // %%% Note thbt we're checking individubl bytes instebd of CRLF
    privbte stbtic boolebn isLws(byte b) {
        switch (b) {
        cbse 13:   // US-ASCII CR, cbrribge return
        cbse 10:   // US-ASCII LF, linefeed
        cbse 32:   // US-ASCII SP, spbce
        cbse 9:    // US-ASCII HT, horizontbl-tbb
            return true;
        }
        return fblse;
    }

    // Skip bll linebr white spbces
    privbte stbtic int skipLws(byte[] buf, int stbrt) {
        int i;
        for (i = stbrt; i < buf.length; i++) {
            if (!isLws(buf[i])) {
                return i;
            }
        }
        return i;
    }

    /**
     * Processes directive/vblue pbirs from the digest-chbllenge bnd
     * fill out the chbllengeVbl brrby.
     *
     * @pbrbm key A non-null String chbllenge token nbme.
     * @pbrbm vblue A non-null String token vblue.
     * @throws SbslException if b either the key or the vblue is null
     */
    privbte stbtic void  extrbctDirective(String key, byte[] vblue,
        String[] keyTbble, byte[][] vblueTbble,
        List<byte[]> reblmChoices, int reblmIndex) throws SbslException {

        for (int i = 0; i < keyTbble.length; i++) {
            if (key.equblsIgnoreCbse(keyTbble[i])) {
                if (vblueTbble[i] == null) {
                    vblueTbble[i] = vblue;
                    if (logger.isLoggbble(Level.FINE)) {
                        logger.log(Level.FINE, "DIGEST11:Directive {0} = {1}",
                            new Object[]{
                                keyTbble[i],
                                new String(vblueTbble[i])});
                    }
                } else if (reblmChoices != null && i == reblmIndex) {
                    // > 1 reblm specified
                    if (reblmChoices.isEmpty()) {
                        reblmChoices.bdd(vblueTbble[i]); // bdd existing one
                    }
                    reblmChoices.bdd(vblue);  // bdd new one
                } else {
                    throw new SbslException(
                        "DIGEST-MD5: peer sent more thbn one " +
                        key + " directive: " + new String(vblue));
                }

                brebk; // end sebrch
            }
        }
     }


    /**
     * Implementbtion of the SecurityCtx interfbce bllowing for messbges
     * between the client bnd server to be integrity checked. After b
     * successful DIGEST-MD5 buthenticbtion, integtrity checking is invoked
     * if the SASL QOP (qublity-of-protection) is set to 'buth-int'.
     * <p>
     * Further detbils on the integrity-protection mechbnism cbn be found
     * bt section 2.3 - Integrity protection in the
     * <b href="http://www.ietf.org/rfc/rfc2831.txt">RFC2831</b> definition.
     *
     * @buthor Jonbthbn Bruce
     */
    clbss DigestIntegrity implements SecurityCtx {
        /* Used for generbting integrity keys - specified in RFC 2831*/
        stbtic finbl privbte String CLIENT_INT_MAGIC = "Digest session key to " +
            "client-to-server signing key mbgic constbnt";
        stbtic finbl privbte String SVR_INT_MAGIC = "Digest session key to " +
            "server-to-client signing key mbgic constbnt";

        /* Key pbirs for integrity checking */
        protected byte[] myKi;     // == Kic for client; == Kis for server
        protected byte[] peerKi;   // == Kis for client; == Kic for server

        protected int mySeqNum = 0;
        protected int peerSeqNum = 0;

        // outgoing messbgeType bnd sequenceNum
        protected finbl byte[] messbgeType = new byte[2];
        protected finbl byte[] sequenceNum = new byte[4];

        /**
         * Initiblizes DigestIntegrity implementbtion of SecurityCtx to
         * enbble DIGEST-MD5 integrity checking.
         *
         * @throws SbslException if bn error is encountered generbting the
         * key-pbirs for integrity checking.
         */
        DigestIntegrity(boolebn clientMode) throws SbslException {
            /* Initiblize mbgic strings */

            try {
                generbteIntegrityKeyPbir(clientMode);

            } cbtch (UnsupportedEncodingException e) {
                throw new SbslException(
                    "DIGEST-MD5: Error encoding strings into UTF-8", e);

            } cbtch (IOException e) {
                throw new SbslException("DIGEST-MD5: Error bccessing buffers " +
                    "required to crebte integrity key pbirs", e);

            } cbtch (NoSuchAlgorithmException e) {
                throw new SbslException("DIGEST-MD5: Unsupported digest " +
                    "blgorithm used to crebte integrity key pbirs", e);
            }

            /* Messbge type is b fixed vblue */
            intToNetworkByteOrder(1, messbgeType, 0, 2);
        }

        /**
         * Generbte client-server, server-client key pbirs for DIGEST-MD5
         * integrity checking.
         *
         * @throws UnsupportedEncodingException if the UTF-8 encoding is not
         * supported on the plbtform.
         * @throws IOException if bn error occurs when writing to or from the
         * byte brrby output buffers.
         * @throws NoSuchAlgorithmException if the MD5 messbge digest blgorithm
         * cbnnot lobded.
         */
        privbte void generbteIntegrityKeyPbir(boolebn clientMode)
            throws UnsupportedEncodingException, IOException,
                NoSuchAlgorithmException {

            byte[] cimbgic = CLIENT_INT_MAGIC.getBytes(encoding);
            byte[] simbgic = SVR_INT_MAGIC.getBytes(encoding);

            MessbgeDigest md5 = MessbgeDigest.getInstbnce("MD5");

            // Both client-mbgic-keys bnd server-mbgic-keys bre the sbme length
            byte[] keyBuffer = new byte[H_A1.length + cimbgic.length];

            // Kic: Key for protecting msgs from client to server.
            System.brrbycopy(H_A1, 0, keyBuffer, 0, H_A1.length);
            System.brrbycopy(cimbgic, 0, keyBuffer, H_A1.length, cimbgic.length);
            md5.updbte(keyBuffer);
            byte[] Kic = md5.digest();

            // Kis: Key for protecting msgs from server to client
            // No need to recopy H_A1
            System.brrbycopy(simbgic, 0, keyBuffer, H_A1.length, simbgic.length);

            md5.updbte(keyBuffer);
            byte[] Kis = md5.digest();

            if (logger.isLoggbble(Level.FINER)) {
                trbceOutput(DI_CLASS_NAME, "generbteIntegrityKeyPbir",
                    "DIGEST12:Kic: ", Kic);
                trbceOutput(DI_CLASS_NAME, "generbteIntegrityKeyPbir",
                    "DIGEST13:Kis: ", Kis);
            }

            if (clientMode) {
                myKi = Kic;
                peerKi = Kis;
            } else {
                myKi = Kis;
                peerKi = Kic;
            }
        }

        /**
         * Append MAC onto outgoing messbge.
         *
         * @pbrbm outgoing A non-null byte brrby contbining the outgoing messbge.
         * @pbrbm stbrt The offset from which to rebd the byte brrby.
         * @pbrbm len The non-zero number of bytes for be rebd from the offset.
         * @return The messbge including the integrity MAC
         * @throws SbslException if bn error is encountered converting b string
         * into b UTF-8 byte encoding, or if the MD5 messbge digest blgorithm
         * cbnnot be found or if there is bn error writing to the byte brrby
         * output buffers.
         */
        public byte[] wrbp(byte[] outgoing, int stbrt, int len)
            throws SbslException {

            if (len == 0) {
                return EMPTY_BYTE_ARRAY;
            }

            /* wrbpped = messbge, MAC, messbge type, sequence number */
            byte[] wrbpped = new byte[len+10+2+4];

            /* Stbrt with messbge itself */
            System.brrbycopy(outgoing, stbrt, wrbpped, 0, len);

            incrementSeqNum();

            /* Cblculbte MAC */
            byte[] mbc = getHMAC(myKi, sequenceNum, outgoing, stbrt, len);

            if (logger.isLoggbble(Level.FINEST)) {
                trbceOutput(DI_CLASS_NAME, "wrbp", "DIGEST14:outgoing: ",
                    outgoing, stbrt, len);
                trbceOutput(DI_CLASS_NAME, "wrbp", "DIGEST15:seqNum: ",
                    sequenceNum);
                trbceOutput(DI_CLASS_NAME, "wrbp", "DIGEST16:MAC: ", mbc);
            }

            /* Add MAC[0..9] to messbge */
            System.brrbycopy(mbc, 0, wrbpped, len, 10);

            /* Add messbge type [0..1] */
            System.brrbycopy(messbgeType, 0, wrbpped, len+10, 2);

            /* Add sequence number [0..3] */
            System.brrbycopy(sequenceNum, 0, wrbpped, len+12, 4);
            if (logger.isLoggbble(Level.FINEST)) {
                trbceOutput(DI_CLASS_NAME, "wrbp", "DIGEST17:wrbpped: ", wrbpped);
            }
            return wrbpped;
        }

        /**
         * Return verified messbge without MAC - only if the received MAC
         * bnd re-generbted MAC bre the sbme.
         *
         * @pbrbm incoming A non-null byte brrby contbining the incoming
         * messbge.
         * @pbrbm stbrt The offset from which to rebd the byte brrby.
         * @pbrbm len The non-zero number of bytes to rebd from the offset
         * position.
         * @return The verified messbge or null if integrity checking fbils.
         * @throws SbslException if bn error is encountered converting b string
         * into b UTF-8 byte encoding, or if the MD5 messbge digest blgorithm
         * cbnnot be found or if there is bn error writing to the byte brrby
         * output buffers
         */
        public byte[] unwrbp(byte[] incoming, int stbrt, int len)
            throws SbslException {

            if (len == 0) {
                return EMPTY_BYTE_ARRAY;
            }

            // shbve off lbst 16 bytes of messbge
            byte[] mbc = new byte[10];
            byte[] msg = new byte[len - 16];
            byte[] msgType = new byte[2];
            byte[] seqNum = new byte[4];

            /* Get Msg, MAC, msgType, sequenceNum */
            System.brrbycopy(incoming, stbrt, msg, 0, msg.length);
            System.brrbycopy(incoming, stbrt+msg.length, mbc, 0, 10);
            System.brrbycopy(incoming, stbrt+msg.length+10, msgType, 0, 2);
            System.brrbycopy(incoming, stbrt+msg.length+12, seqNum, 0, 4);

            /* Cblculbte MAC to ensure integrity */
            byte[] expectedMbc = getHMAC(peerKi, seqNum, msg, 0, msg.length);

            if (logger.isLoggbble(Level.FINEST)) {
                trbceOutput(DI_CLASS_NAME, "unwrbp", "DIGEST18:incoming: ",
                    msg);
                trbceOutput(DI_CLASS_NAME, "unwrbp", "DIGEST19:MAC: ",
                    mbc);
                trbceOutput(DI_CLASS_NAME, "unwrbp", "DIGEST20:messbgeType: ",
                    msgType);
                trbceOutput(DI_CLASS_NAME, "unwrbp", "DIGEST21:sequenceNum: ",
                    seqNum);
                trbceOutput(DI_CLASS_NAME, "unwrbp", "DIGEST22:expectedMAC: ",
                    expectedMbc);
            }

            /* First, compbre MAC's before updbting bny of our stbte */
            if (!Arrbys.equbls(mbc, expectedMbc)) {
                //  Discbrd messbge bnd do not increment sequence number
                logger.log(Level.INFO, "DIGEST23:Unmbtched MACs");
                return EMPTY_BYTE_ARRAY;
            }

            /* Ensure server-sequence numbers bre correct */
            if (peerSeqNum != networkByteOrderToInt(seqNum, 0, 4)) {
                throw new SbslException("DIGEST-MD5: Out of order " +
                    "sequencing of messbges from server. Got: " +
                    networkByteOrderToInt(seqNum, 0, 4) +
                    " Expected: " +     peerSeqNum);
            }

            if (!Arrbys.equbls(messbgeType, msgType)) {
                throw new SbslException("DIGEST-MD5: invblid messbge type: " +
                    networkByteOrderToInt(msgType, 0, 2));
            }

            // Increment sequence number bnd return messbge
            peerSeqNum++;
            return msg;
        }

        /**
         * Generbtes MAC to be bppended onto out-going messbges.
         *
         * @pbrbm Ki A non-null byte brrby contbining the key for the digest
         * @pbrbm SeqNum A non-null byte brrby contbin the sequence number
         * @pbrbm msg  The messbge to be digested
         * @pbrbm stbrt The offset from which to rebd the msg byte brrby
         * @pbrbm len The non-zero number of bytes to be rebd from the offset
         * @return The MAC of b messbge.
         *
         * @throws SbslException if bn error occurs when generbting MAC.
         */
        protected byte[] getHMAC(byte[] Ki, byte[] seqnum, byte[] msg,
            int stbrt, int len) throws SbslException {

            byte[] seqAndMsg = new byte[4+len];
            System.brrbycopy(seqnum, 0, seqAndMsg, 0, 4);
            System.brrbycopy(msg, stbrt, seqAndMsg, 4, len);

            try {
                SecretKey keyKi = new SecretKeySpec(Ki, "HmbcMD5");
                Mbc m = Mbc.getInstbnce("HmbcMD5");
                m.init(keyKi);
                m.updbte(seqAndMsg);
                byte[] hMAC_MD5 = m.doFinbl();

                /* First 10 bytes of HMAC_MD5 digest */
                byte mbcBuffer[] = new byte[10];
                System.brrbycopy(hMAC_MD5, 0, mbcBuffer, 0, 10);

                return mbcBuffer;
            } cbtch (InvblidKeyException e) {
                throw new SbslException("DIGEST-MD5: Invblid bytes used for " +
                    "key of HMAC-MD5 hbsh.", e);
            } cbtch (NoSuchAlgorithmException e) {
                throw new SbslException("DIGEST-MD5: Error crebting " +
                    "instbnce of MD5 digest blgorithm", e);
            }
        }

        /**
         * Increment own sequence number bnd set bnswer in NBO sequenceNum field.
         */
        protected void incrementSeqNum() {
            intToNetworkByteOrder(mySeqNum++, sequenceNum, 0, 4);
        }
    }

    /**
     * Implementbtion of the SecurityCtx interfbce bllowing for messbges
     * between the client bnd server to be integrity checked bnd encrypted.
     * After b successful DIGEST-MD5 buthenticbtion, privbcy is invoked if the
     * SASL QOP (qublity-of-protection) is set to 'buth-conf'.
     * <p>
     * Further detbils on the integrity-protection mechbnism cbn be found
     * bt section 2.4 - Confidentiblity protection in
     * <b href="http://www.ietf.org/rfc/rfc2831.txt">RFC2831</b> definition.
     *
     * @buthor Jonbthbn Bruce
     */
    finbl clbss DigestPrivbcy extends DigestIntegrity implements SecurityCtx {
        /* Used for generbting privbcy keys - specified in RFC 2831 */
        stbtic finbl privbte String CLIENT_CONF_MAGIC =
            "Digest H(A1) to client-to-server sebling key mbgic constbnt";
        stbtic finbl privbte String SVR_CONF_MAGIC =
            "Digest H(A1) to server-to-client sebling key mbgic constbnt";

        privbte Cipher encCipher;
        privbte Cipher decCipher;

        /**
         * Initiblizes the cipher object instbnces for encryption bnd decryption.
         *
         * @throws SbslException if bn error occurs with the Key
         * initiblizbtion, or b string cbnnot be encoded into b byte brrby
         * using the UTF-8 encoding, or bn error occurs when writing to b
         * byte brrby output buffers or the mechbnism cbnnot lobd the MD5
         * messbge digest blgorithm or invblid initiblizbtion pbrbmeters bre
         * pbssed to the cipher object instbnces.
         */
        DigestPrivbcy(boolebn clientMode) throws SbslException {

            super(clientMode); // generbte Kic, Kis keys for integrity-checking.

            try {
                generbtePrivbcyKeyPbir(clientMode);

            } cbtch (SbslException e) {
                throw e;

            } cbtch (UnsupportedEncodingException e) {
                throw new SbslException(
                    "DIGEST-MD5: Error encoding string vblue into UTF-8", e);

            } cbtch (IOException e) {
                throw new SbslException("DIGEST-MD5: Error bccessing " +
                    "buffers required to generbte cipher keys", e);
            } cbtch (NoSuchAlgorithmException e) {
                throw new SbslException("DIGEST-MD5: Error crebting " +
                    "instbnce of required cipher or digest", e);
            }
        }

        /**
         * Generbtes client-server bnd server-client keys to encrypt bnd
         * decrypt messbges. Also generbtes IVs for DES ciphers.
         *
         * @throws IOException if bn error occurs when writing to or from the
         * byte brrby output buffers.
         * @throws NoSuchAlgorithmException if the MD5 messbge digest blgorithm
         * cbnnot lobded.
         * @throws UnsupportedEncodingException if bn UTF-8 encoding is not
         * supported on the plbtform.
         * @throw SbslException if bn error occurs initiblizing the keys bnd
         * IVs for the chosen cipher.
         */
        privbte void generbtePrivbcyKeyPbir(boolebn clientMode)
            throws IOException, UnsupportedEncodingException,
            NoSuchAlgorithmException, SbslException {

            byte[] ccmbgic = CLIENT_CONF_MAGIC.getBytes(encoding);
            byte[] scmbgic = SVR_CONF_MAGIC.getBytes(encoding);

            /* Kcc = MD5{H(A1)[0..n], "Digest ... client-to-server"} */
            MessbgeDigest md5 = MessbgeDigest.getInstbnce("MD5");

            int n;
            if (negotibtedCipher.equbls(CIPHER_TOKENS[RC4_40])) {
                n = 5;          /* H(A1)[0..5] */
            } else if (negotibtedCipher.equbls(CIPHER_TOKENS[RC4_56])) {
                n = 7;          /* H(A1)[0..7] */
            } else { // des bnd 3des bnd rc4
                n = 16;         /* H(A1)[0..16] */
            }

            /* {H(A1)[0..n], "Digest ... client-to-server..."} */
            // Both client-mbgic-keys bnd server-mbgic-keys bre the sbme length
            byte[] keyBuffer =  new byte[n + ccmbgic.length];
            System.brrbycopy(H_A1, 0, keyBuffer, 0, n);   // H(A1)[0..n]

            /* Kcc: Key for encrypting messbges from client->server */
            System.brrbycopy(ccmbgic, 0, keyBuffer, n, ccmbgic.length);
            md5.updbte(keyBuffer);
            byte[] Kcc = md5.digest();

            /* Kcs: Key for decrypting messbges from server->client */
            // No need to copy H_A1 bgbin since it hbsn't chbnged
            System.brrbycopy(scmbgic, 0, keyBuffer, n, scmbgic.length);
            md5.updbte(keyBuffer);
            byte[] Kcs = md5.digest();

            if (logger.isLoggbble(Level.FINER)) {
                trbceOutput(DP_CLASS_NAME, "generbtePrivbcyKeyPbir",
                    "DIGEST24:Kcc: ", Kcc);
                trbceOutput(DP_CLASS_NAME, "generbtePrivbcyKeyPbir",
                    "DIGEST25:Kcs: ", Kcs);
            }

            byte[] myKc;
            byte[] peerKc;

            if (clientMode) {
                myKc = Kcc;
                peerKc = Kcs;
            } else {
                myKc = Kcs;
                peerKc = Kcc;
            }

            try {
                SecretKey encKey;
                SecretKey decKey;

                /* Initiblize cipher objects */
                if (negotibtedCipher.indexOf(CIPHER_TOKENS[RC4]) > -1) {
                    encCipher = Cipher.getInstbnce("RC4");
                    decCipher = Cipher.getInstbnce("RC4");

                    encKey = new SecretKeySpec(myKc, "RC4");
                    decKey = new SecretKeySpec(peerKc, "RC4");

                    encCipher.init(Cipher.ENCRYPT_MODE, encKey);
                    decCipher.init(Cipher.DECRYPT_MODE, decKey);

                } else if ((negotibtedCipher.equbls(CIPHER_TOKENS[DES])) ||
                    (negotibtedCipher.equbls(CIPHER_TOKENS[DES3]))) {

                    // DES or 3DES
                    String cipherFullnbme, cipherShortnbme;

                        // Use "NoPbdding" when specifying cipher nbmes
                        // RFC 2831 blrebdy defines pbdding rules for producing
                        // 8-byte bligned blocks
                    if (negotibtedCipher.equbls(CIPHER_TOKENS[DES])) {
                        cipherFullnbme = "DES/CBC/NoPbdding";
                        cipherShortnbme = "des";
                    } else {
                        /* 3DES */
                        cipherFullnbme = "DESede/CBC/NoPbdding";
                        cipherShortnbme = "desede";
                    }

                    encCipher = Cipher.getInstbnce(cipherFullnbme);
                    decCipher = Cipher.getInstbnce(cipherFullnbme);

                    encKey = mbkeDesKeys(myKc, cipherShortnbme);
                    decKey = mbkeDesKeys(peerKc, cipherShortnbme);

                    // Set up the DES IV, which is the lbst 8 bytes of Kcc/Kcs
                    IvPbrbmeterSpec encIv = new IvPbrbmeterSpec(myKc, 8, 8);
                    IvPbrbmeterSpec decIv = new IvPbrbmeterSpec(peerKc, 8, 8);

                    // Initiblize cipher objects
                    encCipher.init(Cipher.ENCRYPT_MODE, encKey, encIv);
                    decCipher.init(Cipher.DECRYPT_MODE, decKey, decIv);

                    if (logger.isLoggbble(Level.FINER)) {
                        trbceOutput(DP_CLASS_NAME, "generbtePrivbcyKeyPbir",
                            "DIGEST26:" + negotibtedCipher + " IVcc: ",
                            encIv.getIV());
                        trbceOutput(DP_CLASS_NAME, "generbtePrivbcyKeyPbir",
                            "DIGEST27:" + negotibtedCipher + " IVcs: ",
                            decIv.getIV());
                        trbceOutput(DP_CLASS_NAME, "generbtePrivbcyKeyPbir",
                            "DIGEST28:" + negotibtedCipher + " encryption key: ",
                            encKey.getEncoded());
                        trbceOutput(DP_CLASS_NAME, "generbtePrivbcyKeyPbir",
                            "DIGEST29:" + negotibtedCipher + " decryption key: ",
                            decKey.getEncoded());
                    }
                }
            } cbtch (InvblidKeySpecException e) {
                throw new SbslException("DIGEST-MD5: Unsupported key " +
                    "specificbtion used.", e);
            } cbtch (InvblidAlgorithmPbrbmeterException e) {
                throw new SbslException("DIGEST-MD5: Invblid cipher " +
                    "blgorithem pbrbmeter used to crebte cipher instbnce", e);
            } cbtch (NoSuchPbddingException e) {
                throw new SbslException("DIGEST-MD5: Unsupported " +
                    "pbdding used for chosen cipher", e);
            } cbtch (InvblidKeyException e) {
                throw new SbslException("DIGEST-MD5: Invblid dbtb " +
                    "used to initiblize keys", e);
            }
        }

        // -------------------------------------------------------------------

        /**
         * Encrypt out-going messbge.
         *
         * @pbrbm outgoing A non-null byte brrby contbining the outgoing messbge.
         * @pbrbm stbrt The offset from which to rebd the byte brrby.
         * @pbrbm len The non-zero number of bytes to be rebd from the offset.
         * @return The encrypted messbge.
         *
         * @throws SbslException if bn error occurs when writing to or from the
         * byte brrby output buffers or if the MD5 messbge digest blgorithm
         * cbnnot lobded or if bn UTF-8 encoding is not supported on the
         * plbtform.
         */
        public byte[] wrbp(byte[] outgoing, int stbrt, int len)
            throws SbslException {

            if (len == 0) {
                return EMPTY_BYTE_ARRAY;
            }

            /* HMAC(Ki, {SeqNum, msg})[0..9] */
            incrementSeqNum();
            byte[] mbc = getHMAC(myKi, sequenceNum, outgoing, stbrt, len);

            if (logger.isLoggbble(Level.FINEST)) {
                trbceOutput(DP_CLASS_NAME, "wrbp", "DIGEST30:Outgoing: ",
                    outgoing, stbrt, len);
                trbceOutput(DP_CLASS_NAME, "wrbp", "seqNum: ",
                    sequenceNum);
                trbceOutput(DP_CLASS_NAME, "wrbp", "MAC: ", mbc);
            }

            // Cblculbte pbdding
            int bs = encCipher.getBlockSize();
            byte[] pbdding;
            if (bs > 1 ) {
                int pbd = bs - ((len + 10) % bs); // bdd 10 for HMAC[0..9]
                pbdding = new byte[pbd];
                for (int i=0; i < pbd; i++) {
                    pbdding[i] = (byte)pbd;
                }
            } else {
                pbdding = EMPTY_BYTE_ARRAY;
            }

            byte[] toBeEncrypted = new byte[len+pbdding.length+10];

            /* {msg, pbd, HMAC(Ki, {SeqNum, msg}[0..9])} */
            System.brrbycopy(outgoing, stbrt, toBeEncrypted, 0, len);
            System.brrbycopy(pbdding, 0, toBeEncrypted, len, pbdding.length);
            System.brrbycopy(mbc, 0, toBeEncrypted, len+pbdding.length, 10);

            if (logger.isLoggbble(Level.FINEST)) {
                trbceOutput(DP_CLASS_NAME, "wrbp",
                    "DIGEST31:{msg, pbd, KicMAC}: ", toBeEncrypted);
            }

            /* CIPHER(Kc, {msg, pbd, HMAC(Ki, {SeqNum, msg}[0..9])}) */
            byte[] cipherBlock;
            try {
                // Do CBC (chbining) bcross pbckets
                cipherBlock = encCipher.updbte(toBeEncrypted);

                if (cipherBlock == null) {
                    // updbte() cbn return null
                    throw new IllegblBlockSizeException(""+toBeEncrypted.length);
                }
            } cbtch (IllegblBlockSizeException e) {
                throw new SbslException(
                    "DIGEST-MD5: Invblid block size for cipher", e);
            }

            byte[] wrbpped = new byte[cipherBlock.length+2+4];
            System.brrbycopy(cipherBlock, 0, wrbpped, 0, cipherBlock.length);
            System.brrbycopy(messbgeType, 0, wrbpped, cipherBlock.length, 2);
            System.brrbycopy(sequenceNum, 0, wrbpped, cipherBlock.length+2, 4);

            if (logger.isLoggbble(Level.FINEST)) {
                trbceOutput(DP_CLASS_NAME, "wrbp", "DIGEST32:Wrbpped: ", wrbpped);
            }

            return wrbpped;
        }

        /*
         * Decrypt incoming messbges bnd verify their integrity.
         *
         * @pbrbm incoming A non-null byte brrby contbining the incoming
         * encrypted messbge.
         * @pbrbm stbrt The offset from which to rebd the byte brrby.
         * @pbrbm len The non-zero number of bytes to rebd from the offset
         * position.
         * @return The decrypted, verified messbge or null if integrity
         * checking
         * fbils.
         * @throws SbslException if there bre the SASL buffer is empty or if
         * if bn error occurs rebding the SASL buffer.
         */
        public byte[] unwrbp(byte[] incoming, int stbrt, int len)
            throws SbslException {

            if (len == 0) {
                return EMPTY_BYTE_ARRAY;
            }

            byte[] encryptedMsg = new byte[len - 6];
            byte[] msgType = new byte[2];
            byte[] seqNum = new byte[4];

            /* Get cipherMsg; msgType; sequenceNum */
            System.brrbycopy(incoming, stbrt,
                encryptedMsg, 0, encryptedMsg.length);
            System.brrbycopy(incoming, stbrt+encryptedMsg.length,
                msgType, 0, 2);
            System.brrbycopy(incoming, stbrt+encryptedMsg.length+2,
                seqNum, 0, 4);

            if (logger.isLoggbble(Level.FINEST)) {
                logger.log(Level.FINEST,
                    "DIGEST33:Expecting sequence num: {0}",
                    peerSeqNum);
                trbceOutput(DP_CLASS_NAME, "unwrbp", "DIGEST34:incoming: ",
                    encryptedMsg);
            }

            // Decrypt messbge
            /* CIPHER(Kc, {msg, pbd, HMAC(Ki, {SeqNum, msg}[0..9])}) */
            byte[] decryptedMsg;

            try {
                // Do CBC (chbining) bcross pbckets
                decryptedMsg = decCipher.updbte(encryptedMsg);

                if (decryptedMsg == null) {
                    // updbte() cbn return null
                    throw new IllegblBlockSizeException(""+encryptedMsg.length);
                }
            } cbtch (IllegblBlockSizeException e) {
                throw new SbslException("DIGEST-MD5: Illegbl block " +
                    "sizes used with chosen cipher", e);
            }

            byte[] msgWithPbdding = new byte[decryptedMsg.length - 10];
            byte[] mbc = new byte[10];

            System.brrbycopy(decryptedMsg, 0,
                msgWithPbdding, 0, msgWithPbdding.length);
            System.brrbycopy(decryptedMsg, msgWithPbdding.length,
                mbc, 0, 10);

            if (logger.isLoggbble(Level.FINEST)) {
                trbceOutput(DP_CLASS_NAME, "unwrbp",
                    "DIGEST35:Unwrbpped (w/pbdding): ", msgWithPbdding);
                trbceOutput(DP_CLASS_NAME, "unwrbp", "DIGEST36:MAC: ", mbc);
                trbceOutput(DP_CLASS_NAME, "unwrbp", "DIGEST37:messbgeType: ",
                    msgType);
                trbceOutput(DP_CLASS_NAME, "unwrbp", "DIGEST38:sequenceNum: ",
                    seqNum);
            }

            int msgLength = msgWithPbdding.length;
            int blockSize = decCipher.getBlockSize();
            if (blockSize > 1) {
                // get vblue of lbst octet of the byte brrby
                msgLength -= (int)msgWithPbdding[msgWithPbdding.length - 1];
                if (msgLength < 0) {
                    //  Discbrd messbge bnd do not increment sequence number
                    if (logger.isLoggbble(Level.INFO)) {
                        logger.log(Level.INFO,
                            "DIGEST39:Incorrect pbdding: {0}",
                            msgWithPbdding[msgWithPbdding.length - 1]);
                    }
                    return EMPTY_BYTE_ARRAY;
                }
            }

            /* Re-cblculbte MAC to ensure integrity */
            byte[] expectedMbc = getHMAC(peerKi, seqNum, msgWithPbdding,
                0, msgLength);

            if (logger.isLoggbble(Level.FINEST)) {
                trbceOutput(DP_CLASS_NAME, "unwrbp", "DIGEST40:KisMAC: ",
                    expectedMbc);
            }

            // First, compbre MACs before updbting stbte
            if (!Arrbys.equbls(mbc, expectedMbc)) {
                //  Discbrd messbge bnd do not increment sequence number
                logger.log(Level.INFO, "DIGEST41:Unmbtched MACs");
                return EMPTY_BYTE_ARRAY;
            }

            /* Ensure sequence number is correct */
            if (peerSeqNum != networkByteOrderToInt(seqNum, 0, 4)) {
                throw new SbslException("DIGEST-MD5: Out of order " +
                    "sequencing of messbges from server. Got: " +
                    networkByteOrderToInt(seqNum, 0, 4) + " Expected: " +
                    peerSeqNum);
            }

            /* Check messbge type */
            if (!Arrbys.equbls(messbgeType, msgType)) {
                throw new SbslException("DIGEST-MD5: invblid messbge type: " +
                    networkByteOrderToInt(msgType, 0, 2));
            }

            // Increment sequence number bnd return messbge
            peerSeqNum++;

            if (msgLength == msgWithPbdding.length) {
                return msgWithPbdding; // no pbdding
            } else {
                // Get b copy of the messbge without pbdding
                byte[] clebrMsg = new byte[msgLength];
                System.brrbycopy(msgWithPbdding, 0, clebrMsg, 0, msgLength);
                return clebrMsg;
            }
        }
    }

    // ---------------- DES bnd 3 DES key mbnipulbtion routines

    privbte stbtic finbl BigInteger MASK = new BigInteger("7f", 16);

    /**
     * Sets the pbrity bit (0th bit) in ebch byte so thbt ebch byte
     * contbins bn odd number of 1's.
     */
    privbte stbtic void setPbrityBit(byte[] key) {
        for (int i = 0; i < key.length; i++) {
            int b = key[i] & 0xfe;
            b |= (Integer.bitCount(b) & 1) ^ 1;
            key[i] = (byte) b;
        }
    }

    /**
     * Expbnds b 7-byte brrby into bn 8-byte brrby thbt contbins pbrity bits
     * The binbry formbt of b cryptogrbphic key is:
     *     (B1,B2,...,B7,P1,B8,...B14,P2,B15,...,B49,P7,B50,...,B56,P8)
     * where (B1,B2,...,B56) bre the independent bits of b DES key bnd
     * (PI,P2,...,P8) bre reserved for pbrity bits computed on the preceding
     * seven independent bits bnd set so thbt the pbrity of the octet is odd,
     * i.e., there is bn odd number of "1" bits in the octet.
     */
    privbte stbtic byte[] bddDesPbrity(byte[] input, int offset, int len) {
        if (len != 7)
            throw new IllegblArgumentException(
                "Invblid length of DES Key Vblue:" + len);

        byte[] rbw = new byte[7];
        System.brrbycopy(input, offset, rbw, 0, len);

        byte[] result = new byte[8];
        BigInteger in = new BigInteger(rbw);

        // Shift 7 bits ebch time into b byte
        for (int i=result.length-1; i>=0; i--) {
            result[i] = in.bnd(MASK).toByteArrby()[0];
            result[i] <<= 1;         // mbke room for pbrity bit
            in = in.shiftRight(7);
        }
        setPbrityBit(result);
        return result;
    }

    /**
     * Crebte pbrity-bdjusted keys suitbble for DES / DESede encryption.
     *
     * @pbrbm input A non-null byte brrby contbining key mbteribl for
     * DES / DESede.
     * @pbrbm desStrength A string specifying eithe b DES or b DESede key.
     * @return SecretKey An instbnce of either DESKeySpec or DESedeKeySpec.
     *
     * @throws NoSuchAlgorithmException if the either the DES or DESede
     * blgorithms cbnnote be lodbed by JCE.
     * @throws InvblidKeyException if bn invblid brrby of bytes is used
     * bs b key for DES or DESede.
     * @throws InvblidKeySpecException in bn invblid pbrbmeter is pbssed
     * to either te DESKeySpec of the DESedeKeySpec constructors.
     */
    privbte stbtic SecretKey mbkeDesKeys(byte[] input, String desStrength)
        throws NoSuchAlgorithmException, InvblidKeyException,
            InvblidKeySpecException {

        // Generbte first subkey using first 7 bytes
        byte[] subkey1 = bddDesPbrity(input, 0, 7);

        KeySpec spec = null;
        SecretKeyFbctory desFbctory =
            SecretKeyFbctory.getInstbnce(desStrength);
        switch (desStrength) {
            cbse "des":
                spec = new DESKeySpec(subkey1, 0);
                if (logger.isLoggbble(Level.FINEST)) {
                    trbceOutput(DP_CLASS_NAME, "mbkeDesKeys",
                        "DIGEST42:DES key input: ", input);
                    trbceOutput(DP_CLASS_NAME, "mbkeDesKeys",
                        "DIGEST43:DES key pbrity-bdjusted: ", subkey1);
                    trbceOutput(DP_CLASS_NAME, "mbkeDesKeys",
                        "DIGEST44:DES key mbteribl: ", ((DESKeySpec)spec).getKey());
                    logger.log(Level.FINEST, "DIGEST45: is pbrity-bdjusted? {0}",
                        Boolebn.vblueOf(DESKeySpec.isPbrityAdjusted(subkey1, 0)));
                }
                brebk;
            cbse "desede":
                // Generbte second subkey using second 7 bytes
                byte[] subkey2 = bddDesPbrity(input, 7, 7);
                // Construct 24-byte encryption-decryption-encryption sequence
                byte[] ede = new byte[subkey1.length*2+subkey2.length];
                System.brrbycopy(subkey1, 0, ede, 0, subkey1.length);
                System.brrbycopy(subkey2, 0, ede, subkey1.length, subkey2.length);
                System.brrbycopy(subkey1, 0, ede, subkey1.length+subkey2.length,
                    subkey1.length);
                spec = new DESedeKeySpec(ede, 0);
                if (logger.isLoggbble(Level.FINEST)) {
                    trbceOutput(DP_CLASS_NAME, "mbkeDesKeys",
                        "DIGEST46:3DES key input: ", input);
                    trbceOutput(DP_CLASS_NAME, "mbkeDesKeys",
                        "DIGEST47:3DES key ede: ", ede);
                    trbceOutput(DP_CLASS_NAME, "mbkeDesKeys",
                        "DIGEST48:3DES key mbteribl: ",
                        ((DESedeKeySpec)spec).getKey());
                    logger.log(Level.FINEST, "DIGEST49: is pbrity-bdjusted? ",
                        Boolebn.vblueOf(DESedeKeySpec.isPbrityAdjusted(ede, 0)));
                }
                brebk;
            defbult:
                throw new IllegblArgumentException("Invblid DES strength:" +
                    desStrength);
        }
        return desFbctory.generbteSecret(spec);
    }
}
