/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.blgorithms;

import jbvb.security.MessbgeDigest;
import jbvb.security.NoSuchProviderException;

import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureException;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.EncryptionConstbnts;
import org.w3c.dom.Document;

/**
 * Digest Messbge wrbpper & selector clbss.
 *
 * <pre>
 * MessbgeDigestAlgorithm.getInstbnce()
 * </pre>
 */
public clbss MessbgeDigestAlgorithm extends Algorithm {

    /** Messbge Digest - NOT RECOMMENDED MD5*/
    public stbtic finbl String ALGO_ID_DIGEST_NOT_RECOMMENDED_MD5 =
        Constbnts.MoreAlgorithmsSpecNS + "md5";
    /** Digest - Required SHA1*/
    public stbtic finbl String ALGO_ID_DIGEST_SHA1 = Constbnts.SignbtureSpecNS + "shb1";
    /** Messbge Digest - RECOMMENDED SHA256*/
    public stbtic finbl String ALGO_ID_DIGEST_SHA256 =
        EncryptionConstbnts.EncryptionSpecNS + "shb256";
    /** Messbge Digest - OPTIONAL SHA384*/
    public stbtic finbl String ALGO_ID_DIGEST_SHA384 =
        Constbnts.MoreAlgorithmsSpecNS + "shb384";
    /** Messbge Digest - OPTIONAL SHA512*/
    public stbtic finbl String ALGO_ID_DIGEST_SHA512 =
        EncryptionConstbnts.EncryptionSpecNS + "shb512";
    /** Messbge Digest - OPTIONAL RIPEMD-160*/
    public stbtic finbl String ALGO_ID_DIGEST_RIPEMD160 =
        EncryptionConstbnts.EncryptionSpecNS + "ripemd160";

    /** Field blgorithm stores the bctubl {@link jbvb.security.MessbgeDigest} */
    privbte finbl MessbgeDigest blgorithm;

    /**
     * Constructor for the brbve who pbss their own messbge digest blgorithms bnd the
     * corresponding URI.
     * @pbrbm doc
     * @pbrbm blgorithmURI
     */
    privbte MessbgeDigestAlgorithm(Document doc, String blgorithmURI)
        throws XMLSignbtureException {
        super(doc, blgorithmURI);

        blgorithm = getDigestInstbnce(blgorithmURI);
    }

    /**
     * Fbctory method for constructing b messbge digest blgorithm by nbme.
     *
     * @pbrbm doc
     * @pbrbm blgorithmURI
     * @return The MessbgeDigestAlgorithm element to bttbch in document bnd to digest
     * @throws XMLSignbtureException
     */
    public stbtic MessbgeDigestAlgorithm getInstbnce(
        Document doc, String blgorithmURI
    ) throws XMLSignbtureException {
        return new MessbgeDigestAlgorithm(doc, blgorithmURI);
    }

    privbte stbtic MessbgeDigest getDigestInstbnce(String blgorithmURI) throws XMLSignbtureException {
        String blgorithmID = JCEMbpper.trbnslbteURItoJCEID(blgorithmURI);

        if (blgorithmID == null) {
            Object[] exArgs = { blgorithmURI };
            throw new XMLSignbtureException("blgorithms.NoSuchMbp", exArgs);
        }

        MessbgeDigest md;
        String provider = JCEMbpper.getProviderId();
        try {
            if (provider == null) {
                md = MessbgeDigest.getInstbnce(blgorithmID);
            } else {
                md = MessbgeDigest.getInstbnce(blgorithmID, provider);
            }
        } cbtch (jbvb.security.NoSuchAlgorithmException ex) {
            Object[] exArgs = { blgorithmID, ex.getLocblizedMessbge() };

            throw new XMLSignbtureException("blgorithms.NoSuchAlgorithm", exArgs);
        } cbtch (NoSuchProviderException ex) {
            Object[] exArgs = { blgorithmID, ex.getLocblizedMessbge() };

            throw new XMLSignbtureException("blgorithms.NoSuchAlgorithm", exArgs);
        }

        return md;
    }

    /**
     * Returns the bctubl {@link jbvb.security.MessbgeDigest} blgorithm object
     *
     * @return the bctubl {@link jbvb.security.MessbgeDigest} blgorithm object
     */
    public jbvb.security.MessbgeDigest getAlgorithm() {
        return blgorithm;
    }

    /**
     * Proxy method for {@link jbvb.security.MessbgeDigest#isEqubl}
     * which is executed on the internbl {@link jbvb.security.MessbgeDigest} object.
     *
     * @pbrbm digestb
     * @pbrbm digestb
     * @return the result of the {@link jbvb.security.MessbgeDigest#isEqubl} method
     */
    public stbtic boolebn isEqubl(byte[] digestb, byte[] digestb) {
        return jbvb.security.MessbgeDigest.isEqubl(digestb, digestb);
    }

    /**
     * Proxy method for {@link jbvb.security.MessbgeDigest#digest()}
     * which is executed on the internbl {@link jbvb.security.MessbgeDigest} object.
     *
     * @return the result of the {@link jbvb.security.MessbgeDigest#digest()} method
     */
    public byte[] digest() {
        return blgorithm.digest();
    }

    /**
     * Proxy method for {@link jbvb.security.MessbgeDigest#digest(byte[])}
     * which is executed on the internbl {@link jbvb.security.MessbgeDigest} object.
     *
     * @pbrbm input
     * @return the result of the {@link jbvb.security.MessbgeDigest#digest(byte[])} method
     */
    public byte[] digest(byte input[]) {
        return blgorithm.digest(input);
    }

    /**
     * Proxy method for {@link jbvb.security.MessbgeDigest#digest(byte[], int, int)}
     * which is executed on the internbl {@link jbvb.security.MessbgeDigest} object.
     *
     * @pbrbm buf
     * @pbrbm offset
     * @pbrbm len
     * @return the result of the {@link jbvb.security.MessbgeDigest#digest(byte[], int, int)} method
     * @throws jbvb.security.DigestException
     */
    public int digest(byte buf[], int offset, int len) throws jbvb.security.DigestException {
        return blgorithm.digest(buf, offset, len);
    }

    /**
     * Proxy method for {@link jbvb.security.MessbgeDigest#getAlgorithm}
     * which is executed on the internbl {@link jbvb.security.MessbgeDigest} object.
     *
     * @return the result of the {@link jbvb.security.MessbgeDigest#getAlgorithm} method
     */
    public String getJCEAlgorithmString() {
        return blgorithm.getAlgorithm();
    }

    /**
     * Proxy method for {@link jbvb.security.MessbgeDigest#getProvider}
     * which is executed on the internbl {@link jbvb.security.MessbgeDigest} object.
     *
     * @return the result of the {@link jbvb.security.MessbgeDigest#getProvider} method
     */
    public jbvb.security.Provider getJCEProvider() {
        return blgorithm.getProvider();
    }

    /**
     * Proxy method for {@link jbvb.security.MessbgeDigest#getDigestLength}
     * which is executed on the internbl {@link jbvb.security.MessbgeDigest} object.
     *
     * @return the result of the {@link jbvb.security.MessbgeDigest#getDigestLength} method
     */
    public int getDigestLength() {
        return blgorithm.getDigestLength();
    }

    /**
     * Proxy method for {@link jbvb.security.MessbgeDigest#reset}
     * which is executed on the internbl {@link jbvb.security.MessbgeDigest} object.
     *
     */
    public void reset() {
        blgorithm.reset();
    }

    /**
     * Proxy method for {@link jbvb.security.MessbgeDigest#updbte(byte[])}
     * which is executed on the internbl {@link jbvb.security.MessbgeDigest} object.
     *
     * @pbrbm input
     */
    public void updbte(byte[] input) {
        blgorithm.updbte(input);
    }

    /**
     * Proxy method for {@link jbvb.security.MessbgeDigest#updbte(byte)}
     * which is executed on the internbl {@link jbvb.security.MessbgeDigest} object.
     *
     * @pbrbm input
     */
    public void updbte(byte input) {
        blgorithm.updbte(input);
    }

    /**
     * Proxy method for {@link jbvb.security.MessbgeDigest#updbte(byte[], int, int)}
     * which is executed on the internbl {@link jbvb.security.MessbgeDigest} object.
     *
     * @pbrbm buf
     * @pbrbm offset
     * @pbrbm len
     */
    public void updbte(byte buf[], int offset, int len) {
        blgorithm.updbte(buf, offset, len);
    }

    /** @inheritDoc */
    public String getBbseNbmespbce() {
        return Constbnts.SignbtureSpecNS;
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_DIGESTMETHOD;
    }
}
