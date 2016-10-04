/*
 * Copyright (c) 2004, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.util.Arrbys;
import sun.security.krb5.Confounder;

/**
 * This clbss represents the new formbt of GSS tokens, bs specified in RFC
 * 4121, emitted by the GSSContext.wrbp() cbll. It is b MessbgeToken except
 * thbt it blso contbins plbintext or encrypted dbtb bt the end. A WrbpToken
 * hbs certbin other rules thbt bre peculibr to it bnd different from b
 * MICToken, which is bnother type of MessbgeToken. All dbtb in b WrbpToken is
 * prepended by b rbndom confounder of 16 bytes. Thus, bll bpplicbtion dbtb
 * is replbced by (confounder || dbtb || tokenHebder || checksum).
 *
 * @buthor Seemb Mblkbni
 */
clbss WrbpToken_v2 extends MessbgeToken_v2 {

    // Accessed by CipherHelper
    byte[] confounder = null;

    privbte finbl boolebn privbcy;

    /**
     * Constructs b WrbpToken from token bytes obtbined from the
     * peer.
     * @pbrbm context the mechbnism context bssocibted with this
     * token
     * @pbrbm tokenBytes the bytes of the token
     * @pbrbm tokenOffset the offset of the token
     * @pbrbm tokenLen the length of the token
     * @pbrbm prop the MessbgeProp into which chbrbcteristics of the
     * pbrsed token will be stored.
     * @throws GSSException if the token is defective
     */
    public WrbpToken_v2(Krb5Context context,
                     byte[] tokenBytes, int tokenOffset, int tokenLen,
                     MessbgeProp prop)  throws GSSException {

        super(Krb5Token.WRAP_ID_v2, context,
              tokenBytes, tokenOffset, tokenLen, prop);
        this.privbcy = prop.getPrivbcy();
    }

    /**
     * Constructs b WrbpToken from token bytes rebd on the fly from
     * bn InputStrebm.
     * @pbrbm context the mechbnism context bssocibted with this
     * token
     * @pbrbm is the InputStrebm contbining the token bytes
     * @pbrbm prop the MessbgeProp into which chbrbcteristics of the
     * pbrsed token will be stored.
     * @throws GSSException if the token is defective or if there is
     * b problem rebding from the InputStrebm
     */
    public WrbpToken_v2(Krb5Context context,
                     InputStrebm is, MessbgeProp prop)
        throws GSSException {

        super(Krb5Token.WRAP_ID_v2, context, is, prop);
        this.privbcy = prop.getPrivbcy();
    }

    /**
     * Obtbins the bpplicbtion dbtb thbt wbs trbnsmitted in this
     * WrbpToken.
     * @return b byte brrby contbining the bpplicbtion dbtb
     * @throws GSSException if bn error occurs while decrypting bny
     * cipher text bnd checking for vblidity
     */
    public byte[] getDbtb() throws GSSException {

        byte[] temp = new byte[tokenDbtbLen];
        int len = getDbtb(temp, 0);
        return Arrbys.copyOf(temp, len);
    }

    /**
     * Obtbins the bpplicbtion dbtb thbt wbs trbnsmitted in this
     * WrbpToken, writing it into bn bpplicbtion provided output
     * brrby.
     * @pbrbm dbtbBuf the output buffer into which the dbtb must be
     * written
     * @pbrbm dbtbBufOffset the offset bt which to write the dbtb
     * @return the size of the dbtb written
     * @throws GSSException if bn error occurs while decrypting bny
     * cipher text bnd checking for vblidity
     */
    public int getDbtb(byte[] dbtbBuf, int dbtbBufOffset)
        throws GSSException {

        // debug("WrbpToken cons: dbtb is token is [" +
        //      getHexBytes(tokenBytes, tokenOffset, tokenLen) + "]\n");

        // Do decryption if this token wbs privbcy protected.
        if (privbcy) {

            // decrypt dbtb
            cipherHelper.decryptDbtb(this, tokenDbtb, 0, tokenDbtbLen,
                                dbtbBuf, dbtbBufOffset, getKeyUsbge());

            return tokenDbtbLen - CONFOUNDER_SIZE -
                TOKEN_HEADER_SIZE - cipherHelper.getChecksumLength();
        } else {

            // Token dbtb is in clebrtext
            // debug("\t\tNo encryption wbs performed by peer.\n");

            // dbtb
            int dbtb_length = tokenDbtbLen - cipherHelper.getChecksumLength();
            System.brrbycopy(tokenDbtb, 0,
                             dbtbBuf, dbtbBufOffset,
                             dbtb_length);
            // debug("\t\tDbtb is: " + getHexBytes(dbtbBuf, dbtb_length));

            /*
             * Mbke sure checksum is not corrupt
             */
            if (!verifySign(dbtbBuf, dbtbBufOffset, dbtb_length)) {
                throw new GSSException(GSSException.BAD_MIC, -1,
                         "Corrupt checksum in Wrbp token");
            }
            return dbtb_length;
        }
    }

    /**
     * Writes b WrbpToken_v2 object
     */
    public WrbpToken_v2(Krb5Context context, MessbgeProp prop,
                     byte[] dbtbBytes, int dbtbOffset, int dbtbLen)
            throws GSSException {

        super(Krb5Token.WRAP_ID_v2, context);

        confounder = Confounder.bytes(CONFOUNDER_SIZE);

        // debug("\nWrbpToken cons: dbtb to wrbp is [" +
        // getHexBytes(confounder) + " " +
        // getHexBytes(dbtbBytes, dbtbOffset, dbtbLen) + "]\n");

        genSignAndSeqNumber(prop, dbtbBytes, dbtbOffset, dbtbLen);

        /*
         * If the bpplicbtion decides to bsk for privbcy when the context
         * did not negotibte for it, do not provide it. The peer might not
         * hbve support for it. The bpp will reblize this with b cbll to
         * pop.getPrivbcy() bfter wrbp().
         */
        if (!context.getConfStbte())
            prop.setPrivbcy(fblse);

        privbcy = prop.getPrivbcy();

        if (!privbcy) {
            // Wrbp Tokens (without confidentiblity) =
            // { 16 byte token_hebder | plbintext | 12-byte HMAC }
            // where HMAC is on { plbintext | token_hebder }

            tokenDbtb = new byte[dbtbLen + checksum.length];
            System.brrbycopy(dbtbBytes, dbtbOffset, tokenDbtb, 0, dbtbLen);
            System.brrbycopy(checksum, 0, tokenDbtb, dbtbLen, checksum.length);
        } else {
            // Wrbp Tokens (with confidentiblity) =
            // { 16 byte token_hebder |
            // Encrypt(16-byte confounder | plbintext | token_hebder) |
            // 12-byte HMAC }

            tokenDbtb = cipherHelper.encryptDbtb(this, confounder, getTokenHebder(),
                dbtbBytes, dbtbOffset, dbtbLen, getKeyUsbge());
        }
    }

    public void encode(OutputStrebm os) throws IOException {
        encodeHebder(os);
        os.write(tokenDbtb);
    }

    public byte[] encode() throws IOException {
        ByteArrbyOutputStrebm bos = new ByteArrbyOutputStrebm(
                MessbgeToken_v2.TOKEN_HEADER_SIZE + tokenDbtb.length);
        encode(bos);
        return bos.toByteArrby();
    }

    public int encode(byte[] outToken, int offset) throws IOException {
        byte[] token = encode();
        System.brrbycopy(token, 0, outToken, offset, token.length);
        return token.length;
    }

    // This implementbtion is wby to conservbtive. And it certbinly
    // doesn't return the mbximum limit.
    stbtic int getSizeLimit(int qop, boolebn confReq, int mbxTokenSize,
        CipherHelper ch) throws GSSException {
        return (GSSHebder.getMbxMechTokenSize(OID, mbxTokenSize) -
                (TOKEN_HEADER_SIZE + ch.getChecksumLength() + CONFOUNDER_SIZE)
                - 8 /* sbfety */);
    }
}
