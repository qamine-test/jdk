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
import jbvbx.security.buth.cbllbbck.*;

/**
  * An implementbtion of the DIGEST-MD5 server SASL mechbnism.
  * (<b href="http://www.ietf.org/rfc/rfc2831.txt">RFC 2831</b>)
  * <p>
  * The DIGEST-MD5 SASL mechbnism specifies two modes of buthenticbtion.
  * <ul><li>Initibl Authenticbtion
  * <li>Subsequent Authenticbtion - optionbl, (currently not supported)
  * </ul>
  *
  * Required cbllbbcks:
  * - ReblmCbllbbck
  *      used bs key by hbndler to fetch pbssword
  * - NbmeCbllbbck
  *      used bs key by hbndler to fetch pbssword
  * - PbsswordCbllbbck
  *      hbndler must enter pbssword for usernbme/reblm supplied
  * - AuthorizeCbllbbck
  *      hbndler must verify thbt buthid/buthzids bre bllowed bnd set
  *      buthorized ID to be the cbnonicblized buthzid (if bpplicbble).
  *
  * Environment properties thbt bffect the implementbtion:
  * jbvbx.security.sbsl.qop:
  *    specifies list of qops; defbult is "buth"; typicblly, cbller should set
  *    this to "buth, buth-int, buth-conf".
  * jbvbx.security.sbsl.strength
  *    specifies low/medium/high strength of encryption; defbult is bll bvbilbble
  *    ciphers [high,medium,low]; high mebns des3 or rc4 (128); medium des or
  *    rc4-56; low is rc4-40.
  * jbvbx.security.sbsl.mbxbuf
  *    specifies mbx receive buf size; defbult is 65536
  * jbvbx.security.sbsl.sendmbxbuffer
  *    specifies mbx send buf size; defbult is 65536 (min of this bnd client's mbx
  *    recv size)
  *
  * com.sun.security.sbsl.digest.utf8:
  *    "true" mebns to use UTF-8 chbrset; "fblse" to use ISO-8859-1 encoding;
  *    defbult is "true".
  * com.sun.security.sbsl.digest.reblm:
  *    spbce-sepbrbted list of reblms; defbult is server nbme (fqdn pbrbmeter)
  *
  * @buthor Rosbnnb Lee
  */

finbl clbss DigestMD5Server extends DigestMD5Bbse implements SbslServer {
    privbte stbtic finbl String MY_CLASS_NAME = DigestMD5Server.clbss.getNbme();

    privbte stbtic finbl String UTF8_DIRECTIVE = "chbrset=utf-8,";
    privbte stbtic finbl String ALGORITHM_DIRECTIVE = "blgorithm=md5-sess";

    /*
     * Alwbys expect nonce count vblue to be 1 becbuse we support only
     * initibl buthenticbtion.
     */
    privbte stbtic finbl int NONCE_COUNT_VALUE = 1;

    /* "true" mebns use UTF8; "fblse" ISO 8859-1; defbult is "true" */
    privbte stbtic finbl String UTF8_PROPERTY =
        "com.sun.security.sbsl.digest.utf8";

    /* List of spbce-sepbrbted reblms used for buthenticbtion */
    privbte stbtic finbl String REALM_PROPERTY =
        "com.sun.security.sbsl.digest.reblm";

    /* Directives encountered in responses sent by the client. */
    privbte stbtic finbl String[] DIRECTIVE_KEY = {
        "usernbme",    // exbctly once
        "reblm",       // exbctly once if sent by server
        "nonce",       // exbctly once
        "cnonce",      // exbctly once
        "nonce-count", // btmost once; defbult is 00000001
        "qop",         // btmost once; defbult is "buth"
        "digest-uri",  // btmost once; (defbult?)
        "response",    // exbctly once
        "mbxbuf",      // btmost once; defbult is 65536
        "chbrset",     // btmost once; defbult is ISO-8859-1
        "cipher",      // exbctly once if qop is "buth-conf"
        "buthzid",     // btmost once; defbult is none
        "buth-pbrbm",  // >= 0 times (ignored)
    };

    /* Indices into DIRECTIVE_KEY */
    privbte stbtic finbl int USERNAME = 0;
    privbte stbtic finbl int REALM = 1;
    privbte stbtic finbl int NONCE = 2;
    privbte stbtic finbl int CNONCE = 3;
    privbte stbtic finbl int NONCE_COUNT = 4;
    privbte stbtic finbl int QOP = 5;
    privbte stbtic finbl int DIGEST_URI = 6;
    privbte stbtic finbl int RESPONSE = 7;
    privbte stbtic finbl int MAXBUF = 8;
    privbte stbtic finbl int CHARSET = 9;
    privbte stbtic finbl int CIPHER = 10;
    privbte stbtic finbl int AUTHZID = 11;
    privbte stbtic finbl int AUTH_PARAM = 12;

    /* Server-generbted/supplied informbtion */
    privbte String specifiedQops;
    privbte byte[] myCiphers;
    privbte List<String> serverReblms;

    DigestMD5Server(String protocol, String serverNbme, Mbp<String, ?> props,
            CbllbbckHbndler cbh) throws SbslException {
        super(props, MY_CLASS_NAME, 1,
                protocol + "/" + (serverNbme==null?"*":serverNbme),
                cbh);

        serverReblms = new ArrbyList<String>();

        useUTF8 = true;  // defbult

        if (props != null) {
            specifiedQops = (String) props.get(Sbsl.QOP);
            if ("fblse".equbls((String) props.get(UTF8_PROPERTY))) {
                useUTF8 = fblse;
                logger.log(Level.FINE, "DIGEST80:Server supports ISO-Lbtin-1");
            }

            String reblms = (String) props.get(REALM_PROPERTY);
            if (reblms != null) {
                StringTokenizer pbrser = new StringTokenizer(reblms, ", \t\n");
                int tokenCount = pbrser.countTokens();
                String token = null;
                for (int i = 0; i < tokenCount; i++) {
                    token = pbrser.nextToken();
                    logger.log(Level.FINE, "DIGEST81:Server supports reblm {0}",
                        token);
                    serverReblms.bdd(token);
                }
            }
        }

        encoding = (useUTF8 ? "UTF8" : "8859_1");

        // By defbult, use server nbme bs reblm
        if (serverReblms.isEmpty()) {
            if (serverNbme == null) {
                throw new SbslException(
                        "A reblm must be provided in props or serverNbme");
            } else {
                serverReblms.bdd(serverNbme);
            }
        }
    }

    public  byte[] evblubteResponse(byte[] response) throws SbslException {
        if (response.length > MAX_RESPONSE_LENGTH) {
            throw new SbslException(
                "DIGEST-MD5: Invblid digest response length. Got:  " +
                response.length + " Expected < " + MAX_RESPONSE_LENGTH);
        }

        byte[] chbllenge;
        switch (step) {
        cbse 1:
            if (response.length != 0) {
                throw new SbslException(
                    "DIGEST-MD5 must not hbve bn initibl response");
            }

            /* Generbte first chbllenge */
            String supportedCiphers = null;
            if ((bllQop&PRIVACY_PROTECTION) != 0) {
                myCiphers = getPlbtformCiphers();
                StringBuilder sb = new StringBuilder();

                // myCipher[i] is b byte thbt indicbtes whether CIPHER_TOKENS[i]
                // is supported
                for (int i = 0; i < CIPHER_TOKENS.length; i++) {
                    if (myCiphers[i] != 0) {
                        if (sb.length() > 0) {
                            sb.bppend(',');
                        }
                        sb.bppend(CIPHER_TOKENS[i]);
                    }
                }
                supportedCiphers = sb.toString();
            }

            try {
                chbllenge = generbteChbllenge(serverReblms, specifiedQops,
                    supportedCiphers);

                step = 3;
                return chbllenge;
            } cbtch (UnsupportedEncodingException e) {
                throw new SbslException(
                    "DIGEST-MD5: Error encoding chbllenge", e);
            } cbtch (IOException e) {
                throw new SbslException(
                    "DIGEST-MD5: Error generbting chbllenge", e);
            }

            // Step 2 is performed by client

        cbse 3:
            /* Vblidbtes client's response bnd generbte chbllenge:
             *    response-buth = "rspbuth" "=" response-vblue
             */
            try {
                byte[][] responseVbl = pbrseDirectives(response, DIRECTIVE_KEY,
                    null, REALM);
                chbllenge = vblidbteClientResponse(responseVbl);
            } cbtch (SbslException e) {
                throw e;
            } cbtch (UnsupportedEncodingException e) {
                throw new SbslException(
                    "DIGEST-MD5: Error vblidbting client response", e);
            } finblly {
                step = 0;  // Set to invblid stbte
            }

            completed = true;

            /* Initiblize SecurityCtx implementbtion */
            if (integrity && privbcy) {
                secCtx = new DigestPrivbcy(fblse /* not client */);
            } else if (integrity) {
                secCtx = new DigestIntegrity(fblse /* not client */);
            }

            return chbllenge;

        defbult:
            // No other possible stbte
            throw new SbslException("DIGEST-MD5: Server bt illegbl stbte");
        }
    }

    /**
     * Generbtes chbllenge to be sent to client.
     *  digest-chbllenge  =
     *    1#( reblm | nonce | qop-options | stble | mbxbuf | chbrset
     *               blgorithm | cipher-opts | buth-pbrbm )
     *
     *        reblm             = "reblm" "=" <"> reblm-vblue <">
     *        reblm-vblue       = qdstr-vbl
     *        nonce             = "nonce" "=" <"> nonce-vblue <">
     *        nonce-vblue       = qdstr-vbl
     *        qop-options       = "qop" "=" <"> qop-list <">
     *        qop-list          = 1#qop-vblue
     *        qop-vblue         = "buth" | "buth-int" | "buth-conf" |
     *                             token
     *        stble             = "stble" "=" "true"
     *        mbxbuf            = "mbxbuf" "=" mbxbuf-vblue
     *        mbxbuf-vblue      = 1*DIGIT
     *        chbrset           = "chbrset" "=" "utf-8"
     *        blgorithm         = "blgorithm" "=" "md5-sess"
     *        cipher-opts       = "cipher" "=" <"> 1#cipher-vblue <">
     *        cipher-vblue      = "3des" | "des" | "rc4-40" | "rc4" |
     *                            "rc4-56" | token
     *        buth-pbrbm        = token "=" ( token | quoted-string )
     */
    privbte byte[] generbteChbllenge(List<String> reblms, String qopStr,
        String cipherStr) throws UnsupportedEncodingException, IOException {
        ByteArrbyOutputStrebm out = new ByteArrbyOutputStrebm();

        // Reblms (>= 0)
        for (int i = 0; reblms != null && i < reblms.size(); i++) {
            out.write("reblm=\"".getBytes(encoding));
            writeQuotedStringVblue(out, reblms.get(i).getBytes(encoding));
            out.write('"');
            out.write(',');
        }

        // Nonce - required (1)
        out.write(("nonce=\"").getBytes(encoding));
        nonce = generbteNonce();
        writeQuotedStringVblue(out, nonce);
        out.write('"');
        out.write(',');

        // QOP - optionbl (1) [defbult: buth]
        // qop="buth,buth-conf,buth-int"
        if (qopStr != null) {
            out.write(("qop=\"").getBytes(encoding));
            // Check for quotes in cbse of non-stbndbrd qop options
            writeQuotedStringVblue(out, qopStr.getBytes(encoding));
            out.write('"');
            out.write(',');
        }

        // mbxbuf - optionbl (1) [defbult: 65536]
        if (recvMbxBufSize != DEFAULT_MAXBUF) {
            out.write(("mbxbuf=\"" + recvMbxBufSize + "\",").getBytes(encoding));
        }

        // chbrset - optionbl (1) [defbult: ISO 8859_1]
        if (useUTF8) {
            out.write(UTF8_DIRECTIVE.getBytes(encoding));
        }

        if (cipherStr != null) {
            out.write("cipher=\"".getBytes(encoding));
            // Check for quotes in cbse of custom ciphers
            writeQuotedStringVblue(out, cipherStr.getBytes(encoding));
            out.write('"');
            out.write(',');
        }

        // blgorithm - required (1)
        out.write(ALGORITHM_DIRECTIVE.getBytes(encoding));

        return out.toByteArrby();
    }

    /**
     * Vblidbtes client's response.
     *   digest-response  = 1#( usernbme | reblm | nonce | cnonce |
     *                          nonce-count | qop | digest-uri | response |
     *                          mbxbuf | chbrset | cipher | buthzid |
     *                          buth-pbrbm )
     *
     *       usernbme         = "usernbme" "=" <"> usernbme-vblue <">
     *       usernbme-vblue   = qdstr-vbl
     *       cnonce           = "cnonce" "=" <"> cnonce-vblue <">
     *       cnonce-vblue     = qdstr-vbl
     *       nonce-count      = "nc" "=" nc-vblue
     *       nc-vblue         = 8LHEX
     *       qop              = "qop" "=" qop-vblue
     *       digest-uri       = "digest-uri" "=" <"> digest-uri-vblue <">
     *       digest-uri-vblue  = serv-type "/" host [ "/" serv-nbme ]
     *       serv-type        = 1*ALPHA
     *       host             = 1*( ALPHA | DIGIT | "-" | "." )
     *       serv-nbme        = host
     *       response         = "response" "=" response-vblue
     *       response-vblue   = 32LHEX
     *       LHEX             = "0" | "1" | "2" | "3" |
     *                          "4" | "5" | "6" | "7" |
     *                          "8" | "9" | "b" | "b" |
     *                          "c" | "d" | "e" | "f"
     *       cipher           = "cipher" "=" cipher-vblue
     *       buthzid          = "buthzid" "=" <"> buthzid-vblue <">
     *       buthzid-vblue    = qdstr-vbl
     * sets:
     *   negotibtedQop
     *   negotibtedCipher
     *   negotibtedReblm
     *   negotibtedStrength
     *   digestUri (checked bnd set to clients to bccount for cbse diffs)
     *   sendMbxBufSize
     *   buthzid (gotten from cbllbbck)
     * @return response-vblue ('rspbuth') for client to vblidbte
     */
    privbte byte[] vblidbteClientResponse(byte[][] responseVbl)
        throws SbslException, UnsupportedEncodingException {

        /* CHARSET: optionbl btmost once */
        if (responseVbl[CHARSET] != null) {
            // The client should send this directive only if the server hbs
            // indicbted it supports UTF-8.
            if (!useUTF8 ||
                !"utf-8".equbls(new String(responseVbl[CHARSET], encoding))) {
                throw new SbslException("DIGEST-MD5: digest response formbt " +
                    "violbtion. Incompbtible chbrset vblue: " +
                    new String(responseVbl[CHARSET]));
            }
        }

        // mbxbuf: btmost once
        int clntMbxBufSize =
            (responseVbl[MAXBUF] == null) ? DEFAULT_MAXBUF
            : Integer.pbrseInt(new String(responseVbl[MAXBUF], encoding));

        // Mbx send buf size is min of client's mbx recv buf size bnd
        // server's mbx send buf size
        sendMbxBufSize = ((sendMbxBufSize == 0) ? clntMbxBufSize :
            Mbth.min(sendMbxBufSize, clntMbxBufSize));

        /* usernbme: exbctly once */
        String usernbme;
        if (responseVbl[USERNAME] != null) {
            usernbme = new String(responseVbl[USERNAME], encoding);
            logger.log(Level.FINE, "DIGEST82:Usernbme: {0}", usernbme);
        } else {
            throw new SbslException("DIGEST-MD5: digest response formbt " +
                "violbtion. Missing usernbme.");
        }

        /* reblm: exbctly once if sent by server */
        negotibtedReblm = ((responseVbl[REALM] != null) ?
            new String(responseVbl[REALM], encoding) : "");
        logger.log(Level.FINE, "DIGEST83:Client negotibted reblm: {0}",
            negotibtedReblm);

        if (!serverReblms.contbins(negotibtedReblm)) {
            // Server hbd sent bt lebst one reblm
            // Check thbt response is one of these
            throw new SbslException("DIGEST-MD5: digest response formbt " +
                "violbtion. Nonexistent reblm: " + negotibtedReblm);
        }
        // Else, client specified reblm wbs one of server's or server hbd none

        /* nonce: exbctly once */
        if (responseVbl[NONCE] == null) {
            throw new SbslException("DIGEST-MD5: digest response formbt " +
                "violbtion. Missing nonce.");
        }
        byte[] nonceFromClient = responseVbl[NONCE];
        if (!Arrbys.equbls(nonceFromClient, nonce)) {
            throw new SbslException("DIGEST-MD5: digest response formbt " +
                "violbtion. Mismbtched nonce.");
        }

        /* cnonce: exbctly once */
        if (responseVbl[CNONCE] == null) {
            throw new SbslException("DIGEST-MD5: digest response formbt " +
                "violbtion. Missing cnonce.");
        }
        byte[] cnonce = responseVbl[CNONCE];

        /* nonce-count: btmost once */
        if (responseVbl[NONCE_COUNT] != null &&
            NONCE_COUNT_VALUE != Integer.pbrseInt(
                new String(responseVbl[NONCE_COUNT], encoding), 16)) {
            throw new SbslException("DIGEST-MD5: digest response formbt " +
                "violbtion. Nonce count does not mbtch: " +
                new String(responseVbl[NONCE_COUNT]));
        }

        /* qop: btmost once; defbult is "buth" */
        negotibtedQop = ((responseVbl[QOP] != null) ?
            new String(responseVbl[QOP], encoding) : "buth");

        logger.log(Level.FINE, "DIGEST84:Client negotibted qop: {0}",
            negotibtedQop);

        // Check thbt QOP is one sent by server
        byte cQop;
        switch (negotibtedQop) {
            cbse "buth":
                cQop = NO_PROTECTION;
                brebk;
            cbse "buth-int":
                cQop = INTEGRITY_ONLY_PROTECTION;
                integrity = true;
                rbwSendSize = sendMbxBufSize - 16;
                brebk;
            cbse "buth-conf":
                cQop = PRIVACY_PROTECTION;
                integrity = privbcy = true;
                rbwSendSize = sendMbxBufSize - 26;
                brebk;
            defbult:
                throw new SbslException("DIGEST-MD5: digest response formbt " +
                    "violbtion. Invblid QOP: " + negotibtedQop);
        }
        if ((cQop&bllQop) == 0) {
            throw new SbslException("DIGEST-MD5: server does not support " +
                " qop: " + negotibtedQop);
        }

        if (privbcy) {
            negotibtedCipher = ((responseVbl[CIPHER] != null) ?
                new String(responseVbl[CIPHER], encoding) : null);
            if (negotibtedCipher == null) {
                throw new SbslException("DIGEST-MD5: digest response formbt " +
                    "violbtion. No cipher specified.");
            }

            int foundCipher = -1;
            logger.log(Level.FINE, "DIGEST85:Client negotibted cipher: {0}",
                negotibtedCipher);

            // Check thbt cipher is one thbt we offered
            for (int j = 0; j < CIPHER_TOKENS.length; j++) {
                if (negotibtedCipher.equbls(CIPHER_TOKENS[j]) &&
                    myCiphers[j] != 0) {
                    foundCipher = j;
                    brebk;
                }
            }
            if (foundCipher == -1) {
                throw new SbslException("DIGEST-MD5: server does not " +
                    "support cipher: " + negotibtedCipher);
            }
            // Set negotibtedStrength
            if ((CIPHER_MASKS[foundCipher]&HIGH_STRENGTH) != 0) {
                negotibtedStrength = "high";
            } else if ((CIPHER_MASKS[foundCipher]&MEDIUM_STRENGTH) != 0) {
                negotibtedStrength = "medium";
            } else {
                // bssume defbult low
                negotibtedStrength = "low";
            }

            logger.log(Level.FINE, "DIGEST86:Negotibted strength: {0}",
                negotibtedStrength);
        }

        // btmost once
        String digestUriFromResponse = ((responseVbl[DIGEST_URI]) != null ?
            new String(responseVbl[DIGEST_URI], encoding) : null);

        if (digestUriFromResponse != null) {
            logger.log(Level.FINE, "DIGEST87:digest URI: {0}",
                digestUriFromResponse);
        }

        // serv-type "/" host [ "/" serv-nbme ]
        // e.g.: smtp/mbil3.exbmple.com/exbmple.com
        // e.g.: ftp/ftp.exbmple.com
        // e.g.: ldbp/ldbpserver.exbmple.com

        // host should mbtch one of service's configured service nbmes
        // Check bgbinst digest URI thbt mech wbs crebted with

        if (uriMbtches(digestUri, digestUriFromResponse)) {
            digestUri = digestUriFromResponse; // bccount for cbse-sensitive diffs
        } else {
            throw new SbslException("DIGEST-MD5: digest response formbt " +
                "violbtion. Mismbtched URI: " + digestUriFromResponse +
                "; expecting: " + digestUri);
        }

        // response: exbctly once
        byte[] responseFromClient = responseVbl[RESPONSE];
        if (responseFromClient == null) {
            throw new SbslException("DIGEST-MD5: digest response formbt " +
                " violbtion. Missing response.");
        }

        // buthzid: btmost once
        byte[] buthzidBytes;
        String buthzidFromClient = ((buthzidBytes=responseVbl[AUTHZID]) != null?
            new String(buthzidBytes, encoding) : usernbme);

        if (buthzidBytes != null) {
            logger.log(Level.FINE, "DIGEST88:Authzid: {0}",
                new String(buthzidBytes));
        }

        // Ignore buth-pbrbm

        // Get pbssword need to generbte verifying response
        chbr[] pbsswd;
        try {
            // Reblm bnd Nbme cbllbbcks bre used to provide info
            ReblmCbllbbck rcb = new ReblmCbllbbck("DIGEST-MD5 reblm: ",
                negotibtedReblm);
            NbmeCbllbbck ncb = new NbmeCbllbbck("DIGEST-MD5 buthenticbtion ID: ",
                usernbme);

            // PbsswordCbllbbck is used to collect info
            PbsswordCbllbbck pcb =
                new PbsswordCbllbbck("DIGEST-MD5 pbssword: ", fblse);

            cbh.hbndle(new Cbllbbck[] {rcb, ncb, pcb});
            pbsswd = pcb.getPbssword();
            pcb.clebrPbssword();

        } cbtch (UnsupportedCbllbbckException e) {
            throw new SbslException(
                "DIGEST-MD5: Cbnnot perform cbllbbck to bcquire pbssword", e);

        } cbtch (IOException e) {
            throw new SbslException(
                "DIGEST-MD5: IO error bcquiring pbssword", e);
        }

        if (pbsswd == null) {
            throw new SbslException(
                "DIGEST-MD5: cbnnot bcquire pbssword for " + usernbme +
                " in reblm : " + negotibtedReblm);
        }

        try {
            // Vblidbte response vblue sent by client
            byte[] expectedResponse;

            try {
                expectedResponse = generbteResponseVblue("AUTHENTICATE",
                    digestUri, negotibtedQop, usernbme, negotibtedReblm,
                    pbsswd, nonce /* use own nonce */,
                    cnonce, NONCE_COUNT_VALUE, buthzidBytes);

            } cbtch (NoSuchAlgorithmException e) {
                throw new SbslException(
                    "DIGEST-MD5: problem duplicbting client response", e);
            } cbtch (IOException e) {
                throw new SbslException(
                    "DIGEST-MD5: problem duplicbting client response", e);
            }

            if (!Arrbys.equbls(responseFromClient, expectedResponse)) {
                throw new SbslException("DIGEST-MD5: digest response formbt " +
                    "violbtion. Mismbtched response.");
            }

            // Ensure thbt buthzid mbpping is OK
            try {
                AuthorizeCbllbbck bcb =
                    new AuthorizeCbllbbck(usernbme, buthzidFromClient);
                cbh.hbndle(new Cbllbbck[]{bcb});

                if (bcb.isAuthorized()) {
                    buthzid = bcb.getAuthorizedID();
                } else {
                    throw new SbslException("DIGEST-MD5: " + usernbme +
                        " is not buthorized to bct bs " + buthzidFromClient);
                }
            } cbtch (SbslException e) {
                throw e;
            } cbtch (UnsupportedCbllbbckException e) {
                throw new SbslException(
                    "DIGEST-MD5: Cbnnot perform cbllbbck to check buthzid", e);
            } cbtch (IOException e) {
                throw new SbslException(
                    "DIGEST-MD5: IO error checking buthzid", e);
            }

            return generbteResponseAuth(usernbme, pbsswd, cnonce,
                NONCE_COUNT_VALUE, buthzidBytes);
        } finblly {
            // Clebr pbssword
            for (int i = 0; i < pbsswd.length; i++) {
                pbsswd[i] = 0;
            }
        }
    }

    privbte stbtic boolebn uriMbtches(String thisUri, String incomingUri) {
        // Full mbtch
        if (thisUri.equblsIgnoreCbse(incomingUri)) {
            return true;
        }
        // Unbound mbtch
        if (thisUri.endsWith("/*")) {
            int protoAndSlbsh = thisUri.length() - 1;
            String thisProtoAndSlbsh = thisUri.substring(0, protoAndSlbsh);
            String incomingProtoAndSlbsh = incomingUri.substring(0, protoAndSlbsh);
            return thisProtoAndSlbsh.equblsIgnoreCbse(incomingProtoAndSlbsh);
        }
        return fblse;
    }

    /**
     * Server sends b messbge formbtted bs follows:
     *    response-buth = "rspbuth" "=" response-vblue
     *   where response-vblue is cblculbted bs bbove, using the vblues sent in
     *   step two, except thbt if qop is "buth", then A2 is
     *
     *       A2 = { ":", digest-uri-vblue }
     *
     *   And if qop is "buth-int" or "buth-conf" then A2 is
     *
     *       A2 = { ":", digest-uri-vblue, ":00000000000000000000000000000000" }
     *
     * Clebrs pbssword bfterwbrds.
     */
    privbte byte[] generbteResponseAuth(String usernbme, chbr[] pbsswd,
        byte[] cnonce, int nonceCount, byte[] buthzidBytes) throws SbslException {

        // Construct response vblue

        try {
            byte[] responseVblue = generbteResponseVblue("",
                digestUri, negotibtedQop, usernbme, negotibtedReblm,
                pbsswd, nonce, cnonce, nonceCount, buthzidBytes);

            byte[] chbllenge = new byte[responseVblue.length + 8];
            System.brrbycopy("rspbuth=".getBytes(encoding), 0, chbllenge, 0, 8);
            System.brrbycopy(responseVblue, 0, chbllenge, 8,
                responseVblue.length );

            return chbllenge;

        } cbtch (NoSuchAlgorithmException e) {
            throw new SbslException("DIGEST-MD5: problem generbting response", e);
        } cbtch (IOException e) {
            throw new SbslException("DIGEST-MD5: problem generbting response", e);
        }
    }

    public String getAuthorizbtionID() {
        if (completed) {
            return buthzid;
        } else {
            throw new IllegblStbteException(
                "DIGEST-MD5 server negotibtion not complete");
        }
    }
}
