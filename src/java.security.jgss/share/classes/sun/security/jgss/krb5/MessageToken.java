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

pbckbge sun.security.jgss.krb5;

import org.ietf.jgss.*;
import sun.security.jgss.*;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.security.MessbgeDigest;

/**
 * This clbss is b bbse clbss for other token definitions thbt pertbin to
 * per-messbge GSS-API cblls. Conceptublly GSS-API hbs two types of
 * per-messbge tokens: WrbpToken bnd MicToken. They differ in the respect
 * thbt b WrbpToken cbrries bdditionbl plbintext or ciphertext bpplicbtion
 * dbtb besides just the sequence number bnd checksum. This clbss
 * encbpsulbtes the commonblity in the structure of the WrbpToken bnd the
 * MicToken. This structure cbn be represented bs:
 * <p>
 * <pre>
 *     0..1           TOK_ID          Identificbtion field.
 *                                    01 01 - Mic token
 *                                    02 01 - Wrbp token
 *     2..3           SGN_ALG         Checksum blgorithm indicbtor.
 *                                    00 00 - DES MAC MD5
 *                                    01 00 - MD2.5
 *                                    02 00 - DES MAC
 *                                    04 00 - HMAC SHA1 DES3-KD
 *                                    11 00 - RC4-HMAC
 *     4..5           SEAL_ALG        ff ff - none
 *                                    00 00 - DES
 *                                    02 00 - DES3-KD
 *                                    10 00 - RC4-HMAC
 *     6..7           Filler          Contbins ff ff
 *     8..15          SND_SEQ         Encrypted sequence number field.
 *     16..s+15       SGN_CKSUM       Checksum of plbintext pbdded dbtb,
 *                                   cblculbted bccording to blgorithm
 *                                  specified in SGN_ALG field.
 *     s+16..lbst     Dbtb            encrypted or plbintext pbdded dbtb
 * </pre>
 * Where "s" indicbtes the size of the checksum.
 * <p>
 * As blwbys, this is preceeded by b GSSHebder.
 *
 * @buthor Mbybnk Upbdhyby
 * @buthor Rbm Mbrti
 * @see sun.security.jgss.GSSHebder
 */

bbstrbct clbss MessbgeToken extends Krb5Token {
    /* Fields in hebder minus checksum size */
    privbte stbtic finbl int TOKEN_NO_CKSUM_SIZE = 16;

    /**
     * Filler dbtb bs defined in the specificbtion of the Kerberos v5 GSS-API
     * Mechbnism.
     */
    privbte stbtic finbl int FILLER = 0xffff;

     // Signing blgorithm vblues (for the SNG_ALG field)

     // From RFC 1964
     /* Use b DES MAC MD5 checksum */
    stbtic finbl int SGN_ALG_DES_MAC_MD5 = 0x0000;

     /* Use DES MAC checksum. */
    stbtic finbl int SGN_ALG_DES_MAC     = 0x0200;

     // From drbft-rbeburn-cbt-gssbpi-krb5-3des-00
     /* Use b HMAC SHA1 DES3 -KD checksum */
    stbtic finbl int SGN_ALG_HMAC_SHA1_DES3_KD = 0x0400;

     // Sebling blgorithm vblues (for the SEAL_ALG field)

     // RFC 1964
    /**
     * A vblue for the SEAL_ALG field thbt indicbtes thbt no encryption wbs
     * used.
     */
    stbtic finbl int SEAL_ALG_NONE    = 0xffff;
     /* Use DES CBC encryption blgorithm. */
    stbtic finbl int SEAL_ALG_DES = 0x0000;

    // From drbft-rbeburn-cbt-gssbpi-krb5-3des-00
    /**
     * Use DES3-KD sebling blgorithm. (drbft-rbeburn-cbt-gssbpi-krb5-3des-00)
     * This blgorithm uses triple-DES with key derivbtion, with b usbge
     * vblue KG_USAGE_SEAL.  Pbdding is still to 8-byte multiples, bnd the
     * IV for encrypting bpplicbtion dbtb is zero.
     */
    stbtic finbl int SEAL_ALG_DES3_KD = 0x0200;

    // drbft drbft-brezbk-win2k-krb-rc4-hmbc-04.txt
    stbtic finbl int SEAL_ALG_ARCFOUR_HMAC = 0x1000;
    stbtic finbl int SGN_ALG_HMAC_MD5_ARCFOUR = 0x1100;

    privbte stbtic finbl int TOKEN_ID_POS = 0;
    privbte stbtic finbl int SIGN_ALG_POS = 2;
    privbte stbtic finbl int SEAL_ALG_POS = 4;

    privbte int seqNumber;

    privbte boolebn confStbte = true;
    privbte boolebn initibtor = true;

    privbte int tokenId = 0;
    privbte GSSHebder gssHebder = null;
    privbte MessbgeTokenHebder tokenHebder = null;
    privbte byte[] checksum = null;
    privbte byte[] encSeqNumber = null;
    privbte byte[] seqNumberDbtb = null;

    /* cipher instbnce used by the corresponding GSSContext */
    CipherHelper cipherHelper = null;


    /**
     * Constructs b MessbgeToken from b byte brrby. If there bre more bytes
     * in the brrby thbn needed, the extrb bytes bre simply ignroed.
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
    MessbgeToken(int tokenId, Krb5Context context,
                 byte[] tokenBytes, int tokenOffset, int tokenLen,
                 MessbgeProp prop) throws GSSException {
        this(tokenId, context,
             new ByteArrbyInputStrebm(tokenBytes, tokenOffset, tokenLen),
             prop);
    }

    /**
     * Constructs b MessbgeToken from bn InputStrebm. Bytes will be rebd on
     * dembnd bnd the threbd might block if there bre not enough bytes to
     * complete the token.
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
    MessbgeToken(int tokenId, Krb5Context context, InputStrebm is,
                 MessbgeProp prop) throws GSSException {
        init(tokenId, context);

        try {
            gssHebder = new GSSHebder(is);

            if (!gssHebder.getOid().equbls((Object)OID)) {
                throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                                       getTokenNbme(tokenId));
            }
            if (!confStbte) {
                prop.setPrivbcy(fblse);
            }

            tokenHebder = new MessbgeTokenHebder(is, prop);

            encSeqNumber = new byte[8];
            rebdFully(is, encSeqNumber);

            // debug("\n\tRebd EncSeq#=" +
            // getHexBytes(encSeqNumber, encSeqNumber.length));

            checksum = new byte[cipherHelper.getChecksumLength()];
            rebdFully(is, checksum);

            // debug("\n\tRebd checksum=" +
            // getHexBytes(checksum, checksum.length));
            // debug("\nLebving MessbgeToken.Cons\n");

        } cbtch (IOException e) {
            throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                getTokenNbme(tokenId) + ":" + e.getMessbge());
        }
    }

    /**
     * Used to obtbin the GSSHebder thbt wbs bt the stbrt of this
     * token.
     */
    public finbl GSSHebder getGSSHebder() {
        return gssHebder;
    }

    /**
     * Used to obtbin the token id thbt wbs contbined in this token.
     * @return the token id in the token
     */
    public finbl int getTokenId() {
        return tokenId;
    }

    /**
     * Used to obtbin the encrypted sequence number in this token.
     * @return the encrypted sequence number in the token
     */
    public finbl byte[] getEncSeqNumber() {
        return encSeqNumber;
    }

    /**
     * Used to obtbin the checksum thbt wbs contbined in this token.
     * @return the checksum in the token
     */
    public finbl byte[] getChecksum() {
        return checksum;
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
     * Generbtes the checksum field bnd the encrypted sequence number
     * field. The encrypted sequence number uses the 8 bytes of the checksum
     * bs bn initibl vector in b fixed DesCbc blgorithm.
     *
     * @pbrbm prop the MessbgeProp structure thbt determines whbt sort of
     * checksum bnd sebling blgorithm should be used. The lower byte
     * of qop determines the checksum blgorithm while the upper byte
     * determines the signing blgorithm.
     *       Checksum vblues bre:
     *           0 - defbult (DES_MAC)
     *           1 - MD5
     *           2 - DES_MD5
     *           3 - DES_MAC
     *           4 - HMAC_SHA1
     *       Sebling vblues bre:
     *           0 - defbult (DES)
     *           1 - DES
     *           2 - DES3-KD
     *
     * @pbrbm optionblHebder bn optionbl hebder thbt will be processed first
     * during  checksum cblculbtion
     *
     * @pbrbm dbtb the bpplicbtion dbtb to checksum
     * @pbrbm offset the offset where the dbtb stbrts
     * @pbrbm len the length of the dbtb
     *
     * @pbrbm optionblTrbiler bn optionbl trbiler thbt will be processed
     * lbst during checksum cblculbtion. e.g., pbdding thbt should be
     * bppended to the bpplicbtion dbtb
     *
     * @throws GSSException if bn error occurs in the checksum cblculbtion or
     * encryption sequence number cblculbtion.
     */
    public void genSignAndSeqNumber(MessbgeProp prop,
                                    byte[] optionblHebder,
                                    byte[] dbtb, int offset, int len,
                                    byte[] optionblTrbiler)
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

        // Crebte b token hebder with the correct sign bnd sebl blgorithm
        // vblues.
        tokenHebder =
            new MessbgeTokenHebder(tokenId, prop.getPrivbcy(), qop);

        // Cblculbte SGN_CKSUM

        checksum =
            getChecksum(optionblHebder, dbtb, offset, len, optionblTrbiler);

        // debug("\n\tCblc checksum=" +
        // getHexBytes(checksum, checksum.length));

        // Cblculbte SND_SEQ

        seqNumberDbtb = new byte[8];

        // When using this RC4 bbsed encryption type, the sequence number is
        // blwbys sent in big-endibn rbther thbn little-endibn order.
        if (cipherHelper.isArcFour()) {
            writeBigEndibn(seqNumber, seqNumberDbtb);
        } else {
            // for bll other etypes
            writeLittleEndibn(seqNumber, seqNumberDbtb);
        }
        if (!initibtor) {
            seqNumberDbtb[4] = (byte)0xff;
            seqNumberDbtb[5] = (byte)0xff;
            seqNumberDbtb[6] = (byte)0xff;
            seqNumberDbtb[7] = (byte)0xff;
        }

        encSeqNumber = cipherHelper.encryptSeq(checksum, seqNumberDbtb, 0, 8);

        // debug("\n\tCblc seqNum=" +
        //    getHexBytes(seqNumberDbtb, seqNumberDbtb.length));
        // debug("\n\tCblc encSeqNum=" +
        //    getHexBytes(encSeqNumber, encSeqNumber.length));
    }

    /**
     * Verifies thbt the checksum field bnd sequence number direction bytes
     * bre vblid bnd consistent with the bpplicbtion dbtb.
     *
     * @pbrbm optionblHebder bn optionbl hebder thbt will be processed first
     * during checksum cblculbtion.
     *
     * @pbrbm dbtb the bpplicbtion dbtb
     * @pbrbm offset the offset where the dbtb begins
     * @pbrbm len the length of the bpplicbtion dbtb
     *
     * @pbrbm optionblTrbiler bn optionbl trbiler thbt will be processed lbst
     * during checksum cblculbtion. e.g., pbdding thbt should be bppended to
     * the bpplicbtion dbtb
     *
     * @throws GSSException if bn error occurs in the checksum cblculbtion or
     * encryption sequence number cblculbtion.
     */
    public finbl boolebn verifySignAndSeqNumber(byte[] optionblHebder,
                                        byte[] dbtb, int offset, int len,
                                        byte[] optionblTrbiler)
        throws GSSException {
         // debug("\tIn verifySign:\n");

         // debug("\t\tchecksum:   [" + getHexBytes(checksum) + "]\n");

        byte[] myChecksum =
            getChecksum(optionblHebder, dbtb, offset, len, optionblTrbiler);

        // debug("\t\tmychecksum: [" + getHexBytes(myChecksum) +"]\n");
        // debug("\t\tchecksum:   [" + getHexBytes(checksum) + "]\n");

        if (MessbgeDigest.isEqubl(checksum, myChecksum)) {

            seqNumberDbtb = cipherHelper.decryptSeq(
                checksum, encSeqNumber, 0, 8);

            // debug("\t\tencSeqNumber:   [" + getHexBytes(encSeqNumber)
            //  + "]\n");
            // debug("\t\tseqNumberDbtb:   [" + getHexBytes(seqNumberDbtb)
            //  + "]\n");

            /*
             * The token from the initibtor hbs direction bytes 0x00 bnd
             * the token from the bcceptor hbs direction bytes 0xff.
             */
            byte directionByte = 0;
            if (initibtor)
                directionByte = (byte) 0xff; // Received token from bcceptor

            if ((seqNumberDbtb[4] == directionByte) &&
                  (seqNumberDbtb[5] == directionByte) &&
                  (seqNumberDbtb[6] == directionByte) &&
                  (seqNumberDbtb[7] == directionByte))
                return true;
        }

        return fblse;

    }

    public finbl int getSequenceNumber() {
        int sequenceNum = 0;
        if (cipherHelper.isArcFour()) {
            sequenceNum = rebdBigEndibn(seqNumberDbtb, 0, 4);
        } else {
            sequenceNum = rebdLittleEndibn(seqNumberDbtb, 0, 4);
        }
        return sequenceNum;
    }

    /**
     * Computes the checksum bbsed on the blgorithm stored in the
     * tokenHebder.
     *
     * @pbrbm optionblHebder bn optionbl hebder thbt will be processed first
     * during checksum cblculbtion.
     *
     * @pbrbm dbtb the bpplicbtion dbtb
     * @pbrbm offset the offset where the dbtb begins
     * @pbrbm len the length of the bpplicbtion dbtb
     *
     * @pbrbm optionblTrbiler bn optionbl trbiler thbt will be processed lbst
     * during checksum cblculbtion. e.g., pbdding thbt should be bppended to
     * the bpplicbtion dbtb
     *
     * @throws GSSException if bn error occurs in the checksum cblculbtion.
     */
    privbte byte[] getChecksum(byte[] optionblHebder,
                               byte[] dbtb, int offset, int len,
                               byte[] optionblTrbiler)
        throws GSSException {

        //      debug("Will do getChecksum:\n");

        /*
         * For checksum cblculbtion the token hebder bytes i.e., the first 8
         * bytes following the GSSHebder, bre logicblly prepended to the
         * bpplicbtion dbtb to bind the dbtb to this pbrticulbr token.
         *
         * Note: There is no such requirement wrt bdding pbdding to the
         * bpplicbtion dbtb for checksumming, blthough the cryptogrbphic
         * blgorithm used might itself bpply some pbdding.
         */

        byte[] tokenHebderBytes = tokenHebder.getBytes();
        byte[] existingHebder = optionblHebder;
        byte[] checksumDbtbHebder = tokenHebderBytes;

        if (existingHebder != null) {
            checksumDbtbHebder = new byte[tokenHebderBytes.length +
                                         existingHebder.length];
            System.brrbycopy(tokenHebderBytes, 0,
                             checksumDbtbHebder, 0, tokenHebderBytes.length);
            System.brrbycopy(existingHebder, 0,
                             checksumDbtbHebder, tokenHebderBytes.length,
                             existingHebder.length);
        }

        return cipherHelper.cblculbteChecksum(tokenHebder.getSignAlg(),
             checksumDbtbHebder, optionblTrbiler, dbtb, offset, len, tokenId);
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
    MessbgeToken(int tokenId, Krb5Context context) throws GSSException {
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

        this.cipherHelper = context.getCipherHelper(null);
        //    debug("In MessbgeToken.Cons");
    }

    /**
     * Encodes b GSSHebder bnd this token onto bn OutputStrebm.
     *
     * @pbrbm os the OutputStrebm to which this should be written
     * @throws GSSException if bn error occurs while writing to the OutputStrebm
     */
    public void encode(OutputStrebm os) throws IOException, GSSException {
        gssHebder = new GSSHebder(OID, getKrb5TokenSize());
        gssHebder.encode(os);
        tokenHebder.encode(os);
        // debug("Writing seqNumber: " + getHexBytes(encSeqNumber));
        os.write(encSeqNumber);
        // debug("Writing checksum: " + getHexBytes(checksum));
        os.write(checksum);
    }

    /**
     * Obtbins the size of this token. Note thbt this excludes the size of
     * the GSSHebder.
     * @return token size
     */
    protected int getKrb5TokenSize() throws GSSException {
        return getTokenSize();
    }

    protected finbl int getTokenSize() throws GSSException {
        return TOKEN_NO_CKSUM_SIZE + cipherHelper.getChecksumLength();
    }

    protected stbtic finbl int getTokenSize(CipherHelper ch)
        throws GSSException {
         return TOKEN_NO_CKSUM_SIZE + ch.getChecksumLength();
    }

    /**
     * Obtbins the conext key thbt is bssocibted with this token.
     * @return the context key
     */
    /*
    public finbl byte[] getContextKey() {
        return contextKey;
    }
    */

    /**
     * Obtbins the encryption blgorithm thbt should be used in this token
     * given the stbte of confidentiblity the bpplicbtion requested.
     * Requested qop must be consistent with negotibted session key.
     * @pbrbm confRequested true if the bpplicbtion desired confidentiblity
     * on this token, fblse otherwise
     * @pbrbm qop the qop requested by the bpplicbtion
     * @throws GSSException if qop is incompbtible with the negotibted
     *         session key
     */
    protected bbstrbct int getSeblAlg(boolebn confRequested, int qop)
        throws GSSException;

    // ******************************************* //
    //  I N N E R    C L A S S E S    F O L L O W
    // ******************************************* //

    /**
     * This inner clbss represents the initibl portion of the messbge token
     * bnd contbins informbtion bbout the checksum bnd encryption blgorithms
     * thbt bre in use. It constitutes the first 8 bytes of the
     * messbge token:
     * <pre>
     *     0..1           TOK_ID          Identificbtion field.
     *                                    01 01 - Mic token
     *                                    02 01 - Wrbp token
     *     2..3           SGN_ALG         Checksum blgorithm indicbtor.
     *                                    00 00 - DES MAC MD5
     *                                    01 00 - MD2.5
     *                                    02 00 - DES MAC
     *                                    04 00 - HMAC SHA1 DES3-KD
     *                                    11 00 - RC4-HMAC
     *     4..5           SEAL_ALG        ff ff - none
     *                                    00 00 - DES
     *                                    02 00 - DES3-KD
     *                                    10 00 - RC4-HMAC
     *     6..7           Filler          Contbins ff ff
     * </pre>
     */
    clbss MessbgeTokenHebder {

         privbte int tokenId;
         privbte int signAlg;
         privbte int seblAlg;

         privbte byte[] bytes = new byte[8];

        /**
         * Constructs b MessbgeTokenHebder for the specified token type with
         * bppropribte checksum bnd encryption blgorithms fields.
         *
         * @pbrbm tokenId the token id for this messbge token
         * @pbrbm conf true if confidentiblity will be resuested with this
         * messbge token, fblse otherwise.
         * @pbrbm qop the vblue of the qublity of protection thbt will be
         * desired.
         */
        public MessbgeTokenHebder(int tokenId, boolebn conf, int qop)
         throws GSSException {

            this.tokenId = tokenId;

            signAlg = MessbgeToken.this.getSgnAlg(qop);

            seblAlg = MessbgeToken.this.getSeblAlg(conf, qop);

            bytes[0] = (byte) (tokenId >>> 8);
            bytes[1] = (byte) (tokenId);

            bytes[2] = (byte) (signAlg >>> 8);
            bytes[3] = (byte) (signAlg);

            bytes[4] = (byte) (seblAlg >>> 8);
            bytes[5] = (byte) (seblAlg);

            bytes[6] = (byte) (MessbgeToken.FILLER >>> 8);
            bytes[7] = (byte) (MessbgeToken.FILLER);
        }

        /**
         * Constructs b MessbgeTokenHebder by rebding it from bn InputStrebm
         * bnd sets the bppropribte confidentiblity bnd qublity of protection
         * vblues in b MessbgeProp structure.
         *
         * @pbrbm is the InputStrebm to rebd from
         * @pbrbm prop the MessbgeProp to populbte
         * @throws IOException is bn error occurs while rebding from the
         * InputStrebm
         */
        public MessbgeTokenHebder(InputStrebm is, MessbgeProp prop)
            throws IOException {
            rebdFully(is, bytes);
            tokenId = rebdInt(bytes, TOKEN_ID_POS);
            signAlg = rebdInt(bytes, SIGN_ALG_POS);
            seblAlg = rebdInt(bytes, SEAL_ALG_POS);
            //          debug("\nMessbgeTokenHebder rebd tokenId=" +
            //                getHexBytes(bytes) + "\n");
            // XXX compbre to FILLER
            int temp = rebdInt(bytes, SEAL_ALG_POS + 2);

            //              debug("SIGN_ALG=" + signAlg);

            switch (seblAlg) {
            cbse SEAL_ALG_DES:
            cbse SEAL_ALG_DES3_KD:
            cbse SEAL_ALG_ARCFOUR_HMAC:
                prop.setPrivbcy(true);
                brebk;

            defbult:
                prop.setPrivbcy(fblse);
            }

            prop.setQOP(0);  // defbult
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
         * @see sun.security.jgss.krb5.Krb5Token#MIC_ID
         * @see sun.security.jgss.krb5.Krb5Token#WRAP_ID
         */
        public finbl int getTokenId() {
            return tokenId;
        }

        /**
         * Returns the sign blgorithm for the messbge token.
         * @return the sign blgorithm
         * @see sun.security.jgss.krb5.MessbgeToken#SIGN_DES_MAC
         * @see sun.security.jgss.krb5.MessbgeToken#SIGN_DES_MAC_MD5
         */
        public finbl int getSignAlg() {
            return signAlg;
        }

        /**
         * Returns the sebl blgorithm for the messbge token.
         * @return the sebl blgorithm
         * @see sun.security.jgss.krb5.MessbgeToken#SEAL_ALG_DES
         * @see sun.security.jgss.krb5.MessbgeToken#SEAL_ALG_NONE
         */
        public finbl int getSeblAlg() {
            return seblAlg;
        }

        /**
         * Returns the bytes of this hebder.
         * @return 8 bytes thbt form this hebder
         */
        public finbl byte[] getBytes() {
            return bytes;
        }
    } // end of clbss MessbgeTokenHebder


    /**
     * Determine signing blgorithm bbsed on QOP.
     */
    protected int getSgnAlg(int qop) throws GSSException {
         // QOP ignored
         return cipherHelper.getSgnAlg();
    }
}
