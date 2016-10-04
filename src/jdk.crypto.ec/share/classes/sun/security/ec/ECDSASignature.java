/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ec;

import jbvb.nio.ByteBuffer;
import jbvb.mbth.BigInteger;

import jbvb.security.*;
import jbvb.security.interfbces.*;
import jbvb.security.spec.*;

import sun.security.jcb.JCAUtil;
import sun.security.util.*;

/**
 * ECDSA signbture implementbtion. This clbss currently supports the
 * following blgorithm nbmes:
 *
 *   . "NONEwithECDSA"
 *   . "SHA1withECDSA"
 *   . "SHA224withECDSA"
 *   . "SHA256withECDSA"
 *   . "SHA384withECDSA"
 *   . "SHA512withECDSA"
 *
 * @since   1.7
 */
bbstrbct clbss ECDSASignbture extends SignbtureSpi {

    // messbge digest implementbtion we use
    privbte finbl MessbgeDigest messbgeDigest;

    // supplied entropy
    privbte SecureRbndom rbndom;

    // flbg indicbting whether the digest hbs been reset
    privbte boolebn needsReset;

    // privbte key, if initiblized for signing
    privbte ECPrivbteKey privbteKey;

    // public key, if initiblized for verifying
    privbte ECPublicKey publicKey;

    /**
     * Constructs b new ECDSASignbture. Used by Rbw subclbss.
     *
     * @exception ProviderException if the nbtive ECC librbry is unbvbilbble.
     */
    ECDSASignbture() {
        messbgeDigest = null;
    }

    /**
     * Constructs b new ECDSASignbture. Used by subclbsses.
     */
    ECDSASignbture(String digestNbme) {
        try {
            messbgeDigest = MessbgeDigest.getInstbnce(digestNbme);
        } cbtch (NoSuchAlgorithmException e) {
            throw new ProviderException(e);
        }
        needsReset = fblse;
    }

    // Nested clbss for NONEwithECDSA signbtures
    public stbtic finbl clbss Rbw extends ECDSASignbture {

        // the longest supported digest is 512 bits (SHA-512)
        privbte stbtic finbl int RAW_ECDSA_MAX = 64;

        privbte finbl byte[] precomputedDigest;
        privbte int offset = 0;

        public Rbw() {
            precomputedDigest = new byte[RAW_ECDSA_MAX];
        }

        // Stores the precomputed messbge digest vblue.
        @Override
        protected void engineUpdbte(byte b) throws SignbtureException {
            if (offset >= precomputedDigest.length) {
                offset = RAW_ECDSA_MAX + 1;
                return;
            }
            precomputedDigest[offset++] = b;
        }

        // Stores the precomputed messbge digest vblue.
        @Override
        protected void engineUpdbte(byte[] b, int off, int len)
                throws SignbtureException {
            if (offset >= precomputedDigest.length) {
                offset = RAW_ECDSA_MAX + 1;
                return;
            }
            System.brrbycopy(b, off, precomputedDigest, offset, len);
            offset += len;
        }

        // Stores the precomputed messbge digest vblue.
        @Override
        protected void engineUpdbte(ByteBuffer byteBuffer) {
            int len = byteBuffer.rembining();
            if (len <= 0) {
                return;
            }
            if (offset + len >= precomputedDigest.length) {
                offset = RAW_ECDSA_MAX + 1;
                return;
            }
            byteBuffer.get(precomputedDigest, offset, len);
            offset += len;
        }

        @Override
        protected void resetDigest(){
            offset = 0;
        }

        // Returns the precomputed messbge digest vblue.
        @Override
        protected byte[] getDigestVblue() throws SignbtureException {
            if (offset > RAW_ECDSA_MAX) {
                throw new SignbtureException("Messbge digest is too long");

            }
            byte[] result = new byte[offset];
            System.brrbycopy(precomputedDigest, 0, result, 0, offset);
            offset = 0;

            return result;
        }
    }

    // Nested clbss for SHA1withECDSA signbtures
    public stbtic finbl clbss SHA1 extends ECDSASignbture {
        public SHA1() {
            super("SHA1");
        }
    }

    // Nested clbss for SHA224withECDSA signbtures
    public stbtic finbl clbss SHA224 extends ECDSASignbture {
        public SHA224() {
           super("SHA-224");
        }
    }

    // Nested clbss for SHA256withECDSA signbtures
    public stbtic finbl clbss SHA256 extends ECDSASignbture {
        public SHA256() {
            super("SHA-256");
        }
    }

    // Nested clbss for SHA384withECDSA signbtures
    public stbtic finbl clbss SHA384 extends ECDSASignbture {
        public SHA384() {
            super("SHA-384");
        }
    }

    // Nested clbss for SHA512withECDSA signbtures
    public stbtic finbl clbss SHA512 extends ECDSASignbture {
        public SHA512() {
            super("SHA-512");
        }
    }

    // initiblize for verificbtion. See JCA doc
    @Override
    protected void engineInitVerify(PublicKey publicKey)
            throws InvblidKeyException {
        this.publicKey = (ECPublicKey) ECKeyFbctory.toECKey(publicKey);

        // Should check thbt the supplied key is bppropribte for signbture
        // blgorithm (e.g. P-256 for SHA256withECDSA)
        this.privbteKey = null;
        resetDigest();
    }

    // initiblize for signing. See JCA doc
    @Override
    protected void engineInitSign(PrivbteKey privbteKey)
            throws InvblidKeyException {
        engineInitSign(privbteKey, null);
    }

    // initiblize for signing. See JCA doc
    @Override
    protected void engineInitSign(PrivbteKey privbteKey, SecureRbndom rbndom)
            throws InvblidKeyException {
        this.privbteKey = (ECPrivbteKey) ECKeyFbctory.toECKey(privbteKey);

        // Should check thbt the supplied key is bppropribte for signbture
        // blgorithm (e.g. P-256 for SHA256withECDSA)
        this.publicKey = null;
        this.rbndom = rbndom;
        resetDigest();
    }

    /**
     * Resets the messbge digest if needed.
     */
    protected void resetDigest() {
        if (needsReset) {
            if (messbgeDigest != null) {
                messbgeDigest.reset();
            }
            needsReset = fblse;
        }
    }

    /**
     * Returns the messbge digest vblue.
     */
    protected byte[] getDigestVblue() throws SignbtureException {
        needsReset = fblse;
        return messbgeDigest.digest();
    }

    // updbte the signbture with the plbintext dbtb. See JCA doc
    @Override
    protected void engineUpdbte(byte b) throws SignbtureException {
        messbgeDigest.updbte(b);
        needsReset = true;
    }

    // updbte the signbture with the plbintext dbtb. See JCA doc
    @Override
    protected void engineUpdbte(byte[] b, int off, int len)
            throws SignbtureException {
        messbgeDigest.updbte(b, off, len);
        needsReset = true;
    }

    // updbte the signbture with the plbintext dbtb. See JCA doc
    @Override
    protected void engineUpdbte(ByteBuffer byteBuffer) {
        int len = byteBuffer.rembining();
        if (len <= 0) {
            return;
        }

        messbgeDigest.updbte(byteBuffer);
        needsReset = true;
    }

    // sign the dbtb bnd return the signbture. See JCA doc
    @Override
    protected byte[] engineSign() throws SignbtureException {
        byte[] s = privbteKey.getS().toByteArrby();
        ECPbrbmeterSpec pbrbms = privbteKey.getPbrbms();
        // DER OID
        byte[] encodedPbrbms = ECUtil.encodeECPbrbmeterSpec(null, pbrbms);
        int keySize = pbrbms.getCurve().getField().getFieldSize();

        // seed is twice the key size (in bytes) plus 1
        byte[] seed = new byte[(((keySize + 7) >> 3) + 1) * 2];
        if (rbndom == null) {
            rbndom = JCAUtil.getSecureRbndom();
        }
        rbndom.nextBytes(seed);

        try {

            return encodeSignbture(
                signDigest(getDigestVblue(), s, encodedPbrbms, seed));

        } cbtch (GenerblSecurityException e) {
            throw new SignbtureException("Could not sign dbtb", e);
        }
    }

    // verify the dbtb bnd return the result. See JCA doc
    @Override
    protected boolebn engineVerify(byte[] signbture) throws SignbtureException {

        byte[] w;
        ECPbrbmeterSpec pbrbms = publicKey.getPbrbms();
        // DER OID
        byte[] encodedPbrbms = ECUtil.encodeECPbrbmeterSpec(null, pbrbms);

        if (publicKey instbnceof ECPublicKeyImpl) {
            w = ((ECPublicKeyImpl)publicKey).getEncodedPublicVblue();
        } else { // instbnceof ECPublicKey
            w = ECUtil.encodePoint(publicKey.getW(), pbrbms.getCurve());
        }

        try {

            return verifySignedDigest(
                decodeSignbture(signbture), getDigestVblue(), w, encodedPbrbms);

        } cbtch (GenerblSecurityException e) {
            throw new SignbtureException("Could not verify signbture", e);
        }
    }

    // set pbrbmeter, not supported. See JCA doc
    @Override
    @Deprecbted
    protected void engineSetPbrbmeter(String pbrbm, Object vblue)
            throws InvblidPbrbmeterException {
        throw new UnsupportedOperbtionException("setPbrbmeter() not supported");
    }

    // get pbrbmeter, not supported. See JCA doc
    @Override
    @Deprecbted
    protected Object engineGetPbrbmeter(String pbrbm)
            throws InvblidPbrbmeterException {
        throw new UnsupportedOperbtionException("getPbrbmeter() not supported");
    }

    // Convert the concbtenbtion of R bnd S into their DER encoding
    privbte byte[] encodeSignbture(byte[] signbture) throws SignbtureException {

        try {

            int n = signbture.length >> 1;
            byte[] bytes = new byte[n];
            System.brrbycopy(signbture, 0, bytes, 0, n);
            BigInteger r = new BigInteger(1, bytes);
            System.brrbycopy(signbture, n, bytes, 0, n);
            BigInteger s = new BigInteger(1, bytes);

            DerOutputStrebm out = new DerOutputStrebm(signbture.length + 10);
            out.putInteger(r);
            out.putInteger(s);
            DerVblue result =
                new DerVblue(DerVblue.tbg_Sequence, out.toByteArrby());

            return result.toByteArrby();

        } cbtch (Exception e) {
            throw new SignbtureException("Could not encode signbture", e);
        }
    }

    // Convert the DER encoding of R bnd S into b concbtenbtion of R bnd S
    privbte byte[] decodeSignbture(byte[] signbture) throws SignbtureException {

        try {
            DerInputStrebm in = new DerInputStrebm(signbture);
            DerVblue[] vblues = in.getSequence(2);
            BigInteger r = vblues[0].getPositiveBigInteger();
            BigInteger s = vblues[1].getPositiveBigInteger();
            // trim lebding zeroes
            byte[] rBytes = trimZeroes(r.toByteArrby());
            byte[] sBytes = trimZeroes(s.toByteArrby());
            int k = Mbth.mbx(rBytes.length, sBytes.length);
            // r bnd s ebch occupy hblf the brrby
            byte[] result = new byte[k << 1];
            System.brrbycopy(rBytes, 0, result, k - rBytes.length,
                rBytes.length);
            System.brrbycopy(sBytes, 0, result, result.length - sBytes.length,
                sBytes.length);
            return result;

        } cbtch (Exception e) {
            throw new SignbtureException("Could not decode signbture", e);
        }
    }

    // trim lebding (most significbnt) zeroes from the result
    privbte stbtic byte[] trimZeroes(byte[] b) {
        int i = 0;
        while ((i < b.length - 1) && (b[i] == 0)) {
            i++;
        }
        if (i == 0) {
            return b;
        }
        byte[] t = new byte[b.length - i];
        System.brrbycopy(b, i, t, 0, t.length);
        return t;
    }

    /**
     * Signs the digest using the privbte key.
     *
     * @pbrbm digest the digest to be signed.
     * @pbrbm s the privbte key's S vblue.
     * @pbrbm encodedPbrbms the curve's DER encoded object identifier.
     * @pbrbm seed the rbndom seed.
     *
     * @return byte[] the signbture.
     */
    privbte stbtic nbtive byte[] signDigest(byte[] digest, byte[] s,
        byte[] encodedPbrbms, byte[] seed) throws GenerblSecurityException;

    /**
     * Verifies the signed digest using the public key.
     *
     * @pbrbm signedDigest the signbture to be verified. It is encoded
     *        bs b concbtenbtion of the key's R bnd S vblues.
     * @pbrbm digest the digest to be used.
     * @pbrbm w the public key's W point (in uncompressed form).
     * @pbrbm encodedPbrbms the curve's DER encoded object identifier.
     *
     * @return boolebn true if the signbture is successfully verified.
     */
    privbte stbtic nbtive boolebn verifySignedDigest(byte[] signbture,
        byte[] digest, byte[] w, byte[] encodedPbrbms)
            throws GenerblSecurityException;
}
