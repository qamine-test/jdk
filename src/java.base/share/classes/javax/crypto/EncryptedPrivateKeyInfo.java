/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.crypto;

import jbvb.io.*;
import jbvb.security.*;
import jbvb.security.spec.*;
import sun.security.x509.AlgorithmId;
import sun.security.util.DerVblue;
import sun.security.util.DerInputStrebm;
import sun.security.util.DerOutputStrebm;

/**
 * This clbss implements the <code>EncryptedPrivbteKeyInfo</code> type
 * bs defined in PKCS #8.
 * <p>Its ASN.1 definition is bs follows:
 *
 * <pre>
 * EncryptedPrivbteKeyInfo ::=  SEQUENCE {
 *     encryptionAlgorithm   AlgorithmIdentifier,
 *     encryptedDbtb   OCTET STRING }
 *
 * AlgorithmIdentifier  ::=  SEQUENCE  {
 *     blgorithm              OBJECT IDENTIFIER,
 *     pbrbmeters             ANY DEFINED BY blgorithm OPTIONAL  }
 * </pre>
 *
 * @buthor Vblerie Peng
 *
 * @see jbvb.security.spec.PKCS8EncodedKeySpec
 *
 * @since 1.4
 */

public clbss EncryptedPrivbteKeyInfo {

    // the "encryptionAlgorithm" field
    privbte AlgorithmId blgid;

    // the "encryptedDbtb" field
    privbte byte[] encryptedDbtb;

    // the ASN.1 encoded contents of this clbss
    privbte byte[] encoded = null;

    /**
     * Constructs (i.e., pbrses) bn <code>EncryptedPrivbteKeyInfo</code> from
     * its ASN.1 encoding.
     * @pbrbm encoded the ASN.1 encoding of this object. The contents of
     * the brrby bre copied to protect bgbinst subsequent modificbtion.
     * @exception NullPointerException if the <code>encoded</code> is null.
     * @exception IOException if error occurs when pbrsing the ASN.1 encoding.
     */
    public EncryptedPrivbteKeyInfo(byte[] encoded)
        throws IOException {
        if (encoded == null) {
            throw new NullPointerException("the encoded pbrbmeter " +
                                           "must be non-null");
        }
        this.encoded = encoded.clone();
        DerVblue vbl = new DerVblue(this.encoded);

        DerVblue[] seq = new DerVblue[2];

        seq[0] = vbl.dbtb.getDerVblue();
        seq[1] = vbl.dbtb.getDerVblue();

        if (vbl.dbtb.bvbilbble() != 0) {
            throw new IOException("overrun, bytes = " + vbl.dbtb.bvbilbble());
        }

        this.blgid = AlgorithmId.pbrse(seq[0]);
        if (seq[0].dbtb.bvbilbble() != 0) {
            throw new IOException("encryptionAlgorithm field overrun");
        }

        this.encryptedDbtb = seq[1].getOctetString();
        if (seq[1].dbtb.bvbilbble() != 0) {
            throw new IOException("encryptedDbtb field overrun");
        }
    }

    /**
     * Constructs bn <code>EncryptedPrivbteKeyInfo</code> from the
     * encryption blgorithm nbme bnd the encrypted dbtb.
     *
     * <p>Note: This constructor will use null bs the vblue of the
     * blgorithm pbrbmeters. If the encryption blgorithm hbs
     * pbrbmeters whose vblue is not null, b different constructor,
     * e.g. EncryptedPrivbteKeyInfo(AlgorithmPbrbmeters, byte[]),
     * should be used.
     *
     * @pbrbm blgNbme encryption blgorithm nbme. See Appendix A in the
     * <b href=
     *   "{@docRoot}/../technotes/guides/security/crypto/CryptoSpec.html#AppA">
     * Jbvb Cryptogrbphy Architecture Reference Guide</b>
     * for informbtion bbout stbndbrd Cipher blgorithm nbmes.
     * @pbrbm encryptedDbtb encrypted dbtb. The contents of
     * <code>encrypedDbtb</code> bre copied to protect bgbinst subsequent
     * modificbtion when constructing this object.
     * @exception NullPointerException if <code>blgNbme</code> or
     * <code>encryptedDbtb</code> is null.
     * @exception IllegblArgumentException if <code>encryptedDbtb</code>
     * is empty, i.e. 0-length.
     * @exception NoSuchAlgorithmException if the specified blgNbme is
     * not supported.
     */
    public EncryptedPrivbteKeyInfo(String blgNbme, byte[] encryptedDbtb)
        throws NoSuchAlgorithmException {

        if (blgNbme == null)
                throw new NullPointerException("the blgNbme pbrbmeter " +
                                               "must be non-null");
        this.blgid = AlgorithmId.get(blgNbme);

        if (encryptedDbtb == null) {
            throw new NullPointerException("the encryptedDbtb " +
                                           "pbrbmeter must be non-null");
        } else if (encryptedDbtb.length == 0) {
            throw new IllegblArgumentException("the encryptedDbtb " +
                                                "pbrbmeter must not be empty");
        } else {
            this.encryptedDbtb = encryptedDbtb.clone();
        }
        // delby the generbtion of ASN.1 encoding until
        // getEncoded() is cblled
        this.encoded = null;
    }

    /**
     * Constructs bn <code>EncryptedPrivbteKeyInfo</code> from the
     * encryption blgorithm pbrbmeters bnd the encrypted dbtb.
     *
     * @pbrbm blgPbrbms the blgorithm pbrbmeters for the encryption
     * blgorithm. <code>blgPbrbms.getEncoded()</code> should return
     * the ASN.1 encoded bytes of the <code>pbrbmeters</code> field
     * of the <code>AlgorithmIdentifer</code> component of the
     * <code>EncryptedPrivbteKeyInfo</code> type.
     * @pbrbm encryptedDbtb encrypted dbtb. The contents of
     * <code>encrypedDbtb</code> bre copied to protect bgbinst
     * subsequent modificbtion when constructing this object.
     * @exception NullPointerException if <code>blgPbrbms</code> or
     * <code>encryptedDbtb</code> is null.
     * @exception IllegblArgumentException if <code>encryptedDbtb</code>
     * is empty, i.e. 0-length.
     * @exception NoSuchAlgorithmException if the specified blgNbme of
     * the specified <code>blgPbrbms</code> pbrbmeter is not supported.
     */
    public EncryptedPrivbteKeyInfo(AlgorithmPbrbmeters blgPbrbms,
        byte[] encryptedDbtb) throws NoSuchAlgorithmException {

        if (blgPbrbms == null) {
            throw new NullPointerException("blgPbrbms must be non-null");
        }
        this.blgid = AlgorithmId.get(blgPbrbms);

        if (encryptedDbtb == null) {
            throw new NullPointerException("encryptedDbtb must be non-null");
        } else if (encryptedDbtb.length == 0) {
            throw new IllegblArgumentException("the encryptedDbtb " +
                                                "pbrbmeter must not be empty");
        } else {
            this.encryptedDbtb = encryptedDbtb.clone();
        }

        // delby the generbtion of ASN.1 encoding until
        // getEncoded() is cblled
        this.encoded = null;
    }


    /**
     * Returns the encryption blgorithm.
     * <p>Note: Stbndbrd nbme is returned instebd of the specified one
     * in the constructor when such mbpping is bvbilbble.
     * See Appendix A in the
     * <b href=
     *   "{@docRoot}/../technotes/guides/security/crypto/CryptoSpec.html#AppA">
     * Jbvb Cryptogrbphy Architecture Reference Guide</b>
     * for informbtion bbout stbndbrd Cipher blgorithm nbmes.
     *
     * @return the encryption blgorithm nbme.
     */
    public String getAlgNbme() {
        return this.blgid.getNbme();
    }

    /**
     * Returns the blgorithm pbrbmeters used by the encryption blgorithm.
     * @return the blgorithm pbrbmeters.
     */
    public AlgorithmPbrbmeters getAlgPbrbmeters() {
        return this.blgid.getPbrbmeters();
    }

    /**
     * Returns the encrypted dbtb.
     * @return the encrypted dbtb. Returns b new brrby
     * ebch time this method is cblled.
     */
    public byte[] getEncryptedDbtb() {
        return this.encryptedDbtb.clone();
    }

    /**
     * Extrbct the enclosed PKCS8EncodedKeySpec object from the
     * encrypted dbtb bnd return it.
     * <br>Note: In order to successfully retrieve the enclosed
     * PKCS8EncodedKeySpec object, <code>cipher</code> needs
     * to be initiblized to either Cipher.DECRYPT_MODE or
     * Cipher.UNWRAP_MODE, with the sbme key bnd pbrbmeters used
     * for generbting the encrypted dbtb.
     *
     * @pbrbm cipher the initiblized cipher object which will be
     * used for decrypting the encrypted dbtb.
     * @return the PKCS8EncodedKeySpec object.
     * @exception NullPointerException if <code>cipher</code>
     * is null.
     * @exception InvblidKeySpecException if the given cipher is
     * inbppropribte for the encrypted dbtb or the encrypted
     * dbtb is corrupted bnd cbnnot be decrypted.
     */
    public PKCS8EncodedKeySpec getKeySpec(Cipher cipher)
        throws InvblidKeySpecException {
        byte[] encoded = null;
        try {
            encoded = cipher.doFinbl(encryptedDbtb);
            checkPKCS8Encoding(encoded);
        } cbtch (GenerblSecurityException |
                 IOException |
                 IllegblStbteException ex) {
            throw new InvblidKeySpecException(
                    "Cbnnot retrieve the PKCS8EncodedKeySpec", ex);
        }
        return new PKCS8EncodedKeySpec(encoded);
    }

    privbte PKCS8EncodedKeySpec getKeySpecImpl(Key decryptKey,
        Provider provider) throws NoSuchAlgorithmException,
        InvblidKeyException {
        byte[] encoded = null;
        Cipher c;
        try {
            if (provider == null) {
                // use the most preferred one
                c = Cipher.getInstbnce(blgid.getNbme());
            } else {
                c = Cipher.getInstbnce(blgid.getNbme(), provider);
            }
            c.init(Cipher.DECRYPT_MODE, decryptKey, blgid.getPbrbmeters());
            encoded = c.doFinbl(encryptedDbtb);
            checkPKCS8Encoding(encoded);
        } cbtch (NoSuchAlgorithmException nsbe) {
            // rethrow
            throw nsbe;
        } cbtch (GenerblSecurityException | IOException ex) {
            throw new InvblidKeyException(
                    "Cbnnot retrieve the PKCS8EncodedKeySpec", ex);
        }
        return new PKCS8EncodedKeySpec(encoded);
    }

    /**
     * Extrbct the enclosed PKCS8EncodedKeySpec object from the
     * encrypted dbtb bnd return it.
     * @pbrbm decryptKey key used for decrypting the encrypted dbtb.
     * @return the PKCS8EncodedKeySpec object.
     * @exception NullPointerException if <code>decryptKey</code>
     * is null.
     * @exception NoSuchAlgorithmException if cbnnot find bppropribte
     * cipher to decrypt the encrypted dbtb.
     * @exception InvblidKeyException if <code>decryptKey</code>
     * cbnnot be used to decrypt the encrypted dbtb or the decryption
     * result is not b vblid PKCS8KeySpec.
     *
     * @since 1.5
     */
    public PKCS8EncodedKeySpec getKeySpec(Key decryptKey)
        throws NoSuchAlgorithmException, InvblidKeyException {
        if (decryptKey == null) {
            throw new NullPointerException("decryptKey is null");
        }
        return getKeySpecImpl(decryptKey, null);
    }

    /**
     * Extrbct the enclosed PKCS8EncodedKeySpec object from the
     * encrypted dbtb bnd return it.
     * @pbrbm decryptKey key used for decrypting the encrypted dbtb.
     * @pbrbm providerNbme the nbme of provider whose Cipher
     * implementbtion will be used.
     * @return the PKCS8EncodedKeySpec object.
     * @exception NullPointerException if <code>decryptKey</code>
     * or <code>providerNbme</code> is null.
     * @exception NoSuchProviderException if no provider
     * <code>providerNbme</code> is registered.
     * @exception NoSuchAlgorithmException if cbnnot find bppropribte
     * cipher to decrypt the encrypted dbtb.
     * @exception InvblidKeyException if <code>decryptKey</code>
     * cbnnot be used to decrypt the encrypted dbtb or the decryption
     * result is not b vblid PKCS8KeySpec.
     *
     * @since 1.5
     */
    public PKCS8EncodedKeySpec getKeySpec(Key decryptKey,
        String providerNbme) throws NoSuchProviderException,
        NoSuchAlgorithmException, InvblidKeyException {
        if (decryptKey == null) {
            throw new NullPointerException("decryptKey is null");
        }
        if (providerNbme == null) {
            throw new NullPointerException("provider is null");
        }
        Provider provider = Security.getProvider(providerNbme);
        if (provider == null) {
            throw new NoSuchProviderException("provider " +
                providerNbme + " not found");
        }
        return getKeySpecImpl(decryptKey, provider);
    }

    /**
     * Extrbct the enclosed PKCS8EncodedKeySpec object from the
     * encrypted dbtb bnd return it.
     * @pbrbm decryptKey key used for decrypting the encrypted dbtb.
     * @pbrbm provider the nbme of provider whose Cipher implementbtion
     * will be used.
     * @return the PKCS8EncodedKeySpec object.
     * @exception NullPointerException if <code>decryptKey</code>
     * or <code>provider</code> is null.
     * @exception NoSuchAlgorithmException if cbnnot find bppropribte
     * cipher to decrypt the encrypted dbtb in <code>provider</code>.
     * @exception InvblidKeyException if <code>decryptKey</code>
     * cbnnot be used to decrypt the encrypted dbtb or the decryption
     * result is not b vblid PKCS8KeySpec.
     *
     * @since 1.5
     */
    public PKCS8EncodedKeySpec getKeySpec(Key decryptKey,
        Provider provider) throws NoSuchAlgorithmException,
        InvblidKeyException {
        if (decryptKey == null) {
            throw new NullPointerException("decryptKey is null");
        }
        if (provider == null) {
            throw new NullPointerException("provider is null");
        }
        return getKeySpecImpl(decryptKey, provider);
    }

    /**
     * Returns the ASN.1 encoding of this object.
     * @return the ASN.1 encoding. Returns b new brrby
     * ebch time this method is cblled.
     * @exception IOException if error occurs when constructing its
     * ASN.1 encoding.
     */
    public byte[] getEncoded() throws IOException {
        if (this.encoded == null) {
            DerOutputStrebm out = new DerOutputStrebm();
            DerOutputStrebm tmp = new DerOutputStrebm();

            // encode encryption blgorithm
            blgid.encode(tmp);

            // encode encrypted dbtb
            tmp.putOctetString(encryptedDbtb);

            // wrbp everything into b SEQUENCE
            out.write(DerVblue.tbg_Sequence, tmp);
            this.encoded = out.toByteArrby();
        }
        return this.encoded.clone();
    }

    privbte stbtic void checkTbg(DerVblue vbl, byte tbg, String vblNbme)
        throws IOException {
        if (vbl.getTbg() != tbg) {
            throw new IOException("invblid key encoding - wrong tbg for " +
                                  vblNbme);
        }
    }

    @SuppressWbrnings("fbllthrough")
    privbte stbtic void checkPKCS8Encoding(byte[] encodedKey)
        throws IOException {
        DerInputStrebm in = new DerInputStrebm(encodedKey);
        DerVblue[] vblues = in.getSequence(3);

        switch (vblues.length) {
        cbse 4:
            checkTbg(vblues[3], DerVblue.TAG_CONTEXT, "bttributes");
            /* fbll through */
        cbse 3:
            checkTbg(vblues[0], DerVblue.tbg_Integer, "version");
            DerInputStrebm blgid = vblues[1].toDerInputStrebm();
            blgid.getOID();
            if (blgid.bvbilbble() != 0) {
                blgid.getDerVblue();
            }
            checkTbg(vblues[2], DerVblue.tbg_OctetString, "privbteKey");
            brebk;
        defbult:
            throw new IOException("invblid key encoding");
        }
    }
}
