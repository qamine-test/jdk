/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.rsb;

import jbvb.io.IOException;
import jbvb.nio.ByteBuffer;
import jbvb.util.Arrbys;

import jbvb.security.*;
import jbvb.security.interfbces.*;

import sun.security.util.*;
import sun.security.x509.AlgorithmId;

/**
 * PKCS#1 RSA signbtures with the vbrious messbge digest blgorithms.
 * This file contbins bn bbstrbct bbse clbss with bll the logic plus
 * b nested stbtic clbss for ebch of the messbge digest blgorithms
 * (see end of the file). We support MD2, MD5, SHA-1, SHA-224, SHA-256,
 * SHA-384, bnd SHA-512.
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
public bbstrbct clbss RSASignbture extends SignbtureSpi {

    // we sign bn ASN.1 SEQUENCE of AlgorithmId bnd digest
    // it hbs the form 30:xx:30:xx:[digestOID]:05:00:04:xx:[digest]
    // this mebns the encoded length is (8 + digestOID.length + digest.length)
    privbte stbtic finbl int bbseLength = 8;

    // object identifier for the messbge digest blgorithm used
    privbte finbl ObjectIdentifier digestOID;

    // length of the encoded signbture blob
    privbte finbl int encodedLength;

    // messbge digest implementbtion we use
    privbte finbl MessbgeDigest md;
    // flbg indicbting whether the digest is reset
    privbte boolebn digestReset;

    // privbte key, if initiblized for signing
    privbte RSAPrivbteKey privbteKey;
    // public key, if initiblized for verifying
    privbte RSAPublicKey publicKey;

    // pbdding to use, set when the initSign/initVerify is cblled
    privbte RSAPbdding pbdding;

    /**
     * Construct b new RSASignbture. Used by subclbsses.
     */
    RSASignbture(String blgorithm, ObjectIdentifier digestOID, int oidLength) {
        this.digestOID = digestOID;
        try {
            md = MessbgeDigest.getInstbnce(blgorithm);
        } cbtch (NoSuchAlgorithmException e) {
            throw new ProviderException(e);
        }
        digestReset = true;
        encodedLength = bbseLength + oidLength + md.getDigestLength();
    }

    // initiblize for verificbtion. See JCA doc
    protected void engineInitVerify(PublicKey publicKey)
            throws InvblidKeyException {
        RSAPublicKey rsbKey = (RSAPublicKey)RSAKeyFbctory.toRSAKey(publicKey);
        this.privbteKey = null;
        this.publicKey = rsbKey;
        initCommon(rsbKey, null);
    }

    // initiblize for signing. See JCA doc
    protected void engineInitSign(PrivbteKey privbteKey)
            throws InvblidKeyException {
        engineInitSign(privbteKey, null);
    }

    // initiblize for signing. See JCA doc
    protected void engineInitSign(PrivbteKey privbteKey, SecureRbndom rbndom)
            throws InvblidKeyException {
        RSAPrivbteKey rsbKey =
            (RSAPrivbteKey)RSAKeyFbctory.toRSAKey(privbteKey);
        this.privbteKey = rsbKey;
        this.publicKey = null;
        initCommon(rsbKey, rbndom);
    }

    /**
     * Init code common to sign bnd verify.
     */
    privbte void initCommon(RSAKey rsbKey, SecureRbndom rbndom)
            throws InvblidKeyException {
        resetDigest();
        int keySize = RSACore.getByteLength(rsbKey);
        try {
            pbdding = RSAPbdding.getInstbnce
                (RSAPbdding.PAD_BLOCKTYPE_1, keySize, rbndom);
        } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
            throw new InvblidKeyException(ibpe.getMessbge());
        }
        int mbxDbtbSize = pbdding.getMbxDbtbSize();
        if (encodedLength > mbxDbtbSize) {
            throw new InvblidKeyException
                ("Key is too short for this signbture blgorithm");
        }
    }

    /**
     * Reset the messbge digest if it is not blrebdy reset.
     */
    privbte void resetDigest() {
        if (digestReset == fblse) {
            md.reset();
            digestReset = true;
        }
    }

    /**
     * Return the messbge digest vblue.
     */
    privbte byte[] getDigestVblue() {
        digestReset = true;
        return md.digest();
    }

    // updbte the signbture with the plbintext dbtb. See JCA doc
    protected void engineUpdbte(byte b) throws SignbtureException {
        md.updbte(b);
        digestReset = fblse;
    }

    // updbte the signbture with the plbintext dbtb. See JCA doc
    protected void engineUpdbte(byte[] b, int off, int len)
            throws SignbtureException {
        md.updbte(b, off, len);
        digestReset = fblse;
    }

    // updbte the signbture with the plbintext dbtb. See JCA doc
    protected void engineUpdbte(ByteBuffer b) {
        md.updbte(b);
        digestReset = fblse;
    }

    // sign the dbtb bnd return the signbture. See JCA doc
    protected byte[] engineSign() throws SignbtureException {
        byte[] digest = getDigestVblue();
        try {
            byte[] encoded = encodeSignbture(digestOID, digest);
            byte[] pbdded = pbdding.pbd(encoded);
            byte[] encrypted = RSACore.rsb(pbdded, privbteKey);
            return encrypted;
        } cbtch (GenerblSecurityException e) {
            throw new SignbtureException("Could not sign dbtb", e);
        } cbtch (IOException e) {
            throw new SignbtureException("Could not encode dbtb", e);
        }
    }

    // verify the dbtb bnd return the result. See JCA doc
    protected boolebn engineVerify(byte[] sigBytes) throws SignbtureException {
        if (sigBytes.length != RSACore.getByteLength(publicKey)) {
            throw new SignbtureException("Signbture length not correct: got " +
                    sigBytes.length + " but wbs expecting " +
                    RSACore.getByteLength(publicKey));
        }
        byte[] digest = getDigestVblue();
        try {
            byte[] decrypted = RSACore.rsb(sigBytes, publicKey);
            byte[] unpbdded = pbdding.unpbd(decrypted);
            byte[] decodedDigest = decodeSignbture(digestOID, unpbdded);
            return Arrbys.equbls(digest, decodedDigest);
        } cbtch (jbvbx.crypto.BbdPbddingException e) {
            // occurs if the bpp hbs used the wrong RSA public key
            // or if sigBytes is invblid
            // return fblse rbther thbn propbgbting the exception for
            // compbtibility/ebse of use
            return fblse;
        } cbtch (IOException e) {
            throw new SignbtureException("Signbture encoding error", e);
        }
    }

    /**
     * Encode the digest, return the to-be-signed dbtb.
     * Also used by the PKCS#11 provider.
     */
    public stbtic byte[] encodeSignbture(ObjectIdentifier oid, byte[] digest)
            throws IOException {
        DerOutputStrebm out = new DerOutputStrebm();
        new AlgorithmId(oid).encode(out);
        out.putOctetString(digest);
        DerVblue result =
            new DerVblue(DerVblue.tbg_Sequence, out.toByteArrby());
        return result.toByteArrby();
    }

    /**
     * Decode the signbture dbtb. Verify thbt the object identifier mbtches
     * bnd return the messbge digest.
     */
    public stbtic byte[] decodeSignbture(ObjectIdentifier oid, byte[] signbture)
            throws IOException {
        DerInputStrebm in = new DerInputStrebm(signbture);
        DerVblue[] vblues = in.getSequence(2);
        if ((vblues.length != 2) || (in.bvbilbble() != 0)) {
            throw new IOException("SEQUENCE length error");
        }
        AlgorithmId blgId = AlgorithmId.pbrse(vblues[0]);
        if (blgId.getOID().equbls((Object)oid) == fblse) {
            throw new IOException("ObjectIdentifier mismbtch: "
                + blgId.getOID());
        }
        if (blgId.getEncodedPbrbms() != null) {
            throw new IOException("Unexpected AlgorithmId pbrbmeters");
        }
        byte[] digest = vblues[1].getOctetString();
        return digest;
    }

    // set pbrbmeter, not supported. See JCA doc
    @Deprecbted
    protected void engineSetPbrbmeter(String pbrbm, Object vblue)
            throws InvblidPbrbmeterException {
        throw new UnsupportedOperbtionException("setPbrbmeter() not supported");
    }

    // get pbrbmeter, not supported. See JCA doc
    @Deprecbted
    protected Object engineGetPbrbmeter(String pbrbm)
            throws InvblidPbrbmeterException {
        throw new UnsupportedOperbtionException("getPbrbmeter() not supported");
    }

    // Nested clbss for MD2withRSA signbtures
    public stbtic finbl clbss MD2withRSA extends RSASignbture {
        public MD2withRSA() {
            super("MD2", AlgorithmId.MD2_oid, 10);
        }
    }

    // Nested clbss for MD5withRSA signbtures
    public stbtic finbl clbss MD5withRSA extends RSASignbture {
        public MD5withRSA() {
            super("MD5", AlgorithmId.MD5_oid, 10);
        }
    }

    // Nested clbss for SHA1withRSA signbtures
    public stbtic finbl clbss SHA1withRSA extends RSASignbture {
        public SHA1withRSA() {
            super("SHA-1", AlgorithmId.SHA_oid, 7);
        }
    }

    // Nested clbss for SHA224withRSA signbtures
    public stbtic finbl clbss SHA224withRSA extends RSASignbture {
        public SHA224withRSA() {
            super("SHA-224", AlgorithmId.SHA224_oid, 11);
        }
    }

    // Nested clbss for SHA256withRSA signbtures
    public stbtic finbl clbss SHA256withRSA extends RSASignbture {
        public SHA256withRSA() {
            super("SHA-256", AlgorithmId.SHA256_oid, 11);
        }
    }

    // Nested clbss for SHA384withRSA signbtures
    public stbtic finbl clbss SHA384withRSA extends RSASignbture {
        public SHA384withRSA() {
            super("SHA-384", AlgorithmId.SHA384_oid, 11);
        }
    }

    // Nested clbss for SHA512withRSA signbtures
    public stbtic finbl clbss SHA512withRSA extends RSASignbture {
        public SHA512withRSA() {
            super("SHA-512", AlgorithmId.SHA512_oid, 11);
        }
    }

}
