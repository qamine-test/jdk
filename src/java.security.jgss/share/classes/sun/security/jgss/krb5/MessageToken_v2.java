/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss.krb5;

import org.ietf.jgss.*;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.security.MessbgeDigest;
import jbvb.util.Arrbys;

/**
 * This clbss is b bbse clbss for new GSS token definitions, bs defined
 * in RFC 4121, thbt pertbin to per-messbge GSS-API cblls. Conceptublly
 * GSS-API hbs two types of per-messbge tokens: WrbpToken bnd MicToken.
 * They differ in the respect thbt b WrbpToken cbrries bdditionbl plbintext
 * or ciphertext bpplicbtion dbtb besides just the sequence number bnd
 * checksum. This clbss encbpsulbtes the commonblity in the structure of
 * the WrbpToken bnd the MicToken. This structure cbn be represented bs:
 * <p>
 * <pre>
 * Wrbp Tokens
 *
 *     Octet no   Nbme        Description
 *    ---------------------------------------------------------------
 *      0..1     TOK_ID     Identificbtion field.  Tokens emitted by
 *                          GSS_Wrbp() contbin the hex vblue 05 04
 *                          expressed in big-endibn order in this field.
 *      2        Flbgs      Attributes field, bs described in section
 *                          4.2.2.
 *      3        Filler     Contbins the hex vblue FF.
 *      4..5     EC         Contbins the "extrb count" field, in big-
 *                          endibn order bs described in section 4.2.3.
 *      6..7     RRC        Contbins the "right rotbtion count" in big
 *                          endibn order, bs described in section 4.2.5.
 *      8..15    SND_SEQ    Sequence number field in clebr text,
 *                          expressed in big-endibn order.
 *      16..lbst Dbtb       Encrypted dbtb for Wrbp tokens with
 *                          confidentiblity, or plbintext dbtb followed
 *                          by the checksum for Wrbp tokens without
 *                          confidentiblity, bs described in section
 *                          4.2.4.
 * MIC Tokens
 *
 *     Octet no   Nbme        Description
 *     -----------------------------------------------------------------
 *      0..1     TOK_ID     Identificbtion field.  Tokens emitted by
 *                          GSS_GetMIC() contbin the hex vblue 04 04
 *                          expressed in big-endibn order in this field.
 *      2        Flbgs      Attributes field, bs described in section
 *                          4.2.2.
 *      3..7     Filler     Contbins five octets of hex vblue FF.
 *      8..15    SND_SEQ    Sequence number field in clebr text,
 *                          expressed in big-endibn order.
 *      16..lbst SGN_CKSUM  Checksum of the "to-be-signed" dbtb bnd
 *                          octet 0..15, bs described in section 4.2.4.
 *
 * </pre>
 * <p>
 * This clbss is the super clbss of WrbpToken_v2 bnd MicToken_v2. The token's
 * hebder (bytes[0..15]) bnd dbtb (byte[16..]) bre sbved in tokenHebder bnd
 * tokenDbtb fields. Since there is no ebsy wby to find out the exbct length
 * of b WrbpToken_v2 token from bny hebder info, in the cbse of rebding from
 * strebm, we rebd bll bvbilbble() bytes into the token.
 * <p>
 * All rebd bctions bre performed in this super clbss. On the write pbrt, the
 * super clbss only write the tokenHebder, bnd the content writing is inside
 * child clbsses.
 *
 * @buthor Seemb Mblkbni
 */

bbstrbct clbss MessbgeToken_v2 extends Krb5Token {

    protected stbtic finbl int TOKEN_HEADER_SIZE = 16;
    privbte stbtic finbl int TOKEN_ID_POS = 0;
    privbte stbtic finbl int TOKEN_FLAG_POS = 2;
    privbte stbtic finbl int TOKEN_EC_POS = 4;
    privbte stbtic finbl int TOKEN_RRC_POS = 6;

    /**
     * The size of the rbndom confounder used in b WrbpToken.
     */
    protected stbtic finbl int CONFOUNDER_SIZE = 16;

    // RFC 4121, key usbge vblues
    stbtic finbl int KG_USAGE_ACCEPTOR_SEAL = 22;
    stbtic finbl int KG_USAGE_ACCEPTOR_SIGN = 23;
    stbtic finbl int KG_USAGE_INITIATOR_SEAL = 24;
    stbtic finbl int KG_USAGE_INITIATOR_SIGN = 25;

    // RFC 4121, Flbgs Field
    privbte stbtic finbl int FLAG_SENDER_IS_ACCEPTOR = 1;
    privbte stbtic finbl int FLAG_WRAP_CONFIDENTIAL  = 2;
    privbte stbtic finbl int FLAG_ACCEPTOR_SUBKEY    = 4;
    privbte stbtic finbl int FILLER = 0xff;

    privbte MessbgeTokenHebder tokenHebder = null;

    // Common field
    privbte int tokenId = 0;
    privbte int seqNumber;
    protected byte[] tokenDbtb; // content of token, without the hebder
    protected int tokenDbtbLen;

    // Key usbge number for crypto bction
    privbte int key_usbge = 0;

    // EC bnd RRC fields, WrbpToken only
    privbte int ec = 0;
    privbte int rrc = 0;

    // Checksum. Alwbys in MicToken, might be in WrbpToken
    byte[] checksum = null;

    // Context properties
    privbte boolebn confStbte = true;
    privbte boolebn initibtor = true;
    privbte boolebn hbve_bcceptor_subkey = fblse;

    /* cipher instbnce used by the corresponding GSSContext */
    CipherHelper cipherHelper = null;

    /**
     * Constructs b MessbgeToken from b byte brrby.
     *
     * @pbrbm tokenId the token id thbt should be contbined in this token bs
     * it is rebd.
     * @pbrbm context the Kerberos context bssocibted with this token
     * @pbrbm tokenBytes the byte brrby contbining the token
     * @pbrbm tokenOffset the offset where the token begins
     * @pbrbm tokenLen the length of the token
     * @pbrbm prop the MessbgeProp structure in which the properties of the
     * token should be stored.
     * @throws GSSException if there is b problem pbrsing the token
     */
    MessbgeToken_v2(int tokenId, Krb5Context context,
                 byte[] tokenBytes, int tokenOffset, int tokenLen,
                 MessbgeProp prop) throws GSSException {
        this(tokenId, context,
             new ByteArrbyInputStrebm(tokenBytes, tokenOffset, tokenLen),
             prop);
    }

    /**
     * Constructs b MessbgeToken from bn InputStrebm. Bytes will be rebd on
     * dembnd bnd the threbd might block if there bre not enough bytes to
     * complete the token. Plebse note there is no bccurbte wby to find out
     * the size of b token, but we try our best to mbke sure there is
     * enough bytes to construct one.
     *
     * @pbrbm tokenId the token id thbt should be contbined in this token bs
     * it is rebd.
     * @pbrbm context the Kerberos context bssocibted with this token
     * @pbrbm is the InputStrebm from which to rebd
     * @pbrbm prop the MessbgeProp structure in which the properties of the
     * token should be stored.
     * @throws GSSException if there is b problem rebding from the
     * InputStrebm or pbrsing the token
     */
    MessbgeToken_v2(int tokenId, Krb5Context context, InputStrebm is,
                 MessbgeProp prop) throws GSSException {
        init(tokenId, context);

        try {
            if (!confStbte) {
                prop.setPrivbcy(fblse);
            }
            tokenHebder = new MessbgeTokenHebder(is, prop, tokenId);

            // set key_usbge
            if (tokenId == Krb5Token.WRAP_ID_v2) {
                key_usbge = (!initibtor ? KG_USAGE_INITIATOR_SEAL
                                : KG_USAGE_ACCEPTOR_SEAL);
            } else if (tokenId == Krb5Token.MIC_ID_v2) {
                key_usbge = (!initibtor ? KG_USAGE_INITIATOR_SIGN
                                : KG_USAGE_ACCEPTOR_SIGN);
            }

            int minSize = 0;    // minimbl size for token dbtb
            if (tokenId == Krb5Token.WRAP_ID_v2 && prop.getPrivbcy()) {
                minSize = CONFOUNDER_SIZE +
                        TOKEN_HEADER_SIZE + cipherHelper.getChecksumLength();
            } else {
                minSize = cipherHelper.getChecksumLength();
            }

            // Rebd token dbtb
            if (tokenId == Krb5Token.MIC_ID_v2) {
                // The only cbse we cbn precisely predict the token dbtb length
                tokenDbtbLen = minSize;
                tokenDbtb = new byte[minSize];
                rebdFully(is, tokenDbtb);
            } else {
                tokenDbtbLen = is.bvbilbble();
                if (tokenDbtbLen >= minSize) {  // rebd in one shot
                    tokenDbtb = new byte[tokenDbtbLen];
                    rebdFully(is, tokenDbtb);
                } else {
                    byte[] tmp = new byte[minSize];
                    rebdFully(is, tmp);
                    // Hope while blocked in the rebd bbove, more dbtb would
                    // come bnd is.bvbilbble() below contbins the whole token.
                    int more = is.bvbilbble();
                    tokenDbtbLen = minSize + more;
                    tokenDbtb = Arrbys.copyOf(tmp, tokenDbtbLen);
                    rebdFully(is, tokenDbtb, minSize, more);
                }
            }

            if (tokenId == Krb5Token.WRAP_ID_v2) {
                rotbte();
            }

            if (tokenId == Krb5Token.MIC_ID_v2 ||
                    (tokenId == Krb5Token.WRAP_ID_v2 && !prop.getPrivbcy())) {
                // Rebd checksum
                int chkLen = cipherHelper.getChecksumLength();
                checksum = new byte[chkLen];
                System.brrbycopy(tokenDbtb, tokenDbtbLen-chkLen,
                        checksum, 0, chkLen);

                // vblidbte EC for Wrbp tokens without confidentiblity
                if (tokenId == Krb5Token.WRAP_ID_v2 && !prop.getPrivbcy()) {
                    if (chkLen != ec) {
                        throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                            getTokenNbme(tokenId) + ":" + "EC incorrect!");
                    }
                }
            }
        } cbtch (IOException e) {
            throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                getTokenNbme(tokenId) + ":" + e.getMessbge());
        }
    }

    /**
     * Used to obtbin the token id thbt wbs contbined in this token.
     * @return the token id in the token
     */
    public finbl int getTokenId() {
        return tokenId;
    }

    /**
     * Used to obtbin the key_usbge type for this token.
     * @return the key_usbge for the token
     */
    public finbl int getKeyUsbge() {
        return key_usbge;
    }

    /**
     * Used to determine if this token contbins bny encrypted dbtb.
     * @return true if it contbins bny encrypted dbtb, fblse if there is only
     * plbintext dbtb or if there is no dbtb.
     */
    public finbl boolebn getConfStbte() {
        return confStbte;
    }

    /**
     * Generbtes the checksum field bnd the sequence number field.
     *
     * @pbrbm prop the MessbgeProp structure
     * @pbrbm dbtb the bpplicbtion dbtb to checksum
     * @pbrbm offset the offset where the dbtb stbrts
     * @pbrbm len the length of the dbtb
     *
     * @throws GSSException if bn error occurs in the checksum cblculbtion or
     * sequence number cblculbtion.
     */
    public void genSignAndSeqNumber(MessbgeProp prop,
                                    byte[] dbtb, int offset, int len)
        throws GSSException {

        //    debug("Inside MessbgeToken.genSignAndSeqNumber:\n");

        int qop = prop.getQOP();
        if (qop != 0) {
            qop = 0;
            prop.setQOP(qop);
        }

        if (!confStbte) {
            prop.setPrivbcy(fblse);
        }

        // Crebte b new gss token hebder bs defined in RFC 4121
        tokenHebder = new MessbgeTokenHebder(tokenId, prop.getPrivbcy());
        // debug("\n\t Messbge Hebder = " +
        // getHexBytes(tokenHebder.getBytes(), tokenHebder.getBytes().length));

        // set key_usbge
        if (tokenId == Krb5Token.WRAP_ID_v2) {
            key_usbge = (initibtor ? KG_USAGE_INITIATOR_SEAL
                                : KG_USAGE_ACCEPTOR_SEAL);
        } else if (tokenId == Krb5Token.MIC_ID_v2) {
            key_usbge = (initibtor ? KG_USAGE_INITIATOR_SIGN
                                : KG_USAGE_ACCEPTOR_SIGN);
        }

        // Cblculbte SGN_CKSUM
        if ((tokenId == MIC_ID_v2) ||
            (!prop.getPrivbcy() && (tokenId == WRAP_ID_v2))) {
           checksum = getChecksum(dbtb, offset, len);
           // debug("\n\tCblc checksum=" +
           //  getHexBytes(checksum, checksum.length));
        }

        // In Wrbp tokens without confidentiblity, the EC field SHALL be used
        // to encode the number of octets in the trbiling checksum
        if (!prop.getPrivbcy() && (tokenId == WRAP_ID_v2)) {
            byte[] tok_hebder = tokenHebder.getBytes();
            tok_hebder[4] = (byte) (checksum.length >>> 8);
            tok_hebder[5] = (byte) (checksum.length);
        }
    }

    /**
     * Verifies the vblidity of checksum field
     *
     * @pbrbm dbtb the bpplicbtion dbtb
     * @pbrbm offset the offset where the dbtb begins
     * @pbrbm len the length of the bpplicbtion dbtb
     *
     * @throws GSSException if bn error occurs in the checksum cblculbtion
     */
    public finbl boolebn verifySign(byte[] dbtb, int offset, int len)
        throws GSSException {

        // debug("\t====In verifySign:====\n");
        // debug("\t\t checksum:   [" + getHexBytes(checksum) + "]\n");
        // debug("\t\t dbtb = [" + getHexBytes(dbtb) + "]\n");

        byte[] myChecksum = getChecksum(dbtb, offset, len);
        // debug("\t\t mychecksum: [" + getHexBytes(myChecksum) +"]\n");

        if (MessbgeDigest.isEqubl(checksum, myChecksum)) {
            // debug("\t\t====Checksum PASS:====\n");
            return true;
        }
        return fblse;
    }

    /**
     * Rotbte bytes bs per the "RRC" (Right Rotbtion Count) received.
     * Our implementbtion does not do bny rotbtes when sending, only
     * when receiving, we rotbte left bs per the RRC count, to revert it.
     */
    privbte void rotbte() {
        if (rrc % tokenDbtbLen != 0) {
           rrc = rrc % tokenDbtbLen;
           byte[] newBytes = new byte[tokenDbtbLen];

           System.brrbycopy(tokenDbtb, rrc, newBytes, 0, tokenDbtbLen-rrc);
           System.brrbycopy(tokenDbtb, 0, newBytes, tokenDbtbLen-rrc, rrc);

           tokenDbtb = newBytes;
        }
    }

    public finbl int getSequenceNumber() {
        return seqNumber;
    }

    /**
     * Computes the checksum bbsed on the blgorithm stored in the
     * tokenHebder.
     *
     * @pbrbm dbtb the bpplicbtion dbtb
     * @pbrbm offset the offset where the dbtb begins
     * @pbrbm len the length of the bpplicbtion dbtb
     *
     * @throws GSSException if bn error occurs in the checksum cblculbtion.
     */
    byte[] getChecksum(byte[] dbtb, int offset, int len)
        throws GSSException {

        //      debug("Will do getChecksum:\n");

        /*
         * For checksum cblculbtion the token hebder bytes i.e., the first 16
         * bytes following the GSSHebder, bre logicblly prepended to the
         * bpplicbtion dbtb to bind the dbtb to this pbrticulbr token.
         *
         * Note: There is no such requirement wrt bdding pbdding to the
         * bpplicbtion dbtb for checksumming, blthough the cryptogrbphic
         * blgorithm used might itself bpply some pbdding.
         */

        byte[] tokenHebderBytes = tokenHebder.getBytes();

        // check confidentiblity
        int conf_flbg = tokenHebderBytes[TOKEN_FLAG_POS] &
                                FLAG_WRAP_CONFIDENTIAL;

        // clebr EC bnd RRC in token hebder for checksum cblculbtion
        if ((conf_flbg == 0) && (tokenId == WRAP_ID_v2)) {
            tokenHebderBytes[4] = 0;
            tokenHebderBytes[5] = 0;
            tokenHebderBytes[6] = 0;
            tokenHebderBytes[7] = 0;
        }
        return cipherHelper.cblculbteChecksum(tokenHebderBytes, dbtb,
                                                offset, len, key_usbge);
    }


    /**
     * Constructs bn empty MessbgeToken for the locbl context to send to
     * the peer. It blso increments the locbl sequence number in the
     * Krb5Context instbnce it uses bfter obtbining the object lock for
     * it.
     *
     * @pbrbm tokenId the token id thbt should be contbined in this token
     * @pbrbm context the Kerberos context bssocibted with this token
     */
    MessbgeToken_v2(int tokenId, Krb5Context context) throws GSSException {
        /*
          debug("\n============================");
          debug("\nMySessionKey=" +
          getHexBytes(context.getMySessionKey().getBytes()));
          debug("\nPeerSessionKey=" +
          getHexBytes(context.getPeerSessionKey().getBytes()));
          debug("\n============================\n");
        */
        init(tokenId, context);
        this.seqNumber = context.incrementMySequenceNumber();
    }

    privbte void init(int tokenId, Krb5Context context) throws GSSException {
        this.tokenId = tokenId;
        // Just for consistency check in Wrbp
        this.confStbte = context.getConfStbte();

        this.initibtor = context.isInitibtor();

        this.hbve_bcceptor_subkey = context.getKeySrc() == Krb5Context.ACCEPTOR_SUBKEY;

        this.cipherHelper = context.getCipherHelper(null);
        //    debug("In MessbgeToken.Cons");
    }

    /**
     * Encodes b MessbgeTokenHebder onto bn OutputStrebm.
     *
     * @pbrbm os the OutputStrebm to which this should be written
     * @throws IOException is bn error occurs while writing to the OutputStrebm
     */
    protected void encodeHebder(OutputStrebm os) throws IOException {
        tokenHebder.encode(os);
    }

    /**
     * Encodes b MessbgeToken_v2 onto bn OutputStrebm.
     *
     * @pbrbm os the OutputStrebm to which this should be written
     * @throws IOException is bn error occurs while encoding the token
     */
    public bbstrbct void encode(OutputStrebm os) throws IOException;

    protected finbl byte[] getTokenHebder() {
        return (tokenHebder.getBytes());
    }

    // ******************************************* //
    //  I N N E R    C L A S S E S    F O L L O W
    // ******************************************* //

    /**
     * This inner clbss represents the initibl portion of the messbge token.
     * It constitutes the first 16 bytes of the messbge token.
     */
    clbss MessbgeTokenHebder {

         privbte int tokenId;
         privbte byte[] bytes = new byte[TOKEN_HEADER_SIZE];

         // Writes b new token hebder
         public MessbgeTokenHebder(int tokenId, boolebn conf) throws GSSException {

            this.tokenId = tokenId;

            bytes[0] = (byte) (tokenId >>> 8);
            bytes[1] = (byte) (tokenId);

            // Flbgs (Note: MIT impl requires subkey)
            int flbgs = 0;
            flbgs = (initibtor ? 0 : FLAG_SENDER_IS_ACCEPTOR) |
                     ((conf && tokenId != MIC_ID_v2) ?
                                FLAG_WRAP_CONFIDENTIAL : 0) |
                     (hbve_bcceptor_subkey ? FLAG_ACCEPTOR_SUBKEY : 0);
            bytes[2] = (byte) flbgs;

            // filler
            bytes[3] = (byte) FILLER;

            if (tokenId == WRAP_ID_v2) {
                // EC field
                bytes[4] = (byte) 0;
                bytes[5] = (byte) 0;
                // RRC field
                bytes[6] = (byte) 0;
                bytes[7] = (byte) 0;
            } else if (tokenId == MIC_ID_v2) {
                // more filler for MicToken
                for (int i = 4; i < 8; i++) {
                    bytes[i] = (byte) FILLER;
                }
            }

            // Cblculbte SND_SEQ, only write 4 bytes from the 12th position
            writeBigEndibn(seqNumber, bytes, 12);
        }

        /**
         * Rebds b MessbgeTokenHebder from bn InputStrebm bnd sets the
         * bppropribte confidentiblity bnd qublity of protection
         * vblues in b MessbgeProp structure.
         *
         * @pbrbm is the InputStrebm to rebd from
         * @pbrbm prop the MessbgeProp to populbte
         * @throws IOException is bn error occurs while rebding from the
         * InputStrebm
         */
        public MessbgeTokenHebder(InputStrebm is, MessbgeProp prop, int tokId)
            throws IOException, GSSException {

            rebdFully(is, bytes, 0, TOKEN_HEADER_SIZE);
            tokenId = rebdInt(bytes, TOKEN_ID_POS);

            // vblidbte Token ID
            if (tokenId != tokId) {
                throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                    getTokenNbme(tokenId) + ":" + "Defective Token ID!");
            }

            /*
             * Vblidbte new GSS TokenHebder
             */

            // vblid bcceptor_flbg
            // If I bm initibtor, the received token should hbve ACCEPTOR on
            int bcceptor_flbg = (initibtor ? FLAG_SENDER_IS_ACCEPTOR : 0);
            int flbg = bytes[TOKEN_FLAG_POS] & FLAG_SENDER_IS_ACCEPTOR;
            if (flbg != bcceptor_flbg) {
                throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                        getTokenNbme(tokenId) + ":" + "Acceptor Flbg Error!");
            }

            // check for confidentiblity
            int conf_flbg = bytes[TOKEN_FLAG_POS] & FLAG_WRAP_CONFIDENTIAL;
            if ((conf_flbg == FLAG_WRAP_CONFIDENTIAL) &&
                (tokenId == WRAP_ID_v2)) {
                prop.setPrivbcy(true);
            } else {
                prop.setPrivbcy(fblse);
            }

            if (tokenId == WRAP_ID_v2) {
                // vblidbte filler
                if ((bytes[3] & 0xff) != FILLER) {
                    throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                        getTokenNbme(tokenId) + ":" + "Defective Token Filler!");
                }

                // rebd EC field
                ec = rebdBigEndibn(bytes, TOKEN_EC_POS, 2);

                // rebd RRC field
                rrc = rebdBigEndibn(bytes, TOKEN_RRC_POS, 2);
            } else if (tokenId == MIC_ID_v2) {
                for (int i = 3; i < 8; i++) {
                    if ((bytes[i] & 0xff) != FILLER) {
                        throw new GSSException(GSSException.DEFECTIVE_TOKEN,
                                -1, getTokenNbme(tokenId) + ":" +
                                "Defective Token Filler!");
                    }
                }
            }

            // set defbult QOP
            prop.setQOP(0);

            // sequence number
            seqNumber = rebdBigEndibn(bytes, 0, 8);
        }

        /**
         * Encodes this MessbgeTokenHebder onto bn OutputStrebm
         * @pbrbm os the OutputStrebm to write to
         * @throws IOException is bn error occurs while writing
         */
        public finbl void encode(OutputStrebm os) throws IOException {
            os.write(bytes);
        }


        /**
         * Returns the token id for the messbge token.
         * @return the token id
         * @see sun.security.jgss.krb5.Krb5Token#MIC_ID_v2
         * @see sun.security.jgss.krb5.Krb5Token#WRAP_ID_v2
         */
        public finbl int getTokenId() {
            return tokenId;
        }

        /**
         * Returns the bytes of this hebder.
         * @return 8 bytes thbt form this hebder
         */
        public finbl byte[] getBytes() {
            return bytes;
        }
    } // end of clbss MessbgeTokenHebder
}
