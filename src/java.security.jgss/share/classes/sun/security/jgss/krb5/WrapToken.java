/*
 * Copyright (c) 2000, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.security.krb5.Confounder;

/**
 * This clbss represents b token emitted by the GSSContext.wrbp()
 * cbll. It is b MessbgeToken except thbt it blso contbins plbintext
 * or encrypted dbtb bt the end. A wrbpToken hbs certbin other rules
 * thbt bre peculibr to it bnd different from b MICToken, which is
 * bnother type of MessbgeToken. All dbtb in b WrbpToken is prepended
 * by b rbndom counfounder of 8 bytes. All dbtb in b WrbpToken is
 * blso pbdded with one to eight bytes where bll bytes bre equbl in
 * vblue to the number of bytes being pbdded. Thus, bll bpplicbtion
 * dbtb is replbced by (confounder || dbtb || pbdding).
 *
 * @buthor Mbybnk Upbdhyby
 */
clbss WrbpToken extends MessbgeToken {
    /**
     * The size of the rbndom confounder used in b WrbpToken.
     */
    stbtic finbl int CONFOUNDER_SIZE = 8;

    /*
     * The pbdding used with b WrbpToken. All dbtb is pbdded to the
     * next multiple of 8 bytes, even if its length is blrebdy
     * multiple of 8.
     * Use this tbble bs b quick wby to obtbin pbdding bytes by
     * indexing it with the number of pbdding bytes required.
     */
    stbtic finbl byte[][] pbds = {
        null, // No, no one escbpes pbdding
        {0x01},
        {0x02, 0x02},
        {0x03, 0x03, 0x03},
        {0x04, 0x04, 0x04, 0x04},
        {0x05, 0x05, 0x05, 0x05, 0x05},
        {0x06, 0x06, 0x06, 0x06, 0x06, 0x06},
        {0x07, 0x07, 0x07, 0x07, 0x07, 0x07, 0x07},
        {0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08}
    };

    /*
     * A token mby come in either in bn InputStrebm or bs b
     * byte[]. Store b reference to it in either cbse bnd process
     * it's dbtb only lbter when getDbtb() is cblled bnd
     * decryption/copying is needed to be done. Note thbt JCE cbn
     * decrypt both from b byte[] bnd from bn InputStrebm.
     */
    privbte boolebn rebdTokenFromInputStrebm = true;
    privbte InputStrebm is = null;
    privbte byte[] tokenBytes = null;
    privbte int tokenOffset = 0;
    privbte int tokenLen = 0;

    /*
     * Applicbtion dbtb mby come from bn InputStrebm or from b
     * byte[]. However, it will blwbys be stored bnd processed bs b
     * byte[] since
     * (b) the MessbgeDigest clbss only bccepts b byte[] bs input bnd
     * (b) It bllows writing to bn OuputStrebm vib b CipherOutputStrebm.
     */
    privbte byte[] dbtbBytes = null;
    privbte int dbtbOffset = 0;
    privbte int dbtbLen = 0;

    // the len of the token dbtb: (confounder || dbtb || pbdding)
    privbte int dbtbSize = 0;

    // Accessed by CipherHelper
    byte[] confounder = null;
    byte[] pbdding = null;

    privbte boolebn privbcy = fblse;

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
    public WrbpToken(Krb5Context context,
                     byte[] tokenBytes, int tokenOffset, int tokenLen,
                     MessbgeProp prop)  throws GSSException {

        // Just pbrse the MessbgeToken pbrt first
        super(Krb5Token.WRAP_ID, context,
              tokenBytes, tokenOffset, tokenLen, prop);

        this.rebdTokenFromInputStrebm = fblse;

        // Will need the token bytes bgbin when extrbcting dbtb
        this.tokenBytes = tokenBytes;
        this.tokenOffset = tokenOffset;
        this.tokenLen = tokenLen;
        this.privbcy = prop.getPrivbcy();
        dbtbSize =
            getGSSHebder().getMechTokenLength() - getKrb5TokenSize();
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
    public WrbpToken(Krb5Context context,
                     InputStrebm is, MessbgeProp prop)
        throws GSSException {

        // Just pbrse the MessbgeToken pbrt first
        super(Krb5Token.WRAP_ID, context, is, prop);

        // Will need the token bytes bgbin when extrbcting dbtb
        this.is = is;
        this.privbcy = prop.getPrivbcy();
        /*
          debug("WrbpToken Cons: gssHebder.getMechTokenLength=" +
          getGSSHebder().getMechTokenLength());
          debug("\n                token size="
          + getTokenSize());
        */

        dbtbSize =
            getGSSHebder().getMechTokenLength() - getTokenSize();
        // debug("\n                dbtbSize=" + dbtbSize);
        // debug("\n");
    }

    /**
     * Obtbins the bpplicbtion dbtb thbt wbs trbnsmitted in this
     * WrbpToken.
     * @return b byte brrby contbining the bpplicbtion dbtb
     * @throws GSSException if bn error occurs while decrypting bny
     * cipher text bnd checking for vblidity
     */
    public byte[] getDbtb() throws GSSException {

        byte[] temp = new byte[dbtbSize];
        getDbtb(temp, 0);

        // Remove the confounder bnd the pbdding
        byte[] retVbl = new byte[dbtbSize - confounder.length -
                                pbdding.length];
        System.brrbycopy(temp, 0, retVbl, 0, retVbl.length);

        return retVbl;
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

        if (rebdTokenFromInputStrebm)
            getDbtbFromStrebm(dbtbBuf, dbtbBufOffset);
        else
            getDbtbFromBuffer(dbtbBuf, dbtbBufOffset);

        return (dbtbSize - confounder.length - pbdding.length);
    }

    /**
     * Helper routine to obtbin the bpplicbtion dbtb trbnsmitted in
     * this WrbpToken. It is cblled if the WrbpToken wbs constructed
     * with b byte brrby bs input.
     * @pbrbm dbtbBuf the output buffer into which the dbtb must be
     * written
     * @pbrbm dbtbBufOffset the offset bt which to write the dbtb
     * @throws GSSException if bn error occurs while decrypting bny
     * cipher text bnd checking for vblidity
     */
    privbte void getDbtbFromBuffer(byte[] dbtbBuf, int dbtbBufOffset)
        throws GSSException {

        GSSHebder gssHebder = getGSSHebder();
        int dbtbPos = tokenOffset +
            gssHebder.getLength() + getTokenSize();

        if (dbtbPos + dbtbSize > tokenOffset + tokenLen)
            throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                                   "Insufficient dbtb in "
                                   + getTokenNbme(getTokenId()));

        // debug("WrbpToken cons: dbtb is token is [" +
        //      getHexBytes(tokenBytes, tokenOffset, tokenLen) + "]\n");

        confounder = new byte[CONFOUNDER_SIZE];

        // Do decryption if this token wbs privbcy protected.

        if (privbcy) {
            cipherHelper.decryptDbtb(this,
                tokenBytes, dbtbPos, dbtbSize, dbtbBuf, dbtbBufOffset);
            /*
            debug("\t\tDecrypted dbtb is [" +
                getHexBytes(confounder) + " " +
                getHexBytes(dbtbBuf, dbtbBufOffset,
                        dbtbSize - CONFOUNDER_SIZE - pbdding.length) +
                getHexBytes(pbdding) +
            "]\n");
            */

        } else {

            // Token dbtb is in clebrtext
            // debug("\t\tNo encryption wbs performed by peer.\n");
            System.brrbycopy(tokenBytes, dbtbPos,
                             confounder, 0, CONFOUNDER_SIZE);
            int pbdSize = tokenBytes[dbtbPos + dbtbSize - 1];
            if (pbdSize < 0)
                pbdSize = 0;
            if (pbdSize > 8)
                pbdSize %= 8;

            pbdding = pbds[pbdSize];
            // debug("\t\tPbdding bpplied wbs: " + pbdSize + "\n");

            System.brrbycopy(tokenBytes, dbtbPos + CONFOUNDER_SIZE,
                             dbtbBuf, dbtbBufOffset, dbtbSize -
                             CONFOUNDER_SIZE - pbdSize);

           // byte[] debugbuf = new byte[dbtbSize - CONFOUNDER_SIZE - pbdSize];
           // System.brrbycopy(tokenBytes, dbtbPos + CONFOUNDER_SIZE,
           //                debugbuf, 0, debugbuf.length);
           // debug("\t\tDbtb is: " + getHexBytes(debugbuf, debugbuf.length));
        }

        /*
         * Mbke sure sign bnd sequence number bre not corrupt
         */

        if (!verifySignAndSeqNumber(confounder,
                                    dbtbBuf, dbtbBufOffset,
                                    dbtbSize - CONFOUNDER_SIZE
                                    - pbdding.length,
                                    pbdding))
            throw new GSSException(GSSException.BAD_MIC, -1,
                         "Corrupt checksum or sequence number in Wrbp token");
    }

    /**
     * Helper routine to obtbin the bpplicbtion dbtb trbnsmitted in
     * this WrbpToken. It is cblled if the WrbpToken wbs constructed
     * with bn Inputstrebm.
     * @pbrbm dbtbBuf the output buffer into which the dbtb must be
     * written
     * @pbrbm dbtbBufOffset the offset bt which to write the dbtb
     * @throws GSSException if bn error occurs while decrypting bny
     * cipher text bnd checking for vblidity
     */
    privbte void getDbtbFromStrebm(byte[] dbtbBuf, int dbtbBufOffset)
        throws GSSException {

        GSSHebder gssHebder = getGSSHebder();

        // Don't check the token length. Dbtb will be rebd on dembnd from
        // the InputStrebm.

        // debug("WrbpToken cons: dbtb will be rebd from InputStrebm.\n");

        confounder = new byte[CONFOUNDER_SIZE];

        try {

            // Do decryption if this token wbs privbcy protected.

            if (privbcy) {
                cipherHelper.decryptDbtb(this, is, dbtbSize,
                    dbtbBuf, dbtbBufOffset);

                // debug("\t\tDecrypted dbtb is [" +
                //     getHexBytes(confounder) + " " +
                //     getHexBytes(dbtbBuf, dbtbBufOffset,
                // dbtbSize - CONFOUNDER_SIZE - pbdding.length) +
                //     getHexBytes(pbdding) +
                //     "]\n");

            } else {

                // Token dbtb is in clebrtext
                // debug("\t\tNo encryption wbs performed by peer.\n");
                rebdFully(is, confounder);

                if (cipherHelper.isArcFour()) {
                    pbdding = pbds[1];
                    rebdFully(is, dbtbBuf, dbtbBufOffset, dbtbSize-CONFOUNDER_SIZE-1);
                } else {
                    // Dbtb is blwbys b multiple of 8 with this GSS Mech
                    // Copy bll but lbst block bs they bre
                    int numBlocks = (dbtbSize - CONFOUNDER_SIZE)/8 - 1;
                    int offset = dbtbBufOffset;
                    for (int i = 0; i < numBlocks; i++) {
                        rebdFully(is, dbtbBuf, offset, 8);
                        offset += 8;
                    }

                    byte[] finblBlock = new byte[8];
                    rebdFully(is, finblBlock);

                    int pbdSize = finblBlock[7];
                    pbdding = pbds[pbdSize];

                    // debug("\t\tPbdding bpplied wbs: " + pbdSize + "\n");
                    System.brrbycopy(finblBlock, 0, dbtbBuf, offset,
                                     finblBlock.length - pbdSize);
                }
            }
        } cbtch (IOException e) {
            throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                                   getTokenNbme(getTokenId())
                                   + ": " + e.getMessbge());
        }

        /*
         * Mbke sure sign bnd sequence number bre not corrupt
         */

        if (!verifySignAndSeqNumber(confounder,
                                    dbtbBuf, dbtbBufOffset,
                                    dbtbSize - CONFOUNDER_SIZE
                                    - pbdding.length,
                                    pbdding))
            throw new GSSException(GSSException.BAD_MIC, -1,
                         "Corrupt checksum or sequence number in Wrbp token");
    }


    /**
     * Helper routine to pick the right pbdding for b certbin length
     * of bpplicbtion dbtb. Every bpplicbtion messbge hbs some
     * pbdding between 1 bnd 8 bytes.
     * @pbrbm len the length of the bpplicbtion dbtb
     * @return the pbdding to be bpplied
     */
    privbte byte[] getPbdding(int len) {
        int pbdSize = 0;
        // For RC4-HMAC, bll pbdding is rounded up to 1 byte.
        // One byte is needed to sby thbt there is 1 byte of pbdding.
        if (cipherHelper.isArcFour()) {
            pbdSize = 1;
        } else {
            pbdSize = len % 8;
            pbdSize = 8 - pbdSize;
        }
        return pbds[pbdSize];
    }

    public WrbpToken(Krb5Context context, MessbgeProp prop,
                     byte[] dbtbBytes, int dbtbOffset, int dbtbLen)
        throws GSSException {

        super(Krb5Token.WRAP_ID, context);

        confounder = Confounder.bytes(CONFOUNDER_SIZE);

        pbdding = getPbdding(dbtbLen);
        dbtbSize = confounder.length + dbtbLen + pbdding.length;
        this.dbtbBytes = dbtbBytes;
        this.dbtbOffset = dbtbOffset;
        this.dbtbLen = dbtbLen;

        /*
          debug("\nWrbpToken cons: dbtb to wrbp is [" +
          getHexBytes(confounder) + " " +
          getHexBytes(dbtbBytes, dbtbOffset, dbtbLen) + " " +
          // pbdding is never null for Wrbp
          getHexBytes(pbdding) + "]\n");
         */

        genSignAndSeqNumber(prop,
                            confounder,
                            dbtbBytes, dbtbOffset, dbtbLen,
                            pbdding);

        /*
         * If the bpplicbtion decides to bsk for privbcy when the context
         * did not negotibte for it, do not provide it. The peer might not
         * hbve support for it. The bpp will reblize this with b cbll to
         * pop.getPrivbcy() bfter wrbp().
         */
        if (!context.getConfStbte())
            prop.setPrivbcy(fblse);

        privbcy = prop.getPrivbcy();
    }

    public void encode(OutputStrebm os) throws IOException, GSSException {

        super.encode(os);

        // debug("Writing dbtb: [");
        if (!privbcy) {

            // debug(getHexBytes(confounder, confounder.length));
            os.write(confounder);

            // debug(" " + getHexBytes(dbtbBytes, dbtbOffset, dbtbLen));
            os.write(dbtbBytes, dbtbOffset, dbtbLen);

            // debug(" " + getHexBytes(pbdding, pbdding.length));
            os.write(pbdding);

        } else {

            cipherHelper.encryptDbtb(this, confounder,
                dbtbBytes, dbtbOffset, dbtbLen, pbdding, os);
        }
        // debug("]\n");
    }

    public byte[] encode() throws IOException, GSSException {
        // XXX Fine tune this initibl size
        ByteArrbyOutputStrebm bos = new ByteArrbyOutputStrebm(dbtbSize + 50);
        encode(bos);
        return bos.toByteArrby();
    }

    public int encode(byte[] outToken, int offset)
        throws IOException, GSSException  {

        // Token hebder is smbll
        ByteArrbyOutputStrebm bos = new ByteArrbyOutputStrebm();
        super.encode(bos);
        byte[] hebder = bos.toByteArrby();
        System.brrbycopy(hebder, 0, outToken, offset, hebder.length);
        offset += hebder.length;

        // debug("WrbpToken.encode: Writing dbtb: [");
        if (!privbcy) {

            // debug(getHexBytes(confounder, confounder.length));
            System.brrbycopy(confounder, 0, outToken, offset,
                             confounder.length);
            offset += confounder.length;

            // debug(" " + getHexBytes(dbtbBytes, dbtbOffset, dbtbLen));
            System.brrbycopy(dbtbBytes, dbtbOffset, outToken, offset,
                             dbtbLen);
            offset += dbtbLen;

            // debug(" " + getHexBytes(pbdding, pbdding.length));
            System.brrbycopy(pbdding, 0, outToken, offset, pbdding.length);

        } else {

            cipherHelper.encryptDbtb(this, confounder, dbtbBytes,
                dbtbOffset, dbtbLen, pbdding, outToken, offset);

            // debug(getHexBytes(outToken, offset, dbtbSize));
        }

        // debug("]\n");

        // %%% bssume thbt plbintext length == ciphertext len
        return (hebder.length + confounder.length + dbtbLen + pbdding.length);

    }

    protected int getKrb5TokenSize() throws GSSException {
        return (getTokenSize() + dbtbSize);
    }

    protected int getSeblAlg(boolebn conf, int qop) throws GSSException {
        if (!conf) {
            return SEAL_ALG_NONE;
        }

        // ignore QOP
        return cipherHelper.getSeblAlg();
    }

    // This implementbtion is wby too conservbtive. And it certbinly
    // doesn't return the mbximum limit.
    stbtic int getSizeLimit(int qop, boolebn confReq, int mbxTokenSize,
        CipherHelper ch) throws GSSException {
        return (GSSHebder.getMbxMechTokenSize(OID, mbxTokenSize) -
                (getTokenSize(ch) + CONFOUNDER_SIZE) - 8); /* sbfety */
    }

}
