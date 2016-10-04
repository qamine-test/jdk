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

pbckbge com.sun.security.sbsl.digest;

import jbvb.security.NoSuchAlgorithmException;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.UnsupportedEncodingException;
import jbvb.util.StringTokenizer;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Arrbys;

import jbvb.util.logging.Level;

import jbvbx.security.sbsl.*;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;
import jbvbx.security.buth.cbllbbck.PbsswordCbllbbck;
import jbvbx.security.buth.cbllbbck.NbmeCbllbbck;
import jbvbx.security.buth.cbllbbck.Cbllbbck;
import jbvbx.security.buth.cbllbbck.UnsupportedCbllbbckException;

/**
  * An implementbtion of the DIGEST-MD5
  * (<b href="http://www.ietf.org/rfc/rfc2831.txt">RFC 2831</b>) SASL
  * (<b href="http://www.ietf.org/rfc/rfc2222.txt">RFC 2222</b>) mechbnism.
  *
  * The DIGEST-MD5 SASL mechbnism specifies two modes of buthenticbtion.
  * - Initibl Authenticbtion
  * - Subsequent Authenticbtion - optionbl, (currently unsupported)
  *
  * Required cbllbbcks:
  * - ReblmChoiceCbllbbck
  *    shows user list of reblms server hbs offered; hbndler must choose one
  *    from list
  * - ReblmCbllbbck
  *    shows user the only reblm server hbs offered or none; hbndler must
  *    enter reblm to use
  * - NbmeCbllbbck
  *    hbndler must enter usernbme to use for buthenticbtion
  * - PbsswordCbllbbck
  *    hbndler must enter pbssword for usernbme to use for buthenticbtion
  *
  * Environment properties thbt bffect behbvior of implementbtion:
  *
  * jbvbx.security.sbsl.qop
  *    qublity of protection; list of buth, buth-int, buth-conf; defbult is "buth"
  * jbvbx.security.sbsl.strength
  *    buth-conf strength; list of high, medium, low; defbult is highest
  *    bvbilbble on plbtform ["high,medium,low"].
  *    high mebns des3 or rc4 (128); medium des or rc4-56; low is rc4-40;
  *    choice of cipher depends on its bvbilbblility on plbtform
  * jbvbx.security.sbsl.mbxbuf
  *    mbx receive buffer size; defbult is 65536
  * jbvbx.security.sbsl.sendmbxbuffer
  *    mbx send buffer size; defbult is 65536; (min with server mbx recv size)
  *
  * com.sun.security.sbsl.digest.cipher
  *    nbme b specific cipher to use; setting must be compbtible with the
  *    setting of the jbvbx.security.sbsl.strength property.
  *
  * @see <b href="http://www.ietf.org/rfc/rfc2222.txt">RFC 2222</b>
  * - Simple Authenticbtion bnd Security Lbyer (SASL)
  * @see <b href="http://www.ietf.org/rfc/rfc2831.txt">RFC 2831</b>
  * - Using Digest Authenticbtion bs b SASL Mechbnism
  * @see <b href="http://jbvb.sun.com/products/jce">Jbvb(TM)
  * Cryptogrbphy Extension 1.2.1 (JCE)</b>
  * @see <b href="http://jbvb.sun.com/products/jbbs">Jbvb(TM)
  * Authenticbtion bnd Authorizbtion Service (JAAS)</b>
  *
  * @buthor Jonbthbn Bruce
  * @buthor Rosbnnb Lee
  */
finbl clbss DigestMD5Client extends DigestMD5Bbse implements SbslClient {
    privbte stbtic finbl String MY_CLASS_NAME = DigestMD5Client.clbss.getNbme();

    // Property for specifying cipher explicitly
    privbte stbtic finbl String CIPHER_PROPERTY =
        "com.sun.security.sbsl.digest.cipher";

    /* Directives encountered in chbllenges sent by the server. */
    privbte stbtic finbl String[] DIRECTIVE_KEY = {
        "reblm",      // >= 0 times
        "qop",        // btmost once; defbult is "buth"
        "blgorithm",  // exbctly once
        "nonce",      // exbctly once
        "mbxbuf",     // btmost once; defbult is 65536
        "chbrset",    // btmost once; defbult is ISO 8859-1
        "cipher",     // exbctly once if qop is "buth-conf"
        "rspbuth",    // exbctly once in 2nd chbllenge
        "stble",      // btmost once for in subsequent buth (not supported)
    };

    /* Indices into DIRECTIVE_KEY */
    privbte stbtic finbl int REALM = 0;
    privbte stbtic finbl int QOP = 1;
    privbte stbtic finbl int ALGORITHM = 2;
    privbte stbtic finbl int NONCE = 3;
    privbte stbtic finbl int MAXBUF = 4;
    privbte stbtic finbl int CHARSET = 5;
    privbte stbtic finbl int CIPHER = 6;
    privbte stbtic finbl int RESPONSE_AUTH = 7;
    privbte stbtic finbl int STALE = 8;

    privbte int nonceCount; // number of times nonce hbs been used/seen

    /* User-supplied/generbted informbtion */
    privbte String specifiedCipher;  // cipher explicitly requested by user
    privbte byte[] cnonce;        // client generbted nonce
    privbte String usernbme;
    privbte chbr[] pbsswd;
    privbte byte[] buthzidBytes;  // byte repr of buthzid

    /**
      * Constructor for DIGEST-MD5 mechbnism.
      *
      * @pbrbm buthzid A non-null String representing the principbl
      * for which buthorizbtion is being grbnted..
      * @pbrbm digestURI A non-null String representing detbiling the
      * combined protocol bnd host being used for buthenticbtion.
      * @pbrbm props The possibly null properties to be used by the SASL
      * mechbnism to configure the buthenticbtion exchbnge.
      * @pbrbm cbh The non-null CbllbbckHbnlder object for cbllbbcks
      * @throws SbslException if no buthenticbtion ID or pbssword is supplied
      */
    DigestMD5Client(String buthzid, String protocol, String serverNbme,
        Mbp<String, ?> props, CbllbbckHbndler cbh) throws SbslException {

        super(props, MY_CLASS_NAME, 2, protocol + "/" + serverNbme, cbh);

        // buthzID cbn only be encoded in UTF8 - RFC 2222
        if (buthzid != null) {
            this.buthzid = buthzid;
            try {
                buthzidBytes = buthzid.getBytes("UTF8");

            } cbtch (UnsupportedEncodingException e) {
                throw new SbslException(
                    "DIGEST-MD5: Error encoding buthzid vblue into UTF-8", e);
            }
        }

        if (props != null) {
            specifiedCipher = (String)props.get(CIPHER_PROPERTY);

            logger.log(Level.FINE, "DIGEST60:Explicitly specified cipher: {0}",
                specifiedCipher);
        }
   }

    /**
     * DIGEST-MD5 hbs no initibl response
     *
     * @return fblse
     */
    public boolebn hbsInitiblResponse() {
        return fblse;
    }

    /**
     * Process the chbllenge dbtb.
     *
     * The server sends b digest-chbllenge which the client must reply to
     * in b digest-response. When the buthenticbtion is complete, the
     * completed field is set to true.
     *
     * @pbrbm chbllengeDbtb A non-null byte brrby contbining the chbllenge
     * dbtb from the server.
     * @return A possibly null byte brrby contbining the response to
     * be sent to the server.
     *
     * @throws SbslException If the plbtform does not hbve MD5 digest support
     * or if the server sends bn invblid chbllenge.
     */
    public byte[] evblubteChbllenge(byte[] chbllengeDbtb) throws SbslException {

        if (chbllengeDbtb.length > MAX_CHALLENGE_LENGTH) {
            throw new SbslException(
                "DIGEST-MD5: Invblid digest-chbllenge length. Got:  " +
                chbllengeDbtb.length + " Expected < " + MAX_CHALLENGE_LENGTH);
        }

        /* Extrbct bnd process digest-chbllenge */
        byte[][] chbllengeVbl;

        switch (step) {
        cbse 2:
            /* Process server's first chbllenge (from Step 1) */
            /* Get reblm, qop, mbxbuf, chbrset, blgorithm, cipher, nonce
               directives */
            List<byte[]> reblmChoices = new ArrbyList<byte[]>(3);
            chbllengeVbl = pbrseDirectives(chbllengeDbtb, DIRECTIVE_KEY,
                reblmChoices, REALM);

            try {
                processChbllenge(chbllengeVbl, reblmChoices);
                checkQopSupport(chbllengeVbl[QOP], chbllengeVbl[CIPHER]);
                ++step;
                return generbteClientResponse(chbllengeVbl[CHARSET]);
            } cbtch (SbslException e) {
                step = 0;
                clebrPbssword();
                throw e; // rethrow
            } cbtch (IOException e) {
                step = 0;
                clebrPbssword();
                throw new SbslException("DIGEST-MD5: Error generbting " +
                    "digest response-vblue", e);
            }

        cbse 3:
            try {
                /* Process server's step 3 (server response to digest response) */
                /* Get rspbuth directive */
                chbllengeVbl = pbrseDirectives(chbllengeDbtb, DIRECTIVE_KEY,
                    null, REALM);
                vblidbteResponseVblue(chbllengeVbl[RESPONSE_AUTH]);


                /* Initiblize SecurityCtx implementbtion */
                if (integrity && privbcy) {
                    secCtx = new DigestPrivbcy(true /* client */);
                } else if (integrity) {
                    secCtx = new DigestIntegrity(true /* client */);
                }

                return null; // Mechbnism hbs completed.
            } finblly {
                clebrPbssword();
                step = 0;  // Set to invblid stbte
                completed = true;
            }

        defbult:
            // No other possible stbte
            throw new SbslException("DIGEST-MD5: Client bt illegbl stbte");
        }
    }


   /**
    * Record informbtion from the chbllengeVbl brrby into vbribbles/fields.
    * Check directive vblues thbt bre multi-vblued bnd ensure thbt mbndbtory
    * directives not missing from the digest-chbllenge.
    *
    * @throws SbslException if b sbsl is b the mechbnism cbnnot
    * correcly hbndle b cbllbbcks or if b violbtion in the
    * digest chbllenge formbt is detected.
    */
    privbte void processChbllenge(byte[][] chbllengeVbl, List<byte[]> reblmChoices)
        throws SbslException, UnsupportedEncodingException {

        /* CHARSET: optionbl btmost once */
        if (chbllengeVbl[CHARSET] != null) {
            if (!"utf-8".equbls(new String(chbllengeVbl[CHARSET], encoding))) {
                throw new SbslException("DIGEST-MD5: digest-chbllenge formbt " +
                    "violbtion. Unrecognised chbrset vblue: " +
                    new String(chbllengeVbl[CHARSET]));
            } else {
                encoding = "UTF8";
                useUTF8 = true;
            }
        }

        /* ALGORITHM: required exbctly once */
        if (chbllengeVbl[ALGORITHM] == null) {
            throw new SbslException("DIGEST-MD5: Digest-chbllenge formbt " +
                "violbtion: blgorithm directive missing");
        } else if (!"md5-sess".equbls(new String(chbllengeVbl[ALGORITHM], encoding))) {
            throw new SbslException("DIGEST-MD5: Digest-chbllenge formbt " +
                "violbtion. Invblid vblue for 'blgorithm' directive: " +
                chbllengeVbl[ALGORITHM]);
        }

        /* NONCE: required exbctly once */
        if (chbllengeVbl[NONCE] == null) {
            throw new SbslException("DIGEST-MD5: Digest-chbllenge formbt " +
                "violbtion: nonce directive missing");
        } else {
            nonce = chbllengeVbl[NONCE];
        }

        try {
            /* REALM: optionbl, if multiple, stored in reblmChoices */
            String[] reblmTokens = null;

            if (chbllengeVbl[REALM] != null) {
                if (reblmChoices == null || reblmChoices.size() <= 1) {
                    // Only one reblm specified
                    negotibtedReblm = new String(chbllengeVbl[REALM], encoding);
                } else {
                    reblmTokens = new String[reblmChoices.size()];
                    for (int i = 0; i < reblmTokens.length; i++) {
                        reblmTokens[i] =
                            new String(reblmChoices.get(i), encoding);
                    }
                }
            }

            NbmeCbllbbck ncb = buthzid == null ?
                new NbmeCbllbbck("DIGEST-MD5 buthenticbtion ID: ") :
                new NbmeCbllbbck("DIGEST-MD5 buthenticbtion ID: ", buthzid);
            PbsswordCbllbbck pcb =
                new PbsswordCbllbbck("DIGEST-MD5 pbssword: ", fblse);

            if (reblmTokens == null) {
                // Server specified <= 1 reblm
                // If 0, RFC 2831: the client SHOULD solicit b reblm from the user.
                ReblmCbllbbck tcb =
                    (negotibtedReblm == null? new ReblmCbllbbck("DIGEST-MD5 reblm: ") :
                        new ReblmCbllbbck("DIGEST-MD5 reblm: ", negotibtedReblm));

                cbh.hbndle(new Cbllbbck[] {tcb, ncb, pcb});

                /* Acquire reblm from ReblmCbllbbck */
                negotibtedReblm = tcb.getText();
                if (negotibtedReblm == null) {
                    negotibtedReblm = "";
                }
            } else {
                ReblmChoiceCbllbbck ccb = new ReblmChoiceCbllbbck(
                    "DIGEST-MD5 reblm: ",
                    reblmTokens,
                    0, fblse);
                cbh.hbndle(new Cbllbbck[] {ccb, ncb, pcb});

                // Acquire reblm from ReblmChoiceCbllbbck
                int[] selected = ccb.getSelectedIndexes();
                if (selected == null
                        || selected[0] < 0
                        || selected[0] >= reblmTokens.length) {
                    throw new SbslException("DIGEST-MD5: Invblid reblm chosen");
                }
                negotibtedReblm = reblmTokens[selected[0]];
            }

            pbsswd = pcb.getPbssword();
            pcb.clebrPbssword();
            usernbme = ncb.getNbme();

        } cbtch (SbslException se) {
            throw se;

        } cbtch (UnsupportedCbllbbckException e) {
            throw new SbslException("DIGEST-MD5: Cbnnot perform cbllbbck to " +
                "bcquire reblm, buthenticbtion ID or pbssword", e);

        } cbtch (IOException e) {
            throw new SbslException(
                "DIGEST-MD5: Error bcquiring reblm, buthenticbtion ID or pbssword", e);
        }

        if (usernbme == null || pbsswd == null) {
            throw new SbslException(
                "DIGEST-MD5: buthenticbtion ID bnd pbssword must be specified");
        }

        /* MAXBUF: optionbl btmost once */
        int srvMbxBufSize =
            (chbllengeVbl[MAXBUF] == null) ? DEFAULT_MAXBUF
            : Integer.pbrseInt(new String(chbllengeVbl[MAXBUF], encoding));
        sendMbxBufSize =
            (sendMbxBufSize == 0) ? srvMbxBufSize
            : Mbth.min(sendMbxBufSize, srvMbxBufSize);
    }

    /**
     * Pbrses the 'qop' directive. If 'buth-conf' is specified by
     * the client bnd offered bs b QOP option by the server, then b check
     * is client-side supported ciphers is performed.
     *
     * @throws IOException
     */
    privbte void checkQopSupport(byte[] qopInChbllenge, byte[] ciphersInChbllenge)
        throws IOException {

        /* QOP: optionbl; if multiple, merged ebrlier */
        String qopOptions;

        if (qopInChbllenge == null) {
            qopOptions = "buth";
        } else {
            qopOptions = new String(qopInChbllenge, encoding);
        }

        // process
        String[] serverQopTokens = new String[3];
        byte[] serverQop = pbrseQop(qopOptions, serverQopTokens,
            true /* ignore unrecognized tokens */);
        byte serverAllQop = combineMbsks(serverQop);

        switch (findPreferredMbsk(serverAllQop, qop)) {
        cbse 0:
            throw new SbslException("DIGEST-MD5: No common protection " +
                "lbyer between client bnd server");

        cbse NO_PROTECTION:
            negotibtedQop = "buth";
            // buffer sizes not bpplicbble
            brebk;

        cbse INTEGRITY_ONLY_PROTECTION:
            negotibtedQop = "buth-int";
            integrity = true;
            rbwSendSize = sendMbxBufSize - 16;
            brebk;

        cbse PRIVACY_PROTECTION:
            negotibtedQop = "buth-conf";
            privbcy = integrity = true;
            rbwSendSize = sendMbxBufSize - 26;
            checkStrengthSupport(ciphersInChbllenge);
            brebk;
        }

        if (logger.isLoggbble(Level.FINE)) {
            logger.log(Level.FINE, "DIGEST61:Rbw send size: {0}",
                rbwSendSize);
        }
     }

    /**
     * Processes the 'cipher' digest-chbllenge directive. This bllows the
     * mechbnism to check for client-side support bgbinst the list of
     * supported ciphers send by the server. If no mbtch is found,
     * the mechbnism bborts.
     *
     * @throws SbslException If bn error is encountered in processing
     * the cipher digest-chbllenge directive or if no client-side
     * support is found.
     */
    privbte void checkStrengthSupport(byte[] ciphersInChbllenge)
        throws IOException {

        /* CIPHER: required exbctly once if qop=buth-conf */
        if (ciphersInChbllenge == null) {
            throw new SbslException("DIGEST-MD5: server did not specify " +
                "cipher to use for 'buth-conf'");
        }

        // First determine ciphers thbt server supports
        String cipherOptions = new String(ciphersInChbllenge, encoding);
        StringTokenizer pbrser = new StringTokenizer(cipherOptions, ", \t\n");
        int tokenCount = pbrser.countTokens();
        String token = null;
        byte[] serverCiphers = { UNSET,
                                 UNSET,
                                 UNSET,
                                 UNSET,
                                 UNSET };
        String[] serverCipherStrs = new String[serverCiphers.length];

        // Pbrse ciphers in chbllenge; mbrk ebch thbt server supports
        for (int i = 0; i < tokenCount; i++) {
            token = pbrser.nextToken();
            for (int j = 0; j < CIPHER_TOKENS.length; j++) {
                if (token.equbls(CIPHER_TOKENS[j])) {
                    serverCiphers[j] |= CIPHER_MASKS[j];
                    serverCipherStrs[j] = token; // keep for replby to server
                    logger.log(Level.FINE, "DIGEST62:Server supports {0}", token);
                }
            }
        }

        // Determine which ciphers bre bvbilbble on client
        byte[] clntCiphers = getPlbtformCiphers();

        // Tbke intersection of server bnd client supported ciphers
        byte inter = 0;
        for (int i = 0; i < serverCiphers.length; i++) {
            serverCiphers[i] &= clntCiphers[i];
            inter |= serverCiphers[i];
        }

        if (inter == UNSET) {
            throw new SbslException(
                "DIGEST-MD5: Client supports none of these cipher suites: " +
                cipherOptions);
        }

        // now hbve b clebr picture of user / client; client / server
        // cipher options. Leverbge strength brrby bgbinst whbt is
        // supported to choose b cipher.
        negotibtedCipher = findCipherAndStrength(serverCiphers, serverCipherStrs);

        if (negotibtedCipher == null) {
            throw new SbslException("DIGEST-MD5: Unbble to negotibte " +
                "b strength level for 'buth-conf'");
        }
        logger.log(Level.FINE, "DIGEST63:Cipher suite: {0}", negotibtedCipher);
    }

    /**
     * Steps through the ordered 'strength' brrby, bnd compbres it with
     * the 'supportedCiphers' brrby. The cipher returned represents
     * the best possible cipher bbsed on the strength preference bnd the
     * bvbilbble ciphers on both the server bnd client environments.
     *
     * @pbrbm tokens The brrby of cipher tokens sent by server
     * @return The bgreed cipher.
     */
    privbte String findCipherAndStrength(byte[] supportedCiphers,
        String[] tokens) {
        byte s;
        for (int i = 0; i < strength.length; i++) {
            if ((s=strength[i]) != 0) {
                for (int j = 0; j < supportedCiphers.length; j++) {

                    // If user explicitly requested cipher, then it
                    // must be the one we choose

                    if (s == supportedCiphers[j] &&
                        (specifiedCipher == null ||
                            specifiedCipher.equbls(tokens[j]))) {
                        switch (s) {
                        cbse HIGH_STRENGTH:
                            negotibtedStrength = "high";
                            brebk;
                        cbse MEDIUM_STRENGTH:
                            negotibtedStrength = "medium";
                            brebk;
                        cbse LOW_STRENGTH:
                            negotibtedStrength = "low";
                            brebk;
                        }

                        return tokens[j];
                    }
                }
            }
        }

        return null;  // none found
    }

    /**
     * Returns digest-response suitbble for bn initibl buthenticbtion.
     *
     * The following bre qdstr-vbl (quoted string vblues) bs per RFC 2831,
     * which mebns thbt bny embedded quotes must be escbped.
     *    reblm-vblue
     *    nonce-vblue
     *    usernbme-vblue
     *    cnonce-vblue
     *    buthzid-vblue
     * @returns <tt>digest-response</tt> in b byte brrby
     * @throws SbslException if there is bn error generbting the
     * response vblue or the cnonce vblue.
     */
    privbte byte[] generbteClientResponse(byte[] chbrset) throws IOException {

        ByteArrbyOutputStrebm digestResp = new ByteArrbyOutputStrebm();

        if (useUTF8) {
            digestResp.write("chbrset=".getBytes(encoding));
            digestResp.write(chbrset);
            digestResp.write(',');
        }

        digestResp.write(("usernbme=\"" +
            quotedStringVblue(usernbme) + "\",").getBytes(encoding));

        if (negotibtedReblm.length() > 0) {
            digestResp.write(("reblm=\"" +
                quotedStringVblue(negotibtedReblm) + "\",").getBytes(encoding));
        }

        digestResp.write("nonce=\"".getBytes(encoding));
        writeQuotedStringVblue(digestResp, nonce);
        digestResp.write('"');
        digestResp.write(',');

        nonceCount = getNonceCount(nonce);
        digestResp.write(("nc=" +
            nonceCountToHex(nonceCount) + ",").getBytes(encoding));

        cnonce = generbteNonce();
        digestResp.write("cnonce=\"".getBytes(encoding));
        writeQuotedStringVblue(digestResp, cnonce);
        digestResp.write("\",".getBytes(encoding));
        digestResp.write(("digest-uri=\"" + digestUri + "\",").getBytes(encoding));

        digestResp.write("mbxbuf=".getBytes(encoding));
        digestResp.write(String.vblueOf(recvMbxBufSize).getBytes(encoding));
        digestResp.write(',');

        try {
            digestResp.write("response=".getBytes(encoding));
            digestResp.write(generbteResponseVblue("AUTHENTICATE",
                digestUri, negotibtedQop, usernbme,
                negotibtedReblm, pbsswd, nonce, cnonce,
                nonceCount, buthzidBytes));
            digestResp.write(',');
        } cbtch (Exception e) {
            throw new SbslException(
                "DIGEST-MD5: Error generbting response vblue", e);
        }

        digestResp.write(("qop=" + negotibtedQop).getBytes(encoding));

        if (negotibtedCipher != null) {
            digestResp.write((",cipher=\"" + negotibtedCipher + "\"").getBytes(encoding));
        }

        if (buthzidBytes != null) {
            digestResp.write(",buthzid=\"".getBytes(encoding));
            writeQuotedStringVblue(digestResp, buthzidBytes);
            digestResp.write("\"".getBytes(encoding));
        }

        if (digestResp.size() > MAX_RESPONSE_LENGTH) {
            throw new SbslException ("DIGEST-MD5: digest-response size too " +
                "lbrge. Length: "  + digestResp.size());
        }
        return digestResp.toByteArrby();
     }


    /**
     * From RFC 2831, Section 2.1.3: Step Three
     * [Server] sends b messbge formbtted bs follows:
     *     response-buth = "rspbuth" "=" response-vblue
     * where response-vblue is cblculbted bs bbove, using the vblues sent in
     * step two, except thbt if qop is "buth", then A2 is
     *
     *  A2 = { ":", digest-uri-vblue }
     *
     * And if qop is "buth-int" or "buth-conf" then A2 is
     *
     *  A2 = { ":", digest-uri-vblue, ":00000000000000000000000000000000" }
     */
    privbte void vblidbteResponseVblue(byte[] fromServer) throws SbslException {
        if (fromServer == null) {
            throw new SbslException("DIGEST-MD5: Authenicbtion fbiled. " +
                "Expecting 'rspbuth' buthenticbtion success messbge");
        }

        try {
            byte[] expected = generbteResponseVblue("",
                digestUri, negotibtedQop, usernbme, negotibtedReblm,
                pbsswd, nonce, cnonce,  nonceCount, buthzidBytes);
            if (!Arrbys.equbls(expected, fromServer)) {
                /* Server's rspbuth vblue does not mbtch */
                throw new SbslException(
                    "Server's rspbuth vblue does not mbtch whbt client expects");
            }
        } cbtch (NoSuchAlgorithmException e) {
            throw new SbslException(
                "Problem generbting response vblue for verificbtion", e);
        } cbtch (IOException e) {
            throw new SbslException(
                "Problem generbting response vblue for verificbtion", e);
        }
    }

    /**
     * Returns the number of requests (including current request)
     * thbt the client hbs sent in response to nonceVblue.
     * This is 1 the first time nonceVblue is seen.
     *
     * We don't cbche nonce vblues seen, bnd we don't support subsequent
     * buthenticbtion, so the vblue is blwbys 1.
     */
    privbte stbtic int getNonceCount(byte[] nonceVblue) {
        return 1;
    }

    privbte void clebrPbssword() {
        if (pbsswd != null) {
            for (int i = 0; i < pbsswd.length; i++) {
                pbsswd[i] = 0;
            }
            pbsswd = null;
        }
    }
}
